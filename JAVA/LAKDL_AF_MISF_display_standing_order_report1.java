
//--
//SCREEN NAME:STANDING ORDER REPORT
//CREATED BY:
//DATE/TIME:
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_standing_order_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_standing_order_report1 extends javax.servlet.http.HttpServlet { 

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

			String m_start_date = req.getParameter("start_date");	
			String m_end_date= req.getParameter("end_date");
			String m_finance=req.getParameter("finance_no");
			String m_invoice=req.getParameter("invoice_no");
			String m_so_no=req.getParameter("so_no");
			String m_acc_no=req.getParameter("acc_no");
			String m_schema_name = m_sn_methods.schema_name;
			String m_fschema_name=m_sn_methods.client_name.trim();

			if(m_start_date==null){
			m_start_date="";
			}
			
			if(m_end_date==null){
			m_end_date="";
			}
			if(m_finance==null){
			m_finance="";
			}
			if(m_invoice==null){
			m_invoice="";
			}
			if(m_so_no==null){
			m_so_no="";
			}
			if(m_acc_no==null){
			m_acc_no="";
			}
			String m_order_by   = "FINANCE_NO";	
			String m_sort_by = "ASC";
							
			if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			m_order_by = req.getParameter("order_by");
			m_sort_by = req.getParameter("sort_by");

		}



			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Standing Order report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_order_by+"'){");
			out.println("	   if('"+m_sort_by+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_standing_order_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&so_no="+m_so_no+"&start_date="+m_start_date+"&end_date="+m_end_date+"&m_acc_no="+m_acc_no+"&finance_no="+m_finance+"&invoice_no="+m_invoice+"\";");
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"MIS - Payment Details Report - \"+m_val;"); 
			out.println("}");
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\"MIS - Payment Details Report  \";"); 
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
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Standing Order report</td></tr>"); 

			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right></td>");
			out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_out_value();'></td>");
			out.println("</tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO') >FINANCE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Standing Order No'    onclick=sort_data('SO_NO') >SO NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Invoice No'    onclick=sort_data('INVOICE_NO') >INVOICE NO</td>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Start Date'    onclick=sort_data('START_DATE') >START DATE</td>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - End Date'    onclick=sort_data('END_DATE') >END DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Account No'    onclick=sort_data('ACC_NO') >ACC NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Bank Code'    onclick=sort_data('BANK_CODE') >BANK CODE</td>"); 
			out.println("<td width='14%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Bank Name'    onclick=sort_data('BANK_CODE') >BANK NAME</td>");  // Added by Chandana on 21/05/2007 for Ref No 45
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') >STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column2' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('AMOUNT') align='right'  >AMOUNT</td>"); 
			out.println("</tr >"); 


			pstmt = conn.prepareStatement("SELECT a.FINANCE_NO FINANCE_NO "+
			",a.SO_NO SO_NO, "+
			"invoice_no, "+
			"to_char(a.START_DATE,'dd-mm-yyyy') START_DATE, "+
			"to_char(a.END_DATE,'dd-mm-yyyy') END_DATE, "+
			"a.ACC_NO ACC_NO, "+
			"a.BANK_CODE BANK_CODE, "+
			"decode(a.STATUS,'Y','Yes','N','No') STATUS, "+
			"a.AMOUNT AMOUNT, "+
			"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BANK_CODE),'-') "+  // Added by Chandana on 21/05/2007 for Ref No 45
			"FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS a, "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS b, "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+
			"where a.finance_no=b.finance_no "+
			"and b.APPLICATION_NO=C.APPLICATION_NO "+
			"AND UPPER(a.FINANCE_NO)  like UPPER('"+m_finance+"%') "+
			"AND UPPER(a.ACC_NO)  like UPPER('"+m_acc_no+"%') "+
			"and upper(a.SO_NO)  like upper('"+m_so_no+"%') "+
			"and upper(invoice_no)  like upper('"+m_invoice+"%') "+
			"AND APPLICATION_STATUS='ACTIVATED' "+
			"order by "+m_order_by+" "+m_sort_by+" ");
			
			rs=pstmt.executeQuery(); 
			int j=0;

			boolean more=rs.next();
			while(more){
					if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			
      out.println("<tr class=tr_input >");
			}

			out.println("<TD class='txt_report_data' STYLE='{cursor:hand;}' onclick=show_finance_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
			out.println("<TD class='txt_report_data' STYLE='{cursor:hand;}'  onclick=show_std_order_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></TD>");
			out.println("<TD class='txt_report_data' STYLE='{cursor:hand;}' onclick=show_proforma_invoice_drill('"+rs.getString(3)+"')><U>"+rs.getString(3)+"</U></TD>");
			out.println("<TD class='txt_report_data' >"+rs.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' >"+rs.getString(5)+"</TD>");  
			out.println("<TD class='txt_report_data' >"+rs.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' >"+rs.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' >"+rs.getString(10)+"</TD>"); // Added by Chandana on 21/05/2007 for Ref No 45
			if(rs.getString(8)!=null){
			out.println("<TD class='txt_report_data' >"+rs.getString(8)+"</TD>");
			}
			if(rs.getString(8)==null){
			out.println("<TD class='txt_report_data' >-</TD>");
		
			}

			
			
			
			out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(9))+"</TD>");
			out.println("</tr >"); 
			more=rs.next(); 
			j=j+1;
			} 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right></td>");
			out.println("<td colspan=9 align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_out_value();'></td>");

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


