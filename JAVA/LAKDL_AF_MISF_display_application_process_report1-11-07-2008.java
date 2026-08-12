
//--
//SCREEN NAME:APPLICATION PROCESS REPORT
//CREATED BY:delanjali 
//DATE/TIME:
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_application_process_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_application_process_report1 extends javax.servlet.http.HttpServlet { 

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

			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_screen_type= req.getParameter("chksql");
			
			String m_app_no= req.getParameter("application_no");
			String m_client_no= req.getParameter("client_code");
			String m_schema_name = m_sn_methods.schema_name;


			String m_order_by   = "APPLICATION_NO";	
			String m_sort_by = "ASC";
							
							if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			          m_order_by = req.getParameter("order_by");
			          m_sort_by = req.getParameter("sort_by");

		}
	
		
			if (m_app_no==null){
			m_app_no="";
			}
			if (m_client_no==null){
			m_client_no="";
			}


			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Application Process Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
		
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Application Process Report - \"+m_val;"); 
			out.println("}");
			
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\" Application Process Report  \";"); 
			out.println("}");

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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_application_process_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&client_code="+m_client_no+"&application_no="+m_app_no+"\";");
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			
			out.println("function load_sansion_report(m_app_no) {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Credit_Approval_Saction_Letter?pre=V-APP&appro=VERIFY-M&qry=V-APP&Hid_scr_name=AF_MK_APP_STATUS_APPROVE_2&applicaton_no=\"+m_app_no;");
			out.println("window.open(m_url);"); 

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
			out.println("<tr><td width='50%' align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Application Process Report</td>");
			out.println("<td width='50%' class='pdn_txtpos2' align='right'><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td></tr>"); 

			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=14 align=right></td>");
			out.println("<td colspan=15 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_out_value();'></td>");
			out.println("</tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column'     >APPLICATION NO</td>"); 
			out.println("<td width='20%' class='txt_report_column'     >CLIENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >FINANCE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >APPLICATION STATUS</td>");
			out.println("<td width='10%' class='txt_report_column'     >TRANSACTION TYPE </td>"); 
			out.println("<td width='10%' class='txt_report_column'     >DIVISION CODE </td>"); 
			out.println("<td width='10%' class='txt_report_column'     >ENTER DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >ENTER USER</td>"); 
	 		out.println("</tr >"); 
			
			
		if(m_app_no.equals(" ") && !m_client_no.equals(" ")){
			
			
    pstmt = conn.prepareStatement("SELECT APPLICATION_NO, "+
		" CLIENT_CODE, "+
    " "+m_schema_name+".af_co_get_client_name(CLIENT_CODE), "+
		" NVL(FINANCE_NO,'-'), "+
    " INITCAP(DECODE(APPLICATION_STATUS,'ENT_CON','APPLICATION COMPLETED','TERMI','TERMINATED','VERIFY1','CREDIT VERIFICATION','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','PURCHASE ORDER LEVEL',APPLICATION_STATUS)), "+
		" TRANSACTION_TYPE, "+
    " NVL(DIVISION_CODE,'-'), "+
		" ENT_USER, "+
    " TO_CHAR(ENT_DATE,'DD-MM-YYYY') "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE CLIENT_CODE like UPPER('"+m_client_no+"%') "+
		" ORDER BY APPLICATION_NO DESC ");
			
			
			}else if(!m_app_no.equals(" ") && !m_client_no.equals(" ")){
		  
			 pstmt = conn.prepareStatement("SELECT APPLICATION_NO, "+
		" CLIENT_CODE, "+
    " "+m_schema_name+".af_co_get_client_name(CLIENT_CODE), "+
		" NVL(FINANCE_NO,'-'), "+
    " INITCAP(DECODE(APPLICATION_STATUS,'ENT_CON','APPLICATION COMPLETED','TERMI','TERMINATED','VERIFY1','CREDIT VERIFICATION','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','PURCHASE ORDER LEVEL',APPLICATION_STATUS)), "+
		" TRANSACTION_TYPE, "+
		" NVL(DIVISION_CODE,'-'), "+
		" ENT_USER, "+
    " TO_CHAR(ENT_DATE,'DD-MM-YYYY') "+
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE CLIENT_CODE like UPPER('"+m_client_no+"%') AND "+
		" UPPER(APPLICATION_NO) like UPPER('"+m_app_no+"%') "+
		" ORDER BY APPLICATION_NO DESC ");
			
		  
			
			}
			
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

						out.println("<TD class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=load_sansion_report('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><U>"+rs.getString(4)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(6)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(7)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(8)+"</TD>");
						out.println("</tr >"); 
						j=j+1;
						more=rs.next(); 
			} 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=14 align=right></td>");
			out.println("<td colspan=15 align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_out_value();'></td>");

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


