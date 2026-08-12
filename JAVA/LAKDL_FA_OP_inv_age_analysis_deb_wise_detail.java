import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:17-01-2007

public class LAKDL_FA_OP_inv_age_analysis_deb_wise_detail extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt2,stmt3,stmt4,stmt5,stmt6,stmt7,stmt8,stmt9;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
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
			else if(m_chksql.equals("LOAD_INVOICE_DETAILS")){
				
				String m_string="";				
				String m_sql="";	
				String m_batch_no=req.getParameter("batch_no");
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_eff_date=req.getParameter("eff_date");
				String m_date_range=req.getParameter("date_range");
				String m_date_category=req.getParameter("date_category");			
				String m_date="";
				String m_debtor_code=req.getParameter("debtor_code");
			
			//-------------------------- INVOICE DETAILS  -------------------------------
			
			if(m_date_category.equals("DUE_DATE")){
			m_date = "A.DUE_DATE";
			}
				
	
			 if(m_date_range.equals("0")) { 

				rs1= stmt1.executeQuery("SELECT  A.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2	
  			"  A.INVOICE_NO,"+//3
  	  	"  TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//4
    		"  NVL(A.INVOICE_AMOUNT,0),"+//5
  	  	"  NVL(A.ADJUSTMENT_AMOUNT,0),"+//6
  			"  NVL(A.NET_INVOICE_AMOUNT,0),"+//7
  		 	"	 NVL(A.SETTLE_AMOUNT,0),"+//8
    		"  NVL(A.BALANCE_AMOUNT,0),"+//9
  	  	"  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')"+//10
 				"  FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
 				"  WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.INVOICE_STATUS='CONF' "+
			  "  AND A.DEBTOR_CODE='"+m_debtor_code+"' "+ 
				//"  AND ((A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )<=0) AND A.BALANCE_AMOUNT >0 	");
				"  AND ((  TO_DATE('"+m_eff_date+"','DD-MM-YYYY') - A.DUE_DATE  )<=0) AND A.BALANCE_AMOUNT >0 	");
			 }		
			 if(m_date_range.equals("30")) { 

				rs1= stmt1.executeQuery("SELECT  A.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2	
  			"  A.INVOICE_NO,"+//3
  	  	"  TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//4
    		"  NVL(A.INVOICE_AMOUNT,0),"+//5
  	  	"  NVL(A.ADJUSTMENT_AMOUNT,0),"+//6
  			"  NVL(A.NET_INVOICE_AMOUNT,0),"+//7
  		 	"	 NVL(A.SETTLE_AMOUNT,0),"+//8
    		"  NVL(A.BALANCE_AMOUNT,0),"+//9
  	  	"  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')"+//10
 				"  FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
 				"  WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.INVOICE_STATUS='CONF' "+
			  "  AND A.DEBTOR_CODE='"+m_debtor_code+"' "+ 
				//"  AND ((A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')  )>0 AND (A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )<=30 ) AND A.BALANCE_AMOUNT >0 	");
				"  AND ((  TO_DATE('"+m_eff_date+"','DD-MM-YYYY') - A.DUE_DATE  )>0 AND ( TO_DATE('"+m_eff_date+"','DD-MM-YYYY') - A.DUE_DATE )<=30 ) AND A.BALANCE_AMOUNT >0 	");
			 }	
			 if(m_date_range.equals("60")) { 

				rs1= stmt1.executeQuery("SELECT  A.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2	
  			"  A.INVOICE_NO,"+//3
  	  	"  TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//4
    		"  NVL(A.INVOICE_AMOUNT,0),"+//5
  	  	"  NVL(A.ADJUSTMENT_AMOUNT,0),"+//6
  			"  NVL(A.NET_INVOICE_AMOUNT,0),"+//7
  		 	"	 NVL(A.SETTLE_AMOUNT,0),"+//8
    		"  NVL(A.BALANCE_AMOUNT,0) ,"+//9
  	  	"  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')"+//10
 				"  FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A  "+
 				"  WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.INVOICE_STATUS='CONF' "+
			  "  AND A.DEBTOR_CODE='"+m_debtor_code+"' "+ 
				//"  AND (( A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )>30 AND (  A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')  )<=60 ) AND A.BALANCE_AMOUNT >0 	");
				"  AND ((  TO_DATE('"+m_eff_date+"','DD-MM-YYYY') - A.DUE_DATE )>30 AND (   TO_DATE('"+m_eff_date+"','DD-MM-YYYY')  - A.DUE_DATE )<=60 ) AND A.BALANCE_AMOUNT >0 	");
			 }		
			 if(m_date_range.equals("90")) { 

				rs1= stmt1.executeQuery("SELECT  A.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2	
  			"  A.INVOICE_NO,"+//3
  	  	"  TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//4
    		"  NVL(A.INVOICE_AMOUNT,0),"+//5
  	  	"  NVL(A.ADJUSTMENT_AMOUNT,0),"+//6
  			"  NVL(A.NET_INVOICE_AMOUNT,0),"+//7
  		 	"	 NVL(A.SETTLE_AMOUNT,0),"+//8
    		"  NVL(A.BALANCE_AMOUNT,0),"+//9
  	  	"  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')"+//10
 				"  FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
 				"  WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.INVOICE_STATUS='CONF' "+
			  "  AND A.DEBTOR_CODE='"+m_debtor_code+"' "+ 
				//"  AND ((A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )>60 AND (  A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY')  )<=90 ) AND A.BALANCE_AMOUNT >0 	");
				"  AND (( TO_DATE('"+m_eff_date+"','DD-MM-YYYY') - A.DUE_DATE ) > 60 AND (   TO_DATE('"+m_eff_date+"','DD-MM-YYYY')  - A.DUE_DATE  )<=90 ) AND A.BALANCE_AMOUNT >0 	");
			 }		
			 if(m_date_range.equals("99")) { 

				rs1= stmt1.executeQuery("SELECT  A.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE), "+//2	
  			"  A.INVOICE_NO,"+//3
  	  	"  TO_CHAR(A.INVOICE_DATE,'DD-MM-YYYY'),"+//4
    		"  NVL(A.INVOICE_AMOUNT,0),"+//5
  	  	"  NVL(A.ADJUSTMENT_AMOUNT,0),"+//6
  			"  NVL(A.NET_INVOICE_AMOUNT,0),"+//7
  		 	"	 NVL(A.SETTLE_AMOUNT,0),"+//8
    		"  NVL(A.BALANCE_AMOUNT,0),"+//9
  	  	"  TO_CHAR(A.DUE_DATE,'DD-MM-YYYY')"+//10
 				"  FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL A "+
 				"  WHERE A.CLIENT_CODE='"+m_client_code+"' AND A.FACILITY_NO='"+m_facility_no+"' AND A.INVOICE_STATUS='CONF' "+
			  "  AND A.DEBTOR_CODE='"+m_debtor_code+"' "+ 
				//"  AND ((  A.DUE_DATE - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )>90 ) AND A.BALANCE_AMOUNT >0 	");
				"  AND ((   TO_DATE('"+m_eff_date+"','DD-MM-YYYY') ) - A.DUE_DATE >90 ) AND A.BALANCE_AMOUNT >0 	");
			 }		
				
         boolean mflag=true;							
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Invoice Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Invoice Details for Client Code - "+m_client_code+"  Facility No - "+m_facility_no+" </B></TD></TR>");
					 out.println("</TABLE>");
						
					 out.println("<BR>");
					 out.println("<table align='center' width='100%' class='table' >");						
					
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){

						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Due Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Invoice Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Adjustment Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Net Invoie Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Settle Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Outstanding Amount</b></DIV></td>"); 
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
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(10)+"</td>"); /////
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(7))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
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
			else if(m_chksql.equals("LOAD_POD_DETAILS")){
				
				String m_string="";				
				String m_sql="";	
				String m_batch_no=req.getParameter("batch_no");
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_eff_date=req.getParameter("eff_date");
				String m_date_range=req.getParameter("date_range");
				String m_date="";
			
			//-------------------------- POD DETAILS  -------------------------------			

			 if(m_date_range.equals("30")) { 
				rs1= stmt1.executeQuery("SELECT  B.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2	
  			"  B.INVOICE_NO,"+//3
				"	 NVL(A.CHEQUE_NO,'-'),	"+//4
  	  	"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.CHEQUE_AMOUNT,0),"+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE)  "+//7
 				"  FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
 				"  WHERE A.POD_REF_NO=B.POD_REF_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND A.POD_STATUS='N' "+
				"  AND ((TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>=0 AND ( TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))<=30 ) AND A.CHEQUE_AMOUNT<>0 	");
			 }	
			 if(m_date_range.equals("60")) { 
				rs1= stmt1.executeQuery("SELECT  B.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2	
  			"  B.INVOICE_NO,"+//3
				"	 NVL(A.CHEQUE_NO,'-'),	"+//4
  	  	"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.CHEQUE_AMOUNT,0),"+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE)  "+//7
 				"  FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
 				"  WHERE A.POD_REF_NO=B.POD_REF_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND A.POD_STATUS='N'  "+
				"  AND (( TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>30 AND ( TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))<=60 ) AND A.CHEQUE_AMOUNT<>0 	");
			 }		
			 if(m_date_range.equals("90")) { 
				
				rs1= stmt1.executeQuery("SELECT  B.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2	
  			"  B.INVOICE_NO,"+//3
				"	 NVL(A.CHEQUE_NO,'-'),	"+//4
  	  	"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.CHEQUE_AMOUNT,0),"+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE)  "+//7
 				"  FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
 				"  WHERE A.POD_REF_NO=B.POD_REF_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND A.POD_STATUS='N' "+
				"  AND (( TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>60 AND ( TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))<=90 ) AND A.CHEQUE_AMOUNT<>0 	");

			 }		
			 if(m_date_range.equals("99")) { 
				
				rs1= stmt1.executeQuery("SELECT  B.DEBTOR_CODE,"+//1
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE), "+//2	
  			"  B.INVOICE_NO,"+//3
				"	 NVL(A.CHEQUE_NO,'-'),	"+//4
  	  	"  TO_CHAR(A.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.CHEQUE_AMOUNT,0),"+//6
				" "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE)  "+//7
 				"  FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
 				"  WHERE A.POD_REF_NO=B.POD_REF_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND A.POD_STATUS='N'  "+
				"  AND ((TO_DATE(A.CHEQUE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>90 ) AND A.CHEQUE_AMOUNT<>0 	");

			 }		
				
         boolean mflag=true;							
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>PD Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> PD Details for Client Code - "+m_client_code+"  Facility No - "+m_facility_no+" </B></TD></TR>");
					 out.println("</TABLE>");
						
					 out.println("<BR>");
					 out.println("<table align='center' width='100%' class='table' >");						
					
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){

						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Cheque Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
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
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
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
			else if(m_chksql.equals("LOAD_CHEQUE_RETURN_DETAILS")){
				
				String m_string="";				
				String m_sql="";	
				String m_batch_no=req.getParameter("batch_no");
				String m_facility_no=req.getParameter("facility_no");
				String m_client_code=req.getParameter("client_code");
				String m_eff_date=req.getParameter("eff_date");
				String m_date_range=req.getParameter("date_range");
				String m_date="";
			
			//-------------------------- CHEQUE RETURN DETAILS  -------------------------------			

			 if(m_date_range.equals("30")) { 
				
				rs1= stmt1.executeQuery(" SELECT  B.DEBTOR_CODE,"+//1
				"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//2
  			"  NVL(B.INVOICE_NO,'-'),"+//3
				"	 NVL(A.CHEQUE_NO,'-'),"+//4	
  	  	"  TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.DEPOSIT_AMOUNT,0),"+//6
				"  "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) "+ //7
 				"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 				"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND B.REC_STATUS='C' "+
				"  AND (( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>=0 AND ( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))<=30 ) AND A.DEPOSIT_AMOUNT<>0 ");	
				
			 }	
			 if(m_date_range.equals("60")) { 
				rs1= stmt1.executeQuery(" SELECT  B.DEBTOR_CODE,"+//1
				"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//2
  			"  NVL(B.INVOICE_NO,'-'),"+//3
				"	 NVL(A.CHEQUE_NO,'-'),"+//4	
  	  	"  TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.DEPOSIT_AMOUNT,0),"+//6
				"  "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) "+ //7
 				"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 				"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND B.REC_STATUS='C' "+
				"  AND (( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>30 AND ( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))<=60 ) AND A.DEPOSIT_AMOUNT<>0 ");	
				
			 }		
			 if(m_date_range.equals("90")) { 
				
				rs1= stmt1.executeQuery(" SELECT  B.DEBTOR_CODE,"+//1
				"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//2
  			"  NVL(B.INVOICE_NO,'-'),"+//3
				"	 NVL(A.CHEQUE_NO,'-'),"+//4	
  	  	"  TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.DEPOSIT_AMOUNT,0),"+//6
				"  "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) "+ //7
 				"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 				"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND B.REC_STATUS='C' "+
				"  AND (( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>60 AND ( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY') )<=90 ) AND A.DEPOSIT_AMOUNT<>0 ");	

			 }		
			 if(m_date_range.equals("99")) { 
				
				rs1= stmt1.executeQuery(" SELECT  B.DEBTOR_CODE,"+//1
				"  NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'),"+//2
  			"  NVL(B.INVOICE_NO,'-'),"+//3
				"	 NVL(A.CHEQUE_NO,'-'),"+//4	
  	  	"  TO_CHAR(B.CHEQUE_DATE,'DD-MM-YYYY'),"+//5
    		"  NVL(A.DEPOSIT_AMOUNT,0),"+//6
				"  "+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE) "+ //7
 				"  FROM "+m_schema_name+".FA_OP_PRO_RETURN_DETAILS A,"+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT B "+
 				"  WHERE A.RECEIPT_NO=B.RECEIPT_NO AND B.CLIENT_CODE='"+m_client_code+"' AND B.FACILITY_NO='"+m_facility_no+"' AND B.REC_STATUS='C' "+
				"  AND (( TO_DATE(A.REALIZE_DATE) - TO_DATE('"+m_eff_date+"','DD-MM-YYYY'))>90 ) AND A.DEPOSIT_AMOUNT<>0 ");	

			 }		
				
         boolean mflag=true;							
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Cheque Return Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 

					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Cheque Return Details for Client Code - "+m_client_code+"  Facility No - "+m_facility_no+" </B></TD></TR>");
					 out.println("</TABLE>");
						
					 out.println("<BR>");
					 out.println("<table align='center' width='100%' class='table' >");						
					
					if(!more){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
				  if(more){

						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Deposit Amount</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
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
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" style='cursor:hand'><u>"+rs1.getString(3)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(7)+"</td>");
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



