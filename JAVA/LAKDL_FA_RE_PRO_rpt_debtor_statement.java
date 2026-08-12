import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:04-01-2007

public class LAKDL_FA_RE_PRO_rpt_debtor_statement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
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

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_DEBTOR_STATEMENT_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_eff_date = "";
				
				double m_tot_fee_charge_amount =0;
				double m_tot_settle_fee_amount =0;
				double m_tot_bal_fee_amount =0;
				double m_tot_grand =0;
			
			
				rs1= stmt1.executeQuery(" SELECT DISTINCT "+
  			 " A.BATCH_NO,"+//1
  			 " NVL(A.INVOICE_NO,'-'),"+//2
  			 " NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'),"+//3    
  			 " NVL(A.INVOICE_AMOUNT,0),"+//4
  			 " NVL(A.NET_INVOICE_AMOUNT,0),"+//5
  			 " NVL(A.SETTLE_AMOUNT,0),"+//6    
  			 " NVL(A.BALANCE_AMOUNT,0),"+//7
				 " NVL(A.DEBTOR_CODE,'-') "+//8	
 				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A ,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
 				" WHERE A.BATCH_NO=B.BATCH_NO "+
 				" AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
 				" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 				" TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.BATCH_NO ");

		 	String client_code = "";
			boolean mflag=true;
			double m_invoice_tot=0,m_net_tot=0,m_settle_tot=0,m_bal_tot=0;

				boolean more = rs1.next();
				
					out.println("<HTML><HEAD><TITLE>Debtor Statement </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language1.2='JavaScript' >"); 
					out.println(" function show_allocation_details(m_receipt_no,m_debtor_code){ ");
					out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_rpt_debtor_statement?chksql=LOAD_SETTLEMENT_ALLOCATED&receipt_no=\"+m_receipt_no+\"&debtor_code=\"+m_debtor_code;");
					out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println(" }");
				  out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Debtor Statement for the Period of "+m_from_date+" to "+m_to_date+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");

			  if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Net Amount</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Settle Amount</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Balance Amount</b></DIV></td>");  
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input onClick=\"show_invoice_details('"+rs1.getString(8)+"','"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(6))+"</DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(7))+"</DIV></td>");  
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_invoice_tot = m_invoice_tot+rs1.getDouble(4);
						m_net_tot = m_net_tot+rs1.getDouble(5);
						m_settle_tot = m_settle_tot+rs1.getDouble(6);
						m_bal_tot = m_bal_tot+rs1.getDouble(7);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='10%' ></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_invoice_tot)+"</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_net_tot)+"</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_settle_tot)+"</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_bal_tot)+"</b></DIV></td>");  
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");

			 }	
				
				 	rs1= stmt1.executeQuery(" SELECT "+
				   " ADJUSTMENT_NO,"+//1
  	  		 " NVL(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'-'), "+//2    
  				 " NVL(DECODE(ADJUST_TYPE,'DR','Debit','Credit'),'-'),"+//3
  				 " NVL(INITCAP(SOURCE_DOCUMENT),'-'),"+//4
					 " NVL(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1),0)"+//5
 					 " FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
 					 " WHERE CLIENT_CODE='"+m_client_code+"' AND FACILITY_NO='"+m_facility_no+"' AND DEBTOR_CODE='"+m_debtor_code+"' "+
					 " AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 					 " TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY ADJUSTMENT_NO ");	
						
						//boolean mflag=true;
						double m_adjust_tot=0;

						more = rs1.next();

			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjust Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjust Type</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Adjust Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_adjustment_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>");
						if(rs1.getString(3).equals("Debit")){
							out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>"); 
						}
						else {
							out.println("<td width='15%' align='right'><DIV class=div_input>("+nf.format(rs1.getDouble(5)*-1)+")</DIV></td>"); 
						}
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_adjust_tot = m_adjust_tot+rs1.getDouble(5);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%'></td>");
						out.println("<td width='10%'><DIV class=div_input></DIV></td>");
						out.println("<td width='10%'><DIV class=div_input></DIV></td>");
						out.println("<td width='15%'><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_adjust_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");

			 }	

				 rs1= stmt1.executeQuery(" SELECT DISTINCT "+
    		 " A.RECEIPT_NO,"+//1
  			 " TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),"+//2
  			 " NVL(A.REC_AMOUNT,0),"+//3
  			 " DECODE(A.REC_STATUS,'N','Receipt to be deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised'),"+//4    
  			 " DECODE(A.RECEIPT_TYPE,'DS','Debtor wise','CS','Client wise','IS','Invoice wise','POD','Post dated','SSH','SSH'),"+//5    
  			 " NVL(A.ALLO_AMOUNT,0),"+//6
  			 " NVL(A.BALANCE_AMOUNT,0)"+//7
 				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
 				" WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND (A.DEBTOR_CODE='"+m_debtor_code+"' OR B.DEBTOR_CODE='"+m_debtor_code+"') "+
 				" AND A.SUS_REF_NO=B.POD_REF_NO(+) "+
 				" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
 				" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.RECEIPT_NO ");

						double m_receipt_tot=0,m_allo_tot=0,m_balance_tot=0;
  					more = rs1.next();
						
			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>EFF Value Date</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Status</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Type</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Allocated Amount</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Unallocated Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(5)+"</DIV></td>");
						if(rs1.getDouble(6)>0){
							out.println("<td width='15%' align='right'><DIV class=div_input onClick=\"show_allocation_details('"+rs1.getString(1)+"','"+m_debtor_code+"')\" style='cursor:hand'><u>"+nf.format(rs1.getDouble(6))+"</u></DIV></td>"); 
						}
						else {
							out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(6))+"</DIV></td>"); 
						}
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(7))+"</DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_receipt_tot = m_receipt_tot+rs1.getDouble(3);
						m_allo_tot = m_allo_tot+rs1.getDouble(6);
						m_balance_tot = m_balance_tot+rs1.getDouble(7);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_receipt_tot)+"</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_allo_tot)+"</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_balance_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");	
						out.println("</tr>"); 
					  out.println("</table>");

			 }	
				
					
						rs1= stmt1.executeQuery(" SELECT DISTINCT "+
  						 " A.POD_REF_NO,"+//1
  						 " TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//2
  						 " NVL(B.CHEQUE_NO,'-'),"+//3
  						 " NVL(B.CHEQUE_AMOUNT,0)"+//4
 						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B "+
 						" WHERE A.POD_REF_NO=B.POD_REF_NO AND A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
						" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
 						" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.POD_REF_NO ");

							
							
						double m_cheq_tot=0;
  					more = rs1.next();
				
			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Cheque Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' align='right' ><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_cheq_tot = m_cheq_tot+rs1.getDouble(4);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_cheq_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");	
						out.println("</tr>"); 
					  out.println("</table>");

			 }	

					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
				
			}
			else if(m_chksql.equals("LOAD_CLIENT_STATEMENT_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_eff_date = "";
				
				double m_tot_fee_charge_amount =0;
				double m_tot_settle_fee_amount =0;
				double m_tot_bal_fee_amount =0;
				double m_tot_grand =0;
			
			
				rs1= stmt1.executeQuery(" SELECT DISTINCT "+
  			 " A.BATCH_NO,"+//1
  			 " NVL(A.INVOICE_NO,'-'),"+//2
  			 " NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'),"+//3    
  			 " NVL(A.INVOICE_AMOUNT,0),"+//4
  			 " NVL(A.NET_INVOICE_AMOUNT,0),"+//5
  			 " NVL(A.SETTLE_AMOUNT,0),"+//6    
  			 " NVL(A.BALANCE_AMOUNT,0),"+//7
				 " NVL(A.DEBTOR_CODE,'-') "+//8	
 				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A ,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
 				" WHERE A.BATCH_NO=B.BATCH_NO "+
 				" AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' "+
 				" AND TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 				" TO_DATE(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.BATCH_NO ");

		 	String client_code = "";
			boolean mflag=true;
			double m_invoice_tot=0,m_net_tot=0,m_settle_tot=0,m_bal_tot=0;

				boolean more = rs1.next();
				
					out.println("<HTML><HEAD><TITLE>Client Statement </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language1.2='JavaScript' >"); 
					out.println(" function show_allocation_details(m_receipt_no,m_debtor_code){ ");
					out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RE_PRO_rpt_debtor_statement?chksql=LOAD_SETTLEMENT_ALLOCATED&receipt_no=\"+m_receipt_no+\"&debtor_code=\"+m_debtor_code;");
					out.println("  window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println(" }");
				  out.println("</SCRIPT>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Client Statement for the Period of "+m_from_date+" to "+m_to_date+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");

			  if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Net Amount</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Settle Amount</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Balance Amount</b></DIV></td>");  
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input onClick=\"show_invoice_details('"+rs1.getString(8)+"','"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(6))+"</DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(7))+"</DIV></td>");  
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_invoice_tot = m_invoice_tot+rs1.getDouble(4);
						m_net_tot = m_net_tot+rs1.getDouble(5);
						m_settle_tot = m_settle_tot+rs1.getDouble(6);
						m_bal_tot = m_bal_tot+rs1.getDouble(7);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='10%' ></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_invoice_tot)+"</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_net_tot)+"</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_settle_tot)+"</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_bal_tot)+"</b></DIV></td>");  
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");

			 }	
				
				 	rs1= stmt1.executeQuery(" SELECT "+
				   " ADJUSTMENT_NO,"+//1
  	  		 " NVL(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'-'), "+//2    
  				 " NVL(DECODE(ADJUST_TYPE,'DR','Debit','Credit'),'-'),"+//3
  				 " NVL(INITCAP(SOURCE_DOCUMENT),'-'),"+//4
					 " NVL(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1),0)"+//5
 					 " FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
 					 " WHERE CLIENT_CODE='"+m_client_code+"' AND FACILITY_NO='"+m_facility_no+"' "+
					 " AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
 					 " TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY ADJUSTMENT_NO ");	
						
						//boolean mflag=true;
						double m_adjust_tot=0;

						more = rs1.next();

			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjust Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjust Type</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Adjust Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_adjustment_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>");
						if(rs1.getString(3).equals("Debit")){
							out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>"); 
						}
						else {
							out.println("<td width='15%' align='right'><DIV class=div_input>("+nf.format(rs1.getDouble(5)*-1)+")</DIV></td>"); 
						}
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_adjust_tot = m_adjust_tot+rs1.getDouble(5);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%'></td>");
						out.println("<td width='10%'><DIV class=div_input></DIV></td>");
						out.println("<td width='10%'><DIV class=div_input></DIV></td>");
						out.println("<td width='15%'><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_adjust_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");

			 }	

				 rs1= stmt1.executeQuery(" SELECT DISTINCT "+
    		 " A.RECEIPT_NO,"+//1
  			 " TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),"+//2
  			 " NVL(A.REC_AMOUNT,0),"+//3
  			 " DECODE(A.REC_STATUS,'N','Receipt to be deposited','B','Receipt Deposited','C','Receipt Returned','Y','Receipt Realised'),"+//4    
  			 " DECODE(A.RECEIPT_TYPE,'DS','Debtor wise','CS','Client wise','IS','Invoice wise','POD','Post dated','SSH','SSH'),"+//5    
  			 " NVL(A.ALLO_AMOUNT,0),"+//6
  			 " NVL(A.BALANCE_AMOUNT,0),"+//7
				 " B.DEBTOR_CODE,"+//8
         " A.DEBTOR_CODE,"+//9
				 " A.RECEIPT_TYPE "+//10	
 				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A , "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+ //,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B
 				" WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"'  "+
 				" AND A.SUS_REF_NO=B.POD_REF_NO(+) "+
 				" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
 				" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.RECEIPT_NO ");

						double m_receipt_tot=0,m_allo_tot=0,m_balance_tot=0;
  					more = rs1.next();
						
			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>EFF Value Date</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Status</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Type</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Allocated Amount</b></DIV></td>"); 
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Unallocated Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(5)+"</DIV></td>");
						if(rs1.getDouble(6)>0){
						  if(rs1.getString(10).equals("POD")) {
							  out.println("<td width='15%' align='right'><DIV class=div_input onClick=\"show_allocation_details('"+rs1.getString(1)+"','"+rs1.getString(8)+"')\" style='cursor:hand'><u>"+nf.format(rs1.getDouble(6))+"</u></DIV></td>"); 
							}
							else {
								out.println("<td width='15%' align='right'><DIV class=div_input onClick=\"show_allocation_details('"+rs1.getString(1)+"','"+rs1.getString(9)+"')\" style='cursor:hand'><u>"+nf.format(rs1.getDouble(6))+"</u></DIV></td>"); 
							}
						}
						else {
							out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(6))+"</DIV></td>"); 
						}
						out.println("<td width='15%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(7))+"</DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_receipt_tot = m_receipt_tot+rs1.getDouble(3);
						m_allo_tot = m_allo_tot+rs1.getDouble(6);
						m_balance_tot = m_balance_tot+rs1.getDouble(7);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>");
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_receipt_tot)+"</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_allo_tot)+"</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_balance_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");	
						out.println("</tr>"); 
					  out.println("</table>");

			 }	
				
					
						rs1= stmt1.executeQuery(" SELECT DISTINCT "+
  						 " A.POD_REF_NO,"+//1
  						 " TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//2
  						 " NVL(B.CHEQUE_NO,'-'),"+//3
  						 " NVL(B.CHEQUE_AMOUNT,0)"+//4
 						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B "+
 						" WHERE A.POD_REF_NO=B.POD_REF_NO AND A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' "+
						" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
 						" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ORDER BY A.POD_REF_NO ");

							
							
						double m_cheq_tot=0;
  					more = rs1.next();
				
			  if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
					out.println("<td width='15%' align='right'><DIV class=div_input><b>Cheque Amount</b></DIV></td>"); 
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='15%' align='right' ><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						m_cheq_tot = m_cheq_tot+rs1.getDouble(4);
						more = rs1.next();	 

					}
					
					 	out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Total</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>"+nf.format(m_cheq_tot)+"</b></DIV></td>"); 
						out.println("<td width='*%'></td>");	
						out.println("</tr>"); 
					  out.println("</table>");

			 }	

					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
				
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_ALLOCATED")){
			
			  String m_receipt_no=req.getParameter("receipt_no");
			  String m_debtor_code=req.getParameter("debtor_code");
					
					rs1= stmt1.executeQuery(" SELECT  DISTINCT "+
					 " B.RECEIPT_NO, "+//1
  				 " B.INVOICE_NO, "+//2
  				 " TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),"+//3
  				 " NVL(A.ALLOCATED_AMOUNT,0),"+//4
  				 " NVL(B.BALANCE_AMOUNT,0),"+//5
  				 " NVL(A.BALANCE_AMOUNT,0),"+//6
           " B.RECEIPT_TYPE,"+//7
           " C.INVOICE_NO"+//8
 					 " FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO C  "+
 					 " WHERE A.RECEIPT_NO='"+m_receipt_no+"' AND A.RECEIPT_NO=B.RECEIPT_NO "+
           " AND B.SUS_REF_NO=C.POD_REF_NO(+) ORDER BY B.INVOICE_NO ");
					
						boolean mflag=true;
						boolean more = rs1.next();	 
						
  			  out.println("<HTML><HEAD><TITLE>Allocated Settlement details </TITLE></HEAD>");
				  out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				  out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Allocated Settlement details - Receipt No - "+m_receipt_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR>");
					
				if(!more){
				 	out.println("<table align='center' width='100%' class='table' >");	
					out.println("<tr>");
					out.println("<td width='*%' align='center' ><DIV class=div_input><b>No Data Found</b></DIV></td>");
					out.println("</tr>"); 	
					out.println("</table>");
				}	
			  else if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='10%'></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Allocated Date</b></DIV></td>");
					out.println("<td width='20%' align='right'><DIV class=div_input><b>Allocated Amount</b></DIV></td>");
					out.println("<td width='20%' align='right'><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>"); 
					out.println("</table>");
					
					
				
					while(more){

						out.println("<table align='center' width='100%' class='table' >");

							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
						out.println("<td width='10%'></td>"); 
						if(rs1.getString(7).equals("POD")){
							out.println("<td width='10%' ><DIV class=div_input onClick=\"show_invoice_details('"+m_debtor_code+"','"+rs1.getString(8)+"')\" style='cursor:hand' ><u>"+rs1.getString(8)+"</u></DIV></td>");
						}
						else
						{
							out.println("<td width='10%' ><DIV class=div_input onClick=\"show_invoice_details('"+m_debtor_code+"','"+rs1.getString(2)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></DIV></td>");
						}
						out.println("<td width='20%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='20%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
						out.println("<td width='20%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(5))+"</DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					  out.println("</table>");
						more = rs1.next();	 

					}
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			 }	

			
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


