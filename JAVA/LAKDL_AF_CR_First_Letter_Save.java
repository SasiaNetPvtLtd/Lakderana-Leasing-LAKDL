/* ***** There Are Code To Enable When Transferring To Live ***** */

/*
 * SCREEN NAME  : Credit Process - First Letter - New
 * CREATED BY   : Amila
 * DATE / TIME  : 2017-08-21
 * NOTES        :
 */


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


public class LAKDL_AF_CR_First_Letter_Save extends HttpServlet {
    
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
        String m_print_status = null;
		String m_client_print = null;
		String m_fin_no = null;
		String m_cilent_name = null;
		
        String m_process_status = null;
        String m_payment_no = null;
        String m_approval_level = null;
        
        int j = 0;
        
        LAKDL_Bulk_Letter_Printing_Main m_Print_cheques = null;
        int mm_page_count = 0;
        String[] m_temp_list = null;
		String[] m_print_type = null;
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
            
            
            m_row_count             = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "HID_TOTAL_ROW_COUNT"));
            
            
            m_Print_cheques         = new LAKDL_Bulk_Letter_Printing_Main();
            mm_page_count           = 0;
            //m_temp_list             = new String[100]; // commented by udara 16-08-2017
			m_temp_list             = new String[250]; // added by udara 16-08-2017
			m_print_type            = new String[250];
           
            j = 1;
			
            
            
            callableStatement = connection.prepareCall( "BEGIN  "+m_schema_name+".AF_CR_SAVE_FIRST_LETTER(?,?,?,?);END;");
            
            for (int i = 0; i <= m_row_count; i++) {
                
               
				//   m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_" + i);
				     m_print_status =  m_sn_methods.met_formdata(reqstr,"CHK_" + i);
					 m_client_print =  m_sn_methods.met_formdata(reqstr,"CHK_CLIENT_" + i); 	
					 m_fin_no	    =  m_sn_methods.met_formdata(reqstr,"fin_no_" + i);
				     m_cilent_name	=  m_sn_methods.met_formdata(reqstr,"client_name_" + i);
				
				
                
                if (m_print_status.equals("ON")) {
                    
               
					
					callableStatement.setString(1,m_fin_no);
					callableStatement.setString(2,m_cilent_name);
					callableStatement.setString(3,m_print_status);
					callableStatement.setString(4,m_username);
               
                    
                   callableStatement.execute(); //kanishka
                    
                    m_temp_list[mm_page_count] = m_fin_no;
					m_print_type[mm_page_count] = "ALL";
                    mm_page_count++;
                    //int m_cknum=chq_print.printing_interface_1(m_pay_no,"FACT_PR");
                    //out.println("m_fin_no="+m_fin_no+"  m_cknum="+m_cknum);
                    
                    j++;
                    
				} else if (m_client_print.equals("ON")) {
                    
               
					
					callableStatement.setString(1,m_fin_no);
					callableStatement.setString(2,m_cilent_name);
					callableStatement.setString(3,m_client_print);
					callableStatement.setString(4,m_username);
               
                    
                   callableStatement.execute(); //kanishka
                    
                    m_temp_list[mm_page_count] = m_fin_no;
					m_print_type[mm_page_count] = "CLIENT";
                    mm_page_count++;
                    //int m_cknum=chq_print.printing_interface_1(m_pay_no,"FACT_PR");
                    //out.println("m_fin_no="+m_fin_no+"  m_cknum="+m_cknum);
                    
                    j++;
                    
				}
                
            }
             connection.commit();
			
            String[] m_paycount=new String[mm_page_count];
	        String[] mm_print_type=new String[mm_page_count];
	        
            for (int k=0;k<m_paycount.length;k++){
                m_paycount[k]=m_temp_list[k];
				mm_print_type[k]=m_print_type[k];
				
			  //  out.println("m_printer_name="+m_temp_list[k]);
				//out.println("m_print_type="+m_print_type[k]);
            }

             
	   /*     String[] mm_print_type=new String[mm_page_count];
            for (int k=0;k<mm_print_type.length;k++){
                mm_print_type[k]=m_print_type[k];
				
              //  out.println("m_print_type="+m_print_type[k]);
            }
         */   
            
            // letter printing goes here
            if(mm_page_count>0){
                /* Enable When Transferring To Live */
				//out.println("mm_page_count ="+mm_page_count);
				System.out.println("=============================== ");
				System.out.println(" FIRST LETTER PRINTING TEST AMILA ");
				System.out.println("=============================== ");
				System.out.println(" ");
				System.out.println("Selected Firest letter printer*********"+m_printer_name);
				
				//System.out.println("m_paycount*********"+m_paycount);
				
               // int mm=m_Print_cheques.printing_interface_chq(m_paycount,m_printer_name);	
				   //int mm=m_Print_cheques.printing_interface_chq(m_paycount,"\\\\Sanjaya.sasianet.net\\OKI ML5790");
					// int mm=m_Print_cheques.printing_interface_chq(m_paycount,"OKI ML5790"); //LAKDL PRINT WORK
					//int mm=m_Print_cheques.printing_interface_chq(m_paycount,"\\\\malitha-pc\\OKI DATA CORP ML5790"); //LAKDL PRINT WOrl live
					// int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\PRINTER\\HPlASERJ");
					// int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\manoj-pc\\OKI ML5790"); // added by udara 16-08-2017	
					    int mm=m_Print_cheques.printing_interface_chq(m_paycount,mm_print_type,"\\\\manoj-pc\\OKI ML5790"); // added by udara 16-08-2017	
                   //System.out.println("mm  ="+mm);
                // out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Cheque_Printing_display?chksql=main_page");
                
            }
            
           
            
            out.println("<html>");
            out.println("   <head>");
            out.println("       <script type=\"text/javascript\">");
            out.println("           function displaymsg() {");
            out.println("               alert('Information saved successfully.');");
            out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_CR_First_letter?chksql=main_page';");
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