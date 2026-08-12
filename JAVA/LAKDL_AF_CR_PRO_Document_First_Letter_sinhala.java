// created by udara 12-11-2013

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Document_First_Letter_sinhala extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental,stmt2;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental,rs2;
	
	
	// commented by udara 06-11-2018
	/*
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print,m_sys_date;
	public double m_amount_due;
	String rec_count="";
	public int mm_period;
	*/
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		// added by udara 06-11-2018
		String reqstr=null,m_Letter_date=null,m_c_code=null,m_name=null,m_city_desc=null,m_due_date=null,m_no_of_due_date=null,m_finance_no=null,m_sys_date=null;
	    double m_amount_due;
	    String rec_count="";
		int mm_period = 0;
		// end by udara 06-11-2018
		
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
			String m_full_rental_due_date = "";
			String mm_full_rental_due_date = "";
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
			
			double m_gross_new=0; // udara
			
			int odi_rate = 0; // udara 12-11-2015
			
			String m_chksql = req.getParameter("chksql");
			String m_application_no = req.getParameter("application_no");
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
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
				
				rs = stmt.executeQuery ("SELECT RATE FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
				if(rs.next()){
					odi_rate = rs.getInt(1);
				}
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
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
					"  TO_CHAR(ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(ACTIVATED_DATE, 'Month')|| TO_CHAR(ACTIVATED_DATE, 'YYYY') START_DATE, nvl(MASTER_AGREEMENT_NO,'-') "+
					" ,TO_CHAR(ACTIVATED_DATE, 'DD-MM-YYYY'), "+
					"  TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ");
				
				more = rs.next();
				
				if(more){
					m_finance_no=rs.getString(1);
					m_start_date=rs.getString(2);
					m_master_lease=rs.getString(3);
					m_Letter_date=rs.getString(4);
					m_sys_date = rs.getString(5);
				}

				
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
				

				out.println("}"); 
				
				out.println("function get_vector_normal(http_response) {");
				//added by nuwan de silva on 05-09-07
				
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
				//out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>"+m_Letter_date+"</td></tr>");
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2><b>"+m_sys_date+"</td></tr>"); // added by udara 21-01-2014
				out.println("</table>");
				
				//================================================================================
				rs = stmt.executeQuery(Client_Data);
				more = rs.next();		
				
				while(more){	
					m_full_name=rs.getString(2);
					m_add1=rs.getString(3);
					m_add2=rs.getString(4);
					m_city_name=rs.getString(5);
					m_nic_no=rs.getString(6);

					more = rs.next();		
				}
				
				//================================================================================
				
				out.println("<br><br>");

				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 	
				out.println("<td width='95%' colspan='2' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3524;&#3538;&#3501;&#3520;&#3501;&#3530; &#3512;&#3524;&#3501;&#3530;&#3512;&#3514;&#3535;&#3499;&#3505;&#3538; / &#3512;&#3524;&#3501;&#3530;&#3512;&#3538;&#3514;&#3499;&#3538;,");
	            out.println("</font> </td>"); 
				out.println("</table>");
				
				out.println("<br>");

				out.println("<table border='0' width='80%' class='table'>"); 
				out.println("<td width='95%' colspan='2' class='rep-body1' >");
			    out.println("<font size=3 ><b><u>");
		        out.println("&#3482;&#3540;&#3517;&#3539; &#3523;&#3538;&#3505;&#3530;&#3505;&#3482;&#3530;&#3482;&#3515; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512;&#3530; &#3461;&#3458;&#3482;  : "+m_finance_no+" ");
	            out.println("</u></b></font> </td>"); 
				out.println("</table>");

				out.println("<br>");	
				
				rs_make=stmt_make.executeQuery (sql_make_new)	;
				
				int j=0;
				more=rs_make.next();

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

				
				String reg_no = "";
				rs_make=stmt_make.executeQuery (sql_make)	;
				
				if(rs_make.next()){
					m_make_code=rs_make.getString(1);
					m_make_desc=rs_make.getString(5);
					m_item_sub_code=rs_make.getString(3);
					m_model_code=rs_make.getString(4);
					m_sub_model=rs_make.getString(8); //ADDED BY NWUAN DE SILVA 04-07-07
					m_pricing_no=rs_make.getString(7); //ADDED BY NWUAN DE SILVA 04-07-07
					reg_no =rs_make.getString(9); 
					
					
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
						mm_period   =rs.getInt(1);
					}
					
					// end by udara 17-03-2017
					
						
					
					rs=stmt.executeQuery(" SELECT "+
						" TO_CHAR(C.ACTIVATED_DATE, 'fmddth') || ' ' || TO_CHAR(C.ACTIVATED_DATE, 'Month')|| TO_CHAR(C.ACTIVATED_DATE, 'YYYY') START_DATE, "+
						" B.PERIOD,  '', "+
						" SUM(GRENTAL_AMOUNT), "+
						" SUM(B.NIBSM), "+
						" SUM(B.RESIDUAL_VALUE), '',  "+
						//" NVL(TO_CHAR(C.ACTIVATED_DATE,'fmddth'),'-') "+ //comment by nuwan de silva on 22-10-07
						" NVL(TO_CHAR(A.RENTAL_DATE,'DD'),'-'), "+ // 27-01-2014  // " NVL(TO_CHAR(A.RENTAL_DATE,'fmddth'),'-'), "+ //added by nuwan de silva on 22-10-07
						" NVL(TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'),'-'), "+  // udara 21-01-2014
						" NVL(TO_CHAR(A.RENTAL_DATE,'DD'),'-') "+ // udara 21-01-2014
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
						// m_period	=rs.getInt(2); // commented by udara 17-03-2017
						m_rental_start_date=rs.getString(3);
						m_gross_rental=rs.getDouble(4);
						m_security_margin_val=rs.getDouble(5);
						m_residual_value=rs.getDouble(6);
						m_rental_due_date=rs.getString(8);
						m_full_rental_due_date = rs.getString(9);
						mm_full_rental_due_date = rs.getString(10);
						
						// mm_period = rs.getInt(2); // commented by udara 17-03-2017
						
						//out.println("aaaaaaaa" + mm_period);
						
						
						
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
						

						m_period_time=m_period-1;

						
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

						
						more = rs.next();
						if(more){
							m_last_rental_date=rs.getString(1);
							m_last_rental_date_year = rs.getString(2);
						}

						
						
						
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
							" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE   "+
							" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
						
						int end=0;
						int start=0;
						String m_ins="";
						double m_rental_new=0;
						double m_vat_new=0;
						
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
	
							
						}
						
						
						
					}
					
					j=j+1;//Added by Nuwan De Silva 23-04-2007
					
				}
				
				//out.println("bbbbbbbbb" + mm_period);
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<p>"); // added 23-01-2014
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3476;&#3510; &#3461;&#3508;&#3484;&#3546; &#3508;&#3535;&#3515;&#3538;&#3511;&#3549;&#3484;&#3538;&#3482;&#3514;&#3545;&#3482;&#3540; &#3520;&#3539;&#3512; &#3508;&#3538;&#3525;&#3538;&#3510;&#3507;&#3520; &#3482;&#3544;&#3501;&#3493;&#3501;&#3535;&#3520;&#3514; &#3508;&zwj;&#3530;&zwj;&#3515;&#3482;&#3535;&#3521; &#3482;&#3515; &#3523;&#3538;&#3495;&#3538;&#3512;&#3540;. &#3476;&#3510; &#3524;&#3535; &#3476;&#3510;&#3484;&#3546; &#3463;&#3508;&#3482;&#3515;&#3540;&#3520;&#3505;&#3530; &#3520;&#3505;");
	            out.println("</font>");
				//out.println("</td>"); // commented 23-01-2014
				//out.println("</table>"); // commented 23-01-2014
				

				
				// =====================================================================================================
				
				String sql_guaranter = " SELECT "+ 
									   " INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(A.GUARANTOR_CODE)), "+ // 1
									   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE), "+ // 2
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(A.GUARANTOR_CODE),'-') "+  // 3   
									       " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
									       " WHERE A.APPLICATION_NO = '"+m_application_no+"'  "+
									       " ORDER BY "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE) ";
				
				
				rs_rental = stmt_rental.executeQuery(sql_guaranter);
				int gua_count = 0;
				int gua_count_2 = 0;
				gua_count = 0;
				
				boolean more_guar_det = rs_rental.next();
				
				//while(rs_rental.next()){

				while(more_guar_det) {	
					
					gua_count_2 = gua_count_2 + 1;
					
					out.println(" "+gua_count_2+") "); // added by udara 23-01-2014
					
				    out.println("<font size=2 >");
			        out.println(" "+rs_rental.getString(3)+" ");
		            out.println("</font>");
					
					out.println(" "); // added by udara 23-01-2014
					

				    out.println("<font size=2 >");
			        out.println("&#3517;&#3538;&#3508;&#3538;&#3505;&#3514;&#3546; &#3508;&#3503;&#3538;&#3458;&#3488;&#3538;");
		            out.println("</font> ");

					
					out.println(" "); // added by udara 23-01-2014
					
				    out.println("<font size=2 >");
			        out.println(" "+rs_rental.getString(2)+" ");
		            out.println("</font> ");
					
					out.println(" "); // added by udara 23-01-2014
					

					more_guar_det = rs_rental.next();
					
					if(!more_guar_det){
						break;
					}
					else{
						out.println("<font size=2 >");
				    	out.println("&#3524;&#3535;");
			        	out.println("</font> ");
					}

					
					
				}
				
				
				int m_rental_count = 0;
				double m_capitl = 0;
				
				rs2=stmt2.executeQuery(
				//out.println(
				" SELECT SUM(B.NET_PRICE), "+//1
				" SUM(A.INTEREST_AMOUNT), "+//2
				" 0 , "+//3
				" COUNT(*) "+	//4
				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
				" WHERE A.APPLICATION_NO = '"+m_application_no+"'  "+
				" AND A.APPLICATION_NO   = B.APPLICATION_NO "+
				" AND A.PRICING_NO       = B.PRICING_NO "+
				" AND A.PRO_INVOICE_NO   = B.INVOICE_NO "+
				" AND B.ACTIVE_STATUS    IN ('T','Y') "+
				//" AND A.INSTALLMENT_NO <> 0  ");	// commented by udara 17-03-2017 // added by udara 16-03-2017	
				" "); // added by udara 17-03-2017
				
				
				if(rs2.next()){
					m_rental_count= rs2.getInt(4);
					m_capitl      = rs2.getDouble(1)/m_rental_count;
				
				}	
				
				// added by udara 17-03-2017
				double m_down_payment_amount = 0;
				
				rs2=stmt2.executeQuery("  "+
					" SELECT NVL(CAPITAL_AMOUNT,0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE APPLICATION_NO = '"+m_application_no+"'  "+
					" AND INSTALLMENT_NO = 0 "+
					" ");
				
				if(rs2.next()){
					m_down_payment_amount = rs2.getDouble(1);
				}
				
				m_capitl = m_capitl - m_down_payment_amount;
				
				// end by udara 17-03-2017
				

				// temp commented by udara again on 17-03-2017
				/*
				// added by udara 16-03-2017
				double m_amortized_charges = 0;
				
				rs2 = stmt2.executeQuery(" "+  
					" SELECT NVL(SUM(AMOUNT),0)  "+
					 	" FROM( "+
						 	" SELECT "+  
					       		 " B.PRICING_NO PRICING_NO,  "+  
					             " B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
					             " C.DESCRIPTION DESCRIPTION,   "+ 
					             " SUM(D.AMOUNT) AMOUNT ,  "+
					             " B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
					             " 'CHARGES' CHARGES  "+
												 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
												 " "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
												 " WHERE A.APPLICATION_NO=B.APPLICATION_NO   "+ 
											     " AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
												 " AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE  "+ 
												 " AND B.PRICING_NO = D.PRICING_NO  "+
												 " AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE  "+ 
												 " AND D.CHARGE_TYPE = 'AMO'   "+ 	
												 " AND NVL(B.AMOUNT,0) <> 0  "+
												 " GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION 	"+		 
					 " ) "+
					 " ORDER BY CHARGES,DESCRIPTION "+
					 " ");
				
				if(rs2.next()){
						m_amortized_charges = rs2.getDouble(1);
				}
				
				m_capitl = m_capitl - m_amortized_charges;
				// end by udara 16-03-2017
				*/
				
				
				// =====================================================================================================

			    out.println("<font size=2 >");
		        out.println("&#3520;&#3538;&#3523;&#3538;&#3505;&#3530;");
	            out.println("</font>");

			    out.println("<font size=2 >");
		        out.println(" "+m_Letter_date+" &#3503;&#3538;&#3505; &#3461;&#3501;&#3530;&#3523;&#3505;&#3530; &#3482;&#3515;&#3505;&#3540; &#3517;&#3536;&#3510;&#3540; &#3465;&#3524;&#3501; &#3484;&#3538;&#3520;&#3538;&#3523;&#3540;&#3512;&#3495; &#3461;&#3503;&#3535;&#3517; &#3501;&#3548;&#3515;&#3501;&#3540;&#3515;&#3540; &#3508;&#3524;&#3501; &#3503;&#3482;&#3530;&#3520;&#3535; &#3463;&#3501;.");
	            out.println("</font>");
				//out.println("</td>");  // commented 23-01-2014
				//out.println("</table>");  // commented 23-01-2014
				
				out.println("</td>");
				out.println("</table>"); // added 23-01-2014
					
				out.println("<br>");
				
				//out.println("cccccc" + mm_period);
				
				// 1
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3461;&#3514;&#3538;&#3501;&#3512;&#3514;/ &#3520;&#3535;&#3524;&#3505;&#3514; ");
	            out.println("</font> </td>"); 
				out.println("<td width='20%' class='rep-body1' > : "+m_make_desc+"("+reg_no+") </td>");
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				// 2
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3508;&#3524;&#3523;&#3540;&#3482;&#3512;&#3530; &#3520;&#3495;&#3538;&#3505;&#3535;&#3482;&#3512; ");
	            out.println("</font> </td>"); 
				out.println("<td width='20%' class='rep-body1' > : "+nf.format(m_capitl)+" </td>");
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				//out.println("ddddddddeeeeee" + mm_period);
				
				// 3

				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3484;&#3538;&#3499;&#3540;&#3512;&#3530; &#3482;&#3535;&#3517;&#3514; ");
	            out.println("</font> </td>");
				out.println("<td width='20%' class='rep-body1' > : <font size=2 > &#3512;&#3535;&#3523; </font> "+mm_period+" </td>"); 
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				
				
				// 4
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3512;&#3535;&#3523;&#3538;&#3482; &#3520;&#3535;&#3515;&#3538;&#3482;&#3514; ");
	            out.println("</font> </td>"); 
				//out.println("<td width='20%' class='rep-body1' > "+nf.format(m_gross_new)+" </td>"); 
				out.println("<td width='20%' class='rep-body1' > : <font size=2 > &#3515;&#3540; </font> "+nf.format(m_gross_new)+" </td>"); 
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				// 5
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3520;&#3535;&#3515;&#3538;&#3482;&#3514; &#3484;&#3545;&#3520;&#3538;&#3514; &#3514;&#3540;&#3501;&#3540; &#3503;&#3538;&#3505;&#3514; ");
	            out.println("</font> </td>"); 
				out.println("<td width='20%' class='rep-body1' > : <font size=2 > &#3523;&#3537;&#3512; &#3512;&#3535;&#3523;&#3514;&#3482;&#3512; </font> "+m_rental_due_date+" <font size=2 > &#3520;&#3505; &#3503;&#3538;&#3505; </font> </td>"); // m_master_lease
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				// 6
				
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3512;&#3539;&#3517;&#3484; &#3520;&#3535;&#3515;&#3538;&#3482;&#3514; ");
	            out.println("</font> </td>");
				out.println("<td width='20%' class='rep-body1' > : "+m_full_rental_due_date+"  </td>"); // m_full_rental_due_date // m_rental_due_date
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				// 7
				// commented by udara 03-02-2014
				/*
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");

				out.println("&#3515;&#3482;&#3530;&zwj;&#3522;&#3499;&#3535;&#3520;&#3515;&#3505;&#3514; :  &#3508;&zwj;&#3530;&zwj;&#3515;&#3512;&#3535;&#3499;&#3520;&#3501;&#3530;, &#3508;&#3542;&#3515;&#3530;&#3499;    ");
	            out.println("</font> </td>"); 

				out.println("</table>");
				*/
				
				out.println("<table border='0' width='100%' class='table'>"); 
				out.println("<td width='20%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println(" &#3515;&#3482;&#3530;&zwj;&#3522;&#3499;&#3535;&#3520;&#3515;&#3505;&#3514; ");
	            out.println("</font> </td>");
				out.println("<td width='20%' class='rep-body1' > <font size=2 > : &#3508;&zwj;&#3530;&zwj;&#3515;&#3512;&#3535;&#3499;&#3520;&#3501;&#3530;, &#3508;&#3542;&#3515;&#3530;&#3499; </font> </td>"); // m_full_rental_due_date // m_rental_due_date
				out.println("<td width='*%'  class='rep-body1' > &nbsp; </td>");
				out.println("</table>");
				
				out.println("<br>"); // added 23-01-2014
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3515;&#3482;&#3530;&zwj;&#3522;&#3499;&#3535;&#3520;&#3515;&#3505;&#3514; &#3508;&#3540;&#3515;&#3530;&#3499; &#3515;&#3482;&#3530;&zwj;&#3522;&#3499;&#3514;&#3482;&#3530; &#3520;&#3538;&#3514; &#3514;&#3540;&#3501;&#3540; &#3461;&#3501;&#3515; &#3508;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3462;&#3520;&#3515;&#3499;&#3514; &#3501;&#3538;&#3510;&#3538;&#3514; &#3514;&#3540;&#3501;&#3540;&#3514;&#3538;. &#3512;&#3545;&#3514; &#3520;&#3535;&#3515;&#3530;&#3522;&#3538;&#3482;&#3520; &#3461;&#3517;&#3542;&#3501;&#3530; &#3482;&#3525; &#3514;&#3540;&#3501;&#3540; &#3520;&#3546;.  ");
	            out.println("</font> ");

				out.println(" SRCC, FLOOD & TC "); // added 23-01-2014
				
				out.println("</td>");  // commented 23-01-2014
				out.println("</table>"); // commented 23-01-2014
				
				out.println("<br>"); // added 23-01-2014
				
				out.println("<table border='0' width='95%' class='table'>");  // commented 23-01-2014
				out.println("<td width='100%' class='rep-body1' >"); // commented 23-01-2014
			    out.println("<font size=2 >");
		        
	            out.println("&#3523;&#3538;&#3514;&#3517;&#3542;&#3512; &#3520;&#3535;&#3515;&#3538;&#3482; &#3512;&#3540;&#3503;&#3517;&#3530; &#3523;&#3537;&#3512; &#3512;&#3523;&#3482;&#3512;   ");
				out.println("</font>");
		
				out.println(" "+mm_full_rental_due_date+" ");  // out.println(" &nbsp;  &nbsp;  &nbsp;  &nbsp; ");
			
				out.println("<font size=2 >");
		        
	            out.println("&#3503;&#3538;&#3505;&#3495; &#3508;&#3545;&#3515; &#3484;&#3545;&#3520;&#3538;&#3514; &#3514;&#3540;&#3501;&#3540; &#3520;&#3505; &#3461;&#3501;&#3515; &#3508;&zwj;&#3530;&zwj;&#3515;&#3512;&#3535;&#3503; &#3484;&#3535;&#3523;&#3530;&#3501;&#3540; &#3517;&#3545;&#3523;");
			    out.println("</font>");
			    
				//out.println(" 5% "); // commented by udara 12-11-2015
				out.println(" "+odi_rate+"% "); // added by udara 12-11-2015
				out.println("<font size=2 >");
				out.println(" &#3482; &#3512;&#3535;&#3523;&#3538;&#3482; &#3503;&#3497; &#3508;&#3548;&#3517;&#3538;&#3514;&#3482;&#3530; &#3467;&#3508;&#3488;&#3514; &#3520;&#3505; &#3510;&#3520; &#3503;&#3505;&#3530;&#3520;&#3535; &#3523;&#3538;&#3495;&#3538;&#3512;&#3540;. "); // added 23-01-2014
				out.println("</font>");
		
		        out.println("</td>"); 
				out.println("</table>");
				
				out.println("<br>"); // added 23-01-2014
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3465;&#3524;&#3501; &#3523;&#3507;&#3524;&#3505;&#3530; &#3501;&#3548;&#3515;&#3501;&#3540;&#3515;&#3540; &#3476;&#3510;&#3484;&#3546; &#3461;&#3515;&#3512;&#3540;&#3499;&#3495; &#3508;&zwj;&#3530;&zwj;&#3515;&#3512;&#3535;&#3499;&#3520;&#3501;&#3530; &#3520;&#3505; &#3510;&#3520; &#3461;&#3508;&#3484;&#3546; &#3520;&#3538;&#3521;&#3530;&#3520;&#3535;&#3523;&#3514; &#3520;&#3505; &#3461;&#3501;&#3515; &#3476;&#3510;&#3484;&#3546; &#3523;&#3538;&#3514;&#3517;&#3542;&#3512; &#3520;&#3538;&#3512;&#3523;&#3539;&#3512;&#3530; &#3524;&#3549; &#3484;&#3536;&#3495;&#3517;&#3542; &#3523;&#3507;&#3524;&#3535; &#3508;&#3524;&#3501; &#3461;&#3514;&#3495; &#3514;&#3548;&#3512;&#3540; &#3482;&#3515;&#3505;&#3530;&#3505;.");
	            out.println("</font> </td>"); 
				out.println("</table>");
				
				out.println("<br>"); // added 23-01-2014
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3476;&#3510;&#3495; &#3523;&#3546;&#3520;&#3535; &#3523;&#3536;&#3508;&#3514;&#3539;&#3512;&#3495; &#3461;&#3520;&#3523;&#3530;&#3502;&#3535;&#3520; &#3517;&#3510;&#3535;&#3503;&#3539;&#3512; &#3508;&#3538;&#3525;&#3538;&#3510;&#3507;&#3520; &#3523;&#3530;&#3501;&#3542;&#3501;&#3538;&#3520;&#3505;&#3530;&#3501; &#3520;&#3545;&#3512;&#3540;.");
	            out.println("</font> </td>"); 
				out.println("</table>");
				
				out.println("<br>"); // added 23-01-2014
				
				// commented by udara 10-11-2016
				/*
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3523;&#3530;&#3501;&#3542;&#3501;&#3538;&#3514;&#3538;.");
	            out.println("</font> </td>"); 
				out.println("</table>");
				
				//out.println("<br><br>"); // added by udara 05-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				
				// added by udara 30-10-2015
				out.println("<table border='0' width='90%' class='table'>"); 
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ><font size=2> <img src=\""+m_html_client_url+"/sign_agm/sign_agm.jpg\"  > </td></tr>"); // height=\""+new_height+"\" width=\""+new_width+"\"
				out.println("<tr>");
				out.println("    <td width='*%' class='rep-body1' ></td></tr>");
				out.println("</table>");
				// end by udara 30-10-2015
				
				
				//out.println("<br><br>"); // added by udara 05-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				//out.println("<br><br><br>"); // commented by udara 05-11-2015
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3517;&#3482;&#3530;&#3503;&#3545;&#3515;&#3499; &#3465;&#3505;&#3530;&#3520;&#3545;&#3523;&#3530;&#3495;&#3530;&#3512;&#3505;&#3530;&#3495;&#3530; &#3517;&#3538;&#3512;&#3538;&#3495;&#3486;&#3530;");
	            out.println("</font> </td>"); 
				out.println("</table>");
				*/
				
				// added by udara 10-11-2016
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<tr>");
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3523;&#3530;&#3501;&#3542;&#3501;&#3538;&#3514;&#3538;.");
	            out.println("</font> </td>"); 
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3517;&#3482;&#3530;&#3503;&#3545;&#3515;&#3499; &#3465;&#3505;&#3530;&#3520;&#3545;&#3523;&#3530;&#3495;&#3530;&#3512;&#3505;&#3530;&#3495;&#3530; &#3517;&#3538;&#3512;&#3538;&#3495;&#3486;&#3530;");
	            out.println("</font> </td>");
				out.println("</tr>");
				out.println("</table>");
				// end by udara 10-11-2016
				
				//out.println("<br><br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				
				// commented by udara 10-11-2016
				/*
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3523;&#3524;&#3482;&#3535;&#3515; &#3523;&#3535;&#3512;&#3535;&#3505;&#3530;&zwj;&#3514;&#3535;&#3504;&#3538;&#3482;&#3535;&#3515;&#3538;");
	            out.println("</font> </td>"); 
				out.println("</table>");
				
				//out.println("<br><br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				*/
				
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
		        out.println("&#3508;&#3538;&#3495;&#3508;&#3501;&#3530; : &#3463;&#3508;&#3482;&#3515;&#3540;&#3520;&#3505;&#3530;");
	            out.println("</font> </td>"); 
				out.println("</table>");

				out.println("</font></p></blockquote>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");		
				

				 sql_guaranter = " SELECT "+ 
									   " INITCAP("+m_schema_name+".AF_CO_GET_CLIENT_TITLE(A.GUARANTOR_CODE)), "+ // 1
									   " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE), "+ // 2
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.GUARANTOR_CODE),'-')||', '||NVL("+m_schema_name+".AF_CO_GET_CLI_CITY_NAME(A.GUARANTOR_CODE),'-') "+  // 3   
									       " FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A "+
									       " WHERE A.APPLICATION_NO = '"+m_application_no+"'  "+
									       " ORDER BY "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.GUARANTOR_CODE) ";
				
				
				rs_rental = stmt_rental.executeQuery(sql_guaranter);
				
				gua_count = 0;
				
				while(rs_rental.next()){
					gua_count++;
					
					out.println("<table border='0' width='90%' class='table'>"); 	
					out.println("<tr>");
					out.println("<td width='2%' class='rep-body1' ><b></b></td>");
					out.println("<td width='5%' class='rep-body1' ><font size=2>"+gua_count+")</td>");
					if(rs_rental.getString(1) == null){
						out.println("    <td width='*%' class='rep-body1' ><font size=2> "+rs_rental.getString(2)+" ,"+rs_rental.getString(3)+" </td></tr>"); 
					}else{
						out.println("    <td width='*%' class='rep-body1' ><font size=2> "+rs_rental.getString(1)+" "+rs_rental.getString(2)+" ,"+rs_rental.getString(3)+"</td></tr>"); 
					}
					out.println("</table>");
					
				}
				
				
				out.println("</font></p></blockquote></blockquote>");	
				//out.println("<br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				
				
				// added by udara 10-11-2016
				out.println("<blockquote><p style='text-align:left'>");
				out.println("<table border='0' width='95%' class='table'>"); 
				out.println("<td width='100%' class='rep-body1' >");
			    out.println("<font size=2 >");
				out.println(" &#3523;&#3536;.&#3514;&#3540;: &#3512;&#3545;&#3514; &#3523;&#3530;&#3520;&#3514;&#3458;&#3482;&#3530;&zwj;&#3515;&#3539;&#3514; &#3508;&#3515;&#3538;&#3485;&#3499;&#3482; &#3512;&#3540;&#3503;&#3530;&zwj;&#3515;&#3538;&#3501; &#3517;&#3546;&#3483;&#3505;&#3514;&#3482;&#3530; &#3510;&#3536;&#3520;&#3538;&#3505;&#3530; &#3461;&#3501;&#3530;&#3523;&#3505; &#3514;&#3545;&#3503;&#3539;&#3512; &#3461;&#3505;&#3520;&#3521;&#3530;&zwj;&#3514; &#3510;&#3520; &#3523;&#3517;&#3482;&#3505;&#3530;&#3505;. ");
	            out.println("</font> </td>"); 
				out.println("</table>");
				out.println("</p></blockquote>");
				// end by udara 10-11-2016

				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
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
