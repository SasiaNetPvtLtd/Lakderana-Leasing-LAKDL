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

public class LAKDL_AF_CR_PRO_Document_Offer_Letter extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	
	
	Connection conn;
	Statement stmt,stmt_doc_charges,stmt_make,stmt_rental;
	//	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs_doc_charges,rs_make,rs_anx_status,rs_rental;
	
	
	public String m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date,m_no_of_due_date,m_finance_no,m_print;
	public double m_amount_due;
	String rec_count="";
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
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

			String m_start_date="";
			String m_master_lease="";
			String m_nic_no="";

			double m_od_interest_rate=0;
			
			
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
				
				rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");
				
				boolean more = rs.next();
				if(more){
					m_Letter_date=rs.getString(1);
				}
				
				
				rs = stmt.executeQuery ("SELECT RATE FROM "+m_schema_name+".AF_CO_MAS_OD_INTEREST_RATE ");
				if(rs.next()){
					odi_rate = rs.getInt(1);
				}
				
				
				// added by udara 02-11-2018
				String m_company_name = "";
				
				rs = stmt.executeQuery ("SELECT INITCAP(COMPANY_NAME) "+
					" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				
				if(rs.next()){
					m_company_name = rs.getString(1);
				}
				// end by udara 02-11-2018
				
				
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
				
				
				//========================================================================================
				
				//--Close the Result Set And Stateement--------			
				rs.close();
				stmt.close();
				//---------------------------------------------
				//--Create The Statement----------------------
				stmt = conn.createStatement ();
				//--------------------------------------------	

				
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
				

				
				out.println("<html><head>"); 
				out.println("<meta http-equiv=\"content-type\" content=\"text-html; charset=utf-8\">");
				out.println("<title>First Letter </title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				
				
				out.println("<script>");

				
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
				
				//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Documents_Status?chksql=save_page&scr_name=AF_MK_APP_STATUS_APPROVE_3&application_no="+m_application_no+"&status="+m_status+"&client_code="+m_client_code+"&document_code="+m_document_code+"\";"); 
				//out.println(" window.location.href=m_url;"); 
				
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
				out.println("<tr><td width='100%' colspan='2' class='rep-body1' align='center' ><font size=3><b><u>LETTER OF OFFER AND ACCEPTANCE</u></b></td>");
				out.println("</tr>");				
				out.println("</table>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
			    out.println("<td width='*%' class='rep-body1' ><font size=2>Date : "+m_Letter_date+"</td>");
				out.println("<td width='*%' class='rep-body1' align='right' ><font size=2> LKD/LEG/PL/O&A </td>");
			    out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");

				
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
						out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=2>"+m_full_name+"</td></tr>");
						out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=2>"+m_add1+", "+m_add2+"</td></tr>");
						out.println("<tr><td width='*%' align='left' class='rep-body1' ><font size=2>"+m_city_name+"</td></tr>");
						out.println("</table>");	
					}
					
					else if(rs.getString(1).equals("CO-APPLICANT")){
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' align='left' ><font size=2>And</td></tr>");
						out.println("</table>");	
						out.println("<table border='0' width='80%' class='table'>"); 		
						out.println("<tr><td width='*%' class='rep-body1' align='left'><font size=2>"+m_full_name+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' align='left'><font size=2>"+m_add1+", "+m_add2+"</td></tr>");
						out.println("<tr><td width='*%' class='rep-body1' align='left'><font size=2>"+m_city_name+"</td></tr>");
						out.println("</table>");	
					}
					more = rs.next();		
				}
				
				//================================================================================
				
				out.println("<br><br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr><td width='*%' class='rep-body1' ><font size=2>Dear Sir/Madam,</td></tr>");
				out.println("</table>");
				
				out.println("<br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				
				out.println("<tr><td width='100%' colspan='2' class='rep-body1' ><font size=2><b><u>Terms and Conditions of the Financial Facility No  "+m_finance_no+"</u></b></td>");
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("<br>");
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>As per request we are pleased to offer you the under mentioned facility subject to the Terms and conditions contained below; </td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("<br>");	

				String contract_info = " "+					
								" SELECT     SUM(NVL(B.FINANCED_AMOUNT,0)+NVL(B.CHARGES,0)+NVL(B.MAINTENANCE,0)) FINANCE_AMOUNT, "+
										   " SUM(GRENTAL_AMOUNT) GRENTAL_AMOUNT, "+
							               " C.RATE RATE, "+
							               " C.PERIOD PERIOD, "+
										   " (C.RATE / C.PERIOD)  RATE_PER_MONTH "+ // added by udara 02-11-2018
											 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B , "+m_schema_name+".AF_CO_PRO_APP_PRICING C "+
											 " WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')  "+  
											 " AND   A.APPLICATION_NO=B.APPLICATION_NO "+
											 " AND   A.PRO_INVOICE_NO=B.INVOICE_NO "+
											 " AND   A.PRICING_NO=B.PRICING_NO "+
				                             " AND   B.PRICING_NO = C.PRICING_NO "+
											 " AND   B.ACTIVE_STATUS='Y'  "+
											 " AND   A.INSTALLMENT_NO <> 0   "+ 
											 " AND   ROWNUM = 1 "+
											 " GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE , C.RATE, C.PERIOD  "+ 
											 " ORDER BY TO_NUMBER(INSTALLMENT_NO) "+			
										" ";

				
				double m_finance_amount = 0;
				double m_grental_amount = 0;
				double m_pricing_rate = 0;
				int    m_pricing_period = 0;
				String m_str_finance_amount = "";
				String m_str_grental_amount = "";
				double m_pricing_rate_per_month = 0;
				
				rs = stmt.executeQuery(contract_info);
				
				if(rs.next()){
					 m_finance_amount = rs.getDouble("FINANCE_AMOUNT");
				 	 m_grental_amount = rs.getDouble("GRENTAL_AMOUNT");
				 	 m_pricing_rate   = rs.getDouble("RATE");
				 	 m_pricing_period = rs.getInt("PERIOD");
					 m_str_finance_amount = rs.getString("FINANCE_AMOUNT");
					 m_str_grental_amount = rs.getString("GRENTAL_AMOUNT");
					 m_pricing_rate_per_month = rs.getDouble("RATE_PER_MONTH"); 
				}
				
				
				String m_str_finance_amount_word = m_sn_methods.numbersToChar(m_str_finance_amount).toUpperCase();
				String m_str_rental_amount_word  = m_sn_methods.numbersToChar(m_str_grental_amount).toUpperCase();
				
				out.println("<table border='1' width='80%' class='table' >"); 
		
				out.println("<tr>");
				out.println("    <td width='20%' ><font size=2> Amount </font></td>");
				out.println("    <td width='60%' ><font size=2> Rupees "+m_str_finance_amount_word+" only </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' > &nbsp; </td>");
				out.println("    <td width='60%' ><font size=2> (Rs. "+nf.format(m_finance_amount)+") </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' ><font size=2> Rate of Interest </font></td>");
				out.println("    <td width='60%' ><font size=2> ");
	            out.println("               "+nf.format(m_pricing_rate_per_month)+"% per month on the amount outstanding in the event of your failure to settle");
                out.println("                any liability within the stipulated period on overdue interest of "+nf.format(odi_rate)+"% per month");
				out.println("                on the the total due will be levied from you.");
                out.println("         </font>");
				out.println("    </td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' ><font size=2> Repayment </font></td>");
				out.println("    <td width='60%' ><font size=2> to be settled in "+m_pricing_period+" (Months) monthly installment of </font></td>");			
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' > &nbsp; </td>");
				out.println("    <td width='60%' ><font size=2> Rupees "+m_str_rental_amount_word+"  </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' > &nbsp; </td>");
				out.println("    <td width='60%' ><font size=2> (Rs. "+nf.format(m_grental_amount)+") </font></td>");				
				out.println("</tr>");
				
				// added by udara 02-11-2018
				out.println("<tr>");
				out.println("    <td width='20%' > Security </td>");
				out.println("    <td width='60%' ><font size=2> 1. A duly signed promissory Note </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' > &nbsp; </td>");
				out.println("    <td width='60%' ><font size=2> 2. Duly signed letter of set off and Lien over Vehicle </font></td>");				
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("    <td width='20%' > &nbsp; </td>");
				out.println("    <td width='60%' ><font size=2> 3. Two personal Guarantees </font></td>");				
				out.println("</tr>");
				// end by udara 02-11-2018
				
				out.println("</table>");
				
				// added by udara 02-11-2018
				out.println("<br>");
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>The above facility will be made available to you only on completion of the above security Documents. "+
									" Also note that as per our normal practice all advances allowed are repayable on demand. "+
									" In the event that you are agreeable please sign and return the duplicate copy of this letter confirming "+
									" your acceptance of the aforementioned terms and conditions. "+
									" We look forward to a pleasant business relationship. </td>");
				out.println("</tr>");
				
				out.println("</table>");
				out.println("<br>");
				
				

				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("Yours faithfully,");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<br><br>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("....................");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("<b>"+m_company_name+"</b>");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				
				out.println("<hr>");
					
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("ACCEPTANCE");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
					
				out.println("<br>");
					
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("I hereby accept the above terms and conditions.");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
					
				out.println("<br><br>");
					
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("Signature:");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
					
				out.println("<br><br>");
					
				out.println("<table border='0' width='80%' class='table'>"); 		
				out.println("<tr>");
				out.println("<td width='95%' colspan='2' class='rep-body1' ><font size=2>");
				out.println("NIC No : "+m_nic_no+" ");
			    out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				// end by udara 02-11-2018
				
				


				out.println("</font></p></blockquote>");
				
				out.println("<blockquote><blockquote><font size=2><p style='text-align:justify' class='rep-body1'>");		

				
				//out.println("</font></p></blockquote></blockquote>"); // commented by udara 10-11-2016
				out.println("</font>"); // added by udara 10-11-2016
			
				//out.println("<br><br>"); // commented by udara 16-11-2015
				out.println("<br>"); // added by udara 16-11-2015
				

				
				out.println("</p></blockquote></blockquote>"); // added by udara 10-11-2016
				
				// end by udara on 02-01-2013

				
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
