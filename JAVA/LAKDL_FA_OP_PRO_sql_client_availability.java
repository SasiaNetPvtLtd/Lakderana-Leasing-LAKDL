import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_client_availability extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
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
			
			double m_val=0;
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				
				String m_string="";				
				
				//m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
				out.println("<table align='center' width='100%' class='table' >");
				
				out.println("<tr>");
				out.println("<td width='50%' class='div_input'><b>Invoice Batches</b></td>");
				out.println("<td width='50%' class='div_input'><b>Inactive Invoice Batches</b></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table'  border='1' >");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT<>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT<=2 "+
				//" AND (B.REFACT_TOLARENCE_END_DATE)>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				double m_net_amount=0;
				double m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){   
				out.println("<tr >");
				out.println("<td width='35%' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("<td width='50%' class='div_input' valign='top'>");
				out.println("<table align='center' width='100%' class='table' border='1'>");
				
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'),SUM(B.NET_INVOICE_AMOUNT),SUM(B.BALANCE_AMOUNT)  "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
				" WHERE  "+
				" A.BATCH_NO=B.BATCH_NO "+
				" AND A.FACILITY_NO='"+m_facility_code+"' "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND B.BALANCE_AMOUNT<>0 "+
				" AND B.INVOICE_STATUS='CONF' "+
				" AND B.REFACTOR_COUNT>2 "+
				" GROUP BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
				
				m_net_amount=0;
				m_bal_amount=0;
				
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>Batch No</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Date</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>Value</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>O/S</DIV></td>");
				out.println("</tr>");
				while(rs1.next()){
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input>"+rs1.getString(1)+"</DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(3))+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(rs1.getDouble(4))+"</DIV></td>");
				out.println("</tr>");
				m_net_amount=m_net_amount+rs1.getDouble(3);
				m_bal_amount=m_bal_amount+rs1.getDouble(4);
				}
				out.println("<tr >");
				out.println("<td width='35%'><DIV class=div_input></DIV></td>");
				out.println("<td width='15%'><DIV class=div_input>Batch Total</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_net_amount)+"</DIV></td>");
				out.println("<td width='25%' align='right'><DIV class=div_input>"+nf.format(m_bal_amount)+"</DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				//----------------------------------------------------------------------
				out.println("<br>");
				out.println("<hr>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				
				double m_bal_1=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACTIVE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_1=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Active Invoice Batches Total</b></DIV></td>");
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT1')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_1)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				m_bal_1=0;
				
				rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						"	A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"'"+
						" AND A.CLIENT_CODE='"+m_client_code+"'"+
						" AND B.REFACTOR_COUNT=0 "+
						" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') ");
				
				while(rs1.next()){
					m_bal_1=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Pending Approval Invoices Total</b></DIV></td>");
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT2')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_1)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				m_bal_1=0;
				
				rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						"	A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"'"+
						" AND A.CLIENT_CODE='"+m_client_code+"'"+
						" AND B.REFACTOR_COUNT=0 "+
						" AND B.INVOICE_STATUS IN('APP_C','CANCEL','APP_C2') ");
						
				while(rs1.next()){
					m_bal_1=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'  ><DIV class=div_input><b>Disapproval Invoices Total</b></DIV></td>");
				out.println("<td width='20%'  align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT3')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_1)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				m_bal_1=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_EXCE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_1=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Due date exceeded Invoice Total</b></DIV></td>");
				if(m_bal_1<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT4')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_1*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT4')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_1)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");

				double m_bal_2=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_2=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'  ><DIV class=div_input><b>Gross-Payable Amount</b></DIV></td>");
				if(m_bal_2<0){
				out.println("<td width='20%'  align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT5')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_2*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%'  align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT5')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_2)+"</b></DIV></td>");
				}
				out.println("<td width='5%' ><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
		
				double m_bal_3=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_3=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Less Payable Amount from due</b></DIV></td>");
				if(m_bal_3<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT6')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_3*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT6')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_3)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input><hr></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_net_pay_amount=(m_bal_2-m_bal_3);
				
				out.println("<tr >");
				out.println("<td width='30%'  bgcolor=\"#CCFFFF\"><DIV class=div_input><b>Net-Payable Amount</b></DIV></td>");
				if(m_net_pay_amount<0){
				out.println("<td width='20%'  bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format(m_net_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%'  bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_net_pay_amount)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
		
				m_bal_1=0;
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_1=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Less Unsettle Cheque Return</b></DIV></td>");
				if(m_bal_1<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT7')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_1*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT7')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_1)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input><hr></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_min_pay_amount=m_net_pay_amount-m_bal_1;
				
				out.println("<tr >");
				out.println("<td width='30%'  bgcolor=\"#CCFFFF\"><DIV class=div_input><b>Minimum Invoice Payable Amount</b></DIV></td>");
				if(m_min_pay_amount<0){
				out.println("<td width='20%'  bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format(m_min_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%'  bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_min_pay_amount)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<table>");
				//--------------------------------------------------------------------------------------	
				out.println("<br>");
				out.println("<hr>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b><u>Current A/C</u></b></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_bal_op_bal=0;
				
				while(rs1.next()){
					m_bal_op_bal=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Opening Balance</b></DIV></td>");
				if(m_bal_op_bal<0){
				out.println("<td width='20%'  align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT22')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_op_bal*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%'  align='right' onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT22')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_op_bal)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				
				rs2= stmt2.executeQuery("SELECT NVL(VAT_REG_NO,'-') FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
																" WHERE CLIENT_CODE='"+m_client_code+"'");
				
				double m_bal_charges=0;
				double m_bal_tax_charges=0;
				
				if(rs2.next()){
					if(!rs2.getString(1).equals("-")){
					
						m_bal_charges=0;
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
						}
						out.println("<tr >");
						out.println("<td width='30%' ><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
						if(m_bal_charges<0){
						out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT8')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_charges*-1)+")</b></DIV></td>");
						}
						else{
						out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT8')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_charges)+"</b></DIV></td>");
						}
						out.println("<td width='5%'><DIV class=div_input></DIV></td>");
						out.println("<td width='*%'><DIV class=div_input></DIV></td>");
						out.println("</tr>");
						
						m_bal_tax_charges=0;
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
						
						while(rs1.next()){
							m_bal_tax_charges=rs1.getDouble(1);
						}
						out.println("<tr >");
						out.println("<td width='30%' ><DIV class=div_input><b>Add TAX Charges</b></DIV></td>");
						if(m_bal_tax_charges<0){
						out.println("<td width='20%'  align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT9')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_tax_charges*-1)+")</b></DIV></td>");
						}
						else{
						out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT9')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_tax_charges)+"</b></DIV></td>");
						}
						out.println("<td width='5%'><DIV class=div_input></DIV></td>");
						out.println("<td width='*%'><DIV class=div_input></DIV></td>");
						out.println("</tr>");
					
					}
					else{
					
						m_bal_charges=0;
						rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
																		" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
						
						while(rs1.next()){
							m_bal_charges=rs1.getDouble(1);
						}
						out.println("<tr >");
						out.println("<td width='30%'><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
						if(m_bal_charges<0){
						out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_charges*-1)+")</b></DIV></td>");
						}
						else{
						out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_charges)+"</b></DIV></td>");
						}
						out.println("<td width='5%'><DIV class=div_input></DIV></td>");
						out.println("<td width='*%'><DIV class=div_input></DIV></td>");
						out.println("</tr>");
						
						m_bal_tax_charges=0;
					}
				}
				else{
				
					m_bal_charges=0;
					rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY'))+ "+
																	" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
					
					while(rs1.next()){
						m_bal_charges=rs1.getDouble(1);
					}
					out.println("<tr >");
					out.println("<td width='30%'><DIV class=div_input><b>Add Total Charges</b></DIV></td>");
					if(m_bal_charges<0){
					out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_charges*-1)+")</b></DIV></td>");
					}
					else{
					out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT19')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_charges)+"</b></DIV></td>");
					}
					out.println("<td width='5%'><DIV class=div_input></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					
					m_bal_tax_charges=0;
				}
	
				
				double m_bal_total_pending_payment=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PENDING_PAYMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_pending_payment=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Add Total Pending Payments</b></DIV></td>");
				if(m_bal_total_pending_payment<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT10')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_total_pending_payment*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT10')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_total_pending_payment)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_total_payment=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_PAYMENT_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_total_payment=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Add Total Payments</b></DIV></td>");
				if(m_bal_total_payment<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT11')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_total_payment*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT11')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_total_payment)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_active_collections=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_active_collections=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Less Received from Active Invoices</b></DIV></td>");
				if(m_bal_active_collections<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT13')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_active_collections*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT13')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_active_collections)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_inactive_collections=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INACT_SETTLE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_inactive_collections=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Less Received from Inactive Invoices</b></DIV></td>");
				if(m_bal_inactive_collections<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT14')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_inactive_collections*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT14')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_inactive_collections)+"</b></DIV></td>");
				}
				out.println("<td width='5%' ><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_unallocated_funds=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_UNALLOCATED_FUNDS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_unallocated_funds=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Less Unallocated Funds</b></DIV></td>");
				if(m_unallocated_funds<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT15')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_unallocated_funds*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT15')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_unallocated_funds)+"</b></DIV></td>");
				}
				out.println("<td width='5%' ><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_client_adjustments_funds=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_client_adjustments_funds=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Client Adjustments</b></DIV></td>");
				if(m_client_adjustments_funds<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT21')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_client_adjustments_funds*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT21')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_client_adjustments_funds)+"</b></DIV></td>");
				}
				out.println("<td width='5%' ><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_colse_bal=(m_bal_total_pending_payment+m_bal_op_bal+m_bal_charges+m_bal_tax_charges+m_bal_total_payment+m_client_adjustments_funds)-(m_bal_active_collections+m_bal_inactive_collections+m_unallocated_funds);
				
				//m_min_pay_amount
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input><hr></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#CCFFFF\" ><DIV class=div_input><b>Closing Balance</b></DIV></td>");
				if(m_colse_bal<0){
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format(m_colse_bal*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_colse_bal)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_interest=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_interest=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Add Interest Normal</b></DIV></td>");
				if(m_bal_interest<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT12')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_interest*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT12')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_interest)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_overinterest=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_INTEREST_OVER_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_overinterest=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Add Interest Overpaid</b></DIV></td>");
				if(m_bal_overinterest<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT20')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_overinterest*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT20')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_overinterest)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_bal_bank_cheque=0;
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_BANK_CHEQUE_TOT('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				while(rs1.next()){
					m_bal_bank_cheque=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Cheques Banked pending realisation</b></DIV></td>");
				if(m_bal_bank_cheque<0){
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>("+nf.format(m_bal_bank_cheque*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT16')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_bal_bank_cheque)+"</b></DIV></td>");
				}
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input><hr></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#CCFFFF\" ><DIV class=div_input><b>Net Exposure</b></DIV></td>");
				
				double m_exposure=((m_colse_bal+m_bal_overinterest+m_bal_interest)-(m_bal_bank_cheque));
				
				if(m_exposure<0){
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format(m_exposure*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_exposure)+"</b></DIV></td>");
				}
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				
				double m_minim_pay_amount=(m_min_pay_amount-(m_colse_bal+m_bal_overinterest+m_bal_interest));
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#CCFFFF\" ><DIV class=div_input><b>Minimum Payable Amount</b></DIV></td>");
				if(m_minim_pay_amount<0){
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format(m_minim_pay_amount*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_minim_pay_amount)+"</b></DIV></td>");
				}
				
				double m_max_pay_amount=(m_min_pay_amount-(m_colse_bal+m_bal_overinterest+m_bal_interest)+m_bal_bank_cheque);
				
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#CCFFFF\" ><DIV class=div_input><b>Maximum Payable Amount</b></DIV></td>");
				if((m_max_pay_amount)<0){
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>("+nf.format((m_max_pay_amount)*-1)+")</b></DIV></td>");
				}
				else{
				out.println("<td width='20%' bgcolor=\"#CCFFFF\" align='right'><DIV class=div_input><b>"+nf.format(m_max_pay_amount)+"</b></DIV></td>");
				}
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b><u>Additional Information</u></b></DIV></td>");
				out.println("<td width='20%' align='right'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("<table>");
				
				out.println("<table align='center' width='100%' class='table' >");
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_POD_HAND('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_pod_in_hand=0;
				
				while(rs1.next()){
					m_pod_in_hand=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%' ><DIV class=div_input><b>Total unbanked POD Cheques in hand</b></DIV></td>");
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT17')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_pod_in_hand)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				rs1= stmt1.executeQuery("SELECT "+m_schema_name+".FA_CLIENT_AV_DUE_IN7DAYS('"+m_client_code+"','"+m_facility_code+"',TO_CHAR(SYSDATE,'DD-MM-YYYY')) FROM DUAL");
				
				double m_invoices_due_7days=0;
				
				while(rs1.next()){
					m_invoices_due_7days=rs1.getDouble(1);
				}
				out.println("<tr >");
				out.println("<td width='30%'><DIV class=div_input><b>Total Invoices falling due within 7 days</b></DIV></td>");
				out.println("<td width='20%' align='right'  onClick=\"load_availability_drill('"+m_client_code+"','"+m_facility_code+"','OPT18')\" style='cursor:hand'><DIV class=div_input><b>"+nf.format(m_invoices_due_7days)+"</b></DIV></td>");
				out.println("<td width='5%'><DIV class=div_input></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<br>");
				//final credit process
				double m_client_credit=0;
				
				rs1= stmt1.executeQuery(" SELECT  NVL(CREDIT_LIMIT,0) "+
						" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+
						" WHERE  "+
						" FACILITY_NO='"+m_facility_code+"' "+
						" AND CLIENT_CODE='"+m_client_code+"' ");
				
				if(rs1.next()){
				m_client_credit=rs1.getDouble(1);
				}
				
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Credit Limit</b></DIV></td>");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				
				double m_client_final_bal=m_colse_bal-(m_bal_interest);
				
				if(m_client_final_bal<0){
				out.println("<tr >");
				out.println("<td width='30%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
				out.println("<td width='20%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit)+"</b></DIV></td>");
				out.println("<td width='*%'><DIV class=div_input></DIV></td>");
				out.println("</tr>");
				}
				else{
					if(m_client_final_bal>m_client_credit){
					out.println("<tr >");
					out.println("<td width='30%' bgcolor=\"#FF0033\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='20%' bgcolor=\"#FF0033\" align='right'><DIV class=div_input><b>("+nf.format((m_client_credit-m_client_final_bal)*-1)+")</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
					else{
					out.println("<tr >");
					out.println("<td width='30%' bgcolor=\"#FF99FF\" ><DIV class=div_input><b>Client Maximum Availability</b></DIV></td>");
					out.println("<td width='20%' bgcolor=\"#FF99FF\" align='right'><DIV class=div_input><b>"+nf.format(m_client_credit-m_client_final_bal)+"</b></DIV></td>");
					out.println("<td width='*%'><DIV class=div_input></DIV></td>");
					out.println("</tr>");
					}
				}
				out.println("</table>");

			}
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY_DRILL")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				
				m_val=0;
				
				if(m_option_no.equals("OPT1")){
					
				rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.BALANCE_AMOUNT<>0 "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT<=2 "+
					" ORDER BY B.TOLARENCE_END_DATE DESC ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(8);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT2")){
					
				rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.REFACTOR_COUNT=0 "+
					" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') "+
					" ORDER BY B.TOLARENCE_END_DATE DESC ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(8);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT3")){
					
				rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.REFACTOR_COUNT=0 "+
					" AND B.INVOICE_STATUS IN('APP_C','CANCEL','APP_C2') "+
					" ORDER BY B.TOLARENCE_END_DATE ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(8);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT4")){
					
				rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.BALANCE_AMOUNT<>0 "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT>2 "+
					" ORDER BY B.TOLARENCE_END_DATE DESC ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(8);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT5")){
				
				double m_temp1=0;
				double m_temp2=0;
				
				rs2= stmt2.executeQuery("SELECT FACILITY_NO,"+//1
						" CLIENT_CODE,"+//2
						" DEBTOR_CODE,"+//3
						" CREDIT_LIMIT,"+//4
						" RESERVE_MARGIN "+//5
         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
         		" AND CLIENT_CODE='"+m_client_code+"' "+
						" ORDER BY DEBTOR_CODE ");
				
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>With Reserve margin</b></DIV></td>");
				out.println("</tr>");
				
				while(rs2.next()){
			
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.BALANCE_AMOUNT "+//8
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT<>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND (B.TOLARENCE_END_DATE)>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" ORDER BY B.TOLARENCE_END_DATE DESC ");
	
					int j=1;
					
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(8);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8)*(100-rs2.getDouble(5))/100)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					if(m_temp1>rs2.getDouble(4)){
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
					m_temp2=m_temp2+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
					}
					else if(m_temp1<=rs2.getDouble(4)){
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right>Sub Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1*(100-rs2.getDouble(5))/100)+"</td>");
					m_temp2=m_temp2+(m_temp1*(100-rs2.getDouble(5))/100);
					}
					out.println("</tr>");
					m_temp1=0;
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input align=right></td>");
				out.println("<td width='10%' class=div_input align=right></td>");
				out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT6")){
				
				double m_temp1=0;
				double m_temp2=0;
				
				rs2= stmt2.executeQuery("SELECT FACILITY_NO,"+//1
						" CLIENT_CODE,"+//2
						" DEBTOR_CODE,"+//3
						" CREDIT_LIMIT,"+//4
						" RESERVE_MARGIN "+//5
         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
         		" AND CLIENT_CODE='"+m_client_code+"' "+
						" ORDER BY DEBTOR_CODE ");
				
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>With Reserve margin</b></DIV></td>");
				out.println("</tr>");
				
				while(rs2.next()){
			
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.BALANCE_AMOUNT "+//8
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT<>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND (B.TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" ORDER BY B.TOLARENCE_END_DATE DESC ");
	
					int j=1;
					
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(8);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8)*(100-rs2.getDouble(5))/100)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					if(m_temp1>rs2.getDouble(4)){
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
					m_temp2=m_temp2+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
					}
					else if(m_temp1<=rs2.getDouble(4)){
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right>Sub Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1*(100-rs2.getDouble(5))/100)+"</td>");
					m_temp2=m_temp2+(m_temp1*(100-rs2.getDouble(5))/100);
					}
					out.println("</tr>");
					m_temp1=0;
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input align=right></td>");
				out.println("<td width='10%' class=div_input align=right></td>");
				out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT7")){
	
					rs1= stmt1.executeQuery("SELECT A.RETURN_NO,"+//1
					" A.DIPOSIT_NO,"+//2
					" A.RECEIPT_NO,"+//3
					" A.CHEQUE_NO,"+//4
					" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//5
					" A.DEPOSIT_AMOUNT "+//6
					" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.REALIZE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.REALIZE_DATE DESC ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Return No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Deposit No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Realize Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(6);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_return_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_deposit_details('"+rs1.getString(2)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT8")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE<>'VAT' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE<>'VAT' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(5);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_charges_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT9")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE='VAT' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE='VAT' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(5);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_charges_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT10")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							" WHERE "+
							" FACILITY_NO='"+m_facility_code+"' "+
							" AND CLIENT_CODE='"+m_client_code+"' "+
							" AND PAY_STATUS NOT IN('CONF','PRINT','DISB','CANCEL') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
							" ORDER BY PAY_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							" WHERE "+
							" FACILITY_NO='"+m_facility_code+"' "+
							" AND CLIENT_CODE='"+m_client_code+"' "+
							" AND PAY_STATUS NOT IN('CONF','PRINT','DISB','CANCEL') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
							" ORDER BY PAY_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_payment_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT11")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							" WHERE "+
							" FACILITY_NO='"+m_facility_code+"' "+
							" AND CLIENT_CODE='"+m_client_code+"' "+
							" AND PAY_STATUS IN ('CONF','PRINT','DISB') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
							" ORDER BY PAY_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							" WHERE "+
							" FACILITY_NO='"+m_facility_code+"' "+
							" AND CLIENT_CODE='"+m_client_code+"' "+
							" AND PAY_STATUS IN ('CONF','PRINT','DISB') "+
							" AND TO_DATE(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
							" ORDER BY PAY_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_payment_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT12")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Intrest Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){
					
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY TRNDATE DESC ");
					}
					else{
					
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY TRNDATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						if(rs1.getDouble(3)>=0){
							out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right>("+nf.format(rs1.getDouble(3)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					if(m_val>=0){
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right><b>("+nf.format(m_val*-1)+")</b></td>");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT13")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Allocate Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND (B.REFACT_TOLARENCE_END_DATE)>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+ 
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY C.ALLOCATED_DATE ");
					}
					else{
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND (B.REFACT_TOLARENCE_END_DATE)>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+ 
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY C.ALLOCATED_DATE ");
					}
							
					int j=1;
					double m_temp1=0;
					double m_temp2=0;
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(8);
						m_temp2=m_temp2+rs1.getDouble(9);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input >Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp2)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT14")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Allocate Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND (B.REFACT_TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+ 
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY C.ALLOCATED_DATE ");
					}
					else{
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND (B.REFACT_TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+ 
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY C.ALLOCATED_DATE ");
					}
							
					int j=1;
					double m_temp1=0;
					double m_temp2=0;
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(8);
						m_temp2=m_temp2+rs1.getDouble(9);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input >Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp2)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT15")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Date</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){
					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY EFF_VALDATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY EFF_VALDATE DESC ");
					}
							
					int j=1;
					double m_temp1=0;
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(4);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input >Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT16")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Date</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND SETTLE_MODE='CHEQUE' "+
					" AND REC_STATUS='B' "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY EFF_VALDATE DESC ");
							
					int j=1;
					double m_temp1=0;
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(4);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input >Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT17")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Cheque Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO,"+
					" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE,"+
					" NVL(A.CHEQUE_AMOUNT,0), "+
					" A.CHEQUE_NO "+
					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
					" WHERE A.POD_REF_NO=B.POD_REF_NO "+
					" AND A.POD_STATUS='N' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.FACILITY_NO='"+m_facility_code+"' ");
							
					int j=1;
					double m_temp1=0;
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_temp1=m_temp1+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input >Total</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(m_temp1)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT18")){
					
				rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.BALANCE_AMOUNT<>0 "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" AND (TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')+7)<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY B.DUE_DATE DESC ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
				
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_val=m_val+rs1.getDouble(8);
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(6)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
					out.println("</tr>");
				}
				out.println("<tr bgcolor=\"#CCCCCC\">");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><b>Total</b></td>");
				out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");
				}
				if(m_option_no.equals("OPT19")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE>CHARGES AND TAX DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY A.EFF_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(5);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_charges_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT20")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE>OVERPAIED INTEREST DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Intrest Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY TRNDATE DESC ");
					}
					else{
					
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY TRNDATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						if(rs1.getDouble(3)>=0){
							out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right>("+nf.format(rs1.getDouble(3)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					if(m_val>=0){
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right><b>("+nf.format(m_val*-1)+")</b></td>");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT21")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE>CLIENT ADJUSTMENT DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjustment Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment Type</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment Amount</b></DIV></td>");
					out.println("</tr>");

					if(rs2.next()){				
					rs1= stmt1.executeQuery("SELECT TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),"+
					" NVL(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1),0),"+
					" ADJUSTMENT_NO,"+
					" SOURCE_DOCUMENT,"+
					" ADJUST_TYPE "+
					" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND ADJUST_CATEGORY='CLA' "+
					" AND INVOICE_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY ADJUST_DATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery("SELECT TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),"+
					" NVL(DECODE(ADJUST_TYPE,'DR',ADJUST_AMOUNT,ADJUST_AMOUNT*-1),0),"+
					" ADJUSTMENT_NO,"+
					" SOURCE_DOCUMENT,"+
					" ADJUST_TYPE "+
					" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND ADJUST_CATEGORY='CLA' "+
					" AND INVOICE_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY ADJUST_DATE DESC ");
					}
							
				
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(2);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_adjustment_details('"+rs1.getString(3)+"')\"><U>"+rs1.getString(3)+"</U></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(5)+"</td>");
						if(rs1.getDouble(2)>=0){
							out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right>("+nf.format(rs1.getDouble(2)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ><b>Total</b></td>");
					if(m_val>=0){
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right><b>("+nf.format(m_val*-1)+")</b></td>");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT22")){
					
					out.println("<HTML><HEAD><TITLE>PREVIOUS OPEN BALANCES DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_1(m_client,m_from_date,m_to_date) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_RPT_display_accounts_report?chksql=DRILL_2&CLIENT_CODE=\"+m_client+\"&DRILL_TYPE=CA&start_date=\"+m_from_date+\"&end_date=\"+m_to_date;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Month Start Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Month End Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Opening Balance</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT TO_CHAR(MONTH_START_DATE,'DD-MM-YYYY'),"+
					" TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY'),"+
					" TRNAMOUNT "+
					" FROM("+
					" SELECT MONTH_START_DATE,"+
					" MONTH_END_DATE,"+
					" TRNAMOUNT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" UNION ALL "+
					" SELECT MONTH_START_DATE,"+
					" MONTH_END_DATE,"+
					" TRNAMOUNT "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL_BK "+
					" WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND TO_DATE(TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE(TO_CHAR(ADD_MONTHS(SYSDATE,-7),'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ) ORDER BY MONTH_END_DATE");
							
				 
					int j=1;
				
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						if(rs1.getDouble(3)>=0){
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' onclick='drill_down_1(\""+m_client_code+"\",\""+rs1.getString(1)+"\",\""+rs1.getString(2)+"\")'>"+nf.format(rs1.getDouble(3))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' onclick='drill_down_1(\""+m_client_code+"\",\""+rs1.getString(1)+"\",\""+rs1.getString(2)+"\")'>("+nf.format(rs1.getDouble(3)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ><b>Total</b></td>");
					if(m_val>=0){
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right><b>("+nf.format(m_val*-1)+")</b></td>");
					}
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
			}
			else {
			    out.println("Undefined");
			}
			
			stmt1.close();
			stmt2.close();
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

