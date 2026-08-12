//Created by Yohan Gunarathna on 24-10-2006 at  11.40 A.M.
//Application Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_CR_PRO_Payment_Requsion_Document_Follow_up extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	
	Statement stmt2,stmt1,stmt3,stmt4,stmt5,stmt6,stmt_ref_no;
	public ResultSet rs2,rs1,rs3,rs4,rs5,rs6,rs_ref_no;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= con_method.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
			int m_count_payment_no=0;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");

			stmt = conn.createStatement ();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt = conn.createStatement();
			stmt3 = conn.createStatement();
			stmt4 = conn.createStatement();
			stmt5 = conn.createStatement();
			stmt6 = conn.createStatement();
			stmt_ref_no= conn.createStatement();

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
					else if(m_chksql.trim().equals("asset_details")){
					   
					String  m_application_no = req.getParameter("application_no").trim();
					String  m_client         = req.getParameter("client_code").trim();		
					String  m_value_date     = req.getParameter("value_date").trim();				
					String  m_screen_name="";
					String  m_payment_no="";
					
					
					if(req.getParameter("screen_name").trim()!=null){
					m_screen_name=req.getParameter("screen_name").trim();
					}
					
					
								
			//out.println		
			   if (m_screen_name.equals("account_selection")){
					m_payment_no=req.getParameter("payment_no").trim();
					
					//String m_status=req.getParameter("status").trim();
					
			   rs_ref_no = stmt_ref_no.executeQuery 
				 (" SELECT  "+
         " C.SUS_REF_NO, "+
         " REF_NO , "+
				 " NVL(A.TAX_INV_NO,'-') , "+
  			 " NVL(TO_CHAR(A.TAX_INV_DATE,'DD-MM-YYYY'),'-') "+
				 " FROM "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B , "+
         " "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C "+
		     " WHERE A.PAYMENT_NO=B.PAYMENT_NO "+
         " AND A.SUS_REF_NO=C.SUS_REF_NO "+
        // " AND B.PROCESS_STATUS='"+m_status+"' "+ //'RE-APP'
         " AND A.ENTRY_TYPE='V' "+
         " AND C.SUSPENSE_ENTRY_TYPE='V' "+
         " AND UPPER(B.PAYMENT_NO)=UPPER('"+m_payment_no+"') ");
				}
				else {
					
			//out.println		
			rs_ref_no = stmt_ref_no.executeQuery 
			(" SELECT  "+	
			" DISTINCT C.APPLICATION_NO APPLICATION_NO,  "+//1
			" SUS_REF_NO, "+ //2
			" REF_NO, "+ //3
			" nvl(C.FINANCE_NO,'-') FINANCE_NO,  "+ //4
			" (B.BAL_TO_BE_PAID) BAL_TO_BE_PAID,  "+ //5
			" (C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT ,  "+ //6
			" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,  "+ //7
			" NVL(B.RECEIVER,'-')  RECEIVER, 		 "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VENDOR_NAME,  "+ //9
			" (B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT,  "+ //10
			" (B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT  "+ //11
			" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-' )  "+//12
			" FROM   "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,   "+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			" WHERE   "+
			" B.REF_NO=D.INVOICE_NO  "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO  "+
			" AND B.BAL_TO_BE_PAID >0   "+
			" AND D.ACTIVE_STATUS<>'C' "+
			" AND UPPER(C.APPLICATION_NO)='"+m_application_no+"'    "+
			" AND VALUE_DATE = TO_DATE('"+m_value_date+"','DD-MM-YYYY') ");
			
			}
			
			boolean more_ref_no=rs_ref_no.next();
			
			int d=0;
			int f=0;
			int j=0;
			String m_ref="";
			String m_sus_ref="";
			String m_tax_inv_num =""; 
      String m_tax_inv_date="";

      while(more_ref_no) {
			if (m_screen_name.equals("account_selection")){
		  m_sus_ref=rs_ref_no.getString(1);
			m_ref=rs_ref_no.getString(2);
			m_tax_inv_num=rs_ref_no.getString(3);
			m_tax_inv_date=rs_ref_no.getString(4);
			
      }
			else{
			m_sus_ref=rs_ref_no.getString(2);
			m_ref=rs_ref_no.getString(3);
			}
			
		 rs3=stmt3.executeQuery
			//out.println
		 ("SELECT INVOICE_NO,APPLICATION_NO, "+
		 "nvl(ENGINE_NO,'-'),nvl(CHASSIS_NO,'-'),nvl(REG_NO,'-'),NVL(CR_BOOK_NO,'-'),nvl(DISTRICT_CODE,'-'), "+
		 "TO_CHAR(CR_PRINT_DATE,'DD-MM-YYYY'),TO_CHAR(INSURANCE_DATE,'DD-MM-YYYY'),TO_CHAR(REVENUE_LICENSE_DATE,'DD-MM-YYYY'), "+
		 "TO_CHAR(LUXURY_TAX_DATE,'DD-MM-YYYY'),TO_CHAR(DRIVING_LICENSE_DATE,'DD-MM-YYYY'),VEHICLE_NO "+   
		 "FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
		 "WHERE UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
		 "AND UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') ");

			out.println("<hr>");
		  int f1=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<BR>");
			
			

      

			while(rs3.next()){

			if(f1==0){
			
			if (m_screen_name.equals("account_selection")){
			
			out.println("<tr><td><b><u>Tax Invoice Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>"); 
      
			if (!m_tax_inv_num.equals("-")) {
			out.println("<tr>");  
			out.println("<td  width='20%' align=\"left\" ><b>Tax Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" ><input value=\""+m_tax_inv_num+"\" class='txt_input'  maxlength='30' type='hidden' name=TXT_TAX_INVOICE_NO_"+f+" disabled ><B>: "+m_tax_inv_num+"</B></td>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Date:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" ><input value=\""+m_tax_inv_date+"\" class='txt_input'  maxlength='30' type='hidden' name=TXT_TAX_INV_DATE_"+f+" disabled ><B>: "+m_tax_inv_date+"</B></td>");
			out.println("</tr>"); 
			}
			else{
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Tax Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" ><input value=\"\" class='txt_input' maxlength='30'  type='text' name=TXT_TAX_INVOICE_NO_"+f+"></td>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Date:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('8',"+f+")  >   Calendar</a>");
			out.println("</td>");
			out.println("</tr>"); 
			}
      }
			/*else {
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Tax Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" ><input value=\"\" class='txt_input' maxlength='30'  type='text' name=TXT_TAX_INVOICE_NO_"+f+"></td>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Date:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"\">");
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('8',"+f+")  >   Calendar</a>");
			out.println("</td>");
			out.println("</tr>"); 
			}*/
			
      out.println("<tr></tr>");
			out.println("<tr></tr>"); 
	
			
 
			
			
			out.println("<tr><td><b><u>Invoice Details</td></tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");

			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\" ><b>Invoice No:</td><td width='20%' align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs3.getString(1)+"')\"><U><b>:"+rs3.getString(1)+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr></tr>");
			out.println("<tr></tr>");
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='20%' align=\"left\">Engine No</td>");
			out.println("<td width='20%' align=\"left\">Chassis Mode</td>");
			out.println("<td width='20%' align=\"left\">Vehicle No</td>");
			out.println("<td width='20%' align=\"left\">CR Book No</td>");
			out.println("<td width='20%' align=\"left\">District Code</td>");
			out.println("</tr >"); 

			}
			
				
			if(f1>0 && f1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}



			if(rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(3).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(3)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_ENGIN_NO_"+f+" disabled></td>"); 
			}		
			
			if(rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+"></td>"); 
			}	
			if(!rs3.getString(4).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(4)+"\" class='txt_input'  maxlength='50' type='text' name=TXT_CHASSIS_"+f+" disabled></td>"); 
			}	
			if(rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+"></td>"); 
			}
			if(!rs3.getString(5).equals("-")){
			out.println("<td  align=\"left\"><input value=\""+rs3.getString(5).trim()+"\" class='txt_input' maxlength='20'  type='text' name=TXT_VEHICLE_NO_"+f+" disabled></td>"); 
			}
			
			if(rs3.getString(6).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" ></td>"); 
			}
			if(!rs3.getString(6).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(6)+"\" class='txt_input'  maxlength='15' type='text' name=TXT_CR_BOOK_NO_"+f+" disabled></td>"); 
			}

			if(!rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\""+rs3.getString(7)+"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"check_district("+f+")\">");		
			}
			if(rs3.getString(7).equals("-")){
			out.println("<td align=\"left\"><input value=\"\" class=\"txt_input\" maxlength=\"10\" type=\"text\" name=TXT_DISTRICT_CODE_"+f+"   onblur=\"help_button_1("+f+")\">");		 //check_district("+f+")
			}
			out.println("<input class=but_input type=button name=BUT_TXT_DISTRICT_CODE_"+f+" value=\"Help\" onClick=help_button_1("+f+")></td>"); 
      out.println("<input type=hidden name=HID_TXT_REF_NO_"+f+" value="+rs3.getString(1)+" >");
			out.println("<input type=hidden name=HID_TXT_SUS_REF_NO"+f+" value="+m_sus_ref+" >");
			out.println("</tr>");
			
			if(f1==0){

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' align=\"left\">CR Book Date</td>");
			out.println("<td width='20%' align=\"left\">Insurance Date</td>");
			out.println("<td width='20%' align=\"left\">Revenue License Expiry Date</td>"); // insert 'Expiry' by Prabash on 24-04-2012 
			out.println("<td width='20%' align=\"left\">Luxury Tax Date</td>");
			out.println("<td width='20%' align=\"left\">Driving License Date</td>");
			out.println("</tr >"); 

			}
			
			
			
			if(f1>0 && f1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
		
			if(rs3.getString(8)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\""+rs3.getString(8).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\""+rs3.getString(8).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\""+rs3.getString(8).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\" >");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");   
			}
			if(rs3.getString(8)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_dd("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"print_date_mm("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_PRINT_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"print_date_yy("+f+")\">");
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('2',"+f+")>   Calendar</a>");
			out.println("</td>");
			}
			
			
			if(rs3.getString(9)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\""+rs3.getString(9).substring(0,2)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\""+rs3.getString(9).substring(3,5)+"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\""+rs3.getString(9).substring(6,10)+"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
		
			}
			if(rs3.getString(9)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_DD"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_MM"+f+" value=\"\" maxlength=2 size=\"2\" onblur=\"check_insu_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_INSURANCE_DATE_YY"+f+" value=\"\" maxlength=4 size=\"4\" onblur=\"check_insu_date("+f+")\" >");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('3',"+f+")>   Calendar</a>");
			out.println("</td>");
	
			}
		
			
			if(rs3.getString(10)!=null){

			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(10).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(10).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(10).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");
	
			}
			if(rs3.getString(10)==null){

			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_REVENUE_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_rev_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('4',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
		
			if(rs3.getString(11)!=null){

			
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\""+rs3.getString(11).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\""+rs3.getString(11).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\""+rs3.getString(11).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");
		
			}
			if(rs3.getString(11)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_LUXURY_TAX_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_tax_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('5',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
		
			
			if(rs3.getString(12)!=null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\""+rs3.getString(12).substring(0,2)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\""+rs3.getString(12).substring(3,5)+"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\""+rs3.getString(12).substring(6,10)+"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
			if(rs3.getString(12)==null){
			out.println("<td ><input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_DD"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_MM"+f+" value=\"\" maxlength=\"2\" size=\"2\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<input type=\"text\" style=\"{text-align:right;}\" class=\"txt_input5\" name=TXT_DRIVING_LICENSE_DATE_YY"+f+" value=\"\" maxlength=\"4\" size=\"4\" onblur=\"check_dri_date("+f+")\">");		
			out.println("<a href style='{cursor:hand; }' onclick=load_calendar('7',"+f+")>   Calendar</a>");
			out.println("</td>");

			}
			
			out.println("</tr>");

			
			f=f+1;
      f1=f1+1;
			}
			//out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("</table >"); 
			out.println("<HR>");

			//-----asset details-------//
			rs4=stmt4.executeQuery
			// out.println
			("SELECT DOCUMENT_TYPE,"+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE),nvl(REMARK,'-') AS REMARK,nvl(decode(STATUS,'Y','Yes','N','No','A','Applicable'),'-') AS STATUS, "+
				"SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT, "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------
				
				
				"                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) SCREEN "+

				
				
				//--------------------------------------------------------------------------------------------------------------------------------------------
				
				
				
				
        "WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				
				"AND DOCUMENT_TYPE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT
				//"AND STATUS!='Y' "+
				"AND STATUS NOT IN ('Y','A') "+
        "AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+
				//"AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+
        "AND DOCUMENT_TYPE IN (SELECT CODE FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED  "+
        "                          WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
        "                          AND CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                         )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
	   		"                                                WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 ) "+
        "                          ) "+
        " UNION "+
        "SELECT A.CODE CODE,"+m_schema_name+".AF_CO_GET_DOC_DESC(A.CODE),'-' AS REMARK,'-' AS STATUS,SCREEN.FROM_SCREEN_NO,SCREEN.TO_SCREEN_NO "+
				"FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A , "+
				
				//--to get from and to screen no--------------------------------------------------------------------------------------------------------------

        "                                (SELECT CODE,FROM_SCREEN_NO,TO_SCREEN_NO FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				//"																	AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) SCREEN "+


				//--------------------------------------------------------------------------------------------------------------------------------------------
				
        "                           WHERE DOC_APP_TYPE='ASSET' AND ACTIVE_STATUS='Y' "+
				"														AND A.CODE=SCREEN.CODE "+//TO SCRREN AND FROM SCREEN QUERY RESTRICT

        "                           AND A.CODE IN "+
        "                                (SELECT CODE FROM "+
        "                                 "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
        "                                 WHERE PRODUCT_CODE IN (SELECT TRANSACTION_TYPE  "+
				"                                                       FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  "+
				"                                                       WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
			//	"																												AND APPLICATION_STATUS='ACTIVATED' "+
        "                                                        ) "+
        "                                 AND ITEM_CAT_CODE IN (SELECT "+m_schema_name+".AF_CO_GET_ITEM_CATEGORY_CODE(MODEL_CODE)  "+
				"	                                                   FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B  "+
				"	                                                   WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
        "                                                       AND UPPER(INVOICE_NO)=UPPER('"+m_ref+"') "+
        "                                                       ) "+
        "                                 AND ITEM_CAT_CODE IS NOT NULL "+
        "                                 AND (FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        )  "+
				"	                             AND TO_SCREEN_NO >=   (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
				"	                                                    WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1' "+
        "                                                        ) "+
        "                                     )  "+
        "                                 AND ACTIVE_STATUS='Y' ) "+
				
				"														AND A.CODE NOT IN (SELECT DOCUMENT_TYPE "+
				"																						 FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
        "																						 WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
				//"																						 AND UPPER(OTHER_NO)=UPPER('"+m_sus_ref+"') "+

        "																						 AND UPPER(PRO_INVOICE_NO)=UPPER('"+m_ref+"') "+				
        "                                  ) ORDER BY STATUS ASC ");
				
				
			int j1=0;
			out.println("<br>");
			//out.println("<HR>");
			//out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			boolean more4=rs4.next();
			if(!more4){
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr>"); 
			out.println("<td  width='*%' align=\"left\"><b>Asset Document Details </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  width='*%' class=pdn_txtpos2 align=\"center\"><b>No Asset Document Details Exists</td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
      }
			
			if(more4){
			out.println("<HR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more4){
			
			
			
			if(j1==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Asset Document Details</td>"); 
			out.println("</tr>"); 
				
				
			out.println("<tr class=pdn_txtpos2>"); 

			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
			out.println("</tr >"); 
			
			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs4.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
      
			


			else{
			if(!rs4.getString(4).equals("Yes"))
			if(j1>0 && j1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}	
			if(!rs4.getString(4).equals("Yes")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs4.getString(1)+"')\"><U>"+rs4.getString(2)+"<input value="+rs4.getString(1)+" type='hidden' name=TXT_DOC_CODE_"+j+"></td>"); 
			out.println("<td align=\"center\">"+rs4.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs4.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_status_"+j+" value=\"N\" unchecked onclick=\"check_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_napp_status_"+j+" value=\"N\" unchecked onclick=\"check_napp_status("+j+")\" onblur=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_REMARKS_"+j+"></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_fol_status_"+j+" value=\"N\" unchecked onclick=\"check_change_fol("+j+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_FOL_REMARKS_"+j+"></td>"); 
			out.println("</tr>");
			out.println("<input type=\"hidden\" name=hid_TXT_FROM_SCREEN_"+j+" value="+rs4.getString(5)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_TO_SCREEN_"+j+" value="+rs4.getString(6)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_INVOICE_NO_"+j+" value="+m_ref+">");
			}
			more4=rs4.next();
			j=j+1;
			j1=j1+1;
			}

			//out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");

			out.println("</table >"); 
			out.println("<HR>");
			}
			//out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			
			/*
			
			//-----client details-------//

			 //out.println	
				rs5=stmt5.executeQuery
			(
			"SELECT A.CODE,A.DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS,NVL(NEW_QUERY.FROM_SCREEN_NO,0),NVL(NEW_QUERY.TO_SCREEN_NO,0) "+
			"			   FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+  
			//-TO GET SCREEN NO-----------------------------------------------------------------------------
						"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"																				and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) NEW_QUERY "+

			//---------------------------------------------------------------------------------------------
			" 			   WHERE     "+
			"            A.CODE IN ( SELECT  CODE "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"																				and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) "+
			"                 AND  A.DOC_APP_TYPE='CLIENT' "+
			"                 AND  A.ACTIVE_STATUS='Y'   "+
			"									AND A.CODE=NEW_QUERY.CODE "+
			
			"         UNION   "+
			        
			"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, DECODE(C.STATUS,'Y','Yes','N','No','A','Applicable') STATUS,nvl(NEW_QUERY.FROM_SCREEN_NO,0),nvl(NEW_QUERY.TO_SCREEN_NO,0) "+
			"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C , "+ 

			
			"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) NEW_QUERY "+
		
			
			"   WHERE C.DOCUMENT_TYPE=A.CODE  "+
			"   AND A.CODE=NEW_QUERY.CODE "+
			"   AND C.STATUS !='Y' "+
			"   AND C.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			"   AND C.CLIENT_CODE=UPPER('"+m_client+"')  "+
			"   AND C.PRO_INVOICE_NO IS NULL "+
			//"AND upper(C.OTHER_NO)=upper('"+m_sus_ref+"') "+
			"  ORDER BY STATUS ASC ");

		//int d1=0;//================================================================================
			//out.println("<table align='center' width='100%' class='table' border=\"0\">"); 

			int d1=0;

			boolean more5=rs5.next();
			
			if(!more5){
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr>"); 
			out.println("<td  width='*%' align=\"left\"><b>Client Document Details </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  width='*%' class=pdn_txtpos2 align=\"center\"><b>No client document details exists</td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
      }
			
			if(more5) {
			//int d1=0;
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more5){
			
			
			
			if(d1==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Client Document Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
			out.println("</tr >"); 
			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
			else{
			if(!rs5.getString(4).equals("Yes"))
			if(d1>0 && d1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}		
			
			if(!rs5.getString(4).equals("Yes")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs5.getString(1)+"')\"><U>"+rs5.getString(2)+"<input value="+rs5.getString(1)+" type='hidden' name=TXT_CLIENT_DOC_CODE_"+d+"></td>"); 
			out.println("<td align=\"center\">"+rs5.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs5.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_status_"+d+" value=\"N\" unchecked onclick=\"check_client_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_napp_status_"+d+" value=\"N\" unchecked onclick=\"check_client_napp_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input' name=TXT_CLIENT_REMARKS_"+d+" maxlength='50' type='text' ></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_fol_status_"+d+" value=\"N\" unchecked onclick=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CLIENT_FOL_REMARKS_"+d+"></td>"); 
			out.println("</tr>");
			out.println("<input type=\"hidden\" name=hid_TXT_FROM_SCREEN_CLIENT_"+d+" value="+rs5.getString(5)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_TO_SCREEN_CLIENT_"+d+" value="+rs5.getString(6)+">");
      }
			more5=rs5.next();
			d=d+1;
			d1=d1+1;
			}

			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

			out.println("</table >"); 
			out.println("<HR>");
      }*/
			
			//out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");

     more_ref_no=rs_ref_no.next();
     }
			out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");
     
			}
			
			
			else if(m_chksql.trim().equals("client_details")){
					   
					String  m_application_no = req.getParameter("application_no").trim();
					String  m_client         = req.getParameter("client_code").trim();		
					String  m_value_date     = req.getParameter("value_date").trim();				
					
			/*rs_ref_no = stmt_ref_no.executeQuery 
			(" SELECT  "+	
			" DISTINCT C.APPLICATION_NO APPLICATION_NO,  "+//1
			" nvl(C.FINANCE_NO,'-') FINANCE_NO,  "+ //2
			" SUS_REF_NO, "+ //3
			" REF_NO, "+ //4
			" (B.BAL_TO_BE_PAID) BAL_TO_BE_PAID,  "+ //5
			" (C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT ,  "+ //6
			" TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY') VALUE_DATE,  "+ //7
			" NVL(B.RECEIVER,'-')  RECEIVER, 		 "+ //8
			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VENDOR_NAME,  "+ //9
			" (B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT,  "+ //10
			" (B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT  "+ //11
			" ,NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-' )  "+//12
			" FROM   "+
			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,   "+
			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D  "+
			" WHERE   "+
			" B.REF_NO=D.INVOICE_NO  "+
			" AND D.APPLICATION_NO=C.APPLICATION_NO  "+
			" AND B.BAL_TO_BE_PAID >0   "+
			" AND D.ACTIVE_STATUS<>'C' "+
			" AND UPPER(C.APPLICATION_NO)='"+m_application_no+"'    "+
			" AND VALUE_DATE = TO_DATE('"+m_value_date+"','DD-MM-YYYY') ");
			
			boolean more_ref_no=rs_ref_no.next();
			*/
			
			int d=0;
			int f=0;
			int j=0;
			String m_ref="";
			String m_sus_ref="";
    //  while(more_ref_no) {
		//	m_ref=rs_ref_no.getString(4);
    //  m_sus_ref=rs_ref_no.getString(3);
						     
			//++++++++++++++++++++++++++++++++++++++++++ CLIENT DETAILS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
			//out.println	
				rs5=stmt5.executeQuery
			(
			"SELECT A.CODE,A.DESCRIPTION,NVL(NULL,'-') REMARK,NVL(NULL,'-') STATUS,NVL(NEW_QUERY.FROM_SCREEN_NO,0),NVL(NEW_QUERY.TO_SCREEN_NO,0) "+
			"			   FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+  
			//-TO GET SCREEN NO-----------------------------------------------------------------------------
						"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) NEW_QUERY "+

			//---------------------------------------------------------------------------------------------
			" 			   WHERE     "+
			"            A.CODE IN ( SELECT  CODE "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"																				and application_status='ACTIVATED') "+
			"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)) "+
			"                 AND  A.DOC_APP_TYPE='CLIENT' "+
			"                 AND  A.ACTIVE_STATUS='Y'   "+
			"									AND A.CODE=NEW_QUERY.CODE "+
			
			"         UNION   "+
			        
			"  SELECT DISTINCT C.DOCUMENT_TYPE ,A.DESCRIPTION D,NVL(C.REMARK,'-') REMARK, DECODE(C.STATUS,'Y','Yes','N','No','A','Applicable') STATUS,nvl(NEW_QUERY.FROM_SCREEN_NO,0),nvl(NEW_QUERY.TO_SCREEN_NO,0) "+
			"  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED A, "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT C , "+ ///*"+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE B,*/

			
			"            ( SELECT  CODE,FROM_SCREEN_NO,TO_SCREEN_NO "+
			"                  FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE "+
			"                  WHERE  ENTITY_TYPE=(SELECT CLIENT_CATEGORY "+
			"                                      FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
			"                                      WHERE CLIENT_CODE=UPPER('"+m_client+"')) "+
			"                  AND DIVISION_CODE='AF' "+
			"                  AND ITEM_CAT_CODE IS NULL "+
			"                  AND ACTIVE_STATUS='Y' "+
			"                  AND FROM_SCREEN_NO <=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+ 
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+  
			"				   AND TO_SCREEN_NO >= 	(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN   "+
			"				  						WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') "+
			"                  AND PRODUCT_CODE in (SELECT TRANSACTION_TYPE "+
			"                                      FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                                       WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			/*"                  AND  CODE NOT IN (SELECT DOCUMENT_TYPE "+
			"                                    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT "+
			"                                   WHERE APPLICATION_NO=UPPER('"+m_application_no+"') "+
			"                 AND  CLIENT_CODE=UPPER('"+m_client+"') "+
			"                 AND  PRO_INVOICE_NO IS NULL)*/ 
			") NEW_QUERY "+

			
			/*"  WHERE B.ENTITY_TYPE=( SELECT CLIENT_CATEGORY   "+
			"                         FROM  "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                         WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                       )   "+
			            
			"  AND DOC_APP_TYPE='CLIENT' AND A.ACTIVE_STATUS=('Y')  "+
			"  AND C.DOCUMENT_TYPE IN  "+
			"                      (SELECT CODE  "+
			"                      FROM "+m_schema_name+".AF_CO_MAS_DOCUMENT_APPLICABLE  "+
			"                      WHERE FROM_SCREEN_NO <= (SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                               WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1')  "+
			"                      AND TO_SCREEN_NO >=(SELECT POSITION FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN  "+
			"                                          WHERE DIVISION_CODE='AF' AND SCREEN_NAME='AF_CR_PRO_PAYMENT_REQUSITION_MAIN1') AND  "+
			"                      ENTITY_TYPE IN (SELECT CLIENT_CATEGORY   "+
			"                                       FROM "+m_schema_name+".AF_CO_MAS_CLIENT   "+
			"                                       WHERE CLIENT_CODE=UPPER('"+m_client+"')  "+
			"                                       )   "+
			  
			"   AND  PRODUCT_CODE in (SELECT TRANSACTION_TYPE FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
			"                         WHERE APPLICATION_NO=UPPER('"+m_application_no+"') ) "+
			//"													and application_status='ACTIVATED') "+
			                                                                 
			"   AND B.CODE=A.CODE  "+
			"   AND ACTIVE_STATUS=('Y'))  "+
			*/
			
			
			"   WHERE C.DOCUMENT_TYPE=A.CODE  "+
			"   AND A.CODE=NEW_QUERY.CODE "+
			"   AND C.STATUS !='Y' "+
			"   AND C.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
			"   AND C.CLIENT_CODE=UPPER('"+m_client+"')  "+
			"   AND C.PRO_INVOICE_NO IS NULL "+
			//"AND upper(C.OTHER_NO)=upper('"+m_sus_ref+"') "+
			"  ORDER BY STATUS ASC ");

			int d1=0;

			boolean more5=rs5.next();
			if(!more5) {
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr>"); 
			out.println("<td  width='*%' class=pdn_txtpos2 align=\"center\"><b>No client document details exists</td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			}
			
			if(more5) {
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			while(more5){
			
			if(d1==0){
			out.println("<tr>"); 
			out.println("<td  width='20%' align=\"left\"><b><u>Client Document Details</td>"); 
			out.println("</tr>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='30%' align=\"left\">Doc Code</td>");
			out.println("<td width='13%' align=\"center\">Previous Remarks</td>");
			out.println("<td width='12%' align=\"center\">Previous Status</td>");
			out.println("<td width='6%' align=\"center\">Status</td>");
			out.println("<td width='11%' align=\"center\">Not Applicable</td>");
			out.println("<td width='10%' align=\"center\">Remarks</td>");
			out.println("<td width='6%' align=\"center\">Followup</td>");
			out.println("<td width='12%' align=\"center\">Followup Remarks</td>");
			out.println("</tr >"); 
			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr class=\"tr_input1\"><td><DIV id=DIV_TXT_SYSTEM1  class=div_input style=color:red><b><u>System Exception</div></td></tr>");
			}
			}

			if(rs5.getString(4).equals("Applicable")){
			out.println("<tr style=\"{color:red}\" border=1px;#D5D0C6;inset class=tr_input1>");
			}
			else{
			if(!rs5.getString(4).equals("Yes"))
			if(d1>0 && d1%2==1){
     	out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			}		
			
			if(!rs5.getString(4).equals("Yes")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_document_drill('"+rs5.getString(1)+"')\"><U>"+rs5.getString(2)+"<input value="+rs5.getString(1)+" type='hidden' name=TXT_CLIENT_DOC_CODE_"+d+"></td>"); 
			out.println("<td align=\"center\">"+rs5.getString(3)+"</td>"); 
			out.println("<td align=\"center\">"+rs5.getString(4)+"</td>"); 


			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_status_"+d+" value=\"N\" unchecked onclick=\"check_client_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_napp_status_"+d+" value=\"N\" unchecked onclick=\"check_client_napp_status("+d+")\" onblur=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input' name=TXT_CLIENT_REMARKS_"+d+" maxlength='50' type='text' ></td>"); 
			out.println("<td align=\"center\"><input type=\"checkbox\" name=chk_client_fol_status_"+d+" value=\"N\" unchecked onclick=\"check_client_change_fol("+d+")\"></td>");			
			out.println("<td  align=\"center\"><input value=\"\" class='txt_input'  maxlength='50' type='text' name=TXT_CLIENT_FOL_REMARKS_"+d+"></td>"); 
			out.println("</tr>");
			out.println("<input type=\"hidden\" name=hid_TXT_FROM_SCREEN_CLIENT_"+d+" value="+rs5.getString(5)+">");
			out.println("<input type=\"hidden\" name=hid_TXT_TO_SCREEN_CLIENT_"+d+" value="+rs5.getString(6)+">");
      }
			more5=rs5.next();
			d=d+1;
			d1=d1+1;
			}


			out.println("</table >"); 
			out.println("<HR>");
      }

     //more_ref_no=rs_ref_no.next();
     //}
			out.println("<input type=\"hidden\" name=hid_invoice_no value="+f+">");
			out.println("<input type=\"hidden\" name=hid_asset_doc_no value="+j+">");
			out.println("<input type=\"hidden\" name=hid_client_doc_no value="+d+">");
     
			}
			
				else if(m_chksql.trim().equals("main_page")){
				
				  String  m_application_no = req.getParameter("application_no").trim();
					String  m_client         = req.getParameter("client_code").trim();		
					String  m_value_date     = req.getParameter("value_date").trim();				
					String  m_type           = req.getParameter("type").trim();	
					
					String  m_screen_name="";
					if(req.getParameter("screen_name").trim()!=null){
					m_screen_name=req.getParameter("screen_name").trim();
					}
					String  m_payment_no="";
					if(req.getParameter("payment_no").trim()!=null){
					m_payment_no=req.getParameter("payment_no").trim();
          }
				
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				
				
				out.println("<Script>");
				
				
				out.println("var header;");		
				out.println("var ln=0;");
				out.println("var len=0;");
				out.println("var dist=0");
				out.println("var b_flag=1");
				out.println("var b_flag_1=0");
				out.println("var b_flag_2=0");
				out.println("var b_flag_3=0");
				
				out.println("var b_mesg=0");
				out.println("var b_mesg1=0");
				out.println("var b_dates=0");
				out.println("var b_dates1=0");
				out.println("var b_dates2=0");
				out.println("var b_dates3=0");
				out.println("var b_count=0");
				out.println("var b_count_new=0");
				out.println("var m_chk_status_doc=0;");
			
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Credit Process - Document Follow Up \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Credit Process - Document Follow Up - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				
				out.println("function change_val_req(row_no){")	;
        out.println("m_chk_required=\"CHK_REQUIRED\"+row_no;");				
				out.println("if(document.Form1.elements[m_chk_required].checked==true){");
				out.println("document.Form1.elements[m_chk_required].value='on'");
				out.println("}else if(document.Form1.elements[m_chk_required].checked==false){");
				out.println("document.Form1.elements[m_chk_required].value='off'");
				out.println("}");	
				out.println("}");	
				

				out.println("function validate_data(){"); 
				out.println("return true;"); 
				out.println("}"); 
								
				/*out.println("function before_submit(){ "); 
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
				//out.println("   document.Form1.hid_no_rec.value="+j+";");//Added By Nuwan De Silva
				//out.println("   document.Form1.hid_no_rec.value=document.Form1.hid_no_rec_count.value;");//Added By Nuwan De Silva
				
				out.println("		if(validate_data()){"); 
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Save_Payment_Requsion_Document_Follow_up?client_code="+m_client+"&app_no="+m_application_no+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN1&status=TM_APP';");  
				///out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details1?curr_code="+m_curr_code+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN1&status=TM_APP';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("		}"); 
				out.println("else{");
				out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
				out.println("} "); 
				out.println("} "); 
				*/
				
				
				
				
			out.println("function before_submit(){ ");
			out.println("check_foll_rem()");
			//out.println("b_count_new=1");
			out.println("if(document.Form1.hid_asset_doc_no.value==0 && document.Form1.hid_client_doc_no.value==0){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(b_count_new==1){");//
			//out.println("		check_foll_con();");
		 // out.println("	if(m_cond_status==1 && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			//out.println("alert('Please complete the pending status')");
			//out.println("}");
			//out.println("else{");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			//out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_pay_details2?pay_no="+m_pay_no+"&value_date="+m_value_date+"&client_code='+document.Form1.TXT_CLIENT_CODE.value+'&app_no="+m_application_no+"&ref="+m_ref+"&sus_ref="+m_sus_ref+"&form_name=AF_CR_PRO_PAYMENT_MAIN_APP_1&status=APPRO1';");  
			out.println("		document.Form1.action='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Save_Payment_Requsion_Document_Follow_up?client_code="+m_client+"&app_no="+m_application_no+"&form_name=AF_CR_PRO_PAYMENT_REQUSITION_MAIN1&status=TM_APP';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("}");
			out.println("} "); 
			//out.println("} "); 
			
			
			
			out.println("function check_foll_rem(){");
			out.println("b_count=0");
			
			out.println("for(var x=0;x<document.Form1.hid_asset_doc_no.value;x++){");
			out.println("if(document.Form1.hid_asset_doc_no.value !=\"0\"){");
			out.println("  chk_status=\"chk_status_\"+x");			
			out.println("  chk_napp=\"chk_napp_status_\"+x;");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+x;");
			out.println("  foll_status=\"chk_fol_status_\"+x;");
			out.println("if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_1=0");
			out.println("b_mesg=0");
			out.println("}");
			
			out.println("if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value==\"\"){");
			out.println("alert('Please enter folloup remark for asset documents')");
			out.println("document.Form1.elements[foll_status].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");
			out.println("else if(document.Form1.elements[foll_status].checked==true && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk_status].checked==true || document.Form1.elements[chk_napp].checked==true || document.Form1.elements[foll_status].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg=0");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("}");//if
			out.println("}");//for
			
			
			out.println("if(b_count!=document.Form1.hid_asset_doc_no.value){");
			out.println("b_count_new=0;");
			out.println("alert('Please enter either status ,not applicable or follow up for asset details')");	
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(b_count==document.Form1.hid_asset_doc_no.value){");
			out.println("b_count=0");
			out.println("for(var j=0;j<document.Form1.hid_client_doc_no.value;j++){");
			out.println("if(document.Form1.hid_client_doc_no.value !=\"0\"){");
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+j;");
			out.println("  chk_client_status=\"chk_client_status_\"+j");
			out.println("  foll_client_rem=\"TXT_CLIENT_FOL_REMARKS_\"+j;");
			out.println("  foll_client_status=\"chk_client_fol_status_\"+j;");
			out.println("if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[foll_client_status].checked==false){");
			out.println("b_mesg1=0");
			out.println("b_flag_1=0");
			out.println("}");
			out.println("if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value==''){");
		  out.println("alert('Please enter folloup remark for client documents')");
			out.println("document.Form1.elements[foll_client_rem].focus()");
			out.println("b_flag_1=0");
			out.println("b_mesg1=1");
			out.println("b_count_new=0");
			out.println("break");
			out.println("}");
			out.println("else if(document.Form1.elements[foll_client_status].checked==true && document.Form1.elements[foll_client_rem].value!=\"\"){");
			out.println("b_count_new=1");
			out.println("}");
			out.println("if(document.Form1.elements[foll_client_status].checked==true || document.Form1.elements[chk_client_status].checked==true || document.Form1.elements[chk_client_napp].checked==true){");
			out.println("b_flag_1=1");
			out.println("b_mesg1=1");
			out.println("}");
			out.println("b_count=b_count+b_flag_1");
			out.println("}");//if
			out.println("}");//for
			out.println("if(b_count!=document.Form1.hid_client_doc_no.value){");
			out.println("alert('Please enter either status ,not applicable or follow up for client details')");
			out.println("b_count_new=0;");
			out.println("}");
			out.println("else{");
			out.println("b_count_new=1");
			out.println("}");
			out.println("}");
			out.println("}");




			
			 /* out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Status_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
				*/				

				
			out.println("function get_payment_details(){");
			//out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql=asset_details\";");
			out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql="+m_type+"&client_code="+m_client+"&value_date="+m_value_date+"&screen_name="+m_screen_name+"&payment_no="+m_payment_no+"&application_no="+m_application_no+"\";");
			//out.println("window.open()");
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit Process - Document Follow Up - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"\";");  //m_help_msg_LAKDL_AF_PRO_CR_finance_activation
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("	new_window();"); 
					out.println("}");
					out.println("else if(m_val==\"HELP\"){"); 
					out.println(" load_help_msg();");
					out.println("}"); 
					out.println("else{");
					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("}else if(m_val==\"DELETE\"){");  
					out.println("}else if(m_val==\"RACT\"){");  
					out.println("document.Form1.hid_status.value=\"Reactivate\";");  
					out.println("}else{");  
					out.println("document.Form1.hid_status.value=\"\";");  
					out.println("}"); 
					out.println("}"); 
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
					out.println("		window.location.href='"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Payment_Requsion_Document_Follow_up?chksql=main_page&type="+m_type+"&client_code="+m_client+"&value_date="+m_value_date+"&screen_name="+m_screen_name+"&payment_no="+m_payment_no+"&application_no="+m_application_no+"';"); 
					out.println("		}"); 
					out.println("}"); 
					
					/*out.println("function search_client_details(m_client_name) {"); 
					out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Other_Payments_Approval?chksql=load_data_client&pre=\"+m_prev+\"&appro=\"+m_app+\"&qry=\"+m_app+\"&division_code=\"+m_division_code+\"&m_client_name=\"+m_client_name+\"&option=\"+m_option;"); 
					out.println("load_interface(m_url,'NORM');");
					out.println("}"); 
				  */
					
					
			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_PRINT_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_INSURANCE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
			out.println("else if(document.Form1.hid_cal_date.value=='4'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_REVENUE_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
      out.println("else if(document.Form1.hid_cal_date.value=='5'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_LUXURY_TAX_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
			
			out.println("else if(document.Form1.hid_cal_date.value=='7'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_DRIVING_LICENSE_DATE_YY\"+m_row].value=v_yy;");
			out.println("  }");	
			
      out.println("  else if(document.Form1.hid_cal_date.value=='6'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");		
			
			out.println("  else if(document.Form1.hid_cal_date.value=='8'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_DD\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_MM\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_TAX_DATE_YY\"+m_row].value=v_yy;");
			
			out.println("  }");				
			
			out.println("}");		
			
			
			out.println("function check_change_fol(row){");
			out.println("  foll_rem=\"TXT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");		
			
			
						out.println("function check_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("if(document.Form1.elements[chk_napp].checked==true && document.Form1.elements[chk_status].checked==true){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false){");
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			
			out.println("function check_napp_status(row){")	;
			out.println("  chk_napp=\"chk_napp_status_\"+row;");
			out.println("  chk_status=\"chk_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_status].checked==true && document.Form1.elements[chk_napp].checked==true){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("document.Form1.elements[chk_status].checked=false");
			out.println("document.Form1.elements[chk_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_status].checked==false && document.Form1.elements[chk_napp].checked==false){");
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_napp].value='N'");
			out.println("document.Form1.elements[chk_napp].checked=false");
			out.println("}");	
			out.println("}");	
					
			out.println("function check_client_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");
			out.println("if(document.Form1.elements[chk_client_napp].checked==true && document.Form1.elements[chk_client_status].checked==true){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_napp].checked==false && document.Form1.elements[chk_client_status].checked==false){");
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_status].value='Y'");
			out.println("}");	
		  out.println("}");	
			
			
			out.println("function check_client_napp_status(row){")	;
			out.println("  chk_client_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_client_status=\"chk_client_status_\"+row");
			out.println("		if(confirm(\"Are you sure you want to select Not Applicable Field?\")){ "); 
			out.println("if(document.Form1.elements[chk_client_status].checked==true && document.Form1.elements[chk_client_napp].checked==true){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("document.Form1.elements[chk_client_status].checked=false");
			out.println("document.Form1.elements[chk_client_status].value='N'");
			out.println("}else if(document.Form1.elements[chk_client_status].checked==false && document.Form1.elements[chk_client_napp].checked==false){");
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_client_napp].value='Y'");
			out.println("}");		
			out.println("}");	
			out.println("else{");
			out.println("document.Form1.elements[chk_client_napp].value='N'");
			out.println("document.Form1.elements[chk_client_napp].checked=false");
			out.println("}");	
			out.println("}");	
			
			out.println("function check_client_change_fol(row){");
			out.println("  foll_rem=\"TXT_CLIENT_FOL_REMARKS_\"+row;");
			out.println("  foll_status=\"chk_client_fol_status_\"+row;");
			out.println("if(document.Form1.elements[foll_status].checked==true){");
			out.println("document.Form1.elements[foll_status].value='Y'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false){");
			out.println("document.Form1.elements[foll_status].value='N'");
			out.println("}");	
			out.println("if(document.Form1.elements[foll_status].checked==false && document.Form1.elements[foll_rem].value!=\"\"){");
			out.println("document.Form1.elements[foll_rem].value=\"\"");
			out.println("}");	
			out.println("  chk_napp=\"chk_client_napp_status_\"+row;");
			out.println("  chk_status=\"chk_client_status_\"+row");
			out.println(" if((document.Form1.elements[chk_napp].checked==false && document.Form1.elements[chk_status].checked==false) && document.Form1.elements[foll_status].checked==false){");
			out.println("b_flag_3=0");
			out.println("}");		
			out.println("else{");
			out.println("b_flag_3=1");
			out.println("}");
			out.println("}");	
			
			
			out.println("function help_button_1(row) {"); 
			out.println("document.Form1.hid_val.value=row");
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_DISTRICT_CODE_sql1\";"); 
			out.println(" district='TXT_DISTRICT_CODE_'+row");
			out.println("    m_criteria = document.Form1.elements[district].value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_1(oBj) {"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value=oBj.valout[2];"); 
			out.println("dist=\"0\"");
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
			
			out.println("popupwin=window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_PRO_CR_Help_Servlet?class_in="+m_client_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
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
			out.println("	else{");
			out.println("clear_data()");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear_data()");
			out.println("	}	");
			out.println("}"); 
			
			
	 		out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_data(){");
			out.println("if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE_'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value='';"); 
			out.println(" document.Form1.elements[district].focus();"); 
			out.println("}");

			out.println("}");		







        	out.println("</Script>");

				
				
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onLoad=\"get_payment_details()\">"); //load_lock()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"0\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PURCHASE_ORDER_APPROVAL\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			  out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");



										
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
					out.println("<td class='border_wht' valign='top'> "); 
					out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
					out.println("<tr> "); 
					out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Document Follow Up</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='10%'></td>");  
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
					out.println("<tr class='tr_input'>");  
					out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
					out.println("</tr>");  
					out.println("</table>");  
				
 			   out.println("<table align='center' width='100%' class='table'>"); 

				 out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
		
			   out.println("</table>");
				  
					out.println("</td></tr><tr>");  
				  out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  	
				  out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					out.println("<td width='10%'></td>");  
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='before_submit()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Copy\");'  onclick='View_Letter()' value=\"Copy\"></td>"); 
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					
				out.println("</td></tr><tr>");  
				out.println("</tr><tr>");  	
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");

			
			}
			
			//=========================================================================================================================			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
