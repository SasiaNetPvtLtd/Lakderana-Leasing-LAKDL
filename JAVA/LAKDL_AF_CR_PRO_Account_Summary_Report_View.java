import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import oracle.jdbc.driver.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
       
public class LAKDL_AF_CR_PRO_Account_Summary_Report_View extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
 	 java.lang.Math a;

    
 // public ResultSet rs,rs_doc_charge;
//   public ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;
  ResultSet rs = null;

	public String m_chksql;
	public String m_company_name;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username=m_sn_methods.username.trim();
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
			
			stmt=conn.createStatement();


			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("main_page")){
			//out.println("sfsdsd");
			
			// String m_finance_no=req.getParameter("finance_no").trim();
			// String m_date=req.getParameter("date").trim
			
			// 1. Safe Parameter Retrieval (Prevents Null Pointer Exceptions)
    String raw_finance = req.getParameter("finance_no");
    String raw_date = req.getParameter("date");
    
    // If param is null, default to empty string to prevent crash
    String m_finance_no = (raw_finance != null) ? raw_finance.trim() : "";
    String m_date = (raw_date != null) ? raw_date.trim() : "";

    // 2. Validate Inputs before hitting DB
    if(m_finance_no.equals("") || m_date.equals("")) {
        out.println("<h3 style='color:red'>Error: Missing Finance No or Date. Please close and try again.</h3>");
        return; // Stop execution
    }

    // 3. EXECUTE PROCEDURE (The block that was failing)
    // We wrap this in its own Try/Catch so we can see specific procedure errors
    try {
        // Debug print to server console to confirm execution started
        System.out.println("DEBUG: Calling AF_CO_SAVE_ACC_STATEMENT_V2 with: " + m_finance_no + ", " + m_username + ", " + m_date);

        callstmt = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_SAVE_ACC_STATEMENT_V2(?,?,?); END;");
        callstmt.setString(1, m_finance_no);
        callstmt.setString(2, m_username);
        callstmt.setString(3, m_date);
        callstmt.execute();
        
        System.out.println("DEBUG: Procedure Finished Successfully");

    } catch (SQLException e) {
        // This prints the error to the popup window so you can see it immediately
        out.println("<h3 style='color:red'>Database Error: " + e.getMessage() + "</h3>");
        e.printStackTrace();
        return; // Stop generating the rest of the report
    } finally {
        if (callstmt != null) try { callstmt.close(); } catch (Exception ignore) {}
    }
			
	

			rs= stmt.executeQuery(" SELECT COMPANY_NAME FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			if(rs.next()){
				m_company_name=rs.getString(1);
			}
			rs.close();
		
		
			// rs=stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ");
			// rs.next();
			// String m_date=rs.getString(1);
			// rs.close();

			String m_application_no = "-";
			String m_client_code = "-";
			String m_client_name = "-";
			String m_reg_no = "-";
			String m_app_status = "-";

			String m_date_of_facility = "-";
			String m_termination_date = "-";
			String m_last_pay_date = "-";

			String m_facility_amount = "0.00";
			String m_period = "0";
			String m_net_installment = "0.00";
			String m_total_receivable = "0.00";
			String m_insu_renew_default = "0.00";
			String m_other_charges = "0.00";
			String m_over_due_interest = "0.00";
			String m_total_charges = "0.00";
			String m_total_charges_n_receivable = "0.00";
			String m_payment_without_insu = "0.00";
			String m_insurance = "0.00";
			String m_tot_insu = "0.00";
			String m_tot_charges_n_insu = "0.00";
			String m_balance_due = "0.00";

			     String sql_account_stmt = "SELECT " +
												" APPLICATION_NO, " +          // 1
												" CLIENT_CODE, " +             // 2
												" CLIENT_NAME, " +             // 3
												" REG_NO, " +                  // 4
												" APPLICATION_STATUS, " +      // 5
												" TO_CHAR(DATE_OF_FACILITY, 'DD/MM/YYYY'), " + // 6
												" TO_CHAR(TERMINATION_DATE, 'DD/MM/YYYY'), " + // 7
												" TO_CHAR(LAST_PAY_DATE, 'DD/MM/YYYY'), " +    // 8
												" NVL(FACILITY_AMOUNT, 0), " +        // 9
												" NVL(PERIOD, 0), " +                 // 10
												" NVL(NET_INSTALLMENT, 0), " +        // 11
												" NVL(TOTAL_RECEVABLE, 0), " +        // 12
												" NVL(INSU_RENEW_DEFAULT, 0), " +     // 13
												" NVL(OTH_CHARGES, 0), " +            // 14
												" NVL(OVER_DUE_INTEREST, 0), " +      // 15
												" NVL(TOTAL_CHARGES, 0), " +          // 16
												" NVL(TOT_CHARG_N_RECEV, 0), " + // 17
												" NVL(PAYMENT_WITHOUT_INSU, 0), " +   // 18
												" NVL(INSURANCE, 0), " +              // 19
												" NVL(TOT_INSU, 0), " +              // 20
												" NVL(TOT_CHARG_N_INSU, 0), " +     // 21
												" NVL(BALANCE_DUE, 0) " +             // 22
												" FROM " + m_schema_name + ".AF_CO_TBD_ACCOUNT_STATEMENT " +
												" WHERE FINANCE_NO = '" + m_finance_no + "'" +
												" AND ENT_USER = '" + m_username + "' ";



						rs = stmt.executeQuery(sql_account_stmt);

						if (rs.next()) {
							m_application_no = rs.getString(1);
							m_client_code    = rs.getString(2);
							m_client_name    = rs.getString(3);
							m_reg_no         = rs.getString(4);
							m_app_status     = rs.getString(5);
							
							m_date_of_facility = (rs.getString(6) != null) ? rs.getString(6) : "-";
							m_termination_date = (rs.getString(7) != null) ? rs.getString(7) : "-";
							m_last_pay_date    = (rs.getString(8) != null) ? rs.getString(8) : "-";

							m_facility_amount    = nf.format(rs.getDouble(9));
							m_period             = nf1.format(rs.getDouble(10));
							m_net_installment    = nf.format(rs.getDouble(11));
							m_total_receivable   = nf.format(rs.getDouble(12));
							m_insu_renew_default = nf.format(rs.getDouble(13));
							m_other_charges      = nf.format(rs.getDouble(14));
							m_over_due_interest  = nf.format(rs.getDouble(15));
							m_total_charges      = nf.format(rs.getDouble(16));
							m_total_charges_n_receivable = nf.format(rs.getDouble(17));
							m_payment_without_insu = nf.format(rs.getDouble(18));
							m_insurance          = nf.format(rs.getDouble(19));
							m_tot_insu           = nf.format(rs.getDouble(20));
							m_tot_charges_n_insu = nf.format(rs.getDouble(21));
							m_balance_due        = nf.format(rs.getDouble(22));
						}
						rs.close();
				             
					
					 out.println("<HTML><HEAD><TITLE>Account Statement Report</TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						
					 		out.println("<script>");
					        out.println("function export_excel(){");
							out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Account_Summary_Report_View?chksql=export_excel&date="+m_date+"&finance_no="+m_finance_no+"\";"); 
							out.println(" window.location.href=m_url;"); 
							// popupwin = window.open(m_url,'displayWindow1','left=0,top=130,width=790,height=390,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');
							// out.println("m_table.innerHTML=\"\" ");
							//out.println("window.print();");
							out.println("}");

							out.println("function print_report(){");
							out.println("m_table.innerHTML=\"\" ");
							out.println("	window.print();");
							// out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Account_Summary_Report?chksql=main_page&date="+m_date+"&finance_no="+m_finance_no+"\";"); 
							// out.println(" 	window.location.href=m_url;"); 
							out.println("} "); 

							out.println("</script>");
			
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >"); //onLoad=\"add_button()\"
					 out.println("<FORM NAME='Form1' method='post'>"); 
						
					 out.println("<BR><BR>");

						// out.println("<table align='center' width='100%' class='table' >");						
						// out.println("<tr >");
						// out.println("<td width='*%'></td>"); 
						// out.println("<td width='10%'>"); 
						// out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Print\"  Style=\"{width:110px;}\"  onClick=\"print_report()\">");
						// out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"Export\"  Style=\"{width:110px;}\"  onClick=\"export_excel()\"></td>");
						// out.println("</tr>"); 
						// out.println("<tr >");
						// out.println("</table>");

					out.println("<div id='m_table'>");
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr >");
					out.println("<td width='*%'></td>"); 
					out.println("<td width='10%'>"); 
					out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Print\"  Style=\"{width:110px;}\"  onClick=\"print_report()\">");
					out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"Export\"  Style=\"{width:110px;}\"  onClick=\"export_excel()\"></td>");
					out.println("</tr>"); 
					out.println("<tr >");
					out.println("</table>");
					out.println("</div>");
				
					//  out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					//  out.println("<TR><TD align='Center' ><B> Account Statement for Finance No - "+m_finance_no+"  </B></TD></TR>");
					//  out.println("</TABLE>");
					 out.println("<BR><BR>");

					 out.println("<TABLE  WIDTH='100%' STYLE='{ bgcolor='#ffffff' color: black; font: 40pt arial;}'>");
					 out.println("<TR>");
					 out.println("<TD align='Center' ><B>"+m_company_name+"</B></TD>");
					 out.println("</TR>");
					 out.println("<TR>");
					 out.println("<TD align='Center' ><B>STATMENT OF ACCOUNTS</B></TD>");
					 out.println("</TR>");
					 out.println("<TR>");
					 out.println("<TD align='Center' ><B>AS AT "+m_date+"</B></TD>");
					 out.println("</TR>");
					 out.println("</TABLE>");
					 out.println("<BR><BR>");
					 out.println("<BR><BR>");

					 
						
		
						rs=stmt.executeQuery(sql_account_stmt);
						boolean more=rs.next();
						if(more)
						{
						m_application_no=rs.getString(1);
						m_client_code=rs.getString(2);
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Facility No</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input>"+m_finance_no+"</DIV></td>"); // onClick=\"show_application_detail_drill('"+m_application_no+"')\" style='cursor:hand'
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Reg. No.</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input>"+m_reg_no+"</DIV></td>");// onClick=\"show_finance_detail_drill('"+m_finance_no+"')\" style='cursor:hand' 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Client Name</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='46%' align='left'><DIV class=div_input><b>"+m_client_name+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Date Of Facility</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input><b>"+m_date_of_facility+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Date of Termination</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input><b>"+m_termination_date+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Date of Last Payment</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input><b>"+m_last_pay_date+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");	
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Facility Amount</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_facility_amount+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Period</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input><b>"+m_period+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Net Installment</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_net_installment+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Total Recevable (Agreed Amount)</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_total_receivable+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						
						out.println("<br>");

						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Insurance (Renewal + Default)</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_insu_renew_default+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Other Charges</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_other_charges+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Over Due Interest</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right' ><DIV class=div_input style='text-align:right'><b>"+m_over_due_interest+"</DIV></td>");
						out.println("<td width='17.5%' align='right' ><DIV class=div_input style='text-align:right'><b>"+m_total_charges+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' ></td>");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_total_charges_n_receivable+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");

						out.println("<br>");

						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' ><b><u>Total Payment Made Before The Termination<b></u></td>");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='17.5%' align='left'>&nbsp;</td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");

						out.println("<br>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Payment without insurance</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_payment_without_insu+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Insurance</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='right' ><DIV class=div_input style='text-align:right'><b>"+m_insurance+"</DIV></td>");
						out.println("<td width='17.5%' align='right' ><DIV class=div_input style='text-align:right'><b>"+m_tot_insu+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' ></td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b>"+m_tot_charges_n_insu+"</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");

						out.println("<br>");
						
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' >Total Payment Made After The Termination</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='50%' ><b>Balance Due as at "+m_date+"</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%' align='left'><DIV class=div_input></DIV></td>");
						out.println("<td width='17.5%' align='right'><DIV class=div_input style='text-align:right'><b><u>"+m_balance_due+"</u></b></DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>");
						}
						rs.close();
					
		    
					  
						out.println("<br >");
						// out.println("<hr color='black' >");
						out.println("<br >");
						out.println("<table align='center' width='100%' class='table' >");
					    out.println("<tr >");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='10%'>Prepared By</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='20%'></td>"); 
						out.println("<td width='10%'>Checked By</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						 out.println("<tr >");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='10%'>Name</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='20%'></td>"); 
						out.println("<td width='10%'>Name</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						 out.println("<tr >");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='10%'>Designation</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='20%'></td>"); 
						out.println("<td width='10%'>Designation</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						 out.println("<tr >");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='10%'>Date</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='20%'></td>"); 
						out.println("<td width='10%'>Date</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='17.5%'><DIV class=div_input>...................................</DIV></td>");
						out.println("<td width='*%'></td>"); 
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br >");
						out.println("<br >");
						
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    	}
			else if(m_chksql.equals("export_excel")){
				String m_date = req.getParameter("date");
				String m_finance_no = req.getParameter("finance_no");
				///////////////////////////////////////////////
				
				rs= stmt.executeQuery(" SELECT COMPANY_NAME FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				if(rs.next()){
					m_company_name=rs.getString(1);
				}
				rs.close();


				String m_application_no = "-";
				String m_client_code = "-";
				String m_client_name = "-";
				String m_reg_no = "-";
				String m_app_status = "-";

				String m_date_of_facility = "-";
				String m_termination_date = "-";
				String m_last_pay_date = "-";

				Double m_facility_amount = 0.00;
				Double m_period = 0.00;
				Double m_net_installment = 0.00;
				Double m_total_receivable = 0.00;
				Double m_insu_renew_default = 0.00;
				Double m_other_charges = 0.00;
				Double m_over_due_interest = 0.00;
				Double m_total_charges = 0.00;
				Double m_total_charges_n_receivable = 0.00;
				Double m_payment_without_insu = 0.00;
				Double m_insurance = 0.00;
				Double m_tot_insu = 0.00;
				Double m_tot_charges_n_insu = 0.00;
				Double m_balance_due = 0.00;

			     String sql_account_stmt = "SELECT " +
												" APPLICATION_NO, " +          // 1
												" CLIENT_CODE, " +             // 2
												" CLIENT_NAME, " +             // 3
												" REG_NO, " +                  // 4
												" APPLICATION_STATUS, " +      // 5
												" TO_CHAR(DATE_OF_FACILITY, 'DD/MM/YYYY'), " + // 6
												" TO_CHAR(TERMINATION_DATE, 'DD/MM/YYYY'), " + // 7
												" TO_CHAR(LAST_PAY_DATE, 'DD/MM/YYYY'), " +    // 8
												" NVL(FACILITY_AMOUNT, 0), " +        // 9
												" NVL(PERIOD, 0), " +                 // 10
												" NVL(NET_INSTALLMENT, 0), " +        // 11
												" NVL(TOTAL_RECEVABLE, 0), " +        // 12
												" NVL(INSU_RENEW_DEFAULT, 0), " +     // 13
												" NVL(OTH_CHARGES, 0), " +            // 14
												" NVL(OVER_DUE_INTEREST, 0), " +      // 15
												" NVL(TOTAL_CHARGES, 0), " +          // 16
												" NVL(TOT_CHARG_N_RECEV, 0), " + // 17
												" NVL(PAYMENT_WITHOUT_INSU, 0), " +   // 18
												" NVL(INSURANCE, 0), " +              // 19
												" NVL(TOT_INSU, 0), " +              // 20
												" NVL(TOT_CHARG_N_INSU, 0), " +     // 21
												" NVL(BALANCE_DUE, 0) " +             // 22
												" FROM " + m_schema_name + ".AF_CO_TBD_ACCOUNT_STATEMENT " +
												" WHERE FINANCE_NO = '" + m_finance_no + "'" +
												" AND ENT_USER = '" + m_username + "' ";



						rs = stmt.executeQuery(sql_account_stmt);

						if (rs.next()) {
							m_application_no = rs.getString(1);
							m_client_code    = rs.getString(2);
							m_client_name    = rs.getString(3);
							m_reg_no         = rs.getString(4);
							m_app_status     = rs.getString(5);
							
							m_date_of_facility = (rs.getString(6) != null) ? rs.getString(6) : "-";
							m_termination_date = (rs.getString(7) != null) ? rs.getString(7) : "-";
							m_last_pay_date    = (rs.getString(8) != null) ? rs.getString(8) : "-";

							m_facility_amount    = rs.getDouble(9);
							m_period             = rs.getDouble(10);
							m_net_installment    = rs.getDouble(11);
							m_total_receivable   = rs.getDouble(12);
							m_insu_renew_default = rs.getDouble(13);
							m_other_charges      = rs.getDouble(14);
							m_over_due_interest  = rs.getDouble(15);
							m_total_charges      = rs.getDouble(16);
							m_total_charges_n_receivable = rs.getDouble(17);
							m_payment_without_insu = rs.getDouble(18);
							m_insurance          = rs.getDouble(19);
							m_tot_insu           = rs.getDouble(20);
							m_tot_charges_n_insu = rs.getDouble(21);
							m_balance_due        = rs.getDouble(22);
						}
						rs.close();
				             
				
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
				// res.reset(); 
				res.setContentType("text/csv;charset=UTF-8");
				res.setHeader("Content-disposition","attachment; filename=Account_Statement.csv" );

				out.print(","+m_company_name+",\n"); 
				out.print(",    STATMENT OF ACCOUNTS,\n"); //4 spaces
				out.print(",            AS AT "+m_date+"\n"); //14 spaces 
				out.print("\n");

				// out.print(",,,,Report Generated User:,"); 
				// out.print(""+m_username+"\n"); 
				
				// out.print(",,Report Generated Date:,"); 
				// out.print(""+sdf.format(date)+"\n"); 
				out.print("                                 ,\n"); //12	
				out.print("Facility No                      , :,"+m_finance_no+",\n"); //1		
				out.print("Reg. No.                         , :,"+m_reg_no+",\n"); //2	
				out.print("Client Name                      , :,"+m_client_name+",\n"); //3	
				out.print("Date Of Facility                 , :,"+m_date_of_facility+",\n"); //4	
				out.print("Date of Termination              , :,"+m_termination_date+",\n"); //5			
				out.print("Date of Last Payment             , :,"+m_last_pay_date+",\n"); //6	
				out.print("Facility Amount                  , :,,"+m_facility_amount+",\n"); //7	
				out.print("Period                           , :,"+m_period+",\n"); //8	
				out.print("Net Installment                  , :,"+m_net_installment+",\n"); //9	
				out.print("Total Recevable (Agreed Amount)  , :,,"+m_total_receivable+",\n\n"); //10	
				out.print("Insurance (Renewal + Default)    , :,"+m_insu_renew_default+",\n"); //11	
				out.print("Other Charges                    , :,"+m_other_charges+",\n"); //12	
				out.print("Over Due Interest                , :,"+m_over_due_interest+","+m_total_charges+",\n"); //13
				out.print("                                 ,,"+m_total_charges_n_receivable+",\n"); //12	
				out.print("\n");
				out.print("Total Payment Made Before The Termination,\n"); //12	
				out.print("\n");
				out.print("Payment without insurance        , :,"+m_payment_without_insu+",\n"); //5			
				out.print("Insurance                        , :,"+m_insurance+",\n"); //6	
				out.print("                                 ,,"+m_tot_insu+",\n"); //12	
				out.print("                                 ,,"+m_tot_charges_n_insu+",\n"); //12	
				out.print("Total Payment Made After The Termination,\n"); //5
				out.print("Balance Due as at "+m_date+", :,"+m_balance_due+",\n"); //6	
				out.print("\n\n\n");
				out.print("Prepared By, :,.......................,,Checked By, :,.......................,\n");
				out.print("Name, :,.......................,,Name, :,.......................,\n");
				out.print("Designation, :,.......................,,Designation, :,.......................,\n");
				out.print("Date, :,.......................,,Date, :,.......................,\n");
				out.print("\n\n");
				/////////////////////////////////////////
				
				
				/////////////////////////////////////////
			}else if(m_chksql.equals("export_excel_old")){
				String m_date = req.getParameter("date");
				String m_finance_no = req.getParameter("finance_no");
				///////////////////////////////////////////////
				
				rs= stmt.executeQuery(" SELECT COMPANY_NAME FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
				if(rs.next()){
					m_company_name=rs.getString(1);
				}
				rs.close();
				
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
				// res.reset(); 

				// Create Excel workbook
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Account Statement");
				
				// Define styles
				XSSFCellStyle centerStyle = workbook.createCellStyle();
				centerStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
				centerStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
				XSSFFont boldFont = workbook.createFont();
				boldFont.setBold(true);
				boldFont.setFontHeightInPoints((short) 12);
				centerStyle.setFont(boldFont);
				
				// Company name row
				XSSFRow row1 = sheet.createRow(0);
				XSSFCell cell1 = row1.createCell(4);
				cell1.setCellValue(m_company_name);
				cell1.setCellStyle(centerStyle);
				
				// Statement of Accounts row
				XSSFRow row2 = sheet.createRow(1);
				XSSFCell cell2 = row2.createCell(4);
				cell2.setCellValue("STATEMENT OF ACCOUNTS");
				cell2.setCellStyle(centerStyle);
				
				// AS AT date row
				XSSFRow row3 = sheet.createRow(2);
				XSSFCell cell3 = row3.createCell(4);
				cell3.setCellValue("AS AT " + m_date);
				cell3.setCellStyle(centerStyle);
				
				// Report Generated User
				XSSFRow row4 = sheet.createRow(3);
				XSSFCell cell4a = row4.createCell(4);
				cell4a.setCellValue("Report Generated User:");
				XSSFCell cell4b = row4.createCell(5);
				cell4b.setCellValue(m_username);
				
				// Report Generated Date
				XSSFRow row5 = sheet.createRow(4);
				XSSFCell cell5a = row5.createCell(2);
				cell5a.setCellValue("Report Generated Date:");
				XSSFCell cell5b = row5.createCell(3);
				cell5b.setCellValue(sdf.format(date));
				
				// Empty row
				XSSFRow row6 = sheet.createRow(5);
				
				// Header row for data
				XSSFRow headerRow = sheet.createRow(6);
				XSSFCellStyle headerStyle = workbook.createCellStyle();
				headerStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
				headerStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
				XSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerStyle.setFont(headerFont);
				
				String[] headers = {"No", "Finance Number", "Receipt Number", "Receipt Date", "Receipt Amount", "Status", "App. Receipt Amount"};
				for(int col = 0; col < headers.length; col++){
					XSSFCell headerCell = headerRow.createCell(col);
					headerCell.setCellValue(headers[col]);
					headerCell.setCellStyle(headerStyle);
				}
				
				// Data rows
				rs= stmt.executeQuery(
					" SELECT "+
					" FINANCE_NUMBER , "+
					" RECEIPT_NUMBER, "+
					" RECEIPT_DATE, "+
					" RECEIPT_AMOUNT, "+
					" STATUS, "+
					" APP_RECEIPT_AMOUNT "+
					" FROM "+
					" "+m_schema_name+".AF_MISF_FACILITY_WISE_ALLO_RPT "+
					" WHERE ENT_USER='"+m_username+"'");
				
				int rowNum = 7;
				int i = 1;
				
				XSSFCellStyle dataStyle = workbook.createCellStyle();
				dataStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
				
				while(rs.next()){
					XSSFRow dataRow = sheet.createRow(rowNum);
					
					XSSFCell cell = dataRow.createCell(0);
					cell.setCellValue(i);
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(1);
					cell.setCellValue(rs.getString("FINANCE_NUMBER"));
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(2);
					cell.setCellValue(rs.getString("RECEIPT_NUMBER"));
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(3);
					cell.setCellValue(sdf.format(rs.getDate("RECEIPT_DATE")));
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(4);
					cell.setCellValue(rs.getString("RECEIPT_AMOUNT"));
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(5);
					cell.setCellValue(rs.getString("STATUS"));
					cell.setCellStyle(dataStyle);
					
					cell = dataRow.createCell(6);
					cell.setCellValue(rs.getString("APP_RECEIPT_AMOUNT"));
					cell.setCellStyle(dataStyle);
					
					rowNum++;
					i++;
				}
				
				// Set column widths
				for(int col = 0; col < headers.length; col++){
					sheet.autoSizeColumn(col);
				}
				
				// Write workbook to response
				res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				res.setHeader("Content-disposition", "attachment; filename=Account_Statement.xlsx");
				
				workbook.write(res.getOutputStream());
				// workbook.close();
				
				
				/////////////////////////////////////////
			}
			else {
			    out.println("Undefined");
			}
		}
		catch (Exception e) {
			try {
				ByteArrayOutputStream ostr = new ByteArrayOutputStream();
				e.printStackTrace(new PrintWriter(ostr));
				ServletOutputStream out = res.getOutputStream();
				out.println(ostr.toString());
				out.close();
			}catch (Exception eti) {}
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {}
			this.destroy();
		}
	}
}


