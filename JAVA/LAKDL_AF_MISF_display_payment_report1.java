//--
//SCREEN NAME	:PAYMENT DETAILS REPORT
//CREATED BY	:delanjali
//DATE/TIME		:2007-03-27
//NOTES				:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_payment_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_payment_report1 extends javax.servlet.http.HttpServlet { 

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

			String m_schema_name = m_sn_methods.schema_name;

			String m_screen_type= req.getParameter("chksql");

			String m_pay_no=req.getParameter("payment_no");
			String m_sus_ref_no=req.getParameter("sus_ref_no");

			String m_app_no=req.getParameter("application_no");
			String m_invoice_no=req.getParameter("invoice_no");
			String m_client_code=req.getParameter("client_code");
			String m_finance_no=req.getParameter("finance_no");
			
			String m_fschema_name=m_sn_methods.client_name.trim();

						
			String m_order_by   = "PAYMENT_NO";	
			String m_sort_by = "ASC";
							
							if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			          m_order_by = req.getParameter("order_by");
			          m_sort_by = req.getParameter("sort_by");

							}
							
			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Payment Details Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
		
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Payment Details Report - \"+m_val;"); 
			out.println("}");
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\" Payment Details Report  \";"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_payment_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&client_code="+m_client_code+"&application_no="+m_app_no+"&sus_ref_no="+m_sus_ref_no+"&payment_no="+m_pay_no+"&finance_no="+m_finance_no+"&invoice_no="+m_invoice_no+"\";");
			out.println(" window.location.href=m_url;"); 
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
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'> Payment Details Report</td></tr>"); 
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
			
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Application No'    onclick=sort_data('APPLICATION_NO') >APPLICATION NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Finance No'    onclick=sort_data('FINANCE_NO')  >FINANCE NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Proforma Invoice No'    onclick=sort_data('PRO_INVOICE_NO') >PRO INVOICE NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Payment No'    onclick=sort_data('PAYMENT_NO') >PAYMENT NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Paid Amount'    onclick=sort_data('PAY_AMOUNT') >PAY AMOUNT</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('PROCESS_STATUS') >STATUS</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Sus Ref No'    onclick=sort_data('SUS_REF_NO') >SUS REF NO</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Paid Amount'    onclick=sort_data('BAL_TO_BE_PAID') >BAL TO BE PAID</td>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client Name'    onclick=sort_data('FULL_NAME') >CLIENT NAME</td>");
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document'    onclick=sort_data('DOC_NAME') >DOCUMENT</td>");
		
			
			out.println("</tr>");

			//out.println
			pstmt = conn.prepareStatement
			("SELECT "+
      "A.PAYMENT_NO PAYMENT_NO, "+                //1
      "H.APPLICATION_NO APPLICATION_NO, "+        //2
			"J.INVOICE_NO PRO_INVOICE_NO, "+            //3
			"nvl((A.PAY_AMOUNT),0) PAY_AMOUNT, "+       //4
			"nvl(H.FINANCE_NO,'-') FINANCE_NO, "+       //5
			"NVL(DECODE(A.PROCESS_STATUS,'Y','Yes','RE-APP','Reqn Approve','DISBRS','Disburse','PRINT','Print','APPRO1','Approve1','APPRO2','Approve2'),'-') PROCESS_STATUS,  "+//6
			"K.SUS_REF_NO SUS_REF_NO,     "+             //7
			"nvl(K.BAL_TO_BE_PAID,0) BAL_TO_BE_PAID,  "+ //8
      "nvl(H.CLIENT_CODE,'-') CLIENT_CODE,       "+   //9
			""+m_schema_name+".af_co_get_client_name(h.CLIENT_CODE) FULL_NAME  "+//10
      //"F.DOCUMENT_TYPE DOCUMENT_TYPE,    "+            //11	
			//""+m_schema_name+".AF_CO_GET_DOC_DESC(F.DOCUMENT_TYPE) DOC_NAME  "+//12
      "FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
			//""+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT F,  "+
			""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS H, "+
      //""+m_schema_name+".AF_CR_PRO_CRSCORE I,   "+
			""+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS J, "+
      ""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT K   "+
			"WHERE  A.SUS_REF_NO=K.SUS_REF_NO "+
      "AND  K.REF_NO=J.INVOICE_NO "+
			"AND J.APPLICATION_NO=H.APPLICATION_NO "+
      //"AND H.APPLICATION_NO=F.APPLICATION_NO "+
     // "AND I.APPLICATION_CODE=H.APPLICATION_NO  "+    
			"AND UPPER(A.PAYMENT_NO) LIKE UPPER('"+m_pay_no+"%')  "+
    	"AND UPPER(K.SUS_REF_NO) LIKE UPPER('"+m_sus_ref_no+"%') "+
			"AND UPPER(H.APPLICATION_NO) LIKE UPPER('"+m_app_no+"%')  "+
      "AND UPPER(J.INVOICE_NO) LIKE ('"+m_invoice_no+"%') "+
			"AND UPPER(H.CLIENT_CODE) like ('"+m_client_code+"%')  "+
			"AND UPPER(H.FINANCE_NO) LIKE UPPER('"+m_finance_no+"%')  "+
			"AND  UPPER(H.APPLICATION_STATUS)='ACTIVATED' "+
						"ORDER BY "+m_order_by+" "+m_sort_by+" ");

			
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
					
				
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(5)+"')><u>"+rs.getString(5)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_payment_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</TD>");
			out.println("<TD class='txt_report_data' align='right' >"+rs.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_sus_payment_drill('"+rs.getString(7)+"')><u>"+rs.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(8))+"</TD>");
			out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(9)+"') ><u>"+rs.getString(10)+"</u></TD>");
			//out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_document_drill('"+rs.getString(11)+"')><u>"+rs.getString(12)+"</TD>");
			
			out.println("</tr >"); 
			more=rs.next(); 
			j=j+1;
			} 

					
			out.println("<tr class=tr_input>");
			out.println("<td colspan=8 align=right></td>");
			out.println("<td colspan=9 align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_out_value();'></td>");

			out.println("</tr>");
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


