import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
          
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007

public class LAKDL_FA_RE_PRO_rpt_operation_detail extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
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
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();

			String m_from_date=req.getParameter("from_date");
			String m_to_date=req.getParameter("to_date");
			String m_client_code=req.getParameter("client_code");
			String m_facility_no=req.getParameter("facility_no");
			String m_eff_date = "";
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_ADJUSTMENTS_DETAIL_REPORT")){

			
			//------------------ ADJUSTMENTS DETAILS -----------------------------
				
				
 				rs9= stmt9.executeQuery("SELECT  ADJUSTMENT_NO,"+//1
  			 " NVL(FACILITY_NO,'-'), "+//2
  			 " NVL(CLIENT_CODE,'-'), "+//3
				 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//4	
  			 " NVL(BATCH_NO,'-'), "+//5
  			 " NVL(DEBTOR_CODE,'-'), "+//6
				 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//7	
  			 " NVL(INVOICE_NO,'-'), "+//8
  			 " NVL(ADJUST_AMOUNT,0), "+//9
  			 " TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'), "+//10
  			 " DECODE(ADJUST_TYPE,'DR','Debit','Credit'), "+//11
  			 " DECODE(ADJUST_CATEGORY,'CLA','Client','INA','Invoice','INR','Invoice Re-assign'), "+//12
  			 " NVL(ADJUSTMENT_COMMENTS,'-'), "+//13
  			 " DECODE(INVOICE_STATUS,'Y','Adjustment Approved','N','Adjustment to be Approved'), "+//14
  			 " NVL(SOURCE_DOCUMENT,'-') "+//15
 				"FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
 				"WHERE TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 				"TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
 				"FACILITY_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_code+"' ");

			
          boolean mflag9=true;							
			 		boolean more9 = rs9.next();
						
						out.println("<HTML><HEAD><TITLE>Adjustment Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>");
						out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Adjustment Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Adjust Date</B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more9){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more9){
					
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs9.getString(2)+"')\" style='cursor:hand' ><u>"+rs9.getString(2)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs9.getString(3)+"')\" style='cursor:hand'  ><u>"+rs9.getString(4)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='12%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Adjust Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Adjust Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Adjust Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Adjust Cat.</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Adjust Status</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Source Document</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more9){
				
							if(mflag9){
								out.println("<tr class=tr_input>");
								mflag9=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag9=true;
							}
							out.println("<td width='12%' class=div_input onClick=\"show_adjustment_details('"+rs9.getString(1)+"')\" style='cursor:hand' ><u>"+rs9.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs9.getString(5)+"')\" style='cursor:hand' ><u>"+rs9.getString(5)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs9.getString(6)+"')\" style='cursor:hand'><u>"+rs9.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs9.getString(6)+"','"+rs9.getString(8)+"')\" style='cursor:hand' ><u>"+rs9.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs9.getDouble(9))+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(11)+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(14)+"</td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(15)+"</td>");
							out.println("</tr>");
							more9 = rs9.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_CLIENT_FACILITY_DETAIL_REPORT")){

			
			//------------------ CLIENT FACILITY DETAIL REPORT ------------------------------

				rs4= stmt4.executeQuery("SELECT  FACILITY_NO, "+//1
  			 " NVL(CLIENT_CODE,'-'), "+//2
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//3
  			 " NVL(FACILITY_MGR_CODE,'-'), "+//4
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),'-'), "+//5
  			 " NVL(FA_PRODUCT_CODE,0), "+//6
  			 " NVL("+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),'-'), "+//7
  			 " NVL(FEE_PACK_CODE,'-'), "+//8
  			 " NVL("+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE),'-'), "+//9
  			 " NVL(CREDIT_LIMIT,0), "+//10
  			 " NVL(CREDIT_PERIOD,0), "+//11
  			 " NVL(TOLERANCE_CREDIT_PERIOD,0), "+//12
  			 " NVL(RESERVE_MARGIN,0), "+//13
  			 " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+//14
  			 " DECODE(FACILITY_STATUS,'Y','Facility Approved','N','Facility to be Approved','C',' Facility Disapproved','T','Teminated'), "+//15
  			 " NVL(INT_RATE,0) "+//16
 				"FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 				"WHERE TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 				"TO_DATE(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
 				"FACILITY_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_code+"' ");

			
          boolean mflag4=true;							
			 		boolean more4 = rs4.next();
						
						out.println("<HTML><HEAD><TITLE>Client Facility Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Client Facility Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Facility Start Date </B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more4){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more4){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs4.getString(1)+"')\" style='cursor:hand' ><u>"+rs4.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs4.getString(2)+"')\" style='cursor:hand' ><u>"+rs4.getString(2)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='15%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Product Pack. Name</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Fee Pack. Name</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit </b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tol Credit Period </b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Start Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Status</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more4){
				
							if(mflag4){
								out.println("<tr class=tr_input>");
								mflag4=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag4=true;
							}
							out.println("<td width='15%' >"+rs4.getString(5)+"</td>");
							out.println("<td width='15%' >"+rs4.getString(7)+"</td>");
							out.println("<td width='15%' >"+rs4.getString(9)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs4.getDouble(10))+"</td>");
							out.println("<td width='10%' align='right'>"+rs4.getString(11)+"</td>");
							out.println("<td width='10%' align='right'>"+rs4.getString(12)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs4.getDouble(13))+"</td>");
							out.println("<td width='10%' >"+rs4.getString(14)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(15)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(16)+"</td>");
							out.println("</tr>");
							more4 = rs4.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_QUOTATION_DETAIL_REPORT")){

			
			//------------------ QUOTATION DETAIL REPORT  ------------------------------

					
	 			rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
     		 " FA_PRODUCT_CODE, "+//2
				 " NVL("+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),'-'), "+//3
  			 " FEE_PACK_CODE,	"+//4
				 " NVL("+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE),'-'), "+	//5
  			 " DECODE(QUOTATION_STATUS,'N','Quotation to be Approved','Y','Quotation Approved','C','Quotation Disapproved'),"+//6
  			 " NVL(CLIENT_CODE,'-'),"+//7
				 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//8
  			 " NVL(INQUERY_NO,'-'),"+//9
	  		 " NVL(CREDIT_LIMIT,0),"+//10
	  		 " NVL(CREDIT_PERIOD,0),"+//11
	  		 " NVL(TOLERANCE_CREDIT_PERIOD,0),"+//12
  			 " NVL(RESERVE_MARGIN,0),"+//13
  			 " NVL(INT_RATE,0) "+//14
	 			"FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
 				"WHERE CLIENT_CODE='"+m_client_code+"' ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Quotation Details Report </B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs5.getString(7)+"')\" style='cursor:hand' ><u>"+rs5.getString(8)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Product Pack. Name</b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Fee Pack. Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Quotation Status</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Inquiry No</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tol Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(3)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(10))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+rs5.getInt(11)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+rs5.getInt(12)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(13))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(14))+"</td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_CLIENT_DEBTOR_ASSIGN_DETAIL_REPORT")){
			
			//------------------CLIENT DEBTOR ASSIGN DETAIL REPORT ------------------------------
	
		 rs6= stmt6.executeQuery("SELECT  A.FACILITY_NO, "+//1
    	" NVL(A.CLIENT_CODE,'-'), "+//2
    	" NVL(A.DEBTOR_CODE,'-'), "+//3
    	" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+//4
  	  " NVL(A.MKT_CODE,'-'), "+//5
  	  " NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-'), "+//6
  	  " NVL(A.CREDIT_LIMIT,0), "+//7
  	  " NVL(A.CREDIT_PERIOD,0), "+//8
  	  " NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+//9
  	  " NVL(A.RESERVE_MARGIN,0), "+//10
			" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-'), "+//11
			" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-'), "+//12
			" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')), "+//13
  	  " DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved') "+//14
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
 			" WHERE A.DEBTOR_CODE=B.CLIENT_CODE "+
			" AND A.FACILITY_NO='"+m_facility_no+"' AND A.CLIENT_CODE='"+m_client_code+"' ");

			
       boolean mflag6=true;							
			 boolean more6 = rs6.next();
				
					out.println("<HTML><HEAD><TITLE>Client Debtor Assign Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> Client Debtor Assign Details Report </B></TD></TR>");
					out.println("</TABLE>");												
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");

				if(!more6){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
				}
				if(more6){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs6.getString(1)+"')\" style='cursor:hand' ><u>"+rs6.getString(1)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs6.getString(2)+"')\" style='cursor:hand' ><u>"+rs6.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Address</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Telephone Nos</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>BRC No/NIC</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Tol. Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Reverse Margin</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Relation Status</b></DIV></td>");
					out.println("</tr>"); 
				}
				while(more6){
				
							if(mflag6){
								out.println("<tr class=tr_input>");
								mflag6=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag6=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs6.getString(3)+"')\" style='cursor:hand' ><u>"+rs6.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs6.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(11)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(13)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(7))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+rs6.getInt(8)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+rs6.getInt(9)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(10))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(14)+"</td>");
							out.println("</tr>");
							more6 = rs6.next();
				}	

				
        out.println("</table>");
			  out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_INVOICE_DETAIL_REPORT")){
			
			//------------------ INVOICE DETAILS REPORT ------------------------------
			 
 			rs3= stmt3.executeQuery("SELECT DISTINCT B.FACILITY_NO,"+//1
  		 " B.CLIENT_CODE, "+//2
			 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+	//3
  		 " NVL(A.BATCH_NO,'-'), "+//4
  		 " NVL(A.DEBTOR_CODE,'-'), "+//5
			 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//6	
  		 " NVL(DECODE(A.REASSIGN_STATUS,'N','No','Y','Yes'),'-'), "+//7
  		 " NVL(A.INVOICE_NO,'-'), "+//8
  		 " TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//9
		   " NVL(A.INVOICE_AMOUNT,0), "+//10
  		 " NVL(A.ADJUSTMENT_AMOUNT,0), "+//11
  		 " NVL(A.NET_INVOICE_AMOUNT,0), "+//12
  		 " NVL(A.SETTLE_AMOUNT,0), "+//13
  		 " NVL(A.BALANCE_AMOUNT,0), "+//14
  		 " NVL(A.PAIED_AMOUNT,0) "+//15
 			"FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
 			"WHERE A.BATCH_NO=B.BATCH_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' "+
 			"AND TO_DATE(TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 			"TO_DATE(TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

			
          boolean mflag3=true;							
			 		boolean more3 = rs3.next();
						
						out.println("<HTML><HEAD><TITLE>Invoice Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Invoice Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Invoice Date</B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more3){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more3){
					  out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs3.getString(1)+"')\" style='cursor:hand' ><u>"+rs3.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs3.getString(2)+"')\" style='cursor:hand' ><u>"+rs3.getString(3)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='12%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						//out.println("<td width='12%' ><DIV class=div_input><b>Re-assign Status</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Adjust Amount</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Net Amount</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Settle Amount</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Balance Amount</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Paid Amount</b></DIV></td>");
						out.println("</tr>"); 
					}
					while(more3){
				
							if(mflag3){
								out.println("<tr class=tr_input>");
								mflag3=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag3=true;
							}
							out.println("<td width='12%' class=div_input onClick=\"show_invoice_batch_details('"+rs3.getString(4)+"')\" style='cursor:hand'><u>"+rs3.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs3.getString(5)+"')\" style='cursor:hand' ><u>"+rs3.getString(6)+"</u></td>");
							//out.println("<td width='12%' class=div_input >"+rs3.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs3.getString(5)+"','"+rs3.getString(8)+"')\" style='cursor:hand' ><u>"+rs3.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs3.getString(9)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(10))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(11))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(12))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(13))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(14))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs3.getDouble(15))+"</td>");
							out.println("</tr>");
							more3 = rs3.next();

						
					}
				
        	out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_POD_CHEQUE_DETAIL_REPORT")){

			
			//------------------ POD CHEQUE DETAIL REPORT ------------------------------
					
			 rs5= stmt5.executeQuery("SELECT DISTINCT NVL(A.FACILITY_NO,'-'), "+//1
  			 " A.CLIENT_CODE, "+//2
				 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//3	
    		 " NVL(A.POD_REF_NO,'-'), "+//4
  			 " NVL(A.BATCH_NO,'-'), "+//5
  			 " NVL(A.DEBTOR_CODE,'-'), "+//6
				 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//7	
  			 " NVL(A.INVOICE_NO,'-'), "+//8
  			 " DECODE(A.ALLO_TYPE,'IA','Invoice wise Allocation'), "+//9
  			 " NVL(B.PAYER_BRANCH_CODE,'-'), "+//10
  			 " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'), "+//11
  			 " NVL(B.PAYER_ACC_NO,'-'), "+//12
  			 " NVL(B.CHEQUE_NO,'-'), "+//13
  			 " TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'), "+//14
  			 " NVL(B.CHEQUE_AMOUNT,0), "+//15
  			 " DECODE(B.POD_STATUS,'N','POD Cheque Available','Y','POD Cheque Realised','C','POD Cheque Dishonored') "+//16
 			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B "+
 			" WHERE A.POD_REF_NO=B.POD_REF_NO  "+
 			" AND A.FACILITY_NO='"+m_facility_no+"' AND  A.CLIENT_CODE='"+m_client_code+"' "+
 			" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 			" TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>POD Cheque Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> POD Cheque Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Cheque Date</B></TD></TR>");
					 	out.println("</TABLE>");					
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
					 	out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(3)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='15%' ><DIV class=div_input><b>POD Ref No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");  
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Allo. Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Payer Branch</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Payer Acc.No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Cheque Amount</b></DIV></td>"); 
						//out.println("<td width='10%' ><DIV class=div_input><b>POD Status</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_pod_cheque_details('"+rs5.getString(4)+"')\" style='cursor:hand'><u>"+rs5.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs5.getString(5)+"')\" style='cursor:hand'><u>"+rs5.getString(5)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(6)+"')\" style='cursor:hand' ><u>"+rs5.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs5.getString(6)+"','"+rs5.getString(8)+"')\" style='cursor:hand'><u>"+rs5.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(11)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(14)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs5.getDouble(15))+"</td>");
							//out.println("<td width='10%' class=div_input >"+rs5.getString(16)+"</td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_RECEIPT_DETAIL_REPORT")){
			
			//------------------ SETTLEMENT RECEIPT DETAIL REPORT  -----------------------------
	
					
 				rs7= stmt7.executeQuery("SELECT DISTINCT A.FACILITY_NO,"+//1
  			 " NVL(A.CLIENT_CODE,'-'), "+//2
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//3
  			 " NVL(A.RECEIPT_NO,'-'), "+//4
  			 " NVL(A.BATCH_NO,'-'), "+//5
  			 " NVL(A.DEBTOR_CODE,'-'), "+//6
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+//7
  			 " NVL(A.INVOICE_NO,'-'), "+//8
  			 " NVL(A.REC_AMOUNT,0), "+//9
  			 " NVL(A.BALANCE_AMOUNT,0), "+//10
  			 " NVL(A.ALLO_AMOUNT,0), "+//11
  			 " NVL(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'-') "+//12
 			 " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
  		 " WHERE  A.FACILITY_NO='"+m_facility_no+"' AND A.CLIENT_CODE='"+m_client_code+"' "+
			 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 			 " TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')");
			
          boolean mflag7=true;							
			 		boolean more7 = rs7.next();
						
						out.println("<HTML><HEAD><TITLE>Settlement Receipt Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report   [Period - From : "+m_from_date+" To : "+m_to_date+"] Against EFF Value Date </B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more7){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more7){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs7.getString(1)+"')\" style='cursor:hand' ><u>"+rs7.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs7.getString(2)+"')\" style='cursor:hand' ><u>"+rs7.getString(3)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >"); 
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						//out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Allo. Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Eff Value Date</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more7){
				
							if(mflag7){
								out.println("<tr class=tr_input>");
								mflag7=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag7=true;
							}
							out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs7.getString(4)+"')\"  style='cursor:hand'><u>"+rs7.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_invoice_batch_details('"+rs7.getString(5)+"')\" style='cursor:hand'>"+rs7.getString(5)+"</td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs7.getString(6)+"')\" style='cursor:hand' ><u>"+rs7.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs7.getString(6)+"','"+rs7.getString(8)+"')\" style='cursor:hand'><u>"+rs7.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs7.getDouble(9))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs7.getDouble(10))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs7.getDouble(11))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs7.getString(12)+"</td>");
							out.println("</tr>");
							more7 = rs7.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_DEPOSIT_DETAIL_REPORT")){

				
			// ------------------ SETTLEMENT DEPOSIT REPORT - 8 -----------------------------
				
				
				rs8 = stmt8.executeQuery(" SELECT  NVL(B.FACILITY_NO,'-'), "+//1
  				" NVL(B.CLIENT_CODE,'-'), "+//2
					""+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+//3
  				" NVL(A.DEPOSIT_NO,'-'), "+//4
    			" NVL(A.RECEIPT_NO,'-'), "+//5
    			" NVL(C.DEPOSIT_MODE,'-'), "+//6
  			  " NVL(A.DEPOSIT_AMOUNT,0), "+//7
   			  " TO_CHAR(C.DEPOSIT_DATE,'DD-MM-YYYY'), "+//8
   			  " NVL(C.DEPOSIT_COMMENTS,'-'), "+//9
					" NVL(A.CHEQUE_NO,'-'), "+//10
  			  " NVL(C.ACC_NO,'-'), "+//11
  			  " NVL(C.BRANCH_CODE,'-'), "+//12
  			  " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.BRANCH_CODE),'-'), "+//13
  			  " DECODE(C.STATUS,'Y','Deposit Realised','C','Deposit Returned') "+//14
 				"FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B,"+m_schema_name+".FA_OP_PRO_DEPOSIT C "+
 				"WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
 				"AND A.DEPOSIT_NO=C.DEPOSIT_NO "+
 				"AND B.FACILITY_NO='"+m_facility_no+"' AND  B.CLIENT_CODE='"+m_client_code+"' "+
				"AND TO_DATE(TO_CHAR(C.DEPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 				"TO_DATE(TO_CHAR(C.DEPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')	");


			
          boolean mflag8=true;							
			 		boolean more8 = rs8.next();
						
						out.println("<HTML><HEAD><TITLE>Deposit Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Deposit Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Deposit Date</B></TD></TR>");
					 	out.println("</TABLE>"); 
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more8){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more8){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs8.getString(1)+"')\" style='cursor:hand' ><u>"+rs8.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs8.getString(2)+"')\" style='cursor:hand' ><u>"+rs8.getString(3)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >"); 
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='15%' ><DIV class=div_input><b>Deposit No</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Mode</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Deposit Amount</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Date</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Comment</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Account No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Status</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more8){
				
							if(mflag8){
								out.println("<tr class=tr_input>");
								mflag8=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag8=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_deposit_details('"+rs8.getString(4)+"')\" style='cursor:hand'><u>"+rs8.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_receipt_details('"+rs8.getString(5)+"')\" style='cursor:hand'><u>"+rs8.getString(5)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs8.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs8.getDouble(7))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs8.getString(8)+"</td>");
							out.println("<td width='10%'  class=div_input >"+rs8.getString(9)+"</td>");
							out.println("<td width='10%'  class=div_input >"+rs8.getString(10)+"</td>");
							out.println("<td width='10%'  class=div_input >"+rs8.getString(11)+"</td>");
							out.println("<td width='12%'  class=div_input >"+rs8.getString(13)+"</td>");
							out.println("<td width='10%'  class=div_input >"+rs8.getString(14)+"</td>");
							out.println("</tr>");
							more8 = rs8.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_PAYMENT_DETAIL_REPORT")){
			
			//------------------ PAYMENT DETAIL REPORT ------------------------------

				double m_totoal=0;
				
				rs5= stmt5.executeQuery(" SELECT  NVL(FACILITY_NO,'-'),"+//1
  		  "	NVL(CLIENT_CODE,'-'), "+//2
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
				" NVL(PAYMENT_CODE,'-'),"+//4
				" DECODE(PAY_STATUS,'ENTER','Payment to be Approved','APPR1','Payment Approved','CONF','Payment Confirmed','CANCEL','Payment Disapproved','PRINT','Print','DISB','Disbursement'), "+//5
  		  "	NVL(PAYMENT_AMOUNT,0),"+//6
  		  "	NVL(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'-'),"+//7
  		  "	NVL(SETTLE_MODE,'-'),"+//8
  		  "	NVL(CHEQUE_NO,'-'),"+//9
				"	NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//10
  		  "	NVL(REC_AMOUNT_CURR,0),"+//11
				" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(LIC_BRANCH_CODE),'-'), "+//12
				" LIC_ACC_NO "+//13
 				"	FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
				" WHERE FACILITY_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_code+"' "+
        " AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
        " TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')");	
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						
						out.println("<HTML><HEAD><TITLE>Payment Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report  From : "+m_from_date+" To : "+m_to_date+"</B></TD></TR>");
					 	out.println("</TABLE>");	 
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(3)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >"); 					
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='12%' ><DIV class=div_input><b>Payment Code </b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Payment Status</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Payment Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Lic. Branch Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Lic. Account No</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							m_totoal=m_totoal+rs5.getDouble(6);
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs5.getString(4)+"')\" style='cursor:hand'><u>"+rs5.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"\" >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(8)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(9)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(13)+"</td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		
					out.println("<tr>");
					out.println("<td width='12%' class=div_input ></td>");
					out.println("<td width='12%' class=div_input onClick=\"\" >Total</td>");
					out.println("<td width='10%' align='right' class=div_input ><B>"+nf.format(m_totoal)+"</B></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("<td width='10%' class=div_input onClick=\"\" ></td>");
					out.println("</tr>");
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}		
			else if(m_chksql.equals("LOAD_ALLOCATED_INQUIRY_DETAIL_REPORT")){
			
			//------------------ ALLOCATED INQUIRY DETAIL REPORT - 12 -----------------------------

 				// ALLOCATED INQUIRIES
  			rs5= stmt5.executeQuery(" SELECT INQUIRY_CODE,"+//1
  		  "	NVL(CLIENT_NAME,'-'), "+//2
    		"	NVL(TEL_NO,'-'),"+//3
    		" NVL(MOBILE_NO,'-'),"+//4
    		" NVL(FAX_NO,'-'),"+//5
   			" NVL(LEGAL_ENTITY,'-'),"+//6
    		" NVL(INITIATION_TYPE,'-'),"+//7
  	 		" NVL(CLIENT_CATEGORY,'-'),"+//8
    		" NVL(LEAD_SOURCE_CATEGORY,'-'),"+//9
  			" NVL(LEAD_SOURCE_NAME,'-'),"+//10
    		" NVL(INTRODUCER,'-'),"+//11
    		" NVL(ID_NO,'-'),"+//12
    		" NVL(EMAIL,'-'),"+//13
    		" NVL(TEAM,'-'), "+//14
   			" NVL(MK_OFFICER,'-'),"+//15
    		" NVL(MK_SUPERVISOR,'-'),"+//16
  	  	" NVL(CONTACT_PERSON,'-') "+//17
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
 				" WHERE DIVISION_CODE='FA' AND "+
 				" INQUIRY_CODE IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO) ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Allocated Inquiry Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Allocated Inquiry Details Report </B></TD></TR>");
					 	out.println("</TABLE>");

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Inquiry Code </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Tel No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Mobile No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Fax No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Legal Entity</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Initiation Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Introducer</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>ID No/Business Cert. No. </b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>E-mail</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Team</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Marketing Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Marketing Supervisor</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input >"+rs5.getString(1)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(2)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(4)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(14)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(15)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(16)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(17)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_UNALLOCATED_INQUIRY_DETAIL_REPORT")){

			
			//------------------ UNALLOCATED INQUIRY DETAIL REPORT - 13 -----------------------------

 				// UNALLOCATED INQUIRIES
  			rs5= stmt5.executeQuery(" SELECT INQUIRY_CODE,"+//1
  		  "	NVL(CLIENT_NAME,'-'), "+//2
    		"	NVL(TEL_NO,'-'),"+//3
    		" NVL(MOBILE_NO,'-'),"+//4
    		" NVL(FAX_NO,'-'),"+//5
   			" NVL(LEGAL_ENTITY,'-'),"+//6
    		" NVL(INITIATION_TYPE,'-'),"+//7
  	 		" NVL(CLIENT_CATEGORY,'-'),"+//8
    		" NVL(LEAD_SOURCE_CATEGORY,'-'),"+//9
  			" NVL(LEAD_SOURCE_NAME,'-'),"+//10
    		" NVL(INTRODUCER,'-'),"+//11
    		" NVL(ID_NO,'-'),"+//12
    		" NVL(EMAIL,'-'),"+//13
    		" NVL(TEAM,'-'), "+//14
   			" NVL(MK_OFFICER,'-'),"+//15
    		" NVL(MK_SUPERVISOR,'-'),"+//16
  	  	" NVL(CONTACT_PERSON,'-') "+//17
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
 				" WHERE DIVISION_CODE='FA' AND "+
 				" INQUIRY_CODE NOT IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO) ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Unallocated Inquiry Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Unallocated Inquiry Details Report </B></TD></TR>");
					 	out.println("</TABLE>");

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Inquiry Code </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Tel No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Mobile No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Fax No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Legal Entity</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Initiation Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Introducer</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>ID No/Business Cert. No.</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>E-mail</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Team</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Marketing Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Marketing Supervisor</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input >"+rs5.getString(1)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(2)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(4)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(14)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(15)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(16)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(17)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}

			else if(m_chksql.equals("LOAD_ALLOCATED_QUOTATION_DETAIL_REPORT")){

			
			//------------------ ALLOCATED QUOTATION DETAIL REPORT - 14 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0) "+//8
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
				" WHERE QUOTATION_NO IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");	
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Allocated Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Allocated Quotation Details Report </B></TD></TR>");
					 	out.println("</TABLE>");
							
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_UNALLOCATED_QUOTATION_DETAIL_REPORT")){

			
			//------------------ UNALLOCATED QUOTATION DETAIL REPORT - 15 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0) "+//8
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
				" WHERE QUOTATION_NO NOT IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");	
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Unallocated Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Unallocated Quotation Details Report </B></TD></TR>");
					 	out.println("</TABLE>");
							
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}

			else {
			    out.println("Undefined");
			}

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



