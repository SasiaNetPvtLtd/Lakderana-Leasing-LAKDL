/*
 * SCREEN NAME  : Credit Process - Main Screen Payment New 2
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-19
 * NOTES        :
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_PRO_CR_Payment_Req_Main_Screen_2_save extends HttpServlet {
    
    LAKDL_AF_CO_conn_methods m_sn_methods;
    
    ServletOutputStream out;
    
    Connection connection;
    CallableStatement callableStatement;
    
    BufferedReader bufferedReader;
    
    String m_schema_name;
    String m_fschema_name;
    String m_username;
    String m_html_client_url;
    String m_class_url;
    
    String reqstr;
    
    int m_row_count;
    String m_approve_status;
    String m_process_status;
    String m_bank_branch_code;
    String m_bank_account_no;
    String m_payment_no;
    String m_group_payment_no;
    String m_tax_invoice_no;
    Date m_tax_invoice_date;
    Calendar calendar;
    
    int j;
    
    public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        
        try {
            
            bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()), 2000);
            reqstr = bufferedReader.readLine();
            out = httpServletResponse.getOutputStream();
            
            m_sn_methods = new LAKDL_AF_CO_conn_methods();
            connection = m_sn_methods.met_user_validate(httpServletRequest);
            
            m_schema_name           = m_sn_methods.schema_name;
            m_fschema_name          = m_sn_methods.client_name;
            m_username              = m_sn_methods.username;
            m_html_client_url       = m_sn_methods.html_client_url;
            m_class_url             = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
            
            connection.setAutoCommit(false);
            
            
            m_row_count             = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_count"));
            m_bank_branch_code      = m_sn_methods.met_formdata(reqstr, "HID_BRANCH_CODE");
            m_bank_account_no       = m_sn_methods.met_formdata(reqstr, "TXT_ACC_NO");
            m_process_status        = "RE-APP";
            
            calendar = Calendar.getInstance();
            j = 1;
            
            
            callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_PRO_CR_SAVE_PAYMENT(:1, :2, :3, :4, :5, :6, :7, :8, :9); END;");
            callableStatement.registerOutParameter(1, java.sql.Types.CHAR);
            callableStatement.setString(2, m_bank_branch_code);
            callableStatement.setString(3, m_bank_account_no);
            callableStatement.setString(8, m_process_status);
            callableStatement.setString(9, m_username);
            
            for (int i = 1; i <= m_row_count; i++) {
                
                m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_" + (i - 1));
                
                if (m_approve_status.equals("A")) {
                    
                    m_payment_no = m_sn_methods.met_formdata(reqstr, "TXT_PAYMENT_NO_" + (i - 1));
                    m_tax_invoice_no = m_sn_methods.met_formdata(reqstr, "TXT_TAX_INVOICE_NO_" + (i - 1));
                    
                    calendar.clear();
                    calendar.set(Calendar.DATE, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_TAX_INVOICE_DATE_DD_" + (i - 1))));
                    calendar.set(Calendar.MONTH, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_TAX_INVOICE_DATE_MM_" + (i - 1))));
                    calendar.set(Calendar.YEAR, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_TAX_INVOICE_DATE_YY_" + (i - 1))));
                    m_tax_invoice_date = new Date(calendar.getTimeInMillis());
                    
                    callableStatement.setString(4, m_payment_no);
                    callableStatement.setString(5, m_tax_invoice_no);
                    callableStatement.setDate(6, m_tax_invoice_date);
                    callableStatement.setInt(7, j);
                    
                    if (j > 1) {
                        callableStatement.setString(1, m_group_payment_no);
                    }
                    else {
                        callableStatement.setString(1, null);
                    }
                    
                    callableStatement.execute();
                    
                    if (j == 1) {
                        m_group_payment_no = callableStatement.getString(1);
                    }
                    
                    
                    j++;
                    
                }
                
            }
            
            connection.commit();
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            out.println("               alert('Information saved successfully.');");
            out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Req_Main_Screen_2?chksql=R&chksql2=B&sql=main_page';");
            out.println("           }");
            out.println("       </script>");
            out.println("   </head>");
            out.println("   <body onload=\"displaymsg();\">");
            out.println("   </body>");
            out.println("</html>");
            
        }
        
        catch (Exception exception) {
            
            try {
                connection.rollback();
            }
            catch(Exception e) {}
            
            out.println("Error : " + exception.toString());
            exception.printStackTrace();
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            //out.println("               alert('Error When Saving Record..');");
            //out.println("               window.history.back();");
            out.println("           }");
            out.println("       </script>");
            out.println("   </head>");
            out.println("   <body onload=\"displaymsg();\">");
            out.println("   </body>");
            out.println("</html>");
            
        }
        
        finally {
            
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
