import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
       
// DEVELOP BY : MAHELA FOR OFSCL LEASING    DATE:23-03-2007

public class LAKDL_AF_MAS_display_seizer_list extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt_invoice;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;

    public ResultSet rs,rs1,rs2,rs3,rs4,rs_invoice;
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
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
			stmt=conn.createStatement();
			stmt_invoice=conn.createStatement();

				
				int count = 0;
				String m_string="";								
				String m_finance_no=req.getParameter("finance_no");
			
			
					out.println("<HTML><HEAD><TITLE> Seizer List </TITLE></HEAD>");
					
					out.println(" <SCRIPT language1.2='JavaScript' > ");
					
					out.println("	function show_transaction_info(m_client_code,m_finance_no){");
					out.println("    	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
					out.println("    	window.open(m_url); ");
					out.println("	}");	

					
					out.println(" </SCRIPT> ");
					
					
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url1+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Seizer List </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
			
					out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\"  cellpadding=\"2\"> "); 
	
					out.println("<tr>");
					out.println("  <td width=\"10%\"  align='left'><b>Seizer Code</b></td>");
					out.println("  <td width=\"10%\"  align='left'><b>First Name</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>Last Name</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>Address 1</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>Address 2</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>Mobile No.</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>Tel No.</b></td> ");
					out.println("  <td width=\"10%\"  align='left'><b>City Name</b></td> ");
					out.println("  <td width=\"*%\"   align='left'> &nbsp; </td> ");
					out.println("</tr>");

				
					stmt4 = conn.createStatement ();
					rs4 = stmt4.executeQuery("  "+
													" SELECT "+
													    " SEIZER_CODE, "+
													    " FIRST_NAME, "+
													    " LAST_NAME, "+
													    " ADDRESS1, "+
													    " ADDRESS2, "+
													    " MOBILE_NO, "+
													    " TEL_NO, "+
													    " "+m_schema_name+".AF_CO_GET_CITY_NAME(CITY_CODE) CITY_NAME "+
															" FROM "+m_schema_name+".AF_CO_MAS_SEIZER "+
													    	" WHERE ACTIVE_STATUS = 'Y' "+
															" ORDER BY SEIZER_CODE "+
													" ");
	    
					//boolean more4 = rs4.next();		
					
					
					while(rs4.next()){	
	
						out.println("<tr>");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("SEIZER_CODE")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("FIRST_NAME")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("LAST_NAME")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("ADDRESS1")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("ADDRESS2")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("MOBILE_NO")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("TEL_NO")+" </td> ");
						out.println("  <td width=\"10%\"   align='left'> "+rs4.getString("CITY_NAME")+" </td> ");
						out.println("</tr>");
	
					}

				
				out.println("</table >");
				
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
			
			


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


