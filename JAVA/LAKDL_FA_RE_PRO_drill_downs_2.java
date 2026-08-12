import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_RE_PRO_drill_downs_2 extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs,rs1,rs2;
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
			stmt=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	

			else if(m_chksql.equals("SHOW_INVOICE_DETAIL_DRILL")){ 
				
				String m_string="";				
				
				String m_invoice_no=req.getParameter("invoice_no");
				String m_debtor_code=req.getParameter("debtor_no");

				rs= stmt1.executeQuery(" SELECT  "+
				" A.BATCH_NO,   "+//1
				" A.DEBTOR_CODE,  "+//2
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//3
				" A.INVOICE_NO,  "+//4
				" NVL(A.INVOICE_AMOUNT,0),  "+//5
				" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//6
				" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),  "+//7
				" TO_CHAR(A.TOLARENCE_END_DATE,'DD-MM-YYYY'),  "+//8
				"	NVL(ADJUSTMENT_AMOUNT,0),"+//9
				"	NVL(NET_INVOICE_AMOUNT,0),"+//10
				"	NVL(SETTLE_AMOUNT,0),"+//11
				"	NVL(CURR_CODE,'-'),"+//12
				"	NVL(EXCHANGE_RATE,0),"+//13
				"	NVL(AMOUNT_RPT_CURR,0),"+//14
				"	NVL(EXCHANGE_GAIN_LOSS,0),"+//15
				" NVL(A.INVOICE_COMMENTS,'-'),  "+//16
				" DECODE(A.INVOICE_STATUS,'ENTER','Enter','APPR1','Approve','CANCEL','Disapprove','CONF','Approved','APP_C','Approve Level Cancel','APP_2','Approve Level 1 Approval','APP_C2','Credit Approval Cancelation'), "+//17
				" NVL(A.ENT_USER,'-'),"+//18
				" TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'),"+//19
				" NVL(A.MOD_USER,'-'),"+//20
				" NVL(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY HH24:MI'),' '),"+//21
				" NVL(A.APP_USER,'-'),"+//22
				" TO_CHAR(A.APP_DATE,'DD-MM-YYYY HH24:MI'),"+//23
				" NVL(A.APPROVAL_COMMENTS,'-'), "+//24
				" INVOICE_SEQ_NO, "+//25
				" BALANCE_AMOUNT, "+//26
				" NVL(A.CLIENT_CODE,'-') , "+//27 	//-------ADDED BY ASHINI ON 12-02-2008----------------------------------
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-') "+//28 	//-------ADDED BY ASHINI ON 12-02-2008----------------------------------
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A  "+
				" WHERE A.INVOICE_NO='"+m_invoice_no+"' AND A.DEBTOR_CODE='"+m_debtor_code+"'");
				
				out.println("<HTML><HEAD><TITLE>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD><CENTER><B>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
				String m_inv_seq_no="";
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Batch No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//-------ADDED BY ASHINI ON 12-02-2008----------------------------------
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(27)+" - "+rs.getString(28)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					//-------END MODIFICATIONS DONE BY  ASHINI ON 12-02-2008------------------
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Debtor Name</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+" - "+rs.getString(3)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(17)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Amount Rs.</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Due Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Tolerance End Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Adjustment Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(9))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Net Invoice Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Settle Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Balance Amount</b></td>");
					out.println("<td width='50%' class=div_input><b>"+nf.format(rs.getDouble(26))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Rate</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(13))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(14))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Exchange Gain Loss</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(15))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Comment</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered User</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(18)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Entered Date/Time</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(19)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					if(!rs.getString(11).equals("-")){
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified User</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(20)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Last Modified Date/Time</b></td>");
						out.println("<td width='50%' class=div_input><b>"+rs.getString(21)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
					}
					if(!rs.getString(13).equals("-")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approve User</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approve Date/Time</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Approve Comments</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					m_inv_seq_no=rs.getString(25);
					//End of modification on 01-01-2007
					}
					out.println("</table>");
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='*%' class=div_input><b>Settlement Details</b></td>");
					out.println("</tr>");
					out.println("</table>");
					rs.close();
					
					rs= stmt1.executeQuery(" SELECT A.RECEIPT_NO,"+
							" A.RECEIPT_AMOUNT,"+
							" A.ALLOCATED_AMOUNT,"+
							" TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY') "+
							" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A "+
							" WHERE INVOICE_NO='"+m_inv_seq_no+"' "+
							" ORDER BY A.ALLOCATED_DATE ");
							
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='10%' class=div_input><b>Allo Amount</b></td>");
					out.println("<td width='10%' class=div_input><b>Allo Date</b></td>");
					out.println("</tr>");
					while(rs.next()){
					out.println("<tr>");
					out.println("<td width='15%' onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand'><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='10%' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("</tr>");				
					}
					out.println("</table>");
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='50%' class=div_input><b><u>Followup Comments</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					rs=stmt1.executeQuery(" SELECT INVOICE_SEQ_NO, "+
					" ENT_USER, "+
					" TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS'), "+
					" NVL(COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
					" WHERE INVOICE_SEQ_NO='"+m_inv_seq_no+"' "+
					" ORDER BY ENT_DATE DESC ");
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input><b>Enter User</b></td>"); 
					out.println("<td width=\"25%\" class=div_input><b>Enter Date/Time</b></td>"); 
					out.println("<td width=\"60%\" class=div_input><b>Comments</b></td>"); 
					out.println("</tr>");
					
					while(rs.next()){
					out.println("<tr >");
					out.println("<td width=\"20%\" class=div_input>"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"25%\" class=div_input>"+rs.getString(3)+"</td>"); 
					out.println("<td width=\"60%\" class=div_input>"+rs.getString(4)+"</td>"); 
					out.println("</tr>");
					}
					out.println("</table>"); 
					out.println("<BR>");
				}
				else{
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2'>");
					out.println("<TR><TD class=div_input><CENTER><B>Invoice Details - Invoice No: "+m_invoice_no+" Debtor Code: "+m_debtor_code+"</B></TD></TR>");
					out.println("</TABLE>");
				}
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			}
			
			
				else if(m_chksql.equals("SHOW_SETTLE_DETAIL_DRILL")){
				
				String m_rec_no=req.getParameter("rec_no");

				
				rs1= stmt1.executeQuery("SELECT  "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),"+//1
					" A.RECEIPT_NO, "+//2
					" A.REC_AMOUNT, "+//3
					" A.RE_BANK_RECEIPT_NO, "+ //4
					" A.RE_BANK_REC_AMOUNT, "+ //5
					" A.ALLO_REC_AMOUNT, "+ //6
					" DECODE(B.SETTLE_MODE,'CHEQUE',B.SETTLE_MODE ||'-'||B.CHEQUE_NO,B.SETTLE_MODE) "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
					" AND B.REBANK_STATUS IN('N','R') "+
					" AND A.RE_BANK_RECEIPT_NO ='"+m_rec_no+"' "+
					//" AND DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)='"+m_debtor_code+"' "+
					" ");
							
					out.println("<HTML><HEAD><TITLE>UNSETTLE CHEQUE RETURN</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>UNSETTLE CHEQUE RETURN</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
					out.println("</tr>");
					
					int j=1;
					double m_val=0;
					double m_val1=0;
					double m_val2=0;
					
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
						
						out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
						//out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(2)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(6))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='20%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
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


