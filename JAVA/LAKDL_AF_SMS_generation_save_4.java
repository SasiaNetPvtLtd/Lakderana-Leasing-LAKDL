// DEVELOP BY :  DISNAKA FOR OFSCL FACTORING    DATE:2011-11-08


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.regex.*;

public class LAKDL_AF_SMS_generation_save_4 extends HttpServlet {
	
	Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	java.text.NumberFormat nf;
	
	public synchronized void  service(HttpServletRequest req, HttpServletResponse res)	throws IOException{
		
		String m_class_url = "";
		String m_fschema_name = "";
		String m_schema_name = "";
		
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			
			m_fschema_name = m_sn_methods.client_name.trim();
			m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			m_msg = "'Information saved successfully'";
			//PDFConversion pdfConversion = new PDFConversion();
			stmt = conn.createStatement();
			
			
			
			Vector smsNumbers = new Vector();
			Vector smsmessage = new Vector();
			String smpp_host_name = "";
			int smpp_port         = 0;
			String smpp_path      = "";
			String smpp_auth_user = "";
			String smpp_auth_pwd  = "";
			String smpp_display_name  = "";
			
			String m_send_type = m_sn_methods.met_formdata(reqstr,"hid_send_type");
			
			String m_phone_num = m_sn_methods.met_formdata(reqstr,"TXT_TO_NUM");
			String m_message = m_sn_methods.met_formdata(reqstr,"TXT_MESSAGE");
			
			rs = stmt.executeQuery("SELECT A.SMPP_HOST_NAME, A.SMPP_PORT, A.PATH,A.SMPP_AUTH_USER, A.SMPP_AUTH_PWD,A.DISPLAY_NAME"+
				" FROM "+m_schema_name+".AF_CO_MAS_SMS_SERVER_DET A");
			
			if(rs.next()){
				smpp_host_name = rs.getString(1);
				smpp_port      = rs.getInt(2);
				smpp_path      = rs.getString(3);
				smpp_auth_user = rs.getString(4);
				smpp_auth_pwd  = rs.getString(5);
				smpp_display_name = rs.getString(6);
				
				
			}
			
				LAKDL_SasianetSms objSms = new LAKDL_SasianetSms();
			
				String message = m_message; //"Ayubowan! Obage "+rs.getString(6)+" wahanaye maasika warikaya "+rs.getString(3)+" dinata Rs. " + nf.format(rs.getDouble(2)) + " gewiya yuthu bawa danwa sitimu. Suba dawasak! Lakderana" ;
						
				String patternStr = "\n"; 
				String replacementStr = "%0D%0A";
						
				//  Compile regular expression
				Pattern pattern = Pattern.compile(patternStr);
						
				//Replace all occurrences of pattern in input
				Matcher matcher = pattern.matcher(message);
				message =  matcher.replaceAll(replacementStr);
						
				patternStr = " "; 
				replacementStr = "%20";
						
				//  Compile regular expression
				pattern = Pattern.compile(patternStr);
						
				//Replace all occurrences of pattern in input
				matcher = pattern.matcher(message);
				message =  matcher.replaceAll(replacementStr);
						
				//m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message); // commented by udara
				
				/*
				rs = stmt.executeQuery(" "+
						 " SELECT B.CLIENT_CODE, "+
						 " A.APPLICATION_NO,  "+
						 " NVL(C.MOBILE_NO,'-'),  "+
						 " NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),'-') VEHICLE_NO, "+
						 " A.RENTAL_DATE,  "+
						 " A.GRENTAL_AMOUNT "+
						 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						 " WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
						 " AND B.CLIENT_CODE = C.CLIENT_CODE  "+
						 " AND A.RENTAL_DATE = TRUNC(SYSDATE)+3 "+
						 " AND A.INVOICE_NO IS NULL "+
					" ");
				*/
				
				rs = stmt.executeQuery(" "+
						 " SELECT 'Test Message Bulk', "+
							" CLIENT_CODE, "+
							" MOBILE_NO "+
							" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
							" WHERE MOBILE_NO IS NOT NULL "+
							" AND MOBILE_NO <> '-' "+
							" AND ACTIVE_STATUS = 'Y' "+
					" ");
				
				while(rs.next()){
					m_phone_num = rs.getString(3);
					//message = rs.getString(1);
					//message = m_message;
					m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
				}
				
				
			
			
			/*
			try {	
				// with passing req
				//LAKDL_AF_SMS_generation_reminder_1  reminder_sms  = new LAKDL_AF_SMS_generation_reminder_1();
				//reminder_sms.generateSMS(req);
				
				// without passing req
				//LAKDL_AF_SMS_generation_reminder_2  reminder_sms  = new LAKDL_AF_SMS_generation_reminder_2();
				LAKDL_AF_SMS_generation_reminder_rental reminder_sms  = new LAKDL_AF_SMS_generation_reminder_rental();
				reminder_sms.generateSMS();
			}
			catch (Exception eee) {
				eee.printStackTrace();
			}
			*/
			
			
				out.println("<html>");
				out.println("	<head>");
				out.println("		<script type = \"text/javascript\">");
				out.println("			function displaymsg() {");
				out.println("				alert('Successfully Sent');");
				out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';"); 
				out.println("			}");
				out.println("		</script>");
				out.println("	</head>");
				out.println("	<body onload = \"displaymsg();\">");
				out.println("	</body>");
				out.println("</html>");
			
				
			
		}
		catch (SQLException sqlex) {
			sqlex.printStackTrace();
			try{conn.rollback();}catch(Exception e){}
			out.println();
			out.println("<html><head>");
			out.println("<script type='text/javascript'>");
			out.println("     function displaymsg() {");
			
			out.println("     alert('Database error ocurred..');");
			
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';"); 
			out.println("	}"); 
			out.println("</script></head>");
			out.println("<body onload=\'displaymsg();\'>Error: Database error occured.<br/> "+sqlex.toString()+"</body>");
			out.println("</html>");
			out.flush();
			out.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			}
			catch(Exception e) {out.println(e.toString());}
			
			
			
			out.println("<html>");
			out.println("	<head>");
			out.println("		<script type = \"text/javascript\">");
			out.println("			function displaymsg() {");
			out.println("				alert('Error When Saving Record..');");
			out.println("				window.location.href='" + m_class_url + "/" + m_fschema_name + "AF_MAS_Send_Sms_Leasing';"); 
			out.println("			}");
			out.println("		</script>");
			out.println("	</head>");
			out.println("	<body onload = \"displaymsg();\">");
			out.println("     Error: "+ ex.toString()+" ");
			out.println("	</body>");
			out.println("</html>");
			
			out.flush();
			out.close();
		}
		
		finally {
			try {
				conn.setAutoCommit(true);
			}
			catch(Exception e) {}
			
			if(conn != null) {
				try {
					conn.close();
				}
				catch(Exception e) {}
			}
			
			if(out != null) {
				try {
					out.close();
				}
				catch(Exception e) {}
			}
			
		}
		
	}
}
