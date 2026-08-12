import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

//DEVELOPED BY MAHELA FOR LEASING ON 22-03-2007
//NEW RECEIPT FORMAT CREATED BY CHANDANA ON 15/10/2007 
//NEW RECEIPT FORMAT CREATERD BY KANISHKA ON 21-06-2013

public class LAKDL_AF_RE_Receipt_Document extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
	CallableStatement callstmt1;
	java.text.NumberFormat nf;
	
	public ResultSet rs;
	public ResultSet rs1,rs2,rs3,rs4,rs5,rs6;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date;
	public String m_c_code,m_add1,m_add2,m_name,m_city_desc,m_due_date;
	public String m_receipt_no,m_client_no,m_no_of_due_date,m_finance_no;
	public String m_print,m_inv_type,m_inv_no,m_vat_reg_no,m_vat_reg_date,m_value_date;
	public double m_amount_due;
	public double m_gross_rent;
	public String m_LAKDL_vat_no="",m_email="";
	public String m_vat_precentage="";
	
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username.trim();
			int m_count=0;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_loc_receipt_no=""; //added by Prabash on 18-05-2012
			String m_reg_no=""; //added by Prabash on 21-05-2012
			String m_application_no =""; //added by Prabash on 21-05-2012
			double m_arreas= 0; //added by Prabash on 22-05-2012
			String m_installment_no= ""; //added by Prabash on 21-05-2012
			String m_slab = ""; //added by Prabash on 22-05-2012
			double m_rental_amount = 0; //added by Prabash on 22-05-2012
			String m_sysdate =""; //added by Prabash on 22-05-2012
			String m_comments="",m_payer_branch="",m_cheque_cash_type="";
			
			m_chksql = req.getParameter("chksql");
			m_receipt_no = req.getParameter("receipt_no");		
			m_client_no = req.getParameter("client_no");		
			m_print = req.getParameter("print");
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			
			//modified by nuwan de silva ---------23-07-07----------------------
			rs = stmt.executeQuery(" SELECT "+
				" UPPER(NVL(COMPANY_NAME,' ')), "+
				" INITCAP(NVL(ADDRESS1,' ')), "+
				" INITCAP(NVL(ADDRESS2,' ')), "+
				" INITCAP(NVL(CITY,' ')), "+
				" NVL(TEL_NO,' '), "+
				" NVL(FAX_NO,' '),  "+
				" NVL(VAT_RATE,0), "+
				" NVL(VAT_REG_NO,' '), "+
				" NVL(EMAIL, ' ') "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			boolean  more = rs.next();		
			
			if(more)
			{
				m_orient_name=rs.getString(1);
				m_orient_add1=rs.getString(2);
				m_orient_add2=rs.getString(3);
				m_orient_city_name=rs.getString(4);
				m_orient_tel_no=rs.getString(5);
				m_orient_fax_no=rs.getString(6);
				m_vat_precentage=rs.getString(7);			
				m_LAKDL_vat_no=rs.getString(8);			
				m_email=rs.getString(9);			
			}
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}					
			else if (m_chksql.trim().equals("main_page")) {
				
				out.println("<html><head>"); 
				out.println("<title>Receipt Document</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");			
				out.println("<script>");
				out.println("function save_data(){");
				out.println("m_table.innerHTML=\"\" ");
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Collection_Receipt_Document?chksql=save_page&receipt_no="+m_receipt_no+"&client_no="+m_client_no+"&scr_name=AF_RE_SETTELMENT\";"); 
				out.println(" window.location.href=m_url;"); 
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
				out.println("</script>");
				
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
				out.println("<body bgcolor='white'>");	
				out.println("<form name='Form1'>");				
				
				
				/*	 comment by Prabash on 18-05-2012----*
				rs = stmt.executeQuery ("SELECT TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),NVL(OTH_COMMENTS,'-') ,NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-') ,NVL(THIRD_PARTY_NAME,'-'), NVL(THIRD_PARTY_ADDRESS,'-') FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
									" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");							
				*/ 	//-----------------------------------*
				
				//added by Prabash on 16-05-2012---------*
				rs = stmt.executeQuery ("SELECT TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),NVL(A.OTH_COMMENTS,'-') ,NVL(A.CHEQUE_NO,'CASH'),NVL(A.PAYER_BRANCH_CODE,'-') ,NVL(A.THIRD_PARTY_NAME,'-'), NVL(A.THIRD_PARTY_ADDRESS,'-'),B.LOC_REC_NO,C.REG_NO,B.FINANCE_NO,C.APPLICATION_NO FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_RE_PRO_LOCATION_RECEIPT B,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+ 
					" WHERE A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  A.REC_NO = B.REC_NO  AND  A.REC_NO LIKE UPPER('"+m_receipt_no+"%') AND C.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO(B.FINANCE_NO) ");	
				//---------------------------------------*	
				more = rs.next();
				String m_third_party_name="",m_third_party_add="";
				if(more){
					m_Letter_date=rs.getString(1);
					m_comments=rs.getString(2);
					m_cheque_cash_type=rs.getString(3);
					m_payer_branch=rs.getString(4);
					m_third_party_name=rs.getString(5);
					m_third_party_add =rs.getString(6);
					m_loc_receipt_no =rs.getString(7); //added by Prabash on 18-05-2012
					m_reg_no = rs.getString(8); //added by Prabash on 21-05-2012
					m_finance_no = rs.getString(9); //added by Prabash on 21-05-2012
					m_application_no = rs.getString(10); //added by Prabash on 21-05-2012
				}
				
				rs1 = stmt1.executeQuery (	" SELECT "+
					" CLIENT_CODE, "+
					" UPPER(FULL_NAME), "+
					" NVL(UPPER(ADDRESS1),'ADD1'), "+
					" NVL(UPPER(ADDRESS2),'ADD2'), "+
					" NVL(UPPER("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE)),'CITY') CITY_NAME ,"+
					" NVL(VAT_REG_NO,'-') VAT_REG_NO "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE CLIENT_CODE='"+m_client_no+"' ");
				
				
				more = rs1.next();
				if(more){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_vat_reg_no=rs1.getString(6);
				}
				String m_address="";
				
				if(m_add1.equals("ADD1")){
					m_address="";
				}
				else{
					m_address=m_add1;
				}
				if(!m_add2.equals("ADD2")){
					m_address=m_address+","+m_add2;
				}
				
				
				// Added by prabash on 22-05-2012------**
				rs3 = stmt1.executeQuery (	" SELECT "+
					//	out.println(	" SELECT "+
					" NVL("+m_schema_name+".AF_CO_GET_ARREAS('"+m_application_no+"','"+m_Letter_date+"'),0), "+
					" A.INSTALLMENT_NO,"+
					" NVL(B.PERIOD,0),"+
					" NVL(A.NET_RENTAL_AMOUNT,0)"+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A ,"+m_schema_name+".AF_CO_PRO_APP_PRICING B "+
					" WHERE A.APPLICATION_NO = UPPER('"+m_application_no+"') AND B.PRO_INVOICE_NO = A.PRO_INVOICE_NO " );
				
				
				more = rs3.next();
				if(more){
					m_arreas = rs3.getDouble(1);
					m_installment_no = rs3.getString(2);
					m_slab = rs3.getString(3);
					m_rental_amount = rs3.getDouble(4);
				}
				//---------------------------------------**
				// Added by prabash on 22-05-2012------**
				rs5 = stmt1.executeQuery (	" SELECT "+
					
					" TO_CHAR(SYSDATE,'DD-Month-YYYY HH:MM:SS AM') "+
					" FROM DUAL " );
				
				
				
				more = rs5.next();
				if(more){
					m_sysdate = rs5.getString(1);
					
				}
				//---------------------------------------**
				
				// COMMENT ON 02-01-2008-------------------------------------------------------------
				
				
				/*out.println("<blockquote><font size=2><p style='text-align:left'>");						
				out.println("<table border='0' width='90%' class='table'>"); 		
				out.println("<tr><td width='70%'class='rep-body'>Receipt No : "+m_receipt_no+"</td><td width='30%' class='rep-body' align='right'>Date &nbsp;:"+m_Letter_date+"</td></tr>");
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>");		
				out.println("<tr><td width='*%' class='rep-body' >Received with thanks from : "+m_name+"</td></tr>");	
  			out.println("</TABLE>");				
			  out.println("</font></p></blockquote>");
				*/
				// COMMENT ON 02-01-2008-------------------------------------------------------------
				
				
				//out.println("<blockquote><font size=2><p style='text-align:left'>");			
				
				/*			out.println("<table border='0' width='90%' class='table'>"); 		
							out.println("<tr><td width='15%'class='rep-body'>&nbsp;</td><td width='50%'class='rep-body'>"+m_loc_receipt_no+"</td><td width='10%' class='rep-body' align='right'>&nbsp;</td><td width='20%' class='rep-body' align='right'>"+m_Letter_date+"</td></tr>");
							out.println("</table>");
							out.println("<table border='0' width='90%' class='table'>");		
							//out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");	
							
							if(!m_third_party_name.equals("-")){
							out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_third_party_name+"</td></tr>");	
							}
							else{
							out.println("<tr><td width='20%' class='rep-body' >&nbsp;</td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");	
							}
							
							out.println("</TABLE>");
				*/		
				// out.println("</font></p></blockquote>");
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("");
				
				
				
				
				
				String Sql1 =	"	SELECT C.FINANCE_NO, NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges Invoice No.'||B.INVOICE_NO DESCREPTION,SETTELED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_no+"%') "+
					" AND B.INVOICE_NO = C.INVOICE_NO "+
					" UNION ALL "+
					" SELECT '&nbsp',NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'), DESCREPTION ,CHARGE SETTELED_AMOUNT "+
					" FROM "+
					" (SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,INSURANCE CHARGE,'Insurance' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,LUXURY_TAX CHARGE,'Luxury Tax' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,REVANUE_LICENCE CHARGE,'Revanue Licence' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,RMV_CHARGES CHARGE,'RMV Charges' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"')A "+
					" WHERE A.REC_NO IN (SELECT REC_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS WHERE REC_NO ='"+m_receipt_no+"') ";
				
				
				
				String Sql2 =	" SELECT  NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Receipt Amount' DESCREPTION,REC_AMOUNT SETTELED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"'  AND REC_AMOUNT = RENTAL_OTER_INVOICE "+
					" UNION ALL "+
					" SELECT  NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'),'Rental Charges' DESCREPTION,RENTAL_OTER_INVOICE SETTELED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A "+
					" WHERE A.REC_NO ='"+m_receipt_no+"' AND REC_AMOUNT <> RENTAL_OTER_INVOICE "+
					" UNION ALL "+
					" SELECT NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'), DESCREPTION ,CHARGE SETTELED_AMOUNT "+
					" FROM "+
					" (SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,INSURANCE CHARGE,'Insurance' DESCREPTION "+ 
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' AND REC_AMOUNT <> RENTAL_OTER_INVOICE "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,LUXURY_TAX CHARGE,'Luxury Tax' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' AND REC_AMOUNT <> RENTAL_OTER_INVOICE "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,REVANUE_LICENCE CHARGE,'Revanue Licence' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' AND REC_AMOUNT <> RENTAL_OTER_INVOICE "+
					" UNION ALL "+
					" SELECT REC_NO,PAYER_BRANCH_CODE,CHEQUE_NO,RMV_CHARGES CHARGE,'RMV Charges' DESCREPTION "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE REC_NO ='"+m_receipt_no+"' AND REC_AMOUNT <> RENTAL_OTER_INVOICE ) ";
				
				
				/*
			String Inv_det =	" SELECT C.FINANCE_NO,NVL(CHEQUE_NO,'CASH'),NVL(PAYER_BRANCH_CODE,'-'), "+
								" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(C.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION,SETTELED_AMOUNT "+
												" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
												" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+
												" "+m_schema_name+".AF_CO_PRO_INVOICE C "+
												" WHERE A.REC_NO = B.RECEIPT_NO AND "+
												" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_no+"%') "+
												" AND B.INVOICE_NO = C.INVOICE_NO "+
												" ORDER BY C.ENT_DATE ";
								*/
				
				
				String Inv_det =	" SELECT FINANCE_NO,CHEQUE_NO,/*PAYER_BRANCH_CODE,DESCREPTION, */"+
					" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					" DESCREPTION , "+
					" SETTELED_AMOUNT,ENT_DATE "+
					" FROM "+
					" ( SELECT C.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(C.INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(C.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, "+
					" SETTELED_AMOUNT,C.ENT_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_no+"%') "+
					" AND B.INVOICE_NO = C.INVOICE_NO "+
					
					" UNION ALL "+
					
					/*" SELECT D.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(D.INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(D.INVOICE_TYPE))||'  '||B.INVOICE_NO DESCREPTION, "+
					" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC('ODI'),"+m_schema_name+".AF_CO_GET_INVOICE_DESCR('ODI'))||'  '||B.INVOICE_NO DESCREPTION, "+
					" SETTELED_AMOUNT, D.ENT_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE D "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND "+
					" A.REC_NO LIKE UPPER('"+m_receipt_no+"%') AND "+
					" B.INVOICE_NO = C.ODI_REF_NO AND "+
					" C.INVOICE_NO = D.INVOICE_NO "+
					*/
					
					" SELECT D.FINANCE_NO,NVL(CHEQUE_NO,'CASH') CHEQUE_NO,NVL(PAYER_BRANCH_CODE,'-') PAYER_BRANCH_CODE, "+
					" 'OD Interest' ,"+
					" SUM(SETTELED_AMOUNT),NULL ENT_DATE  "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,  "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B,  "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C, "+ 
					" "+m_schema_name+".AF_CO_PRO_INVOICE D  "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND  "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND "+
					" A.REC_NO LIKE UPPER('"+m_receipt_no+"%') AND "+
					" B.INVOICE_NO = C.ODI_REF_NO AND "+
					" C.INVOICE_NO = D.INVOICE_NO  "+
					" GROUP BY D.FINANCE_NO,CHEQUE_NO,PAYER_BRANCH_CODE  "+
					
					" ) "+
					" WHERE SETTELED_AMOUNT <>0 "+		//added by nuwan de silva on 14-06-2010	 >0  change into <>0										
					" ORDER BY ENT_DATE ";											
				
				
				
				String m_dis_status="Y";
				
				rs2 = stmt2.executeQuery (" select REC_AMOUNT ,SUM(SETTELED_AMOUNT)+NVL("+m_schema_name+".AF_CO_GET_REC_UNALLO_AMOUNT_2('"+m_receipt_no+"'),0)	  "+
					" from (	 "+
					" SELECT REC_AMOUNT,SETTELED_AMOUNT "+
					" FROM  "+
					" ( "+
					" SELECT  "+
					" A.REC_AMOUNT,SETTELED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,  "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND A.REC_NO LIKE UPPER('"+m_receipt_no+"%')  "+
					" AND B.INVOICE_NO = C.INVOICE_NO  "+
					/*" AND B.INVOICE_NO NOT IN ( "+
					" SELECT NVL( B.INVOICE_NO,'-') "+
					" FROM "+m_schema_name+".AF_CO_PRO_INITIAL_REC_ALLO A , "+m_schema_name+".AF_CO_PRO_INITIAL_RECEIPT B "+
					" WHERE A.FINANCE_NO=B.FINANCE_NO "+
					" AND   A.CHARGE_REFERENCE=B.CHARGE_REFERENCE "+
					" AND   A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%')   "+
					" AND   A.REC_NO LIKE UPPER('"+m_receipt_no+"%')  "+
					" ) "+
					*/
					" UNION ALL "+
					
					" SELECT  "+
					" A.REC_AMOUNT,SUM(SETTELED_AMOUNT) SETTELED_AMOUNT "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,   "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B,   "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C,   "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE D   "+
					" WHERE A.REC_NO = B.RECEIPT_NO AND   "+
					" A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  "+
					" A.REC_NO LIKE UPPER('"+m_receipt_no+"%') AND  "+
					" B.INVOICE_NO = C.ODI_REF_NO AND  "+
					" C.INVOICE_NO = D.INVOICE_NO   "+
					" GROUP BY A.REC_AMOUNT "+
					
					/*	" UNION ALL "+
						" SELECT "+
						" A.REC_AMOUNT,SUM(ALLOCATION_AMOUNT) SETTELED_AMOUNT "+
						" FROM "+m_schema_name+".AF_CO_PRO_INITIAL_REC_ALLO A , "+m_schema_name+".AF_CO_PRO_INITIAL_RECEIPT B "+ 
						" WHERE A.FINANCE_NO=B.FINANCE_NO(+) "+
						" AND   A.CHARGE_REFERENCE=B.CHARGE_REFERENCE(+) "+
						" AND   A.CLIENT_CODE LIKE UPPER('"+m_client_no+"%')  "+
						" AND   A.REC_NO LIKE UPPER('"+m_receipt_no+"%') "+
						" GROUP BY A.REC_AMOUNT "+
						*/
					
					" ) "+
					" WHERE SETTELED_AMOUNT <>0 	"+												
					
					" ) group by REC_AMOUNT  ");				
				
				if(rs2.next()){			
					if(rs2.getDouble(1)<rs2.getDouble(2)){
						m_dis_status = "N";
					}
				}			
				
				
				
				
				
				rs2 = stmt2.executeQuery (" SELECT  NVL(REC_NO,'-'), "+
					" DECODE(SETTLE_MODE,'CASH','Cash',CHEQUE_NO), "+
					" NVL(PAYER_BRANCH_CODE,'-'), "+
					" NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(PAYER_BRANCH_CODE),'')||' - '|| NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+
					" NVL(REC_AMOUNT,0), "+
					" DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','DIR_DEP','Derect Deposit','STD_ORD','Standing Order'), "+
					" NVL(PAYER_ACC_NO,'-'), "+
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+
					" CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE UPPER('"+m_client_no+"%') AND  REC_NO LIKE UPPER('"+m_receipt_no+"%') ");
				
				String m_Cheque_no   = "";
				String m_Bank_Branch = "";
				String m_Sett_ref    = "";
				String m_Sett_mod    = "";
				double m_amount      = 0;
				String m_amount_wd   ="";
				
				more = rs2.next();	
				
				if(more){
					m_Cheque_no   = rs2.getString(2);
					m_Bank_Branch = rs2.getString(4);
					m_amount      = rs2.getDouble(5);
					m_amount_wd   = rs2.getString(5);
					m_Sett_mod    = rs2.getString(6); 
					m_Sett_ref    = rs2.getString(2);
				}
				
				
				
				//out.println("<blockquote><font size=2><p style='text-align:left'>");
				
				// COMMENT ON 02-01-08-----------------------------------------
				/*out.println("<table border='0' width='90%' class='table'>");		
			out.println("<tr>");
			out.println("<td width='25%' class='rep-body' >Settlement Mode </b></td>");
			out.println("<td width='*%' class='rep-body' >: "+m_Sett_mod+"</td>");
			out.println("</tr>");	
			out.println("<td width='25%' class='rep-body' >Reference No </b></td>");
			out.println("<td width='*%' class='rep-body' >: "+m_Sett_ref+"</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td width='25%' class='rep-body' >Bank & Branch </b></td>");
			out.println("<td width='*%' class='rep-body' >: "+m_Bank_Branch+"</td>");
			out.println("</tr>");	
	out.println("<tr>");
			out.println("<td width='25%' class='rep-body' >Amount </b></td>");
			out.println("<td width='*%' class='rep-body' >: "+nf.format(m_amount)+"</td>");
			out.println("</tr>");	
	out.println("<tr>");
			out.println("<td width='25%' class='rep-body' >Amount in words </b></td>");
			out.println("<td width='*%' class='rep-body' >: RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY</td>");
			out.println("</tr>");	
			out.println("</TABLE>");		
			out.println("<br><br>");	
			
			*/
				
				
				
				
				
				/*	out.println("<table border='0' width='90%' class='table'>");	
					out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
					out.println("</table>");
						out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>S1No</td>	
					out.println("<tr><td width='15%' class='rep-body'>Receipt No</td>");
					out.println("<td width='12%' class='rep-body'>Cheque No</td>");
					out.println("<td width='10%' class='rep-body'>Bank & Branch</td>");
					out.println("<td width='15%' class='rep-body' >Description</td>");
					out.println("<td width='10%' class='rep-body' style='text-align:right'>Amount Rs.</td></tr>");
					out.println("</table>");
					out.println("<table border='0' width='90%' class='table'>");	
					out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
					out.println("</table>");
				
					int i=1;
					double sum_amount=0;
					int int_sum_amount=0;
					while(more)
					{
						out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>"+i+"</td>	
						out.println("<tr><td width='15%' class='rep-body'>"+rs2.getString(1)+"</td>");
						out.println("<td width='12%' class='rep-body'>"+rs2.getString(2)+"</td>");
						out.println("<td width='10%' class='rep-body'>"+rs2.getString(4)+"</td>");
						out.println("<td width='15%' class='rep-body'>"+rs2.getString(5)+"</td>");
						out.println("<td width='10%' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(6))+" </td></tr>");
						out.println("</table>");
						i=i+1;
						sum_amount =sum_amount+rs2.getDouble(6);
						int_sum_amount =int_sum_amount+rs2.getInt(6);
						more=rs2.next();
					}
				
				out.println("<table border='0' width='90%' class='table'>");	
				out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
				out.println("</table>");
				out.println("<table border='0' width='90%' class='table'>");//<td width='5%' class='rep-body'>"+i+"</td>	
				out.println("<tr><td width='15%' class='rep-body'></td>");
				out.println("<td width='12%' class='rep-body'></td>");
				out.println("<td width='10%' class='rep-body'></td>");
				out.println("<td width='15%' class='rep-body' style='text-align:right'>Total Amount</td>");
				out.println("<td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+" </td></tr>");
				out.println("</table>");
				out.println("<br><br>");*/
				
				
				/*----------------------------------------------------------------------------------------------------------------*/
				
				
				
				//rs2 = stmt2.executeQuery (Sql1);	
				double m_over_payment=0;
				//rs2 = stmt2.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_REC_UNALLO_AMOUNT('"+m_receipt_no+"'),0) FROM DUAL ");
				rs2 = stmt2.executeQuery("SELECT NVL("+m_schema_name+".AF_CO_GET_REC_UNALLO_AMOUNT_2('"+m_receipt_no+"'),0) FROM DUAL ");
				
				
				more = rs2.next();
				if(more){
					m_over_payment=rs2.getDouble(1);
				}
				
				
				rs2 = stmt2.executeQuery (Inv_det);		
				
				//COMENT ON 02-01-08 ---------------------------------------------------------------------------
				out.println("<br><br><br>");
				
				//out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
				/*out.println("<tr><td width='15%' class='rep-body' style='text-align:center'><B>Agreement No</B></td>");
				//out.println("<td width='12%' class='rep-body' style='text-align:center' ><B>Cheque No/Cash</B></td>");
				//out.println("<td width='10%' class='rep-body' style='text-align:center' ><B>Bank & Branch</B></td>");
				out.println("<td width='35%' class='rep-body' style='text-align:center' ><B>Description</B></td>");
				out.println("<td width='10%' class='rep-body' style='text-align:right'><B>Amount Rs.</B></td></tr>");
				//out.println("</table>");
			*/
				//COMENT ON 02-01-08 -------------------------------------------------------------------------------------
				
				
				more = rs2.next();
				
				String flag ="A";
				int i=1;
				double sum_amount=0;
				int int_sum_amount=0;
				
				/*
				
				
				if(more){
				
				while(more){	
								
					out.println("<tr><td width='15%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr><td>"+rs2.getString(1)+"</td></tr>");
					out.println("</table>");//</td>
				
					out.println("<td width='35%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr><td>&nbsp;"+rs2.getString(4)+"</td></tr>");
			out.println("</table></td>");
			out.println("<td width='10%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0' style='text-align:right'>");
					out.println("<tr><td>"+nf.format(rs2.getDouble(5))+"</td></tr>");
			out.println("</table></td>");   
					out.println("</tr>");
			i=i+1;
					sum_amount =sum_amount+rs2.getDouble(5);
					int_sum_amount =int_sum_amount+rs2.getInt(5);
				more=rs2.next();				
					}
					flag ="B";
					}
				else if(flag.equals("A")){
					
					rs4 = stmt4.executeQuery (Sql2);
					boolean more4 = rs4.next();
					
					while(more4){	
						
					
					out.println("<tr><td width='15%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr><td>&nbsp</td></tr>");
					out.println("</table></td>");
					
					out.println("<td width='35%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
					out.println("<tr><td>&nbsp;"+rs2.getString(3)+"</td></tr>");
			out.println("</table></td>");
			out.println("<td width='10%' class='rep-body'>");
					out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0' style='text-align:right'>");
					out.println("<tr><td>"+nf.format(rs2.getDouble(4))+"</td></tr>");
			out.println("</table></td>");
					out.println("</tr>");
					
								
					
					i=i+1;
					sum_amount =sum_amount+rs4.getDouble(4);
					int_sum_amount =int_sum_amount+rs4.getInt(4);
				more4=rs4.next();				
					}
					
					
					
					
					}  */
				
				
				
				/*
				out.println("<tr><td width='15%' class='rep-body'></td>");
			  out.println("<td width='12%' class='rep-body'></td>");
		  	out.println("<td width='10%' class='rep-body'></td>");
			  out.println("<td width='15%' class='rep-body' style='text-align:center'>Total Amount</td>");
			  out.println("<td width='10%' class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+" </td></tr>");*/
				
				if(m_dis_status.equals("N")){
					out.println("<br><br><br><br>");
		/*			out.println("<TABLE align='center' width=100% BORDER=0 CELLPADDING=0 CELLSPACING=0 STYLE={color: black;align:center;} width='90%'>");
					out.println("<tr>");
					out.println("<td align=center>There is an error please check the allocation.");//style='padding-left:35px'
					out.println("</td>");
					out.println("</tr>");
					out.println("</TABLE>");*/  //commented by kanishka on 24-06-2013 [FOR NEW RECEIPT FORMAT]
				}else{
					
					
					out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' height='360px'>");
					out.println("<tr><td width='100%' class='rep-body' valign='top'>");
					out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table' >");
					
					if(more){
						while(more){
							/*out.println("<tr><td width='15%' class='rep-body'>");
							out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
							out.println("<tr><td class='rep-body'>"+rs2.getString(1)+"</td></tr>");
							out.println("</table>");//</td>
							out.println("<td width='35%' class='rep-body'>");
							out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
							out.println("<tr><td class='rep-body'>&nbsp;"+rs2.getString(4)+"</td></tr>");
					out.println("</table></td>");
					out.println("<td width='10%' class='rep-body'>");
							out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0' style='text-align:right'>");
							out.println("<tr><td class='rep-body'>"+nf.format(rs2.getDouble(5))+"</td></tr>");
					out.println("</table></td>");   
							out.println("</tr>");
							*/
							
							/*out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>"+rs2.getString(1)+"</td>"); //commented by kanishka on 24-06-2013 [FOR NEW RECEIPT FORMAT]
							out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(2)+"</td>");
							out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(3)+"</td>");
							out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >"+rs2.getString(4)+"</td>");
							out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(rs2.getDouble(5))+"</td></tr>");*/ 
							i=i+1;
							sum_amount =sum_amount+rs2.getDouble(5);
							int_sum_amount =int_sum_amount+rs2.getInt(5);
							more=rs2.next();				
							
						}
						if(m_over_payment>0){
						/*	out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");	//commented by kanishka on 24-06-2013 [FOR NEW RECEIPT FORMAT]
							out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>"); 
							out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
							out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Towards Dues of the Contract</td>"); //Over Payment
							out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_over_payment)+"</td></tr>");*/
							sum_amount =sum_amount+m_over_payment;
						}
						
						
					}
					else if(flag.equals("A")){
						
						//rs4 = stmt4.executeQuery (Sql2);
						//boolean more4 = rs4.next();
						//while(more4){	
						/*out.println("<tr><td width='15%' class='rep-body'>");
						out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
						out.println("<tr><td>&nbsp</td></tr>");
						out.println("</table></td>");
						out.println("<td width='35%' class='rep-body'>");
						out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'>");
						out.println("<tr><td>&nbsp;"+rs2.getString(3)+"</td></tr>");
				out.println("</table></td>");
				out.println("<td width='10%' class='rep-body'>");
						out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0' style='text-align:right'>");
						out.println("<tr><td>"+nf.format(rs2.getDouble(4))+"</td></tr>");
				out.println("</table></td>");
						out.println("</tr>");
						*/
						
						/*
						rs4= stmt4.executeQuery ("SELECT "+
						" SUM(GRENTAL_AMOUNT) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
						" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  "+
						" AND INSTALLMENT_NO=0 ");
						
						if(rs.next()){
						out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'></td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Initial Payment</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>nf.format(rs4.getDouble(1))</td></tr>");
						}
						
						
						*/
						/*out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'></td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Initial Payment</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>59,900.00</td></tr>");
						out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'></td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Document Charges Amount</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>2,500.00</td></tr>");
						out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'></td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Government Levy</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>2,106.00</td></tr>");
						*/
						
						/*out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'></td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' ></td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >Initial Payment</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_amount)+"</td></tr>");
				*/
						
						/*String charge_desc="First Rental ";
						out.println
						//rs= stmt.executeQuery 
						("SELECT "+
						" SUB_TYPE_CODE, "+
						" UPPER(DESCRIPTION) "+
						" FROM  "+m_schema_name+".AF_CO_MAS_SUB_CHARGES  "+
						" WHERE  CHARGE_TYPE='INV' ");
														
						while(rs.next()){
						charge_desc=charge_desc+" // "+rs.getString(2);
						}
						*/
						
						
						/*out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
						out.println("<td width='12%'     align='left' class='rep-body' style='text-align:left' >"+m_cheque_cash_type+"</td>");
						out.println("<td width='23%'     align='left' class='rep-body' style='text-align:left' >"+m_payer_branch+"</td>");
						out.println("<td width='30%'     align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_amount)+"</td></tr>");
					*/
						String charge_desc="Initial Payment / Document Charges / Insurance / Advance Monthly Instruments / RMV Charges  / "+   
							"Semi luxury / Luxury Tax / Governement Levy / Insurance Claims / Lease Receivable - Settlement / ODI / Sale Price ";
						/*			
									//comment by nuwan de silva on 04-04-2008---------------------------------------------------------------------------
									out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>"+charge_desc+"</td>");
									out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>"+nf.format(m_amount)+"</td></tr>");
									out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>Settle Mode - "+m_Sett_mod+" &nbsp;&nbsp;AGREEMENT NO: "+m_comments+"</td>");
									out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'></td></tr>");
						*/    
						
						/*out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>"+charge_desc+"</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
						out.println("<tr><td width='80%'  clospan=4 align='left' class='rep-body' style='text-align:left'>AGREEMENT NO: "+m_comments+"</td>");
						out.println("<td width='20%'     align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
				*/
						
						
						
						
						
						i=i+1;
						sum_amount =sum_amount+m_amount;
						//int_sum_amount =int_sum_amount+rs4.getInt(4);
						//more4=rs4.next();				
						//}
					}  
					
					
					
					// comment on 02-01-2008
					/*out.println("<tr><td colspan='2' class='rep-body' style='text-align:center'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <b>Allocated Amount </b></td>");
					out.println("<td class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+"</td></tr>");
					*/
					//			out.println("</table>");
					//			out.println("</td></tr></table>");
					
					//out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
					
					//out.println("<tr><td colspan='4' class='rep-body' style='text-align:right'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <b>&nbsp; </b></td>");
					//out.println("<td class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+"</td></tr>");
					/*			out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
								out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
								out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
								out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+nf.format(sum_amount)+"</td></tr>");
								out.println("</table>");
					*/			
					//added by Prabash on 21-05-2012----*
					//added by Prabash on 21-05-2012----*
				/*	out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>kanishka receipt"+m_loc_receipt_no+"</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>kanishka letter date"+m_Letter_date+"</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' > kanishka  reg no"+m_reg_no+"</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'> kanishka fin number"+m_finance_no+"</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					if(!m_third_party_name.equals("-")){
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >kanishka 3rd "+m_third_party_name+"</td>");
					}
					else{
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >kanishka name"+m_name+"</td>");
					}
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' > kanishka amount in words"+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >kanishka chqno"+m_Cheque_no+"</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					if(m_amount > m_rental_amount){
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >RENTAL "+m_slab+"/"+m_installment_no+"</td></tr>");
					}
					else{
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' > - </td> </tr>");
					}
					
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >kanishka amount"+nf.format(m_amount)+"</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>kanishka arrears"+m_arreas+"</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>kanishka sysdate"+m_sysdate+";</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("</table>");	
					//------------------------------
					
					
					out.println("");
					
					
					out.println("<BR><BR>");  */
				String user_name="";
				String user_loc="";
				String user_firstname="";
				
				rs6 =stmt5.executeQuery("SELECT NAME,LOCATION_CODE FROM "+m_schema_name+".CO_CO_MAS_USER WHERE USER_ID='"+m_username+"' ");
				if(rs6.next()){
					user_name=rs6.getString(1);
					user_loc=rs6.getString(2);
				}
				
				rs6 =stmt5.executeQuery("SELECT SUBSTR('"+user_name+"',0,INSTR('"+user_name+"',' ',1,1)-1) FROM DUAL");//kanishka
				if(rs6.next()){
					user_firstname=rs6.getString(1);
				}
				//-------------------------------------------------------------------------------//
				//ADDED NE ISHKA DILSHAN ON 21-06-2013
					out.println("<table border='1' bordercolor='black' cellspacing='0' width='100%' class='table'>");
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+m_loc_receipt_no+"</td></tr>");//kanishka receipt
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+m_Letter_date+"</td></tr>");//kanishka letter date
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >"+m_reg_no+"</td>");// VEHICLE NUMBER
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+m_finance_no+"</td></tr>");// kanishka fin number
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					if(!m_third_party_name.equals("-")){
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >"+m_third_party_name+"</td>");//kanishka 3rd 
					}
					else{
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >"+m_name+"</td>");//kanishka name
					}
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >"+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY</td>");// kanishka amount in words
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >"+m_Cheque_no+"</td>");//kanishka chqno
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					if(m_amount > m_rental_amount){
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >RENTAL /"+m_slab+"/"+m_installment_no+"</td></tr>");//KANISHKA HAVE TO MODIFY RENATAL PART
					}
					else{
						out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' > - </td> </tr>");
					}
					
					
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >"+nf.format(m_amount)+"</td>");//kanishka amount
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>&nbsp;</td>");
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>"+m_arreas+"</td></tr>");//kanishka arrears
					
					out.println("<tr><td width='15%' align='left' class='rep-body' style='text-align:left'>"+m_sysdate+"</td>");//kanishka sysdate [remove semicolon]
					out.println("<td width='12%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='23%' align='left' class='rep-body' style='text-align:left' >&nbsp;</td>");
					out.println("<td width='30%' align='left' class='rep-body' style='text-align:left' >-"+user_firstname+"-"+user_loc+"</td>");//HAVE TO ADD USER - BRANCH  user_name ==> full name
					out.println("<td width='20%' align='right' class='rep-body' style='text-align:right'>&nbsp;</td></tr>");//user fist name in ==> user_firstname
					
					out.println("</table>");	
					//------------------------------**
					
					
					out.println("");
					
					
					out.println("<BR><BR>");
				
				
				
				
				
				
				
				
				
				
					//COMENT ON 02-01-08 ---------------------------------------------------------------------------
					
					/*out.println("<table border='0' width='90%' class='table'>");	
					out.println("<tr><td width='5%' class='rep-body'></td><td width='*%' class='rep-body'><b>STAMP DUTY HAS BEEN COMPOUNDED IN TERMS OF SECTION 7 OF<br> THE STAMP DUTY(SPECIAL PROVISIONS) ACT NO.12 OF 2006</b></td><td width='16%' class='rep-body'></td></tr>");
					out.println("</table>");
					*/
					//COMENT ON 02-01-08 ---------------------------------------------------------------------------
					
					/*		out.println("<table border='0' bordercolor='black' cellspacing='0' width='100%' class='table'>");
							out.println("<tr>");
							out.println("<td width='20%' class='rep-body' >&nbsp;</td>");
							out.println("<td width='*%' class='rep-body' >RUPEES "+m_sn_methods.numbersToChar(m_amount_wd).toUpperCase()+" ONLY</td>");
							out.println("</tr>");	
								out.println("</TABLE>");		
							String msg="Season's Greetings and Best Wishes for a New Year " ;
							out.println("");
							
						*/	
					//out.println("<BR><BR>");
					
					/* // COMMENT BY CHANDANA ON 16/10/2007 
					out.println("<table border='0' width='90%' class='table'>");	
					out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
					out.println("</table>"); 
								
					
								String m_val = nf.format(sum_amount);
								String m_val2 = m_sn_methods.met_unformat_number(m_val);
								int m_dot=m_val.indexOf(".");
								int m_length=m_val.length();
								String m_cents=m_val.substring(m_dot+1,m_length);
								//int m_int = 	Integer.parseInt(sum_amount);
								String m_int_val= int_sum_amount+"";	
								
								
							
							if(!m_cents.equals("00")) {	
							out.println("<table border='0' width='90%' class='table'>");		
							out.println("<tr><td width='*%' class='rep-body'><B>Amount in words</B></td></tr>");
							out.println("</table>");
							out.println("<table border='0' width='90%' class='table'>");		
							out.println("<tr><td width='*%' class='rep-body'> ");
							out.println(" RUPEES  "+m_sn_methods.numbersToChar(m_val2).toUpperCase()+" ONLY  "); //@@ "+numbersToChar_inside(m_val2)+"
							out.println("</td></tr>");
							out.println("</table>");
							}
							else {
							out.println("<table border='0' width='90%' class='table'>");		
							out.println("<tr><td width='*%' class='rep-body'><B>Amount in words</B></td></tr>");
							out.println("</table>");
							out.println("<table border='0' width='90%' class='table'>");		
							out.println("<tr><td width='*%' class='rep-body'> ");
							out.println(" RUPEES "+m_sn_methods.numbersToChar(m_int_val).toUpperCase()+" ONLY  "); //@@ "+numbersToChar_inside(m_val2)+"
							out.println("</td></tr>");
							out.println("</table>");
							}
							
							
					out.println("<table border='0' width='90%' class='table'>");	
					out.println("<tr><td width='*%' class='rep-body'>------------------------------------------------------------------------------------------------------------------------------------</td></tr>");
					out.println("</table>"); */
					
					
					//COMMENT ON 02-01-2008 --------------------------------------------------------------
					/*out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr><td width='*%' class='rep-body' align='right'><B><I>Please inform any discrepancies within 10 days</I></B></td></tr>");
					out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr><td width='*%' class='rep-body' align='right'><B><I>Receipt is valid subject to realisation of cheque/s</I></B></td></tr>");
					out.println("</table>");
					
					out.println("<br><br>");
					out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr ><td width='30%' class='rep-body' style='{text-align:justify;}'>");
					out.println(" ................................");
					out.println("</td>");
					out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'><B>");
					out.println(" IF THE CONTRACT IS ALREADY TERMINATED ");
					out.println("</B></td>");
					out.println("</tr></table>");
					
					out.println("<table border='0' width='90%' class='table'> ");	
					out.println("<tr ><td width='30%' class='rep-body' style='{text-align:justify;}'><B>");
				out.println(" Authorized Signatory ");	
					out.println("</B></td>");
					out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'><B>");
					out.println(" PAYMENT IS ACCEPTED WITHOUT PREJUDICE ");
					out.println("</B></td>");
					out.println("</tr></table>");
					
					out.println("<br><br><br><br><br>");
					out.println("<blockquote><font size=2><p style='text-align:left'>");						
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' ><B><I>If undelivered please return to :</I></B></td></tr>");	
					out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' ><B>"+m_orient_name+".</B></td></tr>");	
					out.println("</table>");
					
					out.println("<table border='0' width='90%' class='table'>"); 		
						out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' ><B>"+m_orient_add1+","+m_orient_add2+","+m_orient_city_name+",Sri Lanka.</B></td></tr>");
					out.println("</table>");	
					
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' ><B>Tel :"+m_orient_tel_no+" Fax :"+m_orient_fax_no+"  Email :"+m_email+"</B></td></tr>");
					out.println("</table>");	
					
					out.println("</font></p></blockquote>");
				
					
					out.println("<table border='0' width='90%' class='table'>"); 		
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%'class='rep-body'><b>To &nbsp;</b> :</td></tr>");
					out.println("</table>");	
				*/
					//COMMENT ON 02-01-2008 --------------------------------------------------------------
					out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
					out.println("<blockquote><font size=2><p style='text-align:left'>");						
					/*			out.println("<table border='0' width='90%' class='table'>"); 
									
							if(!m_third_party_name.equals("-")){
								out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_third_party_name+"</td></tr>");
								}
								else{
								out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_name+"</td></tr>");
								}
					*/		  
					/*if(m_add1.equals("ADD1")){ //Added by Chandana on 06/06/2007
					}else{
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_add1+"</td></tr>");
					}
					if(m_add2.equals("ADD2")){ //Added by Chandana on 06/06/2007
					}else{
					out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_add2+"</td></tr>");
					}
					*/
					/*			
								if(!m_third_party_name.equals("-")){
								out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_third_party_add+"</td></tr>");
								}
								else{
								out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_address+"</td></tr>");
								}
								
								if(m_third_party_name.equals("-")){
								if(m_city_desc.equals("CITY")){ //Added by Chandana on 11/06/2007
								}else{			
								out.println("<tr><td width='10%' class='rep-body'></td><td width='*%' class='rep-body' >"+m_city_desc+".</td></tr>");
								}
								}
								
								out.println("</TABLE>");
					*/			
					out.println("</font></p></blockquote>");
					
					out.println("</font></p></blockquote>");		
					out.println("</form></body></html>");
					
				}//nsssss
				
			}//main page end
			
			
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
