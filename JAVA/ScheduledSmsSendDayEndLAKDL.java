

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

import java.text.SimpleDateFormat;  // added by udara 03-05-2019
import java.util.Date; // added by udara 03-05-2019


public class ScheduledSmsSendDayEndLAKDL {
	
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
			
	}
	
	public void ScheduledSmsSendDayEndLAKDL() {
		
		final Runnable runnable = new Runnable() {
			@SuppressWarnings("unchecked")
				public void run() {
				
				getConnection();
				LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
				String m_schema_name = m_sn_methods.schema_name.trim();
				String m_username = m_sn_methods.username;
				String status = "NOT_OK";
				String m_time = "";
				
				try {
					
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					

					rs = stmt.executeQuery(" "+
						" SELECT COUNT(*) "+
						" FROM "+m_schema_name+".AF_CO_DAY_END_PROC_LOG "+
						" WHERE PROCESS_DESC = 'DAY_END_REPORT_RUN' "+
						" AND STATUS = 'COMPLETED' "+
						" AND DAY_END_DATE = TRUNC(SYSDATE) - 1 "+
						" ");
					
					if  (rs.next()){
						if (rs.getInt(1) > 0) {
							status = "OK";
						}
					}	
					
					
					
					if(status.equals("OK")){						
						
					 	LAKDL_AF_SMS_generation_day_end reminder_sms  = new LAKDL_AF_SMS_generation_day_end();
						reminder_sms.generateSMS("DAY_END_REPORT_RUN");
					}
					
					// added by udara 03-05-2019
					try {
			
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");  
					    Date date = new Date();  

					    String sys_time = formatter.format(date);  
						File statText = new File("E:/SasiaNet/NetAsset/LAKDL/UPLOAD/SheduleChkFolder/SheduleChk.txt");	
			            FileOutputStream is = new FileOutputStream(statText);
			            OutputStreamWriter osw = new OutputStreamWriter(is);    
			            Writer w = new BufferedWriter(osw);
			
						w.write(status + " " + sys_time);
			
			            w.close();
						
					} catch (IOException eex) {
			            eex.printStackTrace(); 
			        }
					// end by udara 03-05-2019
					
				}catch (Exception ex) {
					
					ex.printStackTrace();
					
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
					
					
				}
				
				
				
			}
		};
		

		//sendSms = scheduler.scheduleAtFixedRate(runnable,1, 20,TimeUnit.SECONDS); 
		//sendSms = scheduler.scheduleAtFixedRate(runnable,1, 1,TimeUnit.HOURS); 
		sendSms = scheduler.scheduleAtFixedRate(runnable,0, 1,TimeUnit.HOURS); // commented on 06-05-2019
		//sendSms = scheduler.scheduleAtFixedRate(runnable,0, 1,TimeUnit.MINUTES); // enabled on 06-05-2019 
		//sendSms = scheduler.scheduleAtFixedRate(runnable,0, 15,TimeUnit.MINUTES); 
		
		
	}
	
	public void cancelScheduler() {
		sendSms.cancel(false);
		
	}
	
}
