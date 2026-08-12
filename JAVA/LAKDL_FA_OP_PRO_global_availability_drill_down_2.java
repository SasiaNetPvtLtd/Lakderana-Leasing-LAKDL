/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
            
// DEVELOP BY : DINETH FOR OFSCL FACTORING    DATE:2008-09-09
  
public class LAKDL_FA_OP_PRO_global_availability_drill_down_2 extends javax.servlet.http.HttpServlet {
	Connection conn;
	Statement stmt1,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
    
  public ResultSet rs1,rs2;
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

			
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			else if(m_chksql.trim().equals("LOAD_GLOBAL_AVAILABILITY_DRILL"))
			{
			String m_option_no=req.getParameter("OPTION_NO");
			if(m_option_no.equals("OPT29")){
					double m_val=0;
					double m_val1=0;
					rs1= stmt1.executeQuery(" SELECT A.CLIENT_CODE, "+
																	"	"+m_schema_name+".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE), "+
																	" A.FACILITY_NO, "+
																	" "+m_schema_name+".FA_CLIENT_AV_POD_CANCEL(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+
																	"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
																	" WHERE "+m_schema_name+".FA_CLIENT_AV_POD_CANCEL(A.CLIENT_CODE,A.FACILITY_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY'))>0 "+
																	" GROUP BY A.CLIENT_CODE,A.FACILITY_NO ");
								
					out.println("<HTML><HEAD><TITLE></TITLE><SCRIPT>");
				
					out.println(" function show_client_details(client_code,facility_no){ ");
					out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_OP_ClientWise_Details?CLIENT_CODE=\"+client_code+\"&FACILITY_NO=\"+facility_no+\"\";");	
 					out.println("		popupwin = window.open(m_url,\"display_window1\",\"width=800,height=800,scrollbars=2\");");
					out.println(" }");
					out.println("</SCRIPT></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<p class=pdn_txtpos2><center><u><B>TOTAL CANCELLED POD CHEQUES</B></center></u></p>");
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='10%' ><DIV class=div_input><b>Client Code</b></DIV></td>");
					out.println("<td width='15%' ><DIV class=div_input><b>Client Name</b></DIV></td>");
					out.println("<td width='10%' ><DIV class=div_input><b>Facility No</b></DIV></td>");
					out.println("<td width='10%' align=right><DIV class=div_input><b>Balance Amount</b></DIV></td>");
					out.println("</tr>");
					
					int j=1;
					
					while(rs1.next()){
						if(j==0){
							out.println("<tr bgcolor=\"#FFFFFF\">");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=0;
						}
						m_val=m_val+rs1.getDouble(4);
						//out.println("<td width='10%' class=div_input style='cursor:hand' ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='10%' class=div_input style='cursor:hand' onclick=\"show_client_details('"+rs1.getString(1)+"','"+rs1.getString(3)+"')\" ><u>"+rs1.getString(1)+"</u></td>");
						out.println("<td width='15%' class=div_input >"+rs1.getString(2)+"</td>");
						out.println("<td width='10%' class=div_input >"+rs1.getString(3)+"</td>");
						out.println("<td width='10%' class=div_input align=right>"+nf.format(rs1.getDouble(4))+"</td>");
						out.println("</tr>");						
					}
					out.println("<tr bgcolor=\"#999966\">");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ></td>");
					out.println("<td width='10%' class=div_input ><b>Total</b></td>");
					out.println("<td width='10%' class=div_input align=right><b>"+nf.format(m_val)+"</b></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</BODY><SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("</HTML>");
				}
	
	
		}
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
