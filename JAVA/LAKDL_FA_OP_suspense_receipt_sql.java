import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_OP_suspense_receipt_sql extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
  public ResultSet rs1,rs4,rs5,rs6;
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
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
						else if(m_chksql.equals("LOAD_SETTLEMENT_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE||'-'||CHEQUE_NO,SETTLE_MODE),"+
					" BALANCE_AMOUNT,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+
					" RECEIPT_TYPE, "+
					" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) "+
					"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE SUSPENDED_ALLOCATION='Y' "+
					" ORDER BY EFF_VALDATE,RECEIPT_NO ");
								
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Receipt No</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Settlement Mode/Chq. no</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=\"right\" ><DIV class=div_input><b>Balance Amount</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Settlement Date</b></DIV></td>"; 
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
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
					m_string=m_string+"<td width='20%' class=div_input  onClick=\"show_receipt_details('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_RECEIPT_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  >"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=\"right\">"+nf.format(rs1.getDouble(3))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
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

