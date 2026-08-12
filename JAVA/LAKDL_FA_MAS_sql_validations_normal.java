import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
  
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MAS_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("PRODUCT_PACKAGES")){
				
				String m_product_id=req.getParameter("product_id");
				
				String m_string="";				
				
				if(!m_product_id.equals("")){
					rs= stmt.executeQuery (" SELECT A.FA_FEATURE_CODE,A.FA_FEATURE_DESC,NVL(A.FA_FEATURE_COMMENTS,'-'),NVL(B.FA_PROD_COMMENT,'-'),'Y',NVL(B.PARAMETER_VALUE,'-') "+
						" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES A,"+m_schema_name+".FA_CO_MAS_PRODUCT_PACK B "+
						" WHERE A.FA_FEATURE_CODE=B.FA_FEATURE_CODE AND B.FA_PRODUCT_CODE='"+m_product_id+"'"+
						" UNION "+
						" SELECT A.FA_FEATURE_CODE,A.FA_FEATURE_DESC,NVL(A.FA_FEATURE_COMMENTS,'-'),'-','N','-' "+
						" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES A "+
						" WHERE A.ACTIVE_STATUS='Y' AND A.FA_FEATURE_CODE NOT IN(SELECT FA_FEATURE_CODE  FROM "+m_schema_name+".FA_CO_MAS_PRODUCT_PACK WHERE FA_PRODUCT_CODE='"+m_product_id+"') ");
				}
				else{
					rs= stmt.executeQuery (" SELECT A.FA_FEATURE_CODE,A.FA_FEATURE_DESC,NVL(A.FA_FEATURE_COMMENTS,'-'),'-','N','-' "+
						" FROM "+m_schema_name+".FA_CO_MAS_PROD_FEATURES A "+
						" WHERE A.ACTIVE_STATUS='Y' ORDER BY A.FA_FEATURE_CODE ");
				}
				
				m_string="<table align='center' width='100%' class='table'>";
				int chk_nums=0;
				
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%'></td>"; 
				m_string=m_string+"<td width='15%' class='div_input'>Feature Code</td>";
				m_string=m_string+"<td width='15%' class='div_input'>Description</td>";
				m_string=m_string+"<td width='15%' class='div_input'>Comments</td>"; 
				m_string=m_string+"<td width='15%' class='div_input'>Additional Comments</td>"; 
				m_string=m_string+"<td width='15%' class='div_input'>Parameter Value</td>"; 
				m_string=m_string+"<td width='9%' class='div_input'>Applicability</td>"; 
				m_string=m_string+"<td width='*%'></td>";
				m_string=m_string+"</tr>";
				
				int j=0;
				
				while(rs.next()){
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEATURE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" DISABLED width=\"50\">"+rs.getString(1)+"</td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEATURE_DESC_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" DISABLED width=\"100\">"+rs.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='HIDDEN' class='txt_input' NAME='FEATURE_COMM_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\" DISABLED width=\"100\">"+rs.getString(3)+"</td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='FEATURE_COMM_N_"+chk_nums+"' VALUE=\""+rs.getString(4)+"\"  maxlength=\"100\"></td>"; 
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='FEATURE_PARAM_VALUE_"+chk_nums+"' VALUE=\""+rs.getString(6)+"\" maxlength=\"10\" ></td>"; 
					if(rs.getString(5).equals("Y")){
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
			else if(m_chksql.equals("COLLECTION_ROUTE")){
				
				String m_route_id=req.getParameter("route_id");
				String m_screen_id=req.getParameter("screen_id");
				
				String m_string="";				
				
				int chk_nums=0;

				if(!m_route_id.equals("")){
				
					rs= stmt.executeQuery(" SELECT A.AREA_CODE,B.AREA_DESC,NVL(A.COLL_COMMENT,'-'),NVL(A.COLL_ORDER,1) COLL_ORDER"+
						" FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES_DET A,"+m_schema_name+".AF_CO_MAS_AREA B "+
						" WHERE A.AREA_CODE=B.AREA_CODE AND A.COLL_ROUTE_CODE='"+m_route_id+"'"+
						" ORDER BY A.COLL_ORDER " );
					
					int j=0;
					
						while(rs.next()){
							chk_nums++;
							if(m_screen_id.equals("EDIT")){
								m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" maxlength=\"10\"><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_area_code("+chk_nums+")\"></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_NAME_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" maxlength=\"50\" disabled></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\" maxlength=\"50\"></td>"; 
								m_string=m_string+"<td width='13%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ORDER_"+chk_nums+"' VALUE=\""+rs.getString(4)+"\" maxlength=\"10\"></td>"; 
								m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_area_details("+chk_nums+")\"></td>";
								m_string=m_string+"</tr>";
							}
							else{
								m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" maxlength=\"10\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_area_code("+chk_nums+")\" disabled></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_NAME_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" maxlength=\"50\" disabled></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_COMMENT_"+chk_nums+"' VALUE=\""+rs.getString(3)+"\" maxlength=\"50\" disabled></td>"; 
								m_string=m_string+"<td width='13%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ORDER_"+chk_nums+"' VALUE=\""+rs.getString(4)+"\" maxlength=\"10\" disabled></td>"; 
								m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_area_details("+chk_nums+")\" disabled></td>";
								m_string=m_string+"</tr>";
							}
						}
					}
					if(m_string.equals("")){
					chk_nums=1;
					m_string=m_string+"<table width=\"100%\">";
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_CODE_"+chk_nums+"' VALUE=\"\" maxlength=\"10\"><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_area_code("+chk_nums+")\"></td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_AREA_NAME_"+chk_nums+"' VALUE=\"\" maxlength=\"50\" disabled></td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"50\"></td>"; 
					m_string=m_string+"<td width='13%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ORDER_"+chk_nums+"' VALUE=\"\" maxlength=\"10\"></td>"; 
					m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_area_details("+chk_nums+")\"></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					}
					else{
					m_string="<table width=\"100%\">"+m_string;
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					}
				
					out.println(m_string);
			}
			else if(m_chksql.equals("COLLECTION_ROUTE_ASSIGN")){
				
				String m_route_officer_id=req.getParameter("route_officer_id");
				String m_screen_id=req.getParameter("screen_id");
				
				String m_string="";				
				
				int chk_nums=0;

				if(!m_route_officer_id.equals("")){
						
					rs= stmt.executeQuery("	SELECT A.COLL_ROUTE_CODE,A.COLL_ROUTE_DESC "+
 					 "	FROM "+m_schema_name+".AF_CO_MAS_COLL_ROUTES A,"+m_schema_name+".AF_CO_MAS_COLL_ROUT_ASSIGN B,LAKDL.CO_CO_MAS_EMPLOYEE C "+
 					 "  WHERE A.COLL_ROUTE_CODE=B.COLL_ROUTE_CODE "+
 					 "	AND   B.EMP_CODE = C.EMP_CODE AND B.EMP_CODE ='"+m_route_officer_id+"' "); 
						
					int j=0;
						while(rs.next()){
							chk_nums++;
							if(m_screen_id.equals("EDIT")){
								m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" maxlength=\"10\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code("+chk_nums+")\"></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_DESC_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" maxlength=\"50\" disabled></td>";
								m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details("+chk_nums+")\"></td>";
								m_string=m_string+"</tr>";
							}
							else if(m_screen_id.equals("RACT") || m_screen_id.equals("DACT") ){
								m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" maxlength=\"10\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code("+chk_nums+")\" disabled ></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_DESC_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" maxlength=\"50\" disabled></td>";
								m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details("+chk_nums+")\" disabled ></td>";
								m_string=m_string+"</tr>";
							}
							else{
								m_string=m_string+"<td width='1%'></td>"; 
								m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_CODE_"+chk_nums+"' VALUE=\""+rs.getString(1)+"\" maxlength=\"10\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code("+chk_nums+")\" disabled></td>";
								m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_DESC_"+chk_nums+"' VALUE=\""+rs.getString(2)+"\" maxlength=\"50\" disabled></td>";
								m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details("+chk_nums+")\" ></td>";
								m_string=m_string+"</tr>";
							}
						}
					}
					if(m_string.equals("")){
					chk_nums=1;
					m_string=m_string+"<table width=\"100%\">";
					m_string=m_string+"<tr >";
					m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='20%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_CODE_"+chk_nums+"' VALUE=\"\" maxlength=\"10\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_BANK_ACC_HELP\" value=\"?\" onClick=\"update_route_code("+chk_nums+")\"></td>";
					m_string=m_string+"<td width='15%' ><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_ROUTE_DESC_"+chk_nums+"' VALUE=\"\" maxlength=\"50\" disabled></td>";
					m_string=m_string+"<td width='*%'><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_ACC_MAIN\" value=\"Delete\" onClick=\"delete_route_details("+chk_nums+")\"></td>";
					m_string=m_string+"</tr>";
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					}
					else{
					m_string="<table width=\"100%\">"+m_string;
					m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
					m_string=m_string+"</table>";
					}
				
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

