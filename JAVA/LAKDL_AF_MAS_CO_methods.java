//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Common Methods for SQL
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import oracle.jdbc.driver.*;
import java.sql.*;
import sun.misc.BASE64Decoder;

public Connection conn;
public ResultSet rs;
public Statement stmt;


public class LAKDL_AF_MAS_CO_methods  
 {
	//java.text.NumberFormat nf;
	
	LAKDL_AF_MAS_CO_methods()
	{
	conn = m_sn_methods.met_user_validate(req); 
	}
	    
			stmt=conn.createStatement();
	
 public String getCustomerStatus(String client) {
	 

 return 
   	
 }
	
 public String getCustomerType(String Schema,String Active,String EntityCode) {
		
		
		return " SELECT ENTITY_CODE Entity,DESCRIPTION \"Entity Name\" "+
													 " FROM "+Schema+".AF_CO_MAS_LEGAL_ENTITY "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        ENTITY_CODE LIKE '%"+EntityCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
 }

 public String getInitiationType(String Schema,String Active,String InitiationCode) {
		
		return " SELECT INITIATION_CODE Initiation,DESCRIPTION \"Initiation Name\" "+
                           " FROM   "+Schema+".AF_MK_MAS_INITIATION_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        INITIATION_CODE LIKE '%"+InitiationCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
	 	 
 }

 public String getLeadSourceCat(String Schema,String Active,String SourceCode) {
		
		return " SELECT SOURCE_CODE Source,NAME \"Source Name\" "+
                           " FROM   "+Schema+".AF_MK_MAS_LEAD_SOURCE_CAT "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        SOURCE_CODE LIKE '%"+SourceCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }

 public String getTransactionType(String Schema,String Active,String TranCode,String Division) {
		
		return " SELECT TRAN_CODE Transaction,DESCRIPTION \"Transaction Name\"  "+
                           " FROM   "+Schema+".C_CO_MAS_TRANSACTION_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        TRAN_CODE LIKE '%"+TranCode+"%' AND DIVISION_CODE='"+Division+"' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
	  	 
 }
	
 public String getTransactionSubType(String Schema,String Active,String TranCode,String SubTranCode) {
		
		return " SELECT TRN_SUB_TYPE \"Sub Code\",DESCRIPTION \"Sub Name\"  "+
                           " FROM   "+Schema+".C_CO_MAS_TRANSACTION_SUB_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        TRN_SUB_TYPE LIKE '%"+SubTranCode+"%' AND TRN_CODE='"+TranCode+"' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			 
 }
	
  public String getItemCat(String Schema,String Active,String CatCode) {
		
		return " SELECT ITEM_CAT_CODE \"Item Category\",DESCRIPTION \"Category Name\" "+
                           " FROM   "+Schema+".AF_CO_MAS_ITEM_CATEGORY "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        ITEM_CAT_CODE LIKE '%"+CatCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
														
 }
	
 public String getItemSubCat(String Schema,String Active,String CatCode,String SubCatCode) {
		
		return " SELECT ITEM_SUB_CAT \"Sub Category\",DESCRIPTION \"Sub Category Name\"  "+
                           " FROM   "+Schema+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        ITEM_SUB_CAT LIKE '%"+SubCatCode+"%' AND ITEM_CAT_CODE='"+CatCode+"' "+
													 " ORDER BY	DEFAULT_VALUE DESC";


 }
	
public String getInterestType(String Schema,String Active,String InterestType) {
		
		return " SELECT CODE \"Interest\",DESCRIPTION \"Interest Name\" "+
                           " FROM   "+Schema+".AF_CO_MAS_INTEREST_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        CODE LIKE '%"+InterestType+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }
	
 public String getConditionOfAsset(String Schema,String Active,String CatCode,String SubCatCode) {
		
		return " SELECT CODE \"Condition\",DESCRIPTION \"Condition Desc\" "+
                           " FROM   "+Schema+".AF_CO_MAS_CONDITION_OF_ASSET "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        CODE LIKE '%"+SubCatCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }
	
 public String getMake(String Schema,String Active,String MakeCode,String SubCatCode) {
		
		return " SELECT MAKE_CODE \"Make\",MAKE_DESC \"Make Name\" "+
                           " FROM   "+Schema+".AF_CO_MAS_MAKE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        MAKE_CODE LIKE '%"+MakeCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }
	
 public String getFuelType(String Schema,String Active,String CatCode,String SubCatCode) {
		
		return " SELECT CODE \"Fuel Type\",DESCRIPTION \"Fuel Desc\" "+
                           " FROM   "+Schema+".AF_CO_MAS_FUEL_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
													 "        CODE LIKE '%"+SubCatCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }
	
 public String getTransaction(String Schema,String Active,String CatCode) {
		
		return " SELECT TRAN_CODE \"Transaction Code\", DESCRIPTION \"Description\""+
           " FROM   "+Schema+".AF_CO_MAS_TRANSACTION_TYPE "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        TRAN_CODE LIKE '%"+CatCode+"%' "+
													 " ORDER BY	DEFAULT_VALUE DESC";
			
 }	
	
 public String getSubTransaction(String Schema,String Active,String ProductCode,String SubProCode) {
		
		return " SELECT TRN_SUB_TYPE,DESCRIPTION "+
           " FROM   "+Schema+".AF_CO_MAS_TRANSACTION_SUB_TYPE "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        CODE LIKE '%"+SubProCode+"%' AND TRN_CODE=UPPER('"+ProductCode+"') "+
													 " ORDER BY	DEFAULT_VALUE DESC";

 }	
	
 public String getCity(String Schema,String Active,String City) {
		
		return " SELECT CITY_CODE, CITY_DESC "+
           " FROM   "+Schema+".AF_CO_MAS_CITY "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        CITY_CODE LIKE '%"+City+"%' "+
					 " ORDER BY	DEFAULT_VALUE DESC";

 }		
	
 public String getTrnSubType(String Schema,String Active,String TrnType,String TrnSubType) {
		
		return " SELECT A.TRN_SUB_TYPE,  A.DESCRIPTION "+
					 " FROM   "+Schema+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        A.TRN_CODE LIKE '%"+TrnType+"%' AND TRN_SUB_TYPE LIKE '%"+TrnSubType+"%' "+
					 " ORDER BY	DEFAULT_VALUE DESC,DESCRIPTION ";
 }	
	
 public String getAssetUsageType(String Schema,String Active,String AssetUsage) {
		
		return " SELECT A.USAGE_TYPE,  A.DESCRIPTION "+
					 " FROM   "+Schema+".AF_CO_MAS_ASSET_USAGE_TYPE A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        A.USAGE_TYPE LIKE '%"+AssetUsage+"%'  "+
					 " ORDER BY	DEFAULT_VALUE DESC";
 }	
	
 public String getEngineCapacity(String Schema,String Active,String EngineCap) {
		
		return " SELECT A.CAPACITY_CODE,A.DESCRIPTION "+
					 " FROM   "+Schema+".AF_CO_MAS_ENGINE_CAPACITY A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        A.CAPACITY_CODE LIKE '%"+EngineCap+"%' "+
					 " ORDER BY	DEFAULT_VALUE DESC";
 }	
	
 public String getRepaymentInt(String Schema,String Active,String IntType) {
		
		return " SELECT A.DURATION,  A.DESCRIPTION, A.DURATION_TYPE "+
					 " FROM   "+Schema+".AF_CO_MAS_REPAYMENT_INTERVAL A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        DURATION LIKE '%"+IntType+"%' ";
 }	
	
 public String getRepaymentMeth(String Schema,String Active,String RePayMeth) {
		
		return " SELECT A.REPAYMENT_TYPE,  A.DESCRIPTION "+
					 " FROM   "+Schema+".AF_CO_MAS_REPAYMENT_METHOD A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        REPAYMENT_TYPE LIKE '%"+RePayMeth+"%' "+
					 " ORDER BY	DEFAULT_VALUE DESC";
 }	

 public String getCharges(String Schema,String Active,String ChargType) {
		
		return " SELECT A.TYPE_CODE,  A.DESCRIPTION "+
					 " FROM   "+Schema+".AF_CO_MAS_CHARGES A "+
					 " WHERE  ACTIVE_STATUS ='"+Active+"' AND "+
					 "        TYPE_CODE LIKE '%"+ChargType+"%' "+
					 " ORDER BY	DEFAULT_VALUE DESC";
 }	

 public String getChargesApp(String Schema,String Active,String SubChargType,String SubItemCat,String Fual) {
		
		return " SELECT B.DESCRIPTION, A.SUB_TYPE_CODE,NVL(A.AMOUNT,'0'),NVL(A.PERCENTAGE*100,'0'),   "+
		       "        A.ITEM_SUB_CAT,A.FUAL_TYPE_CODE,A.FROM_DATE,A.TO_DATE,A.ACTIVE_STATUS "+
 					 " FROM  "+Schema+".AF_CO_MAS_CHARGES_APPLICABLE A,"+Schema+".AF_CO_MAS_SUB_CHARGES B "+
					 " WHERE A.SUB_TYPE_CODE=B.SUB_TYPE_CODE AND A.ITEM_SUB_CAT LIKE '"+SubItemCat+"%' AND "+
					 "       A.SUB_TYPE_CODE LIKE '"+SubChargType+"%' AND A.SUB_TYPE_CODE LIKE '"+Fual+"%' AND "+
					 " FROM_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') AND "+
					 " TO_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') ";
 }	
 public String getMaintenancApp(String Schema,String Active,String SubChargType,String SubItemCat,String Fual,String Tyep) {
		
		return " SELECT B.DESCRIPTION, A.SUB_TYPE_CODE,NVL(A.AMOUNT,'0'),NVL(A.PERCENTAGE*100,'0'),   "+
		       "        A.ITEM_SUB_CAT,A.FUAL_TYPE_CODE,A.FROM_DATE,A.TO_DATE,A.ACTIVE_STATUS "+
 					 " FROM  "+Schema+".AF_CO_MAS_CHARGES_APPLICABLE A,"+Schema+".AF_CO_MAS_SUB_CHARGES B "+
					 " WHERE A.SUB_TYPE_CODE=B.SUB_TYPE_CODE AND A.ITEM_SUB_CAT LIKE '"+SubItemCat+"%' AND "+
					 "       A.SUB_TYPE_CODE LIKE '"+SubChargType+"%' AND A.SUB_TYPE_CODE LIKE '"+Fual+"%' AND "+
					 " FROM_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') AND "+
					 " TO_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') AND TYPE_CODE='"+Tyep+"'";
 }		
	/*
	SELECT A.ITEM_SUB_CAT, A.SUB_TYPE_CODE, A.FUAL_TYPE_CODE, A.FROM_DATE,
       A.TO_DATE, A.AMOUNT, A.ACTIVE_STATUS
  FROM L_CO_MAS_CHARGERS_APPLICABLE A
	
public ResultSet getInitiationType(Connection conn,String Schema,String Active,String TranCode) {
		
		ResultSet rs=null;
		Statement stmt = conn.createStatement ();
		
		rs = stmt.executeQuery(" SELECT INITIATION_CODE,DESCRIPTION  "+
                           " FROM   "+Schema+".L_CO_MAS_INTEREST_TYPE "+
													 " WHERE  ACTIVE_STATUS ='"+Active+"' AND CAT_TYPE_CODE LIKE '%"+CatType+"%' ");
		return rs;

 }	

//public Connection met_user_validate(HttpServletRequest in_req, HttpServletResponse res) {
	public Connection met_user_validate(HttpServletRequest in_req) {
		
		try {

			String rights="norights";
			int count=0;
  		username="Undefined";

			rights=in_req.getHeader("authorization");
			String bufferString = in_req.getHeader("authorization").substring(6,in_req.getHeader("authorization").length());
			byte 	 mydata[];
			BASE64Decoder base64 = new BASE64Decoder();
			mydata = base64.decodeBuffer(bufferString);
			String mydatastr = new String(mydata);
			int colpos = mydatastr.indexOf(":");
			username = new String(mydata).substring(0,colpos).toUpperCase();
			int colpos1 = mydatastr.length();
			password = new String(mydata).substring(colpos+1,colpos1).toUpperCase(); 
			
			if (username=="") {
				username="Undefined";
			}
		  Class.forName("oracle.jdbc.driver.OracleDriver");
			out_conn =
				  DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G",username,password);
	     // DriverManager.setLoginTimeout(int seconds)
		}	catch (Exception e) {
				try {
					/*ServletOutputStream out = res.getOutputStream();
					ByteArrayOutputStream ostr = new ByteArrayOutputStream();
					e.printStackTrace(new PrintStream(ostr));
					out.println(ostr.toString());
		      out.close();	*    /	
				}
				catch (Exception e1) {
				}
			}
		
		    return out_conn;
		
 }*/
	
 
}




