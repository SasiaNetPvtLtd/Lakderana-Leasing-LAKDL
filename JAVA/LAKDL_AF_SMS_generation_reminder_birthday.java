 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.sql.CallableStatement;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import java.text.NumberFormat;
 import java.util.Locale;
 import java.util.Vector;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.servlet.ServletOutputStream;
 
 public class LAKDL_AF_SMS_generation_reminder_birthday
 {
   public static Connection conn;
   String m_msg;
   String m_url;
   CallableStatement callstmt;
   ServletOutputStream out = null;
   Statement stmt = null;
   public ResultSet rs = null;
   BufferedWriter log = null;
   NumberFormat nf;
   String m_fschema_name = "";
   String m_schema_name = "";
 
   String m_client_code = "";
 
   String m_message = "";
   String m_phone_num = "";
 
   private static void getConnection() {
     try {
       Class.forName("oracle.jdbc.driver.OracleDriver");
 
         conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin"); // LIVE
	     //conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); // TEST
		 //conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.20.1:1521:SNPDDB", "LAKDL", "EIGHTITENGPW82"); // development
     }
     catch (Exception localException) {
       System.out.println("Error:" + localException.toString());
     }
   }
 
   public synchronized void generateSMS()
     throws IOException
   {
     try
     {
       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
 
       getConnection();
       conn.setAutoCommit(false);
 
       this.m_fschema_name = localLAKDL_AF_CO_conn_methods.client_name.trim();
       this.m_schema_name = localLAKDL_AF_CO_conn_methods.schema_name.trim();
 
       String str1 = localLAKDL_AF_CO_conn_methods.username;
 
       this.nf = NumberFormat.getInstance(Locale.US);
       this.nf.setMinimumFractionDigits(2);
       this.nf.setMaximumFractionDigits(2);
       this.m_msg = "'Information saved successfully'";
 
       this.stmt = conn.createStatement();
 
       Vector localVector1 = new Vector();
       Vector localVector2 = new Vector();
       String str2 = "";
       int i = 0;
       String str3 = "";
       String str4 = "";
       String str5 = "";
       String str6 = "";
 
       this.rs = this.stmt.executeQuery("SELECT A.SMPP_HOST_NAME, A.SMPP_PORT, A.PATH,A.SMPP_AUTH_USER, A.SMPP_AUTH_PWD,A.DISPLAY_NAME FROM " + this.m_schema_name + ".AF_CO_MAS_SMS_SERVER_DET A");
 
       if (this.rs.next()) {
         str2 = this.rs.getString(1);
         i = this.rs.getInt(2);
         str3 = this.rs.getString(3);
         str4 = this.rs.getString(4);
         str5 = this.rs.getString(5);
         str6 = this.rs.getString(6);
       }
 
       LAKDL_SasianetSms localLAKDL_SasianetSms = new LAKDL_SasianetSms();
 
       String str7 = this.m_message;
 
       String str8 = "\n";
       String str9 = "%0D%0A";
 
       Pattern localPattern = Pattern.compile(str8);
 
       Matcher localMatcher = localPattern.matcher(str7);
       str7 = localMatcher.replaceAll(str9);
 
       str8 = " ";
       str9 = "%20";
 
       localPattern = Pattern.compile(str8);
 
       localMatcher = localPattern.matcher(str7);
       str7 = localMatcher.replaceAll(str9);
	
			
		this.rs = this.stmt.executeQuery("  "+
					" SELECT CLIENT_CODE, MOBILE_NO "+
					" FROM " + this.m_schema_name + ".AF_TBL_BIRTHDAY_REMINDER "+
					" WHERE STATUS = 'N' "+
			" ");
			
			
		
		this.callstmt = conn.prepareCall("BEGIN " + this.m_schema_name + ".SAVE_SMS_LOG_BDAY_REMINDER(:1,:2,:3,:4,:5); END;");
		
		
       while (this.rs.next())
       {
		 
			
         this.m_client_code = this.rs.getString(1);

		 this.m_message = ("Happy Birthday!" + " " + "Wishing you a Wonderful birthday and a year of good Health, Happiness and Success...."+ " " + "Lakderana Investments Limited");
 
         str7 = this.m_message;
 
         localPattern = Pattern.compile(str8);
 
         localMatcher = localPattern.matcher(str7);
         str7 = localMatcher.replaceAll(str9);
 
         this.m_phone_num = this.rs.getString(2);
 
         this.m_msg = localLAKDL_SasianetSms.SendMessage(str2, i, str3, str4, str5, str6, this.m_phone_num, str7);
         String str10 = this.m_msg.substring(0, this.m_msg.indexOf(":"));
         String str11 = this.m_msg.substring(this.m_msg.lastIndexOf(":"), this.m_msg.length());
		 

         this.callstmt.setString(1, this.m_client_code);
         this.callstmt.setString(2, str10);
         this.callstmt.setString(3, str11);
         this.callstmt.setString(4, this.m_message);
		 this.callstmt.setString(5, "LAKDLALL");
         this.callstmt.execute();
		 
			
         //conn.commit();
 
         System.out.println("BIRTHDAY REMINDER SENT");
 
         //Thread.sleep(50);
       }
		
		//this.callstmt.close(); 
		
		conn.commit();
		this.callstmt.close(); 
 
     }
     catch (Exception localException4)
     {
       localException4.printStackTrace();
       System.out.println(localException4.toString());
       try
       {
         conn.rollback();
       } catch (Exception localException8) {
         this.out.println(localException8.toString());
       }
     }
     finally
     {
       try
       {
         conn.setAutoCommit(true);
       }
       catch (Exception localException9) {
       }
       if (conn != null)
         try {
           conn.close();
         }
         catch (Exception localException10)
         {
         }
       if (this.out != null)
         try {
           this.out.close();
         }
         catch (Exception localException11)
         {
         }
     }
   }
 }

