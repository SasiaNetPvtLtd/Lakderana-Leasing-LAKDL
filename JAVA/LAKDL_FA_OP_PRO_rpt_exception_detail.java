import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;   
       
// DEVELOP BY : MAHELA FOR OFSCL FACTORING    DATE:09-01-2007
      
public class LAKDL_FA_OP_PRO_rpt_exception_detail extends javax.servlet.http.HttpServlet {
	
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
			else if(m_chksql.equals("LOAD_CLIENT_DETAIL_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				String m_status=req.getParameter("status");
				
			//--------------------------CLIENT CLIENT DETAILS REPORT - 1 -------------------------------
			
        rs1= stmt1.executeQuery("SELECT  CLIENT_CODE,"+//1
				" FULL_NAME,"+//2
				" DECODE(CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
				" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//4
				" NVL(KEY_DECISION_MAKER,'-')"+//5
 			  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			  " WHERE ACTIVE_STATUS ='"+m_status+"' AND (FACTORING_TYPE ='C' OR FACTORING_TYPE ='B') ");	
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					 out.println("<HTML><HEAD><TITLE>Client Details Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 
						
					 if(m_status.equals("E")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Client Creation </B></TD></TR>");
					 out.println("</TABLE>");
					 }
					 else	if(m_status.equals("I")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Approved Initial </B></TD></TR>");
					 out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("Y")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Approved Credit </B></TD></TR>");
					 out.println("</TABLE>");												
					 }	
					 else if(m_status.equals("N")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Disapproved Client </B></TD></TR>");
					 out.println("</TABLE>");												
					 }	
					 else if(m_status.equals("T")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Terminated </B></TD></TR>");
					 out.println("</TABLE>");												
					 }							
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
						out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Type</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Key Decision Maker</b></DIV></td>"); 
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
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(5)+"</td>");
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
			else if(m_chksql.equals("LOAD_CLIENT_DETAIL_REPORT_ALL")){
				
				String m_string="";				
				String m_sql="";	
				
			//--------------------------CLIENT CLIENT DETAILS REPORT - 1 -------------------------------
			
        rs1= stmt1.executeQuery("SELECT  CLIENT_CODE,"+//1
				" FULL_NAME,"+//2
				" DECODE(CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
				" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//4
				" NVL(KEY_DECISION_MAKER,'-'),"+//5
				" DECODE(ACTIVE_STATUS,'E','Client Creation','I','Approved Initial','Y','Approved Credit','N','Disapproved Client','T','Terminated'), "+
				" REGISTERED_ADDRESS1 || ' ' || REGISTERED_ADDRESS2 || ' ' || "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE), "+
				" NVL(REGISTERED_OFFICE_TEL_NO,'-') "+
 			    " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE FACTORING_TYPE='C' OR FACTORING_TYPE ='B' ");	
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					out.println("<HTML><HEAD><TITLE>Client Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
					out.println("<tr>");
					out.println("<td width=\"1\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
					out.println("<td class=\"border_wht\" valign=\"top\"> ");
					out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
					out.println("<tr> ");
					out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
					out.println("</tr>");
					out.println("<tr> ");
					out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td style=\"height: 327px\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
					out.println("<tr>");
					out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Client Details Report</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
					out.println("</td>	");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");				
					out.println("</table>");
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
						out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Type</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Address</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Telephone No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Key Decision Maker</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Status</b></DIV></td>"); 
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
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(8)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(6)+"</td>");
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
			else if(m_chksql.equals("LOAD_DEBTOR_DETAIL_REPORT_ALL")){
				
				String m_string="";				
				String m_sql="";	
				
			//--------------------------CLIENT CLIENT DETAILS REPORT - 1 -------------------------------
			
        rs1= stmt1.executeQuery("SELECT  CLIENT_CODE,"+//1
				" FULL_NAME,"+//2
				" DECODE(CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
				" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//4
				" NVL(KEY_DECISION_MAKER,'-'),"+//5
				" DECODE(ACTIVE_STATUS,'E','Client Creation','I','Approved Initial','Y','Approved Credit','N','Disapproved Client','T','Terminated') "+
 			  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT "+
				" WHERE FACTORING_TYPE='D' OR FACTORING_TYPE ='B' ");	
			
         boolean mflag=true;							
		 	   String client_code = "";
			   boolean more = rs1.next();
					
					out.println("<HTML><HEAD><TITLE>Client Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
					out.println("<tr>");
					out.println("<td width=\"1\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
					out.println("<td class=\"border_wht\" valign=\"top\"> ");
					out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
					out.println("<tr> ");
					out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
					out.println("</tr>");
					out.println("<tr> ");
					out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td style=\"height: 327px\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
					out.println("<tr>");
					out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Debtor Details Report</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
					out.println("</td>	");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");				
					out.println("</table>");
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
						out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Type</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Key Decision Maker</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Status</b></DIV></td>"); 
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
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(5)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs1.getString(6)+"</td>");
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
			else if(m_chksql.equals("LOAD_DEBTOR_DETAIL_REPORT")){
			
			String m_status=req.getParameter("status");
			
			//------------------ DEBTOR DETAILS REPORT - 2 -----------------------------
			
      rs2 = stmt2.executeQuery("SELECT  CLIENT_CODE,"+//1
				" FULL_NAME,"+//2
				" DECODE(CLIENT_TYPE,'I','Individual','C','Corporate'),"+//3
				" NVL(REGISTERED_CONTACT_PERSON,'-'),"+//4
				" NVL(KEY_DECISION_MAKER,'-')"+//5
 			  " FROM "+m_schema_name+".FA_CO_MAS_CLIENT  "+
			  " where active_status ='"+m_status+"' and factoring_type ='D' ");	
			
       boolean mflag2=true;							
			 boolean more2 = rs2.next();
				
					out.println("<HTML><HEAD><TITLE>Debtor Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					 if(m_status.equals("E")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Debtor Creation </B></TD></TR>");
					 out.println("</TABLE>");
					 }
					 else	if(m_status.equals("I")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Approved Initial </B></TD></TR>");
					 out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("Y")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Approved Credit </B></TD></TR>");
					 out.println("</TABLE>");												
					 }	
					 else if(m_status.equals("N")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Disapproved Debtor </B></TD></TR>");
					 out.println("</TABLE>");												
					 }	
					 else if(m_status.equals("T")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Details Report - Terminated </B></TD></TR>");
					 out.println("</TABLE>");												
					 }
					
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");

				if(!more2){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
				}
				if(more2){

						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Type</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Key Decision Maker</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
						
				}
				while(more2){
				
							if(mflag2){
								out.println("<tr class=tr_input>");
								mflag2=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag2=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs2.getString(1)+"')\" style='cursor:hand' ><u>"+rs2.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs2.getString(2)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs2.getString(3)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs2.getString(4)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs2.getString(5)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more2 = rs2.next();
				}	
				
        out.println("</table>");
			  out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}	

			else if(m_chksql.equals("LOAD_CLIENT_DEBTOR_ASSIGN_DETAIL_REPORT")){
			
			String m_status=req.getParameter("status");
			
			//------------------CLIENT DEBTOR ASSIGN DETAIL REPORT - 3 -----------------------------
			
      rs6= stmt6.executeQuery("SELECT  FACILITY_NO, "+//1
			" CLIENT_CODE, "+//2
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
			" DEBTOR_CODE, "+//4
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//5
			" NVL(CREDIT_LIMIT,0),"+//6
			" NVL(CREDIT_PERIOD,0),"+//7
			" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//8
			" NVL(RESERVE_MARGIN,0) "+//9
 			" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_DEBTOR "+
			" WHERE RELATION_STATUS='"+m_status+"' ");
			
       boolean mflag6=true;							
			 boolean more6 = rs6.next();
				
					out.println("<HTML><HEAD><TITLE>Client Debtor Assign Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					 if(m_status.equals("N")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Debtor Assign Details Report - Debtor to be Assigned </B></TD></TR>");
					 out.println("</TABLE>");
					 }
					 else	if(m_status.equals("Y")) {	
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Debtor Assign Details Report -  Assigned Approved </B></TD></TR>");
					 out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client Debtor Assign Details Report - Assigned Disapproved </B></TD></TR>");
					 out.println("</TABLE>");												
					 }
					
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");

				if(!more6){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
				}
				if(more6){
				
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Limit</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>");
					out.println("<td width='10%' align='right' ><DIV class=div_input><b>Reverse Margin</b></DIV></td>");
					//out.println("<td width='*%'></td>");
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
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs6.getString(1)+"')\" style='cursor:hand'><u>"+rs6.getString(1)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs6.getString(2)+"')\" style='cursor:hand' ><u>"+rs6.getString(2)+"</u></td>");
							out.println("<td width='15%' class=div_input >"+rs6.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs6.getString(4)+"')\" style='cursor:hand' ><u>"+rs6.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input >"+rs6.getString(5)+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(6))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(7))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(8))+"</td>");
							out.println("<td width='10%' align='right' class=div_input >"+nf.format(rs6.getDouble(9))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more6 = rs6.next();
				}	

				
        out.println("</table>");
			  out.println("<br>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_INVOICE_DETAIL_REPORT")){
			
					String m_status=req.getParameter("status");
			
			//------------------ INVOICE DETAILS REPORT - 4 -----------------------------
		
		
			rs3= stmt3.executeQuery("SELECT  BATCH_NO, "+//1
			" DEBTOR_CODE, "+//2
			" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE), "+//3
			" NVL(INVOICE_NO,'-'), "+//4
			" NVL(INVOICE_AMOUNT,0), "+//5
			" TO_CHAR(INVOICE_DATE,'DD-MM-YYYY')"+//6
 			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
			" WHERE INVOICE_STATUS='"+m_status+"' ");	
			
			
          boolean mflag3=true;							
			 		boolean more3 = rs3.next();
						
						out.println("<HTML><HEAD><TITLE>Invoice Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					 if(m_status.equals("ENTER")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Invoice Details Report - Invoice to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("APPR1")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Invoice Details Report - Invoice Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("CANCEL")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Invoice Details Report - Invoice Disapproved </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("CONF")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Invoice Details Report - Invoice Confirmed </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more3){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more3){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Batch No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Debtor Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input align='right'><b>Invoice Amount</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice Date</b></DIV></td>");
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more3){
				
							if(mflag3){
								out.println("<tr class=tr_input>");
								mflag3=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag3=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_batch_details('"+rs3.getString(1)+"')\" style='cursor:hand'><u>"+rs3.getString(1)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs3.getString(2)+"')\" style='cursor:hand' ><u>"+rs3.getString(2)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs3.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs3.getString(2)+"','"+rs3.getString(4)+"')\" style='cursor:hand' ><u>"+rs3.getString(4)+"</u></td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs3.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input >"+rs3.getString(6)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more3 = rs3.next();

						
					}
				
        	out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_CLIENT_FACILITY_DETAIL_REPORT")){
			
			  String m_status=req.getParameter("status");
			
			//------------------ CLIENT FACILITY DETAIL REPORT - 5 -----------------------------


				rs4= stmt4.executeQuery("SELECT  FACILITY_NO, "+//1
				" CLIENT_CODE, "+//2
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE), "+//3
				" NVL(TO_CHAR(FACILITY_START_DATE,'DD-MM-YYYY'),'-'), "+//4
				" NVL(TO_CHAR(FACILITY_END_DATE,'DD-MM-YYYY'),'-') "+//5
 				" FROM "+m_schema_name+".FA_CR_PRO_CLIENT_FACILITY "+
				" WHERE FACILITY_STATUS='"+m_status+"' ");
			
          boolean mflag4=true;							
			 		boolean more4 = rs4.next();
						
						out.println("<HTML><HEAD><TITLE>Client Facility Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					 if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Client Facility Details Report - Facility to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Client Facility Details Report - Facility Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Client Facility Details Report - Facility Disapproved </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("T")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Client Facility Details Report - Teminated </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }						
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more4){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more4){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility Start Date</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Facility End Date</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
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
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs4.getString(1)+"')\" style='cursor:hand' ><u>"+rs4.getString(1)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_client('"+rs4.getString(2)+"')\" style='cursor:hand' ><u>"+rs4.getString(2)+"</u></td>");
							out.println("<td width='20%' >"+rs4.getString(3)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(4)+"</td>");
							out.println("<td width='10%' >"+rs4.getString(5)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more4 = rs4.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_POD_CHEQUE_DETAIL_REPORT")){
			
			 String m_status=req.getParameter("status");
			
			//------------------ POD CHEQUE STATUS REPORT - 6 -----------------------------
		
				rs5= stmt5.executeQuery("SELECT  A.POD_REF_NO,"+//1
				" NVL(B.CLIENT_CODE,'-'), "+//2
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.CLIENT_CODE),'-'), "+//3
				" NVL(B.FACILITY_NO,'-'), "+//4
				"	NVL(B.BATCH_NO,'-'), "+//5
				" NVL(B.DEBTOR_CODE,'-'),"+//6
				" NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(B.DEBTOR_CODE),'-'), "+//7
				" NVL(B.INVOICE_NO,'-')"+//8
 				" FROM "+m_schema_name+".FA_OP_PRO_POD_CHEQUES A,"+m_schema_name+".FA_OP_PRO_POD_CHEQUES_ALLO B "+
				" WHERE A.POD_STATUS='"+m_status+"' AND "+
				" A.POD_REF_NO = B.POD_REF_NO(+) ");
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>POD Cheque Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> POD Cheque Details Report - POD Cheque Available </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> POD Cheque Details Report - POD Cheque Realised </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> POD Cheque Details Report - POD Cheque Dishonored </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>POD Ref No </b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_pod_cheque_details('"+rs5.getString(1)+"')\" style='cursor:hand'><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(3)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs5.getString(4)+"')\" style='cursor:hand' ><u>"+rs5.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs5.getString(5)+"')\" style='cursor:hand'><u>"+rs5.getString(5)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs5.getString(6)+"')\" style='cursor:hand'><u>"+rs5.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs5.getString(6)+"','"+rs5.getString(8)+"')\" style='cursor:hand'><u>"+rs5.getString(8)+"</u></td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_RECEIPT_DETAIL_REPORT")){
			
			String m_status=req.getParameter("status");
			
			//------------------ SETTLEMENT RECEIPT REPORT - 7 -----------------------------
		
				
 				rs7= stmt7.executeQuery("SELECT  RECEIPT_NO,"+//1
  			"  DECODE(SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash','BANKTR','Bank Transfer'),"+//2
  			"  NVL(PAYER_BRANCH_CODE,'-'),"+//3
				"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//4
  			"  NVL(PAYER_ACC_NO,'-'),"+//5
  			"  NVL(REC_AMOUNT,0),"+//6
  		 	"  NVL(BALANCE_AMOUNT,0) "+//7  
 				"  FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				"  WHERE REC_STATUS='"+m_status+"' "); 

			
          boolean mflag7=true;							
			 		boolean more7 = rs7.next();
						
						out.println("<HTML><HEAD><TITLE>Settlement Receipt Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
  				if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt to be Deposited </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("B")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Deposited </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Returned </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("Y")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Realised </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }		
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more7){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more7){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Payer Branch Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Payer Account No</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more7){
				
							if(mflag7){
								out.println("<tr class=tr_input>");
								mflag7=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag7=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_receipt_details('"+rs7.getString(1)+"')\"  style='cursor:hand'><u>"+rs7.getString(1)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs7.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs7.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs7.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input align='right' >"+nf.format(rs7.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs7.getDouble(7))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more7 = rs7.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_RECEIPT_DETAIL_REPORT_EX")){
			
			String m_status=req.getParameter("status");
			String m_settle_mode=req.getParameter("settle_mode");
			
			//------------------ SETTLEMENT RECEIPT EXPANSION  -----------------------------
		
				
 				rs7= stmt7.executeQuery("SELECT  RECEIPT_NO,"+//1
  			"  NVL(PAYER_BRANCH_CODE,'-'),"+//2
				"  NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(PAYER_BRANCH_CODE),'-'),"+//3
  			"  NVL(PAYER_ACC_NO,'-'),"+//4
  			"  NVL(REC_AMOUNT,0),"+//5
  		 	"  NVL(BALANCE_AMOUNT,0) "+//6  
 				"  FROM "+m_schema_name+".FA_OP_PRO_SETTL_RECEIPT "+
				"  WHERE REC_STATUS='"+m_status+"' AND SETTLE_MODE='"+m_settle_mode+"' "); 

			
          boolean mflag7=true;							
			 		boolean more7 = rs7.next();
						
						out.println("<HTML><HEAD><TITLE>Settlement Receipt Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					 if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt to be Deposited </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("B")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Deposited </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Returned </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("Y")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Settlement Receipt Details Report - Receipt Realised </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }		

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more7){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more7){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Receipt No</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Payer Branch Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Payer Account No</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Receipt Amount</b></DIV></td>"); 
						out.println("<td width='10%' align='right' ><DIV class=div_input><b>Balance Amount</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more7){
				
							if(mflag7){
								out.println("<tr class=tr_input>");
								mflag7=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag7=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input onClick=\"show_receipt_details('"+rs7.getString(1)+"')\" style='cursor:hand'><u>"+rs7.getString(1)+"</u></td>");
							out.println("<td width='20%' class=div_input >"+rs7.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs7.getString(4)+"</td>");
							out.println("<td width='10%' class=div_input  align='right' >"+nf.format(rs7.getDouble(5))+"</td>");
							out.println("<td width='10%' class=div_input  align='right' >"+nf.format(rs7.getDouble(6))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more7 = rs7.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_SETTLEMENT_DEPOSIT_DETAIL_REPORT")){
			
				String m_status=req.getParameter("status");
				
			// ------------------ SETTLEMENT DEPOSIT REPORT - 8 -----------------------------
		
				rs8 = stmt8.executeQuery(" SELECT	 DEPOSIT_NO,"+//1
  			"	TO_CHAR(DEPOSIT_DATE,'DD-MM-YYYY'),"+//2
    		"	NVL(DEPOSIT_COMMENTS,'-'),"+//3
    		"	NVL(ACC_NO,'-'),"+//4
    		"	NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-'),"+//5
    		"	NVL(DEPOSIT_TOTAL,0) "+//6
 				"	FROM "+m_schema_name+".FA_OP_PRO_DEPOSIT "+
				" WHERE STATUS='"+m_status+"' "); 	

			
          boolean mflag8=true;							
			 		boolean more8 = rs8.next();
						
						out.println("<HTML><HEAD><TITLE>Deposit Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

  				if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Deposit Details Report - Deposit Realised </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("C")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Deposit Details Report - Deposit Returned </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more8){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more8){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Deposit No</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Deposit Date</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Deposit Comment</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Account No</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Branch Name</b></DIV></td>"); 
						out.println("<td width='15%' align='right'><DIV class=div_input><b>Deposit Total</b></DIV></td>"); 
						out.println("</tr>"); 
					}
					while(more8){
				
							if(mflag8){
								out.println("<tr class=tr_input>");
								mflag8=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag8=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_deposit_details('"+rs8.getString(1)+"')\" style='cursor:hand' ><u>"+rs8.getString(1)+"</u></td>");
							out.println("<td width='10%' class=div_input >"+rs8.getString(2)+"</td>");
							out.println("<td width='20%' class=div_input >"+rs8.getString(3)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs8.getString(4)+"</td>");
							out.println("<td width='15%' class=div_input >"+rs8.getString(5)+"</td>");
							out.println("<td width='15%' align='right' class=div_input >"+nf.format(rs8.getDouble(6))+"</td>");
							out.println("</tr>");
							more8 = rs8.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_ADJUSTMENTS_DETAIL_REPORT")){
			
			String m_status=req.getParameter("status");
			
			//------------------ ADJUSTMENTS STATUS REPORT - 9 -----------------------------
		
				
 				rs9= stmt9.executeQuery("SELECT  ADJUSTMENT_NO,"+//1
    		"NVL(FACILITY_NO,'-'),"+//2
    		"NVL(CLIENT_CODE,'-'),"+//3
				"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//4
    		"NVL(BATCH_NO,'-'),"+//5
    		"NVL(DEBTOR_CODE,'-'),"+//6
				"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//7
    		"NVL(INVOICE_NO,'-'),"+//8
    		"NVL(SOURCE_DOCUMENT,'-') "+//9
 				"FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
				"WHERE INVOICE_STATUS='"+m_status+"' ");	

			
          boolean mflag9=true;							
			 		boolean more9 = rs9.next();
						
						out.println("<HTML><HEAD><TITLE>Adjustment Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>");
						
  				if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Adjustments Details Report - Adjustment Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Adjustments Details Report - Adjustment to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more9){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more9){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Source Document</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more9){
				
							if(mflag9){
								out.println("<tr class=tr_input>");
								mflag9=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag9=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_adjustment_details('"+rs9.getString(1)+"')\" style='cursor:hand' ><u>"+rs9.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_facility('"+rs9.getString(2)+"')\" style='cursor:hand'><u>"+rs9.getString(2)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs9.getString(3)+"')\" style='cursor:hand' ><u>"+rs9.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs9.getString(5)+"')\" style='cursor:hand' ><u>"+rs9.getString(5)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs9.getString(6)+"')\" style='cursor:hand'><u>"+rs9.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs9.getString(6)+"','"+rs9.getString(8)+"')\" style='cursor:hand' ><u>"+rs9.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(9)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more9 = rs9.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}	
			else if(m_chksql.equals("LOAD_ADJUSTMENTS_DETAIL_REPORT_EX")){
			
			String m_status=req.getParameter("status");
			String m_adjust_category=req.getParameter("adjust_category");
			
			//------------------ ADJUSTMENTS EXPANSION REPORT - 9 -----------------------------
		
				
 				rs9= stmt9.executeQuery("SELECT  ADJUSTMENT_NO,"+//1
    		"NVL(FACILITY_NO,'-'),"+//2
    		"NVL(CLIENT_CODE,'-'),"+//3
				"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE),'-'), "+//4
    		"NVL(BATCH_NO,'-'),"+//5
    		"NVL(DEBTOR_CODE,'-'),"+//6
				"NVL("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE),'-'), "+//7
    		"NVL(INVOICE_NO,'-'),"+//8
    		"NVL(SOURCE_DOCUMENT,'-') "+//9
 				"FROM "+m_schema_name+".FA_CR_PRO_ADJUSTMENTS "+
				"WHERE INVOICE_STATUS='"+m_status+"' AND ADJUST_CATEGORY='"+m_adjust_category+"' ");	

			
          boolean mflag9=true;							
			 		boolean more9 = rs9.next();
						
						out.println("<HTML><HEAD><TITLE>Adjustment Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
  				 if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Adjustments Details Report - Adjustment Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Adjustments Details Report - Adjustment to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }
						
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");
					
					if(!more9){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}					
					if(more9){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Adjustment No</b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
						out.println("<td width='20%' ><DIV class=div_input><b>Client Name</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Batch No</b></DIV></td>"); 
						out.println("<td width='20%' ><DIV class=div_input><b>Debtor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Invoice No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Source Document</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more9){
				
							if(mflag9){
								out.println("<tr class=tr_input>");
								mflag9=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag9=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_adjustment_details('"+rs9.getString(1)+"')\" style='cursor:hand' ><u>"+rs9.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_facility('"+rs9.getString(2)+"')\" style='cursor:hand'><u>"+rs9.getString(2)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs9.getString(3)+"')\" style='cursor:hand' ><u>"+rs9.getString(4)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_invoice_batch_details('"+rs9.getString(5)+"')\" style='cursor:hand' ><u>"+rs9.getString(5)+"</u></td>");
							out.println("<td width='20%' class=div_input onClick=\"show_client('"+rs9.getString(6)+"')\" style='cursor:hand'><u>"+rs9.getString(7)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_invoice_details('"+rs9.getString(6)+"','"+rs9.getString(8)+"')\" style='cursor:hand' ><u>"+rs9.getString(8)+"</u></td>");
							out.println("<td width='10%' class=div_input  >"+rs9.getString(9)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more9 = rs9.next();
							
					}		
					
					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			}
			else if(m_chksql.equals("LOAD_QUOTATION_DETAIL_REPORT")){
			
			 String m_status=req.getParameter("status");
			
			//------------------ QUOTATION DETAIL REPORT - 10 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0) "+//8
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
				"	WHERE QUOTATION_STATUS='"+m_status+"' ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					if(m_status.equals("N")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Quotation Details Report - Quotation to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("Y")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Quotation Details Report - Quotation Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("C")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Quotation Details Report - Quotation Disapproved </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_QUOTATION_DETAIL_ALL")){
			
			//------------------ QUOTATION DETAIL REPORT - 10 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0), "+//8
				" DECODE(QUOTATION_STATUS,'Y','Quotation Approved','N','Quotation Disapproved','C','Quotation to be Approved') "+//9
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
					out.println("<HTML><HEAD><TITLE>Quotation Details Report </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
					out.println("<tr>");
					out.println("<td width=\"1\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
					out.println("<td class=\"border_wht\" valign=\"top\"> ");
					out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
					out.println("<tr> ");
					out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
					out.println("</tr>");
					out.println("<tr> ");
					out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td style=\"height: 327px\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
					out.println("<tr>");
					out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing  - Quotation Detail</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td  class=\"pdn_txtpos\" style=\"height: 28px\">");
					out.println("</td>	");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
					out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");				
					out.println("</table>");
				
					out.println("<BR>");
					out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Status</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(9)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_PAYMENT_DETAIL_REPORT")){
			
			 String m_status=req.getParameter("status");
			
			//------------------ PAYMENT DETAIL REPORT - 11 -----------------------------

				rs5= stmt5.executeQuery(" SELECT  PAYMENT_CODE,"+//1
  		  "	NVL(CLIENT_CODE,'-'), "+//2
  		  "	NVL(FACILITY_NO,'-'),"+//3
  		  "	NVL(PAYMENT_AMOUNT,0),"+//4
  		  "	NVL(TO_CHAR(PAY_DATE,'DD-MM-YYYY'),'-'),"+//5
  		  "	NVL(SETTLE_MODE,'-'),"+//6
  		  "	NVL(CHEQUE_NO,'-'),"+//7
				"	NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'),"+//8
  		  "	NVL(REC_AMOUNT_CURR,0),"+//9
  		  "	NVL(EXCHANGE_RATE_BANK,0), "+//10
  		  "	NVL(EXCHANGE_RATE_REP_CURR,0), "+//11
  		  "	NVL(EXCHANGE_GAIN_LOSS,0), "+//12
				" "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(CLIENT_CODE) "+//13
 				"	FROM "+m_schema_name+".FA_OP_PRO_CLIENT_PAYMENTS "+
				" WHERE PAY_STATUS='"+m_status+"'ORDER BY PAYMENT_CODE DESC ");
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Payment Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 
						
					if(m_status.equals("ENTER")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment to be Approved </B></TD></TR>");
					 	out.println("</TABLE>");
					 }
					 else	if(m_status.equals("APPR1")) {	
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment Approved </B></TD></TR>");
					 	out.println("</TABLE>");						
					 }	
					 else if(m_status.equals("CONF")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment Confirmed </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("CANCEL")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment Disapproved </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }	 
					 else if(m_status.equals("DISB")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment Disburse </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }
					 else if(m_status.equals("PRINT")){
					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Payment Details Report - Payment Cheques Print </B></TD></TR>");
					 	out.println("</TABLE>");												
					 }	 

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Payment Code </b></DIV></td>");
						out.println("<td width='12%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Payment Amount</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Payment Date</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Settle Mode</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Cheque Date</b></DIV></td>"); 
						//out.println("<td width='10%' align='right'><DIV class=div_input><b>Rep. Currency Amount</b></DIV></td>"); 
						//out.println("<td width='10%' align='right'><DIV class=div_input><b>Exchange Rate Bank</b></DIV></td>"); 
						//out.println("<td width='10%' align='right'><DIV class=div_input><b>Exchange Rate Rep. Currency</b></DIV></td>"); 
						//out.println("<td width='10%' align='right'><DIV class=div_input><b>Exchange Gain Loss</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='12%' class=div_input onClick=\"show_payment_details('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='12%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(13)+"</u></td>");
							out.println("<td width='10%' class=div_input onClick=\"show_facility('"+rs5.getString(3)+"')\" style='cursor:hand' ><u>"+rs5.getString(3)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='8%' class=div_input onClick=\"\" >"+rs5.getString(5)+"</td>");
							out.println("<td width='8%' class=div_input onClick=\"\" >"+rs5.getString(6)+"</td>");
							out.println("<td width='8%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='8%' class=div_input onClick=\"\" >"+rs5.getString(8)+"</td>");
							//out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(9))+"</td>");
							//out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(10))+"</td>");
							//out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(11))+"</td>");
							//out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(12))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
else if(m_chksql.equals("LOAD_ALLOCATED_INQUIRY_DETAIL_REPORT")){
			
			//------------------ ALLOCATED INQUIRY DETAIL REPORT - 12 -----------------------------

 				// ALLOCATED INQUIRIES
  			rs5= stmt5.executeQuery(" SELECT INQUIRY_CODE,"+//1
  		  "	NVL(CLIENT_NAME,'-'), "+//2
    		"	NVL(TEL_NO,'-'),"+//3
    		" NVL(MOBILE_NO,'-'),"+//4
    		" NVL(FAX_NO,'-'),"+//5
   			" NVL(LEGAL_ENTITY,'-'),"+//6
    		" NVL(INITIATION_TYPE,'-'),"+//7
  	 		" NVL(CLIENT_CATEGORY,'-'),"+//8
    		" NVL(LEAD_SOURCE_CATEGORY,'-'),"+//9
  			" NVL(LEAD_SOURCE_NAME,'-'),"+//10
    		" NVL(INTRODUCER,'-'),"+//11
    		" NVL(ID_NO,'-'),"+//12
    		" NVL(EMAIL,'-'),"+//13
    		" NVL(TEAM,'-'), "+//14
   			" NVL(MK_OFFICER,'-'),"+//15
    		" NVL(MK_SUPERVISOR,'-'),"+//16
  	  	" NVL(CONTACT_PERSON,'-') "+//17
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
 				" WHERE DIVISION_CODE='FA' AND "+
 				" INQUIRY_CODE IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO) ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Allocated Inquiry Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Allocated Inquiry Details Report </B></TD></TR>");
					 	out.println("</TABLE>");

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Inquiry Code </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Tel No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Mobile No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Fax No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Legal Entity</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Initiation Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Introducer</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>ID No/Business Cert. No. </b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>E-mail</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Team</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Marketing Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Marketing Supervisor</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input >"+rs5.getString(1)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(2)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(4)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(14)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(15)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(16)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(17)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
else if(m_chksql.equals("LOAD_UNALLOCATED_INQUIRY_DETAIL_REPORT")){

			
			//------------------ UNALLOCATED INQUIRY DETAIL REPORT - 13 -----------------------------

 				// UNALLOCATED INQUIRIES
  			rs5= stmt5.executeQuery(" SELECT INQUIRY_CODE,"+//1
  		  "	NVL(CLIENT_NAME,'-'), "+//2
    		"	NVL(TEL_NO,'-'),"+//3
    		" NVL(MOBILE_NO,'-'),"+//4
    		" NVL(FAX_NO,'-'),"+//5
   			" NVL(LEGAL_ENTITY,'-'),"+//6
    		" NVL(INITIATION_TYPE,'-'),"+//7
  	 		" NVL(CLIENT_CATEGORY,'-'),"+//8
    		" NVL(LEAD_SOURCE_CATEGORY,'-'),"+//9
  			" NVL(LEAD_SOURCE_NAME,'-'),"+//10
    		" NVL(INTRODUCER,'-'),"+//11
    		" NVL(ID_NO,'-'),"+//12
    		" NVL(EMAIL,'-'),"+//13
    		" NVL(TEAM,'-'), "+//14
   			" NVL(MK_OFFICER,'-'),"+//15
    		" NVL(MK_SUPERVISOR,'-'),"+//16
  	  	" NVL(CONTACT_PERSON,'-') "+//17
 				" FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
 				" WHERE DIVISION_CODE='FA' AND "+
 				" INQUIRY_CODE NOT IN(SELECT INQUIRY_CODE FROM "+m_schema_name+".FA_MK_PRO_INQUIRY_ALLO) ");

			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Unallocated Inquiry Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Unallocated Inquiry Details Report </B></TD></TR>");
					 	out.println("</TABLE>");

						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Inquiry Code </b></DIV></td>");
						out.println("<td width='10%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
						out.println("<td width='8%' ><DIV class=div_input><b>Tel No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Mobile No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Fax No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Legal Entity</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Initiation Type</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Client Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Category</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Lead Source Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Introducer</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>ID No/Business Cert. No.</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>E-mail</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Team</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Marketing Officer</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Marketing Supervisor</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Contact Person</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='10%' class=div_input >"+rs5.getString(1)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(2)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(3)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(4)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(5)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(6)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(8)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(9)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(10)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(11)+"</td>");
							out.println("<td width='8%' class=div_input >"+rs5.getString(12)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(13)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(14)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(15)+"</td>");
							out.println("<td width='12%' class=div_input >"+rs5.getString(16)+"</td>");
							out.println("<td width='10%' class=div_input >"+rs5.getString(17)+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}

			else if(m_chksql.equals("LOAD_ALLOCATED_QUOTATION_DETAIL_REPORT")){

			
			//------------------ ALLOCATED QUOTATION DETAIL REPORT - 14 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0) "+//8
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
				" WHERE QUOTATION_NO IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");	
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Allocated Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Allocated Quotation Details Report </B></TD></TR>");
					 	out.println("</TABLE>");
							
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
					}		

					out.println("</table>");
			  	out.println("<br>"); 
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			
			}
			else if(m_chksql.equals("LOAD_UNALLOCATED_QUOTATION_DETAIL_REPORT")){

			
			//------------------ UNALLOCATED QUOTATION DETAIL REPORT - 15 -----------------------------
	
 				rs5= stmt5.executeQuery("SELECT  QUOTATION_NO,"+//1
  		  " NVL(CLIENT_CODE,'-'),"+//2
  		  " NVL(CREDIT_LIMIT,0),"+//3
  	  	" NVL(CREDIT_PERIOD,0),"+//4
   	 		" NVL(TOLERANCE_CREDIT_PERIOD,0),"+//5
  	 	 	" NVL(RESERVE_MARGIN,0),"+//6
    		" NVL(APPROVAL_COMMENTS,'-'),"+//7
    		" NVL(INT_RATE,0) "+//8
 				" FROM "+m_schema_name+".FA_MK_PRO_QUOTATION "+
				" WHERE QUOTATION_NO NOT IN( SELECT QUOTATION_NO FROM "+m_schema_name+".FA_CR_PRO_QUOTA_ALLO_FACTY )");	
			
          boolean mflag5=true;							
			 		boolean more5 = rs5.next();
						out.println("<HTML><HEAD><TITLE>Unallocated Quotation Details Report </TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>"); 

					 	out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 	out.println("<TR><TD align='Center' ><B> Unallocated Quotation Details Report </B></TD></TR>");
					 	out.println("</TABLE>");
							
						out.println("<BR>");
						out.println("<table align='center' width='100%' class='table' >");

					if(!more5){
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");
					}
					if(more5){
						out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
						out.println("<td width='1%'></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Quotation No </b></DIV></td>");
						out.println("<td width='15%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Limit</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Tolerance Credit Period</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Reverse Margin</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Approval Comment</b></DIV></td>"); 
						out.println("<td width='10%' align='right'><DIV class=div_input><b>Interest Rate</b></DIV></td>"); 
						//out.println("<td width='*%'></td>");
						out.println("</tr>"); 
					}
					while(more5){
				
							if(mflag5){
								out.println("<tr class=tr_input>");
								mflag5=false;
							}
							else{
								out.println("<tr class=tr_input1>");
								mflag5=true;
							}
							out.println("<td width='1%'></td>"); 
							out.println("<td width='15%' class=div_input onClick=\"show_quotation('"+rs5.getString(1)+"')\" style='cursor:hand' ><u>"+rs5.getString(1)+"</u></td>");
							out.println("<td width='15%' class=div_input onClick=\"show_client('"+rs5.getString(2)+"')\" style='cursor:hand' ><u>"+rs5.getString(2)+"</u></td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(3))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(4))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(5))+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(6))+"</td>");
							out.println("<td width='10%' class=div_input onClick=\"\" >"+rs5.getString(7)+"</td>");
							out.println("<td width='10%' align='right' class=div_input onClick=\"\" >"+nf.format(rs5.getDouble(8))+"</td>");
							//out.println("<td width='*%'></td>");
							out.println("</tr>");
							more5 = rs5.next();
							
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



