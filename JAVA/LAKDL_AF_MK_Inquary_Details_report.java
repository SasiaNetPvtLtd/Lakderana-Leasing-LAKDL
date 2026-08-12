
//--
//SCREEN NAME:INQUARY DETAILS REPORT
//CREATED BY:CHANDANA
//DATE/TIME:27/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MK_Inquary_Details_report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_Inquary_Details_report extends javax.servlet.http.HttpServlet { 

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
			
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String header_name    = con_method.header_name;
			

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.equals("MAIN")){
			
			String m_inq_no= req.getParameter("INQ_NO");
			String m_from_date= req.getParameter("FROM_DATE");
			String m_to_date= req.getParameter("TO_DATE");	
			
			
			//out.println("m_inv_no"+m_inq_no+"m_from_date==="+m_from_date+"m_to_date==="+m_to_date);

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Inquiry Details Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			
			out.println("function load_data(m_val) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_Inquary_Client_Details_Drill?chksql=MAIN&INQ_NO=\"+m_val+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=110,top=110,width=650,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			
			out.println("function befor_back(){");
			out.println("  window.close()");
			out.println("}");
			
			
			
						
			
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			
			out.println("<tr><td height=\"6%\" class=\"pdn_mainHD\" class>"+header_name+"</td></tr>"); 
			//out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System</td></tr>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Inquiry Details Report</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column'>INQUIRY CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CLIENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CLIENT ADDRESS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>LEGAL ENTITY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CONTACT PERSON</td>"); 
			out.println("<td width='10%' class='txt_report_column'>INITIATION TYPE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>LEAD SOURCE CATEGORY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>LEAD SOURCE NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'>INTRODUCER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>INQUIRY STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TEAM</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MK OFFICER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MK SUPERVISOR</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SUB PRODUCT CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TRANSACTION SUB TYPE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ENTER DATE</td>"); 
			out.println("</tr >"); 
			
			
			
			if(!m_inq_no.equals("")){
						pstmt = conn.prepareStatement("SELECT INQUIRY_CODE, NVL(CLIENT_NAME,'-'), "+
			                              "ADDRESS||', '||ADDRESS2||','||CITY_CODE, "+
																		"NVL(LEGAL_ENTITY,'-'),NVL(STATUS,'-'),NVL(CONTACT_PERSON,'-'), "+
																		"NVL(INITIATION_TYPE,'-'),NVL(LEAD_SOURCE_CATEGORY,'-'), "+
																		"NVL(LEAD_SOURCE_NAME,'-'),NVL(INTRODUCER,'-'),NVL(INQUIRY_STATUS,'-'), "+
																		"NVL(TEAM,'-'),NVL(MK_OFFICER,'-'),NVL(MK_SUPERVISOR,'-'),NVL(SUB_PRODUCT_CODE,'-'), "+
																		"NVL(TRANSACTION_SUB_TYPE,'-'),TO_CHAR(ENT_DATE,'DD-MON-YYYY') "+
																		"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
																		"WHERE INQUIRY_CODE='"+m_inq_no+"' ");
			
			
			}else if((!m_from_date.equals("--"))&&(!m_to_date.equals("--"))){
			
			
									pstmt = conn.prepareStatement("SELECT INQUIRY_CODE, NVL(CLIENT_NAME,'-'), "+
			                              "ADDRESS||', '||ADDRESS2||','||CITY_CODE, "+
																		"NVL(LEGAL_ENTITY,'-'),NVL(STATUS,'-'),NVL(CONTACT_PERSON,'-'), "+
																		"NVL(INITIATION_TYPE,'-'),NVL(LEAD_SOURCE_CATEGORY,'-'), "+
																		"NVL(LEAD_SOURCE_NAME,'-'),NVL(INTRODUCER,'-'),NVL(INQUIRY_STATUS,'-'), "+
																		"NVL(TEAM,'-'),NVL(MK_OFFICER,'-'),NVL(MK_SUPERVISOR,'-'),NVL(SUB_PRODUCT_CODE,'-'), "+
																		"NVL(TRANSACTION_SUB_TYPE,'-'),TO_CHAR(ENT_DATE,'DD-MON-YYYY') "+
																		"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY "+
																		"WHERE TO_DATE(ENT_DATE,'DD-MM-YY')< TO_DATE('"+m_to_date+"','DD-MM-YY') AND "+
																		"TO_DATE(ENT_DATE,'DD-MM-YY')> TO_DATE('"+m_from_date+"','DD-MM-YY')");
			
			
			
			
			
			}else{			
			pstmt = conn.prepareStatement("SELECT INQUIRY_CODE, NVL(CLIENT_NAME,'-'), "+
			                              "ADDRESS||', '||ADDRESS2||','||CITY_CODE, "+
																		"NVL(LEGAL_ENTITY,'-'),NVL(STATUS,'-'),NVL(CONTACT_PERSON,'-'), "+
																		"NVL(INITIATION_TYPE,'-'),NVL(LEAD_SOURCE_CATEGORY,'-'), "+
																		"NVL(LEAD_SOURCE_NAME,'-'),NVL(INTRODUCER,'-'),NVL(INQUIRY_STATUS,'-'), "+
																		"NVL(TEAM,'-'),NVL(MK_OFFICER,'-'),NVL(MK_SUPERVISOR,'-'),NVL(SUB_PRODUCT_CODE,'-'), "+
																		"NVL(TRANSACTION_SUB_TYPE,'-'),TO_CHAR(ENT_DATE,'DD-MON-YYYY') "+
																		"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY");
       }                                 

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_inquiry_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></TD>");
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=load_data('"+rs.getString(1)+"')><u>"+rs.getString(2)+"</u></TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_legal_entity_drill('"+rs.getString(4)+"')><u>"+rs.getString(4)+"</u></TD>"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_lead_source_cat_drill('"+rs.getString(8)+"') ><u>"+rs.getString(8)+"</u></TD>"); 
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" >"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(11)+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_team_member_drill('"+rs.getString(12)+"') ><u>"+rs.getString(12)+"</u></TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(13)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(14)+"</TD>");
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_transaction_type_drill('"+rs.getString(15)+"')><u>"+rs.getString(15)+"</u></TD>"); 
						out.println("<TD class='txt_report_data' align='right' style=\"{cursor:hand; }\" onclick=show_transaction_sub_type_drill('"+rs.getString(16)+"')><u>"+rs.getString(16)+"</u></TD>"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(17)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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


