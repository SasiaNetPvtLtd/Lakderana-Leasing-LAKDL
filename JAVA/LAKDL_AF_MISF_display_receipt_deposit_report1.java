
//--
//SCREEN NAME:RECEIPT DEPOSIT REPORT
//CREATED BY:
//DATE/TIME:
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_receipt_deposit_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_receipt_deposit_report1 extends javax.servlet.http.HttpServlet { 

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
			
			
			String m_receipt_no=req.getParameter("receipt_no");
			String m_deposite_no=req.getParameter("deposite_no");
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			String m_schema_name = m_sn_methods.schema_name;

		
			if(m_receipt_no==null){
			m_receipt_no="";
			}
			if(m_deposite_no==null){
			m_deposite_no="";
			}

			String m_fschema_name=m_sn_methods.client_name.trim();

						
			String m_order_by   = "DIPOSIT_NO";	
			String m_sort_by = "ASC";
							
							if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			          m_order_by = req.getParameter("order_by");
			          m_sort_by = req.getParameter("sort_by");

		}



			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Receipt Deposit Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
		
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
							
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Receipt Deposit Report - \"+m_val;"); 
			out.println("}");
			out.println("function load_roll_out_value(){"); 
			out.println("help_box.innerHTML=\"Receipt Deposit Report  \";"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_deposit_report1?chksql=MAIN&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\"&receipt_no="+m_receipt_no+"&deposite_no="+m_deposite_no+"&from_date="+m_from_date+"&to_date="+m_to_date+"\";");

			out.println(" window.location.href=m_url;"); 
			out.println("}");
			//Added by Dineth on 2009-01-19
			out.println(" function show_slip(val){");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_Receipt_Deposit_Slip?chksql=print_deposit_slip&deposit_no=\"+val;"); 
			out.println("popupwin =window.open(m_url,'displayWindow3','left=110,top=110,width=660,height=700,toolbar=0,location=0,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');");
			out.println(" }");
			//End by Dineth on 2009-01-19
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
			out.println("<tr class='pdn_txtpos2'><td width='60%' align='left'  style='height: 18px' id='help_box'> Receipt Deposit Report</td>");
			out.println("<td width='40%' align='right'><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=6 align=right></td>");
			out.println("<td colspan=7 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_out_value();'></td>");
			out.println("</tr>");

			out.println("<tr class='pdn_txtpos2'>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Receipt No'    onclick=sort_data('RECEIPT_NO') >RECEIPT NO</td>");//Commented by Dineth on 2009-01-19 
			
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Deposit No'    onclick=sort_data('DIPOSIT_NO') >DEPOSIT NO</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Deposit Date'    onclick=sort_data('DIPOSIT_DATE') >DEPOSIT DATE</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Reference'    onclick=sort_data('REFERENCE') >REFERENCE</td>"); //Commented by Dineth on 2009-01-19
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Account No'    onclick=sort_data('ACCOUNT_NO') >ACCOUNT NO</td>"); //Commented by Dineth on 2009-01-19
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Branch'    onclick=sort_data('BRANCH_CODE') >BANK DEPOSITED BRANCH</td>");//Modified by Dineth on 2009-01-19 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Status'    onclick=sort_data('STATUS') >STATUS</td>");//Commented by Dineth on 2009-01-19 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Amount'    onclick=sort_data('AMOUNT') >DEPOSIT VALUE</td>");//Modified by Dineth on 2009-01-19 
			out.println("<td width='10%' class='txt_report_column' >DEPOSIT USER</td>");//Added by Dineth on 2009-01-19
			out.println("<td width='10%' class='txt_report_column' >COMPANY BRANCH</td>");//Added by Dineth on 2009-01-19
			out.println("<td width='10%' class='txt_report_column' >DEPOSIT SLIP</td>");//Added by Dineth on 2009-01-19
			out.println("</tr >"); 
			
      
			//if(m_from_date.equals("") && m_to_date.equals("")){//Commented by Dineth on 2009-01-20
			if(!m_deposite_no.equals("")){
			pstmt = conn.prepareStatement("SELECT "+
			//"B.RECEIPT_NO RECEIPT_NO,    "+
			"A.DIPOSIT_NO DIPOSIT_NO,   "+
			"TO_CHAR(A.DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE,  "+
			"A.REFERENCE REFERENCE,   "+
			"A.ACC_NO ACCOUNT_NO,    "+
			"A.BRANCH_CODE BRANCH_CODE,    "+
			"decode(A.STATUS,'Y','Yes') STATUS,     "+
			""+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE), "+
			" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(B.ENT_USER),'-'), "+//Added by Dineth on 2009-01-19
      " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(B.ENT_USER)),'-'), "+//Added by Dineth on 2009-01-19
			"NVL(SUM(B.AMOUNT),0) AMOUNT    "+
			"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT A,   "+
			""+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B   "+
			"WHERE A.DIPOSIT_NO=B.DIPOSIT_NO "+
			//"AND UPPER(B.RECEIPT_NO) like UPPER('"+m_receipt_no+"%') "+//Commented by Dineth on 2009-01-19
			"AND UPPER(a.DIPOSIT_NO) like UPPER('"+m_deposite_no+"%') "+
			" GROUP BY A.DIPOSIT_NO,A.DIPOSIT_DATE,A.REFERENCE,A.ACC_NO,A.BRANCH_CODE,A.STATUS,B.ENT_USER "+//Added by Dineth on 2009-01-20
			"ORDER BY "+m_order_by+" "+m_sort_by+" ");
			
			}else {
			
      pstmt = conn.prepareStatement("SELECT "+
			//"B.RECEIPT_NO RECEIPT_NO,    "+
			"A.DIPOSIT_NO DIPOSIT_NO,   "+
			"TO_CHAR(A.DIPOSIT_DATE,'DD-MM-YYYY') DIPOSIT_DATE,  "+
			"A.REFERENCE REFERENCE,   "+
			"A.ACC_NO ACCOUNT_NO,    "+
			"A.BRANCH_CODE BRANCH_CODE,    "+
			"decode(A.STATUS,'Y','Yes') STATUS,     "+
			""+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE), "+
			" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(B.ENT_USER),'-'), "+//Added by Dineth on 2009-01-19
      " NVL("+m_schema_name+".AF_CO_GET_LOCATION_DESC("+m_schema_name+".AF_CO_GET_USER_LOCATION(B.ENT_USER)),'-'), "+//Added by Dineth on 2009-01-19
			"NVL(SUM(B.AMOUNT),0) AMOUNT    "+
			"FROM "+m_schema_name+".AF_CO_PRO_DIPOSIT A,   "+
			""+m_schema_name+".AF_CO_PRO_DIPOSIT_DETAILS B   "+
			"WHERE A.DIPOSIT_NO=B.DIPOSIT_NO "+
			//"AND UPPER(B.RECEIPT_NO) like UPPER('"+m_receipt_no+"%') "+//Commented by Dineth on 2009-01-19
			//"AND UPPER(a.DIPOSIT_NO) like UPPER('"+m_deposite_no+"%') "+//Commented by Dineth on 2009-01-20
			"AND TO_DATE(TO_CHAR(A.DIPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
      "AND TO_DATE(TO_CHAR(A.DIPOSIT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" GROUP BY A.DIPOSIT_NO,A.DIPOSIT_DATE,A.REFERENCE,A.ACC_NO,A.BRANCH_CODE,A.STATUS,B.ENT_USER "+//Added by Dineth on 2009-01-20
			"ORDER BY "+m_order_by+" "+m_sort_by+" ");			
			
			
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

					//	out.println("<tr >"); 
						//out.println("<TD class='txt_report_data' align='right' style= cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");//Commented by Dineth on 2009-01-19
						out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_deposit_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></TD>"); 
						out.println("<TD class='txt_report_data' align='left'>"+rs.getString(2)+"</TD>");
						//out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");//Commented by Dineth on 2009-01-19
						//out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");//Commented by Dineth on 2009-01-19
						out.println("<TD class='txt_report_data' align='left'>"+rs.getString(7)+"</TD>");
						//out.println("<TD class='txt_report_data' align='right'>"+rs.getString(7)+"</TD>");//Commented by Dineth on 2009-01-19
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(10))+"</TD>");
						out.println("<TD class='txt_report_data' align='left'>"+rs.getString(8)+"</TD>");//Added by Dineth on 2009-01-19 ent user
						out.println("<TD class='txt_report_data' align='left'>"+rs.getString(9)+"</TD>");//Added by Dineth on 2009-01-19 user location
						out.println("<TD class='txt_report_data' align='center'><INPUT TYPE='button' CLASS='but_input' NAME=\"but_deposit_slip"+j+"\" VALUE='Slip' ONCLICK=\"show_slip('"+rs.getString(1)+"')\"></TD>");//Added by Dineth on 2009-01-19 user location
						out.println("</tr >"); 
						more=rs.next(); 
						j=j+1;
			} 

			out.println("<tr class=tr_input>");
			out.println("<td colspan=6 align=right></td>");
			out.println("<td colspan=7 align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_out_value();'></td>");

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


