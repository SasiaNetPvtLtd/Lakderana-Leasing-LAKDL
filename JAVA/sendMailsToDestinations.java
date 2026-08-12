// created by udara on 24-01-2013

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import oracle.jdbc.driver.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class sendMailsToDestinations 
{	
	ResultSet rs;
	Statement stmt;
	CallableStatement callstmt;
	BufferedWriter log=null;
    java.text.NumberFormat nf;
	Connection conn;
	
	String m_schema_name      = "NETFAC";
	String m_schema_password  = "EIGHTITENGPW82";
	
	String last_day_end_date = "";
	int    pending_count = 0;
	String e_mail_body_text = "";
	
	String smtp_host_name = "";
	String smtp_port      = "";
	String smtp_auth_user = "";
	String smtp_auth_pwd  = "";
	String display_name   = "";
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> sendEmail;
	
	
	public void collect_data(String shedule_process, String user_name){		
			
			// ==================== GET SQL DATA START ====================================================					
			try{
				
				// Get Last Day End Date
				rs = stmt.executeQuery(" "+
						" SELECT "+
						" TO_CHAR(A.LAST_DAYEND_PROCESS,'DD-MM-YYYY'), "+
						" A.PROCESS_USER, "+
						" A.PROCESS_DATE "+
	  						" FROM "+m_schema_name+".FA_OP_DAYEND_ROUTINE A "+
							" ");
					
				if(rs.next()){
					last_day_end_date = rs.getString(1);
				}
				
				// Get E-Mail Sending Related Details
				rs = stmt.executeQuery("SELECT A.SMTP_HOST_NAME,A.SMTP_PORT, A.SMTP_AUTH_USER, A.SMTP_AUTH_PWD, "+
					" A.DISPLAY_NAME "+
					" FROM "+m_schema_name+".FA_CO_MAS_EMAIL_SERVER_DET A");
					
				if(rs.next()){
					smtp_host_name = rs.getString(1);
					smtp_port      = rs.getString(2);
					smtp_auth_user = rs.getString(3);
					smtp_auth_pwd  = rs.getString(4);
					display_name   = rs.getString(5);
							
				}
				
				// Get Pending Count
				rs = stmt.executeQuery(" "+
					 	" SELECT COUNT(*) "+
					    " FROM   "+m_schema_name+".FA_OP_DAY_END_PENDING_TRN A "+ 
					    " WHERE  A.DAY_END_DATE = TO_DATE('"+last_day_end_date+"', 'DD-MM-YYYY')  "+
					    " AND    A.ENT_USER LIKE '%%' "+
					    " ");
				
				if(rs.next()){
					pending_count = rs.getInt(1);
				}
				
			}
			catch(SQLException sp_1){
				System.out.println("GETTING SQL DETAILS ERROR (GENERAL DETAILS) : " + sp_1.toString());
			}			
			// ==================== GET SQL DATA END =======================================================
			
			
			// ==================== CHECKING AND SET THE E-MAIL BODY FORMAT ================================
			if(pending_count>0){
				e_mail_body_text = set_pending_trans_body(user_name);
			}else{
				e_mail_body_text = set_no_pending_trans_body(user_name);
			}
			// ==================== END CHECKING AND SET THE E-MAIL BODY FORMAT ============================
			
			sending_email("udara@sasianet.com",shedule_process,user_name);
			sending_email("nuwan@sasianet.com",shedule_process,user_name);		
			
	}
	
	public String set_no_pending_trans_body(String user_name){	
		
		String body_text = "";
		
		body_text = body_text + "<table align='center' width='100%' class='table'  border='1'>";
					body_text = body_text + "	   <tr height='25px'> "+
								  "    <td width='10%' align=\"left\"><DIV class=div_input><b> Day end can be proceed  </b></DIV></td> "+
								  "    </tr>";
					body_text = body_text + "</table><br>";
					
		return body_text;
		
	}
	
	public String set_pending_trans_body(String user_name){	
		
			String body_text = "";
			
			try{
				
				updatePendingTransactions(user_name);
				
				body_text = body_text + "<table align='center' width='100%' class='table'  border='0'>";
				body_text = body_text + "	   <tr height='25px'> "+
										"    <td width='10%' align=\"left\"><DIV class=div_input style=\"color: red;\" > You cannot process the day end until you take actions for the below mentioned transactions.  </DIV></td> "+
										"    </tr><br>";
				body_text = body_text + "</table><br>";
									
				body_text = body_text + "<table align='center' width='100%' class='table'  border='0'>";
				body_text = body_text + "	   <tr height='25px'> "+
										"    <td width='10%' align=\"center\"><DIV class=div_input><b><u> Pending Transactions  </u></b></DIV></td> "+
										"    </tr><br>";
				body_text = body_text + "</table>";
									
				body_text = body_text + "<table align='center' width='100%' class='table'  border='1'>";
									
				body_text = body_text + "	   <tr bgcolor=\"#999999\" height='25px'> "+
										"    <td width='10%' align=\"left\"><DIV class=div_input><b> Facility No. </b></DIV></td> "+
										"    <td width='10%' align=\"left\"><DIV class=div_input><b> Document Ref </b></DIV></td> "+
										"    <td width='10%' align=\"left\"><DIV class=div_input><b> Document Type </b></DIV></td> "+
									    "    <td width='10%' align=\"left\"><DIV class=div_input><b> Status </b></DIV></td> "+
										"    </tr>";
									
				rs = stmt.executeQuery(" "+
											" SELECT "+
												" FACILITY_NO, "+
												" DOCREF_NO, "+
												" DOC_TYPE, "+
												" DOC_TYPE_ORDER, "+
												" CRITICAL_STATUS, "+
												" USER_CODES "+
													" FROM "+m_schema_name+".FA_OP_DAY_END_PENDING_TRANS "+
										" ");
						
									while(rs.next()){
										
										body_text = body_text + "	   <tr height='25px'> "+
										  "    <td width='10%' align=\"left\"><DIV class=div_input> "+rs.getString(1)+" </DIV></td> "+
										  "    <td width='10%' align=\"left\"><DIV class=div_input> "+rs.getString(2)+" </DIV></td> "+
										  "    <td width='10%' align=\"left\"><DIV class=div_input> "+rs.getString(3)+" </DIV></td> "+
									      "    <td width='10%' align=\"left\"><DIV class=div_input> "+rs.getString(5)+" </DIV></td> "+
										  "    </tr>";
										
									}
		
									body_text = body_text + "</table>"+
									"    <br>";
									
				return body_text;									
	
			}
			catch(SQLException sp_2){
				
				body_text = "GETTING SQL DETAILS ERROR (PENDING APPROVAL BODY) : " + sp_2.toString();
				return body_text;
				
			}
		
	}
	
		
	public void sending_email(String m_email, String shedule_process, String user_name){	
	
		try {				
				Properties props = new Properties();
					
				props.put("mail.smtp.host", smtp_host_name);
						
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", smtp_port); 
				props.put("mail.smtp.starttls.enable", "false");
				props.put("mail.smtp.socketFactory.fallback", "false");    
				props.setProperty("mail.smtp.quitwait", "false");    
				props.setProperty("mail.smtp.sasl.enable", "true");    
				props.put("mail.smtp.sendpartial", "true");
						
				NETFAC_FA_CR_SMTPAuthenticator auth = new NETFAC_FA_CR_SMTPAuthenticator(smtp_auth_user,smtp_auth_pwd);			
					
				Session mail_session = Session.getInstance(props,auth);
				mail_session.setDebug(false);
										
				javax.mail.Message msg = new MimeMessage(mail_session);
					
				InternetAddress addressFrom = new InternetAddress(smtp_auth_user, display_name);
				msg.setFrom(addressFrom);
									
				InternetAddress addressTo = new InternetAddress(m_email);
				msg.setRecipient(javax.mail.Message.RecipientType.TO, addressTo);
										
				// Setting the Subject and Content Type
				msg.setSubject("Day End : "+ " Test"); 
				BodyPart messageBodyPart = new MimeBodyPart();
						 
				//messageBodyPart.setText(" Auto generated email");
						
				String body_text = e_mail_body_text;
				
				//messageBodyPart.setText(body_text);
				messageBodyPart.setContent(body_text, "text/html");
		
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
		
				// Completing the  message and sending it
				List messages = new ArrayList();				
				msg.setContent(multipart);
				messages.add(msg);	
						
				List thread1  = new ArrayList();				
				Iterator itr = messages.iterator();				
				while(itr.hasNext()){
					msg = (javax.mail.Message)itr.next();
					thread1.add(msg);
				}
						
				List all_threads =  new ArrayList();				
				all_threads.add(thread1);
						
				Iterator itr_threads = all_threads.iterator();
						
				while(itr_threads.hasNext()){
					try{
						List msgs = (List)itr_threads.next();
						if(msgs.size()>0){
							new NETFAC_EMAIL_Thread(msgs).start();
						}
					}
					catch (Exception ex) {
						System.out.println(ex.toString());
					}
						
				} 
				
				System.out.println("MAIL SENT " + shedule_process);
						
        }	
        catch (Exception e) {
            System.out.println("ERROR IN SENDING E-MAILS "+e.toString());
        }
		
		updateSheduleDetails(shedule_process,user_name);
	
		
	}
	
	
	public void timeShedule(){

		int email_hh = 0;
		int email_mm = 0;
		
		int day_end_email_hh = 0;
		int day_end_email_mm = 0;
		
		int sent_count = 0;
		
		int day_end_count = 0;
		
		try{
			
			rs = stmt.executeQuery(" "+
						 	" SELECT A.SHEDULE_TIME_HOURS, "+
							"        A.SHEDULE_TIME_MINS "+
						    " FROM   "+m_schema_name+".FA_OP_DAY_END_SHEDULE_PROCESS A "+ 
						    " WHERE  A.SHEDULE_ID = 'BEFORE_ALERT_EMAIL'  "+
						    " ");
					
			if(rs.next()){
				email_hh = rs.getInt(1);
				email_mm = rs.getInt(2);
			}
			
			rs = stmt.executeQuery(" "+
						 	" SELECT A.SHEDULE_TIME_HOURS, "+
							"        A.SHEDULE_TIME_MINS "+
						    " FROM   "+m_schema_name+".FA_OP_DAY_END_SHEDULE_PROCESS A "+ 
						    " WHERE  A.SHEDULE_ID = 'DAYEND_PROCESS'  "+
						    " ");
					
			if(rs.next()){
				day_end_email_hh = rs.getInt(1);
				day_end_email_mm = rs.getInt(2);
			}
					

			
			rs = stmt.executeQuery(" "+
						 	" SELECT COUNT(A.SHEDULE_ID) "+
						    " FROM   "+m_schema_name+".FA_OP_DAY_END_SHEDULE_STATUS A "+ 
						    " WHERE  A.SHEDULE_ID = 'BEFORE_ALERT_EMAIL'  "+
							" AND    TRUNC(A.SHEDULE_TIME) = TRUNC(SYSDATE) "+
							" AND    A.SHEDULE_STATUS = 'Y' "+
						    " ");
			
			if(rs.next()){
				sent_count = rs.getInt(1);
			}
			
			
			rs = stmt.executeQuery(" "+
						 	" SELECT COUNT(A.SHEDULE_ID) "+
						    " FROM   "+m_schema_name+".FA_OP_DAY_END_SHEDULE_STATUS A "+ 
						    " WHERE  A.SHEDULE_ID = 'DAYEND_PROCESS'  "+
							" AND    TRUNC(A.SHEDULE_TIME) = TRUNC(SYSDATE) "+
							" AND    A.SHEDULE_STATUS = 'Y' "+
						    " ");
			
			if(rs.next()){
				day_end_count = rs.getInt(1);
			}

			
		}
		catch(SQLException sp_3){
				System.out.println("GETTING SQL DETAILS ERROR (GETTING TIME SHEDULE) : " + sp_3.toString());
		}	
		
		
		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int hour_24 = calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hour_24==email_hh && minute>=email_mm && sent_count==0 ){

			collect_data("BEFORE_ALERT_EMAIL","TEST_USER");
			
		}
		
		
		if(hour_24==day_end_email_hh && minute>=day_end_email_mm && day_end_count==0 ){

			e_mail_body_text = ready_stage_for_day_end();
			sending_email("udara@sasianet.com","DAYEND_PROCESS","TEST_USER");
			
		}

		
	}
	
	
	public String ready_stage_for_day_end(){	
		
		String body_text = "";
		
		body_text = body_text + "<table align='center' width='100%' class='table'  border='1'>";
					body_text = body_text + "	   <tr height='25px'> "+
								  "    <td width='10%' align=\"left\"><DIV class=div_input><b> Ready stage for day end process </b></DIV></td> "+
								  "    </tr>";
					body_text = body_text + "</table><br>";
					
		return body_text;
		
	}


	
	public void sendMailsToDestinations() {
		
		final Runnable runnable = new Runnable() {
			@SuppressWarnings("unchecked")
				public void run() {
				
				try{		
					getting_connection();	
				
				}catch(Exception e){
					System.out.println("ERROR RETRIEWVING DB CONNECTION "+e.toString());
					e.printStackTrace();
				}
				
				timeShedule();
				
			}
		};
		
		sendEmail = scheduler.scheduleAtFixedRate(runnable,1, 50,TimeUnit.MINUTES); 
		//sendEmail = scheduler.scheduleAtFixedRate(runnable,1, 2,TimeUnit.SECONDS); 
		
	}
	
	public void cancelScheduler() {
		
		try{
			sendEmail.cancel(false);
		}
		catch(Exception can_sh){
			can_sh.printStackTrace();
		}
		
	}
	
	
	public void email_reports(){	

	    try{
			sendMailsToDestinations();
		}
		catch(Exception eeee){
			System.out.println(" ERROR " + eeee.toString()); 
		}
		
	}
	
	
	//public Connection getting_connection() {
	public void getting_connection() {

		String connectionString   = "jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB"; 

        try {	
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); 
	                conn = DriverManager.getConnection(connectionString,m_schema_name,m_schema_password);	
					stmt = conn.createStatement();
        }	
        catch (Exception e) {
            System.out.println("ERROR CREATING DB CONNECTION "+e.toString());
        }
		
        //return conn;
		
    }
	


	public void updateSheduleDetails(String shedule_process, String user_name){		
	
		try {
					callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".FA_DAY_END_SHEDULE_STATUS_UP(:1,:2); END;");
					callstmt.setString(1,shedule_process);
					callstmt.setString(2,user_name); 
					callstmt.execute();					
					conn.commit();
		}
		catch (SQLException sqlex) {
					try{sqlex.printStackTrace();conn.rollback();}catch(Exception e){}						
					
		}

	}
	
	
	public void updatePendingTransactions(String user_name){
		
		try {
					callstmt  = conn.prepareCall("BEGIN " + m_schema_name + ".FA_DAY_END_PEND_TRANS_COLLECT(:1); END;");
					callstmt.setString(1,user_name); 
					callstmt.execute();					
					conn.commit();
		}
		catch (SQLException sqlex) {
					try{sqlex.printStackTrace();conn.rollback();}catch(Exception e){}						
					
		}
		
	}
	
	
	
}

