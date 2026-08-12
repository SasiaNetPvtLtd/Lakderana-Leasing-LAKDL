/* ***** There Are Code To Enable When Transferring To Live ***** */

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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LAKDL_AF_PRO_CR_Cheque_Printing_save extends HttpServlet {
	
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
		String m_approve_status = null;
		String m_approve_payee_status =null;
		String m_approve_payee_status_b = null;
		String m_process_status = null;
		String m_payment_no = null;
		String m_approval_level = null;
		
		int j = 0;
		
		Bulk_Printing_Main m_Print_cheques = null; //enable when live
		int mm_page_count = 0;
		String[] m_temp_list = null;
		String[] m_temp_list1 = null;
		String m_printer_name = null;
		
		int sel_stage = 0;
		
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
			
			
			m_Print_cheques         = new Bulk_Printing_Main(); 
			mm_page_count           = 0;
			m_temp_list             = new String[100];
			m_temp_list1             = new String[100];
			m_printer_name          = m_sn_methods.met_formdata(reqstr, "TXT_PRINTER");
			
			
			
			sql = " " +
				"   SELECT A.POSITION " +
				"   FROM   " + m_schema_name + ".CO_CO_MAS_USER_SCREEN A " +
				"   WHERE  SCREEN_NAME = 'AF_CR_PRO_PAYMENT_CHEQUE_PRINT1' " +
				" ";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {
				sel_stage = resultSet.getInt("POSITION");
			}
			
			
			j = 1;
			
			
			callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_PRO_CR_SAVE_CHEQUE_NEW_2(:1, :2, :3, :4, :5, :6, :7, :8, :9); END;");
			
			for (int i = 1; i <= m_row_count; i++) {
				
				m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_" + i);
				
				
				if (m_approve_status.equals("A")) {
					
					m_approve_payee_status = m_sn_methods.met_formdata(reqstr, "chkbox_acc_payee" +i);

						if(m_approve_payee_status.equals("Y")){
							m_approve_payee_status="Y";
							break;
						}else{
						m_approve_payee_status="N";
						}
					
				}
				
			}
			
			
			for (int i = 1; i <= m_row_count; i++) {
				
				m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_" + i);
				//m_approve_payee_status = m_sn_methods.met_formdata(reqstr, "chkbox_acc_payee" +i);
				System.out.println("***************************** 01IN SAVING:=    "+m_approve_payee_status+"///////"+m_approve_status);
				
				
				
				
				// System.out.println("*****************************:=    "+m_approve_payee_status);
				if (m_approve_status.equals("A")) {
					/*
					if(m_approve_payee_status.equals("Y")){
						m_approve_payee_status="Y";
					}else{
						m_approve_payee_status="N";	
					}*/
					
					System.out.println("***************************** 1 IN SAVING:=    "+m_approve_payee_status);
					m_payment_no = m_sn_methods.met_formdata(reqstr, "HID_PAYMENT_NO_" + i);
					callableStatement.setString(1, m_payment_no);
					callableStatement.setInt(2, sel_stage);
					callableStatement.setString(3, "PRINT");
					callableStatement.setString(4, "PRINT");
					callableStatement.setString(5, m_sn_methods.met_formdata(reqstr, "SCREEN_NAME"));
					callableStatement.setString(6, m_username);
					callableStatement.setString(7, "AF_CR_PRO_PAYMENT_CHEQUE_PRINT1");
					callableStatement.setInt(8, i);
					callableStatement.setString(9, m_sn_methods.met_formdata(reqstr, "TXT_CHEQUE_NO_" + i));
					// out.println(m_payment_no);
					// out.println(sel_stage);
					// out.println("PRINT");
					// out.println("PRINT");
					// out.println(m_sn_methods.met_formdata(reqstr, "SCREEN_NAME"));
					// out.println(m_username);
					// out.println("AF_CR_PRO_PAYMENT_CHEQUE_PRINT1");
					// out.println(i);
					// out.println(m_sn_methods.met_formdata(reqstr, "TXT_CHEQUE_NO_" + i));
					
					callableStatement.execute();
					
					m_temp_list[mm_page_count] = m_payment_no;
					//m_temp_list1[mm_page_count] =m_approve_payee_status;
					mm_page_count++;
					//int m_cknum=chq_print.printing_interface_1(m_pay_no,"FACT_PR");
					//out.println("m_payment_no="+m_pay_no+"  m_cknum="+m_cknum);
					
					j++;
					
				}
				
			}
			
			String[] m_paycount=new String[1];
			for (int k=0;k<m_paycount.length;k++){
				m_paycount[k]=m_temp_list[k];
				
				//out.println("m_printer_name="+m_temp_list[k]);
			}
			
			/*String[] m_paystatus=new String[1];
            for (int v=0;v<m_paystatus.length;v++){
                m_paystatus[v]=m_temp_list1[v];*/
			
			System.out.println("m_approve_payee_status IN PRINT SAVINFG 2::::::::::::::="+m_approve_payee_status);
			//  }
			
			
			// cheques printing goes here
			//if(mm_page_count>0){ //comment by roshan 18/02/2020 JB08112019-09654
				/* Enable When Transferring To Live */
		/*		System.out.println("***************************** 3 IN SAVING:=    "+m_approve_payee_status); 
				int mm=m_Print_cheques.printing_interface_8(m_paycount,m_printer_name,m_approve_payee_status);//uncommented by milinda	*/ //comment by roshan 18/02/2020 JB08112019-09654
				
				// out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Cheque_Printing_display?chksql=main_page");
				
		//	}
		
		
		//modyfy by Roshan 18/02/2020  JB08112019-09654
			if(mm_page_count>0){
				
				try{
				//	out.println("***************************** Start =:********* "); 
					int mm=m_Print_cheques.printing_interface_8(m_paycount,m_printer_name,m_approve_payee_status);
					//out.println("*****************************end:= ********** "+mm);
				}catch(Exception e){
					
					//out.println("*****************************error:=********** ");
				}
					
					
			}
			
			//End Roshan
			
			connection.commit();
			
			out.println("<html>");
			out.println("   <head>");
			out.println("       <script type=\"text/javascript\">");
			out.println("           function displaymsg() {");
			out.println("               alert('Information saved successfully.');");
			out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Cheque_Printing_display?chksql=main_page';");
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
