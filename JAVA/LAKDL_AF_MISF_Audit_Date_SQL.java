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

public class LAKDL_AF_MISF_Audit_Date_SQL  
 {
	//java.text.NumberFormat nf;
			
 public String met_unformat_number(String numstr) {
			
			String m_number="";
			for (int i = 0; i < numstr.length(); i++) {
				String oneChar = numstr.substring(i,i+1);
				if (!oneChar.equals(",")) {
					m_number=m_number+oneChar;
				}
			}
      //return nf.format(numstr);
      return m_number;

 }
	
 public String getQuery(String Schema,String FDate,String TDate,String Type) {
	
   if (Type.equals("AF_AD_CUSTOMER_CATEGORY")){
		return " SELECT CAT_TYPE_CODE \"Category\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CAT_TYPE_CODE, A.DESCRIPTION "+
					 "        FROM "+Schema+".AF_MK_MAS_CUSTOMER_CATOGORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CAT_TYPE_CODE, A.DESCRIPTION "+
					 "        FROM  "+Schema+".AF_MK_MAS_CUSTOMER_CATOGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CAT_TYPE_CODE "+	
					 " ORDER BY	CAT_TYPE_CODE DESC";
						
						
  }else if(Type.equals("AF_AD_ITEM_CATEGORY")){ //MK
		return" SELECT ITEM_CAT_CODE \"Category\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ITEM_CAT_CODE, A.DESCRIPTION "+
					 "        FROM "+Schema+".AF_CO_MAS_ITEM_CATEGORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ITEM_CAT_CODE, A.DESCRIPTION "+
					 "        FROM  "+Schema+".AF_CO_MAS_ITEM_CATEGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ITEM_CAT_CODE "+	
					 " ORDER BY	ITEM_CAT_CODE DESC";
	
	  }else if(Type.equals("AF_AD_ITEM_SUB_CATEGORY")){ //MK
		return" SELECT ITEM_SUB_CAT \"Sub Category\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ITEM_SUB_CAT, A.DESCRIPTION "+
					 "        FROM "+Schema+".AF_CO_MAS_ITEM_SUB_CATEGORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ITEM_SUB_CAT, A.DESCRIPTION "+
					 "        FROM  "+Schema+".AF_CO_MAS_ITEM_SUB_CATEGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ITEM_SUB_CAT "+	
					 " ORDER BY	ITEM_SUB_CAT DESC";
	
		}else if(Type.equals("AF_AD_MODEL_CREATION")){ //MK
		return" SELECT MODEL_CODE \"Model Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.MODEL_CODE, A.DESCRIPTION "+
					 "        FROM "+Schema+".AF_CO_MAS_MODEL A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.MODEL_CODE, A.DESCRIPTION "+
					 "        FROM  "+Schema+".AF_CO_MAS_MODEL_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY MODEL_CODE "+	
					 " ORDER BY	MODEL_CODE DESC";
	
	
	}else if(Type.equals("AF_AD_CLIENT_CLASSIFICATION")){ //SJ
		return" SELECT SCORE_MODEL_CODE \"Score Model\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE "+
					 "        FROM "+Schema+".AF_CR_PRO_CRSCORE_EVALTION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE "+
					 "        FROM  "+Schema+".AF_CR_PRO_CRSCORE_EVALTION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCORE_MODEL_CODE "+	
					 " ORDER BY	SCORE_MODEL_CODE DESC";
						
		}else if(Type.equals("AF_AD_YARD_CREATION")){ //SJ
		return" SELECT YARD_CODE \"Yard Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.YARD_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_YARD A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.YARD_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_YARD_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY YARD_CODE "+	
					 " ORDER BY	YARD_CODE DESC";
						
		}else if(Type.equals("AF_AD_LAWYER")){ //SJ
		return" SELECT LAWYER_CODE \"Lawyer Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.LAWYER_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_LAWYER A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.LAWYER_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LAWYER_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY LAWYER_CODE "+	
					 " ORDER BY	LAWYER_CODE DESC";
		
		}else if(Type.equals("AF_AD_SEIZER")){ //SJ
		return" SELECT SEIZER_CODE \"Seizer Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SEIZER_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_SEIZER A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SEIZER_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SEIZER_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SEIZER_CODE "+	
					 " ORDER BY	SEIZER_CODE DESC";
						
				}else if(Type.equals("AF_AD_MISSING_VEHICLE")){ //SJ
		return" SELECT VEHICLE_NO \"Vehicle No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.VEHICLE_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_MISSING_VEHICLES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.VEHICLE_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_MISSING_VEHICLES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY VEHICLE_NO "+	
					 " ORDER BY	VEHICLE_NO DESC";
						
			/*}else if(Type.equals("AF_AD_CLIENT_BACKLIST")){ //SJ
		return" SELECT CLIENT_CODE \"Client Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CLIENT_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE A.ACTIVE_STATUS ='B' AND (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT  A.CLIENT_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A,"+Schema+".AF_CO_MAS_CLIENT B "+
					 "        WHERE A.CLIENT_CODE = B.CLIENT_CODE AND B.ACTIVE_STATUS='B' AND (B.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND B.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (B.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND B.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CLIENT_CODE "+	
					 " ORDER BY	CLIENT_CODE DESC";*/
		
		}else if(Type.equals("FA_CO_MAS_PRODUCT")){ //SJ
		return" SELECT FA_PRODUCT_CODE \"Product Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FA_PRODUCT_CODE "+
					 "        FROM "+Schema+".FA_CO_MAS_PRODUCT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FA_PRODUCT_CODE "+
					 "        FROM  "+Schema+".FA_CO_MAS_PRODUCT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FA_PRODUCT_CODE "+	
					 " ORDER BY	FA_PRODUCT_CODE DESC";
		
		}else if(Type.equals("FA_CO_MAS_FEES")){ //SJ
		return" SELECT FEE_CODE \"Fee Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FEE_CODE "+
					 "        FROM "+Schema+".FA_CO_MAS_FEES  A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FEE_CODE "+
					 "        FROM  "+Schema+".FA_CO_MAS_FEES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FEE_CODE "+	
					 " ORDER BY	FEE_CODE DESC";
		
		}else if(Type.equals("FA_CO_MAS_PRODUCT_CATEGORY")){ //SJ
		return" SELECT PRODUCT_CATEGORY \"Product Category\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PRODUCT_CATEGORY "+
					 "        FROM "+Schema+".FA_CO_MAS_PRODUCT_CATEGORY  A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PRODUCT_CATEGORY "+
					 "        FROM  "+Schema+".FA_CO_MAS_PRODUCT_CATEGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PRODUCT_CATEGORY "+	
					 " ORDER BY	PRODUCT_CATEGORY DESC";
	
	}else if(Type.equals("AF_AD_DISCOUNT_RATE")){ //SJ
		return" SELECT RATE \"Rate\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.RATE "+
					 "        FROM "+Schema+".AF_CO_MAS_DISCOUNT_RATE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.RATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_DISCOUNT_RATE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY RATE"+	
					 " ORDER BY	RATE DESC";
	
	}else if(Type.equals("AF_AD_TERMINATION_RATE")){ //SJ
		return" SELECT RATE \"Rate\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.MIN_CHARGE||'-'||A.MAX_CHARGES RATE"+
					 "        FROM "+Schema+".AF_CO_MAS_TERMNATION_RATE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.MIN_CHARGE||'-'||A.MAX_CHARGES RATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TERMNATION_RATE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY RATE "+	
					 " ORDER BY	1 DESC";
	
	}else if(Type.equals("FA_CO_MAS_PROD_FEATURES")){ //SJ
		return" SELECT FA_FEATURE_CODE \"Feature Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FA_FEATURE_CODE "+
					 "        FROM "+Schema+".FA_CO_MAS_PROD_FEATURES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FA_FEATURE_CODE "+
					 "        FROM  "+Schema+".FA_CO_MAS_PROD_FEATURES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FA_FEATURE_CODE"+	
					 " ORDER BY	FA_FEATURE_CODE DESC";
	
	 }else if(Type.equals("FA_CO_MAS_COLLECTION_ROUTES")){ //SJ
		return" SELECT COLL_ROUTE_CODE \"Route Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.COLL_ROUTE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_COLL_ROUTES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.COLL_ROUTE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_COLL_ROUTES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY COLL_ROUTE_CODE "+	
					 " ORDER BY	COLL_ROUTE_CODE DESC";
	
	 }else if(Type.equals("FA_CO_MAS_COLLECTION_ROUTES_ASSIGN")){ //SJ
		return" SELECT COLL_ROUTE_CODE \"Route Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.COLL_ROUTE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_COLL_ROUT_ASSIGN A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.COLL_ROUTE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_COLL_ROUT_ASSIGN_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY COLL_ROUTE_CODE "+	
					 " ORDER BY	COLL_ROUTE_CODE DESC";
	
		}else if(Type.equals("AF_AD_SUB_DIVISION")){ //LK
		return" SELECT SUB_DIVISION_CODE \"Sub Div Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUB_DIVISION_CODE "+
					 "        FROM "+Schema+".CO_CO_MAS_SUB_DIVISION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_DIVISION_CODE "+
					 "        FROM  "+Schema+".CO_CO_MAS_SUB_DIVISION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_DIVISION_CODE "+	
					 " ORDER BY	SUB_DIVISION_CODE DESC";
						
						
		}else if(Type.equals("AF_AD_LOCATION")){ //LK
		return" SELECT LOCATION_CODE \"Location Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.LOCATION_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_LOCATION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.LOCATION_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOCATION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY LOCATION_CODE "+	
					 " ORDER BY	LOCATION_CODE DESC";
						
						
	}else if(Type.equals("AF_AD_MAKE_CREATION")){ //MK
		return" SELECT MAKE_CODE \"Make No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.MAKE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_MAKE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_MAKE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY MAKE_CODE "+	
					 " ORDER BY	MAKE_CODE DESC";
	
		}else if(Type.equals("AF_AD_ENGINE_CAPACITY")){ //MK
		return" SELECT CAPACITY_CODE \"Capacity Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CAPACITY_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_ENGINE_CAPACITY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CAPACITY_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ENGINE_CAPACITY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CAPACITY_CODE "+	
					 " ORDER BY	CAPACITY_CODE DESC";
	}
	else if(Type.equals("AF_CO_MAS_REFERNCE_CONDITIONS")){ // DJ
	
		return" SELECT CODE \"Condition Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE "+
					 "        FROM "+Schema+".AF_MK_CONDITIONS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE "+
					 "        FROM  "+Schema+".AF_MK_CONDITIONS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE "+	
					 " ORDER BY	CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_PHONE_AREA_CODE")){ // DJ-1
	
		return" SELECT PHONE_AREA_CODE \"Phone Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PHONE_AREA_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_PHONE_CODES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PHONE_AREA_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_PHONE_CODES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PHONE_AREA_CODE "+	
					 " ORDER BY	PHONE_AREA_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_BRANCH")){ // DJ-2
	
		return" SELECT BRANCH_CODE \"Branch Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.BRANCH_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_BANK_BRANCH A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.BRANCH_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_BANK_BRANCH_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BRANCH_CODE "+	
					 " ORDER BY	BRANCH_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_USER_LIST")){ // DJ-3
	
		return" SELECT USER_ID \"User ID\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.USER_ID "+
					 "        FROM "+Schema+".CO_CO_MAS_USER_ACCESS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID "+
					 "        FROM  "+Schema+".CO_CO_MAS_USER_ACCESS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USER_ID "+	
					 " ORDER BY	USER_ID DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_DIVISION")){ // DJ-4
	
		return" SELECT DIVISION_CODE \"Division Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.DIVISION_CODE "+
					 "        FROM "+Schema+".CO_CO_MAS_DIVISION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.DIVISION_CODE "+
					 "        FROM  "+Schema+".CO_CO_MAS_DIVISION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY DIVISION_CODE "+	
					 " ORDER BY	DIVISION_CODE DESC";
						
						
	}
	
	
	else if(Type.equals("AF_AD_LEAD_SOURCE_CATEGORY")){ // DJ-5
	
		return" SELECT SOURCE_CODE \"Source Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SOURCE_CODE "+
					 "        FROM "+Schema+".AF_MK_MAS_LEAD_SOURCE_CAT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SOURCE_CODE "+
					 "        FROM  "+Schema+".AF_MK_MAS_LEAD_SOURCE_CAT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SOURCE_CODE "+	
					 " ORDER BY	SOURCE_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_ENGINE_CAPACITY")){ // DJ-6
	
		return" SELECT CAPACITY_CODE \"Capacity Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CAPACITY_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_ENGINE_CAPACITY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CAPACITY_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ENGINE_CAPACITY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CAPACITY_CODE "+	
					 " ORDER BY	CAPACITY_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_APPLICABLE_CHARGES")){ // DJ-7
	
		return" SELECT SUB_TYPE_CODE \"Sub Type Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUB_TYPE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CHARGES_APPLICABLE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TYPE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CHARGERS_APPLI_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_TYPE_CODE "+	
					 " ORDER BY	SUB_TYPE_CODE DESC";
						
						
	}
	
  else if(Type.equals("AF_AD_CLIENT_GROUP")){ // DJ-8
	
		return" SELECT GROUP_ID \"Group Id\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GROUP_ID "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT_GROUP A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_ID "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_GROUP_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GROUP_ID "+	
					 " ORDER BY	GROUP_ID DESC";
						
						
	}
  
	else if(Type.equals("AF_AD_DOCUMENT_APPLICABLE")){ // DJ-9
	
		return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_DOCUMENT_APPLICABLE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_DOC_APPLICABLE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE "+	
					 " ORDER BY	CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_SCORE_CATEGORY")){ // DJ-10
	
		return" SELECT SCORE_CODE \"Score Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SCORE_CODE "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_CATEGORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_CODE "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_CATEGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCORE_CODE "+	
					 " ORDER BY	SCORE_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_RMV_AGENTS")){ // DJ-11
	
		return" SELECT RMV_AGENT_CODE \"Agent Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.RMV_AGENT_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_RMV_AGENTS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.RMV_AGENT_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_RMV_AGENTS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY RMV_AGENT_CODE "+	
					 " ORDER BY	RMV_AGENT_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_LOAN_FACILITIES")){ // DJ-12
	
		return" SELECT LOAN_FACILITY_NO \"Loan Facility Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.LOAN_FACILITY_NO "+
					 "        FROM "+Schema+".AF_CO_MAS_LOAN_FACILITIES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.LOAN_FACILITY_NO "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOAN_FACILITIES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY LOAN_FACILITY_NO "+	
					 " ORDER BY	LOAN_FACILITY_NO DESC";				
	}
	
	else if(Type.equals("AF_AD_FOLLOW_UP_ACTION")){ // DJ-13
	
		return" SELECT CATEGORY_CODE \"Category Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CATEGORY_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_FOLLOWUP_CATEGORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CATEGORY_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_FOLLOWUP_CATEGORY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CATEGORY_CODE "+	
					 " ORDER BY	CATEGORY_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_ASSIGN_MEMBERS")){ // DJ-14
	
		return" SELECT TEAM_ID \"Team Id\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TEAM_ID "+
					 "        FROM "+Schema+".AF_CO_MAS_TEAM_MEMBERS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID "+
					 "        FROM  "+Schema+".AF_CO_MAS_TEAM_MEMBERS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TEAM_ID "+	
					 " ORDER BY	TEAM_ID DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_CLIENT_CREATION")){ //DJ-15
	
		return" SELECT CLIENT_CODE \"Client Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CLIENT_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CLIENT_CODE"+	
					 " ORDER BY CLIENT_CODE DESC";
						
						
	}
	else if(Type.equals("AF_CO_MAS_EXPOSURE_CODE")){ // CJ
		return" SELECT EXPOSURE_CODE \"Exposure Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.EXPOSURE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_BUSI_EXPOSURE_CAT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.EXPOSURE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_BUSI_EXPOSURE_CAT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY EXPOSURE_CODE "+	
					 " ORDER BY	EXPOSURE_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_CURRENCY")){ // CJ
		return" SELECT CURR_CODE \"Currency Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CURR_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CURRENCY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CURR_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CURRENCY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CURR_CODE "+	
					 " ORDER BY CURR_CODE DESC";
						
						
	}

        
  else if(Type.equals("AF_AD_DISTRICT")){ // CJ
		return" SELECT DISTRICT_CODE \"District Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.DISTRICT_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_DISTRICT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.DISTRICT_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_DISTRICT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY DISTRICT_CODE "+	
					 " ORDER BY	DISTRICT_CODE DESC";
						
						
	}


	
	else if(Type.equals("AF_AD_BANK")){ // TJ
		return" SELECT BANK_CODE \"Bank Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.BANK_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_BANKS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.BANK_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_BANKS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BANK_CODE "+	
					 " ORDER BY	BANK_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_PROVINCE")){ // TJ
		return" SELECT PROVINCE_CODE \"Province Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PROVINCE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_PROVINCE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PROVINCE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_PROVINCE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PROVINCE_CODE "+	
					 " ORDER BY	PROVINCE_CODE DESC";
						
						
	}

	else if(Type.equals("AF_AD_SUB_BUSINESS_SECTORS")){ // TJ
		return" SELECT SUB_CODE \"Sub Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUB_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_BUSINESS_SECTORS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_BUSI_SECTORS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_CODE "+	
					 " ORDER BY	SUB_CODE DESC";
						
						
	}

  else if(Type.equals("AF_AD_EMPLOYEE")){ // TJ
		return" SELECT EMP_CODE \"Employee Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.EMP_CODE "+
					 "        FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.EMP_CODE "+
					 "        FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY EMP_CODE "+	
					 " ORDER BY	EMP_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_ASSIGN_SUB_TEAMS")){ // TJ
		return" SELECT TEAM_ID \"Team Id\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TEAM_ID "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_TEAMS_ASSIGN A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_TEAMS_ASSIGN_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TEAM_ID "+	
					 " ORDER BY	TEAM_ID DESC";
						
						
	}
  
		else if(Type.equals("AF_AD_REPAYMENT_INERVAL")){ // TJ
		return" SELECT DURATION \"Duration\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.DURATION "+
					 "        FROM "+Schema+".AF_CO_MAS_REPAYMENT_INTERVAL A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.DURATION "+
					 "        FROM  "+Schema+".AF_CO_MAS_REPAYMENT_INTER_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY DURATION "+	
					 " ORDER BY	DURATION DESC";
						
						
	}
	else if(Type.equals("AF_AD_SUB_CHARGE")){ // TJ
		return" SELECT SUB_TYPE_CODE \"Sub Type Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUB_TYPE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_CHARGES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TYPE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_CHARGES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_TYPE_CODE "+	
					 " ORDER BY	SUB_TYPE_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_TRANSACTION")){ // TJ
		return" SELECT TRAN_CODE \"Transaction Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TRAN_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_TRANSACTION_TYPE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TRAN_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TRANSACTION_TYPE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TRAN_CODE "+	
					 " ORDER BY	TRAN_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_ASSET_USAGE_TYPE")){ // TJ
		return" SELECT USAGE_TYPE \"Usage Type\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.USAGE_TYPE "+
					 "        FROM "+Schema+".AF_CO_MAS_ASSET_USAGE_TYPE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.USAGE_TYPE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ASSET_USAGE_TYPE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USAGE_TYPE "+	
					 " ORDER BY	USAGE_TYPE DESC";
						
						
	}

 else if(Type.equals("AF_AD_GARAGE")){ // TJ
		return" SELECT GARAGE_CODE \"Garage Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GARAGE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_GARAGE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GARAGE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_GARAGE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GARAGE_CODE "+	
					 " ORDER BY	GARAGE_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_INCOME_EXPENCE_TYPE")){ // TJ
		return" SELECT I_E_CODE \"Income Expence Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.I_E_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_INCOME_EXPENCE_TYPE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.I_E_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_INCOME_EXP_TYPE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY I_E_CODE "+	
					 " ORDER BY	I_E_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_LICENCEE_SETTLEMENT")){ // TJ
		return" SELECT ACC_NO \"Account No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ACC_NO "+
					 "        FROM "+Schema+".AF_CO_MAS_LICENCEE_SETTLEMENT A "+
					 "        WHERE (DN_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND DN_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ACC_NO "+
					 "        FROM  "+Schema+".AF_CO_MAS_LICENCEE_SETTLEME_BK A "+
					 "        WHERE (DN_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND DN_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ACC_NO "+	
					 " ORDER BY	ACC_NO DESC";
						
						
	}

	else if(Type.equals("FA_CO_MAS_CLIENT_GROUPS")){ // TJ
		return" SELECT GROUP_CODE \"Group Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GROUP_CODE "+
					 "        FROM "+Schema+".FA_CO_MAS_GROUPS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_CODE "+
					 "        FROM  "+Schema+".FA_CO_MAS_GROUPS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GROUP_CODE "+	
					 " ORDER BY	GROUP_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_CO_MAS_APPLICATION_STATUS")){ // TJ
		return" SELECT APPLICATION_NO \"Application Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO "+	
					 " ORDER BY	APPLICATION_NO DESC";
						
						
	}
	else if(Type.equals("AF_AD_SCORE_RATING")){ // TJ
		return" SELECT RATING_CODE \"Rating Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.RATING_CODE "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_RATING A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.RATING_CODE "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_RATING_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY RATING_CODE "+	
					 " ORDER BY	RATING_CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_ODI_ALTERATION")){ // TJ
		return" SELECT NVL(RATE,0) \"Rate\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.RATE "+
					 "        FROM "+Schema+".AF_CO_MAS_OD_INTEREST_RATE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.RATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_OD_INTEREST_RATE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY RATE "+	
					 " ORDER BY	RATE DESC";
						
						
	}
			else if(Type.equals("AF_AD_USER")){ // TJ
		return" SELECT USER_ID \"User Id\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.USER_ID "+
					 "        FROM "+Schema+".CO_CO_MAS_USER A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID "+
					 "        FROM  "+Schema+".CO_CO_MAS_USER_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USER_ID "+	
					 " ORDER BY	USER_ID DESC";
						
						
	}
	else if(Type.equals("AF_AD_NATIONALITY")){ // MC
		return" SELECT NATIONALITY_CODE \"Nationality Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.NATIONALITY_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_NATIONALITY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.NATIONALITY_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_NATIONALITY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY NATIONALITY_CODE "+	
					 " ORDER BY	NATIONALITY_CODE DESC";
						
						
	}

else if(Type.equals("AF_AD_BUSINESS_SECTOR")){ // MC
		return" SELECT SECTOR_CODE \"Sector Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SECTOR_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_BUSINESS_SECTOR A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SECTOR_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_BUSINESS_SECTOR_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SECTOR_CODE "+	
					 " ORDER BY	SECTOR_CODE DESC";
						
						
	}




else if(Type.equals("AF_AD_CITY")){ // MC-3
		return" SELECT CITY_CODE \"City Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CITY_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CITY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CITY_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CITY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CITY_CODE "+	
					 " ORDER BY	CITY_CODE DESC";
						
						
	}




else if(Type.equals("AF_AD_LOCATION")){ // MC-4
		return" SELECT LOCATION_CODE \"Location Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.LOCATION_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_LOCATION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.LOCATION_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOCATION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY LOCATION_CODE "+	
					 " ORDER BY	LOCATION_CODE DESC";
						
						
	}




else if(Type.equals("AD_AD_SUB_TEAMS")){ // MC-5
		return" SELECT SUB_TEAM_ID \"Sub Team Id\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUB_TEAM_ID "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_TEAMS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TEAM_ID "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_TEAMS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_TEAM_ID "+	
					 " ORDER BY	SUB_TEAM_ID DESC";
						
						
	}
	
	
else if(Type.equals("AF_AD_PRICE_DEFAULT_VALUE")){ // MC-6
	return" SELECT INTEREST_RATE \"Interest rate\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.INTEREST_RATE "+
				 "        FROM "+Schema+".AF_CO_MAS_PRICING_DEFAULT_VAL A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.INTEREST_RATE "+
				 "        FROM  "+Schema+".AF_CO_MAS_PRICING_DEFAU_VAL_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INTEREST_RATE "+	
					 " ORDER BY	INTEREST_RATE DESC";
						
						
	}


else if(Type.equals("AF_AD_MAINTANANCE")){ // MC-7
	return" SELECT CHARGE_SUB_CODE \"Charge sub code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.CHARGE_SUB_CODE "+
				 "        FROM "+Schema+".AF_CO_MAS_MAINTENANCE_RATE A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.CHARGE_SUB_CODE "+
				 "        FROM  "+Schema+".AF_CO_MAS_MAINTENANCE_RATE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CHARGE_SUB_CODE "+	
					 " ORDER BY	CHARGE_SUB_CODE DESC";
						
						
	}



else if(Type.equals("AF_AD_CONDITION_OF_ASSET")){ // MC-8
	return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.CODE "+
				 "        FROM "+Schema+".AF_CO_MAS_CONDITION_OF_ASSET A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.CODE "+
				 "        FROM  "+Schema+".AF_CO_MAS_CONDI_OF_ASSET_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE "+	
					 " ORDER BY	CODE DESC";
						
						
	}






else if(Type.equals("AF_AD_VENDOR_CREATION")){ // MC-9
	return" SELECT VENDOR_CODE \"Vendor Code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.VENDOR_CODE "+
				 "        FROM "+Schema+".AF_CO_MAS_VENDORS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.VENDOR_CODE "+
				 "        FROM  "+Schema+".AF_CO_MAS_VENDORS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY VENDOR_CODE "+	
					 " ORDER BY	VENDOR_CODE DESC";
						
						
	}
	
	
	else if(Type.equals("AF_AD_VALUER")){ // MC-10
	return" SELECT VALUER_CODE \"Valuer Code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.VALUER_CODE "+
				 "        FROM "+Schema+".AF_CO_MAS_VALUERS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.VALUER_CODE "+
				 "        FROM  "+Schema+".AF_CO_MAS_VALUERS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY VALUER_CODE "+	
					 " ORDER BY	VALUER_CODE DESC";
						
						
	}



else if(Type.equals("AF_AD_SCORE_SUB_CATEGORY")){ // MC-11
	return" SELECT SCORE_SUB_CODE \"Score Sub Code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.SCORE_SUB_CODE "+
				 "        FROM "+Schema+".AF_CR_MAS_SCORE_SUB_CATEGORY A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.SCORE_SUB_CODE "+
				 "        FROM  "+Schema+".AF_CR_MAS_SCORE_SUB_CAT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCORE_SUB_CODE "+	
					 " ORDER BY	SCORE_SUB_CODE DESC";
						
						
	}
	
	
	
		else if(Type.equals("AF_AD_ACCOUNT_CODE")){ // MC-12
	return" SELECT ACC_TYPE_CODE \"Account Type Code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.ACC_TYPE_CODE "+
				 "        FROM "+Schema+".CO_FN_MAS_ACCOUNT_CODE A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.ACC_TYPE_CODE "+
				 "        FROM  "+Schema+".CO_FN_MAS_ACCOUNT_CODE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ACC_TYPE_CODE "+	
					 " ORDER BY	ACC_TYPE_CODE DESC";
						
						
	}
	


	else if(Type.equals("AF_AD_LOAN_FACILITIES_ASSIGN")){ // MC-13
	return" SELECT LOAN_FACILITY_NO \"Loan Facility No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.LOAN_FACILITY_NO "+
				 "        FROM "+Schema+".AF_CO_MAS_LOAN_FACILI_ASSIGN A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.LOAN_FACILITY_NO "+
				 "        FROM  "+Schema+".AF_CO_MAS_LOAN_FACILI_AS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY LOAN_FACILITY_NO "+	
					 " ORDER BY	LOAN_FACILITY_NO DESC";
						
						
	}


else if(Type.equals("FA_CO_MAS_FEE_STRUCT")){ // MC-14
	return" SELECT FEE_PACK_CODE \"Fee pack code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.FEE_PACK_CODE "+
				 "        FROM "+Schema+".FA_CO_MAS_FEES_PACKS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.FEE_PACK_CODE "+
				 "        FROM  "+Schema+".FA_CO_MAS_FEES_PACKS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FEE_PACK_CODE "+	
					 " ORDER BY	FEE_PACK_CODE DESC";
						
						
	}





else if(Type.equals("AF_AD_CLIENT_COMMENT_ADDITION")){ // MC-15
	return" SELECT CLIENT_CODE \"Client code\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.CLIENT_CODE "+
				 "        FROM "+Schema+".AF_CO_MAS_CLIENT_COMMENT A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )"+
				 "        ) "+
					 " GROUP BY CLIENT_CODE "+	
					 " ORDER BY	CLIENT_CODE DESC";
						
						
	}
	
	
	
	
	else if(Type.equals("AF_CO_MAS_TARGET_MONTHS")){ // MC-16
	return" SELECT AGE \"Age\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.AGE "+
				 "        FROM "+Schema+".AF_CO_MAS_TARGET_MONTHS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )"+
				 "        ) "+
					 " GROUP BY AGE "+	
					 " ORDER BY	AGE DESC";
						
						
	}



else if(Type.equals("AF_AD_USER_APPROVAL2")){ // SK
		return " SELECT NVL(USER_ID,'-') \"User ID\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.USER_ID "+
					 "		FROM "+Schema+".CO_CO_MAS_USER_APPROVAL A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.USER_ID "+
					 "		FROM  "+Schema+".CO_CO_MAS_USER_APPROVAL_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USER_ID "+
					 " ORDER BY USER_ID DESC";
					 
}

 else if(Type.equals("AF_AD_HOLIDAY")){ // SK
		return " SELECT HOLIDAY_DATE \"Holiday Date\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.HOLIDAY_DATE "+
					 "		FROM "+Schema+".CO_CO_MAS_HOLIDAY A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.HOLIDAY_DATE "+
					 "		FROM  "+Schema+".CO_CO_MAS_HOLIDAY_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY HOLIDAY_DATE "+
					 " ORDER BY HOLIDAY_DATE DESC";
					
}

else if(Type.equals("AF_AD_POSTAL_CODE")){ // SK
		return " SELECT POSTAL_CODE \"Postal Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.POSTAL_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_POSTAL_CODES A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.POSTAL_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_POSTAL_CODES_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY POSTAL_CODE "+
					 " ORDER BY POSTAL_CODE DESC";
					 
}

else if(Type.equals("AF_AD_COUNTRY")){ // SK
		return " SELECT COUNTRY_CODE \"Country Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.COUNTRY_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_COUNTRY A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.COUNTRY_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_COUNTRY_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY COUNTRY_CODE "+
					 " ORDER BY COUNTRY_CODE DESC";
					 
}

else if(Type.equals("AF_AD_EMPLOYEE_STAGE2")){ // SK
		return " SELECT EMP_CODE \"Employee Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.EMP_CODE "+
					 "		FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.EMP_CODE "+
					 "		FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY EMP_CODE "+
					 " ORDER BY EMP_CODE DESC";
					 
}

else if(Type.equals("AF_AD_BROKER")){ // SK
		return " SELECT BROKER_CODE \"Broker Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.BROKER_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_BROKER A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.BROKER_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_BROKER_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BROKER_CODE "+
					 " ORDER BY BROKER_CODE DESC";
					 
}
	
	else if(Type.equals("AF_AD_MODEL_CREATION")){ // SK
		return " SELECT MODEL_CODE \"Model Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.MODEL_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_MODEL A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.MODEL_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_MODEL_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY MODEL_CODE "+
					 " ORDER BY MODEL_CODE DESC";
					 
}

else if(Type.equals("AF_AD_MILEAGE")){ // SK
		return " SELECT SUB_MODEL \"Sub Model\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.SUB_MODEL "+
					 "		FROM "+Schema+".AF_CO_MAS_MILEAGE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.SUB_MODEL "+
					 "		FROM  "+Schema+".AF_CO_MAS_MILEAGE_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUB_MODEL "+
					 " ORDER BY SUB_MODEL DESC";
					 
}

else if(Type.equals("AF_AD_APPLICABLE_FIELDS")){ // SK
		return " SELECT FILED_CODE \"Filed Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.FILED_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_FILEDS_APPLICABLE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.FILED_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_FILEDS_APPLICABLE_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FILED_CODE "+
					 " ORDER BY FILED_CODE DESC";
	}
	
	else if(Type.equals("AF_AD_BROKER_COMMENTS")){ // SK 
		return " SELECT BROKER_CODE \"Broker Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.BROKER_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_BROKER A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.BROKER_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_BROKER_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BROKER_CODE "+
					 " ORDER BY BROKER_CODE DESC";
					 
}

else if(Type.equals("AF_AD_AUTHORIZATION_LIMITS")){ // SK
		return " SELECT USER_ID \"User ID\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.USER_ID "+
					 "		FROM "+Schema+".AF_CO_MAS_AUTHORIZATION_LIMITS A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.USER_ID "+
					 "		FROM  "+Schema+".AF_CO_MAS_AUTHORIZ_LIMITS_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USER_ID "+
					 " ORDER BY USER_ID DESC";
					 
}

else if(Type.equals("AF_AD_EAR_TEM_CHARGE")){ // SK
		return " SELECT TERMINATION_TYPE \"Termination Type\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.TERMINATION_TYPE "+
					 "		FROM "+Schema+".AF_CO_MAS_EARLY_TERMI_CHARGE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_TYPE "+
					 "		FROM  "+Schema+".AF_CO_MAS_EARLY_TERMI_CHAR_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TERMINATION_TYPE "+
					 " ORDER BY TERMINATION_TYPE DESC";
					 
}

else if(Type.equals("AF_AD_VARIABLE_INTEREST_BASE")){ // SK
		return " SELECT BASE_CODE \"Base Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.BASE_CODE "+
					 "		FROM "+Schema+".AF_CO_MAS_INTEREST_BASE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE "+
					 "		FROM  "+Schema+".AF_CO_MAS_INTEREST_BASE_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BASE_CODE "+
					 " ORDER BY BASE_CODE DESC";
					 
}

else if(Type.equals("AF_AD_VAR_BASE_RATE_NEW")){ // TJ
		return " SELECT BASE_CODE \"Base Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.BASE_CODE "+
					 "		FROM "+Schema+".AF_CO_PRO_INTEREST_BASE_RATE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE "+
					 "		FROM  "+Schema+".AF_CO_PRO_INTEREST_BASE_RA_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BASE_CODE "+
					 " ORDER BY BASE_CODE DESC";
					 
}

else if(Type.equals("AF_AD_SCREEN_ORDER")){ // SK
		return " SELECT NVL(SCREEN_NAME,'-') \"Screen Name\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.SCREEN_NAME "+
					 "		FROM "+Schema+".CO_CO_MAS_USER_SCREEN A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.SCREEN_NAME "+
					 "		FROM  "+Schema+".CO_CO_MAS_USER_SCREEN_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCREEN_NAME "+
					 " ORDER BY SCREEN_NAME DESC";
					 
}

else if(Type.equals("AF_AD_VAR_BASE_RATE")){ // SK
		return " SELECT BASE_CODE \"Base Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.BASE_CODE "+
					 "		FROM "+Schema+".AF_CO_PRO_INTEREST_BASE_RATE A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE "+
					 "		FROM  "+Schema+".AF_CO_PRO_INTEREST_BASE_RA_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BASE_CODE "+
					 " ORDER BY BASE_CODE DESC";
					 
}


else if(Type.equals("AF_CO_MAS_RENTAL_TARGET_SETUP")){ // SK
		return " SELECT FINANCE_NO \"Finance Number\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.FINANCE_NO "+
					 "		FROM "+Schema+".AF_MAS_RENTAL_ARR_SETUP A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.FINANCE_NO "+
					 "		FROM  "+Schema+".AF_MAS_RENTAL_ARR_SETUP_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+
					 " ORDER BY FINANCE_NO DESC";
					 
}

else if(Type.equals("AF_AD_LEGAL_ENTITY")){ // US
		return" SELECT ENTITY_CODE \"Entity Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ENTITY_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_LEGAL_ENTITY A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ENTITY_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_LEGAL_ENTITY_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ENTITY_CODE"+	
					 " ORDER BY ENTITY_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_GUARANTOR_CREATION")){ // US re color guarantor sql
		return" SELECT CLIENT_CODE \"ClientCode\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CLIENT_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CLIENT_CODE"+	
					 " ORDER BY CLIENT_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_AREA")){ // US
		return" SELECT AREA_CODE \"Area Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.AREA_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_AREA A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.AREA_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_AREA_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY AREA_CODE"+	
					 " ORDER BY AREA_CODE DESC";
						
						
	}


else if(Type.equals("AF_AD_FUEL_TYPE")){ // US
		return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_FUEL_TYPE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_FUEL_TYPE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE"+	
					 " ORDER BY CODE DESC";
						
						
	}
	else if(Type.equals("AF_AD_DESIGNATION")){ // US
		return" SELECT DESIGNATION_CODE \"Designation Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.DESIGNATION_CODE"+
					 "        FROM "+Schema+".CO_CO_MAS_DESIGNATION A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.DESIGNATION_CODE"+
					 "        FROM  "+Schema+".CO_CO_MAS_DESIGNATION_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY DESIGNATION_CODE"+	
					 " ORDER BY DESIGNATION_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_TEAM")){ // US
		return" SELECT TEAM_ID \"Team ID\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TEAM_ID"+
					 "        FROM "+Schema+".AF_CO_MAS_TEAMS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID"+
					 "        FROM  "+Schema+".AF_CO_MAS_TEAMS_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TEAM_ID"+	
					 " ORDER BY TEAM_ID";
						
						
	}


else if(Type.equals("AF_AD_INITIATION_TYPE")){ // US
		return" SELECT INITIATION_CODE \"Initiation Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.INITIATION_CODE"+
					 "        FROM "+Schema+".AF_MK_MAS_INITIATION_TYPE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.INITIATION_CODE"+
					 "        FROM  "+Schema+".AF_MK_MAS_INITIATION_TYPE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INITIATION_CODE"+	
					 " ORDER BY INITIATION_CODE";
						
						
	}

else if(Type.equals("AF_CO_PRO_VAT_ON_RENTAL")){ // US
		return" SELECT TRN_CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TRN_CODE"+
					 "        FROM "+Schema+".AF_CO_PRO_VAT_ON_RENTAL A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TRN_CODE"+
					 "        FROM  "+Schema+".AF_CO_PRO_VAT_ON_RENTAL_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TRN_CODE"+	
					 " ORDER BY TRN_CODE DESC";
						
						
	}

 else if(Type.equals("AF_AD_MAKE_CREATION")){ // US
		return" SELECT MAKE_CODE \"Creation Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.MAKE_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_MAKE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_MAKE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY MAKE_CODE"+	
					 " ORDER BY MAKE_CODE DESC";
						
						
	}


else if(Type.equals("AF_AD_DOCUMENTS_REQUIRED")){ // US
		return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_DOCUMENTS_REQUIRED A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_DOC_REQUIRED_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE"+	
					 " ORDER BY CODE DESC";
						
						
	}
	else if(Type.equals("AF_CO_MAS_CHEQ_RET_NARRATIONS")){ // US
		return" SELECT CHQ_NARR_CODE \"Narration Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CHQ_NARR_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_CHEQUE_NARRATIONS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CHQ_NARR_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_CHEQUE_NARR_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CHQ_NARR_CODE"+	
					 " ORDER BY CHQ_NARR_CODE DESC";
						
						
	}
  else if(Type.equals("AF_AD_LEASE_PROCESS_STAGE")){ // US
		return" SELECT STAGE_CODE \"Process Stage Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.STAGE_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_PROCESS_STAGE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.STAGE_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_PROCESS_STAGE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY STAGE_CODE"+	
					 " ORDER BY STAGE_CODE DESC";
						
						
	}
	
  else if(Type.equals("AF_CO_MAS_ODI_TARGET_SETUP")){ // US
		return" SELECT TARGET_MONTH \"Target Month\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TARGET_MONTH"+
					 "        FROM "+Schema+".AF_CO_MAS_ODI_TARGET_SETUP A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TARGET_MONTH"+
					 "        FROM  "+Schema+".AF_CO_MAS_ODI_TARGET_SETUP_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TARGET_MONTH"+	
					 " ORDER BY TARGET_MONTH DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_EMPLOYEE_STAGE1")){ // CJ
		return" SELECT EMP_CODE \"Employee Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.EMP_CODE "+
					 "        FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.EMP_CODE "+
					 "        FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY EMP_CODE "+	
					 " ORDER BY	EMP_CODE DESC";
						
						
	}
       else if(Type.equals("AF_AD_CHARGES")){ // CJ
		return" SELECT TYPE_CODE \"Type Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TYPE_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CHARGES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TYPE_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CHARGES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TYPE_CODE "+	
					 " ORDER BY	TYPE_CODE DESC";
						
						
	}


  else if(Type.equals("AF_AD_TRANSACTION_SUB_TYPE")){ // CJ
		return" SELECT TRN_SUB_TYPE \"Transaction Sub Type\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.TRN_SUB_TYPE "+
					 "        FROM "+Schema+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.TRN_SUB_TYPE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TRANSASUB_TYPE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY TRN_SUB_TYPE "+	
					 " ORDER BY	TRN_SUB_TYPE DESC";
						
						
	}






  else if(Type.equals("AF_AD_OPTION")){ // CJ
		return" SELECT OPTION_CODE \"Option Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.OPTION_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_OPTION_TYPE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.OPTION_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_OPTION_TYPE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY OPTION_CODE "+	
					 " ORDER BY	OPTION_CODE DESC";
						
						
	}

 else if(Type.equals("AF_CO_MAS_CR_DB_NARRATIONS")){ // CJ
		return" SELECT NARRATIONS_CODE \"Narration Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.NARRATIONS_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CR_DB_NARRATIONS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.NARRATIONS_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CR_DB_NARRATIONS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY NARRATIONS_CODE "+	
					 " ORDER BY	NARRATIONS_CODE DESC";
						
						
	}

         


        	
	else if(Type.equals("AF_AD_CR_SCORE_MODEL")){ // CJ
		return" SELECT SCORE_MODEL_CODE \"Score Model Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_MODEL A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_MODEL_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCORE_MODEL_CODE "+	
					 " ORDER BY	SCORE_MODEL_CODE DESC";
						
						
	}
	



  else if(Type.equals("AF_AD_VENDOR_BACKLIST")){ // CJ
		return" SELECT VENDOR_CODE \"Vender Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.VENDOR_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_VENDORS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.VENDOR_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_VENDORS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY VENDOR_CODE "+	
					 " ORDER BY	VENDOR_CODE DESC";
						
						
	}
	
	else if(Type.equals("FA_CO_MAS_FACTOR_DEFAULT_VALUES")){ // CJ
		return" SELECT CREDIT_LIMIT \"Credit Limit\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CREDIT_LIMIT "+
					 "        FROM "+Schema+".FA_MK_PRO_QUOTA_DEFAULT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CREDIT_LIMIT "+
					 "        FROM  "+Schema+".FA_MK_PRO_QUOTA_DEFAULT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CREDIT_LIMIT "+	
					 " ORDER BY	CREDIT_LIMIT DESC";
						
						
	}
	
	
         		else if(Type.equals("AF_AD_CLIENT_CRIB")){ // CJ
		return" SELECT CLIENT_CODE \"Client Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CLIENT_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CLIENT_CODE "+	
					 " ORDER BY	CLIENT_CODE DESC";
						
						
	}



        	else if(Type.equals("AF_CO_MAS_BUSINESS_VOL_SETUP")){ // CJ
		return" SELECT BUDGET_MONTH \"Budget Month\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.BUDGET_MONTH "+
					 "        FROM "+Schema+".AF_CO_MAS_BSNESS_VOL_BUDGET A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.BUDGET_MONTH "+
					 "        FROM  "+Schema+".AF_CO_MAS_BSNESS_VOL_BUDGET_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY BUDGET_MONTH "+	
					 " ORDER BY	BUDGET_MONTH DESC";
						
						
	}
	



      	else if(Type.equals("AF_AD_INQUIRY_STATUS")){ // CJ
		return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_INQUARY_STATUS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_INQUARY_STATUS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE "+	
					 " ORDER BY	CODE DESC";
						
						
	}
	
	
          	else if(Type.equals("AF_AD_FIELDS")){ // CJ
		return" SELECT FILED_CODE \"Field Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FILED_CODE "+
					 "        FROM "+Schema+".AF_CO_MAS_FILEDS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FILED_CODE "+
					 "        FROM  "+Schema+".AF_CO_MAS_FILEDS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FILED_CODE "+	
					 " ORDER BY	FILED_CODE DESC";
						
						
	}
		else if(Type.equals("AF_AD_ASSIGN_PAYEE_SUB_CHARGES")){ // CJ
		return" SELECT NVL(PAYEE_CODE,'-') \"Payee Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYEE_CODE "+
					 "        FROM "+Schema+".AF_CR_PRO_SUB_CHAR_PAYEE_REF A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYEE_CODE "+
					 "        FROM  "+Schema+".AF_CR_PRO_SUB_CHAR_PAY_REF_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYEE_CODE "+	
					 " ORDER BY	PAYEE_CODE DESC";
						
						
	}
	


       
        	else if(Type.equals("AF_AD_USER_APPROVAL1")){ // CJ
		return" SELECT NVL(USER_ID,'-') \"User ID\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.USER_ID "+
					 "        FROM "+Schema+".CO_CO_MAS_USER_APPROVAL A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY')  ) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID "+
					 "        FROM  "+Schema+".CO_CO_MAS_USER_APPROVAL_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) ) "+
					 " GROUP BY USER_ID "+	
					 " ORDER BY	USER_ID DESC";
						
						
	}
	
	
	else if(Type.equals("AF_AD_LEASE_PROCESS_STAGE")){ // US
		return" SELECT STAGE_CODE \"Process Stage Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.STAGE_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_PROCESS_STAGE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.STAGE_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_PROCESS_STAGE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY STAGE_CODE"+	
					 " ORDER BY STAGE_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_AD_GROUP_USER")){ // US  red color CO_CO_MAS_GROUP sql
		return" SELECT GROUP_ID \"Group ID\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GROUP_ID"+
					 "        FROM "+Schema+".CO_CO_MAS_GROUP A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_ID"+
					 "        FROM  "+Schema+".CO_CO_MAS_GROUP_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GROUP_ID"+	
					 " ORDER BY GROUP_ID DESC";						
						
	}



else if(Type.equals("AF_AD_CLIENT_BACKLIST")){ // US re color long sql
		return" SELECT CLIENT_CODE \"ClientCode\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CLIENT_CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CLIENT_CODE"+	
					 " ORDER BY CLIENT_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_CO_MAS_REFERENCE_ADMIN")){ // US  reference admin 1 tabel
		return" SELECT CODE \"Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CODE"+
					 "        FROM "+Schema+".AF_CO_MAS_REFERENCE_ADMIN A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE"+
					 "        FROM  "+Schema+".AF_CO_MAS_REFERENCE_ADMIN A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CODE"+	
					 " ORDER BY CODE DESC";						
						
	}
	
	else if(Type.equals("LOGIN_DETAILS")){ // LOGIN INFO
		return" SELECT USER_NAME \"User Name\",COUNT(*) \"No Of Records\" "+
           " FROM  ( "+
					 "        SELECT A.USER_NAME"+
					 "        FROM  "+Schema+".CO_CO_PRO_LOGIN_LOG A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY USER_NAME"+	
					 " ORDER BY USER_NAME DESC";						
						
	}
//Added by kanchana on 2016-08-30

		else if(Type.equals("AF_AD_PASSWORD_POLICY")){ // LOGIN INFO
			return"  SELECT ENT_USER \"User Name\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ENT_USER"+
					 "        FROM "+Schema+".CO_CO_MAS_PASSWORD_POLICY A"+
					 "        WHERE (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND TO_DATE(A.MOD_DATE,'DD-MM-YYYY')<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ENT_USER"+
					 "        FROM  "+Schema+".CO_CO_MAS_PASSWORD_POLICY_BK A"+
					 "        WHERE (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND TO_DATE(A.MOD_DATE,'DD-MM-YYYY')<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ENT_USER"+	
					 " ORDER BY ENT_USER DESC";						
							
		}else if(Type.equals("AF_AD_SCHEDULE_DETAILS")){ // SCHEDULE INFO
			return  "  SELECT SHEDULE_REF \"Schedule Name\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SHEDULE_REF"+
					 "        FROM "+Schema+".AF_CO_PRO_APP_SHEDULE A"+
					 "        WHERE (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND TO_DATE(A.MOD_DATE,'DD-MM-YYYY')<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					
					 " )GROUP BY SHEDULE_REF"+	
					 " ORDER BY SHEDULE_REF DESC";						
							
		}
		
//Enddeed by Kanchana on 2016-08-30
	
	else{
		 return " ";
   }	
 }	

	

	
 /*public String getQuery_drill(String Schema,String FDate,String TDate,String Type,String Filter) {
	
   if (Type.equals("AF_AD_CUSTOMER_CATEGORY")){
		return " SELECT CAT_TYPE_CODE \"Category\",DESCRIPTION \"Category Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",MOD_USER \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CAT_TYPE_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_MK_MAS_CUSTOMER_CATOGORY A "+
					 "        WHERE CAT_TYPE_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CAT_TYPE_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_MK_MAS_CUSTOMER_CATOGORY_BK A "+
					 "        WHERE  CAT_TYPE_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";
   }else{
		 return " ";
   }	
 }	
	*/	
 
}




