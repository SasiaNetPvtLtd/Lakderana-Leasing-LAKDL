import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : ASHINI FOR OFSCL FACTORING    DATE:15-02.2008

public class LAKDL_FA_MISF_pod_and_receipt_reports extends javax.servlet.http.HttpServlet {
	
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
			
			else if(m_chksql.equals("LOAD_POD_REPORT")){ 
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_no=req.getParameter("facility_no");
				
			
					rs1= stmt1.executeQuery(" SELECT "+
					  "  DISTINCT A.POD_REF_NO, "+//1
					  "  NVL(A.PAYER_BRANCH_CODE,'-'), "+//2
					  "  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+//3
					  "  NVL("+m_schema_name+".AF_CO_GET_BANK_NAME(A.PAYER_BRANCH_CODE),'-'), "+//4
					  "  NVL(A.CHEQUE_NO,'-'),"+//5
					  "  NVL(TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//6
					  "  NVL(A.CHEQUE_AMOUNT,0),"+//7
					  "  NVL(A.CLIENT_CODE,'-'),"+//8
					  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'),"+//9
					  "  NVL(A.FACILITY_NO,'-'),"+//10
					  "  NVL(A.DEBTOR_CODE,'-'),"+//11
					  "  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//12
					  "  NVL(DECODE(A.POD_STATUS,'N','PD Cheque Available','Y','PD Cheque Realised','C','PD Cheque Dishonored'),'-'), "+//13
						"  NVL(DECODE(A.POD_TYPE,'IA','Invoice Wise','DA','Debtor Wise','CA','Client Wise'),'-') "+//14
					 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
					 " WHERE  A.POD_REF_NO=B.POD_REF_NO(+) "+
					 " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					 " TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
					 "  ");
			
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
						out.println("<td width='10%' ><DIV class=div_input><b>PD Ref No</b></DIV></td>");
						out.println("<td width='6%' ><DIV class=div_input><b>Cheque No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Bank Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Branch</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>PD Status</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>PD Type</b></DIV></td>"); 
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
							out.println("<td width='10%' class=div_input onClick=\"show_pod_cheque_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='6%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(10)+"')\" style='cursor:hand'><u>"+rs1.getString(10)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(8)+"')\" style='cursor:hand'><u>"+rs1.getString(9)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(11)+"')\" style='cursor:hand'><u>"+rs1.getString(12)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(14)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more = rs1.next();
					}	
					
      	 		out.println("</table>");
							
					 out.println("<br>"); 		
							
					 rs1= stmt1.executeQuery(" SELECT "+
					 "  COUNT(DISTINCT A.POD_REF_NO) "+//1
					 " FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
					 " WHERE  A.POD_REF_NO=B.POD_REF_NO(+) "+
					 " AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					 " TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					 " AND UPPER(A.FACILITY_NO) LIKE UPPER('"+m_facility_no+"%') "+
					 "  ");
						
						if(rs1.next()){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Total Cheques </b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>"+rs1.getInt(1)+"</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
					 }
						
						
												out.println("<br><br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Checked By</b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>.............................................</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br><br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Approved By</b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>.............................................</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
						
			  	
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			
			else if(m_chksql.equals("LOAD_RECEIPT_REPORT")){ 
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_facility_no=req.getParameter("facility_no");
				
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
					"  NVL(TO_CHAR(BANK_DATE,'DD-MM-YYYY'),'-') "+//13
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND RECEIPT_TYPE<>'POD' "+
					" ORDER BY EFF_VALDATE "); 

				
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
					out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs1.getString(7)+"')\" style='cursor:hand'><u>"+rs1.getString(8)+"</u></td>");
					out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs1.getString(9)+"')\" style='cursor:hand'><u>"+rs1.getString(9)+"</u></td>");
					out.println("</tr>");
					more = rs1.next();
				}	
  	 		out.println("</table>");
	  		out.println("<br>"); 
				
				
					rs1= stmt1.executeQuery(" SELECT "+
					" COUNT(RECEIPT_NO)"+//1
					" ,DECODE(rec_status,'B','Bank','D','Deposit','E','Enterd','C','Cancel','Y','Realise','N','Pending') "+
					" FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
					" WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND  "+
					" TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND RECEIPT_TYPE<>'POD' GROUP BY rec_status ");
					
						
						while(rs1.next()){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Total Cheques "+rs1.getString(2)+"</b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>"+rs1.getInt(1)+"</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
					 }
						
						out.println("<br><br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Checked By</b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>.............................................</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
						out.println("<br><br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Approved By</b></DIV></td>");
						out.println("<td width='*%' ><DIV class=div_input><b>.............................................</b></DIV></td>");
						out.println("</tr>"); 
						out.println("</table>"); 
						
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



