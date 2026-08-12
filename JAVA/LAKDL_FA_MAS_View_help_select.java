import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_FA_MAS_View_help_select  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();

	//------------------------------------------------------------------------
	//-------------------------------------------------------------------------
	
	
	// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

	public String m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql="";
	public String m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql_Header="System Administration - Product Features ";	

	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql="";
	public String m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql_Header="System Administration - Product Category ";	
	
	public String m_help_DIV_TXT_CLIENT_GROUP_CODE_sql="";
	public String m_help_DIV_TXT_CLIENT_GROUP_CODE_sql_Header="System Administration - Client/Debtor Classification ";	
	
	public String m_help_DIV_TXT_FEES_CODE_sql="";
	public String m_help_DIV_TXT_FEES_CODE_sql_Header="System Administration - Fees ";	
	
	public String m_help_DIV_TXT_PRODUCT_CODE_sql="";
	public String m_help_DIV_TXT_PRODUCT_CODE_sql_Header="System Administration - Products ";	
	
	public String m_help_DIV_TXT_FEE_PACK_CODE_sql="";
	public String m_help_DIV_TXT_FEE_PACK_CODE_sql_Header="System Administration - Fee Structure";	
	
	public String m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql="";
	public String m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql_Header="System Administration - Exposure Category";	
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql_Header="System Administration - Collection Routes ";	
	
	public String m_view_TXT_FACTORING_DEFAULT_VALUES_sql="";
	public String m_view_TXT_FACTORING_DEFAULT_VALUES_sql_Header="System Administration - Factoring Default Values ";
	
	public String m_view_DIV_TXT_REMARK_CODE_sql="";
	public String m_view_DIV_TXT_REMARK_CODE_sql_Header="System Administration - Factoring Default Values ";
	
	//---------------------------------------------------------
	
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
		
	


		// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

		m_help_DIV_TXT_PRODUCT_FEATURES_CODE_sql=
			" SELECT L.NO ,L.FA_FEATURE_CODE,L.FA_FEATURE_DESC,L.FA_FEATURE_COMMENTS,L.BASIS_CODE,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_FEATURE_CODE,P.FA_FEATURE_DESC,P.FA_FEATURE_COMMENTS,P.BASIS_CODE,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " FA_FEATURE_CODE ,"+
			"	FA_FEATURE_DESC,"+
			"	NVL(FA_FEATURE_COMMENTS,'-') FA_FEATURE_COMMENTS,"+
			"	ACTIVE_STATUS,"+
			"	DEFAULT_VALUE,"+
			" BASIS_CODE "+
      " FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_PRODUCT_CATEGORY_CODE_sql=	
		  " SELECT L.NO ,L.PRODUCT_CATEGORY,L.PRODUCT_CATEGORY_DESC,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CATEGORY,P.PRODUCT_CATEGORY_DESC,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " PRODUCT_CATEGORY ,"+
			"	PRODUCT_CATEGORY_DESC,"+
			"	ACTIVE_STATUS "+
      " FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_CATEGORY "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
			//---------------------added by ashini on 26-02-2008------------
		m_view_DIV_TXT_REMARK_CODE_sql=	
		  " SELECT L.NO ,L.REMARK_CODE,L.REMARK_DESC,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REMARK_CODE,P.REMARK_DESC,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " REMARK_CODE,"+
			"	REMARK_DESC,"+
			"	ACTIVE_STATUS "+
      " FROM "+m_schema_name+".FA_CO_MAS_REMARKS "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	  //----end modifications done by ashini---------------------------		

		
		m_help_DIV_TXT_CLIENT_GROUP_CODE_sql=
			" SELECT L.NO ,L.GROUP_CODE,L.GROUP_DESC,L.GROUP_APPLI,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_DESC,P.GROUP_APPLI,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " GROUP_CODE,"+
			"	GROUP_DESC,"+
			"	GROUP_APPLI,"+
			"	ACTIVE_STATUS,"+
			"	DEFAULT_VALUE"+
      " FROM "+m_schema_name+".FA_CO_MAS_GROUPS "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEES_CODE_sql=
			" SELECT L.NO,L.FEE_CODE,L.FEE_DESC,L.FEE_TYPE,L.MINIUM_VALUE,L.APP_CLIENT,L.APP_DEBTOR,L.APP_ACT_LEDGER,L.APP_INACT_LEDGER,L.ACTIVE_STATUS,L.DEFAULT_VALUE,L.TAX_APPLICABILITY "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_CODE,P.FEE_DESC,P.FEE_TYPE,P.APP_CLIENT,P.APP_DEBTOR,P.APP_ACT_LEDGER,P.APP_INACT_LEDGER,P.MINIUM_VALUE,P.ACTIVE_STATUS,P.DEFAULT_VALUE,P.TAX_APPLICABILITY "+
			" FROM( "+
			" SELECT FEE_CODE,FEE_DESC,FEE_TYPE,APP_CLIENT,APP_DEBTOR,APP_ACT_LEDGER,APP_INACT_LEDGER,NVL(MINIUM_VALUE,0) MINIUM_VALUE,ACTIVE_STATUS,DEFAULT_VALUE,TAX_APPLICABILITY "+
  		" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_PRODUCT_CODE_sql=
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC,ACTIVE_STATUS,DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEE_PACK_CODE_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC,ACTIVE_STATUS,DEFAULT_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_EXPOSURE_CAT_CODE_sql=
		" SELECT L.NO,L.EXPOSURE_CODE,L.EXPOSURE_DESC,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EXPOSURE_CODE,P.EXPOSURE_DESC,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT EXPOSURE_CODE,EXPOSURE_DESC,ACTIVE_STATUS,DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BUSI_EXPOSURE_CAT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql=
		" SELECT L.NO,L.COLL_ROUTE_CODE,L.COLL_ROUTE_DESC,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COLL_ROUTE_CODE,P.COLL_ROUTE_DESC,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT COLL_ROUTE_CODE,COLL_ROUTE_DESC,ACTIVE_STATUS,DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_view_TXT_FACTORING_DEFAULT_VALUES_sql=
		" SELECT L.NO,L.CREDIT_LIMIT,L.CREDIT_PERIOD,L.TOLERANCE_CREDIT_PERIOD,L.RESERVE_MARGIN,L.INT_RATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CREDIT_LIMIT,P.CREDIT_PERIOD,P.TOLERANCE_CREDIT_PERIOD,P.RESERVE_MARGIN,P.INT_RATE "+
			" FROM( "+
			" SELECT A.CREDIT_LIMIT,A.CREDIT_PERIOD,A.TOLERANCE_CREDIT_PERIOD,A.RESERVE_MARGIN,A.INT_RATE "+
	  	" FROM "+m_schema_name+".FA_MK_PRO_QUOTA_DEFAULT A "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


