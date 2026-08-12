import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : DISNAKA     DATE:2011-12-29

public class LAKDL_AF_LT_sql_validations_normal extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	ServletOutputStream out;
	
	public ResultSet rs;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public  void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
	Statement stmt=null;
	CallableStatement callstmt=null;
	java.text.NumberFormat nf=null;
	ServletOutputStream out=null;
	
	 ResultSet rs=null;
	 String m_chksql=null;
		
			 out = res.getOutputStream();
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
			
		
			
			m_chksql=req.getParameter("chksql");
			stmt=conn.createStatement();
			
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if (m_chksql.trim().equals("LEGAL_INV_DET")){
				
				String m_finance_no = req.getParameter("financeNo");
				String m_string        = "";
				double m_bal_total     = 0.00 ;
				rs= stmt.executeQuery(""+
				//out.println(""+
				

				    " SELECT P.INVOICE_NO,P.INVOICE_TYPE_DESC,P.TOTAL_AMOUNT,P.SETTELE_AMOUNT,P.BALANCE_TO_BE_RECEIVED,P.CLIENT_CODE,P.ACTIVE_STATUS, "+
					" P.INVOICE_TYPE,P.FINANCE_NO,P.SETTLED_STATUS "+
					" FROM ( "+
					" SELECT "+
					" 	A.INVOICE_NO, "+
					" 	NVL("+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE),'-') INVOICE_TYPE_DESC, "+   
					" 	A.TOTAL_AMOUNT, "+
					" 	A.SETTELE_AMOUNT,"+
					" 	A.BALANCE_TO_BE_RECEIVED, "+
					" 	A.CLIENT_CODE, "+
					" 	A.ACTIVE_STATUS,"+
					" 	A.INVOICE_TYPE, "+
					" 	A.FINANCE_NO ,"+
					" 	A.SETTLED_STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A WHERE FINANCE_NO = '"+m_finance_no+"'  AND ACTIVE_STATUS = 'Y' "+ 
					" AND INVOICE_TYPE IN ('LEGAL_ARR','LEGAL_CAP','LEGAL_ODI') AND BALANCE_TO_BE_RECEIVED > 0 "+
					" AND INVOICE_NO NOT IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_LT_FIN_INVOICE_DET ) "+
				    " UNION "+
				    " SELECT "+
					" 	A.INVOICE_NO, "+
					" 	"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE) , "+   
					" 	A.TOTAL_AMOUNT, "+
					" 	A.SETTELE_AMOUNT,"+
					" 	A.BALANCE_TO_BE_RECEIVED, "+
					" 	A.CLIENT_CODE, "+
					" 	A.ACTIVE_STATUS,"+
					" 	A.INVOICE_TYPE, "+
					" 	A.FINANCE_NO ,"+
					" 	A.SETTLED_STATUS "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A WHERE FINANCE_NO = '"+m_finance_no+"'  AND ACTIVE_STATUS = 'Y' "+ 
					" AND BALANCE_TO_BE_RECEIVED > 0 "+
					" AND INVOICE_NO NOT IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_LT_FIN_INVOICE_DET ) "+
					" ) P "+
					" ORDER BY P.INVOICE_TYPE_DESC ASC");
			
				
				
				m_string=m_string+"<table align='left' width='1000px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Invoice No</b></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Description</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Settle Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Balanace Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Wave off Amount</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input><b>Select</b></DIV></td>";
				
				m_string=m_string+"</tr>";
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width='15%' ><input type='hidden' class='txt_input' name='invNo_"+chk_nums+"' value=\""+rs.getString(1)+"\"><DIV class=div_input>"+rs.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='15%' ><input type='hidden' class='txt_input' name='invType_"+chk_nums+"' value=\""+rs.getString(8)+"\"><DIV class=div_input>"+rs.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='totAmt_"+chk_nums+"' value=\""+rs.getDouble(3)+"\"><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='setAmt_"+chk_nums+"' value=\""+rs.getDouble(4)+"\"><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='hidden' class='txt_input' name='balAmtId_"+chk_nums+"' value=\""+rs.getDouble(5)+"\">"+nf.format(rs.getDouble(5))+"</td>";
					m_string=m_string+"<td width='10%' align='right' ><input type='text' style='text-align:right' class='txt_input' name='waveAmtId_"+chk_nums+"' value=\""+nf.format(rs.getDouble(5))+"\" disabled  ></td>"; //format_num(document.Form1.waveAmtId_"+chk_nums+",4);check_value("+chk_nums+")
					m_string=m_string+"<td width='6%' align='center' ><input type='checkbox' name='received_"+chk_nums+"' onclick=\"calWaveVal()\" ></td>"; 
					
					m_string=m_string+"</tr>";
					
					m_bal_total = m_bal_total + rs.getDouble(5);
					
					
				} 
				
				
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_bal_total)+"</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='right' ><DIV id='div_wave_amt' class=div_input><b>"+nf.format(0)+"</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input></DIV></td>";
				
				m_string=m_string+"</tr>";
				
				
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				out.println(m_string);
				
				
			}
			else if (m_chksql.trim().equals("LEGAL_INV_DET_APP")){
				
				
				String m_string        = "";
				
				rs= stmt.executeQuery(""+
					" SELECT "+
					" 	A.TERMINATION_NO, "+
					" 	A.FINANCE_NO , "+ 
					" 	A.CLIENT_CODE, "+
					" 	A.TOT_TER_AMOUNT "+
					" FROM "+m_schema_name+".AF_LT_FIN_INVOICE A WHERE APP_STATUS = 'N' ");
				
				
				
				
				m_string=m_string+"<table align='left' width='800px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Legal Temination Settlement No.</b></DIV></td>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Finance No.</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total Wave off Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%'  align='center' ><DIV class=div_input><b>Approve</b></DIV></td>";
				m_string=m_string+"<td width='15%'  align='center' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td width='6%'  align='center' ><DIV class=div_input><b>Select</b></DIV></td>";
				//m_string=m_string+"<td width='10%'  align='center' ><DIV class=div_input><b>Details</b></DIV></td>";
				
				m_string=m_string+"</tr>";
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width='15%' style= cursor:hand; onClick=\"show_details('"+chk_nums+"')\" ><input type='hidden' class='txt_input' name='ltNo_"+chk_nums+"'  value=\""+rs.getString(1)+"\"><DIV class=div_input><u>"+rs.getString(1)+"</u></DIV></td>";
					m_string=m_string+"<td width='15%' style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(2)+"')\" ><DIV class=div_input><u>"+rs.getString(2)+"</u></DIV></td>";
					m_string=m_string+"<td width='10%' style= cursor:hand; onClick=\"show_client('"+rs.getString(3)+"')\" ><DIV class=div_input><u>"+rs.getString(3)+"</u></DIV></td>";   
					m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>";
					
					m_string=m_string+"<td width='10%' align='center'><select class=txt_input type=text name=TXT_APPROVE_TYPE_"+chk_nums+" maxlength=1 size=1  onChange=\"\"  >";  
					m_string=m_string+"<option value=\"A\" selected>Approve</option>";
					m_string=m_string+"<option value=\"D\"  >Disapprove</option>";
					m_string=m_string+"</select></td>";
					
					m_string=m_string+"<td width='15%' ><input type='text' class='txt_input' maxlength=200 style='width:150px' name='comment_"+chk_nums+"' value=\"-\" ></td>";
					
					m_string=m_string+"<td width='6%' align='center' ><input type='checkbox' name='received_"+chk_nums+"' onclick=\"\" ></td>"; 
					
					//m_string=m_string+"<td width='10%' style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_"+chk_nums+"\" id=\"BUT_DETAILS_"+chk_nums+"\" value=\"Details\" onclick=\"show_details('" + chk_nums + "');\" /></td>";
					
					m_string=m_string+"</tr>";
					
					
					
					
				} 

				
				rs.close();
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				out.println(m_string);
				
				
			}
			else if (m_chksql.trim().equals("LEGAL_INV_DET_APP_DET")){
				
				String m_term_no = req.getParameter("terminationNo");
				String m_string        = "";
				
				rs= stmt.executeQuery(""+
					" SELECT "+
					" 	A.INVOICE_NO, "+
					" 	A.TOTAL_AMOUNT , "+ 
					" 	A.SETTELE_AMOUNT, "+
					" 	A.BALANCE_AMOUNT, "+
					" 	A.WAVE_OF_AMOUNT, "+
					" 	"+m_schema_name+".AF_CO_GET_INVOICE_DESCR(A.INVOICE_TYPE)  "+  
					" FROM "+m_schema_name+".AF_LT_FIN_INVOICE_DET A "+
					" WHERE "+
					" A.APP_STATUS = 'N' "+
					" AND A.TERMINATION_NO = '"+m_term_no+"' ");
				
				
				
				
				m_string=m_string+"<table align='left' width='800px' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' align='center' ><DIV class=div_input><b>Invoice Number</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Total Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Settle Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%' align='center' ><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='10%'  align='center' ><DIV class=div_input><b>Wave off Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%'  align='center' ><DIV class=div_input><b>Description</b></DIV></td>";
				
				
				m_string=m_string+"</tr>";
				
				
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width='15%' ><DIV class=div_input>"+rs.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(2))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(3))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(4))+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='right'><DIV class=div_input>"+nf.format(rs.getDouble(5))+"</DIV></td>";
					m_string=m_string+"<td width='15%' ><DIV class=div_input>"+rs.getString(6)+"</DIV></td>";
					
					m_string=m_string+"</tr>";
					
					
					
					
				} 
				
				
				
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				out.println(m_string);
				
				
			}
			
			else {
				out.println("Undefined");
			}
			
			//out.close();
			conn.close();
			this.destroy();
			
			
		}
		catch (Exception e) {
			try {
				conn.close();
				out.println("Error:"+e.toString());
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			//ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();
			
		}
	}
}

