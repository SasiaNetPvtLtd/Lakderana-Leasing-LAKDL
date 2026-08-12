
//--
//SCREEN NAME:INVOICE DETAILS REPORT
//CREATED BY:CHANDANA
//DATE/TIME:30/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Invoice_Details_Report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

//Modified by Mahela on 10-04-2007

public class LAKDL_AF_MK_display_App_Client_data extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
     
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

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
		
	//		if(m_screen_type.equals("MAIN")){
			
			String m_app_no= req.getParameter("App_no2");
			String m_my_screen= "NEW" ;
		
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Invoice Details Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 

			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function save_client(val){	"); 
			out.println("	document.Form1.action=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_save_Client_as_Vender?&Client_no=\"+val;");
			out.println("		document.Form1.submit();"); 
		//	out.println("alert(val);");
			out.println("}"); 
			out.println("</script>");

		//	out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
		//	out.println("<FORM NAME='Form1' method='post'>"); 
		
			out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0' >");
			out.println("<FORM NAME='Form1' method='post'>"); 
		
			out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
			out.println("<TR><TD align='Center' ><B> Client Detail for Application No - "+m_app_no+"  </B></TD></TR>");
			out.println("</TABLE>");
			out.println("<BR><BR>");
				

			out.println("<table align='left' width='50%' class='table' border=0>"); 	
			out.println("<TR>");
		//	out.println(" SELECT DISTINCT"+
			rs=stmt.executeQuery(" SELECT DISTINCT"+
										" A.CLIENT_CODE,"+
   										" FULL_NAME,"+
   										" CLIENT_TYPE,"+
   										" ACTIVE_STATUS, "+
   										" NVL(VAT_REG_NO,'_'),"+
   										" ADDRESS1,"+
   										" NVL(ADDRESS2,'-'),"+
   										" CITY_CODE"+
										" FROM "+m_schema_name+".AF_CO_MAS_CLIENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B"+
										" WHERE A.CLIENT_CODE = B.CLIENT_CODE"+
										" AND B.APPLICATION_NO = '"+m_app_no+"' ");
									//	" AND B.APPLICATION_NO = 'AP20120315-1816' ");
		
	//	rs=pstmt.executeQuery(); 
	//	out.println(pstmt); 
			boolean more=rs.next();
			
					
			while(more){
				
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(01)</b></td>"); 
						out.println("<td width='20%' ><b>CLIENT CODE</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'  >"+rs.getString(1)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(02)</b></td>"); 
						out.println("<td width='20%' ><b>CLIENT NAME</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='35%' align='left'  >"+rs.getString(2)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(03)</b></td>"); 
						out.println("<td width='20%' ><b>CLIENT_TYPE</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(3)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(04)</b></td>"); 
						out.println("<td width='20%' ><b>ACTIVE STATUS</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(4)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(05)</b></td>"); 
						out.println("<td width='20%' ><b>VAT REG NO</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(5)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(06)</b></td>"); 
						out.println("<td width='20%' ><b>ADDRESS1</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(6)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(07)</b></td>"); 
						out.println("<td width='20%' ><b>ADDRESS2</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(7)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b>(08)</b></td>"); 
						out.println("<td width='20%' ><b>CITY CODE</td>");
						out.println("<td width='2%'>:</td>"); 
						out.println("<td width='50%' align='left'  >"+rs.getString(8)+"</td>"); 
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
						
						out.println("<tr height='25px'>");
						out.println("<td width='1%'></td>"); 
						out.println("<td width='4%'><b><input class='but_input' type='button' name='save' value=\"save\" onClick=\"save_client('"+rs.getString(1)+"')\"> </b></td>"); 
						out.println("<td width='20%' ><b></td>");
						out.println("<td width='2%'></td>"); 
						out.println("<td width='50%' align='left'  ></td>"); 
			
						out.println("<td width='*%'></td>"); 
						out.println("</tr>");
						
					
					
						more=rs.next(); 
			}

			
			out.println("</table>"); 
			

			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			//pstmt.close();
			conn.close();
			out.flush();
			out.close();
	//		}

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


