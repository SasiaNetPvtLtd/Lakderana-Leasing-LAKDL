import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
          
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_OP_PRO_sql_invoice_settlement extends javax.servlet.http.HttpServlet {
	
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
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
     	res.setDateHeader("Expires", 0);

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_SETTLEMENT_POD_ALLO")){
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
			rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//7
						" A.CLIENT_CODE,"+//8
						" A.FACILITY_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.RECEIPT_TYPE='POD' "+
						" AND A.BALANCE_AMOUNT<>0 "+
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				m_rec_tot=0;
				m_rec_bal=0;
				
				out.println("<tr class=tr_input1>");
				out.println("<td onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
				
				rs1 = stmt1.executeQuery (" SELECT A.BATCH_NO,"+//1
					" C.INVOICE_SEQ_NO,"+//2
					" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'), "+//3
					" NVL(C.INVOICE_AMOUNT,0),"+//4
					" NVL(C.SETTLE_AMOUNT,0),"+//5
					" NVL(C.BALANCE_AMOUNT,0), "+//6
					" C.INVOICE_NO "+//7
					" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
					" WHERE A.POD_REF_NO=B.POD_REF_NO "+
					" AND A.BATCH_NO=C.BATCH_NO AND A.INVOICE_NO=C.INVOICE_NO "+
					" AND A.DEBTOR_CODE=C.DEBTOR_CODE "+
					" AND A.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.FACILITY_NO='"+m_facility_code+"' "+
					" AND A.POD_REF_NO='"+rs.getString(3)+"' "+
					" AND C.BALANCE_AMOUNT<>0 "+
					" ORDER BY C.INVOICE_DATE ");

				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Invoice No</td>");
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(2)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(4))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(6))+"\" class=\"txt_input2\" ></td>");
					
					m_inv_bal=m_inv_bal+rs1.getDouble(6);
					
					if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}			
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_INV_SP_ALLO")){
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
			rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       			" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.RECEIPT_TYPE='IS' "+
						" AND A.BALANCE_AMOUNT<>0 "+
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				out.println("<tr class=tr_input1>");
				out.println("<td  onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
				
				rs1 = stmt1.executeQuery (" SELECT A.BATCH_NO,"+//1
				  " A.DEBTOR_CODE, "+//2
					" A.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(A.INVOICE_AMOUNT,0),"+//5
					" NVL(A.SETTLE_AMOUNT,0),"+//6
					" NVL(A.BALANCE_AMOUNT,0), "+//7
					" A.INVOICE_NO "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					" WHERE A.BATCH_NO='"+rs.getString(7)+"' "+
					" AND A.DEBTOR_CODE='"+rs.getString(8)+"' "+
					" AND A.INVOICE_NO='"+rs.getString(9)+"' "+
					" AND A.BALANCE_AMOUNT<>0 "+
					" ORDER BY A.INVOICE_DATE ");
				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Invoice No</td>");
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(7))+"\" class=\"txt_input2\"  onchange=\"format_num(this,2);\"></td>");
					
					m_inv_bal=m_inv_bal+rs1.getDouble(7);
					
					out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
					out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked disabled>");
							
					/*if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}		*/	
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_INV_ALLO")){ 
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
			rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       			" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.RECEIPT_TYPE='DS' "+
						" AND A.BALANCE_AMOUNT<>0 "+//12
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				out.println("<tr class=tr_input1>");
				out.println("<td  onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
				
				rs1 = stmt1.executeQuery (" SELECT A.BATCH_NO,"+//1
				  " A.DEBTOR_CODE, "+//2
					" A.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(A.INVOICE_AMOUNT,0),"+//5
					" NVL(A.SETTLE_AMOUNT,0),"+//6
					" NVL(A.BALANCE_AMOUNT,0), "+//7
					" A.INVOICE_NO "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					" WHERE A.DEBTOR_CODE='"+rs.getString(8)+"' "+
					" AND A.BALANCE_AMOUNT<>0 "+
					" ORDER BY A.INVOICE_DATE ");
				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Invoice No</td>");
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(7))+"\" class=\"txt_input2\"></td>");
					
					m_inv_bal=m_inv_bal+rs1.getDouble(7);
					
					if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}			
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_INV_SP_ALLO_2")){  
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
			rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       			" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.RECEIPT_TYPE='IB' "+
						" AND A.BALANCE_AMOUNT<>0 "+
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				out.println("<tr class=tr_input1>");
				out.println("<td  onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
					
				rs1 = stmt1.executeQuery (" SELECT A.BATCH_NO,"+//1
				  " A.DEBTOR_CODE, "+//2
					" A.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(A.INVOICE_AMOUNT,0),"+//5
					" NVL(A.SETTLE_AMOUNT,0),"+//6
					" NVL(A.BALANCE_AMOUNT,0), "+//7
					" NVL(B.ALLO_AMOUNT,0), "+//8
					" A.INVOICE_NO "+//9
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO B "+
					" WHERE "+
					" B.RECEIPT_NO='"+rs.getString(1)+"'"+
					" AND A.BATCH_NO=B.BATCH_NO "+
					" AND A.INVOICE_NO=B.INVOICE_NO "+
					" AND A.BALANCE_AMOUNT<>0 "+
					" ORDER BY A.INVOICE_DATE ");
				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Invoice No</td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(9)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(7))+"\" class=\"txt_input2\"></td>");
					
					m_inv_bal=m_inv_bal+rs1.getDouble(8);
					
					if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}			
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_INV_ALLO_ALL")){
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");
			String m_receipt_no=req.getParameter("RECEIPT_NO");
			String m_batch_no=req.getParameter("BATCH_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
			if(m_receipt_no.equals("")){

			rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       			" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.BALANCE_AMOUNT>0 "+//12
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");
			 }
			 else{
				
			 rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       			" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.CLIENT_CODE='"+m_client_code+"' "+
						" AND A.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.RECEIPT_NO='"+m_receipt_no+"' "+
						" AND A.BALANCE_AMOUNT>0 "+//12
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");
			 }

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				out.println("<tr class=tr_input1>");
				out.println("<td  onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
				
				if(m_receipt_no.equals("")){
				rs1 = stmt1.executeQuery(" SELECT A.BATCH_NO,"+//1
				  " A.DEBTOR_CODE, "+//2
					" A.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(A.INVOICE_AMOUNT,0),"+//5
					" NVL(A.SETTLE_AMOUNT,0),"+//6
					" NVL(A.BALANCE_AMOUNT,0), "+//7
					" INVOICE_NO "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.FACILITY_NO='"+m_facility_code+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.BALANCE_AMOUNT>0 "+
					" ORDER BY A.INVOICE_DATE ");
				}
				else{				
				rs1 = stmt1.executeQuery(" SELECT A.BATCH_NO,"+//1
				  " A.DEBTOR_CODE, "+//2
					" A.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(A.INVOICE_AMOUNT,0),"+//5
					" NVL(A.SETTLE_AMOUNT,0),"+//6
					" NVL(A.BALANCE_AMOUNT,0), "+//7
					" A.INVOICE_NO "+//8
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND B.FACILITY_NO='"+m_facility_code+"' "+
					" AND B.CLIENT_CODE='"+m_client_code+"' "+
					" AND A.INVOICE_NO='"+m_batch_no+"' "+
					" AND A.BALANCE_AMOUNT>0 "+
					" ORDER BY A.INVOICE_DATE ");
				}
				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					//out.println("<td WIDTH=\"20%\">Invoice Seq. No</td>"); COMMENTED BY ASHINI ON 29-02-2008
					out.println("<td WIDTH=\"15%\">Invoice No</td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(7))+"\" class=\"txt_input2\"></td>");
					
					m_inv_bal=m_inv_bal+rs1.getDouble(7);
					
					if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}			
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
			}
			
			
			
			else if(m_chksql.equals("LOAD_INVOICE_UNALLOCATION")){
			
			String m_receipt_no=req.getParameter("RECEIPT_NO");

			out.println("<table class=table border='0' width='100%'>");
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right><input type=\"button\" name=\"end_b\" value=\"Go to End\" class=\"mainbut\" onclick=\"befor_end(document.Form1.top_b)\"></td>");
			out.println("</tr>");
			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='15%' >Receipt No</td>");
			out.println("<td width='15%' align=right>Receipt Amount</td>");
			out.println("<td width='15%' align=right>Allocated Amount</td>");
			out.println("<td width='20%' align=right>Balance Amount</td>");
			out.println("</tr>");
			
			int j=0; 
			
				
			 rs = stmt.executeQuery ("SELECT A.RECEIPT_NO, "+//1
						" A.EFF_VALDATE,"+//2
						" A.SUS_REF_NO,"+//3
						" NVL(A.REC_AMOUNT,0),"+//4
						" NVL(A.ALLO_AMOUNT,0), "+//5
						" NVL(A.BALANCE_AMOUNT,0), "+//6
						" A.BATCH_NO,"+//7
       					" A.DEBTOR_CODE,"+//8
						" A.INVOICE_NO "+//9
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
						" WHERE A.REC_STATUS='Y' "+
						" AND A.RECEIPT_NO='"+m_receipt_no+"' "+
						//" AND A.BALANCE_AMOUNT>0 "+//12
						" AND A.SUSPENDED_ALLOCATION='N' "+//modified by ns on 09-03-2011
						" ORDER BY A.EFF_VALDATE ");

			double m_rec_tot=0;
			double m_rec_bal=0;
			
			while(rs.next()){
				out.println("<tr class=tr_input1>");
				out.println("<td  onClick=\"show_receipt_details('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u><input type=hidden name=\"REC_NO_"+j+"\" value=\""+rs.getString(1)+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(4))+"<input type=hidden name=\"REC_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(4))+"\"></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(5))+"<input type=hidden name=\"ALLO_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(5))+"\"><input type=hidden name=\"Edit_Type"+j+"\" ></td>");
				out.println("<td align=right>"+nf.format(rs.getDouble(6))+"<input type=hidden name=\"BAL_AMOUNT_"+j+"\" value=\""+nf.format(rs.getDouble(6))+"\"></td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				
				out.println("<td colspan=6>");
				
				m_rec_bal=rs.getDouble(6);
				
				rs1 = stmt1.executeQuery(" SELECT B.BATCH_NO,"+//1
				  	" B.DEBTOR_CODE, "+//2
					" B.INVOICE_SEQ_NO,"+//3
					" TO_CHAR(B.INVOICE_DATE,'DD-MM-YYYY'), "+//4
					" NVL(B.INVOICE_AMOUNT,0),"+//5
					//" NVL(A.ALLOCATED_AMOUNT,0),"+//6 A.SETTLE_AMOUNT
					" "+m_schema_name+".FA_GET_ALLOCATE_AMOUNTS(B.INVOICE_SEQ_NO,A.RECEIPT_NO), "+
					" NVL(B.BALANCE_AMOUNT,0), "+//7
					" B.INVOICE_NO "+//8
					" FROM "+m_schema_name+".FA_OP_PRO_SETTLE_ALLO A,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL B "+
					" WHERE "+
					" A.INVOICE_NO=B.INVOICE_SEQ_NO "+
					" AND A.RECEIPT_NO ='"+m_receipt_no+"' "+
					" GROUP BY A.RECEIPT_NO,B.INVOICE_NO,B.BATCH_NO,B.DEBTOR_CODE,B.INVOICE_SEQ_NO,B.INVOICE_DATE,B.INVOICE_AMOUNT,B.BALANCE_AMOUNT "+
					" ORDER BY B.INVOICE_DATE ");
				
				boolean more1 = rs1.next();	
				
				int i=0;
				if(more1){	
					out.println("<table><tr class=\"pdn_txtpos2\" WIDTH=\"100%\">");
					//out.println("<td WIDTH=\"20%\">Invoice Seq. No</td>"); COMMENTED BY ASHINI ON 29-02-2008
					out.println("<td WIDTH=\"15%\">Invoice No</td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td WIDTH=\"15%\">Invoice Seq. No</td>");
					out.println("<td WIDTH=\"15%\">Date</td>");
					out.println("<td WIDTH=\"15%\">Invoice Amount</td>");
					out.println("<td WIDTH=\"15%\">Balance Amount</td>");
					out.println("<td WIDTH=\"15%\">Allocated Amount</td>");
					//out.println("<td WIDTH=\"10%\">Status</td></tr>");
					
					double m_inv_bal=0;
					
					while(more1){	
					out.println("<tr>");
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>"); //ADDED BY ASHINI ON 29-02-2008
					out.println("<td align=left onClick=\"show_invoice_details_ref_no('"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u><input type=\"hidden\" name=\"INV_NO_"+j+"_"+i+"\" disabled value=\""+rs1.getString(3)+"\" class=\"txt_input2\" style=\"width:200;text-align=left\"></td>");
					out.println("<td align=left>"+rs1.getString(4)+"</td>");
					out.println("<td align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td align=right>"+nf.format(rs1.getDouble(7))+"</td>");
					out.println("<td align=right>"+nf.format(rs1.getDouble(6))+"</td>");
					//out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");

					/*out.println("<td align=left><input type=\"text\" name=\"INV_DATE_"+j+"_"+i+"\" disabled value=\""+rs1.getString(4)+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"INV_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(5))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"BAL_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(7))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" disabled value=\""+nf.format(rs1.getDouble(6))+"\" class=\"txt_input2\"></td>");
					out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
					*/
					/*
					m_inv_bal=m_inv_bal+rs1.getDouble(7);
					
					if(m_rec_tot<m_inv_bal){
						if(m_rec_bal>=(m_inv_bal-m_rec_tot)){
							out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format((m_inv_bal-m_rec_tot))+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
							out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
							m_rec_bal=m_rec_bal-(m_inv_bal-m_rec_tot);
							m_rec_tot=m_rec_tot +(m_inv_bal-m_rec_tot);
						}
						else{
							if(m_rec_bal>0){ 
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\" disabled></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"YES\" checked>");
								m_rec_tot=m_rec_tot+m_rec_bal;
								m_rec_bal=0;
							}
							else{
								out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(m_rec_bal)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>");
								out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\" >");		
							}	
						}
					}
					else{
						out.println("<td align=right><input type=\"text\" name=\"SETTLE_AMT_"+j+"_"+i+"\" value=\""+nf.format(0.00)+"\" class=\"txt_input2\" onchange=\"format_num(this,2);chk_bal('"+j+"','"+i+"')\"></td>"); 
						out.println("<td align=center><INPUT TYPE=\"checkbox\" NAME=\"SETTLE_CHK_"+j+"_"+i+"\" onclick=check_status(\""+j+"\",\""+i+"\") value=\"NO\">");
					}	*/		
					out.println("</tr>");
					
					i=i+1;
					more1 = rs1.next();	
				}
				out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></table></div>");
				}
				else{
					out.println("<input type=\"hidden\" name=\"hid_invoice_count_"+j+"\"  value="+i+"></div>");
				}
				
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td>&nbsp;</TD>");
				out.println("<td colspan=5>");
				out.println("</td>");
				out.println("</tr>");
				j=j+1;
			}
			out.println("<tr class=tr_input>");
			out.println("<td align=right colspan=8><input type=button name=top_b value=\"Go to Top\" class=mainbut onclick=\"befor_end(document.Form1.end_b)\"></td>");
			out.println("<input type=\"hidden\" name=\"hid_count\" value="+j+"></tr></table>");
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

