import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY')  "+//9
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='ENTER'  ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\">"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL_CONF")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//9
								" NVL(A.APPROVAL_COMMENTS,'-'),"+//10
								" A.INVOICE_STATUS, "+//11
								" "+m_schema_name+".FA_OP_SYSTEM_CHECK_INVOICE(A.BATCH_NO,A.DEBTOR_CODE,A.INVOICE_NO) "+//12
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS IN('APP_2','APP_C2') ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>System Alert</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Pre. action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\">"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					if(rs1.getString(12).equals("N")){
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"N\"><DIV class=div_input>-</DIV></td>"; 
					}
					else{
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"Y\"><DIV class=div_input>Please Check</DIV></td>"; 
					}
					if(rs1.getString(11).equals("APP_C2")){
						m_string=m_string+"<td width='10%' class=div_input>Disapproved - "+rs1.getString(10)+"</td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input>Approved - "+rs1.getString(10)+"</td>";
					}
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_BATCH_APPROVAL")){
				
				String m_string="";				

				    /**comment by ns 08-04-2011 for performance improve**/
					/*rs1= stmt1.executeQuery(" SELECT "+
								" A.FACILITY_NO, "+//1
								" A.BATCH_NO, "+//2
								" A.CLIENT_CODE, "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
								" NVL(A.TOTAL_BATCH_AMOUNT,0), "+//5
								" NVL(A.TOTAL_BATCH_INVOICES,0), "+//6
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'), "+//7
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'ENTER') "+//8
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER' AND "+
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'ENTER')>0 ");
					*/
					//out.println("conn"+conn);
					
					rs1= stmt1.executeQuery(
					//out.println(
					            " SELECT "+			
								" A.FACILITY_NO,A.BATCH_NO,A.CLIENT_CODE,A.FULL_NAME,NVL(A.TOTAL_BATCH_AMOUNT,0),A.TOTAL_BATCH_INVOICES "+
								" ,A.INVOICE_BATCH_DATE,B.INV_COUNT "+
								" FROM  "+
								" (SELECT  "+
								" A.FACILITY_NO FACILITY_NO, "+
								" A.BATCH_NO BATCH_NO, "+
								" A.CLIENT_CODE CLIENT_CODE, "+
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME, "+
								" NVL(A.TOTAL_BATCH_AMOUNT,0) TOTAL_BATCH_AMOUNT, "+
								" NVL(A.TOTAL_BATCH_INVOICES,0) TOTAL_BATCH_INVOICES, "+
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER'  ) A, "+

								" (SELECT A.FACILITY_NO,A.BATCH_NO,COUNT(INVOICE_NO) INV_COUNT "+
								" FROM   "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
								" WHERE  INVOICE_STATUS='ENTER' "+
								" GROUP BY A.FACILITY_NO,A.BATCH_NO)B "+

								" WHERE A.FACILITY_NO=B.FACILITY_NO"+
								" AND   A.BATCH_NO=B.BATCH_NO "+
								" AND   INV_COUNT >0 ");

          

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Total Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='30%' ><DIV class=div_input><b>Pending Invoices / Total Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input></DIV></td>"; 
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='30%' class=div_input>"+rs1.getString(8)+"/"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Show Invoices\" onClick=\"show_invoices('"+rs1.getString(2)+"')\" style='width:100'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS_ALL' VALUE="+chk_nums+">";
				
				m_string=m_string+"</table>";
				
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_BATCH_APPROVAL_CONF")){
				
				String m_string="";				

					/*rs1= stmt1.executeQuery(" SELECT "+
								" A.FACILITY_NO, "+//1
								" A.BATCH_NO, "+//2
								" A.CLIENT_CODE, "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
								" NVL(A.TOTAL_BATCH_AMOUNT,0), "+//5
								" NVL(A.TOTAL_BATCH_INVOICES,0), "+//6
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'), "+//7
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'APP_2') "+//8
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER' AND "+
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'APP_2')>0");
                     */

                     					rs1= stmt1.executeQuery(
					//out.println(
					            " SELECT "+			
								" A.FACILITY_NO,A.BATCH_NO,A.CLIENT_CODE,A.FULL_NAME,NVL(A.TOTAL_BATCH_AMOUNT,0),A.TOTAL_BATCH_INVOICES "+
								" ,A.INVOICE_BATCH_DATE,B.INV_COUNT "+
								" FROM  "+
								" (SELECT  "+
								" A.FACILITY_NO FACILITY_NO, "+
								" A.BATCH_NO BATCH_NO, "+
								" A.CLIENT_CODE CLIENT_CODE, "+
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME, "+
								" NVL(A.TOTAL_BATCH_AMOUNT,0) TOTAL_BATCH_AMOUNT, "+
								" NVL(A.TOTAL_BATCH_INVOICES,0) TOTAL_BATCH_INVOICES, "+
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER'  ) A, "+

								" (SELECT A.FACILITY_NO,A.BATCH_NO,COUNT(INVOICE_NO) INV_COUNT "+
								" FROM   "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
								" WHERE  INVOICE_STATUS='APP_2' "+
								" GROUP BY A.FACILITY_NO,A.BATCH_NO)B "+

								" WHERE A.FACILITY_NO=B.FACILITY_NO"+
								" AND   A.BATCH_NO=B.BATCH_NO "+
								" AND   INV_COUNT >0 ");					 

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Total Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='30%' ><DIV class=div_input><b>Pending Invoices / Total Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input></DIV></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='30%' class=div_input>"+rs1.getString(8)+"/"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Show Invoices\" onClick=\"show_invoices('"+rs1.getString(2)+"')\" style='width:100'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS_ALL' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL_BATCH")){
				
				String m_batch=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY')  "+//9
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='ENTER'  AND "+
								" A.BATCH_NO='"+m_batch+"'");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\"><u>"+rs1.getString(7)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class=\"div_input\"><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL_BATCH_CONF")){
				
				String m_batch=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//9
								" NVL(A.APPROVAL_COMMENTS,'-'), "+//10
								" A.INVOICE_STATUS, "+//11
								" "+m_schema_name+".FA_OP_SYSTEM_CHECK_INVOICE(A.BATCH_NO,A.DEBTOR_CODE,A.INVOICE_NO) "+//12
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS IN('APP_2','APP_C2')  AND "+
								" A.BATCH_NO='"+m_batch+"'");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>System Alert</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Pre. action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\"><u>"+rs1.getString(7)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					if(rs1.getString(12).equals("N")){
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"N\"><DIV class=div_input>-</DIV></td>"; 
					}
					else{
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"Y\"><DIV class=div_input>Please Check</DIV></td>"; 
					}
					if(rs1.getString(11).equals("APP_C2")){
						m_string=m_string+"<td width='10%' class=div_input>Disapproved - "+rs1.getString(10)+"</td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input>Approved - "+rs1.getString(10)+"</td>";
					}
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_APPROVAL_BATCH")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT "+
								" A.FACILITY_NO, "+//1
								" A.BATCH_NO, "+//2
								" A.CLIENT_CODE, "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
								" NVL(A.TOTAL_BATCH_AMOUNT,0), "+//5
								" NVL(A.TOTAL_BATCH_INVOICES,0), "+//6
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'), "+//7
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'Y'), "+//8
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'C'),"+//9
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'O') "+//10
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER' ORDER BY A.INVOICE_BATCH_DATE ");
			
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Total Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Total Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Approve Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Disapprove Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Pending Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(2)+"')\" style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf1.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf1.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf1.format(rs1.getDouble(9))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf1.format(rs1.getDouble(10))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ADJUST_APPROVAL")){
				
				String m_string="";				
				String m_sql="";	
				String m_adjust_type=req.getParameter("adjust_type");
				
				
				/*m_sql=" SELECT "+
				" ADJUSTMENT_NO, "+//1
				" FACILITY_NO, "+//2
				" CLIENT_CODE, "+//3
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//4
				" BATCH_NO, "+//5
				" DEBTOR_CODE, "+//6 
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//7
				" INVOICE_NO,  "+//8
				" NVL(INVOICE_AMOUNT,0), "+//9
				" TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'), "+//10
				" NVL(ADJUSTMENT_COMMENTS,'-') "+//11
				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_ADJUST "+
				" WHERE INVOICE_STATUS='N' AND ADJUST_CATEGORY='"+m_adjust_type+"' "+
				" ORDER BY ADJUSTMENT_NO ";*/
				
				//added by nuwan de silva 21-01-2009 --------------------------------
				if(m_adjust_type.equals("EFT")) {
				
				rs1 = stmt1.executeQuery(" SELECT  "+
				" REF_NO,"+
				" NVL(TRANSFER_AMOUNT,0), "+
				" TO_CHAR(TRANSFER_DATE,'DD-MM-YYYY'), "+
				" FACILITY_NO,  "+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)," +
				" RECEIPT_NO," +
				" NVL(COMMENTS,'-')," +
				" DECODE(ADJ_TYPE,'NS','Non Sales Funds Transfer','EX','Excess Funds Transfer ','Other') " +
				" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT " +
				" WHERE STATUS='E' "+
				" ORDER BY REF_NO ");
				
				}else	if(m_adjust_type.equals("CRT")) {
				
				/* Comment By ns on 22-07-2011 for add the chq number into the query*/
				/*rs1 = stmt1.executeQuery(" SELECT "+
				" REF_NO, "+
				" NVL(TRANSFER_AMOUNT,0), "+
				" TO_CHAR(TRANSFER_DATE,'DD-MM-YYYY'), "+
				" FACILITY_NO, "+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)," + 
				" RECEIPT_NO," +
				" NVL(COMMENTS,'-')," +
				" DECODE(ADJ_TYPE,'CRT','Cheque Retrun Trasnsfer') " + 
				" FROM "+m_schema_name+".FA_OP_PRO_CHQ_RET_ADJUSTMENT " +
				" WHERE STATUS='E' " + 
				" ORDER BY REF_NO ");
				*/
				/* Added By ns on 22-07-2011 for add the chq number into the query*/
				rs1 = stmt1.executeQuery(" SELECT "+
				" A.REF_NO, "+
				" NVL(A.TRANSFER_AMOUNT,0), "+
				" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+
				" A.FACILITY_NO, "+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE)," + 
				" A.RECEIPT_NO," +
				" NVL(A.COMMENTS,'-')," +
				" DECODE(A.ADJ_TYPE,'CRT','Cheque Retrun Trasnsfer'), " + 
				" B.CHEQUE_NO CHEQUE_NO" +
				" FROM "+m_schema_name+".FA_OP_PRO_CHQ_RET_ADJUSTMENT A , "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B " +
				" WHERE  A.RECEIPT_NO=B.RECEIPT_NO "+
				" AND    A.STATUS='E' " + 
				" ORDER BY REF_NO ");
				
				
							
				}
				else{
				rs1 = stmt1.executeQuery(" SELECT "+
				//out.println(" SELECT "+
				" A.ADJUSTMENT_NO, "+
				" NVL(A.ADJUST_AMOUNT,0), "+
				" TO_CHAR(A.ADJUST_DATE,'DD-MM-YYYY'),"+
				" A.INVOICE_STATUS, "+
				" A.ADJUST_TYPE,  "+
				" A.ADJUST_CATEGORY, "+
				" NVL(A.ADJUSTMENT_COMMENTS,'-'), "+
				" DECODE(A.ADJUST_CATEGORY,'INA','Invoice Adjustments','CLA','Client Adjustments','FAA','Facility Adjustments',  'DBA','Debtor Adjustments','Other') ,"+
				" NVL(A.NARRATION,'-'), "+
				" NVL(A.INVOICE_NO,'-'), "+//Added By Sandun on 25-09-2009
				" NVL(INVOICE_AMOUNT,0), "+//Added By Sandun on 25-09-2009
				" NVL(BALANCE_AMOUNT,0) "+//Added By Sandun on 25-09-2009
				" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B " + 
				" WHERE A.INVOICE_NO = B.INVOICE_NO(+) "+
				" AND A.BATCH_NO = B.BATCH_NO(+) "+
				" AND A.INVOICE_STATUS='N' AND A.ADJUST_CATEGORY='"+m_adjust_type+"' " +
				" ORDER BY A.ADJUSTMENT_NO "); 
        }
				
				//comment by nuwan de silva on 21-01-2009
				
				/*rs1= stmt1.executeQuery(" SELECT "+
				" ADJUSTMENT_NO, "+//1
				" NVL(ADJUST_AMOUNT,0), "+//2
				" TO_CHAR(ADJUST_DATE,'DD-MM-YYYY'), "+//3
				" INVOICE_STATUS, "+//4
				" ADJUST_TYPE,"+//5
				" ADJUST_CATEGORY,"+//6
				" NVL(ADJUSTMENT_COMMENTS,'-'),"+//7
				" DECODE(ADJUST_CATEGORY,'INA','Invoice Adjustments','CLA','Client Adjustments','FAA','Facility Adjustments', "+
				" 'DBA','Debtor Adjustments','Other') "+//8
				" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
				" WHERE INVOICE_STATUS='N' AND ADJUST_CATEGORY='"+m_adjust_type+"' "+
				" ORDER BY ADJUSTMENT_NO ");
				
				*/
				
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>";
				if(!m_adjust_type.equals("CRT") && !m_adjust_type.equals("EFT") && !m_adjust_type.equals("CLA")){//Added By Sandun on 25-09-2009
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Invoice Amount Rs.</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Balance Amount Rs.</b></DIV></td>";
				}
				if(m_adjust_type.equals("CRT") ){//Added By Sandun on 25-09-2009
				m_string=m_string+"<td width='7%' ><DIV class=div_input><b>Chq No</b></DIV></td>";
				}
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Adjusted Amount Rs.</b></DIV></td>";
  			    m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Adjustment Date</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Type</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Category</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='12%' class=div_input style='cursor:hand'  onClick=\"show_adjustment_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_ADJUSTMENT_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					if(!m_adjust_type.equals("CRT") && !m_adjust_type.equals("EFT") && !m_adjust_type.equals("CLA")){//Added By Sandun on 25-09-2009
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(10)+"</td>";//Added By Sandun on 25-09-2009
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(11))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(12))+"</td>";
					}
					
					if(m_adjust_type.equals("CRT") ){
				    m_string=m_string+"<td width='7%' class=div_input >"+rs1.getString("CHEQUE_NO")+"</td>";
				    }
					
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(2))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input >"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(8)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select style='width:85px' name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					
					
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_POD_CHEQUE_REALISATION")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				/*s1= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO,"+//1
				" B.BRANCH_NAME, "+//2
				" A.PAYER_ACC_NO, "+ //3
				" A.CHEQUE_NO, "+//4
				" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+//5
				" A.CHEQUE_AMOUNT, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.DEBTOR_CODE) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO C "+
				" WHERE  A.POD_STATUS='N' AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE AND A.POD_REF_NO=C.POD_REF_NO AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY A.POD_REF_NO ");*/
				
				rs1= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO,"+//1
				" B.BRANCH_NAME, "+//2
				" A.PAYER_ACC_NO, "+ //3
				" A.CHEQUE_NO, "+//4
				" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+//5
				" A.CHEQUE_AMOUNT, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
				" WHERE  A.POD_STATUS='N' AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY A.POD_REF_NO ");
				
				boolean more = rs1.next();
				
				if(more) {
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>POD Ref No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Branch Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Account No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";

				m_string=m_string+"</tr>";
				}
				int chk_nums=0;
				int j=1;
				
				while(more){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class='div_input' style='cursor:hand' onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_POD_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' align=right>"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input'><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"R\">Banking</option><OPTION value=\"C\">Cancel</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"'  style=\"width: 170px\"  maxlength=\"200\" ></td>";  // added by ashini on 21-02-2008 for ofscl modifications

					m_string=m_string+"</tr>";
					chk_nums++;
					more = rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			
				else if(m_chksql.equals("LOAD_POD_CHEQUE_APPROVAL")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				rs1= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO,"+//1
				" B.BRANCH_NAME, "+//2
				" A.PAYER_ACC_NO, "+ //3
				" A.CHEQUE_NO, "+//4
				" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+//5
				" A.CHEQUE_AMOUNT, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(A.DEBTOR_CODE,A.CLIENT_CODE)) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
				" WHERE  A.POD_STATUS='E' AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE AND "+
				" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
				" TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY A.POD_REF_NO ");
				
				boolean more = rs1.next();
				
				if(more) {
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>POD Ref No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Branch Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Account No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor/Client Name</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>";
				//m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";

				m_string=m_string+"</tr>";
				}
				int chk_nums=0;
				int j=1;
				
				while(more){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class='div_input' style='cursor:hand' onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_POD_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' align=right>"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input'><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					//m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"'  style=\"width: 170px\"  maxlength=\"200\" ></td>";  // added by ashini on 21-02-2008 for ofscl modifications

					m_string=m_string+"</tr>";
					chk_nums++;
					more = rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
				else if(m_chksql.equals("LOAD_POD_CHEQUE_REVIEW")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				
				rs1= stmt1.executeQuery(" SELECT DISTINCT A.POD_REF_NO,"+//1
				" B.BRANCH_NAME, "+//2
				" A.PAYER_ACC_NO, "+ //3
				" A.CHEQUE_NO, "+//4
				" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'), "+//5
				" A.CHEQUE_AMOUNT, "+//6
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(NVL(A.DEBTOR_CODE,A.CLIENT_CODE)) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".AF_CO_MAS_BANK_BRANCH B "+
				" WHERE  A.POD_STATUS='N' AND A.PAYER_BRANCH_CODE=B.BRANCH_CODE AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
				" TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY A.POD_REF_NO ");
				
				boolean more = rs1.next();
				
				if(more) {
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>POD Ref No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Branch Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Account No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor/Client Name</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>";
				//m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";

				m_string=m_string+"</tr>";
				}
				int chk_nums=0;
				int j=1;
				
				while(more){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class='div_input' style='cursor:hand' onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_POD_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input' >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' align=right>"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='15%' class='div_input' >"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class='div_input'><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					//m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"'  style=\"width: 170px\"  maxlength=\"200\" ></td>";  // added by ashini on 21-02-2008 for ofscl modifications

					m_string=m_string+"</tr>";
					chk_nums++;
					more = rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			else if(m_chksql.equals("LOAD_SETTLEMENT_DEPOSIT_DETAILS")){
			
				String m_settle_mode=req.getParameter("SETTLE_MODE");
				
				String m_string="";				
					
				rs1= stmt1.executeQuery("SELECT RECEIPT_NO,"+//1
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',CHEQUE_NO,SETTLE_MODE),'-'), "+//2
					" PAYER_BRANCH_CODE,"+//3
					" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),"+//4
					" PAYER_ACC_NO,"+//5
       		" REC_AMOUNT "+//6
 					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE SETTLE_MODE='"+m_settle_mode+"' AND REC_STATUS='N'"+
					" ORDER BY EFF_VALDATE");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='20%' class='div_input'><b>Receipt No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Cheque No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Branch Code</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Branch Name</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Account No</b></td>";
				m_string=m_string+"<td width='20%' class='div_input' align=right><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='5%' class='div_input'></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"20%\" class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><input class=\"txt_input\" type=\"hidden\" name=\"TXT_RECEIPT_NO_"+chk_nums+"\" value=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_REF_NO_"+chk_nums+"\" value=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(3)+"</td>"+
					"<td width=\"20%\" class=div_input>"+rs1.getString(4)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(5)+"</td>"+
					"<td width=\"20%\" class=div_input  align=right><input class=\"txt_input\" type=\"hidden\" name=\"TXT_AMOUNT_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(6))+"\" style='text-align:right;'>"+nf.format(rs1.getDouble(6))+"</td>"+
					"<td width=\"5%\" class=div_input><input type=\"checkbox\" name=\"TXT_SETTLE_CHECK_"+chk_nums+"\" onclick='load_deposit_total(\""+chk_nums+"\")'></td>"+
					"</tr>";
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_DEPOSIT_DETAIL_DELETES")){
			
				String m_deposit_no=req.getParameter("DEPOSIT_NO");
				
				String m_string="";				
					
				rs1= stmt1.executeQuery("SELECT "+
				" A.RECEIPT_NO,  "+
				" NVL(B.CHEQUE_NO,'-'), "+
				" B.PAYER_BRANCH_CODE, "+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE), "+
				" B.PAYER_ACC_NO, "+
				" A.DEPOSIT_AMOUNT "+
				" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
				" WHERE A.DEPOSIT_NO='"+m_deposit_no+"' AND A.RECEIPT_NO=B.RECEIPT_NO "+
				" ORDER BY B.EFF_VALDATE");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='20%' class='div_input'><b>Receipt No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Cheque No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Branch Code</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Branch Name</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Account No</b></td>";
				m_string=m_string+"<td width='20%' class='div_input' align=right><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='5%' class='div_input'></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"20%\" class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><input class=\"txt_input\" type=\"hidden\" name=\"TXT_RECEIPT_NO_"+chk_nums+"\" value=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(3)+"</td>"+
					"<td width=\"20%\" class=div_input>"+rs1.getString(4)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(5)+"</td>"+
					"<td width=\"20%\" class=div_input align=right><input class=\"txt_input\" type=\"hidden\" name=\"TXT_AMOUNT_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>"+
					"<td width=\"5%\" class=div_input><input type=\"checkbox\" name=\"TXT_SETTLE_CHECK_"+chk_nums+"\" onclick='load_deposit_total(\""+chk_nums+"\")' checked disabled></td>"+
					"</tr>";
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICES_POD_CHEQUES")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				String m_batch_no=req.getParameter("BATCH_NO");
				String m_invoice_no=req.getParameter("INVOICE_NO");
				
				String m_string="";				
				String m_sql="";						
						
				rs1= stmt1.executeQuery(
							" SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE,INV_DATE,INV_AMOUNT,DISPLAY_BAL FROM( "+
					 		" SELECT X.BATCH_NO,"+//1
							" X.INVOICE_NO,"+//2
							" X.DEBTOR_CODE,"+//3
							" TO_CHAR(X.INVOICE_DATE,'DD-MM-YYYY') INV_DATE,"+//4
							" NVL(X.INVOICE_AMOUNT,0) INV_AMOUNT,"+//5
							" (NVL(X.BALANCE_AMOUNT,0)-NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(Y.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) DISPLAY_BAL "+//6
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
							" WHERE "+
							" X.BATCH_NO=Y.BATCH_NO AND "+
							" Y.BATCH_NO LIKE '%"+m_batch_no+"%' AND "+
							" X.INVOICE_NO LIKE '%"+m_invoice_no+"%' AND "+
							" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+ 
							" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
							" Y.CLIENT_CODE='"+m_client_code+"' AND "+
							" Y.FACILITY_NO='"+m_facility_no+"' "+
							" ORDER BY X.INVOICE_DATE "+
							" ) "+
							" WHERE DISPLAY_BAL > 0  "
							);
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>"; // added by ashini 
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' style='text-align:right' class='txt_input' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"  onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\"></td>"; // added by ashini
					m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\" ></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICES_POD_CHEQUES_EDIT")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_pod_code=req.getParameter("POD_CODE");
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				
				String m_string="";				
				String m_sql="";						

				rs1= stmt1.executeQuery(" SELECT BATCH_NO, "+//1
						" INVOICE_NO,"+//2
						" DEBTOR_CODE,"+//3
						" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),"+//4
						" NVL(INVOICE_AMOUNT,0),"+//5
						" NVL(BALANCE_AMOUNT,0), "+//6
						" STYPE, "+//7
						" ALLO_AMOUNT "+//8
						" FROM ( "+
						" SELECT X.BATCH_NO, "+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" NVL(X.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+ //COMMENTED BY MADHAWA 2011-04-26
						//" (NVL(X.BALANCE_AMOUNT,0) - NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(X.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) BALANCE_AMOUNT, "+
						" 'N' STYPE, "+
						" NVL(X.BALANCE_AMOUNT,0) ALLO_AMOUNT "+ //ADDED BY ASHINI 
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.CLIENT_CODE='"+m_client_code+"' AND "+
						" Y.FACILITY_NO='"+m_facility_no+"' AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" (X.BATCH_NO,X.INVOICE_NO,X.DEBTOR_CODE) NOT IN "+
						" (SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO  "+
						" WHERE POD_REF_NO='"+m_pod_code+"') "+
						" UNION ALL "+
						" SELECT X.BATCH_NO,"+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" NVL(X.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+ //COMMENTED BY MADHAWA 2011-04-26
						//" (NVL(X.BALANCE_AMOUNT,0) - NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(X.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) BALANCE_AMOUNT, "+ //ADDED BY MADHAWA 2011-04-27
						" 'Y' STYPE, "+
						" NVL(Y.ALLO_AMOUNT,0) ALLO_AMOUNT "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.DEBTOR_CODE=Y.DEBTOR_CODE AND "+
						" X.INVOICE_NO=Y.INVOICE_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.CLIENT_CODE='"+m_client_code+"' AND "+
						" Y.FACILITY_NO='"+m_facility_no+"' AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" Y.POD_REF_NO='"+m_pod_code+"'  "+
						" ) WHERE BALANCE_AMOUNT > 0 "+
						" ORDER BY INVOICE_DATE "); // 23-09-2011 comment by nuwan de silva on 23-09-2011

						/*" SELECT X.BATCH_NO,"+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" NVL(X.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+
						" 'Y' STYPE "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.CLIENT_CODE='"+m_client_code+"' AND "+
						" Y.FACILITY_NO='"+m_facility_no+"' AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" (X.BATCH_NO,X.INVOICE_NO,X.DEBTOR_CODE) IN "+
						" (SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO  "+
						" WHERE POD_REF_NO='"+m_pod_code+"') "+
						" ) ORDER BY INVOICE_DATE ");*/ // COMMENTED BY ASHINI TO ADD ABOVE QUERY 
         
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>"; // ADDED BY ASHINI ON 05-03-2008
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					if(rs1.getString(7).equals("Y")){
						m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' style='text-align:right' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(8))+"\"   onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\" ></td>";//disabled
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\" checked></td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' style='text-align:right' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(8))+"\"   onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\" ></td>";
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\"></td>";
					}
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_RETURN_REALISE")){
			
				String m_account_no=req.getParameter("ACCOUNT_NO");
				
				String m_string="";				
					
				rs1= stmt1.executeQuery("SELECT A.DEPOSIT_NO, "+//1
				" TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'),"+//2
				" B.RECEIPT_NO,  "+//3
				" B.DEPOSIT_AMOUNT, "+//4
				" NVL(B.CHEQUE_NO,'-'), "+//5
				" TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'), "+//8
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.CLIENT_CODE),"+//9
				" DECODE(C.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.DEBTOR_CODE)) "+//10
  			" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A,"+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS B,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
  			" WHERE A.DEPOSIT_NO=B.DEPOSIT_NO AND B.RECEIPT_NO=C.RECEIPT_NO AND "+
        " A.ACC_NO='"+m_account_no+"' AND "+
        " A.STATUS='Y' AND C.REC_STATUS='B'"+
				" ORDER BY A.DEPOSIT_DATE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class='div_input'><b>Deposit No</b></td>";
				m_string=m_string+"<td width='15%' class='div_input'><b>Deposit Date</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Receipt No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input' align=right><b>Deposit Amount</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Cheque No</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Client Name</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Debtor Name</b></td>";
				m_string=m_string+"<td width='15%' class='div_input'><b>Comments</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Return/Realize Date</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Return/Realize Date-Company</b></td>";
				m_string=m_string+"<td width='5%' class='div_input'><b>Realize</b></td>";
				m_string=m_string+"<td width='5%' class='div_input'><b>Return</b></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DEPOSIT_NO_"+chk_nums+"\" value=\""+rs1.getString(1)+"\">"+rs1.getString(1)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input onClick=\"show_receipt_details('"+rs1.getString(3)+"')\"  style='cursor:hand'><input class=\"txt_input\" type=\"hidden\" name=\"TXT_RECEIPT_NO_"+chk_nums+"\" value=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>"+
					"<td width=\"10%\" class=div_input align=right><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DEPOSIT_AMT_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(4))+"\">"+nf.format(rs1.getDouble(4))+"</td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_REF_NO_"+chk_nums+"\" value=\""+rs1.getString(5)+"\">"+rs1.getString(5)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(9)+"</td>"+
					"<td width=\"10%\" class=div_input>"+rs1.getString(10)+"</td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" maxlength=\"200\" onblur=\"help_cheque_comments('"+chk_nums+"')\">"+
					"<INPUT TYPE='BUTTON' class='but_input' NAME='TXT_CHEQUE_"+chk_nums+"' VALUE=\"Help\" onclick=\"help_cheque_comments('"+chk_nums+"')\">"+
					"</td>"+
					//Modified by Dianaka Jayasuriya on 2009-10-19 for date validation
					"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_REALIZE_DD_"+chk_nums+"\" VALUE=\""+rs1.getString(6)+"\" class=\"txt_input5\"  maxlength='2' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_"+chk_nums+",document.Form1.TXT_REALIZE_MM_"+chk_nums+",document.Form1.TXT_REALIZE_YY_"+chk_nums+")\"><input class=\"txt_input5\"  type=\"TEXT\" name=\"TXT_REALIZE_MM_"+chk_nums+"\" VALUE=\""+rs1.getString(7)+"\"  maxlength='2' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_"+chk_nums+",document.Form1.TXT_REALIZE_MM_"+chk_nums+",document.Form1.TXT_REALIZE_YY_"+chk_nums+")\"><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_REALIZE_YY_"+chk_nums+"\" VALUE=\""+rs1.getString(8)+"\"  maxlength='4' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_"+chk_nums+",document.Form1.TXT_REALIZE_MM_"+chk_nums+",document.Form1.TXT_REALIZE_YY_"+chk_nums+")\"><input class=\"txt_input\" type=\"hidden\" name=\"TXT_RSTATUS_"+chk_nums+"\" value=\"N\" ></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_REALIZE_DD_CO_"+chk_nums+"\" VALUE=\""+rs1.getString(6)+"\" class=\"txt_input5\"  maxlength='2' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_CO_"+chk_nums+",document.Form1.TXT_REALIZE_MM_CO_"+chk_nums+",document.Form1.TXT_REALIZE_YY_CO_"+chk_nums+")\"><input class=\"txt_input5\"  type=\"TEXT\" name=\"TXT_REALIZE_MM_CO_"+chk_nums+"\" VALUE=\""+rs1.getString(7)+"\"  maxlength='2' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_CO_"+chk_nums+",document.Form1.TXT_REALIZE_MM_CO_"+chk_nums+",document.Form1.TXT_REALIZE_YY_CO_"+chk_nums+")\"><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_REALIZE_YY_CO_"+chk_nums+"\" VALUE=\""+rs1.getString(8)+"\"  maxlength='4' size='2' onblur=\"checkMonthLength(document.Form1.TXT_REALIZE_DD_CO_"+chk_nums+",document.Form1.TXT_REALIZE_MM_CO_"+chk_nums+",document.Form1.TXT_REALIZE_YY_CO_"+chk_nums+")\"></td>"+
					"<td width=\"5%\" class=div_input><input type=\"checkbox\" name=\"TXT_SETTLE_REALIZE_"+chk_nums+"\" onclick='check_tick_1("+chk_nums+")'></td>"+
					"<td width=\"5%\" class=div_input><input type=\"checkbox\" name=\"TXT_SETTLE_RETURN_"+chk_nums+"\" onclick='check_tick_2("+chk_nums+")'></td>"+
					"</tr>";
					//end
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_PAYMENT_APPROVAL")){
				
				String m_client_mgr=req.getParameter("CLIENT_MGR");

				String m_string="";				
				
				if(m_client_mgr.equals("ALL")){
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" ,NVL(CHEQUE_NO,'')"+//8
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					// " WHERE A.PAY_STATUS='ENTER' "+
					" WHERE A.PAY_STATUS='PRINT' "+
					" ORDER BY A.PAY_DATE ");
				}
				else{
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" ,NVL(CHEQUE_NO,'')"+ //8
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					// " WHERE A.PAY_STATUS='ENTER' AND "+
					" WHERE A.PAY_STATUS='PRINT' AND "+
					" UPPER("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'C','C'))=UPPER('"+m_client_mgr+"')"+
					" ORDER BY A.PAY_DATE ");
				}

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Payment Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Mode</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(8)+"</td>";//added by ns on 11-10-2010
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='*%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT>";
					m_string=m_string+"<INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\">"; 
					m_string=m_string+"<INPUT TYPE='BUTTON' class='txt_input' NAME='TXT_AVAILABLE_"+chk_nums+"' VALUE=\"Availability\" maxlength=\"100\" onclick=\"show_client_availability('"+rs1.getString(2)+"','"+rs1.getString(4)+"')\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_PAYMENT_HIGH_APPROVAL")){
				
				String m_client_mgr=req.getParameter("CLIENT_MGR");

				String m_string="";				
				
				if(m_client_mgr.equals("ALL")){
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					" WHERE A.PAY_STATUS='HIGH' "+
					" ORDER BY A.PAY_DATE ");
				}
				else{
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					" WHERE A.PAY_STATUS='HIGH' AND "+
					" UPPER("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'C','C'))=UPPER('"+m_client_mgr+"')"+
					" ORDER BY A.PAY_DATE ");
				}

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Payment Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Mode</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='*%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT>";
					m_string=m_string+"<INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\">";
					m_string=m_string+"<INPUT TYPE='BUTTON' class='txt_input' NAME='TXT_AVAILABLE_"+chk_nums+"' VALUE=\"Availability\" maxlength=\"100\" onclick=\"show_client_availability('"+rs1.getString(2)+"','"+rs1.getString(4)+"')\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
				else if(m_chksql.equals("LOAD_PAYMENT_REALISATION")){
				
				//String m_licen_account=req.getParameter("LICEN_ACCOUNT");

				String m_string="";				
				
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.DISB_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE  ,"+//7
					" TO_CHAR(A.DISB_DATE,'DD'), "+//8
					" TO_CHAR(A.DISB_DATE,'MM'), "+//9
					" TO_CHAR(A.DISB_DATE,'YYYY') ,"+//10
					" "+m_schema_name+".af_co_get_branch_name_2(A.LIC_BRANCH_CODE) , "+ //11
					" A.CHEQUE_NO ,"+ //12
					" TO_CHAR(SYSDATE,'DD'), "+//13
					" TO_CHAR(SYSDATE,'MM'), "+//14
					" TO_CHAR(SYSDATE,'YYYY'), "+//15
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)  "+ // added by udara on 22-11-2011
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					" WHERE A.PAY_STATUS='DISB' "+
					" AND (  A.REALISE_STATUS <>'YES' "+
					"  OR    A.REALISE_STATUS IS NULL )"+
					//" AND   A.LIC_ACC_NO='"+m_licen_account+"' "+
					" ORDER BY A.PAYMENT_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				//m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>"; // added by udara on 22-11-2011
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Payment No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Bank Branch</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Payment Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Disburse Date</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Realise Date</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					//m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='12%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(4)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  onClick=\"show_client('"+rs1.getString(2)+"')\" >"+rs1.getString(16)+"</td>"; // added by udara on 22-11-2011
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  >"+rs1.getString(1)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  >"+rs1.getString(11)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  >"+rs1.getString(12)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"<INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_TXT_ST_DD_"+chk_nums+"' VALUE=\""+rs1.getString(8)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_TXT_ST_MM_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='HID_TXT_ST_YY_"+chk_nums+"' VALUE=\""+rs1.getString(10)+"\"></td>";
					m_string=m_string+"<td width='20%' class=div_input><input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_DD_"+chk_nums+"\" maxlength=\"2\" size=\"2\"  VALUE=\""+rs1.getString(13)+"\" onblur=\"validate_date_realise("+chk_nums+")\" >"; // Modified by Thamali Jayatunga on 2009.10.20, Added onblur event
			    m_string=m_string+"<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_MM_"+chk_nums+"\" maxlength=\"2\" size=\"2\"  VALUE=\""+rs1.getString(14)+"\" onblur=\"validate_date_realise("+chk_nums+")\" >"; // Modified by Thamali Jayatunga on 2009.10.20, Added onblur event
			    m_string=m_string+"<input class=\"txt_input5\" type=\"text\" name=\"TXT_ST_YY_"+chk_nums+"\" maxlength=\"4\" size=\"4\"  VALUE=\""+rs1.getString(15)+"\" onblur=\"validate_date_realise("+chk_nums+")\"   >[DD-MM-YYYY]</td>"; //[DD-MM-YYYY] //onblur=\"check_dates(document.Form1.TXT_ST_DD_"+chk_nums+",document.Form1.TXT_ST_MM_"+chk_nums+",document.Form1.TXT_ST_YY_"+chk_nums+")\" tabindex=\"3\"
					m_string=m_string+"<td width='*%' align=\"center\" class=div_input><INPUT TYPE=\"checkbox\" NAME=TXT_CHK_"+chk_nums+"  onclick=\"check_condition(this)\"></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_PAYMENT_CONFIRM")){
				
				String m_client_mgr=req.getParameter("CLIENT_MGR");

				String m_string="";				
				
				if(m_client_mgr.equals("ALL")){
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					" WHERE A.PAY_STATUS='APPR1' "+
					" ORDER BY A.PAY_DATE ");
				}
				else{
					rs1= stmt1.executeQuery(" SELECT  "+
					" A.PAYMENT_CODE, "+//1
					" A.CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.FACILITY_NO, "+//4
					" A.PAYMENT_AMOUNT,"+//5
					" TO_CHAR(A.PAY_DATE,'DD-MM-YYYY'), "+//6
					" A.SETTLE_MODE "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS A "+
					" WHERE A.PAY_STATUS='APPR1' AND "+
					" UPPER("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.CLIENT_CODE,'C','C'))=UPPER('"+m_client_mgr+"')"+
					" ORDER BY A.PAY_DATE ");
				}

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Payment Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Mode</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT>";
					m_string=m_string+"<INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\">"; 
					m_string=m_string+"<INPUT TYPE='BUTTON' class='txt_input' NAME='TXT_AVAILABLE_"+chk_nums+"' VALUE=\"Availability\" maxlength=\"100\" onclick=\"show_client_availability('"+rs1.getString(2)+"','"+rs1.getString(4)+"')\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICES_FOR_SCHEDULE_CHEQUES")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				
				String m_string="";				
				String m_sql="";						
						
				rs1= stmt1.executeQuery(" SELECT X.BATCH_NO,"+//1
							" X.INVOICE_NO,"+//2
							" X.DEBTOR_CODE,"+//3
							" TO_CHAR(X.INVOICE_DATE,'DD-MM-YYYY'),"+//4
							" NVL(X.INVOICE_AMOUNT,0),"+//5
							" NVL(X.BALANCE_AMOUNT,0), "+//6
							" Y.FACILITY_NO,"+//7
							" Y.CLIENT_CODE, "+//8
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(Y.CLIENT_CODE) "+//9
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
							" WHERE "+
							" X.BATCH_NO=Y.BATCH_NO AND "+
							" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
							" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
							" (X.BATCH_NO,X.INVOICE_NO,X.DEBTOR_CODE) NOT IN  "+
							" (SELECT A.BATCH_NO,A.INVOICE_NO,A.DEBTOR_CODE "+
							" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B "+
							" WHERE A.POD_REF_NO=B.POD_REF_NO AND B.POD_STATUS='N') "+
							" ORDER BY X.INVOICE_DATE ");
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>";
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(8)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(8)+"\"><u>"+rs1.getString(9)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(7)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\"><u>"+rs1.getString(7)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' style='text-align:right' class='txt_input' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"  onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\"></td>";
					m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\" onclick=\"check_status("+chk_nums+")\"></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICES_FOR_SCHEDULE_CHEQUES_EDIT")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_pod_code=req.getParameter("POD_CODE");
				
				String m_string="";				
				String m_sql="";						

				rs1= stmt1.executeQuery(" SELECT BATCH_NO, "+//1
						" INVOICE_NO,"+//2
						" DEBTOR_CODE,"+//3
						" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),"+//4
						" NVL(INVOICE_AMOUNT,0),"+//5
						" NVL(BALANCE_AMOUNT,0), "+//6
						" STYPE, "+//7
						" FACILITY_NO,"+//8
						" CLIENT_CODE,"+//9
						" CLIENT_NAME, "+//10
						" ALLO_AMOUNT "+//11
						" FROM ( "+
						" SELECT X.BATCH_NO, "+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" NVL(X.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+
						" 'N' STYPE, "+
						" Y.FACILITY_NO,"+
						" Y.CLIENT_CODE, "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(Y.CLIENT_CODE) CLIENT_NAME, "+
						" NVL(X.BALANCE_AMOUNT,0) ALLO_AMOUNT "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" (X.BATCH_NO,X.INVOICE_NO,X.DEBTOR_CODE) NOT IN "+
						" (SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO  "+
						" WHERE SETTLE_SCHDULE_REF_NO='"+m_pod_code+"') "+
						" UNION ALL "+
						" SELECT X.BATCH_NO,"+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" NVL(X.BALANCE_AMOUNT,0) BALANCE_AMOUNT, "+
						" 'Y' STYPE, "+
						" Y.FACILITY_NO,"+
						" Y.CLIENT_CODE, "+
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(Y.CLIENT_CODE) CLIENT_NAME, "+
						" NVL(Y.ALLO_AMOUNT,0) ALLO_AMOUNT "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_OP_PRO_SETTLE_SCH_ALLO Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.DEBTOR_CODE=Y.DEBTOR_CODE AND "+
						" X.INVOICE_NO=Y.INVOICE_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" Y.SETTLE_SCHDULE_REF_NO='"+m_pod_code+"' "+
						" ) ORDER BY INVOICE_DATE ");
	
	
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>";
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(9)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\"><u>"+rs1.getString(10)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(8)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(8)+"\"><u>"+rs1.getString(8)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(3)+"','"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					if(rs1.getString(7).equals("Y")){
						m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' style='text-align:right' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(11))+"\"   onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\" disabled></td>";
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\"  onclick=\"check_status("+chk_nums+")\" checked ></td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' style='text-align:right' NAME='TXT_INV_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(11))+"\"   onblur=\"format_num(document.Form1.TXT_INV_ALLO_"+chk_nums+",4);check_value("+chk_nums+")\" ></td>";
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\"  onclick=\"check_status("+chk_nums+")\"></td>";
					}
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_AVAIL_SETTLEMENT")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");					
						
				rs1= stmt1.executeQuery(" SELECT "+
							" A.RECEIPT_NO, "+//1
							" A.SETTLE_MODE, "+//2
							" A.REC_AMOUNT, "+//3
							" A.BALANCE_AMOUNT,"+//4
							" A.ALLO_AMOUNT,"+//5
							" TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),"+//6
							" DECODE(A.RECEIPT_TYPE,'POD','POD','OTHER'),"+//7
							" NVL(A.SUS_REF_NO,'-') "+//8
							" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
							" WHERE "+
							" A.REC_STATUS='Y' "+
							" AND A.CLIENT_CODE='"+m_client_code+"' "+
							" AND A.FACILITY_NO='"+m_facility_no+"' "+
							" AND A.BALANCE_AMOUNT<>0 "+
							" ORDER BY A.EFF_VALDATE ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Settlement Mode</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Value Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Receipt Type</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Ref. No</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Receipt Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>");
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
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs1.getString(6)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");

			}
			else if(m_chksql.equals("LOAD_AVAIL_BALANCE")){

				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");					
						
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO, "+//1
							" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),"+//2
							" A.INVOICE_AMOUNT,"+//3
							" A.SETTLE_AMOUNT,"+//4
							" A.BALANCE_AMOUNT, "+//5
							" A.DEBTOR_CODE, "+//6
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE) CLIENT_NAME, "+//7
							" A.INVOICE_NO "+//8
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
							" WHERE A.INVOICE_STATUS='CONF' "+
							" AND A.BATCH_NO=B.BATCH_NO "+
							" AND B.FACILITY_NO='"+m_facility_no+"' "+
							" AND B.CLIENT_CODE='"+m_client_code+"' "+
							" AND A.BALANCE_AMOUNT<>0 "+
							" ORDER BY A.DUE_DATE ");
							
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Settlement Amount</b></DIV></td>");
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
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(6)+"')\"><u>"+rs1.getString(7)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style='cursor:hand' onClick=\"show_invoice_details('"+rs1.getString(6)+"','"+rs1.getString(8)+"')\"><u>"+rs1.getString(8)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</HTML>");

			}
			else if(m_chksql.equals("LOAD_CHEQUE_PRINTING")){
				
				String m_string="";				
				String m_sql="";						
						
				rs1= stmt1.executeQuery("SELECT PAYMENT_CODE, "+
							" CLIENT_CODE,  "+
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+
							" FACILITY_NO, "+
							" PAYMENT_AMOUNT, "+
							" TO_CHAR(PAY_DATE,'DD-MM-YYYY'), "+
							" NVL(APP_COMMENTS,'-') "+
							" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
							// " WHERE PAY_STATUS='CONF' "+
							" WHERE PAY_STATUS='ENTER' "+
							// " AND SETTLE_MODE='CHEQUE' "+
							" ORDER BY PAY_DATE ");
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Payment Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Payment Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Approval Comments</b></DIV></td>";
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_payment_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_PAYMENT_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(2)+"')\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_facility('"+rs1.getString(4)+"')\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><input type=\"checkbox\" name=\"TXT_PRINT_STATUS_"+chk_nums+"\"></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL_CREDIT")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//9
								" NVL(A.APPROVAL_COMMENTS,'-'),"+//10
								" A.INVOICE_STATUS, "+//11
								" "+m_schema_name+".FA_OP_SYSTEM_CHECK_INVOICE(A.BATCH_NO,A.DEBTOR_CODE,A.INVOICE_NO) "+//12
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS='APP_C' ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>System Alert</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\">"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"Y\"><DIV class=div_input>Please Check</DIV></td>"; 
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_BATCH_APPROVAL_CREDIT")){
				
				String m_string="";				

					/*rs1= stmt1.executeQuery(" SELECT "+
								" A.FACILITY_NO, "+//1
								" A.BATCH_NO, "+//2
								" A.CLIENT_CODE, "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
								" NVL(A.TOTAL_BATCH_AMOUNT,0), "+//5
								" NVL(A.TOTAL_BATCH_INVOICES,0), "+//6
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY'), "+//7
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'APP_C') "+//8
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER' AND "+
								" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(A.BATCH_NO,'APP_C')>0");
                     */
					 
					 
					 					rs1= stmt1.executeQuery(
					//out.println(
					            " SELECT "+			
								" A.FACILITY_NO,A.BATCH_NO,A.CLIENT_CODE,A.FULL_NAME,NVL(A.TOTAL_BATCH_AMOUNT,0),A.TOTAL_BATCH_INVOICES "+
								" ,A.INVOICE_BATCH_DATE,B.INV_COUNT "+
								" FROM  "+
								" (SELECT  "+
								" A.FACILITY_NO FACILITY_NO, "+
								" A.BATCH_NO BATCH_NO, "+
								" A.CLIENT_CODE CLIENT_CODE, "+
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE) FULL_NAME, "+
								" NVL(A.TOTAL_BATCH_AMOUNT,0) TOTAL_BATCH_AMOUNT, "+
								" NVL(A.TOTAL_BATCH_INVOICES,0) TOTAL_BATCH_INVOICES, "+
								" TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
								" WHERE A.APPROVE_STATUS='ENTER'  ) A, "+

								" (SELECT A.FACILITY_NO,A.BATCH_NO,COUNT(INVOICE_NO) INV_COUNT "+
								" FROM   "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
								" WHERE  INVOICE_STATUS='APP_C' "+
								" GROUP BY A.FACILITY_NO,A.BATCH_NO)B "+

								" WHERE A.FACILITY_NO=B.FACILITY_NO"+
								" AND   A.BATCH_NO=B.BATCH_NO "+
								" AND   INV_COUNT >0 ");
								
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Total Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='30%' ><DIV class=div_input><b>Pending Invoices / Total Invoices</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input></DIV></td>"; 
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='30%' class=div_input>"+rs1.getString(8)+"/"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Show Invoices\" onClick=\"show_invoices('"+rs1.getString(2)+"')\" style='width:100'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS_ALL' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICE_ALL_APPROVAL_BATCH_CREDIT")){
				
				String m_batch=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
								" B.FACILITY_NO,  "+//1
								" A.BATCH_NO,   "+//2
								" B.CLIENT_CODE,  "+//3
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),  "+//4
								" A.DEBTOR_CODE,  "+//5
								" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),  "+//6
								" A.INVOICE_NO,  "+//7
								" NVL(A.INVOICE_AMOUNT,0),  "+//8
								" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),  "+//9
								" NVL(A.APPROVAL_COMMENTS,'-'), "+//10
								" A.INVOICE_STATUS, "+//11
								" "+m_schema_name+".FA_OP_SYSTEM_CHECK_INVOICE(A.BATCH_NO,A.DEBTOR_CODE,A.INVOICE_NO) "+//12
								" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B  "+
								" WHERE A.BATCH_NO=B.BATCH_NO AND A.INVOICE_STATUS IN('APP_C')  AND "+
								" A.BATCH_NO='"+m_batch+"'");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>System Alert</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Pre. action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input   style='cursor:hand'  onClick=\"show_facility('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(5)+"')\" style='cursor:hand'><u><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(5)+"','"+rs1.getString(7)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\"><u>"+rs1.getString(7)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					if(rs1.getString(12).equals("N")){
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"N\"><DIV class=div_input>-</DIV></td>"; 
					}
					else{
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' NAME='TXT_SYS_CHK_"+chk_nums+"' VALUE=\"Y\"><DIV class=div_input>Please Check</DIV></td>"; 
					}
					if(rs1.getString(11).equals("APP_C")){
						m_string=m_string+"<td width='10%' class=div_input>Disapproved - "+rs1.getString(10)+"</td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input>Approved - "+rs1.getString(10)+"</td>";
					}
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INVOICES_RECEIPT")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				
				String m_string="";				
				String m_sql="";						
						
				rs1= stmt1.executeQuery(
					        " SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE,INVOICE_DATE,INVOICE_AMOUNT,DISPLAY_BAL FROM ( "+
					        " SELECT X.BATCH_NO,"+//1
							" X.INVOICE_NO,"+//2
							" X.DEBTOR_CODE,"+//3
							" TO_CHAR(X.INVOICE_DATE,'DD-MM-YYYY') INVOICE_DATE,"+//4
							" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+//5
							" ( NVL(X.BALANCE_AMOUNT,0) - NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(Y.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) DISPLAY_BAL "+//6
							" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
							" WHERE "+
							" X.BATCH_NO=Y.BATCH_NO AND "+
							" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+ //COMMENTED BY MADHAWA 2011-04-26
							" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
							" Y.CLIENT_CODE='"+m_client_code+"' AND "+
							" Y.FACILITY_NO='"+m_facility_no+"' "+
							" ORDER BY X.INVOICE_DATE "+
							" )WHERE DISPLAY_BAL > 0  ");
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>";
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					//m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ALLO_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"></td>";
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' align=right NAME='TXT_ALLO_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\"></td>";
					m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\" ></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("REBANK_RECEIPT_EDIT")){

				String m_receipt_no=req.getParameter("RECEIPT_NO");
				
				String m_string="";						
						
				rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,REC_AMOUNT,RE_BANK_RECEIPT_NO,RE_BANK_REC_AMOUNT,ALLO_REC_AMOUNT "+
  					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT_REBANK "+
						" WHERE RECEIPT_NO='"+m_receipt_no+"'");
				
				int chk_nums=1;
				int j=1;
				
				while(rs1.next()){
					m_string=m_string+"<table width='70%' class='table' >";
					m_string=m_string+"<tr><td width='1%' class=div_input ><B>"+chk_nums+"</B></td>";
					m_string=m_string+"<td width='10%' class=div_input ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\" DISABLED></td>";
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_TOTAL_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(4))+"\" DISABLED></td>";
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_AMT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(5))+"\" DISABLED>";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_receipt('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					chk_nums++;
				}
				
				out.println(m_string);
			}
			
			// Added by Udara on 30-05-2011
			
			else if(m_chksql.equals("REBANK_PD_EDIT")){

				String m_pd_no=req.getParameter("POD_REF_NO");
				
				String m_string="";						
						
				rs1= stmt1.executeQuery(" SELECT POD_REF_NO,CHEQUE_AMOUNT,RE_BANK_RECEIPT_NO,RE_BANK_REC_AMOUNT,ALLO_REC_AMOUNT "+
  					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHQ_REBANK "+
						" WHERE POD_REF_NO='"+m_pd_no+"'");
				
				int chk_nums=1;
				int j=1;
				
				while(rs1.next()){
					m_string=m_string+"<table width='70%' class='table' >";
					m_string=m_string+"<tr><td width='1%' class=div_input ><B>"+chk_nums+"</B></td>";
					m_string=m_string+"<td width='10%' class=div_input ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_NO_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\" DISABLED></td>";
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_TOTAL_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(4))+"\" DISABLED></td>";
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_RE_DEPOSIT_RECEIPT_AMT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(5))+"\" DISABLED>";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_receipt('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"</table>";
					chk_nums++;
				}
				
				out.println(m_string);
			}

			// End by Udara on 30-05-2011
			
			else if(m_chksql.equals("LOAD_INVOICES_RECEIPT_EDIT")){

				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_pod_code=req.getParameter("POD_CODE");
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				
				String m_string="";				
				String m_sql="";						

				rs1= stmt1.executeQuery(" SELECT BATCH_NO, "+//1
						" INVOICE_NO,"+//2
						" DEBTOR_CODE,"+//3
						" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY'),"+//4
						" NVL(INVOICE_AMOUNT,0),"+//5
						" NVL(BALANCE_AMOUNT,0), "+//6
						" STYPE, "+//7
						" ALLO_AMOUNT "+//8
						" FROM ( "+
						" SELECT X.BATCH_NO, "+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" (NVL(X.BALANCE_AMOUNT,0)-NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(X.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) BALANCE_AMOUNT, "+
						" 'Y' STYPE, "+
						" Y.ALLO_AMOUNT ALLO_AMOUNT "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO Y "+
						" WHERE "+
						" Y.RECEIPT_NO='"+m_pod_code+"' AND "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.INVOICE_NO=Y.INVOICE_NO AND "+
						" X.DEBTOR_CODE=Y.DEBTOR_CODE AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.CLIENT_CODE='"+m_client_code+"' AND "+
						" Y.FACILITY_NO='"+m_facility_no+"' AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' "+
						" UNION ALL "+
						" SELECT X.BATCH_NO,"+
						" X.INVOICE_NO,"+
						" X.DEBTOR_CODE,"+
						" X.INVOICE_DATE,"+
						" NVL(X.INVOICE_AMOUNT,0) INVOICE_AMOUNT,"+
						" (NVL(X.BALANCE_AMOUNT,0) - NVL("+m_schema_name+".FA_GET_POD_ALLO_AMT(X.BATCH_NO,X.DEBTOR_CODE,X.INVOICE_NO),0)) BALANCE_AMOUNT, "+
						" 'N' STYPE, "+
						" X.BALANCE_AMOUNT ALLO_AMOUNT "+
						" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL X,"+m_schema_name+".FA_CR_PRO_INVOICE Y "+
						" WHERE "+
						" X.BATCH_NO=Y.BATCH_NO AND "+
						" X.INVOICE_STATUS<>'CANCEL' AND X.BALANCE_AMOUNT>0 AND "+
						" Y.CLIENT_CODE='"+m_client_code+"' AND "+
						" Y.FACILITY_NO='"+m_facility_no+"' AND "+
						" X.DEBTOR_CODE='"+m_debtor_code+"' AND "+
						" (X.BATCH_NO,X.INVOICE_NO,X.DEBTOR_CODE) NOT IN "+
						" (SELECT BATCH_NO,INVOICE_NO,DEBTOR_CODE "+
						" FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO  "+
						" WHERE RECEIPT_NO='"+m_pod_code+"') "+
						" ) WHERE BALANCE_AMOUNT > 0  ORDER BY INVOICE_DATE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Batch No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Invoice Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=right><DIV class=div_input><b>Allo Amount</b></DIV></td>";
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b></b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='20%' class=div_input style='cursor:hand' onClick=\"show_invoice_batch_details('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_BATCH_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INVOICE_NO_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\">"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input >"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_INV_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\">"+nf.format(rs1.getDouble(6))+"</td>";
					if(rs1.getString(7).equals("Y")){
						m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ALLO_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(8))+"\"></td>";
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\" checked></td>";
					}
					else{
					m_string=m_string+"<td width='15%' class=div_input align=right><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ALLO_BALANCE_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(8))+"\"></td>";
						m_string=m_string+"<td width='5%' class=div_input><input type=\"checkbox\" name=\"TXT_POD_STATUS_"+chk_nums+"\"></td>";
					}
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_RENABKING_CHEQUES")){
				
				String m_string="";				
	
					rs1= stmt1.executeQuery("SELECT A.RETURN_NO, "+//1
						" A.RECEIPT_NO,  "+//2
						" A.DEPOSIT_AMOUNT, "+//3
						" A.CHEQUE_NO,  "+//4
						" TO_CHAR(A.REALIZE_DATE,'DD-MM-YYYY'), "+//5
						" NVL(A.RETURN_COMMENTS,'-'), "+//6
						" B.FACILITY_NO, "+//7
						" B.CLIENT_CODE, "+//8
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE) "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
						" WHERE A.RECEIPT_NO=B.RECEIPT_NO "+
						//" AND B.REBANK_STATUS IN('N','C') "+//Commented by Dineth on 16-06-2009
						" AND B.REBANK_STATUS IN('N') "+//Added by Dineth on 16-06-2009
						" ORDER BY A.REALIZE_DATE DESC ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Rerurn No</b></DIV></td>";			
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Return Date</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Return Comments</b></DIV></td>"; 
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(7)+"')\"  style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(8)+"')\"><u>"+rs1.getString(9)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_return_details('"+rs1.getString(1)+"')\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input style='cursor:hand' onClick=\"show_receipt_details('"+rs1.getString(2)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REF_RECEIPT_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='5%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"Y\">Re-Bank</option><OPTION value=\"C\">Cancel</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			//Added by Mahela on 14-06-2007
			else if(m_chksql.equals("LOAD_CLIENT_DISPUTES")){
				
				String m_string="";				
	      String m_facility_no=req.getParameter("FACILITY_NO");
				
				rs1= stmt1.executeQuery(" SELECT"+
				" TO_CHAR(COMMENT_ENT_DATE,'DD-MM-YYYY / HH:MM:SS') COMMENT_ENT_DATE, "+
				" CLIENT_DISPUTES "+
				" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_DISPUTES "+
				" WHERE FACILITY_NO='"+m_facility_no+"' ORDER BY COMMENT_ENT_DATE DESC ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Date / Time </b></DIV></td>";
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Comment</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				
				boolean more =  rs1.next();
				
				if(!more){
				m_string="";
				}
				
				while(more){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(1)+"</td>";
					m_string=m_string+"<td width='*%' class=div_input>"+rs1.getString(2)+"</td>";
					m_string=m_string+"</tr>";
					chk_nums++;
					more =  rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if (m_chksql.trim().equals("LOAD_CLIENT_DEBTOR_DISPUTES")){
				
				String m_facility_code = req.getParameter("m_facility_code").trim();
				String m_client_code = req.getParameter("m_client_code").trim();
				String m_debtor_code = req.getParameter("m_debtor").trim();
				
				String m_string="";		
				
				rs1= stmt1.executeQuery(" SELECT COMMENTS,ENT_USER,TO_CHAR(ENT_DATE,'DD-MM-YYYY HH24:MI:SS') "+
 				" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_CRD_COMMENTS "+
        " WHERE UPPER(FACILITY_NO) =UPPER('"+m_facility_code+"') "+
				" AND (CLIENT_CODE)=UPPER('"+m_client_code+"') "+
				" AND (DEBTOR_CODE)=UPPER('"+m_debtor_code+"') ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Entered User</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Entered Date/Time</b></DIV></td>";
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs1.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='*%' class=div_input>"+rs1.getString(1)+"</td>";
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"</table>";
				out.println(m_string);				
			}
			
			else if (m_chksql.trim().equals("LOAD_FEE_PACKAGE_DETAILS")){//Added by Sandun on 20-09-2008 For FACTORING OPERATION
				
				String m_facility_code = req.getParameter("FACILITY_NO").trim();
				String m_client_code = req.getParameter("CLIENT_CODE").trim();
				int j=1;	
				int i=0;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='100%' class=div_input><b>Fee Package Details</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
	
					rs1= stmt1.executeQuery(" SELECT A.FEE_CODE,B.FEE_DESC,NVL(A.APPLICABLE_VALUE,0),nvl(A.HOLD_STATUS,'N') "+
								" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACT_FEE A,"+m_schema_name+".FA_CO_MAS_FEES B "+
								" WHERE A.FEE_CODE=B.FEE_CODE AND A.FACILITY_NO='"+m_facility_code+"'");
					
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr class='pdn_txtpos2'>");
					out.println("<td width='10%' class=div_input align='left'><b>Fee Code</b></td>");
					out.println("<td width='30%' class=div_input align='left'><b>Description</b></td>");
					out.println("<td width='10%' class=div_input align='right'><b>Fee Value</b></td>");
					out.println("<td width='5%' class=div_input align='center'><b>Hold</b></td>");
					out.println("</tr>");
					while(rs1.next()){
					//i=i+1;
					if(j%2==1){
					out.println("<tr class=tr_input>");
					}else{
					out.println("<tr class=tr_input1>");
					}
					out.println("<input type='hidden' name='hid_fee_code_"+i+"' value="+rs1.getString(1)+" ><input type='hidden' name='hid_fee_value_"+i+"' value="+rs1.getString(3)+" >");
					out.println("<td width='10%' class=div_input align='left'>"+rs1.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input align='left'>"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
					
					if(rs1.getString(4).equals("Y")){
					out.println("<td width='5%'  class=div_input align='center'><input type='checkbox' name='chkbox_fee_"+i+"'   onclick='chk_status(this)' checked></td>");
					}
					else if(rs1.getString(4).equals("N")){
					out.println("<td width='5%'  class=div_input align='center'><input type='checkbox' name='chkbox_fee_"+i+"'   onclick='chk_status(this)'></td>");
					}
					out.println("</tr>");
					j=j+1;
					i=i+1;
					}
					out.println("<input type='hidden' name='hid_line_count' value="+i+">");
				  out.println("</table>");
					out.println("<br>");
				
				
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

