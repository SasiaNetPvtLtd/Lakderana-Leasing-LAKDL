import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MAS_help_select_client  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();

	public String m_help_DIV_TXT_CLIENT_GROUP_sql="";
	public String m_help_DIV_TXT_CLIENT_GROUP_sql_Header="System Administration - Client/Debtor Groups ";
	
	public String m_help_DIV_CLIENT_LEGAL_ST_BUSINESS_sql="";
	public String m_help_DIV_CLIENT_LEGAL_ST_BUSINESS_sql_Header="System Administration - Business Legal Entity ";
	
	public String m_help_DIV_TXT_CLIENT_CITY_sql="";
	public String m_help_DIV_TXT_CLIENT_CITY_sql_Header="System Administration - City ";
	
	public String m_help_DIV_TXT_CLIENT_COLLECTION_AREA_sql="";
	public String m_help_DIV_TXT_CLIENT_COLLECTION_AREA_sql_Header="System Administration - Area ";
	
	public String m_help_DIV_TXT_CLIENT_BANK_sql="";
	public String m_help_DIV_TXT_CLIENT_BANK_sql_Header="System Administration - Bank ";
	
	public String m_help_DIV_TXT_CLIENT_BRANCH_sql="";
	public String m_help_DIV_TXT_CLIENT_BRANCH_sql_Header="System Administration - Branch ";
	
	public String m_help_DIV_TXT_FACTOR_CLIENT_CODE_sql="";
	public String m_help_DIV_TXT_FACTOR_CLIENT_CODE_sql_Header=" Marketing - Client/Debtor Information Collection ";

	public String m_help_DIV_TXT_FACTOR_CLIENT_CODE_MKT_sql="";
	public String m_help_DIV_TXT_FACTOR_CLIENT_CODE_MKT_sql_Header=" Marketing - Client/Debtor Information Collection ";
	
	public String m_help_DIV_TXT_FACTOR_CLIENT_NAME_CHANGE_CLIENT_sql=""; // Added by Udar Somathilake on 05-05-2010
	public String m_help_DIV_TXT_FACTOR_CLIENT_NAME_CHANGE_CLIENT_sql_Header=" Marketing - Change CLient Details ";
	
	public String m_help_DIV_TXT_PRODUCT_SUB_SECTOR_sql="";
	public String m_help_DIV_TXT_PRODUCT_SUB_SECTOR_sql_Header="System Administration - Business Sub Sector ";
	
	public String m_help_DIV_TXT_EXPOSURE_CAT_sql="";
	public String m_help_DIV_TXT_EXPOSURE_CAT_sql_Header="System Administration - Exposure Category ";
	
	public String m_help_DIV_TXT_EXPOSURE_SECTOR_sql="";
	public String m_help_DIV_TXT_EXPOSURE_SECTOR_sql_Header="System Administration - Sector ";
	
	public String m_help_DIV_CLIENT_INQUERY_NO_sql="";
	public String m_help_DIV_CLIENT_INQUERY_NO_sql_Header=" Marketing - Inquiry ";
	
	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql= "";
	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql_Header= "System Administration - Product Category ";
	
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
		
		m_help_DIV_TXT_CLIENT_GROUP_sql=
			" SELECT L.NO ,L.GROUP_CODE,L.GROUP_DESC,L.GROUP_APPLI,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_DESC,P.GROUP_APPLI,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+ 
			" GROUP_CODE, "+ 
			" GROUP_DESC, "+ 
			" GROUP_APPLI, "+ 
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+ 
			" WHERE (GROUP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR GROUP_DESC LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_CLIENT_LEGAL_ST_BUSINESS_sql=
			" SELECT L.NO ,L.ENTITY_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENTITY_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT ENTITY_CODE,DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE (ENTITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_CLIENT_CITY_sql=
			" SELECT L.NO ,L.CITY_CODE,L.CITY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC "+
			" FROM( "+
			" SELECT CITY_CODE,CITY_DESC,DISTRICT_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CITY "+
			" WHERE (CITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR CITY_DESC LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_CLIENT_COLLECTION_AREA_sql=
			" SELECT L.NO ,L.AREA_CODE, L.AREA_DESC,L.CITY_CODE,L.CITY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE, P.AREA_DESC,P.CITY_CODE,P.CITY_DESC "+
			" FROM( "+
			" SELECT A.AREA_CODE,A.AREA_DESC, A.CITY_CODE,B.CITY_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_AREA A,"+m_schema_name+".AF_CO_MAS_CITY B "+
			" WHERE A.CITY_CODE=B.CITY_CODE AND (A.AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR A.AREA_DESC LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND A.ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

 		m_help_DIV_TXT_CLIENT_BANK_sql=
			" SELECT L.NO ,L.BANK_CODE,L.NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME "+
			" FROM( "+
			" SELECT BANK_CODE,NAME "+
  		" FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
			" WHERE (BANK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR NAME LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_CLIENT_BRANCH_sql=
			" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.BANK_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.BANK_NAME "+
			" FROM( "+
			" SELECT A.BRANCH_CODE,A.BRANCH_NAME,B.BANK_CODE,B.NAME BANK_NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_BANKS B "+
			" WHERE A.BANK_CODE=B.BANK_CODE AND ((A.BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR A.BRANCH_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND B.BANK_CODE = UPPER('"+m_vector.elementAt(1)+"'))  AND A.ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		//Modified by Mahela on 22-12-2006	
		m_help_DIV_TXT_FACTOR_CLIENT_CODE_sql=
			" SELECT L.NO,L.CLIENT_CODE,DECODE(L.CLIENT_TYPE,'C','CORPORATE','I','INDIVIDUAL') CLIENT_TYPE,DECODE(L.FACTORING_TYPE,'C','CLIENT','D','DEBTOR','B','CLIENT OR DEBTOR') FACTORING_TYPE,L.FULL_NAME,L.ACTIVE_STATUS,L.CLIENT_DEC "+
			" FROM( "+
			" SELECT ROWNUM NO,P.CLIENT_CODE,P.CLIENT_TYPE,P.FACTORING_TYPE,P.FULL_NAME,P.ACTIVE_STATUS,P.CLIENT_DEC "+
			" FROM( "+
			" SELECT CLIENT_CODE,CLIENT_TYPE,FACTORING_TYPE,FULL_NAME,ACTIVE_STATUS,'FACTORING' CLIENT_DEC "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE "+
			" ACTIVE_STATUS IN('I') AND "+
			" ( "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(REGISTERED_TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(REGISTERED_MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(PASSPORT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" UNION "+
			" SELECT CLIENT_CODE,CLIENT_TYPE,'C' FACTORING_TYPE,FULL_NAME,ACTIVE_STATUS,'LEASING' CLIENT_DEC "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE ( "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(PASSPORT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) AND CLIENT_CODE NOT IN (SELECT CLIENT_CODE FROM "+m_schema_name+".FA_CO_MAS_CLIENT ) "+
			" )P "+
			" ORDER BY P.CLIENT_DEC,P.FULL_NAME "+
			" )L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			m_help_DIV_TXT_FACTOR_CLIENT_CODE_MKT_sql=
			" SELECT L.NO,L.CLIENT_CODE,DECODE(L.CLIENT_TYPE,'C','CORPORATE','I','INDIVIDUAL') CLIENT_TYPE,DECODE(L.FACTORING_TYPE,'C','CLIENT','D','DEBTOR','B','CLIENT OR DEBTOR') FACTORING_TYPE,L.FULL_NAME,L.ACTIVE_STATUS,L.CLIENT_DEC "+
			" FROM( "+
			" SELECT ROWNUM NO,P.CLIENT_CODE,P.CLIENT_TYPE,P.FACTORING_TYPE,P.FULL_NAME,P.ACTIVE_STATUS,P.CLIENT_DEC "+
			" FROM( "+
			" SELECT CLIENT_CODE,CLIENT_TYPE,FACTORING_TYPE,FULL_NAME,ACTIVE_STATUS,'FACTORING' CLIENT_DEC "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			" WHERE "+
			" ACTIVE_STATUS IN('E') AND "+
			" ( "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(REGISTERED_TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(REGISTERED_MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(PASSPORT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" UNION "+
			" SELECT CLIENT_CODE,CLIENT_TYPE,'C' FACTORING_TYPE,FULL_NAME,ACTIVE_STATUS,'LEASING' CLIENT_DEC "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE ( "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(PASSPORT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) AND CLIENT_CODE NOT IN (SELECT CLIENT_CODE FROM "+m_schema_name+".FA_CO_MAS_CLIENT ) "+
			" )P "+
			" ORDER BY P.CLIENT_DEC,P.FULL_NAME "+
			" )L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			// Added by Udara Somathilake on 05-05-2010
			m_help_DIV_TXT_FACTOR_CLIENT_NAME_CHANGE_CLIENT_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.FINANCE_NO,L.APPLICATION_NO,L.DIVISION_CODE,L.CLIENT_TYPE,DECODE(L.FACTORING_TYPE,'C','CLIENT','D','DEBTOR','B','CLIENT OR DEBTOR') FACTORING_TYPE,L.ACTIVE_STATUS,L.CLIENT_DEC "+
			" FROM( "+
			" SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.FINANCE_NO,P.APPLICATION_NO,P.DIVISION_CODE,P.CLIENT_TYPE,P.FACTORING_TYPE,P.ACTIVE_STATUS,P.CLIENT_DEC "+
			" FROM( "+
			" SELECT A.CLIENT_CODE,A.FULL_NAME,B.FINANCE_NO,B.APPLICATION_NO,B.DIVISION_CODE,A.CLIENT_TYPE,'C' FACTORING_TYPE,A.ACTIVE_STATUS,'LEASING' CLIENT_DEC "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.CLIENT_CODE = B.CLIENT_CODE  "+
			" AND A.ACTIVE_STATUS='Y' "+
			//" AND B.APPLICATION_STATUS = 'ACTIVATED' "+
			" AND ( "+
			"    UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR UPPER(A.FULL_NAME)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(B.FINANCE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" )"+
			" )P "+
			" ORDER BY P.CLIENT_DEC,P.FULL_NAME "+
			" )L "+
			" WHERE L.NO>= "+Start_Val+" AND L.NO<= "+End_Val+" ";
			
			// End by Udara Somathilake on 05-05-2010
			
			m_help_DIV_TXT_PRODUCT_SUB_SECTOR_sql=
			" SELECT L.NO ,L.SUB_CODE,L.DESCRIPTION1,L.SECTOR_CODE,L.DESCRIPTION2 "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION1,P.SECTOR_CODE,P.DESCRIPTION2 "+
			" FROM( "+
			" SELECT A.SUB_CODE,NVL(A.DESCRIPTION,' ') DESCRIPTION1,B.SECTOR_CODE,NVL(B.DESCRIPTION,' ') DESCRIPTION2"+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS A,"+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR B "+
			" WHERE A.SECTOR_CODE=B.SECTOR_CODE AND A.ACTIVE_STATUS='Y' AND "+
			" (UPPER(A.SUB_CODE) LIKE  UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(A.DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
			m_help_DIV_TXT_EXPOSURE_CAT_sql=
			" SELECT L.NO ,L.EXPOSURE_CODE,L.EXPOSURE_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EXPOSURE_CODE,P.EXPOSURE_DESC "+
			" FROM( "+
			" SELECT EXPOSURE_CODE,NVL(EXPOSURE_DESC,' ') EXPOSURE_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			" WHERE ACTIVE_STATUS='Y' AND "+
			" (UPPER(EXPOSURE_CODE) LIKE  UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(EXPOSURE_DESC) LIKE  UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

			/*m_help_DIV_TXT_EXPOSURE_SECTOR_sql=
			" SELECT L.NO ,L.SECTOR_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SECTOR_CODE,P.DESCRIPTION "+
			" FROM( "+
			" SELECT SECTOR_CODE,NVL(DESCRIPTION,' ') DESCRIPTION"+
			" FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR "+
			" WHERE ACTIVE_STATUS='Y' AND "+
			" (UPPER(SECTOR_CODE) LIKE  UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE  UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
			
			m_help_DIV_TXT_EXPOSURE_SECTOR_sql=
			" SELECT L.NO ,L.SUB_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION "+
			" FROM( "+
			" SELECT SUB_CODE,NVL(DESCRIPTION,' ') DESCRIPTION"+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
			" WHERE ACTIVE_STATUS='Y' AND "+
			" (UPPER(SUB_CODE) LIKE  UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE  UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		m_help_DIV_CLIENT_INQUERY_NO_sql=
		 	" SELECT L.NO,L.INQUIRY_CODE,L.CLIENT_NAME,L.LEGAL_ENTITY,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,L.MOBILE_NO,L.FAX_NO,L.EMAIL,L.CONTACT_PERSON  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME,P.LEGAL_ENTITY,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.MOBILE_NO,P.FAX_NO,P.EMAIL,P.CONTACT_PERSON "+
			" FROM( "+
			" SELECT "+
			" A.INQUIRY_CODE, "+
			" A.CLIENT_NAME, "+
			" DECODE(A.LEGAL_ENTITY,'INDIVIDUAL','I','C') LEGAL_ENTITY, "+
			" NVL(A.ADDRESS,'-') ADDRESS1, "+
			" NVL(A.ADDRESS2,'-') ADDRESS2, "+
			" NVL(A.CITY_CODE,'-') CITY_CODE, "+
			" NVL(A.TEL_NO,'-') TEL_NO, "+
			" NVL(A.MOBILE_NO,'-') MOBILE_NO, "+
			" NVL(A.FAX_NO,'-') FAX_NO, "+
			" NVL(A.EMAIL,'-') EMAIL, "+
			" NVL(A.CONTACT_PERSON,'-') CONTACT_PERSON "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A "+
			" WHERE A.DIVISION_CODE='FA' AND A.INQUIRY_CODE NOT IN (SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO) "+
			" AND (A.INQUIRY_CODE LIKE ('"+m_vector.elementAt(0)+"%') OR UPPER(A.CLIENT_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			//Added by Mahela on 01-01-2007
		m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql=	
		  " SELECT L.NO ,L.PRODUCT_CATEGORY,L.PRODUCT_CATEGORY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CATEGORY,P.PRODUCT_CATEGORY_DESC "+
			" FROM( "+ 
			" SELECT "+ 
			" PRODUCT_CATEGORY, "+ 
			" PRODUCT_CATEGORY_DESC "+ 
			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+ 
			" WHERE (PRODUCT_CATEGORY LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(PRODUCT_CATEGORY_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		//------------------------------------------------------------
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


