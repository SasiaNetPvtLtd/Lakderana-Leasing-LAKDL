// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LAKDL_AF_MK_help_select.java

import java.util.Vector;

public class LAKDL_AF_MK_help_select
{
	
	public LAKDL_AF_MK_help_select()
	{
		Ret_Object = new Object();
		m_sn_methods = new LAKDL_AF_CO_conn_methods();
		m_schema_name = m_sn_methods.schema_name.trim();
		
		///	M_APPLICATION_PROCESS_APPLICATION_HELP="";
		///	M_APPLICATION_PROCESS_APPLICATION_HELP_Header="Application Processing - Application Number Help";
		m_help_TXT_USER_ID_sql="";
		m_help_TXT_USER_ID_sql_Header="Marketing - Authorised Signatory";
		
		
		m_help_TXT_MK_OFFICER_sql="";
		m_help_TXT_MK_OFFICER_sql_Header="Marketing - Officer";
		
		m_help_TXT_MK_OFFICER_sql_new="";
		m_help_TXT_MK_OFFICER_sql_new_Header="Marketing - Officer";
		
		ClientSql = " ";
		ClientSql_Header = "Marketing - Client Help ";
		PAYSql   = " ";
		PAYSql_Header = "Marketing - Payee";
		
		ClientSql1 = " ";
		ClientSql1_Header = "Marketing - Client Help ";
		InquirySql = " ";
		InquirySql_Header = "Marketing - Inquiry Help ";
		BrokerSql = " ";
		BrokerSql_Header = "Marketing - Broker Help ";
		MKOfficerSql = " ";
		MKOfficerSql_Header = "Marketing - Marketing Officer Help ";
		MKOfficerSqlNew = " ";
		MKOfficerSqlNew_Header = "Marketing - Marketing Officer Help ";//Added By Sandun on 25-08-2008
		
		// added by udara 09-10-2019
		PledgeContractSqlNew = " ";
		PledgeContractSqlNew_Header = "Marketing - Marketing Officer Help ";
		// end by udara 09-10-2019
		
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
		BranchSql  = " ";
		BranchSql_Header = "Help ";
		m_help_TXT_DISTRICT_CODE_sql = "Marketing - District Help ";
		m_help_TXT_DISTRICT_CODE_sql_Header = "Marketing - Help ";
		m_help_TXT_DISTRICT_CODE_sql1 = "Marketing - District Help ";
		m_help_TXT_DISTRICT_CODE_sql1_Header = "Marketing - District Help ";
		
		//Added by Dineth on 2008-11-03
		m_help_TXT_CHK_NO_sql_Header = "Cheque No - Help";
		m_help_TXT_CHK_NO_sql = " ";
		
		//End by Dineth on 2008-11-03
		
		m_help_TXT_USER_ID_new_sql = "";
		m_help_TXT_USER_ID_new_sql_Header = "Quotation - Marketing Officer Help";
		
		m_help_TXT_APPLICATION_NO_MOD ="";
		m_help_TXT_APPLICATION_NO_MOD_Header ="Application - Credit Officer";//milinda
		
		m_help_TXT_APPLICATION_NO = " ";
		m_help_TXT_APPLICATION_NO_Header = "Marketing - Application Help";
		
		
		m_help_TXT_APPLICATION_NO_new = " ";
		m_help_TXT_APPLICATION_NO_new_Header = "Application Proccess - Application Help";
		
		
		m_help_TXT_CLIENT_CODE = " ";
		m_help_TXT_CLIENT_CODE_Header = "Marketing - Client Help";
		
		
		m_help_TXT_CLIENT_CODE_GURAN = " ";
		m_help_TXT_CLIENT_CODE_GURAN_Header = "Marketing - Client Help";
		
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
		
		m_help_vendor_proforma_invoice       ="";
		m_help_vendor_proforma_invoice_Header="Marketing - Vendor help";//added by nuwan de silva on 16-11-0
		
		m_help_TXT_LOCATION_CODE_sql = "";
		m_help_TXT_LOCATION_CODE_sql_Header = "Marketing - Location Help";
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
		
		//Added by Dineth on 20-03-2009
		m_help_TXT_FinanceSql_new3           = " ";
		m_help_TXT_FinanceSql_new3_Header    = "Credit - Finance Help "; 
		
		
		
		//End by Dineth on 20-03-2009
		
		m_help_TXT_FinanceSql_new2           = " ";
		m_help_TXT_FinanceSql_new2_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_SO_NO_sql= " ";
		m_help_TXT_SO_NO_sql_Header= "Credit - Standing Order No Help "; 
		
		m_help_TXT_INVOICE_NO_app_sql1=" ";
		m_help_TXT_INVOICE_NO_app_sql1_Header= "Credit - Vehicle No Help "; 
		
		m_help_TXT_APPLICATION_NO_1="";
		m_help_TXT_APPLICATION_NO_1_Header= "Credit -Application No Help "; 
		
		m_help_TXT_FINANCE_NO_1="";
		m_help_TXT_FINANCE_NO_1_Header= "Credit -Finance No Help "; 
		
		
		
		m_help_application_no_change_pro_invoice="";
		m_help_application_no_change_pro_invoice_Header="Credit -Application No Help "; 
		
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
		
		m_help_TXT_RECIEPT_NO_sql_report="";
		m_help_TXT_RECIEPT_NO_sql_report_Header="Reciept No Help";
		
		m_help_TXT_TEMP_REC_NO_sql="";
		m_help_TXT_TEMP_REC_NO_sql_Header="Temprary Reciept No Help";
		
		m_help_TXT_ADVER_OFFERS_NO_sql=""; 
		m_help_TXT_ADVER_OFFERS_NO_sql_Header="Advertiesment Offers No Help";
		
		m_help_TXT_OD_INTEREST_NO_sql="";
		m_help_TXT_OD_INTEREST_NO_sql_Header="O D Interest No Help";
		
		m_help_TXT_PAYMENT_NO_sql="";
		m_help_TXT_PAYMENT_NO_sql_Header="Payment Settlement Help";
		
		m_help_TXT_INVOICE_NO_sql_report="";
		m_help_TXT_INVOICE_NO_sql_report_Header="Invoice No Help";
		
		m_help_TXT_POD_NO_sql_report="";
		m_help_TXT_POD_NO_sql_report_Header="Invoice No Help";
		
		m_help_TXT_FINANCE_NO_sql_report="";
		m_help_TXT_FINANCE_NO_sql_report_Header="Invoice No Help";
		
		m_help_TXT_LEASE_NO_sql_report="";
		m_help_TXT_LEASE_NO_sql_report_Header = "Lease No Help";
		
		
		FinanceSql           = "";
		FinanceSql_Header    = "Credit - Finance Help "; 
		
		m_help_TXT_APPLICATION_NO_3 = "";
		m_help_TXT_APPLICATION_NO_3_Header = " Application Help";
		
		
		m_help_TXT_APPLICATION_NOCK="";
		m_help_TXT_APPLICATION_NOCK_Header=" Application Help";
		
		CitySql ="";
		CitySql_Header = "City Help";
		
		m_help_TXT_INVOICE_NO_NEW_sql="";
		m_help_TXT_INVOICE_NO_NEW_sql_Header="Pro Invoice Help";
		
		PriceSql_invoice_new = "";
		PriceSql_invoice_new_Header = "Marketing - Pricing Help";
		
		m_help_txt_sub_team_sql = "";
		m_help_txt_sub_team_sql_Header = "Team Help";
		
		m_help_team_user_id_sql_new ="";
		m_help_team_user_id_sql_new_Header ="User Help";
		
		m_help_TXT_PROVINCE_sql=" ";
		m_help_TXT_PROVINCE_sql_Header= "Application Process - Province Help "; //add by waruna 2012-04-25
		
		
		// Added by Thamali Jayatunga on 2010.02.25
		m_help_TXT_VEHICLE_NO_sql=" ";
		m_help_TXT_VEHICLE_NO_sql_Header= "Application Process - Vehicle No Help "; 
		
		m_help_TXT_MORTGAGE_NO_sql=" ";
		m_help_TXT_MORTGAGE_NO_sql_Header= "Application Process - Mortgage No Help "; 
		
		ClientSql_Receipt_terminate ="";
		ClientSql_Receipt_terminate_Header ="Collection Help";
		
		m_help_TXT_FD_NO_sql=" ";
		m_help_TXT_FD_NO_sql_Header= "Application Process - Account No Help "; 
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
		
		//Modified by Mahela on 17-05-2007
		//Purpose : To filter Customer Name by Selected Customer Type
		ClientSql = "SELECT P.NO, P.CLIENT_CODE Client, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE FROM (SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE FROM (SELECT CLIENT_CODE,NVL(DECODE(CLIENT_TYPE,'I', FIRST_NAME,'C',FULL_NAME),'-') FIRST_NAME, NVL(SURNAME,'-') SURNAME ,NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,'C',BUSINESS_CERTIFICATE_NO),'-') NIC_NO ,NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1,NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2,NVL(TEL_NO,'-') TEL_NO,NVL(MOBILE_NO,'-') MOBILE_NO,NVL(EMAIL,'-') EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE \t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "\t WHERE (UPPER(FULL_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ADDRESS1)   LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(CITY_CODE)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(MOBILE_NO)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(TEL_NO)     LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)      LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(NIC_NO)     LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%'))  AND CLIENT_CATEGORY='"+vector.elementAt(1)+"'   " + " ORDER BY FULL_NAME )) P " + "WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		// added by SH on 25-03-2008
		PAYSql=	
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
			" WHERE (UPPER(PAYEE_CODE)    LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR   UPPER(PAYEE_NAME)    LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" AND   UPPER(SUB_TYPE_CODE) = UPPER('"+vector.elementAt(1)+"') "+
			" AND ACTIVE_STATUS = 'Y'   "+ // added by udara 23-02-2016
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		InquirySql = "SELECT P.NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO,ADDRESS ADDRESS1 , ADDRESS2, CITY_CODE, LEGAL_ENTITY,LEAD_SOURCE_NAME,ID_NO, MK_OFFICER,INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,INTRODUCER,EMAIL, TEAM,FAX_NO,MK_SUPERVISOR, CONTACT_PERSON,SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE,STATUS,INQUIRY_STATUS FROM (SELECT ROWNUM NO, INQUIRY_CODE, CLIENT_NAME, TEL_NO, MOBILE_NO, FAX_NO,\t       ADDRESS, CITY_CODE, LEGAL_ENTITY, STATUS,\t       INITIATION_TYPE, CLIENT_CATEGORY, LEAD_SOURCE_CATEGORY,\t       LEAD_SOURCE_NAME, INTRODUCER, ID_NO, INQUIRY_STATUS,        ENT_USER,ADDRESS2, EMAIL, TEAM,        MK_OFFICER, MK_SUPERVISOR, CONTACT_PERSON,\t       SUB_PRODUCT_CODE, TRANSACTION_SUB_TYPE FROM (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t      UPPER(ADDRESS)      LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t      UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(MOBILE_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )) P " + "WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " "; //modified by nuwan de silva on 07-09-07 remove the % from id and mob no 
		
		
		m_help_txt_sub_team_sql=
			
			" SELECT L.NO ,L.TEAM_HEAD,L.SUB_TEAM_DESC,NVL(L.TEAM_ID,'N/A') AS TEAM_ID ,L.TEAM_DESC  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEAM_HEAD,P.SUB_TEAM_DESC,P.TEAM_ID ,P.TEAM_DESC"+
			" FROM( "+ 
			"  SELECT "+
			"  C.TEAM_HEAD, "+
			"  C.TEAM_ID, "+
			"  B.SUB_TEAM_ID, "+
			"  SUB_TEAM_DESC, "+
			"  A.ACTIVE_STATUS ,"+
			"  C.TEAM_DESC "+
			"  FROM "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN  A, "+m_schema_name+".AF_CO_MAS_SUB_TEAMS B , "+m_schema_name+".AF_CO_MAS_TEAMS C "+
			"  WHERE A.ACTIVE_STATUS = 'Y' "+
			"  AND A.SUB_TEAM_ID=B.SUB_TEAM_ID "+
			"  AND A.TEAM_ID=C.TEAM_ID "+	
			"  AND (C.TEAM_ID LIKE UPPER('%"+vector.elementAt(0)+"%')  "+		
			"  OR C.TEAM_HEAD LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		
		
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
			//" 			       UPPER(CONTACT_NO)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+ // commented by udara 11-03-2019
			//" 			       UPPER(MOBILE_NO)   LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+ // commented by udara 11-03-2019
			//" 			       UPPER(ID_NO)       LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+ // commented by udara 11-03-2019
			" 			       CONTACT_NO  LIKE '%" + vector.elementAt(0) + "%' OR "+ // added by udara 11-03-2019
			" 			       MOBILE_NO   LIKE '%" + vector.elementAt(0) + "%' OR "+ // added by udara 11-03-2019
			" 			       ID_NO       LIKE '%" + vector.elementAt(0) + "%' OR "+ // added by udara 11-03-2019
			" 			       UPPER(ADDRESS1)    LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" 			       UPPER(CITY_CODE)   LIKE UPPER('%" + vector.elementAt(0) + "%') ) "+
			" ORDER BY FIRST_NAME,LAST_NAME )) P " + 
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		
		
		m_help_TXT_DISTRICT_CODE_sql = " SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE   FROM   (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE  FROM(  SELECT  DISTRICT_CODE, DISTRICT_DESC,  PROVINCE_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_DISTRICT " + " WHERE DISTRICT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_ASSET_ID_sql = " SELECT L.NO,L.ASSET_ID,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.MODEL_CODE,L.SUB_MODEL_CODE,L.PRICING_NO,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.ADDRESS,L.CITY_CODE,L.PERIOD,L.APPLICATION_NO FROM   (SELECT ROWNUM NO,P.ASSET_ID,P.REG_NO,P.REG_DATE,P.MODEL_CODE,P.SUB_MODEL_CODE,P.PRICING_NO,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.ADDRESS,P.CITY_CODE,P.PERIOD,P.APPLICATION_NO FROM(  SELECT  ASSET_ID,  REG_NO,  REG_DATE,  MODEL_CODE,  SUB_MODEL_CODE,  PRICING_NO,  STATUS,  SUPPLIER_CODE,  QTY,  COST,  PURPOSE,  ADDRESS,  CITY_CODE,  PERIOD,  APPLICATION_NO  FROM " + m_schema_name + ".AF_CO_PRO_ASSET_DETAILS " + " WHERE ASSET_ID LIKE UPPER('%" + vector.elementAt(0) + "%')" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_MODEL_CODE_inv_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE MODEL_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SUB_M_CODE_sql = " SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE FROM   (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE FROM(  SELECT  SUB_CODE, MODEL_CODE, DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, DEFAULT_VALUE FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE " + " WHERE SUB_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY SUB_CODE ASC" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//MODIFIED NUWAN DE SILVA 21-05-07======================
		/*
		m_help_TXT_INVOICE_NO_sql = " SELECT L.NO,L.INVOICE_NO,L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE,L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR,L.SEATING_CAPACITY,L.NET_PRICE,L.VAT,L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE,L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE,L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS,L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID,L.FUEL_CONVERTION_STATUS,TO_CHAR(L.DUE_DATE,'DD-MM-YYYY') AS DUE_DATE,INITCAP(L.MODEL_DESC),INITCAP(L.SUB_MODEL_DESC) FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.REG_NO,P.REG_DATE,P.PRICING_NO,P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY,P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO,P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE,P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE,P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS,P.DUE_DATE,P.MODEL_DESC,P.SUB_MODEL_DESC FROM(  SELECT  A.INVOICE_NO,  A.APPLICATION_NO,  A.ASSET_ID,  NVL(A.ENGINE_NO,'-') ENGINE_NO ,  NVL(A.CHASSIS_NO,'-') CHASSIS_NO,  A.REG_NO,  A.REG_DATE,  A.PRICING_NO,  A.SUB_MODEL_CODE,  NVL(A.COLOUR,'-') COLOUR ,  A.SEATING_CAPACITY,  A.NET_PRICE,  A.VAT,  A.TOTAL_AMOUNT,  A.TO_BE_DELIVERD_TO,  A.VALUE,  A.CURR_CODE,  A.MODEL_CODE,  " + m_schema_name + ".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, " + " NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO, " + " NVL(A.CITY_CODE,' ') CITY_CODE, " + " NVL(A.ADDRESS,'-') ADDRESS, " + " NVL(A.VENDOR_CODE,'-') VENDOR_CODE, " + " NVL( (SELECT B.NAME FROM " + m_schema_name + ".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , " + " NVL(A.BRANCH_ID,'-')  BRANCH_ID," + " A.FUEL_CONVERTION_STATUS  FUEL_CONVERTION_STATUS, " + " A.DUE_DATE, " +
									" ( SELECT B.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,  "+
							" ( SELECT C.DESCRIPTION FROM   " + m_schema_name + ".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC  "+
							
																" FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS A" + " WHERE A.INVOICE_NO LIKE UPPER('" + vector.elementAt(0) + "%') AND A.APPLICATION_NO =UPPER('" + vector.elementAt(1) + "') AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
																*/
		//========================================================
		
		//------------------------ Modified by Mahela on 05-07-2007  ----------------------------------------------------------------------------------------------------------------
		
		m_help_TXT_INVOICE_NO_sql = " SELECT L.NO,L.INVOICE_NO,"+
			" L.APPLICATION_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO, "+
			" L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, "+
			" L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR, "+
			" L.SEATING_CAPACITY,L.NET_PRICE,L.VAT, "+
			" L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE, "+
			" L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE, "+
			" L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS, "+
			" L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID, "+
			" L.FUEL_CONVERTION_STATUS,TO_CHAR(L.DUE_DATE,'DD-MM-YYYY') AS DUE_DATE, "+
			" INITCAP(L.MODEL_DESC),INITCAP(L.SUB_MODEL_DESC),L.YEAR_OF_MANUFACTURE ,"+
			" L.EXTRAS_INCLUDED, "+
			" L.CITY_NAME "+
			" ,L.MAKE_DESC "+
			" ,L.ITEM_SUB_DESC,L.SUM_INSURED,L.AREA,L.POLICE,L.OWNER_ADDRESS,L.COLLECTON_SECURITY,L.LIC_AUTH,L.VEHICAL_AGA,L.CR_BOOK_RECEIVED "+
			" FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO, "+
			" P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO, "+
			" P.REG_NO,P.REG_DATE,P.PRICING_NO, "+
			" P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY, "+
			" P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO, "+
			" P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE, "+
			" P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE, "+
			" P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS, "+
			" P.DUE_DATE,P.MODEL_DESC,P.SUB_MODEL_DESC, "+
			" P.YEAR_OF_MANUFACTURE ,"+
			" P.EXTRAS_INCLUDED, "+
			" P.CITY_NAME "+
			" ,P.MAKE_DESC "+
			" ,P.ITEM_SUB_DESC,P.SUM_INSURED,P.AREA,P.POLICE,P.OWNER_ADDRESS,P.COLLECTON_SECURITY,P.LIC_AUTH,P.VEHICAL_AGA,P.CR_BOOK_RECEIVED "+
			//" FROM(  SELECT  A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID, "+ // commented by udara 05-12-2013
			" FROM(  SELECT  DISTINCT A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID, "+ // added by udara 05-12-2013
			" NVL(A.ENGINE_NO,'-') ENGINE_NO,NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
			" A.REG_NO,A.REG_DATE,A.PRICING_NO,A.SUB_MODEL_CODE, "+
			" NVL(A.COLOUR,'-') COLOUR ,A.SEATING_CAPACITY,A.NET_PRICE, "+
			" A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE, "+
			" A.CURR_CODE,A.MODEL_CODE,"+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
			" NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO,NVL(A.CITY_CODE,' ') CITY_CODE, "+
			" NVL(A.ADDRESS,'-') ADDRESS,NVL(A.VENDOR_CODE,'-') VENDOR_CODE, "+
			" NVL( (SELECT B.NAME FROM   "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
			" NVL(A.BRANCH_ID,'-')  BRANCH_ID,A.FUEL_CONVERTION_STATUS FUEL_CONVERTION_STATUS, "+
			" A.DUE_DATE, "+  
			" (SELECT B.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,  "+
			" (SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE) SUB_MODEL_DESC, "+
			" A.YEAR_OF_MANUFACTURE  ,"+ 
			" A.EXTRAS_INCLUDED EXTRAS_INCLUDED , "+
			" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+ //added by nuwan de silva on 19-09-07
			
			" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
			" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC ,  "+
			" NVL(B.SUM_INSURED,0) SUM_INSURED,B.AREA,B.POLICE,B.OWNER_ADDRESS,B.COLLECTON_SECURITY,B.LIC_AUTH,B.VEHICAL_AGA, NVL(A.CR_BOOK_RECEIVED,'N') CR_BOOK_RECEIVED "+ //added by prabash on30-04-2012		
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_SUM_INSURED_DETAILS B WHERE A.INVOICE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') AND "+
			" A.APPLICATION_NO=UPPER('"+vector.elementAt(1)+"') AND A.ACTIVE_STATUS=('"+vector.elementAt(2)+"') AND A.INVOICE_NO=B.INVOICE_NO(+))P)L "+
			" WHERE L.NO>=" +s1+ "    AND L.NO<=" +s2+ "  ";
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//dont delete()
		//m_help_TXT_APPLICATION_NO = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, " + " CO_APPLICANT," + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, " + " NVL(INQUARY_NO,'-') INQUARY_NO, " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, " + " NVL(" + m_schema_name + ".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER, " + " TRANSACTION_TYPE " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  " + " AND APPLICATION_STATUS =('COMPLETED') " + " ORDER BY ENT_DATE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//	m_help_TXT_APPLICATION_NO = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, " + " CO_APPLICANT," + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, " + " NVL(INQUARY_NO,'-') INQUARY_NO, " + " NVL(" + m_schema_name + ".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, " + " NVL(" + m_schema_name + ".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER, " + " TRANSACTION_TYPE " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('" + vector.elementAt(0) + "%')  " + " AND APPLICATION_STATUS IN('" + vector.elementAt(1) + "','" + vector.elementAt(2) + "') " + " ORDER BY ENT_DATE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		m_help_TXT_INQUARY_NO = "SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,\t       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,\t       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,\t       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,\t       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE,INITCAP(L.CLIENT_LAST_NAME),L.BRANCH_CODE,L.BRANCH_DESC  FROM  (SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,\t       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,\t       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,\t       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,\t       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE ,P.CLIENT_LAST_NAME,P.BRANCH_CODE,P.BRANCH_DESC  FROM  (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE, NVL(CLIENT_LAST_NAME,'-')  CLIENT_LAST_NAME  ,NVL(BRANCH_CODE,'-') BRANCH_CODE , NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),'-') BRANCH_DESC FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE ( UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND " + "         ENT_USER='" + vector.elementAt(2) + "'" + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		m_help_TXT_INQUARY_NO_ALL = "SELECT L.NO, L.INQUIRY_CODE, L.CLIENT_NAME, L.TEL_NO, L.MOBILE_NO, L.FAX_NO,\t       L.ADDRESS, L.CITY_CODE, L.LEGAL_ENTITY, L.STATUS,\t       L.INITIATION_TYPE, L.CLIENT_CATEGORY, L.LEAD_SOURCE_CATEGORY,\t       L.LEAD_SOURCE_NAME, L.INTRODUCER, L.ID_NO, L.INQUIRY_STATUS,        L.ENT_USER,L.ADDRESS2, L.EMAIL, L.TEAM,        L.MK_OFFICER, L.MK_SUPERVISOR, L.CONTACT_PERSON,\t       L.SUB_PRODUCT_CODE, L.TRANSACTION_SUB_TYPE,L.BRANCH_CODE,L.BRANCH_DESC  FROM  (SELECT ROWNUM NO, P.INQUIRY_CODE, P.CLIENT_NAME, P.TEL_NO, P.MOBILE_NO, P.FAX_NO,\t       P.ADDRESS, P.CITY_CODE, P.LEGAL_ENTITY, P.STATUS,\t       P.INITIATION_TYPE, P.CLIENT_CATEGORY, P.LEAD_SOURCE_CATEGORY,\t       P.LEAD_SOURCE_NAME, P.INTRODUCER, P.ID_NO, P.INQUIRY_STATUS,        P.ENT_USER,P.ADDRESS2, P.EMAIL, P.TEAM,        P.MK_OFFICER, P.MK_SUPERVISOR, P.CONTACT_PERSON,\t       P.SUB_PRODUCT_CODE, P.TRANSACTION_SUB_TYPE,P.BRANCH_CODE,P.BRANCH_DESC  FROM  (SELECT NVL(INQUIRY_CODE,'-') INQUIRY_CODE,NVL(CLIENT_NAME,'-') CLIENT_NAME,NVL(TEL_NO,'-') TEL_NO,  NVL(MOBILE_NO,'-') MOBILE_NO,NVL(FAX_NO,'-') FAX_NO,NVL(ADDRESS,'-') ADDRESS,  NVL(CITY_CODE,'-') CITY_CODE,NVL(LEGAL_ENTITY,'-') LEGAL_ENTITY,NVL(STATUS,'-') STATUS,  NVL(INITIATION_TYPE,'-') INITIATION_TYPE,NVL(CLIENT_CATEGORY,'-') CLIENT_CATEGORY, NVL(LEAD_SOURCE_CATEGORY,'-') LEAD_SOURCE_CATEGORY,NVL(LEAD_SOURCE_NAME,'-') LEAD_SOURCE_NAME,  NVL(INTRODUCER,'-') INTRODUCER,NVL(ID_NO,'-') ID_NO,NVL(INQUIRY_STATUS,'-') INQUIRY_STATUS, NVL(ENT_USER,'-') ENT_USER,NVL(ADDRESS2,'-') ADDRESS2,NVL(EMAIL,'-') EMAIL,NVL(TEAM,'-') TEAM,  NVL(MK_OFFICER,'-') MK_OFFICER,NVL(MK_SUPERVISOR,'-') MK_SUPERVISOR,NVL(CONTACT_PERSON,'-') CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE ,NVL(BRANCH_CODE,'-') BRANCH_CODE , NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(BRANCH_CODE),'-') BRANCH_DESC FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE   UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(CLIENT_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         TEL_NO \t\t\t        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t        UPPER(EMAIL)        LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(ID_NO) \t\t\t  LIKE UPPER('%" + vector.elementAt(0) + "%')   " + " ORDER BY INQUIRY_CODE DESC,CLIENT_NAME )P)L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		// commented below by udara 30-10-2013
		//m_help_TXT_CLIENT_CODE = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category,NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, P.ADDRESS1, P.ADDRESS2, P.CITY FROM (SELECT CLIENT_CODE, FULL_NAME, TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY,ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY,ACTIVE_STATUS, TEMP_ACTIVE_STATUS " + "\t FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " + "  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO' AND " + "       ( UPPER(FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "        NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "\t       BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%')) AND " + "        ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " + " ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		// added by udara 30-10-2013
		m_help_TXT_CLIENT_CODE = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, "+
				" NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category, "+
				" NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City "+
					" FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, "+
					" P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, "+
					" P.ADDRESS1, P.ADDRESS2, P.CITY "+
					  " FROM (SELECT CLIENT_CODE, FULL_NAME, "+
						" TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY, "+
						" ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY, "+
						" ACTIVE_STATUS, TEMP_ACTIVE_STATUS " +
						" FROM " + m_schema_name + ".AF_CO_MAS_CLIENT " +
						"  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO'  " +
						"  AND ( UPPER(FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
									" NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%'))  " +
						"  AND  ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " +
						" ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		
		
		// added by udara 30-10-2013
		m_help_TXT_CLIENT_CODE_GURAN = " SELECT L.NO, NVL(L.CLIENT_CODE,'-') Client, NVL(L.FULL_NAME,'-') Name, "+
				" NVL(L.TEL_NO,'-') Tel, NVL(L.NIC_NO,'-') Nic_Busi_No,NVL(L.CLIENT_CATEGORY,'-') Category, "+
				" NVL(L.ADDRESS1,'-') Address1,NVL(L.ADDRESS2,'-') Address2,NVL(CITY,'-') City "+
					" FROM (SELECT ROWNUM NO, P.CLIENT_CODE, P.FULL_NAME, "+
					" P.TEL_NO, P.NIC_NO , P.CLIENT_CATEGORY, "+
					" P.ADDRESS1, P.ADDRESS2, P.CITY "+
					  " FROM (SELECT CLIENT_CODE, FULL_NAME, "+
						" TEL_NO, NVL(NIC_NO,BUSINESS_CERTIFICATE_NO) NIC_NO,CLIENT_CATEGORY, "+
						" ADDRESS1,ADDRESS2," + m_schema_name + ".AF_CO_GET_CITY_NAME(CITY_CODE) CITY, "+
						" ACTIVE_STATUS, TEMP_ACTIVE_STATUS " +
						" FROM " + m_schema_name + ".AF_CO_MAS_CLIENT ,"+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR B  " +
						"  WHERE " + m_schema_name + ".AF_CO_VAL_CLIENT(CLIENT_CODE,'" + vector.elementAt(1) + "','" + vector.elementAt(2) + "')='NO'  AND B.GUARANTOR_CODE=CLIENT_CODE   " +
						"  AND ( UPPER(FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" CLIENT_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" TEL_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
									" NIC_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
									" BUSINESS_CERTIFICATE_NO LIKE UPPER('%" + vector.elementAt(0) + "%'))  " +
						"  AND  ACTIVE_STATUS=('" + vector.elementAt(3) + "')  " +
						" ORDER BY FULL_NAME )P) L " + "WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		//Comment by Nuwan De Silva 24-05-07============
		
		//    m_help_TXT_MAKE = " SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC  FROM   (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC  FROM(  SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC  FROM " + m_schema_name + ".AF_CO_MAS_MAKE A," + m_schema_name + ".AF_CO_MAS_MODEL B " + " WHERE A.MAKE_CODE=B.MAKE_CODE AND A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND B.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "')  " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		//Added by Nuwan De Silv 24-05-07=========================================
		
		/*	    m_help_TXT_MAKE =			
		"					SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC "+
		"					FROM "+
		"					(SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC "+
		"					FROM( "+
		"					SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC "+
		"					FROM   " + m_schema_name + ".AF_CO_MAS_MAKE A, "+
		"					" + m_schema_name + ".AF_CO_MAS_MODEL B   "+
		"					WHERE A.MAKE_CODE=B.MAKE_CODE AND "+
		"					(A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  OR UPPER(A.MAKE_DESC)     LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND "+
		"					(B.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%')  ) AND "+
		"					A.ACTIVE_STATUS=('" + vector.elementAt(2) + "')      )P)L "+
		"	        WHERE L.NO>=    " + s1 + "   AND L.NO<=    " + s2 + "   ";
		*/
		
		m_help_TXT_MAKE =			
			"					SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC "+
			"					FROM "+
			"					(SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC "+
			"					FROM( "+
			"					SELECT DISTINCT A.MAKE_CODE ,A.MAKE_DESC "+
			"					FROM   " + m_schema_name + ".AF_CO_MAS_MAKE A "+
			//"					," + m_schema_name + ".AF_CO_MAS_MODEL B   "+
			"					WHERE /*A.MAKE_CODE=B.MAKE_CODE AND */ "+
			"					(A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  OR UPPER(A.MAKE_DESC)     LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND "+
			//"					(B.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%')  ) AND "+
			"					A.ACTIVE_STATUS=('" + vector.elementAt(2) + "')      )P)L "+
			"	        WHERE L.NO>=    " + s1 + "   AND L.NO<=    " + s2 + "   ";
		
		
		
		//Comment by Nuwan De Silva 24-05-07============
		//  m_help_TXT_MODEL_CODE_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION  FROM(  SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION  FROM " + m_schema_name + ".AF_CO_MAS_MODEL A," + m_schema_name + ".AF_CO_MAS_SUB_MODLE B " + " WHERE A.MODEL_CODE=B.MODEL_CODE AND A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(2) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(3) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//Added by Nuwan De Silva 24-05-07=========================================
		
		/*	m_help_TXT_MODEL_CODE_sql =	"	SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.ITEM_SUB_CAT "+
					"	FROM "+
					"	(SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.ITEM_SUB_CAT "+
					"	FROM( "+
					"	SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION,D.DESCRIPTION ITEM_SUB_CAT "+
					"	FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A, "+
					"	" + m_schema_name + ".AF_CO_MAS_SUB_MODLE B  , "+
					"	" + m_schema_name + ".AF_CO_MAS_MAKE C ,  "+
					" " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY D "+
					
					"	WHERE A.MODEL_CODE=B.MODEL_CODE AND "+
					"	A.MAKE_CODE=C.MAKE_CODE AND "+
					"	A.ITEM_SUB_CAT(+)=D.ITEM_SUB_CAT AND "+
					"	(A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(0) + "%') )AND "+
					"	(A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR  UPPER(C.MAKE_DESC) LIKE UPPER('%" + vector.elementAt(1) + "%') ) AND "+
					"	(B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(2) + "%') OR  UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(2) + "%') ) AND "+
					"	A.ACTIVE_STATUS=('" + vector.elementAt(3) + "')     )P)L    "+
					"	WHERE L.NO>=    " + s1 + "   AND L.NO<=    " + s2 + "   ";
		*/
		
		m_help_TXT_MODEL_CODE_sql =	"	SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.ITEM_SUB_CAT "+
			"	FROM "+
			"	(SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.ITEM_SUB_CAT "+
			"	FROM( "+
			"	SELECT DISTINCT A.MODEL_CODE ,A.DESCRIPTION,D.DESCRIPTION ITEM_SUB_CAT "+
			"	FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A, "+
			//"	" + m_schema_name + ".AF_CO_MAS_SUB_MODLE B  , "+
			"	" + m_schema_name + ".AF_CO_MAS_MAKE C ,  "+
			" " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY D "+
			
			"	WHERE /*A.MODEL_CODE=B.MODEL_CODE AND */ "+
			"	A.MAKE_CODE=C.MAKE_CODE AND "+
			"	A.ITEM_SUB_CAT(+)=D.ITEM_SUB_CAT AND "+
			"	(A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(0) + "%') )AND "+
			"	(A.MAKE_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR  UPPER(C.MAKE_DESC) LIKE UPPER('%" + vector.elementAt(1) + "%') ) AND "+
			//"	(B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(2) + "%') OR  UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(2) + "%') ) AND "+
			"	A.ACTIVE_STATUS=('" + vector.elementAt(3) + "')     )P)L    "+
			"	WHERE L.NO>=    " + s1 + "   AND L.NO<=    " + s2 + "   ";
		
		//Comment by Nuwan De Silva 24-05-07============
		
		//        m_help_TXT_SUB_MODEL_sql = " SELECT L.NO ,L.SUB_CODE,L.MODEL_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE FROM   (SELECT ROWNUM NO,P.SUB_CODE,P.MODEL_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE FROM(  SELECT  DISTINCT SUB_CODE, MODEL_CODE, DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, DEFAULT_VALUE FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE " + " WHERE SUB_CODE LIKE UPPER('" + vector.elementAt(0) + "%') AND MODEL_CODE LIKE UPPER('" + vector.elementAt(1) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + " ORDER BY SUB_CODE ASC" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		//Added by Nuwan De Silv 24-05-07=========================================
		
		/*   m_help_TXT_SUB_MODEL_sql ="	SELECT L.NO ,L.SUB_CODE,L.DESCRIPTION,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE "+
				" FROM "+
				" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE "+
				" FROM( "+
				" SELECT  DISTINCT SUB_CODE,B.DESCRIPTION, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, B.DEFAULT_VALUE "+
				" FROM    "+
				" " + m_schema_name + ".AF_CO_MAS_MODEL A, "+
				" " + m_schema_name + ".AF_CO_MAS_SUB_MODLE B   "+
				" WHERE A.MODEL_CODE=B.MODEL_CODE AND "+
				" (B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') OR  UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND "+
				" (A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%') )AND "+
				" A.ACTIVE_STATUS=('Y')   "+
				" ORDER BY B.SUB_CODE ASC    )P)L    "+
				" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; 
				*/
		
		// comment by nuwan de silva on 11-12-2007 --------- don't delete ---------------------------------------------------------------------------------
		/* m_help_TXT_SUB_MODEL_sql ="	SELECT L.NO ,L.SUB_CODE,L.DESCRIPTION,L.MODEL_CODE,L.DESC_MODEL, L.MAKE_CODE ,L.MAKE_DESC,L.ITEM_SUB_CAT,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE "+
		" FROM "+
		" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION,P.MODEL_CODE,P.DESC_MODEL,P.MAKE_CODE ,P.MAKE_DESC,P.ITEM_SUB_CAT,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE "+
		" FROM( "+
		" SELECT  DISTINCT SUB_CODE,B.DESCRIPTION,A.MODEL_CODE,A.DESCRIPTION DESC_MODEL,C.MAKE_CODE ,C.MAKE_DESC,D.DESCRIPTION ITEM_SUB_CAT, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, B.DEFAULT_VALUE "+
		" FROM    "+
		" " + m_schema_name + ".AF_CO_MAS_MODEL A, "+
		" " + m_schema_name + ".AF_CO_MAS_SUB_MODLE B ,  "+
		"	" + m_schema_name + ".AF_CO_MAS_MAKE C,   "+
		" " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY D "+
		" WHERE A.MODEL_CODE=B.MODEL_CODE AND "+
		"	A.MAKE_CODE=C.MAKE_CODE AND "+
		"	A.ITEM_SUB_CAT(+)=D.ITEM_SUB_CAT AND "+
		" (B.SUB_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') OR  UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND "+
		" (A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%') )AND "+
		" A.ACTIVE_STATUS=('Y')   "+
		" ORDER BY B.SUB_CODE ASC    )P)L    "+
		" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; 
		*/
		
		// added by nuwan de silva on 11-12-2007 _____________________________________
		m_help_TXT_SUB_MODEL_sql ="	SELECT L.NO ,L.SUB_CODE MODEL_CODE,L.DESCRIPTION DESC_MODEL , L.MAKE_CODE ,L.MAKE_DESC,L.ITEM_SUB_CAT,L.ENGINE_CAPACITY,L.OPTION_TYPE,L.COUNTRY_CODE,L.YEAR_OF_MANUFACTURE,L.DEFAULT_VALUE "+
			" FROM "+
			" (SELECT ROWNUM NO,P.SUB_CODE,P.DESCRIPTION,P.MAKE_CODE ,P.MAKE_DESC,P.ITEM_SUB_CAT,P.ENGINE_CAPACITY,P.OPTION_TYPE,P.COUNTRY_CODE,P.YEAR_OF_MANUFACTURE,P.DEFAULT_VALUE "+
			" FROM( "+
			" SELECT  DISTINCT SUB_CODE  , B.DESCRIPTION  ,C.MAKE_CODE ,C.MAKE_DESC,D.DESCRIPTION ITEM_SUB_CAT, ENGINE_CAPACITY, OPTION_TYPE, COUNTRY_CODE, YEAR_OF_MANUFACTURE, B.DEFAULT_VALUE "+
			" FROM    "+
			" " + m_schema_name + ".AF_CO_MAS_MODEL A, "+
			" " + m_schema_name + ".AF_CO_MAS_SUB_MODLE B ,  "+
			"	" + m_schema_name + ".AF_CO_MAS_MAKE C,   "+
			" " + m_schema_name + ".AF_CO_MAS_ITEM_SUB_CATEGORY D "+
			" WHERE A.MODEL_CODE=B.MODEL_CODE AND "+
			"	A.MAKE_CODE=C.MAKE_CODE AND "+
			"	A.ITEM_SUB_CAT(+)=D.ITEM_SUB_CAT AND "+
			" (A.MAKE_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND "+
			" (A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR  UPPER(A.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%') )AND "+
			" A.ACTIVE_STATUS=('Y')   "+
			" ORDER BY B.DESCRIPTION ASC    )P)L    "+
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; 
		
		
		
		m_help_TXT_INVOICE_PURCHASE = " SELECT L.NO ,L.INVOICE_NO,L.ASSET_ID,L.ENGINE_NO,L.CHASSIS_NO,L.NET_PRICE,L.VAT,L.GROSS_AMOUNT  FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO,P.NET_PRICE,P.VAT,P.GROSS_AMOUNT  FROM(  SELECT  A.INVOICE_NO,  A.ASSET_ID,  A.ENGINE_NO,  A.CHASSIS_NO,  A.NET_PRICE,  A.VAT,  (A.NET_PRICE +A.VAT) GROSS_AMOUNT  FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS A " + " WHERE  A.VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND A.APPLICATION_NO LIKE UPPER('%" + vector.elementAt(1) + "%') AND A.INVOICE_NO LIKE UPPER('%" + vector.elementAt(2) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(3) + "') AND A.CURR_CODE IN (SELECT CURR_CODE FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS WHERE INVOICE_NO LIKE UPPER('%" + vector.elementAt(2) + "%'))" + " AND A.INVOICE_NO NOT IN(SELECT " + " DISTINCT    PRO_INVOICE_NO " + " FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER_DET " + " WHERE PURCHASE_ORDER_NO IN " + " (SELECT " + " PURCHASE_ORDER_NO " + " FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER " + " WHERE   VENDER_CODE LIKE UPPER('%" + vector.elementAt(0) + "%'))) " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_PURCHASE_ORDER_NO = " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.VENDER_CODE  FROM   (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.VENDER_CODE  FROM(  SELECT  PURCHASE_ORDER_NO,  APPLICATION_NO,  VENDER_CODE  FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER " + " WHERE  PURCHASE_ORDER_NO LIKE UPPER('%" + vector.elementAt(0) + "%') AND ACTIVE_STATUS IN('" + vector.elementAt(2) + "','" + vector.elementAt(3) + "') AND VENDER_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') ORDER BY PURCHASE_ORDER_NO DESC    " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		PriceSql_invoice = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + " WHERE PRICING_NO LIKE '%" + vector.elementAt(0) + "%' /*AND PRICING_STATUS=('" + vector.elementAt(2) + "')*/ " + " AND APP_NO ='" + vector.elementAt(1) + "' " + " ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		m_help_TXT_CITY_CODE_sql = " SELECT L.NO ,L.CITY_CODE,L.CITY_DESC,L.DISTRICT_CODE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.CITY_CODE,P.CITY_DESC,P.DISTRICT_CODE,P.DEFAULT_VALUE  FROM(  SELECT  CITY_CODE,  CITY_DESC,  DISTRICT_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_CITY " + " WHERE ( CITY_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(CITY_DESC) LIKE UPPER('%" + vector.elementAt(0) + "%') ) AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_APPLICATION_NO_2 = " SELECT L.NO ,L.APPLICATION_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO  FROM(  SELECT  DISTINCT APPLICATION_NO  FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS " + " WHERE ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_APPLICATION_NO_3 = " SELECT L.NO ,L.APPLICATION_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO  FROM(  SELECT  DISTINCT APPLICATION_NO  FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%') AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_SUPPLIER_PURCHASE_ORDER = " SELECT L.NO ,L.VENDOR_CODE,L.NAME  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME  FROM(  SELECT  VENDOR_CODE,NAME  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS A " + " WHERE A.VENDOR_CODE IN  " + " (SELECT " + " B.VENDOR_CODE " + " FROM " + m_schema_name + ".AF_CO_PRO_APP_INVOICE_DETAILS B  " + " WHERE B.APPLICATION_NO LIKE UPPER('%" + vector.elementAt(1) + "%') )  " + " AND A.VENDOR_CODE LIKE ('%" + vector.elementAt(0) + "%') AND A.ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SUPPLIER_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME  FROM(  SELECT  VENDOR_CODE,  NAME  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_SCREEN_NAME_sql = "SELECT L.NO,L.SCREEN_NAME,L.DISPLAY_NAME  FROM (SELECT ROWNUM NO,P.SCREEN_NAME,P.DISPLAY_NAME  FROM (SELECT SCREEN_NAME,DISPLAY_NAME  FROM " + m_schema_name + ".CO_CO_MAS_USER_SCREEN " + "  WHERE SCREEN_NAME LIKE UPPER('%" + vector.elementAt(0) + "%') " + "  ORDER BY SCREEN_NAME )P) L\t" + " WHERE L.NO>= " + s1 + " AND L.NO<= " + s2 + " ";
		
		MKOfficerSql = " SELECT NO,USER_ID,NAME   "+
			" FROM  ( SELECT ROWNUM NO,USER_ID,NAME  "+
			" FROM  ( SELECT USER_ID,NAME  FROM   "+
			" " + m_schema_name + ".CO_CO_MAS_USER " + 
			" WHERE  /*DIVISION_CODE='" + vector.elementAt(1) + "' AND */ " + //commnetd by manjula on 04-03-2008 
			" ACTIVE_STATUS='Y' AND "+
			" (USER_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			"  UPPER(NAME) LIKE UPPER('%" + vector.elementAt(0) + "%')) " + 
			" ORDER BY NAME)) P " + 
			" WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		//===========================================================================
		
		m_help_team_user_id_sql_new = //Added By Sandun on 05-11-2008
			" SELECT L.NO ,L.USER_ID,L.NAME,NVL(L.SUB_TEAM_ID,'N/A') AS SUB_TEAM_ID,L.SUB_TEAM_DESC ,NVL(L.TEAM_ID,'N/A') AS TEAM_ID,NVL(L.TEAM_DESC,'N/A') AS TEAM_DESC ,L.TEAM_HEAD "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.SUB_TEAM_ID,P.SUB_TEAM_DESC,P.TEAM_ID,P.TEAM_DESC,P.TEAM_HEAD "+
			" FROM( "+ 
			" SELECT  "+
			" USER_ID,  "+
			" "+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID) NAME,  "+
			" C.SUB_TEAM_ID, "+
			" D.SUB_TEAM_DESC, "+
			" A.TEAM_ID,  "+
			" TEAM_DESC,  "+
			" A.TEAM_HEAD "+
			" FROM "+m_schema_name+".AF_CO_MAS_TEAMS A, "+m_schema_name+".AF_CO_MAS_TEAM_MEMBERS B , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS_ASSIGN C , "+m_schema_name+".AF_CO_MAS_SUB_TEAMS D "+
			" WHERE B.TEAM_ID=C.SUB_TEAM_ID "+
			" AND   A.TEAM_ID=C.TEAM_ID "+
			" AND   C.SUB_TEAM_ID=D.SUB_TEAM_ID "+
			" AND  C.ACTIVE_STATUS='Y'   "+
			" AND  B.ACTIVE_STATUS='Y'   "+
			" AND ( UPPER(USER_ID) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR   UPPER("+m_schema_name+".AF_CO_GET_EMP_NAME(USER_ID)) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			" ORDER BY A.TEAM_ID ,C.SUB_TEAM_ID,USER_ID "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1 +"  AND L.NO<=  "+ s2 +" ";
		
		//==========================================================================
		
		
		
		MKOfficerSqlNew = 	" SELECT L.NO ,L.EMP_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ADDRESS,L.LOCATION_CODE,NVL(L.AREA_CODE,'N/A') AS AREA_CODE ,NVL(L.CITY_CODE,'N/A') AS CITY_CODE,NVL(L.CONTACT_NO,'N/A')AS CONTACT_NO,L.DESIGNATION_CODE,L.DIVISION_CODE,NVL(L.EPF_NO,'N/A') AS EPF_NO,L.ID_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ADDRESS,P.LOCATION_CODE,P.AREA_CODE,P.CITY_CODE,P.CONTACT_NO,P.DESIGNATION_CODE,P.DIVISION_CODE,P.EPF_NO,P.ID_NO "+
			" FROM( "+ 
			" SELECT "+
			" EMP_CODE,"+//Added By Sandun on 25-08-2008
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
			" WHERE ( EMP_CODE LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(FIRST_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(LAST_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
			" AND ACTIVE_STATUS=('Y') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1 +"  AND L.NO<=  "+s2+" ";
		
		// added by udara 09-10-2018		
		PledgeContractSqlNew=				
			" SELECT L.NO ,L.FINANCE_NO, L.APPLICATION_NO  "+
			" FROM  "+ 
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO "+
			" FROM( "+ 
			" SELECT "+
			" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
			" A.APPLICATION_NO "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE   "+ 
			"  AND (UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			//"  A.CLIENT_CODE LIKE '%"+vector.elementAt(0)+"%' OR  "+ // commented by udara 25-10-2018
			"  B.NIC_NO LIKE '%"+vector.elementAt(0)+"%' OR  "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO)) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ 
			"  A.APPLICATION_NO LIKE '%"+vector.elementAt(0)+"%' OR "+
			"  A.FINANCE_NO LIKE '%"+vector.elementAt(0)+"%'  "+	
			"  )  "+
			"  AND A.CLIENT_CODE = '"+vector.elementAt(1)+"' "+ // added by udara 25-10-2018
			"  AND A.APPLICATION_STATUS = 'ACTIVATED'  "+
			"  AND A.TRANSACTION_TYPE = 'HIREPURCH' "+
			"  AND TO_DATE("+m_schema_name+".AF_CO_GET_LAST_RENTAL_DATE(A.APPLICATION_NO),'DD-MM-YYYY') > SYSDATE   "+
			"  AND "+m_schema_name+".AF_GET_PERFORM_STATUS(A.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) = 'PERFORM' "+ // added by udara 25-10-2018
			"  AND "+m_schema_name+".AF_RE_IS_VEHICLE_IN_YARD2( A.FINANCE_NO, TO_CHAR(SYSDATE,'DD-MM-YYYY')) = 'Y' "+ // added by udara 25-10-2018
			
			//"  AND A.FINANCE_NO NOT IN (SELECT PLEDGE_CONTRACT FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS WHERE APPLICATION_NO <> '"+vector.elementAt(1)+"' ) "+
			//" AND (A.FINANCE_NO IN (SELECT PLEDGE_CONTRACT FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS WHERE APPLICATION_NO = '"+vector.elementAt(1)+"' ) "+
	        //" OR A.FINANCE_NO NOT IN (SELECT PLEDGE_CONTRACT FROM "+m_schema_name+".AF_MAS_PLEDGE_CONTRACTS ) "+
	        //" ) "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ s1+"  AND L.NO<= "+s2+" ";
		// end by udara 09-10-2018
		
		
		
		
		MKSuperSql = " SELECT NO,USER_ID,NAME  FROM  ( SELECT ROWNUM NO,USER_ID,NAME  FROM  ( SELECT USER_ID,NAME  FROM   " + m_schema_name + ".CO_CO_MAS_USER " + " WHERE  DIVISION_CODE='" + vector.elementAt(1) + "' AND " + "\t      (USER_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "         UPPER(NAME) LIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY NAME)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		//Modified by Mahela on 18-05-2007
		MKTeamSql = " SELECT NO,TEAM_ID, TEAM_DESC,TEAM_HEAD  FROM  ( SELECT ROWNUM NO,TEAM_ID, TEAM_DESC,TEAM_HEAD  FROM  ( SELECT B.TEAM_ID,TEAM_DESC,TEAM_HEAD  FROM " + m_schema_name + ".AF_CO_MAS_TEAMS B " + " WHERE B.ACTIVE_STATUS='Y' AND " + " (B.TEAM_ID LIKE UPPER('%" + vector.elementAt(0) + "%') OR  " + "  TEAM_DESC\tLIKE UPPER('%" + vector.elementAt(0) + "%') OR TEAM_HEAD\tLIKE UPPER('%" + vector.elementAt(0) + "%') ) " + " ORDER BY TEAM_DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		TrnSubSql = " SELECT NO,TRN_SUB_TYPE,DESCRIPTION,RATE  FROM  ( SELECT ROWNUM NO, TRN_SUB_TYPE, DESCRIPTION,RATE  FROM  ( SELECT A.TRN_SUB_TYPE,  A.DESCRIPTION,A.RATE  FROM   " + m_schema_name + ".AF_CO_MAS_TRANSACTION_SUB_TYPE A " + " WHERE  ACTIVE_STATUS  LIKE '%" + vector.elementAt(2) + "%' AND " + "     (A.TRN_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND " + " TRN_SUB_TYPE LIKE UPPER('%" + vector.elementAt(0) + "%') ) " + " ORDER BY TRN_SUB_TYPE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		ModelSql = " SELECT NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE  FROM  ( SELECT ROWNUM NO,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE  FROM  ( SELECT A.MODEL_CODE, A.DESCRIPTION,A.TAX_RATE,                 A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE  FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A " + " WHERE  ACTIVE_STATUS ='" + vector.elementAt(2) + "' AND " + "        A.MODEL_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') AND " + "        A.MAKE_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY MODEL_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		SubModelSql = " SELECT NO,SUB_CODE SUB_MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MODEL_CODE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY  FROM  ( SELECT ROWNUM NO,SUB_CODE,MODEL_CODE,DESCRIPTION,TAX_RATE,TAX_FOR_LEASE,MAKE_CODE,FUEL_TYPE,ENGINE_CAPACITY  FROM  ( SELECT B.SUB_CODE ,B.MODEL_CODE, B.DESCRIPTION,A.TAX_RATE,                 A.TAX_FOR_LEASE, A.MAKE_CODE, A.FUEL_TYPE,b.ENGINE_CAPACITY  FROM   " + m_schema_name + ".AF_CO_MAS_MODEL A," + m_schema_name + ".AF_CO_MAS_SUB_MODLE B " + " WHERE  B.ACTIVE_STATUS ='" + vector.elementAt(2) + "' AND " + "        A.MODEL_CODE=B.MODEL_CODE AND  " + "        (B.SUB_CODE LIKE UPPER('%" + vector.elementAt(1) + "%') OR " + "        UPPER(B.DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%')) AND  " + "        A.MAKE_CODE LIKE  UPPER('%" + vector.elementAt(0) + "%') " + " ORDER BY MODEL_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		IntBaseSql = " SELECT NO,BASE_CODE, DESCRIPTION, RATE "+
			" FROM(  SELECT ROWNUM NO,BASE_CODE, DESCRIPTION, RATE "+
			" FROM(  SELECT A.BASE_CODE, A.DESCRIPTION, A.RATE "+
			" FROM   " + m_schema_name + ".AF_CO_MAS_INTEREST_BASE A " + 
			" WHERE  ACTIVE_STATUS='Y' AND " + 
			"       (BASE_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			"        DESCRIPTION LIKE UPPER('%" + vector.elementAt(0) + "%')) )) P " + 
			" WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		VendorSql = " SELECT NO,VENDOR_CODE,NAME,CATEGORY,TYPE  FROM  ( SELECT ROWNUM NO,VENDOR_CODE,NAME,CATEGORY,TYPE  FROM  ( SELECT A.VENDOR_CODE, A.NAME, NVL(A.CATEGORY,'-') CATEGORY,NVL(A.TYPE,'-') TYPE  FROM  " + m_schema_name + ".AF_CO_MAS_VENDORS A " + " WHERE ACTIVE_STATUS='" + vector.elementAt(1) + "' AND " + "       (VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + "       UPPER(NAME)  \t LIKE UPPER('%" + vector.elementAt(0) + "%')) " + " ORDER BY VENDOR_CODE)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		// commented by udara 15-02-2017
		/*
		PriceSql = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   "+
			" FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  "+
			" FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + 
			" WHERE PRICING_NO LIKE '%" + vector.elementAt(0) + "%' AND " + 
			//" " + m_schema_name + ".AF_GET_PRICE_ALLO_STATUS(PRICING_NO)='Y' AND "+//ADD BY INDITHA 6-12-2007
			"       (INQUIRY_NO LIKE '%" + vector.elementAt(1) + "%' OR INQUIRY_NO IS NULL ) " + " ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		*/
		
		// added by udara 15-02-2017
		PriceSql = "SELECT NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   "+
			" FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  "+
			" FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + 
			" WHERE PRICING_NO LIKE '%" + vector.elementAt(0) + "%'  " + 
			" AND (INQUIRY_NO LIKE '%" + vector.elementAt(1) + "%' OR INQUIRY_NO IS NULL ) " +
			//" AND APP_NO NOT IN (SELECT APPLICATION_NO FROM AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO = A.APP_NO     AND APPLICATION_STATUS = 'ACTIVATED') "+ // added by udara 15-02-2017 // commented by udara 19-09-2017
			//" AND APP_NO NOT IN (SELECT APPLICATION_NO FROM AF_CO_PRO_APPLICATION_DETAILS WHERE INQUIRY_NO     = A.INQUIRY_NO AND APPLICATION_STATUS = 'ACTIVATED') "+ // added by udara 15-02-2017 // commented by udara 19-09-2017
			" ORDER BY ENT_DATE DESC)) P " +
			" WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		// end by udara 15-02-2017
		
		m_help_TXT_PRICING_NO_sql = " SELECT L.NO ,L.PRICING_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.MAKE_CODE,L.MODEL_CODE,LTRIM(TO_CHAR(L.GROSS_AMOUNT,'999,999,999.99')) AS GROSS_AMOUNT,L.SUB_MODEL_CODE,L.ITEM_CATEGORY,L.ITEM_SUB_CAT_CODE,L.TRANSACION_TYPE,L.TRN_SUB_TYPE,L.INTEREST_TYPE,L.VARIABLE_INT_BASE,L.INT_MARGIN,L.CONDITION_OF_ASSET, L.ASSET_USAGE_TYPE,L.VAT_PERCENTAGE,L.ENGINE_CAPACITY, L.FUEL_TYPE,L.TARE,L.MAINTENANCE_STATUS,L.BUY_BACK,PERIOD,L.PAYMENT_MODE,L.PAYMENT_INTERVAL,L.RATE,LTRIM(TO_CHAR(L.VAT_AMOUNT,'999,999,999.99')) AS VAT_AMOUNT, LTRIM(TO_CHAR(L.NET_AMOUNT,'999,999,999,999,999,999,999,999.99')) AS NET_AMOUNT,L.NIBSM,LTRIM(TO_CHAR(L.AMI,'999,999,999.99')),LTRIM(TO_CHAR(L.LAST_RENTAL,'999,999,999.99')) AS LAST_RENTAL,LTRIM(TO_CHAR(L.RESIDUAL_VALUE,'999,999,999.99')) AS RESIDUAL_VALUE,L.OUTFLOW_PATTERN,L.SUPPLIER_CREDIT,L.INFLOW_PATTERN,L.PRICING_STATUS, L.CURRENCY_CODE,L.SUPPLIER,LTRIM(TO_CHAR(L.TRAN_AMOUNT_CURRENCY,'999,999,999.99')) AS TRAN_AMOUNT_CURRENCY ,L.DESC5 AS CONDITION FROM   (SELECT ROWNUM NO,P.PRICING_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.MAKE_CODE,P.MODEL_CODE,P.GROSS_AMOUNT,P.SUB_MODEL_CODE,P.ITEM_CATEGORY,P.ITEM_SUB_CAT_CODE,P.TRANSACION_TYPE,P.TRN_SUB_TYPE,P.INTEREST_TYPE,P.VARIABLE_INT_BASE,P.INT_MARGIN,P.CONDITION_OF_ASSET, P.ASSET_USAGE_TYPE,P.VAT_PERCENTAGE,P.ENGINE_CAPACITY, P.FUEL_TYPE,P.TARE,P.MAINTENANCE_STATUS,P.BUY_BACK,PERIOD,P.PAYMENT_MODE,P.PAYMENT_INTERVAL,P.RATE,P.VAT_AMOUNT, P.NET_AMOUNT,P.NIBSM,P.AMI,P.LAST_RENTAL,P.RESIDUAL_VALUE,P.OUTFLOW_PATTERN,P.SUPPLIER_CREDIT,P.INFLOW_PATTERN,P.PRICING_STATUS, P.CURRENCY_CODE,P.SUPPLIER,P.TRAN_AMOUNT_CURRENCY,P.DESC5 FROM(  SELECT \tPRICING_NO, \tINQUIRY_NO,  CLIENT_NAME, \tA.MAKE_CODE, \tA.MODEL_CODE, \tGROSS_AMOUNT, \tSUB_MODEL_CODE, \tITEM_CATEGORY, \tITEM_SUB_CAT_CODE, \tTRANSACION_TYPE, \tTRN_SUB_TYPE, \tINTEREST_TYPE, \tVARIABLE_INT_BASE, \tINT_MARGIN, \tCONDITION_OF_ASSET, \tASSET_USAGE_TYPE, \tVAT_PERCENTAGE, \tENGINE_CAPACITY, \tA.FUEL_TYPE, \tTARE, \tMAINTENANCE_STATUS, \tBUY_BACK, \tPERIOD, \tPAYMENT_MODE, \tPAYMENT_INTERVAL, \tRATE, \tVAT_AMOUNT, \tNET_AMOUNT, \tNIBSM, \tAMI, \tLAST_RENTAL, \tRESIDUAL_VALUE, \tOUTFLOW_PATTERN, \tSUPPLIER_CREDIT, \tINFLOW_PATTERN, \tPRICING_STATUS,  CURRENCY_CODE, \tSUPPLIER, \tTRAN_AMOUNT_CURRENCY , \tF.DESCRIPTION AS DESC5  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A" + "\t," + m_schema_name + ".AF_CO_MAS_CONDITION_OF_ASSET F " + " ," + m_schema_name + ".AF_MK_PRO_INQUIRY G " + " WHERE PRICING_NO LIKE UPPER('%" + vector.elementAt(1) + "%')  " + " AND A.CONDITION_OF_ASSET=F.CODE " + " AND A.INQUIRY_NO=('" + vector.elementAt(0) + "') " + " AND A.INQUIRY_NO=G.INQUIRY_CODE\t" + " ORDER BY PRICING_NO DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_QUOTATION_NO_sql = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.STATUS,P.APPR_USER,P.APPR_DATE FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO,\tSTATUS, APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION " + " WHERE QUOTATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%')  " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		//Modified by Mahela on 23-04-2007 -  purpose - inquiry No search
		m_help_TXT_QUOTATION_NO_sql1 = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.STATUS,L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO,P.CLIENT_NAME,P.STATUS,P.APPR_USER,P.APPR_DATE FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO, CLIENT_NAME, \tA.STATUS, APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION A ," + m_schema_name + ".AF_MK_PRO_INQUIRY B" + " WHERE (QUOTATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(CLIENT_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR INQUIRY_NO LIKE UPPER('%" + vector.elementAt(0) + "%') )  " + " AND INQUIRY_CODE=INQUIRY_NO " + " AND A.STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY QUOTATION_NO DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_CONDITION_OF_ASSET_sql = " SELECT L.NO ,L.CODE,L.DESCRIPTION,L.ACTIVE_STATUS,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.CODE,P.DESCRIPTION,P.ACTIVE_STATUS,P.DEFAULT_VALUE  FROM(  SELECT \tCODE,\tDESCRIPTION,\tACTIVE_STATUS,\tDEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_CONDITION_OF_ASSET " + " WHERE UPPER(CODE) LIKE UPPER('%" + vector.elementAt(0) + "%')  " + " AND UPPER(DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%')  " + " AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_MAKE_CODE_sql = " SELECT L.NO ,L.MAKE_CODE,L.MAKE_DESC,L.ITEM_SUB_CAT,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MAKE_CODE,P.MAKE_DESC,P.ITEM_SUB_CAT,P.DEFAULT_VALUE  FROM(  SELECT  MAKE_CODE, MAKE_DESC,  ITEM_SUB_CAT,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MAKE " + " WHERE UPPER(MAKE_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') AND UPPER(MAKE_DESC) LIKE UPPER('%" + vector.elementAt(1) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_MODEL_CODE1_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE UPPER(MODEL_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') AND UPPER(DESCRIPTION) LIKE UPPER('%" + vector.elementAt(1) + "%') AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_MODEL_CODE2_sql = " SELECT L.NO ,L.MODEL_CODE,L.DESCRIPTION,L.MAKE_CODE,L.FUEL_TYPE,L.TAX_RATE,L.TAX_FOR_LEASE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.MODEL_CODE,P.DESCRIPTION,P.MAKE_CODE,P.FUEL_TYPE,P.TAX_RATE,P.TAX_FOR_LEASE,P.DEFAULT_VALUE  FROM(  SELECT  MODEL_CODE, DESCRIPTION,  MAKE_CODE,  FUEL_TYPE,  TAX_RATE,  TAX_FOR_LEASE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_MODEL " + " WHERE UPPER(MAKE_CODE) LIKE UPPER('%" + vector.elementAt(1) + "%') AND (UPPER(MODEL_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') AND UPPER(DESCRIPTION) LIKE UPPER('%" + vector.elementAt(2) + "%')) AND ACTIVE_STATUS=('" + vector.elementAt(3) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";//optimize by prabash on03-02-2012
		m_help_TXT_INQUIRY_NO_sql = " SELECT L.NO,L.INQUIRY_CODE AS INQUIRY_NO,L.CLIENT_NAME,L.ADDRESS,L.ADDRESS2,L.CITY_CODE  FROM   (SELECT ROWNUM NO,P.INQUIRY_CODE,P.CLIENT_NAME,P.ADDRESS,P.ADDRESS2,P.CITY_CODE  FROM(  SELECT  INQUIRY_CODE,\tCLIENT_NAME,\tADDRESS,\tADDRESS2,\tCITY_CODE  FROM " + m_schema_name + ".AF_MK_PRO_INQUIRY " + " WHERE (UPPER(INQUIRY_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%')OR UPPER(CLIENT_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%')OR UPPER(MK_OFFICER) LIKE UPPER('%" + vector.elementAt(0) + "%')OR UPPER(ID_NO) LIKE UPPER('%" + vector.elementAt(0) + "%')OR UPPER(TEL_NO) LIKE UPPER('%" + vector.elementAt(0) + "%')) AND STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY INQUIRY_CODE DESC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_PURCHASE_ORDER_NO_sql = " SELECT L.NO ,L.PURCHASE_ORDER_NO,L.APPLICATION_NO,L.FINANCE_NO,TO_CHAR(NVL(L.TOTAL_NET,0),'999,999,999.99'),TO_CHAR(NVL(L.TOTAL_VAT,0),'999,999,999.99'),L.VENDOR_CODE,L.NAME,L.CLIENT_CODE,L.FULL_NAME,TO_CHAR(NVL(L.TOTAL,0),'999,999,999.99')  FROM   (SELECT ROWNUM NO,P.PURCHASE_ORDER_NO,P.APPLICATION_NO,P.FINANCE_NO,P.TOTAL_NET,P.TOTAL_VAT,P.VENDOR_CODE,P.NAME,P.CLIENT_CODE,P.FULL_NAME,P.TOTAL  FROM(  SELECT  PURCHASE_ORDER_NO,  A.APPLICATION_NO,  FINANCE_NO , TOTAL_NET,  TOTAL_VAT,  VENDOR_CODE,  NAME,  D.CLIENT_CODE,  FULL_NAME,  (TOTAL_NET+TOTAL_VAT)AS TOTAL  FROM " + m_schema_name + ".AF_CR_PRO_PURCHASE_ORDER A," + m_schema_name + ".AF_CO_MAS_VENDORS B," + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS C," + m_schema_name + ".AF_CO_MAS_CLIENT D " + " WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('%" + vector.elementAt(0) + "%') AND A.ACTIVE_STATUS=('%" + vector.elementAt(1) + "') AND A.APPLICATION_NO=C.APPLICATION_NO  " + "\tAND B.VENDOR_CODE=A.VENDER_CODE " + "\tAND D.CLIENT_CODE=C.CLIENT_CODE " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_APP_NO_sql = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.DISTRICT_CODE  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, " + " CO_APPLICANT," + " " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, " + " DISTRICT_CODE, " + " INQUARY_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_APP_NO_sql_1 = " SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,L.APPLICANT_NAME,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'-') INQUARY_NO,INQUARY_NO  FROM   (SELECT ROWNUM NO,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO  FROM(  SELECT  APPLICATION_NO,  FACILITY_NO,  CLIENT_CODE,  " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) APPLICANT_NAME, " + " CO_APPLICANT," + " " + m_schema_name + ".AF_CO_GET_CLIENT_NAME(CO_APPLICANT) CORE_APPLICANT_NAME, " + " INQUARY_NO " + " FROM " + m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS " + " WHERE APPLICATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		m_help_TXT_VENDER_CODE_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE  FROM(  SELECT  VENDOR_CODE, NAME,  CATEGORY,  TYPE, DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE VENDOR_CODE LIKE UPPER('%" + vector.elementAt(0) + "%')  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY VENDOR_CODE ASC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; //optimize by prabash on03-02-2012
		// m_help_TXT_ASSET_ID_sql2 = " SELECT L.NO,L.ASSET_ID,/* L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, */L.MODEL_CODE,L.SUB_MODEL_CODE,/* L.PRICING_NO, */L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,/*L.ADDRESS,L.CITY_CODE,L.PERIOD,*/L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE FROM   (SELECT ROWNUM NO,P.ASSET_ID,/* P.REG_NO,P.REG_DATE, */P.MODEL_CODE,P.SUB_MODEL_CODE,/* P.PRICING_NO, */P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,/*P.ADDRESS,P.CITY_CODE,P.PERIOD,*/P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE FROM(  SELECT  ASSET_ID,  MODEL_CODE,  SUB_MODEL_CODE,  STATUS,  SUPPLIER_CODE,  QTY,  COST,  PURPOSE,  APPLICATION_NO,  " + m_schema_name + ".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE," + " " + m_schema_name + ".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE) ITEM_CAT_CODE" + " FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS " + " WHERE ASSET_ID LIKE UPPER('" + vector.elementAt(0) + "%') AND APPLICATION_NO = '" + vector.elementAt(1) + "'" + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; 
		
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//-------modified : delanjali----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//-------date			: 2007-06-18----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//-------reason		: ref.317 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		m_help_TXT_ASSET_ID_sql2 = " SELECT L.NO,L.ASSET_ID,L.MODEL_CODE,L.MODEL_DESC,   L.SUB_MODEL_CODE,L.SUB_MODEL_DESC,L.STATUS,L.SUPPLIER_CODE,L.QTY,L.COST,L.PURPOSE,L.APPLICATION_NO,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.MAKE_DESC , L.ITEM_SUB_DESC  "+
			" FROM   (SELECT ROWNUM NO,P.ASSET_ID,P.MODEL_CODE,P.MODEL_DESC, P.SUB_MODEL_CODE,P.SUB_MODEL_DESC,P.STATUS,SUPPLIER_CODE,P.QTY,P.COST,P.PURPOSE,P.APPLICATION_NO,P.FUEL_TYPE,P.ITEM_CAT_CODE ,P.MAKE_DESC , P.ITEM_SUB_DESC "+
			" FROM(  SELECT  A.ASSET_ID,  A.MODEL_CODE,  A.SUB_MODEL_CODE,  A.STATUS,  A.SUPPLIER_CODE,  A.QTY,  A.COST,  A.PURPOSE,  A.APPLICATION_NO,  " + m_schema_name + ".AF_CO_GET_FUEL_TYPE(A.MODEL_CODE) FUEL_TYPE," + m_schema_name + ".AF_CO_GET_ITEM_CATEGORY_CODE(A.MODEL_CODE) ITEM_CAT_CODE, "+
			" ( SELECT B.DESCRIPTION FROM " + m_schema_name + ".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC, "+
			" ( SELECT C.DESCRIPTION FROM " + m_schema_name + ".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE ) SUB_MODEL_DESC "+
			" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
			" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC   "+ //added by nuwan de silva on 21-12-07
			" FROM " + m_schema_name + ".AF_CO_PRO_APP_ASSET_DETAILS A "+
			" WHERE ASSET_ID LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			" AND ACTIVE_STATUS =('" + vector.elementAt(2) + "') "+
			" AND APPLICATION_NO = '" + vector.elementAt(1) + "')P)L "+
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " "; 
		
		
		
		
		m_help_TXT_DISTRICT_CODE_sql1 = " SELECT L.NO ,NVL(L.DISTRICT_CODE,'-') DISTRICT_CODE ,NVL(L.DISTRICT_DESC,'-') DISTRICT_DESC ,NVL(L.PROVINCE_CODE,'-') PROVINCE_CODE, NVL(L.DEFAULT_VALUE,'-') DEFAULT_VALUE   FROM   (SELECT ROWNUM NO,P.DISTRICT_CODE,P.DISTRICT_DESC,P.PROVINCE_CODE,P.DEFAULT_VALUE  FROM(  SELECT  DISTRICT_CODE, DISTRICT_DESC,  PROVINCE_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_DISTRICT " + " WHERE (UPPER(DISTRICT_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(DISTRICT_DESC) LIKE UPPER('%" + vector.elementAt(0) + "%'))  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		m_help_TXT_VENDOR_CODE_sql = " SELECT L.NO ,L.VENDOR_CODE,L.NAME VENDOR_NAME,l.CATEGORY,L.TYPE,L.DEFAULT_VALUE  FROM   (SELECT ROWNUM NO,P.VENDOR_CODE,p.NAME,P.CATEGORY,p.TYPE,P.DEFAULT_VALUE  FROM(  SELECT  VENDOR_CODE, NAME,  CATEGORY,  TYPE, DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDORS " + " WHERE (UPPER(VENDOR_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(NAME) LIKE UPPER('%" + vector.elementAt(0) + "%'))  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + " ORDER BY VENDOR_CODE ASC " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		
		//Added by Dineth on 2008-11-03
		m_help_TXT_CHK_NO_sql=" SELECT "+
			" L.NO,L.POD_REF_NO,L.CHEQUE_NO,"+
			" L.PAYER_BRANCH_CODE,L.CLIENT_CODE "+
			" FROM ("+
			" SELECT ROWNUM NO,P.POD_REF_NO,P.CHEQUE_NO,P.PAYER_BRANCH_CODE,P.CLIENT_CODE "+
			" FROM ("+
			" SELECT A.POD_REF_NO,A.CHEQUE_NO,A.PAYER_BRANCH_CODE,A.CLIENT_CODE "+
			" FROM " + m_schema_name + ".AF_RE_PRO_POD_CHEQUES A "+
			" WHERE UPPER(A.CHEQUE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			" AND A.STATUS IN ('INV','APP'))P)L "+
			" WHERE L.NO >= " + s1 + " AND L.NO<= " + s2 + " ";
		//end by Dineth on 2008-11-03
		
		m_help_vendor_proforma_invoice=" SELECT "+
			" L.NO,L.VENDOR_CODE,L.NAME,L.BRANCH "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.VENDOR_CODE,P.NAME,P.BRANCH"+
			" FROM ("+
			" SELECT A.VENDOR_CODE,NAME,BRANCH "+
			" FROM   " + m_schema_name + ".AF_CO_MAS_VENDORS A," + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION B "+
			" WHERE  A.VENDOR_CODE=B.VENDOR_CODE "+
			" AND (UPPER(A.VENDOR_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%')"+
			" OR UPPER(NAME)            LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			" OR UPPER(BRANCH)          LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			" OR A.VAT_REG_NO          LIKE UPPER('%" + vector.elementAt(0) + "%') "+//Added by Jithendra 29-11-2016 for SR# 22372
			" ) "+
			" AND A.ACTIVE_STATUS=('" + vector.elementAt(1) + "')  "+
			" AND B.ACTIVE_STATUS=('" + vector.elementAt(1) + "')  "+
			"  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";	
		
		
		m_help_TXT_LOCATION_CODE_sql = " SELECT L.NO ,L.LOCATION_CODE,L.LOCATION_DESC,L.ADDRESS1,NVL(L.ADDRESS2,'N/A') AS ADDRESS2,L.CITY_CODE,NVL(L.POSTAL_CODE,'N/A') AS POSTAL_CODE ,NVL(L.COUNTRY_CODE,'N/A') AS COUNTRY_CODE FROM   (SELECT ROWNUM NO,P.LOCATION_CODE,P.LOCATION_DESC,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.POSTAL_CODE,P.COUNTRY_CODE  FROM(  SELECT  LOCATION_CODE,  LOCATION_DESC,  ADDRESS1,   ADDRESS2,  CITY_CODE,  POSTAL_CODE,  COUNTRY_CODE  FROM " + m_schema_name + ".AF_CO_MAS_LOCATION " + " WHERE (LOCATION_CODE LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(LOCATION_DESC) LIKE UPPER('%" + vector.elementAt(0) + "%') )  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		//    m_help_TXT_MAS_VENDOR_LOCATION_sql = " SELECT L.NO,L.BRANCH,L.LOCATION_CODE,L.VENDOR_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.ADDRESS,L.CITY_CODE FROM   (SELECT ROWNUM NO,P.BRANCH,P.LOCATION_CODE,P.VENDOR_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.ADDRESS,P.CITY_CODE  FROM(  SELECT  BRANCH,  LOCATION_CODE,  VENDOR_CODE,   TITLE,  FIRST_NAME,  LAST_NAME,  ID_NO,  ADDRESS,  CITY_CODE,  DEFAULT_VALUE  FROM " + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION " + " WHERE (BRANCH LIKE UPPER('" + vector.elementAt(0) + "%') OR UPPER(VENDOR_CODE) LIKE UPPER('" + vector.elementAt(0) + "%') )  AND ACTIVE_STATUS=('" + vector.elementAt(1) + "') " + "  )P)L  " + " WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";
		
		m_help_TXT_MAS_VENDOR_LOCATION_sql = 
			" SELECT L.NO,L.BRANCH,L.ADDRESS,L.VENDOR_CODE,L.TITLE,L.FIRST_NAME,L.LAST_NAME,L.ID_NO,L.LOCATION_CODE,L.CITY_CODE "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.BRANCH,P.ADDRESS,P.VENDOR_CODE,P.TITLE,P.FIRST_NAME,P.LAST_NAME,P.ID_NO,P.LOCATION_CODE,P.CITY_CODE "+
			" FROM "+
			" ( SELECT  BRANCH,  ADDRESS ,  VENDOR_CODE,   TITLE,  FIRST_NAME,  LAST_NAME,  ID_NO,  LOCATION_CODE,  CITY_CODE,  DEFAULT_VALUE "+
			" FROM " + m_schema_name + ".AF_CO_MAS_VENDOR_LOCATION " +
			" WHERE UPPER(VENDOR_CODE)=UPPER('" + vector.elementAt(1) + "') AND (BRANCH LIKE UPPER('%" + vector.elementAt(0) + "%') OR UPPER(VENDOR_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') ) "+
			" AND ACTIVE_STATUS=('" + vector.elementAt(2) + "') " + 
			"  )P)L  " +
			" WHERE L.NO>=  " + s1 + "  AND L.NO<=  " + s2 + " ";	
		
		//Modified by Mahela on 18-05-2007
		m_help_TXT_VALUATION_NO2_sql = 
			" SELECT L.NO ,L.VALUATION_NO,L.PRO_INVOICE_NO,L.ASSET_ID,L.SUB_MODEL_CODE,L.REG_NO,L.ENGINE_NO, "+
			" L.CHASSIS_NO,L.COLOUR, L.MODEL_CODE,L.NOTES,L.REMARKS,TO_CHAR(L.VALUATION_DATE,'DD-MM-YYYY') AS VALUATION_DATE , "+
			" L.VALUE,L.TYPE_OF_BODY,L.DATE_OF_REG,L.METER_READING, L.ACTIVE_STATUS,L.GENERAL_INDEX,L.SEATING_CAPACITY,L.NO_OF_CYLINDERS,L.FUEL_TYPE,L.ITEM_CAT_CODE,L.VALUER_CODE,l.amt,L.YEAR_OF_MANUFACTURE,L.CONDITION_OF_ASSET,L.FORCED_SALES_VALUE,NVL(L.VALUER_NAME,' '),L.SUB_MODEL_DESC "+
			" ,L.MAKE_DESC,L.ITEM_SUB_DESC"+
			" FROM "+
			" (SELECT ROWNUM NO,P.VALUATION_NO,P.PRO_INVOICE_NO,P.ASSET_ID,P.SUB_MODEL_CODE,P.REG_NO,P.ENGINE_NO, "+
			" P.CHASSIS_NO,P.COLOUR,\tP.MODEL_CODE,P.NOTES,P.REMARKS,P.VALUATION_DATE,P.VALUE,P.TYPE_OF_BODY,P.DATE_OF_REG, "+
			" P.METER_READING, P.ACTIVE_STATUS,P.GENERAL_INDEX,P.SEATING_CAPACITY,P.NO_OF_CYLINDERS,P.FUEL_TYPE,P.ITEM_CAT_CODE,P.VALUER_CODE,p.amt,P.YEAR_OF_MANUFACTURE,P.CONDITION_OF_ASSET,P.FORCED_SALES_VALUE,P.VALUER_NAME,P.SUB_MODEL_DESC  "+
			" ,P.MAKE_DESC,P.ITEM_SUB_DESC"+
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
			" (select SUM(valuer_amount) from " + m_schema_name + ".AF_CO_MAS_VALUERS where valuer_code=VALUER_CODE) as amt, "+
			"	NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
			"	NVL(CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
			"	NVL(FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+ 
			//--------MODIFIED BY : DELANJALI-------------------------
			//--------DATE				: 2007-07-11------------------------
			" "+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE) VALUER_NAME ,"+
			//-----------------------------------------------------------------
			//" (SELECT DISTINCT X.FIRST_NAME FROM " + m_schema_name + ".AF_CO_MAS_VALUERS X WHERE X.VALUER_CODE=VALUER_CODE) VALUER_NAME "+
			//________ added by nuwan de silva on 17-12-2007 ___________________________
			//" NVL("+m_schema_name+".AF_CO_GET_MODEL_DESC(MODEL_CODE),' ' ) MODLE_DESC  "+ //added by nuwan de silva on 17-12-2007
			" (SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=SUB_MODEL_CODE) SUB_MODEL_DESC "+
			" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(MODEL_CODE)),' ') MAKE_DESC  "+ //added by nuwan de silva on 21-12-07
			" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(MODEL_CODE),' ') ITEM_SUB_DESC   "+
			" FROM " + m_schema_name + ".AF_CO_PRO_APP_VALUATION " +
			" WHERE VALUATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%')  "+
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
			" WHERE VALUATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%')  "+
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
			" WHERE VALUER_CODE LIKE UPPER('%"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		//mili
		m_help_TXT_APPLICATION_NO_MOD=
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
			//" ORDER BY A.APPLICATION_NO ASC"+
			"  )P )L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";	
		
		
		//MODIFIED BY NUWAN DE SILVa 12-07-07
		m_help_TXT_APPLICATION_NO=	
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.FINANCE_NO FinanceNo,L.BROKER_CODE, L.RE_FIN_NO, L.RE_FIN_CLOSE_AMNT, L.ARR_AMOUNT, L.PLEDGE_CONTRACT "+
			//" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,NVL(L.FINANCE_NO,'-') FinanceNo,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			//" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.BROKER_CODE "+
			" FROM  "+ 
			" (SELECT ROWNUM NO,P.INSURANCE_OFFICER,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.FINANCE_NO,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE,P.INSURANCE_DONE_BY,P.PRIORITY,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.REMARK,P.BRANCH_CODE,P.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,P.LEAD_SOURCE_CATEGORY,P.LEAD_SOURCE_NAME,P.DIVISION_CODE,P.MK_OFFICER_ID,P.REG_NO,P.BROKER_CODE, P.RE_FIN_NO, P.RE_FIN_CLOSE_AMNT, P.ARR_AMOUNT, P.PLEDGE_CONTRACT "+
			" FROM( "+ 
			" SELECT "+
			//" DISTINCT A.APPLICATION_NO, "+ // commented by udara 11-03-2019
			" A.APPLICATION_NO, "+ // added by udara 11-03-2019
			" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
			" A.CLIENT_CODE, "+
			" A.FINANCE_NO, "+
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
			
			//" "+m_schema_name+".AF_CO_GET_CLIENT_CODE(PRE_APPLICATION_NO) TER_CLI ,"+ // commented by udara 17-07-2017
			" '-' TER_CLI, "+ // added by udara 17-07-2017 
			
			" NVL(A.LEAD_SOURCE_CATEGORY,'N/A') LEAD_SOURCE_CATEGORY ,"+ //added by nuwan de silva on 19-11-2007
			" NVL(A.LEAD_SOURCE_NAME,' ') LEAD_SOURCE_NAME, "+	           //added by nuwan de silva on 19-11-2007
			" NVL(A.DIVISION_CODE,' ') DIVISION_CODE, "+          //added by nuwan de silva on 27-11-2007
			" "+m_schema_name+".af_co_get_MK_OFFICER_ID(A.INQUARY_NO) MK_OFFICER_ID, "+ //Added by Chandana on 30/11/2007
			"  NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO),'-')REG_NO, "+ //added by prabash on 22-03-2012
			//"  NVL(A.FINANCE_NO,'-') FINANCE_NO, "+    //added by prabash on 13-07-2012
			" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE  "+  // added by udara on 16-08-2013
			//" ,A.RE_FIN_NO, NVL("+m_schema_name+".AF_CO_GET_CLOSE_REC_AMNT(A.RE_FIN_NO),0) RE_FIN_CLOSE_AMNT, "+ // added by udara 07-05-2014 // commented by udara 20-02-2017
			" ,A.RE_FIN_NO, NVL("+m_schema_name+".AF_CO_GET_REFIN_REC_AMOUNT(A.RE_FIN_NO),0) RE_FIN_CLOSE_AMNT, "+ // added by udara 20-02-2017
			//" NVL("+m_schema_name+".AF_GET_TOTAL_ARREARS("+m_schema_name+".AF_CO_GET_FINANCE_NO(A.PRE_APPLICATION_NO),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'LAKDLALL'),0) ARR_AMOUNT  "+ //  added by udara 01-01-2016
			" NVL("+m_schema_name+".AF_CO_GET_BAL_TRANSFER("+m_schema_name+".AF_CO_GET_FINANCE_NO(A.PRE_APPLICATION_NO),TO_CHAR(SYSDATE,'DD-MM-YYYY'),'LAKDLALL'),0) ARR_AMOUNT  "+ //  added by udara 08-02-2016
			//" NVL("+m_schema_name+".af_co_get_arreas_3(A.RE_FIN_NO),0) ARR_AMOUNT "+  //  added by udara 01-01-2016
			" ,NVL("+m_schema_name+".AF_CO_GET_PLEDGE_CONTRACT(A.APPLICATION_NO,NULL),'-') PLEDGE_CONTRACT "+ // added by udara 09-10-2018
			" ,NVL("+m_schema_name+".AF_CO_GET_VENDOR_CODE_HG(A.APPLICATION_NO),'-') VENDOR_CODE  "+ // 38 //added by kasun on 26-11-2024

			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			/*"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+ //,M.REMARK
				"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					//	" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL M "+ //comment by nuwan de silva 12-07-07
				"  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+ //AND
						//"  X.APPLICATION_NO=M.APPLICATION_NO(+) AND  "+ //comment by nuwan de silva 12-07-07
						//"	 M.STATUS='ENTERED' AND  M.STAGE='ENTER' "+ //MODIFIED BY NUWAN DE SILVA 28-05-07----  //comment by nuwan de silva 12-07-07
						//  "  AND V.ACTIVE_STATUS=('"+vector.elementAt(2)+"')  "+
				"  ) B  "+
										
				"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
				*/
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE   "+ 
			"  AND ACTIVE_STATUS NOT IN ('B')  "+ // added by udara on 06-11-2013
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			//"  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+ // commented by udara 11-03-2019
			"  A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+ // added by udara 11-03-2019
			"  B.TEL_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO)) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //Added by Prabash on 22-03-2012
			"  B.BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  "+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"  A.APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('"+vector.elementAt(1)+"','"+vector.elementAt(2)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ s1+"  AND L.NO<= "+s2+" ";
		
		
		
		m_help_TXT_APPLICATION_NOCK=	
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,NVL(L.FINANCE_NO,'-') FinanceNo,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.BROKER_CODE "+
			//" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,NVL(L.FINANCE_NO,'-') FinanceNo,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			//" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.BROKER_CODE "+
			" FROM  "+ 
			" (SELECT ROWNUM NO,P.INSURANCE_OFFICER,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.FINANCE_NO,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE,P.INSURANCE_DONE_BY,P.PRIORITY,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.REMARK,P.BRANCH_CODE,P.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,P.LEAD_SOURCE_CATEGORY,P.LEAD_SOURCE_NAME,P.DIVISION_CODE,P.MK_OFFICER_ID,P.REG_NO,P.BROKER_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.APPLICATION_NO, "+
			" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
			" A.CLIENT_CODE, "+
			
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') APPLICANT_NAME, "+
			" A.FINANCE_NO, "+
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
			//"  NVL(A.FINANCE_NO,'-') FINANCE_NO, "+    //added by prabash on 13-07-2012
			" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE  "+  // added by udara on 16-08-2013
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			/*"(SELECT X.APPLICATION_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO "+ //,M.REMARK
				"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X,"+m_schema_name+".AF_CO_MAS_CLIENT V  "+
					//	" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL M "+ //comment by nuwan de silva 12-07-07
				"  WHERE V.CLIENT_CODE=X.CLIENT_CODE   "+ //AND
						//"  X.APPLICATION_NO=M.APPLICATION_NO(+) AND  "+ //comment by nuwan de silva 12-07-07
						//"	 M.STATUS='ENTERED' AND  M.STAGE='ENTER' "+ //MODIFIED BY NUWAN DE SILVA 28-05-07----  //comment by nuwan de silva 12-07-07
						//  "  AND V.ACTIVE_STATUS=('"+vector.elementAt(2)+"')  "+
				"  ) B  "+
										
				"  WHERE A.APPLICATION_NO =B.APPLICATION_NO  "+
				*/
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE   "+ 
			"  AND ACTIVE_STATUS NOT IN ('B')  "+ // added by udara on 06-11-2013
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO)) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //Added by Prabash on 22-03-2012
			"  B.BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  "+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"  A.APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('"+vector.elementAt(1)+"','"+vector.elementAt(2)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			"   )P)L  "+
			"  WHERE L.NO>=  "+ s1+"  AND L.NO<= "+s2+" ";
		
		//added milinda 2013-10-17
		m_help_TXT_APPLICATION_NO_new=	
			
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FACILITY_NO,'-') FACILITY_NO,L.APPLICANT_NAME,L.FINANCE_NO FinanceNo,L.MK_OFFICER,NVL(L.CLIENT_CODE,'-') CLIENT_CODE,NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE,L.TRANSACTION_TYPE,L.INSURANCE_DONE_BY,L.PRIORITY,L.FULL_NAME,L.TEL_NO,L.NIC_NO,L.REMARK ,L.BRANCH_CODE,L.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,L.LEAD_SOURCE_CATEGORY,L.LEAD_SOURCE_NAME,L.DIVISION_CODE,L.MK_OFFICER_ID, L.INSURANCE_OFFICER,L.REG_NO Vehicle_No,L.BROKER_CODE "+
			" FROM  "+ 
			" (SELECT ROWNUM NO,P.INSURANCE_OFFICER,P.APPLICATION_NO,P.FACILITY_NO,P.CLIENT_CODE,P.FINANCE_NO,P.APPLICANT_NAME,P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER,P.TRANSACTION_TYPE,P.INSURANCE_DONE_BY,P.PRIORITY,P.FULL_NAME,P.TEL_NO,P.NIC_NO,P.REMARK,P.BRANCH_CODE,P.BRANCH_DESC, "+
			
			" TER_DESC,TERMINATION_NO,PRE_APPLICATION_NO,TER_TYPE,TER_AMT,TER_CLI,P.LEAD_SOURCE_CATEGORY,P.LEAD_SOURCE_NAME,P.DIVISION_CODE,P.MK_OFFICER_ID,P.REG_NO,P.BROKER_CODE "+
			" FROM( "+ 
			" SELECT "+
			" DISTINCT A.APPLICATION_NO, "+
			" NVL(A.FACILITY_NO,'-') FACILITY_NO, "+
			" A.CLIENT_CODE, "+
			" NVL(A.FINANCE_NO,'-') FINANCE_NO, "+
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
			//"  NVL(A.FINANCE_NO,'-') FINANCE_NO, "+    //added by prabash on 13-07-2012
			" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE  "+  // added by udara on 16-08-2013
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+m_schema_name+".AF_CO_MAS_CLIENT B  "+
			
			"  WHERE A.CLIENT_CODE=B.CLIENT_CODE   "+ 
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS (A.FINANCE_NO)) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+ //Added by Prabash on 22-03-2012
			"  B.BUSINESS_CERTIFICATE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  "+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(A.INQUARY_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"  A.APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			
			"  )  "+
			"  AND A.APPLICATION_STATUS IN('ACTIVATED')  "+
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
			" WHERE (UPPER(LEAD_SOURCE_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') and LEAD_SOURCE_NAME IS NOT NULL AND  LEAD_SOURCE_NAME NOT IN ('-')) OR UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('%"+vector.elementAt(0)+"%')   "+
			//" AND ACTIVE_STATUS='Y' " +
			" ORDER BY LEAD_SOURCE_NAME ASC "+
			"  )P)L  "+
			" WHERE L.NO>= "+ s1+"  AND L.NO<="+s2+" ";
		
		
		
		//Modified Nuwan De Silva----------23-04-07-----------------------------------------------------
		//	Add Serarch Facilities-----------------------------
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
			" WHERE ( UPPER(BANK_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  OR "+
			"         UPPER(BRANCH_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  OR "+
			"         UPPER(BRANCH_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')  OR "+
			"         UPPER(ADDRESS1) LIKE UPPER('%"+vector.elementAt(0)+"%')  OR "+
			"         UPPER(TEL_NO) LIKE UPPER('%"+vector.elementAt(0)+"%'))   "+
			" AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
			"ORDER BY BANK_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		//------------------------------------------------------------------------------------------------------------
		m_help_TXT_FinanceSql_new =
			" SELECT P.NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,FINANCE_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT APPLICATION_NO,FINANCE_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   upper(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" ORDER BY APPLICATION_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
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
			" DEFAULT_VALUE,valuer_amount,"+
			" NVL((FIRST_NAME || ' ' || LAST_NAME),'-') VALUER_NAME "+	 //added by nuwan de silva 18-07-07
			" FROM "+m_schema_name+".AF_CO_MAS_VALUERS "+
			" WHERE (VALUER_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR FIRST_NAME LIKE UPPER('%"+vector.elementAt(0)+"%') OR LAST_NAME LIKE UPPER('%"+vector.elementAt(0)+"%')  OR ADDRESS LIKE UPPER('%"+vector.elementAt(0)+"%')  OR CITY_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR  TEL_NO LIKE UPPER('%"+vector.elementAt(0)+"%')   ) AND ACTIVE_STATUS=('"+vector.elementAt(1)+"') "+
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
			//	" AND CLIENT_CODE LIKE UPPER('%"+vector.elementAt(1)+"%') "+
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
			" WHERE VALUER_CODE LIKE UPPER('%"+vector.elementAt(0)+"%')  AND ACTIVE_STATUS=('"+vector.elementAt(1)+"')"+
			"	ORDER BY VALUER_CODE ASC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_FinanceSql_new1 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		//Added by Dineth on 20-03-2009
		m_help_TXT_FinanceSql_new3 =
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE "+
			" FROM "+
			" (SELECT FINANCE_NO,APPLICATION_NO,CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE   FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL','NORM_TERMI','TERMI','TERMINATED')"+
			" ORDER BY FINANCE_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		//End by Dineth on 20-03-2009
		
		
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
			//" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.APPLICANT_NAME APPLICANT_NAME,L.MK_OFFICER, L.REGISTRATION_NUM REGISTRATION_NUM, NVL(L.CLIENT_CODE,'-') CLIENT_CODE, NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+ //MODIFIED BY SAJITH MENDIS ON 13/09/2013
			" SELECT L.NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,NVL(L.FINANCE_NO,'-') FINANCE_NO,L.APPLICANT_NAME APPLICANT_NAME, L.REGISTRATION_NUM REGISTRATION_NUM, L.MK_OFFICER, NVL(L.CLIENT_CODE,'-') CLIENT_CODE, NVL(L.CO_APPLICANT,'-') CO_APPLICANT ,L.CORE_APPLICANT_NAME,NVL(L.INQUARY_NO,'') INQUARY_NO,L.CLIENT_TYPE "+ //MODIFIED BY SAJITH MENDIS ON 13/09/2013
			" FROM  "+
			" (SELECT ROWNUM NO,P.APPLICATION_NO,P.FINANCE_NO,P.CLIENT_CODE,P.APPLICANT_NAME, P.REGISTRATION_NUM, P.CO_APPLICANT,P.CORE_APPLICANT_NAME,P.INQUARY_NO,P.CLIENT_TYPE,P.MK_OFFICER "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO , "+
			" FINANCE_NO , "+
			" CLIENT_CODE , "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME, "+
			//" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(APPLICATION_NO),' ') REGISTRATION_NUM, "+ // commented by udara 03-01-2013
			" "+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS_2(FINANCE_NO) REGISTRATION_NUM, "+ // added by udara 03-01-2013
			" CO_APPLICANT ,"+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CO_APPLICANT),'-') CORE_APPLICANT_NAME, "+
			//  " DISTRICT_CODE, "+
			" NVL(INQUARY_NO,'-') INQUARY_NO, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_TYPE(CLIENT_CODE),'-') CLIENT_TYPE, "+
			" NVL("+m_schema_name+".AF_CO_GET_MK_OFFICER_NAME(INQUARY_NO),'-') MK_OFFICER "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
			" WHERE (APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%')  OR "+
			" FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),' ')) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR UPPER(NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO(APPLICATION_NO),' ')) LIKE UPPER('%"+vector.elementAt(0)+"%') )"+
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+  // OR APPLICATION_STATUS=('"+vector.elementAt(2)+"')
			//" GROUP BY A.APPLICATION_NO,A.FINANCE_NO,A.CLIENT_CODE,A.CO_APPLICANT,A.INQUARY_NO "+
			" ORDER BY APPLICATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_FINANCE_NO_1 =  
			/*
			" SELECT P.NO,FINANCE_NO,APPLICATION_NO,CLIENT_CODE,CLIENT_NAME "+
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
		*/
			
			" SELECT L.NO  ,NVL(L.FINANCE_NO,'-') FINANCE_NO ,NVL(L.APPLICATION_NO,'-') APPLICATION_NO ,L.APPLICANT_NAME "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.APPLICANT_NAME  "+
			" FROM( "+ 
			" SELECT "+
			" APPLICATION_NO, "+
			" FINANCE_NO, "+
			" CLIENT_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-') APPLICANT_NAME "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			" WHERE (  UPPER(FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" OR UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+ 
			" OR UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE))  LIKE UPPER('%"+vector.elementAt(0)+"%')) "+ 
			" AND APPLICATION_STATUS=('"+vector.elementAt(1)+"')"+  // OR APPLICATION_STATUS=('"+vector.elementAt(2)+"')
			" ORDER BY APPLICATION_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		m_help_application_no_change_pro_invoice=
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
			" WHERE APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			" AND APPLICATION_STATUS IN ('VERIFYL','ACTIVATED')"+  // OR APPLICATION_STATUS=('"+vector.elementAt(2)+"')
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
			" (VALUATION_NO LIKE UPPER('5"+vector.elementAt(0)+"%') OR "+
			" APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(REG_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(ENGINE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(CHASSIS_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
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
			" (VALUATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(REG_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(ENGINE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(CHASSIS_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
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
			"CONTACT_PERSON,  NVL(SUB_PRODUCT_CODE,'-') SUB_PRODUCT_CODE,NVL(TRANSACTION_SUB_TYPE,'-') TRANSACTION_SUB_TYPE  FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
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
			" (SELECT VALUE FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE ENT_DATE= (SELECT MAX(ENT_DATE) ENT_DATE "+
			" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION WHERE APPLICATION_NO=B.APPLICATION_NO)) VALUE, "+
			" "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) SEIZER_NAME, "+
			" TO_CHAR(A.REPOSSESSED_DATE,'DD-MON-YY') REPOSSESSED_DATE "+
			" FROM "+m_schema_name+".AF_RE_PRO_REPOSSESSION A, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D , "+
			" "+m_schema_name+".AF_CO_MAS_MODEL F "+
			" WHERE A.FINANCE_NO=B.FINANCE_NO "+
			" AND B.APPLICATION_NO=C.APPLICATION_NO "+
			" AND D.CLIENT_CODE=B.CLIENT_CODE "+
			" AND C.MODEL_CODE=F.MODEL_CODE "+
			" AND (UPPER(A.REPOSSESSION_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(A.FINANCE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(D.FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(C.VEHICLE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER("+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE)) LIKE UPPER('%" + vector.elementAt(0) + "%')) "+
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
			" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			" "+m_schema_name+".AF_CO_MAS_VENDORS B, "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT D "+
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
		
		
		
		
		
		m_help_TXT_RECIEPT_NO_sql_report=
			" SELECT P.NO,P.REC_NO,P.CLIENT_CODE,P.FULL_NAME,P.SETTLE_MODE,P.EFF_VALDATE,P.CURR_CODE,P.REC_AMOUNT "+
			" FROM "+
			" (SELECT ROWNUM NO,REC_NO,CLIENT_CODE,FULL_NAME,SETTLE_MODE,EFF_VALDATE,CURR_CODE,REC_AMOUNT "+
			" FROM "+
			" (SELECT REC_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME, "+
			" NVL(SETTLE_MODE,'-') SETTLE_MODE, "+
			" TO_CHAR(A.EFF_VALDATE,'DD-MON-YY') EFF_VALDATE, "+
			" NVL(CURR_CODE,'-') CURR_CODE, "+
			" NVL(REC_AMOUNT,0) REC_AMOUNT, "+
			" NVL(EXCHANGE_RATE_REP_CURR,0) EXCHANGE_RATE_REP_CURR "+
			" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" (REC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') )))P "+
			"WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		
		m_help_TXT_TEMP_REC_NO_sql=
			" SELECT L.NO ,L.TEMP_REC_NO,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.TRN_DATE,L.AMOUNT,L.SETTELMENT_MODE,L.BRANCH_CODE,L.BANK_CODE,L.ACCOUNT_NO,L.CURR_CODE,L.EXCHANGE_RATE,L.TRN_AMOUNT_CURR,L.COLLECTION_OFFICER,L.RECEIPT_NO,L.REC_BOOK_NO,L.CHEQUE_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.TEMP_REC_NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.TRN_DATE,P.AMOUNT,P.SETTELMENT_MODE,P.BRANCH_CODE,P.BANK_CODE,P.ACCOUNT_NO,P.CURR_CODE,P.EXCHANGE_RATE,P.TRN_AMOUNT_CURR,P.COLLECTION_OFFICER,P.RECEIPT_NO,P.REC_BOOK_NO,P.CHEQUE_NO "+
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
				" WHERE TEMP_REC_NO LIKE UPPER('"+vector.elementAt(0)+"%')  AND STATUS=('"+vector.elementAt(1)+"') "+
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
			" NVL(A.CHEQUE_NO,'-') CHEQUE_NO "+
			" FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT A, "+
			"(SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_RE_PRO_TEMP_RECEIPT X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  A.TEMP_REC_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
			"  )  "+
			"  ORDER BY A.TEMP_REC_NO DESC  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		m_help_TXT_ADVER_OFFERS_NO_sql=
			"SELECT P.NO,P.OFFER_NO,P.ADVER_NO,P.FULL_NAME,P.ADDRESS,P.VEHICLE_NO,P.ADVER_DATE,P.TEL_NO,P.INVENTORY_NO "+
			"FROM "+
			"(SELECT ROWNUM NO,OFFER_NO,ADVER_NO,FULL_NAME,ADDRESS,VEHICLE_NO,ADVER_DATE,TEL_NO,INVENTORY_NO "+
			"FROM "+
			"(SELECT "+
			" OFFER_NO, "+
			" A.ADVER_NO ADVER_NO, "+
			" NVL(FULL_NAME,'-') FULL_NAME, "+
			" NVL(ADDRESS,'-') ADDRESS, "+
			" NVL(B.VEHICLE_NO,'-') VEHICLE_NO, "+
			" TO_CHAR(B.ADVER_DATE,'DD-MON-YY') ADVER_DATE, "+
			" NVL(TEL_NO,'-') TEL_NO, "+
			" NVL(A.INVENTORY_NO,'-') INVENTORY_NO "+
			" FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS A, "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL B "+
			" WHERE A.ADVER_NO=B.ADVER_NO AND "+ 
			" (OFFER_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.ADVER_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" VEHICLE_NO LIKE UPPER('%"+vector.elementAt(0)+"%'))))P "+
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_OD_INTEREST_NO_sql=
			
			" SELECT P.NO,P.ODI_REF_NO,P.INVOICE_NO,P.ODI_DATE,P.ADJUSTED_DATE,P.SETTLED_DATE "+
			" FROM "+
			" (SELECT ROWNUM NO,ODI_REF_NO,INVOICE_NO,ODI_DATE,ADJUSTED_DATE,SETTLED_DATE "+
			" FROM "+
			" (SELECT ODI_REF_NO,INVOICE_NO,TO_CHAR(ODI_DATE,'DD-MON-YY') ODI_DATE, "+
			" NVL(TO_CHAR(ADJUSTED_DATE,'DD-MON-YY'),'-') ADJUSTED_DATE,NVL(TO_CHAR(SETTLED_DATE,'DD-MON-YY'),'-') SETTLED_DATE "+
			" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
			" WHERE ODI_REF_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" INVOICE_NO LIKE UPPER('%"+vector.elementAt(0)+"%')) )P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		m_help_TXT_PAYMENT_NO_sql=
			"SELECT P.NO,P.PAYMENT_NO, P.SUS_REF_NO,P.CLIENT_CODE,P.FULL_NAME,P.SETTLE_MODE,P.LIC_BRANCH_CODE,P.LIC_ACC_NO,P.PAYEE_BRANCH_CODE "+
			" FROM "+
			" (SELECT ROWNUM NO,PAYMENT_NO, SUS_REF_NO,CLIENT_CODE,FULL_NAME,SETTLE_MODE,LIC_BRANCH_CODE,LIC_ACC_NO,PAYEE_BRANCH_CODE "+
			" FROM "+
			" (SELECT A.PAYMENT_NO,A.SUS_REF_NO SUS_REF_NO, A.CLIENT_CODE CLIENT_CODE, B.FULL_NAME FULL_NAME, "+
			" NVL(DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash'),'-') SETTLE_MODE, "+
			" NVL(A.LIC_BRANCH_CODE,'-') LIC_BRANCH_CODE, "+
			" NVL(A.LIC_ACC_NO,'-') LIC_ACC_NO, "+
			" NVL(A.PAYEE_BRANCH_CODE,'-') PAYEE_BRANCH_CODE "+
			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" where A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" (A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.SUS_REF_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.PAYMENT_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) ))P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		//Modified by Mahela on 07-05-2007
		//Purpose : Search by Finance No
		m_help_TXT_INVOICE_NO_sql_report=
			" SELECT P.NO,P.INVOICE_NO,P.FINANCE_NO,P.FULL_NAME,P.VALUE_DATE,P.INVOICE_TYPE,P.GROUP_INV_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,INVOICE_NO,FINANCE_NO,FULL_NAME,VALUE_DATE,INVOICE_TYPE,GROUP_INV_NO "+
			" FROM "+
			" (SELECT INVOICE_NO, "+
			" FINANCE_NO,FULL_NAME, "+
			" TO_CHAR(VALUE_DATE,'DD-MON-YY') VALUE_DATE, "+
			" NVL(INVOICE_TYPE,'-') INVOICE_TYPE, "+
			" NVL(GROUP_INV_NO,'-') GROUP_INV_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
			" AND ( UPPER(A.INVOICE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(A.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') OR"+
			" A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(FULL_NAME) LIKE UPPER('%%"+vector.elementAt(0)+"%') "+
			" )ORDER BY A.DUE_DATE DESC ))P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+"  ";
		
		
		
		/*	m_help_TXT_POD_NO_sql_report=	
			" SELECT P.NO,P.APPLICATION_NO,P.CLIENT_CODE,P.FULL_NAME,P.FINANCE_NO "+
			" FROM "+
			" (SELECT ROWNUM NO,APPLICATION_NO,CLIENT_CODE,FULL_NAME,FINANCE_NO "+
			" FROM "+
			" (SELECT  A.APPLICATION_NO, A.CLIENT_CODE, B.FULL_NAME, "+
			" A.FINANCE_NO, A.APPLICATION_STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
			" "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE APPLICATION_STATUS='ACTIVATED' AND "+
			" A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" ( A.CLIENT_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" A.FINANCE_NO LIKE UPPER('"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%')) ))P "+	
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" "; */
		
		
		m_help_TXT_POD_NO_sql_report=	
			" SELECT P.NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,CLIENT_CODE,FULL_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO FINANCE_NO,A.CLIENT_CODE,B.FULL_NAME "+
			" FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+  
			" WHERE FINANCE_NO IS NOT NULL AND "+
			" A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" (A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%')) ))P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_LEASE_NO_sql_report=
			" SELECT P.NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,CLIENT_CODE,FULL_NAME "+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO FINANCE_NO,A.CLIENT_CODE,B.FULL_NAME "+
			" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
			" WHERE FINANCE_NO IS NOT NULL AND "+
			" A.CLIENT_CODE=B.CLIENT_CODE AND "+
			" (A.CLIENT_CODE LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.FULL_NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" A.FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%')) ))P "+	
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		m_help_TXT_FINANCE_NO_sql_report=
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.STATUS "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO,NVL(STATUS,'-') STATUS "+
			" FROM "+
			" (SELECT  FINANCE_NO,APPLICATION_NO,NVL(STATUS,'-') STATUS "+
			" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT "+
			" WHERE FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" APPLICATION_NO LIKE UPPER('%"+vector.elementAt(0)+"%')))P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";	
		
		
		BranchSql = 	"SELECT P.NO, LOCATION_CODE, LOCATION_DESC, ADDRESS1, ADDRESS2 "+
			"FROM "+
			"(SELECT ROWNUM NO,LOCATION_CODE, LOCATION_DESC, ADDRESS1, ADDRESS2 "+
			"FROM "+
			"(SELECT LOCATION_CODE, LOCATION_DESC, ADDRESS1, ADDRESS2 "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
			"	 WHERE ACTIVE_STATUS='Y' AND "+
			"        (UPPER(LOCATION_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(LOCATION_DESC) LIKE UPPER('%"+vector.elementAt(0)+"%')) " +
			" ORDER BY LOCATION_DESC )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";		
		
		
		
		ClientSql1 = 	"SELECT P.NO, P.CLIENT_CODE Client, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS Status,CITY_CODE "+
			"FROM "+
			"(SELECT ROWNUM NO, CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL,ACTIVE_STATUS,CITY_CODE "+
			"FROM "+
			"(SELECT CLIENT_CODE, FIRST_NAME, SURNAME,NIC_NO,ADDRESS1,ADDRESS2,TEL_NO,MOBILE_NO,EMAIL, ACTIVE_STATUS, TEMP_ACTIVE_STATUS,CITY_CODE "+
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"	 WHERE (UPPER(FULL_NAME)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"					UPPER(CLIENT_CODE) LIKE ('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(ADDRESS1)   LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(CITY_CODE)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(MOBILE_NO)  LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(TEL_NO)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(EMAIL)      LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"         UPPER(NIC_NO)     LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			"	        UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')) " +
			" ORDER BY FULL_NAME )) P "+		
			"WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";		
		
		
		
		
		m_help_TXT_QUOTATION_NO_sql2 = " SELECT L.NO ,L.QUOTATION_NO,L.INQUIRY_NO,L.CLIENT_NAME,L.STATUS, "+
			"L.APPR_USER,TO_CHAR(L.APPR_DATE,'DD-MM-YYYY')AS APPR_DATE  "+
			"FROM   (SELECT ROWNUM NO,P.QUOTATION_NO,P.INQUIRY_NO, "+
			"P.CLIENT_NAME,P.STATUS,P.APPR_USER,P.APPR_DATE "+
			"FROM(  SELECT  QUOTATION_NO,\tINQUIRY_NO, CLIENT_NAME, \tA.STATUS, "+
			"APPR_USER , APPR_DATE  FROM " + m_schema_name + ".AF_MK_PRO_QUOTATION A ,"+
			"" + m_schema_name + ".AF_MK_PRO_INQUIRY B" + 
			" WHERE (QUOTATION_NO LIKE UPPER('%" + vector.elementAt(0) + "%') "+
			" and INQUIRY_NO  like ('%" + vector.elementAt(1) + "%')) "+
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
			"WHERE PRICING_NO LIKE '%" + vector.elementAt(0) + "%' AND " + 
			"(INQUIRY_NO LIKE '%" + vector.elementAt(1) + "%' OR INQUIRY_NO IS NULL ) " + 
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
			" WHERE  A.PURCHASE_ORDER_NO LIKE UPPER('%" + vector.elementAt(0) + "%') and upper(a.application_no) like UPPER('%" + vector.elementAt(1) + "%') "+
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
			"and upper(APPLICATION_NO) like upper('%"+vector.elementAt(1)+"%') "+
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
			"WHERE UPPER(SO_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			"and upper(A.FINANCE_NO) like upper('%"+vector.elementAt(1)+"%') "+
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
			" WHERE UPPER(ACCOUNT_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') AND  UPPER(A.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+vector.elementAt(2)+"')"+
			" AND A.CLIENT_CODE=B.CLIENT_CODE "+
			"order by ACCOUNT_NO DESC "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		//Added by Chandan on 21/06/2007 
		CitySql=
			" SELECT L.NO, L.CITY_CODE, L.CITY_DESC "+
			" FROM "+
			" (SELECT ROWNUM NO, CITY_CODE, CITY_DESC "+
			" FROM "+
			" (SELECT CITY_CODE, CITY_DESC "+
			" FROM   "+m_schema_name+".AF_CO_MAS_CITY "+
			" WHERE  ACTIVE_STATUS ='Y' AND "+
			" (UPPER(CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(CITY_DESC) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+ 
			" ORDER BY	DEFAULT_VALUE DESC)P)L "+		
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		//End on 21/06/2007	
		
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
			" DESIGNATION_CODE, "+ 
			//" "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') PASSWORD "+ 
			" '-' PASSWORD "+// Add by Amila 2017-02-24
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE ( UPPER(USER_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		//Added by Mahela on 25-04-2007
		/*
		m_help_TXT_MK_OFFICER_sql=
			" SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE,L.PASSWORD,L.CONTACT_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE,P.PASSWORD,P.CONTACT_NO "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
			" "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') PASSWORD, "+ 
			" NVL((SELECT CONTACT_NO FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=EMP_ID),'-') CONTACT_NO"+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE (UPPER(USER_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		*/
		
		m_help_TXT_MK_OFFICER_sql=
			" SELECT L.NO ,L.USER_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.EMP_ID,L.DIVISION_CODE,L.DESIGNATION_CODE,L.CONTACT_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.USER_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.EMP_ID,P.DIVISION_CODE,P.DESIGNATION_CODE,P.CONTACT_NO "+
			" FROM( "+ 
			" SELECT "+
			" USER_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+
			" EMP_ID, "+
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
			" NVL((SELECT CONTACT_NO FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=EMP_ID),'-') CONTACT_NO"+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE (UPPER(USER_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		//-----ADDED BY : DELANJALI-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//-----DATE			:	2007-08-03-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		m_help_TXT_INVOICE_NO_NEW_sql = " SELECT L.NO,L.INVOICE_NO,"+
			" L.APPLICATION_NO,L.ASSET_ID,L.TOTAL_AMOUNT GROSS_AMOUNT ,L.ENGINE_NO,L.CHASSIS_NO, "+
			" L.REG_NO,TO_CHAR(L.REG_DATE,'DD-MM-YYYY') AS REG_DATE, "+
			" L.PRICING_NO,L.SUB_MODEL_CODE,L.COLOUR, "+
			" L.SEATING_CAPACITY,L.NET_PRICE,L.VAT, "+
			" L.TOTAL_AMOUNT,L.TO_BE_DELIVERD_TO,L.VALUE, "+
			" L.CURR_CODE,L.MODEL_CODE,L.FUEL_TYPE, "+
			" L.INVOICE_DOC_NO,L.CITY_CODE,L.ADDRESS, "+
			" L.VENDOR_CODE,L.VENDOR_NAME,L.BRANCH_ID, "+
			" L.FUEL_CONVERTION_STATUS,TO_CHAR(L.DUE_DATE,'DD-MM-YYYY') AS DUE_DATE, "+
			" INITCAP(L.MODEL_DESC),INITCAP(L.SUB_MODEL_DESC),L.YEAR_OF_MANUFACTURE, "+
			" L.EXTRAS_INCLUDED ,"+
			" L.CITY_NAME "+
			" ,L.MAKE_DESC "+
			" ,L.ITEM_SUB_DESC "+
			" FROM   (SELECT ROWNUM NO,P.INVOICE_NO,P.APPLICATION_NO, "+
			" P.ASSET_ID,P.ENGINE_NO,P.CHASSIS_NO, "+
			" P.REG_NO,P.REG_DATE,P.PRICING_NO, "+
			" P.SUB_MODEL_CODE,P.COLOUR,P.SEATING_CAPACITY, "+
			" P.NET_PRICE,P.VAT,P.TOTAL_AMOUNT,P.TO_BE_DELIVERD_TO, "+
			" P.VALUE,P.CURR_CODE,P.MODEL_CODE,P.FUEL_TYPE, "+
			" P.INVOICE_DOC_NO,P.CITY_CODE,P.ADDRESS,P.VENDOR_CODE, "+
			" P.VENDOR_NAME,P.BRANCH_ID,P.FUEL_CONVERTION_STATUS, "+
			" P.DUE_DATE,P.MODEL_DESC,P.SUB_MODEL_DESC, "+
			" P.YEAR_OF_MANUFACTURE, "+
			" P.EXTRAS_INCLUDED, "+
			" P.CITY_NAME "+
			" ,P.MAKE_DESC "+
			" ,P.ITEM_SUB_DESC "+
			" FROM(  SELECT  A.INVOICE_NO,A.APPLICATION_NO,A.ASSET_ID, "+
			" NVL(A.ENGINE_NO,'-') ENGINE_NO,NVL(A.CHASSIS_NO,'-') CHASSIS_NO, "+
			" A.REG_NO,A.REG_DATE,A.PRICING_NO,A.SUB_MODEL_CODE, "+
			" NVL(A.COLOUR,'-') COLOUR ,A.SEATING_CAPACITY,A.NET_PRICE, "+
			" A.VAT,A.TOTAL_AMOUNT,A.TO_BE_DELIVERD_TO,A.VALUE, "+
			" A.CURR_CODE,A.MODEL_CODE,"+m_schema_name+".AF_CO_GET_FUEL_TYPE(MODEL_CODE) FUEL_TYPE, "+
			" NVL(A.INVOICE_DOC_NO,'N/A') INVOICE_DOC_NO,NVL(A.CITY_CODE,' ') CITY_CODE, "+
			" NVL(A.ADDRESS,'-') ADDRESS,NVL(A.VENDOR_CODE,'-') VENDOR_CODE, "+
			" NVL( (SELECT B.NAME FROM   "+m_schema_name+".AF_CO_MAS_VENDORS B WHERE B.VENDOR_CODE=A.VENDOR_CODE),'N/A') VENDOR_NAME , "+
			" NVL(A.BRANCH_ID,'-')  BRANCH_ID,A.FUEL_CONVERTION_STATUS FUEL_CONVERTION_STATUS, "+
			" A.DUE_DATE, "+  
			" (SELECT B.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_MODEL B WHERE A.MODEL_CODE=B.MODEL_CODE) MODEL_DESC,  "+
			" (SELECT C.DESCRIPTION FROM  "+m_schema_name+".AF_CO_MAS_SUB_MODLE C WHERE C.SUB_CODE=A.SUB_MODEL_CODE) SUB_MODEL_DESC, "+
			" A.YEAR_OF_MANUFACTURE,  "+
			" A.EXTRAS_INCLUDED, "+
			" NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),' ') CITY_NAME  "+ //added by nuwan de silva on 19-09-07
			" ,NVL("+m_schema_name+".AF_CO_GET_MAKE_DESC("+m_schema_name+".AF_CO_GET_MAKE_CODE(A.MODEL_CODE)),' ') MAKE_DESC  "+
			" ,NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_DESC(A.MODEL_CODE),' ') ITEM_SUB_DESC   "+
			
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A WHERE A.INVOICE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') AND "+
			" A.APPLICATION_NO=UPPER('"+vector.elementAt(1)+"') AND A.ACTIVE_STATUS=('"+vector.elementAt(2)+"'))P)L "+
			" WHERE L.NO>=" +s1+ "    AND L.NO<=" +s2+ "  ";
		
		
		PriceSql_invoice_new = "SELECT NO,PRICING_NO, INQUIRY_NO, GROSS_AMOUNT,TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,      ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET   FROM (SELECT ROWNUM NO,PRICING_NO, INQUIRY_NO, TRANSACION_TYPE,    \tTRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE, SUPPLIER,      BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM (SELECT PRICING_NO, INQUIRY_NO, TRANSACION_TYPE, TRN_SUB_TYPE,PERIOD,      PAYMENT_INTERVAL, RATE, GROSS_AMOUNT, VAT_AMOUNT,     NET_AMOUNT, NIBSM, AMI, RESIDUAL_VALUE,     ITEM_CATEGORY, ITEM_SUB_CAT_CODE,     MAKE_CODE, MODEL_CODE, SUB_MODEL_CODE, ASSET_USAGE_TYPE,     VAT_PERCENTAGE, ENGINE_CAPACITY, FUEL_TYPE, TARE,     MAINTENANCE_STATUS,      OUTFLOW_PATTERN, SUPPLIER_CREDIT,PAYMENT_MODE,     CURRENCY_CODE,  SUPPLIER,     BUY_BACK,     INTEREST_TYPE, VARIABLE_INT_BASE, INT_MARGIN,     CONDITION_OF_ASSET  FROM " + m_schema_name + ".AF_MK_PRO_PRICING A " + " WHERE PRICING_NO LIKE '%" + vector.elementAt(0) + "%' /*AND PRICING_STATUS=('" + vector.elementAt(2) + "')*/ " + " AND APP_NO ='" + vector.elementAt(1) + "' " + " ORDER BY ENT_DATE DESC)) P " + " WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		
		/*
		m_help_TXT_MK_OFFICER_sql_new=
			" SELECT L.NO ,L.EMP_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.DIVISION_CODE,L.DESIGNATION_CODE,L.PASSWORD,L.CONTACT_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.DIVISION_CODE,P.DESIGNATION_CODE,P.PASSWORD,P.CONTACT_NO "+
			" FROM( "+ 
			" SELECT "+
			" EMP_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+      
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
			" "+m_schema_name+".AF_CO_DATA(PASSWORD,'ECONV') PASSWORD, "+ 
			" NVL((SELECT CONTACT_NO FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=EMP_ID),'-') CONTACT_NO"+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE (UPPER(EMP_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		*/
		
		m_help_TXT_MK_OFFICER_sql_new=
			" SELECT L.NO ,L.EMP_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.DIVISION_CODE,L.DESIGNATION_CODE,L.CONTACT_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.DIVISION_CODE,P.DESIGNATION_CODE,P.CONTACT_NO "+
			" FROM( "+ 
			" SELECT "+
			" EMP_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+      
			" DIVISION_CODE, "+
			" DESIGNATION_CODE, "+ 
			" NVL((SELECT CONTACT_NO FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=EMP_ID),'-') CONTACT_NO"+
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE (UPPER(EMP_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		
		
		// added by udara 28-11-2014
		m_help_TXT_USER_ID_new_sql=
			" SELECT L.NO ,L.EMP_ID,L.NAME,L.LOCATION_CODE,L.USER_TYPE,L.DIVISION_CODE,L.DESIGNATION_CODE "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.EMP_ID,P.NAME,P.LOCATION_CODE,P.USER_TYPE,P.DIVISION_CODE,P.DESIGNATION_CODE "+
			" FROM( "+ 
			" SELECT "+
			" EMP_ID, "+
			" NAME, "+
			" LOCATION_CODE, "+
			" USER_TYPE, "+     
			" DIVISION_CODE, "+
			" DESIGNATION_CODE "+ 
			" FROM "+m_schema_name+".CO_CO_MAS_USER "+
			" WHERE ( UPPER(EMP_ID) LIKE UPPER('%"+vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') )  AND (ACTIVE_STATUS=('"+vector.elementAt(1)+"') OR ACTIVE_STATUS=('"+vector.elementAt(2)+"') )"+
			"  )P)L  "+
			" WHERE L.NO>=  "+s1+"  AND L.NO<=  "+s2+" ";
		
		
		m_help_TXT_FIN_SQL =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.NAME "+
			" FROM "+
			" (SELECT ROWNUM NO,FINANCE_NO,APPLICATION_NO ,CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE) NAME"+
			" FROM "+
			" (SELECT DISTINCT A.FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE  "+
			" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B"+
			" WHERE  A.APPLICATION_NO = B.APPLICATION_NO "+
			" AND B.ACTIVE_STATUS='T' "+
			" AND (A.FINANCE_NO LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" OR UPPER(A.APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')) "+
			" ORDER BY A.APPLICATION_NO DESC)) P "+
			" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
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
			" WHERE  (UPPER(CLIENT_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(FULL_NAME)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " +
			" UPPER(ADDRESS1)   LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(CITY_CODE)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(MOBILE_NO)  LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(TEL_NO)     LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(EMAIL)      LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(NIC_NO)     LIKE UPPER('%" + vector.elementAt(0) + "%') OR " + 
			" UPPER(BUSINESS_CERTIFICATE_NO) LIKE UPPER('%" + vector.elementAt(0) + "%')) "+ 
			" AND ACTIVE_STATUS = 'Y' " + 
			" ORDER BY CLIENT_CODE DESC "+
			" )) P " + 
			" WHERE P.NO>= " + s1 + " AND P.NO<= " + s2 + " ";
		
		// Added by Waruna on 201204-25 get from multi
		m_help_TXT_PROVINCE_sql=
			" SELECT L.NO ,L.PROVINCE_CODE,L.PROVINCE_DESC "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.PROVINCE_CODE,P.PROVINCE_DESC "+
			" FROM( "+ 
			" SELECT "+
			" PROVINCE_CODE,"+
			" PROVINCE_DESC"+
			" FROM "+m_schema_name+".AF_CO_MAS_PROVINCE "+
			" WHERE (PROVINCE_CODE LIKE UPPER('"+vector.elementAt(0)+"%') OR UPPER(PROVINCE_DESC) LIKE UPPER('"+vector.elementAt(0)+"%')) AND ACTIVE_STATUS=('"+vector.elementAt(1)+"')"+
			" ORDER BY PROVINCE_CODE ASC "+
			" )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		
		// Added by Thamali Jayatunga on 2010.02.25
		m_help_TXT_VEHICLE_NO_sql=
			" SELECT L.NO,L.VEHICLE_NO,L.CUSTOMER_NAME,L.REG_DATE,L.VEHICLE_TYPE,L.MODEL_CODE,L.YEAR_OF_MANUFACTURE,L.CUBIC_CAPACITY,L.CHASIS_NO, "+
			"        L.ENGINE_NO,L.EXTEND,L.FUEL_TYPE,L.ADDRESS,L.CR_BOOK_DATE,L.PROVINCE,L.CR_BOOK_NO,L.VALUER_CODE,L.VALUER_NAME, "+
			"        L.VALUATION_DATE,L.VALUE,L.FORCED_SALES_VALUE,L.METER_READING,L.CONDITION_OF_ASSET,L.NOTES,L.REMARKS,L.TYPE_OF_BODY "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.VEHICLE_NO,P.CUSTOMER_NAME,P.REG_DATE,P.VEHICLE_TYPE,P.MODEL_CODE,P.YEAR_OF_MANUFACTURE,P.CUBIC_CAPACITY,P.CHASIS_NO, "+
			"        P.ENGINE_NO,P.EXTEND,P.FUEL_TYPE,P.ADDRESS,P.CR_BOOK_DATE,P.PROVINCE,P.CR_BOOK_NO,P.VALUER_CODE,P.VALUER_NAME, "+
			"        P.VALUATION_DATE,P.VALUE,P.FORCED_SALES_VALUE,P.METER_READING,P.CONDITION_OF_ASSET,P.NOTES,P.REMARKS,P.TYPE_OF_BODY "+
			" FROM( "+ 
			" SELECT "+ 
			" VEHICLE_NO, "+
			" CUSTOMER_NAME, "+
			" TO_CHAR(REG_DATE,'DD-MM-YYYY') REG_DATE, "+
			" NVL(VEHICLE_TYPE,'-') VEHICLE_TYPE, "+
			" NVL(MODEL_CODE,'-') MODEL_CODE, "+ 
			" NVL(YEAR_OF_MANUFACTURE,0) YEAR_OF_MANUFACTURE, "+
			" NVL(CUBIC_CAPACITY,'-') CUBIC_CAPACITY, "+
			" NVL(CHASIS_NO,'-') CHASIS_NO, "+
			" NVL(ENGINE_NO,'-') ENGINE_NO, "+ 
			" NVL(EXTEND,'-') EXTEND, "+
			" NVL(FUEL_TYPE,'-') FUEL_TYPE, "+ 
			" NVL(ADDRESS,'-') ADDRESS, "+
			" TO_CHAR(CR_BOOK_DATE,'DD-MM-YYYY') CR_BOOK_DATE, "+
			" NVL(PROVINCE,'-') PROVINCE, "+
			" NVL(CR_BOOK_NO,'-') CR_BOOK_NO, "+
			" NVL(VALUER_CODE,'-') VALUER_CODE, "+
			" NVL("+m_schema_name+".AF_CO_GET_VALUER_NAME(VALUER_CODE),'-') VALUER_NAME, "+
			" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+
			" NVL(VALUE,0) VALUE, "+
			" NVL(FORCED_SALES_VALUE,0) FORCED_SALES_VALUE, "+
			" NVL(METER_READING,0) METER_READING, "+
			" NVL(CONDITION_OF_ASSET,'-') CONDITION_OF_ASSET, "+
			" NVL(NOTES,'-') NOTES, "+
			" NVL(REMARKS,'-') REMARKS, "+
			" NVL(TYPE_OF_BODY,'-') TYPE_OF_BODY "+
			" FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE "+
			" WHERE UPPER(VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND   UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND   ACTIVE_STATUS=('"+vector.elementAt(2)+"') "+
			" ORDER BY VEHICLE_NO DESC "+
			" )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		// Added by Thamali Jayatunga on 2010.02.25
		m_help_TXT_FD_NO_sql=
			" SELECT L.NO,L.FD_ACC_NO,L.AMOUNT,L.STARTING_DATE,L.MATURITY_DATE,L.INTEREST_DATE,L.INTEREST_PAYABLE,L.PERIOD,L.REMARKS "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.FD_ACC_NO,P.AMOUNT,P.STARTING_DATE,P.MATURITY_DATE,P.INTEREST_DATE,P.INTEREST_PAYABLE,P.PERIOD,P.REMARKS "+
			" FROM( "+ 
			" SELECT "+ 
			" FD_ACC_NO, "+
			" NVL(AMOUNT,0) AMOUNT, "+
			" TO_CHAR(STARTING_DATE,'DD-MM-YYYY') STARTING_DATE, "+
			" TO_CHAR(MATURITY_DATE,'DD-MM-YYYY') MATURITY_DATE, "+
			" TO_CHAR(INTEREST_DATE,'DD-MM-YYYY') INTEREST_DATE, "+
			" NVL(INTEREST_PAYABLE,'-') INTEREST_PAYABLE, "+
			" NVL(PERIOD,0) PERIOD, "+
			" NVL(REMARKS,'-') REMARKS "+
			" FROM "+m_schema_name+".AF_MK_APP_SECURITY_FIXED_DEP "+
			" WHERE UPPER(FD_ACC_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND   UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND   ACTIVE_STATUS=('"+vector.elementAt(2)+"') "+
			" ORDER BY FD_ACC_NO DESC "+
			" )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
		
		// Added by Thamali Jayatunga on 2010.02.25
		m_help_TXT_MORTGAGE_NO_sql=
			" SELECT L.NO,L.MORTGAGE_NO,L.MORTGAGE_TYPE,L.DEED_NO,L.ADDRESS,L.VALUE,L.VALUES_NAME,L.VALUATION_DATE,L.REMARKS "+
			" FROM  "+
			"(SELECT ROWNUM NO,P.MORTGAGE_NO,P.MORTGAGE_TYPE,P.DEED_NO,P.ADDRESS,P.VALUE,P.VALUES_NAME,P.VALUATION_DATE,P.REMARKS "+
			" FROM( "+ 
			" SELECT "+ 
			" MORTGAGE_NO, "+
			" MORTGAGE_TYPE, "+
			" NVL(DEED_NO,'-') DEED_NO, "+ 
			" NVL(ADDRESS,'-') ADDRESS, "+   
			" NVL(VALUE,0) VALUE, "+ 
			" NVL(VALUES_NAME,'-') VALUES_NAME, "+ 
			" TO_CHAR(VALUATION_DATE,'DD-MM-YYYY') VALUATION_DATE, "+
			" NVL(REMARKS,'-') REMARKS "+
			" FROM "+m_schema_name+".AF_MK_APP_SECURITY_LAND "+
			" WHERE UPPER(MORTGAGE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
			" AND   UPPER(APPLICATION_NO) LIKE UPPER('%"+vector.elementAt(1)+"%') "+
			" AND   ACTIVE_STATUS=('"+vector.elementAt(2)+"') "+
			" ORDER BY MORTGAGE_NO DESC "+
			" )P)L  "+
			" WHERE L.NO>=  "+ s1+"  AND L.NO<=  "+s2+" ";
			// added by udara on 03-01-2012
			
			//---------------added by ishani 2014-02-27--------get terminated contract's finance no--------------------
		ClientSql_Receipt_terminate =   	" "+
			" SELECT R.AAA, R.FINANCE_NO, R.Name, R.Vehicle_No, R.ADDRESS1, R.ADDRESS2, R.CITY_NAME, R.Status, R.Client,R.TOTAL_FINANCE_AMOUNT  FROM ( "+ // new
			" SELECT  ROWNUM AAA, P.NO, P.FINANCE_NO FINANCE_NO,P.FULL_NAME Name, P.REG_NO Vehicle_No,P.ADDRESS1 ADDRESS1 , P.ADDRESS2 ADDRESS2,P.CITY_NAME CITY_NAME,ACTIVE_STATUS Status,P.CLIENT_CODE Client, P.TOTAL_FINANCE_AMOUNT TOTAL_FINANCE_AMOUNT "+ // new
			" "+
			"FROM "+
			"(SELECT DISTINCT ROWNUM NO, FINANCE_NO,FULL_NAME,REG_NO ,ADDRESS1, ADDRESS2,CITY_NAME ,ACTIVE_STATUS,CLIENT_CODE,TOTAL_FINANCE_AMOUNT "+
			"FROM "+
			"(SELECT DISTINCT NVL(FINANCE_NO,'-') FINANCE_NO ,FULL_NAME , A.ACTIVE_STATUS , TEMP_ACTIVE_STATUS, NVL(DECODE(CLIENT_TYPE,'I',ADDRESS1,'C',REGISTERED_ADDRESS1),'-') ADDRESS1 , "+
			"        NVL(DECODE(CLIENT_TYPE,'I',ADDRESS2,'C',REGISTERED_ADDRESS2),'-') ADDRESS2 ,"+
			//"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE, "+m_schema_name+".AF_CO_GET_TERMINATED_AMT_FIN(B.FINANCE_NO)  TOTAL_FINANCE_AMOUNT "+ //added by nuwan de silva 25-07-07
			"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE, "+m_schema_name+".AF_CO_GET_CLOSE_REC_AMNT(B.FINANCE_NO)  TOTAL_FINANCE_AMOUNT "+ // added by udara 11-04-2014  
			//"        NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE)),'-') CITY_NAME , c.REG_NO REG_NO,A.CLIENT_CODE, B.TOTAL_FINANCE_AMOUNT  TOTAL_FINANCE_AMOUNT "+ //added by nuwan de silva 25-07-07
			"     "+//ADDED MILINDA   //TOTAL_AMOUNT
			"	 FROM "+m_schema_name+".AF_CO_MAS_CLIENT a,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS c "+
			"		WHERE "+
			"		     A.CLIENT_CODE = B.CLIENT_CODE(+) AND "+
			" 			 B.FINANCE_NO IS NOT NULL AND "+ //added by Prabash on 16-06-2012
			"		     B.APPLICATION_NO=C.APPLICATION_NO(+) AND  "+
			"		     A.CLIENT_CODE LIKE ('%" + vector.elementAt(1) + "%') AND  "+ 
			"            B.APPLICATION_STATUS IN ('TERMI','TERMINATED','NORM_TERMI') AND "+ 
			" (UPPER(b.CLIENT_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(a.FULL_NAME) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(a.CLIENT_CODE) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(A.CITY_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(B.FINANCE_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(b.APPLICATION_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" UPPER(A.NIC_NO)    LIKE UPPER('%"+vector.elementAt(0)+"%') OR "+
			" UPPER(C.REG_NO) LIKE UPPER('%" + vector.elementAt(0) + "%') OR "+
			" "+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(B.APPLICATION_NO) LIKE '%"+vector.elementAt(0)+"%') "+ 
			" ORDER BY FULL_NAME )) P "+	
			" )R "+ // new 
			" WHERE R.AAA >= "+ s1+" AND R.AAA <= "+s2+" ";
		
		
		
		Ret_Object = s;
		return Ret_Object;
	}
	
	public Object Ret_Object;
	public String ret_str;
	LAKDL_AF_CO_conn_methods m_sn_methods;
	String m_schema_name;
	
	public String ClientSql;
	public String ClientSql_Header;
	public String PAYSql;
	public String PAYSql_Header;
	public String ClientSql1;
	public String ClientSql1_Header;
	
	public String MKOfficerSqlNew;
	public String MKOfficerSqlNew_Header;
	
	// added by udara 09-10-2018
	public String PledgeContractSqlNew;
	public String PledgeContractSqlNew_Header;
	// end by udara 09-10-2018
	
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
	public String BranchSql;
	public String BranchSql_Header;
	public String m_help_TXT_DISTRICT_CODE_sql;
	public String m_help_TXT_DISTRICT_CODE_sql_Header;
	public String m_help_TXT_DISTRICT_CODE_sql1;
	public String m_help_TXT_DISTRICT_CODE_sql1_Header;
	public String m_help_TXT_APPLICATION_NO;
	public String m_help_TXT_APPLICATION_NO_Header;
	
	public String m_help_TXT_APPLICATION_NO_new;
	public String m_help_TXT_APPLICATION_NO_new_Header;
	
	public String m_help_TXT_CLIENT_CODE;
	public String m_help_TXT_CLIENT_CODE_Header;
	public String m_help_TXT_CLIENT_CODE_GURAN;
	public String m_help_TXT_CLIENT_CODE_GURAN_Header;
	
	
	
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
	
	public String	m_help_TXT_FinanceSql_new2;
	public String	m_help_TXT_FinanceSql_new2_Header; 
	
	//Added by Dineth on 20-03-2009
	public String	m_help_TXT_FinanceSql_new3;
	public String	m_help_TXT_FinanceSql_new3_Header; 
	//End by Dineth on 20-03-2009
	
	public String	m_help_TXT_SO_NO_sql;
	public String	m_help_TXT_SO_NO_sql_Header; 
	
	
	public String m_help_TXT_INVOICE_NO_app_sql1;
	public String m_help_TXT_INVOICE_NO_app_sql1_Header; 
	
	public String m_help_TXT_APPLICATION_NO_1;
	public String m_help_TXT_APPLICATION_NO_1_Header; 
	
	public String m_help_TXT_FINANCE_NO_1;
	public String m_help_TXT_FINANCE_NO_1_Header; 
	
	public String m_help_application_no_change_pro_invoice;
	public String m_help_application_no_change_pro_invoice_Header;
	
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
	
	public String m_help_TXT_FIN_SQL="";
	public String m_help_TXT_FIN_SQL_Header="Finance No Help";
	
	public String m_help_client_code_help="";
	public String m_help_client_code_help_Header="Client Help";
	
	
	public String m_help_TXT_RECIEPT_NO_sql_report;
	public String m_help_TXT_RECIEPT_NO_sql_report_Header;
	
	public String m_help_TXT_TEMP_REC_NO_sql;
	public String m_help_TXT_TEMP_REC_NO_sql_Header;
	
	public String m_help_TXT_ADVER_OFFERS_NO_sql; 
	public String m_help_TXT_ADVER_OFFERS_NO_sql_Header;
	
	public String m_help_TXT_OD_INTEREST_NO_sql;
	public String	m_help_TXT_OD_INTEREST_NO_sql_Header;
	
	public String m_help_TXT_PAYMENT_NO_sql;
	public String m_help_TXT_PAYMENT_NO_sql_Header;
	
	public String m_help_TXT_INVOICE_NO_sql_report;
	public String m_help_TXT_INVOICE_NO_sql_report_Header;
	
	public String m_help_TXT_POD_NO_sql_report;
	public String m_help_TXT_POD_NO_sql_report_Header;
	
	public String m_help_TXT_FINANCE_NO_sql_report;
	public String m_help_TXT_FINANCE_NO_sql_report_Header;
	
	public String m_help_TXT_LEASE_NO_sql_report;
	public String m_help_TXT_LEASE_NO_sql_report_Header;
	
	
	public String FinanceSql;
	public String FinanceSql_Header;
	
	public String m_help_TXT_USER_ID_sql;
	public String m_help_TXT_USER_ID_sql_Header;
	
	public String m_help_TXT_MK_OFFICER_sql;
	public String m_help_TXT_MK_OFFICER_sql_Header;
	
	public String m_help_TXT_MK_OFFICER_sql_new;
	public String m_help_TXT_MK_OFFICER_sql_new_Header;
	
	public String m_help_TXT_APPLICATION_NO_3;
	public String m_help_TXT_APPLICATION_NO_3_Header;
	
	public String m_help_TXT_APPLICATION_NOCK;
	public String m_help_TXT_APPLICATION_NOCK_Header;
	
	public String m_help_TXT_INVOICE_NO_NEW_sql;
	public String m_help_TXT_INVOICE_NO_NEW_sql_Header;
	
	public String PriceSql_invoice_new;
	public String PriceSql_invoice_new_Header;
	
	public String m_help_vendor_proforma_invoice;
	public String m_help_vendor_proforma_invoice_Header;
	
	
	public String CitySql;
	public String CitySql_Header;
	
	public String m_help_txt_sub_team_sql;
	public String m_help_txt_sub_team_sql_Header;
	
	public String m_help_TXT_USER_ID_new_sql;
	public String m_help_TXT_USER_ID_new_sql_Header;
	
	public String m_help_TXT_APPLICATION_NO_MOD ;
	public String m_help_TXT_APPLICATION_NO_MOD_Header;
	
	public String m_help_team_user_id_sql_new ;
	public String m_help_team_user_id_sql_new_Header ;//Added By Sandun 0n 05-11-2008
	
	//Added by Dineth on 2008-11-03
	public String m_help_TXT_CHK_NO_sql;
	public String m_help_TXT_CHK_NO_sql_Header;
	
	//End by Dineth on 2008-11-03
	
	public String m_help_TXT_PROVINCE_sql; //add by waruna 2012-04-25
	public String	m_help_TXT_PROVINCE_sql_Header;
	
	// Added by Thamali Jayatunga on 2010.02.25
	public String m_help_TXT_VEHICLE_NO_sql;
	public String m_help_TXT_VEHICLE_NO_sql_Header;
	
	public String	m_help_TXT_MORTGAGE_NO_sql;
	public String	m_help_TXT_MORTGAGE_NO_sql_Header; 
	
	public String ClientSql_Receipt_terminate ;
	public String ClientSql_Receipt_terminate_Header;
	
	
	public String	m_help_TXT_FD_NO_sql;
	public String	m_help_TXT_FD_NO_sql_Header; 
	
}
