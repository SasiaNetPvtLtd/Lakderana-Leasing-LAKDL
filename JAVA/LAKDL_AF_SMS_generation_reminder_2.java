// created by udara 08-09-2015

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.regex.*;

public class LAKDL_AF_SMS_generation_reminder_2  {
	
	public static Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt;
	//String reqstr;
	ServletOutputStream out = null;
	Statement stmt = null;
	public ResultSet rs = null;
	BufferedWriter log=null;
	java.text.NumberFormat nf;
	
	//String m_class_url = "";
	String m_fschema_name = "";
	String m_schema_name = "";
	
	String m_finance_no = "";
	String mm_vehicle_no = "";
	String m_type = "";
	String m_doc_ref_no = "";
	
	String m_message = "";
	String m_phone_num = "";
	
	private static void getConnection() {	
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.234.132:1521:SNPDDB","LAKDL","EIGHTITENGPW82");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82");
		}	
		catch (Exception e) {
			System.out.println("Error:"+e.toString());
		}
		//return out_conn;		
	}
	
	//public synchronized void  generateSMS(HttpServletRequest req)	throws IOException{
	public synchronized void  generateSMS()	throws IOException{
		
		
		
		try {
			
			//BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()),2000);
			//reqstr = input.readLine();   	
			//out = res.getOutputStream();
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			//************************************************************	
			//conn =m_sn_methods.met_user_validate(req);
			getConnection();
			conn.setAutoCommit(false);
			//**************************************************************		
			
			m_fschema_name = m_sn_methods.client_name.trim();
			m_schema_name = m_sn_methods.schema_name.trim();
			//String m_client_name = m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			//String m_html_client_url=m_sn_methods.html_client_url;
			//m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();

			
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
			
			//String m_send_type = m_sn_methods.met_formdata(reqstr,"hid_send_type");
			
			//String m_phone_num = m_sn_methods.met_formdata(reqstr,"TXT_TO_NUM");
			//String m_message = m_sn_methods.met_formdata(reqstr,"TXT_MESSAGE");
			
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
				
				rs = stmt.executeQuery(" "+
						 " SELECT B.CLIENT_CODE, "+
						 " A.APPLICATION_NO,  "+
						 " NVL(C.MOBILE_NO,'-'),  "+
						 " NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(A.APPLICATION_NO),'-') VEHICLE_NO, "+
						 " TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),  "+
						 " A.GRENTAL_AMOUNT, "+
						 " NVL(B.FINANCE_NO,'-') "+ // 7
						 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
						 " WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
						 " AND B.CLIENT_CODE = C.CLIENT_CODE  "+
						 //" AND A.RENTAL_DATE = TRUNC(SYSDATE)+3 "+
							" AND A.RENTAL_DATE = TRUNC(SYSDATE)+2 "+
						 " AND A.INVOICE_NO IS NULL "+
					" ");
				
				while(rs.next()){
					
					m_finance_no = rs.getString(7);
					mm_vehicle_no = rs.getString(4);
					m_type = "rental_reminder";
					m_doc_ref_no = rs.getString(2); // application number for rental reminder
					
					m_message = "Ayubowan! Obage "+rs.getString(4)+" wahanaya sandaha maasika warikaya "+rs.getString(5)+" dinata Rs."+rs.getString(6)+" gewiya yuthu bawa karunikawa danwa sitimu. Suba dawasak! Lakderana";
					message = m_message;
					//  Compile regular expression
					pattern = Pattern.compile(patternStr);
							
					//Replace all occurrences of pattern in input
					matcher = pattern.matcher(message);
					message =  matcher.replaceAll(replacementStr);
					
					m_phone_num = rs.getString(3);
					//message = 
					
					// CHECK THIS
					//m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
					//String m_status = m_msg.substring(0,m_msg.indexOf(":"));
					//String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
					
					String m_status = "";
					String m_ref = "";
					
					System.out.println("BEFORE SAVE SMS LOG - DUE RENTAL REMINDER");
					
					callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG_RENTAL_REMINDER(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
					callstmt.setString(1,m_finance_no);
					callstmt.setString(2,mm_vehicle_no);
					callstmt.setString(3,m_status);
					callstmt.setString(4,m_ref);
					callstmt.setString(5,null);
					callstmt.setString(6,m_username);  
					callstmt.setString(7,message);  
					callstmt.setString(8,m_type);  
					callstmt.setString(9,m_doc_ref_no); 
					callstmt.execute();
					conn.commit();
					
					System.out.println("AFTER SAVE SMS LOG - DUE RENTAL REMINDER");
			
			
			}
				
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.toString());
			
			try {
				conn.rollback();
			}
			catch(Exception e) {out.println(e.toString());}
			
		
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
