
//--
//SCREEN NAME:TEST123
//CREATED BY:
//DATE/TIME:
//NOTES:
//URL:http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL__TEST_REPORT?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL__TEST_REPORT extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt = conn.createStatement();

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.equals("MAIN")){

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Test123</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function load_drill(m_item){");
			out.println("m_url='http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL__TEST_REPORT?chksql=DRILL_1&drill_item='+m_item;");
			out.println("popupwin = window.open(m_url,'displayWindow1','left=100,top=100,width=600,height=400,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
			out.println("popupwin.focus();");
			out.println("}");
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
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Test123</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			double m_f_txt_qty=0; 
			double m_f_txt_cost=0; 
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ASSET ID</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SUB MODEL CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SUPPLIER CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>QTY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>COST</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PURPOSE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PERIOD</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MODEL CODE</td>"); 
			out.println("</tr >"); 
			pstmt = conn.prepareStatement("");

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data_drill' align='right' onclick=\"load_drill('"+rs.getString(1)+"')\">"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</TD>");
						m_f_txt_qty=m_f_txt_qty+rs.getDouble(6);
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(7))+"</TD>");
						m_f_txt_cost=m_f_txt_cost+rs.getDouble(7);
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(14)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(m_f_txt_qty)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(m_f_txt_cost)+"</TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("<TD class='txt_report_data' align='left'></TD>");
						out.println("</tr >"); 

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

			else if(m_screen_type.equals("DRILL_1")){

			String M_PARAM_ITEM= req.getParameter("drill_item");

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Test123</TITLE>"); 
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
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Test123 for ASSET ID "+M_PARAM_ITEM+"</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			pstmt = conn.prepareStatement("SELECT a.asset_id, a.reg_no, a.reg_date, a.sub_model_code, a.pricing_no,        a.status, a.supplier_code, a.qty, a.cost, a.purpose, a.address,        a.city_code, a.period, a.application_no, a.ent_user, a.ent_date,        a.mod_user, a.mod_date, a.model_code   FROM af_co_pro_asset_details a where a.asset_id=?");
			pstmt.setString(1,M_PARAM_ITEM);
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();

			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td width='10%' class='txt_report_column'>ASSET ID</td>"); 
			out.println("<td width='10%' class='txt_report_column'>REG NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>REG DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SUB MODEL CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PRICING NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SUPPLIER CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>QTY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>COST</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PURPOSE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ADDRESS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CITY CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PERIOD</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ENT_USER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ENT_DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MOD_USER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MOD_DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MODEL_CODE</td>"); 
			out.println("</tr >"); 

			while(more){
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(11)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(12)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(13)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(14)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(15)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(16)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(17)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(18)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(19)+"</TD>");
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


