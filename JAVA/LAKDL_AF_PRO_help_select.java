import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_PRO_help_select  {  

	public Object Ret_Object = new Object();
	//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS();
	 LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();

	//------------------------------------------------------------------------
	
	
	
	
	public String m_help_TXT_INVOICE_NO_sql= "";
	public String m_help_TXT_INVOICE_NO_sql_Header= "Application Processing - Invoice Number ";
	
	public String m_help_TXT_ITEM_SUB_CAT_sql_NEW= "";
	public String m_help_TXT_ITEM_SUB_CAT_sql_NEW_Header ="System Administration - Item Sub Category";
	
	public String m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql= "";
	public String m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql_Header ="System Administration - Item Sub Category Help";
	
	public String m_help_TXT_ASSET_ID_sql= "";
	public String m_help_TXT_ASSET_ID_sql_Header= "Application Processing - Asset ID ";
	
	
	public String m_help_TXT_SCREEN_NAME_sql= "";
	public String m_help_TXT_SCREEN_NAME_sql_Header= "System Administration - User Screen";
	
	
	public String m_help_TXT_STAGE_CODE_sql= "";
	public String m_help_TXT_STAGE_CODE_sql_Header= "System Administration - Process Stage";
	
	public String m_help_TXT_SUB_PRODUCT_CODE_sql= "";
	public String m_help_TXT_SUB_PRODUCT_CODE_sql_Header= "System Administration - Sub Product";
	
	
	public String m_help_TXT_REPAYMENT_TYPE_sql= "";
	public String m_help_TXT_REPAYMENT_TYPE_sql_Header= "System Administration - Assest Usage";
		
	public String m_help_TXT_USAGE_TYPE_sql= "";
	public String m_help_TXT_USAGE_TYPE_sql_Header= "System Administration - Assest Usage";
	
	public String m_help_TXT_TERMINATION_TYPE_sql= "";
	public String m_help_TXT_TERMINATION_TYPE_sql_Header= "System Administration - Early Termination Charge";
	
	public String m_help_TXT_TEAM_ID_team_sql= "";
	public String m_help_TXT_TEAM_ID_team_sql_Header= "System Administration - Team Assign";
	
	
	public String m_help_TXT_TRN_SUB_TYPE_sql= "";
	public String m_help_TXT_TRN_SUB_TYPE_sql_Header= "System Administration - Transaction Sub Type";
		
	public String m_help_TXT_TRAN_CODE_sql= "";
	public String m_help_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
	
	public String m_help_vat_on_rental_transaction_code ="";
	public String m_help_vat_on_rental_transaction_code_Header="System Administration - Transaction";
	
	public String m_help_vat_on_rental_delete_help="";
	public String m_help_vat_on_rental_delete_help_Header="System Administration - Transaction";
	
	public String m_help_TXT_SOURCE_CODE_sql= "";
	public String m_help_TXT_SOURCE_CODE_sql_Header= "System Administration - Lead Source Category";
	
	public String m_help_TXT_VEHICLE_NO_sql= "";
	public String m_help_TXT_VEHICLE_NO_sql_Header= "System Administration - Missing Vehicle";
	
	public String m_help_TXT_OPTION_CODE_sql= "";
	public String m_help_TXT_OPTION_CODE_sql_Header= "System Administration - Option";
	
	public String m_help_TXT_MODEL_sql= "";
	public String m_help_TXT_MODEL_sql_Header= "System Administration - Mileage";
	
	public String m_help_TXT_LAWYER_CODE_sql= "";
	public String m_help_TXT_LAWYER_CODE_sql_Header= "System Administration - Lawyer";
	
	public String m_help_TXT_SUB_M_CODE_sql= "";
	public String m_help_TXT_SUB_M_CODE_sql_Header= "System Administration - Sub Model";
	
	public String m_help_TXT_USER_ID_Authorization_sql= "";
	public String m_help_TXT_USER_ID_Authorization_sql_Header= "System Administration - Authorization";

	public String m_help_TXT_VALUER_CODE_sql= "";
	public String m_help_TXT_VALUER_CODE_sql_Header= "System Administration - Valuer";

	public String m_help_TXT_RATING_CODE_sql= "";
	public String m_help_TXT_RATING_CODE_sql_Header= "System Administration - Score Rating";

	public String m_help_TXT_L_S_CODE_sql= "";
	public String m_help_TXT_L_S_CODE_sql_Header= "System Administration - Lead Source";

	
	public String m_help_TXT_INQ_CODE_sql= "";
	public String m_help_TXT_INQ_CODE_sql_Header= "System Administration - Inquiry Status";
	
  public String m_help_TXT_NATIONALITY_CODE_sql= "";
	public String m_help_TXT_NATIONALITY_CODE_sql_Header= "System Administration - Nationality";
	
	
	public String m_help_TXT_I_T_CODE_sql= "";
	public String m_help_TXT_I_T_CODE_sql_Header= "System Administration - Interest Type";
	
	public String m_help_TXT_I_E_CODE_sql= "";
	public String m_help_TXT_I_E_CODE_sql_Header= "Systen Administration - Income Expence Type";
	
	public String m_help_TXT_GARAGE_CODE_sql= "";
	public String m_help_TXT_GARAGE_CODE_sql_Header= "Systen Administration - Garage";
	
	public String m_help_TXT_BROKER_CODE_sql= "";
	public String m_help_TXT_BROKER_CODE_sql_Header= "Systen Administration - Broker";
	
	public String m_help_TXT_BRANCH_CODE_sql= "";
	public String m_help_TXT_BRANCH_CODE_sql_Header= "System Administration - Bank Branch";
	
	public String m_help_TXT_SCORE_CODE_sql= "";
	public String m_help_TXT_SCORE_CODE_sql_Header= "Client Help - Credit Score Category";
	
	public String m_help_TXT_SCORE_SUB_CODE_sql="";
	public String m_help_TXT_SCORE_SUB_CODE_sql_Header= "Client Help - Credit Score Sub Category";
	
	public String m_help_TXT_SCORE_MODEL_CODE_sql="";
	public String m_help_TXT_SCORE_MODEL_CODE_sql_Header= "Client Help - Credit Score Model Creation";

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
	
	public String m_help_TXT_LOCATION_CODE_sql="";
	public String m_help_TXT_LOCATION_CODE_sql_Header="System Administration - Location";
	
	public String m_help_TXT_SECTOR_CODE_sql="";
	public String m_help_TXT_SECTOR_CODE_sql_Header="System Administration - Business Sector";
	
	public String m_help_TXT_BANK_CODE_sql="";
  public String m_help_TXT_BANK_CODE_sql_Header="System Administration - Bank";
	
	public String m_help_TXT_DIVISION_CODE_sql="";
  public String m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_DIVISION1_CODE_sql="";
  public String m_help_TXT_DIVISION1_CODE_sql_Header="System Administration - Division";
	
	public String m_help_TXT_CAPACITY_CODE_sql="";
  public String m_help_TXT_CAPACITY_CODE_sql_Header="System Administration - Engine Capacity";
	
	public String m_help_TXT_USER_ID_sql="";
  public String m_help_TXT_USER_ID_sql_Header="System Administration - User";
	
	public String	m_help_TXT_TYPE_CODE_sql="";
	public String	m_help_TXT_TYPE_CODE_sql_Header="System Administration - Charges";
	

	public String	m_help_TXT_ITEM_CAT_CODE_sql="";
	public String	m_help_TXT_ITEM_CAT_CODE_sql_Header="System Administration - Item Category";

	public String	m_help_TXT_ITEM_SUB_CAT_sql="";
	public String	m_help_TXT_ITEM_SUB_CAT_sql_Header="System Administration - Item Sub Category";
	
	public String	m_help_TXT_FILED_CODE_sql="";
	public String	m_help_TXT_FILED_CODE_sql_Header="System Administration - Filed Code";
	
	public String	m_help_TXT_FILED_CODE_sql_applicable="";
	public String	m_help_TXT_FILED_CODE_sql_applicable_Header="System Administration - Applicable Filed Code";//Added By Nuwan De Silva(Modified)
	
	public String	m_help_TXT_SUB_CODE_sql="";
	public String	m_help_TXT_SUB_CODE_sql_Header="System Administration - Business Sub Sector";
	
	public String m_help_TXT_ENTITY_CODE_sql="";
	public String m_help_TXT_ENTITY_CODE_sql_Header="System Administration - Legal Entity Codes ";
	 
	public String m_help_TXT_PHONE_AREA_CODE_sql="";
	public String m_help_TXT_PHONE_AREA_CODE_sql_Header="System Administration - Phone Area Codes ";

	public String m_help_TXT_PRODUCT_CODE_sql="";
	public String m_help_TXT_PRODUCT_CODE_sql_Header="System Administration - Product Codes";
	
	public String m_help_TXT_SUB_TYPE_CODE_sql="";
  public String m_help_TXT_SUB_TYPE_CODE_sql_Header="System Administration - Sub Type Code";
	
	public String m_help_TXT_FUAL_TYPE_CODE_sql="";
	public String m_help_TXT_FUAL_TYPE_CODE_sql_Header="System Administration - Fual Type Code";
	
	public String m_help_TXT_ITEM_SUB_CAT_sql_1="";
	public String m_help_TXT_ITEM_SUB_CAT_sql_1_Header="System Administration - Item Sub Catogory Code";
	
	public String m_help_TXT_RMV_AGENT_CODE_sql="";
	public String m_help_TXT_RMV_AGENT_CODE_sql_Header="System Administration - RMV Agents Code";
	
	public String m_help_TXT_CODE_sql="";
	public String m_help_TXT_CODE_sql_Header="System Administration - Asset Condition Code";
  
	public String m_help_TXT_SEIZER_CODE_sql="";
	public String m_help_TXT_SEIZER_CODE_sql_Header="System Administration - Seizer Code";

	public String m_help_TXT_YARD_CODE_sql="";
	public String m_help_TXT_YARD_CODE_sql_Header="System Administration - Yard Code";
	
	public String m_help_TXT_CODE_sql_fuel_type="";
	public String m_help_TXT_CODE_sql_fuel_type_Header="System Administration - Fuel type";
	
	public String m_help_TXT_MAKE_CODE_sql="";
	public String m_help_TXT_MAKE_CODE_sql_Header="System Administration - Make Code";
	
	public String m_help_TXT_TEAM_ID_sql="";
	public String m_help_TXT_TEAM_ID_sql_Header="System Administration - Team ID";
	
	public String m_help_TXT_SUB_DIVISION_CODE_sql="";
	public String m_help_TXT_SUB_DIVISION_CODE_sql_Header="System Administration - Sub Division ID";
	
	public String m_help_TXT_DURATION_sql="";
	public String m_help_TXT_DURATION_sql_Header="System Administration - Repayment Interval";
	
	public String m_help_TXT_RCODE_sql="";
	public String m_help_TXT_RCODE_sql_Header="System Administration - Revenue License";
	
	public String m_help_TXT_MODEL_CODE_sql="";
	public String m_help_TXT_MODEL_CODE_sql_Header="System Administration - Model Creation";
	
	public String m_help_TXT_VENDOR_CODE_sql="";
	public String m_help_TXT_VENDOR_CODE_sql_Header="System Administration - Vender Creation";
	
	public String m_help_TXT_CAT_TYPE_CODE_sql="";
	public String m_help_TXT_CAT_TYPE_CODE_sql_Header="System Administration - Customer Category";
	
	public String m_help_TXT_INITIATION_CODE_sql="";
	public String m_help_TXT_INITIATION_CODE_sql_Header="System Administration - Initiation Type";
	
	public String m_help_TXT_CATEGORY_sql="";
	public String m_help_TXT_CATEGORY_sql_Header="System Administration - Item Category";
	
	public String m_help_TXT_CODE_sql_1="";
  public String m_help_TXT_CODE_sql_1_Header="System Administration - Document Required";
	
	public String m_help_TXT_CLIENT_CODE_sql="";
	public String m_help_TXT_CLIENT_CODE_sql_Header="System Administration -Backlisted Clients ";
	
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

	public String m_help_TXT_RATE_sql="";
	public String m_help_TXT_RATE_sql_Header="System Administration - Discount Rate";
	
	public String m_help_TXT_TEAM_ID_sql_new="";
	public String m_help_TXT_TEAM_ID_sql_new_Header="System Administration - Assign Team Members";
	
	public String m_help_TXT_BASE_CODE_sql="";
	public String m_help_TXT_BASE_CODE_sql_Header="System Administration - Variable Interest Rates";

	public String m_help_TXT_MAIN_CODE_sql="";
	public String m_help_TXT_MAIN_CODE_sql_Header="System Administration - Maintanance Rate";

	public String m_help_TXT_SUB_MODEL_CODE_sql="";
	public String m_help_TXT_SUB_MODEL_CODE_sql_Header="System Administration - Sub Model Creation";

	public String m_help_TXT_CHARGE_SUB_CODE_sql="";
	public String m_help_TXT_CHARGE_SUB_CODE_sql_Header="System Administration - Sub Charge";

	public String m_help_TXT_MILEAGE_CODE_sql="";
	public String m_help_TXT_MILEAGE_CODE_sql_Header="System Administration - Milage";

	public String m_help_TXT_HOLIDAY_DATE_sql="";
	public String m_help_TXT_HOLIDAY_DATE_sql_Header="System Administration - Holidays";
	
	
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
	
	public String m_help_TXT_ACCOUNT_CODE_sql="";
  public String m_help_TXT_ACCOUNT_CODE_sql_Header="System Administration - Account Code Creation";

	public String m_help_TXT_SUB_MODEL_sql="";
	public String m_help_TXT_SUB_MODEL_sql_Header="System Administration -Sub Model Creation";
	
	public String m_help_TXT_ASSET_ID_sql1= "";
	public String m_help_TXT_ASSET_ID_sql1_Header= "Application Processing - Asset ID ";
	
	public String m_help_TXT_APPLICATION_NO_sql="";
	public String m_help_TXT_APPLICATION_NO_sql_Header="System Administration -Applicable Client Documents";
	
	public String m_view_sql="";
	public String m_view_sql_Header="Valution Details";
	
	public String m_help_TXT_FOLLOW_UP_NO_sql="";
  public String m_help_TXT_FOLLOW_UP_NO_sql_Header="Follow Up Details";
		
	public String m_help_TXT_PRICING_NO_sql="";
  public String m_help_TXT_PRICING_NO_sql_Header="Pricing Details";
	
	public String m_help_TXT_APPLICATION_NO_x_sql="";
	public String m_help_TXT_APPLICATION_NO_x_sql_Header="System Administration -Applicable Client Documents";
	

		
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
		
		
		
		
		m_help_TXT_SCORE_CODE_sql=
			" SELECT L.NO ,L.SCORE_CODE,L.DESCRIPTION,L.DISPLAY_POSITION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SCORE_CODE,P.DESCRIPTION,P.DISPLAY_POSITION "+
			" FROM( "+ 
			" SELECT "+ 
			" SCORE_CODE, "+ 
			" DESCRIPTION, "+ 
			" DISPLAY_POSITION "+ 
			" FROM LAKDL.AF_CR_MAS_SCORE_CATEGORY "+ 
			" WHERE SCORE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
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
			" FROM LAKDL.AF_CR_MAS_SCORE_SUB_CATEGORY "+ 
			" WHERE SCORE_SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
	 		" FROM LAKDL.AF_CR_MAS_SCORE_MODEL "+
			" WHERE SCORE_MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
		" FROM LAKDL.AF_CR_PRO_CRSCORE "+ 
		" WHERE APP_STATUS='ENTER' AND APPLICATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
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
      " FROM LAKDL.AF_CO_MAS_PROVINCE "+
		  " WHERE PROVINCE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_CITY "+
		  " WHERE CITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_COUNTRY "+
	  	" WHERE COUNTRY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_DISTRICT "+
	  	" WHERE DISTRICT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_BUSINESS_SECTOR "+
	  	" WHERE SECTOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_CHARGES "+
	  	" WHERE TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_SUB_BUSINESS_SECTORS "+
	  	" WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------  ID      : 1.18 Applicable Charges Creation Process-----------------------------------
			--------------------Purpose    : Sub Type Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  --------------------  Date     : 21-07-2006---------------------------------------------------------*/
		
			m_help_TXT_SUB_TYPE_CODE_sql=
			" SELECT L.NO ,L.SUB_TYPE_CODE,L.TYPE_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUB_TYPE_CODE,P.TYPE_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
		  " SELECT "+
      " SUB_TYPE_CODE,"+
			" TYPE_CODE,"+
      " DESCRIPTION, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_SUB_CHARGES "+
	  	" WHERE SUB_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_FUEL_TYPE "+
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_CHARGES_APPLICABLE "+
	  	" WHERE ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_CONDITION_OF_ASSET "+
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
				 /*------------------ ID     : 1.40 Make Creation Process ----------------------------------
			--------------------Purpose    : Make Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-07-2006---------------------------------------------------------*/
			
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
      " FROM LAKDL.AF_CO_MAS_MAKE "+
	  	" WHERE MAKE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID       : 1.41 Model Creation Process ----------------------------------
			--------------------Purpose    :Model Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-07-2006---------------------------------------------------------*/
			
			m_help_TXT_MODEL_CODE_sql=
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
      " FROM LAKDL.AF_CO_MAS_MODEL "+
	  	" WHERE MODEL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
				 /*------------------ ID     : 1.42 Vendor Creation Process ----------------------------------
			--------------------Purpose    : Vendor Code Help ----------------------------------------------
		  ------------------- Added By   : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date      : 24-07-2006---------------------------------------------------------*/
			
			m_help_TXT_VENDOR_CODE_sql=
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
      " FROM LAKDL.AF_CO_MAS_VENDORS "+
	  	" WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_ITEM_CATEGORY "+
	  	" WHERE ITEM_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_MK_MAS_CUSTOMER_CATOGORY "+
	  	" WHERE CAT_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_MK_MAS_INITIATION_TYPE "+
	  	" WHERE INITIATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			 /*------------------ ID        : 1.50 Document Required Creation Process  ----------------------------------
			-------------------- Purpose    : Document Required Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 25-07-2006---------------------------------------------------------*/
			m_help_TXT_CODE_sql_1=
			" SELECT L.NO ,L.CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " DESCRIPTION "+
      " FROM LAKDL.AF_CO_MAS_DOCUMENTS_REQUIRED "+
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------ ID         : 1.51 Applicable Document Type Creation Process  ----------------------------------
			-------------------- Purpose    : Applicable Document Type Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 26-07-2006---------------------------------------------------------*/
	
			
			m_help_TXT_CODE_sql_2=
			" SELECT L.NO ,L.CODE,L.ENTITY_TYPE,L.STAGE,L.ITEM_CAT_CODE,L.ENTITY_CODE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CODE,P.ENTITY_TYPE,P.STAGE,P.ITEM_CAT_CODE,P.ENTITY_CODE "+
			" FROM( "+ 
		  " SELECT "+
      " CODE,"+
      " ENTITY_TYPE, "+
			" STAGE,"+
			" ITEM_CAT_CODE,"+
			" ENTITY_CODE "+
      " FROM LAKDL.AF_CO_MAS_DOCUMENT_APPLICABLE "+
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_LEGAL_ENTITY "+
	  	" WHERE ENTITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_STAGE "+
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
      " FROM LAKDL.AF_CO_PRO_APP_CLIENT_DOCS "+
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
			
      " FROM LAKDL.AF_CO_MAS_CLIENT "+
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
      " FROM LAKDL.AF_MK_MAS_CUSTOMER_CATOGORY "+
	  	" WHERE CAT_TYPE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			
				/*------------------ ID       : 1.53 Account Code Creation Process  ----------------------------------
			-------------------- Purpose    : Account Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 08-08-2006---------------------------------------------------------*/		
						
			
			m_help_TXT_ACCOUNT_CODE_sql=
			" SELECT L.NO,L.ACCOUNT_CODE,L.DESCRIPTION,L.REPORT_TYPE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ACCOUNT_CODE,P.DESCRIPTION,P.REPORT_TYPE "+
			" FROM( "+ 
		  " SELECT "+
      " ACCOUNT_CODE,"+
      " DESCRIPTION,"+
			" REPORT_TYPE "+
      " FROM LAKDL.AF_CO_ACC_ACCOUNTS_CODE "+
	  	" WHERE ACCOUNT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	
	
	
		/*------------------     ID       :   ----------------------------------
			-------------------- Purpose    : Follow Up Code Help ----------------------------------------------
		  ------------------- Added By    : N.V.P.Chandana------------------------------------------------------
		  -------------------- Date       : 25-08-2006---------------------------------------------------------*/		
			
			
			m_help_TXT_FOLLOW_UP_NO_sql=
			 " SELECT L.NO,L.FOLLOW_UP_NO,L.ID_NO,L.ACTION_TOBE_TAKEN,L.EFF_VAL_DATE,L.ACTION_TAKEN,L.ACTION_DATE,L.ACTION_SET_FOR,L.SCREEN_NAME, "+
				" L.DIVISION_CODE,L.ENT_REMARKS,L.REMARKS,L.ACTION_ENT_DATE,L.STATUS,L.PRIORITY "+
				" FROM  "+
			" (SELECT ROWNUM NO,P.FOLLOW_UP_NO,P.ID_NO,P.ACTION_TOBE_TAKEN,P.EFF_VAL_DATE,P.ACTION_TAKEN,P.ACTION_DATE,P.ACTION_SET_FOR,P.SCREEN_NAME, "+
				" P.DIVISION_CODE,P.ENT_REMARKS,P.REMARKS,P.ACTION_ENT_DATE,P.STATUS,P.PRIORITY "+
			" FROM( "+ 
		  " SELECT "+
			" FOLLOW_UP_NO,ID_NO,ACTION_TOBE_TAKEN,EFF_VAL_DATE,ACTION_TAKEN,ACTION_DATE,ACTION_SET_FOR,SCREEN_NAME, "+
				" DIVISION_CODE,ENT_REMARKS,REMARKS,ACTION_ENT_DATE,STATUS,PRIORITY "+
			" FROM LAKDL.AF_CO_PRO_FOLLOW_UP "+
	  	" WHERE FOLLOW_UP_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
			"  )P)L  "+
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
      " FROM LAKDL.AF_CO_MAS_AREA"+
			" WHERE AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			

		 //---------------------ID      :1.20 Currency Creation Process---------------------------------//
		//----------------------Purpose :Currency Code Help -------------------------------------------------//
		//----------------------Name    :Delanjali------------------------------------------------------//
		//----------------------Date    :19-07-2006----------------------------------------------------//

	
		m_help_TXT_CURR_CODE_sql=
		" SELECT L.NO ,L.CURR_CODE,L.CURR_SYMBOL,L.REP_CURR,TO_CHAR(L.TRN_DATE,'DD-MM-YYYY') AS TRN_DATE,L.CATEGORY_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.CURR_CODE,P.CURR_SYMBOL,P.REP_CURR,P.TRN_DATE,P.CATEGORY_CODE,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " CURR_CODE, "+
      " CURR_SYMBOL, "+
			" REP_CURR, "+
      " TRN_DATE, "+
			" CATEGORY_CODE, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_CURRENCY "+
		  " WHERE CURR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			//" ORDER BY CURR_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID         : 1.10 Designation Creation Process-----------------------------------------
			--------------------Purpose    :Designation Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :19-07-2006---------------------------------------------------------*/

    m_help_TXT_DESIGNATION_CODE_sql=
		" SELECT L.NO ,L.DESIGNATION_CODE,L.DESIGNATION_NAME,NVL(L.DESIGNATION_LEVEL,0),L.DIVISION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DESIGNATION_CODE,P.DESIGNATION_NAME,P.DESIGNATION_LEVEL,P.DIVISION "+
			" FROM( "+ 
  		" SELECT "+
      " DESIGNATION_CODE, "+
      " DESIGNATION_NAME, "+
			" DESIGNATION_LEVEL, "+
      " DIVISION "+
      " FROM LAKDL.CO_CO_MAS_DESIGNATION "+
		  " WHERE DESIGNATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.CO_CO_MAS_DIVISION "+
		  " WHERE DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		/*------------------  ID         : 1.11 Employee Creation Process-----------------------------------------
			--------------------Purpose    :Emp Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :19-07-2006---------------------------------------------------------*/
			
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
      " FROM LAKDL.CO_CO_MAS_EMPLOYEE "+
		  " WHERE EMP_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_BANKS "+
		  " WHERE BANK_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
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
      " FROM LAKDL.AF_CO_MAS_BANK_BRANCH "+
		  " WHERE BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_BROKER "+
		  " WHERE BROKER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
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
      " FROM LAKDL.AF_CO_MAS_GARAGE "+
		  " WHERE GARAGE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			
			/*------------------  ID       : 1.31 Income / Expense Type Creation Process-----------------------------------------
			--------------------Purpose    :Income Expence Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :24-07-2006---------------------------------------------------------*/
			
			m_help_TXT_I_E_CODE_sql=
			" SELECT L.NO ,L.I_E_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.I_E_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " I_E_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_INCOME_EXPENCE_TYPE "+
		  " WHERE I_E_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_INTEREST_TYPE "+
		  " WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------  ID       : 1.34 Nationality Creation Process-----------------------------------------
			--------------------Purpose    :Nationality Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :24-07-2006---------------------------------------------------------*/
			
			
			m_help_TXT_NATIONALITY_CODE_sql=
			" SELECT L.NO ,L.NATIONALITY_CODE,L.DESCRIPTION"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.NATIONALITY_CODE,P.DESCRIPTION "+
			" FROM( "+ 
  		" SELECT "+
      " NATIONALITY_CODE , "+
      " DESCRIPTION  "+
      " FROM LAKDL.AF_CO_MAS_NATIONALITY "+
		  " WHERE NATIONALITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_INQUARY_STATUS "+
		  " WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_MK_MAS_LEAD_SOURCE "+
		  " WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.46 Valuer Creation Process-----------------------------------------
			--------------------Purpose    :Valuer Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :25-07-2006---------------------------------------------------------*/
			
			m_help_TXT_VALUER_CODE_sql=
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
         " FROM LAKDL.AF_CO_MAS_VALUERS "+
				 " WHERE VALUER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			/*------------------  ID       : 1.48 Sub Model Creation Process-----------------------------------------
			--------------------Purpose    :Sub Model Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :25-07-2006---------------------------------------------------------*/
			
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
         " FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
				 " WHERE SUB_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			 " ORDER BY SUB_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.47 Mileage Creation Process-----------------------------------------
			--------------------Purpose    : Model Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :25-07-2006---------------------------------------------------------*/
			
			m_help_TXT_MODEL_sql=
			" SELECT L.NO ,L.MODEL,L.SUB_MODEL,L.CONDITION_OF_ASSET,L.USAGE_FROM,L.USAGE_TO,L.AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.MODEL,P.SUB_MODEL,P.CONDITION_OF_ASSET,P.USAGE_FROM,P.USAGE_TO,P.AMOUNT "+
			" FROM( "+
		   " SELECT "+
       "  MODEL,"+
       "  SUB_MODEL,"+
       "  CONDITION_OF_ASSET,"+
       "  USAGE_FROM,"+
       "  USAGE_TO,"+
       "  AMOUNT "+
       " FROM LAKDL.AF_CO_MAS_MILEAGE "+
		   " WHERE MODEL LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		   " ORDER BY MODEL ASC"+
		  "  )P)L  "+
		 " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
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
      " FROM LAKDL.AF_CO_MAS_OPTION_TYPE "+
		  " WHERE OPTION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_PRO_MISSING_VEHICLES "+
		  " WHERE VEHICLE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') "+
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
      " FROM LAKDL.AF_MK_MAS_LEAD_SOURCE_CAT "+
		  " WHERE SOURCE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			/*------------------  ID       : 1.69 Transaction Process-----------------------------------------
			--------------------Purpose    : Transaction Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :26-07-2006---------------------------------------------------------*/
			
			m_help_TXT_TRAN_CODE_sql=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " TRAN_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_TRANSACTION_TYPE "+
		  " WHERE TRAN_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			//added by nuwan de silva 30-07-07-------------------------------------------------------------
			m_help_vat_on_rental_transaction_code=
			" SELECT L.NO ,L.TRAN_CODE,L.DESCRIPTION,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRAN_CODE,P.DESCRIPTION,P.DEFAULT_VALUE "+
			" FROM( "+ 
  		" SELECT "+
      " TRAN_CODE , "+
      " DESCRIPTION, "+
			" DEFAULT_VALUE "+
      " FROM "+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE "+
		  " WHERE TRAN_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" AND TRAN_CODE NOT IN (SELECT NVL(TRN_CODE,'-') FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			//-----------------------------------------------------------------------------------------------
			
			
			/*------------------  ID       : 1.70 Transaction Sub Type Process-----------------------------------------
			--------------------Purpose    : Transaction Sub Code Help ----------------------------------------------
		  ------------------- Added By   :M.M. Wickramasekara------------------------------------------------------
		  --------------------  Date     :26-07-2006---------------------------------------------------------*/
			
			m_help_TXT_TRN_SUB_TYPE_sql=
			" SELECT L.NO ,L.TRN_SUB_TYPE,L.TRN_CODE,L.DESCRIPTION,NVL(L.RATE,0)AS RATE ,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRN_SUB_TYPE,P.TRN_CODE,P.DESCRIPTION,P.RATE,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
      " TRN_SUB_TYPE,"+
      " TRN_CODE,"+
      " DESCRIPTION,"+
      " RATE,"+
      " DEFAULT_VALUE"+
      " FROM LAKDL.AF_CO_MAS_TRANSACTION_SUB_TYPE"+
			" WHERE TRN_SUB_TYPE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_PRODUCT "+
		  " WHERE PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY PRODUCT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

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
      " FROM LAKDL.AF_CO_LEASE_PROCESS_STAGE"+
		  " WHERE DIVISION LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.CO_CO_MAS_DIVISION "+
		  " WHERE ACTIVE_STATUS=('Y') "+
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
      " FROM LAKDL.AF_CO_MAS_POSTAL_CODES "+
      " WHERE POSTAL_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//---------------------ID     :1.7 Location Creation Process---------------------------------//
		//----------------------Purpose :Location Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
			
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
      " FROM LAKDL.AF_CO_MAS_LOCATION "+
      " WHERE LOCATION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" FROM LAKDL.CO_CO_MAS_DIVISION "+
			 " WHERE DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_ENGINE_CAPACITY "+
      " WHERE CAPACITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

    m_help_TXT_USER_ID_sql=
		
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
      " FROM LAKDL.CO_CO_MAS_USER "+
			" WHERE USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.22 Item Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Cat Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :20-07-2006--------------------------------------------------------//

	
		 m_help_TXT_ITEM_CAT_CODE_sql=
		
		  " SELECT L.NO ,L.ITEM_CAT_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.ITEM_CAT_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
      " SELECT "+
      " ITEM_CAT_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_ITEM_CATEGORY "+
			" WHERE ITEM_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ITEM_CAT_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.23 Item Sub Category Creation Process-----------------------------------//
		//----------------------Purpose :Item Sub Cat Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :20-07-2006--------------------------------------------------------//

	 m_help_TXT_ITEM_SUB_CAT_sql=
		
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
			" WHERE (ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			//comment by nuwan de silva 30-07-07-------------------------------------------
		/*m_help_TXT_ITEM_SUB_CAT_sql_NEW=
			
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
			" WHERE (ITEM_SUB_CAT LIKE UPPER('"+m_vector.elementAt(0)+"%') OR UPPER(DESCRIPTION) LIKE UPPER('"+m_vector.elementAt(0)+"%') )  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" AND ITEM_SUB_CAT NOT IN (SELECT NVL(ITEM_SUB_CAT_CODE,'-') FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL ) "+
			" ORDER BY ITEM_SUB_CAT ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		*/	
			
			
			
			
			//---------------------ID     :1.26 Fields Creation Process---------------------------------//
		//----------------------Purpose :Fields   Code Code Help -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva------------------------------------------------------//
		//----------------------Date    :20-07-2006----------------------------------------------------//
			m_help_TXT_FILED_CODE_sql=
		  " SELECT L.NO ,L.FILED_CODE,L.DESCRIPTION,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FILED_CODE,P.DESCRIPTION,P.DEFAULT_VALUE  "+
			" FROM( "+ 
      " SELECT "+
      " FILED_CODE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_FILEDS "+
			" WHERE FILED_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_FILEDS_APPLICABLE "+
			" WHERE FILED_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_LEGAL_ENTITY "+
			" WHERE ENTITY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY ENTITY_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		//----------------------ID      :1.35 Phone Area Code Creation Process-----------------------------------//
		//----------------------Purpose :Phone Area Code help-------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :21-07-2006--------------------------------------------------------//


	m_help_TXT_PHONE_AREA_CODE_sql=
			
		  " SELECT L.NO ,L.PHONE_AREA_CODE,L.DISTRICT_CODE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PHONE_AREA_CODE,P.DISTRICT_CODE,P.DEFAULT_VALUE  "+
			" FROM( "+ 
      " SELECT "+
      " PHONE_AREA_CODE,"+
      " DISTRICT_CODE, "+
      " DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_PHONE_CODES "+
			" WHERE PHONE_AREA_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_PRODUCT "+
			" WHERE PRODUCT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_RMV_AGENTS "+
			" WHERE RMV_AGENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_SEIZER "+
			" WHERE SEIZER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_YARD "+
			" WHERE YARD_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_FUEL_TYPE "+
	  	" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_TEAMS "+
    	" WHERE TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
			
			/*------------------ ID       : 1.72 Marketing Team Process----------------------------------
			--------------------Purpose   : Team id Help ----------------------------------------------
		  ------------------- Added By  : Nuwan De Silva------------------------------------------------------
		  -------------------- Date     : 24-07-2006---------------------------------------------------------*/	
				
			
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
      " FROM LAKDL.AF_CO_MAS_TEAMS "+
    	" WHERE ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') AND TEAM_ID NOT IN(SELECT TEAM_ID FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS) ORDER BY TEAM_ID "+
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
      " FROM LAKDL.AF_CO_MAS_TEAM_MEMBERS A,LAKDL.AF_CO_MAS_TEAMS B "+
			" WHERE A.TEAM_ID=B.TEAM_ID AND A.TEAM_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND A.ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') ORDER BY TEAM_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


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
      " FROM LAKDL.CO_CO_MAS_SUB_DIVISION "+
     	" WHERE SUB_DIVISION_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_REPAYMENT_INTERVAL "+
			" WHERE DURATION LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY DURATION ASC"+
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
      " FROM LAKDL.AF_CO_MAS_REVENUE_LICENSE "+
			" WHERE CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
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
      " FROM LAKDL.AF_CR_MAS_SCORE_RATING "+
			" WHERE RATING_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY RATING_CODE ASC"+
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
      " FROM LAKDL.AF_CO_MAS_AUTHORIZATION_LIMITS "+
			" WHERE USER_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FAX_NO, "+
      " OFFICE_FAX_NO, "+
			" FEE_PER_CASE, "+
      " MONTHLY_FEE "+
      
      " FROM LAKDL.AF_CO_MAS_LAWYER "+
			" WHERE LAWYER_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_CLIENT "+
			" WHERE CLIENT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_VENDORS "+
	  	" WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS NOT IN ('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_FOLLOWUP_CATEGORY "+
			" WHERE CATEGORY_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" FROM LAKDL.AF_CO_MAS_DISCOUNT_RATE "+
			" WHERE RATE LIKE UPPER('"+m_vector.elementAt(0)+"%')"+
			" ORDER BY RATE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";


		//----------------------ID      :1.71 Variable Interest Base Process -----------------------------------//
		//----------------------Purpose :Base Code Validation -------------------------------------------------//
		//----------------------Name    :Delanjali----------------------------------------------------------//
		//----------------------Date    :27-07-2006--------------------------------------------------------//

 m_help_TXT_BASE_CODE_sql=
			
			" SELECT L.NO ,L.BASE_CODE,L.DESCRIPTION,L.RATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BASE_CODE,P.DESCRIPTION,P.RATE"+
			" FROM( "+ 
      " SELECT "+
			"	BASE_CODE,"+
    	"	DESCRIPTION ,"+
			" RATE "+
			" FROM LAKDL.AF_CO_MAS_INTEREST_BASE "+
			" WHERE BASE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY BASE_CODE ASC"+
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
			" FROM LAKDL.AF_CO_MAS_MAINTENANCE_RATE "+
			" WHERE MAKE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
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
      " FROM LAKDL.AF_CO_MAS_SUB_CHARGES "+
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
       " FROM LAKDL.AF_CO_MAS_MILEAGE "+
		   " WHERE MODEL LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
			" FROM LAKDL.CO_CO_MAS_HOLIDAY "+
			" WHERE TO_CHAR(HOLIDAY_DATE,'DD-MM-YYYY') LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY HOLIDAY_DATE ASC"+
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
      " FROM LAKDL.AF_CO_MAS_EARLY_TERMI_CHARGE "+
			" WHERE TERMINATION_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY TERMINATION_TYPE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

    
		//----------------------ID      : -----------------------------------//
		//----------------------Purpose :Assest Usage  -------------------------------------------------//
		//----------------------Name    :Nuwan De Silva----------------------------------------------------------//
		//----------------------Date    :31-07-2006--------------------------------------------------------//
		
    m_help_TXT_USAGE_TYPE_sql=
		
		  " SELECT L.NO ,L.USAGE_TYPE,NVL(L.DESCRIPTION,'N/A') AS DESCRIPTION ,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USAGE_TYPE,P.DESCRIPTION,P.DEFAULT_VALUE"+
			" FROM( "+ 
      " SELECT "+
      " USAGE_TYPE, "+
      " DESCRIPTION, "+
      " DEFAULT_VALUE "+
      " FROM LAKDL.AF_CO_MAS_ASSET_USAGE_TYPE "+
      " WHERE USAGE_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY USAGE_TYPE ASC"+
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
      " FROM LAKDL.AF_CO_MAS_REPAYMENT_METHOD "+
      " WHERE REPAYMENT_TYPE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			" ORDER BY REPAYMENT_TYPE ASC"+
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
      " FROM LAKDL.AF_CO_MAS_SUB_PRODUCT "+
      " WHERE SUB_PRODUCT_CODE LIKE ('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
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
      " FROM LAKDL.AF_CO_MAS_VENDOR_LOCATION "+
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
			" FROM LAKDL.AF_CO_MAS_VENDOR_LOC_CONTACT "+
		  " WHERE VENDOR_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND BRANCH_CODE LIKE UPPER('"+m_vector.elementAt(1)+"%')  AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"')"+
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
      " FROM LAKDL.AF_CO_MAS_PROCESS_STAGE "+
      " WHERE STAGE_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"')"+
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
      " FROM LAKDL.CO_CO_MAS_USER_SCREEN "+
	    " WHERE SCREEN_NAME LIKE UPPER('"+m_vector.elementAt(0)+"%') AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
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
    " FROM LAKDL.AF_CO_PRO_ASSET_DETAILS "+
		" WHERE ASSET_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
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
    " FROM LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS "+
    " WHERE INVOICE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
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
			
      " FROM LAKDL.AF_CO_PRO_APP_VALUATION "+
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
			" FROM LAKDL.AF_CO_PRO_APP_VALUATION X,LAKDL.AF_CO_MAS_MODEL A,LAKDL.AF_CO_MAS_MAKE B,LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY C"+
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
			" FROM LAKDL.AF_CO_PRO_APP_VALUATION X,LAKDL.AF_CO_MAS_MODEL A,LAKDL.AF_CO_MAS_MAKE B,LAKDL.AF_CO_MAS_ITEM_SUB_CATEGORY C,LAKDL.AF_CO_MAS_ITEM_CATEGORY D,LAKDL.AF_CO_MAS_SUB_MODLE E"+
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
      " FROM LAKDL.AF_CO_MAS_VALUERS "+
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
      " FROM LAKDL.AF_CO_MAS_MODEL "+
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
      " FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
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
      " FROM LAKDL.AF_CO_MAS_SUB_MODLE "+
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
    " FROM LAKDL.AF_CO_PRO_ASSET_DETAILS "+
		" WHERE ASSET_ID LIKE UPPER('"+m_vector.elementAt(0)+"%')"+ // AND DISPLAY_STATUS=('"+m_vector.elementAt(1)+"')"+
		"  )P)L  "+
	  " WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";

		m_help_TXT_PRICING_NO_sql=

 			" SELECT L.NO ,L.PRICING_NO,L.INQUIRY_NO,L.MAKE_CODE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.ITEM_CATEGORY,L.ITEM_SUB_CAT_CODE,L.TRANSACION_TYPE,L.TRN_SUB_TYPE,L.INTEREST_TYPE,L.VARIABLE_INT_BASE,L.INT_MARGIN,L.CONDITION_OF_ASSET,"+
    	" L.ASSET_USAGE_TYPE,L.VAT_PERCENTAGE,L.ENGINE_CAPACITY,"+
    	" L.FUEL_TYPE,L.TARE,L.MAINTENANCE_STATUS,L.BUY_BACK,PERIOD,L.PAYMENT_MODE,L.PAYMENT_INTERVAL,L.RATE,L.GROSS_AMOUNT,L.VAT_AMOUNT,"+
    	" L.NET_AMOUNT,L.NIBSM,L.AMI,L.LAST_RENTAL,L.RESIDUAL_VALUE,L.OUTFLOW_PATTERN,L.SUPPLIER_CREDIT,L.INFLOW_PATTERN,L.PRICING_STATUS,"+
    	" L.CURRENCY_CODE,L.sUPPLIER,L.TRAN_AMOUNT_CURRENCY"+
						
			" FROM  "+
			" (SELECT ROWNUM NO,P.PRICING_NO,P.INQUIRY_NO,P.MAKE_CODE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.ITEM_CATEGORY,P.ITEM_SUB_CAT_CODE,P.TRANSACION_TYPE,P.TRN_SUB_TYPE,P.INTEREST_TYPE,P.VARIABLE_INT_BASE,P.INT_MARGIN,P.CONDITION_OF_ASSET,"+
    	" P.ASSET_USAGE_TYPE,P.VAT_PERCENTAGE,P.ENGINE_CAPACITY,"+
    	" P.FUEL_TYPE,P.TARE,P.MAINTENANCE_STATUS,P.BUY_BACK,PERIOD,P.PAYMENT_MODE,P.PAYMENT_INTERVAL,P.RATE,P.GROSS_AMOUNT,P.VAT_AMOUNT,"+
    	" P.NET_AMOUNT,P.NIBSM,P.AMI,P.LAST_RENTAL,P.RESIDUAL_VALUE,P.OUTFLOW_PATTERN,P.SUPPLIER_CREDIT,P.INFLOW_PATTERN,P.PRICING_STATUS,"+
    	" P.CURRENCY_CODE,P.SUPPLIER,P.TRAN_AMOUNT_CURRENCY"+

			" FROM( "+ 
		  " SELECT "+
     	"	PRICING_NO, "+
    	"	INQUIRY_NO, "+
			"	MAKE_CODE, "+
    	"	MODEL_CODE, "+
    	"	SUB_MODEL_CODE, "+
			"	ITEM_CATEGORY, "+
    	"	ITEM_SUB_CAT_CODE, "+
    	"	TRANSACION_TYPE, "+
    	"	TRN_SUB_TYPE, "+
    	"	INTEREST_TYPE, "+
    	"	VARIABLE_INT_BASE, "+
    	"	INT_MARGIN, "+
    	"	CONDITION_OF_ASSET, "+
    	"	ASSET_USAGE_TYPE, "+
    	"	VAT_PERCENTAGE, "+
    	"	ENGINE_CAPACITY, "+
    	"	FUEL_TYPE, "+
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
      " FROM LAKDL.AF_MK_PRO_PRICING "+
	  	" WHERE PRICING_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			//AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";




			/*------------------ ID         :Credit verification approval----------------------------------
			-------------------- Purpose    : Application No Help ----------------------------------------------
		  ------------------- Added By    : delanjali------------------------------------------------------
		  -------------------- Date       : 12-11-2006---------------------------------------------------------*/
			m_help_TXT_APPLICATION_NO_x_sql=
				" SELECT L.NO ,L.APPLICATION_NO,L.CLIENT_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.CLIENT_CODE "+
			" FROM( "+ 
		  " SELECT "+
      " APPLICATION_NO,"+
      " CLIENT_CODE "+
      " FROM LAKDL.AF_CO_PRO_APP_CLIENT_DOCS "+
	  	" WHERE APPLICATION_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  AND STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
			
		/*	m_help_TXT_ITEM_SUB_CAT_VAT_PRICE_sql=
			
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
			" WHERE A.ITEM_SUB_CAT_CODE=B.ITEM_SUB_CAT AND (A.ITEM_SUB_CAT_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR B.DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%') )  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			*/
			
			//added by nuwan de silva 30-07-07----------------------------------------
			m_help_vat_on_rental_delete_help=
			
			" SELECT L.NO ,L.TRN_CODE,L.DESCRIPTION,L.APP_DATE,L.VAT_RATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TRN_CODE,P.DESCRIPTION,P.APP_DATE,P.VAT_RATE "+
			" FROM( "+ 
		  " SELECT "+
      " A.TRN_CODE TRN_CODE ,"+
			" B.DESCRIPTION DESCRIPTION, "+
      " TO_CHAR(A.APP_DATE,'DD-MM-YYYY') APP_DATE , "+
			" A.VAT_RATE VAT_RATE "+
      " FROM "+m_schema_name+".AF_CO_PRO_VAT_ON_RENTAL A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
			" WHERE A.TRN_CODE=B.TRAN_CODE AND (A.TRN_CODE LIKE UPPER('"+m_vector.elementAt(0)+"%') OR B.DESCRIPTION LIKE UPPER('"+m_vector.elementAt(0)+"%') )  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		//-------------------------------------------------------------------------------
 


		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


