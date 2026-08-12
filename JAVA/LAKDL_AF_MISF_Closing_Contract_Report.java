// CREATED BY SAMITHA KULATILAKA
// ON 2011-07-12
// SCREEN NAME : AF_CR_PRO_CLOSING_CONTRACT_REPORT

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class LAKDL_AF_MISF_Closing_Contract_Report extends HttpServlet {
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        Connection connection   = null;
        Statement statement     = null;
        Statement statement1    = null;
        ResultSet resultSet     = null;
        ResultSet resultSet1    = null;
        java.text.NumberFormat nf,nf1;
        ServletOutputStream out = null;
        String sqlQuery = null;
        
        try {
            
            LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
            // SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
            
            connection = con_method.met_user_validate(req);
            
            String m_html_client_url    = con_method.html_client_url.trim();
            String m_class_url          = con_method.servlet_client_url.trim() + ":" + con_method.client_t3_port.trim();
            String header_name          = con_method.header_name.trim();
            String m_schema_name        = con_method.schema_name.trim();
            String m_fschema_name       = con_method.client_name.trim();
            // String m_servlet_client_url = con_method.servlet_client_url.trim();
            // String m_client_name        = con_method.client_name.trim();
            // String m_client_t3_port     = con_method.client_t3_port.trim();
            // String m_username           = con_method.username.trim();
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
            
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			
            out = res.getOutputStream();
            
            
            String screenType = req.getParameter("chksql");
            
            if (screenType.equals("main_page")) {
                
                String m_date_dd = "";
                String m_date_mm = "";
                String m_date_yy = "";
                
                sqlQuery = " " +
                    "   SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') \"SYSDATE\" " +
                    "   FROM   DUAL " +
                    " ";
                
                statement  = connection.createStatement();
                resultSet = statement.executeQuery(sqlQuery);
                resultSet.next();
                
                m_date_dd = resultSet.getString("SYSDATE").substring(0, 2);
                m_date_mm = resultSet.getString("SYSDATE").substring(3, 5);
                m_date_yy = resultSet.getString("SYSDATE").substring(6, 10);
                
                resultSet.close();
                statement.close();
                
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System - Closing Contract Report</title>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
                
                out.println("       <script type=\"text/javascript\">");
                
                out.println("           var g_date_dd = '" + m_date_dd + "';");
                out.println("           var g_date_mm = '" + m_date_mm + "';");
                out.println("           var g_date_yy = '" + m_date_yy + "';");
                
                
                out.println("           function load_roll_value(m_val) {");
                out.println("               document.getElementById('help_box').innerHTML = 'Closing Contract Report - ' + m_val;");
                out.println("           }");
                
                
                out.println("           function load_roll_out_value() {");
                out.println("               document.getElementById('help_box').innerHTML = 'Closing Contract Report';");
                out.println("           }");
                
                
                out.println("           function clear_window() {");
                out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
                out.println("                   window.location.href = window.location.href;");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_screen_status(m_val) {");
                // out.println("               if( m_val == 'HELP') {");
                // out.println("                   load_help_msg();");
                // out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_help_msg() {"); 
                out.println("               var m_help_message = 'm_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report';");
                out.println("               HelpBox_msg(m_help_message);"); 
                out.println("           }");
                
                
                out.println("           function HelpBox_msg(m_help_message) {");
                out.println("               popupwin = window.showModalDialog(servlet_client_url + \":\" + client_t3_port + \"/\" + client_name + \"AF_MAS_Help_Msg_Servlet?class_in=\" + client_name + \"AF_MAS_Help_Msg_select\" + \"&help_message_in=\" + m_help_message);");
                out.println("           }");
                
                
                out.println("           function getSystemDate() {");
                out.println("               document.getElementById('TXT_FROM_DAY').value   = g_date_dd;");
                out.println("               document.getElementById('TXT_FROM_MONTH').value = g_date_mm;");
                out.println("               document.getElementById('TXT_FROM_YEAR').value  = g_date_yy;");
                out.println("               document.getElementById('TXT_TO_DAY').value     = g_date_dd;");
                out.println("               document.getElementById('TXT_TO_MONTH').value   = g_date_mm;");
                out.println("               document.getElementById('TXT_TO_YEAR').value    = g_date_yy;");
                out.println("           }");
                
                
                out.println("           function loadCalendar(num) {");
                out.println("               document.Form1.hid_cal_date.value = num;");
                out.println("               popupwin = window.open(servlet_client_url + \":\" + client_t3_port + \"/\" + client_name + \"CO_Calendar_Window\", \"oBj\", \"left=190,top=380,width=320,height=230\");");
                out.println("           }");
                
                
                out.println("           function view_report() {");
                out.println("               var m_from_date = document.getElementById('TXT_FROM_DAY').value + '-' + document.getElementById('TXT_FROM_MONTH').value + '-' + document.getElementById('TXT_FROM_YEAR').value;");
                out.println("               var m_to_date = document.getElementById('TXT_TO_DAY').value + '-' + document.getElementById('TXT_TO_MONTH').value + '-' + document.getElementById('TXT_TO_YEAR').value;");
                // out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termination_Report?chksql=view_report&rpt_type=\"+m_rpt_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"\";");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_MISF_Closing_Contract_Report?chksql=view_report&from_date=' + m_from_date + '&to_date=' + m_to_date + '&sort_column=AGREEMENT_NO&sort_order=ASC';");
                out.println("               popupwin = window.open(m_url, 'displayWindow1', 'left=10, top=110, width=975, height=450, toolbar=0, location=0, center:yes, direction=0, menuBar=1, status=0, scrollbars=1, resizable=1');");
                out.println("           }");
                
                
                out.println("           function check_date(objdd, objmm, objyy) {");
                out.println("               if ((objdd.value != '') && (objmm.value != '') && (objyy.value != '')) {");
                out.println("                   checkMonthLength(objdd, objmm, objyy);");
                out.println("               }");
                out.println("           }");
                
                
                out.println("function load_c_date(val) {");
                out.println("var date1='' ");
                out.println("var date2='' ");
                out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
                out.println("v_date=val.substr(0,val.indexOf('-'));");
                out.println("if(v_date.length<2)");
                out.println("v_date=0+v_date");
                out.println("val=val.substr(val.indexOf('-')+1,val.length);");
                out.println("v_month=val.substr(0,val.indexOf('-'));");
                out.println("if(v_month.length<2)");
                out.println("v_month=0+v_month");
                out.println("val=val.substr(val.indexOf('-')+1,val.length);");
                out.println("     document.Form1.TXT_FROM_DAY.value=v_date;");
                out.println("     document.Form1.TXT_FROM_MONTH.value=v_month;");
                out.println("     document.Form1.TXT_FROM_YEAR.value=val;");
                out.println("date1=v_date+'-'+v_month+'-'+val;");
                out.println("document.Form1.hid_from_date.value=date1");			
                out.println("}");
                
                out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
                out.println("v_date=val.substr(0,val.indexOf('-'));");
                out.println("if(v_date.length<2)");
                out.println("v_date=0+v_date");
                out.println("val=val.substr(val.indexOf('-')+1,val.length);");
                out.println("v_month=val.substr(0,val.indexOf('-'));");
                out.println("if(v_month.length<2)");
                out.println("v_month=0+v_month");
                out.println("val=val.substr(val.indexOf('-')+1,val.length);");
                out.println("     document.Form1.TXT_TO_DAY.value=v_date;");
                out.println("     document.Form1.TXT_TO_MONTH.value=v_month;");
                out.println("     document.Form1.TXT_TO_YEAR.value=val;");
                out.println("date2=document.Form1.TXT_TO_DAY.value+'-'+document.Form1.TXT_TO_MONTH.value+'-'+document.Form1.TXT_TO_YEAR.value;");
                out.println("document.Form1.hid_to_date.value=date2");
                out.println("}");
                out.println("}");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" onload=\"getSystemDate();\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                out.println("           <input type=\"hidden\" name=\"hid_cal_date\" />");
                out.println("           <input type=\"hidden\" name=\"hid_from_date\" />");
                out.println("           <input type=\"hidden\" name=\"hid_to_date\" />");
                
                out.println("           <table class=\"table\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("               <tr>");
                out.println("                   <td width=\"8\" valign=\"top\"><img src=\"" + m_html_client_url + "/images/spacer.gif\" width=\"8\" height=\"8\" /></td>");
                out.println("                   <td class=\"border_wht\" valign=\"top\">");
                out.println("                       <table class=\"table\" border=\"0\" height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                           <tr>");
                out.println("                               <td height=\"30\" class=\"pdn_mainHD\">" + header_name + "</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td height=\"1\"><img src=\"" + m_html_client_url + "/images/spacer.gif\" width=\"1\" height=\"1\" /></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td style=\"height: 327px;\">");
                out.println("                                   <table class=\"table\" border=\"0\" height=\"100%\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"1\"><img src=\"" + m_html_client_url + "/images/spacer.gif\" width=\"1\" height=\"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"pdn_txtpos2\" align=\"left\" style=\"height: 18px;\" id=\"help_box\">Closing Contract Report</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
                out.println("                                               <table class=\"table\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Cancel');\" onclick=\"clear_window();\" value=\"Cancel\" /></td>");
                out.println("                                                       <td width=\"100%\"></td>"); 
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Help');\" onclick=\"load_screen_status('HELP');\" value=\"Help\" /></td>");
                out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Close');\" onclick=\"close_window();\" value=\"Close\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"line\" height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" height=\"1\" width=\"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
                out.println("                                               <table class=\"table\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"></table>");
                out.println("                                               <br />");
                out.println("                                               <table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">");
                out.println("                                                   <tr class=\"tr_input\">");
                out.println("                                                       <td width=\"20%\" ID=\"DIV_FROM_DATE\">From</td>");
                out.println("                                                       <td width=\"30%\">");
                out.println("                                                           <input type=\"text\" id=\"TXT_FROM_DAY\" name=\"TXT_FROM_DAY\" maxlength=\"2\" style=\"width: 25px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_FROM_DAY, document.Form1.TXT_FROM_MONTH, document.Form1.TXT_FROM_YEAR);\" />");
                out.println("                                                           <input type=\"text\" id=\"TXT_FROM_MONTH\" name=\"TXT_FROM_MONTH\" maxlength=\"2\" style=\"width: 25px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_FROM_DAY, document.Form1.TXT_FROM_MONTH, document.Form1.TXT_FROM_YEAR);\" />");
                out.println("                                                           <input type=\"text\" id=\"TXT_FROM_YEAR\" name=\"TXT_FROM_YEAR\" maxlength=\"4\" style=\"width: 45px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_FROM_DAY, document.Form1.TXT_FROM_MONTH, document.Form1.TXT_FROM_YEAR);\" />");
                out.println("                                                           <a href style=\"cursor:hand;\" onclick=\"loadCalendar('2');\">Calendar</a>");
                out.println("                                                       </td>");
                out.println("                                                       <td width=\"20%\" ID=\"DIV_TO_DATE\">To</td>");
                out.println("                                                       <td width=\"30%\">");
                out.println("                                                           <input type=\"text\" id=\"TXT_TO_DAY\" name=\"TXT_TO_DAY\" maxlength=\"2\" style=\"width: 25px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_TO_DAY, document.Form1.TXT_TO_MONTH, document.Form1.TXT_TO_YEAR);\" />");
                out.println("                                                           <input type=\"text\" id=\"TXT_TO_MONTH\" name=\"TXT_TO_MONTH\" maxlength=\"2\" style=\"width: 25px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_TO_DAY, document.Form1.TXT_TO_MONTH, document.Form1.TXT_TO_YEAR);\" />");
                out.println("                                                           <input type=\"text\" id=\"TXT_TO_YEAR\" name=\"TXT_TO_YEAR\" maxlength=\"4\" style=\"width: 45px; text-align: right;\" class=\"txt_input\" onchange=\"check_date(document.Form1.TXT_TO_DAY, document.Form1.TXT_TO_MONTH, document.Form1.TXT_TO_YEAR);\" />");
                out.println("                                                           <a href style=\"cursor:hand;\" onclick=\"loadCalendar('3');\">Calendar</a>");
                out.println("                                                       </td>");
                out.println("                                                       <td><input type='button' class='but_input' style='width: 100px;' name='BUT_VIEW2' value=\"View Report\" onclick=\"view_report();\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                               </td>");
                out.println("                           </tr>");
                out.println("                       </table>");
                out.println("                   </td>");
                out.println("               </tr>");
                out.println("            </table>");
                
                out.println("       </form>");
                out.println("   </body>");
                out.println("</html>");
                
            }
            
            else if (screenType.equals("view_report")) {
                
                String m_from_date      = req.getParameter("from_date");
                String m_to_date        = req.getParameter("to_date");
                String m_sort_column    = req.getParameter("sort_column");
                String m_sort_order     = req.getParameter("sort_order");
                String m_sort           = null;
                int j = 1;
                
                if (m_sort_column.equals("AGREEMENT_NO")) {
                    m_sort = "FINANCE_NO" + " " + m_sort_order;
                }
                else if (m_sort_column.equals("CLIENT_NAME")) {
                    m_sort = "CLIENT_NAME" + " " + m_sort_order;
                }
                else if (m_sort_column.equals("ADDRESS")) {
                    m_sort = "ADDRESS1" + " " + m_sort_order + ", ADDRESS2" + " " + m_sort_order + ", CITY_NAME" + " " + m_sort_order;
                }
                else if (m_sort_column.equals("VEHICLE_REG_NO")) {
                    m_sort = "REG_NO" + " " + m_sort_order;
                }
                else if (m_sort_column.equals("CONTRACT_CLOSING_DATE")) {
                    m_sort = "CLOSE_DATE_1" + " " + m_sort_order;
                }
                else if (m_sort_column.equals("CONTACT_NO")) {
                    m_sort = "TEL_NO" + " " + m_sort_order;
                }
                
				
				// commented by udara 13-06-2016
				/*
                sqlQuery = " " +
                    "   SELECT NVL(A.FINANCE_NO, '-') FINANCE_NO, " +
                    // "          NVL(A.CLIENT_CODE, '-') CLIENT_CODE, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), '-') CLIENT_NAME, " +
                    "          NVL(NVL(B.ADDRESS1, B.REGISTERED_ADDRESS1), '-') ADDRESS1, " +
                    "          NVL(NVL(B.ADDRESS2, B.REGISTERED_ADDRESS2), '-') ADDRESS2, " +
                    // "          NVL(B.CITY_CODE, B.REGISTERED_CITY_CODE) CITY_CODE, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_CITY_NAME(NVL(B.CITY_CODE, B.REGISTERED_CITY_CODE)), '-') CITY_NAME, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO), '-') REG_NO, " +
                    "          NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'DD-MM-YYYY'), '-') CLOSE_DATE, " +
                    "          NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'YYYY-MM-DD'), '-') CLOSE_DATE_1, " +
                    "          NVL(NVL(B.TEL_NO, B.F_TEL_NO), '-') TEL_NO " +
                    // "          NVL(B.MOBILE_NO, '-') MOBILE_NO " +
                    "   FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " +
                    "          " + m_schema_name + ".AF_CO_MAS_CLIENT B " +
                    "   WHERE  A.CLIENT_CODE = B.CLIENT_CODE " +
                    "   AND    A.APPLICATION_STATUS = 'ACTIVATED' " +
                    "   AND    A.ACTIVATED_DATE IS NOT NULL " +
                    "   AND    " + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
                    "   AND    " + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
                    "   ORDER BY " + m_sort +
                    " ";
				*/
				
				// added by udara 13-06-2016
				
				sqlQuery = " " +
                    "   SELECT NVL(A.FINANCE_NO, '-') FINANCE_NO, " +
                    // "          NVL(A.CLIENT_CODE, '-') CLIENT_CODE, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), '-') CLIENT_NAME, " +
                    "          NVL(NVL(B.ADDRESS1, B.REGISTERED_ADDRESS1), '-') ADDRESS1, " +
                    "          NVL(NVL(B.ADDRESS2, B.REGISTERED_ADDRESS2), '-') ADDRESS2, " +
                    // "          NVL(B.CITY_CODE, B.REGISTERED_CITY_CODE) CITY_CODE, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_CITY_NAME(NVL(B.CITY_CODE, B.REGISTERED_CITY_CODE)), '-') CITY_NAME, " +
                    "          NVL(" + m_schema_name + ".AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO), '-') REG_NO, " +
                    "          NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'DD-MM-YYYY'), '-') CLOSE_DATE, " +
                    "          NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'YYYY-MM-DD'), '-') CLOSE_DATE_1, " +
                    "          NVL(NVL(B.TEL_NO, B.F_TEL_NO), '-') TEL_NO, " +
					" 		   NVL((SELECT SUM(C.NET_RENTAL_AMOUNT) FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT C WHERE C.APPLICATION_NO=A.APPLICATION_NO AND TO_NUMBER(INSTALLMENT_NO)= 1 ),'0') RENTAL_AMT "+ //Added by Kanchana on 2016-06-24
                    // "          NVL(B.MOBILE_NO, '-') MOBILE_NO " +
                    "   FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, " +
                    "          " + m_schema_name + ".AF_CO_MAS_CLIENT B " +
                    "   WHERE  A.CLIENT_CODE = B.CLIENT_CODE " +
                    //"   AND    A.APPLICATION_STATUS = 'ACTIVATED' " + // commented by udara 04-07-2016
					"   AND    A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') " + // added by udara 04-07-2016
                    "   AND    A.ACTIVATED_DATE IS NOT NULL " +
                    "   AND    " + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO) >= TO_DATE('" + m_from_date + "', 'DD-MM-YYYY') " +
                    "   AND    " + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO) <= TO_DATE('" + m_to_date + "', 'DD-MM-YYYY') " +
					"   GROUP BY  A.FINANCE_NO,A.CLIENT_CODE,B.REGISTERED_CITY_CODE,B.REGISTERED_ADDRESS1,B.REGISTERED_ADDRESS2,B.ADDRESS1,B.ADDRESS2,B.CITY_CODE,B.F_TEL_NO,B.TEL_NO,A.APPLICATION_NO"+//Added by Kanchana on 2016-06-24
					"   UNION "+
					
					
					" SELECT NVL(A.FINANCE_NO, '-') FINANCE_NO, "+
					  " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), '-') CLIENT_NAME, "+
					  " NVL(NVL(B.ADDRESS1, B.REGISTERED_ADDRESS1), '-') ADDRESS1, "+
					  " NVL(NVL(B.ADDRESS2, B.REGISTERED_ADDRESS2), '-') ADDRESS2, "+
					  " NVL(" + m_schema_name + ".AF_CO_GET_CITY_NAME(NVL(B.CITY_CODE, B.REGISTERED_CITY_CODE)), '-') CITY_NAME, "+
					  " NVL(" + m_schema_name + ".AF_CO_GET_ALL_REG_NUMBERS(A.FINANCE_NO), '-') REG_NO, "+
					  " NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'DD-MM-YYYY'), '-') CLOSE_DATE, "+
					  " NVL(TO_CHAR(" + m_schema_name + ".AF_CO_GET_APP_END_DATE(A.FINANCE_NO), 'YYYY-MM-DD'), '-') CLOSE_DATE_1, "+
					  " NVL(NVL(B.TEL_NO, B.F_TEL_NO), '-') TEL_NO, "+
					  " NVL((SELECT SUM(C.NET_RENTAL_AMOUNT) FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT C WHERE C.APPLICATION_NO=A.APPLICATION_NO AND TO_NUMBER(INSTALLMENT_NO)= 1 ),'0') RENTAL_AMT"+//Added by Kanchana on 2016-06-24	
					" FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A, "+
					  " " + m_schema_name + ".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE       = B.CLIENT_CODE "+
					" AND A.APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') "+
					" AND TRUNC(TO_DATE(" + m_schema_name + ".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'), 'MM') = TRUNC(TO_DATE('" + m_to_date + "','DD-MM-YYYY'), 'MM') "+
					
					" AND   A.FINANCE_NO IN ( "+
					     " SELECT FINANCE_NO "+
					     " from   " + m_schema_name + ".AF_CR_PRO_TERMINATION "+
					     " where    TERMINATION_VALIDITY_DATE >= TO_DATE('" + m_to_date + "','DD-MM-YYYY') "+
					     
					     " AND   A.FINANCE_NO NOT IN ( "+
					     " SELECT FINANCE_NO "+
					     " FROM   " + m_schema_name + ".AF_CR_PRO_TERMINATION "+
					     " WHERE  TERMINATION_VALIDITY_DATE <= LAST_DAY(TO_DATE('" + m_to_date + "','DD-MM-YYYY')) "+
					     " AND    TERMINATION_VALIDITY_DATE >= TRUNC(TO_DATE('" + m_to_date + "','DD-MM-YYYY'),'MONTH') "+
					     " and    ACTIVE_STATUS = 'TERM_CHECK' "+
					   " ) "+
					 
					 " and    ACTIVE_STATUS = 'TERM_CHECK' "+
					" ) "+
					
					" AND ( "+
					" SELECT COUNT(RENTAL_DATE)   "+
					  " FROM " + m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT "+
					  " WHERE RENTAL_DATE >= TRUNC((LAST_DAY(ADD_MONTHS(TO_DATE ('" + m_to_date + "','DD-MM-YYYY' ),0))),'MONTH') "+
					  " AND APPLICATION_NO = A.APPLICATION_NO "+
					  " AND INVOICE_NO  IS NOT NULL "+
					" ) > 0 "+
 					"   GROUP BY  A.FINANCE_NO,A.CLIENT_CODE,B.REGISTERED_CITY_CODE,B.REGISTERED_ADDRESS1,B.REGISTERED_ADDRESS2,B.ADDRESS1,B.ADDRESS2,B.CITY_CODE,B.F_TEL_NO,B.TEL_NO,A.APPLICATION_NO"+//Added by Kanchana on 2016-06-24
					
					"   ORDER BY " + m_sort +
                    " ";
				
				//out.println(sqlQuery); // udara test
                
                statement1 = connection.createStatement();
                resultSet1 = statement1.executeQuery(sqlQuery);
                
                boolean more = resultSet1.next();
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System - Closing Contract Report</title>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/leasing_drill_down.js\"></script>");
                
                out.println("       <script type=\"text/javascript\">");
                
                out.println("           var g_sort_column = '" + m_sort_column + "';");
                out.println("           var g_sort_order = '" + m_sort_order + "';");
                
                out.println("           function view_report(m_sort_cloumn) {");
                out.println("               var m_from_date = '" + m_from_date + "';");
                out.println("               var m_to_date = '" + m_to_date + "';");
                out.println("               var m_sort_order = null;");
                
                out.println("               if (g_sort_column == m_sort_cloumn) {");
                out.println("                   if (g_sort_order == 'ASC') {");
                out.println("                       m_sort_order = 'DESC';");
                out.println("                   }");
                out.println("                   else if (g_sort_order == 'DESC') {");
                out.println("                       m_sort_order = 'ASC';");
                out.println("                   }");
                out.println("               }");
                out.println("               else if (g_sort_column != m_sort_cloumn) {");
                out.println("                   m_sort_order = 'ASC';");
                out.println("               }");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_MISF_Closing_Contract_Report?chksql=view_report&from_date=' + m_from_date + '&to_date=' + m_to_date + '&sort_column=' + m_sort_cloumn + '&sort_order=' + m_sort_order;");
                out.println("               popupwin = window.open(m_url, 'displayWindow1', 'left=10, top=110, width=975, height=450, toolbar=0, location=0, center:yes, direction=0, menuBar=1, status=0, scrollbars=1, resizable=1');");
                out.println("           }");
                
                out.println("       </script>");
                
                out.println("       <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
                out.println("           <form name=\"Form1\" method=\"post\">");
                
                if (!more) {
                    
                    out.println("               <table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
                    out.println("                   <tr>");
                    out.println("                       <td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">Closing Contract Report</td>");
                    out.println("                   </tr>");
                    out.println("               </table>");
                    out.println("               <table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">");
                    out.println("                   <tr>");
                    out.println("                       <td style=\"text-align: center;\"><b>No Records</b></td>");
                    out.println("                   </tr>");
                    out.println("               </table>");
                    
                }
                
                else if (more) {
                    
                    out.println("               <table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
                    out.println("                   <tr>");
                    out.println("                       <td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">Closing Contract Report</td>");
                    out.println("                   </tr>");
                    out.println("               </table>");
                    out.println("               <table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">");
                    out.println("                   <tr class=\"pdn_txtpos2\">");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('AGREEMENT_NO');\">AGREEMENT NO</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('CLIENT_NAME');\">CLIENT NAME</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('ADDRESS');\">ADDRESS</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('VEHICLE_REG_NO');\">VEHICLE REG NO</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('CONTRACT_CLOSING_DATE');\">CONTRACT CLOSING DATE</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('CONTACT_NO');\">CONTACT NO</td>");
                    out.println("                       <td class=\"txt_report_column\" style=\"cursor: hand;\" onclick=\"view_report('RENTAL_AMT');\">RENTAL</td>");
					
					out.println("                   </tr>");
                    
                    
                    while (more) {
                        
                        if (j % 2 == 1) {
                            out.println("                   <tr class=\"tr_input\">");
                        }
                        else {
                            out.println("                   <tr class=\"tr_input1\">");
                        }
                        
                        out.println("                       <td align=\"left\">" + resultSet1.getString("FINANCE_NO") + "</td>");
                        out.println("                       <td align=\"left\">" + resultSet1.getString("CLIENT_NAME") + "</td>");
                        out.println("                       <td align=\"left\">" + resultSet1.getString("ADDRESS1") + ", " + resultSet1.getString("ADDRESS2") + ", " + resultSet1.getString("CITY_NAME") + ".</td>");
                        out.println("                       <td align=\"left\">" + resultSet1.getString("REG_NO") + "</td>");
                        out.println("                       <td align=\"center\">" + resultSet1.getString("CLOSE_DATE") + "</td>");
                        out.println("                       <td align=\"left\">" + resultSet1.getString("TEL_NO") + "</td>");
						out.println("                       <td align=\"right\">" + nf.format(resultSet1.getDouble("RENTAL_AMT")) + "</td>");
                        
                        // out.println("<TD  align='left' style= cursor:hand;cursor-color:blue onclick=\"show_termination_approval_done('"+resultSet1.getString(1)+"');\" ><u>"+resultSet1.getString(1)+"</u></TD>");
                        // out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_finance_detail_drill('"+resultSet1.getString(2)+"');\" ><u> "+resultSet1.getString(2)+"</u></TD>");
                        // out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_client('"+resultSet1.getString(4)+"');\"><u>"+resultSet1.getString(3)+"</u></TD>");
                        // out.println("<TD  align='left' >"+resultSet1.getString(5)+"</TD>");
                        // out.println("<TD  align='center'><input type='button' name=\"btn_dis_"+j+"\" class='but_input' value='Sheet' onclick=\"show_termination_appr('"+resultSet1.getString(2)+"','"+resultSet1.getString(5)+"','"+resultSet1.getString(1)+"')\"></TD>");
                        // out.println("<TD  align='center'><input type='button' name=\"btn_dis1_"+j+"\" style='width:100px' class='but_input' value='Account Entries' onclick=\"show_account_entries('"+resultSet1.getString(2)+"');\"></TD>");
                        out.println("                   </tr >");
                        
                        j = j + 1;
                        more = resultSet1.next();
                        
                    }
                    
                    out.println("               </table>");
                    
                }
                
                out.println("           <br />");
                out.println("           <table align=\"center\" width=\"100%\">");
                out.println("               <tr><td width=\"100%\" class=\"note\"></td></tr>");
                out.println("           </table>");
                out.println("       </form>");
                out.println("   </body>");
                out.println("</html>");
                
            }
            
        }
        
        catch(Exception exception) {
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            exception.printStackTrace(new PrintStream(byteArrayOutputStream));
            
            out = res.getOutputStream();
            out.println(byteArrayOutputStream.toString());
            out.close();
            
        }
        
    }
    
}
