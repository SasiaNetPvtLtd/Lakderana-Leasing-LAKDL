import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_Reminder_letter_contracts extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

    public ResultSet rs,rs1,rs2,rs3,rs4,rs_invoice;
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
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 

			String url = "";
			if(req.getParameter("url")!=null){
			url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
	 		m_html_client_url="http://www.lakdac.lk"; 
			m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
		
			else{
		
			m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}
			
			
			//---------------------------------------------------------------
			
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
			stmt_invoice=conn.createStatement();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			
			else if(m_chksql.equals("SHOW_RUNNING_CON_DETAILS")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
			
			
					out.println("<HTML><HEAD><TITLE> Letter Sent History - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					
					out.println(" <SCRIPT language1.2='JavaScript' > ");
					
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    	window.open(m_url); ");
					out.println("	}");	
					
					// added by udara 10-10-2019
					out.println("function print_letter(client_code,app_no,total_arr, reminder_type, due_date)");
					out.println("{ ");	

					out.println(" 		if(reminder_type==\"1STREIM\"  ){ ");
					//out.println(" 		    alert('1st Reminder Letter'); ");
					out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_First_Reminder_front_end?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr+\"&due_date=\"+due_date; ");
					out.println("			popupwin = window.open(m_url); ");
					out.println(" 		} ");
					
					out.println(" 		else if(reminder_type==\"FINREIM\"  ){ ");
					//out.println(" 		   alert('Final Reminder Letter'); ");
					out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Final_Reminder_front_end?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr+\"&due_date=\"+due_date; ");
					out.println("			popupwin = window.open(m_url); ");
					out.println(" 		} ");
					
					out.println(" 		else if(reminder_type==\"NOTTERM\"  ){ ");
					//out.println(" 		   alert('Notice of Termination'); ");
					out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_NOT_Letter_Reminder_front_end?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr+\"&due_date=\"+due_date; ");
					out.println("			popupwin = window.open(m_url); ");
					out.println(" 		} ");
					
					out.println(" 		else if(reminder_type==\"LETTERM\"  ){ ");
					//out.println(" 		   alert('Letter of Termination'); ");
					out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_LOT_Letter_Reminder_front_end?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr+\"&due_date=\"+due_date; ");
					out.println("			popupwin = window.open(m_url); ");
					out.println(" 		} ");

					// Notice of Termination Final										
					out.println(" 		else if(reminder_type==\"NOTTERM1\"  ){ ");
				    out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_NOT_Reminder_front_end?chksql=print_not_final_letter&document_code=NOTTERM1&print=TRUE&client_code=\"+client_code+\"&application_no=\"+app_no+\"&total_arrears=\"+total_arr+\"&due_date=\"+due_date; ");
					out.println("			popupwin = window.open(m_url); ");
					out.println(" 		} ");
					
					out.println("} ");
					// end by udara 10-10-2019
					
					
					out.println(" </SCRIPT> ");
					
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Letter Sent History - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
			
					out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
	
					out.println("<tr>");
					out.println("  <td width=\"20%\"  align='left'><b>Letter Name</b></td>");
					out.println("  <td width=\"20%\"  align='left'><b>User</b></td> ");
					out.println("  <td width=\"20%\"  align='left'><b>Date/Time</b></td> ");
					out.println("  <td width=\"*%\"   align='left'> &nbsp; </td> ");
					out.println("</tr>");

				
					stmt4 = conn.createStatement ();
					rs4 = stmt4.executeQuery("  "+
					//out.println(" "+
						" SELECT "+ 
													" DECODE(REMINDER_TYPE, "+
													" '1STREIM','1st Reminder Letter',"+
													" 'FINREIM','Final Reminder Letter',"+
													" 'LETTERM','Letter of Termination',"+
													" 'NOTTERM','Notice of Termination',"+
													" REMINDER_TYPE), "+ //1
													" PRINT_USER, "+
													" TO_CHAR(PRINT_DATE,'DD-MM-YYYY HH:MI:SS AM'), "+
													" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)) CLIENT_CODE, "+
													//" "+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO) APPLICATION_NO, "+ // added by udara 10-10-2019
													" FINANCE_NO, "+ // added by udara 10-10-2019
													" ARR_AMOUNT, "+ // added by udara 10-10-2019
													" REMINDER_TYPE, "+ // added by udara 10-10-2019
													" TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE, "+
													" TO_CHAR(ENT_DATE,'DD-MM-YYYY')  ENT_DATE "+
													" FROM "+m_schema_name+".AF_CO_TBL_REMINDER_LOG "+
													" WHERE FINANCE_NO = '"+m_finance_no+"' "+
													" AND PRINT_STATUS = 'P'  "+ // added by udara 05-11-2019
													" ORDER BY PRINT_DATE "+
													" ");
	    
					//boolean more4 = rs4.next();		
					
					
					while(rs4.next()){	
	
						out.println("<tr>");
						out.println("  <td width=\"20%\"  align='left'> "+rs4.getString(1)+" </td> ");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString(2)+" </td> ");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString(3)+" </td> ");
						//out.println("  <td width=\"*%\"   align='left'> <INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_letter('"+rs4.getString("CLIENT_CODE")+"','"+rs4.getString("FINANCE_NO")+"','"+nf.format(rs4.getDouble("ARR_AMOUNT"))+"','"+rs4.getString("REMINDER_TYPE")+"');\" > </td> "); // commented by udara 05-11-2019
						out.println("  <td width=\"*%\"   align='left'> <INPUT TYPE='BUTTON' class='but_input'  VALUE=\"Print\" onClick=\"print_letter('"+rs4.getString("CLIENT_CODE")+"','"+rs4.getString("FINANCE_NO")+"','"+nf.format(rs4.getDouble("ARR_AMOUNT"))+"','"+rs4.getString("REMINDER_TYPE")+"','"+rs4.getString("ENT_DATE")+"');\" > </td> ");  // added by udara 05-11-2019
						out.println("</tr>");
	
					}

				
				out.println("</table >");
				
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			
				
			}
			
			
			// added by udara 06-01-2021
			else if(m_chksql.equals("SHOW_FUTURE_MORATORIUM_INVOICES")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
			
			
					out.println("<HTML><HEAD><TITLE> Moratorium Future Invoices - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					
					out.println(" <SCRIPT language1.2='JavaScript' > ");
					
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    	window.open(m_url); ");
					out.println("	}");	
					
					
					
					
					out.println(" </SCRIPT> ");
					
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Moratorium Future Invoices - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
			
					out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
	
					out.println("<tr>");
					out.println("  <td width=\"20%\"  align='left'><b>Invoice No</b></td>");
					out.println("  <td width=\"20%\"  align='left'><b>Invoice Type</b></td> ");
					out.println("  <td width=\"20%\"  align='right'><b>Amount</b></td> ");
					out.println("  <td width=\"2%\"  align='right'> &nbsp; </td> ");
					out.println("  <td width=\"20%\"  align='left'><b>Value Date</b></td> ");
					out.println("  <td width=\"20%\"  align='left'><b>Due Date</b></td> ");
					out.println("  <td width=\"*%\"   align='left'> &nbsp; </td> ");
					out.println("</tr>");

				
					stmt4 = conn.createStatement ();
					rs4 = stmt4.executeQuery("  "+													
													" SELECT INVOICE_NO, "+
														" INVOICE_TYPE, "+
														" TOTAL_AMOUNT, "+
														" TO_CHAR(VALUE_DATE,'DD-MM-YYYY') VALUE_DATE_1, "+
														" TO_CHAR(DUE_DATE,'DD-MM-YYYY') DUE_DATE, "+
														" VALUE_DATE "+
														" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
															" WHERE FINANCE_NO = '"+m_finance_no+"' "+
															" AND INVOICE_TYPE = 'MORATO-INT' "+
															" AND ACTIVE_STATUS = 'Y' "+
															" ORDER BY VALUE_DATE "+													
													" ");
	    
					//boolean more4 = rs4.next();		
					
					
					while(rs4.next()){	
	
						out.println("<tr>");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString("INVOICE_NO")+" </td> ");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString("INVOICE_TYPE")+" </td> ");
						out.println("  <td width=\"20%\"   align='right'> "+nf.format(rs4.getDouble("TOTAL_AMOUNT"))+" </td> ");
						out.println("  <td width=\"2%\"  align='right'> &nbsp; </td> ");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString("VALUE_DATE_1")+" </td> ");
						out.println("  <td width=\"20%\"   align='left'> "+rs4.getString("DUE_DATE")+" </td> ");
						out.println("</tr>");
	
					}

				
				out.println("</table >");
				
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			
				
			}
			// end by udara 06-01-2021
			
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


