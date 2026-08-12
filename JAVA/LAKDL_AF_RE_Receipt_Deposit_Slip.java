import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : NUWAN DE SILVA    DATE:27-03-2007

public class LAKDL_AF_RE_Receipt_Deposit_Slip extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1;
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
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("print_deposit_slip")){
							
			String m_deposit_no=req.getParameter("deposit_no");
			
		//	String m_print=req.getParameter("print");
			
			
			String Sys_date="";
			String m_orient_name="";
			
				
					 String		Sql_sys_date=" SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') "+
																 " FROM DUAL ";
																	
					rs1=stmt1.executeQuery(Sql_sys_date);
					boolean more =rs1.next();
					
					if(more){
					Sys_date=rs1.getString(1);
					}
																	
																	
																	
					 String		Sql_company_details=" SELECT "+
					    " COMPANY_NAME, "+
					    " ADDRESS1, "+
					    " ADDRESS2, "+
					    " CITY, "+
					    " TEL_NO, "+
					    " FAX_NO,  "+
							" VAT_RATE "+
							" FROM "+m_schema_name+".AF_CO_MAS_COMPANY_DETAILS ";
							
				  	rs1=stmt1.executeQuery(Sql_company_details);
					  more =rs1.next();


							 											
											if(more)
											{
											m_orient_name=rs1.getString(1);
											}
				
						
	
					
					
						/*String		Sql_deposit=" SELECT "+
						"    DISTINCT A.DIPOSIT_NO, "+//1
						"    'CR' TYPE, "+//2
						"    A.RECEIPT_NO DOC_NO, " +//3
						"  	 DECODE(SETTLE_MODE,'CHEQUE',C.CHEQUE_NO,'CASH','-') CHEQUE_NO ,  "+//4
						"    DECODE(SETTLE_MODE,'CHEQUE',NVL(TO_CHAR(C.CHEQUE_DATE,'DD-MM-YYYY'),'-'),'CASH','-') CHEQUE_DATE,  " +//5
						"    DECODE(SETTLE_MODE,'CHEQUE',NVL(C.PAYER_BRANCH_CODE,'-'),'CASH','-') PAYER_BRANCH_CODE,  "+     //6
						"	   DECODE(SETTLE_MODE,'CHEQUE',INITCAP("+m_schema_name+".AF_CO_GET_BANK_NAME(C.PAYER_BRANCH_CODE)),'CASH','-') BRANCH_NAME,  "+//7
						"    A.AMOUNT, "+//8
						"    TO_CHAR(B.DIPOSIT_DATE,'DD/MM/YYYY'), "+//9
						"    B.ACC_NO, "+//10
						"    B.BRANCH_CODE, "+//11
						"    C.SETTLE_MODE, "+//12
						"    D.BANK_CODE, "+//13
            "    DECODE(SETTLE_MODE,'CHEQUE',INITCAP("+m_schema_name+".AF_CO_GET_BANK_NAME(D.BRANCH_CODE)),'CASH','-') CLIENT_BRANCH_NAME "+ //14
						"    FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_DIPOSIT B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C ,"+m_schema_name+".AF_CO_MAS_CLIENT_BANKS D"+
						"   WHERE A.DIPOSIT_NO=B.DIPOSIT_NO AND  "+
						"         A.RECEIPT_NO=C.REC_NO  AND "+
						"         D.ACCOUNT_NO=C.PAYER_ACC_NO  AND "+
						"       A.STATUS='Y' AND B.STATUS='Y' AND "+
						"       A.DIPOSIT_NO='"+m_deposit_no+"'  ";
						*/
												
						
						String		Sql_deposit=" SELECT "+
						"    DISTINCT A.DIPOSIT_NO, "+//1
						"    'CR' TYPE, "+//2
						"    NVL(C.SUB_REC_NO,C.REC_NO) DOC_NO, " +//3 modified by udara on 08-08-2013 // "    A.RECEIPT_NO DOC_NO, " +//3
						"  	 NVL(DECODE(SETTLE_MODE,'CHEQUE',C.CHEQUE_NO,'CASH','-'),'-') CHEQUE_NO ,  "+//4
						"    DECODE(SETTLE_MODE,'CHEQUE',NVL(TO_CHAR(C.CHEQUE_DATE,'DD-MM-YYYY'),'-'),'CASH',TO_CHAR(C.EFF_VALDATE,'DD-MM-YYYY')) CHEQUE_DATE,  " +//5
						"    DECODE(SETTLE_MODE,'CHEQUE',NVL(C.PAYER_BRANCH_CODE,'-'),'CASH','-') PAYER_BRANCH_CODE,  "+     //6
						"	   DECODE(SETTLE_MODE,'CHEQUE',INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(C.PAYER_BRANCH_CODE)),'CASH','-') BRANCH_NAME,  "+//7
						"    A.AMOUNT, "+//8
						"    TO_CHAR(B.DIPOSIT_DATE,'DD-MM-YYYY HH:MI:SS AM'), "+ // "    TO_CHAR(B.DIPOSIT_DATE,'DD/MM/YYYY'), "+//9 // mod by udara on 30-08-2013
						"    B.ACC_NO, "+//10
						"    B.BRANCH_CODE, "+//11
						"    C.SETTLE_MODE, "+//12
						//"    F.BANK_CODE, "+//13
						//"  	 DECODE(SETTLE_MODE,'CHEQUE',F.BANK_CODE,'CASH','-') BANK_CODE ,  "+//13
						"  	 NVL((SELECT BANK_CODE  FROM   "+m_schema_name+".AF_CO_MAS_BANK_BRANCH  WHERE  BRANCH_CODE=C.PAYER_BRANCH_CODE),'-') BANK_CODE ,  "+//13
						//"    DECODE(SETTLE_MODE,'CHEQUE',INITCAP("+m_schema_name+".AF_CO_GET_BANK_NAME(B.BRANCH_CODE)),'CASH','-') CLIENT_BRANCH_NAME,   "+ //14
						"    DECODE(SETTLE_MODE,'CHEQUE',INITCAP("+m_schema_name+".AF_CO_GET_BRANCH_NAME_2(C.PAYER_BRANCH_CODE)),'CASH','-') CLIENT_BRANCH_NAME,   "+ //14
						" 	 "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.BRANCH_CODE), "+//15
						"    "+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(A.ENT_USER)) "+ // 16 added by udara on 08-08-2013
						"    FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_DIPOSIT B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C, "+
						"    "+m_schema_name+".AF_CO_MAS_LICENCEE_SETTLEMENT E, "+m_schema_name+".AF_CO_MAS_BANK_BRANCH F "+
						"    WHERE A.DIPOSIT_NO=B.DIPOSIT_NO AND   "+
						"    A.RECEIPT_NO=C.REC_NO  AND  "+
						"    B.ACC_NO=E.ACC_NO AND "+
						"    B.BRANCH_CODE=E.BRANCH_CODE AND "+
						"    B.BRANCH_CODE=F.BRANCH_CODE AND "+
						"    A.STATUS='Y' AND B.STATUS='Y' AND  "+
						"    C.STATUS NOT IN ('C','CAD') AND "+  //added by nuwan de silva on 08-04-2008
						"    A.DIPOSIT_NO='"+m_deposit_no+"'  "+
						//"    ORDER BY A.RECEIPT_NO ";
						"    ORDER BY DOC_NO "; // added by udara on 08-08-2013

			
					
				
					
					
					 out.println("<HTML><HEAD><TITLE>Charges Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						
						
			out.println("<script>");
			out.println("function save_data(){");

			out.println("m_table.innerHTML=\"\" ");
	
			out.println("window.print();");
								
			out.println("}");
			
			
			out.println("function add_button(){");
			
			//if (m_print.trim().equals("FALSE")) {
			//out.println("m_table.innerHTML=\"\" ");
			//}
			
			//else
			//{
			out.println("m_writedata='<tr><td width=\"*%\" align=\"right\"><input class=\"but_input\" type=\"button\" name=\"BUT_PRINT\" value=\"Print\" onClick=\"save_data()\"></td></tr>';"); 
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\">'+");
    	out.println("m_writedata+'</table>';");
			//}
				
			out.println("}");
			
			out.println("</script>");



					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' bgcolor='white' onLoad=\"add_button()\" >");
					 out.println("<FORM NAME='Form1' method='post'>"); 

						
				 out.println("<table align='center' width='100%' class='table'>"); 
			   out.println("<tr>");  
			   out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		     out.println("</tr>"); 
			   out.println("</table>");
					
				
					  out.println("<br><br>"); 	
					
					  out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					  out.println("<tr > ");
						out.println("<td width='60%'  class='rep_body' align='center'>"+m_orient_name.toUpperCase()+"</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left' >Date</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>"+Sys_date+"</td>");
						out.println("</tr>"); 
						
						out.println("<tr > ");
						out.println("<td width='60%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left' >Page</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'></td>");
						out.println("</tr>"); 

						
					out.println("</table>");
					
					out.println("<br><br><br>"); 	
					
					String depo_branch = ""; // added by udara on 08-08-2013
					
					rs1=stmt1.executeQuery(Sql_deposit);
					more =rs1.next();
					
					if(more){
						
						depo_branch = rs1.getString(16);
					
					  out.println("<table border=\"0\" align='center' width='100%' class='table' >");	
					
					  out.println("<tr > ");
						out.println("<td width='20%'  class='rep_body' align='left'>Bank Code/Name</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='60%'  class='rep_body' align='left'>"+rs1.getString(11)+" - "+rs1.getString(15)+"</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >&nbsp;</td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'>&nbsp;</td>");
						out.println("</tr>"); 
						
						out.println("<tr > ");
						out.println("<td width='10%'  class='rep_body' align='left'>Account No</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='60%'  class='rep_body' align='left'>"+rs1.getString(10)+"</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >&nbsp;</td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'>&nbsp;</td>");
						out.println("</tr>"); 
						
						
						out.println("<tr > ");
						out.println("<td width='10%'  class='rep_body' align='left'>Deposit No</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='60%'  class='rep_body' align='left'>"+rs1.getString(1)+" &nbsp;&nbsp;&nbsp; "+rs1.getString(9)+"</td>");
						out.println("<td width='10%'  class='rep_body' align='left'>Activity</td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'>Leasing</td>");
						out.println("</tr>"); 
						
						/*
						// added by udara on 08-08-2013
						
						out.println("<tr > ");
						out.println("<td width='10%'  class='rep_body' align='left'> Branch </td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
						out.println("<td width='60%'  class='rep_body' align='left'>  "+rs1.getString(16)+" </td>"); depo_branch
						out.println("<td width='10%'  class='rep_body' align='left'> &nbsp; </td>"); 
						out.println("<td width='2%'   class='rep_body' align='center' > &nbsp; </td>"); 
						out.println("<td width='15%'  class='rep_body' align='left'> &nbsp; </td>");
						out.println("</tr>");
						
						// end by udara on 08-08-2013
						*/
										
						
					out.println("</table>");
					
					out.println("<br><br>"); 	
					
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr > ");
					out.println("<td width='100%'  class='rep_body' align='left'>"); 
					out.println("---------------------------------------------------------------------------"+
					            "------------------------------------------------------------------------------------</td>");
						
					out.println("</tr > ");
					out.println("</table>");
					
					
					out.println("<table align='center' width='100%' class='table' >");						
		 
			  					
						out.println("<tr > ");//class=txt_report_column
						out.println("<td width='5%'  class='rep_body' align='left'><b>SINo</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left' ><b>Type</td>"); 
						out.println("<td width='18%' class='rep_body' align='left' ><b>Doc No</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'><b>Chq No</td>");
						out.println("<td width='15%' class='rep_body' align='left' ><b>Cheque Date</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'><b>Bank Code</td>"); 
						out.println("<td width='15%' class='rep_body' align='left' ><b>Payee Bank</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'><b>Amount</td>");
						out.println("</tr>"); 
				
					}
					out.println("</table>");
					
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr > ");
					out.println("<td width='100%'  class='rep_body' align='left'>"); 
					out.println("---------------------------------------------------------------------------"+
					            "------------------------------------------------------------------------------------</td>");
						
					out.println("</tr > ");
					out.println("</table>");

					
					out.println("<br>"); 
					out.println("<table align='center' width='100%' class='table' >");						
					
					int i=1;
					double tot_sub=0;
					double tot_gross=0;
					
					while(more){
					
						out.println("<tr > ");//class=txt_report_column
						out.println("<td width='5%'  class='rep_body' align='left'>"+i+"</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>"+rs1.getString(2)+"</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>"+rs1.getString(3)+"</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>"+rs1.getString(4)+"</td>");
						out.println("<td width='15%' class='rep_body' align='left'>"+rs1.getString(5)+"</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>"+rs1.getString(13)+"</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>"+rs1.getString(14)+"</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
						out.println("</tr>"); 					
						
						tot_sub=tot_sub+rs1.getDouble(8);
						tot_gross=tot_gross+rs1.getDouble(8);
						//if(i==8){
							if(i>0 && i%8==0){
						
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>---------------</td>");
						out.println("</tr>"); 					
						
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>"+nf.format(tot_sub)+"</td>");
						out.println("</tr>"); 					
						
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>---------------</td>");
						out.println("</tr>"); 			
						
						out.println("<tr></tr><tr></tr><tr></tr><tr></tr>"); 			
						tot_sub=0;
						}
						
						
						i=i+1;
						
						more =rs1.next();
						
					}
					  out.println("<tr></tr><tr></tr><tr></tr><tr></tr>");
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>---------------</td>");
						out.println("</tr>"); 			
						
						
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>Deposit Slip Total</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>"+nf.format(tot_gross)+"</td>");
						out.println("</tr>"); 					
						
						out.println("<tr > ");
						out.println("<td width='5%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='10%'  class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='18%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>");
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='12%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='left'>&nbsp;</td>"); 
						out.println("<td width='15%' class='rep_body' align='right'>---------------</td>");
						out.println("</tr>"); 			
					
					
					
					
					out.println("</table>");
					
					
      	 		out.println("</table>");
					
					// added by udara on 08-08-2013
					out.println("<br>"); 
					
					out.println("<table align='center' width='100%' class='table' >");	
					out.println("<tr > ");
					out.println("<td width='10%'  class='rep_body' align='left'> Branch </td>"); 
					out.println("<td width='2%'   class='rep_body' align='center' >:</td>"); 
					out.println("<td width='60%'  class='rep_body' align='left'>  "+depo_branch+" </td>"); 
					out.println("<td width='10%'  class='rep_body' align='left'> &nbsp; </td>"); 
					out.println("<td width='2%'   class='rep_body' align='center' > &nbsp; </td>"); 
					out.println("<td width='15%'  class='rep_body' align='left'> &nbsp; </td>");
					out.println("</tr>");
					out.println("</table>");
					// end by udara on 08-08-2013	
					
					
					
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
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



