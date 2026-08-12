import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:09-01-2007

public class LAKDL_AF_MAS_rpt_blacklists extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
		
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
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
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOAD_CLIENT_BLACKLIST_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				//String m_acc_type_code=req.getParameter("acc_type_code");
				
				
			//-------------------------- CLIENT BLACKLIST REPORT -------------------------------
			  
									rs1= stmt1.executeQuery
									//out.println
									(" SELECT "+
								    " CLIENT_CODE, "+//1
					   				" NVL(FULL_NAME,'-'), "+//2
					   				" NVL(NIC_NO,'-'), "+//3
					   				" NVL((TO_CHAR(DATE_OF_BIRTH,'DD-MM-YYYY')),'-'), "+//4
					   				" DECODE(CLIENT_TYPE,'I','Individual','C','Corporate'), "+//5
					   				" INITCAP(CLIENT_CATEGORY), "+//6
					   				" NVL(ADDRESS1,'-'), "+//7
					   				" NVL(ADDRESS2,'-'), "+//8
					   				" NVL(CITY_CODE,'-') CITY_CODE, "+//9
					   				" NVL(BUSINESS_CERTIFICATE_NO,'-'), "+//10
					   				" NVL(KEY_DECISION_MAKER,'-'),nvl("+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE),'-'), " +//11
									" MOD_DATE, "+ // 12 added by udara 22-07-2022
									" TO_CHAR(MOD_DATE,'DD-MM-YYYY') MOD_DATE_C "+//13 // added by udara 22-07-2022
					    		" FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
					 				" WHERE ACTIVE_STATUS=UPPER('B') "+
										" ORDER BY MOD_DATE "); // added by udara 22-07-2022
 
   
			       boolean m_dataflag=false;							
		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs1.next();
						 double m_cr_tot=0,m_dr_tot=0;	
					
					 out.println("<HTML><HEAD><TITLE>Client BlackList Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Client BlackList Report </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 //out.println("<BR>");	
						
					if(!more){
   
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");

					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='7%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
						out.println("<td width='18%' ><DIV class=div_input><b>Full Name</b></DIV></td>"); 
						out.println("<td width='7%' ><DIV class=div_input><b>NIC No</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>Date of Birth</b></DIV></td>"); 
						out.println("<td width='7%' ><DIV class=div_input><b>Client Type</b></DIV></td>"); 
						out.println("<td width='7%' ><DIV class=div_input><b>Client Cat.</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Address</b></DIV></td>"); 
						out.println("<td width='8%' ><DIV class=div_input><b>City</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Business Cert. No</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Key Decision Maker</b></DIV></td>"); 
						out.println("<td width='12%' ><DIV class=div_input><b>Modified&nbsp;Date</b></DIV></td>"); // added by udara 22-07-2022
						out.println("</tr>"); 
					  m_dataflag=true; 
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
							out.println("<td width='7%' ><DIV class=div_input onClick=\"show_client('"+rs1.getString(1)+"')\" style='cursor:hand' ><u>"+rs1.getString(1)+"</u></DIV></td>");
							out.println("<td width='18%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>"); 
							out.println("<td width='7%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>"); 
							out.println("<td width='8%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>"); 
							out.println("<td width='7%' ><DIV class=div_input>"+rs1.getString(5)+"</DIV></td>"); 
							out.println("<td width='7%' ><DIV class=div_input>"+rs1.getString(6)+"</DIV></td>"); 
							
							if(!rs1.getString(7).equals("-") || !rs1.getString(8).equals("-")){
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(7)+","+rs1.getString(8)+"</DIV></td>"); 
							}
							else if (rs1.getString(8).equals("-")){
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(7)+"</DIV></td>"); 
							}
							else if (rs1.getString(7).equals("-")){
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(8)+"</DIV></td>"); 
							}
							
							out.println("<td width='8%' ><DIV class=div_input onClick=\"show_city_drill('"+rs1.getString(9)+"')\" style='cursor:hand' ><u>"+rs1.getString(12)+"</DIV></td>"); 
							out.println("<td width='12%' ><DIV class=div_input>"+rs1.getString(10)+"</DIV></td>"); 
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(11)+"</DIV></td>"); 
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString("MOD_DATE_C")+"</DIV></td>"); // added by udara 22-07-2022
							out.println("</tr>");
							more = rs1.next();
					}	
					       
      	 		out.println("</table>");
			  		out.println("<br>"); 
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

			
	    }
			else if(m_chksql.equals("LOAD_VENDOR_BLACKLIST_REPORT")){
				
				String m_string="";				
				String m_sql="";	
				//String m_acc_type_code=req.getParameter("acc_type_code");
				
				
			//-------------------------- VENDOR BLACKLIST REPORT -------------------------------
			  
				rs= stmt1.executeQuery(" SELECT "+
							   " A.VENDOR_CODE, "+//1
							   " NVL(A.NAME,'-'), "+//2
							   " NVL(A.CATEGORY,'-'), "+//3
							   " NVL(B.DESCRIPTION,'-'), "+//4
							   " NVL(A.TYPE,'-'), "+//5
							   " DECODE(A.DEFAULT_VALUE,'Y','Yes','No') "+//6
							 "FROM "+m_schema_name+".AF_CO_MAS_VENDORS A,"+m_schema_name+".AF_CO_MAS_ITEM_CATEGORY B "+
							 "WHERE A.CATEGORY=B.ITEM_CAT_CODE(+) "+
							 "AND A.ACTIVE_STATUS='B' ORDER BY A.VENDOR_CODE ");

		         boolean mflag=true;							
				 	   String client_code = "";
					   boolean more = rs.next();
					
					 out.println("<HTML><HEAD><TITLE>Vendor BlackList Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("</SCRIPT>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> Vendor BlackList Report </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 //out.println("<BR>");	
						
					if(!more){
   
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");

					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Vendor Code</b></DIV></td>");
						out.println("<td width='18%' ><DIV class=div_input><b>Vendor Name</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Category</b></DIV></td>"); 
						out.println("<td width='15%' ><DIV class=div_input><b>Desc.</b></DIV></td>"); 
						out.println("<td width='10%' ><DIV class=div_input><b>Vendor Type</b></DIV></td>"); 
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
							out.println("<td width='10%' ><DIV class=div_input onClick=\"show_vendor_drill('"+rs.getString(1)+"')\" style='cursor:hand' ><u>"+rs.getString(1)+"</u></DIV></td>");
							out.println("<td width='18%' ><DIV class=div_input>"+rs.getString(2)+"</DIV></td>"); 
							out.println("<td width='10%' ><DIV class=div_input>"+rs.getString(3)+"</DIV></td>"); 
							out.println("<td width='15%' ><DIV class=div_input>"+rs.getString(4)+"</DIV></td>"); 
							out.println("<td width='10%' ><DIV class=div_input>"+rs.getString(5)+"</DIV></td>"); 
							out.println("</tr>");
							more = rs.next();
					}	
					       
      	 		out.println("</table>");
			  		out.println("<br>"); 
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






