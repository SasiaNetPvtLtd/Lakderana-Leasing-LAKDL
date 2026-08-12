/*
 * SCREEN NAME  : Credit Process - Group Payment Details
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-10-05
 * NOTES        :
 */


import java.io.IOException;
import java.math.BigDecimal;
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


public class LAKDL_AF_PRO_CR_Group_Payment_Details_display extends HttpServlet {
    
    public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        
        LAKDL_AF_CO_conn_methods m_sn_methods = null;
        String m_html_client_url = null;
        String m_fschema_name = null;
        String m_schema_name = null;
        String m_username = null;
        String m_class_url = null;
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql;
        
        ServletOutputStream out = null;
        
        NumberFormat numberFormat = null;
        DateFormat dateFormat = null;
        DateFormat dateFormat2 = null;
        
        String m_chksql = null;
        String m_sort_column = null;
        String m_order_by_type = null;
        
        String group_payment_no = null;
        
        String m_screen_title = null;
        
        String payee_name = null;
        String payer_bank_name = null;
        String cheque_no = null;
		String brac_vou_no = null; //added by Prabash on 27-10-2014
		
        String cheque_date = null;
        BigDecimal m_pay_amount_total = null;
        
        int j = 0;
        
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
            group_payment_no    = httpServletRequest.getParameter("group_payment_no");
            
            m_screen_title = "Finance - Payment Details";
            
            
            numberFormat = NumberFormat.getInstance(Locale.US);
            numberFormat.setMinimumFractionDigits(2);
            numberFormat.setMaximumFractionDigits(2);
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
            
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>" + m_screen_title + "</title>");
            
            out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url+"/css/Asset_Financing_System.css\" />");
            
            out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/leasing_drill_down.js\"></script>");
            out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
            out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
            out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
            
            out.println("       <style type=\"text/css\" media=\"print\">");
            out.println("           .hide_elements {");
            out.println("               display: none;");
            out.println("           }");
            out.println("       </style>");
            out.println("   </head>");
            
            out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
            out.println("       <form name=\"Form1\" method=\"post\">");
            
            out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
            out.println("               <tr>");
            out.println("                   <td width=\"8\" valign=\"top\"></td>");
            out.println("                   <td class=\"border_wht\" valign=\"top\"> ");
            out.println("                       <table class=\"table\" height=\"100%\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            out.println("                           <tr> ");
            out.println("                               <td height=\"30\" class=\"pdn_mainHD\">Asset Financing System</td>");
            out.println("                           </tr>");
            out.println("                           <tr> ");
            out.println("                               <td height=\"1\"></td>");
            out.println("                           </tr>");
            out.println("                           <tr>");
            out.println("                               <td>");
            out.println("                                   <table class=\"table\" height=\"100%\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            out.println("                                       <tr>");
            out.println("                                           <td height=\"1\"></td>");
            out.println("                                       </tr>");
            out.println("                                       <tr>");
            out.println("                                           <td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">" + m_screen_title + "</td>");
            out.println("                                       </tr>");
            out.println("                                       <tr class=\"hide_elements\">");
            out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
            out.println("                                               <table class=\"table\" cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
            out.println("                                                   <tr>");
            out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_PRINT\" id=\"BUT_PRINT\" value=\"Print\" onclick=\"window.print();\" /></td>");
            out.println("                                                   </tr>");
            out.println("                                               </table>");
            out.println("                                           </td>");
            out.println("                                       </tr>");
            out.println("                                       <tr>");
            out.println("                                           <td class=\"line\" height=\"1\"></td>");
            out.println("                                       </tr>");
            
            
            sql = " " +
                "   SELECT A.PAYEE_NAME, " +
                "          NVL(" + m_schema_name + ".AF_CO_GET_BANK_NAME(A.LIC_BRANCH_CODE), '-') LIC_BANK_NAME, " +
                "          NVL(A.CHEQUE_NO, '-') CHEQUE_NO, " +
                "          ( " +
                "               SELECT C.ENT_DATE " +
                "               FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT B, " +
                "                      " + m_schema_name + ".AF_CR_PRO_APPROVALDETAILS C " +
                "               WHERE  B.GROUP_PAYMENT_NO = C.REF_NO " +
                "               AND    C.APP_STATUS = 'PRINT' " +
                "               AND    B.GROUP_PAYMENT_NO = '" + group_payment_no + "' " +
                "               AND    ROWNUM = 1 " +
                "          ) CHEQUE_PRINT_DATE ," +
				"        NVL(A.BRAC_VOU_NO,'-')BRAC_VOU_NO   "+ //Added by Prabash on 27-10-2014 
                "   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
                "   WHERE  A.GROUP_PAYMENT_NO = '" + group_payment_no + "' " +
                "   AND    ROWNUM = 1 " +
                " ";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()) {
                
                payee_name          = resultSet.getString("PAYEE_NAME");
                payer_bank_name     = resultSet.getString("LIC_BANK_NAME");
                cheque_no           = resultSet.getString("CHEQUE_NO");
				brac_vou_no           = resultSet.getString("BRAC_VOU_NO"); //added by Prabash on 27-10-2014
				
				
                if (resultSet.getDate("CHEQUE_PRINT_DATE") != null) {
                    cheque_date         = dateFormat.format(resultSet.getDate("CHEQUE_PRINT_DATE"));
                }
                else {
                    cheque_date         = "-";
                }
            }
            resultSet.close();
            statement.close();
            
            
            out.println("                                       <tr class=\"pdn_txtpos\" valign=\"top\">");
            out.println("                                           <td>");
            out.println("                                               <br />");
            out.println("                                               <table style=\"font-weight: bold;\">");
            out.println("                                                   <tr>");
            out.println("                                                       <td style=\"width: 150px;\">Master SP Number (GP)</td>");
            out.println("                                                       <td style=\"width: 25px;\">:</td>");
            out.println("                                                       <td>" + group_payment_no + "</td>");
            out.println("                                                   </tr>");
			
			//-----Added by Prabash on 27-10-2014--------------------------------------**
			 out.println("                                                   <tr>");
            out.println("                                                       <td style=\"width: 150px;\">Branch Account VC Number</td>");
            out.println("                                                       <td style=\"width: 25px;\">:</td>");
            out.println("                                                       <td>" + brac_vou_no + "</td>");
            out.println("                                                   </tr>");
			//--------------------------------------------------------------------------**
            out.println("                                                   <tr>");
            out.println("                                                       <td>Payee Name</td>");
            out.println("                                                       <td>:</td>");
            out.println("                                                       <td>" + payee_name + "</td>");
            out.println("                                                   </tr>");
            out.println("                                                   <tr>");
            out.println("                                                       <td>Bank Name</td>");
            out.println("                                                       <td>:</td>");
            out.println("                                                       <td>" + payer_bank_name + "</td>");
            out.println("                                                   </tr>");
            out.println("                                                   <tr>");
            out.println("                                                       <td>Cheque Number</td>");
            out.println("                                                       <td>:</td>");
            out.println("                                                       <td>" + cheque_no + "</td>");
            out.println("                                                   </tr>");
            out.println("                                                   <tr>");
            out.println("                                                       <td>Cheque Date</td>");
            out.println("                                                       <td>:</td>");
            out.println("                                                       <td>" + cheque_date + "</td>");
            out.println("                                                   </tr>");
            out.println("                                               </table>");
            out.println("                                           </td>");
            out.println("                                       </tr>");
            
            out.println("                                       <tr class=\"pdn_txtpos\" valign=\"top\">");
            out.println("                                           <td>");
            
            
            
            
            sql = " " +
                "   SELECT A.PAYMENT_NO, " +
                "          NVL(A.FINANCE_NO, '-') APPLICATION_NO, " +
                "          " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, " +
                "          A.EFF_VALDATE, " +
                "          NVL(A.TAX_INV_NO, '-') TAX_INV_NO, " +
                //"          " + m_schema_name + ".AF_CO_GET_ALL_ENGINE_NO(A.FINANCE_NO) ENGINE_NO, " + // commented by udara 30-03-2017
				"          NVL(" + m_schema_name + ".AF_CO_GET_VEHICLE_NO(" + m_schema_name + ".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-') ENGINE_NO, " + // added by udara 30-03-2017
                "          A.PAY_AMOUNT " +
                "   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
                "   WHERE  A.GROUP_PAYMENT_NO = '" + group_payment_no + "' " +
                "   ORDER BY  A.PAYMENT_NO ASC " +
                // "   ORDER BY " + m_sort_column + " " + m_order_by_type + " " +
                " ";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            j = 0;
            m_pay_amount_total = new BigDecimal("0.00");
            
            out.println("<br />");
            out.println("<br />");
            out.println("<table class=\"table\" align=\"left\" border=\"0\">");
            
            while (resultSet.next()) {
                
                j++;
                
                if (j == 1) {
                    out.println("<tr class=\"pdn_txtpos2\">");
                    out.println("<td width=\"2%\" style=\"text-align: center;\">No.</td>");
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Payment No.</td>");
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Agreement No.</td>");
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Client Name</td>");
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Value Date</td>");
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Tax Invoice No.</td>");
                    //out.println("<td width=\"10%\" style=\"text-align: center;\">Engine No.</td>"); // commented by udara 30-03-2017
					out.println("<td width=\"10%\" style=\"text-align: center;\">Vehicle No.</td>"); // added by udara 30-03-2017
                    out.println("<td width=\"10%\" style=\"text-align: center;\">Amount</td>");
                    out.println("</tr>");
                }
                
                // if (j % 2 == 1) {
                    // out.println("<tr class=\"tr_input\">");
                // }
                // else {
                    // out.println("<tr class=\"tr_input1\">");
                // }
                out.println("<tr>");
                
                out.println("<td style=\"text-align: right;\">" + j + ".</td>");
                out.println("<td style=\"text-align: center;\">" + resultSet.getString("PAYMENT_NO") + "</td>");
                out.println("<td style=\"text-align: center;\">" + resultSet.getString("APPLICATION_NO") + "</td>");
                out.println("<td style=\"text-align: left;\">" + resultSet.getString("CLIENT_NAME") + "</td>");
                out.println("<td style=\"text-align: center;\">" + dateFormat.format(resultSet.getDate("EFF_VALDATE")) + "</td>");
                out.println("<td style=\"text-align: left;\">" + resultSet.getString("TAX_INV_NO") + "</td>");
                out.println("<td style=\"text-align: left;\">" + resultSet.getString("ENGINE_NO") + "</td>");
                out.println("<td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
                out.println("</tr>");
                
                m_pay_amount_total = m_pay_amount_total.add(resultSet.getBigDecimal("PAY_AMOUNT"));
                
            }
            resultSet.close();
            statement.close();
            
            
            out.println("<tr></tr>");
            out.println("<td colspan=\"8\">&nbsp;</tr>");
            out.println("<tr class=\"pdn_txtpos2\">");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td style=\"font-weight: bold; text-align: center;\">Total</td>");
            out.println("<td colspan=\"4\"></td>");
            out.println("<td style=\"font-weight: bold; text-align: right;\">" + numberFormat.format(m_pay_amount_total) + "</td>");
            out.println("</tr>");
            
            out.println("</table>");
            
            
            out.println("                                           </td>");
            out.println("                                       </tr>");
            
            
            out.println("                                       <tr>");
            out.println("                                           <td>&nbsp;</td>");
            out.println("                                       </tr>");
            out.println("                                       <tr>");
            out.println("                                           <td>&nbsp;</td>");
            out.println("                                       </tr>");
            
            out.println("                                       <tr>");
            out.println("                                           <td>");
            
            out.println("<table class=\"table\" align=\"left\" border=\"0\">");
            out.println("   <tr>");
            out.println("       <td style=\"width: 10px;\">&nbsp</td>");
            out.println("       <td style=\"width: 200px;\">Amount In Words</td>");
            out.println("       <td>Rupees " + m_sn_methods.numbersToChar(m_pay_amount_total.toString()) + " only</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 50px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">Authorized Signatory 01</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 50px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">Authorized Signatory 02</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 40px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom; text-decoration: underline;\">Disbursement Details</td>");
            out.println("       <td style=\"vertical-align: bottom;\"></td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 40px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">Name</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 40px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">NIC No.</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 40px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">Date</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("   <tr style=\"height: 50px;\">");
            out.println("       <td style=\"vertical-align: bottom;\">&nbsp</td>");
            out.println("       <td style=\"vertical-align: bottom;\">Signature</td>");
            out.println("       <td style=\"vertical-align: bottom;\">..................................................</td>");
            out.println("   </tr>");
            out.println("</table>");
            
            out.println("                                           </td>");
            out.println("                                       </tr>");
            
            
            out.println("                                       <tr><td>&nbsp;</td></tr>");
            out.println("                                       <tr><td>&nbsp;</td></tr>");
            out.println("                                       <tr><td>");
            
            out.println("<table class=\"table\" align=\"left\" border=\"0\">");
            out.println("   <tr>");
            out.println("       <td style=\"width: 10px;\">&nbsp</td>");
            out.println("       <td style=\"font-weight: bold; text-decoration: underline\">Approval Details</td>");
            out.println("   </tr>");
            out.println("</table>");
            
            out.println("                                       </td></tr>");
            
            out.println("                                       <tr>");
            out.println("                                           <td>");
            
            out.println("<table class=\"table\" align=\"left\" border=\"0\">");
            
            sql = " " +
                "   SELECT NVL(DECODE(B.APP_STATUS, " +
                "            'RE-APP', 'Payment Requsition', " +
                "            'RE-APP-REVERSE', 'Payment Requsition Reversal', " +
                "            'CANCEL', 'Cancel', " +
                "            'TEMP', 'Temp', " +
                "            'APPRO1','Approve Level 1', " +
                "            'APPRO1-REVERSE','Approve Level 1 Reversal', " +
                "            'APPRO2', 'Approve Level 2', " +
                "            'APPRO2-REVERSE', 'Approve Level 2 Reversal', " +
                "            'PRINT', 'Payment Print', " +
                "            'PRINT-REVERSE', 'PaymentPrint Reversal', " +
                "            'DISBRS', 'Payment Disburse', " +
                "            'DISBRS-REV', 'Payment Disburse Reversal', " +
                "            'OTHER') " +
                "          , '-') APP_STATUS, " +
                "          NVL(B.ENT_USER, '-') ENT_USER, " +
                "          B.ENT_DATE " +
                "   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A, " +
                "          " + m_schema_name + ".AF_CR_PRO_APPROVALDETAILS B " +
                "   WHERE  A.GROUP_PAYMENT_NO = B.REF_NO " +
                "   AND    A.GROUP_PAYMENT_NO = '" + group_payment_no + "' " +
                "   AND    A.GROUP_PAYMENT_NO IS NOT NULL " +
                "   GROUP BY B.APP_STATUS, " +
                "            B.ENT_USER, " +
                "            B.ENT_DATE " +
                "   ORDER BY B.ENT_DATE ASC " +
                " ";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            j = 0;
            while (resultSet.next()) {
                
                j++;
                
                if (j == 1) {
                    out.println("<tr class=\"pdn_txtpos2\">");
                    out.println("<td style=\"width: 10px; background-color: white;\">&nbsp</td>");
                    out.println("<td style=\"text-align: center; width: 20px;\">No.</td>");
                    out.println("<td style=\"text-align: center; width: 200px;\">Approval Level</td>");
                    out.println("<td style=\"text-align: center; width: 150px;\">Approved User</td>");
                    out.println("<td style=\"text-align: center; width: 200px;\">Approved Date / Time</td>");
                    out.println("</tr>");
                }
                
                out.println("<tr>");
                
                out.println("<td>&nbsp</td>");
                out.println("<td style=\"text-align: center;\">" + j + "</td>");
                out.println("<td style=\"text-align: center;\">" + resultSet.getString("APP_STATUS") + "</td>");
                out.println("<td style=\"text-align: center;\">" + resultSet.getString("ENT_USER") + "</td>");
                out.println("<td style=\"text-align: center;\">" + dateFormat2.format(resultSet.getTimestamp("ENT_DATE")) + "</td>");
                out.println("</tr>");
                
            }
            resultSet.close();
            statement.close();
            
            
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
        
        catch (Exception exception) {
            
            try {
                out.println("Error : " + exception.toString());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            exception.printStackTrace();
            
        }
        
        finally {
            
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }
    
}
