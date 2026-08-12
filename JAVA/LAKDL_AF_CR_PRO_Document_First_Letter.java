//ID         :
//SCREEN NAME:Document Printing - First Letter
//CREATED BY :Nuwan De Silva	
//DATE/TIME  : 07-02-2006
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_First_Letter extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental;
	
	// commented by udara 01-11-2018
	/*
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	*/
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		// added by udara 01-11-2018
		//String m_html_client_url,
		//String reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
		String reqstr=null,m_Letter_date=null,m_c_code=null,m_name=null,m_city_desc=null,m_due_date=null,m_no_of_due_date=null,m_finance_no=null;
	    double m_amount_due;
	    String rec_count="";
		// end by udara 01-11-2018
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			//	String m_chksql;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			// out.println("conn"+conn);
			int m_data_count=0;
			String m_status ="";
			
			
			//Decaring variables
			
			String m_full_name="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_repayment_interval="";
			String m_start_date="";
			String m_master_lease="";
			String m_nic_no="";
			
			String m_end_date="";
			String m_no_of="";
			String m_no_of_mon="";
			String m_rental="";
			int m_period=0;
			String m_rental_start_date="";
			String m_rental_start_day="";
			double m_gross_rental=0;
			String m_credit_manager_name="";
			String m_make_code="";
			String m_make_desc="";
			String m_item_sub_code="";
			String m_model_code="";
			String m_last_rental_date="";
			String m_last_rental_date_year=""; // added by udara on 07-12-2012
			String m_invoice_no="";
			double m_od_interest_rate=0;
			int b_flag=0;
			int m_period_time=0;
			double m_security_margin_val=0;
			double m_residual_value=0;
			String m_rental_due_date="";
			String m_sub_model=""; //added by nuwan de silva 04-07-07
			String m_pricing_no="";  //added by nuwan de silva 04-07-07
			int m_qty=0;
			String m_item_desc="";
			
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			
			String m_chksql = req.getParameter("chksql");
			String m_application_no = req.getParameter("application_no");
			stmt = conn.createStatement ();
			
			int odi_rate = 0; // udara 12-11-2015
			
			if(m_chksql.trim().equals("main_page")){
				//stmt = conn.createStatement ();
				stmt_doc_charges = conn.createStatement ();
				stmt_make = conn.createStatement ();
				stmt_rental= conn.createStatement (); //added by nuwan de silva on 10-09-07
				
				//String m_application_no = req.getParameter("application_no");
				//String m_client_code	  =req.getParameter("client_code");		
				String m_client_code="";
				String m_document_code="";
				String m_print="";
				if(req.getParameter("client_code")!=null){
					m_client_code	  =req.getParameter("client_code");		
				}
				if(req.getParameter("document_code")!=null){
					m_document_code	=req.getParameter("document_code");	
				}
				if(req.getParameter("print")!=null){
					m_print=req.getParameter("print");
				}
				
				//String m_document_code	=req.getParameter("document_code");	
				//String m_print=req.getParameter("print");
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
				
				
				rs = stmt.executeQuery ("SELECT RATE FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
				if(rs.next()){
					odi_rate = rs.getInt(1);
				}
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				if(req.getParameter("status")==null){
					
					rs=stmt.executeQuery (" SELECT "+
						" COUNT(DOCUMENT_CODE) "+
						" FROM "+m_schema_name+".AF_CR_PRO_DOCUMENT_STATUS "+
						" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND CLIENT_CODE=UPPER('"+m_client_code+"') AND DOCUMENT_CODE=UPPER('"+m_document_code+"') ");
					
					more = rs.next();
					if(more){
						m_data_count=rs.getInt(1);
					}
					
					if(m_data_count==0){
						m_status="ORIGINAL";
					}
					else{
						m_status="COPY";
					}
					
				}
				else
				{
					m_status=req.getParameter("status");
				}
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				//Company Details============================================
				rs = stmt.executeQuery(" SELECT "+
					" NVL(UPPER(COMPANY_NAME),' '), "+
					" NVL(UPPER(ADDRESS1),' '), "+
					" NVL(UPPER(ADDRESS2),' '), "+
					" NVL(UPPER(CITY),' '), "+
					" NVL(TEL_NO,' '), "+
					" NVL(FAX_NO,' '),  "+
					" NVL(VAT_RATE,0) "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				more = rs.next();		
				
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
				
				//=====================================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				
				String Client_Data=" SELECT  "+
					" 'CLIENT', "+ //1
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  || */UPPER(FULL_NAME)),' ') ,   "+ //2
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+ //6
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =  "+
					" (SELECT  "+
					" CLIENT_CODE "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) "+
					
					" UNION "+
					
					" SELECT  "+
					" 'CO-APPLICANT', "+
					" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',/*'MESS' || '. '  ||*/ UPPER(FULL_NAME)),   "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
					" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-'),   "+
					" NVL(DECODE(CLIENT_TYPE,'I',UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ')    "+ //2
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
					" WHERE   CLIENT_CODE =  "+
					" (SELECT  "+
					" CO_APPLICANT  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
				
				
				/*   comment by nuwan de sivla on 04-09-07-----------------------------
				//Client Details=======================================================
				rs = stmt.executeQuery(" SELECT "+
				" NVL(TITLE,' '), "+
				//" NVL(UPPER(FULL_NAME),' '), "+
				" DECODE(CLIENT_TYPE,'I',NVL(UPPER(TITLE),' ')||' '||NVL(UPPER(FULL_NAME),' '),'C',NVL(UPPER(FULL_NAME),' ')), "+ //MODIFIED BY Chandana on 26/07/2007 For Ref No.748
				" REPLACE(REPLACE(NVL(UPPER(ADDRESS1),' '),'-',' '),'null',' '),"+
				" REPLACE(REPLACE(NVL(UPPER(ADDRESS2),' '),'-',' '),'null',' '),"+
				" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') "+
				" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
				" WHERE   CLIENT_CODE = "+
				" (SELECT "+
				" CLIENT_CODE "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
				" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
				
				more = rs.next();		
				
				if(more){
				m_title=rs.getString(1);
				m_full_name=rs.getString(2);
				m_add1=rs.getString(3);
				m_add2=rs.getString(4);
				m_city_name=rs.getString(5);
				}
				*/
				
				
				/*if(!m_full_name.equals(" "))
				{
				m_full_name=m_full_name+",";
				}
				
				if(!m_add1.equals(" "))
				{
				m_add1=m_add1+",";
				}
				if(!m_add2.equals(" "))
				{
				m_add2=m_add2+",";
				}*/
				
				//============================================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				rs=stmt.executeQuery (" SELECT "+
					"  NVL(FINANCE_NO,'-'), "+
					"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE,nvl(MASTER_AGREEMENT_NO,'-') "+
					" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
				
				if(more){
					m_finance_no=rs.getString(1);
					m_start_date=rs.getString(2);
					m_master_lease=rs.getString(3);
					m_Letter_date=rs.getString(4);
				}
				/* String sql_make=" SELECT DISTINCT "+
					" C.MAKE_CODE,       "+ //1
					" NVL(C.MAKE_DESC,' '),  "+ //2
					" NVL(G.DESCRIPTION,' '), "+ //3
						" NVL(B.MODEL_CODE,' '), "+	 //4
						" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||G.DESCRIPTION), "+
						" NVL(B.SUB_MODEL_CODE,' '), "+ //5 
						" B.PRICING_NO, "+ //6
						" SUM(A.QTY), "+ //7
						" D.MODEL_CODE ||' '||D.DESCRIPTION ||'-'|| G.DESCRIPTION DESCRIPTION "+ //8	
						//" '' "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E ,"+m_schema_name+".AF_CO_MAS_MODEL F, "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY G "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
					" A.ACTIVE_STATUS='Y' AND "+
					" A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
					" A.ASSET_ID=B.ASSET_ID AND "+
					" C.MAKE_CODE= "+
					" (SELECT  "+
					"  MAKE_CODE   "+
					"  FROM "+m_schema_name+".AF_CO_MAS_MODEL  "+
					"  WHERE   "+
					"  MODEL_CODE IN (  "+
					"  SELECT  "+
					"  MODEL_CODE   "+
					"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+ 
					" WHERE INVOICE_NO=B.INVOICE_NO "+
					"  )) AND "+
					"  D.SUB_CODE=B.SUB_MODEL_CODE AND "+ 
					"  UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
					"  UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) "+ 
					"  AND B.MODEL_CODE=F.MODEL_CODE "+
						"  AND F.ITEM_SUB_CAT=G.ITEM_SUB_CAT "+
					"   GROUP BY C.MAKE_CODE,C.MAKE_DESC,G.DESCRIPTION,B.MODEL_CODE,F.DESCRIPTION,B.SUB_MODEL_CODE,B.PRICING_NO,D.MODEL_CODE,D.DESCRIPTION,G.DESCRIPTION ";
					
				*/
				
				//--------Added by Chandana on 16/07/07 ---------------------//
				/* String sql_make= "	SELECT "+
								" NVL(B.REG_NO,'-'), "+
										" nvl(B.ENGINE_NO,'-'), "+
										" nvl(B.CHASSIS_NO,'-'), "+
										" B.MODEL_CODE,"+
										" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+
										" NVL(D.YEAR_OF_MANUFACTURE,''), "+
										" B.PRICING_NO, "+
										" NVL(B.SUB_MODEL_CODE,' '), "+ 
										" INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
										" C.MAKE_CODE, "+
										" F.ITEM_SUB_CAT,"+
										" UPPER(E.VENDOR_CODE),"+
										" UPPER(E.BRANCH), "+
										" INITCAP(G.NAME) "+
										" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
										" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
										" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
										" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
										" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
										" "+m_schema_name+".AF_CO_MAS_MODEL F , "+
										" "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
										" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
										" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
										" A.ACTIVE_STATUS='Y' AND "+
										" B.ACTIVE_STATUS='Y' AND "+
										" A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
										" A.ASSET_ID=B.ASSET_ID AND "+
										" C.MAKE_CODE=(SELECT "+
										" MAKE_CODE "+
										" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
										" WHERE "+
										" MODEL_CODE IN ( SELECT "+
										" MODEL_CODE "+
										" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
										" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
										" )) AND "+
										" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
										" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
										" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
										" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
										" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
										" B.MODEL_CODE=F.MODEL_CODE ";
				
				*/
				
				//--------Added by Chandana on 08/08/2007 Ref no.783---------------------//
				
				String sql_make= " SELECT '','','', "+
					" MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,' '/*PRICING_NO*/,SUB_MODEL_CODE,REG_NO "+
					" FROM "+
					" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
					" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
					" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
					" B.MODEL_CODE, "+
					//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ // comment by nuwan de silva on 12-12-2007 at ofscl
					" INITCAP(D.DESCRIPTION) MODEL_DESC, "+ // added by nuwan de silva on 12-12-2007 at ofscl
					" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
					" '' , /*B.PRICING_NO,*/ "+
					" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
					" INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
					" C.MAKE_CODE, "+
					" F.ITEM_SUB_CAT, "+
					" UPPER(E.VENDOR_CODE), "+
					" UPPER(E.BRANCH), "+
					" INITCAP(G.NAME) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
					" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
					" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL F , "+
					" "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
					" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
					" A.ACTIVE_STATUS='Y' AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
					" A.ASSET_ID=B.ASSET_ID AND "+
					" C.MAKE_CODE=(SELECT "+
					" MAKE_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE "+
					" MODEL_CODE IN ( SELECT "+
					" MODEL_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
					" )) AND "+
					" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
					" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
					" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
					" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
					" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
					" B.MODEL_CODE=F.MODEL_CODE ) "+
					" GROUP BY MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE,REG_NO ";   //PRICING_NO
				
				//------ End Ref no.783 -----------------------------//		
				
				
				
				
				String sql_make_new= " SELECT '','','', "+
					" MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,PRICING_NO,SUB_MODEL_CODE "+
					" FROM "+
					" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
					" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
					" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
					" B.MODEL_CODE, "+
					//" INITCAP(C.MAKE_DESC||' '||F.DESCRIPTION||' '||D.DESCRIPTION ||' '||H.DESCRIPTION) MODEL_DESC, "+ // comment by nuwan de silva on 12-12-2007 at ofscl
					" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ // added by nuwan de silva on 12-12-2007 at ofscl
					" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
					" B.PRICING_NO, "+
					" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
					" INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
					" C.MAKE_CODE, "+
					" F.ITEM_SUB_CAT, "+
					" UPPER(E.VENDOR_CODE), "+
					" UPPER(E.BRANCH), "+
					" INITCAP(G.NAME) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
					" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
					" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL F , "+
					" "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
					" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
					" A.ACTIVE_STATUS='Y' AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
					" A.ASSET_ID=B.ASSET_ID AND "+
					
					" C.MAKE_CODE=(SELECT "+
					" MAKE_CODE "+
					" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
					" WHERE "+
					" MODEL_CODE IN ( SELECT "+
					" MODEL_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
					" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
					" )) AND "+
					" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
					" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
					" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
					" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
					" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
					" B.MODEL_CODE=F.MODEL_CODE ) "+
					" GROUP BY PRICING_NO,MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE ";   //PRICING_NO
				
				
				//========================================================================================
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				
				//Credit Manager Details================================================================
				rs=stmt.executeQuery (" SELECT "+
					"  NVL(UPPER(NAME),'-') "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
					" WHERE UPPER(POSITION)='CREDIT MANAGER' ");
				
				
				more = rs.next();
				
				if(more){
					m_credit_manager_name=rs.getString(1);
				}
				
				//=======================================================================================
				
				//Interest Rate=======================================================
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	
				
				rs=stmt.executeQuery (" SELECT "+ 	 
					" RATE "+
					" FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE "+
					" WHERE ACTIVE_STATUS='Y' ");
				
				more=rs.next();
				if(more){
					m_od_interest_rate=rs.getDouble(1);
				}
				//===================================================================
				
				String sql_anx_status = " SELECT ROWNUM,GRENTAL_AMOUNT "+      
					" FROM( SELECT GRENTAL_AMOUNT "+
					" FROM( SELECT TO_NUMBER(INSTALLMENT_NO), "+
					" SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"') "+
					" AND TO_NUMBER(INSTALLMENT_NO) NOT IN((SELECT COUNT(INSTALLMENT_NO)-1 "+
					" FROM(SELECT TO_NUMBER(INSTALLMENT_NO) INSTALLMENT_NO , "+
					" SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"') "+
					" GROUP BY  TO_NUMBER(INSTALLMENT_NO) "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO))), "+
					" (SELECT COUNT(INSTALLMENT_NO) -2 "+
					" FROM(SELECT TO_NUMBER(INSTALLMENT_NO) INSTALLMENT_NO , "+
					" SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"') "+
					" GROUP BY TO_NUMBER(INSTALLMENT_NO) "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO))),0 ) "+
					" GROUP BY TO_NUMBER(INSTALLMENT_NO) "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO) ) "+
					" GROUP BY GRENTAL_AMOUNT) ";
				
				
				//=====================================================================
				
				out.println("<html><head>"); 
				out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>First Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");
				
				out.println("function get_annexure(m_application_no){");
				
				
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_First_Letter?chksql=ANNEXURE;");
				
				//comment by nuwan de silva on 10-09-07--------------------------------------------------------------------
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Document_First_Letter?chksql=ANNEXURE&application_no=\"+m_application_no;");
				//out.println("load_interface(m_url,'NORM');");
				
				//	out.println("window.open(m_url);");
				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				//added by nuwan de silva on 05-09-07
				
				//comment by nuwan de silva on 10-09-07-----------------
				/*out.println("if('"+rec_count+"'!='1'){"); 
				out.println(" m_table_annexure.innerHTML =\"\" ; ");
				out.println(" m_table_annexure.innerHTML = http_response; ");
				out.println("}"); 
				out.println("else {");  //added by nuwan de silva on 05-09-07
				out.println(" m_table_annexure.innerHTML =\"\" ; ");
				out.println("}"); 
				*/
				
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
				out.println("}");
				
				
				
				
				out.println("function save_data(){");
				//out.println("get_annexure('"+m_application_no+"')");
				out.println("m_table.innerHTML=\"\" ");
				out.println("window.print();");
				
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				out.println(" window.location.href=m_url;"); 
				
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
				
				out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
				out.println("<body bgcolor='white'><br>");
				out.println("<form name='Form1'>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<blockquote><font size=3><p style='text-align:left'>");					
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
				out.println("</table>");
				out.println("</font></p></blockquote>");	
				//out.println("<br><br><br><br><br>");
				//out.println("<br><br><br><br><br><br><br><br>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				
				//	out.println("<br>");
				
				/*out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_full_name+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add1+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_add2+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' ><b>"+m_city_name+"</td></tr>");
				out.println("</table>");
				*/
				
				//================================================================================
				rs = stmt.executeQuery(Client_Data);
				more = rs.next();		
				
				while(more){	
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);
					
					if(rs.getString(1).equals("CLIENT")){
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' align='center' class='rep-body1' ><font size=2><B>"+m_full_name+"</B></td></tr>");
						out.println("<tr><td width='*%' align='center' class='rep-body1' ><font size=2><B>"+m_add1+", "+m_add2+"</B></td></tr>");
						//out.println("<tr><td width='*%' align='center' class='rep-body1' ><B>"+m_add2+"</B></td></tr>");
						out.println("<tr><td width='*%' align='center' class='rep-body1' ><font size=2><B>"+m_city_name+"</B></td></tr>");
						out.println("</table>");	
					}
					
					else if(rs.getString(1).equals("CO-APPLICANT")){
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' align='center' ><font size=2><B>And</B></td></tr>");
						out.println("</table>");	
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' align='center'><font size=2><B>"+m_full_name+" </B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' align='center'><font size=2><B>"+m_add1+", "+m_add2+"</B></td></tr>");
						//out.println("<tr><td width='*%' class='rep-body1' align='center'><B>"+m_add2+"</B></td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' align='center'><font size=2><B>"+m_city_name+"</B></td></tr>");
						out.println("</table>");	
					}
					more = rs.next();		
				}
				
				//================================================================================
				
				out.println("<br><br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				
				//out.println("<tr><td width='40%' class='rep-body1' ><b>Master Lease Agreement No</td>");
				//out.println("<td width='*%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_master_lease+"</td></tr>");
				
				out.println("<tr><td width='100%' colspan='2' class='rep-body1' ><font size=3><b><u>HIRE PURCHASE AGREEMENT NO "+m_finance_no+"</u></b></td>");
				out.println("</tr>");
				//out.println("<td width='*%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_finance_no+"</td></tr>");
				
				//out.println("<tr><td width='40%' class='rep-body1' ><b>Date</td>");
				//out.println("<td width='*%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_start_date+"</td></tr>");
				
				out.println("</table>");
				out.println("<br>");
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>We refer to the above mentioned HIRE PURCHASE agreement and give below the following particulars.</td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("<br>");	
				
				rs_make=stmt_make.executeQuery (sql_make_new)	;
				
				int j=0;
				more=rs_make.next();
				/*
				if(more){
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='40%' class='rep-body1' ><b>Leased Asset/s</td>");
					out.println("<td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;</b></td></tr>"); 
					out.println("</table>");
				}
				*/
				int count=1;
				while(more){
					
					if(count==1){
						m_make_desc=rs_make.getString(5);
					}
					if(count!=1){
						m_make_desc+=", "+rs_make.getString(5);
					}
					count=count+1;
					more=rs_make.next();
				}
				/*
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='40%' class='rep-body1' >&nbsp;</td>");
				out.println("<td width='*%' class='rep-body1' ><b>"+m_make_desc+"</b></td></tr>"); 
				out.println("</table>");
				*/
				
				String reg_no = "";
				rs_make=stmt_make.executeQuery (sql_make)	;
				//more=rs_make.next();
				// while(rs_make.next()){
				if(rs_make.next()){
					m_make_code=rs_make.getString(1);
					m_make_desc=rs_make.getString(5);
					m_item_sub_code=rs_make.getString(3);
					m_model_code=rs_make.getString(4);
					m_sub_model=rs_make.getString(8); //ADDED BY NWUAN DE SILVA 04-07-07
					m_pricing_no=rs_make.getString(7); //ADDED BY NWUAN DE SILVA 04-07-07
					reg_no =rs_make.getString(9); 
					
					/*out.println("<br>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='40%' class='rep-body1' ><b>LEASED ASSET</td>");
					out.println("<td width='*%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_make_desc+"</b></td></tr>"); //"+m_item_sub_code+"&nbsp; &nbsp;  "+m_item_desc+"
					out.println("</table>");
					out.println("</font></p></blockquote>");
					*/
					
					
					
					//	if(j==0){
					/*
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
					out.println("For your convenience, a summary of information relating to above mentioned Lease agreement is "+	
						"given hereunder.");
					out.println("</font></p></blockquote>");
					*/
					/*
					out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='*%' class='rep-body1' ><b>LEASE PAYMENT PLAN</B></td></tr>");
					out.println("</table>");
					*/
					//	out.println("<br>");
					// }
					
					/*	rs=stmt.executeQuery(" SELECT "+
							" DISTINCT TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY') START_DATE, "+
							" B.PERIOD,'', "+
						//	" TO_CHAR(C.RENTAL_DATE, 'fmddth') || ' ' ||   TO_CHAR(C.RENTAL_DATE, 'Month')||    TO_CHAR(RENTAL_DATE, 'YYYY') rental_date, "+
							" SUM(C.GRENTAL_AMOUNT), "+
							" B.NIBSM,B.RESIDUAL_VALUE,'',nvl(to_char(A.ACTIVATED_DATE,'fmddth'),'-')  "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B , "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT C "+
						" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
							" A.APPLICATION_NO=C.APPLICATION_NO "+
							" AND C.INSTALLMENT_NO=1  "+
						" AND A.APPLICATION_NO=UPPER('"+m_application_no+"') "+
							//" AND B.PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+ //MODIFIED BY NUWAN DE SILVA 04-07-07
							" AND B.PRICING_NO=UPPER('"+m_pricing_no+"') "+
							" GROUP by A.ACTIVATED_DATE,B.PERIOD,B.NIBSM,B.RESIDUAL_VALUE ");
							
							
							//----modified by : delanjali------------------------------
							//----date				: 2007-06-06-----------------------------
							
							//" AND B.PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
							//---------------------------------------------------------
						*/	
					
					
					//--Close the Result Set And Stateement--------			
					//rs.close();
					//stmt.close();
					//---------------------------------------------
					//--Create The Statement----------------------
					//stmt = conn.createStatement ();
					//--------------------------------------------	
					
					
					// added by udara 17-03-2017
					rs=stmt.executeQuery(" SELECT "+
						" COUNT(A.INSTALLMENT_NO) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE A.APPLICATION_NO =B.APPLICATION_NO  AND  "+
						"       A.PRICING_NO     =B.PRICING_NO      AND  "+
						"       A.APPLICATION_NO =C.APPLICATION_NO  AND  "+
						"       A.PRO_INVOICE_NO =B.PRO_INVOICE_NO  AND  "+
						"       A.APPLICATION_NO =UPPER('"+m_application_no+"') AND  "+
						"       TO_NUMBER(A.INSTALLMENT_NO) <> 0       AND  "+
						//"       TO_NUMBER(A.INSTALLMENT_NO) <> 1       AND  "+
						"       A.PRO_INVOICE_NO IN (SELECT INVOICE_NO   "+
						"                            FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS      "+
						"                            WHERE  "+
						"                                  APPLICATION_NO =UPPER('"+m_application_no+"') AND   "+
						"                            	      MODEL_CODE=UPPER('"+m_model_code+"')          AND 	 "+
						"                                   SUB_MODEL_CODE=UPPER('"+m_sub_model+"')    AND   "+
						"                                   ACTIVE_STATUS='Y' "+
						"                                   ) "+
						"  ");
					
					if(rs.next()){
						m_period	=rs.getInt(1);
					}
					
					// end by udara 17-03-2017
					
					rs=stmt.executeQuery(" SELECT "+
						" TO_CHAR(C.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(C.ACTIVATED_DATE, 'Month')|| TO_CHAR(C.ACTIVATED_DATE, 'YYYY') START_DATE, "+
						" B.PERIOD,  '', "+
						" SUM(GRENTAL_AMOUNT), "+
						" SUM(B.NIBSM), "+
						" SUM(B.RESIDUAL_VALUE), '',  "+
						//" NVL(TO_CHAR(C.ACTIVATED_DATE,'fmddth'),'-') "+ //comment by nuwan de silva on 22-10-07
						" NVL(TO_CHAR(A.RENTAL_DATE,'fmddth'),'-') "+ //added by nuwan de silva on 22-10-07
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
						" WHERE A.APPLICATION_NO =B.APPLICATION_NO  AND  "+
						"       A.PRICING_NO     =B.PRICING_NO      AND  "+
						"       A.APPLICATION_NO =C.APPLICATION_NO  AND  "+
						"       A.PRO_INVOICE_NO =B.PRO_INVOICE_NO  AND  "+
						"       A.APPLICATION_NO =UPPER('"+m_application_no+"') AND  "+
						"       TO_NUMBER(A.INSTALLMENT_NO)=1       AND  "+
						//"       A.PRICING_NO     =UPPER('"+m_pricing_no+"') AND  "+
						"       A.PRO_INVOICE_NO IN (SELECT INVOICE_NO   "+
						"                            FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS      "+
						"                            WHERE /*PRICING_NO=UPPER('"+m_pricing_no+"')      AND    */ "+
						"                                  APPLICATION_NO =UPPER('"+m_application_no+"') AND   "+
						"                            	      MODEL_CODE=UPPER('"+m_model_code+"')          AND 	 "+
						"                                   SUB_MODEL_CODE=UPPER('"+m_sub_model+"')    AND   "+
						"                                   ACTIVE_STATUS='Y' "+
						"                                   ) "+
						" GROUP BY B.NIBSM,B.RESIDUAL_VALUE,B.PERIOD,C.ACTIVATED_DATE,A.RENTAL_DATE ");
					
					more=rs.next();
					if(more){
						m_start_date=rs.getString(1);
						//m_period	=rs.getInt(2); // commented by udara 17-03-2017
						m_rental_start_date=rs.getString(3);
						m_gross_rental=rs.getDouble(4);
						m_security_margin_val=rs.getDouble(5);
						m_residual_value=rs.getDouble(6);
						m_rental_due_date=rs.getString(8);
						if(m_security_margin_val>0 || m_residual_value>0)
						{
							b_flag=1;
						}
						
						
						//--Close the Result Set And Stateement--------			
						rs.close();
						stmt.close();
						//---------------------------------------------
						//--Create The Statement----------------------
						stmt = conn.createStatement ();
						//--------------------------------------------	
						
						
						rs=stmt.executeQuery(" SELECT "+
							" DECODE(DURATION_TYPE,'Daily','Days','Monthly','Months','Weekly','Weeks','Quarterly','Quarters','Semi Annually','Half Years','Annually','Years','Once Every 4 Months','Once Every 4 Months' ) INTERVELS, "+ 
							" DECODE(DURATION_TYPE,'Daily','Day','Monthly','Month','Weekly','Week','Quarterly','Quarter','Semi Annually','Half Year','Annually','Year','Once Every 4 Months','Once Every 4 Month' ) INTERVEL "+ 
							" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
							" WHERE DURATION IN( "+
							" SELECT DISTINCT PAYMENT_INTERVAL "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+ 
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) ");
						
						more = rs.next();
						
						if(more){
							
							m_no_of=rs.getString(1);
							m_no_of_mon=rs.getString(2);//added by nuwan de silva on 10-09-07
						}
						
						
						//if(b_flag==1)
						//{
						m_period_time=m_period-1;
						
						//}else
						//{
						//m_period_time=m_period;
						//}
						
						
						
						//--Close the Result Set And Stateement--------			
						rs.close();
						stmt.close();
						//---------------------------------------------
						//--Create The Statement----------------------
						stmt = conn.createStatement ();
						//--------------------------------------------	
						
						rs=stmt.executeQuery (" SELECT "+
							// " TO_CHAR(RENTAL_DATE, 'fmddth') || ' ' ||   TO_CHAR(RENTAL_DATE, 'Month')||    TO_CHAR(RENTAL_DATE, 'YYYY') rental_date "+ //comment by nuwan de silva on 10-09-07-------
							" TO_CHAR(MIN(RENTAL_DATE), 'fmddth') || ' ' ||   TO_CHAR(MIN(RENTAL_DATE), 'Month')||    TO_CHAR(MIN(RENTAL_DATE), 'YYYY') rental_date, "+ //added by nuwan de silva on 10-09-07
							" TO_CHAR(MIN(RENTAL_DATE), 'YYYY') "+ // added by udara on 07-12-2012
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
							//" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT GROUP BY APPLICATION_NO)  "+
							" AND   TO_NUMBER(INSTALLMENT_NO) = 1 "+ /*added by ns on 12-11-2012*/
							""); //Added By Sandun on 03-12-2008
						//----modified by : delanjali------------------------------
						//----date				: 2007-06-06-----------------------------
						
						//AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
						//---------------------------------------------------------
						
						//"AND INSTALLMENT_NO="+m_period_time+" "); --comment by nuwan de silva on 10-09-07-------
						
						more = rs.next();
						if(more){
							m_last_rental_date=rs.getString(1);
							m_last_rental_date_year = rs.getString(2);
						}
						
						//while(rs_invoice.next()){
						/*
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='5%'  class='rep-body1' ><b>1.</b></td>");
						out.println("    <td width='35%' class='rep-body1' ><b>Monthly Rental Inclusive of VAT</b></td>");
						out.println("<td width='*%'  class='rep-body1' ><b>:&nbsp;</b></td></tr>");
						out.println("</table>");		
						*/
						
						
						//=====================================================
						
						/*String sql_rent_new="   SELECT  "+
													" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
													" SUM(NET_RENTAL_AMOUNT),  "+//2
													" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
													" SUM(GRENTAL_AMOUNT), "+//4
													" TO_CHAR(RENTAL_DATE,'MON-YYYY') "+//5
													" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A  "+
													//" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
													" WHERE  A.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
													//" AND A.PRICING_NO=UPPER('"+m_pricing_no+"')  "+
													//" AND TO_NUMBER(INSTALLMENT_NO) < B.PERIOD "+
													" AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT GROUP BY APPLICATION_NO) "+ //Added By Sandun on 04-12-2008
													" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+ //,RENTAL_DATE //A.PRICING_NO
													" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
						 */							
						
						//added by nuwan de silva 08-07-2009
						
						
						
						//=====================================================
						
						
						//comment by nuwan de silva on 10-09-07-------------
						/*	rs_anx_status=stmt.executeQuery (sql_anx_status);
							boolean more_anx=rs_anx_status.next();
							
							if(more_anx){		
							rec_count=rs_anx_status.getString(1);
							}
							
							if(!rec_count.equals("1")){		
							out.println("    <td width='30%' class='rep-body1' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_annexure('"+m_application_no+"')\" ><b>:&nbsp;&nbsp;<u>Refer Annexure</u></b></td>");
							}else{
							out.println("    <td width='30%' class='rep-body1' ><b>:&nbsp;&nbsp;Rs."+nf.format(m_gross_rental)+"</b></td>");
							}
							
							*/
						
						
						//out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						//out.println("<tr></tr>");
						out.println("</table>");		
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
						//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
						//out.println("    <td width='30%' class='rep-body1' ><font size=2>ITEM</td>");  // commented by udara 12-11-2013
						out.println("    <td width='30%' class='rep-body1' ><font size=2>VEHICLE</td>"); // added by udara 12-11-2013
						if(!reg_no.equals("-")){
							out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+m_make_desc+"("+reg_no+")</td>"); //pathum..width change to 30%to40% 29/11/2012
						}
						else{
							out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+m_make_desc+"</td>"); //pathum 29/11/2012
						}
						out.println("    <td width='*%' class='rep-body1' >&nbsp;</td></tr>");
						
						out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
						//	out.println("<td width='2%' class='rep-body1' ><b></b></td>");
						//out.println("    <td width='30%' class='rep-body1' ><font size=2>TERM</td>"); // commented by udara 12-11-2013
						out.println("    <td width='30%' class='rep-body1' ><font size=2>LOC</td>"); // added by udara 12-11-2013
						out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp; "+m_period+" &nbsp; Months</td>"); //pathum 29/11/2012
						out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						out.println("<tr></tr>"); 
						
						/*
						rs_doc_charges=stmt_doc_charges.executeQuery (" SELECT "+
							" B.DESCRIPTION,  "+
							" SUM(A.AMOUNT)  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B  "+
							" WHERE UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"')  "+
							// " AND UPPER(A.PRICING_NO)=UPPER('"+m_pricing_no+"')    "+
							" AND A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE   "+
							" AND B.SUB_TYPE_CODE='DOCUMENT' "+ //ADDEDV BY NUWAN DE SILVA 04-07-07
							"  GROUP BY B.DESCRIPTION ");
						
						while(rs_doc_charges.next()){
							out.println("<tr></tr>");
							out.println("<tr><td width='5%' class='rep-body1' >&nbsp;</td>");
							out.println("    <td width='35%' class='rep-body1' >MONTHLY RENTAL</td>");
							out.println("    <td width='30%' class='rep-body1' >:&nbsp;&nbsp;Rs."+nf.format(rs_doc_charges.getDouble(2))+"</td>");
							out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						}
						*/
						
						
						String			sql_rent_new="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) +1 ,  "+
							" SUM(NET_RENTAL_AMOUNT),   "+
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
							" SUM(GRENTAL_AMOUNT) , "+
							" TO_CHAR(RENTAL_DATE,'MON-YYYY') "+//5
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
							" WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')   "+
							" AND   A.APPLICATION_NO=B.APPLICATION_NO "+
							" AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
							" AND   A.PRICING_NO=B.PRICING_NO "+
							" AND   B.ACTIVE_STATUS='Y' "+
							" AND   A.INSTALLMENT_NO <> 0  "+ // added by udara 17-03-2017
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
						
						int end=0;
						int start=0;
						String m_ins="";
						double m_rental_new=0;
						double m_vat_new=0;
						double m_gross_new=0;
						int count_period=0;
						String rental_start_date="";
						String rental_end_date="";
						
						rs_rental = stmt_rental.executeQuery(sql_rent_new);
						
						boolean more3 =rs_rental.next();
						
						if(more3)
						{
							m_rental_new=rs_rental.getDouble(2);
							start=rs_rental.getInt(1);
							rental_start_date=rs_rental.getString(5);
							m_vat_new=rs_rental.getDouble(3);
							m_gross_new=rs_rental.getDouble(4);
							
							
							while(more3) //START INSTALLMENT LOOP
							{
								
								
								if(m_rental_new!=rs_rental.getDouble(2))
								{
									/*
									out.println("<table border='0' width='80%' class='table'>"); 		
									out.println("<tr><td width='5%' class='rep-body1' ><b>&nbsp;</b></td>");
									out.println("    <td width='35%' class='rep-body1' ><b>&nbsp;</b></td>");
										out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of_mon+" ,"+rental_start_date+" - "+rental_start_date+")  </b></td>");
										out.println("<tr></tr>");
									
									out.println("</table>");
									*/
									
									out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
									//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
									out.println("    <td width='30%' class='rep-body1' ><font size=2>MONTHLY RENTAL</td>");
									out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+nf.format(m_gross_new)+"</td>"); //pathum 29/11/2012
									out.println("    <td width='*%' class='rep-body1' ></td></tr>");
									out.println("<tr></tr>"); 
									
									start=rs_rental.getInt(1);		
									rental_start_date=rs_rental.getString(5);
									m_rental_new=rs_rental.getDouble(2);
									m_vat_new=rs_rental.getDouble(3);
									m_gross_new=rs_rental.getDouble(4);
									count_period=0;
								}
								
								count_period=count_period+1;
								end=rs_rental.getInt(1);
								rental_end_date=rs_rental.getString(5);
								
								more3=rs_rental.next();
								
								if(!more3)
								{
									break;
								}
								
							}
							
							
							out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
							//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
							out.println("    <td width='30%' class='rep-body1' ><font size=2>MONTHLY RENTAL</td>");
							out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;SLR&nbsp;"+nf.format(m_gross_new)+"</td>"); //pathum 29/11/2012
							out.println("    <td width='*%' class='rep-body1' ></td></tr>");
							out.println("<tr></tr>"); 
							
						}
						
						
						out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
						//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
						out.println("    <td width='30%' class='rep-body1' ><font size=2>DUE DATE</td>");
						//----modified by : delanjali-------------------------------------------------------------------------------
						//----date				: 2007-06-06------------------------------------------------------------------------------
						//		out.println("    <td width='30%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_rental_start_date+"</b></td>");
						//----------------------------------------------------------------------------------------------------------
						//out.println("    <td width='70%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+m_rental_due_date.toUpperCase()+" OF EACH MONTH</td>"); //pathum 29/11/2012 
						out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+m_rental_due_date+" OF EACH MONTH</td>"); //pathum 30/11/2012

						out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						out.println("<tr></tr>");
						out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
						//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
						out.println("    <td width='30%' class='rep-body1' ><font size=2>NEXT RENTAL DUE ON</td>");
						//out.println("    <td width='70%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+m_last_rental_date.toUpperCase()+"</td>"); //pathum 29/11/2012
						
						//Added by pathum 30/11/2012
						//....split the date to show the super script of the date.....//
						String[] parts = m_last_rental_date.split(" ");
						String part1 = parts[0];
						String part2 = parts[1];
						//String part3 = parts[2]; // commented by udara on 07-12-2012
						String part3 = m_last_rental_date_year; // added by udara on 07-12-2012
						out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;"+part1+" "+part2.toUpperCase()+" "+part3+"</td>"); //pathum 30/11/2012
						//.............//
						
						out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						out.println("<tr></tr>");
						//out.println(m_rental_start_date);
						
						
						
						/*
						out.println("<tr><td width='5%' class='rep-body1' ><b>6.</b></td>");
						out.println("    <td width='35%' class='rep-body1' ><b>Date of Last Payment</b></td>");
						out.println("    <td width='30%' class='rep-body1' ><b>:&nbsp;&nbsp;"+m_last_rental_date+"</b></td>");
						out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						out.println("<tr></tr>");
						*/
						out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
						//out.println("<td width='2%' class='rep-body1' ><b></b></td>");
						out.println("    <td width='30%' class='rep-body1' ><font size=2>INSURANCE</td>");
						out.println("    <td width='40%' class='rep-body1' ><font size=2>:&nbsp;&nbsp;COMPREHENSIVE*</td>"); //pathum 29/11/2012
						out.println("    <td width='*%' class='rep-body1' ></td></tr>");
						out.println("<tr></tr>");
						
						out.println("</table>");
						
						// out.println("<br>");
					}
					
					j=j+1;//Added by Nuwan De Silva 23-04-2007
					
				}
				out.println("</font></p></blockquote>");
				out.println("</font></p></blockquote>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");		
				
				
				String data="*Insurance to be renewed yearly with an assignment to "+m_orient_name.toUpperCase()+" including (SRCC, FLOOD & TC covers)";
				
				
				out.println("<table border='0' width='80%' class='table'>"); 	
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2><b>"+data+"</b></td></tr>");
				out.println("</table>");
				//data="We wish to inform you that OVERDUE INTEREST at 5% P.M is charged on all rentals received after the due date. As such we request you to kindly ensure that payments are made to us promptly on the due date."; // commented by udara 12-11-2015
				data="We wish to inform you that OVERDUE INTEREST at "+odi_rate+"% P.M is charged on all rentals received after the due date. As such we request you to kindly ensure that payments are made to us promptly on the due date."; // added by udara 12-11-2015
				out.println("<table border='0' width='90%' class='table'>"); 	
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("</table><br>");
				
				data="Please quote above agreement number when making payments.";
				out.println("<table border='0' width='90%' class='table'>"); 	
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("</table><br>");
				
				data="We trust that the information given above is sufficient for your purpose. If however, you require any further information or any clarification, please don't hesitate to contact the undersigned.";
				out.println("<table border='0' width='90%' class='table'>"); 	
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("</table><br>");
				
				data="Thanking you for the opportunity given us to serve you.";
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("</table><br>");
				
				// commented by udara 10-11-2016
				/*
				data="Yours faithfully,";
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ></td></tr>");
				out.println("</table>");			
				
				out.println("<br><br>"); // added by udara 05-11-2015
				
				// added by udara 30-10-2015
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2> <img src=\""+m_html_client_url+"/sign_agm/sign_agm.jpg\"  > </td></tr>"); // height=\""+new_height+"\" width=\""+new_width+"\"
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ></td></tr>");
				out.println("</table>");
				// end by udara 30-10-2015
				
				
				//out.println("<br><br><br><br>"); // commented by udara 05-11-2015
				//out.println("<br><br>"); // added by udara 05-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				
				
				
				//data="Assistant General Manager"; // commented by udara 02-06-2014
				//data="Assistant Recovery Manager"; // added by udara 02-06-2014
			   //	data="Manager Recoveries"; // mod by udara 31-03-2015
				// out.println("<br><br><br><br><br>Authorized Signatory");
				out.println("<table border='0' width='90%' class='table'>"); 	
			//	out.println("<tr>");
			//	out.println("    <td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2>"+m_orient_name.toUpperCase()+"</td></tr>");
				out.println("</table>");
				//out.println("<br><br>");  
				out.println("<br>");  // added by udara 31-03-2015
				
				*/
				// end commented by udara 10-11-2016
				
				
				// added by udara 10-11-2016
				data="Yours faithfully,";
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("    <tr><td width='*%' class='rep-body1' ><font size=2>"+data+"</td></tr>");
				out.println("    <tr><td width='*%' class='rep-body1' ><font size=2>"+m_orient_name.toUpperCase()+"</td></tr>");
				out.println("</table>");
				out.println("<br>"); 
				// end by udara 10-11-2016
				
				
				// commented below by udara on 02-01-2012
				/*
				String			sql_guaranter="  SELECT  INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(a.guarantor_code)),"+m_schema_name+".AF_CO_GET_CLIENT_NAME(a.guarantor_code) "+
					" FROM "+m_schema_name+".af_co_pro_appli_guarantor a "+
					" WHERE a.application_no = '"+m_application_no+"'  ORDER BY "+m_schema_name+".AF_CO_GET_CLIENT_NAME(a.guarantor_code) ";
				
				*/
				
				// added below by udara on 02-01-2012
				String sql_guaranter = " SELECT "+ 
									   " INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(A.GUARANTOR_CODE)), "+ // 1
									   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE), "+ // 2
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(A.GUARANTOR_CODE),'-') "+  // 3   
									       " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
									       " WHERE A.APPLICATION_NO = '"+m_application_no+"'  "+
									       " ORDER BY "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE) ";
				
				
				rs_rental = stmt_rental.executeQuery(sql_guaranter);
				int gua_count = 0;
				while(rs_rental.next()){
					gua_count++;
					
					out.println("<table border='0' width='90%' class='table'>"); 	
					out.println("<tr>");
					out.println("<td width='2%' class='rep-body1' ><b></b></td>");
					out.println("<td width='5%' class='rep-body1' ><font size=2>"+gua_count+")</td>");
					if(rs_rental.getString(1) == null){
						//out.println("    <td width='*%' class='rep-body1' ><font size=2>Guarantor   - "+rs_rental.getString(2)+"</td></tr>"); // commented by udara on 02-01-2012
						out.println("    <td width='*%' class='rep-body1' ><font size=2>Guarantor   - "+rs_rental.getString(2)+" ,"+rs_rental.getString(3)+" </td></tr>"); // added by udara on 02-01-2012
					}else{
						//out.println("    <td width='*%' class='rep-body1' ><font size=2>Guarantor   - "+rs_rental.getString(1)+" "+rs_rental.getString(2)+"</td></tr>"); // commented by udara on 02-01-2012
						out.println("    <td width='*%' class='rep-body1' ><font size=2>Guarantor   - "+rs_rental.getString(1)+" "+rs_rental.getString(2)+" ,"+rs_rental.getString(3)+"</td></tr>"); // added by udara on 02-01-2012
					}
					out.println("</table>");
					
				}
				
				
				/*
				//out.println("<br><U>IMPORTANT</U>");
				data="<U>IMPORTANT</U>";
				out.println("<table border='0' width='90%' class='table'>"); 	
				out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
				out.println("    <td width='*%' class='rep-body1' >"+data+"</td></tr>");
				out.println("</table>");
				
				
				//out.println("<BR>When every payment/s are made by cheque/s or cash please ensure that you receive a "+
				//            "official receipt/s. If you are issuing third party cheque/s, please ensure that those cheque/s are endorsed by you. " ); //modified by nuwan de silva on 19-09-07
				data="<BR>When every payment/s are made by cheque/s or cash please ensure that you receive a "+
					"official receipt/s. If you are issuing third party cheque/s, please ensure that those cheque/s are endorsed by you. " ; //modified by nuwan de silva on 19-09-07
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr><td width='5%' class='rep-body1' ><b></b></td>");
				out.println("    <td width='*%' class='rep-body1' >"+data+"</td></tr>");
				out.println("</table>");
				*/
				
				//out.println("</font></p></blockquote></blockquote>"); // commented by udara 10-11-2016
				out.println("</font>"); // added by udara 10-11-2016
			
				//out.println("<br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				
				
				// added by udara 10-11-2016
				out.println("<table border='0' align='center' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2 >Note: This is a computer generated letter and hence no signature is required. </font> </td>"); 
				out.println("</tr>");
				out.println("</table>");
				// end by udara 10-11-2016
				
				// added by udsara on 02-01-2013
				
				out.println("<table border='0' align='center' width='80%' class='table'>"); 		
				out.println("<tr>");
				//out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2 face=\"aKandyNew\" >s#.ý </font> : <font size=2 face=\"aKandyNew\" > âAhl pÝvRwny s>h` päÓt blNn </font></td>"); // commented by udara on 11-02-2013
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2 ><b> &#3523;&#3536;.&#3514;&#3540;: &#3523;&#3538;&#3458;&#3524;&#3517; &#3508;&#3515;&#3538;&#3520;&#3515;&#3530;&#3501;&#3505;&#3514; &#3523;&#3503;&#3524;&#3535; &#3508;&#3523;&#3540; &#3508;&#3538;&#3495; &#3510;&#3517;&#3505;&#3530;&#3505; </b> </font> </td>"); 
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("</p></blockquote></blockquote>"); // added by udara 10-11-2016
				
				// end by udara on 02-01-2013
				
				//comment by nuwan de silva on 10-09-07--------------------------
				/*out.println("<table align='center' width='80%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"80%\"><DIV ID='m_table_annexure'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				*/
				
				
				/* if (m_print.trim().equals("ANNEXURE")) {
					
							String Sql_Pricing="   SELECT  "+
							" TO_NUMBER(INSTALLMENT_NO) ,  "+
							" SUM(NET_RENTAL_AMOUNT),   "+
							" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
							" SUM(GRENTAL_AMOUNT)  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
							" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')   "+ //AND
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
									
							rs= stmt.executeQuery(Sql_Pricing);
							
							boolean more_pricing = rs.next();
							out.println("   <p style=\"page-break-after:always\"></p>");  
							out.println("<blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");	
							out.println("<table align='center' width='80%' class='table' >");
							out.println("<tr>");
							out.println("<td width='*%' class=div_input align='center'><b><u>ANNEXURE</u></b></td>");
							out.println("</tr>");
							out.println("</table>");
												
							out.println("<table align='center' width='80%' class='table' border='1' bordercolor='black' cellspacing='0' >");
							
							out.println("<tr >"); //class=pdn_txtpos2
							out.println("<td width='20%' class=div_input><b>Installment No</b></td>");
							out.println("<td width='20%' align='right' class=div_input><b>Net Amount&nbsp;&nbsp</b></td>");
							out.println("<td width='20%' align='right' class=div_input><b>VAT Amount&nbsp;&nbsp</b></td>");
							out.println("<td width='20%' align='right' class=div_input><b>Gross Amount&nbsp;&nbsp</b></td>");
							out.println("</tr>");
							
						double sum_net=0;
							double sum_vat=0;
							double sum_gross=0;
					
						while(more_pricing){
							out.println("<tr>");
							out.println("<td width='20%' class=div_input>"+rs.getString(1)+"</td>");
							out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp</td>");
							out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp</td>");
							out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp</td>");
							out.println("</tr>");
							
							sum_net=sum_net+rs.getDouble(2);
							sum_vat=sum_vat+rs.getDouble(3);
							sum_gross=sum_gross+rs.getDouble(4);
							
							more_pricing = rs.next();
						}
			
							out.println("</table>");
												
							//out.println("<br><br>Yours faithfully");
							//out.println("<br><b>"+m_orient_name.toUpperCase()+"</b>");	
							//out.println("<br><br><br><br>Authorized Signatory");
							//out.println("</font></p></blockquote>");		
					}
					*/
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			else if(m_chksql.trim().equals("ANNEXURE")){			
				
				
				String Sql_Pricing=" SELECT  "+
					" (TO_NUMBER(INSTALLMENT_NO)+1) INSTALLMENT_NO  ,  "+
					" SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT,   "+
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
					" SUM(GRENTAL_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
					" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')   "+ //AND 
					" AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM LAKDL.AF_CO_PRO_APP_INSTALLMENT GROUP BY APPLICATION_NO)  "+ //Added By Sandun on 04-12-2008
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO) "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
				
				rs= stmt.executeQuery(Sql_Pricing);
				
				boolean more_pricing = rs.next();
				out.println("   <p style=\"page-break-after:always\"></p>");  
				out.println("<blockquote><font size=3><p style='text-align:justify' class='rep-body1'>");	
				out.println("<table align='center' width='80%' class='table' >");
				out.println("<table align='center' width='80%' class='table' >");
				out.println("<tr>");
				out.println("<td width='*%' class=div_input align='center'><b><u>ANNEXURE</u></b></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table align='center' width='80%' class='table' border='1' bordercolor='black' cellspacing='0' >");
				
				out.println("<tr >"); //class=pdn_txtpos2
				out.println("<td width='20%' class=div_input><b>Installment No</b></td>");
				out.println("<td width='20%' align='right' class=div_input><b>Net Amount&nbsp;&nbsp</b></td>");
				out.println("<td width='20%' align='right' class=div_input><b>VAT Amount&nbsp;&nbsp</b></td>");
				out.println("<td width='20%' align='right' class=div_input><b>Gross Amount&nbsp;&nbsp</b></td>");
				out.println("</tr>");
				
				double sum_net=0;
				double sum_vat=0;
				double sum_gross=0;
				
				while(more_pricing){
					out.println("<tr>");
					out.println("<td width='20%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp</td>");
					out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp</td>");
					out.println("<td width='20%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp</td>");
					out.println("</tr>");
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					
					more_pricing = rs.next();
				}
				out.println("</table>");
				
				out.println("<br><br>");
				out.println("<table align='center' width='80%' class='table' border='0'  cellspacing='0' >");
				out.println("<td width='*%' class=div_input>Yours faithfully</td>");					
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='80%' class='table' border='0'  cellspacing='0' >");
				out.println("<td width='*%' class=div_input><b>"+m_orient_name.toUpperCase()+"</td>");					
				out.println("</table>");
				out.println("<br><br><br><br>");
				out.println("<table align='center' width='80%' class='table' border='0'  cellspacing='0' >");
				out.println("<td width='*%' class=div_input><b>Authorized Signatory</td>");					
				out.println("</table>");
				out.println("</table>");
				out.println("</font></p></blockquote>");						
			}
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
