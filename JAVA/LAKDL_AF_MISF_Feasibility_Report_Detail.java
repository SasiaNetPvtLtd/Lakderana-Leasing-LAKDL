import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : NUWAN DE SILVA    DATE:12-08-07 on 10.40am

public class LAKDL_AF_MISF_Feasibility_Report_Detail extends javax.servlet.http.HttpServlet {
	
	// commented by udara 18-05-2017
	/*
	
	Connection conn;
	Statement stmt_2,stmt,stmt_invoice,stmt_rental,stmt_pricing,stmt_charges;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    java.lang.Math a;

    
    // public ResultSet rs1,rs_doc_charge;
    public ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;

	public String m_chksql;
	
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // commented by udara 14-03-2019
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // added by udara 14-03-2019
		
		// added by udara 18-05-2017
		Connection conn = null;
		Statement stmt_2= null,stmt= null,stmt_invoice= null,stmt_rental= null,stmt_pricing= null,stmt_charges= null;
		CallableStatement callstmt= null;
		java.text.NumberFormat nf= null,nf1= null;
		java.lang.Math a= null;
		ResultSet rs= null,rs2= null,rs3= null,rs_rental= null,rs_pricing= null,rs_charges= null;
		String m_chksql= null;
		// end by udara 18-05-2017
		
		try {
			
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			m_chksql=req.getParameter("chksql");
			
			stmt_invoice=conn.createStatement();
			stmt_pricing=conn.createStatement();
			stmt=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_rental=conn.createStatement();
			stmt_charges = conn.createStatement ();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
				//out.println("sfsdsd");
				
				String m_finance_no=req.getParameter("finance_no").trim();
				String m_print=req.getParameter("print").trim();
				
				String m_client_code="";
				String m_application_no="";
				String m_client_nic="";
				String m_client_name="";
				
				String m_insurance_done_by = "";
				
				
				rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY'),"+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"') FROM DUAL ");
				rs.next();
				String m_date=rs.getString(1);
				String m_user=rs.getString(2);
				
				//rs.close();
				
				//String Sql_client_data="SELECT DISTINCT A.APPLICATION_NO, "+//1 // commented by udara 14-03-2019
				String Sql_client_data="SELECT A.APPLICATION_NO, "+//1 // added by udara 14-03-2019
					" A.CLIENT_CODE, "+//2
					" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NIC(A.CLIENT_CODE),'-'), "+ //3
					" UPPER(DECODE(B.CLIENT_TYPE,'I',INITCAP(B.TITLE) || '. ' || B.FULL_NAME,'C','Mess' || '. '  || B.FULL_NAME)), "+ //4
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS1,'C',B.ADDRESS1),'-')), "+//5 //B.REGISTERED_ADDRESS1
					" UPPER(NVL(DECODE(B.CLIENT_TYPE,'I',B.ADDRESS2,'C',B.ADDRESS2),'-')), "+//6 // B.REGISTERED_ADDRESS2
					" UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),' ')) CITY_NAME ,"+ //6
					//" NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//7 comment and add by malik on 26-8-2008//6 comment and add by malik on 26-8-2008
					//"NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess'),'-') "+//7
					" NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO),'ACTIVATED','ACTIVATED',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'ENTERED','ENTERED','ENT_CON','COMPLETED','VERIFY1','CREDIT VERIFICATION','V-APP','CREDIT SCORE APPROVAL','VERIFY-M','CREDIT APPROVAL 1','VERIFY2','CREDIT APPROVAL 2','VERIFYL','ENTERED LEASING NO'),'-') "+//add by malik on 7-10-2008 Modified by Dineth on 15-06-2009
					" ,INSURANCE_DONE_BY "+ // 9 added by udara 29-05-2014
					
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE AND "+
					//" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') "; // commented by udara 14-03-2019
					" A.FINANCE_NO=UPPER('"+m_finance_no+"') "; // added by udara 14-03-2019
				
				
				out.println("<HTML><HEAD><TITLE>Offer Letter</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				
				out.println("<script>");
				out.println("</script>");
				
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >"); //onLoad=\"add_button()\"
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Offer Letter for Finance No - "+m_finance_no+"  </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				
				//out.println("ERR 1");
				
				rs=stmt.executeQuery(Sql_client_data);
				
				//out.println("ERR 2");
				
				boolean more=rs.next();
				if(more)
				{
					m_application_no=rs.getString(1);
					m_client_code=rs.getString(2);
					m_client_name=rs.getString(4);
					m_insurance_done_by = rs.getString(9); // added by udara 29-05-2014
					m_client_nic= rs.getString(3);
					
				}
				//rs.close();
				
				//out.println("ERR 3");
				
				
				String		Sql_net_Rent=" SELECT "+
					" APPLICATION_NO, "+
					" NET_RENTAL_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					" APPLICATION_STATUS <>'CANCEL' "+
					"  ) "+
					"  AND INSTALLMENT_NO <> 0 "+ // added by udara 22-03-2017
					" GROUP BY   APPLICATION_NO,NET_RENTAL_AMOUNT      ";                    
				
				rs=stmt.executeQuery(Sql_net_Rent);
				more =rs.next();
				double net_rental_amt=0;				
				if(more){
					net_rental_amt=rs.getDouble(2);
				}
				
				//out.println("ERR 4");
				
				
				
				// added by udara 26-01-2015
				rs=stmt.executeQuery(" "+
					" SELECT AMOUNT, PAYEE_CODE, PAYEE_NAME "+
					" FROM ( "+
					" SELECT E.PREMIUM AMOUNT, "+
					" E.INSUR_COM PAYEE_CODE,	"+	
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(E.INSUR_COM),'-') PAYEE_NAME "+
					" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E,  "+m_schema_name+".AF_CO_PRO_INVOICE B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
					" WHERE E.FINANCE_NO = '"+m_finance_no+"' "+
					" AND E.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
					" AND E.PRO_INVOICE_NO = C.INVOICE_NO  "+
					" ORDER BY E.ENT_DATE ASC "+
					" ) "+
					" WHERE ROWNUM = 1 "+
					" ");
				
				
				more =rs.next();
				double insu_amt=0;	
				double sum_insu_amt=0;	
				
				double m_stamp_duty=0; // added by udara 21-08-2014
				
				String insu_company = "";
				
				if(more){
					
					insu_amt=rs.getDouble(1);
					//insu_company=rs.getString(2);
					insu_company=rs.getString(3);
					
				}
				
				//out.println("ERR 5");
				
				// end by udara 21-02-2014
				
				
				// added by udara 04-06-2014
				if(insu_company==null || insu_company.equals("")){
					
					rs=stmt.executeQuery(" "+
						" SELECT  SUM_INSSURED,PAYEE_NAME FROM (   "+
						" SELECT NVL(E.SUM_INSSURED,0) SUM_INSSURED, NVL(E.INSUR_COM,'-') INSUR_COM , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(E.INSUR_COM),'-') PAYEE_NAME "+
						" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E "+
						" WHERE E.FINANCE_NO = '"+m_finance_no+"' "+
						//" ORDER BY E.ENT_DATE  DESC "+ // commented by udara 30-03-2017
						" ORDER BY E.ENT_DATE  ASC "+ // added by udara 30-03-2017
						" ) "+
						"  WHERE ROWNUM = 1 "+
						" ");
					
					//rs = stmt.executeQuery(Sql_broker);
					
					more = rs.next();	
					
					if(more)
					{
						sum_insu_amt=rs.getDouble(1);
						insu_company=rs.getString(2);
					}
					
				}
				
				// end by udara 04-06-2014
				
				//out.println("ERR 6");
				
				
				// added by udara 21-08-2014
				rs=stmt.executeQuery(" "+
					//out.println(" "+
					//" SELECT NVL(SUM(TOTAL_AMOUNT),0) "+
					" SELECT NVL(SUM(NVL(TOTAL_AMOUNT,0)),0) "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO = '"+m_finance_no+"' "+
					" AND ACTIVE_STATUS = 'Y' "+ // added by udara 22-06-2015
					//" AND INVOICE_TYPE IN (SELECT TYPE_CODE  "+
					" AND INVOICE_TYPE IN (SELECT SUB_TYPE_CODE  "+
					" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
					" WHERE SUB_TYPE_CODE = 'STDUTY' "+ 
					" ) "+
					" ");
				
				more = rs.next();
				
				if(more){
					m_stamp_duty = rs.getDouble(1); // m_stamp_duty = rs.getDouble(2);
				}
				
				// end by udara 21-08-2014
				
				
				// added by udara 03-08-2015
				try{
					if(m_stamp_duty==0){
						
						rs=stmt.executeQuery(" "+					
							" SELECT NVL(SUM(AMOUNT),0)  "+
							" FROM( "+
							" SELECT   "+
							" B.PRICING_NO PRICING_NO,  "+  
							" B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
							" C.DESCRIPTION DESCRIPTION,  "+  
							" SUM(D.AMOUNT) AMOUNT ,  "+
							" B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
							" 'CHARGES' CHARGES  "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
							" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
							" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+  
							" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
							" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
							" AND B.PRICING_NO = D.PRICING_NO  "+
							" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
							//" AND C.SUB_TYPE_CODE IN ('SERVICECHR','6M','PRVCHNG') "+ // TRANS
							" AND C.SUB_TYPE_CODE IN ('STDUTY') "+ // TRANS
							" AND NVL(B.AMOUNT,0) <> 0  "+
							" GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION "+			 
							" ) "+
							" ORDER BY CHARGES,DESCRIPTION "+
							" ");
						
						more = rs.next();
						
						if(more){
							m_stamp_duty = rs.getDouble(1); 
						}
						
					}
				}
				catch(Exception eeee){
					out.println(eeee.toString());
				}
				
				// end by udara 03-08-2015
				
				
				
				
				// added by udara 20-05-2015
				rs=stmt.executeQuery(" "+					
					" SELECT NVL(SUM(AMOUNT),0)  "+
					" FROM( "+
					" SELECT   "+
					" B.PRICING_NO PRICING_NO,  "+  
					" B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
					" C.DESCRIPTION DESCRIPTION,  "+  
					" SUM(D.AMOUNT) AMOUNT ,  "+ // commented by udara 20-08-2015
					//" SUM(B.AMOUNT) AMOUNT ,  "+ // added by udara 20-08-2015
					" B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
					" 'CHARGES' CHARGES  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+  
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					//" AND C.SUB_TYPE_CODE IN ('SERVICECHR','6M','PRVCHNG') "+ // TRANS
					//" AND C.SUB_TYPE_CODE IN ('SERVICECHR') "+ // TRANS
					" AND C.SUB_TYPE_CODE IN ('SERVICECHR','SCC4') "+ // added by udara 03-08-2015
					" AND NVL(B.AMOUNT,0) <> 0  "+
					" GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION "+			 
					" ) "+
					" ORDER BY CHARGES,DESCRIPTION "+
					
					
					" ");
				
				more =rs.next();
				double initial_charge=0;
				
				if(more){
					initial_charge=rs.getDouble(1);
				}
				// added by udara 22-10-2014
				
				
				
				// added by udara 230-05-2015
				rs=stmt.executeQuery(" "+					
					" SELECT NVL(SUM(AMOUNT),0)  "+
					" FROM( "+
					" SELECT   "+
					" B.PRICING_NO PRICING_NO,  "+  
					" B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
					" C.DESCRIPTION DESCRIPTION,  "+  
					" SUM(D.AMOUNT) AMOUNT ,  "+
					" B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
					" 'CHARGES' CHARGES  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+  
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					//" AND C.SUB_TYPE_CODE IN ('SERVICECHR','6M','PRVCHNG') "+ // TRANS
					" AND C.SUB_TYPE_CODE IN ('PRVCHNG','SCC1') "+ // TRANS
					" AND NVL(B.AMOUNT,0) <> 0  "+
					" GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION "+			 
					" ) "+
					" ORDER BY CHARGES,DESCRIPTION "+
					
					
					" ");
				
				
				
				more =rs.next();
				double province_charge=0;
				
				if(more){
					province_charge=rs.getDouble(1);
				}
				
				
				
				// added by udara 20-05-2015
				rs=stmt.executeQuery(" "+					
					" SELECT NVL(SUM(AMOUNT),0)  "+
					" FROM( "+
					" SELECT   "+
					" B.PRICING_NO PRICING_NO,  "+  
					" B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
					" C.DESCRIPTION DESCRIPTION,  "+  
					" SUM(D.AMOUNT) AMOUNT ,  "+
					" B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
					" 'CHARGES' CHARGES  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+  
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					//" AND C.SUB_TYPE_CODE IN ('SERVICECHR','6M','PRVCHNG') "+ // TRANS
					" AND C.SUB_TYPE_CODE IN ('6M','SCC2') "+ // TRANS
					" AND NVL(B.AMOUNT,0) <> 0  "+
					" GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION "+			 
					" ) "+
					" ORDER BY CHARGES,DESCRIPTION "+
					
					
					" ");
				
				more =rs.next();
				double sixmonth_charge=0;
				
				if(more){
					sixmonth_charge=rs.getDouble(1);
				}
				
				
				
				// added by udara 20-05-2015
				rs=stmt.executeQuery(" "+					
					" SELECT NVL(SUM(AMOUNT),0)  "+
					" FROM( "+
					" SELECT   "+
					" B.PRICING_NO PRICING_NO,  "+  
					" B.SUB_CHAGE_CODE SUB_CHAGE_CODE,  "+  
					" C.DESCRIPTION DESCRIPTION,  "+  
					" SUM(D.AMOUNT) AMOUNT ,  "+
					" B.PRO_INVOICE_NO PRO_INVOICE_NO,  "+
					" 'CHARGES' CHARGES  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES B ,  "+
					" "+m_schema_name+".AF_CO_MAS_SUB_CHARGES C, AF_MK_PRO_PRICING_CHARGES D  "+
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO  "+  
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE   "+
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					//" AND C.SUB_TYPE_CODE IN ('SERVICECHR','6M','PRVCHNG') "+ // TRANS
					" AND C.SUB_TYPE_CODE IN ('TRANS','SCC3') "+ // TRANS
					" AND NVL(B.AMOUNT,0) <> 0  "+
					" GROUP BY B.PRICING_NO,B.SUB_CHAGE_CODE,C.DESCRIPTION,B.PRO_INVOICE_NO ORDER BY  C.DESCRIPTION "+			 
					" ) "+
					" ORDER BY CHARGES,DESCRIPTION "+
					
					
					" ");
				
				more =rs.next();
				double transfer_charge=0;
				
				if(more){
					transfer_charge=rs.getDouble(1);
				}
				
				// end by udara 02-12-2014
				
				
				
				// added by udara 29-08-2017
				
				// RMV
				
				double rmv_charge = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N17713_1' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					rmv_charge=rs.getDouble(1);
				}
				
				
				// VALUATION
				
				double valuation_charge = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N17713_2' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					valuation_charge=rs.getDouble(1);
				}
				
				// BROKER FEE
				
				double broker_fee_charge = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N17713_3' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					broker_fee_charge=rs.getDouble(1);
				}
				
				
				double direct_income = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N1794_1' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					direct_income=rs.getDouble(1);
				}
				
				
				// end by udara 29-08-2017
				//[Added by milinda for get PC Charges on 16-11-2017]
				double pc_charge = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N171110' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					pc_charge=rs.getDouble(1);
				}
				
				//[End Milinda]
				
				
				// charges TC added by udara 17-07-2018
				double tc_charge = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N180713' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					tc_charge=rs.getDouble(1);
				}
				
				// end TC added by udara 17-07-2018
				
				// added by udara 03-12-2018
				double gas_charges = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N181203' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					gas_charges=rs.getDouble(1);
				}
				
				double tyre_charges = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N181203_1' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					tyre_charges=rs.getDouble(1);
				}
				
				double helmet_charges = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N181203_3' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					helmet_charges=rs.getDouble(1);
				}
				
				double jacket_charges = 0;
				
				rs=stmt.executeQuery(" "+					
					" SELECT   NVL(SUM(D.AMOUNT),0) "+
					" FROM LAKDL.AF_CO_PRO_APPLICATION_DETAILS A, LAKDL.AF_CO_PRO_APP_PRICING_CHARGES B, "+ 
					" LAKDL.AF_CO_MAS_SUB_CHARGES C, LAKDL.AF_MK_PRO_PRICING_CHARGES D "+ 
					" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+   
					" AND A.FINANCE_NO=UPPER('"+m_finance_no+"')  "+
					" AND B.SUB_CHAGE_CODE=C.SUB_TYPE_CODE "+  
					" AND B.PRICING_NO = D.PRICING_NO  "+
					" AND B.SUB_CHAGE_CODE = D.SUB_CHAGE_CODE   "+
					" AND C.SUB_TYPE_CODE = 'N181203_4' "+
					" AND NVL(B.AMOUNT,0) <> 0 "+
					" ");
				
				more =rs.next();
				
				if(more){
					jacket_charges=rs.getDouble(1);
				}
				// end by udara 03-12-2018
				
				//out.println("ERR 7");
				
				rs=stmt.executeQuery(""+					
					"				SELECT  "+
					"				NVL(SETTELE_AMOUNT,0)  "+
					"				FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
					"				WHERE    "+
					"	            A.FINANCE_NO=UPPER('"+m_finance_no+"') AND "+
					"				A.REMARKS = 'CHARGES - INSURANCE'  ");
				
				more =rs.next();
				double paid_amount=0;				
				if(more){
					
					paid_amount=rs.getDouble(1);
					
				}
				
				// added by udara 05-06-2015
				
				//out.println("chk 1");
				
				double int_paid_amount = 0;	
				
				rs=stmt.executeQuery(" "+					
					"				SELECT  "+
					"				NVL(A.INSURANCE,0)  "+
					"				FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
					"				WHERE    "+
					"	            A.SUS_REF_NO=UPPER('"+m_finance_no+"')  "+
					"	");
				
				if(rs.next()){
					int_paid_amount = rs.getDouble(1);
				}
				
				//out.println("chk 2");
				
				String m_application_status = "";
				
				rs=stmt.executeQuery(" "+					
					"				SELECT A.APPLICATION_STATUS  "+
					"				FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
					"	            WHERE A.FINANCE_NO = UPPER('"+m_finance_no+"')  "+
					"	");
				
				if(rs.next()){
					m_application_status = rs.getString(1);
				}
				
				//out.println("chk 3");
				
				//if( (m_application_status.equals("ENTERED")) || (m_application_status.equals("VERIFY-M")) || (m_application_status.equals("ENT_CON")) || (m_application_status.equals("VERIFYL")) || (m_application_status.equals("V-RECOM")) || (m_application_status.equals("V-APP")) ){
				if(paid_amount==0){
					paid_amount = int_paid_amount;
				}
				
				//out.println("chk 4");
				// end by udara 05-06-2015
				
				
				//out.println("ERR 8");
				
				double sum_insu_2 = 0;
				String coll_securi= "";
				rs=stmt.executeQuery(""+					
					"				SELECT  "+
					"				NVL(SUM_INSURED,0),COLLECTON_SECURITY,"+m_schema_name+".GET_SUM_INSURED('"+m_finance_no+"')  "+   //COLLECTON_SECURITY added by Prabash on 02-05-2012
					"				FROM "+m_schema_name+".AF_CO_SUM_INSURED_DETAILS A "+
					"				WHERE    "+
					"	            A.APPLICATION_NO=UPPER('"+m_application_no+"')  ");
				
				more =rs.next();
				if(more){
					sum_insu_2=rs.getDouble(1);
					coll_securi=rs.getString(2); //added by Prabash on 02-05-2012
				}
				
				//out.println("ERR 9");
				
				
				//=================
				rs=stmt.executeQuery(" "+
					" SELECT  SUM_INSSURED,PAYEE_NAME FROM (   "+
					" SELECT NVL(E.SUM_INSSURED,0) SUM_INSSURED, NVL(E.INSUR_COM,'-') INSUR_COM , NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(E.INSUR_COM),'-') PAYEE_NAME "+
					" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E "+
					" WHERE E.FINANCE_NO = '"+m_finance_no+"' "+
					//" ORDER BY E.ENT_DATE  DESC "+ // commented by udara 30-03-2017
					" ORDER BY E.ENT_DATE  ASC "+ // added by udara 30-03-2017
					" ) "+
					"  WHERE ROWNUM = 1 "+
					" ");
				
				more = rs.next();	
				
				if(more)
				{
					sum_insu_amt=rs.getDouble(1);
					insu_company=rs.getString(2); // released by udara 22-09-2017
				}
				
				
				
				//=================
				
				
				
				double sum_insu_22 = 0;
				
				rs=stmt.executeQuery(""+					
					"				SELECT  "+
					"				"+m_schema_name+".GET_SUM_INSURED('"+m_finance_no+"')  "+   //added milinda
					"				FROM DUAL "+
					"				 ");
				
				more =rs.next();
				if(more){
					sum_insu_22=rs.getDouble(1);
					
				}
				
				//out.println("ERR 10");
				
				double cash_price = 0;
				double market_price = 0;
				
				rs=stmt.executeQuery(""+					
					"				SELECT  "+
					"				NVL(FORCED_SALES_VALUE,0), NVL(VALUE,0)  "+
					"				FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION A "+
					"				WHERE    "+
					"	            A.APPLICATION_NO=UPPER('"+m_application_no+"')  ");
				
				more =rs.next();
				if(more){
					
					cash_price=rs.getDouble(1);
					market_price=rs.getDouble(2);
				}
				
				
				
				//out.println("ERR 11");
				
				
				double net_amount = 0;
				int period = 0;
				String vendor_nic= "";
				String vendor_name= "";
				String indroducer="";
				String vehicle="";
				double sum_insu=0;
				String com_date = "";
				String prepared_user = ""; // added by udara on 14-10-2013
				
				String Sql_prising_data="SELECT  "+
					" NVL(E.NET_AMOUNT,0),"+ //1
					" NVL(E.PERIOD,0), "+ //2
					" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NIC(A.VENDOR_CODE),'-'), "+ //3
					" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(A.VENDOR_CODE),'-'), "+ //4
					" NVL((SELECT NVL(FIRST_NAME || ' ' || LAST_NAME,'-') FROM "+m_schema_name+".AF_CO_MAS_BROKER WHERE BROKER_CODE=G.LEAD_SOURCE_NAME ),G.LEAD_SOURCE_NAME) BROKER_CODE , "+ //5 //NVL(G.LEAD_SOURCE_NAME,'-')
					" TO_CHAR(G.ENT_DATE,'DD-MM-YYYY'), "+ //6
					" A.CHASSIS_NO || '-' || A.ENGINE_NO || '-' || A.COLOUR || '-' || A.REG_NO, "+ //7
					//" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(G.COLLECTION_OFFICER),'-') "+ // 8 added by udara on 14-10-2013
					
					// mod by udara 16-10-2013
					" (SELECT NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(MK_OFFICER),'-') FROM "+
					" "+m_schema_name+".AF_MK_PRO_INQUIRY "+
					" WHERE INQUIRY_CODE IN  "+
					" (SELECT INQUARY_NO  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
					" WHERE FINANCE_NO='"+m_finance_no+"' )) MK_OFFICER "+
					
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B, "+
					" "+m_schema_name+".AF_CO_MAS_MODEL C,"+m_schema_name+".AF_CO_MAS_MAKE D, "+
					" "+m_schema_name+".AF_CO_PRO_APP_PRICING E ,"+
					" "+m_schema_name+".AF_CO_MAS_SUB_MODLE F, "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS G "+
					" WHERE G.FINANCE_NO='"+m_finance_no+"'  AND "+
					" A.ASSET_ID=B.ASSET_ID AND "+
					" A.APPLICATION_NO=E.APPLICATION_NO AND "+
					" A.APPLICATION_NO=G.APPLICATION_NO AND "+ 
					" A.INVOICE_NO=E.PRO_INVOICE_NO AND  "+
					" C.MODEL_CODE=A.MODEL_CODE AND "+
					" C.MAKE_CODE=D.MAKE_CODE AND "+
					" A.ACTIVE_STATUS='Y' AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					//" C.ACTIVE_STATUS='Y' AND  "+ // commented by udara on 07-08-2013
					" A.SUB_MODEL_CODE=F.SUB_CODE AND "+
					" D.ACTIVE_STATUS='Y'   ";
				
				rs=stmt.executeQuery(Sql_prising_data);
				boolean more11=rs.next();
				if(more11)
				{
					net_amount = rs.getDouble(1);
					period = rs.getInt(2);
					vendor_nic=rs.getString(3);
					vendor_name=rs.getString(4);
					indroducer=rs.getString(5);
					vehicle=rs.getString(7);
					sum_insu=rs.getDouble(1);
					com_date = rs.getString(6);
					
					prepared_user = rs.getString(8); // added by udara on 14-10-2013
					
				}
				
				
				// added by udara 20-03-2017
				double m_down_payment = 0;
				
				String Sql_down_payment="  "+
					" select CAPITAL_AMOUNT "+
					" from "+m_schema_name+".af_co_pro_app_installment "+
					" where application_no='"+m_application_no+"' "+
					" and INSTALLMENT_NO = 0 "+
					" ";
				
				rs=stmt.executeQuery(Sql_down_payment);
				
				if(rs.next()){
					m_down_payment = rs.getDouble(1);
				}
				
				net_amount = net_amount - m_down_payment;
				
				String Sql_period_count="  "+
					" SELECT "+
					" COUNT(INSTALLMENT_NO) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
					" WHERE APPLICATION_NO =UPPER('"+m_application_no+"') "+
					" AND TO_NUMBER(INSTALLMENT_NO) <> 0  "+
					" ";
				rs=stmt.executeQuery(Sql_period_count);
				
				if(rs.next()){
					period = rs.getInt(1);
				}
				
				// end by udara 20-03-2017
				
				
				//out.println("ERR 12");
				
				//rs.close();
				
				out.println("<table align='center' width='100%' class='table' >");	
				out.println("<tr valign='top'>");
				out.println("<td>");
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(01)</b></td>"); 
				out.println("<td width='20%' ><b>Contract No</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left'  ><DIV class=div_input>"+m_finance_no+"</DIV></td>"); //onClick=\"show_finance_detail_drill('"+m_finance_no+"')\" style='cursor:hand'
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(02)</b></td>"); 
				out.println("<td width='20%' ><b>Client Name</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left'  ><DIV class=div_input>"+m_client_name+'-'+m_client_nic+"</DIV></td>"); //onClick=show_client('"+m_client_code+"') style='cursor:hand'
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(03)</b></td>"); 
				out.println("<td width='20%' ><b>Credit Value</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(net_amount)+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(04)</b></td>"); 
				out.println("<td width='20%' ><b>Period</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+period+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(05)</b></td>"); 
				out.println("<td width='20%' ><b>Rental</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(net_rental_amt)+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(06)</b></td>"); 
				//out.println("<td width='20%' ><b>Initial Charges</td>"); // commented by udara 02-12-2014
				//out.println("<td width='20%' ><b> Initial + Province + 6 Month + Transfer </td>"); // commented by udara 29-08-2017
				//out.println("<td width='20%' ><b> Initial + Province + 6 Month + Transfer + Broker fee + Valuation + RMV + Direct Income + PC + TC</td>"); // commented by udara 03-12-2018 // added by udara 29-08-2017 PC Charge added by milinda for#JB10112017-01665
				out.println("<td width='20%' ><b> Initial + Province + 6 Month + Transfer + Broker fee + Valuation + RMV + Direct Income + PC + TC + Gas + Tyre + Helmet + Jacket </td>");
				out.println("<td width='2%'>:</td>"); 
				//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(initial_charge)+"</DIV></td>");
				//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(initial_charge)+" + "+nf.format(province_charge)+" + "+nf.format(sixmonth_charge)+" + "+nf.format(transfer_charge)+" = "+nf.format(initial_charge + province_charge +sixmonth_charge + transfer_charge)+" </DIV></td>"); // commented by udara 29-08-2017
				//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(initial_charge)+" + "+nf.format(province_charge)+" + "+nf.format(sixmonth_charge)+" + "+nf.format(transfer_charge)+" + "+nf.format(broker_fee_charge)+" + "+nf.format(valuation_charge)+" + "+nf.format(rmv_charge)+" + "+nf.format(direct_income)+" + "+nf.format(pc_charge)+" + " +nf.format(tc_charge)+" = "+nf.format(initial_charge + province_charge +sixmonth_charge + transfer_charge + broker_fee_charge + valuation_charge + rmv_charge + direct_income + pc_charge + tc_charge)+" </DIV></td>"); // commented by udara 03-12-2018 // added by udara 29-08-2017[pc_charge Added by milinda on 16-11-2017] // tc_charge by udara 17-07-2018
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(initial_charge)+" + "+nf.format(province_charge)+" + "+nf.format(sixmonth_charge)+" + "+nf.format(transfer_charge)+" + "+nf.format(broker_fee_charge)+" + "+nf.format(valuation_charge)+" + "+nf.format(rmv_charge)+" + "+nf.format(direct_income)+" + "+nf.format(pc_charge)+" + " +nf.format(tc_charge)+" + " +nf.format(gas_charges)+" + " +nf.format(tyre_charges)+" + " +nf.format(helmet_charges)+" + " +nf.format(jacket_charges)+" = "+nf.format(initial_charge + province_charge +sixmonth_charge + transfer_charge + broker_fee_charge + valuation_charge + rmv_charge + direct_income + pc_charge + tc_charge + gas_charges + tyre_charges + helmet_charges + jacket_charges)+" </DIV></td>"); // added by udara 03-12-2018
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				// added by udara 21-08-2014
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(07)</b></td>"); 
				out.println("<td width='20%' ><b>Stamp Duty</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input> "+nf.format(m_stamp_duty)+" </DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				// end by udara 21-08-2014
				
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(08)</b></td>"); 
				out.println("<td width='20%' ><b>Vendor Name</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+vendor_name+'-'+vendor_nic+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(09)</b></td>"); 
				out.println("<td width='20%' ><b>Introducer</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+indroducer+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(10)</b></td>"); 
				out.println("<td width='20%' ><b>Vehicle Details</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+vehicle+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(11)</b></td>"); 
				out.println("<td width='20%' ><b>Security Details</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>  "+coll_securi+"</DIV></td>");  //coll_securi added by prabash on 03-05-2012
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				if(!m_insurance_done_by.equals("CLIENT")){ // add condition udara 29-05-2014 // cilent lessee
					
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'><b>(12)</b></td>"); 
					out.println("<td width='20%' ><b>Insurance</td>");
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+insu_company+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					
					out.println("</tr>"); 
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Sum Insurance</td>");//1
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(sum_insu_amt)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Insurance Amount</td>");
					out.println("<td width='2%'>:</td>"); 
					//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(sum_insu_2)+"</DIV></td>");
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(sum_insu_22)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
				}
				else{
					
					// udara 04-06-2014
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'><b>(12)</b></td>"); 
					out.println("<td width='20%' ><b>Insurance</td>");
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+insu_company+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					
					out.println("</tr>"); 
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Sum Insurance</td>");//2
					out.println("<td width='2%'>:</td>"); 
					//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(insu_amt)+"</DIV></td>"); // commented by udara 01-09-2014
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(sum_insu_amt)+"</DIV></td>"); // added by udara 01-09-2014
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					
					/*
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'><b>(11)</b></td>"); 
					out.println("<td width='20%' ><b>Insurance</td>");
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input> - </DIV></td>");
					out.println("<td width='*%'></td>"); 
					
					out.println("</tr>"); 
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Sum Insurance</td>");
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(0)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					*/
					
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Insurance Amount</td>");
					out.println("<td width='2%'>:</td>"); 
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(0)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
				}
				
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'></td>"); 
				out.println("<td width='20%' ><b>Paid Amount</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(paid_amount)+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				
				// commented by udara 06-06-2014
				/*
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'></td>"); 
				out.println("<td width='20%' ><b>Balance</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(insu_amt-paid_amount)+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				*/
				
				// added by udara 06-06-2014
				if(!m_insurance_done_by.equals("CLIENT")){ 
					
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Balance</td>");
					out.println("<td width='2%'>:</td>"); 
					//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(0)+"</DIV></td>");
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(insu_amt-paid_amount)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
				}
				else{
					
					out.println("<tr height='25px'>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='4%'></td>"); 
					out.println("<td width='20%' ><b>Balance</td>");
					out.println("<td width='2%'>:</td>"); 
					//out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(insu_amt-paid_amount)+"</DIV></td>");
					out.println("<td width='50%' align='left' ><DIV class=div_input>"+nf.format(0)+"</DIV></td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
				}
				// end by udara 06-06-2014
				
				
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(13)</b></td>"); 
				out.println("<td width='20%' ><b>Signature of Hirer</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>...................................</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(14)</b></td>"); 
				out.println("<td width='20%' ><b>Prepared By</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+prepared_user+"</DIV></td>"); // m_user // mod by udara on 14-10-2013
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(15)</b></td>"); 
				out.println("<td width='20%' ><b>Commencement Date</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>"+com_date+"</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr height='25px'>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='4%'><b>(16)</b></td>"); 
				out.println("<td width='20%' ><b>Approved By</td>");
				out.println("<td width='2%'>:</td>"); 
				out.println("<td width='50%' align='left' ><DIV class=div_input>...................................</DIV></td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</td>");
				out.println("<td>");
				out.println("<table align='center' width='100%' class='table' border=1 >");						
				out.println("<tr >");
				out.println("<td width='50%' ><b>Cash Price</td>");
				out.println("<td width='50%' ><b>Market Price</td>");
				out.println("</tr>"); 
				out.println("<tr >");
				out.println("<td width='50%' align='right'  ><DIV class=div_input>"+nf.format(cash_price)+"</DIV></td>"); 
				out.println("<td width='50%' align='right'  ><DIV class=div_input>"+nf.format(market_price)+"</DIV></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</td>");
				
				out.println("</tr>");
				out.println("</table>"); 
				out.println("<br>");
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
				
			}
			
			
			else {
				out.println("Undefined");
			}
			
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(rs3!=null){try{rs3.close();  }catch(Exception e){}}
			if(rs_rental!=null){try{rs_rental.close();  }catch(Exception e){}}
			if(rs_pricing!=null){try{rs_pricing.close();  }catch(Exception e){}}
			if(rs_charges!=null){try{rs_charges.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt_2!=null){try{stmt_2.close();  }catch(Exception e){}}
			if(stmt_invoice!=null){try{stmt_invoice.close();  }catch(Exception e){}}
			if(stmt_rental!=null){try{stmt_rental.close();  }catch(Exception e){}}
			if(stmt_pricing!=null){try{stmt_pricing.close();  }catch(Exception e){}}
			if(stmt_charges!=null){try{stmt_charges.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
			
			
			out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}


