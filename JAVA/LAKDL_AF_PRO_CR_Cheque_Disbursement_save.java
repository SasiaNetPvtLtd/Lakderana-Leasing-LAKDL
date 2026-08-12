/*
 * SCREEN NAME  : Credit Process - Cheque Disbursement
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-23
 * NOTES        :
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_PRO_CR_Cheque_Disbursement_save extends HttpServlet {
    
    LAKDL_AF_CO_conn_methods m_sn_methods;
    String m_schema_name;
    String m_fschema_name;
    String m_username;
    String m_class_url;
    
    BufferedReader bufferedReader;
    ServletOutputStream out;
    String reqstr;
    
    Connection connection;
    CallableStatement callableStatement;
    
    int m_row_count;
    String m_approve_status;
    
    Date m_disburse_date;
    Date m_posted_date;
    Calendar calendar;
    
    public synchronized void  service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        
        try {
            
            bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()), 2000);
            reqstr = bufferedReader.readLine();
            out = httpServletResponse.getOutputStream();
            
            m_sn_methods = new LAKDL_AF_CO_conn_methods();
            connection = m_sn_methods.met_user_validate(httpServletRequest);
            
            m_schema_name           = m_sn_methods.schema_name;
            m_fschema_name          = m_sn_methods.client_name;
            m_username              = m_sn_methods.username;
            m_class_url             = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
            
            connection.setAutoCommit(false);
            
            
            m_row_count             = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_count"));
            
            calendar = Calendar.getInstance();
            
            callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_PRO_CR_SAVE_PAYMENT_DISBURS(:1, :2, :3, :4, :5, :6, :7, :8); END;");
            
            for (int i = 1; i <= m_row_count; i++) {
                
                m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_" + i);
                
                if (m_approve_status.equals("A")) {
                    
                    calendar.clear();
                    calendar.set(Calendar.DATE, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_DIS_DATE_DD_" + i)));
                    calendar.set(Calendar.MONTH, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_DIS_DATE_MM_" + i)) - 1);
                    calendar.set(Calendar.YEAR, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_DIS_DATE_YY_" + i)));
                    m_disburse_date = new Date(calendar.getTimeInMillis());
                    
                    calendar.clear();
                    calendar.set(Calendar.DATE, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_POST_DATE_DD_" + i)));
                    calendar.set(Calendar.MONTH, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_POST_DATE_MM_" + i)) - 1);
                    calendar.set(Calendar.YEAR, Integer.parseInt(m_sn_methods.met_formdata(reqstr, "TXT_POST_DATE_YY_" + i)));
                    m_posted_date = new Date(calendar.getTimeInMillis());
                    
                   // out.println(m_sn_methods.met_formdata(reqstr, "HID_PAYMENT_NO_" + i));
                    callableStatement.setString(1, m_sn_methods.met_formdata(reqstr, "HID_PAYMENT_NO_" + i));
                    callableStatement.setString(2, m_sn_methods.met_formdata(reqstr, "TXT_CHEQUE_NO_" + i));
                    callableStatement.setString(3, m_sn_methods.met_formdata(reqstr, "TXT_DIS_TO_" + i));
                    callableStatement.setString(4, m_sn_methods.met_formdata(reqstr, "TXT_ID_NO_" + i));
                    callableStatement.setString(5, m_sn_methods.met_formdata(reqstr, "TXT_DIS_BY_" + i));
                    callableStatement.setDate(6, m_disburse_date);
                    callableStatement.setDate(7, m_posted_date);
                    callableStatement.setString(8, m_username);
                    
                    callableStatement.execute();
                    
                }
                
            }
            callableStatement.close();
            
            connection.commit();
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            out.println("               alert('Information saved successfully.');");
            out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Cheque_Disbursement_display?chksql=main_page';");
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
            catch (Exception e) {
                e.printStackTrace();
            }
            
            out.println("Error : " + exception.toString());
            exception.printStackTrace();
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            out.println("               alert('Error When Saving Record..');");
            out.println("               window.history.back();");
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
