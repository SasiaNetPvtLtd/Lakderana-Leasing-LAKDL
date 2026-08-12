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
  
public class LAKDL_FA_OP_PRO_global_availability_drill_down extends javax.servlet.http.HttpServlet {
	
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
				String m_option_no=req.getParameter("OPTION_NO");
				if(m_option_no.equals("OPT24")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO,NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+
																" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																" WHERE "+
																"	A.BATCH_NO=B.BATCH_NO " +
																" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");
//--AND A.FACILITY_NO=M_FACILITY_CODE
//--AND A.CLIENT_CODE=M_CLIENT_CODE;
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
				
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL INVOICE LIST</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT23")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO,NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+ 
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
 
//--AND A.FACILITY_NO=M_FACILITY_CODE
//--AND A.CLIENT_CODE=M_CLIENT_CODE;
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>APPROVED INVOICE TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						//out.println("<td width='10%' class=div_input ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT2")){
				
					double m_val=0;
					double m_val1=0;
					
					/*rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+//1
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
						" ORDER BY B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_NO ");*/
								
								
								
						rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO,NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+ 
																		" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																		" WHERE "+
																		"	A.BATCH_NO=B.BATCH_NO "+
																		" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') "+
																		" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");

					out.println("<HTML><HEAD><TITLE>PENDING APPROVAL INVOICES TOTAL</TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>PENDING APPROVAL INVOICES TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");*/
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input  >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO,"+
																	" NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+  
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	//--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND B.INVOICE_STATUS='CANCEL' "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>DISAPPROVAL INVOICES TOTAL</B></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Namw</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");*/
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input>"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input>"+rs1.getString(3)+"</td>");
						//out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					//out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				if(m_option_no.equals("OPT1")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL(SUM(B.BALANCE_AMOUNT),0) "+ 
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	//--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND B.BALANCE_AMOUNT>0 "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" AND B.REFACTOR_COUNT<=2 "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO");
					
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					/*out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function drill_down_sub(m_client,m_facility,m_debtor) {");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_PRO_sql_client_availability_new_scr_drill?chksql=DRILL_DOOWN_SUB_1&CLIENT_CODE=\"+m_client+\"&FACILITY_NO=\"+m_facility+\"&DEBTOR_CODE=\"+m_debtor;");	
					out.println("		window.open(m_url,'popupwin1','status=0,menubar=0,scrollbars=1,height=500,width=700');");
					out.println("}");
					out.println("</script>");*/ 
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>ACTIVE INVOICE BATCHES TOTAL</b></u></center></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right style='cursor:hand' onClick=\"drill_down_sub('"+m_client_code+"','"+m_facility_code+"','"+rs1.getString(1)+"')\">"+nf.format(rs1.getDouble(3))+"</td>");*/
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(4)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(5)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input ><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input ><u>"+rs1.getString(3)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");

						out.println("</tr>");
					}
					/*out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");*/
					//out.println("<td width='15%' class=div_input align=right></td>");
					//out.println("<td width='15%' class=div_input align=right></td>");
					//out.println("<td width='15%' class=div_input align=right></td>");
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
				
				if(m_option_no.equals("OPT4")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE, "+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL(SUM(B.BALANCE_AMOUNT),0) "+ 
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	//--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND B.BALANCE_AMOUNT>0 "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" AND (B.TOLARENCE_END_DATE)<TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>DUE DATE EXCEEDED INVOICE TOTAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input  >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
			
				if(m_option_no.equals("OPT5")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL("+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) "+
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_GROSS_PAY_INV_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0"+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>GROSS-PAYABLE AMOUNT</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT6")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_GROSS_DUE_INV_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>PAYABLE AMOUNT FROM DUE</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				
				if(m_option_no.equals("OPT7")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>UNSETTLE CHEQUE RETURN</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT22")){
					double m_val=0;
					double m_val1=0;
					/*rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");*/
					
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_CODE, "+
																	" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL(A.CLIENT_CODE,A.FACILITY_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_OP_CLIENT_LOAN_OP_BAL A "+
																	//" WHERE "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL(A.CLIENT_CODE,A.FACILITY_CODE,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_CODE ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OPENING BALANCE</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT8")){
					double m_val=0;
					double m_val1=0;
					// Modified by Dineth on 2008-09-16 instead of FA_CR_PRO_INVOICE CHANGED TO FA_OP_PRO_CLIENT_CHARGES
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT9")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL TAX CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				
				if(m_option_no.equals("OPT10")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_PENDING_PAYMENTS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_PENDING_PAYMENTS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL PENDING PAYMENTS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT11")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_PAYMENT_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_PAYMENT_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL PAYMENTS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_COLLECT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_COLLECT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL COLLECTIONS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT13")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>RECEIVED FROM ACTIVE INVOICES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT14")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INACT_SETTLE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INACT_SETTLE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>RECEIVED FROM INACTIVE INVOICES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT15")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_UNALLOCATED_FUNDS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_UNALLOCATED_FUNDS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>UNALLOCATED FUNDS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT21")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CLIENT ADJUSTMENT DETAILS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT12")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>INTEREST NORMAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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

				if(m_option_no.equals("OPT20")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INTEREST_OVER_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INTEREST_OVER_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OVERPAIED INTEREST</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input  >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT16")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_BANK_CHEQUE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_BANK_CHEQUE_TOT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CHEQUES BANKED PENDING REALISATION</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT17")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_POD_HAND(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_POD_HAND(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL UNBANKED POD CHEQUES IN HAND</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT18")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_DUE_IN7DAYS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_DUE_IN7DAYS(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL INVOICES FALLING DUE WITHIN 7 DAYS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT27")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ON_ACC_CLIENT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ON_ACC_CLIENT(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>COLLECTIONS ON ACC OF CLIENT</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT28")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ON_ACC_DEBTOR(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ON_ACC_DEBTOR(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>COLLECTIONS ON ACC OF DEBTOR</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				
			}
			else if(m_chksql.trim().equals("LOAD_GLOBAL_AVAILABILITY_DRILL_MORE")){
			String m_option_no=req.getParameter("OPTION_NO");
			String m_date=req.getParameter("ST_DATE");
				if(m_option_no.equals("OPT24")){
					double m_val=0;
					double m_val1=0;
			
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO,NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+
															" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
															" WHERE "+
															" A.BATCH_NO=B.BATCH_NO "+
															" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
															" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
															" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");
															
															
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>TOTAL INVOICES LIST<b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						//out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						//out.println("<td width='10%' class=div_input style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					if(m_option_no.equals("OPT23")){
					double m_val=0;
					double m_val1=0;
			
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO,NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+ 
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																	" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
															
															
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>APPROVED INVOICE TOTAL<b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>");
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
						//out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
						//out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						//out.println("<td width='10%' class=div_input style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					
					if(m_option_no.equals("OPT2")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE,"+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+
																	" A.FACILITY_NO,"+
																	" NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+  
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
//--AND A.FACILITY_NO=M_FACILITY_CODE
//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																	" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
																	" AND B.INVOICE_STATUS NOT IN('CONF','CANCEL') "+
																	"	GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>PENDING APPROVAL INVOICES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");*/
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				
				//opt3
				if(m_option_no.equals("OPT3")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("	SELECT A.CLIENT_CODE, "+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL(SUM(B.NET_INVOICE_AMOUNT),0) "+  
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	" WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	//--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																	" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
																	" AND B.INVOICE_STATUS='CANCEL' "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>DISAPPROVAL INVOICES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					//out.println("<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>");
						out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(5)+"')\"><u>"+rs1.getString(6)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"><u>"+rs1.getString(7)+"</u></td>");
						out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>");*/
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						//out.println("<td width='15%' class=div_input align=right>"+rs1.getString(9)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='15%' class=div_input ></td>");
					//out.println("<td width='15%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
				
				//OPT1
				if(m_option_no.equals("OPT1")){
					double m_val=0;
					double m_val1=0;
					
					rs1= stmt1.executeQuery("SELECT A.CLIENT_CODE,"+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+
																	" A.FACILITY_NO,"+
																	" NVL(SUM(B.BALANCE_AMOUNT),0) "+ 
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
                                  " WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
                                  //--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND B.BALANCE_AMOUNT>0 "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" AND A.INVOICE_BATCH_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																	" AND A.INVOICE_BATCH_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
																	" AND B.REFACTOR_COUNT<=2 "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO");
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D001'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D002'),"+
						//" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(A.CLIENT_CODE,A.FACILITY_NO,B.DEBTOR_CODE,'D003') ");
							
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><b>ACTIVE INVOICE BATCHES TOTAL</b></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						/*out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(1)+"')\"><u>"+rs1.getString(2)+"</u></td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");*/
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(4)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(5)+"</td>");
						//out.println("<td width='15%' class=div_input align=center>"+rs1.getString(6)+"</td>");
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");
					}
					out.println("<tr bgcolor=\"#999966\">");
					/*out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");*/
					//out.println("<td width='10%' class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
					//out.println("<td width='10%' class=div_input ></td>");
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
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery("	SELECT A.CLIENT_CODE, "+
																	" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL(SUM(B.BALANCE_AMOUNT),0) "+ 
																	" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
																	"	WHERE "+
																	" A.BATCH_NO=B.BATCH_NO "+
																	//--AND A.FACILITY_NO=M_FACILITY_CODE
																	//--AND A.CLIENT_CODE=M_CLIENT_CODE
																	" AND B.BALANCE_AMOUNT>0 "+
																	" AND B.INVOICE_STATUS='CONF' "+
																	" AND B.TOLARENCE_END_DATE>=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
																	" AND B.TOLARENCE_END_DATE<=LAST_DAY(TO_DATE('"+m_date+"','DD-MM-YYYY')) "+
																	" GROUP BY A.CLIENT_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>DUE DATE EXCEEDED INVOICE TOTAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				
				if(m_option_no.equals("OPT5")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_GROSSPAYINV_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>GROSS-PAYABLE AMOUNT</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT6")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" NVL("+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"'),0) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_GROSSDUEINV_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>LESS PAYABLE AMOUNT FROM DUE</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT7")){
					double m_val=0;
					double m_val1=0;
					// Modified by Dineth on 2008-09-16 -changed FA_CR_PRO_INVOICE to FA_OP_PRO_SETTL_RECEIPT
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>UNSETTLE CHEQUE RETURN</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_RTN_CHEQUE_HIS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CHEQUE RETURN HISTORY DETAILS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT22")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_OPEN_BAL_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OPENING BALANCE</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input  >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT8")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_CHARGES_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT9")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_TAX_CHARGES_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL TAX CHARGES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT11")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_PAY_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL PAYMENTS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_COLLECT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_COLLECT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL COLLECTIONS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT13")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ACT_SETTLE_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>RECEIVED FROM ACTIVE INVOICES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT14")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INACT_SETT_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>RECEIVED FROM INACTIVE INVOICES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT15")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_UNALLO_FUNDS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>UNALLOCATED FUNDS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT21")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_ADJUSTMENTS_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>CLIENT ADJUSTMENT DETAILS</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT12")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INTEREST_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>INTEREST NORMAL</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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
				if(m_option_no.equals("OPT20")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"') "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_INTER_OVER_TOT_M(A.CLIENT_CODE,A.FACILITY_NO,'"+m_date+"')>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>OVERPAIED INTEREST</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
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
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
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

	
		
