/*
// header - edit "Data/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
// CREATED BY SANJEEWA ON 2010-07-14
// DISPLAY NAME INVOICE DETAIL REPORT
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_FA_OP_Insurance_Payable_Report_display extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs2;
	public String m_chksql;
	ServletOutputStream out = null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
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
			String m_client_code="";
			String m_from_date="";
			String m_to_date="";
			String query="";
			m_client_code = req.getParameter("client_code").trim();																		
			m_from_date = req.getParameter("as_at_date").trim();																		
			m_to_date = req.getParameter("as_at_date1").trim();
			
			
			stmt=conn.createStatement();
			ServletOutputStream out = res.getOutputStream();
			
			String m_insurence_done          = req.getParameter("insurance_done");
			if(m_insurence_done.equals("ALL")){
				m_insurence_done = "";
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Insurance Payable Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 30px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Payable Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("</table>"); 
			
			
			
			out.println("<br>");
			out.println("<table border=0 class='table' width='100%'>");
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='14%' align='center'>Canvased By  </td>");
			out.println("<td width='14%' align='center'>C/N Date </td>");
			out.println("<td width='14%' align='center'>Debit Note No.  </td>");
			out.println("<td width='14%' align='center'>Policy No.   </td>");
			out.println("<td width='14%' align='center'>Vehicle No.</td>");
			out.println("<td width='14%' align='center'>Name of  Insured.</td>");
			out.println("<td width='14%' align='center'>Total Premium.</td>");
			
			out.println("</tr>");
			//out.println(" "+
			rs2 = stmt.executeQuery(" "+
				" SELECT "+m_schema_name+".AF_CO_GET_FINANCE_NO(LAKDL.AF_CO_GET_FIN_NO(A.REF_NO)) FIN_NO,	"+
				" TO_CHAR(B.START_DATE,'DD-MM-YYYY'),				"+
				" A.REF_NO,				"+
				" B.policy_no,			"+
				" nvl(C.vehicle_no,'-'), "+
				" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.payer)Name_of_Insured,				"+
				" A.bal_to_be_paid 		"+
				" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,	"+
				" "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA B,		"+
				" "+m_schema_name+".af_co_pro_app_invoice_details C	"+
				" WHERE A.SUSPENSE_ENTRY_TYPE='INSURANCE' "+
				" AND A.RECEIVER LIKE '%"+m_client_code+"%' "+
				" AND "+m_schema_name+".AF_CO_GET_FINANCE_NO(LAKDL.AF_CO_GET_FIN_NO(A.REF_NO))=B.finance_no "+
				" AND    TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
				" AND    TO_DATE(TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
				//	" AND A.VALUE_DATE <='01-JUN-2011' "+
				" AND A.bal_to_be_paid >0 "+
				" AND B.pro_invoice_no=C.invoice_no "+
				" AND UPPER("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))) LIKE TRIM(UPPER('%"+m_insurence_done+"%')) ");
			
			
			
			double m_sub_total=0;
			while (rs2.next()) {	
				out.println("<tr class='div_input'>");
				out.println("<td width='10%'><DIV class='div_input'align='left' > 					"+rs2.getString(1)+" </DIV></td>"); 
				out.println("<td width='10%'><DIV class='div_input'align='center'  > 					"+rs2.getString(2)+" </DIV></td>"); 
				out.println("<td width='5%'><DIV class='div_input' align='left' > 					"+rs2.getString(3)+" </DIV></td>"); 
				out.println("<td width='5%'><DIV class='div_input' align='left'> 					"+rs2.getString(4)+" </DIV></td>"); 
				out.println("<td width='15%'><DIV class='div_input'align='center'  > 					"+rs2.getString(5)+" </DIV></td>"); 
				out.println("<td width='10%'><DIV class='div_input'align='center' > 					"+rs2.getString(6)+" </DIV></td>"); 
				out.println("<td width='10%'><DIV class='div_input'align='right' >					"+nf.format(rs2.getDouble(7))+" </DIV></td>");	
				
				out.println("</tr>");
				
				m_sub_total=m_sub_total+rs2.getDouble(7);
			}
			out.println("<tr class='div_input'>");
			out.println("<td width='5%' colspan='6'><DIV class='div_input'align='Left'  ><b>Total</td>");
			out.println("<td width='5%'><DIV class='div_input' align='right' ><b>"+nf.format(m_sub_total)+"</DIV></td>"); 
			//	out.println("<td width='5%'><DIV class='div_input'  >&nbsp;</DIV></td>"); 
			//	out.println("<td width='5%'><DIV class='div_input'  >&nbsp;</DIV></td>"); 
			//	out.println("<td width='5%'><DIV class='div_input'  >&nbsp;</DIV></td>"); 
			//	out.println("<td width='5%'><DIV class='div_input'  >&nbsp;</DIV></td>"); 
			//	out.println("<td width='10%'><DIV class='div_input' >&nbsp;</DIV></td>"); 
			//	out.println("<td width='15%'><DIV class='div_input' >&nbsp;</DIV></td>"); 
			out.println("</tr>");
			
			
			
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</BODY>"); 
			out.println("</html>"); 
			out.flush();
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}







