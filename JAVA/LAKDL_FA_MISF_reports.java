import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007
// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_MISF_reports extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
  
 
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String header_name=m_sn_methods.header_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
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
			stmt2=conn.createStatement();
			stmt3=conn.createStatement();
			stmt4=conn.createStatement();
			stmt5=conn.createStatement();
			stmt6=conn.createStatement();
			stmt7=conn.createStatement();
			stmt8=conn.createStatement();
			stmt9=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_PD_CHEQUE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_pd_status=req.getParameter("pd_status");
				
			//--------------------------PD CHEQUE REPORT - 1 -------------------------------
			
			
					rs1= stmt1.executeQuery(" SELECT DISTINCT"+
					  "  A.POD_REF_NO, "+//1
					  "  NVL(A.PAYER_BRANCH_CODE,'-'), "+//2
					  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+//3
					  "  NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'-'), "+//4
					  "  NVL(A.CHEQUE_NO,'-'),"+//5
					  "  NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//6
					  "  NVL(A.CHEQUE_AMOUNT,0),"+//7
					  "  NVL(B.CLIENT_CODE,'-'),"+//8
					  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'),"+//9
					  "  NVL(B.FACILITY_NO,'-'),"+//10
					  "  NVL(B.DEBTOR_CODE,'-'),"+//11
					  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//12
					  "  NVL(DECODE(A.POD_STATUS,'N','PD Cheque Available','Y','PD Cheque Realised','C','PD Cheque Dishonored'),'-') "+//13
					 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
					 " WHERE  A.POD_REF_NO=B.POD_REF_NO(+) "+
					 " AND TO_DATE(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					 " TO_DATE(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " AND UPPER(B.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
					 " AND UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					 " AND UPPER(B.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
					 " AND UPPER(A.POD_STATUS) LIKE UPPER('"+m_pd_status+"%') ");
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Cheque Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Cheque Report </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Bank Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Branch</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>PD Status</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(10)+"')\" style='cursor:hand'><u>"+rs1.getString(10)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(8)+"')\" style='cursor:hand'><u>"+rs1.getString(9)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(11)+"')\" style='cursor:hand'><u>"+rs1.getString(12)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }		
			/*else if(m_chksql.equals("LOAD_PD_CHEQUE_INVOICE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");		
			
				rs1= stmt1.executeQuery(" SELECT A.POD_REF_NO,"+//1
				" A.CLIENT_CODE,"+//2
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//3
				" A.FACILITY_NO,"+//4
				" A.BATCH_NO,"+//5
				" A.DEBTOR_CODE,"+//6
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//7
				" A.INVOICE_NO,"+//8
				" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'), "+//9
				" B.CHEQUE_NO,"+//10
				" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//11
				" TO_CHAR(B.PD_REALISE_DATE,'DD-MM-YYYY'),"+//12
				" B.CHEQUE_AMOUNT,"+//13
				" DECODE(B.POD_STATUS,'Y','REALISE','N','NOT REALISE','C','CANCEL'),"+//14
				" C.INVOICE_AMOUNT,"+//15
				" C.BALANCE_AMOUNT,"+//16
				" TO_CHAR(C.TOLARENCE_END_DATE,'DD-MM-YYYY') "+//17
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
				" WHERE A.POD_REF_NO=B.POD_REF_NO "+
				" AND A.CLIENT_CODE=C.CLIENT_CODE "+
				" AND A.FACILITY_NO=C.FACILITY_NO "+
				" AND A.BATCH_NO=C.BATCH_NO "+
				" AND A.INVOICE_NO=C.INVOICE_NO "+
				" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
				" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
				" AND UPPER(A.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
				" ORDER BY A.FACILITY_NO,B.CHEQUE_NO,A.DEBTOR_CODE,A.BATCH_NO,B.CHEQUE_DATE ");
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Cheque Report - Invoice </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Cheque Report - Invoice Assign</B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>PD Realise Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>PD Status</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>PD Amount</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Bal. Amount</b></DIV></td>"); 
						out.println("</tr>"); 
					
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(12)+"</td>");
							//out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");//Commented by Dineth on 2008-09-04
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");//Added by Dineth on 2008-09-04

							out.println("<td width='8%' class=div_input >"+rs1.getString(14)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(13))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(17)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(15))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(16))+"</td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    }*/
			// Added by Dineth on 2008-09-20
			else if(m_chksql.equals("LOAD_PD_CHEQUE_INVOICE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");		
			
				rs1= stmt1.executeQuery(" SELECT A.POD_REF_NO,"+//1
				" A.CLIENT_CODE,"+//2
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//3
				" A.FACILITY_NO,"+//4
				" A.BATCH_NO,"+//5
				" A.DEBTOR_CODE,"+//6
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//7
				" A.INVOICE_NO,"+//8
				" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(B.PAYER_BRANCH_CODE),'-'), "+//9
				" B.CHEQUE_NO,"+//10
				" TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//11
				" TO_CHAR(B.PD_REALISE_DATE,'DD-MM-YYYY'),"+//12
				" B.CHEQUE_AMOUNT,"+//13
				" DECODE(B.POD_STATUS,'Y','REALISE','N','NOT REALISE','C','CANCEL'),"+//14
				" C.INVOICE_AMOUNT,"+//15
				" C.BALANCE_AMOUNT,"+//16
				" TO_CHAR(C.TOLARENCE_END_DATE,'DD-MM-YYYY') "+//17
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
				" WHERE A.POD_REF_NO=B.POD_REF_NO "+
				" AND A.CLIENT_CODE=C.CLIENT_CODE "+
				" AND A.FACILITY_NO=C.FACILITY_NO "+
				" AND A.BATCH_NO=C.BATCH_NO "+
				" AND A.INVOICE_NO=C.INVOICE_NO "+
				" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
				" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
				" AND UPPER(A.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
				//" ORDER BY A.FACILITY_NO,B.CHEQUE_NO,A.DEBTOR_CODE,A.BATCH_NO,B.CHEQUE_DATE ");
			  " ORDER BY A.INVOICE_NO,B.CHEQUE_NO ASC");
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Cheque Report - Invoice </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Cheque Report - Invoice Assign</B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>PD Realise Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>PD Status</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>PD Amount</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>");
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Bal. Amount</b></DIV></td>"); 
						out.println("</tr>"); 
					
					}
					
					while(more){
							double bal_amt=rs1.getDouble(15);
							String inv_no=rs1.getString(8);
							while(inv_no.equals(rs1.getString(8))){
							bal_amt-=rs1.getDouble(13);
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(12)+"</td>");
							//out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");//Commented by Dineth on 2008-09-04
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(4)+"')\" style='cursor:hand'><u>"+rs1.getString(4)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");//Added by Dineth on 2008-09-04

							out.println("<td width='8%' class=div_input >"+rs1.getString(14)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(13))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(6)+"')\" style='cursor:hand'><u>"+rs1.getString(7)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(17)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(15))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(bal_amt)+"</td>");
							out.println("</tr>");
							more = rs1.next();
							if(!more){
							break;
							}
							}
							
							
					}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    }
			
			else if(m_chksql.equals("LOAD_PD_CHEQUE_INVOICE_REPORT_UNASSIGN")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");		
			
				rs1= stmt1.executeQuery(" SELECT A.BATCH_NO, "+//1
				" A.DEBTOR_CODE, "+//2
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//3
				" A.INVOICE_NO,  "+//4
				" A.INVOICE_AMOUNT, "+//5
       	" TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//6
				" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+//7
				" TO_CHAR(A.TOLARENCE_END_DATE,'DD-MM-YYYY'), "+//8
       	" A.BALANCE_AMOUNT,"+//9
				" A.FACILITY_NO,"+//10
				" A.CLIENT_CODE,"+//11
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-') "+//12
 				" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
				" WHERE A.INVOICE_STATUS='CONF' "+
				" AND (A.CLIENT_CODE,A.INVOICE_NO) NOT IN (SELECT CLIENT_CODE,INVOICE_NO FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO) "+
				" AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
				" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
				" AND UPPER(A.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
				" ORDER BY A.FACILITY_NO,A.INVOICE_NO,A.INVOICE_DATE ");
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
				out.println("<HTML><HEAD><TITLE>PD Cheque Report - Invoice </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				//out.println("<BR>");
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> PD Cheque Report - Invoice Unassign</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
				}					
			  if(more){
					out.println("<table align='center' width='100%' class='table' >");						
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
					out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>"); 
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>"); 
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
					out.println("</tr>"); 				
				}
					
				while(more){
			
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(10)+"')\" style='cursor:hand'>"+rs1.getString(10)+"</td>");
						out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(11)+"')\" style='cursor:hand'>"+rs1.getString(12)+"</td>");
						out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand'>"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(1)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
						out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(5))+"</td>");
						out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(9))+"</td>");
						out.println("</tr>");
						more = rs1.next();
				}	
  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
	    }
			
			else if(m_chksql.equals("LOAD_SETTLE_RECEIPT_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_coll_officer=req.getParameter("coll_officer");
				String m_settle_type=req.getParameter("settle_type");//
				String m_settle_mode=req.getParameter("settle_mode");
				
			//--------------------------SETTLE RECEIPT REPORT - 2 -------------------------------		
				if(m_settle_type.equals("VD")){				
					rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL(ALLO_AMOUNT,0), "+
					"  NVL(BALANCE_AMOUNT,0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(COLL_OFFICER) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY EFF_VALDATE "); 
				}
				else if(m_settle_type.equals("CD")){
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL(ALLO_AMOUNT,0), "+
					"  NVL(BALANCE_AMOUNT,0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(COLL_OFFICER) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY CHEQUE_DATE "); 
				}
				else if(m_settle_type.equals("RD")){
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL(ALLO_AMOUNT,0), "+
					"  NVL(BALANCE_AMOUNT,0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(COLL_OFFICER) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY RECON_DATE "); 
				}
				else if(m_settle_type.equals("BD")){
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL(ALLO_AMOUNT,0), "+
					"  NVL(BALANCE_AMOUNT,0) "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(COLL_OFFICER) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY BANK_DATE "); 
				}
				
				boolean mflag=true;							
				String client_code = "";
				boolean more = rs1.next();
				
				out.println("<HTML><HEAD><TITLE>Settlement Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Settlement Receipt Report </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	
				if(!more){
				out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
				out.println("</TABLE>");
				}					
				if(more){
				out.println("<table align='center' width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
				out.println("<td width='1%'></td>"); 
				out.println("<td width='12%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
				out.println("<td width='10%' ><DIV align='right' class=div_input><b>Settle Amout</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Value Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Realise Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Bank Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
				out.println("<td width='8%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' ><DIV class=div_input><b>Receipt Status</b></DIV></td>"); 
				out.println("<td width='10%' ><DIV class=div_input><b>Allocated Amount</b></DIV></td>"); 
				out.println("<td width='10%' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
				out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
				out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
				out.println("</tr>"); 
				}
				
				while(more){
					if(mflag){
					out.println("<tr class=tr_input>");
					mflag=false;
					}
					else{
					out.println("<tr class=tr_input1>");
					mflag=true;
					}
					out.println("<td width='1%'></td>"); 
					out.println("<td width='12%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
					out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(2))+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(11)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(12)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(10)+"</td>");
					out.println("<td width='8%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input >"+rs1.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input >"+nf.format(rs1.getDouble(14))+"</td>");
					out.println("<td width='10%' class=div_input >"+nf.format(rs1.getDouble(15))+"</td>");
					out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u></td>");
					out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(9)+"')\" style='cursor:hand'><u>"+rs1.getString(9)+"</u></td>");
					out.println("</tr>");
					more = rs1.next();
				}	
  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
	    }
			else if(m_chksql.equals("LOAD_SETTLE_RECEIPT_LETTER")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_coll_officer=req.getParameter("coll_officer");
				String m_settle_type=req.getParameter("settle_type");//
				String m_settle_mode=req.getParameter("settle_mode");
				
				out.println("<HTML><HEAD><TITLE>Settlement Receipt Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 
				
				String m_today="";
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				
				rs2 = stmt2.executeQuery (" SELECT "+
					  " CLIENT_CODE, "+//1
					  " FULL_NAME, "+//2
					  " NVL(REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-')), "+//5
						" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
					  " WHERE CLIENT_CODE='"+m_client_code+"' ");
	
				if(rs2.next()){
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs2.getString(5)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+m_today+"</td></tr>");	
						out.println("</table></blockquote>");
				}
				
				out.println("<blockquote><TABLE  WIDTH='100%'>");
				out.println("<TR><TD align='Center' class='factoring-letter-body'><B><U>Report on Realized and Returned Cheques</U></B></TD></TR>");
				out.println("</TABLE></blockquote>");
				out.println("<BR>");	
								
			//--------------------------SETTLE RECEIPT REPORT - 21 -------------------------------		
				if(m_settle_type.equals("VD")){				
					rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realized'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//14
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//15
					"  NVL("+m_schema_name+".FA_GET_RETURN_CHEQUE_COMM(RECEIPT_NO),'-') "+//16
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_STATUS IN('C','Y') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(NVL(DEBTOR_CODE,' ')) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(NVL(COLL_OFFICER,' ')) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY REC_STATUS,RECEIPT_NO "); 
				}
				else if(m_settle_type.equals("CD")){
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//14
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//15
					"  NVL("+m_schema_name+".FA_GET_RETURN_CHEQUE_COMM(RECEIPT_NO),'-') "+//16
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_STATUS IN('C','Y') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(NVL(DEBTOR_CODE,' ')) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(NVL(COLL_OFFICER,' ')) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY REC_STATUS,RECEIPT_NO "); 
				}
				else if(m_settle_type.equals("RD")){
				
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//14
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//15
					"  NVL("+m_schema_name+".FA_GET_RETURN_CHEQUE_COMM(RECEIPT_NO),'-') "+//16
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_STATUS IN('C','Y') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(NVL(DEBTOR_CODE,' ')) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(NVL(COLL_OFFICER,' ')) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY REC_STATUS,RECEIPT_NO "); 
				}
				else if(m_settle_type.equals("BD")){
				rs1= stmt1.executeQuery(" SELECT "+
					"  RECEIPT_NO,"+//1
					"  NVL(REC_AMOUNT,0),"+//2
					"  NVL(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//3
					"  NVL(DECODE(SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//4
					"  NVL(DECODE(RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),    "+//5
					"  NVL(DECODE(REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//6
					"  NVL(CLIENT_CODE,'-'),"+//7
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'),"+//8
					"  NVL(FACILITY_NO,'-'),"+//9
					"  NVL(CHEQUE_NO,SETTLE_MODE), "+//10
					"  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//11
					"  NVL(TO_CHAR(RECON_DATE,'DD-MM-YYYY'),'-'), "+//12
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-'), "+//13
					"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//14
					"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'), "+//15
					"  NVL("+m_schema_name+".FA_GET_RETURN_CHEQUE_COMM(RECEIPT_NO),'-') "+//16
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND REC_STATUS IN('C','Y') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(NVL(DEBTOR_CODE,' ')) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND UPPER(NVL(COLL_OFFICER,' ')) LIKE UPPER('"+m_coll_officer+"%') "+ 
					" AND UPPER(SETTLE_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
					" ORDER BY REC_STATUS,RECEIPT_NO "); 
				}
				
				boolean mflag=true;							
				String client_code = "";
				boolean more = rs1.next();
				
				out.println("<blockquote>");
				
				if(more){
				out.println("<table width='100%' class='table' >");						
				out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class='factoring-letter-body'><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='15%' class='factoring-letter-body'><DIV class=div_input><b>Bank/Branch Name</b></DIV></td>");
				out.println("<td width='10%' class='factoring-letter-body'><DIV class=div_input><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' class='factoring-letter-body'><DIV align='right' class=div_input><b>Cheque Amout</b></DIV></td>");
				//out.println("<td width='10%' class='factoring-letter-body'><DIV class=div_input><b>Value Date</b></DIV></td>");
				//out.println("<td width='10%' class='factoring-letter-body'><DIV class=div_input><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' class='factoring-letter-body' align='center'><DIV class=div_input><b>Realise Date</b></DIV></td>");
				//out.println("<td width='10%' class='factoring-letter-body'><DIV class=div_input><b>Bank Date</b></DIV></td>");				
				//out.println("<td width='8%' class='factoring-letter-body'><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
				out.println("<td width='15%' class='factoring-letter-body'><DIV class=div_input><b>Receipt Status</b></DIV></td>"); 
				out.println("<td width='15%' class='factoring-letter-body'><DIV class=div_input><b>Return Comments</b></DIV></td>");
				out.println("</tr>"); 
				}
				
				while(more){
					out.println("<tr class=tr_input>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class='factoring-letter-body'>"+rs1.getString(14)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body'>"+rs1.getString(15)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body'>"+rs1.getString(10)+"</td>");
					out.println("<td width='10%' align='right' class='factoring-letter-body'>"+nf.format(rs1.getDouble(2))+"</td>");
					//out.println("<td width='10%' class='factoring-letter-body'>"+rs1.getString(3)+"</td>");
					//out.println("<td width='10%' class='factoring-letter-body'>"+rs1.getString(11)+"</td>");
					out.println("<td width='10%' class='factoring-letter-body' align='center'>"+rs1.getString(12)+"</td>");
					//out.println("<td width='10%' class='factoring-letter-body'>"+rs1.getString(13)+"</td>");
					//out.println("<td width='8%' class='factoring-letter-body'>"+rs1.getString(4)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body'>"+rs1.getString(6)+"</td>");
					out.println("<td width='15%' class='factoring-letter-body'>"+rs1.getString(16)+"</td>");
					out.println("</tr>");
					more = rs1.next();
				}	
  	 		out.println("</table>");
				out.println("</blockquote>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
	    }
			else if(m_chksql.equals("LOAD_DEPOSIT_SETTLE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_branch_code=req.getParameter("branch_code");
				String m_settle_mode=req.getParameter("settle_mode");
				
			//--------------------------DEPOSIT SETTLE REPORT - 3 -------------------------------

			
							rs1= stmt1.executeQuery(" SELECT "+
							  "  A.DEPOSIT_NO,"+//1
							  "  NVL(TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'),'-'),"+//2
							  "  NVL(A.BRANCH_CODE,'-'),"+//3
							  "  NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.BRANCH_CODE),'-'),"+//4
							  "  NVL(B.RECEIPT_NO,'-'),"+//5
							  "  NVL(B.DEPOSIT_AMOUNT,0),"+//6
							  "  NVL(TO_CHAR(C.EFF_VALDATE,'DD-MM-YYYY'),'-'),"+//7
							  "  NVL(DECODE(A.DEPOSIT_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'),"+//8
							  "  NVL(DECODE(C.RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'),"+//9
							  "  NVL(DECODE(C.REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'),"+//10
							  "  NVL(C.CLIENT_CODE,'-'),"+//11
							  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.CLIENT_CODE),'-'),"+//12
							  "  NVL(C.FACILITY_NO,'-'),"+//13
							  "  NVL(C.DEBTOR_CODE,'-'),"+//14
							  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(C.DEBTOR_CODE),'-'),    "+//15
							  "  NVL(C.INVOICE_NO,'-') "+//16
							 " FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT A, "+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS B, "+
							 " "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT C "+
							 " WHERE A.DEPOSIT_NO=B.DEPOSIT_NO(+) "+
							 " AND B.RECEIPT_NO=C.RECEIPT_NO(+)  "+
							 " AND TO_DATE(TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
							 " AND TO_DATE(TO_CHAR(A.DEPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							 " AND UPPER(C.FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
							 " AND UPPER(C.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
							 " AND UPPER(NVL(C.DEBTOR_CODE,'-')) LIKE UPPER('"+m_debtor_code+"%') "+
							 " AND UPPER(A.DEPOSIT_MODE) LIKE UPPER('"+m_settle_mode+"%') "+
							 " AND UPPER(A.BRANCH_CODE) LIKE UPPER('"+m_branch_code+"%') ");
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Deposit Settlement Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>Deposit Settlement Report  </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Deposit No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Date</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Bank</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='12%' align='right' ><DIV class=div_input><b>Deposit Amount</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>EFF Val. Date</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Settle Type</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Receipt Status</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					
					}
					
					while(more){
				
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_deposit_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='10%'  class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(5)+"')\" style='cursor:hand' >"+rs1.getString(5)+"</td>");
							out.println("<td width='12%' align='right' class=div_input >"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='12%' class=div_input>"+rs1.getString(8)+"</td>");
							out.println("<td width='10%' class=div_input>"+rs1.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input>"+rs1.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(11)+"')\" style='cursor:hand'><u>"+rs1.getString(12)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(13)+"')\" style='cursor:hand'><u>"+rs1.getString(13)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(14)+"')\" style='cursor:hand'><u>"+rs1.getString(15)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(14)+"','"+rs1.getString(16)+"')\" style='cursor:hand'><u>"+rs1.getString(16)+"</u></td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    } 
			else if(m_chksql.equals("LOAD_INVOICE_PORTFOLIO_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_settle_ratio=req.getParameter("settle_ratio");
				
			//-------------------------- INVOICE PORTFOLIO REPORT -  -------------------------------
					rs1= stmt1.executeQuery(" SELECT "+
					" B.CLIENT_CODE, "+//1
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'), "+//2
					" NVL(B.FACILITY_NO,'-'), "+//3
					" NVL(A.DEBTOR_CODE,'-'), "+//4
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+//5
					" NVL(A.BATCH_NO,'-'), "+//6
					" NVL(A.INVOICE_NO,'-'), "+//7
					" NVL(TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),'-'), "+//8
					" NVL(TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'),'-'), "+//9
					" NVL(TO_CHAR(A.TOLARENCE_END_DATE,'DD-MM-YYYY'),'-'), "+//10
					" NVL(A.INVOICE_AMOUNT,0), "+//11
					" NVL(A.NET_INVOICE_AMOUNT,0), "+//12
					" NVL(A.ADJUSTMENT_AMOUNT*-1,0), "+//13
					" NVL(A.BALANCE_AMOUNT,0), "+//14
					" "+m_schema_name+".FA_GET_INVOICE_POD(A.INVOICE_SEQ_NO), "+//15
					" NVL(A.SETTLE_AMOUNT,0) "+//16
					" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A,"+m_schema_name+".FA_CR_PRO_INVOICE B "+
					" WHERE "+
					//" TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					//" AND TO_DATE(TO_CHAR(B.INVOICE_BATCH_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" A.BATCH_NO=B.BATCH_NO "+
					" AND UPPER(B.FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(B.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
					" AND UPPER(NVL(A.DEBTOR_CODE,'-')) LIKE UPPER('"+m_debtor_code+"%') "+
					" AND ((A.SETTLE_AMOUNT/A.INVOICE_AMOUNT)*100) >=TO_NUMBER("+m_settle_ratio+") "+
					" ORDER BY B.CLIENT_CODE,B.FACILITY_NO,A.DEBTOR_CODE,A.BATCH_NO,A.INVOICE_NO ");
					
					boolean mflag=true;							
					boolean mflag2=false;							
					String client_code = "";
					String client_code_temp = "";	
					
					double m_tot_invoice=0,m_tot_net=0,m_tot_adj=0,m_tot_bal=0,m_tot_settle=0;
					double m_tot_invoice_g=0,m_tot_net_g=0,m_tot_adj_g=0,m_tot_bal_g=0,m_tot_settle_g=0;	
					double m_grand_tot=0;	
					
					 boolean more = rs1.next();  
						
					 out.println("<HTML><HEAD><TITLE>Invoice Portfolio Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>Invoice Portfolio Report  </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
					  client_code=rs1.getString(1);
					  mflag2=true;							
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='40%' onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
						out.println("<td width='20%' onClick=\"show_facility('"+rs1.getString(3)+"')\" style='cursor:hand' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input></DIV></td>");
						out.println("</tr>");	
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Due Date</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Adjust Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Net Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
						out.println("<td width='8%' align='right' ><DIV class=div_input>POD Status</DIV></td>"); 
						out.println("</tr>"); 
					}
							
					while(more){
				      client_code_temp=rs1.getString(1);
							if(client_code.equals(client_code_temp)){
									m_tot_invoice=m_tot_invoice + rs1.getDouble(11);
									m_tot_net=m_tot_net + rs1.getDouble(12);
									m_tot_adj=m_tot_adj + rs1.getDouble(13);
									m_tot_bal=m_tot_bal + rs1.getDouble(14);
									m_tot_settle=m_tot_settle+rs1.getDouble(16);
									m_tot_invoice_g=m_tot_invoice_g + rs1.getDouble(11);
									m_tot_net_g=m_tot_net_g + rs1.getDouble(12);
									m_tot_adj_g=m_tot_adj_g + rs1.getDouble(13);
									m_tot_bal_g=m_tot_bal_g + rs1.getDouble(14);
									m_tot_settle_g=m_tot_settle_g+rs1.getDouble(16);
							}
							else{
									out.println("<tr class=pdn_txtpos2>");
									out.println("<td width='10%' ><DIV class=div_input></DIV></td>");
									out.println("<td width='10%' ><DIV class=div_input></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input></DIV></td>");
									out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
									out.println("<td width='8%' align='right'><DIV class=div_input><b>Sub Total</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_invoice)+"</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_adj)+"</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_net)+"</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_settle)+"</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_bal)+"</b></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
									out.println("</tr>");	
									out.println("</table>");
									out.println("<br><br>");
									out.println("<table align='center' width='100%' class='table' >");
									out.println("<tr class=pdn_txtpos2>");
									out.println("<td width='40%' onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand'><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
									out.println("<td width='20%' onClick=\"show_facility('"+rs1.getString(3)+"')\" style='cursor:hand' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
									out.println("<td width='*%' ><DIV class=div_input></DIV></td>");
									out.println("</tr>");	
									out.println("</table>");
									out.println("<table align='center' width='100%' class='table' >");						
									out.println("<tr class=pdn_txtpos2>");
									out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
									out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
									out.println("<td width='8%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input><b>Due Date</b></DIV></td>"); 
									out.println("<td width='8%' ><DIV class=div_input><b>Tol. Date</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>Adjust Amount</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>Net Amount</b></DIV></td>"); 
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>Settle Amount</b></DIV></td>");
									out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
									out.println("<td width='8%' align='right' ><DIV class=div_input>POD Status</DIV></td>"); 
									out.println("</tr>"); 
									client_code=rs1.getString(1);
									m_tot_invoice=0;
									m_tot_net=0;
									m_tot_adj=0;
									m_tot_bal=0;
									m_tot_settle=0;
									m_tot_invoice=m_tot_invoice + rs1.getDouble(11);
									m_tot_net=m_tot_net + rs1.getDouble(12);
									m_tot_adj=m_tot_adj + rs1.getDouble(13);
									m_tot_bal=m_tot_bal + rs1.getDouble(14);
									m_tot_settle=m_tot_settle+rs1.getDouble(16);
									m_tot_invoice_g=m_tot_invoice_g + rs1.getDouble(11);
									m_tot_net_g=m_tot_net_g + rs1.getDouble(12);
									m_tot_adj_g=m_tot_adj_g + rs1.getDouble(13);
									m_tot_bal_g=m_tot_bal_g + rs1.getDouble(14);
									m_tot_settle_g=m_tot_settle_g+rs1.getDouble(16);
							}
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}

							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(4)+"')\" style='cursor:hand' ><u>"+rs1.getString(5)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_batch_details('"+rs1.getString(6)+"')\" style='cursor:hand' ><u>"+rs1.getString(6)+"</u></td>");
							out.println("<td width='8%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(4)+"','"+rs1.getString(7)+"')\" style='cursor:hand' ><u>"+rs1.getString(7)+"</u></td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(8)+"</td>");
							out.println("<td width='8%' class=div_input>"+rs1.getString(9)+"</td>");
							out.println("<td width='8%' class=div_input>"+rs1.getString(10)+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(11))+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(13))+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(12))+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(16))+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(14))+"</td>");
							out.println("<td width='8%' align='right'  class=div_input>"+rs1.getString(15)+"</td>");
							out.println("</tr>");
							more = rs1.next();
					}	
						 
					if(mflag2){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' align='right'><DIV class=div_input><b>Sub Total</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_invoice)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_adj)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_net)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_settle)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_bal)+"</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("</tr>");	
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("<td width='8%' align='right'><DIV class=div_input><b>Grand Total</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_invoice_g)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_adj_g)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_net_g)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_settle_g)+"</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>"+nf.format(m_tot_bal_g)+"</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input></DIV></td>"); 
						out.println("</tr>");	
					}
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    }
			else if(m_chksql.equals("LOAD_RETURN_SETTLE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_code=req.getParameter("facility_no");
				
				out.println("<HTML><HEAD><TITLE>Cheques in Hand Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B>Cheques in Hand Report for "+m_facility_code+" Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total unbanked Cheques in Hand</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Ref No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Cheque Amount</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Bank Name</b></DIV></td>");
				out.println("</tr>");
			
				rs1= stmt1.executeQuery(" SELECT REF_NO,CNAME,CHQ_NO,TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),CHQ_AMT,BR_NAME "+
				" FROM ( "+
				" SELECT RECEIPT_NO REF_NO, "+
				" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME,"+
				" CHEQUE_NO CHQ_NO,"+
				" REC_AMOUNT CHQ_AMT, "+
				" CHEQUE_DATE CHEQUE_DATE, "+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE) BR_NAME "+
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				" WHERE REC_STATUS='E' "+
				" AND SETTLE_MODE='CHEQUE' "+
				" AND FACILITY_NO='"+m_facility_code+"' "+
				//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" UNION ALL "+
				" SELECT DISTINCT A.POD_REF_NO REF_NO,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) CNAME, "+
				" A.CHEQUE_NO CHQ_NO, "+
				" A.CHEQUE_AMOUNT CHQ_AMT, "+
				" A.CHEQUE_DATE CHEQUE_DATE,"+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE) BR_NAME "+
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
				" WHERE A.POD_REF_NO=B.POD_REF_NO "+
				" AND A.POD_STATUS='N' "+
				" AND B.FACILITY_NO='"+m_facility_code+"' "+
				//" AND TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				//" AND TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ) "+
				" ORDER BY CHEQUE_DATE ");
				
				int j=1;
				double m_temp1=0;
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_temp1=m_temp1+rs1.getDouble(5);
					out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><B>Total</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp1)+"</B></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				out.println("<p class=pdn_txtpos2><center><u><b>Total Cheques Bank pending Realisation</b></u></center></p>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Ref No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Bank Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=div_input><b>Cheque Amount</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Bank Name</b></DIV></td>");
				out.println("</tr>");
				
				j=1;
				
				rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE)),"+//2
				" CHEQUE_NO,"+//3
				" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+//4
				" TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+//5
				" REC_AMOUNT, "+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				" WHERE "+
				" FACILITY_NO='"+m_facility_code+"' "+
				" AND SETTLE_MODE='CHEQUE' "+
				" AND REC_STATUS='B' "+
				" AND TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY BANK_DATE ");
					
				double m_temp2=0;
				while(rs1.next()){
					if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}
					m_temp2=m_temp2+rs1.getDouble(6);
					out.println("<td width='15%' class=div_input >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
					out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				out.println("<td width='15%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='10%' class=div_input ><B>Total</B></td>");
				out.println("<td width='10%' class=div_input align=right><B>"+nf.format(m_temp2)+"</B></td>");
				out.println("<td width='10%' class=div_input ></td>");
				out.println("</tr>");
				out.println("</table>");
								
  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
	    }
			else if(m_chksql.equals("LOAD_RETURN_SETTLE_LETTER_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_code=req.getParameter("facility_no");
				
				out.println("<HTML><HEAD><TITLE>Cheques in Hand Report</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				String m_today="";
				
				rs1 = stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL ");
				if(rs1.next()){
					m_today=rs1.getString(1);
				}
				
				rs1 = stmt1.executeQuery (" SELECT "+
					  " A.CLIENT_CODE, "+//1
					  " A.FULL_NAME, "+//2
					  " NVL(A.REGISTERED_ADDRESS1,'-'), "+//3
					  " NVL(A.REGISTERED_ADDRESS2,'-'), "+//4
					  " INITCAP(NVL("+m_schema_name+".AF_CO_GET_CITY_NAME(A.CITY_CODE),'-')), "+//5
						" NVL(A.REGISTERED_CONTACT_PERSON,'-'),"+//6
	    			" NVL(A.DESIGNATION_PAYMENT,'-') "+//7
					  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY B "+
					  " WHERE B.FACILITY_NO='"+m_facility_code+"' "+
						" AND A.CLIENT_CODE=B.CLIENT_CODE ");
	
				if(rs1.next()){
						out.println("<blockquote>");
						out.println("<table border='0' width='100%' class=table >");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(6)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(7)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(2)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(3)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(4)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+rs1.getString(5)+"</td></tr>");
						out.println("<tr><td width='*%' class='factoring-letter-body'>"+m_today+"</td></tr>");	
						out.println("</table></blockquote>");
				}
				out.println("<blockquote>");
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Cheques in Hand Report for "+m_facility_code+" Period "+m_from_date+" - "+m_to_date+"</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<p class=factoring-letter-body><center><u><b>Total unbanked Cheques in Hand</b></u></center></p>");
				out.println("<br>");
				out.println("<table width='70%' class='table' border='1' cellpadding='1' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				//out.println("<td width='15%' ><DIV class=factoring-letter-body><b>Ref No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Cheque Amount</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Bank Name</b></DIV></td>");
				out.println("</tr>");
			
				rs1= stmt1.executeQuery(" SELECT REF_NO,CNAME,CHQ_NO,TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),CHQ_AMT,BR_NAME "+
				" FROM ( "+
				" SELECT RECEIPT_NO REF_NO, "+
				" DECODE(RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) CNAME,"+
				" CHEQUE_NO CHQ_NO,"+
				" REC_AMOUNT CHQ_AMT, "+
				" CHEQUE_DATE CHEQUE_DATE, "+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE) BR_NAME "+
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				" WHERE REC_STATUS='E' "+
				" AND SETTLE_MODE='CHEQUE' "+
				" AND FACILITY_NO='"+m_facility_code+"' "+
				//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				//" AND TO_DATE(TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" UNION ALL "+
				" SELECT DISTINCT A.POD_REF_NO REF_NO,"+
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE) CNAME, "+
				" A.CHEQUE_NO CHQ_NO, "+
				" A.CHEQUE_AMOUNT CHQ_AMT, "+
				" A.CHEQUE_DATE CHEQUE_DATE,"+
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE) BR_NAME "+
				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
				" WHERE A.POD_REF_NO=B.POD_REF_NO "+
				" AND A.POD_STATUS='N' "+
				" AND B.FACILITY_NO='"+m_facility_code+"' "+
				//" AND TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				//" AND TO_DATE(TO_CHAR(A.PD_REALISE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ) "+
				" ORDER BY CHEQUE_DATE ");
				
				int j=1;
				double m_temp1=0;
				while(rs1.next()){
					/*if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}*/
					out.println("<tr>");
					m_temp1=m_temp1+rs1.getDouble(5);
					//out.println("<td width='15%' class=factoring-letter-body >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(5))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(6)+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				//out.println("<td width='15%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ><B>Total</B></td>");
				out.println("<td width='10%' class=factoring-letter-body align=right><B>"+nf.format(m_temp1)+"</B></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<br>");
				out.println("<p class=factoring-letter-body><center><u><b>Total Cheques Bank pending Realisation</b></u></center></p>");
				out.println("<br>");
				out.println("<table width='80%' class='table' border='1' cellpadding='1' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				//out.println("<td width='15%' ><DIV class=factoring-letter-body><b>Ref No</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Client/Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Cheque No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Cheque Date</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=factoring-letter-body><b>Bank Date</b></DIV></td>");
				out.println("<td width='10%' align=right><DIV class=factoring-letter-body><b>Cheque Amount</b></DIV></td>");
				out.println("<td width='20%' ><DIV class=factoring-letter-body><b>Bank Name</b></DIV></td>");
				out.println("</tr>");
				
				j=1;
				
				rs1= stmt1.executeQuery(" SELECT RECEIPT_NO,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DECODE(RECEIPT_TYPE,'CS',CLIENT_CODE,DEBTOR_CODE)),"+//2
				" CHEQUE_NO,"+//3
				" TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'), "+//4
				" TO_CHAR(BANK_DATE,'DD-MM-YYYY'), "+//5
				" REC_AMOUNT, "+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE) "+//7
				" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				" WHERE "+
				" FACILITY_NO='"+m_facility_code+"' "+
				" AND SETTLE_MODE='CHEQUE' "+
				" AND REC_STATUS='B' "+
				" AND TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND TO_DATE(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" ORDER BY BANK_DATE ");
					
				double m_temp2=0;
				while(rs1.next()){
					/*if(j==0){
						out.println("<tr bgcolor=\"#FFFFFF\">");
						j=1;
					}
					else{
						out.println("<tr bgcolor=\"#C0C0C0\" >");
						j=0;
					}*/
					out.println("<tr>");
					m_temp2=m_temp2+rs1.getDouble(6);
					//out.println("<td width='15%' class=factoring-letter-body >"+rs1.getString(1)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(2)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(3)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(4)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(5)+"</td>");
					out.println("<td width='10%' class=factoring-letter-body align=right>"+nf.format(rs1.getDouble(6))+"</td>");
					out.println("<td width='10%' class=factoring-letter-body >"+rs1.getString(7)+"</td>");
					out.println("</tr>");
				}
				out.println("<tr>");
				//out.println("<td width='15%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("<td width='10%' class=factoring-letter-body ><B>Total</B></td>");
				out.println("<td width='10%' class=factoring-letter-body align=right><B>"+nf.format(m_temp2)+"</B></td>");
				out.println("<td width='10%' class=factoring-letter-body ></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</blockquote>");
  	 		out.println("</table>");
	  		out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
	    }
			else if(m_chksql.equals("LOAD_REALISE_SETTLE_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");
				String m_branch_code=req.getParameter("branch_code");
				String m_settle_mode=req.getParameter("settle_mode");
				
			//--------------------------REALISE SETTLE REPORT -  -------------------------------

			
						rs1= stmt1.executeQuery(" SELECT "+
						   " TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'), "+//1
						   " NVL(B.DEPOSIT_NO,'-'), "+//2
						   " NVL(TO_CHAR(C.DEPOSIT_DATE,'DD-MM-YYYY'),'-'), "+//3
						   " NVL(C.BRANCH_CODE,'-'), "+//4
						   " NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(C.BRANCH_CODE),'-'), "+//5
						   " NVL(A.RECEIPT_NO,'-'), "+//6
						   " NVL(A.REC_AMOUNT,0), "+//7
						   " NVL(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'-'), "+//8
						   " NVL(DECODE(A.SETTLE_MODE,'CASH','Cash','CHEQUE','Cheque','BANKTR','Bank Transfer'),'-'), "+//9
						   " NVL(DECODE(A.RECEIPT_TYPE,'CS','Client wise Settlements','IS','Invoice wise Settlements','Debtor wise Settlements'),'-'), "+//10
						   " NVL(DECODE(A.REC_STATUS,'E','Entered','N','Receipt to be Deposited','B','Receipt Deposited','C','Receipt Return','Y','Receipt Realised'),'-'), "+//11
						   " NVL(A.CLIENT_CODE,'-'), "+//12
						   " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), "+//13
						   " NVL(A.FACILITY_NO,'-'), "+//14
						   " NVL(A.DEBTOR_CODE,'-'), "+//15
						   " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+//16
						   " NVL(A.INVOICE_NO,'-')  "+//17   
						" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT A ,"+m_schema_name+".FA_OP_PRO_DEPOSIT_DETAILS B, "+
						" "+m_schema_name+".FA_OP_PRO_DEPOSIT C "+
						" WHERE TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
						" TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.RECEIPT_NO=B.RECEIPT_NO(+) "+
						" AND B.DEPOSIT_NO=C.DEPOSIT_NO(+) "+
						" AND NVL(A.CLIENT_CODE,'-') LIKE UPPER('"+m_client_code+"%') "+
						" AND NVL(A.FACILITY_NO,'-') LIKE UPPER('"+m_facility_no+"%') "+
						" AND NVL(A.DEBTOR_CODE,'-') LIKE UPPER('"+m_debtor_code+"%') "+
						" AND NVL(A.SETTLE_MODE,'-') LIKE UPPER('"+m_settle_mode+"%') "+
						" AND NVL(C.BRANCH_CODE,'-') LIKE UPPER('"+m_branch_code+"%') ");

		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Realise Settlements Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>Realise Settlements Report  </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
							if(!more){
								out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
								out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
								out.println("</TABLE>");
							}					
						  if(more){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
								out.println("<td width='1%'></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Realise Date</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Deposit No</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Deposit Date</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Deposit Branch</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
								out.println("<td width='12%' align='right' ><DIV class=div_input><b>Settle Amount</b></DIV></td>"); 
								out.println("<td width='8%' ><DIV class=div_input><b>EFF Val. Date</b></DIV></td>"); 
								out.println("<td width='12%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Settle Type</b></DIV></td>"); 
								out.println("<td width='12%' ><DIV class=div_input><b>Receipt Status</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
								out.println("</tr>"); 
							
							}
							
							while(more){
						
									if(mflag){
										out.println("<tr class=tr_input>");
										mflag=false;
									}
									else{
										out.println("<tr class=tr_input1>");
										mflag=true;
									}
									out.println("<td width='1%'></td>"); 
									out.println("<td width='10%' class=div_input >"+rs1.getString(1)+"</td>");
									out.println("<td width='10%'  class=div_input onClick=\"show_deposit_details('"+rs1.getString(2)+"')\" style='cursor:hand'  ><u>"+rs1.getString(2)+"</u></td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
									out.println("<td width='10%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(6)+"')\" style='cursor:hand' ><u>"+rs1.getString(6)+"</u></td>");
									out.println("<td width='12%' align='right' class=div_input >"+nf.format(rs1.getDouble(7))+"</td>");
									out.println("<td width='8%' class=div_input >"+rs1.getString(8)+"</td>");
									out.println("<td width='12%' class=div_input>"+rs1.getString(9)+"</td>");
									out.println("<td width='10%' class=div_input>"+rs1.getString(10)+"</td>");
									out.println("<td width='12%' class=div_input>"+rs1.getString(11)+"</td>");
									out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(12)+"')\" style='cursor:hand'><u>"+rs1.getString(13)+"</u></td>");
									out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(14)+"')\" style='cursor:hand'><u>"+rs1.getString(14)+"</u></td>");
									out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(15)+"')\" style='cursor:hand'><u>"+rs1.getString(16)+"</u></td>");
									out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(15)+"','"+rs1.getString(17)+"')\" style='cursor:hand'><u>"+rs1.getString(17)+"</u></td>");
									out.println("</tr>");
									more = rs1.next();
							}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_ACTIVE_DEBTOR_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				
				
			//--------------------------ACTIVE DEBTOR LIST REPORT -  -------------------------------
			
					// commented by udara somathilake on 17-11-2010
					/*
					rs1= stmt1.executeQuery("SELECT  A.FACILITY_NO, "+//1
					" NVL(A.CLIENT_CODE,'-'), "+//2
					" NVL(A.DEBTOR_CODE,'-'), "+//3
					" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+//4
					" NVL(A.MKT_CODE,'-'), "+//5
					" NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-'), "+//6
					" NVL(A.CREDIT_LIMIT,0), "+//7
					" NVL(A.CREDIT_PERIOD,0), "+//8
					" NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+//9
					" NVL(A.RESERVE_MARGIN,0), "+//10
					" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-'), "+//11
					" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-'), "+//12
					" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')), "+//13
					" DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved') "+//14
					" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
					" WHERE A.DEBTOR_CODE=B.CLIENT_CODE "+
					" AND A.FACILITY_NO='"+m_facility_no+"' AND A.CLIENT_CODE='"+m_client_code+"' ");
					*/
					
					/*
					// added by udara somathilake on 17-11-2010
					rs1= stmt1.executeQuery(" "+	
								" SELECT   A.FACILITY_NO, "+ 			  // 1
											" NVL(A.CLIENT_CODE,'-'), "+ // 2
											" NVL(A.DEBTOR_CODE,'-'), "+ // 3
											" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+ // 4
											" NVL(A.MKT_CODE,'-'), "+    // 5
											" NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-'), "+ // 6
											" NVL(A.CREDIT_LIMIT,0), "+  // 7
											" NVL(A.CREDIT_PERIOD,0), "+ // 8
											" NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+ // 9
											" NVL(A.RESERVE_MARGIN,0), "+ // 10
											" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-'), "+ // 11
											" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-'), "+ // 12
											" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')), "+ // 13
											" DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved'), "+ // 14
											" 'Y' "+ // 15
												" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+ 
												" WHERE A.DEBTOR_CODE = B.CLIENT_CODE "+
												" AND A.FACILITY_NO LIKE '%"+m_facility_no+"%' "+
									         " AND A.CLIENT_CODE LIKE '%"+m_client_code+"%' "+
									            " AND ( "+
									                    " A.DEBTOR_CODE IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE) "+
									            " ) "+
								" UNION "+
								" SELECT   A.FACILITY_NO, "+          // 1
											" NVL(A.CLIENT_CODE,'-'), "+ // 2
											" NVL(A.DEBTOR_CODE,'-'), "+ // 3
											" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'), "+ // 4
											" NVL(A.MKT_CODE,'-'), "+    // 5
											" NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-'), "+ // 6
											" NVL(A.CREDIT_LIMIT,0), "+  // 7
											" NVL(A.CREDIT_PERIOD,0), "+ // 8
											" NVL(A.TOLERANCE_CREDIT_PERIOD,0), "+ // 9
											" NVL(A.RESERVE_MARGIN,0), "+ // 10
											" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-'), "+ // 11
											" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-'), "+ // 12
											" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')), "+ // 13
											" DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved'), "+ // 14
											" 'N' "+ // 15
												" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
												" WHERE A.DEBTOR_CODE = B.CLIENT_CODE "+
												" AND A.FACILITY_NO LIKE '%"+m_facility_no+"%' "+
									         " AND A.CLIENT_CODE LIKE '%"+m_client_code+"%' "+
									         	" AND ( "+
									                    " A.DEBTOR_CODE NOT IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE) "+
									            " ) "+	
							" ");
					*/
					
					// added by udara somathilake on 18-11-2010
					rs1= stmt1.executeQuery(" "+	
						" SELECT "+
								" FACILITY_NO, "+
								" CLIENT_CODE, "+
								" DEBTOR_CODE, "+
								" DEBTOR_NAME, "+
								" MKT_CODE, "+
								" CLIENT_MANAGER, "+
								" CREDIT_LIMIT, "+
								" CREDIT_PERIOD, "+
								" TOLERANCE_CREDIT_PERIOD, "+
								" RESERVE_MARGIN, "+
								" ADDRESS, "+
								" TELEPHONE, "+
								" BR_ID, "+
								" RELATION_STATUS, "+
								" STATUS "+
										" FROM ( "+
												" SELECT  A.FACILITY_NO FACILITY_NO, "+
														" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+ 
														" NVL(A.DEBTOR_CODE,'-') DEBTOR_CODE, "+ 
														" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR_NAME, "+ 
														" NVL(A.MKT_CODE,'-') MKT_CODE, "+   
														" NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-') CLIENT_MANAGER, "+
														" NVL(A.CREDIT_LIMIT,0) CREDIT_LIMIT,  "+
														" NVL(A.CREDIT_PERIOD,0) CREDIT_PERIOD, "+
														" NVL(A.TOLERANCE_CREDIT_PERIOD,0) TOLERANCE_CREDIT_PERIOD, "+
														" NVL(A.RESERVE_MARGIN,0) RESERVE_MARGIN, "+ 
														" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-') ADDRESS, "+ 
														" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-') TELEPHONE, "+
														" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')) BR_ID, "+ 
														" DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved') RELATION_STATUS, "+ 
														" 'Y' STATUS "+ 
																" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+ 
																		" WHERE A.DEBTOR_CODE = B.CLIENT_CODE "+
																				" AND A.FACILITY_NO LIKE '%"+m_facility_no+"%' "+
																				" AND A.CLIENT_CODE LIKE '%"+m_client_code+"%' "+
																				" AND ( "+
																						" A.DEBTOR_CODE IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE) "+
																				" ) "+
							" UNION "+
												" SELECT   A.FACILITY_NO FACILITY_NO, "+         
														" NVL(A.CLIENT_CODE,'-') CLIENT_CODE, "+ 
														" NVL(A.DEBTOR_CODE,'-') DEBTOR_CODE, "+ 
														" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR_NAME, "+ 
														" NVL(A.MKT_CODE,'-'), "+    
														" NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(A.MKT_CODE,'O','O')),'-') CLIENT_MANAGER, "+ 
														" NVL(A.CREDIT_LIMIT,0) CREDIT_LIMIT, "+ 
														" NVL(A.CREDIT_PERIOD,0) CREDIT_PERIOD, "+ 
														" NVL(A.TOLERANCE_CREDIT_PERIOD,0) TOLERANCE_CREDIT_PERIOD, "+ 
														" NVL(A.RESERVE_MARGIN,0) RESERVE_MARGIN, "+ 
														" NVL(B.REGISTERED_ADDRESS1,'-') || ',' || NVL(B.REGISTERED_ADDRESS2,'-') ADDRESS, "+
														" 'TELNO-' || NVL(B.REGISTERED_TEL_NO,'-') || ',FAX-' || NVL(B.REGISTERED_FAX_NO,'-') || ',OFTEL-' || NVL(B.REGISTERED_OFFICE_TEL_NO,'-') || ',MOBIL-' || NVL(B.REGISTERED_MOBILE_NO,'-') TELEPHONE, "+ 
														" DECODE(B.CLIENT_CATEGORY,'INDIVIDUAL',NVL(B.NIC_NO,'-'),NVL(B.BUSINESS_CERTIFICATE_NO,'-')) BR_ID, "+ 
														" DECODE(A.RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved') RELATION_STATUS, "+
														" 'N' STATUS "+ 
																" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR A,"+m_schema_name+".FA_CO_MAS_CLIENT B "+
																" WHERE A.DEBTOR_CODE = B.CLIENT_CODE "+
																			" AND A.FACILITY_NO LIKE '%"+m_facility_no+"%' "+
																			" AND A.CLIENT_CODE LIKE '%"+m_client_code+"%' "+
																			" AND ( "+
																						" A.DEBTOR_CODE NOT IN (SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIE_DEBT_DOC_UPDATE) "+
																			" ) "+		
							" ) "+
							" ORDER BY DEBTOR_NAME "+
						" ");
	
	            boolean mflag=true;							
			 	   String client_code = "";
				   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Factor Active Debtor List Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>Factor Active Debtor List Report </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
							if(!more){
								out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
								out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
								out.println("</TABLE>");
							}					
						  if(more){
								out.println("<table align='center' width='100%' class='table' >");
								out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
								out.println("<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>"); // added by udara somathilake on 17-11-2010
								out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
								// out.println("<td width='10%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>"); // commented by udara somathilake on 17-11-2010
								out.println("<td width='10%' ><DIV class=div_input><b>Address</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Telephone Nos</b></DIV></td>");
								//out.println("<td width='10%' ><DIV class=div_input><b>BRC No/NIC</b></DIV></td>"); // commented by udara on 17-11-2010
								out.println("<td width='10%' ><DIV class=div_input><b>Accepted Assignment Letter - Y/N</b></DIV></td>");   // added by udara somathilake on 17-11-2010
								out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Limit</b></DIV></td>");
								out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Period</b></DIV></td>");
								out.println("<td width='10%' align='right' ><DIV class=div_input><b>Tol. Credit Period</b></DIV></td>");
								out.println("<td width='10%' align='right' ><DIV class=div_input><b>Reverse Margin</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Relation Status</b></DIV></td>");
								out.println("</tr>"); 
							}
							
							while(more){
						
									if(mflag){
									out.println("<tr class=tr_input>");
									mflag=false;
									}
									else{
									out.println("<tr class=tr_input1>");
									mflag=true;
									}
									out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>"); // added by udara somathilake on 17-11-2010
									out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><u>"+rs1.getString(4)+"</u></td>");
									//out.println("<td width='12%' class=div_input >"+rs1.getString(6)+"</td>"); // commented by udara somathilake on 17-11-2010 - Facility Manager
									out.println("<td width='10%' class=div_input >"+rs1.getString(11)+"</td>"); 
									out.println("<td width='10%' class=div_input >"+rs1.getString(12)+"</td>");
									//out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>"); // commented by udara on 17-11-2010 - BRC/NIC
									out.println("<td width='10%' class=div_input >"+rs1.getString(15)+"</td>"); // added by udara somathilake 
									out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(7))+"</td>");
									out.println("<td width='10%' align='right' class=div_input >"+rs1.getInt(8)+"</td>");
									out.println("<td width='10%' align='right' class=div_input >"+rs1.getInt(9)+"</td>");
									out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(10))+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(14)+"</td>");
									out.println("</tr>");
									more = rs1.next();
							}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_FACTOR_DEBTOR_LIST_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_client_code=req.getParameter("client_code");
				String m_client_type=req.getParameter("client_type");
				String m_status=req.getParameter("status");
				
			//--------------------------DEBTOR LIST REPORT -  -------------------------------

								
								String m_query="";
								
							if(m_client_code.equals("")){	
							 m_query = " SELECT "+
								  "  A.CLIENT_CODE,"+//1
								  "  A.FULL_NAME,"+//2
								  "  DECODE(A.CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
								  "  DECODE(A.FACTORING_TYPE,'C','As a Client','D','As a Debtor','As a Client or Debtor'),"+//4
								  "  NVL(A.REGISTERED_ADDRESS1,'-'),"+//5
								  "  NVL(A.REGISTERED_ADDRESS2,'-'),"+//6
								  "  A.AREA_CODE,"+//7
								  "  "+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),"+//8
								  "  A.REGISTERED_CONTACT_PERSON,"+//9
								  "  INITCAP(A.CLIENT_CATEGORY),"+//10
								  "  NVL(A.NIC_NO,'-'),"+//11
								  "  NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-'),"+//12
								  "  NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'),"+//13
								  "  NVL(A.VAT_REG_NO,'-'),  "+//14  
								  "  DECODE(A.ACTIVE_STATUS,'E','Client Creation','I','Approved Initial','Y','Approved Credit','N','Disapproved Client','T','Terminated') "+//15
								" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_MAS_AREA B "+
								" WHERE A.AREA_CODE=B.AREA_CODE(+) "+
								" AND A.CLIENT_TYPE LIKE UPPER('"+m_client_type+"%') "+
								" AND A.FACTORING_TYPE LIKE UPPER('D%') "+
								" AND A.ACTIVE_STATUS LIKE UPPER('"+m_status+"%') ";
							}	
						 else {					
							 m_query =	" SELECT DISTINCT "+
								  "  A.CLIENT_CODE,"+//1
								  "  A.FULL_NAME,"+//2
								  "  DECODE(A.CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
								  "  DECODE(A.FACTORING_TYPE,'C','As a Client','D','As a Debtor','As a Client or Debtor'),"+//4
								  "  NVL(A.REGISTERED_ADDRESS1,'-'),"+//5
								  "  NVL(A.REGISTERED_ADDRESS2,'-'),"+//6
								  "  A.AREA_CODE,"+//7
								  "  "+m_schema_name+".AF_CO_GET_CITY_NAME(B.CITY_CODE),"+//8
								  "  A.REGISTERED_CONTACT_PERSON,"+//9
								  "  INITCAP(A.CLIENT_CATEGORY),"+//10
								  "  NVL(A.NIC_NO,'-'),"+//11
								  "  NVL(TO_CHAR(A.DATE_OF_BIRTH,'DD-MM-YYYY'),'-'),"+//12
								  "  NVL(TO_CHAR(A.DATE_OF_INCORPORATION,'DD-MM-YYYY'),'-'),"+//13
								  "  NVL(A.VAT_REG_NO,'-'),  "+//14  
								  "  DECODE(A.ACTIVE_STATUS,'E','Client Creation','I','Approved Initial','Y','Approved Credit','N','Disapproved Client','T','Terminated') "+//15
								" FROM "+m_schema_name+".FA_CO_MAS_CLIENT A,"+m_schema_name+".AF_CO_MAS_AREA B,"+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR C "+
								" WHERE A.AREA_CODE=B.AREA_CODE(+) AND "+
								" A.CLIENT_CODE=C.CLIENT_CODE(+) "+
								" AND ( A.FACTORING_TYPE LIKE UPPER('D%') AND A.CLIENT_TYPE LIKE UPPER('"+m_client_type+"%') AND A.ACTIVE_STATUS LIKE UPPER('"+m_status+"%') "+
								" AND "+
								" (A.CLIENT_CODE IN ( SELECT DEBTOR_CODE FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR WHERE CLIENT_CODE='"+m_client_code+"')) ) ";
							}

							rs1= stmt1.executeQuery(m_query);
							
			         boolean mflag=true;							
					 	   String client_code = "";
						   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE> Factor Debtor List Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Factor Debtor List Report </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
						
							if(!more){
								out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
								out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
								out.println("</TABLE>");
							}					
						  if(more){
								out.println("<table align='center' width='100%' class='table' >");						
								out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
								out.println("<td width='1%'></td>"); 
								out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
								out.println("<td width='8%' ><DIV class=div_input><b>Client Type</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Factoring Relationship</b></DIV></td>");
								out.println("<td width='10%' ><DIV class=div_input><b>Reg. Address</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Reg. Address2</b></DIV></td>"); 
								out.println("<td width='8%' ><DIV class=div_input><b>City</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Legal Entity</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>NIC No</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Date of Birth</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Date of Incor.</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>VAT Reg No</b></DIV></td>"); 
								out.println("<td width='10%' ><DIV class=div_input><b>Client Status</b></DIV></td>"); 
								out.println("</tr>"); 
							
							}
							
							while(more){
						
									if(mflag){
										out.println("<tr class=tr_input>");
										mflag=false;
									}
									else{
										out.println("<tr class=tr_input1>");
										mflag=true;
									}
									out.println("<td width='1%'></td>"); 
									out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
									out.println("<td width='8%'  class=div_input >"+rs1.getString(3)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
									out.println("<td width='8%' class=div_input >"+rs1.getString(8)+"</td>");
									out.println("<td width='10%' class=div_input>"+rs1.getString(9)+"</td>");
									out.println("<td width='10%' class=div_input>"+rs1.getString(10)+"</td>");
									out.println("<td width='10%' class=div_input>"+rs1.getString(11)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(12)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(14)+"</td>");
									out.println("<td width='10%' class=div_input >"+rs1.getString(15)+"</td>");
									out.println("</tr>");
									more = rs1.next();
							}	
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_PD_CHEQUE_LIST_REPORT")){//Added By Sandun on 29-06-2009
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_debtor_code=req.getParameter("debtor_code");		
			
									
				
					
			    rs1= stmt1.executeQuery(" SELECT  A.DEBTOR_CODE DEBTOR_CODE, "+
															    " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR_NAME, "+
															    " A.INVOICE_NO INVOICE_NO , "+
															    " SUM(C.INVOICE_AMOUNT) INVOICE_AMOUNT, "+
															    " SUM(A.ALLO_AMOUNT) ALLO_AMOUNT, "+
																	" A.FACILITY_NO, "+
															    " A.CLIENT_CODE, "+
															    " A.DEBTOR_CODE "+
															    " FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
															    " WHERE A.BATCH_NO = C.BATCH_NO "+
																	" AND A.RECEIPT_NO = B.RECEIPT_NO "+
																	" AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
														      " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
															    " AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%')  "+
															    " AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') "+
															    " AND UPPER(A.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%')  "+
															    " GROUP BY A.DEBTOR_CODE,A.INVOICE_NO,A.FACILITY_NO,A.CLIENT_CODE, A.DEBTOR_CODE "+
															     
															    " UNION ALL "+
  
														      " SELECT 		"+	
														      " A.DEBTOR_CODE DEBTOR_CODE,  "+//1
														      " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-') DEBTOR_NAME,  "+//2
														      " A.INVOICE_NO INVOICE_NO,  "+//3
														      " SUM(nvl(C.INVOICE_AMOUNT,0)) INVOICE_AMOUNT,  "+//4
														      " SUM(nvl(A.ALLO_AMOUNT,0)) ALLO_AMOUNT, "+//5
																	" A.FACILITY_NO, "+//6
															    " A.CLIENT_CODE, "+//7
															    " A.DEBTOR_CODE "+//8
														      " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
														      " WHERE A.POD_REF_NO=B.POD_REF_NO   "+
														      " AND A.CLIENT_CODE=C.CLIENT_CODE   "+
														      " AND A.FACILITY_NO=C.FACILITY_NO   "+
														      " AND A.BATCH_NO=C.BATCH_NO   "+
														      " AND A.INVOICE_NO=C.INVOICE_NO  "+ 
														      " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
														      " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
														      " AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%')  "+
														      " AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')  "+
														      " AND UPPER(A.DEBTOR_CODE) LIKE UPPER('"+m_debtor_code+"%')  "+
														      " GROUP BY A.DEBTOR_CODE,A.INVOICE_NO,A.FACILITY_NO,A.CLIENT_CODE, A.DEBTOR_CODE ");
					
					
					
					
					
					boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Cheque Report - Invoice </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					  out.println("<script>");
						
						 out.println("function load_allo_data(facility,client,invoice){");
						 out.println("m_from_date = '"+m_from_date+"'; ");	
						 out.println("m_to_date   = '"+m_to_date+"'; ");	
						 out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"FA_MISF_reports?chksql=LOAD_PD_CHEQUE_LIST_REPORT_DRILL&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&client_code=\"+client+\"&facility_no=\"+facility+\"&invoice_no=\"+invoice+\" \";");
			       out.println("popupwin=window.open(m_url,'displayWindow17','left=90,top=110,width=900,height=400,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
						 out.println("}");
						
						out.println("</script>");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Cheque Report - Assign List</B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); //11
						out.println("<td width='12%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); //12
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>");//14
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Allocation Amount</b></DIV></td>");//
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Diffrent</b></DIV></td>");//
						out.println("</tr>"); 
					
					}
					
					while(more){
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}							
							out.println("<td width='1%'></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand'><u>"+rs1.getString(2)+"</u></td>");//11
							out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");//12
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(4))+"</td>");//14
							out.println("<td width='10%' align='right' class=div_input style='cursor:hand' onclick=\"load_allo_data('"+rs1.getString(6)+"','"+rs1.getString(7)+"','"+rs1.getString(3)+"')\"><u>"+nf.format(rs1.getDouble(5))+"</td>");//
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(4)-rs1.getDouble(5))+"</td>");//
							out.println("</tr>");
							
							more = rs1.next();
							if(!more){
							break;
							}
							}
							
							
					
					
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");
	    }
			else if(m_chksql.equals("LOAD_PD_CHEQUE_LIST_REPORT_DRILL")){//Added By Sandun on 29-06-2009
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_invoice_no=req.getParameter("invoice_no");		
				
				
				rs1= stmt1.executeQuery("	SELECT  A.RECEIPT_NO, "+
													      " A.INVOICE_NO INVOICE_NO , "+
													      " C.INVOICE_AMOUNT INVOICE_AMOUNT, "+
													      " A.ALLO_AMOUNT ALLO_AMOUNT, "+
													      " A.FACILITY_NO, "+
													      " A.CLIENT_CODE, "+
													      " A.DEBTOR_CODE, "+
																" B.CHEQUE_NO "+
													      " FROM "+m_schema_name+".FA_OP_PRO_RECEIPT_ALLO A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
													      " WHERE A.BATCH_NO = C.BATCH_NO "+
													      " AND A.RECEIPT_NO = B.RECEIPT_NO "+
													      " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
													      " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
													      " AND UPPER(A.FACILITY_NO) = UPPER('"+m_facility_no+"')  "+
													      " AND UPPER(A.CLIENT_CODE) = UPPER('"+m_client_code+"')  "+
													      " AND UPPER(A.INVOICE_NO) = UPPER('"+m_invoice_no+"')  "+  
													     
													      " UNION ALL "+	
																
														    " SELECT A.POD_REF_NO, "+			 
														    " A.INVOICE_NO INVOICE_NO,  "+
														    " nvl(C.INVOICE_AMOUNT,0) INVOICE_AMOUNT,  "+
														    " nvl(A.ALLO_AMOUNT,0) ALLO_AMOUNT, "+
														    " A.FACILITY_NO, "+
														    " A.CLIENT_CODE, "+
														    " A.DEBTOR_CODE, "+
																" B.CHEQUE_NO "+
														    " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES B,"+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL C "+
														    " WHERE A.POD_REF_NO=B.POD_REF_NO   "+
														    " AND A.CLIENT_CODE=C.CLIENT_CODE   "+
														    " AND A.FACILITY_NO=C.FACILITY_NO   "+
														    " AND A.BATCH_NO=C.BATCH_NO   "+
														    " AND A.INVOICE_NO=C.INVOICE_NO "+ 
														    " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
														    " AND TO_DATE(TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
														    " AND UPPER(A.FACILITY_NO)  = UPPER('"+m_facility_no+"')  "+
														    " AND UPPER(A.CLIENT_CODE)  = UPPER('"+m_client_code+"')  "+
														    " AND UPPER(A.INVOICE_NO) = UPPER('"+m_invoice_no+"')  ");
																			
					
					
					
					boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Cheque Report - Invoice </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					  
						
						 
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 //out.println("<BR>");
					 
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Cheque Report - Assign List</B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<BR>");	
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Ref No</b></DIV></td>"); //11
						out.println("<td width='12%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); //12
						out.println("<td width='12%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); //12
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Invoice Amount</b></DIV></td>");//14
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Allocation Amount</b></DIV></td>");//
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Diffrent</b></DIV></td>");//
						out.println("</tr>"); 
					
					}
					
					while(more){
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}							
							out.println("<td width='1%'></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");//11
							out.println("<td width='12%' class=div_input >"+rs1.getString(2)+"</td>");//12
							out.println("<td width='12%' class=div_input >"+rs1.getString(8)+"</td>");//12
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(3))+"</td>");//14
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(4))+"</td>");//
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(3)-rs1.getDouble(4))+"</td>");//
							out.println("</tr>");
							
							more = rs1.next();
							if(!more){
							break;
							}
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



