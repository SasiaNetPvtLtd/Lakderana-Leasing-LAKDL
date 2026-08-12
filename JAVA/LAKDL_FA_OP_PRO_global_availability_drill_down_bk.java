/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : DINETH FOR OFSCL FACTORING    DATE:2008-09-09
  
public class LAKDL_FA_OP_PRO_global_availability_drill_down_bk extends javax.servlet.http.HttpServlet {
	
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

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.trim().equals("LOAD_GLOBAL_AVAILABILITY_DRILL"))
			{

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				
				if(m_option_no.equals("OPT1")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT B.DEBTOR_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2
						" NVL(SUM(B.BALANCE_AMOUNT),0) "+//3
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D001'),"+//4
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D002'),"+//5
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D003') "+//6
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND B.REFACTOR_COUNT<=2 "+
						" GROUP BY B.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) ");
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D001'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D002'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D003') ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_1&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>ACTIVE INVOICE BATCHES TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>OFSCL Assignment Letter</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_code+"','"+rs1.getString(1)+"')\">"+nf.format(rs1.getDouble(3))+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(4)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(5)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					//out.println("<td width='15%' class=div_input align=right></td>");
					//out.println("<td width='15%' class=div_input align=right></td>");
					//out.println("<td width='15%' class=div_input align=right></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT2")){
				
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT "+//8
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') "+
						" ORDER BY B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_NO ");
								
					out.println("<HTML><HEAD><TITLE>PENDING APPROVAL INVOICES TOTAL</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>PENDING APPROVAL INVOICES TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Invoice Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
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
				
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT, "+//8
						" NVL(B.APPROVAL_COMMENTS,' ') "+//9
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.INVOICE_STATUS='CANCEL' "+
						" ORDER BY A.INVOICE_BATCH_DATE DESC,B.DEBTOR_CODE,B.INVOICE_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>DISAPPROVAL INVOICES TOTAL</B></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Invoice Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT4")){
				
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT B.DEBTOR_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2
						" NVL(SUM(B.BALANCE_AMOUNT),0) "+//3
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND (B.TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  "+
						" GROUP BY B.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_2&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>DUE DATE EXCEEDED INVOICE TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_code+"','"+rs1.getString(1)+"')\">"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
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
							" RESERVE_MARGIN, "+//5
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//6
							" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D001'),"+//7
						  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D002'),"+//8
						  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D003'),"+//9
							" "+m_schema_name+".FA_DEBTOR_ACTIVE_STATUS(DEBTOR_CODE), "+//10
							" "+m_schema_name+".FA_DEBTOR_RELATION_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),"+//11
							" "+m_schema_name+".FA_DEBTOR_RELATION_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE), "+//12
							" NVL("+m_schema_name+".FA_DEBTOR_CLIENT_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),'-') "+//13
	         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
	     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
	         		" AND CLIENT_CODE='"+m_client_code+"' "+
							" ORDER BY "+m_schema_name+".FA_DEBTOR_RELATION_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),DEBTOR_CODE ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_debtor) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_5&DEBTOR_CODE=\"+m_debtor;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</SCRIPT>"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>GROSS-PAYABLE AMOUNT</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='5%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Payable Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Company Assignment Letter</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>");
					//------
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Status</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Relationship</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Remarks</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Credit Comments</b></DIV></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("</tr>");
					
					int chk_nums=1;
					
					while(rs2.next()){
				
						rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+//1
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE "+
							" A.BATCH_NO=B.BATCH_NO "+
							" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
							" AND A.FACILITY_NO='"+m_facility_code+"' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.BALANCE_AMOUNT>0 "+
							" AND B.INVOICE_STATUS='CONF' ");
		
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
							out.println("<td width='5%' class=div_input align=center>"+rs2.getString(3)+"</td>");
							out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs2.getString(3)+"')\"><u>"+rs2.getString(6)+"</u></td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1))+"</td>");
							if(rs2.getDouble(4)<rs1.getDouble(1)){
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							else{
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs1.getDouble(1)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(8)+"</td>");
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(9)+"</td>");
							//-----------------------------
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(10)+"</td>");//--
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(11)+"</td>");//---
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input align=center style='cursor:hand' onClick=\"drill_down_sub('"+rs2.getString(3)+"')\">"+rs2.getString(13)+"</td>");
							//out.println("<td width='10%' class=div_input align=center><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option></select><input type=\"button\" style=\"width:30px\" class='txt_input' onClick='before_submit("+chk_nums+")' value=\"Save\"></td>");
							out.println("</tr>");
							//chk_nums++;
						}
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='5%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
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
							" RESERVE_MARGIN, "+//5
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) "+//6
	         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
	     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
	         		" AND CLIENT_CODE='"+m_client_code+"' "+
							" ORDER BY DEBTOR_CODE ");
					
					out.println("<HTML><HEAD><TITLE>PAYABLE AMOUNT FROM DUE</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>PAYABLE AMOUNT FROM DUE</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Payable Amount</b></DIV></td>");
					out.println("</tr>");
					
					while(rs2.next()){
				
						rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE "+
							" A.BATCH_NO=B.BATCH_NO "+
							" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
							" AND A.FACILITY_NO='"+m_facility_code+"' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.BALANCE_AMOUNT>0 "+
							" AND B.INVOICE_STATUS='CONF' "+
							" AND (B.TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
		
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
							out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs2.getString(3)+"')\"><u>"+rs2.getString(6)+"</u></td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1))+"</td>");
							if(rs2.getDouble(4)<rs1.getDouble(1)){
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							else{
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs1.getDouble(1)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
						}
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT7")){
	
					rs1= stmt1.executeQuery("SELECT  "+
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),"+//1
							" SUM(B.REC_AMOUNT), "+//2
							" SUM("+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)), "+//3
							" SUM(B.REC_AMOUNT)-SUM("+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)), "+//4
							" DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE) "+//5
							" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
							" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
							" AND B.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.FACILITY_NO='"+m_facility_code+"' "+
							" AND B.REBANK_STATUS IN('N','R') "+
							" AND A.REALIZE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')  "+
							" AND (B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO))>0 "+
							" GROUP BY DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE) ");
							
					out.println("<HTML><HEAD><TITLE>UNSETTLE CHEQUE RETURN</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_debtor_code) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_7&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"&DEBTOR_CODE=\"+m_debtor_code;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("function drill_down_sub_total() {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_7_ALL&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"\";");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>UNSETTLE CHEQUE RETURN</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Return Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Balance Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						m_val1=m_val1+rs1.getDouble(3);
						m_val2=m_val2+rs1.getDouble(4);
						
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\">"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right >"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>"+nf.format(m_val2)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT8")){
					double m_val=0;
					double m_val1=0;
				
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL CHARGES</B></u></center></p>");
					out.println("<br>");
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL TAX CHARGES</B></u></center></p>");
					out.println("<br>");
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
				
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL PENDING PAYMENTS</B></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT, "+
							" NVL(APP_COMMENTS,' ') "+
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
							" PAYMENT_AMOUNT, "+
							" NVL(APP_COMMENTS,' ') "+
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
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT11")){
					double m_val=0;
					double m_val1=0;
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL PAYMENTS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Amount</b></DIV></td>");
					out.println("</tr>");
				
					if(rs2.next()){
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT, "+
							" NVL(APP_COMMENTS,' ') "+
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
							" PAYMENT_AMOUNT, "+
							" NVL(APP_COMMENTS,' ') "+
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
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT12")){
					double m_val=0;
					double m_val1=0;
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>INTEREST NORMAL</B></u></center></p>");
					out.println("<br>");
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
					out.println("<tr bgcolor=\"#999966\">");
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
					out.println("<p class=pdn_txtpos2><center><u><b>RECEIVED FROM ACTIVE INVOICES</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Allo Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
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
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT<=2 "+ 
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
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT<=2 "+ 
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(11)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(10)+"')\"><u>"+rs1.getString(10)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
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
					out.println("<p class=pdn_txtpos2><center><u><b>RECEIVED FROM INACTIVE INVOICES</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Allo Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
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
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT>2 "+ 
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
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND AND B.REFACTOR_COUNT>2 "+ 
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(11)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(10)+"')\"><u>"+rs1.getString(10)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right><b>"+nf.format(rs1.getDouble(8))+"</b></td>");
						out.println("<td width='10%' class=div_input align=right><b>"+nf.format(rs1.getDouble(9))+"</b></td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
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
					out.println("<p class=pdn_txtpos2><center><u><b>UNALLOCATED FUNDS</b></u></center></p>");
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

					if(rs2.next()){
					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0), "+
					" SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET, "+
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND BALANCE_AMOUNT>0 "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY EFF_VALDATE DESC ");
					}
					else{
					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
					" REC_AMOUNT, "+
					" NVL((BALANCE_AMOUNT),0), "+
					" SETTLE_MODE || '-'  || NVL(CHEQUE_NO,'') SET_DET, "+
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND BALANCE_AMOUNT>0 "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
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
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT16")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_type) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_16&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"&R_TYPE=\"+m_type;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("function drill_down_sub_total() {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_16_ALL&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"\";");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CHEQUES BANKED PENDING REALISATION</B></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt Category</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT DECODE(RECEIPT_TYPE,'CS','On Account of Client','POD','Post Dated Cheques','On Account of Debtor'),"+
					" DECODE(RECEIPT_TYPE,'CS','CS','POD','POD','DS'), "+
					" NVL(SUM(BALANCE_AMOUNT),0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND SETTLE_MODE='CHEQUE' "+
					" AND REC_STATUS='B' "+
					" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" GROUP BY RECEIPT_TYPE ");
							
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(2)+"')\">"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(2)+"')\">"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT17")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_debtor) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_17&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"&DEBTOR_CODE=\"+m_debtor;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("function drill_down_sub_total() {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_17_ALL&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"\";");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL UNBANKED POD CHEQUES IN HAND</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Cheque Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT "+
					" DEBTOR_CODE,"+
					" DEBTOR_NAME,"+
					" SUM(CHQ_AMOUNT) "+
					" FROM( "+
					" SELECT A.POD_REF_NO,"+
					" A.DEBTOR_CODE DEBTOR_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) DEBTOR_NAME,"+
					" NVL(A.CHEQUE_AMOUNT,0) CHQ_AMOUNT "+
					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A "+
					" WHERE A.POD_STATUS='N' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" ) "+
					" GROUP BY DEBTOR_CODE,DEBTOR_NAME "+
					" ORDER BY DEBTOR_CODE ");
							
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(1)+"')\">"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input  style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right  style='cursor:hand' onClick=\"drill_down_sub_total()\"><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT18")){
					
					double m_val=0;
					double m_val1=0;
					
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
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND TO_DATE(TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
						" AND (TO_DATE(TO_CHAR(B.TOLARENCE_END_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'))<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')+7 "+
						" ORDER BY B.DUE_DATE DESC ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL INVOICES FALLING DUE WITHIN 7 DAYS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
				
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE>CHARGES AND TAX DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u>CHARGES AND TAX DETAILS</u></center></p>");
					out.println("<br>");
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick='show_charges_details(\""+rs1.getString(1)+"\")'><u>"+rs1.getString(1)+"</u></td>");
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
					
					double m_val=0;
					double m_val1=0;
				
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OVERPAIED INTEREST</B></u></center></p>");
					out.println("<br>");
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
					out.println("<tr bgcolor=\"#999966\">");
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
					
					double m_val=0;
					double m_val1=0;
				
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CLIENT ADJUSTMENT DETAILS</B></u></center></p>");
					out.println("<br>");
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
					out.println("<tr bgcolor=\"#999966\">");
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
					
					double m_val=0;
					double m_val1=0;
				
					String m_st="";
					String m_end="";
					
					rs1= stmt1.executeQuery("SELECT TO_CHAR(LAST_MONTH_ST_PROCESS,'DD-MM-YYYY'),TO_CHAR(LAST_MONTH_END_PROCESS,'DD-MM-YYYY') "+
 					" FROM "+m_schema_name+".FA_OP_MONTHEND_ROUTINE ");		
					
					if(rs1.next()){
					m_st=rs1.getString(1);
					m_end=rs1.getString(2);
					}
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<p class=pdn_txtpos2><center><u><b>OPEN BALANCES DETAILS</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Amount</b></DIV></td>");
					out.println("</tr>");
					
					double m_open1=0;
					rs1= stmt1.executeQuery("SELECT SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE  "+
					" WHERE  FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_st+"','DD-MM-YYYY') ");		
					
					if(rs1.next()){
					m_open1=rs1.getDouble(1);
					}	
					
					rs1= stmt1.executeQuery("SELECT INITCAP(PROC_DESC),"+
					" SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE  "+
					" WHERE  FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_st+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_st+"','DD-MM-YYYY')) "+
					" GROUP BY PROC_DESC "+
					" ORDER BY PROC_DESC ");						
				 
					int j=1;
					
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					out.println("<td width='15%' class=div_input >Openning Balance as at "+m_st+"</td>");
					if(m_open1>=0){
					out.println("<td width='15%' class=div_input align=right style='cursor:hand' >"+nf.format(m_open1)+"</td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right style='cursor:hand' >("+nf.format(m_open1*-1)+")</td>");
					}
					out.println("</tr>");
						
					m_val=m_val+m_open1;
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
						if(rs1.getDouble(2)>=0){
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' >"+nf.format(rs1.getDouble(2))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' >("+nf.format(rs1.getDouble(2)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ><b>Closing Balance</b></td>");
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
				if(m_option_no.equals("OPT23")){
					double m_val=0;
					double m_val1=0;
				
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT, "+//8
						" NVL(B.APPROVAL_COMMENTS,' ') "+//9
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.INVOICE_STATUS='CONF' "+
						//" ORDER BY A.INVOICE_BATCH_DATE DESC,B.DEBTOR_CODE "); //Commented by Dineth on 2008-09-04
							" ORDER BY B.BATCH_NO,A.INVOICE_BATCH_DATE ASC,B.DEBTOR_CODE");// Modified by Dineth
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u>APPROVED INVOICE TOTAL</u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT24")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("SELECT A.BATCH_NO, "+//1
						" NVL(A.TOTAL_BATCH_AMOUNT,0),"+//2
						" A.TOTAL_BATCH_INVOICES, "+//3
						" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY')"+//4
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
						" WHERE "+
						" A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" ORDER BY A.BATCH_NO,A.INVOICE_BATCH_DATE DESC ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL INVOICE LIST</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>No of Invoices</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch Date</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Batch Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT26")){
					double m_val=0;
					double m_val1=0;
	
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
						" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
						" WHERE "+
						" CLIENT_CODE='"+m_client_code+"' "+
						" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					if(rs2.next()){
						rs1= stmt1.executeQuery("SELECT  "+
						" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//1
						" REC_AMOUNT,"+//2
						" BALANCE_AMOUNT,"+//3
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+//4
						" TO_CHAR(RECON_DATE,'DD-MM-YYYY'),"+//5
						" DECODE(SETTLE_MODE,'CASH',SETTLE_MODE,SETTLE_MODE||'-'||NVL(CHEQUE_NO,'-')), "+//6
						" RECEIPT_NO "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
						" WHERE "+
						" FACILITY_NO='"+m_facility_code+"' "+
						" AND CLIENT_CODE='"+m_client_code+"' "+
						" AND RECON_STATUS='Y' "+
						" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>TO_DATE('"+rs2.getString(1)+"','DD-MM-YYYY') "+
						" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')"+
						" ORDER BY RECON_DATE DESC ");
					}
					else{
						rs1= stmt1.executeQuery("SELECT  "+
						" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//1
						" REC_AMOUNT,"+//2
						" BALANCE_AMOUNT,"+//3
						" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+//4
						" TO_CHAR(RECON_DATE,'DD-MM-YYYY'),"+//5
						" DECODE(SETTLE_MODE,'CASH',SETTLE_MODE,SETTLE_MODE||'-'||NVL(CHEQUE_NO,'-')), "+//6
						" RECEIPT_NO "+//7
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
						" WHERE "+
						" FACILITY_NO='"+m_facility_code+"' "+
						" AND CLIENT_CODE='"+m_client_code+"' "+
						" AND RECON_STATUS='Y' "+
						" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')"+
						" ORDER BY RECON_DATE DESC ");
					}
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL COLLECTIONS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amt</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amt</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Value Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Realise Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						m_val1=m_val1+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT27")){
					double m_val=0;
					double m_val1=0;

					rs1= stmt1.executeQuery("SELECT RECEIPT_NO, "+
						" DECODE(SETTLE_MODE,'CASH','CASH',SETTLE_MODE ||'-'||CHEQUE_NO), "+
						" REC_AMOUNT, "+
						" BALANCE_AMOUNT,"+
						" NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+
						" NVL(RECEIPT_COMMENTS,'-') "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
						" WHERE REC_STATUS='E' "+
						" AND RECEIPT_TYPE='CS' "+
						" AND CLIENT_CODE='"+m_client_code+"' "+
						" AND FACILITY_NO='"+m_facility_code+"' "+
						" ORDER BY CHEQUE_DATE ");

					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>COLLECTIONS ON ACCOUNT OF CLIENT</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Ref No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amt</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amt</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						m_val1=m_val1+rs1.getDouble(4);
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT28")){
					double m_val=0;
					double m_val1=0;

					rs1= stmt1.executeQuery("SELECT RECEIPT_NO, "+
						" DECODE(SETTLE_MODE,'CASH','CASH',SETTLE_MODE ||'-'||CHEQUE_NO), "+
						" REC_AMOUNT, "+
						" BALANCE_AMOUNT,"+
						" NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+
						" NVL(RECEIPT_COMMENTS,'-'), "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
						" WHERE REC_STATUS='E' "+
						" AND RECEIPT_TYPE<>'CS' "+
						" AND CLIENT_CODE='"+m_client_code+"' "+
						" AND FACILITY_NO='"+m_facility_code+"' "+
						" ORDER BY CHEQUE_DATE ");

					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>COLLECTIONS ON ACCOUNT OF DEBTOR</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Ref No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amt</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amt</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						m_val1=m_val1+rs1.getDouble(4);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
			}
			
		 else if(m_chksql.equals("LOAD_GLOBAL_AVAILABILITY_DRILL_MORE")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				String m_date=req.getParameter("ST_DATE");
				
				if(m_option_no.equals("OPT1")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT B.DEBTOR_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2
						" NVL(SUM(B.BALANCE_AMOUNT),0) "+//3
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D001'),"+//4
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D002'),"+//5
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D003')"+//6
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND B.REFACTOR_COUNT<=2 "+
						" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" GROUP BY B.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) ");
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D001'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D002'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D003') ");
							
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>ACTIVE INVOICE BATCHES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>OFSCL Assignment Letter</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(4)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(5)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT2")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT "+//8
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') "+
						" ORDER BY B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>PENDING APPROVAL INVOICES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Invoice Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT, "+//8
						" NVL(B.APPROVAL_COMMENTS,' ') "+//9
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" AND B.INVOICE_STATUS='CANCEL' "+
						" ORDER BY A.INVOICE_BATCH_DATE DESC,B.DEBTOR_CODE,B.INVOICE_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>DISAPPROVAL INVOICES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Invoice Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT4")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("SELECT B.DEBTOR_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2
						" NVL(SUM(B.BALANCE_AMOUNT),0) "+//3
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.BALANCE_AMOUNT>0 "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND B.TOLARENCE_END_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND B.TOLARENCE_END_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" GROUP BY B.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>DUE DATE EXCEEDED INVOICE TOTAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
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
							" RESERVE_MARGIN, "+//5
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//6
							" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D001'),"+//7
						  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D002'),"+//8
						  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D003'),"+//9
							" "+m_schema_name+".FA_DEBTOR_ACTIVE_STATUS(DEBTOR_CODE), "+//10
							" "+m_schema_name+".FA_DEBTOR_RELATION_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),"+//11
							" "+m_schema_name+".FA_DEBTOR_RELATION_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE), "+//12
							" NVL("+m_schema_name+".FA_DEBTOR_CLIENT_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),'-') "+//13
	         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
	     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
	         		" AND CLIENT_CODE='"+m_client_code+"' "+
							" ORDER BY "+m_schema_name+".FA_DEBTOR_RELATION_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),DEBTOR_CODE ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					//out.println("function before_submit(m_num){ "); 
					//out.println("  window.location.href=m_url;");
					//out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_save_final_chq_return_rem_without_rk_count?client_no="+m_client_no+"&debtor_code="+m_debtor_code+"&print="+debtor_code+"&receipt_no="+m_receipt_no+"&return_no="+m_return_no+"\";");  
					//out.println("  window.location.href=m_url;"); 
					//out.println("} ");
					out.println("</SCRIPT>"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>GROSS-PAYABLE AMOUNT</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='5%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Payable Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Company Assignment Letter</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>");
					//------
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Status</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Relationship</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Remarks</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Credit Comments</b></DIV></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("</tr>");
					
					int chk_nums=1;
					
					out.println("</tr>");
					
					while(rs2.next()){
				
						rs1= stmt1.executeQuery("SELECT NVL(SUM(FA_CLIENT_PRE_INV_BAL(A.CLIENT_CODE,A.FACILITY_NO,B.INVOICE_SEQ_NO,'"+m_date+"')),0) "+//NVL(SUM(B.BALANCE_AMOUNT),0) "+//
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE "+
							" A.BATCH_NO=B.BATCH_NO "+
							" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
							" AND A.FACILITY_NO='"+m_facility_code+"' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							//" AND B.BALANCE_AMOUNT>0 "+
							" AND B.INVOICE_STATUS='CONF' "+
							" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
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
							out.println("<td width='5%' class=div_input align=center>"+rs2.getString(3)+"</td>");
							out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs2.getString(3)+"')\"><u>"+rs2.getString(6)+"</u></td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1))+"</td>");
							if(rs2.getDouble(4)<rs1.getDouble(1)){
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							else{
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs1.getDouble(1)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(8)+"</td>");
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(9)+"</td>");
							//-----------------------------
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(10)+"</td>");//--
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(11)+"</td>");//---
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input align=center>"+rs2.getString(13)+"</td>");
							//out.println("<td width='10%' class=div_input align=center><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option></select><input type=\"button\" style=\"width:30px\" class='txt_input' onClick='before_submit("+chk_nums+")' value=\"Save\"></td>");
							out.println("</tr>");
						}
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='5%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
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
							" RESERVE_MARGIN, "+//5
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) "+//6
	         		" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
	     			  " WHERE FACILITY_NO='"+m_facility_code+"' "+
	         		" AND CLIENT_CODE='"+m_client_code+"' "+
							" ORDER BY DEBTOR_CODE ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>PAYABLE AMOUNT FROM DUE</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Reserve Margin(%)</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Payable Amount</b></DIV></td>");
					out.println("</tr>");
					
					while(rs2.next()){
				
						rs1= stmt1.executeQuery("SELECT NVL(SUM(B.BALANCE_AMOUNT),0) "+
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
							" WHERE "+
							" A.BATCH_NO=B.BATCH_NO "+
							" AND B.DEBTOR_CODE='"+rs2.getString(3)+"' "+
							" AND A.FACILITY_NO='"+m_facility_code+"' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.BALANCE_AMOUNT>0 "+
							" AND B.INVOICE_STATUS='CONF' "+
							" AND B.TOLARENCE_END_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND B.TOLARENCE_END_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) ");
	
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
							out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs2.getString(3)+"')\"><u>"+rs2.getString(6)+"</u></td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4))+"</td>");
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1))+"</td>");
							if(rs2.getDouble(4)<rs1.getDouble(1)){
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs2.getDouble(4)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs2.getDouble(4)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							else{
							out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(1)*(100-rs2.getDouble(5))/100)+"</td>");
							m_temp1=m_temp1+(rs1.getDouble(1)*(100-rs2.getDouble(5))/100);
							m_temp2=m_temp2+rs1.getDouble(1);
							}
							out.println("</tr>");
						}
					}
					out.println("<tr bgcolor=\"#CCCCCC\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT7")){
					
					rs1= stmt1.executeQuery("SELECT  "+
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),"+//1
							" SUM(B.REC_AMOUNT), "+//2
							" SUM("+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)), "+//3
							" SUM(B.REC_AMOUNT)-SUM("+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO)), "+//4
							" DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE) "+//5
							" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
							" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
							" AND B.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.FACILITY_NO='"+m_facility_code+"' "+
							" AND B.REBANK_STATUS IN('N','R') "+
							" AND A.REALIZE_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND A.REALIZE_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
							" AND (B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO))>0 "+
							" GROUP BY DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE) ");
							
					out.println("<HTML><HEAD><TITLE>UNSETTLE CHEQUE RETURN</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_debtor_code) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_7M&CLIENT_CODE="+m_client_code+"&FACILITY_NO="+m_facility_code+"&TRN_DATE="+m_date+"&DEBTOR_CODE=\"+m_debtor_code;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>UNSETTLE CHEQUE RETURN</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Return Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Balance Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						m_val1=m_val1+rs1.getDouble(3);
						m_val2=m_val2+rs1.getDouble(4);
						
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\">"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right >"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"drill_down_sub('"+rs1.getString(5)+"')\" align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val2)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT8")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE<>'VAT' "+
					" AND A.EFF_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.EFF_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY A.EFF_DATE DESC ");
				
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL TAX CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.FEE_CODE='VAT' "+
					" AND A.EFF_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.EFF_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY A.EFF_DATE DESC ");
				
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
					out.println("<tr bgcolor=\"#999966\">");
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

				}
				if(m_option_no.equals("OPT11")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL PAYMENTS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Payment Amount</b></DIV></td>");
					out.println("</tr>");
				
					rs1= stmt1.executeQuery("SELECT PAYMENT_CODE,"+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" PAYMENT_AMOUNT, "+
							" NVL(APP_COMMENTS,' ') "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							" WHERE "+
							" FACILITY_NO='"+m_facility_code+"' "+
							" AND CLIENT_CODE='"+m_client_code+"' "+
							" AND PAY_STATUS IN ('CONF','PRINT','DISB') "+
							" AND PAY_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND PAY_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
							" ORDER BY PAY_DATE DESC ");
				
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
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT12")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>INTEREST NORMAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Intrest Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='DAILY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY TRNDATE DESC ");
				
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>RECEIVED FROM ACTIVE INVOICES</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Allo Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Allocate Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT<=2 "+
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY C.ALLOCATED_DATE ");
							
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(11)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(10)+"')\"><u>"+rs1.getString(10)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT14")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>RECEIVED FROM INACTIVE INVOICES</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Allo Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Allocate Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
					" A.FACILITY_NO, "+//3
					" B.BATCH_NO,"+//4
					" B.DEBTOR_CODE, "+//5
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
					" B.INVOICE_NO,"+//7
					" B.BALANCE_AMOUNT, "+//8
					" C.ALLOCATED_AMOUNT, "+//9
					" C.RECEIPT_NO,"+//10
					" TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY') "+//11
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B,"+m_schema_name+".FA_OP_PRO_SETTLE_ALLO C "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.INVOICE_SEQ_NO=C.INVOICE_NO "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.INVOICE_STATUS='CONF' "+
					" AND B.REFACTOR_COUNT>2 "+ 
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(C.ALLOCATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY C.ALLOCATED_DATE ");

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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' >"+rs1.getString(11)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(10)+"')\"><u>"+rs1.getString(10)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_temp2)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT15")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<p class=pdn_txtpos2><center><u><b>UNALLOCATED FUNDS</b></center></u></p>");
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
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND REC_STATUS='Y' "+
					" AND BALANCE_AMOUNT>0 "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
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
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT16")){

				}
				if(m_option_no.equals("OPT17")){

				}
				if(m_option_no.equals("OPT18")){

				}
				if(m_option_no.equals("OPT19")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u>CHARGES AND TAX DETAILS</center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Charge Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
					out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Effective Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Charge Amount</b></DIV></td>");
					out.println("</tr>");
				
					rs1= stmt1.executeQuery("SELECT A.CHARGES_REF_NO,"+
					" A.FEE_CODE, "+
					" A.FEE_DESC, "+
					" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), "+
       		" DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1) "+
 					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
					" WHERE A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY A.EFF_DATE DESC ");
						
				
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
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick='show_charges_details(\""+rs1.getString(1)+"\")'><u>"+rs1.getString(1)+"</u></td>");
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
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OVERPAIED INTEREST</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Intrest Amount</b></DIV></td>");
					out.println("</tr>");

					rs1= stmt1.executeQuery(" SELECT TO_CHAR(TRNDATE,'DD-MM-YYYY'), "+
					" PROC_DESC,"+
					" NVL((DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)),0) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_DAILY_INTEREST "+
					"	WHERE "+
					" FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND PROC_DESC='OVERPAY INTEREST' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY TRNDATE DESC ");
							
				
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CLIENT ADJUSTMENT DETAILS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Transaction Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Adjustment Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment Type</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Adjustment Amount</b></DIV></td>");
					out.println("</tr>");

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
					" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY ADJUST_DATE DESC ");
				
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
					out.println("<tr bgcolor=\"#999966\">");
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
					double m_val=0;
					double m_val1=0;
					
					String m_st="";
					String m_end="";
					
					rs1= stmt1.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY'),"+
					" TO_CHAR(LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')),'DD-MON-YYYY') "+
					" FROM DUAL ");		
					
					if(rs1.next()){
					m_st=rs1.getString(1);
					m_end=rs1.getString(2);
					}
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<p class=pdn_txtpos2><center><u><b>OPEN BALANCES DETAILS</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Description</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Amount</b></DIV></td>");
					out.println("</tr>");
					
					double m_open1=0;
					rs1= stmt1.executeQuery("SELECT SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE  "+
					" WHERE  FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<TO_DATE('"+m_date+"','DD-MM-YYYY') ");		
					
					if(rs1.next()){
					m_open1=rs1.getDouble(1);
					}	
					
					rs1= stmt1.executeQuery("SELECT INITCAP(PROC_DESC),"+
					" SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)) "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_BALANCE  "+
					" WHERE  FACILITY_CODE='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRNDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" GROUP BY PROC_DESC "+
					" ORDER BY PROC_DESC ");						
				 
					int j=1;
					
					out.println("<tr bgcolor=\"#C0C0C0\" >");
					out.println("<td width='15%' class=div_input >Openning Balance as at "+m_date+"</td>");
					if(m_open1>=0){
					out.println("<td width='15%' class=div_input align=right style='cursor:hand' >"+nf.format(m_open1)+"</td>");
					}
					else{
					out.println("<td width='15%' class=div_input align=right style='cursor:hand' >("+nf.format(m_open1*-1)+")</td>");
					}
					out.println("</tr>");
						
					m_val=m_val+m_open1;
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
						if(rs1.getDouble(2)>=0){
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' >"+nf.format(rs1.getDouble(2))+"</td>");
						}
						else{
							out.println("<td width='15%' class=div_input align=right style='cursor:hand' >("+nf.format(rs1.getDouble(2)*-1)+")</td>");
						}
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ><b>Closing Balance</b></td>");
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
				if(m_option_no.equals("OPT23")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//2
						" A.FACILITY_NO, "+//3
						" B.BATCH_NO,"+//4
						" B.DEBTOR_CODE, "+//5
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//6
						" B.INVOICE_NO,"+//7
						" B.NET_INVOICE_AMOUNT, "+//8
						" NVL(B.APPROVAL_COMMENTS,' ') "+//9
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
						" WHERE "+
						" A.BATCH_NO=B.BATCH_NO "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND B.INVOICE_STATUS='CONF' "+
						" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" ORDER BY A.INVOICE_BATCH_DATE DESC,B.DEBTOR_CODE ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>APPROVED INVOICE TOTAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Net Amount</b></DIV></td>");
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
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT24")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT A.BATCH_NO, "+//1
						" NVL(A.TOTAL_BATCH_AMOUNT,0),"+//2
						" A.TOTAL_BATCH_INVOICES, "+//3
						" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY')"+//4
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
						" WHERE "+
						" A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
						" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" ORDER BY A.BATCH_NO,A.INVOICE_BATCH_DATE ");
								
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL INVOICES LIST<b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>No of Invoices</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Batch Date</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Batch Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT25")){
					double m_val=0;
					rs1= stmt1.executeQuery("SELECT  "+
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)),"+//1
							" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'),"+//2
							" A.RECEIPT_NO, "+//3
							" B.REC_AMOUNT, "+//4
							" B.CHEQUE_NO, "+//5
							" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE), "+//6
							" "+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO), "+//7
							" B.REC_AMOUNT-"+m_schema_name+".FA_GET_RETURN_CHEQUE_AMT(A.RECEIPT_NO), "+//8
							" DECODE(B.REBANK_STATUS,'Y','RE-BANK','N','PENDING','R','SETTLE'), "+//9
							" NVL(A.RETURN_COMMENTS,'-') "+//10
							" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B  "+
							" WHERE A.RECEIPT_NO=B.RECEIPT_NO  "+
							" AND B.CLIENT_CODE='"+m_client_code+"' "+
							" AND B.FACILITY_NO='"+m_facility_code+"' "+
							" AND B.REBANK_STATUS IN('N','R','Y') "+
							" AND A.REALIZE_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND A.REALIZE_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
							" ORDER BY A.REALIZE_DATE DESC ");
							
					out.println("<HTML><HEAD><TITLE>CHEQUE RETURN HISTORY DETAILS</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>CHEQUE RETURN HISTORY DETAILS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Return Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Branch Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Status</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Return Comments</b></DIV></td>");
					out.println("</tr>");
					
					int j=1;
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
						m_val=m_val+rs1.getDouble(4);
						m_val1=m_val1+rs1.getDouble(7);
						m_val2=m_val2+rs1.getDouble(8);
						
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(3)+"')\"><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_settle_details('"+rs1.getString(3)+"')\" align=right>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(10)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val2)+"</b></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("<td width='10'  class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT26")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT  "+
					" DECODE(RECEIPT_TYPE,'CS',FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)),"+//1
					" REC_AMOUNT,"+//2
					" BALANCE_AMOUNT,"+//3
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+//4
					" TO_CHAR(RECON_DATE,'DD-MM-YYYY'),"+//5
					" DECODE(SETTLE_MODE,'CASH',SETTLE_MODE,SETTLE_MODE||'-'||NVL(CHEQUE_NO,'-')), "+//6
					" RECEIPT_NO "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE "+
					" FACILITY_NO='"+m_facility_code+"' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND RECON_STATUS='Y' "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY'))"+
					" ORDER BY RECON_DATE DESC ");
							
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL COLLECTIONS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amt</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amt</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Value Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Realise Date</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(2);
						m_val1=m_val1+rs1.getDouble(3);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
			}
		
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

	
