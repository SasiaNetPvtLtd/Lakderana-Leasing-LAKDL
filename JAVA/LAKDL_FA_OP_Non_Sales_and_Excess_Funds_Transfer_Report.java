import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : ASHINI  FOR OFSCL FACTORING    DATE:24-10-2007
// Non Sales and Excess Funds Transfer REPORT            

public class LAKDL_FA_OP_Non_Sales_and_Excess_Funds_Transfer_Report extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_TRANSFER_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_client_code=req.getParameter("client_code");
				String m_facility_no=req.getParameter("facility_no");
				

					rs1= stmt1.executeQuery(" SELECT "+
					" A.FACILITY_NO, "+ //1
					" A.CLIENT_CODE, "+ //2
					" DECODE (B.RECEIPT_TYPE,'CS',"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE)), "+// 3
					" A.RECEIPT_NO, "+ //4
					" B.REC_AMOUNT, "+ //5
					" DECODE(B.SETTLE_MODE,'CHEQUE',B.SETTLE_MODE ||'-'||B.CHEQUE_NO,B.SETTLE_MODE), "+ //6
					" A.REF_NO, "+ //7
					" A.TRANSFER_AMOUNT, "+ //8
					" TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'), "+ //9
					" A.SOURCE_DOCUMENT, "+  //10
					" A.COMMENTS "+ //11
					" FROM "+m_schema_name+".FA_OP_PRO_NON_SALE_ADJUSTMENT A, "+
					" "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
					" WHERE A.STATUS ='E' "+
					" AND A.FACILITY_NO = B.FACILITY_NO "+
					" AND A.CLIENT_CODE = B.CLIENT_CODE "+
					" AND A.RECEIPT_NO = B.RECEIPT_NO "+
					" AND TO_DATE(TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					" AND TO_DATE(TO_CHAR(A.TRANSFER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
					" AND UPPER(A.FACILITY_NO) LIKE  UPPER('"+m_facility_no+"%') "+
					" AND UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%')  "+
					" ORDER BY A.FACILITY_NO,A.CLIENT_CODE,A.RECEIPT_NO ");
					
					
					
					boolean mflag=true;							
					boolean mflag2=false;							
					String client_code = "";
					String client_code_temp = "";	
					
					 boolean more = rs1.next();  
						
					 out.println("<HTML><HEAD><TITLE>Non Sales and Excess Funds Transfer Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B>Non Sales and Excess Funds Transfer Report</B></TD></TR>");
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
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Code</b></DIV></td>");
						out.println("<td width='7%' ><DIV class=div_input><b>Client Code</b></DIV></td>"); 
						out.println("<td width='13%' ><DIV class=div_input><b>Receipt No</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Receipt Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Settlement Mode</b></DIV></td>"); 
						out.println("<td width='13%' ><DIV class=div_input><b>Reference No</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Transfer Amount</b></DIV></td>"); 
						out.println("<td width='7%' ><DIV class=div_input><b>Transfer Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Source Document</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Comments</b></DIV></td>"); 
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

							out.println("<td width='10%' class=div_input onclick=\"show_facility('"+rs1.getString(1)+"')\" style='cursor:hand' ><U>"+rs1.getString(1)+"</U></td>");
							out.println("<td width='7%' class=div_input onclick=\"show_client('"+rs1.getString(2)+"')\" style='cursor:hand' ><U>"+rs1.getString(2)+"</U></td>");
							out.println("<td width='13%' class=div_input onClick=\"show_receipt_details('"+rs1.getString(4)+"')\"  style='cursor:hand' ><U>"+rs1.getString(4)+"</U></td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input>"+rs1.getString(6)+"</td>");
							out.println("<td width='13%' class=div_input>"+rs1.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input>"+nf.format(rs1.getDouble(8))+"</td>");
							out.println("<td width='7%' class=div_input>"+rs1.getString(9)+"</td>");
							out.println("<td width='10%' class=div_input>"+rs1.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input>"+rs1.getString(11)+"</td>");
							out.println("</tr>");
						
							more = rs1.next();
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



