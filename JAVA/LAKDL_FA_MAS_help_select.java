import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MAS_help_select  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	
	public String m_help_DIV_TXT_DISPUTE_CODE_sql= "";
    public String m_help_DIV_TXT_DISPUTE_CODE_sql_Header= "System Administration - Dispute Codes"; 
	
	public String m_help_DIV_TXT_PRODUCT_FEATURES_DESC_sql= "";
	public String m_help_DIV_TXT_PRODUCT_FEATURES_DESC_sql_Header= "System Administration - Product Features ";

	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql= "";
	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql_Header= "System Administration - Product Category ";

	public String m_help_DIV_TXT_PRODUCT_CATEGORY_DESC_sql= "";
	public String m_help_DIV_TXT_PRODUCT_CATEGORY_DESC_sql_Header= "System Administration - Product Category ";
	
	public String m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql= "";
	public String m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql_Header= "System Administration - Product Features ";
	
	public String m_help_DIV_TXT_CLIENT_GROUP_CODE_sql= "";
	public String m_help_DIV_TXT_CLIENT_GROUP_CODE_sql_Header= "System Administration - Client/Debtor Classification ";
	
	public String m_help_DIV_TXT_CLIENT_GROUP_DESC_sql= "";
	public String m_help_DIV_TXT_CLIENT_GROUP_DESC_sql_Header= "System Administration - Client/Debtor Classification ";
	
	public String m_help_DIV_TXT_FEES_CODE_sql= "";
	public String m_help_DIV_TXT_FEES_CODE_sql_Header= "System Administration - Fees ";
	
	public String m_help_DIV_TXT_FEES_DESC_sql= "";
	public String m_help_DIV_TXT_FEES_DESC_sql_Header= "System Administration - Fees ";
	
	public String m_help_DIV_TXT_PRODUCT_CODE_sql="";
	public String m_help_DIV_TXT_PRODUCT_CODE_sql_Header= "System Administration - Products ";
	
	public String m_help_DIV_TXT_PRODUCT_DESC_sql="";
	public String m_help_DIV_TXT_PRODUCT_DESC_sql_Header= "System Administration - Products ";
	
	public String m_help_DIV_TXT_FEE_PACK_CODE_sql="";
	public String m_help_DIV_TXT_FEE_PACK_CODE_sql_Header= "System Administration - Fee Structure ";
	
	public String m_help_DIV_TXT_FEE_PACK_DESC_sql="";
	public String m_help_DIV_TXT_FEE_PACK_DESC_sql_Header= "System Administration - Fee Structure ";
	
	public String m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql="";
	public String m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql_Header= "System Administration - Exposure Category ";

	public String m_help_DIV_TXT_EXPOSURE_CAT_DESC_sql="";
	public String m_help_DIV_TXT_EXPOSURE_CAT_DESC_sql_Header= "System Administration - Exposure Category ";
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql_Header="System Administration - Collection Routes ";

	public String m_help_DIV_TXT_COLLECTION_ASSIGN_ROUTE_CODE_sql="";
	public String m_help_DIV_TXT_COLLECTION_ASSIGN_ROUTE_CODE_sql_Header="System Administration - Collection Routes ";
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_sql_Header="System Administration - Collection Routes Assign ";
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_EDIT_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_EDIT_sql_Header="System Administration - Collection Routes Assign ";
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_DESC_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_DESC_sql_Header="System Administration - Collection Routes ";
	
	public String m_help_DIV_TXT_CLIENT_CODE_QUOTATION_sql="";
	public String m_help_DIV_TXT_CLIENT_CODE_QUOTATION_sql_Header=" Marketing - Client for Quotation ";
	
	public String m_help_DIV_TXT_PRODUCT_CODE_QUOTATION_sql="";
	public String m_help_DIV_TXT_PRODUCT_CODE_QUOTATION_sql_Header=" System Administration - Products ";
	
	public String m_help_DIV_TXT_FEE_CODE_QUOTATION_sql="";
	public String m_help_DIV_TXT_FEE_CODE_QUOTATION_sql_Header=" System Administration - Fees ";
	
	public String m_help_DIV_TXT_INQUERY_CODE_QUOTATION_sql="";
	public String m_help_DIV_TXT_INQUERY_CODE_QUOTATION_sql_Header=" Marketing - Inqury ";
	
	public String m_help_DIV_TXT_COLLECTION_AREA_sql="";
	public String m_help_DIV_TXT_COLLECTION_AREA_sql_Header=" System Administration - Collection Areas ";
	
	public String m_help_DIV_TXT_QUOTATION_PRINT_sql="";
	public String m_help_DIV_TXT_QUOTATION_PRINT_sql_Header=" Marketing - Client for Quotation ";
	
	public String m_help_DIV_TXT_REMARK_CODE_sql="";
	public String m_help_DIV_TXT_REMARK_CODE_sql_Header="System Administration - Remarks";	

	
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
		
		m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql=
			" SELECT L.NO ,L.FA_FEATURE_CODE,L.FA_FEATURE_DESC,L.FA_FEATURE_COMMENTS,L.DEFAULT_VALUE,L.BASIS_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_FEATURE_CODE,P.FA_FEATURE_DESC,P.FA_FEATURE_COMMENTS,P.DEFAULT_VALUE,P.BASIS_CODE "+
			" FROM( "+ 
			" SELECT "+ 
			" FA_FEATURE_CODE, "+ 
			" FA_FEATURE_DESC, "+ 
			" NVL(FA_FEATURE_COMMENTS,'-') FA_FEATURE_COMMENTS, "+ 
			" DEFAULT_VALUE,BASIS_CODE "+
			" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+ 
			" WHERE (FA_FEATURE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(FA_FEATURE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql=	
		  " SELECT L.NO ,L.PRODUCT_CATEGORY,L.PRODUCT_CATEGORY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CATEGORY,P.PRODUCT_CATEGORY_DESC "+
			" FROM( "+ 
			" SELECT "+ 
			" PRODUCT_CATEGORY, "+ 
			" PRODUCT_CATEGORY_DESC "+ 
			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+ 
			" WHERE (PRODUCT_CATEGORY LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(PRODUCT_CATEGORY_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//---------------------added by ashini on 26-02-2008------------
		m_help_DIV_TXT_REMARK_CODE_sql=	
		  " SELECT L.NO ,L.REMARK_CODE,L.REMARK_DESC,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REMARK_CODE,P.REMARK_DESC,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " REMARK_CODE,"+
			"	REMARK_DESC,"+
			"	ACTIVE_STATUS "+
      " FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
			" WHERE (REMARK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(REMARK_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(1)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	  //----end modifications done by ashini---------------------------		
		
		m_help_DIV_TXT_PRODUCT_CATEGORY_DESC_sql=	
		  " SELECT L.NO ,L.PRODUCT_CATEGORY,L.PRODUCT_CATEGORY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CATEGORY,P.PRODUCT_CATEGORY_DESC "+
			" FROM( "+ 
			" SELECT "+ 
			" PRODUCT_CATEGORY, "+ 
			" PRODUCT_CATEGORY_DESC "+ 
			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+ 
			" WHERE UPPER(PRODUCT_CATEGORY_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_DISPUTE_CODE_sql=
            " SELECT L.NO ,L.DISPUTE_CODE \"Dispute Code\",L.DISPUTE_DESC \"Dispute Description\",NVL(L.ACTIVATION_POINT,'-') \"Activation Point\" "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.DISPUTE_CODE,P.DISPUTE_DESC,P.ACTIVATION_POINT "+
            " FROM( "+ 
            " SELECT "+ 
            " DISPUTE_CODE, "+ 
            " DISPUTE_DESC, "+ 
            " ACTIVATION_POINT "+ 
            " FROM "+m_schema_name+".fa_co_mas_dispute_code"+ 
            //modified by madhawa 2020-03-19
            //" WHERE (DISPUTE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(DISPUTE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
            " WHERE (DISPUTE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DISPUTE_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//System.out.println("test"+m_help_DIV_TXT_DISPUTE_CODE_sql);
		
		
		m_help_DIV_TXT_PRODUCT_FEATURES_DESC_sql=
			" SELECT L.NO ,L.FA_FEATURE_CODE,L.FA_FEATURE_DESC,L.FA_FEATURE_COMMENTS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_FEATURE_CODE,P.FA_FEATURE_DESC,P.FA_FEATURE_COMMENTS,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+ 
			" FA_FEATURE_CODE, "+ 
			" FA_FEATURE_DESC, "+ 
			" NVL(FA_FEATURE_COMMENTS,'-') FA_FEATURE_COMMENTS, "+ 
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+ 
			" WHERE UPPER(FA_FEATURE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	


		
		m_help_DIV_TXT_CLIENT_GROUP_CODE_sql=
			" SELECT L.NO ,L.GROUP_CODE,L.GROUP_DESC,L.GROUP_APPLI GROUP_TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_DESC,P.GROUP_APPLI,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+ 
			" GROUP_CODE, "+ 
			" GROUP_DESC, "+ 
			" GROUP_APPLI, "+ 
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+ 
			" WHERE (GROUP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(GROUP_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_CLIENT_GROUP_DESC_sql=
		  " SELECT L.NO ,L.GROUP_CODE,L.GROUP_DESC,L.GROUP_APPLI GROUP_TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_DESC,P.GROUP_APPLI,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+ 
			" GROUP_CODE, "+ 
			" GROUP_DESC, "+ 
			" GROUP_APPLI, "+ 
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+ 
			" WHERE  UPPER(GROUP_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		m_help_DIV_TXT_FEES_CODE_sql=		
			" SELECT L.NO,L.FEE_CODE,L.FEE_DESC,L.FEE_TYPE,L.MINIUM_VALUE MINIMUM_VALUE ,L.DEFAULT_VALUE,L.APP_CLIENT,L.APP_DEBTOR,L.APP_ACT_LEDGER,L.APP_INACT_LEDGER,L.CAL_BASIS,L.FEE_RATIO,L.ACTIVATION_POINT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_CODE,P.FEE_DESC,P.FEE_TYPE,P.MINIUM_VALUE,P.DEFAULT_VALUE,P.APP_CLIENT,P.APP_DEBTOR,P.APP_ACT_LEDGER,P.APP_INACT_LEDGER,P.CAL_BASIS,P.FEE_RATIO,P.ACTIVATION_POINT "+
			" FROM( "+
			" SELECT FEE_CODE,FEE_DESC,FEE_TYPE,NVL(MINIUM_VALUE,0) MINIUM_VALUE,DEFAULT_VALUE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,CAL_BASIS,FEE_RATIO,ACTIVATION_POINT "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			" WHERE (FEE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(FEE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEES_DESC_sql=
		  " SELECT L.NO,L.FEE_CODE,L.FEE_DESC,L.FEE_TYPE,L.MINIUM_VALUE,L.DEFAULT_VALUE,L.APP_CLIENT,L.APP_DEBTOR,L.APP_ACT_LEDGER,L.APP_INACT_LEDGER,L.CAL_BASIS,L.FEE_RATIO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_CODE,P.FEE_DESC,P.FEE_TYPE,P.MINIUM_VALUE,P.DEFAULT_VALUE,P.APP_CLIENT,P.APP_DEBTOR,P.APP_ACT_LEDGER,P.APP_INACT_LEDGER,P.CAL_BASIS,P.FEE_RATIO "+
			" FROM( "+
			" SELECT FEE_CODE,FEE_DESC,FEE_TYPE,NVL(MINIUM_VALUE,0) MINIUM_VALUE,DEFAULT_VALUE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,CAL_BASIS,FEE_RATIO "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			" WHERE UPPER(FEE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		  
		m_help_DIV_TXT_PRODUCT_CODE_sql=		
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			" WHERE (FA_PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(FA_PRODUCT_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_PRODUCT_DESC_sql=
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			" WHERE UPPER(FA_PRODUCT_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_FEE_PACK_CODE_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			" WHERE (FEE_PACK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(FEE_PACK_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEE_PACK_DESC_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			" WHERE  UPPER(FEE_PACK_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

		m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql=
		" SELECT L.NO,L.COLL_ROUTE_CODE,L.COLL_ROUTE_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COLL_ROUTE_CODE,P.COLL_ROUTE_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			" WHERE (COLL_ROUTE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(COLL_ROUTE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_COLLECTION_ASSIGN_ROUTE_CODE_sql=
		" SELECT L.NO,L.COLL_ROUTE_CODE,L.COLL_ROUTE_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COLL_ROUTE_CODE,P.COLL_ROUTE_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			" WHERE (COLL_ROUTE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND UPPER(COLL_ROUTE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"	AND  COLL_ROUTE_CODE NOT IN (SELECT COLL_ROUTE_CODE FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN )"+ 
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_sql=
		" SELECT L.NO,L.ROUTE_OFFICER_CODE,L.ROUTE_OFFICER_NAME,L.CONTACT_NO,L.DESIGNATION_CODE,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ROUTE_OFFICER_CODE,P.ROUTE_OFFICER_NAME,P.CONTACT_NO,P.DESIGNATION_CODE,P.ID_NO "+
			" FROM( "+
			"	SELECT A.EMP_CODE ROUTE_OFFICER_CODE,A.TITLE || ' ' || A.FIRST_NAME || ' ' || A.LAST_NAME ROUTE_OFFICER_NAME,A.CONTACT_NO,A.DESIGNATION_CODE,A.ID_NO "+
			"	FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A "+
			"	WHERE ( UPPER(A.EMP_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.FIRST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.LAST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(1)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_COLLECTION_ROUTE_EMP_CODE_EDIT_sql=
		" SELECT L.NO,L.ROUTE_OFFICER_CODE,L.ROUTE_OFFICER_NAME,L.CONTACT_NO,L.DESIGNATION_CODE,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ROUTE_OFFICER_CODE,P.ROUTE_OFFICER_NAME,P.CONTACT_NO,P.DESIGNATION_CODE,P.ID_NO "+
			" FROM( "+
			"	SELECT DISTINCT A.EMP_CODE ROUTE_OFFICER_CODE,A.TITLE || ' ' || A.FIRST_NAME || ' ' || A.LAST_NAME ROUTE_OFFICER_NAME,A.CONTACT_NO,A.DESIGNATION_CODE,A.ID_NO "+
			"	FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A,"+m_schema_name+".AF_CO_MAS_COLL_ROUTES B,"+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN C "+
			"	WHERE ( UPPER(A.EMP_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.FIRST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.LAST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			" AND B.COLL_ROUTE_CODE=C.COLL_ROUTE_CODE "+
			" AND C.EMP_CODE = A.EMP_CODE "+
			" AND C.ACTIVE_STATUS LIKE ('"+m_vector.elementAt(1)+"%')"+
			//" AND A.EMP_CODE IN (SELECT EMP_CODE FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
		m_help_DIV_TXT_COLLECTION_ROUTE_DESC_sql=
			" SELECT L.NO,L.COLL_ROUTE_CODE,L.COLL_ROUTE_DESC,DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COLL_ROUTE_CODE,P.COLL_ROUTE_DESC,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,DEFAULT_VALUE "+
 			" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			" WHERE  UPPER(COLL_ROUTE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_CLIENT_CODE_QUOTATION_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.FULL_NAME,L.INQUIRY_CODE "+
			" FROM( "+
			" SELECT ROWNUM NO,P.CLIENT_CODE,P.CLIENT_TYPE,P.FULL_NAME,P.INQUIRY_CODE "+
			" FROM( "+
			" SELECT A.CLIENT_CODE,DECODE(A.CLIENT_TYPE,'I','INDIVIDUAL','CORPERATE') CLIENT_TYPE,A.FULL_NAME,NVL(B.INQUIRY_CODE,' ') INQUIRY_CODE"+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO B "+
			" WHERE ( "+
			" UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" UPPER(A.REGISTERED_TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.REGISTERED_MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.PASSPORT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) AND "+
			" A.ACTIVE_STATUS IN('I','Y') AND A.FACTORING_TYPE IN('C','B') AND A.CLIENT_CODE=B.CLIENT_CODE(+) "+
			" )P "+
			" ORDER BY P.CLIENT_CODE "+
			" )L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";


		m_help_DIV_TXT_PRODUCT_CODE_QUOTATION_sql=
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC "+
 			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			" WHERE (FA_PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(FA_PRODUCT_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_FEE_CODE_QUOTATION_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			" WHERE (FEE_PACK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(FEE_PACK_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_INQUERY_CODE_QUOTATION_sql=
			" SELECT L.NO,L.INQUIRY_CODE,L.CLIENT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME "+
			" FROM( "+
			" SELECT INQUIRY_CODE,CLIENT_NAME "+
 			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			" WHERE (INQUIRY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR CLIENT_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND DIVISION_CODE='FA' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql=
			" SELECT L.NO,L.EXPOSURE_CODE,L.EXPOSURE_DESC,L.DEFAULT_VALUE,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EXPOSURE_CODE,P.EXPOSURE_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+
			" SELECT EXPOSURE_CODE,EXPOSURE_DESC,DEFAULT_VALUE,ACTIVE_STATUS "+
 			" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			" WHERE (EXPOSURE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND upper(EXPOSURE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%'))   AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	 m_help_DIV_TXT_EXPOSURE_CAT_DESC_sql=
			" SELECT L.NO,L.EXPOSURE_CODE,L.EXPOSURE_DESC,L.DEFAULT_VALUE,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EXPOSURE_CODE,P.EXPOSURE_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+
			" SELECT EXPOSURE_CODE,EXPOSURE_DESC,DEFAULT_VALUE,ACTIVE_STATUS "+
 			" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			" WHERE upper(EXPOSURE_DESC) LIKE UPPER('"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";		
		
		m_help_DIV_TXT_COLLECTION_AREA_sql=
		" SELECT L.NO ,L.AREA_CODE, L.AREA_DESC,L.CITY_CODE,L.CITY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE, P.AREA_DESC,P.CITY_CODE,P.CITY_DESC "+
			" FROM( "+
			" SELECT A.AREA_CODE,A.AREA_DESC, A.CITY_CODE,B.CITY_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_AREA A,"+m_schema_name+".AF_CO_MAS_CITY B "+
			" WHERE A.CITY_CODE=B.CITY_CODE AND A.AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS LIKE ('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_QUOTATION_PRINT_sql=
			" SELECT L.NO,L.QUOTATION_NO,L.CLIENT_CODE,L.FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.QUOTATION_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			" SELECT QUOTATION_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) FULL_NAME "+
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+
			" WHERE QUOTATION_STATUS='Y' AND "+
			" (QUOTATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//------------------------------------------------------------
		//------------------------------------------------------------
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


