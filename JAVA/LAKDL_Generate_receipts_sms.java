// CREATED BY UDARA ON 04-09-2015 FOR LAKDL

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.regex.*;


public class LAKDL_Generate_receipts_sms {
	
			Connection conn=null;	
			String m_msg=null,m_url=null;
			CallableStatement callstmt=null;
			String reqstr=null;
			ServletOutputStream out = null;
			Statement stmt = null;
			ResultSet rs = null;
			BufferedWriter log=null;
			java.text.NumberFormat nf=null;
			String m_class_url = "";
			String m_fschema_name = "";
			String m_schema_name = "";	
			String m_username = "";
			
			String mm_vehicle_no = "";
			String mm_message = "";
	
	public synchronized void generateSMS(HttpServletRequest req, String m_client_no, String m_finance_no, String m_rec_no, String m_amount, String m_type) throws Exception {
		
		try {


			BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			reqstr = input.readLine();   	
			//out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			conn =m_sn_methods.met_user_validate(req);
			conn.setAutoCommit(false);
			//**************************************************************		
			
			m_fschema_name = m_sn_methods.client_name.trim();
			m_schema_name = m_sn_methods.schema_name.trim();
			String m_client_name = m_sn_methods.client_name.trim();
			//String m_username = m_sn_methods.username;
			m_username = m_sn_methods.username;
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

			
			//String m_phone_num = m_sn_methods.met_formdata(reqstr,"TXT_TO_NUM");
			//String m_message = m_sn_methods.met_formdata(reqstr,"TXT_MESSAGE");
			
			String m_phone_num = "714199938";
			String m_message = "Test From Receipts New " + m_client_no;		
			
			String m_date = "";
			String m_vehicle_no = "";
			
			rs = stmt.executeQuery(" "+
				//" SELECT A.MOBILE_NO "+
				" SELECT SUBSTR(A.MOBILE_NO,2), "+
				" TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+
				" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')),'-') "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+
				" WHERE CLIENT_CODE = '"+m_client_no+"'  "+
				" ");
			if(rs.next()){
				m_phone_num = rs.getString(1);
				m_date = rs.getString(2);
				m_vehicle_no = rs.getString(3);
			}
			
			// added by udara 18-11-2016
			String send_sms_flag = "N";
			
			rs = stmt.executeQuery(" "+
			      " SELECT "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2('"+m_finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))  "+
					"  FROM DUAL  "+
					" ");
		
		    if(rs.next()){
				send_sms_flag = rs.getString(1);
			}	
			// end by udara 18-11-2016
			
			
					if(send_sms_flag.equals("Y")){ // condition added by udara 18-11-2016	
			
							if(m_type.equals("receipt")){
								m_message = "Ayubowan! Obage "+m_vehicle_no+" darana wahanaya sandaha Rs."+m_amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
							}
							
							
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
							
							mm_vehicle_no = m_vehicle_no;
							mm_message = m_message;
							
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
										
							m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
							
							System.out.println("BEFORE SAVE SMS LOG");
							
							String m_status = m_msg.substring(0,m_msg.indexOf(":"));
							String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
							
							callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
							callstmt.setString(1,m_finance_no);
							callstmt.setString(2,mm_vehicle_no);
							callstmt.setString(3,m_status);
							callstmt.setString(4,m_ref);
							callstmt.setString(5,null);
							callstmt.setString(6,m_username);  
							callstmt.setString(7,mm_message);  
							callstmt.setString(8,m_type);  
							callstmt.setString(9,m_rec_no); 
							callstmt.execute();
							conn.commit();
							
							System.out.println("AFTER SAVE SMS LOG");
							
							
						} // condition added by udara 18-11-2016		
			
			
		}
		
		
		
		catch (Exception exception) {
			
			exception.printStackTrace();
			
			callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			callstmt.setString(1,m_finance_no);
			callstmt.setString(2,mm_vehicle_no);
			callstmt.setString(3,"ERROR");
			callstmt.setString(4,m_rec_no);
			callstmt.setString(5,null);
			callstmt.setString(6,m_username);  
			callstmt.setString(7,mm_message);  
			callstmt.setString(8,m_type);  
			callstmt.setString(9,m_rec_no); 
			callstmt.execute();
			conn.commit();
			
			
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