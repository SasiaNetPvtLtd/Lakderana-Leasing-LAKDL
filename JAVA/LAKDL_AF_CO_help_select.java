//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_CO_help_select  {  
 
  //Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
  LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	 
	public String UserSql            = " ";
	public String UserSql_Header     = " Help "; 
	public String FollowIDSql        = " ";
	public String FollowIDSql_Header = " Help "; 
	public String FollowupSql        = " ";
	public String FollowupSql_Header = " Help "; 
	
	public String LeaseSql           = " ";
  public String LeaseSql_Header    = " Help ";
  public String ClientSql           = "";
  public String ClientSql_Header = " Help ";
	public String AlloReceiptSql    ="";
	public String AlloReceiptSql_Header ="Help";
	
	
	
	// Method for Sql Put Sqls Inside
	
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

	FollowupSql =   	"SELECT P.NO, FOLLOW_UP_NO, ID_NO,ACTION_TOBE_TAKEN,"+
												"        EFF_VAL_DATE,ACTION_SET_FOR,ENT_REMARKS "+
										"FROM "+
												"(SELECT ROWNUM NO, FOLLOW_UP_NO, ID_NO,ACTION_TOBE_TAKEN,"+
												"        EFF_VAL_DATE,ACTION_SET_FOR,ENT_REMARKS "+
												"FROM "+
														"(SELECT  A.FOLLOW_UP_NO, A.ID_NO, A.ACTION_TOBE_TAKEN, "+
														"         A.EFF_VAL_DATE, A.ACTION_SET_FOR, A.ENT_REMARKS "+
														"  FROM   "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A "+
														 " WHERE  FOLLOW_UP_NO LIKE '"+m_vector.elementAt(0)+"%' AND  STATUS='PENDING' "+
														" ORDER BY EFF_VAL_DATE )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
											
											           
/*----------------------------------------------------------------
		Purpose  : select Inquiry Number
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/			
  FollowIDSql = "SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO "+//5
										"FROM "+
												"(SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO "+
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
														" WHERE INQUIRY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') " +
												" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
/*----------------------------------------------------------------
		Purpose  : select Marketing Supervisor
	
		Used in  : MK Inquiry
	-----------------------------------------------------------------*/
	UserSql = " SELECT NO,USER_ID, NAME "+
               " FROM  ( SELECT ROWNUM NO,USER_ID, NAME "+
               " FROM  ( SELECT USER_ID, NAME "+
               " FROM   "+m_schema_name+".CO_CO_MAS_USER "+
							 " WHERE  USER_ID LIKE '%"+m_vector.elementAt(0)+"%' "+
				 			 " ORDER BY NAME)) P "+		
						 	 " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	

//================================================================================================================
			
			
			//================================================================================================================
			
  LeaseSql = "SELECT P.NO,FINANCE_NO \"Finance No\",APPLICATION_NO \"Application No\",Name,FACILITY_NO Facility, \t\t      CLIENT_CODE Code,APPLICATION_STATUS Status,CO_APPLICANT FROM (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,Name, \t\t      CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO FROM ( SELECT FINANCE_NO,APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) Name, " + "\t\t CLIENT_CODE,APPLICATION_STATUS,CO_APPLICANT,FACILITY_NO " + "\t FROM   " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS A " + "\t WHERE  APPLICATION_STATUS IN('ACTIVATED','LEGAL','TERMI','TERMINATED','TERM_TO') AND " + "         CLIENT_CODE LIKE '%" +m_vector.elementAt(1)+ "%' AND  " + "         (FINANCE_NO LIKE '%" +m_vector.elementAt(0)+ "%' OR  " + "          APPLICATION_NO LIKE '%" +m_vector.elementAt(0)+ "%') " + "  ORDER BY APPLICATION_NO DESC)) P " + "WHERE P.NO>= " +Start_Val+ " AND P.NO<= " +End_Val+ " ";
	ClientSql = "SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status FROM (SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS FROM (SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  \t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "\t WHERE (UPPER(FULL_NAME) LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "        UPPER(CLIENT_CODE)  LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "        UPPER(ADDRESS1)  LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "\t       UPPER(CITY_CODE) LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "        UPPER(TEL_NO)    LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "\t       UPPER(EMAIL)     LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "        UPPER(NIC_NO)    LIKE UPPER('%" +m_vector.elementAt(0)+ "%') OR " + "\t       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" +m_vector.elementAt(0)+ "%')) " + " ORDER BY FULL_NAME )) P " + "WHERE P.NO>= " +Start_Val+ " AND P.NO<= " +End_Val+ " ";
	
	AlloReceiptSql =
 " SELECT P.NO, REC_NO,EFF_VALDATE, REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE "+
 " FROM "+
	
 " (SELECT ROWNUM NO, REC_NO,EFF_VALDATE, REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE "+
 " FROM ( "+
 " SELECT A.REC_NO, A.REC_AMOUNT,ALLOCATED_AMOUNT,BAL_TOBE_RECEIVE,OTH_COMMENTS,CURR_CODE,"+
 " TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE "+
 " FROM   " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT A, " + 
 " " + m_schema_name + ".AF_CO_PRO_SETTL_RECEIPT_BAL B " + 
 " WHERE  A.REC_NO = B.REC_NO  " + 
 " AND ALLOCATED_AMOUNT>0  "+
 " AND A.REC_NO LIKE '%" + m_vector.elementAt(0) + "%'  " +
 /*" AND A.REC_NO NOT IN ( "+ // added by nuwan de silva 09-07-2008
	
	" SELECT REC_NO FROM( "+
  " SELECT A.REC_NO,COUNT(A.FINANCE_NO) CONTRACT "+
  " FROM  " + m_schema_name + ".AF_CO_PRO_SETTL_REC_APP_BAL A , " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS B "+
  " WHERE A.FINANCE_NO=B.FINANCE_NO "+
  " AND   B.APPLICATION_STATUS  IN ('NORM_TERM','TERMI') "+
  " AND   A.REC_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') "+
  " GROUP BY A.FINANCE_NO,REC_NO) "+
  " WHERE CONTRACT=1 "+
 "	)"+	
	*/
 " ORDER BY EFF_VALDATE )) P " +
 " WHERE P.NO>= " + Start_Val + " AND P.NO<= " + End_Val + " ";
 
  // ****Do not Alter The Parts Below
	 Ret_Object= (Object)Sql_Name;
	 return Ret_Object;
	  
	} 
}

/*----------------------------------------------------------------*/


