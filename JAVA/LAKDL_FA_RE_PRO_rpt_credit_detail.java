import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
          
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007

public class LAKDL_FA_RE_PRO_rpt_credit_detail extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt4,stmt6;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs4,rs6;
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
		
			stmt4=conn.createStatement();
			stmt6=conn.createStatement();
			
			String m_client_code=req.getParameter("client_code");
			String m_facility_no=req.getParameter("facility_no");
			String m_eff_date = "";
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CLIENT_FACILITY_DETAIL_REPORT")){

			
			//------------------ CLIENT FACILITY DETAIL REPORT ------------------------------

				rs4= stmt4.executeQuery("SELECT  FACILITY_NO, "+//1
  			 " NVL(CLIENT_CODE,'-'), "+//2
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//3
  			 " NVL(FACILITY_MGR_CODE,'-'), "+//4
  			 " NVL("+m_schema_name+".FA_GET_CLIENT_MANAGER(FACILITY_MGR_CODE,'O','O'),'-'), "+//5
  			 " NVL(FA_PRODUCT_CODE,0), "+//6
  			 " NVL("+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),'-'), "+//7
  			 " NVL(FEE_PACK_CODE,'-'), "+//8
  			 " NVL("+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE),'-'), "+//9
  			 " NVL(CREDIT_LIMIT,0), "+//10
  			 " NVL(CREDIT_PERIOD,0), "+//11
  			 " NVL(TOLERANCE_CREDIT_PERIOD,0), "+//12
  			 " NVL(RESERVE_MARGIN,0), "+//13
  			 " TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'), "+//14
  			 " DECODE(FACILITY_STATUS,'Y','Facility Approved','N','Facility to be Approved','C',' Facility Disapproved','T','Teminated'), "+//15
  			 " NVL(INT_RATE,0) "+//16
 				"FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
 				"WHERE FACILITY_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_code+"' ");

			
          boolean mflag4=true;							
			 		boolean more4 = rs4.next();
						
						out.println("<HTML><HEAD><TITLE>Client Facility Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Client Facility Details Report  [Period - From : "+m_from_date+" To : "+m_to_date+"] Against Facility Start Date </B></TD></TR>");
					 	out.println("</TABLE>");
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more4){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more4){
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs4.getString(1)+"')\" style='cursor:hand' ><u>"+rs4.getString(1)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
						out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs4.getString(2)+"')\" style='cursor:hand' ><u>"+rs4.getString(2)+"</u></td>");
						out.println("<td width='*%'></td>");
						out.println("</tr>");
						out.println("</table>");
						out.println("<br><br>");
						out.println("<table align='center' width='100%' class='table' >");
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='15%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Product Pack. Name</b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Fee Pack. Name</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit </b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tol Credit Period </b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Start Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Status</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more4){
				
							if(mflag4){
								out.println("<tr class=tr_input>");
								mflag4=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag4=true;
							}
							out.println("<td width='15%' >"+rs4.getString(5)+"</td>");
							out.println("<td width='15%' >"+rs4.getString(7)+"</td>");
							out.println("<td width='15%' >"+rs4.getString(9)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs4.getDouble(10))+"</td>");
							out.println("<td width='10%' align='right'>"+rs4.getString(11)+"</td>");
							out.println("<td width='10%' align='right'>"+rs4.getString(12)+"</td>");
							out.println("<td width='10%' align='right'>"+nf.format(rs4.getDouble(13))+"</td>");
							out.println("<td width='10%' >"+rs4.getString(14)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(15)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(16)+"</td>");
							out.println("</tr>");
							more4 = rs4.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_CLIENT_DEBTOR_ASSIGN_DETAIL_REPORT")){
			
			//------------------CLIENT DEBTOR ASSIGN DETAIL REPORT ------------------------------
	
		 rs6= stmt6.executeQuery("SELECT  FACILITY_NO, "+//1
    	" NVL(CLIENT_CODE,'-'), "+//2
  	  " NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//3
    	" NVL(DEBTOR_CODE,'-'), "+//4
    	" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//5
  	  " NVL(MKT_CODE,'-'), "+//6
  	  " NVL(INITCAP("+m_schema_name+".FA_GET_CLIENT_MANAGER(MKT_CODE,'O','O')),'-'), "+//7
  	  " NVL(FA_PRODUCT_CODE,'-'), "+//8
			" "+m_schema_name+".FA_GET_PRODUCT_PACK_NAME(FA_PRODUCT_CODE),"+//9
  	  " NVL(FEE_PACK_CODE,'-'), "+//10
			" "+m_schema_name+".FA_GET_FEE_PACK_NAME(FEE_PACK_CODE), "+//11
  	  " NVL(CREDIT_LIMIT,0), "+//12
  	  " NVL(CREDIT_PERIOD,0), "+//13
  	  " NVL(TOLERANCE_CREDIT_PERIOD,0), "+//14
  	  " NVL(RESERVE_MARGIN,0), "+//15
  	  " DECODE(RELATION_STATUS,'N','Debtor to be Assigned','Y','Assigned Approved','C','Assigned Disapproved') "+//16
 		"FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
 		"WHERE FACILITY_NO='"+m_facility_no+"' AND CLIENT_CODE='"+m_client_code+"' ");

			
       boolean mflag6=true;							
			 boolean more6 = rs6.next();
				
					out.println("<HTML><HEAD><TITLE>Client Debtor Assign Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> Client Debtor Assign Details Report </B></TD></TR>");
					out.println("</TABLE>");												
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");

				if(!more6){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
				}
				if(more6){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Facility No</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_facility('"+rs6.getString(1)+"')\" style='cursor:hand' ><u>"+rs6.getString(1)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>Client Name</b></td>");
					out.println("<td width='50%' class=div_input onClick=\"show_client('"+rs6.getString(2)+"')\" style='cursor:hand' ><u>"+rs6.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br><br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='12%' ><DIV class=div_input><b>Facility Manager</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Product Pack. Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Fee Pack. Name</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Tol. Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Reverse Margin</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Relation Status</b></DIV></td>");
					out.println("</tr>"); 
				}
				while(more6){
				
							if(mflag6){
								out.println("<tr class=tr_input>");
								mflag6=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag6=true;
							}
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs6.getString(4)+"')\" style='cursor:hand' ><u>"+rs6.getString(5)+"</u></td>");
							out.println("<td width='12%' class=div_input >"+rs6.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(9)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(11)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(12))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+rs6.getInt(13)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+rs6.getInt(14)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(15))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs6.getString(16)+"</td>");
							out.println("</tr>");
							more6 = rs6.next();
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



