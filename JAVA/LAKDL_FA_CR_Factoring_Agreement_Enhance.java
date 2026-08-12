
//SCREEN NAME:RECOURSE FACTORING AGREEMENT
//DEVELOPED BY MAHELA FOR FACTORING ON 27-02-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Factoring_Agreement_Enhance extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2;
	public String m_chksql,m_html_client_url,reqstr,m_Letter_date,m_c_code,m_add1,m_add2,m_name,m_city_desc,m_client_no,m_print;
	
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
			
			String m_debtor_code="-",m_client_name="-",m_receipt_no="-",m_batch_no="-",m_return_no="-",m_facility_no="-";
			String m_chq_no="-",m_chq_date="-",m_bank="-",m_branch="-",m_deadline_date="-",m_invoice_no="-";
			String m_cli_nic="-",m_bus_nature="-",m_fee_pack="-",m_client_cat="-",m_product_code="-",m_product_name="-";
			double m_chq_amount=0,m_facility_amount=0,m_int_rate=0,m_rever_margin=0,m_initial_pay=0;
			int m_tol_period=0,m_init_pay=0,m_rever_margin2=0,m_int_rate2=0;
			String m_to_date="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
						
			m_chksql=req.getParameter("chksql");
			m_client_no=req.getParameter("client_no");		
			m_print=req.getParameter("print");
			m_facility_no=req.getParameter("facility_no");
			
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();

		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Recourse Factoring Agreement</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_save_Factoring_Agreement_Enhance?client_no="+m_client_no+"&facility_no="+m_facility_no+"&print="+m_print+"\";");  
			out.println("  window.location.href=m_url;"); 
			out.println("	 m_table.innerHTML=\"\" ");
			out.println("	 window.print();");
			out.println("}");
			  
			out.println("function add_button(){");
			if (m_print.trim().equals("FALSE")) {
			out.println("m_table.innerHTML=\"\" ");
			}
			else
			{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			}
			out.println("}");

			out.println("</script>");				
			out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");	
			out.println("<body bgcolor='white'><br>");
			out.println("<form name='Form1'>");	
		  out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			String m_val_facility ="";
			String m_val_facility2 ="";
			
				rs2 = stmt2.executeQuery (	" SELECT "+
  				  " NVL(CREDIT_LIMIT,0), "+//1
						" NVL(FEE_PACK_CODE,'-'), "+//2
						" NVL(INT_RATE,0),"+//3
						" NVL(TOLERANCE_CREDIT_PERIOD,0), "+//4
						" NVL(RESERVE_MARGIN,0), "+//5
						" NVL(FA_PRODUCT_CODE,'-'),"+//6
						" TO_CHAR(SYSDATE,'DD-MON-YYYY') "+//7
  				  " FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
  				  " WHERE FACILITY_NO='"+m_facility_no+"' ");

					boolean more = rs2.next();
						if(more){
							m_facility_amount = rs2.getDouble(1);
							m_fee_pack = rs2.getString(2);
							m_int_rate = rs2.getDouble(3);
							m_int_rate2 = rs2.getInt(3);
							m_tol_period = rs2.getInt(4);
							m_rever_margin = rs2.getDouble(5);
							m_rever_margin2 = rs2.getInt(5);
							m_initial_pay = (100 - m_rever_margin);
							m_init_pay = (100 - m_rever_margin2);
							m_product_code = rs2.getString(6);
							m_to_date=rs2.getString(7);
												
							m_val_facility = nf.format(rs2.getDouble(1));
							m_val_facility2 = m_sn_methods.met_unformat_number(m_val_facility);
						 }

							/*rs2 = stmt2.executeQuery (	" SELECT "+
  						  " FA_PRODUCT_CODE,"+
  						  " INITCAP(FA_PRODUCT_DESC) "+
 							"FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
 							"WHERE FA_PRODUCT_CODE='"+m_product_code+"' ");
							
							more = rs2.next();
							if(more){
							 m_product_name= rs2.getString(2);
							}*/
							
			m_product_name="Recourse Factoring";
			
			out.println("<blockquote><p style='text-align:justify'>");	
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAgreement No:"+m_facility_no+"</b></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:right;}'><b>1 of 3</b></td></tr>");
			out.println("</table>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b><font size=4> "+m_product_name+" Agreement</font></b></td></tr>");
			out.println("</TABLE>");		
			/*out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b>Agreement No:"+m_facility_no+"</b></td>");
			out.println("</tr>");
			out.println("</table>");*/
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b> (Enhancement/Variation) </b></td>");
			out.println("</tr>");
			out.println("</table>");		
			out.println("<br>");
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='factoring-letter-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>	Whereas the parties entered into the above numbered "+m_product_name+" Agreement at the place "+
			" mentioned therein between <b><i>Lakderana Investments Limited</i></b> having its registered office at No. 100,   Buthgamuwa Road,  Rajagiriya and the principle place of business at No. 100, Buthgamuwa Road,  Rajagiriya "+
			" carrying on business under the trade name <b><i>'Orient Factor'</i></b> to factor the debts.  </td> ");
			out.println("</tr>");
			out.println("</table><br>");
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='factoring-letter-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:justify;}'>	The company / sole proprietorship / partnership hereinafter called the <b><i>'The Seller'</i></b> as described item 1A of the schedule and to "+
			" assign such debt to the <b><i>Orient Factor</i></b> in consideration of advance payment of debts by <b><i>Orient Factor</i></b> and</td>");
			out.println("</tr>");
			out.println("</table><br>");
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='factoring-letter-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:justify;}'> The Guarantors (hereinafter called <b><i>'The Guarantors'</i></b> named in item "+
			" 1B of the schedule which term or expression as herein used shall where the context so requires or admits mean and include the said Guarantors his / their heirs executors and "+
			" administrators / its successors and assigns) </td>");
			out.println("</tr>");
			out.println("</table><br>");
			
			 String m_val_fac = nf.format(m_facility_amount);
			 String m_val2_fac = m_sn_methods.met_unformat_number(m_val_fac);

			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='factoring-letter-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:justify;}'> Whereas the parties have agreed that the factoring facility limit to be enhanced up to"+
			"  <input class='txt_input6' type='text' name='TXT_NIL' maxlength='170' value='Rupees "+numbersToChar_inside(m_val2_fac)+"' size='170' style='width:165'>  (<input class='txt_input6' type='text' name='TXT_NIL' maxlength='80' value='Rs "+nf.format(m_facility_amount)+"' size='80' style='width:100'> /- ) and / or to vary the terms and conditions in the schedule to the main agreement by substituting "+
			" same with the amended schedule annexed hereto and the same terms and conditions in the amended schedule will be retrospectively binding and effective as far it is applicable. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='factoring-letter-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:justify;}'> Whereas the parties including the Guarantors have further agreed that all other terms "+
			" and conditions obligations and covenants contained in the "+m_product_name+" Agreement will be binding on all parties until the enhanced amount is also fully recovered by  "+
			" the <b><i> Orient Factor</i></b> and that this will be read as part and parcel of the original agreement. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			
			//-----------------------------------------------------Schedule
			
			rs1 = stmt1.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" DECODE(CLIENT_CATEGORY,'INDIVIDUAL',NVL(NIC_NO,'-'),NVL(BUSINESS_CERTIFICATE_NO,'-')),"+//6
						" NVL(DECODE(CLIENT_CATEGORY,'INDIVIDUAL','Individual','CORPORATE','Corporate','LIMITED','Private Limited Liability','PUBLIC','Public/Quoted','SOLEPROPRI','Sole Proprietorship','PARTNERS','Partnership'),'-'), "+//7
						" NVL(THIRD_PART_DEBTOR_DET,'-'),"+//8
						" NVL(CLIENT_CATEGORY,'-') "+//9
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");

					more = rs1.next();
					if(more){
						m_c_code=rs1.getString(1);
						m_name=rs1.getString(2);
						m_add1=rs1.getString(3);
						m_add2=rs1.getString(4);
						m_city_desc=rs1.getString(5);
						m_cli_nic=rs1.getString(6);
						m_bus_nature=rs1.getString(7);
						m_client_cat=rs1.getString(9);
					}
			//----------------------------------------------------------------------------------------		
			out.println("<p style=\"page-break-after:always\"></p>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b>Agreement No:"+m_facility_no+"</b></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:right;}'><b>2 of 3</b></td></tr>");
			out.println("</table>");			
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b> SCHEDULE </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table width='90%' border='1' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Date of Agreement <br>Place of execution is deemed to be No.75,Arnold Ratnayake Mawatha,Colombo 10");
			out.println("</td>");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_N818' maxlength='100' value='"+m_to_date+"' size='200' style='width:250'><br> "); 
			out.println("</td>");
			out.println("</tr>");		
			out.println("<tr >");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 1A  <br>Name<br>NIC No./BRC No./PVS No.<br> Address<br>Nature of Business of Seller");
			out.println("</td>");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<br>"+m_name+"<br>"+m_cli_nic+"<br>"+m_add1+","+m_add2+"<br>"+m_bus_nature+" ");
			out.println("</td>");
			out.println("</tr>");		
			
			String m_gurantor_data="";
			String m_gurantor_data1="";
			
			rs1 = stmt1.executeQuery (" SELECT CLIENT_CODE,DIR_NAME,DIR_NIC_NO,ADDRESS "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR "+
			" WHERE GUARANTOR_STATUS='Y' "+
			" AND CLIENT_CODE='"+m_client_no+"' ");
			
			while(rs1.next()){
			m_gurantor_data=m_gurantor_data+"<br>"+rs1.getString(2)+"<br>"+rs1.getString(3)+"<br>"+rs1.getString(4)+"<br>";
			//m_gurantor_data=m_gurantor_data+"<input class='txt_input6' type='text' name='TXT_N221' maxlength='400' value='' size='400' style='width:450'><br>";
			m_gurantor_data1=m_gurantor_data1+"<br>Name<br>NIC No./BRC No./PVS No.<br>Address<br>";
			}
			
			m_gurantor_data=m_gurantor_data+"<br>";
			m_gurantor_data1=m_gurantor_data1+"<br>";
			
			rs1 = stmt1.executeQuery ("	SELECT "+
			" CLIENT_CODE,"+
			" NVL(GUA_NAME,'-'),"+
			" NVL(GUA_NIC_NO,'-'),"+
			" NVL(GUA_ADDRESS,'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			while(rs1.next()){
			m_gurantor_data=m_gurantor_data+""+rs1.getString(2)+"<br>"+rs1.getString(3)+"<br>"+rs1.getString(4)+"<br>";
			m_gurantor_data1=m_gurantor_data1+"Name<br>NIC No./BRC No./PVS No.<br>Address<br>";
			}
			
			out.println("<tr >");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 1B Guarantor/s <br>"+m_gurantor_data1+"<br> Individual/Corporate");
			out.println("</td>");
			out.println("<td width='50%' height='40' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<br>"+m_gurantor_data+"<br><input class='txt_input6' type='text' name='TXT_N221' maxlength='400' value='Individual/Corporate' size='400' style='width:250'></td> ");
			out.println("</tr>");
			
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 2. Administration  Charge (Clause 7.2)  ");
			out.println("</td>");

				rs1 = stmt1.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0003.0' AND FACILITY_NO='"+m_facility_no+"' ");
				more = rs1.next();		
			
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			 if(more){
					
				  String m_val = nf.format(rs1.getDouble(1));
					String m_val2 = m_sn_methods.met_unformat_number(m_val);
					
					//out.println(" <input class='txt_input6' type='text' name='TXT_N11' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val2)+"' size='100' style='width:100'> Percent (<input class='txt_input6' type='text' name='TXT_N12' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:30'>%)  of the  invoice  value  of each  debt.<br> "); 
					out.println(" "+get_number_from_percentage(m_val2)+"  Percent ("+nf.format(rs1.getDouble(1))+"%)  of the  invoice  value  of each  debt.<br> "); 
					
					/*
					int m_dot=m_val.indexOf(".");
					int m_length=m_val.length();
					String m_cents=m_val.substring(m_dot+1,m_length);
					String m_int_val= rs1.getInt(1)+"";
					String m_val2 = m_sn_methods.met_unformat_number(m_val);
					if(!m_cents.equals("00")) {
					out.println(" <input class='txt_input6' type='text' name='TXT_N11' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val2)+"' size='100' style='width:100'> Percent (<input class='txt_input6' type='text' name='TXT_N12' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:30'>%)  <br><br> "); 
					}
					else {
					out.println(" <input class='txt_input6' type='text' name='TXT_N11' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val2)+"' size='100' style='width:100'> Percent (<input class='txt_input6' type='text' name='TXT_N12' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:30'>%)  <br><br> "); 
					}
					out.println("	of the  invoice  value  of each  debt.");*/
			 }
			 else {	
					out.println(" ............Percent (.....%) <br><br> ");
					out.println("	of the  invoice  value  of each  debt.");
			 }
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 3. Minimum Administration  charge (Clause 7.2) ");
			out.println("</td>");

				rs1 = stmt1.executeQuery ("	SELECT "+
  				" NVL(APPLICABLE_VALUE,0) "+
 					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0004.0' and FACILITY_NO='"+m_facility_no+"' "+
					" UNION ALL "+
					"	SELECT "+
  				" NVL(APPLICABLE_VALUE,0) "+
 					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0005.0' and FACILITY_NO='"+m_facility_no+"' ");
						
				more = rs1.next();		
				
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
		  if(more){
			   String m_val = nf.format(rs1.getDouble(1));
				 String m_val2 = m_sn_methods.met_unformat_number(m_val);
				 int m_dot=m_val.indexOf(".");
				 int m_length=m_val.length();
				 String m_cents=m_val.substring(m_dot+1,m_length);
				 String m_int_val= rs1.getInt(1)+"";	
				if(!m_cents.equals("00")) {	
			  out.println(" Rupees "+numbersToChar_inside(m_val2)+" per month (Rs "+nf.format(rs1.getDouble(1))+" /- ) from commencement ");
				}
				else {
				out.println(" Rupees "+numbersToChar_inside(m_int_val)+"  per month (Rs "+nf.format(rs1.getDouble(1))+" /- ) from commencement ");
				}
			}
			else {
			  out.println(" Rupees ..........   per month (Rs....../- ) from commencement ");
			}
			
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 4. Maximum  initial  payment percentage   (clause 7.5) ");
			out.println("</td>");
			
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			
		  String m_int_pay = nf.format(m_initial_pay);
			String m_int_pay2 = m_sn_methods.met_unformat_number(m_int_pay);
			out.println(" "+get_number_from_percentage(m_int_pay2)+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the invoice value of  a notified  approved  debt.");
			
			/*int m_dot2=m_int_pay.indexOf(".");
			int m_length2=m_int_pay.length();
			String m_cents2=m_int_pay.substring(m_dot2+1,m_length2);
			//String m_int_val2= rs1.getInt(1)+"";	
			
			out.println(" "+m_sn_methods.numbersToChar_Percentage(m_initial_pay+"")+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the invoice value of  a notified  approved  debt.");
			
			if(!m_cents2.equals("00")) {		
			out.println(" "+m_sn_methods.numbersToChar_Percentage(m_initial_pay+"")+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the invoice value of  a notified  approved  debt.");
			}
			else {
			out.println(" "+m_sn_methods.numbersToChar_Percentage(m_init_pay+"")+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the invoice value of  a notified  approved  debt.");
			}*/
				
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 5. Discount  Charge (Clause 7.5) ");
			out.println("</td>");
						
			rs1 = stmt1.executeQuery ("	SELECT "+
			"  NVL(APPLICABLE_VALUE,0) "+
			"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
			" WHERE FEE_CODE='FEE0002.0' and FACILITY_NO='"+m_facility_no+"' ");
			more = rs1.next();		
			
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			if(more){
			 String m_val = nf.format(rs1.getDouble(1));
			 String m_val2 = m_sn_methods.met_unformat_number(m_val);
			 String m_val3 = nf.format(m_int_rate);	
			 String m_rate = m_sn_methods.met_unformat_number(m_val3);	
				
			 out.println(" "+get_number_from_percentage(m_rate)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus.");
			 out.println(" "+get_number_from_percentage(m_val2)+" percent per annum ("+nf.format(rs1.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient Factor's discretion.");
				
			 /*int m_dot4=m_val.indexOf(".");
			 int m_length4=m_val.length();
		   String m_cents4=m_val.substring(m_dot4+1,m_length4);	
			 String m_int_val=rs1.getInt(1)+"";
				
			 String m_val3 = nf.format(m_int_rate);	
			 int m_dot3=m_val3.indexOf(".");
			 int m_length3=m_val3.length();
		   String m_cents3=m_val3.substring(m_dot3+1,m_length3);	
			 String m_int_val2=m_int_rate2+"";	
			 String m_rate = m_sn_methods.met_unformat_number(m_val3);	
				
			 if(!m_cents3.equals("00")) {			
			 out.println(" "+get_number_from_percentage(m_rate)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus.");
			 }
			 else {
			 out.println(" "+get_number_from_percentage(m_int_val2)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus.");	
			 }	
			 if(!m_cents4.equals("00")) {				
			 //out.println(" "+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(32-rs1.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
			 out.println(" <input class='txt_input6' type='text' name='TXT_N1' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val)+"' size='100' style='width:150'>  per cent per annum (<input class='txt_input6' type='text' name='TXT_N2' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:50'>%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
			 }
			 else {
			 //out.println(" "+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(32-rs1.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");	
			 out.println(" <input class='txt_input6' type='text' name='TXT_N1' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val)+"' size='100' style='width:150'>  per cent per annum (<input class='txt_input6' type='text' name='TXT_N2' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:50'>%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
			 }*/
			}
			else {
			 String m_val = nf.format(rs1.getDouble(1));
			 String m_val2 = m_sn_methods.met_unformat_number(m_val);
			 String m_val3 = nf.format(m_int_rate);	
			 String m_rate = m_sn_methods.met_unformat_number(m_val3);
			 out.println(" "+get_number_from_percentage(m_rate)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus. .......  per cent per annum (....%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient Factor's discretion.");
			}
			
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 6. Maturity  Date  (Clause 7.3)  ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" For all notified approved debts the maturity  date shall be the date  on which the cheque drawn under clause 7.6 is realized.");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 7. Recourse Period (Clause  11.7)   ");
			out.println("</td>");
			
			String m_vall = m_tol_period+"";
			
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" "+numbersToChar_inside(m_vall)+"  days ("+m_tol_period+" days)  after  the respective  debt falls due ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 8. Additional Monthly  Factoring  Administration  Charge ( Clause 11.8)    ");
			out.println("</td>");
			   
			rs1 = stmt1.executeQuery ("	SELECT "+
			"  NVL(APPLICABLE_VALUE,0) "+
			"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
			" WHERE FEE_CODE='FEE0020.0' AND FACILITY_NO='"+m_facility_no+"' ");
			more = rs1.next();		
				
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			if(more){
			  String m_val = nf.format(rs1.getDouble(1));
			 	String m_val2 = m_sn_methods.met_unformat_number(m_val);
					
				out.println(" "+get_number_from_percentage(m_val2)+" Percent ("+nf.format(rs1.getDouble(1))+"%) during the "+m_product_name+"  period ");
				
				/*String m_val = nf.format(rs1.getDouble(1));
			 	String m_val2 = m_sn_methods.met_unformat_number(m_val);
				int m_dot=m_val.indexOf(".");
				int m_length=m_val.length();
				String m_cents=m_val.substring(m_dot+1,m_length);
				String m_int_val= rs1.getInt(1)+""; 	
				if(!m_cents.equals("00")) {	
				//out.println(" "+m_sn_methods.numbersToChar_Percentage(m_val)+" Percent  ("+nf.format(rs1.getDouble(1))+"%) <br>  during the "+m_product_name+"  period");
				out.println(" <input class='txt_input6' type='text' name='TXT_N100' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val)+"' size='100' style='width:200'> Percent (<input class='txt_input6' type='text' name='TXT_N200' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:50'>%)  <br>  during the "+m_product_name+"  period ");
				}
				else {
				//out.println(" "+m_sn_methods.numbersToChar_Percentage(m_int_val)+" Percent  ("+nf.format(rs1.getDouble(1))+"%) <br>  during the "+m_product_name+"  period");
				out.println(" <input class='txt_input6' type='text' name='TXT_N100' maxlength='100' value='"+m_sn_methods.numbersToChar_Percentage(m_val)+"' size='100' style='width:200'> Percent (<input class='txt_input6' type='text' name='TXT_N200' maxlength='100' value='"+nf.format(rs1.getDouble(1))+"' size='100' style='width:50'>%)  <br>  during the "+m_product_name+"  period ");
				}*/
			}
			else {
				out.println(" ........ Percent  (.....) <br>  during the "+m_product_name+"  period");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 9. Security    ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <br>");
			out.println(" <input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value='Joint and several  guarantee' size='100' style='width:200' onblur=\"\"><br>");
			out.println(" <input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value='Corporate Gurantee' size='100' style='width:200' onblur=\"\"><br>");
			out.println(" <input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value='Mortgage of Property' size='100' style='width:200' onblur=\"\"><br>");
			out.println(" <input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value='Cash Deposit' size='100' style='width:200' onblur=\"\">");
			//out.println(" Joint and several  guarantee&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='checkbox' type='checkbox'><br>");
			//out.println(" Corporate Gurantee&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='checkbox' type='checkbox'><br>");
			//out.println(" Mortgage of Property&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='checkbox' type='checkbox'><br>");
			//out.println(" Cash Deposit&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='checkbox' type='checkbox'><br> ");
			out.println(" <br><input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value=' A cash  deposit  of Rs.' size='100' style='width:150' onblur=\"\"><input class='txt_input6' type='text' name='TXT_N300' maxlength='100' value='' size='100' style='width:150'><input class='txt_input6' type='text' name='TXT_INT_RATE1' maxlength='100' value='/- as additional  security' size='100' style='width:150' onblur=\"\"><br> ");
			out.println("</td>");
			out.println("</tr>");
			
			/*String m_security_data="";
			
			rs1 = stmt1.executeQuery (" SELECT CLIENT_CODE,DIR_NAME,DIR_NIC_NO "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_COMP_DIR "+
			" WHERE GUARANTOR_STATUS='Y' "+
			" AND CLIENT_CODE='"+m_client_no+"' ");
			
			while(rs1.next()){
			m_security_data=m_security_data+rs1.getString(2)+"<br>"+rs1.getString(3)+"<br>";
			}
			
			rs1 = stmt1.executeQuery ("	SELECT "+
			" CLIENT_CODE,"+
			" NVL(GUA_NAME,'-'),"+
			" NVL(GUA_NIC_NO,'-'),"+
			" NVL(GUA_ADDRESS,'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			while(rs1.next()){
			m_security_data=m_security_data+rs1.getString(2)+"<br>"+rs1.getString(3)+"<br>"+rs1.getString(4)+"<br>";
			}
			
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Joint and several  guarantees of <br> "+m_security_data+" ");
			
			rs1 = stmt1.executeQuery (" SELECT "+
			" NVL(CLIENT_CODE,'-'),"+
			" NVL(CREDIT_GRANTED_SECURE_DETAIL,'-'), "+
			" BASIS_OF_CREDIT_GRANT "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			more = rs1.next();			
					
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			if(more){
				String m_credit_grant_det=rs1.getString(2);
				if(rs1.getString(3).equals("Y")){
					if(!m_credit_grant_det.equals("-")) {
					 out.println(" Joint and several  guarantees of <br><br> "+m_credit_grant_det+"  as additional  security ");
					}
					else {
					 out.println(" Joint and several  guarantees of <br><br> A cash  deposit  of Rs.                 /- as additional  security ");
			 	  }
				}
				else{
					out.println(" ");
				}
			}
			else{
				out.println(" ");
			}				
			
			out.println("</td>");
			out.println("</tr>");*/	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 10.  Government Levies    ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" All applicable statutory charges imposed  by the Government. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 11. Exclusions <input class='txt_input6' type='text' name='TXT_NUM' maxlength='50' value='(Clause5)' size='50' style='width:50' onblur=\"\">   ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_DEBT' maxlength='50' value='Existing  debts  ( at commencement  date)' size='10' style='width:320' onblur=\"\" >   ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 12.  Countries   ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Sri Lanka  ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 13.  Clauses inapplicable ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='100' value='Nil' size='10' style='width:300' onblur=\"\">  ");
			out.println("</td>");
			out.println("</tr>");
			
			double m_cheque_return=0;
			rs1 = stmt1.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0008.0' AND FACILITY_NO='"+m_facility_no+"' ");
			more = rs1.next();		
			if(more){
			m_cheque_return=rs1.getDouble(1);
			}

			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 14  Special Condition  ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" A minimum  fee Rs.<input class='txt_input6' type='text' name='TXT_INT_RATE' maxlength='100' value='"+nf.format(m_cheque_return)+"' size='100' style='width:40' onblur=\"\">/- will be charged for every dishonoured  cheque.  ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			////-------------------------------------------------------------------------------
			String m_third_party_gurantee="";
			
			rs1 = stmt1.executeQuery ("	SELECT "+
			" CLIENT_CODE,"+
			" NVL(GUA_NAME,'-'),"+
			" NVL(GUA_NIC_NO,'-'),"+
			" NVL(GUA_ADDRESS,'-') "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
			" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			while(rs1.next()){
			m_third_party_gurantee=rs1.getString(2);
			}
			
			out.println("<p style=\"page-break-after:always\"></p>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='factoring-letter-body' style='{text-align:center;}'><b>Agreement No:"+m_facility_no+"</b></td>");
			out.println("<td width='*%' class='factoring-letter-body' style='{text-align:right;}'><b>3 of 3</b></td></tr>");
			out.println("</table>");			
			out.println("<br><br><br><br>");	
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_DESC1' maxlength='100' value='Authorised Signature' size='100' style='width:200'>");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_DESC1' maxlength='100' value='Authorised Signature' size='100' style='width:200'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Lakderana Investments Limited");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Lakderana Investments Limited");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Factor");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Factor");
			out.println("</td>");
			out.println("</tr>");
			/*out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_NAME21' maxlength='100' value='Lohika Fonseka' size='100' style='width:200'>");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_NAME1' maxlength='100' value='Nishaman Karunapala' size='100' style='width:200'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_DESC1' maxlength='100' value='Senior Manager - Factoring' size='100' style='width:200'>");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("<input class='txt_input6' type='text' name='TXT_DESC1' maxlength='100' value='Assistant General Manager - Operations' size='100' style='width:200'>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Financial Services Corporation Ltd");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Financial Services Corporation Ltd");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Factor");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Orient Factor");
			out.println("</td>");
			out.println("</tr>");*/
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" I/We hereby agree to the terms and conditions set out in this  agreement. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			if(m_client_cat.equals("LIMITED")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name of Directors attesting the sealing");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Director ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Director ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<b>Guarantors</b>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Guarantor ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Guarantor ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			if(!m_third_party_gurantee.equals("")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Corporate Guarantor: "+m_third_party_gurantee+"' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Name of Directors attesting the sealing' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='1. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='2. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			//out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println(" ................................ ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			}
			}
			if( m_client_cat.equals("CORPORATE")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name of Directors attesting the sealing");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Director ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Director ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			if(!m_third_party_gurantee.equals("")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Corporate Guarantor: "+m_third_party_gurantee+"' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Name of Directors attesting the sealing' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='1. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='2. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			//out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println(" ................................ ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			}
			}
			else if(m_client_cat.equals("PARTNERS")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1.Partner ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2.Partner ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<b>Guarantors</b>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Guarantor ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Guarantor ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			if(!m_third_party_gurantee.equals("")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Corporate Guarantor: "+m_third_party_gurantee+"' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Name of Directors attesting the sealing' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='1. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='2. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			//out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println(" ................................ ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			}
			}
			else if(m_client_cat.equals("SOLEPROPRI")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Proprietor ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<b>Guarantors</b>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(".............................");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Guarantors ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Guarantors ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Date ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			if(!m_third_party_gurantee.equals("")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Corporate Guarantor: "+m_third_party_gurantee+"' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Name of Directors attesting the sealing' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='1. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='2. ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			//out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println(" ................................ ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='................................  ' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Director ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Director' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			//out.println("Date ");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='300' value='Date' size='300' style='width:300'> ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br>");
			}
			}
			else {
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Proprietor");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Guarantors ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Guarantors ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			}
			
			
			out.println("<br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("<b>Witness</b>");
			out.println("</td>");
			out.println("<tr >");
			out.println("</table>");
			//out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Address ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Address");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No.");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC No.");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Signature ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='factoring-letter-body' style='{text-align:left;}' valign='top'>	");
			out.println("Signature ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("</font></p></blockquote>");			
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
	
	public static String get_number_from_percentage(String obj){

	Object[] ar_ones  = new Object[10];
	Object[] ar_tens  = new Object[10];
	Object[] ar_teens = new Object[10];
	
	ar_ones[0]        = "Zero";
	ar_ones[1]        = "One";
	ar_ones[2]        = "Two"; 
	ar_ones[3]		  =	"Three";
	ar_ones[4]        = "Four";
	ar_ones[5]        = "Five";
	ar_ones[6]        = "Six"; 
	ar_ones[7]		  =	"Seven";
	ar_ones[8]        = "Eight";
	ar_ones[9]        = "Nine";
	
	ar_tens[0]        = "";
	ar_tens[1]        = "Ten";
	ar_tens[2]        = "Twenty"; 
	ar_tens[3]		  =	"Thirty";
	ar_tens[4]        = "Forty";
	ar_tens[5]        = "Fifty";
	ar_tens[6]        = "Sixty"; 
	ar_tens[7]		  =	"Seventy";
	ar_tens[8]        = "Eighty";
	ar_tens[9]        = "Ninety";
	
	ar_teens[0]        = "";
	ar_teens[1]        = "Eleven";
	ar_teens[2]        = "Twelve"; 
	ar_teens[3]		   = "Thirteen";
	ar_teens[4]        = "Fourteen";
	ar_teens[5]        = "Fifteen";
	ar_teens[6]        = "Sixteen"; 
	ar_teens[7]		   = "Seventeen";
	ar_teens[8]        = "Eighteen";
	ar_teens[9]        = "Nineteen";
	
	String m_value;
	String m_cents="";
	
	String m_full_str="";
	String m_digit="0";
	int m_length=obj.length();
	m_value=obj.toString();
	int m_dot=obj.indexOf(".");
	if (m_dot!= -1 ) {
	m_cents=obj.substring(m_dot+1,m_length);
	int num = m_cents.length();
	if (num==1) {
	m_cents=m_cents;//+"0";
	}
	else if (m_cents.length()==2) {
	m_cents=m_cents;//+"00";
	}
	m_length=m_value.substring(0,m_dot).length();
	m_value=obj.substring(0,m_dot);
	}
	
	
	for (int j=1 ; j<= 15-m_length; j++) {
	m_value="0"+m_value;
	}
	int m_cnt=15-m_length;
	for (int i=m_cnt; i<=14; i++) {
	
	m_digit = m_value.substring(m_cnt,m_cnt+1);
	
	if ((m_cnt)==3) {  // 100,000,000,000
	if (!m_digit.equals("0")) {
	if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Billion ";
	m_cnt+=2;
	i+=2;
	}
	else {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
	}
	}
	}
	
	else if (m_cnt==4) {  // 10,000,000,000
	if (!m_digit.equals("0")) {
	if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
	m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Billion ";
	m_cnt+=1;
	i+=1;
	}
	else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Billion ";
	}
	
	else {
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
	}
	}
	}
	
	else if (m_cnt==5) {  // 1,000,000,000
	if (!m_digit.equals("0")) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Billion ";
	}
	}
	
	
	else if ((m_cnt)==6) {  // 100,000,000
	if (!m_digit.equals("0")) {
	if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Million ";
	m_cnt+=2;
	i+=2;
	}
	else {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
	}
	}
	}
	
	else if (m_cnt==7) {  // 10,000,000
	if (!m_digit.equals("0")) {
	if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
	m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Million ";
	m_cnt+=1;
	i+=1;
	}
	else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Million ";
	m_cnt+=1;
	i+=1;
	}
	else {
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
	}
	}
	}
	
	else if ((m_cnt)==8) {  // 1,000,000
	if (!m_digit.equals("0")) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Million ";
	}
	}
	
	else if ((m_cnt)==9) {  // 100,000
	if (!m_digit.equals("0")) {
	if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Thousand ";
	m_cnt+=2;
	i+=2;
	}
	else {						
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
	}
	}
	}
	
	else if ((m_cnt)==10) {  // 10,000
	if (!m_digit.equals("0")) {
	if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Thousand ";
	m_cnt+=1;
	i+=1;
	//m_cnt+=2;
	}
	else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
	m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Thousand ";
	m_cnt+=1;
	i+=1;
	}
	else {						
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
	}
	}
	}
	
	else if ((m_cnt)==11) {  
	if (!m_digit.equals("0")) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Thousand ";
	}
	}
	
	else if ((m_cnt)==12) {  
	if (!m_digit.equals("0")) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
	}
	}
	
	else if ((m_cnt)==13) {  
	if (!m_digit.equals("0")) {
	if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) {
	m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
	m_cnt+=1;
	i+=1;
	}
	else {
	m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
	}
	}
	}
	
	else if ((m_cnt)==14) {  
	if (!m_digit.equals("0")) {
	m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" ";
	}
	}
	
	m_cnt++;
	}
	
	
	m_cnt=0;
	
	
	//System.out.println(m_cnt+" "+m_cents);
	if (!m_cents.equals("")){
		
		if(m_full_str.equals("")){
		m_full_str=m_full_str+"Zero point ";
		}
		else{
		m_full_str=m_full_str+" point ";
		}
		
		for (int i=m_cnt; i<=1; i++) {			
			m_digit = m_cents.substring(m_cnt,m_cnt+1);
			//System.out.println("m_digit="+m_digit+" i="+i+" m_cnt="+m_cnt );
			if ((m_cnt)==0) {  // 1
				System.out.println("ar_ones[Integer.parseInt("+m_digit+")]="+ar_ones[Integer.parseInt(m_digit)]);
				m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)];
				/*if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
						if(!m_full_str.toString().equals(""))	{
							if(!m_full_str.equals("")){
							m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
							}
							else{
							m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
							}
						}
						else{
							m_full_str=ar_ones[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
						}
						m_cnt+=1;
					}
					else {
						if(!m_full_str.equals("")){
							m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";
						}
						else{
							m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";
						}
					}
				}*/
			}
			else if ((m_cnt)==1) {  // 2
				if (!m_digit.equals("0")) {
					if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
						if(!m_full_str.toString().equals("")){
						m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";						
						}
						else{
						m_full_str=ar_ones[Integer.parseInt(m_digit)]+" "+ar_ones[Integer.parseInt(m_digit)]+" ";				
						}
					}
					else {
						m_full_str=m_full_str+" "+ar_ones[Integer.parseInt(m_digit)]+" ";	
					}
				}
			}
			
			m_cnt++;
			
			if (m_cnt>1){
			m_full_str=m_full_str +" ";
			}
		
		}
	}
	else{
	m_full_str=m_full_str+" point zero ";
	}
	
	return m_full_str.toString();
	
	}
	
	public String numbersToChar_inside(String obj){

		Object[] ar_ones  = new Object[10];
		Object[] ar_tens  = new Object[10];
		Object[] ar_teens = new Object[10];
		
		ar_ones[0]        = "";
		ar_ones[1]        = "One";
		ar_ones[2]        = "Two"; 
		ar_ones[3]		  =	"Three";
		ar_ones[4]        = "Four";
		ar_ones[5]        = "Five";
		ar_ones[6]        = "Six"; 
		ar_ones[7]		  =	"Seven";
		ar_ones[8]        = "Eight";
		ar_ones[9]        = "Nine";
		
		ar_tens[0]        = "";
		ar_tens[1]        = "Ten";
		ar_tens[2]        = "Twenty"; 
		ar_tens[3]		  =	"Thirty";
		ar_tens[4]        = "Forty";
		ar_tens[5]        = "Fifty";
		ar_tens[6]        = "Sixty"; 
		ar_tens[7]		  =	"Seventy";
		ar_tens[8]        = "Eighty";
		ar_tens[9]        = "Ninety";
		
		ar_teens[0]        = "";
		ar_teens[1]        = "Eleven";
		ar_teens[2]        = "Twelve"; 
		ar_teens[3]		   = "Thirteen";
		ar_teens[4]        = "Fourteen";
		ar_teens[5]        = "Fifteen";
		ar_teens[6]        = "Sixteen"; 
		ar_teens[7]		   = "Seventeen";
		ar_teens[8]        = "Eighteen";
		ar_teens[9]        = "Nineteen";
        
		String m_value;
		String m_cents="";
		
		String m_full_str="";
		String m_digit="0";
		int m_length=obj.length();
		m_value=obj.toString();
		int m_dot=obj.indexOf(".");
		if (m_dot!= -1 ) {
			m_cents=obj.substring(m_dot+1,m_length);
			int num = m_cents.length();
			if (num==1) {
				m_cents=m_cents+"0";
			}
			else if (m_cents.length()==2) {
				m_cents=m_cents+"00";
			}
			m_length=m_value.substring(0,m_dot).length();
			m_value=obj.substring(0,m_dot);
		}
		
		
		for (int j=1 ; j<= 15-m_length; j++) {
			m_value="0"+m_value;
		}
		int m_cnt=15-m_length;
		for (int i=m_cnt; i<=14; i++) {
			
			m_digit = m_value.substring(m_cnt,m_cnt+1);
			//System.out.println("m_digit="+m_digit);
			if ((m_cnt)==3) {  // 100,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Billion ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==4) {  // 10,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Billion ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Billion ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if (m_cnt==5) {  // 1,000,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Billion ";
				}
			}
			
			
			else if ((m_cnt)==6) {  // 100,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Million ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if (m_cnt==7) {  // 10,000,000
				//System.out.println("m_value="+m_value+" m_value.substring(m_cnt,m_cnt+1)="+m_value.substring(m_cnt,m_cnt+1)+"  m_value.substring(m_cnt+1,m_cnt+2)="+m_value.substring(m_cnt+1,m_cnt+2));
				if (!m_digit.equals("0")) {
					//if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Million ";
						m_cnt+=1;
						i+=1;
						//System.out.println("m_full_str="+m_full_str);
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Million ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==8) {  // 1,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Million ";
				}
			}
			
			else if ((m_cnt)==9) {  // 100,000
				if (!m_digit.equals("0")) {
					if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred Thousand ";
						m_cnt+=2;
						i+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
					}
				}
			}
			
			else if ((m_cnt)==10) {  // 10,000
				if (!m_digit.equals("0")) {
					if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" Thousand ";
						m_cnt+=1;
						i+=1;
						//m_cnt+=2;
					}
					else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" Thousand ";
						m_cnt+=1;
						i+=1;
					}
					else {						
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==11) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Thousand ";
				}
			}
			
			else if ((m_cnt)==12) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" Hundred ";
				}
			}
			
			else if ((m_cnt)==13) {  
				if (!m_digit.equals("0")) {
					if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) {
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
						i+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if ((m_cnt)==14) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" ";
				}
			}
			
			m_cnt++;
		}
		
		
		m_cnt=0;
		if (!m_cents.equals("")){
			for (int i=m_cnt; i<=1; i++) {			
				m_digit = m_cents.substring(m_cnt,m_cnt+1);
				if ((m_cnt)==0) {  // 10
					if (!m_digit.equals("0")) {
						if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
							if(!m_full_str.toString().equals(""))
							{
								//m_full_str=m_full_str+"and "+ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							else 
							{
								//m_full_str=ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							m_cnt+=1;
						}
						else {
							//m_full_str=m_full_str+"and "+ar_tens[Integer.parseInt(m_digit)]+"";
						}
					}
				}
				
				else if ((m_cnt)==1) {  // 1
					if (!m_digit.equals("0")) {
						if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
							if(!m_full_str.toString().equals(""))
							{
								//m_full_str=m_full_str+"and "+ar_ones[Integer.parseInt(m_digit)]+"";						
							}
							else
							{
								//m_full_str=ar_ones[Integer.parseInt(m_digit)]+"";		
							}
						}
						else {
							//m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+"";	
						}
					}
				}
				
				
				m_cnt++;
				if (m_cnt>1){
					//m_full_str=m_full_str +" cents ";
				}
				
			}
		}
		
		return m_full_str.toString();
	}
}  
