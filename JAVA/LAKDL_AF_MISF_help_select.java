// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LAKDL_AF_MK_help_select.java

import java.util.Vector;

public class LAKDL_AF_MISF_help_select
{
	
	public LAKDL_AF_MISF_help_select()
	{
		Ret_Object = new Object();
		m_sn_methods = new LAKDL_AF_CO_conn_methods();
		m_schema_name = m_sn_methods.schema_name.trim();
		
		///	M_APPLICATION_PROCESS_APPLICATION_HELP="";
		///	M_APPLICATION_PROCESS_APPLICATION_HELP_Header="Application Processing - Application Number Help";
		
		m_help_TXT_DIVISION_CODE_sql="";
		m_help_TXT_DIVISION_CODE_sql_Header="System Administration - Division";
		m_help_TXT_TRAN_CODE_sql= "";
		m_help_TXT_TRAN_CODE_sql_Header= "System Administration - Transaction";
		ClientSql = " ";
		ClientSql_Header = "Marketing - Client Help ";
		ClientSql1 = " ";
		
		bulkprintClient_Sql_Header="Bulk Print - Client Help ";
		bulkprintClient_Sql="";
		
		ClientSql1_Header = "Marketing - Client Help ";
		//===========Added by Dineth on 2008-08-25
		ClientSql2 = " ";
		ClientSql2_Header = "Marketing - Client Help ";
		InquirySql = " ";
		InquirySql_Header = "Marketing - Inquiry Help ";
		BrokerSql = " ";
		BrokerSql_Header = "Marketing - Broker Help ";
		MKOfficerSql = " ";
		MKOfficerSql_Header = "Marketing - Marketing Officer Help ";
		MKSuperSql = " ";
		MKSuperSql_Header = "Marketing - Supervisor Help ";
		MKTeamSql = " ";
		MKTeamSql_Header = "Marketing - Team Help ";
		TrnSubSql = " ";
		TrnSubSql_Header = "Help ";
		ModelSql = " ";
		ModelSql_Header = "Help ";
		VendorSql = " ";
		VendorSql_Header = "Help ";
		PriceSql = " ";
		PriceSql_Header = "Help ";
		SubModelSql = " ";
		SubModelSql_Header = "Help ";
		IntBaseSql = " ";
		IntBaseSql_Header = "Help ";
		m_help_TXT_DISTRICT_CODE_sql = "Marketing - District Help ";
		m_help_TXT_DISTRICT_CODE_sql_Header = "Marketing - Help ";
		m_help_TXT_DISTRICT_CODE_sql1 = "Marketing - District Help ";
		m_help_TXT_DISTRICT_CODE_sql1_Header = "Marketing - District Help ";
		
		m_help_TXT_APPLICATION_NO = " ";
		m_help_TXT_APPLICATION_NO_Header = "Marketing - Application Help";
		
		m_help_TXT_CLIENT_CODE = " ";
		m_help_TXT_CLIENT_CODE_Header = "Marketing - Client Help";
		m_help_TXT_INQUARY_NO = "";
		m_help_TXT_INQUARY_NO_Header = "Marketing - Inquiry Help ";
		m_help_TXT_INQUARY_NO_ALL = "";
		m_help_TXT_INQUARY_NO_ALL_Header = "Marketing - Inquiry Help ";
		m_help_TXT_MAKE = " ";
		m_help_TXT_MAKE_Header = "Marketing - Make Help";
		m_help_TXT_INVOICE_PURCHASE = "";
		m_help_TXT_INVOICE_PURCHASE_Header = "Invoice Help";
		m_help_TXT_PURCHASE_ORDER_NO = "";
		m_help_TXT_PURCHASE_ORDER_NO_Header = "Purchase Order Help";
		m_help_TXT_MODEL_CODE_sql = " ";
		m_help_TXT_MODEL_CODE_sql_Header = "Marketing - Model Help";
		m_help_TXT_MODEL_CODE_inv_sql = "";
		m_help_TXT_MODEL_CODE_inv_sql_Header = "Marketing - Model Help";
		PriceSql_invoice = "";
		PriceSql_invoice_Header = "Marketing - Pricing Help";
		m_help_TXT_SUB_MODEL_sql = "";
		m_help_TXT_SUB_MODEL_sql_Header = "Marketing - Sub Model Help";
		m_help_TXT_SUB_M_CODE_sql = "";
		m_help_TXT_SUB_M_CODE_sql_Header = "Marketing - Sub Model Help";
		m_help_TXT_CITY_CODE_sql = "";
		m_help_TXT_CITY_CODE_sql_Header = "Marketing - City Help";
		m_help_TXT_APPLICATION_NO_2 = "";
		m_help_TXT_APPLICATION_NO_2_Header = "Marketing - Application Help";
		m_help_TXT_SUPPLIER_sql = "";
		m_help_TXT_SUPPLIER_sql_Header = "Marketing - Supplier Help";
		m_help_SUPPLIER_PURCHASE_ORDER = "";
		m_help_SUPPLIER_PURCHASE_ORDER_Header = "Marketing - Supplier Help";
		m_help_TXT_INVOICE_NO_sql = "";
		m_help_TXT_INVOICE_NO_sql_Header = "Marketing - Invoice Help";
		m_help_TXT_ASSET_ID_sql = "";
		m_help_TXT_ASSET_ID_sql_Header = "Marketing - Asset Details Help";
		m_help_TXT_PRICING_NO_sql = "";
		m_help_TXT_PRICING_NO_sql_Header = "Marketing - Pricing Help";
		m_help_TXT_QUOTATION_NO_sql = "";
		m_help_TXT_QUOTATION_NO_sql_Header = "Marketing - Quotation Help";
		m_help_TXT_QUOTATION_NO_sql1 = "";
		m_help_TXT_QUOTATION_NO_sql1_Header = "Marketing - Quotation Help";
		m_help_TXT_QUOTATION_NO_sql2 = "";
		m_help_TXT_QUOTATION_NO_sql2_Header = "Marketing - Quotation Help";
		m_help_TXT_CONDITION_OF_ASSET_sql = "";
		m_help_TXT_CONDITION_OF_ASSET_sql_Header = "Marketing - Condition of Assets Help";
		m_help_TXT_MAKE_CODE_sql = "";
		m_help_TXT_MAKE_CODE_sql_Header = "Marketing - Make Help";
		m_help_TXT_MODEL_CODE1_sql = "";
		m_help_TXT_MODEL_CODE1_sql_Header = "Marketing - Model Help";
		m_help_TXT_MODEL_CODE2_sql = "";
		m_help_TXT_MODEL_CODE2_sql_Header = "Marketing - Model Help";
		m_help_TXT_INQUIRY_NO_sql = "";
		m_help_TXT_INQUIRY_NO_sql_Header = "Marketing - Inquiry Help";
		m_help_TXT_SCREEN_NAME_sql = "";
		m_help_TXT_SCREEN_NAME_sql_Header = "Marketing - Screen Name Help";
		m_help_TXT_PURCHASE_ORDER_NO_sql = "";
		m_help_TXT_PURCHASE_ORDER_NO_sql_Header = "Marketing - Payment Help";
		
		m_help_TXT_PURCHASE_ORDER_NO_sql_1 = "";
		m_help_TXT_PURCHASE_ORDER_NO_sql_1_Header = "Marketing - Payment Help";
		
		m_help_TXT_APP_NO_sql = "";
		m_help_TXT_APP_NO_sql_Header = "Marketing - Application Help";
		m_help_TXT_VENDER_CODE_sql = "";
		m_help_TXT_VENDER_CODE_sql_Header = "Marketing - Vendor Help";
		m_help_TXT_ASSET_ID_sql2 = "";
		m_help_TXT_ASSET_ID_sql2_Header = "Marketing - Asset Details Help";
		m_help_TXT_APP_NO_sql_1 = "";
		m_help_TXT_APP_NO_sql_1_Header = "Marketing - Application Help";
		m_help_TXT_VENDOR_CODE_sql = "";
		m_help_TXT_VENDOR_CODE_sql_Header = "Marketing - Vendor help";
		m_help_TXT_LOCATION_CODE_sql = "";
		m_help_TXT_LOCATION_CODE_sql_Header = "Marketing - Location Help"; 
			
		new_m_help_TXT_LOCATION_CODE_sql = "";
		new_m_help_TXT_LOCATION_CODE_sql_Header = "Marketing - Location Help";//added milinda
		
		m_help_TXT_MAS_VENDOR_LOCATION_sql = "";
		m_help_TXT_MAS_VENDOR_LOCATION_sql_Header = "Marketing -  Vendor Branch Location Help";
		m_help_TXT_VALUATION_NO1_sql = "";
		m_help_TXT_VALUATION_NO1_sql_Header = "Marketing - Valuation help";
		
		m_help_TXT_VALUATION_NO2_sql = "";
		m_help_TXT_VALUATION_NO2_sql_Header = "Marketing - Valuation help";
		
		m_help_TXT_VALUATION_NO_sql="";
		m_help_TXT_VALUATION_NO_sql_Header="System Administration - Valuer Details";
		
		m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql="";
		m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql_Header="System Administration - Lead Source Details";				
		
		m_help_TXT_ISSUER_CODE_sql="";
		m_help_TXT_ISSUER_CODE_sql_Header="System Administration - Bank Branch Details";			
		
		m_help_TXT_FinanceSql_new           = " ";
		m_help_TXT_FinanceSql_new_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_INVOICE_NO_app_sql=" ";
		m_help_TXT_INVOICE_NO_app_sql_Header= "Credit - Vehicle No Help "; 
		
		m_help_TXT_VALUER_CODE_sql= "";
		m_help_TXT_VALUER_CODE_sql_Header= "	Marketing - Valuer Code Help";
		
		m_help_TXT_VALUATION_NO_sql_new="";
		m_help_TXT_VALUATION_NO_sql_new_Header="System Administration - Valuer Details";
		
		m_help_TXT_FinanceSql_new1           = " ";
		m_help_TXT_FinanceSql_new1_Header    = "Credit - Finance Help "; 
		//Added by Dineth on 2009-01-15
		m_help_TXT_FinanceSql_new4           = " ";
		m_help_TXT_FinanceSql_new4_Header    = "Credit - Finance Help "; 
		//end by Dineth on 2009-01-15
		
		m_help_TXT_FinanceSql_new2           = " ";
		m_help_TXT_FinanceSql_new2_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_FinanceSql_new3           = " ";
		m_help_TXT_FinanceSql_new3_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_SO_NO_sql= " ";
		m_help_TXT_SO_NO_sql_Header= "Credit - Standing Order No Help "; 
		
		m_help_TXT_INVOICE_NO_app_sql1=" ";
		m_help_TXT_INVOICE_NO_app_sql1_Header= "Credit - Vehicle No Help "; 
		
		m_help_TXT_APPLICATION_NO_1="";
		m_help_TXT_APPLICATION_NO_1_Header= "Credit -Application No Help "; 
		
		
		m_help_TXT_DISPUTES_VALUATION_NO_sql_new="";
		m_help_TXT_DISPUTES_VALUATION_NO_sql_new_Header="Credit - Valuation No Help";
		
		m_help_TXT_DISPUTES_VALUATION_NO_sql_edit="";
		m_help_TXT_DISPUTES_VALUATION_NO_sql_edit_Header="Credit - Valuation No Help";
		
		m_help_TXT_INQUARY_NO_sql_report="";
		m_help_TXT_INQUARY_NO_sql_report_Header="Inquary No Help  ";
		
		m_help_TXT_REPOSSESSION_NO_sql_report="";
		m_help_TXT_REPOSSESSION_NO_sql_report_Header="Finance No Help";
		
		m_help_TXT_PURCHASE_ORDER_NO_sql_report="";
		m_help_TXT_PURCHASE_ORDER_NO_sql_report_Header="Furchase Order Help";
		PriceSql_3="";
		PriceSql_3_Header= "Credit - Price No Help "; 
		
		m_help_TXT_CREDIT_NO_sql_report="";
		m_help_TXT_CREDIT_NO_sql_report_Header="Credit No Help";
		
		ReceiptSql1               = " ";
		ReceiptSql1_Header        = " Collection Help "; 
		
		m_help_Collection_process_Deposit_code        ="";
		m_help_Collection_process_Deposit_code_Header ="Deposit Number Help";
		
		m_help_TXT_FinanceSql_sql           = " ";
		m_help_TXT_FinanceSql_sql_Header    = "Credit - Finance Help ";
		
		m_help_TXT_upload_doc = ""; 										// ADDED BY SAJITH MENDIS ON 11-04-2014
		m_help_TXT_upload_doc_Header    = "Credit - Upload Document Help "; // ADDED BY SAJITH MENDIS ON 11-04-2014
		
		m_help_TXT_FinanceSql2_sql = ""; // added by udara 20-11-2013
		m_help_TXT_FinanceSql2_sql_Header    = "Credit - Finance Help "; // added by udara 20-11-2013
		
		m_help_TXT_FinanceSql2_sql_new = ""; 								// ADDED BY SAJITH MENDIS ON 22-04-2014
		m_help_TXT_FinanceSql2_sql_new_Header    = "Credit - Finance Help ";// ADDED BY SAJITH MENDIS ON 22-04-2014
		
		m_help_TXT_FinanceSql2_VIR_sql_new = ""; // added by udara 04-09-2014
		m_help_TXT_FinanceSql2_VIR_sql_new_Header = ""; // added by udara 04-09-2014
		
		
		m_help_TXT_BulkPrintFinanceSql_sql           = " ";
		m_help_TXT_BulkPrintFinanceSql_sql_Header    = "Bulk Print Finance Number Help ";
		
		//ADD BY A/S ON FOR LEGAL LETTER
		m_help_TXT_BulkPrintFinanceSql_1_sql           = " ";
		m_help_TXT_BulkPrintFinanceSql_1_sql_Header    = "Bulk Print Finance Number Help ";
		
		// Added by Dineth on 2008-08-29
		m_help_TXT_TerminationSql_sql           = " ";
		m_help_TXT_TerminationSql_sql_Header    = "Credit - Termination Help "; 
		
		m_help_TXT_TerminationSql_sql2           = " ";
		m_help_TXT_TerminationSql_sql2_Header    = "Credit - Termination Help "; 
		// End by Dineth on 2008-08-29
		
		
		m_help_TXT_FinanceSql_sql2           = " ";
		m_help_TXT_FinanceSql_sql2_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_confirmation_rpt_appr_sql           = " ";
		m_help_TXT_confirmation_rpt_appr_sql_Header    = "Credit - Finance Help "; 
		
		// added by udara 24-07-2018
		m_help_insurance_app_sql           = " ";
		m_help_insurance_app_sql_Header    = "Credit - Finance Help "; 
		
		// added by udara 31-08-2015
		m_help_TXT_confirmation_rpt_view_sql           = " ";
		m_help_TXT_confirmation_rpt_view_sql_Header    = "Credit - Finance Help ";
		// end by udara 31-08-2015
		
	    // ADD BY AS ON 01-08-2017
		m_help_Finance_No_sql           = " ";
		m_help_Finance_No_sql_Header    = "Credit - Finance Help ";
		// END BY 01-08-2017
		
		m_help_TXT_confirmation_rpt_edit_sql           = " ";
		m_help_TXT_confirmation_rpt_edit_sql_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_confirmation_rpt_gen_sql           = " ";
		m_help_TXT_confirmation_rpt_gen_sql_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_confirmation_rpt_regenerate_sql    = " ";
		m_help_TXT_confirmation_rpt_regenerate_sql_Header    = " Credit - Finance Help ";
		
		// added by udara 31-10-2014
		m_help_TXT_FinanceSql_sanction_sql           = " ";
		m_help_TXT_FinanceSql_sanction_sql_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_FinanceSql_sanction_sql2           = " ";
		m_help_TXT_FinanceSql_sanction_sql2_Header    = "Credit - Finance Help "; 
		// end by udara 31-10-2014
		
		m_help_TXT_BulkPrintFinanceSql_sql2           = " ";
		m_help_TXT_BulkPrintFinanceSql_sql2_Header    = "Credit - Finance Help "; 
		
		// ADD BY A/S FOR LEGAL LETTER
		m_help_TXT_BulkPrintFinanceSql_1_sql2           = " ";
		m_help_TXT_BulkPrintFinanceSql_1_sql2_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_BULKPRINT_TEAM_ID_sql= " ";
		m_help_TXT_BULKPRINT_TEAM_ID_sql_Header ="Collection - Team";
		
		m_help_txt_bulk_print_sub_team_sql=" ";
		m_help_txt_bulk_print_sub_team_sql_Header="Collection - SubTeam";
		
		m_help_collection_officer=" ";
		m_help_collection_officer_Header="Collection - Collection  Officer";
		
		FinanceSql           = " ";
		FinanceSql_Header    = "Credit - Finance Help "; 
		
		FinanceSql_finance           = " ";
		FinanceSql_finance_Header    = "Credit - Finance Help "; 
		
		//ADDED MILINDA
		FinanceSql_finance_CHANGE           = " ";
		FinanceSql_finance_CHANGE_Header    = "Credit - Finance Help "; 
		
		FinanceSql_period           = " ";
		FinanceSql_period_Header    = "Credit - Finance Help "; 
		
		ClientSql_Balance_Confim ="";
		ClientSql_Balance_Confim_Header = "Balance Confirmation - Client Help ";
		
		m_help_TXT_FinanceSql_BC ="";
		m_help_TXT_FinanceSql_BC_Header = "Balance Confirmation - Finance No Help "; //Added By Sandun on 22-12-2008
		
		m_help_TXT_FinanceSql_BalReceive="";
		m_help_TXT_FinanceSql_BalReceive_Header ="Finance Help";
		
	}
	
	public Object getSql(Object obj, Object obj1, Object obj2, Object obj3)
	{
		String s = (String)obj;
		String s1 = (String)obj1;
		String s2 = (String)obj2;
		String s3 = (String)obj3;
		
		//    int i = Integer.parseInt(s2);
		//  s2 = Integer.toString(++i);
		
		int m_val =(Integer.parseInt(s2));
		m_val++;
		s2 = Integer.toString(m_val);
		
		Vector vector = new Vector();
		String s4 = "";
		int j = 0;
		int k = 0;
		int l = 0;
		int j1 = s3.lastIndexOf("@");
		if(j1 != 0)
			while(k < j1) 
				try
				{
					k = s3.trim().indexOf("@", j);
					String s5 = s3.substring(j, k);
					if(s5.length() > 0)
						vector.addElement(s5);
					else
						vector.addElement("");
					j = k + 1;
				}
				catch(Exception exception)
				{
					k = j1;
				}
		else{
			if(j1 == 0)
				vector.addElement("");
		}
		l = vector.size();
		
		for(int i1 = l; i1 < 8; i1++){
			vector.addElement("");
		}
		
		ClientSql = "SELECT P.NO, P.CLIENT_CODE Client, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE FROM (SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE FROM (SELECT CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE \t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "\t WHERE (UPPER(FULL_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ADDRESS1)   LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(CITY_CODE)  LIKE UPPER('" + vector.elementAt(0) + "%') OR " + "\t        UPPER(MOBILE_NO)  LIKE UPPER('" + vector.elementAt(0) + "%') OR " + "         UPPER(TEL_NO)     LIKE UPPER('" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)      LIKE UPPER('" + vector.elementAt(0) + "%') OR " + "         UPPER(NIC_NO)     LIKE UPPER('" + vector.elementAt(0) + "%') OR " + "\t        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('" + vector.elementAt(0) + "%')) " + " ORDER BY FULL_NAME )) P " + "WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		InquirySql = "SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO,ADDRESS ADDRESS1 , ADDRESS2, CITY_CODE, LEGAL_ENTITY,LEAD_SOURCE_NAME,ID_NO, MK_OFFICER,INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,INTRODUCER,EMAIL, TEAM,FAX_NO,MK_SUPERVISOR, CONTACT_PERSON,SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE,STATUS,INQUIRY_STATUS FROM (SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO, FAX_NO,\t       ADDRESS, CITY_CODE, LEGAL_ENTITY, STATUS,\t       INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,\t       LEAD_SOURCE_NAME, INTRODUCER, ID_NO, INQUIRY_STATUS,        ENT_USER,ADDRESS2, EMAIL, TEAM,        MK_OFFICER, MK_SUPERVISOR, CONTACT_PERSON,\t       SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE FROM (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t      UPPER(ADDRESS)      LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t      UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(MOBILE_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P " + "WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		BrokerSql = 
			"       SELECT P.NO,BROKER_CODE,FIRST_NAME,LAST_NAME, CONTACT_NO, "+
			" 			       MOBILE_NO, ID_NO,ADDRESS1, "+
			" 			       ADDRESS2, LOCATION_CODE, CITY_CODE,COMMISSION_RATE, "+
			" 			       COMMISSION_AMOUNT, ACTIVE_STATUS "+				
			" FROM (SELECT ROWNUM NO, BROKER_CODE,FIRST_NAME,LAST_NAME, CONTACT_NO, "+
			" 			       MOBILE_NO, ID_NO,ADDRESS1, "+
			" 			       ADDRESS2, LOCATION_CODE, CITY_CODE,COMMISSION_RATE, "+
			" 			       COMMISSION_AMOUNT, ACTIVE_STATUS "+
			" FROM (SELECT BROKER_CODE,FIRST_NAME,LAST_NAME, CONTACT_NO, "+
			" 			       MOBILE_NO, ID_NO,ADDRESS1, "+
			" 			       ADDRESS2, LOCATION_CODE, CITY_CODE,COMMISSION_RATE, "+
			" 			       COMMISSION_AMOUNT, ACTIVE_STATUS "+
			" 			FROM "+m_schema_name + ".AF_CO_MAS_BROKER "+
			" 			WHERE  ACTIVE_STATUS='Y' AND ( "+
			" 			       UPPER(BROKER_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       UPPER(FIRST_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       UPPER(LAST_NAME)   LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       CONTACT_NO  LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       MOBILE_NO   LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       ID_NO       LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       UPPER(ADDRESS1)    LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       CITY_CODE   LIKE UPPER('%" + vector.elementAt(0) + "%') ) "+
			" ORDER BY FIRST_NAME,LAST_NAME )) P " + 
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		m_help_TXT_DISTRICT_CODE_sql = " SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE   FROM   (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE  FROM(  SELECT  DISTRICT_CODE, DISTRICT_DESC,  PROVINCE_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_DISTRICT " + " WHERE DISTRICT_CODE LIKE UPPER('" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_ASSET_ID_sql = " SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO FROM   (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO FROM(  SELECT  ASSET_ID,  REG_NO,  REG_DATE,  MODEL_CODE,  SUB_MODEL_CODE,  PRICING_NO,  STATUS,  SUPPLIER_CODE,  QTY,  COST,  PURPOSE,  ADDRESS,  CITY_CODE,  PERIOD,  APPLICATION_NO  FROM " + m_schema_name + ".AF_CO_PRO_ASSET_DETAILS " + " WHERE ASSET_ID LIKE UPPER('" + vector.elementAt(0) + "%')" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MODEL_CODE_inv_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE MODEL_CODE LIKE UPPER('" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SUB_M_CODE_sql = " SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE FROM   (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE FROM(  SELECT  SUB_CODE, MODEL_CODE, DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, DEFAULT_VALUE FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE " + " WHERE SUB_CODE LIKE UPPER('" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY SUB_CODE ASC" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_INVOICE_NO_sql = " SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS,L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID,L.FUEL_CONVERTION_STATUS,TO_CHAR(L.DUE_DATE,'DD-MM-YYYY') AS DUE_DATE FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE,P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS,P.DUE_DATE FROM(  SELECT  A.INVOICE_NO,  A.APPLICATION_NO,  A.ASSET_ID,  NVL(A.ENGINE_NO,'-') ENGINE_NO ,  NVL(A.CHASSIS_NO,'-') CHASSIS_NO,  A.REG_NO,  A.REG_DATE,  A.PRICING_NO,  A.SUB_MODEL_CODE,  NVL(A.COLOUR,'-') COLOUR ,  A.SEATING_CAPACITY,  A.NET_PRICE,  A.VAT,  A.TOTAL_AMOUNT,  A.TO_BE_DELIVERD_TO,  A.VALUE,  A.CURR_CODE,  A.MODEL_CODE,  " + m_schema_name + ".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, " + " NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO, " + " NVL(A.CITY_CODE,' ') CITY_CODE, " + " NVL(A.ADDRESS,'-') ADDRESS, " + " NVL(A.VENDOR_CODE,'-') VENDOR_CODE, " + " NVL( (SELECT B.NAME FROM " + m_schema_name + ".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , " + " NVL(A.BRANCH_ID,'-')  BRANCH_ID," + " A.FUEL_CONVERTION_STATUS  FUEL_CONVERTION_STATUS, " + " A.DUE_DATE " + " FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS A" + " WHERE A.INVOICE_NO LIKE UPPER('" + vector.elementAt(0) + "%') AND A.APPLICATION_NO =UPPER('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//dont delete()
		//m_help_TXT_APPLICATION_NO = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, " + " CO_APPLICANT," + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, " + " NVL(INQUARY_NO,'-') INQUARY_NO, " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, " + " NVL(" + m_schema_name + ".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER, " + " TRANSACTION_TYPE " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  " + " AND APPLICATION_STATUS =('COMPLETED') " + " ORDER BY ENT_DATE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//	m_help_TXT_APPLICATION_NO = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, " + " CO_APPLICANT," + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, " + " NVL(INQUARY_NO,'-') INQUARY_NO, " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, " + " NVL(" + m_schema_name + ".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER, " + " TRANSACTION_TYPE " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  " + " AND APPLICATION_STATUS IN('" + vector.elementAt(1) + "','" + vector.elementAt(2) + "') " + " ORDER BY ENT_DATE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		m_help_TXT_INQUARY_NO = "SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,\t       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,\t       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,\t       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,\t       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE  FROM  (SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,\t       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,\t       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,\t       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,\t       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE  FROM  (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE ( UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND " + "         ENT_USER='" + vector.elementAt(2) + "'" + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		m_help_TXT_INQUARY_NO_ALL = "SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,\t       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,\t       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,\t       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,\t       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE  FROM  (SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,\t       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,\t       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,\t       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,\t       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE  FROM  (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE   UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%')   " + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		m_help_TXT_CLIENT_CODE = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category,NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, P.ADDRESS1, P.ADDRESS2, P.CITY FROM (SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY,ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY,ACTIVE_STATUS, TEMP_ACTIVE_STATUS " + "\t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO' AND " + "       ( UPPER(FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t       BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%')) AND " + "        ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " + " ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		m_help_TXT_MAKE = " SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC  FROM   (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC  FROM(  SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC  FROM " + m_schema_name + ".AF_CO_MAS_MAKE A," + m_schema_name + ".AF_CO_MAS_MODEL B " + " WHERE A.MAKE_CODE=B.MAKE_CODE AND A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND B.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "')  " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MODEL_CODE_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION  FROM(  SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION  FROM " + m_schema_name + ".AF_CO_MAS_MODEL A," + m_schema_name + ".AF_CO_MAS_SUB_MODLE B " + " WHERE A.MODEL_CODE=B.MODEL_CODE AND A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(2) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(3) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SUB_MODEL_sql = " SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE FROM   (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE FROM(  SELECT  DISTINCT SUB_CODE, MODEL_CODE, DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, DEFAULT_VALUE FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE " + " WHERE SUB_CODE LIKE UPPER('" + vector.elementAt(0) + "%') AND MODEL_CODE LIKE UPPER('" + vector.elementAt(1) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + " ORDER BY SUB_CODE ASC" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_INVOICE_PURCHASE = " SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT,L.GROSS_AMOUNT  FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT,P.GROSS_AMOUNT  FROM(  SELECT  A.INVOICE_NO,  A.ASSET_ID,  A.ENGINE_NO,  A.CHASSIS_NO,  A.NET_PRICE,  A.VAT,  (A.NET_PRICE +A.VAT) GROSS_AMOUNT  FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS A " + " WHERE  A.VENDOR_CODE LIKE UPPER('" + vector.elementAt(0) + "%') AND A.APPLICATION_NO LIKE UPPER('" + vector.elementAt(1) + "%') AND A.INVOICE_NO LIKE UPPER('" + vector.elementAt(2) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(3) + "') AND A.CURR_CODE IN (SELECT CURR_CODE FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO LIKE UPPER('" + vector.elementAt(2) + "%'))" + " AND A.INVOICE_NO NOT IN(SELECT " + " DISTINCT    PRO_INVOICE_NO " + " FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER_DET " + " WHERE PURCHASE_ORDER_NO IN " + " (SELECT " + " PURCHASE_ORDER_NO " + " FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER " + " WHERE   VENDER_CODE LIKE UPPER('" + vector.elementAt(0) + "%'))) " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_PURCHASE_ORDER_NO = " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE  FROM   (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE  FROM(  SELECT  PURCHASE_ORDER_NO,  APPLICATION_NO,  VENDER_CODE  FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER " + " WHERE  PURCHASE_ORDER_NO LIKE UPPER('" + vector.elementAt(0) + "%') AND ACTIVE_STATUS IN('" + vector.elementAt(2) + "','" + vector.elementAt(3) + "') AND VENDER_CODE LIKE UPPER('" + vector.elementAt(1) + "%') ORDER BY PURCHASE_ORDER_NO DESC    " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		PriceSql_invoice = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + " WHERE PRICING_NO LIKE '" + vector.elementAt(0) + "%' /*AND PRICING_STATUS=('" + vector.elementAt(2) + "')*/ " + " AND APP_NO ='" + vector.elementAt(1) + "' " + " ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		m_help_TXT_CITY_CODE_sql = " SELECT L.NO ,L.CITY_CODE,L.CITY_DESC,L.DISTRICT_CODE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC,P.DISTRICT_CODE,P.DEFAULT_VALUE  FROM(  SELECT  CITY_CODE,  CITY_DESC,  DISTRICT_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_CITY " + " WHERE ( CITY_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(CITY_DESC) LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_APPLICATION_NO_2 = " SELECT L.NO ,L.APPLICATION_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO  FROM(  SELECT  DISTINCT APPLICATION_NO  FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS " + " WHERE ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_SUPPLIER_PURCHASE_ORDER = " SELECT L.NO ,L.VENDOR_CODE,L.NAME  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME  FROM(  SELECT  VENDOR_CODE,NAME  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS A " + " WHERE A.VENDOR_CODE IN  " + " (SELECT " + " B.VENDOR_CODE " + " FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B  " + " WHERE B.APPLICATION_NO LIKE UPPER('" + vector.elementAt(1) + "%') )  " + " AND A.VENDOR_CODE LIKE ('" + vector.elementAt(0) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SUPPLIER_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME  FROM(  SELECT  VENDOR_CODE,  NAME  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SCREEN_NAME_sql = "SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME  FROM (SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME  FROM (SELECT SCREEN_NAME,DISPLAY_NAME  FROM " + m_schema_name + ".CO_CO_MAS_USER_SCREEN " + "  WHERE SCREEN_NAME LIKE UPPER('%" + vector.elementAt(0) + "%') " + "  ORDER BY SCREEN_NAME )P) L\t" + " WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		MKOfficerSql = " SELECT NO,USER_ID,NAME  FROM  ( SELECT ROWNUM NO,USER_ID,NAME  FROM  ( SELECT USER_ID,NAME  FROM   " + m_schema_name + ".CO_CO_MAS_USER " + " WHERE  DIVISION_CODE='" + vector.elementAt(1) + "' AND " + "\t      (USER_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(NAME) LIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY NAME)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		MKSuperSql = " SELECT NO,USER_ID,NAME  FROM  ( SELECT ROWNUM NO,USER_ID,NAME  FROM  ( SELECT USER_ID,NAME  FROM   " + m_schema_name + ".CO_CO_MAS_USER " + " WHERE  DIVISION_CODE='" + vector.elementAt(1) + "' AND " + "\t      (USER_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(NAME) LIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY NAME)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		MKTeamSql = " SELECT NO,TEAM_ID, TEAM_DESC,TEAM_HEAD  FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC,TEAM_HEAD  FROM  ( SELECT B.TEAM_ID,TEAM_DESC,TEAM_HEAD  FROM " + m_schema_name + ".AF_CO_MAS_TEAMS B " + " WHERE B.ACTIVE_STATUS='Y' AND " + " (B.TEAM_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR  " + "  TEAM_DESC\tLIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY TEAM_DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		TrnSubSql = " SELECT NO,TRN_SUB_TYPE,DESCRIPTION,RATE  FROM  ( SELECT ROWNUM NO, TRN_SUB_TYPE, DESCRIPTION,RATE  FROM  ( SELECT A.TRN_SUB_TYPE,  A.DESCRIPTION,A.RATE  FROM   " + m_schema_name + ".AF_CO_MAS_TRANSACTION_SUB_TYPE A " + " WHERE  ACTIVE_STATUS  LIKE '" + vector.elementAt(2) + "%' AND " + "     (A.TRN_CODE LIKE UPPER('" + vector.elementAt(1) + "%') AND " + " TRN_SUB_TYPE LIKE UPPER('" + vector.elementAt(0) + "%') ) " + " ORDER BY TRN_SUB_TYPE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		ModelSql = " SELECT NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE  FROM  ( SELECT ROWNUM NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE  FROM  ( SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE,                 A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE  FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A " + " WHERE  ACTIVE_STATUS ='" + vector.elementAt(2) + "' AND " + "        A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND " + "        A.MAKE_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY MODEL_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		SubModelSql = " SELECT NO,SUB_CODE SUB_MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MODEL_CODE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY  FROM  ( SELECT ROWNUM NO,SUB_CODE,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY  FROM  ( SELECT B.SUB_CODE ,B.MODEL_CODE, B.DESCRIPTION,A.TAX_RATE,                 A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE,b.ENGINE_CAPACITY  FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A," + m_schema_name + ".AF_CO_MAS_SUB_MODLE B " + " WHERE  B.ACTIVE_STATUS ='" + vector.elementAt(2) + "' AND " + "        A.MODEL_CODE=B.MODEL_CODE AND  " + "        (B.SUB_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR " + "        UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%')) AND  " + "        A.MAKE_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY MODEL_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		IntBaseSql = " SELECT NO,BASE_CODE, DESCRIPTION, RATE FROM(  SELECT ROWNUM NO,BASE_CODE, DESCRIPTION, RATE FROM(  SELECT A.BASE_CODE, A.DESCRIPTION, A.RATE  FROM   " + m_schema_name + ".AF_CO_MAS_INTEREST_BASE A " + " WHERE  ACTIVE_STATUS='Y' AND " + "       (BASE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        DESCRIPTION LIKE UPPER('%" + vector.elementAt(0) + "%')) )) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		VendorSql = " SELECT NO,VENDOR_CODE,NAME,CATEGORY,TYPE  FROM  ( SELECT ROWNUM NO,VENDOR_CODE,NAME,CATEGORY,TYPE  FROM  ( SELECT A.VENDOR_CODE, A.NAME, NVL(A.CATEGORY,'-') CATEGORY,NVL(A.TYPE,'-') TYPE  FROM  " + m_schema_name + ".AF_CO_MAS_VENDORS A " + " WHERE ACTIVE_STATUS='" + vector.elementAt(1) + "' AND " + "       (VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(NAME)  \t LIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY VENDOR_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		PriceSql = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + " WHERE PRICING_NO LIKE '" + vector.elementAt(0) + "%' AND " + "       (INQUIRY_NO LIKE '" + vector.elementAt(1) + "%' OR INQUIRY_NO IS NULL ) " + " ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		m_help_TXT_PRICING_NO_sql = " SELECT L.NO ,L.PRICING_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.MAKE_CODE,L.MODEL_CODE,LTRIM(TO_CHAR(L.GROSS_AMOUNT,'999,999,999.99')) AS GROSS_AMOUNT,L.SUB_MODEL_CODE,L.ITEM_CATEGORY,L.ITEM_SUB_CAT_CODE,L.TRANSACION_TYPE,L.TRN_SUB_TYPE,L.INTEREST_TYPE,L.VARIABLE_INT_BASE,L.INT_MARGIN,L.CONDITION_OF_ASSET, L.ASSET_USAGE_TYPE,L.VAT_PERCENTAGE,L.ENGINE_CAPACITY, L.FUEL_TYPE,L.TARE,L.MAINTENANCE_STATUS,L.BUY_BACK,PERIOD,L.PAYMENT_MODE,L.PAYMENT_INTERVAL,L.RATE,LTRIM(TO_CHAR(L.VAT_AMOUNT,'999,999,999.99')) AS VAT_AMOUNT, LTRIM(TO_CHAR(L.NET_AMOUNT,'999,999,999,999,999,999,999,999.99')) AS NET_AMOUNT,L.NIBSM,LTRIM(TO_CHAR(L.AMI,'999,999,999.99')),LTRIM(TO_CHAR(L.LAST_RENTAL,'999,999,999.99')) AS LAST_RENTAL,LTRIM(TO_CHAR(L.RESIDUAL_VALUE,'999,999,999.99')) AS RESIDUAL_VALUE,L.OUTFLOW_PATTERN,L.SUPPLIER_CREDIT,L.INFLOW_PATTERN,L.PRICING_STATUS, L.CURRENCY_CODE,L.SUPPLIER,LTRIM(TO_CHAR(L.TRAN_AMOUNT_CURRENCY,'999,999,999.99')) AS TRAN_AMOUNT_CURRENCY ,L.DESC5 AS CONDITION FROM   (SELECT ROWNUM NO,P.PRICING_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.MAKE_CODE,P.MODEL_CODE,P.GROSS_AMOUNT,P.SUB_MODEL_CODE,P.ITEM_CATEGORY,P.ITEM_SUB_CAT_CODE,P.TRANSACION_TYPE,P.TRN_SUB_TYPE,P.INTEREST_TYPE,P.VARIABLE_INT_BASE,P.INT_MARGIN,P.CONDITION_OF_ASSET, P.ASSET_USAGE_TYPE,P.VAT_PERCENTAGE,P.ENGINE_CAPACITY, P.FUEL_TYPE,P.TARE,P.MAINTENANCE_STATUS,P.BUY_BACK,PERIOD,P.PAYMENT_MODE,P.PAYMENT_INTERVAL,P.RATE,P.VAT_AMOUNT, P.NET_AMOUNT,P.NIBSM,P.AMI,P.LAST_RENTAL,P.RESIDUAL_VALUE,P.OUTFLOW_PATTERN,P.SUPPLIER_CREDIT,P.INFLOW_PATTERN,P.PRICING_STATUS, P.CURRENCY_CODE,P.SUPPLIER,P.TRAN_AMOUNT_CURRENCY,P.DESC5 FROM(  SELECT \tPRICING_NO, \tINQUIRY_NO,  CLIENT_NAME, \tA.MAKE_CODE, \tA.MODEL_CODE, \tGROSS_AMOUNT, \tSUB_MODEL_CODE, \tITEM_CATEGORY, \tITEM_SUB_CAT_CODE, \tTRANSACION_TYPE, \tTRN_SUB_TYPE, \tINTEREST_TYPE, \tVARIABLE_INT_BASE, \tINT_MARGIN, \tCONDITION_OF_ASSET, \tASSET_USAGE_TYPE, \tVAT_PERCENTAGE, \tENGINE_CAPACITY, \tA.FUEL_TYPE, \tTARE, \tMAINTENANCE_STATUS, \tBUY_BACK, \tPERIOD, \tPAYMENT_MODE, \tPAYMENT_INTERVAL, \tRATE, \tVAT_AMOUNT, \tNET_AMOUNT, \tNIBSM, \tAMI, \tLAST_RENTAL, \tRESIDUAL_VALUE, \tOUTFLOW_PATTERN, \tSUPPLIER_CREDIT, \tINFLOW_PATTERN, \tPRICING_STATUS,  CURRENCY_CODE, \tSUPPLIER, \tTRAN_AMOUNT_CURRENCY , \tF.DESCRIPTION AS DESC5  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A" + "\t," + m_schema_name + ".AF_CO_MAS_CONDITION_OF_ASSET F " + " ," + m_schema_name + ".AF_MK_PRO_INQUIRY G " + " WHERE PRICING_NO LIKE UPPER('" + vector.elementAt(1) + "%')  " + " AND A.CONDITION_OF_ASSET=F.CODE " + " AND A.INQUIRY_NO=('" + vector.elementAt(0) + "') " + " AND A.INQUIRY_NO=G.INQUIRY_CODE\t" + " ORDER BY PRICING_NO DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_QUOTATION_NO_sql = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.STATUS,P.APPR_USER,P.APPR_DATE FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO,\tSTATUS, APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION " + " WHERE QUOTATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_QUOTATION_NO_sql1 = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.STATUS,P.APPR_USER,P.APPR_DATE FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO, CLIENT_NAME, \tA.STATUS, APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION A ," + m_schema_name + ".AF_MK_PRO_INQUIRY B" + " WHERE (QUOTATION_NO LIKE UPPER('" + vector.elementAt(0) + "%') OR CLIENT_NAME LIKE('" + vector.elementAt(0) + "%'))  " + " AND INQUIRY_CODE=INQUIRY_NO " + " AND A.STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY QUOTATION_NO DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_CONDITION_OF_ASSET_sql = " SELECT L.NO ,L.CODE,L.DESCRIPTION,L.ACTIVE_STATUS,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.ACTIVE_STATUS,P.DEFAULT_VALUE  FROM(  SELECT \tCODE,\tDESCRIPTION,\tACTIVE_STATUS,\tDEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_CONDITION_OF_ASSET " + " WHERE UPPER(CODE) LIKE UPPER('" + vector.elementAt(0) + "%')  " + " AND UPPER(DESCRIPTION) LIKE UPPER('" + vector.elementAt(1) + "%')  " + " AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MAKE_CODE_sql = " SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,L.ITEM_SUB_CAT,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.ITEM_SUB_CAT,P.DEFAULT_VALUE  FROM(  SELECT  MAKE_CODE, MAKE_DESC,  ITEM_SUB_CAT,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MAKE " + " WHERE UPPER(MAKE_CODE) LIKE UPPER('" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') AND UPPER(MAKE_DESC) LIKE UPPER('" + vector.elementAt(1) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MODEL_CODE1_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE UPPER(MODEL_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') AND UPPER(DESCRIPTION) LIKE UPPER('" + vector.elementAt(1) + "%') AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MODEL_CODE2_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE UPPER(MAKE_CODE)=UPPER('" + vector.elementAt(1) + "') AND (UPPER(MODEL_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') AND UPPER(DESCRIPTION) LIKE UPPER('" + vector.elementAt(2) + "%')) AND ACTIVE_STATUS=('" + vector.elementAt(3) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_INQUIRY_NO_sql = " SELECT L.NO,L.INQUIRY_CODE AS INQUIRY_NO,L.CLIENT_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE  FROM   (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE  FROM(  SELECT  INQUIRY_CODE,\tCLIENT_NAME,\tADDRESS,\tADDRESS2,\tCITY_CODE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE (UPPER(INQUIRY_CODE) LIKE UPPER('" + vector.elementAt(0) + "%')OR UPPER(CLIENT_NAME) LIKE UPPER('" + vector.elementAt(0) + "%')OR UPPER(MK_OFFICER) LIKE UPPER('" + vector.elementAt(0) + "%')OR UPPER(ID_NO) LIKE UPPER('" + vector.elementAt(0) + "%')OR UPPER(TEL_NO) LIKE UPPER('" + vector.elementAt(0) + "%')) AND STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY INQUIRY_CODE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_PURCHASE_ORDER_NO_sql = " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.FINANCE_NO,TO_CHAR(NVL(L.TOTAL_NET,0),'999,999,999.99'),TO_CHAR(NVL(L.TOTAL_VAT,0),'999,999,999.99'),L.VENDOR_CODE,L.NAME,L.CLIENT_CODE,L.FULL_NAME,TO_CHAR(NVL(L.TOTAL,0),'999,999,999.99')  FROM   (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.FINANCE_NO,P.TOTAL_NET,P.TOTAL_VAT,P.VENDOR_CODE,P.NAME,P.CLIENT_CODE,P.FULL_NAME,P.TOTAL  FROM(  SELECT  PURCHASE_ORDER_NO,  A.APPLICATION_NO,  FINANCE_NO , TOTAL_NET,  TOTAL_VAT,  VENDOR_CODE,  NAME,  D.CLIENT_CODE,  FULL_NAME,  (TOTAL_NET+TOTAL_VAT)AS TOTAL  FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER A," + m_schema_name + ".AF_CO_MAS_VENDORS B," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C," + m_schema_name + ".AF_CO_MAS_CLIENT D " + " WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('" + vector.elementAt(0) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(1) + "') AND A.APPLICATION_NO=C.APPLICATION_NO  " + "\tAND B.VENDOR_CODE=A.VENDER_CODE " + "\tAND D.CLIENT_CODE=C.CLIENT_CODE " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_APP_NO_sql = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.DISTRICT_CODE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, " + " CO_APPLICANT," + " " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, " + " DISTRICT_CODE, " + " INQUARY_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_APP_NO_sql_1 = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, " + " CO_APPLICANT," + " " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, " + " INQUARY_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_VENDER_CODE_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE  FROM(  SELECT  VENDOR_CODE, NAME,  CATEGORY,  TYPE, DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE VENDOR_CODE LIKE UPPER('" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY VENDOR_CODE ASC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_ASSET_ID_sql2 = " SELECT L.NO,L.ASSET_ID,/* L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, */L.MODEL_CODE,L.SUB_MODEL_CODE,/* L.PRICING_NO, */L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,/*L.ADDRESS,L.CITY_CODE,L.PERIOD,*/L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE FROM   (SELECT ROWNUM NO,P.ASSET_ID,/* P.REG_NO,P.REG_DATE, */P.MODEL_CODE,P.SUB_MODEL_CODE,/* P.PRICING_NO, */P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,/*P.ADDRESS,P.CITY_CODE,P.PERIOD,*/P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE FROM(  SELECT  ASSET_ID,  MODEL_CODE,  SUB_MODEL_CODE,  STATUS,  SUPPLIER_CODE,  QTY,  COST,  PURPOSE,  APPLICATION_NO,  " + m_schema_name + ".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE," + " " + m_schema_name + ".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE" + " FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS " + " WHERE ASSET_ID LIKE UPPER('" + vector.elementAt(0) + "%') AND APPLICATION_NO = '" + vector.elementAt(1) + "'" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_DISTRICT_CODE_sql1 = " SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE   FROM   (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE  FROM(  SELECT  DISTRICT_CODE, DISTRICT_DESC,  PROVINCE_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_DISTRICT " + " WHERE (UPPER(DISTRICT_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(DISTRICT_DESC) LIKE UPPER('" + vector.elementAt(0) + "%'))  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_VENDOR_CODE_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME VENDOR_NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE  FROM(  SELECT  VENDOR_CODE, NAME,  CATEGORY,  TYPE, DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE (UPPER(VENDOR_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(NAME) LIKE UPPER('" + vector.elementAt(0) + "%'))  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY VENDOR_CODE ASC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_LOCATION_CODE_sql = " SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE FROM   (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE  FROM(  SELECT  LOCATION_CODE,  LOCATION_DESC,  ADDRESS1,   ADDRESS2,  CITY_CODE,  POSTAL_CODE,  COUNTRY_CODE  FROM " + m_schema_name + ".AF_CO_MAS_LOCATION " + " WHERE (LOCATION_CODE LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(LOCATION_DESC) LIKE UPPER('" + vector.elementAt(0) + "%') )  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		new_m_help_TXT_LOCATION_CODE_sql = " SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE FROM   (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE  FROM(  SELECT  LOCATION_CODE,  LOCATION_DESC,  ADDRESS1,   ADDRESS2,  CITY_CODE,  POSTAL_CODE,  COUNTRY_CODE  FROM " + m_schema_name + ".AF_CO_MAS_LOCATION  WHERE LOCATION_CODE NOT IN(SELECT BRANCH_CODE FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS WHERE BRANCH_CODE=LOCATION_CODE AND FINANCE_NO='"+vector.elementAt(2)+"') AND (LOCATION_CODE LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(LOCATION_DESC) LIKE UPPER('" + vector.elementAt(0) + "%') )  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		//    m_help_TXT_MAS_VENDOR_LOCATION_sql = " SELECT L.NO,L.BRANCH,L.LOCATION_CODE,L.VENDOR_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS,L.CITY_CODE FROM   (SELECT ROWNUM NO,P.BRANCH,P.LOCATION_CODE,P.VENDOR_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS,P.CITY_CODE  FROM(  SELECT  BRANCH,  LOCATION_CODE,  VENDOR_CODE,   TITLE,  FIRST_NAME,  LAST_NAME,  ID_NO,  ADDRESS,  CITY_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION " + " WHERE (BRANCH LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(VENDOR_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') )  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_MAS_VENDOR_LOCATION_sql = 
			" SELECT L.NO,L.BRANCH,L.LOCATION_CODE,L.VENDOR_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS,L.CITY_CODE "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.BRANCH,P.LOCATION_CODE,P.VENDOR_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS,P.CITY_CODE "+
			" FROM "+
			" ( SELECT  BRANCH,  LOCATION_CODE,  VENDOR_CODE,   TITLE,  FIRST_NAME,  LAST_NAME,  ID_NO,  ADDRESS,  CITY_CODE,  DEFAULT_VALUE "+
			" FROM " + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION " +
			" WHERE UPPER(VENDOR_CODE)=UPPER('" + vector.elementAt(1) + "') AND (BRANCH LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(VENDOR_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') ) "+
			" AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + 
			"  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";	
		
		
		m_help_TXT_VALUATION_NO2_sql = 
			" SELECT L.NO ,L.VALUATION_NO,L.PRO_INVOICE_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
			" L.CHASSIS_NO,L.COLOUR, L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE , "+
			" L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING, L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.VALUER_CODE,l.amt,L.YEAR_OF_MANUFACTURE,L.CONDITION_OF_ASSET,L.FORCED_SALES_VALUE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.PRO_INVOICE_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO, "+
			" P.CHASSIS_NO,P.COLOUR,\tP.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG, "+
			" P.METER_READING, P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.VALUER_CODE,p.amt,P.YEAR_OF_MANUFACTURE,P.CONDITION_OF_ASSET,P.FORCED_SALES_VALUE  "+
			" FROM( "+
			" SELECT  VALUATION_NO,"+
			" NVL(PRO_INVOICE_NO,'-') PRO_INVOICE_NO,"+
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
			" (select valuer_amount from " + m_schema_name + ".AF_CO_MAS_VALUERS where valuer_code=VALUER_CODE) as amt, "+
			"	NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
			"	NVL(CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
			"	NVL(FORCED_SALES_VALUE,0) FORCED_SALES_VALUE "+
			" FROM " + m_schema_name + ".AF_CO_PRO_APP_VALUATION " +
			" WHERE VALUATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  "+
			" AND APPLICATION_NO = '" + vector.elementAt(1) + "' "+
			" AND ACTIVE_STATUS  ='" + vector.elementAt(2) + "' " +
			" ORDER BY VALUATION_NO ASC " +
			"  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		m_help_TXT_VALUATION_NO1_sql = 
			" SELECT L.NO ,L.VALUATION_NO,L.PRO_INVOICE_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
			" L.CHASSIS_NO,L.COLOUR, L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE , "+
			" L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING, L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.VALUER_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.PRO_INVOICE_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO, "+
			" P.CHASSIS_NO,P.COLOUR,\tP.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG, "+
			" P.METER_READING, P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.VALUER_CODE  "+
			" FROM( "+
			" SELECT  VALUATION_NO,"+
			" NVL(PRO_INVOICE_NO,'-') PRO_INVOICE_NO,"+
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
			"	NVL(VALUER_CODE,'-') VALUER_CODE "+
			" FROM " + m_schema_name + ".AF_CO_PRO_APP_VALUATION " +
			" WHERE VALUATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  "+
			" AND APPLICATION_NO = '" + vector.elementAt(1) + "' "+
			" AND ACTIVE_STATUS  ='" + vector.elementAt(2) + "' " +
			" ORDER BY VALUATION_NO ASC " +
			"  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		
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
			" WHERE VALUER_CODE LIKE UPPER('"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_APPLICATION_NO=	
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE,P.INSURANCE_DONE_BY,P.PRIORITY,P.FULL_NAME,P.TEL_NO,P.NIC_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.APPLICATION_NO, "+
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
			" B.FULL_NAME FULL_NAME, "+ 
			" B.TEL_NO TEL_NO,  "+
			" B.NIC_NO NIC_NO  "+
			
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS X,LAKDL.AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			//  "  AND V.ACTIVE_STATUS=('"+vector.elementAt(2)+"')  "+
			"  ) B  "+
			"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('"+vector.elementAt(0)+"%') OR  "+
			"  UPPER(A.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR  "+
			"  A.APPLICATION_NO LIKE UPPER('"+vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('"+vector.elementAt(1)+"','"+vector.elementAt(2)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ s1+"  AND L.NO<= "+s2+" ";
		
		
		m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql=
			/*
		" SELECT L.NO ,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.INQUIRY_CODE,L.CLIENT_NAME "+
		" FROM  "+
		" (SELECT ROWNUM NO,P.LEAD_SOURCE_CATEGORY,P.LEAD_SOURCE_NAME,P.INQUIRY_CODE,P.CLIENT_NAME "+
		" FROM( "+
	" SELECT LEAD_SOURCE_CATEGORY,LEAD_SOURCE_NAME,INQUIRY_CODE,CLIENT_NAME "+
	" FROM LAKDL.AF_MK_PRO_INQUIRY a,LAKDL.AF_MK_MAS_LEAD_SOURCE_CAT c "+
	" WHERE LEAD_SOURCE_CATEGORY=SOURCE_CODE "+
	" and LEAD_SOURCE_CATEGORY LIKE UPPER('"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
		" ORDER BY LEAD_SOURCE_CATEGORY ASC "+
		"  )P)L  "+
		" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";   
	*/
			
			
			" SELECT L.NO ,L.LEAD_SOURCE_NAME ,L.LEAD_SOURCE_CATEGORY "+
			" FROM   "+
			" (SELECT ROWNUM NO,P.LEAD_SOURCE_NAME,P.LEAD_SOURCE_CATEGORY  "+
			" FROM(  "+
			" SELECT DISTINCT TRIM(LEAD_SOURCE_NAME) LEAD_SOURCE_NAME,LEAD_SOURCE_CATEGORY  "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY  "+
			" WHERE (UPPER(LEAD_SOURCE_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') and LEAD_SOURCE_NAME IS NOT NULL AND  LEAD_SOURCE_NAME NOT IN ('-')) OR UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('"+vector.elementAt(0)+"%')   "+
			//" AND ACTIVE_STATUS='Y' " +
			" ORDER BY LEAD_SOURCE_NAME ASC "+
			"  )P)L  "+
			" WHERE L.NO>= "+ s1+"  AND L.NO<="+s2+" ";
		
		
		
		
		
		m_help_TXT_ISSUER_CODE_sql=
			" SELECT L.NO ,L.BANK_CODE,L.BRANCH_CODE,L.BRANCH_NAME,L.ADDRESS1,L.ADDRESS2, "+
			"L.CITY_CODE,L.TEL_NO,L.FAX_NO,L.DAYS_TO_REALISE,L.DEFAULT_VALUE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.BRANCH_CODE,P.BRANCH_NAME,P.ADDRESS1,P.ADDRESS2, "+
			"P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT "+
			"BANK_CODE, "+
			"BRANCH_CODE, "+
			"BRANCH_NAME, "+
			"ADDRESS1, "+
			"ADDRESS2, "+
			"CITY_CODE, "+
			"TEL_NO, "+
			"FAX_NO, "+
			"DAYS_TO_REALISE, "+
			"DEFAULT_VALUE "+
			"FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
			"WHERE BANK_CODE LIKE UPPER('"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"ORDER BY BANK_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_FinanceSql_new =
			" SELECT P.NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') ) AND "+
			" UPPER(A.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" ORDER BY APPLICATION_NO ASC,PRIORITY ASC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
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
			" DEFAULT_VALUE,valuer_amount"+
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE (VALUER_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR FIRST_NAME LIKE UPPER('"+vector.elementAt(0)+"%') OR LAST_NAME LIKE UPPER('"+vector.elementAt(0)+"%')  OR ADDRESS LIKE UPPER('"+vector.elementAt(0)+"%')  OR CITY_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR  TEL_NO LIKE UPPER('"+vector.elementAt(0)+"%')   ) AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			" ORDER BY VALUER_CODE ASC"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";	
		
		
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
			" WHERE upper(REG_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			//	" AND CLIENT_CODE LIKE UPPER('%"+m_vector.elementAt(1)+"%') "+
			" AND upper(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			"AND A.ACTIVE_STATUS=('"+vector.elementAt(2)+"') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			"order by REG_NO DESC "+
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		m_help_TXT_VALUATION_NO_sql_new=
			" SELECT L.NO ,L.VALUER_CODE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE,L.VALUER_AMOUNT"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.VALUER_CODE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE,P.VALUER_AMOUNT "+
			" FROM( "+ 
			" SELECT "+
			" VALUER_CODE,"+
			" FIRST_NAME,"+
			" LAST_NAME,"+
			" ADDRESS,"+
			" ADDRESS2,"+
			" CITY_CODE, "+
			" VALUER_AMOUNT "+
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE VALUER_CODE LIKE UPPER('"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_FinanceSql_new1 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		//Added by Dineth on 2009-01-15
		m_help_TXT_FinanceSql_new4 =
			" SELECT P.NO,FINANCE_NO,BRANCH_CODE,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,BRANCH_CODE,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,BRANCH_CODE,APPLICATION_NO,CLIENT_CODE,UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND BRANCH_CODE LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND TRANSACTION_TYPE LIKE UPPER('%"+vector.elementAt(2)+"%') "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(3)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		//End by Dineth on 2009-01-15
		
		
		m_help_TXT_FinanceSql_new3 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" AND 		UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(2)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
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
			" WHERE UPPER(A.INVOICE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			" AND UPPER(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND A.ACTIVE_STATUS=('Y') "+
			" AND APPLICATION_STATUS='ACTIVATED' "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			" ORDER BY A.INVOICE_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_APPLICATION_NO_1=
			
			
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
			" WHERE APPLICATION_NO LIKE UPPER('"+vector.elementAt(0)+"%')  "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+  // OR APPLICATION_STATUS=('"+m_vector.elementAt(2)+"')
			" ORDER BY APPLICATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		
		
		m_help_TXT_DISPUTES_VALUATION_NO_sql_new=		
			
			" SELECT L.NO,L.VALUATION_NO,L.ASSET_ID,L.APPLICATION_NO,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
			" L.CHASSIS_NO,L.COLOUR,L.MODEL_CODE,L.NOTES,L.REMARKS,L.VALUATION_DATE,L.VALUE, "+
			" L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING,L.ACTIVE_STATUS,L.GENERAL_INDEX, "+
			" L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.PRO_INVOICE_NO,L.INVENTORY_NO,L.VAL_STATUS, "+
			" L.VALUER_CODE,L.YEAR_OF_MANUFACTURE,L.CONDITION_OF_ASSET,L.FORCED_SALES_VALUE, "+
			" L.DISP_COMMENTS,L.DISP_STATUS  "+
			" FROM "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.APPLICATION_NO,P.SUB_MODEL_CODE,P.REG_NO, "+
			" P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE, "+
			" P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,P.ACTIVE_STATUS,P.GENERAL_INDEX, "+
			" P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.PRO_INVOICE_NO,P.INVENTORY_NO,P.VAL_STATUS, "+
			" P.VALUER_CODE,P.YEAR_OF_MANUFACTURE,P.CONDITION_OF_ASSET,P.FORCED_SALES_VALUE, "+
			" P.DISP_COMMENTS,P.DISP_STATUS  "+
			" FROM "+
			" (SELECT "+
			" VALUATION_NO, "+ //2
			" ASSET_ID, "+   //3
			" APPLICATION_NO, "+  //4
			" SUB_MODEL_CODE, "+  //5
			" REG_NO, "+       //6
			" ENGINE_NO, "+    //7
			" CHASSIS_NO, "+   //8
			" NVL(COLOUR,'-') COLOUR, "+       //9
			" MODEL_CODE, "+   //10
			" NOTES, "+     //11
			" REMARKS, "+   //12
			" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+ //13
			" VALUE, "+ //14
			" TYPE_OF_BODY, "+ //15
			" TO_CHAR(DATE_OF_REG,'DD-MM-YYYY') DATE_OF_REG, "+ //16
			" METER_READING, "+  //17
			" ACTIVE_STATUS, "+  //18
			" GENERAL_INDEX, "+  //19
			" SEATING_CAPACITY, "+ //20
			" NO_OF_CYLINDERS, "+ //21
			" PRO_INVOICE_NO, "+ //22
			" INVENTORY_NO, "+ 
			" VAL_STATUS, "+  //24
			" VALUER_CODE, "+ //25
			" YEAR_OF_MANUFACTURE, "+ //26
			" CONDITION_OF_ASSET, "+ //27
			" FORCED_SALES_VALUE, "+ //28
			" NVL(DISP_COMMENTS,'-') DISP_COMMENTS, "+
			" DISP_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			" WHERE "+
			" DISP_STATUS='N' AND "+
			" (VALUATION_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" APPLICATION_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(REG_NO) LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(ENGINE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(CHASSIS_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) "+
			" ORDER BY VALUATION_NO DESC)P)L "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		
		m_help_TXT_DISPUTES_VALUATION_NO_sql_edit=		
			
			" SELECT L.NO,L.VALUATION_NO,L.ASSET_ID,L.APPLICATION_NO,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
			" L.CHASSIS_NO,L.COLOUR,L.MODEL_CODE,L.NOTES,L.REMARKS,L.VALUATION_DATE,L.VALUE, "+
			" L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING,L.ACTIVE_STATUS,L.GENERAL_INDEX, "+
			" L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.PRO_INVOICE_NO,L.INVENTORY_NO,L.VAL_STATUS, "+
			" L.VALUER_CODE,L.YEAR_OF_MANUFACTURE,L.CONDITION_OF_ASSET,L.FORCED_SALES_VALUE, "+
			" L.DISP_COMMENTS,L.DISP_STATUS  "+
			" FROM "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.ASSET_ID,P.APPLICATION_NO,P.SUB_MODEL_CODE,P.REG_NO, "+
			" P.ENGINE_NO,P.CHASSIS_NO,P.COLOUR,P.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE, "+
			" P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG,P.METER_READING,P.ACTIVE_STATUS,P.GENERAL_INDEX, "+
			" P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.PRO_INVOICE_NO,P.INVENTORY_NO,P.VAL_STATUS, "+
			" P.VALUER_CODE,P.YEAR_OF_MANUFACTURE,P.CONDITION_OF_ASSET,P.FORCED_SALES_VALUE, "+
			" P.DISP_COMMENTS,P.DISP_STATUS  "+
			" FROM "+
			" (SELECT "+
			" VALUATION_NO, "+ //2
			" ASSET_ID, "+   //3
			" APPLICATION_NO, "+  //4
			" SUB_MODEL_CODE, "+  //5
			" REG_NO, "+       //6
			" ENGINE_NO, "+    //7
			" CHASSIS_NO, "+   //8
			" NVL(COLOUR,'-') COLOUR, "+       //9
			" MODEL_CODE, "+   //10
			" NOTES, "+     //11
			" REMARKS, "+   //12
			" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+ //13
			" VALUE, "+ //14
			" TYPE_OF_BODY, "+ //15
			" TO_CHAR(DATE_OF_REG,'DD-MM-YYYY') DATE_OF_REG, "+ //16
			" METER_READING, "+  //17
			" ACTIVE_STATUS, "+  //18
			" GENERAL_INDEX, "+  //19
			" SEATING_CAPACITY, "+ //20
			" NO_OF_CYLINDERS, "+ //21
			" PRO_INVOICE_NO, "+ //22
			" INVENTORY_NO, "+ 
			" VAL_STATUS, "+  //24
			" VALUER_CODE, "+ //25
			" YEAR_OF_MANUFACTURE, "+ //26
			" CONDITION_OF_ASSET, "+ //27
			" FORCED_SALES_VALUE, "+ //28
			" NVL(DISP_COMMENTS,'-') DISP_COMMENTS, "+
			" DISP_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION "+
			" WHERE "+
			" DISP_STATUS='Y' AND "+
			" (VALUATION_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" APPLICATION_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(REG_NO) LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(ENGINE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(CHASSIS_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) "+
			" ORDER BY VALUATION_NO DESC)P)L "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		
		
		m_help_TXT_INQUARY_NO_sql_report=		
			"SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO,ADDRESS ADDRESS1 , ADDRESS2, CITY_CODE, "+
			"LEGAL_ENTITY,LEAD_SOURCE_NAME,ID_NO, MK_OFFICER,INITIATION_TYPE, CLIENT_CATEGORY, "+
			"LEAD_SOURCE_CATEGORY,INTRODUCER,EMAIL, TEAM,FAX_NO,MK_SUPERVISOR, CONTACT_PERSON, "+
			"SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE,STATUS,INQUIRY_STATUS "+
			"FROM "+
			"(SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO, FAX_NO, "+
			"ADDRESS, CITY_CODE, LEGAL_ENTITY, STATUS, "+
			"INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY, "+
			"LEAD_SOURCE_NAME, INTRODUCER, ID_NO, INQUIRY_STATUS, "+
			"ENT_USER,ADDRESS2, EMAIL, TEAM, "+
			"MK_OFFICER, MK_SUPERVISOR, CONTACT_PERSON, "+
			"SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE "+
			"FROM "+
			"(SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') "+
			"TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') "+
			"CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE, "+
			"NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') "+
			"LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER, "+
			"NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') "+
			"CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM LAKDL.AF_MK_PRO_INQUIRY "+
			"WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			"UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			"UPPER(ID_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			"UPPER(MOBILE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			"ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P "+
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		
		m_help_TXT_REPOSSESSION_NO_sql_report=
			" SELECT L.NO,L.REPOSSESSION_NO,L.FULL_NAME,L.VEHICLE_NO,L.ENGINE_NO,L.CHASSIS_NO,L.VALUE,L.SEIZER_NAME,L.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.REPOSSESSION_NO,P.FULL_NAME,P.VEHICLE_NO,P.ENGINE_NO,P.CHASSIS_NO,P.VALUE,P.SEIZER_NAME,P.REPOSSESSED_DATE "+
			" FROM "+
			" (SELECT "+
			" DISTINCT A.REPOSSESSION_NO, "+
			" D.FULL_NAME, "+
			" C.VEHICLE_NO, "+
			" C.ENGINE_NO, "+
			" C.CHASSIS_NO, "+
			" (SELECT VALUE FROM LAKDL.AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
			" FROM LAKDL.AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+
			" LAKDL.af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+
			" TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YY') REPOSSESSED_DATE "+
			" FROM LAKDL.AF_RE_PRO_REPOSSESSION A, "+
			" LAKDL.AF_CO_PRO_APPLICATION_DETAILS B, "+
			" LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS C, "+
			" LAKDL.AF_CO_MAS_CLIENT D , "+
			" LAKDL.AF_CO_MAS_MODEL F "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO "+
			" AND B.APPLICATION_NO=C.APPLICATION_NO "+
			" AND D.CLIENT_CODE=B.CLIENT_CODE "+
			" AND C.MODEL_CODE=F.MODEL_CODE "+
			" AND (UPPER(A.REPOSSESSION_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(A.FINANCE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(D.FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(C.VEHICLE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(LAKDL.af_co_get_seizer_name(A.SEIZER_CODE)) LIKE UPPER('%" + vector.elementAt(0) + "%')) "+
			" ORDER BY A.REPOSSESSION_NO DESC)P)L "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		m_help_TXT_PURCHASE_ORDER_NO_sql_report=
			
			" SELECT P.NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.CLIENT_NAME, "+
			" P.VENDER_NAME,P.ISSUED_DATE,P.BRANCH_CODE,P.ID_NO,P.PAYER_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,PURCHASE_ORDER_NO,APPLICATION_NO,CLIENT_NAME, "+
			" VENDER_NAME,ISSUED_DATE,BRANCH_CODE,ID_NO,PAYER_NAME "+
			" FROM "+
			" (SELECT A.PURCHASE_ORDER_NO PURCHASE_ORDER_NO, "+
			" A.APPLICATION_NO APPLICATION_NO, "+
			" D.FULL_NAME CLIENT_NAME, "+
			" B.NAME VENDER_NAME, "+
			" NVL(TO_CHAR(ISSUED_DATE,'DD-MON-YY'),'-') ISSUED_DATE, "+
			" NVL(BRANCH_CODE,'-') BRANCH_CODE, "+
			" NVL(ID_NO,'-') ID_NO, "+
			" NVL(PAYER_NAME,'-') PAYER_NAME "+
			" FROM LAKDL.AF_CR_PRO_PURCHASE_ORDER A, "+
			" LAKDL.AF_CO_MAS_VENDORS B, "+
			" LAKDL.AF_CO_PRO_APPLICATION_DETAILS C, "+
			" LAKDL.AF_CO_MAS_CLIENT D "+
			" WHERE B.VENDOR_CODE=A.VENDER_CODE AND "+
			" A.APPLICATION_NO=C.APPLICATION_NO AND "+
			" D.CLIENT_CODE=C.CLIENT_CODE "+
			" AND "+
			" (UPPER(A.PURCHASE_ORDER_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(D.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))))P "+
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		
		m_help_TXT_CREDIT_NO_sql_report=
			"SELECT P.NO,P.REF_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.INVOICE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO, REF_NO,FINANCE_NO,CLIENT_CODE,FULL_NAME,INVOICE_NO "+
			" FROM "+ 
			" (SELECT "+
			" A.REF_NO, "+
			" A.FINANCE_NO, "+
			" B.CLIENT_CODE, "+
			" B.FULL_NAME, "+
			" A.INVOICE_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
			" WHERE A.FINANCE_NO=C.FINANCE_NO AND "+
			" A.INVOICE_NO=C.INVOICE_NO AND "+
			" C.CLIENT_CODE=B.CLIENT_CODE AND ( "+
			" A.REF_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" B.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" B.FULL_NAME LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ 
			" A.INVOICE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') ) ))P "+	
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		
		
		
		
		
		
		
		
		ClientSql1 =   	"SELECT P.NO, P.CLIENT_CODE Client,UPPER(FULL_NAME) FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT CLIENT_CODE,FULL_NAME, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(CLIENT_CODE) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(MOBILE_NO)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(TEL_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(NIC_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) " +
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";		
		/*added by madhawa for BUlk print 2012-01-30 **/
		bulkprintClient_Sql = "SELECT P.NO, P.CLIENT_CODE Client,UPPER(FULL_NAME) FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT CLIENT_CODE,FULL_NAME, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(CLIENT_CODE) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(MOBILE_NO)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(TEL_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(NIC_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) " +
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";		
		
		//======================Added by Dineth on 2008-08-25====================
		ClientSql2 =   	"SELECT P.NO, P.CLIENT_CODE Client,P.FINANCE_NO,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE,FINANCE_NO,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE,BUSINESS_CERTIFICATE_NO "+
			"FROM "+
			"(SELECT Q.CLIENT_CODE,Q.FINANCE_NO,Q.FULL_NAME, Q.FIRST_NAME, Q.SURNAME,Q.NIC_NO,Q.ADDRESS1,Q.ADDRESS2,Q.TEL_NO,Q.MOBILE_NO,Q.EMAIL, Q.ACTIVE_STATUS, Q.TEMP_ACTIVE_STATUS,Q.CITY_CODE,Q.BUSINESS_CERTIFICATE_NO "+
			"	 FROM "+
			"(SELECT A.CLIENT_CODE,B.FINANCE_NO,A.FULL_NAME,A.FIRST_NAME,A.SURNAME,A.NIC_NO,A.ADDRESS1,A.ADDRESS2,A.TEL_NO,A.MOBILE_NO,A.EMAIL, A.ACTIVE_STATUS, A.TEMP_ACTIVE_STATUS,A.CITY_CODE,A.BUSINESS_CERTIFICATE_NO "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
			"  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE(+) "+
			//"  UPPER(B.FINANCE_NO) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			"  ORDER BY A.CLIENT_CODE DESC) Q  "+
			
			"	 WHERE (UPPER(Q.FULL_NAME)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(Q.CLIENT_CODE) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(Q.ADDRESS1)   LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(Q.CITY_CODE)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(Q.MOBILE_NO)  LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(Q.TEL_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(Q.EMAIL)      LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"         UPPER(Q.NIC_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(Q.BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) "+
			
			" ORDER BY Q.CLIENT_CODE DESC )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";		
		
		
		//======================End by Dineth on 2008-08-25
		
		ClientSql_Balance_Confim =   	"SELECT P.NO,P.CLIENT_CODE Client,P.FINANCE_NO,P.FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,CITY_CODE,P.CITY "+//Added By Sandun on 16-12-2008
			"FROM "+
			"(SELECT ROWNUM NO,CLIENT_CODE,FINANCE_NO,FULL_NAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,CITY_CODE,BUSINESS_CERTIFICATE_NO,CITY "+
			"FROM "+
			"(SELECT DISTINCT Q.CLIENT_CODE,Q.FINANCE_NO,Q.FULL_NAME,Q.NIC_NO,Q.ADDRESS1,Q.ADDRESS2,Q.TEL_NO,Q.MOBILE_NO,Q.EMAIL, Q.CITY_CODE,Q.BUSINESS_CERTIFICATE_NO,Q.CITY "+
			"	 FROM "+
			"(SELECT A.CLIENT_CODE,NVL(B.FINANCE_NO,'-') FINANCE_NO,NVL(A.FULL_NAME,'-') FULL_NAME,NVL(A.NIC_NO,'-') NIC_NO,NVL(A.ADDRESS1,'-') ADDRESS1,NVL(A.ADDRESS2,'-') ADDRESS2,NVL(A.TEL_NO,'-') TEL_NO,NVL(A.MOBILE_NO,'-') MOBILE_NO,NVL(A.EMAIL,'-') EMAIL, NVL(A.CITY_CODE,'-') CITY_CODE,NVL(A.BUSINESS_CERTIFICATE_NO,'-') BUSINESS_CERTIFICATE_NO ,"+
			" INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')) CITY "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+
			"  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			"  WHERE A.CLIENT_CODE = B.CLIENT_CODE "+	
			"  AND A.ACTIVE_STATUS = 'Y' "+
			"  AND B.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL') "+
			"  ORDER BY A.CLIENT_CODE DESC) Q  "+														
			"	 WHERE (UPPER(Q.FULL_NAME)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(Q.CLIENT_CODE) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(Q.FINANCE_NO) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(Q.NIC_NO)     LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(Q.BUSINESS_CERTIFICATE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')) "+	
			" AND Q.FINANCE_NO IS NOT NULL "+
			" ORDER BY Q.CLIENT_CODE )) P "+		
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_QUOTATION_NO_sql2 = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.STATUS, "+
			"L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  "+
			"FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO, "+
			"P.CLIENT_NAME,P.STATUS,P.APPR_USER,P.APPR_DATE "+
			"FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO, CLIENT_NAME, \tA.STATUS, "+
			"APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION A ,"+
			"" + m_schema_name + ".AF_MK_PRO_INQUIRY B" + 
			" WHERE (QUOTATION_NO LIKE UPPER('" + vector.elementAt(0) + "%') "+
			" and INQUIRY_NO  like ('" + vector.elementAt(1) + "%')) "+
			//"OR CLIENT_NAME LIKE('" + vector.elementAt(0) + "%'))  " + 
			" AND INQUIRY_CODE=INQUIRY_NO " + " AND A.STATUS=('"+vector.elementAt(2)+"') " + 
			" ORDER BY QUOTATION_NO DESC " + "  )P)L  " + 
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		PriceSql_3 = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD, "+
			"PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,   "+
			"NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      "+
			"ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     "+
			"MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE,  "+
			"ASSET_USAGE_TYPE,     VAT_PERCENTAGE,  "+
			"ENGINE_CAPACITY, FUEL_TYPE, TARE,     "+
			" MAINTENANCE_STATUS,      OUTFLOW_PATTERN,  "+
			"SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,   "+
			"SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE,  "+
			"INT_MARGIN,     CONDITION_OF_ASSET    "+
			"FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,   "+
			"TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     "+
			"NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,  "+
			"MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,   "+
			"VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     "+
			"MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,   "+
			"CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE,  "+
			"VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, "+
			"INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, "+
			"GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     "+
			"ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE,  "+
			"ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     "+
			"MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,    "+
			"CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE,  "+
			"INT_MARGIN,     CONDITION_OF_ASSET  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " +
			"WHERE PRICING_NO LIKE '" + vector.elementAt(0) + "%' AND " + 
			"(INQUIRY_NO LIKE '" + vector.elementAt(1) + "%' OR INQUIRY_NO IS NULL ) " + 
			"ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		m_help_TXT_PURCHASE_ORDER_NO_sql_1 = " SELECT L.NO ,L.PURCHASE_ORDER_NO, "+
			"L.APPLICATION_NO,L.FINANCE_NO,TO_CHAR(NVL(L.TOTAL_NET,0),'999,999,999.99'), "+
			"TO_CHAR(NVL(L.TOTAL_VAT,0),'999,999,999.99'),L.VENDOR_CODE,L.NAME,L.CLIENT_CODE, "+
			"L.FULL_NAME,TO_CHAR(NVL(L.TOTAL,0),'999,999,999.99')  FROM    "+
			"(SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.FINANCE_NO,P.TOTAL_NET, "+
			"P.TOTAL_VAT,P.VENDOR_CODE,P.NAME,P.CLIENT_CODE,P.FULL_NAME,P.TOTAL   "+
			"FROM(  SELECT  PURCHASE_ORDER_NO,  A.APPLICATION_NO,  FINANCE_NO , TOTAL_NET,  "+
			" TOTAL_VAT,  VENDOR_CODE,  NAME,  D.CLIENT_CODE,  FULL_NAME,  "+
			" (TOTAL_NET+TOTAL_VAT)AS TOTAL  FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER A,  "+
			"" + m_schema_name + ".AF_CO_MAS_VENDORS B, "+
			"" + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C, "+
			"" + m_schema_name + ".AF_CO_MAS_CLIENT D " +
			" WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('" + vector.elementAt(0) + "%') and upper(a.application_no) like UPPER('" + vector.elementAt(1) + "%') "+
			"AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "') AND A.APPLICATION_NO=C.APPLICATION_NO  " +
			"AND B.VENDOR_CODE=A.VENDER_CODE " + "AND D.CLIENT_CODE=C.CLIENT_CODE " + "  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_FinanceSql_new2 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			"and upper(APPLICATION_NO) like upper('"+vector.elementAt(1)+"%') "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(2)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		m_help_TXT_SO_NO_sql=
			
			"SELECT L.NO ,L.SO_NO,L.FINANCE_NO,L.APPLICATION_NO,L.CLIENT_CODE, "+
			"L.REG_NO,L.END_DATE,L.START_DATE,L.ACC_NO,L.BANK_CODE,L.AMOUNT "+
			"FROM     "+
			"(SELECT ROWNUM NO,P.SO_NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE, "+
			"P.REG_NO,P.END_DATE,P.START_DATE,P.ACC_NO,P.BANK_CODE,P.AMOUNT  "+
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
			"AMOUNT  "+
			"FROM  "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS  A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
			"WHERE UPPER(SO_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			"and upper(A.FINANCE_NO) like upper('"+vector.elementAt(1)+"%') "+
			"AND A.FINANCE_NO=B.FINANCE_NO "+
			"AND B.APPLICATION_NO=C.APPLICATION_NO "+
			"AND STATUS =UPPER('"+vector.elementAt(2)+"') "+
			"order by so_no DESC "+
			" )P)L     "+			
			" WHERE L.NO>= "+ s1+"   AND L.NO<=  "+s2+" ";
		
		m_help_TXT_ACC_sql=	
			"SELECT L.NO ,L.ACCOUNT_NO,L.BANK_CODE,L.BRANCH_CODE,L.CLIENT_CODE "+
			"FROM  "+
			"(SELECT ROWNUM NO,P.ACCOUNT_NO,P.BANK_CODE,P.BRANCH_CODE,P.CLIENT_CODE "+
			"FROM(  "+
			" SELECT "+
			"DISTINCT ACCOUNT_NO,BANK_CODE,BRANCH_CODE ,A.CLIENT_CODE "+
			"FROM "+m_schema_name+".AF_CO_MAS_CLIENT_BANKS A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
			" WHERE UPPER(ACCOUNT_NO) LIKE UPPER('"+vector.elementAt(0)+"%') AND  UPPER(A.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+vector.elementAt(2)+"')"+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			"order by ACCOUNT_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		ReceiptSql1 = "SELECT P.NO, REC_NO,REC_AMOUNT,CHEQUE_NO,EFF_VALDATE,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
			"FROM "+
			"(SELECT ROWNUM NO, REC_NO,REC_AMOUNT,EFF_VALDATE,CHEQUE_NO,CLIENT_CODE,PAYER_BRANCH_CODE,PAYER_ACC_NO,CHEQUE_DATE "+
			"FROM "+
			"(SELECT A.REC_NO,A.REC_AMOUNT,A.CHEQUE_NO,TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY') EFF_VALDATE,A.CLIENT_CODE,A.PAYER_BRANCH_CODE,A.PAYER_ACC_NO,TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE  A.CLIENT_CODE= B.CLIENT_CODE AND "+
			"        (REC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(B.ADDRESS1)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(B.TEL_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.EMAIL)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(B.NIC_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(B.BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR " +
			"        A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%')) AND "+
			"        A.STATUS='E' "+
			" ORDER BY A.REC_NO DESC,A.EFF_VALDATE,B.FULL_NAME )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
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
			" WHERE DIPOSIT_NO LIKE UPPER('%"+vector.elementAt(0)+"%')  AND  STATUS=('"+vector.elementAt(1)+"') "+
			" ORDER BY DIPOSIT_NO DESC " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		m_help_TXT_PAYMENT_NO=
			" SELECT L.NO ,L.PAYMENT_NO,L.SUS_REF_NO,L.CLIENT_CODE,L.SETTLE_MODE,L.ENTRY_TYPE,L.PAY_AMOUNT,L.LIC_ACC_NO,L.LIC_BRANCH_CODE,L.BRANCH_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PAYMENT_NO,P.SUS_REF_NO,P.CLIENT_CODE,P.SETTLE_MODE,P.ENTRY_TYPE,P.PAY_AMOUNT,P.LIC_ACC_NO,P.LIC_BRANCH_CODE,P.BRANCH_NAME "+
			" FROM( "+ 
			" SELECT PAYMENT_NO, "+
			" SUS_REF_NO, "+
			" CLIENT_CODE, "+
			" SETTLE_MODE, "+
			" ENTRY_TYPE, "+ 
			" PAY_AMOUNT, "+
			" LIC_ACC_NO, "+
			" LIC_BRANCH_CODE, "+
			" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE) BRANCH_NAME "+
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
			" WHERE UPPER(PAYMENT_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') AND UPPER(SUS_REF_NO) LIKE ('%"+vector.elementAt(1)+"%') "+
			" ORDER BY PAYMENT_NO DESC " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_SUS_REF_NO=
			" SELECT L.NO ,L.SUS_REF_NO,L.REF_NO,L.TOT_SETTLE_AMOUNT,L.BAL_TO_BE_PAID,L.CURR_CODE,L.VALUE_DATE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.SUS_REF_NO,P.REF_NO,P.TOT_SETTLE_AMOUNT,P.BAL_TO_BE_PAID,P.CURR_CODE,P.VALUE_DATE "+
			" FROM( "+ 
			" SELECT  "+
			" SUS_REF_NO, "+
			" REF_NO, "+
			" TOT_SETTLE_AMOUNT, "+
			" BAL_TO_BE_PAID, "+
			" CURR_CODE, "+
			" TO_CHAR(VALUE_DATE,'dd-mm-yyyy') VALUE_DATE	"+
			" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
			" WHERE UPPER(SUS_REF_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') AND UPPER(REF_NO) LIKE UPPER('%"+vector.elementAt(1)+"%')  "+
			" ORDER BY SUS_REF_NO DESC " +
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		//Added by mahela on 30-04-2007
		m_help_TXT_DIVISION_CODE_sql=
			
			" SELECT L.NO ,L.DIVISION_CODE,L.DESCRIPTION "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.DIVISION_CODE,P.DESCRIPTION "+
			" FROM( "+ 
			" SELECT "+
			" DIVISION_CODE, "+
			" DESCRIPTION "+
			" FROM "+m_schema_name+".CO_CO_MAS_DIVISION "+
			" WHERE (DIVISION_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR  DESCRIPTION LIKE UPPER('"+vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		//Added by mahela on 30-04-2007	
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
			" WHERE (TRAN_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR DESCRIPTION LIKE UPPER('"+vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";		
		
		
		
		
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
			" WHERE ( UPPER(TEAM_ID) LIKE UPPER('"+vector.elementAt(0)+"%') OR UPPER(TEAM_DESC) LIKE UPPER('"+vector.elementAt(0)+"%') ) AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		/*m_help_TXT_FinanceSql_sql =
		" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
		" FROM "+
		" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
		" FROM "+
		" (SELECT NVL(FINANCE_NO,'-') finance_NO,APPLICATION_NO,CLIENT_CODE,UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
		" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		//" WHERE   UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
		//" AND 		UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
		//" AND 		APPLICATION_STATUS=('"+vector.elementAt(2)+"')"+
		//" WHERE APPLICATION_STATUS=('"+vector.elementAt(1)+"') "+
		" ORDER BY FINANCE_NO DESC) "+
		" WHERE   (UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
		" AND 		UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"') "+
		" OR 		UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
		" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+			
		//" AND   "+m_schema_name+".AF_CO_GET_LETTER_SENT_STATUS(FINANCE_NO,'15')='Y' "+
		" ORDER BY FINANCE_NO DESC   ) P "+
		" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" "; */
		
		/*==================Commented by Dineth on 04-08-2008
		m_help_TXT_FinanceSql_sql =
		" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
		" FROM "+
		" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
		" FROM "+
		" (SELECT NVL(FINANCE_NO,'-') finance_NO,APPLICATION_NO,CLIENT_CODE, "+
		" UPPER(LAKDL.af_co_get_client_name(CLIENT_CODE)) AS NAME "+
		" FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE APPLICATION_STATUS=('ACTIVATED') "+
		" ORDER BY FINANCE_NO DESC) "+
		" WHERE  (UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
		" OR 		UPPER(APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
		" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
		" AND	UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
		" ORDER BY FINANCE_NO DESC   ) P "+
		" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";*/
		
		//================Modified by Dineth on 04-08-2008
		
		// commented by udara 14-11-2013
		/*		
		m_help_TXT_FinanceSql_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER(LAKDL.af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, "+
			" LAKDL.AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ // mod by udara on 19-09-2012 with % for left search
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ // mod by udara on 19-09-2012 with % for left search
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))  "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			//" AND	UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		*/
		
		
		// commented by udara 10-04-2014
		/*
		// added by udara 14-11-2013
		m_help_TXT_FinanceSql_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME  "+
			" FROM  "+
			" ( "+
			" SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME  "+
			" FROM  "+
			" ( "+
			
			" SELECT DISTINCT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO,  "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
			" WHERE A.APPLICATION_STATUS=('ACTIVATED')  "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+)  "+
			
			
			" ) Q "+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')   "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))   "+
			" ORDER BY Q.FINANCE_NO DESC   ) P  "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		*/
		
		
		// added by udara on 31-10-2014
		
		m_help_TXT_FinanceSql_sanction_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME  "+
			" FROM  "+
			" ( "+
			" SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME  "+
			" FROM  "+
			" ( "+
			
			" SELECT DISTINCT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO,  "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
			//" WHERE A.APPLICATION_STATUS=('ACTIVATED')  "+ // commented by udara 29-04-2014
			" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI','REJECT','CANCEL_PO')  "+ // mod by udara 31-10-2014 // " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI')  "+ // " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED')  "+ // commented by udara 29-04-2014
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+)  "+
			//" AND B.ACTIVE_STATUS = 'Y' "+ // added by udara 10-04-2014
			
			" ) Q "+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')   "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('"+vector.elementAt(0)+"%'))   "+
			" ORDER BY Q.FINANCE_NO DESC   ) P  "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		m_help_TXT_FinanceSql_sanction_sql2 =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			//" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+ // commented by udara 29-04-2014
			//" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED') "+
			" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI','REJECT','CANCEL_PO') "+ // mod by udara 31-10-2014 " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI') "+ 
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			//" AND B.ACTIVE_STATUS = 'Y' "+ // added by udara 09-04-2014
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		// end by udara 31-10-2014
		
		// added by udara 10-04-2014
		m_help_TXT_FinanceSql_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME  "+
			" FROM  "+
			" ( "+
			" SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME  "+
			" FROM  "+
			" ( "+
			
			" SELECT DISTINCT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO,  "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
			//" WHERE A.APPLICATION_STATUS=('ACTIVATED')  "+ // commented by udara 29-04-2014
			" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI')  "+ // mod by udara 31-10-2014 // " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI')  "+ // " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED')  "+ // commented by udara 29-04-2014
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+)  "+
			" AND B.ACTIVE_STATUS = 'Y' "+ // added by udara 10-04-2014
			
			" ) Q "+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('"+vector.elementAt(0)+"%')   "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('"+vector.elementAt(0)+"%'))   "+
			" ORDER BY Q.FINANCE_NO DESC   ) P  "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		
		// added by udara 20-11-2013
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
			
			
			
			//"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // commented by udara 13-05-2015 // added by udara on 26-06-2013 // ,'TERMI'
			"            B.APPLICATION_STATUS NOT IN ('CANCEL','ENT_CON','ENTERED') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
			"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ s1+" AND R.AAA <= "+s2+" ";
		
		// added by udara 04-09-2014
		
		m_help_TXT_FinanceSql2_VIR_sql_new =   	" "+
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
			" 			 B.FINANCE_NO IN (SELECT D.FINANCE_NO FROM "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DOC D WHERE D.FINANCE_NO =  B.FINANCE_NO AND D.STATUS <> 'T') AND "+
			
			
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
			"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ s1+" AND R.AAA <= "+s2+" ";
		
		// end by udara 04-09-2014
		
		
		// ADDED BY SAJITH MENDIS ON 22-04-2014
		m_help_TXT_FinanceSql2_sql_new =   	" "+
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
			" 			 B.FINANCE_NO NOT IN (SELECT D.FINANCE_NO FROM "+m_schema_name+".AF_MK_VEHICLE_INSPEC_DOC D WHERE D.FINANCE_NO =  B.FINANCE_NO) AND "+
			
			
			"            B.APPLICATION_STATUS NOT IN ('CANCEL') AND "+ // added by udara on 26-06-2013 // ,'TERMI'
			"    (   UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(ADDRESS1)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(A.CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(TEL_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(EMAIL)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        UPPER(NIC_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	       UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR " +
			"        FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"        CHASSIS_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //added by nuwan de silva 30-jul-07
			"            NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO),' ') LIKE '%"+vector.elementAt(0)+"%' OR "+ // added by udara on 11-07-2013
			
			"        c.REG_NO   LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			//"WHERE P.NO>= "+ Start_Val+" AND P.NO<= "+End_Val+" ";
			" WHERE R.AAA >= "+ s1+" AND R.AAA <= "+s2+" ";
		
		
		// ADDED BY SAJITH MENDIS ON 11-04-2014
		m_help_TXT_upload_doc =  
			" SELECT L.NO ,L.FINANCE_NO,L.DOCUMENT_NAME,L.DOCUMENT_NO,L.FILE_NAME  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.DOCUMENT_NAME,P.DOCUMENT_NO,P.FILE_NAME"+
			" FROM( "+ 
			" SELECT "+
			" FINANCE_NO, "+
			" DOCUMENT_NAME, "+
			" DOCUMENT_NO, "+
			" FILE_NAME "+
			" FROM "+m_schema_name+".AF_MK_DOCUMENT_UPLOAD "+
			" WHERE UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') AND ACTIVE_STATUS=('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" "; 
		
		
		
		
		
		
		/*
		m_help_TXT_FinanceSql2_sql =
		" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.NAME  "+
 		" FROM  "+
			 " ( "+
        " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.NAME  "+
        " FROM  "+
        " ( "+
        
          " SELECT DISTINCT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,  "+
          " UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
          " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
          " WHERE A.APPLICATION_STATUS=('ACTIVATED')  "+
          " AND A.APPLICATION_NO=B.APPLICATION_NO(+)  "+
          
          
        " ) Q "+
			 " WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			 " OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			 " OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			 " OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))   "+
			 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
		" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		*/
		
		
		
		//====================Added by Dineth on 2008-08-26
		
		
		m_help_TXT_FinanceSql_sql2 =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			//" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+ // commented by udara 29-04-2014
			//" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED') "+
			" WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI') "+ // mod by udara 31-10-2014 " WHERE A.APPLICATION_STATUS IN ('ACTIVATED','TERMINATED','TERMI') "+ 
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" AND B.ACTIVE_STATUS = 'Y' "+ // added by udara 09-04-2014
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		// udara 12-03-2015

		
		m_help_TXT_confirmation_rpt_edit_sql =
				" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.APPLICATION_STATUS "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.APPLICATION_STATUS  "+
								 " FROM  "+
							        " ( "+
							            " select NVL(a.FINANCE_NO,'-') FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)   APPLICATION_NO, "+
										"  B.APPLICATION_STATUS APPLICATION_STATUS "+
							            " FROM   "+m_schema_name+".AF_CONFIRMATION_REPORT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
										//" WHERE "+m_schema_name+".AF_CO_GET_DOCREF_LOCATION(a.FINANCE_NO) = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+
							         	" WHERE UPPER(NVL("+m_schema_name+".AF_CO_GET_DOCREF_LOCATION(a.FINANCE_NO),' ')) LIKE UPPER('"+vector.elementAt(2)+"%')   "+
										" AND  a.FINANCE_NO = b.FINANCE_NO  "+
										" AND  A.REPORT_STATUS IN('GEN', 'APPROVED') "+//Added by Minal on 19-06-2015 for #17087
										 " ) Q "+
										 " where  UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" "+
											"  ORDER BY P.NO  ";
		
		
		
		
		m_help_TXT_confirmation_rpt_gen_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME, P.APPLICATION_STATUS "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME, Q.APPLICATION_STATUS "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME, A.APPLICATION_STATUS APPLICATION_STATUS "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			//" WHERE A.APPLICATION_STATUS NOT IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI') "+ 
			" WHERE A.APPLICATION_STATUS NOT IN ('TERMINATED','TERMI','NORM_TERMI') "+ 
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" AND B.ACTIVE_STATUS = 'Y' "+ 
			" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CONFIRMATION_REPORT WHERE FINANCE_NO=A.FINANCE_NO)    "+
			//" AND A.BRANCH_CODE = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+ // commented by udara 11-05-2015
			" AND UPPER(A.BRANCH_CODE) LIKE UPPER('"+vector.elementAt(2)+"%')   "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			//" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_confirmation_rpt_regenerate_sql =
				" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.APPLICATION_STATUS "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.APPLICATION_STATUS  "+
								 " FROM  "+
							        " ( "+
							            " select NVL(a.FINANCE_NO,'-') FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)   APPLICATION_NO, "+
										"  B.APPLICATION_STATUS APPLICATION_STATUS "+
							            " FROM   "+m_schema_name+".AF_CONFIRMATION_REPORT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
										//" WHERE "+m_schema_name+".AF_CO_GET_DOCREF_LOCATION(a.FINANCE_NO) = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+
							         	//" WHERE UPPER(NVL("+m_schema_name+".AF_CO_GET_DOCREF_LOCATION(a.FINANCE_NO),' ')) LIKE UPPER('"+vector.elementAt(2)+"%')   "+
										" WHERE UPPER(B.BRANCH_CODE) LIKE UPPER('"+vector.elementAt(2)+"%')   "+
										" AND  a.FINANCE_NO = b.FINANCE_NO  "+
										//" AND  A.REPORT_STATUS <> 'APPROVED'  "+
										//" AND  A.REPORT_STATUS IN('GEN', 'APPROVED') "+ // commented by udara 13-10-2015 //Added by Minal on 19-06-2015 for #17087
										" AND  A.REPORT_STATUS IN('GEN') "+ // added by udara 13-10-2015
										" AND  B.APPLICATION_STATUS <> 'ACTIVATED'  "+
										 " ) Q "+
										 " where  UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" "+
											"  ORDER BY P.NO  ";
		
		
		
		
		
		/*
		m_help_TXT_confirmation_rpt_gen_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.NAME "+
			" FROM "+
			" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE A.APPLICATION_STATUS NOT IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI') "+ 
			" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CONFIRMATION_REPORT)    "+
			" AND A.BRANCH_CODE = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		*/
		
		
		m_help_TXT_confirmation_rpt_appr_sql =
				" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO  "+
								 " FROM  "+
							        " ( "+
							            " select NVL(a.FINANCE_NO,'-') FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)   APPLICATION_NO "+
							            " FROM   "+m_schema_name+".AF_CONFIRMATION_REPORT A  "+
										//" WHERE "+m_schema_name+".AF_CO_GET_DOCREF_LOCATION(a.FINANCE_NO) = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+
							         	" WHERE REPORT_STATUS = 'GEN'   "+
										
										" ) Q "+
										 " where  UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" ";
		
		
		
		// end udara 12-03-2015
		
		
		// added by udara 24-07-2018
			m_help_insurance_app_sql =
				" SELECT P.NO,P.FINANCE_NO,P.PAYEE_NAME "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.PAYEE_NAME  "+
								 " FROM  "+
							        " ( "+
							            " select A.FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM) PAYEE_NAME "+
							            " FROM   "+m_schema_name+".AF_INIT_INSURANCE_DATA A  "+										
										" ) Q "+
										 " where  UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" ";
		
		
		// added by udara 31-08-2015
		m_help_TXT_confirmation_rpt_view_sql =
				" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO  "+
								 " FROM  "+
							        " ( "+
							            " select NVL(a.FINANCE_NO,'-') FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)   APPLICATION_NO, "+
										" VEHICAL_NO_HIRER "+
							            " FROM   "+m_schema_name+".AF_CONFIRMATION_REPORT A  "+
										" ) Q "+
										 //" where  UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+ // commented by udara 13-03-2019
											" where  Q.FINANCE_NO like UPPER('%"+vector.elementAt(0)+"%')  "+ // added by udara 13-03-2019
											" OR    Q.VEHICAL_NO_HIRER LIKE '%"+vector.elementAt(0)+"%' "+ // added by udara 13-03-2019
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" ";
		// end by udara 31-08-2014
		
		
		
		// ADD BY AMILA 01-08-2017
		m_help_Finance_No_sql =
				" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO "+
					 " FROM  "+
					 " ( "+
					       " SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO  "+
								 " FROM  "+
							        " ( "+
							            " select NVL(a.FINANCE_NO,'-') FINANCE_NO, "+
							            " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)   APPLICATION_NO "+
							            " FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
										" ) Q "+
										 " where  (UPPER(Q.FINANCE_NO) like UPPER('%"+vector.elementAt(0)+"%')  "+
										 " OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
										 " ORDER BY Q.FINANCE_NO DESC   ) P  "+
										 " WHERE P.NO>="+ s1+" AND P.NO<="+s2+" ";
		// END BY AMILA 01-08-20174
		
		
		
		//====================End by Dineth on 2008-08-26
		
		m_help_TXT_BulkPrintFinanceSql_sql2 =
			" SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM      " +
			" (SELECT   ROWNUM NO,NVL(Q.FINANCE_NO,'-') FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT DISTINCT(A.FINANCE_NO) FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
			" "+m_schema_name+".AF_CO_PRO_INVOICE C "+ //ADDED BY MADHAWA 2012-02-10
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" AND C.FINANCE_NO=A.FINANCE_NO  "+ //ADDED BY MADHAWA 2012-02-10
			" AND C.BALANCE_TO_BE_RECEIVED > 0 "+ //ADDED BY MADHAWA 2012-02-10
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		//ADD BY A/S FOR LEGAL LETTER
		m_help_TXT_BulkPrintFinanceSql_1_sql2 =
			" SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM      " +
			" (SELECT   ROWNUM NO,NVL(Q.FINANCE_NO,'-') FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT DISTINCT(A.FINANCE_NO) FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
			" "+m_schema_name+".AF_CO_PRO_INVOICE C "+ //ADDED BY MADHAWA 2012-02-10
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" AND C.FINANCE_NO=A.FINANCE_NO  "+ //ADDED BY MADHAWA 2012-02-10
			" AND C.BALANCE_TO_BE_RECEIVED > 0 "+ //ADDED BY MADHAWA 2012-02-10
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  ((Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		//=======================added by madhawa 2012-01-30 
		m_help_TXT_BulkPrintFinanceSql_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,NVL(Q.FINANCE_NO,'-') FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT DISTINCT(A.FINANCE_NO) FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))  "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			//" AND	UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		// ADD BY A/S FOR LEGAL LETTER
		 m_help_TXT_BulkPrintFinanceSql_1_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,NVL(Q.FINANCE_NO,'-') FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME "+
			" FROM "+
			" (SELECT DISTINCT(A.FINANCE_NO) FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
			" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_STATUS=('ACTIVATED') "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
			" ORDER BY A.FINANCE_NO DESC) Q"+
			" WHERE  ((Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		(Q.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%'))  "+
			//" OR 		UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			//" AND	UPPER(CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" ORDER BY Q.FINANCE_NO DESC   ) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_BULKPRINT_TEAM_ID_sql=
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
			" WHERE UPPER(TEAM_ID) LIKE UPPER('"+vector.elementAt(0)+"%') AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		m_help_txt_bulk_print_sub_team_sql=
			
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
			"  AND C.TEAM_ID LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			"  AND B.SUB_TEAM_ID LIKE UPPER('%"+vector.elementAt(1)+"%')  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_collection_officer=
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
			" AND ( UPPER(USER_ID) LIKE UPPER('%"+vector.elementAt(2)+"%')  "+
			" OR   UPPER("+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID)) LIKE UPPER('%"+vector.elementAt(2)+"%') ) "+
			" AND  UPPER(A.TEAM_ID) LIKE UPPER('%"+vector.elementAt(0)+"')  "+
			" AND  UPPER(C.SUB_TEAM_ID)LIKE  UPPER('%"+vector.elementAt(1)+"')  "+
			" ORDER BY A.TEAM_ID ,C.SUB_TEAM_ID,USER_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		//======================end added by madhawa 2012-01-30
		//====================Added by Dineth on 2008-08-29
		
		m_help_TXT_TerminationSql_sql=" SELECT P.NO,P.TERMINATION_NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.TERMINATION_TYPE "+
			" FROM "+
			"	(SELECT ROWNUM NO,B.TERMINATION_NO,B.FINANCE_NO,B.APPLICATION_NO,B.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME,B.TERMINATION_TYPE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+
			" "+m_schema_name+".AF_CR_PRO_TERMINATION B"+
			" WHERE UPPER(B.ACTIVE_STATUS) <> UPPER('CANCEL') AND "+
			" A.FINANCE_NO=B.FINANCE_NO(+) "+
			" ORDER BY B.TERMINATION_NO DESC) P "+
			" WHERE "+
			" UPPER(P.TERMINATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			//" AND UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND P.NO>= "+s1+" AND P.NO<= "+s2+" ";
		
		m_help_TXT_TerminationSql_sql2=" SELECT P.NO,P.TERMINATION_NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.CLIENT_NAME,P.TERMINATION_TYPE "+
			" FROM "+
			"	(SELECT ROWNUM NO,B.TERMINATION_NO,B.FINANCE_NO,B.APPLICATION_NO,B.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME,B.TERMINATION_TYPE "+
			//" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+
			
			" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B"+
			
			" WHERE UPPER(B.ACTIVE_STATUS) <> UPPER('CANCEL') AND "+
			" (UPPER(B.TERMINATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR UPPER(B.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			//" A.FINANCE_NO=B.FINANCE_NO(+) "+
			" ORDER BY B.TERMINATION_NO ASC) P "+
			" WHERE "+
			" P.NO>= "+s1+" AND P.NO<= "+s2+" ";
		//====================End by Dineth on 2008-08-29
		FinanceSql =" SELECT P.NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.APPLICATION_NO APPLICATION_NO,A.FINANCE_NO FINANCE_NO,A.CLIENT_CODE CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT B  "+
			" WHERE (UPPER(A.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR UPPER("+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE))  LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND B.STATUS='N' "+
			" AND A.APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY A.FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		FinanceSql_finance =" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+vector.elementAt(0)+"%')) "+ 
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		//ADDED MILINDA  2014-02-20
		FinanceSql_finance_CHANGE =" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,BRANCH_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME,BRANCH_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME,BRANCH_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+vector.elementAt(0)+"%')) "+ 
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		//END
		FinanceSql_period =" SELECT P.NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE,CLIENT_NAME "+
			" FROM "+
			" (SELECT APPLICATION_NO,NVL(FINANCE_NO,'-') FINANCE_NO,CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(CLIENT_CODE) CLIENT_NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+vector.elementAt(0)+"%')) "+ 
			" AND APPLICATION_STATUS IN ('ACTIVATED','VERIFY1','VERIFY2','VERIFY-M','VERIFYL','V-APP')"+
			" ORDER BY FINANCE_NO DESC)) P "+		
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_FinanceSql_BC =//Added By Sandun on 22-12-2008
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.FULL_NAME,P.ADDRESS1,P.ADDRESS2,P.CITY "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,FULL_NAME,ADDRESS1,ADDRESS2,CITY "+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO ,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.FULL_NAME,'-') FULL_NAME,NVL(B.ADDRESS1,'-') ADDRESS1,NVL(B.ADDRESS2,'-') ADDRESS2, "+
			" INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),'-')) CITY "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.CLIENT_CODE= B.CLIENT_CODE "+
			" AND A.FINANCE_NO IS NOT NULL "+
			" AND B.ACTIVE_STATUS = 'Y' "+
			" AND A.APPLICATION_STATUS IN ('ACTIVATED','TERMI','TERMINATED','NORM_TERMI','LEGAL') "+
			" AND(UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(FINANCE_NO) LIKE UPPER('"+vector.elementAt(0)+"%') ) AND "+
			" UPPER(A.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%') "+
			" ORDER BY A.FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		m_help_TXT_FinanceSql_BalReceive =//Sandun on 30-02-2009
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,NAME "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE,UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND 		UPPER(CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		
		Ret_Object = s;
		return Ret_Object;
	}
	
	public Object Ret_Object;
	public String ret_str;
	LAKDL_AF_CO_conn_methods m_sn_methods;
	String m_schema_name;
	
	public String ClientSql;
	public String ClientSql_Header;
	public String ClientSql1;
	public String ClientSql1_Header;
	// Added by Dineth on 2008-08-25
	public String ClientSql2;
	public String ClientSql2_Header;
	
	public String m_help_collection_officer;
	public String m_help_collection_officer_Header;
	public String InquirySql;
	public String InquirySql_Header;
	public String MKOfficerSql;
	public String MKOfficerSql_Header;
	public String MKSuperSql;
	public String MKSuperSql_Header;
	public String MKTeamSql;
	public String MKTeamSql_Header;
	public String TrnSubSql;
	public String TrnSubSql_Header;
	public String ModelSql;
	public String ModelSql_Header;
	public String VendorSql;
	public String VendorSql_Header;
	public String PriceSql;
	public String PriceSql_Header;
	public String SubModelSql;
	public String SubModelSql_Header;
	public String IntBaseSql;
	public String IntBaseSql_Header;
	public String m_help_TXT_DISTRICT_CODE_sql;
	public String m_help_TXT_DISTRICT_CODE_sql_Header;
	public String m_help_TXT_DISTRICT_CODE_sql1;
	public String m_help_TXT_DISTRICT_CODE_sql1_Header;
	public String m_help_TXT_APPLICATION_NO;
	public String m_help_TXT_APPLICATION_NO_Header;
	public String m_help_TXT_CLIENT_CODE;
	public String m_help_TXT_CLIENT_CODE_Header;
	public String m_help_TXT_INQUARY_NO;
	public String m_help_TXT_INQUARY_NO_Header;
	public String m_help_TXT_INQUARY_NO_ALL;
	public String m_help_TXT_INQUARY_NO_ALL_Header;
	public String m_help_TXT_MAKE;
	public String m_help_TXT_MAKE_Header;
	public String m_help_TXT_INVOICE_PURCHASE;
	public String m_help_TXT_INVOICE_PURCHASE_Header;
	public String m_help_TXT_PURCHASE_ORDER_NO;
	public String m_help_TXT_PURCHASE_ORDER_NO_Header;
	public String m_help_TXT_MODEL_CODE_sql;
	public String m_help_TXT_MODEL_CODE_sql_Header;
	public String m_help_TXT_MODEL_CODE_inv_sql;
	public String m_help_TXT_MODEL_CODE_inv_sql_Header;
	public String PriceSql_invoice;
	public String PriceSql_invoice_Header;
	public String m_help_TXT_SUB_MODEL_sql;
	public String m_help_TXT_SUB_MODEL_sql_Header;
	public String m_help_TXT_SUB_M_CODE_sql;
	public String m_help_TXT_SUB_M_CODE_sql_Header;
	public String m_help_TXT_CITY_CODE_sql;
	public String m_help_TXT_CITY_CODE_sql_Header;
	public String m_help_TXT_APPLICATION_NO_2;
	public String m_help_TXT_APPLICATION_NO_2_Header;
	public String m_help_TXT_SUPPLIER_sql;
	public String m_help_TXT_SUPPLIER_sql_Header;
	public String m_help_SUPPLIER_PURCHASE_ORDER;
	public String m_help_SUPPLIER_PURCHASE_ORDER_Header;
	public String m_help_TXT_INVOICE_NO_sql;
	public String m_help_TXT_INVOICE_NO_sql_Header;
	public String m_help_TXT_ASSET_ID_sql;
	public String m_help_TXT_ASSET_ID_sql_Header;
	public String m_help_TXT_PRICING_NO_sql;
	public String m_help_TXT_PRICING_NO_sql_Header;
	public String m_help_TXT_QUOTATION_NO_sql;
	public String m_help_TXT_QUOTATION_NO_sql_Header;
	public String m_help_TXT_QUOTATION_NO_sql1;
	public String m_help_TXT_QUOTATION_NO_sql1_Header;
	public String m_help_TXT_QUOTATION_NO_sql2;
	public String m_help_TXT_QUOTATION_NO_sql2_Header;
	public String m_help_TXT_CONDITION_OF_ASSET_sql;
	public String m_help_TXT_CONDITION_OF_ASSET_sql_Header;
	public String m_help_TXT_MAKE_CODE_sql;
	public String m_help_TXT_MAKE_CODE_sql_Header;
	public String m_help_TXT_MODEL_CODE1_sql;
	public String m_help_TXT_MODEL_CODE1_sql_Header;
	public String m_help_TXT_MODEL_CODE2_sql;
	public String m_help_TXT_MODEL_CODE2_sql_Header;
	public String m_help_TXT_INQUIRY_NO_sql;
	public String m_help_TXT_INQUIRY_NO_sql_Header;
	public String m_help_TXT_SCREEN_NAME_sql;
	public String m_help_TXT_SCREEN_NAME_sql_Header;
	public String m_help_TXT_PURCHASE_ORDER_NO_sql;
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_Header;
	public String m_help_TXT_APP_NO_sql;
	public String m_help_TXT_APP_NO_sql_Header;
	public String m_help_TXT_VENDER_CODE_sql;
	public String m_help_TXT_VENDER_CODE_sql_Header;
	public String m_help_TXT_ASSET_ID_sql2;
	public String m_help_TXT_ASSET_ID_sql2_Header;
	public String m_help_TXT_APP_NO_sql_1;
	public String m_help_TXT_APP_NO_sql_1_Header;
	public String m_help_TXT_VENDOR_CODE_sql;
	public String m_help_TXT_VENDOR_CODE_sql_Header;
	public String m_help_TXT_LOCATION_CODE_sql;
	public String m_help_TXT_LOCATION_CODE_sql_Header;
	public String new_m_help_TXT_LOCATION_CODE_sql;
	public String new_m_help_TXT_LOCATION_CODE_sql_Header;
	public String m_help_TXT_MAS_VENDOR_LOCATION_sql;
	public String m_help_TXT_MAS_VENDOR_LOCATION_sql_Header;
	public String m_help_TXT_VALUATION_NO1_sql;
	public String m_help_TXT_VALUATION_NO1_sql_Header;
	public String m_help_TXT_VALUATION_NO2_sql;
	public String m_help_TXT_VALUATION_NO2_sql_Header;
	
	public String m_help_TXT_VALUATION_NO_sql;
	public String m_help_TXT_VALUATION_NO_sql_Header;
	
	public String m_help_TXT_VALUATION_NO_sql_new;
	public String m_help_TXT_VALUATION_NO_sql_new_Header;
	
	public String BrokerSql ;
	public String BrokerSql_Header;
	
	public String m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql;
	public String m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql_Header;
	
	public String m_help_TXT_ISSUER_CODE_sql;
	public String m_help_TXT_ISSUER_CODE_sql_Header;
	
	public String	m_help_TXT_FinanceSql_new;
	public String	m_help_TXT_FinanceSql_new_Header; 
	
	public String m_help_TXT_INVOICE_NO_app_sql;
	public String m_help_TXT_INVOICE_NO_app_sql_Header; 
	
	public String m_help_TXT_VALUER_CODE_sql;
	public String m_help_TXT_VALUER_CODE_sql_Header;
	//public String M_APPLICATION_PROCESS_APPLICATION_HELP;
	//	public String M_APPLICATION_PROCESS_APPLICATION_HELP_Header;
	
	public String	m_help_TXT_FinanceSql_new1;
	public String	m_help_TXT_FinanceSql_new1_Header; 
	//Added by Dineth on 2009-01-15
	public String	m_help_TXT_FinanceSql_new4;
	public String	m_help_TXT_FinanceSql_new4_Header; 
	//end by Dineth on 2009-01-15
	
	public String	m_help_TXT_FinanceSql_new2;
	public String	m_help_TXT_FinanceSql_new2_Header; 
	
	public String	m_help_TXT_FinanceSql_new3;
	public String	m_help_TXT_FinanceSql_new3_Header; 
	
	public String	m_help_TXT_SO_NO_sql;
	public String	m_help_TXT_SO_NO_sql_Header; 
	
	
	public String m_help_TXT_INVOICE_NO_app_sql1;
	public String m_help_TXT_INVOICE_NO_app_sql1_Header; 
	
	public String m_help_TXT_APPLICATION_NO_1;
	public String m_help_TXT_APPLICATION_NO_1_Header; 
	
	public String m_help_TXT_DISPUTES_VALUATION_NO_sql_new;
	public String m_help_TXT_DISPUTES_VALUATION_NO_sql_new_Header;
	
	public String m_help_TXT_DISPUTES_VALUATION_NO_sql_edit;
	public String m_help_TXT_DISPUTES_VALUATION_NO_sql_edit_Header;
	
	
	public String m_help_TXT_INQUARY_NO_sql_report;
	public String m_help_TXT_INQUARY_NO_sql_report_Header;
	
	
	public String m_help_TXT_REPOSSESSION_NO_sql_report;
	public String m_help_TXT_REPOSSESSION_NO_sql_report_Header;
	
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_report;
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_report_Header;
	
	public String PriceSql_3;
	public String PriceSql_3_Header;
	
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_1;
	public String m_help_TXT_PURCHASE_ORDER_NO_sql_1_Header;
	
	public String m_help_TXT_CREDIT_NO_sql_report;
	public String m_help_TXT_CREDIT_NO_sql_report_Header;
	
	public String m_help_TXT_ACC_sql="";
	public String m_help_TXT_ACC_sql_Header="Credit Process - Account Details ";
	
	public String ReceiptSql1="";
	public String ReceiptSql1_Header="Collection Process - Receipt Details ";
	
	public String m_help_Collection_process_Deposit_code        ="";
	public String m_help_Collection_process_Deposit_code_Header ="Deposit Number Help";
	
	public String m_help_TXT_PAYMENT_NO        ="";
	public String m_help_TXT_PAYMENT_NO_Header ="Payment Number Help";
	
	public String m_help_TXT_SUS_REF_NO        ="";
	public String m_help_TXT_SUS_REF_NO_Header ="Sus Ref No Help";
	
	public String m_help_TXT_DIVISION_CODE_sql;
	public String m_help_TXT_DIVISION_CODE_sql_Header;
	
	public String bulkprintClient_Sql_Header;
	public String bulkprintClient_Sql;
	
	public String m_help_TXT_TRAN_CODE_sql;
	public String m_help_TXT_TRAN_CODE_sql_Header;
	
	public String m_help_TXT_FinanceSql_sql;
	public String m_help_TXT_FinanceSql_sql_Header;
	
	public String m_help_TXT_upload_doc;
	public String m_help_TXT_upload_doc_Header;
	
	public String m_help_TXT_FinanceSql2_sql; // added by udara on 20-11-2013
	public String m_help_TXT_FinanceSql2_sql_Header; // added by udara on 20-11-2013

	public String m_help_TXT_FinanceSql2_sql_new; 			// ADDED BY SAJITH MENDIS ON 22-04-2014
	public String m_help_TXT_FinanceSql2_sql_new_Header; 	// ADDED BY SAJITH MENDIS ON 22-04-2014
	
	public String m_help_TXT_FinanceSql2_VIR_sql_new; // added by udara 04-09-2014
	public String m_help_TXT_FinanceSql2_VIR_sql_new_Header; // added by udara 04-09-2014

	public String m_help_TXT_BulkPrintFinanceSql_sql;
	public String m_help_TXT_BulkPrintFinanceSql_sql_Header;
	
	//ADD BY A/S ON FOR LEGAL LETTER
	public String m_help_TXT_BulkPrintFinanceSql_1_sql;
	public String m_help_TXT_BulkPrintFinanceSql_1_sql_Header;
	
	public String m_help_TXT_FinanceSql_sql2;
	public String m_help_TXT_FinanceSql_sql2_Header;
	
	// added by udara 31-10-2014
	public String m_help_TXT_FinanceSql_sanction_sql;
	public String m_help_TXT_FinanceSql_sanction_sql_Header;
	
	
	public String m_help_TXT_confirmation_rpt_appr_sql;
	public String m_help_TXT_confirmation_rpt_appr_sql_Header;
	
	// added by udara 24-07-2018
	public String m_help_insurance_app_sql;
	public String m_help_insurance_app_sql_Header;
	// end by udara 24-07-2018
	
	// added by udara 31-08-2015
	public String m_help_TXT_confirmation_rpt_view_sql;
	public String m_help_TXT_confirmation_rpt_view_sql_Header;
	// end by udara 31-08-2015
	
	// ADD BY AS ON 01-08-2017
	public String m_help_Finance_No_sql;
	public String m_help_Finance_No_sql_Header;
	// END BY AS ON 01-08-2017
	
	public String m_help_TXT_confirmation_rpt_edit_sql;
	public String m_help_TXT_confirmation_rpt_edit_sql_Header;
	
	public String m_help_TXT_confirmation_rpt_gen_sql;
	public String m_help_TXT_confirmation_rpt_gen_sql_Header;
	
	public String m_help_TXT_confirmation_rpt_regenerate_sql;
	public String m_help_TXT_confirmation_rpt_regenerate_sql_Header;
	
	
	public String m_help_TXT_FinanceSql_sanction_sql2;
	public String m_help_TXT_FinanceSql_sanction_sql2_Header;
	// end by udara 31-10-2014
	
	
	public String m_help_TXT_BulkPrintFinanceSql_sql2;
	public String m_help_TXT_BulkPrintFinanceSql_sql2_Header;
	
	//ADD BY A/S FOR LEGAL LETTER
	public String m_help_TXT_BulkPrintFinanceSql_1_sql2;
	public String m_help_TXT_BulkPrintFinanceSql_1_sql2_Header;
	
	public String m_help_TXT_BULKPRINT_TEAM_ID_sql;
	public String m_help_TXT_BULKPRINT_TEAM_ID_sql_Header;
	
	public String m_help_txt_bulk_print_sub_team_sql_Header;
	public String m_help_txt_bulk_print_sub_team_sql;
	// Added by Dineth on 2008-08-29
	public String m_help_TXT_TerminationSql_sql;
	public String m_help_TXT_TerminationSql_sql_Header;
	
	public String m_help_TXT_TerminationSql_sql2;
	public String m_help_TXT_TerminationSql_sql2_Header;
	// End by Dineth on 2008-08-29
	
	public String m_help_TXT_TEAM_ID_sql;
	public String m_help_TXT_TEAM_ID_sql_Header="Team ID";
	
	public String FinanceSql;
	public String FinanceSql_Header;
	
	public String FinanceSql_finance;
	public String FinanceSql_finance_Header;
	
	
	public String FinanceSql_finance_CHANGE;
	public String FinanceSql_finance_CHANGE_Header;
	
	
	public String FinanceSql_period;
	public String FinanceSql_period_Header;
	
	public String ClientSql_Balance_Confim;
	public String ClientSql_Balance_Confim_Header;
	
	public String m_help_TXT_FinanceSql_BC;
	public String m_help_TXT_FinanceSql_BC_Header;
	
	public String m_help_TXT_FinanceSql_BalReceive;
	public String m_help_TXT_FinanceSql_BalReceive_Header;
	
	
}
