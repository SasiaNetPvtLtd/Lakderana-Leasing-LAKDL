//--
//SCREEN NAME	:CREDIT APPROVAL SANCTION LETTER
//CREATED BY	:NUWAN DE SILVA
//DATE/TIME		: 11-06-07
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Credit_Approval_Saction_Letter2 extends javax.servlet.http.HttpServlet { 
	Connection conn;
	Statement stmt,stmt_rental,stmt_charges,stmt_price,stmt_ter;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs_rental,rs_charges,rs_price,rs_ter;
	public String m_chksql;
	ServletOutputStream out = null;
	String reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		try { 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			//Hold TO Orient Details===========
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="",m_broker_name="";
			String m_insurance_agent="";
			
			String _m_tmp_nic_no        ="";
			String _m_tmp_gender        ="";
			String _m_tmp_date_of_birth ="";
			String _m_tmp_client_type   ="";
			String m_termi_type = "";
			String Sql_data_facility_details = "";
			String Sql_data_facility_details_sum = "";
			String Sql_data_total_income="";
			
			double total_charges=0;
			double sum_year2=0;
			double sum_year3=0;
			double sum_year4=0;
			double sum_year5=0;
			
			double m_NIBSM=0;
			int m_AMI=0;
			int m_PERIOD=0;
			int    no_of_records=0;
			int _m_tmp_pricing_count=0; //added by nuwan de silva on 03-01-2008
			//================================
			String		Sql_data_exposure_prev="";
			String		Sql_data_exposure_current="";	
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			CallableStatement callstmt1 =null;
			
			stmt_charges = conn.createStatement ();
			stmt = conn.createStatement ();
			stmt_rental=conn.createStatement ();
			stmt_price = conn.createStatement ();
			stmt_ter  =  conn.createStatement ();
			
			String m_applicaton_no        = req.getParameter("applicaton_no");
			String m_pre_stage="";
			String m_app_stage="";
			String m_pre_stage1="";
			
			if(req.getParameter("pre")!=null){
				m_pre_stage=req.getParameter("pre");
			}
			//String m_pre_stage=req.getParameter("pre");//comment by nuwan de silva on 23-11-2007
			
			if(req.getParameter("appro")!=null){
				m_app_stage=req.getParameter("appro");
			}
			//String m_app_stage=req.getParameter("appro"); //comment by nuwan de silva on 23-11-2007
			
			if(req.getParameter("qry")!=null){
				m_pre_stage1=req.getParameter("qry");
			}
			//String m_pre_stage1=req.getParameter("qry"); //comment by nuwan de silva on 23-11-2007
			
			
			String m_close="23";  //added  by nuwan de silva on 23-11-2007
			String m_Hid_scr_name=""; //added by nuwan de silva on 23-11-2007
			
			//String m_close=req.getParameter("CLS"); //comment by nuwan de silva on 23-11-2007
			if(req.getParameter("CLS")!=null){
				m_close=req.getParameter("CLS");
			}
			
			//String m_Hid_scr_name        = req.getParameter("Hid_scr_name");  //comment by nuwan de silva on 23-11-2007
			if(req.getParameter("Hid_scr_name")!=null){
				m_Hid_scr_name        = req.getParameter("Hid_scr_name");
			}			
			
			String m_return_status="";
			String m_chksql="main_page";
			
			if(req.getParameter("chksql")!=null){
				m_chksql=req.getParameter("chksql");
			}
			
			//---added by nuwan de silva on 03-10-07---------
			if(m_pre_stage.equals("ENT_CON")){
				m_return_status="ENTERED";
			}
			else if(m_pre_stage.equals("V-APP")){
				m_return_status="VERIFY1";
			}
			else if(m_pre_stage.equals("VERIFY-M")){
				m_return_status="V-APP";
			}
			else if(m_pre_stage.equals("V-RECOM")){
				m_return_status="V-APP";
			}
			
			//-----------------------------------------------
			
			
			//out.println("m_applicaton_no"+m_applicaton_no);
			
			
			String m_schema_name = m_sn_methods.schema_name;
			
			
			rs_ter = stmt_ter.executeQuery("SELECT "+m_schema_name+".AF_CO_GET_APP_TERMI_TYPE('"+m_applicaton_no+"') FROM DUAL"); //Sandun on 24-04-2009
			
			if(rs_ter.next()){
				m_termi_type = rs_ter.getString(1);
			}
			
			rs = stmt.executeQuery(" SELECT "+
				" UPPER(COMPANY_NAME), "+
				" UPPER(ADDRESS1), "+
				" UPPER(ADDRESS2), "+
				" CITY, "+
				" TEL_NO, "+
				" FAX_NO,  "+
				" VAT_RATE "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			
			boolean more = rs.next();		
			
			if(more)
			{
				m_orient_name=rs.getString(1);
				m_orient_add1=rs.getString(2);
				m_orient_add2=rs.getString(3);
				m_orient_city_name=rs.getString(4);
				m_orient_tel_no=rs.getString(5);
				m_orient_fax_no=rs.getString(6);
				m_orient_vat_rate=rs.getString(7);			
			}
			
			/*String Sql_broker=" SELECT DISTINCT B.payee_name "+
			" FROM "+m_schema_name+".af_co_pro_app_pricing_charges a , "+m_schema_name+".af_cr_pro_sub_char_payee_ref B "+
			" WHERE A.payee_code=B.payee_code "+
			" AND   B.sub_type_code='BROKERCOMM' "+
			" AND   A.application_no='"+m_applicaton_no+"' ";
			rs = stmt.executeQuery(Sql_broker);
			more = rs.next();		
			if(more)
			{
			m_broker_name=rs.getString(1);
			}
			*/
			
			String Sql_broker=" SELECT DISTINCT B.payee_name "+
				" FROM "+m_schema_name+".af_co_pro_app_pricing_charges a , "+m_schema_name+".af_cr_pro_sub_char_payee_ref B "+
				" WHERE A.payee_code=B.payee_code "+
				" AND   B.sub_type_code='INSURANCE' "+
				" AND   A.application_no='"+m_applicaton_no+"' ";
			rs = stmt.executeQuery(Sql_broker);
			more = rs.next();		
			if(more)
			{
				m_insurance_agent=rs.getString(1);
			}
			
			
			
			
			
			String Sql_client_data="SELECT DISTINCT A.APPLICATION_NO, "+//1
				" A.CLIENT_CODE, "+//2
				//" DECODE(B.CLIENT_TYPE,'I',TITLEB.FULL_NAME, "+//3
				" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //3
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.REGISTERED_ADDRESS1),'-')), "+//4
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.REGISTERED_ADDRESS2),'-')), "+//5
				" NVL(DECODE(B.CLIENT_TYPE,'I',B.NIC_NO,  'C',B.BUSINESS_CERTIFICATE_NO),'-'), "+//6
				" NVL((SELECT DISTINCT(NAME) FROM "+m_schema_name+".CO_CO_MAS_USER WHERE USER_ID=A.COLLECTION_OFFICER ),'-'),"+ //7
				" NVL(DECODE(B.CLIENT_TYPE,'I',B.EMP_NAME,'C',(SELECT ACTIVITY "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_BUSINESS_ACTIVI "+
				" WHERE CLIENT_CODE=A.CLIENT_CODE AND CAT_TYPE_CODE='PRIME' AND STATUS='Y')),'-'), "+//8
				" TO_CHAR(SYSDATE,'DD.MM.YYYY'), "+ //9
				" NVL(FINANCE_NO,'-'), "+ //10
				" NVL(MASTER_AGREEMENT_NO,'-'), "+ //11
				//" UPPER(NVL(A.LEAD_SOURCE_NAME,'-')) , "+ //12
				" (SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=A.LEAD_SOURCE_NAME ) BROKER_CODE , "+ //4 //NVL(G.LEAD_SOURCE_NAME,'-')
				//" UPPER(NVL((SELECT DISTINCT(NAME) FROM "+m_schema_name+".CO_CO_MAS_USER WHERE USER_ID=C.MK_OFFICER ),'-')) ,"+ //13
				
				
				//" UPPER(NVL((SELECT FIRST_NAME || ' ' || LAST_NAME FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=C.MK_OFFICER ),'-')) ,"+ //13 // commented by udara on 11-02-2015
				
				// added by udara on 11-02-2015
				" ( "+
					" CASE WHEN A.COLLECTION_OFFICER IS NOT NULL THEN "+
					  " UPPER(NVL((SELECT FIRST_NAME || ' ' || LAST_NAME FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=A.COLLECTION_OFFICER ),'-')) "+
							" ELSE "+
					  			" UPPER(NVL((SELECT FIRST_NAME || ' ' || LAST_NAME FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE WHERE EMP_CODE=C.MK_OFFICER ),'-')) "+
					  		" END "+
					  " ), "+ 
						// end by udara on 11-02-2015
				
				
				//" NVL(D.MK_OFFICER,'-')  "+ //13
				" NVL(B.BUSINESS_SUB_SECTOR,'-'),  "+ //14
				" NVL(A.BRANCH_CODE,'-'),  "+ //15
				" NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.BRANCH_CODE),'-') BRANCH_DESC, "+ //16
				" NVL("+m_schema_name+".AF_CO_GET_BUS_SUB_SECT_NAME(B.BUSINESS_SUB_SECTOR),'-') BUSINESS_SUB_SECTOR_DESC, "+ //17
				" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME ,"+ //18 //Added by Chandana on 03/08/2007 Ref No.764
				" NVL(GENDER,'-') ,"+        //Added by nuwan de silva on 23-11-2007  19
				" NVL(TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY'),'-')  DATE_OF_BIRTH ,"+  //Added by nuwan de silva on 23-11-2007  20
				" UPPER(NVL(B.CLIENT_TYPE,'-'))  ,"+ //Added by nuwan de silva on 23-11-2007  21 and modified by indika 09/16/08
				/*" (SELECT COUNT(DISTINCT PRICING_NO)  "+
				"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  WHERE  APPLICATION_NO=A.APPLICATION_NO "+
				" )  pricing_count"+ //added  by nuwan de silva on 03-12-08 //22
				*/
				" "+m_schema_name+".AF_CO_GET_PRICING_COUNT(A.APPLICATION_NO) pricing_count,  "+
				
				// added below by udara on 10-04-2013
				" ( SELECT NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(RECEIVER),'-') "+
				" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  "+
				" WHERE REF_NO = '"+m_applicaton_no+"' "+
				" AND SUSPENSE_ENTRY_TYPE='INSURANCE' ) INSURANCE_AGENT, "+ // 23
				// commented by udara on 11-06-2013
				/*
				" ( SELECT "+ 
						" SUM(SETTELE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND INVOICE_TYPE = 'INSURANCE'  "+
						" AND FINANCE_NO IN  "+    
						" (SELECT "+
							" FINANCE_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
							" WHERE  UPPER(APPLICATION_NO) = '"+m_applicaton_no+"' "+
							" AND APPLICATION_STATUS<>'CANCEL' "+
							" ) "+
				" ) SUM_INSURED, "+ // 24
				*/
				
				// commented by udara 20-01-2014
				/*
				// added by udara on 11-06-2013
				" ( SELECT  "+
				" SUM(SUM_INSURED)  "+
				" FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS A  "+
				//" WHERE     ACTIVE_STATUS='Y' "+
				// " AND INVOICE_TYPE = 'INSURANCE'  "+
				" WHERE APPLICATION_NO = '"+m_applicaton_no+"'  "+   
				" ) SUM_INSURED, "+ // 24
				// end by udara on 11-06-2013
				*/
				
				
				// added by udara 20-01-2014
				" ( SELECT "+ 
						" SUM(SETTELE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
						" WHERE     ACTIVE_STATUS='Y' "+
						" AND INVOICE_TYPE IN ('INSURANCE','INS_INV')  "+
						" AND FINANCE_NO IN  "+    
						" (SELECT "+
							" FINANCE_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
							" WHERE  UPPER(APPLICATION_NO) = '"+m_applicaton_no+"' "+
							" AND APPLICATION_STATUS<>'CANCEL' "+
							" ) "+
				" ) SUM_INSURED, "+ // 24
				// end by udara 20-01-2014
				
				
				" ( SELECT NVL(REG_NO,'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				" WHERE APPLICATION_NO = '"+m_applicaton_no+"' AND ACTIVE_STATUS='Y' ) REG_NO, "+ // 25
				
				" ( SELECT NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(VENDOR_CODE),'-') "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
				" WHERE APPLICATION_NO = '"+m_applicaton_no+"' AND ACTIVE_STATUS='Y' ) VENDOR_CODE, "+	// 26
				// end by udara on 10-04-2013
				
				" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_NEW(A.APPLICATION_NO),'-') "+ // added by udara on 15-07-2013 // 27
				" , A.LEAD_SOURCE_NAME"+
				
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B, "+
				" "+m_schema_name+".AF_MK_PRO_INQUIRY C "+
				" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
				" C.INQUIRY_CODE=A.INQUARY_NO AND "+
				" A.APPLICATION_NO='"+m_applicaton_no+"' ";
			//_______added by nuwan de silva on 23-11-2007_
			rs.close();
			rs = stmt.executeQuery(Sql_client_data);
			more = rs.next();		
			
			if(more)
			{
				_m_tmp_nic_no        =rs.getString(6);
				_m_tmp_gender        =rs.getString(19);
				_m_tmp_date_of_birth =rs.getString(20);
				_m_tmp_client_type   =rs.getString(21);
				_m_tmp_pricing_count   =rs.getInt(22);
			}
			//_______end by nuwan de silva on 23-11-2007
			
			String sql_co_applicant	=" SELECT  "+
				" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C','MESS' || '. '  || UPPER(FULL_NAME)),   "+
				" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
				" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
				" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
				" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-')   "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
				" WHERE   CLIENT_CODE =  "+
				" (SELECT  "+
				" CO_APPLICANT  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')) ";
			
			
			
			String Sql_data_guarantor="SELECT DISTINCT "+
				" NVL(C.GUARANTOR_CODE,'-'), "+ 	 //1
				" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //2
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.REGISTERED_ADDRESS1),'-')), "+//3
				" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.REGISTERED_ADDRESS2),'-')), "+//4
				" NVL(DECODE(B.CLIENT_TYPE,'I',B.NIC_NO,  'C',B.BUSINESS_CERTIFICATE_NO),'-'), "+//5
				" "+m_schema_name+".AF_CO_GET_CLIENT_EXPOSURE(C.GUARANTOR_CODE),  "+	//6	
				" C.GUAR_ID "+//7 added by ns on 11-08-2010
				" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR C ,"+m_schema_name+".AF_CO_MAS_CLIENT B "+ 
				" WHERE  B.CLIENT_CODE=C.GUARANTOR_CODE AND  "+
				" C.APPLICATION_NO='"+m_applicaton_no+"' AND "+
				" C.ACTIVE_STATUS = 'Y' "+ //ACTIVE_STATUS Added by Chandana for Ref no:541 on 24/07/2007
				" ORDER BY C.GUAR_ID ";	
			
			/*String				Sql_data_facility_type="  SELECT "+
											" DISTINCT B.DESCRIPTION  "+
											" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A  ,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
											" WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND B.TRAN_CODE=A.TRANSACION_TYPE ";
			*/
			
			String				Sql_data_facility_type="  SELECT "+
				" DISTINCT B.DESCRIPTION  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B "+
				" WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND B.TRAN_CODE=A.TRANSACTION_TYPE ";
			
			String				Sql_data_asset_details="  SELECT "+												
				" A.INVOICE_NO,"+ //1
				" A.APPLICATION_NO,  "+ //2
				" A.ASSET_ID, "+ //3
				" C.MAKE_CODE, "+ //4
				" D.MAKE_DESC, "+ //5
				" A.MODEL_CODE, "+ //6
				" C.DESCRIPTION, "+ //7
				" NVL(A.ENGINE_NO,'-'), "+ //8
				" NVL(A.CHASSIS_NO,'-'), "+ //9
				" '1' /*QTY*/, "+//10  //Modified by chandana on 24/08/2007
				" DECODE(B.STATUS,'U','Used','N','New','R','Re-Condition'), "+ //11
				//"((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT +NET_AMOUNT ) "+ 
				" ROUND((VAT_PERCENTAGE-VAT_APP)/100*NET_AMOUNT + NET_AMOUNT,0) "+//12
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+
				" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D, "+
				" "+m_schema_name+".AF_CO_PRO_APP_PRICING E"+
				" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  AND "+
				" A.ASSET_ID=B.ASSET_ID AND "+
				" A.APPLICATION_NO=E.APPLICATION_NO AND "+
				" A.INVOICE_NO=E.PRO_INVOICE_NO AND  "+
				" C.MODEL_CODE=A.MODEL_CODE  "+
				" AND C.MAKE_CODE=D.MAKE_CODE  "+
				" AND A.ACTIVE_STATUS='Y'  "+
				" AND B.ACTIVE_STATUS='Y'  "+
				//" AND C.ACTIVE_STATUS='Y'  "+ // commented by udara on 12-08-2013
				//" AND D.ACTIVE_STATUS='Y'  "; // commented by udara on 12-08-2013
				" ";
			
			
			String				Sql_data_valuation_details="  SELECT "+												
				" A.VALUATION_NO, "+ //1
				" A.VALUER_CODE, "+ //2
				" UPPER(B.FIRST_NAME || ' ' || B.LAST_NAME), "+ //3 //added by nuwan de silva 09-07-07
				" YEAR_OF_MANUFACTURE, "+ //4
				" NVL(VALUE,0), "+ //5
				" NVL(FORCED_SALES_VALUE,0)   "+  //6
				" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A, "+m_schema_name+".AF_CO_MAS_VALUERS B "+
				" WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
				"     B.VALUER_CODE=A.VALUER_CODE AND  "+
				"     A.ACTIVE_STATUS='Y' AND "+
				"    B.ACTIVE_STATUS='Y' ";
			
			
			if(m_termi_type.equals("RESCHEDULE")){
				Sql_data_facility_details="  SELECT "+												
					// " DISTINCT A.INVOICE_NO, "+//1
					// "  A.APPLICATION_NO, "+ //2
					// "  A.ASSET_ID, "+ //3
					"  A.PRICING_NO, "+ //1
					"  SUM(A.TOTAL_AMOUNT), "+ //2
					"  SUM(A.NET_PRICE), "+ //3
					"  SUM(A.VAT) , "+ //4
					"  B.VAT_PERCENTAGE , "+ //5
					"  SUM(ROUND(((B.VAT_PERCENTAGE - B.VAT_APP)/100)*B.NET_AMOUNT +B.NET_AMOUNT,0)) NET_APP, "+ //6
					"  PERIOD, "+ //7
					"  DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks') INTERVEL, "+ //8
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(GRENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					//"  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_GROSS, "+ //9
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_GROSS, "+ //9   
					
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(NET_RENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					// "  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_NET, "+ //10
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(NET_RENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_NET, "+ //10   
					
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					// "  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_VAT, "+ //11
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_VAT, "+ //11
					
					
					"  SUM(B.NIBSM) , "+ //12
					"  B.SUPPLIER_CREDIT, "+ //13
					"  B.AMI, "+ //14
					"  B.RATE "+ //15
					
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
					"  "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL C  "+
					"  WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
					"  A.APPLICATION_NO=B.APPLICATION_NO AND "+
					"  A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
					"  C.DURATION=B.PAYMENT_INTERVAL  "+
					"  AND A.ACTIVE_STATUS='Y' "+
					//"  AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING WHERE APPLICATION_NO='"+m_applicaton_no+"' GROUP BY APPLICATION_NO ) "+ //ADDED BY SANDUN ON 10-12-2008  ------------
					"  GROUP BY A.PRICING_NO,B.VAT_PERCENTAGE,PERIOD,DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks'),B.SUPPLIER_CREDIT,B.AMI,B.RATE ";
			}
			else{												
				Sql_data_facility_details="  SELECT "+												
					// " DISTINCT A.INVOICE_NO, "+//1
					// "  A.APPLICATION_NO, "+ //2
					// "  A.ASSET_ID, "+ //3
					"  A.PRICING_NO, "+ //1
					"  SUM(A.TOTAL_AMOUNT), "+ //2
					"  SUM(A.NET_PRICE), "+ //3
					"  SUM(A.VAT) , "+ //4
					"  B.VAT_PERCENTAGE , "+ //5
					"  SUM(ROUND(((B.VAT_PERCENTAGE - B.VAT_APP)/100)*B.NET_AMOUNT +B.NET_AMOUNT,0)) NET_APP, "+ //6
					"  PERIOD, "+ //7
					"  DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks') INTERVEL, "+ //8
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(GRENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					//"  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_GROSS, "+ //9
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_GROSS, "+ //9   
					
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(NET_RENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					// "  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_NET, "+ //10
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(NET_RENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_NET, "+ //10   
					
					// "  SUM(DECODE(B.AMI,'0',B.AMI,(SELECT SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE  A.APPLICATION_NO=APPLICATION_NO AND "+
					// "  A.INVOICE_NO=PRO_INVOICE_NO AND AMI_AMOUNT>0 ))) AMI_VAT, "+ //11
					
					"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
					"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
					"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
					"  AND   PRICING_NO     = B.PRICING_NO "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
					"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_VAT, "+ //11
					
					
					"  SUM(B.NIBSM) , "+ //12
					"  B.SUPPLIER_CREDIT, "+ //13
					"  B.AMI, "+ //14
					"  B.RATE "+ //15
					
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
					"  "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL C  "+
					"  WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
					"  A.APPLICATION_NO=B.APPLICATION_NO AND "+
					"  A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
					"  C.DURATION=B.PAYMENT_INTERVAL  "+
					"  AND A.ACTIVE_STATUS='Y' "+
					//"  AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING GROUP BY APPLICATION_NO) "+ //ADDED BY SANDUN ON 10-12-2008  ------------
					"  GROUP BY A.PRICING_NO,B.VAT_PERCENTAGE,PERIOD,DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks'),B.SUPPLIER_CREDIT,B.AMI,B.RATE ";
			}
			
			
			String				Sql_data_Comments="  SELECT "+												
				" APPLICATION_CODE, "+
				" A.SCORE_CODE, "+
				" B.DESCRIPTION, "+
				" A.COMMENTS "+
				" FROM "+m_schema_name+".AF_CR_PRO_SCORE_DETAIL A, "+m_schema_name+".AF_CR_MAS_SCORE_CATEGORY B "+
				" WHERE A.SCORE_CODE=B.SCORE_CODE AND "+
				" UPPER(A.APPLICATION_CODE)=UPPER('"+m_applicaton_no+"') AND "+
				" COMMENTS IS NOT NULL "+
				" ORDER BY DISPLAY_POSITION /*DESCRIPTION*/ ";
			
			
			/*	 String				Sql_data_approval_prcess="  SELECT "+																								
					" APPLICATION_NO, 'Application entry level','1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER),ENT_USER,TO_CHAR(ENT_DATE,'HH:MM'),TO_CHAR(ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-')  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					" WHERE STATUS IN ('ENTERED') AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')  "+
					" AND ENT_DATE IN "+
					" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='ENTERED') "+
					
					" UNION "+
					" SELECT APPLICATION_NO,'Credit Verification Level:-','2' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER),ENT_USER,TO_CHAR(ENT_DATE,'HH:MM'),TO_CHAR(ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					" WHERE  STATUS='VERIFY1' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
					" AND ENT_DATE IN "+
					" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='VERIFY1') "+
					
					" UNION "+
					" SELECT APPLICATION_NO,'Credit Score Evaluation Level:-','3' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER),ENT_USER,TO_CHAR(ENT_DATE,'HH:MM'),TO_CHAR(ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')  AND STATUS='V-APP'  "+
					" AND ENT_DATE IN "+
					" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='V-APP') "+
					
					" UNION "+
					" SELECT APPLICATION_NO,'Credit Approval 1 Level:-','4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER),ENT_USER,TO_CHAR(ENT_DATE,'HH:MM'),TO_CHAR(ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')  AND STATUS='VERIFY-M' "+
					" AND ENT_DATE IN "+
					" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='VERIFY-M') "+
					" ORDER BY E_LEVEL ASC ";
					*/
			
			//Modified by Mahela on 15-11-2007 - Approval Levels	
			
			String				Sql_data_approval_prcess=" SELECT * FROM ( SELECT "+																								 
				" APPLICATION_NO, 'Application Process - New','1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				
				" WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				//"                   A.STATUS IN ('ENTERED') AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+ // commented by udara 08-08-2014
				"                   A.STATUS IN ('ENT_CON') AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+ // added by udara 08-08-2014
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='ENTERED')  "+
				
				"  UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Verification','2' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME ,A.ENT_DATE ENT_DATE2  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='VERIFY1' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='VERIFY1')  "+
				
				"  UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Verification Reversal','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-VERIFY1' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-VERIFY1')  "+
				
				" UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Score Evaluation','3' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME ,A.ENT_DATE ENT_DATE2  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='V-APP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='V-APP')  "+
				
				
				" UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Score Evaluation Reversal','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-V-APP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-V-APP')  "+
				
				//ADDED BY NS ON 27-07-2010	
				" UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Recommendation','4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='V-RECOM' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='V-RECOM')  "+
				
				" UNION  "+
				"                   SELECT APPLICATION_NO, 'Credit Recommendation Reversal','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-V-RECOM' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-V-RECOM')  "+
				
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 1','5' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='VERIFY-M' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='VERIFY-M')  "+
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 1 Reversal','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-VERIFY-M' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-VERIFY-M')  "+
				
				//added by nuwan de silva 04-jul-07---------- 										
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 2','6' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='VERIFY2' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='VERIFY2')  "+
				
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 2 Reversal','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-VERIFY2' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-VERIFY2')  "+
				
				
				//added by nuwan de silva 29-10-07----------------------------------------------------	
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 2 - Return','R1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='RET-VERY-M' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='RET-VERY-M')  "+
				
				//added by nuwan de silva 29-10-07----------------------------------------------------	
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Verification - Return','R2' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='RET-EN_CON' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='RET-EN_CON')  "+
				//added by nuwan de silva 29-10-07----------------------------------------------------	
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 2 Level','R3' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME ,A.ENT_DATE ENT_DATE2  "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='RET-VERY-1' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='RET-VERY-1')  "+
				
				" UNION  "+
				
					"         SELECT APPLICATION_NO, 'Credit Approval 2 Level Reversal','R3' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME ,A.ENT_DATE ENT_DATE2  "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='R-RET-VERY-1' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='R-RET-VERY-1')  "+
				
				//added by nuwan de silva 29-10-07----------------------------------------------------	
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Credit Approval 1 - Return','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='RET-VE-APP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				//" AND A.ENT_DATE IN  "+
				//" (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"') AND STATUS='RET-VE-APP')  "+
				
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Purchase Order - Delete','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='PO_DEL' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Purchase Order - Enter','1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='PO_ENT' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Purchase Order - Approve','1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='PO_APP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Purchase Order - Disapprove','1' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='PO_DISAPP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				" UNION  "+
				"         SELECT APPLICATION_NO, 'Cancelation After PO','R4' AS E_LEVEL ,"+m_schema_name+".AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME,A.ENT_DATE ENT_DATE2   "+
				"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
				"                   WHERE B.USER_ID=A.ENT_USER AND "+
				"                         C.DESIGNATION_CODE=B.DESIGNATION_CODE AND "+
				"                         A.STATUS='CANCEL_PO' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   "+
				
				") E ORDER BY E.ENT_DATE2 ASC ";
			
			
			
			String				Sql_data_score_model="  SELECT "+																								
				" SCORE_MODEL_CODE "+
				" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE "+
				" WHERE UPPER(APPLICATION_CODE)=UPPER('"+m_applicaton_no+"') ";
			
			
			String				Sql_data_agm_note="  SELECT "+																								
				" APPLICATION_NO, 'Credit Approval 1 - Return','R4' AS E_LEVEL ,LAKDL.AF_CO_GET_USER_NAME(A.ENT_USER),A.ENT_USER,TO_CHAR(A.ENT_DATE,'HH:MI:SS'),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),NVL(REMARK,'-'),C.DESIGNATION_NAME    "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL A,"+m_schema_name+".CO_CO_MAS_USER  B,"+m_schema_name+".CO_CO_MAS_DESIGNATION C  "+
				" WHERE B.USER_ID=A.ENT_USER AND  "+
				" C.DESIGNATION_CODE=B.DESIGNATION_CODE AND  "+
				" A.STATUS='AGM_APP' AND UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"')   ";
			
			
			/*	String Sql_data_total_income="  SELECT "+												
				" A.APPLICATION_NO, "+
				" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
				" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
				" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
				" GROUP BY A.APPLICATION_NO ";
				*/
			
			if(m_chksql.equals("main_page")) {
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				
				if(m_pre_stage.equals("ENT_CON")){
					out.println("<TITLE>Credit Verification Approval</TITLE>"); 
				}
				
				else if(m_pre_stage.equals("V-APP")){
					out.println("<TITLE>Credit Recommendation</TITLE>"); 
				}
				else if(m_pre_stage.equals("V-RECOM")){
					out.println("<TITLE>Credit Approval 1</TITLE>"); 
				}
				else if(m_pre_stage.equals("VERIFY-M")){
					
					out.println("<TITLE>Credit Approval 2</TITLE>"); 
					
				}
				
				
				
				
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				//Declare Global Variables
				out.println("var lineno=0;");
				out.println("var arr_size=0;");
				
				//Declare Global Arrays
				out.println("var array_follow_up_no=new Array();");
				out.println("var array_condition=new Array();");
				out.println("var array_status=new Array();");
				out.println("var array_document=new Array();"); //added by nuwan de silva on 09-11-07
				out.println("var array_stage=new Array();");
				out.println("var array_document_code=new Array();");
				
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(document.Form1.hid_data_status.value=='M1'){");
				out.println("if(data_vec.length>0){");
				out.println("      assing_remarks(data_vec);"); 
				out.println("			}else{");
				out.println("    assing_blank_remarks();"); 
				out.println("			}");		
				
				out.println("			}else{");			
				out.println("display_data(data_vec);");
				out.println("}");
				out.println("}");
				
				out.println("function makeRequest(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_credit_verification_approval&data_val=\"+obj.value;");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				
				out.println("function get_conditions(){ "); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
				// out.println("window.open(m_url);");
				out.println("load_interface(m_url,'XML');");
				
				out.println("}");
				
				//______ added by nuwan de silva on 23-11-2007 _______
				out.println("function validate_nic_no(){ "); 
				
				out.println("if('"+_m_tmp_client_type+"'=='I'){ ");
				out.println("if(!validate_NIC_Return('"+_m_tmp_nic_no+"','"+_m_tmp_gender+"','"+_m_tmp_date_of_birth+"')){");
				out.println("m_nic_no.style.color='red';");
				out.println("}");
				out.println("}");
				out.println("}");
				// ________ end by nuwan de silva on 23-11-2007 ______
				
				//================================================
				
				out.println("	function chk_comment_length(obj){ ");
				
				out.println("	 var keyChar=window.event.keyCode; ");
				
				out.println(" var remarks_length=obj.value.toString().length;");
				
				out.println("if(remarks_length>obj.maxlength) ");
				out.println("		window.event.keyCode=\"\"; ");
				out.println("} ");
				
				
				out.println("function count_length(obj){ ");
				out.println("var remarks_length=obj.value.toString().length; ");
				out.println("var remarks=obj.value.toString(); ");
				out.println("if(remarks_length>obj.maxlength){ ");
				out.println("obj.value=remarks.substring(0,obj.maxlength); ");
				out.println("} ");
				out.println("} ");
				
				
				
				//================================================
				
				
				
				//---------------------ADDED BY CHANDANA ON 09/04/2007---------------------//
				out.println("function get_remarks(obj){ ");		
				//out.println("document.Form1.hid_data_status.value=obj;"); 
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
				//out.println("load_interface(m_url,'XML');");			
				out.println("}");
				
				
				out.println("function assing_remarks(data_vec) { ");
				out.println("m_table_remaks.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
				out.println("'<TD WIDTH=\"13%\"  align=\"left\"></TD>'+"); 
				out.println("'<TD WIDTH=\"65%\" align=\"left\">'+data_vec[1]+'</TD>'+");
				out.println("'<TD WIDTH=\"22%\" ></TD>' +");
				out.println("'</tr></table>';");
				out.println("}");		
				
				
				
				out.println("function assing_blank_remarks() { ");
				out.println("m_table_remaks.innerHTML='';");
				out.println("}");	
				
				//---------------------------- END -------------------------------// 
				
				
				out.println("function load_Follow(row_No){ "); 
				out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
				//out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&scr_name=AF_CR_PRO_SANACTION_LETTER&status=Y&Followu_no='+document.Form1.elements[m_fol_no].value;"); //modified by nuwan de silva on 17-10-07--------
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				
				/*out.println("function header(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR class=pdn_txtpos2>'+");
			out.println("'<TD WIDTH=\"14%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"10%\" align=\"left\">&nbsp;</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
			*/
				
				out.println("function header(){");
				out.println("m_table.innerHTML=\"\" ");	
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR class=pdn_txtpos2>'+");
				out.println("'<TD WIDTH=\"12%\" align=\"left\"><B>Follow up No</B></TD>'+");
				out.println("'<TD WIDTH=\"7%\" align=\"left\">&nbsp;</TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"left\">Type</TD>'+");
				out.println("'<TD WIDTH=\"20%\" align=\"left\">Document</TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\">Stage Entered</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\"><B>Status</B></TD>'+");
				out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
				//out.println("'<TD WIDTH=\"*%\" ></TD>' +");
				out.println("'</TR></table>';");
				out.println("}");
				
				
				
				
				/*	out.println("function display_data(data_vec){ "); 
					out.println("lineno=0 ");
					out.println("arr_size=0 ");
					out.println("var i=0");
					out.println("header();");
					out.println("if(data_vec.length>0){");
					out.println("while(i<data_vec.length){");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
					out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno+')\" >Follow up</TD>'+");//&nbsp;&nbsp;Follow up
					out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
					out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
					out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"*%\" ></TD>' +");
					out.println("'</tr></table>';");
					out.println("i=i+3;");
					out.println("lineno=lineno+1;");
					out.println("arr_size=arr_size+1;");
					out.println("}");
					out.println("}");
					
					out.println("else if(data_vec.length==0){");
					out.println("add_row()");
					out.println("}");
					out.println("}");
				*/
				
				
				out.println("function display_data(data_vec){ "); 
				out.println("lineno=0 ");
				out.println("arr_size=0 ");
				out.println("var i=0");
				out.println("header();");
				out.println("if(data_vec.length>0){");
				out.println("while(i<data_vec.length){");
				//out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
				
				out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
				out.println("      '<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+lineno+')\" ><U>Follow up</U></TD>';");//&nbsp;&nbsp;Follow up
				
				out.println("if(data_vec[i+3]==\"-\"){");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >-</TD>';");//&nbsp;&nbsp;Follow up
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" ) >'+data_vec[i+2].replace('$','&')+'</TD>';");//&nbsp;&nbsp;Follow up
				out.println("}");
				out.println("else {");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\" >Document</TD>';");//&nbsp;&nbsp;Follow up
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+data_vec[i+6]+'\") ><U>'+data_vec[i+2].replace('$','&')+'</U></TD>';");//&nbsp;&nbsp;Follow up
				out.println("}");
				
				
				out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\"   >'+data_vec[i+3]+'</TD>';");
				out.println("m_condition='<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>';");
				out.println("m_status='<TD WIDTH=\"10%\"  align=\"left\">'+data_vec[i+5]+'</TD>';");
				out.println("m_button='<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>';");
				
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+5]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	VALUE=\"'+data_vec[i+2].replace('$','&')+'\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	VALUE=\"'+data_vec[i+6]+'\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	VALUE=\"'+data_vec[i+3]+'\" >';");
				
				//out.println("'<TD WIDTH=\"*%\" ></TD>'; ");
				
				
				out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				
				//out.println("i=i+3;");
				out.println("i=i+7;");
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
				out.println("}");
				out.println("}");
				
				out.println("else if(data_vec.length==0){");
				out.println("add_row()");
				out.println("}");
				out.println("}");
				
				
				
				
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				
				//out.println("else{"); 
				out.println("return true;"); 
				//	out.println("}"); 
				out.println("}"); 
				
				
				
				out.println("function before_submit(){ "); 
				out.println("   document.Form1.Hid_scr_name.value='"+m_Hid_scr_name+"';");//Added By Nuwan De Silva
				
				out.println("if(document.Form1.Hid_scr_name.value!='AF_CR_PRO_AGM_COMMENT') {" );
				
				out.println("if (document.Form1.chk_app.checked==true || document.Form1.chk_return.checked==true ||  document.Form1.chk_rej.checked==true){");
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
				
				if(m_pre_stage.equals("ENT_CON")){
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_credit_approval_details?appli_no="+m_applicaton_no+"&return_status="+m_return_status+"&scr="+m_pre_stage1+"&actst1="+m_pre_stage+"&actst2="+m_app_stage+"';");   
				}	else{
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_save_score_approval?pre_stage1="+m_pre_stage1+"&return_status="+m_return_status+"&app_no="+m_applicaton_no+"&appro="+m_app_stage+"';");
				}
				
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("} ");
				out.println("} ");
				
				out.println("else { ");
				
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_Save_AGM_Comments?app_no="+m_applicaton_no+"&appro="+m_app_stage+"';");
				out.println("		document.Form1.submit();	"); 
				out.println("} ");
				out.println("} ");
				
				out.println("} ");
				
				
				out.println("function load_lock(){	"); 
				out.println("if("+m_close+"==1){");
				out.println("window.close()");
				out.println("}	"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=main_page&pre="+m_pre_stage+"&applicaton_no="+m_applicaton_no+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?chksql=main_page&pre="+m_pre_stage+"&applicaton_no="+m_applicaton_no+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"';");
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_HELP_Sanction_Letter_Approval\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				
				
				out.println("function load_roll_value(m_val){"); 
				
				if(m_pre_stage.equals("ENT_CON")){
					out.println("help_box.innerHTML=\" Credit - Credit Verification Approval - \"+m_val;"); 
				}
				
				else if(m_pre_stage.equals("V-APP")){
					out.println("help_box.innerHTML=\" Credit - Credit Approval 1 - \"+m_val;"); 
				}
				else if(m_pre_stage.equals("VERIFY-M")){
					
					out.println("help_box.innerHTML=\" Credit - Credit Approval 2 - \"+m_val;"); 
					
				}
				
				out.println("}"); 
				out.println(""); 
				
				
				out.println("function load_roll_out_value(){");
				
				if(m_pre_stage.equals("ENT_CON")){
					out.println("help_box.innerHTML=\" Credit - Credit Verification Approval  - \"+document.Form1.hid_status.value;"); 
				}
				
				else if(m_pre_stage.equals("V-APP")){
					out.println("help_box.innerHTML=\" Credit - Credit Approval 1 - \"+document.Form1.hid_status.value;"); 
				}
				else if(m_pre_stage.equals("VERIFY-M")){
					
					out.println("help_box.innerHTML=\" Credit - Credit Approval 2 - \"+document.Form1.hid_status.value;"); 
					
				}
				
				out.println("}"); 
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}");
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.TXT_GUARANTOR_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_PERIOD.disabled=true;"); 
				out.println("document.Form1.TXT_TEL_NO.disabled=true;"); 
				out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_FULL_NAME.disabled=true;"); 
				out.println("document.Form1.TXT_ADDRESS1.disabled=true;"); 
				out.println("document.Form1.TXT_ADDRESS2.disabled=true;"); 
				out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_NIC_NO.disabled=true;"); 
				out.println("document.Form1.TXT_PRICING_NO.disabled=true;"); 
				out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
				out.println("document.Form1.TXT_PRO_INVOICE_NO.disabled=true;"); 
				out.println("document.Form1.TXT_PAYMENT_MODE.disabled=true;"); 
				out.println("document.Form1.TXT_PAYMENT_INTERVAL.disabled=true;"); 
				out.println("document.Form1.TXT_RATE.disabled=true;"); 
				out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
				out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
				out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
				out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
				out.println("document.Form1.TXT_SUB_MODEL_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_COLOUR.disabled=true;"); 
				out.println("document.Form1.TXT_TOTAL_AMOUNT.disabled=true;"); 
				out.println("document.Form1.TXT_MODEL_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_ADDRESS.disabled=true;"); 
				out.println("document.Form1.TXT_VENDOR_CODE.disabled=true;"); 
				
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Servlet?class_in=\"+client_name+\"AF_PRO_CR_help_select\"+"); 
				out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
				out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
				out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("		help_value_assign_1();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
				out.println("		help_value_assign_2();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
				out.println("		help_value_assign_3();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("		help_value_assign_4();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
				out.println("		help_value_assign_5();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
				out.println("		help_value_assign_6();"); 
				out.println("		}"); 
				out.println("	}"); 
				out.println("	else{"); 
				out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("		return false;"); 
				out.println("	} "); 
				out.println("	}"); 
				out.println("	else{	"); 
				out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
				out.println("	}	"); 
				out.println("	}		"); 
				out.println("	}	"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 
				out.println("function help_button_1() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_1() {"); 
				out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_2() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    m_sql = \"m_help_TXT_GUARANTOR_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_GUARANTOR_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_2() {"); 
				out.println("    document.Form1.TXT_GUARANTOR_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_3() {"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_3() {"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_4() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_4() {"); 
				out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_5() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    m_sql = \"m_help_TXT_PRICING_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_PRICING_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_5() {"); 
				out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_6() {"); 
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    m_sql = \"m_help_TXT_PRO_INVOICE_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_PRO_INVOICE_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_6() {"); 
				out.println("    document.Form1.TXT_PRO_INVOICE_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_update() {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
				out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
				out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
				out.println("    } ");
				out.println("    else{");
				out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"N@\";}"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_GUARANTOR_CODE.value=oBj.valout[3];"); 
				out.println("    document.Form1.TXT_PERIOD.value=oBj.valout[4];"); 
				out.println("    document.Form1.TXT_TEL_NO.value=oBj.valout[5];"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[6];"); 
				out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[7];"); 
				out.println("    document.Form1.TXT_ADDRESS1.value=oBj.valout[8];"); 
				out.println("    document.Form1.TXT_ADDRESS2.value=oBj.valout[9];"); 
				out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[10];"); 
				out.println("    document.Form1.TXT_NIC_NO.value=oBj.valout[11];"); 
				out.println("    document.Form1.TXT_PRICING_NO.value=oBj.valout[12];"); 
				out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[13];"); 
				out.println("    document.Form1.TXT_PRO_INVOICE_NO.value=oBj.valout[14];"); 
				out.println("    document.Form1.TXT_PAYMENT_MODE.value=oBj.valout[15];"); 
				out.println("    document.Form1.TXT_PAYMENT_INTERVAL.value=oBj.valout[16];"); 
				out.println("    document.Form1.TXT_RATE.value=oBj.valout[17];"); 
				out.println("    document.Form1.TXT_ASSET_ID.value=oBj.valout[18];"); 
				out.println("    document.Form1.TXT_ENGINE_NO.value=oBj.valout[19];"); 
				out.println("    document.Form1.TXT_CHASSIS_NO.value=oBj.valout[20];"); 
				out.println("    document.Form1.TXT_REG_NO.value=oBj.valout[21];"); 
				out.println("    document.Form1.TXT_SUB_MODEL_CODE.value=oBj.valout[22];"); 
				out.println("    document.Form1.TXT_COLOUR.value=oBj.valout[23];"); 
				out.println("    document.Form1.TXT_TOTAL_AMOUNT.value=oBj.valout[24];"); 
				out.println("    document.Form1.TXT_MODEL_CODE.value=oBj.valout[25];"); 
				out.println("    document.Form1.TXT_ADDRESS.value=oBj.valout[26];"); 
				out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[27];"); 
				out.println("}"); 
				
				/*out.println("function change() {"); 
				out.println("if(document.Form1.chk_app.checked==true && document.Form1.chk_rej.checked==true){");
				out.println("document.Form1.chk_app.value='Y'");
				out.println("document.Form1.chk_rej.checked=false");
				out.println("document.Form1.chk_rej.value='N'");
				out.println("}");	
				out.println("else if(document.Form1.chk_app.checked==true && document.Form1.chk_rej.checked==true){");
				out.println("document.Form1.chk_app.value='Y'");
				out.println("}");	
				out.println("else");	
				out.println("{");	
				out.println("document.Form1.chk_app.value='Y'");
				out.println("}");	
				out.println("}"); 		
				*/
				
				out.println("function change_return() {"); 
				out.println("if(document.Form1.chk_return.checked==true && ( document.Form1.chk_rej.checked==true || document.Form1.chk_app.checked==true ) ){");
				out.println("document.Form1.chk_return.value='Y'");
				out.println("document.Form1.chk_rej.checked=false");
				out.println("document.Form1.chk_rej.value='N'");
				out.println("document.Form1.chk_app.checked=false");
				out.println("document.Form1.chk_app.value='N'");
				out.println("}");	
				out.println("else if(document.Form1.chk_return.checked==true && ( document.Form1.chk_rej.checked==false && document.Form1.chk_app.checked==false ) ){");
				out.println("document.Form1.chk_return.value='Y'");
				out.println("}");	
				out.println("else");	
				out.println("{");	
				out.println("document.Form1.chk_return.value='N'");
				out.println("document.Form1.chk_return.checked=false");
				out.println("}");	
				out.println("}"); 	
				
				
				out.println("function change() {"); 
				out.println("if(document.Form1.chk_app.checked==true && ( document.Form1.chk_rej.checked==true || document.Form1.chk_return.checked==true ) ){");
				out.println("document.Form1.chk_app.value='Y'");
				out.println("document.Form1.chk_rej.checked=false");
				out.println("document.Form1.chk_rej.value='N'");
				out.println("document.Form1.chk_return.checked=false");
				out.println("document.Form1.chk_return.value='N'");
				out.println("}");	
				out.println("else if(document.Form1.chk_app.checked==true && ( document.Form1.chk_rej.checked==false && document.Form1.chk_return.checked==false ) ){");
				out.println("document.Form1.chk_app.value='Y'");
				out.println("}");	
				out.println("else");	
				out.println("{");	
				out.println("document.Form1.chk_app.value='N'");
				out.println("document.Form1.chk_app.checked=false");
				out.println("}");	
				out.println("}"); 		
				
				
				out.println("function change_reject() {"); 
				out.println("if(document.Form1.chk_rej.checked==true && ( document.Form1.chk_return.checked==true || document.Form1.chk_app.checked==true ) ){");
				out.println("document.Form1.chk_rej.value='Y'");
				out.println("document.Form1.chk_return.checked=false");
				out.println("document.Form1.chk_return.value='N'");
				out.println("document.Form1.chk_app.checked=false");
				out.println("document.Form1.chk_app.value='N'");
				out.println("}");	
				out.println("else if(document.Form1.chk_rej.checked==true && ( document.Form1.chk_return.checked==false && document.Form1.chk_app.checked==false ) ){");
				out.println("document.Form1.chk_rej.value='Y'");
				out.println("}");	
				out.println("else");	
				out.println("{");	
				out.println("document.Form1.chk_rej.value='N'");
				out.println("document.Form1.chk_rej.checked=false");
				out.println("}");	
				out.println("}"); 		
				
				
				
				/*out.println("function change_reject() {"); 
				out.println("if(document.Form1.chk_app.checked==true && document.Form1.chk_rej.checked==true){");
				out.println("document.Form1.chk_rej.value='Y'");
				out.println("document.Form1.chk_app.checked=false");
				out.println("document.Form1.chk_app.value='N'");
				out.println("}");	
				out.println("else if(document.Form1.chk_app.checked==true && document.Form1.chk_rej.checked==true){");
				out.println("document.Form1.chk_rej.value='Y'");
				out.println("}");	
				out.println("else");	
				out.println("{");	
				out.println("document.Form1.chk_rej.value='Y'");
				out.println("}");	
				out.println("}"); 
				*/
				out.println("function close_1(){");
				out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
				out.println("window.close()");
				out.println("}");
				out.println("}");
				
				
				/*----------------------------------------------------------------
						Purpose  : Add Conditions
					
					----------------------------------------------------------------*/			
				
				/* out.println("function add_row(){"); 
					out.println("b_flag=0;");
					out.println("if(lineno!=0){");
					out.println("count=lineno-1;");
					out.println("m_condition=\"TXT_CONDITION\"+count");
					out.println("if(document.Form1.elements[m_condition].value==\"\") {");
					out.println("alert('Condition can not be null.');");
					out.println("b_flag=1;");
					out.println("}");
					out.println("}");
					out.println("if(b_flag==0){");
					out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
					out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
					out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
					out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
					out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
					out.println("'<TD WIDTH=\"*%\" ></TD>' +");
					out.println("'</tr></table>';");
					out.println("lineno=lineno+1;");
					out.println("arr_size=arr_size+1;");
			out.println("}");
				out.println("}");
				*/
				
				out.println("function add_row(){"); 
				out.println("b_flag=0;");
				out.println("if(lineno!=0){");
				out.println("count=lineno-1;");
				out.println("m_condition=\"TXT_CONDITION\"+count");
				out.println("if(document.Form1.elements[m_condition].value==\"\") {");
				out.println("alert('Condition can not be null.');");
				out.println("b_flag=1;");
				out.println("}");
				out.println("}");
				out.println("if(b_flag==0){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
				out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+lineno+'	        VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+lineno+'	            VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+lineno+'	    VALUE=\"-\" >'+");
				
				out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
				//out.println("'<TD WIDTH=\"*%\" ></TD>' +");
				out.println("'</tr></table>';");
				out.println("lineno=lineno+1;");
				out.println("arr_size=arr_size+1;");
				out.println("}");
				out.println("}");
				
				
				/* out.println("function del_row(rowNo){"); 
					out.println("if(arr_size!=1){");
					out.println("var j=0;");
					out.println("for(var i=0;i<arr_size;i++){");
					out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
					out.println("m_condition=\"TXT_CONDITION\"+i");
					out.println("m_status=\"hid_TXT_STATUS\"+i");					
					out.println("if(i==rowNo)");
					out.println("continue;");
					out.println("array_follow_up_no[j]=document.Form1.elements[m_follow_up].value;");
					out.println("array_condition[j]=document.Form1.elements[m_condition].value;");
				out.println("array_status[j]=document.Form1.elements[m_status].value;");    
					out.println("j=j+1;");
					out.println("}");
					out.println("lineno=lineno-1;");
					out.println("arr_size=arr_size-1;");
				out.println("write_data(arr_size);");
					out.println("}");
				*/
				
				
				out.println("function del_row(rowNo){"); 
				out.println("if(arr_size!=1){");
				out.println("var j=0;");
				out.println("for(var i=0;i<arr_size;i++){");
				out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
				out.println("m_condition=\"TXT_CONDITION\"+i");
				out.println("m_status=\"hid_TXT_STATUS\"+i");					
				out.println("m_document=\"hid_DOCUMENT\"+i");					
				out.println("m_document_code=\"hid_DOCUMENT_CODE\"+i");					
				out.println("m_stage=\"hid_STAGE\"+i");			
				
				out.println("if(i==rowNo)");
				out.println("continue;");
				out.println("array_follow_up_no[j]       = document.Form1.elements[m_follow_up].value;");
				out.println("array_condition[j]          = document.Form1.elements[m_condition].value;");
				out.println("array_status[j]             = document.Form1.elements[m_status].value;");    
				out.println("array_document[j]           = document.Form1.elements[m_document].value;"); //added by nwuan de silva on 09-11-07
				out.println("array_stage[j]              = document.Form1.elements[m_stage].value;");
				out.println("array_document_code[j]     = document.Form1.elements[m_document_code].value;"); //added by nwuan de silva on 09-11-07
				
				
				out.println("j=j+1;");
				out.println("}");
				out.println("lineno=lineno-1;");
				out.println("arr_size=arr_size-1;");
				out.println("write_data(arr_size);");
				out.println("}");
				
				
				/* out.println("function write_data(size){");
					out.println("sum=0;");
					out.println("m_table.innerHTML=\"\";");
					out.println("header();");
			out.println(" for(var j=0;j<size;j++){");
				out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
					out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
					out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
					out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
					out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
					out.println("'<TD WIDTH=\"*%\" ></TD>' +");
					out.println("'</tr></table>';");
			out.println("continue;");
					out.println("}");
					out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
					out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
					out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
					out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
					out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
					out.println("'<TD WIDTH=\"*%\" ></TD>' +");
					out.println("'</tr></table>';");
			out.println("continue;");
					out.println("}");
					out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
					out.println("'<TD WIDTH=\"14%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"10%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</TD>'+"); //Follow up
					out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"15%\" align=\"left\">'+array_status[j]+'</TD>'+");
					out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE='+array_status[j]+'>'+");
					out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>'+");
					out.println("'<TD WIDTH=\"*%\" ></TD>' +");
					out.println("'</tr></table>';");
			out.println("continue;");
					out.println("}");
					out.println("}");		
					out.println("}");		
					out.println("}");		
				*/
				
				//added by nuwan de silva on 09-11-07----
				out.println("function write_data(size){");
				out.println("sum=0;");
				out.println("m_table.innerHTML=\"\";");
				out.println("header();");
				out.println(" for(var j=0;j<size;j++){");
				out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
				out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\"  style=\"{width:110px;}\"  disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	      VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	        VALUE=\"-\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	            VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	    VALUE=\"-\" >'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
				//out.println("'<TD WIDTH=\"*%\" ></TD>' +");
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");
				out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
				out.println("'<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" style=\"{width:110px}\" size=\"10\" onblur=\"\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\">Follow up</TD>'+");
				out.println("'<TD WIDTH=\"5%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"20%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"10%\"  align=\"left\">-</TD>'+");
				out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" value=\"'+array_condition[j]+'\" maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
				out.println("'<TD WIDTH=\"10%\" align=\"left\">-</TD>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	     VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	       VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	           VALUE=\"-\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	 VALUE=\"-\" >'+");
				out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
				//out.println("'<TD WIDTH=\"*%\" ></TD>' +");
				out.println("'</tr></table>';");
				out.println("continue;");
				out.println("}");
				
				out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
				// out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
				out.println("m_fol='<TD WIDTH=\"12%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" style=\"{width:110px}\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
				out.println("'<TD WIDTH=\"7%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=\"load_Follow('+j+')\" >Follow up</TD>';"); //Follow up
				
				out.println("if(array_stage[j]==\"-\"){");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">-</TD>';");
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" >'+array_document[j]+'</TD>';");
				out.println("}");
				out.println("else {");
				out.println("m_type='<TD WIDTH=\"5%\"  align=\"left\">Document</TD>';");
				out.println("m_doc='<TD WIDTH=\"20%\"  align=\"left\" style=\"{cursor:hand;}\" onclick=show_document_drill(\"'+array_document_code[j]+'\") ><u>'+array_document[j]+'</u></TD>';");
				out.println("}");
				
				out.println("m_stage='<TD WIDTH=\"10%\"  align=\"left\">'+array_stage[j]+'</TD>';");
				out.println("m_condition='<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:250px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>';");
				out.println("m_status='<TD WIDTH=\"10%\" align=\"left\">'+array_status[j]+'</TD>';");
				out.println("m_button='<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>';");
				out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	        VALUE='+array_status[j]+'>'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT'+j+'	          VALUE=\"'+array_document[j]+'\">'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_STAGE'+j+'	              VALUE=\"'+array_stage[j]+'\" >'+");
				out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_DOCUMENT_CODE'+j+'	      VALUE=\"'+array_document_code[j]+'\" >';");
				
				out.println("m_writedata='<TR>'+m_fol+m_type+m_doc+m_stage+m_condition+m_status+m_button+'</TR>'+m_hid_input;"); 
				out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("continue;");
				out.println("}");
				out.println("}");		
				out.println("}");		
				out.println("}");		
				
				//_____________________________________________________________________________________________________________________________
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),get_conditions(),get_remarks('M1'),validate_nic_no()\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_data_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_remarks' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MK_APP_STATUS_APPROVE_1\">"); 
				
				
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 327px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				
				
				out.println("<tr>"); 
				
				if(m_pre_stage.equals("ENT_CON")){
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Verification Approval</td>"); 
				}
				
				if(m_pre_stage.equals("V-APP")){
					
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 1</td>"); 
					
				}
				else if(m_pre_stage.equals("VERIFY-M")){
					
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - Credit Approval 2</td>"); 
				}
				
				out.println("</tr>"); 
				
				
				
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  
				
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");
				out.println(" <tr> ");
				out.println("  <td colspan=\"5\"><div align=\"center\"><b>"+m_orient_name.toUpperCase()+"</b><br> ");
				out.println("   <b>SANCTION LETTER<b></div></td>");
				out.println(" </tr>");
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_client_data);
				
				more = rs.next();		
				
				if(more){
					
					
					String m_client_code =rs.getString(2);
					String m_name        =rs.getString(3);
					String m_client_add1 =rs.getString(4);
					String m_client_add2 =rs.getString(5);
					String m_reg_no      =rs.getString(6);
					String m_officer      =rs.getString(7);
					String m_industry    =rs.getString(8);			
					String m_sys_date    =rs.getString(9);
					String m_finance_no  =rs.getString(10);
					String m_mas_lease_no=rs.getString(11);
					String m_broker      =rs.getString(12);
					String m_mk_officer  =rs.getString(13);
					String m_sub_sector  =rs.getString(14);
					String m_branch_code =rs.getString(15);
					String m_branch_desc =rs.getString(16);
					String m_sub_sector_desc =rs.getString(17);
					String m_city_name   =rs.getString(18); //Added by Chandana on 03/08/2007 for Ref No.764 
					String m_address="";
					
					// added by udara on 10-04-2013
					//String m_insurance_agent  =rs.getString(23);
					double m_sum_insured      =rs.getDouble(24);
					String m_veh_no           =rs.getString(25);
					String m_vendor_code      =rs.getString(26);
					
					String m_security_details      =rs.getString(27); // added by udara on 15-07-2013
					String m_broker_code      =rs.getString(28);
					// end by udara on 10-04-2013
					
					if(!m_client_add1.equals("-") && !m_client_add2.equals("-")){
						m_address=m_client_add1+", "+m_client_add2+", "+m_city_name;
					}
					else if(!m_client_add1.equals("-")){
						m_address=m_client_add1+", "+m_city_name;
					}
					rs.close();
					
					rs = stmt.executeQuery(sql_co_applicant);
					more = rs.next();		
					String m_co_applicant="";
					if(more){
						m_co_applicant=rs.getString(1);
					}
					
					
					Sql_data_exposure_prev="SELECT "+												
						"	  A.ARREARS,C.ODI,B.NIL "+
						"		FROM  "+
						"		(SELECT "+
						"		NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"') AND "+
						"		A.ACTIVE_STATUS='Y' AND "+
						"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
						"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
						
						/*"		(SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"		"+m_schema_name+".AF_CO_PRO_INVOICE C     "+
						"		WHERE B.CLIENT_CODE=C.CLIENT_CODE(+) AND "+
						"		UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND"+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND "+ 
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND "+
						"	 (C.BALANCE_TO_BE_RECEIVED>0  OR "+
						"		A.INVOICE_NO IS NULL))B, "+
						*/
						
						//"	  (SELECT SUM(NIL) NIL "+
						//"		FROM "+
						
						"		(SELECT  "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL  "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"			A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND  "+
						"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		A.INVOICE_NO IS NULL ) B, "+
						
						/*"		UNION     "+                             
						"		SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0)  NIL "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND  "+
						"		A.INVOICE_NO IN ( "+
						"		SELECT "+
						"		INVOICE_NO "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
						"		WHERE INVOICE_TYPE='INV_GENER' AND "+
						"		ACTIVE_STATUS='Y' AND "+
						"		BALANCE_TO_BE_RECEIVED>0 ))) B, "+
						*/
						
						
						"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
						"		WHERE INVOICE_NO IN   "+
						"		(SELECT   "+
						"		INVOICE_NO   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND "+
						"		A.ACTIVE_STATUS='Y'  "+
						"		) )C        ";
					
					
					Sql_data_exposure_current="  SELECT "+												
						"	  A.ARREARS,C.ODI,B.NIL "+
						"		FROM  "+
						"		(SELECT "+
						"		NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"') AND "+
						"		A.ACTIVE_STATUS='Y' AND "+
						"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
						"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
						
						//"	  (SELECT SUM(NIL) NIL "+
						//"		FROM "+
						"		(SELECT  "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL  "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"			A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND  "+
						//"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		A.INVOICE_NO IS NULL ) B, "+
						
						/*"		UNION     "+                             
						"		SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0)  NIL "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND  "+
						"		A.INVOICE_NO IN ( "+
						"		SELECT "+
						"		INVOICE_NO "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
						"		WHERE INVOICE_TYPE='INV_GENER' AND "+
						"		ACTIVE_STATUS='Y' AND "+
						"		BALANCE_TO_BE_RECEIVED>0 ))) B, "+
						*/
						
						
						
						/*		
							"		(SELECT "+
							"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL "+
							"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+
							"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
							"		"+m_schema_name+".AF_CO_PRO_INVOICE C     "+
							"		WHERE B.CLIENT_CODE=C.CLIENT_CODE(+) AND "+
							"		UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND"+
							"		A.APPLICATION_NO=B.APPLICATION_NO AND "+ 
							"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND "+
							"	 (C.BALANCE_TO_BE_RECEIVED>0  OR "+
							"		A.INVOICE_NO IS NULL))B, "+
						*/		
						
						"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
						"		WHERE INVOICE_NO IN   "+
						"		(SELECT   "+
						"		INVOICE_NO   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND "+
						"		A.ACTIVE_STATUS='Y'  "+
						"		) )C        ";
					
					
					
					
					out.println(" <tr>");
					out.println("  <td colspan=\"5\"><table width=\"100%\"  border=\"0\"  cellspacing=\"0\" cellpadding=\"0\">");
					out.println("   <tr>");
					out.println("    <td width=\"18%\"><b>Application No</b> </td>");
					out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
					out.println("    <td width=\"27%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_applicaton_no+"')\" ><u>"+m_applicaton_no+"</u></td>");
					out.println("    <td width=\"17%\"><b>Agreement No </td>");
					out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
					out.println("    <td width=\"17%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+m_finance_no+"')\" ><u>"+m_finance_no+"</u></td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Introduced by </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_broker+"</td>");
					out.println("    <td><b>Master Lease Agreement No </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+m_mas_lease_no+"')\" ><u>"+m_mas_lease_no+"</u></td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Broker</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					//out.println("    <td>"+m_broker_name+"</td>"); // commented by udara on 11-06-2013
					//out.println("    <td>"+m_broker+"</td>"); // added by udara 11-06-2013
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_broker_det('"+m_broker_code+"')\" ><u>"+m_broker+"</u></td>");
					
					out.println("    <td>&nbsp;</td>");
					out.println("    <td><div align=\"center\"></div></td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Date</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_sys_date+"</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td><div align=\"center\"></div></td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Client </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+m_client_code+"')\" ><u>"+m_name+"</u></td>");
					out.println("    <td><b>Sector/Industry</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("      <td>"+m_sub_sector_desc+"</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Address </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_address+" </td>");
					out.println("      <td><b>Credit Rating </td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>-</td>");
					out.println("     </tr>");
					
					out.println("     <tr>");
					out.println("       <td><b>Reg/NIC No</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					//out.println("       <td>"+m_reg_no+"</td>");
					out.println("       <td><div id=m_nic_no>"+m_reg_no+"</div></td>");
					out.println("    <td><b>Branch Name</td>");
					out.println("    <td align=\"center\">:</td>");
					out.println("    <td>"+m_branch_desc+"</td>");
					out.println("   </tr>");
					
					
					
					out.println("     <tr>");
					out.println("       <td><b>Co-Applicant</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_co_applicant+"</td>");
					out.println("    <td><b>Marketing Officer</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_mk_officer+"</td>");
					out.println("   </tr>");
					
					// added by udara on 10-04-2013
					out.println("     <tr>");
					out.println("       <td><b>Vehicle No</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_veh_no+"</td>");
					out.println("    <td><b>Vendor Name</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_vendor_code+"</td>");
					out.println("   </tr>");
					
					out.println("     <tr>");
					out.println("       <td><b>Sum Insured</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+nf.format(m_sum_insured)+"</td>");
					out.println("    <td><b>Insurance Agent</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_insurance_agent+"</td>");
					out.println("   </tr>");
					// end by udara 10-04-2013
					
					// added by udara on 15-07-2013
					
					out.println("     <tr>");
					out.println("       <td><b> Security Details </td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>  "+m_security_details+" </td>");
					out.println("    <td><b> </td>");
					out.println("    <td>  &nbsp; </td>");
					out.println("    <td>  &nbsp; </td>");
					out.println("   </tr>");
					
					// end by udara on 15-07-2013
					
					/*out.println("     <tr>");
					out.println("       <td><b>Co-Applicant</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_co_applicant+"</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");	
				*/
					
					
					
					out.println("  </table></td>");
					out.println(" </tr>");
					
				}
				
				
				
				
				//========Guarantors==============================================
				out.println(" <tr>");
				out.println("  <td colspan=\"5\"><table width=\"100%\"  class='table'  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("   <tr>");
				out.println("    <td width=\"24%\" valign='top' ><b>Guarantors</b></td>");
				out.println("    <td width=\"76%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_data_guarantor);
				
				more = rs.next();		
				
				if(!more){
					out.println("     <tr height=\"18\">");
					out.println("     <td width=\"11%\" ><div align=\"center\">&nbsp;</div></td>");
					out.println("     <td width=\"89%\" >&nbsp;</td>");
					out.println("     </tr>");
					
				}
				int i=1;
				while(more){
					
					out.println("     <tr height=\"18\">");
					out.println("     <td width=\"11%\"><div align=\"center\">"+i+"</div></td>");
					out.println("     <td width=\"89%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(1)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("     </tr>");
					more = rs.next();		
					i=i+1;
				}
				
				//out.println("     <tr>");
				//out.println("      <td><div align=\"center\">2</div></td>");
				//out.println("      <td>Mrs.Perera</td>");
				//out.println("     </tr>");
				
				out.println("    </table></td>");
				out.println("   </tr>");
				out.println("   <tr>");
				
				//==============end of guarantors============================================		
				out.println("    <td colspan=\"2\">&nbsp;</td>");
				out.println("    </tr>");
				
				//start of the exposure with OFSCL===========================================
				out.println("   <tr>");
				out.println("    <td colspan=\"2\"><b>Total exposure with "+m_schema_name+" </b></td>");
				out.println("    </tr>");
				out.println("   <tr>");
				out.println("    <td colspan=\"2\" valign=\"top\"><table width=\"100%\" class='table'   border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("     <tr>");
				out.println("      <td width=\"47%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("       <tr>");
				out.println("        <td width=\"31%\"><b>Exposure</b></td>");
				out.println("        <td width=\"25%\">&nbsp;</td>");
				out.println("        <td width=\"19%\">&nbsp;</td>");
				out.println("        <td width=\"25%\">&nbsp;</td>");
				out.println("       </tr>");
				out.println("       <tr>");
				out.println("        <td>&nbsp;</td>");
				out.println("        <td><div align=\"center\">Arrears</div></td>");
				out.println("        <td><div align=\"center\">ODI</div></td>");
				out.println("        <td><div align=\"center\">NIL</div></td>");
				out.println("       </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_data_exposure_prev);
				
				double tot_arrears=0;
				double tot_odi=0;
				double tot_nil=0;
				
				more = rs.next();		
				
				if(more){
					
					out.println("       <tr>");
					out.println("        <td>Client</td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(1))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("       </tr>");
					tot_arrears=tot_arrears+rs.getDouble(1);
					tot_odi=tot_odi+rs.getDouble(2);
					tot_nil=tot_nil+rs.getDouble(3);
				}
				
				out.println("       <tr>");
				out.println("        <td>Group</td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("       </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_data_exposure_current);
				more = rs.next();		
				
				if(more){
					
					out.println("       <tr>");
					out.println("        <td>Transaction</td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(1))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("       </tr>");
					
					tot_arrears=tot_arrears+rs.getDouble(1);
					tot_odi=tot_odi+rs.getDouble(2);
					tot_nil=tot_nil+rs.getDouble(3);
				}
				
				
				out.println("       <tr>");
				out.println("        <td>Total</td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_arrears)+"</div></td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_odi)+"</div></td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_nil)+"</div></td>");
				out.println("       </tr>");
				out.println("      </table></td>");
				out.println("      <td width=\"11%\">&nbsp;</td>");
				
				//=============end total exposure with OFSCL=====================================================
				
				//====start facility=============================================================================
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_data_facility_type);
				
				more = rs.next();		
				
				out.println("      <td width=\"23%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("       <tr>");
				out.println("        <td><div align=\"center\">Type of facility </div></td>");
				out.println("       </tr>");
				if(more){	
					out.println("       <tr>");
					out.println("        <td><div align=\"center\">"+rs.getString(1)+"</div></td>");
					out.println("       </tr>");
				}
				out.println("      </table></td>");
				//====end facility===========================================
				out.println("      <td width=\"19%\">&nbsp;</td>");
				out.println("     </tr>");
				out.println("     <tr>");
				out.println("      <td colspan=\"4\">&nbsp;</td>");
				out.println("      </tr>");
				//start asset details=====================================================
				out.println("     <tr>");
				out.println("      <td colspan=\"4\"><b>Asset Details</b> </td>");
				out.println("     </tr>");
				out.println("     <tr>");
				out.println("      <td colspan=\"3\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
				
				out.println("       <tr>");
				out.println("        <td width=\"18%\"><b>Make</b></td>");
				out.println("        <td width=\"15%\"><b>Model</b></td>");
				out.println("        <td width=\"10%\"><b>Engine No </b></td>");
				out.println("        <td width=\"15%\"><b>Chass.No/Serial No</b></td>");
				out.println("        <td width=\"14%\"><b>Status</b></td>");
				out.println("        <td width=\"17%\"><b>No.of units </b></td>");
				out.println("        <td width=\"11%\"><b>Asset Cover </b></td>");
				out.println("       </tr>");
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				rs = stmt.executeQuery(Sql_data_asset_details);
				
				more = rs.next();		
				double asset_cover=0;
				while(more){
					
					
					rs_rental = stmt_rental.executeQuery("  SELECT "+												
						" NVL(VALUE,0) "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
						" WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
						" A.ASSET_ID='"+rs.getString(3)+"' AND "+
						" A.ACTIVE_STATUS='Y' ");
					
					if(rs_rental.next()){
						
						if(rs_rental.getDouble(1)>0){
							//asset_cover=(rs.getDouble(12)/rs_rental.getDouble(1))*100;
							asset_cover=(rs_rental.getDouble(1)/rs.getDouble(12))*100; //modified by nuwan de silva 04-jul-07
						}
					}
					
					out.println("       <tr height=\"18\">");
					out.println("        <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_make_details_drill('"+rs.getString(4)+"')\" ><u>"+rs.getString(5)+"</u></td>");
					out.println("        <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model_details_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(7)+"</u></td>");
					out.println("        <td>"+rs.getString(8)+"</td>");
					out.println("        <td>"+rs.getString(9)+"</td>");
					out.println("         <td>"+rs.getString(11)+"</td>");
					out.println("          <td>"+rs.getString(10)+"</td>");
					
					out.println("          <td>"+nf.format(asset_cover)+" %</td>");
					
					out.println("         </tr>");
					
					more = rs.next();
				}
				
				out.println("       </table></td>");
				out.println("       <td>&nbsp;</td>");
				out.println("      </tr>");
				
				out.println("      <tr>");
				out.println("         <td colspan=\"4\">&nbsp;</td>");
				out.println("       </tr>");
				out.println("        <tr>");
				out.println("         <td colspan=\"4\"><b>Valuation Details</b></td>");
				out.println("       </tr>");
				out.println("        <tr>");
				out.println("          <td><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
				out.println("            <tr>");
				out.println("              <td width=\"31%\">Valuer</td>");
				out.println("              <td width=\"26%\">Year</td>");
				out.println("              <td width=\"26%\">Market Value </td>");
				out.println("              <td width=\"17%\">FSV</td>");
				out.println("            </tr>");
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_valuation_details);
				
				more = rs.next();		
				if(!more){
					out.println("            <tr>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("            </tr>");
				}
				while(more){
					out.println("            <tr height=\"18\">");
					out.println("             <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuer_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(3)+"</u></td>");
					out.println("             <td>"+rs.getString(4)+"</td>");
					out.println("             <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_drill('"+rs.getString(1)+"')\" ><u>"+nf1.format(rs.getDouble(5))+"</u></td>");
					out.println("             <td>"+nf1.format(rs.getDouble(6))+"</td>");
					out.println("            </tr>");
					more = rs.next();
				}
				
				out.println("          </table></td>");
				out.println("          <td colspan=\"3\">&nbsp;</td>");
				out.println("          </tr>");
				out.println("        <tr>");
				out.println("           <td colspan=\"4\">&nbsp;</td>");
				out.println("         </tr>");
				out.println("         <tr>");
				out.println("           <td colspan=\"4\"><b>Facility Details</b></td>");
				out.println("         </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_facility_details);
				
				more = rs.next();		
				String m_pricing_no=rs.getString(1);
				
				
				
				
				while(more){
					
					m_pricing_no=rs.getString(1);
					total_charges=0;
					
					out.println("				 <tr>");
					out.println(" <p style=page-break-after:always></p>");
					out.println("       <td height=\"8%\" colspan=\"4\"></p>");
					
					
					out.println("	<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("              <tr> ");
					out.println("                <td colspan=\"3\">&nbsp;</td>");
					out.println("                <td width=\"27%\"  rowspan=\"9\" valign=\"top\"><table width=\"100%\" class='table' bordercolor='black'  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                  <tr>");
					out.println("                    <td width=\"45%\" ><div align=\"center\">Gross</div></td>");
					out.println("                    <td width=\"30%\" ><div align=\"center\">Net</div></td>");
					out.println("                    <td width=\"25%\" ><div align=\"center\">VAT</div></td>");
					out.println("                   </tr>");
					out.println("                   <tr>");
					out.println("                     <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("                     <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("                    <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(4))+"</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("                <td width=\"45%\"><div align=\"right\"><b>"+rs.getString(5)+" %VAT</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(6))+"</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+rs.getInt(7)+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(9))+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(10))+"</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(11))+"</div></td>");
					out.println("            </tr>");
					out.println("            <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(12))+"</div></td>");
					out.println("             <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+rs.getInt(13)+"</div></td>");
					out.println("              <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td colspan=\"3\"><strong>Rental Stream</strong></td>");
					out.println("             </tr>");
					out.println("           <tr>");
					out.println("            <td width=\"45%\"><div align=\"center\"><strong>Gross</strong></div></td>");
					out.println("             <td width=\"30%\" class=\"style1\"><div align=\"center\"><b>Net</b></div></td>");
					out.println("             <td width=\"25%\" class=\"style1\"><div align=\"center\"><b>VAT</b></div></td>");
					out.println("           </tr>");
					out.println("         </table></td>");
				//	out.println("         <td colspan=\"2\">Expenses included in to the Lease Rental </td>");
					out.println("       </tr>");
					out.println("       <tr>");
					out.println("          <td colspan=\"3\">Net Cost</td>");
					out.println("         <td width=\"35%\" rowspan=\"7\" valign=\"top\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
						"  PRICING_NO, "+ //1
						"  SUB_CHAGE_CODE, "+ //2
						"  DESCRIPTION, "+ //3
						"  SUM(AMOUNT) "+ //4
						//"  CHARGE_TYPE "+
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
						"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  PRICING_NO='"+m_pricing_no+"'  AND "+
						"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
						"  NVL(AMOUNT,0) <> 0 "+
						"  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION ");			
					
					
					
					
					
					
					
					//if(rs_charges.next()){
					
					/*out.println("  <tr> ");
					out.println("             <td width=\"40%\">Charge Type</td> ");
					out.println("             <td width=\"12%\" align=\"right\">Year1</td> ");
					//out.println("            <td width=\" 12%\"><div align=\"right\">Year2</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">Year3</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">Year4</div></td> "); // commented by udara 17-10-2013
					// out.println("             <td width=\"12%\"><div align=\"right\">Year5</div></td> "); // commented by udara 17-10-2013
					out.println("  </tr> ");
					
					
					//}
					
					while(rs_charges.next()){
						
						
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");
						out.println("            <td width=\"12%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+rs_charges.getString(2)+"')\" ><div align=\"right\"><u>"+nf1.format(rs_charges.getDouble(4))+"</u></div></td>");
						//out.println("            <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013 
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						total_charges=total_charges+rs_charges.getDouble(4);
					}
					*/
					
					
					rs_charges.close();
					stmt_charges.close();
					stmt_charges = conn.createStatement ();
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
						"  PRICING_NO, "+ //1
						"  SUB_CHAGE_CODE, "+ //2
						"  DESCRIPTION, "+ //3
						"  SUM(AMOUNT) ,"+ //4
						"  YEAR_NO+1 "+ //5
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
						"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  PRICING_NO='"+m_pricing_no+"'  AND "+
						"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
						"  NVL(AMOUNT,0) <> 0 "+
						"  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,YEAR_NO "+
						"  ORDER BY SUB_CHAGE_CODE ,YEAR_NO ");
					
					boolean more_maintaince=rs_charges.next();
					
					String m_code="";
					if(more_maintaince){
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">Maintaince</td> ");
						out.println("             <td width=\"12%\" align=\"right\">&nbsp;</td> ");
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
						m_code=rs_charges.getString(2);
						
					}
					
					
					int year=1;
					while(more_maintaince){
						
						if(rs_charges.getString(2).equals(m_code)){
							out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
							
							if(year==1){
								total_charges=total_charges+rs_charges.getDouble(4);
							}
							if(year==2){
								sum_year2=sum_year2+rs_charges.getDouble(4);
							}
							if(year==3){
								sum_year3=sum_year3+rs_charges.getDouble(4);
							}
							if(year==4){
								sum_year4=sum_year4+rs_charges.getDouble(4);
							}
							if(year==5){
								sum_year5=sum_year5+rs_charges.getDouble(4);
							}
							
						}
						else{
							out.println("  </tr> ");
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
							out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
							year=1;
							total_charges=total_charges+rs_charges.getDouble(4);
							m_code=rs_charges.getString(2);
							
						}
						more_maintaince=rs_charges.next();
						year=year+1; 
						//total_charges=total_charges+rs_charges.getDouble(4);
					}
					
					
					/*out.println("  <tr> ");
					out.println("             <td width=\"40%\">Total</td> ");
					out.println("              <td width=\"12%\"><div align=\"right\">"+nf1.format(total_charges)+"</div></td>");
					//out.println("            <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year2)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year3)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year4)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year5)+"</div></td> "); // commented by udara 17-10-2013
					out.println("  </tr> ");
					*/
					
					
					out.println("            </table></td>");
					
					out.println("            <td width=\"12%\">&nbsp;</td>");
					out.println("          </tr>");
					out.println("         <tr>");
					out.println("            <td colspan=\"3\">Rental Based on</td>");
					out.println("           <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Period ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("           <tr>");
					out.println("            <td>AMI</td>");
					out.println("            <td width=\"10%\">&nbsp;</td>");
					out.println("            <td width=\"7%\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("              <tr>");
					out.println("                <td><div align=\"center\"><b>"+rs.getInt(14)+"</div></td>");
					out.println("              </tr>");
					out.println("            </table></td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("             <td colspan=\"3\">NIBSM</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Supplier Credit ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\"><strong>Installment Structure</strong></td>");
					out.println("           <td>&nbsp;</td>");
					out.println("         </tr>");
					out.println("         <tr>");
					out.println("           <td colspan=\"3\">&nbsp;</td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					/*String sql_nibsm=" SELECT  "+
						" SUM(NIBSM), "+
						" MAX(AMI), "+
					" MAX(PERIOD) "+	
						" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
						" WHERE UPPER(APPLICATION_NO)= UPPER('"+m_applicaton_no+"') ";
					//" AND  ENT_DATE IN(SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"'))";//Added By Sandun on 24-06-2009
					*/
					
					String sql_nibsm=" SELECT  "+
						" SUM(NIBSM), "+
						" MAX(AMI), "+
						" MAX(PERIOD) "+	
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
						" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
						" AND A.APPLICATION_NO=B.APPLICATION_NO "+
						" AND A.INVOICE_NO=B.PRO_INVOICE_NO "+
						" AND A.ACTIVE_STATUS='Y' ";
					
					
					rs_rental = stmt_rental.executeQuery(sql_nibsm);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
					{
						m_NIBSM=rs_rental.getDouble(1);
						m_AMI=rs_rental.getInt(2);
						m_PERIOD=rs_rental.getInt(3);
					}
					
					if(m_NIBSM>0)
					{
						no_of_records=m_PERIOD-1;
					}
					else{
						no_of_records=m_PERIOD;
					}
					
					rs_rental.close();
					stmt_rental.close();
					stmt_rental=conn.createStatement ();
					
					
					
					String sql_rent_new="   SELECT  "+
						" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
						" SUM(NET_RENTAL_AMOUNT),  "+//2
						" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
						" SUM(GRENTAL_AMOUNT) "+//4
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
						" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+//m_pricing_no
						" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
						" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+ //,RENTAL_DATE,,PRICING_NO
						" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
					
					
					
					int end=0;
					int start=0;
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					more3 =rs_rental.next();
					
					if(more3)
					{
						m_rental_new=rs_rental.getDouble(2);
						start=rs_rental.getInt(1);
						m_vat_new=rs_rental.getDouble(3);
						m_gross_new=rs_rental.getDouble(4);
						
						
						while(more3) //START INSTALLMENT LOOP
						{
							
							
							if(m_rental_new!=rs_rental.getDouble(2))
							{
								
								
								
								out.println("         <tr>");
								out.println("           <td width=\"11%\">&nbsp;</td>");
								//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
								if(start==end){
									out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
								}
								else{
									out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
								}
								
								
								out.println("           <td valign=\"top\"><div align=\"right\">");
								out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
								out.println("                 </tr>");
								out.println("                               </table>");
								out.println("           </div></td>");
								out.println("           <td colspan=\"2\">&nbsp;</td>");
								out.println("         </tr>");
								
								
								
								
								start=rs_rental.getInt(1);		
								m_rental_new=rs_rental.getDouble(2);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
								count_period=0;
							}
							
							count_period=count_period+1;
							end=rs_rental.getInt(1);
							
							more3=rs_rental.next();
							
							if(!more3)
							{
								break;
							}
							
						}
						
						out.println("         <tr>");
						out.println("           <td width=\"11%\">&nbsp;</td>");
						//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
						if(start==end){
							out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
						}
						else{
							out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"   </strong></td>");
						}
						out.println("           <td valign=\"top\"><div align=\"right\">");
						out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                 <tr>");
						out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
						out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
						out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
						out.println("                 </tr>");
						out.println("                               </table>");
						out.println("           </div></td>");
						out.println("           <td colspan=\"2\">&nbsp;</td>");
						out.println("         </tr>");
						
						
						
						
					} //END OF INSTALLMENT LOOP
					
					if(m_NIBSM>0){
						
						
						sql_rent_new="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
							" SUM(NET_RENTAL_AMOUNT),  "+//2
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
							" SUM(GRENTAL_AMOUNT) "+//4
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
							" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
							" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+ //
							" TO_NUMBER(INSTALLMENT_NO) ="+m_PERIOD+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO),PRICING_NO "+ //,RENTAL_DATE
							" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
						
						rs_rental = stmt_rental.executeQuery(sql_rent_new);
						
						more3 =rs_rental.next();
						
						if(more3){	
							out.println("         <tr>");
							out.println("           <td width=\"11%\">&nbsp;</td>");
							out.println("           <td colspan=\"2\"><strong>Final rentals of </strong></td>");
							out.println("           <td valign=\"top\"><div align=\"right\">");
							out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
							out.println("                 </tr>");
							out.println("                               </table>");
							out.println("           </div></td>");
							out.println("           <td colspan=\"2\">&nbsp;</td>");
							out.println("         </tr>");
						}
					}
					
					
					out.println("       <tr>");
					out.println("          <td colspan=\"6\">&nbsp;</td>");
					out.println("         </tr>");
					out.println("     </table>");
					
					
					out.println("        <tr>");
					//out.println("	  <p style=page-break-after:always></p>");
					out.println("           <td colspan=\"4\"></p><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("             <tr>");
					out.println("                <td width=\"29%\"><b>True Interst Rate (TIR) </td>");
					out.println("                <td width=\"11%\"><div align=\"right\"><b>"+nf.format(rs.getDouble(15))+"%</div></td>");
					out.println("                <td width=\"60%\">&nbsp;</td>");
					out.println("              </tr>");
					
					
					Sql_data_total_income="  SELECT "+												
						" A.APPLICATION_NO, "+
						" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
						" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
						" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
						" AND UPPER(A.PRICING_NO)=UPPER('"+m_pricing_no+"')  "+
						" GROUP BY A.APPLICATION_NO ";
					
					
					rs_rental = stmt_rental.executeQuery(Sql_data_total_income);
					//out.println("pricing No"+m_pricing_no);
					more3 =rs_rental.next();
					
					if(more3){
						
						out.println("              <tr>");
						out.println("                <td><b>Total income earned from the transaction </td>");
						out.println("               <td><div align=\"right\"><b>"+nf1.format(rs_rental.getDouble(2))+"</div></td>");
						out.println("               <td>&nbsp;</td>");
						out.println("             </tr>");
					}				
					
					out.println("           </table></td>");
					out.println("         </tr>");
					
					
					
					more = rs.next();		
					// count_period=0;
					
				}
				
				
				
				
				//___________________________total figure____________________________________________________________________________________
				
				/*	if(_m_tmp_pricing_count > 1 ) {
					
								
											Sql_data_facility_details_sum="  SELECT "+												
											//"  A.PRICING_NO, "+ //1
											"  '' ,"+
											"  SUM(A.TOTAL_AMOUNT), "+ //2
											"  SUM(A.NET_PRICE), "+ //3
											"  SUM(A.VAT) , "+ //4
											"  MAX(B.VAT_PERCENTAGE) , "+ //5
											"  SUM(ROUND(((B.VAT_PERCENTAGE - B.VAT_APP)/100)*B.NET_AMOUNT +B.NET_AMOUNT,0)) NET_APP, "+ //6
											"  MAX(PERIOD), "+ //7
											"  DECODE(MAX(B.PAYMENT_INTERVAL),'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks') INTERVEL, "+ //8
											"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT),0) "+
												"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
												"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
												"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
												"  AND   PRICING_NO     = B.PRICING_NO "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_GROSS, "+ //9   
												"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(NET_RENTAL_AMOUNT),0) "+
												"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
												"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
												"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
												"  AND   PRICING_NO     = B.PRICING_NO "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_NET, "+ //10   
												"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
												"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
												"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
												"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
												"  AND   PRICING_NO     = B.PRICING_NO "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
												"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_VAT, "+ //11
												"  SUM(B.NIBSM) , "+ //12
											"  MAX(B.SUPPLIER_CREDIT), "+ //13
												"  MAX(B.AMI), "+ //14
												"  MAX(B.RATE) "+ //15
											"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
											"  "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL C  "+
											"  WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
											"  A.APPLICATION_NO=B.APPLICATION_NO AND "+
											"  A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
											"  C.DURATION=B.PAYMENT_INTERVAL AND "+
												"  A.ACTIVE_STATUS='Y' ";
											
											
											
											
											
					rs.close();
					stmt.close();
					stmt = conn.createStatement ();
					rs = stmt.executeQuery(Sql_data_facility_details_sum);
					more = rs.next();		
					
					while(more){
					total_charges=0;
					out.println("				 <tr>");
					out.println(" <p style=page-break-after:always></p>");
					out.println("       <td height=\"8%\" colspan=\"4\"></p>");
					
					
					out.println("	<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("              <tr> ");
					out.println("                <td colspan=\"3\">&nbsp;</td>");
					out.println("                <td width=\"27%\"  rowspan=\"9\" valign=\"top\"><table width=\"100%\" class='table' bordercolor='black'  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                  <tr>");
					out.println("                    <td width=\"45%\" ><div align=\"center\">Gross</div></td>");
					out.println("                    <td width=\"30%\" ><div align=\"center\">Net</div></td>");
					out.println("                    <td width=\"25%\" ><div align=\"center\">VAT</div></td>");
					out.println("                   </tr>");
					out.println("                   <tr>");
					out.println("                     <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("                     <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("                    <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(4))+"</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("                <td width=\"45%\"><div align=\"right\"><b>"+rs.getString(5)+" %VAT</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(6))+"</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+rs.getInt(7)+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(9))+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(10))+"</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(11))+"</div></td>");
					out.println("            </tr>");
					out.println("            <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(12))+"</div></td>");
					out.println("             <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+rs.getInt(13)+"</div></td>");
					out.println("              <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td colspan=\"3\"><strong>Rental Stream</strong></td>");
					out.println("             </tr>");
					out.println("           <tr>");
					out.println("            <td width=\"45%\"><div align=\"center\"><strong>Gross</strong></div></td>");
					out.println("             <td width=\"30%\" class=\"style1\"><div align=\"center\"><b>Net</b></div></td>");
					out.println("             <td width=\"25%\" class=\"style1\"><div align=\"center\"><b>VAT</b></div></td>");
					out.println("           </tr>");
					out.println("         </table></td>");
					out.println("         <td colspan=\"2\">Expenses included in to the Lease Rental </td>");
					out.println("       </tr>");
					out.println("       <tr>");
					out.println("          <td colspan=\"3\">Net Cost</td>");
					out.println("         <td width=\"35%\" rowspan=\"7\" valign=\"top\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
					//"  PRICING_NO, "+ //1
					"  '' ,"+
					"  SUB_CHAGE_CODE, "+ //2
					"  DESCRIPTION, "+ //3
					"  SUM(AMOUNT) "+ //4
					//"  CHARGE_TYPE "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
					"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
					"  PRICING_NO='"+m_pricing_no+"'  AND "+//Un Commented by Sandun on 16-12-2008
					"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
					"  NVL(AMOUNT,0) <> 0 "+
					"  GROUP BY SUB_CHAGE_CODE,DESCRIPTION ");			
					
					//if(rs_charges.next()){
					
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">Charge Type</td> ");
					out.println("             <td width=\"12%\" align=\"right\">Year1</td> ");
					out.println("            <td width=\" 12%\"><div align=\"right\">Year2</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">Year3</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">Year4</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">Year5</div></td> ");
					out.println("  </tr> ");
					
					
					//}
					
					while(rs_charges.next()){
					
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");
					out.println("            <td width=\"12%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+rs_charges.getString(2)+"')\" ><div align=\"right\"><u>"+nf1.format(rs_charges.getDouble(4))+"</u></div></td>");
					out.println("            <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("  </tr> ");
					
					
					total_charges=total_charges+rs_charges.getDouble(4);
					}
					
					rs_charges.close();
					stmt_charges.close();
					stmt_charges = conn.createStatement ();
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
					//"  PRICING_NO, "+ //1
					" '' ,"+
					"  SUB_CHAGE_CODE, "+ //2
					"  DESCRIPTION, "+ //3
					"  SUM(AMOUNT) ,"+ //4
					"  YEAR_NO+1 "+ //5
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
					"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
					"  PRICING_NO='"+m_pricing_no+"'  AND "+ //Un Commented by Sandun on 16-12-2008
					"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
					"  NVL(AMOUNT,0) <> 0 "+
					"  GROUP BY  SUB_CHAGE_CODE,DESCRIPTION,YEAR_NO "+
					"  ORDER BY SUB_CHAGE_CODE ,YEAR_NO ");
					
					boolean more_maintaince=rs_charges.next();
					
					String m_code="";
					if(more_maintaince){
					
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">Maintaince</td> ");
					out.println("             <td width=\"12%\" align=\"right\">&nbsp;</td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");
					out.println("  </tr> ");
					
					
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
					m_code=rs_charges.getString(2);
					
					}
					
					
					int year=1;
					while(more_maintaince){
					
					if(rs_charges.getString(2).equals(m_code)){
					out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
					
					if(year==1){
					total_charges=total_charges+rs_charges.getDouble(4);
					}
					if(year==2){
					sum_year2=sum_year2+rs_charges.getDouble(4);
					}
					if(year==3){
					sum_year3=sum_year3+rs_charges.getDouble(4);
					}
					if(year==4){
					sum_year4=sum_year4+rs_charges.getDouble(4);
					}
					if(year==5){
					sum_year5=sum_year5+rs_charges.getDouble(4);
					}
					
					}
					else{
					out.println("  </tr> ");
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
					out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
					year=1;
					total_charges=total_charges+rs_charges.getDouble(4);
					m_code=rs_charges.getString(2);
					
					}
					more_maintaince=rs_charges.next();
					year=year+1; 
					//total_charges=total_charges+rs_charges.getDouble(4);
					}
					
					
					out.println("  <tr> ");
					out.println("             <td width=\"40%\">Total</td> ");
					out.println("              <td width=\"12%\"><div align=\"right\">"+nf1.format(total_charges)+"</div></td>");
					out.println("            <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year2)+"</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year3)+"</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year4)+"</div></td> ");
					out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year5)+"</div></td> ");
					out.println("  </tr> ");
					
					
					
					out.println("            </table></td>");
					
					out.println("            <td width=\"12%\">&nbsp;</td>");
					out.println("          </tr>");
					out.println("         <tr>");
					out.println("            <td colspan=\"3\">Rental Based on</td>");
					out.println("           <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Period ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("           <tr>");
					out.println("            <td>AMI</td>");
					out.println("            <td width=\"10%\">&nbsp;</td>");
					out.println("            <td width=\"7%\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("              <tr>");
					out.println("                <td><div align=\"center\"><b>"+rs.getInt(14)+"</div></td>");
					out.println("              </tr>");
					out.println("            </table></td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("             <td colspan=\"3\">NIBSM</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Supplier Credit ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\"><strong>Installment Structure</strong></td>");
					out.println("           <td>&nbsp;</td>");
					out.println("         </tr>");
					out.println("         <tr>");
					out.println("           <td colspan=\"3\">&nbsp;</td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					
					
					String sql_nibsm=" SELECT  "+
			" SUM(NIBSM), "+
			" MAX(AMI), "+
			" MAX(PERIOD) "+	
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
			" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
			" AND A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND A.INVOICE_NO=B.PRO_INVOICE_NO "+
			" AND A.ACTIVE_STATUS='Y' ";
					rs_rental = stmt_rental.executeQuery(sql_nibsm);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
					{
					m_NIBSM=rs_rental.getDouble(1);
					m_AMI=rs_rental.getInt(2);
					m_PERIOD=rs_rental.getInt(3);
					}
					
					if(m_NIBSM>0)
					{
					no_of_records=m_PERIOD-1;
					}
					else{
					no_of_records=m_PERIOD;
					}
					
					rs_rental.close();
					stmt_rental.close();
					stmt_rental=conn.createStatement ();
					
					//==================================================
					String m_price_no = "";
					
																
																String sql_price_new  = " SELECT B.PRICING_NO "+
									" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
											" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
											" AND   A.APPLICATION_NO=B.APPLICATION_NO  "+
											" AND   A.INVOICE_NO=B.PRO_INVOICE_NO  "+
											" AND   A.ACTIVE_STATUS='Y' ";
												
			rs_price = stmt_price.executeQuery(sql_price_new);
			boolean more_price = rs_price.next();
			
			while(more_price){				
			m_price_no = rs_price.getString(1);
			
					//==================================================
					
					
					String sql_rent_new="   SELECT  "+
					" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
					" SUM(NET_RENTAL_AMOUNT),  "+//2
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
					" SUM(GRENTAL_AMOUNT) "+//4
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
					" PRICING_NO=UPPER('"+m_price_no+"') AND "+
					" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+ //,RENTAL_DATE
					" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
					
					
					
					int end=0;
					int start=0;
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					more3 =rs_rental.next();
					
					if(more3)
					{
					m_rental_new=rs_rental.getDouble(2);
					start=rs_rental.getInt(1);
					m_vat_new=rs_rental.getDouble(3);
					m_gross_new=rs_rental.getDouble(4);
					
					
					while(more3) //START INSTALLMENT LOOP
					{
					
					
					if(m_rental_new!=rs_rental.getDouble(2))
					{
					
					
					
					out.println("         <tr>");
					out.println("           <td width=\"11%\">&nbsp;</td>");
					//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
					if(start==end){
					out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
					}
					else{
					out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
					}
					
					
					out.println("           <td valign=\"top\"><div align=\"right\">");
					out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                 <tr>");
					out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
					out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
					out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
					out.println("                 </tr>");
					out.println("                               </table>");
					out.println("           </div></td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					
					
					start=rs_rental.getInt(1);		
					m_rental_new=rs_rental.getDouble(2);
					m_vat_new=rs_rental.getDouble(3);
					m_gross_new=rs_rental.getDouble(4);
					count_period=0;
					}
					
					count_period=count_period+1;
					end=rs_rental.getInt(1);
					
					more3=rs_rental.next();
					
					if(!more3)
					{
					break;
					}
					
					}
					
					out.println("         <tr>");
					out.println("           <td width=\"11%\">&nbsp;</td>");
					//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
					if(start==end){
					out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
					}
					else{
					out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
					}
					out.println("           <td valign=\"top\"><div align=\"right\">");
					out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                 <tr>");
					out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
					out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
					out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
					out.println("                 </tr>");
					out.println("                               </table>");
					out.println("           </div></td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					
				
					
					
					} //END OF INSTALLMENT LOOP
					
					if(m_NIBSM>0){
					
					
					sql_rent_new="   SELECT  "+
					" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
					" SUM(NET_RENTAL_AMOUNT),  "+//2
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
					" SUM(GRENTAL_AMOUNT) "+//4
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
					" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
					" PRICING_NO=UPPER('"+m_price_no+"') AND "+
					" TO_NUMBER(INSTALLMENT_NO) ="+m_PERIOD+" "+
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+ //,RENTAL_DATE
					" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					more3 =rs_rental.next();
					
					if(more3){	
					out.println("         <tr>");
					out.println("           <td width=\"11%\">&nbsp;</td>");
					out.println("           <td colspan=\"2\"><strong>Final rentals of </strong></td>");
					out.println("           <td valign=\"top\"><div align=\"right\">");
					out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                 <tr>");
					out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
					out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
					out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
					out.println("                 </tr>");
					out.println("                               </table>");
					out.println("           </div></td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					}
					}
				//	more_price = rs_price.next();
			//	}
					out.println("       <tr>");
					out.println("          <td colspan=\"6\">&nbsp;</td>");
					out.println("         </tr>");
					out.println("     </table>");
					
					
					out.println("        <tr>");
					out.println("           <td colspan=\"4\"></p><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("             <tr>");
					out.println("                <td width=\"29%\"><b>True Interst Rate (TIR) </td>");
					out.println("                <td width=\"11%\"><div align=\"right\"><b>"+nf.format(rs.getDouble(15))+"%</div></td>");
					out.println("                <td width=\"60%\">&nbsp;</td>");
					out.println("              </tr>");
					
					
					Sql_data_total_income="  SELECT "+												
					" A.APPLICATION_NO, "+
					" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
					" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
					" AND UPPER(A.PRICING_NO)=UPPER('"+m_price_no+"')  "+ //Added By Sandun on 16-12-2008
					" GROUP BY A.APPLICATION_NO ";
					
					
					rs_rental = stmt_rental.executeQuery(Sql_data_total_income);
					more3 =rs_rental.next();
					if(more3){
					out.println("              <tr>");
					out.println("                <td><b>Total income earned from the transaction </td>");
					out.println("               <td><div align=\"right\"><b>"+nf1.format(rs_rental.getDouble(2))+"</div></td>");
					out.println("               <td>&nbsp;</td>");
					out.println("             </tr>");
					}	
					more_price = rs_price.next();
					}
					out.println("           </table></td>");
					out.println("         </tr>");
					more = rs.next();		
					}
					//______________________________________________________________________________________________________________
					
					} // end check no of pricing 
					*/
				
				
				if(_m_tmp_pricing_count > 1 ) {
					
					Sql_data_facility_details_sum="  SELECT "+												
						//"  A.PRICING_NO, "+ //1
						"  '' ,"+
						"  SUM(A.TOTAL_AMOUNT), "+ //2
						"  SUM(A.NET_PRICE), "+ //3
						"  SUM(A.VAT) , "+ //4
						"  MAX(B.VAT_PERCENTAGE) , "+ //5
						"  SUM(ROUND(((B.VAT_PERCENTAGE - B.VAT_APP)/100)*B.NET_AMOUNT +B.NET_AMOUNT,0)) NET_APP, "+ //6
						"  MAX(PERIOD), "+ //7
						"  DECODE(MAX(B.PAYMENT_INTERVAL),'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks') INTERVEL, "+ //8
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_GROSS, "+ //9   
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(NET_RENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_NET, "+ //10   
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_VAT, "+ //11
						"  SUM(B.NIBSM) , "+ //12
						"  MAX(B.SUPPLIER_CREDIT), "+ //13
						"  MAX(B.AMI), "+ //14
						"  MAX(B.RATE) "+ //15
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
						"  "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL C  "+
						"  WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  A.APPLICATION_NO=B.APPLICATION_NO AND "+
						"  A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
						"  C.DURATION=B.PAYMENT_INTERVAL AND "+
						"  A.ACTIVE_STATUS='Y' ";
					// "  GROUP BY A.PRICING_NO,B.VAT_PERCENTAGE,PERIOD,DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks'),B.SUPPLIER_CREDIT,B.AMI,B.RATE ";
					
					rs.close();
					stmt.close();
					stmt = conn.createStatement ();
					rs = stmt.executeQuery(Sql_data_facility_details_sum);
					more = rs.next();		
					
					while(more){
						total_charges=0;
						out.println("				 <tr>");
						out.println(" <p style=page-break-after:always></p>");
						out.println("       <td height=\"8%\" colspan=\"4\"></p>");
						
						
						out.println("	<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
						out.println("              <tr> ");
						out.println("                <td colspan=\"3\">&nbsp;</td>");
						out.println("                <td width=\"27%\"  rowspan=\"9\" valign=\"top\"><table width=\"100%\" class='table' bordercolor='black'  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                  <tr>");
						out.println("                    <td width=\"45%\" ><div align=\"center\">Gross</div></td>");
						out.println("                    <td width=\"30%\" ><div align=\"center\">Net</div></td>");
						out.println("                    <td width=\"25%\" ><div align=\"center\">VAT</div></td>");
						out.println("                   </tr>");
						out.println("                   <tr>");
						out.println("                     <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
						out.println("                     <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
						out.println("                    <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(4))+"</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("                <td width=\"45%\"><div align=\"right\"><b>"+rs.getString(5)+" %VAT</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(6))+"</div></td>");
						out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("               <td width=\"45%\"><div align=\"right\">"+rs.getInt(7)+"</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("               <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(9))+"</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(10))+"</div></td>");
						out.println("              <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(11))+"</div></td>");
						out.println("            </tr>");
						out.println("            <tr>");
						out.println("             <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(12))+"</div></td>");
						out.println("             <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("              <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("           </tr>");
						out.println("           <tr>");
						out.println("             <td width=\"45%\"><div align=\"right\">"+rs.getInt(13)+"</div></td>");
						out.println("              <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("           </tr>");
						out.println("           <tr>");
						out.println("             <td colspan=\"3\"><strong>Rental Stream</strong></td>");
						out.println("             </tr>");
						out.println("           <tr>");
						out.println("            <td width=\"45%\"><div align=\"center\"><strong>Gross</strong></div></td>");
						out.println("             <td width=\"30%\" class=\"style1\"><div align=\"center\"><b>Net</b></div></td>");
						out.println("             <td width=\"25%\" class=\"style1\"><div align=\"center\"><b>VAT</b></div></td>");
						out.println("           </tr>");
						out.println("         </table></td>");
					//	out.println("         <td colspan=\"2\">Expenses included in to the Lease Rental </td>");
						out.println("       </tr>");
						out.println("       <tr>");
						out.println("          <td colspan=\"3\">Net Cost</td>");
						out.println("         <td width=\"35%\" rowspan=\"7\" valign=\"top\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						
						rs_charges =stmt_charges.executeQuery("SELECT "+
							//"  PRICING_NO, "+ //1
							"  '' ,"+
							"  SUB_CHAGE_CODE, "+ //2
							"  DESCRIPTION, "+ //3
							"  SUM(AMOUNT) "+ //4
							//"  CHARGE_TYPE "+
							"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
							"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
							
							
							
	
							//"  PRICING_NO='"+m_pricing_no+"'  AND "+
							"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
							"  NVL(AMOUNT,0) <> 0 "+
							"  GROUP BY /*PRICING_NO,*/SUB_CHAGE_CODE,DESCRIPTION ");			
						
						//if(rs_charges.next()){
						
					/*	out.println("  <tr> ");
						out.println("             <td width=\"40%\">Charge Type</td> ");
						out.println("             <td width=\"12%\" align=\"right\">Year1</td> ");
						//out.println("            <td width=\" 12%\"><div align=\"right\">Year2</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year3</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year4</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year5</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						//}
						
						while(rs_charges.next()){
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");
							out.println("            <td width=\"12%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+rs_charges.getString(2)+"')\" ><div align=\"right\"><u>"+nf1.format(rs_charges.getDouble(4))+"</u></div></td>");
							//out.println("            <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							out.println("  </tr> ");
							
							
							total_charges=total_charges+rs_charges.getDouble(4);
						}
						*/// commented by ishani
						rs_charges.close();
						stmt_charges.close();
						stmt_charges = conn.createStatement ();
						
						rs_charges =stmt_charges.executeQuery("SELECT "+
							//"  PRICING_NO, "+ //1
							" '' ,"+
							"  SUB_CHAGE_CODE, "+ //2
							"  DESCRIPTION, "+ //3
							"  SUM(AMOUNT) ,"+ //4
							"  YEAR_NO+1 "+ //5
							"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
							"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
							//"  PRICING_NO='"+m_pricing_no+"'  AND "+
							"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
							"  NVL(AMOUNT,0) <> 0 "+
							"  GROUP BY /*PRICING_NO,*/ SUB_CHAGE_CODE,DESCRIPTION,YEAR_NO "+
							"  ORDER BY SUB_CHAGE_CODE ,YEAR_NO ");
						
						boolean more_maintaince=rs_charges.next();
						
						String m_code="";
						if(more_maintaince){
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">Maintaince</td> ");
							out.println("             <td width=\"12%\" align=\"right\">&nbsp;</td> ");
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");  // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							out.println("  </tr> ");
							
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
							m_code=rs_charges.getString(2);
							
						}
						
						
						int year=1;
						while(more_maintaince){
							
							if(rs_charges.getString(2).equals(m_code)){
								out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
								
								if(year==1){
									total_charges=total_charges+rs_charges.getDouble(4);
								}
								if(year==2){
									sum_year2=sum_year2+rs_charges.getDouble(4);
								}
								if(year==3){
									sum_year3=sum_year3+rs_charges.getDouble(4);
								}
								if(year==4){
									sum_year4=sum_year4+rs_charges.getDouble(4);
								}
								if(year==5){
									sum_year5=sum_year5+rs_charges.getDouble(4);
								}
								
							}
							else{
								out.println("  </tr> ");
								out.println("  <tr> ");
								out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
								out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
								year=1;
								total_charges=total_charges+rs_charges.getDouble(4);
								m_code=rs_charges.getString(2);
								
							}
							more_maintaince=rs_charges.next();
							year=year+1; 
							//total_charges=total_charges+rs_charges.getDouble(4);
						}
						
						
						/*out.println("  <tr> ");
						out.println("             <td width=\"40%\">Total</td> ");
						out.println("              <td width=\"12%\"><div align=\"right\">"+nf1.format(total_charges)+"</div></td>");
						//out.println("            <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year2)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year3)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year4)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year5)+"</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						*/
						
						
						out.println("            </table></td>");
						
						out.println("            <td width=\"12%\">&nbsp;</td>");
						out.println("          </tr>");
						out.println("         <tr>");
						out.println("            <td colspan=\"3\">Rental Based on</td>");
						out.println("           <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\">Period ("+rs.getString(8)+")</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("           <tr>");
						out.println("            <td>AMI</td>");
						out.println("            <td width=\"10%\">&nbsp;</td>");
						out.println("            <td width=\"7%\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
						out.println("              <tr>");
						out.println("                <td><div align=\"center\"><b>"+rs.getInt(14)+"</div></td>");
						out.println("              </tr>");
						out.println("            </table></td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("             <td colspan=\"3\">NIBSM</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\">Supplier Credit ("+rs.getString(8)+")</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\"><strong>Installment Structure</strong></td>");
						out.println("           <td>&nbsp;</td>");
						out.println("         </tr>");
						out.println("         <tr>");
						out.println("           <td colspan=\"3\">&nbsp;</td>");
						out.println("           <td colspan=\"2\">&nbsp;</td>");
						out.println("         </tr>");
						
						
						/*	String sql_nibsm=" SELECT  "+
							" SUM(NIBSM), "+
							" MAX(AMI), "+
							" MAX(PERIOD) "+	
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
							" WHERE UPPER(APPLICATION_NO)= UPPER('"+m_applicaton_no+"') "+
							" AND  ENT_DATE IN(SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"'))";//Added By Sandun on 24-06-2009
							*/
						
						String sql_nibsm=" SELECT  "+
							" SUM(NIBSM), "+
							" MAX(AMI), "+
							" MAX(PERIOD) "+	
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
							" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
							" AND A.APPLICATION_NO=B.APPLICATION_NO "+
							" AND A.INVOICE_NO=B.PRO_INVOICE_NO "+
							" AND A.ACTIVE_STATUS='Y' ";
						
						rs_rental = stmt_rental.executeQuery(sql_nibsm);
						//out.println(sql_nibsm);
						boolean more3 =rs_rental.next();
						
						if(more3)
						{
							m_NIBSM=rs_rental.getDouble(1);
							m_AMI=rs_rental.getInt(2);
							m_PERIOD=rs_rental.getInt(3);
						}
						
						if(m_NIBSM>0)
						{
							no_of_records=m_PERIOD-1;
						}
						else{
							no_of_records=m_PERIOD;
						}
						
						rs_rental.close();
						stmt_rental.close();
						stmt_rental=conn.createStatement ();
						
						String sql_rent_new = "";
						
						if(m_termi_type.equals("RESCHEDULE")){
							
							/*sql_rent_new="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
							" SUM(NET_RENTAL_AMOUNT),  "+//2
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
							" SUM(GRENTAL_AMOUNT) "+//4
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
							" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
							" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
							" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+ //,RENTAL_DATE ,PRICING_NO 
							" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
							*/
							
							sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
								" SUM(NET_RENTAL_AMOUNT),   "+
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
								" SUM(GRENTAL_AMOUNT)  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
								" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
								" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
								" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
								" AND   A.PRICING_NO=B.PRICING_NO "+
								" AND   B.ACTIVE_STATUS='Y' "+
								" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
								" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
							
							
						}
						else{
							sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
								" SUM(NET_RENTAL_AMOUNT),  "+//2
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
								" SUM(GRENTAL_AMOUNT) "+//4
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
								" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
								//" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
								" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)/*,PRICING_NO */ "+ //,RENTAL_DATE
								" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
						}
						
						
						int end=0;
						int start=0;
						String m_ins="";
						double m_rental_new=0;
						double m_vat_new=0;
						double m_gross_new=0;
						int count_period=0;
						
						rs_rental = stmt_rental.executeQuery(sql_rent_new);
						
						more3 =rs_rental.next();
						
						if(more3)
						{
							m_rental_new=rs_rental.getDouble(2);
							start=rs_rental.getInt(1);
							m_vat_new=rs_rental.getDouble(3);
							m_gross_new=rs_rental.getDouble(4);
							
							
							while(more3) //START INSTALLMENT LOOP
							{
								
								
								if(m_rental_new!=rs_rental.getDouble(2))
								{
									
									
									
									out.println("         <tr>");
									out.println("           <td width=\"11%\">&nbsp;</td>");
									//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
									if(start==end){
										out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
									}
									else{
										out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
									}
									
									
									out.println("           <td valign=\"top\"><div align=\"right\">");
									out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
									out.println("                 <tr>");
									out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
									out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
									out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
									out.println("                 </tr>");
									out.println("                               </table>");
									out.println("           </div></td>");
									out.println("           <td colspan=\"2\">&nbsp;</td>");
									out.println("         </tr>");
									
									
									
									
									start=rs_rental.getInt(1);		
									m_rental_new=rs_rental.getDouble(2);
									m_vat_new=rs_rental.getDouble(3);
									m_gross_new=rs_rental.getDouble(4);
									count_period=0;
								}
								
								count_period=count_period+1;
								end=rs_rental.getInt(1);
								
								more3=rs_rental.next();
								
								if(!more3)
								{
									break;
								}
								
							}
							
							out.println("         <tr>");
							out.println("           <td width=\"11%\">&nbsp;</td>");
							//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
							if(start==end){
								out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
							}
							else{
								out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
							}
							out.println("           <td valign=\"top\"><div align=\"right\">");
							out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
							out.println("                 </tr>");
							out.println("                               </table>");
							out.println("           </div></td>");
							out.println("           <td colspan=\"2\">&nbsp;</td>");
							out.println("         </tr>");
							
							
							
							
						} //END OF INSTALLMENT LOOP
						
						if(m_NIBSM>0){
							
							
							sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
								" SUM(NET_RENTAL_AMOUNT),   "+
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
								" SUM(GRENTAL_AMOUNT)  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
								" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
								" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
								" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
								" AND   A.PRICING_NO=B.PRICING_NO "+
								" AND   B.ACTIVE_STATUS='Y' "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
								" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
							
							
							rs_rental = stmt_rental.executeQuery(sql_rent_new);
							
							more3 =rs_rental.next();
							
							if(more3){	
								out.println("         <tr>");
								out.println("           <td width=\"11%\">&nbsp;</td>");
								out.println("           <td colspan=\"2\"><strong>Final rentals of </strong></td>");
								out.println("           <td valign=\"top\"><div align=\"right\">");
								out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
								out.println("                 </tr>");
								out.println("                               </table>");
								out.println("           </div></td>");
								out.println("           <td colspan=\"2\">&nbsp;</td>");
								out.println("         </tr>");
							}
						}
						
						out.println("       <tr>");
						out.println("          <td colspan=\"6\">&nbsp;</td>");
						out.println("         </tr>");
						out.println("     </table>");
						
						
						out.println("        <tr>");
						out.println("           <td colspan=\"4\"></p><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("             <tr>");
						out.println("                <td width=\"29%\"><b>True Interst Rate (TIR) </td>");
						out.println("                <td width=\"11%\"><div align=\"right\"><b>"+nf.format(rs.getDouble(15))+"%</div></td>");
						out.println("                <td width=\"60%\">&nbsp;</td>");
						out.println("              </tr>");
						
						
						
						Sql_data_total_income="  SELECT "+												
							" A.APPLICATION_NO, "+
							" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
							" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
							" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
							" AND UPPER(A.PRICING_NO)=UPPER('"+m_pricing_no+"')  "+
							" GROUP BY A.APPLICATION_NO ";
						
						
						
						rs_rental = stmt_rental.executeQuery(Sql_data_total_income);
						more3 =rs_rental.next();
						if(more3){
							out.println("              <tr>");
							out.println("                <td><b>Total income earned from the transaction </td>");
							out.println("               <td><div align=\"right\"><b>"+nf1.format(rs_rental.getDouble(2))+"</div></td>");
							out.println("               <td>&nbsp;</td>");
							out.println("             </tr>");
						}				
						out.println("           </table></td>");
						out.println("         </tr>");
						more = rs.next();		
					}
					//______________________________________________________________________________________________________________
					
				}
				else {
					
					out.println("         <tr>");
					out.println("           <td colspan=\"4\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					out.println("			<tr> ");
					out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("        <tr> ");
					out.println("          <td width=\"11%\">&nbsp;</td> ");
					out.println("          <td width=\"17%\">&nbsp;</strong></td> ");
					out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                 <tr>");
					out.println("                   <td width=\"45%\"><div align=\"center\">Gross</div></td>");
					out.println("                   <td width=\"30%\"><div align=\"center\">Net</div></td>");
					out.println("                   <td width=\"25%\"><div align=\"center\">VAT</div></td>");
					out.println("                 </tr>");
					
					out.println("         </table></td> ");
					out.println("         <td width=\"45%\">&nbsp;</td> ");
					out.println("       </tr> ");
					out.println("    </table></td> ");
					out.println("   </tr> ");
					
					
					
					
					/*		String sql_rent_tot="   SELECT  "+
					
					" TO_NUMBER(INSTALLMENT_NO)+1 ,  "+
					" SUM(NET_RENTAL_AMOUNT),   "+
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
					" SUM(GRENTAL_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
					" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND  "+
					" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
					//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE APPLICATION_NO = '"+m_applicaton_no+"') "+//Added by Sandun on 24-06-2009
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
			*/
					String sql_rent_tot="   SELECT  "+
						" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
						" SUM(NET_RENTAL_AMOUNT),   "+
						" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
						" SUM(GRENTAL_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
						" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
						" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
						" AND   A.PRICING_NO=B.PRICING_NO "+
						" AND   B.ACTIVE_STATUS='Y' "+
						" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
						" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
						" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
					
					
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					int end=0;
					int start=0;
					
					
					rs_rental = stmt_rental.executeQuery(sql_rent_tot);
					
					boolean more3 =rs_rental.next()	;
					
					if(more3)
					{
						m_rental_new=rs_rental.getDouble(2);
						m_vat_new=rs_rental.getDouble(3);
						m_gross_new=rs_rental.getDouble(4);
						start=rs_rental.getInt(1);
						
						
						while(more3) //START INSTALLMENT LOOP
						{
							
							
							if(m_rental_new!=rs_rental.getDouble(2))
							{
								out.println("			<tr> ");
								out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
								out.println("        <tr> ");
								out.println("          <td width=\"11%\">&nbsp;</td> ");
								//out.println("          <td width=\"17%\"><strong>"+count_period+" total rentals of  </strong></td> ");
								
								if(start==end){
									out.println("          <td width=\"17%\"><strong>"+start+"     </strong></td> ");
								}
								else{
									out.println("          <td width=\"17%\"><strong>"+start+" - "+end+" </strong></td> ");
								}
								
								out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
								out.println("                 </tr>");
								
								out.println("         </table></td> ");
								out.println("         <td width=\"45%\">&nbsp;</td> ");
								out.println("       </tr> ");
								out.println("    </table></td> ");
								out.println("   </tr> ");
								
								start=rs_rental.getInt(1);		
								m_rental_new=rs_rental.getDouble(2);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
								count_period=0;
							}
							
							count_period=count_period+1;
							end=rs_rental.getInt(1);
							
							more3=rs_rental.next();
							
							if(!more3)
							{
								break;
							}
							
						}
						
						out.println("			<tr> ");
						out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
						out.println("        <tr> ");
						out.println("          <td width=\"11%\">&nbsp;</td> ");
						//out.println("          <td width=\"17%\"><strong>"+count_period+" total rentals of  </strong></td> ");
						if(start==end){
							out.println("          <td width=\"17%\"><strong>"+start+"  </strong></td> ");
						}
						else{
							out.println("          <td width=\"17%\"><strong>"+start+" - "+end+"   </strong></td> ");
						}
						
						out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                 <tr>");
						out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
						out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
						out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
						out.println("                 </tr>");
						
						out.println("         </table></td> ");
						out.println("         <td width=\"45%\">&nbsp;</td> ");
						out.println("       </tr> ");
						out.println("    </table></td> ");
						out.println("   </tr> ");
						
						
						
						
						
					} //END OF INSTALLMENT LOOP
					
					
					if(m_NIBSM>0){
						
						/*
								sql_rent_tot="   SELECT  "+
							
							" TO_NUMBER(INSTALLMENT_NO)+1 ,  "+
							" SUM(NET_RENTAL_AMOUNT),   "+
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
							" SUM(GRENTAL_AMOUNT)  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
							" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND  "+
							" TO_NUMBER(INSTALLMENT_NO) = "+m_PERIOD+" "+
							//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE APPLICATION_NO = '"+m_applicaton_no+"') "+//Added by Sandun on 24-06-2009
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
							*/
						
						sql_rent_tot="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
							" SUM(NET_RENTAL_AMOUNT),   "+
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
							" SUM(GRENTAL_AMOUNT)  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
							" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
							" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
							" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
							" AND   A.PRICING_NO=B.PRICING_NO "+
							" AND   B.ACTIVE_STATUS='Y' "+
							" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
						
						rs_rental = stmt_rental.executeQuery(sql_rent_tot);
						
						more3 =rs_rental.next();
						
						if(more3){	
							out.println("			<tr> ");
							out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
							out.println("        <tr> ");
							out.println("          <td width=\"11%\">&nbsp;</td> ");
							out.println("          <td width=\"17%\"><strong>Final rental of  </strong></td> ");
							out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
							out.println("                 </tr>");
							
							out.println("         </table></td> ");
							out.println("         <td width=\"45%\">&nbsp;</td> ");
							out.println("       </tr> ");
							out.println("    </table></td> ");
							out.println("   </tr> ");
							
						}
					}
					
				} //end else part
				
				
				
				out.println("         <tr>");
				out.println("           <td colspan=\"4\">&nbsp;</td>");
				out.println("         </tr>");
				out.println("      </table></td>");
				out.println("      </tr>");
				out.println("   </table></td>");
				out.println(" </tr>");
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_Comments);
				
				more =rs.next();
				
				while(more){
					
					out.println("  <tr>");
					out.println("    <td colspan=\"5\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("      <tr>");
					out.println("        <td><b>"+rs.getString(3)+"</td>");
					out.println("      </tr>");
					
					out.println("      <tr>");
					out.println("       <td>"+rs.getString(4)+"</td>");
					out.println("     </tr>");
					out.println("   </table></td>");
					out.println("  </tr>");
					
					more =rs.next();
				}
				
				out.println("  </table>");
				
				//added by nuwan de silva 
				out.println("   <p style=\"page-break-after:always\"></p>");		
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");	
				out.println("  <tr>");
				out.println("    <td colspan=\"6\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("      <tr>");
				out.println("       <td><b>APPROVAL PROCESS </td>");
				out.println("     </tr>");
				out.println("    </table></td>");
				out.println(" </tr>");
				out.println(" <tr>");
				out.println("  <td width=\"19%\"><b>Name</td>");
				out.println("  <td width=\"18%\"><b>Designation</td>");
				out.println("  <td width=\"20%\"><b>Screen Name</td>");//Added by Mahela on 15-11-2007
				out.println("  <td width=\"10%\"><b>Time</td>");
				out.println("  <td width=\"10%\"><b>Date</td>");
				out.println(" <td width=\"43%\"><div align=\"center\"><b>Conditions</div></td>");
				out.println(" </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_approval_prcess);
				
				more =rs.next();
				if(!more){
					
					out.println(" <tr>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
				}
				
				while(more){
					
					
					
					
					if(rs.getString(3).equals("R1") || rs.getString(3).equals("R2") || rs.getString(3).equals("R3") || rs.getString(3).equals("R4") ){
						out.println(" <tr >");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }' >"+rs.getString(4)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(9)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(6)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(7)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(8)+"</td>");
						out.println(" </tr>");
					}
					else{
						out.println(" <tr>");
						out.println("   <td>"+rs.getString(4)+"</td>");
						out.println("   <td>"+rs.getString(9)+"</td>");
						out.println("   <td>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007	
						out.println("   <td>"+rs.getString(6)+"</td>");
						out.println("   <td>"+rs.getString(7)+"</td>");
						out.println("   <td>"+rs.getString(8)+"</td>");
						out.println(" </tr>");
						
					}
					
					
					more =rs.next();
				}
				
				
				out.println("</table>");
				out.println("<BR>");
				//added by nuwande silva on 27-12-2007 --------------------------------------------------------
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");	
				out.println("  <tr>");
				out.println("    <td colspan=\"6\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("      <tr>");
				out.println("       <td><b>AGM COMMENTS </td>");
				out.println("     </tr>");
				out.println("    </table></td>");
				out.println(" </tr>");
				out.println(" <tr>");
				out.println("  <td width=\"19%\"><b>Name</td>");
				out.println("  <td width=\"18%\"><b>Designation</td>");
				//out.println("  <td width=\"20%\"><b>Screen Name</td>");//Added by Mahela on 15-11-2007
				out.println("  <td width=\"10%\"><b>Time</td>");
				out.println("  <td width=\"10%\"><b>Date</td>");
				out.println(" <td width=\"43%\"><div align=\"center\"><b>Conditions</div></td>");
				out.println(" </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_agm_note);
				
				more =rs.next();
				if(!more){
					
					out.println(" <tr>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					//out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
				}
				
				while(more){
					
					out.println(" <tr>");
					out.println("   <td>"+rs.getString(4)+"</td>");
					out.println("   <td>"+rs.getString(9)+"</td>");
					//out.println("   <td>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007	
					out.println("   <td>"+rs.getString(6)+"</td>");
					out.println("   <td>"+rs.getString(7)+"</td>");
					out.println("   <td>"+rs.getString(8)+"</td>");
					out.println(" </tr>");
					
					more =rs.next();
				}
				
				
				out.println("</table>");
				
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				out.println("<br>"); 
				
				
				//===========add Conditionds============nuwan de silva 13-06-07=========
				out.println("<hr color='black'>");
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width='*%'><b><u>Conditions</td>"); 
				out.println("</tr>");  
				out.println("<tr>");  
				out.println("<td width='*%'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\" ></td>"); 
				out.println("</tr>");  
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>");
				
				out.println("<hr color='black'>");
				//=================================================================================
				
				
				//********************************Addded By Nuwan De Silva 13-06-07*************************************************************************************************************************************************************
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_score_model);
				
				more =rs.next();
				String score_model="";
				if(more){
					score_model=rs.getString(1);
				}
				
				if(!m_Hid_scr_name.equals("AF_CR_PRO_AGM_COMMENT")){
					
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='76%' style='{text-align:left;}'><b>Remarks</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Approve</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Return</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Abandoned</td>");
					out.println("<tr>");
					
					out.println("<tr valign='center'>");
					out.println("<td width='76%'><TEXTAREA NAME='TXT_REMARKS' class='txt_input' style=\"width:750px; height:50px;\"  maxlength=\"2000\" onkeyPress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"></TEXTAREA></td>");
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_app' value=\"N\" unchecked onClick=\"change()\"></td>"); 
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_return' value=\"N\" unchecked onClick=\"change_return()\"></td>"); 
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_rej' value=\"N\" unchecked onClick=\"change_reject()\">"); 
					out.println("<input type='hidden' NAME='Hid_Score_Model' VALUE="+score_model+"</td>");
					out.println("</tr>");
					out.println("</table>");		
				}
				else{
					
					out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='76%' style='{text-align:left;}'><b>Remarks</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Approve</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Return</td>");
					out.println("<td width='8%' style='{text-align:center;}'><b>Abandoned</td>");
					out.println("<tr>");
					
					out.println("<tr valign='center'>");
					out.println("<td width='76%'><TEXTAREA NAME='TXT_REMARKS' class='txt_input' style=\"width:750px; height:50px;\"  maxlength=\"2000\" onkeyPress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\"></TEXTAREA></td>");
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_app' value=\"N\" unchecked onClick=\"change()\" disabled></td>"); 
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_return' value=\"N\" unchecked onClick=\"change_return()\" disabled></td>"); 
					out.println("<td width='8%' align='center'><input type='checkbox' name='chk_rej' value=\"N\" unchecked onClick=\"change_reject()\" disabled>"); 
					out.println("<input type='hidden' NAME='Hid_Score_Model' VALUE="+score_model+"</td>");
					out.println("</tr>");
					out.println("</table>");		
					
					
					
				}
				out.println("<hr color='black'>");
				
				//=================================================================================
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  
				
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				
				out.println("</body>");
				
				out.println("</html>");
				
			}
			
			else if(m_chksql.equals("Report")){
				
				String m_print=req.getParameter("print");
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function save_data(){");
				
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Purchase_Order_View_Letter?chksql=save_page&scr_name=AF_CR_PRO_PURCHASE_ORDER&vendor_code="+m_vendor_code+"&status="+m_status+"&app_no="+m_app_no+"&print="+m_print+"&pur_ord_no="+m_pur_ord_no+"&branch_code="+m_branch_code+"\";"); 
				//out.println(" window.location.href=m_url;"); 
				
				out.println("m_table.innerHTML=\"\" ");
				
				out.println("window.print();");
				
				out.println("}");
				
				out.println("function add_button(){");
				
				if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
				}
				else
				{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
				}
				
				
				out.println("}");
				
				
				//______ added by nuwan de silva on 23-11-2007 _______
				out.println("function validate_nic_no(){ "); 
				
				out.println("if('"+_m_tmp_client_type+"'=='I'){ ");
				out.println("if(!validate_NIC_Return('"+_m_tmp_nic_no+"','"+_m_tmp_gender+"','"+_m_tmp_date_of_birth+"')){");
				out.println("m_nic_no.style.color='red';");
				out.println("}");
				out.println("}");
				out.println("}");
				// ________ end by nuwan de silva on 23-11-2007 ______
				
				
				out.println("</script>");
				
				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"add_button(),validate_nic_no()\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				out.println("<br>"); 
				out.println("<br>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				
				out.println("</table>");
				out.println("<br>"); 
				
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");
				out.println(" <tr> ");
				out.println("  <td colspan=\"5\"><div align=\"center\"><b>"+m_orient_name.toUpperCase()+"</b><br> ");
				out.println("   <b>SANCTION LETTER<b></div></td>");
				out.println(" </tr>");
				
				rs = stmt.executeQuery(Sql_client_data);
				
				more = rs.next();		
				
				if(more){
					
					String m_client_code =rs.getString(2);
					String m_name        =rs.getString(3);
					String m_client_add1 =rs.getString(4);
					String m_client_add2 =rs.getString(5);
					String m_reg_no      =rs.getString(6);
					String m_officer      =rs.getString(7);
					String m_industry    =rs.getString(8);			
					String m_sys_date    =rs.getString(9);
					String m_finance_no  =rs.getString(10);
					String m_mas_lease_no=rs.getString(11);
					String m_broker      =rs.getString(12);
					String m_mk_officer  =rs.getString(13);
					String m_sub_sector  =rs.getString(14);
					String m_branch_code =rs.getString(15);
					String m_branch_desc =rs.getString(16);
					String m_sub_sector_desc =rs.getString(17);
					String m_city_name   =rs.getString(18); //Added by Chandana on 03/08/2007 for Ref No.764 
					
					//String m_insurance_agent = rs.getString(23);
					double m_sum_insured = rs.getDouble(24);
					String m_veh_no = rs.getString(25); 
					String m_vendor_code = rs.getString(26);
					String m_security_details      =rs.getString(27); // added by udara on 15-07-2013
					String m_broker_code      =rs.getString(28);
					String m_address="";
					
					if(!m_client_add1.equals("-") && !m_client_add2.equals("-")){
						m_address=m_client_add1+", "+m_client_add2+", "+m_city_name;
					}
					else if(!m_client_add1.equals("-") ){
						m_address=m_client_add1+", "+m_city_name;
					}
					
					
					//--Close the Result Set And Stateement--------			
					rs.close();
					stmt.close();
					//---------------------------------------------
					//--Create The Statement----------------------
					stmt = conn.createStatement ();
					//-------------------------------------------- 
					
					rs = stmt.executeQuery(sql_co_applicant);
					String m_co_applicant="";
					more = rs.next();		
					if(more){
						m_co_applicant=rs.getString(1);
					}
					
					
					
					
					
					Sql_data_exposure_prev="  SELECT "+												
						"	  A.ARREARS,C.ODI,B.NIL "+
						"		FROM  "+
						"		(SELECT "+
						"		NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"') AND "+
						"		A.ACTIVE_STATUS='Y' AND "+
						"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
						"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
						
						/*"		(SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL "+
						"		FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT A, "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"		"+m_schema_name+".AF_CO_PRO_INVOICE C     "+
						"		WHERE B.CLIENT_CODE=C.CLIENT_CODE(+) AND "+
						"		UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND"+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND "+ 
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND "+
						"	 (C.BALANCE_TO_BE_RECEIVED>0  OR "+
						"		A.INVOICE_NO IS NULL))B, "+
						*/
						
						"	  (SELECT SUM(NIL) NIL "+
						"		FROM "+
						"		(SELECT  "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL  "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"			A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND  "+
						"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		A.INVOICE_NO IS NULL "+
						"		UNION     "+                             
						"		SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0)  NIL "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		UPPER(A.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND  "+
						"		A.INVOICE_NO IN ( "+
						"		SELECT "+
						"		INVOICE_NO "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
						"		WHERE INVOICE_TYPE='INV_GENER' AND "+
						"		ACTIVE_STATUS='Y' AND "+
						"		BALANCE_TO_BE_RECEIVED>0 ))) B, "+
						
						"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
						"		WHERE INVOICE_NO IN   "+
						"		(SELECT   "+
						"		INVOICE_NO   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) <> UPPER('"+m_applicaton_no+"')  AND "+
						"		A.ACTIVE_STATUS='Y'  "+
						"		) )C        ";
					
					
					Sql_data_exposure_current="  SELECT "+												
						"	  A.ARREARS,C.ODI,B.NIL "+
						"		FROM  "+
						"		(SELECT "+
						"		NVL(SUM(A.BALANCE_TO_BE_RECEIVED),0) ARREARS "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+ 
						"		WHERE A.INVOICE_TYPE='INV_GENER' AND "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"') AND "+
						"		A.ACTIVE_STATUS='Y' AND "+
						"		A.BALANCE_TO_BE_RECEIVED>0  AND "+
						"		UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"')) A, "+
						
						/*"		(SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL "+
						"		FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT A, "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"		"+m_schema_name+".AF_CO_PRO_INVOICE C     "+
						"		WHERE B.CLIENT_CODE=C.CLIENT_CODE(+) AND "+
						"		UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND"+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND "+ 
						"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND "+
						"	 (C.BALANCE_TO_BE_RECEIVED>0  OR "+
						"		A.INVOICE_NO IS NULL))B, "+
						*/
						
						"	  (SELECT SUM(NIL) NIL "+
						"		FROM "+
						"		(SELECT  "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0) NIL  "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,  "+
						"		"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"			A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND  "+
						//"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		A.INVOICE_NO IS NULL "+
						"		UNION     "+                             
						"		SELECT "+
						"		NVL(SUM(A.CAPITAL_AMOUNT),0)  NIL "+
						"		FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"')  AND "+
						"		A.APPLICATION_NO=B.APPLICATION_NO AND   "+
						"		UPPER(A.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND  "+
						"   APPLICATION_STATUS='ACTIVATED' AND"+
						"   pro_invoice_no in (SELECT invoice_no  FROM "+m_schema_name+".af_co_pro_app_invoice_details  WHERE application_no=A.APPLICATION_NO AND ACTIVE_STATUS='Y') and "+
						"		A.INVOICE_NO IN ( "+
						"		SELECT "+
						"		INVOICE_NO "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
						"		WHERE INVOICE_TYPE='INV_GENER' AND "+
						"		ACTIVE_STATUS='Y' AND "+
						"		BALANCE_TO_BE_RECEIVED>0 ))) B, "+
						
						
						"		(SELECT NVL(SUM(ODI_BAL_AMOUNT),0) ODI   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  "+
						"		WHERE INVOICE_NO IN   "+
						"		(SELECT   "+
						"		INVOICE_NO   "+
						"		FROM "+m_schema_name+".AF_CO_PRO_INVOICE  A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						"		WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  "+
						"		A.CLIENT_CODE=B.CLIENT_CODE AND "+
						"		B.FINANCE_NO=A.FINANCE_NO(+) AND "+
						"		UPPER(B.APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  AND "+
						"		A.ACTIVE_STATUS='Y'  "+
						"		) )C        ";
					
					
					
					
					
					
					
					
					out.println(" <tr>");
					out.println("  <td colspan=\"5\"><table width=\"100%\"  border=\"0\"  cellspacing=\"0\" cellpadding=\"0\">");
					out.println("   <tr>");
					out.println("    <td width=\"18%\"><b>Application No</b> </td>");
					out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
					out.println("    <td width=\"27%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+m_applicaton_no+"')\" ><u>"+m_applicaton_no+"</u></td>");
					out.println("    <td width=\"17%\"><b>Agreement No </td>");
					out.println("    <td width=\"1%\"><div align=\"center\">:</div></td>");
					out.println("    <td width=\"17%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+m_finance_no+"')\" ><u>"+m_finance_no+"</u></td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Introduced by </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_broker+"</td>");
					out.println("    <td><b>Master Lease Agreement No </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+m_mas_lease_no+"')\" ><u>"+m_mas_lease_no+"</u></td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Broker</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					//out.println("    <td>"+m_broker_name+"</td>");
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_broker_det('"+m_broker_code+"')\" ><u>"+m_broker+"</u></td>");
					//out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+m_client_code+"')\" ><u>"+m_name+"</u></td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td><div align=\"center\"></div></td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Date</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_sys_date+"</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td><div align=\"center\"></div></td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Client </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+m_client_code+"')\" ><u>"+m_name+"</u></td>");
					out.println("    <td><b>Sector/Industry</td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("      <td>"+m_sub_sector_desc+"</td>");
					out.println("   </tr>");
					
					out.println("   <tr>");
					out.println("    <td><b>Address </td>");
					out.println("    <td><div align=\"center\">:</div></td>");
					out.println("    <td>"+m_address+" </td>");
					out.println("      <td><b>Credit Rating </td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>-</td>");
					out.println("     </tr>");
					
					out.println("     <tr>");
					out.println("       <td><b>Reg/NIC No</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td><div id=m_nic_no>"+m_reg_no+"</div></td>");
					out.println("    <td><b>Branch Name</td>");
					out.println("    <td align=\"center\">:</td>");
					out.println("    <td>"+m_branch_desc+"</td>");
					out.println("   </tr>");
					
					out.println("     <tr>");
					out.println("       <td><b>Co-Applicant</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_co_applicant+"</td>");
					out.println("    <td><b>Marketing Officer</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_mk_officer+"</td>");
					out.println("   </tr>");
					
					// added by udara on 10-04-2013
					out.println("     <tr>");
					out.println("       <td><b>Vehicle No</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_veh_no+"</td>");
					out.println("    <td><b>Vendor Name</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_vendor_code+"</td>");
					out.println("   </tr>");
					
					out.println("     <tr>");
					out.println("       <td><b>Sum Insured</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+nf.format(m_sum_insured)+"</td>");
					out.println("    <td><b>Insurance Agent</td>");
					out.println("    <td>:</td>");
					out.println("    <td>"+m_insurance_agent+"</td>");
					out.println("   </tr>");
					// end by udara 10-04-2013
					
					
					// added by udara on 15-07-2013
					
					out.println("     <tr>");
					out.println("       <td><b> Security Details </td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>  "+m_security_details+" </td>");
					out.println("    <td><b> </td>");
					out.println("    <td>  &nbsp; </td>");
					out.println("    <td>  &nbsp; </td>");
					out.println("   </tr>");
					
					// end by udara on 15-07-2013
					
					
					
					/*out.println("     <tr>");
					out.println("       <td><b>Co-applicant</td>");
					out.println("       <td><div align=\"center\">:</div></td>");
					out.println("       <td>"+m_co_applicant+"</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("    <td>&nbsp;</td>");
					out.println("   </tr>");	
				*/
					
					out.println("  </table></td>");
					out.println(" </tr>");
					
				}
				
				
				
				
				//========Guarantors==============================================
				out.println(" <tr>");
				out.println("  <td colspan=\"5\"><table width=\"100%\"  class='table'  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("   <tr>");
				out.println("    <td width=\"24%\" valign='top' ><b>Guarantors</b></td>");
				out.println("    <td width=\"76%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_guarantor);
				
				more = rs.next();		
				int i=1;
				while(more){
					
					out.println("     <tr height=\"18\">");
					out.println("     <td width=\"11%\"><div align=\"center\">"+i+"</div></td>");
					out.println("     <td width=\"89%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(1)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("     </tr>");
					more = rs.next();		
					i=i+1;
				}
				
				//out.println("     <tr>");
				//out.println("      <td><div align=\"center\">2</div></td>");
				//out.println("      <td>Mrs.Perera</td>");
				//out.println("     </tr>");
				
				out.println("    </table></td>");
				out.println("   </tr>");
				out.println("   <tr>");
				
				//==============end of guarantors============================================		
				out.println("    <td colspan=\"2\">&nbsp;</td>");
				out.println("    </tr>");
				
				//start of the exposure with OFSCL===========================================
				out.println("   <tr>");
				out.println("    <td colspan=\"2\"><b>Total exposure with "+m_schema_name+" </b></td>");
				out.println("    </tr>");
				out.println("   <tr>");
				out.println("    <td colspan=\"2\" valign=\"top\"><table width=\"100%\" class='table'   border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("     <tr>");
				out.println("      <td width=\"47%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("       <tr>");
				out.println("        <td width=\"31%\"><b>Exposure</b></td>");
				out.println("        <td width=\"25%\">&nbsp;</td>");
				out.println("        <td width=\"19%\">&nbsp;</td>");
				out.println("        <td width=\"25%\">&nbsp;</td>");
				out.println("       </tr>");
				out.println("       <tr>");
				out.println("        <td>&nbsp;</td>");
				out.println("        <td><div align=\"center\">Arrears</div></td>");
				out.println("        <td><div align=\"center\">ODI</div></td>");
				out.println("        <td><div align=\"center\">NIL</div></td>");
				out.println("       </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_exposure_prev);
				
				double tot_arrears=0;
				double tot_odi=0;
				double tot_nil=0;
				
				more = rs.next();		
				
				if(more){
					
					out.println("       <tr>");
					out.println("        <td>Client</td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(1))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("       </tr>");
					tot_arrears=tot_arrears+rs.getDouble(1);
					tot_odi=tot_odi+rs.getDouble(2);
					tot_nil=tot_nil+rs.getDouble(3);
				}
				
				out.println("       <tr>");
				out.println("        <td>Group</td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("        <td><div align=\"right\">&nbsp;</div></td>");
				out.println("       </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_exposure_current);
				more = rs.next();		
				
				if(more){
					
					out.println("       <tr>");
					out.println("        <td>Transaction</td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(1))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("        <td><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("       </tr>");
					
					tot_arrears=tot_arrears+rs.getDouble(1);
					tot_odi=tot_odi+rs.getDouble(2);
					tot_nil=tot_nil+rs.getDouble(3);
				}
				
				
				out.println("       <tr>");
				out.println("        <td>Total</td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_arrears)+"</div></td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_odi)+"</div></td>");
				out.println("        <td><div align=\"right\">"+nf1.format(tot_nil)+"</div></td>");
				out.println("       </tr>");
				out.println("      </table></td>");
				out.println("      <td width=\"11%\">&nbsp;</td>");
				
				//=============end total exposure with OFSCL=====================================================
				
				//====start facility=============================================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_facility_type);
				
				more = rs.next();		
				
				out.println("      <td width=\"23%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("       <tr>");
				out.println("        <td><div align=\"center\">Type of facility </div></td>");
				out.println("       </tr>");
				if(more){	
					out.println("       <tr>");
					out.println("        <td><div align=\"center\">"+rs.getString(1)+"</div></td>");
					out.println("       </tr>");
				}
				out.println("      </table></td>");
				//====end facility===========================================
				out.println("      <td width=\"19%\">&nbsp;</td>");
				out.println("     </tr>");
				out.println("     <tr>");
				out.println("      <td colspan=\"4\">&nbsp;</td>");
				out.println("      </tr>");
				//start asset details=====================================================
				out.println("     <tr>");
				out.println("      <td colspan=\"4\"><b>Asset Details</b> </td>");
				out.println("     </tr>");
				out.println("     <tr>");
				out.println("      <td colspan=\"3\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
				
				out.println("       <tr>");
				out.println("        <td width=\"18%\"><b>Make</b></td>");
				out.println("        <td width=\"15%\"><b>Model</b></td>");
				out.println("        <td width=\"10%\"><b>Engine No </b></td>");
				out.println("        <td width=\"15%\"><b>Chass.No/Serial No</b></td>");
				out.println("        <td width=\"14%\"><b>Status</b></td>");
				out.println("        <td width=\"17%\"><b>No.of units </b></td>");
				out.println("        <td width=\"11%\"><b>Asset Cover </b></td>");
				out.println("       </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_asset_details);
				
				more = rs.next();		
				double asset_cover=0;
				while(more){
					
					
					rs_rental = stmt_rental.executeQuery("  SELECT "+												
						" NVL(VALUE,0) "+ 
						" FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
						" WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
						" A.ASSET_ID='"+rs.getString(3)+"' AND "+
						" A.ACTIVE_STATUS='Y' ");
					
					if(rs_rental.next()){
						
						if(rs_rental.getDouble(1)>0){
							//	asset_cover=(rs.getDouble(12)/rs_rental.getDouble(1))*100;
							asset_cover=(rs_rental.getDouble(1)/rs.getDouble(12))*100; //modified by nuwan de silva 04-jul-07
						}
					}
					
					out.println("       <tr height=\"18\">");
					out.println("        <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_make_details_drill('"+rs.getString(4)+"')\" ><u>"+rs.getString(5)+"</u></td>");
					out.println("        <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_model_details_drill('"+rs.getString(6)+"')\" ><u>"+rs.getString(7)+"</u></td>");
					out.println("        <td>"+rs.getString(8)+"</td>");
					out.println("        <td>"+rs.getString(9)+"</td>");
					out.println("         <td>"+rs.getString(11)+"</td>");
					out.println("          <td>"+rs.getString(10)+"</td>");
					
					out.println("          <td>"+nf.format(asset_cover)+" %</td>");
					
					out.println("         </tr>");
					
					more = rs.next();
				}
				
				out.println("       </table></td>");
				out.println("       <td>&nbsp;</td>");
				out.println("      </tr>");
				
				out.println("      <tr>");
				out.println("         <td colspan=\"4\">&nbsp;</td>");
				out.println("       </tr>");
				out.println("        <tr>");
				out.println("         <td colspan=\"4\"><b>Valuation Details</b></td>");
				out.println("       </tr>");
				out.println("        <tr>");
				out.println("          <td><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
				out.println("            <tr>");
				out.println("              <td width=\"31%\">Valuer</td>");
				out.println("              <td width=\"26%\">Year</td>");
				out.println("              <td width=\"26%\">Market Value </td>");
				out.println("              <td width=\"17%\">FSV</td>");
				out.println("            </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_valuation_details);
				
				more = rs.next();		
				if(!more){
					out.println("            <tr>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("             <td>&nbsp;</td>");
					out.println("            </tr>");
				}
				while(more){
					out.println("            <tr height=\"18\">");
					out.println("             <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuer_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(3)+"</u></td>");
					out.println("             <td>"+rs.getString(4)+"</td>");
					out.println("             <td style= \"cursor:hand;cursor-color:blue\" onclick=\"show_valuation_drill('"+rs.getString(1)+"')\" ><u>"+nf1.format(rs.getDouble(5))+"</u></td>");
					out.println("             <td>"+nf1.format(rs.getDouble(6))+"</td>");
					out.println("            </tr>");
					more = rs.next();
				}
				
				out.println("          </table></td>");
				out.println("          <td colspan=\"3\">&nbsp;</td>");
				out.println("          </tr>");
				out.println("        <tr>");
				out.println("           <td colspan=\"4\">&nbsp;</td>");
				out.println("         </tr>");
				out.println("         <tr>");
				out.println("           <td colspan=\"4\"><b>Facility Details</b></td>");
				out.println("         </tr>");
				
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_facility_details);
				
				more = rs.next();		
				
				String m_pricing_no="";
				//m_pricing_no=rs.getString(1);//commented by indika 09/16/08
				while(more){		
					
					m_pricing_no=rs.getString(1);
					total_charges=0;
					
					out.println("				 <tr>");
					out.println(" <p style=page-break-after:always></p>");
					out.println("       <td height=\"8%\" colspan=\"4\"></p>");
					
					
					out.println("	<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("              <tr> ");
					out.println("                <td colspan=\"3\">&nbsp;</td>");
					out.println("                <td width=\"27%\"  rowspan=\"9\" valign=\"top\"><table width=\"100%\" class='table' bordercolor='black'  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                  <tr>");
					out.println("                    <td width=\"45%\" ><div align=\"center\">Gross </div></td>");
					out.println("                    <td width=\"30%\" ><div align=\"center\">Net</div></td>");
					out.println("                    <td width=\"25%\" ><div align=\"center\">VAT</div></td>");
					out.println("                   </tr>");
					out.println("                   <tr>");
					
					out.println("                     <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
					out.println("                     <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
					out.println("                    <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(4))+"</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");	
					out.println("                <td width=\"45%\"><div align=\"right\"><b>"+rs.getString(5)+" %VAT</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(6))+"</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+rs.getInt(7)+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             </tr>");
					out.println("             <tr>");
					out.println("               <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(9))+"</div></td>");
					out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(10))+"</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(11))+"</div></td>");
					out.println("            </tr>");
					out.println("            <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(12))+"</div></td>");
					out.println("             <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("              <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td width=\"45%\"><div align=\"right\">"+rs.getInt(13)+"</div></td>");
					out.println("              <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("             <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
					out.println("           </tr>");
					out.println("           <tr>");
					out.println("             <td colspan=\"3\"><strong>Rental Stream</strong></td>");
					out.println("             </tr>");
					out.println("           <tr>");
					out.println("            <td width=\"45%\"><div align=\"center\"><strong>Gross</strong></div></td>");
					out.println("             <td width=\"30%\" class=\"style1\"><div align=\"center\"><b>Net</b></div></td>");
					out.println("             <td width=\"25%\" class=\"style1\"><div align=\"center\"><b>VAT</b></div></td>");
					out.println("           </tr>");
					out.println("         </table></td>");
				//	out.println("         <td colspan=\"2\">Expenses included in to the Lease Rental </td>");
					out.println("       </tr>");
					out.println("       <tr>");
					out.println("          <td colspan=\"3\">Net Cost</td>");
					out.println("         <td width=\"35%\" rowspan=\"7\" valign=\"top\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
						"  PRICING_NO, "+ //1
						"  SUB_CHAGE_CODE, "+ //2
						"  DESCRIPTION, "+ //3
						"  SUM(AMOUNT) "+ //4
						//"  CHARGE_TYPE "+
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
						"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  PRICING_NO='"+m_pricing_no+"'  AND "+
						"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
						"  NVL(AMOUNT,0) <> 0 "+
						"  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION ");			
					
					
					
					
					//double total_charges=0;
					//double sum_year2=0;
					//double sum_year3=0;
					//	double sum_year4=0;
					//double sum_year5=0;
					//int    no_of_records=0;
					
					//	double m_NIBSM=0;
					//	int m_AMI=0;
					//	int m_PERIOD=0;
					
					//if(rs_charges.next()){
					
				/*	out.println("  <tr> ");
					out.println("             <td width=\"40%\">Charge Type</td> ");
					out.println("             <td width=\"12%\" align=\"right\">Year1</td> ");
					// out.println("            <td width=\" 12%\"><div align=\"right\">Year2</div></td> "); // commented by udara 17-10-2013
					// out.println("             <td width=\"12%\"><div align=\"right\">Year3</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">Year4</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">Year5</div></td> "); // commented by udara 17-10-2013
					out.println("  </tr> ");
					
					
					//}
					
					while(rs_charges.next()){
						
						
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");
						out.println("            <td width=\"12%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+rs_charges.getString(2)+"')\" ><div align=\"right\"><u>"+nf1.format(rs_charges.getDouble(4))+"</u></div></td>");
						//out.println("            <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013 
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");// commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						total_charges=total_charges+rs_charges.getDouble(4);
					}
					*/ 
				//commented by ishani 2014.02.20
					rs_charges.close();
					stmt_charges.close();
					stmt_charges = conn.createStatement ();
					
					rs_charges =stmt_charges.executeQuery("SELECT "+
						"  PRICING_NO, "+ //1
						"  SUB_CHAGE_CODE, "+ //2
						"  DESCRIPTION, "+ //3
						"  SUM(AMOUNT) ,"+ //4
						"  YEAR_NO+1 "+ //5
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
						"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  PRICING_NO='"+m_pricing_no+"'  AND "+
						"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
						"  NVL(AMOUNT,0) <> 0 "+
						"  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,YEAR_NO "+
						"  ORDER BY SUB_CHAGE_CODE ,YEAR_NO ");
					
					boolean more_maintaince=rs_charges.next();
					
					String m_code="";
					if(more_maintaince){
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">Maintaince</td> ");
						out.println("             <td width=\"12%\" align=\"right\">&nbsp;</td> ");
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
						// out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013 
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");// commented by udara 17-10-2013 
						//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> ");// commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						out.println("  <tr> ");
						out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
						m_code=rs_charges.getString(2);
						
					}
					
					
					int year=1;
					while(more_maintaince){
						
						if(rs_charges.getString(2).equals(m_code)){
							out.println("            <td width=\"12%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
							
							if(year==1){
								total_charges=total_charges+rs_charges.getDouble(4);
							}
							if(year==2){
								sum_year2=sum_year2+rs_charges.getDouble(4);
							}
							if(year==3){
								sum_year3=sum_year3+rs_charges.getDouble(4);
							}
							if(year==4){
								sum_year4=sum_year4+rs_charges.getDouble(4);
							}
							if(year==5){
								sum_year5=sum_year5+rs_charges.getDouble(4);
							}
							
						}
						else{
							out.println("  </tr> ");
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
							out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
							year=1;
							total_charges=total_charges+rs_charges.getDouble(4);
							m_code=rs_charges.getString(2);
							
						}
						more_maintaince=rs_charges.next();
						year=year+1; 
						//total_charges=total_charges+rs_charges.getDouble(4);
					}
					
					
					/*out.println("  <tr> ");
					out.println("             <td width=\"40%\">Total</td> ");
					out.println("              <td width=\"12%\"><div align=\"right\">"+nf1.format(total_charges)+"</div></td>");
					//out.println("            <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year2)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year3)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year4)+"</div></td> "); // commented by udara 17-10-2013
					//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year5)+"</div></td> "); // commented by udara 17-10-2013
					out.println("  </tr> ");
					*/
					
					
					out.println("            </table></td>");
					
					out.println("            <td width=\"10%\">&nbsp;</td>");
					out.println("          </tr>");
					out.println("         <tr>");
					out.println("            <td colspan=\"3\">Rental Based on</td>");
					out.println("           <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Period ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("           <tr>");
					out.println("            <td>AMI</td>");
					out.println("            <td width=\"10%\">&nbsp;</td>");
					out.println("            <td width=\"7%\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
					out.println("              <tr>");
					out.println("                <td><div align=\"center\"><b>"+rs.getInt(14)+"</div></td>");
					out.println("              </tr>");
					out.println("            </table></td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("             <td colspan=\"3\">NIBSM</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\">Supplier Credit ("+rs.getString(8)+")</td>");
					out.println("            <td>&nbsp;</td>");
					out.println("          </tr>");
					out.println("          <tr>");
					out.println("            <td colspan=\"3\"><strong>Installment Structure</strong></td>");
					out.println("           <td>&nbsp;</td>");
					out.println("         </tr>");
					out.println("         <tr>");
					out.println("           <td colspan=\"3\">&nbsp;</td>");
					out.println("           <td colspan=\"2\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					/*String sql_nibsm=" SELECT  "+
						" SUM(NIBSM), "+
						" MAX(AMI), "+
					" MAX(PERIOD) "+	
						" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
						" WHERE UPPER(APPLICATION_NO)= UPPER('"+m_applicaton_no+"') "+
					" AND  ENT_DATE IN(SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"'))";//Added By Sandun on 24-06-2009
					*/
					
					String sql_nibsm=" SELECT  "+
						" SUM(NIBSM), "+
						" MAX(AMI), "+
						" MAX(PERIOD) "+	
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
						" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
						" AND A.APPLICATION_NO=B.APPLICATION_NO "+
						" AND A.INVOICE_NO=B.PRO_INVOICE_NO "+
						" AND A.ACTIVE_STATUS='Y' ";
					rs_rental = stmt_rental.executeQuery(sql_nibsm);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
					{
						m_NIBSM=rs_rental.getDouble(1);
						m_AMI=rs_rental.getInt(2);
						m_PERIOD=rs_rental.getInt(3);
					}
					
					if(m_NIBSM>0)
					{
						no_of_records=m_PERIOD-1;
					}
					else{
						no_of_records=m_PERIOD;
					}
					
					rs_rental.close();
					stmt_rental.close();
					stmt_rental=conn.createStatement ();
					
					
					
					
					String sql_rent_new="   SELECT  "+
						" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
						" SUM(NET_RENTAL_AMOUNT),  "+//2
						" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
						" SUM(GRENTAL_AMOUNT) "+//4
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
						" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
						" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
						" GROUP BY   TO_NUMBER(INSTALLMENT_NO),PRICING_NO "+ //,RENTAL_DATE
						" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
					
					
					
					int end=0;
					int start=0;
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					more3 =rs_rental.next();
					
					if(more3)
					{
						m_rental_new=rs_rental.getDouble(2);
						start=rs_rental.getInt(1);
						m_vat_new=rs_rental.getDouble(3);
						m_gross_new=rs_rental.getDouble(4);
						
						while(more3) //START INSTALLMENT LOOP
						{
							if(m_rental_new!=rs_rental.getDouble(2))
							{
								
								out.println("         <tr>");
								out.println("           <td width=\"11%\">&nbsp;</td>");
								//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
								if(start==end){
									out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
								}
								else{
									out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
								}
								out.println("           <td valign=\"top\"><div align=\"right\">");
								out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
								out.println("                 </tr>");
								out.println("                               </table>");
								out.println("           </div></td>");
								out.println("           <td colspan=\"2\">&nbsp;</td>");
								out.println("         </tr>");
								
								start=rs_rental.getInt(1);		
								m_rental_new=rs_rental.getDouble(2);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
								count_period=0;
							}
							
							count_period=count_period+1;
							end=rs_rental.getInt(1);
							
							more3=rs_rental.next();
							
							if(!more3)
							{
								break;
							}
							
						}
						
						out.println("         <tr>");
						out.println("           <td width=\"11%\">&nbsp;</td>");
						//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
						if(start==end){
							out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
						}
						else{
							out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
						}
						out.println("           <td valign=\"top\"><div align=\"right\">");
						out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                 <tr>");
						out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
						out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
						out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
						out.println("                 </tr>");
						out.println("                               </table>");
						out.println("           </div></td>");
						out.println("           <td colspan=\"2\">&nbsp;</td>");
						out.println("         </tr>");
						
					} //END OF INSTALLMENT LOOP
					
					if(m_NIBSM>0){
						
						
						sql_rent_new="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
							" SUM(NET_RENTAL_AMOUNT),  "+//2
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
							" SUM(GRENTAL_AMOUNT) "+//4
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
							" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
							" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
							" TO_NUMBER(INSTALLMENT_NO) ="+m_PERIOD+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO),PRICING_NO "+ //,RENTAL_DATE
							" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
						
						rs_rental = stmt_rental.executeQuery(sql_rent_new);
						
						more3 =rs_rental.next();
						
						if(more3){	
							out.println("         <tr>");
							out.println("           <td width=\"11%\">&nbsp;</td>");
							out.println("           <td colspan=\"2\"><strong>Final rentals of </strong></td>");
							out.println("           <td valign=\"top\"><div align=\"right\">");
							out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
							out.println("                 </tr>");
							out.println("                               </table>");
							out.println("           </div></td>");
							out.println("           <td colspan=\"2\">&nbsp;</td>");
							out.println("         </tr>");
						}
					}
					
					out.println("       <tr>");
					out.println("          <td colspan=\"6\">&nbsp;</td>");
					out.println("         </tr>");
					out.println("     </table>");
					
					out.println("        <tr>");
					//out.println("	  <p style=page-break-after:always></p>");
					out.println("           <td colspan=\"4\"></p><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("             <tr>");
					out.println("                <td width=\"29%\"><b>True Interst Rate (TIR) </td>");
					out.println("                <td width=\"11%\"><div align=\"right\"><b>"+nf.format(rs.getDouble(15))+"%</div></td>");
					out.println("                <td width=\"60%\">&nbsp;</td>");
					out.println("              </tr>");
					
					
					
					Sql_data_total_income="  SELECT "+												
						" A.APPLICATION_NO, "+
						" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
						" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
						" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
						" AND UPPER(A.PRICING_NO)=UPPER('"+m_pricing_no+"')  "+
						" GROUP BY A.APPLICATION_NO ";
					
					
					rs_rental = stmt_rental.executeQuery(Sql_data_total_income);
					//out.println("pricing No"+m_pricing_no);
					more3 =rs_rental.next();
					
					if(more3){
						
						out.println("              <tr>");
						out.println("                <td><b>Total income earned from the transaction </td>");
						out.println("               <td><div align=\"right\"><b>"+nf1.format(rs_rental.getDouble(2))+"</div></td>");
						out.println("               <td>&nbsp;</td>");
						out.println("             </tr>");
					}				
					
					out.println("           </table></td>");
					out.println("         </tr>");
					
					more = rs.next();		
					// count_period=0;
					
				}
				
				
				
				//_________________total figure______________________________________________________________________________________________
				
				
				if(_m_tmp_pricing_count > 1 ) {
					
					Sql_data_facility_details_sum="  SELECT "+												
						//"  A.PRICING_NO, "+ //1
						"  '' ,"+
						"  SUM(A.TOTAL_AMOUNT), "+ //2
						"  SUM(A.NET_PRICE), "+ //3
						"  SUM(A.VAT) , "+ //4
						"  MAX(B.VAT_PERCENTAGE) , "+ //5
						"  SUM(ROUND(((B.VAT_PERCENTAGE - B.VAT_APP)/100)*B.NET_AMOUNT +B.NET_AMOUNT,0)) NET_APP, "+ //6
						"  MAX(PERIOD), "+ //7
						"  DECODE(MAX(B.PAYMENT_INTERVAL),'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks') INTERVEL, "+ //8
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_GROSS, "+ //9   
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(NET_RENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_NET, "+ //10   
						"  SUM(DECODE(B.AMI,'0',B.AMI, (SELECT NVL(SUM(GRENTAL_AMOUNT-NET_RENTAL_AMOUNT),0) "+
						"  FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
						"  WHERE APPLICATION_NO = B.APPLICATION_NO "+
						"  AND   PRO_INVOICE_NO = B.PRO_INVOICE_NO "+
						"  AND   PRICING_NO     = B.PRICING_NO "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)>=(B.PERIOD-B.AMI) "+
						"  AND   TO_NUMBER(INSTALLMENT_NO)<  B.PERIOD))) AMI_VAT, "+ //11
						"  SUM(B.NIBSM) , "+ //12
						"  MAX(B.SUPPLIER_CREDIT), "+ //13
						"  MAX(B.AMI), "+ //14
						"  MAX(B.RATE) "+ //15
						"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
						"  "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL C  "+
						"  WHERE A.APPLICATION_NO='"+m_applicaton_no+"' AND "+
						"  A.APPLICATION_NO=B.APPLICATION_NO AND "+
						"  A.INVOICE_NO=B.PRO_INVOICE_NO AND "+
						"  C.DURATION=B.PAYMENT_INTERVAL AND "+
						"  A.ACTIVE_STATUS='Y' ";
					// "  GROUP BY A.PRICING_NO,B.VAT_PERCENTAGE,PERIOD,DECODE(B.PAYMENT_INTERVAL,'6','Semi Annualls','3','Quarters','1','Annualls','4','Once Every 4 Months','12','Months','365','Days','52','Weeks'),B.SUPPLIER_CREDIT,B.AMI,B.RATE ";
					
					rs.close();
					stmt.close();
					stmt = conn.createStatement ();
					rs = stmt.executeQuery(Sql_data_facility_details_sum);
					more = rs.next();		
					
					while(more){
						total_charges=0;
						out.println("				 <tr>");
						out.println(" <p style=page-break-after:always></p>");
						out.println("       <td height=\"8%\" colspan=\"4\"></p>");
						
						
						out.println("	<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
						out.println("              <tr> ");
						out.println("                <td colspan=\"3\">&nbsp;</td>");
						out.println("                <td width=\"27%\"  rowspan=\"9\" valign=\"top\"><table width=\"100%\" class='table' bordercolor='black'  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                  <tr>");
						out.println("                    <td width=\"45%\" ><div align=\"center\">Gross</div></td>");
						out.println("                    <td width=\"30%\" ><div align=\"center\">Net</div></td>");
						out.println("                    <td width=\"25%\" ><div align=\"center\">VAT</div></td>");
						out.println("                   </tr>");
						out.println("                   <tr>");
						out.println("                     <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(2))+"</div></td>");
						out.println("                     <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(3))+"</div></td>");
						out.println("                    <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(4))+"</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("                <td width=\"45%\"><div align=\"right\"><b>"+rs.getString(5)+" %VAT</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(6))+"</div></td>");
						out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("               <td width=\"45%\"><div align=\"right\">"+rs.getInt(7)+"</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("               <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             </tr>");
						out.println("             <tr>");
						out.println("               <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(9))+"</div></td>");
						out.println("               <td width=\"30%\"><div align=\"right\">"+nf1.format(rs.getDouble(10))+"</div></td>");
						out.println("              <td width=\"25%\"><div align=\"right\">"+nf1.format(rs.getDouble(11))+"</div></td>");
						out.println("            </tr>");
						out.println("            <tr>");
						out.println("             <td width=\"45%\"><div align=\"right\">"+nf1.format(rs.getDouble(12))+"</div></td>");
						out.println("             <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("              <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("           </tr>");
						out.println("           <tr>");
						out.println("             <td width=\"45%\"><div align=\"right\">"+rs.getInt(13)+"</div></td>");
						out.println("              <td width=\"30%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("             <td width=\"25%\"><div align=\"right\">&nbsp;</div></td>");
						out.println("           </tr>");
						out.println("           <tr>");
						out.println("             <td colspan=\"3\"><strong>Rental Stream</strong></td>");
						out.println("             </tr>");
						out.println("           <tr>");
						out.println("            <td width=\"45%\"><div align=\"center\"><strong>Gross</strong></div></td>");
						out.println("             <td width=\"30%\" class=\"style1\"><div align=\"center\"><b>Net</b></div></td>");
						out.println("             <td width=\"25%\" class=\"style1\"><div align=\"center\"><b>VAT</b></div></td>");
						out.println("           </tr>");
						out.println("         </table></td>");
					//	out.println("         <td colspan=\"2\">Expenses included in to the Lease Rental </td>");
						out.println("       </tr>");
						out.println("       <tr>");
						out.println("          <td colspan=\"3\">Net Cost</td>");
						out.println("         <td width=\"35%\" rowspan=\"7\" valign=\"top\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						
						rs_charges =stmt_charges.executeQuery("SELECT "+
							//"  PRICING_NO, "+ //1
							"  '' ,"+
							"  SUB_CHAGE_CODE, "+ //2
							"  DESCRIPTION, "+ //3
							"  SUM(AMOUNT) "+ //4
							//"  CHARGE_TYPE "+
							"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
							"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
							//"  PRICING_NO='"+m_pricing_no+"'  AND "+
							"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
							"  NVL(AMOUNT,0) <> 0 "+
							"  GROUP BY /*PRICING_NO,*/SUB_CHAGE_CODE,DESCRIPTION ");			
						
						//if(rs_charges.next()){
						
					/*	out.println("  <tr> ");
						out.println("             <td width=\"40%\">Charge Type</td> ");
						out.println("             <td width=\"12%\" align=\"right\">Year1</td> ");
						//out.println("            <td width=\" 12%\"><div align=\"right\">Year2</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year3</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year4</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">Year5</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						
						
						//}
						
						while(rs_charges.next()){
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");
							out.println("            <td width=\"12%\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_charge_drill('"+m_applicaton_no+"','"+m_pricing_no+"','"+rs_charges.getString(2)+"')\" ><div align=\"right\"><u>"+nf1.format(rs_charges.getDouble(4))+"</u></div></td>");
							//out.println("            <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							out.println("  </tr> ");
							
							
							total_charges=total_charges+rs_charges.getDouble(4);
						}
						*/
						rs_charges.close();
						stmt_charges.close();
						stmt_charges = conn.createStatement ();
						
						rs_charges =stmt_charges.executeQuery("SELECT "+
							//"  PRICING_NO, "+ //1
							" '' ,"+
							"  SUB_CHAGE_CODE, "+ //2
							"  DESCRIPTION, "+ //3
							"  SUM(AMOUNT) ,"+ //4
							"  YEAR_NO+1 "+ //5
							"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
							"  WHERE APPLICATION_NO='"+m_applicaton_no+"' AND "+
							//"  PRICING_NO='"+m_pricing_no+"'  AND "+
							"  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
							"  NVL(AMOUNT,0) <> 0 "+
							"  GROUP BY /*PRICING_NO,*/ SUB_CHAGE_CODE,DESCRIPTION,YEAR_NO "+
							"  ORDER BY SUB_CHAGE_CODE ,YEAR_NO ");
						
						boolean more_maintaince=rs_charges.next();
						
						String m_code="";
						if(more_maintaince){
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">Maintaince</td> ");
							out.println("             <td width=\"12%\" align=\"right\">&nbsp;</td> ");
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013 
							//out.println("             <td width=\"12%\"><div align=\"right\">&nbsp;</div></td> "); // commented by udara 17-10-2013  
							out.println("  </tr> ");
							
							
							out.println("  <tr> ");
							out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
							m_code=rs_charges.getString(2);
							
						}
						
						
						int year=1;
						while(more_maintaince){
							
							if(rs_charges.getString(2).equals(m_code)){
								out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
								
								if(year==1){
									total_charges=total_charges+rs_charges.getDouble(4);
								}
								if(year==2){
									sum_year2=sum_year2+rs_charges.getDouble(4);
								}
								if(year==3){
									sum_year3=sum_year3+rs_charges.getDouble(4);
								}
								if(year==4){
									sum_year4=sum_year4+rs_charges.getDouble(4);
								}
								if(year==5){
									sum_year5=sum_year5+rs_charges.getDouble(4);
								}
								
							}
							else{
								out.println("  </tr> ");
								out.println("  <tr> ");
								out.println("             <td width=\"40%\">"+rs_charges.getString(3)+"</td> ");    
								out.println("            <td width=\"10%\" ><div align=\"right\">"+nf1.format(rs_charges.getDouble(4))+"</div></td>");
								year=1;
								total_charges=total_charges+rs_charges.getDouble(4);
								m_code=rs_charges.getString(2);
								
							}
							more_maintaince=rs_charges.next();
							year=year+1; 
							//total_charges=total_charges+rs_charges.getDouble(4);
						}
						
						
						/*out.println("  <tr> ");
						out.println("             <td width=\"40%\">Total</td> ");
						out.println("              <td width=\"12%\"><div align=\"right\">"+nf1.format(total_charges)+"</div></td>");
						//out.println("            <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year2)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year3)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year4)+"</div></td> "); // commented by udara 17-10-2013
						//out.println("             <td width=\"12%\"><div align=\"right\">"+nf1.format(sum_year5)+"</div></td> "); // commented by udara 17-10-2013
						out.println("  </tr> ");
						*/
						
						
						out.println("            </table></td>");
						
						out.println("            <td width=\"12%\">&nbsp;</td>");
						out.println("          </tr>");
						out.println("         <tr>");
						out.println("            <td colspan=\"3\">Rental Based on</td>");
						out.println("           <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\">Period ("+rs.getString(8)+")</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("           <tr>");
						out.println("            <td>AMI</td>");
						out.println("            <td width=\"10%\">&nbsp;</td>");
						out.println("            <td width=\"7%\"><table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
						out.println("              <tr>");
						out.println("                <td><div align=\"center\"><b>"+rs.getInt(14)+"</div></td>");
						out.println("              </tr>");
						out.println("            </table></td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("             <td colspan=\"3\">NIBSM</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\">Supplier Credit ("+rs.getString(8)+")</td>");
						out.println("            <td>&nbsp;</td>");
						out.println("          </tr>");
						out.println("          <tr>");
						out.println("            <td colspan=\"3\"><strong>Installment Structure</strong></td>");
						out.println("           <td>&nbsp;</td>");
						out.println("         </tr>");
						out.println("         <tr>");
						out.println("           <td colspan=\"3\">&nbsp;</td>");
						out.println("           <td colspan=\"2\">&nbsp;</td>");
						out.println("         </tr>");
						
						
						/*	String sql_nibsm=" SELECT  "+
							" SUM(NIBSM), "+
							" MAX(AMI), "+
							" MAX(PERIOD) "+	
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
							" WHERE UPPER(APPLICATION_NO)= UPPER('"+m_applicaton_no+"') "+
							" AND  ENT_DATE IN(SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING WHERE UPPER(APPLICATION_NO)=UPPER('"+m_applicaton_no+"'))";//Added By Sandun on 24-06-2009
							*/
						
						String sql_nibsm=" SELECT  "+
							" SUM(NIBSM), "+
							" MAX(AMI), "+
							" MAX(PERIOD) "+	
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B  "+
							" WHERE A.APPLICATION_NO='"+m_applicaton_no+"'  "+
							" AND A.APPLICATION_NO=B.APPLICATION_NO "+
							" AND A.INVOICE_NO=B.PRO_INVOICE_NO "+
							" AND A.ACTIVE_STATUS='Y' ";
						
						rs_rental = stmt_rental.executeQuery(sql_nibsm);
						//out.println(sql_nibsm);
						boolean more3 =rs_rental.next();
						
						if(more3)
						{
							m_NIBSM=rs_rental.getDouble(1);
							m_AMI=rs_rental.getInt(2);
							m_PERIOD=rs_rental.getInt(3);
						}
						
						if(m_NIBSM>0)
						{
							no_of_records=m_PERIOD-1;
						}
						else{
							no_of_records=m_PERIOD;
						}
						
						rs_rental.close();
						stmt_rental.close();
						stmt_rental=conn.createStatement ();
						
						String sql_rent_new = "";
						
						if(m_termi_type.equals("RESCHEDULE")){
							
							/*sql_rent_new="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
							" SUM(NET_RENTAL_AMOUNT),  "+//2
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
							" SUM(GRENTAL_AMOUNT) "+//4
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
							" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
							" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
							" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+ //,RENTAL_DATE ,PRICING_NO 
							" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
							*/
							
							sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
								" SUM(NET_RENTAL_AMOUNT),   "+
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
								" SUM(GRENTAL_AMOUNT)  "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
								" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
								" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
								" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
								" AND   A.PRICING_NO=B.PRICING_NO "+
								" AND   B.ACTIVE_STATUS='Y' "+
								" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
								" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
							
							
						}
						else{
							sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO) +1 , "+//1
								" SUM(NET_RENTAL_AMOUNT),  "+//2
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
								" SUM(GRENTAL_AMOUNT) "+//4
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
								" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
								//" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
								" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)/*,PRICING_NO */ "+ //,RENTAL_DATE
								" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
						}
						
						
						int end=0;
						int start=0;
						String m_ins="";
						double m_rental_new=0;
						double m_vat_new=0;
						double m_gross_new=0;
						int count_period=0;
						
						rs_rental = stmt_rental.executeQuery(sql_rent_new);
						
						more3 =rs_rental.next();
						
						if(more3)
						{
							m_rental_new=rs_rental.getDouble(2);
							start=rs_rental.getInt(1);
							m_vat_new=rs_rental.getDouble(3);
							m_gross_new=rs_rental.getDouble(4);
							
							
							while(more3) //START INSTALLMENT LOOP
							{
								
								
								if(m_rental_new!=rs_rental.getDouble(2))
								{
									
									
									
									out.println("         <tr>");
									out.println("           <td width=\"11%\">&nbsp;</td>");
									//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
									if(start==end){
										out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
									}
									else{
										out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
									}
									
									
									out.println("           <td valign=\"top\"><div align=\"right\">");
									out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
									out.println("                 <tr>");
									out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
									out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
									out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
									out.println("                 </tr>");
									out.println("                               </table>");
									out.println("           </div></td>");
									out.println("           <td colspan=\"2\">&nbsp;</td>");
									out.println("         </tr>");
									
									
									
									
									start=rs_rental.getInt(1);		
									m_rental_new=rs_rental.getDouble(2);
									m_vat_new=rs_rental.getDouble(3);
									m_gross_new=rs_rental.getDouble(4);
									count_period=0;
								}
								
								count_period=count_period+1;
								end=rs_rental.getInt(1);
								
								more3=rs_rental.next();
								
								if(!more3)
								{
									break;
								}
								
							}
							
							out.println("         <tr>");
							out.println("           <td width=\"11%\">&nbsp;</td>");
							//out.println("           <td colspan=\"2\"><strong>"+count_period+" rentals of </strong></td>");
							if(start==end){
								out.println("           <td colspan=\"2\"><strong>"+start+"  </strong></td>");
							}
							else{
								out.println("           <td colspan=\"2\"><strong>"+start+" - "+end+"  </strong></td>");
							}
							out.println("           <td valign=\"top\"><div align=\"right\">");
							out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
							out.println("                 </tr>");
							out.println("                               </table>");
							out.println("           </div></td>");
							out.println("           <td colspan=\"2\">&nbsp;</td>");
							out.println("         </tr>");
							
							
							
							
						} //END OF INSTALLMENT LOOP
						
						if(m_NIBSM>0){
							
							if(m_termi_type.equals("RESCHEDULE")){
								
								/*sql_rent_new="   SELECT  "+
								" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
								" SUM(NET_RENTAL_AMOUNT),  "+//2
								" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
								" SUM(GRENTAL_AMOUNT) "+//4
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
								" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
								" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+ //COMMENT NUWAN
								" TO_NUMBER(INSTALLMENT_NO) ="+m_PERIOD+" "+
								" GROUP BY   TO_NUMBER(INSTALLMENT_NO)"+ //,RENTAL_DATE ,PRICING_NO  
								" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
								*/
								
								sql_rent_new="   SELECT  "+
									" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
									" SUM(NET_RENTAL_AMOUNT),   "+
									" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
									" SUM(GRENTAL_AMOUNT)  "+
									" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
									" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
									" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
									" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
									" AND   A.PRICING_NO=B.PRICING_NO "+
									" AND   B.ACTIVE_STATUS='Y' "+
									" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
									" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
								
								
							}
							else{
								sql_rent_new="   SELECT  "+
									" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
									" SUM(NET_RENTAL_AMOUNT),  "+//2
									" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
									" SUM(GRENTAL_AMOUNT) "+//4
									" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
									" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND "+
									//" PRICING_NO=UPPER('"+m_pricing_no+"') AND "+
									" TO_NUMBER(INSTALLMENT_NO) ="+m_PERIOD+" "+
									" GROUP BY   TO_NUMBER(INSTALLMENT_NO)/*,PRICING_NO */ "+ //,RENTAL_DATE
									" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
								
							}
							
							rs_rental = stmt_rental.executeQuery(sql_rent_new);
							
							more3 =rs_rental.next();
							
							if(more3){	
								out.println("         <tr>");
								out.println("           <td width=\"11%\">&nbsp;</td>");
								out.println("           <td colspan=\"2\"><strong>Final rentals of </strong></td>");
								out.println("           <td valign=\"top\"><div align=\"right\">");
								out.println("             <table width=\"100%\"  border=\"1\" class='table' bordercolor='black' cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
								out.println("                 </tr>");
								out.println("                               </table>");
								out.println("           </div></td>");
								out.println("           <td colspan=\"2\">&nbsp;</td>");
								out.println("         </tr>");
							}
						}
						
						out.println("       <tr>");
						out.println("          <td colspan=\"6\">&nbsp;</td>");
						out.println("         </tr>");
						out.println("     </table>");
						
						
						out.println("        <tr>");
						out.println("           <td colspan=\"4\"></p><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("             <tr>");
						out.println("                <td width=\"29%\"><b>True Interst Rate (TIR) </td>");
						out.println("                <td width=\"11%\"><div align=\"right\"><b>"+nf.format(rs.getDouble(15))+"%</div></td>");
						out.println("                <td width=\"60%\">&nbsp;</td>");
						out.println("              </tr>");
						
						
						
						Sql_data_total_income="  SELECT "+												
							" A.APPLICATION_NO, "+
							" ROUND(SUM(INTEREST_AMOUNT*(RATE-18)/RATE),0) "+ //value needed
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
							" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
							" UPPER(A.APPLICATION_NO)=UPPER('"+m_applicaton_no+"') "+
							" AND UPPER(A.PRICING_NO)=UPPER('"+m_pricing_no+"')  "+
							" GROUP BY A.APPLICATION_NO ";
						
						
						
						rs_rental = stmt_rental.executeQuery(Sql_data_total_income);
						more3 =rs_rental.next();
						if(more3){
							out.println("              <tr>");
							out.println("                <td><b>Total income earned from the transaction </td>");
							out.println("               <td><div align=\"right\"><b>"+nf1.format(rs_rental.getDouble(2))+"</div></td>");
							out.println("               <td>&nbsp;</td>");
							out.println("             </tr>");
						}				
						out.println("           </table></td>");
						out.println("         </tr>");
						more = rs.next();		
					}
					//______________________________________________________________________________________________________________
					
				} // end no of pricing check
				
				else {
					out.println("         <tr>");
					out.println("           <td colspan=\"4\">&nbsp;</td>");
					out.println("         </tr>");
					
					
					out.println("			<tr> ");
					out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
					out.println("        <tr> ");
					out.println("          <td width=\"11%\">&nbsp;</td> ");
					out.println("          <td width=\"17%\">&nbsp;</strong></td> ");
					out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("                 <tr>");
					out.println("                   <td width=\"45%\"><div align=\"center\">Gross</div></td>");
					out.println("                   <td width=\"30%\"><div align=\"center\">Net</div></td>");
					out.println("                   <td width=\"25%\"><div align=\"center\">VAT</div></td>");
					out.println("                 </tr>");
					
					out.println("         </table></td> ");
					out.println("         <td width=\"45%\">&nbsp;</td> ");
					out.println("       </tr> ");
					out.println("    </table></td> ");
					out.println("   </tr> ");
					
					
					/*String sql_rent_tot="   SELECT  "+
					" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
					" SUM(NET_RENTAL_AMOUNT),   "+
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
					" SUM(GRENTAL_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
					" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND  "+
					" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
					//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE APPLICATION_NO = '"+m_applicaton_no+"') "+//Added by Sandun on 24-06-2009
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
					*/
					
					String sql_rent_tot="   SELECT  "+
						" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
						" SUM(NET_RENTAL_AMOUNT),   "+
						" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
						" SUM(GRENTAL_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
						" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
						" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
						" AND   A.PRICING_NO=B.PRICING_NO "+
						" AND   B.ACTIVE_STATUS='Y' "+
						" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
						" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
						" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
					
					
					
					//out.println(sql_rent_tot);		
					
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					int end=0;
					int start=0;
					
					
					rs_rental = stmt_rental.executeQuery(sql_rent_tot);
					
					boolean more3 =rs_rental.next()	;
					
					if(more3)
					{
						m_rental_new=rs_rental.getDouble(2);
						m_vat_new=rs_rental.getDouble(3);
						m_gross_new=rs_rental.getDouble(4);
						start=rs_rental.getInt(1);
						
						while(more3) //START INSTALLMENT LOOP
						{
							if(m_rental_new!=rs_rental.getDouble(2))
							{
								
								out.println("			<tr> ");
								out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
								out.println("        <tr> ");
								out.println("          <td width=\"11%\">&nbsp;</td> ");
								//out.println("          <td width=\"17%\"><strong>"+count_period+" total rentals of  </strong></td> ");
								if(start==end){
									out.println("          <td width=\"17%\"><strong>"+start+"   </strong></td> ");
								}
								else{
									out.println("          <td width=\"17%\"><strong>"+start+" - "+end+"  </strong></td> ");
								}
								out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
								out.println("                 <tr>");
								out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
								out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
								out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
								out.println("                 </tr>");
								
								out.println("         </table></td> ");
								out.println("         <td width=\"45%\">&nbsp;</td> ");
								out.println("       </tr> ");
								out.println("    </table></td> ");
								out.println("   </tr> ");
								
								start=rs_rental.getInt(1);		
								m_rental_new=rs_rental.getDouble(2);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
								count_period=0;
							}
							
							count_period=count_period+1;
							end=rs_rental.getInt(1);
							
							more3=rs_rental.next();
							
							if(!more3)
							{
								break;
							}
							
						}
						
						
						out.println("			<tr> ");
						out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
						out.println("        <tr> ");
						out.println("          <td width=\"11%\">&nbsp;</td> ");
						//out.println("          <td width=\"17%\"><strong>"+count_period+" total rentals of  </strong></td> ");
						if(start==end){
							out.println("          <td width=\"17%\"><strong>"+start+"   </strong></td> ");
						}
						else{
							out.println("          <td width=\"17%\"><strong>"+start+" - "+end+"   </strong></td> ");
						}
						
						out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
						out.println("                 <tr>");
						out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(m_gross_new)+"</strong></div></td>");
						out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(m_rental_new)+"</strong></div></td>");
						out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(m_vat_new)+"</strong></div></td>");
						out.println("                 </tr>");
						
						out.println("         </table></td> ");
						out.println("         <td width=\"45%\">&nbsp;</td> ");
						out.println("       </tr> ");
						out.println("    </table></td> ");
						out.println("   </tr> ");
						
						
						
						
						
					} //END OF INSTALLMENT LOOP
					
					
					if(m_NIBSM>0){
						
						
						/* sql_rent_tot="   SELECT  "+
						
						" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
						" SUM(NET_RENTAL_AMOUNT),   "+
						" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
						" SUM(GRENTAL_AMOUNT)  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
						" WHERE  APPLICATION_NO=UPPER('"+m_applicaton_no+"') AND  "+
						" TO_NUMBER(INSTALLMENT_NO) = "+m_PERIOD+" "+
						//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE APPLICATION_NO = '"+m_applicaton_no+"') "+//Added by Sandun on 24-06-2009
						" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
						" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
						*/
						
						sql_rent_tot="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
							" SUM(NET_RENTAL_AMOUNT),   "+
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
							" SUM(GRENTAL_AMOUNT)  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
							" WHERE A.APPLICATION_NO=UPPER('"+m_applicaton_no+"')   "+
							" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
							" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
							" AND   A.PRICING_NO=B.PRICING_NO "+
							" AND   B.ACTIVE_STATUS='Y' "+
							" AND   TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
						
						rs_rental = stmt_rental.executeQuery(sql_rent_tot);
						
						more3 =rs_rental.next();
						
						if(more3){	
							out.println("			<tr> ");
							out.println("      <td height=\"14%\" colspan=\"4\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
							out.println("        <tr> ");
							out.println("          <td width=\"11%\">&nbsp;</td> ");
							out.println("          <td width=\"17%\"><strong>Final rental of  </strong></td> ");
							out.println("          <td width=\"27%\"><table width=\"100%\"  class='table' bordercolor='black' border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
							out.println("                 <tr>");
							out.println("                   <td width=\"45%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(4))+"</strong></div></td>");
							out.println("                   <td width=\"30%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(2))+"</strong></div></td>");
							out.println("                   <td width=\"25%\"><div align=\"right\"><strong>"+nf1.format(rs_rental.getDouble(3))+"</strong></div></td>");
							out.println("                 </tr>");
							
							out.println("         </table></td> ");
							out.println("         <td width=\"45%\">&nbsp;</td> ");
							out.println("       </tr> ");
							out.println("    </table></td> ");
							out.println("   </tr> ");
							
						}
					}
					
					
				} // end else part
				
				
				
				
				
				
				
				out.println("         <tr>");
				out.println("           <td colspan=\"4\">&nbsp;</td>");
				out.println("         </tr>");
				out.println("      </table></td>");
				out.println("      </tr>");
				out.println("   </table></td>");
				out.println(" </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				rs = stmt.executeQuery(Sql_data_Comments);
				
				more =rs.next();
				
				while(more){
					
					out.println("  <tr>");
					out.println("    <td colspan=\"5\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					out.println("      <tr>");
					out.println("        <td><b>"+rs.getString(3)+"</td>");
					out.println("      </tr>");
					
					out.println("      <tr>");
					out.println("       <td>"+rs.getString(4)+"</td>");
					out.println("     </tr>");
					out.println("   </table></td>");
					out.println("  </tr>");
					
					more =rs.next();
				}
				out.println("  </table>");
				//added by nuwan de silva 
				out.println("   <p style=\"page-break-after:always\"></p>");		
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");	
				out.println("  <tr>");
				out.println("    <td colspan=\"6\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("      <tr>");
				out.println("       <td><b>APPROVAL PROCESS </td>");
				out.println("     </tr>");
				out.println("    </table></td>");
				out.println(" </tr>");
				out.println(" <tr>");
				out.println("  <td width=\"19%\"><b>Name</td>");
				out.println("  <td width=\"18%\"><b>Designation</td>");
				out.println("  <td width=\"20%\"><b>Screen Name</td>");//Added by Mahela on 15-11-2007
				out.println("  <td width=\"10%\"><b>Time</td>");
				out.println("  <td width=\"10%\"><b>Date</td>");
				out.println(" <td width=\"43%\"><div align=\"center\"><b>Conditions</div></td>");
				out.println(" </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				rs = stmt.executeQuery(Sql_data_approval_prcess);
				
				more =rs.next();
				if(!more){
					
					out.println(" <tr>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
				}
				
				while(more){
					
					if(rs.getString(3).equals("R1") || rs.getString(3).equals("R2") || rs.getString(3).equals("R3") || rs.getString(3).equals("R4") ){
						out.println(" <tr >");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }' >"+rs.getString(4)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(9)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(6)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(7)+"</td>");
						out.println("   <td STYLE='{font:  8pt arial; color=red; }'>"+rs.getString(8)+"</td>");
						out.println(" </tr>");
					}
					else{
						out.println(" <tr>");
						out.println("   <td style=\"{text-color='red'}\">"+rs.getString(4)+"</td>");
						out.println("   <td style=\"{text-color='red'}\">"+rs.getString(9)+"</td>");
						out.println("   <td>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007	
						out.println("   <td style=\"{text-color='red'}\">"+rs.getString(6)+"</td>");
						out.println("   <td style=\"{text-color='red'}\">"+rs.getString(7)+"</td>");
						out.println("   <td style=\"{text-color='red'}\">"+rs.getString(8)+"</td>");
						out.println(" </tr>");
						
					}
					
					
					more =rs.next();
				}
				
				
				out.println("</table>");
				
				
				out.println("<BR>");
				//added by nuwande silva on 27-12-2007 --------------------------------------------------------
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				out.println("<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class='table' bordercolor='black'>");	
				out.println("  <tr>");
				out.println("    <td colspan=\"6\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
				out.println("      <tr>");
				out.println("       <td><b>AGM COMMENTS </td>");
				out.println("     </tr>");
				out.println("    </table></td>");
				out.println(" </tr>");
				out.println(" <tr>");
				out.println("  <td width=\"19%\"><b>Name</td>");
				out.println("  <td width=\"18%\"><b>Designation</td>");
				//out.println("  <td width=\"20%\"><b>Screen Name</td>");//Added by Mahela on 15-11-2007
				out.println("  <td width=\"10%\"><b>Time</td>");
				out.println("  <td width=\"10%\"><b>Date</td>");
				out.println(" <td width=\"43%\"><div align=\"center\"><b>Conditions</div></td>");
				out.println(" </tr>");
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//-------------------------------------------- 
				
				
				rs = stmt.executeQuery(Sql_data_agm_note);
				
				more =rs.next();
				if(!more){
					
					out.println(" <tr>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					//out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
				}
				
				while(more){
					
					out.println(" <tr>");
					out.println("   <td>"+rs.getString(4)+"</td>");
					out.println("   <td>"+rs.getString(9)+"</td>");
					//out.println("   <td>"+rs.getString(2)+"</td>");//Added by Mahela on 15-11-2007	
					out.println("   <td>"+rs.getString(6)+"</td>");
					out.println("   <td>"+rs.getString(7)+"</td>");
					out.println("   <td>"+rs.getString(8)+"</td>");
					out.println(" </tr>");
					
					more =rs.next();
				}
				
				
				out.println("</table>");
				
				
				
				///////// manjula on 25.03.2008	
				out.println("<BR>");
				out.println("<BR>");
				out.println("<BR>");
				out.println("<BR>");
				out.println("<BR>");
				
				out.println("<table border='0' align='center' width='100%' class='table' border=\"0\">"); 
				out.println("<tr>");
				out.println("<td width='40%' style='{text-align:left;}'><b>..........................................</td>");
				out.println("<td width='20%' style='{text-align:left;}'><b></td>");
				out.println("<td width='40%' style='{text-align:left;}'><b>..........................................</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td width='40%' style='{text-align:left;}'><b>Authorized Signatory</td>");
				out.println("<td width='20%' style='{text-align:left;}'><b></td>");
				out.println("<td width='40%' style='{text-align:left;}'><b>Authorized Signatory</td>");
				out.println("</tr>");
				out.println("</table>");	
				
				
				
				
				
				
				
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				
				out.println("</body>");
				
				out.println("</html>");
				
				
				
				
			}
			
			//=============================================================================================================
			out.flush();
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs_rental !=null){try{rs_rental.close(); }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt_rental  !=null){try{stmt_rental.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			
			//if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
