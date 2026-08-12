

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


public class ScheduledSmsSendLAKDL {
	
	private final ScheduledExecutorService scheduler = Executors
		.newScheduledThreadPool(1);
	private ScheduledFuture<?> sendSms;
	
	private static Connection conn;
	private Statement stmt;
	private ResultSet rs ;
	private CallableStatement callstmt;
	private NumberFormat nf;
	
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
	
	public void ScheduledSmsSendLAKDL() {
		
		final Runnable runnable = new Runnable() {
			@SuppressWarnings("unchecked")
				public void run() {
				
				getConnection();
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
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
					
					
					// =============== rental reminder start ===================================================================
					rs = stmt.executeQuery(" "+
						" SELECT COUNT(*) "+
						" FROM "+m_schema_name+".SMS_LOG_RENTAL_REMINDER "+
						" WHERE TRUNC(ENT_DATE) = TRUNC(SYSDATE) "+
						" ");
					
					if  (rs.next()){
						if (rs.getInt(1) > 0) {
							status = "OK";
						}
					}	
					
					System.out.println("Start 1");

					System.out.println(status);
					System.out.println(hour);
					System.out.println(am_pm);
					
					if(hour==0 && am_pm.equals("PM")) // if(hour==0)
						hour=12;
					
					System.out.println(" hour : "+hour);
					
					//status = "OK"; // ADDED BY UDARA TO PREVENT MESSAGES
					
					//if (hour==10 && am_pm.equals("AM") && status.equals("NOT OK")) {
					//if ((hour==7 || hour>7) && am_pm.equals("PM") && status.equals("NOT OK")) { // commented by udara 30-03-2016
					//if ((hour==10 || hour>10) && am_pm.equals("AM") && status.equals("NOT OK")) { // added by udara 30-03-2016 // commented
					
					if ( ((hour==10 || hour>10) && am_pm.equals("AM")) || ((hour<12) && am_pm.equals("PM")) ) {  // commented by udara 06-09-2018// added by udara 19-04-2016
					
					//if ( hour==10 && am_pm.equals("AM") ) { // added by udara 06-09-2018
					
					//if ((hour==8 || hour>7) && am_pm.equals("AM") && status.equals("NOT OK")) {
						//LAKDL_AF_SMS_generation_reminder_2  reminder_sms  = new LAKDL_AF_SMS_generation_reminder_2();
						LAKDL_AF_SMS_generation_reminder_rental reminder_sms  = new LAKDL_AF_SMS_generation_reminder_rental();
						reminder_sms.generateSMS();
					}

				// =============== rental reminder end =========================================================================
					
					
					
				}catch (Exception ex) {
					
					ex.printStackTrace();
					
					try {
						conn.rollback();
					}
					catch(Exception e) {e.printStackTrace();}
					
					
				}
				
				/*
				
				finally {
					try {
						conn.setAutoCommit(true);
					}
					catch(Exception e) {}
					
					
				}
				*/
				
				// added by udara 26-06-2017
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
				// end by udara 26-06-2017
				
			}
		};
		
		//sendSms = scheduler.scheduleAtFixedRate(runnable,1, 4,
		//	TimeUnit.HOURS); 
		
		//sendSms = scheduler.scheduleAtFixedRate(runnable,1, 20,TimeUnit.SECONDS); 
		
		sendSms = scheduler.scheduleAtFixedRate(runnable,1, 1,TimeUnit.HOURS); // added by udara 06-09-2018 // commented by udara 19-04-2016  
		//sendSms = scheduler.scheduleAtFixedRate(runnable,0, 20,TimeUnit.MINUTES); // commented by udara 06-09-2018   // added by udara 19-04-2016 
		
		
	}
	
	public void cancelScheduler() {
		sendSms.cancel(false);
		
	}
	
}
