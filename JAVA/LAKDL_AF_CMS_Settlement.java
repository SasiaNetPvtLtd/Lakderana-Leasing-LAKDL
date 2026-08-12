
//This File was created by DSP Chathuranga 2013-04-24 

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
//import FCAM_sn_methods;
import java.util.*;
import java.math.BigDecimal;
import oracle.jdbc.driver.*;

public class LAKDL_AF_CMS_Settlement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt12,stmt_act;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2,rs12,rs_act;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			
			LAKDL_AF_CO_FU_methods CO_methods = new LAKDL_AF_CO_FU_methods();		
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			String header_name    = con_method.header_name;
			String m_rep_cur=""; //added by nuwan de silva23-07-07
			
			String m_fschema_name=con_method.client_name.trim();
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			out = res.getOutputStream();
			//out.println("conn="+conn);
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@147.120.40.1:1521:DN10G", "system", "sys123");
			CallableStatement callstmt1 =null;
			
			
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
			nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			stmt2= conn.createStatement ();
			stmt12= conn.createStatement ();
			stmt_act= conn.createStatement ();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("get_inv_details")){
				
				String m_client    = req.getParameter("Client_Code");
				double m_invoice_amt =0.00;
				double m_rental_amt  =0.00;
				double m_rental_amt_future  =0.00;
				double m_odi_amt     =0.00;
				double m_other_amt   =0.00;
				double m_other_amt_future   =0.00;
				double m_total_amt   =0.00;
				double m_total_amt_future =0.00;
				double m_odi_amt_future=0.00;
				String m_sys_date="";
				
				double m_excess_receipt_amount =0.00; /*Added By ns on 26-06-2012*/
				double m_excess_receipt_amount_ins =0.00; /*Added By ns on 26-06-2012*/
				double mm_insurance_arrears=0.00;
				double mm_insurance_future=0.00;
				double m_amount_after_excess = 0.00; //Added by Kanishka Dilshan On 08-05-2014
				double m_total_amt_ins   =0.00;
				double m_total_amt_future_ins =0.00;
				
                
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO ");
				
				if(rs1.next()){
					m_invoice_amt = rs1.getDouble(1);			
					m_sys_date = rs1.getString(2);			
				}
				
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt_future = rs1.getDouble(1);			
				}
				
				
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date >SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt_future = rs1.getDouble(1);			
				}
				
				
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt_future = rs1.getDouble(1);			
				}
				
				//added by sh on 23-11-2010 
				double m_new_odi=0;
				double m_old_odi=0;
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_new_odi = rs1.getDouble(1);
					m_old_odi = m_odi_amt-m_new_odi;
					if (m_old_odi<0){
						m_old_odi=0;
					}	
				}
				
				
				
				/*Added By Ns on 26-06-2012*/
				rs1 = stmt1.executeQuery (
					" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					" AND    A.CLIENT_CODE='"+m_client+"' "+
					//" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND   NVL ( A.RENTAL_OTER_INVOICE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount = rs1.getDouble(1);			
				}
				rs1.close();
				
				out.println("<br>");
				out.println("<fieldset>");
				out.println("<legend style=\"margin-bottom: 5px;\" ><strong>Outstanding Details:</strong></legend>");
				out.println("<div class=\"client_contract\" >");//outstanding
				out.println("<table WIDTH=100%  >");  //table_cms_th
				//	out.println("  <thead>");
				out.println("  <tr class='' >");
				out.println("  <td WIDTH=25% ><b>&nbsp;</td>");
				out.println("  <th WIDTH=30%  align=right><b>Arr as at - "+m_sys_date+"</th>");
				out.println("  <th WIDTH=20%  align=right><b>Next Due</th>");
				out.println("  <th WIDTH=20%  align=right><b>Total</th>");
				out.println("  </tr>");
				//	out.println("  </thead> ");
				//	out.println("  <tbody> ");
				out.println("  <tr  >");
				out.println("  <th WIDTH=25%  ><b>Rental Invoice </th>"); //FFFF00
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_rental_amt)+"</td>"); //CC66FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt_future)+"</td>"); //CC99FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt+m_rental_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr  >");
				out.println("  <th WIDTH=25% ><b>OD Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_odi_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt+m_odi_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr  >");
				out.println("  <th WIDTH=25%  ><b>Other Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_other_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt+m_other_amt_future)+"</td>");
				out.println("  </tr>");
				
					
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
				out.println("  </tr>");	
				
				m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt)- m_excess_receipt_amount;;
				m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future);
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>&nbsp;</th>");
				out.println("  <td WIDTH=30%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Insurance Invoice</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(mm_insurance_arrears)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_arrears+mm_insurance_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts Ins (-) </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_excess_receipt_amount_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				m_total_amt_ins        = (mm_insurance_arrears) - m_excess_receipt_amount_ins; //mm_insurance_arrears
				m_total_amt_future_ins = (mm_insurance_future); //mm_insurance_future
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding Ins</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_ins+m_total_amt_future_ins)+"</td>");
				out.println("  </tr>");
				
				
				
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>New ODI</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_new_odi)+"</td>");
				out.println("  <th WIDTH=20%  ><b>OLD ODI</th>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_old_odi)+"</td>");
				out.println("  </tr>");
				out.println("</table>");
				out.println("  </div> ");
				out.println("</fieldset>");
				
			}	
			else if(m_chksql.trim().equals("get_inv_details_fin_wise")){ //INESH 2017-07-20 FOR  Cms- Receipt - Entry 
				
				String m_client    = req.getParameter("Client_Code");
				String m_finance_no    = req.getParameter("fin_no");
				
				double m_invoice_amt =0.00;
				double m_rental_amt  =0.00;
				double m_rental_amt_future  =0.00;
				double m_odi_amt     =0.00;
				double m_other_amt   =0.00;
				double m_other_amt_future   =0.00;
				double m_total_amt   =0.00;
				double m_total_amt_ins   =0.00;
				double m_total_amt_future =0.00;
				double m_total_amt_future_ins =0.00;
				double m_odi_amt_future=0.00;
				double m_excess_receipt_amount =0.00; /*Added By ns on 26-06-2012*/
				double m_excess_receipt_amount_ins =0.00; /*Added By ns on 26-06-2012*/
				double m_excess_receipt_amount_allo =0.00; 
				double m_excess_receipt_amount_ins_allo =0.00; 
				String m_sys_date="";
				String mm_application_no="";
				String mm_RENTAL_DATE="";
				double mm_insurance_arrears=0.00;
				double mm_insurance_future=0.00;
				double m_amount_after_excess = 0.00;
				
                
				rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					//" A.FINANCE_NO = B.FINANCE_NO ");
					" WHERE A.FINANCE_NO ='"+m_finance_no+"'  "+
					" AND A.FINANCE_NO = B.FINANCE_NO ");
				
				if(rs1.next()){
					m_invoice_amt = rs1.getDouble(1);			
					m_sys_date = rs1.getString(2);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  APPLICATION_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"'  "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"'  "+
					" AND   A.APPLICATION_STATUS = 'ACTIVATED' ");
				
				if(rs1.next()){
					mm_application_no = rs1.getString("APPLICATION_NO");			
				}
				
				rs1=stmt1.executeQuery( " "+
					" SELECT TO_CHAR(MAX(A.RENTAL_DATE),'DD') DUE_DATE "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					//" WHERE A.APPLICATION_NO = '"+mm_application_no+"' "+
					" WHERE A.APPLICATION_NO = "+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"') "+	
					" ");
				
				if(rs1.next()){
					mm_RENTAL_DATE            = rs1.getString("DUE_DATE");
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt_future = rs1.getDouble(1);			
				}
				 
				rs1 = stmt1.executeQuery (
					
					" SELECT  SUM(ODI_BAL_AMOUNT) FROM ("+
					" SELECT  SUM(ODI_BAL_AMOUNT)  ODI_BAL_AMOUNT"+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					//" C.odi_date <=SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO  "+
					/*" UNION ALL "+		 
					" SELECT SUM(B.ODI_BAL_AMOUNT) ODI_BAL_AMOUNT "+
						" FROM  "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					"       "+m_schema_name+".AF_CO_PRO_OD_INTEREST_DAILY B "+
						" WHERE A.FINANCE_NO = B.INVOICE_NO  "+
					" AND   A.CLIENT_CODE ='"+m_client+"'  "+
						" AND   TRUNC(ODI_DATE,'MM') = TRUNC(SYSDATE,'MM') "+
					*/
					
					")  "+
					
					"");
				
				if(rs1.next()){
					m_odi_amt = rs1.getDouble(1);			
				}
				
				m_odi_amt_future=0;
				
				rs1 = stmt1.executeQuery (
					//" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
					" SELECT SUM(A.RENTAL_OTER_INVOICE) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					//" AND    A.CLIENT_CODE='"+m_client+"' "+
					" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND   NVL ( A.RENTAL_OTER_INVOICE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount = rs1.getDouble(1);			
				}
				rs1.close();
				// added on 2017-08-24 inesh req by NS
				rs1 = stmt1.executeQuery (
					
					//" SELECT SUM("+m_schema_name+".AF_CO_GET_REC_RENTAL_ALLO(A.REC_NO,B.FINANCE_NO)) "+ //2017-10-11
					" SELECT SUM( ("+m_schema_name+".AF_CO_GET_REC_RENTAL_ALLO(A.REC_NO,B.FINANCE_NO)) + ("+m_schema_name+".AF_CO_GET_REC_ODI_ALLO(A.REC_NO,B.FINANCE_NO)) ) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					//" AND    A.CLIENT_CODE='"+m_client+"' "+
					" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND   NVL ( A.RENTAL_OTER_INVOICE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount_allo = rs1.getDouble(1);			
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery (
					//" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
					" SELECT SUM(A.INSURANCE) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					//" AND    A.CLIENT_CODE='"+m_client+"' "+
					" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND    NVL ( A.INSURANCE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount_ins = rs1.getDouble(1);			
				}
				rs1.close();
				
				//added on 2017-08-24 Inesh req by NS
				rs1 = stmt1.executeQuery (
					
					" SELECT SUM("+m_schema_name+".AF_CO_GET_REC_INS_ALLO(A.REC_NO,B.FINANCE_NO)) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					//" AND    A.CLIENT_CODE='"+m_client+"' "+
					" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND    NVL ( A.INSURANCE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount_ins_allo = rs1.getDouble(1);			
				}
				rs1.close();
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INSURANC' AND "+ // added by udara 04-08-20174
					" B.INVOICE_TYPE <> 'INSREF' AND "+ // added by udara 11-06-2018
					" B.INVOICE_TYPE <> 'INV_GENER' AND "+
					" B.INVOICE_TYPE <> 'INSURANCE' AND "+
					" B.REMARKS <> 'CHARGES - INSURANCE' "+
					"");
				
				if(rs1.next()){
					m_other_amt = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" ( "+
					" B.INVOICE_TYPE = 'INSURANC' OR "+ // added by udara 04-08-2014
					" B.INVOICE_TYPE = 'INSREF' OR "+ // added by udara 11-06-2018
					" B.INVOICE_TYPE = 'INSURANCE' "+
					" OR B.REMARKS = 'CHARGES - INSURANCE' "+
					" ) "+
					"");
				
				if(rs1.next()){
					mm_insurance_arrears = rs1.getDouble(1);			
				}
				
				// commented by udara 04-11-2020
				/*
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INSURANC' AND "+ // added by udara 04-08-2014
					" B.INVOICE_TYPE <> 'INSREF' AND "+ // added by udara 11-06-2018
					" B.INVOICE_TYPE <> 'INV_GENER' AND "+
					" B.INVOICE_TYPE <> 'INSURANCE' AND "+
					" B.REMARKS <> 'CHARGES - INSURANCE' "+
					"");
				*/
				
				// added by udara 04-11-2020
				/*
				rs1 = stmt1.executeQuery ("  "+
					
					 " SELECT SUM(BALANCE_TO_BE_RECEIVED) "+
					 " FROM ( "+
					 		" SELECT  INVOICE_NO, BALANCE_TO_BE_RECEIVED "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
							" "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
							" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
							" A.FINANCE_NO = B.FINANCE_NO AND "+
							" B.VALUE_DATE >SYSDATE  AND "+
							" B.ACTIVE_STATUS='Y' AND "+
					        " B.INVOICE_TYPE <> 'MORATO-INT' AND "+
							" B.INVOICE_TYPE <> 'INSURANC' AND "+
							" B.INVOICE_TYPE <> 'INSREF' AND "+
							" B.INVOICE_TYPE <> 'INV_GENER' AND  "+
							" B.INVOICE_TYPE <> 'INSURANCE' AND "+
							" B.REMARKS <> 'CHARGES - INSURANCE' "+
					           
					        " UNION "+
					           
					        " SELECT  INVOICE_NO, BALANCE_TO_BE_RECEIVED "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
							" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
							" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
							" A.FINANCE_NO = B.FINANCE_NO AND "+
							" B.VALUE_DATE > TRUNC(SYSDATE)  AND "+
					        " B.VALUE_DATE <= LAST_DAY(TRUNC(SYSDATE))  AND "+
							" B.ACTIVE_STATUS='Y' AND "+
					        " B.INVOICE_TYPE = 'MORATO-INT' "+
					" ) "+

					"");
				*/
				
				
				rs1 = stmt1.executeQuery ("  "+
					
					 " SELECT SUM(BALANCE_TO_BE_RECEIVED) "+
					 " FROM ( "+
					 		" SELECT  SUM(BALANCE_TO_BE_RECEIVED) BALANCE_TO_BE_RECEIVED "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
							" "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
							" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
							" A.FINANCE_NO = B.FINANCE_NO AND "+
							" B.VALUE_DATE >SYSDATE  AND "+
							" B.ACTIVE_STATUS='Y' AND "+
					        " B.INVOICE_TYPE <> 'MORATO-INT' AND "+
							" B.INVOICE_TYPE <> 'INSURANC' AND "+
							" B.INVOICE_TYPE <> 'INSREF' AND "+
							" B.INVOICE_TYPE <> 'INV_GENER' AND  "+
							" B.INVOICE_TYPE <> 'INSURANCE' AND "+
							" B.REMARKS <> 'CHARGES - INSURANCE' "+
					           
					        " UNION "+
					           
					        " SELECT  SUM(BALANCE_TO_BE_RECEIVED) BALANCE_TO_BE_RECEIVED "+
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,  "+
							" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
							" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
							" A.FINANCE_NO = B.FINANCE_NO AND "+
							" B.VALUE_DATE > SYSDATE  AND "+
					        " B.VALUE_DATE <= LAST_DAY(SYSDATE)  AND "+
							" B.ACTIVE_STATUS='Y' AND "+
					        " B.INVOICE_TYPE = 'MORATO-INT' "+
					" ) "+

					"");
				
				    // end by udara 04-11-2020
				
				if(rs1.next()){
					m_other_amt_future = rs1.getDouble(1);			
				}
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE > SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" ( "+
					" B.INVOICE_TYPE = 'INSURANC' OR "+ // added by udara 04-08-2014
					" B.INVOICE_TYPE = 'INSREF' OR "+ // added by udara 11-06-2018
					" B.INVOICE_TYPE = 'INSURANCE' "+
					" OR B.REMARKS = 'CHARGES - INSURANCE' ) "+
					"");
				
				if(rs1.next()){
					mm_insurance_future = rs1.getDouble(1);			
				}
				
				double m_new_odi=0;
				double m_old_odi=0;
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					//" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" WHERE A.FINANCE_NO ='"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_new_odi = rs1.getDouble(1);
					m_old_odi = m_odi_amt-m_new_odi;
					if (m_old_odi<0){
						m_old_odi=0;
					}	
				}
				
				out.println("<br>");
				out.println("<fieldset>");
				out.println("<legend class='pdn_txtpos' ><strong>Outstanding Details of "+m_finance_no+":</strong></legend>");
				out.println("<div class=\"client_contract\" >");//outstanding
				out.println("<table WIDTH=100%  >");  //table_cms_th
				//	out.println("  <thead>");
				out.println("  <tr class='' >");
				out.println("  <td WIDTH=25% ><b>&nbsp;</td>");
				out.println("  <th WIDTH=30%  align=right><b>Arr as at - "+m_sys_date+"</th>");
				out.println("  <th WIDTH=20%  align=right><b>Next Due</th>");
				out.println("  <th WIDTH=20%  align=right><b>Total</th>");
				out.println("  </tr>");
				//	out.println("  </thead> ");
				//	out.println("  <tbody> ");
				out.println("  <tr  class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Rental Invoice </th>"); //FFFF00
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_rental_amt)+"</td>"); //CC66FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt_future)+"</td>"); //CC99FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt+m_rental_amt_future)+"</td>");
				out.println("  </tr>");
				 
				out.println("  <tr  class='alt' >");
				out.println("  <th WIDTH=25% ><b>OD Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_odi_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt+m_odi_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt'  >");
				out.println("  <th WIDTH=25%  ><b>Other Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_other_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt+m_other_amt_future)+"</td>");
				out.println("  </tr>");
				
					
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_excess_receipt_amount-m_excess_receipt_amount_allo)+"</td>");//-m_excess_receipt_amount_allo
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");	
				
				m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt) - (m_excess_receipt_amount-m_excess_receipt_amount_allo); //mm_insurance_arrears // -m_excess_receipt_amount_allo
				m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future); //mm_insurance_future
				
				m_amount_after_excess = m_total_amt;
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>&nbsp;</th>");
				out.println("  <td WIDTH=30%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Insurance Invoice</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(mm_insurance_arrears)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_arrears+mm_insurance_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts Ins (-) </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_excess_receipt_amount_ins-m_excess_receipt_amount_ins_allo)+"</td>");//-m_excess_receipt_amount_ins_allo
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				m_total_amt_ins        = (mm_insurance_arrears) - (m_excess_receipt_amount_ins-m_excess_receipt_amount_ins_allo); //mm_insurance_arrears  //-m_excess_receipt_amount_ins_allo
				m_total_amt_future_ins = (mm_insurance_future); //mm_insurance_future
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding Ins</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_ins+m_total_amt_future_ins)+"</td>");
				out.println("  </tr>");
				 
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>&nbsp;</th>");
				out.println("  <td WIDTH=30%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>New ODI</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_old_odi)+"</td>");
				out.println("  <th WIDTH=20%  ><b>OLD ODI</th>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_new_odi)+"</td>");
				out.println("  </tr>");
				
				out.println("</table>");
				out.println("  </div> ");
				out.println("</fieldset>");
				
			}
			else if(m_chksql.trim().equals("get_inv_details_fin_wise_old")){ //INESH 2017-07-20 FOR  Cms- Receipt - Entry 
				
				String m_client    = req.getParameter("Client_Code");
				String m_finance_no    = req.getParameter("fin_no");
				double m_invoice_amt =0.00;
				double m_rental_amt  =0.00;
				double m_rental_amt_future  =0.00;
				double m_odi_amt     =0.00;
				double m_other_amt   =0.00;
				double m_other_amt_future   =0.00;
				double m_total_amt   =0.00;
				double m_total_amt_future =0.00;
				double m_odi_amt_future=0.00;
				String m_sys_date="";
				
				double m_excess_receipt_amount =0.00; /*Added By ns on 26-06-2012*/
				double m_excess_receipt_amount_ins =0.00; /*Added By ns on 26-06-2012*/
				double mm_insurance_arrears=0.00;
				double mm_insurance_future=0.00;
				double m_amount_after_excess = 0.00; //Added by Kanishka Dilshan On 08-05-2014
				double m_total_amt_ins   =0.00;
				double m_total_amt_future_ins =0.00;
				
                
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO ");
				*/
				rs1 = stmt1.executeQuery (" SELECT  SUM(TOTAL_AMOUNT),TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO "+
					" ");
				if(rs1.next()){
					m_invoice_amt = rs1.getDouble(1);			
					m_sys_date = rs1.getString(2);			
				}
				
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' "); */
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				

				if(rs1.next()){
					m_rental_amt = rs1.getDouble(1);			
				}
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");   */
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO =  '"+m_finance_no+"' AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE = 'INV_GENER' ");
				
				if(rs1.next()){
					m_rental_amt_future = rs1.getDouble(1);			
				}
				
				
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO "); */
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" C.odi_date <=SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt = rs1.getDouble(1);			
				}
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date >SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO "); */
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" C.odi_date >SYSDATE AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				
				if(rs1.next()){
					m_odi_amt_future = rs1.getDouble(1);			
				}
				
				
				/*
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' "); */
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" B.VALUE_DATE <=SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt = rs1.getDouble(1);			
				}
				/*
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+ 
					" B.INVOICE_TYPE <> 'INV_GENER' ");  */
				
				rs1 = stmt1.executeQuery ("  SELECT  SUM(BALANCE_TO_BE_RECEIVED) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" B.VALUE_DATE >SYSDATE  AND "+
					" B.ACTIVE_STATUS='Y' AND "+ 
					" B.INVOICE_TYPE <> 'INV_GENER' ");
				
				if(rs1.next()){
					m_other_amt_future = rs1.getDouble(1);			
				}
				
				//added by sh on 23-11-2010 
				double m_new_odi=0;
				double m_old_odi=0;
				/*
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" C.odi_date <=SYSDATE AND "+
					"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO "); */
				
				rs1 = stmt1.executeQuery (" SELECT  SUM(ODI_BAL_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
					" "+m_schema_name+".AF_CO_PRO_INVOICE B, "+
					" "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
					" WHERE A.CLIENT_CODE ='"+m_client+"' AND "+
					" A.FINANCE_NO = B.FINANCE_NO AND "+
					" A.FINANCE_NO = '"+m_finance_no+"' AND "+
					" C.odi_date <=SYSDATE AND "+
					"C.ODI_DATE>=(SELECT ODI_ALLO_DATE FROM   "+m_schema_name+".AF_CO_MAS_ODI_DATE) AND "+
					" B.ACTIVE_STATUS='Y' AND "+
					" B.INVOICE_NO = C.INVOICE_NO ");
				if(rs1.next()){
					m_new_odi = rs1.getDouble(1);
					m_old_odi = m_odi_amt-m_new_odi;
					if (m_old_odi<0){
						m_old_odi=0;
					}	
				}
				
				
				
				/*Added By Ns on 26-06-2012*/
				rs1 = stmt1.executeQuery (
					" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
					" FROM   "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
					" WHERE  A.REC_NO=B.REC_NO   "+
					" AND    A.CLIENT_CODE=B.CLIENT_CODE   "+
					//" AND    A.CLIENT_CODE='"+m_client+"' "+
					" AND    B.FINANCE_NO = '"+m_finance_no+"' "+
					" AND    B.BAL_TOBE_RECEIVE<>0 "+
					" AND   NVL ( A.RENTAL_OTER_INVOICE,0 ) > 0 "+
					" AND    STATUS NOT IN ('CAD','RET','CANCLE','C') "+
					"");
				if(rs1.next()){
					m_excess_receipt_amount = rs1.getDouble(1);			
				}
				rs1.close();
				
				out.println("<br>");
				out.println("<fieldset>");
				out.println("<legend class='pdn_txtpos' ><strong>Outstanding Details of "+m_finance_no+":</strong></legend>");
				out.println("<div class=\"client_contract\" >");//outstanding
				out.println("<table WIDTH=100%  >");  //table_cms_th
				//	out.println("  <thead>");
				out.println("  <tr class='' >");
				out.println("  <td WIDTH=25% ><b>&nbsp;</td>");
				out.println("  <th WIDTH=30%  align=right><b>Arr as at - "+m_sys_date+"</th>");
				out.println("  <th WIDTH=20%  align=right><b>Next Due</th>");
				out.println("  <th WIDTH=20%  align=right><b>Total</th>");
				out.println("  </tr>");
				//	out.println("  </thead> ");
				//	out.println("  <tbody> ");
				out.println("  <tr  class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Rental Invoice </th>"); //FFFF00
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_rental_amt)+"</td>"); //CC66FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt_future)+"</td>"); //CC99FF
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_rental_amt+m_rental_amt_future)+"</td>");
				out.println("  </tr>");
				 
				out.println("  <tr  class='alt' >");
				out.println("  <th WIDTH=25% ><b>OD Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_odi_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_odi_amt+m_odi_amt_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt'  >");
				out.println("  <th WIDTH=25%  ><b>Other Invoice </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_other_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_other_amt+m_other_amt_future)+"</td>");
				out.println("  </tr>");
				
					
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts</th>");
				out.println("  <td WIDTH=30%  align=right><b>m_excess_receipt_amount</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");	
				
				m_total_amt = (m_rental_amt+m_odi_amt+m_other_amt)- m_excess_receipt_amount;;
				m_total_amt_future = (m_rental_amt_future+m_odi_amt_future+m_other_amt_future);
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt+m_total_amt_future)+"</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>&nbsp;</th>");
				out.println("  <td WIDTH=30%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Insurance Invoice</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(mm_insurance_arrears)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_future)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(mm_insurance_arrears+mm_insurance_future)+"</td>");
				out.println("  </tr>");
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Excess Receipts Ins (-) </th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_excess_receipt_amount_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  <td WIDTH=20%  align=right><b>&nbsp;</td>");
				out.println("  </tr>");
				
				m_total_amt_ins        = (mm_insurance_arrears) - m_excess_receipt_amount_ins; //mm_insurance_arrears
				m_total_amt_future_ins = (mm_insurance_future); //mm_insurance_future
				
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>Total Outstanding Ins</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_total_amt_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_future_ins)+"</td>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_total_amt_ins+m_total_amt_future_ins)+"</td>");
				out.println("  </tr>");
				
				
				
				
				/*
				out.println("  <tr class='alt' >");
				out.println("  <th WIDTH=25%  ><b>New ODI</th>");
				out.println("  <td WIDTH=30%  align=right><b>"+nf.format(m_new_odi)+"</td>");
				out.println("  <th WIDTH=20%  ><b>OLD ODI</th>");
				out.println("  <td WIDTH=20%  align=right><b>"+nf.format(m_old_odi)+"</td>");
				out.println("  </tr>");
				*/
				out.println("</table>");
				out.println("  </div> ");
				out.println("</fieldset>");
				
			}
			else if(m_chksql.trim().equals("get_contract_details_client")){
				
				String m_client    = req.getParameter("Client_Code");
				
				String m_sql =""+
							" SELECT FINANCE_NO, "+
							//" NVL("+m_schema_name+".AF_CO_GET_RENTAL_DATE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)),'-') RENTAL_DATE, "+
							//" NVL("+m_schema_name+".AF_CO_GET_RENTAL_DATE2(A.APPLICATION_NO),'-') RENTAL_DATE, "+ // commented by udara 06-09-2019
							" NVL(DECODE(TER_TYPE,'BAL_TRANSF',"+m_schema_name+".AF_CO_GET_RENTAL_DATE3(A.APPLICATION_NO),"+m_schema_name+".AF_CO_GET_RENTAL_DATE2(A.APPLICATION_NO)),'-') RENTAL_DATE, "+
							" NVL("+m_schema_name+".AF_CO_GET_INSTALMENT_AMT(A.APPLICATION_NO),0) RENTAL_AMT, "+
							" NVL("+m_schema_name+".AF_CO_GET_TOT_NO_RENTALS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) TOTAL_NO_RENTAL, "+
							" DECODE(NVL("+m_schema_name+".AF_GET_PERFORM_STATUS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),'PERFORM'),'PERFORM','Perform','NPERFORM','Non Perform',NVL("+m_schema_name+".AF_GET_PERFORM_STATUS(FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),'PERFORM')) PERFORM_STATUS, "+
							" NVL("+m_schema_name+".AF_CO_GET_ALL_REG_NUMBERS(FINANCE_NO),'-') REGISTRATION_NO,  "+
							" DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(A.APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(A.APPLICATION_NO)) APPLICATION_STATUS, "+
							" NVL( (SELECT TO_CHAR(MAX(DUE_DATE),'DD-MM-YYYY') FROM "+m_schema_name+".AF_CO_PRO_INVOICE  WHERE FINANCE_NO = A.FINANCE_NO AND ( INVOICE_TYPE = 'INSURANCE' OR REMARKS = 'CHARGES - INSURANCE')  AND BALANCE_TO_BE_RECEIVED > 0),'_') INS_DUE_DATE, "+
							" "+m_schema_name+".AF_CO_REM_LETTER_STATUS(A.FINANCE_NO) LETTER_STATUS, "+ // added by udara 22-04-2019
							" DECODE("+m_schema_name+".AF_CO_GET_MORA_FLAG_STATUS(A.FINANCE_NO),'Yes','Moratorium',' ') MORATORIUM "+ // added by udara 12-08-2020
							" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS  A "+
							" WHERE  CLIENT_CODE= '"+m_client+"' "+
							" AND A.FINANCE_NO IS NOT NULL "+
							" AND A.APPLICATION_STATUS NOT IN ('CANCEL') ";
							//"			 AND APPLICATION_STATUS NOT IN('NORM_TERMI','TERMI','WRITE_OFF','LG_SETTLED','RP_SOLD','RP_SETTLED', "+
							//" 			 'TERMINATED','CANCEL','REJECT','CANCEL_PO')  ";

				
				out.println("<br>");
				out.println("<fieldset>");
				out.println("<legend class ='pdn_txtpos' ><strong>Contract Details:</strong></legend>");
				out.println("<div class=\"client_contract\">");
				out.println("<table width='100%' >");  				
				out.println("  <tr >");				
				out.println("  <th align=center ><b>Finance No</b></td>");
				out.println("  <th align=center ><b>Due&nbsp;date</b></th>");//Rental Due Date
				out.println("  <th align=center ><b>Rental</b></th>");//Rental Amount
				out.println("  <th align=center ><b>Period</b></th>");//No.Of.Rentals
				out.println("  <th align=center ><b>Insurance Due Date</b></th>");
				out.println("  <th align=center ><b>Status</b></th>");//Contract Status
				out.println("  <th align=center ><b>Trn.History</b></th>");
				out.println("  <th align=center ><b>Details</b></th>");
				out.println("  </tr>");
				
				rs1 = stmt1.executeQuery (m_sql);
				int no_of_rec =0;
				while(rs1.next()){
					out.println("  <tr class='alt' >");
					out.println("  <td >"+rs1.getString("FINANCE_NO")+"</td>");//Finance No
					out.println("  <td align=center>"+rs1.getString("RENTAL_DATE")+"</td>");//Rental Due Date 
					out.println("  <td align=right>"+nf.format(rs1.getDouble("RENTAL_AMT"))+"</td>");//Rental Amount
					out.println("  <td align=center>"+rs1.getString("TOTAL_NO_RENTAL")+"</td>");//No.Of.Rentals
					out.println("  <td align=center>"+rs1.getString("INS_DUE_DATE")+"</td>");//Insurance Due Date
					//out.println("  <td >"+rs1.getString("APPLICATION_STATUS")+" - "+rs1.getString("PERFORM_STATUS")+"</td>");//Status // commented by udara 22-04-2019
					//out.println("  <td >"+rs1.getString("APPLICATION_STATUS")+" - "+rs1.getString("PERFORM_STATUS")+" - "+ rs1.getString("LETTER_STATUS")+"</td>");//Status // added by udara 22-04-2019 // commented by udara 12-08-2020
					out.println("  <td >"+rs1.getString("APPLICATION_STATUS")+" - "+rs1.getString("PERFORM_STATUS")+" - "+ rs1.getString("LETTER_STATUS")+" - "+ rs1.getString("MORATORIUM") +" </td>"); // added by udara 12-08-2020
					out.println("  <td style= cursor:hand; onclick=\"show_transaction_info('"+m_client+"','"+rs1.getString("FINANCE_NO")+"');\" ><u>Trn. History</u></td>");//Trn.History 
					out.println("  <td style= cursor:hand; onclick=\"view_finance_det('"+rs1.getString("FINANCE_NO")+"');\" ><u>Details</u></td>");//Details					
					out.println("  </tr>");
					no_of_rec++;
				}
				
				
				out.println("</table>");
				out.println(" 	<input type=\"hidden\" name=\"Hid_no_of_contract\" value=\""+no_of_rec+"\" />");
				out.println("  </div> ");
				out.println("</fieldset>");
				
			}
			else if(m_chksql.trim().equals("LOAD_INVOICE_DETAILS")){
				String m_finance_no=req.getParameter("finance_no");
				String id=req.getParameter("id");
				
				try{
					
					
					
					rs = stmt.executeQuery(	
						//out.println(
						" SELECT ROWNUM,INVOICE_TYPE,INVOICE_NO,  "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE,DUE_DATE,ORDER_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE_FMATTER,TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE_FMATTER "+ 
						" FROM (  "+
						" SELECT INVOICE_TYPE,INVOICE_NO,  "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE , DUE_DATE ,ORDER_NO "+
						
						" FROM "+  
						" ( "+
						
						" SELECT DECODE(INVOICE_TYPE,'INV_GENER','RENTAL','OTHER') INVOICE_TYPE, INVOICE_NO, "+
						" TOTAL_AMOUNT,BALANCE_TO_BE_RECEIVED,SETTELE_AMOUNT,VALUE_DATE,DUE_DATE,DECODE(INVOICE_TYPE,'INV_GENER','3','2') ORDER_NO "+
						" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,  "+
						" "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD B  "+
						" WHERE    "+
						" BALANCE_TO_BE_RECEIVED>0 AND   "+
						" A.INVOICE_TYPE = B.INVOICE_TYPE_CODE AND  "+
						" A.ACTIVE_STATUS = 'Y'  AND  "+
						" FINANCE_NO = '"+m_finance_no+"'  "+
						
						" UNION ALL  "+
						
						" SELECT 'ODI' INVOICE_TYPE, ODI_REF_NO,  "+
						" ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT,ODI_DATE VALUE_DATE ,ODI_DATE DUE_DATE,'1' ORDER_NO "+
						" FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A,  "+
						" "+m_schema_name+".AF_CO_PRO_INVOICE B  "+
						" WHERE   A.INVOICE_NO=B.INVOICE_NO AND  "+
						" A.FIN_NO = '"+m_finance_no+"' AND  "+
						" ODI_BAL_AMOUNT>0  "+
						" ) "+
						
						" ) "+
						" ORDER BY ORDER_NO,VALUE_DATE  "+
						"");
					
					boolean more = rs.next();
					
					if(more){
						out.println(" 		<div width=\"100%\"   > ");
						out.println(" 		<table align=\"left\" width=\"60%\" class=\"table\" border=\"1\"> "); //remove_inv
						out.println(" 			<tr valign='top' >");		
						out.println(" 				<TD align=\"left\"><strong>Finance No</strong></TD>");
						out.println(" 				<TD align=\"left\"><strong>Vehicle Reg. No</strong></TD>");
						out.println(" 				<TD align=\"right\"><strong>Rental  ODI Balance</strong></TD>");
						out.println(" 				<TD align=\"right\"><strong>Allocation Amount</strong></TD>");
						out.println(" 				<TD align=\"right\"><strong>Other Balance</strong></TD>");
						out.println(" 				<TD align=\"right\"><strong>Allocation Amount</strong></TD>");
						out.println(" 			</tr>");
						out.println(" 			<tr valign='top' >");		
						out.println(" 				<TD align=\"left\">  <div id=\"div_invoice_finance_no_"+id+"\"> </div> </TD>");  
						out.println(" 				<TD align=\"left\">  <div id=\"div_invoice_vr_no_"+id+"\"> </div> </TD>");
						out.println(" 				<TD align=\"right\"> <div id=\"div_invoice_rd_amount_"+id+"\"> </div> </TD>");
						out.println(" 				<TD align=\"right\"> <div id=\"div_invoice_allocation_amount1_"+id+"\"> </div>  </TD>");
						out.println(" 				<TD align=\"right\"> <div id=\"div_invoice_other_amount_"+id+"\"> </div>  </TD>");
						out.println(" 				<TD align=\"right\"> <div id=\"div_invoice_allocation_amount2_"+id+"\">  </div>  </TD>");
						out.println(" 			</tr>");
						out.println(" 		</table> ");
						out.println("       </div> ");
						out.println("       <br/>");
						out.println("       <br/>");
						out.println("       <br/>");
						
						out.println(" <div width=\"100%\"  > ");
						out.println(" <table border='1' WIDTH=90% class='table' align='right' >");  
						
						out.println("  <tr valign='top' >");
						out.println("  <td WIDTH='13%' align=\"left\" ><strong>Invoice No</strong></td>");
						out.println("  <td WIDTH='13%' align=\"left\" ><strong>Invoice Type</strong></td>");
						out.println("  <td WIDTH='13%' align=\"left\" ><strong>Invoiced Date</strong></td>");
						out.println("  <td WIDTH='13%' align=\"left\" ><strong>Due Date</strong></td>");
						out.println("  <td WIDTH='13%' align=\"right\" ><strong>Invoice Amount</strong></td>");
						out.println("  <td WIDTH='13%' align=\"right\" ><strong>Balance Amount</strong></td>");
						out.println("  <td WIDTH='13%' align=\"right\" ><strong>Allocation Amount</strong></td>");
						out.println("  <td WIDTH='8%'  align=\"center\" ><strong>Status</strong></td>");
						out.println("  </tr>");
						
						
					}
					//int i=0;
					while(more){
						
						out.println("  <tr valign='top' >");
						out.println("  <td WIDTH='13%' align=\"left\" >"+rs.getString("INVOICE_NO")+"</td>");
						out.println("  <td WIDTH='13%' align=\"left\" >"+rs.getString("INVOICE_TYPE")+"</td>");
						out.println("  <td WIDTH='13%' align=\"left\" >"+rs.getString("VALUE_DATE_FMATTER")+"</td>");
						out.println("  <td WIDTH='13%' align=\"left\" >"+rs.getString("DUE_DATE_FMATTER")+"</td>");
						BigDecimal amount1 = new BigDecimal("0.00");
						amount1 = amount1.add(rs.getBigDecimal("TOTAL_AMOUNT"));
						out.println("  <td WIDTH='13%' align=\"right\" >"+nf.format(amount1)+"</td>");
						BigDecimal amount2 = new BigDecimal("0.00");
						amount2 = amount2.add(rs.getBigDecimal("BALANCE_TO_BE_RECEIVED"));	
						out.println("  <td WIDTH='13%' align=\"right\" >"+nf.format(amount2)+"</td>");
						BigDecimal amount3 = new BigDecimal("0.00");
						out.println("  <td WIDTH='13%' align=\"right\"><input type = \"text\" name = \"TXT_INV_OTHER_" + id + "\" class=\"txt_input_number\" ONCHANGE=\"change_othere_invoice_amount("+id+");\" value=\""+nf.format(amount3)+"\" />  <input type=hidden name=\"HID_TXT_INV_OTHER_"+id+"\" value=\""+nf.format(amount3)+"\" >  </td>");
						out.println("  <td WIDTH='8%'  align=\"center\"><INPUT TYPE=\"checkbox\" name=\"Text_INV_status_"+id+"\"  onclick=\"check_invoice_status("+id+");\"  value=\"NO\" ></td>");
						out.println("  </tr>");
						
						//i++;
						more = rs.next();
						
					}
					
					out.println(" </table>");
					out.println(" 		</div> ");
					
					
					
					
				}catch(Exception e){
					out.println(e.toString());
				}
				
				
			}	else if(m_chksql.trim().equals("GET_IF_BACKDATED")){//Added by Jithendra 20-10-2016
			
				String m_date= req.getParameter("m_date");
			try{
			
				String slq_query=" SELECT "+m_schema_name+".AF_CO_GET_IF_BACKDATE('"+m_date+"')"+
                                  " FROM DUAL ";
			   
				rs = stmt.executeQuery(slq_query);
			    boolean more=rs.next();
			
		    if(more){
			out.print(rs.getString(1).trim());
			}
			
			
			
			
			}catch(Exception e){
					out.println(e.toString());
				}
			
		}else if(m_chksql.trim().equals("GET_NO_INVOICES")){//Added by Jithendra 20-10-2016
			
				String m_client_code=req.getParameter("m_client_code");
				/*String m_id=req.getParameter("id");
				String m_rental=req.getParameter("rentel");
				String m_other=req.getParameter("other");*/
			try{
			    
				
				
				String invoice_sql=" SELECT COUNT(*) FROM "+
									" ( "+
									" SELECT INVOICE_NO "+
									" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A "+
									" WHERE CLIENT_CODE='"+m_client_code+"' "+
									" AND BALANCE_TO_BE_RECEIVED>0 AND "+   
									" A.ACTIVE_STATUS = 'Y'   "+
									
									" UNION "+
									
									"  SELECT 'ODI' INVOICE_NO "+
									"  FROM   "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY  A, "+  
									"  AF_CO_PRO_INVOICE B   "+
									"  WHERE   A.INVOICE_NO=B.INVOICE_NO  "+
			                        "  AND  CLIENT_CODE='"+m_client_code+"' "+
									"  AND ODI_BAL_AMOUNT>0  "+
                                    "  ) "+
						             "";
				
				
				rs = stmt.executeQuery(invoice_sql);
				
			    boolean more=rs.next();
			    
		   
		    if(more){
			out.print(rs.getDouble(1));
			}
			
			
			
			
			}catch(Exception e){
					out.println(e.toString());
				}
			
		}//End
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










