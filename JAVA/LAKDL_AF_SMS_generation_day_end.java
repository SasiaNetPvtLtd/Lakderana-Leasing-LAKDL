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

public class LAKDL_AF_SMS_generation_day_end  {
	
	public static Connection conn;	
	String m_msg,m_url;
	CallableStatement callstmt, callstmt1;
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
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82"); // development
	        //conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); // lakdl test
			conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin"); // lakdl live
		}	
		catch (Exception e) {
			System.out.println("Error:"+e.toString());
		}
		//return out_conn;		
	}
	
	//public synchronized void  generateSMS(HttpServletRequest req)	throws IOException{
	public synchronized void  generateSMS(String day_end_section)	throws IOException{
		
		
		
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
				
				
				    // added by udara 15-02-2017
					/*
					String m_sys_date = "";
				
					rs = stmt.executeQuery(" "+
						" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL "+
						" ");
		
					if(rs.next()){
						m_sys_date = rs.getString(1);
					}
					*/
		
				
					//callstmt1  = conn.prepareCall("BEGIN " + m_schema_name + ".AF_SAVE_RENTAL_REMINDERS(:1); END;");
					//callstmt1.setString(1,m_sys_date);
					//callstmt1.execute();
					
				int day_end_count = 0;	
					
				rs = stmt.executeQuery(" "+
					" SELECT COUNT(*) "+
								" FROM "+m_schema_name+".DAY_END_SMS_LOG "+
								" WHERE DAY_END_DATE = TRUNC(SYSDATE,'DD') - 1 "+								
					" ");	
					
				if(rs.next()){
					day_end_count = rs.getInt(1);
				}
				
				
				
				if(day_end_count==0){
				
							rs = stmt.executeQuery(" "+
								
								"SELECT "+
									     " SMS_USER, "+
									     " PHONE_NO  "+
											" FROM "+m_schema_name+".DAY_END_SMS_USERS "+
											" WHERE ACTIVE_STATUS = 'Y' "+								
								" ");
							
							
							
							
							
							
							//callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG_RENTAL_REMINDER(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;"); // added by udara 17-05-2017
							
							while(rs.next()){
								
			
								m_type = "DAY_END";
								
								if(day_end_section.equals("DAY_END_REPORT_RUN"))
									m_message = "Day end is completed and the reports are completed";
								
								message = m_message;
								//  Compile regular expression
								pattern = Pattern.compile(patternStr);
										
								//Replace all occurrences of pattern in input
								matcher = pattern.matcher(message);
								message =  matcher.replaceAll(replacementStr);
			
								//m_phone_num = "765320953"; // hardcode Malitha's phone number
								//m_phone_num = "714199938"; // sasianet BA's phone number // jithendra
								//m_phone_num = "766671555"; // sasianet udara
								//message = 
								
								m_phone_num = rs.getString(2);
								
								// CHECK THIS
								m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
								String m_status = m_msg.substring(0,m_msg.indexOf(":"));
								String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
			
								/*
								System.out.println("BEFORE SAVE SMS LOG - DUE RENTAL REMINDER");
								
								//callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".SAVE_SMS_LOG_RENTAL_REMINDER(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;"); // commented by udara 17-05-2017
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
								
								Thread.sleep(50); // Thread.sleep(35); // to limit 30 messages per second
								*/
						
						
						}
							
						callstmt1  = conn.prepareCall("BEGIN " + m_schema_name + ".AF_SAVE_DAY_END_SMS_LOG(:1); END;");
						callstmt1.setString(1,m_type);
						callstmt1.execute();
							
						callstmt1.close(); // commented by udara 17-05-2017	
						
					} // count if		
				
			
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
