import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:15-02.2008

public class LAKDL_FA_MISF_Unallocated_fund_reports extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String header_name=m_sn_methods.header_name.trim(); 
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
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			else if(m_chksql.equals("LOAD_RETURN_SETTLE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_code=req.getParameter("facility_no");
				
				out.println("<HTML><HEAD><TITLE>Cheques in Hand Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B>Unallocated Funds Detail Report for "+m_facility_code+" Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total Unallocated Funds Detail Report</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='10%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Tot Receipt Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Tot Allocated Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Tot Unallocated Amount</b></DIV></td>");
				out.println("</tr>");
			
				rs1= stmt1.executeQuery(" SELECT "+
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+ //1
					" SUM(REC_AMOUNT), "+ //2
					" SUM(NVL((BALANCE_AMOUNT),0)), "+ //3
					" SUM(ALLO_AMOUNT), "+ //4
					" DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) "+ //5
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND BALANCE_AMOUNT>0 "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" GROUP BY DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) "+
					" ORDER BY DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) ");
					

				
				int j=1;
				double m_temp1=0;
				double m_temp2=0;
				double m_temp3=0;
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_temp1=m_temp1+rs1.getDouble(2);
					m_temp2=m_temp2+rs1.getDouble(4);
					m_temp3=m_temp3+rs1.getDouble(3);
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"show_unallocated_detail('"+rs1.getString(5)+"','"+m_facility_code+"','"+m_from_date+"','"+m_to_date+"')\" >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"show_unallocated_detail('"+rs1.getString(5)+"','"+m_facility_code+"','"+m_from_date+"','"+m_to_date+"')\" >"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"show_unallocated_detail('"+rs1.getString(5)+"','"+m_facility_code+"','"+m_from_date+"','"+m_to_date+"')\" >"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("</tr>");
				}
				
				out.println("<tr>");
				out.println("<td width='10%' class=div_input ><B>Total</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp3)+"</B></td>");
				out.println("</tr>");
				out.println("</table>");

  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
	    }
			
			
		else if(m_chksql.equals("SHOW_UNALLOCATED_FUND_DETAIL_DRILL")){
				
			
				String m_code=req.getParameter("debtor_client_no");
				String m_facility_code=req.getParameter("facility_no");
				String m_from_date=req.getParameter("m_from");
				String m_to_date=req.getParameter("m_to");
			//	out.println("m_code" +m_code);
			//	out.println("m_facility_code" +m_facility_code);
			//	out.println("m_from_date" +m_from_date);
			//	out.println("m_to_date" +m_to_date);


				out.println("<HTML><HEAD><TITLE>Cheques in Hand Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B>Unallocated Funds Detail Report for "+m_facility_code+" Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total Unallocated Funds Detail Report</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Settle Details</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Receipt Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("</tr>");
			
		     rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0), "+
					" SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET, "+
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+
					" DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) ='"+m_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND BALANCE_AMOUNT>0 "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" ORDER BY EFF_VALDATE DESC ");
				int j=1;
				double m_temp1=0;
				while(rs1.next()){
				
			//			out.println("here" );

				
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
						m_temp1=m_temp1+rs1.getDouble(4);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"show_unallocated_invoice_details('"+m_facility_code+"','"+rs1.getString(7)+"')\"  >"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"show_unallocated_invoice_details('"+m_facility_code+"','"+rs1.getString(7)+"')\"  >"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");

				}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><B>Total</B></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
			}			
			
			
			else if(m_chksql.equals("SHOW_UNALLOCATED_INVOICE_DETAIL_DRILL")){

        String m_client_code=req.getParameter("debtor_client_no");
				String m_facility_no=req.getParameter("facility_no");					
						
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO, "+//1
							" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),"+//2
							" A.INVOICE_AMOUNT,"+//3
							" A.SETTLE_AMOUNT,"+//4
							" A.BALANCE_AMOUNT, "+//5
							" A.DEBTOR_CODE, "+//6
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) CLIENT_NAME, "+//7
							" A.INVOICE_NO "+//8
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
							" WHERE A.INVOICE_STATUS='CONF' "+
							" AND A.BATCH_NO=B.BATCH_NO "+
							" AND B.FACILITY_NO='"+m_facility_no+"' "+
							" AND B.CLIENT_CODE='"+m_client_code+"' "+
					   // " AND DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE) ='"+m_code+"' "+
							" AND A.BALANCE_AMOUNT<>0 "+
							" ORDER BY A.DUE_DATE ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Settlement Amount</b></DIV></td>");
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
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(6)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(6)+"','"+rs1.getString(8)+"')\"><u>"+rs1.getString(8)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");

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



