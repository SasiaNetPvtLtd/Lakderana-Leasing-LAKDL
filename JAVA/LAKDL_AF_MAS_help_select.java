import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_MAS_help_select  {  
	
	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	
	//------------------------------------------------------------------------
	
	public String m_dash="";
	public String m_dash_Header ="Dash Help";
	
	public String m_help_client_group=""; //Added by nuwan de silva on 14-11-07
	public String m_help_client_group_Header ="Client Group Help";
	
	public String m_help_VendorSql_sql="";
	public String m_help_VendorSql_sql_Header ="Vendor Help";
	
	public String m_help_ReceiptSql_sql="";
	public String m_help_ReceiptSql_sql_Header="";
	
	public String m_help_TXT_APPLICATION_NO="";
	public String m_help_TXT_APPLICATION_NO_Header="Application - Credit Officer";
	
	public String m_help_TXT_APPLICATION_NO_MOD ="";
	public String m_help_TXT_APPLICATION_NO_MOD_Header ="Application - Credit Officer";//milinda
	
	public String m_help_TXT_APPLICATION_NO_REA ="";
	public String m_help_TXT_APPLICATION_NO_REA_Header ="Application - Credit Officer Reactivate";//milinda
	
	
	public String m_help_VendorsPECIFIC_APPLICATION_sql="";
	public String m_help_VendorsPECIFIC_APPLICATION_sql_Header="";
	
	public String m_help_CHANGE_CLIENT_sql=""; // Added by Udar Somathilake on 05-05-2010
	public String m_help_CHANGE_CLIENT_sql_Header=" Marketing - Change CLient Details ";
	
	public String m_help_txt_sub_team_sql ="";
	public String m_help_txt_sub_team_sql_Header ="Sub Team Help";
	
	public String ClientSql= "";
	public String ClientSql_Header="System Administration - Client Code";
	
	public String Client_code_help_client_creation="";
	public String Client_code_help_client_creation_Header="Application Process - Client Code";
	public String Client_code_help_client_creation_temp="";
	public String Client_code_help_client_creation_temp_Header="Application Process - Client Code";
	
	public String m_help_sub_charges_help_liability ="";
	public String m_help_sub_charges_help_liability_Header="System Administration - Sub Type Code";
	
	public String m_help_sub_charge_payee_code ="";
	public String m_help_sub_charge_payee_code_Header="System Adminstration - Sub Charge Reference Payee Help";
	
	public String m_help_TXT_INVOICE_NO_sql= "";
	public String m_help_TXT_INVOICE_NO_sql_Header= "Application Processing - Invoice Number ";
	
	public String m_help_TXT_ASSET_ID_sql= "";
	public String m_help_TXT_ASSET_ID_sql_Header= "Marketing - Asset Details Help ";
	
	public String m_help_TXT_ASSET_ID_sql2= "";
	public String m_help_TXT_ASSET_ID_sql2_Header= "Marketing - Asset Details Help ";
	
	
	public String m_help_TXT_SCREEN_NAME_sql= "";
	public String m_help_TXT_SCREEN_NAME_sql_Header= "System Administration - User Screen";
	
	
	public String m_help_TXT_STAGE_CODE_sql= "";
	public String m_help_TXT_STAGE_CODE_sql_Header= "System Administration - Process Stage";
	
	public String m_help_TXT_SUB_PRODUCT_CODE_sql= "";
	public String m_help_TXT_SUB_PRODUCT_CODE_sql_Header= "System Administration - Sub Product";
	
	
	public String m_help_TXT_REPAYMENT_TYPE_sql= "";
	public String m_help_TXT_REPAYMENT_TYPE_sql_Header="System Administration - Repayment Method";
	
	public String m_help_TXT_REPAYMENT_DESC_sql="";
	public String m_help_TXT_REPAYMENT_DESC_sql_Header="System Administration - Repayment Method";
	
	public String m_help_TXT_USAGE_TYPE_sql= "";
	public String m_help_TXT_USAGE_TYPE_sql_Header="System Administration - Assest Usage Type";
	
	public String m_help_TXT_USAGE_DESC_sql="";
	public String m_help_TXT_USAGE_DESC_sql_Header="System Administration - Assest Usage Type";
	
	public String m_help_TXT_TERMINATION_TYPE_sql= "";
	public String m_help_TXT_TERMINATION_TYPE_sql_Header= "System Administration - Early Termination Charge";
	
	public String m_help_TXT_TERMINATION_DESC_sql="";
	public String m_help_TXT_TERMINATION_DESC_sql_Header= "System Administration - Early Termination Charge";
	
	public String m_help_TXT_TEAM_ID_team_sql= "";
	public String m_help_TXT_TEAM_ID_team_sql_Header= "System Administration - Assign Team Members";
	
	//public String m_help_TXT_TEAM_ID_team_sql_DESC= "";
	//public String m_help_TXT_TEAM_ID_team_sql_DESC_Header= "System Administration - Assign Team Members";
	
	public String m_help_txt_team_sub_team_id_team_sql ="";
	public String m_help_txt_team_sub_team_id_team_sql_Header ="System Administration - Team Help";
	
	public String m_help_TXT_TRN_SUB_TYPE_sql= "";
	public String m_help_TXT_TRN_SUB_TYPE_sql_Header= "System Administration - Transaction Sub Type";
	
	public String m_help_TXT_TRN_SUB_DESC_sql= "";
	public String m_help_TXT_TRN_SUB_DESC_sql_Header= "System Administration - Transaction Sub Type";
	
	
	public String m_help_TXT_TRAN_CODE_sql= "";
	public String m_help_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
	
	public String m_help_TXT_TRAN_DESC_sql= "";
	public String m_help_TXT_TRAN_DESC_sql_Header= "System Administration - Transaction";
	
	
	public String m_help_TXT_TRAN_CODE_sql1= "";
	public String m_help_TXT_TRAN_CODE_sql1_Header= "System Administration - Transaction";
	
	public String m_help_TXT_TRAN_CODE_sql_edit= "";
	public String m_help_TXT_TRAN_CODE_sql_edit_Header= "System Administration - Transaction";
	
	public String m_help_TXT_ACC_CODE_sql= "";
	public String m_help_TXT_ACC_CODE_sql_Header= "Finance - Account Code";
	
	public String m_help_TXT_SOURCE_CODE_sql_desc= "";
	public String m_help_TXT_SOURCE_CODE_sql_desc_Header= "System Administration - Lead Source Category";
	
	
	public String m_help_TXT_SOURCE_CODE_sql= "";
	public String m_help_TXT_SOURCE_CODE_sql_Header= "System Administration - Lead Source Category";
	
	public String m_help_TXT_VEHICLE_NO_sql= "";
	public String m_help_TXT_VEHICLE_NO_sql_Header= "System Administration - Missing Vehicle";
	
	public String m_help_TXT_OPTION_CODE_sql= "";
	public String m_help_TXT_OPTION_CODE_sql_Header= "System Administration - Option";
	
	public String m_help_TXT_TAX_CODE_sql= "";		//Added by Prabash on 13-05-2011
	public String m_help_TXT_TAX_CODE_sql_Header= "System Administration - tax";
	
	
	public String m_help_TXT_MODEL_sql= "";
	public String m_help_TXT_MODEL_sql_Header= "System Administration - Mileage";
	
	public String m_help_TXT_LAWYER_CODE_sql= "";
	public String m_help_TXT_LAWYER_CODE_sql_Header= "System Administration - Lawyer";
	
	public String m_help_TXT_LAWYER_CODE_sql_New= "";//Added by Sandun on 31-07-2008
	public String m_help_TXT_LAWYER_CODE_sql_New_Header= "System Administration - Lawyer";
	
	
	public String m_help_TXT_LAWYER_NAME_sql= "";
	public String m_help_TXT_LAWYER_NAME_sql_Header= "System Administration - Lawyer";
	
	public String m_help_TXT_SUB_M_CODE_DESC_sql= "";
	public String m_help_TXT_SUB_M_CODE_DESC_sql_Header= "System Administration - Sub Model";
	
	public String m_help_TXT_SUB_M_CODE_sql= "";
	public String m_help_TXT_SUB_M_CODE_sql_Header= "System Administration - Sub Model";
	
	public String m_help_TXT_USER_ID_Authorization_sql= "";
	public String m_help_TXT_USER_ID_Authorization_sql_Header= "System Administration - User";
	
	public String m_help_TXT_VALUER_CODE_sql= "";
	public String m_help_TXT_VALUER_CODE_sql_Header= "System Administration - Valuer";
	
	
	public String m_help_TXT_VALUER_NAME_sql= "";
	public String m_help_TXT_VALUER_NAME_sql_Header= "System Administration - Valuer";
	
	public String m_help_TXT_RATING_CODE_sql= "";
	public String m_help_TXT_RATING_CODE_sql_Header= "System Administration - Score Rating";
	
	public String m_help_TXT_RATING_DESC_sql= "";
	public String m_help_TXT_RATING_DESC_sql_Header= "System Administration - Score Rating";
	
	public String m_help_TXT_L_S_CODE_sql= "";
	public String m_help_TXT_L_S_CODE_sql_Header= "System Administration - Lead Source";
	
	public String m_help_TXT_L_S_CODE_sql_new= "";
	public String m_help_TXT_L_S_CODE_sql_new_Header= "System Administration - Lead Source";
	
	public String m_help_TXT_L_S_CODE_sql_desc= "";
	public String m_help_TXT_L_S_CODE_sql_desc_Header= "System Administration - Lead Source";
	
	public String m_help_TXT_INQ_CODE_sql= "";
	public String m_help_TXT_INQ_CODE_sql_Header= "System Administration - Inquiry Status";
	
	public String m_help_TXT_INQ_CODE_sql_desc= "";
	public String m_help_TXT_INQ_CODE_sql_desc_Header= "System Administration - Inquiry Status";
	
	
	public String m_help_TXT_NATIONALITY_CODE_sql= "";
	public String m_help_TXT_NATIONALITY_CODE_sql_Header= "System Administration - Nationality";
	
	public String m_help_TXT_NATIONALITY_CODE_sql_desc= "";
	public String m_help_TXT_NATIONALITY_CODE_sql_desc_Header= "System Administration - Nationality";
	
	// added by udara on 18-05-2012
	public String m_help_TXT_SCHEDULE_CODE_sql = ""; 
	public String m_help_TXT_SCHEDULE_CODE_sql_Header= "System Administration - Schedule Details";
	
	
	public String m_help_TXT_I_T_CODE_sql= "";
	public String m_help_TXT_I_T_CODE_sql_Header= "System Administration - Interest Type";
	
	public String m_help_TXT_I_E_CODE_sql= "";
	public String m_help_TXT_I_E_CODE_sql_Header= "System Administration - Income Expense Type";
	
	public String m_help_TXT_I_E_CODE_sql_desc= "";
	public String m_help_TXT_I_E_CODE_sql_desc_Header= "System Administration - Income Expense Type";
	
	public String m_help_TXT_GARAGE_CODE_sql= "";
	public String m_help_TXT_GARAGE_CODE_sql_Header= "System Administration - Garage";
	
	public String m_help_TXT_GARAGE_CODE_NAME_sql= "";
	public String m_help_TXT_GARAGE_CODE_NAME_sql_Header= "System Administration - Garage";
	
	public String m_help_DIV_TXT_DISPUTE_CODE_sql="";
	public String m_help_DIV_TXT_DISPUTE_CODE_sql_Header="";
	
	
	public String m_help_TXT_BROKER_CODE_sql= "";
	public String m_help_TXT_BROKER_CODE_sql_Header= "System Administration - Broker";
	
	public String m_help_TXT_BROKER_CODE_sql_New= "";//
	public String m_help_TXT_BROKER_CODE_sql_New_Header="System Administration - Broker";//
	
	public String m_help_TXT_BROKER_CODE_BLAK_sql_New= "";//
	public String m_help_TXT_BROKER_CODE_BLAK_sql_New_Header="System Administration - Broker";//
	
	
	
	public String m_help_TXT_BRANCH_CODE_sql= "";
	public String m_help_TXT_BRANCH_CODE_sql_Header= "System Administration - Bank Branch";
	
	public String m_help_TXT_BRANCH_CODE_1_sql= "";
	public String m_help_TXT_BRANCH_CODE_1_sql_Header= "System Administration - Licencee Settlement";
	
	public String m_help_TXT_BRANCH_NAME_sql= "";
	public String m_help_TXT_BRANCH_NAME_sql_Header= "System Administration - Bank Branch";
	
	public String m_help_TXT_SCORE_CODE_sql= "";
	public String m_help_TXT_SCORE_CODE_sql_Header= "Client Help - Credit Score Category";
	
	
	
	public String m_help_TXT_SCORE_DESC_sql= "";
	public String m_help_TXT_SCORE_DESC_sql_Header= "Client Help - Credit Score Category";
	
	public String m_help_TXT_SCORE_SUB_CODE_sql="";
	public String m_help_TXT_SCORE_SUB_CODE_sql_Header= "Client Help - Credit Score Sub Category";
	
	
	public String m_help_TXT_SCORE_SUB_CODE_DESC_sql="";
	public String m_help_TXT_SCORE_SUB_CODE_DESC_sql_Header= "Client Help - Credit Score Sub Category";
	
	
	public String m_help_TXT_SCORE_MODEL_CODE_DESC_sql="";
	public String m_help_TXT_SCORE_MODEL_CODE_DESC_sql_Header= "Client Help - Credit Score Model Creation";
	
	public String m_help_TXT_SCORE_MODEL_CODE_sql="";
	public String m_help_TXT_SCORE_MODEL_CODE_sql_Header= "Client Help - Credit Score Model Creation";
	
	public String m_help_cr_score_application_sql="";
	public String m_help_cr_score_application_sql_Header=" Credit Score - Applications";
	
	public String m_help_cr_score_application_sql_1="";
	public String m_help_cr_score_application_sql_1_Header=" Credit Score - Applications";
	
	
	public String m_help_TXT_PROVINCE_CODE_sql="";	
	public String m_help_TXT_PROVINCE_CODE_sql_Header="Systen Administration - Province Help";	
	
	public String m_help_TXT_PROVINCE_DESC_sql="";	
	public String m_help_TXT_PROVINCE_DESC_sql_Header="Systen Administration - Province Code Help";	
	
	public String m_help_TXT_COUNTRY_CODE_sql="";	
	public String m_help_TXT_COUNTRY_CODE_sql_Header="System Administration - Country";
	
	public String m_help_TXT_CITY_CODE_sql="";	
	public String m_help_TXT_CITY_CODE_sql_Header="System Administration - City";
	
	public String m_help_TXT_CITY_DESC_sql="";	
	public String m_help_TXT_CITY_DESC_sql_Header="System Administration - City";
	
	public String m_help_TXT_DISTRICT_DESC_sql="";
	public String m_help_TXT_DISTRICT_DESC_sql_Header="System Administration - District";
	
	public String m_help_TXT_DISTRICT_CODE_sql="";
	public String m_help_TXT_DISTRICT_CODE_sql_Header="System Administration - District";
	
	public String m_help_TXT_AREA_CODE_sql="";
	public String m_help_TXT_AREA_CODE_sql_Header="System Administration - Area";
	
	public String m_help_TXT_AREA_DESC_sql="";
	public String m_help_TXT_AREA_DESC_sql_Header="System Administration - Area";
	
	public String m_help_TXT_CURR_CODE_sql="";
	public String m_help_TXT_CURR_CODE_sql_Header="System Administration - Currency";
	
	public String m_help_TXT_DESIGNATION_CODE_sql="";
	public String m_help_TXT_DESIGNATION_CODE_sql_Header="System Administration - Designation";
	
	public String m_help_TXT_DESIGNATION_DESC_sql="";
	public String m_help_TXT_DESIGNATION_DESC_sql_Header="System Administration - Designation";
	
	public String m_help_TXT_DIVISION_sql="";
	public String m_help_TXT_DIVISION_sql_Header="System Administration - Division";
	
	public String m_help_TXT_PRODUCT_ID_sql="";
	public String m_help_TXT_PRODUCT_ID_sql_Header="System Administration - Leasing Stage Process";
	
	public String m_help_TXT_POSTAL_CODE_sql="";
	public String m_help_TXT_POSTAL_CODE_sql_Header="System Administration - Postal Code";
	
	public String m_help_TXT_POSTAL_CODE_NEW_sql="";
	public String m_help_TXT_POSTAL_CODE_NEW_sql_Header="System Administration - Postal Code";
	
	
	public String m_help_TXT_EMP_CODE_sql="";
	public String m_help_TXT_EMP_CODE_sql_Header="System Administration - Employee";
	
	public String m_help_TXT_EMP_CODE_NAME_sql="";
	public String m_help_TXT_EMP_CODE_NAME_sql_Header="System Administration - Employee";
	
	public String m_help_TXT_DIV_sql="";
	public String m_help_TXT_DIV_sql_Header="System Administration - Division1";
	
	public String m_help_TXT_LOCATION_CODE_sql="";
	public String m_help_TXT_LOCATION_CODE_sql_Header="System Administration - Location ";
	
	public String m_help_TXT_LOCATION_CODE_NEW_sql="";
	public String m_help_TXT_LOCATION_CODE_NEW_sql_Header="System Administration - Location";
	
	public String m_help_TXT_SECTOR_CODE_sql="";
	public String m_help_TXT_SECTOR_CODE_sql_Header="System Administration - Business Sector";
	
	public String m_help_TXT_SECTOR_DESC_sql="";
	public String m_help_TXT_SECTOR_DESC_sql_Header="System Administration - Business Sector";
	
	public String m_help_TXT_BANK_CODE_sql="";
	public String m_help_TXT_BANK_CODE_sql_Header="System Administration - Bank";
	
	//addedbyPrabash on 17-05-2012-----
	public String m_help_TXT_VEHI_BLACKLIST_sql="";
	public String m_help_TXT_VEHI_BLACKLIST_sql_Header="System Administration - vehicle blacklist";
	
	public String m_help_TXT_VEHI_BLACKLIST1_sql="";
	public String m_help_TXT_VEHI_BLACKLIST1_sql_Header="System Administration - vehicle blacklist";
	
	
	public String m_help_TXT_VEHI_BLACKLIST2_sql="";
	public String m_help_TXT_VEHI_BLACKLIST2_sql_Header="System Administration - vehicle blacklist";
	
	//addedbyPrabash on 13-06-2012-----
	public String m_help_TXT_VEHI_BLACKLIST_DACT_sql="";
	public String m_help_TXT_VEHI_BLACKLIST_DACT_sql_Header="System Administration -  blacklisted vehicle DACT";
	
	public String m_help_TXT_VEHI_BLACKLIST_RACT_sql="";
	public String m_help_TXT_VEHI_BLACKLIST_RACT_sql_Header="System Administration -  blacklisted vehicle DACT";
	
	
	//---------------------------------
	
	
	public String m_help_TXT_BANK_NAME_sql="";
	public String m_help_TXT_BANK_NAME_sql_Header="System Administration - Bank";
	
	public String m_help_TXT_DIVISION_CODE_sql="";
	public String m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_DIVISION_DESC_sql="";
	public String m_help_TXT_DIVISION_DESC_sql_Header="System Administration - Division";
	
	public String m_help_TXT_DIVISION1_CODE_sql="";
	public String m_help_TXT_DIVISION1_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_CAPACITY_CODE_sql="";
	public String m_help_TXT_CAPACITY_CODE_sql_Header="System Administration - Engine Capacity";
	
	public String m_help_TXT_CAPACITY_DESC_sql="";
	public String m_help_TXT_CAPACITY_DESC_sql_Header="System Administration - Engine Capacity";
	
	public String m_help_TXT_USER_ID_sql="";
	public String m_help_TXT_USER_ID_sql_Header="System Administration - User";
	
	public String m_help_group_user_sql="";
	public String m_help_group_user_sql_Header="system Administration - Group ";
	
	public String m_help_TXT_USER_ID_NAME_sql="";
	public String m_help_TXT_USER_ID_NAME_sql_Header="System Administration - User";
	
	public String	m_help_TXT_TYPE_CODE_sql="";
	public String	m_help_TXT_TYPE_CODE_sql_Header="System Administration - Charges";
	
	
	public String	m_help_TXT_ITEM_CAT_CODE_sql="";
	public String	m_help_TXT_ITEM_CAT_CODE_sql_Header="System Administration - Item Category";
	
	public String	m_help_TXT_BASE_CODE_NEW_sql="";//ADDED BY LALANKA ON 28-01-2010
	public String	m_help_TXT_BASE_CODE_NEW_sql_Header="System Administration - Item Category";
	
	public String m_help_TXT_ITEM_CAT_Code_desc_sql="";
	public String m_help_TXT_ITEM_CAT_Code_desc_sql_Header="System Administration - Item Category";
	
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_DESC_sql_Header="System Administration - Item Sub Category";
	
	public String	m_help_TXT_ITEM_SUB_CAT_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_sql_Header="System Administration - Item Sub Category";
	
	// added by udara 15-05-2018
	public String	m_help_TXT_ITEM_SUB_CAT_VC_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_VC_sql_Header="System Administration - Item Sub Category";
	
	// added by udara 04-05-2018
	public String	m_help_TXT_VEHICLE_CATEGORY_sql="";
	public String	m_help_TXT_VEHICLE_CATEGORY_sql_Header="System Administration - Item Sub Category";
	
	public String	m_help_TXT_FILED_CODE_sql="";
	public String	m_help_TXT_FILED_CODE_sql_Header="System Administration - Field ";
	
	public String m_help_TXT_FILED_DESC_sql="";
	public String m_help_TXT_FILED_DESC_sql_Header="System Administration - Field ";
	
	public String	m_help_TXT_FILED_CODE_sql_applicable="";
	public String	m_help_TXT_FILED_CODE_sql_applicable_Header="System Administration - Applicable Filed Code";//Added By Nuwan De Silva(Modified)
	
	public String	m_help_TXT_SUB_CODE_sql="";
	public String	m_help_TXT_SUB_CODE_sql_Header="System Administration - Business Sub Sector";
	
	public String	m_help_TXT_SUB_CODE_sql_new="";
	public String	m_help_TXT_SUB_CODE_sql_new_Header="System Administration - Business Sub Sector";
	
	public String m_help_TXT_SUB_DESC_sql="";
	public String m_help_TXT_SUB_DESC_sql_Header="System Administration - Business Sub Sector";
	
	public String m_help_TXT_ENTITY_CODE_sql="";
	public String m_help_TXT_ENTITY_CODE_sql_Header="System Administration - Legal Entity";
	
	public String m_help_TXT_ENTITY_DESC_sql="";
	public String m_help_TXT_ENTITY_DESC_sql_Header="System Administration - Legal Entity";
	
	public String m_help_TXT_PHONE_AREA_CODE_sql="";
	public String m_help_TXT_PHONE_AREA_CODE_sql_Header="System Administration - Phone Area Codes ";
	
	public String m_help_TXT_PRODUCT_CODE_sql="";
	public String m_help_TXT_PRODUCT_CODE_sql_Header="System Administration - Product Codes";
	
	public String m_help_TXT_SUB_TYPE_CODE_sql="";
	public String m_help_TXT_SUB_TYPE_CODE_sql_Header="System Administration - Sub Type Code";
	
	public String m_help_TXT_FUAL_TYPE_CODE_sql="";
	public String m_help_TXT_FUAL_TYPE_CODE_sql_Header="System Administration - Fuel Type Code";
	
	public String m_help_TXT_ITEM_SUB_CAT_sql_1="";
	public String m_help_TXT_ITEM_SUB_CAT_sql_1_Header="System Administration - Item Sub Category Code";
	
	public String m_help_TXT_RMV_AGENT_CODE_sql="";
	public String m_help_TXT_RMV_AGENT_CODE_sql_Header="System Administration - RMV Agents Code";
	
	public String m_help_TXT_RMV_AGENT_CODE_NAME_sql="";
	public String m_help_TXT_RMV_AGENT_CODE_NAME_sql_Header="System Administration - RMV Agents Code";
	
	public String m_help_TXT_CODE_sql="";
	public String m_help_TXT_CODE_sql_Header="System Administration - Condition of Asset";
	
	public String m_help_TXT_CODE_DESC_sql="";
	public String m_help_TXT_CODE_DESC_sql_Header="System Administration - Condition of Asset";
	
	
	public String m_help_TXT_SEIZER_CODE_sql="";
	public String m_help_TXT_SEIZER_CODE_sql_Header="System Administration - Seizer Code";
	
	public String m_help_TXT_SEIZER_CODE_sql_New=""; //Added by Sandun on 30-07-2008
	public String m_help_TXT_SEIZER_CODE_sql_New_Header="System Administration - Seizer Code";
	
	public String m_help_TXT_SEIZER_DESC_sql="";
	public String m_help_TXT_SEIZER_DESC_sql_Header="System Administration - Seizer Code";
	
	public String m_help_TXT_YARD_CODE_sql="";
	public String m_help_TXT_YARD_CODE_sql_Header="System Administration - Yard Code";
	
	public String m_help_TXT_CODE_sql_fuel_type="";
	public String m_help_TXT_CODE_sql_fuel_type_Header="System Administration - Fuel type";
	
	public String m_help_TXT_CODE_sql_fuel_type_desc="";
	public String m_help_TXT_CODE_sql_fuel_type_desc_Header="System Administration - Fuel type";
	
	public String m_help_TXT_MAKE_CODE_sql="";
	public String m_help_TXT_MAKE_CODE_sql_Header="System Administration - Make Creation";
	
	public String m_help_TXT_MAKE_DESC_sql="";
	public String m_help_TXT_MAKE_DESC_sql_Header="System Administration - Make Creation";
	
	
	public String m_help_TXT_TEAM_ID_sql="";
	public String m_help_TXT_TEAM_ID_sql_Header="System Administration - Team";
	
	public String m_help_TXT_TEAM_ID_DESC_sql="";
	public String m_help_TXT_TEAM_ID_DESC_sql_Header="System Administration - Team";
	
	public String m_help_TXT_SUB_DIVISION_CODE_sql="";
	public String m_help_TXT_SUB_DIVISION_CODE_sql_Header="System Administration - Sub Division";
	
	//Added by Disnaka Jayasuriya on 2009-10-16 for filter sub division codes for to relevant division code 
	public String m_help_TXT_SUB_DIVISION_CODE1_sql="";
	public String m_help_TXT_SUB_DIVISION_CODE1_sql_Header="System Administration - Sub Division";
	//end
	
	public String m_help_TXT_SUB_DIVISION_DESC_sql="";
	public String m_help_TXT_SUB_DIVISION_DESC_sql_Header="System Administration - Sub Division";
	
	
	public String m_help_TXT_DURATION_sql="";
	public String m_help_TXT_DURATION_sql_Header="System Administration - Repayment Interval";
	
	public String m_help_TXT_DURATION_DESC_sql="";
	public String m_help_TXT_DURATION_DESC_sql_Header="System Administration - Repayment Interval";
	
	public String m_help_TXT_RCODE_sql="";
	public String m_help_TXT_RCODE_sql_Header="System Administration - Revenue License";
	
	public String m_help_TXT_MODEL_CODE_sql="";
	public String m_help_TXT_MODEL_CODE_sql_Header="System Administration - Model Creation";
	
	public String m_help_TXT_MODEL_DESC_sql="";
	public String m_help_TXT_MODEL_DESC_sql_Header="System Administration - Model Creation";
	
	
	public String m_help_TXT_VENDOR_CODE_sql="";
	public String m_help_TXT_VENDOR_CODE_sql_Header="System Administration - Vendor Creation";
	
	public String m_help_TXT_CAT_TYPE_CODE_sql="";
	public String m_help_TXT_CAT_TYPE_CODE_sql_Header="System Administration - Customer Category";
	
	public String m_help_TXT_CAT_TYPE_DESC_sql="";
	public String m_help_TXT_CAT_TYPE_DESC_sql_Header="System Administration - Customer Category";
	
	public String m_help_TXT_INITIATION_CODE_sql="";
	public String m_help_TXT_INITIATION_CODE_sql_Header="System Administration - Initiation Type";
	
	public String m_help_TXT_INITIATION_DESC_sql="";
	public String m_help_TXT_INITIATION_DESC_sql_Header="System Administration - Initiation Type";
	
	
	public String m_help_TXT_CATEGORY_sql="";
	public String m_help_TXT_CATEGORY_sql_Header="System Administration - Item Category Code";
	
	public String m_help_TXT_CODE_sql_1="";
	public String m_help_TXT_CODE_sql_1_Header="System Administration - Documents Required";
	
	public String m_help_TXT_CODE_DESC_sql_1="";
	public String m_help_TXT_CODE_DESC_sql_1_Header="System Administration - Documents Required";
	
	public String m_help_TXT_CLIENT_CODE_sql="";
	public String m_help_TXT_CLIENT_CODE_sql_Header="System Administration -Backlisted Clients ";
	
	public String m_help_TXT_CLIENT_CODE_sql1="";
	public String m_help_TXT_CLIENT_CODE_sql1_Header="System Administration - Client Creation ";
	
	
	public String m_help_TXT_CLIENT_CODE_COR_sql1="";
	public String m_help_TXT_CLIENT_CODE_COR_sql1_Header="System Administration - Client Creation ";
	
	public String m_help_TXT_CLIENT_CODE_COR_TMP_sql1="";
	public String m_help_TXT_CLIENT_CODE_COR_TMP_sql1_Header="System Administration - Client Creation ";
	
	public String m_help_TXT_CLIENT_CODE_IND_sql1="";
	public String m_help_TXT_CLIENT_CODE_IND_sql1_Header="System Administration - Client Creation ";
	
	public String m_help_TXT_CLIENT_CODE_IND_TMP_sql1="";
	public String m_help_TXT_CLIENT_CODE_IND_TMP_sql1_Header="System Administration - Client Creation ";
	
	
	public String m_help_TXT_ENTITY_TYPE_sql="";
	public String m_help_TXT_ENTITY_TYPE_sql_Header="System Administration - Entity Type";
	
	public String m_help_TXT_STAGE_sql="";
	public String m_help_TXT_STAGE_sql_Header="System Administration - Stage";
	
	public String m_help_TXT_CODE_sql_2="";
	public String m_help_TXT_CODE_sql_2_Header="System Administration - Document Applicable";
	
	public String m_help_TXT_VENDOR_BCODE_sql="";
	public String m_help_TXT_VENDOR_BCODE_sql_Header="System Administration - Vender Backlisting";
	
	public String m_help_TXT_CATEGORY_CODE_sql="";
	public String m_help_TXT_CATEGORY_CODE_sql_Header="System Administration - Follow up Action Category";
	
	public String m_help_TXT_CATEGORY_CODE_desc_sql="";
	public String m_help_TXT_CATEGORY_CODE_desc_sql_Header="System Administration - Follow up Action Category";
	
	
	public String m_help_TXT_RATE_sql="";
	public String m_help_TXT_RATE_sql_Header="System Administration - Discount Rate";
	
	public String m_help_TXT_RATE_sql_desc="";
	public String m_help_TXT_RATE_sql_desc_Header="System Administration - Discount Rate";
	
	
	public String m_help_TXT_TEAM_ID_sql_new="";
	public String m_help_TXT_TEAM_ID_sql_new_Header="System Administration - Assign Team Members";
	
	public String m_help_txt_sub_team_id_sql_new ="";
	public String m_help_txt_sub_team_id_sql_new_Header ="System Administration - Team Help";
	
	public String m_help_TXT_BASE_CODE_sql="";
	public String m_help_TXT_BASE_CODE_sql_Header="System Administration - Variable Interest Base";
	
	public String m_help_TXT_BASE_DESC_sql="";
	public String m_help_TXT_BASE_DESC_sql_Header="System Administration - Variable Interest Base";
	
	
	public String m_help_TXT_MAIN_CODE_sql="";
	public String m_help_TXT_MAIN_CODE_sql_Header="System Administration - Maintanance Rate";
	
	public String m_help_TXT_SUB_MODEL_CODE_sql="";
	public String m_help_TXT_SUB_MODEL_CODE_sql_Header="System Administration - Sub Model Creation";
	
	public String m_help_TXT_SUB_MODEL_CODE_sql_new="";
	public String m_help_TXT_SUB_MODEL_CODE_sql_new_Header="System Administration - Sub Model Creation";
	
	public String m_help_TXT_CHARGE_SUB_CODE_sql="";
	public String m_help_TXT_CHARGE_SUB_CODE_sql_Header="System Administration - Sub Charge";
	
	public String m_help_TXT_MILEAGE_CODE_sql="";
	public String m_help_TXT_MILEAGE_CODE_sql_Header="System Administration - Milage";
	
	public String m_help_TXT_MILEAGE_CODE_sql_new="";
	public String m_help_TXT_MILEAGE_CODE_sql_new_Header="System Administration - Milage";
	
	
	public String m_help_TXT_HOLIDAY_DATE_sql="";
	public String m_help_TXT_HOLIDAY_DATE_sql_Header="System Administration - Holiday";
	
	public String m_help_TXT_HOLIDAY_DESC_sql="";
	public String m_help_TXT_HOLIDAY_DESC_sql_Header="System Administration - Holiday";
	
	public String	m_help_TXT_CLIENT_CODE_sql_1="";
	public String	m_help_TXT_CLIENT_CODE_sql_1_Header="System Administration - Type Code";
	
	public String	m_help_TXT_CLIENT_TYPE_sql="";
	public String	m_help_TXT_CLIENT_TYPE_sql_Header="System Administration - Cat Type Code";
	
	public String m_help_TXT_VEN_CODE_sql="";
	public String m_help_TXT_VEN_CODE_sql_Header="System Administration - Vender Creation";
	
	public String m_help_TXT_BRANCH_sql="";
	public String m_help_TXT_BRANCH_sql_Header="System Administration - Branch Contact Details";
	
	public String m_help_TXT_VALUATION_NO_sql="";
	public String m_help_TXT_VALUATION_NO_sql_Header="System Administration - Valuer Details";
	
	public String m_help_TXT_VALUATION_NO1_sql="";
	public String m_help_TXT_VALUATION_NO1_sql_Header="Marketing - Valuation Help";
	
	
	public String m_help_TXT_VALUATION_DET_sql="";
	public String m_help_TXT_VALUATION_DET_sql_Header="System Administration - Valuer Details";
	
	public String m_help_TXT_MODEL_CODE_sql1="";
	public String m_help_TXT_MODEL_CODE_sql1_Header="System Administration - Model Creation";
	
	public String m_help_TXT_SUB_MODEL_CODE_sql1="";
	public String m_help_TXT_SUB_MODEL_CODE_sql1_Header="System Administration -Sub Model Creation";
	
	public String m_help_TXT_SUB_MODEL_CODE_sql2="";
	public String m_help_TXT_SUB_MODEL_CODE_sql2_Header="System Administration -Sub Model Creation";
	
	public String m_help_TXT_ACCOUNT_CODE_sql="";
	public String m_help_TXT_ACCOUNT_CODE_sql_Header="System Administration - Account Codes ";
	
	public String m_help_TXT_ACCOUNT_DESC_sql="";
	public String m_help_TXT_ACCOUNT_DESC_sql_Header="System Administration - Account Codes";
	
	public String m_help_TXT_SUB_MODEL_sql="";
	public String m_help_TXT_SUB_MODEL_sql_Header="System Administration -Sub Model Creation";
	
	public String m_help_TXT_ASSET_ID_sql1= "";
	public String m_help_TXT_ASSET_ID_sql1_Header= "Application Processing - Asset ID ";
	
	public String m_help_TXT_APPLICATION_NO_sql="";
	public String m_help_TXT_APPLICATION_NO_sql_Header="System Administration -Applicable Client Documents";
	
	public String m_view_sql="";
	public String m_view_sql_Header="Valution Details";
	
	public String m_help_TXT_ID_NO_sql="";
	public String m_help_TXT_ID_NO_sql_Header="Follow Up Details";
	
	//Added by Dineth on 2008-10-10	
	public String m_help_TXT_ASSIGNED_BY_sql="";
	public String m_help_TXT_ASSIGNED_BY_sql_Header="Follow Up Details";
	
	public String m_help_TXT_ASSIGNED_TO_sql="";
	public String m_help_TXT_ASSIGNED_TO_sql_Header="Follow Up Details";
	
	public String m_help_TXT_DIVISION_CODE_sql1="";
	public String m_help_TXT_DIVISION_CODE_sql1_Header="Follow Up Details";
	
	public String m_help_TXT_SUB_DIVISION_sql1="";
	public String m_help_TXT_SUB_DIVISION_sql1_Header="Follow Up Details";
	//End by Dineth on 2008-10-10
	public String m_help_TXT_PRICING_NO_sql="";
	public String m_help_TXT_PRICING_NO_sql_Header="Pricing Details";
	
	public String m_help_TXT_ACTION_SET_FOR_sql="";
	public String m_help_TXT_ACTION_SET_FOR_sql_Header="Action Set For Details";
	
	public String m_help_TXT_SCREEN_NAME_sql_1="";
	public String m_help_TXT_SCREEN_NAME_sql_1_Header="Screen Name Details";	
	
	public String m_help_TXT_COUNTRY_DESC_sql="";
	public String m_help_TXT_COUNTRY_DESC_sql_Header="Country Details";	
	
	public String m_help_TXT_INQUARY_NO        ="";
	public String m_help_TXT_INQUARY_NO_Header ="Inquiry Help ";
	
	public String	m_help_TXT_CONDITION_CODE_sql="";
	public String m_help_TXT_CONDITION_CODE_sql_Header ="System Administration - Condition Code Help";
	
	public String m_help_TXT_NARRATIONS_CODE_sql="";
	public String m_help_TXT_NARRATIONS_CODE_sql_Header="CR/DB Narration Code";
	
	public String m_help_TXT_CHQ_NARRATIONS_CODE_sql="";
	public String m_help_TXT_CHQ_NARRATIONS_CODE_sql_Header="Cheque Return Narrations";
	
	public String m_help_loan_facilities_sql ="";
	public String m_help_loan_facilities_sql_Header =" Loan Facilit Help";
	
	public String m_help_loan_facilities_assign_sql ="";
	public String m_help_loan_facilities_assign_sql_Header ="Loan Facility Help";
	
	public String m_help_loan_facilities_assign_edit_sql="";
	public String m_help_loan_facilities_assign_edit_sql_Header="Loan Facility Help";
	
	public String m_help_TXT_BRANCH_CODE_sql_new= "";
	public String m_help_TXT_BRANCH_CODE_sql_new_Header= "System Administration - Bank Branch";
	
	public String m_help_TXT_ACCOUNT_CODE_sql_new="";
	public String m_help_TXT_ACCOUNT_CODE_sql_new_Header="System Administration - Account Codes ";
	
	
	public String m_help_TXT_BROKER_DETAILS_sql="";
	public String m_help_TXT_BROKER_DETAILS_sql_Header="System Administration - Broker Codes";
	
	//public String m_help_TXT_TERMINATION_NO_sql="";
	//public String m_help_TXT_TERMINATION_NO_sql_Header="System Administration - Termination No";
	
	public String m_help_TXT_CLIENT_NAME_sql="";
	public String m_help_TXT_CLIENT_NAME_sql_Header="System Administration - Client Name";
	
	public String finance_help_loan_facility ="";
	public String finance_help_loan_facility_Header ="Finance Help";
	
	
	public String m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql="";
	public String m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql_Header="System Administration - Item Sub Code";
	
	public String m_help_TXT_ITEM_SUB_CAT_sql_NEW="";
	public String m_help_TXT_ITEM_SUB_CAT_sql_NEW_Header="System Administration - Item Sub Code";
	
	//====================================== Add By Indika on 28/08/08 ================================================
	public String m_help_FINANCE_NO_termination = "";
	public String m_help_FINANCE_NO_termination_Header = "System Administration - Employee Name";
	
	public String m_help_TXT_FINANCENO_sql ="";
	public String m_help_TXT_FINANCENO_sql_Header="System Administration - Finance No";
	
	public String m_help_TXT_FINANCE_NO_sql="";
	public String m_help_TXT_FINANCE_NO_sql_Header="System Administration - Finance No";
	
	public String m_help_TXT_EMPLOYEE_sql="";
	public String m_help_TXT_EMPLOYEE_sql_Header="System Administration - Employee Name";
	//======================================= End By Indika on 28/08/08 ==============================================
	//-------------------------------------------------------------------------
	
	//===================================== Added by Dineth on 2008-09-17 for Contract Level Account Entries
	
	public String m_help_TXT_FINANCENO_sql1 ="";
	public String m_help_TXT_FINANCENO_sql1_Header="System Administration - Finance No";
	
	public String m_help_TXT_FINANCENO_sql2 ="";
	public String m_help_TXT_FINANCENO_sql2_Header="System Administration - Finance No";
	
	//===================================== End by Dineth on 2008-09-17==========
	public String m_help_TXT_APP_STATUS_sql="";
	public String m_help_TXT_APP_STATUS_sql_Header="System Administration - Application Status";//Added By Sandun 28-10-2008
	
	public String m_help_deact_TXT_APP_STATUS_sql="";
	public String m_help_deact_TXT_APP_STATUS_sql_Header="System Administration - Deactivate Application Status";//Added By Sandun 28-10-2008
	
	public String m_help_react_TXT_APP_STATUS_sql="";
	public String m_help_react_TXT_APP_STATUS_sql_Header="System Administration - Reactivate Application Status";//Added By Sandun 28-10-2008
	
	public String m_help_Fin_No_Sql="";
	public String m_help_Fin_No_Sql_Header="Managment Information - Finance No Help";//Added By Sandun 27-11-2008
	
	public String m_help_Assign_Mkt_Col_Collection_Officer_New_sql="";
	public String m_help_Assign_Mkt_Col_Collection_Officer_New_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
	
	public String m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql="";
	public String m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql_Header="Collection Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
	
	public String m_help_Assign_Mkt_Col_Marketing_Officer_New_sql="";
	public String m_help_Assign_Mkt_Col_Marketing_Officer_New_sql_Header="Marketing Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
	
	public String m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql="";
	public String m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql_Header="Marketing Officer Help"; //Added By Samitha Kulatilaka On 2012-01-26
	
	// added by udara on 09-05-2013
	public String ClientSql_new ="";
	public String ClientSql_new_Header ="Application process - Client Help";
	
	// added by udara 06-03-2014
	public String m_help_insurance_company_code ="";
	public String m_help_insurance_company_code_Header ="Application process - Insurance Help";
	
	// added by udara 11-07-2018
	public String m_help_TXT_DOC_ID_sql ="";
	public String m_help_TXT_DOC_ID_sql_Header ="Application process - Document ID Help";
	
	public String m_help_TXT_LETTER_ID_sql="";
	public String m_help_TXT_LETTER_ID_sql_Header="System Administration - Recovery Letter Types";
	
	public String m_help_TXT_TRAN_CODE_sql_new= "";
	public String m_help_TXT_TRAN_CODE_sql_new_Header= "System Administration - Transaction";
	
	// public String m_help_TXT_EMP_CODE_2_sql="";
	// public String m_help_TXT_EMP_CODE_2_sql_Header="Collection Officer Help";//Added By Samitha Kulatilaka On 2012-01-26
	
	// added by udara 11-07-2018
	public String m_help_TXT_DOC_ID_sql_new="";
	public String m_help_TXT_DOC_ID_sql_new_Header="System Administration - Recovery Letter Types";
	
	// added by udara 10-07-2019 - to fix file corruption
	public String m_help_TXT_MAIN_TRAN_CODE_sql="";
	public String m_help_TXT_MAIN_TRAN_CODE_sql_Header="System Administration - Transaction";
	
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
		
		
		/*		Purpose  : Select Client Name,Termination No,Termination Date,Client Code
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
			" ((APPLICATION_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  (CLIENT_CODE)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR"+
			"  (FINANCE_NO)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ) "+
			" ORDER BY ENT_DATE  DESC))P "+    
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		/*----------------------------------------------------------------------------------------------------------------------------------*/	
		m_help_TXT_SCORE_CODE_sql=
			" SELECT L.NO ,L.SCORE_CODE,L.DESCRIPTION,L.DISPLAY_POSITION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_CODE,P.DESCRIPTION,P.DISPLAY_POSITION "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_CODE, "+ 
			" DESCRIPTION, "+ 
			" DISPLAY_POSITION "+ 
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+ 
			" WHERE (SCORE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_client_group=
			" SELECT L.NO ,L.GROUP_ID,L.GROUP_MASTER_ID,L.GROUP_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_ID,P.GROUP_MASTER_ID,P.GROUP_NAME "+
			" FROM( "+ 
			" SELECT "+
			" GROUP_ID, "+
			" GROUP_MASTER_ID, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(GROUP_MASTER_ID) GROUP_NAME, "+
			" CLIENT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_GROUP "+
			" WHERE CLIENT_TYPE='M' "+
			"  )P)L  "+ 
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//added by madhawa 2012-02-17
		m_help_VendorSql_sql=  " SELECT NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
			" FROM  ( SELECT ROWNUM NO,VENDOR_CODE,NAME,CATEGORY,TYPE "+
			" FROM  ( SELECT A.VENDOR_CODE, A.NAME, NVL(A.CATEGORY,'-') CATEGORY,NVL(A.TYPE,'-') TYPE "+
			" FROM  "+m_schema_name+".AF_CO_MAS_VENDORS A "+
			" WHERE ACTIVE_STATUS='"+m_vector.elementAt(1)+"' AND "+
			"       (VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       UPPER(NAME)  	 LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" ORDER BY VENDOR_CODE)) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		m_help_VendorsPECIFIC_APPLICATION_sql=
			" SELECT NO,APPLICATION_NO,FINANCE_NO,REG_NO  Vehicle_No"+
			" FROM  ( SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,REG_NO "+
			" FROM  ( "+
			" SELECT C.APPLICATION_NO,C.FINANCE_NO, "+ 
			" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (C.FINANCE_NO),'-')REG_NO "+ //Added by Prabash on 22-03-2012
			" FROM "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,"+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
			" WHERE  "+
			" B.REF_NO=D.INVOICE_NO "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO "+
			" AND B.BAL_TO_BE_PAID >0  "+
			" AND D.ACTIVE_STATUS <> 'C' "+  
			" AND C.APPLICATION_STATUS='ACTIVATED' "+
			" AND ((C.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+	
			" OR UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (C.FINANCE_NO)) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+ //Added by Prabash on 22-03-2012
			" AND UPPER(B.RECEIVER) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" GROUP BY C.APPLICATION_NO,C.FINANCE_NO,B.VALUE_DATE,B.RECEIVER "+
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		m_help_ReceiptSql_sql= 
			" SELECT NO,REC_NO "+
			" FROM  ( SELECT ROWNUM NO,REC_NO "+
			" FROM  ( "+
			" SELECT REC_NO "+
			" FROM "+
			" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+ 
			" WHERE "+
			" (A.REC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND A.STATUS=('E') "+ 
			" AND A.SETTLE_MODE IN('CHEQUE','CASH') "+
			" )) P "+		
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";	
		
		
		m_help_txt_sub_team_sql=
			
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
			" WHERE ( (SUB_TEAM_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(SUB_TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*	m_help_TXT_TERMINATION_NO_sql=
            " SELECT L.NO,L.TERMINATION_NO,L.VEHICLE_NO "+
            " FROM "+
            " (SELECT ROWNUM NO,P.TERMINATION_NO,P.VEHICLE_NO "+
            " FROM "+
            " (SELECT "+
            " TERMINATION_NO, "+
            " VEHICLE_NO "+
            " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES "+
            " WHERE (TERMINATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(VEHICLE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%')))P)L "+
            " WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		
		//======================= Add By Indika get help for LAKDL_AF_MAS_Deletion_Letter on 28/08/08 ==========================================================		
		
		m_help_TXT_FINANCE_NO_sql=
			" SELECT L.NO,L.FINANCE_NO,L.APPLICATION_NO,NVL(L.VEHICLE_NO,'-') VEHICLE_NO,L.CLIENT_CODE,L.CLIENT_NAME,L.CLIENT_ADDRESS "+    
			" FROM "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.VEHICLE_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.CLIENT_ADDRESS "+  
			" FROM "+ 
			" (SELECT "+ 
			" C.FINANCE_NO,  "+
			" B.APPLICATION_NO, "+
			" NVL(A.VEHICLE_NO,'-') VEHICLE_NO, "+
			" B.CLIENT_CODE, "+
			" "+m_schema_name+".af_co_get_client_name(B.CLIENT_CODE) CLIENT_NAME,  "+
			" ("+m_schema_name+".af_co_get_client_ADD1(B.CLIENT_CODE)||' '||"+m_schema_name+".af_co_get_client_ADD1(B.CLIENT_CODE))CLIENT_ADDRESS  "+
			" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION_VEHICLES A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CR_PRO_TERMINATION C "+
			" WHERE (B.APPLICATION_STATUS = 'ACTIVATED' AND "+
			" (C.FINANCE_NO = B.FINANCE_NO) AND "+
			" (C.TERMINATION_NO = A.TERMINATION_NO)  AND "+
			" C.TERMINATION_TYPE IN ('ERL_TER','NOR_TER') AND "+
			" (C.FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')OR  "+
			" UPPER(A.VEHICLE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))))P)L  "+
			" WHERE L.NO>= "+Start_Val+"  AND L.NO<= "+End_Val+" "; 
		
		m_help_TXT_EMPLOYEE_sql=
			" SELECT L.NO,L.EMPLOYEE_NAME,L.ID_NO,L.DESIGNATION,L.LOCATION_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,EMPLOYEE_NAME,ID_NO,DESIGNATION,LOCATION_CODE "+
			" FROM "+
			" (SELECT EMP_CODE, "+
			" TITLE ||' ' || FIRST_NAME ||' '|| LAST_NAME EMPLOYEE_NAME, "+
			" ID_NO, "+
			" "+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE) DESIGNATION, "+
			" LOCATION_CODE "+
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			" WHERE ACTIVE_STATUS='Y' AND (UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (ID_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ))L "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//=========================================== End By Indika on 28/08/08 ========================================================
		
		m_help_TXT_CLIENT_NAME_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.NIC_NO,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,CITY_CODE "+
			" FROM "+
			" (SELECT "+
			" CLIENT_CODE, "+
			" FULL_NAME, "+
			" NIC_NO, "+
			" ADDRESS1, "+
			" ADDRESS2, "+
			" CITY_CODE, "+
			" FIRST_NAME, "+
			" SURNAME, "+
			" OTHER_NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE ACTIVE_STATUS='Y' AND (UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(SURNAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(OTHER_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ))L "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_FINANCENO_sql=
			" SELECT L.NO,L.FINANCE_NO,L.FULL_NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,FULL_NAME,ADDRESS1,ADDRESS2,CITY_CODE "+
			" FROM "+
			" (SELECT A.FINANCE_NO,B.FULL_NAME,B.ADDRESS1,B.ADDRESS2,B.CITY_CODE,B.NIC_NO,B.FIRST_NAME,B.SURNAME,B.OTHER_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE FINANCE_NO IS NOT NULL AND A.CLIENT_CODE=B.CLIENT_CODE "+
			" AND ( UPPER(B.SURNAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.OTHER_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (B.NIC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) ))L "+ 
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//==========================Added by Dineth on 2008-09-17
		m_help_TXT_FINANCENO_sql1=
			" SELECT L.NO,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.FINANCE_NO IS NOT NULL "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+		
			" AND ((A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')))L "+ 
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_FINANCENO_sql2=
			" SELECT L.NO,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.FINANCE_NO IS NOT NULL "+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+			
			" AND ((A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" (A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))))L "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//==========================End by Dineth on 2008-09-17
		
		
		
		finance_help_loan_facility =   	"SELECT P.NO, P.FINANCE_NO,P.CLIENT_CODE Client,P.FULL_NAME Name, P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status "+ //P.REG_NO
			"FROM "+
			"(SELECT ROWNUM NO, FINANCE_NO,CLIENT_CODE,FULL_NAME,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS "+ //REG_NO
			"FROM "+
			"(SELECT DISTINCT FINANCE_NO,NVL(A.CLIENT_CODE,'-') CLIENT_CODE ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME  "+ //added by nuwan de silva 25-07-07 //, c.REG_NO REG_NO 
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE AND "+
			"		     B.APPLICATION_NO=C.APPLICATION_NO AND  "+
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
			"        AND  FINANCE_NO NOT IN (SELECT finance_no  FROM "+m_schema_name+".af_co_mas_loan_facili_assign  ) "+
			"        AND  B.APPLICATION_STATUS='ACTIVATED' "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		
		
		
		
		m_help_TXT_SCORE_DESC_sql=
			" SELECT L.NO ,L.SCORE_CODE,L.DESCRIPTION,L.DISPLAY_POSITION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_CODE,P.DESCRIPTION,P.DISPLAY_POSITION "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_CODE, "+ 
			" DESCRIPTION, "+ 
			" DISPLAY_POSITION "+ 
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+ 
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SCORE_SUB_CODE_sql=
			
			" SELECT L.NO ,L.SCORE_SUB_CODE,L.SCORE_CODE,L.DESCRIPTION,L.DISPALY_POSITION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_SUB_CODE,P.SCORE_CODE,P.DESCRIPTION,P.DISPALY_POSITION "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_SUB_CODE, "+ 
			" SCORE_CODE, "+
			" DESCRIPTION, "+ 
			" DISPALY_POSITION "+ 
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+ 
			" WHERE (SCORE_SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  SCORE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SCORE_SUB_CODE_DESC_sql=	
			
			" SELECT L.NO ,L.SCORE_SUB_CODE,L.SCORE_CODE,L.DESCRIPTION,L.DISPALY_POSITION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_SUB_CODE,P.SCORE_CODE,P.DESCRIPTION,P.DISPALY_POSITION "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_SUB_CODE, "+ 
			" SCORE_CODE, "+
			" DESCRIPTION, "+ 
			" DISPALY_POSITION "+ 
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+ 
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
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
		
		
		m_help_TXT_SCORE_MODEL_CODE_DESC_sql=	
			" SELECT L.NO ,L.SCORE_MODEL_CODE,L.DESCRIPTION,L.TOTAL_SCORE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_MODEL_CODE,P.DESCRIPTION,P.TOTAL_SCORE "+
			" FROM( "+ 
			" SELECT SCORE_MODEL_CODE, "+
			" DESCRIPTION, "+
			" TOTAL_SCORE "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
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
			" WHERE APP_STATUS='ENTER' AND APPLICATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//-------------------- ID : 1.2 Province Creation Process-----------------------------//
		//--------------------Province Code Help -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------19-07-2006---------------------------------------------------------//
		m_help_TXT_PROVINCE_CODE_sql=
			" SELECT L.NO ,L.PROVINCE_CODE,L.PROVINCE_DESC,L.COUNTRY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PROVINCE_CODE,P.PROVINCE_DESC,P.COUNTRY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" PROVINCE_CODE, "+
			" PROVINCE_DESC, "+
			" COUNTRY_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_PROVINCE "+
			" WHERE (PROVINCE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(PROVINCE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_PROVINCE_DESC_sql=
			" SELECT L.NO ,L.PROVINCE_CODE,L.PROVINCE_DESC,L.COUNTRY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PROVINCE_CODE,P.PROVINCE_DESC,P.COUNTRY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" PROVINCE_CODE, "+
			" PROVINCE_DESC, "+
			" COUNTRY_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_PROVINCE "+
			" WHERE UPPER(PROVINCE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//---------------------ID      :1.4 City Creation Process---------------------------------//
		//----------------------Purpose :City Code Help -------------------------------------------------//
		//----------------------Name    :Delanjali------------------------------------------------------//
		//----------------------Date    :19-07-2006----------------------------------------------------//
		
		
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
			//" ORDER BY CITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_CITY_DESC_sql=
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
			" WHERE (CITY_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//" ORDER BY CITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.1 Country Creation Process-----------------------------------------
            --------------------Purpose    :Country Code Help ----------------------------------------------
          ------------------- Added By   :Nuwan De Silva------------------------------------------------------
          --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
		m_help_TXT_COUNTRY_CODE_sql=
			
			" SELECT L.NO ,L.COUNTRY_CODE,L.COUNTRY_DESC,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COUNTRY_CODE,P.COUNTRY_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" COUNTRY_CODE,"+
			" COUNTRY_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_COUNTRY "+
			" WHERE (COUNTRY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR COUNTRY_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql=
			
			" SELECT L.NO ,L.ITEM_SUB_CAT_CODE,L.DESCRIPTION,L.APP_DATE,L.VAT_RATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT_CODE,P.DESCRIPTION,P.APP_DATE,P.VAT_RATE "+
			" FROM( "+ 
			" SELECT "+
			" A.ITEM_SUB_CAT_CODE ITEM_SUB_CAT_CODE ,"+
			" B.DESCRIPTION DESCRIPTION, "+
			" TO_CHAR(A.APP_DATE,'DD-MM-YYYY') APP_DATE , "+
			" A.VAT_RATE VAT_RATE "+
			" FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL A,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B "+
			" WHERE A.ITEM_SUB_CAT_CODE=B.ITEM_SUB_CAT AND (A.ITEM_SUB_CAT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR B.DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ITEM_SUB_CAT_sql_NEW=
			
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.ITEM_CAT_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.ITEM_CAT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT ,"+
			" ITEM_CAT_CODE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			" WHERE (ITEM_SUB_CAT LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" AND ITEM_SUB_CAT NOT IN (SELECT NVL(ITEM_SUB_CAT_CODE,'-') FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL ) "+
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// added by udara 04-05-2018
		m_help_TXT_VEHICLE_CATEGORY_sql=
			
			" SELECT L.NO ,L.VEHICLE_CAT,L.VEHICLE_CAT_DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VEHICLE_CAT,P.VEHICLE_CAT_DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" VEHICLE_CAT ,"+
			" VEHICLE_CAT_DESCRIPTION "+
			" FROM "+m_schema_name+".AF_TBL_ADMIN_VEHICLE_CAT_MAS "+
			" WHERE (VEHICLE_CAT LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(VEHICLE_CAT_DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY VEHICLE_CAT_DESCRIPTION ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		// end by udara 04-05-2018
		
		
		m_help_TXT_COUNTRY_DESC_sql=
			
			" SELECT L.NO ,L.COUNTRY_CODE,L.COUNTRY_DESC,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COUNTRY_CODE,P.COUNTRY_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" COUNTRY_CODE,"+
			" COUNTRY_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_COUNTRY "+
			" WHERE (COUNTRY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR COUNTRY_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//--------------------District Code Help -------------------------------------------------//
		//-------------------------Chandana------------------------------------------------------//
		//------------------------19-07-2006---------------------------------------------------------//
		
		m_help_TXT_DISTRICT_CODE_sql=
			
			" SELECT L.NO ,L.DISTRICT_CODE,L.DISTRICT_DESC,L.PROVINCE_CODE,L.DEFAULT_VALUE "+
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
		
		m_help_TXT_DISTRICT_DESC_sql=
			
			" SELECT L.NO ,L.DISTRICT_CODE,L.DISTRICT_DESC,L.PROVINCE_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" DISTRICT_CODE,"+
			" DISTRICT_DESC, "+
			" PROVINCE_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
			" WHERE UPPER(DISTRICT_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------  ID     : 1.15 Business Sector Creation Process----------------------------------------
    --------------------Purpose    :Business Sector Code Help ----------------------------------------------
    ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
    --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
		m_help_TXT_SECTOR_CODE_sql=
			" SELECT L.NO ,L.SECTOR_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SECTOR_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR "+
			" WHERE ( UPPER(SECTOR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//------------------- Added By   :Nuwan De Silva------------------------------------------------------
		
		m_help_TXT_SECTOR_DESC_sql=
			" SELECT L.NO ,L.SECTOR_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SECTOR_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------  ID     : 1.17 Charges Creation Process-----------------------------------
    --------------------Purpose    : Charges Code Help ----------------------------------------------
    ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
    --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
		m_help_TXT_TYPE_CODE_sql=
			" SELECT L.NO ,L.TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TYPE_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CHARGES "+
			" WHERE TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------  ID         : 1.16 Business Sub Sector Creation Process-----------------------------------
                --------------------Purpose    : Business Sub Sector Code Help ----------------------------------------------
                ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
                --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
		m_help_TXT_SUB_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.SECTOR_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE,"+
			" SECTOR_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
			" WHERE (SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SUB_CODE_sql_new=
			" SELECT L.NO ,L.SUB_CODE,L.SECTOR_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE,"+
			" SECTOR_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
			" WHERE upper(SECTOR_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') and (upper(SUB_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_SUB_DESC_sql=
			
			" SELECT L.NO ,L.SUB_CODE,L.SECTOR_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE,"+
			" SECTOR_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------  ID      : 1.18 Applicable Charges Creation Process-----------------------------------
    --------------------Purpose    : Sub Type Code Help ----------------------------------------------
    ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
    --------------------  Date     : 21-07-2006---------------------------------------------------------*/
		
		m_help_TXT_SUB_TYPE_CODE_sql=
			" SELECT L.NO ,L.SUB_TYPE_CODE,L.TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE,L.MAINTENANCE_STATUS,L.CHARGE_TYPE,L.ACCOUNT_TYPE "+ //modified by nuwan de silva on 04-12-2007
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TYPE_CODE,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.MAINTENANCE_STATUS,P.CHARGE_TYPE,P.ACCOUNT_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE,"+
			" TYPE_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" MAINTENANCE_STATUS, "+
			" CHARGE_TYPE, "+
			" NVL(ACCOUNT_TYPE,'-') ACCOUNT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			//" WHERE SUB_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // commented by udara 17-07-2017
			" WHERE (SUB_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ // added by udara 17-07-2017
			" AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------  ID      : 1.18 Applicable Charges Creation Process-----------------------------------
    --------------------Purpose    : Fuel Type Code Help ----------------------------------------------
    ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
    --------------------  Date     : 21-07-2006---------------------------------------------------------*/
		m_help_TXT_FUAL_TYPE_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
			" WHERE (UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------ ID         : 1.18 Applicable Charges Creation Process-----------------------------------
        --------------------Purpose    : Item Sub Catogary Help ----------------------------------------------
        ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
        -------------------- Date      : 21-07-2006---------------------------------------------------------*/	
		
		m_help_TXT_ITEM_SUB_CAT_sql_1=
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.SUB_TYPE_CODE,L.FUAL_TYPE_CODE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY'),TO_CHAR(L.TO_DATE,'DD-MM-YYYY'),L.AMOUNT,L.PERCENTAGE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.SUB_TYPE_CODE,P.FUAL_TYPE_CODE,P.FROM_DATE,P.TO_DATE,P.AMOUNT,P.PERCENTAGE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT,"+
			" SUB_TYPE_CODE, "+
			" FUAL_TYPE_CODE, "+
			" FROM_DATE, "+
			" TO_DATE, "+
			" AMOUNT, "+
			" PERCENTAGE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
			" WHERE ITEM_SUB_CAT LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID       : 1.19 Condition of Asset Creation Process----------------------------------
    --------------------Purpose    : Code Help ----------------------------------------------
    ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
    -------------------- Date      : 24-07-2006---------------------------------------------------------*/	
		
		m_help_TXT_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
			" WHERE ( UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID     : 1.40 Make Creation Process ----------------------------------
        --------------------Purpose    : Make Code Help ----------------------------------------------
        ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
        -------------------- Date      : 24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_MAKE_CODE_sql=
			" SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MAKE_CODE,"+
			" MAKE_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
			" WHERE (MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(MAKE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_MAKE_DESC_sql=
			" SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" MAKE_CODE,"+
			" MAKE_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
			" WHERE UPPER(MAKE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID       : 1.41 Model Creation Process ----------------------------------
    --------------------Purpose    :Model Code Help ----------------------------------------------
    ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
    -------------------- Date      : 24-07-2006---------------------------------------------------------*/
		
		/*m_help_TXT_MODEL_CODE_sql=
        " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE,L.ITEM_SUB_CAT,L.MAKE_DESC "+
        " FROM  "+
        " (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE,P.ITEM_SUB_CAT,P.MAKE_DESC "+
        " FROM( "+ 
        " SELECT "+
    " MODEL_CODE,"+
    " DESCRIPTION, "+
        " MAKE_CODE, "+
        " FUEL_TYPE, "+
        " TAX_RATE, "+
        " TAX_FOR_LEASE, "+
        " DEFAULT_VALUE, "+
        " NVL(ITEM_SUB_CAT,'-') ITEM_SUB_CAT, "+
        " "+m_schema_name+".AF_CO_GET_MAKE_DESC(MAKE_CODE) MAKE_DESC "+
        " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
        " WHERE (MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		
		
		m_help_TXT_MODEL_CODE_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE,L.ITEM_SUB_CAT,L.MAKE_DESC, "+
			" L.ENGINE_CAPACITY, "+
			" L.OPTION_TYPE, "+
			" L.COUNTRY_CODE, "+
			" L.YEAR_OF_MANUFACTURE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE,P.ITEM_SUB_CAT,P.MAKE_DESC, "+
			" P.ENGINE_CAPACITY, "+
			" P.OPTION_TYPE, "+
			" P.COUNTRY_CODE, "+
			" P.YEAR_OF_MANUFACTURE "+
			" FROM( "+ 
			" SELECT  "+
			" A.MODEL_CODE, "+
			" A.DESCRIPTION, "+ 
			" MAKE_CODE,  "+
			" FUEL_TYPE,  "+
			" TAX_RATE,  "+
			" TAX_FOR_LEASE,  "+
			" A.DEFAULT_VALUE,  "+
			" NVL(ITEM_SUB_CAT,'-') ITEM_SUB_CAT,  "+
			" "+m_schema_name+".AF_CO_GET_MAKE_DESC(MAKE_CODE) MAKE_DESC , "+
			" NVL(B.ENGINE_CAPACITY,0) ENGINE_CAPACITY , "+
			" NVL(B.OPTION_TYPE,'-') OPTION_TYPE, "+
			" NVL(B.COUNTRY_CODE,'-') COUNTRY_CODE , "+
			" NVL(B.YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL A, "+m_schema_name+".AF_CO_MAS_SUB_MODLE B "+
			" WHERE A.MODEL_CODE=B.SUB_CODE "+
			" AND (A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(A.DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		m_help_TXT_MODEL_DESC_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE,L.ITEM_SUB_CAT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE,P.ITEM_SUB_CAT "+
			" FROM( "+ 
			" SELECT "+
			" MODEL_CODE,"+
			" DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE, "+
			" NVL(ITEM_SUB_CAT,'-') ITEM_SUB_CAT "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------ ID     : 1.42 Vendor Creation Process ----------------------------------
        --------------------Purpose    : Vendor Code Help ----------------------------------------------
        ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
        -------------------- Date      : 24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_VENDOR_CODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE,L.VAT_REG_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE,P.VAT_REG_NO "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE,"+
			" NAME, "+
			" CATEGORY, "+
			" NVL(TYPE,'-') TYPE,"+
			" DEFAULT_VALUE, "+
			" NVL(VAT_REG_NO,' ') VAT_REG_NO "+//Added by Sandun 05-01-2009
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
			//" WHERE (UPPER(VENDOR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // commented by udara 29-03-2016
			" WHERE ((VENDOR_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(VAT_REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY VENDOR_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------ ID     : 1.42 Vendor Creation Process ----------------------------------
        --------------------Purpose    : Item Category Code Help ----------------------------------------------
        ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
        -------------------- Date      : 24-07-2006---------------------------------------------------------*/
		m_help_TXT_CATEGORY_sql=
			" SELECT L.NO ,L.ITEM_CAT_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_CAT_CODE,p.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_CAT_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
			" WHERE ITEM_CAT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID       : 1.43 Customer Category Creation Process ----------------------------------
    --------------------Purpose    : Customer Category Code Help ----------------------------------------------
    ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
    -------------------- Date      : 25-07-2006---------------------------------------------------------*/
		m_help_TXT_CAT_TYPE_CODE_sql=
			" SELECT L.NO ,L.CAT_TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAT_TYPE_CODE,p.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CAT_TYPE_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
			" WHERE (CAT_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_CAT_TYPE_DESC_sql=
			" SELECT L.NO ,L.CAT_TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAT_TYPE_CODE,p.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CAT_TYPE_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID        : 1.44 Initiation Type Creation Process  ----------------------------------
    -------------------- Purpose    : Initiation Type Code Help ----------------------------------------------
    ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
    -------------------- Date       : 25-07-2006---------------------------------------------------------*/
		m_help_TXT_INITIATION_CODE_sql=
			" SELECT L.NO ,L.INITIATION_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INITIATION_CODE,p.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" INITIATION_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_INITIATION_TYPE "+
			" WHERE (INITIATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_INITIATION_DESC_sql=
			" SELECT L.NO ,L.INITIATION_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INITIATION_CODE,p.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" INITIATION_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_INITIATION_TYPE "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID        : 1.50 Document Required Creation Process  ----------------------------------
    -------------------- Purpose    : Document Required Code Help ----------------------------------------------
    ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
    -------------------- Date       : 25-07-2006---------------------------------------------------------*/
		m_help_TXT_CODE_sql_1=
			" SELECT L.NO ,L.CODE AS DOCUMENTS_REQUIRED_CODE ,L.DESCRIPTION AS DOCUMENTS_REQUIRED_DESCRIPTION,L.DOC_APP_TYPE AS DOCUMENTS_REQUIRED_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DOC_APP_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DOC_APP_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
			" WHERE ( CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------ ID        : 1.50 Document Required Creation Process  ----------------------------------
    -------------------- Purpose    : Document Required Code Help ----------------------------------------------
    ------------------- Added By    : Mahela Wickramasekara ------------------------------------------------------
    -------------------- Date       : 27-09-2006---------------------------------------------------------*/
		
		m_help_TXT_CODE_DESC_sql_1=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------ ID         : 1.51 Applicable Document Type Creation Process  ----------------------------------
        -------------------- Purpose    : Applicable Document Type Code Help ----------------------------------------------
        ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
        -------------------- Date       : 26-07-2006---------------------------------------------------------*/
		
		
		m_help_TXT_CODE_sql_2=
			" SELECT L.NO ,L.CODE,L.ENTITY_TYPE,L.STAGE,L.ITEM_CAT_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.ENTITY_TYPE,P.STAGE,P.ITEM_CAT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" ENTITY_TYPE, "+
			" STAGE,"+
			" ITEM_CAT_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			" WHERE CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		/*------------------ ID         : 1.51 Applicable Document Type Creation Process  ----------------------------------
        -------------------- Purpose    : Entity Type Code Help ----------------------------------------------
        ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
        -------------------- Date       : 25-07-2006---------------------------------------------------------*/
		m_help_TXT_ENTITY_TYPE_sql=
			" SELECT L.NO ,L.ENTITY_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENTITY_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" ENTITY_CODE,"+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE ENTITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------ ID       : 1.51 Applicable Document Type Creation Process  ----------------------------------
    -------------------- Purpose    : Stage Help ----------------------------------------------
    ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
    -------------------- Date       : 25-07-2006---------------------------------------------------------*/
		m_help_TXT_STAGE_sql=
			" SELECT L.NO ,L.STAGE_CODE,L.SCREEN_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STAGE_CODE,P.SCREEN_NAME "+
			" FROM( "+ 
			" SELECT "+
			" STAGE_CODE,"+
			" SCREEN_NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_STAGE "+
			" WHERE STAGE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------ ID         :                  ----------------------------------
        -------------------- Purpose    : Application No Help ----------------------------------------------
        ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
        -------------------- Date       : 11-08-2006---------------------------------------------------------*/
		m_help_TXT_APPLICATION_NO_sql=
			" SELECT L.NO ,L.APPLICATION_NO,L.CLIENT_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.CLIENT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO,"+
			" CLIENT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_CLIENT_DOCS "+
			" WHERE APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID         : 1.52 Client Creation Process  ----------------------------------
        -------------------- Purpose    : Cat Type Code Help ----------------------------------------------
        ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
        -------------------- Date       : 27-07-2006---------------------------------------------------------*/		
		
		
		
		
		
		m_help_TXT_CLIENT_CODE_sql_1=
			"SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.FULL_NAME,NVL(L.BUSINESS_SUB_SECTOR,' ') AS BUSINESS_SUB_SECTOR,L.CLIENT_CATEGORY,NVL(L.ADDRESS1,'N/L'),NVL(L.ADDRESS2,'N/L'),NVL(L.CITY_CODE,'N/L'),"+
			"NVL(REFERENCE,'N/L'),NVL(L.TEL_NO,0),NVL(L.FAX_NO,0),NVL(L.EMAIL,'N/L'),NVL(L.OFFICE_TEL_NO,0),NVL(L.MOBILE_NO,0),L.CAT_TYPE_CODE, "+
			"NVL(L.NIC_NO,0),NVL(L.BUSINESS_CERTIFICATE_NO,0),"+
			"NVL(L.KEY_DECISION_MAKER,'N/L'),NVL(L.DESIGNATION,'N/L'),NVL(L.DIRECT_TEL_NO,0),"+
			"NVL(L.CONTACT_FOR_PAYMENT,'N/L'),NVL(L.DESIGNATION_PAYMENT,'N/L'),NVL(L.FACTORY_ADDRESS1,'N/L'),NVL(L.FACTORY_ADDRESS2,'N/L'), "+
			"NVL(L.FACTORY_STATUS,'N/L'),NVL(L.F_CONTACT_PERSON,'N/L'),NVL(L.REGISTERED_ADDRESS1,'N/L'),NVL(L.REGISTERED_ADDRESS2,'N/L'),NVL(L.REGISTERED_CITY_CODE,'N/L'),"+
			"NVL(L.REGISTERED_STATUS,'N/L'),NVL(L.CORRESPONDENCE_STATUS,'N/L'),NVL(F_TEL_NO,0),NVL(F_FAX_NO,0),NVL(F_EMAIL,'N/L'),"+
			"NVL(L.ISSUED_SHARE_CAPITAL,0),NVL(to_Char(L.DATE_OF_INCORPORATION),SYSDATE),NVL(L.VAT_REG_NO,'N/L'),NVL(to_Char(L.VAT_REG_DATE),SYSDATE),NVL(L.TITLE,'N/L'),"+
			"NVL(L.FIRST_NAME,'N/L'),NVL(L.SURNAME,'N/L'),NVL(L.INITIALS,'N/L'),NVL(L.OTHER_NAME,'N/L'),NVL(L.RESIDENTIAL_STATUS,'N/L'),"+
			"NVL(L.DURATION_AT_YEARS,0),NVL(L.DURATION_AT_MONTHS,0), "+
			"NVL(L.PASSPORT_NO,'N/L'),NVL(L.MARITAL_STATUS,'N/L'),NVL(to_Char(L.DATE_OF_BIRTH),''),NVL(L.NATIONALITY,'N/L'),NVL(L.GENDER,'N/L') "+
			
			
			" FROM  "+
			
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.CLIENT_TYPE,P.FULL_NAME,P.BUSINESS_SUB_SECTOR,P.CLIENT_CATEGORY,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE, "+
			"  P.REFERENCE,P.TEL_NO,P.FAX_NO,P.EMAIL,P.OFFICE_TEL_NO,P.MOBILE_NO,P.CAT_TYPE_CODE, "+
			"  P.NIC_NO,P.BUSINESS_CERTIFICATE_NO,"+
			"  P.KEY_DECISION_MAKER,P.DESIGNATION,P.DIRECT_TEL_NO,"+
			"  P.CONTACT_FOR_PAYMENT,P.DESIGNATION_PAYMENT,P.FACTORY_ADDRESS1,P.FACTORY_ADDRESS2, "+
			"  P.FACTORY_STATUS,P.F_CONTACT_PERSON,P.REGISTERED_ADDRESS1,P.REGISTERED_ADDRESS2,P.REGISTERED_CITY_CODE, "+
			"  P.REGISTERED_STATUS,P.CORRESPONDENCE_STATUS,P.F_TEL_NO,P.F_FAX_NO,P.F_EMAIL, "+
			"  P.ISSUED_SHARE_CAPITAL,P.DATE_OF_INCORPORATION,P.VAT_REG_NO,P.VAT_REG_DATE,P.TITLE, "+
			"  P.FIRST_NAME,P.SURNAME,P.INITIALS,P.OTHER_NAME,P.RESIDENTIAL_STATUS, "+
			"  P.DURATION_AT_YEARS,P.DURATION_AT_MONTHS,"+
			"  P.PASSPORT_NO,P.MARITAL_STATUS,P.DATE_OF_BIRTH,P.NATIONALITY,P.GENDER "+
			
			" FROM( "+ 
			" SELECT "+
			" CLIENT_CODE,CLIENT_TYPE,FULL_NAME,BUSINESS_SUB_SECTOR,CLIENT_CATEGORY,ADDRESS1,ADDRESS2,CITY_CODE, "+
			" REFERENCE,TEL_NO,FAX_NO,EMAIL,OFFICE_TEL_NO,MOBILE_NO,CAT_TYPE_CODE, "+
			" NIC_NO,BUSINESS_CERTIFICATE_NO,"+
			" KEY_DECISION_MAKER,DESIGNATION,DIRECT_TEL_NO, "+
			" CONTACT_FOR_PAYMENT,DESIGNATION_PAYMENT,FACTORY_ADDRESS1,FACTORY_ADDRESS2, "+
			" FACTORY_STATUS,F_CONTACT_PERSON,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,REGISTERED_CITY_CODE, "+
			" REGISTERED_STATUS,CORRESPONDENCE_STATUS,F_TEL_NO,F_FAX_NO,F_EMAIL, "+
			" ISSUED_SHARE_CAPITAL,DATE_OF_INCORPORATION,VAT_REG_NO,VAT_REG_DATE,TITLE, "+
			" FIRST_NAME,SURNAME,INITIALS,OTHER_NAME,RESIDENTIAL_STATUS, "+
			" DURATION_AT_YEARS,DURATION_AT_MONTHS,"+
			" PASSPORT_NO,MARITAL_STATUS,DATE_OF_BIRTH,NATIONALITY,GENDER "+
			
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		/*------------------ ID       : 1.52 Client Creation Process  ----------------------------------
    -------------------- Purpose    : Cat Type Code Help ----------------------------------------------
    ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
    -------------------- Date       : 27-07-2006---------------------------------------------------------*/		
		
		
		m_help_TXT_CLIENT_TYPE_sql=
			" SELECT L.NO ,L.CAT_TYPE_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAT_TYPE_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" CAT_TYPE_CODE,"+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
			" WHERE CAT_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//===========================================================================
		//Added by Sandun 
		//on 28-10-2008
		// m_help_TXT_APP_STATUS_sql
		//===========================================================================
		
		m_help_TXT_APP_STATUS_sql=
			" SELECT L.NO ,L.STATUS_CODE,INITCAP(L.STATUS_DESC) DESCREPTION ,DECODE(L.DEFAULT_VALUE,'Y','Yes','N','No') AS DEFAULT_VALUE, DECODE(L.ACTIVE_STATUS,'Y','Yes','N','No') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STATUS_CODE,P.STATUS_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+
			" STATUS_CODE, "+
			" STATUS_DESC, "+
			" NVL(DEFAULT_VALUE,'Y') DEFAULT_VALUE, "+
			" NVL(ACTIVE_STATUS,'Y') ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+	
			" WHERE (STATUS_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(STATUS_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" )P)L  "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";	
		
		m_help_deact_TXT_APP_STATUS_sql= 
			" SELECT L.NO ,L.STATUS_CODE,INITCAP(L.STATUS_DESC) DESCREPTION ,DECODE(L.DEFAULT_VALUE,'Y','Yes','N','No') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','Yes','N','No') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STATUS_CODE,P.STATUS_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+
			" STATUS_CODE, "+
			" STATUS_DESC, "+
			" NVL(DEFAULT_VALUE,'Y') DEFAULT_VALUE, "+
			" NVL(ACTIVE_STATUS,'Y') ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+
			" WHERE ACTIVE_STATUS = 'Y' "+
			" AND (STATUS_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(STATUS_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" )P)L  "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		m_help_react_TXT_APP_STATUS_sql=
			" SELECT L.NO ,L.STATUS_CODE,INITCAP(L.STATUS_DESC) DESCREPTION ,DECODE(L.DEFAULT_VALUE,'Y','Yes','N','No') AS DEFAULT_VALUE, DECODE(L.ACTIVE_STATUS,'Y','Yes','N','No') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STATUS_CODE,P.STATUS_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+
			" STATUS_CODE, "+
			" STATUS_DESC, "+
			" NVL(DEFAULT_VALUE,'Y') DEFAULT_VALUE, "+
			" NVL(ACTIVE_STATUS,'Y') ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+	
			" WHERE ACTIVE_STATUS = 'N' "+
			" AND (STATUS_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(STATUS_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" )P)L  "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";		
		
		
		/*------------------ ID       : 1.53 Account Code Creation Process  ----------------------------------
            -------------------- Purpose    : Account Code Help ----------------------------------------------
          ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
          -------------------- Date       : 08-08-2006---------------------------------------------------------*/		
		
		
		m_help_TXT_ACCOUNT_CODE_sql=
			" SELECT L.NO,L.ACC_TYPE_CODE,L.DESCRIPTION,L.REPORT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACC_TYPE_CODE,P.DESCRIPTION,P.REPORT_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" ACC_TYPE_CODE ,"+
			" ACC_TYPE_DESC DESCRIPTION,"+
			" ACC_BS_PL REPORT_TYPE "+
			" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
			" WHERE (ACC_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ACC_TYPE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//---------modfied by delanjali---------
		//---------date 2007-06-28--------------
		/*	m_help_TXT_ACCOUNT_CODE_sql=
            " SELECT L.NO,L.ACCOUNT_CODE,L.DESCRIPTION,L.REPORT_TYPE "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.ACCOUNT_CODE,P.DESCRIPTION,P.REPORT_TYPE "+
            " FROM( "+ 
          " SELECT "+
      " ACCOUNT_CODE,"+
      " DESCRIPTION,"+
            " REPORT_TYPE "+
      " FROM "+m_schema_name+".AF_CO_ACC_ACCOUNT_CODE "+
        " WHERE (ACCOUNT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND STATUS=('"+m_vector.elementAt(1)+"') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" "; */
		
		
		m_help_TXT_ACCOUNT_DESC_sql=
			" SELECT L.NO,L.ACCOUNT_CODE,L.DESCRIPTION,L.REPORT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_CODE,P.DESCRIPTION,P.REPORT_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" ACCOUNT_CODE,"+
			" DESCRIPTION,"+
			" REPORT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_ACC_ACCOUNTS_CODE "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------     ID       :   ----------------------------------
            -------------------- Purpose    : Follow Up Code Help ----------------------------------------------
          ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
          -------------------- Date       : 25-08-2006---------------------------------------------------------*/		
		
		
		m_help_TXT_ID_NO_sql=
			" SELECT L.NO,L.ID_NO,L.ACTION_TOBE_TAKEN,TO_CHAR(L.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(L.ACTION_DATE,'DD-MM-YYYY'),L.ACTION_SET_FOR,L.SCREEN_NAME, "+
			" L.DIVISION_CODE,L.STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ID_NO,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_DATE,P.ACTION_SET_FOR,P.SCREEN_NAME, "+
			" P.DIVISION_CODE,P.STATUS "+
			" FROM( "+ 
			" SELECT "+
			" ID_NO,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_DATE,ACTION_SET_FOR,SCREEN_NAME, "+
			" DIVISION_CODE,STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
			//" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//Added by Dineth on 2008-10-10
		/*m_help_TXT_ASSIGNED_BY_sql=
            " SELECT L.NO,L.ID_NO,L.ASSIGNED_BY,L.ACTION_TOBE_TAKEN,TO_CHAR(L.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(L.ACTION_DATE,'DD-MM-YYYY'),L.ACTION_SET_FOR,L.SCREEN_NAME, "+
            " L.DIVISION_CODE,L.STATUS "+
            " FROM  "+
        " (SELECT ROWNUM NO,P.ID_NO,P.ENT_USER ASSIGNED_BY,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_DATE,P.ACTION_SET_FOR,P.SCREEN_NAME, "+
            " P.DIVISION_CODE,P.STATUS "+
        " FROM( "+ 
        " SELECT "+
        " ID_NO,ENT_USER,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_DATE,ACTION_SET_FOR,SCREEN_NAME, "+
            " DIVISION_CODE,STATUS "+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        m_help_TXT_ASSIGNED_TO_sql=
            " SELECT L.NO,L.ID_NO,L.ASSIGNED_TO,L.ACTION_TOBE_TAKEN,TO_CHAR(L.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(L.ACTION_DATE,'DD-MM-YYYY'),L.ACTION_SET_FOR,L.SCREEN_NAME, "+
            " L.DIVISION_CODE,L.STATUS "+
            " FROM  "+
        " (SELECT ROWNUM NO,P.ID_NO,P.ACTION_SET_FOR ASSIGNED_TO,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_DATE,P.ACTION_SET_FOR,P.SCREEN_NAME, "+
            " P.DIVISION_CODE,P.STATUS "+
        " FROM( "+ 
        " SELECT "+
        " ID_NO,ACTION_SET_FOR,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_DATE,SCREEN_NAME, "+
            " DIVISION_CODE,STATUS "+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        m_help_TXT_DIVISION_CODE_sql1=
            " SELECT L.NO,L.ID_NO,L.DIVISION_CODE,L.ACTION_TOBE_TAKEN,TO_CHAR(L.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(L.ACTION_DATE,'DD-MM-YYYY'),L.SCREEN_NAME, "+
            " L.STATUS "+
            " FROM  "+
        " (SELECT ROWNUM NO,P.ID_NO,P.DIVISION_CODE,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_DATE,P.SCREEN_NAME, "+
            " P.STATUS "+
        " FROM( "+ 
        " SELECT "+
        " ID_NO,DIVISION_CODE,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_DATE,SCREEN_NAME, "+
            " STATUS "+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        " WHERE DIVISION_CODE IS NOT NULL "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        m_help_TXT_SUB_DIVISION_sql1=
            " SELECT L.NO,L.ID_NO,L.SUB_DIVISION_CODE,L.ACTION_TOBE_TAKEN,TO_CHAR(L.EFF_VAL_DATE,'DD-MM-YYYY'),TO_CHAR(L.ACTION_DATE,'DD-MM-YYYY'),L.SCREEN_NAME, "+
            " L.STATUS "+
            " FROM  "+
        " (SELECT ROWNUM NO,P.ID_NO,P.SUB_DIVISION_CODE,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_DATE,P.SCREEN_NAME, "+
            " P.STATUS "+
        " FROM( "+ 
        " SELECT "+
        " ID_NO,SUB_DIVISION_CODE,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_DATE,SCREEN_NAME, "+
            " STATUS "+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        " WHERE SUB_DIVISION_CODE IS NOT NULL "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        */
		/*
        m_help_TXT_ASSIGNED_BY_sql=
        " SELECT L.NO,L.ASSIGNED_BY "+
        " FROM  "+
        " (SELECT ROWNUM NO,P.ENT_USER ASSIGNED_BY "+
        " FROM( "+ 
        " SELECT "+
        " DISTINCT ENT_USER"+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        
        m_help_TXT_ASSIGNED_TO_sql=
        " SELECT L.NO,L.ASSIGNED_TO "+
        " FROM  "+
        " (SELECT ROWNUM NO,P.ACTION_SET_FOR ASSIGNED_TO "+
        " FROM( "+ 
        " SELECT "+
        " DISTINCT ACTION_SET_FOR "+
        " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
        //" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
        "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		m_help_TXT_ASSIGNED_BY_sql=
			" SELECT L.NO,L.ASSIGNED_BY,L.EMP_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENT_USER ASSIGNED_BY,P.FULLNAME EMP_NAME "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.ENT_USER,C.TITLE||'.'||C.FIRST_NAME||' '||C.LAST_NAME FULLNAME"+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,"+m_schema_name+".CO_CO_MAS_USER B,"+
			" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C "+
			" WHERE A.ENT_USER=B.USER_ID AND B.EMP_ID=C.EMP_CODE "+
			" AND (UPPER(C.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(C.LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ASSIGNED_TO_sql=
			" SELECT L.NO,L.ASSIGNED_TO,L.EMP_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACTION_SET_FOR ASSIGNED_TO,P.FULLNAME EMP_NAME "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.ACTION_SET_FOR,C.TITLE||'.'||C.FIRST_NAME||' '||C.LAST_NAME FULLNAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A,"+m_schema_name+".CO_CO_MAS_USER B,"+
			" "+m_schema_name+".CO_CO_MAS_EMPLOYEE C "+
			" WHERE A.ACTION_SET_FOR=B.USER_ID AND B.EMP_ID=C.EMP_CODE "+
			" AND (UPPER(C.FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			" UPPER(C.LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			//" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_DIVISION_CODE_sql1=
			" SELECT L.NO,L.DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
			" WHERE DIVISION_CODE IS NOT NULL "+
			//" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SUB_DIVISION_sql1=
			" SELECT L.NO,L.SUB_DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT SUB_DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP "+
			" WHERE SUB_DIVISION_CODE IS NOT NULL "+
			//" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//End by Dineth on 2008-10-10
		m_help_TXT_SCREEN_NAME_sql_1=
			" SELECT L.NO,L.DISPLAY_NAME AS SCREEN_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISPLAY_NAME "+
			" FROM( "+
			" SELECT "+
			" DISPLAY_NAME "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE DISPLAY_NAME LIKE ('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_ACTION_SET_FOR_sql=
			" SELECT 	L.NO,L.USER_ID,L.NAME AS USER_NAME "+	
			" FROM  "+	
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME "+	
			" FROM( "+ 
			" SELECT "+
			" USER_ID,NAME "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE NAME LIKE ('%"+m_vector.elementAt(0)+"%') "+
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID       : 1.1 Area Creation Process-----------------------------------------
        --------------------Purpose    :Area Code Help ----------------------------------------------
        ------------------- Added By   :Nuwan De Silva------------------------------------------------------
        --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
		
		m_help_TXT_AREA_CODE_sql=
			" SELECT L.NO ,L.AREA_CODE,L.AREA_DESC,L.CITY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE,P.AREA_DESC,P.CITY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" AREA_CODE,"+
			" AREA_DESC,"+
			" CITY_CODE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_AREA"+
			" WHERE (AREA_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR AREA_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_AREA_DESC_sql=
			" SELECT L.NO ,L.AREA_CODE,L.AREA_DESC,L.CITY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE,P.AREA_DESC,P.CITY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" AREA_CODE,"+
			" AREA_DESC,"+
			" CITY_CODE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_AREA"+
			" WHERE AREA_DESC LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//---------------------ID      :1.20 Currency Creation Process---------------------------------//
		//----------------------Purpose :Currency Code Help -------------------------------------------------//
		//----------------------Name    :Delanjali------------------------------------------------------//
		//----------------------Date    :19-07-2006----------------------------------------------------//
		
		
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
			//" CATEGORY_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
			" WHERE CURR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY CURR_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.10 Designation Creation Process-----------------------------------------
        --------------------Purpose    :Designation Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
		m_help_TXT_DESIGNATION_CODE_sql=
			" SELECT L.NO ,L.DESIGNATION_CODE,L.DESIGNATION_NAME,NVL(L.DESIGNATION_LEVEL,0) DESIGNATION_LEVEL,L.DIVISION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DESIGNATION_CODE,P.DESIGNATION_NAME,P.DESIGNATION_LEVEL,P.DIVISION "+
			" FROM( "+ 
			" SELECT "+
			" DESIGNATION_CODE, "+
			" DESIGNATION_NAME, "+
			" DESIGNATION_LEVEL, "+
			" DIVISION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
			" WHERE ( DESIGNATION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESIGNATION_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_DESIGNATION_DESC_sql=
			" SELECT L.NO ,L.DESIGNATION_CODE,L.DESIGNATION_NAME,NVL(L.DESIGNATION_LEVEL,0) DESIGNATION_LEVEL,L.DIVISION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DESIGNATION_CODE,P.DESIGNATION_NAME,P.DESIGNATION_LEVEL,P.DIVISION "+
			" FROM( "+ 
			" SELECT "+
			" DESIGNATION_CODE, "+
			" DESIGNATION_NAME, "+
			" DESIGNATION_LEVEL, "+
			" DIVISION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
			" WHERE DESIGNATION_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID         : 1.10 Designation Creation Process-----------------------------------------
            --------------------Purpose    :Division Code Help ----------------------------------------------
          ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
          --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
		m_help_TXT_DIVISION_sql=
			" SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION_CODE , "+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			" WHERE DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.11 Employee Creation Process-----------------------------------------
            --------------------Purpose    :Emp Code Help ----------------------------------------------
          ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
          --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
		m_help_TXT_EMP_CODE_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
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
			" ID_NO ,"+
			" NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
			" EMP_DOB ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			//   " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ //comment byPrabash on 03-02-2012
			" WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ //Added by prabash on 03-02-2012
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.12 Bank Creation Process-----------------------------------------
        --------------------Purpose    :Bank Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
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
		
		//added by prabash on 17-05-2012------
		
		m_help_TXT_VEHI_BLACKLIST_sql=
			" SELECT L.NO ,NVL(L.REG_NO,'-')REG_NO,L.ENGINE_NO,L.CHASSIS_NO,NVL(L.DISTRICT_CODE,'-') "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.DISTRICT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" A.REG_NO , "+
			" A.ENGINE_NO ,"+
			" A.CHASSIS_NO , "+
			" A.DISTRICT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
			" WHERE REG_NO NOT IN(SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE B  "+
			"  WHERE A .REG_NO =B.REG_NO AND B.STATUS ='B') "+
			" AND(UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//added by prabash on 17-05-2012------
		
		m_help_TXT_VEHI_BLACKLIST1_sql=
			" SELECT L.NO ,NVL(L.REG_NO,'-')REG_NO,L.ENGINE_NO,L.CHASSIS_NO,NVL(L.DISTRICT_CODE,'-') "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.DISTRICT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" A.REG_NO , "+
			" A.ENGINE_NO ,"+
			" A.CHASSIS_NO , "+
			" A.DISTRICT_CODE  DISTRICT_CODE"+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A "+
			" WHERE REG_NO NOT IN(SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE B  "+
			" WHERE A .REG_NO =B.REG_NO AND B.STATUS ='B') "+
			" AND(UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			" UNION "+
			" SELECT  "+
			" A.REG_NO , "+
			" A.ENGINE_NO ,"+
			" A.CHASSIS_NO , "+
			" '' DISTRICT_CODE "+
			" FROM "+m_schema_name+".AF_CR_PRO_LIST_OF_VEHICLE A "+
			" WHERE REG_NO NOT IN(SELECT REG_NO FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE B  "+
			"  WHERE A .REG_NO =B.REG_NO AND B.STATUS ='B') "+
			" AND(UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			""+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_VEHI_BLACKLIST2_sql=
			" SELECT L.NO ,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO, L.DISTRICT_CODE , L.COMMENTS,L.ENT_DATE BLACK_LISTED_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.DISTRICT_CODE,P.COMMENTS,P.ENT_DATE "+
			" FROM( "+ 
			" SELECT "+
			" REG_NO , "+
			" ENGINE_NO ,"+
			" CHASSIS_NO , "+
			" NVL(DISTRICT_CODE,'-') DISTRICT_CODE, "+
			" NVL(COMMENTS,'-') COMMENTS, "+
			" TO_CHAR(ENT_DATE,'DD-MM-YYYY')ENT_DATE "+
			" FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
			" WHERE  STATUS ='B' AND (UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//13-06-2012----
		m_help_TXT_VEHI_BLACKLIST_DACT_sql=
			" SELECT L.NO ,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO, L.DISTRICT_CODE , L.COMMENTS,L.ENT_DATE BLACK_LISTED_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.DISTRICT_CODE,P.COMMENTS,P.ENT_DATE "+
			" FROM( "+ 
			" SELECT "+
			" REG_NO , "+
			" ENGINE_NO ,"+
			" CHASSIS_NO , "+
			" NVL(DISTRICT_CODE,'-') DISTRICT_CODE, "+
			" NVL(COMMENTS,'-') COMMENTS, "+
			" TO_CHAR(ENT_DATE,'DD-MM-YYYY')ENT_DATE "+
			" FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
			" WHERE  B.STATUS ='B' AND (UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			" AND STATUS='A'"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_VEHI_BLACKLIST_RACT_sql=
			" SELECT L.NO ,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO, L.DISTRICT_CODE , L.COMMENTS,L.ENT_DATE BLACK_LISTED_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.DISTRICT_CODE,P.COMMENTS,P.ENT_DATE "+
			" FROM( "+ 
			" SELECT "+
			" REG_NO , "+
			" ENGINE_NO ,"+
			" CHASSIS_NO , "+
			" NVL(DISTRICT_CODE,'-') DISTRICT_CODE, "+
			" NVL(COMMENTS,'-') COMMENTS, "+
			" TO_CHAR(ENT_DATE,'DD-MM-YYYY')ENT_DATE "+
			" FROM "+m_schema_name+".AF_CR_PRO_BLACK_LIST_VEHICLE "+
			" WHERE (UPPER(REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ENGINE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			" AND STATUS='B'"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//------------------------------------
		
		
		
		m_help_TXT_BANK_NAME_sql=
			" SELECT L.NO ,L.BANK_CODE,L.NAME,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" BANK_CODE , "+
			" NAME ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
			" WHERE UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID       : Licencee settlement Creation Process-----------------------------------------
        --------------------Purpose    : Account Code Code Help ----------------------------------------------
        ------------------- Added By   : M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     : 03-10-2006---------------------------------------------------------*/
		
		m_help_TXT_BRANCH_CODE_1_sql=	
			
		/*   comment by Prabash on 24-102014----*
		"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A') "+
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
			" FROM LAKDL.AF_CO_MAS_LICENCEE_SETTLEMENT "+
			" WHERE UPPER(ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		*/
		//added by Prabash on 24-10-2014-----**
		
		"SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A'),NVL(L.ACC_DESC,'N/A'),L.PREFIX "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC,P.PREFIX "+
			"FROM(  "+
			" SELECT "+
			" A.ACC_NO,"+
			" A.BRANCH_CODE,"+	
			" A.ACC_SYS_REFNO,"+
			" A.CURR_CODE,+"+
			" A.ACC_CODE,"+
			" A.ACC_DESC,"+
			"B.PREFIX  "+
			" FROM LAKDL.AF_CO_MAS_LICENCEE_SETTLEMENT A, "+
			" LAKDL.AF_CO_LIC_SETTL_ACC_PREFIX_SEQ B "+
			" WHERE UPPER(A.ACC_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			" AND A.BRANCH_CODE=B.BRANCH_CODE(+) "+
			" AND A.ACC_NO=B.ACC_NO(+)"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		 //------------------------------------**
		
		/*------------------  ID         : 1.13 Bank Branch Creation Process-----------------------------------------
            --------------------Purpose    :Bank Branch Code Help ----------------------------------------------
          ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
          --------------------  Date     :20-07-2006---------------------------------------------------------*/	
		
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
			" WHERE UPPER(BANK_CODE)=UPPER('"+m_vector.elementAt(1)+"') AND (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_BRANCH_NAME_sql= 
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
			" WHERE UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID         : 1.14 Broker Creation Process-----------------------------------------
            --------------------Purpose    :Broker Code Help ----------------------------------------------
          ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
          --------------------  Date     :20-07-2006---------------------------------------------------------*/
		
		m_help_TXT_BROKER_CODE_sql=
			" SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,NVL(L.CITY_CODE,'N/A'),NVL(L.POSTAL_CODE,'N/A'),NVL(L.CONTACT_NO,'N/A'),"+
			" NVL(L.MOBILE_NO,'N/A'),NVL(L.FAX_NO,'N/A'),NVL(L.SECTOR_CODE,'N/A'),NVL(L.COMMISSION_RATE,0),NVL(L.COMMISSION_AMOUNT,0) "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO,"+
			" P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT "+
			" FROM( "+ 
			" SELECT "+
			" BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,"+
			" MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,COMMISSION_AMOUNT"+
			" FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
			" WHERE (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//added by sandun	
		
		m_help_TXT_BROKER_CODE_sql_New=
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
			//" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // commented by udara on 14-08-2013
			" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ID_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // added by udara on 14-08-2013
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//added by sandun	
		
		m_help_TXT_BROKER_CODE_BLAK_sql_New=
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
			//" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ // commented by udara on 14-08-2013
			" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ID_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )   AND (ACTIVE_STATUS='N' OR ACTIVE_STATUS= 'Y')  "+ // added by udara on 14-08-2013 // AND (ACTIVE_STATUS='N' OR ACTIVE_STATUS= 'Y') added by ishani 2013.08.14
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*	m_help_TXT_BROKER_CODE_sql_New=
            "SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME, "+
    "L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,"+
    "NVL(L.CITY_CODE,'N/A'),NVL(L.POSTAL_CODE,'N/A'),NVL(L.CONTACT_NO,'N/A'),"+
    "NVL(L.MOBILE_NO,'N/A'),NVL(L.FAX_NO,'N/A'),NVL(L.SECTOR_CODE,'N/A'),"+
    "NVL(L.COMMISSION_RATE,0),NVL(L.COMMISSION_AMOUNT,0)"+
    "FROM  "+
        "(SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,"+
    "P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,"+
    "P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO,"+
            "P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT"+ 
            "FROM( "+
            "SELECT "+
        "A.BROKER_CODE,A.TITLE,A.FIRST_NAME,A.LAST_NAME,A.ID_NO,A.ADDRESS1,A.ADDRESS2,A.LOCATION_CODE,A.CITY_CODE,A.POSTAL_CODE,A.CONTACT_NO,"+
        "A.MOBILE_NO,A.FAX_NO,A.SECTOR_CODE,A.COMMISSION_RATE,A.COMMISSION_AMOUNT,B.PAYEE_CODE "+
        "FROM "+m_schema_name+".AF_CO_MAS_BROKER A ,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B"+
            "WHERE (UPPER(BROKER_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
    "UPPER(FIRST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
    "UPPER(LAST_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%')) "+
    "AND ACTIVE_STATUS=('"+m_vector.elementAt(0)+"%') AND A.PAYEE_CODE=B.PAYEE_CODE)P)L  "+
            "WHERE L.NO>="+ Start_Val+"  AND L.NO<="+End_Val+" ";
        */
		
		
		
		m_help_TXT_BROKER_DETAILS_sql=	
			
			" SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,NVL(L.CITY_CODE,'N/A'),NVL(L.POSTAL_CODE,'N/A'),NVL(L.CONTACT_NO,'N/A'), "+
			" NVL(L.MOBILE_NO,'N/A'),NVL(L.FAX_NO,'N/A'),NVL(L.SECTOR_CODE,'N/A'),NVL(L.COMMISSION_RATE,0),NVL(L.COMMISSION_AMOUNT,0), "+
			" NVL(L.COMMENTS,'N/A'),L.BROKER_STATUS "+ 
			" FROM  "+
			" (SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO, "+
			" P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT,P.COMMENTS,P.BROKER_STATUS "+
			" FROM(  "+
			" SELECT "+
			" BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO, "+
			" MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,COMMISSION_AMOUNT,COMMENTS,BROKER_STATUS "+
			" FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
			" WHERE (UPPER(BROKER_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('Y') AND BROKER_STATUS=('"+m_vector.elementAt(1)+"') "+
			" )P)L "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		
		
		
		
		
		/*------------------  ID       : 1.30 Garage Creation Process-----------------------------------------
        --------------------Purpose    :Garage Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_GARAGE_CODE_sql=
			" SELECT L.NO ,L.GARAGE_CODE,L.NAME"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GARAGE_CODE,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" GARAGE_CODE , "+
			" NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
			" WHERE ( UPPER(GARAGE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE  UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID       : 1.30 Garage Creation Process-----------------------------------------
        --------------------Purpose    :Garage Name Help ----------------------------------------------
        ------------------- Added By   :Yohan Gunarathna------------------------------------------------------
        --------------------  Date     :23-09-2006---------------------------------------------------------*/
		
		m_help_TXT_GARAGE_CODE_NAME_sql=
			" SELECT L.NO ,L.GARAGE_CODE,L.NAME"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GARAGE_CODE,P.NAME "+
			" FROM( "+ 
			" SELECT "+
			" GARAGE_CODE , "+
			" NAME "+
			" FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
			" WHERE UPPER(NAME) =  UPPER('"+m_vector.elementAt(0)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//////////////////////////////
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
			" WHERE (UPPER(DISPUTE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DISPUTE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			"	AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		/*	
        " SELECT L.NO ,L.NATIONALITY_CODE \"Nationality\",L.DESCRIPTION"+
        " FROM  "+
        " (SELECT ROWNUM NO,P.NATIONALITY_CODE,P.DESCRIPTION "+
        " FROM( "+ 
            " SELECT "+
            " NATIONALITY_CODE , "+
            " DESCRIPTION  "+
            " FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
            " WHERE (UPPER(NATIONALITY_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  "+
        "	AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		//System.out.println("m_help_DIV_TXT_DISPUTE_CODE_sql"+m_help_DIV_TXT_DISPUTE_CODE_sql);
		/*
        
            " SELECT L.NO ,L.DISPUTE_CODE \"Dispute Code\",L.DISPUTE_DESC \"Dispute Description\",NVL(L.ACTIVATION_POINT,'-') \"Activation Point\" "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.DISPUTE_CODE,P.DISPUTE_DESC,P.ACTIVATION_POINT "+
            " FROM( "+ 
            " SELECT "+ 
            " DISPUTE_CODE, "+ 
            " DISPUTE_DESC, "+ 
            " ACTIVATION_POINT "+ 
            " FROM "+m_schema_name+".fa_co_mas_dispute_code"+ 
            //" WHERE (DISPUTE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DISPUTE_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS LIKE ('"+m_vector.elementAt(2)+"%') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		//////////////////////////////
		
		
		
		/*------------------  ID       : 1.31 Income / Expense Type Creation Process-----------------------------------------
        --------------------Purpose    :Income Expence Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_I_E_CODE_sql=
			" SELECT L.NO ,L.I_E_CODE,L.DESCRIPTION,L.DEFAULT_VALUE,L.TYPE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.I_E_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.TYPE "+
			" FROM( "+ 
			" SELECT "+
			" I_E_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
			" WHERE ( UPPER(I_E_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER(DESCRIPTION) LIKE  UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------  ID       : 1.33 Interest Type Creation Process-----------------------------------------
        --------------------Purpose    :Interest Type Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_I_T_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INTEREST_TYPE "+
			" WHERE CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------  ID       : 1.34 Nationality Creation Process-----------------------------------------
        --------------------Purpose    :Nationality Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :24-07-2006---------------------------------------------------------*/
		
		
		m_help_TXT_NATIONALITY_CODE_sql=
			" SELECT L.NO ,L.NATIONALITY_CODE,L.DESCRIPTION, L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.NATIONALITY_CODE,P.DESCRIPTION, P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" NATIONALITY_CODE , "+
			" DESCRIPTION , DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
			" WHERE (UPPER(NATIONALITY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara on 18-05-2012
		m_help_TXT_SCHEDULE_CODE_sql = 
			" SELECT L.NO \"No.\",L.SHEDULE_REF \"Schedule No.\",L.DESCRIPTION \"Description\",L.ITEM_CAT_CODE \"Item Category Code\",L.ITEM_SUB_CAT \"Item Sub Category Code\" "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SHEDULE_REF,P.DESCRIPTION,P.ITEM_CAT_CODE,P.ITEM_SUB_CAT "+
			" FROM( "+ 
			" SELECT "+
			" SHEDULE_REF , "+
			" DESCRIPTION, "+
			" ITEM_CAT_CODE, "+
			" ITEM_SUB_CAT "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_SHEDULE "+
			" WHERE (UPPER(SHEDULE_REF) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		// end by udara on 18-05-2012
		
		m_help_TXT_NATIONALITY_CODE_sql_desc=
			" SELECT L.NO ,L.NATIONALITY_CODE,L.DESCRIPTION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.NATIONALITY_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" NATIONALITY_CODE , "+
			" DESCRIPTION  "+
			" FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
			" WHERE (UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.32 Inquiry Status Creation Process-----------------------------------------
        --------------------Purpose    :Inquiry Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :24-07-2006---------------------------------------------------------*/
		
		m_help_TXT_INQ_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INQUARY_STATUS "+
			" WHERE (UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_INQ_CODE_sql_desc=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INQUARY_STATUS "+
			" WHERE (UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.45 Lead Source Creation Process-----------------------------------------
        --------------------Purpose    :Lead Source Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :25-07-2006---------------------------------------------------------*/
		
		m_help_TXT_L_S_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE "+
			" WHERE (UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_L_S_CODE_sql_desc=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.46 Valuer Creation Process-----------------------------------------
        --------------------Purpose    :Valuer Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :25-07-2006---------------------------------------------------------*/
		
		m_help_TXT_VALUER_CODE_sql=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,L.MOBILE_NO,L.DEFAULT_VALUE,l.valuer_amount"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.MOBILE_NO,P.DEFAULT_VALUE,p.valuer_amount"+
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
			" DEFAULT_VALUE,"+
			" trim(to_char(valuer_amount,'9,999,999,999,999,999,999,999,999.99')) as valuer_amount "+
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE (VALUER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR FIRST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR LAST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR ADDRESS LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')   ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_TXT_VALUER_NAME_sql=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,L.MOBILE_NO,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.MOBILE_NO,P.DEFAULT_VALUE"+
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
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  "+
			" ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		/*------------------  ID       : 1.48 Sub Model Creation Process-----------------------------------------
        --------------------Purpose    :Sub Model Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :25-07-2006---------------------------------------------------------*/
		
		m_help_TXT_SUB_M_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.DESCRIPTION,L.MODEL_CODE,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE,L.MODEL_DESC"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION,P.MODEL_CODE,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE,P.MODEL_DESC "+
			" FROM( "+
			" SELECT "+
			" SUB_CODE,"+ 
			" DESCRIPTION,"+
			" MODEL_CODE,"+	
			" ENGINE_CAPACITY,"+
			" OPTION_TYPE,"+
			" COUNTRY_CODE,"+
			" YEAR_OF_MANUFACTURE,"+
			" DEFAULT_VALUE,"+
			" "+m_schema_name+".AF_CO_GET_MODEL_DESC(MODEL_CODE) MODEL_DESC"+ 	
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE (UPPER(SUB_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SUB_M_CODE_DESC_sql=
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
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.47 Mileage Creation Process-----------------------------------------
        --------------------Purpose    : Model Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :25-07-2006---------------------------------------------------------*/
		
		m_help_TXT_MODEL_sql=
			" SELECT L.NO ,L.SUB_MODEL,L.MODEL,L.CONDITION_OF_ASSET,L.USAGE_FROM,L.USAGE_TO,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_MODEL,P.MODEL,P.CONDITION_OF_ASSET,P.USAGE_FROM,P.USAGE_TO,P.AMOUNT "+
			" FROM( "+
			" SELECT "+
			"  SUB_MODEL,"+
			"  MODEL,"+
			"  CONDITION_OF_ASSET,"+
			"  USAGE_FROM,"+
			"  USAGE_TO,"+
			"  AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_MILEAGE "+
			" WHERE (UPPER(SUB_MODEL) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY MODEL ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//mili
		m_help_TXT_APPLICATION_NO_MOD=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CR_OFFICER)CR_OFFICER,L.ENT_DATE,L.INQUIRY_CODE,L.ENT_USER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CR_OFFICER,P.ENT_DATE,P.INQUIRY_CODE,P.ENT_USER "+
			" FROM( "+
			" SELECT "+
			"  A.APPLICATION_NO,"+
			"  A.FACILITY_NO,"+
			"  B.CR_OFFICER,"+
			"  TO_CHAR(B.ENT_DATE,'DD-MM-YYYY') ENT_DATE ,"+
			"  B.INQUIRY_CODE,"+
			"  B.ENT_USER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
			" WHERE A.INQUARY_NO=B.INQUIRY_CODE "+
			//" AND (UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND B.STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY A.APPLICATION_NO ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		m_help_TXT_APPLICATION_NO_REA=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CR_OFFICER)CR_OFFICER,TO_CHAR(L.ENT_DATE,'dd-mm-yyyy'),L.INQUIRY_CODE,L.ENT_USER "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CR_OFFICER,P.ENT_DATE,P.INQUIRY_CODE,P.ENT_USER "+
			" FROM( "+
			" SELECT "+
			"  A.APPLICATION_NO,"+
			"  A.FACILITY_NO,"+
			"  B.CR_OFFICER,"+
			"  B.ENT_DATE,"+
			"  B.INQUIRY_CODE,"+
			"  B.ENT_USER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
			" WHERE A.INQUARY_NO=B.INQUIRY_CODE "+
			" AND B.STATUS='N' "+
			//" AND (UPPER(A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND B.STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY A.APPLICATION_NO ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		//MODIFIED BY NUWAN DE SILVa 12-07-07
		m_help_TXT_APPLICATION_NO=	
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.FINANCE_NO FinanceNo,L.BROKER_CODE "+
			" FROM  "+ 
			" (SELECT ROWNUM NO,P.INSURANCE_OFFICER,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE,P.INSURANCE_DONE_BY,P.PRIORITY,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.REMARK,P.BRANCH_CODE,P.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,P.LEAD_SOURCE_CATEGORY,P.LEAD_SOURCE_NAME,P.DIVISION_CODE,P.MK_OFFICER_ID,P.REG_NO,P.FINANCE_NO,P.BROKER_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.APPLICATION_NO, "+
			" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
			" A.CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
			" NVL(A.CO_APPLICANT,'-') CO_APPLICANT ,"+
			
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			" NVL(A.INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(A.CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO),'-') MK_OFFICER, "+
			" A.TRANSACTION_TYPE, "+
			" A.INSURANCE_DONE_BY, "+
			" A.PRIORITY, "+
			" A.INSURANCE_OFFICER, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" B.TEL_NO TEL_NO,  "+
			" B.NIC_NO NIC_NO,  "+
			//" NVL(B.REMARK,'-') REMARK ,"+	
			//" '-' REMARK ,"+
			" NVL("+m_schema_name+".AF_CO_GET_REMARK(A.APPLICATION_NO),'-') REMARK,"+ //added by nuwan de silva 12-07-07
			" NVL(A.BRANCH_CODE,'-') BRANCH_CODE,  "+
			" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') BRANCH_DESC, "+
			
			" DECODE(TER_TYPE,NULL,'-',"+m_schema_name+".AF_CO_GET_TERMINATION_DESC(TER_TYPE)) TER_DESC,"+
			" TERMINATION_NO,PRE_APPLICATION_NO,NVL(TER_TYPE,'-') TER_TYPE, "+
			" "+m_schema_name+".AF_CO_GET_TERMINATED_AMOUNT(TERMINATION_NO) TER_AMT, "+
			" "+m_schema_name+".AF_CO_GET_CLIENT_CODE(PRE_APPLICATION_NO) TER_CLI ,"+
			" NVL(A.LEAD_SOURCE_CATEGORY,'N/A') LEAD_SOURCE_CATEGORY ,"+ //added by nuwan de silva on 19-11-2007
			" NVL(A.LEAD_SOURCE_NAME,' ') LEAD_SOURCE_NAME, "+	           //added by nuwan de silva on 19-11-2007
			" NVL(A.DIVISION_CODE,' ') DIVISION_CODE, "+          //added by nuwan de silva on 27-11-2007
			" "+m_schema_name+".af_co_get_MK_OFFICER_ID(A.INQUARY_NO) MK_OFFICER_ID, "+ //Added by Chandana on 30/11/2007
			"  NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO),'-')REG_NO, "+ //added by prabash on 22-03-2012
			"  NVL(A.FINANCE_NO,'-') FINANCE_NO, "+    //added by prabash on 13-07-2012
			" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE  "+  // added by udara on 16-08-2013
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			/*"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+ //,M.REMARK
				"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					//	" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL M "+ //comment by nuwan de silva 12-07-07
				"  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+ //AND
						//"  X.APPLICATION_NO=M.APPLICATION_NO(+) AND  "+ //comment by nuwan de silva 12-07-07
						//"	 M.STATUS='ENTERED' AND  M.STAGE='ENTER' "+ //MODIFIED BY NUWAN DE SILVA 28-05-07----  //comment by nuwan de silva 12-07-07
						//  "  AND V.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')  "+
				"  ) B  "+
										
				"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
				*/
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE   "+ 
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO)) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ //Added by Prabash on 22-03-2012
			"  B.BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  "+
			"  "+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"  A.APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('"+m_vector.elementAt(1)+"','"+m_vector.elementAt(2)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ Start_Val+"  AND L.NO<= "+End_Val+" ";
		
		/*------------------  ID       : 1.49 Option Creation Process-----------------------------------------
        --------------------Purpose    : Option Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :26-07-2006---------------------------------------------------------*/
		//m_help_TXT_OPTION_CODE_sql
		m_help_TXT_OPTION_CODE_sql=
			" SELECT L.NO ,L.OPTION_CODE,L.OPTION_DESC,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.OPTION_CODE,P.OPTION_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" OPTION_CODE , "+
			" OPTION_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_OPTION_TYPE "+
			" WHERE OPTION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		/*------------------  ID       : 1.49 Option Creation Process-----------------------------------------
        --------------------Purpose    : insurance taxers Code Help ----------------------------------------------
        ------------------- Added By   :K.G Prabash------------------------------------------------------
        --------------------  Date     :13-05-2011---------------------------------------------------------*/
		//m_help_TXT_OPTION_CODE_sql
		m_help_TXT_TAX_CODE_sql=
			" SELECT L.NO ,L.TAX_CODE,L.TAX_DESC,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TAX_CODE,P.TAX_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TAX_CODE , "+
			" TAX_DESC, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".af_co_mas_ins_tax "+
			" WHERE TAX_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.66 Missing Vehicle Process-----------------------------------------
        --------------------Purpose    : Vehicle No Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :26-07-2006---------------------------------------------------------*/
		
		
		m_help_TXT_VEHICLE_NO_sql=
			" SELECT L.NO ,L.VEHICLE_NO,TO_CHAR(L.MISSING_DATE,'DD-MM-YYYY') AS MISSING_DATE,L.ENGIN_NO,L.CHASSISS_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VEHICLE_NO,P.MISSING_DATE,P.ENGIN_NO,P.CHASSISS_NO "+
			" FROM( "+ 
			" SELECT "+
			" VEHICLE_NO , "+
			" MISSING_DATE, "+
			" ENGIN_NO, "+
			" CHASSISS_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_MISSING_VEHICLES "+
			" WHERE VEHICLE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.68 Lead Source Category Process-----------------------------------------
        --------------------Purpose    : Source Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :26-07-2006---------------------------------------------------------*/
		
		m_help_TXT_SOURCE_CODE_sql=
			" SELECT L.NO ,L.SOURCE_CODE,L.NAME,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SOURCE_CODE,P.NAME,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SOURCE_CODE , "+
			" NAME, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
			" WHERE (UPPER(SOURCE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SOURCE_CODE_sql_desc=
			" SELECT L.NO ,L.SOURCE_CODE,L.NAME,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SOURCE_CODE,P.NAME,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SOURCE_CODE , "+
			" NAME, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
			" WHERE UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.69 Transaction Process-----------------------------------------
        --------------------Purpose    : Transaction Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :26-07-2006---------------------------------------------------------*/
		
		m_help_TXT_TRAN_CODE_sql=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE,L.TRAN_HEADER"+ // mod by udara 15-03-2012
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.TRAN_HEADER "+ // mod by udara on 15-03-2012
			" FROM( "+ 
			" SELECT "+
			" TRAN_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" TRAN_HEADER "+ // added by udara 15-03-2012
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
			" WHERE (TRAN_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//Added by Mahela on 30-04-2007
		m_help_TXT_ACC_CODE_sql=
			" SELECT L.NO ,L.ACC_TYPE_CODE,L.ACC_TYPE_DESC,L.ACC_TYPE_CATEGORY,L.DIVISION_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACC_TYPE_CODE,P.ACC_TYPE_DESC,P.ACC_TYPE_CATEGORY,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			"   ACC_TYPE_CODE,"+
			"   ACC_TYPE_DESC,"+
			"   ACC_TYPE_CATEGORY,"+
			"   DIVISION_CODE "+
			" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
			" WHERE (ACC_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ACC_TYPE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_TRAN_DESC_sql=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TRAN_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.70 Transaction Sub Type Process-----------------------------------------
        --------------------Purpose    : Transaction Sub Code Help ----------------------------------------------
        ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
        --------------------  Date     :26-07-2006---------------------------------------------------------*/
		
		m_help_TXT_TRN_SUB_TYPE_sql=
			" SELECT L.NO ,L.TRN_SUB_TYPE,L.DESCRIPTION,L.TRN_CODE,NVL(L.RATE,0)AS RATE ,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRN_SUB_TYPE,P.DESCRIPTION,P.TRN_CODE,P.RATE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TRN_SUB_TYPE,"+
			" DESCRIPTION,"+
			" TRN_CODE,"+
			" RATE,"+
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE"+
			" WHERE (TRN_SUB_TYPE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_TRN_SUB_DESC_sql=
			" SELECT L.NO ,L.TRN_SUB_TYPE,L.DESCRIPTION,L.TRN_CODE,NVL(L.RATE,0)AS RATE ,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRN_SUB_TYPE,P.DESCRIPTION,P.TRN_CODE,P.RATE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TRN_SUB_TYPE,"+
			" DESCRIPTION,"+
			" TRN_CODE,"+
			" RATE,"+
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE"+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//---------------------ID      :1.21 Product Creation Process---------------------------------//
		//----------------------Purpose :Product Code Help -------------------------------------------------//
		//----------------------Name    :Delanjali------------------------------------------------------//
		//----------------------Date    :19-07-2006----------------------------------------------------//
		m_help_TXT_TRAN_CODE_sql1=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TRAN_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
			" WHERE (UPPER(TRAN_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY TRAN_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_TRAN_CODE_sql_edit=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" TRAN_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
			" WHERE (UPPER(TRAN_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" AND UPPER(TRAN_CODE) NOT IN (SELECT PRODUCT_ID FROM  "+m_schema_name+".AF_CO_LEASE_PROCESS_STAGE WHERE DIVISION=UPPER('"+m_vector.elementAt(1)+"')) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*
            
                m_help_TXT_PRODUCT_ID_sql=
            " SELECT L.NO ,L.PRODUCT_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
                " FROM  "+
                " (SELECT ROWNUM NO,P.PRODUCT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
                " FROM( "+ 
                " SELECT "+
            " PRODUCT_CODE , "+
            " DESCRIPTION ,"+
                " DEFAULT_VALUE "+
            " FROM "+m_schema_name+".AF_CO_MAS_PRODUCT "+
                " WHERE PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
                " ORDER BY PRODUCT_CODE ASC"+
                "  )P)L  "+
                " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		//----------------------ID      :1.21 Lease Stage Creation Process-----------------------------------//
		//----------------------Purpose :Leasing Code Help (for NEW Option)-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :19-07-2006--------------------------------------------------------//
		
		m_help_TXT_DIV_sql=
			" SELECT L.NO ,L.DIVISION,L.PRODUCT_ID"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION,P.PRODUCT_ID "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION,"+
			" PRODUCT_ID "+     
			" FROM "+m_schema_name+".AF_CO_LEASE_PROCESS_STAGE"+
			" WHERE DIVISION LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY DIVISION ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_DIVISION1_CODE_sql=
			" SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION_CODE , "+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			" WHERE ACTIVE_STATUS=UPPER('"+m_vector.elementAt(1)+"') AND (DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') or DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))"+
			
			//" ORDER BY DIVISION ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//------------------------------------------------------------------------------------------------
		
		
		//---------------------ID      :1.6 Postal Code Creation Process---------------------------------//
		//----------------------Purpose :Postal Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		m_help_TXT_POSTAL_CODE_sql=
			
			" SELECT L.NO ,L.POSTAL_CODE,L.DESCRIPTION,L.CITY_CODE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.POSTAL_CODE,P.DESCRIPTION,P.CITY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" POSTAL_CODE, "+
			" DESCRIPTION, "+   
			" CITY_CODE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_POSTAL_CODES "+
			" WHERE (UPPER(POSTAL_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%' ) ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//---------------------ID      :1.6 Postal Code Creation Process---------------------------------//
		//----------------------Purpose :Postal Code New Description Help -------------------------------------------------//
		//----------------------Name    :Yohan Gunarathna------------------------------------------------------//
		//----------------------Date    :21-07-2006----------------------------------------------------//
		
		m_help_TXT_POSTAL_CODE_NEW_sql=
			
			" SELECT L.NO ,L.POSTAL_CODE,L.DESCRIPTION,L.CITY_CODE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.POSTAL_CODE,P.DESCRIPTION,P.CITY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" POSTAL_CODE, "+
			" DESCRIPTION, "+   
			" CITY_CODE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_POSTAL_CODES "+
			" WHERE DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%' ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		
		//---------------------ID     :1.7 Location Creation Process---------------------------------//
		//----------------------Purpose :Location Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		
		m_help_TXT_LOCATION_CODE_sql=
			
			" SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE, L.LOC_HEADER AS LOCATION_PREFIX , NVL(L.REGIONS_CODE,'NOT_SELECT') REGIONS_CODE"+ // mod by udara on 15-03-2012
			" FROM  "+
			" (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE,P.LOC_HEADER, P.REGIONS_CODE "+ // mod by udara on 15-03-2012
			" FROM( "+ 
			" SELECT "+
			" LOCATION_CODE, "+
			" LOCATION_DESC, "+
			" ADDRESS1,  "+
			" ADDRESS2, "+
			" CITY_CODE, "+
			" POSTAL_CODE, "+
			" COUNTRY_CODE, "+
			" LOC_HEADER, "+ // mod by udara on 15-03-2012
			" REGIONS_CODE "+ // Added By: Samith Dilshan on 2015-06-01
			" FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
			" WHERE (UPPER(LOCATION_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		//----------------------ID      :1.7 Location Creation Process---------------------------------//
		//----------------------Purpose :Location Description Help -------------------------------------------------//
		//----------------------Name    :Yohan Gunarathna------------------------------------------------------//
		//----------------------Date    :21-07-2006----------------------------------------------------//
		
		m_help_TXT_LOCATION_CODE_NEW_sql=
			
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
			" WHERE UPPER(LOCATION_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		//---------------------ID     :1.8 Division Creation Process---------------------------------//
		//----------------------Purpose :Division  Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		
		m_help_TXT_DIVISION_CODE_sql=
			
			" SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION_CODE, "+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			" WHERE (DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_DIVISION_DESC_sql=
			
			" SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION_CODE, "+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//---------------------ID     :1.25 Engine Capacity Creation Process---------------------------------//
		//----------------------Purpose :Engine Capacity  Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		
		m_help_TXT_CAPACITY_CODE_sql=
			" SELECT L.NO ,L.CAPACITY_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAPACITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CAPACITY_CODE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
			" WHERE (CAPACITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_CAPACITY_DESC_sql=
			
			" SELECT L.NO ,L.CAPACITY_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAPACITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CAPACITY_CODE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_USER_ID_sql=
			
			" SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE,L.PASSWORD,L.EMP_NAME,L.GROUP_ID "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE,P.PASSWORD,P.EMP_NAME,P.GROUP_ID "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
		//	" "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') PASSWORD, "+ 
		    " '-' PASSWORD, "+ // Add by Amila 2017-02-24
			" "+m_schema_name+".AF_CO_GET_EMP_NAME(EMP_ID) EMP_NAME ,"+
			" NVL(GROUP_ID,'-') GROUP_ID "+ // added by nuwan de silva on 13-12-2007
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			//  " WHERE ( USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
			" WHERE ( USER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR EMP_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+   //Added by Prabsh on 03-02-2012
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//__________________________ added by nuwan de silva on 13-12-2007 _____________________________
		m_help_group_user_sql=
			" SELECT L.NO ,L.GROUP_ID,L.GROUP_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_ID,P.GROUP_DESC "+
			" FROM( "+ 
			" SELECT "+
			" GROUP_ID, "+
			" GROUP_DESC "+
			" FROM "+m_schema_name+".CO_CO_MAS_GROUP "+
			//   " WHERE ( UPPER(GROUP_ID) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(GROUP_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+ //comment by Prabsh on 03-02-2012
			" WHERE ( UPPER(GROUP_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(GROUP_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') )"+  //Added by Prabsh on 03-02-2012
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		//__________________________ end by nuwan de silva on 13-12-2007 _____________________________
		
		
		
		m_help_TXT_USER_ID_NAME_sql=
			
			" SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE "+ 
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE UPPER(NAME) = UPPER('"+m_vector.elementAt(0)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.22 Item Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Cat Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :20-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_ITEM_CAT_CODE_sql=
			
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
			" WHERE (ITEM_CAT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+   // Added by Prabash on 03-02-2012
			" ORDER BY ITEM_CAT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_BASE_CODE_NEW_sql=//ADDED BY LALANKA ON 28-01-2010
			
			" SELECT L.NO ,L.ITEM_CAT_CODE,L.DESCRIPTION,L.VAT_APP,L.VAT,L.DEFAULT_VALUE "+
			" FROM(  "+
			" SELECT P.BASE_CODE,P.DESCRIPTION,P.APPLY_DATE,P.RATE "+
			" FROM( "+
			" SELECT  "+
			" DISTINCT A.BASE_CODE BASE_CODE, B.DESCRIPTION DESCRIPTION,TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY') APPLY_DATE, "+
			" A.RATE RATE "+
			" FROM "+m_schema_name+".AF_CO_PRO_INTEREST_BASE_RATE A,  "+
			" "+m_schema_name+".AF_CO_MAS_INTEREST_BASE B  "+
			" WHERE A.BASE_CODE=B.BASE_CODE AND A.ENT_DATE IN( "+
			" SELECT MAX(A.ENT_DATE) "+
			" FROM "+m_schema_name+".AF_CO_PRO_INTEREST_BASE_RATE A "+
			" WHERE A.STATUS='Y' "+
			" GROUP BY A.BASE_CODE)		"+											 
			" UNION ALL  "+
			" SELECT  "+
			" A.BASE_CODE BASE_CODE,  "+
			" A.DESCRIPTION DESCRIPTION, "+
			" '-' APPLY_DATE,  "+
			" A.RATE RATE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE A  "+
			" WHERE A.ACTIVE_STATUS = 'Y' "+
			" AND A.BASE_CODE NOT IN (  "+
			" SELECT  "+
			" DISTINCT A.BASE_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_INTEREST_BASE_RATE A, "+
			" "+m_schema_name+".AF_CO_MAS_INTEREST_BASE B "+
			" WHERE A.BASE_CODE=B.BASE_CODE ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ITEM_CAT_Code_desc_sql=
			
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
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      :1.23 Item Sub Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Sub Cat Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :20-07-2006--------------------------------------------------------//
		
		m_help_TXT_ITEM_SUB_CAT_sql=
			
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
			" WHERE (ITEM_SUB_CAT LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+  //Added by Prabash on 03-02-2012
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// added by udara 15-05-2018
		
		m_help_TXT_ITEM_SUB_CAT_VC_sql=
			
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.DESCRIPTION,L.ITEM_CAT_CODE,L.VAT_APP,L.VAT,L.DEFAULT_VALUE,L.CAPITAL_ALLOWANCE,L.CAP_ALLO_EFF_FATE,L.ITEM_SUB_CAT_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.DESCRIPTION,P.ITEM_CAT_CODE,P.VAT_APP,P.VAT,P.DEFAULT_VALUE,P.CAPITAL_ALLOWANCE,P.CAP_ALLO_EFF_FATE,P.ITEM_SUB_CAT_DESC  "+
			" FROM( "+ 
			" SELECT "+
			" ITEM_SUB_CAT ,"+
			" DESCRIPTION, "+
			" ITEM_CAT_CODE, "+
			" NVL(VAT_APP,0) VAT_APP ,"+
			" NVL(VAT,0) VAT ,"+
			" DEFAULT_VALUE, "+
			"	NVL(CAPITAL_ALLOWANCE,0) CAPITAL_ALLOWANCE,"+
			"	NVL(TO_CHAR(CAP_ALLO_EFF_FATE,'DD-MM-YYYY'),'-') CAP_ALLO_EFF_FATE,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CAT_DESC(ITEM_CAT_CODE) ITEM_SUB_CAT_DESC "+
			" FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			//  " WHERE (ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ //coment by Prabash on 03-02-2012
			" WHERE (ITEM_SUB_CAT LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+  //Added by Prabash on 03-02-2012
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
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
			" AND ACTIVE_STATUS = 'Y'  "+ // added by udara 03-11-2015
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//---------------------ID     :1.26 Fields Creation Process---------------------------------//
		//----------------------Purpose :Fields   Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		m_help_TXT_FILED_CODE_sql=
			" SELECT L.NO ,L.FILED_CODE FIELD_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" FILED_CODE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FILEDS "+
			//      " WHERE (FILED_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+ //comment by Prabash on 03-02-2012
			" WHERE (FILED_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+   //Added by Prabash on 03-02-2012
			" ORDER BY FILED_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_FILED_DESC_sql=
			" SELECT L.NO ,L.FILED_CODE FIELD_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" FILED_CODE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FILEDS "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY FILED_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//---------------------ID     :1.27 Applicable Fields Creation Process---------------------------------//
		//----------------------Purpose :Applicable Fields   Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//	
		
		m_help_TXT_FILED_CODE_sql_applicable=
			
			" SELECT L.NO ,L.FILED_CODE,L.ITEM_CATEGORY,L.PROCESS_STAGE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.ITEM_CATEGORY,P.PROCESS_STAGE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" FILED_CODE,"+
			" ITEM_CATEGORY, "+
			" PROCESS_STAGE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE "+
			" WHERE FILED_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY FILED_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//---------------------ID     :1.28 Fuel Type Creation Process---------------------------------//
		//----------------------Purpose :Fuel Code   Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//	
		
		
		/*   m_help_TXT_CODE_sql=
                " SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
                " FROM  "+
                " (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
                " FROM( "+ 
            " SELECT "+
            " CODE,"+
            " DESCRIPTION, "+
            " DEFAULT_VALUE "+
            " FROM LAKDL.AF_CO_MAS_FUEL_TYPE "+
            " WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
                " ORDER BY CODE ASC"+
                "  )P)L  "+
                " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		//----------------------ID      :1.24 Legal Entiy Creation Process-----------------------------------//
		//----------------------Purpose :Legal Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_ENTITY_CODE_sql=
			
			" SELECT L.NO ,L.ENTITY_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENTITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ENTITY_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE (ENTITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ENTITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_ENTITY_DESC_sql=
			
			" SELECT L.NO ,L.ENTITY_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENTITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" ENTITY_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.35 Phone Area Code Creation Process-----------------------------------//
		//----------------------Purpose :Phone Area Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_PHONE_AREA_CODE_sql=
			
			" SELECT L.NO ,L.PHONE_AREA_CODE,L.CITY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PHONE_AREA_CODE,P.CITY_CODE,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" PHONE_AREA_CODE,"+
			" CITY_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_PHONE_CODES "+
			" WHERE PHONE_AREA_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PHONE_AREA_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID    :1.36 Product Creation Process----------------------------------//
		//----------------------Purpose :Product Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_PRODUCT_CODE_sql=
			
			" SELECT L.NO ,L.PRODUCT_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" PRODUCT_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_PRODUCT "+
			" WHERE PRODUCT_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PRODUCT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		//----------------------ID      :1.37 RMV Agent Creation Process----------------------------------//
		//----------------------Purpose :RMV Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_RMV_AGENT_CODE_sql=
			
			" SELECT L.NO ,L.RMV_AGENT_CODE,L.NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE, "+
			" L.MOBILE_NO,L.TEL_NO,L.MONTHLY_FEE,L.FEE_FOR_CASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RMV_AGENT_CODE,P.NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE, "+
			" P.MOBILE_NO,P.TEL_NO,P.MONTHLY_FEE,P.FEE_FOR_CASE,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" RMV_AGENT_CODE,"+
			" NAME, "+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE, "+
			" MOBILE_NO,"+
			" TEL_NO,"+
			" MONTHLY_FEE,"+
			" FEE_FOR_CASE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_RMV_AGENTS "+
			" WHERE ( UPPER(RMV_AGENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY RMV_AGENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		//----------------------ID      :1.38 Seizer Creation Process----------------------------------//
		//----------------------Purpose :Seizer Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :24-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_SEIZER_CODE_sql=
			
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
			" WHERE SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//*******************Added by Sandun*******************on 30-07-2008***********************************************
		
		// commented by udara 11-12-2013
		/*
		
		m_help_TXT_SEIZER_CODE_sql_New=
			
			" SELECT L.NO ,L.SEIZER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS1,L.ADDRESS2,L.MOBILE_NO,L.TEL_NO,L.CITY_CODE,"+
			" L.FEE_PER_CASE,L.MONTHLY_FEE,L.DEFAULT_VALUE,NVL(L.VALIDITY_PERIOD,0),L.PAYEE_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SEIZER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS1,P.ADDRESS2,P.MOBILE_NO,P.TEL_NO,P.CITY_CODE,"+
			" P.FEE_PER_CASE,P.MONTHLY_FEE,P.DEFAULT_VALUE,P.VALIDITY_PERIOD,P.PAYEE_CODE"+
			" FROM( "+ 
			" SELECT "+
			" A.SEIZER_CODE,"+
			" A.FIRST_NAME, "+
			" A.LAST_NAME,"+
			" A.ADDRESS1,"+
			" A.ADDRESS2,"+
			" A.MOBILE_NO,"+
			" A.TEL_NO,"+
			" A.CITY_CODE, "+
			" A.FEE_PER_CASE,"+
			" A.MONTHLY_FEE,"+
			" A.DEFAULT_VALUE, "+
			" A.VALIDITY_PERIOD,"+
			" A.PAYEE_CODE"+
			" FROM "+m_schema_name+".AF_CO_MAS_SEIZER A ,"+
			" "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
			" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/
		
		// added by udara 11-12-2013
		
		m_help_TXT_SEIZER_CODE_sql_New=
			
			" SELECT L.NO ,L.SEIZER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS1,L.ADDRESS2,L.MOBILE_NO,L.TEL_NO,L.CITY_CODE,"+
			" L.FEE_PER_CASE,L.MONTHLY_FEE,L.DEFAULT_VALUE,NVL(L.VALIDITY_PERIOD,0),L.PAYEE_CODE,NVL(L.NIC_NO,' ')"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SEIZER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS1,P.ADDRESS2,P.MOBILE_NO,P.TEL_NO,P.CITY_CODE,"+
			" P.FEE_PER_CASE,P.MONTHLY_FEE,P.DEFAULT_VALUE,P.VALIDITY_PERIOD,P.PAYEE_CODE,P.NIC_NO"+
			" FROM( "+ 
			" SELECT "+
			" A.SEIZER_CODE,"+
			" A.FIRST_NAME, "+
			" A.LAST_NAME,"+
			" A.ADDRESS1,"+
			" A.ADDRESS2,"+
			" A.MOBILE_NO,"+
			" NVL(A.TEL_NO,'-') TEL_NO, "+ // " A.TEL_NO,"+
			" A.CITY_CODE, "+
			" A.FEE_PER_CASE,"+
			" A.MONTHLY_FEE,"+
			" A.DEFAULT_VALUE, "+
			" A.VALIDITY_PERIOD,"+
			" A.PAYEE_CODE,"+
			" A.NIC_NO "+
			" FROM "+m_schema_name+".AF_CO_MAS_SEIZER A ,"+
			" "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
			" WHERE A.PAYEE_CODE=B.PAYEE_CODE(+) AND (A.SEIZER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR A.NIC_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY A.SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//**********************************************************************************************
		
		
		m_help_TXT_SEIZER_DESC_sql=
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
			" WHERE FIRST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      :1.39 Yard Creation Process----------------------------------//
		//----------------------Purpose :Yard Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :24-07-2006--------------------------------------------------------//
		
		
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
			" WHERE YARD_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY YARD_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------ ID       : 1.28 Fuel Type Creation Process----------------------------------
        --------------------Purpose   : Fuel Code Help ----------------------------------------------
        ------------------- Added By  : Nuwan De Silva------------------------------------------------------
        -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
		
		m_help_TXT_CODE_sql_fuel_type=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
			" WHERE (CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_CODE_sql_fuel_type_desc=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
			" WHERE DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID       : 1.29 Team Creation Process----------------------------------
        --------------------Purpose   : Team id Help ----------------------------------------------
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
			" WHERE ( UPPER(TEAM_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID       : 1.72 Marketing Team Process----------------------------------
        --------------------Purpose   : Team id Help ----------------------------------------------
        ------------------- Added By  : Nuwan De Silva------------------------------------------------------
        -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
		/*
        m_help_TXT_TEAM_ID_sql_new=
        
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
    " WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND TEAM_ID NOT IN(SELECT TEAM_ID FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS) AND (TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(TEAM_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%'))   ORDER BY TEAM_ID "+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		
		
		m_help_txt_sub_team_id_sql_new=
			
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
			" WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND TEAM_ID NOT IN(SELECT TEAM_ID FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN ) AND (TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))   ORDER BY TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		//added by nuwan de silva on 02-06-2008
		m_help_TXT_TEAM_ID_sql_new=
			
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
			" WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND SUB_TEAM_ID NOT IN(SELECT TEAM_ID FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS) AND (SUB_TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(SUB_TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))   ORDER BY SUB_TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*
        m_help_TXT_TEAM_ID_team_sql=
        
        " SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
        " FROM  "+
        " (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
        " FROM( "+ 
        " SELECT "+
    " DISTINCT A.TEAM_ID, "+
        " B.TEAM_DESC, "+
    " B.TEAM_HEAD, "+
    " B.DIVISION_CODE, "+
    " B.SUB_DIVISION_CODE "+
    " FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_TEAMS B "+
        " WHERE A.TEAM_ID=B.TEAM_ID AND (A.TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(B.TEAM_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY TEAM_ID "+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
        */
		
		
		//added by nuwan de silva on 02-06-2008
		m_help_txt_team_sub_team_id_team_sql=
			
			" SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT B.TEAM_ID, "+
			" B.TEAM_DESC, "+
			" B.TEAM_HEAD, "+
			" B.DIVISION_CODE, "+
			" B.SUB_DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN A,"+m_schema_name+".AF_CO_MAS_TEAMS B "+
			" WHERE A.TEAM_ID=B.TEAM_ID AND (B.TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//added by nuwan de silva on 02-06-2008
		m_help_TXT_TEAM_ID_team_sql=
			
			" SELECT L.NO ,L.SUB_TEAM_ID,L.SUB_TEAM_DESC,NVL(L.SUB_TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TEAM_ID,P.SUB_TEAM_DESC,P.SUB_TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT B.SUB_TEAM_ID, "+
			" B.SUB_TEAM_DESC, "+
			" B.SUB_TEAM_HEAD, "+
			" B.DIVISION_CODE, "+
			" B.SUB_DIVISION_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_SUB_TEAMS B "+
			" WHERE A.TEAM_ID=B.SUB_TEAM_ID AND (B.SUB_TEAM_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.SUB_TEAM_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY SUB_TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*	m_help_TXT_TEAM_ID_team_sql_DESC=
            
            " SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE  "+
            " FROM  "+
            " (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE "+
            " FROM( "+ 
            " SELECT "+
      " DISTINCT A.TEAM_ID, "+
            " B.TEAM_DESC, "+
      " B.TEAM_HEAD, "+
      " B.DIVISION_CODE, "+
      " B.SUB_DIVISION_CODE "+
      " FROM "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS A,"+m_schema_name+".AF_CO_MAS_TEAMS B "+
            " WHERE A.TEAM_ID=B.TEAM_ID AND UPPER(B.TEAM_DESC) LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
            "  )P)L  "+
            " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		
		/*--------------END OF TEAM ASSIGN----------------------------------------------------------------------------------------------------------------------------*/
		
		
		
		
		/*------------------ ID       : 1.80 Sub Division Creation Process----------------------------------
        --------------------Purpose   : Sub Division Code Help ----------------------------------------------
        ------------------- Added By  : Nuwan De Silva------------------------------------------------------
        -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
		
		
		m_help_TXT_SUB_DIVISION_CODE_sql=
			
			" SELECT L.NO ,L.SUB_DIVISION_CODE,L.DESCRIPTION,L.DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_DIVISION_CODE,P.DESCRIPTION,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_DIVISION_CODE,"+
			" DESCRIPTION, "+
			" DIVISION_CODE "+
			" FROM "+m_schema_name+".CO_CO_MAS_SUB_DIVISION "+
			" WHERE (SUB_DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  DESCRIPTION LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//Added by Disnaka Jayasuriya on 2009-10-16 for filter sub division codes for to relevant disition code 
		m_help_TXT_SUB_DIVISION_CODE1_sql=
			
			" SELECT L.NO ,L.SUB_DIVISION_CODE,L.DESCRIPTION,L.DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_DIVISION_CODE,P.DESCRIPTION,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_DIVISION_CODE,"+
			" DESCRIPTION, "+
			" DIVISION_CODE "+
			" FROM "+m_schema_name+".CO_CO_MAS_SUB_DIVISION "+
			" WHERE ((SUB_DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND DIVISION_CODE LIKE UPPER('%"+m_vector.elementAt(2)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_SUB_DIVISION_DESC_sql=
			
			" SELECT L.NO ,L.SUB_DIVISION_CODE,L.DESCRIPTION,L.DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_DIVISION_CODE,P.DESCRIPTION,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_DIVISION_CODE,"+
			" DESCRIPTION, "+
			" DIVISION_CODE "+
			" FROM "+m_schema_name+".CO_CO_MAS_SUB_DIVISION "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//----------------------ID      :1.55 Repayment Interval Creation Process----------------------------------//
		//----------------------Purpose :Yard Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :24-07-2006--------------------------------------------------------//
		
		
		
		m_help_TXT_DURATION_sql=
			
			" SELECT L.NO ,L.DURATION,L.DESCRIPTION,L.DURATION_TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DURATION,P.DESCRIPTION,P.DURATION_TYPE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" DURATION ,"+
			" DESCRIPTION , "+			
			" DURATION_TYPE ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
			" WHERE (DURATION LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY DURATION ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_DURATION_DESC_sql=
			
			" SELECT L.NO ,L.DURATION,L.DESCRIPTION,L.DURATION_TYPE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DURATION,P.DESCRIPTION,P.DURATION_TYPE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" DURATION ,"+
			" DESCRIPTION , "+			
			" DURATION_TYPE ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      :1.56 Revenue License Creation Process----------------------------------//
		//----------------------Purpose :Revenue Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :24-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_RCODE_sql=
			
			" SELECT L.NO,L.CODE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY')AS FROM_DATE,TO_CHAR(L.TO_DATE,'DD-MM-YYYY') AS TO_DATE,L.FUEL_TYPE,L.RATE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.FROM_DATE,P.TO_DATE,P.FUEL_TYPE,P.RATE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" CODE ,"+
			" FROM_DATE, "+			
			" TO_DATE,"+
			" FUEL_TYPE,"+
			" RATE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_REVENUE_LICENSE "+
			" WHERE CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			" ORDER BY CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		//----------------------ID    :1.64 Score Rating Process----------------------------------//
		//----------------------Purpose :Score Rating Code help-------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :25-07-2006--------------------------------------------------------//
		m_help_TXT_RATING_CODE_sql=
			
			" SELECT L.NO ,L.RATING_CODE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,NVL(L.FROM_RAGE,0) AS FROM_RAGE,NVL(L.TO_RANGE,0) AS TO_RANGE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATING_CODE,P.DESCRIPTION,P.FROM_RAGE,P.TO_RANGE"+
			" FROM( "+ 
			" SELECT "+
			" RATING_CODE, "+
			" DESCRIPTION, "+
			" FROM_RAGE,"+
			" TO_RANGE "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_RATING "+
			" WHERE (RATING_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY RATING_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_RATING_DESC_sql=
			
			" SELECT L.NO ,L.RATING_CODE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,NVL(L.FROM_RAGE,0) AS FROM_RAGE,NVL(L.TO_RANGE,0) AS TO_RANGE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATING_CODE,P.DESCRIPTION,P.FROM_RAGE,P.TO_RANGE"+
			" FROM( "+ 
			" SELECT "+
			" RATING_CODE, "+
			" DESCRIPTION, "+
			" FROM_RAGE,"+
			" TO_RANGE "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_RATING "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID    :1.73 Authorization Limits Process----------------------------------//
		//----------------------Purpose :User id help-------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :25-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_USER_ID_Authorization_sql=
			
			" SELECT L.NO ,L.USER_ID,L.AUTHORIZATION_LEVEL,L.LIMIT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.AUTHORIZATION_LEVEL,P.LIMIT,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" AUTHORIZATION_LEVEL, "+
			" LIMIT, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS "+
			" WHERE UPPER(USER_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY USER_ID ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
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
			" NVL(FAX_NO,'-') FAX_NO, "+
			" NVL(OFFICE_FAX_NO,'-') OFFICE_FAX_NO, "+
			" FEE_PER_CASE, "+
			" MONTHLY_FEE "+
			
			" FROM "+m_schema_name+".AF_CO_MAS_LAWYER "+
			" WHERE (LAWYER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR FIRST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR LAST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR ADDRESS1 LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR NAME_WITH_INITIALS LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')   OR  TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY LAWYER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_LAWYER_CODE_sql_New=// Added by Sandun on 31-07-2008------
			" SELECT L.NO ,L.LAWYER_CODE,L.FIRST_NAME,L.LAST_NAME,L.NAME_WITH_INITIALS,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2 ,L.CITY_CODE,L.TEL_NO,L.OFFICE_TEL_NO,L.MOBILE_NO,L.FAX_NO,L.OFFICE_FAX_NO,L.FEE_PER_CASE,L.MONTHLY_FEE, L.PAYEE_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LAWYER_CODE,P.FIRST_NAME,P.LAST_NAME,P.NAME_WITH_INITIALS,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.OFFICE_TEL_NO,P.MOBILE_NO,P.FAX_NO,P.OFFICE_FAX_NO,P.FEE_PER_CASE,P.MONTHLY_FEE, P.PAYEE_CODE"+
			" FROM( "+ 
			" SELECT "+
			" A.LAWYER_CODE, "+
			" A.FIRST_NAME, "+
			" A.LAST_NAME, "+
			" A.NAME_WITH_INITIALS, "+
			" A.ADDRESS1, "+
			" A.ADDRESS2, "+
			" A.CITY_CODE, "+
			" A.TEL_NO, "+
			" A.OFFICE_TEL_NO, "+
			" A.MOBILE_NO, "+
			" NVL(A.FAX_NO,'-') FAX_NO, "+
			" NVL(A.OFFICE_FAX_NO,'-') OFFICE_FAX_NO, "+
			" A.FEE_PER_CASE, "+
			" A.MONTHLY_FEE, "+
			" A.PAYEE_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_LAWYER A ,"+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF B "+
			" WHERE A.PAYEE_CODE= B.PAYEE_CODE(+) AND (A.LAWYER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR A.FIRST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR A.LAST_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR A.ADDRESS1 LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR A.NAME_WITH_INITIALS LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR A.CITY_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')   OR  A.TEL_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY LAWYER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_LAWYER_NAME_sql=
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
			" WHERE UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" ORDER BY LAWYER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID    :1.59 Client Backlisting Creation Process----------------------------------//
		//----------------------Purpose :Client Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :25-07-2006--------------------------------------------------------//
		
		
		
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
			" WHERE ( "+
			" (CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR NIC_NO LIKE UPPER('%" + m_vector.elementAt(0) + "%')  " + // added by udara 30-10-2013
			" ) "+
			" AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//---------------------- Client Creation Process----------------------------------//
		//----------------------Purpose :Client Code help-------------------------------------------------//
		//----------------------Name    :Mahela ----------------------------------------------------------//
		//----------------------Date    :30-08-2006--------------------------------------------------------//
		
		
		m_help_TXT_CLIENT_CODE_COR_sql1 =
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.REGISTERED_STATUS,NVL(L.BUSINESS_CERTIFICATE_NO,'') AS BUSINESS_CERTIFICATE_NO,NVL(L.KEY_DECISION_MAKER,''),NVL(L.DESIGNATION_PAYMENT,''),NVL(L.DIRECT_TEL_NO,''),"+
			" NVL(L.CONTACT_FOR_PAYMENT,''),L.CORRESPONDENCE_STATUS,NVL(L.DESIGNATION,''),NVL(L.TEL_NO_GEN,''),NVL(L.FAX_NO_GEN,''),NVL(L.EMAIL_GEN,''),L.FACTORY_STATUS,"+
			" NVL(L.F_CONTACT_PERSON,''),NVL(L.F_TEL_NO,''),NVL(L.F_FAX_NO,''),NVL(L.F_EMAIL,''),L.CLIENT_CATEGORY,NVL(L.ISSUED_SHARE_CAPITAL,0),"+
			" TO_CHAR(L.DATE_OF_INCORPORATION,'DD-MM-YYYY'),NVL(L.VAT_REG_NO,''),TO_CHAR(L.VAT_REG_DATE,'DD-MM-YYYY'),L.REGISTERED_ADDRESS1,L.REGISTERED_ADDRESS2,L.ADDRESS1,L.ADDRESS2,L.FACTORY_ADDRESS1,L.FACTORY_ADDRESS2,L.CITY_CODE,NVL(L.POSTALCODE,'') POSTALCODE,NVL(L.GRIB_NO,'') GRIB_NO,  "+
			" L.BUSINESS_SUB_SECTOR,L.SECTOR_CODE,L.CITY_DESC,L.POST_DESC,L.SECT_DESC,L.SUB_SECT_DESC "+
			" FROM  "+	
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.REGISTERED_STATUS,P.BUSINESS_CERTIFICATE_NO,P.KEY_DECISION_MAKER,P.DESIGNATION_PAYMENT,P.DIRECT_TEL_NO,"+
			" P.CONTACT_FOR_PAYMENT,P.CORRESPONDENCE_STATUS,P.DESIGNATION,P.TEL_NO_GEN,P.FAX_NO_GEN,P.EMAIL_GEN,P.FACTORY_STATUS,"+
			" P.F_CONTACT_PERSON,P.F_TEL_NO,P.F_FAX_NO,P.F_EMAIL,P.CLIENT_CATEGORY,P.ISSUED_SHARE_CAPITAL,"+
			" P.DATE_OF_INCORPORATION,P.VAT_REG_NO,P.VAT_REG_DATE,P.REGISTERED_ADDRESS1,P.REGISTERED_ADDRESS2,P.ADDRESS1,P.ADDRESS2,P.FACTORY_ADDRESS1,P.FACTORY_ADDRESS2,P.CITY_CODE,P.POSTALCODE,P.GRIB_NO, "+
			" P.BUSINESS_SUB_SECTOR,P.SECTOR_CODE,P.CITY_DESC,P.POST_DESC,P.SECT_DESC,P.SUB_SECT_DESC "+
			" FROM ("+
			" SELECT "+
			" CLIENT_CODE,FULL_NAME,REGISTERED_STATUS,BUSINESS_CERTIFICATE_NO,KEY_DECISION_MAKER,DESIGNATION_PAYMENT,DIRECT_TEL_NO,"+
			" CONTACT_FOR_PAYMENT,CORRESPONDENCE_STATUS,DESIGNATION,TEL_NO_GEN,FAX_NO_GEN,EMAIL_GEN,FACTORY_STATUS,"+
			" F_CONTACT_PERSON,F_TEL_NO,F_FAX_NO,F_EMAIL,CLIENT_CATEGORY,ISSUED_SHARE_CAPITAL,"+
			" DATE_OF_INCORPORATION,VAT_REG_NO,VAT_REG_DATE,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,ADDRESS1,ADDRESS2,FACTORY_ADDRESS1,FACTORY_ADDRESS2,CITY_CODE,POSTALCODE,GRIB_NO ,"+
			" NVL(BUSINESS_SUB_SECTOR,'-') BUSINESS_SUB_SECTOR,NVL(SECTOR_CODE,'-') SECTOR_CODE , "+
			" "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_DESC, "+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTALCODE) POST_DESC,  "+ //added by nuwan de silva 22-06-07
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
			
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			" WHERE ((CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//"        UPPER(MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(DIRECT_TEL_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(REGISTERED_ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(BUSINESS_CERTIFICATE_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND CLIENT_TYPE='C' "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_CLIENT_CODE_IND_sql1 =
			"SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.TITLE,NVL(L.FIRST_NAME,'') AS FIRST_NAME ,NVL(L.SURNAME,'') AS SURNAME , NVL(L.NIC_NO,'') AS NIC_NO ,NVL(L.TEL_NO,'') AS TEL_NO ,NVL(L.MOBILE_NO,'') AS MOBILE_NO ,NVL(L.ADDRESS1,'') AS ADDRESS , NVL(L.INITIALS,''),NVL(L.FULL_NAME,''),NVL(L.OTHER_NAME,''),L.RESIDENTIAL_STATUS,"+
			"NVL(L.ADDRESS2,''),NVL(L.OFFICE_TEL_NO,''),NVL(L.FAX_NO,''),NVL(L.EMAIL,''),NVL(L.DURATION_AT_YEARS,''),NVL(L.DURATION_AT_MONTHS,''),NVL(L.EMP_NAME,''),NVL(L.EMP_ADDRESS1,''),"+
			"NVL(L.EMP_ADDRESS2,''),NVL(L.EMP_REFERENCE,''),NVL(L.EMP_RDESIGNATION,''),NVL(L.EMP_TEL_NO,''),NVL(L.EMP_FAX_NO,''),L.NAME,L.REL_ADD1,L.REL_ADD2,L.RELATIONSHIP,L.HOME_TEL_NO,L.REL_OFF_TEL,L.REL_MOB,TO_CHAR(L.DATE_OF_BIRTH,'DD-MM-YYYY'),NVL(L.PASSPORT_NO,''),NVL(L.NATIONALITY,''),L.MARITAL_STATUS,"+
			"L.GENDER,NVL(L.BA_NATURE_OF_BUSINESS,''),NVL(L.BA_PROFESSION,''),NVL(L.BA_QUALIFICATIONS,''),NVL(L.BA_DESIGNATION,''),NVL(L.NO_OF_CHILDREN,''),NVL(L.DEPENDENTS,''),L.CITY_CODE,L.VAT_REG_NO,L.DRIVING_LICENSE_NO,L.POSTALCODE,NVL(L.GRIB_NO,'') GRIB_NO,L.BUSINESS_SUB_SECTOR,L.SECTOR_CODE,L.CITY_DESC,L.POST_DESC,L.SECT_DESC,L.SUB_SECT_DESC "+	
			"FROM  "+		
			"(SELECT ROWNUM NO,	P.CLIENT_CODE,P.CLIENT_TYPE,P.TITLE,P.FIRST_NAME,P.SURNAME,P.NIC_NO,P.TEL_NO,P.MOBILE_NO,P.ADDRESS1,P.INITIALS,P.FULL_NAME,P.OTHER_NAME,P.RESIDENTIAL_STATUS,"+
			"P.ADDRESS2,P.OFFICE_TEL_NO,P.FAX_NO,P.EMAIL,P.DURATION_AT_YEARS,P.DURATION_AT_MONTHS,P.EMP_NAME,P.EMP_ADDRESS1,"+
			"P.EMP_ADDRESS2,P.EMP_REFERENCE,P.EMP_RDESIGNATION,P.EMP_TEL_NO,P.EMP_FAX_NO,P.NAME,P.REL_ADD1,P.REL_ADD2,P.RELATIONSHIP,P.HOME_TEL_NO,P.REL_OFF_TEL,P.REL_MOB,"+
			"P.DATE_OF_BIRTH,P.PASSPORT_NO,P.NATIONALITY,P.MARITAL_STATUS,"+
			"P.GENDER,P.BA_NATURE_OF_BUSINESS,P.BA_PROFESSION,P.BA_QUALIFICATIONS,P.BA_DESIGNATION,P.NO_OF_CHILDREN,P.DEPENDENTS,P.CITY_CODE,P.VAT_REG_NO,P.DRIVING_LICENSE_NO,P.POSTALCODE,P.GRIB_NO,P.BUSINESS_SUB_SECTOR,P.SECTOR_CODE,P.CITY_DESC,P.POST_DESC,P.SECT_DESC,P.SUB_SECT_DESC "+	
			"FROM("+  		
			"SELECT "+
			" DISTINCT  A.CLIENT_CODE, A.CLIENT_TYPE,A.TITLE,A.FIRST_NAME,A.SURNAME,A.NIC_NO,A.TEL_NO,A.MOBILE_NO,A.ADDRESS1,A.INITIALS,A.FULL_NAME,A.OTHER_NAME,A.RESIDENTIAL_STATUS,"+
			" A.ADDRESS2,A.OFFICE_TEL_NO,A.FAX_NO,A.EMAIL,A.DURATION_AT_YEARS,A.DURATION_AT_MONTHS,A.EMP_NAME,"+
			" A.EMP_ADDRESS1,A.EMP_ADDRESS2,A.EMP_REFERENCE,A.EMP_RDESIGNATION,A.EMP_TEL_NO,A.EMP_FAX_NO,B.NAME,B.ADDRESS1 REL_ADD1,B.ADDRESS2 REL_ADD2,"+
			" B.RELATIONSHIP,B.HOME_TEL_NO,B.OFFICE_TEL_NO REL_OFF_TEL,B.MOBILE_NO REL_MOB,A.DATE_OF_BIRTH,A.PASSPORT_NO,"+
			" A.NATIONALITY,A.MARITAL_STATUS,A.GENDER,A.BA_NATURE_OF_BUSINESS,A.BA_PROFESSION,A.BA_QUALIFICATIONS,A.BA_DESIGNATION,A.NO_OF_CHILDREN,"+
			" A.DEPENDENTS,A.CITY_CODE,A.VAT_REG_NO,A.DRIVING_LICENSE_NO,NVL(A.POSTALCODE,'-') POSTALCODE,GRIB_NO,NVL(BUSINESS_SUB_SECTOR,'-') BUSINESS_SUB_SECTOR,NVL(SECTOR_CODE,'-') SECTOR_CODE , "+
			" "+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE) CITY_DESC, "+m_schema_name+".AF_CO_GET_POSTAL_DESC(A.POSTALCODE) POST_DESC,  "+ //added by nuwan de silva 22-06-07
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE B "+
			" WHERE ((A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.TEL_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.PASSPORT_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.NIC_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"       A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) AND CLIENT_TYPE='I' "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//To Retrieve data From Temp table
		m_help_TXT_CLIENT_CODE_IND_TMP_sql1 =
			"SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.TITLE,NVL(L.FIRST_NAME,'') AS FIRST_NAME ,NVL(L.SURNAME,'') AS SURNAME , NVL(L.NIC_NO,'') AS NIC_NO ,NVL(L.TEL_NO,'') AS TEL_NO ,NVL(L.MOBILE_NO,'') AS MOBILE_NO ,NVL(L.ADDRESS1,'') AS ADDRESS , NVL(L.INITIALS,''),NVL(L.FULL_NAME,''),NVL(L.OTHER_NAME,''),L.RESIDENTIAL_STATUS,"+
			"NVL(L.ADDRESS2,''),NVL(L.OFFICE_TEL_NO,''),NVL(L.FAX_NO,''),NVL(L.EMAIL,''),NVL(L.DURATION_AT_YEARS,''),NVL(L.DURATION_AT_MONTHS,''),NVL(L.EMP_NAME,''),NVL(L.EMP_ADDRESS1,''),"+
			"NVL(L.EMP_ADDRESS2,''),NVL(L.EMP_REFERENCE,''),NVL(L.EMP_RDESIGNATION,''),NVL(L.EMP_TEL_NO,''),NVL(L.EMP_FAX_NO,''),L.NAME,L.REL_ADD1,L.REL_ADD2,L.RELATIONSHIP,L.HOME_TEL_NO,L.REL_OFF_TEL,L.REL_MOB,TO_CHAR(L.DATE_OF_BIRTH,'DD-MM-YYYY'),NVL(L.PASSPORT_NO,''),NVL(L.NATIONALITY,''),L.MARITAL_STATUS,"+
			"L.GENDER,NVL(L.BA_NATURE_OF_BUSINESS,''),NVL(L.BA_PROFESSION,''),NVL(L.BA_QUALIFICATIONS,''),NVL(L.BA_DESIGNATION,''),NVL(L.NO_OF_CHILDREN,''),NVL(L.DEPENDENTS,''),L.CITY_CODE,L.VAT_REG_NO,L.DRIVING_LICENSE_NO,L.POSTALCODE,NVL(L.GRIB_NO,'') GRIB_NO,L.BUSINESS_SUB_SECTOR,L.SECTOR_CODE,L.CITY_DESC,L.POST_DESC,L.SECT_DESC,L.SUB_SECT_DESC "+	
			"FROM  "+		
			"(SELECT ROWNUM NO,	P.CLIENT_CODE,P.CLIENT_TYPE,P.TITLE,P.FIRST_NAME,P.SURNAME,P.NIC_NO,P.TEL_NO,P.MOBILE_NO,P.ADDRESS1,P.INITIALS,P.FULL_NAME,P.OTHER_NAME,P.RESIDENTIAL_STATUS,"+
			"P.ADDRESS2,P.OFFICE_TEL_NO,P.FAX_NO,P.EMAIL,P.DURATION_AT_YEARS,P.DURATION_AT_MONTHS,P.EMP_NAME,P.EMP_ADDRESS1,"+
			"P.EMP_ADDRESS2,P.EMP_REFERENCE,P.EMP_RDESIGNATION,P.EMP_TEL_NO,P.EMP_FAX_NO,P.NAME,P.REL_ADD1,P.REL_ADD2,P.RELATIONSHIP,P.HOME_TEL_NO,P.REL_OFF_TEL,P.REL_MOB,"+
			"P.DATE_OF_BIRTH,P.PASSPORT_NO,P.NATIONALITY,P.MARITAL_STATUS,"+
			"P.GENDER,P.BA_NATURE_OF_BUSINESS,P.BA_PROFESSION,P.BA_QUALIFICATIONS,P.BA_DESIGNATION,P.NO_OF_CHILDREN,P.DEPENDENTS,P.CITY_CODE,P.VAT_REG_NO,P.DRIVING_LICENSE_NO,P.POSTALCODE,P.GRIB_NO,P.BUSINESS_SUB_SECTOR,P.SECTOR_CODE,P.CITY_DESC,P.POST_DESC,P.SECT_DESC,P.SUB_SECT_DESC "+	
			"FROM("+  		
			"SELECT "+
			" DISTINCT  A.CLIENT_CODE, A.CLIENT_TYPE,A.TITLE,A.FIRST_NAME,A.SURNAME,A.NIC_NO,A.TEL_NO,A.MOBILE_NO,A.ADDRESS1,A.INITIALS,A.FULL_NAME,A.OTHER_NAME,A.RESIDENTIAL_STATUS,"+
			" A.ADDRESS2,A.OFFICE_TEL_NO,A.FAX_NO,A.EMAIL,A.DURATION_AT_YEARS,A.DURATION_AT_MONTHS,A.EMP_NAME,"+
			" A.EMP_ADDRESS1,A.EMP_ADDRESS2,A.EMP_REFERENCE,A.EMP_RDESIGNATION,A.EMP_TEL_NO,A.EMP_FAX_NO,B.NAME,B.ADDRESS1 REL_ADD1,B.ADDRESS2 REL_ADD2,"+
			" B.RELATIONSHIP,B.HOME_TEL_NO,B.OFFICE_TEL_NO REL_OFF_TEL,B.MOBILE_NO REL_MOB,A.DATE_OF_BIRTH,A.PASSPORT_NO,"+
			" A.NATIONALITY,A.MARITAL_STATUS,A.GENDER,A.BA_NATURE_OF_BUSINESS,A.BA_PROFESSION,A.BA_QUALIFICATIONS,A.BA_DESIGNATION,A.NO_OF_CHILDREN,"+
			" A.DEPENDENTS,A.CITY_CODE,A.VAT_REG_NO,A.DRIVING_LICENSE_NO,NVL(A.POSTALCODE,'-') POSTALCODE,GRIB_NO,NVL(BUSINESS_SUB_SECTOR,'-') BUSINESS_SUB_SECTOR,NVL(SECTOR_CODE,'-') SECTOR_CODE,  "+
			" "+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE) CITY_DESC, "+m_schema_name+".AF_CO_GET_POSTAL_DESC(A.POSTALCODE) POST_DESC,  "+ //added by nuwan de silva 22-06-07
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
			
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE_TM B "+
			" WHERE ((A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.TEL_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(A.PASSPORT_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (A.NIC_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"       A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) AND CLIENT_TYPE='I' "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*"SELECT L.NO,L.CLIENT_CODE,L.CLIENT_TYPE,L.TITLE,NVL(L.FIRST_NAME,'') AS FIRST_NAME ,NVL(L.SURNAME,'') AS SURNAME ,NVL(L.INITIALS,''),NVL(L.FULL_NAME,''),NVL(L.OTHER_NAME,''),L.RESIDENTIAL_STATUS,NVL(L.TEL_NO,''),"+
    "NVL(L.ADDRESS1,''),NVL(L.ADDRESS2,''),NVL(L.OFFICE_TEL_NO,''),NVL(L.FAX_NO,''),NVL(L.MOBILE_NO,''),NVL(L.EMAIL,''),NVL(L.DURATION_AT_YEARS,''),NVL(L.DURATION_AT_MONTHS,''),NVL(L.EMP_NAME,''),NVL(L.EMP_ADDRESS1,''),"+
    "NVL(L.EMP_ADDRESS2,''),NVL(L.EMP_REFERENCE,''),NVL(L.EMP_RDESIGNATION,''),NVL(L.EMP_TEL_NO,''),NVL(L.EMP_FAX_NO,''),L.NAME,L.REL_ADD1,L.REL_ADD2,L.RELATIONSHIP,L.HOME_TEL_NO,L.REL_OFF_TEL,L.REL_MOB, NVL(L.NIC_NO,''),TO_CHAR(L.DATE_OF_BIRTH,'DD-MM-YYYY'),NVL(L.PASSPORT_NO,''),NVL(L.NATIONALITY,''),L.MARITAL_STATUS,"+
    "L.GENDER,NVL(L.BA_NATURE_OF_BUSINESS,''),NVL(L.BA_PROFESSION,''),NVL(L.BA_QUALIFICATIONS,''),NVL(L.BA_DESIGNATION,''),NVL(L.NO_OF_CHILDREN,''),NVL(L.DEPENDENTS,''),L.CITY_CODE "+	
      "FROM  "+		
    "(SELECT ROWNUM NO,	P.CLIENT_CODE,P.CLIENT_TYPE,P.TITLE,P.FIRST_NAME,P.SURNAME,P.INITIALS,P.FULL_NAME,P.OTHER_NAME,P.RESIDENTIAL_STATUS,P.TEL_NO,"+
    "P.ADDRESS1,P.ADDRESS2,P.OFFICE_TEL_NO,P.FAX_NO,P.MOBILE_NO,P.EMAIL,P.DURATION_AT_YEARS,P.DURATION_AT_MONTHS,P.EMP_NAME,P.EMP_ADDRESS1,"+
    "P.EMP_ADDRESS2,P.EMP_REFERENCE,P.EMP_RDESIGNATION,P.EMP_TEL_NO,P.EMP_FAX_NO,P.NAME,P.REL_ADD1,P.REL_ADD2,P.RELATIONSHIP,P.HOME_TEL_NO,P.REL_OFF_TEL,P.REL_MOB,"+
    "P.NIC_NO,P.DATE_OF_BIRTH,P.PASSPORT_NO,P.NATIONALITY,P.MARITAL_STATUS,"+
    "P.GENDER,P.BA_NATURE_OF_BUSINESS,P.BA_PROFESSION,P.BA_QUALIFICATIONS,P.BA_DESIGNATION,P.NO_OF_CHILDREN,P.DEPENDENTS,P.CITY_CODE "+	
      "FROM("+  		
      "SELECT "+
      " DISTINCT  A.CLIENT_CODE, A.CLIENT_TYPE,A.TITLE,A.FIRST_NAME,A.SURNAME,A.INITIALS,A.FULL_NAME,A.OTHER_NAME,A.RESIDENTIAL_STATUS,A.TEL_NO,"+
    " A.ADDRESS1,A.ADDRESS2,A.OFFICE_TEL_NO,A.FAX_NO,A.MOBILE_NO,A.EMAIL,A.DURATION_AT_YEARS,A.DURATION_AT_MONTHS,A.EMP_NAME,"+
    " A.EMP_ADDRESS1,A.EMP_ADDRESS2,A.EMP_REFERENCE,A.EMP_RDESIGNATION,A.EMP_TEL_NO,A.EMP_FAX_NO,B.NAME,B.ADDRESS1 REL_ADD1,B.ADDRESS2 REL_ADD2,"+
    " B.RELATIONSHIP,B.HOME_TEL_NO,B.OFFICE_TEL_NO REL_OFF_TEL,B.MOBILE_NO REL_MOB,A.NIC_NO,A.DATE_OF_BIRTH,A.PASSPORT_NO,"+
    " A.NATIONALITY,A.MARITAL_STATUS,A.GENDER,A.BA_NATURE_OF_BUSINESS,A.BA_PROFESSION,A.BA_QUALIFICATIONS,A.BA_DESIGNATION,A.NO_OF_CHILDREN,"+
    " A.DEPENDENTS,A.CITY_CODE "+
    " FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE_TM B "+
      " WHERE (UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(A.MOBILE_NO) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(A.TEL_NO)   LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(A.ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(A.NIC_NO)   LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(A.PASSPORT_NO)   LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
        "        UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
        "        A.CLIENT_CODE=B.CLIENT_CODE(+) AND CLIENT_TYPE='I' "+
    " ORDER BY CLIENT_CODE ASC"+
        "  )P)L  "+
        " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		m_help_TXT_CLIENT_CODE_COR_TMP_sql1 =
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.REGISTERED_STATUS,NVL(L.BUSINESS_CERTIFICATE_NO,'') AS BUSINESS_CERTIFICATE_NO,NVL(L.KEY_DECISION_MAKER,''),NVL(L.DESIGNATION_PAYMENT,''),NVL(L.DIRECT_TEL_NO,''),"+
			" NVL(L.CONTACT_FOR_PAYMENT,''),L.CORRESPONDENCE_STATUS,NVL(L.DESIGNATION,''),NVL(L.TEL_NO_GEN,''),NVL(L.FAX_NO_GEN,''),NVL(L.EMAIL_GEN,''),L.FACTORY_STATUS,"+
			" NVL(L.F_CONTACT_PERSON,''),NVL(L.F_TEL_NO,''),NVL(L.F_FAX_NO,''),NVL(L.F_EMAIL,''),L.CLIENT_CATEGORY,NVL(L.ISSUED_SHARE_CAPITAL,0),"+
			" TO_CHAR(L.DATE_OF_INCORPORATION,'DD-MM-YYYY'),NVL(L.VAT_REG_NO,''),TO_CHAR(L.VAT_REG_DATE,'DD-MM-YYYY'),L.REGISTERED_ADDRESS1,L.REGISTERED_ADDRESS2,L.ADDRESS1,L.ADDRESS2,L.FACTORY_ADDRESS1,L.FACTORY_ADDRESS2,L.CITY_CODE,NVL(L.POSTALCODE,'') POSTALCODE , NVL(L.GRIB_NO,'') GRIB_NO,L.BUSINESS_SUB_SECTOR,L.SECTOR_CODE ,L.CITY_DESC,L.POST_DESC,L.SECT_DESC,L.SUB_SECT_DESC "+
			
			" FROM  "+	
			" (SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.REGISTERED_STATUS,P.BUSINESS_CERTIFICATE_NO,P.KEY_DECISION_MAKER,P.DESIGNATION_PAYMENT,P.DIRECT_TEL_NO,"+
			" P.CONTACT_FOR_PAYMENT,P.CORRESPONDENCE_STATUS,P.DESIGNATION,P.TEL_NO_GEN,P.FAX_NO_GEN,P.EMAIL_GEN,P.FACTORY_STATUS,"+
			" P.F_CONTACT_PERSON,P.F_TEL_NO,P.F_FAX_NO,P.F_EMAIL,P.CLIENT_CATEGORY,P.ISSUED_SHARE_CAPITAL,"+
			" P.DATE_OF_INCORPORATION,P.VAT_REG_NO,P.VAT_REG_DATE,P.REGISTERED_ADDRESS1,P.REGISTERED_ADDRESS2,P.ADDRESS1,P.ADDRESS2,P.FACTORY_ADDRESS1,P.FACTORY_ADDRESS2,P.CITY_CODE,P.POSTALCODE,P.GRIB_NO,P.BUSINESS_SUB_SECTOR,P.SECTOR_CODE,P.CITY_DESC,P.POST_DESC,P.SECT_DESC,P.SUB_SECT_DESC "+
			" FROM ("+
			" SELECT "+
			" CLIENT_CODE,FULL_NAME,REGISTERED_STATUS,BUSINESS_CERTIFICATE_NO,KEY_DECISION_MAKER,DESIGNATION_PAYMENT,DIRECT_TEL_NO,"+
			" CONTACT_FOR_PAYMENT,CORRESPONDENCE_STATUS,DESIGNATION,TEL_NO_GEN,FAX_NO_GEN,EMAIL_GEN,FACTORY_STATUS,"+
			" F_CONTACT_PERSON,F_TEL_NO,F_FAX_NO,F_EMAIL,CLIENT_CATEGORY,ISSUED_SHARE_CAPITAL,"+
			" DATE_OF_INCORPORATION,VAT_REG_NO,VAT_REG_DATE,REGISTERED_ADDRESS1,REGISTERED_ADDRESS2,ADDRESS1,ADDRESS2,FACTORY_ADDRESS1,FACTORY_ADDRESS2,CITY_CODE,POSTALCODE,GRIB_NO,nvl(BUSINESS_SUB_SECTOR,'-') BUSINESS_SUB_SECTOR,NVL(SECTOR_CODE,'-') SECTOR_CODE, "+
			" "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_DESC, "+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTALCODE) POST_DESC, "+ //added by nuwan de silva 22-06-07
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SECT_NAME(SECTOR_CODE),'-') SECT_DESC, "+    //Added by Chandana on 10/07/2007 For Ref No.446
			" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(BUSINESS_SUB_SECTOR),'-') SUB_SECT_DESC "+ //Added by Chandana on 10/07/2007 For Ref No.446
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM "+
			" WHERE ((CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			//"        UPPER(MOBILE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        (DIRECT_TEL_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(REGISTERED_ADDRESS1) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(BUSINESS_CERTIFICATE_NO)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND "+
			"        ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND CLIENT_TYPE='C' "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*------------------ ID         : 1.58 Vendor Backlisting Creation Process ----------------------------------
        --------------------Purpose    : Vendor Code Help ----------------------------------------------
        ------------------- Added By   : Delanjali------------------------------------------------------
        -------------------- Date      : 26-07-2006---------------------------------------------------------*/
		
		m_help_TXT_VENDOR_BCODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME,P.CATEGORY,P.TYPE,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" VENDOR_CODE,"+
			" NAME, "+
			" CATEGORY, "+
			" TYPE,"+
			" ACTIVE_STATUS ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
			" WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.78 Follow up Category Creation Process----------------------------------//
		//----------------------Purpose :Follow up Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :26-07-2006--------------------------------------------------------//
		
		m_help_TXT_CATEGORY_CODE_sql=
			
			" SELECT L.NO ,L.CATEGORY_CODE,L.CATEGORY_NAME,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CATEGORY_CODE,P.CATEGORY_NAME,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			"	CATEGORY_CODE,"+
			"	CATEGORY_NAME,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY "+
			" WHERE (UPPER(CATEGORY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(CATEGORY_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CATEGORY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_CATEGORY_CODE_desc_sql=
			
			" SELECT L.NO ,L.CATEGORY_CODE,L.CATEGORY_NAME,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CATEGORY_CODE,P.CATEGORY_NAME,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			"	CATEGORY_CODE,"+
			"	CATEGORY_NAME,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY "+
			" WHERE UPPER(CATEGORY_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" ORDER BY CATEGORY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//----------------------ID      :1.77 Discount Rate Creation Process----------------------------------//
		//----------------------Purpose :Rate Validation -------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :26-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_RATE_sql=
			
			" SELECT L.NO ,L.RATE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY') AS FROM_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATE,P.FROM_DATE"+
			" FROM( "+ 
			" SELECT "+
			"	RATE,"+
			"	FROM_DATE"+
			" FROM "+m_schema_name+".AF_CO_MAS_DISCOUNT_RATE "+
			" WHERE RATE LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			" ORDER BY RATE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_RATE_sql_desc=
			
			" SELECT L.NO ,L.RATE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY') AS FROM_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATE,P.FROM_DATE"+
			" FROM( "+ 
			" SELECT "+
			"	RATE,"+
			"	FROM_DATE"+
			" FROM "+m_schema_name+".AF_CO_MAS_DISCOUNT_RATE "+
			" WHERE TO_CHAR(FROM_DATE,'DD-MM-YYYY') LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			" ORDER BY RATE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.71 Variable Interest Base Process -----------------------------------//
		//----------------------Purpose :Base Code Validation -------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :27-07-2006--------------------------------------------------------//
		
		m_help_TXT_BASE_CODE_sql=
			
			" SELECT L.NO ,L.BASE_CODE,L.DESCRIPTION,L.RATE,NVL(L.DD,' '),NVL(L.MM,' '),NVL(L.YYYY,' '),NVL(L.FREQUENCY,0) "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BASE_CODE,P.DESCRIPTION,P.RATE,P.DD,P.MM,P.YYYY,P.FREQUENCY "+
			" FROM( "+ 
			" SELECT "+
			"	BASE_CODE,"+
			"	DESCRIPTION ,"+
			" RATE "+
			" ,TO_CHAR(STRT_DATE,'DD') DD,TO_CHAR(STRT_DATE,'MM') MM,TO_CHAR(STRT_DATE,'YYYY') YYYY,FREQUENCY "+//ADDED BY LALANKA ON 22-01-2010
			" FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE "+
			" WHERE (BASE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY BASE_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_BASE_DESC_sql=
			
			" SELECT L.NO ,L.BASE_CODE,L.DESCRIPTION,L.RATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BASE_CODE,P.DESCRIPTION,P.RATE"+
			" FROM( "+ 
			" SELECT "+
			"	BASE_CODE,"+
			"	DESCRIPTION ,"+
			" RATE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.54 Maintenance Rates Creation Process  -----------------------------------//
		//----------------------Purpose :Make Code Help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :27-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_MAIN_CODE_sql=
			
			" SELECT L.NO ,L.MAKE_CODE,L.SUB_MODEL_CODE,L.CHARGE_SUB_CODE,L.MILEAGE_CODE,L.INCREASE_DECREASE, "+
			"	L.AMOUNT,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.SUB_MODEL_CODE,P.CHARGE_SUB_CODE,P.MILEAGE_CODE,P.INCREASE_DECREASE, "+
			"	P.AMOUNT,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			"	MAKE_CODE,"+
			"	SUB_MODEL_CODE ,"+
			" CHARGE_SUB_CODE, "+
			" MILEAGE_CODE,"+
			" INCREASE_DECREASE,"+
			" AMOUNT,"+
			" DEFAULT_VALUE"+
			" FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE "+
			" WHERE MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY MAKE_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_SUB_MODEL_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_CHARGE_SUB_CODE_sql=
			" SELECT L.NO ,L.SUB_TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE,"+
			
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			" WHERE SUB_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_MILEAGE_CODE_sql=
			" SELECT L.NO ,L.MODEL,L.CONDITION_OF_ASSET,L.USAGE_FROM,L.USAGE_TO,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL,P.CONDITION_OF_ASSET,P.USAGE_FROM,P.USAGE_TO,P.AMOUNT "+
			" FROM( "+
			" SELECT "+
			"  MODEL,"+
			
			"  CONDITION_OF_ASSET,"+
			"  USAGE_FROM,"+
			"  USAGE_TO,"+
			"  AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_MILEAGE "+
			" WHERE MODEL LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY MODEL ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		//----------------------ID      :Holiday Process -----------------------------------//
		//----------------------Purpose :Holiday Help -------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :28-07-2006--------------------------------------------------------//
		
		m_help_TXT_HOLIDAY_DATE_sql=
			
			" SELECT L.NO ,TO_CHAR(L.HOLIDAY_DATE,'DD-MM-YYYY') AS HOLIDAY_DATE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.HOLIDAY_DATE,P.DESCRIPTION"+
			" FROM( "+ 
			" SELECT "+
			"	HOLIDAY_DATE,"+
			"	DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_HOLIDAY "+
			" WHERE (TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY') LIKE ('%"+m_vector.elementAt(0)+"%') OR  UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY HOLIDAY_DATE DESC"+ //DESC Added by Chandana on 24/04/2007 
			" ORDER BY HOLIDAY_DATE ASC "+//ADDED BY DELANJALI ON 2007-08-29
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_HOLIDAY_DESC_sql=
			" SELECT L.NO ,TO_CHAR(L.HOLIDAY_DATE,'DD-MM-YYYY') AS HOLIDAY_DATE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.HOLIDAY_DATE,P.DESCRIPTION"+
			" FROM( "+ 
			" SELECT "+
			"	HOLIDAY_DATE,"+
			"	DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_HOLIDAY "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      :1.74 Early termination Charge Process -----------------------------------//
		//----------------------Purpose :Early Termination  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
		
		m_help_TXT_TERMINATION_TYPE_sql=
			
			" SELECT L.NO ,L.TERMINATION_TYPE,L.DESCRIPTION,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TERMINATION_TYPE,P.DESCRIPTION,P.AMOUNT"+
			" FROM( "+ 
			" SELECT "+
			" TERMINATION_TYPE, "+
			" DESCRIPTION, "+
			" AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "+
			" WHERE (UPPER(TERMINATION_TYPE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY TERMINATION_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_TERMINATION_DESC_sql=
			" SELECT L.NO ,L.TERMINATION_TYPE,L.DESCRIPTION,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TERMINATION_TYPE,P.DESCRIPTION,P.AMOUNT"+
			" FROM( "+ 
			" SELECT "+
			" TERMINATION_TYPE, "+
			" DESCRIPTION, "+
			" AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "+
			" WHERE UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Assest Usage  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
		
		m_help_TXT_USAGE_TYPE_sql=
			
			" SELECT L.NO ,L.USAGE_TYPE USAGE_TYPE_CODE ,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USAGE_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" USAGE_TYPE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
			" WHERE (USAGE_TYPE LIKE ('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE ('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY USAGE_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_USAGE_DESC_sql=
			
			" SELECT L.NO ,L.USAGE_TYPE USAGE_TYPE_CODE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USAGE_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" USAGE_TYPE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
			" WHERE DESCRIPTION LIKE ('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Repayment Type  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_REPAYMENT_TYPE_sql=
			
			" SELECT L.NO ,L.REPAYMENT_TYPE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REPAYMENT_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" REPAYMENT_TYPE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_METHOD "+
			" WHERE (REPAYMENT_TYPE LIKE ('%"+m_vector.elementAt(0)+"%') OR DESCRIPTION LIKE ('%"+m_vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY REPAYMENT_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_REPAYMENT_DESC_sql=
			
			" SELECT L.NO ,L.REPAYMENT_TYPE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REPAYMENT_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" REPAYMENT_TYPE, "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_METHOD "+
			" WHERE DESCRIPTION LIKE ('%"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Sub Product  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
		
		
		m_help_TXT_SUB_PRODUCT_CODE_sql=
			
			" SELECT L.NO ,L.SUB_PRODUCT_CODE,L.DESCRIPTION,L.PRODUCT_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_PRODUCT_CODE,P.DESCRIPTION,P.PRODUCT_CODE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" SUB_PRODUCT_CODE, "+
			" DESCRIPTION, "+
			" PRODUCT_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_PRODUCT "+
			" WHERE SUB_PRODUCT_CODE LIKE ('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SUB_PRODUCT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------ ID        : 1.58 Vendor Creation Process ----------------------------------
    --------------------Purpose    : Vendor Code Help ----------------------------------------------
    ------------------- Added By   : Delanjali------------------------------------------------------
    -------------------- Date      : 31-07-2006---------------------------------------------------------*/
		
		
		m_help_TXT_VEN_CODE_sql=
			" SELECT L.NO ,L.BRANCH,L.LOCATION_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS,L.CITY_CODE,L.ACTIVE_STATUS,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH,P.LOCATION_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS,P.CITY_CODE,P.ACTIVE_STATUS,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" BRANCH,"+
			" LOCATION_CODE,"+
			" TITLE,"+
			" FIRST_NAME,"+
			" LAST_NAME,"+
			" ID_NO,"+
			" ADDRESS,"+
			" CITY_CODE,"+
			" ACTIVE_STATUS, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION "+
			" WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"	ORDER BY BRANCH ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		/*m_help_TXT_VEN_CODE_sql=
    " SELECT L.NO,L.VENDOR_CODE,L.NAME,L.CATEGORY,L.TYPE,L.DEFAULT_VALUE,L.BRANCH,L.LOCATION_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,"+
    " L.ID_NO,L.ADDRESS,L.CITY_CODE,L.DEFAULT_VALUE,L.VENDOR_CODE,L.BRANCH_CODE,L.TEL_NO,L.FAX_NO,L.COL_609"+
    " FROM  "+
    " (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME,P.CATEGORY,P.TYPE,P.DEFAULT_VALUE,P.BRANCH,P.LOCATION_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,"+
    " P.ID_NO,P.ADDRESS,P.CITY_CODE,P.DEFAULT_VALUE,P.VENDOR_CODE,P.BRANCH_CODE,P.TEL_NO,P.FAX_NO,P.COL_609"+
    " FROM( "+ 
    " SELECT "+
    " A.VENDOR_CODE,"+
    "	A.NAME,"+
    " A.CATEGORY,"+
    " A.TYPE,"+
    " A.DEFAULT_VALUE,"+
    " B.BRANCH,"+
    " B.LOCATION_CODE,"+
    " B.TITLE,"+
    " B.FIRST_NAME,"+
    " B.LAST_NAME,"+
    " B.ID_NO,"+
    " B.ADDRESS,"+
    " B.CITY_CODE,"+
    " B.DEFAULT_VALUE,"+
    " C.VENDOR_CODE,"+
    " C.BRANCH_CODE,"+
    " C.TEL_NO,"+
    " C.FAX_NO,"+
    " C.COL_609"+
        " FROM LAKDL.AF_CO_MAS_VENDORS A,LAKDL.AF_CO_MAS_VENDOR_LOCATION B,LAKDL.AF_CO_MAS_VENDOR_LOC_CONTACT C"+
    " WHERE A.VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
    " AND A.VENDOR_CODE=B.VENDOR_CODE"+
    " AND A.VENDOR_CODE=C.VENDOR_CODE"+
    "  )P)L  "+
    " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";*/
		
		
		/*------------------ ID        : 1.58 Vendor Creation Process ----------------------------------
        --------------------Purpose    : Vendor Code Help ----------------------------------------------
        ------------------- Added By   : Delanjali------------------------------------------------------
        -------------------- Date      : 31-07-2006---------------------------------------------------------*/
		
		
		
		m_help_TXT_BRANCH_sql=
			" SELECT L.NO ,L.BRANCH_CODE,L.TEL_NO,L.FAX_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.TEL_NO,P.FAX_NO"+
			" FROM( "+ 
			" SELECT "+
			" BRANCH_CODE,"+
			" TEL_NO,"+
			" FAX_NO"+
			" FROM "+m_schema_name+".AF_CO_MAS_VENDOR_LOC_CONTACT "+
			" WHERE VENDOR_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND BRANCH_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------ ID        : Process Stage Creation Process ----------------------------------
        --------------------Purpose    : Stage Code Help ----------------------------------------------
        ------------------- Added By   : Nuwan De Silva------------------------------------------------------
        -------------------- Date      : 02-08-2006---------------------------------------------------------*/
		
		
		m_help_TXT_STAGE_CODE_sql=
			
			" SELECT L.NO,L.STAGE_CODE,L.DESCRIPTION,L.DIVISION_CODE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STAGE_CODE,P.DESCRIPTION,P.DIVISION_CODE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" STAGE_CODE, "+
			" DESCRIPTION, "+
			" DIVISION_CODE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_PROCESS_STAGE "+
			" WHERE STAGE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------ ID        : User Screen Creation Process ----------------------------------
        --------------------Purpose    : Screen Help ----------------------------------------------
        ------------------- Added By   : Nuwan De Silva------------------------------------------------------
        -------------------- Date      : 02-08-2006---------------------------------------------------------*/
		
		
		m_help_TXT_SCREEN_NAME_sql=
			" SELECT L.NO,L.SCREEN_NAME,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE ,NVL(L.DIVISION_SUB_CODE,'N/A') AS DIVISION_SUB_CODE ,NVL(L.OPTION_NAME,'N/A') AS OPTION_NAME ,NVL(L.SUB_OPTION1,'N/A') AS SUB_OPTION1 , "+
			"	NVL(L.SUB_OPTION2,'N/A') AS SUB_OPTION2 ,NVL(L.SUB_OPTION3,'N/A') AS SUB_OPTION3 ,NVL(L.SUB_OPTION4,'N/A') AS SUB_OPTION4 ,NVL(L.ROW_ID,0) AS ROW_ID ,NVL(L.SCREEN_URL,'N/A') AS SCREEN_URL ,L.DISPLAY_STATUS,NVL(L.SCREEN_LEVEL,0) AS SCREEN_LEVEL , "+
			" L.SUB_OPTION_STATUS,NVL(L.DISPLAY_NAME,'N/A') AS DISPLAY_NAME ,NVL(L.OPTION_ID,0) AS OPTION_ID  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCREEN_NAME,P.DIVISION_CODE,P.DIVISION_SUB_CODE,P.OPTION_NAME,P.SUB_OPTION1, "+
			" P.SUB_OPTION2,P.SUB_OPTION3,P.SUB_OPTION4,P.ROW_ID,P.SCREEN_URL,P.DISPLAY_STATUS,P.SCREEN_LEVEL, "+
			" P.SUB_OPTION_STATUS,P.DISPLAY_NAME,P.OPTION_ID "+
			" FROM( "+ 
			" SELECT "+
			" SCREEN_NAME, "+
			" DIVISION_CODE, "+
			" DIVISION_SUB_CODE, "+
			" OPTION_NAME, "+
			" SUB_OPTION1, "+
			" SUB_OPTION2, "+
			" SUB_OPTION3, "+
			" SUB_OPTION4, "+
			" ROW_ID, "+
			" SCREEN_URL, "+
			" DISPLAY_STATUS, "+
			" SCREEN_LEVEL, "+
			" SUB_OPTION_STATUS, "+
			" DISPLAY_NAME, "+
			" OPTION_ID "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE (SCREEN_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR DISPLAY_NAME LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_ASSET_ID_sql=
			
			" SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO"+
			" FROM( "+ 
			" SELECT "+
			" ASSET_ID, "+
			" REG_NO, "+
			" REG_DATE, "+
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
		
		
		m_help_TXT_ASSET_ID_sql2=
			
			" SELECT L.NO,L.ASSET_ID,/* L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, */L.MODEL_CODE,L.SUB_MODEL_CODE,/* L.PRICING_NO, */L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,/*L.ADDRESS,L.CITY_CODE,L.PERIOD,*/L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,/* P.REG_NO,P.REG_DATE, */P.MODEL_CODE,P.SUB_MODEL_CODE,/* P.PRICING_NO, */P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,/*P.ADDRESS,P.CITY_CODE,P.PERIOD,*/P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE"+
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
			//" PERIOD, "+
			" APPLICATION_NO, "+
			" "+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE,"+
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE"+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
			" WHERE ASSET_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND APPLICATION_NO = '"+m_vector.elementAt(1)+"'"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";			
		/*------------------ ID        : Performa Invoice Process ----------------------------------
            --------------------Purpose    : Invoice no Help ----------------------------------------------
          ------------------- Added By   : Nuwan De Silva------------------------------------------------------
          -------------------- Date      : 07-08-2006---------------------------------------------------------*/
		
		
		m_help_TXT_INVOICE_NO_sql=
			
			" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE"+
			" FROM( "+ 
			" SELECT "+
			" INVOICE_NO, "+
			" APPLICATION_NO, "+
			" ASSET_ID, "+
			" ENGINE_NO, "+
			" CHASSIS_NO, "+
			" REG_NO, "+
			" REG_DATE, "+
			" PRICING_NO, "+
			" SUB_MODEL_CODE, "+
			" COLOUR, "+
			" SEATING_CAPACITY, "+
			" NET_PRICE, "+
			" VAT, "+
			" TOTAL_AMOUNT, "+
			" TO_BE_DELIVERD_TO, "+
			" VALUE, "+
			" CURR_CODE, "+
			" MODEL_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
			" WHERE INVOICE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//added by nuwan de silva on 10-02-2008
		m_help_sub_charges_help_liability=
			" SELECT L.NO ,L.SUB_TYPE_CODE,L.TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE,L.MAINTENANCE_STATUS,L.CHARGE_TYPE,L.ACCOUNT_TYPE "+ //modified by nuwan de silva on 04-12-2007
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TYPE_CODE,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.MAINTENANCE_STATUS,P.CHARGE_TYPE,P.ACCOUNT_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_TYPE_CODE,"+
			" TYPE_CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" MAINTENANCE_STATUS, "+
			" CHARGE_TYPE, "+
			" NVL(ACCOUNT_TYPE,'-') ACCOUNT_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			" WHERE SUB_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" AND ACCOUNT_TYPE='L' "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//added by nuwan de silva on 10-02-2008
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
			" nvl(PAYEE_VAT_REG_NO,0)PAYEE_VAT_REG_NO, "+
			" nvl(PAYEE_WHT,0)PAYEE_WHT  "+
			" FROM "+m_schema_name+".AF_CR_PRO_SUB_CHAR_PAYEE_REF "+
			" WHERE (upper(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" or   upper(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND   ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+//ADDED MILINDA 2014-01-20
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		/*------------------ ID        : 1.58 Valuation Process ----------------------------------
        --------------------Purpose    : Valuation Code Help ----------------------------------------------
        ------------------- Added By   : Delanjali------------------------------------------------------
        -------------------- Date      : 06-08-2006---------------------------------------------------------*/
		m_help_TXT_VALUATION_NO1_sql=
			" SELECT L.NO ,L.VALUATION_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO,L.COLOUR,"+
			" L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE ,L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING,"+
			" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,"+
			"	P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,"+
			" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE "+
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
			" "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			//" FROM LAKDL.AF_CO_PRO_APP_VALUATION "+
			" WHERE VALUATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND APPLICATION_NO= '"+m_vector.elementAt(1)+"'  AND ACTIVE_STATUS ='"+m_vector.elementAt(2)+"' "+
			"	ORDER BY VALUATION_NO ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_VALUATION_DET_sql=
			" SELECT L.NO ,L.VALUATION_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO,L.COLOUR,"+
			" L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY')AS VALUATION_DATE,L.VALUE,L.TYPE_OF_BODY,TO_CHAR(L.DATE_OF_REG,'DD-MM-YYYY') AS DATE_OF_REG,L.METER_READING,"+
			" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.ITEM_CAT_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,"+
			"	P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,"+
			" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.ITEM_CAT_CODE "+
			" FROM( "+ 
			" SELECT "+
			" X.VALUATION_NO,"+
			"	X.ASSET_ID,"+
			"	SUB_MODEL_CODE,"+
			"	REG_NO,"+
			"	ENGINE_NO,"+
			"	CHASSIS_NO,"+
			"	COLOUR,"+
			"	X.MODEL_CODE,"+
			"	NOTES,"+
			"	REMARKS,"+
			"	VALUATION_DATE,"+
			"	VALUE,"+
			"	TYPE_OF_BODY,"+
			"	DATE_OF_REG,"+
			"	METER_READING,"+
			"	X.ACTIVE_STATUS,"+
			"	GENERAL_INDEX,"+
			"	SEATING_CAPACITY,"+
			"	NO_OF_CYLINDERS,"+
			" ITEM_CAT_CODE"+
			//" FROM LAKDL.AF_CO_PRO_APP_VALUATION "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION X,"+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_MAKE B,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY C"+
			" WHERE x.VALUATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND x.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" AND A.MAKE_CODE=B.MAKE_CODE"+
			"	AND B.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
			"	AND A.MODEL_CODE=X.MODEL_CODE"+
			"	AND A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')"+
			"	ORDER BY VALUATION_NO ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_view_sql=
			" SELECT L.NO ,L.VALUATION_NO VALUATION ,L.ASSET_ID,L.DESC1 MODEL,L.DESC2 SUBMODEL,L.VALUE,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY')AS VALUATIONDATE,L.NOTES,L.REMARKS,L.COLOUR,L.TYPE_OF_BODY AS TYPE,L.METER_READING READING,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO"+
			" ,TO_CHAR(L.DATE_OF_REG,'DD-MM-YYYY') AS DATE_OF_REG,"+
			" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.ITEM_CAT_CODE,L.DESCRIPTION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.DESC1,P.DESC2,P.VALUE,P.VALUATION_DATE,P.NOTES,P.REMARKS,P.COLOUR,P.TYPE_OF_BODY,P.METER_READING,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO"+
			"	,P.DATE_OF_REG,"+
			" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.ITEM_CAT_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" X.VALUATION_NO,"+
			"	X.ASSET_ID,"+
			//"	X.MODEL_CODE,"+
			"	A.DESCRIPTION DESC1,"+
			"	E.DESCRIPTION DESC2,"+
			//"	SUB_MODEL_CODE,"+
			"	VALUE,"+
			"	VALUATION_DATE,"+
			"	NOTES,"+
			"	REMARKS,"+
			"	COLOUR,"+
			"	TYPE_OF_BODY,"+
			"	METER_READING,"+
			"	REG_NO,"+
			"	ENGINE_NO,"+
			"	CHASSIS_NO,"+
			"	DATE_OF_REG,"+
			"	X.ACTIVE_STATUS,"+
			"	GENERAL_INDEX,"+
			"	SEATING_CAPACITY,"+
			"	NO_OF_CYLINDERS,"+
			" D.ITEM_CAT_CODE,"+
			" D.DESCRIPTION"+
			//" FROM LAKDL.AF_CO_PRO_APP_VALUATION "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION X,"+m_schema_name+".AF_CO_MAS_MODEL A,"+m_schema_name+".AF_CO_MAS_MAKE B,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY C,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY D,"+m_schema_name+".AF_CO_MAS_SUB_MODLE E"+
			//" WHERE x.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" WHERE A.MAKE_CODE=B.MAKE_CODE"+
			"	AND B.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
			"	AND D.ITEM_CAT_CODE=C.ITEM_CAT_CODE"+	
			"	AND A.MODEL_CODE=X.MODEL_CODE"+
			"	AND E.SUB_CODE=X.SUB_MODEL_CODE"+
			"	AND A.MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"	ORDER BY VALUATION_NO ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_VALUATION_NO_sql=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE"+
			" FROM( "+ 
			" SELECT "+
			" VALUER_CODE,"+
			" FIRST_NAME,"+
			" LAST_NAME,"+
			" ADDRESS,"+
			" ADDRESS2,"+
			" CITY_CODE"+
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE VALUER_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_MODEL_CODE_sql1=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE,L.ITEM_SUB_CAT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE,P.ITEM_SUB_CAT "+
			" FROM( "+ 
			" SELECT "+
			" MODEL_CODE,"+
			" DESCRIPTION, "+
			" MAKE_CODE, "+
			" FUEL_TYPE, "+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE, "+
			" ITEM_SUB_CAT "+
			" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
			" WHERE MAKE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') AND MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_SUB_MODEL_CODE_sql1=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE ,"+
			" MODEL_CODE ,"+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_TXT_SUB_MODEL_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE ,"+
			"	MODEL_CODE,"+
			"	DESCRIPTION,"+
			"	ENGINE_CAPACITY,"+
			"	OPTION_TYPE,"+
			"	COUNTRY_CODE,"+
			"	YEAR_OF_MANUFACTURE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_ASSET_ID_sql1=
			
			" SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO"+
			" FROM( "+ 
			" SELECT "+
			" ASSET_ID, "+
			" REG_NO, "+
			" REG_DATE, "+
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
		
		m_help_TXT_PRICING_NO_sql=
			
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE ,"+
			"	MODEL_CODE,"+
			"	DESCRIPTION,"+
			"	ENGINE_CAPACITY,"+
			"	OPTION_TYPE,"+
			"	COUNTRY_CODE,"+
			"	YEAR_OF_MANUFACTURE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE SUB_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID         : 1.11 Employee Creation Process-----------------------------------------
            --------------------Purpose    : Prevnt Entering Duplicate Names Help ----------------------------------------------
          ------------------- Added By   :Yohan Gunarathna------------------------------------------------------
          --------------------  Date     :22-09-2006---------------------------------------------------------*/
		
		m_help_TXT_EMP_CODE_NAME_sql=
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
			" WHERE  UPPER(FIRST_NAME) = UPPER('"+m_vector.elementAt(0)+"')  AND  UPPER(LAST_NAME) = UPPER('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//----------------------ID      :1.37 RMV Agent Creation Process----------------------------------//
		//----------------------Purpose :RMV Name help-------------------------------------------------//
		//----------------------Name    :Yohan----------------------------------------------------------//
		//----------------------Date    :22-09-2006--------------------------------------------------------//
		
		
		m_help_TXT_RMV_AGENT_CODE_NAME_sql=
			
			" SELECT L.NO ,L.RMV_AGENT_CODE,L.NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE, "+
			" L.MOBILE_NO,L.TEL_NO,L.MONTHLY_FEE,L.FEE_FOR_CASE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RMV_AGENT_CODE,P.NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE, "+
			" P.MOBILE_NO,P.TEL_NO,P.MONTHLY_FEE,P.FEE_FOR_CASE,P.DEFAULT_VALUE  "+
			" FROM( "+ 
			" SELECT "+
			" RMV_AGENT_CODE,"+
			" NAME, "+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE, "+
			" MOBILE_NO,"+
			" TEL_NO,"+
			" MONTHLY_FEE,"+
			" FEE_FOR_CASE,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_RMV_AGENTS "+
			" WHERE UPPER(NAME) = UPPER('"+m_vector.elementAt(0)+"') "+
			" ORDER BY RMV_AGENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";			
		
		
		/*------------------ ID       : 1.19 Condition of Asset Creation Process----------------------------------
    --------------------Purpose    : Asset Type Code Description Help ----------------------------------------------
    ------------------- Added By   : Yohan Gunarathna------------------------------------------------------
    -------------------- Date      : 22-09-2006---------------------------------------------------------*/	
		
		m_help_TXT_CODE_DESC_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" CODE,"+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
			" WHERE UPPER(DESCRIPTION) = UPPER('"+m_vector.elementAt(0)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";		
		
		
		/*------------------ ID       : 1.29 Team Creation Process----------------------------------
        --------------------Purpose   : Prevent Entering Duplicate Teamd Description Help ----------------------------------------------
        ------------------- Added By  : Yohan Gunarathna------------------------------------------------------
        -------------------- Date     : 23-09-2006---------------------------------------------------------*/	
		
		m_help_TXT_TEAM_ID_DESC_sql=
			
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
			" WHERE  UPPER(TEAM_DESC) = UPPER('"+m_vector.elementAt(0)+"')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		/*------------------  ID       : 1.31 Income / Expense Type Creation Process-----------------------------------------
        --------------------Purpose    :Income Expence Description Help ----------------------------------------------
        ------------------- Added By   :Yohan Gunarathna------------------------------------------------------
        --------------------  Date     :25-09-2006---------------------------------------------------------*/
		
		m_help_TXT_I_E_CODE_sql_desc=
			" SELECT L.NO ,L.I_E_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.I_E_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" I_E_CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
			" WHERE UPPER(DESCRIPTION) =  UPPER('"+m_vector.elementAt(0)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID       : 1.47 Mileage Creation Process-----------------------------------------
        --------------------Purpose    : Model Code Help ----------------------------------------------
        ------------------- Added By   :delanjali------------------------------------------------------
        --------------------  Date     :26-09-2006---------------------------------------------------------*/
		
		m_help_TXT_SUB_MODEL_CODE_sql2=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE ,"+
			" MODEL_CODE ,"+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
			" WHERE (UPPER(SUB_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND MODEL_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
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
			"	       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE,INITCAP(L.CLIENT_LAST_NAME) "+
			" FROM  "+
			
			"(SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,"+
			"	       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,"+
			"	       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,"+
			"	       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,"+
			"        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,"+
			"        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,"+
			"	       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE,P.CLIENT_LAST_NAME "+
			
			
			
			" FROM "+ 
			" (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO, "+
			" NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS, "+
			" NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS, "+
			" NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY,"+
			" NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME, "+
			" NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS,"+
			" NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM, "+
			" NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON, "+
			" NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE,NVL(CLIENT_LAST_NAME,'-') CLIENT_LAST_NAME "+ //Modified By Nuwan De Silva --15-05-07-
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			
			" WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR " +
			"       UPPER(CLIENT_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       TEL_NO 			        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	      UPPER(EMAIL)        LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"       UPPER(ID_NO) 			  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+				
			" ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L "+		
			"WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";			
		
		
		
		//--------------------------------------------------------------------------------------------------------------		
		//--------------------------------------------------------------------------------------------------------------
		/*------------------  ID       :3.21 Credit Score Sub Evaluation Process-----------------------------------------
        --------------------Purpose    :Application Help ----------------------------------------------
        ------------------- Added By   :Delanjali------------------------------------------------------
        --------------------  Date     :12-11-2006---------------------------------------------------------*/
		
		m_help_cr_score_application_sql_1=
			" SELECT L.NO ,L.APPLICATION_NO,L.APPLICATION_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.APPLICATION_STATUS "+
			" FROM( "+ 
			" SELECT "+ 
			" APPLICATION_NO, "+ 
			" APPLICATION_STATUS "+ 
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
			" WHERE APPLICATION_STATUS=('"+m_vector.elementAt(1)+"') AND APPLICATION_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//----------process 		:Maintanance rate--------------------------------------------------------------------------------------------
		//----------modified by :Delanjali---------------------------------------------------------------------------------------------------
		//----------date				:16-01-2007--------------------------------------------------------------------------------------------------
		
		//Sub Model--------------------------------------------------------------------------------------------------------------------------	
		
		m_help_TXT_SUB_MODEL_CODE_sql_new=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" SUB_CODE,A.MODEL_CODE, "+
			" A.DESCRIPTION"+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE A ,"+m_schema_name+".AF_CO_MAS_MODEL B "+
			" WHERE UPPER(SUB_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+ 
			" AND UPPER(MAKE_CODE)=UPPER('"+m_vector.elementAt(0)+"') "+
			" AND A.MODEL_CODE=B.MODEL_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//Milage code------------------------------------------------------------------------------------------------------------------------	
		
		m_help_TXT_MILEAGE_CODE_sql_new=
			" SELECT L.NO ,L.MODEL,L.CONDITION_OF_ASSET,L.USAGE_FROM,L.USAGE_TO,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL,P.CONDITION_OF_ASSET,P.USAGE_FROM,P.USAGE_TO,P.AMOUNT "+
			" FROM( "+
			" SELECT "+
			"  MODEL,"+
			
			"  CONDITION_OF_ASSET,"+
			"  USAGE_FROM,"+
			"  USAGE_TO,"+
			"  AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_MILEAGE "+
			" WHERE UPPER(SUB_MODEL)=UPPER('"+m_vector.elementAt(0)+"') AND MODEL LIKE UPPER('%"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			" ORDER BY MODEL ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		//condition refernce screen------------------------------------------------------------------------------------------------------------------------	
		//condition code------------------------------------------------------------------------------------------------------------------------	
		//Delanjali----------------------------------------------------------------------------------------------------------------------------
		m_help_TXT_CONDITION_CODE_sql=	
			
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.INSERT_SCREEN,L.ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.INSERT_SCREEN,P.ACTIVE_STATUS"+
			" FROM( "+
			" SELECT "+
			" CODE, "+
			" DESCRIPTION, "+
			" INSERT_SCREEN, "+			
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_MK_CONDITIONS "+
			" WHERE UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') 	AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		//Lead Source screen----------------------------------------------------------------------------------------------------------------------	
		//Lead Source code------------------------------------------------------------------------------------------------------------------------	
		//Delanjali-------------------------------------------------------------------------------------------------------------------------------
		
		
		m_help_TXT_L_S_CODE_sql_new=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DEFAULT_VALUE,L.CREATED_DATE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.CREATED_DATE "+
			" FROM( "+ 
			" SELECT "+
			" CODE , "+
			" DESCRIPTION, "+
			" DEFAULT_VALUE,TO_CHAR(CREATED_DATE,'DD-MM-YYYY') as CREATED_DATE "+
			" FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE "+
			" WHERE (UPPER(CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//CR DB Narration screen----------------------------------------------------------------------------------------------------------------------	
		//Narration code------------------------------------------------------------------------------------------------------------------------------	
		//Delanjali-----------------------------------------------------------------------------------------------------------------------------------
		m_help_TXT_NARRATIONS_CODE_sql=
			"SELECT L.NO ,L.NARRATIONS_CODE,L.NARRATIONS,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
			"FROM "+
			"(SELECT ROWNUM NO ,P.NARRATIONS_CODE,P.NARRATIONS,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+
			"SELECT "+
			" NARRATIONS_CODE, "+
			" NARRATIONS, "+
			" ACTIVE_STATUS, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CR_DB_NARRATIONS  "+
			" WHERE (UPPER(NARRATIONS_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NARRATIONS) LIKE UPPER('%"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		//--purpose 		: licensee settlemnt screen refinment--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//--date    		: (2007-03-02)--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//--modified by :	delanjali--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
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
		
		
		
		m_help_TXT_ACCOUNT_CODE_sql_new=
			" SELECT L.NO,L.ACC_TYPE_CODE,L.ACC_TYPE_DESC,L.ACC_TYPE_CATEGORY, "+
			" L.ACC_BS_PL,L.ACC_NOTE,L.STATUS,L.DIVISION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACC_TYPE_CODE,P.ACC_TYPE_DESC,P.ACC_TYPE_CATEGORY, "+
			" P.ACC_BS_PL,P.ACC_NOTE,P.STATUS,P.DIVISION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" ACC_TYPE_CODE, "+
			" ACC_TYPE_DESC, "+
			" ACC_TYPE_CATEGORY, "+
			" ACC_BS_PL,ACC_NOTE, "+
			" STATUS,DIVISION_CODE "+
			//" FROM "+m_schema_name+".AF_CO_ACC_ACCOUNT_CODE "+ //Comment by Chandana for Ref No.645 on 26/08/2007
			" FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+ //Added by Chandana for Ref No.645 on 26/08/2007
			" WHERE (ACC_TYPE_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(ACC_TYPE_DESC) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//Added by Mahela on 08-05-2007
		//Purpose : CRIB No Help
		ClientSql =   "SELECT P.NO, P.CLIENT_CODE Client, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			
			"(SELECT CLIENT_CODE, FULL_NAME,SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) " +
			" 				AND GRIB_NO IS NULL AND "+
			" 				ACTIVE_STATUS=UPPER('Y') AND "+
			" 				(AF_CLIENT=UPPER('Y') OR "+
			" 				AF_GUARANTORS=UPPER('Y')) "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		//added by nuwan de silva 08-08-07									
		Client_code_help_client_creation = "SELECT P.NO, P.CLIENT_CODE Client,P.CLIENT_TYPE Type, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,CLIENT_TYPE, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			
			"(SELECT CLIENT_CODE,CLIENT_TYPE, FULL_NAME,SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					(CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (PRE_NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+ // added by udara 10-07-2019
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			" 				ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		//added by nuwan de silva 08-08-07									
		Client_code_help_client_creation_temp = "SELECT P.NO, P.CLIENT_CODE Client,P.CLIENT_TYPE Type, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,CLIENT_TYPE, FULL_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			
			"(SELECT CLIENT_CODE,CLIENT_TYPE, FULL_NAME,SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT_TM "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"					UPPER(CLIENT_CODE) LIKE ('%"+m_vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        (MOBILE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (TEL_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"         (NIC_NO)     LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) AND " +
			" 				ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')  "+
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+Start_Val+" AND P.NO<= "+End_Val+" ";		
		
		
		
		//Cheque return Narration screen----------------------------------------------------------------------------------------------------------------------	
		//Narration code------------------------------------------------------------------------------------------------------------------------------	
		//Delanjali-----------------------------------------------------------------------------------------------------------------------------------
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
		
		
		
		
		m_help_loan_facilities_assign_sql=	
			"SELECT L.NO ,L.loan_facility_no,L.ref_no,L.ref_name,L.ref_add1,L.ref_add2,L.ref_tel,L.ref_fax "+
			"FROM "+
			"(SELECT ROWNUM NO ,P.loan_facility_no,P.ref_no,P.ref_name,P.ref_add1,P.ref_add2,P.ref_tel,P.ref_fax "+
			" FROM( "+
			" SELECT a.loan_facility_no,a.ref_no,a.ref_name,a.ref_add1, "+
			" a.ref_add2, a.ref_tel, a.ref_fax "+
			" FROM   "+m_schema_name+".af_co_mas_loan_facilities a "+
			" WHERE  UPPER(a.loan_facility_no)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND    a.active_status='Y' "+
			" AND    a.loan_facility_no not in (SELECT DISTINCT loan_facility_no  FROM "+m_schema_name+".af_co_mas_loan_facili_assign ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_loan_facilities_assign_edit_sql=	
			"SELECT L.NO ,L.loan_facility_no,L.ref_no,L.ref_name,L.ref_add1,L.ref_add2,L.ref_tel,L.ref_fax "+
			"FROM "+
			"(SELECT ROWNUM NO ,P.loan_facility_no,P.ref_no,P.ref_name,P.ref_add1,P.ref_add2,P.ref_tel,P.ref_fax "+
			" FROM( "+
			" SELECT a.loan_facility_no,a.ref_no,a.ref_name,a.ref_add1, "+
			" a.ref_add2, a.ref_tel, a.ref_fax "+
			" FROM   "+m_schema_name+".af_co_mas_loan_facilities a "+
			" WHERE  UPPER(a.loan_facility_no)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" AND    a.active_status='Y' "+
			" AND    a.loan_facility_no in (SELECT DISTINCT loan_facility_no  FROM "+m_schema_name+".af_co_mas_loan_facili_assign ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		m_help_Fin_No_Sql =  //Added By SJ on 27-11-2008
			
			" SELECT P.NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME,NIC_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME ,NIC_NO"+
			" FROM "+
			" (SELECT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,B.FULL_NAME CLIENT_NAME ,B.NIC_NO "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE = B.CLIENT_CODE AND  "+
			" ( (A.FINANCE_NO)        LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			"   OR (A.APPLICATION_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.FULL_NAME)      LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR UPPER(B.TEL_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ 
			"   OR (A.CLIENT_CODE)    LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			"   OR (B.NIC_NO)         LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+ 
			"   AND A.FINANCE_NO IS NOT NULL "+
			"   ORDER BY A.FINANCE_NO DESC)) P "+		
			"   WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		
		// Added by Udara Somathilake on 05-05-2010
		m_help_CHANGE_CLIENT_sql=
			" SELECT L.NO,L.CLIENT_CODE,L.FULL_NAME,L.FINANCE_NO,L.APPLICATION_NO,L.DIVISION_CODE,L.CLIENT_TYPE,DECODE(L.FACTORING_TYPE,'C','CLIENT','D','DEBTOR','B','CLIENT OR DEBTOR') FACTORING_TYPE,L.ACTIVE_STATUS,L.CLIENT_DEC "+
			" FROM( "+
			" SELECT ROWNUM NO,P.CLIENT_CODE,P.FULL_NAME,P.FINANCE_NO,P.APPLICATION_NO,P.DIVISION_CODE,P.CLIENT_TYPE,P.FACTORING_TYPE,P.ACTIVE_STATUS,P.CLIENT_DEC "+
			" FROM( "+
			" SELECT A.CLIENT_CODE,A.FULL_NAME,B.FINANCE_NO,B.APPLICATION_NO,B.DIVISION_CODE,A.CLIENT_TYPE,'C' FACTORING_TYPE,A.ACTIVE_STATUS,'LEASING' CLIENT_DEC "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE A.CLIENT_CODE = B.CLIENT_CODE  "+
			" AND A.ACTIVE_STATUS='Y' "+
			" AND B.APPLICATION_STATUS = 'ACTIVATED' "+
			" AND ( "+
			"    (A.CLIENT_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%')   "+
			" OR UPPER(A.FULL_NAME)   LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" OR (B.FINANCE_NO)  LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" )"+
			" )P "+
			" ORDER BY P.CLIENT_DEC,P.FULL_NAME "+
			" )L "+
			" WHERE L.NO>= "+Start_Val+" AND L.NO<= "+End_Val+" ";
		
		// End by Udara Somathilake on 05-05-2010
		
		
		
		
		
		m_help_Assign_Mkt_Col_Collection_Officer_New_sql=
			// m_help_TXT_EMP_CODE_2_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
			//" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
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
			" ID_NO ,"+
			" NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
			" EMP_DOB ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			// " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND EMP_CODE NOT IN ( " +
			"     SELECT DISTINCT COLLECTION_OFFICER " +
			"     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
			" ) " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_Assign_Mkt_Col_Collection_Officer_Edit_sql=
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
		
		
		m_help_Assign_Mkt_Col_Marketing_Officer_New_sql=
			// m_help_TXT_EMP_CODE_2_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
			//" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
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
			" ID_NO ,"+
			" NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
			" EMP_DOB ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			// " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND EMP_CODE NOT IN ( " +
			"     SELECT MARKETING_OFFICER " +
			"     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
			" ) " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql=
			// m_help_TXT_EMP_CODE_2_sql=
			" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,L.USER_ID,L.EMP_DOB,L.EMP_DO_JOIN,L.EMP_DO_RESIGN "+
			//" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.USER_ID,P.EMP_DOB,P.EMP_DO_JOIN,P.EMP_DO_RESIGN "+
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
			" ID_NO ,"+
			" NVL("+m_schema_name+".AF_CO_GET_USER(EMP_CODE),'-' ) USER_ID, "+
			" EMP_DOB ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_JOIN ,"+ //Added by Prabash on 19-08-2011
			" EMP_DO_RESIGN "+ //Added by Prabash on 19-08-2011
			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
			// " WHERE ( EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" WHERE ( EMP_CODE LIKE UPPER('%"+m_vector.elementAt(0)+"%')  OR UPPER(FIRST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(LAST_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" AND EMP_CODE NOT IN ( " +
			"     SELECT MARKETING_OFFICER " +
			"     FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP " +
			"     WHERE  COLLECTION_OFFICER <> '"+m_vector.elementAt(1)+"' " +
			" ) " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		// added by udara on 09-05-2013
		ClientSql_new =     "SELECT P.NO, P.FINANCE_NO,P.FULL_NAME Name, P.REG_NO,P.ADDRESS1 , P.ADDRESS2 ,P.CITY_NAME ,ACTIVE_STATUS Status,P.CLIENT_CODE Client "+
			"FROM "+
			"(SELECT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE  "+ //added by nuwan de silva 25-07-07
			"    FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"       WHERE "+
			"            A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			"            B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"    (   (A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(1)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"          UPPER(A.CITY_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"        (TEL_NO)    LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"          UPPER(EMAIL)     LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"        (NIC_NO)    LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+
			"          UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR " +
			//"        FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%') OR "+ 
			
			"        c.REG_NO   LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			"        AND FINANCE_NO IS NOT NULL "+ // added by udara on 28-05-2013
			"        AND FINANCE_NO LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			" ORDER BY FULL_NAME )) P "+        
			" WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
		
		
		// added by udara 06-03-2014
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
			" AND   (UPPER(PAYEE_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+
			" OR    UPPER(PAYEE_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%')) "+
			" AND ACTIVE_STATUS = 'Y'  "+ // added by udara 03-11-2015
			" ORDER BY PAYEE_CODE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		// end by udara 06-03-2014
		
		// added by udara 11-07-2018
		m_help_TXT_DOC_ID_sql=
			
			" SELECT L.NO ,L.DOCUMENT_ID,L.DOCUMENT_NAME, L.LETTER_ID, L.TRANSACTION_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DOCUMENT_ID,P.DOCUMENT_NAME, P.LETTER_ID, P.TRANSACTION_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" DOCUMENT_ID, "+
			" DOCUMENT_NAME, "+ 
			" LETTER_ID, "+
			" TRANSACTION_TYPE "+
			" FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS "+
			" WHERE (DOCUMENT_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER(DOCUMENT_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_LETTER_ID_sql=
			
			" SELECT L.NO ,L.LETTER_ID,L.LETTER_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LETTER_ID,P.LETTER_NAME "+
			" FROM( "+ 
			" SELECT "+
			" LETTER_ID, "+
			" LETTER_NAME "+
			" FROM "+m_schema_name+".AF_RL_MAS_RECOVERY_LETTER_TYPE "+
			" WHERE (LETTER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER(LETTER_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		m_help_TXT_DOC_ID_sql_new=
			
			" SELECT L.NO ,L.DOCUMENT_ID,L.DOCUMENT_NAME, L.LETTER_ID, L.TRANSACTION_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DOCUMENT_ID,P.DOCUMENT_NAME, P.LETTER_ID, P.TRANSACTION_TYPE "+
			" FROM( "+ 
			" SELECT "+
			" DOCUMENT_ID, "+
			" DOCUMENT_NAME, "+ 
			" LETTER_ID, "+
			" TRANSACTION_TYPE "+
			" FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DOCS "+
			" WHERE (DOCUMENT_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR  UPPER(DOCUMENT_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND TRANSACTION_TYPE = ('"+m_vector.elementAt(2)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// added by udara to fix file curruption 10-07-2019
		m_help_TXT_MAIN_TRAN_CODE_sql = 
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM   (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			"      FROM(  SELECT  TRAN_CODE ,  DESCRIPTION,  DEFAULT_VALUE "+
			"              FROM " + m_schema_name + ".AF_CO_MAS_MAIN_TRAN_TYPE " +
						   " WHERE (TRAN_CODE LIKE UPPER('%" + m_vector.elementAt(0) + "%') OR UPPER(DESCRIPTION) LIKE UPPER('%" + m_vector.elementAt(0) + "%')) "+
						   " AND ACTIVE_STATUS=('" + m_vector.elementAt(1) + "') " + "  )P)L  " +
							" WHERE L.NO>=  " + Start_Val + "  AND L.NO<=  " + End_Val + " ";
		
		
		m_help_TXT_TRAN_CODE_sql_new=
			" SELECT L.NO ,L.LETTER_ID,L.TRANSACTION_TYPE,L.DEPENDANT_LETTER_ID,L.DURATION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LETTER_ID,P.TRANSACTION_TYPE,P.DEPENDANT_LETTER_ID,P.DURATION "+
			" FROM( "+ 
			" SELECT "+
			" LETTER_ID , "+
			" TRANSACTION_TYPE, "+
			" DEPENDANT_LETTER_ID, "+
			" DURATION "+
			" FROM "+m_schema_name+".AF_RL_RECOVERY_LETTER_DURATION "+
			" WHERE (LETTER_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(TRANSACTION_TYPE) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY TRANSACTION_TYPE"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		// end by udara 11-07-2018
		
		
		/***********DASH HELPS************/
		m_dash=" SELECT L.NO,L.GRAPH_TYPE_ID,L.GRAPH_TYPE,L.GRAPH_DESCRIPTION,L.GRAPH_SWF_LOCATION,L.DRAW_EXC_SECTION "+
			" FROM( "+
			" SELECT ROWNUM NO,P.GRAPH_TYPE_ID,P.GRAPH_TYPE,P.GRAPH_DESCRIPTION,P.GRAPH_SWF_LOCATION,P.DRAW_EXC_SECTION "+
			" FROM( "+
			" SELECT "+
			" GRAPH_TYPE_ID,"+
			" GRAPH_TYPE,"+
			" GRAPH_DESCRIPTION, "+
			" GRAPH_SWF_LOCATION, "+
			" DRAW_EXC_SECTION "+
			" FROM "+m_schema_name+".DH_REF_GRAPH_TYPES "+
			" WHERE (GRAPH_TYPE_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR GRAPH_TYPE LIKE UPPER('%"+m_vector.elementAt(0)+"%') ) "+
			" )P)L "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";
			
			
			
			
			//------------------------------------------------------------------------------------------------------------------------------------------------
			Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	}
	
}
