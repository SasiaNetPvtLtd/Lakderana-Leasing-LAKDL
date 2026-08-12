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
 
 public class LAKDL_AF_SMS_generation_reminder_rental
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
 
   String m_finance_no = "";
   String mm_vehicle_no = "";
   String m_type = "";
   String m_doc_ref_no = "";
 
   String m_message = "";
   String m_phone_num = "";
 
   private static void getConnection() {
     try {
       Class.forName("oracle.jdbc.driver.OracleDriver");
 
       conn = DriverManager.getConnection("jdbc:oracle:thin:@DBSVR01:1521:LAKDB", "LAKDL", "snora7623admin"); // LIVE
	     //conn = DriverManager.getConnection("jdbc:oracle:thin:@NetAsset:1521:LAKTESTDB", "LAKDL", "EIGHTITENGPW82"); // TEST
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
 
       //this.rs = this.stmt.executeQuery("  SELECT  B.CLIENT_CODE,  B.APPLICATION_NO APPLICATION_NO,   NVL(D.MOBILE_NO,'-'),   NVL(" + this.m_schema_name + ".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO),'-') VEHICLE_NO, " + " TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),  " + " A.GRENTAL_AMOUNT, " + " NVL(B.FINANCE_NO,'-')    " + " FROM   " + this.m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A, " + this.m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B, " + this.m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS C, " + this.m_schema_name + ".AF_CO_MAS_CLIENT D " + " WHERE  TRUNC(A.RENTAL_DATE) = TRUNC(SYSDATE)+3 " + " AND B.APPLICATION_NO   = C.APPLICATION_NO " + " AND A.PRO_INVOICE_NO   = C.INVOICE_NO " + " AND A.APPLICATION_NO   = B.APPLICATION_NO " + " AND B.CLIENT_CODE      = D.CLIENT_CODE " + " AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') " + " AND C.ACTIVE_STATUS    = 'Y' " + " AND B.STATUS = 'PERFORM'  " + " AND B.BRANCH_CODE IN ('ALDH','BLDH','BGLDH','CHLDH','DLDH','EHLDH','ELDH', " + " 'GALDH','GLDH','HNLDH','HPLDH','HLDH','KTLDH','CLDH', " + " 'KELDH','KILDH','KOLDH','KLDH','MTLDH','MLDH','NLDH', " + " 'NELDH','PLDH','PYLDH','LDH','RLDH','WLDH','WMLDH') " + " AND " + this.m_schema_name + ".AF_RE_IS_VEHICLE_IN_YARD2(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) = 'Y' " + " AND B.FINANCE_NO NOT IN (SELECT CONTRACT_NO FROM " + this.m_schema_name + ".SMS_LOG_RENTAL_REMINDER WHERE TRUNC(ENT_DATE) = TRUNC(SYSDATE)) " + " ORDER BY APPLICATION_NO " + " ");
 		
		// commented by udara 23-08-2018
		/*
		this.rs = this.stmt.executeQuery("  "+
				" SELECT  "+
						" B.CLIENT_CODE,  "+
						" B.APPLICATION_NO APPLICATION_NO, "+
						" NVL(D.MOBILE_NO,'-'), "+
						" NVL(" + this.m_schema_name + ".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO),'-') VEHICLE_NO, " +
						" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),  " +
						" A.GRENTAL_AMOUNT, " +
						" NVL(B.FINANCE_NO,'-')    " +
								" FROM   " + this.m_schema_name + ".AF_CO_PRO_APP_INSTALLMENT A, " + this.m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B, " + this.m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS C, " + this.m_schema_name + ".AF_CO_MAS_CLIENT D,  " + this.m_schema_name + ".AF_MASTER_ARR_RPT_FINAL E " +
								" WHERE  TRUNC(A.RENTAL_DATE) = TRUNC(SYSDATE)+3 " +
								" AND B.APPLICATION_NO   = C.APPLICATION_NO " +
								" AND B.APPLICATION_NO   = E.APPLICATION_NO "+
								" AND A.PRO_INVOICE_NO   = C.INVOICE_NO " +
								" AND A.APPLICATION_NO   = B.APPLICATION_NO " +
								" AND B.CLIENT_CODE      = D.CLIENT_CODE " +
								" AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') " +
								" AND C.ACTIVE_STATUS    = 'Y' " +
								" AND B.STATUS = 'PERFORM'  " +
								" AND E.TOTAL_AMOUNT > 0 "+
								" AND B.BRANCH_CODE IN ('ALDH','BLDH','BGLDH','CHLDH','DLDH','EHLDH','ELDH', " +
								" 'GALDH','GLDH','HNLDH','HPLDH','HLDH','KTLDH','CLDH', " + 
								" 'KELDH','KILDH','KOLDH','KLDH','MTLDH','MLDH','NLDH', " +
								" 'NELDH','PLDH','PYLDH','LDH','RLDH','WLDH','WMLDH') " +
								" AND " + this.m_schema_name + ".AF_RE_IS_VEHICLE_IN_YARD2(B.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) = 'Y' " +
								" AND B.FINANCE_NO NOT IN (SELECT CONTRACT_NO FROM " + this.m_schema_name + ".SMS_LOG_RENTAL_REMINDER WHERE TRUNC(ENT_DATE) = TRUNC(SYSDATE)) " +
								" ORDER BY APPLICATION_NO " + " ");
			
			*/
		
		// added by udara 23-08-2018
		this.rs = this.stmt.executeQuery("  "+
					" SELECT  "+
						" CLIENT_CODE, "+  
						" APPLICATION_NO, "+  
						" MOBILE_NO,  "+ 
						" VEHICLE_NO, "+
						" TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),  "+
						" GRENTAL_AMOUNT, "+
						" NVL(FINANCE_NO,'-') "+
							" FROM " + this.m_schema_name + ".AF_TBL_RENTAL_REMINDER "+
							" ORDER BY APPLICATION_NO "+ 
			" ");
		// end by udara 23-08-2018
		
		this.callstmt = conn.prepareCall("BEGIN " + this.m_schema_name + ".SAVE_SMS_LOG_RENTAL_REMINDER(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
		
		
       while (this.rs.next())
       {
		 	
		 // commented by udara 08-08-2017 for test
		 
			
         this.m_finance_no = this.rs.getString(7);
         this.mm_vehicle_no = this.rs.getString(4);
         this.m_type = "rental_reminder";
         this.m_doc_ref_no = this.rs.getString(2);
 
         //this.m_message = ("Ayubowan! Obage " + this.rs.getString(4) + " wahanaya sandaha masika warikaya " + this.rs.getString(5) + " dinata Rs." + this.rs.getString(6) + " gewiya yuthu bawa karunikawa danwa sitimu. Suba dawasak! Lakderana");
 		 this.m_message = ("Obage " + this.rs.getString(4) + " wahanaya sandaha masika warikaya " + this.rs.getString(5) + " dinata Rs." + this.rs.getString(6) + " gewiya yuthu bawa danwa sitimu. Oba geweem kara athnam meya nosalaka harinna"); // added by udara 27-11-2018
			
         str7 = this.m_message;
 
         localPattern = Pattern.compile(str8);
 
         localMatcher = localPattern.matcher(str7);
         str7 = localMatcher.replaceAll(str9);
 
         this.m_phone_num = this.rs.getString(3);
 
         this.m_msg = localLAKDL_SasianetSms.SendMessage(str2, i, str3, str4, str5, str6, this.m_phone_num, str7);
         String str10 = this.m_msg.substring(0, this.m_msg.indexOf(":"));
         String str11 = this.m_msg.substring(this.m_msg.lastIndexOf(":"), this.m_msg.length());
			
		 
			
		 // added below by udara 08-08-2017 for test
		 //String str10 = "";
		 //String str11 = "";
		 
 
         System.out.println("BEFORE SAVE SMS LOG - DUE RENTAL REMINDER");
 
         //this.callstmt = conn.prepareCall("BEGIN " + this.m_schema_name + ".SAVE_SMS_LOG_RENTAL_REMINDER(:1,:2,:3,:4,:5,:6,:7,:8,:9); END;");
         this.callstmt.setString(1, this.m_finance_no);
         this.callstmt.setString(2, this.mm_vehicle_no);
         this.callstmt.setString(3, str10);
         this.callstmt.setString(4, str11);
         this.callstmt.setString(5, null);
         this.callstmt.setString(6, str1);
         this.callstmt.setString(7, str7);
         this.callstmt.setString(8, this.m_type);
         this.callstmt.setString(9, this.m_doc_ref_no);
         this.callstmt.execute();
         //conn.commit();
 
         System.out.println("AFTER SAVE SMS LOG - DUE RENTAL REMINDER");
 
         Thread.sleep(50);
       }
		
		conn.commit();
		this.callstmt.close(); // commented by udara 17-05-2017	
 
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

