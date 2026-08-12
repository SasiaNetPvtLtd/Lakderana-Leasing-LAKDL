import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:19-10-2007

public class LAKDL_FA_OP_Charges_addition_approval_sql extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_DETAILS")){
				String m_string="";				
			
				     rs1= stmt1.executeQuery(
				    " SELECT A.CHARGES_REF_NO, A.CLIENT_CODE, A.FACILITY_NO, A.FEE_CODE,  "+
						" A.FEE_DESC, A.DRCR_STATUS, TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), A.FEE_CHARGE_AMOUNT,  "+
						" A.FEE_COMMENT, A.ENT_USER, A.ENT_DATE "+
						" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARG_ADD A "+
						" WHERE ACTIVE_STATUS='ENT' ");
						
			
     boolean more2=rs1.next();
			if(more2){
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='9%' class=div_input><b>Charge ref</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Client Code</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Facility No</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Fee Code</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Fee Desc</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Fee Eff Date</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Fee Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Comments</b></td>";
				m_string=m_string+"<td width='9%' class=div_input><b>Select</td>";
				m_string=m_string+"</tr>";
			}
				int chk_nums=0;
				int j=1;
				while(more2){
					//chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width=\"10%\" class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CHARGE_REF_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(1)+"</td>"+
					"<td width=\"10%\" class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>"+
					"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FEE_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(4)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(5)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(7)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+nf.format(rs1.getDouble(8))+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(9)+"</td>"+					
					"<td width='5%' class=div_input><select name='TXT_SETT_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"0\">No-Action</option><OPTION value=\"A\">Approve</option><option value=\"D\">Dispprove</option></SELECT></td>"+ //---ADDED BY ASHINI ON 22-02-2008-
					"</tr>";
		    	more2=rs1.next();
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			// Added by Udara Somathilake on 31-03-2010
			else if(m_chksql.equals("LOAD_DETAILS_CHARGE_REVERSAL")){
				String m_string="";				
			
				     rs1= stmt1.executeQuery(
				    " SELECT A.REVERSE_CHARGE_REF_NO, A.CLIENT_CODE, A.FACILITY_NO, A.FEE_CODE,  "+
						" A.FEE_DESC, A.DRCR_STATUS, TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'), A.FEE_CHARGE_AMOUNT,  "+
						" NVL(A.FEE_COMMENT,'-'), A.ENT_USER, A.ENT_DATE "+
						" FROM "+m_schema_name+".FA_OP_PRO_CHARGES_REVERSE A "+
						" WHERE ACTIVE_STATUS='ENT' ");
						
			
		     boolean more2=rs1.next();
					if(more2){
						m_string=m_string+"<table align='center' width='100%' class='table' >";
						m_string=m_string+"<tr class=pdn_txtpos2>"; 
						m_string=m_string+"<td width='9%' class=div_input><b>Charge ref</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Client Code</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Facility No</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Fee Code</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Fee Desc</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Fee Eff Date</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Fee Amount</b></td>";
						m_string=m_string+"<td width='10%' class=div_input><b>Comments</b></td>";
						m_string=m_string+"<td width='9%' class=div_input><b>Select</td>";
						m_string=m_string+"</tr>";
					}
				int chk_nums=0;
				int j=1;
				while(more2){
					//chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					
					m_string=m_string+"<td width=\"10%\" class=div_input ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CHARGE_REF_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\">"+rs1.getString(1)+"</td>"+
					"<td width=\"10%\" class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>"+
					"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(3)+"</td>"+					
					//"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FEE_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\">"+rs1.getString(4)+"</td>"+	
					"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FEE_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(3)+"\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EFF_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\">"+rs1.getString(4)+"</td>"+
					//"<td width=\"9%\" class=div_input   style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_EFF_DATE_"+chk_nums+"' VALUE=\""+rs1.getString(7)+"\">"+rs1.getString(4)+"</td>"+
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(5)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(7)+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+nf.format(rs1.getDouble(8))+"</td>"+					
					"<td width=\"9%\" class=div_input   style='cursor:hand'>"+rs1.getString(9)+"</td>"+					
					"<td width='5%' class=div_input><select name='TXT_SETT_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"0\">No-Action</option><OPTION value=\"A\">Approve</option><option value=\"D\">Dispprove</option></SELECT></td>"+ //---ADDED BY ASHINI ON 22-02-2008-
					"</tr>";
		    	more2=rs1.next();
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			
			// End by Udara Somathilake on 31-03-2010
			
			
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

