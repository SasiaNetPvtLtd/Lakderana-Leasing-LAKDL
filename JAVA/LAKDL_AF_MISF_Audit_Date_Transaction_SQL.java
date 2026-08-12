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

public class LAKDL_AF_MISF_Audit_Date_Transaction_SQL  
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
	
	if(Type.equals("AF_MK_INQUIRY")){ // DJ--1
		
		return" SELECT INQUIRY_CODE \"Inquiry Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.INQUIRY_CODE "+
					 "        FROM "+Schema+".AF_MK_PRO_INQUIRY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.INQUIRY_CODE "+
					 "        FROM  "+Schema+".AF_MK_PRO_INQUIRY_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INQUIRY_CODE "+	
					 " ORDER BY	INQUIRY_CODE DESC";
						
						
						
  }

	else if(Type.equals("AF_CR_PRO_CANCEL_AFTER_PO")){ // DJ--2
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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
	
	else if(Type.equals("AF_CR_PRO_RENTAL_DATE_CHANGE")){ // DJ--3
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INSTALLMENT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INSTALLMENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO "+	
					 " ORDER BY	APPLICATION_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_STANDING_ORDER")){ // DJ--4
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FINANCE_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_STANDING_ORDERS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+	
					 " ORDER BY	FINANCE_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_ACTIVATED_DATE_CHANGE")){ // DJ--5
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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

	else if(Type.equals("AF_CR_PRO_STANDING_ORDER_APP")){ // DJ--6
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FINANCE_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_STANDING_ORDERS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+	
					 " ORDER BY	FINANCE_NO DESC";
						
						
	}

	else if(Type.equals("AF_CR_ADD_GUARANTOR")){ // DJ--7
	
		return" SELECT GUARANTOR_CODE \"Guarantor Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GUARANTOR_CODE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLI_GUARANTOR A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GUARANTOR_CODE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLI_GUARANTOR_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GUARANTOR_CODE "+	
					 " ORDER BY	GUARANTOR_CODE DESC";
						
						
	}

	else if(Type.equals("AF_CR_DEL_GUARANTOR")){ // DJ--8
	
		return" SELECT GUARANTOR_CODE \"Guarantor Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.GUARANTOR_CODE "+
					 "        FROM "+Schema+".AF_CO_PRO_APPLI_GUARANTOR A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.GUARANTOR_CODE "+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLI_GUARANTOR_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY GUARANTOR_CODE "+	
					 " ORDER BY	GUARANTOR_CODE DESC";
						
						
	}

	else if(Type.equals("AF_CR_PRO_APPLICATION_REVERSAL")){ // DJ--9
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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
	
	else if(Type.equals("AF_CR_PRO_APPLICATION_REVERSE")){ // DJ--10
	
		return" SELECT SCORE_MODEL_CODE \"Score Model Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SCORE_MODEL_CODE "+
					 "        FROM "+Schema+".AF_CR_PRO_CRSCORE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SCORE_MODEL_CODE "+
					 "        FROM  "+Schema+".AF_CR_PRO_CRSCORE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SCORE_MODEL_CODE "+	
					 " ORDER BY	SCORE_MODEL_CODE DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_APPLICATION_ACTIVATED")){ // DJ--11
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO "+
					 "        FROM "+Schema+".AF_CO_LOG_ACTIVATION_ERROR A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO "+	
					 " ORDER BY	APPLICATION_NO DESC";
						
						
	}

	else if(Type.equals("AF_CR_PRO_CHANGE_RENTAL_AMOUNT")){ // DJ--12
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INSTALLMENT A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INSTALLMENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO "+	
					 " ORDER BY	APPLICATION_NO DESC";
						
						
	}

	else if(Type.equals("AF_CR_PRO_CHANGE_INSUARANCE_DATE")){ // DJ--13
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FINANCE_NO "+
					 "        FROM "+Schema+".AF_IS_PRO_ASET_INSUR_DETA A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO "+
					 "        FROM  "+Schema+".AF_IS_PRO_ASET_INSUR_DETA_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+	
					 " ORDER BY	FINANCE_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_CREDIT_DEBIT_NOTE")){ // DJ--14
	
		return" SELECT REF_NO \"Reference Number\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.REF_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_CR_DR_DETAILS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.REF_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_CR_DR_DETAILS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY REF_NO "+	
					 " ORDER BY	REF_NO DESC";
						
						
	}

	else if(Type.equals("AF_CR_PRO_CHANGE_ACTIVATED_DATE")){ // DJ--15
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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
	
	else if(Type.equals("AF_RE_COLLECTION_ADVEST_GEN")){ // DJ--16
	
		return" SELECT ADVER_NO \"Advertisements No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ADVER_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_ADVERTISEMENT_DETAIL A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ADVER_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_ADVERTISEMENT_DET_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ADVER_NO "+	
					 " ORDER BY	ADVER_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_RE_INVENTORY_APPROVAL")){ // DJ--17
	
		return" SELECT VEHICLE_NO \"Vehicle Number\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.VEHICLE_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_VEHICLE_INVENTORY A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.VEHICLE_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_VEHICLE_INVENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY VEHICLE_NO "+	
					 " ORDER BY	VEHICLE_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_RE_COLLECTION_ADVEST_OFFER_PROCESS")){ // DJ--18
	
		return" SELECT OFFER_NO \"Offer No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.OFFER_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_ADVERTISEMENT_OFFERS A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.OFFER_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_ADVERTISEMENT_OFF_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY OFFER_NO "+	
					 " ORDER BY	OFFER_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_MK_PRICE")){ // TJ 
	
		return" SELECT PRICING_NO \"Pricing No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PRICING_NO"+
					 "        FROM "+Schema+".AF_MK_PRO_PRICING A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) )"+
					 " GROUP BY PRICING_NO"+	
					 " ORDER BY PRICING_NO DESC";						
						
	}
	
	else if(Type.equals("AF_MK_APP_STATUS_APPROVE_1")){ // TJ
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO"+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO"+	
					 " ORDER BY APPLICATION_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_CREDIT_EVA_DETAILS")){ // TJ
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO"+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO"+	
					 " ORDER BY APPLICATION_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_ENTER_LEASE")){ // TJ
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.APPLICATION_NO"+
					 "        FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO"+	
					 " ORDER BY APPLICATION_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_STIPULATED_MAIN_SCREEN")){ // TJ
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FINANCE_NO"+
					 "        FROM "+Schema+".AF_CR_PRO_STIPULATED_VALUE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FINANCE_NO"+
					 "        FROM  "+Schema+".AF_CR_PRO_STIPULATED_VALUE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO"+	
					 " ORDER BY FINANCE_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PUR_MAIN_SCREEN")){ // TJ
	
		return" SELECT PURCHASE_ORDER_NO \"Purchase Order No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PURCHASE_ORDER_NO"+	
					 " ORDER BY PURCHASE_ORDER_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PURCHASE_ORDER_APPROVAL")){ // TJ
	
		return" SELECT PURCHASE_ORDER_NO \"Purchase Order No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PURCHASE_ORDER_NO"+	
					 " ORDER BY PURCHASE_ORDER_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PURCHASE_ORDER_MAIN_SCREEN_DELETION")){ // TJ
	
		return" SELECT PURCHASE_ORDER_NO \"Purchase Order No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO"+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCHASE_ORDER_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PURCHASE_ORDER_NO"+	
					 " ORDER BY PURCHASE_ORDER_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_REQUSITION_MAIN1")){ // TJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO"+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO"+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO"+	
					 " ORDER BY PAYMENT_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_REQ_SPECIAL_APPROVAL")){ // TJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO"+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO"+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO"+	
					 " ORDER BY PAYMENT_NO DESC";						
						
	}
	else if(Type.equals("AF_CR_PRO_PAYMENT_REQUSITION_LOAN")){ // TJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO"+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO"+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A"+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO"+	
					 " ORDER BY PAYMENT_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_INVOICE_REVERSAL_OPTION")){ // TJ
	
		return" SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.INVOICE_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO"+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INVOICE_NO"+	
					 " ORDER BY INVOICE_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_AGM_COMMENT")){ // TJ
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.APPLICATION_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_APPLICATION_APPROVAL A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY APPLICATION_NO"+	
					 " ORDER BY APPLICATION_NO DESC";						
						
	}
	
	else if(Type.equals("AF_CR_PRO_CRIB_LETTER")){ // TJ
	
		return" SELECT CREIB_REF_NO \"CREIB Ref No No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.CREIB_REF_NO"+
					 "        FROM "+Schema+".AF_CR_PRO_CRIB_REQ_DETAILS A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY CREIB_REF_NO"+	
					 " ORDER BY CREIB_REF_NO DESC";						
						
	}
	
	else if(Type.equals("AF_FOLLOWUP_ENTRY")){ // TJ
	
		return" SELECT FOLLOW_UP_NO \"Follow Up No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FOLLOW_UP_NO"+
					 "        FROM "+Schema+".AF_CO_PRO_FOLLOW_UP A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.FOLLOW_UP_NO"+
					 "        FROM  "+Schema+".AF_CO_PRO_FOLLOW_UP_BK A"+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FOLLOW_UP_NO"+	
					 " ORDER BY FOLLOW_UP_NO DESC";						
						
	}
  
	else if(Type.equals("AF_MK_APPLICATION_PROCESS")){ // MC-17
	
	 return" SELECT APPLICATION_NO \"Application Id\",COUNT(*) \"No Of Records\" "+
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


	 
	else if(Type.equals("AF_CR_INVOICE_ADJUSTMENTS")){ // MC-18
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.FINANCE_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_CREDIT_DETAILS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.FINANCE_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_CREDIT_DETAILS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+	
					 " ORDER BY	FINANCE_NO DESC";
						
						
	}


	else if(Type.equals("AF_RE_OTHER_INVOICES")){ // MC-19
	
		return" SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
	         " FROM  (SELECT A.INVOICE_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY INVOICE_NO "+	
						 " ORDER BY	INVOICE_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_CR_ODI_ADJUST_APPROVAL")){ // MC-20
	
		return" SELECT ALLOCATION_NO \"Allocation No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.ALLOCATION_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.ALLOCATION_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ALLOCATION_NO "+	
					 " ORDER BY	ALLOCATION_NO DESC";
						
						
 }


 else if(Type.equals("AF_RE_OTHER_INVOICES_FOR_TERMI_CON")){ // MC-21

	 return" SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.INVOICE_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.INVOICE_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INVOICE_NO "+	
					 " ORDER BY	INVOICE_NO DESC";
						
						
	}
	
	
	else if(Type.equals("AF_CR_PRO_CHANGING_PAYEE_CODE")){ // MC-22
	
		return" SELECT SUS_REF_NO \"Refernce No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.SUS_REF_NO "+
				 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.SUS_REF_NO "+
				 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUS_REF_NO "+	
					 " ORDER BY	SUS_REF_NO DESC";
						
						
	}
	
	
	else if(Type.equals("AF_RE_DEBIT_NOTE_CANCEL")){ // MC-23--chech this for correctness
	
		return" SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.INVOICE_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_INVOICE A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.INVOICE_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INVOICE_NO "+	
					 " ORDER BY	INVOICE_NO DESC";
						
						
	}

	else if(Type.equals("AF_RE_CREDIT_NOTE_CANCEL")){ // MC-24
	
		return" SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.FINANCE_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_CREDIT_DETAILS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.FINANCE_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_CREDIT_DETAILS_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FINANCE_NO "+	
					 " ORDER BY	FINANCE_NO DESC";
						
						
	}

	else if(Type.equals("AF_CR_PRO_PAYMENT_DELETION")){ // MC-25
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.PAYMENT_NO "+
				 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.PAYMENT_NO "+
				 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+	
					 " ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_OTHER_PAY_ACCOUNT_SELECT")){ // MC-26
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.PAYMENT_NO "+
				 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+

					 "              ) "+
					 " GROUP BY PAYMENT_NO "+	
					 " ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	
	
	else if(Type.equals("AF_CR_PRO_PAYMENTS_CLEARING")){ // MC-27
	
		return" SELECT SUS_REF_NO \"Reference No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.SUS_REF_NO "+
				 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.SUS_REF_NO "+
				 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUS_REF_NO "+	
					 " ORDER BY	SUS_REF_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_ODI_ADJUST_APPROVAL1")){ // MC-28
	
		return" SELECT ALLOCATION_NO \"Allocation No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.ALLOCATION_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.ALLOCATION_NO "+
				 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ALLOCATION_NO "+	
					 " ORDER BY	ALLOCATION_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_OTHER_PAYMENTS_AUTHO")){ // MC-29
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.PAYMENT_NO "+
				 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.PAYMENT_NO "+
				 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
					 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+	
					 " ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_FN_PRO_INCOME_PROVISION")){ // MC-30
	
		return" SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.APPLICATION_NO "+
				 "        FROM "+Schema+".AF_CO_PRO_PROVISION_DETAILS A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )  "+
					 "              ) "+
					 " GROUP BY APPLICATION_NO "+	
					 " ORDER BY	APPLICATION_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_REPOSSESSION")){ // MC-31
	
		return" SELECT REPOSSESSION_NO \"Repossession No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.REPOSSESSION_NO "+
				 "        FROM "+Schema+".AF_RE_PRO_REPOSSESSION A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.REPOSSESSION_NO "+
				 "        FROM  "+Schema+".AF_RE_PRO_REPOSSESSION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY REPOSSESSION_NO "+	
					 " ORDER BY	REPOSSESSION_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_REVERSE_REPOSSESSION")){ // MC-32
	
		return" SELECT REPOSSESSION_NO \"Repossession No\",COUNT(*) \"No Of Records\" "+
         " FROM  (SELECT A.REPOSSESSION_NO "+
				 "        FROM "+Schema+".AF_RE_PRO_REPOSSESSION A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
				 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
				 "        UNION ALL "+
				 "        SELECT A.REPOSSESSION_NO "+
				 "        FROM  "+Schema+".AF_RE_PRO_REPOSSESSION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY REPOSSESSION_NO "+	
					 " ORDER BY	REPOSSESSION_NO DESC";
						
						
	}

	else if(Type.equals("AF_AD_INDICATIVE_QUOTATION")){ // SK
	
		return " SELECT MAIN_CODE \"Main Code\",COUNT(*) \"No Of Records\" "+
			" FROM  (SELECT A.MAIN_CODE "+
					 "		FROM "+Schema+".AF_MK_CONDITIONS_MAIN A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "		UNION ALL "+
					 "		SELECT A.MAIN_CODE "+
					 "		FROM  "+Schema+".AF_MK_CONDITIONS_MAIN_BK A "+
					 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY MAIN_CODE "+
					 " ORDER BY MAIN_CODE DESC";
					 
  }


	else if(Type.equals("AF_CR_TERMINATION_CALC")){ // SK
	
			return " SELECT TERMINATION_NO \"Termination Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_ALLO")){ // SK
	
			return " SELECT INVOICE_NO \"Invoice Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.INVOICE_NO "+
						 "		FROM "+Schema+".AF_CO_PRO_INVOICE_DETAILS A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.INVOICE_NO "+
						 "		FROM  "+Schema+".AF_CO_PRO_INVOICE_DETAILS_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY INVOICE_NO "+
						 " ORDER BY INVOICE_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_CHECK")){ // SK
	
			return " SELECT APPLICATION_NO \"Application Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.APPLICATION_NO "+
						 "		FROM "+Schema+".AF_CO_PRO_APPLICATION_DETAILS A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.APPLICATION_NO "+
						 "		FROM  "+Schema+".AF_CO_PRO_APPLICATION_DETAI_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY APPLICATION_NO "+
						 " ORDER BY APPLICATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_LEGAL")){ // SK
	
			return " SELECT TERMINATION_NO \"Application Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_APPR1")){ // SK
	
			return " SELECT TERMINATION_NO \"Termination Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_APPR2")){ // SK
	
			return " SELECT TERMINATION_NO \"Termination Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_TERMINATION_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK")){ // SK
	
			return " SELECT TERMINATION_NO \"Application Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK2")){ // SK
	
			return " SELECT TERMINATION_NO \"Application Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_TERMINATION_LEGAL_CHECK3")){ // SK
	
			return " SELECT TERMINATION_NO \"Application Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.TERMINATION_NO "+
						 "		FROM "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.TERMINATION_NO "+
						 "		FROM  "+Schema+".AF_CR_PRO_LEGAL_TERMINATION A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TERMINATION_NO "+
						 " ORDER BY TERMINATION_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_SECURITY_MARKETTING_APPR")){ // SK
	
			return " SELECT FINANCE_NO \"Finance Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.FINANCE_NO "+
						 "		FROM "+Schema+".AF_CO_PRO_SECURITYFILE_MOVMENT A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.FINANCE_NO "+
						 "		FROM  "+Schema+".AF_CO_PRO_SECURITYFILE_MOV_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY FINANCE_NO "+
						 " ORDER BY FINANCE_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CR_SECURITY_MARKETTING_FILE")){ // SK
	
			return " SELECT FINANCE_NO \"Finance Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.FINANCE_NO "+
						 "		FROM "+Schema+".AF_CO_PRO_SECURITYFILE_MOVMENT A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.FINANCE_NO "+
						 "		FROM  "+Schema+".AF_CO_PRO_SECURITYFILE_MOV_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY FINANCE_NO "+
						 " ORDER BY FINANCE_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_CO_DAYEND")){ // SK
	
			return " SELECT DAY_END_DATE \"Day End Date\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.DAY_END_DATE "+
						 "		FROM "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.DAY_END_DATE "+
						 "		FROM  "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY DAY_END_DATE "+
						 " ORDER BY DAY_END_DATE DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_RE_COLLECTION_LEGAL_ACTIVITIES")){ // SK
	
			return " SELECT LEGAL_NO \"Legal Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.LEGAL_NO "+
						 "		FROM "+Schema+".AF_RE_PRO_LEGAL_ACTIVITIES A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.LEGAL_NO "+
						 "		FROM  "+Schema+".AF_RE_PRO_LEGAL_ACTIVITIES_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY LEGAL_NO "+
						 " ORDER BY LEGAL_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_RE_COLLECTION_LEGAL_ACTIONS")){ // SK
	
			return " SELECT LEGAL_NO \"Legal Number\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.LEGAL_NO "+
						 "		FROM "+Schema+".AF_RE_PRO_LEGAL_ACTIONS A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.LEGAL_NO "+
						 "		FROM  "+Schema+".AF_RE_PRO_LEGAL_ACTIONS_BK A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY LEGAL_NO "+
						 " ORDER BY LEGAL_NO DESC";
						 
	}
	
	
	
	else if(Type.equals("AF_DAY_END_ROUTING")){ // SK
	
			return " SELECT DAY_END_DATE \"Day End Date\",COUNT(*) \"No Of Records\" "+
				" FROM  (SELECT A.DAY_END_DATE "+
						 "		FROM "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "		UNION ALL "+
						 "		SELECT A.DAY_END_DATE "+
						 "		FROM  "+Schema+".AF_CO_PRO_SYS_PARAMETER A "+
						 "		WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "			(ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY DAY_END_DATE "+
						 " ORDER BY DAY_END_DATE DESC";
						 
	}
	
	else if(Type.equals("AF_MK_APPROVAL_QUOTATION")){ // CJ
	
		return" SELECT QUOTATION_NO \"Quotation No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.QUOTATION_NO "+
					 "        FROM "+Schema+".AF_MK_PRO_QUOTATION A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (APPR_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND APPR_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.QUOTATION_NO "+
					 "        FROM  "+Schema+".AF_MK_PRO_QUOTATION_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (APPR_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND APPR_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY QUOTATION_NO "+	
					 " ORDER BY	QUOTATION_NO DESC";
						
						
	}
		else if(Type.equals("AF_CR_PRO_PAYMENT_REQ_MAIN")){ // CJ
		
		 return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}

	
	
	else if(Type.equals("AF_PRO_CR_TEMP_PAYMENT1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAILS A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_CR_PRO_PAY_VEHICLE_DETAI_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}


		else if(Type.equals("AF_PRO_CR_TEMP_PAYMENT")){ // CJ
		
		 return" SELECT PURCHASE_ORDER_NO \"Purchase Order No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PURCHASE_ORDER_NO "+
					 "        FROM "+Schema+".AF_CR_PRO_PURCHASE_ORDER_DET A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PURCHASE_ORDER_NO "+
					 "        FROM  "+Schema+".AF_CR_PRO_PURCH_ORDER_DET_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PURCHASE_ORDER_NO "+
						"ORDER BY	PURCHASE_ORDER_NO DESC";
						
						
	}
	
	
	else if(Type.equals("AF_CR_PRO_SUS_PAYMENT_ENTER")){ // CJ
	
		return" SELECT SUS_REF_NO \"Sus Ref No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUS_REF_NO "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUS_REF_NO "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUS_REF_NO "+
						"ORDER BY	SUS_REF_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_OTHER_PAYMENTS")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	
	else if(Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE")){ // CJ
	
		return" SELECT SUS_REF_NO \"Sus Ref No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUS_REF_NO "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUS_REF_NO "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUS_REF_NO "+
						"ORDER BY	SUS_REF_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_OTHER_PAYMENTS_APPROVE1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_PRINT1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_DISBURSE1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
		
 else if(Type.equals("AF_CR_PRO_PAYMENT_CHEQUE_CANCEL1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}

	
	else if(Type.equals("AF_CR_PRO_FINANCE_ACTIVATION")){ // CJ
	
		return" SELECT SUS_REF_NO \"Sus Ref No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.SUS_REF_NO "+
					 "        FROM "+Schema+".AF_RE_ACC_SUS_PAYMENT A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.SUS_REF_NO "+
					 "        FROM  "+Schema+".AF_RE_ACC_SUS_PAYMENT_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (LAST_MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND LAST_MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY SUS_REF_NO "+
						"ORDER BY	SUS_REF_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_CHANGE_CAP_ALLOW")){ // CJ
	
		return" SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.INVOICE_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.INVOICE_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_APP_INVOICE_DET_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY INVOICE_NO "+
						"ORDER BY	INVOICE_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_ODI_WRITEOFF")){ // CJ
	
		return" SELECT ALLOCATION_NO \"Allocation No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ALLOCATION_NO "+
					 "        FROM "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ALLOCATION_NO "+
					 "        FROM  "+Schema+".AF_CO_PRO_ODI_AJUSTMENT_DET_BK A "+
				 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ALLOCATION_NO "+
						"ORDER BY	ALLOCATION_NO DESC";
						
						
	}
	
 else if(Type.equals("AF_CR_RESIDUAL")){ // CJ
	
		return" SELECT FROM_DATE \"From Date\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.FROM_DATE "+
					 "        FROM "+Schema+".AF_CO_PRO_RESIDUAL_DATE A "+
					  "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY FROM_DATE "+
						"ORDER BY	FROM_DATE DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_MAIN_APP_1")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	else if(Type.equals("AF_CR_PRO_PAYMENT_MAIN_APP_2")){ // CJ
	
		return" SELECT PAYMENT_NO \"Payment No\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.PAYMENT_NO "+
					 "        FROM "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
					  "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.PAYMENT_NO "+
					 "        FROM  "+Schema+".AF_RE_PRO_SETTLMENT_PAYMENT_BK A "+
				 "        WHERE (ENTDATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENTDATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY PAYMENT_NO "+
						"ORDER BY	PAYMENT_NO DESC";
						
						
	}
	
	
	else if(Type.equals("AF_MK_CHANGE_INVOICE_DETAILS")){ // US Change Profoma Invoice
		
			return " SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.INVOICE_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.INVOICE_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_APP_INVOICE_DET_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY INVOICE_NO "+	
						 " ORDER BY	INVOICE_NO DESC";
							
							
	}


	else if(Type.equals("AF_RE_RPT_COL_TEMP_RECEIPT_APP")){ // US Temporary Receipts - Approval
	
			return" SELECT TEMP_REC_NO \"Temp Rec No.\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.TEMP_REC_NO"+
						 "        FROM "+Schema+".AF_CO_PRO_SET_TMRECEIPT A"+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.TEMP_REC_NO"+
						 "        FROM  "+Schema+".AF_CO_PRO_SET_TMRECEIPT_BK A"+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TEMP_REC_NO"+	
						 " ORDER BY TEMP_REC_NO DESC";
							
							
	}

	else if(Type.equals("AF_RE_LEASE_ASSIGN")){ // US Assign Collection Officer
	
		
			return " SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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
	
	else if(Type.equals("AF_RE_RPT_COLLECTION_TEMP_RECEIPT")){ // US Temporary Receipts - Entry
	
		
			return " SELECT TEMP_REC_NO \"Temp Rec No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.TEMP_REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SET_TMRECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.TEMP_REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SET_TMRECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TEMP_REC_NO "+	
						 " ORDER BY	TEMP_REC_NO DESC";
							
							
	}
	
	
	
	else if(Type.equals("AF_RE_SETTLEMENT")){ // US Receipts - Entry
		
			return " SELECT REC_NO \"Record No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY REC_NO "+	
						 " ORDER BY	REC_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_POST_DATED_RECEIPT_GENERATION")){ // US P D C - Receipt Generation
		
			return " SELECT REC_NO \"Record No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY REC_NO "+	
						 " ORDER BY	REC_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_SETTLEMENT_GROUP")){ // US Grouped Receipts - Entry
		
			return " SELECT REC_NO \"Record No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY REC_NO "+	
						 " ORDER BY	REC_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_CANCELLATION")){ // US Receipts Cancellation
		
			return " SELECT REC_NO \"Record No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY REC_NO "+	
						 " ORDER BY	REC_NO DESC";
							
							
		
	}
	
	
	else if(Type.equals("AF_RE_RETURN_REALIZATION")){ // US Receipts - Return & Realization
		
			return " SELECT REC_NO \"Record No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.REC_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_SETTL_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.REC_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_SETTL_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY REC_NO "+	
						 " ORDER BY	REC_NO DESC";
							
							
	}
	
	
	
	else if(Type.equals("AF_RE_TEMP_RECEIPT")){ // US Temporary Receipts - Approval
		
			return " SELECT TEMP_REC_NO \"Temp Rec No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.TEMP_REC_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_TEMP_RECEIPT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.TEMP_REC_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_TEMP_RECEIPT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY TEMP_REC_NO "+	
						 " ORDER BY	TEMP_REC_NO DESC";
							
							
	}
	
	
	
	
	else if(Type.equals("AF_RE_POD_CHEQUES")){ // US P D C - Entry
		
			return " SELECT POD_REF_NO \"Pod Ref No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.POD_REF_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_POD_CHEQUES A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.POD_REF_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_POD_CHEQUES_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY POD_REF_NO "+	
						 " ORDER BY	POD_REF_NO DESC";
							
							
	}
	
	
	
	else if(Type.equals("AF_RE_APP_PODS_WITHDRAW")){ // US P D C - Withdrawal
		
			return " SELECT POD_REF_NO \"Pod Ref No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.POD_REF_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_POD_CHEQUES A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.POD_REF_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_POD_CHEQUES_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY POD_REF_NO "+	
						 " ORDER BY	POD_REF_NO DESC";
							
							
		
	}
	
	
	
	else if(Type.equals("AF_RE_INSUR_OFFICER_ASSIGN")){ // US Assign Insurance Officer
	
		
			return " SELECT APPLICATION_NO \"Application No\",COUNT(*) \"No Of Records\" "+
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
	
	
	
	
	else if(Type.equals("AF_RE_GROUP_INVOICES")){ // US Grouped Receipts - Setup
	
		
			return " SELECT GROUP_CODE \"Group Code\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.GROUP_CODE "+
						 "        FROM "+Schema+".AF_RE_PRO_GROUP_INVOICES A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.GROUP_CODE "+
						 "        FROM  "+Schema+".AF_RE_PRO_GROUP_INVOICES_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY GROUP_CODE "+	
						 " ORDER BY	GROUP_CODE DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_INVENTORY_ALLO")){ // US Receipts - Invoice Allocation
	
		
			return " SELECT INVOICE_NO \"Invoice No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.INVOICE_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_INVOICE_DETAILS A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.INVOICE_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_INVOICE_DETAILS_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY INVOICE_NO "+	
						 " ORDER BY	INVOICE_NO DESC";
							

	}
	
	
	else if(Type.equals("AF_RE_SETTELMENT_BANK")){ // US Receipts - Deposit
		
			return " SELECT DIPOSIT_NO \"Deposit No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.DIPOSIT_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_DIPOSIT A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.DIPOSIT_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_DIPOSIT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY DIPOSIT_NO "+	
						 " ORDER BY	DIPOSIT_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_PRO_APP_STATUS_CHANGE")){ // US Application Status Change - Entry
		
			return " SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.FINANCE_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.FINANCE_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY FINANCE_NO "+	
						 " ORDER BY	FINANCE_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_PRO_APP_STATUS_CHANGE_APPROVAL")){ // US Application Status Change - Approval
		
			return " SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.FINANCE_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.FINANCE_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY FINANCE_NO "+	
						 " ORDER BY	FINANCE_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_PRO_COLLECTION_MONTHLY_STATEMENT")){ // US Monthly Statement
		
			return " SELECT FINANCE_NO \"Finance No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.FINANCE_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.FINANCE_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_APP_STATUS_CHANGE_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY FINANCE_NO "+	
						 " ORDER BY	FINANCE_NO DESC";
							
							
	}
	
	
	else if(Type.equals("AF_RE_CHEQUE_RTN_LETTER")){ // US Cheque Return Letter
		
			return " SELECT RETURN_NO \"Return No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.RETURN_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_RETURN_DETAILS A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.RETURN_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_RETURN_DETAILS_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (MOD_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND MOD_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY RETURN_NO "+	
						 " ORDER BY	RETURN_NO DESC";
							
							
	}
	
	

	else if(Type.equals("AF_RE_PRO_COLL_OFFICER_TARGETS")){ // US Collection Officer Targets - Setup
		
			return " SELECT BRANCH_CODE \"Branch Code\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.BRANCH_CODE "+
						 "        FROM "+Schema+".AF_RE_PRO_OFFICER_MONTH_TARGET A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.BRANCH_CODE "+
						 "        FROM  "+Schema+".AF_RE_PRO_OFFICER_MONTH_TARGET A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY BRANCH_CODE "+	
						 " ORDER BY	BRANCH_CODE DESC";
							
							
	}

	else if(Type.equals("AF_RE_COLLECTION_ADVEST_OFFER_ISSUE")){ // US Acceptance Of Offers
	
		return " SELECT ADVETIST_NO \"Branch Code\",COUNT(*) \"No Of Records\" "+
           " FROM  (SELECT A.ADVETIST_NO "+
					 "        FROM "+Schema+".AF_RE_ADVTIST_OFFER_VALUES A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
					 "        UNION ALL "+
					 "        SELECT A.ADVETIST_NO "+
					 "        FROM  "+Schema+".AF_RE_ADVTIST_OFFER_VALUES_BK A "+
					 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
					 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
					 " GROUP BY ADVETIST_NO "+	
					 " ORDER BY	ADVETIST_NO DESC";
						
						
	}


	else if(Type.equals("AF_RE_COLLECTION_VEHICLE_INVENTORY")){ // US Vehicle Inventory - Entry
		
			return " SELECT VEHICLE_NO \"Vehicle No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.VEHICLE_NO "+
						 "        FROM "+Schema+".AF_RE_PRO_VEHICLE_INVENTORY A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.VEHICLE_NO "+
						 "        FROM  "+Schema+".AF_RE_PRO_VEHICLE_INVENT_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY VEHICLE_NO "+	
						 " ORDER BY	VEHICLE_NO DESC";
							
							
	}
	
	
	
	else if(Type.equals("AF_RE_COL_VALUATION_YARD")){ // US Valuation For Vehicle In Yard
		
			return " SELECT VALUATION_NO \"Valuation No\",COUNT(*) \"No Of Records\" "+
	           " FROM  (SELECT A.VALUATION_NO "+
						 "        FROM "+Schema+".AF_CO_PRO_APP_VALUATION A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) "+
						 "        UNION ALL "+
						 "        SELECT A.VALUATION_NO "+
						 "        FROM  "+Schema+".AF_CO_PRO_APP_VALUATION_BK A "+
						 "        WHERE (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') ) OR "+
						 "              (ENT_DATE>=TO_DATE('"+FDate+"','DD-MM-YYYY') AND ENT_DATE<=TO_DATE('"+TDate+"','DD-MM-YYYY') )) "+
						 " GROUP BY VALUATION_NO "+	
						 " ORDER BY	VALUATION_NO DESC";
							
							
	}

	
	else{
		 return " ";
   }	
 }	
	
	

 
}




