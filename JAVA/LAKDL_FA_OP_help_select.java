import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_OP_help_select  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	//Added by Dineth on 2008-10-06
	public String m_help_TXT_CLIENT_CODE_sql="";
	public String m_help_TXT_CLIENT_CODE_sql_Header="System Administration -Backlisted Clients ";

	
	//End by Dineth on 2008-10-06
	
	public String m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql_new ="";
	public String m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql_new_Header ="Client Help";
	
	public String m_help_DIV_TXT_FACILITY_sql_new ="";
	public String m_help_DIV_TXT_FACILITY_sql_new_Header ="Facility Code Help";
	
	public String m_help_DIV_TXT_DEBTOR_SQL_NEW ="";
	public String m_help_DIV_TXT_DEBTOR_SQL_NEW_Header="Debtor Code Help";
	
	public String m_help_DIV_TXT_CR_CLIENT_MGT_sql= "";
	public String m_help_DIV_TXT_CR_CLIENT_MGT_sql_Header= "Credit Process - Employees ";
	
	public String m_help_DIV_TXT_COLLECTION_AREA_sql="";
	public String m_help_DIV_TXT_COLLECTION_AREA_sql_Header=" System Administration - Collection Areas ";
	
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql="";
	public String m_help_DIV_TXT_COLLECTION_ROUTE_CODE_sql_Header="System Administration - Collection Routes ";
	
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
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_sql_Header="Credit Process - Assign Debtors to Facility - Facility";
	
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql="";
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql_Header="Credit Process - Assign Debtors to Facility - Debtors";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql_Header="Credit Process - Assign Debtors to Facility - Modify";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql_Header="Credit Process - Client Debtors Relationship Termination";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql_Header="Operation Process - Invoice Enter - Facility ";
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql_Header="Operation Process - Invoice Enter - Debtors ";
	
	//ADD BY MALIK ON 8-9-2008
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_CLIENT_sql="";
	public String m_help_DIV_TXT_ASSIGN_DEBTOR_CLIENT_sql_Header="Factoring Collection Report";
	
	//END
	public String m_help_DIV_TXT_INVOICE_BANK_sql="";
	public String m_help_DIV_TXT_INVOICE_BANK_sql_Header="Bank Details";
	
	public String m_help_DIV_TXT_INVOICE_BRANCH_sql="";
	public String m_help_DIV_TXT_INVOICE_BRANCH_sql_Header="Branch Details";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_ENTER_EDIT_sql_Header="Operation Process - Invoice Enter - Edit ";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_EDIT_sql_Header="Operation Process - Invoice Enter - Edit ";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql_Header="Operation Process - Invoice Adjustments ";
	
	public String m_help_DIV_TXT_INVOICE_BATCH_sql="";
	public String m_help_DIV_TXT_INVOICE_BATCH_sql_Header="Operation Process - Receipts ";
	
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ADJUEST_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_ADJUEST_sql_Header="Operation Process - Invoice Adjustments - Debtors ";
	
	public String m_help_DIV_TXT_FACILITY_ADJUST_ENTER_sql="";
	public String m_help_DIV_TXT_FACILITY_ADJUST_ENTER_sql_Header="Operation Process - Adjustments - Facility ";
	
	public String m_help_DIV_TXT_DEBTOR_ADJUEST_sql="";
	public String m_help_DIV_TXT_DEBTOR_ADJUEST_sql_Header="Operation Process - Adjustments - Debtors ";
	
	public String m_help_DIV_TXT_CLIENT_ADJUST_ENTER_sql="";
	public String m_help_DIV_TXT_CLIENT_ADJUST_ENTER_sql_Header="Operation Process - Adjustments - Client ";
	
	public String m_help_DIV_TXT_POD_CHEQUE_BRANCH_sql="";
	public String m_help_DIV_TXT_POD_CHEQUE_BRANCH_sql_Header="Operation Process - Branch Details ";
	
	public String m_help_DIV_TXT_DEBTOR_CHEQUE_ENTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_CHEQUE_ENTER_sql_Header="Operation Process - Debtor Details ";
	
	public String m_help_DIV_TXT_POD_CHEQUE_ENTER_EDIT_sql="";
	public String m_help_DIV_TXT_POD_CHEQUE_ENTER_EDIT_sql_Header="Operation Process - POD Cheque Allocation Edit ";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_SETTLE_ENTER_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_SETTLE_ENTER_sql_Header="Operation Process - Settlement Receipts ";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_sql_Header="Operation Process - Settlement Receipts ";
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_sql_Header="Operation Process - Settlement Receipts ";
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_DETAILS_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_DETAILS_sql_Header="Operation Process - Settlement Receipts ";
	
	public String m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql="";
	public String m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql_Header="Operation Process - Payer Settlement Account Details ";
	
	public String m_help_DIV_TXT_EDIT_SETTLE_DETAILS_sql="";
	public String m_help_DIV_TXT_EDIT_SETTLE_DETAILS_sql_Header="Operation Process - Settlement Receipts Edit ";
	
	public String m_help_DIV_TXT_RECEIPT_CANCEL_sql="";
	public String m_help_DIV_TXT_RECEIPT_CANCEL_sql_Header="Operation Process - Receipts Cancellation ";
	
	
	public String m_help_DIV_TXT_RECEIPT_UNALLO_sql="";
	public String m_help_DIV_TXT_RECEIPT_UNALLO_sql_Header="Operation Process - Receipts Unallocation ";

	public String m_help_DIV_TXT_SETTLEMENT_ACCOUNTS_sql="";
	public String m_help_DIV_TXT_SETTLEMENT_ACCOUNTS_sql_Header="Account Code Help";

	public String m_help_DIV_TXT_SETTLE_DEPOSIT_UPDATE_sql="";
	public String m_help_DIV_TXT_SETTLE_DEPOSIT_UPDATE_sql_Header="Operation Process - Settlement Deposit Delete ";
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_EDIT_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_EDIT_sql_Header="Operation Process - Settlement Batch Entry ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_ADJUSTMENT_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_ADJUSTMENT_sql_Header="Operation Process - Client Adjustment ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql_Header="Operation Process - Client Availability - Facility ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_VIEW_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_VIEW_sql_Header="Operation Process - Client Availability - Facility ";
	
	public String m_help_DIV_TXT_CLIENT_AVAILABLE_ENTER_sql="";
	public String m_help_DIV_TXT_CLIENT_AVAILABLE_ENTER_sql_Header="Operation Process - Client Availability - Client ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_INVOICE_ALLO_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_INVOICE_ALLO_sql_Header="Operation Process - Client Availability - Facility ";
	
	public String m_help_DIV_TXT_CLIENT_INVOICE_ALLO_ENTER_sql="";
	public String m_help_DIV_TXT_CLIENT_INVOICE_ALLO_ENTER_sql_Header="Operation Process - Client Availability - Client ";
	
	public String m_help_DIV_TXT_EDIT_PAYMENT_sql="";
	public String m_help_DIV_TXT_EDIT_PAYMENT_sql_Header="Operation Process - Client Payments";
	//Added by Dineth on 28-07-2009
	public String m_help_DIV_TXT_PAYMENT_NO_sql="";
	public String m_help_DIV_TXT_PAYMENT_NO_sql_Header="Operation Process - Client Payments";
	//Added by Dineth on 28-07-2009
	
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_SCHEDULE_sql="";
	public String m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_SCHEDULE_sql_Header="Operation Process - Settlement Schedule Entry ";
	
	public String m_help_DIV_TXT_SETTLE_SCHEDULE_ENTER_EDIT_sql="";
	public String m_help_DIV_TXT_SETTLE_SCHEDULE_ENTER_EDIT_sql_Header="Operation Process - Settlement Schedule Entry - Edit ";
	
	public String m_help_DIV_TXT_PAYMENT_DISBURSMENT_sql="";
	public String m_help_DIV_TXT_PAYMENT_DISBURSMENT_sql_Header="Operation Process - Client Payments Disbursment";

	public String m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql="";
	public String m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql_Header="Operation Reports - Client Charges ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql="";
	public String m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql_Header="Operation Process - Client Charges ";
	
	public String m_help_DIV_TXT_DEBTOR_REPORT_sql="";
	public String m_help_DIV_TXT_DEBTOR_REPORT_sql_Header="Operation Reports - Debtor Statement ";
	
	public String m_help_DIV_TXT_CLIENT_ADJUST_EDIT_sql="";
	public String m_help_DIV_TXT_CLIENT_ADJUST_EDIT_sql_Header="Operation Process - Client Adjustments  ";
	
	public String m_help_DIV_TXT_CLIENT_LEGAL_LETTER_sql="";
	public String m_help_DIV_TXT_CLIENT_LEGAL_LETTER_sql_Header="Operation Process - Legal Letter ";
	
	public String m_help_DIV_TXT_TAX_INVOICE_sql="";
	public String m_help_DIV_TXT_TAX_INVOICE_sql_Header="Operation Process - TAX Invoice";
	
	public String m_help_DIV_TXT_CHARGES_REVERSAL_sql="";
	public String m_help_DIV_TXT_CHARGES_REVERSAL_sql_Header="Operation Process - Charges Reversal";
	
	public String m_help_DIV_TXT_PRINT_SETTLE_sql="";
	public String m_help_DIV_TXT_PRINT_SETTLE_sql_Header="Operation Process - Print Receipt";
	
	public String m_help_DIV_TXT_PRINT_SETTLE_ALL_sql="";
	public String m_help_DIV_TXT_PRINT_SETTLE_ALL_sql_Header="Operation Process - Print Receipt All";
	
	public String m_help_DIV_TXT_DEBTOR_CODE_LEGAL_LETTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_CODE_LEGAL_LETTER_sql_Header="Operation Process - Legal Letter to Debtor ";
	
	public String m_help_DIV_TXT_DEBTOR_LEGAL_LETTER_sql="";
	public String m_help_DIV_TXT_DEBTOR_LEGAL_LETTER_sql_Header="Operation Process - Legal Letter to Debtor ";
	
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_sql="";
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_sql_Header="Operation Process - Return Cheque Re-Banking ";
	
	// Added by Udara on 31-05-2011
	public String m_help_DIV_TXT_POD_RETURN_CHEQUE_sql="";
	public String m_help_DIV_TXT_POD_RETURN_CHEQUE_sql_Header="Operation Process - PD Cheque Re-Banking ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_DISPUTE_sql= "";
	public String m_help_DIV_TXT_FACILITY_CLIENT_DISPUTE_sql_Header= "Operation Process - Client Disputes ";
	
	public String m_help_DIV_TXT_FACILITY_CLIENT_CHARGE_ADDITION_sql= "";
	public String m_help_DIV_TXT_FACILITY_CLIENT_CHARGE_ADDITION_sql_Header= "Operation Process - Charges Addition ";
	
	public String m_help_DIV_TXT_CHEQUE_RETURN_COMMENT_sql= "";
	public String m_help_DIV_TXT_CHEQUE_RETURN_COMMENT_sql_Header= "Operation Process - Cheque Return Comments ";
	
	public String m_help_DIV_TXT_INV_ALLO_RECEIPTS_sql="";
	public String m_help_DIV_TXT_INV_ALLO_RECEIPTS_sql_Header= "Operation Process - Invoice Settlement Receipt ";
	
	public String m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql="";
	public String m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql_Header= "Operation Process - Invoice Settlement Receipt ";
	
	
	public String m_help_DIV_TXT_INV_sql="";
	public String m_help_DIV_TXT_INV_sql_Header= "Operation Process - Invoice Settlement Receipt ";
	
	public String m_help_TXT_LOCATION_CODE_sql  ="";
    public String m_help_TXT_LOCATION_CODE_sql_Header ="Location Help";
		
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql="";
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql_Header="Operation Process - Cheque Return Settlement Details ";
	//Added by Dineth on 27-04-2009
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql1="";
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql1_Header="Operation Process - Cheque Return Settlement Details ";
	
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLEMENT_DETAILS_sql1="";
	public String m_help_DIV_TXT_CHQ_RETURN_SETTLEMENT_DETAILS_sql1_Header="Operation Process - Cheque Return Settlement Details ";	
	//Added by Dineth on 27-04-2009
	
	public String m_help_DIV_TXT_DISPUTE_CODE_sql="";
	public String m_help_DIV_TXT_DISPUTE_CODE_sql_Header="";
	
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_ALL_sql="";
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_ALL_sql_Header="Operation Process - Cheque Return Settlement Details ";
	
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_CLIENT_sql="";
	public String m_help_DIV_TXT_RETURN_SETTLE_DETAILS_CLIENT_sql_Header="Operation Process - Cheque Return Settlement Details ";

//======================================= add by indika 05/09/08/======================================================

	public String m_help_DIV_TXT_FACTOR_CLIENT_NAME_sql="";
	public String m_help_DIV_TXT_FACTOR_CLIENT_NAME_sql_Header="Invoice Verification Report";
	
	public String m_help_TXT_INVOICE_NO_sql_report_sql="";
	public String m_help_TXT_INVOICE_NO_sql_report_sql_Header="Invoice Verification Report";

//======================================= end of adding by indika 05/09/08 ============================================
	
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
			"	WHERE A.ACTIVE_STATUS='Y' AND UPPER(A.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql=
		" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('C','B') AND ACTIVE_STATUS='Y' AND ( CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//Added by Dineth on 2008-10-06
		m_help_TXT_CLIENT_CODE_sql=
 "SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.FULL_NAME,NVL(L.TITLE,''),NVL(L.FIRST_NAME,''),NVL(L.SURNAME,''),NVL(L.INITIALS,''),NVL(L.OTHER_NAME,''),NVL(L.TEL_NO,''),NVL(L.FAX_NO,''),NVL(L.EMAIL,''),NVL(L.OFFICE_TEL_NO,''),"+
        "NVL(L.MOBILE_NO,''),NVL(L.ADDRESS1,''),NVL(L.ADDRESS2,''),NVL(L.CITY_CODE,''),NVL(L.NIC_NO,''),NVL(L.PASSPORT_NO,''),NVL(L.MARITAL_STATUS,''),NVL(L.DATE_OF_BIRTH,''),NVL(L.NATIONALITY,''),NVL(L.GENDER,''),NVL(L.BUSINESS_SUB_SECTOR,''),"+
        "L.CLIENT_CATEGORY,L.CAT_TYPE_CODE,NVL(REFERENCE,''),L.ACTIVE_STATUS,NVL(L.BUSINESS_CERTIFICATE_NO,''),NVL(L.KEY_DECISION_MAKER,''),NVL(L.DESIGNATION,''),NVL(L.DIRECT_TEL_NO,''),"+
        "NVL(L.CONTACT_FOR_PAYMENT,''),NVL(L.DESIGNATION_PAYMENT,''),NVL(L.FACTORY_ADDRESS1,''),NVL(L.FACTORY_ADDRESS2,''),NVL(L.FACTORY_STATUS,''),NVL(L.F_CONTACT_PERSON,''),NVL(L.REGISTERED_ADDRESS1,''),NVL(L.REGISTERED_ADDRESS2,''),"+
        "NVL(L.REGISTERED_CITY_CODE,''),NVL(L.REGISTERED_STATUS,''),NVL(L.CORRESPONDENCE_STATUS,''),NVL(F_TEL_NO,''),NVL(L.F_FAX_NO,''),nvl(F_EMAIL,''),NVL(L.ISSUED_SHARE_CAPITAL,''),NVL(L.DATE_OF_INCORPORATION,''),"+
        "NVL(L.VAT_REG_NO,''),NVL(L.VAT_REG_DATE,''),NVL(L.RESIDENTIAL_STATUS,''),NVL(L.DURATION_AT_YEARS,''),NVL(L.DURATION_AT_MONTHS,'')"+
			" FROM  "+
			" (SELECT ROWNUM NO,    P.CLIENT_CODE,P.CLIENT_TYPE,P.FULL_NAME,P.TITLE,P.FIRST_NAME,P.SURNAME,P.INITIALS,P.OTHER_NAME,P.TEL_NO,P.FAX_NO,P.EMAIL,P.OFFICE_TEL_NO,"+
    	"	P.MOBILE_NO,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.NIC_NO,P.PASSPORT_NO,P.MARITAL_STATUS,P.DATE_OF_BIRTH,P.NATIONALITY,P.GENDER,P.BUSINESS_SUB_SECTOR,"+
    	"	P.CLIENT_CATEGORY,P.CAT_TYPE_CODE,REFERENCE,P.ACTIVE_STATUS,P.BUSINESS_CERTIFICATE_NO,P.KEY_DECISION_MAKER,P.DESIGNATION,P.DIRECT_TEL_NO,"+
    	"	P.CONTACT_FOR_PAYMENT,P.DESIGNATION_PAYMENT,P.FACTORY_ADDRESS1,P.FACTORY_ADDRESS2,P.FACTORY_STATUS,P.F_CONTACT_PERSON,P.REGISTERED_ADDRESS1,P.REGISTERED_ADDRESS2,"+
    	"	P.REGISTERED_CITY_CODE,P.REGISTERED_STATUS,P.CORRESPONDENCE_STATUS,F_TEL_NO,P.F_FAX_NO,F_EMAIL,P.ISSUED_SHARE_CAPITAL,P.DATE_OF_INCORPORATION,"+
			"	P.VAT_REG_NO,P.VAT_REG_DATE,P.RESIDENTIAL_STATUS,P.DURATION_AT_YEARS,P.DURATION_AT_MONTHS"+
			" FROM( "+ 
      " SELECT "+
     	" CLIENT_CODE,CLIENT_TYPE,FULL_NAME,TITLE,FIRST_NAME,SURNAME,INITIALS,OTHER_NAME,TEL_NO,FAX_NO,EMAIL,OFFICE_TEL_NO,"+
    	"	MOBILE_NO,ADDRESS1,ADDRESS2,CITY_CODE,NIC_NO,PASSPORT_NO,MARITAL_STATUS,DATE_OF_BIRTH,NATIONALITY,GENDER,BUSINESS_SUB_SECTOR,"+
    	"	CLIENT_CATEGORY,CAT_TYPE_CODE,REFERENCE,ACTIVE_STATUS,BUSINESS_CERTIFICATE_NO,KEY_DECISION_MAKER,DESIGNATION,DIRECT_TEL_NO,"+
    	"	CONTACT_FOR_PAYMENT,DESIGNATION_PAYMENT,FACTORY_ADDRESS1,FACTORY_ADDRESS2,FACTORY_STATUS,F_CONTACT_PERSON,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,"+
    	"	REGISTERED_CITY_CODE,REGISTERED_STATUS,CORRESPONDENCE_STATUS,F_TEL_NO,F_FAX_NO,F_EMAIL,ISSUED_SHARE_CAPITAL,DATE_OF_INCORPORATION,"+
    	"	VAT_REG_NO,VAT_REG_DATE,RESIDENTIAL_STATUS,DURATION_AT_YEARS,DURATION_AT_MONTHS"+   
      " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE (UPPER(CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
		
		//End by Dineth on 2008-10-06
			
		
		m_help_DIV_TXT_PRODUCT_FACILITY_sql=
			" SELECT L.NO,L.FA_PRODUCT_CODE,L.FA_PRODUCT_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FA_PRODUCT_CODE,P.FA_PRODUCT_DESC "+
			" FROM( "+
			" SELECT FA_PRODUCT_CODE,FA_PRODUCT_DESC "+
 			" FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
			" WHERE (FA_PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND FA_PRODUCT_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FEE_FACILITY_sql=
			" SELECT L.NO,L.FEE_PACK_CODE,L.FEE_PACK_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_PACK_CODE,P.FEE_PACK_DESC "+
			" FROM( "+
			" SELECT FEE_PACK_CODE,FEE_PACK_DESC "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES_PACKS "+
			" WHERE (FEE_PACK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND FEE_PACK_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS='Y' "+
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
 			" WHERE FACILITY_STATUS='N' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_sql=
		" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

		m_help_DIV_TXT_ASSIGN_DEBTOR_FACILITY_sql=
		" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.MKT_EXECUTIVE,L.MKT_EXECUTIVE_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.MKT_EXECUTIVE,P.MKT_EXECUTIVE_NAME "+
			" FROM( "+
			"	SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			"	WHERE FACTORING_TYPE IN ('D','B') AND ACTIVE_STATUS='Y' AND ( CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR FIRST_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') ) "+
			" AND CLIENT_CODE  NOT IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR WHERE FACILITY_NO=UPPER('"+m_vector.elementAt(1)+"') AND CLIENT_CODE=UPPER('"+m_vector.elementAt(2)+"')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			//add by malik on 8-9-2008
			
			m_help_DIV_TXT_ASSIGN_DEBTOR_CLIENT_sql=
			"SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.MKT_EXECUTIVE,L.MKT_EXECUTIVE_NAME "+
			"FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.MKT_EXECUTIVE,P.MKT_EXECUTIVE_NAME "+
			" FROM( "+
			"	SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
			"	WHERE FACTORING_TYPE IN ('D','B')"+ 
      "  AND ACTIVE_STATUS='Y' "+
      "  AND CLIENT_CODE IN "+
      "  (SELECT DEBTOR_CODE "+
      "  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
      "  WHERE CLIENT_CODE LIKE ('"+m_vector.elementAt(1)+"%') "+
			"  AND  (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
      "  OR DEBTOR_CODE LIKE ('"+m_vector.elementAt(0)+"%'))) "+
			"  )P)L "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//end 
						
			
	
		m_help_DIV_TXT_FACILITY_CLIENT_DETAIL_EDIT_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.DEBTOR_CODE,L.DEBTOR_FULL_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.DEBTOR_CODE,P.DEBTOR_FULL_NAME "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)  DEBTOR_FULL_NAME "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
 			" WHERE RELATION_STATUS='N' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_DEBTOR_RELATION_TERMINATION_sql=
		" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		m_help_DIV_TXT_DEBTOR_INVOICE_ENTER_sql=
			" SELECT L.NO,L.DEBTOR_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DEBTOR_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.DEBTOR_CODE,B.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			"	A.CLIENT_CODE='"+m_vector.elementAt(2)+"' AND "+
			" B.CLIENT_CODE=A.DEBTOR_CODE AND "+
			" B.ACTIVE_STATUS NOT IN('N','B','T') AND "+
			" ( A.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		/*" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('D','O') AND ACTIVE_STATUS NOT IN('N','B','T') AND ( CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
			  
		m_help_DIV_TXT_INVOICE_BANK_sql=
		" SELECT L.NO,L.BANK_CODE,L.NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME "+
			" FROM( "+
			"	SELECT BANK_CODE,NAME "+
			"	FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
			"	WHERE ACTIVE_STATUS='Y' AND ( BANK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
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
			" ( BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')  OR BRANCH_NAME LIKE UPPER('%"+m_vector.elementAt(1)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_INVOICE_ENTER_EDIT_sql=
		" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT A.FACILITY_NO,A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(A.FACILITY_MGR_CODE,'O','O') CLIENT_MGR,A.CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY A "+
 			" WHERE A.FACILITY_STATUS='Y' AND ((A.FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND A.FACILITY_NO IN (SELECT FACILITY_NO FROM "+m_schema_name+".FA_CR_PRO_INVOICE WHERE APPROVE_STATUS='ENTER') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
  
		m_help_DIV_TXT_FACILITY_INVOICE_BATCH_EDIT_sql=
		" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE,L.REF_BATCH_NO,L.SERIAL_NO  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE,P.REF_BATCH_NO,P.SERIAL_NO "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE,NVL(A.REF_BATCH_NO,'-') REF_BATCH_NO,NVL(A.SERIAL_NO,'-') SERIAL_NO "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" ("+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'EDIT')>0) AND "+
			" ( A.BATCH_NO LIKE UPPER('"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql=	
			" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(BATCH_NO,'CONF')>0 AND "+
			" ( A.BATCH_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		//Added by Disnaka on 2012-02-15
			m_help_DIV_TXT_INVOICE_BATCH_sql=	
			" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(BATCH_NO,'CONF')>0 AND "+
			" ( A.BATCH_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
			
			
		m_help_DIV_TXT_DEBTOR_INVOICE_ADJUEST_sql=
		" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('D','B') AND ACTIVE_STATUS NOT IN('N','B','T') AND ( CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_INVOICE_ENTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_ADJUST_ENTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		m_help_DIV_TXT_DEBTOR_ADJUEST_sql=
		" SELECT L.NO,L.DEBTOR_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DEBTOR_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A "+
			"	WHERE A.RELATION_STATUS='Y' AND ( A.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR  UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND  A.FACILITY_NO='"+m_vector.elementAt(1)+"' AND  A.CLIENT_CODE='"+m_vector.elementAt(2)+"' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		m_help_DIV_TXT_CLIENT_ADJUST_ENTER_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR "+
			" FROM( "+
			" SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR "+
 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
 			" WHERE ACTIVE_STATUS IN ('Y','I') AND FACTORING_TYPE IN ('C','B') AND "+//Modified by Dineth on 01-04-2009
			" ( ((CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_POD_CHEQUE_BRANCH_sql=
			" SELECT L.NO,L.BRANCH_CODE,L.BRANCH_NAME,L.NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.NAME "+
			" FROM( "+
			"	SELECT A.BRANCH_CODE,A.BRANCH_NAME,B.NAME "+
			"	FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH A,"+m_schema_name+".AF_CO_MAS_BANKS B "+
			"	WHERE A.BANK_CODE=B.BANK_CODE AND A.ACTIVE_STATUS='Y' AND "+
			" ( A.BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR A.BRANCH_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_DEBTOR_CHEQUE_ENTER_sql=
		" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE FACTORING_TYPE IN ('D','B') AND ACTIVE_STATUS NOT IN('N','B','T') AND ( CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*
		m_help_DIV_TXT_POD_CHEQUE_ENTER_EDIT_sql=
		" SELECT L.NO,L.POD_REF_NO,L.PAYER_BRANCH_CODE,L.BRANCH_NAME,L.PAYER_ACC_NO,L.CHEQUE_NO,L.CHEQUE_DATE,L.CHEQUE_AMOUNT,L.CHEQUE_COMMENTS,L.DEBTOR_CODE,L.FULL_NAME,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.FACILITY_NO,L.CLIENT_CODE,L.CLIENT_FULL_NAME,L.PD_REALISE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.POD_REF_NO,P.PAYER_BRANCH_CODE,P.BRANCH_NAME,P.PAYER_ACC_NO,P.CHEQUE_NO,P.CHEQUE_DATE,P.CHEQUE_AMOUNT,P.CHEQUE_COMMENTS,P.DEBTOR_CODE,P.FULL_NAME,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.FACILITY_NO,P.CLIENT_CODE,P.CLIENT_FULL_NAME,P.PD_REALISE_DATE "+
			" FROM( "+		
			" SELECT DISTINCT A.POD_REF_NO,A.PAYER_BRANCH_CODE,C.BRANCH_NAME,A.PAYER_ACC_NO,A.CHEQUE_NO,TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,A.CHEQUE_AMOUNT,NVL(A.CHEQUE_COMMENTS,'-') CHEQUE_COMMENTS, "+
			" B.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) FULL_NAME, "+
			" A.CURR_CODE,NVL(A.REC_AMOUNT_CURR,0) REC_AMOUNT_CURR,NVL(A.EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" B.FACILITY_NO,"+
			" B.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) CLIENT_FULL_NAME, "+
			" TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY') PD_REALISE_DATE "+
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B ,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
			" WHERE A.POD_REF_NO=B.POD_REF_NO AND A.POD_STATUS='N'  AND A.PAYER_BRANCH_CODE=C.BRANCH_CODE "+
			" AND ((A.POD_REF_NO LIKE '"+m_vector.elementAt(0)+"%') OR (B.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		// Commented below by Udara on 31-05-2011
		// UNCOMMENT TEMPORALY BY NS ON 23-09-2011
		m_help_DIV_TXT_POD_CHEQUE_ENTER_EDIT_sql=
		" SELECT L.NO,L.POD_REF_NO,L.PAYER_BRANCH_CODE,L.BRANCH_NAME,L.PAYER_ACC_NO,L.CHEQUE_NO,L.CHEQUE_DATE,L.CHEQUE_AMOUNT,L.CHEQUE_COMMENTS,L.DEBTOR_CODE,L.FULL_NAME,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.FACILITY_NO,L.CLIENT_CODE,L.CLIENT_FULL_NAME,L.PD_REALISE_DATE,L.REC_SETT_TYPE "+ 
			" FROM  "+
			" (SELECT ROWNUM NO,P.POD_REF_NO,P.PAYER_BRANCH_CODE,P.BRANCH_NAME,P.PAYER_ACC_NO,P.CHEQUE_NO,P.CHEQUE_DATE,P.CHEQUE_AMOUNT,P.CHEQUE_COMMENTS,P.DEBTOR_CODE,P.FULL_NAME,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.FACILITY_NO,P.CLIENT_CODE,P.CLIENT_FULL_NAME,P.PD_REALISE_DATE,P.REC_SETT_TYPE "+
			" FROM( "+		
			" SELECT DISTINCT A.POD_REF_NO,A.PAYER_BRANCH_CODE,C.BRANCH_NAME,A.PAYER_ACC_NO,A.CHEQUE_NO,TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,A.CHEQUE_AMOUNT,NVL(A.CHEQUE_COMMENTS,'-') CHEQUE_COMMENTS, "+
			" A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME, "+
			" A.CURR_CODE,NVL(A.REC_AMOUNT_CURR,0) REC_AMOUNT_CURR,NVL(A.EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" A.FACILITY_NO,"+
			" A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) CLIENT_FULL_NAME, "+
			" TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY') PD_REALISE_DATE,REC_SETT_TYPE REC_SETT_TYPE "+ //REC_SETT_TYPE ADDED BY ASHINI ON 22-02-2008
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
			" WHERE A.POD_STATUS ='E'  AND A.PAYER_BRANCH_CODE=C.BRANCH_CODE "+  //changed into 'N' from to 'E' nuwan de silva on 07-06-2010
			" AND ((A.POD_REF_NO LIKE '%"+m_vector.elementAt(0)+"%') OR (A.CHEQUE_NO LIKE '%"+m_vector.elementAt(0)+"%') OR (A.DEBTOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			/* COMMENT TEMPORALY BY NS ON 23-09-2011
			// Added below by Udara on 31-05-2011
			m_help_DIV_TXT_POD_CHEQUE_ENTER_EDIT_sql=
		" SELECT L.NO,L.POD_REF_NO,L.PAYER_BRANCH_CODE,L.BRANCH_NAME,L.PAYER_ACC_NO,L.CHEQUE_NO,L.CHEQUE_DATE,L.CHEQUE_AMOUNT,L.CHEQUE_COMMENTS,L.DEBTOR_CODE,L.FULL_NAME,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.FACILITY_NO,L.CLIENT_CODE,L.CLIENT_FULL_NAME,L.PD_REALISE_DATE,L.REC_SETT_TYPE,L.RET_CHQ_SETTLE "+ 
			" FROM  "+
			" (SELECT ROWNUM NO,P.POD_REF_NO,P.PAYER_BRANCH_CODE,P.BRANCH_NAME,P.PAYER_ACC_NO,P.CHEQUE_NO,P.CHEQUE_DATE,P.CHEQUE_AMOUNT,P.CHEQUE_COMMENTS,P.DEBTOR_CODE,P.FULL_NAME,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.FACILITY_NO,P.CLIENT_CODE,P.CLIENT_FULL_NAME,P.PD_REALISE_DATE,P.REC_SETT_TYPE,P.RET_CHQ_SETTLE "+
			" FROM( "+		
			" SELECT DISTINCT A.POD_REF_NO,A.PAYER_BRANCH_CODE,C.BRANCH_NAME,A.PAYER_ACC_NO,A.CHEQUE_NO,TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,A.CHEQUE_AMOUNT,NVL(A.CHEQUE_COMMENTS,'-') CHEQUE_COMMENTS, "+
			" A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME, "+
			" A.CURR_CODE,NVL(A.REC_AMOUNT_CURR,0) REC_AMOUNT_CURR,NVL(A.EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" A.FACILITY_NO,"+
			" A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) CLIENT_FULL_NAME, "+
			" TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY') PD_REALISE_DATE,REC_SETT_TYPE REC_SETT_TYPE, "+ //REC_SETT_TYPE ADDED BY ASHINI ON 22-02-2008
			" RET_CHQ_SETTLE RET_CHQ_SETTLE "+
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
			" WHERE A.POD_STATUS ='E'  AND A.PAYER_BRANCH_CODE=C.BRANCH_CODE "+  //changed into 'N' from to 'E' nuwan de silva on 07-06-2010
			" AND ((A.POD_REF_NO LIKE '%"+m_vector.elementAt(0)+"%') OR (A.CHEQUE_NO LIKE '%"+m_vector.elementAt(0)+"%') OR (A.DEBTOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			// End by Udara on 31-05-2011
	        */
		m_help_DIV_TXT_FACILITY_INVOICE_SETTLE_ENTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_sql=
		" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE,L.REF_BATCH_NO  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE,P.REF_BATCH_NO "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE,A.REF_BATCH_NO "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" ( A.BATCH_NO LIKE UPPER('"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B  "+
			"	WHERE A.CLIENT_CODE=B.DEBTOR_CODE AND A.FACTORING_TYPE IN('D','B') AND "+
			" A.ACTIVE_STATUS NOT IN('N','B','T') AND "+
			" B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			" B.CLIENT_CODE='"+m_vector.elementAt(2)+"' AND "+
			" (A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_DETAILS_sql=
			" SELECT L.NO,L.BATCH_NO,L.FULL_NAME,L.INVOICE_NO,L.INVOICE_AMOUNT,L.BALANCE_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.FULL_NAME,P.INVOICE_NO,P.INVOICE_AMOUNT,P.BALANCE_AMOUNT "+
			" FROM( "+
			"	SELECT A.BATCH_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME,A.INVOICE_NO,A.INVOICE_AMOUNT,A.BALANCE_AMOUNT "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
			"	WHERE "+
			" A.BATCH_NO='"+m_vector.elementAt(0)+"' AND "+
			" A.DEBTOR_CODE='"+m_vector.elementAt(1)+"' AND "+
			" A.INVOICE_NO LIKE '"+m_vector.elementAt(2)+"%' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_PAYER_SETTLE_ACC_DETAILS_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.BRANCH_CODE,L.ACCOUNT_NO,L.BRANCH_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.BRANCH_CODE,P.ACCOUNT_NO,P.BRANCH_NAME "+
			" FROM( "+
			" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME,A.BRANCH_CODE,A.ACCOUNT_NO,B.BRANCH_NAME "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_BANKS A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
			" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND "+
			" ( A.CLIENT_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" A.BRANCH_CODE LIKE '"+m_vector.elementAt(0)+"%' ) AND "+
			" A.CLIENT_CODE='"+m_vector.elementAt(1)+"'  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_EDIT_SETTLE_DETAILS_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RECEIPT_TYPE,L.REC_AMOUNT,L.SETTLE_MODE,L.PAYER_BRANCH_CODE,L.PAYER_ACC_NO,L.CLIENT_CODE,L.FACILITY_NO,L.BATCH_NO,L.DEBTOR_CODE,L.INVOICE_NO,L.EFFDD,L.EFFMM,L.EFFYY,L.RECEIPT_COMMENTS,L.CHQDD,L.CHQMM,L.CHQYY,L.CHEQUE_NO,L.CURR_CODE,L.EXCHANGE_RATE_REP_CURR,L.REC_AMOUNT_CURR,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CLIENT_NAME,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-') DEBTOR_NAME,L.TEMP_RECEIPT_NO,L.COLL_OFFICER,L.RECEIPT_COUNT,L.REC_SETT_TYPE, L.RET_CHQ_SETTLE "+ // added RET_CHQ_SETTLE by udara on 27-05-2011
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RECEIPT_TYPE,P.REC_AMOUNT,P.SETTLE_MODE,P.PAYER_BRANCH_CODE,P.PAYER_ACC_NO,P.CLIENT_CODE,P.FACILITY_NO,P.BATCH_NO,P.DEBTOR_CODE,P.INVOICE_NO,P.EFFDD,P.EFFMM,P.EFFYY,P.RECEIPT_COMMENTS,P.CHQDD,P.CHQMM,P.CHQYY,P.CHEQUE_NO,P.CURR_CODE,P.EXCHANGE_RATE_REP_CURR,P.REC_AMOUNT_CURR,P.TEMP_RECEIPT_NO,P.COLL_OFFICER,P.RECEIPT_COUNT,P.REC_SETT_TYPE, P.RET_CHQ_SETTLE "+ // added RET_CHQ_SETTLE by udara on 27-05-2011
			" FROM( "+
			" SELECT RECEIPT_NO,  "+
			" RECEIPT_TYPE, "+
			" REC_AMOUNT, "+
			" SETTLE_MODE,  "+
			" PAYER_BRANCH_CODE,  "+
			" PAYER_ACC_NO, "+
			" NVL(CLIENT_CODE,'-') CLIENT_CODE,  "+
			" NVL(FACILITY_NO,'-') FACILITY_NO,  "+
			" NVL(BATCH_NO,'-') BATCH_NO, "+
			" NVL(DEBTOR_CODE,'-') DEBTOR_CODE, "+ 
			" NVL(INVOICE_NO,'-') INVOICE_NO,  "+
			" TO_CHAR(EFF_VALDATE,'DD') EFFDD, "+
			" TO_CHAR(EFF_VALDATE,'MM') EFFMM, "+
			" TO_CHAR(EFF_VALDATE,'YYYY') EFFYY, "+
			" NVL(RECEIPT_COMMENTS,'-') RECEIPT_COMMENTS,  "+
			" TO_CHAR(CHEQUE_DATE,'DD') CHQDD, "+
			" TO_CHAR(CHEQUE_DATE,'MM') CHQMM, "+
			" TO_CHAR(CHEQUE_DATE,'YYYY') CHQYY, "+
			" NVL(CHEQUE_NO,'-') CHEQUE_NO,   "+
			" EXCHANGE_RATE_REP_CURR, "+			
			" CURR_CODE, "+
			" REC_AMOUNT_CURR, "+
			" NVL(TEMP_RECEIPT_NO,'-') TEMP_RECEIPT_NO,"+
      " NVL(COLL_OFFICER,'-') COLL_OFFICER, "+
			" "+m_schema_name+".FA_GET_REBANK_COUNT(RECEIPT_NO) RECEIPT_COUNT, "+
			" REC_SETT_TYPE REC_SETT_TYPE, "+ //REC_SETT_TYPE ADDED BY ASHINI ON 22-02-2008 //26
			" RET_CHQ_SETTLE RET_CHQ_SETTLE "+ // added by udara on 27-05-2011
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
			" WHERE REC_STATUS='E'  AND RECEIPT_TYPE NOT IN('POD') AND "+
			" ( CLIENT_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" FACILITY_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" BATCH_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" DEBTOR_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" INVOICE_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" PAYER_ACC_NO LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" CHEQUE_NO LIKE '%"+m_vector.elementAt(0)+"%' ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			m_help_DIV_TXT_RECEIPT_CANCEL_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RECEIPT_TYPE,L.REC_AMOUNT,L.SETTLE_MODE,L.PAYER_BRANCH_CODE,L.PAYER_ACC_NO,L.CLIENT_CODE,L.FACILITY_NO,L.BATCH_NO,L.DEBTOR_CODE,L.INVOICE_NO,L.EFFDD,L.EFFMM,L.EFFYY,L.RECEIPT_COMMENTS,L.CHQDD,L.CHQMM,L.CHQYY,L.CHEQUE_NO,L.CURR_CODE,L.EXCHANGE_RATE_REP_CURR,L.REC_AMOUNT_CURR,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CLIENT_NAME,NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-') DEBTOR_NAME,L.TEMP_RECEIPT_NO,L.COLL_OFFICER,L.RECEIPT_COUNT,L.REC_SETT_TYPE, L.RET_CHQ_SETTLE "+ // added RET_CHQ_SETTLE by udara on 27-05-2011
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RECEIPT_TYPE,P.REC_AMOUNT,P.SETTLE_MODE,P.PAYER_BRANCH_CODE,P.PAYER_ACC_NO,P.CLIENT_CODE,P.FACILITY_NO,P.BATCH_NO,P.DEBTOR_CODE,P.INVOICE_NO,P.EFFDD,P.EFFMM,P.EFFYY,P.RECEIPT_COMMENTS,P.CHQDD,P.CHQMM,P.CHQYY,P.CHEQUE_NO,P.CURR_CODE,P.EXCHANGE_RATE_REP_CURR,P.REC_AMOUNT_CURR,P.TEMP_RECEIPT_NO,P.COLL_OFFICER,P.RECEIPT_COUNT,P.REC_SETT_TYPE, P.RET_CHQ_SETTLE "+ // added RET_CHQ_SETTLE by udara on 27-05-2011
			" FROM( "+
			" SELECT RECEIPT_NO,  "+
			" RECEIPT_TYPE, "+
			" REC_AMOUNT, "+
			" SETTLE_MODE,  "+
			" PAYER_BRANCH_CODE,  "+
			" PAYER_ACC_NO, "+
			" NVL(CLIENT_CODE,'-') CLIENT_CODE,  "+
			" NVL(FACILITY_NO,'-') FACILITY_NO,  "+
			" NVL(BATCH_NO,'-') BATCH_NO, "+
			" NVL(DEBTOR_CODE,'-') DEBTOR_CODE, "+ 
			" NVL(INVOICE_NO,'-') INVOICE_NO,  "+
			" TO_CHAR(EFF_VALDATE,'DD') EFFDD, "+
			" TO_CHAR(EFF_VALDATE,'MM') EFFMM, "+
			" TO_CHAR(EFF_VALDATE,'YYYY') EFFYY, "+
			" NVL(RECEIPT_COMMENTS,'-') RECEIPT_COMMENTS,  "+
			" TO_CHAR(CHEQUE_DATE,'DD') CHQDD, "+
			" TO_CHAR(CHEQUE_DATE,'MM') CHQMM, "+
			" TO_CHAR(CHEQUE_DATE,'YYYY') CHQYY, "+
			" NVL(CHEQUE_NO,'-') CHEQUE_NO,   "+
			" EXCHANGE_RATE_REP_CURR, "+			
			" CURR_CODE, "+
			" REC_AMOUNT_CURR, "+
			" NVL(TEMP_RECEIPT_NO,'-') TEMP_RECEIPT_NO,"+
            " NVL(COLL_OFFICER,'-') COLL_OFFICER, "+
			" "+m_schema_name+".FA_GET_REBANK_COUNT(RECEIPT_NO) RECEIPT_COUNT, "+
			" REC_SETT_TYPE REC_SETT_TYPE, "+ //REC_SETT_TYPE ADDED BY ASHINI ON 22-02-2008 //26
			" RET_CHQ_SETTLE RET_CHQ_SETTLE "+ // added by udara on 27-05-2011
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+
			" WHERE RECEIPT_NO NOT IN (SELECT RECEIPT_NO FROM "+m_schema_name+".FA_CR_PRO_RECEIPT_CANCELLATION) "+
			//" AND REC_STATUS='E'  AND RECEIPT_TYPE NOT IN('POD') AND "+
			" AND REC_STATUS<>'D'  AND "+
			" ( CLIENT_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" FACILITY_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" BATCH_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" DEBTOR_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" INVOICE_NO LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" PAYER_ACC_NO LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" CHEQUE_NO LIKE '%"+m_vector.elementAt(0)+"%' ) "+
			" ORDER BY RECEIPT_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

			m_help_DIV_TXT_RECEIPT_UNALLO_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.CLIENT_CODE,L.FACILITY_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CLIENT_NAME "+ // added RET_CHQ_SETTLE by udara on 27-05-2011
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.CLIENT_CODE,P.FACILITY_NO "+ 
			" FROM( "+
			" SELECT DISTINCT RECEIPT_NO,  "+
			" NVL(CLIENT_CODE,'-') CLIENT_CODE,  "+
			" NVL(FACILITY_NO,'-') FACILITY_NO  "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO  "+
			" WHERE  "+
			" ( RECEIPT_NO LIKE '"+m_vector.elementAt(0)+"%' OR CLIENT_CODE LIKE '"+m_vector.elementAt(0)+"%' OR "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR "+
			" FACILITY_NO LIKE '"+m_vector.elementAt(0)+"%' ) "+
			" ORDER BY RECEIPT_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
	m_help_DIV_TXT_SETTLEMENT_ACCOUNTS_sql=
		" SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE ,L.BRANCH_NAME,L.ACC_SYS_REFNO "+
		" FROM  "+
	  " (SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.ACC_SYS_REFNO "+
		" FROM( "+ 
	  " SELECT "+
    " ACC_NO, "+
    " BRANCH_CODE, "+
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
    " ACC_SYS_REFNO "+
    " FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
    " WHERE ACC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND  ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
	m_help_DIV_TXT_SETTLE_DEPOSIT_UPDATE_sql=
		" SELECT L.NO,L.DEPOSIT_NO,L.DEPOSIT_DATE,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.ACC_SYS_REFNO,L.DEPOSIT_TOTAL,L.DEPOSIT_COMMENTS "+
		" FROM  "+
	  " (SELECT ROWNUM NO,P.DEPOSIT_NO,P.DEPOSIT_DATE,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.ACC_SYS_REFNO,P.DEPOSIT_TOTAL,P.DEPOSIT_COMMENTS "+
		" FROM( "+
		" SELECT  "+
		" A.DEPOSIT_NO,  "+
		" TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY') DEPOSIT_DATE, "+
		" A.ACC_NO, "+
	  " A.BRANCH_CODE,  "+
		" B.ACC_SYS_REFNO, "+
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME, "+ 
		" A.DEPOSIT_TOTAL, "+
		" NVL(A.DEPOSIT_COMMENTS,'-') DEPOSIT_COMMENTS "+
	  " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A,"+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT B "+
		" WHERE A.ACC_NO=B.ACC_NO AND A.STATUS='Y' AND "+
		" (A.ACC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		" A.DEPOSIT_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		" A.BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
		" ORDER BY A.DEPOSIT_DATE DESC "+
		" )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
			
	m_help_DIV_TXT_FACILITY_INVOICE_BATCH_SETTLE_EDIT_sql=
			" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE,L.REF_BATCH_NO  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE,P.REF_BATCH_NO "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE,A.REF_BATCH_NO "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'CONF')>0 AND "+
			" ( A.BATCH_NO LIKE UPPER('"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
	//Added by Mahela on 02-01-2007
		m_help_DIV_TXT_FACILITY_CLIENT_ADJUSTMENT_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) AND ( CLIENT_CODE=('"+m_vector.elementAt(0)+"') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE))=('"+m_vector.elementAt(0)+"')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			//" WHERE FACILITY_STATUS IN ('Y','T') AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) OR ( CLIENT_CODE LIKE ('"+m_vector.elementAt(1)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('"+m_vector.elementAt(1)+"%')) ) "+//Modified by Dineth on 07-04-2009
				" WHERE FACILITY_STATUS = 'Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) OR ( CLIENT_CODE LIKE ('"+m_vector.elementAt(1)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('"+m_vector.elementAt(1)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_AVAILABLE_VIEW_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			//" WHERE FACILITY_STATUS IN('Y','T') AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) OR ( CLIENT_CODE LIKE ('"+m_vector.elementAt(1)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('"+m_vector.elementAt(1)+"%')) ) "+//Commented and Modified by Dineth on 07-04-2009
			" WHERE ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) OR ( CLIENT_CODE LIKE ('"+m_vector.elementAt(1)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('"+m_vector.elementAt(1)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
					
			
		m_help_DIV_TXT_CLIENT_AVAILABLE_ENTER_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR "+
			" FROM( "+
			" SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR "+
 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
 			" WHERE ACTIVE_STATUS='Y' AND FACTORING_TYPE IN ('C','B') AND "+
			" ( ((CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
			m_help_DIV_TXT_FACILITY_CLIENT_INVOICE_ALLO_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) AND ( CLIENT_CODE=('"+m_vector.elementAt(0)+"') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE))=('"+m_vector.elementAt(0)+"')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_CLIENT_INVOICE_ALLO_ENTER_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR "+
			" FROM( "+
			" SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR "+
 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
 			" WHERE ACTIVE_STATUS='Y' AND FACTORING_TYPE IN ('C','B') AND "+
			" ( ((CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_EDIT_PAYMENT_sql=
			" SELECT L.NO,L.PAYMENT_CODE,L.CLIENT_CODE,L.CFULL_NAME,L.CLIENT_MGR,L.FACILITY_NO,L.CR_LIMIT,L.PAYMENT_AMOUNT,L.PAY_DATE,L.SETTLE_MODE,L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PAY_COMMENTS,L.CHEQUE_NO,L.CHEQUE_DATE,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.PAY_3RD_PARTY_STATUS,L.PAY_3RD_PARTY_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYMENT_CODE,P.CLIENT_CODE,P.CFULL_NAME,P.CLIENT_MGR,P.FACILITY_NO,P.CR_LIMIT,P.PAYMENT_AMOUNT,P.PAY_DATE,P.SETTLE_MODE,P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PAY_COMMENTS,P.CHEQUE_NO,P.CHEQUE_DATE,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.PAY_3RD_PARTY_STATUS,P.PAY_3RD_PARTY_NAME "+
			" FROM( "+
			" SELECT PAYMENT_CODE,"+
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CFULL_NAME,"+
			" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR,"+
			" FACILITY_NO,"+
			" "+m_schema_name+".FA_GET_CLIENT_TOTAL_CR_LIMIT(CLIENT_CODE) CR_LIMIT,"+
			" NVL(PAYMENT_AMOUNT,0) PAYMENT_AMOUNT, "+
			" TO_CHAR(PAY_DATE,'DD-MM-YYYY') PAY_DATE, "+
			" SETTLE_MODE,"+ 
			" LIC_BRANCH_CODE, "+
			" LIC_ACC_NO,"+
			" PAYEE_BRANCH_CODE, "+
			" PAYEE_ACC_NO, "+
			" NVL(PAY_COMMENTS,'-') PAY_COMMENTS, "+
			" NVL(CHEQUE_NO,'-') CHEQUE_NO, "+
			" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+
			" CURR_CODE, "+
			" NVL(REC_AMOUNT_CURR,0) REC_AMOUNT_CURR, "+
			" NVL(EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" NVL(PAY_3RD_PARTY_STATUS,'N') PAY_3RD_PARTY_STATUS, "+
			" NVL(PAY_3RD_PARTY_NAME,' ') PAY_3RD_PARTY_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
 			//" WHERE PAY_STATUS='ENTER' AND "+
			" WHERE PAY_STATUS IN ('ENTER','HIGH') AND "+ //added by nuwan de silva on 12-05-08
			" ( ((PAYMENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			" ORDER BY PAY_DATE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
//Added by Dineth on 28-07-2009
m_help_DIV_TXT_PAYMENT_NO_sql=
			" SELECT L.NO,L.PAYMENT_CODE,L.CLIENT_CODE,L.CFULL_NAME,L.CLIENT_MGR,L.FACILITY_NO,L.CR_LIMIT,L.PAYMENT_AMOUNT,L.PAY_DATE,L.SETTLE_MODE,L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PAY_COMMENTS,L.CHEQUE_NO,L.CHEQUE_DATE,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.PAY_3RD_PARTY_STATUS,L.PAY_3RD_PARTY_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYMENT_CODE,P.CLIENT_CODE,P.CFULL_NAME,P.CLIENT_MGR,P.FACILITY_NO,P.CR_LIMIT,P.PAYMENT_AMOUNT,P.PAY_DATE,P.SETTLE_MODE,P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PAY_COMMENTS,P.CHEQUE_NO,P.CHEQUE_DATE,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.PAY_3RD_PARTY_STATUS,P.PAY_3RD_PARTY_NAME "+
			" FROM( "+
			" SELECT PAYMENT_CODE,"+
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CFULL_NAME,"+
			" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR,"+
			" FACILITY_NO,"+
			" "+m_schema_name+".FA_GET_CLIENT_TOTAL_CR_LIMIT(CLIENT_CODE) CR_LIMIT,"+
			" NVL(PAYMENT_AMOUNT,0) PAYMENT_AMOUNT, "+
			" TO_CHAR(PAY_DATE,'DD-MM-YYYY') PAY_DATE, "+
			" SETTLE_MODE,"+ 
			" LIC_BRANCH_CODE, "+
			" LIC_ACC_NO,"+
			" PAYEE_BRANCH_CODE, "+
			" PAYEE_ACC_NO, "+
			" NVL(PAY_COMMENTS,'-') PAY_COMMENTS, "+
			" NVL(CHEQUE_NO,'-') CHEQUE_NO, "+
			" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+
			" CURR_CODE, "+
			" NVL(REC_AMOUNT_CURR,0) REC_AMOUNT_CURR, "+
			" NVL(EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" NVL(PAY_3RD_PARTY_STATUS,'N') PAY_3RD_PARTY_STATUS, "+
			" NVL(PAY_3RD_PARTY_NAME,' ') PAY_3RD_PARTY_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
 			//" WHERE PAY_STATUS='ENTER' AND "+
			//" WHERE PAY_STATUS IN ('ENTER','HIGH') AND "+ //added by nuwan de silva on 12-05-08
			" WHERE "+
			" ( ((PAYMENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			" ORDER BY PAY_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";

//End by Dineth on 28-07-2009


		m_help_DIV_TXT_DEBTOR_INVOICE_SETTLE_SCHEDULE_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B  "+
			"	WHERE A.CLIENT_CODE=B.DEBTOR_CODE AND A.FACTORING_TYPE IN('D','B') AND "+
			" A.ACTIVE_STATUS NOT IN('N','B','T') AND "+
			" (A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_SETTLE_SCHEDULE_ENTER_EDIT_sql=
		" SELECT L.NO,L.SETTLE_SCHDULE_REF_NO,L.PAYER_BRANCH_CODE,L.BRANCH_NAME,L.PAYER_ACC_NO,L.CHEQUE_NO,L.VALUE_DATE,L.VALUE_AMOUNT,L.SCH_COMMENTS,L.DEBTOR_CODE,L.FULL_NAME,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.TEMP_RECEIPT_NO,L.COLL_OFFICER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SETTLE_SCHDULE_REF_NO,P.PAYER_BRANCH_CODE,P.BRANCH_NAME,P.PAYER_ACC_NO,P.CHEQUE_NO,P.VALUE_DATE,P.VALUE_AMOUNT,P.SCH_COMMENTS,P.DEBTOR_CODE,P.FULL_NAME,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.TEMP_RECEIPT_NO,P.COLL_OFFICER "+
			" FROM( "+		
			" SELECT DISTINCT A.SETTLE_SCHDULE_REF_NO,A.PAYER_BRANCH_CODE,C.BRANCH_NAME,A.PAYER_ACC_NO,A.CHEQUE_NO,TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,A.VALUE_AMOUNT,NVL(A.SCH_COMMENTS,'-') SCH_COMMENTS, "+
			" A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) FULL_NAME, "+
			" A.CURR_CODE,NVL(A.REC_AMOUNT_CURR,0) REC_AMOUNT_CURR,NVL(A.EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" NVL(A.TEMP_RECEIPT_NO,'-') TEMP_RECEIPT_NO,A.COLL_OFFICER "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH C "+
			" WHERE A.SCH_STATUS='N'  AND A.PAYER_BRANCH_CODE=C.BRANCH_CODE "+
			" AND ((A.SETTLE_SCHDULE_REF_NO LIKE '"+m_vector.elementAt(0)+"%') OR (A.DEBTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_PAYMENT_DISBURSMENT_sql=
			" SELECT L.NO,L.PAYMENT_CODE,L.CLIENT_CODE,L.CFULL_NAME,L.CLIENT_MGR,L.FACILITY_NO,L.CR_LIMIT,L.PAYMENT_AMOUNT,L.PAY_DATE,L.SETTLE_MODE,L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PAY_COMMENTS,L.CHEQUE_NO,L.CHEQUE_DATE,L.CURR_CODE,L.REC_AMOUNT_CURR,L.EXCHANGE_RATE_REP_CURR,L.PAY_3RD_PARTY_STATUS,L.PAY_3RD_PARTY_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYMENT_CODE,P.CLIENT_CODE,P.CFULL_NAME,P.CLIENT_MGR,P.FACILITY_NO,P.CR_LIMIT,P.PAYMENT_AMOUNT,P.PAY_DATE,P.SETTLE_MODE,P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PAY_COMMENTS,P.CHEQUE_NO,P.CHEQUE_DATE,P.CURR_CODE,P.REC_AMOUNT_CURR,P.EXCHANGE_RATE_REP_CURR,P.PAY_3RD_PARTY_STATUS,P.PAY_3RD_PARTY_NAME "+
			" FROM( "+
			" SELECT PAYMENT_CODE,"+
			" CLIENT_CODE,"+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) CFULL_NAME,"+
			" "+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR,"+
			" FACILITY_NO,"+
			" "+m_schema_name+".FA_GET_CLIENT_TOTAL_CR_LIMIT(CLIENT_CODE) CR_LIMIT,"+
			" NVL(PAYMENT_AMOUNT,0) PAYMENT_AMOUNT, "+
			" TO_CHAR(PAY_DATE,'DD-MM-YYYY') PAY_DATE, "+
			" SETTLE_MODE,"+ 
			" LIC_BRANCH_CODE, "+
			" LIC_ACC_NO,"+
			" PAYEE_BRANCH_CODE, "+
			" PAYEE_ACC_NO, "+
			" NVL(PAY_COMMENTS,'-') PAY_COMMENTS, "+
			" NVL(CHEQUE_NO,'-') CHEQUE_NO, "+
			" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+
			" CURR_CODE, "+
			" NVL(REC_AMOUNT_CURR,0) REC_AMOUNT_CURR, "+
			" NVL(EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR, "+
			" NVL(PAY_3RD_PARTY_STATUS,'N') PAY_3RD_PARTY_STATUS, "+
			" NVL(PAY_3RD_PARTY_NAME,' ') PAY_3RD_PARTY_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
 			// " WHERE PAY_STATUS='PRINT' AND "+
 			" WHERE PAY_STATUS='CONF' AND "+
			" ( ((PAYMENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			" ORDER BY PAY_DATE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
//==================================================== add by indika 05/09/08 ========================================================================
			m_help_DIV_TXT_FACTOR_CLIENT_NAME_sql=
					" SELECT L.NO,L.FULL_NAME,L.CLIENT_CODE,L.CLIENT_ADDRESS  "+
					" FROM  "+
					" (SELECT ROWNUM NO,P.FULL_NAME,P.CLIENT_CODE,P.CLIENT_ADDRESS "+
					" FROM( "+
					" SELECT FULL_NAME,CLIENT_CODE,(TO_CHAR(REGISTERED_ADDRESS1)||' '||TO_CHAR(REGISTERED_ADDRESS2)) CLIENT_ADDRESS "+
		 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
		 			" WHERE FACTORING_TYPE IN ('C','B') AND "+
					" ( ((CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
					"  )P)L  "+
					" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
					
			m_help_TXT_INVOICE_NO_sql_report_sql=
					" SELECT L.NO,L.INVOICE_NO,L.FULL_NAME,L.INVOICE_AMOUNT,L.BALANCE_AMOUNT,L.CLIENT_CODE,L.INVOICE_DATE "+
					" FROM "+
					" (SELECT ROWNUM NO,P.INVOICE_NO,P.FULL_NAME,P.INVOICE_AMOUNT,P.BALANCE_AMOUNT,P.CLIENT_CODE,P.INVOICE_DATE "+ 
					" FROM( "+
					" SELECT A.INVOICE_NO,B.FULL_NAME,A.INVOICE_AMOUNT,A.BALANCE_AMOUNT,A.CLIENT_CODE,A.INVOICE_DATE "+
					" FROM LAKDL.FA_CR_PRO_INVOICE_DETAIL A,LAKDL.FA_CO_MAS_CLIENT B "+
					" WHERE "+
					" A.INVOICE_STATUS ='CONF' AND"+
					" A.INVOICE_DATE >= TO_DATE('"+m_vector.elementAt(3)+"','dd/mm/yyyy') AND "+
					" A.INVOICE_DATE <= TO_DATE('"+m_vector.elementAt(2)+"','dd/mm/yyyy') AND "+
					" A.INVOICE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND"+
					" (UPPER(B.FULL_NAME) LIKE  UPPER('"+m_vector.elementAt(1)+"%'))  "+
					" )P)L "+
					" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
//============================================== end of adding by indika 05/09/08 ==============================================================
		m_help_DIV_TXT_FACTOR_CLIENT_CHARGES_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR "+
			" FROM( "+
			" SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR "+
 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
 			" WHERE FACTORING_TYPE IN ('C','B') AND "+
			" ( ((CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
			m_help_DIV_TXT_FACILITY_CLIENT_INVOICE_ALLO_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')) AND ( CLIENT_CODE=('"+m_vector.elementAt(0)+"') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE))=('"+m_vector.elementAt(0)+"')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_CLIENT_INVOICE_ALLO_ENTER_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR "+
			" FROM( "+
			" SELECT CLIENT_CODE,FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MGR "+
 			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
 			" WHERE ACTIVE_STATUS='Y' AND FACTORING_TYPE IN ('C','B') AND "+
			" ( ((CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_FACILITY_CLIENT_CHARGES_sql=	
		  " SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_DIV_TXT_DEBTOR_REPORT_sql=	
		  " SELECT L.NO,L.DEBTOR_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DEBTOR_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.DEBTOR_CODE,B.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			"	A.CLIENT_CODE='"+m_vector.elementAt(2)+"' AND "+
			" B.CLIENT_CODE=A.DEBTOR_CODE AND "+
			" B.ACTIVE_STATUS NOT IN('N','B','T') AND "+
			" ( A.DEBTOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_DIV_TXT_CLIENT_ADJUST_EDIT_sql=
			" SELECT L.NO,L.ADJUSTMENT_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.FACILITY_NO,L.ADJUST_DATE,L.ADJUST_AMOUNT,L.SOURCE_DOCUMENT,L.APPROVAL_COMMENTS,L.ADJUST_TYPE ,L.NARRATION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ADJUSTMENT_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.FACILITY_NO,P.ADJUST_DATE,P.ADJUST_AMOUNT,P.SOURCE_DOCUMENT,P.APPROVAL_COMMENTS,P.ADJUST_TYPE ,P.NARRATION"+
			" FROM( "+
			" SELECT B.ADJUSTMENT_NO ADJUSTMENT_NO,"+
			" B.CLIENT_CODE CLIENT_CODE, "+
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) FULL_NAME, "+
			" "+m_schema_name+".FA_GET_CLIENT_MANAGER(B.CLIENT_CODE,'C','N') CLIENT_MGR, "+
			" B.FACILITY_NO FACILITY_NO, "+
			" TO_CHAR(B.ADJUST_DATE,'DD-MM-YYYY') ADJUST_DATE, "+
			" NVL(B.ADJUST_AMOUNT,0) ADJUST_AMOUNT, "+
			" NVL(B.SOURCE_DOCUMENT,'-') SOURCE_DOCUMENT, "+
			" NVL(B.APPROVAL_COMMENTS,'-') APPROVAL_COMMENTS, "+
			" NVL(B.ADJUST_TYPE,'-') ADJUST_TYPE ,"+
			" NVL(B.NARRATION,'-') NARRATION "+
 			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS B "+
 			" WHERE B.ADJUST_CATEGORY='CLA' AND "+
			" ( ((B.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(B.ADJUSTMENT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_CLIENT_LEGAL_LETTER_sql=
			" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND FACILITY_NO NOT IN(SELECT FACILITY_NO FROM  "+m_schema_name+".FA_OP_PRO_LEGAL_LETTER WHERE FACILITY_NO=FACILITY_NO AND CLIENT_CODE=CLIENT_CODE ) "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
	
		m_help_DIV_TXT_TAX_INVOICE_sql=
			" SELECT L.NO,L.TAX_INVOICE_NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.MONTH_START,L.MONTH_END1  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TAX_INVOICE_NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.MONTH_START,P.MONTH_END1 "+
			" FROM( "+
			" SELECT TAX_INVOICE_NO,FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,TO_CHAR(MONTH_START,'DD-MM-YYYY') MONTH_START,TO_CHAR(MONTH_END,'DD-MM-YYYY') MONTH_END1 "+
 			" FROM "+m_schema_name+".FA_CR_PRO_TAX_INVOICE "+
 			" WHERE ( (UPPER(FACILITY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" ORDER BY MONTH_END  DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
		m_help_DIV_TXT_CHARGES_REVERSAL_sql=
		" SELECT L.NO,L.CHARGES_REF_NO,L.CLIENT_CODE,L.FACILITY_NO,L.FEE_DESC,L.DRCR_STATUS,L.EFF_DATE,L.FEE_CHARGE_AMOUNT,L.FEE_CODE  "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.CHARGES_REF_NO,P.CLIENT_CODE,P.FACILITY_NO,P.FEE_DESC,P.DRCR_STATUS,P.EFF_DATE,P.FEE_CHARGE_AMOUNT,P.FEE_CODE "+
		" FROM( "+
		" SELECT CHARGES_REF_NO,CLIENT_CODE,FACILITY_NO,FEE_DESC,DRCR_STATUS,EFF_DATE,FEE_CHARGE_AMOUNT,FEE_CODE "+
  	" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES  "+
 		" WHERE CLIENT_CODE='"+m_vector.elementAt(1)+"' AND FACILITY_NO='"+m_vector.elementAt(2)+"' AND "+
		" (UPPER(CHARGES_REF_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
		"  )P)L  "+
		" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_PRINT_SETTLE_sql=
		" SELECT L.NO,L.RECEIPT_NO,L.SETTLE_MODE,L.PAYER_BRANCH_CODE,L.PAYER_ACC_NO,L.REC_AMOUNT,L.EFF_VALDATE,L.CLIENT_CODE,L.FACILITY_NO  "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.RECEIPT_NO,P.SETTLE_MODE,P.PAYER_BRANCH_CODE,P.PAYER_ACC_NO,P.REC_AMOUNT,P.EFF_VALDATE,P.CLIENT_CODE,P.FACILITY_NO "+
		" FROM( "+
		" SELECT RECEIPT_NO,SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,REC_AMOUNT,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,CLIENT_CODE,FACILITY_NO "+
		" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
		" WHERE RECON_STATUS='Y' AND "+
		" (UPPER(RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
		" ORDER BY RECON_DATE DESC "+
		"  )P)L  "+
		" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_PRINT_SETTLE_ALL_sql=
		" SELECT L.NO,L.RECEIPT_NO,L.SETTLE_MODE,L.PAYER_BRANCH_CODE,L.PAYER_ACC_NO,L.REC_AMOUNT,L.EFF_VALDATE,L.CLIENT_CODE,L.FACILITY_NO  "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.RECEIPT_NO,P.SETTLE_MODE,P.PAYER_BRANCH_CODE,P.PAYER_ACC_NO,P.REC_AMOUNT,P.EFF_VALDATE,P.CLIENT_CODE,P.FACILITY_NO "+
		" FROM( "+
		" SELECT RECEIPT_NO,SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,REC_AMOUNT,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,CLIENT_CODE,FACILITY_NO "+
		" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
		" WHERE RECON_STATUS='Y' AND "+
		" RECEIPT_NO NOT IN (SELECT RECEIPT_NO FROM FA_OP_PRO_PRINT_RECEIPT ) AND "+
		" (UPPER(RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
		" ORDER BY RECON_DATE DESC "+
		"  )P)L  "+
		" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_DIV_TXT_DEBTOR_CODE_LEGAL_LETTER_sql= 
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
		
		m_help_DIV_TXT_DEBTOR_LEGAL_LETTER_sql=
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
			
		m_help_DIV_TXT_RETURN_SETTLE_DETAILS_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RETURN_NO,L.CHEQUE_NO,L.REALIZE_DATE,L.RETURN_BAL,L.C_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RETURN_NO,P.CHEQUE_NO,P.REALIZE_DATE,P.RETURN_BAL,P.C_NAME "+
			" FROM( "+
			" SELECT A.RECEIPT_NO,A.RETURN_NO,A.CHEQUE_NO,TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE,"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO) RETURN_BAL,"+
			" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) C_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 			" WHERE "+
			" A.RECEIPT_NO=B.RECEIPT_NO AND B.REBANK_STATUS IN ('N','R')"+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			"	( "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RETURN_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  ) "+
			" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)>0 "+
			//" ORDER BY A.RETURN_NO
			")P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			// Added by Udara on 31-05-2011
			m_help_DIV_TXT_POD_RETURN_CHEQUE_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RETURN_NO,L.CHEQUE_NO,L.REALIZE_DATE,L.RETURN_BAL,L.C_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RETURN_NO,P.CHEQUE_NO,P.REALIZE_DATE,P.RETURN_BAL,P.C_NAME "+
			" FROM( "+
			
			// Query 1
			" SELECT A.RECEIPT_NO,A.RETURN_NO,A.CHEQUE_NO,TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE,"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO) RETURN_BAL,"+
			" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) C_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 			" WHERE "+
			" A.RECEIPT_NO=B.RECEIPT_NO AND B.REBANK_STATUS IN ('N','R')"+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			"	( "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RETURN_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  ) "+
			" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)>0 "+
			" AND A.RECEIPT_NO NOT IN (SELECT RE_BANK_RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_POD_CHQ_REBANK) "+ // new change
			
			// Query 2
			" UNION "+
			
			" SELECT A.RECEIPT_NO,A.RETURN_NO,A.CHEQUE_NO,TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE,"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO) RETURN_BAL,"+
			" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) C_NAME "+
 			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 			" WHERE "+
			" A.RECEIPT_NO=B.RECEIPT_NO AND B.REBANK_STATUS IN ('N','R')"+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			"	( "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR "+
			" (UPPER(A.RETURN_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  ) "+
			// " AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)>0 "+ // commeted this as a new change
			" AND A.RECEIPT_NO IN (SELECT RE_BANK_RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_POD_CHQ_REBANK) "+
			
			")P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			// End by Udara on 31-05-2011
		
		//Added by Mahela on 14-06-2007
		m_help_DIV_TXT_FACILITY_CLIENT_DISPUTE_sql=
		 	" SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.MK_OFFICER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.MK_OFFICER "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','C') MK_OFFICER "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND ( CLIENT_CODE LIKE ('"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE ('"+m_vector.elementAt(0)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	

		m_help_DIV_TXT_FACILITY_CLIENT_CHARGE_ADDITION_sql=
			" SELECT L.NO,L.FEE_CODE,L.FEE_DESC,L.MINIUM_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FEE_CODE,P.FEE_DESC,P.MINIUM_VALUE "+
			" FROM( "+
			" SELECT B.FEE_CODE,B.FEE_DESC,NVL(B.MINIUM_VALUE,0) MINIUM_VALUE "+
 			" FROM "+m_schema_name+".FA_CO_MAS_FEES B "+
 			" WHERE B.ACTIVE_STATUS='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
		m_help_DIV_TXT_CHEQUE_RETURN_COMMENT_sql=
			" SELECT L.NO,L.CHQ_NARR_CODE,L.CHQ_NARRATIONS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CHQ_NARR_CODE,P.CHQ_NARRATIONS "+
			" FROM( "+
			" SELECT CHQ_NARR_CODE,UPPER(CHQ_NARRATIONS) CHQ_NARRATIONS "+
			" FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS "+
			" WHERE ACTIVE_STATUS='Y' "+
			" AND (UPPER(CHQ_NARRATIONS) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
		m_help_DIV_TXT_INV_ALLO_RECEIPTS_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.BALANCE_AMOUNT,L.CHEQUE_NO,L.C_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.BALANCE_AMOUNT,P.CHEQUE_NO,P.C_NAME "+
			" FROM( "+
			" SELECT RECEIPT_NO,BALANCE_AMOUNT,CHEQUE_NO,DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) C_NAME "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
			" WHERE REC_STATUS='Y' "+
			" AND BALANCE_AMOUNT>0 "+
			" AND FACILITY_NO='"+m_vector.elementAt(0)+"' "+
			" AND ((CHEQUE_NO LIKE ('%"+m_vector.elementAt(1)+"%')) OR (RECEIPT_NO LIKE ('%"+m_vector.elementAt(1)+"%')) ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	

		m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql=
			" SELECT L.NO,L.D_NAME,L.INVOICE_NO,L.BALANCE_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.D_NAME,P.INVOICE_NO,P.BALANCE_AMOUNT "+
			" FROM( "+
			" SELECT "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) D_NAME,INVOICE_NO,BALANCE_AMOUNT "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
			" WHERE INVOICE_STATUS='CONF' "+
			" AND BALANCE_AMOUNT>0 "+
			" AND FACILITY_NO='"+m_vector.elementAt(0)+"' "+
			" AND ( "+
			" (BATCH_NO LIKE ('%"+m_vector.elementAt(1)+"%')) OR "+
			" (INVOICE_NO LIKE ('%"+m_vector.elementAt(1)+"%')) OR "+
			" (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			" ) "+
			" ORDER BY DEBTOR_CODE,BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
        
		//added by disnaka on 2012-02-15
		m_help_DIV_TXT_INV_sql=
			" SELECT L.NO,L.INVOICE_NO,L.D_NAME,L.BALANCE_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.D_NAME,P.INVOICE_NO,P.BALANCE_AMOUNT "+
			" FROM( "+
			" SELECT "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) D_NAME,INVOICE_NO,BALANCE_AMOUNT "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
			" WHERE INVOICE_STATUS='CONF' "+
			" AND BALANCE_AMOUNT>0 "+
			" AND FACILITY_NO='"+m_vector.elementAt(0)+"' "+
			" AND BATCH_NO LIKE ('"+m_vector.elementAt(1)+"') "+
			" AND DEBTOR_CODE LIKE ('"+m_vector.elementAt(2)+"') "+
			" AND INVOICE_NO LIKE ('%"+m_vector.elementAt(3)+"%') "+
			" ORDER BY DEBTOR_CODE,BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		 
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
        
		
		
		m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.SET_DET,L.REC_AMOUNT,L.BALANCE_AMOUNT,L.FACILITY_NO,L.CNAME,L.CCODE,L.RECEIPT_TYPE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.SET_DET,P.REC_AMOUNT,P.BALANCE_AMOUNT,P.FACILITY_NO,P.CNAME,P.CCODE,P.RECEIPT_TYPE "+
			" FROM( "+
			" SELECT RECEIPT_NO,SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET,REC_AMOUNT,"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL_SETT(RECEIPT_NO) BALANCE_AMOUNT,"+
			" FACILITY_NO, "+
			" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+
			" DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) CCODE,RECEIPT_TYPE "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
			" WHERE BALANCE_AMOUNT>0 "+
			" AND REC_STATUS='Y' "+
			" AND FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			" AND ( "+
			" (RECEIPT_NO LIKE ('%"+m_vector.elementAt(0)+"%')) OR "+
			" (NVL(CHEQUE_NO,' ') LIKE ('%"+m_vector.elementAt(0)+"%')) OR "+
			" (NVL(UPPER(DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE))),' ') LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ) "+
			" ORDER BY BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	

//Added by Dineth on 27-04-2009
m_help_DIV_TXT_CHQ_RETURN_SETTLE_DETAILS_sql1=
			" SELECT L.NO,L.RECEIPT_NO,L.SET_DET,L.REC_AMOUNT,L.BALANCE_AMOUNT,L.FACILITY_NO,L.CNAME,L.CCODE,L.RECEIPT_TYPE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.SET_DET,P.REC_AMOUNT,P.BALANCE_AMOUNT,P.FACILITY_NO,P.CNAME,P.CCODE,P.RECEIPT_TYPE "+//BALANCE_AMOUNT CHANGED INTO REC_AMOUNT
			" FROM( "+
			" SELECT RECEIPT_NO,SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET,REC_AMOUNT,"+
			" "+m_schema_name+".FA_OP_SETTL_REC_REBANK(RECEIPT_NO) BALANCE_AMOUNT,"+//"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL_SETT(RECEIPT_NO)
			//" BALANCE_AMOUNT ,"+
			" FACILITY_NO, "+
			//" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+//comment by ns on 24-09-2010
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(DEBTOR_CODE,CLIENT_CODE)) CNAME , "+
			" NVL(DEBTOR_CODE,CLIENT_CODE) CCODE, "+
			//" DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) CCODE,"+//comment by ns on 24-09-2010
			" RECEIPT_TYPE "+ 
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
			//" WHERE BALANCE_AMOUNT>0 "+ //comment by ns on 21-09-2010
			" WHERE REC_STATUS='Y' "+ 
			" AND FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			" AND  "+m_schema_name+".FA_OP_SETTL_REC_REBANK(RECEIPT_NO)>0 "+//Added by Sandun on 14-08-2009
			//" AND RECEIPT_NO NOT IN(SELECT RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK) "+
			" AND ( "+
			" RECEIPT_NO LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			" NVL(CHEQUE_NO,' ') LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			//" NVL(UPPER(DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE))),' ') LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(DEBTOR_CODE,CLIENT_CODE))) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" ) "+
			" ORDER BY BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			


		//Added by MADHAWA 2011-04-25
m_help_DIV_TXT_CHQ_RETURN_SETTLEMENT_DETAILS_sql1=
			" SELECT L.NO,L.RECEIPT_NO,L.SET_DET,L.REC_AMOUNT,L.BALANCE_AMOUNT,L.FACILITY_NO,L.CNAME,L.CCODE,L.RECEIPT_TYPE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.SET_DET,P.REC_AMOUNT,P.BALANCE_AMOUNT,P.FACILITY_NO,P.CNAME,P.CCODE,P.RECEIPT_TYPE "+//BALANCE_AMOUNT CHANGED INTO REC_AMOUNT
			" FROM( "+
			" SELECT RECEIPT_NO,SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET,REC_AMOUNT,"+
			" "+m_schema_name+".FA_OP_SETTL_REC_REBANK(RECEIPT_NO) BALANCE_AMOUNT,"+//"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL_SETT(RECEIPT_NO)
			" FACILITY_NO, "+
			//" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+//comment by ns on 24-09-2010
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(DEBTOR_CODE,CLIENT_CODE)) CNAME , "+
			" NVL(DEBTOR_CODE,CLIENT_CODE) CCODE,"+
			//" DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) CCODE,"+//comment by ns on 24-09-2010
			" RECEIPT_TYPE "+ 
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
			//" WHERE BALANCE_AMOUNT>0 "+ //comment by ns on 21-09-2010
			" WHERE REC_STATUS='Y' "+ 
			//" WHERE REC_STATUS='B' "+ 
			" AND RECEIPT_TYPE ='RS' "+//PICK RECEIPTS ONLY FOR SETTLING THE RETURNS 
			" AND FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			" AND  "+m_schema_name+".FA_OP_SETTL_REC_REBANK(RECEIPT_NO)>0 "+//Added by Sandun on 14-08-2009
			//" AND RECEIPT_NO NOT IN(SELECT RECEIPT_NO FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK) "+
			" AND ( "+
			" RECEIPT_NO LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			" NVL(CHEQUE_NO,' ') LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			//" NVL(UPPER(DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE))),' ') LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(DEBTOR_CODE,CLIENT_CODE))) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" ) "+
			" ORDER BY BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			
			m_help_DIV_TXT_DISPUTE_CODE_sql=
			" SELECT L.NO ,L.DISPUTE_CODE \"Disputes\",L.DISPUTE_DESC,NVL(L.ACTIVATION_POINT,'-')"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISPUTE_CODE,P.DISPUTE_DESC,P.ACTIVATION_POINT "+
			" FROM( "+ 
  			" SELECT "+
      		" DISPUTE_CODE , "+
      		" DISPUTE_DESC,  "+
			" ACTIVATION_POINT "+	
      		" FROM "+m_schema_name+".FA_CO_MAS_DISPUTE_CODE "+
		  	" WHERE (UPPER(DISPUTE_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DISPUTE_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  "+
			"	AND ACTIVE_STATUS=('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			


			
			
			m_help_DIV_TXT_COLLECTION_AREA_sql=
			" SELECT L.NO ,L.AREA_CODE, L.AREA_DESC,L.CITY_CODE,L.CITY_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE, P.AREA_DESC,P.CITY_CODE,P.CITY_DESC "+
			" FROM( "+
			" SELECT A.AREA_CODE,A.AREA_DESC, A.CITY_CODE,B.CITY_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_AREA A,"+m_schema_name+".AF_CO_MAS_CITY B "+
			" WHERE A.CITY_CODE=B.CITY_CODE "+
			" AND   ( UPPER(A.AREA_CODE) LIKE  UPPER('"+m_vector.elementAt(0)+"%')  "+
			" OR      UPPER(A.AREA_DESC) LIKE  UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" )AND   A.ACTIVE_STATUS LIKE ('Y') "+
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







//End by Dineth on 27-04-2009
		m_help_DIV_TXT_RETURN_SETTLE_DETAILS_ALL_sql=
			" SELECT L.NO,L.RECEIPT_NO,L.RETURN_NO,L.CHEQUE_NO,L.REALIZE_DATE,L.RETURN_BAL,L.C_NAME ,L.REC_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RETURN_NO,P.CHEQUE_NO,P.REALIZE_DATE,P.RETURN_BAL,P.C_NAME,P.REC_AMOUNT "+
			" FROM( "+
			" SELECT A.RECEIPT_NO,A.RETURN_NO,A.CHEQUE_NO,TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE,"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO) RETURN_BAL,"+
			" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) C_NAME ,B.REC_AMOUNT REC_AMOUNT"+
 			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 			" WHERE "+
			" A.RECEIPT_NO=B.RECEIPT_NO "+
			" AND B.REBANK_STATUS IN ('N','R') "+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			//" AND DECODE('"+m_vector.elementAt(3)+"','CS',B.CLIENT_CODE,B.DEBTOR_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')   AND "+
			//" AND DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')   AND "+
			//" AND NVL(B.DEBTOR_CODE,B.CLIENT_CODE) = '"+m_vector.elementAt(2)+"'   "+//MOD BY SANDUN ON 14-08-2009
			"	 AND ( "+
			" UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.RETURN_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  ) "+
			" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)>0 "+
			//" ORDER BY A.RETURN_NO 
			")P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			
			
			m_help_DIV_TXT_RETURN_SETTLE_DETAILS_CLIENT_sql=//SANDUN ON 14-08-2009
			" SELECT L.NO,L.RECEIPT_NO,L.RETURN_NO,L.CHEQUE_NO,L.REALIZE_DATE,L.RETURN_BAL,L.C_NAME ,L.REC_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_NO,P.RETURN_NO,P.CHEQUE_NO,P.REALIZE_DATE,P.RETURN_BAL,P.C_NAME,P.REC_AMOUNT "+
			" FROM( "+
			" SELECT A.RECEIPT_NO,A.RETURN_NO,A.CHEQUE_NO,TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY') REALIZE_DATE, "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO) RETURN_BAL,"+//"+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)
			" DECODE(B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)) C_NAME,B.REC_AMOUNT REC_AMOUNT "+
 			" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 			" WHERE "+
			" A.RECEIPT_NO=B.RECEIPT_NO "+
			" AND B.REBANK_STATUS IN ('N','R') "+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' "+
			"	AND ( "+
			" UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.RETURN_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  ) "+
			" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL(A.RECEIPT_NO)>0 "+
			")P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			
			
			
			m_help_DIV_TXT_CLIENT_CODE_FACILITY_sql_new=
		" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MANAGER,L.MKT_EXECUTIVE,L.FACILITY_MANAGER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MANAGER,P.MKT_EXECUTIVE,P.FACILITY_MANAGER "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','N') CLIENT_MANAGER,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'M','N') MKT_EXECUTIVE,"+m_schema_name+".FA_GET_CLIENT_MANAGER(CLIENT_CODE,'C','C') FACILITY_MANAGER "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
			"	WHERE "+
			" A.CLIENT_CODE IN (SELECT DISTINCT CLIENT_CODE FROM "+m_schema_name+".FA_CR_PRO_INVOICE) "+
			" AND ACTIVE_STATUS='Y' AND ( CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			m_help_DIV_TXT_FACILITY_sql_new=	
		  " SELECT L.NO,L.FACILITY_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_MGR,L.CREDIT_LIMIT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FACILITY_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_MGR,P.CREDIT_LIMIT "+
			" FROM( "+
			" SELECT FACILITY_NO,CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)  FULL_NAME,"+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O') CLIENT_MGR,CREDIT_LIMIT "+
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 			" WHERE FACILITY_STATUS='Y' AND ( (FACILITY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) OR (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ) "+
			" AND CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
				m_help_DIV_TXT_DEBTOR_SQL_NEW=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+
			"	SELECT A.CLIENT_CODE,A.FULL_NAME "+
			"	FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B  "+
			"	WHERE A.CLIENT_CODE = B.DEBTOR_CODE  "+
			" AND B.DEBTOR_CODE IN (SELECT DISTINCT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL)"+
			" AND B.FACILITY_NO='"+m_vector.elementAt(1)+"' AND "+
			" B.CLIENT_CODE='"+m_vector.elementAt(2)+"' AND "+
			" (A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		//------------------------------------------------------------
		//------------------------------------------------------------
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


