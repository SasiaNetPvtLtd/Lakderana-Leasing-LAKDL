import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
        
// DEVELOP BY : PRABASH DE SILVA    DATE:17-05-2012

public class LAKDL_AF_MISF_user_login_detail_report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt1,stmt_cr,stmt_dr;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
    public ResultSet rs1,rs,rs2;
	public ResultSet rs_cr,rs_dr;
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
			stmt_cr=conn.createStatement();
			stmt_dr=conn.createStatement();
			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.equals("LOGIN_DETAILS")){
				
				String m_string="";				
				String m_sql="";	
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");

			//-------------------------------------------------------------------------------------------
			  
				
						rs1= stmt1.executeQuery("  SELECT  (TO_CHAR(A.ENT_DATE,'DD-MM-YYYY')) LOG_DATE, "+
												"	(TO_CHAR(A.ENT_DATE,'HH:MI:SS PM'))LOGIN_TIME, "+
 												"	A.USER_NAME, "+
 												"	A.CLIENT_IP, "+ 
												"	B.DISPLAY_NAME LOGIN_SCREEN "+
												"	FROM "+m_schema_name+".CO_CO_PRO_LOGIN_LOG A,  "+m_schema_name+".CO_CO_MAS_USER_SCREEN B "+
												"	WHERE A.SCREEN_NAME= B.SCREEN_NAME "+
												"   AND A.SCREEN_NAME = 'AF_LOGIN' "+
												"   AND TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
												"   TO_DATE(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
												"   order by a.ent_date Asc ");
 					
			       		boolean m_dataflag=false;							
		         		boolean mflag=true;							
					   	boolean more = rs1.next();
						
					
					
					 out.println("<HTML><HEAD><TITLE>User Login Details </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
					 out.println("</SCRIPT>");
					 out.println("<br>");
		 		//	 out.println("<DIV align=center><img src=\""+m_html_client_url+"/images/logo.gif\"></DIV><br><hr>");
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>"); 							
					 out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					 out.println("<TR><TD align='Center' ><B> User Login Details From "+m_from_date+" To "+m_to_date+" </B></TD></TR>");
					 out.println("</TABLE>");
					 out.println("<hr>");	
					 out.println("<BR>");	
						
					if(!more){
					
						out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
						out.println("</TABLE>");	

					}					
				  if(more){
						out.println("<table align='center' width='100%' class='table' >");						
						out.println("<tr class=pdn_txtpos2>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='20%' align='center'><DIV class=div_input><b>Login Date</b></DIV></td>");
						out.println("<td width='20%' align='center' ><DIV class=div_input><b>Login Time</b></DIV></td>"); 
						out.println("<td width='20%' align='center'><DIV class=div_input><b>USER</b></DIV></td>"); 
						out.println("<td width='20%' align='center' ><DIV class=div_input><b>Client IP</b></DIV></td>"); 
				//		out.println("<td width='20%' align='center' ><DIV class=div_input><b>Login Screen</b></DIV></td>"); 
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
								out.println("<td width='20%' class=div_input >"+rs1.getString(1)+"</td>");
								out.println("<td width='20%' class=div_input >"+rs1.getString(2)+"</td>");
								out.println("<td width='20%' class=div_input >"+rs1.getString(3)+"</td>");
								out.println("<td width='20%' class=div_input >"+rs1.getString(4)+"</td>");
						//		out.println("<td width='20%' class=div_input >"+rs1.getString(5)+"</td>");

							out.println("</tr>");
							more = rs1.next();
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






