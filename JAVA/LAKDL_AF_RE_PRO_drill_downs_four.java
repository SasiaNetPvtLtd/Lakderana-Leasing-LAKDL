import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : delanjali FOR OFSCL LEASING    DATE:20-04-2007

public class LAKDL_AF_RE_PRO_drill_downs_four extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt,stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
    public ResultSet rs,rs1,rs2;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
		Statement stmt=null,stmt1=null,stmt2=null;
		CallableStatement callstmt=null;
		java.text.NumberFormat nf=null,nf1=null;
		java.lang.Math a=null;
	     ResultSet rs=null,rs1=null,rs2=null;
		 String m_chksql=null;
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			//**************************************************************					
			//**************************************************************					
			//**************************************************************					
			//--MODIFIED By :delanjali----------------------------------------
			//--DATE				: 2007-07-27--------------------------------------
			
			
			
			String m_html_client_url1=m_sn_methods.html_client_url.trim(); 

			String url = "";
			if(req.getParameter("url")!=null){
			url=req.getParameter("url");
			}
			
			if(url.equals("http://www.lakdac.lk")){
	 		m_html_client_url="http://www.lakdac.lk"; 
			m_class_url="http://www.lakdac.lk:/myserver/servlet"; 
			}
		
			else{
		
			m_html_client_url=m_sn_methods.html_client_url.trim(); 
			}


			
			//---------------------------------------------------------------
	
			
			
			
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
			stmt =conn.createStatement();
			stmt2=conn.createStatement();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
				
				
			else if(m_chksql.equals("SHOW_SUS_REF_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_sus_ref_no=req.getParameter("sus_ref_no");		
				String m_sus_no="";
				

						rs= stmt1.executeQuery("SELECT "+	
																	"SUS_REF_NO, "+	
																	"REF_NO, "+	
																	"DECODE(SUSPENSE_ENTRY_TYPE,'V','Vendor','S','Supplier','E','Seizer'), "+
																	"RECEIVER, "+
																	"PAYER, "+
																	"TOT_SETTLE_AMOUNT, "+
																	"INT_BAL_SETTLE_AMOUNT, "+
																	"BAL_TO_BE_PAID, "+				
																	//"PAY_FROM_INT_BAL_AMT_CURR, "+
																	//"BAL_TO_BE_PAID_CURR, "+		
																	"CURR_CODE, "+							
																	"EXCHANGE_RATE, "+					
																	"TO_CHAR(VALUE_DATE,'DD-MM-YYYY') "+
																	"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT "+
																	"WHERE UPPER(SUS_REF_NO)=UPPER('"+m_sus_ref_no+"') ");
																
		
					boolean more_sus = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Suspense Payment Details - Payment No : "+m_sus_ref_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Suspense Payment Details - Suspense Payment No : "+m_sus_ref_no+" </B></TD></TR>");
					out.println("</TABLE>");
					
					out.println("<BR><BR>");
				
        if (!more_sus) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Suspense Payment No  "+m_sus_ref_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
					out.println("<table align='center' width='100%' class='table' border=0 >");

				while(more_sus){
				m_sus_no=rs.getString(1);

					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sus Ref No</b></td>");
					out.println("<td width='50%' class=div_input>: "+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Ref No</b></td>");
					out.println("<td width='50%'  class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(2)+"')><u>: "+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0 >");
					out.println("<tr class=pdn_txtpos2>");

					out.println("<td width='1%' style='{background-color:white}'></td>"); 
					out.println("<td width='10%' align=left  class=div_input><b>Entry Type</b></td>");
					out.println("<td width='10%'  align=left  class=div_input><b>Receiver</b></td>");
					out.println("<td width='10%'  align=left  class=div_input><b>Payer</b></td>");
					out.println("<td width='10%'  align=right  class=div_input><b> Initial Amount </b></td>");
					out.println("<td width='10%'  align=right  class=div_input><b>Payments Already Made</b></td>");
					out.println("<td width='11%'  align=right  class=div_input><b>Balance To Be Paid</b></td>");
					out.println("<td width='5%' align=left   class=div_input><b>Curr</b></td>");
					out.println("<td width='5%'  align=left  class=div_input><b>Rate</b></td>");
					out.println("<td width='9%'  align=left class=div_input><b>Value Date</b></td>");
					out.println("<td width='1%' style='{background-color:white}'></td>");
					out.println("</tr>");
		
					while(m_sus_no.equals(rs.getString(1))){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td   align=left  class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td   align=left  class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td   align=left  class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td   align=right  class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td   align=right  class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td  align=right  class=div_input>"+nf.format(rs.getDouble(8))+"</td>");
					out.println("<td  align=left  class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td  align=left  class=div_input>"+rs.getString(10)+"</td>");
					out.println("<td  align=left  class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='1%' ></td>");
					out.println("</tr>");		
					more_sus=rs.next();
				
							
					if(!more_sus){
					break;
					}
		
					}	
					out.println("</table>");
		
					if(more_sus){
					m_sus_no=rs.getString(1);
					}
					
					
					}
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
				else if(m_chksql.equals("SHOW_BANK_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_bank_code=req.getParameter("bank_no");				
								
				rs= stmt1.executeQuery
				( "SELECT "+
														    "BANK_CODE, "+
														    "NAME, "+
														    "DECODE(ACTIVE_STATUS,'Y','Yes','N','No'), "+
														    "DECODE(DEFAULT_VALUE,'Y','Yes','N','No') "+
															 	"FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
																"WHERE UPPER(BANK_CODE)=UPPER('"+m_bank_code+"') ");

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Bank  Details - Document Code : "+m_bank_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Bank  Details - Bank Code : "+m_bank_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Bank Code "+m_bank_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Bank Code</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Name</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Default Value</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			
			
			
			
			
			else if(m_chksql.equals("SHOW_ADVERTISTMENT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_add_no=req.getParameter("add_no");				
								
				rs= stmt1.executeQuery
			(	"SELECT "+
													    "INVENTORY_NO, "+
													    "VEHICLE_NO, "+
													    "TO_CHAR(ADVER_DATE,'DD-MM-YYYY'), "+
													    "ADVER_NO, "+
													    "AMOUNT, "+
													    "VAT_AMOUNT, "+
													    "TOTAL_AMOUNT, "+
													    "nvl(NO_OF_OFFERS,0), "+
													    "ADD_VALUE_PAID_TO "+
														  "FROM "+m_schema_name+".AF_RE_PRO_ADVERTISEMENT_DETAIL "+
															"WHERE UPPER(ADVER_NO)=UPPER('"+m_add_no+"') ");

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Advertistment  Details - Advertistment No : "+m_add_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Advertistment  Details - Advertistment No : "+m_add_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Advertistment No "+m_add_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Advertistment No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invetory No</b></td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_inventory_drill('"+rs.getString(1)+"')\"><U><b>"+rs.getString(1)+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vehicle No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Advertistment Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
									out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
	
										out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Vat Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>No of Offeres</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Advertistment Money Paid To</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");


					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			else if(m_chksql.equals("SHOW_LEGAL_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_legal_no=req.getParameter("legal_no");				
								
				rs= stmt1.executeQuery("SELECT "+
													    "LEGAL_NO, "+
													    "to_char(LEGAL_DATE,'DD-MM-YYYY'), "+
													    "DAILY_DECISION, "+
													    "TO_CHAR(NEXT_COURT_DATE,'DD-MM-YYYY'), "+
													    "nvl(NEXT_COURT_REQ,'-'), "+
													    "nvl(LAWYER_CHARGE_AMOUNT,0), "+
													    "nvl(BILL_REF_NO,'-') "+
													 		"FROM "+m_schema_name+".AF_RE_PRO_LEGAL_ACTIONS "+
															"WHERE UPPER(LEGAL_NO)=UPPER('"+m_legal_no+"') ");
		



					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> Legal Action  Details - Legal Action No : "+m_legal_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Legal Action  Details - Legal Action No : "+m_legal_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Legal Action No "+m_legal_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Legal Action No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Legal Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Daily Decision</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Next Court Date</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
	
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Next Court Request</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

	
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lawyer Charge Amount</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(6))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");


					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Bill  Ref No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");


					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
			
				else if(m_chksql.equals("SHOW_BUS_SECT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_bus_sect_no=req.getParameter("bus_sect_no");				
								

					 rs= stmt1.executeQuery("SELECT "+
															    "SECTOR_CODE, "+
															    "DESCRIPTION, "+
															    "ACTIVE_STATUS "+    
																  "FROM "+m_schema_name+".AF_CO_MAS_BUSINESS_SECTOR  "+
																  "WHERE UPPER(SECTOR_CODE)=UPPER('"+m_bus_sect_no+"') ");


					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Bussiness Sector Details - Sector No : "+m_bus_sect_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Bussiness Sector Details - Sector No : "+m_bus_sect_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Sector No "+m_bus_sect_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sector No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
			
				else if(m_chksql.equals("SHOW_BUS_SUB_SECT_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_bus_sect_no=req.getParameter("bus_sub_sect_no");				
								

					 rs= stmt1.executeQuery("SELECT "+
															    "SUB_CODE, "+
															    "DESCRIPTION, "+
															    "ACTIVE_STATUS "+    
																  "FROM "+m_schema_name+".AF_CO_MAS_SUB_BUSINESS_SECTORS  "+
																  "WHERE UPPER(SUB_CODE)=UPPER('"+m_bus_sect_no+"') ");


					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Bussiness Sub Sector Details - Sub Sector No : "+m_bus_sect_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Bussiness Sub Sector Details - Sub Sector No : "+m_bus_sect_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Sub Sector No "+m_bus_sect_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Sector No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Description</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
						else if(m_chksql.equals("SHOW_MKT_OFFICER_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_inquiry_no=req.getParameter("inquiry_no");				
								

									  rs= stmt1.executeQuery
										("SELECT "+
									   " A.USER_ID, "+
									   " A.NAME, "+
									   " A.LOCATION_CODE, "+
									   " A.USER_TYPE, "+
									   " A.EMP_ID, "+
									   " A.ACTIVE_STATUS, "+
									   " A.DIVISION_CODE, "+
									   " A.DESIGNATION_CODE "+
										 " FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".AF_MK_PRO_INQUIRY B "+
										 " WHERE UPPER(B.MK_OFFICER)=UPPER(A.EMP_ID) "+
										 " AND UPPER(b.INQUIRY_CODE)=UPPER('"+m_inquiry_no+"') ");
	


					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Marketting Officer - Inquiry No : "+m_inquiry_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Marketting Officer - Inquiry No : "+m_inquiry_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Marketting officer with Inquiry No "+m_inquiry_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>User Id</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Location code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>User Type</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Employee Id</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Active Status</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Designation Code</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
	
	//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_CLIENT_NAME_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_full_name=req.getParameter("full_name");				
								

	

 rs= stmt1.executeQuery("SELECT Distinct "+
										    "CLIENT_CODE, "+
										    "FULL_NAME, "+
												"FIRST_NAME, "+
										    "CLIENT_TYPE, "+
										    "InitCap(CLIENT_CATEGORY), "+
										    "NVL(ADDRESS1,'-'), "+
										    "NVL(ADDRESS2,'-'), "+
										    "CITY_CODE, "+
										    "TEL_NO, "+
										    "FAX_NO, "+
										    "EMAIL, "+
										    "NIC_NO "+
												"FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
												"WHERE UPPER(FULL_NAME)=UPPER('"+m_full_name+"') OR "+
												"UPPER(FIRST_NAME)=UPPER('"+m_full_name+"')");
	

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Client Details - Client Name : "+m_full_name+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Client Details - Client Name : "+m_full_name+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Name "+m_full_name+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Code</b></td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Full Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client First Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Client Category</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 1</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 2</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>NIC</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			





	//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_BRANCH_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_branch_code=req.getParameter("branch_code");				
								

	

				 rs= stmt1.executeQuery("SELECT "+
														    "BRANCH_CODE, "+
														    "BRANCH_NAME, "+
														    "BANK_CODE, "+
														    "nvl(ADDRESS1,'-'), "+
														    "nvl(ADDRESS2,'-'), "+
														    "nvl(CITY_CODE,'-'), "+
														    "nvl(TEL_NO,'-'), "+
														    "nvl(FAX_NO,'-'), "+
														    "DAYS_TO_REALISE "+
														 		"FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
																"WHERE UPPER(BRANCH_CODE)=UPPER('"+m_branch_code+"') ");
	

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Branch Details - Branch Code : "+m_branch_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Branch Details - Branch Code : "+m_branch_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Branch No "+m_branch_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Code</b></td>");
					out.println("<td width='50%' class=div_input >"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Branch Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Bank Code</b></td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_bank_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 1</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 2</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City</b></td>");
					if(!rs.getString(6).equals("-")){
					out.println("<td width='50%' class=div_input  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_city_drill('"+rs.getString(6)+"')\"><U>"+rs.getString(6)+"</td>");
					}
					else{
					out.println("<td width='50%' class=div_input >"+rs.getString(6)+"</td>");
					}

					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Telephone No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Fax No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					

					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
	
	//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_CITY_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_city_code=req.getParameter("city_code");				
								

	

				 rs= stmt1.executeQuery("SELECT CITY_CODE, "+
														   	"CITY_DESC,  "+
														  	"DISTRICT_CODE "+
														 		"FROM "+m_schema_name+".AF_CO_MAS_CITY "+
																"WHERE UPPER(CITY_CODE)=UPPER('"+m_city_code+"') ");

	

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>City Details - City Code : "+m_city_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>City Details - City Code : "+m_city_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for City No "+m_city_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City Code</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City Name</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>District Code</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			

		//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_LOC_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_loc_code=req.getParameter("loc_code");				
								

	

	
			rs= stmt1.executeQuery
													("SELECT "+
											    "LOCATION_CODE, "+
											    "LOCATION_DESC, "+
											    "ADDRESS1, "+
											    "ADDRESS2, "+
											    "CITY_CODE, "+
											    "POSTAL_CODE, "+
											    "COUNTRY_CODE, "+
													""+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE), "+
													""+m_schema_name+".AF_CO_GET_POSTAL_DESC(POSTAL_CODE), "+
													""+m_schema_name+".AF_CO_GET_COUNTRY_NAME(COUNTRY_CODE) "+
											 		"FROM "+m_schema_name+".AF_CO_MAS_LOCATION "+
													"WHERE UPPER(LOCATION_CODE)=UPPER('"+m_loc_code+"') ");
	

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Location Details - Location Code : "+m_loc_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Location Details - Location Code : "+m_loc_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Location Code "+m_loc_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Location Code</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Location</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 1</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address 2</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City</b></td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_city_drill('"+rs.getString(5)+"')\"><U>:"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Postal</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
						out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Country</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(10)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			

			//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_EMP_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_emp_code=req.getParameter("emp_code");				
								

	

	
			rs= stmt1.executeQuery("SELECT "+
													   	"EMP_CODE, "+//1
													    "TITLE, "+//2
													    "FIRST_NAME, "+//3
													    "LAST_NAME, "+//4
													    "ADDRESS, "+//5
													    "LOCATION_CODE, "+//6
													    "AREA_CODE, "+//7
													    "CITY_CODE, "+//8 
													    "CONTACT_NO,"+//9
													    "DESIGNATION_CODE, "+//10
													    "DIVISION_CODE, "+//11
													    "EPF_NO, "+//12
													    "ID_NO, "+//13
															""+m_schema_name+".AF_CO_GET_LOCATION_DESC(LOCATION_CODE), "+//14
															""+m_schema_name+".AF_CO_GET_DESIGNATION_DESC(DESIGNATION_CODE), "+//15
															"InitCap("+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION_CODE)), "+//16
															""+m_schema_name+".AF_CO_GET_AREA_NAME(AREA_CODE), "+//17		
																""+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) "+//18														
													 		"FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE "+
															"WHERE UPPER(EMP_CODE)=UPPER('"+m_emp_code+"')");

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Employee Details - Employee Code : "+m_emp_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Employee Details - Employee Code : "+m_emp_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Employee Code "+m_emp_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Employee Code</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Title</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Name</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(3)+" "+rs.getString(4)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Address</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
						out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Location </b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(14)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
				
						out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Area</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>City</b></td>");
					out.println("<td width='50%' class=div_input style= \"cursor:hand;cursor-color:blue\" onclick=\"show_city_drill('"+rs.getString(8)+"')\"><U>:"+rs.getString(18)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");

					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Contact No</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(9)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Designation</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(15)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(16)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>EPF No</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(12)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ID No</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(13)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
				
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}			
	//----------------------------------------------------------------------------------------------------------------------------

	
	
	
				//----------------------------------------------------------------------------------------------------------------------------
				else if(m_chksql.equals("SHOW_DESG_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_desg_code=req.getParameter("desig_code");				
								

	
				 rs= stmt1.executeQuery
					("SELECT "+
														    "DESIGNATION_CODE, "+
														    "DESIGNATION_NAME, "+
														    "DESIGNATION_LEVEL, "+
														    "DIVISION, "+
																"InitCap("+m_schema_name+".AF_CO_GET_DIVISION_DESC(DIVISION)) "+
														 		"FROM "+m_schema_name+".CO_CO_MAS_DESIGNATION "+
																"WHERE UPPER(DESIGNATION_CODE)=UPPER('"+m_desg_code+"') ");
	
	

					boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE>Designation Details - Designation Code : "+m_desg_code+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Designation Details - Designation Code : "+m_desg_code+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Designation Code "+m_desg_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				while(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Designation Code</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Designation Name</b></td>");
					out.println("<td width='50%' class=div_input>:"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Designation Level</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(3)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division</b></td>");
					out.println("<td width='50%' class=div_input >:"+rs.getString(5)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
			
				
					out.println("</table>");
					out.println("<br>");
					more_dir = rs.next();
				}
				
				
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}		
			
				else if(m_chksql.equals("SHOW_CUR_MONTH_DETAIL_INV_INVOCED_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

											
							String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							//" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
			
			else if(m_chksql.equals("SHOW_CUR_MONTH_DETAIL_INV_SETT_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

											
							String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							//" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(3);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
				else if(m_chksql.equals("SHOW_TOTAL_DETAIL_INV_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
						  String		Sql_detail_inv=  " SELECT "+
						  " A.INVOICE_NO INVOICE_NO ,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+ 
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) "+
							
              "UNION "+
											
							" SELECT "+
							" A.INVOICE_NO INVOICE_NO ,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
				else if(m_chksql.equals("SHOW_TOTAL_SETTLE_DETAIL_INV_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
						String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+ 
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO "+
							
              "UNION "+
											
							" SELECT A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(3);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			
				else if(m_chksql.equals("SHOW_TOTAL_BALANCE_DETAIL_INV_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
									
							
				String		Sql_detail_inv=  " SELECT "+
				" XX.INVOICE_NO, XX.TOTAL, NVL(XX.SETT,0),NVL(YY.SETT,0), NVL(XX.BALANCE,0) - NVL(YY.SETT,0) "+
				" FROM "+
				" (SELECT "+
				" A.INVOICE_NO INVOICE_NO ,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
				" FROM "+
				" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   A.ACTIVE_STATUS='Y'  "+
				" GROUP BY A.INVOICE_NO)A, "+
				
				" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
				" WHERE C.ALLOCATED_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))   "+
				" AND   C.INVOICE_NO IN ( "+
				" SELECT INVOICE_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))    "+
				" AND   A.FINANCE_NO=B.FINANCE_NO "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   A.ACTIVE_STATUS='Y' ) "+
				" GROUP BY C.INVOICE_NO )B "+
				" WHERE A.INVOICE_NO=B.INVOICE_NO(+) "+
				
				" UNION "+
				
				" SELECT "+
				" A.INVOICE_NO INVOICE_NO ,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
				" FROM "+
				" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)  "+ 
				" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   A.ACTIVE_STATUS='Y'  "+
				" GROUP BY A.INVOICE_NO)A, "+
				
				" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
				" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   C.INVOICE_NO IN ( "+
				" SELECT INVOICE_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   A.FINANCE_NO=B.FINANCE_NO "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   A.ACTIVE_STATUS='Y' ) "+
				" GROUP BY C.INVOICE_NO )B "+
				" WHERE A.INVOICE_NO=B.INVOICE_NO(+) "+
				" ) XX ,"+
				
				" (SELECT "+
				" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
				" FROM "+
				" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND   A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))  "+ 
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   A.ACTIVE_STATUS='Y'  "+
				" GROUP BY A.INVOICE_NO)A, "+
				
				" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
				" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   C.INVOICE_NO IN ( "+
				" SELECT INVOICE_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.DUE_DATE<=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))    "+
				" AND   A.FINANCE_NO=B.FINANCE_NO "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   A.ACTIVE_STATUS='Y' ) "+
				" GROUP BY C.INVOICE_NO )B "+
				" WHERE A.INVOICE_NO=B.INVOICE_NO "+
				
				" UNION "+
				
				" SELECT A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
				" FROM "+
				" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE A.FINANCE_NO=B.FINANCE_NO "+
				" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   A.ACTIVE_STATUS='Y'  "+
				" GROUP BY A.INVOICE_NO)A, "+
				
				" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
				" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
				" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   C.INVOICE_NO IN ( "+
				" SELECT INVOICE_NO "+
				" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
				" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
				" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
				" AND   A.FINANCE_NO=B.FINANCE_NO "+
				" AND   B.APPLICATION_STATUS='ACTIVATED' "+
				" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
				" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				" AND   A.ACTIVE_STATUS='Y' ) "+
				" GROUP BY C.INVOICE_NO )B "+
				" WHERE A.INVOICE_NO=B.INVOICE_NO ) YY "+
				" WHERE XX.INVOICE_NO=YY.INVOICE_NO(+) ";
							




		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(5);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
				
			
				else if(m_chksql.equals("SHOW_CUR_MONTH_DETAIL_INV_INVOCED_DRILL")){
				int count = 0;
				String m_string="";								
				String m_user_id=req.getParameter("user_id");
				String m_date=req.getParameter("m_date");
			  String m_finance_no=req.getParameter("finance_no");
			
				
					out.println("<HTML><HEAD><TITLE> Invoice Details - Collection Officer : "+m_user_id+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Invoice Details - Collection Officer : "+m_user_id+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
							

											
							String		Sql_detail_inv=  " SELECT "+
							" A.INVOICE_NO,NVL(A.TOTAL_AMOUNT,0) TOTAL,NVL(B.SETTELED_AMOUNT,0) SETT, ( NVL(A.TOTAL_AMOUNT,0)- NVL(B.SETTELED_AMOUNT,0) ) BALANCE "+
							" FROM "+
							" (SELECT A.INVOICE_NO,NVL(SUM(TOTAL_AMOUNT),0) TOTAL_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE A.FINANCE_NO=B.FINANCE_NO "+
							" AND   A.DUE_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   A.DUE_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   A.ACTIVE_STATUS='Y'  "+
							" GROUP BY A.INVOICE_NO)A, "+
							
							" (SELECT C.INVOICE_NO,NVL(SUM(SETTELED_AMOUNT),0) SETTELED_AMOUNT "+
							" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C  "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   C.INVOICE_NO IN ( "+
							" SELECT INVOICE_NO "+
							" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE C.ALLOCATED_DATE>=(LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1)   "+
 							" AND   C.ALLOCATED_DATE<=TO_DATE('"+m_date+"','DD-MM-YYYY') "+
							" AND   A.FINANCE_NO=B.FINANCE_NO "+
							" AND   B.APPLICATION_STATUS='ACTIVATED' "+
							" AND   UPPER(B.COLLECTION_OFFICER)=UPPER('"+m_user_id+"') "+
							" AND   UPPER(B.FINANCE_NO)=UPPER('"+m_finance_no+"') "+
							" AND   A.ACTIVE_STATUS='Y' ) "+
							" GROUP BY C.INVOICE_NO )B "+
							" WHERE A.INVOICE_NO=B.INVOICE_NO(+) ";

		    rs=stmt1.executeQuery(Sql_detail_inv);
				boolean  more =rs.next();
			
        if (!more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Collection Officer "+m_user_id+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if (more) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Settled Amount</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Balance Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
				//	out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double tot=0;
				double bal=0;
				while(more){
					count++;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' class=div_input style= cursor:hand; onclick=show_invoice_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("</tr>");
					bal=bal+rs.getDouble(4);
					more = rs.next();
				}
				
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input>&nbsp;</td>");
					out.println("<td width='25%' align='right' class=div_input><b>Total</td>");
					out.println("<td width='25%' align='right' class=div_input><b>"+nf.format(bal)+"</td>");
					
					out.println("</tr>");
				 
					out.println("</table>");
				  out.println("</table>");
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			else if(m_chksql.equals("SHOW_FOLLOWUP_DRILL")){
				
				int count = 0;
				String m_string="";								
				String m_followup_no=req.getParameter("followup_no");					
									
						rs= stmt1.executeQuery(" SELECT "+
						   " A.FOLLOW_UP_NO, "+//1
						   " NVL(A.ID_NO,'-'), "+//2
						   " NVL(A.ACTION_TOBE_TAKEN,'-'), "+//3
						   " NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=A.ACTION_TOBE_TAKEN),'-'),"+//4
						   " NVL(TO_CHAR(A.EFF_VAL_DATE,'DD-MM-YYYY'),'-'), "+//5
						   " NVL(A.ACTION_TAKEN,'-'), "+//6
						   " NVL((SELECT CATEGORY_NAME FROM "+m_schema_name+".AF_CO_MAS_FOLLOWUP_CATEGORY WHERE CATEGORY_CODE=A.ACTION_TAKEN),'-'),"+//7
						   " NVL(TO_CHAR(A.ACTION_DATE,'DD-MM-YYYY'),'-'), "+//8
						   " NVL(A.ACTION_SET_FOR,'-'), "+//9
						   " NVL(A.SCREEN_NAME,'-'), "+//10
						   " NVL(A.DIVISION_CODE,'-'),'', "+//11
							 //" NVL(C.DESCRIPTION,'-'), "+//12	
						   " NVL(A.ENT_REMARKS,'-'), "+//13
						   " NVL(A.REMARKS,'-'), "+//14
						   " NVL(TO_CHAR(A.ACTION_ENT_DATE,'DD-MM-YYYY'),'-'), "+//15
						   " DECODE(A.STATUS,'INPROGRESS','In Progress','COMPLETED','Completed','PENDING','Pending'), "+//16
						   " NVL(A.PRIORITY,0), "+//17
						   " NVL(A.PREV_FOLLOWUP_NO,'-'), "+//18
						   " NVL(A.ORG_FOLLOWUP_NO,'-'), "+//19
						   " NVL(A.FOLLOWUP_TIME,'-'), "+//20
						   " NVL(A.SUB_DIVISION_CODE,'-'),'', "+//21
							// " NVL(D.DESCRIPTION,'-'), "+	//22
						   " NVL(A.PRODUCT_CODE,'-') "+//23
							 //" NVL(E.DESCRIPTION,'-') "+//24	
						 "FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A "+
						 //","+m_schema_name+".CO_CO_MAS_DIVISION C,"+m_schema_name+".CO_CO_MAS_SUB_DIVISION D, "+
						// ""+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE E "+	
						 "WHERE "+//AND B.CATEGORY_CODE=A.ACTION_TAKEN(+)
						// "A.DIVISION_CODE=C.DIVISION_CODE "+	
						// "AND A.SUB_DIVISION_CODE=D.SUB_DIVISION_CODE"+	
						// "AND A.PRODUCT_CODE=E.TRAN_CODE "+	
						 " A.FOLLOW_UP_NO='"+m_followup_no+"' ");


				boolean more_dir = rs.next();
				
					out.println("<HTML><HEAD><TITLE> FollowUp  Details - FollowUp No : "+m_followup_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> FollowUp  Details - FollowUp No : "+m_followup_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for FollowUp No "+m_followup_no+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b> FollowUp No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>ID No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Division Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					//out.println("<td width='20%' class=div_input><b>Division Name</b></td>");
					//out.println("<td width='*%' class=div_input>"+rs.getString(12)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Division Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(21)+"</td>");
					//out.println("<td width='20%' class=div_input><b>Sub Division Name</b></td>");
					//out.println("<td width='*%' class=div_input>"+rs.getString(22)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Product Code</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(23)+"</td>");
					//out.println("<td width='20%' class=div_input><b>Product Name</b></td>");
					//out.println("<td width='*%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Action To Be Taken</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(4)+"</td>");
					out.println("<td width='20%' class=div_input><b>Action Taken</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Action Assign To</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='20%' class=div_input><b>Action Target Date</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Actual Action Date</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Entered User Remarks</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("<td width='20%' class=div_input><b>Remarks</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(14)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(16)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Screen Name</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(10)+" </td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Priority</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Previous FollowUp No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(18)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>FollowUp Time</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(20)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");
			}
			
			  //=========================== transaction history by contract wise ===========================================//
				
			  else if(m_chksql.equals("SHOW_TRANSACTION_HISTORY_BY_CONTRACT")){
				String m_finance_no=req.getParameter("finance_no").trim();		
				String m_client_code=req.getParameter("client_code").trim();		

				
				int count = 0;
				String m_string="";		
				String m_orient_name="";
				String m_name="";
				String m_cheque_no="";
				double m_cum_value=0;
				double m_val=0;
				double m_debit=0;
				double m_credit=0;
				
					out.println("<HTML><HEAD><TITLE> Transaction History - Finance No : "+m_finance_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					
					out.println("<script>");
					out.println("	function show_invoice_info(m_invoice_no){");
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_drill_downs_two?chksql=SHOW_INVOICE_DRILL&url="+url+"&invoice_no='+m_invoice_no+'';"); 
					out.println("window.open(m_url,'displayWindow2','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');"); 
					out.println("	}");
					out.println("</script>");

					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Transaction History - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

					
					String		Sql_invoice=
					" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					"  NULL STATUS ,"+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"        ACTIVE_STATUS='Y'  "+
					"   AND VALUE_DATE <=SYSDATE "+  

					
					"  UNION "+
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') DESCRIPTION,  "+
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO AND "+
					" A.INVOICE_NO=C.INVOICE_NO AND "+
					" C.FINANCE_NO='"+m_finance_no+"' "+
					"   AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					//added by sh for odi allocations
					
					"  UNION "+
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(B.STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') DESCRIPTION,  "+
					" B.STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+
					"       "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B,"+
					"       "+m_schema_name+".AF_CO_PRO_INVOICE C, "+
					"       "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY D "+
					
					" WHERE RECEIPT_NO=REC_NO AND "+
					" D.INVOICE_NO=C.INVOICE_NO AND A.INVOICE_NO =D.ODI_REF_NO AND "+
					" C.FINANCE_NO='"+m_finance_no+"' "+
					"   AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,B.STATUS "+
					
					//end of odi allo
					"	UNION "+
					" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE ODI_SETTLED_AMOUNT > 0 "+
					" AND INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"          ACTIVE_STATUS='Y' "+
					") "+
					"   AND DUE_DATE <=SYSDATE "+  

					
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"    ACTIVE_STATUS='Y' "+
					" ) "+
					"   AND ADJUSTED_DATE <=SYSDATE "+  

					
					" UNION "+
					
					" SELECT a.rec_no REF_NO , "+
					" b.eff_valdate   DUE_DATE ,"+
					" a.bal_tobe_receive ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','CAD','Chq Cancel','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" STATUS ,"+
					" '' NO "+ //7
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  

				
				 /* 
				  "   UNION      "+
					"		SELECT    "+
					"		RETURN_NO REF_NO,   "+
					"		EFF_VALDATE DUE_DATE,  "+
					"		NVL(RETURN_CHARGE,0)  AMOUNT,   "+
					"		'RET_CHARGE' TYP,  "+
					"		'Cheque - '||CHEQUE_NO  CHEQUE_NO ,  "+
					"		'Return Cheque Charges'  DESCRIPTION,    "+
					"		STATUS,"+
					" '' NO    "+
					"		FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B, "+
					"        "+m_schema_name+".af_co_pro_settl_rec_app_bal C "+ 
					"		WHERE A.REC_NO=B.RECEIPT_NO "+
					"		AND C.REC_NO=B.RECEIPT_NO "+
					"		AND C.REC_NO=A.REC_NO "+
					"		AND C.finance_no='"+m_finance_no+"'"+
					"		AND STATUS='RET' "+
					*/
				
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";
					/*" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					"  NULL STATUS ,"+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"        ACTIVE_STATUS='Y'  "+
					
					"  UNION "+
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc') DESCRIPTION,  "+
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO AND "+
					" A.INVOICE_NO=C.INVOICE_NO AND "+
					" C.FINANCE_NO='"+m_finance_no+"' "+
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					
					"	UNION "+
					" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
					" WHERE ODI_SETTLED_AMOUNT > 0 "+
					" AND INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"          ACTIVE_STATUS='Y' "+
					") "+
					
					
					" UNION "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					"    ACTIVE_STATUS='Y' "+
					" ) "+
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";*/

				  rs=stmt.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();
					/*if(more_inv){
						out.println("<br>"); 
						out.println("<hr color='black'>"); 
						out.println("<br>"); 
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr >");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='*%' ><b><u>Transaction History Details</b></u></td>");
						out.println("</tr>"); 
						out.println("</table >");
					}*/
					
					
					while(more_inv){
					count++;
					if(count==1){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
					out.println("</tr>");
					
					// ------ Modified by Dineth on 29-07-2008
					String sql_col_status  = " SELECT "+ m_schema_name + ".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),"+
																	 " NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-') "+//2
																	 " FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
																	 " WHERE FINANCE_NO = '"+m_finance_no +"'";
					rs2 = stmt2.executeQuery(sql_col_status);
					boolean more2 = rs2.next();
					if(more2){
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Collection Officer</td><td>: "+rs2.getString(1)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Application Status</td><td>: "+rs2.getString(2)+"</td>");
					out.println("</tr>");
					}
					// ------ End by Dineth on 29-07-2008
					
					
					
					out.println("<tr>");
					out.println("<td width='100%'align='center' colspan='2' class=div_input><b>Asset Finance Ledger</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<hr color='black'>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='20%' class=div_input>Lessee Name</td>");
					out.println("<td width='50%' class=div_input>"+m_name+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					*/
					
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
										

					}
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
					m_debit=rs.getDouble(3);
					
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					if(rs.getString(7).equals("RET")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					
					else if(rs.getString(4).equals("DR/CR")){
					
					if(rs.getString(7).equals("DR")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					
					}
					
					else if(rs.getString(4).equals("ODI")){
					
					m_debit=rs.getDouble(3);
			
					}
					else if(rs.getString(4).equals("OTHER")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					 else if(rs.getString(4).equals("BAL")){
						
					 /* if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					    m_credit=rs.getDouble(3);
					  }else{
						  m_debit=rs.getDouble(3);
						}
						*/
					  m_credit=rs.getDouble(3);
					}
					
					m_val=m_debit-m_credit;
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 
					m_cum_value=m_cum_value-m_credit;
					}
					else{
					m_cum_value=m_cum_value+m_val;
					}
          
					
          //m_cum_value=m_cum_value+m_val;
					
					
					if(rs.getString(4).equals("INVOICE")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
          //---------------------------------------------------------------------------------------------------
					if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
					m_debit =0;
					m_credit=0;
					m_debit=rs.getDouble(3);
          				
					m_val=m_debit-m_credit;
          m_cum_value=m_cum_value+m_val;

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					//---------------------------------------------------------------------------------------------------
					
					
					}
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("OTHER")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					
					/////?????????????????????????--
					else if(rs.getString(4).equals("BAL")){
					if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					//m_val=m_credit;
          // m_cum_value=m_cum_value-m_val;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>Receipt</td>");
					
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					//m_val=m_debit;
					m_val=m_credit;  // added by nuwan de silva on 10-07-2008
					if (m_cum_value<0)
					{
					m_cum_value=m_cum_value+m_val;
					}
					else{
					m_cum_value=m_cum_value-m_val;
					}
     //     m_cum_value=m_cum_value-m_val;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
          }
					
					//comment by nuwan de silva on 27-06-2008---------------------------
					/*out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					*/
					else{
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					}
					
					/////??????????????????????
					else if(rs.getString(4).equals("DR/CR")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
				  if(rs.getString(7).equals("DR") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					more_inv = rs.next();
				}
				out.println("</table>");
    				
				
				
				/*while(more_inv){
					count++;
					if(count==1){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Cheque No.</td>");
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
					}
					m_debit =0;
					m_credit=0;
					if(rs.getString(4).equals("INVOICE")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RECEIPT")){
					if(rs.getString(7).equals("RET")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					else if(rs.getString(4).equals("DR/CR")){
					if(rs.getString(7).equals("DR")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					else if(rs.getString(4).equals("ODI")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("OTHER")){
					m_debit=rs.getDouble(3);
					}
					m_val=m_debit-m_credit;
          m_cum_value=m_cum_value+m_val;
					if(rs.getString(4).equals("INVOICE")){
					out.println("<tr>");
					out.println("<td width='1%'>"+rs.getString(8)+"</td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("RECEIPT")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("ODI")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("OTHER")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					else if(rs.getString(4).equals("DR/CR")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>"); //style= cursor:hand; show_invoice_drill('"+rs.getString(1)+"')
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					if(rs.getString(7).equals("DR") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					more_inv = rs.next();
				}
				out.println("</table>");
				
				*/
				
				
				
							/*						String		Sql_Unallocated=" SELECT "+ 
								  "  REC_NO, "+
								  "  NVL(PAYER_ACC_NO,'-'), "+
								  "  NVL(PAYER_BRANCH_CODE,'-'), "+
								  "  REC_AMOUNT, "+
								  "  NVL(ACC_NO,'-'), "+
								  "  NVL(BRANCH_CODE,'-') ,"+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT "+
								  "  WHERE  REC_NO NOT IN  "+
								  "  ( "+
								  "  SELECT "+
								  "  RECEIPT_NO "+
								  "  FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS "+
								  "  WHERE ALLOCATION_NO IS NOT NULL "+
								  "  ) AND STATUS <> 'C' "+
									"   AND UPPER(CLIENT_CODE)=UPPER('"+m_client_code+"')  ";*/
									
									
									String		Sql_Unallocated=" SELECT "+ 
								  "  A.REC_NO, "+
								  "  A.REC_AMOUNT, "+
									"  B.allocated_amount, "+
									"  B.bal_tobe_receive, "+
									"  TO_CHAR(EFF_VALDATE,'DD-MM-YYYY') "+ //added by nuwan de silva on 18-09-07
								  "  FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B "+
								  "  WHERE  A.REC_NO=B.REC_NO /*REC_NO NOT IN */ "+
								  "  AND UPPER(A.CLIENT_CODE)=UPPER('"+m_client_code+"') AND  B.BAL_TOBE_RECEIVE > 0  AND A.STATUS NOT IN ('C','CAD','RET') ORDER BY  EFF_VALDATE ";
									
		
			  rs=stmt.executeQuery(Sql_Unallocated);
				boolean  more =rs.next();
				
				if (more) {
				  out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><u><b>Un Allocated Receipts Details</b></u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				 
					out.println("<br>");
				
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input ><b>Receipt No</b></td>");
					out.println("<td width='10%' class=div_input ><b>Effective Value Date</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Receipt Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Allocated Amount</b></td>");
					out.println("<td width='20%' align='right' class=div_input ><b>Balance To Be Allocated</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				double sum_amount=0;
				while(more){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='10%' class=div_input >"+rs.getString(5)+"</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp;</td>");

					out.println("</tr>");
					sum_amount=sum_amount+rs.getDouble(4);
					more = rs.next();
				}
				  
					if(sum_amount >0){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input >&nbsp;</td>");
					out.println("<td width='10%' class=div_input >&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>Total&nbsp;&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'><b>"+nf.format(sum_amount)+"&nbsp;&nbsp;</td>");
					out.println("</tr>");
          }
					out.println("</table>");
					
					
//================Added by Sandun on 30-07-2008======================================================================
   
		
		
     String		Sql_Pod_Cheque_Hand= " SELECT "+ 
												           "  NVL(POD_REF_NO,'-'), "+//1
												           "  NVL(FINANCE_NO,'-'), "+//2
												           "  NVL(CHEQUE_NO,'-'), "+//3
												           "  NVL(TO_CHAR(CHEQUE_DATE,'DD-MM-YYYY'),'-'), "+//4
												           "  NVL(PAYER_ACC_NO,'-'), "+//5
												           "  NVL(PAYER_BRANCH_CODE,'-'), "+//6
												           "  NVL(CHEQUE_AMOUNT,0) "+//7
												           "  FROM "+m_schema_name+".AF_RE_PRO_POD_CHEQUES "+
												           "  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') AND STATUS IN ('INV' ,'APP')  " ;

					     

			  rs=stmt1.executeQuery(Sql_Pod_Cheque_Hand);
				
				
				boolean  more1=rs.next();
				if(more1){
				
				
				 out.println("<table align='center' width='100%' class='table' >");
				 out.println("<tr>");
				 out.println("<td width='100%' class=div_input><u><b>Post Dated Cheque Details - Finance No: "+m_finance_no+"</b></u></td>");					
				 out.println("<td width='*%'></td>");
				 out.println("</tr>");
				 out.println("</table>");
			/*
        if (!more1) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Client Code "+m_client_code+"</b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				*/
					  
          out.println("<br>");
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input><b>POD Ref No</b></td>");
					out.println("<td width='15%' class=div_input><b>Finance No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque No</b></td>");
					out.println("<td width='12%' class=div_input><b>Cheque Date</b></td>");
					out.println("<td width='15%' class=div_input><b>Account No</b></td>");
					out.println("<td width='15%' class=div_input><b>Branch code</b></td>");
					out.println("<td width='15%' class=div_input align='right' ><b>Amount</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
				}
				out.println("<table align='center' width='100%' class='table' >");
				while(more1){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_POD_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_finance_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(3)+"</td>");
					out.println("<td width='12%' class=div_input align='left'>"+rs.getString(4)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(5)+"</td>");
					out.println("<td width='15%' class=div_input align='left'>"+rs.getString(6)+"</td>");
					out.println("<td width='15%' class=div_input align='right'>"+nf.format(rs.getDouble(7))+"</td>");
					out.println("</tr>");
					
					more1 = rs.next();
				}
				
	
				  out.println("</table>");
					


//==============================End on 30-07-2008========================================================
				
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					out.println("</BODY></HTML>");

			
			}
			 //=========================== end transaction history by contract wise ===========================================//
	//----------------------------------------------------------------------------------------------------------------------------
  else if(m_chksql.equals("SHOW_PRICING_DRILL")) {
				
				int count = 0;
				String m_string="";								
				String m_pricing_no=req.getParameter("pricing_no");					
				
				String sql_main=" SELECT "+
								  "  A.PRICING_NO,"+//1
								  "  NVL(A.INQUIRY_NO,'-'),"+//2
									"  NVL(A.APPLICATION_NO,'-'),"+//3
								  "  NVL(A.PRO_INVOICE_NO,'-'),"+//4
								  "  NVL(A.TRANSACION_TYPE,'-'),"+//5
								  "  NVL(B.DESCRIPTION,'-'),"+//6
								  "  NVL(A.TRN_SUB_TYPE,'-'),"+//7
								  "  NVL(C.DESCRIPTION,'-'),"+//8
								  "  NVL(A.INTEREST_TYPE,'-'),"+//9 
								  "  NVL(A.CONDITION_OF_ASSET,'-'),"+//10
								  "  NVL(D.DESCRIPTION,'-'),"+//11
									"  NVL(A.ASSET_USAGE_TYPE,'-'),"+//12
								  "  NVL(J.DESCRIPTION,'-'),"+//13
								  "  NVL(A.ITEM_CATEGORY,'-'),"+//14
								  "  NVL(E.DESCRIPTION,'-'),"+//15
								  "  NVL(A.ITEM_SUB_CAT_CODE,'-'),"+//16
								  "  NVL(F.DESCRIPTION,'-'),"+//17
								  "  NVL(A.MAKE_CODE,'-'),"+//18
								  "  NVL(G.MAKE_DESC,'-'),"+//19
								  "  NVL(A.MODEL_CODE,'-'),"+//20
								  "  NVL(H.DESCRIPTION,'-'),"+//21
								  "  NVL(A.SUB_MODEL_CODE,'-'),"+//22
								  "  NVL(I.DESCRIPTION,'-'),"+//23
								  "  NVL(A.ENGINE_CAPACITY,'-'),"+//24
								  "  NVL(A.FUEL_TYPE,'-'),"+//25
								  "  NVL(A.TARE,'-'),"+//26
								  "  NVL(DECODE(A.MAINTENANCE_STATUS,'Y','Yes','No'),'-'),"+//27 
								  "  NVL(A.PAYMENT_MODE,'-'),"+//28
								  "  NVL(A.PAYMENT_INTERVAL,0),"+//29
								  "  NVL(A.RATE,0),"+//30
									"  NVL(A.VAT_PERCENTAGE,0),"+//31
								  "  NVL(A.GROSS_AMOUNT,0),"+//32
								  "  NVL(A.VAT_AMOUNT,0),"+//33
								  "  NVL(A.NET_AMOUNT,0),"+//34
								  "  NVL(A.NIBSM,0),"+//35
								  "  NVL(A.AMI,0),"+//36
								  "  NVL(A.LAST_RENTAL,0),"+//37
								  "  NVL(A.RESIDUAL_VALUE,0),"+//38
								  "  NVL(A.SUPPLIER_CREDIT,0),"+//39
									"  NVL(A.VAT_APP,0),"+//40
									"  NVL(A.INT_MARGIN,0),"+//41
									"  NVL(A.BUY_BACK,'-'),"+//42
								  "  NVL(A.PERIOD,0),"+//43 
									"  NVL(A.OUTFLOW_PATTERN,'-'),"+//44
								  "  NVL(A.INFLOW_PATTERN,0),"+//45
								  "  NVL(DECODE(A.PRICING_STATUS,'Y','Yes','No'),'-'),"+//46
								  "  NVL(A.CURRENCY_CODE,'-'),"+//47
								  "  NVL(A.SUPPLIER,'-'),"+//48
									"  NVL(A.SUPPLIER_LOCATION,'-'),"+//49
								  "  NVL(A.VARIABLE_INT_BASE,'-'), "+//50
									//"  NVL((SELECT   LEAD_SOURCE_NAME FROM "+m_schema_name+".AF_MK_PRO_PRICING WHERE PRICING_NO=A.PRICING_NO),'-') "+//51 // aAdded by Mahela on 15-11-2007
									"  NVL((SELECT   LEAD_SOURCE_NAME FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO=A.APPLICATION_NO),'-'), "+//51 // aAdded by nuwan de silva on 27-11-2007
									"  NVL((SELECT   LEAD_SOURCE_CATEGORY FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE APPLICATION_NO=A.APPLICATION_NO),'-') "+//52 // aAdded by nuwan de silva on 27-11-2007
								  "  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING A,"+m_schema_name+".AF_CO_MAS_TRANSACTION_TYPE B,"+m_schema_name+".AF_CO_MAS_TRANSACTION_SUB_TYPE C,"+
								  "  "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET D,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY E,"+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY F,"+
								  "  "+m_schema_name+".AF_CO_MAS_MAKE G,"+m_schema_name+".AF_CO_MAS_MODEL H,"+m_schema_name+".AF_CO_MAS_SUB_MODLE I,"+m_schema_name+".AF_CO_MAS_ASSET_USAGE_TYPE J "+
								  "  WHERE  A.TRANSACION_TYPE=B.TRAN_CODE(+) "+
								  "  AND A.TRN_SUB_TYPE=C.TRN_SUB_TYPE(+) "+
								  "  AND A.CONDITION_OF_ASSET=D.CODE(+) "+
								  "  AND A.ITEM_CATEGORY=E.ITEM_CAT_CODE(+) "+
								  "  AND A.ITEM_SUB_CAT_CODE=F.ITEM_SUB_CAT(+) "+
								  "  AND A.MAKE_CODE=G.MAKE_CODE(+) "+
								  "  AND A.MODEL_CODE=H.MODEL_CODE(+) "+
								  "  AND A.SUB_MODEL_CODE=I.SUB_CODE(+) "+
								  "  AND A.ASSET_USAGE_TYPE=J.USAGE_TYPE(+) "+
								  "  AND A.PRICING_NO='"+m_pricing_no+"' "; 
									
									
		/*		String		Sql_Pricing=  " SELECT "+
																"    DISTINCT INSTALLMENT_NO, "+
																"    NET_RENTAL_AMOUNT NET_AMOUNT, "+
																"    (GRENTAL_AMOUNT -NET_RENTAL_AMOUNT) VAT_AMOUNT, "+
																"    GRENTAL_AMOUNT GROSS_AMOUNT, "+
																"    TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') "+
																"    FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																"    WHERE PRICING_NO='"+m_pricing_no+"' "+
																"    ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
																
		*/													
																		String Sql_Sub_Charges="SELECT "+
															  "  PRICING_NO, "+ //1
															  "  SUB_CHAGE_CODE, "+ //2
															  "  DESCRIPTION, "+ //3
															  "  SUM(AMOUNT), "+ //4
																"  CHARGE_TYPE "+ //5
															  "  FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING_CHARGES A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
															  "  WHERE PRICING_NO='"+m_pricing_no+"' AND "+
															  "  A.SUB_CHAGE_CODE=B.SUB_TYPE_CODE AND "+
															  "  NVL(AMOUNT,0) <> 0 "+
																"  GROUP BY PRICING_NO,SUB_CHAGE_CODE,DESCRIPTION,CHARGE_TYPE "+
															 	"  ORDER BY  CHARGE_TYPE ";

																String		Sql_Pricing=  " SELECT "+
																"    DISTINCT INSTALLMENT_NO, "+
																"    SUM(NET_RENTAL_AMOUNT) NET_AMOUNT, "+
																"    (SUM(GRENTAL_AMOUNT) -SUM(NET_RENTAL_AMOUNT)) VAT_AMOUNT, "+
																"    SUM(GRENTAL_AMOUNT) GROSS_AMOUNT "+
																//"    TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') "+
																"    FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																"    WHERE PRICING_NO='"+m_pricing_no+"' "+
																"    GROUP BY INSTALLMENT_NO  "+
																"    ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
																
																
		
																
					rs= stmt1.executeQuery(sql_main);
					boolean more_dir = rs.next();
				  double tot_gross=0;
					double tot_vat=0;
					double tot_net=0;
					
					out.println("<HTML><HEAD><TITLE> Pricing Details - Pricing No : "+m_pricing_no+" </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Pricing Details - Pricing No : "+m_pricing_no+" </B></TD></TR>");
					out.println("</TABLE>");
					
					out.println("<BR><BR>");
					
        if (!more_dir) {
				  out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='80%' class=div_input><b>No records found for Pricing No  "+m_pricing_no+"  </b></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");
				}
				
				if(more_dir){
					count++;
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inquiry No</b></td>");
					out.println("<td width='50%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Application No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Invoice No</b></td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					tot_gross=rs.getDouble(32);
					tot_vat=rs.getDouble(33);
					tot_net=rs.getDouble(34);
					more_dir=rs.next();
					
					while(more_dir){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input>&nbsp;</td>");
					out.println("<td width='50%' class=div_input style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(4)+"') ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					tot_gross+=rs.getDouble(32);
					tot_vat+=rs.getDouble(33);
					tot_net+=rs.getDouble(34);
					more_dir=rs.next();
					}
					
					out.println("</table>");
					}
					rs= stmt1.executeQuery(sql_main);
				  more_dir = rs.next();
					if(more_dir){
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Transaction Type</b></td>");
					out.println("<td width='25%' class=div_input><b>"+rs.getString(6)+"</b></td>");
					out.println("<td width='20%' class=div_input><b>Transaction Sub Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(8)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Interest Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(9)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Asset Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Condition Of Asset</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(11)+"</td>");
					out.println("<td width='20%' class=div_input><b>Asset Usage Type</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(13)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Item Category</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(15)+"</td>");
					out.println("<td width='20%' class=div_input><b>Item Sub Category</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(17)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Make</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(19)+"</td>");
					out.println("<td width='20%' class=div_input><b>Model</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(21)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Sub Model</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(23)+"</td>");
					out.println("<td width='20%' class=div_input><b>Engine Capacity</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(24)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Fuel Type</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(25)+"</td>");
					out.println("<td width='20%' class=div_input><b>Tare</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(26)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Maintenance Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(27)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Payment Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Payment Mode</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(28)+"</td>");
					out.println("<td width='20%' class=div_input><b>Payment Interval</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(29))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Rate</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(30))+"</td>");
					out.println("<td width='20%' class=div_input><b>VAT Percentage</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(31))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Gross Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(tot_gross)+"</td>");
					out.println("<td width='20%' class=div_input><b>VAT Amount</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(tot_vat)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>NET Amount</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(tot_net)+"</td>");
					out.println("<td width='20%' class=div_input><b>NIBSM</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(35))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>AMI</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(36))+"</td>");
					out.println("<td width='20%' class=div_input><b>Last Rental </b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(37))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Residual Value</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(38))+"</td>");
					out.println("<td width='20%' class=div_input><b>Supplier Credit</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(39))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>VAT Applicable</b></td>");
					out.println("<td width='25%' class=div_input>"+nf.format(rs.getDouble(40))+"</td>");
					out.println("<td width='20%' class=div_input><b>Interest Margin</b></td>");
					out.println("<td width='*%' class=div_input>"+nf.format(rs.getDouble(41))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Buy Back</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(42)+"</td>");
					out.println("<td width='20%' class=div_input><b>Period</b></td>");
					out.println("<td width='*%' class=div_input>"+rs.getString(43)+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Outflow Pattern</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(44)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Inflow Pattern</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(45)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Pricing Status</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(46)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Currency</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(47)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Supplier</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(48)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Supplier Location</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(49)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Variable Interest Base</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(50)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lead Source Name</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(51)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("<tr>"); //added by nuwan de silva on 27-11-2007
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>Lead Source Category</b></td>");
					out.println("<td width='25%' class=div_input>"+rs.getString(52)+"</td>");
					out.println("<td width='20%' class=div_input></td>");
					out.println("<td width='*%' class=div_input></td>");
					out.println("</tr>");
					out.println("</table>");
					more_dir = rs.next();
				}
				 out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
					
					
								//==========ADDED BY NUWAN DE SILVA =================================================
		      
					rs= stmt1.executeQuery(Sql_Sub_Charges);
					
					boolean more=rs.next();
					
					if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Sub Charges Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
					
				  out.println("<br>");
														
					
					}
					int count_amo=0;
			    int count_inv=0;
					String m_charge_type="";
          
					out.println("<table align='center' width='100%' class='table' >");
					
					while(more){
					m_charge_type=rs.getString(5);
					
					if(m_charge_type.equals("AMO")){
					
					if(count_amo==0){
				  out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='30%' class=div_input><u><b>Amortise</b></u></td>");
					out.println("<td width='50%' class=div_input>&nbsp;</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");
					}
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='30%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");
					
					count_amo=1;
					
					}
					
					else if(m_charge_type.equals("INV")){
					
					if(count_inv==0){
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='30%' class=div_input><u><b>Up Front</b></u></td>");
					out.println("<td width='50%' class=div_input>&nbsp;</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");
					}
					
					out.println("<tr>");
					out.println("<td width='1%'>&nbsp;</td>"); 
					out.println("<td width='30%' class=div_input><b>"+rs.getString(3)+"</b></td>");
					out.println("<td width='50%' class=div_input>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='*%'>&nbsp;</td>");
					out.println("</tr>");
					count_inv=1;
					
					}
										
					
					more=rs.next();
										
					}
					
					out.println("</table>");	
					

				
				  out.println("<hr color='black' >");
					
					rs= stmt1.executeQuery(Sql_Pricing);
					
					boolean more_pricing = rs.next();
					
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='*%' class=div_input><b><u>Rental Details</u></b></td>");
					out.println("</tr>");
					out.println("</table>");
										
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input><b>Installment No</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Net Amount&nbsp;&nbsp</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>VAT Amount&nbsp;&nbsp</b></td>");
					out.println("<td width='25%' align='right' class=div_input><b>Gross Amount&nbsp;&nbsp</b></td>");
					//out.println("<td width='20%' class=div_input><b>Rental Due Date</b></td>");
					out.println("</tr>");
				//	out.println("</table>");
				//	out.println("<br>");
					
					
			//		out.println("<table align='center' width='100%' class='table' >");
			    double sum_net=0;
					double sum_vat=0;
					double sum_gross=0;
			
				while(more_pricing){
					count++;
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(2))+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(3))+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(rs.getDouble(4))+"&nbsp;&nbsp</td>");
					//out.println("<td width='20%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("</tr>");
					
					sum_net=sum_net+rs.getDouble(2);
					sum_vat=sum_vat+rs.getDouble(3);
					sum_gross=sum_gross+rs.getDouble(4);
					
					more_pricing = rs.next();
				}
				  out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='24%' class=div_input><b>Total<b></td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_net)+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_vat)+"&nbsp;&nbsp</td>");
					out.println("<td width='25%' align='right' class=div_input>"+nf.format(sum_gross)+"&nbsp;&nbsp</td>");
					//out.println("<td width='20%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("</tr>");
	
				
		
				  out.println("</table>");
							
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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


