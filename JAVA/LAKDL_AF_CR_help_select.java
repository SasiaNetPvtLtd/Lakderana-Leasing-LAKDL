//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_CR_help_select  {  
 
  //Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
  LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	 
	public String ClientSql           = " ";
	public String ClientSql_Header    = "Client Help "; 
	
	public String LeaseSql            = " ";
	public String LeaseSql_Header     = "Finace Help "; 
	
	public String FinanceSql          = " ";
	public String FinanceSql_Header   = "Finance Help "; 
	
	public String FinanceSql_finance_act          = " ";
	public String FinanceSql_finance_act_Header   = "Finance Help "; 
	
	
	public String InquirySql          = " ";
	public String InquirySql_Header   = "Inquiry Help "; 
	public String MKOfficerSql        = " ";
	public String MKOfficerSql_Header = "Officer Help "; 
	public String MKSuperSql        = " ";
	public String MKSuperSql_Header = "Supervisor Help "; 
	public String MKTeamSql        = " ";
	public String MKTeamSql_Header = "Team Help "; 
	public String TrnSubSql        = " ";
	public String TrnSubSql_Header = "Help "; 
	public String ModelSql        = " ";
	public String ModelSql_Header = "Help "; 
	public String VendorSql        = " ";
	public String VendorSql_Header = "Help "; 
	public String PriceSql         = " ";
	public String PriceSql_Header  = "Help "; 
	public String SubModelSql         = " ";
	public String SubModelSql_Header  = "Help "; 
	public String IntBaseSql         = " ";
	public String IntBaseSql_Header  = "Help "; 
	
	public String m_help_TXT_DISTRICT_CODE_sql        = "District Help ";
	public String m_help_TXT_DISTRICT_CODE_sql_Header = "Help "; 
	public String m_help_TXT_APPLICATION_NO        = " ";
	
  public String m_help_TXT_APPLICATION_NO_Header = "Marketing - Application Number Help";
	
	
  public String m_help_TXT_CLIENT_CODE        = " ";
  public String m_help_TXT_CLIENT_CODE_Header = "Marketing Client Help";
	
	public String m_help_TXT_INQUARY_NO        ="";
	public String m_help_TXT_INQUARY_NO_Header ="Inquiry Help ";
	
	public String m_help_TXT_MAKE        =" ";
	public String m_help_TXT_MAKE_Header ="Make Help";	
	
	public String m_help_TXT_INVOICE_PURCHASE        ="";
	public String m_help_TXT_INVOICE_PURCHASE_Header ="Invoice Help"; 
	
	public String m_help_TXT_PURCHASE_ORDER_NO         ="";
	public String m_help_TXT_PURCHASE_ORDER_NO_Header  ="Purchase Order Help";         
	
	public String m_help_TXT_MODEL_CODE_sql        =" ";
	public String m_help_TXT_MODEL_CODE_sql_Header ="Model Help";
	
	public String m_help_TXT_MODEL_CODE_inv_sql           ="";
	public String m_help_TXT_MODEL_CODE_inv_sql_Header    ="Model Help";
	
	public String PriceSql_invoice           ="";
	public String PriceSql_invoice_Header           ="Pricing Help";
	
	public String m_help_TXT_SUB_MODEL_sql        ="";
	public String m_help_TXT_SUB_MODEL_sql_Header ="Sub Model Help";
	
	public String m_help_TXT_SUB_M_CODE_sql          ="";
	public String m_help_TXT_SUB_M_CODE_sql_Header   ="Sub Model Help";
	

	
	public String m_help_TXT_CITY_CODE_sql        ="";
	public String m_help_TXT_CITY_CODE_sql_Header ="City Help"; 
	
	public String 	m_help_TXT_APPLICATION_NO_2        ="";
  public String 	m_help_TXT_APPLICATION_NO_2_Header ="Application Help";
	
	public String m_help_TXT_SUPPLIER_sql           ="";
	public String m_help_TXT_SUPPLIER_sql_Header    ="Supplier Help";
	
	//public String m_help_TXT_INVOICE_sql           ="";
	//public String m_help_TXT_INVOICE_sql_Header    ="Invoice Help";
	
	public String m_help_SUPPLIER_PURCHASE_ORDER   ="";
	public String m_help_SUPPLIER_PURCHASE_ORDER_Header   ="Supplier Help";
	
	public String m_help_TXT_INVOICE_NO_sql        ="";
	public String m_help_TXT_INVOICE_NO_sql_Header ="Invoice Help";
	
	public String m_help_TXT_ASSET_ID_sql          ="";
	public String m_help_TXT_ASSET_ID_sql_Header   ="Asset ID Help";
	
	public String m_help_TXT_PRICING_NO_sql="";
  public String m_help_TXT_PRICING_NO_sql_Header="Pricing Details";

	public String m_help_TXT_QUOTATION_NO_sql="";
  public String m_help_TXT_QUOTATION_NO_sql_Header="Quation Details";

	
	public String m_help_TXT_CONDITION_OF_ASSET_sql="";
  public String m_help_TXT_CONDITION_OF_ASSET_sql_Header="Condition of Assets Details";

	public String m_help_TXT_MAKE_CODE_sql="";
  public String m_help_TXT_MAKE_CODE_sql_Header="Make Details";

	public String m_help_TXT_MODEL_CODE1_sql="";
  public String m_help_TXT_MODEL_CODE1_sql_Header="Model Details";



	public String m_help_TXT_INQUIRY_NO_sql="";
  public String m_help_TXT_INQUIRY_NO_sql_Header="Inquiry Details";


 // public String m_help_TXT_CLIENT_CODE2        = " ";
 // public String m_help_TXT_CLIENT_CODE2_Header = "Marketing Client Help";
	
	public String m_help_TXT_SCREEN_NAME_sql="";
	public String m_help_TXT_SCREEN_NAME_sql_Header="Screen Name Help";
	
	// Method for Sql Put Sqls Inside
	
	
		public String ClientSql_ODI_APP1 ="";//Added by Sandun on 30-06-2009
		public String ClientSql_ODI_APP1_Header ="Client Help";
		
		public String ClientSql_ODI_APP2 ="";//Added by Sandun on 30-06-2009
		public String ClientSql_ODI_APP2_Header ="Client Help";
		
		public String LeaseSql_ODI_APP1   = " "; //Added by Sandun on 30-06-2009
	  public String LeaseSql_ODI_APP1_Header     = "Finace Help "; 
		
		public String LeaseSql_ODI_APP2   = " ";//Added by Sandun on 30-06-2009
	  public String LeaseSql_ODI_APP2_Header     = "Finace Help "; 
	
	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4) {
	String Sql_Name  = (String) reqObj1;
	String Start_Val = (String) reqObj2;
	String End_Val   = (String) reqObj3;	
	String Criteria	 = (String) reqObj4;
		
	 int m_val =(Integer.parseInt(End_Val));
	 m_val++;
	 End_Val = Integer.toString(m_val);
		
	 Vector m_vector = new Vector();
	 String m_substring="";
	 int start_index, stop_index, cnt, i;
	 start_index = 0;
	 stop_index = 0;
	 cnt = 0;
	 int m_length = Criteria.lastIndexOf("@");
	
	 if (m_length!=0) {
      //m_debtorsql	= "inside if "  + m_length;
			while (stop_index<m_length) {
		 	 try {
				 stop_index = Criteria.trim().indexOf("@",start_index);
				 m_substring = Criteria.substring(start_index,stop_index);
				 if (m_substring.length()>0) {
			 		 m_vector.addElement(m_substring);
				 }
				 else {
					 m_vector.addElement("");
				 }
				 start_index = stop_index+1;
			 }
			 catch (Exception e) {
				 stop_index=m_length;
			 }
		 }
	 }
	 else if (m_length==0) {
		   //m_debtorsql	= "inside else if "  + m_length;
			 m_vector.addElement("");
	 }
		
	cnt = m_vector.size();
	 
	for ( i = cnt ; i < 8 ; i++ ) {
		m_vector.addElement("");
	}
		
								
		
	/*----------------------------------------------------------------
		Purpose  : select clients
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/			
		
	/*----------------------------------------------------------------
		Purpose  : select clients
	
		Used in  : CR TERMINATION 
	-----------------------------------------------------------------*/			

	ClientSql =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
												"FROM "+
														"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CLIENT_CODE)LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
														"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
														"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"                      	 WHERE  APPLICATION_STATUS = 'ACTIVATED') "+
												" ORDER BY FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		

/*----------------------------------------------------------------
		Purpose  : select Lease No
	
		Used in  : CR ODI Stop
	-----------------------------------------------------------------*/			

	LeaseSql = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, "+
 														"		      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT "+
										"FROM "+
												"(SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
												"FROM "+
														"( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
														"	 FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERM_TO','TERMI','REPOSSESS') AND "+ // added REPOSSESS by udara 27-01-2014
                            "         CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND "+
                            "         (FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
                            "          APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
											
	
 /*----------------------------------------------------------------
		Purpose  : select Finance Number
	
		Used in  : Finance Activation
	-----------------------------------------------------------------*/			
  FinanceSql =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT distinct FINANCE_NO,A.APPLICATION_NO, A.CLIENT_CODE, "+
												"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,A.CURRENCY_CODE "+
												" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+
												"        "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B "+
												" WHERE  A.APPLICATION_NO=B.APPLICATION_NO AND "+  //MODIFIEDCBY NUWAN DE SILVA 09-JUL-07
												" (      A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"        AND APPLICATION_STATUS='VERIFYL' "+ //VERIFY2
										    " ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
										    " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
/*
  FinanceSql_finance_act =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT distinct FINANCE_NO,A.APPLICATION_NO, A.CLIENT_CODE, "+
												"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,A.CURRENCY_CODE "+
												" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+
												"        "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER B "+
												" WHERE  A.APPLICATION_NO=B.APPLICATION_NO "+
												" AND B.ACTIVE_STATUS='VERIFY' "+
												"AND "+  //MODIFIEDCBY NUWAN DE SILVA 09-JUL-07
												" (      A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"        AND APPLICATION_STATUS='VERIFYL' "+ //VERIFY2
										    " ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
										    " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
*/

											           
/*----------------------------------------------------------------
		Purpose  : select Inquiry Number
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/			
  InquirySql = "SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO,ADDRESS, "+//6
							      "ADDRESS2, CITY_CODE, LEGAL_ENTITY,LEAD_SOURCE_NAME,ID_NO, "+//11
							      "MK_OFFICER,INITIATION_TYPE, CLIENT_CATEGORY, "+//14
							      "LEAD_SOURCE_CATEGORY,INTRODUCER,"+//16
							      "EMAIL, TEAM,FAX_NO,"+//19
							      "MK_SUPERVISOR, CONTACT_PERSON,"+//21
							      "SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE,STATUS,INQUIRY_STATUS "+//25
										"FROM "+
												"(SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO, FAX_NO,"+
												"	       ADDRESS, CITY_CODE, LEGAL_ENTITY, STATUS,"+
												"	       INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,"+
												"	       LEAD_SOURCE_NAME, INTRODUCER, ID_NO, INQUIRY_STATUS,"+
												"        ENT_USER,ADDRESS2, EMAIL, TEAM,"+
												"        MK_OFFICER, MK_SUPERVISOR, CONTACT_PERSON,"+
												"	       SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE "+
												"FROM "+
														"(SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO, "+
														" NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS, "+
														" NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS, "+
														" NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY,"+
														" NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME, "+
														" NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS,"+
														" NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM, "+
														" NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON, "+
														" NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE "+
														" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
														" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"       UPPER(CLIENT_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"       TEL_NO 			        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	      UPPER(EMAIL)        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"       UPPER(ID_NO) 			  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														
												" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
										
										
									
										
				
	
		
/*----------------------------------------------------------------
		Purpose  : Select District
	
		Used in  : MK District
	-----------------------------------------------------------------*/			
	
	
	m_help_TXT_DISTRICT_CODE_sql=
			
			" SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " DISTRICT_CODE,"+
      " DISTRICT_DESC, "+
			" PROVINCE_CODE, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
	  	" WHERE DISTRICT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	/*----------------------------------------------------------------
		Purpose  : Select  Asset
	
		Used in  : MK Asset
	-----------------------------------------------------------------*/			
			
			
		m_help_TXT_ASSET_ID_sql=
		
		" SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO"+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO"+
		" FROM( "+ 
		" SELECT "+
    " ASSET_ID, "+
    " REG_NO, "+
    " REG_DATE, "+
    " SUB_MODEL_CODE, "+
    " PRICING_NO, "+
    " STATUS, "+
    " SUPPLIER_CODE, "+
    " QTY, "+
    " COST, "+
    " PURPOSE, "+
    " ADDRESS, "+
    " CITY_CODE, "+
    " PERIOD, "+
    " APPLICATION_NO "+
    " FROM "+m_schema_name+".AF_CO_PRO_ASSET_DETAILS "+
		" WHERE ASSET_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		/*----------------------------------------------------------------
		Purpose  : Select  Model Code
	
		Used in  : MK Model
	-----------------------------------------------------------------*/			
		
		
		m_help_TXT_MODEL_CODE_inv_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " MODEL_CODE,"+
      " DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
	  	" WHERE MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		/*----------------------------------------------------------------
		Purpose  : Select  Sub Model Code
	
		Used in  : MK Model
	-----------------------------------------------------------------*/			
		
			
			m_help_TXT_SUB_M_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE"+
			" FROM( "+
			   " SELECT "+
         " SUB_CODE,"+ 
         " MODEL_CODE,"+
         " DESCRIPTION,"+
         " ENGINE_CAPACITY,"+
         " OPTION_TYPE,"+
         " COUNTRY_CODE,"+
         " YEAR_OF_MANUFACTURE,"+
         " DEFAULT_VALUE"+
         " FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
				 " WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------ ID        : Performa Invoice Process ----------------------------------
			--------------------Purpose    : Invoice no Help ----------------------------------------------
		  ------------------- Added By   : Nuwan De Silva------------------------------------------------------
		  -------------------- Date      : 07-08-2006---------------------------------------------------------*/
			
		
		m_help_TXT_INVOICE_NO_sql=
		
		" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE"+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE"+
		" FROM( "+ 
		" SELECT "+
    " INVOICE_NO, "+
    " APPLICATION_NO, "+
    " ASSET_ID, "+
    " ENGINE_NO, "+
    " CHASSIS_NO, "+
    " REG_NO, "+
    " REG_DATE, "+
    " PRICING_NO, "+
    " SUB_MODEL_CODE, "+
    " COLOUR, "+
    " SEATING_CAPACITY, "+
    " NET_PRICE, "+
    " VAT, "+
    " TOTAL_AMOUNT, "+
    " TO_BE_DELIVERD_TO, "+
    " VALUE, "+
    " CURR_CODE, "+
    " MODEL_CODE, "+
		" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND APPLICATION_NO =UPPER('"+m_vector.elementAt(1)+"') " + // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
			
			
			
										
	/*----------------------------------------------------------------
		Purpose  : Select Application Number
	
		Used in  : MK Application
	-----------------------------------------------------------------*/												
										
		
		m_help_TXT_APPLICATION_NO=
		
		
		" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO "+
		" FROM( "+ 
    " SELECT "+
    " APPLICATION_NO, "+
		" FACILITY_NO, "+
    " CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, "+
    " CO_APPLICANT,"+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+
  //  " DISTRICT_CODE, "+
		" INQUARY_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		
		" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
		" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') "+
		" OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";		
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Inqury
	
		Used in  : MK Inqury
	-----------------------------------------------------------------*/							
		
		 m_help_TXT_INQUARY_NO=
				"SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,"+
												"	       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,"+
												"	       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,"+
												"	       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,"+
												"        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,"+
												"        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,"+
												"	       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE "+
			" FROM  "+
						
			              	"(SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,"+
												"	       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,"+
												"	       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,"+
												"	       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,"+
												"        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,"+
												"        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,"+
												"	       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE "+
			
			
			
			 " FROM "+ 
		 												" (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO, "+
														" NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS, "+
														" NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS, "+
														" NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY,"+
														" NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME, "+
														" NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS,"+
														" NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM, "+
														" NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON, "+
														" NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE "+
														" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
				                    
														" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"       UPPER(CLIENT_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"       TEL_NO 			        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	      UPPER(EMAIL)        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"       UPPER(ID_NO) 			  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+				
														" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L "+		
														"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";
		
		/*----------------------------------------------------------------
		Purpose  : Select Client 
	
		Used in  : MK Client
	-----------------------------------------------------------------*/							
					
			m_help_TXT_CLIENT_CODE=
      		    	
	 	" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic "+
										"FROM "+
												"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO "+
												"FROM "+
														"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"  WHERE "+m_schema_name+".AF_CO_VAL_CLIENT(CLIENT_CODE,'"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')='NO' AND "+
													//	"	 WHERE (CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"       ( FULL_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS=('"+m_vector.elementAt(3)+"')  "+
														" ORDER BY FULL_NAME )P) L "+		
										"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
										
										
	
	m_help_TXT_MAKE=
				
		  " SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC "+
			" FROM( "+ 
		  " SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC "+
      " FROM "+m_schema_name+".AF_CO_MAS_MAKE A,"+m_schema_name+".AF_CO_MAS_MODEL B "+
      " WHERE A.MAKE_CODE=B.MAKE_CODE AND A.MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND B.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
	
	m_help_TXT_MODEL_CODE_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION "+
			" FROM( "+ 
		  " SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION "+
      " FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
      " WHERE A.MODEL_CODE=B.MODEL_CODE AND A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND B.SUB_CODE LIKE  UPPER('%"+m_vector.elementAt(2)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			m_help_TXT_SUB_MODEL_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE"+
			" FROM( "+
			   " SELECT "+
         " DISTINCT SUB_CODE,"+ 
         " MODEL_CODE,"+
         " DESCRIPTION,"+
         " ENGINE_CAPACITY,"+
         " OPTION_TYPE,"+
         " COUNTRY_CODE,"+
         " YEAR_OF_MANUFACTURE,"+
         " DEFAULT_VALUE"+
         " FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
				 " WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			 " ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		
		/*----------------------------------------------------------------
		Purpose  : Select invoice Number
	
		Used in  : Purchase Order
	-----------------------------------------------------------------*/							
	
	m_help_TXT_INVOICE_PURCHASE=
	
	 " SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT,L.GROSS_AMOUNT "+
	 " FROM  "+
	 " (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT,P.GROSS_AMOUNT "+
	 " FROM( "+
	 " SELECT "+
   " A.INVOICE_NO, "+
   " A.ASSET_ID, "+
   " A.ENGINE_NO, "+
   " A.CHASSIS_NO, "+
   " A.NET_PRICE-ROUND(DECODE("+m_schema_name+".AF_CO_GET_APP_TERMI_TYPE(a.APPLICATION_NO),'ENHA_DOWN',"+m_schema_name+".af_co_get_terminated_amount(a.APPLICATION_NO)),0) NET_PRICE, "+
   " A.VAT, "+
   " (A.NET_PRICE +A.VAT-ROUND(DECODE("+m_schema_name+".AF_CO_GET_APP_TERMI_TYPE(a.APPLICATION_NO),'ENHA_DOWN',"+m_schema_name+".af_co_get_terminated_amount(a.APPLICATION_NO)),0)) GROSS_AMOUNT "+
	
   " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
   " WHERE A.APPLICATION_NO IN "+
   " ( SELECT "+
   " APPLICATION_NO "+
   " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
   " WHERE  SUPPLIER LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%')) AND A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') "+
   "  )P)L  "+
	 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
			
		/*----------------------------------------------------------------
		Purpose  : Select Purchase Order Number
	
		Used in  : Purchase Order
	-----------------------------------------------------------------*/						
			
	m_help_TXT_PURCHASE_ORDER_NO=
	
    " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE "+
		" FROM( "+
		" SELECT "+
    " PURCHASE_ORDER_NO, "+
    " APPLICATION_NO, "+
    " VENDER_CODE "+
    " FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
		" WHERE  PURCHASE_ORDER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND VENDER_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
    "  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

	
	
			
			
			
			
			
			
			
			PriceSql_invoice="SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,  "+
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE,  SUPPLIER, "+
			   "     BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET  "+
			   " FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,  "+
				 "  	TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE, SUPPLIER, "+
			   "     BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET "+
			   " FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE,  SUPPLIER, "+
			   "    BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET "+
			   " FROM "+m_schema_name+".AF_MK_PRO_PRICING A "+
			   " WHERE PRICING_NO LIKE '%"+m_vector.elementAt(0)+"%' AND PRICING_STATUS=('"+m_vector.elementAt(1)+"') "+
			   " ORDER BY ENT_DATE DESC)) P "+
			   " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			
			
			
			
		m_help_TXT_CITY_CODE_sql=
		" SELECT L.NO ,L.CITY_CODE,L.CITY_DESC,L.DISTRICT_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC,P.DISTRICT_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " CITY_CODE, "+
      " CITY_DESC, "+
			" DISTRICT_CODE, "+
      " DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_CITY "+
		  " WHERE CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	
	m_help_TXT_APPLICATION_NO_2=
		
    " SELECT L.NO ,L.APPLICATION_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.APPLICATION_NO "+
		" FROM( "+ 
		" SELECT "+
    //" DISTINCT APPLICATION_NO "+ // commented by udara 18-03-2019
	"  		APPLICATION_NO "+ // added by udara 18-03-2019
		" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
    " WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
			
		/*----------------------------------------------------------------
		Purpose  : Select Suppliers
	
		Used in  : purchase order
	-----------------------------------------------------------------*/					
		
		
		m_help_SUPPLIER_PURCHASE_ORDER=
		
		" SELECT L.NO ,L.VENDOR_CODE,L.NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME "+
		" FROM( "+ 
		" SELECT "+
    " VENDOR_CODE,NAME "+
    " FROM "+m_schema_name+".AF_CO_MAS_VENDORS A "+
    " WHERE A.VENDOR_CODE IN  "+
    " (SELECT "+
    " B.SUPPLIER "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING B ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
    " WHERE B.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
    " AND A.VENDOR_CODE LIKE ('%"+m_vector.elementAt(1)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
		
		m_help_TXT_SUPPLIER_sql=
		
		" SELECT L.NO ,L.VENDOR_CODE,L.NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME "+
		" FROM( "+ 
		" SELECT "+
    " VENDOR_CODE, "+
    " NAME "+
    " FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
    " WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
	/* m_help_TXT_INVOICE_sql=
	
	 " SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.TOTAL_AMOUNT,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT "+
	 " FROM  "+
	 " (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.TOTAL_AMOUNT,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT "+
	 " FROM( "+ 
	 " SELECT "+
   " INVOICE_NO, "+
   " ASSET_ID, "+
	 " TOTAL_AMOUNT, "+	
   " ENGINE_NO, "+
   " CHASSIS_NO, "+
   " NET_PRICE, "+
   " VAT "+
   " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
   " WHERE PRICING_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
   "  )P)L  "+
	 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		
		
		
		
		
		
			/*----------------------------------------------------------------
		Purpose  : Select Screen Name 
	
		Used in  : MK Follow Up
	-----------------------------------------------------------------*/
		
		
		m_help_TXT_SCREEN_NAME_sql=		
					"SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME "+
					" FROM (SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME "+
					" FROM (SELECT SCREEN_NAME,DISPLAY_NAME "+
          " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
					"  WHERE SCREEN_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
					"  ORDER BY SCREEN_NAME )P) L	"+
					" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";									
														
	
		/*" SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME "+
				  "FROM "+
												"(SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME "+
	                   "FROM "+
														"(SELECT SCREEN_NAME,DISPLAY_NAME "+
														"	 FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
														"	 WHERE (SCREEN_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														" ORDER BY SCREEN_NAME )P) L "+	
														"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";*/
		
		/*m_help_TXT_CLIENT_CODE2=
      		    	
	 	" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic "+
										"FROM "+
												"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO "+
												"FROM "+
														"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE (FULL_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        ADDRESS1 LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
														"        TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND "+
														"        CLIENT_CODE NOT IN('"+m_vector.elementAt(1)+"')  "+
												    " ORDER BY FULL_NAME )P) L "+		
										"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		*/
			
		
		
		
										
										
	
	
/*----------------------------------------------------------------
		Purpose  : select Marketing Officer
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/			
	MKOfficerSql =" SELECT NO,USER_ID,NAME "+
               " FROM  ( SELECT ROWNUM NO,USER_ID,NAME "+
               " FROM  ( SELECT USER_ID,NAME "+
               " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
							 " WHERE  DIVISION_CODE='"+m_vector.elementAt(1)+"' AND "+
							 "	      (USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
  						 "         UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
				 			 " ORDER BY NAME)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
/*----------------------------------------------------------------
		Purpose  : select Marketing Supervisor
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/
	MKSuperSql = " SELECT NO,USER_ID,NAME "+
               " FROM  ( SELECT ROWNUM NO,USER_ID,NAME "+
               " FROM  ( SELECT USER_ID,NAME "+
               " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
							 " WHERE  DIVISION_CODE='"+m_vector.elementAt(1)+"' AND "+
							 "	      (USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
  						 "         UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
				 			 " ORDER BY NAME)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
/*----------------------------------------------------------------
		Purpose  : select Marketing Team		
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/	
	/*MKTeamSql =  " SELECT NO,TEAM_ID, TEAM_DESC,USER_ID, NAME "+
               " FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC,USER_ID, NAME "+
               " FROM  ( SELECT A.TEAM_ID, A.USER_ID,D.NAME,TEAM_DESC "+
							 " FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_TEAMS B, "+
							 " "+m_schema_name+".CO_CO_MAS_USER D "+
							 " WHERE A.TEAM_ID=B.TEAM_ID AND "+//"+
							 " D.USER_ID  =A.USER_ID AND "+
							 " A.ACTIVE_STATUS='Y' AND A.USER_ID='"+m_vector.elementAt(1)+"' AND "+
	 						 " A.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
				 			 " ORDER BY TEAM_DESC)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";*/
	MKTeamSql =  " SELECT NO,TEAM_ID, TEAM_DESC "+
               " FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC "+
               " FROM  ( SELECT B.TEAM_ID,TEAM_DESC "+
							 " FROM "+m_schema_name+".AF_CO_MAS_TEAMS B "+
							 " WHERE B.ACTIVE_STATUS='Y' AND "+
	 						 " (B.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
							 "  TEAM_DESC	LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
				 			 " ORDER BY TEAM_DESC)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";							
	
/*----------------------------------------------------------------
		Purpose  : select Transaction Sub Code
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/	
	TrnSubSql =  " SELECT NO,TRN_SUB_TYPE,DESCRIPTION,RATE "+
               " FROM  ( SELECT ROWNUM NO, TRN_SUB_TYPE, DESCRIPTION,RATE "+
               " FROM  ( SELECT A.TRN_SUB_TYPE,  A.DESCRIPTION,A.RATE "+
							 " FROM   "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
							 " WHERE  ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
							 "        A.TRN_CODE=UPPER('"+m_vector.elementAt(1)+"') AND "+
							 "        TRN_SUB_TYPE LIKE UPPER('%"+m_vector.elementAt(0)+"') "+
				 			 " ORDER BY TRN_SUB_TYPE)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
								
								
/*---------------------------------------------------------------

-----------------------------------------------------------------*/
ModelSql =  " SELECT NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
               " FROM  ( SELECT ROWNUM NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
               " FROM  ( SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE, "+
							 "                A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE "+
							 " FROM   "+m_schema_name+".AF_CO_MAS_MODEL A "+
							 " WHERE  ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
							 "        A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
							 "        A.MAKE_CODE LIKE  UPPER('%"+m_vector.elementAt(0)+"%') "+
				 			 " ORDER BY MODEL_CODE)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
									
/*---------------------------------------------------------------

-----------------------------------------------------------------*/
SubModelSql =  " SELECT NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
               " FROM  ( SELECT ROWNUM NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
               " FROM  ( SELECT B.SUB_CODE MODEL_CODE, B.DESCRIPTION,A.TAX_RATE, "+
							 "                A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE "+
							 " FROM   "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
							 " WHERE  B.ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
							 "        A.MODEL_CODE=B.MODEL_CODE AND  "+	
							 "        (B.SUB_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
							 "        UPPER(B.DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) AND  "+
				 			 "        A.MAKE_CODE LIKE  UPPER('%"+m_vector.elementAt(0)+"%') "+
				 			 " ORDER BY MODEL_CODE)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
/*---------------------------------------------------------------

-----------------------------------------------------------------*/

IntBaseSql = " SELECT NO,BASE_CODE, DESCRIPTION, RATE FROM( "+
 						 " SELECT ROWNUM NO,BASE_CODE, DESCRIPTION, RATE FROM( "+
 						 " SELECT A.BASE_CODE, A.DESCRIPTION, A.RATE "+
 						 " FROM   "+m_schema_name+".AF_CO_MAS_INTEREST_BASE A "+
						 " WHERE  ACTIVE_STATUS='Y' AND "+
						 "       (BASE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
						 "        DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%')) )) P "+
						 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";


/*---------------------------------------------------------------

-----------------------------------------------------------------*/
VendorSql =  " SELECT NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
               " FROM  ( SELECT ROWNUM NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
               " FROM  ( SELECT A.VENDOR_CODE, A.NAME, NVL(A.CATEGORY,'-') CATEGORY,NVL(A.TYPE,'-') TYPE "+
							 " FROM  "+m_schema_name+".AF_CO_MAS_VENDORS A "+
               " WHERE ACTIVE_STATUS='"+m_vector.elementAt(1)+"' AND "+
							 "       (VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
							 "       UPPER(NAME)  	 LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
							 " ORDER BY VENDOR_CODE)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		

/*---------------------------------------------------------------

-----------------------------------------------------------------*/

PriceSql="SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,  "+
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE,  SUPPLIER, "+
			   "     BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET  "+
			   " FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,  "+
				 "  	TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE, SUPPLIER, "+
			   "     BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET "+
			   " FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			   "    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			   "    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			   "    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			   "    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			   "    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			   "    MAINTENANCE_STATUS,  "+
			   "    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			   "    CURRENCY_CODE,  SUPPLIER, "+
			   "    BUY_BACK, "+
			   "    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			   "    CONDITION_OF_ASSET "+
			   " FROM "+m_schema_name+".AF_MK_PRO_PRICING A "+
			   " WHERE PRICING_NO LIKE '%"+m_vector.elementAt(0)+"%' "+
			   " ORDER BY ENT_DATE DESC)) P "+
			   " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//Indicative Quation
//------------------


m_help_TXT_PRICING_NO_sql=

 			" SELECT L.NO ,L.PRICING_NO,L.INQUIRY_NO,L.MAKE_CODE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.ITEM_CATEGORY,L.ITEM_SUB_CAT_CODE,L.TRANSACION_TYPE,L.TRN_SUB_TYPE,L.INTEREST_TYPE,L.VARIABLE_INT_BASE,L.INT_MARGIN,L.CONDITION_OF_ASSET,"+
    	" L.ASSET_USAGE_TYPE,L.VAT_PERCENTAGE,L.ENGINE_CAPACITY,"+
    	" L.FUEL_TYPE,L.TARE,L.MAINTENANCE_STATUS,L.BUY_BACK,PERIOD,L.PAYMENT_MODE,L.PAYMENT_INTERVAL,L.RATE,LTRIM(TO_CHAR(L.GROSS_AMOUNT,'999,999.99')) AS GROSS_AMOUNT,LTRIM(TO_CHAR(L.VAT_AMOUNT,'999,999.99')) AS VAT_AMOUNT,"+
    	" LTRIM(TO_CHAR(L.NET_AMOUNT,'999,999.99')) AS NET_AMOUNT,L.NIBSM,LTRIM(TO_CHAR(L.AMI,'999,999.99')),LTRIM(TO_CHAR(L.LAST_RENTAL,'999,999.99')) AS LAST_RENTAL,LTRIM(TO_CHAR(L.RESIDUAL_VALUE,'999,999.99')) AS RESIDUAL_VALUE,L.OUTFLOW_PATTERN,L.SUPPLIER_CREDIT,L.INFLOW_PATTERN,L.PRICING_STATUS,"+
    	" L.CURRENCY_CODE,L.SUPPLIER,LTRIM(TO_CHAR(L.TRAN_AMOUNT_CURRENCY,'999,999.99')) AS TRAN_AMOUNT_CURRENCY ,L.DESC5 AS CONDITION"+
			//L.DESC1 AS MAKE,L.DESC2 AS MODEL,L.DESC3 AS ITEM ,L.DESC4 AS SUBCAT,L.DESC5 AS CONDITION "+
						
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRICING_NO,P.INQUIRY_NO,P.MAKE_CODE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.ITEM_CATEGORY,P.ITEM_SUB_CAT_CODE,P.TRANSACION_TYPE,P.TRN_SUB_TYPE,P.INTEREST_TYPE,P.VARIABLE_INT_BASE,P.INT_MARGIN,P.CONDITION_OF_ASSET,"+
    	" P.ASSET_USAGE_TYPE,P.VAT_PERCENTAGE,P.ENGINE_CAPACITY,"+
    	" P.FUEL_TYPE,P.TARE,P.MAINTENANCE_STATUS,P.BUY_BACK,PERIOD,P.PAYMENT_MODE,P.PAYMENT_INTERVAL,P.RATE,P.GROSS_AMOUNT,P.VAT_AMOUNT,"+
    	" P.NET_AMOUNT,P.NIBSM,P.AMI,P.LAST_RENTAL,P.RESIDUAL_VALUE,P.OUTFLOW_PATTERN,P.SUPPLIER_CREDIT,P.INFLOW_PATTERN,P.PRICING_STATUS,"+
    	" P.CURRENCY_CODE,P.SUPPLIER,P.TRAN_AMOUNT_CURRENCY,P.DESC5"+
			//,P.DESC1,P.DESC2,P.DESC3,P.DESC4,P.DESC5 "+

			" FROM( "+ 
		  " SELECT "+
     	"	PRICING_NO, "+
    	"	INQUIRY_NO, "+
			"	A.MAKE_CODE, "+
			//" B.MAKE_DESC AS DESC1,"+
    	"	A.MODEL_CODE, "+
			//" C.DESCRIPTION AS DESC2,"+
    	"	SUB_MODEL_CODE, "+
			"	ITEM_CATEGORY, "+
			
			//" D.DESCRIPTION AS DESC3, "+
    	"	ITEM_SUB_CAT_CODE, "+
			//" E.DESCRIPTION AS DESC4,"+
    	"	TRANSACION_TYPE, "+
    	"	TRN_SUB_TYPE, "+
    	"	INTEREST_TYPE, "+
    	"	VARIABLE_INT_BASE, "+
    	"	INT_MARGIN, "+
    	"	CONDITION_OF_ASSET, "+
			"	F.DESCRIPTION AS DESC5,"+
    	"	ASSET_USAGE_TYPE, "+
    	"	VAT_PERCENTAGE, "+
    	"	ENGINE_CAPACITY, "+
    	"	A.FUEL_TYPE, "+
    	"	TARE, "+
   		"	MAINTENANCE_STATUS, "+
    	"	BUY_BACK, "+
    	"	PERIOD, "+
    	"	PAYMENT_MODE, "+
    	"	PAYMENT_INTERVAL, "+
   		"	RATE, "+
    	"	GROSS_AMOUNT, "+
    	"	VAT_AMOUNT, "+
    	"	NET_AMOUNT, "+
   		"	NIBSM, "+
    	"	AMI, "+
    	"	LAST_RENTAL, "+
    	"	RESIDUAL_VALUE, "+
    	"	OUTFLOW_PATTERN, "+
    	"	SUPPLIER_CREDIT, "+
    	"	INFLOW_PATTERN, "+
    	"	PRICING_STATUS, "+
   		" CURRENCY_CODE, "+
    	"	SUPPLIER, "+
    	"	TRAN_AMOUNT_CURRENCY  "+
      //" FROM LAKDL.AF_MK_PRO_PRICING "+
			" FROM LAKDL.AF_MK_PRO_PRICING A"+
			//,LAKDL.AF_CO_MAS_MAKE B,LAKDL.AF_CO_MAS_MODEL C,LAKDL.AF_CO_MAS_ITEM_CATEGORY D,LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY E"+
			"	,LAKDL.AF_CO_MAS_CONDITION_OF_ASSET F "+

	  	" WHERE PRICING_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			//" AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" AND L.SUB_MODEL_CODE,
			// " AND E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE
			//" AND B.MAKE_CODE=A.MAKE_CODE
      // "AND C.MODEL_CODE=A.MODEL_CODE
     	" AND A.CONDITION_OF_ASSET=F.CODE"+
      //" AND D.ITEM_CAT_CODE=A.ITEM_CATEGORY"+
     	//" AND E.ITEM_CAT_CODE=D.ITEM_CAT_CODE"+
					
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";



//--------------------------------------------------------------------------------------------------
m_help_TXT_QUOTATION_NO_sql=

			" SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE "+

						
			" FROM  "+
			" (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.STATUS,P.APPR_USER,P.APPR_DATE"+

			" FROM( "+ 
		  " SELECT "+
     	" QUOTATION_NO,"+
    	"	INQUIRY_NO,"+
    	"	STATUS,"+
			" APPR_USER ,"+
			" APPR_DATE "+
			" FROM LAKDL.AF_MK_PRO_QUOTATION "+
	  	" WHERE QUOTATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			//AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

//--------------------------------------------------------------------------------------------------------	
		m_help_TXT_CONDITION_OF_ASSET_sql=
	
			" SELECT L.NO ,L.CODE,REPLACE(L.DESCRIPTION,' ','-')AS DESCRIPTION,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+

						
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+

			" FROM( "+ 
		  " SELECT "+
     	"	CODE,"+
    	"	DESCRIPTION,"+
    	"	ACTIVE_STATUS,"+
    	"	DEFAULT_VALUE "+
			" FROM LAKDL.AF_CO_MAS_CONDITION_OF_ASSET "+
	  	" WHERE UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
//-------------------------------------------------------------------------------------------------------------
	m_help_TXT_MAKE_CODE_sql=
			" SELECT L.NO ,L.MAKE_CODE,REPLACE(L.MAKE_DESC,' ','-') AS DESCRIPTION,L.ITEM_SUB_CAT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.ITEM_SUB_CAT,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " MAKE_CODE,"+
      " MAKE_DESC, "+
			" ITEM_SUB_CAT, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_MAKE "+
	  	" WHERE UPPER(MAKE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND UPPER(MAKE_DESC) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
//--------------------------------------------------------------------------------------------------------------

m_help_TXT_MODEL_CODE1_sql=
			" SELECT L.NO ,L.MODEL_CODE,REPLACE(L.DESCRIPTION,' ','-') DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " MODEL_CODE,"+
      " DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_MODEL "+
	  	" WHERE UPPER(MODEL_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
//AND DESCRIPTION LIKE UPPER('"+m_vector.elementAt(1)+"%')
//---------------------------------------------------------------------------------------------------------------


m_help_TXT_INQUIRY_NO_sql=
			" SELECT L.NO,L.INQUIRY_CODE,L.CLIENT_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE "+
			" FROM( "+ 
		  " SELECT "+
      " INQUIRY_CODE,"+
    	"	CLIENT_NAME,"+
    	"	ADDRESS,"+
    	"	ADDRESS2,"+
    	"	CITY_CODE "+
      " FROM LAKDL.AF_MK_PRO_INQUIRY "+
	  	" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";





//================Sandun on 01-07-2009================================================================================================
			ClientSql_ODI_APP1 =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
												"FROM "+
														"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CLIENT_CODE)LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
														"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
														"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"                      	 WHERE  APPLICATION_STATUS = 'ACTIVATED') AND "+
														"        CLIENT_CODE IN (SELECT DISTINCT A.CLIENT_CODE "+
                            "                        FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET B "+
                            "                        WHERE A.INVOICE_NO = B.INVOICE_NO "+
                            "                        AND  B.STATUS = 'ENT' ) "+
												    " ORDER BY FULL_NAME )) P "+		
										        " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
				
				ClientSql_ODI_APP2 =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
												"FROM "+
														"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CLIENT_CODE)LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
														"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
														"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"                      	 WHERE  APPLICATION_STATUS = 'ACTIVATED') AND "+
														"        CLIENT_CODE IN (SELECT DISTINCT A.CLIENT_CODE "+
                            "                        FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET B "+
                            "                        WHERE A.INVOICE_NO = B.INVOICE_NO "+
                            "                        AND  B.STATUS = 'AP1' ) "+
												    " ORDER BY FULL_NAME )) P "+		
										        " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			
			
			
			LeaseSql_ODI_APP1 = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, "+
 														"		      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT "+
										"FROM "+
												"(SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
												"FROM "+
														"( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
														"	 FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERM_TO','TERMI','REPOSSESS') AND "+ // REPOSSESS by udara 27-01-2014
                            "         FINANCE_NO IN (SELECT DISTINCT A.FINANCE_NO "+
                            "                        FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET B "+
                            "                        WHERE A.INVOICE_NO = B.INVOICE_NO "+
                            "                        AND  B.STATUS = 'ENT' ) 	AND "+
														"         CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND "+
                            "         (FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
                            "          APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
			
			
			LeaseSql_ODI_APP2 = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, "+
 														"		      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT "+
										"FROM "+
												"(SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
												"FROM "+
														"( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, "+
 														"		      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO "+
														"	 FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
														"	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERM_TO','TERMI','REPOSSESS') AND "+ // REPOSSESS by udara 27-01-2014
                            "         FINANCE_NO IN (SELECT DISTINCT A.FINANCE_NO "+
                            "                        FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_ODI_AJUSTMENT_DET B "+
                            "                        WHERE A.INVOICE_NO = B.INVOICE_NO "+
                            "                        AND  B.STATUS = 'AP1' ) AND "+
														"         CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' AND "+
                            "         (FINANCE_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
                            "          APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%') )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
										
	//VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
 
  // ****Do not Alter The Parts Below
	 Ret_Object= (Object)Sql_Name;
	 return Ret_Object;
	  
	} 
}

/*----------------------------------------------------------------*/


