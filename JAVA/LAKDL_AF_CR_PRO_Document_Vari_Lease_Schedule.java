//ID         :
//SCREEN NAME:Document Printing - Variable Lease Schedule

//NOTES:
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// Modified by Mahela on 20-06-2007

public class LAKDL_AF_CR_PRO_Document_Vari_Lease_Schedule extends javax.servlet.http.HttpServlet 
{ 

 ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt_location,stmt_invoice,stmt_rental,stmt_partner,stmt_rental2;
//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
  public ResultSet rs,rs_location,rs_invoice,rs_rental,rs_partner,rs_rental2;

	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException 
	{ 
		 
		try 
		{ 
			 
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
			
			
			//Decaring variables
			
			String m_full_name="";
			String m_full_name1="";
			String m_add1="";
			String m_add2="";
			String m_city_name="";
			String m_title="";
			String m_client_type=""; 
			String m_asset_type="";
			String m_asset_type_desc="";
			String m_repayment_interval="";
			String m_start_date="";
			String m_end_date="";
			String m_no_of="";
			String m_rental="";
			String m_period="";
			String m_rental_start_date="";
			String m_rental_start_day="";
			double m_security_margin_val=0;
			double m_residual_value=0;
			String m_security_margin="";
			String m_type_use="";
			String m_finance_no="";
			String m_insurance="NO";
			String m_date="";
			String m_agreement_date="";
			String m_master_lease="";
			String m_b_cert_no="";
			String m_ami=""; 
			
			double m_od_interest_rate=0;
			Vector data_item,data_item2,data_item3,data_item4,data_item5,data_item6;
			int install [];
		  int rows;
			int count=0;
			int size=0;
			int b_flag=0;
			int arr_size=0;
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_address="";
			String m_partner_name [];
			int m_data_count=0;
		  String m_status ="";
		 String m_chksql = req.getParameter("chksql");
			
  	 if(m_chksql.trim().equals("main_page"))
			{
			stmt = conn.createStatement ();
			stmt_location = conn.createStatement ();
			stmt_invoice = conn.createStatement ();
			stmt_rental = conn.createStatement ();
			stmt_partner= conn.createStatement ();
			stmt_rental2 = conn.createStatement ();
						
			String m_application_no = req.getParameter("application_no");
			String m_client_code	  =req.getParameter("client_code");		
			m_client_type	  =req.getParameter("client_type");	
			String m_document_code	=req.getParameter("document_code");	
			String m_print=req.getParameter("print");
			
							rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YY') FROM DUAL ");
							boolean more = rs.next();
							if(more){
							m_Letter_date=rs.getString(1);
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
							//out.println("test1"+more);
							m_data_count=rs.getInt(1);
							}
							if(m_data_count==0){
							m_status="ORIGINAL";
							}	else{
							m_status="COPY";
							}
							}else{
							m_status=req.getParameter("status");
							}
							
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
							//	out.println("test2"+more);
							m_od_interest_rate=rs.getDouble(1);
							}
				
					//--Close the Result Set And Stateement--------			
					rs.close();
					stmt.close();
					//---------------------------------------------
					//--Create The Statement----------------------
					stmt = conn.createStatement ();
					//--------------------------------------------
			
					 rs = stmt.executeQuery(" SELECT "+
					    " UPPER(NVL(COMPANY_NAME,' ')), "+
					    " UPPER(NVL(ADDRESS1,' ')), "+
					    " UPPER(NVL(ADDRESS2,' ')), "+
					    " UPPER(NVL(CITY,' ')), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0) "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
							 
								more = rs.next();		
											if(more)
											{
										//	out.println("test3"+more);
											m_orient_name=rs.getString(1);
											m_orient_add1=rs.getString(2);
											m_orient_add2=rs.getString(3);
											m_orient_city_name=rs.getString(4);
											m_orient_tel_no=rs.getString(5);
											m_orient_fax_no=rs.getString(6);
											m_orient_vat_rate=rs.getString(7);			
											}
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
				
				// comment by nuwan de silva on 06-09-07----------------------
				/*rs = stmt.executeQuery(" SELECT "+
				 " DISTINCT ITEM_CAT_CODE "+
         "  FROM   "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY "+
         "  WHERE  ITEM_SUB_CAT IN( "+
         "    SELECT "+
         "       ITEM_SUB_CAT "+ 
         "    FROM "+
         "      "+m_schema_name+".AF_CO_MAS_MODEL "+ 
         "    WHERE "+
         "      MODEL_CODE IN( "+
         "           SELECT "+
         "              MODEL_CODE "+
         "           FROM "+
         "             "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
         "           WHERE "+
         "            UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
         "                 ) "+
         "                 ) ");
				*/
				//added by nwuan de silva on 06-09-07--------------------------------------
				 rs = stmt.executeQuery(" SELECT "+
				" DISTINCT A.ITEM_CAT_CODE,UPPER(B.DESCRIPTION) "+
				" FROM   "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY A,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY B "+
				" WHERE  A.ITEM_CAT_CODE=B.ITEM_CAT_CODE "+
				" AND ITEM_SUB_CAT IN( "+
				" SELECT ITEM_SUB_CAT   "+
				" FROM "+m_schema_name+".AF_CO_MAS_MODEL   "+
				" WHERE MODEL_CODE IN "+
				" (SELECT MODEL_CODE  "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') )  "+
				" ) ");

        more = rs.next();		
				if(more)
				{
				m_asset_type=rs.getString(1);
				m_asset_type_desc=rs.getString(2);
				
				}
				
				String Sql_stipulated="	 SELECT "+
				"  YEAR, "+
				"  NET_AMOUNT "+
				"  FROM "+m_schema_name+".AF_CR_PRO_STIPULATED_VALUE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				"  WHERE	 A.FINANCE_NO=B.FINANCE_NO AND UPPER(B.APPLICATION_NO)=UPPER('"+m_application_no+"') ORDER BY YEAR ";

			String Client_Data=" SELECT  "+
			" 'CLIENT', "+ //1
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),' ') ,   "+ //2 'MESS' || '. '  || 
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+ //3
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+ //4
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+ //5
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-')   "+ //6
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+
			" CLIENT_CODE "+ 
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) "+
			
			" UNION "+
			
			" SELECT  "+
			" 'CO-APPLICANT', "+
			" DECODE(CLIENT_TYPE,'I',UPPER(TITLE) || '. ' || UPPER(FULL_NAME),'C',UPPER(FULL_NAME)),   "+ //'MESS' || '. '  || 
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS1),'C',UPPER(REGISTERED_ADDRESS1)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',UPPER(ADDRESS2),'C',UPPER(REGISTERED_ADDRESS2)),' ') , "+
			" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' '),  "+
			" NVL(DECODE(CLIENT_TYPE,'I',NIC_NO,  'C',BUSINESS_CERTIFICATE_NO),'-')   "+
			" FROM "+m_schema_name+".AF_CO_MAS_CLIENT  "+
			" WHERE   CLIENT_CODE =  "+
			" (SELECT  "+
		  " CO_APPLICANT  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
			" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')) ";
			
			
			
				rs_location =stmt_location.executeQuery (" SELECT "+
	      " NVL(UPPER(ADDRESS),' '), "+
        " NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),' ') "+
        " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
        " ACTIVE_STATUS='Y' ");
				
				
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------
						
        rs=stmt.executeQuery (" SELECT "+
        "  NVL(FINANCE_NO,'-'), "+
				"  DECODE(INSURANCE_DONE_BY,'LESSEE','YES','NO') INSURANCE, "+
				"	NVL(TO_CHAR(ACTIVATED_DATE,'fmddth Mon yyyy'),'-'),nvl(MASTER_AGREEMENT_NO,'-') ,"+
				" NVL(TO_CHAR(AGREEMENT_DATE, 'fmddth Mon yyyy'  ),'-') AGREEMENT_DATE "+ //MODIFIED BY NUWAN DE SILVA 12-07-07
        " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
        " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
			if(more)
				{
			//	out.println("test7"+more);
			  m_finance_no=rs.getString(1);
			//  m_insurance=rs.getString(2);
				m_date=rs.getString(3);
				m_master_lease=rs.getString(4);
				m_agreement_date=rs.getString(5);
			  }
				
				
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------
			
				//ADDED BY NUWAN DE SILVA 11-07-07------------------------------------------				
        rs=stmt.executeQuery (" SELECT "+ 
															 " AMOUNT, "+
															 " APPLICATION_NO "+
															 " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_MAINTEIN "+
															 " WHERE SUB_CHAGE_CODE='INSURANCE' AND  "+
															 " APPLICATION_NO=UPPER('"+m_application_no+"') ");

			more = rs.next();
			if(more){
			  m_insurance="YES";
			  }
				
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------
																
				//----------------------------------------------------------------------------			
				rs=stmt.executeQuery (" SELECT "+
				" DURATION_TYPE "+
				" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
				" WHERE DURATION IN( "+
				" SELECT DISTINCT PAYMENT_INTERVAL "+
				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+ 
				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
				" ) ");
			more = rs.next();
			if(more)
				{
			//	out.println("test8"+more);
			m_repayment_interval=rs.getString(1);
			}
			if(m_repayment_interval.trim().equals("Daily"))
				{
			m_no_of="DAYS";
			m_rental="DAILY";
			}
			else if(m_repayment_interval.trim().equals("Monthly"))
				{
			m_no_of="MONTHS";
			m_rental="MONTHLY";
			}
			else if(m_repayment_interval.trim().equals("Weekly"))
				{
			m_no_of="WEEKS";
			m_rental="WEEKLY";
			}
			else if(m_repayment_interval.trim().equals("Quarterly"))
				{
			m_no_of="QUARTERS";
			m_rental="QUARTERLY";
			}
			else if(m_repayment_interval.trim().equals("Semi Annually"))
				{
			m_no_of="HALF YEARS";
			m_rental="SEMI ANNUALLY";
			}
				else if(m_repayment_interval.trim().equals("Annually"))
				{
			m_no_of="YEARS";
			m_rental="ANNUALLY";
			}
			else if(m_repayment_interval.trim().equals("Once Every 4 Months"))
				{
			
			m_no_of="ONCE EVERY 4 MONTHS";
			m_rental="ONCE EVERY 4 MONTHS";
			}
			//--added by nuwan de silva on 18-07-07--------
			rs=stmt.executeQuery (" SELECT "+
			" MAX(AMI) "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
      " WHERE APPLICATION_NO=UPPER('"+m_application_no+"')");
			more = rs.next();
	 	if(more)
			{
			m_ami=rs.getString(1);
			}	
		//--Close the Result Set And Stateement--------			
		rs.close();
		stmt.close();
		//---------------------------------------------
		//--Create The Statement----------------------
		stmt = conn.createStatement ();
		//--------------------------------------------
			
			
		//Modifed nuwan De silva 07-06-07================	
			if(m_repayment_interval.trim().equals("Daily"))
				{
			rs=stmt.executeQuery (" SELECT "+
			" DISTINCT TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY') START_DATE, "+
			//" TO_CHAR((A.ACTIVATED_DATE+B.PERIOD), 'fmddth') || ' ' || TO_CHAR((A.ACTIVATED_DATE+B.PERIOD), 'Month')|| TO_CHAR((A.ACTIVATED_DATE+(B.PERIOD-1)), 'YYYY') END_DATE , "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'fmddth')   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) || ' '  || "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'Month')    FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) ||          "+  
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'YYYY')     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"'))      ,        "+
			" B.PERIOD,B.NIBSM,B.RESIDUAL_VALUE "+
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
      " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
      " UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"')");
			}
			else if(m_repayment_interval.trim().equals("Weekly"))
				{
			rs=stmt.executeQuery (" SELECT "+
			" DISTINCT TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY') START_DATE, "+
			//" TO_CHAR((A.ACTIVATED_DATE+7*B.PERIOD), 'fmddth') || ' ' || TO_CHAR((A.ACTIVATED_DATE+7*B.PERIOD), 'Month')|| TO_CHAR((A.ACTIVATED_DATE+7*(B.PERIOD-1)), 'YYYY') END_DATE , "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'fmddth')   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) || ' '  || "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'Month')    FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) ||          "+  
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'YYYY')     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"'))      ,        "+
			" B.PERIOD,B.NIBSM,B.RESIDUAL_VALUE "+
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
      " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
      " A.APPLICATION_NO=UPPER('"+m_application_no+"')");
			}
			else
			{
			rs=stmt.executeQuery (" SELECT "+
			" DISTINCT TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY') START_DATE, "+
			//" TO_CHAR(ADD_MONTHS(TO_DATE(A.ACTIVATED_DATE,'DD-MM-YYYY'),((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'fmddth') || ' ' || TO_CHAR(ADD_MONTHS(TO_DATE(A.ACTIVATED_DATE,'DD-MM-YYYY'),((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'Month')|| TO_CHAR(ADD_MONTHS(A.ACTIVATED_DATE,((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'YYYY') END_DATE , "+
			//" TO_CHAR(SELECT MAX(RENTAL_DATE)   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'),'fmddth') || TO_CHAR(SELECT MAX(RENTAL_DATE)   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'),'Month') || ' ' || TO_CHAR(SELECT MAX(RENTAL_DATE)   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'),'YYYY')  END_DATE , "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'fmddth')   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) || ' '  || "+
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'Month')    FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"')) ||          "+  
			" (SELECT TO_CHAR(MAX(RENTAL_DATE),'YYYY')     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER('"+m_application_no+"'))      ,        "+

			" B.PERIOD,SUM(B.NIBSM),SUM(B.RESIDUAL_VALUE)  "+ //modified by nuwan de silva 02-07-07-------
      " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
      " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
			" A.APPLICATION_NO=UPPER('"+m_application_no+"')"+
			"  GROUP BY B.PERIOD, "+
      "  TO_CHAR(A.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(A.ACTIVATED_DATE, 'Month')|| TO_CHAR(A.ACTIVATED_DATE, 'YYYY'),  "+
      "  TO_CHAR(ADD_MONTHS(TO_DATE(A.ACTIVATED_DATE,'DD-MM-YYYY'),((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'fmddth') || ' ' || TO_CHAR(ADD_MONTHS(TO_DATE(A.ACTIVATED_DATE,'DD-MM-YYYY'),((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'Month')|| TO_CHAR(ADD_MONTHS(A.ACTIVATED_DATE,((B.PERIOD-1))*(DECODE(B.PAYMENT_INTERVAL,'12','1','1','12',B.PAYMENT_INTERVAL))),'YYYY')  ");
			}
			more = rs.next();
			if(more)
				{
		//	out.println("test9"+more);
			m_start_date=rs.getString(1);
			m_end_date=rs.getString(2);
			m_period=rs.getString(3);
			m_security_margin_val=rs.getDouble(4);
			m_residual_value=rs.getDouble(5);
			}
			//============================================
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
			//modified by nwuan de silva 17-07-07------------------------------
			rs=stmt.executeQuery (" SELECT "+
			//" TO_CHAR(RENTAL_DATE, 'fmddth') || ' ' ||   TO_CHAR(RENTAL_DATE, 'Month')||    TO_CHAR(RENTAL_DATE, 'YYYY') start_date ,TO_CHAR(RENTAL_DATE, 'fmddth') day"+
			 " TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' ||   TO_CHAR(ACTIVATED_DATE, 'Month')||    TO_CHAR(ACTIVATED_DATE, 'YYYY') start_date ,"+ //TO_CHAR(ACTIVATED_DATE, 'fmddth') day //comment by nuwan de silva on 22-10-07
			 " (SELECT TO_CHAR(MAX(RENTAL_DATE),'fmddth') FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE TO_NUMBER(INSTALLMENT_NO)=1 AND APPLICATION_NO=UPPER('"+m_application_no+"')) day "+ 
     // " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
		   " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
       " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
			//-----modified by 	:delanjali
			//-----date					:2007-06-05
			//"AND  "+
     	// " INSTALLMENT_NO=0 ");
			more = rs.next();
			if(more)
			{
		//	out.println("test10"+more);
			m_rental_start_date=rs.getString(1);
			m_rental_start_day=rs.getString(2);
			}
			
			
			//--Close the Result Set And Stateement--------			
			rs.close();
			stmt.close();
			//---------------------------------------------
			//--Create The Statement----------------------
			stmt = conn.createStatement ();
			//--------------------------------------------	
			
			rs=stmt.executeQuery (" SELECT "+
			" DECODE(PURPOSE,'P','Personal','B' ,'Business') TYPE_USE "+
		  " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS  "+
			" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  ");
			more = rs.next();
			if(more)
				{
		//	out.println("test11"+more);
			m_type_use=rs.getString(1);
			}
			String m_net="";
			
    rs_invoice=stmt_invoice.executeQuery (" SELECT "+
   // " E.TITLE, "+
   // " E.FIRST_NAME,  "+
   // " E.LAST_NAME,  "+
		" NULL, "+ 
		" UPPER("+m_schema_name+".af_co_get_vendor_name(E.VENDOR_CODE)), "+
    " NVL(UPPER(E.ADDRESS),' '),  "+
    " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(E.CITY_CODE),' ')  , "+ //modified by nuwan de silva on 11-10-07
    " '1'/*A.QTY*/ || ' '|| 'UNIT' QTY, "+//modified by nuwan de silva for refinement no 784 on 09-08-07
  //  " C.MAKE_DESC||D.MODEL_CODE||'-'||D.DESCRIPTION, "+
	 // " C.MAKE_DESC||D.MODEL_CODE||'-'||D.DESCRIPTION ||'-'|| F.DESCRIPTION DESCRIPTION, "+
		//" NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER("+m_schema_name+".AF_CO_GET_MODEL_DESC(B.MODEL_CODE)), ' ') ||'-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+ //MODIFIED BY NUWAN DE SILVA 16-07-07 //comment by nuwan de silva on 12-12-2007 at ofscl
		" NVL(UPPER(C.MAKE_DESC),' ' ) || '-'|| NVL(UPPER(D.DESCRIPTION),' ') ||'-'|| NVL(UPPER(F.DESCRIPTION),' ' ) DESCRIPTION, "+ //MODIFIED BY NUWAN DE SILVA 16-07-07   //added by nuwan de silva on 12-12-2007 at ofscl
    " B.YEAR_OF_MANUFACTURE, "+
    " NVL(B.ENGINE_NO,'-') , "+
    " NVL(B.CHASSIS_NO,'-'), "+
		" B.MODEL_CODE, "+
		" B.SUB_MODEL_CODE "+
    " FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
    " "+m_schema_name+".AF_CO_MAS_MAKE C,"+m_schema_name+".AF_CO_MAS_SUB_MODLE D,"+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E ,"+
		" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F "+
    " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
    " A.ACTIVE_STATUS='Y' AND "+
    " A.APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
    " A.ASSET_ID=B.ASSET_ID AND "+
    //" C.MAKE_CODE= "+
		" (C.MAKE_CODE ,F.ITEM_SUB_CAT ) IN "+ //MODIFIED NUWAN DE SILVA 06-07-07
    "   (SELECT  "+
    "  MAKE_CODE ,ITEM_SUB_CAT  "+
    "  FROM "+m_schema_name+".AF_CO_MAS_MODEL  "+
    "  WHERE   "+
    "  MODEL_CODE IN (  "+
    "  SELECT  "+
    "  MODEL_CODE  "+ 
    "  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+ 
    "  WHERE INVOICE_NO=B.INVOICE_NO "+
		//-----------------------------------------------------------------------------------
		//----MODIFIED BY :DELANJALI-------------------------------------------------------------------------------
		//----DATE				:2007-06-05-------------------------------------------------------------------------------

		"	 AND ACTIVE_STATUS='Y' "+
		//-----------------------------------------------------------------------------------

    "  )) AND "+
    "  D.SUB_CODE=B.SUB_MODEL_CODE AND "+
    "  UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
    "  UPPER(E.BRANCH)=UPPER(B.BRANCH_ID)  ");
		
	
		
		/*String sql_rent=" SELECT "+
    "  DISTINCT (INSTALLMENT_NO), "+
    "  NET_RENTAL_AMOUNT, "+
    "  (GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT, "+
    "  GRENTAL_AMOUNT, "+
    "  TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE, "+
		" (INSTALLMENT_NO+1 ) PERIOD "+
    "  FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
    "  WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
		"  ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
		*/
		
   String sql_rent="   SELECT  "+
   "   TO_NUMBER(INSTALLMENT_NO) , "+//1
   "   SUM(NET_RENTAL_AMOUNT),  "+//2
   "   SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
   "   SUM(GRENTAL_AMOUNT), "+//4
	 "   TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE, "+//5
	 "   (INSTALLMENT_NO+1 ) PERIOD "+//6
   "   FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
   "   WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
   "   GROUP BY TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+
   "   ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
		
		rs_rental=stmt_rental.executeQuery(sql_rent);
     more=rs_rental.next();
		//boolean more_rent =rs_rental2.next();
		// install=new int[rows];
		 data_item=new Vector();
		 data_item2=new Vector();
		 data_item3=new Vector();
		 data_item4=new Vector();
		 data_item5=new Vector();
		 data_item6=new Vector();
      while(more)
				{
    
         data_item.addElement(rs_rental.getString(1));//inst
				 data_item2.addElement(rs_rental.getString(2));//net
				 data_item3.addElement(rs_rental.getString(3));//vat
				 data_item4.addElement(rs_rental.getString(4));///gross
				 data_item5.addElement(rs_rental.getString(5));//date
				 data_item6.addElement(rs_rental.getString(6));//period
				 size=size+1;
         more=rs_rental.next();
     }
			
	Object a [] = new Object[size];
	Object b [] = new Object[size];
	Object c [] = new Object[size];
	Object d [] = new Object[size];
	Object e [] = new Object[size];
	Object range[] = new Object[size];
	Object period[] = new Object[size];
	
	int i=0;
	
	if(b_flag==1)
				{
	arr_size=data_item2.size() -1;//net
	}
	else
				{
	arr_size=data_item2.size();//net
	}
	
//int j=1;
  count=0;
	
	while(i<arr_size)
				{
	
	if(count==0){	//assign first rental values
	a[count]=data_item.firstElement(); //inst
	b[count]=data_item2.firstElement(); //net
	c[count]=data_item3.firstElement(); //vat
	d[count]=data_item4.firstElement();  //gross
	e[count]=data_item5.firstElement(); //date
	}
	if(!b[count].equals(data_item2.get(i))) //check the retal is different
		{
		range[count]=data_item5.get(i-1);
		//m_net=data_item2.get(i-1).toString();	
		  m_net=data_item2.get(i).toString();	

String sql_rent2=" SELECT "+		
" COUNT(NO),NET "+
" FROM(  "+
      
  "     SELECT  "+
  "    TO_NUMBER(INSTALLMENT_NO) NO , "+
  "     SUM(NET_RENTAL_AMOUNT) NET  "+
  "     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
  "     WHERE  APPLICATION_NO=UPPER('"+m_application_no+"') "+
  "     GROUP BY TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+
  "     ORDER BY TO_NUMBER(INSTALLMENT_NO)  "+
  " ) "+
  " WHERE NET=('"+m_net+"') "+
  " GROUP BY NET ";
		rs_rental2=stmt_rental2.executeQuery(sql_rent2);		
		//out.println(sql_rent2);
		boolean more_rent=rs_rental2.next();
		if(more_rent){
		period[count]=rs_rental2.getString(1);
		}
	  count=count+1;
		a[count]=data_item.get(i);
		b[count]=data_item2.get(i);
		c[count]=data_item3.get(i);
		d[count]=data_item4.get(i);
		e[count]=data_item5.get(i);
	//	out.println("data_item2.get(i)"+data_item2.get(i));
	//	out.println("b[count]"+b[count]);
	}
	i=i+1;
	}		
	
		//	out.println(count);

	if(count==0){
	range[count]=data_item5.get(arr_size-1);
	//period[count]=data_item6.get(arr_size-1);
		m_net=data_item2.get(arr_size-1).toString();	

String sql_rent2=" SELECT "+		
" COUNT(NO),NET "+
" FROM(  "+
      
  "     SELECT  "+
  "    TO_NUMBER(INSTALLMENT_NO) NO , "+
  "     SUM(NET_RENTAL_AMOUNT) NET  "+
  "     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
  "     WHERE  APPLICATION_NO=UPPER('"+m_application_no+"') "+
  "     GROUP BY TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+
  "     ORDER BY TO_NUMBER(INSTALLMENT_NO)  "+
  " ) "+
  " WHERE NET=('"+m_net+"') "+
  " GROUP BY NET ";
		rs_rental2=stmt_rental2.executeQuery(sql_rent2);		
		//out.println(sql_rent2);
		boolean more_rent=rs_rental2.next();
		if(more_rent){
		period[count]=rs_rental2.getString(1);
		}
		count=count+1;
	}
	
String Sql_Guarantor=" SELECT "+		
" B.CLIENT_TYPE, "+ //1
" DECODE(B.CLIENT_TYPE,'I',UPPER(B.TITLE) || '. ' || UPPER(B.FULL_NAME),'C',/*'MESS' || '. '  ||*/ UPPER(B.FULL_NAME)), "+ //2
" NVL(DECODE(B.CLIENT_TYPE,'I',UPPER(B.ADDRESS1),'C',UPPER(B.REGISTERED_ADDRESS1)),' '), "+//3
" NVL(DECODE(B.CLIENT_TYPE,'I',UPPER(B.ADDRESS2),'C',UPPER(B.REGISTERED_ADDRESS2)),' ') ,"+//4
" A.GUARANTOR_CODE "+
" FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
" WHERE  A.GUARANTOR_CODE=B.CLIENT_CODE AND UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND  A.ACTIVE_STATUS='Y'  "+
" ORDER BY A.GUAR_ID,B.CLIENT_TYPE, A.GUARANTOR_CODE ";


			out.println("<html><head>"); 
					out.println("<title>Lease schedule </title></head>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					
					out.println("<script>");
					out.println("function save_data(){");
					out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&client_type="+m_client_type+"&document_code="+m_document_code+"\";"); 
					out.println(" window.location.href=m_url;"); 
					out.println("m_table.innerHTML=\"\" ");
					out.println("window.print();");
		      out.println("}");
					
					out.println("function add_button(){");
					if (m_print.trim().equals("FALSE")) {
					out.println("m_table.innerHTML=\"\" ");
					}else{
					out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
					out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
					out.println("m_writedata+'</table>';");
					}
					out.println("}");
			    out.println("</script>");
				
					out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");//add_button()
					//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
				 out.println("<body bgcolor='white'><br>");
				 out.println("<form name='Form1'>");
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
			out.println("<font size=3><p style='text-align:left'>");					
			/*out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr><td width=\"100%\" class='rep-body1'><b></b></td></tr>");
		  out.println("</table>");*/
			out.println("</font></p></blockquote>");	
			out.println("<font size=3><p style='text-align:center'>");				
			out.println("<table border='0' width='100%' class='table' align='center'>"); 		
			out.println("<tr><td width='*%' class='rep-body1' align='center' style='{font-size: 10px;}'><B>VARIABLE LEASE SCHEDULE</td></tr>");
			out.println("</table>");
			out.println("</font></p></center>");
			
			if(m_asset_type.trim().equals("VEHICLE"))
				{
			
if(m_client_type.trim().equals("INDIVIDUAL"))
				{

					out.println("<font size=2><p style='text-align:justify' class='rep-body1'>");	
					
					//added by nuwan de silva on 01-09-07
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' align='right' style='{text-align:right;}'><b>1 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("  <tr> ");
					out.println("    <td colspan='6' class='rep-body1' style='{font-size: 9px;}' ><b><p>&nbsp;Master Lease Agreement No. &nbsp;"+m_master_lease+" </p></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='6' class='rep-body1' style='{font-size: 9px;}'><b>&nbsp;Schedule No :"+m_finance_no+" </td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' style='{font-size: 9px;}'><b>&nbsp;Date of the Schedule : "+m_agreement_date+" </td>");
					out.println(" </tr>");
					out.println(" <tr>");
				
					out.println("    <td colspan='3' valign='top' class='rep-body1'><strong>");
					
				  rs = stmt.executeQuery(Client_Data);
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					while(rs.next()){
								
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_b_cert_no=rs.getString(6);
					m_full_name1=rs.getString(2);			
					
					
			//MODIFIED BY NWUAN DE SILVA 12-07-07----------------------------------------
						if(!m_full_name.equals(" ") && !m_add1.equals(" ") ){
						//m_full_name=m_full_name+",";
						m_full_name=m_full_name;
						}if(!m_add1.equals(" ") && (!m_add2.equals(" ") || !m_city_name.equals(" ") )){
						m_add1=m_add1+",";
						}	if(!m_add2.equals(" ") && !m_city_name.equals(" ")){
						m_add2=m_add2+",";
						}
					
					if(rs.getString(1).equals("CLIENT")){
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}' ><B>1.</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NAME/ADDRESS/DESCRIPTION OF THE LESSEE</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					
					if(!m_add2.equals(" ")){ //---Added by Chandana on 03/08/2007---------//
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					}
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_client_type+"&nbsp;NIC NO : "+m_b_cert_no+" </b></td> ");
					//out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>&nbsp;NIC NO : "+m_b_cert_no+" </b></td> ");
	
					out.println("</tr>");
					out.println("</table>");
					}
					
					else if(rs.getString(1).equals("CO-APPLICANT")){

					out.println("<br>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>And</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					if(!m_add2.equals(" ")){ //---Added by Chandana on 03/08/2007---------//
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					}
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NIC NO/REG NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					}
				  out.println("</strong></td>");
					
					out.println("<td colspan='3' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}' ><B>2.</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>LOCATION OF THE PROPERTY</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>Place at Which equipment is to be delivered and kept.</td> ");
					out.println("</tr>");
					more = rs_location.next();		
					int i_location=1;
					String m_location_data="";
				
				  while(more){
					if(!rs_location.getString(1).equals(m_location_data)){
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}'><B>("+i_location+").</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>"+rs_location.getString(1)+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>"+rs_location.getString(2)+".</td> ");
					out.println("</tr>");
					m_location_data=rs_location.getString(1);
					i_location=i_location+1;
					}
					
					more = rs_location.next();		
					}
	        out.println("   </table>");
				  out.println("   <br>");
				  out.println("</strong></td>");
	
					out.println("  </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='3' valign='top' class='rep-body1' style='{font-size: 9px;}'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><B>3.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>ACCEPTANCE RECEIPT TO BE ISSUED WITHIN 7 WORKING DAYS OF DELIVERAY OF EQUIPMENT</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					
	        out.println("   <td colspan='3' valign='top' class='rep-body1' style='text-align:justify' >");				
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>4.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>("+m_rental+")</td> ");
					out.println("</tr>");
					
					String data="Commencing from <strong>"+m_start_date+"</strong> ending on payment of the total receivables "+
			            "calculated as per Article 2.2 place of payment shall be at principal place of business of Lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+".";
									

					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
          out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' >METHOD OF PAYMENT :CHEQUE/BANK STANDING ORDER/CASH</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");				
					out.println(" </tr>");
					
					
					out.println(" <tr>");
					out.println("<td colspan='3' rowspan='2' class='rep-body1' valign='top'>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><B>5.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>INITIAL TERM (TERMS OF LEASE)</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>Initial term (terms of lease) shall be <b>"+m_period+ " "+ m_no_of+"</b> subject to Article 2.2 Lease to commence from the date of Acceptance Receipt </td> ");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("</td>");
					
					
					out.println("    <td colspan='2' class='rep-body1' style='{font-size: 9px;}'>&nbsp;NO. OF "+ m_no_of+"</td>");
					out.println("    <td width='21%' class='rep-body1' style='{font-size: 9px;}'><strong>&nbsp;"+ m_rental+" RENTAL - given in item No 15 of the schedule (initial)</strong></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='2' class='rep-body1' style='{font-size: 9px;}'>initially <b>"+m_period+ " "+ m_no_of+"</b> subject to increase or decrease of the period</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
					out.println("<tr>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					data="Payment stream given in item 15 of the schedule will commence from <B>"+m_rental_start_date+"</B> with a <b>"+m_ami+"</b> months grace period. Rental payments will commence on the <B>"+m_rental_start_date+" </B>thereafter <B>"+m_rental_start_day+"</B> Day of each month";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><B>6.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>7.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>SECURITY MARGIN INTEREST BEARING</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					if(m_security_margin_val>0)
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+nf.format(m_security_margin_val)+"</td> ");
					}
					else
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>NIL</td> ");
					}
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>8.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>PURPOSE OF EQUIPMENT/S </td> "); //EQUIPMENT //"+m_asset_type_desc.toUpperCase()+"
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+m_type_use+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}'>");
					data="<strong>STIPULATED LOSS VALUE(For 12 months period calculated from date of commencement of Lease until total receivables are paid)</strong> "+
							 "after the expiry of the initial period the stipulated loss value shall be the amount payable at the expiry of the initial period";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>9.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					rs=stmt.executeQuery (Sql_stipulated);
					
					while(rs.next()){
					out.println("<tr> ");
          out.println(" <td class='rep-body1' valign='top'>&nbsp;</td> ");
          out.println(" <td class='rep-body1' valign='top'>Year"+rs.getInt(1)+"</td> ");
          out.println(" <td colspan='4' class='rep-body1' valign='top'>"+nf.format(rs.getDouble(2))+"</td> ");
          out.println(" </tr> ");
					}
					
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}' >");
					data="<strong>OVERDUE INTEREST :(Interest at "+nf.format(m_od_interest_rate)+"% per month on all overdues until payment of such rental and interest).</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>10.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}' >");
					data="<strong>EXCLUTION CLAUSE: it is agreed between the parties that the following clauses of the lease agreement shall be excluded.</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b>11.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' >"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b>CLAUSE NO. NIL</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					/*
					out.println("</table>");
					out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}' style='{font-size: 9px;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}' style='{font-size: 9px;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					
					//-----------------------------------------------------------------------------------------------------------------------------------------
					*/
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}'>");
					data="<strong>DESCRIPTION OF EQUIPMENT LEASED</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>12.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td class='rep-body1' style='{font-size: 9px;}'>SELLER/SUPPLIER<br>");
					out.println("NAME/ADDRESS</td>");
					out.println("   <td class='rep-body1'>QTY</td>");
					out.println("  <td class='rep-body1'>DESCRIPTION</td>");
					out.println("   <td width='10%' class='rep-body1'>YEAR OF MANUFACTUR</td>");
					out.println("   <td width='13%' class='rep-body1'>ENGINE NO. </td>");
					out.println("   <td class='rep-body1'>CHASSIS NO</td>");
					out.println(" </tr>");
			
					more = rs_invoice.next();
			    while(more){
					out.println("<tr>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(2)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(3)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(4)+"");
					out.println("<br>");
					out.println("</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;"+rs_invoice.getString(5)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(6)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(7)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(8)+"</td>");
					out.println("   <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(9)+"</td>");
					out.println("  </tr>");
					 more=rs_invoice.next();
			     }
						
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					
					data="<strong>DESCRIPTION OF PROPERTY PLEDGED SECURED/OR OTHER SECURITIES</strong>";
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>13.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					
					rs=stmt.executeQuery (Sql_Guarantor);
					more = rs.next();
					int gur_data_i=1;					
					int gur_data_c=1;		
					if(!more){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>NIL</td> ");
					 out.println("</tr>");
					}
			    while(more){
										
					 if(rs.getString(1).equals("I") ){
					 
					 if(gur_data_i==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Personal Guarantee of</td> ");
					 out.println("</tr>");
					 
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_i+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
					gur_data_i=gur_data_i+1;				
					 }
					 else if(rs.getString(1).equals("C") ){
					 
					 if(gur_data_c==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Corporate Guarantee of</td> ");
					 out.println("</tr>");
					 
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_c+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
					 /*out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(3)+"</td> ");
					 out.println("</tr>");
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(4)+"</td> ");
					 out.println("</tr>");
					 */	
					 gur_data_c=gur_data_c+1;				
					 }
									
					more=rs.next();
			    }
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>Whether the insurance premium is included</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>14.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><B>"+m_insurance+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>"+ m_rental+" RENTAL BREAKUP</strong> (subject to for above)";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>15.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td class='rep-body1'><div align='center'><strong>NET<br>");
					out.println(" Rs</strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>VAT<br>");
					out.println("  Rs </strong></div></td>");
					out.println("    <td class='rep-body1'><div align='center'><strong>GROSS<br>");
					out.println("  Rs </strong></div></td>");
					out.println("  </tr>");
					/*int j=0;
		
			    while(j<count){
			
					out.println("  <tr>");
					out.println("    <td colspan='2' class='rep-body1'><strong><b>"+Integer.parseInt(period[j].toString())+" "+ m_no_of+" <br>");
					out.println("("+e[j].toString()+" - "+range[j].toString()+") </strong></td>");
					out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(Double.parseDouble(b[j].toString()))+"</div></td>");
					out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(Double.parseDouble(c[j].toString()))+"</div></td>");
					out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(Double.parseDouble(d[j].toString()))+"</div></td>");
					out.println(" </tr>");
					j=j+1;
			    }
					
			
			if(b_flag==1){
			
			
			 rs=stmt.executeQuery (" SELECT "+
 			 "  NET_RENTAL_AMOUNT, "+
 			 "  (GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT, "+
 			 "  GRENTAL_AMOUNT, "+
 			 "  TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+
 			 "  FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
 			 "  WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
 			 "  AND INSTALLMENT_NO=("+m_period+") ");
	
			more=rs.next();
			
			if(more){
					
					out.println("  <tr>");
					out.println("   <td colspan='2' class='rep-body1'><strong>LAST MONTH ("+rs.getString(4)+") </strong></td>");
					out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(rs.getDouble(1))+"</div></td>");
					out.println("  <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(rs.getDouble(2))+"</div></td>");
					out.println("  <td class='rep-body1'><div align='right'><b>"+nf.format(rs.getDouble(3))+"</div></td>");
					out.println(" </tr>");
		   
				}
					
		}
		
		*/
		//================================================================================
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					boolean more3 =rs_rental.next();
					
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
									
								while(more3) //START INSTALLMENT LOOP
									{
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	out.println("  <tr>");
												if(start==end)
									       {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													else
										      {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													
													}
													
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
												
													out.println("</tr>"); 
													
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
													out.println("  <tr>");	
													if(start==end)
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													else
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													
													out.println("</tr>"); 
								
								} //END OF INSTALLMENT LOOP
		//=====================================================================================
			out.println("</table>");
			// --------------  Added by Mahela on 21-06-2007 ----------------------------------------------------------------------------------------------
			
			/*out.println(" <br><br>");
      out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
			out.println("   </table>");			
			out.println("   <p style=\"page-break-after:always\"></p>");		
			*/
			
			// --------------  Added by Nuwan on 01-08-2007 -----------------
			//-----------------------------------------------------------------------------
			out.println("   <p style=\"page-break-after:always\"></p>");		
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>2 of 2 </b></td></tr>");
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
			out.println("   </table>");			
			//out.println(" <br>");
					
			out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
			out.println("   <tr><td width='100%' class='rep-body1'>");
			out.println("   <blockquote><br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> having read Understood contains hereof</td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
			out.println("   </table>");			
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>The common seal of the within name</td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
			out.println("   </table>");			
			//COMMENT BY NUWAN DE SILVA ON 28-11-2007 AT OFSCL
			/*out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
			out.println("   </table>");			
			*/
			/*out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
			out.println("   </table>");
			*/
			
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
			//out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><P><b>Company Emboss</b><BR><b>Seal</b></P></td></tr>");
			out.println("   </table>");
			
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			
			//COMMENT BY NUWAN DE SILVA ON 28-11-2007 AT OFSCL

			/*out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
			out.println("   </table>");
			*/
			
			//out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>Who do hereby attest the sealing</td></tr>");
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>hereof</td></tr>");
			out.println("   </table>");
			//out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'>Lessees by</td><td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------Aadded by nuwan de silva on 01-09-07-------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td>");
			out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='25%' class='rep-body1' style='{text-align:left;}'>Date of Signing the schedule : </td><td width='37%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
			out.println("   <td width='37%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
			out.println("   </table>");
			//out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------Aadded by nuwan de silva on 01-09-07-------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------------------
			
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------Aadded by nuwan de silva on 01-09-07-------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='*%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> for and on behalf of "+m_orient_name+"</td></tr>"); //ORIENT FINANCIAL SERVICES CORPORATION LIMITED 
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Lessor By  </td><td width='42%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
			out.println("   <td width='42%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
			out.println("   </table>");
			/*
					
			out.println("   </blockquote>");
			out.println("   </td></tr>");
			out.println("</table>");
			out.println(" <br><br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
			out.println("   </table>");			
			out.println("   <p style=\"page-break-after:always\"></p>");		
			out.println("<table width='100%'  border='1' cellspacing='0' > ");
			out.println("   <tr><td width='100%' class='rep-body1'>");
			out.println("   <blockquote><br>");
			*/
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
			out.println("   </table>");
			//out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
			out.println("   </table>");  
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------Aadded by nuwan de silva on 01-09-07-------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------Aadded by nuwan de silva on 01-09-07-------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//----------------------
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			out.println("   <br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
			out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
			out.println("   </table>");
			//out.println("   <br>");
			out.println("   </blockquote>");
			out.println("   </td></tr>");
			out.println("</table>");
			//comment by nuwan de silva on 01-09-07
			/*out.println(" <br><br>");
			out.println("   <table border='0' width='100%' class='table'> ");	
			out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
			out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
			out.println("   </table>");			
			*/
			//out.println("   <p style=\"page-break-after:always\"></p>");		
			//-------------------------- End of Addition ------------------------------------------------------------------------------------------			
			out.println("</font></p></blockquote>");
			
			}
			
			
			else if(m_client_type.trim().equals("SOLEPROPRI"))
				{
				
				String m_reg_add1="";
				String m_reg_add2="";
				String m_reg_city_name="";
				String data="";
				
				  rs = stmt.executeQuery(" SELECT "+
        												" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS1),' '),'-',' '),'null',' '),"+
																" REPLACE(REPLACE(NVL(UPPER(REGISTERED_ADDRESS2),' '),'-',' '),'null',' '),"+
                                " UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(REGISTERED_CITY_CODE)) "+
																" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                " WHERE   CLIENT_CODE = "+
                                " (SELECT "+
		                            " CLIENT_CODE "+
                                " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
                                " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"'))");
    
			 more = rs.next();		
				
				if(more)
				{
				m_reg_add1=rs.getString(1);
				m_reg_add2=rs.getString(2);
				m_reg_city_name=rs.getString(3);
							
				}
				
				
				
				if(!m_reg_add1.equals(" "))
				{
				m_reg_add1=m_reg_add1+",";
				}
				if(!m_reg_add2.equals(" "))
				{
				m_reg_add2=m_reg_add2+",";
				}
				  out.println("<font size=2><p style='text-align:left' class='rep-body1'>");	
					//added by nuwan de silva on 01-09-07
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' align='right' style='{text-align:right;}'><b>1 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("  <tr> ");
					out.println("    <td colspan='7' class='rep-body1'><b><p>&nbsp;Master Lease Agreement No. &nbsp;"+m_master_lease+" </p></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='7' class='rep-body1'><b>&nbsp;Schedule No : "+m_finance_no+"</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><b>&nbsp;Date of the Schedule : "+m_agreement_date+"</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					
					data="<strong>NAME/ADDRESS/DESCRIPTION OF THE LESSEE</strong>";
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					rs = stmt.executeQuery(Client_Data);
					while(rs.next()){
								
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_b_cert_no=rs.getString(6);
					m_full_name1=rs.getString(2);			
					
						if(!m_full_name.equals("-"))
						{
						m_full_name=m_full_name+",";
						}
						
						if(!m_add1.equals("-"))
						{
						m_add1=m_add1+",";
						}
						if(!m_add2.equals("-"))
						{
						m_add2=m_add2+",";
						}
					
					if(rs.getString(1).equals("CLIENT")){
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>1.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");

					//data="<B>" +m_full_name+m_add1+m_add2+m_city_name+" PROPRIETOR OF "+m_reg_add1+m_reg_add2+m_reg_city_name+"</B>";
					data="<B> MR. TERREANCE ROHANA DELWITA SOLE PROPRIETOR OF " +m_full_name+m_add1+m_add2+m_city_name+"</B>";
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					
					data="SOLE PROPRIETORSHIP CERTIFICATE NO :"+m_b_cert_no;
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					}

					else if(rs.getString(1).equals("CO-APPLICANT")){
					
						if(!m_full_name.equals("-"))
						{
						m_full_name=rs.getString(2);
						}

					out.println("<br>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>And</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					//out.println("<tr>");
					//out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					//out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b><u>NAME/ADDRESS/DESCRIPTION OF THE CO-APPLICANT</u></b></td> ");
					//out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF </td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NIC NO/REG NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					}
					
					out.println("</td>");
					out.println("<td colspan='4' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>2.</td> ");
					out.println("<td width='*%' class='rep-body1'><b>LOCATION OF THE PROPERTY</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1'><b>Place at Which equipment is to be delivered and kept.</td> ");
					out.println("</tr>");
					
					more = rs_location.next();		
					int i_location=1;
					String m_location_data="";
					
					while(more){
					if(!rs_location.getString(1).equals(m_location_data)){
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>("+i_location+").</td> ");
					out.println("<td width='*%' class='rep-body1'><b>"+rs_location.getString(1)+"</td> ");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1'><b>"+rs_location.getString(2)+".</td> ");
					out.println("</tr>");
					m_location_data=rs_location.getString(1);
					i_location=i_location+1;
					}
					more = rs_location.next();		
					
					}
	        out.println("   </table>");
				  out.println("   <br>");
				  out.println("</strong></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='3' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><B>3.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>ACCEPTANCE RECEIPT TO BE ISSUED WITHIN 7 WORKING DAYS OF DELIVERAY OF EQUIPMENT</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
				  out.println("   <td colspan='4' valign='top' class='rep-body1' style='text-align:justify' >");				
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>4.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>("+m_rental+")</td> ");
					out.println("</tr>");
					
					data="Commencing from <strong>"+m_start_date+"</strong> ending <strong>"+m_end_date+"</strong> "+
			            "Place of payment shall be at principal place of business of lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+","+m_orient_city_name+".";

					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
          out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>METHOD OF PAYMENT :CHEQUE/BANK STANDING ORDER/CASH</td> ");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("</td>");				
					out.println(" <tr> ");
					out.println("   <td colspan='3'  rowspan='4' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><B>5.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>INITIAL TERM (TERMS OF LEASE)</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><B></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>Lease to commence from the date of Acceptance Receipt</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("  <td colspan='2' class='rep-body1'>NO. OF "+ m_no_of+"</td> ");
					out.println("  <td colspan='2' class='rep-body1'><strong>"+ m_rental+" RENTAL - given in item No 15 of the schedule</strong></td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td colspan='2' rowspan='3' class='rep-body1'>&nbsp;<b>"+m_period+ " "+ m_no_of+" </td> ");
					rs_rental=stmt_rental.executeQuery(sql_rent);
					more=rs_rental.next();
					if(more){
					out.println("  <td width='12%' class='rep-body1'>&nbsp;Amount Rs.</td> ");
					out.println("  <td width='10%' style='text-align:right' class='rep-body1' >&nbsp;"+nf.format(rs_rental.getDouble(2))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td class='rep-body1'>&nbsp;Plus VAT Rs.</td> ");
					out.println("   <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(3))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> "); 
					out.println("  <td class='rep-body1'>&nbsp;Total Rs.</td> "); 
					out.println("  <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(4))+"</td> ");
					out.println(" </tr> ");
					out.println(" </tr>");
					}
					out.println("<tr>");
					data="Payment of <b>Rs."+nf.format(rs_rental.getDouble(4))+"</b> covering the first and the last <b>"+m_ami+"</b> month/s rental payable on<B>"+m_rental_start_date+"</B> and thereafter <B>"+m_rental_start_day+"</B> day of each month after the expiry of grace period of <b>"+m_ami+"</b> month/s.";
					out.println("   <td colspan='3'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>6.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					data="<b>SECURITY MARGIN INTEREST BEARING</b>";
					out.println("   <td colspan='4'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>7.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					
					if(m_security_margin_val>0)
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+nf.format(m_security_margin_val)+"</td> ");
					}
					else
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>NIL</td> ");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					data="<b>PURPOSE OF EQUIPMENT/S </b>"; //EQUIPMENT //"+m_asset_type_desc.toUpperCase()+"
					out.println("   <td colspan='7'   valign='top' class='rep-body1'>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>8.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");

					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>"+m_type_use+"</td> ");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("</td>");
					
					out.println(" </tr>");
					
					out.println(" <tr>");
					data="<b>STIPULATED LOSS VALUE(For 12 months period calculated from date of commencement of Lease)</b>";
					out.println("   <td colspan='7'   valign='top' class='rep-body1'>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>9.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
				
					out.println("</table>");
					
					out.println("</td>");

					out.println(" </tr>");
					rs=stmt.executeQuery (Sql_stipulated);
					while(rs.next()){
					out.println("<tr> ");
          out.println(" <td class='rep-body1' valign='top'>&nbsp;</td> ");
          out.println(" <td class='rep-body1' valign='top'>Year"+rs.getInt(1)+"</td> ");
          out.println(" <td colspan='5' class='rep-body1' valign='top'>"+nf.format(rs.getDouble(2))+"</td> ");
          out.println(" </tr> ");
					}
					
					out.println(" <tr>");
				  data="<b>OVERDUE INTEREST :(Interest at "+nf.format(m_od_interest_rate)+"% per month on all overdues until payment of such rental and interest).</b>";
					out.println("   <td colspan='7'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>10.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("</td>");


					out.println(" </tr>");
					
					out.println(" <tr>");
					data="<b>EXCLUTION CLAUSE : it is agreed between the parties that the following clauses of the lease agreement shall be excluded.</b>";
					out.println("   <td colspan='7'   valign='top' class='rep-body1'>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>11.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>CLAUSE NO. NIL</td> ");
					out.println("</tr>");
				
					out.println("</table>");
					
					out.println("</td>");
					
					
					out.println(" </tr>");
					
					/*
					out.println("</table>");
					out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					*/
					//-----------------------------------------------------------------------------------------------------------------------------------------		
					//out.println(" <tr></tr><tr></tr><tr></tr>");
					
					//-----------------------------------------------------------------------------------------------------------------------------------------
					
					out.println("  <tr>");
					data="<b>DESCRIPTION OF EQUIPMENT LEASE</b>";
					out.println("   <td colspan='7'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>12.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td class='rep-body1'>SELLER/SUPPLIER<br>");
					out.println("NAME/ADDRESS</td>");
					out.println("   <td class='rep-body1'>QTY</td>");
					out.println("  <td class='rep-body1'>DESCRIPTION</td>");
					out.println("   <td width='15%' class='rep-body1'>YEAR OF MANUFACTUR</td>");
					out.println("   <td width='10%' class='rep-body1'>ENGINE NO. </td>");
					out.println("   <td colspan='2' class='rep-body1'>CHASSIS NO</td>");
					out.println(" </tr>");
					
					more = rs_invoice.next();
			    while(more){
					out.println("<tr>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(2)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(3)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(4)+"");
					out.println("<br>");
					out.println("</td>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;"+rs_invoice.getString(5)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(6)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(7)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(8)+"</td>");
					out.println("   <td colspan='2' class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(9)+"</td>");
					out.println("  </tr>");
					 more=rs_invoice.next();
			
			}
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>DESCRIPTION OF PROPERTY PLEDGED SECURED/OR OTHER SECURITIES</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>13.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					
						rs=stmt.executeQuery (Sql_Guarantor);
					more = rs.next();
					int gur_data_i=1;					
					int gur_data_c=1;	
					if(!more){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>NIL</td> ");
					 out.println("</tr>");
					}
			    while(more){
										
					 if(rs.getString(1).equals("I") ){
					 
					 if(gur_data_i==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Personal Guarantee of</td> ");
					 out.println("</tr>");
						
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_i+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
						gur_data_i=gur_data_i+1;										
					 }
						
					 else if(rs.getString(1).equals("C") ){
					 
					 if(gur_data_c==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Corporate Guarantee of</td> ");
					 out.println("</tr>");
						
					 }
					 
		       out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_c+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
					 /*out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(3)+"</td> ");
					 out.println("</tr>");
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(4)+"</td> ");
					 out.println("</tr>");
						*/
					 gur_data_c=gur_data_c+1;				
												
					 }
						
					
					
					more=rs.next();
					
			    }
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>Whether the insurance premium is included</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>14.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
				
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>"+m_insurance+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>"+ m_rental+" RENTAL BREAKUP</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>15.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td class='rep-body1'><div align='center'><strong>NET<br>");
					out.println(" Rs</strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>VAT<br>");
					out.println("  Rs </strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>GROSS<br>");
					out.println("  Rs </strong></div></td>");
					out.println("  </tr>");
		//================================================================================
	
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
									
									
								while(more3) //START INSTALLMENT LOOP
									{
													  									
									
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	out.println("  <tr>");
												if(start==end)
									       {
																			
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													
													else
										      {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													
													}
													
																								
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
												
													out.println("</tr>"); 
													
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
													out.println("  <tr>");	
													if(start==end)
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													else
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													
													out.println("</tr>"); 
								
								} //END OF INSTALLMENT LOOP
		
		       //=====================================================================================
					//-----------------------------------------------------------------------------------------------------------------------------------------
							
					out.println("</table>");
					
					/*out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println(" <br><br>");
					out.println("   <p style=\"page-break-after:always\"></p>");		
					*/
					
					// --------------  Added by Nuwan on 01-08-2007 ----------------------------------------------------------------------------------------------
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>2 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> having read Understood contains hereof</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>The common seal of the within name</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					
					//comment by nuwan de silva on 28-11-2007
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");			
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					//out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					
	
					//comment by nuwan de silva on 28-11-2007
					/*out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					//out.println("   <br>");
					
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>Who do hereby attest the sealing</td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>hereof</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'>Lessees by</td><td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
							//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='25%' class='rep-body1' style='{text-align:left;}'>Date of Signing the schedule : </td><td width='37%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='37%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='*%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> for and on behalf of "+m_orient_name+" </td></tr>"); //ORIENT FINANCIAL SERVICES CORPORATION LIMITED
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Lessor By  </td><td width='42%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='42%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					/*
					out.println("   </blockquote>");
					out.println("   </td></tr>");
					out.println("</table>");
					out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					*/
					
					out.println("   <br>");					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   </blockquote>");
					//out.println("   <br><br><br>");
					out.println("   </td></tr>");
					out.println("</table>");
					//comment by nuwan de silva on 01-09-07
					/*out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					*/
					//out.println("   <p style=\"page-break-after:always\"></p>");		
					//-------------------------- End of Addition ------------------------------------------------------------------------------------------			
		
			
			out.println("</font></p></blockquote>");
			
			
			}
			
			else if(m_client_type.trim().equals("CORPORATE") || m_client_type.trim().equals("PARTNERS") ) //MODIFED NUWAN DE SILVA 02-JUL-07
				{
			String data="";
					 String sql=" SELECT "+
         " UPPER(NAME) "+
         " FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DIRECTORS "+
         " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";
					
					rs_partner=stmt_partner.executeQuery (sql);
		int count_part=0;
		int x=0;
				
		while(rs_partner.next()){
		count_part=count_part+1;
		}
		
		m_partner_name = new String[count_part];
		
		rs_partner=stmt_partner.executeQuery (sql);
		rs_partner.next();
		while(x<count_part){
		m_partner_name[x]=rs_partner.getString(1);
		x=x+1;
		rs_partner.next();
		}
		
	   
		rs_partner=stmt_partner.executeQuery (sql);
		
			
		String m_contact_people="";
		int m_pos=count_part-1;
		int j_part=1;
		x=0;
		while(x<count_part){
					
		if(j_part==m_pos)		{
		m_contact_people=m_contact_people+m_partner_name[x]+" and"+" ";
		}
		else if(x==m_pos){
		m_contact_people=m_contact_people+m_partner_name[x];
		}
		
		else{
		m_contact_people=m_contact_people+m_partner_name[x]+","+" ";
		}
		
		x=x+1;
		j_part=j_part+1;
		}
		
		//String m_details=m_add1
									
			out.println("<font size=2><p style='text-align:left' class='rep-body1'>");	
					//added by nuwan de silva on 01-09-07
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' align='right' style='{text-align:right;}'><b>1 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
				//	out.println(" <br>");
						
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("  <tr> ");
					out.println("    <td colspan='6' class='rep-body1'><b><p>&nbsp;Master Lease Agreement No. &nbsp;"+m_master_lease+" </p></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='6' class='rep-body1'><b>&nbsp;Schedule No : "+m_finance_no+"</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1'><b>&nbsp;Date of the Schedule :"+m_agreement_date+" </td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					
					data="<strong>NAME/ADDRESS/DESCRIPTION OF THE LESSEE</strong>";
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					rs = stmt.executeQuery(Client_Data);
					
					while(rs.next()){
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_b_cert_no=rs.getString(6);
					m_full_name1=rs.getString(2);			
					
						if(!m_full_name.equals("-"))
						{
						m_full_name=m_full_name+",";
						}
						
						if(!m_add1.equals("-"))
						{
						m_add1=m_add1+",";
						}
						if(!m_add2.equals("-"))
						{
						m_add2=m_add2+",";
						}
						
					if(rs.getString(1).equals("CLIENT")){
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>1.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
			    data="<b>"+m_contact_people+" CARRING ON BUSINESS IN PARTNERSHIP UNDER THE NAME STYLE AND FIRM OF " +m_full_name+" "+m_add1+" "+m_add2+" "+m_city_name+"</b> ";
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					data="PARTNERSHIP CERTIFICATE NO :"+m_b_cert_no;
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
				  }
						
					else if(rs.getString(1).equals("CO-APPLICANT")){

           if(!m_full_name.equals("-"))
						{
						m_full_name=rs.getString(2);
						}
					out.println("<br>");
					//added by nuwan de silva for the refinement no :848
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>And</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					//out.println("<tr>");
					//out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					//out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b><u>NAME/ADDRESS/DESCRIPTION OF THE CO-APPLICANT</u></b></td> ");
					//out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NIC NO/REG NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					
					}
					
					out.println("</td>");
					out.println("<td colspan='3' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>2.</td> ");
					out.println("<td width='*%' class='rep-body1'><b>LOCATION OF THE PROPERTY</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1'><b>Place at Which equipment is to be delivered and kept.</td> ");
					out.println("</tr>");
					
					more = rs_location.next();		
					int i_location=1;
					String m_location_data="";
					
					while(more){
					if(!rs_location.getString(1).equals(m_location_data)){
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>("+i_location+").</td> ");
					out.println("<td width='*%' class='rep-body1'><b>"+rs_location.getString(1)+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1'><b>"+rs_location.getString(2)+".</td> ");
					out.println("</tr>");
					m_location_data=rs_location.getString(1);
					i_location=i_location+1;
					}
					more = rs_location.next();		
					
					}
					
	        out.println("   </table>");
				  out.println("   <br>");
				  out.println("</strong></td>");
	
					out.println("  </tr>");
					out.println("  <tr>");
					
					out.println("   <td colspan='3' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><B>3.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>ACCEPTANCE RECEIPT TO BE ISSUED WITHIN 7 WORKING DAYS OF DELIVERAY OF EQUIPMENT</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					
					out.println("   <td colspan='3' valign='top' class='rep-body1' style='text-align:justify' >");				
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>4.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>("+m_rental+")</td> ");
					out.println("</tr>");
					
					/*data="Commencing from <strong>"+m_start_date+"</strong> ending <strong>"+m_end_date+"</strong> "+
			            "Place of payment shall be at principal place of business of lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+","+m_orient_city_name+".";
          */
					 data="Commencing from <strong>"+m_start_date+"</strong> ending on payment of the total receivables "+
			            "calculated as per Article 2.2 place of payment shall be at principal place of business of Lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+".";
					
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
          out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>METHOD OF PAYMENT :CHEQUE/BANK STANDING ORDER/CASH</td> ");
					out.println("</tr>");
					
					out.println("</table>");
					
					out.println("</td>");				
					
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td colspan='3' rowspan='2' class='rep-body1' valign='top'><strong>&nbsp;5. INITIAL TERM (TERMS OF LEASE)</strong><br>");
					out.println("     <br>");
					out.println("&nbsp;Lease to commence from the date of Acceptance Receipt </td>");
					out.println("    <td colspan='2' class='rep-body1'>&nbsp;NO. OF "+ m_no_of+"</td>");
					out.println("    <td width='21%' class='rep-body1'><strong>&nbsp;"+ m_rental+" RENTAL - given in item No 15 of the schedule</strong></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='2' class='rep-body1'>&nbsp;<b>initially "+m_period+ " "+ m_no_of+" subject to increase or decrease of the period</td>");
					out.println("   <td>&nbsp;</td>");
					out.println(" </tr>");
					out.println("<tr>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					data="Payment stream given in item 15 of the schedule will commence from <B>"+m_rental_start_date+"</B> with a <b>"+m_ami+"</b> months grace period.Rental payments will commence on the <B>"+m_rental_start_date+" </B>thereafter <B>"+m_rental_start_day+"</B> day of each month";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><B>6.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					
					
					data="<b>SECURITY MARGIN INTEREST BEARING</b>";
					out.println("   <td colspan='3'   valign='top' class='rep-body1' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>7.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					if(m_security_margin_val>0)
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+nf.format(m_security_margin_val)+"</td> ");
					}
					else
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>NIL</td> ");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					
					out.println("  <tr>");
					data="<b>PURPOSE OF EQUIPMENT/S </b>";//EQUIPMENT //"+m_asset_type_desc.toUpperCase()+"
					out.println("   <td colspan='6'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>8.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><b>"+m_type_use+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1'><strong>9. STIPULATED LOSS VALUE(For 12 months period calculated from date of commencement of Lease until total receivables are paid) after the expiry of the initial  period the stipulated loss value shall be the amount payable at the expiry of the initial period</strong></td>");
					out.println(" </tr>");
					
				  rs=stmt.executeQuery (Sql_stipulated);
					
					while(rs.next()){
					
					out.println("<tr> ");
          out.println(" <td class='rep-body1' valign='top'>&nbsp;</td> ");
          out.println(" <td class='rep-body1' valign='top'>Year"+rs.getInt(1)+"</td> ");
          out.println(" <td colspan='4' class='rep-body1' valign='top'>"+nf.format(rs.getDouble(2))+"</td> ");
          out.println(" </tr> ");
					}
						
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1'><strong>10. OVERDUE INTEREST :(Interest at "+nf.format(m_od_interest_rate)+"% per month on all overdues until payment of such rental and interest).</strong></td>");
					out.println(" </tr>");
										
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}' >");
					data="<strong>EXCLUTION CLAUSE : it is agreed between the parties that the following clauses of the lease agreement shall be excluded.</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b>11.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' >"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b></td> ");//CLAUSE NO. NIL
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					/*
					out.println("</table>");
					out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					*/
					//-----------------------------------------------------------------------------------------------------------------------------------------		
							
					/*
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println(" <tr></tr><tr></tr><tr></tr>");
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   </td>");
					out.println(" </tr>");
					out.println(" <br>");
					//-----------------------------------------------------------------------------------------------------------------------------------------
					*/
					
						out.println("  <tr>");
					data="<b>DESCRIPTION OF EQUIPMENT LEASE</b>";
					out.println("   <td colspan='6'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>12.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td class='rep-body1'>SELLER/SUPPLIER<br>");
					out.println("NAME/ADDRESS</td>");
					out.println("   <td class='rep-body1'>QTY</td>");
					out.println("  <td class='rep-body1'>DESCRIPTION</td>");
					out.println("   <td width='15%' class='rep-body1'>YEAR OF MANUFACTUR</td>");
					out.println("   <td width='10%' class='rep-body1'>ENGINE NO. </td>");
					out.println("   <td colspan='2' class='rep-body1'>CHASSIS NO</td>");
					out.println(" </tr>");
					more = rs_invoice.next();
			    while(more){
					out.println("<tr>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B> "+rs_invoice.getString(2)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(3)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(4)+"");
					out.println("<br>");
					out.println("</td>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;"+rs_invoice.getString(5)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(6)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(7)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(8)+"</td>");
					out.println("   <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(9)+"</td>");
					out.println("  </tr>");
					 more=rs_invoice.next();
			}
					
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>DESCRIPTION OF PROPERTY PLEDGED SECURED/OR OTHER SECURITIES</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>13.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					
					rs=stmt.executeQuery (Sql_Guarantor);
					more = rs.next();
					int gur_data_i=1;					
					int gur_data_c=1;		
					if(!more){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>NIL</td> ");
					 out.println("</tr>");
					}
			    while(more){
										
					 if(rs.getString(1).equals("I") ){
					 
					 if(gur_data_i==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Personal Guarantee of</td> ");
					 out.println("</tr>");
						
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_i+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
						gur_data_i=gur_data_i+1;				
					 }
					 else if(rs.getString(1).equals("C") ){
					 if(gur_data_c==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Corporate Guarantee of</td> ");
					 out.println("</tr>");
					 }
		       out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_c+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
					 /*out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(3)+"</td> ");
					 out.println("</tr>");
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(4)+"</td> ");
					 out.println("</tr>");
						*/
						
						gur_data_c=gur_data_c+1;				

					 }
					
					
					more=rs.next();
			    }
	        out.println("</table>");
				  out.println("</td>");
					out.println(" </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>Whether the insurance premium is included</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>14.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'><B>"+m_insurance+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");

					
				
					
					out.println("  <tr>");
					out.println("    <td colspan='6' class='rep-body1'><strong>15. "+ m_rental+" RENTAL BREAKUP</strong></td>");
					out.println("  </tr>");
					
					out.println("  <tr>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td class='rep-body1'><div align='center'><strong>NET<br>");
					out.println(" Rs</strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>VAT<br>");
					out.println("  Rs </strong></div></td>");
					out.println("    <td class='rep-body1'><div align='center'><strong>GROSS<br>");
					out.println("  Rs </strong></div></td>");
					out.println("  </tr>");
					
		
		//================================================================================
	
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
									
									
								while(more3) //START INSTALLMENT LOOP
									{
													  									
									
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	out.println("  <tr>");
												if(start==end)
									       {
																			
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													
													else
										      {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													
													}
													
																								
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
												
													out.println("</tr>"); 
													
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
													out.println("  <tr>");	
													if(start==end)
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													else
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													
													out.println("</tr>"); 
								
								} //END OF INSTALLMENT LOOP
		
		
					out.println("</table>");
					// --------------  Added by Mahela on 21-06-2007 ----------------------------------------------------------------------------------------------
					/*out.println(" <br><br>");
		      out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					*/
					
					out.println("   <p style=\"page-break-after:always\"></p>");		
					//Added by Nuwan on 01-09-2007 ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>2 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> having read Understood contains hereof</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>The common seal of the within name</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");		
					//COMMENT BY NUWAN DE SILVA ON 28-11-2007 AT OFSCL
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");			
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					//out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");

										
					/*out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					
					
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>Who do hereby attest the sealing</td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>hereof</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'>Lessees by</td><td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
							//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='25%' class='rep-body1' style='{text-align:left;}'>Date of Signing the schedule : </td><td width='37%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='37%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='*%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> for and on behalf of "+m_orient_name+"</td></tr>"); //ORIENT FINANCIAL SERVICES CORPORATION LIMITED 
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Lessor By  </td><td width='42%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='42%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   </blockquote>");
					//out.println("   <br><br><br>");
					out.println("   </td></tr>");
					out.println("</table>");
					//comment by nuwan de silva on 01-09-07
					/*out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					*/
					//out.println("   <p style=\"page-break-after:always\"></p>");		
					//-------------------------- End of Addition ------------------------------------------------------------------------------------------			
			
			
						
			out.println("</font></p></blockquote>");			
			}
			
			else if(m_client_type.trim().equals("LIMITED"))
				{
				//added by nuwan de silva on 01-09-07
					out.println("<font size=2><p style='text-align:left' class='rep-body1'>");	
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' align='right' style='{text-align:right;}'><b>1 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("  <tr> ");
					out.println("    <td colspan='7' class='rep-body1'><b><p>&nbsp;Master Lease Agreement No. &nbsp;"+m_master_lease+" </p></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='7' class='rep-body1'><b>&nbsp;Schedule No : "+m_finance_no+"</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><b>&nbsp;Date of the Schedule : "+m_agreement_date+" </td>");
					out.println(" </tr>");
					out.println(" <tr>");
					
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					rs = stmt.executeQuery(Client_Data);
					while(rs.next()){
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_b_cert_no=rs.getString(6);
					m_full_name1=rs.getString(2);			
					
						if(!m_full_name.equals("-"))
						{
						//m_full_name=m_full_name+",";
						m_full_name=m_full_name;
						}
						if(!m_add1.equals("-"))
						{
						m_add1=m_add1+",";
						}
						if(!m_add2.equals("-"))
						{
						m_add2=m_add2+",";
						}
					if(rs.getString(1).equals("CLIENT")){	
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>1.</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NAME/ADDRESS/DESCRIPTION OF THE LESSEE</b></td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>LIMITED LIABILITY COMPANY REGISTRATION NO : "+m_b_cert_no+" </td> "); 
					out.println("</tr>");
					out.println("</table>");
					}
					else if(rs.getString(1).equals("CO-APPLICANT")){
					out.println("<br>");
					//added by nuwan de silva for the refinement no :848
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>And</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					//out.println("<tr>");
					//out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					//out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b><u>NAME/ADDRESS/DESCRIPTION OF THE CO-APPLICANT</u></b></td> ");
					//out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NIC NO/REG NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					
					}
					out.println("</td>");
					
					out.println("<td colspan='4' valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}' ><B>2.</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>LOCATION OF THE PROPERTY</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>Place at Which equipment is to be delivered and kept.</td> ");
					out.println("</tr>");
					more = rs_location.next();		
					int i_location=1;
					String m_location_data="";
				
				  while(more){
					if(!rs_location.getString(1).equals(m_location_data)){
					out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}'><B>("+i_location+").</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>"+rs_location.getString(1)+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' style='{font-size: 9px;}'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}'><b>"+rs_location.getString(2)+".</td> ");
					out.println("</tr>");
					m_location_data=rs_location.getString(1);
					i_location=i_location+1;
					}
					
					more = rs_location.next();		
					}
	        out.println("   </table>");
				  out.println("   <br>");
				  out.println("</strong></td>");
					out.println("  </tr>");
					
					
					out.println("  <tr>");
					out.println("   <td colspan='3' valign='top' class='rep-body1' style='{font-size: 9px;}'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><B>3.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>ACCEPTANCE RECEIPT TO BE ISSUED WITHIN 7 WORKING DAYS OF DELIVERAY OF EQUIPMENT</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					
	        out.println("   <td colspan='4' valign='top' class='rep-body1' style='text-align:justify' >");				
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>4.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>("+m_rental+")</td> ");
					out.println("</tr>");
					
					/*String data="Commencing from <strong>"+m_start_date+"</strong> ending <strong>"+m_end_date+"</strong> "+
			            "Place of payment shall be at principal place of business of lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+","+m_orient_city_name+".";
					*/				
					String data="Commencing from <strong>"+m_start_date+"</strong> ending on payment of the total receivables "+
			            "calculated as per Article 2.2 place of payment shall be at principal place of business of Lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+".";
									
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'>"+data+"</td> ");
					out.println("</tr>");
          out.println("<tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' >METHOD OF PAYMENT :CHEQUE/BANK STANDING ORDER/CASH</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");				
					out.println(" </tr>");
					
					out.println(" <tr> ");
					out.println("  <td colspan='3' rowspan='4' valign='top'><strong>5. INITIAL TERM (TERMS OF LEASE)</strong><br> ");
					out.println("   <br> ");
					out.println("Initial term (terms of lease)shall be "+m_period+ " "+ m_no_of+" subject to Article 2.2 Lease to commence from the date of Acceptance Receipt </td> "); 
					
					
					out.println("  <td colspan='2'>NO. OF "+ m_no_of+"</td> ");
					out.println("  <td colspan='2'><strong>"+ m_rental+" RENTAL - given in item No 15 of the schedule (initial)</strong></td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td colspan='2' rowspan='3' >&nbsp;<b>initial "+m_period+ " "+ m_no_of+" subject to increase or decrease of the period</td> ");
					
					rs_rental=stmt_rental.executeQuery(sql_rent);
					
					more=rs_rental.next();
					
					if(more){
					out.println("  <td width='12%' class='rep-body1'>&nbsp;Amount Rs.</td> ");
					out.println("  <td width='10%' style='text-align:right' class='rep-body1' >&nbsp;"+nf.format(rs_rental.getDouble(2))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td class='rep-body1'>&nbsp;Plus VAT Rs.</td> ");
					out.println("   <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(3))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> "); 
					out.println("  <td class='rep-body1'>&nbsp;Total Rs.</td> "); 
					out.println("  <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(4))+"</td> ");
					out.println(" </tr> ");
					
					out.println(" </tr>");
					}
					out.println("<tr>");
				  data="Payment of <b>Rs."+nf.format(rs_rental.getDouble(4))+"</b> covering the first and the last <b>"+m_ami+"</b> month/s rental payable on<B>"+m_rental_start_date+"</B> and thereafter <B>"+m_rental_start_day+"</B> day of each month after the expiry of grace period of <b>"+m_ami+"</b> month/s.";
					out.println("   <td colspan='3'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>6.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					data="<b>SECURITY MARGIN INTEREST BEARING</b>";
					out.println("<td colspan='4'   valign='top' class='rep-body1'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>7.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					if(m_security_margin_val>0)
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+nf.format(m_security_margin_val)+"</td> ");
					}
					else
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>NIL</td> ");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>8.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>PURPOSE OF EQUIPMENT/S </td> "); //EQUIPMENT //"+m_asset_type_desc.toUpperCase()+"
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+m_type_use+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>9. STIPULATED LOSS VALUE(For 12 months period calculated from date of commencement of Lease until total receivables are paid) after the expiry of the initial  period the stipulated loss value shall be the amount payable at the expiry of the initial period</strong></td>");
					out.println(" </tr>");
					
					/*out.println("  <tr>");
					out.println("  <td width='18%' class='rep-body1' rowspan='2'>&nbsp;</td>");
					out.println("  <td width='14%' class='rep-body1'>&nbsp;</td>");
					out.println("  <td width='22%' class='rep-body1'>&nbsp;</td>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td colspan='2' >&nbsp;</td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td >&nbsp;</td>");
					out.println("   <td > &nbsp;</td>");
					out.println("   <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("   <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println(" </tr>");
					*/
					
					
					rs=stmt.executeQuery (Sql_stipulated);
					
					while(rs.next()){
					out.println("<tr> ");
          out.println(" <td class='rep-body1' valign='top'>&nbsp;</td> ");
          out.println(" <td class='rep-body1' valign='top'>Year"+rs.getInt(1)+"</td> ");
          out.println(" <td colspan='5' class='rep-body1' valign='top'>"+nf.format(rs.getDouble(2))+"</td> ");
          out.println(" </tr> ");
					}
						
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>10. OVERDUE INTEREST :(Interest at "+nf.format(m_od_interest_rate)+"% per month on all overdues until payment of such rental and interest).</strong></td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify' style='{font-size: 9px;}' >");
					data="<strong>EXCLUTION CLAUSE : it is agreed between the parties that the following clauses of the lease agreement shall be excluded.</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b>11.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' >"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}' ><b></td> ");//CLAUSE NO. NIL
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					
					/*
					out.println("</table>");
					out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					*/
					//-----------------------------------------------------------------------------------------------------------------------------------------		
					
					
					/*
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   </td>");
					out.println(" </tr>");
					out.println(" <br>");
					//-----------------------------------------------------------------------------------------------------------------------------------------
					*/
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>12. DESCRIPTION OF EQUIPMENT LEASED</strong></td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td class='rep-body1'>SELLER/SUPPLIER<br>");
					out.println("NAME/ADDRESS</td>");
					out.println("   <td class='rep-body1'>QTY</td>");
					out.println("  <td class='rep-body1'>DESCRIPTION</td>");
					out.println("   <td width='15%' class='rep-body1'>YEAR OF MANUFACTUR</td>");
					out.println("   <td width='10%' class='rep-body1'>ENGINE NO. </td>");
					out.println("   <td colspan='2' class='rep-body1'>CHASSIS NO</td>");
					out.println(" </tr>");
					more = rs_invoice.next();
			    while(more){
					out.println("<tr>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B> "+rs_invoice.getString(2)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(3)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(4)+"");
					out.println("<br>");
					out.println("</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;"+rs_invoice.getString(5)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(6)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(7)+"</td>");
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(8)+"</td>");
					out.println("   <td colspan='2' class='rep-body1' valign='top'>&nbsp;<B>"+rs_invoice.getString(9)+"</td>");
					out.println("  </tr>");
					 more=rs_invoice.next();
			}
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>DESCRIPTION OF PROPERTY PLEDGED SECURED/OR OTHER SECURITIES</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>13.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
						rs=stmt.executeQuery (Sql_Guarantor);
					more = rs.next();
					int gur_data_i=1;					
					int gur_data_c=1;		
					if(!more){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>NIL</td> ");
					 out.println("</tr>");
					}
			    while(more){
					 if(rs.getString(1).equals("I") ){
					 if(gur_data_i==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Personal Guarantee of</td> ");
					 out.println("</tr>");
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_i+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
  					gur_data_i=gur_data_i+1;				

					 }
					 else if(rs.getString(1).equals("C") ){
					 if(gur_data_c==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Corporate Guarantee of</td> ");
					 out.println("</tr>");
	
					 }
		       out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_c+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
					/* out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(3)+"</td> ");
					 out.println("</tr>");
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(4)+"</td> ");
					 out.println("</tr>");
					*/
					gur_data_c=gur_data_c+1;				
					 }
					
					
					more=rs.next();
			    }
          out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>14. Whether the insurance premium is included</strong><br>");
					out.println("     <br>");
					out.println("     <strong><B>"+m_insurance+"</strong></td>");
					out.println(" </tr>");				
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>"+ m_rental+" RENTAL BREAKUP (subject to for above)</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>15.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td class='rep-body1'><div align='center'><strong>NET<br>");
					out.println(" Rs</strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>VAT<br>");
					out.println("  Rs </strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>GROSS<br>");
					out.println("  Rs </strong></div></td>");
					out.println("  </tr>");
		//================================================================================
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					boolean more3 =rs_rental.next();
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
								while(more3) //START INSTALLMENT LOOP
									{
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	out.println("  <tr>");
												if(start==end)
									       {
																			
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													
													else
										      {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													
													}
													
																								
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
												
													out.println("</tr>"); 
													
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
													out.println("  <tr>");	
													if(start==end)
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													else
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													
													out.println("</tr>"); 
								
								} //END OF INSTALLMENT LOOP
		
		      //=====================================================================================
					out.println("</table>");
					// --------------  Added by Mahela on 21-06-2007 ----------------------------------------------------------------------------------------------
					/*out.println(" <br><br>");
		      out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					*/
					
					// --------------  Added by Nuwan on 01-08-2007 ----------------------------------------------------------------------------------------------
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>2 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> having read Understood contains hereof</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>The common seal of the within name</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					//COMMENT BY NUWAN DE SILVA ON 28-11-2007 AT OFSCL
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");			
					*/
					
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");
		
		
					/*out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
				 */	
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>Who do hereby attest the sealing</td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>hereof</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'>Lessees by</td><td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
							//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='25%' class='rep-body1' style='{text-align:left;}'>Date of Signing the schedule : </td><td width='37%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='37%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='*%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> for and on behalf of "+m_orient_name+"</td></tr>"); //ORIENT FINANCIAL SERVICES CORPORATION LIMITED 
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Lessor By  </td><td width='42%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='42%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
					/*
					out.println("   </blockquote>");
					out.println("   </td></tr>");
					out.println("</table>");
					out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					*/
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   </blockquote>");
					//out.println("   <br><br><br>");
					out.println("   </td></tr>");
					out.println("</table>");
					//comment by nuwan de silva on 01-08-07------------------------
					/*out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					*/
					
					//out.println("   <p style=\"page-break-after:always\"></p>");		
					//-------------------------- End of Addition ------------------------------------------------------------------------------------------			
						
			out.println("</font></p>");
			
			
			}
			
			}
			else if(m_asset_type.trim().equals("EQUIPMENT"))
				{
					out.println("<font size=2><p style='text-align:left' class='rep-body1'>");	
					//added by nuwan de silva on 01-09-07
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' align='right' style='{text-align:right;}'><b>1 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");		
					//out.println(" <br>");
					
					out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("  <tr> ");
					out.println("    <td colspan='7' class='rep-body1'><b><p>&nbsp;Master Lease Agreement No. &nbsp;"+m_master_lease+" </p></td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("    <td colspan='7' class='rep-body1'><b>&nbsp;Schedule No : "+m_finance_no+" </td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><b>&nbsp;Date of the Schedule "+m_agreement_date+"</td>");
					out.println(" </tr>");
					
					out.println(" <tr>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top' style='text-align:justify'>");
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					rs = stmt.executeQuery(Client_Data);
					while(rs.next()){
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_b_cert_no=rs.getString(6);
					m_full_name1=rs.getString(2);			
					
						if(!m_full_name.equals("-"))
						{
						//m_full_name=m_full_name+",";
						m_full_name=m_full_name;
						}
						if(!m_add1.equals("-"))
						{
						m_add1=m_add1+",";
						}
						if(!m_add2.equals("-"))
						{
						m_add2=m_add2+",";
						}
					if(rs.getString(1).equals("CLIENT")){	
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'><B>1.</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NAME/ADDRESS/DESCRIPTION OF THE LESSEE</b></td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>LIMITED LIABILITY COMPANY REGISTRATION NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					else if(rs.getString(1).equals("CO-APPLICANT")){
					out.println("<br>");
					//added by nuwan de silva for the refinement no :848
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>And</td> ");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					//out.println("<tr>");
					//out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					//out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b><u>NAME/ADDRESS/DESCRIPTION OF THE CO-APPLICANT</u></b></td> ");
					//out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_full_name+" OF</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add1+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_add2+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>"+m_city_name+"</td> ");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1'>&nbsp;</td> ");
					out.println("<td width='*%' class='rep-body1' style='{font-size: 9px;}' ><b>NIC NO/REG NO : "+m_b_cert_no+" </td> ");
					out.println("</tr>");
					out.println("</table>");
					}
					
					}

					out.println("</strong></td>");
					out.println("    <td colspan='4' valign='top' class='rep-body1'><strong>&nbsp;2. LOCATION OF THE PROPERTY ");
					out.println("       <br><br>");
					out.println("&nbsp;Place at Which equipment is to be delivered and &nbsp;kept.");
					
					more = rs_location.next();		
					int i_location=1;
					String m_location_data="";
					while(more){
					if(!rs_location.getString(1).equals(m_location_data)){
					out.println("   <br><br>");
				  out.println("  &nbsp;("+i_location+").&nbsp;"+rs_location.getString(1)+"");
					out.println("   <br><br>");
				  out.println("  &nbsp;"+rs_location.getString(2)+".");
					m_location_data=rs_location.getString(1);
					i_location=i_location+1;
					}
					more = rs_location.next();		
					
					}
				  out.println("   <br>");
				  out.println("</strong></td>");
	
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='3' valign='top' class='rep-body1'><strong>&nbsp;3. ACCEPTANCE RECEIPT TO BE ISSUED WITHIN 7 WORKING DAYS OF DELIVERAY OF EQUIPMENT </strong></td>");
					out.println("   <td colspan='4' valign='top' class='rep-body1'><strong>&nbsp;4. ("+m_rental+")</strong><br>");
					
					
					out.println(     "<br>&nbsp;Commencing from <strong>"+m_start_date+"</strong> ending <strong>"+m_end_date+"</strong> "+
			            "Place of payment shall be at principal place of business of lessor at "+
			            ""+m_orient_add1+", "+m_orient_add2+","+m_orient_city_name+".");
									
					out.println("<br><br>METHOD OF PAYMENT :CHEQUE/BANK STANDING ORDER/CASH				");
					out.println("</td>");				
					
					out.println(" <tr> ");
					out.println("  <td colspan='3' rowspan='4' valign='top'><strong>5. INITIAL TERM (TERMS OF LEASE)</strong><br> ");
					
					out.println("   <br> ");
					out.println("Lease to commence from the date of Acceptance Receipt </td> "); 
					
					out.println("  <td colspan='2'>NO. OF "+ m_no_of+"</td> ");
					out.println("  <td colspan='2'><strong>"+ m_rental+" RENTAL - given in item No 15 of the schedule</strong></td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td colspan='2' rowspan='3' >&nbsp;<b>"+m_period+ " "+ m_no_of+" </td> ");
					rs_rental=stmt_rental.executeQuery(sql_rent);
					more=rs_rental.next();
					
					if(more){
					out.println("  <td width='12%' class='rep-body1'>&nbsp;Amount Rs.</td> ");
					out.println("  <td width='10%' style='text-align:right' class='rep-body1' >&nbsp;"+nf.format(rs_rental.getDouble(2))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> ");
					out.println("  <td class='rep-body1'>&nbsp;Plus VAT Rs.</td> ");
					out.println("   <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(3))+"</td> ");
					out.println(" </tr> ");
					out.println(" <tr> "); 
					out.println("  <td class='rep-body1'>&nbsp;Total Rs.</td> "); 
					out.println("  <td  style='text-align:right' class='rep-body1'>&nbsp;"+nf.format(rs_rental.getDouble(4))+"</td> ");
					out.println(" </tr> ");
					
					out.println(" </tr>");
					}
					out.println("<tr>");
					
					out.println("   <td colspan='3' class='rep-body1' valign='top'><b>&nbsp;6.</b>Payment of <b>Rs."+nf.format(rs_rental.getDouble(4))+"</b> covering the first and the last <b>"+m_ami+"</b> month/s rental payable on<B>"+m_rental_start_date+"</B> and thereafter <B>"+m_rental_start_day+"</B> day of each month after the expiry of grace period of <b>"+m_ami+"</b> month/s.</td>");
					String	data="<b>SECURITY MARGIN INTEREST BEARING</b>";
					out.println("<td colspan='4'   valign='top' class='rep-body1'>");
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>7.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");

					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b></td> ");
					if(m_security_margin_val>0)
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>"+nf.format(m_security_margin_val)+"</td> ");
					}
					else
					{
					out.println("<td width='*%' class='rep-body1' valign='top' style='{font-size: 9px;}'><b>NIL</td> ");
					}
				
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>8. PURPOSE OF EQUIPMENT/S <br>"); //EQUIPMENT //"+m_asset_type_desc.toUpperCase()+"
					out.println("       <br>");
					out.println("   </strong>"+m_type_use+" </td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>9. STIPULATED LOSS VALUE(For 12 months period calculated from date of commencement of Lease)</strong></td>");
					out.println(" </tr>");
					
					/*out.println("  <tr>");
					out.println("  <td width='18%' class='rep-body1' rowspan='2'>&nbsp;</td>");
					out.println("  <td width='14%' class='rep-body1'>&nbsp;</td>");
					out.println("  <td width='22%' class='rep-body1'>&nbsp;</td>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td colspan='2' >&nbsp;</td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td >&nbsp;</td>");
					out.println("   <td > &nbsp;</td>");
					out.println("   <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("   <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println(" </tr>");
					*/
					
					rs=stmt.executeQuery (Sql_stipulated);
					
					while(rs.next()){
					out.println("<tr> ");
          out.println(" <td class='rep-body1' valign='top'>&nbsp;</td> ");
          out.println(" <td class='rep-body1' valign='top'>Year"+rs.getInt(1)+"</td> ");
          out.println(" <td colspan='5' class='rep-body1' valign='top'>"+nf.format(rs.getDouble(2))+"</td> ");
          out.println(" </tr> ");
					}
						
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>10. OVERDUE INTEREST :(Interest at "+nf.format(m_od_interest_rate)+"% per month on all overdues until payment of such rental and interest).</strong></td>");
					out.println(" </tr>");
					out.println(" <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>11. EXCLUTION CLAUSE : it is agreed between the parties that the following clauses of the lease agreement shall be excluded.<br>");
					out.println("       <br>");
					out.println("CLAUSE NO. NIL </strong></td>");
					out.println(" </tr>");
					
					/*
					out.println("</table>");
					out.println(" <br><br>");
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					*/
					//-----------------------------------------------------------------------------------------------------------------------------------------		
					/*
					//Added by Mahela on 21-06-2007  ----------------------------------------------------------------------------------------------------------
					out.println(" <tr></tr><tr></tr><tr></tr>");
					out.println(" <tr>");
					out.println("   <td colspan='6' class='rep-body1' valign='top' style='text-align:justify'>");					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   </td>");
					out.println(" </tr>");
					out.println(" <br>");
					//-----------------------------------------------------------------------------------------------------------------------------------------
					*/
					
					
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>12. DESCRIPTION OF EQUIPMENT LEASE</strong></td>");
					out.println(" </tr>");
					out.println("<tr> ");
					out.println("  <td>SUPPLIER<br></td> ");
					out.println("  <td>QTY</td> ");
					out.println("  <td colspan='5'>DESCRIPTION</td> ");
					out.println(" </tr> ");

					out.println("  <tr> ");
					out.println("    <td>&nbsp;</td> ");
					out.println("  <td>&nbsp;</td> ");
					out.println("  <td colspan='5'>&nbsp;</td> ");
					out.println(" </tr> ");
					more = rs_invoice.next();
					
			    while(more){
					out.println("<tr>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;<B> "+rs_invoice.getString(2)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(3)+"");
					out.println("<br>");
					out.println("  &nbsp;<B>"+rs_invoice.getString(4)+"");
					out.println("<br>");
					out.println("</td>");
					
					out.println("  <td class='rep-body1' valign='top'>&nbsp;"+rs_invoice.getString(5)+"</td>");
					out.println("  <td colspan='5' class='rep-body1' valign='top'>");
					
					out.println("  <B>"+rs_invoice.getString(6)+""); //added by nwuan de silva on 04-09-07
					out.println("  <br><br>");
					/*out.println("  <B>"+rs_invoice.getString(10)+"");
					out.println("  <br><br>");
					out.println("  "+rs_invoice.getString(11)+"");
					out.println("  <br><br>");
					*/
					out.println("  SERIAL NO : "+rs_invoice.getString(9)+"");
					out.println("  <br><br>");
					out.println("  </td>");
					out.println("  </tr>");
					 more=rs_invoice.next();
			
			}
						out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>DESCRIPTION OF PROPERTY PLEDGED SECURED/OR OTHER SECURITIES</strong>";
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>13.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
					out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>");
					
						rs=stmt.executeQuery (Sql_Guarantor);
					more = rs.next();
					int gur_data_i=1;					
					int gur_data_c=1;	
					if(!more){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>NIL</td> ");
					 out.println("</tr>");
					}
			    while(more){
					 if(rs.getString(1).equals("I") ){
					 if(gur_data_i==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Personal Guarantee of</td> ");
					 out.println("</tr>");
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_i+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 out.println("</tr>");
						gur_data_i=gur_data_i+1;							
					 }
					 else if(rs.getString(1).equals("C") ){
					 if(gur_data_c==1){
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>&nbsp;</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'><b>Corporate Guarantee of</td> ");
					 out.println("</tr>");
					 }
					 out.println("<tr>");
					 out.println("<td width='1%' class='rep-body1' valign='top'>"+gur_data_c+")</td> ");
					 out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(2)+"</td> ");
					 //out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(3)+"</td> ");
					 //out.println("<td width='*%' class='rep-body1' valign='top'>"+rs.getString(4)+"</td> ");
					 out.println("</tr>");
						gur_data_c=gur_data_c+1;				
					 }
					
					
					more=rs.next();
					
			    }
          out.println("</table>");
					out.println("</td>");
					out.println(" </tr>");
					out.println("  <tr>");
					out.println("   <td colspan='7' class='rep-body1'><strong>14. Whether the insurance premium is included</strong><br>");
					out.println("     <br>");
					out.println("     <strong><B>"+m_insurance+"</strong></td>");
					out.println(" </tr>");
					//-----------------------------------------------------------------------------------------------------------------------------------------
					out.println("  <tr>");
					
					out.println("   <td colspan='7' class='rep-body1' valign='top' style='text-align:justify'>");
					data="<strong>"+ m_rental+" RENTAL BREAKUP</strong>";
					
					out.println("<table width='100%'  border='0' cellspacing='0' > ");
					out.println("<tr>");
					out.println("<td width='1%' class='rep-body1' valign='top'><b>15.</td> ");
					out.println("<td width='*%' class='rep-body1' valign='top'>"+data+"</td> ");
					out.println("</tr>");
	        out.println("</table>");
					out.println("</td>");
					out.println("  </tr>");
					out.println("  <tr>");
					out.println("  <td colspan='2' class='rep-body1'>&nbsp;</td>");
					out.println("  <td class='rep-body1'><div align='center'><strong>NET<br>");
					out.println(" Rs</strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>VAT<br>");
					out.println("  Rs </strong></div></td>");
					out.println("    <td colspan='2' class='rep-body1'><div align='center'><strong>GROSS<br>");
					out.println("  Rs </strong></div></td>");
					out.println("  </tr>");
		//================================================================================
	
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					boolean more3 =rs_rental.next();
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
								while(more3) //START INSTALLMENT LOOP
									{
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	out.println("  <tr>");
												if(start==end)
									       {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													else
										      {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													out.println("</tr>"); 
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
													out.println("  <tr>");	
													if(start==end)
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													else
									        {
													out.println("    <td colspan='2' class='rep-body1'><strong><b>"+count_period+" "+ m_no_of+" <br>");
													out.println("("+start_date+" - "+end_date+") </strong></td>");
													}
													out.println("   <td class='rep-body1'><div align='right'><b>"+nf.format(m_rental_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_vat_new)+"</div></td>");
													out.println("   <td colspan='2' class='rep-body1'><div align='right'><b>"+nf.format(m_gross_new)+"</div></td>");
													out.println("</tr>"); 
								} //END OF INSTALLMENT LOOP
					out.println("</table>");
					// --------------  Added by Mahela on 21-06-2007 ----------------------------------------------------------------------------------------------
					/*out.println(" <br><br>");
		      out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");				
					*/
					
					// --------------  Added by Nuwan on 01-08-2007 ----------------------------------------------------------------------------------------------
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>&nbsp;</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>2 of 2 </b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					//out.println(" <br>");
					
			    out.println("<table width='100%'  border='1' cellspacing='0' bordercolor='black' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> having read Understood contains hereof</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>The common seal of the within name</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");			
					//COMMENT BY NUWAN DE SILVA ON 28-11-2007 AT OFSCL
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");			
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					*/
					
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>was affixed hereto in the presence of</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Company Emboss</b></td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>"+m_full_name1.toUpperCase()+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:center;}'><b>Seal</b></td></tr>");
					out.println("   </table>");


					out.println("   <br>");
					/*out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					*/
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------</td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'></td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>Who do hereby attest the sealing</td></tr>");
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>hereof</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'>Lessees by</td><td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Signature</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Chairman/Director</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Partner/Proprietor/Secretary</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>Name  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					
						//----------Aadded by nuwan de silva on 01-09-07-------------
				out.println("   <br>");
				out.println("   <table border='0' width='100%' class='table'> ");	
				out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td>");
				out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------------------</td></tr>");
				out.println("   </table>");
				//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='20%' class='rep-body1' style='{text-align:left;}'></td><td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td>");
					out.println("   <td width='40%' class='rep-body1' style='{text-align:left;}'>NIC No  -------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='25%' class='rep-body1' style='{text-align:left;}'>Date of Signing the schedule : </td><td width='37%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='37%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='*%' class='rep-body1' style='{text-align:left;}'><b>SIGNED</b> for and on behalf of "+m_orient_name+"  </td></tr>"); //ORIENT FINANCIAL SERVICES CORPORATION LIMITED
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Lessor By  </td><td width='42%' class='rep-body1' style='{text-align:left;}'> -----------------------------------------------------------</td>");
					out.println("   <td width='42%' class='rep-body1' style='{text-align:left;}'>  -----------------------------------------------------------</td></tr>");
					out.println("   </table>");
					/*
					out.println("   </blockquote>");
					out.println("   </td></tr>");
					out.println("</table>");
					out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					out.println("   <p style=\"page-break-after:always\"></p>");		
					out.println("<table width='100%'  border='1' cellspacing='0' > ");
					out.println("   <tr><td width='100%' class='rep-body1'>");
					out.println("   <blockquote><br>");
					*/
					out.println("   <br>");	
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'>In the presence of : </td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 1 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Witness 2 :</td><td width='35%' class='rep-body1' style='{text-align:left;}'></td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Signature :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Name :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Address :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>-------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------Aadded by nuwan de silva on 01-09-07-------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>&nbsp;</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//----------------------
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>NIC No :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					out.println("   <br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td>");
					out.println("   <td width='15%' class='rep-body1' style='{text-align:left;}'>Date :</td><td width='35%' class='rep-body1' style='{text-align:left;}'>---------------------------------------------------------</td></tr>");
					out.println("   </table>");
					//out.println("   <br>");
					out.println("   </blockquote>");
					//out.println("   <br><br><br>");
					out.println("   </td></tr>");
					out.println("</table>");
					//comment by nuwan de silva on 01-09-07
					/*out.println(" <br><br>");
					out.println("   <table border='0' width='100%' class='table'> ");	
					out.println("   <tr ><td width='50%' class='rep-body1' style='{text-align:left;}'><b>Master Lease Agreement No: "+m_master_lease+"</b></td>");
					out.println("   <td width='50%' class='rep-body1' style='{text-align:right;}'><b>Schedule No : "+m_finance_no+"</b></td></tr>");
					out.println("   </table>");			
					*/
					
					//out.println("   <p style=\"page-break-after:always\"></p>");		
					//-------------------------- End of Addition ------------------------------------------------------------------------------------------			
			out.println("</font></p>");
			}
			else
			{
			out.println("Sorry No Document For The Item Category :\t " +m_asset_type);
			}
		  out.println("</form></body></html>");
			}
			out.flush();
		}
		catch (Exception ex) 
		{
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally
		{
		  
				if(rs_location!=null){try{rs_location.close();  }catch(Exception e){}}
		    if(stmt_location!=null){try{stmt_location.close();  }catch(Exception e){}}
				if(rs_invoice!=null){try{rs_invoice.close();  }catch(Exception e){}}
		    if(stmt_invoice!=null){try{stmt_invoice.close();  }catch(Exception e){}}
				if(rs_rental!=null){try{rs_rental.close();  }catch(Exception e){}}
		    if(stmt_rental!=null){try{stmt_rental.close();  }catch(Exception e){}}
				if(rs_partner!=null){try{rs_partner.close();  }catch(Exception e){}}
		    if(stmt_partner!=null){try{stmt_partner.close();  }catch(Exception e){}}
				if(rs_rental2!=null){try{rs_rental2.close();  }catch(Exception e){}}
		    if(stmt_rental2!=null){try{stmt_rental2.close();  }catch(Exception e){}}
				if(rs!=null){try{rs.close();  }catch(Exception e){}}
		    if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
				
						
			
			
		}
	}
}
