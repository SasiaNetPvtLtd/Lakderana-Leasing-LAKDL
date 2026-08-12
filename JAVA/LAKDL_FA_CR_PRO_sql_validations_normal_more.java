import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:14-03-2008
   
public class LAKDL_FA_CR_PRO_sql_validations_normal_more extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
    
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

			else if(m_chksql.equals("LOAD_CLIENTS_FACILITY_UPDATION")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT FACILITY_NO, "+//1
					" CLIENT_CODE,"+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),"+//4
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+//5
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+//6
					" CREDIT_LIMIT,"+//7
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),"+//8
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY'), "+//9
					" ROUND(NVL("+m_schema_name+".FA_CLIENT_AV_LOAN_BAL(CLIENT_CODE,FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0)), "+//10
					" FACILITY_STATUS, "+//11
					" (FACILITY_END_DATE - TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')) "+ //12
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='Y' AND "+
					" ((FACILITY_END_DATE - TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')< 0) OR "+ 
					" (FACILITY_END_DATE - TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<= 30)) "+
					" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) ");
					
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2><td width='10%' ><DIV class=div_input><b>Approved Facilities</b></DIV></td></TR></table>";
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='10%'><DIV class=div_input><b>Status</b></DIV></td>"; 
				m_string=m_string+"<td width='12%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					m_string=m_string+"<td width='10%' class=div_input   onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input   style='cursor:hand' onClick=\"show_client('"+rs1.getString(2)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(11)+"</td>";
					if(rs1.getDouble(10)>0){
					m_string=m_string+"<td width='12%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"U\">Update Facility</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='25%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					}
					else{
					m_string=m_string+"<td width='12%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"T\">Termination</option><OPTION value=\"U\">Update Facility</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='25%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					}
					m_string=m_string+"<td width='*%'></td>";
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

