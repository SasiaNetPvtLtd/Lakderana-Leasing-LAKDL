import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;   
      
public class LAKDL_FA_OP_PRO_rpt_detail extends javax.servlet.http.HttpServlet {
	
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
			String header_name=m_sn_methods.header_name.trim(); 
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");

			ServletOutputStream out = res.getOutputStream();

			m_chksql=req.getParameter("chksql");
			stmt1=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CHEQUE_RETURN_AVAILABLE_BAL")){
			
			String m_facility_code=req.getParameter("facility_code");

			rs1= stmt1.executeQuery("SELECT  RECEIPT_NO,"+//1
			" SETTLE_MODE || '-' || NVL(CHEQUE_NO,'') SET_DET,"+//2
			" REC_AMOUNT,"+//3
			" BALANCE_AMOUNT,"+//4
			" "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL_SETT(RECEIPT_NO) BALANCE_AMT,"+//5
			" FACILITY_NO, "+//6
			" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME, "+//7
			" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+//8
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
			" WHERE "+
			//" BALANCE_AMOUNT>0 "+
			" REC_STATUS='Y' "+
			" AND FACILITY_NO='"+m_facility_code+"' "+
			" AND "+m_schema_name+".FA_GET_RETURN_CHEQUE_BAL_SETT(RECEIPT_NO)>0 "+
			" ORDER BY EFF_VALDATE "); 

			out.println("<HTML><HEAD><TITLE>Settlement Receipt Details Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class=tr_input1 STYLE='{ bgcolor='#8fb382' color:black;font:12pt arial;}'>");
			out.println("<TR><TD align='Center'><B>Cheque Return Settlement Available Balances</B></TD></TR>");
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=tr_input1 STYLE='{color:black;font:10pt arial;}'>");
			out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input><b>Settle Details</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>"); 
			out.println("<td width='10%' ><DIV class=div_input><b>Value Date</b></DIV></td>"); 
			out.println("<td width='10%' align='right' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>"); 
			out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Ava. for Chq. Return</b></DIV></td>"); 
			out.println("</tr>"); 
			
			boolean mflag=true;
			
			while(rs1.next()){
				if(mflag){
				out.println("<tr class=tr_input>");
				mflag=false;
				}
				else{
				out.println("<tr class=tr_input1>");
				mflag=true;
				}
				out.println("<td width='10%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>");
				out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
				out.println("<td width='20%' class=div_input >"+rs1.getString(7)+"</td>");
				out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
				out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs1.getDouble(3))+"</td>");
				out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(4))+"</td>");
				out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
				out.println("</tr>");
			}		
			
			out.println("</table>");
			out.println("<br>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
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



