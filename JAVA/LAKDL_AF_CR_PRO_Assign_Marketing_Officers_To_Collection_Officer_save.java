/*
 * Created By       :   Samitha Kulatilaka
 * Created Date     :   2012-01-30
 * 
 * Screen Name      :   Collection Officer Assignment
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
// import java.io.PrintStream;
import java.sql.Connection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;


public class LAKDL_AF_CR_PRO_Assign_Marketing_Officers_To_Collection_Officer_save extends HttpServlet {
    
    public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        
        Connection connection = null;
        OracleCallableStatement oracleCallableStatement = null;
        
        ServletOutputStream out = null;
        
        String reqstr = null;
        
        try {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()), 2000);
            reqstr = input.readLine();
            out = res.getOutputStream();
            
            LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
            connection  = m_sn_methods.met_user_validate(req);
            String m_schema_name = m_sn_methods.schema_name.trim();
            String m_client_name = m_sn_methods.client_name.trim();
            String m_username = m_sn_methods.username;
            String m_html_client_url;
            String m_class_url;
            String m_class_name_save;
            String m_save_procedure_name;
            
            
            m_html_client_url=m_sn_methods.html_client_url;
            m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
            
            String m_fschema_name=m_sn_methods.client_name.trim();
            
            // PrintStream out = new PrintStream(res.getOutputStream());
            
            
            // synchronized(this) {
            
            connection.setAutoCommit(false);
            
            
            String m_screen_mode                        = m_sn_methods.met_formdata(reqstr, "HID_SCREEN_MODE");
            
            int m_marketing_officer_count               = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "HID_MARKETING_OFFICER_COUNT"));
            String[] m_marketing_officer_list           = new String[m_marketing_officer_count];
            
            int m_marketing_officer_delete_count        = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "HID_MARKETING_OFFICER_DELETE_COUNT"));
            String[] m_marketing_officer_delete_list    = new String[m_marketing_officer_delete_count];
            
            
            for (int i = 1; i <= m_marketing_officer_count; i++) {
                m_marketing_officer_list[i - 1] = m_sn_methods.met_formdata(reqstr, "HID_MARKETING_OFFICER_" + i);
            }
            if (m_screen_mode.equals("MOD")) {
                for (int i = 1; i <= m_marketing_officer_delete_count; i++) {
                    m_marketing_officer_delete_list[i - 1] = m_sn_methods.met_formdata(reqstr, "HID_DELETED_MARKETING_OFFICER_" + i);
                }
            }
            
            
            oracleCallableStatement = (OracleCallableStatement)connection.prepareCall
                    ("begin " + m_schema_name + ".AF_CR_PRO_SAVE_MKT_COLL_MAP(?, ?, ?, ?, ?); end;");
            
            // if (m_screen_mode.equals("NEW")) {
                
                oracleCallableStatement.setString(1, m_screen_mode);
                oracleCallableStatement.setString(2, m_sn_methods.met_formdata(reqstr, "TXT_COLLECTION_OFFICER"));
                
                ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("STRING_DATA_ARRAY", connection);
                ARRAY m_marketing_officer_array = new ARRAY(arrayDescriptor, connection, m_marketing_officer_list);
                oracleCallableStatement.setARRAY(3, m_marketing_officer_array);
                
                ArrayDescriptor arrayDescriptor_1 = ArrayDescriptor.createDescriptor("STRING_DATA_ARRAY", connection);
                ARRAY m_marketing_officer_delete_array = new ARRAY(arrayDescriptor_1, connection, m_marketing_officer_delete_list);
                oracleCallableStatement.setARRAY(4, m_marketing_officer_delete_array);
                
                oracleCallableStatement.setString(5, m_username);
                
                oracleCallableStatement.executeUpdate();
                
            // }
            
            
            connection.commit();
            
            // }//end syncro.
            
            
            
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert('Marketing officer allocation saved successfully.');");
            out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Assign_Marketing_Officers_To_Collection_Officer_display?chksql=main_page';");
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            
            out.flush();
            
        }
        catch (Exception E) {
            try{connection.rollback();}catch(Exception e){}
            out.println("ERROR:"+E.toString());
            out.println("<HTML><HEAD>");
            out.println("<SCRIPT language='JavaScript'>");
            out.println("function displaymsg() {");
            out.println("alert('Error when Saving');");
            //out.println("window.history.back();"); 
            out.println("}</SCRIPT></HEAD>");
            out.println("<body onload='displaymsg();'></body>");
            out.println("</html>");
            out.flush();
            
        }
        finally{
            try{connection.setAutoCommit(true);}catch(Exception e){}
            //if(input     !=null){try{input.close();    }catch(Exception e){}}
            if(connection!=null){try{connection.close(); }catch(Exception e){}}
            if(out!=null){try{out.close();  }catch(Exception e){}}
        }
        
    }
    
}
