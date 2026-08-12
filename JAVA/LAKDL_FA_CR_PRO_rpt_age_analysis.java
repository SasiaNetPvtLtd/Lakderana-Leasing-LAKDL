import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:16-01-2007

public class LAKDL_FA_CR_PRO_rpt_age_analysis extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_INVOICE_AGE_ANALYSIS_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_eff_date=req.getParameter("eff_date");
				String m_facility_no = "";
				String m_eff_date = "";
				
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
    	" A.FEE_DESC,"+//7
    	" A.DRCR_STATUS,"+//8
    	" TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),"+//9
    	"	NVL(A.FEE_CHARGE_AMOUNT,0),"+//10
    	" NVL(A.SETTLE_FEE_AMOUNT,0),"+//11
    	" NVL(A.BAL_FEE_AMOUNT,0),"+//12
  	  " A.CURR_CODE,"+//13
    	" A.REC_AMOUNT_CURR,"+//14
  	 	" A.EXCHANGE_RATE_BANK,"+//15
    	" A.EXCHANGE_RATE_REP_CURR,"+//16
    	" A.EXCHANGE_GAIN_LOSS,"+//17
    	" A.DEBTOR_CODE,"+//18
    	" A.BATCH_NO,"+//19
    	" A.INVOICE_NO"+//20
 			" FROM "+m_schema_name+".FA_OP_PRO_CLIENT_CHARGES A "+
			" WHERE   A.CLIENT_CODE='"+m_client_code+"' AND "+
			" TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
			" TO_DATE(TO_CHAR(A.EFF_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			" ORDER BY A.CHARGES_REF_NO ");	

		 	String client_code = "";

				boolean more = rs1.next();
					out.println("<HTML><HEAD><TITLE>Client Charges Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Client Charges Report for the Period of "+m_from_date+" to "+m_to_date+"</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
				if(more){
					
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
					m_facility_no = rs1.getString(5);
					
				}	
				
				while(more){
				
					 	
							
						m_facility_no = rs1.getString(5);
						m_eff_date = rs1.getString(9);	
						
						out.println("<table align='center' width='100%' class='table' >");	
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input><b>Facility No : "+m_facility_no+"</b></DIV></td>");
						out.println("</tr>"); 	
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");	
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='15%' ><DIV class=div_input><b>EFF Date : "+m_eff_date+"</b></DIV></td>");
						out.println("</tr>"); 	
						out.println("</table>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='10%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Date</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Fee Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Fee Description</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>Fee Charge Amount</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>Settle Fee Amount</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>Balance Fee Amount</b></DIV></td>"); 
						out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					 
						
					while( (more) && (m_facility_no.trim().equals(rs1.getString(5))) && (m_eff_date.trim().equals(rs1.getString(9))) ){
					
					 	
						boolean mflag=true;

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
							out.println("<td width='15%' class=div_input>"+rs1.getString(9)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(6)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(10))+"</td>");
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
							out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							
							m_tot_fee_charge_amount=m_tot_fee_charge_amount+rs1.getDouble(10);
							m_tot_settle_fee_amount=m_tot_settle_fee_amount+rs1.getDouble(11);
							m_tot_bal_fee_amount=m_tot_bal_fee_amount+rs1.getDouble(12);
							m_tot_grand=m_tot_fee_charge_amount+m_tot_settle_fee_amount+m_tot_bal_fee_amount;

						more = rs1.next();	 
					}
							
							out.println("<tr>");
							out.println("<td width='10%'></td>"); 
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='10%' class=div_input ></td>");
							out.println("<td width='20%' class=div_input ><b>Sub Total </b></td>");
							out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_fee_charge_amount)+"</b></td>");
							out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_settle_fee_amount)+"</b></td>");
							out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_bal_fee_amount)+"</b></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td width='10%'></td>"); 
							out.println("<td width='15%' class=div_input></td>");
							out.println("<td width='10%' class=div_input ></td>");
							out.println("<td width='20%' class=div_input ></td>");
							out.println("<td width='15%' class=div_input align='right'></td>");
							out.println("<td width='15%' class=div_input align='right'><b>Grand Total </b></td>");
							out.println("<td width='15%' class=div_input align='right'><b>"+nf.format(m_tot_grand)+"</b></td>");
							out.println("<td width='*%'></td>");
							out.println("</tr>");
							m_tot_fee_charge_amount=0;
							m_tot_settle_fee_amount=0;
							m_tot_bal_fee_amount=0;
							m_tot_grand=0;

				}
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


