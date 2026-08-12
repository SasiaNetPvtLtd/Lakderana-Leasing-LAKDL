import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:20-02-2007

public class LAKDL_AF_MAS_emp_details_drill_down extends javax.servlet.http.HttpServlet {
	/*
	Connection conn;
	Statement stmt1;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
    public ResultSet rs1;
	public String m_chksql;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn=null;
	Statement stmt1=null;
	CallableStatement callstmt=null;
	java.text.NumberFormat nf=null,nf1=null;
    
     ResultSet rs1=null;
	 String m_chksql=null;
		
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
			else if(m_chksql.equals("LOAD_EMP_DETAILS_DRILL")){
				
				String m_string="";				
				String m_sql="";	
				String m_emp_code=req.getParameter("emp_code");
				String m_eff_date = "";
			
			
			rs1= stmt1.executeQuery(" SELECT   NVL(A.EMP_CODE,'-'),"+//1
  			"  NVL(A.FIRST_NAME,'-'),"+//2
  			"  NVL(A.LAST_NAME,'-'),"+//3
  			"  NVL(A.ADDRESS,'-'),"+//4
  			"  NVL(B.LOCATION_DESC,'-'),"+//5
  			"  NVL(INITCAP(A.AREA_CODE),'-'),"+//6
  			"  NVL(C.CITY_DESC,'-'),"+//7
  			"  NVL(A.CONTACT_NO,'-'),"+//8
  			"  NVL(D.DESIGNATION_NAME,'-'),"+//9
  			"  NVL(A.DIVISION_CODE,'-'),"+//10
  			"  NVL(A.EPF_NO,'-'),"+//11
  			"  NVL(A.ID_NO,'-'),"+//12
  			"  DECODE(A.ACTIVE_STATUS,'Y','Yes','N','No'),"+//13
  			"  NVL(A.ENT_USER,'-'),"+//14
  			"  TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH24:MI'),"+//15
  			"  NVL(A.MOD_USER,'-'),"+//16
  			"  NVL(TO_CHAR(A.MOD_DATE,'DD-MM-YYYY HH24:MI'),'-')"+//17
 			" FROM "+m_schema_name+".CO_CO_MAS_EMPLOYEE_BK A,"+m_schema_name+".AF_CO_MAS_LOCATION B,"+m_schema_name+".AF_CO_MAS_CITY C,"+m_schema_name+".CO_CO_MAS_DESIGNATION D "+
 			" WHERE A.EMP_CODE='"+m_emp_code+"' AND A.LOCATION_CODE=B.LOCATION_CODE AND A.CITY_CODE=C.CITY_CODE "+
 			" AND A.DESIGNATION_CODE=D.DESIGNATION_CODE ");
				

		 	String client_code = "";
			boolean mflag=true;	

				boolean more = rs1.next();
					out.println("<HTML><HEAD><TITLE>Employee Modification Details  </TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B>Employee Modification Details</B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
					
				if(!more){
				 	out.println("<table align='center' width='100%' class='table' >");	
					out.println("<tr>");
					out.println("<td width='*%' align='center' ><DIV class=div_input><b>No Records Available</b></DIV></td>");
					out.println("</tr>"); 	
					out.println("</table>");
				}	
			  else if(more){
					
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");//class=txt_report_column
					out.println("<td width='15%' ><DIV class=div_input><b>First Name</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Last Name </b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Address</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Location</b></DIV></td>"); 
					out.println("<td width='10%' ><DIV class=div_input><b>Area code</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>City</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>Contact No</b></DIV></td>");  
					out.println("<td width='15%' ><DIV class=div_input><b>Designation</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>Division Code</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>EPF No</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>ID No</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>Active Status</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>Ent User</b></DIV></td>");  
					out.println("<td width='20%' ><DIV class=div_input><b>Ent Date</b></DIV></td>");  
					out.println("<td width='10%' ><DIV class=div_input><b>Mod User</b></DIV></td>");  
					out.println("<td width='20%' ><DIV class=div_input><b>Mod Date</b></DIV></td>");  
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
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(2)+"</DIV></td>");
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(3)+"</DIV></td>");
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(4)+"</DIV></td>");
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(5)+"</DIV></td>"); 
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(6)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(7)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(8)+"</DIV></td>");  
							out.println("<td width='15%' ><DIV class=div_input>"+rs1.getString(9)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(10)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(11)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(12)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(13)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(14)+"</DIV></td>");  
							out.println("<td width='20%' ><DIV class=div_input>"+rs1.getString(15)+"</DIV></td>");  
							out.println("<td width='10%' ><DIV class=div_input>"+rs1.getString(16)+"</DIV></td>");  
							out.println("<td width='20%' ><DIV class=div_input>"+rs1.getString(17)+"</DIV></td>");  
							out.println("</tr>"); 
							more = rs1.next();	 
					}

				}	
				 	out.println("</table>");
					out.println("</form>"); 
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


