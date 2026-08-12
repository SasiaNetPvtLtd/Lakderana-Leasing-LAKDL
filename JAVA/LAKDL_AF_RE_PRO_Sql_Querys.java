import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_RE_PRO_Sql_Querys extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

  public ResultSet rs,rs1,rs2,rs_invoice;
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
			
			
				/*else if(m_chksql.equals("SHOW_CONTRACT_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
					
				String		Sql_Contracts=" SELECT "+
									 " APPLICATION_NO, "+
									 " NVL(FINANCE_NO,'-'), "+
									 " NVL(DECODE(TRANSACTION_TYPE,'FA','Factoring','FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','Hiring'),'-'), "+
									 //" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel'),'-') "+
									 //" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','CLOSED','Closed','TERMINATED','Terminated','TERMI','Terminated','TERM_TO','Terminated','REJECT','Riject'),'-') "+	 //comment by nuwan de silva 15-12-08
									 // " NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','NORM_TERMI','Normal Termination'),'-')  "+
									  " "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
										" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									 " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";
			

       

			  rs=stmt1.executeQuery(Sql_Contracts);
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='14%' class=div_input><b>Application No</b></td>");
					out.println("<td width='10%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='16%' class=div_input><b>Transaction Type</b></td>");
					out.println("<td width='16%' class=div_input><b>Stage</b></td>");
					out.println("<td width='10%' class=div_input><b>Transaction History</b></td>");
					out.println("<td width='5%   class=div_input><b>Ledger-Test</b></td>");
					out.println("<td width='10%' class=div_input><b>Rental Schedule</b></td>");
					out.println("<td width='10%' class=div_input><b>Contract Details</b></td>");//add by malik on 15-8-2008
					//out.println("<td width='10%' class=div_input><b>Insurance Ledger</b></td>");//added by Sandun on 18-08-2008
					out.println("<td width='*%' class=div_input><b>Sanction Letter</b></td>");//add by Indika on 09-16-2008
					
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='14%' class=div_input style= cursor:hand; onclick=show_application_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='16%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='16%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Transaction History</u></td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"Ledger_New('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Ledger -Test</u></td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_rental_schedule('"+rs.getString(1)+"')\"  class=div_input><u>Rental Schedule</u></td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_contract_details('"+rs.getString(2)+"')\"  class=div_input><u>Contract Details</u></td>");//add by malik on 15-8-2008
					//out.println("<td width='10%' style= cursor:hand; onclick=\"show_insurance_ledger('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Insurance Ledger</u></td>");//added by Sandun on 18-08-2008 
					out.println("<td width='*%' style= cursor:hand; onclick=\"load_data_report('"+rs.getString(1)+"')\"  class=div_input><u>Sanction Letter</u></td>");//add by Indika on 09-16-2008
					//out.println("<td width='*%' ><input class='mainbut1' style='width:100px' type='button' name=\"BUTTON_SANCTION\" value=\"Sanction Letter\" onclick=load_data_report('"+rs.getString(1)+"')></td>");//add by Indika on 09-16-2008
					out.println("</tr>");
					
					more = rs.next();
				}
				
		      out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				  out.println("</table>");
				
			}
			*/
			
			
				else if(m_chksql.equals("SHOW_CONTRACT_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
					
				String		Sql_Contracts=" SELECT "+
									 " APPLICATION_NO, "+
									 " NVL(FINANCE_NO,'-'), "+
									 " NVL(DECODE(TRANSACTION_TYPE,'FA','Factoring','FINLEASE','Finance Lease','OPELEASE','Operating Lease','HIREPURCH','Hire Purchase','LOANS','Loans','HIRING','Hiring'),'-'), "+
									 //" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel'),'-') "+
									 //" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','CLOSED','Closed','TERMINATED','Terminated','TERMI','Terminated','TERM_TO','Terminated','REJECT','Riject'),'-') "+	 //comment by nuwan de silva 15-12-08
									 // " NVL(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'ENTERED','Entered','ENT_CON','Entered','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','NORM_TERMI','Normal Termination'),'-')  "+
									  " "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
										" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									 " WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";
			

       

			  rs=stmt1.executeQuery(Sql_Contracts);
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='14%' class=div_input><b>Application No</b></td>");
					out.println("<td width='10%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='16%' class=div_input><b>Transaction Type</b></td>");
					out.println("<td width='16%' class=div_input><b>Stage</b></td>");
					out.println("<td width='10%' class=div_input><b>Transaction History</b></td>");
					//out.println("<td width='5%   class=div_input><b>Ledger-Test</b></td>");
					out.println("<td width='10%' class=div_input><b>Rental Schedule</b></td>");
					out.println("<td width='10%' class=div_input><b>Contract Details</b></td>");//add by malik on 15-8-2008
					//out.println("<td width='10%' class=div_input><b>Insurance Ledger</b></td>");//added by Sandun on 18-08-2008
					//out.println("<td width='*%' class=div_input><b>Sanction Letter</b></td>");//add by Indika on 09-16-2008 // commented by ishani 2014-02-20 (REDMINE 10811)
					
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='14%' class=div_input style= cursor:hand; onclick=show_application_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='16%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='16%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_transaction_history_by_contract('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Transaction History</u></td>");
					//out.println("<td width='10%' style= cursor:hand; onclick=\"Ledger_New('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Ledger -Test</u></td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_rental_schedule('"+rs.getString(1)+"')\"  class=div_input><u>Rental Schedule</u></td>");
					out.println("<td width='10%' style= cursor:hand; onclick=\"show_contract_details('"+rs.getString(2)+"')\"  class=div_input><u>Contract Details</u></td>");//add by malik on 15-8-2008
					//out.println("<td width='10%' style= cursor:hand; onclick=\"show_insurance_ledger('"+rs.getString(2)+"','"+m_client_code+"')\"  class=div_input><u>Insurance Ledger</u></td>");//added by Sandun on 18-08-2008 
					//out.println("<td width='*%' style= cursor:hand; onclick=\"load_data_report('"+rs.getString(1)+"')\"  class=div_input><u>Sanction Letter</u></td>");//add by Indika on 09-16-2008 // commented by ishani 2014-02-20 (REDMINE 10811)
					//out.println("<td width='*%' ><input class='mainbut1' style='width:100px' type='button' name=\"BUTTON_SANCTION\" value=\"Sanction Letter\" onclick=load_data_report('"+rs.getString(1)+"')></td>");//add by Indika on 09-16-2008
					out.println("</tr>");
					
					more = rs.next();
				}
				
		      out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				  out.println("</table>");
					
		       
				
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


