import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;

// DEVELOP BY : DISNAKA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MIS_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt3;
	CallableStatement callstmt;
	java.text.NumberFormat nf;
	
	public ResultSet rs,rs1,rs3;
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
			stmt3=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			else if(m_chksql.equals("FEE_PACKAGES")){
				
				String m_fee_pack_id=req.getParameter("fee_pack_id");
				
				String m_string="";				
				
				if(!m_fee_pack_id.equals("")){
					rs= stmt.executeQuery (" SELECT A.FEE_CODE,A.FEE_DESC,DECODE(A.FEE_TYPE,'C','CHARGES','P','PRIMARY CHARGES','OTHER'),NVL(B.PACK_DESC,'-'),NVL(A.MINIUM_VALUE,0),NVL(B.MINIUM_VALUE,0),'Y',DECODE(A.CAL_BASIS,'F','Flat','Percentage'),C.BASIS_DESC,D.ACTIVATION_DESC,A.TAX_APPLICABILITY "+
						" FROM "+m_schema_name+".FA_CO_MAS_FEES A,"+m_schema_name+".FA_CO_MAS_FEE_PACK_DET B,"+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT D,"+m_schema_name+".FA_CO_MAS_FEES_CAL_BASIS C "+
						" WHERE A.FEE_CODE=B.FEE_CODE AND B.FEE_PACK_CODE='"+m_fee_pack_id+"'"+
						" AND A.FEE_RATIO=C.BASIS_CODE AND A.ACTIVATION_POINT=D.ACTIVATION_POINT_CODE "+
						" UNION "+
						" SELECT A.FEE_CODE,A.FEE_DESC,DECODE(A.FEE_TYPE,'C','CHARGES','P','PRIMARY CHARGES','OTHER'),'-',NVL(A.MINIUM_VALUE,0),NVL(A.MINIUM_VALUE,0),'N',DECODE(A.CAL_BASIS,'F','Flat','Percentage'),C.BASIS_DESC,B.ACTIVATION_DESC,A.TAX_APPLICABILITY "+
						" FROM "+m_schema_name+".FA_CO_MAS_FEES A,"+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT B,"+m_schema_name+".FA_CO_MAS_FEES_CAL_BASIS C "+
						" WHERE A.ACTIVE_STATUS='Y' AND A.FEE_CODE NOT IN(SELECT FEE_CODE  FROM "+m_schema_name+".FA_CO_MAS_FEE_PACK_DET WHERE FEE_PACK_CODE='"+m_fee_pack_id+"') "+
						" AND A.FEE_RATIO=C.BASIS_CODE AND A.ACTIVATION_POINT=B.ACTIVATION_POINT_CODE ");
				}
				else{
					rs= stmt.executeQuery (" SELECT A.FEE_CODE,A.FEE_DESC,DECODE(A.FEE_TYPE,'C','CHARGES','P','PRIMARY CHARGES','OTHER'),'-',NVL(A.MINIUM_VALUE,0),NVL(A.MINIUM_VALUE,0),'N',DECODE(A.CAL_BASIS,'F','Flat','Percentage'),C.BASIS_DESC,B.ACTIVATION_DESC,A.TAX_APPLICABILITY "+
						" FROM "+m_schema_name+".FA_CO_MAS_FEES A,"+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT B,"+m_schema_name+".FA_CO_MAS_FEES_CAL_BASIS C "+
						" WHERE A.ACTIVE_STATUS='Y' "+
						" AND A.FEE_RATIO=C.BASIS_CODE AND A.ACTIVATION_POINT=B.ACTIVATION_POINT_CODE "+
						" ORDER BY A.FEE_CODE");
				}
				
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Fee Code</DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Fee Description</DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Fee Type</DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Minimum Fee</DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Minimum Value for Pack.</DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Comment</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Calculation Basis</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Calculation</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Activation Point</DIV></td>"; 
				m_string=m_string+"<td width='5%' ><DIV class=div_input>TAX Applicability</DIV></td>"; 
				m_string=m_string+"<td width='9%' >Applicability</td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=0;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" DISABLED width=\"50\">"+rs.getString(1)+"</td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEE_DESC_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" DISABLED>"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEE_TYPE_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\" DISABLED>"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='FEE_MIN_VALUE_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(5))+"\" DISABLED  style='text-align:right'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='FEE_MIN_ALLO_"+chk_nums+"' VALUE=\""+nf.format(rs.getDouble(6))+"\" onblur='check_min_value("+chk_nums+")'  style='text-align:right'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='FEE_PACK_DESC_"+chk_nums+"' VALUE=\""+rs.getString(4)+"\" ></td>"; 
					m_string=m_string+"<td width='20%' class='div_input' >"+rs.getString(8)+"</td>"; 
					m_string=m_string+"<td width='20%' class='div_input' >"+rs.getString(9)+"</td>"; 
					m_string=m_string+"<td width='20%' class='div_input' >"+rs.getString(10)+"</td>"; 
					m_string=m_string+"<td width='5%' class='div_input' >"+rs.getString(11)+"</td>"; 
					if(rs.getString(7).equals("Y")){
						m_string=m_string+"<td width='9%' ><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"' checked></td>"; 
					}
					else{
						m_string=m_string+"<td width='9%' ><INPUT TYPE='CHECKBOX' NAME='CHK_"+chk_nums+"'></td>"; 
					}
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			

			else if (m_chksql.trim().equals("CLIENT_STATEMENT")){
			
				
				String m_string        = "";
				
				rs= stmt.executeQuery( " SELECT "+
					" ROWNUM, "+ //1
					" REPORT_ID, "+ //2
					" NVL(REPORT_NAME,'-'), "+ //3
					" FROM "+m_schema_name+".FA_MIS_EMAIL_REPORTS "+
					" WHERE REPORT_CATEGORY = 'CLIENT_STATEMENT' ");
				
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' ></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Report Id</DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input>Report Name</DIV></td>";
				m_string=m_string+"<td width='9%' ><DIV class=div_input>Select</DIV></td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				
				int chk_nums=1;
				int j=0;
				while(rs.next()){
					
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
				    m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='REP_ID_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\">"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' >"+rs.getString(3)+"</td>";
					m_string=m_string+"<td width='9%' ><INPUT TYPE='CHECKBOX' NAME='RECEIVED_"+chk_nums+"'></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
					
				} 
				
				
				m_string=m_string+"</table>";
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
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

