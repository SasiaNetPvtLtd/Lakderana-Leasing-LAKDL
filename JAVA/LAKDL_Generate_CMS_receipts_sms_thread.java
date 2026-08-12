// udara 03-03-2017

import java.io.*;
import java.util.*;
import java.sql.*;

import java.util.regex.*;
import oracle.jdbc.driver.*;

public class LAKDL_Generate_CMS_receipts_sms_thread extends Thread {
	
	private String client_no;
	private String finance_no;
	private String rec_no;
	private String amount;
	private String type;
	private String allo_mode;
	private String user;
	private String schema_name;
	private Connection	conn = null;
	CallableStatement callstmt =null;
	
	//public LAKDL_Generate_receipts_sms_thread(String m_client_no, String m_finance_no, String m_rec_no, String m_amount, String m_type, String m_user, String m_schema_name, Connection m_conn){
	public LAKDL_Generate_CMS_receipts_sms_thread(String m_client_no, String m_finance_no, String m_rec_no, String m_amount, String m_type, String m_user, String m_schema_name,String m_allo_mode){
		this.client_no   = m_client_no;
		this.finance_no  = m_finance_no;
		this.rec_no      = m_rec_no;
		this.amount      = m_amount;
		this.type        = m_type;
		this.allo_mode   = m_allo_mode;
		this.user        = m_user;
		this.schema_name = m_schema_name;
		//this.conn        = m_conn;
	}
	
	
	public void run() {
		//synchronized(this){
		
		
		
		try{
			
			System.out.println("From CMS SMS Thread Start ");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82"); // development
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); // lakdl test
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin"); // lakdl live
			conn = DriverManager.getConnection("jdbc:oracle:thin:@BKPTST01:1521:LAKDB", "LAKDL", "SNORA7623ADMIN"); // bkptest
			
			System.out.println("From CMS SMS Thread Conn Created: " + this.conn);
			
			ResultSet rs         = null;
			Statement stmt       = null;
			String m_msg         = null;
			String mm_vehicle_no = null;
			String mm_message    = null;
			
			stmt = conn.createStatement();
			
			String smpp_host_name = "";
			int    smpp_port         = 0;
			String smpp_path      = "";
			String smpp_auth_user = "";
			String smpp_auth_pwd  = "";
			String smpp_display_name  = "";
			
			String m_phone_num = "714199938";
			String m_message = "Test From Receipts New " + client_no;
			
			String m_date = "";
			String m_vehicle_no = "";
			
			rs = stmt.executeQuery(" "+
				//" SELECT SUBSTR(A.MOBILE_NO,2), "+ // commented by udara 15-07-2019
				" SELECT SUBSTR(NVL(A.MOBILE_NO,A.DIRECT_TEL_NO),2), "+ // added by udara 15-07-2019
				" TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+
				" NVL("+schema_name+".AF_CO_GET_VEHICLE_NO("+schema_name+".AF_CO_GET_APPLICATION_NO('"+finance_no+"')),'-') "+
				" FROM "+schema_name+".AF_CO_MAS_CLIENT A "+
				" WHERE CLIENT_CODE = '"+client_no+"'  "+
				" ");
			
			if(rs.next()){
				m_phone_num  = rs.getString(1);
				m_date       = rs.getString(2);
				m_vehicle_no = rs.getString(3);
			}
			
			
			String send_sms_flag = "N";
			
			rs = stmt.executeQuery(" "+
				" SELECT "+schema_name+".AF_RE_IS_VEHICLE_IN_YARD2('"+finance_no+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))  "+
				"  FROM DUAL  "+
				" ");
			
			if(rs.next()){
				send_sms_flag = rs.getString(1);
			}
			
			System.out.println("Allocation mode*****"+allo_mode+"**********Finance_no::::"+finance_no);
			if(!finance_no.equals("")){
				System.out.println(":::::::::::::::::::::::::::CMS WITH CONTRATS :TEST MILINDA:::::::::::::::::::::::::::::");
				if(send_sms_flag.equals("Y")){ 
					
					if(type.equals("receipt")){
						//m_message = "Thread! Obage "+m_vehicle_no+" darana wahanaya sandaha Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
						//m_message = "Obage "+m_vehicle_no+" darana wahanaya sandaha Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
						//m_message = "Ayubowan! Obage "+m_vehicle_no+" darana wahanaya sandaha Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
						
						//m_message = "Ayubowan! (Obage "+m_vehicle_no+" darana wahanaya sandaha) Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
						m_message = "Ayubowan! Obage "+m_vehicle_no+" darana wahanaya sandaha Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
						
					}
				}
			}else{
				System.out.println("::::::::::::::::::::::::::CMS WITHOUT CONTRATS :TEST MILINDA::::::::::::::::::::::::::::");
				if(type.equals("receipt")){
					//m_message = "Ayubowan! (Obage "+m_vehicle_no+" darana wahanaya sandaha) Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
					m_message = "Ayubowan! Rs."+amount+" ka mudalak, "+m_date+" dina ginumgatha wee athi bawa danwa sitimu. Suba Dawasak! Lakderana";
				}
				
			}
			
			rs = stmt.executeQuery("SELECT A.SMPP_HOST_NAME, A.SMPP_PORT, A.PATH,A.SMPP_AUTH_USER, A.SMPP_AUTH_PWD,A.DISPLAY_NAME"+
				" FROM "+schema_name+".AF_CO_MAS_SMS_SERVER_DET A");
			
			if(rs.next()){
				smpp_host_name    = rs.getString(1);
				smpp_port         = rs.getInt(2);
				smpp_path         = rs.getString(3);
				smpp_auth_user    = rs.getString(4);
				smpp_auth_pwd     = rs.getString(5);
				smpp_display_name = rs.getString(6);	
			}
			
			LAKDL_SasianetSms objSms = new LAKDL_SasianetSms();
			
			String message = m_message; 
			
			mm_vehicle_no = m_vehicle_no;
			mm_message    = m_message;
			
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
			
			// commented by udara 20-04-2021
			/*
			
			m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
			
			System.out.println("BEFORE SAVE CMS SMS LOG");
			
			String m_status = m_msg.substring(0,m_msg.indexOf(":"));
			String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
			
			callstmt  = conn.prepareCall("BEGIN " + schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
			if(!finance_no.equals("")){
				callstmt.setString(1,finance_no);
			}else{
				callstmt.setString(1,client_no);
			}
			callstmt.setString(2,mm_vehicle_no);
			callstmt.setString(3,m_status);
			callstmt.setString(4,m_ref);
			callstmt.setString(5,null);
			callstmt.setString(6,user);  
			callstmt.setString(7,mm_message);  
			callstmt.setString(8,type);  
			callstmt.setString(9,rec_no); 
			callstmt.execute();
			conn.commit();
			
			
			//callstmt.close();
			//conn.close();
			
			System.out.println("AFTER SAVE CMS SMS LOG");
			
			
			//} // send_sms_flag	
			
			*/
			
			// added by udara 20-04-2020
			if(!finance_no.equals("")){
				if(send_sms_flag.equals("Y")){ 
					if(type.equals("receipt")){
						
						m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
			
						System.out.println("BEFORE SAVE CMS SMS LOG");
						
						String m_status = m_msg.substring(0,m_msg.indexOf(":"));
						String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
						
						callstmt  = conn.prepareCall("BEGIN " + schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
						if(!finance_no.equals("")){
							callstmt.setString(1,finance_no);
						}else{
							callstmt.setString(1,client_no);
						}
						callstmt.setString(2,mm_vehicle_no);
						callstmt.setString(3,m_status);
						callstmt.setString(4,m_ref);
						callstmt.setString(5,null);
						callstmt.setString(6,user);  
						callstmt.setString(7,mm_message);  
						callstmt.setString(8,type);  
						callstmt.setString(9,rec_no); 
						callstmt.execute();
						conn.commit();
						
						System.out.println("AFTER SAVE CMS SMS LOG");
						
					}
				}
			}
			else{
				if(type.equals("receipt")){
					
						m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
			
						System.out.println("BEFORE SAVE CMS SMS LOG");
						
						String m_status = m_msg.substring(0,m_msg.indexOf(":"));
						String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
						
						callstmt  = conn.prepareCall("BEGIN " + schema_name + ".SAVE_SMS_LOG(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
						if(!finance_no.equals("")){
							callstmt.setString(1,finance_no);
						}else{
							callstmt.setString(1,client_no);
						}
						callstmt.setString(2,mm_vehicle_no);
						callstmt.setString(3,m_status);
						callstmt.setString(4,m_ref);
						callstmt.setString(5,null);
						callstmt.setString(6,user);  
						callstmt.setString(7,mm_message);  
						callstmt.setString(8,type);  
						callstmt.setString(9,rec_no); 
						callstmt.execute();
						conn.commit();
						
						System.out.println("AFTER SAVE CMS SMS LOG");
						
				}
			}
			// end by udara 20-04-2020
			
			callstmt.close();
			conn.close();
			
			
		}
		catch(Exception ee){		
			try{conn.close(); callstmt.close();}catch(Exception e){}
			System.out.println("Error occurred in sms thread " + ee.toString());
		}
		
		
		//} synch
	}
	
	
}