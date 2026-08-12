import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
    
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006
  
public class LAKDL_FA_MK_PRO_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
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
		  else if(m_chksql.equals("LOAD_QUOTATION_APPROVAL")){
				
				String m_string="";				
				
					rs= stmt.executeQuery(" SELECT QUOTATION_NO, "+//1
							" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE), "+//2
							" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+//3
							" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//4
							" CLIENT_CODE,  "+//5
							" NVL(INQUERY_NO,'-'), "+//6
							" NVL(CREDIT_LIMIT,0), "+//7
							" NVL(CREDIT_PERIOD,0), "+//8
							" NVL(TOLERANCE_CREDIT_PERIOD,0), "+//9
							" NVL(RESERVE_MARGIN,0), "+//10
							" FEE_PACK_CODE "+//11
							" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+
							" WHERE QUOTATION_STATUS='N' "+
							" ORDER BY ENT_DATE ");
				
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Quotation No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Product Package</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Fee Package</b></DIV></td>";  
				m_string=m_string+"<td width='10%' align=right ><DIV class=div_input><b>Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=right ><DIV class=div_input><b>Credit Period</b></DIV></td>"; 
				m_string=m_string+"<td width='10%'  ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_quotation('"+rs.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_QUOTATION_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><u>"+rs.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs.getString(5)+"')\"><u>"+rs.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs.getString(2)+"</td>"; 
					m_string=m_string+"<td width='10%' class=div_input>"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='10%' align=right class=div_input>"+nf.format(rs.getDouble(7))+"</td>";
					m_string=m_string+"<td width='10%' align=right class=div_input>"+nf.format(rs.getDouble(8))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					//m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter('"+rs.getString(1)+"')\" name=BUT_LETTER value=\"Letter\" ></td>"; 
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_INITIAL_CREDIT_APPROVE_LIST")){
				
				String m_string="";				
				
					rs= stmt.executeQuery(" SELECT A.CLIENT_CODE, "+
					  " DECODE(A.CLIENT_TYPE,'C','CORPORATE','INDIVIDUAL'), "+
					  " DECODE(A.FACTORING_TYPE,'C','CLIENT','DEBTOR'), "+
					  " NVL(A.FULL_NAME,'-'), "+
					  " NVL(A.BUSINESS_SUB_SECTOR,'-'), "+
					  " NVL(A.REGISTERED_TEL_NO,'-'), "+
					  " NVL(A.REGISTERED_MOBILE_NO,'-'), "+
					  " NVL(A.REGISTERED_CONTACT_PERSON,'-') "+
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					  " WHERE A.ACTIVE_STATUS='E' "+
						" ORDER BY A.CLIENT_CODE ");
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Type</b></DIV></td>";
				m_string=m_string+"<td width='18%' ><DIV class=div_input><b>Factoring Relationship</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Full Name</b></DIV></td>"; 
				m_string=m_string+"<td width='12%'  ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Comment</b></DIV></td>";
				m_string=m_string+"</tr>";
				int chk_nums=0;
				int j=1;
				while(rs.next()){
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width='15%' class=div_input  style='cursor:hand'  onClick=\"show_client('"+rs.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\"><u>"+rs.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='18%' class=div_input>"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='20%' class=div_input>"+rs.getString(4)+"</td>"; 
					m_string=m_string+"<td width='12%' class=div_input><select name='TXT_CLIENT_APPROVE_TYPE_"+chk_nums+"' style='width:80'  class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='TEXT' class='txt_input'  NAME='TXT_CLIENT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style='width:200' maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("FEE_PACKAGES")){
				
				String m_fee_pack_id=req.getParameter("fee_pack_id");
				
				String m_string="";				
				
				rs= stmt.executeQuery (" SELECT A.FEE_CODE,A.FEE_DESC,DECODE(A.FEE_TYPE,'C','Charges','P','Primary charges','Other'),"+
					" NVL(B.PACK_DESC,'-'),NVL(A.MINIUM_VALUE,0),NVL(B.MINIUM_VALUE,0),'Y', "+
					" DECODE(A.CAL_BASIS,'F','Flat','Percentage'),C.BASIS_DESC,D.ACTIVATION_DESC,A.TAX_APPLICABILITY "+
					" FROM "+m_schema_name+".FA_CO_MAS_FEES A,"+m_schema_name+".FA_CO_MAS_FEE_PACK_DET B,"+m_schema_name+".FA_CO_MAS_FEES_ACT_POINT D,"+m_schema_name+".FA_CO_MAS_FEES_CAL_BASIS C "+
					" WHERE  A.ACTIVE_STATUS='Y' AND A.FEE_CODE=B.FEE_CODE "+
					" AND A.FEE_RATIO=C.BASIS_CODE AND A.ACTIVATION_POINT=D.ACTIVATION_POINT_CODE "+
					" AND B.FEE_PACK_CODE='"+m_fee_pack_id+"'");
				int chk_nums=0;
				
				m_string=m_string+"<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Fee Code</DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Fee Desc.</DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Fee Type</DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Minimum Value</DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Quotation Value</DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comment</DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input>Calculation Basis</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Calculation</DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input>Activation Point</DIV></td>"; 
				m_string=m_string+"<td width='5%' ><DIV class=div_input>TAX Applicability</DIV></td>"; 
				m_string=m_string+"<td width='9%' ></td>"; 
				m_string=m_string+"</tr>";
				
				int j=1;
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
					m_string=m_string+"<td width='10%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FEE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" disabled>"+rs.getString(1)+"</td>";
					m_string=m_string+"<td width='20%' >"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' >"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='10%' style='text-align:right'>"+nf.format(rs.getDouble(6))+"</td>"; 
					m_string=m_string+"<td width='10%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_FEE_CHARGE_"+chk_nums+"' MAXLENGTH = '21' VALUE=\""+nf.format(rs.getDouble(6))+"\" onchange='format_number(document.Form1.TXT_FEE_CHARGE_"+chk_nums+",21)'   style='text-align:right'></td>"; // Modified by Thamali Jayatunga on 2009.10.20
					m_string=m_string+"<td width='10%' >"+rs.getString(4)+"</td>"; 
					m_string=m_string+"<td width='10%' >"+rs.getString(8)+"</td>"; 
					m_string=m_string+"<td width='20%' >"+rs.getString(9)+"</td>"; 
					m_string=m_string+"<td width='20%' >"+rs.getString(10)+"</td>"; 
					m_string=m_string+"<td width='5%' >"+rs.getString(11)+"</td>"; 
					m_string=m_string+"<td width='9%' ><INPUT TYPE='CHECKBOX' NAME='CHK_FEE_"+chk_nums+"' checked></td>"; 
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_FEE_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("PRODUCT_PACKAGES")){
				
				String m_product_id=req.getParameter("product_id");
				
				String m_string="";				
				
				rs= stmt.executeQuery (" SELECT A.FA_FEATURE_CODE,A.FA_FEATURE_DESC,NVL(A.FA_FEATURE_COMMENTS,'-'),NVL(B.FA_PROD_COMMENT,'-'),NVL(B.PARAMETER_VALUE,'-') "+
						" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES A,"+m_schema_name+".FA_CO_MAS_PRODUCT_PACK B "+
						" WHERE  A.ACTIVE_STATUS='Y' AND  A.FA_FEATURE_CODE=B.FA_FEATURE_CODE AND B.FA_PRODUCT_CODE='"+m_product_id+"'");
				
				int chk_nums=0;
				int j=1;
				
				m_string="<table align='center' width='100%' class='table'>";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Product Code</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Description</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Parameter Value</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Quotation Parameter Value</b></DIV></td>"; 
				m_string=m_string+"<td width='9%' ></td>"; 
				m_string=m_string+"</tr>";
				
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
					m_string=m_string+"<td width='10%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_PRODUCT_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" DISABLED></td>";
					m_string=m_string+"<td width='20%' >"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' >"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='15%' >"+rs.getString(4)+"</td>"; 
					m_string=m_string+"<td width='10%' >"+rs.getString(5)+"</td>"; 
					m_string=m_string+"<td width='10%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_PARAM_VALUE_"+chk_nums+"' VALUE=\""+rs.getString(5)+"\"></td>";
					m_string=m_string+"<td width='9%' ><INPUT TYPE='CHECKBOX' NAME='CHK_PRODUCT_"+chk_nums+"' checked></td>"; 
					m_string=m_string+"</tr>";
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_PRODUCT_CHKS' VALUE="+chk_nums+">";
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

