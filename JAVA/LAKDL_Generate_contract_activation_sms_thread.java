// udara 03-03-2017

import java.io.*;
import java.util.*;
import java.sql.*;

import java.util.regex.*;
import oracle.jdbc.driver.*;

public class LAKDL_Generate_contract_activation_sms_thread extends Thread {
	
	private String app_no;
	private String user;
	private String schema_name;
	private Connection	conn = null;
	CallableStatement callstmt =null;

	public LAKDL_Generate_contract_activation_sms_thread(String m_app_no, String m_user, String m_schema_name){
		this.app_no   = m_app_no;
		this.user        = m_user;
		this.schema_name = m_schema_name;
	}
	
	
	public void run() {
		//synchronized(this){
		try{
			
			System.out.println("From Contract Activation Thread Start ");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82"); // development
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); // lakdl test
			conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin"); // lakdl live
			
			System.out.println("From Contract Activation Thread Conn Created: " + this.conn);
			
			ResultSet rs         = null;
			Statement stmt       = null;
			String m_msg         = null;
			String mm_message    = null;
			
			stmt = conn.createStatement();
			
			String smpp_host_name = "";
			int    smpp_port         = 0;
			String smpp_path      = "";
			String smpp_auth_user = "";
			String smpp_auth_pwd  = "";
			String smpp_display_name  = "";

			String m_phone_num = "766671555";
			String m_message = "Test From App No " + app_no;
			
			rs = stmt.executeQuery(" "+
					" SELECT B.MOBILE_NO "+
					" FROM "+schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
					" AND A.APPLICATION_NO = '"+app_no+"' "+
			" ");			
			
			if(rs.next()){
				m_phone_num  = rs.getString(1);
			}

			m_message = "Suba pathum!!! Oba wisin ayadum kala mulya pahasukama apa ayathanaya wisin anumatha kala bawa sathutin danwa sitimu. Obage mulya pahasukama sandaha apa aayathanaya thora ganeema pilibandawa sthuthiwantha wemu. Lakderana Investments Ltd.";
			
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
			
			//mm_vehicle_no = m_vehicle_no;
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
			
			m_msg = objSms.SendMessage ( smpp_host_name,smpp_port ,smpp_path,smpp_auth_user,smpp_auth_pwd,smpp_display_name,m_phone_num,message);
			
			System.out.println("BEFORE SAVE CONTRACT ACTIVATION SMS");
			
			String m_status = m_msg.substring(0,m_msg.indexOf(":"));
			String m_ref = m_msg.substring(m_msg.lastIndexOf(":"),m_msg.length());
			
			
			callstmt  = conn.prepareCall("BEGIN " + schema_name + ".CON_ACTIVE_SAVE_SMS_LOG(:1,:2,:3,:4,:5); END;");
			
			callstmt.setString(1,app_no);
			callstmt.setString(2,m_status);
			callstmt.setString(3,m_ref);
			callstmt.setString(4,user);  
			callstmt.setString(5,"Activation SMS");  
			callstmt.execute();
			conn.commit();
			
			callstmt.close();
			
			
			System.out.println("AFTER SAVE CONTRACT ACTIVATION SMS");
			
			conn.close();
			
			
		}
		catch(Exception ee){		
			try{conn.close(); callstmt.close();}catch(Exception e){}
			System.out.println("Error occurred in contarct activation sms thread " + ee.toString());
		}
		
		
		//} synch
	}
	
	
}