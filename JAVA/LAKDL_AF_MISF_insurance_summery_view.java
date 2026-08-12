//Created by Minal for #14286 on 10-10-2014
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class LAKDL_AF_MISF_insurance_summery_view extends javax.servlet.http.HttpServlet {
	/*
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt, stmt1, stmt2, stmt3;
	java.text.NumberFormat nf, nf1;
	public ResultSet rs, rs1, rs2, rs3;
	public String m_chksql;
	*/
	
	public  void service(HttpServletRequest req, HttpServletResponse res) throws IOException { // synchronized
		
		ServletOutputStream out = null;
		Connection conn = null;
		Statement stmt=null, stmt1=null, stmt2=null, stmt3=null;
		java.text.NumberFormat nf=null, nf1=null;
		ResultSet rs=null, rs1=null, rs2=null, rs3=null;
		String m_chksql=null;
		//String m_username = null;
		
		try {			
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_schema_name = m_sn_methods.schema_name;
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			//String m_username=m_sn_methods.username;
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2); 
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);   
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			m_chksql=req.getParameter("chksql");
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement(); 
			stmt3=conn.createStatement();
			CallableStatement callstmt1 =null;
			String m_username 	= m_sn_methods.username;
			
			String m_from_date=req.getParameter("from_date");
			String m_to_date=req.getParameter("to_date");
			String m_branch_code=req.getParameter("branch_code"); //Added by Kanchana Karunarathna.
			
//out.println(m_username);
			
			if(m_chksql.equals("main_page")){ 
				
				out.println("<HTML><HEAD><TITLE>Asset Financing System</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_drill(m_branch,m_insurance_comp,m_insurance_done_by){ ");
				out.println(" var m_branch_code='"+m_branch_code+"' ; ");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_insurance_summery_view?chksql=show_drill_window&branch=\"+m_branch+\"&insurance_comp=\"+m_insurance_comp+\"&insurance_done_by=\"+m_insurance_done_by+\"&branch_code=\"+m_branch_code;");
				out.println("   window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Insurance Summary Report  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	

				out.println("<table align='center' class='table' >");

				int item_count = 0;
				/*
				rs= stmt.executeQuery(" "+
								" SELECT "+
								"    COUNT(INSUR_COM) "+
										" FROM (  "+
										" SELECT DISTINCT INSUR_COM INSUR_COM "+
										" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
								
								" ) "+
							" ");
				*/
				
				
				// commented by udara 10-12-2015
				/*
				rs= stmt.executeQuery(" "+
								" SELECT "+
								"    COUNT(INSUR_COM) "+
										" FROM (  "+
										" SELECT DISTINCT A.INSUR_COM INSUR_COM "+
										" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT A "+
										" WHERE A.VALUE_AMOUNT > 0 "+ // added by udara 04-09-2015
										" AND A.ENT_USER = '"+m_username+"' "+ // added by udara 08-09-2015
										//" WHERE  (SELECT SUM(VALUE_AMOUNT) "+ // commented by udara 04-09-2015
						                //			" FROM  "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+ // commented by udara 04-09-2015
						                // 			" WHERE INSUR_COM = A.INSUR_COM) > 0 "+ // commented by udara 04-09-2015
													" AND TRUNC(A.START_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara 04-09-2015
              										" AND TRUNC(A.START_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara 04-09-2015
								
								" ) "+
							" ");
				*/
				
				// added by udara 10-12-2015
				rs= stmt.executeQuery(" "+
						" SELECT COUNT(INSUR_COM) FROM (  "+         
					      " SELECT INSUR_COM INSUR_COM, TOTAL "+
					      " FROM ( "+
					                " SELECT  "+
					                         " INSUR_COM INSUR_COM, "+
					                         " "+m_schema_name+".GET_INSUR_SUM_RPT_TOTAL(INSUR_COM,'"+m_from_date+"','"+m_to_date+"','"+m_username+"') TOTAL "+
					                         " FROM (   "+
							                         " SELECT DISTINCT A.INSUR_COM INSUR_COM "+
							                         " FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT A  "+
							                         " WHERE A.VALUE_AMOUNT > 0 "+
							                         " AND A.ENT_USER = '"+m_username+"' "+
							                         " AND TRUNC(A.START_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
							                         " AND TRUNC(A.START_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+ 
					                    			 " AND A.BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
												" ) "+
					      " ) "+
					      " WHERE TOTAL > 0 "+
						  " ) ");
				// end by udara 10-12-2015
				
			
				if(rs.next()){
					item_count = rs.getInt(1);
				}
				
				String [][] arr_rpt  = new String [2][item_count];

				/*
				rs= stmt.executeQuery(" "+
								" SELECT "+
								"    INSUR_COM, "+
								"    NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-')  "+
										" FROM (  "+
										" SELECT DISTINCT INSUR_COM INSUR_COM "+
										" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
								
								" ) "+
							" ");
				*/
				
				
				
				// commented by udara 10-12-2015
				
				// commented by udara 10-12-2015
				/*
				rs= stmt.executeQuery(" "+
								" SELECT "+
								"    INSUR_COM, "+
								"    NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-')  "+
										" FROM (  "+
										" SELECT DISTINCT A.INSUR_COM INSUR_COM "+
										" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT A "+
										" WHERE A.VALUE_AMOUNT > 0 "+ // added by udara 04-09-2015
										" AND A.ENT_USER = '"+m_username+"' "+ // added by udara 08-09-2015
										//" WHERE  (SELECT SUM(VALUE_AMOUNT) "+ // commented by udara 04-09-2015
						                //			" FROM  "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+ // commented by udara 04-09-2015
						                //			" WHERE INSUR_COM = A.INSUR_COM) > 0 "+ // commented by udara 04-09-2015
													" AND TRUNC(A.START_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ // added by udara 04-09-2015
              										" AND TRUNC(A.START_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ // added by udara 04-09-2015
								" ) "+
							" ");
				
				*/
				
				// added by udara 10-12-2015
				rs= stmt.executeQuery(" "+
						" SELECT INSUR_COM, "+
		                     " PAYEE_NAME, "+
		                     " TOTAL FROM ( "+
		                            " SELECT  "+
		                            " INSUR_COM INSUR_COM,  "+
		                            " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') PAYEE_NAME, "+
		                            " "+m_schema_name+".GET_INSUR_SUM_RPT_TOTAL(INSUR_COM,'"+m_from_date+"','"+m_to_date+"','"+m_username+"') TOTAL "+
		                             " FROM (   "+
		                                 " SELECT DISTINCT A.INSUR_COM INSUR_COM  "+
		                                 " FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT A  "+
		                                 " WHERE A.VALUE_AMOUNT > 0  "+
		                                 " AND A.ENT_USER = '"+m_username+"'  "+
		                                 " AND TRUNC(A.START_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+ 
		                                 " AND TRUNC(A.START_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
					                     " AND A.BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
									" ) "+
		                    " ) "+
		                    " WHERE TOTAL > 0 "+
							" ");
				
				// end by udara 10-12-2015
				
				int i = 0;
				
				while(rs.next()){
					
					arr_rpt[0][i] = rs.getString(1);
					arr_rpt[1][i] = rs.getString(2);
					i++;
					
				}
				
				
				out.println("<table width='100%' class='table' border=1 >");	
			
				// ===================== Heading Start =========================================
				out.println("<tr>");
				
				out.println("<td width='1%' colspan=2 ><b> &nbsp; </b></td>"); 

				for(int x=0; x<item_count; x++){
					out.println("<td width='5%' colspan=2 align=\"center\" ><b> "+arr_rpt[1][x]+" </b></td>"); // out.println("<td width='5%' colspan=2 align=\"center\" ><b> "+arr_rpt[0][x]+" - "+arr_rpt[1][x]+"</b></td>"); 
				}
				
				out.println("<td width='5%' colspan=2 ><b>Total</b></td>");
			    out.println("<td width='5%' colspan=2 ><b>Upgrades</b></td>"); 
				out.println("<td width='5%' colspan=2 ><b>Total</b></td>"); 

				out.println("</tr>"); 
				
				out.println("<tr>");				
			
				out.println("<td width='1%' ><b>No.</b></td>"); 
				out.println("<td width='5%' ><b>Branch</b></td>"); 
				
				for(int x=0; x<item_count; x++){
					out.println("<td width='5%'  ><b>No. of cases </b></td>");
					out.println("<td width='5%'  ><b>Value</b></td>"); 
				}
				
				out.println("<td width='5%'  ><b>No. of cases </b></td>");
				out.println("<td width='5%'  ><b>Value</b></td>");
			    
				out.println("<td width='5%'  ><b>No. of cases </b></td>");
				out.println("<td width='5%'  ><b>Value</b></td>");
				
				out.println("<td width='5%'  ><b>No. of cases </b></td>");
				out.println("<td width='5%'  ><b>Value</b></td>");
				
				
				out.println("</tr>"); 
				
				
				// ===================== Heading End ============================================
				
				rs= stmt.executeQuery(" "+
						" SELECT LOCATION_CODE, "+
						" "+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE) "+
						" FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
						" WHERE ACTIVE_STATUS = 'Y' "+
						" AND LOCATION_CODE LIKE '"+m_branch_code+"%' "+  // Added by Kanchana for issue no 18429
						" ORDER BY "+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE) ASC  "+
				" ");
				
				
				int row_count = 1;
				double branch_comp_total = 0;
				double upgrades = 0;
				double rowtotal = 0;
				
				int branch_comp_total_cases = 0;
				int upgrades_cases = 0;
				int rowtotal_cases = 0;
				
				// main while
				while(rs.next()){
					
					out.println("<tr>");
					out.println("<td width='1%' > "+row_count+" </td>"); 
					out.println("<td width='5%' > "+rs.getString(2)+" </td>"); // out.println("<td width='5%' > "+rs.getString(1)+" - "+rs.getString(2)+" </td>"); 
					
					branch_comp_total_cases = 0;
					branch_comp_total = 0;
					
					// =========================== dynamic columns start ===============================================
					for(int x=0; x<item_count; x++){
						
						rs1= stmt1.executeQuery(" "+
							" SELECT COUNT(VALUE_AMOUNT) "+
							" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
							" WHERE ENT_USER = '"+m_username+"' "+
							" AND BRANCH_CODE = '"+rs.getString(1)+"' "+
							//" AND INSURANCE_DONE_BY = 'LICENSEE'  "+
							" AND INSUR_COM = '"+arr_rpt[0][x]+"' "+   
							" AND REF_DEBIT_NOTE_NO IS NOT NULL  "+ // added by udara 26-01-2015
						" ");
						
						if(rs1.next()){
							branch_comp_total_cases = branch_comp_total_cases + rs1.getInt(1);
							out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','"+arr_rpt[0][x]+"','LICENSEE');\" ><u> "+rs1.getInt(1)+" </u></td>"); 
						}
						
						rs1= stmt1.executeQuery(" "+
							" SELECT SUM(VALUE_AMOUNT) "+
							" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
							" WHERE ENT_USER = '"+m_username+"' "+
							" AND BRANCH_CODE = '"+rs.getString(1)+"' "+
							//" AND INSURANCE_DONE_BY = 'LICENSEE'  "+
							" AND INSUR_COM = '"+arr_rpt[0][x]+"' "+
							" AND REF_DEBIT_NOTE_NO IS NOT NULL  "+ // added by udara 26-01-2015 
							" ");
						
						if(rs1.next()){
							branch_comp_total = branch_comp_total + rs1.getDouble(1);
							out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','"+arr_rpt[0][x]+"','LICENSEE');\" ><u> "+nf.format(rs1.getDouble(1))+" </u></td>"); 
						}
	
					}
					// =========================== dynamic columns end ==================================================
					
					out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','LICENSEE');\" ><b><u> "+branch_comp_total_cases+" </u></b></td>");
					out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','LICENSEE');\" ><b><u> "+nf.format(branch_comp_total)+" </u></b></td>");
					
					rs2= stmt2.executeQuery(" "+
							" SELECT COUNT(VALUE_AMOUNT) "+
							" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
							" WHERE ENT_USER = '"+m_username+"' "+
							" AND BRANCH_CODE = '"+rs.getString(1)+"' "+
							" AND INSURANCE_DONE_BY = 'CLIENT'  "+
							" AND REF_DEBIT_NOTE_NO IS NULL  "+ // added by udara 26-01-2015 
						" ");
					
					upgrades_cases = 0;
			    	if(rs2.next()){
							upgrades_cases = rs2.getInt(1);
							out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','CLIENT');\" ><u> "+upgrades_cases+" </u></td>"); 
					}
					
					
					rs2= stmt2.executeQuery(" "+
							" SELECT SUM(VALUE_AMOUNT) "+
							" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
							" WHERE ENT_USER = '"+m_username+"' "+
							" AND BRANCH_CODE = '"+rs.getString(1)+"' "+
							" AND INSURANCE_DONE_BY = 'CLIENT'  "+
							" AND REF_DEBIT_NOTE_NO IS NULL  "+ // added by udara 26-01-2015 
						" ");
					
					upgrades = 0;
			    	if(rs2.next()){
							upgrades = rs2.getDouble(1);
							out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','CLIENT');\" ><u> "+nf.format(upgrades)+" </u></td>"); 
					}
					
					rowtotal = 0;
					rowtotal_cases = 0;
					rowtotal = branch_comp_total + upgrades;
					rowtotal_cases = branch_comp_total_cases + upgrades_cases;
					
					out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','ALL');\" ><b><u> "+rowtotal_cases+" </u></b></td>"); 
					out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL','ALL');\" ><b><u> "+nf.format(rowtotal)+" </u></b></td>"); 
					
					
					out.println("</tr>"); 

					
					
					row_count++;
				}
				// main while end
				
				
				// ================================= total row start ============================================== 
				
				branch_comp_total = 0;
				upgrades = 0;
				rowtotal = 0;
				
				branch_comp_total_cases = 0;
				upgrades_cases = 0;
				rowtotal_cases = 0;
				
				out.println("<tr>");
				
				out.println("<td width='5%' colspan=2 > <b> Total </b> </td>"); // out.println("<td width='5%' > "+rs.getString(1)+" </td>"); 
				
				branch_comp_total_cases = 0;
				branch_comp_total = 0;
				
				// =========================== dynamic columns start ===============================================
				for(int x=0; x<item_count; x++){
					
					rs1= stmt1.executeQuery(" "+
						" SELECT COUNT(VALUE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
						" WHERE ENT_USER = '"+m_username+"' "+
						//" AND INSURANCE_DONE_BY = 'LICENSEE'  "+
						" AND INSUR_COM = '"+arr_rpt[0][x]+"' "+ 
						" AND REF_DEBIT_NOTE_NO IS NOT NULL  "+ // added by udara 26-01-2015
					    " AND BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
					" ");
					
					if(rs1.next()){
						branch_comp_total_cases = branch_comp_total_cases + rs1.getInt(1);
						out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','"+arr_rpt[0][x]+"','LICENSEE');\" ><b><u> "+rs1.getInt(1)+" </u></b></td>"); 
					}
					
					rs1= stmt1.executeQuery(" "+
						" SELECT SUM(VALUE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
						" WHERE ENT_USER = '"+m_username+"' "+
						//" AND INSURANCE_DONE_BY = 'LICENSEE'  "+
						" AND INSUR_COM = '"+arr_rpt[0][x]+"' "+
						" AND REF_DEBIT_NOTE_NO IS NOT NULL  "+ // added by udara 26-01-2015
					    " AND BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
					" ");
					
					if(rs1.next()){
						branch_comp_total = branch_comp_total + rs1.getDouble(1);
						out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','"+arr_rpt[0][x]+"','LICENSEE');\" ><b><u> "+nf.format(rs1.getDouble(1))+" </u></b></td>"); 
					}
	
				}
				// =========================== dynamic columns end ==================================================
				
				out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','LICENSEE');\" ><b><u> "+branch_comp_total_cases+" </u></b></td>");
				out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','LICENSEE');\" ><b><u> "+nf.format(branch_comp_total)+" </u></b></td>");
				
				rs2= stmt2.executeQuery(" "+
						" SELECT COUNT(VALUE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND INSURANCE_DONE_BY = 'CLIENT'  "+
						" AND REF_DEBIT_NOTE_NO IS NULL  "+ // added by udara 26-01-2015
					    " AND BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
					" ");
				
				upgrades_cases = 0;
			    	if(rs2.next()){
						upgrades_cases = rs2.getInt(1);
						out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','CLIENT');\" ><b><u> "+upgrades_cases+" </u></b></td>"); 
				}
				
				
				rs2= stmt2.executeQuery(" "+
						" SELECT SUM(VALUE_AMOUNT) "+
						" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND INSURANCE_DONE_BY = 'CLIENT'  "+
						" AND REF_DEBIT_NOTE_NO IS NULL  "+ // added by udara 26-01-2015
					    " AND BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429
					" ");
				
				upgrades = 0;
			    	if(rs2.next()){
						upgrades = rs2.getDouble(1);
						out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','CLIENT');\" ><b><u> "+nf.format(upgrades)+" </u></b></td>"); 
				}
				
				rowtotal = 0;
				rowtotal_cases = 0;
				rowtotal = branch_comp_total + upgrades;
				rowtotal_cases = branch_comp_total_cases + upgrades_cases;
				
				out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','ALL');\" ><b><u> "+rowtotal_cases+" </u></b></td>"); 
				out.println("<td width='5%' align=\"right\" style={cursor:hand} onclick=\"show_drill('ALL','ALL','ALL');\" ><b><u> "+nf.format(rowtotal)+" </u></b></td>"); 
				
				
				out.println("</tr>");
				
				// ================================= end total row end ==========================================
				
				
				
				
				out.println("</table>"); 
				
				
				
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			else if(m_chksql.equals("show_drill_window")){		
				
				String m_branch = "";
				String m_insurance_comp = "";
				String m_insurance_done_by = "";
				
				String m_branch_condition = "";
				String m_insurance_comp_condition = "";
				String m_insurance_done_by_condition = "";
					   m_branch_code=req.getParameter("branch_code");
				
				if(!m_branch_code.equals("")){ //Added by Kanchana on 2015-02-9 for issue no 18429				
					m_branch_code =	" AND BRANCH_CODE LIKE '"+m_branch_code+"%' ";				
				}else{
					m_branch_code ="";
					}

				
				if(req.getParameter("branch")!=null ){
					m_branch = req.getParameter("branch").trim();
				}
			
				if(req.getParameter("insurance_comp")!=null ){
					m_insurance_comp = req.getParameter("insurance_comp").trim();
				}
				
				if(req.getParameter("insurance_done_by")!=null ){
					m_insurance_done_by = req.getParameter("insurance_done_by").trim();
				}
				
				if(m_branch.equals("ALL")){
					m_branch_condition = " ";
				}
				else{
					m_branch_condition = " AND   BRANCH_CODE = '"+m_branch+"'  ";
				}
				
				if(m_insurance_comp.equals("ALL")){
					m_insurance_comp_condition = " ";
				}
				else{
					m_insurance_comp_condition = " AND   INSUR_COM = '"+m_insurance_comp+"'  ";
				}
				
				if(m_insurance_done_by.equals("ALL")){
					m_insurance_done_by_condition = " ";
				}
				// added by udara 26-01-2015
				else if(m_insurance_done_by.equals("LICENSEE")){
					m_insurance_done_by_condition = " AND REF_DEBIT_NOTE_NO IS NOT NULL ";
				}
				else if(m_insurance_done_by.equals("CLIENT")){
					m_insurance_done_by_condition = " AND  INSURANCE_DONE_BY='CLIENT' AND REF_DEBIT_NOTE_NO IS NULL ";
				}
				// end by udara 26-01-2015
				else{
					m_insurance_done_by_condition = " AND   INSURANCE_DONE_BY = '"+m_insurance_done_by+"'  ";
				}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Insurance Summary Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Insurance Summary Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;

				
				Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" POLICY_NO, "+
						" VALUE_AMOUNT "+
						" FROM  "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT  "+ 
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND   BRANCH_CODE IS NOT NULL "+
						" "+ m_branch_condition +" "+
						" "+ m_insurance_comp_condition +" "+
						" "+ m_insurance_done_by_condition +" "+
						" "+m_branch_code+" "+
					" ";
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='5%' class=div_input  align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Policy No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Amount </B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rec_count+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(2)+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(3))+" </td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(3);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			// end drilldown
			
			
			
			
			
		}
		catch(Exception ex) {
			try {
				out.println("Error : " + ex.toString());
			}
			catch(Exception e) {}
		}
		
		
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch(Exception e) {}
			}
		}
		
	}
	
}