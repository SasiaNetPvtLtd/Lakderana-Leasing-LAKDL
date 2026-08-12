/*
 * Screen Name      : Insurance Summary Head By Head Report
 * Created By       : Samitha Kulatilaka
 * Date             : 2010-09-05
 */

 
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LAKDL_AF_RE_Collection_Insurance_Summary_Head_By_Head_Report extends HttpServlet {
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        Connection connection   = null;
        Statement statement     = null;
        ResultSet resultSet     = null;
        String sql              = null;
        
        try {
            
            LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
            
            String m_html_client_url    = con_method.html_client_url;
            String m_servlet_client_url = con_method.servlet_client_url;
            String m_client_t3_port     = con_method.client_t3_port;
            String m_class_url          = m_servlet_client_url + ":" + m_client_t3_port;
            String m_schema_name        = con_method.schema_name;
            String m_fschema_name       = con_method.client_name;
            String m_header_name        = con_method.header_name;
            
            
            connection = con_method.met_user_validate(req);
            
            
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            
            
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("text/html");
            ServletOutputStream out = res.getOutputStream();
            
            String m_screen_type = req.getParameter("chksql");
            
            
            if (m_screen_type.trim().equals("main_page")) {
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System</title>");
                out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/validate.js\"></script>");
                out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/validate_v1.js\"></script>");
                out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
                out.println("       <link rel = \"stylesheet\" type = \"text/css\" href = \"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type = \"text/javascript\">");
                
                out.println("           window.onload = function() {");
                out.println("               document.getElementById('SEL_MONTH').value = g_sysdate_mm;");
                out.println("               document.getElementById('TXT_YEAR').value = g_sysdate_yy;");
                out.println("           }");
                
                
                /* Get The Sysdate */
                sql = " " +
                    "   SELECT TO_CHAR(SYSDATE, 'YYYY') YYYY, " +
                    "          TO_CHAR(SYSDATE, 'MM') MM, " +
                    "          TO_CHAR(SYSDATE, 'DD') DD " +
                    "   FROM   DUAL " +
                    " ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                
                out.println("           var g_sysdate_yy = '" + resultSet.getString("YYYY") + "';");
                out.println("           var g_sysdate_mm = '" + resultSet.getString("MM") + "';");
                out.println("           var g_sysdate_dd = '" + resultSet.getString("DD") + "';");
                
                resultSet.close();
                statement.close();
                
                
                out.println("           function load_roll_value(m_val) {");
                out.println("               document.getElementById('help_box').innerHTML = 'Collection Process - Insurance Summary Head By Head - ' + m_val;");
                out.println("           }");
                
                
                out.println("           function load_roll_out_value() {");
                out.println("               document.getElementById('help_box').innerHTML = 'Collection Process - Insurance Summary Head By Head';");
                out.println("           }");
                
                
                out.println("           function clear_window() {");
                out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
                out.println("                   window.location.href = window.location.href;");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_screen_status(m_val){"); 
                out.println("               if(m_val == 'HELP') {");
                out.println("                   load_help_msg();"); 
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function load_help_msg() {"); 
                out.println("               m_help_message = 'm_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report';");
                out.println("               HelpBox_msg(m_help_message);"); 
                out.println("           }");
                
                
                out.println("           function HelpBox_msg(m_help_message) {");
                out.println("               popupwin = window.showModalDialog(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_MAS_Help_Msg_Servlet?class_in=' + client_name + 'AF_MAS_Help_Msg_select' +");
                out.println("                   '&help_message_in=' + m_help_message);");
                out.println("           }");
                
                
                out.println("           function makeRequest(){");
                out.println("               var m_report_date = '01-' + document.getElementById('SEL_MONTH').value + '-' + document.getElementById('TXT_YEAR').value;");
                out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_RE_Collection_Insurance_Summary_Head_By_Head_Report?chksql=view_report&report_date=' + m_report_date;");
                out.println("               window.open(m_url, 'insurance_summary_head_by_head_report', 'left=20, top=20, width=900, height=600, toolbar=0, location=0, directories=0, status=0, menuBar=0, scrollBars=1, resizable=1');");
                out.println("           }");
                
                
                out.println("           function isNumber(object) { ");
                out.println("               var m_value = object.value;");
                
                out.println("               if (m_value == '') {");
                out.println("                   alert('Please enter a value.');");
                out.println("                   object.focus();");
                out.println("               }");
                out.println("               else if (isNaN(m_value)) {");
                out.println("                   alert('Please enter a number.');");
                out.println("                   object.focus();");
                out.println("               }");
                out.println("               else if ((parseInt(m_value) < 1920) || (parseInt(m_value) > 2120)) {");
                out.println("                   alert('Please enter a number between 1920 & 2120.');");
                out.println("                   object.focus();");
                out.println("               }");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                out.println("   <body class = \"body & txt-body\" leftmargin = \"0\" topmargin = \"0\" marginwidth = \"0\" marginheight = \"0\" \">");
                out.println("       <form name = \"Form1\" method = \"post\">");
                
                out.println("           <table class = \"table\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("               <tr>");
                out.println("                   <td width = \"8\" valign = \"top\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"8\" height = \"8\" /></td>");
                out.println("                   <td class = \"border_wht\" valign = \"top\">");
                out.println("                       <table class = \"table\" width = \"100%\"  height = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("                           <tr>");
                out.println("                               <td class = \"pdn_mainHD\" height = \"30\">" + m_header_name + "</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td height = \"1\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td style = \"height: 327px;\">");
                out.println("                                   <table class = \"table\" height = \"100%\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("                                       <tr>");
                out.println("                                           <td height = \"1\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"pdn_txtpos2\" align = \"left\" style = \"height: 18px;\" id = \"help_box\">Collection Process - Insurance Summary Head By Head</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"pdn_txtpos\" height = \"10px\">");
                out.println("                                               <table class = \"table\" border=\"0\" cellpadding = \"2\" cellspacing = \"2\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td><input class = \"mainbut\" type = \"button\" onmouseout = \"load_roll_out_value();\" onmouseover = \"load_roll_value('Cancel');\" onclick = \"clear_window();\" value = \"Cancel\" /></td>");
                out.println("                                                       <td width = \"100%\"></td>");
                out.println("                                                       <td><input class = \"mainbut\" type = \"button\" onmouseout = \"load_roll_out_value();\" onmouseover = \"load_roll_value('Help');\" onclick = \"load_screen_status('HELP');\" value = \"Help\" /></td>");
                out.println("                                                       <td><input class = \"mainbut\" type = \"button\" onmouseout = \"load_roll_out_value();\" onmouseover = \"load_roll_value('Close');\" onclick = \"close_window();\" value = \"Close\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"line\" height = \"1\"><img src=\"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"pdn_txtpos\" height = \"150\" valign = \"top\">");
                out.println("                                               <table class = \"table\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\"></table>");
                out.println("                                               <br />");
                
                out.println("                                               <table class = \"table\" align = \"center\" width = \"100%\" border = \"0\">");
                out.println("                                                   <tr class = \"tr_input\">");
                out.println("                                                       <td width = \"15%\">Month</td>");
                out.println("                                                       <td width = \"20%\">");
                out.println("                                                           <select class = \"txt_input\" id = \"SEL_MONTH\" name = \"SEL_MONTH\" style = \"width: 100px;\">");
                out.println("                                                               <option value = \"01\">January</option>");
                out.println("                                                               <option value = \"02\">February</option>");
                out.println("                                                               <option value = \"03\">March</option>");
                out.println("                                                               <option value = \"04\">April</option>");
                out.println("                                                               <option value = \"05\">May</option>");
                out.println("                                                               <option value = \"06\">June</option>");
                out.println("                                                               <option value = \"07\">July</option>");
                out.println("                                                               <option value = \"08\">August</option>");
                out.println("                                                               <option value = \"09\">September</option>");
                out.println("                                                               <option value = \"10\">October</option>");
                out.println("                                                               <option value = \"11\">November</option>");
                out.println("                                                               <option value = \"12\">December</option>");
                out.println("                                                           </select>");
                out.println("                                                           <input class = \"txt_input\" type = \"text\" id = \"TXT_YEAR\" name = \"TXT_YEAR\" maxlength = \"4\" style = \"width: 45px;\" onblur= \"isNumber(this);\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td width = \"65%\" align = \"left\"><input class = \"mainbut\" type = \"button\" onclick = \"makeRequest();\" value = \"View Report\" /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                                   <table align = \"center\" width = \"100%\">");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"note\" width = \"100%\"></td>");
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
            
            else if(m_screen_type.equals("view_report")) {
                
                String m_report_date                = req.getParameter("report_date");
                
                boolean has_rows                    = false;
                String m_insurance_company          = null;
                String m_insurance_company_name     = null;
                
                BigDecimal m_bike_leasing           = new BigDecimal("0.00");
                BigDecimal m_lease                  = new BigDecimal("0.00");
                BigDecimal m_leasing_renewals       = new BigDecimal("0.00");
                
                BigDecimal m_bike_leasing_total     = new BigDecimal("0.00");
                BigDecimal m_lease_total            = new BigDecimal("0.00");
                BigDecimal m_leasing_renewals_total = new BigDecimal("0.00");
                
                BigDecimal m_insurer_total          = new BigDecimal("0.00");
                BigDecimal m_reverse_total          = new BigDecimal("0.00");
                BigDecimal m_grand_total            = new BigDecimal("0.00");
                
                
                sql = " " +
                    "   SELECT * " +
                    "   FROM ( " +
                    "       SELECT A.INSUR_COM, " +
                    "              C.PAYEE_NAME, " +
                    "              A.BUSINESS_TYPE, " +
                    "              " + m_schema_name + ".AF_CO_GET_ITEM_CATEGORY_CODE(B.MODEL_CODE) ITEM_CATEGORY_CODE, " +
                    "              A.BASIC_PREMIUM_COMMISION, " +
                    "              A.RCC_TC_COMMISION " +
                    "       FROM   " + m_schema_name + ".AF_IS_PRO_ASET_INSUR_DETA A, " +
                    "              " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B, " +
                    "              " + m_schema_name + ".AF_CR_PRO_SUB_CHAR_PAYEE_REF C " +
                    "       WHERE  A.PRO_INVOICE_NO = B.INVOICE_NO " +
                    "       AND    A.INSUR_COM = C.PAYEE_CODE " +
                    "       AND    C.SUB_TYPE_CODE = 'INSURANCE' " +
                    "       AND    ( " +
                    "           A.BASIC_PREMIUM_COMMISION > 0 " +
                    "           OR " +
                    "           A.RCC_TC_COMMISION > 0 " +
                    "       ) " +
                    "       AND    A.START_DATE >= TO_DATE('" + m_report_date + "', 'DD-MM-YYYY') " +
                    "       AND    A.START_DATE <= LAST_DAY(TO_DATE('" + m_report_date + "', 'DD-MM-YYYY')) " +
                    "   ) P " +
                    "   WHERE  P.ITEM_CATEGORY_CODE IN ('VEHICLE', 'EQUIPMENT') " +
                    "   ORDER BY P.PAYEE_NAME ASC, " +
                    "            P.BUSINESS_TYPE ASC, " +
                    "            P.ITEM_CATEGORY_CODE ASC " +
                    " ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                
                
                
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System</title>");
                out.println("       <script type = \"text/javascript\" src = \"" + m_html_client_url + "/validate.js\"></script>");
                out.println("       <link rel = \"stylesheet\" type = \"text/css\" href = \"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                out.println("   </head>");
                
                out.println("   <body class = \"body & txt-body\" leftmargin = \"0\" topmargin = \"0\" marginwidth = \"0\" marginheight = \"0\" \">");
                out.println("       <form name = \"Form1\" method = \"post\">");
                out.println("           <table class = \"table\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("               <tr>");
                out.println("                   <td width = \"8\" valign = \"top\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"8\" height = \"8\" /></td>");
                out.println("                   <td class = \"border_wht\" valign = \"top\">");
                out.println("                       <table class = \"table\" width = \"100%\"  height = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("                           <tr>");
                out.println("                               <td class = \"pdn_mainHD\" height = \"30\">" + m_header_name + "</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td height = \"1\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td style = \"height: 327px;\">");
                out.println("                                   <table class = \"table\" height = \"100%\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                out.println("                                       <tr>");
                out.println("                                           <td height = \"1\"><img src = \"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"pdn_txtpos2\" align = \"left\" style = \"height: 18px;\" id = \"help_box\">Collection Process - Insurance Summary Head By Head</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"line\" height = \"1\"><img src=\"" + m_html_client_url + "/images/spacer.gif\" width = \"1\" height = \"1\" /></td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"pdn_txtpos\" height = \"150\" valign = \"top\">");
                out.println("                                               <table class = \"table\" width = \"100%\" border = \"0\" cellpadding = \"0\" cellspacing = \"0\"></table>");
                out.println("                                               <br />");
                
                out.println("                                               <table class = \"table\" align = \"center\" width = \"100%\" border = \"1\">");
                
                int rowCount = 0;
                while (resultSet.next()) {
                    
                    if (has_rows == false) {
                        
                        out.println("                                                   <tr style = \"font-weight: bold;\">");
                        out.println("                                                       <td width = \"40%\" align = \"center\">Insurance Company</td>");
                        out.println("                                                       <td width = \"15%\" align = \"center\">Bike Leasing</td>");
                        out.println("                                                       <td width = \"15%\" align = \"center\">Lease</td>");
                        out.println("                                                       <td width = \"15%\" align = \"center\">Leasing Renwals</td>");
                        out.println("                                                       <td width = \"15%\" align = \"center\">Grand Total</td>");
                        out.println("                                                   </tr>");
                        
                        has_rows = true;
                        
                    }
                    
                    if ((m_insurance_company != null) && (!m_insurance_company.equals(resultSet.getString("INSUR_COM")))) {
                        
                        rowCount = rowCount + 1;
                        m_insurer_total = m_bike_leasing.add(m_lease).add(m_leasing_renewals);
                        m_bike_leasing_total = m_bike_leasing_total.add(m_bike_leasing);
                        m_lease_total = m_lease_total.add(m_lease);
                        m_leasing_renewals_total = m_leasing_renewals_total.add(m_leasing_renewals);
                        m_grand_total = m_grand_total.add(m_insurer_total);
                        
                        // if (rowCount % 2 == 1) {
                            // out.println("                                                   <tr class = \"tr_input\">");
                        // }
                        // else {
                            // out.println("                                                   <tr class = \"tr_input1\">");
                        // }
                        
                        out.println("                                                   <tr>");
                        out.println("                                                       <td width = \"40%\" align = \"left\">" + m_insurance_company_name + "</td>");
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_bike_leasing) + "</td>");
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_lease) + "</td>");
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_leasing_renewals) + "</td>");
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_insurer_total) + "</td>");
                        out.println("                                                   </tr>");
                        
                        m_bike_leasing = new BigDecimal("0.00");
                        m_lease = new BigDecimal("0.00");
                        m_leasing_renewals = new BigDecimal("0.00");
                        m_insurer_total = new BigDecimal("0.00");
                        
                    }
                    
                    m_insurance_company = resultSet.getString("INSUR_COM");
                    m_insurance_company_name = resultSet.getString("PAYEE_NAME");
                    
                    if ((resultSet.getString("BUSINESS_TYPE").equals("NEW")) && (resultSet.getString("ITEM_CATEGORY_CODE").equals("VEHICLE"))) {
                        
                        m_bike_leasing = m_bike_leasing.add(resultSet.getBigDecimal("BASIC_PREMIUM_COMMISION").add(resultSet.getBigDecimal("RCC_TC_COMMISION")));
                        
                    }
                    else if ((resultSet.getString("BUSINESS_TYPE").equals("NEW")) && (resultSet.getString("ITEM_CATEGORY_CODE").equals("EQUIPMENT"))) {
                        
                        m_lease = m_lease.add(resultSet.getBigDecimal("BASIC_PREMIUM_COMMISION").add(resultSet.getBigDecimal("RCC_TC_COMMISION")));
                        
                    }
                    else if (resultSet.getString("BUSINESS_TYPE").equals("RENEWAL")) {
                        
                        m_leasing_renewals = m_leasing_renewals.add(resultSet.getBigDecimal("BASIC_PREMIUM_COMMISION").add(resultSet.getBigDecimal("RCC_TC_COMMISION")));
                        
                    }
                    
                }
                resultSet.close();
                statement.close();
                
                
                if (has_rows == true) {
                    
                    rowCount = rowCount + 1;
                    m_insurer_total = m_bike_leasing.add(m_lease).add(m_leasing_renewals);
                    m_bike_leasing_total = m_bike_leasing_total.add(m_bike_leasing);
                    m_lease_total = m_lease_total.add(m_lease);
                    m_leasing_renewals_total = m_leasing_renewals_total.add(m_leasing_renewals);
                    m_grand_total = m_grand_total.add(m_insurer_total);
                    
                    
                    sql = " " +
                        "   SELECT NVL(SUM(A.NET_AMOUNT), 0) REVERSE_TOTAL " +
                        "   FROM   " + m_schema_name + ".AF_CO_PRO_INVOICE A " +
                        "   WHERE  A.INVOICE_TYPE = 'INSURANCE' " +
                        "   AND    A.ACTIVE_STATUS = 'DB_CAN' " +
                        "   AND    A.VALUE_DATE >= TO_DATE('" + m_report_date + "', 'DD-MM-YYYY') " +
                        "   AND    A.VALUE_DATE <= LAST_DAY(TO_DATE('" + m_report_date + "', 'DD-MM-YYYY')) " +
                        " ";
                    
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    
                    if (resultSet.next()) {
                        
                        m_reverse_total = resultSet.getBigDecimal("REVERSE_TOTAL");
                        
                    }
                    resultSet.close();
                    statement.close();
                    
                    
                    // if (rowCount % 2 == 1) {
                        // out.println("                                                   <tr class = \"tr_input\">");
                    // }
                    // else {
                        // out.println("                                                   <tr class = \"tr_input1\">");
                    // }
                    
                    out.println("                                                   <tr>");
                    out.println("                                                       <td width = \"40%\" align = \"left\">" + m_insurance_company_name + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_bike_leasing) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_lease) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_leasing_renewals) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_insurer_total) + "</td>");
                    out.println("                                                   </tr>");
                    
                    out.println("                                                   <tr>");
                    out.println("                                                       <td width = \"40%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                   </tr>");
                    
                    out.println("                                                   <tr style = \"font-weight: bold;\">");
                    out.println("                                                       <td width = \"40%\" align = \"left\">Total</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_bike_leasing_total) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_lease_total) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_leasing_renewals_total) + "</td>");
                    out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_grand_total) + "</td>");
                    out.println("                                                   </tr>");
                    
                    out.println("                                                   <tr style = \"font-weight: bold;\">");
                    out.println("                                                       <td width = \"40%\" align = \"left\">Less: Reversals (Over Accounted Commision)</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    /* m_reverse_total > 0 */
                    if (m_reverse_total.compareTo(new BigDecimal("0")) == 1) {
                        out.println("                                                       <td width = \"15%\" align = \"right\">(" + nf.format(m_reverse_total) + ")</td>");
                    }
                    /* m_reverse_total = 0 */
                    else if (m_reverse_total.compareTo(new BigDecimal("0")) == 0) {
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_reverse_total) + "</td>");
                    }
                    /* m_reverse_total < 0 */
                    else if (m_reverse_total.compareTo(new BigDecimal("0")) == -1) {
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_reverse_total.negate()) + "</td>");
                    }
                    out.println("                                                   </tr>");
                    
                    m_grand_total = m_grand_total.subtract(m_reverse_total);
                    
                    out.println("                                                   <tr style = \"font-weight: bold;\">");
                    out.println("                                                       <td width = \"40%\" align = \"left\">Total</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    out.println("                                                       <td width = \"15%\" align = \"left\">&nbsp;</td>");
                    /* m_grand_total < 0 */
                    if (m_grand_total.compareTo(new BigDecimal("0")) == -1) {
                        out.println("                                                       <td width = \"15%\" align = \"right\">(" + nf.format(m_grand_total.negate()) + ")</td>");
                    }
                    else  {
                        out.println("                                                       <td width = \"15%\" align = \"right\">" + nf.format(m_grand_total) + "</td>");
                    }
                    out.println("                                                   </tr>");
                    
                }
                
                
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                                   <table align = \"center\" width = \"100%\">");
                out.println("                                       <tr>");
                out.println("                                           <td class = \"note\" width = \"100%\"></td>");
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
            
        }
        
        catch (Exception e) { 
            try { 
                
            }
            catch (Exception eti) {}
            
            ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
            e.printStackTrace(new PrintStream(ostr));
            
            ServletOutputStream out = res.getOutputStream();
            out.println(ostr.toString()); 
            out.close();
            
        }
    }
    
}
