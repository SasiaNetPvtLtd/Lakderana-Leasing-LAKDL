
//--
//SCREEN NAME:INQUARY - CLIENT DETAILS
//CREATED BY:CHANDANA
//DATE/TIME:27/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_Inquary _Client_Details_Drill?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_Inquary_Client_Details_Drill extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.equals("MAIN")){
			
			String m_inq_no = req.getParameter("INQ_NO");

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Inquary - Client Details</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Inquary - Client Details</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column'>CLIENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ID NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TELEPHONE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MOBILE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>FAX NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>EMAIL</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CLIENT CATEGORY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CLIENT TYPE</td>"); 
			out.println("</tr >"); 
																			
			pstmt = conn.prepareStatement(" SELECT INQUIRY_CODE, "+
			                              "NVL(CLIENT_NAME,'-'), "+
																		"NVL(ID_NO,'-'), "+
																		"NVL(TEL_NO,'-'), "+
																		"NVL(MOBILE_NO,'-'), "+
																		"NVL(FAX_NO,'-'), "+
																		"NVL(EMAIL,'-'), "+
																		"NVL(CLIENT_CATEGORY,'-'), "+
																		"NVL(CLIENT_TYPE,'-'), "+
																		"NVL(LEAD_SOURCE_CATEGORY,'-')  "+
																		"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
																		"WHERE INQUIRY_CODE='"+m_inq_no+"' ");
																			
																			
																			
																			
																			
																			
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			pstmt.close();
			conn.close();
			out.flush();
			out.close();
			}


			}

			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


