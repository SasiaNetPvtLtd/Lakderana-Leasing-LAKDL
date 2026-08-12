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

public class LAKDL_AF_MISF_Audit_Date_Drill_SQL  
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
	
/* public String getQuery(String Schema,String FDate,String TDate,String Type) {
	
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
   }else{
		 return " ";
   }	
 }	*/
	
 public String getQuery_drill(String Schema,String FDate,String TDate,String Type,String Filter) {
	
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
   
		
		}else if (Type.equals("AF_AD_ITEM_CATEGORY")){//MK
		return " SELECT ITEM_CAT_CODE \"Category\",DESCRIPTION \"Category Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",MOD_USER \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.ITEM_CAT_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_ITEM_CATEGORY A "+
					 "        WHERE ITEM_CAT_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ITEM_CAT_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ITEM_CATEGORY_BK A "+
					 "        WHERE  ITEM_CAT_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";
		
		
		}else if (Type.equals("AF_AD_ITEM_SUB_CATEGORY")){//MK
		return " SELECT ITEM_SUB_CAT \"Sub Category\",DESCRIPTION \"Sub Category Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",MOD_USER \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.ITEM_SUB_CAT, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_ITEM_SUB_CATEGORY A "+
					 "        WHERE ITEM_SUB_CAT = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ITEM_SUB_CAT, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ITEM_SUB_CATEGORY_BK A "+
					 "        WHERE  ITEM_SUB_CAT = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";
						
		}else if (Type.equals("AF_AD_MODEL_CREATION")){ //MK
		return " SELECT MODEL_CODE \"Model Category\",DESCRIPTION \"Sub Category Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",MOD_USER \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.MODEL_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_MODEL A "+
					 "        WHERE MODEL_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MODEL_CODE, A.DESCRIPTION,ACTIVE_STATUS,ENT_USER,ENT_DATE,MOD_USER,NVL(MOD_DATE,ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_MODEL_BK A "+
					 "        WHERE  MODEL_CODE = '"+Filter+"' AND "+
					 "              ((ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";				
		
		}else if (Type.equals("AF_AD_CLIENT_CLASSIFICATION")){//SJ
		return " SELECT SCORE_MODEL_CODE \"Score Model\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APP_STATUS \"Status\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE, B.DESCRIPTION,A.APP_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_CRSCORE_EVALTION A,"+Schema+".AF_CR_MAS_SCORE_MODEL B "+
					 "        WHERE A.SCORE_MODEL_CODE = '"+Filter+"' AND  A.SCORE_MODEL_CODE= B.SCORE_MODEL_CODE AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE, B.DESCRIPTION,A.APP_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_PRO_CRSCORE_EVALTION_BK A ,"+Schema+".AF_CR_MAS_SCORE_MODEL B "+
					 "        WHERE  A.SCORE_MODEL_CODE = '"+Filter+"' AND  A.SCORE_MODEL_CODE= B.SCORE_MODEL_CODE AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
		
		}else if (Type.equals("AF_AD_YARD_CREATION")){//SJ
		return " SELECT YARD_CODE \"Yard Code\",NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.YARD_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_YARD A "+
					 "        WHERE A.YARD_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.YARD_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_YARD_BK A  "+
					 "        WHERE  A.YARD_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
			
			}else if (Type.equals("AF_AD_LAWYER")){//SJ
		return " SELECT LAWYER_CODE \"Lawyer Code\",FIRST_NAME||' '||LAST_NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.LAWYER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_LAWYER A "+
					 "        WHERE A.LAWYER_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.LAWYER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LAWYER_BK A  "+
					 "        WHERE  A.LAWYER_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
						
		}else if (Type.equals("AF_AD_SEIZER")){//SJ
		return " SELECT SEIZER_CODE \"Seizer Code\",FIRST_NAME||' '||LAST_NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SEIZER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_SEIZER A "+
					 "        WHERE A.SEIZER_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SEIZER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SEIZER_BK A  "+
					 "        WHERE  A.SEIZER_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
		
		}else if (Type.equals("AF_AD_MISSING_VEHICLE")){//SJ
		return " SELECT VEHICLE_NO \"Vehicle No\",ENGIN_NO \"Engine No\",CHASSISS_NO \"Chassiss No\",MISSING_DATE \"Missing Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.VEHICLE_NO, A.ENGIN_NO,A.CHASSISS_NO,TO_CHAR(A.MISSING_DATE,'DD-MM-YYYY') MISSING_DATE ,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_MISSING_VEHICLES A "+
					 "        WHERE A.VEHICLE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VEHICLE_NO, A.ENGIN_NO,A.CHASSISS_NO,TO_CHAR(A.MISSING_DATE,'DD-MM-YYYY') MISSING_DATE ,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_MISSING_VEHICLES_BK A  "+
					 "        WHERE  A.VEHICLE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
					
					
			/*}else if (Type.equals("AF_AD_CLIENT_BACKLIST")){//SJ
		return " SELECT CLIENT_CODE \"Client Code\",FULL_NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CLIENT_CODE, A.FULL_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE AND A.ACTIVE_STATUS ='B' AND A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE, A.FULL_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A,"+Schema+".AF_CO_MAS_CLIENT B  "+
					 "        WHERE  A.CLIENT_CODE = B.CLIENT_CODE AND B.ACTIVE_STATUS ='B' AND A.CLIENT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	*/
						
		}else if (Type.equals("FA_CO_MAS_PRODUCT")){//SJ
		return " SELECT FA_PRODUCT_CODE \"Product Code\",FA_PRODUCT_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.FA_PRODUCT_CODE, A.FA_PRODUCT_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_CO_MAS_PRODUCT  A "+
					 "        WHERE A.FA_PRODUCT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FA_PRODUCT_CODE, A.FA_PRODUCT_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_CO_MAS_PRODUCT_BK A  "+
					 "        WHERE  A.FA_PRODUCT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";					
		
		}else if (Type.equals("FA_CO_MAS_FEES")){//SJ
		return " SELECT FEE_CODE \"Fee Code\",FEE_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.FEE_CODE, A.FEE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_CO_MAS_FEES  A "+
					 "        WHERE A.FEE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FEE_CODE, A.FEE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_CO_MAS_FEES_BK A  "+
					 "        WHERE  A.FEE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
		
		}else if (Type.equals("FA_CO_MAS_PRODUCT_CATEGORY")){//SJ
		return " SELECT PRODUCT_CATEGORY \"Fee Code\",PRODUCT_CATEGORY_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.PRODUCT_CATEGORY, A.PRODUCT_CATEGORY_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_CO_MAS_PRODUCT_CATEGORY  A "+
					 "        WHERE A.PRODUCT_CATEGORY = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PRODUCT_CATEGORY, A.PRODUCT_CATEGORY_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_CO_MAS_PRODUCT_CATEGORY_BK A  "+
					 "        WHERE  A.PRODUCT_CATEGORY = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
		
		}else if (Type.equals("AF_AD_DISCOUNT_RATE")){//SJ
		return " SELECT RATE \"Rate\",FROM_DATE \"Apply Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.RATE,TO_CHAR(A.FROM_DATE,'DD-MM-YYYY') FROM_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_DISCOUNT_RATE  A "+
					 "        WHERE A.RATE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.RATE, TO_CHAR(A.FROM_DATE,'DD-MM-YYYY') FROM_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_DISCOUNT_RATE_BK A  "+
					 "        WHERE  A.RATE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
		
		
		}else if (Type.equals("AF_AD_TERMINATION_RATE")){//SJ
		return " SELECT RATE \"Rate\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.MIN_CHARGE||'-'||A.MAX_CHARGES RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_TERMNATION_RATE  A "+
					 "        WHERE A.MIN_CHARGE = '"+Filter.substring(0,Filter.indexOf("-"))+"' AND MAX_CHARGES = '"+Filter.substring(Filter.indexOf("-")+1)+"'  AND  "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MIN_CHARGE||'-'||A.MAX_CHARGES RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TERMNATION_RATE_BK A  "+
					 "       WHERE A.MIN_CHARGE = '"+Filter.substring(0,Filter.indexOf("-"))+"' AND MAX_CHARGES = '"+Filter.substring(Filter.indexOf("-")+1)+"'  AND  "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
		
		
		}else if (Type.equals("FA_CO_MAS_PROD_FEATURES")){//SJ
		return " SELECT FA_FEATURE_CODE \"Feature Code\",FA_FEATURE_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.FA_FEATURE_CODE, A.FA_FEATURE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_CO_MAS_PROD_FEATURES  A "+
					 "        WHERE A.FA_FEATURE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FA_FEATURE_CODE, A.FA_FEATURE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_CO_MAS_PROD_FEATURES_BK A  "+
					 "        WHERE  A.FA_FEATURE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
		
		}else if (Type.equals("FA_CO_MAS_COLLECTION_ROUTES")){//SJ
		return " SELECT COLL_ROUTE_CODE \"Route Code\",COLL_ROUTE_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.COLL_ROUTE_CODE, A.COLL_ROUTE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_COLL_ROUTES  A "+
					 "        WHERE A.COLL_ROUTE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.COLL_ROUTE_CODE, A.COLL_ROUTE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_COLL_ROUTES_BK A  "+
					 "        WHERE  A.COLL_ROUTE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
		
		}else if (Type.equals("FA_CO_MAS_COLLECTION_ROUTES_ASSIGN")){//SJ
		return " SELECT COLL_ROUTE_CODE \"Route Code\",EMP_NAME \"Assing To\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.COLL_ROUTE_CODE, "+Schema+".AF_CO_GET_EMP_NAME(A.EMP_CODE) EMP_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_COLL_ROUT_ASSIGN  A "+
					 "        WHERE A.COLL_ROUTE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.COLL_ROUTE_CODE, "+Schema+".AF_CO_GET_EMP_NAME(A.EMP_CODE) EMP_NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_COLL_ROUT_ASSIGN_BK A  "+
					 "        WHERE  A.COLL_ROUTE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
		
		}else if (Type.equals("AF_AD_SUB_DIVISION")){//LK
		return " SELECT SUB_DIVISION_CODE \"Sub Div Code\",DESCRIPTION \"Description\",DIVISION_CODE \"Div Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SUB_DIVISION_CODE, A.DESCRIPTION,A.DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_SUB_DIVISION A "+
					 "        WHERE A.SUB_DIVISION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_DIVISION_CODE, A.DESCRIPTION,A.DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".CO_CO_MAS_SUB_DIVISION_BK A  "+
					 "        WHERE  A.SUB_DIVISION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

		}else if (Type.equals("AF_AD_LOCATION")){//LK
		return " SELECT LOCATION_CODE \"Location Code\",LOCATION_DESC \"Location Des\",ADDRESS \"Address\",POSTAL_CODE \"Post Code\",COUNTRY_CODE \"Country Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.LOCATION_CODE, A.LOCATION_DESC,A.ADDRESS1||' '||A.ADDRESS2 ADDRESS,A.POSTAL_CODE,A.COUNTRY_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_LOCATION A "+
					 "        WHERE A.LOCATION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.LOCATION_CODE, A.LOCATION_DESC,A.ADDRESS1||' '||A.ADDRESS2 ADDRESS,A.POSTAL_CODE,A.COUNTRY_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOCATION_BK A  "+
					 "        WHERE  A.LOCATION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	


	}else if (Type.equals("AF_AD_MAKE_CREATION")){//MK
		return " SELECT MAKE_CODE \"Make Code\",MAKE_DESC \"Make Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.MAKE_CODE, A.MAKE_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_MAKE A "+
					 "        WHERE A.MAKE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE, A.MAKE_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_MAKE_BK A  "+
					 "        WHERE  A.MAKE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
		}else if (Type.equals("AF_AD_ENGINE_CAPACITY")){//MK
		return " SELECT CAPACITY_CODE \"Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.CAPACITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_ENGINE_CAPACITY A "+
					 "        WHERE A.CAPACITY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CAPACITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ENGINE_CAPACITY_BK A  "+
					 "        WHERE  A.CAPACITY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_CO_MAS_REFERNCE_CONDITIONS")){//DJ
	
		return " SELECT CODE \"Code\",NVL(DESCRIPTION,'-') \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_MK_CONDITIONS A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_MK_CONDITIONS_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_PHONE_AREA_CODE")){//DJ-1
	
		return " SELECT PHONE_AREA_CODE \"Phone Code\",NVL(CITY_CODE,'-') \"City Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.PHONE_AREA_CODE,A.CITY_CODE, A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_PHONE_CODES A "+
					 "        WHERE A.PHONE_AREA_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PHONE_AREA_CODE,A.CITY_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_PHONE_CODES_BK A  "+
					 "        WHERE  A.PHONE_AREA_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_ENGINE_CAPACITY")){//DJ-2
	
		return " SELECT CAPACITY_CODE \"Capacity Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CAPACITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_ENGINE_CAPACITY A "+
					 "        WHERE A.CAPACITY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CAPACITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_ENGINE_CAPACITY_BK A  "+
					 "        WHERE  A.CAPACITY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_USER_LIST")){//DJ-3
	
		return " SELECT USER_ID \"User ID\",NVL(SCREEN_NAME,'-') \"Screen Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.USER_ID, A.SCREEN_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".CO_CO_MAS_USER_ACCESS A "+
					 "        WHERE A.USER_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID, A.SCREEN_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".CO_CO_MAS_USER_ACCESS_BK A  "+
					 "        WHERE  A.USER_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_BRANCH")){//DJ-4
	
		return " SELECT BRANCH_CODE \"Branch Code\",BRANCH_NAME \"Branch Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.BRANCH_CODE, A.BRANCH_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_BANK_BRANCH A "+
					 "        WHERE A.BRANCH_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.BRANCH_CODE, A.BRANCH_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_BANK_BRANCH_BK A  "+
					 "        WHERE  A.BRANCH_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_DIVISION")){//DJ-5
	
		return " SELECT DIVISION_CODE \"Division Code\",NVL(DESCRIPTION,'-') \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.DIVISION_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".CO_CO_MAS_DIVISION A "+
					 "        WHERE A.DIVISION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.DIVISION_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".CO_CO_MAS_DIVISION_BK A  "+
					 "        WHERE  A.DIVISION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_LEAD_SOURCE_CATEGORY")){//DJ-6
	
		return " SELECT SOURCE_CODE \"Source Code\",NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SOURCE_CODE, A.NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_MK_MAS_LEAD_SOURCE_CAT A "+
					 "        WHERE A.SOURCE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SOURCE_CODE, A.NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_MK_MAS_LEAD_SOURCE_CAT_BK A  "+
					 "        WHERE  A.SOURCE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_APPLICABLE_CHARGES")){//DJ-7
	
		return " SELECT SUB_TYPE_CODE \"Sub Type Code\",ITEM_SUB_CAT \"Item Sub Category\",NVL(AMOUNT,0) \"Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SUB_TYPE_CODE,A.ITEM_SUB_CAT,A.AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CHARGES_APPLICABLE A "+
					 "        WHERE A.SUB_TYPE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TYPE_CODE,A.ITEM_SUB_CAT,A.AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CHARGERS_APPLI_BK A "+
					 "        WHERE  A.SUB_TYPE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
  }
	
  else if (Type.equals("AF_AD_CLIENT_GROUP")){//DJ-8
	
		return " SELECT NVL(GROUP_ID,'-') \"Group Id\",NVL(MEMBER_ID,'-') \"Member Id\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(CLIENT_STATUS,'-') \"Status\" "+
           " FROM  (SELECT A.GROUP_ID,A.MEMBER_ID,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.CLIENT_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT_GROUP A "+
					 "        WHERE A.GROUP_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_ID,A.MEMBER_ID,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.CLIENT_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_GROUP_BK A  "+
					 "        WHERE  A.GROUP_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_AD_DOCUMENT_APPLICABLE")){//DJ-9
	
		return " SELECT CODE \"Code\",NVL(ITEM_CAT_CODE,'-') \"Item Code\",NVL(ENTITY_TYPE,'-') \"Entity Type\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CODE,A.ITEM_CAT_CODE,A.ENTITY_TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_DOCUMENT_APPLICABLE A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE,A.ITEM_CAT_CODE,A.ENTITY_TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_DOC_APPLICABLE_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_AD_SCORE_CATEGORY")){//DJ-10
	
		return " SELECT SCORE_CODE \"Source Code\",NVL(DESCRIPTION,'-') \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SCORE_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_CATEGORY A "+
					 "        WHERE A.SCORE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_CATEGORY_BK A  "+
					 "        WHERE  A.SCORE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_RMV_AGENTS")){//DJ-11
	
		return " SELECT RMV_AGENT_CODE \"Agent Code\",NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.RMV_AGENT_CODE, A.NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_RMV_AGENTS A "+
					 "        WHERE A.RMV_AGENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.RMV_AGENT_CODE, A.NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_RMV_AGENTS_BK A  "+
					 "        WHERE  A.RMV_AGENT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_LOAN_FACILITIES")){//DJ-12
	
		return " SELECT LOAN_FACILITY_NO \"Loan Facility Code\",REF_NAME \"Ref Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.LOAN_FACILITY_NO, A.REF_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_LOAN_FACILITIES A "+
					 "        WHERE A.LOAN_FACILITY_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.LOAN_FACILITY_NO, A.REF_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOAN_FACILITIES_BK A  "+
					 "        WHERE  A.LOAN_FACILITY_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_FOLLOW_UP_ACTION")){//DJ-13
	
		return " SELECT CATEGORY_CODE \"Category Code\",CATEGORY_NAME \"Category Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CATEGORY_CODE, A.CATEGORY_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_FOLLOWUP_CATEGORY A "+
					 "        WHERE A.CATEGORY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CATEGORY_CODE, A.CATEGORY_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_FOLLOWUP_CATEGORY_BK A  "+
					 "        WHERE  A.CATEGORY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	


	
	}
	
  else if (Type.equals("AF_AD_ASSIGN_MEMBERS")){//DJ-14
	
		return " SELECT TEAM_ID \"Team Id\",USER_ID \"User Id\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.TEAM_ID, A.USER_ID,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_TEAM_MEMBERS A "+
					 "        WHERE A.TEAM_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID, A.USER_ID,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_TEAM_MEMBERS_BK A  "+
					 "        WHERE  A.TEAM_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	


	
	}
  
	else if (Type.equals("AF_AD_CLIENT_CREATION")){//DJ-15
	
		return " SELECT CLIENT_CODE \"Client Code\",NVL(CLIENT_TYPE,'-') \"Client Type\",NVL(FULL_NAME,'-') \"Full Name\",NVL(BUSINESS_SUB_SECTOR,'-') \"Business Sub Sector\",NVL(DESIGNATION,'-') \"Designation\",CLIENT_CATEGORY \"Client Category\",NVL(AF_CLIENT,'-') \"AF Client\",NVL(NIC_NO,'-') \"N.I.C\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A  "+
					 "        WHERE  A.CLIENT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
 else if (Type.equals("AF_AD_CHARGES")){//CJ
	
		return " SELECT TYPE_CODE \"Type Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.TYPE_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CHARGES A "+
					 "        WHERE A.TYPE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TYPE_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CHARGES_BK A  "+
					 "        WHERE  A.TYPE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


     

	
		else if (Type.equals("AF_AD_TRANSACTION_SUB_TYPE")){//CJ
	
		return " SELECT TRN_SUB_TYPE \"TransAction Sub Type\",TRN_CODE \"Transaction Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.TRN_SUB_TYPE,A.TRN_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
					 "        WHERE A.TRN_SUB_TYPE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TRN_SUB_TYPE,A.TRN_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TRANSASUB_TYPE_BK A  "+
					 "        WHERE  A.TRN_SUB_TYPE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}





         else if (Type.equals("AF_AD_OPTION")){//CJ
	
		return " SELECT OPTION_CODE \"Option Code\",OPTION_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.OPTION_CODE,A.OPTION_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_OPTION_TYPE A "+
					 "        WHERE A.OPTION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.OPTION_CODE,A.OPTION_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_OPTION_TYPE_BK A  "+
					 "        WHERE  A.OPTION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_CO_MAS_EXPOSURE_CODE")){//CJ
	
		return " SELECT EXPOSURE_CODE \"Exposure Code\",EXPOSURE_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS\"Status\" "+
           " FROM  (SELECT A.EXPOSURE_CODE,A.EXPOSURE_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_BUSI_EXPOSURE_CAT A "+
					 "        WHERE A.EXPOSURE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.EXPOSURE_CODE,A.EXPOSURE_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_BUSI_EXPOSURE_CAT_BK A  "+
					 "        WHERE  A.EXPOSURE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}



        
else if (Type.equals("AF_AD_VENDOR_BACKLIST")){//CJ
	
		return " SELECT VENDOR_CODE \"Vender Code\",NAME \"Name\",NVL(CATEGORY,'-') \"Category\",NVL(TYPE,'-') \"Type\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.VENDOR_CODE,A.NAME,A.CATEGORY,A.TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_VENDORS A "+
					 "        WHERE A.VENDOR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VENDOR_CODE,A.NAME,A.CATEGORY,A.TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_VENDORS_BK A  "+
					 "        WHERE  A.VENDOR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_AD_CLIENT_CRIB")){//CJ
	
		return " SELECT CLIENT_CODE \"Client Code\",CLIENT_TYPE \"Client Type\",FULL_NAME \"Full Name\",NVL(BUSINESS_SUB_SECTOR,'-') \"Business Sub Sector\",CLIENT_CATEGORY \"Client Category\",NVL(GRIB_NO,'-') \"Grib Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.CLIENT_CATEGORY,A.GRIB_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.CLIENT_CATEGORY,A.GRIB_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
           "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A  "+
					 "        WHERE  A.CLIENT_CODE = '"+Filter+"' AND "+
           "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}

  else if (Type.equals("AF_AD_EMPLOYEE_STAGE1")){//CJ
	
		return " SELECT EMP_CODE \"Employee Code\",TITLE \"Title\",FIRST_NAME \"First Name\",LAST_NAME \"Last Name\",ADDRESS \"Address\",LOCATION_CODE \"Location Code\",NVL(AREA_CODE,'-') \"Area Code\",NVL(CITY_CODE,'-') \"City Code\",NVL(CONTACT_NO,'-') \"Contact Number\",DESIGNATION_CODE \"Designation Code\",DIVISION_CODE \"Division Code\",NVL(EPF_NO,'-') \"EPF Number\",ID_NO \"ID Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.EMP_CODE,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DESIGNATION_CODE,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "        WHERE A.EMP_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.EMP_CODE,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DESIGNATION_CODE,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A  "+
					 "        WHERE  A.EMP_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}




else if (Type.equals("AF_CO_MAS_CR_DB_NARRATIONS")){//CJ
	
		return " SELECT NARRATIONS_CODE \"Narrations Code\",NARRATIONS \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
           " FROM  (SELECT A.NARRATIONS_CODE,A.NARRATIONS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CR_DB_NARRATIONS A "+
					 "        WHERE A.NARRATIONS_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.NARRATIONS_CODE,A.NARRATIONS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CR_DB_NARRATIONS_BK A  "+
					 "        WHERE  A.NARRATIONS_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_AD_CURRENCY")){//CJ
	
		return " SELECT CURR_CODE \"Currency Code\",CURR_SYMBOL \"Currency Symbol\", ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.CURR_CODE,A.CURR_SYMBOL,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CURRENCY A "+
					 "        WHERE A.CURR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CURR_CODE,A.CURR_SYMBOL,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE  "+
					 "        FROM  "+Schema+".AF_CO_MAS_CURRENCY_BK A  "+
					 "        WHERE  A.CURR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
 

else if (Type.equals("AF_AD_DISTRICT")){//CJ
	
		return " SELECT DISTRICT_CODE \"District Code\",DISTRICT_DESC \"Description\",PROVINCE_CODE \"Province Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(CRIB_CAT_CODE,'-') \"CRIB Category code\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.DISTRICT_CODE,A.DISTRICT_DESC,A.PROVINCE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.CRIB_CAT_CODE,A.ACTIVE_STATUS  "+
					 "        FROM "+Schema+".AF_CO_MAS_DISTRICT A "+
					 "        WHERE A.DISTRICT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.DISTRICT_CODE,A.DISTRICT_DESC,A.PROVINCE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.CRIB_CAT_CODE,A.ACTIVE_STATUS  "+
					 "        FROM  "+Schema+".AF_CO_MAS_DISTRICT_BK A  "+
					 "        WHERE  A.DISTRICT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


     else if (Type.equals("AF_CO_MAS_BUSINESS_VOL_SETUP")){//CJ
	
		return " SELECT BUDGET_MONTH \"Budget Month\",NVL(HEAD_OFFICE_BUDGET,'0') \"Head Office Budget\",NVL(BRANCH_BUDGET,'0') \"Branch Budget\",NVL(BIKE_BUDGET,'0') \"Bike Budget\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.BUDGET_MONTH,A.HEAD_OFFICE_BUDGET,A.BRANCH_BUDGET,A.BIKE_BUDGET,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_BSNESS_VOL_BUDGET A "+
					 "        WHERE A.BUDGET_MONTH = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.BUDGET_MONTH,A.HEAD_OFFICE_BUDGET,A.BRANCH_BUDGET,A.BIKE_BUDGET,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
           "        FROM  "+Schema+".AF_CO_MAS_BSNESS_VOL_BUDGET_BK A  "+
					 "        WHERE  A.BUDGET_MONTH = '"+Filter+"' AND "+
           "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}



 else if (Type.equals("FA_CO_MAS_FACTOR_DEFAULT_VALUES")){//CJ
	
		return " SELECT CREDIT_LIMIT \"Credit Limit\",CREDIT_PERIOD \"Credit Period\",TOLERANCE_CREDIT_PERIOD \"Tolerance Credit period\",RESERVE_MARGIN \"Reserve Margin\",INT_RATE \"Interest Rate\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.CREDIT_LIMIT,A.CREDIT_PERIOD,A.TOLERANCE_CREDIT_PERIOD,A.RESERVE_MARGIN,A.INT_RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_MK_PRO_QUOTA_DEFAULT A "+
					 "        WHERE A.CREDIT_LIMIT = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CREDIT_LIMIT,A.CREDIT_PERIOD,A.TOLERANCE_CREDIT_PERIOD,A.RESERVE_MARGIN,A.INT_RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_MK_PRO_QUOTA_DEFAULT_BK A  "+
					 "        WHERE  A.CREDIT_LIMIT = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}



else if (Type.equals("AF_AD_CR_SCORE_MODEL")){//CJ
	
		return " SELECT SCORE_MODEL_CODE \"Score Model Code\",DESCRIPTION \"Description\",TOTAL_SCORE \"Total Score\",NVL(ENT_USER,'-') \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE,A.DESCRIPTION,A.TOTAL_SCORE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_MODEL A "+
					 "        WHERE A.SCORE_MODEL_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE,A.DESCRIPTION,A.TOTAL_SCORE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_MODEL_BK A  "+
					 "        WHERE  A.SCORE_MODEL_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
  
	
else if (Type.equals("AF_AD_INQUIRY_STATUS")){//CJ
	
		return " SELECT CODE \"Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_INQUARY_STATUS A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
           "        FROM  "+Schema+".AF_CO_MAS_INQUARY_STATUS_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
           "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
 
 else if (Type.equals("AF_AD_FIELDS")){//CJ
	
		return " SELECT FILED_CODE \"Field Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.FILED_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_FILEDS A "+
					 "        WHERE A.FILED_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FILED_CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_FILEDS_BK A  "+
					 "        WHERE  A.FILED_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
			else if (Type.equals("AF_AD_ASSIGN_PAYEE_SUB_CHARGES")){//CJ
	
		return " SELECT NVL(PAYEE_CODE,'-') \"Payee Code\",NVL(SUB_TYPE_CODE,'-') \"Sub Type Code\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PAYEE_ADDRESS,'-') \"Payee Address\",NVL(PAYEE_VAT_REG_NO,'-') \"Payee VAT Reg No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.PAYEE_CODE,A.SUB_TYPE_CODE,A.PAYEE_NAME,A.PAYEE_ADDRESS,A.PAYEE_VAT_REG_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_SUB_CHAR_PAYEE_REF A "+
					 "        WHERE A.PAYEE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYEE_CODE,A.SUB_TYPE_CODE,A.PAYEE_NAME,A.PAYEE_ADDRESS,A.PAYEE_VAT_REG_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
           "        FROM  "+Schema+".AF_CR_PRO_SUB_CHAR_PAY_REF_BK A  "+
					 "        WHERE  A.PAYEE_CODE = '"+Filter+"' AND "+
           "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_AD_USER_APPROVAL1")){//CJ
	
		return " SELECT NVL(USER_ID,'-') \"User ID\",NVL(APPR_USER,'-') \"Approved User\",NVL(APPR_STATUS,'-') \"Approve Status\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
           " FROM  (SELECT A.USER_ID,A.APPR_USER,A.APPR_STATUS,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_USER_APPROVAL A "+
					 "        WHERE A.USER_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID,A.APPR_USER,A.APPR_STATUS,A.ENT_USER,A.ENT_DATE "+
           "        FROM  "+Schema+".CO_CO_MAS_USER_APPROVAL_BK A  "+
					 "        WHERE  A.USER_ID = '"+Filter+"' AND "+
           "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) )) "+
					 " ORDER BY	ENT_DATE DESC";	 
	
	
	
	}

	
	else if (Type.equals("AF_AD_BANK")){//TJ
	
		return " SELECT BANK_CODE \"Bank Code\",NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.BANK_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_BANKS A "+
					 "        WHERE A.BANK_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.BANK_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_BANKS_BK A  "+
					 "        WHERE  A.BANK_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
		else if (Type.equals("AF_AD_PROVINCE")){//TJ
	
		return " SELECT PROVINCE_CODE \"Province Code\",PROVINCE_DESC \"Province Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.PROVINCE_CODE, A.PROVINCE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_PROVINCE A "+
					 "        WHERE A.PROVINCE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PROVINCE_CODE, A.PROVINCE_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_PROVINCE_BK A  "+
					 "        WHERE  A.PROVINCE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
		else if (Type.equals("AF_AD_SUB_BUSINESS_SECTORS")){//TJ
	
		return " SELECT SUB_CODE \"Sub Code\",DESCRIPTION \"Description\",SECTOR_CODE \"Sector Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.SUB_CODE, A.DESCRIPTION,A.SECTOR_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_BUSINESS_SECTORS A "+
					 "        WHERE A.SUB_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_CODE, A.DESCRIPTION,A.SECTOR_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_BUSI_SECTORS_BK A  "+
					 "        WHERE  A.SUB_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}



	else if (Type.equals("AF_AD_ASSIGN_SUB_TEAMS")){//TJ
	
		return " SELECT TEAM_ID \"Team Id\",SUB_TEAM_ID \"Sub Team Id\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.TEAM_ID, A.SUB_TEAM_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_TEAMS_ASSIGN A "+
					 "        WHERE A.TEAM_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID, A.SUB_TEAM_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_TEAMS_ASSIGN_BK A  "+
					 "        WHERE  A.TEAM_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_AD_REPAYMENT_INERVAL")){//TJ
	
		return " SELECT DURATION \"Duration\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.DURATION, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_REPAYMENT_INTERVAL A "+
					 "        WHERE A.DURATION = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.DURATION, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_REPAYMENT_INTER_BK A  "+
					 "        WHERE  A.DURATION = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_AD_SUB_CHARGE")){//TJ
	
		return " SELECT SUB_TYPE_CODE \"Sub Type Code\",TYPE_CODE \"Type Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.SUB_TYPE_CODE, A.TYPE_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_CHARGES A "+
					 "        WHERE A.SUB_TYPE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TYPE_CODE, A.TYPE_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_CHARGES_BK A  "+
					 "        WHERE  A.SUB_TYPE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_AD_TRANSACTION")){//TJ
	
		return " SELECT TRAN_CODE \"Transaction Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.TRAN_CODE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_TRANSACTION_TYPE A "+
					 "        WHERE A.TRAN_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TRAN_CODE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TRANSACTION_TYPE_BK A  "+
					 "        WHERE  A.TRAN_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	
	else if (Type.equals("AF_AD_INCOME_EXPENCE_TYPE")){//TJ
	
		return " SELECT I_E_CODE \"Income Expence Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.I_E_CODE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_INCOME_EXPENCE_TYPE A "+
					 "        WHERE A.I_E_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.I_E_CODE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_INCOME_EXP_TYPE_BK A  "+
					 "        WHERE  A.I_E_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_GARAGE")){//TJ
	
		return " SELECT GARAGE_CODE \"Garage Code\",NAME \"Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.GARAGE_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_GARAGE A "+
					 "        WHERE A.GARAGE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GARAGE_CODE, A.NAME,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_GARAGE_BK A  "+
					 "        WHERE  A.GARAGE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

else if (Type.equals("AF_AD_EMPLOYEE")){//TJ
	
		return " SELECT EMP_CODE \"Employee Code\",TITLE \"Title\",FIRST_NAME \"First Name\",LAST_NAME \"Last Name\",ADDRESS \"Address\",LOCATION_CODE \"Location Code\",NVL(AREA_CODE,'-') \"Area Code\",NVL(CITY_CODE,'-') \"City Code\",NVL(CONTACT_NO,'-') \"Contact No\",DIVISION_CODE \"Division Code\",NVL(EPF_NO,'-') \"EPF No\",ID_NO \"ID No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.EMP_CODE, A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "        WHERE A.EMP_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.EMP_CODE, A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A  "+
					 "        WHERE  A.EMP_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_AD_ASSET_USAGE_TYPE")){//TJ
	
		return " SELECT USAGE_TYPE \"Usage Type\",NVL(DESCRIPTION,'-') \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.USAGE_TYPE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_ASSET_USAGE_TYPE A "+
					 "        WHERE A.USAGE_TYPE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.USAGE_TYPE, A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ASSET_USAGE_TYPE_BK A  "+
					 "        WHERE  A.USAGE_TYPE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_AD_LICENCEE_SETTLEMENT")){//TJ
	
		return " SELECT ACC_NO \"Account No\",BRANCH_CODE \"Branch Code\",NVL(DN_USER,'-') \"Entered User\",DN_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.ACC_NO, A.BRANCH_CODE,A.ACTIVE_STATUS,A.DN_USER,A.DN_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.DN_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_LICENCEE_SETTLEMENT A "+
					 "        WHERE A.ACC_NO = '"+Filter+"' AND   "+
					 "              ((A.DN_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.DN_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ACC_NO, A.BRANCH_CODE,A.ACTIVE_STATUS,A.DN_USER,A.DN_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.DN_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LICENCEE_SETTLEME_BK A  "+
					 "        WHERE  A.ACC_NO = '"+Filter+"' AND "+
					 "              ((A.DN_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.DN_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("FA_CO_MAS_CLIENT_GROUPS")){//TJ
	
		return " SELECT NVL(GROUP_CODE,'-') \"Group Code\",NVL(GROUP_DESC,'-') \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.GROUP_CODE, A.GROUP_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".FA_CO_MAS_GROUPS A "+
					 "        WHERE A.GROUP_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_CODE, A.GROUP_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".FA_CO_MAS_GROUPS_BK A  "+
					 "        WHERE  A.GROUP_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_CO_MAS_APPLICATION_STATUS")){//TJ
	
		return " SELECT APPLICATION_NO \"Application Code\",CLIENT_CODE \"Client Code\",CLIENT_NO \"Client No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(APPLICATION_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.CLIENT_NO,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.CLIENT_NO,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_AD_SCORE_RATING")){//TJ
	
		return " SELECT RATING_CODE \"Rating Code\",NVL(DESCRIPTION,'-') \"Description\",NVL(FROM_RAGE,0) \"From Range\",NVL(TO_RANGE,0) \"To Range\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.RATING_CODE, A.DESCRIPTION,A.FROM_RAGE,A.TO_RANGE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_RATING A "+
					 "        WHERE A.RATING_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.RATING_CODE, A.DESCRIPTION,A.FROM_RAGE,A.TO_RANGE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_RATING_BK A  "+
					 "        WHERE  A.RATING_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	else if (Type.equals("AF_AD_ODI_ALTERATION")){//TJ
	
		return " SELECT NVL(RATE,0) \"Rate\",APPLY_DATE \"Apply Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.RATE, A.APPLY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_OD_INTEREST_RATE A "+
					 "        WHERE A.RATE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.RATE, A.APPLY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_OD_INTEREST_RATE_BK A  "+
					 "        WHERE  A.RATE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
		else if (Type.equals("AF_AD_USER")){//TJ
	
		return " SELECT USER_ID \"User Id\",NVL(NAME,'-') \"Name\",NVL(LOCATION_CODE,'-') \"Location Code\",NVL(USER_TYPE,'-') \"User Type\",NVL(EMP_ID,'-') \"Employee Id\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+ 
           " FROM  (SELECT A.USER_ID, A.NAME,A.LOCATION_CODE,A.USER_TYPE,A.EMP_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_USER A "+
					 "        WHERE A.USER_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.USER_ID, A.NAME,A.LOCATION_CODE,A.USER_TYPE,A.EMP_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".CO_CO_MAS_USER_BK A  "+
					 "        WHERE  A.USER_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if(Type.equals("AF_AD_USER_APPROVAL2")){ // SK
		return " SELECT NVL(USER_ID,'-') \"User ID\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
			" FROM  (SELECT A.USER_ID,A.ENT_USER,A.ENT_DATE "+
					 "		FROM "+Schema+".CO_CO_MAS_USER_APPROVAL A "+
					 "		WHERE A.USER_ID = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.USER_ID,A.ENT_USER,A.ENT_DATE "+
					 "		FROM  "+Schema+".CO_CO_MAS_USER_APPROVAL_BK A  "+
					 "		WHERE  A.USER_ID = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY ENT_DATE DESC";
					
}

else if(Type.equals("AF_AD_HOLIDAY")){ // SK
		return " SELECT HOLIDAY_DATE \"Holiday Date\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.HOLIDAY_DATE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".CO_CO_MAS_HOLIDAY A "+
					 "		WHERE A.HOLIDAY_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.HOLIDAY_DATE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".CO_CO_MAS_HOLIDAY_BK A  "+
					 "		WHERE  A.HOLIDAY_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_POSTAL_CODE")){ // SK
		return " SELECT POSTAL_CODE \"Postal Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.POSTAL_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_POSTAL_CODES A "+
					 "		WHERE A.POSTAL_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.POSTAL_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_POSTAL_CODES_BK A  "+
					 "		WHERE  A.POSTAL_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_COUNTRY")){ // SK
		return " SELECT COUNTRY_CODE \"Country Code\",COUNTRY_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.COUNTRY_CODE,A.COUNTRY_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_COUNTRY A "+
					 "		WHERE A.COUNTRY_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.COUNTRY_CODE,A.COUNTRY_DESC,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_COUNTRY_BK A  "+
					 "		WHERE  A.COUNTRY_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_EMPLOYEE_STAGE2")){ // SK
		return " SELECT EMP_CODE \"Employee Code\",TITLE \"Title\",FIRST_NAME \"First Name\",LAST_NAME \"Last Name\",ADDRESS \"Address\",LOCATION_CODE \"Location Code\",NVL(AREA_CODE,'-') \"Area Code\",NVL(CITY_CODE,'-') \"City Code\",NVL(CONTACT_NO,'-') \"Contact Number\",DESIGNATION_CODE \"Designation Code\",DIVISION_CODE \"Division Code\",NVL(EPF_NO,'-') \"EPF Number\",ID_NO \"ID Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.EMP_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DESIGNATION_CODE,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".CO_CO_MAS_EMPLOYEE A "+
					 "		WHERE A.EMP_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.EMP_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.LOCATION_CODE,A.AREA_CODE,A.CITY_CODE,A.CONTACT_NO,A.DESIGNATION_CODE,A.DIVISION_CODE,A.EPF_NO,A.ID_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".CO_CO_MAS_EMPLOYEE_BK A  "+
					 "		WHERE  A.EMP_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_BROKER")){ // SK
		return " SELECT BROKER_CODE \"Broker Code\",TITLE \"Title\",FIRST_NAME \"First Name\",LAST_NAME \"Last Name\",ID_NO \"ID Number\",ADDRESS1 \"Address 1\",ADDRESS2 \"Address 2\",LOCATION_CODE \"Location Code\",NVL(CITY_CODE,'-') \"City Code\",NVL(POSTAL_CODE,'-') \"Postal Code\",NVL(CONTACT_NO,'-') \"Contact Number\",NVL(MOBILE_NO,'-') \"Mobile Number\",NVL(FAX_NO,'-') \"Fax Number\",NVL(SECTOR_CODE,'-') \"Sector Code\",NVL(COMMISSION_RATE,0) \"Commission Rate\",NVL(COMMISSION_AMOUNT,0) \"Commission Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.BROKER_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_BROKER A "+
					 "		WHERE A.BROKER_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.BROKER_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_BROKER_BK A  "+
					 "		WHERE  A.BROKER_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_MODEL_CREATION")){ // SK
		return " SELECT MODEL_CODE \"Model Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.MODEL_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_MODEL A "+
					 "		WHERE A.MODEL_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.MODEL_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_MODEL_BK A  "+
					 "		WHERE  A.MODEL_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_MILEAGE")){ // SK
		return " SELECT SUB_MODEL \"Sub Model\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.SUB_MODEL,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_MILEAGE A "+
					 "		WHERE A.SUB_MODEL = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.SUB_MODEL,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_MILEAGE_BK A  "+
					 "		WHERE  A.SUB_MODEL = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}
  
 
 else if(Type.equals("AF_AD_APPLICABLE_FIELDS")){ // SK
		return " SELECT FILED_CODE \"Filed Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.FILED_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_FILEDS_APPLICABLE A "+
					 "		WHERE A.FILED_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.FILED_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_FILEDS_APPLICABLE_BK A  "+
					 "		WHERE  A.FILED_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_BROKER_COMMENTS")){ // SK
		return " SELECT BROKER_CODE \"Broker Code\",TITLE \"Title\",FIRST_NAME \"First Name\",LAST_NAME \"Last Name\",ID_NO \"ID Number\",ADDRESS1 \"Address 1\",ADDRESS2 \"Address 2\",LOCATION_CODE \"Location Code\",NVL(CITY_CODE,'-') \"City Code\",NVL(POSTAL_CODE,'-') \"Postal Code\",NVL(CONTACT_NO,'-') \"Contact Number\",NVL(MOBILE_NO,'-') \"Mobile Number\",NVL(FAX_NO,'-') \"Fax Number\",NVL(SECTOR_CODE,'-') \"Sector Code\",NVL(COMMISSION_RATE,0) \"Commission Rate\",NVL(COMMISSION_AMOUNT,0) \"Commission Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.BROKER_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_BROKER A "+
					 "		WHERE A.BROKER_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.BROKER_CODE,A.ACTIVE_STATUS,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_BROKER_BK A  "+
					 "		WHERE  A.BROKER_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}
else if(Type.equals("AF_AD_AUTHORIZATION_LIMITS")){ // SK
		return " SELECT USER_ID \"User ID\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.USER_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_AUTHORIZATION_LIMITS A "+
					 "		WHERE A.USER_ID = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.USER_ID,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_AUTHORIZ_LIMITS_BK A  "+
					 "		WHERE  A.USER_ID = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					
}

else if(Type.equals("AF_AD_EAR_TEM_CHARGE")){ // SK
		return " SELECT TERMINATION_TYPE \"Termination Type\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.TERMINATION_TYPE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_EARLY_TERMI_CHARGE A "+
					 "		WHERE A.TERMINATION_TYPE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_TYPE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_EARLY_TERMI_CHAR_BK A  "+
					 "		WHERE  A.TERMINATION_TYPE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
}

else if(Type.equals("AF_AD_VARIABLE_INTEREST_BASE")){ // SK
		return " SELECT BASE_CODE \"Base Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
			" FROM  (SELECT A.BASE_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_MAS_INTEREST_BASE A "+
					 "		WHERE A.BASE_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_MAS_INTEREST_BASE_BK A  "+
					 "		WHERE  A.BASE_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
}
else if(Type.equals("AF_AD_VAR_BASE_RATE_NEW")){ // TJ
		return " SELECT BASE_CODE \"Base Code\",RATE \"Rate\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
			" FROM  (SELECT A.BASE_CODE,A.RATE,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_INTEREST_BASE_RATE A "+
					 "		WHERE A.BASE_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE,A.RATE,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_INTEREST_BASE_RA_BK A  "+
					 "		WHERE  A.BASE_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
}

else if(Type.equals("AF_AD_VAR_BASE_RATE")){ // SK
		return " SELECT BASE_CODE \"Base Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.BASE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_INTEREST_BASE_RATE A "+
					 "		WHERE A.BASE_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.BASE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_INTEREST_BASE_RA_BK A  "+
					 "		WHERE  A.BASE_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
}

else if(Type.equals("AF_AD_SCREEN_ORDER")){ // SK
		return " SELECT NVL(SCREEN_NAME,'-') \"Screen Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.SCREEN_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".CO_CO_MAS_USER_SCREEN A "+
					 "		WHERE A.SCREEN_NAME = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.SCREEN_NAME,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".CO_CO_MAS_USER_SCREEN_BK A  "+
					 "		WHERE  A.SCREEN_NAME = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
}


else if(Type.equals("AF_CO_MAS_RENTAL_TARGET_SETUP")){ // SK
		return " SELECT FINANCE_NO \"Finance Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
			" FROM  (SELECT A.FINANCE_NO,A.ENT_USER,A.ENT_DATE "+
					 "		FROM "+Schema+".AF_MAS_RENTAL_ARR_SETUP A "+
					 "		WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.FINANCE_NO,A.ENT_USER,A.ENT_DATE "+
					 "		FROM  "+Schema+".AF_MAS_RENTAL_ARR_SETUP_BK A  "+
					 "		WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY ENT_DATE DESC";
					 
}

else if (Type.equals("AF_AD_FUEL_TYPE")){//US fuel
	
		return " SELECT CODE \"Code\",DESCRIPTION \"Fuel Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_FUEL_TYPE A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_FUEL_TYPE_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_AD_LEGAL_ENTITY")){//US legal
	
		return " SELECT ENTITY_CODE \"Entity Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.ENTITY_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_LEGAL_ENTITY A "+
					 "        WHERE A.ENTITY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ENTITY_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_LEGAL_ENTITY_BK A  "+
					 "        WHERE  A.ENTITY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}

else if (Type.equals("AF_AD_GUARANTOR_CREATION")){//US red guaranter creation of guaranters
	
		return " SELECT CLIENT_CODE \"Client Code\",NVL(CLIENT_TYPE,'-')\"Client Type\",NVL(FULL_NAME,'-')\"Full Name\",NVL(BUSINESS_SUB_SECTOR,'-')\"Business Sub Sector\",NVL(DESIGNATION,'-')\"Designation\",NVL(CLIENT_CATEGORY,'-')\"Client Category\",NVL(AF_CLIENT,'-')\"Client\",NVL(NIC_NO,'-') \"N.I.C\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A  "+
					 "        WHERE  A.CLIENT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
else if (Type.equals("AF_AD_INITIATION_TYPE")){//US
	
		return " SELECT INITIATION_CODE \"Initiation Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.INITIATION_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_MK_MAS_INITIATION_TYPE A "+
					 "        WHERE A.INITIATION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INITIATION_CODE,A.DESCRIPTION,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_MK_MAS_INITIATION_TYPE_BK A  "+
					 "        WHERE  A.INITIATION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_CO_PRO_VAT_ON_RENTAL")){//US vat rental mod
	
		return " SELECT TRN_CODE \"Code\",APP_DATE \"App Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ITEM_SUB_CAT_CODE,'-') \"Item Sub Category Code\" "+
           " FROM  (SELECT A.TRN_CODE,A.APP_DATE,A.ITEM_SUB_CAT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_VAT_ON_RENTAL A "+
					 "        WHERE A.TRN_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TRN_CODE,A.APP_DATE,A.ITEM_SUB_CAT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_VAT_ON_RENTAL_BK A  "+
					 "        WHERE  A.TRN_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	TRN_CODE DESC";	 
	
	
	
	}
  
	else if (Type.equals("AF_AD_AREA")){//US Area new
	
		return " SELECT AREA_CODE \"Area Code\",AREA_DESC \"Area Description\",NVL(CITY_CODE,'-') \"City Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.AREA_CODE,A.AREA_DESC,A.CITY_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_AREA A "+
					 "        WHERE A.AREA_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.AREA_CODE,A.AREA_DESC,A.CITY_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_AREA_BK A  "+
					 "        WHERE  A.AREA_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}





	else if (Type.equals("AF_AD_DESIGNATION")){//US desig new
	
		return " SELECT DESIGNATION_CODE \"Designation Code\",DESIGNATION_NAME \"Designation Name\",NVL(DIVISION,'-') \"Division\",DESIGNATION_LEVEL \"Designation Level\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.DESIGNATION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.DESIGNATION_NAME,A.DIVISION,A.DESIGNATION_LEVEL,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_DESIGNATION A "+
					 "        WHERE A.DESIGNATION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.DESIGNATION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.DESIGNATION_NAME,A.DIVISION,A.DESIGNATION_LEVEL,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".CO_CO_MAS_DESIGNATION_BK A  "+
					 "        WHERE  A.DESIGNATION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}




else if (Type.equals("AF_AD_TEAM")){//US teams new
	
		return " SELECT TEAM_ID \"Team ID\",TEAM_DESC \"Team Description\",TEAM_HEAD \"Team Head\",SUB_DIVISION_CODE \"Sub Division Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.TEAM_ID,A.TEAM_DESC,A.TEAM_HEAD,A.SUB_DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_TEAMS A "+
					 "        WHERE A.TEAM_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEAM_ID,A.TEAM_DESC,A.TEAM_HEAD,A.SUB_DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_TEAMS_BK A  "+
					 "        WHERE  A.TEAM_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}




else if (Type.equals("AF_AD_MAKE_CREATION")){//US creation new
	
		return " SELECT MAKE_CODE \"Modified Creation Code\",MAKE_DESC \"Make Description\",ITEM_SUB_CAT \"Item Sub Category\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.MAKE_CODE,A.MAKE_DESC,A.ITEM_SUB_CAT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_MAKE A "+
					 "        WHERE A.MAKE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE,A.MAKE_DESC,A.ITEM_SUB_CAT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_MAKE_BK A  "+
					 "        WHERE  A.MAKE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}

else if (Type.equals("AF_AD_DOCUMENTS_REQUIRED")){//US
	
		return " SELECT CODE \"Code\",DESCRIPTION \"Description\",NVL(DOC_APP_TYPE,'-') \"Document App Type\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CODE,A.DESCRIPTION,A.DOC_APP_TYPE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_DOCUMENTS_REQUIRED A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE,A.DESCRIPTION,A.DOC_APP_TYPE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_DOC_REQUIRED_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	else if (Type.equals("AF_CO_MAS_CHEQ_RET_NARRATIONS")){//US
	
		return " SELECT CHQ_NARR_CODE \"Narration Code\",CHQ_NARRATIONS \"Cheque Narrations\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CHQ_NARR_CODE,A.CHQ_NARRATIONS,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CHEQUE_NARRATIONS A "+
					 "        WHERE A.CHQ_NARR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CHQ_NARR_CODE,A.CHQ_NARRATIONS,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CHEQUE_NARR_BK A  "+
					 "        WHERE  A.CHQ_NARR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
  
	else if (Type.equals("AF_AD_LEASE_PROCESS_STAGE")){//US
	
		return " SELECT STAGE_CODE \"Stage Code\",DIVISION_CODE \"Devision Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.STAGE_CODE,A.DESCRIPTION,A.DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_PROCESS_STAGE A "+
					 "        WHERE A.STAGE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.STAGE_CODE,A.DESCRIPTION,A.DIVISION_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_PROCESS_STAGE_BK A  "+
					 "        WHERE  A.STAGE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
  else if (Type.equals("AF_CO_MAS_ODI_TARGET_SETUP")){//US
	
		return " SELECT TARGET_MONTH \"Target Month\",ODI_RATE \"ODI Rate\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.TARGET_MONTH,A.ODI_RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_ODI_TARGET_SETUP A "+
					 "        WHERE A.TARGET_MONTH = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TARGET_MONTH,A.ODI_RATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_ODI_TARGET_SETUP_BK A  "+
					 "        WHERE  A.TARGET_MONTH = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";
	}
	else if (Type.equals("AF_AD_GROUP_USER")){//US red GROUP Drill
	
		return " SELECT GROUP_ID \"Group ID\",GROUP_DESC\"Group Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.GROUP_ID,A.GROUP_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".CO_CO_MAS_GROUP A "+
					 "        WHERE A.GROUP_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_ID,A.GROUP_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".CO_CO_MAS_GROUP_BK A  "+
					 "        WHERE  A.GROUP_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


else if (Type.equals("AF_AD_CLIENT_BACKLIST")){//US red CLient black list
	
		return " SELECT CLIENT_CODE \"Client Code\",NVL(CLIENT_TYPE,'-')\"Client Type\",NVL(FULL_NAME,'-')\"Full Name\",NVL(BUSINESS_SUB_SECTOR,'-')\"Business Sub Sector\",NVL(DESIGNATION,'-')\"Designation\",NVL(CLIENT_CATEGORY,'-')\"Client Category\",NVL(AF_CLIENT,'-')\"Client\",NVL(NIC_NO,'-') \"N.I.C\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT A "+
					 "        WHERE A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CLIENT_CODE,A.CLIENT_TYPE,A.FULL_NAME,A.BUSINESS_SUB_SECTOR,A.DESIGNATION,A.CLIENT_CATEGORY,A.AF_CLIENT,A.NIC_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_CLIENT_BK A  "+
					 "        WHERE  A.CLIENT_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CO_MAS_REFERENCE_ADMIN")){//US referance admin
	
		return " SELECT CODE \"Code\",NVL(INSERT_SCREEN,'-')\"Insert Screen\",NVL(TYPE_CODE,'-')\"Type Code\",NVL(TYPE_DESCRIPTION,'-')\"Type Description\",NVL(UPDATE_SCREEN,'-')\"Update Screen\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
           " FROM  (SELECT A.CODE,A.INSERT_SCREEN,A.TYPE_CODE,A.TYPE_DESCRIPTION,A.UPDATE_SCREEN,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_REFERENCE_ADMIN A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE,A.INSERT_SCREEN,A.TYPE_CODE,A.TYPE_DESCRIPTION,A.UPDATE_SCREEN,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_REFERENCE_ADMIN A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_AD_NATIONALITY")){//MC
	
		return " SELECT NATIONALITY_CODE \"Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.NATIONALITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_NATIONALITY A "+
					 "        WHERE A.NATIONALITY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.NATIONALITY_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_NATIONALITY_BK A  "+
					 "        WHERE  A.NATIONALITY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}


else if (Type.equals("AF_AD_BUSINESS_SECTOR")){//MC-2
	
		return " SELECT SECTOR_CODE \"Sector Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SECTOR_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_BUSINESS_SECTOR A "+
					 "        WHERE A.SECTOR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SECTOR_CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_BUSINESS_SECTOR_BK A  "+
					 "        WHERE  A.SECTOR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}


else if (Type.equals("AF_AD_CITY")){//MC-3
	
		return " SELECT CITY_CODE \"City Code\",CITY_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CITY_CODE, A.CITY_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CITY A "+
					 "        WHERE A.CITY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CITY_CODE, A.CITY_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CITY_BK A  "+
					 "        WHERE  A.CITY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

else if (Type.equals("AF_AD_LOCATION")){//MC-4
	
		return " SELECT LOCATION_CODE \"Location Code\",LOCATION_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.LOCATION_CODE, A.LOCATION_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_LOCATION A "+
					 "        WHERE A.LOCATION_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.LOCATION_CODE, A.LOCATION_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOCATION_BK A  "+
					 "        WHERE  A.LOCATION_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}



else if (Type.equals("AD_AD_SUB_TEAMS")){//MC-5
	
		return " SELECT SUB_TEAM_ID \"Sub team id\",SUB_TEAM_DESC \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SUB_TEAM_ID, A.SUB_TEAM_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_SUB_TEAMS A "+
					 "        WHERE A.SUB_TEAM_ID = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUB_TEAM_ID, A.SUB_TEAM_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_SUB_TEAMS_BK A  "+
					 "        WHERE  A.SUB_TEAM_ID = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}




else if (Type.equals("AF_AD_PRICE_DEFAULT_VALUE")){//MC-6
	
		return " SELECT INTEREST_RATE \"Interest Rate\",VAT_PER \"Percentage\",VAT_APP \"Vat Applicable\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.INTEREST_RATE, A.VAT_PER,A.VAT_APP,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_PRICING_DEFAULT_VAL A "+
					 "        WHERE A.INTEREST_RATE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INTEREST_RATE, A.VAT_PER,A.VAT_APP,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_PRICING_DEFAU_VAL_BK A  "+
					 "        WHERE  A.INTEREST_RATE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}


else if (Type.equals("AF_AD_MAINTANANCE")){//MC-7
	
		return " SELECT NVL(MAKE_CODE,'-') \"Make Code\",SUB_MODEL_CODE \"Model Code\",CHARGE_SUB_CODE \"Charge sub code\",NVL(MILEAGE_CODE,'-') \"Milage Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.MAKE_CODE, A.SUB_MODEL_CODE,A.CHARGE_SUB_CODE,A.MILEAGE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_MAINTENANCE_RATE A "+
					 "        WHERE A.CHARGE_SUB_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE, A.SUB_MODEL_CODE,A.CHARGE_SUB_CODE,A.MILEAGE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_MAS_MAINTENANCE_RATE_BK A  "+
					 "        WHERE  A.CHARGE_SUB_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

else if (Type.equals("AF_AD_MAINTANANCE")){//MC-7
	
		return " SELECT MAKE_CODE \"Make Code\",SUB_MODEL_CODE \"Model Code\",CHARGE_SUB_CODE \"Charge sub code\",MILEAGE_CODE \"Milage Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.MAKE_CODE, A.SUB_MODEL_CODE,A.CHARGE_SUB_CODE,A.MILEAGE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_MAINTENANCE_RATE A "+
					 "        WHERE A.MAKE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.MAKE_CODE, A.SUB_MODEL_CODE,A.CHARGE_SUB_CODE,A.MILEAGE_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_MAINTENANCE_RATE_BK A  "+
					 "        WHERE  A.MAKE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}



else if (Type.equals("AF_AD_CONDITION_OF_ASSET")){//MC-8
	
		return " SELECT CODE \"Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CONDITION_OF_ASSET A "+
					 "        WHERE A.CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CODE, A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_CONDI_OF_ASSET_BK A  "+
					 "        WHERE  A.CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}







else if (Type.equals("AF_AD_VENDOR_CREATION")){//MC-9
	
		return " SELECT VENDOR_CODE \"Vendor Code\",NAME \"Name\",NVL(CATEGORY,'-') \"Category\",NVL(TYPE,'-') \"Type\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.VENDOR_CODE, A.NAME,A.CATEGORY,A.TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_VENDORS A "+
					 "        WHERE A.VENDOR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VENDOR_CODE, A.NAME,A.CATEGORY,A.TYPE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_VENDORS_BK A  "+
					 "        WHERE  A.VENDOR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	
	else if (Type.equals("AF_AD_VALUER")){//MC-10
	
		return " SELECT VALUER_CODE \"Valuer Code\",FIRST_NAME \"Name\",LAST_NAME \"Last Name\",ADDRESS \"Address\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.VALUER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_VALUERS A "+
					 "        WHERE A.VALUER_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VALUER_CODE, A.FIRST_NAME,A.LAST_NAME,A.ADDRESS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_VALUERS_BK A  "+
					 "        WHERE  A.VALUER_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}



	else if (Type.equals("AF_AD_SCORE_SUB_CATEGORY")){//MC-11
	
		return " SELECT SCORE_SUB_CODE \"Score Sub Code\",DESCRIPTION \"Description\",SCORE_CODE \"Score Code\",DISPALY_POSITION \"Display Position\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.SCORE_SUB_CODE, A.DESCRIPTION,A.SCORE_CODE,A.DISPALY_POSITION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_MAS_SCORE_SUB_CATEGORY A "+
					 "        WHERE A.SCORE_SUB_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_SUB_CODE, A.DESCRIPTION,A.SCORE_CODE,A.DISPALY_POSITION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_MAS_SCORE_SUB_CAT_BK A  "+
					 "        WHERE  A.SCORE_SUB_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_AD_ACCOUNT_CODE")){//MC-12
	
		return " SELECT ACC_TYPE_CODE \"Account type Code\",NVL(ACC_TYPE_DESC,'-') \"Description\",NVL(ACC_TYPE_CATEGORY,'-') \"Account Category\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.ACC_TYPE_CODE, A.ACC_TYPE_DESC,A.ACC_TYPE_CATEGORY,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".CO_FN_MAS_ACCOUNT_CODE A "+
					 "        WHERE A.ACC_TYPE_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ACC_TYPE_CODE, A.ACC_TYPE_DESC,A.ACC_TYPE_CATEGORY,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".CO_FN_MAS_ACCOUNT_CODE_BK A  "+
					 "        WHERE  A.ACC_TYPE_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
else if (Type.equals("AF_AD_LOAN_FACILITIES_ASSIGN")){//MC-13
	
		return " SELECT LOAN_FACILITY_NO \"Facility No\",FINANCE_NO \"Finance No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.LOAN_FACILITY_NO, A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_LOAN_FACILI_ASSIGN A "+
					 "        WHERE A.LOAN_FACILITY_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.LOAN_FACILITY_NO, A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_MAS_LOAN_FACILI_AS_BK A  "+
					 "        WHERE  A.LOAN_FACILITY_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}


	else if (Type.equals("FA_CO_MAS_FEE_STRUCT")){//MC-14
	
		return " SELECT FEE_PACK_CODE \"Fee Pack Code\",FEE_PACK_DESC \"Fee Pack Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",FEE_CODE \"Fee Code\",PACK_DESC \"Pack Description\", ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.FEE_PACK_CODE, A.FEE_PACK_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,B.FEE_CODE,B.PACK_DESC,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".FA_CO_MAS_FEES_PACKS A,"+Schema+".FA_CO_MAS_FEE_PACK_DET B "+
					 "        WHERE A.FEE_PACK_CODE = '"+Filter+"' AND A.FEE_PACK_CODE=B.FEE_PACK_CODE  AND  "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FEE_PACK_CODE, A.FEE_PACK_DESC,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,B.FEE_CODE,B.PACK_DESC,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".FA_CO_MAS_FEES_PACKS_BK A,"+Schema+".FA_CO_MAS_FEE_PACK_DET_BK B  "+
					 "        WHERE  A.FEE_PACK_CODE = '"+Filter+"'AND A.FEE_PACK_CODE=B.FEE_PACK_CODE AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}




else if (Type.equals("AF_AD_CLIENT_COMMENT_ADDITION")){//MC-15
	
		return " SELECT CLIENT_CODE \"Client Code\",APPLICATION_NO \"Application No\",COMMENTS \"Comments\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",CASHIER_STATUS \" Cashier Status\" "+
           " FROM  (SELECT A.CLIENT_CODE, A.APPLICATION_NO,A.COMMENTS,A.ENT_USER,A.ENT_DATE,A.CASHIER_STATUS "+
					 "        FROM "+Schema+".AF_CO_MAS_CLIENT_COMMENT A "+
					 "        WHERE A.CLIENT_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )  "+
					 "              	) "+
					 "        ) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	}

	
	else if (Type.equals("AF_CO_MAS_TARGET_MONTHS")){//MC-16
	
		return " SELECT AGE \"Age\",PRECENTAGE \"Percentage\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
           " FROM  (SELECT A.AGE, A.PRECENTAGE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_MAS_TARGET_MONTHS A "+
					 "        WHERE A.AGE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )  "+
					 "              	) "+
					 "        ) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	}

 else if (Type.equals("LOGIN_DETAILS")){//MC-16
	
		return " SELECT USER_NAME \"User ID\",CLIENT_IP \"IP\",SCREEN_NAME \"Screen ID\",ENT_DATE \"Entered Date\" "+
           //" FROM  (SELECT A.USER_NAME,A.ENT_DATE,A.SCREEN_NAME,A.CLIENT_IP,A.LOGIN_STATUS "+ // commented by udara 03-12-2025
			" FROM  (SELECT A.USER_NAME,A.ENT_DATE,A.SCREEN_NAME,A.CLIENT_IP "+ // added by udara 03-12-2025
					 "        FROM "+Schema+".CO_CO_PRO_LOGIN_LOG A "+
					 "        WHERE A.USER_NAME = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )  "+
					 "              	) "+
					 "        ) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	}

 	else if (Type.equals("AF_AD_PASSWORD_POLICY")){//MC-16
	
		return " SELECT MINIMUM_LENGTH \"Max Length\",MAXIMUM_LENGTH \"Min Length\",LOWERCASE_CHAR \"LOWERCASE CHAR\" ,UPPERCASE_CHAR \"UPPERCASE CHAR\",NUMERIC_CHAR \"NUMERIC CHAR\",NUMERIC_EMBEDDED \"NUMERIC EMBEDDED\",SPECIAL_CHAR \"SPECIAL CHAR\",SPECIAL_CHAR_EMBEDDED \"SPECIAL CHAR EMBEDDED\",PASSWORD_CHANGE_ATTEMPT \"PASSWORD CHANGE ATTEMPT\", "+
 			   " ALLOW_USER_NAME \"ALLOW USER NAME\",REPEAT_PASSWORD \"REPEAT PASSWORD\",PASSWORD_CHANGE_MINIMUM_GAP \"PASSWORD CHANGE MINIMUM GAP\",PASSWORD_EXPIRATION_DAYS \"PASSWORD EXPIRATION DAYS\",WARNING_MESSAGE_DAYS \"WARNING MESSAGE DAYS\",ENT_USER \"ENT USER\",ENT_DATE \"ENT DATE\",MOD_USER \"MOD USER\",MOD_DATE \"MOD DATE\" "+
					 "        FROM (SELECT A.MINIMUM_LENGTH,A.MAXIMUM_LENGTH,A.LOWERCASE_CHAR,A.UPPERCASE_CHAR,A.NUMERIC_CHAR,A.NUMERIC_EMBEDDED,A.SPECIAL_CHAR,A.SPECIAL_CHAR_EMBEDDED,A.PASSWORD_CHANGE_ATTEMPT,A.ALLOW_USER_NAME,A.REPEAT_PASSWORD,A.PASSWORD_CHANGE_MINIMUM_GAP,A.PASSWORD_EXPIRATION_DAYS,A.WARNING_MESSAGE_DAYS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,A.MOD_DATE "+
					 " 		  FROM "+Schema+".CO_CO_MAS_PASSWORD_POLICY A "+
					 "        WHERE A.ENT_USER = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "  			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND TO_DATE(A.MOD_DATE,'DD-MM-YYYY')<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) )    "+	
					 "        UNION ALL "+
						
					 " 		  SELECT A.MINIMUM_LENGTH,A.MAXIMUM_LENGTH,A.LOWERCASE_CHAR,A.UPPERCASE_CHAR,A.NUMERIC_CHAR,A.NUMERIC_EMBEDDED,A.SPECIAL_CHAR,A.SPECIAL_CHAR_EMBEDDED,A.PASSWORD_CHANGE_ATTEMPT,A.ALLOW_USER_NAME,A.REPEAT_PASSWORD,A.PASSWORD_CHANGE_MINIMUM_GAP,A.PASSWORD_EXPIRATION_DAYS,A.WARNING_MESSAGE_DAYS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,A.MOD_DATE "+
					 " 		  FROM "+Schema+".CO_CO_MAS_PASSWORD_POLICY_BK A "+
					 "        WHERE A.ENT_USER = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "  			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND TO_DATE(A.MOD_DATE,'DD-MM-YYYY')<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) ) 	  "+
					 "        ) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	}




	else{
		 return " ";
   }	
 }	
		
 
}




