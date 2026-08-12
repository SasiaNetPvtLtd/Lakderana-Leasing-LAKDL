
//SCREEN NAME:QUOTATION APPROVAL LETTER
//DEVELOPED BY MAHELA FOR FACTORING ON 20-12-2006

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_MK_Quotation_Approval_Letter extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3,rs4,rs5;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	public double m_credit_limit,m_reverse_margin,m_int_rate;
	String m_product_desc,m_toler_period,m_credit_period,m_user_name,m_emp_id,m_designation="-",m_fee_pack_code,m_quotation_no;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 

			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			//m_client_no=req.getParameter("client_no");		
			//m_fee_pack_code=req.getParameter("fee_pack_code");		
			m_quotation_no=req.getParameter("quotation_no");		
			m_print=req.getParameter("print");
			
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();			
			stmt4 = conn.createStatement();			
			stmt5 = conn.createStatement();			

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Indicative Quotation Letter </title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			out.println("function print_data(){");
			out.println("m_table.innerHTML=\"\" ");
			out.println(" window.print();");
			//out.println("window.preview();");
			out.println("}");
			
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
		  out.println("m_table.innerHTML=\"\" ");
			}
			else{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");
			
			out.println("function Generate_Annexure(m_quotation_no){ ");	
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"FA_MK_Quotation_Annexure?chksql=main_page&quotation_no=\"+m_quotation_no+\"&print=TRUE\";"); 
			out.println(" popupwin=window.open(m_url,'displayWindow2','left=180,top=180,width=650,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");	
			
			rs2 = stmt2.executeQuery(" SELECT "+
						" A.CLIENT_CODE "+
						" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION A "+
						" WHERE A.QUOTATION_NO=UPPER('"+m_quotation_no+"') ");
						
			if(rs2.next()){
			m_client_no=rs2.getString(1);
			}

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
		
			rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL ");								
			boolean more = rs.next();
			
			if(more){
			m_Letter_date=rs.getString(1);
			}
	
	
			String m_reg_cont_person="";
			String m_reg_desig="";
			
			rs1 = stmt1.executeQuery (" SELECT "+
				  " CLIENT_CODE, "+
				  " FULL_NAME, "+
				  " NVL(REGISTERED_ADDRESS1,'-'), "+
				  " NVL(REGISTERED_ADDRESS2,'-'), "+
				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+
					" NVL(REGISTERED_CONTACT_PERSON,'-'),"+
    			" NVL(DESIGNATION_PAYMENT,'-') "+
				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				  " WHERE CLIENT_CODE='"+m_client_no+"' ");
	
				more = rs1.next();
				if(more){
					m_c_code=rs1.getString(1);
					m_name=rs1.getString(2);
					m_add1=rs1.getString(3);
					m_add2=rs1.getString(4);
					m_city_desc=rs1.getString(5);
					m_reg_cont_person=rs1.getString(6);
					m_reg_desig=rs1.getString(7);
				}
	
		  double adv_payment =0;
			
			rs2 = stmt2.executeQuery(" SELECT "+
	 					" A.CLIENT_CODE, "+//1
						" A.FA_PRODUCT_CODE, "+ //2
					  " UPPER(NVL(B.FA_PRODUCT_DESC,'N/A')), "+//3
					  " NVL(A.CREDIT_LIMIT,0), "+ //4
					  " NVL(A.RESERVE_MARGIN,0), "+ //5
					  " NVL(A.INT_RATE,0), "+ //6
						" NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+//7
						" NVL(A.CREDIT_PERIOD,0) "+ //8
						" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION a, "+m_schema_name+".FA_CO_MAS_PRODUCT b "+
						" where a.FA_PRODUCT_CODE = b.FA_PRODUCT_CODE AND QUOTATION_NO=UPPER('"+m_quotation_no+"') ");
	
				more = rs2.next();
					if(more){
						m_product_desc=rs2.getString(3)+" FACILITY ";
						m_credit_limit = rs2.getDouble(4);
						m_reverse_margin = rs2.getDouble(5);
						m_int_rate = rs2.getDouble(6);
						m_toler_period =rs2.getString(7);
						m_credit_period =rs2.getString(8);
						adv_payment = 100.00 - m_reverse_margin;
					 }
					else {
						m_product_desc = "NO DATA FOUND";
						m_credit_limit  = 0.00;
						m_reverse_margin = 0.00;	
						m_int_rate = 0.00;
						m_toler_period = "0";
						m_credit_period = "0";
						adv_payment = 0.00;
					}
		
					rs3 = stmt3.executeQuery(" SELECT NAME,EMP_ID "+
								" FROM "+m_schema_name+".CO_CO_MAS_USER "+
								" WHERE USER_ID='"+m_username+"'");
					
						more = rs3.next();	
						if(more){
							m_user_name = rs3.getString(1);
							m_emp_id = rs3.getString(2);
						}
					
	 				rs4 = stmt4.executeQuery("SELECT NVL(DESIGNATION_NAME,'-') "+ 
								" FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION  "+
								" WHERE DESIGNATION_CODE=(SELECT DESIGNATION_CODE FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE  "+
								" WHERE EMP_CODE=upper('"+m_emp_id+"')) ");			
					
						more = rs4.next();				
						if(more){
							m_designation = rs4.getString(1);
						}
						

			out.println("<blockquote><blockquote><blockquote><p style='{text-align:left;font-size: 9px;}'>");	
			out.println("<br><br>");
			out.println("<br><br>");
			out.println("<br><br><br>");
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr><td width='*%'class='rep-body' style='{text-align:right;font-size: 9px;}' ><b> Ref No :- "+m_quotation_no+"</b></td></tr>");
			out.println("<tr><td width='*%'class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_Letter_date+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_reg_cont_person+"</td></tr>");
			out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_reg_desig+"</td></tr>");
	  	out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_name+"</td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_add1+" </td></tr>");
		  out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_add2+" </td></tr>");
      out.println("<tr><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}' >"+m_city_desc+" </td></tr>");
			out.println("</TABLE>");
			out.println("<table border='0' width='80%' class='table'> ");	
			//out.println("<tr ><td width='80%' class='rep-body' style='{text-align:left;font-size: 9px;}'><br><b>Dear Sir/Madam,</b></td></tr>");
			out.println("<tr ><td width='40%' class='rep-body' style='{text-align:left;font-size: bold 10px;}'>"+
			" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' style='{font: bold 9pt arial;}' value='Dear Sir/Madam,' size='300' style='width:300'></td>");
			out.println("<td width='40%' class='rep-body' style='{text-align:right;font-size: bold 10px;}'>"+
			" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' style='{font: bold 9pt arial;}' value='APPROVAL WITHIN TWO WORKING DAYS' size='300' style='width:300'></td></tr>");
			out.println("</TABLE>");		
			out.println("</p></blockquote>");
			out.println("<blockquote><p style='{text-align:left;font-size: 9px;}' class='rep-body'>");			
			out.println("INDICATIVE QUOTATION FOR <b>"+m_product_desc+"</b>");
			out.println("</p></blockquote>");				
			out.println("<blockquote><font size=1><p style='text-align:justify' class='rep-body'>");		
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='80%' class='rep-body' style='{text-align:justify;;font-size: 9px;}'> ");
			out.println("Further to the discussion we had with you, we are pleased to offer our indicative quotation for a <b> "+m_product_desc+" </b>  "+
									" for your valued organization .");
			out.println("</td></tr>");						
			out.println("</table>");						
			out.println("</font></p></blockquote>");				
			out.println("<blockquote><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<b>Financing Credit Limit.</b><br> ");
			out.println(" Financing credit limit of <b> Rs. "+nf.format(m_credit_limit)+" </b> for the assigned invoices to Orient Factor.");
			out.println("</p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<b>Initial Pre Payment Percentage </b><br> ");
			out.println(" Initial Pre Payment of <b> "+nf.format(adv_payment)+" % </b> on approved assigned invoices.");
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<b>Financing Fee Package </b><br> ");
			
			 rs5 = stmt5.executeQuery(" SELECT DISTINCT A.FEE_CODE,NVL(B.FEE_VALUE,0),NVL(A.FEE_DESC,'N/A') FROM "+m_schema_name+".FA_CO_MAS_FEES A, "+m_schema_name+".FA_MK_PRO_QUOTATION_FEE B "+
									" WHERE A.FEE_TYPE='P' AND A.FEE_CODE=B.FEE_CODE AND B.QUOTATION_NO = UPPER('"+m_quotation_no+"') ");
									
						
						more = rs5.next();				
						int i = 0;
						 /*while(more){
							out.println(" "+rs5.getString(1)+"  "+rs5.getString(3)+"  -  <b> "+nf.format(rs5.getDouble(2))+" </b><br>  ");
							more = rs5.next();				
							i++;
						 }*/
						 while(more){
							if(rs5.getString(1).equals("FEE0003.0")) {
								out.println(" Administration Charge of Rs<b> "+nf.format(rs5.getDouble(2))+"</b> (%) on assigned invoices <br>  ");
							}
							else if(rs5.getString(1).equals("FEE0004.0")) {
							  out.println(" Minimum Administration Charge of Rs<b> "+nf.format(rs5.getDouble(2))+"</b> per month <br>  ");
							}
							else if(rs5.getString(1).equals("FEE0005.0")) {
							  out.println(" Minimum Service Commitment of Rs<b> "+nf.format(rs5.getDouble(2))+"</b> per month <br>  ");
							}
							
							
							more = rs5.next();				
							i++;
						 }	
			
			out.println("<b> ( Recourse Period of "+m_toler_period+" days after "+
									" approved Credit Period of "+m_credit_period+"days. )</b> ");
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;font-size: 9px;}'> ");
			out.println("<b>Discount Charge of "+nf.format(m_int_rate)+" % </b> per annum will be charged for Funds in Use calculated on daily basis. ");
			out.println("</td></tr></table>");
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<b>Extra Value Addition Service Fees </b> ");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");		
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;font-size: 9px;}'> ");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> We provide the service of collections of your receivables on daily basis. ");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Collection of your invoice will be handled by us on daily or weekly basis through our delivery service or upon request. ");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> In additions we also send you the following on daily,weekly and monthly basis upon request. ");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println(" <li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Client reports");
			out.println(" <li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Cheques realized and return cheques statements.");
			out.println("	<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Cheques collection & banking.");
			out.println(" <li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Disapproved and due date exceeded invoices statements.");
			out.println("</font></p></blockquote>");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> We will also send you monthly statements of your own account listing of all the transactions that have taken place in your account.	");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Also your debtors will receive statements, reminders and notices at your request. ");
			out.println("<li class='rep-body' style='{text-align:justify;font-size: 9px;}'> Please find <u style=\"cursor:hand\" onclick=\"Generate_Annexure('"+m_quotation_no+"')\">attached annexure </u> for the extra value addition fees.");
			out.println("</td></tr></table>");
			out.println("</font></p></blockquote>");
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body' >");				
			out.println("<b>General Terms & Conditions </b><br> ");
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:justify;font-size: 9px;}'> ");
			out.println(" Value Added Tax rate of 15 % will be levied on the Gross Administration charges. Also No initial "+
									  " pre payments will be considered on invoices raised against Related Party transactions,"+
										" invoices raised before the execution of above agreement and any other special conditions "+
										" which are not accepted by the credit committee of Orient Factor.<br><br>");
			out.println(" While we thank you for considering our proposal , we trust an opportunity would be granted "+
										" to service your organization and  will remain valid for 15days from date above and subject "+
										" to approval of our credit committee after the routine due diligence work.<br><br>");							
			out.println(" Should you require any further clarification pertaining to the above proposal, please do not "+
									" hesitate to contact the undersigned.");		
									
						
						String m_full_user_name="";
						String m_user_designation="";
						
						rs1 = stmt1.executeQuery ("  SELECT "+
							" A.USER_ID,"+
							" A.NAME,"+
							" A.EMP_ID,"+
							" B.DESIGNATION_CODE,"+
							" B.FIRST_NAME || ' ' ||	B.LAST_NAME,"+
							" C.DESIGNATION_NAME "+
							" FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B, "+
							" "+m_schema_name+".CO_CO_MAS_DESIGNATION C "+
							" WHERE A.EMP_ID=B.EMP_CODE "+
							" AND C.DESIGNATION_CODE=B.DESIGNATION_CODE "+
							" AND UPPER(A.USER_ID)=UPPER('"+m_username+"')");
						
						more = rs1.next();
						if(more){
						m_full_user_name=rs1.getString(5);
						m_user_designation=rs1.getString(6);
						}
	
			out.println("</td></tr></table>");						
			out.println("</font></p></blockquote>");
			out.println("<blockquote><font size=1><p style='{text-align:justify;font-size: 9px;}' class='rep-body'>");	
			out.println("Yours faithfully,<br>");	
			out.println("Lakderana Investments Limited,<br> ");	
			out.println("Orient Factor, <br><br><br><br><br> ");	
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}'> ");
			out.println(" "+m_full_user_name+" <br>");	
			out.println("</td></tr></table>");						
			out.println("<table border='0' width='80%' class='table'>"); 		
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;font-size: 9px;}'> ");
    	out.println(" "+m_user_designation+"<br>");	
			out.println("</td></tr>");
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:left;font: 8pt arial;}'><input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' style='{font-size: bold 10px;}' value='077-3306061' size='300' style='width:300'></td></tr>");
			out.println("</TABLE>");		
			out.println("</table>");						
			out.println("</font></p></blockquote></blockquote></blockquote>");		
		  out.println("</form></body></html>");
			}//End of main page
			else  {
			out.println("idle");
			}	
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
