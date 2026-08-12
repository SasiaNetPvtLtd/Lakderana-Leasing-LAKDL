import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_MAS_help_select2 { 
     
    public Object Ret_Object = new Object();
    LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
    String m_schema_name = m_sn_methods.schema_name.trim();
    
    
    //------------------------------------------------------------------------
    
    
    public String m_help_Assign_Mkt_Col_Collection_Officer_New_sql="";
    public String m_help_Assign_Mkt_Col_Collection_Officer_New_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
    
    public String m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql="";
    public String m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
    
    public String m_help_Assign_Mkt_Col_Marketing_Officer_New_sql="";
    public String m_help_Assign_Mkt_Col_Marketing_Officer_New_sql_Header="Marketing Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
    
    public String m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql="";
    public String m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql_Header="Marketing Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
    
	public String m_help_TXT_DOCUMENT_TYPE_sql="";
    public String m_help_TXT_DOCUMENT_TYPE_sql_Header="Document Types"; //INESH 2018-01-09 JB02012018-02276
	 
	public String m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql = "";
	public String m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql_Header = "Finance No";//INESH 2018-01-09 JB02012018-02276
    
    public String m_help_TXT_ACTIVE_USER_ID_sql = "";
	public String m_help_TXT_ACTIVE_USER_ID_sql_Header = "User Id";//INESH 2018-10-03 JB02012018-02276
	
	public String m_help_TXT_ACTIVE_USER_ID_sql_2 = "";
	public String m_help_TXT_ACTIVE_USER_ID_sql_2_Header = "User Id";//INESH 2018-10-03 JB02012018-02276
	
	public String m_help_TXT_REBATE_RATE_sql = "";
	public String m_help_TXT_REBATE_RATE_sql_Header = "User Id";//INESH 2018-10-03 JB02012018-02276
	
	public String m_help_TXT_FINANCENO_SECURITY_INSURANCE_sql = "";
	public String m_help_TXT_FINANCENO_SECURITY_INSURANCE_sql_Header = "Finance No";//INESH 2018-10-17 JB21082018-04816
	
    public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4,Object reqObj5) {
        String Sql_Name  = (String) reqObj1;
        String Start_Val = (String) reqObj2;
        String End_Val   = (String) reqObj3;	
        String Criteria  =	(String) reqObj4;
        String m_user  =   (String) reqObj5;
        
        int m_val =(Integer.parseInt(End_Val));
        m_val++;
        End_Val = Integer.toString(m_val);
        
        Vector m_vector = new Vector();
        String m_substring="";
        int start_index,stop_index,cnt,i;
        start_index = 0;
        stop_index = 0;
        cnt = 0;
        int m_length = Criteria.lastIndexOf("@");
        
        if (m_length!=0) {
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
            m_vector.addElement("");
        }
        
        cnt = m_vector.size();
        
        for ( i = cnt ; i < 8 ; i++ ) {
            m_vector.addElement("");
        }
        
        
        
        
        
        m_help_Assign_Mkt_Col_Collection_Officer_New_sql=
            // m_help_TXT_EMP_CODE_2_sql=
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
            //" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
            " FROM( "+ 
            " SELECT "+
            " EMP_CODE,"+
            " TITLE,"+
            " FIRST_NAME,"+
            " LAST_NAME,"+
            " ADDRESS,"+
            " LOCATION_CODE,"+
            " AREA_CODE,"+
            " CITY_CODE,"+
            " CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " DIVISION_CODE,"+
            " EPF_NO,"+
            " ID_NO ,"+
            " NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
            " EMP_DOB ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
            // " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            " WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
            " AND EMP_CODE NOT IN ( " +
            "     SELECT DISTINCT COLLECTION_OFFICER " +
            "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
            " ) " +
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql=
            // m_help_TXT_EMP_CODE_2_sql=
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
            //" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
            " FROM( "+ 
            " SELECT DISTINCT "+
            " A.EMP_CODE,"+
            " A.TITLE,"+
            " A.FIRST_NAME,"+
            " A.LAST_NAME,"+
            " A.ADDRESS,"+
            " A.LOCATION_CODE,"+
            " A.AREA_CODE,"+
            " A.CITY_CODE,"+
            " A.CONTACT_NO,"+
            " A.DESIGNATION_CODE,"+
            " A.DIVISION_CODE,"+
            " A.EPF_NO,"+
            " A.ID_NO ,"+
            " NVL("+m_schema_name+".AF_CO_GET_USER(A.EMP_CODE),'-' ) USER_ID, "+
            " A.EMP_DOB ,"+ //Added by Prabash on 19-08-2011
            " A.EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
            " A.EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+
            "      "+m_schema_name+".AF_CR_PRO_MKT_COLL_MAP B "+
            // " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            " WHERE A.EMP_CODE = B.COLLECTION_OFFICER " +
            "   AND ( A.EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(A.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
            // " AND EMP_CODE NOT IN ( " +
            // "     SELECT DISTINCT COLLECTION_OFFICER " +
            // "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
            // " ) " +
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        m_help_Assign_Mkt_Col_Marketing_Officer_New_sql=
            // m_help_TXT_EMP_CODE_2_sql=
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
            //" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
            " FROM( "+ 
            " SELECT "+
            " EMP_CODE,"+
            " TITLE,"+
            " FIRST_NAME,"+
            " LAST_NAME,"+
            " ADDRESS,"+
            " LOCATION_CODE,"+
            " AREA_CODE,"+
            " CITY_CODE,"+
            " CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " DIVISION_CODE,"+
            " EPF_NO,"+
            " ID_NO ,"+
            " NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
            " EMP_DOB ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
            // " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            " WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
            " AND EMP_CODE NOT IN ( " +
            "     SELECT MARKETING_OFFICER " +
            "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
            " ) " +
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql=
            // m_help_TXT_EMP_CODE_2_sql=
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
            //" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
            " FROM( "+ 
            " SELECT "+
            " EMP_CODE,"+
            " TITLE,"+
            " FIRST_NAME,"+
            " LAST_NAME,"+
            " ADDRESS,"+
            " LOCATION_CODE,"+
            " AREA_CODE,"+
            " CITY_CODE,"+
            " CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " DIVISION_CODE,"+
            " EPF_NO,"+
            " ID_NO ,"+
            " NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
            " EMP_DOB ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
            " EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
            // " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            " WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
            " AND EMP_CODE NOT IN ( " +
            "     SELECT MARKETING_OFFICER " +
            "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
            "     WHERE  COLLECTION_OFFICER <> '"+m_vector.elementAt(1)+"' " +
            " ) " +
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        m_help_TXT_DOCUMENT_TYPE_sql =
			 " SELECT L.NO,L.DOC_NAME ,L.DOC_DESCRIPTION ,L.NUM_OF_DOC ,L.DOC_ID"+
			 " FROM( "+
			 "     SELECT ROWNUM NO,P.DOC_NAME,P.DOC_DESCRIPTION,P.DOC_ID,P.NUM_OF_DOC "+
			 "     FROM ( "+
			 "         SELECT UPPER(DOC_NAME) DOC_NAME,DOC_ID,NVL(DOC_DESCRIPTION,'_') DOC_DESCRIPTION,NVL(NUM_OF_DOC,0) NUM_OF_DOC "+
			 "         FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_TYPES "+
			 "         WHERE ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' ";
		if(m_vector.elementAt(0)!=null && !m_vector.elementAt(0).equals("")){
			m_help_TXT_DOCUMENT_TYPE_sql+=
			 "         AND ( "+
			 "           DOC_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DOC_DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DOC_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			 "         ) ";
			}
		m_help_TXT_DOCUMENT_TYPE_sql +=	 
			 "         )P "+
			 "     ) L "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
				
		m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql =
		" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client  FROM ( "+ 
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			
			" "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,  "+
			"    "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) VEHICLE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') ";
		if(m_vector.elementAt(0)!= null && !m_vector.elementAt(0).equals("")){
			m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql +=
			" AND  (UPPER(b.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			//" UPPER(a.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+			
			" A.CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.FINANCE_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" b.APPLICATION_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" A.NIC_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+			
			" UPPER(C.REG_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')  "+
			" OR "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(0)+"%' "+ 
			" ) ";
		}
			
			m_help_TXT_DOCUMENT_UPLOAD_FIN_NO_sql+=
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ 			
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
			
			
		m_help_TXT_ACTIVE_USER_ID_sql = 
			" SELECT L.NO ,L.USER_ID,L.NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME "+
			 
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+  
			" AND ACTIVE_STATUS='Y' "+
			" AND USER_ID NOT IN (SELECT DISTINCT USER_ID FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ACTIVE_USER_ID_sql_2 = 
			" SELECT L.NO ,L.USER_ID,L.NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME "+
			 
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+  
			//" AND ACTIVE_STATUS='Y' "+
			//" AND USER_ID NOT IN (SELECT DISTINCT USER_ID FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_REBATE_RATE_sql = 
			" SELECT L.NO ,L.USER_ID,L.REBATE_FROM,L.REBATE_TO,L.ODI_FROM,L.ODI_TO, L.CHARGE_APPLICABLE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.REBATE_FROM,P.REBATE_TO,P.ODI_FROM,P.ODI_TO, P.CHARGE_APPLICABLE "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NVL(REBATE_FROM,0) REBATE_FROM, "+
			" NVL(REBATE_TO,0) 	 REBATE_TO, "+
			" NVL(ODI_FROM,0) 	 ODI_FROM, "+
			" NVL(ODI_TO,0) 	 ODI_TO, "+
			" NVL(CHARGE_APPLICABLE,'N') CHARGE_APPLICABLE  "+ // added by udara 30-10-2018
			" FROM "+m_schema_name+".AF_AD_REBATE_CONFIGURATION "+
			
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+  
			" AND ACTIVE_STATUS='"+m_vector.elementAt(1)+"' "+
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_FINANCENO_SECURITY_INSURANCE_sql = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND APPLICATION_STATUS NOT IN ('NORM_TERMI','TERMI', 'TERMINATED') "+ 
			" AND A.FINANCE_NO NOT IN (SELECT DISTINCT B.FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B WHERE B.ACTIVE_STATUS != 'CANCEL') "+ 
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
        //------------------------------------------------------------------------------------------------------------------------------------------------
        Ret_Object= (Object)Sql_Name;
        return Ret_Object;
        
    }
    
}
