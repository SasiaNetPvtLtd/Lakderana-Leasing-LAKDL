import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_display_receipt extends javax.servlet.http.HttpServlet {
	  
	Connection conn;
	Statement stmt,stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	java.text.NumberFormat nf1;
    
  public ResultSet rs,rs1,rs2;
	public String m_chksql;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url = m_sn_methods.html_client_url;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
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
			
			m_chksql=req.getParameter("chksql");

			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("display_receipt")){
			//------------------------------------------------------------------------------------
				String m_receipt_no=req.getParameter("RECEIPT_NO").trim();
				
				String m_debtor_no="";
				String m_client_no="";
				String m_facility_no="";
				String m_report_period="";
				String m_client_name="";
				String m_receipt_type="";
				String m_client_name2="";
				
				out.println("<html><head><font 10pt arial><title>RECEIPT DETAILS</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<script>");
				out.println("function print_data(){");
				out.println("  m_receipt_no='"+m_receipt_no+"'; ");
				out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_receipt_info?RECEIPT_NO="+m_receipt_no+"\";");  
				out.println("  window.location.href=m_url;"); 
				out.println("	 m_table.innerHTML=\"\" ");
				out.println("	 window.print();");
				out.println("}");
				out.println("function add_button(){");
				out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"print_data()\"></td></tr>';"); 
				out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
				out.println("m_writedata+'</table>';");
				out.println("}");
				
				out.println("</script>");	
				out.println("<body leftmargin='0' topmargin='0' class=body onLoad=\"add_button()\">");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  out.println("</tr>"); 
				out.println("</table>");
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><B>OFFICIAL RECEIPT</B></td></tr>");
				out.println("</table></blockquote>");	
			  out.println("</BR>");	
				rs1 = stmt1.executeQuery (" SELECT RECEIPT_NO,"+
				" DEBTOR_CODE,"+
				" CLIENT_CODE,"+
				" FACILITY_NO,"+
				" TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI'), "+
				" RECEIPT_TYPE, "+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) "+
  			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				" WHERE RECEIPT_NO='"+m_receipt_no+"'");
				
				while(rs1.next()){
				m_debtor_no=rs1.getString(2);
				m_client_no=rs1.getString(3);
				m_facility_no=rs1.getString(4);
				m_report_period=rs1.getString(5);
				m_receipt_type=rs1.getString(6);
				m_client_name2=rs1.getString(7);
				}
				
				if(!m_receipt_type.equals("CS")){
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-'), "+//7
						" NVL(VAT_REG_NO,'-') "+//8
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_debtor_no+"' ");
				}
				else{
				rs1 = stmt1.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-'), "+//7
						" NVL(VAT_REG_NO,'-') "+//8
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_no+"' ");
				}
				
				if(rs1.next()){
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'>ADDRESS</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("</table></blockquote>");
						out.println("<blockquote><table border='0' width='100%' class=table >");
						out.println("<tr><td width='20%' class='factoring-letter-body'><b>RECEIPT NO</b></td>");
						out.println("<td width='50%' class='factoring-letter-body'><b>"+m_receipt_no+"</b></td>");
						out.println("<td width='*%' class='factoring-letter-body'></b></td>");
						out.println("</tr>");	
						out.println("<tr><td width='20%' class='factoring-letter-body'><b>CLIENT CODE/NAME</b></td>");
						out.println("<td width='50%' class='factoring-letter-body'><b>"+m_client_no+"/"+m_client_name2+"</b></td>");
						out.println("<td width='*%' class='factoring-letter-body'></b></td>");
						out.println("</tr>");	
						out.println("<tr><td width='20%' class='factoring-letter-body'><b>DATE/TIME</b></td>");
						out.println("<td width='50%' class='factoring-letter-body'><b>"+m_report_period+"</b></td>");
						out.println("<td width='*%' class='factoring-letter-body'></b></td>");
						out.println("</tr>");	
						out.println("</table></blockquote>");	
						m_client_name=rs1.getString(2);
				}
				
				if(!m_receipt_type.equals("CS")){
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>RECEIVED WITH THANKS FROM "+m_client_name+", DEBTOR CODE:"+m_debtor_no+"</td></tr>");
				out.println("</table></blockquote>");	
				}
				else{
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>RECEIVED WITH THANKS FROM "+m_client_name+"</td></tr>");
				out.println("</table></blockquote>");	
				}
				
				rs = stmt.executeQuery (" SELECT A.RECEIPT_NO, "+//1
				" A.SETTLE_MODE, "+//2
				" NVL(A.PAYER_BRANCH_CODE,'-'), "+//3
				" NVL(A.PAYER_ACC_NO,'-'),"+//4
       	" NVL(A.REC_AMOUNT,0), "+//5
				" DECODE(A.SETTLE_MODE,'CHEQUE',CHEQUE_NO,A.SETTLE_MODE),"+//6
       	" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),"+//7
				" A.CLIENT_CODE,"+//8
				" A.FACILITY_NO,"+//9
				" A.INVOICE_NO,"+//10
       	" DECODE(A.SETTLE_MODE,'CHEQUE',"+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),A.SETTLE_MODE),"+//11
				" 0, "+//12
				" A.RECEIPT_TYPE, "+//13
				" A.SUS_REF_NO, "+//14
				" A.DEBTOR_CODE, "+//15
				" A.INVOICE_NO, "+//16
				" TO_CHAR(A.RECON_DATE,'DD-MM-YYYY') "+//17
  			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				" WHERE RECEIPT_NO='"+m_receipt_no+"'");
				
				out.println("<blockquote><table border='1' width='85%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>FACILITY NO</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>CHQ NO/CASH</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ DATE</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>REALISE/DEPOSIT DATE</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>BANK AND BRANCH</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ AMOUNT</b></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>DESCRIPTION</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>AMOUNT</b></td>");
				out.println("</tr>");
				double m_total=0;
				while(rs.next()){
					
					if(rs.getString(13).equals("POD")){
							
						rs2 = stmt2.executeQuery (" SELECT A.POD_REF_NO, A.INVOICE_NO,B.BALANCE_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE A.DEBTOR_CODE=B.DEBTOR_CODE "+
							" AND A.INVOICE_NO=B.INVOICE_NO  "+
							" AND A.POD_REF_NO='"+rs.getString(14)+"'");
						
						while(rs2.next()){
							out.println("<tr>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(7)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(17)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(11)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs2.getString(2)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs2.getDouble(3))+"</td>");
							out.println("</tr>");
						}
					}
					else if(rs.getString(13).equals("IS")){

						rs2 = stmt2.executeQuery (" SELECT B.INVOICE_NO,B.BALANCE_AMOUNT "+
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE B.DEBTOR_CODE='"+rs.getString(15)+"' "+
							" AND B.INVOICE_NO='"+rs.getString(16)+"'");
						
						while(rs2.next()){
							out.println("<tr>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(7)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(17)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(11)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs2.getString(1)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs2.getDouble(2))+"</td>");
							out.println("</tr>");
						}
					}
					else if(rs.getString(13).equals("IB")){
							
						rs2 = stmt2.executeQuery (" SELECT B.INVOICE_NO,B.ALLO_AMOUNT "+
							" FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO B "+
							" WHERE B.DEBTOR_CODE='"+rs.getString(15)+"' "+
							" AND B.RECEIPT_NO='"+m_receipt_no+"'");
						
						while(rs2.next()){
							out.println("<tr>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(7)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(17)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(11)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs2.getString(1)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs2.getDouble(2))+"</td>");
							out.println("</tr>");
						}
					}
					else{
							out.println("<tr>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(7)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(17)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(11)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>CLIENT SETTLEMENT</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(5))+"</td>");
							out.println("</tr>");
					}
					m_total=m_total+rs.getDouble(5);
				}
				out.println("<tr>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'>TOTAL</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'>**"+nf.format(m_total)+"</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>STAMP DUTY HAS BEEN COMPOUND IN TERMS OF SECTION 7 OF THE STAMP DUTY(SPECIAL PROVISIONS) ACT NO 12 OF 2006</td></tr>");
				out.println("</table></blockquote>");	
				String m_val = nf.format(m_total);
				String m_val2 = m_sn_methods.met_unformat_number(m_val);
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>AMOUNT IN WORDS RUPPES **"+numbersToChar_inside(m_val2)+"** ONLY</td></tr>");
				out.println("</table></blockquote>");	
				out.println("</blockquote>");
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>PLEASE INFORM ANY DISCREPANCIES WITHIN 7 DAYS</td></tr>");
				out.println("</table></blockquote>");	
				out.println("</blockquote>");
				out.println("<BR><BR><BR>");
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'>...........................</td></tr>");
				out.println("<tr><td width='*%' class='factoring-letter-body'>AUTHORISED SIGNATURE<BR>EXECUTIVE OPERATIONS</td></tr>");
				out.println("</table></blockquote>");	
				out.println("</blockquote>");
			
			}
			else if (m_chksql.trim().equals("display_deposit_slip")){
			//------------------------------------------------------------------------------------
				String m_receipt_no=req.getParameter("DEPOSIT_NO").trim();
				
				out.println("<html><head><font 10pt arial><title>RECEIPT DETAILS</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				
				rs1 = stmt1.executeQuery (" SELECT A.DEPOSIT_NO, "+
				" TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'), "+
				" A.DEPOSIT_COMMENTS, "+
				" A.ACC_NO,"+
       	" A.BRANCH_CODE,"+
				" "+m_schema_name+".AF_CO_GET_BANK_NAME(A.BRANCH_CODE),"+
				" A.DEPOSIT_TOTAL,"+ 
				" A.DEPOSIT_MODE "+
  			" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A "+
				" WHERE A.DEPOSIT_NO='"+m_receipt_no+"'");
				
				
			String m_deposit_date="";
			String m_deposit_acc_no="";
			String m_branch_code_name="";
			
			 if(rs1.next()){
						m_deposit_date=rs1.getString(2);
						m_deposit_acc_no=rs1.getString(4);
						m_branch_code_name=rs1.getString(6);
			 }
			
			double m_total=0;
				
			rs1 = stmt1.executeQuery (" SELECT DISTINCT NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH'),"+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE),SUM(A.DEPOSIT_AMOUNT) "+
			 " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
			 " WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
			 " AND A.DEPOSIT_NO='"+m_receipt_no+"'"+
			 " GROUP BY NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH'),"+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE)");
					
			while(rs1.next()){//10	
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><B>DEPOSIT SLIP</B></td></tr>");
				out.println("</table></blockquote>");	
			  out.println("</BR>");	
				out.println("<blockquote><table border='0' width='100%' class=table >");
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT NO</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_receipt_no+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT DATE</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_date+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>ACCOUNT NO</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_acc_no+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>BANK CODE AND NAME</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+rs1.getString(1)+" - "+rs1.getString(2)+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT TOTAL</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("</table></blockquote>");	

				out.println("<BR><BR><BR>");
				
				out.println("<blockquote><table border='1' width='65%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>RECEIPT NO</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ NO/CASH</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ DATE</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>BANK CODE</b>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'><b>BRANCH NAME</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>AMOUNT</b></td>");
				out.println("</tr>");
						
					rs = stmt.executeQuery (" SELECT A.DEPOSIT_NO, "+
						" A.RECEIPT_NO, "+
						" A.DEPOSIT_AMOUNT, "+
						" DECODE(B.SETTLE_MODE,'CHEQUE',A.CHEQUE_NO,B.SETTLE_MODE), "+
						" B.PAYER_BRANCH_CODE,"+
						" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),"+
						" B.PAYER_ACC_NO, "+
						" B.SETTLE_MODE, "+
						" NVL(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+
						" B.RECEIPT_TYPE, "+
						" B.SUS_REF_NO "+//11
						" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.DEPOSIT_NO='"+m_receipt_no+"'"+
						" AND  NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH')='"+rs1.getString(1)+"'"+
						" ORDER BY B.PAYER_BRANCH_CODE,A.RECEIPT_NO ");
					
					double m_tot_sub=0;
					int m_row_count=0;
					
					while(rs.next()){//10
							
							out.println("<tr>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(2)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(4)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
							out.println("<td width='15%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							out.println("</tr>");
							m_row_count++;
							m_tot_sub=m_tot_sub+rs.getDouble(3);
							
							if(m_row_count==7){
								out.println("<tr>");
								out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'>SUB TOTAL</td>");
								out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_tot_sub)+"</td>");
								out.println("</tr>");
								out.println("</table></blockquote>");	
								out.println("<p style=\"page-break-after:always\"></p>");		
								out.println("<blockquote>");
								out.println("<table border='0' width='100%' class=table >");
								out.println("<tr><td width='*%' class='factoring-letter-body'><B>DEPOSIT SLIP</B></td></tr>");
								out.println("</table></blockquote>");	
							  out.println("</BR>");	
								out.println("<blockquote><table border='0' width='100%' class=table >");
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT NO</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_receipt_no+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT DATE</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_date+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>ACCOUNT NO</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_acc_no+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>BANK CODE AND NAME</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+rs1.getString(1)+" - "+rs1.getString(2)+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT TOTAL</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+nf.format(rs1.getDouble(3))+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("</table></blockquote>");	

								out.println("<BR><BR><BR>");
								out.println("<blockquote><table border='1' width='65%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
								out.println("<td width='15%' class='factoring-letter-body' align='center'><b>RECEIPT NO</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ NO/CASH</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ DATE</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>BANK CODE</b>");
								out.println("<td width='15%' class='factoring-letter-body' align='center'><b>BRANCH NAME</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>AMOUNT</b></td>");
								out.println("</tr>");
								m_row_count=0;
								m_tot_sub=0;
							}
							m_total=m_total+rs.getDouble(3);
					}
					if(m_row_count!=7 && m_tot_sub>0){
						out.println("<tr>");
						out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='10%' class='factoring-letter-body' align='center'>SUB TOTAL</td>");
						out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_tot_sub)+"</td>");
						out.println("</tr>");
						m_row_count=0;
						m_tot_sub=0;
					}
					out.println("</table>");
					out.println("</blockquote>");
					out.println("<p style=\"page-break-after:always\"></p>");		
				}
				/*out.println("<tr>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'>TOTAL</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_total)+"</td>");
				out.println("</tr>");
				out.println("</table>");	
				out.println("</blockquote>");*/
			
			}
												
				//Added by Nuwan De Silva on 23-12-2009
				else if (m_chksql.trim().equals("display_deposit_slip_new")){
			//------------------------------------------------------------------------------------
				String m_receipt_no=req.getParameter("DEPOSIT_NO").trim();
				
				out.println("<html><head><font 10pt arial><title>RECEIPT DETAILS</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				//out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo_factor.gif\"></DIV><br>");
				out.println("<form name='form1'>");
				
				rs1 = stmt1.executeQuery (" SELECT A.DEPOSIT_NO, "+
				" TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'), "+
				" A.DEPOSIT_COMMENTS, "+
				" A.ACC_NO,"+
       	" A.BRANCH_CODE,"+
				//" "+m_schema_name+".AF_CO_GET_BANK_NAME(A.BRANCH_CODE),"+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(A.BRANCH_CODE),"+
				" A.DEPOSIT_TOTAL,"+ 
				" A.DEPOSIT_MODE "+
  			" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A "+
				" WHERE A.DEPOSIT_NO='"+m_receipt_no+"'");
				
				
			String m_deposit_date="";
			String m_deposit_acc_no="";
			String m_branch_code_name="";
			double m_deposit_total=0;	
			
			 if(rs1.next()){
						m_deposit_date=rs1.getString(2);
						m_deposit_acc_no=rs1.getString(4);
						m_branch_code_name=rs1.getString(6);
						m_deposit_total=rs1.getDouble(7);
						//m_branch_code_name=rs1.getString(6);
			 }
			
			double m_total=0;
			
			/*rs1 = stmt1.executeQuery (" SELECT DISTINCT NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH'),"+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE),SUM(A.DEPOSIT_AMOUNT) "+
			 " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
			 " WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
			 " AND A.DEPOSIT_NO='"+m_receipt_no+"'"+
			 " GROUP BY NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH'),"+m_schema_name+".AF_CO_GET_BANK_NAME(B.PAYER_BRANCH_CODE)");
			*/
				
				
			//while(rs1.next()){//10	
				out.println("<blockquote>");
				out.println("<table border='0' width='100%' class=table >");
				out.println("<tr><td width='*%' class='factoring-letter-body'><B>DEPOSIT SLIP</B></td></tr>");
				out.println("</table></blockquote>");	
			  out.println("</BR>");	
				out.println("<blockquote><table border='0' width='100%' class=table >");
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT NO</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_receipt_no+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT DATE</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_date+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>ACCOUNT NO</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_acc_no+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>BANK CODE AND NAME</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+m_branch_code_name+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				
				out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT TOTAL</b></td>");
				out.println("<td width='20%' class='factoring-letter-body'><b>"+nf.format(m_deposit_total)+"</b></td>");
				out.println("<td width='*%' class='factoring-letter-body'></b></td>");
				out.println("</tr>");	
				out.println("</table></blockquote>");	

				out.println("<BR>");
				
				out.println("<blockquote><table border='1' width='90%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
				out.println("<td width='3%' class='factoring-letter-body' align='center'><b>NO</b></td>");
				out.println("<td width='17%' class='factoring-letter-body' align='center'><b>RECEIPT NO</b></td>");
				out.println("<td width='8%' class='factoring-letter-body' align='center'><b>CHQ NO/CASH</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ DATE</b></td>");
				out.println("<td width='7%' class='factoring-letter-body' align='center'><b>BANK CODE</b>");
				out.println("<td width='30%' class='factoring-letter-body' align='center'><b>BRANCH NAME</b></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><b>AMOUNT</b></td>");
				out.println("</tr>");
						
					rs = stmt.executeQuery (" SELECT A.DEPOSIT_NO, "+
						" A.RECEIPT_NO, "+
						" A.DEPOSIT_AMOUNT, "+
						" DECODE(B.SETTLE_MODE,'CHEQUE',A.CHEQUE_NO,B.SETTLE_MODE), "+
						" B.PAYER_BRANCH_CODE,"+
						" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),"+
						" B.PAYER_ACC_NO, "+
						" B.SETTLE_MODE, "+
						" NVL(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+
						" B.RECEIPT_TYPE, "+
						" B.SUS_REF_NO "+//11
						" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
						" AND A.DEPOSIT_NO='"+m_receipt_no+"'"+
						//" AND  NVL(SUBSTR(B.PAYER_BRANCH_CODE,1,4),'CASH')='"+rs1.getString(1)+"'"+
						" ORDER BY B.PAYER_BRANCH_CODE,A.RECEIPT_NO ");
					
					double m_tot_sub=0;
					int m_row_count=0;
					int m_row_no=1;
					
					while(rs.next()){//10
							
							out.println("<tr>");
							out.println("<td width='3%' class='factoring-letter-body' align='center'>"+m_row_no+"</td>");
							out.println("<td width='17%' class='factoring-letter-body' align='center'>"+rs.getString(2)+"</td>");
							out.println("<td width='8%' class='factoring-letter-body' align='center'>"+rs.getString(4)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs.getString(9)+"</td>");
							out.println("<td width='7%' class='factoring-letter-body' align='center'>"+rs.getString(5)+"</td>");
							out.println("<td width='30%' class='factoring-letter-body' align='center'>"+rs.getString(6)+"</td>");
							out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
							out.println("</tr>");
							m_row_count++;
							m_row_no = m_row_no+1;
							m_tot_sub=m_tot_sub+rs.getDouble(3);
							
							if(m_row_count==30){
								/*out.println("<tr>");
								out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'>SUB TOTAL</td>");
								out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_tot_sub)+"</td>");
								out.println("</tr>");
								*/
								
								out.println("</table></blockquote>");	
								
								
								out.println("<p style=\"page-break-after:always\"></p>");		
								out.println("<blockquote>");
								out.println("<table border='0' width='100%' class=table >");
								out.println("<tr><td width='*%' class='factoring-letter-body'><B>DEPOSIT SLIP</B></td></tr>");
								out.println("</table></blockquote>");	
							  out.println("</BR>");	
								out.println("<blockquote><table border='0' width='100%' class=table >");
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT NO</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_receipt_no+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT DATE</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_date+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>ACCOUNT NO</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_deposit_acc_no+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>BANK CODE AND NAME</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+m_branch_code_name+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");	
								out.println("<tr><td width='20%' class='factoring-letter-body'><b>DEPOSIT TOTAL</b></td>");
								out.println("<td width='20%' class='factoring-letter-body'><b>"+nf.format(m_deposit_total)+"</b></td>");
								out.println("<td width='*%' class='factoring-letter-body'></b></td>");
								out.println("</tr>");
								out.println("</table></blockquote>");	
                
								out.println("<BR>");
								out.println("<blockquote><table border='1' width='90%' class=table cellpadding=\"3\" cellspacing=\"0\" >");
								out.println("<td width='3%' class='factoring-letter-body' align='center'><b>NO</b></td>");
								out.println("<td width='17%' class='factoring-letter-body' align='center'><b>RECEIPT NO</b></td>");
								out.println("<td width='8%' class='factoring-letter-body' align='center'><b>CHQ NO/CASH</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>CHQ DATE</b></td>");
								out.println("<td width='7%' class='factoring-letter-body' align='center'><b>BANK CODE</b>");
								out.println("<td width='30%' class='factoring-letter-body' align='center'><b>BRANCH NAME</b></td>");
								out.println("<td width='10%' class='factoring-letter-body' align='center'><b>AMOUNT</b></td>");
								out.println("</tr>");
								m_row_count=0;
								//m_tot_sub=0;
							}
							
							//m_tot_sub=m_tot_sub+rs.getDouble(3);
					}
					//if(m_row_count!=7 && m_tot_sub>0){
						out.println("<tr>");
						out.println("<td width='3%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='17%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='8%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='7%' class='factoring-letter-body' align='center'></td>");
						out.println("<td width='30%' class='factoring-letter-body' align='center'>SUB TOTAL</td>");
						out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_tot_sub)+"</td>");
						out.println("</tr>");
						//m_row_count=0;
						//m_tot_sub=0;
					//}
					out.println("</table>");
					out.println("</blockquote>");
					//out.println("<p style=\"page-break-after:always\"></p>");		
				//}
				/*out.println("<tr>");
				out.println("<td width='15%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'>TOTAL</td>");
				out.println("<td width='10%' class='factoring-letter-body' align='right'>"+nf.format(m_total)+"</td>");
				out.println("</tr>");
				out.println("</table>");	
				out.println("</blockquote>");*/
			
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
	
	public String numbersToChar_inside(String obj){

		Object[] ar_ones  = new Object[10];
		Object[] ar_tens  = new Object[10];
		Object[] ar_teens = new Object[10];
		
		ar_ones[0]        = "";
		ar_ones[1]        = "ONE";
		ar_ones[2]        = "TWO"; 
		ar_ones[3]		  =	"THREE";
		ar_ones[4]        = "FOUR";
		ar_ones[5]        = "FIVE";
		ar_ones[6]        = "SIX"; 
		ar_ones[7]		  =	"SEVEN";
		ar_ones[8]        = "EIGHT";
		ar_ones[9]        = "NINE";
		
		ar_tens[0]        = "";
		ar_tens[1]        = "TEN";
		ar_tens[2]        = "TWENTY"; 
		ar_tens[3]		  =	"THIRTY";
		ar_tens[4]        = "FORTY";
		ar_tens[5]        = "FIFTY";
		ar_tens[6]        = "SIXTY"; 
		ar_tens[7]		  =	"SEVENTY";
		ar_tens[8]        = "EIGHTY";
		ar_tens[9]        = "NINETY";
		
		ar_teens[0]        = "";
		ar_teens[1]        = "ELEVEN";
		ar_teens[2]        = "TWELVE"; 
		ar_teens[3]		   = "THIRTEEN";
		ar_teens[4]        = "FOURTEEN";
		ar_teens[5]        = "FIFTEEN";
		ar_teens[6]        = "SIXTEEN"; 
		ar_teens[7]		   = "SEVENTEEN";
		ar_teens[8]        = "EIGHTEEN";
		ar_teens[9]        = "NINETEEN";
        
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
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED BILLION ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED ";
					}
				}
			}
			
			else if (m_cnt==4) {  // 10,000,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" BILLION ";
						m_cnt+=1;
						i+=1;
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" BILLION ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" ";
					}
				}
			}
			
			else if (m_cnt==5) {  // 1,000,000,000
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" BILLION ";
				}
			}
			
			
			else if ((m_cnt)==6) {  // 100,000,000
				if (!m_digit.equals("0")) {
					if ((m_value.substring(m_cnt+1,m_cnt+2).equals("0")) && (m_value.substring(m_cnt+2,m_cnt+3).equals("0"))) { 
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED MILLION ";
						m_cnt+=2;
						i+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED ";
					}
				}
			}
			
			else if (m_cnt==7) {  // 10,000,000
				//System.out.println("m_value="+m_value+" m_value.substring(m_cnt,m_cnt+1)="+m_value.substring(m_cnt,m_cnt+1)+"  m_value.substring(m_cnt+1,m_cnt+2)="+m_value.substring(m_cnt+1,m_cnt+2));
				if (!m_digit.equals("0")) {
					//if ((m_value.substring(m_cnt,m_cnt+1).equals("1")) && (m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
					if ((m_value.substring(m_cnt,m_cnt+1).equals("1"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" MILLION ";
						m_cnt+=1;
						i+=1;
						//System.out.println("m_full_str="+m_full_str);
					}
					else if ( Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" MILLION ";
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
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" MILLION ";
				}
			}
			
			else if ((m_cnt)==9) {  // 100,000
				if (!m_digit.equals("0")) {
					if ((Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (Integer.parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED THOUSAND ";
						m_cnt+=2;
						i+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED ";
					}
				}
			}
			
			else if ((m_cnt)==10) {  // 10,000
				if (!m_digit.equals("0")) {
					if (Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[Integer.parseInt(m_digit)]+" THOUSAND ";
						m_cnt+=1;
						i+=1;
						//m_cnt+=2;
					}
					else if ((m_digit.equals("1")) && (!m_value.substring(m_cnt+1,m_cnt+2).equals("0"))) { 
						m_full_str=m_full_str+ar_teens[Integer.parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" THOUSAND ";
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
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" THOUSAND ";
				}
			}
			
			else if ((m_cnt)==12) {  
				if (!m_digit.equals("0")) {
					m_full_str=m_full_str+ar_ones[Integer.parseInt(m_digit)]+" HUNDRED ";
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
		
		String m_cents_str="";
		m_cnt=0;
		if (!m_cents.equals("")){
			for (int i=m_cnt; i<=1; i++) {			
				m_digit = m_cents.substring(m_cnt,m_cnt+1);
				if ((m_cnt)==0) {  // 10
					if (!m_digit.equals("0")) {
						if ((m_digit.equals("1")) && (!m_cents.substring(m_cnt+1,m_cnt+2).equals("0"))) {
							if(!m_cents_str.toString().equals(""))
							{
								m_cents_str=m_cents_str+"AND "+ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							else 
							{
								m_cents_str=ar_teens[Integer.parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+"";
							}
							m_cnt+=1;
						}
						else {
							m_cents_str=m_cents_str+"AND "+ar_tens[Integer.parseInt(m_digit)]+"";
						}
					}
				}
				
				else if ((m_cnt)==1) {  // 1
					if (!m_digit.equals("0")) {
						if (m_cents.substring(m_cnt-1,m_cnt).equals("0")) {
							if(!m_cents_str.toString().equals(""))
							{
								m_cents_str=m_cents_str+"AND "+ar_ones[Integer.parseInt(m_digit)]+"";						
							}
							else
							{
								m_cents_str=ar_ones[Integer.parseInt(m_digit)]+"";		
							}
						}
						else {
							m_cents_str=m_cents_str+ar_ones[Integer.parseInt(m_digit)]+"";	
						}
					}
				}
				
				
				m_cnt++;
				if (m_cnt>1){
					if(!m_cents_str.equals("")){
					m_full_str=m_full_str +" "+m_cents_str+" CENTS ";
					}
				}
				
			}
		}
		
		return m_full_str.toString();
	}
}

