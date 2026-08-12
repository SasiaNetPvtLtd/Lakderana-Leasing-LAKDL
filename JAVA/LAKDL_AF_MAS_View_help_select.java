import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_MAS_View_help_select  {  

	public Object Ret_Object = new Object();
	//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();  //added by nuwan de silva 20-07-07
	
	String m_schema_name = m_sn_methods.schema_name.trim();

	//------------------------------------------------------------------------
	
	
	public String m_help_TXT_BRANCH_CODE_1_sql= "";
	public String m_help_TXT_BRANCH_CODE_1_sql_Header= "System Administration - Licencee Settlement";
	
	public String m_view_TXT_INVOICE_NO_sql= "";
	public String m_view_TXT_INVOICE_NO_sql_Header= "Application Process - Invoice Number ";
	
	public String m_help_TXT_ASSET_ID_sql= "";
	public String m_help_TXT_ASSET_ID_sql_Header= "Application Process - Asset ID ";
	
	
	public String m_view_TXT_SCREEN_NAME_sql= "";
	public String m_view_TXT_SCREEN_NAME_sql_Header= "System Administration - User Screen";
	
	
	public String m_view_TXT_STAGE_CODE_sql= "";
	public String m_view_TXT_STAGE_CODE_sql_Header= "System Administration - Process Stage";
	
	public String m_view_TXT_SUB_PRODUCT_CODE_sql= "";
	public String m_view_TXT_SUB_PRODUCT_CODE_sql_Header= "System Administration - Sub Product";
	
	
	public String m_view_TXT_REPAYMENT_TYPE_sql= "";
	public String m_view_TXT_REPAYMENT_TYPE_sql_Header= "System Administration - Assest Usage";
		
	public String m_view_TXT_USAGE_TYPE_sql= "";
	public String m_view_TXT_USAGE_TYPE_sql_Header= "System Administration - Assest Usage";
	
	public String m_view_TXT_TERMINATION_TYPE_sql= "";
	public String m_view_TXT_TERMINATION_TYPE_sql_Header= "System Administration - Early Termination Charge";
	
	public String m_help_TXT_TEAM_ID_team_sql= "";
	public String m_help_TXT_TEAM_ID_team_sql_Header= "System Administration - Team Assign";
	
	
	public String m_view_TXT_TRN_SUB_TYPE_sql= "";
	public String m_view_TXT_TRN_SUB_TYPE_sql_Header= "System Administration - Transaction Sub Type";
		
	public String m_view_TXT_TRAN_CODE_sql= "";
	public String m_view_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
	
	public String m_view_TXT_SOURCE_CODE_sql= "";
	public String m_view_TXT_SOURCE_CODE_sql_Header= "System Administration - Lead Source Category";
	
	public String m_view_TXT_VEHICLE_NO_sql= "";
	public String m_view_TXT_VEHICLE_NO_sql_Header= "System Administration - Missing Vehicle";
	
	public String m_view_TXT_OPTION_CODE_sql= "";
	public String m_view_TXT_OPTION_CODE_sql_Header= "System Administration - Option";
	//added by Prabash 19-05-2011--------------------------------**
	public String m_view_TXT_TAX_CODE_sql= "";
	public String m_view_TXT_TAX_CODE_sql_Header= "System Administration - TAX";
	//-----------------------------------------------------------**
	public String m_view_TXT_MODEL_sql= "";
	public String m_view_TXT_MODEL_sql_Header= "System Administration - Mileage";
	
	public String m_view_TXT_app_sql= "";
	public String m_view_TXT_app_sql_Header= "System Administration - Credit Officer";//milinda
	
	public String m_view_TXT_LAWYER_CODE_sql= "";
	public String m_view_TXT_LAWYER_CODE_sql_Header= "System Administration - Lawyer";
	
	public String m_view_TXT_SUB_M_CODE_sql= "";
	public String m_view_TXT_SUB_M_CODE_sql_Header= "System Administration - Sub Model";
	
	public String m_view_TXT_USER_ID_Authorization_sql= "";
	public String m_view_TXT_USER_ID_Authorization_sql_Header= "System Administration - Authorization";

	public String m_view_TXT_VALUER_CODE_sql= "";
	public String m_view_TXT_VALUER_CODE_sql_Header= "System Administration - Valuer";

	public String m_view_TXT_RATING_CODE_sql= "";
	public String m_view_TXT_RATING_CODE_sql_Header= "System Administration - Score Rating";

	public String m_help_TXT_L_S_CODE_sql= "";
	public String m_help_TXT_L_S_CODE_sql_Header= "System Administration - Lead Source";

	
	public String m_view_TXT_INQ_CODE_sql= "";
	public String m_view_TXT_INQ_CODE_sql_Header= "System Administration - Inquiry Status";
	
  public String m_view_TXT_NATIONALITY_CODE_sql= "";
	public String m_view_TXT_NATIONALITY_CODE_sql_Header= "System Administration - Nationality";
	
	
	public String m_view_TXT_I_T_CODE_sql= "";
	public String m_view_TXT_I_T_CODE_sql_Header= "System Administration - Interest Type";
	
	public String m_view_TXT_I_E_CODE_sql= "";
	public String m_view_TXT_I_E_CODE_sql_Header= "Systen Administration - Income Expence Type";
	
	public String m_view_TXT_GARAGE_CODE_sql= "";
	public String m_view_TXT_GARAGE_CODE_sql_Header= "Systen Administration - Garage";
	
	public String m_help_TXT_BROKER_CODE_sql= "";
	public String m_help_TXT_BROKER_CODE_sql_Header= "Systen Administration - Broker";
	
	
	// added by udara 31-10-2014
	public String m_help_TXT_BROKER_CODE_BLACKLIST_sql= "";
	public String m_help_TXT_BROKER_CODE_BLACKLIST_sql_Header= "Systen Administration - Blacklisted Broker ";
	// end by udara 31-10-2014
	
	public String m_help_TXT_BRANCH_CODE_sql= "";
	public String m_help_TXT_BRANCH_CODE_sql_Header= "System Administration - Bank Branch";
	
	public String m_view_TXT_SCORE_CODE_sql= "";
	public String m_view_TXT_SCORE_CODE_sql_Header= "Client Help - Credit Score Category";
	
	public String m_view_TXT_SCORE_SUB_CODE_sql="";
	public String m_view_TXT_SCORE_SUB_CODE_sql_Header= "Client Help - Credit Score Sub Category";
	
	public String m_view_TXT_SCORE_MODEL_CODE_sql="";
	public String m_view_TXT_SCORE_MODEL_CODE_sql_Header= "Client Help - Credit Score Model Creation";

  public String m_help_cr_score_application_sql="";
	public String m_help_cr_score_application_sql_Header=" Credit Score - Applications";
	
	public String m_help_TXT_PROVINCE_CODE_sql="";	
  public String m_help_TXT_PROVINCE_CODE_sql_Header="Province Code Help";	
	
	public String m_help_TXT_COUNTRY_CODE_sql="";	
  public String m_help_TXT_COUNTRY_CODE_sql_Header="System Administration - Counttry";
	
	public String m_help_TXT_CITY_CODE_sql="";	
  public String m_help_TXT_CITY_CODE_sql_Header="System Administration - City";

	public String m_help_TXT_DISTRICT_CODE_sql="";
	public String m_help_TXT_DISTRICT_CODE_sql_Header="System Administration - District";
	
	public String m_help_TXT_AREA_CODE_sql="";
	public String m_help_TXT_AREA_CODE_sql_Header="System Administration - Area";
		
	public String m_help_TXT_CURR_CODE_sql="";
	public String m_help_TXT_CURR_CODE_sql_Header="System Administration - Currency";
	
	public String m_help_TXT_DESIGNATION_CODE_sql="";
	public String m_help_TXT_DESIGNATION_CODE_sql_Header="System Administration - Designation";
	
	public String m_help_TXT_DIVISION_sql="";
	public String m_help_TXT_DIVISION_sql_Header="System Administration - Division";

	public String m_help_TXT_PRODUCT_ID_sql="";
	public String m_help_TXT_PRODUCT_ID_sql_Header="System Administration - Leasing Stage Process";
	
	public String m_help_TXT_POSTAL_CODE_sql="";
	public String m_help_TXT_POSTAL_CODE_sql_Header="System Administration - Postal Codes";
	
	public String m_help_TXT_EMP_CODE_sql="";
	public String m_help_TXT_EMP_CODE_sql_Header="System Administration - Employess";
	
	public String m_help_TXT_DIV_sql="";
	public String m_help_TXT_DIV_sql_Header="System Administration - Division1";
	
	public String m_view_TXT_LOCATION_CODE_sql="";
	public String m_view_TXT_LOCATION_CODE_sql_Header="System Administration - Location";
	
	public String m_help_TXT_SECTOR_CODE_sql="";
	public String m_help_TXT_SECTOR_CODE_sql_Header="System Administration - Business Sector";
	
	public String m_help_TXT_BANK_CODE_sql="";
  public String m_help_TXT_BANK_CODE_sql_Header="System Administration - Bank";
	
	
	public String m_help_TXT_DIVISION_CODE_sql="";
  public String m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_DIVISION1_CODE_sql="";
  public String m_help_TXT_DIVISION1_CODE_sql_Header="System Administration - Division";
	
	public String m_view_TXT_CAPACITY_CODE_sql="";
  public String m_view_TXT_CAPACITY_CODE_sql_Header="System Administration - Engine Capacity";
	
	public String m_view_TXT_USER_ID_sql="";
  public String m_view_TXT_USER_ID_sql_Header="System Administration - User";
	
	public String	m_help_TXT_TYPE_CODE_sql="";
	public String	m_help_TXT_TYPE_CODE_sql_Header="System Administration - Charges";
	

	public String	m_view_TXT_ITEM_CAT_CODE_sql="";
	public String	m_view_TXT_ITEM_CAT_CODE_sql_Header="System Administration - Item Category";

	public String	m_view_TXT_ITEM_SUB_CAT_sql="";
	public String	m_view_TXT_ITEM_SUB_CAT_sql_Header="System Administration - Item Sub Category";
	
	public String	m_view_TXT_FILED_CODE_sql="";
	public String	m_view_TXT_FILED_CODE_sql_Header="System Administration - Filed Code";
	
	public String	m_view_TXT_FILED_CODE_sql_applicable="";
	public String	m_view_TXT_FILED_CODE_sql_applicable_Header="System Administration - Applicable Filed Code";//Added By Nuwan De Silva(Modified)
	
	public String	m_help_TXT_SUB_CODE_sql="";
	public String	m_help_TXT_SUB_CODE_sql_Header="System Administration - Business Sub Sector";
	
	public String m_view_TXT_ENTITY_CODE_sql="";
	public String m_view_TXT_ENTITY_CODE_sql_Header="System Administration - Legal Entity ";
	 
	public String m_view_TXT_PHONE_AREA_CODE_sql="";
	public String m_view_TXT_PHONE_AREA_CODE_sql_Header="System Administration - Phone Area Codes ";

	public String m_view_TXT_PRODUCT_CODE_sql="";
	public String m_view_TXT_PRODUCT_CODE_sql_Header="System Administration - Product Codes";
	
	public String m_view_TXT_SUB_TYPE_CODE_sql="";
  public String m_view_TXT_SUB_TYPE_CODE_sql_Header="System Administration - Sub Type Code";
	
	public String m_help_TXT_FUAL_TYPE_CODE_sql="";
	public String m_help_TXT_FUAL_TYPE_CODE_sql_Header="System Administration - Fual Type Code";
	
	public String m_help_TXT_ITEM_SUB_CAT_sql_1="";
	public String m_help_TXT_ITEM_SUB_CAT_sql_1_Header="System Administration - Item Sub Catogory Code";
	
	public String m_view_TXT_RMV_AGENT_CODE_sql="";
	public String m_view_TXT_RMV_AGENT_CODE_sql_Header="System Administration - RMV Agents Code";
	
	public String m_view_TXT_CODE_sql="";
	public String m_view_TXT_CODE_sql_Header="System Administration - Asset Condition Code";
  
	public String m_view_TXT_SEIZER_CODE_sql="";
	public String m_view_TXT_SEIZER_CODE_sql_Header="System Administration - Seizer Code";

	public String m_view_TXT_YARD_CODE_sql="";
	public String m_view_TXT_YARD_CODE_sql_Header="System Administration - Yard Code";
	
	public String m_view_TXT_CODE_sql_fuel_type="";
	public String m_view_TXT_CODE_sql_fuel_type_Header="System Administration - Fuel type";
	
	public String m_view_TXT_MAKE_CODE_sql="";
	public String m_view_TXT_MAKE_CODE_sql_Header="System Administration - Make Code";
	
	public String m_view_TXT_TEAM_ID_sql="";
	public String m_view_TXT_TEAM_ID_sql_Header="System Administration - Team ID";
	
	public String m_view_TXT_SUB_DIVISION_CODE_sql="";
	public String m_view_TXT_SUB_DIVISION_CODE_sql_Header="System Administration - Sub Division ID";
	
	public String m_view_TXT_DURATION_sql="";
	public String m_view_TXT_DURATION_sql_Header="System Administration - Repayment Interval";
	
	public String m_view_TXT_RCODE_sql="";
	public String m_view_TXT_RCODE_sql_Header="System Administration - Revenue License";
	
	public String m_view_TXT_MODEL_CODE_sql="";
	public String m_view_TXT_MODEL_CODE_sql_Header="System Administration - Model Creation";
	
	public String m_view_TXT_VENDOR_CODE_sql="";
	public String m_view_TXT_VENDOR_CODE_sql_Header="System Administration - Vender Creation";
	
	public String m_view_TXT_CAT_TYPE_CODE_sql="";
	public String m_view_TXT_CAT_TYPE_CODE_sql_Header="System Administration - Customer Category";
	
	public String m_view_TXT_INITIATION_CODE_sql="";
	public String m_view_TXT_INITIATION_CODE_sql_Header="System Administration - Initiation Type";
	
	public String m_help_TXT_CATEGORY_sql="";
	public String m_help_TXT_CATEGORY_sql_Header="System Administration - Item Category";
	
	public String m_view_TXT_CODE_sql_1="";
  public String m_view_TXT_CODE_sql_1_Header="System Administration - Document Required";
	
	public String m_help_TXT_CLIENT_CODE_sql="";
	public String m_help_TXT_CLIENT_CODE_sql_Header="System Administration -Backlisted Clients ";
	
	public String m_help_TXT_ENTITY_TYPE_sql="";
	public String m_help_TXT_ENTITY_TYPE_sql_Header="System Administration - Entity Type";
	
	public String m_help_TXT_STAGE_sql="";
	public String m_help_TXT_STAGE_sql_Header="System Administration - Stage";
	
	public String m_view_TXT_CODE_sql_2="";
	public String m_view_TXT_CODE_sql_2_Header="System Administration - Document Applicable";

	public String m_view_TXT_VENDOR_BCODE_sql="";
	public String m_view_TXT_VENDOR_BCODE_sql_Header="System Administration - Vender Backlisting";

	public String m_view_TXT_CATEGORY_CODE_sql="";
	public String m_view_TXT_CATEGORY_CODE_sql_Header="System Administration - Follow up Action Category";

	public String m_view_TXT_RATE_sql="";
	public String m_view_TXT_RATE_sql_Header="System Administration - Discount Rate";
	
	public String m_view_TXT_TEAM_ID_sql_new="";
	public String m_view_TXT_TEAM_ID_sql_new_Header="System Administration - Assign Team Members";
	
	public String m_view_TXT_BASE_CODE_sql="";
	public String m_view_TXT_BASE_CODE_sql_Header="System Administration - Variable Interest Rates";

	public String m_view_TXT_MAIN_CODE_sql="";
	public String m_view_TXT_MAIN_CODE_sql_Header="System Administration - Maintanance Rate";

	public String m_help_TXT_SUB_MODEL_CODE_sql="";
	public String m_help_TXT_SUB_MODEL_CODE_sql_Header="System Administration - Sub Model Creation";

	public String m_help_TXT_CHARGE_SUB_CODE_sql="";
	public String m_help_TXT_CHARGE_SUB_CODE_sql_Header="System Administration - Sub Charge";

	public String m_help_TXT_MILEAGE_CODE_sql="";
	public String m_help_TXT_MILEAGE_CODE_sql_Header="System Administration - Milage";

	public String m_view_TXT_HOLIDAY_DATE_sql="";
	public String m_view_TXT_HOLIDAY_DATE_sql_Header="System Administration - Holidays";
	
	
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
	public String m_help_TXT_VALUATION_NO1_sql_Header="System Administration - Valuer Details";
	

	public String m_help_TXT_VALUATION_DET_sql="";
	public String m_help_TXT_VALUATION_DET_sql_Header="System Administration - Valuer Details";

	public String m_help_TXT_MODEL_CODE_sql1="";
	public String m_help_TXT_MODEL_CODE_sql1_Header="System Administration - Model Creation";
	
	public String m_help_TXT_SUB_MODEL_CODE_sql1="";
	public String m_help_TXT_SUB_MODEL_CODE_sql1_Header="System Administration -Sub Model Creation";
	
	public String m_view_TXT_ACCOUNT_CODE_sql="";
  public String m_view_TXT_ACCOUNT_CODE_sql_Header="System Administration - Account Code Creation";

	public String m_help_TXT_SUB_MODEL_sql="";
	public String m_help_TXT_SUB_MODEL_sql_Header="System Administration -Sub Model Creation";
	
	public String m_help_TXT_ASSET_ID_sql1= "";
	public String m_help_TXT_ASSET_ID_sql1_Header= "Application Process - Asset ID ";
	
	public String m_help_TXT_APPLICATION_NO_sql="";
	public String m_help_TXT_APPLICATION_NO_sql_Header="System Administration -Applicable Client Documents";
	
	public String m_view_sql="";
	public String m_view_sql_Header="Marketing - Valuation Details";
	
	
	public String m_view_TXT_INTEREST_RATE_sql="";
  public String m_view_TXT_INTEREST_RATE_sql_Header="System Administration - Pricing Default Value";
	
	
	public String m_view_TXT_MIN_CHARGE_sql="";
	public String m_view_TXT_MIN_CHARGE_sql_Header="System Administration - Termination Rate";
	
	
	public String m_view_TXT_NARRATIONS_CODE_sql="";
	public String m_view_TXT_NARRATIONS_CODE_sql_Header="System Administration - CR/DR Standard Narrations ";
	
	public String m_view_TXT_CHEQUE_NARRATIONS_CODE_sql="";
	public String m_view_TXT_CHEQUE_NARRATIONS_CODE_sql_Header="System Administration - Cheque Return Narrations ";

	public String m_view_TXT_REFRENCE_CODE_sql="";
	public String m_view_TXT_REFRENCE_CODE_sql_Header="System Administration - Reference Conditions ";
	
	public String m_help_TXT_GRP_INV_sql="";
	public String m_help_TXT_GRP_INV_sql_Header="Collection - Group Receipt ";
	
	public String m_help_TXT_APP_STATUS_sql="";
	public String m_help_TXT_APP_STATUS_sql_Header="System Administration - All Application Status";
	
	
	//-------------------------------------------------------------------------
	
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
		
		
		 m_help_TXT_APP_STATUS_sql=//Added by Sandun on 29-10-2008
		  " SELECT L.NO ,L.STATUS_CODE,INITCAP(L.STATUS_DESC) AS DESCREPTION ,DECODE(L.DEFAULT_VALUE,'Y','Yes','N','No') AS DEFAULT_VALUE, DECODE(L.ACTIVE_STATUS,'Y','Yes','N','No') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STATUS_CODE,P.STATUS_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " STATUS_CODE, "+
			" STATUS_DESC, "+
			" NVL(DEFAULT_VALUE,'Y') DEFAULT_VALUE, "+
			" NVL(ACTIVE_STATUS,'Y') ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+		 
			" )P)L  "+
			" WHERE L.NO>= "+ Start_Val+" AND L.NO<= "+End_Val+" ";	
		
		
		
		m_view_TXT_INTEREST_RATE_sql=
		
		  " SELECT L.NO ,L.INTEREST_RATE,L.VAT_PER,L.VAT_APP,L.PERIOD,L.AMOUNT "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INTEREST_RATE,P.VAT_PER,P.VAT_APP,P.PERIOD,P.AMOUNT "+
		" FROM( "+ 
			" SELECT "+ 
			" INTEREST_RATE,"+
			" VAT_PER,"+
			" VAT_APP,"+
			" PERIOD,"+
			" AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_MAS_PRICING_DEFAULT_VAL "+
		"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
		/*	m_view_TXT_MIN_CHARGE_sql=
			" SELECT L.NO ,L.MIN_CHARGE,L.MAX_CHARGES,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE "+
			" FROM( "+ 
			" (SELECT ROWNUM NO,P.MIN_CHARGE,P.MAX_CHARGES,P.DEFAULT_VALUE "+ 
			" FROM( "+ 
			" SELECT "+
			" MIN_CHARGE, "+
			" MAX_CHARGES,"+
			" DEFAULT_VALUE "+
			" FROM LAKDL.AF_CO_MAS_TERMNATION_RATE "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" "; */
			
			
			
			m_view_TXT_MIN_CHARGE_sql=
			" SELECT L.NO ,L.MIN_CHARGE \"MIN CHARGE\",L.MAX_CHARGES \"MAX CHARGES\",DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS \"DEFAULT VALUE\" "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.MIN_CHARGE,P.MAX_CHARGES,P.DEFAULT_VALUE "+
		" FROM( "+ 
			" SELECT "+ 
			" MIN_CHARGE,"+
			" MAX_CHARGES,"+
			" DEFAULT_VALUE "+
			//" PERIOD,"+
			//" AMOUNT "+
		" FROM "+m_schema_name+".AF_CO_MAS_TERMNATION_RATE "+
		"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		
		 //---------------------ID      : Credit Score Category---------------------------------//
		//----------------------Purpose : Credit Score Category Help -------------------------------------------------//
		//----------------------Name    : N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    : 24-08-2006----------------------------------------------------//
		
		
		m_view_TXT_SCORE_CODE_sql=
			" SELECT L.NO ,L.SCORE_CODE,L.DESCRIPTION,L.DISPLAY_POSITION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_CODE,P.DESCRIPTION,P.DISPLAY_POSITION,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_CODE, "+ 
			" DESCRIPTION, "+ 
			" DISPLAY_POSITION, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY "+ 
			//" WHERE SCORE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		
		 //---------------------ID      : Sub Credit Score Category---------------------------------//
		//----------------------Purpose : Sub Credit Score Category View Help -------------------------------------------------//
		//----------------------Name    : N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    : 24-08-2006----------------------------------------------------//
			
					
		
		m_view_TXT_SCORE_SUB_CODE_sql=
			" SELECT L.NO ,L.SCORE_SUB_CODE,L.SCORE_CODE,L.DESCRIPTION,L.DISPALY_POSITION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_SUB_CODE,P.SCORE_CODE,P.DESCRIPTION,P.DISPALY_POSITION,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_SUB_CODE, "+ 
			" SCORE_CODE, "+
			" DESCRIPTION, "+ 
			" DISPALY_POSITION, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_CR_MAS_SCORE_SUB_CATEGORY "+ 
			//" WHERE SCORE_SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
		
		m_view_TXT_SCORE_MODEL_CODE_sql=
			" SELECT L.NO ,L.SCORE_MODEL_CODE,L.DESCRIPTION,L.TOTAL_SCORE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_MODEL_CODE,P.DESCRIPTION,P.TOTAL_SCORE,P.ACTIVE_STATUS "+
			" FROM( "+ 
	    " SELECT SCORE_MODEL_CODE, "+
	    " DESCRIPTION, "+
	    " TOTAL_SCORE, "+
			" ACTIVE_STATUS "+
	 		" FROM "+m_schema_name+".AF_CR_MAS_SCORE_MODEL "+
			//" WHERE SCORE_MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		" WHERE APP_STATUS='ENTER' AND APPLICATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
		"  )P)L  "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//-------------------- ID : 1.2 Province Creation Process-----------------------------//
		//--------------------Province Code Help -------------------------------------------------//
		//--------------------Mahela Wickramasekara------------------------------------------------------//
		//--------------------19-07-2006---------------------------------------------------------//
		m_help_TXT_PROVINCE_CODE_sql=
		" SELECT L.NO ,L.PROVINCE_CODE,L.PROVINCE_DESC,L.COUNTRY_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PROVINCE_CODE,P.PROVINCE_DESC,P.COUNTRY_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " PROVINCE_CODE, "+
      " PROVINCE_DESC, "+
			" COUNTRY_CODE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_PROVINCE "+
		  //" WHERE PROVINCE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		 //---------------------ID      :1.4 City Creation Process---------------------------------//
		//----------------------Purpose :City Code Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :23-08-2006----------------------------------------------------//

		m_help_TXT_CITY_CODE_sql=
		" SELECT L.NO ,L.CITY_CODE,L.CITY_DESC,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC,P.DISTRICT_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " CITY_CODE, "+
      " CITY_DESC, "+
			" DISTRICT_CODE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_CITY "+
		  //" WHERE CITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY CITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		/*------------------  ID         : 1.1 Country Creation Process-----------------------------------------
			--------------------Purpose    :Country Code Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :19-07-2006---------------------------------------------------------*/
		
			m_help_TXT_COUNTRY_CODE_sql=
		
		  " SELECT L.NO ,L.COUNTRY_CODE,L.COUNTRY_DESC,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.COUNTRY_CODE,P.COUNTRY_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " COUNTRY_CODE,"+
      " COUNTRY_DESC, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_COUNTRY "+
	  	//" WHERE COUNTRY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
		
		
			//--------------------District Code Help -------------------------------------------------//
		//-------------------------Chandana------------------------------------------------------//
		//------------------------23-08-2006---------------------------------------------------------//
			
			m_help_TXT_DISTRICT_CODE_sql=
			
			" SELECT L.NO ,L.DISTRICT_CODE,L.DISTRICT_DESC,L.PROVINCE_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " DISTRICT_CODE,"+
      " DISTRICT_DESC, "+
			" PROVINCE_CODE, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_DISTRICT "+
	  	//" WHERE DISTRICT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			/*------------------  ID       : Licencee settlement Creation Process-----------------------------------------
			--------------------Purpose    : Bank Branch Code Help ----------------------------------------------
		  ------------------- Added By   : M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     : 03-10-2006---------------------------------------------------------*/
		
		 m_help_TXT_BRANCH_CODE_1_sql=	
				
		
			 "SELECT L.NO ,L.ACC_NO,L.BRANCH_CODE,L.ACC_SYS_REFNO,L.CURR_CODE, NVL(L.ACC_CODE,'N/A') AS ACC_CODE ,NVL(L.ACC_DESC,'N/A') AS ACC_DESC,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			 "FROM  "+
			 "(SELECT ROWNUM NO,P.ACC_NO,P.BRANCH_CODE,P.ACC_SYS_REFNO,P.CURR_CODE,P.ACC_CODE,P.ACC_DESC,P.ACTIVE_STATUS "+
			 "FROM(  "+
  		 " SELECT "+
 			 " ACC_NO,"+
			 " BRANCH_CODE,"+	
 			 " ACC_SYS_REFNO,"+
 			 " CURR_CODE,+"+
 			 " ACC_CODE,"+
 			 " ACC_DESC,"+
			 " ACTIVE_STATUS "+	

       " FROM "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT "+
			 "  )P)L  "+
			 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
			
			
			
			
				/*------------------  ID     : 1.15 Business Sector Creation Process----------------------------------------
			--------------------Purpose    :Business Sector Code View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/
			
		  m_help_TXT_SECTOR_CODE_sql=
			" SELECT L.NO ,L.SECTOR_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " SECTOR_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE,"+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR "+
	  	//" WHERE SECTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
				
				/*------------------  ID     : 1.17 Charges Creation Process-----------------------------------
			--------------------Purpose    : Charges Code View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/
			
		  m_help_TXT_TYPE_CODE_sql=
			" SELECT L.NO ,L.TYPE_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " TYPE_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_CHARGES "+
	  	//" WHERE TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
    /*------------------  ID         : 1.16 Business Sub Sector Creation Process-----------------------------------
			--------------------Purpose    : Business Sub Sector Code View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :20-07-2006---------------------------------------------------------*/
			
			 m_help_TXT_SUB_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.SECTOR_CODE,L.DESCRIPTION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.SECTOR_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " SUB_CODE,"+
			" SECTOR_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS "+
	  	//" WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------  ID      : 1.18 Applicable Charges Creation Process-----------------------------------
			--------------------Purpose    : Sub Type Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     : 21-07-2006---------------------------------------------------------*/
		
			m_view_TXT_SUB_TYPE_CODE_sql=
			" SELECT L.NO ,L.SUB_TYPE_CODE,L.TYPE_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS,"+
			" DECODE(L.MAINTENANCE_STATUS,'Y','YES','N','NO') AS MAINTENANCE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TYPE_CODE,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS,P.MAINTENANCE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " SUB_TYPE_CODE,"+
			" TYPE_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS,"+
			" MAINTENANCE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
	  	//" WHERE SUB_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		 /*------------------ ID         : 1.18 Applicable Charges Creation Process-----------------------------------
			--------------------Purpose    : Item Sub Catogary Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 21-07-2006---------------------------------------------------------*/	
			
			m_help_TXT_ITEM_SUB_CAT_sql_1=
			" SELECT L.NO ,L.ITEM_SUB_CAT,L.ITEM_CAT_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.ITEM_CAT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " ITEM_SUB_CAT,"+
      " ITEM_CAT_CODE, "+
			 " DESCRIPTION, "+
				// " FROM_DATE, "+
				//	 " TO_DATE, "+
				//		" AMOUNT, "+
					//	" PERCENTAGE, "+
						   " DEFAULT_VALUE, "+
								" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
	  	//" WHERE ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID       : 1.19 Condition of Asset Creation Process----------------------------------
			--------------------Purpose    : View All Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/	
			
			m_view_TXT_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET "+
	  	//" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
				 /*------------------ ID     : 1.40 Make Creation Process ----------------------------------
			--------------------Purpose    : Make View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_MAKE_CODE_sql=
			" SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " MAKE_CODE,"+
      " MAKE_DESC, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_MAKE "+
	  	//" WHERE MAKE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID       : 1.41 Model Creation Process ----------------------------------
			--------------------Purpose    : Model View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_MODEL_CODE_sql=
			" SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.ITEM_SUB_CAT,L.TAX_RATE,L.TAX_FOR_LEASE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO')AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.ITEM_SUB_CAT,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " MODEL_CODE,"+
      " DESCRIPTION, "+
			" MAKE_CODE,"+
			" FUEL_TYPE,"+
			" ITEM_SUB_CAT,"+
			" TAX_RATE, "+
			" TAX_FOR_LEASE, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
	  	//" WHERE MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
				 /*------------------ ID     : 1.42 Vendor Creation Process ----------------------------------
			--------------------Purpose    : Vendor View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/
			
				
			
			m_view_TXT_VENDOR_CODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE,L.ACTIVE_STATUS, "+
			" L.BRANCH/*,L.LOCATION_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO*/,L.ADDRESS,L.CITY_CODE, "+ //COMMNTED BY AH
			" L.TEL_NO,L.FAX_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME,P.CATEGORY,P.TYPE,P.DEFAULT_VALUE,P.ACTIVE_STATUS, "+
			"  P.BRANCH/*,P.LOCATION_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO*/,P.ADDRESS,P.CITY_CODE, "+ //COMMNTED BY AH
			" P.TEL_NO,P.FAX_NO "+
			" FROM( "+ 
		  " SELECT "+
      " A.VENDOR_CODE,"+
      " A.NAME, "+
			" A.CATEGORY, "+
			" A.TYPE,"+
			" A.DEFAULT_VALUE, "+
			" A.ACTIVE_STATUS, "+
			" B.BRANCH, "+
			//" B.LOCATION_CODE,"+ COMMNTED BY AH
			//" B.TITLE, "+ COMMNTED BY AH
			//" B.FIRST_NAME,"+ COMMNTED BY AH
			//" B.LAST_NAME,"+ COMMNTED BY AH
			//" B.ID_NO,"+ COMMNTED BY AH
			" B.ADDRESS,"+
			" B.CITY_CODE, "+
			//" B.ACTIVE_STATUS, "+
			//" B.DEFAULT_VALUE, "+
			" C.TEL_NO,"+
			" C.FAX_NO "+
			
      " FROM "+m_schema_name+".AF_CO_MAS_VENDORS A, "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION B,"+m_schema_name+".AF_CO_MAS_VENDOR_LOC_CONTACT C "+
	  	" WHERE A.VENDOR_CODE = UPPER(B.VENDOR_CODE) AND B.BRANCH=UPPER(C.BRANCH_CODE) "+
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
	  	" WHERE ITEM_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID       : 1.43 Customer Category Creation Process ----------------------------------
			--------------------Purpose    : Customer Category View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/
			m_view_TXT_CAT_TYPE_CODE_sql=
			" SELECT L.NO ,L.CAT_TYPE_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAT_TYPE_CODE,p.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " CAT_TYPE_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_MK_MAS_CUSTOMER_CATOGORY "+
	  	//" WHERE CAT_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
				
			 /*------------------ ID        : 1.44 Initiation Type Creation Process  ----------------------------------
			-------------------- Purpose    : Initiation Type Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 24-08-2006---------------------------------------------------------*/
			m_view_TXT_INITIATION_CODE_sql=
			" SELECT L.NO ,L.INITIATION_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.INITIATION_CODE,p.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " INITIATION_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_MK_MAS_INITIATION_TYPE "+
	  	//" WHERE INITIATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID        : 1.50 Document Required Creation Process  ----------------------------------
			-------------------- Purpose    : Document Required View Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 24-08-2006---------------------------------------------------------*/
			m_view_TXT_CODE_sql_1=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,L.DOC_APP_TYPE AS TYPE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DOC_APP_TYPE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " DESCRIPTION, "+
			" DOC_APP_TYPE , "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED "+
	  	//" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------ ID         : 1.51 Applicable Document Type Creation Process  ----------------------------------
			-------------------- Purpose    : Applicable Document Type Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 24-08-2006---------------------------------------------------------*/
	
			
			m_view_TXT_CODE_sql_2=
			" SELECT L.NO ,L.CODE,L.ENTITY_TYPE,L.STAGE,L.ITEM_CAT_CODE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.ENTITY_TYPE,P.STAGE,P.ITEM_CAT_CODE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " ENTITY_TYPE, "+
			" STAGE,"+
			" ITEM_CAT_CODE,"+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
	  	//" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE ENTITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
				/*------------------ ID       : 1.51 Applicable Document Type Creation Process  ----------------------------------
			-------------------- Purpose    : Stage Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 24-08-2006---------------------------------------------------------*/
			m_help_TXT_STAGE_sql=
			" SELECT L.NO ,L.STAGE_CODE,L.SCREEN_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STAGE_CODE,P.SCREEN_NAME "+
			" FROM( "+ 
		  " SELECT "+
      " STAGE_CODE,"+
      " SCREEN_NAME "+
      " FROM "+m_schema_name+".AF_CO_MAS_STAGE "+
	  	" WHERE STAGE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE CAT_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
				/*------------------ ID       : 1.53 Account Code Creation Process  ----------------------------------
			-------------------- Purpose    : Account Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 08-08-2006---------------------------------------------------------*/		
						
			
			m_view_TXT_ACCOUNT_CODE_sql=
			" SELECT L.NO,L.ACCOUNT_CODE,L.DESCRIPTION,L.REPORT_TYPE,DECODE(L.STATUS,'Y','YES','N','NO') AS STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_CODE,P.DESCRIPTION,P.REPORT_TYPE,P.STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " ACC_TYPE_CODE ACCOUNT_CODE,"+
      " ACC_TYPE_DESC DESCRIPTION ,"+
			" ACC_TYPE_CATEGORY REPORT_TYPE , "+
			" STATUS "+
      " FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE "+
	  	//" WHERE ACCOUNT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
			
			
			
			/*------------------  ID       : 1.1 Area Creation Process-----------------------------------------
			--------------------Purpose    :Area Code View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/
		
			
			m_help_TXT_AREA_CODE_sql=
			" SELECT L.NO ,L.AREA_CODE,L.AREA_DESC,L.CITY_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.AREA_CODE,P.AREA_DESC,P.CITY_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		 	" SELECT "+
      " AREA_CODE,"+
      " AREA_DESC,"+
      " CITY_CODE,"+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_AREA"+
			//" WHERE AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			

		 //---------------------ID      :1.20 Currency Creation Process---------------------------------//
		//----------------------Purpose :Currency Code View Help -------------------------------------------------//
		//----------------------Name    :Chandana------------------------------------------------------//
		//----------------------Date    :23-08-2006----------------------------------------------------//

	
		m_help_TXT_CURR_CODE_sql=
		" SELECT L.NO ,L.CURR_CODE,L.CURR_SYMBOL,L.REP_CURR,TO_CHAR(L.TRN_DATE,'DD-MM-YYYY') AS TRN_DATE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CURR_CODE,P.CURR_SYMBOL,P.REP_CURR,P.TRN_DATE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " CURR_CODE, "+
      " CURR_SYMBOL, "+
			" REP_CURR, "+
      " TRN_DATE, "+
			//" CATEGORY_CODE, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_CURRENCY "+
		 // " WHERE CURR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY CURR_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.10 Designation Creation Process-----------------------------------------
			--------------------Purpose    :Designation Code Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/

    m_help_TXT_DESIGNATION_CODE_sql=
		" SELECT L.NO ,L.DESIGNATION_CODE,L.DESIGNATION_NAME,NVL(L.DESIGNATION_LEVEL,0) AS DESIGNATION_LEVEL,L.DIVISION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DESIGNATION_CODE,P.DESIGNATION_NAME,P.DESIGNATION_LEVEL,P.DIVISION,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " DESIGNATION_CODE, "+
      " DESIGNATION_NAME, "+
			" DESIGNATION_LEVEL, "+
      " DIVISION, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
		  //" WHERE DESIGNATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		  " WHERE DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.11 Employee Creation Process-----------------------------------------
			--------------------Purpose    :Emp Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :19-07-2006---------------------------------------------------------*/
			
			m_help_TXT_EMP_CODE_sql=
		" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO,P.ACTIVE_STATUS "+
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
			" ID_NO, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
		 // " WHERE EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		 /*------------------  ID        : 1.12 Bank Creation Process-----------------------------------------
			--------------------Purpose    :Bank Code View All Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/
		
		 m_help_TXT_BANK_CODE_sql=
		" SELECT L.NO ,L.BANK_CODE,L.NAME,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " BANK_CODE , "+
      " NAME ,"+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
		 // " WHERE BANK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------  ID       : 1.13 Bank Branch Creation Process-----------------------------------------
			--------------------Purpose    :Bank Branch Code View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :23-08-2006---------------------------------------------------------*/
		//Modified by Disnaka Jayasuriya on 2009-1014 for change not enter for '-' in some coloumns
		 m_help_TXT_BRANCH_CODE_sql=
		" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.ADDRESS1,NVL(L.ADDRESS2,'-') AS ADDRESS2 ,NVL(L.CITY_CODE,'-') AS CITY_CODE,NVL(L.TEL_NO,'-') AS TELEPHONE_NO,NVL(L.FAX_NO,'-') AS FAX_NO,L.DAYS_TO_REALISE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
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
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
		  //" WHERE BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
		/*------------------  ID         : 1.14 Broker Creation Process-----------------------------------------
			--------------------Purpose    :  Broker Code View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     : 23-08-2006---------------------------------------------------------*/
			
			m_help_TXT_BROKER_CODE_sql=
		" SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,NVL(L.CITY_CODE,'Not_Enter') AS CITY_CODE,NVL(L.POSTAL_CODE,'Not_Enter') AS POSTAL_CODE,NVL(L.CONTACT_NO,'Not_Enter') AS CONTACT_NO,"+
		  " NVL(L.MOBILE_NO,'Not_Enter') AS MOBILE_NO,NVL(L.FAX_NO,'Not_Enter') AS FAX_NO,NVL(L.SECTOR_CODE,'Not_Enter') AS SECTOR_CODE,NVL(L.COMMISSION_RATE,0) AS COMMISSION_RATE,NVL(L.COMMISSION_AMOUNT,0) AS COMMISSION_AMOUNT,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO','B','BLACKLISTED') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO,"+
			" P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT,P.ACTIVE_STATUS"+
			" FROM( "+ 
  		" SELECT "+
      " BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,"+
      " MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,COMMISSION_AMOUNT,ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
		 // " WHERE BROKER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			// added by udara 31-10-2014
			
			m_help_TXT_BROKER_CODE_BLACKLIST_sql =
			" SELECT L.NO ,L.BROKER_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS1,L.ADDRESS2,L.LOCATION_CODE,NVL(L.CITY_CODE,'Not_Enter') AS CITY_CODE,NVL(L.POSTAL_CODE,'Not_Enter') AS POSTAL_CODE,NVL(L.CONTACT_NO,'Not_Enter') AS CONTACT_NO,"+
			  " NVL(L.MOBILE_NO,'Not_Enter') AS MOBILE_NO,NVL(L.FAX_NO,'Not_Enter') AS FAX_NO,NVL(L.SECTOR_CODE,'Not_Enter') AS SECTOR_CODE,NVL(L.COMMISSION_RATE,0) AS COMMISSION_RATE,NVL(L.COMMISSION_AMOUNT,0) AS COMMISSION_AMOUNT,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO','B','BLACKLISTED') AS ACTIVE_STATUS "+
				" FROM  "+
				" (SELECT ROWNUM NO,P.BROKER_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS1,P.ADDRESS2,P.LOCATION_CODE,P.CITY_CODE,P.POSTAL_CODE,P.CONTACT_NO,"+
				" P.MOBILE_NO,P.FAX_NO,P.SECTOR_CODE,P.COMMISSION_RATE,P.COMMISSION_AMOUNT,P.ACTIVE_STATUS"+
				" FROM( "+ 
				  	  " SELECT "+
				      " BROKER_CODE,TITLE,FIRST_NAME,LAST_NAME,ID_NO,ADDRESS1,ADDRESS2,LOCATION_CODE,CITY_CODE,POSTAL_CODE,CONTACT_NO,"+
				      " MOBILE_NO,FAX_NO,SECTOR_CODE,COMMISSION_RATE,COMMISSION_AMOUNT,ACTIVE_STATUS "+
				      " FROM "+m_schema_name+".AF_CO_MAS_BROKER "+
						"  WHERE ACTIVE_STATUS = 'B'  "+
						 // " WHERE BROKER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
							"  )P)L  "+
							" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			// end by udara 31-10-2014
			
			
			
			
			/*------------------  ID       : 1.30 Garage Creation Process-----------------------------------------
			--------------------Purpose    :Garage View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_GARAGE_CODE_sql=
			" SELECT L.NO ,L.GARAGE_CODE,L.NAME,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GARAGE_CODE,P.NAME,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " GARAGE_CODE , "+
      " NAME, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_GARAGE "+
		 // " WHERE GARAGE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			/*------------------  ID       : 1.31 Income / Expense Type Creation Process-----------------------------------------
			--------------------Purpose    :Income Expence View Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_I_E_CODE_sql=
			" SELECT L.NO ,L.I_E_CODE AS INCOME_EXPENSE_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.I_E_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " I_E_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_INCOME_EXPENCE_TYPE "+
		  //" WHERE I_E_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------  ID       : 1.33 Interest Type Creation Process-----------------------------------------
			--------------------Purpose    :Interest Type View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_I_T_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_INTEREST_TYPE "+
		  //" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------  ID       : 1.34 Nationality Creation Process-----------------------------------------
			--------------------Purpose    :Nationality View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			
			m_view_TXT_NATIONALITY_CODE_sql=
			" SELECT L.NO ,L.NATIONALITY_CODE,L.DESCRIPTION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS, DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.NATIONALITY_CODE,P.DESCRIPTION,P.ACTIVE_STATUS,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " NATIONALITY_CODE , "+
      " DESCRIPTION,  "+
			" ACTIVE_STATUS, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_NATIONALITY "+
		  //" WHERE NATIONALITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		 /*------------------  ID       : 1.32 Inquiry Status Creation Process-----------------------------------------
			--------------------Purpose    :Inquiry View Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
		  m_view_TXT_INQ_CODE_sql=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_INQUARY_STATUS "+
		  //" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		  " WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.46 Valuer Creation Process-----------------------------------------
			--------------------Purpose    :Valuer Code Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_VALUER_CODE_sql=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,L.MOBILE_NO,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.MOBILE_NO,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
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
					" ACTIVE_STATUS "+
         " FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
				 //" WHERE VALUER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			/*------------------  ID       : 1.48 Sub Model Creation Process-----------------------------------------
			--------------------Purpose    : Sub Model Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     : 24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_SUB_M_CODE_sql=
			" SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
			" FROM( "+
			   " SELECT "+
         " SUB_CODE,"+ 
         " MODEL_CODE,"+
         " DESCRIPTION,"+
         " ENGINE_CAPACITY,"+
         " OPTION_TYPE,"+
         " COUNTRY_CODE,"+
         " YEAR_OF_MANUFACTURE,"+
         " DEFAULT_VALUE, "+
					" ACTIVE_STATUS "+
         " FROM "+m_schema_name+".AF_CO_MAS_SUB_MODLE "+
				// " WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.47 Mileage Creation Process-----------------------------------------
			--------------------Purpose    : Model Code Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_MODEL_sql=
			" SELECT L.NO ,L.MODEL,L.SUB_MODEL,L.CONDITION_OF_ASSET,L.USAGE_FROM,L.USAGE_TO,L.AMOUNT,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL,P.SUB_MODEL,P.CONDITION_OF_ASSET,P.USAGE_FROM,P.USAGE_TO,P.AMOUNT,P.ACTIVE_STATUS "+
			" FROM( "+
		   " SELECT "+
       "  MODEL,"+
       "  SUB_MODEL,"+
       "  CONDITION_OF_ASSET,"+
       "  USAGE_FROM,"+
       "  USAGE_TO,"+
       "  AMOUNT, "+
				" ACTIVE_STATUS "+
       " FROM "+m_schema_name+".AF_CO_MAS_MILEAGE "+
		  // " WHERE MODEL LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		   " ORDER BY MODEL ASC"+
		  "  )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			m_view_TXT_app_sql=
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CR_OFFICER,'-') CR_OFFICER,L.ENT_DATE,NVL(L.INQUIRY_CODE,'-') INQUIRY_CODE,L.ENT_USER "+
			" FROM  "+
			" ( SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CR_OFFICER,P.ENT_DATE,P.INQUIRY_CODE,P.ENT_USER "+
			" FROM( "+
			" SELECT "+
			"  A.APPLICATION_NO,"+
			"  A.FACILITY_NO,"+
			"  B.CR_OFFICER,"+
			"  TO_CHAR(B.ENT_DATE,'DD-MM-YYYY') ENT_DATE,"+
			"  B.INQUIRY_CODE,"+
			"  B.ENT_USER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
			" WHERE A.INQUARY_NO=B.INQUIRY_CODE "+
			//" AND (UPPER(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%'))  AND B.STATUS=('"+vector.elementAt(1)+"') "+
			" ORDER BY B.ENT_DATE DESC"+
			"  )P )L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			/*------------------  ID       : 1.49 Option Creation Process-----------------------------------------
			--------------------Purpose    : Option Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			//m_help_TXT_OPTION_CODE_sql
			m_view_TXT_OPTION_CODE_sql=
			" SELECT L.NO ,L.OPTION_CODE,L.OPTION_DESC,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.OPTION_CODE,P.OPTION_DESC,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " OPTION_CODE , "+
      " OPTION_DESC, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_OPTION_TYPE "+
		 // " WHERE OPTION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		// tax Creation Process-----------------------------------------
			//--------------------Purpose    : tax Code Help ----------------------------------------------
		 // ------------------- Added By   : K.G Prabash------------------------------------------------------
		//  --------------------  Date     :19-05-2011---------------------------------------------------------*/
			
			m_view_TXT_TAX_CODE_sql=
			" SELECT L.NO ,L.TAX_CODE,L.TAX_DESC,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TAX_CODE,P.TAX_DESC,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " TAX_CODE , "+
      " TAX_DESC, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".af_co_mas_ins_tax "+
	//	  " WHERE TAX_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			/*------------------  ID       : 1.66 Missing Vehicle Process-----------------------------------------
			--------------------Purpose    : Vehicle No Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			
			m_view_TXT_VEHICLE_NO_sql=
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
		 // " WHERE VEHICLE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.68 Lead Source Category Process-----------------------------------------
			--------------------Purpose    : Source Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_SOURCE_CODE_sql=
			" SELECT L.NO ,L.SOURCE_CODE,L.NAME,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SOURCE_CODE,P.NAME,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
			" FROM( "+ 
  		" SELECT "+
      " SOURCE_CODE , "+
      " NAME, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_MK_MAS_LEAD_SOURCE_CAT "+
		 // " WHERE SOURCE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.69 Transaction Process-----------------------------------------
			--------------------Purpose    : Transaction Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_TRAN_CODE_sql=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " TRAN_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
		 // " WHERE TRAN_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.70 Transaction Sub Type Process-----------------------------------------
			--------------------Purpose    : Transaction Sub Code Help ----------------------------------------------
		  ------------------- Added By   :N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     :24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_TRN_SUB_TYPE_sql=
			" SELECT L.NO ,L.TRN_SUB_TYPE,L.TRN_CODE,L.DESCRIPTION,NVL(L.RATE,0)AS RATE ,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRN_SUB_TYPE,P.TRN_CODE,P.DESCRIPTION,P.RATE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
			" SELECT "+
      " TRN_SUB_TYPE,"+
      " TRN_CODE,"+
      " DESCRIPTION,"+
      " RATE,"+
      " DEFAULT_VALUE,"+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE"+
		//	" WHERE TRN_SUB_TYPE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		 //---------------------ID      :1.21 Product Creation Process---------------------------------//
		//----------------------Purpose :Product Code Help -------------------------------------------------//
		//----------------------Name    :Delanjali------------------------------------------------------//
		//----------------------Date    :19-07-2006----------------------------------------------------//
		
		
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
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			

 		//----------------------ID      :1.21 Lease Stage Creation Process-----------------------------------//
		//----------------------Purpose :Leasing Code View Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//

		 m_help_TXT_DIV_sql=
		  " SELECT L.NO ,L.DIVISION,L.PRODUCT_ID,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION,P.PRODUCT_ID,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
			" DIVISION,"+
      " PRODUCT_ID,"+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_LEASE_PROCESS_STAGE"+
		 // " WHERE DIVISION LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY DIVISION ASC"+
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
		  " WHERE ACTIVE_STATUS=('Y') "+
			//" ORDER BY DIVISION ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			//------------------------------------------------------------------------------------------------
			
			
		 //---------------------ID      :1.6 Postal Code Creation Process---------------------------------//
		//----------------------Purpose :Postal Code View Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :23-08-2006----------------------------------------------------//
			m_help_TXT_POSTAL_CODE_sql=
			
			" SELECT L.NO ,L.POSTAL_CODE,L.DESCRIPTION,L.CITY_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.POSTAL_CODE,P.DESCRIPTION,P.CITY_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
		  " POSTAL_CODE, "+
      " DESCRIPTION, "+   
      " CITY_CODE,"+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS"+
      " FROM "+m_schema_name+".AF_CO_MAS_POSTAL_CODES "+
     // " WHERE POSTAL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//---------------------ID     :1.7 Location Creation Process---------------------------------//
		//----------------------Purpose :Location Code Code Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :25-08-2006----------------------------------------------------//
			
			m_view_TXT_LOCATION_CODE_sql=
			
			" SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " LOCATION_CODE, "+
      " LOCATION_DESC, "+
      " ADDRESS1,  "+
      " ADDRESS2, "+
      " CITY_CODE, "+
      " POSTAL_CODE, "+
      " COUNTRY_CODE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
     // " WHERE LOCATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
			//---------------------ID     :1.8 Division Creation Process---------------------------------//
		//----------------------Purpose :Division  Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
		
			m_help_TXT_DIVISION_CODE_sql=
			
		  " SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION,P.ACTIVE_STATUS"+
			" FROM( "+ 
   		" SELECT "+
			" DIVISION_CODE, "+
      " DESCRIPTION, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			//" WHERE DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
  		//---------------------ID     :1.25 Engine Capacity Creation Process---------------------------------//
		//----------------------Purpose :Engine Capacity View Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :24-08-2006----------------------------------------------------//
	
		m_view_TXT_CAPACITY_CODE_sql=
		  " SELECT L.NO ,L.CAPACITY_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CAPACITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " CAPACITY_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_ENGINE_CAPACITY "+
      //" WHERE CAPACITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

    m_view_TXT_USER_ID_sql=
		
		  " SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " USER_ID, "+
      " NAME, "+
      " LOCATION_CODE, "+
      " USER_TYPE, "+
      " EMP_ID, "+
      " DIVISION_CODE, "+
      " DESIGNATION_CODE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".CO_CO_MAS_USER "+
			//" WHERE USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.22 Item Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Cat Code View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//

	
		 m_view_TXT_ITEM_CAT_CODE_sql=
		
		  " SELECT L.NO ,L.ITEM_CAT_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_CAT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " ITEM_CAT_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY "+
		//	" WHERE ITEM_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ITEM_CAT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.23 Item Sub Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Sub Cat Code View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//

	 m_view_TXT_ITEM_SUB_CAT_sql=
		
		  " SELECT L.NO ,L.ITEM_SUB_CAT,L.ITEM_CAT_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_SUB_CAT,P.ITEM_CAT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
			" FROM( "+ 
      " SELECT "+
			" ITEM_SUB_CAT ,"+
      " ITEM_CAT_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
			//" WHERE ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//---------------------ID     :1.26 Fields Creation Process---------------------------------//
		//----------------------Purpose :Fields View All Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :24-08-2006----------------------------------------------------//
			m_view_TXT_FILED_CODE_sql=
		  " SELECT L.NO ,L.FILED_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
			" FROM( "+ 
      " SELECT "+
      " FILED_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_FILEDS "+
			//" WHERE FILED_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY FILED_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

     
			
			//---------------------ID     :1.27 Applicable Fields Creation Process---------------------------------//
		//----------------------Purpose :Applicable Fields   Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//	
			
      m_view_TXT_FILED_CODE_sql_applicable=
			
		  " SELECT L.NO ,L.FILED_CODE,L.ITEM_CATEGORY,L.PROCESS_STAGE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.ITEM_CATEGORY,P.PROCESS_STAGE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " FILED_CODE,"+
      " ITEM_CATEGORY, "+
      " PROCESS_STAGE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_FILEDS_APPLICABLE "+
			//" WHERE FILED_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		//----------------------Purpose :Legal Code View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chanda----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//

 
		m_view_TXT_ENTITY_CODE_sql=
			
		  " SELECT L.NO ,L.ENTITY_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ENTITY_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
			" FROM( "+ 
      " SELECT "+
      " ENTITY_CODE,"+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_LEGAL_ENTITY "+
			//" WHERE ENTITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ENTITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.35 Phone Area Code Creation Process-----------------------------------//
		//----------------------Purpose :Phone Area Code View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//


	m_view_TXT_PHONE_AREA_CODE_sql=
			
		  " SELECT L.NO ,L.PHONE_AREA_CODE,L.CITY_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO')AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PHONE_AREA_CODE,P.CITY_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
			" FROM( "+ 
      " SELECT "+
      " PHONE_AREA_CODE,"+
      " CITY_CODE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_PHONE_CODES "+
			//" WHERE PHONE_AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PHONE_AREA_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		//----------------------ID      :1.36 Product Creation Process----------------------------------//
		//----------------------Purpose :Product Code view help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :23-08-2006--------------------------------------------------------//


	m_view_TXT_PRODUCT_CODE_sql=
			
		  " SELECT L.NO ,L.PRODUCT_CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO')AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRODUCT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
			" FROM( "+ 
      " SELECT "+
      " PRODUCT_CODE,"+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_PRODUCT "+
			//" WHERE PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PRODUCT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";





	  //----------------------ID      :1.37 RMV Agent Creation Process----------------------------------//
		//----------------------Purpose :RMV Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//


	m_view_TXT_RMV_AGENT_CODE_sql=
			
			" SELECT L.NO ,L.RMV_AGENT_CODE,L.NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE, "+
			" L.MOBILE_NO,L.TEL_NO,L.MONTHLY_FEE,L.FEE_FOR_CASE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO')AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RMV_AGENT_CODE,P.NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE, "+
			" P.MOBILE_NO,P.TEL_NO,P.MONTHLY_FEE,P.FEE_FOR_CASE,P.DEFAULT_VALUE,P.ACTIVE_STATUS  "+
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
		  " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_RMV_AGENTS "+
			//" WHERE RMV_AGENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY RMV_AGENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";




 		//----------------------ID      :1.38 Seizer Creation Process----------------------------------//
		//----------------------Purpose :Seizer View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//


	m_view_TXT_SEIZER_CODE_sql=
			
			" SELECT L.NO ,L.SEIZER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS1,L.ADDRESS2,L.MOBILE_NO,L.TEL_NO,L.CITY_CODE,"+
			" L.FEE_PER_CASE,L.MONTHLY_FEE,NVL(L.VALIDITY_PERIOD,0) AS VALIDITY_PERIOD,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SEIZER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS1,P.ADDRESS2,P.MOBILE_NO,P.TEL_NO,P.CITY_CODE,"+
			" P.FEE_PER_CASE,P.MONTHLY_FEE,P.VALIDITY_PERIOD,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
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
			" VALIDITY_PERIOD, "+
		  " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
			//" WHERE SEIZER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY SEIZER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
		
		//----------------------ID      :1.39 Yard Creation Process----------------------------------//
		//----------------------Purpose :Yard View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//


	m_view_TXT_YARD_CODE_sql=
			
			" SELECT L.NO ,L.YARD_CODE,L.NAME,L.ADDRESS1,L.ADDRESS2,L.CITY_CODE,L.TEL_NO,"+
			" L.FAX_NO,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.YARD_CODE,P.NAME,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,"+
			" P.FAX_NO,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " YARD_CODE ,"+
      " NAME , "+			
			" ADDRESS1 ,"+
			" ADDRESS2 ,"+
			" CITY_CODE , "+
			" TEL_NO ,"+
			" FAX_NO  ,"+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_YARD "+
			//" WHERE YARD_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY YARD_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------ ID       : 1.28 Fuel Type Creation Process----------------------------------
			--------------------Purpose   : Fuel View Help ----------------------------------------------
		  ------------------- Added By  : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date     : 24-08-2006---------------------------------------------------------*/	
			
			m_view_TXT_CODE_sql_fuel_type=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE ,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
	  	//" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
			/*------------------ ID       : 1.29 Team Creation Process----------------------------------
			--------------------Purpose   : Team View Help ----------------------------------------------
		  ------------------- Added By  : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date     : 24-08-2006---------------------------------------------------------*/	
				
		 m_view_TXT_TEAM_ID_sql=
			
			" SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE,P.ACTIVE_STATUS "+
  		" FROM( "+ 
      " SELECT "+
      " TEAM_ID, "+
      " TEAM_DESC, "+
      " TEAM_HEAD, "+
      " DIVISION_CODE, "+
      " SUB_DIVISION_CODE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_TEAMS "+
    	//" WHERE TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------ ID       : 1.72 Marketing Team Process----------------------------------
			--------------------Purpose   : Team View Help ----------------------------------------------
		  ------------------- Added By  : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date     : 24-08-2006---------------------------------------------------------*/	
				
			
			m_view_TXT_TEAM_ID_sql_new=
			
			" SELECT L.NO ,L.TEAM_ID,L.TEAM_DESC,NVL(L.TEAM_HEAD,'N/A') AS TEAM_HEAD,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE,NVL(L.SUB_DIVISION_CODE,'N/A') AS SUB_DIVISION_CODE,L.USER_ID  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD,P.DIVISION_CODE,P.SUB_DIVISION_CODE,P.USER_ID "+
  		" FROM( "+ 
      " SELECT "+
      " A.TEAM_ID, "+
      " A.TEAM_DESC, "+
      " A.TEAM_HEAD, "+
      " A.DIVISION_CODE, "+
      " A.SUB_DIVISION_CODE, "+
			" B.USER_ID "+
      " FROM "+m_schema_name+".AF_CO_MAS_TEAMS A,"+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B "+
			" WHERE A.TEAM_ID=B.TEAM_ID "+
    	//" WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND TEAM_ID NOT IN(SELECT TEAM_ID FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS) ORDER BY TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
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
			" WHERE A.TEAM_ID=B.TEAM_ID AND A.TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


			/*--------------END OF TEAM ASSIGN----------------------------------------------------------------------------------------------------------------------------*/
			
			
			
			
			/*------------------ ID       : 1.80 Sub Division Creation Process----------------------------------
			--------------------Purpose   : Sub Division Code Help ----------------------------------------------
		  ------------------- Added By  : Nuwan De Silva------------------------------------------------------
		  -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
				
			m_view_TXT_SUB_DIVISION_CODE_sql=
			
		  " SELECT L.NO ,L.SUB_DIVISION_CODE,L.DESCRIPTION,L.DIVISION_CODE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_DIVISION_CODE,P.DESCRIPTION,P.DIVISION_CODE,P.ACTIVE_STATUS "+
  		" FROM( "+ 
      " SELECT "+
      " SUB_DIVISION_CODE,"+
      " DESCRIPTION, "+
      " DIVISION_CODE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".CO_CO_MAS_SUB_DIVISION "+
     	//" WHERE SUB_DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		//----------------------ID      :1.55 Repayment Interval Creation Process----------------------------------//
		//----------------------Purpose :Repayment Interval View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//



 m_view_TXT_DURATION_sql=
			
			" SELECT L.NO ,L.DURATION,L.DESCRIPTION,L.DURATION_TYPE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DURATION,P.DESCRIPTION,P.DURATION_TYPE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " DURATION ,"+
      " DESCRIPTION , "+			
			" DURATION_TYPE ,"+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
			//" WHERE DURATION LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY DURATION ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		
		//----------------------ID      :1.56 Revenue License Creation Process----------------------------------//
		//----------------------Purpose :Revenue View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//


 	m_view_TXT_RCODE_sql=
			
			" SELECT L.NO,L.CODE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY')AS FROM_DATE,TO_CHAR(L.TO_DATE,'DD-MM-YYYY') AS TO_DATE,L.FUEL_TYPE,L.RATE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE "+
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
			//" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
			" ORDER BY CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			//----------------------ID    :1.64 Score Rating Process----------------------------------//
		//----------------------Purpose :Score Rating View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//
			m_view_TXT_RATING_CODE_sql=
			
			" SELECT L.NO ,L.RATING_CODE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,NVL(L.FROM_RAGE,0) AS FROM_RAGE,NVL(L.TO_RANGE,0) AS TO_RANGE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATING_CODE,P.DESCRIPTION,P.FROM_RAGE,P.TO_RANGE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " RATING_CODE, "+
      " DESCRIPTION, "+
      " FROM_RAGE,"+
      " TO_RANGE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CR_MAS_SCORE_RATING "+
			//" WHERE RATING_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY RATING_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		
			//----------------------ID    :1.73 Authorization Limits Process----------------------------------//
		//----------------------Purpose : Authorization Limits View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//


	    m_view_TXT_USER_ID_Authorization_sql=
			
		  " SELECT L.NO ,L.USER_ID,L.AUTHORIZATION_LEVEL,L.LIMIT,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.AUTHORIZATION_LEVEL,P.LIMIT,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
			" FROM( "+ 
      " SELECT "+
      " USER_ID, "+
      " AUTHORIZATION_LEVEL, "+
      " LIMIT, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_AUTHORIZATION_LIMITS "+
			//" WHERE USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY USER_ID ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			//----------------------ID    :----------------------------------//
		//----------------------Purpose : Lawyer View help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :24-08-2006--------------------------------------------------------//
			
			
			m_view_TXT_LAWYER_CODE_sql=
       " SELECT L.NO ,L.LAWYER_CODE,L.FIRST_NAME,L.LAST_NAME,L.NAME_WITH_INITIALS,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2 ,L.CITY_CODE,L.TEL_NO,L.OFFICE_TEL_NO,L.MOBILE_NO,L.FAX_NO,L.OFFICE_FAX_NO,L.FEE_PER_CASE,L.MONTHLY_FEE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LAWYER_CODE,P.FIRST_NAME,P.LAST_NAME,P.NAME_WITH_INITIALS,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.OFFICE_TEL_NO,P.MOBILE_NO,P.FAX_NO,P.OFFICE_FAX_NO,P.FEE_PER_CASE,P.MONTHLY_FEE,P.ACTIVE_STATUS "+
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
      " MONTHLY_FEE, "+
      " ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_LAWYER "+
			//" WHERE LAWYER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" WHERE CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CLIENT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		 /*------------------ ID         : 1.58 Vendor Backlisting Creation Process ----------------------------------
			--------------------Purpose    : Vendor Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-08-2006---------------------------------------------------------*/
			
			m_view_TXT_VENDOR_BCODE_sql=
			" SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME,P.CATEGORY,P.TYPE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
		  " SELECT "+
      " VENDOR_CODE,"+
      " NAME, "+
			" CATEGORY, "+
			" TYPE,"+
			" DEFAULT_VALUE,"+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_VENDORS "+
	  	//" WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		//----------------------ID      :1.78 Follow up Category Creation Process----------------------------------//
		//----------------------Purpose :Follow up Code help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//

 m_view_TXT_CATEGORY_CODE_sql=
			
			" SELECT L.NO ,L.CATEGORY_CODE,L.CATEGORY_NAME,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CATEGORY_CODE,P.CATEGORY_NAME,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
			"	CATEGORY_CODE,"+
    	"	CATEGORY_NAME,"+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY "+
			//" WHERE CATEGORY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY CATEGORY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";




		//----------------------ID      :1.77 Discount Rate Creation Process----------------------------------//
		//----------------------Purpose :Rate Validation -------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :26-07-2006--------------------------------------------------------//


 m_view_TXT_RATE_sql=
			
			" SELECT L.NO ,L.RATE,TO_CHAR(L.FROM_DATE,'DD-MM-YYYY') AS FROM_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.RATE,P.FROM_DATE"+
			" FROM( "+ 
      " SELECT "+
			"	RATE,"+
    	"	FROM_DATE"+
			" FROM "+m_schema_name+".AF_CO_MAS_DISCOUNT_RATE "+
			//" WHERE RATE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
			" ORDER BY RATE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		//----------------------ID      :1.71 Variable Interest Base Process -----------------------------------//
		//----------------------Purpose :Base Code View -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//

 m_view_TXT_BASE_CODE_sql=
			
			" SELECT L.NO ,L.BASE_CODE,L.DESCRIPTION,L.RATE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BASE_CODE,P.DESCRIPTION,P.RATE,P.ACTIVE_STATUS"+
			" FROM( "+ 
      " SELECT "+
			"	BASE_CODE,"+
    	"	DESCRIPTION ,"+
			" RATE, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_CO_MAS_INTEREST_BASE "+
			//" WHERE BASE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY BASE_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		
		//----------------------ID      :1.54 Maintenance Rates Creation Process  -----------------------------------//
		//----------------------Purpose :Make Code Help-------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//


 m_view_TXT_MAIN_CODE_sql=
			
			" SELECT L.NO ,L.MAKE_CODE,L.SUB_MODEL_CODE,L.CHARGE_SUB_CODE,L.MILEAGE_CODE,L.INCREASE_DECREASE, "+
			"	L.AMOUNT,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MAKE_CODE,P.SUB_MODEL_CODE,P.CHARGE_SUB_CODE,P.MILEAGE_CODE,P.INCREASE_DECREASE, "+
			"	P.AMOUNT,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
			"	MAKE_CODE,"+
    	"	SUB_MODEL_CODE ,"+
			" CHARGE_SUB_CODE, "+
			" MILEAGE_CODE,"+
			" INCREASE_DECREASE,"+
			" AMOUNT,"+
			" DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE "+
			//" WHERE MAKE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	  	" WHERE SUB_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		   " WHERE MODEL LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		   " ORDER BY MODEL ASC"+
		   "  )P)L  "+
		   " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	


		//----------------------ID      :Holiday Process -----------------------------------//
		//----------------------Purpose :Holiday Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//

 m_view_TXT_HOLIDAY_DATE_sql=
			
			" SELECT L.NO ,TO_CHAR(L.HOLIDAY_DATE,'DD-MM-YYYY') AS HOLIDAY_DATE,L.DESCRIPTION,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.HOLIDAY_DATE,P.DESCRIPTION,P.ACTIVE_STATUS"+
			" FROM( "+ 
      " SELECT "+
			"	HOLIDAY_DATE,"+
    	"	DESCRIPTION, "+
			" ACTIVE_STATUS "+
			" FROM "+m_schema_name+".CO_CO_MAS_HOLIDAY "+
			//" WHERE TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY') LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY HOLIDAY_DATE DESC"+ //DESC Added by Chandana on 24/04/2007
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
		//----------------------ID      :1.74 Early termination Charge Process -----------------------------------//
		//----------------------Purpose :Early Termination  -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//
			
			m_view_TXT_TERMINATION_TYPE_sql=
			
		  " SELECT L.NO ,L.TERMINATION_TYPE \"TERMINATION TYPE\" ,L.DESCRIPTION,L.AMOUNT,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS \"ACTIVE STATUS\" "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TERMINATION_TYPE,P.DESCRIPTION,P.AMOUNT,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " TERMINATION_TYPE, "+
      " DESCRIPTION, "+
      " AMOUNT, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_EARLY_TERMI_CHARGE "+
			//" WHERE TERMINATION_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY TERMINATION_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

    
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Assest Usage  -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//
		
    m_view_TXT_USAGE_TYPE_sql=
		
		  " SELECT L.NO ,L.USAGE_TYPE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USAGE_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS"+
			" FROM( "+ 
      " SELECT "+
      " USAGE_TYPE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE "+
      //" WHERE USAGE_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY USAGE_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Repayment Type  -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//
		
		
		m_view_TXT_REPAYMENT_TYPE_sql=
		
		" SELECT L.NO ,L.REPAYMENT_TYPE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.REPAYMENT_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " REPAYMENT_TYPE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_METHOD "+
     // " WHERE REPAYMENT_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY REPAYMENT_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Sub Product  -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana----------------------------------------------------------//
		//----------------------Date    :25-08-2006--------------------------------------------------------//
		
			
			m_view_TXT_SUB_PRODUCT_CODE_sql=
	  		
		  " SELECT L.NO ,L.SUB_PRODUCT_CODE,L.DESCRIPTION,L.PRODUCT_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_PRODUCT_CODE,P.DESCRIPTION,P.PRODUCT_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
      " SELECT "+
      " SUB_PRODUCT_CODE, "+
      " DESCRIPTION, "+
      " PRODUCT_CODE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_SUB_PRODUCT "+
      //" WHERE SUB_PRODUCT_CODE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		  " WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
		  " WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
				
			/*------------------ ID        : Process Stage Creation Process ----------------------------------
			--------------------Purpose    : Stage Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 25-08-2006---------------------------------------------------------*/
			
			
			m_view_TXT_STAGE_CODE_sql=
			
			" SELECT L.NO,L.STAGE_CODE,L.DESCRIPTION,L.DIVISION_CODE,DECODE(L.DEFAULT_VALUE,'Y','YES','N','NO') AS DEFAULT_VALUE,DECODE(L.ACTIVE_STATUS,'Y','YES','N','NO') AS ACTIVE_STATUS "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.STAGE_CODE,P.DESCRIPTION,P.DIVISION_CODE,P.DEFAULT_VALUE,P.ACTIVE_STATUS "+
			" FROM( "+ 
  		" SELECT "+
      " STAGE_CODE, "+
      " DESCRIPTION, "+
      " DIVISION_CODE, "+
      " DEFAULT_VALUE, "+
			" ACTIVE_STATUS "+
      " FROM "+m_schema_name+".AF_CO_MAS_PROCESS_STAGE "+
      //" WHERE STAGE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			 
     
			/*------------------ ID        : User Screen Creation Process ----------------------------------
			--------------------Purpose    : Screen Help ----------------------------------------------
		  ------------------- Added By   : Nuwan De Silva------------------------------------------------------
		  -------------------- Date      : 02-08-2006---------------------------------------------------------*/
			
			
			m_view_TXT_SCREEN_NAME_sql=
	 	  " SELECT L.NO,L.SCREEN_NAME,NVL(L.DIVISION_CODE,'N/A') AS DIVISION_CODE ,NVL(L.DIVISION_SUB_CODE,'N/A') AS DIVISION_SUB_CODE ,NVL(L.OPTION_NAME,'N/A') AS OPTION_NAME ,NVL(L.SUB_OPTION1,'N/A') AS SUB_OPTION1 , "+
			"	NVL(L.SUB_OPTION2,'N/A') AS SUB_OPTION2 ,NVL(L.SUB_OPTION3,'N/A') AS SUB_OPTION3 ,NVL(L.SUB_OPTION4,'N/A') AS SUB_OPTION4 ,NVL(L.ROW_ID,0) AS ROW_ID ,NVL(L.SCREEN_URL,'N/A') AS SCREEN_URL ,L.DISPLAY_STATUS,NVL(L.SCREEN_LEVEL,0) AS SCREEN_LEVEL , "+
			" L.SUB_OPTION_STATUS,NVL(L.DISPLAY_NAME,'N/A') AS DISPLAY_NAME ,NVL(L.OPTION_ID,0) AS OPTION_ID "+
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
			//" DISPLAY_STATUS "+
      " FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
	   // " WHERE SCREEN_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
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
		" WHERE ASSET_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------ ID        : Performa Invoice Process ----------------------------------
			--------------------Purpose    : Invoice no Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 25-08-2006---------------------------------------------------------*/
			
		
		m_view_TXT_INVOICE_NO_sql=
		
		" SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS"+
		" FROM  "+
		" (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS"+
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
    " MODEL_CODE, "+
    " NVL(INVOICE_DOC_NO,'') INVOICE_DOC_NO, "+
    " NVL(CITY_CODE,'') CITY_CODE, "+
    " NVL(ADDRESS,'N/A') ADDRESS "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
   // " WHERE INVOICE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		


   	//---------------------ID       :CR/DR Standard Narrations ---------------------------------//
		//----------------------Purpose :View All Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :24-04-2007----------------------------------------------------//



   m_view_TXT_NARRATIONS_CODE_sql=
		" SELECT L.NO,L.NARRATIONS_CODE,L.NARRATIONS,L.ACTIVE_STATUS "+
		" FROM "+
		" (SELECT ROWNUM NO,NARRATIONS_CODE,NARRATIONS,ACTIVE_STATUS "+
		" FROM "+
		" (SELECT NARRATIONS_CODE, "+
		" NARRATIONS, "+
		" DECODE(ACTIVE_STATUS,'Y','Yes','N','No') ACTIVE_STATUS "+
		" FROM "+m_schema_name+".AF_CO_MAS_CR_DB_NARRATIONS))L "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//---------------------ID       :Cheque Return Narrations ---------------------------------//
		//----------------------Purpose :View All Help -------------------------------------------------//
		//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
		//----------------------Date    :2007-08-14----------------------------------------------------//


   m_view_TXT_CHEQUE_NARRATIONS_CODE_sql=
		" SELECT L.NO,L.CHQ_NARR_CODE,L.CHQ_NARRATIONS,L.ACTIVE_STATUS,L.DEFAULT_VALUE "+
		" FROM "+
		" (SELECT ROWNUM NO,CHQ_NARR_CODE,CHQ_NARRATIONS,ACTIVE_STATUS,DEFAULT_VALUE "+
		" FROM "+
		" (SELECT CHQ_NARR_CODE,CHQ_NARRATIONS, "+
		" DECODE(ACTIVE_STATUS,'Y','Yes','N','No') ACTIVE_STATUS,DEFAULT_VALUE "+
		" FROM "+m_schema_name+".AF_CO_MAS_CHEQUE_NARRATIONS))L "+
		" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

	//---------------------ID       :Reference Code ---------------------------------//
 	//----------------------Purpose :View All Help -------------------------------------------------//
	//----------------------Name    :N.V.P.Chandana------------------------------------------------------//
	//----------------------Date    :24-04-2007----------------------------------------------------//

 m_view_TXT_REFRENCE_CODE_sql=
	 " SELECT L.NO,L.CODE,L.DESCRIPTION,L.INSERT_SCREEN,L.ACTIVE_STATUS "+
	 " FROM "+
	 " (SELECT ROWNUM NO,CODE,DESCRIPTION,INSERT_SCREEN,ACTIVE_STATUS "+
	 " FROM "+
	 " (SELECT "+
	 " A.CODE, "+
	 " A.DESCRIPTION, "+
	 " INITCAP(B.DISPLAY_NAME) INSERT_SCREEN, "+
	 " DECODE(A.ACTIVE_STATUS,'Y','Yes','N','No') ACTIVE_STATUS "+
	 " FROM "+m_schema_name+".AF_MK_CONDITIONS A, "+m_schema_name+".CO_CO_MAS_USER_SCREEN B "+
	 " WHERE A.INSERT_SCREEN=B.SCREEN_NAME))L "+
	 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";









		 /*------------------ ID        : 1.58 Valuation Process ----------------------------------
			--------------------Purpose    : Valuation Code Help ----------------------------------------------
		  ------------------- Added By   : Delanjali------------------------------------------------------
		  -------------------- Date      : 06-08-2006---------------------------------------------------------*/
		m_help_TXT_VALUATION_NO1_sql=
			" SELECT L.NO ,L.VALUATION_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO,L.CHASSIS_NO,L.COLOUR,"+
   		" L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE ,L.VALUE,L.TYPE_OF_BODY,TO_CHAR(L.DATE_OF_REG,'DD-MM-YYYY') AS DATE_OF_REG,L.METER_READING,"+
    	" L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,"+
    	"	P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,"+
    	" P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS "+
			" FROM( "+ 
  		" SELECT "+
      " VALUATION_NO,"+
			"	ASSET_ID,"+
			"	SUB_MODEL_CODE,"+
			"	REG_NO,"+
			"	ENGINE_NO,"+
			"	CHASSIS_NO,"+
			"	COLOUR,"+
			"	MODEL_CODE,"+
			"	NOTES,"+
			"	REMARKS,"+
			"	VALUATION_DATE,"+
			"	VALUE,"+
			"	TYPE_OF_BODY,"+
			"	DATE_OF_REG,"+
			"	METER_READING,"+
			"	ACTIVE_STATUS,"+
			"	GENERAL_INDEX,"+
			"	SEATING_CAPACITY,"+
			"	NO_OF_CYLINDERS"+
			
      " FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			//" FROM LAKDL.AF_CO_PRO_APP_VALUATION "+
		  " WHERE VALUATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
		  " WHERE x.VALUATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND x.ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
			" AND A.MAKE_CODE=B.MAKE_CODE"+
 			"	AND B.ITEM_SUB_CAT=C.ITEM_SUB_CAT"+
			"	AND A.MODEL_CODE=X.MODEL_CODE"+
			"	AND A.MODEL_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')"+
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
			"	AND A.MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
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
		  " WHERE VALUER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
	
		
			
			m_help_TXT_MODEL_CODE_sql1=
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
	  	" WHERE MAKE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND MODEL_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
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
	  	" WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')  AND MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
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
	  	" WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		" WHERE ASSET_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

//---added by delanjali on 2007-09-12----------------------------------------------------------------------------------------------------------------------------------------------

			m_help_TXT_GRP_INV_sql=			
			" SELECT L.NO ,L.GROUP_CODE,L.GROUP_NAME,L.GROUP_ADDRESS,L.BRC_NO, "+
			" L.CLIENT_CODE,L.REMARKS,L.PERCENTAGE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.GROUP_CODE,P.GROUP_NAME,P.GROUP_ADDRESS,P.BRC_NO, "+
			" P.CLIENT_CODE,P.REMARKS,P.PERCENTAGE "+
			" FROM( "+ 
		  " SELECT DISTINCT A.GROUP_CODE GROUP_CODE,A.GROUP_NAME GROUP_NAME,A.GROUP_ADDRESS GROUP_ADDRESS,A.BRC_NO BRC_NO, "+
		  " B.CLIENT_CODE CLIENT_CODE,NVL(B.REMARKS,'-') REMARKS,NVL(B.PERCENTAGE,0) PERCENTAGE "+
		  " FROM "+m_schema_name+".AF_RE_PRO_GROUP_INVOICES A,"+m_schema_name+".AF_RE_PRO_ASSN_GROUP_INV B "+
		  " WHERE A.GROUP_CODE=B.GROUP_CODE  "+	
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


