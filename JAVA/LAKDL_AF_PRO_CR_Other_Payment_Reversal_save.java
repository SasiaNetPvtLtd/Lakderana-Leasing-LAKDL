/**********************************     CREATED BY MILINDA       *************************/
/**********************************  OTHER PAYMENT REVERSAL SAVE *************************/
/*********************************          LAKDERANA            *************************/
/********************************          24-09-2015    *********************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_PRO_CR_Other_Payment_Reversal_save extends HttpServlet {
	
	public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		
		LAKDL_AF_CO_conn_methods m_sn_methods = null;
		String m_schema_name = null;
		String m_fschema_name = null;
		String m_username = null;
		String m_class_url = null;
		
		ServletOutputStream out = null;
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		BufferedReader bufferedReader = null;
		
		String reqstr = null;
		
		//int m_row_count = 0;
		String m_approve_status = null;
		String m_process_status_get = null;
		String m_process_status = null;
		String m_group_payment_no = null;
		
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
			
			
			int m_row_count             = Integer.parseInt(m_sn_methods.met_formdata(reqstr, "hid_count"));
			m_process_status_get    = m_sn_methods.met_formdata(reqstr, "hid_process_status_get");
			m_process_status        = m_sn_methods.met_formdata(reqstr, "hid_process_status");
			
			
			callableStatement = connection.prepareCall("BEGIN " + m_schema_name + ".AF_PRO_SAVE_OTHER_PAYMENT_REV(:1, :2, :3, :4); END;");
			
			callableStatement.setString(2, m_process_status_get);
			callableStatement.setString(3, m_process_status);
			callableStatement.setString(4, m_username);
			
			for (int i = 1; i <= m_row_count; i++) {
				//out.println("************"+i);
				m_approve_status = m_sn_methods.met_formdata(reqstr, "CHK_APPROVE_"  +(Integer.toString(i)));
				
				if (m_approve_status.equals("A")) {
					
					m_group_payment_no = m_sn_methods.met_formdata(reqstr, "HID_GROUP_PAYMENT_NO_" +(Integer.toString(i)));
					//out.println("************"+m_group_payment_no);
					callableStatement.setString(1, m_group_payment_no);					
					callableStatement.execute();
					
					//i++;
					
				}
				
			}
			
			connection.commit();
			
			out.println("<html>");
			out.println("   <head>");
			out.println("       <script type=\"text/javascript\">");
			out.println("           function displaymsg() {");
			out.println("               alert('Information saved successfully.');");
			out.println("               window.location.href = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Other_Payment_Reversal_display?chksql=main_page';");
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