//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL

import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_RE_help_select1 {
    
    //Enter Filds Here
    
    public Object Ret_Object = new Object();
    public String ret_str;
    //String m_schema_name="";
    
    //Put the Sqlnames Bigining with m_*
    
    LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
    String m_schema_name=m_sn_methods.schema_name.trim();
    
    
    public String m_help_coll_officer_list_sql="";
    public String m_help_coll_officer_list_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
    
    public String m_help_TXT_FINANCE_NO_branch_sql        = "";
    public String m_help_TXT_FINANCE_NO_branch_sql_Header ="Finance Number Help"; //Added By Sandun For Collection Report - Branch 07-11-2008

    public String m_help_TXT_LOCATION_CODE_sql  ="";
    public String m_help_TXT_LOCATION_CODE_sql_Header ="Location Help";
	
    public String m_help_marketing_officer ="";
    public String m_help_marketing_officer_Header ="Marketing Officer - Help";
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

        m_help_marketing_officer=
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
            " FROM( "+ 
            " SELECT "+
            " DISTINCT A.EMP_CODE,"+
            " A.TITLE,"+
            " A.FIRST_NAME,"+
            " A.LAST_NAME,"+
            " A.ADDRESS,"+
            " A.LOCATION_CODE,"+
            " A.AREA_CODE,"+
            " A.CITY_CODE,"+
            " A.CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " A.DIVISION_CODE,"+
            " A.EPF_NO,"+
            " A.ID_NO "+
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
            " WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
            " AND ("+
            "     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
            " OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
            " AND UPPER(A.LOCATION_CODE)   LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
            
            " AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
 
//added by nuwan de silva on 20-09-07-------------	
        m_help_TXT_LOCATION_CODE_sql=
            
            " SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE"+
            " FROM  "+
            " (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE "+
            " FROM( "+ 
            " SELECT "+
            " LOCATION_CODE, "+
            " LOCATION_DESC, "+
            " ADDRESS1,  "+
            " ADDRESS2, "+
            " CITY_CODE, "+
            " POSTAL_CODE, "+
            " COUNTRY_CODE "+
            " FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
            " WHERE (LOCATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR LOCATION_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        m_help_TXT_FINANCE_NO_branch_sql=
            //Added By Sandun For Collection Report - Branch 07-11-2008
            " SELECT L.NO ,L.FINANCE_NO,L.NAME,L.ADDRESS,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE,L.EMP_CODE ,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.FINANCE_NO,P.NAME,P.ADDRESS,P.CONTACT_NO,P.CITY_CODE,P.LOCATION_CODE,P.AREA_CODE,P.EMP_CODE,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
            " FROM( "+ 
            " SELECT   "+
            " DISTINCT B.FINANCE_NO,	"+		 
            " A.FIRST_NAME || ' ' || A.LAST_NAME NAME, "+
            " A.ADDRESS, "+
            " A.CONTACT_NO,"+
            " A.CITY_CODE, "+
            " A.LOCATION_CODE,"+
            " A.AREA_CODE,"+			 			 
            " A.EMP_CODE,"+
            " DESIGNATION_CODE,"+
            " A.DIVISION_CODE, "+
            " A.EPF_NO, "+
            " A.ID_NO "+
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
            " WHERE A.EMP_CODE = B.COLLECTION_OFFICER "+
            " AND ("+
            "     UPPER(B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
            " OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        
        m_help_coll_officer_list_sql=
            /*
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
            " FROM( "+ 
            " SELECT "+
            " DISTINCT A.EMP_CODE,"+
            " A.TITLE,"+
            " A.FIRST_NAME,"+
            " A.LAST_NAME,"+
            " A.ADDRESS,"+
            " A.LOCATION_CODE,"+
            " A.AREA_CODE,"+
            " A.CITY_CODE,"+
            " A.CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " A.DIVISION_CODE,"+
            " A.EPF_NO,"+
            " A.ID_NO "+
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
            " WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
            " AND ("+
            "     UPPER(A.EMP_CODE)      LIKE UPPER('"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
            " OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('"+m_vector.elementAt(0)+"%')  ) "+
            // " AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
            
            " AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
            */
            
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
            " WHERE  A.EMP_CODE = B.COLLECTION_OFFICER AND " + 
            "    ( A.EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(A.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
            // " AND EMP_CODE NOT IN ( " +
            // "     SELECT DISTINCT COLLECTION_OFFICER " +
            // "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
            // " ) " +
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        
        // ****Do not Alter The Parts Below
        Ret_Object= (Object)Sql_Name;
        return Ret_Object;
        
    } 
}
