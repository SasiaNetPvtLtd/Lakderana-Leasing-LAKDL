import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
    
public class LAKDL_FA_RPT_display_collection_remarks_report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_header_name=m_sn_methods.header_name.trim();
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			ServletOutputStream out = res.getOutputStream(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
   
			String m_screen_type=req.getParameter("chksql");
			String m_report_s_date=req.getParameter("REPORT_S_DATE");
			String m_report_e_date=req.getParameter("REPORT_E_DATE");
			String m_remark_type=req.getParameter("REMARK_TYPE");
			
			
			String m_schema_name = m_sn_methods.schema_name;
 
					 		
			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Operation Process - Remarks Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
		
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
				
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Operation Process - Remarks Report - \"+m_val;"); 
			out.println("}");
			
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\"Operation Process - Remarks Report  \";"); 
			out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='1300'>"); 
			out.println("<tr><td align='center' class='pdn_txtpos2' style='height: 18px' id='help_box'>Remarks Report from "+m_report_s_date+" to "+m_report_e_date+" </td></tr>"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
     /* comment by malik on 22-10-2008
			out.println("<table align='center' width='1000' class='table' border=1 cellpadding='2' cellspacing='0' >"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100' class='txt_report_column' >FACILITY NO</td>"); 
			out.println("<td width='200' class='txt_report_column' >CLIENT NAME</td>"); 
			out.println("<td width='200' class='txt_report_column' >DEBTOR NAME </td>"); 
			out.println("<td width='100' class='txt_report_column' >ENTER USER</td>"); 
			out.println("<td width='100' class='txt_report_column' >ENTER DATE</td>"); 
			out.println("<td width='300' class='txt_report_column' >COMMENT</td>"); 
			out.println("</tr >"); 
			*/ //add by malik on 22-10-2008
			out.println("<table align='center' width='1300' class='table' border=1 cellpadding='2' cellspacing='0' >"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100' class='txt_report_column' >FACILITY NO</td>");
			out.println("<td width='200' class='txt_report_column' >CLIENT NAME</td>"); 
			out.println("<td width='200' class='txt_report_column' >DEBTOR NAME </td>"); 
			out.println("<td width='200' class='txt_report_column' >INVOICE NO</td>"); 
			out.println("<td width='100' class='txt_report_column' >INVOICE DATE</td>"); 
			out.println("<td width='100' class='txt_report_column' >INVOICE AMOUNT</td>"); 
			out.println("<td width='100' class='txt_report_column' >BALANCE AMOUNT</td>"); 
			out.println("<td width='300' class='txt_report_column' >COMMENT</td>"); 
			out.println("</tr >"); 											
			if (m_remark_type.trim().equals("NO")) {				
			/* comment by malik on 22-10-2008
			rs=stmt.executeQuery(" SELECT "+
                           " FACILITY_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),COMMENTS "+
                           " FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
													 " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') " +
													 " AND   TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') " +
                           " UNION "+
                           " SELECT "+
                           " FACILITY_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),COMMENTS "+
                           " FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS "+
													 " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') " +
													 " AND   TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') ");
			*/
			
			// commented by udara somathilake on 17-11-2010
			/*
			rs=stmt.executeQuery(" SELECT B.FACILITY_NO,"+
													 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+
													 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+
													 " A.INVOICE_NO,"+
													 " TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'),"+
													 " A.INVOICE_AMOUNT,"+
													 " A.BALANCE_AMOUNT,"+
													 " B.COMMENTS "+
													 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS B "+
													 " WHERE A.FACILITY_NO=B.FACILITY_NO "+
													 " AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
													 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY')"+
													 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY')"+
													 " AND B.ent_date IN (SELECT MAX(ENT_DATE) FROM LAKDL.FA_CR_PRO_DEBTOR_CRD_COMMENTS  GROUP BY FACILITY_NO)"+
													 " UNION ALL "+
													 " SELECT B.FACILITY_NO,"+
													 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+
													 " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+
													 " A.INVOICE_NO,"+
													 " TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'),"+
													 " A.INVOICE_AMOUNT,"+
													 " A.BALANCE_AMOUNT,"+
													 " B.COMMENTS "+
													 " FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_COLL_COMMENT B "+
													 " WHERE A.FACILITY_NO=B.FACILITY_NO "+
													 " AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
													 " AND A.BALANCE_AMOUNT > 0 "+	//Added By Sandun on 22-09-2009
													 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') "+
													 " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') "+
													 " AND B.ent_date IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT  GROUP BY FACILITY_NO)	");
      								*/
										
			// Added by udara somathilake on 17-11-2010 to perform according to the Action Date
			rs = stmt.executeQuery(" "+
							" SELECT  B.FACILITY_NO, "+
							        " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME (B.CLIENT_CODE), "+
							        " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME (B.DEBTOR_CODE), "+
							        " A.INVOICE_NO, "+
							        " TO_CHAR (A.INVOICE_DATE, 'DD/MM/YYYY'), "+
							        " A.INVOICE_AMOUNT, "+
							        " A.BALANCE_AMOUNT, "+
							        " B.COMMENTS "+
				            					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS B "+
				                        			" WHERE A.FACILITY_NO = B.FACILITY_NO "+
						                            " AND A.DEBTOR_CODE = B.DEBTOR_CODE "+
						                            " AND TO_DATE (TO_CHAR (B.ACTION_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') <= TO_DATE ('"+m_report_e_date+"', 'DD-MM-YYYY') "+
						                            " AND B.ENT_DATE IN (SELECT   MAX (ENT_DATE) "+
				                                			" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS "+
				                        			" GROUP BY FACILITY_NO) "+
							" UNION ALL "+
							" SELECT  B.FACILITY_NO, "+
							        " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME (B.CLIENT_CODE), "+
							        " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME (B.DEBTOR_CODE), "+
							        " A.INVOICE_NO, "+
							        " TO_CHAR (A.INVOICE_DATE, 'DD/MM/YYYY'), "+
							        " A.INVOICE_AMOUNT, "+
							        " A.BALANCE_AMOUNT, "+
							        " B.COMMENTS "+
							            		" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A, "+m_schema_name+".FA_CR_PRO_COLL_COMMENT B "+
							                			" WHERE A.FACILITY_NO = B.FACILITY_NO "+
										                " AND A.DEBTOR_CODE = B.DEBTOR_CODE "+
										                " AND A.BALANCE_AMOUNT > 0 "+
										                " AND TO_DATE (TO_CHAR (B.ACTION_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY') <= TO_DATE ('"+m_report_e_date+"', 'DD-MM-YYYY') "+
										                " AND B.ENT_DATE IN (SELECT   MAX (ENT_DATE) "+
							                          		" FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
							            				" GROUP BY FACILITY_NO) "+
										" ");
										
										
		}	
			else{
			/* comment by malik on 22-10-2008
			rs=stmt.executeQuery(" SELECT "+
                           " FACILITY_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),COMMENTS "+
                           " FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT "+
													 " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') " +
													 " AND   TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') " +
													 " AND REMARK_CODE LIKE '"+m_remark_type+"%' "+	
                           " UNION "+
                           " SELECT "+
                           " FACILITY_NO,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY'),COMMENTS "+
                           " FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS "+
													 " WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') " +
													 " AND   TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') " +
								           " AND REMARK_CODE LIKE '"+m_remark_type+"%' ");
														*/
				
				// Commented by Udara Somathilake on 17-11-2010
				/*
				rs=stmt.executeQuery(" SELECT B.FACILITY_NO,"+
													   " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+
													   " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+
													   " A.INVOICE_NO,"+
													   " TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'),"+
													   " A.INVOICE_AMOUNT,"+
													   " A.BALANCE_AMOUNT,"+
													   " B.COMMENTS "+
													   " FROM "+m_schema_name+".fa_cr_pro_invoice_detail A,"+m_schema_name+".fa_cr_pro_debtor_crd_comments B "+
													   " WHERE A.FACILITY_NO=B.FACILITY_NO "+
														 " AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
													   " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') "+
													   " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') "+
													   " AND B.ent_date IN (SELECT MAX(ENT_DATE) FROM LAKDL.FA_CR_PRO_DEBTOR_CRD_COMMENTS  GROUP BY FACILITY_NO)"+
 														 " AND B.REMARK_CODE LIKE '"+m_remark_type+"%' "+
													   " UNION ALL "+
													   " SELECT B.FACILITY_NO,"+
													   " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+
													   " "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),"+
													   " A.INVOICE_NO,"+
													   " TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'),"+
													   " A.INVOICE_AMOUNT,"+
													   " A.BALANCE_AMOUNT,"+
													   " B.COMMENTS "+
													   " FROM "+m_schema_name+".fa_cr_pro_invoice_detail A,"+m_schema_name+".FA_CR_PRO_COLL_COMMENT B "+
													   " WHERE A.FACILITY_NO=B.FACILITY_NO "+
														 " AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
													   " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_report_s_date+"','DD-MM-YYYY') "+
													   " AND TO_DATE(TO_CHAR(B.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY')  "+
													   " AND B.remark_code LIKE '"+m_remark_type+"%'	"+
														 " AND A.BALANCE_AMOUNT > 0 "+	//Added By Sandun on 22-09-2009
														 " AND B.ent_date IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT GROUP BY FACILITY_NO)");					
							*/	
							
					rs=stmt.executeQuery(" "+
									" SELECT   B.FACILITY_NO, "+
												" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+
												" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+
												" A.INVOICE_NO, "+
												" TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'), "+
												" A.INVOICE_AMOUNT, "+
												" A.BALANCE_AMOUNT, "+
												" B.COMMENTS "+
														" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS B "+
																" WHERE A.FACILITY_NO=B.FACILITY_NO "+
																		" AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
																		" AND TO_DATE(TO_CHAR(B.ACTION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') "+
																		" AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS  GROUP BY FACILITY_NO) "+
										 								" AND B.REMARK_CODE LIKE '%"+m_remark_type+"%' "+
												" UNION ALL "+
												" SELECT  B.FACILITY_NO, "+
														" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE), "+
														" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+
														" A.INVOICE_NO, "+
														" TO_CHAR(A.INVOICE_DATE,'DD/MM/YYYY'), "+
														" A.INVOICE_AMOUNT, "+
														" A.BALANCE_AMOUNT, "+
														" B.COMMENTS "+
																" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_COLL_COMMENT B "+
																		" WHERE A.FACILITY_NO=B.FACILITY_NO "+
																			 " AND A.DEBTOR_CODE=B.DEBTOR_CODE "+
																			 " AND TO_DATE(TO_CHAR(B.ACTION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_report_e_date+"','DD-MM-YYYY') "+ 
																			 " AND B.REMARK_CODE LIKE '%"+m_remark_type+"%'	"+
																			 " AND A.BALANCE_AMOUNT > 0 "+	
																			 " AND B.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".FA_CR_PRO_COLL_COMMENT GROUP BY FACILITY_NO) "+
												" ");
							
							
							
			}
			
				int j=1;

			boolean more=rs.next();
			while(more){
			
					if(j==0){
						out.println("<tr width='1000' bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr width='1000' bgcolor=\"#C0C0C0\" >");
						j=0;
					}
								out.println("<TD STYLE='{font: 9pt arial; text-align:left; }' width='100'>"+rs.getString(1)+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='200'>"+rs.getString(2)+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='200'>"+rs.getString(3)+"</TD>");
								
								out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='200'>"+rs.getString(4)+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:left;}' width='100'>"+rs.getString(5)+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100'>"+nf.format(rs.getDouble(6))+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:right;}' width='100'>"+nf.format(rs.getDouble(7))+"</TD>");
								out.println("<TD STYLE='{font: 9pt arial; text-align:left;}'  width='200'>"+rs.getString(8)+"</TD>");
								/*
								out.println("<td width='100' class='txt_report_column' >FACILITY NO</td>");
			out.println("<td width='200' class='txt_report_column' >CLIENT NAME</td>"); 
			out.println("<td width='200' class='txt_report_column' >DEBTOR NAME </td>"); 
			out.println("<td width='200' class='txt_report_column' >INVOICE NO</td>"); 
			out.println("<td width='100' class='txt_report_column' >INVOICE DATE</td>"); 
			out.println("<td width='100' class='txt_report_column' >INVOICE AMOUNT</td>"); 
			out.println("<td width='100' class='txt_report_column' >BALANCE AMOUNT</td>"); 
			out.println("<td width='300' class='txt_report_column' >COMMENT</td>"); 
								*/
					
				more=rs.next(); 
			} 
			out.println("</table>");
			out.println("<br>"); 

			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			conn.close();
			out.flush();
			out.close();
			}


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


