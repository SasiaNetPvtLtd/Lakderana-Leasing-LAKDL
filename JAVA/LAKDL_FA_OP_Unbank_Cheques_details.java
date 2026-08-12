import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
          
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE: 29-02-2008
  
public class LAKDL_FA_OP_Unbank_Cheques_details extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_UNBANK_CHEQUES")){
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			String m_facility_code=req.getParameter("FACILITY_NO");
     
			String m_string="";				
			
		
				/*rs1 = stmt1.executeQuery(" SELECT RECEIPT_NO,"+//1
					" PAYER_BRANCH_CODE,"+//2
					" CHEQUE_NO, "+//3
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//4
					" NVL(REC_AMOUNT,0) REC_AMOUNT, "+//5
					" NVL(DEBTOR_CODE,'-') DEBTOR_CODE "+//6
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_NO ='"+m_facility_code+"' "+
					" AND REBANK_STATUS ='C' AND PRINT_STATUS='N' "+
					//" ORDER BY RECEIPT_NO ");
					
					" UNION ALL  "+
					
					" SELECT  "+
					" A.RECEIPT_NO RECEIPT_NO, "+
					" A.PAYER_BRANCH_CODE PAYER_BRANCH_CODE, "+
					" A.CHEQUE_NO CHEQUE_NO, "+
					" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+
					" A.REC_AMOUNT REC_AMOUNT "+
					" ,NVL(DEBTOR_CODE,'-') DEBTOR_CODE "+//6
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				  " WHERE A.REC_STATUS='C' "+
					" AND CLIENT_CODE='"+m_client_code+"' "+
					" AND FACILITY_NO ='"+m_facility_code+"' "+
					" AND PRINT_STATUS='N'  ");
					*/
					
					//edit by malik on 17/2/2009
					
					/*rs1 = stmt1.executeQuery(" SELECT RECEIPT_NO,"+//1
					" PAYER_BRANCH_CODE,"+//2
					" CHEQUE_NO, "+//3
					" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),"+//4
					" NVL(REC_AMOUNT,0) REC_AMOUNT, "+//5
					" NVL(DEBTOR_CODE,'-') DEBTOR_CODE "+//6
					" ,CLIENT_CODE CLIENT_CODE "+//7
					" ,FACILITY_NO FACILITY_NO"+//8
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE CLIENT_CODE LIKE '"+m_client_code+"%' "+
					" AND FACILITY_NO LIKE '"+m_facility_code+"%' "+
					" AND REBANK_STATUS ='C' AND PRINT_STATUS='N' "+
										
					" UNION ALL  "+
					*/
					
					rs1 = stmt1.executeQuery(
					" SELECT  "+
					" A.RECEIPT_NO RECEIPT_NO, "+
					" A.PAYER_BRANCH_CODE PAYER_BRANCH_CODE, "+
					" A.CHEQUE_NO CHEQUE_NO, "+
					" TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY') CHEQUE_DATE, "+
					" A.REC_AMOUNT REC_AMOUNT "+
					" ,NVL(DEBTOR_CODE,'-') DEBTOR_CODE "+//6
					" ,A.CLIENT_CODE CLIENT_CODE "+//7
					" ,A.FACILITY_NO FACILITY_NO"+//8
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A "+
				  " WHERE A.REC_STATUS='C' "+
					" AND   A.RECON_STATUS IS NOT NULL "+
					" AND   CLIENT_CODE LIKE '"+m_client_code+"%' "+
					" AND   FACILITY_NO LIKE '"+m_facility_code+"%' "+
					" AND   PRINT_STATUS='N'  ");
					
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class='div_input'><b>Client Code</b></td>";//add by malik on 17/2/2009
				m_string=m_string+"<td width='10%' class='div_input'><b>Finanace No</b></td>";//add by malik on 17/2/2009
				m_string=m_string+"<td width='15%' class='div_input'><b>Receipt No</b></td>";
				m_string=m_string+"<td width='15%' class='div_input'><b>Branch Code</b></td>";
				m_string=m_string+"<td width='15%' class='div_input'><b>Cheque No</b></td>";
				m_string=m_string+"<td width='15%' class='div_input'><b>Cheque Date</b></td>";
				m_string=m_string+"<td width='15%' class='div_input' align=right><b>Cheque Amount</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Status</b></td>";

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
					m_string=m_string+"<td width='10%' class='div_input'>"+rs1.getString(7)+"</td>"+//add by malik on 16/2/2009
				  "<td width='10%' class='div_input'>"+rs1.getString(8)+"</td>"+//add by malik on 16/2/2009
					"<td width=\"15%\" onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_REC_NO_"+chk_nums+"\" value=\""+rs1.getString(1)+"\"><U>"+rs1.getString(1)+"</U></td>"+
					"<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DEBTOR_CODE_"+chk_nums+"\" value=\""+rs1.getString(6)+"\">"+rs1.getString(2)+"</td>"+
					"<td width=\"15%\" class=div_input>"+rs1.getString(3)+"</td>"+
					"<td width=\"15%\" class=div_input>"+rs1.getString(4)+"</td>"+
					"<td width=\"15%\" class=div_input align='right' >"+nf.format(rs1.getDouble(5))+"</td>"+
					"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"CANCEL_CHK_"+chk_nums+"\" onclick='check_unbank_cheques("+chk_nums+")'></td>"+
					"<input class=\"txt_input\" type=\"hidden\" name=\"HID_CLIENT_CODE_"+chk_nums+"\" VALUE=\""+rs1.getString(7)+"\" >"+//ADD by malik on 17/2/2009
					"<input type=\"hidden\" name=\"HID_FACILTY_NO_"+chk_nums+"\" VALUE=\""+rs1.getString(8)+"\" >"+//ADD by malik on 17/2/2009
					"</tr>";
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);			
			
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

