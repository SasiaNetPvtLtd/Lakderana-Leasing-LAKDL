import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_RE_PRO_drill_downs_3 extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

    public ResultSet rs,rs1,rs2,rs_invoice;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
		Statement stmt=null,stmt1=null,stmt_invoice=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
	
	     ResultSet rs=null,rs1=null,rs2=null,rs_invoice=null;
		 String m_chksql=null;
		
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
			
			
			/*	else if(m_chksql.equals("SHOW_CONTRACT_INFO")){
				
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
			
				else if(m_chksql.equals("SHOW_ASSET_INFO")){
				
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
				//======================Commented by Dineth on 06-08-2008
				/*String		Sql_Asset=" SELECT "+
																"    ASSET_ID, "+
																"    MODEL_CODE, "+
																"    SUB_MODEL_CODE, "+
																"    DECODE(STATUS,'N','New','R','Re-Condition','U','Used'), "+
																"    DECODE(PURPOSE,'P','Private Use','B','Business Use') "+
																"    FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
																"    WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
																"                          FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																"                          WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
																"                          APPLICATION_STATUS <>'CANCEL' "+
																"                             ) "+
																"                          AND ACTIVE_STATUS='Y'   ";*/
																
																
					
          //====================Modified by Dineth on 06-08-2008  
			// commented by udara 20-12-2013		
			/*
			String Sql_Asset=" SELECT "+
																"    DISTINCT A.ASSET_ID, "+
																"    NVL(B.FINANCE_NO,'-'), "+
																"    NVL(C.REG_NO,'-'), "+
																"    A.MODEL_CODE, "+
																"    A.SUB_MODEL_CODE, "+
																"    DECODE(A.STATUS,'N','New','R','Re-Condition','U','Used'), "+
																"    DECODE(A.PURPOSE,'P','Private Use','B','Business Use') "+
																"    FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																"    "+ m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																"    "+ m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
																"    WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
																"    AND A.ASSET_ID = C.ASSET_ID "+
                                "    AND UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
																"    AND B.APPLICATION_STATUS <>'CANCEL' "+
																"    AND A.ACTIVE_STATUS='Y' ";
																
					*/	
			
			// added by udara 20-12-2013
			String Sql_Asset=" SELECT "+
																"    DISTINCT A.ASSET_ID, "+
																"    NVL(B.FINANCE_NO,'-'), "+
																"    NVL(C.REG_NO,'-'), "+
																"    A.MODEL_CODE, "+
																"    A.SUB_MODEL_CODE, "+
																"    DECODE(A.STATUS,'N','New','R','Re-Condition','U','Used'), "+
																"    DECODE(A.PURPOSE,'P','Private Use','B','Business Use') "+
																"    FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
																"    "+ m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
																"    "+ m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C, "+
																"    "+ m_schema_name+".AF_CO_SUM_INSURED_DETAILS D "+ 
																"    WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
																"    AND A.ASSET_ID = C.ASSET_ID "+
                                								"    AND UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') "+
																"    AND B.APPLICATION_STATUS <>'CANCEL' "+
																"    AND A.ACTIVE_STATUS='Y' "+
																"    AND C.INVOICE_NO=D.INVOICE_NO ";
			// end by udara 20-12-2013
			
			
				//=======================End Modify by Dineth on 06-08-2008

       

			  rs=stmt1.executeQuery(Sql_Asset);
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
					out.println("<td width='2%'></td>"); 
					out.println("<td width='14%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='14%' class=div_input><b>Contract No</b></td>");
					out.println("<td width='14%' class=div_input><b>Reg No</b></td>");
					out.println("<td width='14%' class=div_input><b>Model Code</b></td>");
					out.println("<td width='14%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='14%' class=div_input><b>Condition of Asset</b></td>");
					out.println("<td width='14%' class=div_input><b>Status</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='2%'></td>"); 
					out.println("<td width='14%' class=div_input style= cursor:hand; onclick=show_asset_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='14%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
		       
				
			}
			
			
				else if(m_chksql.equals("SHOW_RENTAL_INFO")){
				
				int count = 0;
				String m_string="";		
				String m_application_no="";
				String m_client_code=req.getParameter("client_code");
				
				double sum_net=0;
				double sum_vat=0;
				double sum_tot=0;
				double sum_set=0;
				double sum_bal=0;
				
				double tot_net=0;
				double tot_vat=0;
				double tot_tot=0;
				double tot_set=0;
				double tot_bal=0;
				double tot_rental=0;
				
				double sum_odical_amt=0;
				double sum_odibal_amt=0;
				double sum_odiset_amt=0;
				double sum_odiadj_amt=0;
				
				double odi_cal_tot=0;
				double odi_bal_tot=0;
				double odi_set_tot=0;
				double odi_adj_tot=0;
				int b_flag=0;
					
					
				String		Sql_Rent=" SELECT "+
							  " APPLICATION_NO, "+
							  " SUM(NET_RENTAL_AMOUNT) NET_RENTAL_AMOUNT "+
							  " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
							  " WHERE APPLICATION_NO IN (SELECT APPLICATION_NO "+
							  "                        FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
								"                          WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							  "                        APPLICATION_STATUS <>'CANCEL' "+
							  "                           ) "+
							  " GROUP BY   APPLICATION_NO      ";                    
             
                          
			

       

			  rs=stmt1.executeQuery(Sql_Rent);
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
				  out.println("<table align='center' width='100%' class='table' border='0' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input ><b>Finance No</b></td>");
					out.println("<td width='10%' class=div_input align='right'> <b>Net Rental Amount</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Net</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>Cr Legal</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>Legal Due</b></td>");//-------Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Calculate Amount</b></td>");					
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");					
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Adjusted Amount</b></td>");
					out.println("<td width='10%'  class=div_input align='right'><b>Legal Adujstment</b></td>");//-Sandun on 18-03-2009
					out.println("<td width='10%'  class=div_input align='right'><b>ODI Balance Amount</b></td>");
					//out.println("<td width='10%'  class=div_input align='right'><b>ODI Settled Amount</b></td>");//Commented by Dineth on 2008-08-25
					
					
					out.println("</tr>");
					//out.println("</table>");
					out.println("<br>");
				}
				//out.println("<table align='center' width='100%' class='table' >");
				while(more){
				sum_net=0;
				sum_vat=0;
				sum_tot=0;
				sum_set=0;
				sum_bal=0;
				
					count++;
					
					          m_application_no=rs.getString(1);
										
										String		Sql_Rent_details=" SELECT "+ 
										"  SUM(NET_AMOUNT), "+
										"  SUM(VAT_AMOUNT), "+
										"  SUM(TOTAL_AMOUNT), "+ 
										"  SUM(SETTELE_AMOUNT), "+
										"  SUM(BALANCE_TO_BE_RECEIVED), "+
										"  FINANCE_NO "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL') GROUP BY FINANCE_NO ";
										
				rs2=stmt.executeQuery(Sql_Rent_details);
				boolean  more2 =rs2.next();						
				String m_finance_no="";
				if(more2){
				sum_net=rs2.getDouble(1);
				sum_vat=rs2.getDouble(2);
				sum_tot=rs2.getDouble(3);
				sum_set=rs2.getDouble(4);
				sum_bal=rs2.getDouble(5);
				m_finance_no=rs2.getString(6);

				}
				
				
				
				
				String		Sql_OdRent_details= " SELECT SUM(ODI_CAL_AMOUNT),SUM(ODI_BAL_AMOUNT),SUM(ODI_SETTLED_AMOUNT),SUM(ADJUSTED_AMOUNT) "+
				                           " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
																	 " WHERE INVOICE_NO IN "+
																	 " (SELECT INVOICE_NO "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	 " WHERE FINANCE_NO IN   ( SELECT FINANCE_NO "+
																	 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																	 " WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+    //AP20070122-0319
																	 " APPLICATION_STATUS<>'CANCEL' )) ";
				
						rs2=stmt.executeQuery(Sql_OdRent_details);
				    boolean  more3 =rs2.next();	
				    
						if(more2){
				    sum_odical_amt=rs2.getDouble(1);
				    sum_odibal_amt=rs2.getDouble(2);
				    sum_odiset_amt=rs2.getDouble(3);
						sum_odiadj_amt=rs2.getDouble(4);
				    }
				
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					//out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_application_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+m_finance_no+"') ><u>"+m_finance_no+"</u></td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_net)+"</td>");
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(sum_vat)+"</td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_invoiced_drill('"+m_application_no+"')><u>"+nf.format(sum_tot)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_settled_drill('"+m_application_no+"')><u>"+nf.format(sum_set)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(0)+"</td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(0)+"</td>");//--------Sandun on 18-03-2009			
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_rent_balance_to_be_received_drill('"+m_application_no+"')><u>"+nf.format(sum_bal)+"</u></td>");
          out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_cal_drill('"+m_application_no+"')><u>"+nf.format(sum_odical_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill('"+m_application_no+"')><u>"+nf.format(sum_odiset_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_cal_drill('"+m_application_no+"')><u>"+nf.format(sum_odiadj_amt)+"</u></td>");
					out.println("<td width='10%' class=div_input align='right' >"+nf.format(0)+"</td>");//--------Sandun on 18-03-2009			
					out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_bal_drill('"+m_application_no+"')><u>"+nf.format(sum_odibal_amt)+"</u></td>");
					//out.println("<td width='10%' class=div_input align='right' style= cursor:hand; onclick=show_odi_set_drill('"+m_application_no+"')><u>"+nf.format(sum_odiset_amt)+"</u></td>");// Commented by Dineth on 2008-08-25
					
					out.println("</tr>");
					
					tot_rental=tot_rental+rs.getDouble(2);
					tot_net=tot_net+sum_net;
					tot_vat=tot_vat+sum_vat;
					tot_tot=tot_tot+sum_tot;
					tot_set=tot_set+sum_set;
					tot_bal=tot_bal+sum_bal;
					
					odi_cal_tot=odi_cal_tot+sum_odical_amt;
					odi_bal_tot=odi_bal_tot+sum_odibal_amt;
					odi_set_tot=odi_set_tot+sum_odiset_amt;
					odi_adj_tot=odi_adj_tot+sum_odiadj_amt;
					
					more = rs.next();
					b_flag=1;
				}
	        //out.println("</table>");
					
					if(b_flag==1){
					//out.println("<table align='center' width='100%' class='table' border='0' >");
		  		out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input align='left' ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(tot_rental)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_net)+"</b></td>"); //tot_vat
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_vat)+"</b></td>");//tot_net
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_tot)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_set)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(0)+"</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(0)+"</b></td>");//--------Sandun on 18-03-2009
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(tot_bal)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_cal_tot)+"</b></td>");
          out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_set_tot)+"</b></td>");
          out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_adj_tot)+"</b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(0)+"</b></td>");//--------Sandun on 18-03-2009
			   	out.println("<td width='10%' class=div_input align='right' ><b>"+nf.format(odi_bal_tot)+"</b></td>");
				  out.println("</tr>");
			    //out.println("</table>");
					}
		     out.println("</table>");  
				
			}
			
			
				else if(m_chksql.equals("SHOW_RENT_DETAIL_DRILL")){
				
				int count = 0;
				int flag=0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");	
					
				     String		Sql_Rent_Drill=" SELECT "+
																    " INSTALLMENT_NO, "+
																    " GRENTAL_AMOUNT, "+
																    " NET_RENTAL_AMOUNT, "+
																    " BALANCE_TO_BE_RECEIVED, "+
																		" RECEIVED_AMOUNT "+
																  //  " RENTAL_DATE "+
																    " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+  
																		" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')  "+
																    " ORDER BY  TO_NUMBER(INSTALLMENT_NO) ";

							
				rs=stmt.executeQuery(Sql_Rent_Drill);
				boolean  more =rs.next();
			

				
					out.println("<HTML><HEAD><TITLE>Rent Details - Application No: "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rent Details - Application No: "+m_application_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  flag=1;
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No: "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input ><b>Installment No</b></td>");
					out.println("<td width='20%' class=div_input align='right'> <b>General Amount</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Net Rental</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Balance Amount</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Received Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					

					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input >"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					
					
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
			
			else if(m_chksql.equals("SHOW_RENT_DETAIL_INVOICED_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");	
				
				double sum_net=0;
				double sum_vat=0;
				double sum_gross=0;
				double sum_set=0;
				double sum_bal=0;
				int   flag=0;
				
					
											String		Sql_Rent_details=" SELECT "+ 
										"  INVOICE_NO, "+
										"  NET_AMOUNT, "+
										"  VAT_AMOUNT, "+
										"  TOTAL_AMOUNT, "+ 
										"  SETTELE_AMOUNT, "+
										"  BALANCE_TO_BE_RECEIVED, "+
										"  TO_CHAR(VALUE_DATE,'DD-MM-YYYY'),VALUE_DATE, "+
										"  DECODE(INVOICE_TYPE,'INV_GENER','General Invoice',INVOICE_TYPE) "+ // added by udara 05-11-2018
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										//" INVOICE_TYPE='INV_GENER' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL') "+
										"  ORDER BY VALUE_DATE";
							
				rs=stmt.executeQuery(Sql_Rent_details);
				boolean  more =rs.next();
			

				
					out.println("<HTML><HEAD><TITLE>Rent Details - Application No: "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rent Details - Application No: "+m_application_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  flag=1;
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No: "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Invoiced Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Net</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("<td width='15%' class=div_input ><b>Invoice Type</b></td>");
		
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					
			

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(9)+"</td>"); // added by udara 05-11-2018
					out.println("</tr>");
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					sum_set=sum_set+rs.getDouble(5);
          sum_bal=sum_bal+rs.getDouble(6);
					
					more = rs.next();
				}
				
					if(flag==0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Total</b> </td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_net)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_vat)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_gross)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_set)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_bal)+"</b></td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>"); // added by udara 05-11-2018
					out.println("</tr>");
				  }
		
				  out.println("</table>");
					
					
					
					
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
			
				else if(m_chksql.equals("SHOW_RENT_DETAIL_SETTLED_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");	
				
				double sum_net=0;
				double sum_vat=0;
				double sum_gross=0;
				double sum_set=0;
				double sum_bal=0;
				int   flag=0;


					
											String		Sql_Rent_details=" SELECT "+ 
										"  INVOICE_NO, "+
										"  NET_AMOUNT, "+
										"  VAT_AMOUNT, "+
										"  TOTAL_AMOUNT, "+ 
										"  SETTELE_AMOUNT, "+
										"  BALANCE_TO_BE_RECEIVED ,"+
										" TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL' AND SETTELE_AMOUNT >0 ) ";
							
				rs=stmt.executeQuery(Sql_Rent_details);
				boolean  more =rs.next();
			

				
					out.println("<HTML><HEAD><TITLE>Rent Details - Application No: "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rent Details - Application No: "+m_application_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  flag=1;
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No: "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Invoiced Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Net</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
		
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					sum_set=sum_set+rs.getDouble(5);
          sum_bal=sum_bal+rs.getDouble(6);
					

					
					more = rs.next();
				}
				
					if(flag==0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Total</b> </td>");
					out.println("<td width='15%' class=div_input><b>&nbsp;</b> </td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_net)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_vat)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_gross)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_set)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_bal)+"</b></td>");
					out.println("</tr>");
		      }
		
				  out.println("</table>");
					
					
					
					
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
				else if(m_chksql.equals("SHOW_RENT_DETAIL_BAL_TO_BE_RECEIVED_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");	
				
				double sum_net=0;
				double sum_vat=0;
				double sum_gross=0;
				double sum_set=0;
				double sum_bal=0;
				int   flag=0;

				
					
											String		Sql_Rent_details=" SELECT "+ 
										"  INVOICE_NO, "+
										"  NET_AMOUNT, "+
										"  VAT_AMOUNT, "+
										"  TOTAL_AMOUNT, "+ 
										"  SETTELE_AMOUNT, "+
										"  BALANCE_TO_BE_RECEIVED, "+
										" TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED >0 )  ORDER BY VALUE_DATE";
							
				rs=stmt.executeQuery(Sql_Rent_details);
				boolean  more =rs.next();
			

				
					out.println("<HTML><HEAD><TITLE>Rent Details - Application No: "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rent Details - Application No: "+m_application_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  flag=1;
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No: "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoiced Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Net</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced VAT</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Gross</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoiced Settled</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
		
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					sum_set=sum_set+rs.getDouble(5);
          sum_bal=sum_bal+rs.getDouble(6);
		
					
					more = rs.next();
				}
				
					if(flag==0){
				 	out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Total</b> </td>");
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_net)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_vat)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_gross)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_set)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_bal)+"</b></td>");
					out.println("</tr>");
		      }
				
		
				  out.println("</table>");
					
					
					
					
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
			
			
			else if(m_chksql.equals("SHOW_RENT_DETAIL_ODI_CAL_AMOUNT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");	
				
				double sum_cal=0;
				double sum_bal=0;
				double sum_set=0;
				double sum_adj=0;
				int   flag=0;

				
					/*
			              String		Sql_Rent_details=" SELECT "+ 
										"  INVOICE_NO, "+
										"  NET_AMOUNT, "+
										"  VAT_AMOUNT, "+
										"  TOTAL_AMOUNT, "+ 
										"  SETTELE_AMOUNT, "+
										"  BALANCE_TO_BE_RECEIVED "+
										" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
										" WHERE     ACTIVE_STATUS='Y' AND "+ 
										" FINANCE_NO IN   "+
										" (SELECT "+
										"  FINANCE_NO "+
										"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
										"  WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
										"         APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED >0 ) "; */
										
										
										
										String		Sql_ODI_Rent_details=" SELECT INVOICE_NO,ODI_CAL_AMOUNT,ODI_BAL_AMOUNT,ODI_SETTLED_AMOUNT,ADJUSTED_AMOUNT "+
										          " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
															" WHERE INVOICE_NO IN  "+
															" (SELECT INVOICE_NO "+
                              " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
															" WHERE FINANCE_NO IN   ( SELECT FINANCE_NO "+
															" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															" WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
															" APPLICATION_STATUS<>'CANCEL' )) ";
										
										
										
										
										
										
							
				rs=stmt.executeQuery(Sql_ODI_Rent_details);
				boolean  more =rs.next();
			

				
					out.println("<HTML><HEAD><TITLE>Rent Details - Application No: "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rent ODI Details - Application No: "+m_application_no+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  flag=1;
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No: "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				
				
				
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Cal Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Settled Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Adjusted Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Balance Amount</b></td>");
					
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					
				  out.println("</tr>");
					
					
					sum_cal=sum_cal+rs.getDouble(2);
					sum_bal=sum_bal+rs.getDouble(3);
					sum_set=sum_set+rs.getDouble(4);
					sum_adj=sum_adj+rs.getDouble(5);
          
		
					
					more = rs.next();
				}
				
					if(flag==0){
				 	out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Total</b> </td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_cal)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_set)+"</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(sum_bal)+"</b></td>");
				  out.println("</tr>");
		      }
				
		
				  out.println("</table>");
					
					
										rs=stmt.executeQuery("SELECT TO_CHAR(odi_date,'Mon-YYYY'),SUM(ODI_CAL_AMOUNT),SUM(ODI_BAL_AMOUNT),SUM(ODI_SETTLED_AMOUNT) "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE INVOICE_NO IN  "+
					" (SELECT INVOICE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					" WHERE FINANCE_NO IN   ( SELECT FINANCE_NO "+
					" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
					" WHERE  UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')"+
					" AND APPLICATION_STATUS<>'CANCEL' ))"+
					" GROUP BY odi_date "+
					" ORDER BY odi_date");
					
					out.println("<br>");
					out.println("<br>");

					more =rs.next();
					if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Month</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Cal Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Settled Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				 }
					
					out.println("<table align='center' width='100%' class='table' >");

					while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					
				  out.println("</tr>");
					more = rs.next();
				}
					
					out.println("</table>");

					
					
					
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}

			
			
			
			
			
			
			else if(m_chksql.equals("SHOW_REALISATION_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
					
				String		Sql_Realisation=" SELECT "+
				"    REC_NO,"+
				"    SETTLE_MODE,"+
				"    PAYER_ACC_NO, "+
				"    PAYER_BRANCH_CODE,"+
				"    REC_AMOUNT, "+
				"    INITCAP(OTH_COMMENTS), "+
				"    DECODE(STATUS,'B','Bank','E','Entered') "+
				"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				"    WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
				"    SETTLE_MODE <>'CASH'  AND "+
				"    STATUS IN('B','E') ";
       

			  rs=stmt1.executeQuery(Sql_Realisation);
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
					out.println("<td width='15%' class=div_input><b>Receipt</b></td>");
					out.println("<td width='15%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Status</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
		       
				
			}
			
			
				else if(m_chksql.equals("SHOW_BALANCES_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");

				
																String		Sql_pending_invoice=" SELECT "+
																"    INVOICE_NO, "+
																"    NET_AMOUNT, "+
																"    VAT_AMOUNT, "+
																"    TOTAL_AMOUNT, "+
																"    SETTELE_AMOUNT, "+
																"    BALANCE_TO_BE_RECEIVED, "+ 
																"    TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+ 
																"   FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																"   WHERE     ACTIVE_STATUS='Y' AND "+
																"   FINANCE_NO IN   "+
																"   (SELECT "+
																"    FINANCE_NO "+ 
																"    FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
																"    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
																"           APPLICATION_STATUS<>'CANCEL' AND BALANCE_TO_BE_RECEIVED>0) ";
           

          

			  rs=stmt1.executeQuery(Sql_pending_invoice);
				boolean  more =rs.next();
						
			
      /*  if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				*/
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Invoice Pending</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Invoice No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Net</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Gross</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Outstanding</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input align='left'>"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
				
					
					more = rs.next();
				}
					out.println("</table>");
				
																

								String		Sql_ODI=" SELECT "+
								 "    ODI_REF_NO, "+
								 "    INVOICE_NO, "+
								 "    TO_DATE(ODI_DATE,'DD-MM-YYYY'), "+
								 "    ODI_CAL_AMOUNT, "+
								 "    ODI_BAL_AMOUNT, "+
								 "    ODI_SETTLED_AMOUNT "+
								 "  FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
								 " WHERE INVOICE_NO IN "+
								 "   (SELECT "+
								 "    INVOICE_NO "+
								 "    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
								 "    WHERE  UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
								 "           ACTIVE_STATUS='Y') "+
								 "   AND    ODI_BAL_AMOUNT=0     ";
     
			    rs=stmt1.executeQuery(Sql_ODI);
				  more =rs.next();

				
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Over Due Interest Pending</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>ODI Ref No</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>ODI Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Balance Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Settled Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
				
				
									String		Sql_Unallocated=" SELECT "+ 
								  "  REC_NO, "+
								  "  NVL(PAYER_ACC_NO,'-'), "+
								  "  NVL(PAYER_BRANCH_CODE,'-'), "+
								  "  REC_AMOUNT, "+
								  "  NVL(ACC_NO,'-'), "+
								  "  NVL(BRANCH_CODE,'-') ,"+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								//  "  STATUS "+
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								  "  WHERE  REC_NO NOT IN  "+
								  "  ( "+
								  "  SELECT "+
								  "  RECEIPT_NO "+
								  "  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
								  "  WHERE ALLOCATION_NO IS NOT NULL "+
								  "  ) AND STATUS <> 'C' "+
									"   AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  ";
     
			    rs=stmt1.executeQuery(Sql_Unallocated);
				  more =rs.next();

				
				
				if (more) {
				
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipt</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input ><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input ><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
				
				
						String		Sql_Realisation=" SELECT "+
				"    REC_NO,"+
  			"    SETTLE_MODE,"+
				"    NVL(PAYER_ACC_NO,'-'), "+
				"    NVL(PAYER_BRANCH_CODE,'-'),"+
				"    REC_AMOUNT, "+
				"    DECODE(STATUS,'B','Banked','E','Entered') ,"+
				"    TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
				"    FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
				"    WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
				"    SETTLE_MODE <>'CASH'  AND "+
				"    STATUS IN('B','E') ";
 
     
			    rs=stmt1.executeQuery(Sql_Realisation);
				  more =rs.next();

				
				
				if (more) {
				
				 
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Receipt Pending Realisation </b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Receipt</b></td>");
					out.println("<td width='10%' class=div_input ><b>Date</b></td>");
					out.println("<td width='10%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input><b>Status</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Receipt Amount</b></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
								String		Sql_POD=" SELECT "+
							  "  POD_REF_NO, "+
							 // "  FINANCE_NO, "+
							  "  NVL(CHEQUE_NO,'-'), "+
							  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),  "+
							  "  NVL(PAYER_ACC_NO,'-'), "+
							  "  NVL(PAYER_BRANCH_CODE,'-'), "+
							  "  CHEQUE_AMOUNT "+
							  "  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
								"  WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
							  "  STATUS='INV'   ";
 
 
     
			    rs=stmt1.executeQuery(Sql_POD);
				  more =rs.next();

				
				
				if (more) {
				
				 
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Post Dated Cheque </b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref.No</b></td>");
					out.println("<td width='15%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='15%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='15%' class=div_input ><b>Payer Branch Code</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>cheque Amount</b></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");

					out.println("</tr>");
					
					more = rs.next();
				}
					out.println("</table>");
				
	   
				
			}
			
			
				else if(m_chksql.equals("SHOW_RETURN_CHEQUE_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
							
					String		Sql_Return_Cheque_info=" SELECT "+ 
				  "  RETURN_NO, "+
				  "   DIPOSIT_NO, "+
				  "   RECEIPT_NO, "+
				  "   AMOUNT, "+
				  "   ALLOCATED_AMOUNT, "+
				  "   BAL_AMOUNT "+
				  " FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS "+
				  " WHERE RECEIPT_NO IN "+
				  "	(SELECT REC_NO FROM  "+
					" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS B "+
					" WHERE A.REC_NO=B.RECEIPT_NO AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
					"       A.STATUS='RET' AND B.ALLOCATION_NO IS NOT NULL "+
					" ) ";
										
					
					
					String		Sql_Return_Cheque_Out_standing=" SELECT "+ 
				  "  RETURN_NO, "+
				  "   DIPOSIT_NO, "+
				  "   RECEIPT_NO, "+
				  "   AMOUNT, "+
				  "   ALLOCATED_AMOUNT, "+
				  "   BAL_AMOUNT "+
				  " FROM "+m_schema_name+".AF_CO_PRO_RETURN_DETAILS "+
				  " WHERE RECEIPT_NO IN "+
					"  (SELECT REC_NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
					"  WHERE REC_NO NOT IN  "+
					"  ( SELECT RECEIPT_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
					"    WHERE ALLOCATION_NO IS NOT NULL "+
					"  ) "+
					"  AND STATUS='RET' AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ) ";
 

					
					
					
					

       

			  rs=stmt1.executeQuery(Sql_Return_Cheque_Out_standing);
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
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>OutStanding</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Return No</b></td>");
					out.println("<td width='15%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allocated Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>");
				
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_deposit_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
				rs=stmt1.executeQuery(Sql_Return_Cheque_info);
				 more =rs.next();

					
					
							if (more) {
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>Non Out Standing</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Return No</b></td>");
					out.println("<td width='15%' class=div_input><b>Deposit No</b></td>");
					out.println("<td width='15%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Allocated Amount</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount</b></td>");
				
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_deposit_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
		       
				
			}
			
			
			//============================Nuwan De Silva 15-06-07========================================
			
			
				else if(m_chksql.equals("SHOW_CAPITAL_BAL_OUT_STANDING_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				
					
       String		Sql_capital_bal_out_standing_info= " SELECT  INSTALLMENT_NO,RENTAL_DATE,CAPITAL_AMOUNT "+

        " FROM "+ 
        " ( "+
        " SELECT A.INSTALLMENT_NO INSTALLMENT_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') RENTAL_DATE ,A.CAPITAL_AMOUNT  CAPITAL_AMOUNT "+
        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
        " WHERE A.INVOICE_NO IS NULL AND "+
        " A.APPLICATION_NO=B.APPLICATION_NO AND "+
        " UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') "+
        
        " UNION "+
        
        " SELECT "+
        " A.INSTALLMENT_NO INSTALLMENT_NO,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') RENTAL_DATE ,A.CAPITAL_AMOUNT  CAPITAL_AMOUNT "+
        " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
        " WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
        " UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
        " INVOICE_NO IN ( "+
        " SELECT "+
        " INVOICE_NO "+
        " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
        " WHERE INVOICE_TYPE='INV_GENER' AND "+
        " ACTIVE_STATUS='Y' AND "+
        " BALANCE_TO_BE_RECEIVED>0  AND "+
        " UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')) "+

        " )        "+

        " ORDER BY TO_NUMBER(INSTALLMENT_NO) ";

										
				

       

			  rs=stmt1.executeQuery(Sql_capital_bal_out_standing_info);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE> Capital Balance OutStanding Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Capital Balance OutStanding Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>Capital OutStanding</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input align='left'><b>Installment No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Rental Date</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Capital Amount</b></td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double total=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input align='left'>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					total=total+rs.getDouble(3);
					more = rs.next();
				}
				 
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input align='left'><b>Total</b></td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(total)+"</b></td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
			
			
		//============================Nuwan De Silva 15-06-07========================================
						
				else if(m_chksql.equals("SHOW_TOTAL_RENTAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				String m_date=req.getParameter("date");

							
	 String		Sql_total_rent_info=" SELECT "+
    " INVOICE_NO, "+
    " TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
		" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
    " NET_AMOUNT, "+
    " VAT_AMOUNT, "+
    " TOTAL_AMOUNT "+
		
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE INVOICE_TYPE='INV_GENER' AND "+
    "       ACTIVE_STATUS='Y' AND "+
    "       UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
		" TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
    " ORDER BY INVOICE_NO ";

      

			  rs=stmt1.executeQuery(Sql_total_rent_info);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Total Rental Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Total Rental Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Value Date</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Due Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Net&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;</b></td>");
					
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(4);
					vat=vat+rs.getDouble(5);
					tot=tot+rs.getDouble(6);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Total</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
			    //added by nwuan de silva on 15-08-07----------------			
				else if(m_chksql.equals("SHOW_TOTAL_RENTAL_DRILL_2")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				//String m_date=req.getParameter("date");

							
	 String		Sql_total_rent_info=" SELECT "+
    " INVOICE_NO, "+
    " TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
		" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
    " NET_AMOUNT, "+
    " VAT_AMOUNT, "+
    " TOTAL_AMOUNT "+
		
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE INVOICE_TYPE='INV_GENER' AND "+
    "       ACTIVE_STATUS='Y' AND "+
    "       UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
		//"AND  TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
    " ORDER BY INVOICE_NO ";

      

			  rs=stmt1.executeQuery(Sql_total_rent_info);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Total Rental Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Total Rental Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Value Date</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Due Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Net&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;</b></td>");
					
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(4);
					vat=vat+rs.getDouble(5);
					tot=tot+rs.getDouble(6);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Total</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
			
			
			
				//============================Nuwan De Silva 15-06-07========================================
						
				else if(m_chksql.equals("SHOW_ARREARS_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				String m_date=req.getParameter("date");

							
	 String		Sql_Arrears=" SELECT "+
    " INVOICE_NO, "+
    " TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
		" TO_CHAR(DUE_DATE,'DD-MM-YYYY'), "+
    " NET_AMOUNT, "+
    " VAT_AMOUNT, "+
    " TOTAL_AMOUNT "+
		
    " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
    " WHERE INVOICE_TYPE='INV_GENER' AND "+
    "       ACTIVE_STATUS='Y' AND "+
		"       BALANCE_TO_BE_RECEIVED >0 AND "+
    "       UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
		" TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
    " ORDER BY INVOICE_NO ";

      

			  rs=stmt1.executeQuery(Sql_Arrears);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Arrears Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Arrears Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Value Date</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Due Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Net&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;</b></td>");
					
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(4);
					vat=vat+rs.getDouble(5);
					tot=tot+rs.getDouble(6);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Total</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
			
						
		//============================Nuwan De Silva 15-06-07========================================
						
				else if(m_chksql.equals("SHOW_RENTAL_PAID_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
			  String m_date=req.getParameter("date");
							
		//comment by nuwan de silva on 10-10-07--------------------------------------------------------------------
	 /*String		Sql_total_rent_info=" SELECT "+
		                    "  DISTINCT INVOICE_NO,TOTAL_AMOUNT,SETTELE_AMOUNT,BALANCE_TO_BE_RECEIVED "+ //RECEIPT_NO,
                        " FROM  "+
                        " (SELECT DISTINCT B.INVOICE_NO INVOICE_NO, "+//1
                       // "  A.RECEIPT_NO RECEIPT_NO,  "+ //2
                        //"  A.RECEIPT_AMOUNT RECEIPT_AMOUNT, "+ //3
                       // "  A.ALLOCATED_DATE ALLOCATED_DATE, "+ //4
                        "  B.TOTAL_AMOUNT TOTAL_AMOUNT, "+ //5
                        "  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+ //6
                        "  B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED "+ //7
                         
                        " FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
                        " C.REC_NO=A.RECEIPT_NO AND "+
                        " C.SETTLE_MODE='CASH' AND "+
                        " C.STATUS='B' AND "+
                        " B.INVOICE_TYPE='INV_GENER' AND "+
                        " B.ACTIVE_STATUS='Y' AND "+
                        " TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
                        " UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+

                        " UNION  "+
                        
                        "  SELECT DISTINCT B.INVOICE_NO  INVOICE_NO, "+
                       // "  A.RECEIPT_NO RECEIPT_NO,  "+
                       // "  A.RECEIPT_AMOUNT RECEIPT_AMOUNT, "+
                       // "  A.ALLOCATED_DATE ALLOCATED_DATE, "+
                        "  B.TOTAL_AMOUNT TOTAL_AMOUNT, "+
                        "  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+
                        "  B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED "+
                        "  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+ 
                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
                        " C.REC_NO=A.RECEIPT_NO AND "+
                        " C.SETTLE_MODE='CHEQUE' AND "+
                        " C.STATUS='REC'  AND "+
                        " B.INVOICE_TYPE='INV_GENER' AND "+
                        " B.ACTIVE_STATUS='Y' AND "+
                        " TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
                        " UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
                        " ) ";
										*/
												
	 												String		Sql_total_rent_info=" SELECT "+
													" DISTINCT B.INVOICE_NO INVOICE_NO, "+
													" B.TOTAL_AMOUNT TOTAL_AMOUNT,  "+
													" B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
													" B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED  "+
													" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
													" WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
													" B.INVOICE_TYPE='INV_GENER' AND  "+
													" B.ACTIVE_STATUS='Y' AND  "+
													" TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
													" UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
													" AND SETTELE_AMOUNT >0 ";


      

			  rs=stmt1.executeQuery(Sql_total_rent_info);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Rental Paid Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rental Paid Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='20%' class=div_input align='left'><b>Invoice No</b></td>");
					//out.println("<td width='15%' class=div_input align='left'><b>Receipt No</b></td>");
					//out.println("<td width='15%' class=div_input align='left'><b>Allocated Date</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Total Amount&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Settle Amount&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Balance Amount&nbsp;</b></td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='20%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					//out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></td>");
					//out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(2);
					vat=vat+rs.getDouble(3);
					tot=tot+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='left' ><b>Total</td>");
					//out.println("<td width='20%' class=div_input align='left'>&nbsp;</td>");
					//out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
        //added by nwuan de silva on 15-08-07----------------			
				else if(m_chksql.equals("SHOW_RENTAL_PAID_DRILL_2")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
			  //String m_date=req.getParameter("date");
							
		
	 String		Sql_total_rent_info=" SELECT "+
		                    "  DISTINCT INVOICE_NO,TOTAL_AMOUNT,SETTELE_AMOUNT,BALANCE_TO_BE_RECEIVED "+ //RECEIPT_NO,
                        " FROM  "+
                        " (SELECT DISTINCT B.INVOICE_NO INVOICE_NO, "+//1
                       // "  A.RECEIPT_NO RECEIPT_NO,  "+ //2
                        //"  A.RECEIPT_AMOUNT RECEIPT_AMOUNT, "+ //3
                       // "  A.ALLOCATED_DATE ALLOCATED_DATE, "+ //4
                        "  B.TOTAL_AMOUNT TOTAL_AMOUNT, "+ //5
                        //"  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+ //6
												"  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+ //6
                        "  B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED "+ //7
                         
                        " FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
                        " C.REC_NO=A.RECEIPT_NO AND "+
                        " C.SETTLE_MODE='CASH' AND "+
                        " C.STATUS='B' AND "+
                        " B.INVOICE_TYPE='INV_GENER' AND "+
                        " B.ACTIVE_STATUS='Y' AND "+
                       // "  TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
                        " UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+

                        " UNION  "+
                        
                        "  SELECT DISTINCT B.INVOICE_NO  INVOICE_NO, "+
                       // "  A.RECEIPT_NO RECEIPT_NO,  "+
                       // "  A.RECEIPT_AMOUNT RECEIPT_AMOUNT, "+
                       // "  A.ALLOCATED_DATE ALLOCATED_DATE, "+
                        "  B.TOTAL_AMOUNT TOTAL_AMOUNT, "+
                       // "  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+
												"  B.SETTELE_AMOUNT SETTELE_AMOUNT, "+ //6
                        "  B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED "+
                        "  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+ 
                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
                        " C.REC_NO=A.RECEIPT_NO AND "+
                        " C.SETTLE_MODE='CHEQUE' AND "+
                        " C.STATUS='REC'  AND "+
                        " B.INVOICE_TYPE='INV_GENER' AND "+
                        " B.ACTIVE_STATUS='Y' AND "+
                       // "  TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND "+
                        " UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
                        " ) ";


      

			  rs=stmt1.executeQuery(Sql_total_rent_info);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Rental Paid Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Rental Paid Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='20%' class=div_input align='left'><b>Invoice No</b></td>");
					//out.println("<td width='15%' class=div_input align='left'><b>Receipt No</b></td>");
					//out.println("<td width='15%' class=div_input align='left'><b>Allocated Date</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Total Amount&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Settle Amount&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input align='right'><b>Balance Amount&nbsp;</b></td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='20%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					//out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></td>");
					//out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(2);
					vat=vat+rs.getDouble(3);
					tot=tot+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='left' ><b>Total</td>");
					//out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					//out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='25%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
						
			
				//============================Nuwan De Silva 15-06-07========================================
						
				else if(m_chksql.equals("SHOW_OUTSTANDING_RENTAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				String m_date=req.getParameter("date");

		 //comment by nuwan de sivla on 10-10-07----------
			/*	String		Sql_outstanding_rental=" SELECT "+
				"  INVOICE_NO  ,  "+
				"  TO_CHAR(VALUE_DATE,'DD-MM-YYYY')   , "+
				"  TO_CHAR(DUE_DATE,'DD-MM-YYYY') ,  "+
				"  NET_AMOUNT ,  "+
				"  VAT_AMOUNT ,   "+
				"  TOTAL_AMOUNT "+
				"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
				"  WHERE INVOICE_TYPE='INV_GENER' AND  "+
				"        ACTIVE_STATUS='Y' AND  "+
				"        UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND  "+
				"	 TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY')  AND "+
				" INVOICE_NO NOT IN (      "+
				"                      SELECT  "+
				"                    DISTINCT INVOICE_NO  "+
				"                      FROM   "+
				"                      (SELECT DISTINCT B.INVOICE_NO INVOICE_NO,  "+
				"                       B.TOTAL_AMOUNT TOTAL_AMOUNT,   "+
				"                       B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
				"                       B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED   "+ 
				
				"                      FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C  "+
				"                      WHERE A.INVOICE_NO=B.INVOICE_NO AND  "+
				"                      C.REC_NO=A.RECEIPT_NO AND  "+
				"                         C.SETTLE_MODE='CASH' AND  "+
				"                         C.STATUS='B' AND  "+
				"                         B.INVOICE_TYPE='INV_GENER' AND  "+
				"                      B.ACTIVE_STATUS='Y' AND   "+
				"                      TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
				"                      UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
				
				"                      UNION  "+
				
				"                       SELECT DISTINCT B.INVOICE_NO  INVOICE_NO,  "+
				"                       B.TOTAL_AMOUNT TOTAL_AMOUNT,  "+
				"                       B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
				"                       B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED  "+
				"                       FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C   "+
				"                      WHERE A.INVOICE_NO=B.INVOICE_NO AND  "+
				"                      C.REC_NO=A.RECEIPT_NO AND  "+
				"                      C.SETTLE_MODE='CHEQUE' AND  "+
				"                      C.STATUS='REC'  AND  "+
				"                      B.INVOICE_TYPE='INV_GENER' AND  "+
				"                      B.ACTIVE_STATUS='Y' AND  "+
				"                      TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
				"                      UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
				"                      )  "+
				"                      ) ";
        */
				
									String		Sql_outstanding_rental=" SELECT "+
													"  DISTINCT B.INVOICE_NO  ,  "+
													"  TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY')   , "+
													"  TO_CHAR(B.DUE_DATE,'DD-MM-YYYY') ,  "+
													"  B.NET_AMOUNT ,  "+
													"  B.VAT_AMOUNT ,   "+
													"  B.TOTAL_AMOUNT "+
													/*" DISTINCT B.INVOICE_NO INVOICE_NO, "+
													" B.TOTAL_AMOUNT TOTAL_AMOUNT,  "+
													" B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
													" B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED  "+
													*/
													" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
													" WHERE A.INVOICE_NO=B.INVOICE_NO AND "+
													" B.INVOICE_TYPE='INV_GENER' AND  "+
													" B.ACTIVE_STATUS='Y' AND  "+
													" TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
													" UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
													" AND BALANCE_TO_BE_RECEIVED >0 ";
          
			  rs=stmt1.executeQuery(Sql_outstanding_rental);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Outstanding Rental Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Outstanding Rental Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Value Date</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Due Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Net&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;</b></td>");
					
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(4);
					vat=vat+rs.getDouble(5);
					tot=tot+rs.getDouble(6);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Total</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");

				
				 				  
		
				 out.println("</table>");
					
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
					       
				
			}
			
			
			//added by nwuan de silva on 15-08-07----------------			
			else if(m_chksql.equals("SHOW_OUTSTANDING_RENTAL_DRILL_2")){
			
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
				//String m_date=req.getParameter("date");

		
	 String		Sql_outstanding_rental=" SELECT "+
   "  INVOICE_NO  ,  "+
   "  TO_CHAR(VALUE_DATE,'DD-MM-YYYY')   , "+
   "  TO_CHAR(DUE_DATE,'DD-MM-YYYY') ,  "+
   "  NET_AMOUNT ,  "+
   "  VAT_AMOUNT ,   "+
   "  TOTAL_AMOUNT "+
	 "  FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
   "  WHERE INVOICE_TYPE='INV_GENER' AND  "+
   "        ACTIVE_STATUS='Y' AND  "+
   "        UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND  "+
	 //"	  TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY')  AND "+
   " INVOICE_NO NOT IN (      "+
   "                      SELECT  "+
	 "                    DISTINCT INVOICE_NO  "+
   "                      FROM   "+
   "                      (SELECT DISTINCT B.INVOICE_NO INVOICE_NO,  "+
   "                       B.TOTAL_AMOUNT TOTAL_AMOUNT,   "+
   "                       B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
   "                       B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED   "+ 
                         
   "                      FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C  "+
   "                      WHERE A.INVOICE_NO=B.INVOICE_NO AND  "+
   "                      C.REC_NO=A.RECEIPT_NO AND  "+
   "                         C.SETTLE_MODE='CASH' AND  "+
   "                         C.STATUS='B' AND  "+
   "                         B.INVOICE_TYPE='INV_GENER' AND  "+
   "                      B.ACTIVE_STATUS='Y' AND   "+
  // "                       TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
   "                      UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+

   "                      UNION  "+
                        
   "                       SELECT DISTINCT B.INVOICE_NO  INVOICE_NO,  "+
   "                       B.TOTAL_AMOUNT TOTAL_AMOUNT,  "+
   "                       B.SETTELE_AMOUNT SETTELE_AMOUNT,  "+
   "                       B.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED  "+
   "                       FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C   "+
   "                      WHERE A.INVOICE_NO=B.INVOICE_NO AND  "+
   "                      C.REC_NO=A.RECEIPT_NO AND  "+
   "                      C.SETTLE_MODE='CHEQUE' AND  "+
   "                      C.STATUS='REC'  AND  "+
   "                      B.INVOICE_TYPE='INV_GENER' AND  "+
   "                      B.ACTIVE_STATUS='Y' AND  "+
  // "                       TO_DATE(TO_CHAR(B.DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')< TO_DATE('"+m_date+"','DD-MM-YYYY') AND  "+
   "                      UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
   "                      )  "+
   "                      ) ";
                       
          
			  rs=stmt1.executeQuery(Sql_outstanding_rental);
				boolean  more =rs.next();
				
						
					out.println("<HTML><HEAD><TITLE>Outstanding Rental Details - Agreement No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Outstanding Rental Details - Agreement No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Agreement No "+m_finance_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%'  class=div_input align='left'><b>No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Value Date</b></td>");
					out.println("<td width='15%' class=div_input align='left'><b>Due Date</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Net&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>VAT&nbsp;</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>Total&nbsp;</b></td>");
					
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double net=0;
				double vat=0;
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>"+count+"</td>");
					out.println("<td width='15%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
					net=net+rs.getDouble(4);
					vat=vat+rs.getDouble(5);
					tot=tot+rs.getDouble(6);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='5%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left' ><b>Total</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='left'>&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(net)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(vat)+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(tot)+"&nbsp;</td>");
					out.println("<td width='*%'>&nbsp </td>");
					out.println("</tr>");
				 out.println("</table>");
				 		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
				
			}
			
			//========================================================
				else if(m_chksql.equals("SHOW_SETTLE_RECEIPT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_receipt_no=req.getParameter("receipt_no");		
				
				
				
						rs= stmt1.executeQuery(" SELECT "+
						  "  REC_NO,"+//1
						  "  NVL(DECODE(STATUS,'E','Entered','B','Banked','C','Cancel','REC','Receipt','RET','Return'),'-'),"+//2 //Bank - Modified by Chandana For Ref no 547 on 31/07/2007
						  "  NVL(DECODE(RECON_STATUS,'Y','Yes','N','No'),'-'),"+//3
						  "  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'),"+//4
						  "  NVL(RECON_BY,'-'),"+//5
						  "  NVL(SUS_REF_NO,'-'),"+//6
						  "  NVL(SETTLE_MODE,'-'),"+//7
						  "  NVL(PAYER_BRANCH_CODE,'-'),"+//8
						  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//9
						  "  NVL(PAYER_ACC_NO,'-'),"+//10
						  "  NVL(ENTRY_TYPE,'-'),"+//11
						  "  NVL(REC_AMOUNT,0),"+//12
						  "  NVL(CLIENT_CODE,'-'),"+//13
						  "  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'),"+//14
						  "  NVL(BRANCH_CODE,'-'),"+//15
						  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'),"+//16
						  "  NVL(ACC_NO,'-'),"+//17
						  "  NVL(OTH_COMMENTS,'-'),"+//18
						  "  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//19
						  "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//20
						  "  NVL(CURR_CODE,'-'),"+//21
						  "  NVL(REC_AMOUNT_CURR,0),"+//22
						  "  NVL(EXCHANGE_RATE_BANK,0),"+//23
						  "  NVL(EXCHANGE_RATE_REP_CURR,0),"+//24
						  "  NVL(EXCHANGE_GAIN_LOSS,0),"+//25
						  "  NVL(REC_AMOUNT_REP_CURR,0),"+//26
						  "  NVL(CHEQUE_NO,'-'),"+//27
						  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//28
						  "  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'),"+//29
						  "  NVL(TENDER_AMOUNT,0),"+//30
						  "  NVL(RETURN_AMOUNT,0),"+//31
						  "  NVL(RENTAL_OTER_INVOICE,0),"+//32
						  "  NVL(INSURANCE,0),"+//33
						  "  NVL(LUXURY_TAX,0),"+//34
						  "  NVL(REVANUE_LICENCE,0),"+//35
						  "  NVL(RMV_CHARGES,0)"+//36
						 "FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
						 "WHERE REC_NO='"+m_receipt_no+"'");
							
							
					


						boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Settlement Receipt Details - Receipt No : "+m_receipt_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Settlement Receipt Details - Receipt No : "+m_receipt_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Receipt No  "+m_receipt_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Receipt No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Recon By</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payer Branch Name</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payer Acc. No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='20%' class=div_input><b>Entry Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(14)+"</u></td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					//wildd
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Other Comments</b></td>"); //Comment - Modified by Chandana For Ref no.758 on 31/07/2007
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>EFF Value Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount Current</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(22))+"</td>");
					out.println("<td width='20%' class=div_input><b>Ex. Rate Bank</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(23))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Ex. Rate Reporting Curr.</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(24))+"</td>");
					out.println("<td width='20%' class=div_input><b>Ex. Gain Loss</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(25))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt Amount Reporting Curr.</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(26))+"</td>");
					out.println("<td width='20%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Banked Date</b></td>"); //Bank - Modified by Chandana For Ref no.758 on 31/07/2007
					out.println("<td width='*%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Tender Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(30))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Return Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(31))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Rental Other Invoice</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(32))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Insurance</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(33))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Luxury Tax</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(34))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Revenue Licence</b></td>");  //Revanue - Modified by Chandana For Ref no.760 on 31/07/2007
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(35))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>RMV Charge</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(36))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
				
				
				rs= stmt1.executeQuery("SELECT NVL(SUM(SETTELED_AMOUNT),0) "+
				    " FROM LAKDL.AF_CO_PRO_INVOICE_DETAILS "+
						" WHERE RECEIPT_NO = '"+m_receipt_no+"' ");
				
				if(rs.next()){
				
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Alocated Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(1))+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
				  }
				
				
					/*	String Sql_Allocation="   SELECT "+
							"   RECEIPT_NO, "+ //1
							"   INVOICE_NO, "+ //2
							"   ALLOCATION_NO, "+ //3 
							"   TO_CHAR(ALLOCATED_DATE,'DD-MM-YYYY'), "+//4
							"   INVOICED_AMOUNT, "+ //5 
							"   SETTELED_AMOUNT, "+//6 -- Allo from this Rec
							"  (INVOICED_AMOUNT - SETTELED_AMOUNT)    "+ 
							"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
							"  WHERE UPPER(RECEIPT_NO)=UPPER('"+m_receipt_no+"') ";*/
				
				//Modified by Mahela on 25-07-2007
				String Sql_Allocation="	SELECT "+
					"  A.RECEIPT_NO, "+ //1
					"  A.INVOICE_NO, "+ //2
					"  A.ALLOCATION_NO, "+ //3 
					"  TO_CHAR(A.ALLOCATED_DATE,'DD-MM-YYYY'), "+ //4
					"  A.INVOICED_AMOUNT, "+ //5 
					"  A.SETTELED_AMOUNT, "+//6  Allo from this Rec
					//  --(A.INVOICED_AMOUNT - A.SETTELED_AMOUNT),
					"  B.SETTELE_AMOUNT, "+//7
					"  B.BALANCE_TO_BE_RECEIVED "+ //8 
				"	FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
				"	WHERE UPPER(A.RECEIPT_NO)=UPPER('"+m_receipt_no+"') "+
				"	AND A.INVOICE_NO=B.INVOICE_NO ";
					
				 rs=stmt1.executeQuery(Sql_Allocation);
				
				boolean  more =rs.next();
				 out.println("<br>");
				  out.println("<hr color='black'>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>Receipt Allocation Details</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No allocation data for Receipt No  "+m_receipt_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='12%' class=div_input align='left' ><b>Allocation No</b></td>");
					out.println("<td width='12%' class=div_input align='left' ><b>Allocation Date </b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Invoice Amount&nbsp</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Allo Amount from this Rec&nbsp</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Settled Amount&nbsp</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Balance Amount&nbsp</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input align='left' style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'  >"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left' >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(5))+"&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(6))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(7))+"&nbsp;</td>");
					out.println("<td width='15%' class=div_input align='right' >"+nf.format(rs.getDouble(8))+"&nbsp;</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
				else if(m_chksql.equals("SHOW_POD_CHEQUE_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
					
					
					String		Sql_Pod_Cheque_Hand=" SELECT "+ 
           "              NVL(POD_REF_NO,'-'), "+//1
           "              NVL(FINANCE_NO,'-'), "+//2
           "              NVL(CHEQUE_NO,'-'), "+//3
           "              NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
           "              NVL(PAYER_ACC_NO,'-'), "+//5
           "              NVL(PAYER_BRANCH_CODE,'-'), "+//6
           "              NVL(CHEQUE_AMOUNT,0) "+//7
           "              FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
           "              WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND STATUS IN ('INV' ,'APP')  " ;

					     

			  rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
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
				  /*out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>Post Dated Cheque Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					*/
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
			
			
			}
			
			
			
				else if(m_chksql.equals("SHOW_STD_ORDER_INFO")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
			
			
				String		Sql_Standing_Order=" SELECT "+ 
				  "  A.SO_NO, "+ //1
					"  A.FINANCE_NO, "+ //1
				  "  TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //2
				  "  TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //3
				  "  NVL(A.ACC_NO,'-'), "+ //4
				  "  NVL(A.BANK_CODE,'-'), "+  //5
				  "  NVL(A.AMOUNT,0) "+ //6
				  "  FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				  "  WHERE  A.FINANCE_NO=B.FINANCE_NO AND UPPER(B.CLIENT_CODE)=UPPER('"+m_client_code+"') AND A.STATUS='Y'		 ";
	
	

			  rs=stmt1.executeQuery(Sql_Standing_Order);
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
				 /* out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b><u>Standing Order Details</u></b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					*/
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Std Ord No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Start Date</b></td>");
					out.println("<td width='12%' class=div_input><b>End Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_std_order_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
				  out.println("<td width='12%' class=div_input  >"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
			
			
			}
			
			
				else if(m_chksql.equals("SHOW_STD_ORDER_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_std_order_no=req.getParameter("std_order_no");		
						
						
					String		Sql_Standing_Order=" SELECT "+ 
			  "  SO_NO, "+
			  "  DECODE(STATUS,'Y','Active','CANCEL','Cancel') STATUS, "+
			  "  FINANCE_NO, "+
			  "  NVL(TO_CHAR(START_DATE,'DD-MM-YYYY'),'-') START_DATE, "+
			  "  NVL(TO_CHAR(END_DATE,'DD-MM-YYYY'),'-') END_DATE , "+
			  "  NVL(BANK_CODE,'-'), "+
			  "  NVL(ACC_NO,'-'), "+
			  "  NVL(AMOUNT,0) "+
			  "  FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS "+
				"  WHERE  UPPER(SO_NO)=UPPER('"+m_std_order_no+"') AND STATUS='Y' ";
				

			  rs=stmt1.executeQuery(Sql_Standing_Order);

				boolean more = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Standing Order Details - Standing Order No: "+m_std_order_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Standing Order Details - Standing Order No: "+m_std_order_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Standing Order No "+m_std_order_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Standing Order No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"<b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Start Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>End Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Bank Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Account No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
				else if(m_chksql.equals("SHOW_FINANCE_DETAIL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");					
				
				
									rs= stmt1.executeQuery(" SELECT "+
									   " A.APPLICATION_NO, "+//1
									   " NVL(DECODE(A.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Application Process','VERIFY1','Credit Verification','V-APP','Credit Score Approval','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Purchase Order','CANCEL','Cancel'),'-'), "+//2
									   " NVL(A.CLIENT_CODE,'-'), "+//3
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+//4
									   " NVL(A.CLIENT_NO,0), "+//5
									   " NVL(A.INQUARY_NO,'-'), "+//6
									   " NVL(A.FINANCE_NO,'-'), "+//7
									   " NVL(A.CO_APPLICANT,'-'), "+//8
									   " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CO_APPLICANT),'-'), "+//9
									   " NVL(A.FACILITY_NO,'-'), "+//10
									   " NVL(A.TOTAL_FINANCE_AMOUNT,0), "+//11
									   " NVL(A.CURRENT_FINANCE_AMOUNT,0), "+//12
									   " NVL(A.CURRENCY_CODE,'-'), "+//13
									   " NVL(A.TRANSACTION_TYPE,'-'), "+//14
									   " NVL(B.DESCRIPTION,'-'), "+//15
									   " NVL(A.ALLO_STATUS,'-'), "+//16
									   " NVL(A.ALLOCATED_TO,'-'), "+//17
									   " NVL(A.COLLECTION_OFFICER,'-'), "+//18
									   " NVL(TO_CHAR(A.ASSIGN_DATE,'DD-MM-YYYY'),'-'), "+//19
									   " NVL(A.TER_STATUS,'-'), "+//20
									   " NVL(A.TER_TYPE,'-'), "+//21
									   " NVL(C.TERMINATION_DESC,'-'), "+//22
									   " NVL(DECODE(A.CLIENT_STATUS,'Y','Yes'),'-'), "+//23
									   " NVL(DECODE(A.GUARANTO_STATUS,'Y','Yes'),'-'), "+//24
									   " NVL(DECODE(A.PRICING_STATUS,'Y','Yes'),'-'), "+//25
									   " NVL(DECODE(A.VALUATION_STATUS,'Y','Yes'),'-'), "+//26
									   " NVL(DECODE(A.PRO_FORMA_STATUS,'Y','Yes'),'-'), "+//27
									   " NVL(DECODE(A.ASSET_STATUS,'Y','Yes'),'-'), "+//28
									   " NVL(A.TERMINATION_NO,'-'), "+//29
									   " NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), "+//30
									   " NVL(DECODE(A.PAYMENT_STATUS,'Y','Yes'),'-'), "+//31
									   " NVL(A.INSURANCE_DONE_BY,'-'), "+//32
									   " NVL(A.POSTED_NAME,'-'), "+//33
									   " NVL(A.POST_ADDRESS1,'-'), "+//34
									   " NVL(A.POST_ADDRESS2,'-'), "+//35
									   " NVL(A.TELEPHONE,'-'), "+//36
									   " NVL(TO_CHAR(A.POSTED_DATE,'DD-MM-YYYY'),'-'),"+//37
									   " NVL(A.PREVIOUS_STATUS,'-'), "+//38
									   " NVL(A.PRIORITY,'-') "+//39
									 "FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B,"+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE C  "+
									 "WHERE A.TRANSACTION_TYPE=B.TRAN_CODE(+) AND A.TER_TYPE=C.TERMINATION_TYPE(+) "+
									 "AND A.FINANCE_NO='"+m_finance_no+"' ");


				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Finance Details - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Finance Details - Finance No : "+m_finance_no+"  </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Finance No  "+m_finance_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(3)+"')><u>"+rs.getString(4)+"</u></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='20%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Co-Applicant Code</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(8)+"')><u>"+rs.getString(8)+"</u></td>");
					out.println("<td width='20%' class=div_input><b>Co-Applicant Name</b></td>");
					out.println("<td width='*%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(8)+"')><u>"+rs.getString(9)+"</u></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td width='20%' class=div_input><b>Total Finance Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(11))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Current Finance Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(12))+"</td>");
					out.println("<td width='20%' class=div_input><b>Currency</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Transaction Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Allocation Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input><b>Allocated To</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Collection Officer</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input><b>Assigned Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%'><u><b>Termination Details</b></u></td>"); 
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Termination No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(29)+"</td>");
					out.println("<td width='20%' class=div_input><b>Termination Status</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Termination Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(23)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Guarantor Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Valuation Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Proforma Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Asset Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payment Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(31)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Activated Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(30)+"</td>");
					out.println("<td width='20%' class=div_input><b>Insurance Done By</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(32)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Posted Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(33)+"</td>");
					out.println("<td width='20%' class=div_input><b>Posted Address</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(34)+", "+rs.getString(35)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Posted Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(37)+"</td>");
					out.println("<td width='20%' class=div_input><b>Tel No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(36)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Previous Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(38)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Priority</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(39)+"</td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			else if(m_chksql.equals("SHOW_POD_CHEQUE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_pod_ref_no=req.getParameter("pod_ref_no");					
				
				
									rs= stmt1.executeQuery(" SELECT "+
									  "  POD_REF_NO,"+//1
									  "  NVL(DECODE(STATUS,'INV','Invoice','ENT','Enter','WIT','Withdraw','REC','Receipt'),'-'),"+//2
									  "  NVL(FINANCE_NO,'-'),"+//3
									  "  NVL(REC_NO,'-'),"+//4
									  "  NVL(SUS_REF_NO,'-'),"+//5
									  "  NVL(CHEQUE_NO,'-'),"+//6
									  "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//7
									  "  NVL(CHEQUE_AMOUNT,0),"+//8
									  "  NVL(SETTLE_MODE,'-'),"+//9
									  "  NVL(PAYER_BRANCH_CODE,'-'),"+//10
									  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//11
									  "  NVL(PAYER_ACC_NO,'-'),"+//12
									  "  NVL(CLIENT_CODE,'-'),"+//13
									  "  NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE),'-'),"+//14
									  "  NVL(ENTRY_TYPE,'-'),"+//15
									  "  NVL(OTH_COMMENTS,'-'),"+//16
									  "  NVL(TO_CHAR(REALISED_DATE,'DD-MM-YYYY'),'-'),"+//17
									  "  NVL(CURR_CODE,'-'),"+//18
									  "  NVL(EXCHANGE_RATE_REP_CURR,0),"+//19
									  "  NVL(REC_AMOUNT_REP_CURR,0)"+//20
									 "FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
									 "WHERE POD_REF_NO='"+m_pod_ref_no+"'");

						boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> POD Cheque Details - POD Ref No : "+m_pod_ref_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> POD Cheque Details - POD Ref No : "+m_pod_ref_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for POD Ref No  "+m_pod_ref_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> POD Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>POD Status</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(4)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>SUS Ref No</b></td>");
					out.println("<td width='50%' class=div_input><b>"+rs.getString(5)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='20%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Cheque Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td width='20%' class=div_input><b>Settle Mode</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payer Branch Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payer Account No</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(13)+"</u></td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='25%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(13)+"')><u>"+rs.getString(14)+"</u></td>");
					out.println("<td width='20%' class=div_input><b></b></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					//wildd
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Entry Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Other Comments</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Realised Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Exchange Rate Reporting Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(19))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Reporting Currency Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(20))+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
     //=====================Nuwan De Silva (ASSET DETAILS)==========================================//
		 //======================15-05-07================================================================//
			
			else if(m_chksql.equals("SHOW_ASSET_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				
				
					out.println("<HTML><HEAD><TITLE> Asset Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Asset Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");


																									
										String		Sql_Asset=  " SELECT "+
								  "  ASSET_ID, "+
								  "  SUB_MODEL_CODE, "+
								  "  MODEL_CODE, "+
								  "  DECODE(STATUS,'N','New','R','Re-Conditioned','U','Used'), "+
								  "  DECODE(PURPOSE,'P','Private Use','B','Business Use') "+
								  "  FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS "+
									"  WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND ACTIVE_STATUS='Y'  ";
								        
													 									
												  

		    rs=stmt1.executeQuery(Sql_Asset);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Asset ID</b></td>");
					out.println("<td width='20%' class=div_input><b>Sub Model Code</b></td>");
					out.println("<td width='20%' class=div_input><b>Model Code</b></td>");
					out.println("<td width='10%' class=div_input><b>Status</b></td>");
					out.println("<td width='20%' class=div_input><b>Purpose</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_asset_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
		 //=====================Nuwan De Silva (PROFORMA INVOICE)==================================//
		 //======================15-05-07================================================================//

				else if(m_chksql.equals("SHOW_PROFORMA_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				
				
					out.println("<HTML><HEAD><TITLE> Proforma Invoice Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Proforma Invoice Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

																		
								String		Sql_Pro_Invoice=  " SELECT "+
						    " INVOICE_NO, "+
						    " NET_PRICE, "+
						    " VAT, "+
						    " TOTAL_AMOUNT "+
						    " FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
						    " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') AND ACTIVE_STATUS='Y'  ";
															 									
												  

		    rs=stmt1.executeQuery(Sql_Pro_Invoice);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Net </b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>VAT</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Gross</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_proforma_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			//=====================Nuwan De Silva (PAYMENT INFORMATION)===============================//
		 //======================15-05-07================================================================//
			
				else if(m_chksql.equals("SHOW_PAYMENT_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
					out.println("<HTML><HEAD><TITLE> Payment Details - Client Code : "+m_client_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Payment Details - Client Code : "+m_client_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

																		
										
								
	String		Sql_Payment=  " SELECT "+
							  "  PAYMENT_NO, "+
							  "  SETTLE_MODE, "+
								"  NVL(DECODE(ENTRY_TYPE,'S','Supplier','E','Seizer','V','Vendor'),'-'), "+
							  "  PAY_AMOUNT, "+
							  "  NVL(DECODE(PROCESS_STATUS,'RE-APP','Requisition Approved','PRINT','Print','CANCEL','Cancel','DISBRS','Disburse','TEMP','Temp','APPRO1','Approve Level 1','APPRO2','Approve Level 2'),'-')    "+
							  "  FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT "+
							  "  WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') ";

															 									
												  

		    rs=stmt1.executeQuery(Sql_Payment);
				
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
					out.println("<td width='20%' class=div_input><b>Payment No</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Settlement Mode </b></td>");
					out.println("<td width='10%' class=div_input align='right' ><b>Entry Type</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Pay Amount&nbsp;&nbsp;</b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Status</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_payment_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input align='right' >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align='right' >"+rs.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right' >"+rs.getString(5)+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			
			//=====================Nuwan De Silva (GROSS DRILL DOWN INFORMATION)===============================//
		 //======================14-06-07================================================================//
			
				else if(m_chksql.equals("SHOW_GROSS_DERAIL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_client_code=req.getParameter("client_code");
				
				
					out.println("<HTML><HEAD><TITLE> Receipt Details - Client Code : "+m_client_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Receipt Details - Client Code : "+m_client_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
								
	String		Sql_Receipts=  " SELECT "+
							  " TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
								" REC_NO, "+
								" DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','STD_ORD','Standing Order','DIR_DIP','Direct Deposit'), "+
								" REC_AMOUNT, "+
								" DECODE(STATUS,'B','Bank','REC','Realise','RET','Return','E','Entered') "+
								" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								" WHERE UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
								" STATUS <>'C' ORDER BY EFF_VALDATE ";
															 									
												  

		    rs=stmt1.executeQuery(Sql_Receipts);
				
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
					out.println("<td width='20%' class=div_input align='left'><b>Date</b></td>");
					out.println("<td width='20%' class=div_input align='left' ><b>Receipt No</b></td>");
					out.println("<td width='20%' class=div_input align='left' ><b>Settlement Mode </b></td>");
					out.println("<td width='20%' class=div_input align='right' ><b>Amount&nbsp;&nbsp;</b></td>");
					out.println("<td width='19%' class=div_input align='left' ><b>Status</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input align='left' >"+rs.getString(1)+"</td>");
					out.println("<td width='20%' class=div_input align='left' style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input align='left' >"+rs.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input align='right' >"+nf.format(rs.getDouble(4))+"&nbsp;</td>");
					out.println("<td width='19%' class=div_input align='left' >"+rs.getString(5)+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			
			//=====================Nuwan De Silva (GUARANTOR INFORMATION)============================//
		 //======================15-05-07================================================================//
			
					else if(m_chksql.equals("SHOW_GUARANTOR_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				
				
					out.println("<HTML><HEAD><TITLE> Guarantor Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Guarantor Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

																		
							
								
								String		Sql_Guarantor=  " SELECT "+
							  "  A.GUARANTOR_CODE, "+
							  "  NVL(B.FULL_NAME,'-'), "+
							  "  NVL(A.RELATIONSHIP,'-') "+
							  "  FROM "+m_schema_name+".AF_CO_PRO_APPLI_GUARANTOR A,  "+m_schema_name+".AF_CO_MAS_CLIENT B "+
							  "  WHERE A.GUARANTOR_CODE=B.CLIENT_CODE AND UPPER(A.APPLICATION_NO)=UPPER('"+m_application_no+"') AND "+
							  "  A.ACTIVE_STATUS='Y' ";


															 									
												  

		    rs=stmt1.executeQuery(Sql_Guarantor);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Guarantor Code</b></td>");
					out.println("<td width='40%' class=div_input  ><b>Guarantor Name </b></td>");
					out.println("<td width='20%' class=div_input  ><b>Relationship</b></td>");
					
					
					out.println("</tr>");
					out.println("</table>");
					//out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='40%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(1)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input >"+rs.getString(3)+"</td>");
					
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}


//=====================Nuwan De Silva (VALUATION INFORMATION)============================//
//======================15-05-07================================================================//
	
		else if(m_chksql.equals("SHOW_VALUATION_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				
				
					out.println("<HTML><HEAD><TITLE> Valuation Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Valuation Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");


				String		Sql_Valuation=  " SELECT "+
																  "  VALUATION_NO, "+
																  "  NVL(REG_NO,'-'), "+
																  "  NVL(ENGINE_NO,'-'), "+
																  "  NVL(CHASSIS_NO,'-'), "+
																  "  VALUE "+
																  "  FROM "+m_schema_name+".AF_CO_PRO_APP_VALUATION  "+
																	" WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"')  ";
																 									
												  

		    rs=stmt1.executeQuery(Sql_Valuation);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Valuation No</b></td>");
					out.println("<td width='15%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='20%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='20%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='15%' align='right' class=div_input><b>Value</b></td>");
					out.println("</tr>");
					out.println("</table>");
			//		out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_valuation_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs.getString(3)+"</td>");
					out.println("<td width='20%' class=div_input >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
//=====================Nuwan De Silva (PRICING INFORMATION)============================//
//======================15-05-07================================================================//
			
			
				else if(m_chksql.equals("SHOW_PRICING_INFORMATION")){
				
				int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				
				
					out.println("<HTML><HEAD><TITLE> Pricing Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Pricing Details - Application No : "+m_application_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");


				
		
				
													
				String		Sql_Pricing=  " SELECT "+
														    " PRICING_NO, "+
														    " PERIOD, "+
														    " GROSS_AMOUNT, "+
														    " VAT_AMOUNT, "+
														    " NET_AMOUNT "+
														    " FROM "+m_schema_name+".AF_MK_PRO_PRICING "+
																" WHERE UPPER(APP_NO)=UPPER('"+m_application_no+"')  ";
												  

		    rs=stmt1.executeQuery(Sql_Pricing);
				
				boolean  more =rs.next();
						
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Application No "+m_application_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='10%' class=div_input><b>Period</b></td>");
					out.println("<td width='20%' align='right' class=div_input><b>Gross Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input><b>Vat Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input><b>Net Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_pricing_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					
					more = rs.next();
				}
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			
				else if(m_chksql.equals("SHOW_CHARGE_AMOUNT_DRILL")){
				
				//int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				String m_chargeble_amount=req.getParameter("chargeble_amount");
				
				double m_Other_Charges=0;
				int m_Count=0;
				double m_NIBSM=0;
				int m_AMI=0;
				int m_PERIOD=0;
				double m_AMI_VALUES=0;
				double m_total_charge=0;
				double m_Invoice_Amount=0;
				double m_amo_charges=0;
				int b_flag_date=0;
				double m_general_amount=0;
				double m_receipt_charges=0;
				double receipt_total=0;
				double m_nibsm_charges=0;
				double tot=0;
				
				
							
				   out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<table  width='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<tr><td align='Center' ><B> Charges Details for  Application No - "+m_application_no+"  </b></td></tr>");
					 out.println("</table>");
						
						
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+m_application_no+"')><u>"+m_application_no+"</u></td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
						
			    out.println("<br>"); 
					
					
					out.println("<table align='center' width='100%' class='table' >");
					
										
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Balance To Be Paid</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(a.abs(Double.parseDouble(m_chargeble_amount)))+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>"); 
  						
				
				rs_invoice= stmt_invoice.executeQuery ("SELECT INVOICE_NO ,PRICING_NO"+ //added by nuwan de silva on 27-08-07
				"  FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
  			 " WHERE APPLICATION_NO=UPPER('"+m_application_no+"') AND "+
				"  PURCHASE_ORDER_NO IS NULL ");
				
						  
			boolean 	more_invoice=rs_invoice.next();	
			String m_invoice_no="";
			String m_pricing_no="";
		
		
		  while(more_invoice)
			{
				m_invoice_no=rs_invoice.getString(1);
				m_pricing_no=rs_invoice.getString(2); //added by nuwan de silva on 27-08-07
				
				//cooment by nuwan de silva on 27-08-07
			/*	rs= stmt.executeQuery ("SELECT "+
 				" NIBSM, "+
 				" AMI, "+
				" PERIOD "+	
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
					
					
				boolean more=rs.next();
				if(more)
				{
				m_NIBSM=rs.getDouble(1);
				m_AMI=rs.getInt(2);
				m_PERIOD=rs.getInt(3);
				
				}
				
				int count=0;
								
				if(m_AMI>0){
				count=(m_PERIOD-m_AMI);
				rs= stmt.executeQuery ("SELECT "+
 				"	SUM(GRENTAL_AMOUNT) SUM "+
        "	FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				"	AND INSTALLMENT_NO >=("+count+") AND AMI_AMOUNT >0  ");

				}
				
        more=rs.next();
				if(more)
				{
				m_AMI_VALUES=rs.getDouble(1);
				
				}
				*/
				
					 rs= stmt.executeQuery ("SELECT "+
					  " NVL("+m_schema_name+".AF_CO_GET_AMI_AMOUNT('"+m_application_no+"','"+m_pricing_no+"','"+m_invoice_no+"'),0) FROM DUAL ");

					boolean	more=rs.next();
						if(more){
						m_AMI_VALUES=rs.getDouble(1);
						}
						rs.close();
          rs= stmt.executeQuery ("SELECT "+
					"  NVL(SUM(AMOUNT),0) "+
					"  FROM  "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES "+
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND "+
					"  CHARGE_TYPE='INV' ");
					
					more=rs.next();
					if(more)
					{
					m_amo_charges=rs.getDouble(1);
					}
				rs.close();
				rs= stmt.executeQuery ("SELECT "+
 				" SUM(GRENTAL_AMOUNT) "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') "+
 				" AND INSTALLMENT_NO=0 ");
					
					more=rs.next();
					if(more)
					{
					m_general_amount=rs.getDouble(1);
					}
				rs.close();
				rs= stmt.executeQuery ("SELECT "+
 				" SUM(NIBSM) "+
 				" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+
 				" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') ");
					
					more=rs.next();
					if(more)
					{
					m_nibsm_charges=rs.getDouble(1);
					}
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><u><b>Charges</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input align='left'><b>Invoice No</b></td>");
					out.println("<td width='20%' class=div_input align='right' style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+m_invoice_no+"')><u>"+m_invoice_no+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					if(m_nibsm_charges>0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>NIBSM Charges</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_nibsm_charges)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					}
					if(m_AMI_VALUES>0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>AMI Charges</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_AMI_VALUES)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
          }
					if(m_amo_charges>0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b><u>Other Charges</b></u></td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");//"+nf.format(m_amo_charges)+"
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
				 rs= stmt.executeQuery ("SELECT "+
		     " A.SUB_CHAGE_CODE, "+
		     " INITCAP(B.DESCRIPTION), "+
		     " AMOUNT "+
		     " FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
		     " WHERE APPLICATION_NO=UPPER('"+m_application_no+"')  AND PRO_INVOICE_NO=UPPER('"+m_invoice_no+"') AND CHARGE_TYPE='INV' AND "+
		     " A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE ");
					more=rs.next();
					
					while(more){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>"+rs.getString(2)+"</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					more=rs.next();
					}
					}
					
					if(m_general_amount>0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>First Installment</b></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(m_general_amount)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");


					}
				out.println("</table>");	
					
				tot=tot+m_nibsm_charges+m_AMI_VALUES+m_amo_charges+m_general_amount;
				more_invoice=rs_invoice.next();
				out.println("<br>");
				
				}
				
					
				  out.println("<table align='center' width='100%' class='table' >");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Total</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(tot)+"<b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
															
      	 	out.println("</table>");

				
				
				
				
				
				
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			
			
				else if(m_chksql.equals("SHOW_DOCUMENT_CHARGE_DRILL")){
				
				//int count = 0;
				String m_string="";								
				
				String m_application_no=req.getParameter("application_no");
				String m_pricing_no=req.getParameter("pricing_no");
				String m_charge_type=req.getParameter("charge_type");
				String m_code=req.getParameter("code");
				double m_total=0;
				
				
rs= stmt.executeQuery ("SELECT "+

"    INITCAP(DESCRIPTION), "+
"    PRO_INVOICE_NO, "+ 
"    AMOUNT "+
"    FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
"    WHERE APPLICATION_NO='"+m_application_no+"' AND PRICING_NO='"+m_pricing_no+"'  AND A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
"    CHARGE_TYPE='"+m_charge_type+"' AND SUB_CHAGE_CODE='"+m_code+"' ");
							
				   out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 
					 out.println("<table  width='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<tr><td align='Center' ><B> Charges Details for  Pricing No - "+m_pricing_no+"  </b></td></tr>");
					 out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='50%' class=div_input >"+m_pricing_no+"</td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
			    out.println("<br>"); 
				boolean 	more=rs.next();
					if(more)
					{
					out.println("<table align='center' width='100%' class='table' >");
					
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='60%' class=div_input><u><b>"+m_charge_type+"</u></b></td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");

			
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='60%' class=div_input><u><b>"+rs.getString(1)+"</u></b></td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");
															
      	 	out.println("</table>");
					
					out.println("<br>"); 
						
				  out.println("<table align='center' width='100%' class='table' >");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Amount<b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
															
      	 	
	
	        }
					
					
					while(more){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					m_total=m_total+rs.getDouble(3);
					
					more=rs.next();
					}
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input  ><b>Total</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(m_total)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					
								
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			//==NUWAN DE SILVA 14-06-07====================================
				else if(m_chksql.equals("SHOW_DOCUMENT_CHARGE_DRILL2")){
				//int count = 0;
				String m_string="";								
				String m_application_no=req.getParameter("application_no");
				String m_pricing_no=req.getParameter("pricing_no");
				String m_code=req.getParameter("code");
				double m_total=0;
rs= stmt.executeQuery ("SELECT "+
"    INITCAP(DESCRIPTION), "+
"    PRO_INVOICE_NO, "+ 
"    AMOUNT, "+
"    CHARGE_TYPE "+ //4
"    FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
"    WHERE APPLICATION_NO='"+m_application_no+"' AND PRICING_NO='"+m_pricing_no+"'  AND A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
"    SUB_CHAGE_CODE='"+m_code+"' ORDER BY CHARGE_TYPE  ");
							
				   out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 
					 out.println("<table  width='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<tr><td align='Center' ><B> Charges Details for  Pricing No - "+m_pricing_no+"  </b></td></tr>");
					 out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='50%' class=div_input >"+m_pricing_no+"</td>");
				  out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
			    out.println("<br>"); 
					
					
			//==
				boolean 	more=rs.next();
					if(more)
					{
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='60%' class=div_input><u><b>"+rs.getString(1)+"</u></b></td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");
      	 	out.println("</table>");
					out.println("<br>"); 
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>Amount<b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
	        }
			String m_charge_type=""; //hold to charge type
			int count_amo=0;
			int count_inv=0;
			while(more) //OTHER CHARGES LOOP STARTED
				{
      m_charge_type=rs.getString(4);	 //ADDED BY NUWAN DE SILVA 18-05-07
			if(m_charge_type.equals("AMO")){
			
			if(count_amo==0){
			out.println("<tr >"); 
			out.println("<td width='1%' >&nbsp</td>"); 
			out.println("<td width='60%' ><b><u>Amotise</u></b></td>"); 
			out.println("<td width='20%' >&nbsp</td>"); 
			out.println("<td width='*%' >&nbsp</td>"); 
			out.println("</tr>"); 
      }
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
       count_amo=1;
			}
			else if(m_charge_type.equals("INV")){
			if(count_inv==0){
			out.println("<tr >"); 
			out.println("<td width='1%' >&nbsp</td>"); 
			out.println("<td width='60%' ><b><u>Up Front</u></b></td>"); 
			out.println("<td width='20%' >&nbsp</td>"); 
			out.println("<td width='*%' >&nbsp</td>"); 
			out.println("</tr>"); 
      }
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			count_inv=1;
			}
			m_total=m_total+rs.getDouble(3);
			more=rs.next();
			
			if (!more)
			{
			break;
			}
			
		}
				  out.println("<tr>");
				 	out.println("<td width='1%'></td>"); 
					out.println("<td width='60%' class=div_input  ><b>Total</b></td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(m_total)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_MISSING_VEHICLE_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_vehicle_no=req.getParameter("vehicle_no");					
	
						rs= stmt1.executeQuery(" SELECT "+
						   " VEHICLE_NO, "+
						   " TO_CHAR(MISSING_DATE,'DD-MM-YYYY'), "+
						   " NVL(ENGIN_NO,'-'), "+
						   " NVL(CHASSISS_NO,'-') "+
						 "FROM "+m_schema_name+".AF_CO_PRO_MISSING_VEHICLES "+
						 "WHERE VEHICLE_NO='"+m_vehicle_no+"' ");

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Missing Vehicle  Details - Vehicle No : "+m_vehicle_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Missing Vehicle  Details - Vehicle No : "+m_vehicle_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Vehicle No "+m_vehicle_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Missing Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Engine No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Chassis No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
				else if(m_chksql.equals("SHOW_MASTER_LEASE_AGREEMENT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_agreement_no=req.getParameter("agreement_no");					
	
							
				rs= stmt1.executeQuery(" SELECT "+
		    " AGREEMENT_NO, "+
			  " CLIENT_CODE, "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
		    " TO_CHAR(START_DATE,'DD-MM-YYYY') START_DATE,  "+
		    " TO_CHAR(END_DATE,'DD-MM-YYYY') END_DATE  "+
				" FROM "+m_schema_name+".AF_CO_MAS_MASTER_AGREEMENT_DET "+
				" WHERE UPPER(AGREEMENT_NO)=UPPER('"+m_agreement_no+"')");
		
		

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Master Lease Agreement Details - Master Lease Ageement No : "+m_agreement_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Master Lease Agreement Details - Master Lease Ageement No : "+m_agreement_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Master Lease Ageement No "+m_agreement_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Master Lease Ageement No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input ><b>Client Code</b></td>");
					out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"')><U>"+rs.getString(2)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Start Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>End Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
			 else if(m_chksql.trim().equals("SHOW_ANNEXURE_DRILL")){
				String m_orient_name="";
				String m_application_no=req.getParameter("application_no");
					
					
				rs = stmt1.executeQuery(" SELECT "+
				" UPPER(COMPANY_NAME) "+
				" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ");
			boolean	more = rs.next();		
				if(more)
				{
				m_orient_name=rs.getString(1);
				}
				
			
					String Sql_Pricing="   SELECT  "+
					
					" TO_NUMBER(INSTALLMENT_NO) ,  "+
					" SUM(NET_RENTAL_AMOUNT),   "+
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,   "+
					" SUM(GRENTAL_AMOUNT)  "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT   "+
					" WHERE  APPLICATION_NO=UPPER('"+m_application_no+"')   "+ //AND
					//" TO_NUMBER(INSTALLMENT_NO) <= "+no_of_records+" "+
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO)   "+
					" ORDER BY TO_NUMBER(INSTALLMENT_NO)  ";
			 			
					rs= stmt1.executeQuery(Sql_Pricing);
					
					boolean more_pricing = rs.next();
					
					out.println("<HTML><HEAD><TITLE> Rental Details - Application No : "+m_application_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
		
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input align='center'><b><u>ANNEXURE</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<br>");
										
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input><b>Installment No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Net Amount&nbsp;&nbsp</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>VAT Amount&nbsp;&nbsp</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Gross Amount&nbsp;&nbsp</b></td>");
					out.println("</tr>");
					
			    double sum_net=0;
					double sum_vat=0;
					double sum_gross=0;
			
				while(more_pricing){
					//count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp</td>");
					out.println("</tr>");
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					
					more_pricing = rs.next();
				}
				 
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input><b>Total<b></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_net)+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_vat)+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_gross)+"&nbsp;&nbsp</td>");
					out.println("</tr>");
		      */
				  out.println("</table>");
					
					out.println("<br><br>Yours faithfully");
					out.println("<br><b>"+m_orient_name.toUpperCase()+"</b>");	
					out.println("<br><br><br><br>Authorized Signatory");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					

			
			}
			
			
			//added by nuwan de silva 17-07-07-----------------------------------
			else if(m_chksql.equals("SHOW_OFFER_DRILL")){
				int count = 0;
				String m_string="";								
				String m_offer_no=req.getParameter("offer_no");					
				
				rs= stmt1.executeQuery(" SELECT "+
				"  A.OFFER_NO, "+ //1
				"  DECODE(A.ACTIVE_STATUS,'Y','Active','N','Cancel'), "+ //2
				"  NVL(A.ADVER_NO,'-'),"+   //3
				"  NVL(A.FULL_NAME,'-'), "+ //4
				"  NVL(A.ADDRESS,'-'), "+//5
				"  NVL(A.TEL_NO,'-'), "+ //6
				"  NVL(B.INVENTORY_NO,'-'), "+ //7
				"  NVL(B.VEHICLE_NO,'-'), "+ //8
				"  NVL(B.RELEASE_TYPE,'-'), "+ //9
				"  NVL(A.AMOUNT,0), "+ //10
				"  NVL(B.OUTSTANDING_VALUE,0), "+ //11
				"  NVL(B.OFFER_VALUE,0), "+ //12
				"  NVL(B.PRINT_STATUS,'-'), "+ //13
				"  NVL(B.OUTSTANDING_INVOICE_VAL,0), "+ //14
				"  NVL(B.TOTAL_OUTSTANDING_VAL,0) "+ //15
				"  FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_OFFERS  A, "+m_schema_name+".AF_RE_ADVTIST_OFFER_VALUES B "+
				"  WHERE A.OFFER_NO=B.OFFER_NO(+) AND  UPPER(A.OFFER_NO)=UPPER('"+m_offer_no+"')");
				
					boolean more_dir = rs.next();
					out.println("<HTML><HEAD><TITLE> Offer Details - Offer No : "+m_offer_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Offer Details - Offer No : "+m_offer_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Offer No "+m_offer_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Offer No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input ><b>Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Advertisement No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Offer Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Offer Address</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Offer Telephone No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inventory No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Release Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(10))+"</td>");
					//out.println("<td width='50%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------
			
			else if(m_chksql.equals("SHOW_OTHER_CLIENT_INFO")){
				String m_string="";				
				String m_client_code=req.getParameter("client_code");
				
				rs= stmt1.executeQuery(" SELECT "+
	  		  "NVL(A.CLIENT_CODE,'-'), "+//1
				  "DECODE(A.CLIENT_TYPE,'I','Individual','Corporate'), "+//2
				  "A.TITLE, "+//3
					"NVL(A.FIRST_NAME,'-'), "+//4
					"NVL(A.SURNAME,'-'), "+//5
					"NVL(A.NIC_NO,'-'), "+//6
					"NVL(A.TEL_NO,'-'), "+//7
					"NVL(A.MOBILE_NO,'-'), "+//8
					"NVL(A.ADDRESS1,'-'), "+//9
					"NVL(A.INITIALS,'-'), "+//10
					"NVL(A.FULL_NAME,'-'), "+//11
					"NVL(A.OTHER_NAME,'-'), "+//12
					"NVL(A.RESIDENTIAL_STATUS,'-'),"+//13
  		    "NVL(A.ADDRESS2,'-'), "+//14
					"NVL(A.OFFICE_TEL_NO,'-'), "+//15
					"NVL(A.FAX_NO,'-'), "+//16
					"NVL(A.EMAIL,'-'), "+//17
					"NVL(A.DURATION_AT_YEARS,0), "+//18
					"NVL(A.DURATION_AT_MONTHS,0), "+//19
					"NVL(A.EMP_NAME,'-'), "+//20
  		    "NVL(A.EMP_ADDRESS1,'-'), "+//21
					"NVL(A.EMP_ADDRESS2,'-'), "+//22
					"NVL(A.EMP_REFERENCE,'-'), "+//23
					"NVL(A.EMP_RDESIGNATION,'-'), "+//24
					"NVL(A.EMP_TEL_NO,'-'), "+//25
					"NVL(A.EMP_FAX_NO,'-'), "+//26
					"NVL(B.NAME,'-'), "+//27
					"NVL(B.ADDRESS1,'-') REL_ADD1, "+//28
					"NVL(B.ADDRESS2,'-') REL_ADD2,"+//29
  		    "NVL(B.RELATIONSHIP,'-'), "+//30
					"NVL(B.HOME_TEL_NO,'-'), "+//31
					"NVL(B.OFFICE_TEL_NO,'-') REL_OFF_TEL, "+//32
					"NVL(B.MOBILE_NO,'-') REL_MOB, "+//33
					"NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-'), "+//34
					"NVL(A.PASSPORT_NO,'-'), "+//35
  		    "NVL(A.NATIONALITY,'-'), "+//36
					"NVL(A.MARITAL_STATUS,'-'), "+//37
					"DECODE(A.GENDER,'M','Male','Female'), "+//38
					"NVL(A.BA_NATURE_OF_BUSINESS,'-'), "+//39
					"NVL(A.BA_PROFESSION,'-'), "+//40
					"NVL(A.BA_QUALIFICATIONS,'-'), "+//41
					"NVL(A.BA_DESIGNATION,'-'), "+//42
					"NVL(A.NO_OF_CHILDREN,0), "+//43
  		    "NVL(A.DEPENDENTS,0), "+//44
					"NVL(A.CITY_CODE,'-'), "+//45
					"NVL(A.VAT_REG_NO,'-'), "+//46
					"NVL(A.DRIVING_LICENSE_NO,'-'), "+//47
					"NVL(A.POSTALCODE,'-') POSTALCODE, "+//48
					"NVL(A.GRIB_NO,'-'),  "+//49
					"DECODE(A.ACTIVE_STATUS,'Y','Active','N','Deactive','E','Initial Credit Approval', "+
				  "'I','Waiting for Credit Approval','T','Terminated','B','Black Listed','Other'), "+//50
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.CITY_CODE,'C'), "+//51
					"NVL(A.REGISTERED_ADDRESS1,'-'), "+//52
  			  "NVL(A.REGISTERED_ADDRESS2,'-'), "+//53
  			  "NVL(A.REGISTERED_CITY_CODE,'-'), "+//54
					" "+m_schema_name+".FA_GET_CLIENT_CITY_AREA(A.REGISTERED_CITY_CODE,'C'), "+//55
					"NVL(A.KEY_DECISION_MAKER,'-'), "+//56
					"NVL(A.CONTACT_FOR_PAYMENT,'-'), "+//57
					"NVL(A.DESIGNATION,'-'), "+//58
					"A.REGISTERED_STATUS, "+//59
  				"NVL(A.CORRESPONDENCE_STATUS,'-'), "+//60
  				"NVL(A.F_TEL_NO,'-'), "+//61
  				"NVL(A.F_FAX_NO,'-'), "+//62
  				"NVL(A.F_EMAIL,'-'), "+//63
  				"NVL(A.ISSUED_SHARE_CAPITAL,0), "+//64
  				"NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'), "+//65
  				"NVL(A.VAT_REG_NO,'-'), "+//66
  				"NVL(TO_CHAR(A.VAT_REG_DATE,'DD-MM-YYYY'),'-'), "+//67
					"NVL(A.BUSINESS_SUB_SECTOR,'-'),"+//68
          "NVL(A.CLIENT_CATEGORY,'-'), "+//69
					"NVL(A.BUSINESS_CERTIFICATE_NO,'-'), "+//70
					"NVL(FACTORY_ADDRESS1,'-'), "+//71
    			"NVL(FACTORY_ADDRESS2,'-'), "+//72
  			  "NVL(FACTORY_STATUS,'-'),  "+//73
			    "NVL(F_CONTACT_PERSON,'-'), "+//74
					"NVL(TEL_NO_GEN,'-'), "+//75
  			  "NVL(FAX_NO_GEN,'-'), "+//76
  			  "NVL(EMAIL_GEN,'-'), "+//77
  			  "NVL(DRIVING_LICENSE_NO,'-') "+//78
				  "FROM "+m_schema_name+".AF_CO_MAS_CLIENT A , "+m_schema_name+".AF_CO_MAS_CLIENT_RELATIVE B "+
				  "WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND A.CLIENT_CODE=B.CLIENT_CODE(+) ");
				
				
				if(rs.next()){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>CRIB No</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(49)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='30%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Business Sub Sector</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(68)+"</td>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Client Category</b></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(69)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					if(rs.getString(2).equals("Corporate")){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Business Certification No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(70)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Incorporation</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(65)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Issued share Capital Rs.</b></td>");
						out.println("<td width='30%' class=div_input>"+nf.format(rs.getDouble(64))+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>VAT Reg. No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(66)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>VAT Reg. Date</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(67)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					else{
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Title</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input></td>");
						out.println("<td width='30%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>First Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Last Name</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Initials</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(10)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Other Names</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(12)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>NIC No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Passport No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(35)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Date of Birth</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(34)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>No of Children</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(43)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input><b>Driving License No</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(78)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Dependents</b></td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(44)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='30%' class=div_input><b>Business Activities</b></td>");
						out.println("<td width='50%' class=div_input></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Nature of Business</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(39)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input>Profession</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(40)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' class=div_input>Designation</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(42)+"</td>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input>Qualifications</td>");
						out.println("<td width='30%' class=div_input>"+rs.getString(41)+"</td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
					}
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Factory Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Factory Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(73)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(71)+" "+rs.getString(72)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Contact Person</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(74)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(61)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(62)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(63)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Correspondence Information</b></td>");
					out.println("<td width='50%' class=div_input></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Status</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(60)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Address </td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+" "+rs.getString(14)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Telephone No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(75)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>FAX No</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(76)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Email Address</td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(77)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					
				}
			  else{
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
			}		
			
			
			
			
				else if(m_chksql.equals("SHOW_OS_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Balance Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Balance Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_inv_os_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
				else if(m_chksql.equals("SHOW_SET_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Collection Details - Collection Officer : "+m_user_id+" &nbsp;as at :"+m_date+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Collection Details - Collection Officer : "+m_user_id+" &nbsp;as at :"+m_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Collected Amount </b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_inv_collect_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
				else if(m_chksql.equals("SHOW_BAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Balance Details - Collection Officer : "+m_user_id+" &nbsp;as at :"+m_date+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Balance Details - Collection Officer : "+m_user_id+" &nbsp;as at :"+m_date+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) - NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND (NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) - NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount </b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_inv_bal_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
						
			}
			
			else if(m_chksql.equals("SHOW_INVOICED_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Invoiced Details Current Month - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoiced Details Current Month - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Invoiced Amount </b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
			
			else if(m_chksql.equals("SHOW_INVOICED_SET_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Collection Details Current Month - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Collection Details Current Month - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									//   NVL(LAKDL.AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'25-05-2007',FINANCE_NO),0)
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Collected Amount </b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					//out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_asset_detail_drill('"+rs.getString(4)+"') >"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_settled_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
		       
				
			}
			
				else if(m_chksql.equals("SHOW_INVOICED_SET_BAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
				//out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Invoiced Balace Details Current Month - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoiced Balance Details Current Month - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)-NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									//   NVL(LAKDL.AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'25-05-2007',FINANCE_NO),0)
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND (NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)-NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					//out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_asset_detail_drill('"+rs.getString(4)+"') >"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_cur_month_invoiced_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
						   				
			}
			
			else if(m_chksql.equals("SHOW_TOT_INV_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			//	out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Total Invoiced Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Total Invoiced Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) + NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)"+
									//" NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND (NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) + NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Invoiced</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_invoiced_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				
		
				  out.println("</table>");
					
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
					
			}
			
			else if(m_chksql.equals("SHOW_TOT_INV_SET_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			//	out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Total Collection Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Total Collection Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) +NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									//" NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									" AND (NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) +NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0)) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Collection</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_settled_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("</tr>");
					tot=tot+rs.getDouble(5);
					more = rs.next();
				}
				
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			else if(m_chksql.equals("SHOW_TOT_INV_BAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			//	out.println("date"+m_date);
					out.println("<HTML><HEAD><TITLE> Total Balance Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Total Balance Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									
									String		Sql_os=  " SELECT "+
									" CLIENT_CODE, "+
									" COLLECTION_OFFICER, "+
									" APPLICATION_NO, "+
									" FINANCE_NO, "+
									" NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) + NVL("+m_schema_name+".AF_CO_GET_INV_THIS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0),"+
									" NVL("+m_schema_name+".AF_CO_GET_SETTLE_AMOUNT_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) +NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									//" NVL("+m_schema_name+".AF_CO_GET_SET_INV_THS_MON_AGR(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
									" WHERE UPPER(COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" AND APPLICATION_STATUS='ACTIVATED' "+
									//" AND NVL("+m_schema_name+".AF_CO_GET_CF_BAL_AGRMENT(COLLECTION_OFFICER,'"+m_date+"',FINANCE_NO),0) >0 "+
									" ORDER BY CLIENT_CODE ";

		    rs=stmt1.executeQuery(Sql_os);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Application No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='25%' class=div_input><b>Finance no</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Balance</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					bal=rs.getDouble(5)-rs.getDouble(6);
					if(bal>0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_application_detail_drill('"+rs.getString(3)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right'   class=div_input style= cursor:hand; onclick=show_collection_detail_total_balance_drill('"+m_user_id+"','"+m_date+"','"+rs.getString(4)+"') ><u>"+nf.format(bal)+"</u></td>");
					out.println("</tr>");
					}
					tot=tot+bal;
					more = rs.next();
				}
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>&nbsp;</b></td>");
					out.println("<td width='25%' class=div_input><b>Total</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(tot)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
				else if(m_chksql.equals("SHOW_COLL_OFFICER_CONTRACT_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			
					out.println("<HTML><HEAD><TITLE> Contract Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Contract Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									String		Sql_contract=  " SELECT "+
									" FINANCE_NO, "+
									" "+m_schema_name+".AF_CO_GET_USER_NAME(MK_OFFICER), "+
									" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
									" CLIENT_CODE, "+
									" NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-') ACTIVATED_DATE, "+
									" APPLICATION_STATUS, "+
									" COLLECTION_OFFICER, "+
									" NVL(TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),'-') ASSIGN_DATE "+
									" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
									" WHERE COLLECTION_OFFICER=UPPER('"+m_user_id+"') AND APPLICATION_STATUS='ACTIVATED' "+
									" AND  A.INQUARY_NO=B.INQUIRY_CODE "+
									" ORDER BY FINANCE_NO ";

		    rs=stmt1.executeQuery(Sql_contract);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='20%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='20%' class=div_input><b>Activated Date</b></td>");
					out.println("<td width='20%' class=div_input><b>Officer Assign Date</b></td>");
					out.println("<td width='20%' class=div_input><b>Mkt.Officer</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(4)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='20%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("</tr>");
					more = rs.next();
				}
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
				else if(m_chksql.equals("SHOW_COLL_OFFICER_UN_ALL_REC_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			
					out.println("<HTML><HEAD><TITLE> Un Allocated Receipt Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Un Allocated Receipt Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

									String		Sql_unallocated=  " SELECT "+
									" B.CONTRACT_NO,"+
									" C.CLIENT_CODE,"+
									" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),"+
									" B.REC_NO,"+
									" NVL(SUM(BAL_TOBE_RECEIVE),0)   "+
									" FROM  "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL A, "+m_schema_name+"DATA.AF_CO_PRO_RECEIPT_DET B,  "+
									" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C  "+
									" WHERE B.REC_NO=A.REC_NO    "+
									" AND   B.CONTRACT_NO=C.FINANCE_NO   "+
									" AND   C.APPLICATION_STATUS='ACTIVATED'  "+
									" AND   UPPER(C.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
									" GROUP BY B.CONTRACT_NO,C.CLIENT_CODE,B.REC_NO ";


		    rs=stmt1.executeQuery(Sql_unallocated);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='25%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='25%' class=div_input><b>Receipt No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					more = rs.next();
				}
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
				else if(m_chksql.equals("SHOW_DETAIL_INV_OS_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
				
				//out.println("m_user_id"+m_user_id);
				//out.println("m_date"+m_date);
				//out.println("m_finance_no"+m_finance_no);
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

											
							String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+ 
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) ";



		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
				else if(m_chksql.equals("SHOW_DETAIL_INV_COLLECT_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

											
							String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+ 
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(3);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			else if(m_chksql.equals("SHOW_DETAIL_INV_BAL_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

												
							String		Sql_detail_inv=  " SELECT "+
							" XX.INVOICE_NO, XX.TOTAL, NVL(XX.SETT,0)+NVL(YY.SETTELED_AMOUNT,0), NVL(XX.BALANCE,0) - NVL(YY.SETTELED_AMOUNT,0)  "+
							" FROM  "+
							" (SELECT A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE  "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT  "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO  "+
							" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))    "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"')  "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+ 
							" AND   B.APPLICATION_STATUS='ACTIVATED'  "+
							" AND   A.ACTIVE_STATUS='Y'   "+
							" GROUP BY A.INVOICE_NO)  A,  "+
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT  "+ 
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C   "+
							" WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))     "+
							" AND   C.INVOICE_NO IN (  "+
							" SELECT INVOICE_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))     "+ 
							" AND   A.FINANCE_NO=B.FINANCE_NO  "+
							" AND   B.APPLICATION_STATUS='ACTIVATED'  "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"')  "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
							" AND   A.ACTIVE_STATUS='Y' "+
							" )GROUP BY C.INVOICE_NO  )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) )XX, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT  "+
							" FROM  LAKDL.AF_CO_PRO_INVOICE_DETAILS C   "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)    "+ 
							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY')  "+
							" AND   C.INVOICE_NO IN (  "+
							" SELECT INVOICE_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B  "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))     "+
							" AND   A.FINANCE_NO=B.FINANCE_NO  "+
							" AND   B.APPLICATION_STATUS='ACTIVATED'  "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"')  "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"')  "+
							" AND   A.ACTIVE_STATUS='Y' ) "+ 
							" GROUP BY C.INVOICE_NO )YY  "+
							" WHERE XX.INVOICE_NO=YY.INVOICE_NO(+)  ";

							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
						
			//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		
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


