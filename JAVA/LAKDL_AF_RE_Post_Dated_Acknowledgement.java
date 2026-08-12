import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:27-03-2007

public class LAKDL_AF_RE_Post_Dated_Acknowledgement extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs,rs1;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
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
			stmt =conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("print_ack")){
							
			//String m_finance_no=req.getParameter("finance_no");
			String m_finance_no  = "";
			String m_ack         = req.getParameter("ack");
			String m_client_code = req.getParameter("client_code");
			String m_pod_no      = req.getParameter("pod_no");
			String m_type        = req.getParameter("ent_type");
			String m_batch_no    = req.getParameter("batch_no");  
			
			String Sys_date="";
			String m_name="";
			String m_orient_name="";
			String m_orient_add1="";
			String m_orient_add2="";
			String m_orient_city_name="";
			String m_orient_tel_no="";
			String m_orient_fax_no="";
			String m_orient_vat_rate="";
			String m_orient_email="";
			
			
				
					 String		Sql_sys_date=" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
																 " FROM DUAL ";
																	
					rs1=stmt1.executeQuery(Sql_sys_date);
					boolean more =rs1.next();
					
					if(more){
					Sys_date=rs1.getString(1);
					}
																	
																	
																	
					 String		Sql_company_details=" SELECT "+
					    " NVL(UPPER(COMPANY_NAME),' '), "+
					    " NVL(UPPER(ADDRESS1),' '), "+
					    " NVL(UPPER(ADDRESS2),' '), "+
					    " NVL(UPPER(CITY),' '), "+
					    " NVL(TEL_NO,' '), "+
					    " NVL(FAX_NO,' '),  "+
							" NVL(VAT_RATE,0), "+
							" NVL(EMAIL,' ') "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
							
				  	rs1=stmt1.executeQuery(Sql_company_details);
					  more =rs1.next();


							 											
											if(more)
											{
											m_orient_name=rs1.getString(1);
											m_orient_add1=rs1.getString(2);
											m_orient_add2=rs1.getString(3);
											m_orient_city_name=rs1.getString(4);
											m_orient_tel_no=rs1.getString(5);
											m_orient_fax_no=rs1.getString(6);
											m_orient_vat_rate=rs1.getString(7);	
											m_orient_email=rs1.getString(8);	
											}
											
											
				    String Sql_client_name = " SELECT NVL(UPPER("+m_schema_name+".af_co_get_client_name('"+m_client_code+"')),' ') "+
						                         " FROM DUAL ";
																			
						rs1=stmt1.executeQuery(Sql_client_name);
					  more =rs1.next();													
				
             if(more){
									m_name = rs1.getString(1);
										}

				
					if(m_ack.equals("new")){
					int k=1;
					int i=1;
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 
					 out.println("<script>");
						
					 out.println("function print_data(){");
					 out.println("   m_table.innerHTML = \"\"; ");
					 out.println("   window.print();");
					 out.println("}");
						
					 out.println("</script>");
						
						
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

									
					 out.println("<br><br>"); 
					
					 // added by udara on 18-02-2012
					 out.println("<div id='m_table' name='m_table' >");
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");
					 out.println("<tr><td width='*%' align='right' ><input class='but_input' type='button' name='BUT_PRINT' value='Print' onClick='print_data()' ></td></tr>");
					 out.println("</table>");
					 out.println("</div>");
					 out.println("<br><br>");
					 // end by udara on 18-02-2012
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_name.toUpperCase()+"</b></td></tr>");
	  	     		 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". Tel : "+m_orient_tel_no+" Fax : "+m_orient_fax_no+" Email : "+m_orient_email+"</b></td></tr>");
						
					 	 
					 out.println("</table>");
						
					 out.println("<br><br>"); 	
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'><b>ACKNOWLEDGEMENT OF POST DATED CHEQUES</b></td></tr>");
	  	     					
					 out.println("</table>");
						
					 out.println("<br>"); 	
						
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='5%' class='rep-body' align='left'>Name </td><td width='2%' align='center'>:</td><td width='*%' class='rep-body' align='left'>"+m_name+"</td>");
					 //out.println("<td width='*%' class='rep-body' align='left'>"+m_name+"</td>");
	  	     out.println("</tr>");
					 out.println("</table>");	
						
					//	out.println("<br><br>"); 	
						
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					//out.println("<td width='50%' class='rep-body' align='left'>Agreement No:&nbsp"+m_finance_no+"</td>");//Commented By Sandun on 13-07-2009
					 out.println("<td width='5%' class='rep-body' align='left'>Date </td><td width='2%' align='center'>:</td><td width='*%' class='rep-body' align='left'>"+Sys_date+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
					
					
					
					
								/*		
					
										String		Sql_new=" SELECT "+										
									"   DISTINCT A.POD_REF_NO, "+
									"   A.CLIENT_CODE, "+
									//"   NVL(B.BANK_CODE,'-'), "+
									"   NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE ),'-') BANK_NAME,"+
									"   NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-') BRANCH_NAME, "+
									"   NVL(A.CHEQUE_NO,'-'), "+
									"   NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
									"   NVL(A.CHEQUE_AMOUNT,0),A.CHEQUE_DATE "+//Added by Dineth on 15-06-2009
									"   FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A  "+
									"   WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
									"         UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
									//"           A.CLIENT_CODE=B.CLIENT_CODE(+) AND "+
									//"           A.PAYER_ACC_NO=B.ACCOUNT_NO AND "+
									"         STATUS='INV' AND "+
									"         TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+Sys_date+"','DD-MM-YYYY') "+
									"  ORDER BY A.CHEQUE_DATE DESC ";//Added by Dineth on 15-06-2009
									*/
				    out.println("<table border=\"1\" align='center' width='100%' cellspacing='0'  bordercolor='black' class='table' >");	
					
					  out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >Bank</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>Branch</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>Cheque No</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >Date Of Cheque</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >Agreement No</td>");//Added BY Sandun on 13-07-2009 
						out.println("<td width='20%'  class='rep_body' align='right'>Amount(Rs.)</td>");
						out.println("</tr>"); 
						
				
							rs=stmt.executeQuery(" SELECT DISTINCT A.POD_REF_NO "+
																		 " FROM   "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A "+
																		 " WHERE  A.CLIENT_CODE = '"+m_client_code+"' "+
																		 " AND    A.ENT_DATE IN (SELECT MAX(ENT_DATE)  "+
														         "             FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES  "+
														         "             WHERE CLIENT_CODE = '"+m_client_code+"' ) ");
									boolean more_pod = rs.next();
								
									while(more_pod){								
				i=k;
				//Mod by Sandun on 13-06-2009
				  String		Sql_new=" SELECT "+										
									"   DISTINCT A.POD_REF_NO, "+//1
									"   A.CLIENT_CODE, "+//2
									"   NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE ),'-') BANK_NAME,"+//3
									"   NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-') BRANCH_NAME, "+//4
									"   A.CHEQUE_NO, "+//5
									"   NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+//6
									"   NVL(A.CHEQUE_AMOUNT,0),"+//7
									"   A.CHEQUE_DATE, "+//8
									"   A.FINANCE_NO "+//9
									"   FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A  "+
									"   WHERE "+
									"   A.CLIENT_CODE = '"+m_client_code+"' AND "+
									"   A.POD_REF_NO  = '"+rs.getString(1)+"' and "+
									"   A.STATUS      = 'INV' "+
									"   ORDER BY A.CHEQUE_DATE,A.CHEQUE_NO,A.FINANCE_NO ";				
						
										
					
					rs1=stmt1.executeQuery(Sql_new);
					more =rs1.next();	
					 
														
									
					while(more){
					
					 if(i==k){
						out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='center'>"+k+"</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >"+rs1.getString(3)+"</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>"+rs1.getString(4)+"</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>"+rs1.getString(5)+"</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >"+rs1.getString(6)+"</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >"+rs1.getString(9)+"</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("</tr>"); 
						}
						else{
						out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='center'>&nbsp;</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >&nbsp;</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >&nbsp;</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >"+rs1.getString(9)+"</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("</tr>"); 
						
						}
															
						
						i=i+1;
						more =rs1.next();
						
						
					}
					
					k=k+1;
					more_pod = rs.next();
					}
					
										
					  out.println("</table>");
						
						out.println("<br><br>"); 	

						
	  			  out.println("<table border=\"0\" align='center' cellspacing='0' width='100%' class='table' >");	
            
						out.println("<tr > ");
						out.println("<td width='60%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='40%'   class='rep_body' align='right'> --------------------------------------------------- </td>"); 
						out.println("</tr>"); 
						out.println("<tr>"); 
						out.println("<td width='60%'  class='rep_body' align='left'><i>Original receipt will be issued upon realization of cheque's</i></td>"); 
						out.println("<td width='40%'   class='rep_body' align='right' >Received by :"+m_schema_name+" Accounts Department</td>"); 
						out.println("</tr>"); 

						out.println("</table>");
						
					
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
					}
					else if(m_ack.equals("all")){
					int k=1;
					int i=1;
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

									
					 out.println("<br><br>"); 	
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_name.toUpperCase()+"</b></td></tr>");
	  	     out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". Tel : "+m_orient_tel_no+" Fax : "+m_orient_fax_no+" Email : "+m_orient_email+"</b></td></tr>");
						
					 	 
					 out.println("</table>");
						
					 out.println("<br><br>"); 	
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'><b>ACKNOWLEDGEMENT OF POST DATED CHEQUES</b></td></tr>");
	  	     					
					 out.println("</table>");
						
					 out.println("<br>"); 	
														
					out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='5%' class='rep-body' align='left'>Name </td><td width='2%' align='center'>:</td><td width='*%' class='rep-body' align='left'>"+m_name+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
						
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='5%' class='rep-body' align='left'>Date </td><td width='2%' align='center'>:</td><td width='*%' class='rep-body' align='left'>"+Sys_date+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
					
					out.println("<table border=\"1\" align='center' width='100%' cellspacing='0'  bordercolor='black' class='table' >");	
				
					  out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >Bank</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>Branch</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>Cheque No</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >Date Of Cheque</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >Agreement No</td>");//Added BY Sandun on 13-07-2009 
						out.println("<td width='20%'  class='rep_body' align='right'>Amount(Rs.)</td>");
						out.println("</tr>"); 	
					
					
								/*		
					
										String		Sql_new=" SELECT "+										
									"   DISTINCT A.POD_REF_NO, "+
									"   A.CLIENT_CODE, "+
									//"   NVL(B.BANK_CODE,'-'), "+
									"   NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE ),'-') BANK_NAME,"+
									"   NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-') BRANCH_NAME, "+
									"   NVL(A.CHEQUE_NO,'-'), "+
									"   NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
									"   NVL(A.CHEQUE_AMOUNT,0),A.CHEQUE_DATE "+//Added by Dineth on 15-06-2009
									"   FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A  "+
									"   WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
									"         UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
									//"           A.CLIENT_CODE=B.CLIENT_CODE(+) AND "+
									//"           A.PAYER_ACC_NO=B.ACCOUNT_NO AND "+
									"         STATUS IN ('INV','APP' ) "+
									//"         TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+Sys_date+"','DD-MM-YYYY') ";
									"  ORDER BY A.CHEQUE_DATE DESC ";//Added by Dineth on 15-06-2009 
				
				*/
				
				rs=stmt.executeQuery(" SELECT DISTINCT POD_REF_NO "+
														   " FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES  "+
														   " WHERE POD_BATCH_NO = '"+m_batch_no+"' ");
														   
				
			boolean more_pod = rs.next();
				while(more_pod){
				i=k;
				
				
				
				//Mod by Sandun on 13-06-2009
				  String		Sql_new=" SELECT "+										
									"   DISTINCT A.POD_REF_NO, "+//1
									"   A.CLIENT_CODE, "+//2
									"   NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE ),'-') BANK_NAME,"+//3
									"   NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-') BRANCH_NAME, "+//4
									"   A.CHEQUE_NO, "+//5
									"   NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+//6
									"   NVL(A.CHEQUE_AMOUNT,0),"+//7
									"   A.CHEQUE_DATE, "+//8
									"   A.FINANCE_NO "+//9
									"   FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A  "+
									"   WHERE "+
									"   A.CLIENT_CODE like '%"+m_client_code+"%' AND "+
									"   A.POD_REF_NO = '"+rs.getString(1)+"' AND "+
									"   A.STATUS = 'INV' "+									
									"   ORDER BY A.CHEQUE_DATE,A.CHEQUE_NO ";
				
					
					
					rs1=stmt1.executeQuery(Sql_new);
					more =rs1.next();															
										
					while(more){
					
						if(i==k){
						out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='center'>"+k+"</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >"+rs1.getString(3)+"</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>"+rs1.getString(4)+"</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>"+rs1.getString(5)+"</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >"+rs1.getString(6)+"</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >"+rs1.getString(9)+"</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("</tr>"); 
						}
						else{
						out.println("<tr > ");
						out.println("<td width='2%'  class='rep_body' align='center'>&nbsp;</td>"); 
						out.println("<td width='20%'   class='rep_body' align='left' >&nbsp;</td>"); 
						out.println("<td width='25%'  class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'   class='rep_body' align='left' >&nbsp;</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >"+rs1.getString(9)+"</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("</tr>"); 
						
						}						
						i=i+1;
						
						more =rs1.next();
						
					}
					k=k+1;
					more_pod = rs.next();
					}
										
					  out.println("</table>");
						
						out.println("<br><br>"); 	

						
	  			  out.println("<table border=\"0\" align='center' cellspacing='0' width='100%' class='table' >");	
            
						out.println("<tr > ");
						out.println("<td width='60%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='40%'   class='rep_body' align='right'> --------------------------------------------------- </td>"); 
						out.println("</tr>"); 
						out.println("<tr>"); 
						out.println("<td width='60%'  class='rep_body' align='left'><i>Original receipt will be issued upon realization of cheque's</i></td>"); 
						out.println("<td width='40%'   class='rep_body' align='right' >Received by :"+m_schema_name+" Accounts Department</td>"); 
						out.println("</tr>"); 

						out.println("</table>");
						
					
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
					}
						if(m_ack.equals("withdraw")){
					
										
					
										String		Sql_new=" SELECT "+										
									"   DISTINCT A.POD_REF_NO, "+
									"   A.CLIENT_CODE, "+
									//"   NVL(B.BANK_CODE,'-'), "+
									"   NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE ),'-') BANK_NAME ,"+
									"   NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-') BRANCH_NAME, "+
									"   NVL(A.CHEQUE_NO,'-'), "+
									"   NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-') CHEQUE_DATE, "+
									"   NVL(A.CHEQUE_AMOUNT,0),A.CHEQUE_DATE "+//Added by Dineth on 15-06-2009
									"   FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES A /*,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS B  */ "+
									"   WHERE UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND "+
									"         UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
									//"           A.CLIENT_CODE=B.CLIENT_CODE(+) AND "+
									//"           A.PAYER_ACC_NO=B.ACCOUNT_NO AND "+
									"         STATUS='WIT' AND "+
									"         TO_DATE(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+Sys_date+"','DD-MM-YYYY') "+
									"  ORDER BY A.CHEQUE_DATE DESC ";//Added by Dineth on 15-06-2009
				
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

									
					 out.println("<br><br>"); 	
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_name.toUpperCase()+"</b></td></tr>");
	  	     out.println("<tr><td width='*%' class='rep-body' align='center'>"+m_orient_add1+", "+m_orient_add2+", "+m_orient_city_name+". Tel : "+m_orient_tel_no+" Fax : "+m_orient_fax_no+" Email : "+m_orient_email+"</b></td></tr>");
						
					 	 
					 out.println("</table>");
						
					 out.println("<br><br>"); 	
					
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					 out.println("<tr><td width='*%' class='rep-body' align='center'><b>ACKNOWLEDGEMENT OF POST DATED CHEQUES(WITHDRAW)</b></td></tr>");
	  	     					
					 out.println("</table>");
						
					 out.println("<br><br>"); 	
						
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='*%' class='rep-body' align='left'>Name : &nbsp&nbsp"+m_name+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
						
									
					 out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					 out.println("<tr>");
					 out.println("<td width='50%' class='rep-body' align='left'>Agreement No : &nbsp&nbsp"+m_finance_no+"</td>");
					 out.println("<td width='50%' class='rep-body' align='left'>Date : &nbsp&nbsp"+Sys_date+"</td>");
					 out.println("</tr>");
					 out.println("</table>");	
						
										
					
					rs1=stmt1.executeQuery(Sql_new);
					more =rs1.next();
					int i=1;
					if(more){
					
					  out.println("<table border=\"1\" align='center' cellspacing='0' width='100%' bordercolor='black' class='table' >");	
					
					  out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='30%'   class='rep_body' align='left' >Bank</td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'>Branch</td>");
						out.println("<td width='15%'  class='rep_body' align='left'>Cheque No</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >Date Of Cheque</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>Amount(Rs.)</td>");
						out.println("</tr>"); 
						
					}		
																		
										
					while(more){
					
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='center'>"+i+"</td>"); 
						out.println("<td width='30%'   class='rep_body' align='left' >"+rs1.getString(3)+"</td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'>"+rs1.getString(4)+"</td>");
						out.println("<td width='15%'  class='rep_body' align='left'>"+rs1.getString(5)+"</td>"); 
						out.println("<td width='15%'   class='rep_body' align='left' >"+rs1.getString(6)+"</td>"); 
						out.println("<td width='20%'  class='rep_body' align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
						out.println("</tr>"); 
															
						i=i+1;
						
						more =rs1.next();
						
					}
					
										
					  out.println("</table>");
						
						out.println("<br><br>"); 	

						
	  			  out.println("<table border=\"0\" align='center' cellspacing='0' width='100%' class='table' >");	
            
						out.println("<tr > ");
						out.println("<td width='60%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='40%'   class='rep_body' align='right' > --------------------------------------------------- </td>"); 
						out.println("</tr>"); 
						out.println("<tr>"); 
						out.println("<td width='60%'  class='rep_body' align='left'><i>Original receipt will be issued upon realization of cheque's</i></td>"); 
						out.println("<td width='40%'   class='rep_body' align='right' >Received by :"+m_schema_name+" Accounts Department</td>"); 
						out.println("</tr>"); 

						out.println("</table>");
						
						
						
					
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
						
					}

			
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



