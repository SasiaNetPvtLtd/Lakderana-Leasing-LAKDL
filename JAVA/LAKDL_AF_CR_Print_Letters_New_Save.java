/* ***** There Are Code To Enable When Transferring To Live ***** */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_CR_Print_Letters_New_Save extends HttpServlet {
    
    public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        
        LAKDL_AF_CO_conn_methods m_sn_methods = null;
        String m_schema_name = null;
	    String m_fschema_name = null;
        String m_username = null;
        String m_html_client_url = null;
        String m_class_url = null;
        
        ServletOutputStream out = null;
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = null;
        
        BufferedReader bufferedReader = null;
        
        String reqstr = null;
        
        int m_row_count = 0;
        String m_print_status  = "N";
		String m_cancel_status = "N";
		String m_reminder_type = null;
		String m_client_print = null;
		String m_fin_no = null;
		String m_cilent_name = null;
		String m_due_date = null;
		
        String m_process_status = null;
        String m_payment_no = null;
        String m_approval_level = null;
        
        int j = 0;
        
        LAKDL_Print_Letters_New_Bulk_print_main m_Print_cheques = null;
        int mm_page_count = 0;
        String[] m_temp_list = null;
		String[] m_print_type = null;
		String[] m_statement_date = null; // added by udara 05-11-2019
        String m_printer_name = null;
        
        int sel_stage = 0;
        
        try {
            
            bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()), 2000);
            reqstr = bufferedReader.readLine();
            out = httpServletResponse.getOutputStream();
            //out.println("reqstr ="+reqstr);
            m_sn_methods = new LAKDL_AF_CO_conn_methods();
            connection = m_sn_methods.met_user_validate(httpServletRequest);
            
            m_schema_name           = m_sn_methods.schema_name;
            m_fschema_name          = m_sn_methods.client_name;
            m_username              = m_sn_methods.username;
            m_html_client_url       = m_sn_methods.html_client_url;
            m_class_url             = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
            
            connection.setAutoCommit(false);
            
            
            m_row_count             = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "NUM_CHKS"));
			
			String m_letter_category = ""; 
			m_letter_category             = m_sn_methods.met_formdata(reqstr, "TXT_LETETR_CATEGORY"); 
			
			m_printer_name = m_sn_methods.met_formdata(reqstr, "TXT_PRINTER"); // added by udara 25-04-2019
			
			System.out.println(" m_row_count =  " + m_row_count);
            
            
            m_Print_cheques         = new LAKDL_Print_Letters_New_Bulk_print_main();
            mm_page_count           = 0;
            //m_temp_list             = new String[100]; // commented by udara 16-08-2017
			m_temp_list             = new String[250]; // added by udara 16-08-2017
			m_print_type            = new String[250];
			m_statement_date        = new String[250]; // added by udara 05-11-2019
           
            j = 1;
			
            
            m_reminder_type =  m_sn_methods.met_formdata(reqstr,"TXT_LETETR_CATEGORY");
			
            callableStatement = connection.prepareCall( "BEGIN  "+m_schema_name+".AF_CO_SAVE_REMINDER_LOG(?,?,?,?,?,?);END;");
            
            for (int i = 0; i <= m_row_count; i++) {

				m_print_status  =  m_sn_methods.met_formdata(reqstr,"TXT_APPROVE_TYPE_" + i);
				m_cancel_status =  m_sn_methods.met_formdata(reqstr,"TXT_DISSAPPROVE_TYPE_" + i);
				m_fin_no	    =  m_sn_methods.met_formdata(reqstr,"TXT_FINANCE_NO_" + i);	
				m_due_date		=  m_sn_methods.met_formdata(reqstr,"TXT_DUE_DATE_" + i);
				
                
                if (m_print_status.equals("Y")) { 
					
					callableStatement.setString(1,m_fin_no);
					callableStatement.setString(2,m_due_date);
					callableStatement.setString(3,m_reminder_type);
					callableStatement.setString(4,m_username); 
					callableStatement.setString(5,m_print_status);
					callableStatement.setString(6,m_cancel_status);
		            callableStatement.execute(); 
					
                    
                    m_temp_list[mm_page_count] = m_fin_no;
					m_print_type[mm_page_count] = "CLIENT";
					m_statement_date[mm_page_count] = m_due_date; // added by udara 05-11-2019
                    mm_page_count++;

                    
                    j++;
                    
				} 
				
				if (m_cancel_status.equals("Y")) { 
					
					callableStatement.setString(1,m_fin_no);
					callableStatement.setString(2,m_due_date);
					callableStatement.setString(3,m_reminder_type);
					callableStatement.setString(4,m_username); 
					callableStatement.setString(5,m_print_status);
					callableStatement.setString(6,m_cancel_status);
		            callableStatement.execute(); 
					
				}
				
				
				m_print_status  = "N";
				m_cancel_status = "N";
                
            }
			
			System.out.println(" mm_page_count =  " + mm_page_count);
             
		    //connection.commit();
			
            String[] m_paycount=new String[mm_page_count];
	        String[] mm_print_type=new String[mm_page_count];
			String[] mm_statement_date=new String[mm_page_count]; // added by udara 05-11-2019
	        
            for (int k=0;k<m_paycount.length;k++){
                m_paycount[k]=m_temp_list[k];
				mm_print_type[k]=m_print_type[k];
				mm_statement_date[k]=m_statement_date[k]; // added by udara 05-11-2019

            }


            
            // letter printing goes here
            if(mm_page_count>0){
                /* Enable When Transferring To Live */
				//out.println("mm_page_count ="+mm_page_count);
				System.out.println("=============================== ");
				System.out.println(" PRINT LETTERS NEW ");
				System.out.println("=============================== ");
				System.out.println(" ");
				System.out.println("PRINT LETTERS NEW PRINTER*********"+m_printer_name);
				
				//System.out.println("m_paycount*********"+m_paycount);

				//int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\PRINTER\\HPLASERJ",m_letter_category);
				
				//int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"HP LASERJET PROFESSIONAL P1102",m_letter_category); // commented by udara 25-04-2019
				
				//int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,m_printer_name,m_letter_category); // commented by udara 05-11-2019 // added by udara 25-04-2019
				
				int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,mm_statement_date, m_printer_name,m_letter_category); // added by udara 05-11-2019
				
				//int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\PRINTER\\HP LASERJET_NEW",m_letter_category);
				//int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\manoj-pc\\OKI ML5790",m_letter_category); // added by udara 16-08-2017	
            }
            
           
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            out.println("               alert('Information saved successfully.');");
            out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_RE_Bulk_Letter_print_new?chksql=main_page';");
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