import java.io.*;
import java.util.*;
import java.sql.*;

public class LAKDL_HelloThread extends Thread {
	
	private String from_date;
	private String to_date;
	private String user;
	private String schema_name;
	private Connection	conn = null;
	CallableStatement callstmt1 =null;
	
	public LAKDL_HelloThread(String m_from_date, String m_to_date, String m_user, String m_schema_name, Connection m_conn){
    	this.from_date = m_from_date;
		this.to_date   = m_to_date;
		this.user      = m_user;
		this.schema_name= m_schema_name;
		this.conn=m_conn;
	}

    public void run() {
		synchronized(this){
			
			
        	System.out.println("Hello from a thread!");
			System.out.println("From Thread : " + this.from_date);
			System.out.println("From Thread : " + this.to_date);
			System.out.println("From Thread : " + this.conn);
			
			try{
				
				/*
				callstmt1 = conn.prepareCall( "BEGIN "+schema_name+"."+ 
						"INV_ROUTINE_THREAD_LOG_SAVE(:1,:2,:3,:4);END;");
				
					callstmt1.setString(1 ,from_date);
					callstmt1.setString(2 ,to_date);
					callstmt1.setString(3 ,"Status");
					callstmt1.setString(4 ,user);
					callstmt1.execute();
					conn.commit();
					
					callstmt1.close();
					conn.close();	
					*/
				
				    callstmt1 = conn.prepareCall( "BEGIN "+schema_name+"."+ 
						"AF_RE_INVOICE_GEN_SAVE(:1,:2,:3,:4,:5);END;");

					callstmt1.setString(1 ,from_date);
					callstmt1.setString(2 ,to_date);
					callstmt1.setString(3 ,"AF_RE_INVOICE_GEN");
					callstmt1.setString(4 ,"NEW");
					callstmt1.setString(5 ,user);
					callstmt1.execute();
					conn.commit();
					
					callstmt1.close();
					conn.close();
				
				
			}
			catch(Exception ee){
				System.out.println("Error occurred in invoice thread " + ee.toString());
			}
			
			
		}
    }

	/*
    public static void main(String args[]) {
        (new LAKDL_HelloThread()).start();
    }
	*/

}