//--
//SCREEN NAME	:QUOTATION REPORT
//CREATED BY	:DELANJALI
//DATE/TIME		:2007-03-27
//NOTES				:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_quotation_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_quotation_report1 extends javax.servlet.http.HttpServlet { 

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
			String m_fschema_name=m_sn_methods.client_name.trim();

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 

			String m_schema_name = m_sn_methods.schema_name;


				String m_inq_no=req.getParameter("inq_no");
				String m_price_no=req.getParameter("price_no");
				String m_qu=req.getParameter("quotation");
				String m_status=req.getParameter("status");
				String m_order_by=req.getParameter("order_by");
				String m_sort_by=req.getParameter("sort_by");
		


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Quotation Report</TITLE>"); 
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
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'> Quotation Report</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			


		pstmt = conn.prepareStatement("SELECT A.QUOTATION_NO, "+//1
			"A.INQUIRY_NO, "+//2
			"B.PRICING_NO, "+//3
			"A.ENT_USER, "+//4
			"A.ENT_DATE, "+//5
			"DECODE(A.STATUS,'Y','Yes','P','Pending'), "+//6
			"NVL(A.APPR_USER,'-'), "+//7
			"NVL(TO_CHAR(A.APPR_DATE,'DD-MM-YYYY'),'-'), "+//8
			"B.OPTION_ID, "+//9
			"B.QTY, "+//10
			"B.GROSS_AMOUNT, "+//11
			"B.VAT_AMOUNT, "+//12
			"B.GROSS_RENTAL, "+//13
			"B.VAT_RENTAL, "+//14
			"B.RESIDUAL_AMOUNT, "+//15
			"B.PERIOD, "+//16
			"B.CONDITION_OF_ASSET, "+//17
			"B.MAKE_CODE, "+//18
			"B.MODEL_CODE, "+//19
			"B.MOD_USER,  "+//20
			"B.MOD_DATE  ,"+//21
			"(select make_desc from "+m_schema_name+".AF_CO_MAS_MAKE where MAKE_code=B.MAKE_CODE), "+
			"(select description from "+m_schema_name+".AF_CO_MAS_MODEL where model_code=B.MODEL_CODE), "+
			"(select description from "+m_schema_name+".AF_CO_MAS_CONDITION_OF_ASSET where Code=B.CONDITION_OF_ASSET) "+
			"FROM "+m_schema_name+".AF_MK_PRO_QUOTATION A,"+m_schema_name+".AF_MK_PRO_QUOTATION_DET B "+
			"WHERE A.QUOTATION_NO=B.QUOTATION_NO "+
			"AND UPPER(A.QUOTATION_NO) LIKE UPPER('"+m_qu+"%') "+
			"AND UPPER(A.INQUIRY_NO) LIKE UPPER('"+m_inq_no+"%') "+
			"AND UPPER(B.PRICING_NO) LIKE UPPER('"+m_price_no+"%') "+
			"AND upper(A.STATUS) LIKE upper('"+m_status+"%') "+
			"ORDER BY "+m_order_by+" "+m_sort_by+" "); 

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table' border=0>"); 

			while(more){
			String m_quotation=rs.getString(1);

			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=0>"); 
	
			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>QUOTATION NO</td><TD width='15%' align='left' class='txt_report_data' align='right' style=cursor:hand;cursor-color:blue onclick=show_quotation_drill('"+rs.getString(1)+"')>: <u>"+rs.getString(1)+"</u></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	
			
			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>INQUIRY NO</td><TD width='15%' align='left'  class='txt_report_data' align='right' style=cursor:hand;cursor-color:blue onclick=show_inquiry_drill('"+rs.getString(2)+"')>: <u>"+rs.getString(2)+"</u></TD>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
	

			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='10%' class='txt_report_column'>PRICING NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPROVE  USER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPROVE DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>OPTION ID</td>"); 
			out.println("<td width='10%' class='txt_report_column'>QTY</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CONDITION OF ASSET</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MAKE CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>MODEL CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PERIOD</td>"); 
			out.println("</tr >"); 

			while(m_quotation.trim().equals(rs.getString(1))){

			out.println("<tr >"); 
			out.println("<TD class='txt_report_data' align='right' style=cursor:hand;cursor-color:blue onclick=show_pricing_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(24)+"</TD>");
			out.println("<TD class='txt_report_data' align='right' style=cursor:hand;cursor-color:blue onclick=show_make_details_drill('"+rs.getString(18)+"')><u>"+rs.getString(22)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right' style=cursor:hand;cursor-color:blue onclick=show_model_details_drill('"+rs.getString(19)+"')><u>"+rs.getString(23)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs.getString(16)+"</TD>");
			out.println("</tr >"); 
			
			
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='20%'class='txt_report_column' style='{text-align:left;}'>GROSS AMOUNT</td>");
			out.println("<TD width='15%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
			out.println("<TD width='10%'></TD><td width='20%' class='txt_report_column' style='{text-align:left;}'>VAT AMOUNT</td>");
			out.println("<TD width='*%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(12))+"</TD>");
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='20%' class='txt_report_column' style='{text-align:left;}'>GROSS RENTAL</td>");
			out.println("<TD width='15%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(13))+"</TD>");
			out.println("<TD width='10%'></TD><td width='20%' class='txt_report_column' style='{text-align:left;}'>VAT RENTAL</td>"); 
			out.println("<TD width='*%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("<td width='20%' class='txt_report_column' style='{text-align:left;}'>RESIDUAL AMOUNT</td>");
			out.println("<TD width='*%' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(15))+"</TD>");
			out.println("</tr >"); 

			more=rs.next(); 
			 
			if(!more){
			break;
			}
	
			}	
			
			if(more){
			m_quotation=rs.getString(1);
			}

			out.println("</table>"); 
			out.println("<BR >"); 
			out.println("<HR>"); 

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


