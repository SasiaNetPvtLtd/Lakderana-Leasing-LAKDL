import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:08-01-2007

public class LAKDL_FA_OP_PRO_rpt_exception extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
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
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
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
			else if(m_chksql.equals("LOAD_EXCEPTION_REPORT")){
			
			String m_string="";				
			String m_sql="";	
			
			out.println("<table align='center' width='100%' class='table' bordercolor='darkblue' border='1' style='{background-color:silver}' >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");
			
			// ---------- INQUIRY ALLOCATION STATUS REPORT  -----------------------------------------------------------	
			
			//Allocated Inquiries
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			" WHERE DIVISION_CODE='FA' AND "+
			" INQUIRY_CODE IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO)");
			
			boolean mflag=true;							
			boolean more_i = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Inquiry Allocation Status Report </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Inquiry Allocation Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input align='center'><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more_i){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Inquiry Allocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_allocated_inquiry_detail_report()\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' class=div_input onClick=\"\" style='cursor:hand' align='center' ><input class='but_input' type='button' name='BUT_INQ_EDIT1' value=\"Edit\" onClick=\"edit_inquiry()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Inquiry Allocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_allocated_inquiry_detail_report()\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' class=div_input onClick=\"\" style='cursor:hand' align='center' ><input class='but_input' type='button' name='BUT_INQ_EDIT1' value=\"Edit\" onClick=\"edit_inquiry()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			//Unallocated Inquiries
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
			" WHERE DIVISION_CODE='FA' AND "+
			" INQUIRY_CODE NOT IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO)");
			
			boolean more_iu = rs1.next(); 
			
			if(more_iu){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Inquiry Unallocated </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_unallocated_inquiry_detail_report()\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");		
			out.println("<td width='20%' class=div_input onClick=\"\" style='cursor:hand' align='center' ><input class='but_input' type='button' name='BUT_INQ_EDIT2' value=\"Edit\" onClick=\"edit_inquiry()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Inquiry Unallocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_unallocated_inquiry_detail_report()\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' class=div_input onClick=\"\" style='cursor:hand' align='center' ><input class='but_input' type='button' name='BUT_INQ_EDIT2' value=\"Edit\" onClick=\"edit_inquiry()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' class=div_input onClick=\"\" align='center' >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			out.println("</table>");
			//  ------------------REPORT 13 END -------------------------------------------------------------
			out.println("</td>");										
			out.println("<td width='50%'>");
			
			//--------------------------CLIENT APPROVAL STATUS REPORT - 1 -------------------------------
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE (FACTORING_TYPE ='C' OR FACTORING_TYPE ='B' ) "+
			" AND ACTIVE_STATUS='E'");
			
			String client_code = "";
			boolean more = rs1.next();

			mflag=true;
			
			out.println("<table  width='100%' class='pdn_txtpos2'>");// style='{font:20pt arial;}'
			out.println("<tr><td align='left' ><b>Client Exceptions</b></td></tr>");
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='30%'><DIV class=div_input><b>Client Status</b></DIV></td>");
			out.println("<td width='20%' align='right'><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			
			if(more){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Client Creation</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('E')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT1' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");			
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Client Creation</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('E')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT1' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE (factoring_type ='C' OR factoring_type ='B' ) "+
			" AND active_status='I'");
			
			boolean more12 = rs1.next();
			
			if(more12){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Initial</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('I')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT2' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");			
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Initial</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('I')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT2' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE (factoring_type ='C' OR factoring_type ='B' ) "+
			" AND active_status='Y'");
			
			boolean more13 = rs1.next(); 
			
			if(more13){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT3' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");			
			
			}
			else
			{
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT3' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE (factoring_type ='C' OR factoring_type ='B' ) "+
			" AND active_status='N'");

			boolean more14 = rs1.next(); 
			
			if(more14){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Disapproved Client</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' class=div_input align='center' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT4' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");			
			
			}
			else
			{
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Disapproved Client</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input ><input class='but_input' type='button' name='BUT_CLIENT_EDIT4' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE (factoring_type ='C' OR factoring_type ='B' ) "+
			" AND active_status='T'");

			boolean more15 = rs1.next(); 
			
			if(more15){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('T')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input ><input class='but_input' type='button' name='BUT_CLIENT_EDIT5' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");			
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_detail_report('T')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input ><input class='but_input' type='button' name='BUT_CLIENT_EDIT5' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			out.println("</table>");
			
			//---------- REPORT 1 END -----------------------------------------------------------
			
			out.println("</td>");										
			out.println("</tr>");										
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' bordercolor='darkblue' border='1' style='{background-color:silver}'  >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");
			
			//------------------ DEBTOR APPROVAL STATUS REPORT - 2 -----------------------------
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE factoring_type ='D'  "+
			" AND active_status='E'");
			
			boolean more2 = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Debtor Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Debtor Approval Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>");
			
			if(more2){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Debtor Creation</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('E')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT6' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");		
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Debtor Creation</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('E')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT6' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE factoring_type ='D'  "+
			" AND active_status='I'");
			
			boolean more16 = rs1.next();
			
			if(more16){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Initial</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('I')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT7' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Initial</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('I')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT7' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}						
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE factoring_type ='D'  "+
			" AND active_status='Y'");
			
			boolean more17 = rs1.next();
			
			if(more17){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT8' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Approved Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT8' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}	
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE factoring_type ='D'  "+
			" AND active_status='N'");
			
			boolean more18 = rs1.next();
			
			if(more18){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Disapproved Debtor</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT9' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Disapproved Debtor</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT9' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}					
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			" WHERE factoring_type ='D'  "+
			" AND active_status='T'");
			
			boolean more19 = rs1.next();
			
			if(more19){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('T')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT10' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_debtor_detail_report('T')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_EDIT10' value=\"Edit\" onClick=\"edit_client()\" disabled ></td>");
			out.println("</tr>");
			}					
			
			out.println("</table>");
			
			
			//---------- REPORT 2 END -----------------------------------------------------------
			out.println("</td>");										
			out.println("<td>");										
			//------------------CLIENT DEBTOR ASSIGN STATUS REPORT - 3 -----------------------------
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR  "+
			" WHERE RELATION_STATUS='N'");	
			
			mflag=false;
			boolean more6 = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Client Debtor Assign Status Report </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Relation Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more6){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Debtor to be Assigned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT1' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Debtor to be Assigned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT1' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR  "+
			" WHERE RELATION_STATUS='Y'");	
			
			boolean more20 = rs1.next();				
			
			if(more20){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Assigned Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT2' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Assigned Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT2' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR  "+
			" WHERE RELATION_STATUS='C'");	
			
			boolean more21 = rs1.next();				
			
			if(more21){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Assigned Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT3' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Assigned Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_debtor_assign_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_CLIENT_DEBT_EDIT3' value=\"Edit\" onClick=\"edit_client_debtor_assign()\" disabled ></td>");
			out.println("</tr>");									
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			out.println("</table>");
			
			//---------- REPORT 3 END -----------------------------------------------------------
			
			out.println("</td>");										
			out.println("</tr>");										
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' bordercolor='darkblue' border='1' style='{background-color:silver}' >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");
			
			//------------------ INVOICE APPROVAL STATUS REPORT - 4 -----------------------------
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL  "+	
			" WHERE invoice_status='APP_C' ");//ENTER
			
			boolean more3 = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Invoice Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Invoice Approval Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more3){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice to be Approved-Credit  </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APP_C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT1' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice to be Approved-Credit </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APP_C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT1' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL  "+	
			" WHERE invoice_status='APP_2' ");
			
			boolean more23 = rs1.next();
			
			if(more23){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Approved-Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APP_2')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT2' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Approved-Credit</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APP_2')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT2' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL  "+	
			" WHERE invoice_status='APPR1' ");
			
			boolean more2app = rs1.next();
			
			if(more2app){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APPR1')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT3' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('APPR1')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT3' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL  "+	
			" WHERE invoice_status='CONF' ");
			
			boolean more22 = rs1.next();
			
			if(more22){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Confirmed</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('CONF')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT4' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Invoice Confirmed</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('CONF')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT4' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");										
			}

			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL  "+	
			" WHERE invoice_status='CANCEL' ");
			
			boolean more24 = rs1.next();
			
			if(more24){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('CANCEL')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT5' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Invoice Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_invoice_detail_report('CANCEL')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_INVOICE_EDIT5' value=\"Edit\" onClick=\"edit_invoice()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									

			out.println("</table>");
			
			//---------- REPORT 4 END -----------------------------------------------------------	
			
			out.println("</td>");
			out.println("<td width='50%'>");
			
			//------------------ CLIENT FACILITY STATUS REPORT - 5 -----------------------------
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='N' ");				
			
			
			boolean more4 = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Client Facility Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Client Facility Approval Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more4){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT1' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT1' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='A' ");				
			
			boolean more25 = rs1.next();
			
			if(more25){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('A')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT2' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('A')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT2' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='A2' ");				
			
			boolean more2con = rs1.next();
			
			if(more2con){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Confirmed</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('A2')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT3' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility Cofirmed</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('A2')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT3' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='Y' ");				
			
			boolean more2act = rs1.next();
			
			if(more2act){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Activated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT4' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility Activated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT4' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='C' ");				
			
			boolean more26 = rs1.next();
			
			if(more26){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT5' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Facility Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT5' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY  "+	
			" WHERE FACILITY_STATUS='T' ");				
			
			boolean more27 = rs1.next();
			
			if(more27){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('T')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT6' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");					
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Facility Terminated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_client_facility_detail_report('T')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_FACILITY_EDIT6' value=\"Edit\" onClick=\"edit_facility()\" disabled ></td>");
			out.println("</tr>");										
			}					
			
			out.println("</table>");
			
			//---------- REPORT 5 END -----------------------------------------------------------	
			
			out.println("</td>");										
			out.println("</tr>");										
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' bordercolor='darkblue' border='1' style='{background-color:silver}' >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");
			
			//------------------ POD CHEQUE STATUS REPORT - 6 -----------------------------
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES  "+	
			" WHERE POD_STATUS='N' ");
			
			boolean more5 = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> POD Cheque Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>POD Cheque Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
      
			  
			
			if(more5){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Available</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT1' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Available</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT1' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");										
			}

			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>&nbsp;</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES  "+	
			" WHERE POD_STATUS='Y' ");				
			
			boolean more28 = rs1.next(); 
			
			if(more28){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Realised </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT2' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Realised </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT2' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES  "+	
			" WHERE POD_STATUS='C' ");				
			
			boolean more29 = rs1.next(); 
			
			if(more29){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Dishonored </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT3' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>POD Cheque Dishonored </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_pod_cheque_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_POD_EDIT3' value=\"Edit\" onClick=\"edit_pod_cheque()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									

			
			//------------------ SETTLEMENT DEPOSIT REPORT - 8 -----------------------------
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT  "+	
			" WHERE STATUS='Y' ");				
			
			//boolean mflag=true;							
			boolean more8 = rs1.next();
			String m_dep_status = "";		
			mflag=false;
			
			out.println("</table>");
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' style='{height: 23px;}' ><B> Settlement Deposit Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Deposit Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 						
			
			if(more8){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Deposit Realised</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_deposit_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_DEPOST_EDIT1' value=\"Edit\" onClick=\"edit_deposit()\" disabled ></td>");
			out.println("</tr>");
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Deposit Realised</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_deposit_detail_report('Y')\" style='cursor:hand'  ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_DEPOST_EDIT1' value=\"Edit\" onClick=\"edit_deposit()\" disabled ></td>");
			out.println("</tr>");					
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT  "+	
			" WHERE STATUS='C' ");									
			
			boolean more40 = rs1.next();
			
			if(more40){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Deposit Returned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_deposit_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_DEPOST_EDIT2' value=\"Edit\" onClick=\"edit_deposit()\" disabled ></td>");
			out.println("</tr>");
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Deposit Returned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_deposit_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_DEPOST_EDIT2' value=\"Edit\" onClick=\"edit_deposit()\" disabled ></td>");
			out.println("</tr>");					
			}				
			
	 		//---------- REPORT 8 END -----------------------------------------------------------	

		  //blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			out.println("<tr class=pdn_txtpos2 style='{background-color:white;}' >");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>&nbsp;</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			
			
			out.println("<tr class=pdn_txtpos2 style='{background-color:white;}' >");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>&nbsp;</b></DIV></td>"); 
			out.println("</tr>"); 											

			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									

			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\">&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input >&nbsp;</td>");
			out.println("</tr>");									

			out.println("</table>");
			
			//---------- REPORT 6 END -----------------------------------------------------------	
			
			out.println("</td>");
			out.println("<td width='50%'>");
			
			//------------------ SETTLEMENT RECEIPT REPORT - 7 -----------------------------
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='N' ");
			
			boolean more7 = rs1.next();
			String m_status = "";	
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Settlement Receipt Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%'><DIV class=div_input><b>Receipt Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more7){
			
			mflag=true;
			//m_status = rs7.getString(3);
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Receipt to be Deposited</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT1' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='N' AND SETTLE_MODE='CASH' ");	
			
			boolean more10 = rs1.next();
			
			
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Settle Mode</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more10){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cash</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('N','CASH')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT2' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='N' AND SETTLE_MODE='CHEQUE' ");	
			
			boolean more30 = rs1.next();											
			
			if(more30){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}													 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cheque</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('N','CHEQUE')\" style='cursor:hand'  ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT3' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='N' AND SETTLE_MODE='BANKTR' ");	
			
			//boolean mflag=true;							
			boolean more31 = rs1.next();											
			
			
			if(more31){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}											 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bank Transfer</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('N','BANKTR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT4' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}							
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Receipt to be Deposited</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT1' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			}
			
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='B' ");
			
			boolean more32 = rs1.next();
			
			if(more32){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Receipt Deposited</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('B')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT6' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='B' AND SETTLE_MODE='CASH' ");	
			
			boolean more10 = rs1.next();
			//boolean mflag = true;	
			
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Settle Mode</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more10){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}											
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cash</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('B','CASH')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT7' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='B' AND SETTLE_MODE='CHEQUE' ");	
			
			boolean more30 = rs1.next();											
			
			if(more30){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}											
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cheque</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('B','CHEQUE')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT8' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='B' AND SETTLE_MODE='BANKTR' ");	
			
			boolean mflag10=true;							
			boolean more31 = rs1.next();											
			
			if(more31){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}											 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bank Transfer</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('B','BANKTR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand'><input class='but_input' type='button' name='BUT_RECEIPT_EDIT9' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}															
			
			
			}
			else{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Receipt Deposited</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('B')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT6' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");														
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='C' ");
			
			boolean more33 = rs1.next();
			
			
			
			if(more33){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Receipt Returned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT11' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='C' AND SETTLE_MODE='CASH' ");	
			
			boolean more35 = rs1.next();

			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Settle Mode</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more35){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}											 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cash</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('C','CASH')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT12' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='C' AND SETTLE_MODE='CHEQUE' ");	
			
			boolean more36 = rs1.next();											
			
			if(more36){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cheque</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('C','CHEQUE')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT13' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='C' AND SETTLE_MODE='BANKTR' ");	
			
			boolean more37 = rs1.next();											
			
			if(more37){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bank Transfer</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('C','BANKTR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT14' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}															

			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Receipt Returned</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT11' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='Y' ");
			
			boolean more34 = rs1.next();

			if(more34){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Receipt Realised</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT16' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='Y' AND SETTLE_MODE='CASH' ");	
			
			boolean more38 = rs1.next();

			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Settle Mode</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more38){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cash</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('Y','CASH')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT17' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='Y' AND SETTLE_MODE='CHEQUE' ");	
			
			boolean more39 = rs1.next();											
			
			if(more39){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cheque</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('Y','CHEQUE')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT18' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");															
			}
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT  "+	
			" WHERE REC_STATUS='C' AND SETTLE_MODE='BANKTR' ");	
			
			//boolean mflag10=true;							
			 more40 = rs1.next();											
			
			if(more40){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bank Transfer</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report_ex('Y','BANKTR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT19' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");					
			
			}															

			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Receipt Realised</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_receipt_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_RECEIPT_EDIT16' value=\"Edit\" onClick=\"edit_receipt()\" disabled ></td>");
			out.println("</tr>");
			
			}
			
			out.println("</table>");
			
			//---------- REPORT 7 END -----------------------------------------------------------	
			
			out.println("</td>");										
			out.println("</tr>");										
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' bordercolor='darkblue' border='1' style='{background-color:silver}' >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");

			//------------------ ADJUSTMENTS STATUS REPORT - 9 -----------------------------
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='Y' ");				
			
			//boolean mflag=true;							
			boolean more9 = rs1.next();
			String m_invoice_status="";	
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Adjustments Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Adjustment Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more9){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Adjustment Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_EDIT' value=\"Edit\" onClick=\"\" disabled ></td>");
			out.println("</tr>");
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='Y' AND ADJUST_CATEGORY='INA' ");

			boolean more11 = rs1.next();
			
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Adjustment Category</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center'><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more11){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invoice</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('Y','INA')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_INV_EDIT1' value=\"Edit\" onClick=\"edit_invoice_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}

			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='Y' AND ADJUST_CATEGORY='INR' ");
			
			boolean more4re = rs1.next();
			
			
			if(more4re){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invoice Re-assign</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('Y','INR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_INV_EDIT3' value=\"Edit\" onClick=\"edit_client_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='Y' AND ADJUST_CATEGORY='CLA' ");
			
			boolean more41 = rs1.next();
			
			
			if(more41){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('Y','CLA')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_CLI_EDIT1' value=\"Edit\" onClick=\"edit_client_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Adjustment Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_EDIT' value=\"Edit\" onClick=\"\" disabled ></td>");
			out.println("</tr>");					
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='N' ");				
			
			boolean more42 = rs1.next();
			
			if(more42){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Adjustment to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_EDIT' value=\"Edit\" onClick=\"\" disabled ></td>");
			out.println("</tr>");
			
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='N' AND ADJUST_CATEGORY='INA' ");
			
			boolean more11 = rs1.next();
			
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
			out.println("<td width='30%'><DIV class=div_input><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Adjustment Category</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 											
			
			if(more11){
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			}
			 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invoice</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('N','INA')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_INV_EDIT2' value=\"Edit\" onClick=\"edit_invoice_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='N' AND ADJUST_CATEGORY='INR' ");
			
			boolean more4ren = rs1.next();
			
			
			if(more4ren){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Invoice Re-assign</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('N','INR')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_INV_EDIT4' value=\"Edit\" onClick=\"edit_client_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}
			
			rs1= stmt1.executeQuery("SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS  "+	
			" WHERE INVOICE_STATUS='N' AND ADJUST_CATEGORY='CLA' ");
			
			boolean more43 = rs1.next();
			
			
			if(more43){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else
			{
			out.println("<tr class=tr_input1>");
			mflag=true;													
			} 
			out.println("<td width='30%' class=div_input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report_ex('N','CLA')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_CLI_EDIT2' value=\"Edit\" onClick=\"edit_client_adj()\" disabled ></td>");
			out.println("</tr>");				
			
			}
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Adjustment to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_adjustment_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_ADJUST_EDIT' value=\"Edit\" onClick=\"\" disabled ></td>");
			out.println("</tr>");					
			}

			out.println("</table>");	
			
			//---------- REPORT 9 END -----------------------------------------------------------	
			
			out.println("</td>");
			out.println("<td>");
			
			//---------- QUOTATION STATUS REPORT  -----------------------------------------------------------	
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+	
			" WHERE QUOTATION_STATUS='N' ");
			
			//boolean mflag=true;							
			boolean more_qn = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Quotation Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Quotation Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more_qn){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Quotation to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('N')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('N')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");										
			}
			
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input><b>&nbsp;</b></DIV></td>"); 
			out.println("</tr>"); 
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+	
			" WHERE QUOTATION_STATUS='Y' ");				
			
			boolean more_qy = rs1.next(); 
			
			if(more_qy){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Approved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('Y')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Approved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('Y')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION  "+	
			" WHERE QUOTATION_STATUS='C' ");				
			
			boolean more_qc = rs1.next(); 
			
			if(more_qc){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Disapproved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('C')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Disapproved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_quotation_detail_report('C')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");										
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			out.println("<tr class=pdn_txtpos2 style='{background-color=white}'>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>&nbsp;</b></DIV></td>");
			out.println("<td width='20%' ><DIV class=div_input><b>&nbsp;</b></DIV></td>"); 
			out.println("</tr>"); 
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			out.println("</table>");
			
			//  ------------------REPORT 10 END -------------------------------------------------------------
			
			out.println("</td>");										
			out.println("</tr>");										
			out.println("</table>");
			out.println("<table align='center' width='100%' class='table' border='1' bordercolor='darkblue' style='{background-color:silver}' >");
			out.println("<tr class=pdn_txtpos2 >");//
			out.println("<td width='50%'>");
			
			// ---------- QUOTATION ALLOCATION STATUS REPORT  -----------------------------------------------------------	
			
			//Allocated Quotations					
			rs1= stmt1.executeQuery(" SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
			" WHERE QUOTATION_NO IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");
			
			//boolean mflag=true;							
			boolean more_allo = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Quotation Allocation Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Quotation Allocation Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more_allo){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Allocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_allocated_quotation_detail_report()\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Allocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_allocated_quotation_detail_report()\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");										
			}
			
			//Unallocated Quotations
			rs1= stmt1.executeQuery(" SELECT COUNT(*) "+
			" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
			" WHERE QUOTATION_NO NOT IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");
			
			boolean more_alloc = rs1.next(); 
			
			if(more_alloc){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Unallocated </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_unallocated_quotation_detail_report()\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Quotation Unallocated</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_unallocated_quotation_detail_report()\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" ></td>");
			out.println("</tr>");										
			}
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			//blank
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>&nbsp;</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" >&nbsp;</td>");
			out.println("</tr>");										
			
			out.println("</table>");
			
			//  ------------------REPORT 11 END -------------------------------------------------------------
			
			out.println("</td>");
			out.println("<td>");
			
			//---------- PAYMENT STATUS REPORT  -----------------------------------------------------------	
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='ENTER' ");
			
			//boolean mflag=true;							
			boolean more_pe = rs1.next();
			
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='left' ><B> Payment Approval Status Report </B></TD></TR>");
			out.println("</TABLE>");
			
			out.println("<table align='center' width='100%' class='table' >");
			out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
			out.println("<td width='30%' ><DIV class=div_input><b>Payment Status</b></DIV></td>");
			out.println("<td width='20%' align='right' ><DIV class=div_input><b>Exception Count</b></DIV></td>");
			out.println("<td width='20%' align='center' ><DIV class=div_input><b>Edit</b></DIV></td>"); 
			out.println("</tr>"); 
			
			if(more_pe){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('ENTER')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT1' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('ENTER')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT1' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='HIGH' ");
			
			//boolean mflag=true;							
			boolean more_phigh = rs1.next();

			if(more_phigh){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment to be Approved-High</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('HIGH')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT5' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment to be Approved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('HIGH')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT5' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='APPR1' ");				
			
			boolean more_pa = rs1.next(); 
			
			if(more_pa){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			
			out.println("<td width='30%' class=div_input>Payment Approved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('APPR1')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT2' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Approved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('APPR1')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT2' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='CONF' ");				
			
			boolean more_pc = rs1.next(); 
			
			if(more_pc){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Confirmed </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('CONF')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Confirmed </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('CONF')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='PRINT' ");				
			
			boolean more_pcc = rs1.next(); 
			
			if(more_pcc){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Cheque Printing </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('PRINT')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Cheque Printing </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('PRINT')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
				rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='DISB' ");				
			
			boolean more_pd = rs1.next(); 
			
			if(more_pc){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Disbursement </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('DISB')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			
			}
			else
			{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Disbursement </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('DISB')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT3' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			rs1= stmt1.executeQuery("SELECT  COUNT(*) "+//1
			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS  "+	
			" WHERE PAY_STATUS='CANCEL' ");				
			
			boolean more_pn = rs1.next(); 
			
			if(more_pn){
			
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Disapproved</td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('CANCEL')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT4' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");					
			}
			else{
			if(mflag){
			out.println("<tr class=tr_input>");
			mflag=false;
			}
			else{
			out.println("<tr class=tr_input1>");
			mflag=true;
			}
			out.println("<td width='30%' class=div_input>Payment Disapproved </td>");
			out.println("<td width='20%' align='right' class=div_input onClick=\"show_payment_detail_report('CANCEL')\" style='cursor:hand' ><u>0</u></td>");
			out.println("<td width='20%' align='center' class=div_input onClick=\"\" style='cursor:hand' ><input class='but_input' type='button' name='BUT_PAY_EDIT4' value=\"Edit\" onClick=\"edit_payment()\" disabled ></td>");
			out.println("</tr>");										
			}
			
			out.println("</table>");
			
			//  ------------------REPORT 12 END -------------------------------------------------------------
			
			out.println("</td>");										
			out.println("</tr>");										
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



