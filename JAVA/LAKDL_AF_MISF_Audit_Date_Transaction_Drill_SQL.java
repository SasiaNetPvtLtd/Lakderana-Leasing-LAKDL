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

public class LAKDL_AF_MISF_Audit_Date_Transaction_Drill_SQL  
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
	

	
 public String getQuery_drill(String Schema,String FDate,String TDate,String Type,String Filter) {
	
   if (Type.equals("AF_MK_INQUIRY")){//DJ--1
		
	  return " SELECT INQUIRY_CODE \"Inquiry Code\",CLIENT_NAME \"Client Name\",INITIATION_TYPE \"Initiation Type\",CLIENT_CATEGORY \"Client Category\",NVL(MK_OFFICER,'-') \"Officer\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.INQUIRY_CODE, A.CLIENT_NAME,A.INITIATION_TYPE,A.CLIENT_CATEGORY,A.MK_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_MK_PRO_INQUIRY A "+
					 "        WHERE A.INQUIRY_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INQUIRY_CODE, A.CLIENT_NAME,A.INITIATION_TYPE,A.CLIENT_CATEGORY,A.MK_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".AF_MK_PRO_INQUIRY_BK A  "+
					 "        WHERE  A.INQUIRY_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";

	}

  else if (Type.equals("AF_CR_PRO_CANCEL_AFTER_PO")){//DJ--2
	
	 return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",NVL(INSURANCE_OFFICER,'-') \"Insurance Officer\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APPLICATION_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	

	}
	
	else if (Type.equals("AF_CR_PRO_RENTAL_DATE_CHANGE")){//DJ--3
	
		return " SELECT APPLICATION_NO \"Application No\",PRICING_NO \"Pricing No\",INSTALLMENT_NO \"Installment No\",RENTAL_DATE \"Rental Date\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.PRICING_NO,A.INSTALLMENT_NO,A.RENTAL_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INSTALLMENT A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.PRICING_NO,A.INSTALLMENT_NO,A.RENTAL_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INSTALLMENT_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_STANDING_ORDER")){//DJ--4
	
		return " SELECT FINANCE_NO \"Finance No\",SO_NO \"So No\",NVL(ACC_NO,'-') \"Account No\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.SO_NO,A.ACC_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_STANDING_ORDERS A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_ACTIVATED_DATE_CHANGE")){//DJ--5
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",NVL(INSURANCE_OFFICER,'-') \"Insurance Officer\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APPLICATION_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_STANDING_ORDER_APP")){//DJ--6
	
		 return " SELECT FINANCE_NO \"Finance No\",SO_NO \"So No\",NVL(ACC_NO,'-') \"Account No\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.SO_NO,A.ACC_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_STANDING_ORDERS A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_ADD_GUARANTOR")){//DJ--7
	
		return " SELECT GUARANTOR_CODE \"Guarantor Code\",RELATIONSHIP \"Relationship\",PERIOD \"Period\",APPLICATION_NO \"Application No\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.GUARANTOR_CODE, A.RELATIONSHIP,A.PERIOD,A.APPLICATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLI_GUARANTOR A "+
					 "        WHERE A.GUARANTOR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GUARANTOR_CODE, A.RELATIONSHIP,A.PERIOD,A.APPLICATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLI_GUARANTOR_BK A  "+
					 "        WHERE  A.GUARANTOR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_DEL_GUARANTOR")){//DJ--8
	
		return " SELECT GUARANTOR_CODE \"Guarantor Code\",RELATIONSHIP \"Relationship\",PERIOD \"Period\",APPLICATION_NO \"Application No\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.GUARANTOR_CODE, A.RELATIONSHIP,A.PERIOD,A.APPLICATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLI_GUARANTOR A "+
					 "        WHERE A.GUARANTOR_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GUARANTOR_CODE, A.RELATIONSHIP,A.PERIOD,A.APPLICATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLI_GUARANTOR_BK A  "+
					 "        WHERE  A.GUARANTOR_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_APPLICATION_REVERSAL")){//DJ--9
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",NVL(INSURANCE_OFFICER,'-') \"Insurance Officer\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APPLICATION_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";		
	
	}
	
	else if (Type.equals("AF_CR_PRO_APPLICATION_REVERSE")){//DJ--10
	
		return " SELECT SCORE_MODEL_CODE \"Score Model Code\",APPLICATION_CODE \"Application Code\",NVL(APP_COMMENT,'-') \"Application Comment\",EVAL_USER \"Eval User\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APP_STATUS \"Status\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE, A.APPLICATION_CODE,A.APP_COMMENT,A.EVAL_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APP_STATUS "+
					 "        FROM "+Schema+".AF_CR_PRO_CRSCORE A "+
					 "        WHERE A.SCORE_MODEL_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE, A.APPLICATION_CODE,A.APP_COMMENT,A.EVAL_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APP_STATUS "+
					 "        FROM  "+Schema+".AF_CR_PRO_CRSCORE_BK A  "+
					 "        WHERE  A.SCORE_MODEL_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_APPLICATION_ACTIVATED")){//DJ--11
	
		return " SELECT APPLICATION_NO \"Application No\",NVL(NEW_CATITAL_AMT,0) \"New Catital Amount\",NVL(NEW_INTERREST_AMT,0) \"New Interrest Amount\",NVL(ADJUSTED_AMOUNT,0) \"Ajusted Amount\", ENT_DATE \"Entered Date\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.NEW_CATITAL_AMT,A.NEW_INTERREST_AMT,A.ADJUSTED_AMOUNT,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_LOG_ACTIVATION_ERROR A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " ORDER BY	ENT_DATE DESC";	
	}
	
	
	else if (Type.equals("AF_CR_PRO_CHANGE_RENTAL_AMOUNT")){//DJ--12
	
		return " SELECT APPLICATION_NO \"Application No\",PRICING_NO \"Pricing No\",INSTALLMENT_NO \"Installment No\",RENTAL_DATE \"Rental Date\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.PRICING_NO,A.INSTALLMENT_NO,A.RENTAL_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INSTALLMENT A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.PRICING_NO,A.INSTALLMENT_NO,A.RENTAL_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INSTALLMENT_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_CHANGE_INSUARANCE_DATE")){//DJ--13
	
		return " SELECT FINANCE_NO \"Finance No\",PRO_INVOICE_NO \"Pro Invoice number\",POLICY_NO \"Policy Number\",NVL(ASSET_DESCRIPTION,'-') \"Asset Description\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.FINANCE_NO, A.PRO_INVOICE_NO,A.POLICY_NO,A.ASSET_DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_IS_PRO_ASET_INSUR_DETA A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.PRO_INVOICE_NO,A.POLICY_NO,A.ASSET_DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_IS_PRO_ASET_INSUR_DETA_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_CREDIT_DEBIT_NOTE")){//DJ--14
	
		return " SELECT REF_NO \"Reference Number\",NVL(FINANCE_NO,'-') \"Finance No\",NVL(CLIENT_CODE,'-') \"Client Code\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.REF_NO, A.FINANCE_NO,A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_CR_DR_DETAILS A "+
					 "        WHERE A.REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REF_NO, A.FINANCE_NO,A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_CR_DR_DETAILS_BK A  "+
					 "        WHERE  A.REF_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_CHANGE_ACTIVATED_DATE")){//DJ--15
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",NVL(INSURANCE_OFFICER,'-') \"Insurance Officer\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",APPLICATION_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.INSURANCE_OFFICER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}

	else if (Type.equals("AF_RE_COLLECTION_ADVEST_GEN")){//DJ--16
	
		return " SELECT ADVER_NO \"Advertisement No\",NVL(INVENTORY_NO,'-') \"Inventory No\",NVL(VEHICLE_NO,'-') \"Vehicle No\",ADVER_DATE \"Advertisement Date\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.ADVER_NO, A.INVENTORY_NO,A.VEHICLE_NO,A.ADVER_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_ADVERTISEMENT_DETAIL A "+
					 "        WHERE A.ADVER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ADVER_NO, A.INVENTORY_NO,A.VEHICLE_NO,A.ADVER_DATE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_ADVERTISEMENT_DET_BK A  "+
					 "        WHERE  A.ADVER_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_RE_INVENTORY_APPROVAL")){//DJ--17
	
		return " SELECT VEHICLE_NO \"Vehicle Number\",CUSTOMER_NAME \"Customer Name\",INVENTORY_NO \"Inventory Number\",REPOSSESSION_NO \"Repossession number\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.VEHICLE_NO, A.CUSTOMER_NAME,A.INVENTORY_NO,A.REPOSSESSION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_VEHICLE_INVENTORY A "+
					 "        WHERE A.VEHICLE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VEHICLE_NO, A.CUSTOMER_NAME,A.INVENTORY_NO,A.REPOSSESSION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_VEHICLE_INVENT_BK A  "+
					 "        WHERE  A.VEHICLE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_RE_COLLECTION_ADVEST_OFFER_PROCESS")){//DJ--18
	
		return " SELECT OFFER_NO \"Offer No\",ADVER_NO \"Advertisement No\",FULL_NAME \"Full Name\",NVL(INVENTORY_NO,'-') \"Inventory No\",ENT_USER \"Entered User\", ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STATUS \"Status\" "+
           " FROM  (SELECT A.OFFER_NO, A.ADVER_NO,A.FULL_NAME,A.INVENTORY_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_ADVERTISEMENT_OFFERS A "+
					 "        WHERE A.OFFER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.OFFER_NO, A.ADVER_NO,A.FULL_NAME,A.INVENTORY_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_ADVERTISEMENT_OFF_BK A  "+
					 "        WHERE  A.OFFER_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_MK_PRICE")){//TJ 
	
		return " SELECT PRICING_NO \"Pricing No \",GROSS_AMOUNT \"Gross Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.PRICING_NO, A.GROSS_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_MK_PRO_PRICING A "+
					 "        WHERE A.PRICING_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}

	else if (Type.equals("AF_MK_APP_STATUS_APPROVE_1")){//TJ
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(APPLICATION_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}

	else if (Type.equals("AF_CR_PRO_CREDIT_EVA_DETAILS")){//TJ
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(APPLICATION_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_ENTER_LEASE")){//TJ
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(APPLICATION_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.APPLICATION_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_STIPULATED_MAIN_SCREEN")){//TJ
	
		return " SELECT FINANCE_NO \"Finance No\",NVL(NET_AMOUNT,0) \"Net Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.FINANCE_NO, A.NET_AMOUNT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_STIPULATED_VALUE A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.NET_AMOUNT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_PRO_STIPULATED_VALUE_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_PUR_MAIN_SCREEN")){//TJ
	
		return " SELECT PURCHASE_ORDER_NO \"Purchase Order No\",APPLICATION_NO \"Application No\",VENDER_CODE \"Vender Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A "+
					 "        WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A  "+
					 "        WHERE  A.PURCHASE_ORDER_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	}
	
	else if (Type.equals("AF_CR_PRO_PURCHASE_ORDER_APPROVAL")){//TJ
	
		return " SELECT PURCHASE_ORDER_NO \"Purchase Order No\",APPLICATION_NO \"Application No\",VENDER_CODE \"Vender Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A "+
					 "        WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A  "+
					 "        WHERE  A.PURCHASE_ORDER_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_PURCHASE_ORDER_MAIN_SCREEN_DELETION")){//TJ
	
		return " SELECT PURCHASE_ORDER_NO \"Purchase Order No\",APPLICATION_NO \"Application No\",VENDER_CODE \"Vender Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A "+
					 "        WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO, A.APPLICATION_NO,A.VENDER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A  "+
					 "        WHERE  A.PURCHASE_ORDER_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	}
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_REQUSITION_MAIN1")){//TJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",PAY_AMOUNT \"Pay Amount\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",PROCESS_STATUS \"Status\" "+ 
           " FROM  (SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
					 "        WHERE  A.PAYMENT_NO = '"+Filter+"' AND "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_REQ_SPECIAL_APPROVAL")){//TJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",PAY_AMOUNT \"Pay Amount\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",PROCESS_STATUS \"Status\" "+ 
           " FROM  (SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
					 "        WHERE  A.PAYMENT_NO = '"+Filter+"' AND "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_REQUSITION_LOAN")){//TJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",PAY_AMOUNT \"Pay Amount\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",PROCESS_STATUS \"Status\" "+ 
           " FROM  (SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO, A.SUS_REF_NO,A.PAY_AMOUNT,A.PROCESS_STATUS,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
					 "        WHERE  A.PAYMENT_NO = '"+Filter+"' AND "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	}
	
	else if (Type.equals("AF_CR_PRO_INVOICE_REVERSAL_OPTION")){//TJ
	
		return " SELECT INVOICE_NO \"Invoice No\",FINANCE_NO \"Finance No\",TOTAL_AMOUNT \"Total Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.INVOICE_NO, A.FINANCE_NO,A.TOTAL_AMOUNT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO, A.FINANCE_NO,A.TOTAL_AMOUNT,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_AGM_COMMENT")){//TJ
	
		return " SELECT APPLICATION_NO \"Application No\",STAGE \"Stage\",AUTHORAIZED_USER \"Authoraized User\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",STATUS \"Status\" "+ 
           " FROM  (SELECT A.APPLICATION_NO, A.STAGE,A.AUTHORAIZED_USER,A.STATUS,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_APPROVAL A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_CRIB_LETTER")){//TJ
	
		return " SELECT CREIB_REF_NO \"CREIB Ref No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+ 
           " FROM  (SELECT A.CREIB_REF_NO, A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CR_PRO_CRIB_REQ_DETAILS A "+
					 "        WHERE A.CREIB_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_FOLLOWUP_ENTRY")){//TJ
	
		return " SELECT FOLLOW_UP_NO \"Follow Up No\",ACTION_TOBE_TAKEN \"Action To Be Taken\",SCREEN_NAME \"Screen Name\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(STATUS,'-') \"Status\" "+ 
           " FROM  (SELECT A.FOLLOW_UP_NO, A.ACTION_TOBE_TAKEN,A.SCREEN_NAME,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_FOLLOW_UP A "+
					 "        WHERE A.FOLLOW_UP_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FOLLOW_UP_NO, A.ACTION_TOBE_TAKEN,A.SCREEN_NAME,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_FOLLOW_UP_BK A  "+
					 "        WHERE  A.FOLLOW_UP_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}

	else if (Type.equals("AF_MK_APPLICATION_PROCESS")){//MC-17
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", APPLICATION_STATUS \"Application Status\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.APPLICATION_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}
	
	else if (Type.equals("AF_CR_INVOICE_ADJUSTMENTS")){//MC-18
	
		return " SELECT FINANCE_NO \"Finance No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_CREDIT_DETAILS A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_CREDIT_DETAILS_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}
	
	
	else if (Type.equals("AF_RE_OTHER_INVOICES")){//MC-19
	
		return " SELECT FINANCE_NO \"Finance No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_ODI_ADJUST_APPROVAL")){//MC-20
	
		return " SELECT ALLOCATION_NO \"Allocation No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", STATUS \"Status\" "+
           " FROM  (SELECT A.ALLOCATION_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
					 "        WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ALLOCATION_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A  "+
					 "        WHERE  A.ALLOCATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}
	
	else if (Type.equals("AF_RE_OTHER_INVOICES_FOR_TERMI_CON")){//MC-21
	
		return " SELECT FINANCE_NO \"Finance No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
 }


 else if (Type.equals("AF_CR_PRO_CHANGING_PAYEE_CODE")){//MC-22
	
		return " SELECT SUS_REF_NO \"Reference No\",RECEIVER \"Receiver \",ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Last Mod Date\" "+
           " FROM  (SELECT A.SUS_REF_NO, A.RECEIVER,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUS_REF_NO, A.RECEIVER,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
					 "        WHERE  A.SUS_REF_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	
	
	
	
	
	}
	
	else if (Type.equals("AF_RE_DEBIT_NOTE_CANCEL")){//MC-23
	
		return " SELECT FINANCE_NO \"Finance No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}


	else if (Type.equals("AF_RE_CREDIT_NOTE_CANCEL")){//MC-24
	
		return " SELECT FINANCE_NO \"Finance No\",INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", ACTIVE_STATUS \"Active Status\" "+
           " FROM  (SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_CREDIT_DETAILS A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO, A.INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_CREDIT_DETAILS_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_DELETION")){//MC-25
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(CLIENT_CODE,'-') \"Client Code\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", PROCESS_STATUS \"Process Status\" "+
           " FROM  (SELECT A.PAYMENT_NO, A.CLIENT_CODE,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PROCESS_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO, A.CLIENT_CODE,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PROCESS_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
					 "        WHERE  A.PAYMENT_NO = '"+Filter+"' AND "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	

	
	}
	
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAY_ACCOUNT_SELECT")){//MC-26
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(CLIENT_CODE,'-') \"Client Code\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", PROCESS_STATUS \"Process Status\" "+
           " FROM  (SELECT A.PAYMENT_NO, A.CLIENT_CODE,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PROCESS_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 
					 "              ) "+
					 " ORDER BY	MOD_DATE DESC";	

	
	}
	
	
	else if (Type.equals("AF_CR_PRO_PAYMENTS_CLEARING")){//MC-27
	
		return " SELECT SUS_REF_NO \"Reference No\",RECEIVER \"Receiver \",ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Last Mod Date\" "+
           " FROM  (SELECT A.SUS_REF_NO, A.RECEIVER,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.SUS_REF_NO, A.RECEIVER,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
					 "        WHERE  A.SUS_REF_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	
	
	
	
	
	}
	
	
	
	else if (Type.equals("AF_CR_ODI_ADJUST_APPROVAL1")){//MC-28
	
		return " SELECT ALLOCATION_NO \"Allocation No\",INVOICE_NO \"Invoice No \",ENT_DATE \"Entered Date\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.ALLOCATION_NO, A.INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
					 "        WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ALLOCATION_NO, A.INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A  "+
					 "        WHERE  A.ALLOCATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	

	}
	
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS_AUTHO")){//MC-29
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(CLIENT_CODE,'-') \"Client No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\", PROCESS_STATUS \"Process Status\" "+
           " FROM  (SELECT A.PAYMENT_NO, A.CLIENT_CODE,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PROCESS_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO, A.CLIENT_CODE,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PROCESS_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
					 "        WHERE  A.PAYMENT_NO = '"+Filter+"' AND "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	

	
	}
	
	
	
	else if (Type.equals("AF_FN_PRO_INCOME_PROVISION")){//MC-30
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
           " FROM  (SELECT A.APPLICATION_NO, A.CLIENT_CODE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_PROVISION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )  "+
					 "              ) "+
					 
					 "              ) "+
					 " ORDER BY	ENT_DATE DESC";	
	
	
	
	
	}
	
	
	else if (Type.equals("AF_REPOSSESSION")){//MC-31
	
		return " SELECT REPOSSESSION_NO \"Repossession No\",PRO_INVOICE_NO \"pro Invoice No \",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.REPOSSESSION_NO, A.PRO_INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.MOD_USER "+
					 "        FROM "+Schema+".AF_RE_PRO_REPOSSESSION A "+
					 "        WHERE A.REPOSSESSION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REPOSSESSION_NO, A.PRO_INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.MOD_USER "+
					 "        FROM  "+Schema+".AF_RE_PRO_REPOSSESSION A  "+
					 "        WHERE  A.REPOSSESSION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	}
	
	
	else if (Type.equals("AF_REVERSE_REPOSSESSION")){//MC-32
	
		return " SELECT REPOSSESSION_NO \"Repossession No\",PRO_INVOICE_NO \"pro Invoice No \",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.REPOSSESSION_NO, A.PRO_INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.MOD_USER "+
					 "        FROM "+Schema+".AF_RE_PRO_REPOSSESSION A "+
					 "        WHERE A.REPOSSESSION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REPOSSESSION_NO, A.PRO_INVOICE_NO,A.ENT_DATE,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.MOD_USER "+
					 "        FROM  "+Schema+".AF_RE_PRO_REPOSSESSION A  "+
					 "        WHERE  A.REPOSSESSION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	
	
	
	
	
	}

	else if(Type.equals("AF_AD_INDICATIVE_QUOTATION")){ // SK
	
		return " SELECT MAIN_CODE \"Main Code\",CODE \"Code\",DESCRIPTION \"Description\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",ACTIVE_STATUS \"Active Status\" "+
			" FROM  (SELECT A.MAIN_CODE,A.CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.ACTIVE_STATUS "+
					 "		FROM "+Schema+".AF_MK_CONDITIONS_MAIN A "+
					 "		WHERE A.MAIN_CODE = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.MAIN_CODE,A.CODE,A.DESCRIPTION,A.ENT_USER,A.ENT_DATE,A.ACTIVE_STATUS "+
					 "		FROM  "+Schema+".AF_MK_CONDITIONS_MAIN_BK A  "+
					 "		WHERE  A.MAIN_CODE = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY ENT_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_CALC")){ // SK

		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_ALLO")){ // SK
	
		return " SELECT INVOICE_NO \"Invoice Number\",RECEIPT_NO \"Receipt Number\",NVL(INVOICED_AMOUNT,0) \"Invoiced Amount\",NVL(RECEIPT_AMOUNT,0) \"Receipt Amount\",ALLOCATED_DATE \"Allocated Date\",NVL(SETTELED_AMOUNT,0) \"Setteled Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.INVOICE_NO,A.RECEIPT_NO,A.INVOICED_AMOUNT,A.RECEIPT_AMOUNT,A.ALLOCATED_DATE,A.SETTELED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_INVOICE_DETAILS A "+
					 "		WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.INVOICE_NO,A.RECEIPT_NO,A.INVOICED_AMOUNT,A.RECEIPT_AMOUNT,A.ALLOCATED_DATE,A.SETTELED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_INVOICE_DETAILS_BK A  "+
					 "		WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_CHECK")){ // SK
	
		return " SELECT APPLICATION_NO \"Application Number\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(CLIENT_NO,0) \"Client Number\",NVL(INQUARY_NO,'-') \"Inquary Number\",NVL(FINANCE_NO,'-') \"Finance Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "		WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "		WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_LEGAL")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(CLIENT_CODE,'-') \"Client Code\",TERMINATION_VALIDITY_DATE \"Termination Validity Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_APPR1")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_APPR2")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(CLIENT_CODE,'-') \"Client Code\",TERMINATION_VALIDITY_DATE \"Termination Validity Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK2")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(CLIENT_CODE,'-') \"Client Code\",TERMINATION_VALIDITY_DATE \"Termination Validity Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK3")){ // SK
	
		return " SELECT TERMINATION_NO \"Termination Number\",NVL(FINANCE_NO,'-') \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(CLIENT_CODE,'-') \"Client Code\",TERMINATION_VALIDITY_DATE \"Termination Validity Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
					 "		WHERE A.TERMINATION_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.TERMINATION_NO,A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,A.TERMINATION_VALIDITY_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A  "+
					 "		WHERE  A.TERMINATION_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_SECURITY_MARKETTING_APPR")){ // SK
	
		return " SELECT FINANCE_NO \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(STATUS,'-') \"Status\",NVL(TOTAL_COUNT,0) \"Total Count\",NVL(IN_COUNT,0) \"In Count\",NVL(OUT_COUNT,0) \"Out Count\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_SECURITYFILE_MOVMENT A "+
					 "		WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.FINANCE_NO,A.APPLICATION_NO,A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_SECURITYFILE_MOV_BK A  "+
					 "		WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CR_SECURITY_MARKETTING_FILE")){ // SK
	
		return " SELECT FINANCE_NO \"Finance Number\",NVL(APPLICATION_NO,'-') \"Application Number\",NVL(STATUS,'-') \"Status\",NVL(TOTAL_COUNT,0) \"Total Count\",NVL(IN_COUNT,0) \"In Count\",NVL(OUT_COUNT,0) \"Out Count\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
			" FROM  (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_SECURITYFILE_MOVMENT A "+
					 "		WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.FINANCE_NO,A.APPLICATION_NO,A.STATUS,A.TOTAL_COUNT,A.IN_COUNT,A.OUT_COUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_SECURITYFILE_MOV_BK A  "+
					 "		WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_CO_DAYEND")){ // SK
	
		return " SELECT DAY_END_DATE \"Day End Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
			" FROM  (SELECT A.DAY_END_DATE,A.ENT_USER,A.ENT_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
					 "		WHERE A.DAY_END_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.DAY_END_DATE,A.ENT_USER,A.ENT_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_SYS_PARAMETER A  "+
					 "		WHERE  A.DAY_END_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY ENT_DATE DESC";
					 
 }



 else if(Type.equals("AF_RE_COLLECTION_LEGAL_ACTIVITIES")){ // SK
	
		return " SELECT LEGAL_NO \"Legal Number\",FINANCE_NO \"Finance Number\",CLIENT_CODE \"Client Code\",LAWYER_CODE \"Lawyer Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.LEGAL_NO,A.FINANCE_NO,A.CLIENT_CODE,A.LAWYER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_RE_PRO_LEGAL_ACTIVITIES A "+
					 "		WHERE A.LEGAL_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.LEGAL_NO,A.FINANCE_NO,A.CLIENT_CODE,A.LAWYER_CODE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_RE_PRO_LEGAL_ACTIVITIES_BK A  "+
					 "		WHERE  A.LEGAL_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_RE_COLLECTION_LEGAL_ACTIONS")){ // SK
	
		return " SELECT LEGAL_NO \"Legal Number\",LEGAL_DATE \"Legal Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",NVL(ACTIVE_STATUS,'-') \"Active Status\" "+
			" FROM  (SELECT A.LEGAL_NO,A.LEGAL_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM "+Schema+".AF_RE_PRO_LEGAL_ACTIONS A "+
					 "		WHERE A.LEGAL_NO = '"+Filter+"' AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.LEGAL_NO,A.LEGAL_DATE,A.ACTIVE_STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "		FROM  "+Schema+".AF_RE_PRO_LEGAL_ACTIONS_BK A  "+
					 "		WHERE  A.LEGAL_NO = '"+Filter+"' AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY MOD_DATE DESC";
					 
 }



 else if(Type.equals("AF_DAY_END_ROUTING")){ // SK
	
		return " SELECT DAY_END_DATE \"Day End Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
			" FROM  (SELECT A.DAY_END_DATE,A.ENT_USER,A.ENT_DATE "+
					 "		FROM "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
					 "		WHERE A.DAY_END_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND   "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "		UNION ALL "+
					 "		SELECT A.DAY_END_DATE,A.ENT_USER,A.ENT_DATE "+
					 "		FROM  "+Schema+".AF_CO_PRO_SYS_PARAMETER A  "+
					 "		WHERE  A.DAY_END_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND "+
					 "			((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY ENT_DATE DESC";
					 
 }
	
 else if (Type.equals("AF_CR_PRO_PAYMENT_REQ_MAIN")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_PRO_CR_TEMP_PAYMENT1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(INVOICE_NO,'-') \"Invoice No\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",NVL(REG_NO,'-') \"Reg No\",NVL(DISTRICT_CODE,'-') \"Distric Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Modified Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.INVOICE_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.DISTRICT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAILS A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.INVOICE_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.DISTRICT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAI_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	else if (Type.equals("AF_PRO_CR_TEMP_PAYMENT")){//CJ
	
		return " SELECT PURCHASE_ORDER_NO \"Purchase Order No\",NVL(ASSET_ID,'-') \"Asset Id\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",PRO_INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Modified Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.PURCHASE_ORDER_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRO_INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER_DET A "+
					 "        WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.PURCHASE_ORDER_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRO_INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCH_ORDER_DET_BK A  "+
           "   WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_SUS_PAYMENT_ENTER")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_PRINT1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_DISBURSE1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_CANCEL1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_FINANCE_ACTIVATION")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_CHANGE_CAP_ALLOW")){//CJ
	
		return " SELECT INVOICE_NO \"Invoice No\",APPLICATION_NO \"Application No\",ASSET_ID \"Asset Id\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",PRICING_NO \"Pricing No\",SUB_MODEL_CODE \"Sub Model Code\",NVL(COLOUR,'-') \"Colour\",NET_PRICE \"Net Price\",VAT \"VAT\",TOTAL_AMOUNT \"Total Amount\",TO_BE_DELIVERD_TO \"To Be Deliverd To\",VALUE \"Value\",CURR_CODE \"Currency Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(VEHICLE_NO,'-') \"Vehicle No\",NVL(MODEL_CODE,'-') \"Model Code\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRICING_NO,A.SUB_MODEL_CODE,A.COLOUR,A.NET_PRICE,A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE,A.CURR_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.VEHICLE_NO,A.MODEL_CODE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRICING_NO,A.SUB_MODEL_CODE,A.COLOUR,A.NET_PRICE,A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE,A.CURR_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.VEHICLE_NO,A.MODEL_CODE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INVOICE_DET_BK A  "+
           "   WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	else if (Type.equals("AF_ODI_WRITEOFF")){//CJ
	
		return " SELECT ALLOCATION_NO \"Allocation No\",NVL(INVOICE_NO,'-') \"Invoice No\",NVL(ODI_NO,'-') \"ODI No\",NVL(AJUSTED_AMOUNT,'0') \"Ajusted Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.ALLOCATION_NO,A.INVOICE_NO,A.ODI_NO,A.AJUSTED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
					 "        WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.ALLOCATION_NO,A.INVOICE_NO,A.ODI_NO,A.AJUSTED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A  "+
           "   WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_RESIDUAL")){//CJ
	
		return " SELECT FROM_DATE \"From Date\",TO_DATE \"To Date\",NVL(ENT_USER,'-') \"Entered User\",ENT_DATE \"Entered Date\" "+
         " FROM  (SELECT A.FROM_DATE,A.TO_DATE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_RESIDUAL_DATE A "+
					 "        WHERE A.FROM_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) )) "+
					 " ORDER BY	ENT_DATE DESC";	 
	
	
	
	}



	
	else if (Type.equals("AF_CR_PRO_PAYMENT_REQ_MAIN")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_PRO_CR_TEMP_PAYMENT1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(INVOICE_NO,'-') \"Invoice No\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",NVL(REG_NO,'-') \"Reg No\",NVL(DISTRICT_CODE,'-') \"Distric Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Modified Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.INVOICE_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.DISTRICT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAILS A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.INVOICE_NO,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.DISTRICT_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAI_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_PRO_CR_TEMP_PAYMENT")){//CJ
	
		return " SELECT PURCHASE_ORDER_NO \"Purchase Order No\",NVL(ASSET_ID,'-') \"Asset Id\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",PRO_INVOICE_NO \"Invoice No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Modified Date\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.PURCHASE_ORDER_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRO_INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER_DET A "+
					 "        WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.PURCHASE_ORDER_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRO_INVOICE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCH_ORDER_DET_BK A  "+
           "   WHERE A.PURCHASE_ORDER_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_SUS_PAYMENT_ENTER")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_PRINT1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_DISBURSE1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }
	
	
 else if (Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_CANCEL1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }
	
 else if (Type.equals("AF_CR_PRO_FINANCE_ACTIVATION")){//CJ
	
		return " SELECT SUS_REF_NO \"Purchase Order No\",SUSPENSE_ENTRY_TYPE \"Suspense Entry Type\",RECEIVER \"Receiver\",PAYER \"Payer\",REF_NO \"Ref No\",TOT_SETTLE_AMOUNT \"Total Settle Amount\",INT_BAL_SETTLE_AMOUNT \"Init Balance Settle Amount\",BAL_TO_BE_PAID \"Balance To Be Paid\", ENT_DATE \"Entered Date\",LAST_MOD_DATE \"Modified Date\",CURR_CODE \"Currency Code\",EXCHANGE_RATE \"Exchange Rate\" "+
         " FROM  (SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					 "        WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "     SELECT A.SUS_REF_NO,A.SUSPENSE_ENTRY_TYPE,A.RECEIVER,A.PAYER,A.REF_NO,A.TOT_SETTLE_AMOUNT,A.INT_BAL_SETTLE_AMOUNT,A.BAL_TO_BE_PAID,A.ENT_DATE,NVL(A.LAST_MOD_DATE,A.ENT_DATE) LAST_MOD_DATE,A.CURR_CODE,A.EXCHANGE_RATE "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A  "+
           "   WHERE A.SUS_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	LAST_MOD_DATE DESC";	 
	
	
	
 }
	
 else if (Type.equals("AF_CR_PRO_CHANGE_CAP_ALLOW")){//CJ
	
		return " SELECT INVOICE_NO \"Invoice No\",APPLICATION_NO \"Application No\",ASSET_ID \"Asset Id\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chassis No\",PRICING_NO \"Pricing No\",SUB_MODEL_CODE \"Sub Model Code\",NVL(COLOUR,'-') \"Colour\",NET_PRICE \"Net Price\",VAT \"VAT\",TOTAL_AMOUNT \"Total Amount\",TO_BE_DELIVERD_TO \"To Be Deliverd To\",VALUE \"Value\",CURR_CODE \"Currency Code\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(VEHICLE_NO,'-') \"Vehicle No\",NVL(MODEL_CODE,'-') \"Model Code\",NVL(ACTIVE_STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRICING_NO,A.SUB_MODEL_CODE,A.COLOUR,A.NET_PRICE,A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE,A.CURR_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.VEHICLE_NO,A.MODEL_CODE,A.ACTIVE_STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.PRICING_NO,A.SUB_MODEL_CODE,A.COLOUR,A.NET_PRICE,A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE,A.CURR_CODE,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.VEHICLE_NO,A.MODEL_CODE,A.ACTIVE_STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INVOICE_DET_BK A  "+
           "   WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	else if (Type.equals("AF_ODI_WRITEOFF")){//CJ
	
		return " SELECT ALLOCATION_NO \"Allocation No\",NVL(INVOICE_NO,'-') \"Invoice No\",NVL(ODI_NO,'-') \"ODI No\",NVL(AJUSTED_AMOUNT,'0') \"Ajusted Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(STATUS,'-') \"Status\" "+
         " FROM  (SELECT A.ALLOCATION_NO,A.INVOICE_NO,A.ODI_NO,A.AJUSTED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
					 "        WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.ALLOCATION_NO,A.INVOICE_NO,A.ODI_NO,A.AJUSTED_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.STATUS "+
					 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A  "+
           "   WHERE A.ALLOCATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}
	
	
	else if (Type.equals("AF_CR_RESIDUAL")){//CJ
	
		return " SELECT FROM_DATE \"From Date\",TO_DATE \"To Date\",NVL(ENT_USER,'-') \"Entered User\",ENT_DATE \"Entered Date\" "+
         " FROM  (SELECT A.FROM_DATE,A.TO_DATE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_RESIDUAL_DATE A "+
					 "        WHERE A.FROM_DATE = TO_DATE(SUBSTR('"+Filter+"',1,10), 'YYYY-MM-DD') AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) )) "+
					 " ORDER BY	ENT_DATE DESC";	 
	
	
	
	}

  else if (Type.equals("AF_MK_APPROVAL_QUOTATION")){//CJ

		return " SELECT QUOTATION_NO \"Quotation No\",INQUIRY_NO \"Inquiry No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(APPR_USER,'-') \"Approved User\",NVL(AUTH_SIGNATORY,'-') \"Auth Signatory\", NVL(MKT_OFFICER,'-') \"Marketing Officer\",STATUS \"Status\" "+
           " FROM  (SELECT A.QUOTATION_NO,A.INQUIRY_NO,A.ENT_USER,A.ENT_DATE,A.APPR_USER,A.AUTH_SIGNATORY,A.MKT_OFFICER,A.STATUS  "+
					 "        FROM "+Schema+".AF_MK_PRO_QUOTATION A "+
					 "        WHERE A.QUOTATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.APPR_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.APPR_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.QUOTATION_NO,A.INQUIRY_NO,A.ENT_USER,A.ENT_DATE,A.APPR_USER,A.AUTH_SIGNATORY,A.MKT_OFFICER,A.STATUS  "+
					 "        FROM  "+Schema+".AF_MK_PRO_QUOTATION_BK A  "+
					 "        WHERE  A.QUOTATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.APPR_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.APPR_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	ENT_DATE DESC";	 
	
	
	
	}
	


	else if (Type.equals("AF_CR_PRO_PAYMENT_MAIN_APP_1")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(FINANCE_NO,'-') \"Finance No\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.FINANCE_NO,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.FINANCE_NO,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }
	
 else if (Type.equals("AF_CR_PRO_PAYMENT_MAIN_APP_2")){//CJ
	
		return " SELECT PAYMENT_NO \"Payment No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(FINANCE_NO,'-') \"Finance No\",ENTRY_TYPE \"Entry Type\",PAY_AMOUNT \"Pay Amount\",NVL(LIC_BRANCH_CODE,'-') \"LIC Branch Code\",NVL(LIC_ACC_NO,'-') \"LIC Acoount No\",NVL(PAYEE_BRANCH_CODE,'-') \"Payee Branch Code\",NVL(PAYEE_ACC_NO,'-') \"Payee Account No\",ENT_USER \"Entered User\",ENTDATE \"Entered Date\",NVL(MOD_USER,'-') \"Modified User\",MOD_DATE \"Modified Date\",NVL(PAYEE_NAME,'-') \"Payee Name\",NVL(PROCESS_STATUS,'-') \"Process Status\",RECON_STATUS \"Recon Status\",NVL(WHT,'0.00') \"WHT\",NVL(NET_AMOUNT,'0.00') \"Net Amount\" "+
         " FROM  (SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.FINANCE_NO,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					 "        WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
	 				 "        UNION ALL "+
					 "     SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,A.FINANCE_NO,A.ENTRY_TYPE,A.PAY_AMOUNT,A.LIC_BRANCH_CODE,A.LIC_ACC_NO,A.PAYEE_BRANCH_CODE,A.PAYEE_ACC_NO,A.ENT_USER,A.ENTDATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENTDATE) MOD_DATE,A.PAYEE_NAME,A.PROCESS_STATUS,A.RECON_STATUS,A.WHT,A.NET_AMOUNT "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A  "+
           "   WHERE A.PAYMENT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


	
	else if (Type.equals("AF_MK_CHANGE_INVOICE_DETAILS")){//US Change Proforma Invoice Drill

	
		return " SELECT INVOICE_NO \"Invoice No\",APPLICATION_NO \"Application No\",ASSET_ID \"Asset ID\",NVL(ENGINE_NO,'-') \"Engine No\",NVL(CHASSIS_NO,'-') \"Chasis No\",NVL(REG_NO,'-') \"Reg No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID,A.ENGINE_NO,A.CHASSIS_NO,A.REG_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INVOICE_DET_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_RPT_COL_TEMP_RECEIPT_APP")){//US Temporary Receipts - Approval Drill
	
		return " SELECT TEMP_REC_NO \"Temp Rec No\",NVL(RECEIPT_NO,'-') \"Receipt No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.TEMP_REC_NO,A.RECEIPT_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SET_TMRECEIPT A "+
					 "        WHERE A.TEMP_REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEMP_REC_NO,A.RECEIPT_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SET_TMRECEIPT_BK A  "+
					 "        WHERE  A.TEMP_REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_LEASE_ASSIGN")){//US Assign Collection Officer Drill
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",CLIENT_NO \"Client No\",NVL(INQUARY_NO,'-') \"Inquiry No\",NVL(FINANCE_NO,'-') \"Finance No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ASSET_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.ASSET_STATUS,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.ASSET_STATUS,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_SETTLEMENT")){//US Receipts - Entry Drill
	
		return " SELECT REC_NO \"Record No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
					 "        WHERE A.REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A  "+
					 "        WHERE  A.REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }


 else if (Type.equals("AF_RE_POST_DATED_RECEIPT_GENERATION")){//US P D C - Receipt Generation Drill
	
		return " SELECT REC_NO \"Record No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
					 "        WHERE A.REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A  "+
					 "        WHERE  A.REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }


 else if (Type.equals("AF_RE_SETTLEMENT_GROUP")){//US Grouped Receipts - Entry Drill
	
		return " SELECT REC_NO \"Record No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
					 "        WHERE A.REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A  "+
					 "        WHERE  A.REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }


 else if (Type.equals("AF_RE_CANCELLATION")){//US Receipts Cancellation Drill
	
		return " SELECT REC_NO \"Record No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
					 "        WHERE A.REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A  "+
					 "        WHERE  A.REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_RETURN_REALIZATION")){//US Receipts - Return & Realization Drill
	
		return " SELECT REC_NO \"Record No\",NVL(SUS_REF_NO,'-') \"Sus Ref No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
					 "        WHERE A.REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.REC_NO,A.SUS_REF_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A  "+
					 "        WHERE  A.REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_TEMP_RECEIPT")){//US Temporary Receipts - Approval Drill

	
		return " SELECT TEMP_REC_NO \"Temp Rec No\",NVL(FINANCE_NO,'-') \"Finance No\",CLIENT_CODE \"Client Code\",TRN_DATE \"TRN Date\",AMOUNT \"Amount\",NVL(BANK_CODE,'-') \"Bank Code\",NVL(BRANCH_CODE,'-') \"Branch Code\",NVL(ACCOUNT_NO,'-') \"Account No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.TEMP_REC_NO,A.FINANCE_NO,A.CLIENT_CODE,A.TRN_DATE,A.AMOUNT,A.BANK_CODE,A.BRANCH_CODE,A.ACCOUNT_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_TEMP_RECEIPT A "+
					 "        WHERE A.TEMP_REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEMP_REC_NO,A.FINANCE_NO,A.CLIENT_CODE,A.TRN_DATE,A.AMOUNT,A.BANK_CODE,A.BRANCH_CODE,A.ACCOUNT_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_TEMP_RECEIPT_BK A  "+
					 "        WHERE  A.TEMP_REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_POD_CHEQUES")){//US P D C - Entry Drill

	
		return " SELECT POD_REF_NO \"Pod Ref No\",NVL(CHEQUE_NO,'-') \"Cheque No\",CLIENT_CODE \"Client Code\",CHEQUE_DATE \"Cheque Date\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",NVL(PAYER_BRANCH_CODE,'-') \"Branch Code\",NVL(PAYER_ACC_NO,'-') \"Payer Account No\",STATUS \"Status\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.POD_REF_NO,A.CHEQUE_NO,A.CLIENT_CODE,A.CHEQUE_DATE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_POD_CHEQUES A "+
					 "        WHERE A.POD_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.POD_REF_NO,A.CHEQUE_NO,A.CLIENT_CODE,A.CHEQUE_DATE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_POD_CHEQUES_BK A  "+
					 "        WHERE  A.POD_REF_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_APP_PODS_WITHDRAW")){//US P D C - Withdrawal Drill

	
		return " SELECT POD_REF_NO \"Pod Ref No\",NVL(CHEQUE_NO,'-') \"Cheque No\",CLIENT_CODE \"Client Code\",CHEQUE_DATE \"Cheque Date\",SETTLE_MODE \"Settle Mode\",ENTRY_TYPE \"Entry Type\",NVL(PAYER_BRANCH_CODE,'-') \"Branch Code\",NVL(PAYER_ACC_NO,'-') \"Payer Account No\",STATUS \"Status\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.POD_REF_NO,A.CHEQUE_NO,A.CLIENT_CODE,A.CHEQUE_DATE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_POD_CHEQUES A "+
					 "        WHERE A.POD_REF_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.POD_REF_NO,A.CHEQUE_NO,A.CLIENT_CODE,A.CHEQUE_DATE,A.SETTLE_MODE,A.ENTRY_TYPE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_POD_CHEQUES_BK A  "+
					 "        WHERE  A.POD_REF_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_INSUR_OFFICER_ASSIGN")){//US Assign Insurance Officer Drill
	
		return " SELECT APPLICATION_NO \"Application No\",CLIENT_CODE \"Client Code\",CLIENT_NO \"Client No.\",NVL(INQUARY_NO,'-') \"Inquiry No\",NVL(FINANCE_NO,'-') \"Finance No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ASSET_STATUS \"Status\" "+
           " FROM  (SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.ASSET_STATUS,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
					 "        WHERE A.APPLICATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO,A.CLIENT_CODE,A.ASSET_STATUS,A.CLIENT_NO,A.INQUARY_NO,A.FINANCE_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A  "+
					 "        WHERE  A.APPLICATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_GROUP_INVOICES")){//US Grouped Receipts - Setup Drill
	
		return " SELECT GROUP_CODE \"Group Code\",GROUP_NAME \"Group Name\",GROUP_ADDRESS \"Group Address\",BRC_NO \"BRC No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ACTIVE_STAUS \"Active Status\" "+
           " FROM  (SELECT A.GROUP_CODE,A.GROUP_NAME,A.GROUP_ADDRESS,A.BRC_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STAUS "+
					 "        FROM "+Schema+".AF_RE_PRO_GROUP_INVOICES A "+
					 "        WHERE A.GROUP_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.GROUP_CODE,A.GROUP_NAME,A.GROUP_ADDRESS,A.BRC_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE,A.ACTIVE_STAUS "+
					 "        FROM  "+Schema+".AF_RE_PRO_GROUP_INVOICES_BK A  "+
					 "        WHERE  A.GROUP_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }




 else if (Type.equals("AF_RE_INVENTORY_ALLO")){//US Receipts - Invoice Allocation Drill
	
		return " SELECT INVOICE_NO \"Invoice No\",RECEIPT_NO \"Receipt No\",INVOICED_AMOUNT \"Invoiced Amount\",RECEIPT_AMOUNT \"Receipt Amount\",ALLOCATED_DATE \"Allocated Date\",SETTELED_AMOUNT \"Settled Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",ALLOCATION_NO \"Allocation Number\" "+
           " FROM  (SELECT A.INVOICE_NO,A.RECEIPT_NO,A.INVOICED_AMOUNT,A.RECEIPT_AMOUNT,A.ALLOCATED_DATE,A.SETTELED_AMOUNT,A.ALLOCATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE_DETAILS A "+
					 "        WHERE A.INVOICE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO,A.RECEIPT_NO,A.INVOICED_AMOUNT,A.RECEIPT_AMOUNT,A.ALLOCATED_DATE,A.SETTELED_AMOUNT,A.ALLOCATION_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_DETAILS_BK A  "+
					 "        WHERE  A.INVOICE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_SETTELMENT_BANK")){//US Receipts - Deposit Drill
	
		return " SELECT DIPOSIT_NO \"Deposit No\",DIPOSIT_DATE \"Deposit Date\",NVL(REFERENCE,'-') \"Reference\",NVL(ACC_NO,'-') \"Account No\",NVL(BRANCH_CODE,'-') \"Branch Code\",NVL(STATUS,'-') \"Status\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.DIPOSIT_NO,A.DIPOSIT_DATE,A.REFERENCE,A.ACC_NO,A.BRANCH_CODE,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_CO_PRO_DIPOSIT A "+
					 "        WHERE A.DIPOSIT_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.DIPOSIT_NO,A.DIPOSIT_DATE,A.REFERENCE,A.ACC_NO,A.BRANCH_CODE,A.STATUS,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_DIPOSIT_BK A  "+
					 "        WHERE  A.DIPOSIT_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_PRO_APP_STATUS_CHANGE")){//US Application Status Change - Entry Drill
	
		return " SELECT NVL(FINANCE_NO,'-') \"Finance No\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(STATUS,'-') \"Status\",NVL(PREVIOUS_STATUS,'-') \"Previous Status\",NVL(APPROVE_STATUS,'-') \"Approve Status\",NVL(APPROVE_USER,'-') \"Approve User\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_PRO_APP_STATUS_CHANGE_APPROVAL")){//US Application Status Change - Approval Drill
	
		return " SELECT NVL(FINANCE_NO,'-') \"Finance No\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(STATUS,'-') \"Status\",NVL(PREVIOUS_STATUS,'-') \"Previous Status\",NVL(APPROVE_STATUS,'-') \"Approve Status\",NVL(APPROVE_USER,'-') \"Approve User\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_PRO_COLLECTION_MONTHLY_STATEMENT")){//US Monthly Statement Drill
	
		return " SELECT NVL(FINANCE_NO,'-') \"Finance No\",NVL(CLIENT_CODE,'-') \"Client Code\",NVL(STATUS,'-') \"Status\",NVL(PREVIOUS_STATUS,'-') \"Previous Status\",NVL(APPROVE_STATUS,'-') \"Approve Status\",NVL(APPROVE_USER,'-') \"Approve User\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
					 "        WHERE A.FINANCE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO,A.CLIENT_CODE,A.STATUS,A.PREVIOUS_STATUS,A.APPROVE_STATUS,A.APPROVE_USER,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A  "+
					 "        WHERE  A.FINANCE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_CHEQUE_RTN_LETTER")){//US Cheque Return Letter Drill
	
		return " SELECT RETURN_NO \"Return No\",DIPOSIT_NO \"Deposit No\",RECEIPT_NO \"Receipt No\",NVL(ALLO_RECEIPT_STATUS,'-') \"Allo Receipt Status\",NVL(ALLOCATED_AMOUNT,0) \"Allocated Amount\",NVL(BAL_AMOUNT,0) \"Balance Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\" "+
           " FROM  (SELECT A.RETURN_NO,A.DIPOSIT_NO,A.RECEIPT_NO,A.ALLO_RECEIPT_STATUS,A.ALLOCATED_AMOUNT,A.BAL_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE"+
					 "        FROM "+Schema+".AF_CO_PRO_RETURN_DETAILS A "+
					 "        WHERE A.RETURN_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.RETURN_NO,A.DIPOSIT_NO,A.RECEIPT_NO,A.ALLO_RECEIPT_STATUS,A.ALLOCATED_AMOUNT,A.BAL_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_RETURN_DETAILS_BK A  "+
					 "        WHERE  A.RETURN_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
 }



 else if (Type.equals("AF_RE_PRO_COLL_OFFICER_TARGETS")){//US Collection Officer Targets - Setup Drill
	
		return " SELECT BRANCH_CODE \"Branch Code\",USER_ID \"User ID\",TARGET_AMT \"Target Amount\",TARGET_START_DATE \"Target Start Date\",TARGET_FINISH_DATE \"Target Finish Date\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\" "+
           " FROM  (SELECT A.BRANCH_CODE,A.USER_ID,A.TARGET_AMT,A.TARGET_START_DATE,A.TARGET_FINISH_DATE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_OFFICER_MONTH_TARGET A "+
					 "        WHERE A.BRANCH_CODE = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.BRANCH_CODE,A.USER_ID,A.TARGET_AMT,A.TARGET_START_DATE,A.TARGET_FINISH_DATE,A.ENT_USER,A.ENT_DATE "+
					 "        FROM  "+Schema+".AF_RE_PRO_OFFICER_MONTH_TARGET A  "+
					 "        WHERE  A.BRANCH_CODE = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	ENT_DATE DESC";	 
	
	
	
	}

  else if (Type.equals("AF_RE_COLLECTION_ADVEST_OFFER_ISSUE")){//US Acceptance Of Offers Drill
	
		return " SELECT ADVETIST_NO \"Advertist No\",INVENTORY_NO \"Inventory No\",VEHICLE_NO \"Vehicle No\",OUTSTANDING_VALUE \"Outstanding Value\",OFFER_NO \"Offer No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\"  "+
           " FROM  (SELECT A.ADVETIST_NO,A.INVENTORY_NO,A.VEHICLE_NO,A.OUTSTANDING_VALUE,A.OFFER_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_ADVTIST_OFFER_VALUES A "+
					 "        WHERE A.ADVETIST_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.ADVETIST_NO,A.INVENTORY_NO,A.VEHICLE_NO,A.OUTSTANDING_VALUE,A.OFFER_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_RE_ADVTIST_OFFER_VALUES_BK A  "+
					 "        WHERE  A.ADVETIST_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}

 	else if (Type.equals("AF_RE_COLLECTION_VEHICLE_INVENTORY")){//US Vehicle Inventory - Entry
	
		return " SELECT CUSTOMER_NAME \"Customer Name\",VEHICLE_NO \"Vehicle No\",REPOSSESSION_NO \"Reprocession No\",NVL(ASSET_DESCRIPTION,'-') \"Asset Description\",NVL(MILEAGE,0) \"Mileage\",NVL(INSURANCE,'-') \"Insuarance\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\"  "+
           " FROM  (SELECT A.CUSTOMER_NAME,A.VEHICLE_NO,A.REPOSSESSION_NO,A.ASSET_DESCRIPTION,A.MILEAGE,A.ENT_USER,A.INSURANCE,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_RE_PRO_VEHICLE_INVENTORY A "+
					 "        WHERE A.VEHICLE_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.CUSTOMER_NAME,A.VEHICLE_NO,A.REPOSSESSION_NO,A.ASSET_DESCRIPTION,A.MILEAGE,A.ENT_USER,A.INSURANCE,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE  "+
					 "        FROM  "+Schema+".AF_RE_PRO_VEHICLE_INVENT_BK A  "+
					 "        WHERE  A.VEHICLE_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}


	else if (Type.equals("AF_RE_RPT_COLLECTION_TEMP_RECEIPT")){//US Temporary Receipts - Entry Drill
	
		return " SELECT TEMP_REC_NO \"Temp Rec No\",NVL(RECEIPT_NO,'-') \"Receipt No\",SETTLE_MODE \"Settle Mode\",CLIENT_CODE \"Client Code\",ENTRY_TYPE \"Entry Type\",REC_AMOUNT \"Record Amount\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\",STATUS \"Status\" "+
           " FROM  (SELECT A.TEMP_REC_NO,A.RECEIPT_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_SET_TMRECEIPT A "+
					 "        WHERE A.TEMP_REC_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.TEMP_REC_NO,A.RECEIPT_NO,A.STATUS,A.SETTLE_MODE,A.CLIENT_CODE,A.ENTRY_TYPE,A.REC_AMOUNT,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE "+
					 "        FROM  "+Schema+".AF_CO_PRO_SET_TMRECEIPT_BK A  "+
					 "        WHERE  A.TEMP_REC_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}

 else if (Type.equals("AF_RE_COL_VALUATION_YARD")){//US Valuation For Vehicle In Yard
	
		return " SELECT VALUATION_NO \"Valuation No\",ASSET_ID \"Asset ID\",SUB_MODEL_CODE \"Sub Model Code\",NVL(REG_NO,'-') \"Reg No\",ENGINE_NO \"Engine No\",CHASSIS_NO \"Chasis No\",ENT_USER \"Entered User\",ENT_DATE \"Entered Date\",NVL(MOD_USER,'-') \"Mod User\",MOD_DATE \"Mod Date\"  "+
           " FROM  (SELECT A.VALUATION_NO,A.ASSET_ID,A.SUB_MODEL_CODE,A.REG_NO,A.ENGINE_NO,A.CHASSIS_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE  "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_VALUATION A "+
					 "        WHERE A.VALUATION_NO = '"+Filter+"' AND   "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 "        UNION ALL "+
					 "        SELECT A.VALUATION_NO,A.ASSET_ID,A.SUB_MODEL_CODE,A.REG_NO,A.ENGINE_NO,A.CHASSIS_NO,A.ENT_USER,A.ENT_DATE,A.MOD_USER,NVL(A.MOD_DATE,A.ENT_DATE) MOD_DATE  "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_VALUATION_BK A  "+
					 "        WHERE  A.VALUATION_NO = '"+Filter+"' AND "+
					 "              ((A.ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (A.MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND A.MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ))) "+
					 " ORDER BY	MOD_DATE DESC";	 
	
	
	
	}




	else{
		 return " ";
   }	
 }	
		
 
}




