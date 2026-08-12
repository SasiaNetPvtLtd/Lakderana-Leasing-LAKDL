import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_CR_help_select  {  
	
	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	
	public String m_help_DIV_TXT_CR_CLIENT_MGT_sql= "";
	public String m_help_DIV_TXT_CR_CLIENT_MGT_sql_Header= "Credit Process - Employees ";
	
	public String m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql="";
	public String m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql_Header="Credit Process - Client Details ";
	
	public String m_help_DIV_TXT_PRODUCT_FACILITY_sql="";
	public String m_help_DIV_TXT_PRODUCT_FACILITY_sql_Header="Credit Process - Product Details ";
	
	public String m_help_DIV_TXT_FEE_FACILITY_sql="";
	public String m_help_DIV_TXT_FEE_FACILITY_sql_Header="Credit Process - Fee Details ";
	
	public String m_help_DIV_TXT_PRODUCT_FEATURE_sql="";
	public String m_help_DIV_TXT_PRODUCT_FEATURE_sql_Header="Credit Process - Product Features ";
	
	public String m_help_DIV_TXT_FEE_FEATURE_sql="";
	public String m_help_DIV_TXT_FEE_FEATURE_sql_Header="Credit Process - Fee Features ";
	
	public String m_help_DIV_TXT_FACILITY_sql="";
	public String m_help_DIV_TXT_FACILITY_sql_Header="Credit Process - Facility ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_sql_Header="Credit Process - Facility";
	
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql="";
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql_Header="Credit Process - Debtor";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql_Header="Credit Process - Facility ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql_Header="Credit Process - Client Debtors Relationship Termination";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql_Header="Operation Process - Invoice Enter - Facility ";
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql_Header="Operation Process - Invoice Enter - Debtors ";
	
	public String m_help_DIV_TXT_INVOICE_BANK_sql="";
	public String m_help_DIV_TXT_INVOICE_BANK_sql_Header="Bank Details";
	
	public String m_help_DIV_TXT_INVOICE_BRANCH_sql="";
	public String m_help_DIV_TXT_INVOICE_BRANCH_sql_Header="Branch Details";
	
	public String m_help_DIV_TXT_QUOTATION_FACILITY_sql="";
	public String m_help_DIV_TXT_QUOTATION_FACILITY_sql_Header="Credit Process - Assign Quotation to Facility ";
	
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_sql="";
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_sql_Header=" Credit Process - Credit Score ";
	
	public String m_help_TXT_SCORE_MODEL_CODE_sql="";
	public String m_help_TXT_SCORE_MODEL_CODE_sql_Header= " Credit Process - Credit Score Model";
	
	public String m_help_TXT_USER_ID_sql="";
	public String m_help_TXT_USER_ID_sql_Header=" Credit Process - User";
	
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EDIT_sql_Header= " Credit Process - Edit Credit Score Model Creation";
	
	public String	m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql ="";
	public String	m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql_Header=" Credit Process - Credit Score Model Creation";
	
	public String m_help_TXT_GURANTOR = " ";
	public String m_help_TXT_GURANTOR_Header = "Marketing - Client Help";
	
	
	public String	m_help_DIV_TXT_FACILITY_CR_SOCRE_sql_NEW ="";
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_sql_NEW_Header=" Credit Process - Credit Score Model Creation";
	
	public String	m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql ="";
	public String	m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql_Header=" Credit Process - Edit Credit Score Model Creation";
	
	
	public String m_help_DIV_TXT_FACILITY_ACTIVATION_sql="";
	public String m_help_DIV_TXT_FACILITY_ACTIVATION_sql_Header= " Credit Process - Facility Activation";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DOC_UPDATE_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DOC_UPDATE_sql_Header= " Credit Process - Client Document Updation";
	
	public String m_help_DIV_TXT_CLIENT_CODE_CLIENT_DOC_UPDATE_sql="";
	public String m_help_DIV_TXT_CLIENT_CODE_CLIENT_DOC_UPDATE_sql_Header= " Credit Process - Client Document Updation";
	
	public String m_help_DIV_TXT_CR_CLIENT_ROUTE_sql="";
	public String m_help_DIV_TXT_CR_CLIENT_ROUTE_sql_Header= " Credit Process - Collection Routes";
	
	public String m_help_DIV_TXT_DEBTOR_ADJUEST_sql="";
	public String m_help_DIV_TXT_DEBTOR_ADJUEST_sql_Header= " Credit Process - Debtor List";
	
	public String m_help_DIV_TXT_CANCEL_ASSIGN_LETTER_sql="";
	public String m_help_DIV_TXT_CANCEL_ASSIGN_LETTER_sql_Header= " Credit Process - Cancellation of Assign Letters";
	
	public String m_help_DIV_TXT_DEBTOR_CODE_CANCEL_ASSIGN_LETTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_CODE_CANCEL_ASSIGN_LETTER_sql_Header= " Credit Process - Cancellation of Assign Letters";
	
	public String m_help_DIV_TXT_CLIENT_DEBTOR_DOC_UPDATE_sql="";
	public String m_help_DIV_TXT_CLIENT_DEBTOR_DOC_UPDATE_sql_Header= " Credit Process - Debtor List";
	
	
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql_new = "";//Sj on 24-06-2009
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql_new_Header = "Credit Process - Score Evaluation";
	
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql_new = "";//Sj on 24-06-2009
	public String m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql_new_Header = "Credit Process - Score Evaluation";
	
	
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
		
		
		
		m_help_DIV_TXT_CR_CLIENT_MGT_sql=
			" SELECT L.NO,L.EMP_CODE,L.CLIENT_MANAGER,L.DESIGNATION_CODE,L.DIVISION_CODE,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.CLIENT_MANAGER,P.DESIGNATION_CODE,P.DIVISION_CODE,P.ID_NO "+
			" FROM( "+
			"	SELECT A.EMP_CODE,A.TITLE || ' ' || A.FIRST_NAME || ' ' || A.LAST_NAME CLIENT_MANAGER,A.DESIGNATION_CODE,A.DIVISION_CODE,A.ID_NO "+
			"	FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A "+
			"	WHERE A.ACTIVE_STATUS='Y' AND ( UPPER(A.EMP_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.FIRST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR  UPPER(A.LAST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('C','B') AND ACTIVE_STATUS='Y' AND ( UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_DIV_TXT_PRODUCT_FACILITY_sql=
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC "+
			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			" WHERE (UPPER(FA_PRODUCT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FA_PRODUCT_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEE_FACILITY_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC "+
			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			" WHERE (UPPER(FEE_PACK_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FEE_PACK_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_PRODUCT_FEATURE_sql=
			" SELECT L.NO,L.FA_FEATURE_CODE,L.FA_FEATURE_DESC,L.FA_FEATURE_COMMENTS,L.FEATURE_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_FEATURE_CODE,P.FA_FEATURE_DESC,P.FA_FEATURE_COMMENTS,P.FEATURE_VALUE "+
			" FROM( "+
			" SELECT FA_FEATURE_CODE,NVL(FA_FEATURE_DESC,'-') FA_FEATURE_DESC,NVL(FA_FEATURE_COMMENTS,'-') FA_FEATURE_COMMENTS,' ' FEATURE_VALUE "+
			" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES "+
			" WHERE ACTIVE_STATUS='Y' AND FA_FEATURE_CODE NOT IN (SELECT FA_FEATURE_CODE FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK WHERE FA_PRODUCT_CODE='"+m_vector.elementAt(0)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEE_FEATURE_sql=
			" SELECT L.NO,L.FEE_CODE,L.FEE_DESC,L.FEE_TYPE,L.FEE_AMT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_CODE,P.FEE_DESC,P.FEE_TYPE,P.FEE_AMT "+
			" FROM( "+
			" SELECT FEE_CODE,FEE_DESC,DECODE(FEE_TYPE,'C','CHARGE','O','OTHER') FEE_TYPE,NVL(MINIUM_VALUE,0) FEE_AMT "+
			" FROM "+m_schema_name+".FA_CO_MAS_FEES "+
			" WHERE ACTIVE_STATUS='Y' AND FEE_CODE NOT IN (SELECT FEE_CODE FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET WHERE FEE_PACK_CODE='"+m_vector.elementAt(0)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME"+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS='N' AND ( (UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT,L.FA_PRODUCT_CODE,L.PRODUCT_PACK_NAME,L.FEE_PACK_CODE,L.FEE_PACK_NAME,L.CREDIT_PERIOD,L.TOLERANCE_CREDIT_PERIOD,L.RESERVE_MARGIN "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT,P.FA_PRODUCT_CODE,P.PRODUCT_PACK_NAME,P.FEE_PACK_CODE,P.FEE_PACK_NAME,P.CREDIT_PERIOD,P.TOLERANCE_CREDIT_PERIOD,P.RESERVE_MARGIN "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT, "+
			" FA_PRODUCT_CODE,"+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE) PRODUCT_PACK_NAME,FEE_PACK_CODE,"+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE) FEE_PACK_NAME,CREDIT_PERIOD,TOLERANCE_CREDIT_PERIOD,RESERVE_MARGIN "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS IN('Y','A2') AND ( (UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.MKT_EXECUTIVE,L.MKT_EXECUTIVE_NAME,L.REGISTERED_CONTACT_PERSON,L.DESIGNATION_PAYMENT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.MKT_EXECUTIVE,P.MKT_EXECUTIVE_NAME,P.REGISTERED_CONTACT_PERSON,P.DESIGNATION_PAYMENT "+
			" FROM( "+
			"	SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE_NAME, "+
			" NVL(REGISTERED_CONTACT_PERSON,'-') REGISTERED_CONTACT_PERSON,NVL(DESIGNATION_PAYMENT,'-') DESIGNATION_PAYMENT "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			"	WHERE FACTORING_TYPE IN ('D','B') AND ACTIVE_STATUS='Y' AND (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND CLIENT_CODE  NOT IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR WHERE FACILITY_NO=UPPER('"+m_vector.elementAt(1)+"') AND CLIENT_CODE=UPPER('"+m_vector.elementAt(2)+"')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.DEBTOR_CODE,L.DEBTOR_FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.DEBTOR_CODE,P.DEBTOR_FULL_NAME "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)  DEBTOR_FULL_NAME "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
			" WHERE RELATION_STATUS='N' "+
			" AND RELATION_MOVEMENT<>'CHQENTRY' "+
			" AND ((UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS='Y' AND ( (UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS='Y' AND ((UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_ACTIVATION_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS='A2' AND ((UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('D','B') AND ACTIVE_STATUS NOT IN('N','B','T') AND (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_INVOICE_BANK_sql=
			" SELECT L.NO,L.BANK_CODE,L.NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME "+
			" FROM( "+
			"	SELECT BANK_CODE,NAME "+
			"	FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
			"	WHERE ACTIVE_STATUS='Y' AND (UPPER(BANK_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_INVOICE_BRANCH_sql=
			" SELECT L.NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.NAME "+
			" FROM( "+
			"	SELECT A.BRANCH_CODE, A.BRANCH_NAME,A.BANK_CODE,B.NAME "+
			"	FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_BANKS B "+
			"	WHERE A.BANK_CODE=B.BANK_CODE AND A.ACTIVE_STATUS='Y' AND A.BANK_CODE= '"+m_vector.elementAt(0)+"' AND "+
			" (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(1)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_QUOTATION_FACILITY_sql=
			" SELECT L.NO,L.QUOTATION_NO,L.CLIENT_CODE,L.FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.QUOTATION_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			" SELECT QUOTATION_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) FULL_NAME "+
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+
			" WHERE QUOTATION_STATUS='Y' AND "+
			" (QUOTATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			" QUOTATION_NO NOT IN (SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_sql= //addede upper() to search facility
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
			" WHERE FACILITY_STATUS<>'C' AND ( (FACILITY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR ( CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND FACILITY_NO NOT IN (SELECT FACILITY_NO FROM "+m_schema_name+".FA_CR_PRO_CRSCORE where FACILITY_NO <> '-' )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SCORE_MODEL_CODE_sql=
			" SELECT L.NO ,L.SCORE_MODEL_CODE,L.DESCRIPTION,L.TOTAL_SCORE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_MODEL_CODE,P.DESCRIPTION,P.TOTAL_SCORE "+
			" FROM( "+ 
			" SELECT SCORE_MODEL_CODE, "+
			" DESCRIPTION, "+
			" TOTAL_SCORE "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL "+
			" WHERE (SCORE_MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_USER_ID_sql=
			" SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE,L.PASSWORD "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE,P.PASSWORD "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
			" PASSWORD "+ 
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_EDIT_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.SCORE_MODEL_CODE,L.EVAL_USER,L.FINAL_APP_SCORE,L.MODEL_SCORE,L.COMMENTS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.SCORE_MODEL_CODE,P.EVAL_USER,P.FINAL_APP_SCORE,P.MODEL_SCORE,P.COMMENTS "+
			" FROM( "+ 
			" SELECT "+ 
			" FACILITY_NO, "+ 
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
			" SCORE_MODEL_CODE, "+ 
			" EVAL_USER, "+ 
			" NVL(FINAL_APP_SCORE,0) FINAL_APP_SCORE, "+ 
			" NVL(MODEL_SCORE,0) MODEL_SCORE, "+ 
			" NVL(COMMENTS,'-') COMMENTS "+ 
			" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+ 
			" WHERE APP_STATUS='ENTER' AND FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//=============================================================
		
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_sql_NEW =
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME ,L.ADDRESS,L.TEL,L.FAX "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.ADDRESS,P.TEL,P.FAX "+
			" FROM( "+ 
			/*" SELECT "+ 
			" FACILITY_NO, "+   //Modified By Sandun on 05-11-2008
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT  "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+ 
			" WHERE FACILITY_STATUS <> 'C' "+*/
			" SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME, "+
			"  B.REGISTERED_ADDRESS1 | | B.REGISTERED_ADDRESS2 || B.CITY_CODE ADDRESS,"+
			"  NVL(B.REGISTERED_TEL_NO,'-') TEL, "+
			"  NVL(B.REGISTERED_FAX_NO,'-') FAX  "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A, "+
			"      "+m_schema_name+".FA_CO_MAS_CLIENT B "+
			" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
			" AND   A.FACILITY_STATUS <> 'C' "+
			" AND (A.CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND A.CLIENT_CODE NOT IN (SELECT CLIENT_CODE FROM "+m_schema_name+".FA_CR_PRO_CRSCORE )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.EVAL_USER,L.COMMENTS "+//L.FACILITY_NO,
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.EVAL_USER,P.COMMENTS "+//P.FACILITY_NO,
			" FROM( "+ 
			" SELECT "+ 
			//" FACILITY_NO, "+ 
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
			" EVAL_USER, "+ 		
			" NVL(COMMENTS,'-') COMMENTS "+ 
			" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+ 
			" WHERE APP_STATUS='ENTER' AND (CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('%"+m_vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.EVAL_USER,L.COMMENTS "+//L.FACILITY_NO,
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.EVAL_USER,P.COMMENTS "+//P.FACILITY_NO,
			" FROM( "+ 
			" SELECT  "+//FACILITY_NO,
			" CLIENT_CODE, "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
			" EVAL_USER, "+
			" NVL(COMMENTS,'-') COMMENTS"+					 
			" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE  "+
			" WHERE APP_STATUS = 'EVAL' "+
			" AND (CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('%"+m_vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		//Added by Sandun on 22-10-2008
		//=============================================================
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_sql_new=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.EVAL_USER,L.COMMENTS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.EVAL_USER,P.COMMENTS "+
			" FROM( "+ 
			" SELECT "+ 
			" FACILITY_NO, "+ 
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
			" EVAL_USER, "+ 		
			" NVL(COMMENTS,'-') COMMENTS "+ 
			" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE "+ 
			" WHERE APP_STATUS='ENTER' AND (CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('%"+m_vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CR_SOCRE_EV_EDIT_sql_new=		
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.EVAL_USER,L.COMMENTS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.EVAL_USER,P.COMMENTS "+
			" FROM( "+ 
			" SELECT  "+
			"FACILITY_NO, "+
			" CLIENT_CODE, "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME, "+
			" EVAL_USER, "+
			" NVL(COMMENTS,'-') COMMENTS"+					 
			" FROM "+m_schema_name+".FA_CR_PRO_CRSCORE  "+
			" WHERE APP_STATUS = 'EVAL' "+
			" AND (CLIENT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('%"+m_vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		//=============================================================
		
		
		
		
		
		
		//Added by Mahela on 19-06-2007   
		m_help_DIV_TXT_FACILITY_CLIENT_DOC_UPDATE_sql=	
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT,L.CLIENT_CATEGORY  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT,P.CLIENT_CATEGORY "+
			" FROM( "+
			" SELECT B.FACILITY_NO,B.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(B.FACILITY_MGR_CODE,'O','O') CLIENT_MGR,B.CREDIT_LIMIT,A.CLIENT_CATEGORY "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY B,"+m_schema_name+".FA_CO_MAS_CLIENT A "+
			" WHERE ( (B.FACILITY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" OR (B.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND B.CLIENT_CODE=A.CLIENT_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//Added by Mahela on 19-06-2007
		m_help_DIV_TXT_CLIENT_CODE_CLIENT_DOC_UPDATE_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER,L.CLIENT_CATEGORY "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER,P.CLIENT_CATEGORY "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER,A.CLIENT_CATEGORY "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('C','B') AND ACTIVE_STATUS='Y' AND ( UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_CR_CLIENT_ROUTE_sql=
			" SELECT L.NO,L.COLL_ROUTE_CODE,L.COLL_ROUTE_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COLL_ROUTE_CODE,P.COLL_ROUTE_DESC "+
			" FROM( "+
			" SELECT A.COLL_ROUTE_CODE, A.COLL_ROUTE_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES A "+
			" WHERE A.ACTIVE_STATUS='Y' "+
			" AND (UPPER(A.COLL_ROUTE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.COLL_ROUTE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_DEBTOR_ADJUEST_sql=
			" SELECT L.NO,L.DEBTOR_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DEBTOR_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A "+
			"	WHERE A.RELATION_STATUS IN('Y','N') "+
			" AND ( A.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR  "+
			" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND  A.FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			" AND  A.CLIENT_CODE='"+m_vector.elementAt(2)+"' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//Added by Mahela on 06-09-2007
		m_help_DIV_TXT_CANCEL_ASSIGN_LETTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.DEBTOR_CODE,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.DEBTOR_CODE,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT A.FACILITY_NO FACILITY_NO,A.CLIENT_CODE CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)  FULL_NAME,B.DEBTOR_CODE DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(A.FACILITY_MGR_CODE,'O','O') CLIENT_MGR,A.CREDIT_LIMIT CREDIT_LIMIT "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
			" WHERE A.FACILITY_STATUS='Y' "+
			" AND A.FACILITY_NO=B.FACILITY_NO(+) "+	
			"	AND ( (UPPER(A.FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND A.FACILITY_NO NOT IN(SELECT FACILITY_NO FROM  "+m_schema_name+".FA_OP_PRO_LEGAL_LETTER WHERE FACILITY_NO=FACILITY_NO AND CLIENT_CODE=CLIENT_CODE AND DEBTOR_CODE=B.DEBTOR_CODE AND  LETTER_NAME='LEGAL_LETTER_TO_DEBTOR' ) "+	
			" ORDER BY FACILITY_NO )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//Added by Mahela on 06-09-2007 	
		m_help_DIV_TXT_DEBTOR_CODE_CANCEL_ASSIGN_LETTER_sql= 
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B  "+
			"	WHERE A.CLIENT_CODE=B.DEBTOR_CODE AND A.FACTORING_TYPE IN('D','B') AND "+
			" B.DEBTOR_CODE NOT IN (SELECT DEBTOR_CODE FROM  "+m_schema_name+".FA_OP_PRO_LEGAL_LETTER WHERE DEBTOR_CODE=B.DEBTOR_CODE AND FACILITY_NO=B.FACILITY_NO AND CLIENT_CODE=B.CLIENT_CODE  AND  LETTER_NAME='LEGAL_LETTER_TO_DEBTOR') "+
			" AND A.ACTIVE_STATUS NOT IN('N','B','T') AND "+
			" B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			" B.CLIENT_CODE='"+m_vector.elementAt(2)+"' AND "+
			" (A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		//Added By Disnaka Jayasuriya on 2011-09-28
		
		m_help_TXT_GURANTOR = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category,NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City " +
			" FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, P.ADDRESS1, P.ADDRESS2, P.CITY FROM (SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY,ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY,ACTIVE_STATUS, TEMP_ACTIVE_STATUS " +
			" FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " +   
			//" WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + m_vector.elementAt(1) + "','" + m_vector.elementAt(2) + "')='NO' AND " +  
			" WHERE " +  
			" ( UPPER(FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " +    
			" CLIENT_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " +      
			" TEL_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " +       
			" NIC_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%')) AND " + 
			" ACTIVE_STATUS=('" + m_vector.elementAt(1) + "')  " + 
			" ORDER BY FULL_NAME "+
			" )P) L " + 
			" WHERE L.NO>= " + Start_Val + " AND L.NO<= " + End_Val + " ";
		
		
		m_help_DIV_TXT_CLIENT_DEBTOR_DOC_UPDATE_sql=
			" SELECT L.NO,L.DEBTOR_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DEBTOR_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A "+
			"	WHERE ( A.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR  UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND  A.FACILITY_NO='"+m_vector.elementAt(1)+"' AND  A.CLIENT_CODE='"+m_vector.elementAt(2)+"' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//------------------------------------------------------------
		//------------------------------------------------------------
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


