import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:04-01-2007

public class LAKDL_FA_OP_PRO_rpt_client_charges extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
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
			else if(m_chksql.equals("LOAD_CLIENT_CHARGES_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_fee_code=req.getParameter("fee_code");
				String m_facility_no=req.getParameter("facility_no");
				String m_eff_date = "";
				String m_fee_type="%";
				
				if(m_fee_code.equals("ALL")){
				m_fee_type="%";
				}
				else{
				m_fee_type=m_fee_code;
				}
				
				double m_tot_fee_charge_amount =0;
				double m_tot_settle_fee_amount =0;
				double m_tot_bal_fee_amount =0;
				double m_tot_grand =0;
				
	      rs1= stmt1.executeQuery("SELECT  A.CHARGES_REF_NO, "+//1
	      " A.SUS_REF_NO,"+//2
	    	" A.CLIENT_CODE,"+//3
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+//4
	    	" A.FACILITY_NO,"+//5
	    	" A.FEE_CODE,"+//6
	    	" INITCAP(A.FEE_DESC),"+//7
	    	" A.DRCR_STATUS,"+//8
	    	" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),"+//9
	    	"	NVL(DECODE(A.DRCR_STATUS,'DR',A.FEE_CHARGE_AMOUNT,A.FEE_CHARGE_AMOUNT*-1),0),"+//10
	    	" 0,"+//11
	    	" 0,"+//12
	  	  " A.CURR_CODE,"+//13
	    	" A.REC_AMOUNT_CURR,"+//14
	  	 	" A.EXCHANGE_RATE_BANK,"+//15
	    	" A.EXCHANGE_RATE_REP_CURR,"+//16
	    	" A.EXCHANGE_GAIN_LOSS,"+//17
	    	" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.DEBTOR_CODE),'-'),"+//18
	    	" A.BATCH_NO,"+//19
	    	" NVL(A.INVOICE_NO,'-'), "+//20
				" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),"+//21
				" NVL("+m_schema_name+".FA_GET_CHARGES_REMARKS(A.FEE_CODE,A.SUS_REF_NO),'-') "+//22
	 			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
				" WHERE A.FACILITY_NO='"+m_facility_no+"' "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
				" AND TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				" AND A.FEE_CODE LIKE '"+m_fee_type+"' "+
				" ORDER BY A.FACILITY_NO,A.EFF_DATE,A.FEE_CODE ");	

		 	String client_code = "";
			boolean mflag=true;	

			boolean more = rs1.next();
			out.println("<HTML><HEAD><TITLE>Client Charges Report </TITLE></HEAD>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD><CENTER><B>Client Charges Report for the Period of "+m_from_date+" to "+m_to_date+"</B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
					
			if(!more){
			 	out.println("<table align='center' width='100%' class='table' >");	
				out.println("<tr>");
				out.println("<td width='*%' align='center' ><DIV class=div_input><b>No Records Available</b></DIV></td>");
				out.println("</tr>"); 	
				out.println("</table>");
			}	
		  else{
				out.println("<table align='center' width='100%' class='table'> ");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input>Client Code </td>");
				out.println("<td width='50%' class=div_input >"+rs1.getString(3)+"</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='1%'></td>"); 
				out.println("<td width='20%' class=div_input>Client Name </td>");
				out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs1.getString(3)+"')\" style='cursor:hand' ><b><u>"+rs1.getString(4)+"</u></b></td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br>");
				out.println("<table align='center' width='100%' class='table' >");	
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width='15%' ><DIV class=div_input><b>Facility No : "+m_facility_no+"</b></DIV></td>");
				out.println("</tr>"); 	
				out.println("</table>");
				
				out.println("<table align='center' width='100%'>");
				out.println("<tr class=pdn_txtpos2>");//class=txt_report_column 
				out.println("<td width='15%' ><DIV class=div_input><b>Charges Ref No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Eff Date</b></DIV></td>");
				//out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
				out.println("<td width='15%' ><DIV class=div_input><b>Fee Description</b></DIV></td>"); 
				out.println("<td width='10%' align='right'><DIV class=div_input><b>Fee Charge Amount</b></DIV></td>");  
				out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
				out.println("<td width='10%' ><DIV class=div_input><b>Remarks</b></DIV></td>");
				out.println("</tr>"); 
					
				while(more){
						if(mflag){
							out.println("<tr class=tr_input>");
							mflag=false;
						}
						else{
							out.println("<tr class=tr_input1>");
							mflag=true;
						}
						out.println("<td width='10%' class=div_input onClick=\"show_charges_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(21)+"</td>");
						//out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
						if(rs1.getString(8).equals("DR")){
						out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
						}
						else {
						out.println("<td width='10%' class=div_input align='right'>("+nf.format(rs1.getDouble(10)*-1)+")</td>");
						}
						out.println("<td width='10%' class=div_input >"+rs1.getString(18)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(20)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(22)+"</td>");
						out.println("</tr>");
						
						m_tot_fee_charge_amount=m_tot_fee_charge_amount+rs1.getDouble(10);
						more = rs1.next();	 
				}
				out.println("<tr>");
				out.println("<td width='10%' class=div_input></td>");
				out.println("<td width='10%' class=div_input></td>");
				//out.println("<td width='10%' class=div_input ></td>");
				out.println("<td width='15%' class=div_input ><b>Total </b></td>");
				out.println("<td width='10%' class=div_input align='right'><b>"+nf.format(m_tot_fee_charge_amount)+"</b></td>");
				out.println("<td width='10%' class=div_input></td>");
				out.println("<td width='10%' class=div_input></td>");
				out.println("<td width='10%' class=div_input></td>");
				out.println("</tr>");
			}
				/*while(more){

						m_eff_date = rs1.getString(9);	

						out.println("<table align='center' width='100%' class='table' >");	
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input><b>Effective Date : "+m_eff_date+"</b></DIV></td>");
						out.println("</tr>"); 	
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Charges Ref No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Date</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Fee Description</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Fee Charge Amount</b></DIV></td>");  
						out.println("<td width='10%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Remarks</b></DIV></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					 
						
					while( (more)  && (m_eff_date.trim().equals(rs1.getString(9))) ){  // && (m_facility_no.trim().equals(rs1.getString(5)))

							m_eff_date = rs1.getString(9);						
							if(mflag){
								out.println("<tr class=tr_input>");
								mflag=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag=true;
							}
							out.println("<td width='10%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_charges_details('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input >"+rs1.getString(21)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='15%' class=div_input >"+rs1.getString(7)+"</td>");
							if(rs1.getString(8).equals("DR")){
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
							}
							else {
							out.println("<td width='10%' class=div_input align='right'>("+nf.format(rs1.getDouble(10)*-1)+")</td>");
							}
							out.println("<td width='10%' class=div_input >"+rs1.getString(18)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(20)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(22)+"</td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							
							m_tot_fee_charge_amount=m_tot_fee_charge_amount+rs1.getDouble(10);
							

						more = rs1.next();	 
					}
							
							out.println("<tr>");
							out.println("<td width='10%'></td>"); 
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='10%' class=div_input ></td>");
							out.println("<td width='20%' class=div_input ><b>Sub Total </b></td>");
							out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_fee_charge_amount)+"</b></td>");
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							m_tot_grand=m_tot_grand+m_tot_fee_charge_amount;
							m_tot_fee_charge_amount=0;
							//m_tot_grand=0;

				}

					out.println("<tr>");
					out.println("<td width='10%'></td>"); 
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='20%' class=div_input ><b>Grand Total</b></td>");
					out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_grand)+"</b></td>");
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='15%' class=div_input></td>");
					out.println("<td width='*%' align='right'></td>");
					out.println("</tr>");
				}	*/
			 	out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
					
				int chk_nums=0;
				int j=1;
				
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


