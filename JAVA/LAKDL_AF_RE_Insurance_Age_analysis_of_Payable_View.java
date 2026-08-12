//Created by Minal for #14286 on 10-10-2014
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class LAKDL_AF_RE_Insurance_Age_analysis_of_Payable_View extends javax.servlet.http.HttpServlet {
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
			String m_branch_code=req.getParameter("branch_code");
			//out.println(m_username);
			
			if(m_chksql.equals("main_page")){ 
				
				out.println("<HTML><HEAD><TITLE>Asset Financing System</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_drill(m_insur_comp,m_age){ ");
				out.println(" var m_branch_code='"+m_branch_code+"' ; ");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Age_analysis_of_Payable_View?chksql=show_drill_window&insur_comp=\"+m_insur_comp+\"&age=\"+m_age+\"&branch_code=\"+m_branch_code;;");
				out.println("   window.open(m_url,'slab','width=500,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				//out.println("<TR><TD align='Center' ><B> Age Analysis Of Insurance Payables  From  "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
				out.println("<TR><TD align='Center' ><B> Age Analysis Of Insurance Payables as at "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	

				out.println("<table align='center' class='table' >");

				int item_count = 0;

				rs= stmt.executeQuery(" "+
								" SELECT "+
								"    COUNT(INSUR_COM) "+
										" FROM (  "+
										" SELECT DISTINCT INSUR_COM INSUR_COM "+
										" FROM "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT "+ 
										" WHERE BRANCH_CODE LIKE '"+m_branch_code+"%'  "+  // Added by Kanchana for issue no 18429.
								" ) "+
							" ");
			
				if(rs.next()){
					item_count = rs.getInt(1);
				}
				

				out.println("<table width='100%' class='table' border=1 >");	
			
				// ===================== Heading Start =========================================
				
				out.println("<tr>");				
			
				out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
				out.println("<td width='90%' colspan=12 align=center ><b> Age Analysis </b></td>"); 

				out.println("</tr>");
				
				
				out.println("<tr>");				
			
				out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
				
				out.println("<td width='15%' colspan=2 align=center ><b> 1-30 Days </b></td>"); 
 				out.println("<td width='15%' colspan=2 align=center ><b> 30-61 Days </b></td>"); 
				out.println("<td width='15%' colspan=2 align=center ><b> 61-90 Days </b></td>"); 
 				out.println("<td width='15%' colspan=2 align=center ><b> 90-120 Days </b></td>"); 
				out.println("<td width='15%' colspan=2 align=center ><b> More than 120 Days </b></td>"); 
 				out.println("<td width='15%' colspan=2 align=center ><b> Total </b></td>"); 

				
				out.println("</tr>"); 
				
				out.println("<tr>");				
			
				out.println("<td width='10%' ><b> Insurance Company </b></td>"); 
				
				// 1-30
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>"); 
				
				// 30-61
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>");
				
				// 61-90
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>");
				
				// 90-120
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>");
				
				// More than 120
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>");
				
				// Total
				out.println("<td width='5%' align=right ><b> No. of Cases </b></td>"); 
				out.println("<td width='10%' ><b> Amount(Rs.) </b></td>");
				
				out.println("</tr>"); 

				// commented by udara 16-06-2015
				/*
				
				rs= stmt.executeQuery(" "+
						" SELECT INSUR_COM, "+  // 1
						 	" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') INSUR_COM_NAME, "+  // 2
						 	" COUNT(AGE_1_30), "+ // 3
			       			" SUM(NVL(AGE_1_30,0)),  "+ // 4
			       			" COUNT(AGE_31_60), "+ // 5
						 	" SUM(NVL(AGE_31_60,0)),  "+ // 6
			       			" COUNT(AGE_61_90), "+ // 7
						 	" SUM(NVL(AGE_61_90,0)),  "+ // 8
			       			" COUNT(AGE_90_120), "+ // 9
						 	" SUM(NVL(AGE_90_120,0)),  "+ // 10
			       			" COUNT(AGE_120_MORE), "+ // 11
						 	" SUM(NVL(AGE_120_MORE,0)), "+ // 12
			       			" COUNT(VALUE_AMOUNT), "+ // 13
			       			" SUM(VALUE_AMOUNT) "+ // 14
								 " FROM "+m_schema_name+".AF_INSURANCE_AGE_ANALYSIS_RPT  "+
								 " WHERE ENT_USER = '"+m_username+"'  "+
									//" AND AGE <> 0 "+
								 " GROUP BY INSUR_COM  "+
									" ");
				
				*/
				
				// added by udara 16-06-2015
				rs= stmt.executeQuery(" "+
					/*
						" SELECT  INSUR_COM, "+
								" INSUR_COM_NAME, "+
								" DECODE(SUM_AGE_1_30,0,0,COUNT_AGE_1_30), "+
								" SUM_AGE_1_30, "+
								" DECODE(SUM_AGE_31_60,0,0,COUNT_AGE_31_60), "+
								" SUM_AGE_31_60, "+
								" DECODE(SUM_AGE_61_90,0,0,COUNT_AGE_61_90), "+
								" SUM_AGE_61_90, "+
								" DECODE(SUM_AGE_90_120,0,0,COUNT_AGE_90_120), "+
								" SUM_AGE_90_120, "+
								" DECODE(SUM_AGE_120_MORE,0,0,COUNT_AGE_120_MORE) , "+
								" SUM_AGE_120_MORE, "+
								" DECODE(SUM_VALUE_AMOUNT,0,0,COUNT_VALUE_AMOUNT), "+
								" SUM_VALUE_AMOUNT "+
								*/
					
							" SELECT  INSUR_COM, "+
								 " INSUR_COM_NAME, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,'AGE_1_30'),0), "+
								 " SUM_AGE_1_30, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,'AGE_31_60'),0), "+
								 " SUM_AGE_31_60, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,'AGE_61_90'),0), "+
								 " SUM_AGE_61_90, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,'AGE_90_120'),0), "+
								 " SUM_AGE_90_120, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,'AGE_120_MORE'),0), "+
								 " SUM_AGE_120_MORE, "+
                 				 " NVL("+m_schema_name+".af_age_ana_rpt_con_count('LAKDLALL',NULL,INSUR_COM,NULL),0), "+
								 " SUM_VALUE_AMOUNT  "+
								
								
								" FROM ( "+
								
										" SELECT  "+
								        		" INSUR_COM INSUR_COM,  "+ // 1
												" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') INSUR_COM_NAME, "+  // 2
												" COUNT(AGE_1_30) COUNT_AGE_1_30, "+ // 3
											  	" SUM(NVL(AGE_1_30,0)) SUM_AGE_1_30, "+  // 4
											  	" COUNT(AGE_31_60) COUNT_AGE_31_60, "+ // 5
												" SUM(NVL(AGE_31_60,0)) SUM_AGE_31_60, "+  // 6
											  	" COUNT(AGE_61_90) COUNT_AGE_61_90, "+ // 7
												" SUM(NVL(AGE_61_90,0)) SUM_AGE_61_90, "+  // 8
											  	" COUNT(AGE_90_120) COUNT_AGE_90_120, "+ // 9
												" SUM(NVL(AGE_90_120,0)) SUM_AGE_90_120, "+  // 10
											  	" COUNT(AGE_120_MORE) COUNT_AGE_120_MORE, "+ // 11
												" SUM(NVL(AGE_120_MORE,0)) SUM_AGE_120_MORE, "+ // 12
											  	" COUNT(VALUE_AMOUNT) COUNT_VALUE_AMOUNT, "+ // 13
											  	" SUM(VALUE_AMOUNT) SUM_VALUE_AMOUNT "+ // 14
													
								            		" FROM "+m_schema_name+".AF_INSURANCE_AGE_ANALYSIS_RPT "+  
								            		" WHERE ENT_USER = '"+m_username+"'  "+
													" AND BRANCH_CODE LIKE '"+m_branch_code+"%' "+  // Added by Kanchana for issue no 18429	 (AF_CO_GET_BRANCH_CODE_AGE_RPT)
													//" AND BALNCE_AMOUNT > 0"+ //  Added By Nishantha on 25-02-2016 
													" AND NVL(PAID_NON_PAID,'N') <> 'Y' "+ // added by udara 01-03-2016 // checking if any amount of payment made for this contract
													" GROUP BY INSUR_COM "+ 
								            
								" ) "+
								" WHERE SUM_VALUE_AMOUNT > 0 ");
								
				
				
				
			int count_1_30 = 0;	
			double amount_1_30 = 0;
			
			int count_31_61 = 0;	
			double amount_31_61 = 0;
			
			int count_61_90 = 0;	
			double amount_61_90 = 0;
			
			int count_90_120 = 0;	
			double amount_90_120 = 0;
			
			int count_more_120 = 0;	
			double amount_more_120 = 0;
			
			int count_total = 0;	
			double amount_total = 0;
			
			// main while
				while(rs.next()){
					
						count_1_30  = count_1_30 + rs.getInt(3);	
						amount_1_30 = amount_1_30 + rs.getDouble(4);
						
						count_31_61  = count_31_61 + rs.getInt(5);	
						amount_31_61 = amount_31_61 + rs.getDouble(6);
						
						count_61_90  = count_61_90 + rs.getInt(7);	
						amount_61_90 = amount_61_90 + rs.getDouble(8);
						
						count_90_120  = count_90_120 + rs.getInt(9);	
						amount_90_120 = amount_90_120 + rs.getDouble(10);
						
						count_more_120  = count_more_120 + rs.getInt(11);	
						amount_more_120 = amount_more_120 + rs.getDouble(12);
							
						count_total  = count_total + rs.getInt(13);	
						amount_total = amount_total + rs.getDouble(14);	
					
						out.println("<tr>");				
					
						out.println("<td width='10%' ><b> "+rs.getString(2)+" </b></td>"); 
						
						// 1-30
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','1_30');\" > "+rs.getInt(3)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','1_30');\" > "+nf.format(rs.getDouble(4))+" </td>"); 
						
						// 30-61
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','30_61');\" > "+rs.getInt(5)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','30_61');\" > "+nf.format(rs.getDouble(6))+" </td>"); 
						
						// 61-90
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','61_90');\" > "+rs.getInt(7)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','61_90');\" > "+nf.format(rs.getDouble(8))+" </td>"); 
						
						// 90-120
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','90_120');\" > "+rs.getInt(9)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','90_120');\" > "+nf.format(rs.getDouble(10))+" </td>"); 
						
						// More than 120
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','120_more');\" > "+rs.getInt(11)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','120_more');\" > "+nf.format(rs.getDouble(12))+" </td>"); 
						
						// Total
						out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL');\" > "+rs.getInt(13)+" </td>"); 
						out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('"+rs.getString(1)+"','ALL');\" > "+nf.format(rs.getDouble(14))+" </td>"); 
						
						out.println("</tr>");
				
				}
				
				out.println("<tr>");				
					
				out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
						
				// 1-30
				out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('ALL','1_30');\" ><b> "+count_1_30+" </b></td>"); 
				out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('ALL','1_30');\" ><b> "+nf.format(amount_1_30)+" </b></td>"); 
						
				// 30-61
				out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('ALL','30_61');\" ><b> "+count_31_61+" </b></td>"); 
				out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('ALL','30_61');\" ><b> "+nf.format(amount_31_61)+" </b></td>"); 
						
				// 61-90
				out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('ALL','61_90');\" ><b> "+count_61_90+" </b></td>"); 
				out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('ALL','61_90');\" ><b> "+nf.format(amount_61_90)+" </b></td>"); 
						
				// 90-120
				out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('ALL','90_120');\" ><b> "+count_90_120+" </b></td>"); 
				out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('ALL','90_120');\" ><b> "+nf.format(amount_90_120)+" </b></td>"); 
						
				// More than 120
				out.println("<td width='5%' align=right style={cursor:hand} onclick=\"show_drill('ALL','120_more');\" ><b> "+count_more_120+" </b></td>"); 
				out.println("<td width='1%' align=right style={cursor:hand} onclick=\"show_drill('ALL','120_more');\" ><b> "+nf.format(amount_more_120)+" </b></td>"); 
						
				// Total
				out.println("<td width='5%'  align=right style={cursor:hand} onclick=\"show_drill('ALL','ALL');\" ><b> "+count_total+" </b></td>"); 
				out.println("<td width='10%' align=right style={cursor:hand} onclick=\"show_drill('ALL','ALL');\" ><b> "+nf.format(amount_total)+" </b></td>"); 
						
				out.println("</tr>");

				
				out.println("</table>"); 
				
				
				
				out.println("</table>");
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			else if(m_chksql.equals("show_drill_window")){		
				
				m_branch_code=req.getParameter("branch_code");
				String m_age = "";
				String m_insur_comp = "";

				
				String m_age_condition = "";
				String insur_comp_condition = "";
				
				
				if(req.getParameter("age")!=null ){
					m_age = req.getParameter("age").trim();
				}
			
				if(req.getParameter("insur_comp")!=null ){
					m_insur_comp = req.getParameter("insur_comp").trim();
				}
				

				
				if(m_age.equals("ALL")){
					m_age_condition = " ";
				}
				else if(m_age.equals("1_30")){
					m_age_condition = " AND NVL(AGE_1_30,0) > 0 ";
				}
				else if(m_age.equals("30_61")){
					m_age_condition = " AND NVL(AGE_31_60,0) > 0 ";
				}
				else if(m_age.equals("61_90")){
					m_age_condition = " AND NVL(AGE_61_90,0) > 0 ";
				}
				else if(m_age.equals("90_120")){
					m_age_condition = " AND NVL(AGE_90_120,0) > 0 ";
				}
				else if(m_age.equals("120_more")){
					m_age_condition = " AND NVL(AGE_120_MORE,0) > 0 ";
				}
				else{
					m_age_condition = " ";
				}

				
				if(m_insur_comp.equals("ALL")){
					insur_comp_condition = "  ";
				}
				else{
					insur_comp_condition = " AND  INSUR_COM = '"+m_insur_comp+"' ";
				}
				

				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Age Analysis Of Insurance Payables - Drill </TITLE>"); 
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
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Age Analysis Of Insurance Payables </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;

				
				// commented by udara 21-04-2021
				/*
				Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" POLICY_NO, "+
						" VALUE_AMOUNT "+
						" FROM  "+m_schema_name+".AF_INSURANCE_AGE_ANALYSIS_RPT  "+ 
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND   BRANCH_CODE IS NOT NULL "+
						" "+ m_age_condition +" "+
						" "+ insur_comp_condition +" "+	
						" AND BRANCH_CODE LIKE '"+m_branch_code+"%' "+  // Added by Kanchana for issue no 18429	
						//" AND BALNCE_AMOUNT > 0 "+ //Added By nishantha on 26-02-2016 #19597
						" AND NVL(PAID_NON_PAID,'N') <> 'Y' "+ // added by udara 01-03-2016 // checking if any amount of payment made for this contract
					    // ";
						" ORDER BY FINANCE_NO ";
				*/
				
				// added by udara 21-04-2021
				/*
				Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" POLICY_NO, "+
						" NVL(SUM(VALUE_AMOUNT),0), "+
						" VEHICLE_NO "+
						" FROM  "+m_schema_name+".AF_INSURANCE_AGE_ANALYSIS_RPT  "+ 
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND   BRANCH_CODE IS NOT NULL "+
						" "+ m_age_condition +" "+
						" "+ insur_comp_condition +" "+	
						" AND BRANCH_CODE LIKE '"+m_branch_code+"%' "+  
						" AND NVL(PAID_NON_PAID,'N') <> 'Y' "+ 
						" GROUP BY FINANCE_NO, POLICY_NO, VEHICLE_NO "+
						" ORDER BY FINANCE_NO ";
				*/
				// added by udara 21-04-2021
				
				Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						//" POLICY_NO, "+
						" NVL(SUM(VALUE_AMOUNT),0), "+
						" VEHICLE_NO "+
						" FROM  "+m_schema_name+".AF_INSURANCE_AGE_ANALYSIS_RPT  "+ 
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND   BRANCH_CODE IS NOT NULL "+
						" "+ m_age_condition +" "+
						" "+ insur_comp_condition +" "+	
						" AND BRANCH_CODE LIKE '"+m_branch_code+"%' "+  
						" AND NVL(PAID_NON_PAID,'N') <> 'Y' "+ 
						" GROUP BY FINANCE_NO, VEHICLE_NO "+
						" ORDER BY FINANCE_NO ";
				
				
				
				
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
				//out.println("<td width='15%' class=div_input align='center' bgcolor='lightblue' ><B> Policy No. </B></td>");
				out.println("<td width='15%' class=div_input align='center' bgcolor='lightblue' ><B> Amount </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Vehicle No </B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rec_count+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>"); 
					//out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(2)+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(2))+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(3)+" </td>");
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(2);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				//out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
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