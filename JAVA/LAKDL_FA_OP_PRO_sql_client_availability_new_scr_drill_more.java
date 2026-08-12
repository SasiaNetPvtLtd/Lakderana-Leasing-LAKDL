import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_client_availability_new_scr_drill_more extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY_DRILL")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				
				if(m_option_no.equals("OPT30")){
					double m_val=0;
					double m_val1=0;
					double m_val2=0;
					
					rs1= stmt1.executeQuery("SELECT A.REF_NO, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)), "+//2
					" A.RECEIPT_NO, "+//3
					" DECODE(B.SETTLE_MODE,'CHEQUE','CHEQUE-' || B.CHEQUE_NO,B.SETTLE_MODE), "+//4
					" B.REC_AMOUNT, "+//5
					" A.TRANSFER_AMOUNT,  "+//6
					" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+//7
					" NVL(A.COMMENTS,'-'), "+//8
					" B.BALANCE_AMOUNT, "+//9
					" DECODE(A.ADJ_TYPE,'NS','Non Sales Funds Transfer','EX','Excess Funds Transfer ','Other') "+//10
					" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
					" AND A.STATUS='Y' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)) ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					//out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_1&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
					//out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					//out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>UNALLOCATED FUND TRASFER</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Details</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Date</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Transfer Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Type</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(5);
						m_val1=m_val1+rs1.getDouble(9);
						m_val2=m_val2+rs1.getDouble(6);
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand'  onClick=\"show_receipt_details('"+rs1.getString(3)+"')\">"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(6))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(10)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val2)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				
				//---------------------ADDED BY ASHINI ON 22.02.2008------------------------------------------------------------------------------
				if(m_option_no.equals("OPT31")){
					
					rs2= stmt2.executeQuery("SELECT TO_CHAR(MONTH_END_DATE,'DD-MM-YYYY') "+
					" FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL "+
					" WHERE "+
					" CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_CODE='"+m_facility_code+"' ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>RETURNABLE FUNDS</b></u></center></p>");
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
					" AND REC_SETT_TYPE ='RR' "+
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
					" AND REC_SETT_TYPE ='RR' "+
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
				
				
				
				if(m_option_no.equals("OPT32")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>REFUNDED FUNDS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Reference No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Transfer Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Comment</b></DIV></td>");
					out.println("</tr>");


					rs1= stmt1.executeQuery(" SELECT A.REF_NO,"+
					" A.RECEIPT_NO, "+
					" A.FACILITY_NO, "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
					" NVL((A.TRANSFER_AMOUNT),0), "+
					" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+
					" A.SOURCE_DOCUMENT, "+
					" A.COMMENTS,  "+
					" A.CLIENT_CODE "+
					" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A, "+
          "      "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE "+
					" A.FACILITY_NO=B.FACILITY_NO "+
					" AND A.CLIENT_CODE=B.CLIENT_CODE "+
					" AND A.RECEIPT_NO=B.RECEIPT_NO "+
					" AND B.FACILITY_NO='"+m_facility_code+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.REC_STATUS='Y' "+
					" AND B.BALANCE_AMOUNT>0 "+
					" AND B.REC_SETT_TYPE ='RR' "+
					" AND TO_DATE(TO_CHAR(TRANSFER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
					" ORDER BY TRANSFER_DATE DESC ");
							
							
							
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
						m_temp1=m_temp1+rs1.getDouble(5);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(2)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(9)+"')\">"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ><B>Total</B></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}				
				
				
				//---------END OF MODIFICATION DONE BY ASHINI ON 22.02.2008-----------------------------------------------------------------------
				
								
				
				
			}
			else if(m_chksql.equals("LOAD_CLIENT_AVAILABILITY_DRILL_MORE")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_code=req.getParameter("FACILITY_NO");
				String m_option_no=req.getParameter("OPTION_NO");
				String m_date=req.getParameter("ST_DATE");
				
				if(m_option_no.equals("OPT30")){
					double m_val=0;
					double m_val1=0;
					double m_val2=0;
					
					rs1= stmt1.executeQuery("SELECT A.REF_NO, "+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)), "+//2
					" A.RECEIPT_NO, "+//3
					" DECODE(B.SETTLE_MODE,'CHEQUE','CHEQUE-' || B.CHEQUE_NO,B.SETTLE_MODE), "+//4
					" B.REC_AMOUNT, "+//5
					" A.TRANSFER_AMOUNT,  "+//6
					" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+//7
					" NVL(A.COMMENTS,'-'), "+//8
					" B.BALANCE_AMOUNT, "+//9
					" DECODE(A.ADJ_TYPE,'NS','Non Sales Funds Transfer','EX','Excess Funds Transfer ','Other') "+//10
					" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
					" AND A.STATUS='Y' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.TRANSFER_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND A.TRANSFER_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(B.RECEIPT_TYPE,'CS',B.CLIENT_CODE,B.DEBTOR_CODE)) ");
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<SCRIPT language=\"JavaScript\">"); 
					//out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
					//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_1&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
					//out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					//out.println("}");
					out.println("</script>"); 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>UNALLOCATED FUND TRASFER</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Settle Details</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Date</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Transfer Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Type</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(5);
						m_val1=m_val1+rs1.getDouble(9);
						m_val2=m_val2+rs1.getDouble(6);
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand'  onClick=\"show_receipt_details('"+rs1.getString(3)+"')\">"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("<td width='10%' class=div_input align=right >"+nf.format(rs1.getDouble(6))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(10)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val1)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val2)+"</b></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("<td width='10%' class=div_input align=right></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				
								//---------------------ADDED BY ASHINI ON 22.02.2008------------------------------------------------------------------------------
				
				if(m_option_no.equals("OPT31")){
					double m_val=0;
					double m_val1=0;
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<p class=pdn_txtpos2><center><u><b>RETURNABLE FUNDS</b></center></u></p>");
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
					" AND REC_SETT_TYPE ='RR' "+
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
				
				
				if(m_option_no.equals("OPT32")){
					
					out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>REFUNDED FUNDS</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' ><DIV class=div_input><b>Reference No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Transfer Amount</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Transfer Date</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Comment</b></DIV></td>");
					out.println("</tr>");


					rs1= stmt1.executeQuery(" SELECT A.REF_NO,"+
					" A.RECEIPT_NO, "+
					" A.FACILITY_NO, "+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
					" NVL((A.TRANSFER_AMOUNT),0), "+
					" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+
					" A.SOURCE_DOCUMENT, "+
					" A.COMMENTS  "+
					" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A, "+
          "      "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE "+
					" A.FACILITY_NO=B.FACILITY_NO "+
					" AND A.CLIENT_CODE=B.CLIENT_CODE "+
					" AND A.RECEIPT_NO=B.RECEIPT_NO "+
					" AND B.FACILITY_NO='"+m_facility_code+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND B.REC_STATUS='Y' "+
					" AND B.BALANCE_AMOUNT>0 "+
					" AND B.REC_SETT_TYPE ='RR' "+
					" AND TO_DATE(TO_CHAR(TRANSFER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
					" AND TO_DATE(TO_CHAR(TRANSFER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
					
					
					" ORDER BY TRANSFER_DATE DESC ");
							
							
							
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
						m_temp1=m_temp1+rs1.getDouble(5);
						out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(2)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ><B>Total</B></td>");
					out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}				
				
				
				//---------END OF MODIFICATION DONE BY ASHINI ON 22.02.2008-----------------------------------------------------------------------
				
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

