//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_RE_cancelation_help_select  {  
 
  //Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
  LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	 
	public String InvoiceNoSql        = " ";
	public String InvoiceNoSql_Header = " Help "; 
	
	public String m_help_TXT_LOCATION_CODE_sql  ="";
	public String m_help_TXT_LOCATION_CODE_sql_Header ="Location Help";
	
	public String m_help_marketing_officer ="";
	public String m_help_marketing_officer_Header ="Marketing Officer - Help";
	
	public String m_help_TXT_CHQ_NARRATIONS_CODE_sql="";
	public String m_help_TXT_CHQ_NARRATIONS_CODE_sql_Header="Cheque Return Narrations";
	
	public String m_help_TXT_TEAM_ID_sql;
	public String m_help_TXT_TEAM_ID_sql_Header="Team ID";
	
	public String m_help_team_user_id_sql="";
	public String m_help_team_user_id_sql_Header="User Help";

	public String m_help_Repossseion_Help="";
	public String m_help_Repossseion_Help_Header ="Collection - Repossession Help";
	
	public String m_help_edit_Repossseion_Help="";
	public String m_help_edit_Repossseion_Help_Header ="Collection - Repossession Help";
	
	public String m_help_Finance_Repossession_Sql="";
	public String m_help_Finance_Repossession_Sql_Header="Collection - Finance Number Help";
	
	public String m_help_finance_no_post_dated_sql="";
	public String m_help_finance_no_post_dated_sql_Header ="Collection - Finance Number Help";
	
	public String m_help_advertistemnt_help_offer_issue ="";
	public String m_help_advertistemnt_help_offer_issue_Header ="Collection - Advertisement Help";
	
	public String m_help_advertistemnt_help_offer_issue_new ="";
	public String m_help_advertistemnt_help_offer_issue_new_Header ="Collection - Advertisement Help";

	public String m_help_Inventory_no_Offer_Issue ="";
	public String m_help_Inventory_no_Offer_Issue_Header ="";
	
	public String m_help_Inventory_no_Offer_Issue_new ="";
	public String m_help_Inventory_no_Offer_Issue_new_Header ="";
	
	public String NewReceiptSql_refund			   = " ";
	public String NewReceiptSql_refund_Header     = " Collection Help ";
	
	

	public String m_inventory_advertisement_offer_process="";
	public String m_inventory_advertisement_offer_process_Header="Collection - Inventory Help";
	
	public String InvoiceSql        = " ";
	public String InvoiceSql_Header = " Collection Help "; 
	
	public String FinanceSql             = " ";
	public String FinanceSql_Header      = " Collection Help "; 
	
	public String m_help_TXT_ACCOUNT_NO_sql ="";
	public String m_help_TXT_ACCOUNT_NO_sql_Header ="Account No Help";
	
	public String m_help_Collection_process_Account_code        ="";
	public String m_help_Collection_process_Account_code_Header ="Account Code Help";
	
	public String m_help_Collection_process_Deposit_code        ="";
	public String m_help_Collection_process_Deposit_code_Header ="Deposit Number Help";
	
	public String m_help_Collection_Return_realisation_cheque_no="";
	public String m_help_Collection_Return_realisation_cheque_no_Header="Cheque Number Help";
	
	public String m_help_TXT_CLIENT_CODE                       ="" ;
	public String m_help_TXT_CLIENT_CODE_Header                ="Client Help";
	
	public String m_help_TXT_BANK_CODE_sql                     ="";
	public String m_help_TXT_BANK_CODE_sql_Header              ="Bank help";
	
	public String m_help_TXT_BRANCH_CODE_sql                  ="";
	public String m_help_TXT_BRANCH_CODE_sql_Header           ="Branch Help";
	
	public String m_help_TXT_ACCOUNT_CODE_sql                  ="";
	public String m_help_TXT_ACCOUNT_CODE_sql_Header           ="Account Number Help";
	
	public String m_help_TXT_USER_ID_sql                      ="";
	public String m_help_TXT_USER_ID_sql_Header               ="User Help";
	
	public String m_help_marketing_officer_colection          ="";
	public String m_help_marketing_officer_colection_Header   ="Marketing Officer Help";
	
	public String m_help_collection_officer_colection          ="";
	public String m_help_collection_officer_colection_Header   ="Collection Officer Help";
	
	public String m_help_TXT_TEMP_REC_NO_sql                  ="";
	public String m_help_TXT_TEMP_REC_NO_sql_Header           ="Temp Receipt Help";
	
	public String m_help_TXT_CURR_CODE_sql                     ="";
	public String m_help_TXT_CURR_CODE_sql_Header              ="Currency Code Help"; 
	
	public String m_help_TXT_REC_BOOK_NO_sql                  ="";
	public String m_help_TXT_REC_BOOK_NO_sql_Header           ="Receipt Book No Help";
	
	public String m_help_TXT_FINANCE_NO_sql                   ="";
	public String m_help_TXT_FINANCE_NO_sql_Header            ="Finance No Help";
	
	public String m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql      ="";
	public String m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_Header="Finanace Number Help";
	
	public String m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_new      ="";
	public String m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_new_Header="Finanace Number Help";

	public String m_help_TXT_REPOSSESSION_NO_sql              ="";
	public String m_help_TXT_REPOSSESSION_NO_sql_Header       ="Collection - Repossession No Help";
	
	public String m_help_TXT_VEHICLE_NO_sql                   ="";
  public String  m_help_TXT_VEHICLE_NO_sql_Header            ="Collection - Vehicle No Help";
	
	public String m_help_TXT_VEHICLE_NO_sql_edit              ="";
	public String m_help_TXT_VEHICLE_NO_sql_edit_Header       ="Collection -Vehicle No Help"; 
	
	public String m_help_TXT_REPOSSESSION_NO_edit_sql              ="";
	public String m_help_TXT_REPOSSESSION_NO_edit_sql_Header       ="Collection - Repossession No Help";
	
	public String m_help_TXT_REPOSSESSION_NO_inv_no_sql						="";
	public String m_help_TXT_REPOSSESSION_NO_inv_no_sql_Header    ="Collection - Inventory Number Help";
	
	public String m_help_TXT_CITY_CODE_sql                       ="";
	public String m_help_TXT_CITY_CODE_sql_Header                ="collection - City Code Help";
	
	public String m_help_TXT_YARD_CODE_sql                       ="";
	public String m_help_TXT_YARD_CODE_sql_Header                ="Collection - Yard Code Help";
	
	public String m_help_TXT_INVENTORY_NO_sql										="";
	public String m_help_TXT_INVENTORY_NO_sql_Header            ="Collection -Inventory No Help";
	
	public String m_help_TXT_VEHICLE_NO_ADVEST                   ="";
	public String m_help_TXT_VEHICLE_NO_ADVEST_Header							="Collection - Vehilce No Help";
	
	public String m_help_TXT_ADVETST_NO_sql                      ="";
	public String m_help_TXT_ADVETST_NO_sql_Header               ="Collection - Advertistment Number Help";
	
	public String m_help_TXT_ADVETST_NO_sql_new                      ="";
	public String m_help_TXT_ADVETST_NO_sql_new_Header               ="Collection - Advertistment Number Help";

	public String m_help_TXT_ADERTTISMENT_NO_OFFERS								="";
	public String m_help_TXT_ADERTTISMENT_NO_OFFERS_Header				="Collection - Advertistment Number Help";
	
	public String m_help_TXT_INVENTORY_NO_VALUATION_sql						="";
	public String m_help_TXT_INVENTORY_NO_VALUATION_sql_Header		="Collection - Inventoy Number Help";
	
	public String m_help_TXT_VALUATION_NO1_sql                     ="";
	public String m_help_TXT_VALUATION_NO1_sql_Header              ="Collection - Valauation Number Help";
	
	public String m_help_TXT_VALUATION_NO_sql                     ="";
	public String m_help_TXT_VALUATION_NO_sql_Header              ="Collection - Valauation Number Help";
	
	public String m_help_TXT_VALUATION_NO2_sql                     ="";
	public String m_help_TXT_VALUATION_NO2_sql_Header              ="Collection - Valauation Number Help";
	
	
	public String m_help_TXT_ADVETST_NO_VIEW_LETTER_sql           ="";
	public String m_help_TXT_ADVETST_NO_VIEW_LETTER_sql_Header    ="Collection - Advertistment Number Help ";
	
	public String m_help_TXT_FINANCE_NO_LEGAL_ACTIVITIES_sql              ="";
	public String m_help_TXT_FINANCE_NO_LEGAL_ACTIVITIES_sql_Header       ="Collection - Legal Activities Help";  
	
	public String m_help_TXT_LAWYER_CODE_sql                                  ="";
	public String m_help_TXT_LAWYER_CODE_sql_Header                           ="Collection - Lawyer Code Help";
	
	public String m_help_TXT_LEGAL_CODE_ASSIGN_sql                           ="";
	public String m_help_TXT_LEGAL_CODE_ASSIGN_sql_Header											="Collection - Legal Number Help";
	
	public String m_help_TXT_LEGAL_CODE_sql																		="";
	public String m_help_TXT_LEGAL_CODE_sql_Header														="Collcetion - Legal Number Help";
	
	public String m_help_Legal_action_Assign_sql														="";
	public String m_help_Legal_action_Assign_sql_Header											="Collection - Legal Number Help";
	
	public String m_help_TXT_ADERTTISMENT_NO_OFFERS_VAL											="";
	public String m_help_TXT_ADERTTISMENT_NO_OFFERS_VAL_Header							="Collection - Advertistment Number Help";
	
	
	public String m_help_TXT_FINANCE_NO_1_sql                   ="";
	public String m_help_TXT_FINANCE_NO_1_sql_Header            ="Finance No Help";

	public String m_help_TXT_ACCOUNT_NO_1_sql ="";
	public String m_help_TXT_ACCOUNT_NO_1_sql_Header ="Account No Help";

	public String m_help_TXT_SUS_REF_NO_sql_new ="";
	public String m_help_TXT_SUS_REF_NO_sql_new_Header ="Suspend Reference NO";
	
	
	public String m_help_TXT_SUS_REF_NO_sql ="";
	public String m_help_TXT_SUS_REF_NO_sql_Header ="Suspend Reference NO";

	public String m_help_TXT_ACCOUNT_NO_2_sql ="";
	public String m_help_TXT_ACCOUNT_NO_2_sql_Header ="Account No Help";

	public String m_help_TXT_PAYEE_ACC_NO_sql ="";
	public String m_help_TXT_PAYEE_ACC_NO_sql_Header ="Account No Help";
	
	public String m_help_TXT_PAYMENT_NO_sql ="";
	public String m_help_TXT_PAYMENT_NO_sql_Header ="Payment Settlement No Help";
	
	public String m_help_TXT_PAYMENT_NO_sql_other ="";
	public String m_help_TXT_PAYMENT_NO_sql_other_Header ="Payment Settlement No Help";



	public String m_help_TXT_INVENTORY_NO_sql_new										="";
	public String m_help_TXT_INVENTORY_NO_sql_new_Header            ="Collection -Inventory No Help";
	
	public String m_help_TXT_SUB_MODEL_sql="";
	public String m_help_TXT_SUB_MODEL_sql_Header="collection - help";

	public String	m_help_TXT_VALUER_CODE_sql= "";
	public String	m_help_TXT_VALUER_CODE_sql_Header= "	Marketing - Valuer Code Help";

	public String	m_help_TXT_GRP_INV_sql= "";
	public String	m_help_TXT_GRP_INV_sql_Header= "	Collection - Group Code Help";

	public String	m_help_TXT_GRP_ASSG_sql= "";
	public String	m_help_TXT_GRP_ASSG_sql_Header= "	Collection - Group Code Help";

	public String	ClientSql_Client= "";
	public String	ClientSql_Client_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Client_add= "";
	public String	ClientSql_Client_add_Header= "	Collection - Client Code Help";

	public String	ClientSql_Client_brc= "";
	public String	ClientSql_Client_brc_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Client_city= "";
	public String	ClientSql_Client_city_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Client_tel= "";
	public String	ClientSql_Client_tel_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Client_nic= "";
	public String	ClientSql_Client_nic_Header= "	Collection - Client Code Help";

	public String	ClientSql_Client_email= "";
	public String	ClientSql_Client_email_Header= "	Collection - Client Code Help";

	public String	ClientSql_Finance= "";
	public String	ClientSql_Finance_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Reg= "";
	public String	ClientSql_Reg_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_Chassis= "";
	public String	ClientSql_Chassis_Header= "	Collection - Client Code Help";

	//public String InvoiceNoSql             = " ";
	//public String InvoiceNoSql_Header      = " Collection Help "; 
	public String RepossessionNoSql        = " ";
	public String RepossessionNoSql_Header = " Collection Help "; 
	public String SeizerCodeSql            = " ";
	public String SeizerCodeSql_Header     = " Collection Help "; 
	public String FinanceNoSql             = " ";
	public String FinanceNoSql_Header      = " Collection Help "; 
	public String ClientSql                = " ";
	public String ClientSql_Header         = " Collection Help "; 
	
	public String ReceiptSql               = " ";
	public String ReceiptSql_Header        = " Collection Help "; 
	
	// udara
	public String NewReceiptSql			   = " ";
	public String NewReceiptSql_Header     = " Collection Help ";
	
	public String NewFinanceSql			   = " ";
	public String NewFinanceSql_Header     = " Collection Help ";
	
	// end udara
	
	//kanishka
	public String NewReceiptSql_1		   = " ";
	public String NewReceiptSql_1_Header   = " Collection Help ";
	//end kanishka
	
	public String AccountSql               = " ";
	public String AccountSql_Header        = " Collection Help "; 
	
	public String AccountSql_receipt        ="";
	public String AccountSql_receipt_Header ="Collection Help - Account Number ";
	
	public String ReceiptSql1               = " ";
	public String ReceiptSql1_Header        = " Collection Help "; 
  
	public String m_help_TXT_DIVISION_CODE_sql="";
  public String m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_TRAN_CODE_sql= "";
	public String m_help_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
	
	public String m_help_TXT_BRANCH_CODE_sql_new= "";
	public String m_help_TXT_BRANCH_CODE_sql_new_Header= "System Administration - Branch Code";

	
	public String ClientSql1                = " ";
	public String ClientSql1_Header         = " Client Code Help "; 
	
	public String m_help_TXT_FinanceSql_new2                = " ";
	public String m_help_TXT_FinanceSql_new2_Header         = " Finance Help "; 

	public String ClientSql2                = " ";
	public String ClientSql2_Header         = " Client Code Help "; 
	
	public String m_help_client_code_sql                = " ";
	public String m_help_client_code_sql_Header         = " Client Code Help "; 
	
	public String ReceiptSql_group               = " ";
	public String ReceiptSql_group_Header        = " Collection Help "; 

	public String ReceiptSql_group_1               = " ";
	public String ReceiptSql_group_1_Header        = " Collection Help "; 
	
	public String new_client_help               = " ";
	public String new_client_help_Header        = " Client Help";


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
		
	//Added by Mahela on 30-04-2007
	m_help_TXT_DIVISION_CODE_sql=
			
		  " SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
   		" SELECT "+
			" DIVISION_CODE, "+
      " DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			 " WHERE (DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	m_help_TXT_TRAN_CODE_sql=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " TRAN_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
		  " WHERE (TRAN_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";		
	
	/*----------------------------------------------------------------
		Purpose  : select Account No
	
		Used in  : Return & Realization 
	-----------------------------------------------------------------*/			
	
	m_help_TXT_SUB_MODEL_sql = 
	" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE "+
  " FROM   "+
	"(SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE "+
 " FROM( "+
 " SELECT "+
	" DISTINCT SUB_CODE, MODEL_CODE, DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, DEFAULT_VALUE "+
	" FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE " +
  " WHERE SUB_CODE LIKE UPPER('" + m_vector.elementAt(0) + "%') AND MODEL_CODE LIKE UPPER('" + m_vector.elementAt(1) + "%')  AND ACTIVE_STATUS=('" + m_vector.elementAt(2) + "') " +
	" ORDER BY SUB_CODE ASC" +
   "  )P)L  " +
  " WHERE L.NO>=  " + Start_Val + "  AND L.NO<=  " + End_Val + " ";
	
		
		 m_help_TXT_ACCOUNT_NO_sql=	
			 "SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_NAME,L.ACC_SYS_REFNO "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_NAME,P.ACC_SYS_REFNO "+
			 "FROM(  "+
  		 " SELECT "+
 			 " ACC_NO,"+
			 " BRANCH_CODE,"+	
			 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+	
			 " "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME ,"+		
			 " NVL(ACC_SYS_REFNO,'-') ACC_SYS_REFNO "+
       " FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
		   " WHERE (UPPER(ACC_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)) LIKE UPPER('"+m_vector.elementAt(0)+"%') )AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			 "  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
								
										
											           
/*----------------------------------------------------------------
		Purpose  : select Invoice Number
	
		Used in  : Collection Invoice Cancelation
	-----------------------------------------------------------------*/			
  InvoiceNoSql = "SELECT P.NO, INVOICE_NO, FINANCE_NO, TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
												"        TOTAL_AMOUNT,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE "+
												"FROM "+
												"(SELECT ROWNUM NO, INVOICE_NO, FINANCE_NO, VALUE_DATE, "+
												"        TOTAL_AMOUNT,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE "+
												"FROM "+
														"(SELECT A.INVOICE_NO, A.FINANCE_NO, A.VALUE_DATE, "+
														"        A.TOTAL_AMOUNT,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT,  "+
														"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
														" WHERE  (INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS='Y' "+
												" ORDER BY INVOICE_NO DESC,FINANCE_NO )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	

 /*----------------------------------------------------------------
		Purpose  : select Invoice Number
	
		Used in  : Collection Other Invoices
	-----------------------------------------------------------------*/			
  InvoiceSql = "SELECT P.NO, INVOICE_NO, FINANCE_NO, TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
												"    NET_AMOUNT,VAT_AMOUNT,TOTAL_AMOUNT,TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE,BALANCE_TO_BE_RECEIVED,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE,INVOICE_TYPE "+
												"FROM "+
												"(SELECT ROWNUM NO, INVOICE_NO, FINANCE_NO, VALUE_DATE, "+
												"    NET_AMOUNT,VAT_AMOUNT,TOTAL_AMOUNT,DUE_DATE,BALANCE_TO_BE_RECEIVED,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE,INVOICE_TYPE "+
												"FROM "+
														"(SELECT A.INVOICE_NO, A.FINANCE_NO, A.VALUE_DATE, "+
														"        A.NET_AMOUNT,A.VAT_AMOUNT,A.TOTAL_AMOUNT,A.DUE_DATE,A.BALANCE_TO_BE_RECEIVED,A.CLIENT_CODE CLIENT,  "+
														"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE,A.INVOICE_TYPE "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
														" WHERE  (INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS=UPPER('"+m_vector.elementAt(1)+"') AND INVOICE_TYPE NOT IN('INV_GENER') "+
												" ORDER BY INVOICE_NO DESC,FINANCE_NO )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
	/*------------------------------------------------------------------
	
	Purpose     : Select The account code
  Used In     : Collection Process 
	--------------------------------------------------------------------*/
	
	m_help_Collection_process_Account_code=
	  
		" SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE ,L.BRANCH_NAME,L.ACC_SYS_REFNO "+
		" FROM  "+
	  " (SELECT ROWNUM NO,P.ACC_NO,p.BRANCH_CODE,p.BRANCH_NAME,P.ACC_SYS_REFNO "+
		" FROM( "+ 
	  " SELECT "+
		" A.ACC_NO, "+
    " NVL(A.BRANCH_CODE,'-') BRANCH_CODE, "+
		" NVL(B.BRANCH_NAME,'-') BRANCH_NAME, "+
		" NVL(A.ACC_SYS_REFNO,'-') ACC_SYS_REFNO "+
		" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A ,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
		" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND "+
		" (UPPER(A.ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		" UPPER(B.BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		" UPPER(A.BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND  "+
		" A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		

		
			/*------------------------------------------------------------------
	
	Purpose     : Select The Deposit Number
  Used In     : Collection Process 
	--------------------------------------------------------------------*/
		
		m_help_Collection_process_Deposit_code=
		" SELECT L.NO ,L.DIPOSIT_NO,L.DIPOSIT_DATE,L.SETTLE_MODE,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.REFERENCE "+
		" FROM  "+
	  " (SELECT ROWNUM NO,P.DIPOSIT_NO,p.DIPOSIT_DATE,P.SETTLE_MODE,p.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.REFERENCE "+
		" FROM( "+ 
		" SELECT "+
    " DIPOSIT_NO, "+
    " TO_CHAR(DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE, "+
		" "+m_schema_name+".AF_CO_GET_SETTLE_MODE(DIPOSIT_NO) SETTLE_MODE, "+
    " ACC_NO, "+
		" BRANCH_CODE, "+
    " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
	  " REFERENCE "+
		
    " FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
	  " WHERE DIPOSIT_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND  STATUS=('"+m_vector.elementAt(1)+"') "+
		" ORDER BY DIPOSIT_NO DESC " +
   	"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_Collection_Return_realisation_cheque_no=
		
		" SELECT L.NO ,L.FINANCE_NO,L.NAME,L.CLIENT_CODE,L.CHEQUE_NO,L.CHEQUE_DATE,L.RETURN_NO,L.DIPOSIT_NO,L.RECEIPT_NO,L.AMOUNT "+
		" FROM  "+
	  " (SELECT ROWNUM NO,P.FINANCE_NO,P.NAME,p.CLIENT_CODE,P.CHEQUE_NO,P.CHEQUE_DATE,p.RETURN_NO,P.DIPOSIT_NO,P.RECEIPT_NO,P.AMOUNT "+
		" FROM( "+ 
		" SELECT "+
		" DISTINCT NVL(CHEQUE_NO,'-') CHEQUE_NO, "+ 
		" NVL(D.FINANCE_NO,'-') FINANCE_NO, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) NAME, "+
		" B.CLIENT_CODE, "+
		" NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
		" RETURN_NO, "+
		" DIPOSIT_NO, "+ 
		" A.RECEIPT_NO, "+
		" AMOUNT, "+
		" ALLOCATED_AMOUNT, "+
		" BAL_AMOUNT, "+
		" NVL(PAYER_BRANCH_CODE,'-'), "+
		" NVL(PAYER_ACC_NO,'-') "+
		" FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS A, "+
		" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  B ,"+
		" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C, "+
    " "+m_schema_name+".AF_CO_PRO_INVOICE D "+
		" WHERE A.RECEIPT_NO=B.REC_NO(+) "+
    "  AND B.REC_NO=C.RECEIPT_NO(+) "+
    "  AND C.INVOICE_NO=D.INVOICE_NO(+) "+
		" AND STATUS='RET' "+
		" AND ( UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(DIPOSIT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(RETURN_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(D.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(PAYER_BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"      UPPER(PAYER_ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
		"  ORDER BY RETURN_NO DESC)P)L   "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*--------------------------------------------------------------------------------------------------------------
			Purpose :Select Client Code
			Used In :Collection Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
			
		m_help_TXT_CLIENT_CODE =
		" SELECT P.NO, P.CLIENT_CODE CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
		" FROM (SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
		" FROM ( "+
		" SELECT CLIENT_CODE, "+
		" NVL(FIRST_NAME,'-') FIRST_NAME, "+
		" NVL(SURNAME,'-') SURNAME, "+
		" NVL(NIC_NO,'-') NIC_NO, "+
		" NVL(ADDRESS1,'-') ADDRESS1, "+
		" NVL(ADDRESS2,'-') ADDRESS2, "+
		" NVL(TEL_NO,'-') TEL_NO, "+
		" NVL(MOBILE_NO,'-') MOBILE_NO, "+
		" NVL(EMAIL,'-') EMAIL, "+
		" ACTIVE_STATUS, "+
		" TEMP_ACTIVE_STATUS, "+
		" NVL(CITY_CODE,'-') CITY_CODE "+
		" FROM " + m_schema_name + ".AF_CO_MAS_CLIENT "+
		" WHERE  (UPPER(CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
		" UPPER(FULL_NAME)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " +
		" UPPER(ADDRESS1)   LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(CITY_CODE)  LIKE UPPER('" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(MOBILE_NO)  LIKE UPPER('" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(TEL_NO)     LIKE UPPER('" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(EMAIL)      LIKE UPPER('" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(NIC_NO)     LIKE UPPER('" + m_vector.elementAt(0) + "%') OR " + 
		" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('" + m_vector.elementAt(0) + "%')) "+ 
		" AND ACTIVE_STATUS=('" + m_vector.elementAt(1) + "') " + 
		" ORDER BY CLIENT_CODE DESC "+
		" )) P " + 
		" WHERE P.NO>= " + Start_Val + " AND P.NO<= " + End_Val + " ";
		
    //m_help_TXT_CLIENT_CODE = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category,NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, P.ADDRESS1, P.ADDRESS2, P.CITY FROM (SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY,ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY,ACTIVE_STATUS, TEMP_ACTIVE_STATUS " + "\t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO' AND " + "       ( FULL_NAME LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t       BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%')) AND " + "        ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " + " ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + Start_Val + " AND L.NO<= " + s2 + " ";
		
		
		/*--------------------------------------------------------------------------------------------------------------
			Purpose :selecting Bank Code
			Used In :Collection Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
			
			 m_help_TXT_BANK_CODE_sql=
		" SELECT L.NO ,L.BANK_CODE,L.NAME,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " BANK_CODE , "+
      " NAME ,"+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
		  " WHERE (UPPER(BANK_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Branch Code
			used In  :Collcetion Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
			m_help_TXT_BRANCH_CODE_sql=
		  " SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.ADDRESS1, NVL(L.ADDRESS2,'-'), NVL(L.CITY_CODE,'-'),NVL(L.TEL_NO,'-'),NVL(L.FAX_NO,'-'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE"+
			" FROM( "+ 
  		" SELECT "+
      " BRANCH_CODE,"+
			" BRANCH_NAME,"+
			" BANK_CODE,"+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE,"+
			" TEL_NO,"+
			" FAX_NO,"+
			" DAYS_TO_REALISE, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
		  " WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Account code
			used In  :Collcetion Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
			
			 m_help_TXT_ACCOUNT_CODE_sql=	
			 /*"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			 "FROM(  "+
  		 " SELECT "+
 			 " ACC_NO,"+
			 " BRANCH_CODE,"+	
 			 " ACC_SYS_REFNO,"+
 			 " CURR_CODE,+"+
 			 " ACC_CODE,"+
 			 " ACC_DESC"+
       " FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
		   " WHERE UPPER(ACC_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			 "  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
				*/
				
				
				"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.BANK_NAME "+
			  "FROM  "+
			  "(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.BANK_NAME "+
			  "FROM(  "+
				" SELECT "+
     		" A.ACC_NO ACC_NO, "+
  		  " B.BRANCH_CODE BRANCH_CODE, "+
				" B.BRANCH_NAME BRANCH_NAME, "+
  		  " NVL(B.BANK_CODE,'-') BANK_CODE, "+
				" NVL(C.NAME,'-') BANK_NAME "+
		 		" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT A, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH B,"+m_schema_name+".AF_CO_MAS_BANKS C "+
 				" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND B.BANK_CODE=C.BANK_CODE AND A.ACC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
				"  )P)L  "+
	  		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

					
	
				
				/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Collection officer
			used In  :Collcetion Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
				
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
			" WHERE ( UPPER(USER_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(LOCATION_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(USER_TYPE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(EMP_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(DIVISION_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
      //added by nuwan de silva on 14-07-09-------------------------------------------------------------------
			m_help_marketing_officer_colection=
		
		  " SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE "+
			" FROM( "+ 
			"	SELECT "+
			"		USER_ID,NAME,LOCATION_CODE,USER_TYPE, "+
			"		EMP_ID,ACTIVE_STATUS,DIVISION_CODE,DESIGNATION_CODE "+
			"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" 	WHERE ( UPPER(USER_ID) 				LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(NAME) 					LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(LOCATION_CODE) 	LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(USER_TYPE)	 		LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(EMP_ID) 				LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(DIVISION_CODE) 	LIKE 	UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"   AND ACTIVE_STATUS	 =		('"+m_vector.elementAt(3)+"') "+
			"		AND USER_ID        IN( SELECT DISTINCT MK_OFFICER "+
			"													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
			"                         AND    UPPER(B.CLIENT_CODE)         LIKE UPPER('"+m_vector.elementAt(1)+"%')  "+
      "                         AND    UPPER(B.COLLECTION_OFFICER)  LIKE UPPER('"+m_vector.elementAt(2)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			    //added by nuwan de silva on 14-07-09-------------------------------------------------------------------
			m_help_collection_officer_colection=
		
		  " SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE "+
			" FROM( "+ 
			"	SELECT "+
			"		USER_ID,NAME,LOCATION_CODE,USER_TYPE, "+
			"		EMP_ID,ACTIVE_STATUS,DIVISION_CODE,DESIGNATION_CODE "+
			"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" 	WHERE ( UPPER(USER_ID) 				LIKE  UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(NAME) 					LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(LOCATION_CODE) 	LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(USER_TYPE)	 		LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(EMP_ID) 				LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(DIVISION_CODE) 	LIKE 	UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"   AND ACTIVE_STATUS	 =		('"+m_vector.elementAt(3)+"') "+
		/*	"		AND USER_ID        IN  (SELECT DISTINCT COLLECTION_OFFICER "+
			" 													FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" 													WHERE COLLECTION_OFFICER IS NOT NULL "+
			" 													AND APPLICATION_NO IN  (  "+
			" 													SELECT DISTINCT B.APPLICATION_NO "+
			" 													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" 													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
			" 													AND    UPPER(MK_OFFICER) LIKE  UPPER('"+m_vector.elementAt(1)+"%') ) )"+
		*/	
			"		AND USER_ID        IN( SELECT DISTINCT COLLECTION_OFFICER "+
			"													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
			"                         AND    UPPER(B.CLIENT_CODE)         LIKE UPPER('"+m_vector.elementAt(1)+"%')  "+
      "                         AND    UPPER(A.MK_OFFICER)          LIKE UPPER('"+m_vector.elementAt(2)+"%') ) "+

			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
			
			
			
			
			
			
			
			
			//added by nuwan de silva on 20-09-07------------------------------------------------------------
			m_help_marketing_officer=
		
		  " SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE "+
			" FROM( "+ 
			"	SELECT "+
			"		USER_ID,NAME,LOCATION_CODE,USER_TYPE, "+
			"		EMP_ID,ACTIVE_STATUS,DIVISION_CODE,DESIGNATION_CODE "+
			"		FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" 	WHERE ( UPPER(USER_ID) 				LIKE  UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(NAME) 					LIKE 	UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(USER_TYPE)	 		LIKE 	UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(EMP_ID) 				LIKE 	UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" 					UPPER(DIVISION_CODE) 	LIKE 	UPPER('"+m_vector.elementAt(0)+"%')) "+
			" 	AND			UPPER(LOCATION_CODE) 	LIKE 	UPPER('"+m_vector.elementAt(1)+"%')  "+
			"   AND ACTIVE_STATUS	 =		('"+m_vector.elementAt(2)+"') "+
			"		AND USER_ID        IN( SELECT DISTINCT MK_OFFICER "+
			"													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
		  "                         AND    UPPER(A.MK_OFFICER)          LIKE UPPER('"+m_vector.elementAt(0)+"%') ) "+

			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//===========================================================================================================
			
			
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Receipt Number
			used In  :Collcetion Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
			
		m_help_TXT_TEMP_REC_NO_sql=
		" SELECT L.NO ,L.TEMP_REC_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TRN_DATE,L.AMOUNT,L.SETTELMENT_MODE,L.BRANCH_CODE,L.BANK_CODE,L.ACCOUNT_NO,L.CURR_CODE,L.EXCHANGE_RATE,L.TRN_AMOUNT_CURR,L.COLLECTION_OFFICER,L.RECEIPT_NO,L.REC_BOOK_NO,L.CHEQUE_NO,L.BRANCH_NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.TEMP_REC_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TRN_DATE,P.AMOUNT,P.SETTELMENT_MODE,P.BRANCH_CODE,P.BANK_CODE,P.ACCOUNT_NO,P.CURR_CODE,P.EXCHANGE_RATE,P.TRN_AMOUNT_CURR,P.COLLECTION_OFFICER,P.RECEIPT_NO,P.REC_BOOK_NO,P.CHEQUE_NO,P.BRANCH_NAME "+
		" FROM( "+ 
    " SELECT "+
		
    /*" TEMP_REC_NO, "+
    " FINANCE_NO, "+
    " CLIENT_CODE, "+
		" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME, "+
    " TO_CHAR(TRN_DATE,'DD-MM-YYYY') TRN_DATE, "+
    " AMOUNT, "+
    " SETTELMENT_MODE, "+
    " BRANCH_CODE, "+
	  " BANK_CODE, "+
    " ACCOUNT_NO, "+
    " CURR_CODE, "+
    " EXCHANGE_RATE, "+
    " TRN_AMOUNT_CURR, "+
    " COLLECTION_OFFICER, "+
    " RECEIPT_NO, "+
    " REC_BOOK_NO, "+
    " CHEQUE_NO "+
    " FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT "+
		" WHERE TEMP_REC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
		" ORDER  BY TEMP_REC_NO DESC "+
		*/
		" A.TEMP_REC_NO, "+
    " A.FINANCE_NO, "+
  	" A.CLIENT_CODE, "+
		" B.FULL_NAME, "+
    " TO_CHAR(A.TRN_DATE,'DD-MM-YYYY') TRN_DATE, "+
    " A.AMOUNT, "+
    " A.SETTELMENT_MODE, "+
    " A.BANK_CODE, "+
    " A.BRANCH_CODE, "+
    " A.ACCOUNT_NO, "+
    " A.CURR_CODE, "+
    " A.EXCHANGE_RATE, "+
    " A.TRN_AMOUNT_CURR, "+
    " A.COLLECTION_OFFICER, "+
    " A.RECEIPT_NO, "+
    " A.REC_BOOK_NO, "+
    " NVL(A.CHEQUE_NO,'-') CHEQUE_NO, "+
		" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME "+
    " FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A, "+
		"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
    "  ) B  "+
    "  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
    "  AND   "+
    "  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.TEMP_REC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
    "  )  "+
    "  AND A.STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.TEMP_REC_NO DESC  "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Currency Code
			used In  :Collcetion Temp Receipt
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_CURR_CODE_sql=
		" SELECT L.NO ,L.CURR_CODE,L.CURR_SYMBOL,L.REP_CURR,TO_CHAR(L.TRN_DATE,'DD-MM-YYYY') AS TRN_DATE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CURR_CODE,P.CURR_SYMBOL,P.REP_CURR,P.TRN_DATE,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " CURR_CODE, "+
      " CURR_SYMBOL, "+
			" REP_CURR, "+
      " TRN_DATE, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
		  " WHERE CURR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Repossession no
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_REPOSSESSION_NO_sql=


	  " SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO "+
		" FROM( "+ 
		
    " SELECT "+
    " A.REPOSSESSION_NO, "+
    " A.SEIZER_CODE, "+
	  " A.FINANCE_NO, "+
		" B.CLIENT_CODE ,"+
		" B.FULL_NAME "+
		//" NVL(INVENTORY_NO,'') INVENTORY_NO "+
     " FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
		//" WHERE UPPER(REPOSSESSION_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		
		"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V,"+m_schema_name+".AF_RE_PRO_REPOSSESSION Y  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE AND X.FINANCE_NO=Y.FINANCE_NO "+
    "  ) B  "+
    "  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
    "  AND   "+
    "  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.REPOSSESSION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.SEIZER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
    "  )  "+
    "  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.REPOSSESSION_NO DESC  "+
			
		" )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Repossession no
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_REPOSSESSION_NO_edit_sql=


	/*  " SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO,L.INVENTORY_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO,P.INVENTORY_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.REPOSSESSION_NO, "+
    " A.SEIZER_CODE,  "+
		" B.FINANCE_NO,  "+
		" A.INVENTORY_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
		" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		" SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.REPOSSESSION_NO, "+
    " A.SEIZER_CODE,  "+
		" B.FINANCE_NO "+
		
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
		" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		" ORDER BY A.REPOSSESSION_NO DESC  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		
		
		m_help_edit_Repossseion_Help =" SELECT L.NO ,L.REPOSSESSION_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
													" FROM  "+
													" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
													" FROM( "+ 
																	
													"						 SELECT  "+
													"             D.REPOSSESSION_NO, "+
													"             A.FINANCE_NO,  "+
													"			        A.CLIENT_CODE,  "+
													"             B.FULL_NAME FULL_NAME,   "+
													"             D.VEHICLE_NO, "+
													"             D.INVENTORY_NO, "+
													"             NVL(B.TEL_NO,'-') TEL_NO,   "+
													"    	        NVL(B.NIC_NO,'-') NIC_NO   "+
													             
													"			      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
													"            (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
													"             FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+
													"            WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
													"            ) B  ,"+m_schema_name+".AF_RE_PRO_REPOSSESSION C,"+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY D "+
																	
													"             WHERE A.FINANCE_NO=B.FINANCE_NO   AND  A.FINANCE_NO=C.FINANCE_NO AND "+
													"              C.REPOSSESSION_NO=D.REPOSSESSION_NO "+
													"            AND    "+
													"		        (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
													"		        UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
													"		        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
													"		        B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
													"		        C.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR "+
													"		        C.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
													"		        D.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
													"		        C.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
															                
													"		        )  "+
													
													"        AND D.ACTIVE_STATUS='ENT' "+
													"        ORDER BY D.REPOSSESSION_NO DESC,C.FINANCE_NO  "+		
													"        )P)L  "+
													"        WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
		
		


	/*  " SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO,L.INVENTORY_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO,P.INVENTORY_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.REPOSSESSION_NO, "+
    " A.SEIZER_CODE,  "+
		" B.FINANCE_NO,  "+
		" A.INVENTORY_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
		" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
	/*		m_help_TXT_INVENTORY_NO_VALUATION_sql=
		" SELECT L.NO ,L.INVENTORY_NO,L.SEIZER_CODE "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.SEIZER_CODE "+
		" FROM( "+ 
    " SELECT "+
		" DISTINCT A.INVENTORY_NO,  "+
    " A.SEIZER_CODE  "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A"+
		" WHERE UPPER(A.INVENTORY_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		
		
		m_help_TXT_INVENTORY_NO_VALUATION_sql=

	  " SELECT L.NO ,L.INVENTORY_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
		" "+m_schema_name+".AF_CO_PRO_APP_VALUATION D,"+
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
	  "  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
		"  ) "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
	
	m_help_TXT_REPOSSESSION_NO_inv_no_sql=
		
			" SELECT L.NO ,L.INVENTORY_NO,L.SEIZER_CODE,L.FINANCE_NO,L.SEIZER_NAME,L.SEIZER_FEE "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.SEIZER_CODE,P.FINANCE_NO,P.SEIZER_NAME,P.SEIZER_FEE "+
		" FROM( "+ 
    " SELECT "+
		" DISTINCT A.INVENTORY_NO,  "+
    " A.SEIZER_CODE,  "+
		" B.FINANCE_NO,  "+
		" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+
		" "+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE) SEIZER_FEE "+
		" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
		//"  AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(1)+"')  AND UPPER(A.INVENTORY_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
    "  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.SEIZER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')   "+
    "  ) AND  "+
		"  A.REPOSSESSION_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
    "  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
		
		
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting inventory no
			used In  :Advertistment generation process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_INVENTORY_NO_sql=


	  " SELECT L.NO ,L.INVENTORY_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
				
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
		"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
		"  ) "+
		"  AND A.COMPLETED_OFFER_NO IS NULL "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_Inventory_no_Offer_Issue=
		
		" SELECT L.NO ,L.INVENTORY_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		//" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS B, "+
				
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.INVENTORY_NO=B.INVENTORY_NO  "+
		"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
  //  "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
		"  ) "+
		"  AND A.COMPLETED_OFFER_NO IS NULL "+
	//	"  AND B.ADVER_NO='-' "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		m_inventory_advertisement_offer_process=


	  " SELECT L.NO ,L.INVENTORY_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
				
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
		"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
    "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
		"  ) AND "+
		"  A.INVENTORY_NO NOT IN(SELECT INVENTORY_NO FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL ) "+
		"  AND A.COMPLETED_OFFER_NO IS NULL "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
				
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Adveristment Number
			used In  :Advertistment Offers process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_ADERTTISMENT_NO_OFFERS=

		
		" SELECT L.NO ,L.ADVER_NO,L.INVENTORY_NO,L.VEHICLE_NO,L.CLIENT_CODE,L.FULL_NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVER_NO,P.INVENTORY_NO,P.VEHICLE_NO,P.CLIENT_CODE,P.FULL_NAME "+
		" FROM( "+ 
   	" SELECT   "+
    "  DISTINCT A.ADVER_NO , "+
    "  B.INVENTORY_NO, "+
    "  B.VEHICLE_NO, "+
    "  B.CLIENT_CODE, "+
    "  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME "+

    "  FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS A,"+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY B "+
    " WHERE A.INVENTORY_NO=B.INVENTORY_NO AND "+
		" (UPPER(A.ADVER_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		" UPPER(B.INVENTORY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
		" UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
		" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
		" UPPER(B.VEHICLE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
		" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		" ORDER BY A.ADVER_NO DESC  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_ADERTTISMENT_NO_OFFERS_VAL=

		
		" SELECT L.NO ,L.ADVETIST_NO,L.INVENTORY_NO,L.VEHICLE_NO,L.OUTSTANDING_VALUE,L.OUTSTANDING_INVOICE_VAL,L.TOTAL_OUTSTANDING_VAL,L.RELEASE_TYPE,L.OFFER_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVETIST_NO,P.INVENTORY_NO,P.VEHICLE_NO,P.OUTSTANDING_VALUE,P.OUTSTANDING_INVOICE_VAL,P.TOTAL_OUTSTANDING_VAL,P.RELEASE_TYPE,P.OFFER_NO "+
		" FROM( "+ 
		" SELECT "+
 		" ADVETIST_NO, "+
 		" INVENTORY_NO, "+
 		" VEHICLE_NO, "+
 		" NVL(OUTSTANDING_VALUE,0) OUTSTANDING_VALUE, "+
 		" NVL(OUTSTANDING_INVOICE_VAL,0) OUTSTANDING_INVOICE_VAL, "+
 		" NVL(TOTAL_OUTSTANDING_VAL,0) TOTAL_OUTSTANDING_VAL, "+
 		" RELEASE_TYPE, "+
 		" OFFER_NO "+
 		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
 		" WHERE ADVETIST_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		" ORDER BY ADVETIST_NO DESC )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

  

		
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Vehicle No
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_VEHICLE_NO_sql=
				
			
		" SELECT L.NO ,L.REG_NO VEHILCE_NO,L.ENGINE_NO,L.CHASSIS_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO "+
		" FROM( "+ 
		" SELECT  "+
    "  REG_NO,  "+
    "  ENGINE_NO,  "+
    "  CHASSIS_NO  "+
    "  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
    "  WHERE  APPLICATION_NO IN (  "+
    "  SELECT  "+
    "  APPLICATION_NO  "+
    "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
	  "  WHERE FINANCE_NO IN ( "+
    "  SELECT FINANCE_NO "+
    "  FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
    "  WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"')))    "+
    "  AND PURCHASE_ORDER_NO IS NOT NULL "+
    "  AND UPPER(VEHICLE_NO) NOT IN ( "+
    "  SELECT UPPER(VEHICLE_NO) "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+ 
    "  WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"')) "+
    "  AND( UPPER(REG_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')  OR "+
		"       UPPER(REG_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')  OR "+
		"       UPPER(REG_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')) "+
		
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Vehicle No
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_VEHICLE_NO_sql_edit=
		
		
		" SELECT L.NO ,L.REG_NO VEHICLE_NO,L.ENGINE_NO,L.CHASSIS_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO "+
		" FROM( "+ 
		" SELECT "+
    " DISTINCT REG_NO, "+
    " ENGINE_NO, "+
    " CHASSIS_NO "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE  "+
    " VEHICLE_NO IN ( "+
    " SELECT "+
    " VEHICLE_NO "+
    " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
    " WHERE UPPER(REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"') AND  ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')) "+
    " AND UPPER(REG_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+

		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Vehicle No
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_VEHICLE_NO_ADVEST =
				
		" SELECT L.NO ,L.VEHICLE_NO,L.CHASSIS_NO,L.ENGINE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.VEHICLE_NO,P.CHASSIS_NO,P.ENGINE_NO "+
		" FROM( "+ 
			" SELECT  "+
		  " DISTINCT A.VEHICLE_NO , "+
      " C.CHASSIS_NO, "+
      " C.ENGINE_NO "+
      " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
      " (SELECT "+
      "    Y.FINANCE_NO,X.ENGINE_NO,X.CHASSIS_NO,X.REG_NO "+
      "    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS X,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS Y "+
      "    WHERE X.APPLICATION_NO=Y.APPLICATION_NO) C "+
      "    WHERE A.INVENTORY_NO=B.INVENTORY_NO AND "+
      "    C.FINANCE_NO=B.FINANCE_NO AND       "+
      "    A.INVENTORY_NO=UPPER('"+m_vector.elementAt(0)+"') AND "+
      "    A.KEY=UPPER('"+m_vector.elementAt(3)+"') AND INSURANCE=UPPER('"+m_vector.elementAt(3)+"') AND VEHICLE_ID_CARD=UPPER('"+m_vector.elementAt(3)+"') AND "+
			"    (UPPER(A.VEHICLE_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')  OR"+
			"    UPPER(C.CHASSIS_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')  OR"+
			"    UPPER(C.ENGINE_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')) AND "+
      "    A.ACTIVE_STATUS=UPPER('"+m_vector.elementAt(2)+"') "+
      " ORDER BY A.VEHICLE_NO DESC )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";   
          
		
		
		
		
			/*--------------------------------------------------------------------------------------------------------------
			Purpose  :Selecting Advertisting Number
			used In  :Vehicle Inventoy Sub Process
			----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_ADVETST_NO_sql =
				
		" SELECT L.NO ,L.ADVER_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVENTORY_NO,L.VEHICLE_NO,L.ADVER_DATE,L.AMOUNT,L.VAT_AMOUNT,L.TOTAL_AMOUNT,L.ADD_VALUE_PAID_TO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVER_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVENTORY_NO,P.VEHICLE_NO,P.ADVER_DATE,P.AMOUNT,P.VAT_AMOUNT,P.TOTAL_AMOUNT,P.ADD_VALUE_PAID_TO "+
		" FROM( "+ 
		" SELECT "+
		 " D.ADVER_NO, "+
	   " B.FINANCE_NO,   "+
     " C.CLIENT_CODE, "+
     " C.FULL_NAME, "+
     " D.INVENTORY_NO, "+
     " D.VEHICLE_NO, "+
     " TO_CHAR(D.ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
     " D.AMOUNT, "+
     " D.VAT_AMOUNT, "+
     " D.TOTAL_AMOUNT,NVL(D.ADD_VALUE_PAID_TO,'-')  ADD_VALUE_PAID_TO "+
	   " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D , "+
		 " (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
     "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+ 
     "  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+
     "  ) C   "+
		 "  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  AND D.INVENTORY_NO=A.INVENTORY_NO "+
     "  AND A.INVENTORY_NO=C.INVENTORY_NO   "+
     "  AND    "+
     "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR "+
     "  D.ADVER_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')    "+
     "  )  "+
     "  AND D.STATUS=UPPER('"+m_vector.elementAt(1)+"')  "+ 
     "  ORDER BY D.ADVER_NO DESC   "+
		 "   )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_advertistemnt_help_offer_issue =	
			
			" SELECT L.NO ,L.ADVER_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVENTORY_NO,L.VEHICLE_NO,L.ADVER_DATE,L.AMOUNT,L.VAT_AMOUNT,L.TOTAL_AMOUNT "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVER_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVENTORY_NO,P.VEHICLE_NO,P.ADVER_DATE,P.AMOUNT,P.VAT_AMOUNT,P.TOTAL_AMOUNT "+
		" FROM( "+ 
		" SELECT "+
		 " D.ADVER_NO, "+
	   " B.FINANCE_NO,   "+
     " C.CLIENT_CODE, "+
     " C.FULL_NAME, "+
     " A.INVENTORY_NO, "+
     " A.VEHICLE_NO, "+
     " TO_CHAR(D.ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
     " D.AMOUNT, "+
     " D.VAT_AMOUNT, "+
     " D.TOTAL_AMOUNT "+
	   " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D, "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS E , "+
		 " (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
     "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+ 
     "  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+
     "  ) C   "+
		 "  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  AND D.INVENTORY_NO=A.INVENTORY_NO AND E.INVENTORY_NO=A.INVENTORY_NO "+
     "  AND A.INVENTORY_NO=C.INVENTORY_NO   "+
     "  AND    "+
     "  (UPPER(C.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  A.INVENTORY_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR   "+
     "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR "+
     "  D.ADVER_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')    "+
     "  )  "+
     "  AND D.STATUS=UPPER('"+m_vector.elementAt(1)+"')  "+ 
     "  ORDER BY D.ADVER_NO DESC   "+
		 "   )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
		
		
		
		
		/*m_help_TXT_ADVETST_NO_VIEW_LETTER_sql =
				
		" SELECT L.NO ,L.ADVETIST_NO,L.INVENTORY_NO,L.VEHICLE_NO,L.OFFER_NO,L.OFFER_FULL_NAME,L.OFFER_ADDRESS,L.OFFER_TEL_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVETIST_NO,P.INVENTORY_NO,P.VEHICLE_NO,P.OFFER_NO,P.OFFER_FULL_NAME,P.OFFER_ADDRESS,P.OFFER_TEL_NO "+
		" FROM( "+ 
		" SELECT "+
 		" DISTINCT ADVETIST_NO, "+
 		" INVENTORY_NO, "+
 		" VEHICLE_NO, "+
 		" OFFER_NO, "+
 		" OFFER_FULL_NAME, "+
 		" OFFER_ADDRESS, "+
 		" OFFER_TEL_NO "+
 		" FROM "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES "+
 		" WHERE ADVETIST_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND RELEASE_TYPE=UPPER('"+m_vector.elementAt(1)+"') AND ACTIVE_STATUS=UPPER('"+m_vector.elementAt(2)+"') "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		
		m_help_TXT_ADVETST_NO_VIEW_LETTER_sql =
		
		" SELECT L.NO ,L.ADVETIST_NO,L.INVENTORY_NO,L.VEHICLE_NO,L.OFFER_NO,L.CLIENT_CODE,L.FULL_NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVETIST_NO,P.INVENTORY_NO,P.VEHICLE_NO,P.OFFER_NO,P.CLIENT_CODE,P.FULL_NAME "+
		" FROM( "+ 
	
							" SELECT DISTINCT "+
							" B.ADVETIST_NO, "+
							" B.INVENTORY_NO, "+
							" B.VEHICLE_NO, "+
							" C.CLIENT_CODE, "+
							" C.FULL_NAME, "+
							" E.FINANCE_NO, "+  
							" B.OFFER_NO, "+
							" B.OFFER_FULL_NAME, "+
							" B.OFFER_ADDRESS, "+
							" B.OFFER_TEL_NO  "+
							" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A, "+
							" "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES B, "+
							" "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D ,  "+
							" "+m_schema_name+".AF_RE_PRO_REPOSSESSION E, "+
							" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
							" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V     "+
							" WHERE V.CLIENT_CODE=X.CLIENT_CODE    "+
							" ) C    "+
							
							" WHERE E.REPOSSESSION_NO=A.REPOSSESSION_NO AND "+
							" A.INVENTORY_NO=B.INVENTORY_NO AND "+
							" C.INVENTORY_NO=B.INVENTORY_NO   "+
							" AND     "+
							" (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR    "+
							" UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+   
							" A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR    "+
							" E.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR  "+
							" B.ADVETIST_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    OR "+
							" B.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    "+
							" )  "+
							" AND B.ACTIVE_STATUS=UPPER('Y')   "+
							" ORDER BY ADVETIST_NO DESC "+
							"  )P)L  "+
             	" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		
		
					
				
			m_help_TXT_REC_BOOK_NO_sql=
	    
			" SELECT L.NO ,L.RECEIPT_BOOK_NO,L.USER_NAME,L.BOOK_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIPT_BOOK_NO,P.USER_NAME,P.BOOK_STATUS "+
			" FROM( "+ 
    	" SELECT "+
      " RECEIPT_BOOK_NO, "+
			" USER_NAME, "+
      " BOOK_STATUS "+
      " FROM "+m_schema_name+".AF_RE_PRO_COLLECTION_RECEIPT "+
			" WHERE USER_NAME LIKE('"+m_vector.elementAt(1)+"%') AND  RECEIPT_BOOK_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			m_help_TXT_FINANCE_NO_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
    	" B.TEL_NO TEL_NO,  "+
    	" B.NIC_NO NIC_NO  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
      "(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
      "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
      "  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
      "  ) B  "+
      "  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
      "  AND   "+
      "  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
      "  )  "+
      "  AND A.APPLICATION_STATUS IN('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')  "+
      "  ORDER BY A.APPLICATION_NO DESC  "+
				
		
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			m_help_finance_no_post_dated_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT C.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
    	" B.TEL_NO TEL_NO,  "+
    	" B.NIC_NO NIC_NO  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_RE_PRO_POD_CHEQUES C ,"+
      "(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
      "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
      "  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
      "  ) B  "+
      "  WHERE A.FINANCE_NO=B.FINANCE_NO AND A.FINANCE_NO=C.FINANCE_NO   "+
      "  AND   "+
      "  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
      "  )  "+
      "  AND A.APPLICATION_STATUS IN('"+m_vector.elementAt(1)+"')  "+
      "  ORDER BY C.FINANCE_NO DESC  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
	
			
			
			/*--------------------------------------------------------------------------------------------
					Used In    :Collection Legal Activities
					Purpose    :Get The Finance Number
					Created By :Nuwan De Silva(08/12/06)
			---------------------------------------------------------------------------------------------*/
			m_help_TXT_FINANCE_NO_LEGAL_ACTIVITIES_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.APPLICATION_STATUS,L.TEL_NO,L.NIC_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.APPLICATION_STATUS,P.TEL_NO,P.NIC_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" A.APPLICATION_STATUS, "+
    	" B.TEL_NO TEL_NO,  "+
    	" B.NIC_NO NIC_NO  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
      "(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
      "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
      "  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
      "  ) B  "+
      "  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
      "  AND   "+
      "  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
      "  )  "+
      "  AND A.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')  "+
      "  ORDER BY A.APPLICATION_NO DESC  "+
				
		
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

			
			
				/*--------------------------------------------------------------------------------------------
					Used In    :Collection Legal Activities
					Purpose    :Get The Lawyer Code
					Created By :Nuwan De Silva(08/12/06)
			  ---------------------------------------------------------------------------------------------*/
			
			m_help_TXT_LAWYER_CODE_sql=
       " SELECT L.NO ,L.LAWYER_CODE,L.FIRST_NAME,L.LAST_NAME,L.NAME_WITH_INITIALS,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2 ,L.CITY_CODE,L.TEL_NO,L.OFFICE_TEL_NO,L.MOBILE_NO,L.FAX_NO,L.OFFICE_FAX_NO,L.FEE_PER_CASE,L.MONTHLY_FEE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LAWYER_CODE,P.FIRST_NAME,P.LAST_NAME,P.NAME_WITH_INITIALS,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.OFFICE_TEL_NO,P.MOBILE_NO,P.FAX_NO,P.OFFICE_FAX_NO,P.FEE_PER_CASE,P.MONTHLY_FEE"+
			" FROM( "+ 
   	  " SELECT "+
      " LAWYER_CODE, "+
      " FIRST_NAME, "+
      " LAST_NAME, "+
      " NAME_WITH_INITIALS, "+
      " ADDRESS1, "+
      " ADDRESS2, "+
      " CITY_CODE, "+
      " TEL_NO, "+
      " OFFICE_TEL_NO, "+
			" MOBILE_NO, "+
      " FAX_NO, "+
      " OFFICE_FAX_NO, "+
			" FEE_PER_CASE, "+
      " MONTHLY_FEE "+
      
      " FROM "+m_schema_name+".AF_CO_MAS_LAWYER "+
			" WHERE (UPPER(LAWYER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR"+
			"        UPPER(NAME_WITH_INITIALS) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			"        UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   OR  "+
			"        UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND "+
			"        ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY LAWYER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*--------------------------------------------------------------------------------------------
					Used In    :Collection Legal Activities
					Purpose    :Get The Legal data
					Created By :Nuwan De Silva(08/12/06)
			  ---------------------------------------------------------------------------------------------*/
			
				
			m_help_TXT_LEGAL_CODE_ASSIGN_sql=
			
		 " SELECT L.NO ,L.LEGAL_NO,L.FINANCE_NO,L.CLIENT_CODE,L.CLIENT_NAME,L.LAWYER_CODE,L.NAME_WITH_INITIALS,L.LEGAL_TYPE,L.LEGAL_POSITION,L.REMARKS,L.COURT_DATE,L.AMOUNT_DUE "+
		 " FROM  "+
		 " (SELECT ROWNUM NO,P.LEGAL_NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.LAWYER_CODE,P.NAME_WITH_INITIALS,P.LEGAL_TYPE,P.LEGAL_POSITION,P.REMARKS,P.COURT_DATE,P.AMOUNT_DUE "+
		 " FROM( "+ 
     " SELECT "+
     " A.LEGAL_NO, "+
     " A.FINANCE_NO, "+
     " A.CLIENT_CODE, "+
		 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
     " A.LAWYER_CODE, "+
		 " B.NAME_WITH_INITIALS, "+	
     " A.LEGAL_TYPE, "+
     " A.LEGAL_POSITION, "+
		 " A.REMARKS, "+
     " TO_CHAR(A.COURT_DATE,'DD-MM-YYYY') COURT_DATE, "+
   	 " A.AMOUNT_DUE "+
   	 " FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
		 " WHERE A.LAWYER_CODE=B.LAWYER_CODE AND "+
		 " (UPPER(A.LEGAL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 " UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 " UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 " UPPER(B.NAME_WITH_INITIALS) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 " UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 " UPPER(A.LAWYER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
		 " A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ 
		 "ORDER BY  A.LEGAL_NO DESC"+
			"  )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
 		 /*--------------------------------------------------------------------------------------------
					Used In    :Collection Legal Activities
					Purpose    :Get The Finance Number
					Created By :Nuwan De Silva(08/12/06)
			  ---------------------------------------------------------------------------------------------*/
			
			
			
			
			m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.CLIENT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME "+
			" FROM( "+ 
      " SELECT "+
      " NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
      " NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
      " NVL(B.FULL_NAME,'-') CLIENT_NAME "+		
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
	  	" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_vector.elementAt(1)+"') AND A.COLLECTION_OFFICER IS NOT NULL  "+
			" AND (UPPER(A.FINANCE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') ) ORDER BY FINANCE_NO DESC   "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			m_help_TXT_LEGAL_CODE_sql=
			
		 " SELECT L.NO ,L.LEGAL_NO,L.FINANCE_NO,L.CLIENT_CODE,L.CLIENT_NAME,L.COURT_DATE "+
		 " FROM  "+
		 " (SELECT ROWNUM NO,P.LEGAL_NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.COURT_DATE "+
		 " FROM( "+ 
     " SELECT "+
     " A.LEGAL_NO, "+
     " A.FINANCE_NO, "+
     " A.CLIENT_CODE, "+
		 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME, "+ 
     " TO_CHAR(A.COURT_NEXT_DATE,'DD-MM-YYYY') COURT_DATE "+
   	 " FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIVITIES A ,"+m_schema_name+".AF_CO_MAS_LAWYER B "+
		 " WHERE A.LAWYER_CODE=B.LAWYER_CODE AND "+
		 "       (UPPER(A.LEGAL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 "        UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 "        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 "        UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 "        UPPER(B.NAME_WITH_INITIALS) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		 "        UPPER(A.LAWYER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
		 "        A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ 
			
		 " ORDER BY A.LEGAL_NO DESC "+
		 "  )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			m_help_Legal_action_Assign_sql=
		 " SELECT L.NO ,L.LEGAL_NO,L.LEGAL_DATE,L.DAILY_DECISION,L.NEXT_COURT_DATE,L.NEXT_COURT_REQ "+
		 " FROM  "+
		 " (SELECT ROWNUM NO,P.LEGAL_NO,P.LEGAL_DATE,P.DAILY_DECISION,P.NEXT_COURT_DATE,P.NEXT_COURT_REQ "+
		 " FROM( "+ 
 		 " SELECT "+
     " LEGAL_NO, "+
     " TO_CHAR(LEGAL_DATE,'DD-MM-YYYY') LEGAL_DATE, "+
     " DAILY_DECISION, "+
     " TO_CHAR(NEXT_COURT_DATE,'DD-MM-YYYY') NEXT_COURT_DATE, "+
     " NEXT_COURT_REQ "+
     " FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIONS "+
		 " WHERE UPPER(LEGAL_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ 
		 " ORDER BY LEGAL_NO DESC "+
		 "  )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


			
			
			
															
/*----------------------------------------------------------------
		Purpose  : select City Code
	
		Used in  : collection -Assign Lease (nuwan De Silva)
	-----------------------------------------------------------------*/			

			
			
			m_help_TXT_CITY_CODE_sql=
		" SELECT L.NO ,L.CITY_CODE,L.CITY_DESC,L.DISTRICT_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC,P.DISTRICT_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " CITY_CODE, "+
      " CITY_DESC, "+
			" DISTRICT_CODE, "+
      " DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_CITY "+
		  " WHERE (CITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(CITY_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			/*----------------------------------------------------------------
		Purpose  : Select Yard Code
	
		Used in  : Collection Vehicle Inventory (nuwan De Silva)//24-NOV-06
	-----------------------------------------------------------------*/			

			
			
			m_help_TXT_YARD_CODE_sql=
			
			" SELECT L.NO ,L.YARD_CODE,L.NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,"+
			" L.FAX_NO,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.YARD_CODE,P.NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,"+
			" P.FAX_NO,P.DEFAULT_VALUE "+
			" FROM( "+ 
      " SELECT "+
      " YARD_CODE ,"+
      " NAME , "+			
			" ADDRESS1 ,"+
			" ADDRESS2 ,"+
			" CITY_CODE , "+
			" TEL_NO ,"+
			" FAX_NO  ,"+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_YARD "+
			" WHERE (YARD_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY YARD_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";			

			
			
			
	

	
										
/*----------------------------------------------------------------
		Purpose  : select Receip Number
	
		Used in  : Collection Settlement Receipt
	-----------------------------------------------------------------*/			
  ReceiptSql1 = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE,TENDER_MOUNT,RETURN_AMOUNT "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE ,TENDER_MOUNT,RETURN_AMOUNT"+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,A.CHEQUE_NO,A.EFF_VALDATE,A.CLIENT_CODE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,  "+
														" NVL(A.TENDER_AMOUNT,0) TENDER_MOUNT,NVL(A.RETURN_AMOUNT,0) RETURN_AMOUNT"+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (UPPER(REC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(B.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(B.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"        UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
														"        AND  A.STATUS='E' "+
												" ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";							
	/*----------------------------------------------------------------
		Purpose  : select Receip Number
	
		Used in  : Collection Settlement Receipt
	-----------------------------------------------------------------*/			
  ReceiptSql = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CHEQUE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by Prabash on 15-02-2012
														"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
													//	" /*       AND A.STATUS='REC'*/ "+
													 "        AND A.STATUS NOT IN ('CAD','RET','C')   "+
												" /*ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME*/ )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";

	
	
	// Added below by Udara on 18-02-2012
	
	
	NewReceiptSql = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CHEQUE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+ //added by Prabash on 15-02-2012		
													//	" /*       AND A.STATUS='REC'*/ "+
													 "        AND A.STATUS NOT IN ('CAD','RET','C')   "+
														"        AND A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
												" /*ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME*/ )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	NewReceiptSql_1 = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CHEQUE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+ //added by Prabash on 15-02-2012		
													//	" /*       AND A.STATUS='REC'*/ "+
													//  "        AND A.STATUS NOT IN ('CAD','RET','C','B')   "+ //commnted by lakshitha dilshan 2026/04/22 reason -commn till
														"        AND A.STATUS NOT IN ('CAD','RET','C')   "+ //added by lakshitha dilshan 2026/04/22 reason -commn till
														"        AND A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
												" /*ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME*/ )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
	new_client_help = " SELECT ROWNUM \"No\", CLIENT_CODE \"Client Code\", "+m_schema_name+".AF_GET_CLIENT_FULL_NAME(CLIENT_CODE) FROM( "+
						" SELECT DISTINCT CLIENT_CODE FROM ( "+
							" SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
								" FROM "+
									" (SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
										" FROM "+
											" (SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE "+ 
												" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
												" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
												" (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												" UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												" UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												" UPPER(CHEQUE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ 
												" A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
												" AND A.STATUS NOT IN ('CAD','RET','C') "+  
											" )) P "+
 					" 	)  Q "+
					" ) ";
	
	
	NewFinanceSql =  " SELECT ROWNUM, FINANCE_NO, CLIENT_CODE FROM ( "+
						" SELECT DISTINCT FINANCE_NO FINANCE_NO, CLIENT_CODE CLIENT_CODE FROM ( "+
							" SELECT P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE CLIENT_CODE "+
							            " FROM "+
							            " (SELECT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
							            " FROM "+
							            " (SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
							                    " NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 , "+
							                    " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE  "+ 
							            	 	" FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
							            		" WHERE "+
							            		     " A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
							            		     " B.APPLICATION_NO=C.APPLICATION_NO(+) "+
												     " AND UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
												     " AND UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
													 " AND B.APPLICATION_STATUS NOT IN ('TERMI','TERMINATED','NORM_TERMI')   "+ // added by udara 14-09-2017
							             " ORDER BY FULL_NAME )) P 	"+	
							            " WHERE P.FINANCE_NO <> '-'  "+      
							    " ) "+
							" ) "+
							" ";
	
	// End by Udara on 18-02-2012
	
	
	/*----------------------------------------------------------------
		Purpose  : select Account Number
	
		Used in  : Collection Settlement 
	-----------------------------------------------------------------*/			
	AccountSql = " SELECT L.NO ,L.ACCOUNT_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.REFERENCE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.REFERENCE "+
			" FROM( "+ 
 			"	SELECT "+
    	" ACCOUNT_NO, "+
      " BRANCH_CODE, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
    	" REFERENCE "+
 			"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
			" WHERE ACCOUNT_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')   AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
	AccountSql_receipt = " SELECT L.NO ,L.ACCOUNT_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.REFERENCE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.REFERENCE "+
			" FROM( "+ 
 			"	SELECT "+
    	" ACCOUNT_NO, "+
      " BRANCH_CODE, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
    	" REFERENCE "+
 			"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
			" WHERE (UPPER(ACCOUNT_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE))  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  "+
			" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(1)+"%')    AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

	
	
											           
/*----------------------------------------------------------------
		Purpose  : select Invoice Number
	
		Used in  : Collection Invoice Cancelation
	-----------------------------------------------------------------*/			
  InvoiceNoSql = "SELECT P.NO, INVOICE_NO, FINANCE_NO, TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VAL_DATE, "+
												"        TOTAL_AMOUNT,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE "+
												"FROM "+
												"(SELECT ROWNUM NO, INVOICE_NO, FINANCE_NO, VALUE_DATE, "+
												"        TOTAL_AMOUNT,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE "+
												"FROM "+
														"(SELECT A.INVOICE_NO, A.FINANCE_NO, A.VALUE_DATE, "+
														"        A.TOTAL_AMOUNT,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT,  "+
														"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE "+
														" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A "+
														" WHERE  (INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS='Y' "+
												" ORDER BY INVOICE_NO DESC,FINANCE_NO )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
	/*----------------------------------------------------------------
		Purpose  : select Repossession Number
	
		Used in  : Collection Repossession Screen
	-----------------------------------------------------------------*/			
  RepossessionNoSql =   " SELECT P.NO, REPOSSESSION_NO, FINANCE_NO, SEIZER_CODE,SEIZER_NAME "+
												" FROM "+
												" (SELECT ROWNUM NO, REPOSSESSION_NO, FINANCE_NO, SEIZER_CODE,SEIZER_NAME "+
												" FROM "+
												" (SELECT A.REPOSSESSION_NO, A.FINANCE_NO, A.SEIZER_CODE, "+
												"        "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME "+
												" FROM   "+m_schema_name+".AF_RE_PRO_REPOSSESSION A "+
												" WHERE  (REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
												"        ACTIVE_STATUS='Y' "+
										    " ORDER BY REPOSSESSION_NO DESC,FINANCE_NO )) P "+		
										    " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
												
												
												
//Modified by Mahela on 23-05-2007  (Seizer Name)		
//modified by :delanjali on (2007-06-11) to add seizer fees
m_help_Repossseion_Help =" SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.SEIZER_NAME ,L.SEIZER_FEE "+
													" FROM  "+
													" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.SEIZER_NAME,P.SEIZER_FEE "+
													" FROM( "+ 
													" SELECT  "+
									        " DISTINCT A.REPOSSESSION_NO, "+
													" A.SEIZER_CODE, "+
									        " A.FINANCE_NO, "+
									        " B.CLIENT_CODE, "+
									        " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME, "+
													//" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+
													" C.FIRST_NAME || ' ' || C.LAST_NAME SEIZER_NAME ,  "+	 //ADDED BY NUWAN DE SILVA 13-06-07
													" "+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE) SEIZER_FEE "+
													" FROM "+
									        " "+m_schema_name+".AF_RE_PRO_REPOSSESSION  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,   "+
									        " "+m_schema_name+".AF_CO_MAS_SEIZER  C "+    				
									        " WHERE A.FINANCE_NO=B.FINANCE_NO   AND "+
													" A.SEIZER_CODE=C.SEIZER_CODE "+
									        " AND   "+
									        " (UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
									        " UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
									        " A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
									        " A.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
									        "  )  "+
									        " AND A.ACTIVE_STATUS='Y' "+
													
													/*" AND APPLICATION_NO IN "+
													" (SELECT "+
													"  APPLICATION_NO "+
													"  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
													"  WHERE     ACTIVE_STATUS ='VERIFY') "+
													*/
													" ORDER BY A.REPOSSESSION_NO DESC   "+
													" )P)L  "+
													" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

        		
												
												
												
												
												
												
												
	

/*----------------------------------------------------------------
		Purpose  : select Finance Number
	
		Used in  : Collection Repossession Screen
	-----------------------------------------------------------------*/			
  FinanceNoSql =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
												" FROM "+
												" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
												" FROM "+
												" (SELECT FINANCE_NO,APPLICATION_NO, A.CLIENT_CODE, "+
												"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME "+
												" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
												" WHERE  (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												//"        AND APPLICATION_STATUS='ACTIVATED' "+
										    " ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
										    " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
												
/*----------------------------------------------------------------
		Purpose  : select Finance Number
	
		Used in  : Collection Repossession Screen
	-----------------------------------------------------------------*/			
												
			//Modified by Mahela on 23-05-2007 
			// Purpose : To Select Finance No's with Vehicle No assigned
			m_help_Finance_Repossession_Sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" NVL(B.TEL_NO,'-') TEL_NO,  "+
    	" NVL(B.NIC_NO,'-') NIC_NO  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
      "(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
      "  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
      "  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
      "  ) B  "+
			//"  "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  C "+
				
      "  WHERE A.FINANCE_NO=B.FINANCE_NO   "+
      "  AND   "+
      "  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
		//	"  C.REG_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
      "  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
      "  )  "+
    //  "  AND A.APPLICATION_STATUS IN('"+m_vector.elementAt(1)+"')  "+
		  "	AND A.FINANCE_NO NOT IN "+
			" ( SELECT FINANCE_NO "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+
			" WHERE VEHICLE_INVENTORY_STATUS='ENTERED') "+				 
			//Added By Mahela on 23-05-2007 ---------------------------------------------------------------------
			"	AND A.FINANCE_NO IN "+
			" 									( "+
 			"  									 SELECT "+
  		"   								 FINANCE_NO "+
 			"   								 FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
      "										 WHERE APPLICATION_NO IN ( "+
 			"																							SELECT DISTINCT "+
  		"																							APPLICATION_NO "+
 			"																							FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
 			"																							WHERE VEHICLE_NO IS NOT NULL "+
 			"																						 ) "+
 			" 									)"+				 
			"	AND A.FINANCE_NO NOT IN "+
			" ( SELECT FINANCE_NO "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION "+		  	
			" WHERE ACTIVE_STATUS <> 'C') "+				 
			//End of Addition -------------------------------------------------------------------------------------
      "  ORDER BY A.APPLICATION_NO DESC  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

												
												
	
	/*----------------------------------------------------------------
		Purpose  : select Finance Number
	
		Used in  : Collection Other Invoices
	-----------------------------------------------------------------*/			
  FinanceSql =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
												" FROM "+
												" (SELECT FINANCE_NO,APPLICATION_NO, A.CLIENT_CODE, "+
												"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,A.CURRENCY_CODE "+
												" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
												" WHERE  (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
												"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"        AND APPLICATION_STATUS='ACTIVATED' "+
										    " ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
										    " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	
	
	
/*----------------------------------------------------------------
		Purpose  : select Seizer Code
	
		Used in  : Collection Repossession Screen
	-----------------------------------------------------------------*/			
  SeizerCodeSql =     " SELECT P.NO,SEIZER_CODE,NAME,MOBILE_NO,TEL_NO "+
											" FROM "+
											" (SELECT ROWNUM NO,SEIZER_CODE,NAME,MOBILE_NO,TEL_NO "+
											" FROM "+
											" (SELECT A.SEIZER_CODE, A.FIRST_NAME||' '||A.LAST_NAME NAME, "+
                      "         A.MOBILE_NO, A.TEL_NO "+
											" FROM   "+m_schema_name+".AF_CO_MAS_SEIZER A "+
											" WHERE  (SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
											"        OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
											"        OR UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
											"        OR UPPER(MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
											"        OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
											"        ACTIVE_STATUS='Y' "+
										  " ORDER BY SEIZER_CODE DESC,LAST_NAME,FIRST_NAME )) P "+		
										  " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
	


	/*----------------------------------------------------------------
		Purpose  : select clients
	
		Used in  : RE SETTLEMENT RECEIPT 
	-----------------------------------------------------------------*/			
	ClientSql =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
												"FROM "+
														"(SELECT DISTINCT A.CLIENT_CODE , FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
														"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
														"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ //added by nuwan de silva 25-07-07
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
														"		WHERE "+
														"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
														"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
														"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"        FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
														
														"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
														//commented and added by SH on 04-07-2007 
														//"       "+m_schema_name+".AF_CO_CHECK_FIN_REG_NO(CLIENT_CODE,UPPER('%"+m_vector.elementAt(0)+"%'))='YES' "+
														/*"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
 													  "                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
														"                               "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
														"                        WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
														"                               (FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"                               CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
														
														"                                B.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%'))) "+
														*/ //end 
												" ORDER BY FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
										
	                /*"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
												"FROM "+
														"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'-') CITY_NAME "+ //added by nuwan de silva 25-07-07
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														//commented and added by SH on 04-07-2007 
														//"       "+m_schema_name+".AF_CO_CHECK_FIN_REG_NO(CLIENT_CODE,UPPER('%"+m_vector.elementAt(0)+"%'))='YES' "+
														"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
 													  "                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
														"                               "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
														"                        WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
														"                               (FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"                               CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
														
														"                                B.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%'))) "+
														//end 
												" ORDER BY FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
										*/
										
										
										
										
										
										
										
										
										
		 //------------------ ID         : Valuation Process ----------------------------------
		//--------------------Purpose    : Valuation Code Help ----------------------------------------------



				m_help_TXT_VALUATION_NO_sql=
			" SELECT L.NO ,L.VALUATION_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO,L.COLOUR,"+
   		" L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE ,L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING,"+
    	" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.APPLICATION_NO,L.INVENTORY_NO,L.VALUER_CODE,L.AMT  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,"+
    	"	P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,"+
    	" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.APPLICATION_NO,P.INVENTORY_NO,P.VALUER_CODE,P.AMT "+
			" FROM( "+ 
  		" SELECT "+
      " VALUATION_NO,"+
			"	ASSET_ID,"+
			"	SUB_MODEL_CODE,"+
			"	NVL(REG_NO,'-') REG_NO,"+
			"	ENGINE_NO,"+
			"	CHASSIS_NO,"+
			"	COLOUR,"+
			"	MODEL_CODE,"+
			"	NOTES,"+
			"	REMARKS,"+
			"	VALUATION_DATE,"+
			"	VALUE,"+
			"	TYPE_OF_BODY,"+
			"	NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-') DATE_OF_REG,"+
			"	METER_READING,"+
			"	ACTIVE_STATUS,"+
			"	GENERAL_INDEX,"+
			"	SEATING_CAPACITY,"+
			"	NO_OF_CYLINDERS,"+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE ,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE, "+
			" APPLICATION_NO, "+
			" NVL(INVENTORY_NO,'-') INVENTORY_NO,  "+
			"	VALUER_CODE, "+
			" (select valuer_amount from " + m_schema_name + ".AF_CO_MAS_VALUERS where valuer_code=VALUER_CODE) as amt "+

      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			" WHERE VALUATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' AND INVENTORY_NO IS NOT NULL "+
			"	ORDER BY VALUATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//Added By Nuwan De Silva 19-04-2007---------------------------------------
			////////////Purpose : Valuation Number/////////////////////////////////////
			
				m_help_TXT_VALUATION_NO2_sql = 
				" SELECT L.NO ,L.VALUATION_NO,L.INVENTORY_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
				" L.CHASSIS_NO,L.COLOUR, L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE , "+
				" L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING, L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.VALUER_CODE,l.amt,L.YEAR_OF_MANUFACTURE,L.CONDITION_OF_ASSET,L.FORCED_SALES_VALUE,L.APPLICATION_NO,L.VALUER_NAME "+
				" FROM "+
				" (SELECT ROWNUM NO,P.VALUATION_NO,P.INVENTORY_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO, "+
				" P.CHASSIS_NO,P.COLOUR,\tP.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG, "+
				" P.METER_READING, P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.VALUER_CODE,p.amt,P.YEAR_OF_MANUFACTURE,P.CONDITION_OF_ASSET,P.FORCED_SALES_VALUE,P.APPLICATION_NO,P.VALUER_NAME  "+
				" FROM( "+
				" SELECT  VALUATION_NO,"+
				" NVL(INVENTORY_NO,'-') INVENTORY_NO,"+
				" ASSET_ID,"+
				" SUB_MODEL_CODE,"+
				" NVL(REG_NO,'-') REG_NO,"+
				" ENGINE_NO, "+
				" CHASSIS_NO,"+ 
				" COLOUR, "+
				" MODEL_CODE,"+
				" NOTES, "+
				" REMARKS,"+
				" VALUATION_DATE,"+
				" VALUE,"+
				" TYPE_OF_BODY,"+
				" NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-') DATE_OF_REG,"+
				" METER_READING, "+
				" ACTIVE_STATUS,"+
				" GENERAL_INDEX, "+
				" SEATING_CAPACITY,"+
				" NO_OF_CYLINDERS, "+
				" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE ," +
				" " + m_schema_name + ".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE, " + 
				"	NVL(VALUER_CODE,'-') VALUER_CODE, "+
        " (select sum(valuer_amount) from " + m_schema_name + ".AF_CO_MAS_VALUERS where valuer_code=VALUER_CODE) as amt, "+
				"	NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
				"	NVL(CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
				"	NVL(FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
				"	NVL(APPLICATION_NO,'-') APPLICATION_NO, "+
				" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME "+ //added by nuwan de silva 18-07-07
				" FROM " + m_schema_name + ".AF_CO_PRO_APP_VALUATION " +
				" WHERE ( VALUATION_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
				"         APPLICATION_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
				"         REG_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
				"         INVENTORY_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
				"         ASSET_ID LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
				"         SUB_MODEL_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') ) "+

				" AND ACTIVE_STATUS  ='" + m_vector.elementAt(1) + "' " +
				" AND INVENTORY_NO IS NOT NULL "+
				" ORDER BY VALUATION_NO ASC " +
				"  )P)L  " +
			  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
						
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			

		m_help_TXT_VALUATION_NO1_sql=
			" SELECT L.NO ,L.VALUATION_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO,L.COLOUR,"+
   		" L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE ,L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING,"+
    	" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.APPLICATION_NO,L.INVENTORY_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,"+
    	"	P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,"+
    	" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.APPLICATION_NO,P.INVENTORY_NO "+
			" FROM( "+ 
  		" SELECT "+
      " VALUATION_NO,"+
			"	ASSET_ID,"+
			"	SUB_MODEL_CODE,"+
			"	NVL(REG_NO,'-') REG_NO,"+
			"	ENGINE_NO,"+
			"	CHASSIS_NO,"+
			"	COLOUR,"+
			"	MODEL_CODE,"+
			"	NOTES,"+
			"	REMARKS,"+
			"	VALUATION_DATE,"+
			"	VALUE,"+
			"	TYPE_OF_BODY,"+
			"	NVL(TO_CHAR(DATE_OF_REG,'DD-MM-YYYY'),'-') DATE_OF_REG,"+
			"	METER_READING,"+
			"	ACTIVE_STATUS,"+
			"	GENERAL_INDEX,"+
			"	SEATING_CAPACITY,"+
			"	NO_OF_CYLINDERS,"+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE ,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE, "+
			" APPLICATION_NO, "+
			" NVL(INVENTORY_NO,'-') INVENTORY_NO  "+
      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			" WHERE VALUATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' AND INVENTORY_NO IS NOT NULL "+
			"	ORDER BY VALUATION_NO ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select Finance No
	//--used in - Collection - POD Cheques
	//-------------------------------------------------------------------------------------
	
			m_help_TXT_FINANCE_NO_1_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.CURRENCY_CODE,L.ACCOUNT_NO,L.BRANCH_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.CURRENCY_CODE,P.ACCOUNT_NO,P.BRANCH_CODE "+
			" FROM( "+ 
      " SELECT "+
      " NVL(FINANCE_NO,'-') FINANCE_NO, "+
      " NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
			"	FULL_NAME, "+
			" CURRENCY_CODE,ACCOUNT_NO,A.BRANCH_CODE "+
			//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS C "+
			
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
		//	" (FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
		//	"	OR A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR FULL_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') ) 	"+
			
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
      "  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
      "  )  "+
				
			" AND A.CLIENT_CODE=C.CLIENT_CODE "+
			" AND APPLICATION_STATUS IN ('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')"+
			" AND FINANCE_NO IS NOT NULL "+
			" ORDER BY FINANCE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select Account No
	//--used in - Collection - POD Cheques
	//-------------------------------------------------------------------------------------
		 m_help_TXT_ACCOUNT_NO_1_sql=	
				
			 "SELECT L.NO ,L.ACCOUNT_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.BANK_NAME,L.CLIENT_CODE "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACCOUNT_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.BANK_NAME,P.CLIENT_CODE "+
			 "FROM(  "+
  		 " SELECT "+
			 " ACCOUNT_NO,BRANCH_CODE, "+	
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+		
 			 " BANK_CODE, "+
 			 " "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME,CLIENT_CODE"+		
       " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
		   " WHERE UPPER(CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			 "  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
			
			
	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select Suspend Reference No
	//--used in - Collection - Payment Settlement Screen
	//-------------------------------------------------------------------------------------
			
			m_help_TXT_SUS_REF_NO_sql=
			"SELECT L.NO ,L.SUS_REF_NO,L.SUSPENSE_ENTRY_TYPE,L.RECEIVER,L.PAYER,L.REF_NO,L.TOT_SETTLE_AMOUNT,L.INT_BAL_SETTLE_AMOUNT, "+
			"L.PAY_FROM_INT_BAL_AMT, "+
    	"L.BAL_TO_BE_PAID,L.PAY_FROM_INT_BAL_AMT_CURR,L.BAL_TO_BE_PAID_CURR,L.CURR_CODE, "+
			"L.EXCHANGE_RATE, "+
			"L.RECEIVER_NAME,L.ENT_DATE "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.SUS_REF_NO,P.SUSPENSE_ENTRY_TYPE,P.RECEIVER,P.PAYER,P.REF_NO,P.TOT_SETTLE_AMOUNT,P.INT_BAL_SETTLE_AMOUNT, "+
				" P.PAY_FROM_INT_BAL_AMT, "+
    	 "P.BAL_TO_BE_PAID,P.PAY_FROM_INT_BAL_AMT_CURR,P.BAL_TO_BE_PAID_CURR,P.CURR_CODE,P.EXCHANGE_RATE, "+
			 "P.RECEIVER_NAME,P.ENT_DATE "+
			 "FROM(  "+
  		 " SELECT  "+
    	 " SUS_REF_NO,  "+
    	// " SUSPENSE_ENTRY_TYPE,  "+
			  " nvl(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,  "+

    	 " RECEIVER,  "+
    	 "	PAYER,  "+
    	 " REF_NO,  "+
    	 " TOT_SETTLE_AMOUNT,  "+
    	 " INT_BAL_SETTLE_AMOUNT,  "+
    	 " null PAY_FROM_INT_BAL_AMT,  "+
    	 " BAL_TO_BE_PAID,  "+
       " null PAY_FROM_INT_BAL_AMT_CURR,  "+
    	 " null BAL_TO_BE_PAID_CURR,  "+
			 " CURR_CODE,  "+
    	 " EXCHANGE_RATE,  "+
			 ""+m_schema_name+".AF_CO_GET_CLIENT_NAME(RECEIVER) RECEIVER_NAME, "+	
			 "	TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE"+
			 " FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//---modified by 	: delanjali------------------------------------------------------------------------------------------------------------------------------
			//---date					: 2007-06-13------------------------------------------------------------------------------------------------------------------------------
			//  " WHERE UPPER(PAYER) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(2)+"') "+
			//--------------------------------------------------------------------------------------------------------------------------------------------------------


			 " WHERE UPPER(PAYER) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND  UPPER(SUS_REF_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(2)+"') "+


			// " WHERE (UPPER(PAYER) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(SUS_REF_NO) LIKE UPPER('"+m_vector.elementAt(1)+"%')) "+
			 "  ORDER BY  SUS_REF_NO ASC"+
			"  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select LIC Account & Branch No
	//--used in - Collection - Payment Settlement Screen
	//-------------------------------------------------------------------------------------
			
m_help_TXT_ACCOUNT_NO_2_sql=
			 "SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_NAME "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_NAME "+
			 "FROM(  "+
  		 " SELECT "+
 			 " ACC_NO,"+
			 " BRANCH_CODE,"+	
			 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+	
			 " "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME"+		
       " FROM LAKDL.AF_CO_MAS_LICENCEE_SETTLEMENT "+
		   " WHERE UPPER(ACC_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
      " WHERE (LOCATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR LOCATION_DESC LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
				
	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select Payee Account & Branch No
	//--used in - Collection - Payment Settlement Screen
	//-------------------------------------------------------------------------------------
				

	 m_help_TXT_PAYEE_ACC_NO_sql=	
				
			 "SELECT L.NO ,L.ACCOUNT_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.BANK_NAME,L.CLIENT_CODE "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACCOUNT_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.BANK_NAME,P.CLIENT_CODE "+
			 "FROM(  "+
  		 " SELECT "+
			 " ACCOUNT_NO,BRANCH_CODE, "+	
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+		
 			 " BANK_CODE, "+
 			 " "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME,CLIENT_CODE"+		
       " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
		   " WHERE (UPPER(CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(1)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			 "  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
				//UPPER(ACCOUNT_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR
				
	//-------------------------------------------------------------------------------------
	//--Purpsoe - Select Payement Settlement No
	//--used in - Collection - Payment Settlement Screen
	//-------------------------------------------------------------------------------------

			
					 m_help_TXT_BRANCH_CODE_sql_new=
		" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.ADDRESS1,NVL(L.ADDRESS2,'N/A'),NVL(L.CITY_CODE,' '),NVL(L.TEL_NO,'N/A'),NVL(L.FAX_NO,'N/A'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE"+
			" FROM( "+ 
  		" SELECT "+
      " BRANCH_CODE,"+
			" BRANCH_NAME,"+
			" BANK_CODE,"+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE,"+
			" TEL_NO,"+
			" FAX_NO,"+
			" DAYS_TO_REALISE, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
		  " WHERE (UPPER(BRANCH_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

				m_help_TXT_PAYMENT_NO_sql=
				 "SELECT L.NO ,"+
				 "L.PAYMENT_NO,L.SUS_REF_NO,L.CLIENT_CODE,L.SETTLE_MODE,L.ENTRY_TYPE,L.PAY_AMOUNT, "+
				 "L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PROCESS_STATUS, "+
				 "L.ENTDATE,L.RECON_STATUS,L.RECON_DATE,L.RECON_BY,L.EFF_VALDATE,L.REALISED_DATE, "+
				 "L.PAYEE_NAME,L.PAY_AMOUNT_CURR,L.EXCHANGE_RATE_BANK,L.EXCHANGE_RATE_REP_CURR, "+
				 "L.REC_AMMOUNT_REP_CURR,L.EXCHANGE_GAIN_LOSS,L.COMMENTS,L.PAYER,L.CURR_CODE,L.EXCHANGE_RATE,L.RECEIVER,L.CLIENT_NAME,L.BAL_TO_BE_PAID,L.PAYEE_BRANCH_NAME,L.LIC_BRANCH_NAME "+
				 "FROM  "+
				 "(SELECT ROWNUM NO,P.PAYMENT_NO,P.SUS_REF_NO,P.CLIENT_CODE,P.SETTLE_MODE,P.ENTRY_TYPE,P.PAY_AMOUNT, "+
				 "P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PROCESS_STATUS, "+
				 "P.ENTDATE,P.RECON_STATUS,P.RECON_DATE,P.RECON_BY,P.EFF_VALDATE,P.REALISED_DATE, "+
				 "P.PAYEE_NAME,P.PAY_AMOUNT_CURR,P.EXCHANGE_RATE_BANK,P.EXCHANGE_RATE_REP_CURR, "+
				 "P.REC_AMMOUNT_REP_CURR,P.EXCHANGE_GAIN_LOSS,P.COMMENTS,P.PAYER,P.CURR_CODE,P.EXCHANGE_RATE,P.RECEIVER,P.CLIENT_NAME,P.BAL_TO_BE_PAID,P.PAYEE_BRANCH_NAME,P.LIC_BRANCH_NAME "+
	 			 "FROM(  "+  		 
				 "SELECT PAYMENT_NO,A.SUS_REF_NO,NVL(CLIENT_CODE,'-') CLIENT_CODE,SETTLE_MODE,ENTRY_TYPE,PAY_AMOUNT, "+
				 "LIC_BRANCH_CODE,LIC_ACC_NO,NVL(PAYEE_BRANCH_CODE,'-') PAYEE_BRANCH_CODE,NVL(PAYEE_ACC_NO,'-') PAYEE_ACC_NO,PROCESS_STATUS, "+
				 "ENTDATE,RECON_STATUS,RECON_DATE,RECON_BY,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') AS EFF_VALDATE,REALISED_DATE, "+
				 "PAYEE_NAME,PAY_AMOUNT_CURR,EXCHANGE_RATE_BANK,EXCHANGE_RATE_REP_CURR, "+
				 "REC_AMMOUNT_REP_CURR,EXCHANGE_GAIN_LOSS,COMMENTS,PAYER,CURR_CODE, "+
			   "EXCHANGE_RATE,RECEIVER, "+
				 "NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME,BAL_TO_BE_PAID, "+	
				 ""+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYEE_BRANCH_CODE) PAYEE_BRANCH_NAME, "+
				 ""+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE) LIC_BRANCH_NAME "+

					"FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B"+
			   " WHERE (UPPER(PAYMENT_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND PROCESS_STATUS=('"+m_vector.elementAt(1)+"')"+
				 "AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO) "+
				 "ORDER BY PAYMENT_NO ASC "+					
					"  )P)L  "+
				 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

//other payments

m_help_TXT_PAYMENT_NO_sql_other=
	/*			 "SELECT L.NO ,"+
				 "L.PAYMENT_NO,L.SUS_REF_NO,L.CLIENT_CODE,L.SETTLE_MODE,L.ENTRY_TYPE,L.PAY_AMOUNT, "+
				 "L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PROCESS_STATUS, "+
				 "L.ENTDATE,L.RECON_STATUS,L.RECON_DATE,L.RECON_BY,L.EFF_VALDATE,L.REALISED_DATE, "+
				 "L.PAYEE_NAME,L.PAY_AMOUNT_CURR,L.EXCHANGE_RATE_BANK,L.EXCHANGE_RATE_REP_CURR, "+
				 "L.REC_AMMOUNT_REP_CURR,L.EXCHANGE_GAIN_LOSS,L.COMMENTS,L.PAYER,L.CURR_CODE,L.EXCHANGE_RATE,L.RECEIVER,L.PAYER_NAME,L.BAL_TO_BE_PAID,nvl(L.PAYEE_BRANCH_NAME,'-'),nvl(L.LIC_BRANCH_NAME,'-') "+
				 "FROM  "+
				 "(SELECT ROWNUM NO,P.PAYMENT_NO,P.SUS_REF_NO,P.CLIENT_CODE,P.SETTLE_MODE,P.ENTRY_TYPE,P.PAY_AMOUNT, "+
				 "P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PROCESS_STATUS, "+
				 "P.ENTDATE,P.RECON_STATUS,P.RECON_DATE,P.RECON_BY,P.EFF_VALDATE,P.REALISED_DATE, "+
				 "P.PAYEE_NAME,P.PAY_AMOUNT_CURR,P.EXCHANGE_RATE_BANK,P.EXCHANGE_RATE_REP_CURR, "+
				 "P.REC_AMMOUNT_REP_CURR,P.EXCHANGE_GAIN_LOSS,P.COMMENTS,P.PAYER,P.CURR_CODE,P.EXCHANGE_RATE,P.RECEIVER,P.PAYER_NAME,P.BAL_TO_BE_PAID,P.PAYEE_BRANCH_NAME,P.LIC_BRANCH_NAME "+
	 			 "FROM(  "+  		 
				 "SELECT PAYMENT_NO, "+//1
					"A.SUS_REF_NO,NVL(CLIENT_CODE,'-') CLIENT_CODE, "+//2
					"SETTLE_MODE, "+//3
					"ENTRY_TYPE, "+//4
					"PAY_AMOUNT, "+//5
				  "LIC_BRANCH_CODE, "+//6
					"LIC_ACC_NO, "+//7
					"NVL(PAYEE_BRANCH_CODE,'-') PAYEE_BRANCH_CODE, "+//8
					"NVL(PAYEE_ACC_NO,'-') PAYEE_ACC_NO, "+//9
					"PROCESS_STATUS, "+//10
				  "ENTDATE, "+//11
					"RECON_STATUS, "+//12
					"RECON_DATE, "+//13
					"RECON_BY, "+//14
					"TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') AS EFF_VALDATE, "+//15
					"REALISED_DATE, "+//16
				  "PAYEE_NAME, "+//17
					"PAY_AMOUNT_CURR, "+//18
					"EXCHANGE_RATE_BANK, "+//19
					"EXCHANGE_RATE_REP_CURR, "+//20
				  "REC_AMMOUNT_REP_CURR, "+//21
					"EXCHANGE_GAIN_LOSS, "+//22
					"COMMENTS, "+//23
					"PAYER, "+//24
					"CURR_CODE, "+//25
			    "EXCHANGE_RATE, "+//26
					"RECEIVER, "+//27
				  "NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(PAYER),'-') PAYER_NAME, "+//28
					"BAL_TO_BE_PAID, "+	//29
				  ""+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYEE_BRANCH_CODE) PAYEE_BRANCH_NAME, "+//30
				  ""+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE) LIC_BRANCH_NAME "+//31"
					"FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B "+
			   " WHERE (UPPER(PAYMENT_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) AND PROCESS_STATUS=('"+m_vector.elementAt(1)+"')"+
				 "AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO) "+
				 "ORDER BY PAYMENT_NO ASC "+					
					"  )P)L  "+
				 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

*/

"SELECT L.NO , "+
				" L.PAYMENT_NO,L.SUS_REF_NO,L.CLIENT_CODE,L.SETTLE_MODE,L.ENTRY_TYPE,L.PAY_AMOUNT,  "+
				" L.LIC_BRANCH_CODE,L.LIC_ACC_NO,L.PAYEE_BRANCH_CODE,L.PAYEE_ACC_NO,L.PROCESS_STATUS,  "+
				" L.ENTDATE,L.RECON_STATUS,L.RECON_DATE,L.RECON_BY,L.EFF_VALDATE,L.REALISED_DATE,  "+
				" L.PAYEE_NAME,L.PAY_AMOUNT_CURR,L.EXCHANGE_RATE_BANK,L.EXCHANGE_RATE_REP_CURR,  "+
				" L.REC_AMMOUNT_REP_CURR,L.EXCHANGE_GAIN_LOSS,L.COMMENTS,L.PAYER,L.CURR_CODE,L.EXCHANGE_RATE,L.RECEIVER,L.PAYER_NAME,L.BAL_TO_BE_PAID,nvl(L.PAYEE_BRANCH_NAME,'-'),nvl(L.LIC_BRANCH_NAME,'-') ,l.app,nvl(l.client_code1,'-') client_code1 ,nvl("+m_schema_name+".af_co_get_client_name(client_code1),'-') client_name "+
				" FROM   "+
				" (SELECT ROWNUM NO,P.PAYMENT_NO,P.SUS_REF_NO,P.CLIENT_CODE,P.SETTLE_MODE,P.ENTRY_TYPE,P.PAY_AMOUNT,  "+
				" P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE,P.PAYEE_ACC_NO,P.PROCESS_STATUS,  "+
				" P.ENTDATE,P.RECON_STATUS,P.RECON_DATE,P.RECON_BY,P.EFF_VALDATE,P.REALISED_DATE,  "+
				" P.PAYEE_NAME,P.PAY_AMOUNT_CURR,P.EXCHANGE_RATE_BANK,P.EXCHANGE_RATE_REP_CURR,  "+
				" P.REC_AMMOUNT_REP_CURR,P.EXCHANGE_GAIN_LOSS,P.COMMENTS,P.PAYER,P.CURR_CODE,P.EXCHANGE_RATE,P.RECEIVER,P.PAYER_NAME,P.BAL_TO_BE_PAID,P.PAYEE_BRANCH_NAME,P.LIC_BRANCH_NAME ,p.app,p.client_code1 "+
	 			" FROM(     "+		 
					
					
				"					 SELECT QUERY1.PAYMENT_NO, "+// --1
				"	QUERY1.SUS_REF_NO,QUERY1.CLIENT_CODE,  "+//--2
				"	QUERY1.SETTLE_MODE, "+ //--3
				"	QUERY1.ENTRY_TYPE, "+// --4
				"	QUERY1.PAY_AMOUNT, "+ //--5
				"  QUERY1.LIC_BRANCH_CODE, "+ //--6
				"	QUERY1.LIC_ACC_NO, "+ //--7
				"	QUERY1.PAYEE_BRANCH_CODE, "+// --8
				"	QUERY1.PAYEE_ACC_NO, "+ //--9
				"	QUERY1.PROCESS_STATUS,  "+//--10
				"  QUERY1.ENTDATE, "+ //--11
				"	QUERY1.RECON_STATUS,  "+//--12
				"	QUERY1.RECON_DATE,  "+//--13
					"QUERY1.RECON_BY,  "+//--14
					"QUERY1.EFF_VALDATE,  "+//--15
					"QUERY1.REALISED_DATE, "+ //--16
				 " QUERY1.PAYEE_NAME, "+// --17
					"QUERY1.PAY_AMOUNT_CURR, "+// --18
					"QUERY1.EXCHANGE_RATE_BANK,  "+//--19
					"QUERY1.EXCHANGE_RATE_REP_CURR,  "+//--20
				 " QUERY1.REC_AMMOUNT_REP_CURR,  "+//--21
					"QUERY1.EXCHANGE_GAIN_LOSS, "+// --22
					"QUERY1.COMMENTS,  "+//--23
					"QUERY1.PAYER, "+// --24
					"QUERY1.CURR_CODE,  "+//--25
			   " QUERY1.EXCHANGE_RATE,  "+//--26
					"QUERY1.RECEIVER,  "+//--27
				 " QUERY1.PAYER_NAME, "+ //--28
					"QUERY1.BAL_TO_BE_PAID, 	 "+//--29
				 " QUERY1.PAYEE_BRANCH_NAME,  "+//--30
				  "QUERY1.LIC_BRANCH_NAME,  "+//--31
					"QUERY1.REF_NO, "+ 
				"QUERY1.APPLICATION_NO app, "+
                ""+m_schema_name+".af_co_get_client_CODE(QUERY1.APPLICATION_NO) client_code1	 "+			
					
					"FROM  "+
					
					
					
				  "(SELECT PAYMENT_NO,  "+//--1
					"A.SUS_REF_NO,NVL(CLIENT_CODE,'-') CLIENT_CODE, "+ //--2
					"SETTLE_MODE,  "+//--3
					"ENTRY_TYPE, "+ //--4
					"PAY_AMOUNT,  "+//--5
				 " LIC_BRANCH_CODE,  "+//--6
					"LIC_ACC_NO, "+// --7
				"	NVL(PAYEE_BRANCH_CODE,'-') PAYEE_BRANCH_CODE,  "+//--8
				"	NVL(PAYEE_ACC_NO,'-') PAYEE_ACC_NO,  "+//--9
				"	PROCESS_STATUS,  "+//--10
				 " ENTDATE, "+ //--11
					"RECON_STATUS,  "+//--12
					"RECON_DATE,  "+///--13
					"RECON_BY,  "+//--14
					"TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') AS EFF_VALDATE, "+ //--15
					"REALISED_DATE,  "+//--16
				  "PAYEE_NAME,  "+//--17
					"PAY_AMOUNT_CURR,  "+//--18
					"EXCHANGE_RATE_BANK, "+ //--19
					"EXCHANGE_RATE_REP_CURR, "+ //--20
				  "REC_AMMOUNT_REP_CURR, "+// --21
					"EXCHANGE_GAIN_LOSS,  "+//--22
					"nvl(COMMENTS,'-') comments,  "+//--23
					"PAYER, "+ //--24
					"CURR_CODE,  "+//--25
			    "EXCHANGE_RATE,  "+//--26
					"RECEIVER,  "+//--27
				 " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(PAYER),'-') PAYER_NAME, "+ //--28
					"BAL_TO_BE_PAID, 	 "+//--29
				 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYEE_BRANCH_CODE) PAYEE_BRANCH_NAME, "+// --30
				 " "+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE) LIC_BRANCH_NAME, "+ //--31
					"REF_NO,  "+
				""+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO) APPLICATION_NO 			 "+			
					
					"FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A,"+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B  "+
			   " WHERE (UPPER(PAYMENT_NO) LIKE UPPER('%')) AND PROCESS_STATUS=('Y') "+
				 "AND UPPER(A.SUS_REF_NO)=UPPER(B.SUS_REF_NO)  "+
					
				"	)QUERY1  "+
					
				" ORDER BY PAYMENT_NO DESC 		 "+			
				"	  )P)L   "+
				"  WHERE L.NO>=   "+Start_Val+"  AND L.NO<=  "+End_Val+" ";



		m_help_TXT_INVENTORY_NO_sql_new=

	  " SELECT L.NO ,L.INVENTORY_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
		" "+m_schema_name+".AF_CO_PRO_APP_VALUATION D,"+
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  "+
		"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
		"  AND A.INVENTORY_NO =D.INVENTORY_NO "+
		"  AND VAL_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
		"  ) "+
		"  AND A.COMPLETED_OFFER_NO IS NULL "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		m_help_TXT_ADVETST_NO_sql_new =
				
		" SELECT L.NO ,L.ADVER_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVENTORY_NO,L.VEHICLE_NO,L.ADVER_DATE,L.AMOUNT,L.VAT_AMOUNT,L.TOTAL_AMOUNT "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVER_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVENTORY_NO,P.VEHICLE_NO,P.ADVER_DATE,P.AMOUNT,P.VAT_AMOUNT,P.TOTAL_AMOUNT "+
		" FROM( "+ 
		" SELECT DISTINCT "+
		 " D.ADVER_NO, "+
	   " B.FINANCE_NO,   "+
     " C.CLIENT_CODE, "+
     " C.FULL_NAME, "+
     " D.INVENTORY_NO, "+
     " D.VEHICLE_NO, "+
     " TO_CHAR(D.ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
     " D.AMOUNT, "+
     " D.VAT_AMOUNT, "+
     " D.TOTAL_AMOUNT "+
	   " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D , "+
		 " "+m_schema_name+".AF_CO_PRO_APP_VALUATION E, "+
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
     "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+ 
     "  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+
     "  ) C   "+
		 "  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  AND D.INVENTORY_NO=A.INVENTORY_NO "+
     "  AND A.INVENTORY_NO=C.INVENTORY_NO   "+
	   "  AND E.INVENTORY_NO=C.INVENTORY_NO   "+
		 "  AND VAL_STATUS=('"+m_vector.elementAt(2)+"') "+
     "  AND    "+
     "  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
     "  D.ADVER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    "+
     "  )  "+
     "  AND D.STATUS=UPPER('"+m_vector.elementAt(1)+"')  "+ 
     "  ORDER BY D.ADVER_NO DESC   "+
		 "   )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		m_help_advertistemnt_help_offer_issue_new =	
			
			" SELECT L.NO ,L.ADVER_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVENTORY_NO,L.VEHICLE_NO,L.ADVER_DATE,L.AMOUNT,L.VAT_AMOUNT,L.TOTAL_AMOUNT "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.ADVER_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVENTORY_NO,P.VEHICLE_NO,P.ADVER_DATE,P.AMOUNT,P.VAT_AMOUNT,P.TOTAL_AMOUNT "+
		" FROM( "+ 
		" SELECT DISTINCT "+
		 " D.ADVER_NO, "+
	   " B.FINANCE_NO,   "+
     " C.CLIENT_CODE, "+
     " C.FULL_NAME, "+
     " A.INVENTORY_NO, "+
     " A.VEHICLE_NO, "+
     " TO_CHAR(D.ADVER_DATE,'DD-MM-YYYY') ADVER_DATE, "+
     " D.AMOUNT, "+
     " D.VAT_AMOUNT, "+
     " D.TOTAL_AMOUNT "+
	   " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL D, "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS E , "+
		 " (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
     "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V   "+ 
     "  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+
     "  ) C  , "+
		 ""+m_schema_name+".AF_CO_PRO_APP_VALUATION J "+
		 "  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  AND D.INVENTORY_NO=A.INVENTORY_NO AND E.INVENTORY_NO=A.INVENTORY_NO "+
     "  AND A.INVENTORY_NO=C.INVENTORY_NO   "+
		 "	AND J.INVENTORY_NO=C.INVENTORY_NO "+
			" AND VAL_STATUS=('"+m_vector.elementAt(2)+"') "+
     "  AND    "+
     "  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
     "  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
     "  D.ADVER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    "+
     "  )  "+
     "  AND D.STATUS=UPPER('"+m_vector.elementAt(1)+"')  "+ 
     "  ORDER BY D.ADVER_NO DESC   "+
		 "   )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		m_help_Inventory_no_Offer_Issue_new=
		
		" SELECT L.NO ,L.INVENTORY_NO,L.CLIENT_CODE,L.FULL_NAME,L.VEHICLE_NO "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVENTORY_NO,P.CLIENT_CODE,P.FULL_NAME,P.VEHICLE_NO "+
		" FROM( "+ 
    " SELECT "+
    " DISTINCT A.INVENTORY_NO,  "+
		//" B.FINANCE_NO,  "+
		" C.CLIENT_CODE , "+
		" C.FULL_NAME,  "+
		" A.VEHICLE_NO  "+
	  " FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS B, "+
				
		" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
    "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
    "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
    "  ) C  ,"+
		" "+m_schema_name+".AF_CO_PRO_APP_VALUATION D "+
		"  WHERE A.INVENTORY_NO=B.INVENTORY_NO  "+
		"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
		"  AND D.INVENTORY_NO=C.INVENTORY_NO  "+
		"  AND VAL_STATUS=('"+m_vector.elementAt(2)+"') "+

    "  AND   "+
    "  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  UPPER(C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
    "  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
  //  "  B.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
		"  A.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
		"  ) "+
		"  AND A.COMPLETED_OFFER_NO IS NULL "+
	//	"  AND B.ADVER_NO='-' "+
		"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
    "  ORDER BY A.INVENTORY_NO DESC  "+
    "  )P)L  "+
		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


			m_help_TXT_VALUER_CODE_sql=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,L.MOBILE_NO,L.DEFAULT_VALUE,l.valuer_amount,L.VALUER_NAME"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.MOBILE_NO,P.DEFAULT_VALUE,p.valuer_amount,P.VALUER_NAME"+
			" FROM( "+
			   " SELECT "+
         " VALUER_CODE,"+
         " FIRST_NAME, "+
         " LAST_NAME, "+
         " ADDRESS, "+
         " ADDRESS2,"+
         " CITY_CODE,"+
         " TEL_NO, "+
         " MOBILE_NO,"+
         " DEFAULT_VALUE,valuer_amount ,"+
				 " NVL((FIRST_NAME || ' ' || LAST_NAME),'-') VALUER_NAME "+	 //added by nuwan de silva 18-07-07
         " FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
				 " WHERE (VALUER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(ADDRESS) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')   ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			
			
				 //Cheque return Narration screen----------------------------------------------------------------------------------------------------------------------	
					//Narration code------------------------------------------------------------------------------------------------------------------------------	
					//Nuwan 15-08-07-----------------------------------------------------------------------------------------------------------------------------------
					m_help_TXT_CHQ_NARRATIONS_CODE_sql=
					"SELECT L.NO ,L.CHQ_NARR_CODE,L.CHQ_NARRATIONS,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
					 "FROM "+
					 "(SELECT ROWNUM NO ,P.CHQ_NARR_CODE,P.CHQ_NARRATIONS,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
					 " FROM( "+
					 "SELECT "+
					 " CHQ_NARR_CODE, "+
					 " CHQ_NARRATIONS, "+
					 " ACTIVE_STATUS, "+
					 " DEFAULT_VALUE "+
					 " FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS  "+
					 " WHERE (UPPER(CHQ_NARR_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(CHQ_NARRATIONS) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
					 "  )P)L  "+
					 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

			




			m_help_TXT_SUS_REF_NO_sql_new=
			/*"SELECT L.NO ,L.SUS_REF_NO,L.SUSPENSE_ENTRY_TYPE,L.RECEIVER,L.PAYER,L.REF_NO,L.TOT_SETTLE_AMOUNT,L.INT_BAL_SETTLE_AMOUNT, "+
			"L.PAY_FROM_INT_BAL_AMT, "+
    	"L.BAL_TO_BE_PAID,L.PAY_FROM_INT_BAL_AMT_CURR,L.BAL_TO_BE_PAID_CURR,L.CURR_CODE, "+
			"L.EXCHANGE_RATE, "+
			"L.RECEIVER_NAME,L.ENT_DATE "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.SUS_REF_NO,P.SUSPENSE_ENTRY_TYPE,P.RECEIVER,P.PAYER,P.REF_NO,P.TOT_SETTLE_AMOUNT,P.INT_BAL_SETTLE_AMOUNT, "+
			" P.PAY_FROM_INT_BAL_AMT, "+
    	"P.BAL_TO_BE_PAID,P.PAY_FROM_INT_BAL_AMT_CURR,P.BAL_TO_BE_PAID_CURR,P.CURR_CODE,P.EXCHANGE_RATE, "+
			"P.RECEIVER_NAME,P.ENT_DATE "+
			"FROM(  "+
  		" SELECT  "+
    	" SUS_REF_NO,  "+
			" nvl(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,  "+
	  	" RECEIVER,  "+
	  	"	PAYER,  "+
	  	" REF_NO,  "+
	  	" TOT_SETTLE_AMOUNT,  "+
	  	" INT_BAL_SETTLE_AMOUNT,  "+
	  	" null PAY_FROM_INT_BAL_AMT,  "+
	  	" BAL_TO_BE_PAID,  "+
	    " null PAY_FROM_INT_BAL_AMT_CURR,  "+
	  	" null BAL_TO_BE_PAID_CURR,  "+
			" CURR_CODE,  "+
	  	" EXCHANGE_RATE,  "+
			""+m_schema_name+".AF_CO_GET_CLIENT_NAME(RECEIVER) RECEIVER_NAME, "+	
			"	TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE"+
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			" WHERE UPPER(SUS_REF_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(1)+"') "+
			"  ORDER BY  SUS_REF_NO ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
			"SELECT L.NO ,L.SUS_REF_NO,L.SUSPENSE_ENTRY_TYPE,L.RECEIVER,L.PAYER,L.REF_NO,L.TOT_SETTLE_AMOUNT,L.INT_BAL_SETTLE_AMOUNT, "+
"L.PAY_FROM_INT_BAL_AMT, "+
"L.BAL_TO_BE_PAID,L.PAY_FROM_INT_BAL_AMT_CURR,L.BAL_TO_BE_PAID_CURR,L.CURR_CODE, "+
"L.EXCHANGE_RATE, "+
"L.PAYER_NAME,L.ENT_DATE ,L.APPLICATION_NO,L.CLIENT_CODE,NVL(L.REF_NAME,'-'),"+m_schema_name+".af_co_get_client_name(L.CLIENT_CODE) as CLIENT_NAME "+
"FROM "+
"(SELECT ROWNUM NO,P.SUS_REF_NO,P.SUSPENSE_ENTRY_TYPE,P.RECEIVER,P.PAYER,P.REF_NO,P.TOT_SETTLE_AMOUNT,P.INT_BAL_SETTLE_AMOUNT, "+
"P.PAY_FROM_INT_BAL_AMT, "+
"P.BAL_TO_BE_PAID,P.PAY_FROM_INT_BAL_AMT_CURR,P.BAL_TO_BE_PAID_CURR,P.CURR_CODE,P.EXCHANGE_RATE, "+
"P.PAYER_NAME,P.ENT_DATE ,P.APPLICATION_NO,P.CLIENT_CODE,P.REF_NAME "+
"FROM( "+
"SELECT "+
"query1.SUS_REF_NO, "+
"query1.SUSPENSE_ENTRY_TYPE , "+
"query1.RECEIVER, "+
"query1.PAYER, "+
"query1.REF_NO, "+
"query1.TOT_SETTLE_AMOUNT, "+
"query1.INT_BAL_SETTLE_AMOUNT, "+
"query1.PAY_FROM_INT_BAL_AMT, "+
"query1.BAL_TO_BE_PAID, "+ 
"query1.PAY_FROM_INT_BAL_AMT_CURR,"+
"query1.BAL_TO_BE_PAID_CURR, "+
"query1.CURR_CODE, "+
"query1.EXCHANGE_RATE, "+
"NVL(query1.PAYER_NAME,'-') PAYER_NAME, "+
"query1.ENT_DATE, "+
"NVL(query1.dd,'-') AS APPLICATION_NO, "+
"NVL(("+m_schema_name+".af_co_get_client_CODE(query1.dd)),'-') CLIENT_CODE, "+
 " "+m_schema_name+".AF_CO_GET_REF_NAME(query1.REF_NO,query1.RECEIVER) AS REF_NAME "+
"FROM "+
"(SELECT "+
"SUS_REF_NO, "+
"nvl(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE , "+
"RECEIVER, "+
"PAYER, "+
"REF_NO, "+
"TOT_SETTLE_AMOUNT, "+
"INT_BAL_SETTLE_AMOUNT, "+
"null PAY_FROM_INT_BAL_AMT, "+
"BAL_TO_BE_PAID, "+
"null PAY_FROM_INT_BAL_AMT_CURR, "+
"null BAL_TO_BE_PAID_CURR, "+
"CURR_CODE, "+
"EXCHANGE_RATE, "+
""+m_schema_name+".AF_CO_GET_CLIENT_NAME(PAYER) PAYER_NAME, "+
"TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE,"+m_schema_name+".AF_CO_GET_FIN_NO(REF_NO)dd "+
"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
" WHERE UPPER(SUS_REF_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(1)+"') "+
")  query1 "+
"ORDER BY  SUS_REF_NO DESC "+
")P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
				/*--------------------Purpose   : Team id Help ----------------------------------------------
		  ------------------- Added By  : Nuwan De Silva------------------------------------------------------
		  -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
				
		 m_help_TXT_TEAM_ID_sql=
			
			" SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
  		" FROM( "+ 
      " SELECT "+
      " TEAM_ID, "+
      " TEAM_DESC, "+
      " TEAM_HEAD, "+
      " DIVISION_CODE, "+
      " SUB_DIVISION_CODE "+
      " FROM "+m_schema_name+".AF_CO_MAS_TEAMS "+
    	" WHERE UPPER(TEAM_HEAD) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
      //added by nuwan de silva on 01-08-07----
      m_help_team_user_id_sql=
			" SELECT L.NO ,L.USER_ID,L.NAME,NVL(L.TEAM_ID,'N/A') AS TEAM_ID,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.TEAM_ID,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
  		" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" "+m_schema_name+".AF_CO_GET_USER_NAME(USER_ID) NAME, "+
			" A.TEAM_ID, "+
			" TEAM_DESC, "+
			" TEAM_HEAD, "+
			" DIVISION_CODE, "+
			" SUB_DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAMS A, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B "+
			" WHERE A.TEAM_ID=B.TEAM_ID "+
			" AND  A.ACTIVE_STATUS='Y'  "+
			" AND  B.ACTIVE_STATUS='Y'  "+
			" AND  UPPER(USER_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND  UPPER(TEAM_HEAD) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" ORDER BY DIVISION_CODE,SUB_DIVISION_CODE,TEAM_HEAD "+
				"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
  

			ClientSql1 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
												"FROM "+
														"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"					UPPER(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.CITY_CODE)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.MOBILE_NO)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.TEL_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.EMAIL)      LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.NIC_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) " +
														" and a.client_code=b.client_code "+
														"and b.application_status='ACTIVATED' "+
												" ORDER BY a.FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		

			m_help_TXT_FinanceSql_new2 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT NVL(FINANCE_NO,'-') FINANCE_NO,NVL(APPLICATION_NO,'-') APPLICATION_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND COLLECTION_OFFICER IS NULL "+
			" AND upper(CLIENT_CODE) like upper('"+m_vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";


			m_help_TXT_FINANCE_NO_LEASE_ASSIGN_sql_new=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.CLIENT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME "+
			" FROM( "+ 
      " SELECT "+
      " NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
      " NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
      " NVL(B.FULL_NAME,'-') CLIENT_NAME "+		
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
	  	" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND A.APPLICATION_STATUS=UPPER('"+m_vector.elementAt(2)+"') AND A.COLLECTION_OFFICER IS NOT NULL  "+
			" AND upper(A.CLIENT_CODE) like upper('"+m_vector.elementAt(1)+"%') "+
			" AND (UPPER(A.FINANCE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') ) ORDER BY FINANCE_NO DESC   "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

			ClientSql2 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
										"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
												"FROM "+
														"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
														"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"					UPPER(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.CITY_CODE)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.MOBILE_NO)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.TEL_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.EMAIL)      LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"         UPPER(a.NIC_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
														"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) " +
														" and a.client_code=b.client_code AND B.COLLECTION_OFFICER IS NOT NULL "+
														"and b.application_status='ACTIVATED' "+
												" ORDER BY a.FULL_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		


			m_help_TXT_GRP_INV_sql=			
			" SELECT L.NO ,L.GROUP_CODE,L.GROUP_NAME,L.GROUP_ADDRESS,L.BRC_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_NAME,P.GROUP_ADDRESS,P.BRC_NO "+
			" FROM( "+ 
      " SELECT "+
      " GROUP_CODE,GROUP_NAME,GROUP_ADDRESS,BRC_NO "+
      " FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES "+
	  	" WHERE (UPPER(GROUP_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(GROUP_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
			" AND active_staus='"+m_vector.elementAt(1)+"' ORDER BY GROUP_CODE DESC   "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			

			m_help_client_code_sql =   	"SELECT P.NO, P.CLIENT_CODE Client,APPLICATION_NO,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
																	"FROM "+
																	"(SELECT ROWNUM NO, CLIENT_CODE,APPLICATION_NO,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
																	"FROM "+
																	"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,B.APPLICATION_NO APPLICATION_NO,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
																	"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
																	"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
																	"					UPPER(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
																	"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
																	"	        UPPER(a.CITY_CODE)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
																	"	        UPPER(a.MOBILE_NO)  LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
																	"         UPPER(a.TEL_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
																	"	        UPPER(a.EMAIL)      LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
																	"         UPPER(a.NIC_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
																	"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')) " +
																	" and a.client_code=b.client_code "+
																	"and b.application_status='ACTIVATED' "+
																	" ORDER BY a.FULL_NAME )) P "+		
																	"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		

	/*----------------------------------------------------------------
		Purpose  : select clients
	
		Used in  : RE SETTLEMENT RECEIPT 
	-----------------------------------------------------------------*/			
	ClientSql_Client =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
												
	ClientSql_Client_add = "SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ 
												" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
												
	ClientSql_Client_city ="SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
											  "ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
												
	ClientSql_Client_tel ="SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.TEL_NO "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,TEL_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(A.TEL_NO,'-') TEL_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		

	ClientSql_Client_email =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.EMAIL "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,EMAIL "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(A.EMAIL,'-') EMAIL "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
												
	ClientSql_Client_nic =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.NIC_NO "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,NIC_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(A.NIC_NO,'-') NIC_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE (UPPER(A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
												
		ClientSql_Client_brc =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.BUSINESS_CERTIFICATE_NO "+
										    "FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,BUSINESS_CERTIFICATE_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(A.BUSINESS_CERTIFICATE_NO,'-') BUSINESS_CERTIFICATE_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE ( UPPER(A.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"AND  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
											
												
			ClientSql_Finance =	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.FINANCE_NO "+
												"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,FINANCE_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(B.FINANCE_NO,'-') FINANCE_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"AND (UPPER(B.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"ORDER BY FULL_NAME )) P "+		
										    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";											
										
					ClientSql_Reg ="SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.REG_NO "+
												"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,REG_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(C.REG_NO,'-') REG_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE  A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND	 B.APPLICATION_NO=C.APPLICATION_NO "+
												"AND (UPPER(C.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
										    "ORDER BY FULL_NAME )) P "+		
												"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";											
							
							ClientSql_Chassis ="SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME,P.CHASSIS_NO "+
												"FROM "+
												"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME,CHASSIS_NO "+
												"FROM "+
												"(SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME,NVL(C.CHASSIS_NO,'-') CHASSIS_NO "+ 
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
												"WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
												"AND B.APPLICATION_NO=C.APPLICATION_NO "+
												"AND (UPPER(C.CHASSIS_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
												"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
												"ORDER BY FULL_NAME )) P "+		
												"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";											
					 
						
						
						ReceiptSql_group = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_RE_PRO_GROUP_INVOICES B "+
														" WHERE  A.CLIENT_CODE= B.GROUP_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.GROUP_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.GROUP_ADDRESS)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       UPPER(B.BRC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        A.STATUS='E' "+
												" ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.GROUP_NAME )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";

						
						ReceiptSql_group_1 = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
														" WHERE "+// A.CLIENT_CODE= B.GROUP_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														//"        UPPER(B.GROUP_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														//"        UPPER(B.GROUP_ADDRESS)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														//"	       UPPER(B.BRC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
														"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        A.STATUS='E' "+
												" ORDER BY A.REC_NO DESC,A.EFF_VALDATE )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
						
						
		//----------------------added by ishani 2014.03.13---------------------------------------
		NewReceiptSql_refund = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
												"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
												"FROM "+
														"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
														" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
														" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
														"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        UPPER(CHEQUE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+ //added by Prabash on 15-02-2012		
													    "        AND A.STATUS NOT IN ('CAD','RET','C')   "+
														"        AND A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
													//	"        AND  REC_NO NOT IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL )"+
												" /*ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME*/ )) P "+		
										"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
					


//================================================================================================================
			
 
  // ****Do not Alter The Parts Below
	 Ret_Object= (Object)Sql_Name;
	 return Ret_Object;
	  
	} 
}

/*----------------------------------------------------------------*/


