import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_RE_debtor_sales_ledger_statement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1,rs2,rs3;
	public String m_chksql;
  
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url;
			m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
            
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control","No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();
			
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			
			m_chksql=req.getParameter("chksql");

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("get_report")){
			try{

				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				String m_today="";
			
				out.println("<html><head><font 10pt arial><title>Debtor Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("function print_data(){");
				out.println(" 		m_table.innerHTML    = \"\"; ");
				out.println(" 		window.print();");
				out.println("}");
				out.println("</SCRIPT>");
				out.println("<body leftmargin='0' topmargin='0'>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");

				out.println("<table border='0' width='100%' class='table'>"); 		
				out.println("<tr ><td width='90%' class='factoring-letter-body' style='{text-align:right;}'><div id= 'm_table'> <input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></div> </td>");
				out.println("<td width='*%'></td></tr>");				
				out.println("</table>");
				
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				rs1.close();
				
				
				rs2 = stmt2.executeQuery(" SELECT DISTINCT FACILITY_NO,CLIENT_CODE,DEBTOR_CODE  "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
				" WHERE CLIENT_CODE='"+m_client_no+"' "+
				" AND FACILITY_NO='"+m_facility_no+"' ");
							
				while(rs2.next()){
				
				rs3 = stmt3.executeQuery("SELECT  CLIENT_CODE "+//1
				" FROM( "+
				" SELECT   "+
				" A.CLIENT_CODE CLIENT_CODE  "+
				" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
				" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
				" AND "+m_schema_name+".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='"+rs2.getString(3)+"' "+
				" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
				" AND A.ALLOCATED_AMOUNT>0 "+
				" AND A.FACILITY_NO='"+m_facility_no+"' "+
				" AND A.CLIENT_CODE='"+m_client_no+"' "+
				" UNION ALL "+
				//Added by Dineth on 29-07-2009
        " SELECT A.CLIENT_CODE CLIENT_CODE "+
        " FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				" WHERE A.REC_STATUS='Y'  "+
				" AND A.RECEIPT_TYPE<>'SSH' "+
				" AND A.FACILITY_NO='"+m_facility_no+"' "+
				" AND A.CLIENT_CODE='"+m_client_no+"' "+
				" AND A.DEBTOR_CODE='"+rs2.getString(3)+"' "+ 
				" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
				" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
				" AND A.BALANCE_AMOUNT>0 "+
				" UNION ALL "+
				//End by Dineth on 29-07-2009
				" SELECT  "+
				" B.CLIENT_CODE CLIENT_CODE "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.SETTLE_AMOUNT=0 "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
				" AND B.FACILITY_NO='"+m_facility_no+"' "+
				" AND B.CLIENT_CODE='"+m_client_no+"' "+
				" AND A.DEBTOR_CODE='"+rs2.getString(3)+"' "+
				" UNION ALL "+
				" SELECT  "+
				" B.CLIENT_CODE CLIENT_CODE "+
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
				" WHERE A.BATCH_NO=B.BATCH_NO "+
				" AND A.BALANCE_AMOUNT>0 "+
				" AND B.FACILITY_NO='"+m_facility_no+"' "+
				" AND B.CLIENT_CODE='"+m_client_no+"' "+
				" AND A.DEBTOR_CODE='"+rs2.getString(3)+"' "+
				" AND A.INVOICE_SEQ_NO NOT IN( SELECT A.INVOICE_NO "+
				" 								FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
				" 								WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
				" 								AND "+m_schema_name+".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='"+rs2.getString(3)+"' "+
				" 								AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
				" 								AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
				" 								AND A.ALLOCATED_AMOUNT>0 "+
				" 								AND A.FACILITY_NO='"+m_facility_no+"' "+
				" 								AND A.CLIENT_CODE='"+m_client_no+"') "+
				" ) ");
				

				
				if(rs3.next()){				
							rs1 = stmt1.executeQuery  (	" SELECT "+
							//out.println  (	" SELECT "+
		  				  " DISTINCT A.CLIENT_CODE, "+//1
		  				  " NVL(UPPER(A.FULL_NAME),'-'), "+//2
		  				  " NVL(UPPER(A.REGISTERED_ADDRESS1),'-'), "+//3
		  				  " NVL(UPPER(A.REGISTERED_ADDRESS2),'-'), "+//4
		  				  " UPPER(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), '','' , "+//5
								//" NVL(UPPER(B.CONTACT_PERSON),'-'), "+//6
								//" NVL(UPPER(B.DESIGNATION_PAYMENT),' '), "+//7
								" A.CLIENT_TYPE "+//8
		  				  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR B "+
		  				  " WHERE B.DEBTOR_CODE=A.CLIENT_CODE AND A.CLIENT_CODE='"+rs2.getString(3)+"' ");
		
							while(rs1.next()){
									out.println("<table border='0' width='100%' class=table >");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-header'><B>Co.Reg.No. PB75</B></td></tr>");
									out.println("</table>");
									
									int m_num_lines=6;
									
									out.println("<table border='0' width='100%' class=table >");
									out.println("<tr><td width='*%' class='factoring-letter-body'>"+m_today+"</td></tr>");
									if(rs1.getString(8).equals("C")){
									//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
									//out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");CHIEF FINANCIAL OFFICER/ACCOUNTANT
									out.println("<tr><td width='*%' class='factoring-letter-body'>CHIEF FINANCIAL OFFICER/ACCOUNTANT</td></tr>");
									}
									out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");	
									out.println("</table>");
									out.println("<table border='0' width='100%' class=table >");
									out.println("<tr><td width='*%' class='factoring-letter-body'><b>DEBTOR CODE:"+rs1.getString(1)+"</b></td></tr>");	
									out.println("<tr><td width='*%' class='factoring-letter-body'><b>REPORT PERIOD: "+m_date_from+" - "+m_date_to+"</b></td></tr>");	
									out.println("</table>");			
									
									out.println("<table border='0' width='100%' class=table >");
									out.println("<tr><td width='*%' class='factoring-letter-body'><center><b>INVOICE AND SETTLEMENT REPORT</b></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'><center>PRINT DATE/TIME "+m_today+"<center></td></tr>");
									out.println("</table>");			
									
									//--------------------------------------------------------------------------
									out.println("<table border='0' width='100%' class=table>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>Dear Sir</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>We inform to you that the following invoice amounts were settled against your payments.</td></tr>");
									out.println("</table>");	
									
									out.println("<table border='0' width='100%' class=table >");
									out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Date</b></td>");
									out.println("<td width='20%' class='factoring-letter-body' align='left'><b>Supplier Name</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='left'><b>Cheque No</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='left'><b>Bank Branch Code</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Cheque Amount</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='center'><b>Invoice No</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='right'><b>Invoice Amount</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='right'><b>Settle Amount</b></td>");
									out.println("<td width='10%' class='factoring-letter-body' align='right'><b>Balance Amount</b></td>");
									out.println("<td width='*%'></td>"); 
									out.println("</tr>");
									
									//out.println(
									rs = stmt.executeQuery(
									//out.println(
									"SELECT CLIENT_CODE, "+//1
									" CNAME, "+//2
									" FACILITY_NO, "+//3
									" CHEQUE_NO, "+//4
									" CHQ_AMT, "+//5
									" NVL(BRANCH_NAME,'-'), "+//6
									" INVOICE_NO, "+//7
									" INV_AMOUNT, "+//8
									" ALL_AMT, "+//9
									" BAL_AMT, "+//10
									" TO_CHAR(ALLO_DATE,'DD-MM-YYYY') "+//11
									" FROM( "+
									" SELECT   "+
									" DISTINCT A.CLIENT_CODE CLIENT_CODE,  "+
									" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) CNAME, "+
									" A.FACILITY_NO FACILITY_NO, "+
									" DECODE(B.SETTLE_MODE,'CHEQUE',B.CHEQUE_NO,B.SETTLE_MODE) CHEQUE_NO, "+
									" B.REC_AMOUNT CHQ_AMT, "+
									//" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE) BRANCH_NAME, "+//comment by ns on31-05-2011
									" B.PAYER_BRANCH_CODE BRANCH_NAME, "+
									" "+m_schema_name+".FA_GET_INVOICE_NO(A.INVOICE_NO) INVOICE_NO, "+
									" A.INVOICED_AMOUNT INV_AMOUNT,  "+
									" A.ALLOCATED_AMOUNT ALL_AMT, "+
									" ("+m_schema_name+".FA_CLIENT_PRE_INV_BAL('"+m_client_no+"','"+m_facility_no+"',A.INVOICE_NO,TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'))-A.ALLOCATED_AMOUNT) BAL_AMT,  "+
									" A.ALLOCATED_DATE ALLO_DATE "+
									" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
									" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
									" AND "+m_schema_name+".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='"+rs1.getString(1)+"' "+
									" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
									" AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
									" AND A.ALLOCATED_AMOUNT>0 "+
									" AND A.FACILITY_NO='"+m_facility_no+"' "+
									" AND A.CLIENT_CODE='"+m_client_no+"' "+
									//" UNION ALL "+
									" UNION  "+
									" SELECT  "+
									"  DISTINCT B.CLIENT_CODE CLIENT_CODE, "+
									" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) CNAME, "+
									" B.FACILITY_NO FACILITY_NO, "+
									" '-' CHEQUE_NO, "+
									" 0 CHQ_AMT, "+
									" '-' BRANCH_NAME, "+
									" A.INVOICE_NO INVOICE_NO, "+
									" A.INVOICE_AMOUNT INV_AMOUNT, "+
									" A.SETTLE_AMOUNT ALL_AMT, "+
									" A.BALANCE_AMOUNT BAL_AMT, "+
									" B.INVOICE_BATCH_DATE ALLO_DATE "+
									" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
									" WHERE A.BATCH_NO=B.BATCH_NO "+
									" AND A.SETTLE_AMOUNT=0 "+
									" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
									" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
									" AND B.FACILITY_NO='"+m_facility_no+"' "+
									" AND B.CLIENT_CODE='"+m_client_no+"' "+
									" AND A.DEBTOR_CODE='"+rs1.getString(1)+"' "+
									//" UNION ALL "+
									//Added by Dineth on 29-07-2009
									" UNION "+
									" SELECT "+
									" DISTINCT A.CLIENT_CODE CLIENT_CODE, "+
									" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)||' '||'Unallocated Rec' CNAME, "+
									" A.FACILITY_NO FACILITY_NO, "+
									" A.CHEQUE_NO CHEQUE_NO, "+
									" A.REC_AMOUNT CHQ_AMT, '' BRANCH_NAME ,"+
									//" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE) BRANCH_NAME, "+
									" '-' INVOICE_NO, "+
									" 0 INV_AMOUNT, "+
									" 0 ALL_AMT, "+
									" A.BALANCE_AMOUNT BAL_AMT, "+
									" A.EFF_VALDATE ALLO_DATE "+
									" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
									" WHERE A.REC_STATUS='Y'  "+
									" AND A.RECEIPT_TYPE<>'SSH' "+
									" AND A.FACILITY_NO='"+m_facility_no+"' "+
									" AND A.CLIENT_CODE='"+m_client_no+"' "+
									" AND A.DEBTOR_CODE='"+rs1.getString(1)+"' "+
									" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY')"+
									" AND TO_DATE(TO_CHAR(A.RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY')"+
									" AND A.BALANCE_AMOUNT>0 "+
									//End by Dineth on 29-07-2009
									" UNION  "+
									" SELECT  "+
									" DISTINCT B.CLIENT_CODE CLIENT_CODE, "+
									" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) CNAME, "+
									" B.FACILITY_NO FACILITY_NO, "+
									" '-' CHEQUE_NO, "+
									" 0 CHQ_AMT, "+
									" '-' BRANCH_NAME, "+
									" A.INVOICE_NO INVOICE_NO, "+
									" A.INVOICE_AMOUNT INV_AMOUNT, "+
									" 0 ALL_AMT, "+
									" A.BALANCE_AMOUNT BAL_AMT, "+
									" B.INVOICE_BATCH_DATE ALLO_DATE "+
									" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
									" WHERE A.BATCH_NO=B.BATCH_NO "+
									" AND A.BALANCE_AMOUNT>0 "+
									" AND B.FACILITY_NO='"+m_facility_no+"' "+
									" AND B.CLIENT_CODE='"+m_client_no+"' "+
									" AND A.DEBTOR_CODE='"+rs2.getString(3)+"' "+
									" AND A.INVOICE_SEQ_NO NOT IN( SELECT A.INVOICE_NO "+
									" 								FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
									" 								WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
									" 								AND "+m_schema_name+".FA_GET_INVOICE_DEBTOR(A.INVOICE_NO)='"+rs2.getString(3)+"' "+
									" 								AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date_from+"','DD-MM-YYYY') "+
									" 								AND TO_DATE(TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_date_to+"','DD-MM-YYYY') "+
									" 								AND A.ALLOCATED_AMOUNT>0 "+
									" 								AND A.FACILITY_NO='"+m_facility_no+"' "+
									" 								AND A.CLIENT_CODE='"+m_client_no+"') "+
									" ) ORDER BY ALLO_DATE ");
									
									while(rs.next()){
									 
										out.println("<tr><td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(11)+"</td>");
										out.println("<td width='20%' class='factoring-letter-body' align='left'>"+rs.getString(2)+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='left'>"+rs.getString(4)+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='left'>"+rs.getString(6)+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
										out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(7)+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(8))+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(9))+"</td>");
										out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(10))+"</td>");
										out.println("<td width='*%'></td>"); 
										out.println("</tr>");
									}
									rs.close();
									out.println("</table>");	
									out.println("<table border='0' width='100%' class=table >");
									//out.println("<tr><td width='*%' class='factoring-letter-body'>If you have any queries pertaining to the above please do not hesitate to contact the undersigned.</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'><br>Thanking you</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>Yours faithfully</td></tr>");
									
									out.println("<tr><td width='*%' class='factoring-letter-body'>Factoring Division of Lakderana Investments Limited</td></tr>");
									/*out.println("<tr><td width='*%' class='factoring-letter-body'><br><br><br></td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>..........................</td></tr>");
									out.println("<tr><td width='*%' class='factoring-letter-body'>Authorized Signatory<br>Executive Operations</td></tr>");*/
									out.println("</table>");	
								
									out.println("<p style=\"page-break-after:always\"></p>");		
							}
					}
				}
			 }
			 catch(Exception e)
			 {
					out.println(""+e.toString());
			 }	

			}
			else if (m_chksql.trim().equals("get_report_check")){
				out.println("<html><head><font 10pt arial><title>Debtor Sales Ledger</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				out.println("<table border='0' width='100%' class=table >");
				for(int i=1;i<(100);i++){
				//out.println("<p class='factoring-letter-body'>"+m_num_lines+"--"+(67-m_num_lines)+"</p>");//17
				out.println("<tr><td width='*%' class='factoring-letter-body'>"+i+"</td></tr>");
				}
				out.println("</table>");	
			}
			else if (m_chksql.trim().equals("get_report_bulk")){
				String m_client_no=req.getParameter("client_code").trim();
				String m_facility_no=req.getParameter("facility_no").trim();
				String m_date_from=req.getParameter("date_from").trim();
				String m_date_to=req.getParameter("date_to").trim();
				
				Bulk_Printing_Main debtor_st_print=new Bulk_Printing_Main();
				
				rs2 = stmt2.executeQuery(" SELECT DISTINCT FACILITY_NO,CLIENT_CODE,DEBTOR_CODE  "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
				" WHERE FACILITY_NO='"+m_facility_no+"' "+
				" AND CLIENT_CODE='"+m_client_no+"' "+
				" AND RELATION_STATUS<>'C' "+
				" ORDER BY CLIENT_CODE,DEBTOR_CODE");
				
				while(rs2.next()){
					int m_cknum=debtor_st_print.printing_interface_3(m_facility_no,m_client_no,m_date_from,m_date_to,rs2.getString(3));
					
					out.println("<html><head><font 10pt arial><title>Debtor Sales Ledger</title></head>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("<body leftmargin='0' topmargin='0' class=body>");
					out.println("<table border='0' width='100%' class=table >");
					if(m_cknum==1){
					out.println("<tr><td width='*%' class='factoring-letter-body'>Print Successfull "+rs2.getString(3)+"</td></tr>");
					}
					else{
					out.println("<tr><td width='*%' class='factoring-letter-body'>Error when Printing "+rs2.getString(3)+"</td></tr>");
					}
					out.println("</table>");
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

