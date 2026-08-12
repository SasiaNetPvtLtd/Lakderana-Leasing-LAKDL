
//SCREEN NAME:RECOURSE FACTORING AGREEMENT
//DEVELOPED BY MAHELA FOR FACTORING ON 27-02-2007

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

 

public class LAKDL_FA_CR_Factoring_Agreement extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	java.text.NumberFormat nf;
	
  public ResultSet rs;
  public ResultSet rs1,rs2,rs3;
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
			stmt3 = conn.createStatement();
			//out.println("conn="+conn);
			
		  if (m_chksql.trim().equals("main_page")) {
			out.println("<html><head>"); 
			out.println("<title>Factoring Agreement</title></head>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");	
			out.println("<script>");
			
			out.println("function print_data(){");
			out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_save_Factoring_Agreement?client_no="+m_client_no+"&facility_no="+m_facility_no+"&print="+m_print+"\";");  
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

				rs2 = stmt2.executeQuery (	" SELECT "+
  				  " NVL(CREDIT_LIMIT,0), "+//1
						" NVL(FEE_PACK_CODE,'-'), "+//2
						" NVL(INT_RATE,0),"+//3
						" NVL(TOLERANCE_CREDIT_PERIOD,0), "+//4
						" NVL(RESERVE_MARGIN,0), "+//5
						" NVL(FA_PRODUCT_CODE,'-')"+//6
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
						 }
							
							rs2 = stmt2.executeQuery (	" SELECT "+
  						  " FA_PRODUCT_CODE,"+
  						  " INITCAP(FA_PRODUCT_DESC) "+
 							"FROM "+m_schema_name+".FA_CO_MAS_PRODUCT "+
 							"WHERE FA_PRODUCT_CODE='"+m_product_code+"' ");
							
							more = rs2.next();
							if(more){
							 m_product_name= rs2.getString(2);
							}
							
			out.println("<blockquote><font size=2><p style='text-align:justify'>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:center;}'><b><font size=4> "+m_product_name+" Agreement</font></b></td></tr>");
			out.println("</TABLE><br><br>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >1.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	This Recourse Factoring Agreement entered between <b><i>Lakderana Investments Limited </i></b> having its registered office at No. 100,   Buthgamuwa Road,  Rajagiriya and the principle place of business at No. 100,   Buthgamuwa Road,  Rajagiriya carrying on business under the trade name '<b><i>Orient Factor</i></b>' to factor the debts </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	the company/ sole proprietorship/ partnership hereinafter called the '<b>the seller</b>' as described item 1A of the schedule and to assign such debt to the Orient Factor in consideration of advance payment of debts by Orient Factor and</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	The Guarantors (hereinafter called 'The Guarantors' named in item 1B of the schedule which term or expression as herein used shall where the context so requires or admits mean and include the said Guarantors his/their heirs executors and administrators/ its successors and assigns)  </td>");
			out.println("</tr>");
			out.println("</table><br><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >1.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	In this agreement and the guarantee thereto the following terms shall have the following meaning.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Orient Factor' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	means and includes Lakderana Investments Limited</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Seller' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	the company/ sole proprietorship/ partnership who's debts are to be sold to the Orient Factor </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Guarantors' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	Guarantors to this agreement </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Approved Debt' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A debt assigned for funding purposes </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Agreement' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	The Agreement means the entire terms and conditions contained in this  agreement. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Assignments' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	An absolute written Assignment in favour of Orient Factor. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Associate' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A subsidiary or holding company of seller( as defined in section 150 of the Companies Act No.17 of 1982) or any amendment or any subsequent act or any company in which seller or any such subsidiary  or holding company owns  25 per cent or more of the issued share capital or any company firm, or  business of which any director of Sellers for the time being is a partner in or which seller has an interest (whether directly or  indirectly) in 20 per cent or more of this share capital or its assets (as the case  may be). </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Contract of Sale' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	Debt at arises out of a contract for the sale of goods and/or work done service supplied and/or  hiring you on credit terms to a Customer.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Customer' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	The  party/ parties who shall become indebted to seller under a Contract of Sale whether individual/ partnership/ limited liability company.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Debt' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	The Amount including any tax or duty payable by the customer (and where the context so admits a part of such amount) owing or accruing due (whether presently payable or not) from any customer who resides in and/or carries on business in Sri Lanka or in any one of the additional countries listed under item 12 of the Schedule.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Disapproved Debt' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A Debt which is not accepted by Orient Factor.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Initial Payment' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A payment up to maximum Initial Payment Percentage (specified in item 4 of the schedule) on account of the purchase price of debt (as defined in clause 7.1) before the maturity date specified in item 6 of the schedule relating to such debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Insolvency/ Insolvent' </td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:justify;}' valign='top' >(a) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	In relation company (i)  the presentation of a  petition to wind  "+
					"up the company ; or (ii) the calling of a meeting to pass any resolution to wind up the company whether by its creditors or members or (iii) the appointment of a  receiver or  judical factor in respect of all or any part of a company's undertaking assets or property or (iv) the making of a proposal for a voluntary arrangement or the making of any application or order for or a voluntary agreement or the making of any application or order for in relation to the appointment of an administrator under the companies act no 17 of 1982 (or any statutory modification or re enactment thereof) ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:justify;}' valign='top' >(b) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	In relation  to partnership: the issue of a petition for its "+ 
          "winding up; and in relation to a natural person or partnership the issue of a petition for bankruptcy ; or (ii) the making of any proposal or order for or in relation to and voluntary agreement under part XXXVIIIA of the Civil Procedure Code or under the insolvency Ordinance  No.7 of 1853 ( or any statutory  modification or reenactment thereof) or (iii) sequestration.");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:justify;}' valign='top' >(c) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	In relation to any company natural person or partnership (i) "+   
					"allowing a security  holder or encumbrancer to take possession of any assets or the  suffering of distress execution diligence or sequestration or other legal process upon any assets or (ii) ceasing or threatening to cease to carry on business or (iii) the making of any arrangement or composition with or for the benefit of creditors (including without prejudice to the generality of the foregoing the granting of a trust deed)   or the calling of a  meeting for the same whether formal or informal  or (iv) giving notice of the intended suspension of  payment of debts or (v) allowing any judgment  or decree or unsatisfied for seven days or more.");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Invoice Value' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	The gross amount  payable by the customer and shown on the invoice(including) all statutory charges) </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Minimum Balance' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A retention which  orient factor may at their discretion withhold from sums  due from orient factor to seller to cover any amounts which may from time to time  be owed by seller to orient factor or which may be continently owed whether  ascertained or not. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Notification' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A schedule in such form as orient factor  may from  time to time  specify  to be  completed and signed schedule   by seller's  duly authorized  officer detailing  unpaid debt  and 'notified' in relation to a debt shall be construed accordingly . Where  a variation  of this  agreement  provides  for the  transmission of data  be electronic  means  to orient factor computer facility it shall mean the information  provided   by such electronic transmission  and identified   by such   codes or passwords  as orient factor may from   time to time  specify.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' >'Recourse Period Related rights' </td>");
			out.println("<td width='10%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The period of time after which  debts shall  be deemed to be Disapproved Debts.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' > </td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' > (a) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All seller's  rights in relation  to a contract  of  sale  and all remedies  for enforcing  payment  of the debt  arising  from  such  contract  of sale.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' > (b) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>seller's title  to and all  rights  in  the  goods  to which  such contract  of  sale relates.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' > (c) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All deeds documents  insurance  and  real  and  personal   securities taken  or to  be taken   or held or to be held by seller  in  connection   with such  debt  and</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' > (d) </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The ownership  of all  books  records  computer   data  and documents   on or by   which   such   debt  is recorded   or evidenced.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='25%' class='rep-body' style='{text-align:justify;}' valign='top' >'Working Day' </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>	A day on which orient factor establishment is open.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>2.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> COMMENCEMENT AND DURATION </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >2.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>		The commencement date of this Agreement shall be the day on which seller sign this agreement and this Agreement  shall remain in force until either:-</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:justify;}' valign='top' > a. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>One of orient factor gives to the other at least three months written notice of its/his intention to terminate the agreement; or</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='8%' class='rep-body' style='{text-align:justify;}' valign='top' > b. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>b.	It is determined in accordance with the terms of this agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>3.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> PURCHASE OF DEBTS. </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >3.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller agree to sell and <b><i>orient factor</i></b> agree to purchase to all debts existing at the commencement date or arising thereafter during the currency of this agreement upon the terms of this agreement all debts other than debts excluded by clause 5. The ownership of each debt in existence on the commencement date shall vest in <b><i>orient factor</i></b> on that date unless specifically excluded under items 11 of the schedule. The ownership of any debt coming into existence after the commencement date will,  subject to the provisions of clause 5 and clause 6  automatically vest in orient factory immediately upon the creation of the debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >3.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Upon the debt vesting in <b><i>orient factor</i></b> pursuant to clause 3.1 there shall automatically vest in <b><i>orient factor</i></b> all the related rights legal and otherwise pertaining to such debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >3.3 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Upon request by <b><i>orient factor</i></b>, seller will forthwith create execute and deliver to orient factor at seller's cost duly stamped assignments of such debts as orient factor may require or of the title rights deeds instruments documents insurances and securities included in any related rights, or of any other deed instruments or document required to enable orient factor to exercises their rights under this agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>4.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> NOTIFICATION SCHEDULE AND NOTICES OF ASSIGNMENT </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >4.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller will on the commencement date deliver to orient factor a Notification Schedule in a form  and accompanied by such documents as orient factor may specify from time to time detailing all debts to which this Agreement applies which are unpaid on that date together with details of all relevant credits. Thereafter seller will likewise notify orient factor of all debts in each case within seven days of the delivery of the goods to the customer or the completion of the service (as the each case may be) giving rise to the debt and of every credit within seven days of the customer's becoming entitled thereto. With each notification schedule seller will deliver to orient factor one copy of each invoice or credit note referred to therein ( and such  further copies as orient factor may hereafter specify ) together with such proof  as orient factor may require of the giving of the order for or the performances  of the relevant  contract of sale or its items.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >4.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Prior to the delivery of the relevant notification schedule to orient factor, seller will dispatch to the customer the invoice or credit note in respect to every debt detailed in such notification schedule and ensure that the invoice and each copy bears notice in bold print of the assignment of the debt to orient factor and of the due date for payment with such wording as orient factor may from time to time specify. Seller will also cause a notice of assignment to be endorsed upon any other document that seller may issue relating to debts purchased by orient factor.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >4.3 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller will at all times keep orient factor informed of all persons duly authorized to sign notification schedules on seller's behalf and other documents relating to this Agreement. However orient factor shall be entitled to assume that any person purporting to communicate with orient factor has the requisite authority to do so. Orient factor shall be entitled to relay and act upon such communication notwithstanding any deficiency in or absence  of such authority.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>5.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> EXCLUDED DEBTS </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' > </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All debts of the following classes are excluded from the scope of this agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >a.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>debts due from  a director  or partner  or shareholder of seller's or of their respective spouses;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >b.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>debts due from  an employee of sellers;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >c.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>debts due from any person whose relationship to seller is with  the meaning of 'Associate' as defined as defined in clause 1.2;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >d.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>debts arising from the sale of any of seller's capital  assets;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='15%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >e.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Debts specified in item 11 of the schedule.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>6.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> APPROVAL OF DEBTS </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >6.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>For the efficient day to day running of this Agreement the following procedure will apply.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >a.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>seller will apply for a  funding limit in respect of a specific customer by such means as orient factor shall hereafter from time to time  specify in writing;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >b.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient factor shall investigate such customer and at  orient factor discretion advise seller of a funding for that customer up to which limit unpaid debts should be treated as approval debts subject to the terms of this Agreement; all other debts shall be disapproved  debts;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >c.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient factor shall be entitled at orient factor discretion by written or oral notice to withdraw or vary such funding limit whether before or after receipt of a notification schedule referring to the respective customer.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >6.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Without further notice any debt shall  be treated as a disapproved debt upon any breach of any warranty or undertaking by seller to orient factor in respect of such debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>7.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> THE  PURCHASE PRICE </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The purchase price of each debt vesting in orient factor together with the related rights pertaining to such debt shall be the invoice value of the debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>seller liable to pay orient factor an administration charge as detailed in item 2 of the schedule and the amount of such charge in relation to each debt shall be debited to seller's account as soon as such debt shall have been notified. If in such period as is set out in item 3 of the schedule under the reference to 'Minimum  Administration Charge'  the total of all Administration Charges has not amounted to the sum stated in item 3 of the schedule then seller will forthwith pay to Orient Factor a sum equal to the deficiency. Orient Factor shall be entitled on a monthly basis to debit seller's account pro rata in advance on account of Minimum  Administration Charge.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.3 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The purchase price of each notified  approved  debt  will be  credit  to seller's account  on the maturity date specified in item 6 of the schedule and subject  to Orient Factor rights pursuant  to clauses 7.7, 7.8 and 7.9 the balance of such purchase price  after deduction  of any initial payment relating  to such  debt shall be payable by Orient Factor to on the said maturity date.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.4 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>On request by seller (or at any time in orient factor's discretion) Orient Factor  may (but shall not be obliged to ) make to seller an initial payment on account of the purchase price of any notified approved  debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.5 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller will pay to Orient Factor a discount charge at the rate specified in item 5 of the schedule  calculated  from day to day on such sum as shall represent  the total of all initial payments made by Orient Factor to seller and all sums payable by seller to Orient Factor hereunder LESS the purchase price or the balance  thereof (as applicable) credited  to seller's account on respective  maturity dates.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.6 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Value for cleared funds in respect  to payments from whatsoever source against debts purchased by Orient Factor or the amounts due to Orient Factor from seller under the terms of this agreement  will be given by Orient Factor to seller on the date and time of realization of the cheque or money order or bank draft (as the case may be ) in Orient Factor favour  after receipt  by Orient Factor of the said payments directly or into Orient Factor account with a Bank to be indicated to seller.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.7 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall at any time be entitled to debit seller's account with any sum payable presently contingently by seller to Orient Factor and whether arising under this Agreement or not including any liability of seller to Orient Factor as a customer of any other clients of Orient Factor and Orient Factor may apply any such sum in Orient Factor on account of the discharge of any amount payable by Orient Factor  to seller. Orient Factor may make a reasonable estimate of any sums to be debited to seller's account if the amount thereof is not immediately ascertainable.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.8 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall have the right to deduct from any payments due to seller a sum sufficient to satisfy the requirements of the minimum balance.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.9 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall have the absolute right to retain the minimum balance whether before  or after the  termination of this  agreement  for so long as seller shall be under any actual or contingent liability to Orient Factor whether arising  under this Agreement or not. Orient Factor may draw upon the minimum balance for any sum owing to Orient Factor by seller. Seller shall not be entitled to interest upon sums retained in the minimum balance.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >7.10 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Any payment to seller from seller's account shall be made by Orient Factor posting to seller a cheque drawn upon any branch of the Bank to be indicated to seller or dispatching the same to such other party as seller may nominate in writing. Payments by methods other than cheque at seller's request will be made entirely at Orient Factor discretion and at seller's own risk and shall be subject to additional charges.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>8.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> GENERAL ACCOUNTING  PROCEDURES </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor have the right to debit seller's account with;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >a.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All expenses incurred by Orient Factor connection with the execution or enforcement of this Agreement together with all  stamp duties penalties  and fines payable. Assignments and reassignments of any nature and the cost of obtaining  a release of any lien charge  trust or encumbrance over debts together with any losses  expenses  or damage (contingent or otherwise) suffered  by Orient Factor in the event of any reach of your undertakings warranties or indemnities herein and where necessary  a reasonable estimate  thereof  may  be made.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >b.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All bank charges for collections and should Orient Factor purchase a debt expressed in a currency other than Sri Lankan rupees any exchange  rate  losses.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >c.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All legal and other costs and expenses  incurred  by Orient Factor in collecting or attempting   to collect  any  debt; and </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='7%' class='rep-body' style='{text-align:justify;}' valign='top' >d.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>All losses damages  interest  and costs   suffered or incurred  by reason of any breached by seller of the terms of this Agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall credit seller's account with any exchange  rate gain realized  following the  purchase from seller of a debt expressed in a currency other than Sri Lankan rupees.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.3 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall maintain such accounts as are necessary to record the transactions between seller.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.4 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall not seek to raise any compensation set-of counterclaim against orient factor until all monies due to Orient Factor from any person in respect of all debts vesting in Orient Factor have been paid.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.5 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall render to seller statements of seller's account with Orient Factor at regular intervals.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >8.6 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>A copy of Orient Factor ledger sheets whether maintained manually or by machine or by computer and certified  by either Orient Factor's auditors or  company  secretary to be true and accurate copy shall be final and conclusive evidence  as to the sums collected  and received  by Orient Factor  in inspect of debts  of sums due from seller to Orient Factor or vice versa up to the date  of such certificate  save only  to the extent  that seller shall prove specific errors  or omissions appearing on the face of the ledger sheets.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>9.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> CREDIT  NOTES AND BALANCES  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >9.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Upon a customer  becoming entitled to  a credit  in respect of any debt  notified  to Orient Factor  then seller will forthwith dispatch  a credit note to the customer  and seller will immoderately pay to Orient Factor  the full  face  value  of such credit note.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >9.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller hereby  irrevocable  authorize Orient Factor to  make payment to the customer  of any credit  balance  on any customers  account whether  arising  from   the issue  of a  credit  note or otherwise or to  deal with such credit balance  such a manner  as Orient Factor consider proper .</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>10.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> RECORDS /FLOW OF INFORMATION </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >10.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller will keep proper accounts and minute  books and will allow orient factor their authorized representatives  at  any time  to inspect  the same and  be provided  with  copies. Seller agree to provide Orient Factor upon request with financial statements and such other information relating to debts or seller's business as Orient Factor may require. Upon seller's failure so to assist Orient Factor may at seller's expenses appoint  accountants  or professional experts to obtain such information. Upon receipt by seller of seller's audited annual and  accounts and  in any  event not later than six months from the end of seller's financial year seller will  send Orient Factor copies  of such  audit  annual  accounts  unless other arrangement in writing have been made with Orient Factor  in advance. Seller warrant to Orient Factor that all balance sheets  profit and loss accounts and all other statements   information  and documents  which either  have been or  may hereafter be supplied to Orient Factor whether with the intention or  object of inducing  Orient Factor to enter  this Agreement  or to treat  any debt  as  an approved  debt or otherwise  fairly  represent  seller's  true financial  position  and the debts  and are otherwise accurate complete  and correct in all material  respects.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >10.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>In order to facilitate  the operation of this Agreement seller hereby  authorized  Orient Factor to speak  and /or write to seller's bankers  on any  matter  whatsoever  which  Orient factor may   at their sole discretion  consider  pertinent  to this  agreement and  seller warrant   that seller have  similarly authorized   seller's bankers  to speak  and/or  write to orient factor  about  any matter whatsoever  which at their  sole  discretion  they  consider   pertinent    to the operation  of this Agreement   or to  seller's  banking  arrangements with them.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>11.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> WARRANTIES  UNDERTAKINGS  AND INDEMNITY  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall warrant that  (except as disclosed   to orient Factor  in writing  before the  date hereof )   seller have  created  no security  charge  pledge trust or  other  encumbrance which affects or may affect debts and have no  arrangements  (except  with orient Factor)  for the  sale  assignment or discounting   of debts. Seller shall undertake  to orient Factor that seller will not whilst this Agreement is in force grant  any disposition  trust  pledge or charge  or other encumbrance (except  in orient Factor's favour)  which affects  or may affect  debts  and that at all times seller  will assists orient Factor to  safeguard  orient Factor's interest   in debts  purchased  by orient Factor.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall warrant that all details in every notification  schedule  shall be accurate  correct   and complete  in all respects.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.3 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall undertake to ensure that the warranties  and undertakings   given  in the preceding  paragraph  shall remain  fulfilled  throughout the  currency   of this Agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.4 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>In relation  to each debt  referred  to in a notification  schedule  seller will be  deemed to under take  and/or   warrant  that;</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >a. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The debt relates  to goods  which have been  delivered  to the customer  or services which have been performed  in  both cases before the date of such notification schedule and seller will perform all outstanding  or continuing  obligations  to the  customer  under the  contract of  sale or any related   contract.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >b. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The contract  of sale   under which  the debt arise will be  valid and enforceable and no supplier to seller has  or may have and claimed to the debts  or  its  related  rights  whether  by  equitable tracing right  or otherwise.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >c. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Each invoice  will state  the due  date  for payment  of the debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >d. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The amount  payable  by the customer  will not be less  than  the invoice  value and will be due and payable no later  than  the due  date for payment stated  on the invoice and if not so stated  will be  payable within 30 days  of delivery  to orient Factor  of the relevant notification  schedule.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >e. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The customer will not   raise  any compensation  set off  or counterclaim  against  the debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >f. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall pay <i><b>orient factor</b></i> an amount equal  to any cash  discount  taken  or debit   or credit  note  issued  or relied  upon   by the customer  when  paying   the debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >g. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>  After delivery   to orient Factor  of the notification schedule   no extension  of time  for payment  of the debt  referred  to therein  nor  any waiver  modification  rescission  or termination  of any  contract  of sale  will be  agreed by  seller without  orient Factor's prior  approval; and </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >h. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>  Seller shall notify by orient Factor promptly in writing of any  dispute (whether justifiable  or not)  between  seller  and the   customer.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.5 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller undertake to orient Factor that:-</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >a. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>seller together with seller's  directors  employees  and agents  will assist orient Factor in every  reasonable  way to safeguard orient Factor's right title  interest  in the  debts  vesting  in orient Factor; and</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >b. </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>seller will carry  out any  procedure  we may  require  in relation  to the day  to day  administration  of the Agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.6 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall  pay to <b><i>orient factor</i></b> on demand  the invoice value  of any debt if the customer  claims   to  reject the goods and/or  work  done and/or services  or fails or declines or disputes his  liability  to pay the debt or any part thereof  and gives  as a reason   therefore any alleged breach  by seller of the  contract  of sale  or if the debt shall for any other reason not be or become  due and owing  or shall   be alleged by the customer  not to be  due or owing.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.7 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller hereby  guarantee   to orient Factor  that   each  debt  will be paid to orient Factor  at the earlier  of the customer becoming  insolvent or the expiry of the  recourse  period  referred  to in   item 7 of  the schedule. Upon  failure  of any customer  so to  pay  seller will pay to  orient Factor upon  demand  in respect  of such   debt  the full invoice  value of such  debt  and all other  costs   and expenses  incurred. Upon such payment  the debt  shall be  deemed  to have re-vested  in seller. Upon request by seller will  notify  the customer  at seller's expense  of such re-vesting.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.8 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If at the expiry of the recourse period  seller wish orient Factor to continue  with  collection  activity on the debt concerned  seller  will pay  an additional   monthly  factoring  administration  charge  as specified  in item 8 of the schedule.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >11.9 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller hereby  undertake   to indemnify  orient Factor on demand against  all losses   costs  , damages claims  and expenses  which  orient Factor may  suffer or  incur   as a  result  of any  breach  by seller  of the terms of this Agreement  (including   Seller's warranties  and undertakings)  regardless  of any  knowledge  which  orient Factor may  have  of the breach  or of  the circumstances  which   give  rise  thereto   or any  other  arrangement   orient Factor may  make  to avoid  or mitigate  the same. Any  obligations  by seller under this  indemnity or any other liability  to make  payments  to orient Factor shall continue  without  any  right  of compensation set off  counterclaim  until  orient factor shall  have  received  the full  invoice value  of debts purchased by  orient Factor  from seller and  all other sums  due to orient Factor.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>12.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> COLLECTIONS </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >12.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller will not attempt  to obtain  payment  to seller  of any debt  and seller will co-operate  with orient Factor  to enable  orient Factor to secure  collection and enforcement.  Orient Factor shall have the sole  and unfettered  right  to collect   any debt  and to enforce  payment thereof in such manner  and to such extent  as orient Factor  shall in their  absolute  discretion   think  fit  including  without  prejudice to the foregoing either in orient Factor own and/or seller's name  instituting  carrying  on  or defending  any legal proceedings (whether  in Sri Lanka  or elsewhere)  and to  settle  compromise or adjust  any claim. However  orient Factor shall be  under  no obligation  to  institute  or defend  any proceedings   by or  against  orient Factor in  relation  to any  debt. Seller agree to be   bound   by anything  done  by orient Factor  in the exercise  of their  rights.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >12.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall make available  to orient Factor free  of charge all evidence  required  by orient Factor in any proceedings  and procure  the  attendance at any  hearing of such  witnesses  as orient Factor  may  require.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>13.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> TRUSTS  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >13.1 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall hold  in trust for orient Factor  and  separately  from  seller's own  property  any debt and its  related rights purchased by  orient factor  pursuant  to clauses  3.1 and 3.2   of which  the ownership  shall fail  to vest  in orient Factor for  any reason.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >13.2 </td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If any payment  in respect  of an  approved debt  is paid  direct   to seller  then seller will  keep  same  separate   from  seller own  monies  and seller  shall   hold the same  in trust for orient Factor. Seller will  immediately   deliver  to orient Factor or  direct  to  a bank accounts  specified  by orient Factor  the identical cash  cheque, bill of  exchange  or negotiable instrument  and if necessary  seller will endorse   the same   to orient Factor  prior to such delivery   to enable orient Factor to receive payment.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>14.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> ASSOCIATED   AND SUBSIDIARY  COMPANIES   </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller undertake  to procure that each  of seller's  associated or subsidiary  companies shall  if required  by orient Factor  enter  into  an Agreement in substantially  identical  terms  to this Agreement.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>15.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> LAW  OF CONTRACT OF SALE    </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller undertake  that save  as mutually  agreed  in writing  between  orient Factor and Seller,  seller will  ensure  that the  proper  law  of every   contract   of sale   giving  rise   to a  debt  purchased  by orient Factor   is the  law of Sri Lanka  and  that  no such   contract  of sale  will include  any prohibition  against  the assignment  of the  debt.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>16.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> POWER OF ATTORNEY  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >16.1</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>As  security  for all sums  howsoever due  or becoming  due from  seller to orient Factor  seller hereby  irrevocably  appoint  orient Factor and  each  of their directors  and orient and company  secretary   and  managers  at any time  jointly  and each  of them severally  as seller's attorneys  of attorney  both  during  and after  the termination  of this agreement  in seller's  name  on seller's  behalf as  orient Factor consider  necessary   to execute  or sign deeds  instruments  and  documents  required  hereunder  and to deal with  complete  and endorse  remittances cheques  and other  instruments and to institute or defend  legal  proceedings  and to  perfect   orient Factor's title  to any  debt   or the goods  or any  right  instrument or security taken  or arising in connection  therewith  and to   secure  the performance  of any of seller's  obligations  to orient Factor  or to seller's customers   and to  execute  any security  required  of seller   and do all   other  things  required  to give  effect  to this Agreement   ( including  the execution   of assignments  of debts)</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >16.2</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>orient Factor and  their directors  and  company  secretary  and managers  are hereby  empowered  to appoint  remove  and  substitute  any attorney  or agent  for seller   in  respect   of all or  any of  the  matters  referred  to in  this  power of attorney. Seller agree  to ratify and confirm   whatever shall be   done  by virtue  of this Power  of Attorney.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >16.3</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>This Power of Attorney  shall be  irrevocable  until  all monies  due to orient Factor  from seller under  this agreement  and from every  customer  have  actually  been paid .</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>17.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> TERMINATION  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.1</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>After delivery  of any notice to terminate  this Agreement  seller will  continue  to deliver  notification  schedules to  Orient Factor in  respect  of all  debts  arising  from  goods delivered and  services  performed  up to and  including the actual  date of termination .</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.2</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Orient Factor shall have the right in their  absolute  discretion to terminate  this Agreement  forthwith   by  written  notice  to seller in any of the following events:-</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >a.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If seller commit   a breach of any of the terms of this Agreement </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >b.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If seller become insolvent. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >c.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If any of seller's obligations  to third  parties  for the  payment  of borrowed  money   by reason  of seller's default  become due or capable  of being  declared  due prior   to their stated  maturities   or be not paid  when due. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >d.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If any person who has  given  orient factor a guarantee  or indemnity  in respect  of seller's  obligations  hereunder shall  become   insolvent  or shall  give notice to terminate  such guarantee  or indemnity  or shall  attempt  to do  or shall  die.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >e.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If any  person  who has given to orient Factor an undertaking  or waiver  in reliance  on which  orient Factor shall have  entered in to this Agreement  shall be in beach  of such undertaking or waiver or shall  withdraw  or seek  to  withdraw the same </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >f.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If Seller fail to deliver to orient Factor a notification schedule for a period in excess of one calendar month.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >g.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>For any reason which orient Factor consider to be detrimental to orient their factoring business or any of  their other clients.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.3</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If seller give  orient Factor notice  to terminate  this Agreement  orient Factor shall  be  under no obligation  to treat  as  approved  debts  any  debts  subsequently  referred   to in notification schedule.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.4</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Upon orient Factor's becoming entitled  to terminate  this Agreement  pursuant   to clause 17.2 (whether  or not orient Factor exercise  such right)  orient Factor may withdraw   all previously notified funding  limits   and orient Factor  may  treat  as a   unapproved debt any debt which  shall previously  have been  treated  as an approved  debt. Thereupon orient Factor may require seller to repurchase   immediately   from  orient Factor's at  the full  invoice  value  all debts  purchased  by orient Factor  hereunder  and then remaining  outstanding. No debts  shall  re-vest  in seller  until the  repurchase  price   of all such  debt  required  to be  so  repurchased  together  with all costs and  any stamp  duties  in respect  of such  re-vesting  and all  other sums  due to  orient Factor herein under  shall have been paid </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.5</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>If seller shall  give less  than  the three calendar  months  notice  required   to terminate  this Agreement under clause   2.1 (a)  (or shall become  insolvent)  then such  notice  shall be deemed to be one  of three  calendar months unless by notice in writing   to seller  orient Factor accept such notice  for the  period stated  in seller's   notice whereupon  in either event  orient factor  shall   forthwith  be entitled  to debit  seller's account  with such  sum as shall be greater of either :-</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >a.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The minimum  administration  charge referred to in item  3 of the schedule  (and  calculated pro rata if the said minimum  administration  charge  relates   to  a  period of  other  than  three  months) or </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >b.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>An amount equal to the administration charges for the last three calendar months prior to receipt of the notice to terminate.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >17.6</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Subject to the  provisions of clause 17.3 and 17.4  termination  of this Agreement  shall not  affect  any rights  obligations  or liabilities  of either of orient Factor in relation to any  debt  of which   the  ownership  shall  have  vested   in  orient Factor prior  to such  termination becoming  effective . The  provisions  of this Agreement  shall continue  to bind both of orient Factor for so  long  as may be  necessary  to give  effect  to such  rights  obligations  and liabilities.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>18.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> VARIATIONS AND NOTICES   </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >18.1</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Any variations to this agreement (except as otherwise  provided herein) may be effected  only in writing   signed by  or  on behalf  of both of orient Factor and Seller. Any special terms and conditions set out in any schedule shall be deemed incorporated in this agreement  provided  the contents  of such schedule have been  approved  by both of orient Factor and Seller in writing.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >18.2</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Any notice  required  or permitted  herein  to be served  by orient Factor on seller shall be sufficiently served if  posted by first  class post  to or delivered  to seller's registered   office of  seller's address  last known to orient Factor or if sent  by facsimile  or telex  to seller's number  made known  by seller to orient Factor or  if handed to any one  of seller's directors. Notices  served  by post  shall  take effect   48 hours  after posting. Notices served by hand shall take effect upon receipt. Notices sent by telex or facsimile  shall take  effect  upon  transmission. Notices pursuant to clause 6.1(c) served by post shall take effect upon posting.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >18.3</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>In calculating any clear period of days specified  in the Agreement  the date of  receipt  of any  documents  by orient Factor or the  date of dispatch  of any notice  or other act  by orient Factor shall be excluded .</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>19.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> WAIVER  </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >19.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Any  tacit  or specific  waiver or release  or acquiescence  by orient Factor of  or in any  breach  by seller  of any  term of this Agreement  shall  not  constitute  a general waiver  of such  term or  of any  such  breach nor shall it be  deemed  to  imply consent  to any subsequent  breach. Orient Factor's  rights  under  this Agreement  shall not be  prejudiced of  affected  by  the granting of time  or other indulgence  to seller or any customer guarantor  or indemnifier.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>20.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> EXPRESSION    </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Wherein any place outside Sri Lanka the  meaning of an expression used in this agreement   has counterpart in that place it shall unless the context otherwise  requires have the meaning of the closest equivalent  thereto in the place concerned.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>21.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> GOVERNING LAW   </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >21.</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The arrangements set out herein shall be read and construed in accordance with the Laws of Sri Lanka. Seller hereby submit to the jurisdiction of the courts of Sri Lanka but without prejudice to orient Factor's rights to bring proceedings in the courts of any other jurisdiction.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>22.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> ASSIGNMENT   </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Seller shall not without orient Factor's prior written consent be entitled to  create any charge over or to assign  any of Seller's rights hereunder or to sub-contract or delegate any of Seller's obligations hereunder . Orient Factor shall be liberty to assign the benefit hereof without Seller's consent to any other party. Orient Factor shall be entitled  to novate this Agreement with any other party and seller hereby give  Seller's consent thereto.</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='7%' class='rep-body' style='{text-align:left;}'><b>23.</b></td><td width='*%' class='rep-body' style='{text-align:left;}'><b> GUARANTEE   </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The undersigned Guarantors; </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(a)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>expressly agree and undertake jointly and severally to pay to the orient Factor without demur in first demand the moneys hereafter mentioned provided always that the total liability ultimately enforeceable against guarantors us and/or each of us shall not exceed the sum of Rupees "+nf.format(m_facility_amount)+" and interest at the rate of <input class='txt_input6' type='text' name='TXT_INT_RATE' maxlength='4' value='32' size='10' style='width:20' onblur=\"\"> per annum from the date of demand by the Orient Factor upon us and such other sums as accrue after such demand in accordance with the orient Factor's usual course of business and all legal costs charges and expense incurred by the orient Factor</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(b)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>This guarantee shall apply to all moneys owing payable or belonging to the Orient Factor under and in terms of this agreement or which at any time hereinafter shall or may be owing payable or belonging to Orient Factor is or may be entitled to as and by way of  administration charges and/or fees or other moneys falling due under this agreement and such of other moneys as may be due from the client to the Orient Factor in terms of this agreement in any manner whatsoever. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(c)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>This Guarantee shall not be considered as satisfied by any intermediate payment of the whole or an part of the moneys herein before mentioned but shall be a continuing security and shall extend to cover any sums of money which shall for the time being and which shall from time to time constitute the ultimate balance due by the client to the orient Factor in respect of the moneys herein before mentioned up to the limit aforesaid. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(d)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>This guarantee shall be in addition and shall not in any way be prejudiced or affected by any collateral or other security now or hereafter held by the orient Factor for all or any part of the moneys herein mentioned. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(e)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>Guarantor's obligations under this guarantee shall be a continuing undertaking and shall not be considered as satisfied by any intermediate payment or satisfaction made by Guarantors</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(f)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>A certificate of any director or manager of the orient Factor as to the liabilities for the time being due from the client shall be conclusive and binding upon orient Factor</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(g)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>This guarantee shall not be in any way discharged or dismissed not shall Guarantor's liability be affected by reason of the orient Factor from time to time and without guarantor's knowledge or consent granting any time indulgence or concession or compounding with discharging releasing or varying the liability of the client or any other person or concurring in accepting or varying any compromise agreement or settlement or omitting to claim or enforce payment or determining, varying reducing or extending the terms of this agreement or by anything done or omitted which but for this provision might operate to exonerate the guarantors. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(h)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The guarantors respectively agree that the guarantor's liability under this guarantee shall be joint and several and shall not be prejudiced or in any way affected by the orient Factor granting with or without the knowledge or any of guarantors of any time or indulgence to the other of the guarantors or the orient Factor's into any composition with of release of the other of Guarantors </td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(i)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>A demand shall without prejudice to any other effective mode of making the same be deemed to have been sufficiently made hereunder if sent by post to the Guarantors at the address stated above and shall be assumed to have reached the guarantors within Forty Eight (48) hours of posting and in proving such service shall sufficient to prove that the demand was properly addressed and posted,</td>");
			out.println("</tr>");
			out.println("</table><br>");
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' ></td>");
			out.println("<td width='5%' class='rep-body' style='{text-align:justify;}' valign='top' >(j)</td>");
			out.println("<td width='*%' class='rep-body' style='{text-align:justify;}'>The Guarantors specifically agree that the orient factor shall be at liberty either in one action to sue the client and the guarantors jointly and or severally or to proceed in the first instance against the guarantors only and further guarantors hereby renounce the right to claim that the Company should divide its claim against the guarantors and bring action against Guarantors portion pro rata and the right to claim in any action brought against the guarantors that the orient Factor should only recover from Guarantors a pro rata share of the amount claimed in that action and all other rights, privileges and benefits whatsoever (nothing excepted) to which guarantors or sureties are or maybe entitled at law to in equity. </td>");
			out.println("</tr>");
			out.println("</table><br>");
			
			//Schedule
			//out.println("more=adsadasdadada"+more);
			rs3 = stmt3.executeQuery (" SELECT "+
  				  " CLIENT_CODE, "+//1
  				  " NVL(FULL_NAME,'-'), "+//2
  				  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
  				  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
  				  " NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), "+//5
						" NVL(NIC_NO,'-'),"+//6
						" NVL(DECODE(CLIENT_CATEGORY,'INDIVIDUAL','Individual','CORPORATE','Corporate','LIMITED','Private Limited Liability','PUBLIC','Public/Quoted','SOLEPROPRI','Sole Proprietorship','PARTNERS','Partnership'),'-'), "+//7
						" NVL(THIRD_PART_DEBTOR_DET,'-'),"+//8
						" NVL(CLIENT_CATEGORY,'-') "+//9
  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
  				  " WHERE CLIENT_CODE='"+m_client_no+"' ");
						
					//more = rs3.next();
					//out.println("more=adsadasdadada"+more);
					
					if(rs3.next()){
						m_c_code=rs3.getString(1);
						m_name=rs3.getString(2);
						m_add1=rs3.getString(3);
						m_add2=rs3.getString(4);
						m_city_desc=rs3.getString(5);
						m_cli_nic=rs3.getString(6);
						m_bus_nature=rs3.getString(7);
						m_client_cat=rs3.getString(9);
					}
			out.println("<p style=\"page-break-after:always\"></p>");		
			//out.println("<br><br><br><br><br><br><br><br><br>");		
			//out.println("<br><br><br><br><br><br><br><br><br>");		
			//out.println("<br><br><br><br><br><br><br><br><br><br>");		
			out.println("<table border='0' width='90%' class='table'> ");	
			out.println("<tr ><td width='*%' class='rep-body' style='{text-align:center;}'><b> SCHEDULE </b></td></tr>");
			out.println("</TABLE>");		
			out.println("<br>");
			out.println("<table width='90%' border='1' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='40' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 1A  Name, Address, NIC Number, Nature of Business of Seller");
			out.println("</td>");
			out.println("<td width='50%' height='40' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" "+m_name+","+m_add1+","+m_add2+","+m_cli_nic+","+m_bus_nature+" ");
			out.println("</td>");
			out.println("</tr>");		
			out.println("<tr >");
			out.println("<td width='50%' height='40' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 1B Name, Address, NIC Number of Guarantor/s ");
			out.println("</td>");
			
				rs3 = stmt3.executeQuery ("	SELECT "+
  				 " CLIENT_CODE,"+
  				 " NVL(GUA_NAME,'-'),"+
  				 " NVL(GUA_NIC_NO,'-'),"+
  				 " NVL(GUA_ADDRESS,'-') "+
 					"FROM "+m_schema_name+".FA_CO_MAS_CLIENT_3RD_GUARAN "+
 					" WHERE CLIENT_CODE='"+m_client_no+"' ");
			
			out.println("<td width='50%' height='40' class='rep-body' style='{text-align:left;}' valign='top'>	");
			more = rs3.next();
			    if(!more){
					 out.println(" - <br>"); 
					}
					while(more){
			      out.println(" "+rs3.getString(2)+","+rs3.getString(3)+","+rs3.getString(4)+"<br> ");
						more = rs3.next();
					}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 2. Administration  Charge (Clause 7.2)  ");
			out.println("</td>");
					
				rs3 = stmt3.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0003.0' and FACILITY_NO='"+m_facility_no+"' ");
				more = rs3.next();		
			
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			 if(more){
					
				  String m_val = nf.format(rs3.getDouble(1));
					
					int m_dot=m_val.indexOf(".");
					int m_length=m_val.length();
					String m_cents=m_val.substring(m_dot+1,m_length);
					String m_int_val= rs3.getInt(1)+"";
					String m_val2 = m_sn_methods.met_unformat_number(m_val);
					if(!m_cents.equals("00")) {
					//out.println(" "+m_sn_methods.numbersToChar(m_val2)+" Percent ("+nf.format(rs3.getDouble(1))+"%) <br><br> "); 
					out.println(" ("+nf.format(rs3.getDouble(1))+"%) "); 
					}
					else {
					//out.println(" "+m_sn_methods.numbersToChar(m_int_val)+" Percent ("+nf.format(rs3.getDouble(1))+"%) <br><br> ");
					out.println(" ("+nf.format(rs3.getDouble(1))+"%) ");
					}
					out.println("	of the  invoice  value  of each  debt.");
				}
			 else {	
					out.println(" ............Percent (.....%) <br><br> ");
					out.println("	of the  invoice  value  of each  debt.");
			 }
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 3. Minimum Administration  charge (Clause 7.2) ");
			out.println("</td>");
			
				rs3 = stmt3.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0004.0' and FACILITY_NO='"+m_facility_no+"' ");
				more = rs3.next();		
				
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			  if(more){
				   String m_val = nf.format(rs3.getDouble(1));
					 String m_val2 = m_sn_methods.met_unformat_number(m_val);
					 int m_dot=m_val.indexOf(".");
					 int m_length=m_val.length();
					 String m_cents=m_val.substring(m_dot+1,m_length);
					 String m_int_val= rs3.getInt(1)+"";	
					if(!m_cents.equals("00")) {	
				  out.println(" Rupees "+m_sn_methods.numbersToChar(m_val2)+" month   <br><br>(Rs "+nf.format(rs3.getDouble(1))+" /- ) from commencement ");
					}
					else {
					out.println(" Rupees "+m_sn_methods.numbersToChar(m_int_val)+" month   <br><br>(Rs "+nf.format(rs3.getDouble(1))+" /- ) from commencement ");
					}
				}
				else {
				  out.println(" Rupees .......... month   <br><br>(Rs....../- ) from commencement ");
				}
			
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 4. Maximum  initial  payment percentage   (clause 7.5) ");
			out.println("</td>");
			
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			
			  String m_int_pay = nf.format(m_initial_pay);
				String m_int_pay2 = m_sn_methods.met_unformat_number(m_int_pay);
				int m_dot2=m_int_pay.indexOf(".");
				int m_length2=m_int_pay.length();
				String m_cents2=m_int_pay.substring(m_dot2+1,m_length2);
				//String m_int_val2= rs1.getInt(1)+"";	
			if(!m_cents2.equals("00")) {		
			out.println(" "+m_sn_methods.numbersToChar(m_initial_pay+"")+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the  <br><br> invoice value of  a notified  approved  debt.");
			}
			else {
			out.println(" "+m_sn_methods.numbersToChar(m_init_pay+"")+"  Percent  ("+nf.format(m_initial_pay)+"%)   of the  <br><br> invoice value of  a notified  approved  debt.");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 5 Discount  Charge (Clause 7.5) ");
			out.println("</td>");
				
				rs3 = stmt3.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0002.0' and FACILITY_NO='"+m_facility_no+"' ");
					more = rs3.next();		
			
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			if(more){
			 String m_val = nf.format(rs3.getDouble(1));
			 String m_val2 = m_sn_methods.met_unformat_number(m_val);
			 int m_dot4=m_val.indexOf(".");
			 int m_length4=m_val.length();
		   String m_cents4=m_val.substring(m_dot4+1,m_length4);	
			 String m_int_val=rs3.getInt(1)+"";
				
			 String m_val3 = nf.format(m_int_rate);	
			 int m_dot3=m_val3.indexOf(".");
			 int m_length3=m_val3.length();
		   String m_cents3=m_val3.substring(m_dot3+1,m_length3);	
			 String m_int_val2=m_int_rate2+"";	
			 String m_rate = m_sn_methods.met_unformat_number(m_val3);	
			 if(!m_cents3.equals("00")) {			
			 out.println(" "+m_sn_methods.numbersToChar(m_rate)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus.");
			 }
			 else {
			 out.println(" "+m_sn_methods.numbersToChar(m_int_val2)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus.");	
			 }	
			 if(!m_cents4.equals("00")) {		
				out.println(" <input class='txt_input6' type='text' name='TXT_NIL3' maxlength='150' value='Nil' size='150' style='width:300' onblur=\"\" value=\""+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(rs3.getDouble(1))+"%) \" >  ");
			 //out.println(" "+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(rs3.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
				out.println(" on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
			 }
			 else {
			 //out.println(" "+m_sn_methods.numbersToChar(m_int_val)+"  per cent per annum ("+nf.format(rs3.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");	
				out.println(" <input class='txt_input6' type='text' name='TXT_NIL3' maxlength='150' value='Nil' size='150' style='width:300' onblur=\"\" value=\""+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(rs3.getDouble(1))+"%) \">  ");
			 //out.println(" "+m_sn_methods.numbersToChar(m_val2)+"  per cent per annum ("+nf.format(rs3.getDouble(1))+"%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
				out.println(" on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
				}
			}
			else {
			 String m_val = nf.format(rs3.getDouble(1));
			 String m_val2 = m_sn_methods.met_unformat_number(m_val);
			 String m_val3 = nf.format(m_int_rate);	
			 String m_rate = m_sn_methods.met_unformat_number(m_val3);
			 out.println(" "+m_sn_methods.numbersToChar(m_rate)+" percent  per annum ("+nf.format(m_int_rate)+"%)  plus. .......  per cent per annum (....%)  on the  shortfall   below  the minimum  balance  . The  rates  are  subject  to variations  depending  on market  rates  from  time  to time   and is  subject  to charges at   Orient factor's discretion.");
			}
			
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 6 Maturity  Date  (Clause 7.3)  ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" For all notified approved debts the maturity  date shall be the date  on which the cheque drawn under clause 7.6 is realized.");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 7 Recourse Period (Clause  11.7)   ");
			out.println("</td>");
			
			 String m_vall = m_tol_period+"";
			
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" "+m_sn_methods.numbersToChar(m_vall)+"  days ("+m_tol_period+" days)  after  the respective  debt falls due ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 8 Additional Monthly  Factoring  Administration  Charge ( Clause 11.8)    ");
			out.println("</td>");
			   
					rs3= stmt3.executeQuery ("	SELECT "+
  				"  NVL(APPLICABLE_VALUE,0) "+
 					"  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE "+
 					" WHERE FEE_CODE='FEE0020.0' and FACILITY_NO='"+m_facility_no+"' ");
					more = rs3.next();		
				
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			if(more){
			  String m_val = nf.format(rs3.getDouble(1));
			 	String m_val2 = m_sn_methods.met_unformat_number(m_val);
				int m_dot=m_val.indexOf(".");
				int m_length=m_val.length();
				String m_cents=m_val.substring(m_dot+1,m_length);
				String m_int_val= rs3.getInt(1)+""; 	
				if(!m_cents.equals("00")) {	
				out.println(" "+m_sn_methods.numbersToChar(m_val)+" Percent  ("+nf.format(rs3.getDouble(1))+"%) <br><br>  during the recourses  period");
				}
				else {
				out.println(" "+m_sn_methods.numbersToChar(m_int_val)+" Percent  ("+nf.format(rs3.getDouble(1))+"%) <br><br>  during the recourses  period");
				}
			}
			else {
				out.println(" ........ Percent  (.....) <br><br>  during the recourses  period");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 9 Security    ");
			out.println("</td>");
			
			  rs1 = stmt1.executeQuery (" SELECT "+
  				  " NVL(CLIENT_CODE,'-'),"+
  				  " NVL(CREDIT_GRANTED_SECURE_DETAIL,'-'), "+
						" BASIS_OF_CREDIT_GRANT "+
 				" FROM "+m_schema_name+".FA_CO_MAS_CLIENT_DEBT_DET "+
 				" WHERE CLIENT_CODE='"+m_client_no+"' ");
				more = rs1.next();			
					
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			if(more){
				if(rs1.getString(3).equals("Y")){
					String m_credit_grant_det=rs1.getString(2);
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
			
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 10  Government Levies    ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" All applicable statutory charges imposed  by the Government. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 11 Exclusions (Clause <input class='txt_input6' type='text' name='TXT_NUM' maxlength='2' value='5' size='10' style='width:15' onblur=\"\">)   ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_DEBT' maxlength='50' value='Existing  debts  ( at commencement  date)' size='10' style='width:320' onblur=\"\" >   ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 12  Countries   ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Sri Lanka  ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 13  Clauses inapplicable ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" <input class='txt_input6' type='text' name='TXT_NIL' maxlength='50' value='Nil' size='10' style='width:300' onblur=\"\">  ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" 14  Special Condition  ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" A minimum  fee Rs.<input class='txt_input6' type='text' name='TXT_INT_RATE' maxlength='4' value='350' size='10' style='width:40' onblur=\"\">/- will be charged for every dishonourd  cheque.  ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Lohika Fonseka");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Nishaman Karunapala");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Senior Manger - Factoring");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Assistant General Manager Operations");
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
			out.println("</TABLE>");
			out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='*%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" I/We hereby agree to the terms and conditions set out in this  agreement. ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br>");
			if(m_client_cat.equals("LIMITED")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Directors ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Directors ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			}
			else if(m_client_cat.equals("PARTNERS")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Partners ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("2. Partners ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			out.println("<br><br><br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("3. Partners ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("4. Partners ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
			}
			else if(m_client_cat.equals("SOLEPROPRI")){
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Proprietor ");
			out.println("</td>");
			out.println("</tr>");
			out.println("</TABLE>");
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
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" ................................ ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("1. Guarantors ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
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
			out.println("<td width='*%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Witness ");
			out.println("</td>");
			out.println("<tr >");
			out.println("</table>");
			//out.println("<br>");
			out.println("<table width='90%' border='0' cellspacing='0'> ");	
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println(" Name ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Address ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Address");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("NIC ");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr >");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
			out.println("Signature ");
			out.println("</td>");
			out.println("<td width='50%' height='20' class='rep-body' style='{text-align:left;}' valign='top'>	");
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
}  
