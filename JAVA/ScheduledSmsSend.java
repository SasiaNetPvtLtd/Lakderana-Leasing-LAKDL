

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.text.*;
import java.io.*;
import java.util.*;

import oracle.jdbc.driver.*;
import java.sql.*;


public class ScheduledSmsSend {
	
	private final ScheduledExecutorService scheduler = Executors
		.newScheduledThreadPool(1);
	private ScheduledFuture<?> sendSms;
	
	//private static Connection out_conn;
	private static Connection conn;
	private Statement stmt;
	private ResultSet rs ;
	private CallableStatement callstmt;
	private NumberFormat nf;
	
	
	
	private String smpp_host_name = "";
	private int smpp_port         = 0;
	private String smpp_path      = "";
	private String smpp_auth_user = "";
	private String smpp_auth_pwd  = "";
	private String smpp_display_name  = "";
	//this method is use for get the connection
	private static void getConnection() {	
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.234.132:1521:OFSCL", "OFSCL", "OFSCL123");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.234.132:1521:OFSCLDB","OFSCL","SNORA7623ADMIN");
			//System.out.println("conn disnak disnak disnaka " + conn);
			
		}	
		catch (Exception e) {
			System.out.println("Error:"+e.toString());
		}
		//return out_conn;		
	}
	
	
	public void ScheduledSmsSend() {
		
		final Runnable runnable = new Runnable() {
			@SuppressWarnings("unchecked")
				public void run() {
				
				getConnection();
				
				Vector smsNumbers = new Vector();
				Vector smsmessage = new Vector();
				
				
				Vector amount = new Vector();
				Vector clientNo = new Vector();
				Vector financeNo = new Vector();
				Vector valueDate = new Vector();
				
				OFSCL_AF_CO_conn_methods m_sn_methods = new OFSCL_AF_CO_conn_methods(); 
				//************************************************************	
				
				//**************************************************************	
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_username = m_sn_methods.username;
				String am_pm;
				String status="NOT OK";
				Calendar calendar = new GregorianCalendar();
				int hour = calendar.get(Calendar.HOUR);
				int minute = calendar.get(Calendar.MINUTE);
				if(calendar.get(Calendar.AM_PM) == 0)
					am_pm = "AM";
				else
					am_pm = "PM";
				try {
					
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					System.out.println("Start 1");
					//getConnection();
					//conn.setAutoCommit(false);
					//stmt = conn.createStatement();
					
					
					
					
					//int second = calendar.get(Calendar.SECOND);
					
					
					rs = stmt.executeQuery(""+
						" SELECT COUNT(*) "+
						" FROM "+m_schema_name+".AF_CO_MAS_SMS_SEND_DET A  "+
						" WHERE TRUNC(ENT_DATE,'DD')= TRUNC(SYSDATE,'DD') "+
						" AND SEND_TYPE = 'BULK' ");
					
					if  (rs.next()){
						if (rs.getInt(1) > 0) {
							status = "OK";
						}
					}			
					System.out.println("Start 5");
					System.out.println(status);
					System.out.println(hour);
					System.out.println(am_pm);
					
					if (hour==10 && am_pm.equals("AM") && status.equals("NOT OK")) {
						
						
						nf = java.text.NumberFormat.getInstance(Locale.US);
						nf.setMinimumFractionDigits(2);
						nf.setMaximumFractionDigits(2);
						
						
						
						
						//callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".AF_CO_SAVE_SMS_DET(:1,:2,:3,:4,:5,:6,:7); END;");
						
						smsNumbers = new Vector();
						smsmessage = new Vector();
						
						
						amount = new Vector();
						clientNo = new Vector();
						financeNo = new Vector();
						valueDate = new Vector();
						
						String m_phone_num = null;
						
						rs = stmt.executeQuery(""+
							" SELECT B.FINANCE_NO, A.BALANCE_TO_BE_RECEIVED, TO_CHAR(A.VALUE_DATE,'DD-MON-YYYY'), "+
							" NVL(C.MOBILE_NO,'-') ,C.CLIENT_CODE "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A , "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_CLIENT C "+
							" WHERE "+
							" A.FINANCE_NO = B.FINANCE_NO "+
							" AND B.APPLICATION_STATUS = 'ACTIVATED' AND B.CLIENT_CODE = C.CLIENT_CODE "+
							" AND TRUNC(A.VALUE_DATE - 3,'DD') = TRUNC(SYSDATE,'DD') AND A.INVOICE_TYPE = 'INV_GENER' "+
							" AND A.VALUE_DATE >= TO_DATE('01-04-2012','DD-MM-YYYY') "+
							" AND A.BALANCE_TO_BE_RECEIVED > 0"+
							"");
						
						
						while (rs.next()){
							if (!rs.getString(4).equals("-")) {
								if ( rs.getString(4).indexOf("0")==0 ) {
									m_phone_num = "+94" + rs.getString(4).substring(1);
								}
								
								else {
									m_phone_num = "+94" + rs.getString(4);
								}
								smsNumbers.addElement(m_phone_num);
								//smsNumbers.addElement("772092396");
								smsmessage.addElement("Your next rental of Rs. " + nf.format(rs.getDouble(2)) + " for Agreement Number " + rs.getString(1) + " is due on "+ rs.getString(3) + "\nORIENT FINANCE PLC." );
								
								amount.addElement(rs.getDouble(2));
								clientNo.addElement(rs.getString(5));
								financeNo.addElement(rs.getString(1));
								valueDate.addElement(rs.getString(3));
								
								
								
								//callstmt.setString(1,rs.getString(1));
								//callstmt.setDouble(2,rs.getDouble(2));
								//callstmt.setString(3,rs.getString(3));
								//callstmt.setString(4,m_phone_num);
								//callstmt.setString(5,rs.getString(5));
								//callstmt.setString(6,"BULK"); 
								//callstmt.setString(7,m_username); 
								//callstmt.execute();
							}
							
							
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
						
						
						OFSCL_SasianetSms objSms = new OFSCL_SasianetSms();
						
						objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,smsNumbers,smsmessage);
						
						
						System.out.println("Sms sent successfully");
						
						
						
						//conn.commit();
					}
				}catch (SQLException sqlex) {
					try{sqlex.printStackTrace();conn.rollback();}catch(Exception e){}	
					
					
				} catch (Exception ex) {
					
					try {
						conn.rollback();
					}
					catch(Exception e) {e.printStackTrace();}
					
					
				}
				
				finally {
					try {
						conn.setAutoCommit(true);
					}
					catch(Exception e) {}
					
					/*
					if(conn != null) {
						try {
						//	conn.close();
						}
						catch(Exception e) {}
					}
					*/
					
					
				}
				//second section
				try {
					callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".AF_CO_SAVE_SMS_DET(:1,:2,:3,:4,:5,:6,:7); END;");
					if (hour==10 && am_pm.equals("AM") && status.equals("NOT OK")) {
						for (int i = 0; i < smsNumbers.size(); i++) {
							callstmt.setString(1,financeNo.get(i).toString());
							callstmt.setDouble(2,Double.parseDouble(amount.get(i).toString()));
							callstmt.setString(3,valueDate.get(i).toString());
							callstmt.setString(4,smsNumbers.get(i).toString());
							callstmt.setString(5,clientNo.get(i).toString());
							callstmt.setString(6,"BULK"); 
							callstmt.setString(7,m_username); 
							callstmt.execute();
						}
						
						conn.commit();
					}
				}catch (SQLException sqlex) {
					try{sqlex.printStackTrace();conn.rollback();}catch(Exception e){}	
					
					
				} catch (Exception ex) {
					
					try {
						conn.rollback();
					}
					catch(Exception e) {e.printStackTrace();}
					
					
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
					
					
					
				}
				
				
			}
		};
		
		sendSms = scheduler.scheduleAtFixedRate(runnable,1, 50,
			TimeUnit.MINUTES); 
		
	}
	
	public void cancelScheduler() {
		sendSms.cancel(false);
		
	}
	
}
