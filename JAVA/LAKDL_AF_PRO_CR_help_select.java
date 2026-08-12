//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_PRO_CR_help_select  {  
	
	//Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
	LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	
	public String m_help_TXT_PURCHASE_ORDER_NO1         ="";//Added By Sandun on 15-06-2009
	public String m_help_TXT_PURCHASE_ORDER_NO1_Header  ="Purchase Order Help";   
	
	public String m_help_TXT_APPLICATION_NO_3 = " ";
	public String m_help_TXT_APPLICATION_NO_3_Header = "Credit Process - Application Help";
	
	public String m_help_payment_no_other_payment ="";
	public String m_help_payment_no_other_payment_Header ="Finance - Payment N0";
	
	
	public String FinanceSql_add_gua = " ";
	public String FinanceSql_add_gua_Header = "Credit Process - Finance Help";
	
	public String m_help_suspense_reference_Lawyer ="";
	public String m_help_suspense_reference_Lawyer_Header="Finance - Lawyer Help";
	
	public String m_help_suspense_reference_Seizer ="";
	public String m_help_suspense_reference_Seizer_Header= "Finance - Seizer Help";
	
	public String m_help_suspense_reference ="";
	public String m_help_suspense_reference_Header="Finance - Suspense Reference";
	
	
	public String m_help_Finance_no_Change_activated_date="";
	public String m_help_Finance_no_Change_activated_date_Header="Credit Process - Finance Help";
	
	public String m_help_Finance_no_Change_rental_amount="";
	public String m_help_Finance_no_Change_rental_amount_Header="Credit Process - Finance Help";
	
	public String FinanceSql_sus_payment         ="";
	public String FinanceSql_sus_payment_Header  ="Credit Process - Finance Number Help";
	
	public String m_help_Finance_no_Change_activated_date_app_no="";
	public String m_help_Finance_no_Change_activated_date_app_no_Header="Credit Process - Application Number Help";
	
	public String m_help_Finance_no_Change_rental_amount_app_no="";
	public String m_help_Finance_no_Change_rental_amount_app_no_Header="Credit Process - Application Number Help";
	
	
	public String FinanceSql_change_cap_allow = " ";
	public String FinanceSql_change_cap_allow_Header = "Credit Process - Finance Help";
	
	//public String FinanceSql_change_cap_allow = " ";
	//	public String FinanceSql_change_cap_allow_Header = "Credit Process - Finance Help";
	
	public String InvoiceSql_change_cap_allow = " ";
	public String InvoiceSql_change_cap_allow_Header = "Credit Process - Invoice Help";
	
	public String ClientSql_change_cap_allow = " ";
	public String ClientSql_change_cap_allow_Header = "Credit Process - Client Help";
	
	public String ClientSql_add_gua = " ";
	public String ClientSql_add_gua_Header = "Credit Process - Client Help";
	
	public String GuarantorSql_add_gua = " ";
	public String GuarantorSql_add_gua_Header = "Credit Process - Guarantor Help";
	
	public String m_help_CR_DR_NOTES_FINANCE_NO;
	public String m_help_CR_DR_NOTES_FINANCE_NO_Header="Credit Process - Finance Number Help ";	
	
	public String m_help_CR_DR_NOTES_FINANCE_NO_1="";
	public String m_help_CR_DR_NOTES_FINANCE_NO_1_Header="Credit Process - Finance Number Help ";
	
	
	public String m_help_CR_DR_NOTES_INVOICE_NO="";
	public String m_help_CR_DR_NOTES_INVOICE_NO_Header="Credit Process - Invoice Number Help";
	
	public String m_help_CR_DR_NOTES_INVOICE_NO_ADJ="";
	public String m_help_CR_DR_NOTES_INVOICE_NO_ADJ_Header="Credit Process - Invoice Number Help";
	
	public String m_help_CR_DR_NOTES_CREDIT_NO="";
	public String m_help_CR_DR_NOTES_CREDIT_NO_Header="Credit Process - Credit Number Help";
	
	public String m_help_CR_DR_NOTES_CREDIT_NO1="";
	public String m_help_CR_DR_NOTES_CREDIT_NO1_Header="Credit Process - Credit Number Help";
	
	public String m_help_TXT_FinanceSql_new="";
	public String m_help_TXT_FinanceSql_new_Header="Credit Process - Application Help";
	
	public String  m_help_TXT_INVOICE_NO_app_sql1="";
	public String  m_help_TXT_INVOICE_NO_app_sql1_Header="Credit Process - Invoice No Help";
	
	public String m_help_TXT_FINANCE_NO_4_4 = " ";
	public String m_help_TXT_FINANCE_NO_4_4_Header = "Credit Process - Finance No Help2";  
	
	// added by udara 07-08-2014
	public String m_help_TXT_INS_CANCEL = " ";
	public String m_help_TXT_INS_CANCEL_Header = "Credit Process - Finance No Help2";  
	
	// added by udara 31-10-2013
	public String  m_help_ClientSql_Receipt="";
	public String  m_help_ClientSql_Receipt_Header="Finance Number - Help";
	
	// added by udara 01-11-2013
	public String  m_help_ClientSql_Receipt_cp="";
	public String  m_help_ClientSql_Receipt_cp_Header="Finance Number - Help";
	
	// added by udara 12-03-2014
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_sql_Header="System Administration - Item Sub Category";
	
	// added by udara 20-03-2014
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_NEW_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_NEW_sql_Header="System Administration - Item Sub Category";
	
	public String m_help_TXT_FINANCE_NO_branch_sql        = "";
	public String m_help_TXT_FINANCE_NO_branch_sql_Header ="Finance Number Help"; //Added By Minal For Insurence payment sheduke 29-12-2014
	
	public String ClientSql           = " ";
	public String ClientSql_Header    = "Client Help "; 
	public String InquirySql          = " ";
	public String InquirySql_Header   = "Inquiry Help "; 
	public String MKOfficerSql        = " ";
	public String MKOfficerSql_Header = "Officer Help "; 
	public String MKSuperSql        = " ";
	public String MKSuperSql_Header = "Supervisor Help "; 
	public String MKTeamSql        = " ";
	public String MKTeamSql_Header = "Team Help "; 
	public String TrnSubSql        = " ";
	public String TrnSubSql_Header = "Help "; 
	public String ModelSql        = " ";
	public String ModelSql_Header = "Help "; 
	public String VendorSql        = " ";
	public String VendorSql_Header = "Help "; 
	public String PriceSql         = " ";
	public String PriceSql_Header  = "Help "; 
	public String SubModelSql         = " ";
	public String SubModelSql_Header  = "Help "; 
	public String IntBaseSql         = " ";
	public String IntBaseSql_Header  = "Help "; 
	
	public String m_help_TXT_DISTRICT_CODE_sql        = "District Help ";
	public String m_help_TXT_DISTRICT_CODE_sql_Header = "Help ";
	
	public String m_help_TXT_DISTRICT_CODE_sql1        = "District Help ";
	public String m_help_TXT_DISTRICT_CODE_sql1_Header = "Help ";
	
	
	public String m_help_TXT_APPLICATION_NO        = " ";
	public String m_help_TXT_APPLICATION_NO_Header = "Marketing - Application Number Help";
	
	public String m_help_TXT_APPLICATION_NO_lease        = " ";
	public String m_help_TXT_APPLICATION_NO_lease_Header = "Marketing - Application Number Help";
	
	public String m_help_TXT_CLIENT_CODE        = " ";
	public String m_help_TXT_CLIENT_CODE_Header = "Marketing Client Help";
	
	public String m_help_TXT_INQUARY_NO        ="";
	public String m_help_TXT_INQUARY_NO_Header ="Inquiry Help ";
	
	public String m_help_TXT_MAKE        =" ";
	public String m_help_TXT_MAKE_Header ="Make Help";	
	
	public String m_help_TXT_INVOICE_PURCHASE        ="";
	public String m_help_TXT_INVOICE_PURCHASE_Header ="Invoice Help"; 
	
	public String m_help_TXT_PURCHASE_ORDER_NO         ="";
	public String m_help_TXT_PURCHASE_ORDER_NO_Header  ="Purchase Order Help";         
	
	public String m_help_TXT_MODEL_CODE_sql        =" ";
	public String m_help_TXT_MODEL_CODE_sql_Header ="Model Help";
	
	public String m_help_TXT_MODEL_CODE_inv_sql           ="";
	public String m_help_TXT_MODEL_CODE_inv_sql_Header    ="Model Help";
	
	public String PriceSql_invoice           ="";
	public String PriceSql_invoice_Header           ="Pricing Help";
	
	public String m_help_TXT_SUB_MODEL_sql        ="";
	public String m_help_TXT_SUB_MODEL_sql_Header ="Sub Model Help";
	
	public String m_help_TXT_SUB_M_CODE_sql          ="";
	public String m_help_TXT_SUB_M_CODE_sql_Header   ="Sub Model Help";
	
	public String m_help_licence_account_no        ="";
	public String m_help_licence_account_no_Header ="Account Code Help";
	
	
	public String m_help_TXT_CITY_CODE_sql        ="";
	public String m_help_TXT_CITY_CODE_sql_Header ="City Help"; 
	
	public String 	m_help_TXT_APPLICATION_NO_2        ="";
	public String 	m_help_TXT_APPLICATION_NO_2_Header ="Application Help";
	
	public String m_help_TXT_SUPPLIER_sql           ="";
	public String m_help_TXT_SUPPLIER_sql_Header    ="Supplier Help";
	
	//public String m_help_TXT_INVOICE_sql           ="";
	//public String m_help_TXT_INVOICE_sql_Header    ="Invoice Help";
	
	public String m_help_SUPPLIER_PURCHASE_ORDER   ="";
	public String m_help_SUPPLIER_PURCHASE_ORDER_Header   ="Supplier Help";
	
	public String m_help_TXT_INVOICE_NO_sql        ="";
	public String m_help_TXT_INVOICE_NO_sql_Header ="Invoice Help";
	
	public String m_help_TXT_ASSET_ID_sql          ="";
	public String m_help_TXT_ASSET_ID_sql_Header   ="Asset ID Help";
	
	public String m_help_TXT_PRICING_NO_sql="";
	public String m_help_TXT_PRICING_NO_sql_Header="Pricing Details";
	
	public String m_help_TXT_QUOTATION_NO_sql="";
	public String m_help_TXT_QUOTATION_NO_sql_Header="Quotation Details";
	
	public String m_help_TXT_QUOTATION_NO_sql1="";
	public String m_help_TXT_QUOTATION_NO_sql1_Header="Quotation Details";
	
	public String m_help_TXT_CONDITION_OF_ASSET_sql="";
	public String m_help_TXT_CONDITION_OF_ASSET_sql_Header="Condition of Assets Details";
	
	public String m_help_TXT_MAKE_CODE_sql="";
	public String m_help_TXT_MAKE_CODE_sql_Header="Make Details";
	
	public String m_help_TXT_MODEL_CODE1_sql="";
	public String m_help_TXT_MODEL_CODE1_sql_Header="Model Details";
	
	public String m_help_TXT_MODEL_CODE2_sql="";
	public String m_help_TXT_MODEL_CODE2_sql_Header="Model Details";
	
	
	
	public String m_help_TXT_INQUIRY_NO_sql="";
	public String m_help_TXT_INQUIRY_NO_sql_Header="Inquiry Details";
	
	
	// public String m_help_TXT_CLIENT_CODE2        = " ";
	// public String m_help_TXT_CLIENT_CODE2_Header = "Marketing Client Help";
	
	public String m_help_TXT_SCREEN_NAME_sql="";
	public String m_help_TXT_SCREEN_NAME_sql_Header="Screen Name Help";
	
	public String m_help_TXT_PURCHASE_ORDER_NO_sql="";
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_Header="Payment Details";
	
	
	public String m_help_TXT_APP_NO_sql="";
	public String m_help_TXT_APP_NO_sql_Header="Application Details";
	
	public String m_help_TXT_VENDER_CODE_sql="";
	public String m_help_TXT_VENDER_CODE_sql_Header="Vendor Details";
	
	public String m_help_TXT_ASSET_ID_sql2= "";
	public String m_help_TXT_ASSET_ID_sql2_Header= "Application Processing - Asset ID ";
	
	
	//Added by Dineth on 28-07-2009
	public String m_help_TXT_ASSET_ID_sql3= "";
	public String m_help_TXT_ASSET_ID_sql3_Header= "Application Processing - Asset ID ";
	//End by Dineth on 28-07-2009
	
	public String m_help_TXT_APP_NO_sql_1="";
	public String m_help_TXT_APP_NO_sql_1_Header="Application Details";
	
	public String m_help_TXT_VENDOR_CODE_sql="";
	public String m_help_TXT_VENDOR_CODE_sql_Header="Marketing - Vendor";
	
	public String m_help_TXT_LOCATION_CODE_sql="";
	public String m_help_TXT_LOCATION_CODE_sql_Header="Application Process - Location ";
	
	public String m_help_TXT_MAS_VENDOR_LOCATION_sql="";
	public String m_help_TXT_MAS_VENDOR_LOCATION_sql_Header="Application Process - Vendor Branch Location ";
	
	public String m_help_TXT_BRANCH_CODE_sql="";
	public String m_help_TXT_BRANCH_CODE_sql_Header="Application Process - Branch Code ";
	
	
	public String m_help_TXT_ACCOUNT_sql="";
	public String m_help_TXT_ACCOUNT_sql_Header="Application Process - Account Details ";
	
	public String m_help_TXT_ACCOUNT_1_sql="";
	public String m_help_TXT_ACCOUNT_1_sql_Header="Application Process - Account Details ";
	
	public String m_help_TXT_ACCOUNT_3_sql="";
	public String m_help_TXT_ACCOUNT_3_sql_Header="Application Process - Account Details ";
	
	public String m_help_TXT_ACCOUNT_2_sql="";
	public String m_help_TXT_ACCOUNT_2_sql_Header="Application Process - Account Details ";
	
	public String 	m_help_TXT_APPL_NO        ="";
	public String 	m_help_TXT_APPL_NO_Header ="Application Help";
	
	public String m_help_BRANCH_CODE_PURCHASE_ORDER       ="";
	public String m_help_BRANCH_CODE_PURCHASE_ORDER_Header ="Credit Process - Branch Code";
	
	public String m_help_TXT_APPLICATION_NO_PUR_ORD             ="";
	public String m_help_TXT_APPLICATION_NO_PUR_ORD_Header      ="Credit Process - Application Number Help";  
	
	
	public String FinanceSql           = " ";
	public String FinanceSql_Header    = "Credit - Finance Help "; 
	
	public String FinanceSql_doc           = " ";
	public String FinanceSql_doc_Header    = "Credit - Finance Help "; 
	
	public String FinanceSql_return           = " ";
	public String FinanceSql_return_Header    = "Credit - Finance Help "; 
	
	public String m_help_cr_score_application_sql_1="";
	public String m_help_cr_score_application_sql_1_Header=" Credit Score - Applications";
	
	public String m_help_cr_score_application_sql="";
	public String m_help_cr_score_application_sql_Header=" Credit Score - Applications";
	
	public String m_help_fin_staus_application_sql="";
	public String m_help_fin_staus_application_sql_Header="Finance Status - Application No Help";
	
	public String m_help_fin_staus_application_sql2="";
	public String m_help_fin_staus_application_sql2_Header="Finance Status - Application No Help";
	
	public String m_help_fin_staus_finance_sql="";
	public String m_help_fin_staus_finance_sql_Header="Finance Status - Finance No Help";
	
	public String m_help_fin_staus_vihicle_sql="";
	public String m_help_fin_staus_vihicle_sql_Header="Finance Status - Vihicle No Help";
	
	public String m_help_fin_staus_inquary_sql="";
	public String m_help_fin_staus_inquary_sql_Header="Finance Status - Inquary No Help";
	
	public String m_help_fin_staus_client_sql="";
	public String m_help_fin_staus_client_sql_Header="Finance Status - Client No Help";
	
	public String m_help_TXT_USER_ID_sql="";
	public String m_help_TXT_USER_ID_sql_Header="System Administration - User";
	
	public String m_help_TXT_SCORE_MODEL_CODE_sql="";
	public String m_help_TXT_SCORE_MODEL_CODE_sql_Header= "Client Help - Credit Score Model Creation";
	
	public String m_help_TXT_CLIENT_CODE_app        = " ";
	public String m_help_TXT_CLIENT_CODE_app_Header ="Marketing Client Help";
	
	
	public String m_help_TXT_INVOICE_NO_app_sql			="";
	public String m_help_TXT_INVOICE_NO_app_sql_Header	="Credit Process - Vehicle No Help";
	
	public String FinanceSql_new           = " ";
	public String FinanceSql_new_Header    = "Credit - Finance Help "; 
	
	
	public String m_help_TXT_CLIENT_CODE_new        = " ";
	public String m_help_TXT_CLIENT_CODE_new_Header ="Marketing Client Help";
	
	
	public String m_help_TXT_INVOICE_NO_new_sql			="";
	public String m_help_TXT_INVOICE_NO_new_sql_Header	="Credit Process - Vehicle No Help";
	
	public String m_help_TXT_POLICY_NO_new			="";
	public String m_help_TXT_POLICY_NO_new_Header	="Credit Process - Policy No Help";
	
	public String m_help_TXT_DEBIT_NO_POLICY		  ="";
	public String m_help_TXT_DEBIT_NO_POLICY_Header	  ="Credit Process - Debit No Help";
	
	public String m_help_TXT_DEBIT_NO			="";
	public String m_help_TXT_DEBIT_NO_Header	="Credit Process - Debit No Help";	
	
	
	public String m_help_TXT_FINANCE_NO_COMMISSION			="";
	public String m_help_TXT_FINANCE_NO_COMMISSION_Header	="Credit Process - Finance No Help";	
	
	
	public String m_help_TXT_ACC_sql="";
	public String m_help_TXT_ACC_sql_Header="Application Process - Account Details ";
	
	public String m_help_TXT_SO_NO_sql="";
	public String m_help_TXT_SO_NO_sql_Header="Credit Process - Standing Order Details ";
	
	public String m_help_TXT_ACCOUNT_new_sql="";
	public String m_help_TXT_ACCOUNT_new_sql_Header="Credit Process - Cheque Details ";
	
	public String	m_ClientSql1 = " ";
	public String m_ClientSql1_Header = "Marketing - Client Help ";
	
	// added by udara 14-01-2019
	public String m_help_TXT_APPLICATION_NO_9 = " ";
	public String m_help_TXT_APPLICATION_NO_9_Header = "Credit Process - Application No Help"; 
	// added by udara 14-01-2019
	
	public String m_help_TXT_APPLICATION_NO_4 = " ";
	public String m_help_TXT_APPLICATION_NO_4_Header = "Credit Process - Application No Help"; 
	
	
	public String m_help_TXT_APPLICATION_NO_IN_DETA = " ";//Added By Sndun on 06-10-2008
	public String m_help_TXT_APPLICATION_NO_IN_DETA_Header = "Collection Process- Application No Help"; 
	
	public String m_help_TXT_FINANCE_NO_4 = " ";
	public String m_help_TXT_FINANCE_NO_4_Header = "Credit Process - Finance No Help"; 
	
	// added by udara on 18-12-2012
	public String m_help_TXT_SIEZER_CODE = " ";
	public String m_help_TXT_SIEZER_CODE_Header = "Credit Process - Seizer Code Help"; 
	
	// added by udara on 19-12-2012
	public String m_help_TXT_SIEZER_CODE_LOAD = " ";
	public String m_help_TXT_SIEZER_CODE_LOAD_Header = "Credit Process - Seizer Code Help"; 
	
	// added by udara on 19-12-2012
	public String m_help_TXT_SIEZER_CODE_LOAD_EDIT = " ";
	public String m_help_TXT_SIEZER_CODE_LOAD_EDIT_Header = "Credit Process - Seizer Code Help"; 
	
	// added by udara on 20-12-2012
	public String m_help_TXT_SIEZER_CODE_LOAD_APP = " ";
	public String m_help_TXT_SIEZER_CODE_LOAD_APP_Header = "Credit Process - Seizer Code Help"; 
	
	
	public String m_help_TXT_SIEZER_CODE_LOAD_LETTER = " ";
	public String m_help_TXT_SIEZER_CODE_LOAD_LETTER_Header = "Credit Process - Seizer Code Help"; 
	
	// added by udara on 08-01-2013
	public String m_help_DELETION_LETTER = " ";
	public String m_help_DELETION_LETTER_Header = "Credit Process - Deletion Letter Help"; 
	
	public String m_help_DELETION_LETTER_APP = "";
	public String m_help_DELETION_LETTER_APP_Header = "Credit Process - Deletion Letter Help"; 
	
	// added by udara on 09-01-2013
	public String m_help_DELETION_LETTER_GEN = "";
	public String m_help_DELETION_LETTER_GEN_Header = "Credit Process - Deletion Letter Help";
	
	public String m_help_TXT_CLIENT_NO_4 = " ";
	public String m_help_TXT_CLIENT_NO_4_Header = "Credit Process - Client No Help";
	
	public String m_help_TXT_CLIENT_NO_5 = " ";
	public String m_help_TXT_CLIENT_NO_5_Header = "Credit Process - Client No Help";
	
	public String m_help_TXT_APPLICATION_NO_5 = " ";
	public String m_help_TXT_APPLICATION_NO_5_Header = "Credit Process - Application No Help";
	
	public String m_help_TXT_APPLICATION_NO_6 = " ";
	public String m_help_TXT_APPLICATION_NO_6_Header = "CRIB Letter - Application No Help";
	
	public String m_help_TXT_FINANCE_NO_6 = " ";
	public String m_help_TXT_FINANCE_NO_6_Header = "CRIB Letter - Finance No Help";
	
	public String m_help_TXT_CLIENT_NO_6 = " ";
	public String m_help_TXT_CLIENT_NO_6_Header = "CRIB Letter - Client Help";
	
	public String m_help_TXT_CLIENT_NO_7 = " ";
	public String m_help_TXT_CLIENT_NO_7_Header = "Admistration - Client No Help"; 
	
	public String m_help_TXT_CLIENT_NO_8 = " "; //added by Prabash on 08-02-2012
	public String m_help_TXT_CLIENT_NO_8_Header = "Credit Process - Client No Help";
	
	public String m_help_TXT_APPLICATION_NO_7 = " ";
	public String m_help_TXT_APPLICATION_NO_7_Header = "Admistration - Application No Help";
	
	public String m_help_TXT_APPLICATION_NO_activated = " ";
	public String m_help_TXT_APPLICATION_NO_activated_Header = "Credit Process - Application No Help";
	
	public String m_help_FINANCE_NO_5 = " ";
	public String m_help_FINANCE_NO_5_Header = "Credit Process - Finance No Help";
	
	public String m_help_FINANCE_NO_activated = " ";
	public String m_help_FINANCE_NO_activated_Header = "Credit Process - Finance No Help";
	
	public String m_help_TXT_PRINTER_NAME_sql = " ";
	public String m_help_TXT_PRINTER_NAME_sql_Header = "System Maintenance - Printer Name";
	
	public String m_help_FINANCE_NO_termination = " ";
	public String m_help_FINANCE_NO_termination_Header = "Termination Process - Finance No Help";
	
	public String m_help_DR_CANCEL_INVOICE_NO = "";
	public String m_help_DR_CANCEL_INVOICE_NO_Header = "Credit Process - Invoce Help"; //Added By Sandun on 18-11-2008
	
	public String m_help_TXT_FINANCE_NO_IN_DETA        ="";
	public String m_help_TXT_FINANCE_NO_IN_DETA_Header = "Collection Proccess - Finance No Help";//Added By Sandun on 9-12-2008
	
	public String m_help_Company = "";	
	public String	m_help_Company_Header  ="Insurance - Company Help";	
	
	// added by udara on 23-11-2012
	
	public String   m_help_TXT_EMP_CODE_sql = "";	
	public String	m_help_TXT_EMP_CODE_sql_Header  ="Insurance - Company Help";	
	
	// added by udara on 10-04-2013
	public String   m_help_TXT_USERS_sql = "";	
	public String	m_help_TXT_USERS_sql_Header  ="Insurance - Company Help";	
	
	public String   m_help_TXT_INSU_AGENT_sql = "";	
	public String	m_help_TXT_INSU_AGENT_sql_Header  ="Insurance Agent - Company Help";	
	
	
	
	public String m_help_Finance_Is_History ="";
	public String m_help_Finance_Is_History_Header = "Insurance - Finance No Help";
	
	public String m_help_INVOICE_NO_CR_DR_NOTES ="";//Added By Sandun on 2009-01-22
	public String m_help_INVOICE_NO_CR_DR_NOTES_Header ="Invoice No Help";
	
	public String MKOfficer_help_Sql ="";//Added By Sandun on 2009-06-22
	public String MKOfficer_help_Sql_Header ="Collection Officer Help"; 
	
	public String m_help_TXT_FINANCE_NO_5 = " ";//Added By Sandun on 2009-06-22
	public String m_help_TXT_FINANCE_NO_5_Header = "Finance No Help";
	
	public String FinanceSql_Odi ="";
	public String FinanceSql_Odi_Header = "Finance No Help";
	
	public String ClientSql_odi ="";
	public String ClientSql_odi_Header = "Client Code Help";
	
	// Added by Thamali Jayatunga on 2011.07.12
	public String m_help_insurance_company_code ="";
	public String m_help_insurance_company_code_Header="Asset Insurance Details - Insurance Company Help";
	
	
	public String GroupPaymentCodeSql ="";
	public String GroupPaymentCodeSql_Header="Finance - Group Payment Help";
	
	public String ClientSq3          	    = " ";			  //Added by Prabash on 13-02-2012
	public String ClientSq3_Header   	    = "Client no Help ";
	
	public String cre_noteno          	    = " ";			  //Added by Prabash on 13-02-2012
	public String cre_noteno_Header   	    = "cre_note no Help ";
	
	public String cre_payee          	    = " ";			  //Added by Prabash on 15-02-2012
	public String cre_payee_Header   	    = "cre_note no Help ";
	
	public String cre_payee2          	    = " ";			  //Added by Prabash on 16-02-2012
	public String cre_payee2_Header   	    = "cre_note no Help ";
	
	public String cre_payee3         	    = " ";			  //Added by Prabash on 17-02-2012
	public String cre_payee3_Header   	    = "cre_note no Help ";
	
	public String cre_payee4         	    = " ";			  //Added by Prabash on 17-02-2012
	public String cre_payee4_Header   	    = "cre_note no Help ";
	
	public String receiver         	    = " ";			  //Added by Prabash on 17-02-2012
	public String receiver_Header   	    = "cre_note no Help ";
	
	
	public String DueDateChangeFinanceSql = "";// added by udara on 21-11-2012
	public String DueDateChangeFinanceSql_Header = ""; // added by udara on 21-11-2012
	
	public String m_help_TXT_BRANCH_sql = ""; // added by udara on 07-03-2013
    public String m_help_TXT_BRANCH_sql_Header = "Users"; // added by udara on 07-03-2013
	
	public String  m_help_TXT_FinanceSql2_sql = ""; // added by udara 21-10-2014
	public String  m_help_TXT_FinanceSql2_sql_Header = "Users"; // added by udara 21-10-2014
	
	
	public String  m_help_TXT_FINANCENO_WITHOUT_TERMINATION = ""; // added by udara 21-10-2014
	public String  m_help_TXT_FINANCENO_WITHOUT_TERMINATION_Header = "Finance Number"; // added by udara 21-10-2014
	
	
	
	//---------------------------------------------------------------------------------------------	
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
		
		
		
		/*----------------------------------------------------------------
			Purpose  : select clients
		
			Used in  : MK Inquiry
		-----------------------------------------------------------------*/		
		
		// added by udara 21-10-2014
		
		m_help_TXT_FinanceSql2_sql =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client   FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+ // new
			//"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE  "+ //added by nuwan de silva 25-07-07
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
			
			
			
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
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
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+m_vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ START_VAL+" AND P.NO<= "+END_VAL+" ";
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		// end by udara 21-10-2014
		
		ClientSql =   	"SELECT P.NO, P.CLIENT_CODE Client, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(TEL_NO)     LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		
		m_help_payment_no_other_payment=" SELECT L.NO,L.PAYMENT_NO,L.CLIENT_CODE,L.SETTLE_MODE,L.ENTRY_TYPE,L.PAY_AMOUNT,L.LIC_BRANCH_CODE ,L.LIC_ACC_NO,L.EFF_VALDATE,L.PAYEE_NAME,L.SUS_REF_NO,L.COMMENTS ,L.BANK_NAME,L.WHT,L.NET_AMOUNT"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYMENT_NO,P.CLIENT_CODE,P.SETTLE_MODE,P.ENTRY_TYPE,P.PAY_AMOUNT,P.LIC_BRANCH_CODE ,P.LIC_ACC_NO,P.EFF_VALDATE,P.PAYEE_NAME,P.SUS_REF_NO,P.COMMENTS ,P.BANK_NAME,P.WHT,P.NET_AMOUNT "+
			" FROM(  "+
			"	   SELECT "+
			"    PAYMENT_NO, "+
			"    NVL(CLIENT_CODE,' ') CLIENT_CODE, "+
			//"    DECODE(SETTLE_MODE,'CHQ','Cheque','Cash') SETTLE_MODE ,"+
			"    SETTLE_MODE, "+
			"    ENTRY_TYPE, "+
			"    PAY_AMOUNT, "+
			"    LIC_BRANCH_CODE, "+ 
			"    LIC_ACC_NO, "+
			"    TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE, "+
			"    PAYEE_NAME ,"+
			"    SUS_REF_NO ,"+
			"    NVL(COMMENTS,' ') COMMENTS ,"+
			"    "+m_schema_name+".AF_CO_GET_BANK_NAME(LIC_BRANCH_CODE) BANK_NAME, "+	
			"    WHT,"+
			"    NET_AMOUNT "+
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
			"	WHERE UPPER(PAYMENT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//" AND PROCESS_STATUS =UPPER('"+m_vector.elementAt(1)+"') "+
			" AND PROCESS_STATUS IN('Y','RE-APP','ENTER') "+
			" ORDER BY  PAYMENT_NO DESC )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
				Purpose  : select Inquiry Number
			
				Used in  : MK Inquiry
			-----------------------------------------------------------------*/			
		InquirySql = "SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO,ADDRESS, "+//6
			"ADDRESS2, CITY_CODE, LEGAL_ENTITY,LEAD_SOURCE_NAME,ID_NO, "+//11
			"MK_OFFICER,INITIATION_TYPE, CLIENT_CATEGORY, "+//14
			"LEAD_SOURCE_CATEGORY,INTRODUCER,"+//16
			"EMAIL, TEAM,FAX_NO,"+//19
			"MK_SUPERVISOR, CONTACT_PERSON,"+//21
			"SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE,STATUS,INQUIRY_STATUS "+//25
			"FROM "+
			"(SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO, FAX_NO,"+
			"	       ADDRESS, CITY_CODE, LEGAL_ENTITY, STATUS,"+
			"	       INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,"+
			"	       LEAD_SOURCE_NAME, INTRODUCER, ID_NO, INQUIRY_STATUS,"+
			"        ENT_USER,ADDRESS2, EMAIL, TEAM,"+
			"        MK_OFFICER, MK_SUPERVISOR, CONTACT_PERSON,"+
			"	       SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE "+
			"FROM "+
			"(SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO, "+
			" NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS, "+
			" NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS, "+
			" NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY,"+
			" NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME, "+
			" NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS,"+
			" NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM, "+
			" NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON, "+
			" NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"       UPPER(CLIENT_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       TEL_NO 			        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	      UPPER(ADDRESS)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	      UPPER(EMAIL)        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       UPPER(ID_NO) 			  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			
			" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		m_help_suspense_reference_Lawyer=
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
		
		m_help_suspense_reference=" SELECT L.NO,L.RECEIVER,L.PAYEE_NAME,L.SUSPENSE_ENTRY_TYPE /*,L.SUS_REF_NO,L.PAYER,L.REF_NO */,L.ADDRESS1,L.ADDRESS2 "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RECEIVER,P.PAYEE_NAME,P.SUSPENSE_ENTRY_TYPE/*,P.SUS_REF_NO,P.PAYER,P.REF_NO */ ,ADDRESS1,ADDRESS2"+
			" FROM(  "+
			"	SELECT "+
			"	DISTINCT RECEIVER, "+
			"	NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),'-') PAYEE_NAME, "+
			//"' ' PAYEE_NAME ,"+
			"	NVL(decode(SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Advertistment','R','Repossision'),'-') SUSPENSE_ENTRY_TYPE ,  "+
			//"	SUS_REF_NO,  "+
			//"	PAYER, "+
			//"	REF_NO "+
			"	NVL("+m_schema_name+".AF_CO_GET_REF_ADD(REF_NO,RECEIVER),'-')  ADDRESS1, "+
			//"' ' ADDRESS1 ,"+
			"	NVL("+m_schema_name+".AF_CO_GET_REF_ADD2(REF_NO,RECEIVER),'-') ADDRESS2 "+
			//"' ' ADDRESS2"+
			"	FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			"	WHERE ( UPPER(RECEIVER) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(NVL("+m_schema_name+".AF_CO_GET_REF_NAME(REF_NO,RECEIVER),'-')) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )"+
			" AND UPPER(SUSPENSE_ENTRY_TYPE) = UPPER('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_suspense_reference_Seizer=
			
			" SELECT L.NO ,L.SEIZER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS1,L.ADDRESS2,L.MOBILE_NO,L.TEL_NO,L.CITY_CODE,"+
			" L.FEE_PER_CASE,L.MONTHLY_FEE,L.DEFAULT_VALUE,NVL(L.VALIDITY_PERIOD,0) "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SEIZER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS1,P.ADDRESS2,P.MOBILE_NO,P.TEL_NO,P.CITY_CODE,"+
			" P.FEE_PER_CASE,P.MONTHLY_FEE,P.DEFAULT_VALUE,P.VALIDITY_PERIOD"+
			" FROM( "+ 
			" SELECT "+
			" SEIZER_CODE,"+
			" FIRST_NAME, "+
			" LAST_NAME,"+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" MOBILE_NO,"+
			" TEL_NO,"+
			" CITY_CODE, "+
			" FEE_PER_CASE,"+
			" MONTHLY_FEE,"+
			" DEFAULT_VALUE, "+
			" VALIDITY_PERIOD "+
			" FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
			" WHERE (UPPER(SEIZER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR"+
			"        UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   OR  "+
			"        UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND "+
			"        ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			
			" ORDER BY SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		/*----------------------------------------------------------------
				Purpose  : Select District
			
				Used in  : MK District
			-----------------------------------------------------------------*/			
		
		
		m_help_TXT_DISTRICT_CODE_sql=
			
			" SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" DISTRICT_CODE,"+
			" DISTRICT_DESC, "+
			" PROVINCE_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
			" WHERE DISTRICT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*----------------------------------------------------------------
			Purpose  : Select  Asset
		
			Used in  : MK Asset
		-----------------------------------------------------------------*/			
		
		
		m_help_TXT_ASSET_ID_sql=
			
			" SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO"+
			" FROM( "+ 
			" SELECT "+
			" ASSET_ID, "+
			" REG_NO, "+
			" REG_DATE, "+
			" MODEL_CODE, "+
			" SUB_MODEL_CODE, "+
			" PRICING_NO, "+
			" STATUS, "+
			" SUPPLIER_CODE, "+
			" QTY, "+
			" COST, "+
			" PURPOSE, "+
			" ADDRESS, "+
			" CITY_CODE, "+
			" PERIOD, "+
			" APPLICATION_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_ASSET_DETAILS "+
			" WHERE ASSET_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		/*----------------------------------------------------------------
		Purpose  : Select  Model Code
	
		Used in  : MK Model
	-----------------------------------------------------------------*/			
		
		
		m_help_TXT_MODEL_CODE_inv_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MODEL_CODE,"+
			" DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			" WHERE MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*----------------------------------------------------------------
		Purpose  : Select  Sub Model Code
	
		Used in  : MK Model
	-----------------------------------------------------------------*/			
		
		
		m_help_TXT_SUB_M_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE"+
			" FROM( "+
			" SELECT "+
			" SUB_CODE,"+ 
			" MODEL_CODE,"+
			" DESCRIPTION,"+
			" ENGINE_CAPACITY,"+
			" OPTION_TYPE,"+
			" COUNTRY_CODE,"+
			" YEAR_OF_MANUFACTURE,"+
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		m_help_licence_account_no=
			
			" SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE ,L.BRANCH_NAME,L.BANK_NAME,L.ACC_SYS_REFNO "+
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
		
		
		
		
		
		
		
		
		
		
		
		/*------------------ ID         : Performa Invoice Process ----------------------------------
		--------------------Purpose     : Invoice no Help ----------------------------------------------
		------------------- Added By    : Nuwan De Silva------------------------------------------------------
		-------------------- Date       : 07-08-2006---------------------------------------------------------
		--------------------Modified by : Yohan Gunarathna ---------------------------------------------------*/
		
		
		m_help_TXT_INVOICE_NO_sql=
			
			" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS,L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID,L.FUEL_CONVERTION_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE,P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS"+
			" FROM( "+ 
			" SELECT "+
			" A.INVOICE_NO, "+
			" A.APPLICATION_NO, "+
			" A.ASSET_ID, "+
			" A.ENGINE_NO, "+
			" A.CHASSIS_NO, "+
			" A.REG_NO, "+
			" A.REG_DATE, "+
			" A.PRICING_NO, "+
			" A.SUB_MODEL_CODE, "+
			" A.COLOUR, "+
			" A.SEATING_CAPACITY, "+
			" A.NET_PRICE, "+
			" A.VAT, "+
			" A.TOTAL_AMOUNT, "+
			" A.TO_BE_DELIVERD_TO, "+
			" A.VALUE, "+
			" A.CURR_CODE, "+
			" A.MODEL_CODE, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
			" NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO, "+
			" NVL(A.CITY_CODE,'-') CITY_CODE, "+
			" NVL(A.ADDRESS,'-') ADDRESS, "+
			" NVL(A.VENDOR_CODE,'-') VENDOR_CODE, "+
			" NVL( (SELECT B.NAME FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
			" NVL(A.BRANCH_ID,'-')  BRANCH_ID,"+
			" A.FUEL_CONVERTION_STATUS  FUEL_CONVERTION_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A"+
			" WHERE A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.APPLICATION_NO =UPPER('"+m_vector.elementAt(1)+"') " + // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Application Number
		Used in  : CRIB - Letter
		Added by Chandana on 19-09-2007
	-----------------------------------------------------------------*/												
		
		
		m_help_TXT_APPLICATION_NO_6=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
			" AND UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND (APPLICATION_STATUS IN ('ACTIVATED'))"+  
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Finance Number
		Used in  : CRIB - Letter
		Added by : Chandana on 19-09-2007
	-----------------------------------------------------------------*/												
		
		m_help_TXT_FINANCE_NO_6 = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND upper(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
			" AND APPLICATION_STATUS IN ('ACTIVATED') "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		/*----------------------------------------------------------------
				Purpose  : Select Client Number
				Used in  : CRIB - Letter
				Added by : Chandana on 19-09-2007
			-----------------------------------------------------------------*/												
		
		m_help_TXT_CLIENT_NO_6 = " SELECT DISTINCT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO FINANCE_NO ,A.APPLICATION_NO APPLICATION_NO,A.CLIENT_CODE CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
			" WHERE  UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" AND UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
			" AND A.APPLICATION_STATUS IN ('ACTIVATED') AND "+
			"        (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Application Number
		Used in  : Credit - Agreement Print
		Added by Chandana on 27-07-2007
	-----------------------------------------------------------------*/												
		
		
		m_help_TXT_APPLICATION_NO_4=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
			" AND (APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2'))"+  
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// added by udara 14-01-2019
		
		m_help_TXT_APPLICATION_NO_9=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
			" AND (APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2','VERIFY-M','VERIFY2'))"+  
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 14-01-2019
		
		
		
		m_help_TXT_APPLICATION_NO_IN_DETA =
			" SELECT DISTINCT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+//,L.PRO_INVOICE_NO "+ //Commented by Dineth on 28-07-2009
			" FROM  "+
			" (SELECT DISTINCT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+//,P.PRO_INVOICE_NO"+ //Commented by Dineth on 28-07-2009
			" FROM( "+ 
			" SELECT DISTINCT "+
			" A.APPLICATION_NO , "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
			" A.CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(A.CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') MK_OFFICER "+
			//" B.PRO_INVOICE_NO "+//Commented by Dineth on 28-07-2009
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B "+
			" WHERE (A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			" AND A.FINANCE_NO = B.FINANCE_NO "+
			" AND A.APPLICATION_STATUS = 'ACTIVATED' "+ 
			" AND B.END_DATE >= TO_DATE('"+m_vector.elementAt(2)+"','DD-MM-YYYY') "+
			" AND B.END_DATE <= TO_DATE('"+m_vector.elementAt(1)+"','DD-MM-YYYY') "+
			//" ORDER BY A.ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Finance Number
			Used in  : Credit - Agreement Print
			Added by : Chandana on 27-07-2007
		-----------------------------------------------------------------*/												
		
		m_help_TXT_FINANCE_NO_4 = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//" AND upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+ remove by waruna 2014-06-18
			//" AND APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2') "+ // commented by udara 11-02-2014
			" AND APPLICATION_STATUS NOT IN ('NORM_TERMI','TERMI', 'TERMINATED') "+ // added by udara 11-02-2014
			" AND A.FINANCE_NO NOT IN (SELECT DISTINCT B.FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B WHERE B.ACTIVE_STATUS != 'CANCEL' ) "+ // added by udara 30-07-2015
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		//Added by: Samith Dilshan on 2015-25-27
		m_help_TXT_FINANCENO_WITHOUT_TERMINATION = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND APPLICATION_STATUS NOT IN ('NORM_TERMI','TERMI', 'TERMINATED') "+ 
			" AND A.FINANCE_NO NOT IN (SELECT DISTINCT B.FINANCE_NO FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B WHERE B.ACTIVE_STATUS != 'CANCEL') "+ 
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		// added by udara on 18-12-2012
		m_help_TXT_SIEZER_CODE = " "+
			" SELECT P.NO,SEIZER_CODE,SEIZER_NAME "+
				 " FROM "+
				 " (SELECT ROWNUM NO,SEIZER_CODE,SEIZER_NAME "+
				    " FROM "+
				    " ( "+
				        " SELECT A.SEIZER_CODE, "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME "+
				        " FROM   "+m_schema_name+".AF_CO_MAS_SEIZER A "+
				        " WHERE  UPPER(A.SEIZER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
				        " ORDER BY A.SEIZER_CODE DESC "+
				    " ) "+ 
				 " ) P  "+       
				 " WHERE P.NO>= 0 AND P.NO<= 100 ";
		// end by udara on 18-12-2012
		
		
		// added by udara on 19-12-2012
		m_help_TXT_SIEZER_CODE_LOAD = " "+
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
			" AND (APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2'))"+  
			" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CO_MAS_SEIZER_DETAILS A WHERE A.APPLICATION_NO = APPLICATION_NO)   "+
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		// end by udara on 19-12-2012
		
		m_help_TXT_SIEZER_CODE_LOAD_EDIT = " "+
			" SELECT L.NO,L.APPLICATION_NO,L.FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.SEIZER_CODE "+
			" FROM ( "+
			    " SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.SEIZER_CODE "+
			    " FROM( "+
			        " SELECT  "+
			        " A.APPLICATION_NO, "+
			        " A.FINANCE_NO, "+
			        " A.CLIENT_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
			        " B.SEIZER_CODE "+
			            " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_SEIZER_DETAILS B "+
			            " WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
			            " AND ( "+
			                " A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
			                " A.FINANCE_NO     LIKE '%"+m_vector.elementAt(0)+"%' OR  "+
			                " A.CLIENT_CODE    LIKE '%"+m_vector.elementAt(0)+"%' "+
			            " ) "+
						" AND B.ACTIVE_STATUS NOT IN ('P','Y')  "+
			    " )P "+
			" )L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara on 20-12-2012
		m_help_TXT_SIEZER_CODE_LOAD_APP = " "+
			" SELECT L.NO,L.APPLICATION_NO,L.SEIZER_CODE, L.SEIZER_NAME "+
				" FROM ( "+
				    " SELECT ROWNUM NO,P.APPLICATION_NO,P.SEIZER_CODE, P.SEIZER_NAME "+
				    " FROM( "+
				        " SELECT "+
							" A.APPLICATION_NO, "+
							" A.SEIZER_CODE, "+
							" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME "+
							  " FROM "+m_schema_name+".AF_CO_MAS_SEIZER_DETAILS A "+
							  " WHERE (A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%' OR A.SEIZER_CODE LIKE '%"+m_vector.elementAt(0)+"%') "+
							  " AND A.ACTIVE_STATUS = 'N' "+  
				    " )P "+
				" )L "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_SIEZER_CODE_LOAD_LETTER = " "+
			" SELECT L.NO,L.APPLICATION_NO,L.SEIZER_CODE, L.SEIZER_NAME "+
				" FROM ( "+
				    " SELECT ROWNUM NO,P.APPLICATION_NO,P.SEIZER_CODE, P.SEIZER_NAME "+
				    " FROM( "+
				        " SELECT "+
							" A.APPLICATION_NO, "+
							" A.SEIZER_CODE, "+
							" "+m_schema_name+".AF_CO_GET_SEIZER_NAME(A.SEIZER_CODE) SEIZER_NAME "+
							  " FROM "+m_schema_name+".AF_CO_MAS_SEIZER_DETAILS A "+
							  " WHERE (A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%' OR A.SEIZER_CODE LIKE '%"+m_vector.elementAt(0)+"%') "+
							  " AND A.ACTIVE_STATUS = 'Y' "+  
				    " )P "+
				" )L "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		// end by udara on 20-12-2012
		
		
		
		// added by udara on 08-01-2013
		m_help_DELETION_LETTER = " "+
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
			" AND (APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2'))"+  
			" AND APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CO_MAS_DELE_LET_DETAILS A WHERE A.APPLICATION_NO = APPLICATION_NO)   "+
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		

		m_help_DELETION_LETTER_APP = " "+
			" SELECT L.NO,L.APPLICATION_NO  "+
				" FROM ( "+
				    " SELECT ROWNUM NO,P.APPLICATION_NO  "+
				    " FROM( "+
				        " SELECT "+
							" A.APPLICATION_NO "+
							  " FROM "+m_schema_name+".AF_CO_MAS_DELE_LET_DETAILS A "+
							  " WHERE A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%'  "+
							  " AND A.ACTIVE_STATUS = 'N' "+  
				    " )P "+
				" )L "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_DELETION_LETTER_GEN = " "+
			" SELECT L.NO,L.APPLICATION_NO "+
				" FROM ( "+
				    " SELECT ROWNUM NO,P.APPLICATION_NO "+
				    " FROM( "+
				        " SELECT "+
							" A.APPLICATION_NO "+
							  " FROM "+m_schema_name+".AF_CO_MAS_DELE_LET_DETAILS A "+
							  " WHERE A.APPLICATION_NO LIKE '%"+m_vector.elementAt(0)+"%'  "+
							  " AND A.ACTIVE_STATUS = 'Y' "+  
				    " )P "+
				" )L "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		// end by udara on 08-01-2013

		
		
		
		/*----------------------------------------------------------------
				Purpose  : Select Client Number
				Used in  : Credit - Agreement Print
				Added by : Chandana on 27-07-2007
			-----------------------------------------------------------------*/												
		
		m_help_TXT_CLIENT_NO_4 = " SELECT DISTINCT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO FINANCE_NO ,A.APPLICATION_NO APPLICATION_NO,A.CLIENT_CODE CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
			" WHERE  UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" AND UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
			" AND A.APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2') AND "+
			"        (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Client Number
			Used in  : Credit - Credit Verification 
			Added by : Prabash on 08-02-2012
		-----------------------------------------------------------------*/												
		m_help_TXT_CLIENT_NO_8 = " SELECT DISTINCT P.NO,CLIENT_CODE,CLIENT_NAME ,REG_NO Vehicle_No,FINANCE_NO Contract_Number"+
			" FROM "+
			" (SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME,REG_NO,FINANCE_NO"+
			" FROM "+
			" (SELECT A.CLIENT_CODE CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME ,NVL(C.REG_NO,'-')REG_NO,B.FINANCE_NO FINANCE_NO "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C"+
            "		WHERE "+
            "		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
            "		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"       (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(C.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        A.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"	       ) "+
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		//--Added by Prabash on 15-02-2012---Credit Note No  chack for--Finance - Credit Note Approve/Cancellation --* 	
		
		cre_noteno = 		"SELECT P.NO,P.INVOICE_NO Credit_Note_No,P.FINANCE_NO FINANCE_NO ,P.CLIENT_CODE CLIENT_CODE, P.REF_NO REF_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,INVOICE_NO,FINANCE_NO,CLIENT_CODE,REF_NO "+
			"FROM "+
			"(SELECT A.INVOICE_NO ,A.FINANCE_NO,B.CLIENT_CODE,A.REF_NO 	"+					
			"	FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A,	 "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	WHERE A.FINANCE_NO = B.FINANCE_NO	    "+
			//	"	AND A.ACTIVE_STATUS NOT IN ('C','A') 	    "+
			"	AND A.CREDIT_TYPE = 'CR' 					"+
			"   AND (UPPER( A.FINANCE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      UPPER( A.REF_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      UPPER( B.CLIENT_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR   "+
			"      UPPER(A.INVOICE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%'))		"+
			
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		//--Added by Prabash on 15-02-2012--Client  chack for--Finance - Credit Note Approve/Cancellation --* 	
		
		ClientSq3 = 		"SELECT P.NO, P.CLIENT_CODE CLIENT_CODE,CLIENT_NAME CLIENT, P.ACTIVE_STATUS ACTIVE_STATUS "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME,ACTIVE_STATUS	 "+
			"FROM "+
			"(SELECT DISTINCT B.CLIENT_CODE , 									"+
			"	"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)CLIENT_NAME,	"+
			"	A.ACTIVE_STATUS							    			"+
			"	FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A ,   				"+
			"	"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B 	    			"+
			"	WHERE A.FINANCE_NO = B.FINANCE_NO 		    			"+
			//		"	AND A.ACTIVE_STATUS NOT IN ('C','A') 	    			"+
			"	AND A.CREDIT_TYPE = 'CR' 				    			"+
			" AND (UPPER( B.CLIENT_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      UPPER( "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%'))   "+			
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//-------------------------------------------------------------------------------------------*
		//--Added by Prabash on 16-02-2012---Credit Note No  chack for--Finance -Other Payments Authorization --* 	
		
		cre_payee = 		"SELECT P.NO,P.PAYEE_NAME payee_name,P.PAYMENT_NO Payment_no ,SETTLE_MODE Settle_mode "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME,PAYMENT_NO,SETTLE_MODE "+
			"FROM "+
			"(SELECT DISTINCT PAYEE_NAME,PAYMENT_NO,DECODE(SETTLE_MODE,'CHQ','CHEQUE','CASH')SETTLE_MODE"+
			"	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT"+
			"	WHERE PROCESS_STATUS = 'AUTHO'"+
			"	AND ENTRY_TYPE <> 'V'"+
			"   AND (UPPER( PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"      UPPER(PAYMENT_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%'))   "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		//--Added by Prabash on 16-02-2012---Credit Note No  chack for--Finance -View Payment Voucher --* 	
		
		cre_payee2 = 		"SELECT P.NO,P.CLIENT_CODE CLIENT_CODE,P.PAYEE_NAME PAYEE_NAME ,P.PAYMENT_NO PAYMENT_NO,P.SUS_REF_NO REF_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,PAYEE_NAME,PAYMENT_NO,SUS_REF_NO "+
			"FROM "+
			"(SELECT   CLIENT_CODE,"+
			"  NVL(PAYEE_NAME,'-')PAYEE_NAME ,"+
			" PAYMENT_NO, "+
			" SUS_REF_NO "+
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
			"	WHERE (UPPER( PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER( CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER( PAYMENT_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR "+
			"       UPPER(SUS_REF_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) 		"+
			" AND   UPPER( PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		//---------Added by Prabash on 17-02-2012-----Payee  chack for--Finance - Payments Clearing---------*
		
		cre_payee3 = 		"SELECT P.NO,P.PAYEE_NAME PAYEE_NAME,P.AG_NO AG_NO,P.REF_NO Debit_Note_No,P.RECEIVER RECEIVER "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME,AG_NO,REF_NO,RECEIVER "+
			"FROM "+
			"(SELECT DISTINCT NVL ("+m_schema_name+".AF_CO_GET_REF_NAME (REF_NO, RECEIVER),' ') PAYEE_NAME,"+
			"	NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO (REF_NO)),' ') AG_NO, "+
			"	REF_NO, RECEIVER "+
			"	FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+ 
			"	WHERE UPPER (SUSPENSE_ENTRY_TYPE) = UPPER ('INSURANCE') "+ 
			"	AND BAL_TO_BE_PAID > 0 "+
			"	AND(UPPER( "+m_schema_name+".AF_CO_GET_REF_NAME (REF_NO, RECEIVER))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER( REF_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER(NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO ("+m_schema_name+".AF_CO_GET_FIN_NO (REF_NO)),' ')) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR "+
			"       UPPER(RECEIVER)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) 		"+
			" AND   UPPER( "+m_schema_name+".AF_CO_GET_REF_NAME (REF_NO, RECEIVER))  LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"ORDER BY ref_no"+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		//---------Added by Prabash on 17-02-2012-----Payee  chack for--Finance - Payment Deletion ---------*
		
		cre_payee4 = "SELECT P.NO,P.PAYEE_NAME PAYEE_NAME,P.PAYMENT_NO PAYMENT_NO,P.CLIENT_CODE CLIENT_CODE,P.FINANCE_NO FINANCE_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME,PAYMENT_NO,CLIENT_CODE,FINANCE_NO "+
			"FROM "+
			"(SELECT NVL(A.PAYEE_NAME,'-')PAYEE_NAME,"+
            "	A.PAYMENT_NO,	"+
            "	A.CLIENT_CODE, 	"+
            "	NVL(A.FINANCE_NO,'-') FINANCE_NO	"+
	       	" 	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A	"+
			"	WHERE (UPPER(PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER( PAYMENT_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			"       UPPER(A.CLIENT_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) 	"+
			" AND   UPPER( PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		
		//---------Added by Prabash on 17-02-2012-----Payee  chack for--Finance - Payment Reversal ---------*
		
		receiver = "SELECT P.NO,P.PAYEE_NAME RECEIVER,P.GROUP_PAYMENT_NO PAYMENT_NO,P.PAY_AMOUNT PAY_AMOUNT,P.SETTLE_MODE SETTLE_MODE "+
			"FROM "+
			"(SELECT ROWNUM NO,PAYEE_NAME,GROUP_PAYMENT_NO,PAY_AMOUNT,SETTLE_MODE "+
			"FROM "+
			"(SELECT NVL(B.PAYEE_NAME, '-') PAYEE_NAME, "+
       		"	B.GROUP_PAYMENT_NO,   "+
        	"	SUM(A.SETTELED_AMOUNT) PAY_AMOUNT, "+
        	"	DECODE(B.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash', '-') SETTLE_MODE "+       
            "    FROM   "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+
            "    "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B "+
            "    WHERE  A.PAYMENT_NO = B.PAYMENT_NO "+
            "    AND    A.ENTRY_TYPE = 'V' "+
            "    AND    B.GROUP_PAYMENT_NO IS NOT NULL"+
			"	 AND (UPPER(B.PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR 	"+
			" 		  UPPER( B.GROUP_PAYMENT_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			" 	AND   UPPER( B.PAYEE_NAME)  LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" 	GROUP BY B.GROUP_PAYMENT_NO, "+
            "                  B.PAYEE_NAME, "+
            "                  B.SETTLE_MODE "+
			"  )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		//--------------------------------------------------------------------------------------------------*
		
		/*----------------------------------------------------------------
	
	
		Purpose  : Select Client Number
  	Used in  : Credit - Revese Application
		Added by : Chandana on 09-08-2007
	-----------------------------------------------------------------*/		
		
		
		m_help_TXT_CLIENT_NO_5 = " SELECT P.NO, P.CLIENT_CODE Client, FULL_NAME,ID_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ID_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			" FROM "+
			" (SELECT  DISTINCT A.CLIENT_CODE, NVL(A.FULL_NAME,'-') FULL_NAME,NVL(DECODE(CLIENT_TYPE,'C', BUSINESS_CERTIFICATE_NO,'I',NIC_NO),'-') ID_NO, "+
			" NVL(A.ADDRESS1,'-')ADDRESS1 ,NVL(A.ADDRESS2,'-')ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-')MOBILE_NO ,NVL(A.EMAIL,'-') EMAIL, "+
			" NVL(A.ACTIVE_STATUS,'-') ACTIVE_STATUS, NVL(A.TEMP_ACTIVE_STATUS,'-') TEMP_ACTIVE_STATUS ,NVL(A.CITY_CODE,'-') CITY_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE  A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			" (UPPER(B.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" UPPER(B.FINANCE_NO)      LIKE UPPER('%"+m_vector.elementAt(2)+"%')) "+
			//" ORDER BY FULL_NAME 
			"	)) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		
		// commented by udara 11-06-2015
		/*
		m_help_TXT_APPLICATION_NO_5 = " SELECT P.NO, P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME, "+
			"           NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
			" FROM "+
			" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			//" WHERE APPLICATION_STATUS IN ('ACTIVATED','ENT_CON') AND "+ // commented by udara 07-04-2015 // ACTIVATED // add enterd for refund
			
			" WHERE   "+ // added by udara 07-04-2015
			
			//Added by Dineth on 15-04-2009
			//" FINANCE_NO NOT IN "+ // commented by udara 07-04-2015
			" FINANCE_NO  IN "+ // added by udara 07-04-2015
			
			//" (SELECT "+
			//" REF_NO "+
			//" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//" WHERE INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+
			//" 	SUS_REF_NO NOT IN ( "+
			// " 	SELECT NVL(SUS_REF_NO,'-')  "+ 
			//" 	-- PROCESS_STATUS "+
			//" 	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
			// " 	)  "+
			// " ) AND "+
			" (SELECT DISTINCT A.FINANCE_NO FROM "+m_schema_name+".af_co_pro_application_details A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
			" WHERE A.finance_no=B.finance_no AND B.invoice_no IN(SELECT "+
			" REF_NO "+ 
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//" WHERE  INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+ // commented by udara 27-05-2015
			" WHERE  "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+ // added by udara 27-05-2015
			" SUS_REF_NO NOT IN ( "+ 
			" SELECT NVL(SUS_REF_NO,'-') "+   
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+ 
			" ))) AND "+
			//End by Dineth on 15-04-2009
			
			//" FINANCE_NO NOT IN (SELECT C.FINANCE_NO FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA C) AND "+ // added by udara 27-05-2015
			
			" (UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+	
			" ORDER BY ENT_DATE  DESC))P "+ 
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		*/
		
		m_help_TXT_APPLICATION_NO_5 = " SELECT P.NO, P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME, "+
									  " NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
												" FROM ( "+
														" SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
														" FROM ( "+
																		" SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																		" WHERE APPLICATION_STATUS IN ( "+
																						" 'ENTERED', "+
																						" 'ENT_CON', "+
																						" 'V-APP', "+
																						" 'V-RECOM', "+
																						" 'VERIFYL', "+
																						" 'VERIFY-M', "+
																						" 'VERIFY2', "+
																						" 'VERIFY1'  "+
																		" )  "+ 
												        " )"+
												" )P "+ 
												" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		// end by udara 11-06-2015
		
		
		m_help_TXT_APPLICATION_NO_activated =" SELECT P.NO, P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,NVL(FINANCE_NO,'-') FINANCE_NO ,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_STATUS='ENT_CON' AND "+  // ACTIVATED
			" (UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+	
			" AND FINANCE_NO IS NOT NULL  "+
			" AND ACTIVATED_DATE IS NOT NULL"+
			" ORDER BY ENT_DATE  DESC))P "+ 
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		/***
		m_help_FINANCE_NO_5 =" SELECT P.NO, P.FINANCE_NO,P.CLIENT_NAME,P.APPLICATION_NO,P.CLIENT_CODE, "+
								"           NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
								" FROM "+
													" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
													" FROM "+
													" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,LAKDL.af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
													" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
													" WHERE APPLICATION_STATUS='ACTIVATED' AND "+  //ACTIVATED
													//Added by Dineth on 15-04-2009
													" FINANCE_NO NOT IN "+
													//" (SELECT "+
													//" REF_NO "+
													//" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
													//" WHERE INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+
													//" 	SUS_REF_NO NOT IN ( "+
													//" 	SELECT NVL(SUS_REF_NO,'-') "+ 
														//" 	-- PROCESS_STATUS "+
													//" 	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
													//" 	)  "+
													//" ) AND "+
													" (SELECT DISTINCT A.FINANCE_NO FROM "+m_schema_name+".af_co_pro_application_details A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
							" WHERE A.finance_no=B.finance_no AND B.invoice_no IN(SELECT "+
													" REF_NO "+ 
													" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
													" WHERE  INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+ 
													" SUS_REF_NO NOT IN ( "+ 
													" SELECT NVL(SUS_REF_NO,'-') "+   
													" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+ 
							" ))) AND "+
												//End by Dineth on 15-04-2009	
													" UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
													" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
													" (UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') OR "+
													" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')OR "+
													" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(2)+"%')) "+
													" ORDER BY ENT_DATE  DESC))P "+    
							" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			***/										
		
		// commented by udara 11-06-2015
		/*
		m_help_FINANCE_NO_5 =" SELECT P.NO, P.FINANCE_NO,P.CLIENT_NAME,P.APPLICATION_NO,P.CLIENT_CODE, "+
			"           NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
			" FROM "+
			" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			//" WHERE APPLICATION_STATUS='ACTIVATED' AND "+  //ACTIVATED // commented by udara 07-04-2015
			" WHERE   "+ // added by udara 07-04-2015
			//Added by Dineth on 15-04-2009
			" FINANCE_NO  IN "+
			//" (SELECT "+
			//" REF_NO "+
			//" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//" WHERE INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+
			//" 	SUS_REF_NO NOT IN ( "+
			//" 	SELECT NVL(SUS_REF_NO,'-') "+ 
			//" 	-- PROCESS_STATUS "+
			//" 	FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
			//" 	)  "+
			//" ) AND "+
			" (SELECT DISTINCT A.FINANCE_NO FROM "+m_schema_name+".af_co_pro_application_details A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
			" WHERE A.finance_no=B.finance_no AND B.invoice_no IN(SELECT "+
			" REF_NO "+ 
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			//" WHERE  INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+ // commented by udara 25-05-2015
			" WHERE  "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+  // added by udara 25-05-2015
			" SUS_REF_NO NOT IN ( "+ 
			" SELECT NVL(SUS_REF_NO,'-') "+   
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+ 
			" ))) AND "+
			
			//" FINANCE_NO NOT IN (SELECT C.FINANCE_NO FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA C) AND "+ // added by udara 25-05-2015
			
			
			//End by Dineth on 15-04-2009	
			" UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" (UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') OR "+
			" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')OR "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(2)+"%')) "+
			" ORDER BY ENT_DATE  DESC))P "+    
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		*/
		
		// added by udara 11-06-2015
		m_help_FINANCE_NO_5 = " SELECT P.NO, P.FINANCE_NO,P.CLIENT_NAME,P.APPLICATION_NO,P.CLIENT_CODE, "+
								" NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
								" FROM ( "+
											" SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
											" FROM ("+
													" SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
													" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
													/*
													" WHERE   FINANCE_NO  IN  ( "+ 
													" SELECT DISTINCT A.FINANCE_NO FROM "+m_schema_name+".af_co_pro_application_details A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
													" WHERE A.finance_no=B.finance_no AND B.invoice_no IN(SELECT "+
													" REF_NO "+ 
													" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
													" WHERE  INT_BAL_SETTLE_AMOUNT=0 AND "+m_schema_name+".AF_CO_GET_SUB_CHARG_ACC_TYPE(SUSPENSE_ENTRY_TYPE)='L' AND "+ // commented by udara 25-05-2015
													" SUS_REF_NO NOT IN ( "+ 
													" SELECT NVL(SUS_REF_NO,'-') "+   
													" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+ 
													" )))  "+
													*/
													
													" WHERE APPLICATION_STATUS IN ( "+
																						" 'ENTERED', "+
																						" 'ENT_CON', "+
																						" 'V-APP', "+
																						" 'V-RECOM', "+
																						" 'VERIFYL', "+
																						" 'VERIFY-M', "+
																						" 'VERIFY2', "+
																						" 'VERIFY1' "+
													" )  "+ 
										
													" AND UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
													" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
													" (UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') OR "+
													" UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')OR "+
													" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(2)+"%')) "+
													" ORDER BY ENT_DATE  DESC "+
											" ) "+
									")P "+    
								" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
							// end by udara 11-06-2015
		
		
		
		m_help_FINANCE_NO_activated =" SELECT P.NO, P.FINANCE_NO,P.CLIENT_NAME,P.APPLICATION_NO,P.CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,NVL(FINANCE_NO,'-') FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_STATUS='ENT_CON' AND "+  //ACTIVATED
			" UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			"  UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" ( UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') OR "+
			"  UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(2)+"%')OR "+
			"  UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(2)+"%')) "+
			" AND FINANCE_NO IS NOT NULL  "+
			" AND ACTIVATED_DATE IS NOT NULL"+
			"  ORDER BY ENT_DATE  DESC))P "+    
			"  WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		/*----------------------------------------------------------------
			Purpose  : Select Client Name,Termination No,Termination Date,Client Code
			Used in  : Assert Finance-Leasing/Loans
			Added by : Indika on 22-08-2008
		-----------------------------------------------------------------*/
		
		m_help_FINANCE_NO_termination =" SELECT P.NO, P.FINANCE_NO,P.CLIENT_NAME,P.APPLICATION_NO,P.CLIENT_CODE, "+
			"  NVL("+m_schema_name+".AF_CO_GET_CONTRACT_REC_AMT(P.FINANCE_NO),0) Rec_amt "+
			" FROM "+
			" (SELECT ROWNUM NO ,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_STATUS=UPPER('"+m_vector.elementAt(1)+"') AND "+  
			" (UPPER(APPLICATION_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  UPPER(CLIENT_CODE)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR"+
			"  UPPER(FINANCE_NO)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" ORDER BY ENT_DATE  DESC))P "+    
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Client Number
			Used in  : Adminstration client comment screem
			Added by : Chandana on 03-10-2007
		-----------------------------------------------------------------*/												
		
		m_help_TXT_CLIENT_NO_7 = " SELECT L.NO,L.CLIENT_CODE,L.CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.CLIENT_CODE CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME "+ 
			" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" ( UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE ))L "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";
		
		
		m_help_TXT_APPLICATION_NO_7	= " SELECT L.NO,L.APPLICATION_NO,L.FINANCE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,NVL(FINANCE_NO,'-') FINANCE_NO "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')))L "+										
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Application Number
		
			Used in  : MK Application
		-----------------------------------------------------------------*/												
		
		
		m_help_TXT_APPLICATION_NO=
			
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FACILITY_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			//  " DISTRICT_CODE, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND (APPLICATION_STATUS=('"+m_vector.elementAt(1)+"'))"+  // OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Application Number
		
			Used in  : Credit Add Guarantor
			
			Added by Mahela on 21-02-2007
		-----------------------------------------------------------------*/												
		
		
		m_help_TXT_APPLICATION_NO_3=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND (APPLICATION_STATUS=('ACTIVATED'))"+  // OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')
			//" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*----------------------------------------------------------------
			Purpose  : Select Finance Number
		
			Used in  : Credit Add Guarantor
			
			Added by Mahela on 21-02-2007
		-----------------------------------------------------------------*/												
		
		FinanceSql_add_gua =  " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS=('ACTIVATED')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		//=================================== Add By Indika =============================================================
		
		/*FinanceSql_change_cap_allow =  " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
																		" FROM "+
																		" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
																		" FROM "+
																		" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
																		" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
																		" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
																		" WHERE   upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
																		" AND APPLICATION_STATUS=('ACTIVATED')"+
																	" ORDER BY FINANCE_NO DESC)) P "+		
																	" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			
			*
		//=================================== End by Indika =======================================================================
		
		/*----------------------------------------------------------------
			Purpose  : Select Finance Number
		
			Used in  : Change Capital Allowance
			
			Added by Mahela on 22-02-2007
		-----------------------------------------------------------------*/												
		
		FinanceSql_change_cap_allow =  " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND APPLICATION_STATUS=('ACTIVATED')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		/*----------------------------------------------------------------
				Purpose  : Select Client Number
			
				Used in  : Change Capital Allowance
				
				Added by Mahela on 21-02-2007
			-----------------------------------------------------------------*/												
		
		ClientSql_change_cap_allow =   " SELECT DISTINCT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO FINANCE_NO ,A.APPLICATION_NO APPLICATION_NO,A.CLIENT_CODE CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
			" WHERE  UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND A.APPLICATION_STATUS=('ACTIVATED') AND "+
			"        (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Invoice Number
		
			Used in  : Change Capital Allowance
			
			Added by Mahela on 22-02-2007
		-----------------------------------------------------------------*/	
		
		InvoiceSql_change_cap_allow=	" SELECT L.NO ,L.INVOICE_NO,L.VEHICLE_NO,L.ENGINE_NO,L.CHASSIS_NO,L.TOTAL_AMOUNT,L.NET_PRICE,L.CAPITAL_ALLOWANCE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.VEHICLE_NO,P.ENGINE_NO,P.CHASSIS_NO,P.TOTAL_AMOUNT,P.NET_PRICE,P.CAPITAL_ALLOWANCE  "+
			" 	FROM( "+
			" 		SELECT "+
			"   		NVL(A.INVOICE_NO,'-') INVOICE_NO,  "+
			"   		NVL(A.VEHICLE_NO,'-') VEHICLE_NO, "+
			"   		NVL(A.ENGINE_NO,'-') ENGINE_NO, "+
			"				NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
			"   		NVL(A.TOTAL_AMOUNT,0) TOTAL_AMOUNT, "+
			"   		NVL(A.NET_PRICE,0) NET_PRICE, "+
			"   		NVL(A.CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE   "+
			" 		FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A  "+ 
			" 		WHERE  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			"  		A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+ 
			"  		A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			"    )P)L   "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*----------------------------------------------------------------
			Purpose  : Select Client Number
		
			Used in  : Credit Add Guarantor
			
			Added by Mahela on 21-02-2007
		-----------------------------------------------------------------*/												
		
		ClientSql_add_gua =   " SELECT DISTINCT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT A.FINANCE_NO FINANCE_NO ,A.APPLICATION_NO APPLICATION_NO,A.CLIENT_CODE CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B"+
			" WHERE  UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" AND UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
			" AND A.APPLICATION_STATUS=('ACTIVATED') AND "+
			"        (UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		/*----------------------------------------------------------------
			Purpose  : Select Guarantor 
		
			Used in  : Credit Add Guarantor
			
			Added by Mahela on 21-02-2007
		-----------------------------------------------------------------*/							
		
		GuarantorSql_add_gua=   		    	
			" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic,L.CLIENT_TYPE,NVL(L.BUSINESS_CERTIFICATE_NO,'-') REG_NO "+
			"FROM "+
			"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO,P.CLIENT_TYPE,P.BUSINESS_CERTIFICATE_NO "+
			"FROM "+
			"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CLIENT_TYPE,BUSINESS_CERTIFICATE_NO "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"  WHERE  "+
			"       ( UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=('Y') "+
			//"				 AND AF_GUARANTORS='Y'	"+ // commented by udara 27-03-2019
			" ORDER BY FULL_NAME )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";												
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Client 
		
			Used in  : MK Client
		-----------------------------------------------------------------*/							
		
		m_help_TXT_CLIENT_CODE=   		    	
			" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic "+
			"FROM "+
			"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO "+
			"FROM "+
			"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"  WHERE "+m_schema_name+".AF_CO_VAL_CLIENT(CLIENT_CODE,'"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')='NO' AND "+
			//	"	 WHERE (CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       ( FULL_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=('"+m_vector.elementAt(3)+"')  "+
			" ORDER BY FULL_NAME )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Inqury
	
		Used in  : MK Inqury
	-----------------------------------------------------------------*/							
		
		m_help_TXT_INQUARY_NO=
			"SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,"+
			"	       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,"+
			"	       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,"+
			"	       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,"+
			"        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,"+
			"        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,"+
			"	       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE "+
			" FROM  "+
			
			"(SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,"+
			"	       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,"+
			"	       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,"+
			"	       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,"+
			"        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,"+
			"        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,"+
			"	       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE "+
			
			
			
			" FROM "+ 
			" (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO, "+
			" NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS, "+
			" NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS, "+
			" NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY,"+
			" NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME, "+
			" NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS,"+
			" NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM, "+
			" NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON, "+
			" NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			
			" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"       UPPER(CLIENT_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       TEL_NO 			        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	      UPPER(EMAIL)        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       UPPER(ID_NO) 			  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+				
			" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";								
		
		m_help_TXT_MAKE=
			
			" SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC "+
			" FROM( "+ 
			" SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_MAKE A,"+m_schema_name+".AF_CO_MAS_MODEL B "+
			" WHERE A.MAKE_CODE=B.MAKE_CODE AND A.MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND B.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_MODEL_CODE_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
			" WHERE A.MODEL_CODE=B.MODEL_CODE AND A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND B.SUB_CODE LIKE  UPPER('%"+m_vector.elementAt(2)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_SUB_MODEL_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE"+
			" FROM( "+
			" SELECT "+
			" DISTINCT SUB_CODE,"+ 
			" MODEL_CODE,"+
			" DESCRIPTION,"+
			" ENGINE_CAPACITY,"+
			" OPTION_TYPE,"+
			" COUNTRY_CODE,"+
			" YEAR_OF_MANUFACTURE,"+
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select invoice Number
	
		Used in  : Purchase Order
	-----------------------------------------------------------------*/							
		
		
		
		/* " SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT,L.GROSS_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT,P.GROSS_AMOUNT "+
			" FROM( "+
			" SELECT "+
		" A.INVOICE_NO, "+
		" A.ASSET_ID, "+
		" A.ENGINE_NO, "+
		" A.CHASSIS_NO, "+
		" A.NET_PRICE, "+
		" A.VAT, "+
		" (A.NET_PRICE +A.VAT) GROSS_AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
		// " WHERE A.APPLICATION_NO IN "+
		//  " ( SELECT "+
		// " APPLICATION_NO "+
		// " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
		" WHERE  SUPPLIER LIKE UPPER('"+m_vector.elementAt(0)+"%') AND APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(1)+"%') AND A.INVOICE_NO LIKE UPPER('"+m_vector.elementAt(2)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') AND A.CURR_CODE IN (SELECT CURR_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO LIKE UPPER('"+m_vector.elementAt(2)+"%'))"+
		" AND A.INVOICE_NO NOT IN(SELECT "+
			" DISTINCT    PRO_INVOICE_NO "+
		" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET "+
		" WHERE PURCHASE_ORDER_NO IN "+
		" (SELECT "+
		" PURCHASE_ORDER_NO "+
		" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
		" WHERE   VENDER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%'))) "+
		// ")  ) "+
		"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		*/
		
		m_help_TXT_INVOICE_PURCHASE=	
			" SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT,L.GROSS_AMOUNT,L.CAPITAL_ALLOWANCE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT,P.GROSS_AMOUNT,P.CAPITAL_ALLOWANCE  "+
			" FROM( "+
			" SELECT "+
			"   A.INVOICE_NO,  "+
			"   A.ASSET_ID, "+
			"   NVL(A.ENGINE_NO,'-') ENGINE_NO, "+
			"   NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+//LAKDL.AF_CO_GET_TERMINATED_AMOUNT
			" (A.NET_PRICE-ROUND(DECODE(TER_TYPE,'ENHA_DOWN',"+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(C.TERMINATION_NO),0))) NET_PRICE, "+//"+m_schema_name+".AF_CO_GET_TERMINATED_AMT(TERMINATION_NO)
			" A.VAT, "+
			" (A.NET_PRICE + A.VAT-ROUND(DECODE(TER_TYPE,'ENHA_DOWN',"+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(C.TERMINATION_NO),0))) GROSS_AMOUNT, "+
			//"   A.NET_PRICE, "+
			//"   A.VAT,  "+
			//"   (A.NET_PRICE + A.VAT) GROSS_AMOUNT, "+
			//"   NVL(B.CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE   "+
			"   DECODE(C.TRANSACTION_TYPE,'FINLEASE',NVL(B.CAPITAL_ALLOWANCE,0),0) CAPITAL_ALLOWANCE  "+
			"   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+ 
			"   WHERE  VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			"   A.APPLICATION_NO=C.APPLICATION_NO AND "+
			"   A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			"  A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND "+ 
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') AND "+ 
			"   A.CURR_CODE IN "+
			"  (SELECT CURR_CODE FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			"  WHERE INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%'))  "+
			"   AND A.INVOICE_NO NOT IN(SELECT  "+
			" 	DISTINCT  PRO_INVOICE_NO  "+
			"   FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_DET  "+
			"   WHERE PURCHASE_ORDER_NO IN  "+
			"   (SELECT  "+
			"   PURCHASE_ORDER_NO  "+
			"   FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER  "+
			"   WHERE   VENDER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%'))) AND "+
			"   B.ITEM_SUB_CAT=  "+
			"      (SELECT ITEM_SUB_CAT "+
			"             FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			"             WHERE  MODEL_CODE=( SELECT MODEL_CODE "+
			"                    FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+ 
			"                    WHERE "+
			"                    APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			"                    INVOICE_NO=A.INVOICE_NO )) AND "+
			"     B.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"')   "+
			"    )P)L   "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Purchase Order Number
	
		Used in  : Purchase Order(Nuwan De Silva)
	-----------------------------------------------------------------*/						
		
		m_help_TXT_PURCHASE_ORDER_NO=
			
			/* " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE "+
				" FROM  "+
				" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE "+
				" FROM( "+
				" SELECT "+
			" PURCHASE_ORDER_NO, "+
			" APPLICATION_NO, "+
			" VENDER_CODE "+
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
				" WHERE  PURCHASE_ORDER_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS =('"+m_vector.elementAt(1)+"') "+ //AND VENDER_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
				" ORDER BY ENT_DATE DESC"+
			"  )P)L  "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			*/	
			
			
			
			
			m_help_TXT_PURCHASE_ORDER_NO=
			" SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE,L.VENDER_NAME,L.BRANCH_CODE,L.LOCATION_CODE,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.PRINTED_DATE,L.TRANSACTION_TYPE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE,P.VENDER_NAME,P.BRANCH_CODE,P.LOCATION_CODE,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.PRINTED_DATE,P.TRANSACTION_TYPE "+
			" FROM( "+
			" SELECT "+
			" DISTINCT A.PURCHASE_ORDER_NO PURCHASE_ORDER_NO, "+
			" A.APPLICATION_NO APPLICATION_NO, "+
			" A.VENDER_CODE VENDER_CODE, "+
			" "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME, "+
			" A.BRANCH_CODE BRANCH_CODE, "+
			" C.LOCATION_CODE, "+
			" X.CLIENT_CODE CLIENT_CODE, "+
			" ' ' FULL_NAME, "+
			" ' ' TEL_NO, "+
			" ' ' NIC_NO, "+
			" NVL((TO_CHAR(A.PRINTED_DATE,'DD-MM-YYYY')),'-') PRINTED_DATE, "+
			" X.TRANSACTION_TYPE "+
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+
			/*
			
			" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,X.TRANSACTION_TYPE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V "+
			" WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			" AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" AND (UPPER(V.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(V.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" V.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
			" V.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') ) "+
			" ) B, "+*/
			" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION C "+
			" WHERE "+
			" ( "+
			" UPPER(X.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(X.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.PURCHASE_ORDER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // remove the comment nuwan de silva on 11-02-2008
			" AND A.APPLICATION_NO =X.APPLICATION_NO "+
			" AND UPPER(C.BRANCH)=UPPER(A.BRANCH_CODE) "+
			" ORDER BY PURCHASE_ORDER_NO DESC "+
			" )P)L "+	
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*	
	m_help_TXT_PURCHASE_ORDER_NO=
	" SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE,L.VENDER_NAME,L.BRANCH_CODE,L.LOCATION_CODE,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.PRINTED_DATE,L.TRANSACTION_TYPE "+
	" FROM  "+
	" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE,P.VENDER_NAME,P.BRANCH_CODE,P.LOCATION_CODE,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.PRINTED_DATE,P.TRANSACTION_TYPE "+
	" FROM( "+
	" SELECT  "+
	" A.PURCHASE_ORDER_NO PURCHASE_ORDER_NO , "+ 
	" A.APPLICATION_NO APPLICATION_NO, "+ 
	" A.VENDER_CODE VENDER_CODE, "+
	" "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME,"+
	" A.BRANCH_CODE BRANCH_CODE, "+
	" (SELECT "+
	" DISTINCT LOCATION_CODE "+
	" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
	" WHERE UPPER(BRANCH)=UPPER(A.BRANCH_CODE)) LOCATION_CODE, "+
	" B.CLIENT_CODE CLIENT_CODE, "+
	" B.FULL_NAME FULL_NAME, "+
	" B.TEL_NO TEL_NO, "+
	" B.NIC_NO NIC_NO, "+
	" NVL((TO_CHAR(A.PRINTED_DATE,'DD-MM-YYYY')),'-') PRINTED_DATE,  "+
	" B.TRANSACTION_TYPE "+
	" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
	" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,X.TRANSACTION_TYPE "+
	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V "+
	" WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
	" AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
	" ) B "+
	" WHERE A.APPLICATION_NO =B.APPLICATION_NO "+
	" AND  "+
	" (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
	" UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
	" B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
	" B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
	" A.PURCHASE_ORDER_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
	" ) "+
	" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
	" ORDER BY A.PURCHASE_ORDER_NO DESC "+
	"  )P)L  "+
	" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
	*/
		
		/*-------------------------------------------------------------------------------------------------------
				: Nuwan De Silva
		Date    :07/12/2006
		Used In :Purchase Order
		/*-------------------------------------------------------------------------------------------------------*/
		//modified by madhawa 2009-10-19 commented "m_help_TXT_APPLICATION_NO_PUR_ORD" and aded below sql
		//m_help_TXT_APPLICATION_NO_PUR_ORD=
		//" SELECT L.NO ,L.APPLICATION_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.ACT_DAY,L.ACT_MON,L.ACT_YEAR,L.NEXT_DAY,L.NEXT_MON,L.NEXT_YEAR  "+
		//"  FROM   "+
		//"  (SELECT ROWNUM NO,P.APPLICATION_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO,  "+
		//"  P.ACT_DAY,P.ACT_MON,P.ACT_YEAR, "+
		//"  P.NEXT_DAY,P.NEXT_MON,P.NEXT_YEAR "+
		//"  FROM( "+
		
		//"  SELECT   "+
		//"  A.APPLICATION_NO APPLICATION_NO,   "+
		//"  B.CLIENT_CODE CLIENT_CODE,  "+
		//"  B.FULL_NAME FULL_NAME, "+ 
		//"  B.TEL_NO TEL_NO,  "+
		//"  B.NIC_NO NIC_NO,  "+
		//"  NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-') ACT_DAY, NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-') ACT_MON,NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-') ACT_YEAR, "+
		//"  NVL(SUBSTR("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),0,2),' ') NEXT_DAY, "+
		//"  NVL(SUBSTR("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),4,2),' ') NEXT_MON, "+
		//"  NVL(SUBSTR("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),7,4),' ') NEXT_YEAR "+
		//"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
		//"  (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
		//"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
		//"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
		//"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
		//"  ) B  "+
		//"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
		//"  AND   "+
		//"  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		//"  UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		//"  B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		//"  B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		//"  A.APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
		//"  )  "+
		//"  AND A.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')  "+
		//"  ORDER BY A.APPLICATION_NO DESC  "+
		//"   )P)L  "+
		//"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		//end of modified by madhawa 2009-10-19 commented "m_help_TXT_APPLICATION_NO_PUR_ORD"
		
		//modified by madhawa added following sql 2009-10-19 m_help_TXT_APPLICATION_NO_PUR_ORD
		m_help_TXT_APPLICATION_NO_PUR_ORD=
			" SELECT L.NO ,L.APPLICATION_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.ACT_DAY,L.ACT_MON,L.ACT_YEAR,L.NEXT_DAY,L.NEXT_MON,L.NEXT_YEAR  "+
			"  FROM   "+
			"  (SELECT ROWNUM NO,P.APPLICATION_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO,  "+
			"  P.ACT_DAY,P.ACT_MON,P.ACT_YEAR, "+
			"  P.NEXT_DAY,P.NEXT_MON,P.NEXT_YEAR "+
			"  FROM( "+
			
			"  SELECT   "+
			"  A.APPLICATION_NO APPLICATION_NO,   "+
			"  B.CLIENT_CODE CLIENT_CODE,  "+
			"  B.FULL_NAME FULL_NAME, "+ 
			"  B.TEL_NO TEL_NO,  "+
			"  B.NIC_NO NIC_NO,  "+
			"  NVL(TO_CHAR(ACTIVATED_DATE,'DD'),'-') ACT_DAY, NVL(TO_CHAR(ACTIVATED_DATE,'MM'),'-') ACT_MON,NVL(TO_CHAR(ACTIVATED_DATE,'YYYY'),'-') ACT_YEAR, "+
			"  NVL(SUBSTR(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'),1),'DD-MM-YYYY'),0,2),' ') NEXT_DAY, "+
			"  NVL(SUBSTR(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'),1),'DD-MM-YYYY'),4,2),' ') NEXT_MON, "+
			"  NVL(SUBSTR(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY'),1),'DD-MM-YYYY'),7,4),' ') NEXT_YEAR "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
			"  (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"  ) B  "+
			"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		
		//end modified by madhawa added above sql 2009-10-19 m_help_TXT_APPLICATION_NO_PUR_ORD
		
		
		
		/*-------------------------------------------------------------------------------------------------------
				: Nuwan De Silva
		Date    :24/07/2007
		Used In :Change Activated Date
		/*-------------------------------------------------------------------------------------------------------*/
		m_help_Finance_no_Change_activated_date=
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
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(3)+"')  "+
			
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS !=('"+m_vector.elementAt(2)+"')  "+
			"  AND UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		/*-------------------------------------------------------------------------------------------------------
				: Nuwan De Silva
		Date    :24/07/2007
		Used In :Change Activated Date
		/*-------------------------------------------------------------------------------------------------------*/
		m_help_Finance_no_Change_rental_amount=
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
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			
			"  ) B  "+
			"  WHERE A.FINANCE_NO =B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			//"  AND A.APPLICATION_STATUS !=('"+m_vector.elementAt(2)+"')  "+
			//"  AND UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(1)+"%') "+
			//"  AND A.APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(1)+"%')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		
		
		/*-------------------------------------------------------------------------------------------------------
				: Nuwan De Silva
		Date    :16/08/2007
		Used In :Change Activated Date
		/*-------------------------------------------------------------------------------------------------------*/
		m_help_Finance_no_Change_activated_date_app_no=
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
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(4)+"')  "+
			
			"  ) B  "+
			"  WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS !=('"+m_vector.elementAt(3)+"')  "+
			"  AND UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			//"  AND UPPER(A.FINANCE_NO) LIKE UPPER('"+m_vector.elementAt(2)+"%')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		
		/*-------------------------------------------------------------------------------------------------------
				: Nuwan De Silva
		Date    :16/08/2007
		Used In :Change Activated Date
		/*-------------------------------------------------------------------------------------------------------*/
		m_help_Finance_no_Change_rental_amount_app_no=
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
			"  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			
			"  ) B  "+
			"  WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			//"  AND A.APPLICATION_STATUS !=('"+m_vector.elementAt(3)+"')  "+
			//"  AND UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(1)+"%') "+
			//"  AND UPPER(A.FINANCE_NO) LIKE UPPER('"+m_vector.elementAt(2)+"%')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		
		
		PriceSql_invoice="SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,  "+
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE,  SUPPLIER, "+
			"     BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET  "+
			" FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,  "+
			"  	TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE, SUPPLIER, "+
			"     BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET "+
			" FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE,  SUPPLIER, "+
			"    BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET "+
			" FROM "+m_schema_name+".AF_MK_PRO_PRICING A "+
			" WHERE PRICING_NO LIKE '%"+m_vector.elementAt(0)+"%' /*AND PRICING_STATUS=('"+m_vector.elementAt(2)+"')*/ "+
			" AND APP_NO ='"+m_vector.elementAt(1)+"' "+
			" ORDER BY ENT_DATE DESC)) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
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
			" WHERE ( CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(CITY_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_APPLICATION_NO_2=
			
			" SELECT L.NO ,L.APPLICATION_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT APPLICATION_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
			" WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Suppliers
	
		Used in  : purchase order
	-----------------------------------------------------------------*/					
		
		
		m_help_SUPPLIER_PURCHASE_ORDER=
			
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE,NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS A "+
			" WHERE A.VENDOR_CODE IN  "+
			" (SELECT "+
			" B.VENDOR_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
			" WHERE B.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') )  "+
			" AND A.VENDOR_CODE LIKE ('%"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_SUPPLIER_sql=
			
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE, "+
			" NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
			" WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/* m_help_TXT_INVOICE_sql=
		
			" SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.TOTAL_AMOUNT,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.TOTAL_AMOUNT,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT "+
			" FROM( "+ 
			" SELECT "+
		" INVOICE_NO, "+
		" ASSET_ID, "+
			" TOTAL_AMOUNT, "+	
		" ENGINE_NO, "+
		" CHASSIS_NO, "+
		" NET_PRICE, "+
		" VAT "+
		" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		" WHERE PRICING_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		
		
		
		
		
		
		/*----------------------------------------------------------------
	Purpose  : Select Screen Name 
	
	Used in  : MK Follow Up
	-----------------------------------------------------------------*/
		
		
		m_help_TXT_SCREEN_NAME_sql=		
			"SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME "+
			" FROM (SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME "+
			" FROM (SELECT SCREEN_NAME,DISPLAY_NAME "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			"  WHERE SCREEN_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  ORDER BY SCREEN_NAME )P) L	"+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";									
		
		
		/*" SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME "+
				  "FROM "+
												"(SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME "+
	                   "FROM "+
														"(SELECT SCREEN_NAME,DISPLAY_NAME "+
														"	 FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
														"	 WHERE (SCREEN_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														" ORDER BY SCREEN_NAME )P) L "+	
														"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";*/
		
		/*m_help_TXT_CLIENT_CODE2=
      		    	
	 	" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic "+
										"FROM "+
												"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO "+
												"FROM "+
														"(SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
														"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
														"	 WHERE (FULL_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        ADDRESS1 LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
														"        TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"        NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
														"	       BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
														"        ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND "+
														"        CLIENT_CODE NOT IN('"+m_vector.elementAt(1)+"')  "+
												    " ORDER BY FULL_NAME )P) L "+		
										"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		*/
		
		
		
		
		
		
		
		
		/*----------------------------------------------------------------
				Purpose  : select Marketing Officer
			
				Used in  : MK Inquiry
			-----------------------------------------------------------------*/			
		MKOfficerSql =" SELECT NO,USER_ID,NAME "+
			" FROM  ( SELECT ROWNUM NO,USER_ID,NAME "+
			" FROM  ( SELECT USER_ID,NAME "+
			" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE  DIVISION_CODE='"+m_vector.elementAt(1)+"' AND "+
			"	      (USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY NAME)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
				Purpose  : select Marketing Supervisor
			
				Used in  : MK Inquiry
			-----------------------------------------------------------------*/
		MKSuperSql = " SELECT NO,USER_ID,NAME "+
			" FROM  ( SELECT ROWNUM NO,USER_ID,NAME "+
			" FROM  ( SELECT USER_ID,NAME "+
			" FROM   "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE  DIVISION_CODE='"+m_vector.elementAt(1)+"' AND "+
			"	      (USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY NAME)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		/*----------------------------------------------------------------
				Purpose  : select Marketing Team		
			
				Used in  : MK Inquiry
			-----------------------------------------------------------------*/	
		/*MKTeamSql =  " SELECT NO,TEAM_ID, TEAM_DESC,USER_ID, NAME "+
					" FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC,USER_ID, NAME "+
					" FROM  ( SELECT A.TEAM_ID, A.USER_ID,D.NAME,TEAM_DESC "+
									" FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_TEAMS B, "+
									" "+m_schema_name+".CO_CO_MAS_USER D "+
									" WHERE A.TEAM_ID=B.TEAM_ID AND "+//"+
									" D.USER_ID  =A.USER_ID AND "+
									" A.ACTIVE_STATUS='Y' AND A.USER_ID='"+m_vector.elementAt(1)+"' AND "+
									" A.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
									" ORDER BY TEAM_DESC)) P "+		
									" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";*/
		MKTeamSql =  " SELECT NO,TEAM_ID, TEAM_DESC,TEAM_HEAD "+
			" FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC,TEAM_HEAD "+
			" FROM  ( SELECT B.TEAM_ID,TEAM_DESC,TEAM_HEAD "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAMS B "+
			" WHERE B.ACTIVE_STATUS='Y' AND "+
			" (B.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  TEAM_DESC	LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY TEAM_DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";							
		
		/*----------------------------------------------------------------
				Purpose  : select Transaction Sub Code
			
				Used in  : MK Inquiry
			-----------------------------------------------------------------*/	
		TrnSubSql =  " SELECT NO,TRN_SUB_TYPE,DESCRIPTION,RATE "+
			" FROM  ( SELECT ROWNUM NO, TRN_SUB_TYPE, DESCRIPTION,RATE "+
			" FROM  ( SELECT A.TRN_SUB_TYPE,  A.DESCRIPTION,A.RATE "+
			" FROM   "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE A "+
			" WHERE  ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
			"        A.TRN_CODE=UPPER('"+m_vector.elementAt(1)+"') AND "+
			"        TRN_SUB_TYPE LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY TRN_SUB_TYPE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*---------------------------------------------------------------
		
		-----------------------------------------------------------------*/
		ModelSql =  " SELECT NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
			" FROM  ( SELECT ROWNUM NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE "+
			" FROM  ( SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE, "+
			"                A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE "+
			" FROM   "+m_schema_name+".AF_CO_MAS_MODEL A "+
			" WHERE  ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
			"        A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			"        A.MAKE_CODE LIKE  UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY MODEL_CODE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*---------------------------------------------------------------
		
		-----------------------------------------------------------------*/
		SubModelSql =  " SELECT NO,SUB_CODE SUB_MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MODEL_CODE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY "+
			" FROM  ( SELECT ROWNUM NO,SUB_CODE,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY "+
			" FROM  ( SELECT B.SUB_CODE ,B.MODEL_CODE, B.DESCRIPTION,A.TAX_RATE, "+
			"                A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE,b.ENGINE_CAPACITY "+
			" FROM   "+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
			" WHERE  B.ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' AND "+
			"        A.MODEL_CODE=B.MODEL_CODE AND  "+	
			"        (B.SUB_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"        UPPER(B.DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) AND  "+
			"        A.MAKE_CODE LIKE  UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY MODEL_CODE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		/*---------------------------------------------------------------
		
		-----------------------------------------------------------------*/
		
		IntBaseSql = " SELECT NO,BASE_CODE, DESCRIPTION, RATE FROM( "+
			" SELECT ROWNUM NO,BASE_CODE, DESCRIPTION, RATE FROM( "+
			" SELECT A.BASE_CODE, A.DESCRIPTION, A.RATE "+
			" FROM   "+m_schema_name+".AF_CO_MAS_INTEREST_BASE A "+
			" WHERE  ACTIVE_STATUS='Y' AND "+
			"       (BASE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%')) )) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*---------------------------------------------------------------
		
		-----------------------------------------------------------------*/
		VendorSql =  " SELECT NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
			" FROM  ( SELECT ROWNUM NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
			" FROM  ( SELECT A.VENDOR_CODE, A.NAME, NVL(A.CATEGORY,'-') CATEGORY,NVL(A.TYPE,'-') TYPE "+
			" FROM  "+m_schema_name+".AF_CO_MAS_VENDORS A "+
			" WHERE ACTIVE_STATUS='"+m_vector.elementAt(1)+"' AND "+
			"       (VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       UPPER(NAME)  	 LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY VENDOR_CODE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*---------------------------------------------------------------
		
		-----------------------------------------------------------------*/
		
		PriceSql="SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,  "+
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE,  SUPPLIER, "+
			"     BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET  "+
			" FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,  "+
			"  	TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE, SUPPLIER, "+
			"     BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET "+
			" FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,  "+
			"    PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT, "+
			"    NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE, "+ 
			"    ITEM_CATEGORY, ITEM_SUB_CAT_CODE, "+
			"    MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE, "+
			"    VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE, "+
			"    MAINTENANCE_STATUS,  "+
			"    OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE, "+
			"    CURRENCY_CODE,  SUPPLIER, "+
			"    BUY_BACK, "+
			"    INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN, "+
			"    CONDITION_OF_ASSET "+
			" FROM "+m_schema_name+".AF_MK_PRO_PRICING A "+
			" WHERE PRICING_NO LIKE '%"+m_vector.elementAt(0)+"%' "+
			" ORDER BY ENT_DATE DESC)) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		//Indicative Quation
		//------------------
		//**modified(2006-10-02)
		
		m_help_TXT_PRICING_NO_sql=
			
			" SELECT L.NO ,L.PRICING_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.MAKE_CODE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.ITEM_CATEGORY,L.ITEM_SUB_CAT_CODE,L.TRANSACION_TYPE,L.TRN_SUB_TYPE,L.INTEREST_TYPE,L.VARIABLE_INT_BASE,L.INT_MARGIN,L.CONDITION_OF_ASSET,"+
			" L.ASSET_USAGE_TYPE,L.VAT_PERCENTAGE,L.ENGINE_CAPACITY,"+
			" L.FUEL_TYPE,L.TARE,L.MAINTENANCE_STATUS,L.BUY_BACK,PERIOD,L.PAYMENT_MODE,L.PAYMENT_INTERVAL,L.RATE,LTRIM(TO_CHAR(L.GROSS_AMOUNT,'999,999,999.99')) AS GROSS_AMOUNT,LTRIM(TO_CHAR(L.VAT_AMOUNT,'999,999,999.99')) AS VAT_AMOUNT,"+
			" LTRIM(TO_CHAR(L.NET_AMOUNT,'999,999,999,999,999,999,999,999.99')) AS NET_AMOUNT,L.NIBSM,LTRIM(TO_CHAR(L.AMI,'999,999,999.99')),LTRIM(TO_CHAR(L.LAST_RENTAL,'999,999,999.99')) AS LAST_RENTAL,LTRIM(TO_CHAR(L.RESIDUAL_VALUE,'999,999,999.99')) AS RESIDUAL_VALUE,L.OUTFLOW_PATTERN,L.SUPPLIER_CREDIT,L.INFLOW_PATTERN,L.PRICING_STATUS,"+
			" L.CURRENCY_CODE,L.SUPPLIER,LTRIM(TO_CHAR(L.TRAN_AMOUNT_CURRENCY,'999,999,999.99')) AS TRAN_AMOUNT_CURRENCY ,L.DESC5 AS CONDITION"+
			//L.DESC1 AS MAKE,L.DESC2 AS MODEL,L.DESC3 AS ITEM ,L.DESC4 AS SUBCAT,L.DESC5 AS CONDITION "+
			
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRICING_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.MAKE_CODE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.ITEM_CATEGORY,P.ITEM_SUB_CAT_CODE,P.TRANSACION_TYPE,P.TRN_SUB_TYPE,P.INTEREST_TYPE,P.VARIABLE_INT_BASE,P.INT_MARGIN,P.CONDITION_OF_ASSET,"+
			" P.ASSET_USAGE_TYPE,P.VAT_PERCENTAGE,P.ENGINE_CAPACITY,"+
			" P.FUEL_TYPE,P.TARE,P.MAINTENANCE_STATUS,P.BUY_BACK,PERIOD,P.PAYMENT_MODE,P.PAYMENT_INTERVAL,P.RATE,P.GROSS_AMOUNT,P.VAT_AMOUNT,"+
			" P.NET_AMOUNT,P.NIBSM,P.AMI,P.LAST_RENTAL,P.RESIDUAL_VALUE,P.OUTFLOW_PATTERN,P.SUPPLIER_CREDIT,P.INFLOW_PATTERN,P.PRICING_STATUS,"+
			" P.CURRENCY_CODE,P.SUPPLIER,P.TRAN_AMOUNT_CURRENCY,P.DESC5"+
			//,P.DESC1,P.DESC2,P.DESC3,P.DESC4,P.DESC5 "+
			
			" FROM( "+ 
			" SELECT "+
			"	PRICING_NO, "+
			"	INQUIRY_NO, "+
			" CLIENT_NAME, "+
			"	A.MAKE_CODE, "+
			//" B.MAKE_DESC AS DESC1,"+
			"	A.MODEL_CODE, "+
			//" C.DESCRIPTION AS DESC2,"+
			"	SUB_MODEL_CODE, "+
			"	ITEM_CATEGORY, "+
			
			//" D.DESCRIPTION AS DESC3, "+
			"	ITEM_SUB_CAT_CODE, "+
			//" E.DESCRIPTION AS DESC4,"+
			"	TRANSACION_TYPE, "+
			"	TRN_SUB_TYPE, "+
			"	INTEREST_TYPE, "+
			"	VARIABLE_INT_BASE, "+
			"	INT_MARGIN, "+
			"	CONDITION_OF_ASSET, "+
			"	F.DESCRIPTION AS DESC5,"+
			"	ASSET_USAGE_TYPE, "+
			"	VAT_PERCENTAGE, "+
			"	ENGINE_CAPACITY, "+
			"	A.FUEL_TYPE, "+
			"	TARE, "+
			"	MAINTENANCE_STATUS, "+
			"	BUY_BACK, "+
			"	PERIOD, "+
			"	PAYMENT_MODE, "+
			"	PAYMENT_INTERVAL, "+
			"	RATE, "+
			"	GROSS_AMOUNT, "+
			"	VAT_AMOUNT, "+
			"	NET_AMOUNT, "+
			"	NIBSM, "+
			"	AMI, "+
			"	LAST_RENTAL, "+
			"	RESIDUAL_VALUE, "+
			"	OUTFLOW_PATTERN, "+
			"	SUPPLIER_CREDIT, "+
			"	INFLOW_PATTERN, "+
			"	PRICING_STATUS, "+
			" CURRENCY_CODE, "+
			"	SUPPLIER, "+
			"	TRAN_AMOUNT_CURRENCY  "+
			//" FROM LAKDL.AF_MK_PRO_PRICING "+
			" FROM "+m_schema_name+".AF_MK_PRO_PRICING A"+
			//,LAKDL.AF_CO_MAS_MAKE B,LAKDL.AF_CO_MAS_MODEL C,LAKDL.AF_CO_MAS_ITEM_CATEGORY D,LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY E"+
			"	,"+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET F "+
			" ,"+m_schema_name+".AF_MK_PRO_INQUIRY G "+
			
			" WHERE PRICING_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			//" AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" AND L.SUB_MODEL_CODE,
			// " AND E.ITEM_SUB_CAT=A.ITEM_SUB_CAT_CODE
			//" AND B.MAKE_CODE=A.MAKE_CODE
			// "AND C.MODEL_CODE=A.MODEL_CODE
			" AND A.CONDITION_OF_ASSET=F.CODE "+
			" AND A.INQUIRY_NO=('"+m_vector.elementAt(0)+"') "+
			" AND A.INQUIRY_NO=G.INQUIRY_CODE	"+
			//" AND D.ITEM_CAT_CODE=A.ITEM_CATEGORY"+
			//" AND E.ITEM_CAT_CODE=D.ITEM_CAT_CODE"+
			" ORDER BY PRICING_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//--------------------------------------------------------------------------------------------------
		m_help_TXT_QUOTATION_NO_sql=
			
			" SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE "+
			
			
			" FROM  "+
			" (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.STATUS,P.APPR_USER,P.APPR_DATE"+
			
			" FROM( "+ 
			" SELECT "+
			" QUOTATION_NO,"+
			"	INQUIRY_NO,"+
			"	STATUS,"+
			" APPR_USER ,"+
			" APPR_DATE "+
			" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION "+
			" WHERE QUOTATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			//AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_QUOTATION_NO_sql1=
			
			" SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE "+
			
			" FROM  "+
			" (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.STATUS,P.APPR_USER,P.APPR_DATE"+
			
			" FROM( "+ 
			" SELECT "+
			" QUOTATION_NO,"+
			"	INQUIRY_NO,"+
			" CLIENT_NAME, "+
			"	A.STATUS,"+
			" APPR_USER ,"+
			" APPR_DATE "+
			" FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A ,"+m_schema_name+".AF_MK_PRO_INQUIRY B"+
			" WHERE (QUOTATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR CLIENT_NAME LIKE('%"+m_vector.elementAt(0)+"%'))  "+
			" AND INQUIRY_CODE=INQUIRY_NO "+
			" AND A.STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY QUOTATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//--------------------------------------------------------------------------------------------------------	
		//*modifed(2006-10-02)
		m_help_TXT_CONDITION_OF_ASSET_sql=
			
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			
			
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			
			" FROM( "+ 
			" SELECT "+
			"	CODE,"+
			"	DESCRIPTION,"+
			"	ACTIVE_STATUS,"+
			"	DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
			" WHERE UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//-------------------------------------------------------------------------------------------------------------
		m_help_TXT_MAKE_CODE_sql=
			" SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,L.ITEM_SUB_CAT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.ITEM_SUB_CAT,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MAKE_CODE,"+
			" MAKE_DESC, "+
			" ITEM_SUB_CAT, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
			" WHERE UPPER(MAKE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND UPPER(MAKE_DESC) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//--------------------------------------------------------------------------------------------------------------
		
		m_help_TXT_MODEL_CODE1_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MODEL_CODE,"+
			" DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			" WHERE UPPER(MODEL_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//AND DESCRIPTION LIKE UPPER('"+m_vector.elementAt(1)+"%')
		
		m_help_TXT_MODEL_CODE2_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MODEL_CODE,"+
			" DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			" WHERE UPPER(MAKE_CODE)=UPPER('"+m_vector.elementAt(1)+"') AND (UPPER(MODEL_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(2)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//AND DESCRIPTION LIKE UPPER('"+m_vector.elementAt(1)+"%')
		
		//---------------------------------------------------------------------------------------------------------------
		
		
		m_help_TXT_INQUIRY_NO_sql=
			" SELECT L.NO,L.INQUIRY_CODE AS INQUIRY_NO,L.CLIENT_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE "+
			" FROM( "+ 
			" SELECT "+
			" INQUIRY_CODE,"+
			"	CLIENT_NAME,"+
			"	ADDRESS,"+
			"	ADDRESS2,"+
			"	CITY_CODE "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			" WHERE (UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR UPPER(CLIENT_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR UPPER(MK_OFFICER) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR UPPER(ID_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR UPPER(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY INQUIRY_CODE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
			Purpose  : Select Purchase Order Number
		
			Used in  : Payment Details Process
		-----------------------------------------------------------------*/						
		
		
		m_help_TXT_PURCHASE_ORDER_NO_sql=
			" SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.FINANCE_NO,TO_CHAR(NVL(L.TOTAL_NET,0),'999,999,999.99'),TO_CHAR(NVL(L.TOTAL_VAT,0),'999,999,999.99'),L.VENDOR_CODE,L.NAME,L.CLIENT_CODE,L.FULL_NAME,TO_CHAR(NVL(L.TOTAL,0),'999,999,999.99'),L.USER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.FINANCE_NO,P.TOTAL_NET,P.TOTAL_VAT,P.VENDOR_CODE,P.NAME,P.CLIENT_CODE,P.FULL_NAME,P.TOTAL,P.USER "+
			" FROM( "+
			" SELECT "+
			" PURCHASE_ORDER_NO, "+
			" A.APPLICATION_NO, "+
			" FINANCE_NO ,"+
			" TOTAL_NET, "+
			" TOTAL_VAT, "+
			" VENDOR_CODE, "+
			" NAME, "+
			" D.CLIENT_CODE, "+
			" FULL_NAME, "+
			" (TOTAL_NET+TOTAL_VAT)AS TOTAL ,A.MOD_USER USER"+
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,"+m_schema_name+".AF_CO_MAS_CLIENT D "+
			" WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND A.APPLICATION_NO=C.APPLICATION_NO  "+
			"	AND B.VENDOR_CODE=A.VENDER_CODE "+
			"	AND D.CLIENT_CODE=C.CLIENT_CODE "+		
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*
			" SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE "+
				" FROM  "+
				" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE "+
				" FROM( "+
				" SELECT "+
			" PURCHASE_ORDER_NO, "+
			" APPLICATION_NO, "+
			" VENDER_CODE "+
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER "+
				" WHERE  PURCHASE_ORDER_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') AND VENDER_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')   "+
			"  )P)L  "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		
		m_help_TXT_APP_NO_sql=  	
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.DISTRICT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FACILITY_NO, "+
			" CLIENT_CODE, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+
			" DISTRICT_CODE, "+
			" INQUARY_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_APP_NO_sql_1=  	
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FACILITY_NO, "+
			" CLIENT_CODE, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+
			// " DISTRICT_CODE, "+
			" INQUARY_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		m_help_TXT_VENDER_CODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE,"+
			" NAME, "+
			" CATEGORY, "+
			" TYPE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
			" WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY VENDOR_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_ASSET_ID_sql2=
			
			" SELECT L.NO,L.ASSET_ID,/* L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, */L.MODEL_CODE,L.SUB_MODEL_CODE,/* L.PRICING_NO, */L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,/*L.ADDRESS,L.CITY_CODE,*/L.PERIOD,L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,/* P.REG_NO,P.REG_DATE, */P.MODEL_CODE,P.SUB_MODEL_CODE,/* P.PRICING_NO, */P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,/*P.ADDRESS,P.CITY_CODE,*/P.PERIOD,P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE"+
			" FROM( "+ 
			" SELECT "+
			" ASSET_ID, "+
			//" REG_NO, "+
			//" REG_DATE, "+
			" MODEL_CODE, "+
			" SUB_MODEL_CODE, "+
			//" PRICING_NO, "+
			" STATUS, "+
			" SUPPLIER_CODE, "+
			" QTY, "+
			" COST, "+
			" PURPOSE, "+
			//" ADDRESS, "+
			//" CITY_CODE, "+
			" PERIOD, "+
			" APPLICATION_NO, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE"+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
			" WHERE ASSET_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND APPLICATION_NO = '"+m_vector.elementAt(1)+"'"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//Added by Dineth on 28-07-2009
		m_help_TXT_ASSET_ID_sql3=
			
			" SELECT L.NO,L.ASSET_ID,/* L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, */L.MODEL_CODE,L.SUB_MODEL_CODE,/* L.PRICING_NO, */L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,/*L.ADDRESS,L.CITY_CODE,*/L.PERIOD,L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.PRO_INVOICE_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,/* P.REG_NO,P.REG_DATE, */P.MODEL_CODE,P.SUB_MODEL_CODE,/* P.PRICING_NO, */P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,/*P.ADDRESS,P.CITY_CODE,*/P.PERIOD,P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.PRO_INVOICE_NO"+
			" FROM( "+ 
			" SELECT "+
			" C.ASSET_ID, "+
			//" REG_NO, "+
			//" REG_DATE, "+
			" C.MODEL_CODE, "+
			" C.SUB_MODEL_CODE, "+
			//" PRICING_NO, "+
			" C.STATUS, "+
			" C.SUPPLIER_CODE, "+
			" C.QTY, "+
			" C.COST, "+
			" C.PURPOSE, "+
			//" ADDRESS, "+
			//" CITY_CODE, "+
			" C.PERIOD, "+
			" C.APPLICATION_NO, "+
			" B.PRO_INVOICE_NO, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(C.MODEL_CODE) FUEL_TYPE,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(C.MODEL_CODE) ITEM_CAT_CODE"+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B "+
			" WHERE "+
			" A.FINANCE_NO = B.FINANCE_NO "+
			" AND A.APPLICATION_STATUS = 'ACTIVATED' "+ 
			" AND B.END_DATE >= TO_DATE('"+m_vector.elementAt(2)+"','DD-MM-YYYY') "+
			" AND B.END_DATE <= TO_DATE('"+m_vector.elementAt(1)+"','DD-MM-YYYY') "+
			" AND C.ASSET_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND C.APPLICATION_NO = '"+m_vector.elementAt(3)+"'"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		//End by Dineth on 28-07-2009
		
		m_help_TXT_DISTRICT_CODE_sql1=
			
			" SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" DISTRICT_CODE,"+
			" DISTRICT_DESC, "+
			" PROVINCE_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
			" WHERE (UPPER(DISTRICT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DISTRICT_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_BRANCH_CODE_PURCHASE_ORDER=
			
			/*	" SELECT L.NO ,L.BRANCH "+
				" FROM  "+
				" (SELECT ROWNUM NO,P.BRANCH "+
				" FROM( "+ 
				" SELECT "+
			" DISTINCT BRANCH "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
			" WHERE UPPER(VENDOR_CODE)=UPPER('"+m_vector.elementAt(0)+"') AND BRANCH LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
				"  )P)L  "+
				" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
				*/
			
			
			" SELECT L.NO ,L.BRANCH,L.ADDRESS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH,P.ADDRESS "+
			" FROM( "+ 
			" SELECT DISTINCT BRANCH,ADDRESS "+ // LOCATION CHANGED TO ADDRESS BY ASHINI
			" FROM  "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
			" WHERE (BRANCH,VENDOR_CODE) IN( "+
			" SELECT "+
			" DISTINCT  BRANCH_ID,VENDOR_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			" WHERE UPPER(VENDOR_CODE)=UPPER('"+m_vector.elementAt(0)+"') AND APPLICATION_NO=UPPER('"+m_vector.elementAt(1)+"')) "+
			" AND UPPER(BRANCH) LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(3)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_VENDOR_CODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME VENDOR_NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE,"+
			" NAME, "+
			" CATEGORY, "+
			" TYPE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
			" WHERE (UPPER(VENDOR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY VENDOR_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
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
			" WHERE (LOCATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_MAS_VENDOR_LOCATION_sql=
			
			" SELECT L.NO,L.BRANCH,L.LOCATION_CODE,L.VENDOR_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS,L.CITY_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH,P.LOCATION_CODE,P.VENDOR_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS,P.CITY_CODE "+
			" FROM( "+ 
			" SELECT "+
			" BRANCH, "+
			" LOCATION_CODE, "+
			" VENDOR_CODE,  "+
			" TITLE, "+
			" FIRST_NAME, "+
			" LAST_NAME, "+
			" ID_NO, "+
			" ADDRESS, "+
			" CITY_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
			" WHERE (BRANCH LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(VENDOR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_BRANCH_CODE_sql=
			" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.ADDRESS1,NVL(L.ADDRESS2,'N/A'),NVL(L.CITY_CODE,'N/A'),NVL(L.TEL_NO,'N/A'),NVL(L.FAX_NO,'N/A'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE"+
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
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_ACCOUNT_sql=	
			"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			"FROM(  "+
			" SELECT "+
			" ACC_NO, "+
			" BRANCH_CODE, "+	
			" ACC_SYS_REFNO, "+
			" CURR_CODE, "+
			" ACC_CODE, "+
			" ACC_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ACCOUNT_2_sql=	
			"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.ACC_SYS_REFNO,L.CURR_CODE, L.ACC_CODE,L.ACC_DESC "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			"FROM(  "+
			" SELECT "+
			" ACC_NO, "+
			" BRANCH_CODE, "+	
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
			" ACC_SYS_REFNO, "+
			" CURR_CODE, "+
			" ACC_CODE, "+
			" ACC_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE (UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) LIKE('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ACCOUNT_1_sql=	
			"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			"FROM(  "+
			" SELECT "+
			" ACC_NO, "+
			" BRANCH_CODE, "+	
			" ACC_SYS_REFNO, "+
			" CURR_CODE, "+
			" ACC_CODE, "+
			" ACC_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" AND ACC_NO IN (SELECT DISTINCT ACC_NO FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D "+
			" WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			" AND A.APPLICATION_NO=C.APPLICATION_NO "+
			" AND C.CLIENT_CODE=D.CLIENT_CODE "+
			" AND UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')) "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ACCOUNT_3_sql=	
			"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			"FROM(  "+
			" SELECT "+
			" ACC_NO, "+
			" BRANCH_CODE, "+	
			" ACC_SYS_REFNO, "+
			" CURR_CODE, "+
			" ACC_CODE, "+
			" ACC_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" AND ACC_NO IN (SELECT DISTINCT ACC_NO FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A,"+m_schema_name+".AF_CO_MAS_VENDORS B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D,"+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT E "+
			" WHERE A.VENDER_CODE=B.VENDOR_CODE "+
			" AND A.APPLICATION_NO=C.APPLICATION_NO "+
			" and a.purchase_order_no=e.purchase_order_no "+
			" AND C.CLIENT_CODE=D.CLIENT_CODE "+
			" AND UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND e.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')) "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//================================================================================================================
		
		/*----------------------------------------------------------------
			Purpose  : Select Application Number
		
			Used in  : Credit Verification Process.
		-----------------------------------------------------------------*/												
		
		m_help_TXT_APPL_NO=
			
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FACILITY_NO, "+
			" CLIENT_CODE, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, "+
			//  " DISTRICT_CODE, "+
			" INQUARY_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') "+
			" OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";		
		
		/*------------------------------------------------------------------
			Purpose  : Select Finance Number
		
			Used in  : Security and Marketting Process.
			-------------------------------------------------------------------*/												
		
		FinanceSql =      " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ /*THIS PART ADDED BY ASHINI ON 19-09-2007 */
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ /*THIS PART ADDED BY ASHINI ON 19-09-2007 */
			" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')"+
			" AND RENTAL_FREEZ <> 'Y'   "+ // added by udara 17-02-2017 to block rental freezed contracts
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		/*		  FinanceSql_return =	" SELECT L.NO ,L.FINANCE_NO,L.APPLICATION_NO,L.REQ_NO "+
														" FROM  "+
														" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.REQ_NO "+
														" FROM( "+ 
														" SELECT DISTINCT "+
														" A.FINANCE_NO,A.APPLICATION_NO,REQ_NO "+
														" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B "+
														" WHERE   A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
														//	" AND APPLICATION_STATUS='REQ_APP' "+
														" AND DOCUMENT_STATUS=('"+m_vector.elementAt(1)+"') "+
														" AND A.FINANCE_NO=B.FINANCE_NO "+
														" AND A.PRO_FORMA_INVOICE_NO=B.PRO_FORMA_INVOICE_NO "+
														" ORDER BY FINANCE_NO DESC)P)L  "+
														" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
												
			*/	
		
		
		// added by udara on 21-11-2012
		
		DueDateChangeFinanceSql = " "+
			" SELECT INVOICE_NO,FINANCE_NO,VALUE_DATE,VALUE_DATE_DD,VALUE_DATE_MM,VALUE_DATE_YY,REMARKS,FINANCE_NO,BALANCE_TO_BE_RECEIVED "+
				 " FROM(  "+
				     " SELECT "+
				         " A.INVOICE_NO INVOICE_NO, "+
				         " A.DUE_DATE VALUE_DATE, "+
				         " TO_CHAR(A.DUE_DATE,'DD') VALUE_DATE_DD, "+
				         " TO_CHAR(A.DUE_DATE,'MM') VALUE_DATE_MM, "+
				         " TO_CHAR(A.DUE_DATE,'YYYY') VALUE_DATE_YY, "+
				         " A.DUE_DATE,A.TOTAL_AMOUNT TOTAL_AMOUNT, "+
				         " A.SETTELE_AMOUNT SETTELE_AMOUNT, "+
				         " A.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED, "+
				         " A.BALANCE_TO_BE_RECEIVED_CURR, "+
				         " A.FINANCE_NO FINANCE_NO, "+
				         " NVL(A.REMARKS,'-') REMARKS "+
				             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A ,"+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS B "+
				             " WHERE  A.INVOICE_NO = B.INVOICE_NO  "+
				             " AND (A.FINANCE_NO  LIKE '%"+m_vector.elementAt(0)+"%' OR A.INVOICE_NO  LIKE '%"+m_vector.elementAt(0)+"%') "+
				             " AND A.ACTIVE_STATUS = 'Y' "+ 
				             " AND A.INVOICE_TYPE IN ('INSURANCE') "+
				             " AND A.BALANCE_TO_BE_RECEIVED > 0 "+
				             //" AND A.DUE_DATE > (SELECT LAST_DAYEND_PROCESS FROM "+m_schema_name+".FA_OP_DAYEND_ROUTINE) "+ 
				             " AND A.ADJUSTED_DATE IS NOT NULL  "+
				             "  AND B.ACTIVE_STATUS <> 'A' "+
				                                                                 
				                                                                                                                                                                     
				     " UNION ALL "+
				                                                                                            
				     " SELECT "+
				         " A.INVOICE_NO INVOICE_NO, "+
				         " A.DUE_DATE VALUE_DATE, "+
				         " TO_CHAR(A.DUE_DATE,'DD') VALUE_DATE_DD, "+
				         " TO_CHAR(A.DUE_DATE,'MM') VALUE_DATE_MM, "+
				         " TO_CHAR(A.DUE_DATE,'YYYY') VALUE_DATE_YY, "+
				         " A.DUE_DATE,A.TOTAL_AMOUNT TOTAL_AMOUNT, "+
				         " A.SETTELE_AMOUNT SETTELE_AMOUNT, "+
				         " A.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED, "+
				         " A.BALANCE_TO_BE_RECEIVED_CURR, "+
				         " A.FINANCE_NO FINANCE_NO, "+
				         " NVL(A.REMARKS,'-') REMARKS "+
				             " FROM "+m_schema_name+".AF_CO_PRO_INVOICE A  "+
				             " WHERE (A.FINANCE_NO  LIKE '%"+m_vector.elementAt(0)+"%' OR A.INVOICE_NO  LIKE '%"+m_vector.elementAt(0)+"%') "+
				             " AND A.ACTIVE_STATUS = 'Y' "+
				             " AND A.INVOICE_TYPE IN ('INSURANCE') "+
				             " AND A.BALANCE_TO_BE_RECEIVED > 0 "+
				             //" AND A.DUE_DATE > (SELECT LAST_DAYEND_PROCESS FROM "+m_schema_name+".FA_OP_DAYEND_ROUTINE)  "+ 
				             " AND A.ADJUSTED_DATE IS NULL "+
				             " AND A.MOD_DATE IN (SELECT MAX(MOD_DATE) FROM "+m_schema_name+".AF_CO_PRO_INVOICE WHERE FINANCE_NO=A.FINANCE_NO AND ACTIVE_STATUS = 'Y' ) "+ 
				 " ) "+ 
				                                        
				 " ORDER BY VALUE_DATE DESC ";
		
		// end by udara on 21-11-2012
		
		
		FinanceSql_return =	" SELECT L.NO ,L.FINANCE_NO,L.APPLICATION_NO,L.REQ_NO,L.CLIENT_CODE,L.CLIENT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.REQ_NO,P.CLIENT_CODE,P.CLIENT_NAME "+
			" FROM( "+ 
			" SELECT DISTINCT "+
			" A.FINANCE_NO,A.APPLICATION_NO,REQ_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT A,"+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL B ,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
			" WHERE   (A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			
			" OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			
			" AND A.FINANCE_NO=C.FINANCE_NO "+
			//	" AND APPLICATION_STATUS='REQ_APP' "+
			" AND DOCUMENT_STATUS=('"+m_vector.elementAt(1)+"') "+
			" AND A.FINANCE_NO=B.FINANCE_NO "+
			//" AND A.PRO_FORMA_INVOICE_NO=B.PRO_FORMA_INVOICE_NO "+
			" ORDER BY FINANCE_NO DESC)P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		FinanceSql_doc = " SELECT L.NO ,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE,L.CLIENT_NAME "+
			"FROM "+  
			"(SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.CLIENT_NAME "+
			"FROM( "+
			"SELECT distinct B.FINANCE_NO,B.APPLICATION_NO,B.CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(B.CLIENT_CODE) CLIENT_NAME  "+ 
			"FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,  "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,  "+
			""+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			"WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+
			"AND DOCUMENT_TYPE=CODE  "+
			"AND PRO_INVOICE_NO =INVOICE_NO  "+
			"AND D.APPLICATION_NO=B.APPLICATION_NO  "+
			"AND (UPPER(B.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			"OR( UPPER(B.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			"OR UPPER(LAKDL.af_co_get_client_name(A.CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%'))) "+
			"AND B.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') "+
			"AND CODE IN  (SELECT DISTINCT CODE "+
			"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
			"WHERE FROM_SCREEN_NO <=  "+
			"(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_SECURITY_MARKETTING_FILE') "+ 
			"AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_SECURITY_MARKETTING_FILE'))  "+
			"ORDER BY B.FINANCE_NO DESC)P)L  "+
			"WHERE L.NO>=   "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//--------------------------------------------------------------------------------------------------------------
		/*------------------  ID       :3.21 Credit Score Sub Evaluation Process-----------------------------------------
		--------------------Purpose    :Application Help ----------------------------------------------
		------------------- Added By   :Delanjali------------------------------------------------------
		--------------------  Date     :12-11-2006---------------------------------------------------------*/
		
		
		m_help_cr_score_application_sql_1=
			" SELECT L.NO ,L.APPLICATION_NO,L.CLIENT_CODE,L.FULL_NAME,L.APPLICATION_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.CLIENT_CODE,P.FULL_NAME,P.APPLICATION_STATUS "+
			" FROM( "+ 
			" SELECT "+ 
			" A.APPLICATION_NO, "+ 
			" B.CLIENT_CODE, "+ 
			" B.FULL_NAME, "+ 
			" A.APPLICATION_STATUS "+ 
			
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+ 
			" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V "+
			" WHERE V.CLIENT_CODE=X.CLIENT_CODE )"+
			"  B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND  "+
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" AND A.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')  "+
			" ORDER BY A.APPLICATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_fin_staus_application_sql=
			" SELECT L.NO,L.APPLICATION_NO,L.FULL_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FULL_NAME "+
			" FROM "+
			" (SELECT A.APPLICATION_NO,B.FULL_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			" A.INQUARY_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY APPLICATION_NO DESC "+
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_fin_staus_application_sql2=
			" SELECT L.NO,L.APPLICATION_NO,L.FULL_NAME "+ 
			" FROM "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FULL_NAME "+ 
			" FROM "+
			" (SELECT A.APPLICATION_NO,C.FULL_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_MAS_CLIENT C "+
			" WHERE "+
			" A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" A.CLIENT_CODE=C.CLIENT_CODE AND "+
			" A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+ 
			" A.INQUARY_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND "+
			" A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(3)+"%') AND "+
			" UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(4)+"%') "+
			" ORDER BY A.APPLICATION_NO DESC "+
			" )P)L "+			
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*m_help_fin_staus_finance_sql=
		" SELECT  L.NO,L.FINANCE_NO,L.APPLICATION_NO,L.INQUARY_NO "+
		" FROM "+
		" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.INQUARY_NO "+
		" FROM "+
		" (SELECT FINANCE_NO,APPLICATION_NO,INQUARY_NO "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') AND "+
		" APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(1)+"%') AND "+
		" INQUARY_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+
		" ORDER BY INQUARY_NO DESC "+
		" )P)L "+		
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		m_help_fin_staus_finance_sql=
			" SELECT  L.NO,L.FINANCE_NO,L.APPLICATION_NO,L.INQUIRY_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.INQUIRY_NO "+ 
			" FROM "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.INQUARY_NO INQUIRY_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+ 
			" A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+ 
			" A.INQUARY_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND "+
			" A.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(3)+"%') AND "+
			" UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(4)+"%') "+
			" ORDER BY INQUARY_NO DESC "+
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		m_help_fin_staus_vihicle_sql=
			" SELECT L.NO,L.REG_NO,L.INVOICE_NO,L.APPLICATION_NO "+ 
			" FROM "+
			" (SELECT ROWNUM NO,P.REG_NO,P.INVOICE_NO,P.APPLICATION_NO "+
			" FROM "+ 
			" (SELECT A.REG_NO,A.INVOICE_NO,A.APPLICATION_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE UPPER(A.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+ 
			" A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			" A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" B.INQUARY_NO LIKE UPPER('%"+m_vector.elementAt(2)+"%') AND "+
			" B.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(3)+"%') AND "+
			" B.CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(4)+"%') "+ 
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		m_help_fin_staus_client_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.CLIENT_CODE,B.FULL_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" A.APPLICATION_NO=C.APPLICATION_NO AND "+
			" (A.CLIENT_CODE LIKE('%"+m_vector.elementAt(0)+"%') OR UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+ 
			" A.APPLICATION_NO LIKE('%"+m_vector.elementAt(1)+"%') AND "+ 
			" A.INQUARY_NO LIKE('%"+m_vector.elementAt(2)+"%') AND "+
			" A.FINANCE_NO LIKE('%"+m_vector.elementAt(3)+"%') AND "+
			" UPPER(C.REG_NO) LIKE UPPER('%"+m_vector.elementAt(4)+"%') "+
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		//Modified by Mahela on 07-05-2007
		m_help_fin_staus_inquary_sql=
			" SELECT "+
			" L.NO, "+
			" L.INQUIRY_CODE, "+
			" L.CLIENT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME "+ 
			" FROM  "+
			" (SELECT DISTINCT INQUIRY_CODE,CLIENT_NAME "+ 
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.INQUIRY_CODE=B.INQUARY_NO(+) AND "+ 
			" (UPPER(A.INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.CLIENT_NAME) LIKE UPPER('%" +m_vector.elementAt(0)+ "%')) AND UPPER(B.APPLICATION_NO) LIKE UPPER('%" +m_vector.elementAt(1)+ "%')  "+
			" ORDER BY A.INQUIRY_CODE DESC "+ 
			" )P)L "+ 
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		m_help_cr_score_application_sql=
			" SELECT L.NO ,L.APPLICATION_CODE,L.SCORE_MODEL_CODE,L.EVAL_USER,L.FINAL_APP_SCORE,L.MODEL_SCORE,L.COMMENTS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_CODE,P.SCORE_MODEL_CODE,P.EVAL_USER,P.FINAL_APP_SCORE,P.MODEL_SCORE,P.COMMENTS "+
			" FROM( "+ 
			" SELECT "+ 
			" APPLICATION_CODE, "+ 
			" SCORE_MODEL_CODE, "+ 
			" EVAL_USER, "+ 
			" NVL(FINAL_APP_SCORE,0) FINAL_APP_SCORE, "+ 
			" NVL(MODEL_SCORE,0) MODEL_SCORE, "+ 
			" NVL(COMMENTS,'-') COMMENTS "+ 
			" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE "+ 
			" WHERE APP_STATUS=('"+m_vector.elementAt(1)+"') AND APPLICATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			" ORDER BY APPLICATION_CODE DESC "+
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
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SCORE_MODEL_CODE_sql=
			" SELECT L.NO ,L.SCORE_MODEL_CODE,L.DESCRIPTION,L.TOTAL_SCORE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_MODEL_CODE,P.DESCRIPTION,P.TOTAL_SCORE "+
			" FROM( "+ 
			" SELECT SCORE_MODEL_CODE, "+
			" DESCRIPTION, "+
			" TOTAL_SCORE "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL "+
			" WHERE (SCORE_MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*-------------------------------------------------------
		Purpose 		: Get The Finance Number 
		Used In 		:Credit - Credit Notes Generation
		Created By	:Nuwan De Silva(8/1/07)
		---------------------------------------------------------*/
		
		/*m_help_CR_DR_NOTES_FINANCE_NO=
			
					" SELECT L.NO ,NVL(L.FINANCE_NO,'-') APPLICATION_NO,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO "+
				" FROM  "+
				" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO "+
				" FROM( "+ 
				" SELECT "+
				" 		DISTINCT A.FINANCE_NO, "+
				" 			A.CLIENT_CODE, "+
				" 			 B.FULL_NAME FULL_NAME,   "+
				" 			 B.TEL_NO TEL_NO,   "+
				" 			 B.NIC_NO NIC_NO   "+
				" 				 FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+  
				"         (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO   "+
				"          FROM "+m_schema_name+".AF_CO_PRO_INVOICE X,"+m_schema_name+".AF_CO_MAS_CLIENT V   "+
				"          WHERE V.CLIENT_CODE=X.CLIENT_CODE  "+
				"          )B   "+
				" 				 WHERE A.FINANCE_NO=B.FINANCE_NO  "+ 
				"          AND    "+
			"  			(UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		"  			UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		"  			B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		"  			B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
		"  			A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			"        )   "+
				" 			 AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
				"  			 ORDER BY FINANCE_NO DESC "+
				"   )P)L  "+
		"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
			*/
		
		m_help_CR_DR_NOTES_FINANCE_NO =" SELECT P.NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME,NIC_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME ,NIC_NO"+
			" FROM "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME CLIENT_NAME ,B.NIC_NO "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE=B.CLIENT_CODE AND  "+
			" ( UPPER(A.FINANCE_NO)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"   OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.FULL_NAME)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.TEL_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.NIC_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			" AND   APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','REPOSSESS','TERM_TO','TERMI') "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		//Added by Dineth on 2008-09-15
		m_help_CR_DR_NOTES_FINANCE_NO_1 =" SELECT P.NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME,NIC_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME ,NIC_NO"+
			" FROM "+
			" ((SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME CLIENT_NAME ,B.NIC_NO "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE=B.CLIENT_CODE AND  "+
			" ( UPPER(A.FINANCE_NO)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"   OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.FULL_NAME)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.TEL_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.NIC_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%'))AND  "+ 
			//	" 	UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER( '%"+m_vector.elementAt(1)+"%') AND "+  // Added by prabash on 16-02-2012
			//" AND   APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','REPOSSESS','TERM_TO','TERMI')) "+
			"   APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL')) "+//Sandun on 18-02-2009
			//" UNION ALL "+
			" UNION "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME CLIENT_NAME ,B.NIC_NO "+
			" FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION A ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE=B.CLIENT_CODE AND  "+
			" ( UPPER(A.FINANCE_NO)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"   OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.FULL_NAME)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.TEL_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.NIC_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%'))AND "+ 
			//	" 	UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER( '%"+m_vector.elementAt(1)+"%') AND "+  // Added by prabash on 16-02-2012
			" 	A.ACTIVE_STATUS IN ('ENT','APPRO1','APPRO2')) "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		//End by Dineth on 2008-09-15
		
		
		/*-------------------------------------------------------
		Purpose 		: Get The Invoice Number
		Used In 		:Credit - Credit Notes Generation
		Created By	:Nuwan De Silva(8/1/07)
		---------------------------------------------------------*/
		
		m_help_CR_DR_NOTES_INVOICE_NO=					
			
			" SELECT L.NO ,NVL(L.INVOICE_NO,'-') INVOICE_NO,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.VALUE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.VALUE_DATE "+
			" FROM( "+ 
			"		SELECT "+
			" A.INVOICE_NO, "+
			" A.TOTAL_AMOUNT, "+
			" A.BALANCE_TO_BE_RECEIVED, "+
			//	"  (BALANCE_TO_BE_RECEIVED - (NVL(ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
			"  TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE "+
			"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	WHERE A.FINANCE_NO = B.FINANCE_NO "+  
			" AND A.FINANCE_NO=UPPER('"+m_vector.elementAt(0)+"') AND "+
			" A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			"  A.BALANCE_TO_BE_RECEIVED>0 AND "+
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			//	"  B.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		/*-------------------------------------------------------
		Purpose 		: Get The Invoice Number
		Used In 		:Credit - Credit Notes Generation
		Created By	:Nuwan De Silva(8/1/07)
		---------------------------------------------------------*/
		
		m_help_CR_DR_NOTES_INVOICE_NO_ADJ=					
			
			" SELECT L.NO ,NVL(L.INVOICE_NO,'-') INVOICE_NO,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.VALUE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.VALUE_DATE "+
			" FROM( "+ 
			"		SELECT "+
			" A.INVOICE_NO, "+
			" A.TOTAL_AMOUNT, "+
			" A.BALANCE_TO_BE_RECEIVED, "+
			//	"  (BALANCE_TO_BE_RECEIVED - (NVL(ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
			"  TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE "+
			"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	WHERE A.FINANCE_NO = B.FINANCE_NO "+  
			" AND A.FINANCE_NO=UPPER('"+m_vector.elementAt(0)+"') AND "+
			" A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+
			"  A.BALANCE_TO_BE_RECEIVED>0 AND "+
			"  INVOICE_TYPE NOT LIKE '%ODI%' AND "+ //ADDED BY SH ON 15-02-2010
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			//	"  B.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS') "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		/*-------------------------------------------------------
		Purpose 		: Get The Credit Number
		Used In 		:Credit - Credit Notes Generation
		Created By	:Nuwan De Silva(10/1/07)
		---------------------------------------------------------*/
		
		m_help_CR_DR_NOTES_CREDIT_NO=			
			
			" SELECT L.NO,L.REF_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVOICE_NO,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.ADJUSTED_AMOUNT,L.VALUE_DATE,L.CREDIT_TYPE,L.ADJUSTED_DATE, "+
			" L.REMARKS,L.DOC_REF_NO,L.ADJUST_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REF_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVOICE_NO,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.ADJUSTED_AMOUNT,P.VALUE_DATE,P.CREDIT_TYPE,P.ADJUSTED_DATE, "+
			" P.REMARKS,P.DOC_REF_NO,P.ADJUST_TYPE "+
			" FROM( "+ 						
			" SELECT "+
			" A.REF_NO, "+
			" A.FINANCE_NO, "+
			" B.CLIENT_CODE, "+
			" B.FULL_NAME, "+
			" A.INVOICE_NO, "+
			" C.TOTAL_AMOUNT, "+
			" C.BALANCE_TO_BE_RECEIVED, "+
			//  " (C.BALANCE_TO_BE_RECEIVED - (NVL(C.ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
			" A.ADJUSTED_AMOUNT, "+
			" TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
			" A.CREDIT_TYPE, "+
			" NVL((TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY')),'-') ADJUSTED_DATE, "+
			" NVL(A.REMARKS,'-') REMARKS, "+
			" NVL(A.DOC_REF_NO,'-') DOC_REF_NO, "+
			" NVL(A.ADJUST_TYPE,'-') ADJUST_TYPE " +
			
			" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
			
			" WHERE  "+
			"  A.REF_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND "+
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND  "+
			"  A.FINANCE_NO=C.FINANCE_NO AND "+
			"  A.INVOICE_NO=C.INVOICE_NO AND "+
			//    "  C.BALANCE_TO_BE_RECEIVED>0 AND "+
			"  C.CLIENT_CODE=B.CLIENT_CODE "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Client for history application 
		By			 : delanjali
		date		 : 02-02-2007

	-----------------------------------------------------------------*/							
		
		m_help_TXT_CLIENT_CODE_app=
			
			" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name,l.APPLICATION_NO, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic,l.finance_no "+
			"FROM "+
			"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME,p.APPLICATION_NO, P.TEL_NO, P.NIC_NO,p.finance_no "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE, FULL_NAME,APPLICATION_NO, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS,finance_no "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE (upper(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"   AND     A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
			"   AND     upper(APPLICATION_NO) like ('%"+m_vector.elementAt(1)+"%')  "+
			
			"AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY CLIENT_CODE DESC)P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		
		
		m_help_TXT_INVOICE_NO_app_sql=
			
			" SELECT L.NO,L.REG_NO,L.INVOICE_NO,L.APPLICATION_NO,l.finance_no,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS,L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID,L.FUEL_CONVERTION_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.INVOICE_NO,P.APPLICATION_NO,p.finance_no,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE,P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS"+
			" FROM( "+ 
			" SELECT "+
			" REG_NO, "+
			" A.INVOICE_NO, "+
			" A.APPLICATION_NO, "+
			" finance_no ,"+
			" A.ASSET_ID, "+
			" A.ENGINE_NO, "+
			" A.CHASSIS_NO, "+
			// " A.REG_NO, "+
			" A.REG_DATE, "+
			" A.PRICING_NO, "+
			" A.SUB_MODEL_CODE, "+
			" A.COLOUR, "+
			" A.SEATING_CAPACITY, "+
			" A.NET_PRICE, "+
			" A.VAT, "+
			" A.TOTAL_AMOUNT, "+
			" A.TO_BE_DELIVERD_TO, "+
			" A.VALUE, "+
			" A.CURR_CODE, "+
			" A.MODEL_CODE, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
			" NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO, "+
			" NVL(A.CITY_CODE,'-') CITY_CODE, "+
			" NVL(A.ADDRESS,'-') ADDRESS, "+
			" NVL(A.VENDOR_CODE,'-') VENDOR_CODE, "+
			" NVL( (SELECT B.NAME FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
			" NVL(A.BRANCH_ID,'-')  BRANCH_ID,"+
			" A.FUEL_CONVERTION_STATUS  FUEL_CONVERTION_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
			" WHERE upper(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			//	" AND CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND upper(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			"AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			"order by REG_NO DESC "+
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		FinanceSql_new =     " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//	" AND APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		//---------------------------------------------------------------------------------------------------
		//--date		:12-02-2007------------------------------------------------------------------------------
		//--purpose	:to get application no for entering lease no-------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------
		m_help_TXT_APPLICATION_NO_lease=
			
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FACILITY_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			" CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			//  " DISTRICT_CODE, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" AND (APPLICATION_STATUS=('"+m_vector.elementAt(1)+"'))"+  // OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')
			" ORDER BY ENT_DATE DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*----------------------------------------------------------------
		Purpose  : Select Client for history application 
		By			 : delanjali
		date		 : 02-02-2007

	-----------------------------------------------------------------*/							
		m_help_TXT_CLIENT_CODE_new=
			
			" SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic "+
			"FROM "+
			"(SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO "+
			"FROM "+
			"(SELECT DISTINCT A.CLIENT_CODE, FULL_NAME, TEL_NO, NIC_NO,  ACTIVE_STATUS, TEMP_ACTIVE_STATUS "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT A "+//,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE (upper(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(TEL_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        upper(NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"   AND     A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			//	"   AND     upper(APPLICATION_NO) like ('"+m_vector.elementAt(1)+"%')  "+
			
			//	"AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" ORDER BY FULL_NAME )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		m_help_TXT_POLICY_NO_new=
			
			" SELECT L.NO, NVL(L.POLICY_NO,'-') \"Policy No\", NVL(L.DEBIT_NOTE_NO,'-') \"Debit Note No\", NVL(L.FINANCE_NO,'-') \"Finance No\", NVL(L.CLIENT_NAME,'-') \"Client Name\" "+
			"FROM "+
			"(SELECT ROWNUM NO, P.POLICY_NO, P.DEBIT_NOTE_NO, P.FINANCE_NO, P.CLIENT_NAME "+
			"FROM "+
			"(SELECT DISTINCT A.POLICY_NO, A.DEBIT_NOTE_NO, A.FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME"+
			"	 FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO AND UPPER(A.POLICY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			
			// commented by udara 12-03-2014
			/*
			// added by udara 10-11-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE' AND INT_BAL_SETTLE_AMOUNT > 0 AND BAL_TO_BE_PAID = 0)) "+
		      	//" OR "+ // temp test
		      	//" (B.APPLICATION_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE')) "+ // temp test
		     " ) "+
			// end by udara 10-11-2014
			*/
			
			// added by udara 03-12-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN ( "+
				
						" SELECT X.REF_NO "+
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
							" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+
							" AND Y.PAYMENT_NO = Z.PAYMENT_NO "+
							" AND X.SUSPENSE_ENTRY_TYPE = 'INSURANCE'  "+
							" AND X.INT_BAL_SETTLE_AMOUNT > 0  "+
							" AND X.BAL_TO_BE_PAID = 0 "+
							" AND Z.PROCESS_STATUS = 'DISBRS' "+
						
						" )) "+
		     " ) "+
			// end by udara 03-12-2014
			
			" ORDER BY A.POLICY_NO )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		
		
		
		
		m_help_TXT_DEBIT_NO_POLICY=
			" SELECT L.NO, NVL(L.POLICY_NO,'-') \"Policy No\", NVL(L.DEBIT_NOTE_NO,'-') \"Debit Note No\", NVL(L.FINANCE_NO,'-') \"Finance No\", NVL(L.CLIENT_NAME,'-') \"Client Name\" "+
			"FROM "+
			"(SELECT ROWNUM NO, P.POLICY_NO, P.DEBIT_NOTE_NO, P.FINANCE_NO, P.CLIENT_NAME "+
			"FROM "+
			"(SELECT DISTINCT A.POLICY_NO, A.DEBIT_NOTE_NO, A.FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME"+
			"	 FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO AND UPPER(A.POLICY_NO) = UPPER('"+m_vector.elementAt(0)+"')  "+
			
			/*
			// added by udara 10-11-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE' AND INT_BAL_SETTLE_AMOUNT > 0 AND BAL_TO_BE_PAID = 0)) "+
		      	//" OR "+ // temp test
		      	//" (B.APPLICATION_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE')) "+ // temp test
		     " ) "+
			// end by udara 10-11-2014
			*/
			
			// added by udara 03-12-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN ( "+
				
						" SELECT X.REF_NO "+
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
							" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+
							" AND Y.PAYMENT_NO = Z.PAYMENT_NO "+
							" AND X.SUSPENSE_ENTRY_TYPE = 'INSURANCE'  "+
							" AND X.INT_BAL_SETTLE_AMOUNT > 0  "+
							" AND X.BAL_TO_BE_PAID = 0 "+
							" AND Z.PROCESS_STATUS = 'DISBRS' "+
						
						" )) "+
		     " ) "+
			// end by udara 03-12-2014
			
			
			" ORDER BY A.POLICY_NO )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		
		m_help_TXT_DEBIT_NO=
			" SELECT L.NO, NVL(L.POLICY_NO,'-') \"Policy No\", NVL(L.DEBIT_NOTE_NO,'-') \"Debit Note No\", NVL(L.FINANCE_NO,'-') \"Finance No\", NVL(L.CLIENT_NAME,'-') \"Client Name\" "+
			"FROM "+
			"(SELECT ROWNUM NO, P.POLICY_NO, P.DEBIT_NOTE_NO, P.FINANCE_NO, P.CLIENT_NAME "+
			"FROM "+
			"(SELECT  A.POLICY_NO, A.DEBIT_NOTE_NO, A.FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME"+
			"	 FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO AND UPPER(A.DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			
			// added by udara 03-12-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN ( "+
				
						" SELECT X.REF_NO "+
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
							" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+
							" AND Y.PAYMENT_NO = Z.PAYMENT_NO "+
							" AND X.SUSPENSE_ENTRY_TYPE = 'INSURANCE'  "+
							" AND X.INT_BAL_SETTLE_AMOUNT > 0  "+
							" AND X.BAL_TO_BE_PAID = 0 "+
							" AND Z.PROCESS_STATUS = 'DISBRS' "+
						
						" )) "+
		     " ) "+
			// end by udara 03-12-2014
			
			
			" ORDER BY A.POLICY_NO )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		m_help_TXT_FINANCE_NO_COMMISSION=
			" SELECT L.NO,  NVL(L.FINANCE_NO,'-') \"Finance No\", NVL(L.CLIENT_NAME,'-') \"Client Name\", NVL(L.POLICY_NO,'-') \"Policy No\", NVL(L.DEBIT_NOTE_NO,'-') \"Debit Note No\",NVL(L.VEHICLE_NO,'-')\"Vehicle No\"   "+ //vehicle no added by kanchana on 2016-02-08 for $#19438
			"FROM "+
			"(SELECT ROWNUM NO, P.POLICY_NO, P.DEBIT_NOTE_NO, P.FINANCE_NO, P.CLIENT_NAME,P.VEHICLE_NO "+ //vehicle no added by kanchana on 2016-02-08 for $#19438
			"FROM "+
			"(SELECT  A.POLICY_NO, A.DEBIT_NOTE_NO, A.FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME,"+
			" "+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO) VEHICLE_NO "+ //vehicle no added by kanchana on 2016-02-08 for $#19438
			"	 FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+
			"         "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			//"  WHERE A.FINANCE_NO=B.FINANCE_NO AND UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+ // commented by udara 25-03-2016
			"  WHERE A.FINANCE_NO=B.FINANCE_NO "+
			"  AND (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER("+m_schema_name+".AF_CO_GET_VEHICLE_NO(B.APPLICATION_NO)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+ // added by udara 25-03-2016
			
			
			// commented by udara 03-12-2014
			/*
			// added by udara 10-11-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE' AND INT_BAL_SETTLE_AMOUNT > 0 AND BAL_TO_BE_PAID = 0 )) "+
		      	//" OR "+ // temp test
		      	//" (B.APPLICATION_NO IN (SELECT REF_NO from "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT WHERE SUSPENSE_ENTRY_TYPE = 'INSURANCE')) "+ // temp test
		     " ) "+
			// end by udara 10-11-2014
			*/
			
			// added by udara 03-12-2014
			 " AND ( "+
				" (A.REF_DEBIT_NOTE_NO IN ( "+
				
						" SELECT X.REF_NO "+
							" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
							" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+
							" AND Y.PAYMENT_NO = Z.PAYMENT_NO "+
							" AND X.SUSPENSE_ENTRY_TYPE = 'INSURANCE'  "+
							" AND X.INT_BAL_SETTLE_AMOUNT > 0  "+
							//" AND X.BAL_TO_BE_PAID = 0 "+
							" AND X.BAL_TO_BE_PAID >= 0 "+ // added by udara 28-03-2016
							" AND Z.PROCESS_STATUS = 'DISBRS' "+
						
						" )) "+
		     " ) "+
			// end by udara 03-12-2014
			
			" ORDER BY A.FINANCE_NO )P) L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		
		
		m_help_TXT_INVOICE_NO_new_sql=
			
			" SELECT L.NO,L.REG_NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_DATE,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" REG_NO, "+
			" A.INVOICE_NO, "+
			" A.APPLICATION_NO, "+
			" A.ASSET_ID, "+
			" A.ENGINE_NO, "+
			" A.CHASSIS_NO, "+
			" A.REG_DATE, "+
			" A.SUB_MODEL_CODE, "+
			" A.COLOUR, "+
			" A.SEATING_CAPACITY, "+
			" A.NET_PRICE, "+
			" A.VAT, "+
			" A.TOTAL_AMOUNT, "+
			" A.TO_BE_DELIVERD_TO, "+
			" A.VALUE, "+
			" A.CURR_CODE, "+
			" A.MODEL_CODE, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
			" WHERE upper(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		m_help_TXT_ACC_sql=	
			"SELECT L.NO ,L.CLIENT_CODE,L.ACCOUNT_NO,L.BANK_CODE,L.BRANCH_CODE,L.BRANCH_NAME "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.CLIENT_CODE,P.ACCOUNT_NO,P.BANK_CODE,P.BRANCH_CODE,P.BRANCH_NAME "+
			"FROM(  "+
			" SELECT "+
			"DISTINCT A.CLIENT_CODE,ACCOUNT_NO,BANK_CODE,A.BRANCH_CODE,"+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) BRANCH_NAME "+
			"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE UPPER(ACCOUNT_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			"order by ACCOUNT_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_SO_NO_sql=
			
			"SELECT L.NO ,L.SO_NO,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE, "+
			"L.REG_NO,L.END_DATE,L.START_DATE,L.ACC_NO,L.BANK_CODE,L.AMOUNT,L.BRANCH_NAME "+
			"FROM     "+
			"(SELECT ROWNUM NO,P.SO_NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE, "+
			"P.REG_NO,P.END_DATE,P.START_DATE,P.ACC_NO,P.BANK_CODE,P.AMOUNT,P.BRANCH_NAME  "+
			"FROM(   "+
			"SELECT  "+
			"SO_NO,  "+
			"A.FINANCE_NO, "+
			"B.APPLICATION_NO, "+
			"CLIENT_CODE, "+
			"REG_NO, "+
			"TO_CHAR(END_DATE,'DD-MM-YYYY') END_DATE,  "+
			"TO_CHAR(START_DATE,'DD-MM-YYYY') START_DATE,  "+
			"ACC_NO, "+
			"BANK_CODE,  "+
			"AMOUNT, "+
			""+m_schema_name+".AF_CO_GET_BRANCH_NAME(BANK_CODE) BRANCH_NAME "+
			"FROM  "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
			"WHERE (UPPER(SO_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//MODIFIED BY DELANJALI FOR REF NO 796 ON 2007-08-16--------------------------------------------------------------
			"OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"AND A.FINANCE_NO=B.FINANCE_NO "+
			"AND B.APPLICATION_NO=C.APPLICATION_NO "+
			"AND STATUS =UPPER('"+m_vector.elementAt(1)+"') "+
			"order by so_no DESC "+
			" )P)L     "+			
			" WHERE L.NO>= "+ Start_Val+"   AND L.NO<=  "+End_Val+" ";
		
		
		m_help_CR_DR_NOTES_CREDIT_NO1=			
			
			" SELECT L.NO,L.REF_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.INVOICE_NO,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.ADJUSTED_AMOUNT,L.VALUE_DATE,L.CREDIT_TYPE,L.ADJUSTED_DATE, "+
			" L.REMARKS,L.DOC_REF_NO,L.ADJUST_TYPE,L.NARRATIONS_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REF_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVOICE_NO,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.ADJUSTED_AMOUNT,P.VALUE_DATE,P.CREDIT_TYPE,P.ADJUSTED_DATE, "+
			" P.REMARKS,P.DOC_REF_NO,P.ADJUST_TYPE,P.NARRATIONS_CODE "+
			" FROM( "+ 						
			" SELECT "+
			" A.REF_NO, "+
			" A.FINANCE_NO, "+
			" B.CLIENT_CODE, "+
			" B.FULL_NAME, "+
			" A.INVOICE_NO, "+
			" C.TOTAL_AMOUNT, "+
			" C.BALANCE_TO_BE_RECEIVED, "+
			//  " (C.BALANCE_TO_BE_RECEIVED - (NVL(C.ADJUSTED_AMOUNT,0))) BALANCE_TO_BE_RECEIVED, "+
			" A.ADJUSTED_AMOUNT, "+
			" TO_CHAR(C.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE, "+
			" A.CREDIT_TYPE, "+
			" NVL((TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY')),'-') ADJUSTED_DATE, "+
			" NVL(A.REMARKS,'-') REMARKS, "+
			" NVL(A.DOC_REF_NO,'-') DOC_REF_NO, "+
			" NVL(A.ADJUST_TYPE,'-') ADJUST_TYPE,NARRATIONS_CODE " +
			
			" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
			
			" WHERE  "+
			" ( UPPER(A.REF_NO)          LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"   UPPER(A.INVOICE_NO)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"   UPPER(B.FULL_NAME)       LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"   UPPER(B.CLIENT_CODE)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND  "+ //modified by nuwan de silva on 10-09-07
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND  "+
			"  A.FINANCE_NO=C.FINANCE_NO AND "+
			"  A.INVOICE_NO=C.INVOICE_NO AND "+
			//    "  C.BALANCE_TO_BE_RECEIVED>0 AND "+
			"  C.CLIENT_CODE=B.CLIENT_CODE "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		m_help_TXT_FinanceSql_new =
			" SELECT P.NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY APPLICATION_NO DESC)) P "+
			" WHERE P.NO>= "+ Start_Val +" AND P.NO<= "+End_Val+" ";
		
		m_help_TXT_INVOICE_NO_app_sql1=
			
			" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,l.finance_no,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS,L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID,L.FUEL_CONVERTION_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,p.finance_no,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE,P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS"+
			" FROM( "+ 
			" SELECT "+
			" A.INVOICE_NO, "+
			" A.APPLICATION_NO, "+
			" finance_no ,"+
			" A.ASSET_ID, "+
			" A.ENGINE_NO, "+
			" A.CHASSIS_NO, "+
			" A.REG_NO, "+
			" A.REG_DATE, "+
			" A.PRICING_NO, "+
			" A.SUB_MODEL_CODE, "+
			" A.COLOUR, "+
			" A.SEATING_CAPACITY, "+
			" A.NET_PRICE, "+
			" A.VAT, "+
			" A.TOTAL_AMOUNT, "+
			" A.TO_BE_DELIVERD_TO, "+
			" A.VALUE, "+
			" A.CURR_CODE, "+
			" A.MODEL_CODE, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
			" NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO, "+
			" NVL(A.CITY_CODE,'-') CITY_CODE, "+
			" NVL(A.ADDRESS,'-') ADDRESS, "+
			" NVL(A.VENDOR_CODE,'-') VENDOR_CODE, "+
			" NVL( (SELECT B.NAME FROM "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
			" NVL(A.BRANCH_ID,'-')  BRANCH_ID,"+
			" A.FUEL_CONVERTION_STATUS  FUEL_CONVERTION_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
			" WHERE UPPER(A.INVOICE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" AND UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND A.ACTIVE_STATUS=('Y') "+
			" AND APPLICATION_STATUS='ACTIVATED' "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			" ORDER BY A.INVOICE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_ACCOUNT_new_sql=	
			"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.BRANCH_NAME,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.BRANCH_NAME,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC "+
			"FROM(  "+
			" SELECT "+
			" ACC_NO, "+
			" BRANCH_CODE, "+	
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE) BRANCH_NAME, "+
			" ACC_SYS_REFNO, "+
			" CURR_CODE, "+
			" ACC_CODE, "+
			" ACC_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			// " AND ACC_NO IN "+
			// "(SELECT LIC_ACC_NO from "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
			// "      WHERE PROCESS_STATUS='"+m_vector.elementAt(1)+"' "+
			// "      AND UPPER(LIC_ACC_NO) like UPPER('"+m_vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_ClientSql1 =   	"SELECT P.NO, P.CLIENT_CODE Client,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT CLIENT_CODE,FULL_NAME, FIRST_NAME, SURNAME,NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-') NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					UPPER(CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		
		m_help_TXT_PRINTER_NAME_sql=
			" SELECT P.NO,P.PRINTER_NAME,P.PRINTER_DESC "+
			" FROM ( "+
			" SELECT ROWNUM NO,PRINTER_NAME,PRINTER_DESC "+
			" FROM ( "+
			" SELECT PRINTER_NAME,PRINTER_DESC "+
			" FROM "+m_schema_name+".CO_MAS_PRINTER "+
			" WHERE ACTIVE_STATUS='Y' "+
			" )) P "+
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		FinanceSql_sus_payment = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ /*THIS PART ADDED BY ASHINI ON 19-09-2007 */
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ /*THIS PART ADDED BY ASHINI ON 19-09-2007 */
			" AND UPPER(A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		
		
		m_help_DR_CANCEL_INVOICE_NO=		//Added By Sandun on 18-11-2008						
			" SELECT L.NO ,NVL(L.INVOICE_NO,'-') INVOICE_NO,L.FINANCE_NO,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.VALUE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,p.FINANCE_NO,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.VALUE_DATE "+
			" FROM( "+ 
			"	SELECT "+
			" INVOICE_NO, "+
			" FINANCE_NO ,"+
			" TOTAL_AMOUNT, "+
			" BALANCE_TO_BE_RECEIVED, "+
			" TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE "+
			"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
			" WHERE   (FINANCE_NO=UPPER('"+m_vector.elementAt(0)+"') OR "+	
			" INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			" FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND "+ //added by Prabash on 16-02-2012
			
			//	  " BALANCE_TO_BE_RECEIVED > 0 AND "+
			" ACTIVE_STATUS='Y' "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		m_help_TXT_FINANCE_NO_IN_DETA =//added By Sandun on 9-12-2008
			" SELECT DISTINCT L.NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+//,L.PRO_INVOICE_NO "+//Commented by Dineth on 29-07-2009
			" FROM  "+
			" (SELECT DISTINCT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+// ,P.PRO_INVOICE_NO"+ //Commented by Dineth on 29-07-2009
			" FROM( "+ 
			" SELECT DISTINCT "+//DISTINCT Uncommented by Dineth on 29-07-2009
			" A.APPLICATION_NO , "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
			" A.CO_APPLICANT,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(A.CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') MK_OFFICER "+
			//" B.PRO_INVOICE_NO "+ //Commented by Dineth on 29-07-2009
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B "+
			" WHERE (A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+		
			" UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			" AND A.FINANCE_NO = B.FINANCE_NO "+
			" AND A.APPLICATION_STATUS = 'ACTIVATED' "+ 
			" AND B.END_DATE >= TO_DATE('"+m_vector.elementAt(2)+"','DD-MM-YYYY') "+
			" AND B.END_DATE <= TO_DATE('"+m_vector.elementAt(1)+"','DD-MM-YYYY') "+
			//" ORDER BY A.ENT_DATE DESC "+//Commented by Dineth on 29-07-2009
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_Company=		//Added By Sandun on 12-01-2009					
			" SELECT L.NO ,L.PAYEE_CODE,L.NAME,L.ADDRESS  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYEE_CODE,P.NAME,P.ADDRESS "+
			" FROM( "+ 
			" SELECT DISTINCT PAYEE_CODE PAYEE_CODE,  "+
			" PAYEE_NAME NAME,  "+
			" PAYEE_ADDRESS ADDRESS "+
			
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF A "+
			//	"	WHERE (PAYEE_CODE = UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//	" PAYEE_NAME LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+  
			" WHERE (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') or UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			" AND ACTIVE_STATUS = 'Y'     "+ // added by udara 03-09-2015
			" AND SUB_TYPE_CODE = 'INSURANCE' "+ // released by udara 03-09-2021
			" )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		// added by udara on 23-11-2012
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
			// end by udara on 23-11-2012
			
			
			// added by udara on 10-04-2013
			
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
			
			// end by udara on 10-04-2013
			
			
			// added by udara on 10-04-2013
			
			m_help_TXT_INSU_AGENT_sql=
            
            " SELECT L.NO ,L.PAYEE_CODE,L.PAYEE_NAME "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.PAYEE_CODE,P.PAYEE_NAME"+
            " FROM( "+ 
            " SELECT "+
            " PAYEE_CODE, "+
            " PAYEE_NAME "+
           
            " FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
            //  " WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
            " WHERE SUB_TYPE_CODE='INSURANCE' AND ( PAYEE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+   //Added by Prabsh on 03-02-2012
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			// end by udara on 10-04-2013
		
		
		m_help_Finance_Is_History=" SELECT L.NO ,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE,L.NAME,NVL(L.TEL_NO,'-') TEL_NO  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.NAME,P.TEL_NO "+
			" FROM( "+ 
			" SELECT A.FINANCE_NO FINANCE_NO , "+
			" A.APPLICATION_NO APPLICATION_NO,  "+
			" A.CLIENT_CODE CLIENT_CODE, "+
			" B.TITLE || ' ' || B.FULL_NAME NAME, "+      
			" B.TEL_NO  TEL_NO"+             
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			" WHERE A.CLIENT_CODE = B.CLIENT_CODE "+
			" AND  A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE) "+
			" AND (A.FINANCE_NO = UPPER('"+m_vector.elementAt(0)+"') OR "+
			" B.FULL_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+  
			" ORDER BY A.APPLICATION_NO DESC "+
			" )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		
		
		m_help_INVOICE_NO_CR_DR_NOTES=		 //Added By Sandun on 22-01-2009			
			
			" SELECT L.NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,NVL(L.INVOICE_NO,'-') INVOICE_NO,L.CLIENT_CODE,L.TOTAL_AMOUNT,L.BALANCE_TO_BE_RECEIVED,L.VALUE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.INVOICE_NO,P.CLIENT_CODE,P.TOTAL_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.VALUE_DATE "+
			" FROM( "+ 
			"	SELECT "+
			" A.FINANCE_NO, "+
			" A.INVOICE_NO, "+				
			" B.CLIENT_CODE ,"+
			" A.TOTAL_AMOUNT, "+
			" A.BALANCE_TO_BE_RECEIVED, "+
			"  TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE "+
			"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"	WHERE A.FINANCE_NO = B.FINANCE_NO "+
			" AND (A.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR A.INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"  A.BALANCE_TO_BE_RECEIVED>0 AND "+
			"  A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//MALIK ON 8/4/2009
			"  AND A.FINANCE_NO IN(SELECT FINANCE_NO FROM "+//add for finance no credits notes by malik on 8/4/2009
			"  "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS) "+
			" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM  "+
			" "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
			" WHERE A.FINANCE_NO=FINANCE_NO) "+
			//END	
			" ORDER BY A.INVOICE_NO DESC"+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		//Added By Sandun on 15-06-2009
		m_help_TXT_PURCHASE_ORDER_NO1=
			" SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE,L.VENDER_NAME,L.BRANCH_CODE,L.LOCATION_CODE,L.CLIENT_CODE,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.PRINTED_DATE,L.TRANSACTION_TYPE,L.PRINTED_STATUS "+//Modified by Dineth on 26-03-2009
			" FROM "+
			" (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE,P.VENDER_NAME,P.BRANCH_CODE,P.LOCATION_CODE,P.CLIENT_CODE,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.PRINTED_DATE,P.TRANSACTION_TYPE,P.PRINTED_STATUS "+//Modified by Dineth on 26-03-2009
			" FROM( "+
			" SELECT "+
			" DISTINCT A.PURCHASE_ORDER_NO PURCHASE_ORDER_NO, "+
			" A.APPLICATION_NO APPLICATION_NO, "+
			" A.VENDER_CODE VENDER_CODE, "+
			" "+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE) VENDER_NAME, "+
			" A.BRANCH_CODE BRANCH_CODE, "+
			" C.LOCATION_CODE, "+
			" B.CLIENT_CODE CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+
			" B.TEL_NO TEL_NO, "+
			" B.NIC_NO NIC_NO, "+
			" NVL((TO_CHAR(A.PRINTED_DATE,'DD-MM-YYYY')),'-') PRINTED_DATE, "+
			" B.TRANSACTION_TYPE, "+
			" NVL(A.PRINTED_STATUS,'-') PRINTED_STATUS "+
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			" (SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO,X.TRANSACTION_TYPE,X.FINANCE_NO "+ //modified by nuwan de silva on 06-02-2008
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V "+
			" WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			" AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')) B, "+
			" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION C "+
			" WHERE A.APPLICATION_NO =B.APPLICATION_NO "+
			" AND UPPER(C.BRANCH)=UPPER(A.BRANCH_CODE) "+
			" AND "+
			" (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(A.VENDER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER("+m_schema_name+".AF_CO_GET_VEN_NAME(VENDER_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" A.PURCHASE_ORDER_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PURCHASE_ORDER_NO DESC "+
			" )P)L "+	
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// Added by Thamali Jayatunga on 2011.07.11				
		m_help_insurance_company_code=
			
			" SELECT L.NO,L.PAYEE_CODE,L.PAYEE_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYEE_CODE,P.PAYEE_NAME "+
			" FROM( "+ 
			" SELECT "+
			" PAYEE_CODE, "+
			" PAYEE_NAME "+
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
			" WHERE SUB_TYPE_CODE = 'INSURANCE' "+
			" AND  ACTIVE_STATUS = 'Y'  "+ // added by udara 26-06-2014
			" AND   (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR    UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//Added by Sandun on 22-06-2009
		MKOfficer_help_Sql =" SELECT NO,CODE,NAME,ADDRESS,LOCATION,DESIGNATION,CONTACT "+
			" FROM  ( SELECT ROWNUM NO,CODE,NAME,ADDRESS,LOCATION,DESIGNATION,CONTACT "+
			" FROM  ( SELECT DISTINCT A.EMP_CODE CODE, "+
			" A.FIRST_NAME||' '||A.LAST_NAME NAME, "+
			" NVL(A.ADDRESS,'-') ADDRESS,  "+
			" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE),'-') LOCATION, "+
			" NVL("+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(A.DESIGNATION_CODE),'-') DESIGNATION, "+
			" NVL(A.CONTACT_NO,'-') CONTACT "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
			" WHERE A.EMP_CODE= B.COLLECTION_OFFICER "+
			" AND A.ACTIVE_STATUS = 'Y' "+
			" AND (UPPER(FIRST_NAME) LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" UPPER(FIRST_NAME) LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" UPPER(LAST_NAME) LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" UPPER(EMP_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" UPPER(LOCATION_CODE) LIKE '%"+m_vector.elementAt(0)+"%' OR"+
			" UPPER(CONTACT_NO) LIKE '%"+m_vector.elementAt(0)+"%' "+
			" )"+
			" ORDER BY A.EMP_CODE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		//Added by Sandun on 22-06-2009
		m_help_TXT_FINANCE_NO_5 = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,COLLECTION_OFFICER "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,COLLECTION_OFFICER "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME ,"+
			" NVL(COLLECTION_OFFICER,'-') COLLECTION_OFFICER"+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		FinanceSql_Odi =       " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE))  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			" AND CLIENT_CODE LIKE '%"+m_vector.elementAt(1)+"%' "+
			" AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		ClientSql_odi =   	"SELECT P.NO, P.CLIENT_CODE Client, P.FULL_NAME Name, ACTIVE_STATUS Status "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME,ACTIVE_STATUS "+
			"FROM "+
			"(SELECT CLIENT_CODE , FULL_NAME , ACTIVE_STATUS , TEMP_ACTIVE_STATUS  "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(CLIENT_CODE)LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			"        CLIENT_CODE IN (SELECT CLIENT_CODE "+
			"                        FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			"                      	 WHERE  APPLICATION_STATUS IN ('ACTIVATED','LEGAL') ) "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		
		
		GroupPaymentCodeSql =  " " +
			"   SELECT R.NO \"No.\", " +
			"          R.GROUP_PAYMENT_NO \"Group Payment No.\", " +
			"          R.PAYEE_NAME \"Payee Name\", " +
			"          R.PAYER_BANK_NAME \"Payer Bank Name\", " +
			"          R.CHEQUE_NO \"Cheque No.\", " +
			"          R.PAYMENT_NO \"Payment No.\" "+
			"   FROM ( " +
			"       SELECT ROWNUM NO, " +
			"              P.GROUP_PAYMENT_NO, " +
			"              P.PAYEE_NAME, " +
			"              P.PAYER_BANK_NAME, " +
			"              P.CHEQUE_NO, " +
			"              P.PAYMENT_NO "+ // added by udara 13-10-2015
			"       FROM ( " +
			"           SELECT A.GROUP_PAYMENT_NO, " +
			"                  A.PAYEE_NAME, " +
			"                  NVL(" + m_schema_name + ".AF_CO_GET_BANK_NAME(A.LIC_BRANCH_CODE), '-') PAYER_BANK_NAME, " +
			"                  NVL(A.CHEQUE_NO, '-') CHEQUE_NO, " +
			"                  A.PAYMENT_NO PAYMENT_NO "+ // added by udara 13-10-2015
			"           FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
			//"           WHERE  A.GROUP_PAYMENT_NO LIKE '%" + m_vector.elementAt(0) + "%' " + // commented by udara 13-10-2015
			"           WHERE  ((A.GROUP_PAYMENT_NO LIKE '%" + m_vector.elementAt(0) + "%') OR (A.PAYMENT_NO LIKE '%" + m_vector.elementAt(0) + "%') OR (UPPER(A.PAYEE_NAME) LIKE UPPER('%" + m_vector.elementAt(0) + "%'))) " +
			"           ORDER BY A.GROUP_PAYMENT_NO " +
			"       ) P " +
			"   ) R " +
			"   WHERE R.NO >= " + Start_Val + " AND R.NO <= " + End_Val + " " +
			" ";
		
		
		// added by udara on 07-03-2013
		
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
		
		// end by udara on 07-03-2013
		
		
		// added by udara 31-10-2013

		m_help_ClientSql_Receipt =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.APPLICATION_NO, R.Client, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status   FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client,P.APPLICATION_NO APPLICATION_NO "+ // new
			//"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE,APPLICATION_NO "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,B.APPLICATION_NO  "+ //added by nuwan de silva 25-07-07
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
			
			
			
			"            B.APPLICATION_STATUS  IN ('TERMI','TERMINATED') AND "+// added TERMINATED by udara 03-05-2016 // added by udara on 26-06-2013 // ,'TERMI'
			"            B.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_DEL_LETTER_PROCESS) AND  "+
			//"            B.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_PRO_CR_BOOK WHERE CR_STATUS='SAFE') AND  "+ //Added by jithendr 23-04-2019 JB25092018-05225
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
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+m_vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		
			m_help_ClientSql_Receipt_cp =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.APPLICATION_NO, R.Client, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status   FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client,P.APPLICATION_NO APPLICATION_NO "+ // new
			//"SELECT DISTINCT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE,APPLICATION_NO "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE,B.APPLICATION_NO  "+ //added by nuwan de silva 25-07-07
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     C.ACTIVE_STATUS IN ('Y','T') AND  "+ /*added by ns on 14-12-2012*/
			
			
			
			"            B.APPLICATION_STATUS  IN ('TERMI') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
			"            B.FINANCE_NO  IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_DEL_LETTER_PROCESS) AND  "+
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
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+m_vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ Start_Val+" AND R.AAA <= "+End_Val+" ";
		
		
		
		//================================================================================================================	
		
		
		// added by udara 12-03-2014
		
		m_help_TXT_ITEM_SUB_CAT_DESC_sql=
			
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.DESCRIPTION,L.ITEM_CAT_CODE,L.VAT_APP,L.VAT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.DESCRIPTION,P.ITEM_CAT_CODE,P.VAT_APP,P.VAT,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT ,"+
			" DESCRIPTION, "+
			" ITEM_CAT_CODE, "+
			" NVL(VAT_APP,0) VAT_APP ,"+
			" NVL(VAT,0) VAT ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 12-03-2014
		
		
		// added by udara 20-03-2014
		
		m_help_TXT_ITEM_SUB_CAT_DESC_NEW_sql=
			
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.DESCRIPTION,L.ITEM_CAT_CODE,L.VAT_APP,L.VAT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.DESCRIPTION,P.ITEM_CAT_CODE,P.VAT_APP,P.VAT,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT ,"+
			" DESCRIPTION, "+
			" ITEM_CAT_CODE, "+
			" NVL(VAT_APP,0) VAT_APP ,"+
			" NVL(VAT,0) VAT ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			//" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" WHERE (UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ITEM_SUB_CAT) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+ // added by udara 05-09-2014
			" AND ITEM_SUB_CAT IN (SELECT VEHICLE_TYPE FROM "+m_schema_name+".AF_UPLOAD_ASSET_DETAILS WHERE INSURANCE_AGENT LIKE '"+m_vector.elementAt(2)+"%' ) "+
			" AND ACTIVE_STATUS = 'Y'  "+ // added by udara 05-10-2016
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 20-03-2014
		
		
		// ---------------added by ishani 2014.04.04
		m_help_TXT_FINANCE_NO_4_4 = " SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT NVL(FINANCE_NO,'-') FINANCE_NO,APPLICATION_NO,CLIENT_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') CLIENT_NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE  (upper(FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR upper(CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND upper(APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			//" AND APPLICATION_STATUS IN ('VERIFYL','ACTIVATED','RE-APP','APPRO1','APPRO2') "+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		// added by udara 07-08-2014
		/*
		m_help_TXT_INS_CANCEL = " SELECT P.NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" (SELECT A.FINANCE_NO FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) CLIENT_NAME, "+
			" A.REF_DEBIT_NOTE_NO INVOICE_NO "+
			" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_CO_PRO_INVOICE B "+
			" where a.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
			" and B.SETTELE_AMOUNT = 0 "+
			" AND  ( "+
			" UPPER(A.FINANCE_NO) LIKE UPPER ('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			//" OR UPPER("+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO))) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			//" AND UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		*/
		
		// added by udara 22-08-2014
		// commented by udara 11-03-2015
		/*
		m_help_TXT_INS_CANCEL = "  "+
			" SELECT P.NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" ( "+
			
			" SELECT A.FINANCE_NO FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) CLIENT_NAME, "+
			" A.REF_DEBIT_NOTE_NO INVOICE_NO "+
			" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_CO_PRO_INVOICE B "+
			" where a.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
			" and B.SETTELE_AMOUNT = 0 "+
			" AND  ( "+
			" UPPER(A.FINANCE_NO) LIKE UPPER ('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'LICENSEE' "+
			
			" UNION "+
			
			" SELECT A.FINANCE_NO FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) CLIENT_NAME, "+
			" A.REF_DEBIT_NOTE_NO INVOICE_NO "+
			" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
			" where  ( "+
			" UPPER(A.FINANCE_NO) LIKE UPPER ('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'CLIENT' "+			
			
			" ORDER BY FINANCE_NO DESC"+			
			
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		*/
		
		// added by udara 11-03-2015
		m_help_TXT_INS_CANCEL = "  "+
			" SELECT P.NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,CLIENT_NAME,INVOICE_NO "+
			" FROM "+
			" ( "+
			
			" SELECT A.FINANCE_NO FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) CLIENT_NAME, "+
			" A.REF_DEBIT_NOTE_NO INVOICE_NO "+
			" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_CO_PRO_INVOICE B "+
			" where a.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
			" and B.SETTELE_AMOUNT = 0 "+
			" AND  ( "+
			" UPPER(A.FINANCE_NO) LIKE UPPER ('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			//" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'LICENSEE' "+ // commented by udara 11-03-2015
			// added by udara 11-03-2015
			" and a.REF_DEBIT_NOTE_NO NOT IN ( "+
									
										" SELECT X.REF_NO "+
										" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
										" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+  
										" AND   Z.PAYMENT_NO = Y.PAYMENT_NO "+
										" AND   X.SUSPENSE_ENTRY_TYPE = 'INSURANCE' "+
										//" AND   "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(X.REF_NO)) = '"+m_app_no+"' "+
										" AND   "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(X.REF_NO)) = a.FINANCE_NO "+ // udara 09-03-2015
										" AND Z.PROCESS_STATUS <> 'CANCEL' "+ // added by udara 10-03-2016
									" ) "+
			// added by udara 11-03-2015						
			
			
			" UNION "+
			
			" SELECT A.FINANCE_NO FINANCE_NO, "+
			" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) CLIENT_NAME, "+
			" A.REF_DEBIT_NOTE_NO INVOICE_NO "+
			" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
			" where  ( "+
			" UPPER(A.FINANCE_NO) LIKE UPPER ('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(A.REF_DEBIT_NOTE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			//" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'CLIENT' "+	// commented by udara 11-03-2015		
			" AND REF_DEBIT_NOTE_NO IS  NULL "+ // added by udara 11-03-2015
			" ORDER BY FINANCE_NO DESC"+			
			
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		// end by udara 11-03-2015
		
		
		
		//------------- added by ishani 2014.04.04  end
		
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
			"     UPPER(B.FINANCE_NO)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.EMP_CODE)      LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.FIRST_NAME)    LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR  UPPER(A.LAST_NAME)     LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR  UPPER(B.CLIENT_CODE)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		// added by Minal
		
		
		
		
		// ****Do not Alter The Parts Below
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}

/*----------------------------------------------------------------*/


