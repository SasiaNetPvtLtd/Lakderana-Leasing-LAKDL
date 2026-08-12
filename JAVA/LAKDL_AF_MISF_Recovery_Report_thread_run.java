import java.io.*;
import java.util.*;
import java.sql.*;

public class LAKDL_AF_MISF_Recovery_Report_thread_run extends Thread {
	
	
	private String date;
	private String location_id;
	private String user_id;
	private String username;
	private String finance_no;
	private String cr_offic;
	private String perform_stat;
	private String active_yard_status;
	private String region;
	
	
	
	private String from_date;
	private String to_date;
	private String user;
	private String schema_name;
	private Connection	conn = null;
	CallableStatement callstmt1 =null;
	
	public LAKDL_AF_MISF_Recovery_Report_thread_run(String m_date, String m_location_id, String m_user_id, String m_username, String m_finance_no, String m_cr_offic, String m_perform_stat, String m_active_yard_status, String m_region, String m_schema_name, Connection m_conn){
    	
		this.date               = m_date;
		this.location_id        = m_location_id;
		this.user_id            = m_user_id;
		this.username           = m_username;
		this.finance_no         = m_finance_no;
		this.cr_offic           = m_cr_offic;
		this.perform_stat       = m_perform_stat;
		this.active_yard_status = m_active_yard_status;
		this.region             = m_region;
		
		this.schema_name= m_schema_name;
		this.conn=m_conn;
	}

    public void run() {
		synchronized(this){
			
			
        	System.out.println("Hello from recovery report thread!");
			
			System.out.println("date         : " + this.date);			
			System.out.println("location_id  : " + this.location_id);
			System.out.println("user_id      : " + this.user_id);
			System.out.println("username     : " + this.username);
			System.out.println("finance_no   : " + this.finance_no);
			System.out.println("cr_offic     : " + this.cr_offic);
			System.out.println("perform_stat : " + this.perform_stat);
			System.out.println("active_yard_status : " + this.active_yard_status);
			System.out.println("region       : " + this.region);
			System.out.println("schema_name  : " + this.schema_name);
			System.out.println("conn         : " + this.conn);
			
			
			try{
				
				/*
					callstmt1 = conn.prepareCall( "BEGIN "+schema_name+"."+ 
						"INV_ROUTINE_THREAD_LOG_SAVE(:1,:2,:3,:4);END;");
				
					callstmt1.setString(1 ,date);
					callstmt1.setString(2 ,date);
					callstmt1.setString(3 ,"Status");
					callstmt1.setString(4 ,username);
					callstmt1.execute();
					conn.commit();
					
					callstmt1.close();
					conn.close();	
					*/
				
				    callstmt1 = conn.prepareCall( "BEGIN "+schema_name+"."+ 
						"AF_RE_SAVE_RECOVER_RPT_CHECK(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;");
				
					callstmt1.setString(1 ,date);
					callstmt1.setString(2 ,location_id);
					callstmt1.setString(3 ,user_id);
					callstmt1.setString(4 ,username);
					callstmt1.setString(5 ,finance_no);
					callstmt1.setString(6 ,cr_offic);
					callstmt1.setString(7 ,perform_stat);
					callstmt1.setString(8 ,active_yard_status);
					callstmt1.setString(9 ,region);
					callstmt1.execute();
					conn.commit();
					
					callstmt1.close();
					conn.close();
				
				
				
					
					/*
					System.out.println(" Inside thread 1 ");
					
					callstmt1 = conn.prepareCall("BEGIN "+schema_name+".AF_RE_SAVE_RECOVER_RPT_CHECK(:1,:2,:3,:4,:5,:6,:7,:8,:9);END;"); // added by udara 28-06-2016
					callstmt1.setString(1,date);
					callstmt1.setString(2,location_id);
					callstmt1.setString(3,user_id);
					callstmt1.setString(4,username);
					callstmt1.setString(5,finance_no);
					callstmt1.setString(6,cr_offic);
					callstmt1.setString(7,perform_stat); 
					callstmt1.setString(8,active_yard_status); 
					callstmt1.setString(9,region); 
					
					System.out.println(" Inside thread 2 ");
					
					conn.commit();
					
					System.out.println(" Inside thread 3 ");
					
					callstmt1.close();
					conn.close();

					
					System.out.println(" Inside thread 4 ");

					*/
					
					
					
					
					
				
				
			}
			catch(Exception ee){
				System.out.println("Error occurred in invoice thread " + ee.toString());
			}
			
			
			
			
		}
    }

	/*
    public static void main(String args[]) {
        (new LAKDL_AF_MISF_Recovery_Report_thread_run()).start();
    }
	*/

}