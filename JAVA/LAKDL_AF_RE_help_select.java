//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL

import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_RE_help_select {
	
	//Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
	LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	
	public String InvoiceNoSql        = " ";
	public String InvoiceNoSql_Header = " Help ";
	
	
	public String m_help_txt_sub_team_sql ="";
	public String m_help_txt_sub_team_sql_Header ="";
	
	public String m_help_finance_no_application_status_change ="";//added by nuwan de silva on 28-09-07
	public String m_help_finance_no_application_status_change_Header="Finance No Help";
	
	public String m_help_finance_no_application_status_change_new = "";
	public String m_help_finance_no_application_status_change_new_Header = "Finance NO Help";//Added By Sandun on 23-10-2008
	
	public String m_help_finance_no_application_status_change_edit_new = "";
	public String m_help_finance_no_application_status_change_edit_new_Header ="Finance NO Help";//Added By Sandun on 23-10-2008
	
	public String m_help_client_code_help=""; //added by nuwan de silva 08-11-07
	public String m_help_client_code_help_Header="Client Help";
	
	public String m_help_finance_no_application_status_change_edit ="";//added by nuwan de silva on 01-10-07
	public String m_help_finance_no_application_status_change_edit_Header="Finance No Help";
	
	public String m_help_loan_facilities_sql ="";
	public String m_help_loan_facilities_sql_Header =" Loan Facilit Help";
	
	public String m_help_TXT_REPOSSESSION_NO_sql_report ="" ;
	public String m_help_TXT_REPOSSESSION_NO_sql_report_Header="Repossession Help";
	
	public String m_help_TXT_REPOSSESSION_NO_sql_report_2 ="" ;
	public String m_help_TXT_REPOSSESSION_NO_sql_report_2_Header="Repossession Help";
	
	// thamali 2013.08.13
	public String m_help_TXT_BROKER_DETAILS_sql="";
	public String m_help_TXT_BROKER_DETAILS_sql_Header="System Administration - Broker Codes";
	
	
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
	
	public String m_help_team_user_id_sql_new ="";
	public String m_help_team_user_id_sql_new_Header ="User Help";
	
	public String m_help_Repossseion_Help="";
	public String m_help_Repossseion_Help_Header ="Collection - Repossession Help";
	
	public String m_help_Repossseion_Help1="";
	public String m_help_Repossseion_Help1_Header ="Collection - Repossession Help";
	
	public String m_help_Reposssed_Client_Help="";
	public String m_help_Reposssed_Client_Help_Header ="Collection - Repossession Help";
	
	public String m_help_edit_Repossseion_Help="";
	public String m_help_edit_Repossseion_Help_Header ="Collection - Repossession Help";
	
	public String m_help_Finance_Repossession_Sql="";
	public String m_help_Finance_Repossession_Sql_Header="Collection - Finance Number Help";
	
	
	public String m_help_Finance_Repossessed_Sql="";
	public String m_help_Finance_Repossessed_Sql_Header="Collection - Finance Number Help";
	
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
	
	public String m_help_TXT_FIN_SQL ="";
	public String m_help_TXT_FIN_SQL_Header ="Month End View - Finance Number";
	
	
	
	public String m_inventory_advertisement_offer_process="";
	public String m_inventory_advertisement_offer_process_Header="Collection - Inventory Help";
	
	public String InvoiceSql        = " ";
	public String InvoiceSql_Header = " Collection Help "; 
	
	public String InvoiceSql_debit_note ="";
	public String InvoiceSql_debit_note_Header ="Collection Help ";
	
	public String FinanceSql             = " ";
	public String FinanceSql_Header      = " Collection Help "; 
	//Added by Dineth on 24-03-2009
	public String FinanceSql2             = " ";
	public String FinanceSql2_Header      = " Collection Help "; 
	//End by Dineth on 24-03-2009
	
	public String m_help_TXT_ACCOUNT_NO_sql ="";
	public String m_help_TXT_ACCOUNT_NO_sql_Header ="Account No Help";
	
	public String m_help_Collection_process_Account_code        ="";
	public String m_help_Collection_process_Account_code_Header ="Account Code Help";
	
	public String m_help_Collection_process_Deposit_code        ="";
	public String m_help_Collection_process_Deposit_code_Header ="Deposit Number Help";
	
	public String m_help_Collection_Return_realisation_cheque_no="";
	public String m_help_Collection_Return_realisation_cheque_no_Header="Cheque Number Help";
	
	
	public String GuarantorSql_add_gua = " ";
	public String GuarantorSql_add_gua_Header = "Credit Process - Guarantor Help";
	
	
	public String m_help_TXT_CLIENT_CODE                       ="" ;
	public String m_help_TXT_CLIENT_CODE_Header                ="Client Help";
	
	public String m_help_TXT_CLIENT_CODE_GURAN                       ="" ;
	public String m_help_TXT_CLIENT_CODE_GURAN_Header                ="Client Help";
	
	public String m_help_TXT_BANK_CODE_sql                     ="";
	public String m_help_TXT_BANK_CODE_sql_Header              ="Bank help";
	
	public String m_help_TXT_BRANCH_CODE_sql                  ="";
	public String m_help_TXT_BRANCH_CODE_sql_Header           ="Branch Help";
	
	public String m_help_TXT_ACCOUNT_CODE_sql                  ="";
	public String m_help_TXT_ACCOUNT_CODE_sql_Header           ="Account Number Help";
	
	public String m_help_TXT_USER_ID_sql                      ="";
	public String m_help_TXT_USER_ID_sql_Header               ="User Help";
	
	public String m_help_collection_officer_new                      ="";
	public String m_help_collection_officer_new_Header               ="User Help";
	
	public String m_help_TXT_EMP_CODE_sql="";
	public String m_help_TXT_EMP_CODE_sql_Header="Employee Code Help";
	
	public String m_help_employee_id_new      ="";
	public String m_help_employee_id_new_Header ="Employee Code Help";
	
	public String m_help_marketing_officer_colection          ="";
	public String m_help_marketing_officer_colection_Header   ="Marketing Officer Help";
	
	public String m_help_credi_officer          ="";
	public String m_help_credi_officer_Header   ="Credit Officer Help";
	
	// added by udara 12-04-2017
	public String m_help_item_category_help          ="";
	public String m_help_item_category_help_Header   ="Credit Officer Help";
	
	public String m_help_item_sub_category_help          ="";
	public String m_help_item_sub_category_help_Header   ="Credit Officer Help";
	// end by udara 12-04-2017
	
	public String m_help_credi_officer_re          ="";
	public String m_help_credi_officer_re_Header   ="Credit Officer Help";
	
	
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
	
	public String m_help_sub_charge_payee_code           ="";
	public String m_help_sub_charge_payee_code_Header      ="Payee Code Help";
	
	//added by Dineth on 2008-10-01
	public String m_help_TXT_PAYEE_CODE_sql           ="";
	public String m_help_TXT_PAYEE_CODE_sql_Header      ="Payee Code Help";
	
	
	//end by Dineth on 2008-10-01
	//added by Dineth on 2008-10-09
	public String m_help_TXT_PAYEE_CODE_sql1           ="";
	public String m_help_TXT_PAYEE_CODE_sql1_Header      ="Payee Code Help";
	
	
	//end by Dineth on 2008-10-09
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
	
	public String m_help_TXT_USERS_sql ="";
	public String m_help_TXT_USERS_sql_Header ="Users";
	
	public String m_help_TXT_BRANCH_sql = "";
	public String m_help_TXT_BRANCH_sql_Header = "Users";
	
	// added by udara on 17-10-2013
	public String m_help_TXT_BRANCH_2_sql = "";
	public String m_help_TXT_BRANCH_2_sql_Header = "Users";
	
	public String m_help_TXT_USERS_with_loc_sql ="";
	public String m_help_TXT_USERS_with_loc_sql_Header ="Users";
	
	// added by udara 27-11-2013
	public String m_help_TXT_USERS_with_loc_2_sql ="";
	public String m_help_TXT_USERS_with_loc_2_sql_Header ="Users";
	
	
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
	
	public String	ClientSql_PDC= "";			//Added by Prabash on 17-02-2012
	public String	ClientSql_PDC_Header= "	Collection - Client Code Help";
	
	public String	ClientSql_PDC2= "";			//Added by Prabash on 17-02-2012
	public String	ClientSql_PDC2_Header= "	Collection - Client Code Help";
	
	
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
	
	public String ClientSql_New                = " ";
	public String ClientSql_New_Header         = " Client Help "; //Added by Sandun on 06-08-08
	
	public String ClientSql_Receipt        ="";
	public String ClientSql_Receipt_Header ="Collection Help";
	
	public String ClientSql_Receipt_client        ="";  //inesh 2017-08-14 #23772
	public String ClientSql_Receipt_client_Header ="Collection Help";
	
	public String ReceiptSql               = " ";
	public String ReceiptSql_Header        = " Collection Help "; 
	
	
	public String AccountSql               = " ";
	public String AccountSql_Header        = " Collection Help "; 
	
	public String AccountSql_receipt        ="";
	public String AccountSql_receipt_Header ="Collection Help - Account Number ";
	
	public String ReceiptSql1               = " ";
	public String ReceiptSql1_Header        = " Collection Help "; 
	
	public String Receipt_cancel               = " ";
	public String Receipt_cancel_Header        = " Collection Help "; 
	
	public String m_help_TXT_DIVISION_CODE_sql="";
	public String m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_TRAN_CODE_sql= "";
	public String m_help_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
	
	public String m_help_TXT_BRANCH_CODE_sql_new= "";
	public String m_help_TXT_BRANCH_CODE_sql_new_Header= "System Administration - Branch Code";
	
	public String m_help_txt_branch_code_new_sql="";
	public String m_help_txt_branch_code_new_sql_Header= "System Administration - Branch Code";
	
	public String m_help_TXT_BANK_BRANCH_CODE_sql= "";
	public String m_help_TXT_BANK_BRANCH_CODE_sql_Header= "System Administration - Branch Code";
	
	public String ClientSql1                = " ";
	public String ClientSql1_Header         = " Client Code Help "; 
	
	public String m_help_TXT_FinanceSql_new2                = " ";
	public String m_help_TXT_FinanceSql_new2_Header         = " Finance Help "; 
	
	public String ClientSql2                = " ";
	public String ClientSql2_Header         = " Client Code Help "; 
	
	public String ClientSql_Lease ="";
	public String ClientSql_Lease_Header = " Client Code Help "; 
	
	public String m_help_client_code_sql                = " ";
	public String m_help_client_code_sql_Header         = " Client Code Help "; 
	
	public String m_help_TXT_FINANCE_NO_branch_sql        = "";
	public String m_help_TXT_FINANCE_NO_branch_sql_Header ="Finance Number Help"; //Added By Sandun For Collection Report - Branch 07-11-2008
	
	public String m_help_TXT_FINANCE_NO_branch_2_sql = ""; // added by udara 07-02-2014
	public String m_help_TXT_FINANCE_NO_branch_2_sql_Header ="Finance Number Help"; // added by udara 07-02-2014
	
	public String ReceiptSql_group               = " ";
	public String ReceiptSql_group_Header        = " Collection Help "; 
	
	public String ReceiptSql_group_1               = " ";
	public String ReceiptSql_group_1_Header        = " Collection Help ";
	
	public String m_help_guarator_code_colection = " ";
	public String m_help_guarator_code_colection_Header = "Guarantor Help";//Added By Sandun For Collection Report with Age for M/I
	
	public String m_help_TXT_FinanceSql_sql=" ";
	public String m_help_TXT_FinanceSql_sql_Header="Finance Help";//Added By Sandun on 06-10-2008 For Collection Due Reports - MISF
	
	public String 	FinanceSql_Termi =" ";
	public String 	FinanceSql_Termi_Header ="Terminated Contract Help";//Added By Sandun on 23-09-2009
	
	public String m_help_TXT_FINANCE_OFF_sql_new=" ";
	public String m_help_TXT_FINANCE_OFF_sql_new_Header="Finance Help";//Added By Sandun on 06-10-2008 
	
	//======Added by Dineth on 25-07-2008
	
	public String m_help_TXT_FINANCE_OFF_sql             = " ";
	public String m_help_TXT_FINANCE_OFF_sql_Header        = " Finance No Help "; 
	
	
	
	//===== End by Dineth
	
	public String ClientSql_Collection_Age        = ""; //Added By Sandun On 17-11-2008
	public String ClientSql_Collection_Age_Header = " Client Code Help - Collection Report With Age "; 
	
	public String ClientSql1_Insurance        = ""; //Added By Sandun On 02-03-2009
	public String ClientSql1_Insurance_Header = " Client Code Help - Insurance Officer Help"; 
	
	public String ClientSql_Ins_Officer_edit  ="";//Added By Sandun On 03-03-2009
	public String ClientSql_Ins_Officer_edit_Header  ="Client Code Help - Insurance Officer Help";
	
	public String ClientSql_POD_With        = ""; //Added By Sandun On 04-03-2009
	public String ClientSql_POD_With_Header = " Client Code Help - POD Withdrawal "; 
	
	public String ClientSql_POD_With_Edit        = ""; //Added By Sandun On 04-03-2009
	public String ClientSql_POD_With_Edit_Header = " Client Code Help - POD Withdrawal "; 
	
	public String ClientSql_Lease_new	 ="";//Added By Sandun On 25-05-2009
	public String ClientSql_Lease_new_Header="Client Help";
	
	public String ClientSql_Lease_new1 = ""; //Added By Udara On 25-05-2009
	public String ClientSql_Lease_new1_Header="Client Help";
	
	
	public String	ClientSql1_new			="";	//Added By Sandun On 25-05-2009
	public String ClientSql1_new_Header="Client Help";
	
	public String	ClientSql1_new2			="";	//Added By Udara on 05-10-2012
	public String ClientSql1_new2_Header="Client Help";
	
	public String ClientSql1_new3 = ""; //Added By Udara on 09-10-2012
	public String ClientSql1_new3_Header="Client Help";
	
	public String pod_ack_sql			   = "";	//Added By Sandun On 27-07-2009
	public String pod_ack_sql_Header = "PDC Help";
	
	public String m_help_active_employee_list_sql           = "";   // Added By Samitha Kulatilaka On 2012-01-25
	public String m_help_active_employee_list_sql_Header    = "Active Employees Help";
	
	public String m_help_coll_officer_list_sql="";
	public String m_help_coll_officer_list_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
	
	
	//Samith Dilshan on 2015-05-27
	public String FinanceSqlWithoutTermination         = " ";
	public String FinanceSqlWithoutTermination_Header  = "Finance Number"; 
	
	
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
			" WHERE (DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE (TRAN_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE SUB_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') AND MODEL_CODE LIKE UPPER('%" + m_vector.elementAt(1) + "%')  AND ACTIVE_STATUS=('" + m_vector.elementAt(2) + "') " +
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
			" WHERE (UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   )AND   "+
			"  ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
		
		
		
		InvoiceSql_debit_note = "SELECT P.NO, INVOICE_NO, FINANCE_NO, TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
			"    NET_AMOUNT,VAT_AMOUNT,TOTAL_AMOUNT,TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE,BALANCE_TO_BE_RECEIVED,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE,INVOICE_TYPE ,SUS_REF_NO "+
			"FROM "+
			"(SELECT ROWNUM NO, INVOICE_NO, FINANCE_NO, VALUE_DATE, "+
			"    NET_AMOUNT,VAT_AMOUNT,TOTAL_AMOUNT,DUE_DATE,BALANCE_TO_BE_RECEIVED,CLIENT,REMARKS,CURRENCY_CODE,EXCHANGE_RATE,INVOICE_TYPE ,SUS_REF_NO "+
			"FROM "+
			"(SELECT A.INVOICE_NO, A.FINANCE_NO, A.VALUE_DATE, "+
			"        A.NET_AMOUNT,A.VAT_AMOUNT,A.TOTAL_AMOUNT,A.DUE_DATE,A.BALANCE_TO_BE_RECEIVED,A.CLIENT_CODE CLIENT,  "+
			"        A.REMARKS,A.CURRENCY_CODE, A.EXCHANGE_RATE,A.INVOICE_TYPE , B.SUS_REF_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_INVOICE A  , "+m_schema_name+".af_re_acc_sus_payment  B "+
			" WHERE A.invoice_no=B.ref_no "+
			" AND  (INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=UPPER('"+m_vector.elementAt(1)+"') AND INVOICE_TYPE  IN('INSURANCE','LEGAL','REPOSSESS') "+
			" AND B.int_bal_settle_amount =0 "+
			" ORDER BY INVOICE_NO DESC,FINANCE_NO )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*m_help_txt_sub_team_sql=
        
        " SELECT L.NO ,L.SUB_TEAM_ID,L.SUB_TEAM_DESC,NVL(L.SUB_TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
        " FROM  "+
        " (SELECT ROWNUM NO,P.SUB_TEAM_ID,P.SUB_TEAM_DESC,P.SUB_TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
        " FROM( "+ 
        " SELECT "+
        " SUB_TEAM_ID, "+
        " SUB_TEAM_DESC, "+
        " SUB_TEAM_HEAD, "+
        " DIVISION_CODE, "+
        " SUB_DIVISION_CODE "+
        " FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS "+
        " WHERE ( UPPER(SUB_TEAM_ID) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(SUB_TEAM_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		
		
		m_help_txt_sub_team_sql=
			
			" SELECT L.NO ,L.SUB_TEAM_ID,L.SUB_TEAM_DESC,NVL(L.TEAM_ID,'N/A') AS TEAM_ID ,L.TEAM_DESC  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TEAM_ID,P.SUB_TEAM_DESC,P.TEAM_ID ,P.TEAM_DESC"+
			" FROM( "+ 
			"  SELECT "+
			"  B.SUB_TEAM_HEAD, "+
			"  C.TEAM_ID, "+
			"  B.SUB_TEAM_ID, "+
			"  SUB_TEAM_DESC, "+
			"  A.ACTIVE_STATUS ,"+
			"  C.TEAM_DESC "+
			"  FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN  A, "+m_schema_name+".AF_CO_MAS_SUB_TEAMS B , "+m_schema_name+".AF_CO_MAS_TEAMS C "+
			"  WHERE A.ACTIVE_STATUS='Y' "+
			"  AND A.SUB_TEAM_ID=B.SUB_TEAM_ID "+
			"  AND A.TEAM_ID=C.TEAM_ID "+
			"  AND C.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  AND B.SUB_TEAM_ID LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------------------------------------------------------
        
        Purpose     : Select The account code
        Used In     : Collection Process 
        --------------------------------------------------------------------*/
		
		m_help_Collection_process_Account_code=
			
			" SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE ,L.BANK_NAME,L.BRANCH_NAME,L.ACC_SYS_REFNO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACC_NO,p.BRANCH_CODE,P.BANK_NAME,p.BRANCH_NAME,P.ACC_SYS_REFNO "+
			" FROM( "+ 
			" SELECT "+
			" A.ACC_NO, "+
			" NVL(A.BRANCH_CODE,'-') BRANCH_CODE, "+
			" NVL(B.BRANCH_NAME,'-') BRANCH_NAME, "+
			" NVL(A.ACC_SYS_REFNO,'-') ACC_SYS_REFNO "+
			" ,"+m_schema_name+".AF_CO_GET_BANK_NAME(A.BRANCH_CODE) BANK_NAME "+
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
			" SELECT L.NO ,L.DIPOSIT_NO,L.DIPOSIT_DATE,L.AMOUNT,L.SETTLE_MODE,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.REFERENCE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIPOSIT_NO,p.DIPOSIT_DATE,P.AMOUNT,P.SETTLE_MODE,p.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.REFERENCE "+
			" FROM( "+ 
			" SELECT "+
			" DIPOSIT_NO, "+
			" TO_CHAR(DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE, "+
			" "+m_schema_name+".AF_CO_GET_DEPOSIT_AMOUNT(DIPOSIT_NO) AMOUNT ,"+
			" "+m_schema_name+".AF_CO_GET_SETTLE_MODE(DIPOSIT_NO) SETTLE_MODE, "+
			" ACC_NO, "+
			" BRANCH_CODE, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
			" REFERENCE "+
			" FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT "+
			" WHERE ( DIPOSIT_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR  "+
			"         UPPER(TO_CHAR(DIPOSIT_DATE,'YYYY-MM-DD')) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			"         AND  STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY   DIPOSIT_NO desc" +
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
			"      (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      (A.RECEIPT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      (DIPOSIT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      (RETURN_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      (D.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      UPPER(PAYER_BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"      (CHEQUE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			" UPPER(CITY_CODE)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (MOBILE_NO)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (TEL_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(EMAIL)      LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (NIC_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+ 
			" AND ACTIVE_STATUS=('" + m_vector.elementAt(1) + "') " + 
			" ORDER BY CLIENT_CODE DESC "+
			" )) P " + 
			" WHERE P.NO>= " + Start_Val + " AND P.NO<= " + End_Val + " ";
		
		
					GuarantorSql_add_gua=   		    	
			" SELECT P.NO, NVL(P.CLIENT_CODE,'-') Client, NVL(P.FULL_NAME,'-') Name, NVL(P.TEL_NO,'-') Tel, NVL(P.NIC_NO,'-') Nic,P.CLIENT_TYPE,NVL(P.BUSINESS_CERTIFICATE_NO,'-') REG_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,FULL_NAME, TEL_NO, NIC_NO,CLIENT_TYPE,BUSINESS_CERTIFICATE_NO "+
			"FROM "+
			"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CLIENT_TYPE,BUSINESS_CERTIFICATE_NO "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"  WHERE  "+
			"       ( UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=('Y') "+
			"				 AND AF_GUARANTORS='Y'	"+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
			
			
		m_help_TXT_CLIENT_CODE_GURAN =
			" SELECT P.NO, P.CLIENT_CODE CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			" FROM (SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			" FROM ( "+
			" SELECT DISTINCT A.CLIENT_CODE, "+
			" NVL(A.FIRST_NAME,'-') FIRST_NAME, "+
			" NVL(A.SURNAME,'-') SURNAME, "+
			" NVL(A.NIC_NO,'-') NIC_NO, "+
			" NVL(A.ADDRESS1,'-') ADDRESS1, "+
			" NVL(A.ADDRESS2,'-') ADDRESS2, "+
			" NVL(A.TEL_NO,'-') TEL_NO, "+
			" NVL(A.MOBILE_NO,'-') MOBILE_NO, "+
			" NVL(A.EMAIL,'-') EMAIL, "+
			" A.ACTIVE_STATUS, "+
			" A.TEMP_ACTIVE_STATUS, "+
			" NVL(A.CITY_CODE,'-') CITY_CODE "+
			" FROM " + m_schema_name + ".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B  "+
			" WHERE  (UPPER(A.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(A.FULL_NAME)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " +
			" UPPER(A.ADDRESS1)   LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(A.CITY_CODE)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (A.MOBILE_NO)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (A.TEL_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(A.EMAIL)      LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (A.NIC_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(A.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+ 
			" AND A.ACTIVE_STATUS=('" + m_vector.elementAt(1) + "') AND B.GUARANTOR_CODE<>A.CLIENT_CODE   " + 
			" ORDER BY A.CLIENT_CODE DESC "+
			" )) P " + 
			" WHERE P.NO>= " + Start_Val + " AND P.NO<= " + End_Val + " ";
		
		//m_help_TXT_CLIENT_CODE = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category,NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, P.ADDRESS1, P.ADDRESS2, P.CITY FROM (SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY,ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY,ACTIVE_STATUS, TEMP_ACTIVE_STATUS " + "\t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO' AND " + "       ( FULL_NAME LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t       BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%')) AND " + "        ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " + " ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + Start_Val + " AND L.NO<= " + s2 + " ";
		
		//added by nuwan de silva on 08-11-07
		m_help_client_code_help =
			" SELECT P.NO, P.CLIENT_CODE CLIENT_CODE, FULL_NAME, NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			" FROM (SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME, NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			" FROM ( "+
			" SELECT CLIENT_CODE, "+
			" NVL(FULL_NAME,'-') FULL_NAME, "+
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
			" UPPER(CITY_CODE)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (MOBILE_NO)  LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (TEL_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(EMAIL)      LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" (NIC_NO)     LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR " + 
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+ 
			" AND ACTIVE_STATUS=('" + m_vector.elementAt(1) + "') " + 
			" ORDER BY CLIENT_CODE DESC "+
			" )) P " + 
			" WHERE P.NO>= " + Start_Val + " AND P.NO<= " + End_Val + " ";
		
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
			" WHERE (UPPER(BANK_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*--------------------------------------------------------------------------------------------------------------
        Purpose  :Selecting Branch Code
        used In  :Collcetion Temp Receipt
        ----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_BRANCH_CODE_sql=
			" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,"+
			//		" L.ADDRESS1, NVL(L.ADDRESS2,'-'), NVL(L.CITY_CODE,'-'),NVL(L.TEL_NO,'-'),NVL(L.FAX_NO,'-'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE,"+
			" L.BANK_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,"+
			//		" P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE,"+
			" P.BANK_NAME"+
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
			" DEFAULT_VALUE, "+
			" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
			" WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		m_help_txt_branch_code_new_sql=
			" SELECT L.NO ,L.BRANCH_CODE \"Branch Code\" ,L.BRANCH_NAME \"Branch Name\",L.BANK_NAME \"Bank Name\",L.ADDRESS1 \"Address Line 1\", NVL(L.ADDRESS2,'-') \"Address Line 2\", NVL(L.CITY_CODE,'-') \"City Code\",NVL(L.TEL_NO,'-') \"Tel No.\",NVL(L.FAX_NO,'-') \"Fax No.\",L.DAYS_TO_REALISE \"Days To Realize\",L.DEFAULT_VALUE \"Default Value\",L.BANK_CODE \"Bank Code\" "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE,P.BANK_CODE"+
			" FROM( "+ 
			" SELECT "+
			" BRANCH_CODE,"+
			" BRANCH_NAME,"+
			" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME ,"+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE,"+
			" TEL_NO,"+
			" FAX_NO,"+
			" DAYS_TO_REALISE, "+
			" DEFAULT_VALUE, "+
			" BANK_CODE"+
			" FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
			" WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*--------------------------------------------------------------------------------------------------------------
        Purpose  :Selecting Branch Code
        used In  :Collcetion Temp Receipt
        ----------------------------------------------------------------------------------------------------------------*/
		m_help_TXT_BANK_BRANCH_CODE_sql=
			" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_NAME,L.ADDRESS1, NVL(L.ADDRESS2,'-'), NVL(L.CITY_CODE,'-'),NVL(L.TEL_NO,'-'),NVL(L.FAX_NO,'-'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE,L.BANK_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE,P.BANK_CODE"+
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
			" DEFAULT_VALUE, "+
			" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME "+
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
			" WHERE A.BRANCH_CODE=B.BRANCH_CODE AND B.BANK_CODE=C.BANK_CODE AND A.ACC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		
		
		
		
		m_help_collection_officer_new=
			
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
			" )  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" AND UPPER(USER_ID)!=UPPER('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_EMP_CODE_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT "+
			" EMP_CODE,"+
			" TITLE,"+
			" FIRST_NAME,"+
			" LAST_NAME,"+
			" ADDRESS,"+
			" LOCATION_CODE,"+
			" AREA_CODE,"+
			" CITY_CODE,"+
			" CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" DIVISION_CODE,"+
			" EPF_NO,"+
			" ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			" WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_employee_id_new=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT "+
			" EMP_CODE,"+
			" TITLE,"+
			" FIRST_NAME,"+
			" LAST_NAME,"+
			" ADDRESS,"+
			" LOCATION_CODE,"+
			" AREA_CODE,"+
			" CITY_CODE,"+
			" CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" DIVISION_CODE,"+
			" EPF_NO,"+
			" ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			" WHERE ( UPPER(EMP_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" AND UPPER(EMP_CODE)!=UPPER('"+m_vector.elementAt(1)+"') "+
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
			"                         AND    (B.CLIENT_CODE)         LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			"                         AND    UPPER(B.COLLECTION_OFFICER)  LIKE UPPER('%"+m_vector.elementAt(2)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_marketing_officer=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     (A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
			
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// commented by udara 11-03-2014
		/*
		//added milinda
		m_help_credi_officer=
			" SELECT L.NO ,L.CR_OFFICER,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CR_OFFICER,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT DISTINCT NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO),'-') CR_OFFICER, "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		// added by udara 11-03-2014
		/*
		m_help_credi_officer=
			" SELECT L.NO ,L.CR_OFFICER,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CR_OFFICER,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			//" SELECT DISTINCT NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO),'-') CR_OFFICER, "+
			" SELECT DISTINCT A.EMP_CODE CR_OFFICER, "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
			
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		*/
		
		// added by udara 17-03-2014
		m_help_credi_officer=
			" SELECT L.NO ,L.CR_OFFICER,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CR_OFFICER,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			
			" SELECT DISTINCT C.CR_OFFICER CR_OFFICER, "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B, "+m_schema_name+".AF_MK_PRO_INQUIRY C "+
			" WHERE A.EMP_CODE=C.CR_OFFICER "+
			" AND B.INQUARY_NO = C.INQUIRY_CODE "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			
			
			" UNION "+
			
			" SELECT DISTINCT A.EMP_CODE CR_OFFICER, "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			
			
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// added by udara 12-04-2017
		m_help_item_category_help =
			" SELECT L.NO ,L.ITEM_CAT_CODE,L.DESCRIPTION,L.VAT_APP,L.VAT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_CAT_CODE,P.DESCRIPTION,P.VAT_APP,P.VAT,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_CAT_CODE, "+
			" DESCRIPTION, "+
			" NVL(VAT_APP,0) VAT_APP ,"+
			" NVL(VAT,0) VAT ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
			//   " WHERE (ITEM_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+  //comment by prabash on 03-02-2012
			" WHERE (UPPER(ITEM_CAT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+   // Added by Prabash on 03-02-2012
			" AND ITEM_CAT_CODE <> 'INS'   "+ // added by udara 04-05-2017
			" ORDER BY ITEM_CAT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_item_sub_category_help =
			
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.DESCRIPTION,L.ITEM_CAT_CODE,L.VAT_APP,L.VAT,L.DEFAULT_VALUE,L.CAPITAL_ALLOWANCE,L.CAP_ALLO_EFF_FATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.DESCRIPTION,P.ITEM_CAT_CODE,P.VAT_APP,P.VAT,P.DEFAULT_VALUE,P.CAPITAL_ALLOWANCE,P.CAP_ALLO_EFF_FATE  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT ,"+
			" DESCRIPTION, "+
			" ITEM_CAT_CODE, "+
			" NVL(VAT_APP,0) VAT_APP ,"+
			" NVL(VAT,0) VAT ,"+
			" DEFAULT_VALUE, "+
			"	NVL(CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE,"+
			"	NVL(TO_CHAR(CAP_ALLO_EFF_FATE,'DD-MM-YYYY'),'-') CAP_ALLO_EFF_FATE"+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			//  " WHERE (ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ //coment by Prabash on 03-02-2012
			" WHERE (UPPER(ITEM_SUB_CAT) LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			//" AND  ITEM_CAT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+ // commented by udara 04-05-2017
			" AND  UPPER(ITEM_CAT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"')  "+ // added by udara 04-05-2017
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+  //Added by Prabash on 03-02-2012
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 12-04-2017
		
		
		
		
		m_help_credi_officer_re=
			" SELECT L.NO ,L.CR_OFFICER,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CR_OFFICER,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT DISTINCT NVL("+m_schema_name+".AF_CO_GET_CR_OFFICER(B.INQUARY_NO),'-') CR_OFFICER, "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			" AND UPPER(A.LOCATION_CODE)   LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*	    //added by nuwan de silva on 14-07-09-------------------------------------------------------------------
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
        
        
            "		AND USER_ID        IN( SELECT DISTINCT COLLECTION_OFFICER "+
            "													FROM   "+m_schema_name+".AF_MK_PRO_INQUIRY  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
            "													WHERE  B.INQUARY_NO=A.INQUIRY_CODE "+
            "                         AND    UPPER(B.CLIENT_CODE)         LIKE UPPER('"+m_vector.elementAt(1)+"%')  "+
      "                         AND    UPPER(A.MK_OFFICER)          LIKE UPPER('"+m_vector.elementAt(2)+"%') ) "+

            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
            */
		
		m_help_collection_officer_colection=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
			" AND ("+
			"     UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_guarator_code_colection = " SELECT L.NO ,L.GUARANTOR_CODE,/*L.CLIENT_CODE,*/L.RELATIONSHIP,L.TEL_NO,L.NIC_NO,L.BUSINESS_NO "+
			" FROM  "+    
			" (SELECT ROWNUM NO,P.GUARANTOR_CODE,/*P.CLIENT_CODE,*/P.RELATIONSHIP,P.TEL_NO,P.NIC_NO,P.BUSINESS_NO "+
			" FROM(  "+
			" SELECT "+  
			" DISTINCT A.GUARANTOR_CODE,"+
			//" B.CLIENT_CODE ,"+
			" NVL(A.RELATIONSHIP,'-') RELATIONSHIP,"+
			" NVL(A.TEL_NO,'-') TEL_NO,	"+	
			" NVL(C.NIC_NO,'-') NIC_NO, "+
			" NVL(C.BUSINESS_CERTIFICATE_NO,'-') BUSINESS_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B ,"+
			" "+m_schema_name+".AF_CO_MAS_CLIENT C "+
			" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
			" AND   A.GUARANTOR_CODE = C.CLIENT_CODE "+
			" AND( (A.GUARANTOR_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" OR (C.NIC_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" OR UPPER(C.BUSINESS_CERTIFICATE_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			" AND (B.CLIENT_CODE)      LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";		//Added By Sandun For Collection Report with Age-Gurantor Help for M/I
		
		
		
		
		//added by nuwan de silva on 20-09-07------------------------------------------------------------
		/*	m_help_marketing_officer=
        
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
            */
		
		//===========================================================================================================
		
		
		/*--------------------------------------------------------------------------------------------------------------
        Purpose  :Selecting Receipt Number
        used In  :Collcetion Temp Receipt
        ----------------------------------------------------------------------------------------------------------------*/
		
		m_help_TXT_TEMP_REC_NO_sql=
			" SELECT L.NO ,L.TEMP_REC_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TRN_DATE,L.AMOUNT,L.SETTELMENT_MODE,L.BRANCH_CODE,L.BANK_CODE,L.ACCOUNT_NO,L.CURR_CODE,L.EXCHANGE_RATE,L.TRN_AMOUNT_CURR,L.COLLECTION_OFFICER,L.RECEIPT_NO,L.REC_BOOK_NO,L.CHEQUE_NO,L.BRANCH_NAME, "+
			" L.RENTAL_OTER_INVOICE,L.INSURANCE,L.LUXURY_TAX,L.REVANUE_LICENCE,L.RMV_CHARGES,L.VALUE_DATE,L.CHEQUE_DATE,L.THIRD_PARTY_NAME,L.THIRD_PARTY_ADD,L.CLIENT_ADD "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEMP_REC_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TRN_DATE,P.AMOUNT,P.SETTELMENT_MODE,P.BRANCH_CODE,P.BANK_CODE,P.ACCOUNT_NO,P.CURR_CODE,P.EXCHANGE_RATE,P.TRN_AMOUNT_CURR,P.COLLECTION_OFFICER,P.RECEIPT_NO,P.REC_BOOK_NO,P.CHEQUE_NO,P.BRANCH_NAME, "+
			" P.RENTAL_OTER_INVOICE,P.INSURANCE,P.LUXURY_TAX,P.REVANUE_LICENCE,P.RMV_CHARGES,P.VALUE_DATE,P.CHEQUE_DATE,P.THIRD_PARTY_NAME,P.THIRD_PARTY_ADD,P.CLIENT_ADD "+
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
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME, "+
			" NVL(A.RENTAL_OTER_INVOICE,0) RENTAL_OTER_INVOICE, "+
			" A.INSURANCE, "+
			" A.LUXURY_TAX, "+
			" A.REVANUE_LICENCE, "+
			" A.RMV_CHARGES, "+
			" TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
			" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+	
			" NVL(A.THIRD_PARTY_NAME,'-') THIRD_PARTY_NAME, "+
			" NVL(A.THIRD_PARTY_ADD,'-') THIRD_PARTY_ADD, "+    
			" NVL(A.CLIENT_ADD,'-') CLIENT_ADD "+
			" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A, "+
			"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.TEMP_REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
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
			" WHERE CURR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  A.SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
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
			" WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO AND UPPER(A.REPOSSESSION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			"		        (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"		        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"		        B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"		        C.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR "+
			"		        C.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			"		        D.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			"		        C.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			
			"		        )  "+
			"             AND A.APPLICATION_STATUS NOT IN ('TERMI','TERMINATED','NORM_TERMI')     "+ // added by udara 29-08-2016
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
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			//" A.SEIZER_CODE,  "+
			" DECODE(B.REPOSSESS_TYPE,'OFFICER',B.REPOSSESS_OFFICER,'SEIZER',B.SEIZER_CODE,'COMPANY',B.REPOSSESS_OFFICER)  SEIZER_CODE,"+//Added By Sandun on 03-07-2009
			" "+
			" B.FINANCE_NO,  "+
			//" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+
			" NVL(DECODE(B.REPOSSESS_TYPE,'OFFICER',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER),'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_NAME(B.SEIZER_CODE),'COMPANY',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER)),'-') SEIZER_NAME , "+ ////Added By Sandun on 02-07-2009
			//" "+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE) SEIZER_FEE "+
			" DECODE(B.REPOSSESS_TYPE,'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_FEE(B.SEIZER_CODE),0)  SEIZER_FEE "+//Mod By Sandun on 03-07-2009
			" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
			" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
			"  ) C  "+
			"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
			//"  AND UPPER(A.REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(1)+"')  AND UPPER(A.INVENTORY_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
			"  AND   "+
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  A.SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			"  ) AND  "+
			"  A.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
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
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  A.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  ) "+
			"  AND A.COMPLETED_OFFER_NO IS NULL "+
			"  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  ORDER BY A.INVENTORY_NO DESC  "+
			"  )P)L  "+
			
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_FIN_SQL =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO ,CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) NAME"+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   APPLICATION_STATUS IN  ( 'ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL' ) "+
			" AND (A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR (A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY A.APPLICATION_NO DESC)) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
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
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  A.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
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
			" (B.INVENTORY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			" WHERE ADVETIST_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			"  WHERE (REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"')))    "+
			// "  AND PURCHASE_ORDER_NO IS NOT NULL "+  // Commented By SJ on 26-11-2008
			"  AND UPPER(VEHICLE_NO) NOT IN ( "+
			"  SELECT UPPER(VEHICLE_NO) "+
			"  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+ 
			"  WHERE (REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"')) "+
			"  AND( UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  OR "+
			"       UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  OR "+
			"       UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			
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
			" WHERE (REPOSSESSION_NO)=UPPER('"+m_vector.elementAt(0)+"') AND  ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')) "+
			" AND UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')   "+
			
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
			"    (UPPER(A.VEHICLE_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  OR"+
			"    UPPER(C.CHASSIS_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  OR"+
			"    UPPER(C.ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) AND "+
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
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			"  D.ADVER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    "+
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
			"  (UPPER(C.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  A.INVENTORY_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			"  B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			"  D.ADVER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')    "+
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
			" (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+   
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
			" WHERE USER_NAME LIKE('"+m_vector.elementAt(1)+"%') AND  RECEIPT_BOOK_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_FINANCE_NO_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.CLIENT_ADD "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.CLIENT_ADD "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" B.TEL_NO TEL_NO,  "+
			" B.NIC_NO NIC_NO,  "+
			" NVL(B.ADDRESS1,' ')||' '||NVL(B.ADDRESS2,' ')||' '||NVL(B.CITY_CODE,' ') CLIENT_ADD "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,V.CITY_CODE,V.ADDRESS1,V.ADDRESS2  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_sub_charge_payee_code=
			
			" SELECT L.NO,L.PAYEE_CODE,L.SUB_TYPE_CODE,L.PAYEE_NAME,L.PAYEE_ADDRESS,L.PAYEE_ADDRESS_2,L.CITY_CODE,L.PAYEE_VAT_REG_NO,L.PAYEE_WHT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYEE_CODE,P.SUB_TYPE_CODE,P.PAYEE_NAME,P.PAYEE_ADDRESS,P.PAYEE_ADDRESS_2,P.CITY_CODE,P.PAYEE_VAT_REG_NO,P.PAYEE_WHT "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE, "+
			" PAYEE_CODE, "+
			" PAYEE_NAME, "+
			" PAYEE_ADDRESS, "+
			" PAYEE_ADDRESS_2, "+
			" CITY_CODE, "+
			" PAYEE_VAT_REG_NO, "+
			" PAYEE_WHT "+
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
			" WHERE (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR   UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			" AND   UPPER(SUB_TYPE_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')"+
			" AND ACTIVE_STATUS='Y' "+//ADDED MILINDA 2014-01-29
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//Added by Dineth on 2008-10-01
		m_help_TXT_PAYEE_CODE_sql=
			
			" SELECT L.NO,L.PAYEE_CODE,L.SUB_TYPE_CODE,L.PAYEE_NAME,L.PAYEE_ADDRESS,L.PAYEE_ADDRESS_2,L.CITY_CODE,L.PAYEE_VAT_REG_NO,L.PAYEE_WHT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYEE_CODE,P.SUB_TYPE_CODE,P.PAYEE_NAME,P.PAYEE_ADDRESS,P.PAYEE_ADDRESS_2,P.CITY_CODE,P.PAYEE_VAT_REG_NO,P.PAYEE_WHT "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE, "+
			" PAYEE_CODE, "+
			" PAYEE_NAME, "+
			" PAYEE_ADDRESS, "+
			" PAYEE_ADDRESS_2, "+
			" CITY_CODE, "+
			" PAYEE_VAT_REG_NO, "+
			" PAYEE_WHT "+
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
			" WHERE (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR   UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//End by Dineth on 2008-10-01
		//Added by Dineth on 2008-10-09
		m_help_TXT_PAYEE_CODE_sql1=
			
			" SELECT L.NO,L.PAYEE_CODE,L.SUB_TYPE_CODE,L.PAYEE_NAME,L.PAYEE_ADDRESS,L.PAYEE_ADDRESS_2,L.CITY_CODE,L.PAYEE_VAT_REG_NO,L.PAYEE_WHT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYEE_CODE,P.SUB_TYPE_CODE,P.PAYEE_NAME,P.PAYEE_ADDRESS,P.PAYEE_ADDRESS_2,P.CITY_CODE,P.PAYEE_VAT_REG_NO,P.PAYEE_WHT "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE, "+
			" PAYEE_CODE, "+
			" PAYEE_NAME, "+
			" PAYEE_ADDRESS, "+
			" PAYEE_ADDRESS_2, "+
			" CITY_CODE, "+
			" PAYEE_VAT_REG_NO, "+
			" PAYEE_WHT "+
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
			" WHERE (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR   UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			" AND   UPPER(SUB_TYPE_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')"+ 
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//End by Dineth on 2008-10-09
		m_help_finance_no_post_dated_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.REMARKS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.REMARKS "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT C.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" B.TEL_NO TEL_NO,  "+
			" B.NIC_NO NIC_NO  "+
			" , "+m_schema_name+".AF_CO_GET_POD_REMARKS(C.FINANCE_NO) REMARKS "+ //added by nuwan de silva on 10-04-2008
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_RE_PRO_POD_CHEQUES C ,"+
			"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO AND A.FINANCE_NO=C.FINANCE_NO   "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
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
			"        (TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND "+
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
			" (A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			" AND ((A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) ORDER BY FINANCE_NO DESC   "+
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
			"        (A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			" WHERE UPPER(LEGAL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ 
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
			" WHERE (CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(CITY_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE (YARD_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			"        (UPPER(REC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       (B.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			//"        A.STATUS='E' "+  
			"        A.STATUS NOT IN ('CAD','C') "+  
			
			" ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		
		m_help_TXT_USERS_with_loc_sql=
			
			" SELECT L.NO ,L.USER_ID,L.NAME,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE"+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE "+ 
			
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			//  " WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+   //Added by Prabsh on 03-02-2012
			"  AND LOCATION_CODE = '"+m_vector.elementAt(1)+"'"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara 27-11-2013
		
		m_help_TXT_USERS_with_loc_2_sql=
			
			" SELECT L.NO ,L.USER_ID,L.NAME,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE"+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE "+ 
			
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			//  " WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+   //Added by Prabsh on 03-02-2012
			"  AND LOCATION_CODE LIKE '"+m_vector.elementAt(1)+"%'"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 27-11-2013
		
		m_help_TXT_USERS_sql=
			
			" SELECT L.NO ,L.USER_ID,L.NAME,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE"+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE "+ 
			
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			//  " WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+   //Added by Prabsh on 03-02-2012
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara on 27-02-2013
		
		m_help_TXT_BRANCH_sql=
			" SELECT L.NO,L.LOCATION_CODE,L.LOCATION_DESC FROM ("+
			" SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC "+
			//" SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.USER_ID,L.USER_NAME  "+
			" FROM  "+
			" ( "+ 
			//" SELECT ROWNUM NO,"+
			" SELECT  DISTINCT"+
			" A.LOCATION_CODE LOCATION_CODE, "+
			" B.LOCATION_DESC LOCATION_DESC "+
			//" A.USER_ID USER_ID, "+
			//" "+m_schema_name+".AF_CO_GET_USER_NAME(A.USER_ID) USER_NAME "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER A, "+m_schema_name+".AF_CO_MAS_LOCATION B "+
			" WHERE A.LOCATION_CODE = B.LOCATION_CODE "+
			" AND B.ACTIVE_STATUS = 'Y' "+
			" AND ( "+
			" UPPER(A.LOCATION_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(B.LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.USER_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_USER_NAME(A.USER_ID)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara on 27-02-2013
		
		// added by udara 17-10-2013
		m_help_TXT_BRANCH_2_sql=
			" SELECT L.NO,L.LOCATION_CODE,L.LOCATION_DESC FROM ("+
			" SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC "+
			//" SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.USER_ID,L.USER_NAME  "+
			" FROM  "+
			" ( "+ 
			//" SELECT ROWNUM NO,"+
			" SELECT  DISTINCT"+
			" B.LOCATION_CODE LOCATION_CODE, "+
			" B.LOCATION_DESC LOCATION_DESC "+
			//" A.USER_ID USER_ID, "+
			//" "+m_schema_name+".AF_CO_GET_USER_NAME(A.USER_ID) USER_NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_LOCATION B "+
			" WHERE B.ACTIVE_STATUS = 'Y' "+
			" AND ( "+
			" UPPER(B.LOCATION_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(B.LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 17-10-2013
		
		
		Receipt_cancel = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
			"FROM "+
			"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
			"FROM "+
			"(SELECT A.REC_NO,A.REC_AMOUNT,NVL(A.CHEQUE_NO,'-') CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,NVL(A.PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE,NVL(A.PAYER_ACC_NO,'-') PAYER_ACC_NO,NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
			"        (REC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        A.STATUS ='CAD' "+  
			" ORDER BY A.ENT_DATE DESC,A.EFF_VALDATE,B.FULL_NAME )) P "+		// ENT_DATE REC_NO
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
			"        UPPER(B.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (B.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			//"        A.STATUS='E' "+
			"        A.STATUS NOT IN ('CAD','C') "+  
			" ORDER BY A.ENT_DATE DESC,A.EFF_VALDATE,B.FULL_NAME )) P "+		// ENT_DATE REC_NO
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
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
			" WHERE ACCOUNT_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')   AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		AccountSql_receipt = " SELECT L.NO ,L.ACCOUNT_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.REFERENCE,L.BANK_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.REFERENCE,P.BANK_NAME "+
			" FROM( "+ 
			"	SELECT "+
			" ACCOUNT_NO, "+
			" BRANCH_CODE, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
			" REFERENCE, "+
			" "+m_schema_name+".AF_CO_GET_BANK_NAME(BRANCH_CODE) BANK_NAME "+
			"	FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS "+
			" WHERE (UPPER(ACCOUNT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
			" AND (CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')    AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
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
		m_help_Repossseion_Help =" SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.SEIZER_NAME ,L.SEIZER_FEE,L.VEHICLE_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.SEIZER_NAME,P.SEIZER_FEE,P.VEHICLE_NO "+
			" FROM( "+ 
			" SELECT  "+
			" DISTINCT A.REPOSSESSION_NO, "+
			//	" NVL(A.SEIZER_CODE,'-') SEIZER_CODE , "+
			" DECODE(A.REPOSSESS_TYPE,'OFFICER',A.REPOSSESS_OFFICER,'SEIZER',A.SEIZER_CODE,'COMPANY',A.REPOSSESS_OFFICER)  SEIZER_CODE,"+//Sandun on 03-07-2009
			" A.FINANCE_NO, "+
			" B.CLIENT_CODE, "+
			" NVL(D.REG_NO,'-') VEHICLE_NO,"+//Added by Sandun on 12-08-2008
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME, "+
			//" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+
			//" NVL(C.FIRST_NAME || ' ' || C.LAST_NAME,'-') SEIZER_NAME ,  "+	 //ADDED BY NUWAN DE SILVA 13-06-07 -----Commented by Sandun on 03-07-2009
			" NVL(DECODE(A.REPOSSESS_TYPE,'OFFICER',"+m_schema_name+".AF_CO_GET_EMP_NAME(A.REPOSSESS_OFFICER),'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'COMPANY',"+m_schema_name+".AF_CO_GET_EMP_NAME(A.REPOSSESS_OFFICER)),'-') SEIZER_NAME , "+ ////Added By Sandun on 03-07-2009
			//" "+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE) SEIZER_FEE "+
			" DECODE(A.REPOSSESS_TYPE,'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE),0)  SEIZER_FEE "+//Mod By Sandun on 03-07-2009
			" FROM "+
			" "+m_schema_name+".AF_RE_PRO_REPOSSESSION  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,   "+
			" "+m_schema_name+".AF_CO_MAS_SEIZER  C ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D"+  //Added by Sandun on 12-08-2008  				
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND "+
			" B.APPLICATION_NO=D.APPLICATION_NO AND "+
			" A.SEIZER_CODE=C.SEIZER_CODE(+) "+
			" AND   "+
			" (UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (UPPER(C.FIRST_NAME || ' ' || C.LAST_NAME))LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR"+//Added by Sandun on 12-08-2008
			"	D.REG_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ //Added by Sandun on 12-08-2008
			"  )  "+
			" AND A.ACTIVE_STATUS='Y' "+
			
			/*" AND APPLICATION_NO IN "+
            " (SELECT "+
            "  APPLICATION_NO "+
            "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
            "  WHERE     ACTIVE_STATUS ='VERIFY') "+
            */
			
			//"  AND B.APPLICATION_STATUS NOT IN ('TERMI','TERMINATED','NORM_TERMI')     "+ // added by udara 29-08-2016
			
			
			" ORDER BY A.REPOSSESSION_NO DESC   "+
			" )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_Repossseion_Help1 =" SELECT L.NO ,L.REPOSSESSION_NO,L.SEIZER_CODE,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.SEIZER_NAME ,L.SEIZER_FEE,L.VEHICLE_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.SEIZER_CODE,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.SEIZER_NAME,P.SEIZER_FEE,P.VEHICLE_NO "+
			" FROM( "+ 
			" SELECT  "+
			" DISTINCT A.REPOSSESSION_NO, "+
			//	" NVL(A.SEIZER_CODE,'-') SEIZER_CODE , "+
			" DECODE(A.REPOSSESS_TYPE,'OFFICER',A.REPOSSESS_OFFICER,'SEIZER',A.SEIZER_CODE,'COMPANY',A.REPOSSESS_OFFICER)  SEIZER_CODE,"+//Sandun on 03-07-2009
			" A.FINANCE_NO, "+
			" B.CLIENT_CODE, "+
			" NVL(D.VEHICLE_NO,'-') VEHICLE_NO,"+//Added by Sandun on 12-08-2008
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME, "+
			//" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME, "+
			//" NVL(C.FIRST_NAME || ' ' || C.LAST_NAME,'-') SEIZER_NAME ,  "+	 //ADDED BY NUWAN DE SILVA 13-06-07 -----Commented by Sandun on 03-07-2009
			" NVL(DECODE(A.REPOSSESS_TYPE,'OFFICER',"+m_schema_name+".AF_CO_GET_EMP_NAME(A.REPOSSESS_OFFICER),'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE),'COMPANY',"+m_schema_name+".AF_CO_GET_EMP_NAME(A.REPOSSESS_OFFICER)),'-') SEIZER_NAME , "+ ////Added By Sandun on 03-07-2009
			//" "+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE) SEIZER_FEE "+
			" DECODE(A.REPOSSESS_TYPE,'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE),0)  SEIZER_FEE "+//Mod By Sandun on 03-07-2009
			" FROM "+
			" "+m_schema_name+".AF_RE_PRO_REPOSSESSION  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,   "+
			" "+m_schema_name+".AF_CO_MAS_SEIZER  C, "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY D "+  //Added by Sandun on 12-08-2008  				
			" WHERE A.FINANCE_NO=B.FINANCE_NO AND "+
			//" B.APPLICATION_NO=D.APPLICATION_NO AND "+
			" A.REPOSSESSION_NO=D.REPOSSESSION_NO AND" +
			" D.ACTIVE_STATUS='APP' AND "+
			" A.SEIZER_CODE=C.SEIZER_CODE(+) "+
			" AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(A.FINANCE_NO) = 'N'   "+
			" AND   "+
			" (UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.REPOSSESSION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (UPPER(C.FIRST_NAME || ' ' || C.LAST_NAME))LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR"+//Added by Sandun on 12-08-2008
			"	D.VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ //Added by Sandun on 12-08-2008
			"  )  "+
			" AND A.ACTIVE_STATUS IN ('Y','C') "+
			
			/*" AND APPLICATION_NO IN "+
            " (SELECT "+
            "  APPLICATION_NO "+
            "  FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
            "  WHERE     ACTIVE_STATUS ='VERIFY') "+
            */
			" ORDER BY A.REPOSSESSION_NO DESC   "+
			" )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_Reposssed_Client_Help =" SELECT L.NO ,L.CLIENT_CODE,L.FINANCE_NO,L.FULL_NAME "+  //Added By Sandun on 28-08-2008
			" FROM( "+
			" SELECT ROWNUM NO ,P.CLIENT_CODE,P.FINANCE_NO,P.FULL_NAME "+
			" FROM( "+
			" SELECT DISTINCT B.CLIENT_CODE, "+
			" B.FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) FULL_NAME "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.FINANCE_NO = B.FINANCE_NO AND A.ACTIVE_STATUS='Y' "+
			" AND (UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			" A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//" ORDER BY B.CLIENT_CODE "+
			" )P)L "+
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
		
		/*	//Modified by Mahela on 23-05-2007 
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
            */
		
		
		//added by nuwan de silva on 19-11-2007----------------
		
		m_help_Finance_Repossession_Sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.REG_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.REG_NO "+
			" FROM( "+ 
			" SELECT  "+
			" DISTINCT A.FINANCE_NO FINANCE_NO,  "+
			" A.CLIENT_CODE CLIENT_CODE,  "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME"+
			",B.REG_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND  B.REG_NO IS NOT NULL "+
			" AND A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL','REPOSSESS')    "+
			//" AND  A.FINANCE_NO NOT IN  "+
			//" ( SELECT FINANCE_NO FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION  WHERE VEHICLE_INVENTORY_STATUS='ENTERED' AND ACTIVE_STATUS <> 'C') 				  "+
			//" AND A.APPLICATION_STATUS='ACTIVATED'     "+
			
			//" AND NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'LOT'),'N')='Y' "+ -- comment by nuwan
			" AND  "+
			" (A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR "+
			" (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			" UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" ) "+
			
			// commented by udara 06-08-2015
			/*
			" AND B.invoice_no NOT IN ( "+
			" (SELECT a.pro_invoice_no "+
			" FROM "+m_schema_name+".af_re_pro_repossession a "+
			" WHERE  A.PRO_INVOICE_NO IS NOT NULL AND ACTIVE_STATUS NOT IN ('R') "+
			" AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(a.FINANCE_NO) = 'N'"+
			" )"+
			" ) "+
			*/
			
			// added by udara 06-08-2015
			" AND A.FINANCE_NO NOT IN ( "+
			" (SELECT FINANCE_NO "+
			" FROM "+m_schema_name+".af_re_pro_repossession "+
			//" WHERE  ACTIVE_STATUS NOT IN ('Y','C') "+
			" WHERE FINANCE_NO = A.FINANCE_NO  "+
			" AND   ACTIVE_STATUS IN ('Y') "+ // mod by udara 07-08-2015 " AND   ACTIVE_STATUS IN ('Y','C') "+
			//" AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD(FINANCE_NO) = 'N'"+
			" )"+
			" ) "+
			// end by udara 06-08-2015
			
			
			// "  ORDER BY A.FINANCE_NO DESC  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_Finance_Repossessed_Sql=" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME "+ //Added by Sandun on 28-08-2008
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM(  "+
			" SELECT "+
			" DISTINCT A.FINANCE_NO FINANCE_NO, "+ 
			" A.CLIENT_CODE CLIENT_CODE , "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
			" WHERE A.FINANCE_NO = B.FINANCE_NO		"+
			" AND NVL("+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(A.FINANCE_NO,'LOT'),'N')='Y' "+
			" AND (A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" )P)L  "+
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
			//	"        AND APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','REPOSSESS','TERM_TO','TERMI')  "+
			//"        AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL','TERM_TO','TERMI','TERMINATED')  "+//Mod by Sandun on 18-02-2009
			"        AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL')  "+//Mod by ns 30-06-2009
			" ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		//Added By: Samith Dilshan on 2015-05-27
		//Purpose  : select Finance Number -> Records not in 'AF_CR_PRO_TERMINATION' table
        //Used in  : Collection Other Invoices
     		
		FinanceSqlWithoutTermination = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO, A.CLIENT_CODE, "+
			"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,A.CURRENCY_CODE "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"        AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL')  "+
			"        AND A.FINANCE_NO NOT IN (SELECT DISTINCT B.FINANCE_NO FROM AF_CR_PRO_TERMINATION B WHERE B.ACTIVE_STATUS != 'CANCEL' ) "+
			" ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		//Added by Dineth on 24-03-2009
		FinanceSql2 =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO, A.CLIENT_CODE, "+
			"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT_NAME,A.CURRENCY_CODE "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//	"        AND APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','REPOSSESS','TERM_TO','TERMI')  "+
			"        AND APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL','NORM_TERMI','TERMI','TERMINATED')  "+//Mod by Sandun on 18-02-2009
			" ORDER BY APPLICATION_NO DESC,CLIENT_CODE )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		//End by Dineth on 24-03-2009
		
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
			"        OR TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // removed upper by udara 09-07-2019
			"        OR MOBILE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // removed upper by udara 09-07-2019
			"        OR NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // added by udara 09-07-2019
			"        OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS='Y' "+
			" ORDER BY SEIZER_CODE DESC,LAST_NAME,FIRST_NAME )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*
        ClientSql_New = " SELECT P.NO,CLIENT_CODE,"+
                                            " APPLICATION_NO,"+ 		  											
                                            " NA_STATUS"+
                        " FROM  "+
                                            " (SELECT ROWNUM NO,CLIENT_CODE,APPLICATION_NO,NA_STATUS "+
                                            " FROM "+
                                            " (SELECT A.CLIENT_CODE, A.APPLICATION_NO,A.NA_STATUS "+                         
                                            " FROM "+m_schema_name+".AF_CO_TBD_CONTRACT_BAL A "+
                                            " WHERE (CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
                                            " AND NA_STATUS='ACTIVATED'AND USER_NAME='USER'"+
                                            " ORDER BY CLIENT_CODE DESC)) P "+
                        " WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";*/
		
		/*----------------------------------------------------------------
            Purpose  : select clients
        
            Used in  : RE SETTLEMENT RECEIPT 
        -----------------------------------------------------------------*/			
		ClientSql =   	"SELECT P.NO, P.CLIENT_CODE Client,P.FULL_NAME Name, P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status "+ //P.REG_NO
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS "+ //REG_NO
			"FROM "+
			"(SELECT DISTINCT NVL(A.CLIENT_CODE,'-') CLIENT_CODE ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME  "+ //added by nuwan de silva 25-07-07 //, c.REG_NO REG_NO 
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"    (   (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
		
		
		ClientSql_Collection_Age =		"SELECT P.NO, P.CLIENT_CODE Client,P.FULL_NAME Name, P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status ,P.REG_NO "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS ,REG_NO "+
			"FROM "+
			"(SELECT DISTINCT NVL(A.CLIENT_CODE,'-') CLIENT_CODE ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , nvl(c.REG_NO,'-') REG_NO  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"    (   (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       (EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ 
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
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
		
		
		
		m_help_TXT_FinanceSql_sql =//Added By Sandun On 06-10-2008
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) AS NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+//af_co_app_letter_sent_status
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  ((Q.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR 		(Q.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR 		(Q.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			//" AND	UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			//	" AND "+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(Q.FINANCE_NO,'"+m_vector.elementAt(1)+"')='N' "+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		// commented by udara on 03-01-2012
		//ClientSql_Receipt =   	"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
		//    "FROM "+
		//    "(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
		//    "FROM "+
		//    "(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
		//    "        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
		//    "        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE  "+ //added by nuwan de silva 25-07-07
		//    "	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
		//    "		WHERE "+
		//    "		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
		//	" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
		//    "		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
		//	"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
		//    "    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
		//    "        FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
		//    
		//    "        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
		//    //commented and added by SH on 04-07-2007 
		//    //"       "+m_schema_name+".AF_CO_CHECK_FIN_REG_NO(CLIENT_CODE,UPPER('%"+m_vector.elementAt(0)+"%'))='YES' "+
		//    /*"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
		//    "                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
		//    "                               "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
		//    "                        WHERE  A.APPLICATION_NO = B.APPLICATION_NO AND "+
		//    "                               (FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
		//    "                               CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
		
		//    "                                B.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%'))) "+
		//    */ //end 
		//    " ORDER BY FULL_NAME )) P "+		
		//    "WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		// added by udara on 03-01-2012
		ClientSql_Receipt =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client  FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+ // new
			//"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			" "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,  "+ //added by nuwan de silva 25-07-07
			"    "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) VEHICLE "+//ADDED MILINDA 
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // released by udara 08-09-2015 // commented by udara 03-09-2015 // added by udara on 26-06-2013 // ,'TERMI'
			//"            B.APPLICATION_STATUS NOT IN ('CANCEL','REJECT','CANCEL_PO') AND "+ // re commented by udara 08-09-2015 // added by udara on 03-09-2015
			/*"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+m_vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+*/
			//ADDED MILINDA 2014-02-06
			" ((b.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" (a.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			//" UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (b.APPLICATION_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" (A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//" UPPER(c.ENGINE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(C.REG_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')  "+
			" OR "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(0)+"%' "+ // commented by udara 27-04-2016
			" ) "+ 
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		 
		ClientSql_Receipt_client =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client  FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+ // new			
			" "+
			"FROM "+
			//"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+ // commented by udara 07-03-2019
			"(SELECT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+ // added by udara 07-03-2019
			"FROM "+
			//"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+ // commented by udara 07-03-2019
			"(SELECT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+ // added by udara 07-03-2019
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,  "+
			//"    "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) VEHICLE "+ // commented by udara 20-04-2018
			"  VEHICLE_NO VEHICLE "+ // added by udara 20-04-2018
			
			//"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a, "+
			 "  		(SELECT FINANCE_NO ,APPLICATION_NO ,CLIENT_CODE "+
			 " 				FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			 " 				WHERE FINANCE_NO IS NOT NULL "+
			 " 				AND APPLICATION_STATUS NOT IN ('CANCEL') "+
							" GROUP BY FINANCE_NO ,APPLICATION_NO ,CLIENT_CODE "+ // added by udara 20-04-2018
			           " ) B, "+
			 //"			    AND APPLICATION_STATUS NOT IN('NORM_TERMI','TERMI','WRITE_OFF','LG_SETTLED','RP_SOLD','RP_SETTLED', "+ // released on 2017-10-11
			 //" 			    'TERMINATED','CANCEL','REJECT','CANCEL_PO') )B,  "+
			 " 			(SELECT APPLICATION_NO ,REG_NO "+
			 " 				FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			 " 				WHERE ACTIVE_STATUS IN ('Y','T') "+
							" GROUP BY APPLICATION_NO ,REG_NO "+ // added by udara 20-04-2018
				      " ) C, "+
						
						// added by udara 20-04-2018
						" ( "+
			                " SELECT APPLICATION_NO, VEHICLE_NO "+
			                " FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
			                " WHERE ACTIVE_STATUS = 'Y' "+
			             " ) D "+
						// end by udara 20-04-2018
						
						
						
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			//" 			 B.FINANCE_NO IS NOT NULL AND "+ 
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"            B.APPLICATION_NO=D.APPLICATION_NO(+) AND "+ // added by udara 20-04-2018
			//"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ 
			//"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ 
			
			// commented by udara 20-04-2018
			/*
			" (UPPER(b.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.CLIENT_CODE) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+			
			" UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+			
			" UPPER(B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(b.APPLICATION_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+			
			" UPPER(C.REG_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')  "+
			*/
			
			// added by udara 20-04-2018
			" (b.CLIENT_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" a.CLIENT_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+			
			" UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+			
			" B.FINANCE_NO    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" b.APPLICATION_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" (A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+	
			" (A.PASSPORT_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ // added by udara 20-02-2020	
			" (A.PRE_NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ // added by udara 09-07-2019
			" UPPER(C.REG_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%')  "+
			// end by udara 20-04-2018
			
			//" OR "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(0)+"%' "+ // commented by udara 20-04-2018
			" OR UPPER(D.VEHICLE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // added by udara 20-04-2018
			
			" ) "+ 
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ 
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		
		
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
			" WHERE VALUATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' AND INVENTORY_NO IS NOT NULL "+
			"	ORDER BY VALUATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_REPOSSESSION_NO_sql_report=
			" SELECT L.NO,L.REPOSSESSION_NO,L.FULL_NAME,L.VEHICLE_NO,L.ENGINE_NO,L.CHASSIS_NO,L.VALUE,L.SEIZER_NAME,L.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.FULL_NAME,P.VEHICLE_NO,P.ENGINE_NO,P.CHASSIS_NO,P.VALUE,P.SEIZER_NAME,P.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT "+
			" DISTINCT A.REPOSSESSION_NO, "+
			" D.FULL_NAME, "+
			" NVL(C.VEHICLE_NO,'-') VEHICLE_NO , "+
			" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+
			" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+
			" (SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
			//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1 ) VALUE, "+ // added by udara 20-10-2014
			" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+
			" NVL(TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YY'),'-') REPOSSESSED_DATE "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			" "+m_schema_name+".AF_CO_MAS_MODEL F "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO "+
			" AND B.APPLICATION_NO=C.APPLICATION_NO "+
			" AND D.CLIENT_CODE=B.CLIENT_CODE "+
			" AND C.MODEL_CODE=F.MODEL_CODE "+
			" AND ((A.REPOSSESSION_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" (A.FINANCE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(D.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(C.VEHICLE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER("+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE)) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+
			" ORDER BY A.REPOSSESSION_NO DESC)P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_REPOSSESSION_NO_sql_report_2=
			" SELECT L.NO,L.REPOSSESSION_NO,L.FULL_NAME,L.VEHICLE_NO,L.ENGINE_NO,L.CHASSIS_NO,L.VALUE,L.SEIZER_NAME,L.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.FULL_NAME,P.VEHICLE_NO,P.ENGINE_NO,P.CHASSIS_NO,P.VALUE,P.SEIZER_NAME,P.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT "+
			" DISTINCT A.REPOSSESSION_NO, "+
			" D.FULL_NAME, "+
			" NVL(C.VEHICLE_NO,'-') VEHICLE_NO , "+
			" NVL(C.ENGINE_NO,'-')  ENGINE_NO, "+
			" NVL(C.CHASSIS_NO,'-') CHASSIS_NO, "+
			" (SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
			//" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO) AND ROWNUM=1 ) VALUE, "+ // added by udara 20-10-2014
			" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+
			" NVL(TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YY'),'-') REPOSSESSED_DATE "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			" "+m_schema_name+".AF_CO_MAS_MODEL F "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO "+
			" AND B.APPLICATION_NO=C.APPLICATION_NO "+
			" AND D.CLIENT_CODE=B.CLIENT_CODE "+
			" AND C.MODEL_CODE=F.MODEL_CODE "+
			" AND (UPPER(A.REPOSSESSION_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" (A.FINANCE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(D.FULL_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER(C.VEHICLE_NO) LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR "+
			" UPPER("+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE)) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+
			" AND UPPER(A.SEIZER_CODE) LIKE UPPER('%" + m_vector.elementAt(1) + "%')  "+
			" ORDER BY A.REPOSSESSION_NO DESC)P)L "+
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
			" WHERE VALUATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS ='"+m_vector.elementAt(1)+"' AND INVENTORY_NO IS NOT NULL "+
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
			"  DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO, "+
			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
			"	FULL_NAME, "+
			" CURRENCY_CODE,ACCOUNT_NO,A.BRANCH_CODE "+
			//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS C "+
			
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
			//	" (FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			//	"	OR A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR FULL_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') ) 	"+
			
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			
			" AND A.CLIENT_CODE=C.CLIENT_CODE(+) "+
			" AND APPLICATION_STATUS IN ('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')"+
			" AND FINANCE_NO IS NOT NULL "+
			" ORDER BY FINANCE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		m_help_TXT_FINANCE_OFF_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.CURRENCY_CODE,L.ACCOUNT_NO,L.BRANCH_CODE ,L.APPLICATION_NO,L.COLLECTION_OFFICER"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.CURRENCY_CODE,P.ACCOUNT_NO,P.BRANCH_CODE ,P.APPLICATION_NO,P.COLLECTION_OFFICER"+
			" FROM( "+ 
			" SELECT "+
			"  DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO, "+
			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
			"	FULL_NAME, "+
			" CURRENCY_CODE,ACCOUNT_NO,A.BRANCH_CODE ,"+
			" A.APPLICATION_NO,A.COLLECTION_OFFICER"+
			//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS C "+
			
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
			//	" (FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			//	"	OR A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR FULL_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') ) 	"+
			
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			//"  AND UPPER(A.collection_officer) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  AND A.CLIENT_CODE=C.CLIENT_CODE(+) "+
			//" AND APPLICATION_STATUS IN ('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')"+
			//	" AND APPLICATION_STATUS='ACTIVATED' "+
			" AND FINANCE_NO IS NOT NULL "+
			" ORDER BY FINANCE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_FINANCE_OFF_sql_new=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.CURRENCY_CODE,L.ACCOUNT_NO,L.BRANCH_CODE ,L.APPLICATION_NO,L.COLLECTION_OFFICER"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.CURRENCY_CODE,P.ACCOUNT_NO,P.BRANCH_CODE ,P.APPLICATION_NO,P.COLLECTION_OFFICER"+
			" FROM( "+ 
			" SELECT "+
			"  DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO, "+
			" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+
			"	FULL_NAME, "+
			" CURRENCY_CODE,ACCOUNT_NO,A.BRANCH_CODE ,"+
			" A.APPLICATION_NO,A.COLLECTION_OFFICER"+
			//" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS C "+
			
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
			//	" (FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			//	"	OR A.CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR FULL_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') ) 	"+
			
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			//"  AND UPPER(A.collection_officer) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  AND A.CLIENT_CODE=C.CLIENT_CODE(+) "+
			//" AND APPLICATION_STATUS IN ('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')"+
			" AND APPLICATION_STATUS IN('ACTIVATED','REPOSSESS','TERMINATE','LEGAL') "+//LEGAL Added By Lalanka on 25-06-2009
			" AND FINANCE_NO IS NOT NULL "+
			" ORDER BY FINANCE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//-------------------------------------------------------------------------------------
		//--Purpsoe - Select Account No
		//--used in - Collection - POD Cheques
		//-------------------------------------------------------------------------------------
		/*m_help_TXT_ACCOUNT_NO_1_sql=	
            
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
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
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
			" WHERE (CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
			" TO_CHAR(ENT_DATE,'DD-MM-YYYY') ENT_DATE"+
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//---modified by 	: delanjali------------------------------------------------------------------------------------------------------------------------------
			//---date					: 2007-06-13------------------------------------------------------------------------------------------------------------------------------
			//  " WHERE UPPER(PAYER) LIKE UPPER('"+m_vector.elementAt(0)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(2)+"') "+
			//--------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			" WHERE UPPER(PAYER) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND  UPPER(SUS_REF_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(2)+"') "+
			
			
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
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// thamali 2013.08.13
		m_help_TXT_BROKER_DETAILS_sql=	
			
			" SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,NVL(L.CITY_CODE,'N/A'),NVL(L.POSTAL_CODE,'N/A'),NVL(L.CONTACT_NO,'N/A'),"+
			" NVL(L.MOBILE_NO,'N/A'),NVL(L.FAX_NO,'N/A'),NVL(L.SECTOR_CODE,'N/A'),NVL(L.COMMISSION_RATE,0),NVL(L.COMMISSION_AMOUNT,0) ,L.PAYEE_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO,"+
			" P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT ,P.PAYEE_CODE"+
			" FROM( "+ 
			" SELECT "+
			" A.BROKER_CODE,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,"+
			" A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,A.PAYEE_CODE"+
			" FROM "+m_schema_name+".AF_CO_MAS_BROKER A ,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
			" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE (UPPER(LOCATION_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE ((CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
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
			" WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE (UPPER(PAYMENT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND PROCESS_STATUS=('"+m_vector.elementAt(1)+"')"+
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
			" A.CLIENT_CODE , "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) FULL_NAME ,  "+ //C.FULL_NAME
			" A.VEHICLE_NO  "+
			" FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B, "+
			" "+m_schema_name+".AF_CO_PRO_APP_VALUATION D "+
			/*" (SELECT X.INVENTORY_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
        "  FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
        "  WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
        "  ) C  "+
            */
			
			"  WHERE A.REPOSSESSION_NO=B.REPOSSESSION_NO  "+
			"  AND A.INVENTORY_NO =D.INVENTORY_NO "+
			"  AND VAL_STATUS=('"+m_vector.elementAt(1)+"') "+
			//"  AND A.INVENTORY_NO=C.INVENTORY_NO  "+
			"  AND   "+
			"  ( UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
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
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR   "+
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
			"  (C.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
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
			" WHERE (UPPER(CHQ_NARR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(CHQ_NARRATIONS) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE UPPER(SUS_REF_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND SUSPENSE_ENTRY_TYPE =UPPER('"+m_vector.elementAt(1)+"') "+
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
			" WHERE UPPER(TEAM_HEAD) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//added by nuwan de silva on 01-08-07----
		
		m_help_team_user_id_sql=
			" SELECT L.NO ,L.USER_ID,L.NAME,NVL(L.TEAM_ID,'N/A') AS TEAM_ID,L.TEAM_HEAD ,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.TEAM_ID,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" "+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID) NAME, "+
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
		
		
		m_help_team_user_id_sql_new=
			" SELECT L.NO ,L.USER_ID,L.NAME,NVL(L.SUB_TEAM_ID,'N/A') AS SUB_TEAM_ID,L.SUB_TEAM_DESC ,NVL(L.TEAM_ID,'N/A') AS TEAM_ID,NVL(L.TEAM_DESC,'N/A') AS TEAM_DESC  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.SUB_TEAM_ID,P.SUB_TEAM_DESC,P.TEAM_ID,P.TEAM_DESC "+
			" FROM( "+ 
			" SELECT  "+
			" USER_ID,  "+
			" "+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID) NAME,  "+
			" C.SUB_TEAM_ID, "+
			" D.SUB_TEAM_DESC, "+
			" A.TEAM_ID,  "+
			" TEAM_DESC  "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAMS A, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN C , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS D "+
			" WHERE B.TEAM_ID=C.SUB_TEAM_ID "+
			" AND   A.TEAM_ID=C.TEAM_ID "+
			" AND   C.SUB_TEAM_ID=D.SUB_TEAM_ID "+
			" AND  C.ACTIVE_STATUS='Y'   "+
			" AND  B.ACTIVE_STATUS='Y'   "+
			" AND ( UPPER(USER_ID) LIKE UPPER('%"+m_vector.elementAt(2)+"%')  "+
			" OR   UPPER("+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID)) LIKE UPPER('%"+m_vector.elementAt(2)+"%') ) "+
			" AND  UPPER(A.TEAM_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"')  "+
			" AND  UPPER(C.SUB_TEAM_ID)LIKE  UPPER('%"+m_vector.elementAt(1)+"')  "+
			" ORDER BY A.TEAM_ID ,C.SUB_TEAM_ID,USER_ID "+
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
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code "+
			"and b.application_status='ACTIVATED' "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		ClientSql1_new =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					UPPER(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code "+
			"and b.application_status in ('ACTIVATED','LEGAL','TERMI','NORM_TERMI') "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		// added below by udara on 05-10-2012
		ClientSql1_new2 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE,FINANCE_NO "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE, FINANCE_NO "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE, B.FINANCE_NO FINANCE_NO "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"					(B.FINANCE_NO) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+ // FINANCE_NO
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code "+
			"and b.application_status in ('ACTIVATED','LEGAL','TERMI','NORM_TERMI') "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		// added below by udara on 09-10-2012
		ClientSql1_new3 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE,FINANCE_NO "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE, FINANCE_NO "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE, B.FINANCE_NO FINANCE_NO "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"					(B.FINANCE_NO) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+ // FINANCE_NO
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code "+
			" AND (a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(1)+"%') "+ 
			//"and b.application_status in ('ACTIVATED','LEGAL','TERMI','NORM_TERMI') "+ //commented by kanchana on 2016-06-09 for issue 20554
			"and b.application_status in ('ACTIVATED','LEGAL') "+ //Added by kanchana on 2016-06-09 for issue 20554
			//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(2)+"%' "+ // added by udara on 03-12-2012
			" AND B.BRANCH_CODE LIKE '"+m_vector.elementAt(2)+"%' "+ // added by udara on 12-09-2013
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		ClientSql_Lease_new =   	"SELECT P.NO, P.finance_no,P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, finance_no,CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT B.finance_no finance_no,A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.finance_no)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code AND B.COLLECTION_OFFICER IS NOT NULL "+
			"and b.application_status in ('ACTIVATED','LEGAL','TERMI','NORM_TERMI')  "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		// added by udara on 09-10-2012
		
		ClientSql_Lease_new1 =   	"SELECT P.NO, P.finance_no,P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, finance_no,CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT B.finance_no finance_no,A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.finance_no)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code AND B.COLLECTION_OFFICER IS NOT NULL "+
			" AND (a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(1)+"%')  "+
			"and b.application_status in ('ACTIVATED','LEGAL','TERMI','NORM_TERMI')  "+
			//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(3)+"%' "+ // added by udara on 03-12-2012
			//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION(B.APPLICATION_NO) LIKE '%"+m_vector.elementAt(2)+"%' "+ // added by udara on 31-12-2012
			" AND B.BRANCH_CODE LIKE '"+m_vector.elementAt(2)+"%' "+ // added by udara on 12-09-2013
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		// end by udara on 09-10-2012
		
		
		
		
		m_help_TXT_FinanceSql_new2 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT NVL(FINANCE_NO,'-') FINANCE_NO,NVL(APPLICATION_NO,'-') APPLICATION_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND COLLECTION_OFFICER IS NULL "+
			" AND (CLIENT_CODE) like upper('%"+m_vector.elementAt(1)+"%') "+
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
			" AND (A.CLIENT_CODE) like upper('%"+m_vector.elementAt(1)+"%') "+
			" AND ((A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) ORDER BY FINANCE_NO DESC   "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		ClientSql2 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.finance_no)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code AND B.COLLECTION_OFFICER IS NOT NULL "+
			"and b.application_status='ACTIVATED' "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		ClientSql_Lease =   	"SELECT P.NO, P.finance_no,P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, finance_no,CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT B.finance_no finance_no,A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.finance_no)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
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
			" WHERE (UPPER(GROUP_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(GROUP_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(BRC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+			
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
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
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
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"WHERE ((A.TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"WHERE ((A.NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"AND ((B.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
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
			"(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";											
		
		//--Added by Prabash on 18-02-2012-Client chack for-Collection - Post Dated Cheques-Receipt Generation --*
		
		ClientSql_PDC = "SELECT P.NO,P.CLIENT_CODE ,P.FULL_NAME ,P.NIC_NO ,P.ACTIVE_STATUS "+
			
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,FULL_NAME,NIC_NO,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT DISTINCT  "+
			"	B.CLIENT_CODE,B.FULL_NAME,B.NIC_NO,B.ACTIVE_STATUS"+
			"	FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+m_schema_name+".AF_CO_MAS_CLIENT B"+
			"	WHERE  A.SETTLE_MODE = 'CHEQUE' 		"+
			"	AND A.CLIENT_CODE = B.CLIENT_CODE 	"+
			" 	AND (UPPER(B.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"       (B.NIC_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		//--Added by Prabash on 18-02-2012-Client chack for-Collection - Post Dated Cheques-Receipt Generation --*
		
		ClientSql_PDC2 = "SELECT P.NO,P.FINANCE_NO ,P.CLIENT_CODE ,P.CLIENT_NAME ,P.SETTLE_MODE "+
			
			"FROM "+
			"(SELECT ROWNUM NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME,SETTLE_MODE "+
			"FROM "+
			"(SELECT DISTINCT   A.FINANCE_NO,"+
			"	A.CLIENT_CODE,"+
			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME,"+
			"    A.SETTLE_MODE  "+		
			"	FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+m_schema_name+".AF_CO_MAS_CLIENT B"+
			"	WHERE  A.SETTLE_MODE = 'CHEQUE' "+		
			"	AND A.CLIENT_CODE = B.CLIENT_CODE "+
			" 	AND (UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		( A.FINANCE_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"       (A.CLIENT_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"   AND UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		
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
		
		
		//added by nuwan de silva for the application status change screen on 29-09-07
		
		m_help_finance_no_application_status_change=
			" SELECT L.NO ,L.APPLICATION_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO  "+
			"  FROM   "+
			"  (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO  "+
			"  FROM( "+
			
			"  SELECT   "+
			"  NVL(A.APPLICATION_NO,'-') APPLICATION_NO,   "+
			"  NVL(A.FINANCE_NO,'-') FINANCE_NO,   "+
			"  B.CLIENT_CODE CLIENT_CODE,  "+
			"  B.FULL_NAME FULL_NAME, "+ 
			"  NVL(B.TEL_NO,'-') TEL_NO,  "+
			"  NVL(B.NIC_NO,'-') NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
			"  (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,X.APPLICATION_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			//"  AND A.APPLICATION_STATUS =('"+m_vector.elementAt(1)+"')  "+
			"  AND A.APPLICATION_STATUS IN ('ACTIVATED','NEGOT','ARBIT','RESCH','REPOS','REBON')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";		
		
		
		//===============================================================================
		
		m_help_finance_no_application_status_change_new=
			" SELECT L.NO ,L.APPLICATION_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO  "+
			"  FROM   "+
			"  (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO  "+
			"  FROM( "+
			
			"  SELECT   "+
			"  NVL(A.APPLICATION_NO,'-') APPLICATION_NO,   "+
			"  NVL(A.FINANCE_NO,'-') FINANCE_NO,   "+
			"  B.CLIENT_CODE CLIENT_CODE,  "+
			"  B.FULL_NAME FULL_NAME, "+ 
			"  NVL(B.TEL_NO,'-') TEL_NO,  "+
			"  NVL(B.NIC_NO,'-') NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
			"  (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,X.APPLICATION_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS = 'LEGAL' "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		m_help_finance_no_application_status_change_edit_new=
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO  "+
			"  FROM   "+
			"  (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO  "+
			"  FROM( "+
			
			"  SELECT   "+
			"  NVL(A.FINANCE_NO,'-') FINANCE_NO,   "+
			"  B.CLIENT_CODE CLIENT_CODE,  "+
			"  B.FULL_NAME FULL_NAME, "+ 
			"  NVL(B.TEL_NO,'-') TEL_NO,  "+
			"  NVL(B.NIC_NO,'-') NIC_NO  "+
			"  FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE A,  "+
			"  (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
			"  FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			//"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			"  )  "+
			"  AND A.APPROVE_STATUS = 'N' "+
			"  AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE GROUP BY FINANCE_NO) "+
			"  ORDER BY A.FINANCE_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		//Added By Sandun 23-10-2008
		//===============================================================================AF_RE_PRO_APP_STATUS_CHANGE
		
		
		
		//added by nuwan de silva on 01-10-07
		m_help_finance_no_application_status_change_edit=
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO  "+
			"  FROM   "+
			"  (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO  "+
			"  FROM( "+
			
			"  SELECT   "+
			"  NVL(A.FINANCE_NO,'-') FINANCE_NO,   "+
			"  B.CLIENT_CODE CLIENT_CODE,  "+
			"  B.FULL_NAME FULL_NAME, "+ 
			"  NVL(B.TEL_NO,'-') TEL_NO,  "+
			"  NVL(B.NIC_NO,'-') NIC_NO  "+
			"  FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE A,  "+
			"  (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
			"  FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			"  )  "+
			//"  AND A.APPLICATION_STATUS =('"+m_vector.elementAt(1)+"')  "+
			"  ORDER BY A.FINANCE_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";				
		
		m_help_loan_facilities_sql=	
			"SELECT L.NO ,L.loan_facility_no,L.ref_no,L.ref_name,L.ref_add1,L.ref_add2,L.ref_tel,L.ref_fax "+
			"FROM "+
			"(SELECT ROWNUM NO ,P.loan_facility_no,P.ref_no,P.ref_name,P.ref_add1,P.ref_add2,P.ref_tel,P.ref_fax "+
			" FROM( "+
			" SELECT a.loan_facility_no,a.ref_no,a.ref_name,a.ref_add1, "+
			" a.ref_add2, a.ref_tel, a.ref_fax "+
			" FROM   "+m_schema_name+".af_co_mas_loan_facilities a "+
			" WHERE  UPPER(a.loan_facility_no)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND    a.active_status='Y' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		m_help_TXT_FINANCE_NO_branch_sql=
			//Added By Sandun For Collection Report - Branch 07-11-2008
			" SELECT L.NO ,L.FINANCE_NO,L.NAME,L.ADDRESS,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,L.BRANCH_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE,L.EMP_CODE ,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.NAME,P.ADDRESS,P.CONTACT_NO,P.CITY_CODE,P.BRANCH_CODE,P.AREA_CODE,P.EMP_CODE,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT   "+
			" DISTINCT B.FINANCE_NO,	"+		 
			" A.FIRST_NAME || ' ' || A.LAST_NAME NAME, "+
			" A.ADDRESS, "+
			" A.CONTACT_NO,"+
			" A.CITY_CODE, "+
			" B.BRANCH_CODE,"+//kanishka
			//" A.LOCATION_CODE,"+//kanishka
			" A.AREA_CODE,"+			 			 
			" A.EMP_CODE,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE, "+
			" A.EPF_NO, "+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE = B.COLLECTION_OFFICER "+
			" AND ("+
			"     (B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara 07-02-2014
		
		m_help_TXT_FINANCE_NO_branch_2_sql=
			//Added By Sandun For Collection Report - Branch 07-11-2008
			" SELECT L.NO ,L.FINANCE_NO,L.NAME,L.ADDRESS,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,L.BRANCH_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE,L.EMP_CODE ,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.NAME,P.ADDRESS,P.CONTACT_NO,P.CITY_CODE,P.BRANCH_CODE,P.AREA_CODE,P.EMP_CODE,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT   "+
			" DISTINCT B.FINANCE_NO,	"+		 
			" A.FIRST_NAME || ' ' || A.LAST_NAME NAME, "+
			" A.ADDRESS, "+
			" A.CONTACT_NO,"+
			" A.CITY_CODE, "+
			" B.BRANCH_CODE,"+//kanishka
			//" A.LOCATION_CODE,"+//kanishka
			" A.AREA_CODE,"+			 			 
			" A.EMP_CODE,"+
			" DESIGNATION_CODE,"+
			" A.DIVISION_CODE, "+
			" A.EPF_NO, "+
			" A.ID_NO "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
			" WHERE A.EMP_CODE = B.COLLECTION_OFFICER "+
			" AND UPPER(A.LOCATION_CODE) = UPPER('"+m_vector.elementAt(1)+"')  "+
			" AND ("+
			"     (B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  (A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  (B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 07-02-2014
		
		
		ClientSql1_Insurance =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code AND b.INSURANCE_OFFICER IS NULL "+
			"and b.application_status='ACTIVATED' "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		ClientSql_Ins_Officer_edit =   	"SELECT P.NO, P.finance_no,P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, finance_no,CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT DISTINCT B.finance_no finance_no,A.CLIENT_CODE CLIENT_CODE,A.FULL_NAME FULL_NAME, A.FIRST_NAME FIRST_NAME, A.SURNAME SURNAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS,NVL(A.CITY_CODE,'-') CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	 WHERE (UPPER(a.FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					UPPER(a.CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(a.ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (a.MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (B.finance_no)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (a.NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(a.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" and a.client_code=b.client_code AND B.INSURANCE_OFFICER IS NOT NULL "+
			"and b.application_status='ACTIVATED' "+
			" ORDER BY a.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		
		
		
		
		ClientSql_POD_With = " SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ 
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_RE_PRO_POD_CHEQUES B "+
			" WHERE (UPPER(A.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  (A.CLIENT_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  (B.FINANCE_NO)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  UPPER(A.FULL_NAME)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  AND  A.CLIENT_CODE = B.CLIENT_CODE "+
			"  AND  B.STATUS      = 'INV' "+
			"  ORDER BY FULL_NAME )) P "+		
			"  WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		ClientSql_POD_With_Edit  = " SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status ,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS, ADDRESS1, ADDRESS2,CITY_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.CLIENT_CODE ,A.FULL_NAME , A.ACTIVE_STATUS , A.TEMP_ACTIVE_STATUS, NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , NVL(DECODE(A.CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME "+ 
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_RE_PRO_POD_CHEQUES B "+
			" WHERE (UPPER(A.ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  (A.CLIENT_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  (B.FINANCE_NO)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  UPPER(A.FULL_NAME)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  AND  A.CLIENT_CODE = B.CLIENT_CODE "+
			"  AND  B.STATUS      = 'WIT' "+
			"  ORDER BY FULL_NAME )) P "+		
			"  WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		pod_ack_sql = " SELECT P.NO, P.POD_REF_NO, P.CLIENT_CODE,P.CHEQUE_NO, TO_CHAR(P.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,P.SETTLE_MODE,P.PAYER_BRANCH_CODE,P.PAYER_ACC_NO,P.CHEQUE_AMOUNT,P.POD_BATCH_NO "+
			" FROM "+
			" (SELECT ROWNUM NO, POD_REF_NO, CLIENT_CODE,CHEQUE_NO, CHEQUE_DATE,SETTLE_MODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_AMOUNT,POD_BATCH_NO "+
			" FROM "+
			" (SELECT DISTINCT A.POD_REF_NO, A.CLIENT_CODE, A.CHEQUE_NO, A.CHEQUE_DATE, "+
			" A.SETTLE_MODE, A.PAYER_BRANCH_CODE, A.PAYER_ACC_NO, "+
			" SUM(A.CHEQUE_AMOUNT) CHEQUE_AMOUNT ,POD_BATCH_NO"+
			" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A "+
			" WHERE A.STATUS = 'INV' "+
			" AND A.POD_REF_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')"+														 
			" GROUP BY A.POD_REF_NO, A.CLIENT_CODE, A.CHEQUE_NO, A.CHEQUE_DATE, "+
			" A.SETTLE_MODE, A.PAYER_BRANCH_CODE, A.PAYER_ACC_NO,A.ENTRY_TYPE,POD_BATCH_NO "+
			" ORDER BY A.POD_REF_NO DESC )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		//================================================================================================================
		
		FinanceSql_Termi =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE,NVL(AMOUNT,0) AMOUNT "+//Added By Sandun on 23-09-2009
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,CURRENCY_CODE,AMOUNT "+
			" FROM "+
			" (SELECT DISTINCT B.FINANCE_NO,B.APPLICATION_NO, B.CLIENT_CODE, "+
			"        "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME,B.CURRENCY_CODE, "+
			"        "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'),'') AMOUNT"+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE "+
			" B.APPLICATION_STATUS IN ('TERM_TO','TERMI','TERMINATED','NORM_TERMI') "+
			" AND  (B.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"        OR "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND    "+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'),'') < 0 "+ 
			" ORDER BY B.APPLICATION_NO DESC,B.CLIENT_CODE )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		m_help_active_employee_list_sql = "" +
			"   SELECT L.NO \"No.\", " +
			"          L.EMP_CODE, " +
			"          L.TITLE, " +
			"          L.FIRST_NAME, " +
			"          L.LAST_NAME, " +
			"          L.ADDRESS, " +
			"          L.LOCATION_CODE, " +
			"          NVL(L.AREA_CODE, 'N/A') AREA_CODE, " +
			"          NVL(L.CITY_CODE, 'N/A') CITY_CODE, " +
			"          NVL(L.CONTACT_NO, 'N/A') CONTACT_NO, " +
			"          L.DESIGNATION_CODE, " +
			"          L.DIVISION_CODE, " +
			"          NVL(L.EPF_NO, 'N/A') EPF_NO, " +
			"          L.ID_NO, " +
			"          L.USER_ID, " +
			"          L.EMP_DOB, " +
			"          L.EMP_DO_JOIN, " +
			"          L.EMP_DO_RESIGN " +
			"   FROM ( " +
			"       SELECT ROWNUM NO, " +
			"              P.EMP_CODE, " +
			"              P.TITLE, " +
			"              P.FIRST_NAME, " +
			"              P.LAST_NAME, " +
			"              P.ADDRESS, " +
			"              P.LOCATION_CODE, " +
			"              P.AREA_CODE, " +
			"              P.CITY_CODE, " +
			"              P.CONTACT_NO, " +
			"              P.DESIGNATION_CODE, " +
			"              P.DIVISION_CODE, " +
			"              P.EPF_NO, " +
			"              P.ID_NO, " +
			"              P.USER_ID, " +
			"              P.EMP_DOB, " +
			"              P.EMP_DO_JOIN, " +
			"              P.EMP_DO_RESIGN " +
			"       FROM ( " +
			"           SELECT A.EMP_CODE, " +
			"                  A.TITLE, " +
			"                  A.FIRST_NAME, " +
			"                  A.LAST_NAME, " +
			"                  A.ADDRESS, " +
			"                  A.LOCATION_CODE, " +
			"                  A.AREA_CODE, " +
			"                  A.CITY_CODE, " +
			"                  A.CONTACT_NO, " +
			"                  A.DESIGNATION_CODE, " +
			"                  A.DIVISION_CODE, " +
			"                  A.EPF_NO, " +
			"                  A.ID_NO, " +
			"                  NVL(" + m_schema_name + ".AF_CO_GET_USER(A.EMP_CODE), '-') USER_ID, " +
			"                  A.EMP_DOB, " + // Added by Prabash on 19-08-2011
			"                  A.EMP_DO_JOIN, " + // Added by Prabash on 19-08-2011
			"                  A.EMP_DO_RESIGN " + // Added by Prabash on 19-08-2011
			"           FROM   " + m_schema_name + ".CO_CO_MAS_EMPLOYEE A " +
			"           WHERE ( " +
			"                  A.EMP_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') " +
			"               OR UPPER(A.FIRST_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') " +
			"               OR UPPER(A.LAST_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%') " +
			"           ) " +
			"           AND A.ACTIVE_STATUS = 'Y' " +
			"       ) P " +
			"   ) L " +
			"   WHERE L.NO >= " + Start_Val + " AND L.NO <= " + End_Val + " " +
			"";
		
		
		m_help_coll_officer_list_sql=
			/*
            " SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
            " FROM( "+ 
            " SELECT "+
            " DISTINCT A.EMP_CODE,"+
            " A.TITLE,"+
            " A.FIRST_NAME,"+
            " A.LAST_NAME,"+
            " A.ADDRESS,"+
            " A.LOCATION_CODE,"+
            " A.AREA_CODE,"+
            " A.CITY_CODE,"+
            " A.CONTACT_NO,"+
            " DESIGNATION_CODE,"+
            " A.DIVISION_CODE,"+
            " A.EPF_NO,"+
            " A.ID_NO "+
            " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  B "+
            " WHERE A.EMP_CODE=B.COLLECTION_OFFICER "+
            " AND ("+
            "     UPPER(A.EMP_CODE)      LIKE UPPER('"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
            " OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
            " OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('"+m_vector.elementAt(0)+"%')  ) "+
            // " AND UPPER(A.LOCATION_CODE)   LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
            
            " AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
            */
			
			// m_help_TXT_EMP_CODE_2_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
			//" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
			" FROM( "+ 
			" SELECT DISTINCT "+
			" A.EMP_CODE,"+
			" A.TITLE,"+
			" A.FIRST_NAME,"+
			" A.LAST_NAME,"+
			" A.ADDRESS,"+
			" A.LOCATION_CODE,"+
			" A.AREA_CODE,"+
			" A.CITY_CODE,"+
			" A.CONTACT_NO,"+
			" A.DESIGNATION_CODE,"+
			" A.DIVISION_CODE,"+
			" A.EPF_NO,"+
			" A.ID_NO ,"+
			" NVL("+m_schema_name+".AF_CO_GET_USER(A.EMP_CODE),'-' ) USER_ID, "+
			" A.EMP_DOB ,"+ //Added by Prabash on 19-08-2011
			" A.EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
			" A.EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A, "+
			"      "+m_schema_name+".AF_CR_PRO_MKT_COLL_MAP B "+
			// " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" WHERE A.EMP_CODE = B.COLLECTION_OFFICER " +
			"   AND ( A.EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(A.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			// " AND EMP_CODE NOT IN ( " +
			// "     SELECT DISTINCT COLLECTION_OFFICER " +
			// "     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
			// " ) " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// ****Do not Alter The Parts Below
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}
