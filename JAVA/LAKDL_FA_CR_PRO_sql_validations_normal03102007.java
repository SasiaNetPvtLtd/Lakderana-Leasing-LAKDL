import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_CR_PRO_sql_validations_normal extends javax.servlet.http.HttpServlet {
	
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
		  else if(m_chksql.equals("LOAD_CLIENTS_APPROVAL")){
				
				String m_factor_type=req.getParameter("factor_type");
				String m_client_code=req.getParameter("client_code");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT CLIENT_CODE,FULL_NAME,CLIENT_TYPE,FACTORING_TYPE,CLIENT_MANAGER,MKT_OFFICER,initcap(REASONS),COLL_OFFICER,COLL_ROUTE "+
					" FROM( "+
					" SELECT A.CLIENT_CODE, DECODE(A.CLIENT_TYPE,'I','Individual','C','Co-operate') CLIENT_TYPE, "+
					" DECODE(A.FACTORING_TYPE,'C','Client','D','Debtor','O','As a Client or Debtor') FACTORING_TYPE,A.FULL_NAME, "+
					" B.CLIENT_MANAGER, B.MKT_OFFICER,'CHANGE STATUS OR FACTORING' REASONS, "+
					" NVL(B.COLL_OFFICER,' ') COLL_OFFICER, "+
					" NVL(B.COLL_ROUTE,' ') COLL_ROUTE "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT B "+
					" WHERE A.CLIENT_CODE=B.CLIENT_CODE "+
					" AND A.ACTIVE_STATUS='I' "+
					" AND A.FACTORING_TYPE='"+m_factor_type+"' "+
					" AND UPPER(A.FULL_NAME) LIKE UPPER('%"+m_client_code+"%') "+
					" UNION "+
					" SELECT A.CLIENT_CODE, DECODE(A.CLIENT_TYPE,'I','Individual','C','Co-operate') CLIENT_TYPE, "+
					" DECODE(A.FACTORING_TYPE,'C','Client','D','Debtor','O','As a Client or Debtor') FACTORING_TYPE,A.FULL_NAME, "+
					" ' ' CLIENT_MANAGER,' ' MKT_OFFICER,'NEW CLIENT/DEBTOR' REASONS, "+
					" ' ' COLL_OFFICER, "+
					" ' ' COLL_ROUTE "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					" WHERE A.ACTIVE_STATUS='I' "+
					" AND A.CLIENT_CODE NOT IN (SELECT CLIENT_CODE  FROM "+m_schema_name+".FA_CR_PRO_CLIENT) "+
					" AND A.FACTORING_TYPE='"+m_factor_type+"' "+
					" AND UPPER(A.FULL_NAME) LIKE UPPER('%"+m_client_code+"%') "+
					" )	"+
					" ORDER BY FULL_NAME ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Code</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Mkt. Executive</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Coll. Officer</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Coll. Route</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Reasons</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><U>"+rs1.getString(1)+"</U></td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_CLIENT_MGR_"+chk_nums+"' VALUE=\""+rs1.getString(5)+"\" maxlength=\"10\" style='width:50'><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Help\" onClick=\"HELP_CLIENT_MGR('"+chk_nums+"')\"></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_CLIENT_MKT_"+chk_nums+"' VALUE=\""+rs1.getString(6)+"\" maxlength=\"10\" style='width:50'><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Help\" onClick=\"HELP_CLIENT_MKT('"+chk_nums+"')\"></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_CLIENT_CMGR_"+chk_nums+"' VALUE=\""+rs1.getString(8)+"\" maxlength=\"10\" style='width:50'><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Help\" onClick=\"HELP_CLIENT_COFF('"+chk_nums+"')\"></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_CLIENT_ROUTE_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\" maxlength=\"10\" style='width:50'><input class=\"but_input\" type=\"button\" name=\"BUT_CLIENT_DETAIL\" value=\"Help\" onClick=\"HELP_CLIENT_ROUTE('"+chk_nums+"')\"></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(7)+"</td>"; 
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_FACILITY_APPROVAL_2")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT FACILITY_NO, "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),"+
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+
					" CREDIT_LIMIT,"+
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),"+
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY')"+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='N'"+
					" ORDER BY FACILITY_NO ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=\"right\" ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=\"right\" >"+nf.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='25%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\"  style=\"width: 170px\" maxlength=\"200\">"; 
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"print_facility_app('"+rs1.getString(1) +"')\" name=\"BUT_LETTER1\" style=\"width:100\" value=\"Approval Sheet\"></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_FACILITY_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT FACILITY_NO, "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),"+
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+
					" CREDIT_LIMIT,"+
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),"+
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY'),"+
					" NVL(APPROVAL_COMMENTS,'-') "+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='A' "+
					" ORDER BY FACILITY_NO ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=\"right\" ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Comments</b></DIV></td>"; 
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=\"right\" >"+nf.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td width='11%' class=div_input>"+rs1.getString(10)+"</td>";
					m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='25%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\"  style=\"width: 170px\" maxlength=\"200\"></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR")){
				
				String m_facility_no=req.getParameter("facility_no");
				
				String m_string="";				
							
					rs1= stmt1.executeQuery(" SELECT "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE FACILITY_NO='"+m_facility_no+"'"+
					" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input>Client Code</DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor credit Limit</b></DIV></td>"; 
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand' onClick=\"show_client('"+rs1.getString(2)+"')\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  align=\"right\" >"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}

			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(CREDIT_LIMIT,0), "+//6
					" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D001'),"+//7
				  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D002'),"+//8
				  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D003'),"+//9
					" "+m_schema_name+".FA_DEBTOR_COLLECT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE) "+//10
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND RELATION_MOVEMENT IN ('INVENTRY','ENTRY') "+
					" ORDER BY FACILITY_NO,DEBTOR_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				//m_string=m_string+"<td width='1%'></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><B>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Debtor Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Company Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Collect by Company</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter Status</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>New Debtor Credit Limit</b></DIV></td>";
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='10%' class=div_input   onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align='center' >"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align='center' >"+rs1.getString(8)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align='center' >"+rs1.getString(9)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align='center' >"+rs1.getString(10)+"</td>";
					if(rs1.getString(9).equals("Y")){
						m_string=m_string+"<td width='10%' class=div_input align='center' ><input type=\"checkbox\" name=\"TXT_STATUS_"+chk_nums+"\" checked disabled></td>";
					} 
					else{
						if(rs1.getString(7).equals("Y") && rs1.getString(8).equals("Y")){
						m_string=m_string+"<td width='10%' class=div_input align='center' ><input type=\"checkbox\" name=\"TXT_STATUS_"+chk_nums+"\" onclick=\"enable_approval_box("+chk_nums+")\"></td>";
						}
						else{
						m_string=m_string+"<td width='10%' class=div_input align='center' ><input type=\"checkbox\" name=\"TXT_STATUS_"+chk_nums+"\" onclick=\"enable_approval_box("+chk_nums+")\" disabled></td>";
						}
					}
					if(rs1.getString(7).equals("Y") && rs1.getString(8).equals("Y")){
						m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_DEBTOR_LIMIT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\" style=\"width:100px\" maxlength=\"20\" style='{text-align:right;}'>"; 
						m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					}
					else{
						m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_DEBTOR_LIMIT_"+chk_nums+"' VALUE=\""+nf.format(rs1.getDouble(6))+"\" style=\"width:100px\" maxlength=\"20\" style='{text-align:right;}' disabled>"; 
						m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\"><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					}
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 150px\" maxlength=\"200\">"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR_CHEQUE_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(PRE_CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND RELATION_MOVEMENT IN ('CHQENTRY') "+
					" ORDER BY FACILITY_NO,DEBTOR_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				//m_string=m_string+"<td width='1%'></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><B>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Previous Debtor Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='11%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='10%' class=div_input   onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='20%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 150px\" maxlength=\"200\">"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR_LETTER_APPROVAL")){
				
				String m_client_code=req.getParameter("client_code");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
					" FACILITY_NO, "+//1
					" CLIENT_CODE, "+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+//3
					" DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//5
					" NVL(CREDIT_LIMIT,0) "+//6
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS='N'"+
					" AND (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE)) LIKE UPPER('%"+m_client_code+"%') OR "+
					" UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) LIKE UPPER('%"+m_client_code+"%'))"+
					" ORDER BY FACILITY_NO,DEBTOR_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				//m_string=m_string+"<td width='1%'></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><B>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' align='right' ><DIV class=div_input><b>Debtor Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
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
					//m_string=m_string+"<td width='1%'></td>"; 
					m_string=m_string+"<td width='10%' class=div_input   onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(4)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(4)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align='right' >"+nf.format(rs1.getDouble(6))+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"; 
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter1('"+rs1.getString(2) +"','"+rs1.getString(4)+"')\" name=BUT_LETTER1 value=\"Letter 1\" >"; 
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter2('"+rs1.getString(2) +"','"+rs1.getString(4)+"')\" name=BUT_LETTER2 value=\"Letter 2\" >";
					m_string=m_string+"<input class=\"but_input\" type=\"button\" onclick=\"Generate_Letter3('"+rs1.getString(2) +"','"+rs1.getString(4)+"')\" name=BUT_LETTER3 value=\"Letter 3\" ></td>";
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_FACILITY_TERMINATION")){
				
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
					" NVL("+m_schema_name+".FA_CLIENT_AV_LOAN_BAL(CLIENT_CODE,FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')),0) "+//10
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='Y'"+
					" ORDER BY "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align='right' ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='1%'></td>"; 
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
					m_string=m_string+"<td width='1%'></td>"; 
					if(rs1.getDouble(7)>0){
					m_string=m_string+"<td width='12%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"N\">Review Facility</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='25%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					}
					else{
					m_string=m_string+"<td width='12%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"T\">Termination</option><OPTION value=\"N\">Review Facility</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
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
			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR_RELATION_TERMINATION")){
					
				String m_facility_no=req.getParameter("facility_no");
					
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
					" DEBTOR_CODE,"+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//2
					" NVL(CREDIT_LIMIT,0), "+//3
					" "+m_schema_name+".FA_GET_INVOICE_FOR_DEBTOR(FACILITY_NO,DEBTOR_CODE), "+//4
					" "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D001'),"+//5
				  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D002'),"+//6
				  " "+m_schema_name+".FA_DEBTOR_DOCUMENT_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE,'D003'),"+//7
					" "+m_schema_name+".FA_DEBTOR_ACTIVE_STATUS(DEBTOR_CODE), "+//8
					" "+m_schema_name+".FA_DEBTOR_RELATION_STATUS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE),"+//9
					" "+m_schema_name+".FA_DEBTOR_RELATION_REMARKS(CLIENT_CODE,FACILITY_NO,DEBTOR_CODE) "+//10
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE FACILITY_NO='"+m_facility_no+"'"+
					" ORDER BY RELATION_STATUS,DEBTOR_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Company Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Status</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Debtor Relationship</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Remarks</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Invoice Available</b></DIV></td>";
				///------
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					};
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand'  onClick=\"show_client('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+nf.format(rs1.getDouble(3))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(8)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(9)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(10)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(4)+"</td>";
					//if(rs1.getString(4).equals("N")){
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"T\">Terminate</option><OPTION value=\"D\">Deactivate</option><OPTION value=\"N\">Re-view</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					/*}
					else{
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"N\">Re-view</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					}*/
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_DEBTOR_RELATION_REACTIVATION")){
					
				String m_facility_no=req.getParameter("facility_no");
					
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT  "+
					" DEBTOR_CODE,"+//1
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),"+//2
					" NVL(CREDIT_LIMIT,0) "+//3
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
					" WHERE RELATION_STATUS IN('C','T') "+
					" AND FACILITY_NO='"+m_facility_no+"' "+
					" ORDER BY DEBTOR_CODE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Name</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Credit Limit</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					};
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand'  onClick=\"show_client('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+nf.format(rs1.getDouble(3))+"</td>";
					m_string=m_string+"<td width='10%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"N\">Re-view</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='15%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_TERMINATION")){
				
				String m_factor_type=req.getParameter("factor_type");
				String m_client_code=req.getParameter("client_code");
				
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+//1
					" DECODE(A.CLIENT_TYPE,'I','Individual','C','Co-operate') CLIENT_TYPE, "+//2
					" DECODE(A.FACTORING_TYPE,'C','Client','D','Debtor','O','As a Client or Debtor') FACTORING_TYPE,"+//3
					" A.FULL_NAME, "+//4
					" "+m_schema_name+".FA_DETOR_DEPENDENCY(A.CLIENT_CODE), "+//5
					" "+m_schema_name+".FA_DETOR_DEPENDENCY_CLIENT(A.CLIENT_CODE) "+//6
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					" WHERE A.ACTIVE_STATUS NOT IN('T','N','B') "+
					" AND A.FACTORING_TYPE='"+m_factor_type+"' "+
					" AND UPPER(A.FULL_NAME) LIKE UPPER('%"+m_client_code+"%') "+
					" ORDER BY A.CLIENT_CODE");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Code</b></DIV></td>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Dependancy-Invoice</b></DIV></td>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Dependancy-Client</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				//m_string=m_string+"<td width='*%'></td>";
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
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  onClick=\"show_client('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='25%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='25%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='25%' class=div_input>"+rs1.getString(6)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"T\">Terminate</option><OPTION value=\"N\">Deactivate</option><OPTION value=\"B\">Black List</option><OPTION value=\"I\">Re-view</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' style=\"width: 300px\"  VALUE=\"\" maxlength=\"200\"></td>"; 
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_CLIENTS_REACTIVATION")){
				
				String m_factor_type=req.getParameter("factor_type");
				String m_client_code=req.getParameter("client_code");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT "+
					" A.CLIENT_CODE, "+
					" DECODE(A.CLIENT_TYPE,'I','Individual','C','Co-operate') CLIENT_TYPE, "+
					" DECODE(A.FACTORING_TYPE,'C','Client','D','Debtor','O','As a Client or Debtor') FACTORING_TYPE,"+
					" A.FULL_NAME, "+
					" DECODE(A.ACTIVE_STATUS,'T','Terminated','N','De-Activated','B','Black-Listed',A.ACTIVE_STATUS) "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A "+
					" WHERE A.ACTIVE_STATUS IN('T','N','B') "+
					" AND UPPER(A.FULL_NAME) LIKE UPPER('%"+m_client_code+"%') "+
					" AND A.FACTORING_TYPE='"+m_factor_type+"' "+
					" ORDER BY A.CLIENT_CODE");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Code</b></DIV></td>";
				m_string=m_string+"<td width='25%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Active Status</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='*%' ><DIV class=div_input><b>Comments</b></DIV></td>";
				//m_string=m_string+"<td width='*%'></td>";
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
					m_string=m_string+"<td width='10%' class=div_input  style='cursor:hand'  onClick=\"show_client('"+rs1.getString(1)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='25%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='25%' class=div_input>"+rs1.getString(5)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input'><OPTION value=\"R\">Re-Active</option><OPTION value=\"F\">Follow-up</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='*%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' style=\"width: 300px\"  VALUE=\"\" maxlength=\"200\"></td>"; 
					//m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("EDIT_BATCH_NO")){
				String m_batch_no=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT A.DEBTOR_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),"+//2 
					" A.INVOICE_NO, "+//3
					" A.INVOICE_AMOUNT,"+//4
       		" TO_CHAR(A.INVOICE_DATE,'DD'),"+//5
					" TO_CHAR(A.INVOICE_DATE,'MM'),"+//6
					" TO_CHAR(A.INVOICE_DATE,'YYYY'),"+//7
					" NVL(A.INVOICE_COMMENTS,'-'), "+//8
					" A.CURR_CODE, "+//9
					" NVL(A.EXCHANGE_RATE,0), "+//10
					" NVL(A.AMOUNT_RPT_CURR,0), "+//11
					" A.INVOICE_STATUS, "+//12
					" A.INVOICE_SEQ_NO "+//13
  				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					" WHERE A.BATCH_NO='"+m_batch_no+"' "+
					//" AND A.INVOICE_STATUS IN('APP_C','ENTER')"+
					" ORDER BY A.ENT_DATE ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='1%' class=div_input><b></b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Debtor Code</b></td>";
				m_string=m_string+"<td width='20%' class=div_input><b>Name</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Invoice No</b></td>";
				m_string=m_string+"<td width='15%' class=div_input><b>Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Invoice Date</b></td>";
				m_string=m_string+"<td width=\"*%\"></td></tr>";
				
				
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
					
					if(rs1.getString(12).equals("ENTER") || rs1.getString(12).equals("APP_C")){
					m_string=m_string+"<td width='1%' class=div_input><b>"+chk_nums+"</b></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_"+chk_nums+"\" disabled   value=\""+rs1.getString(1)+"\"></td>"+
						"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" style=\"width:200\" name=\"TXT_DEBTOR_NAME_"+chk_nums+"\" disabled value=\""+rs1.getString(2)+"\"></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_"+chk_nums+"\" disabled value=\""+rs1.getString(3)+"\"></td>"+
						"<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(4))+"\"  style='text-align:right'></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_DD_"+chk_nums+"\" value=\""+rs1.getString(5)+"\" style=\"width:30\" disabled>"+
						"<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_MM_"+chk_nums+"\"  value=\""+rs1.getString(6)+"\"  style=\"width:30\" disabled>"+
						"<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_YY_"+chk_nums+"\"   value=\""+rs1.getString(7)+"\"  style=\"width:30\" disabled>"+
						"<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_COMMENTS_"+chk_nums+"\" value=\""+rs1.getString(8)+"\">"+			
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_CURRENCY_CODE_"+chk_nums+"\" value=\""+rs1.getString(9)+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EXCHANGE_RATE_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(10))+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_RPT_CURR_AMT_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(11))+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_SEQ_NO_"+chk_nums+"\"  value=\""+rs1.getString(13)+"\">"+
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EDIT_STATUS_"+chk_nums+"\"  value=\"Y\">"+
						"<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"Edit\" onClick=\"edit_invoice("+chk_nums+")\" ><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_invoice("+chk_nums+")\" ></td>"+
						"</tr>";
					}
					else{
						m_string=m_string+"<td width='1%' class=div_input><b>"+chk_nums+"</b></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_"+chk_nums+"\" disabled   value=\""+rs1.getString(1)+"\"></td>"+
						"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" style=\"width:200\" name=\"TXT_DEBTOR_NAME_"+chk_nums+"\" disabled value=\""+rs1.getString(2)+"\"></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_"+chk_nums+"\" disabled value=\""+rs1.getString(3)+"\"></td>"+
						"<td width=\"15%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(4))+"\"  style='text-align:right'></td>"+
						"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_DD_"+chk_nums+"\" value=\""+rs1.getString(5)+"\" style=\"width:30\" disabled>"+
						"<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_MM_"+chk_nums+"\"  value=\""+rs1.getString(6)+"\" style=\"width:30\" disabled>"+
						"<input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_YY_"+chk_nums+"\"   value=\""+rs1.getString(7)+"\" style=\"width:30\" disabled>"+
						"<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_COMMENTS_"+chk_nums+"\" value=\""+rs1.getString(8)+"\">"+			
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_CURRENCY_CODE_"+chk_nums+"\" value=\""+rs1.getString(9)+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EXCHANGE_RATE_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(10))+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_RPT_CURR_AMT_"+chk_nums+"\" value=\""+nf.format(rs1.getDouble(11))+"\">"+	
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_INVOICE_SEQ_NO_"+chk_nums+"\"  value=\""+rs1.getString(13)+"\">"+
						"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_EDIT_STATUS_"+chk_nums+"\"  value=\"N\">"+
						"<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"Edit\" onClick=\"edit_invoice("+chk_nums+")\" disabled><input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_INVOICE\" value=\"Delete\" onClick=\"delete_invoice("+chk_nums+")\" disabled></td>"+
						"</tr>";
					}
					
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("ADJUST_INVOICE_SEQUENCE")){
				String m_batch_no=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),"+//2 
					" A.INVOICE_NO, "+//3
					" A.INVOICE_AMOUNT,"+//4
       		" TO_CHAR(A.INVOICE_DATE,'DD'),"+//5
					" TO_CHAR(A.INVOICE_DATE,'MM'),"+//6
					" TO_CHAR(A.INVOICE_DATE,'YYYY'),"+//7
       		" NVL(A.INVOICE_COMMENTS,'-'), "+//8
					" TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+//9
					" NVL(A.BALANCE_AMOUNT,0) "+//10
  				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					" WHERE A.BATCH_NO='"+m_batch_no+"' AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class=div_input><b>Code</b></td>";
				m_string=m_string+"<td width='20%' class=div_input><b>Name</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Invoice No</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Adjuested Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Adjustment Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Type</b></td>";
				m_string=m_string+"<td width='*%' class=div_input><b>Comments</b></td>";
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
					m_string=m_string+"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_"+chk_nums+"\" disabled   value=\""+rs1.getString(1)+"\"></td>"+
					"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_NAME_"+chk_nums+"\" disabled value=\""+rs1.getString(2)+"\"></td>"+
					"<td width=\"5%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_"+chk_nums+"\" disabled value=\""+rs1.getString(3)+"\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(10))+"\" style=\"text-align:right;\" ></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+"\" value=\"0\" style=\"text-align:right;\" onchange=\"format_num(document.Form1.TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+",4)\"  onblur=\"check_value_val(document.Form1.TXT_INVOICE_AMOUNT_"+chk_nums+",document.Form1.TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+")\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_DATE_"+chk_nums+"\" maxlength=\"10\"  value=\""+rs1.getString(9)+"\">"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_SOURCE_"+chk_nums+"\" maxlength=\"200\"  value=\"\">"+
					"<td width=\"5%\" class=div_input><select name=\"TXT_ADJ_TYPE_"+chk_nums+"\" class='txt_input'><OPTION value=\"CR\" SELECTED>CR Note</option><OPTION value=\"DR\">DR Note</option></SELECT></td>"+
					"<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_COMMENT_"+chk_nums+"\" maxlength=\"200\">"+
					"<input type=\"checkbox\" name=\"TXT_INVOICE_ADJUST_OK_"+chk_nums+"\">"+
					"<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"View\" onClick=\"show_invoice_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ></td>"+
					"</tr>";
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("ADJUST_INVOICE_SEQUENCE_REASSIGN")){
				String m_batch_no=req.getParameter("batch_no");
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT A.DEBTOR_CODE,"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),"+//2 
					" A.INVOICE_NO, "+//3
					" A.INVOICE_AMOUNT,"+//4
       		" TO_CHAR(A.INVOICE_DATE,'DD'),"+//5
					" TO_CHAR(A.INVOICE_DATE,'MM'),"+//6
					" TO_CHAR(A.INVOICE_DATE,'YYYY'),"+//7
       		" NVL(A.INVOICE_COMMENTS,'-'), "+//8
					" TO_CHAR(SYSDATE,'DD-MM-YYYY'), "+//9
					" NVL(A.BALANCE_AMOUNT,0) "+//10
  				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
					" WHERE A.BATCH_NO='"+m_batch_no+"' AND A.INVOICE_STATUS='CONF' AND A.BALANCE_AMOUNT>0");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='10%' class=div_input><b>Code</b></td>";
				m_string=m_string+"<td width='20%' class=div_input><b>Name</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Invoice No</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Balance Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Reassignment Amount</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Reassignment Date</b></td>";
				m_string=m_string+"<td width='10%' class=div_input><b>Source Document</b></td>";
				m_string=m_string+"<td width='5%' class=div_input><b>Type</b></td>";
				m_string=m_string+"<td width='*%' class=div_input><b>Comments</b></td>";
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
					m_string=m_string+"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_CODE_"+chk_nums+"\" disabled   value=\""+rs1.getString(1)+"\"></td>"+
					"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_DEBTOR_NAME_"+chk_nums+"\" disabled value=\""+rs1.getString(2)+"\"></td>"+
					"<td width=\"5%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_NO_"+chk_nums+"\" disabled value=\""+rs1.getString(3)+"\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_AMOUNT_"+chk_nums+"\" disabled value=\""+nf.format(rs1.getDouble(10))+"\" style=\"text-align:right;\" ></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+"\"  disabled value=\""+nf.format(rs1.getDouble(10))+"\" style=\"text-align:right;\" onchange=\"format_num(document.Form1.TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+",4)\"  onblur=\"check_value_val(document.Form1.TXT_INVOICE_AMOUNT_"+chk_nums+",document.Form1.TXT_INVOICE_ADJUST_AMOUNT_"+chk_nums+")\"></td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_DATE_"+chk_nums+"\" maxlength=\"10\"  value=\""+rs1.getString(9)+"\">"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_SOURCE_"+chk_nums+"\" maxlength=\"200\"  value=\"\">"+
					"<td width=\"5%\" class=div_input><select name=\"TXT_ADJ_TYPE_"+chk_nums+"\" class='txt_input' disabled><OPTION value=\"CR\" SELECTED>CR Note</option><OPTION value=\"DR\">DR Note</option></SELECT></td>"+
					"<td width=\"*%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_INVOICE_ADJUST_COMMENT_"+chk_nums+"\" maxlength=\"200\">"+
					"<input type=\"checkbox\" name=\"TXT_INVOICE_ADJUST_OK_"+chk_nums+"\">"+
					"<input class=\"but_input\" type=\"button\" name=\"BUT_EDIT_INVOICE\" value=\"View\" onClick=\"show_invoice_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ></td>"+
					"</tr>";
					
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_DEBTOR_LIMIT_ADJUST")){
				
				String m_string="";				
	
					rs1= stmt1.executeQuery(" SELECT A.FACILITY_NO, "+//1
					" A.CLIENT_CODE,"+//2
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),"+//3
					" A.DEBTOR_CODE,"+//4
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),"+//5
					" A.SOURCE_REF_NO,"+//6
					" TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),"+//7
					" NVL(A.ACTION_COMMENT,'-'), "+//8
					" A.SOURCE_REF_NO, "+//9
					" B.CHEQUE_NO "+//10
					" FROM "+m_schema_name+".FA_CR_PRO_DEBTOR_LIMIT_ADJ A,"+m_schema_name+".FA_OP_PRO_RETURN_DETAILS B "+
					" WHERE "+
					" B.RETURN_NO=A.SOURCE_REF_NO "+
					" AND A.ACTION_STATUS='N' "+
					" ORDER BY A.ACTION_DATE ");
	
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>";			
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Source Document</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Date</b></DIV></td>"; 
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Action Description</b></DIV></td>"; 
				m_string=m_string+"<td width='5%' ><DIV class=div_input><b>Action</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>";
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
					m_string=m_string+"<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\" style='cursor:hand'><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(2)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_CLIENT_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(2)+"\"><u>"+rs1.getString(3)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_client('"+rs1.getString(4)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_DEBTOR_CODE_"+chk_nums+"' VALUE=\""+rs1.getString(4)+"\"><u>"+rs1.getString(5)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input style='cursor:hand' onClick=\"show_return_details('"+rs1.getString(6)+"')\"><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_REF_SOURCE_DOC_"+chk_nums+"' VALUE=\""+rs1.getString(9)+"\"><u>"+rs1.getString(6)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(10)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input>"+rs1.getString(7)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(8)+"</td>";
					m_string=m_string+"<td width='5%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 100px\" ><OPTION value=\"Y\">Approve</option><OPTION value=\"C\">Cancel</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					m_string=m_string+"<td width='*%'></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_AGREEMENT_PRINTING")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT FACILITY_NO, "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),"+
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+
					" CREDIT_LIMIT,"+
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),"+
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY')"+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='A2' ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=\"right\" ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=\"right\" >"+nf.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td width='*%'>";// <INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" >
					m_string=m_string+"&nbsp;&nbsp;&nbsp;&nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_agreement('"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='width:70' name=BUT_LETTER value=\"Agreement\" ></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_APPROVAL")){
				
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+
					" DECODE(SETTLE_MODE,'CHEQUE',SETTLE_MODE||'-'||CHEQUE_NO,SETTLE_MODE),"+
					" REC_AMOUNT,"+
					" TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),"+
					" RECEIPT_TYPE "+
					"	FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE REC_STATUS='E' "+
					" ORDER BY EFF_VALDATE,RECEIPT_NO ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Receipt No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Settlement Mode/Chq. no</b></DIV></td>";
				m_string=m_string+"<td width='15%' align=\"right\" ><DIV class=div_input><b>Amount</b></DIV></td>";
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
					m_string=m_string+"<td width='10%' class=div_input  >"+rs1.getString(2)+"</td>";
					m_string=m_string+"<td width='15%' class=div_input align=\"right\">"+nf.format(rs1.getDouble(3))+"</td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='11%' class=div_input><select name='TXT_APPROVE_TYPE_"+chk_nums+"' class='txt_input' style=\"width: 90px\" ><OPTION value=\"A\">Approve</option><OPTION value=\"D\">Disapprove</option><OPTION value=\"O\" SELECTED>No Action</option></SELECT></td>";
					m_string=m_string+"<td width='10%' class=div_input><INPUT TYPE='TEXT' class='txt_input' NAME='TXT_APPROVE_COMMENT_"+chk_nums+"' VALUE=\"\" style=\"width: 170px\"  maxlength=\"200\"></td>"; 
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
				else if(m_chksql.equals("LOAD_AGREEMENT_ENHANCEMENT")){
				  
				String m_string="";				

					rs1= stmt1.executeQuery(" SELECT FACILITY_NO, "+
					" CLIENT_CODE,"+
					" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+
					" "+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),"+
					" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+
					" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+
					" CREDIT_LIMIT,"+
					" TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),"+
					" TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY')"+
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
					" WHERE FACILITY_STATUS='Y' "+
					" AND FACILITY_NO NOT IN (SELECT FACILITY_NO FROM "+m_schema_name+".FA_CR_PRO_AGREEMENT_ENHANCE WHERE FACILITY_NO=FACILITY_NO AND CLIENT_CODE=CLIENT_CODE AND LETTER_NAME='AGREEMENT_ENHANCEMENT') ");

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>";
				m_string=m_string+"<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>";
				m_string=m_string+"<td width='15%' ><DIV class=div_input><b>Name</b></DIV></td>";
				m_string=m_string+"<td width='20%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"; 
				m_string=m_string+"<td width='10%' align=\"right\" ><DIV class=div_input><b>Total Limit</b></DIV></td>"; 
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
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_facility('"+rs1.getString(1)+"')\"  style='cursor:hand'><INPUT TYPE='HIDDEN' class='txt_input' NAME='TXT_FACILITY_NO_"+chk_nums+"' VALUE=\""+rs1.getString(1)+"\"><u>"+rs1.getString(1)+"</u></td>";
					m_string=m_string+"<td width='10%' class=div_input  onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>";
					m_string=m_string+"<td width='15%' class=div_input>"+rs1.getString(3)+"</td>";
					m_string=m_string+"<td width='20%' class=div_input>"+rs1.getString(4)+"</td>";
					m_string=m_string+"<td width='10%' class=div_input align=\"right\" >"+nf.format(rs1.getDouble(7))+"</td>";
					m_string=m_string+"<td width='*%'>";// <INPUT TYPE='CHECKBOX' name='TXT_APPROVE_TYPE_"+chk_nums+"' VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" >
					m_string=m_string+"&nbsp;&nbsp;&nbsp;&nbsp; <input class=\"but_input\" type=\"button\" onclick=\"Generate_agreement('"+rs1.getString(1)+"','"+rs1.getString(2)+"')\" style='width:150' name=BUT_LETTER value=\"Agreement Enhancement\" ></td>";
					m_string=m_string+"</tr>";
					chk_nums++;
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			//Added by Mahela on 19-06-2007
			else if(m_chksql.equals("LOAD_APPLICABLE_DOCS")){
			
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_client_type=req.getParameter("CLIENT_TYPE");
				String m_string="";				
					
				rs1= stmt1.executeQuery(" SELECT "+
				"  NVL(A.DOC_CODE,'-') CODE, "+
				"  NVL(A.DESCRIPTION,'-'), "+
				"  TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') "+
				" FROM "+m_schema_name+".FA_CR_DOCUMENTS_REQUIRED A "+
				" WHERE A.DOC_APP_TYPE='"+m_client_type+"' "+
				" AND A.ACTIVE_STATUS='Y' ");
					boolean more = rs1.next();

				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='25%' class='div_input'><b>Document Description</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Date</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Comments</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Status</b></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				
				if(!more){
				m_string="";
				}
				while(more){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DOC_CODE_"+chk_nums+"\" value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_DD_"+chk_nums+"\" VALUE=\""+rs1.getString(3)+"\" class=\"txt_input5\"  maxlength='2' size='2'><input class=\"txt_input5\"  type=\"TEXT\" name=\"TXT_DOC_MM_"+chk_nums+"\" VALUE=\""+rs1.getString(4)+"\"  maxlength='2' size='2'><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_YY_"+chk_nums+"\" VALUE=\""+rs1.getString(5)+"\"  maxlength='4' size='2'> <a style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+chk_nums+") ><u>Calender</u></a> <input class=\"txt_input\" type=\"hidden\" name=\"TXT_RSTATUS_"+chk_nums+"\" value=\"N\"></td>"+
					"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" style=\"width:270\" maxlength=\"200\"></td>"+
					"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"TXT_APPROVE_TYPE_"+chk_nums+"\" VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  ></td>"+
					"</tr>";
					more = rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";
				out.println(m_string);
			}
			//Added by Mahela on 19-06-2007
			else if(m_chksql.equals("LOAD_APPLICABLE_DOCS_2")){
			
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_client_type=req.getParameter("CLIENT_TYPE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				String m_string="";				
					
					
				rs1= stmt1.executeQuery("SELECT  DISTINCT "+
				" A.DOC_CODE CODE, "+ 
				" B.DESCRIPTION, "+
				" TO_CHAR(A.DOCDATE,'DD'),TO_CHAR(A.DOCDATE,'MM'),TO_CHAR(A.DOCDATE,'YYYY'),  "+
				" NVL(A.COMMENTS,' '), "+
				" A.STATUS "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DOC_UPDATION A,"+m_schema_name+".FA_CR_DOCUMENTS_REQUIRED B "+
				" WHERE A.DOC_CODE=B.DOC_CODE  AND A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_CODE='"+m_facility_no+"' "+
				" UNION ALL "+
				" SELECT "+
				" NVL(A.DOC_CODE,'-') CODE, "+
				" NVL(A.DESCRIPTION,'-'), "+
				" TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY'),"+
				" ' ',"+
				" 'N'"+   
				" FROM "+m_schema_name+".FA_CR_DOCUMENTS_REQUIRED A "+
				" WHERE A.ACTIVE_STATUS='Y' "+
				//" AND A.DOC_APP_TYPE='"+m_client_type+"'  "+
				" AND A.DOC_CODE NOT IN(SELECT DOC_CODE  FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DOC_UPDATION WHERE CLIENT_CODE='"+m_client_code+"' AND FACILITY_CODE='"+m_facility_no+"'  ) ");
					
					boolean more = rs1.next();
					boolean m_flag = true;
					      
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='25%' class='div_input'><b>Document Description</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Date</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Comments</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Status</b></td>";
				m_string=m_string+"</tr>";
				
				int chk_nums=0;
				int j=1;
				
				if(!more){

				}
				
				while(more){
					chk_nums++;
					if(j==0){
						m_string=m_string+"<tr bgcolor=\"#FFFFFF\">";
						j=1;
					}
					else{
						m_string=m_string+"<tr bgcolor=\"#C0C0C0\" >";
						j=0;
					}
					m_string=m_string+"<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DOC_CODE_"+chk_nums+"\" value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</td>";
					if(rs1.getString(7).equals("N")){
					m_string=m_string+"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_DD_"+chk_nums+"\" VALUE=\""+rs1.getString(3)+"\" class=\"txt_input5\"  maxlength='2' size='2'><input class=\"txt_input5\"  type=\"TEXT\" name=\"TXT_DOC_MM_"+chk_nums+"\" VALUE=\""+rs1.getString(4)+"\"  maxlength='2' size='2'><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_YY_"+chk_nums+"\" VALUE=\""+rs1.getString(5)+"\"  maxlength='4' size='2'> <a style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+chk_nums+") ><u>Calender</u></a> <input class=\"txt_input\" type=\"hidden\" name=\"TXT_RSTATUS_"+chk_nums+"\" value=\"N\"></td>";
					m_string=m_string+"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" VALUE=\""+rs1.getString(6)+"\" style=\"width:270\" maxlength=\"200\"></td>";
					m_string=m_string+"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"TXT_APPROVE_TYPE_"+chk_nums+"\" VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\"  ></td>";
					}
					else if(rs1.getString(7).equals("Y")){
					m_string=m_string+"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_DD_"+chk_nums+"\" VALUE=\""+rs1.getString(3)+"\" class=\"txt_input5\"  maxlength='2' size='2' disabled><input class=\"txt_input5\"  type=\"TEXT\" name=\"TXT_DOC_MM_"+chk_nums+"\" VALUE=\""+rs1.getString(4)+"\"  maxlength='2' size='2' disabled><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_YY_"+chk_nums+"\" VALUE=\""+rs1.getString(5)+"\"  maxlength='4' size='2' disabled> <a style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+chk_nums+") ><u>Calender</u></a> <input class=\"txt_input\" type=\"hidden\" name=\"TXT_RSTATUS_"+chk_nums+"\" value=\"N\"></td>";
					m_string=m_string+"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" VALUE=\""+rs1.getString(6)+"\" style=\"width:270\" maxlength=\"200\" disabled></td>";
					m_string=m_string+"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"TXT_APPROVE_TYPE_"+chk_nums+"\" VALUE=\"Y\" onClick=\"check_change('"+chk_nums+"')\" checked disabled></td>";
					}
					m_string=m_string+"</tr>";
					more = rs1.next();
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";

				out.println(m_string);
			}
			else if(m_chksql.equals("LOAD_APPLICABLE_DOCS_CLIENT_DEBTOR_LIST")){
			
				String m_client_code=req.getParameter("CLIENT_CODE");
				String m_facility_no=req.getParameter("FACILITY_NO");
				String m_debtor_code=req.getParameter("DEBTOR_CODE");
				String m_string="";				

				
				rs1= stmt1.executeQuery("SELECT  "+
				" A.DOC_CODE CODE, "+ 
				" B.DESCRIPTION, "+
				" TO_CHAR(A.UPDATE_DATE,'DD-MM-YYYY'), "+
				" NVL(A.UPDATE_COMMENTS,' '), "+
				" 'D' "+
				" FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE A,"+m_schema_name+".FA_CR_FACILITY_DEBTOR_REQ_LIST B "+
				" WHERE A.DOC_CODE=B.DOC_CODE  "+
				" AND A.CLIENT_CODE='"+m_client_code+"' "+
				" AND A.FACILITY_CODE='"+m_facility_no+"' "+
				" AND A.DEBTOR_CODE='"+m_debtor_code+"' "+
				" UNION ALL "+
				" SELECT "+
				" NVL(A.DOC_CODE,'-') CODE, "+
				" NVL(A.DESCRIPTION,'-'), "+
				" TO_CHAR(SYSDATE,'DD-MM-YYYY'),"+
				" ' ',"+
				" 'E' "+ 
				" FROM "+m_schema_name+".FA_CR_FACILITY_DEBTOR_REQ_LIST A "+
				" WHERE "+
				" A.ACTIVE_STATUS='Y' "+
				" AND A.DOC_CODE NOT IN(SELECT DOC_CODE  FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE "+
				" WHERE CLIENT_CODE='"+m_client_code+"' AND FACILITY_CODE='"+m_facility_no+"' AND DEBTOR_CODE='"+m_debtor_code+"') ");

					boolean m_flag = true;
					      
				m_string=m_string+"<table align='center' width='100%' class='table' >";
				m_string=m_string+"<tr class=pdn_txtpos2>"; 
				m_string=m_string+"<td width='25%' class='div_input'><b>Document Description</b></td>";
				m_string=m_string+"<td width='10%' class='div_input'><b>Date(DD-MM-YYYY)</b></td>";
				m_string=m_string+"<td width='20%' class='div_input'><b>Comments</b></td>";
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
				
					if(rs1.getString(5).equals("E")){
					m_string=m_string+"<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DOC_CODE_"+chk_nums+"\" value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_DATE_"+chk_nums+"\" VALUE=\""+rs1.getString(3)+"\" class=\"txt_input5\"  maxlength='10' size='10'>"+
					"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_UPDATE_STATUS_"+chk_nums+"\" value=\"Y\"></td>"+
					"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" VALUE=\""+rs1.getString(4)+"\" style=\"width:270\" maxlength=\"200\" ></td>";
					m_string=m_string+"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"TXT_APPROVE_TYPE_"+chk_nums+"\" VALUE=\"N\" onClick=\"check_change('"+chk_nums+"')\" ></td>";
					m_string=m_string+"</tr>";
					}
					else{
					m_string=m_string+"<td width=\"25%\" class=div_input><input class=\"txt_input\" type=\"hidden\" name=\"TXT_DOC_CODE_"+chk_nums+"\" value=\""+rs1.getString(1)+"\">"+rs1.getString(2)+"</td>"+
					"<td width=\"10%\" class=div_input><input class=\"txt_input5\" type=\"TEXT\" name=\"TXT_DOC_DATE_"+chk_nums+"\" VALUE=\""+rs1.getString(3)+"\" class=\"txt_input5\"  maxlength='10' size='10' disabled>"+
					"<input class=\"txt_input\" type=\"hidden\" name=\"TXT_UPDATE_STATUS_"+chk_nums+"\" value=\"N\"></td>"+
					"<td width=\"20%\" class=div_input><input class=\"txt_input\" type=\"text\" name=\"TXT_COMMENTS_"+chk_nums+"\" VALUE=\""+rs1.getString(4)+"\" style=\"width:270\" maxlength=\"200\" disabled></td>";
					m_string=m_string+"<td width=\"10%\" class=div_input><input type=\"checkbox\" name=\"TXT_APPROVE_TYPE_"+chk_nums+"\" VALUE=\"Y\" onClick=\"check_change('"+chk_nums+"')\" checked disabled></td>";
					m_string=m_string+"</tr>";
					}
				}
				m_string=m_string+"<INPUT TYPE='HIDDEN' NAME='NUM_CHKS' VALUE="+chk_nums+">";
				m_string=m_string+"</table>";

				out.println(m_string);
			}
			else if(m_chksql.equals("CLIENT_NAME_SEARCH_HELP")){
			
				String m_full_name=req.getParameter("full_name");
				String m_string="";				

				
				rs1= stmt1.executeQuery("SELECT CLIENT_CODE,"+
					" FULL_NAME,"+
					" ACTIVE_STATUS,"+
					" CTYPE,CADDRESS,NVL(BNIC,'-') "+
					" FROM ( "+
					" SELECT CLIENT_CODE,FULL_NAME,ACTIVE_STATUS,'FACTORING' CTYPE, "+
					" (REGISTERED_ADDRESS1 || REGISTERED_ADDRESS2) CADDRESS, "+
					" DECODE(CLIENT_CATEGORY,'INDIVIDUAL',NIC_NO,BUSINESS_CERTIFICATE_NO) BNIC "+
					" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
					" WHERE UPPER(FULL_NAME) LIKE UPPER('%"+m_full_name+"%') "+
					" UNION ALL  "+
					" SELECT CLIENT_CODE,FULL_NAME,ACTIVE_STATUS,'LEASING' CTYPE, "+
					" (ADDRESS1 || ADDRESS2) CADDRESS, "+
					" DECODE(CLIENT_CATEGORY,'INDIVIDUAL',NIC_NO,BUSINESS_CERTIFICATE_NO) BNIC "+
					" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					" WHERE UPPER(FULL_NAME) LIKE UPPER('%"+m_full_name+"%') "+
					" AND CLIENT_CODE NOT IN (SELECT CLIENT_CODE FROM "+m_schema_name+".FA_CO_MAS_CLIENT) "+
					" ) "+
					" ORDER BY FULL_NAME,CTYPE ");
				
				out.println("<HTML><HEAD><TITLE></TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 10pt arial;}'>");
				out.println("<TR><TD STYLE='{font:9pt arial;}'><CENTER><B>Client Detail Search Results for %"+m_full_name+"% </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR><BR>");
      
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2 STYLE='{font:8pt arial;}'>"); 
				out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'><b>CLIENT CODE</b></td>");
				out.println("<td width='35%' class='div_input' STYLE='{font:8pt arial;}'><b>FULL NAME</b></td>");
				out.println("<td width='20%' class='div_input' STYLE='{font:8pt arial;}'><b>ADDRESS</b></td>");
				out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'><b>NIC/BRC NO</b></td>");
				out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'><b>ACTIVE STATUS</b></td>");
				out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'><b>DIVISION</b></td>");
				out.println("</tr>");
				
				int chk_nums=0;
				int j=1;

				while(rs1.next()){
					chk_nums++;
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\" STYLE='{font:8pt arial;}'>");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" STYLE='{font:8pt arial;}'>");
						j=0;
					}
					out.println("<td width='25%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(2)+"</td>");
					out.println("<td width='20%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(5)+"</td>");
					out.println("<td width='20%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(6)+"</td>");
					out.println("<td width='20%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class='div_input' STYLE='{font:8pt arial;}'>"+rs1.getString(4)+"</td>");
					out.println("</tr>");
				}
				out.println("</table>");
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

