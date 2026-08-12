
//--
//SCREEN NAME:DISPLAY RECEIPT REALIZATION REPORT
//CREATED BY:
//DATE/TIME:
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_MISF_display_receipt_realization_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
	//Modified by Mahela on 10-04-2007

public class LAKDL_AF_MISF_display_receipt_realization_report1 extends javax.servlet.http.HttpServlet { 

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

			String m_fschema_name=m_sn_methods.client_name.trim();
			
			//String m_receipt_no=req.getParameter("receipt_no");
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date"); 
      //String m_rel_type = req.getParameter("realisation");
			String m_branch  = req.getParameter("branch");
						
			String m_order_by   = "CLIENT_CODE";	
			String m_sort_by = "ASC";
							
			if(req.getParameter("order_by")!=null && req.getParameter("sort_by")!=null){
			    m_order_by = req.getParameter("order_by");
			    m_sort_by = req.getParameter("sort_by");

			}


			if(m_screen_type.equals("MAIN")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE> Return Realization Report</TITLE>"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_receipt_realization_report1?chksql=MAIN&from_date="+m_from_date+"&branch="+m_branch+"&to_date="+m_to_date+"&order_by=\"+m_sort_col+\"&sort_by=\"+m_order_by_type+\" \";");
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
			out.println("<tr class='pdn_txtpos2'><td width='60%' align='left' style='height: 18px'>  Return Realization Report</td>");
			out.println("<td width='40%' align='right'><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='6%' class='txt_report_column'>REC NO</td>");
			out.println("<td width='5%' class='txt_report_column' style='{cursor:hand}' onClick=\"sort_data('CLIENT_CODE')\">CLIENT CODE</td>");
			out.println("<td width='6%' class='txt_report_column'>RETURN NO</td>"); 
			out.println("<td width='6%' class='txt_report_column'>DEPOSIT NO</td>"); 
			//out.println("<td width='5%' class='txt_report_column'>SETTLE MODE</td>"); 
			out.println("<td width='6%' class='txt_report_column'>PAYER BRANCH CODE</td>");
			out.println("<td width='8%' class='txt_report_column'>PAYER BRANCH NAME</td>"); 
			out.println("<td width='6%' class='txt_report_column'>PAYER ACC NO</td>"); 
			 
			out.println("<td width='6%' class='txt_report_column' align='right'>REC AMOUNT</td>"); 
			out.println("<td width='6%' class='txt_report_column'>BRANCH CODE</td>");
			out.println("<td width='8%' class='txt_report_column'>BRANCH NAME</td>");
			out.println("<td width='6%' class='txt_report_column'>ACC NO</td>"); 
			out.println("<td width='6%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='5%' class='txt_report_column'>EFF REALISED/RETURN DATE</td>"); 
			out.println("<td width='5%' class='txt_report_column'>RETURN ENTRY DATE</td>");
			out.println("<td width='6%' class='txt_report_column' align='right'>RETURN AMOUNT</td>");
			out.println("<td width='5%' class='txt_report_column'>COMMENTS</td>");
			out.println("<td width='5%' class='txt_report_column'>CASHIER LOCATION</td>");
			out.println("</tr >"); 
			
			
			if(m_branch.trim().equals("")){
			
			pstmt = conn.prepareStatement("SELECT  "+
			"A.REC_NO REC_NO, "+//1
			"A.SETTLE_MODE SETTLE_MODE,  "+//2
			"A.PAYER_BRANCH_CODE PAYER_BRANCH_CODE,  "+//3
			"A.PAYER_ACC_NO PAYER_ACC_NO,   "+//4
			"A.CLIENT_CODE CLIENT_CODE,   "+//5
			"A.ENTRY_TYPE ENTRY_TYPE,    "+//6
			"nvl(A.REC_AMOUNT,0) REC_AMOUNT,   "+//7
			"A.BRANCH_CODE BRANCH_CODE,  "+//8
			"A.ACC_NO ACC_NO,  "+//9
			"DECODE(A.STATUS,'B','Bank','C','Cancel','E','Entered','REC','Receipt','RET','Return'),"+//10
			"to_char(A.EFF_VALDATE,'dd-mm-yyyy') EFF_VALDATE,   "+//11
			"to_char(A.REALISED_DATE,'dd-mm-yyyy') REALISED_DATE ,   "+//12
			"A.CHEQUE_NO CHEQUE_NO,   "+//13
			"to_char(A.CHEQUE_DATE,'dd-mm-yyyy'),   "+//14
			"to_char(A.BANK_DATE,'dd-mm-yyyy') BANK_DATE,  "+//15
			"A.RETURN_AMOUNT RETURN_AMOUNT,   "+//16
			"B.RETURN_NO RETURN_NO,  "+//17
			"B.DIPOSIT_NO DIPOSIT_NO,   "+//18
			"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+	//19
			"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-'), "+  //20
			" NVL(COMMENTS,'-'), "+//21 Added by Dineth on 26-06-2009
			" NVL(TO_CHAR(b.ent_date,'DD-MM-YYYY'),'-'), "+//22 Added by Dineth on 26-06-2009
			" NVL(A.ENT_USER_LOCATION,'-') "+//23 Added by Dineth on 02-07-2009
			"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
			""+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B WHERE A.REC_NO=B.RECEIPT_NO "+
			//" and upper(A.REC_NO) like upper('"+m_receipt_no+"%') "+
			//" AND A.STATUS LIKE UPPER('"+m_rel_type+"%') ");  //--- Added by Chandana on 08/05/2007  ---------//
      " AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" ORDER BY "+m_order_by+" "+m_sort_by+" ");
			}else{
			
		  pstmt = conn.prepareStatement("SELECT  "+
			"A.REC_NO REC_NO, "+//1
			"A.SETTLE_MODE SETTLE_MODE,  "+//2
			"A.PAYER_BRANCH_CODE PAYER_BRANCH_CODE,  "+//3
			"A.PAYER_ACC_NO PAYER_ACC_NO,   "+//4
			"A.CLIENT_CODE CLIENT_CODE,   "+//5
			"A.ENTRY_TYPE ENTRY_TYPE,    "+//6
			"nvl(A.REC_AMOUNT,0) REC_AMOUNT,   "+//7
			"A.BRANCH_CODE BRANCH_CODE,  "+//8
			"A.ACC_NO ACC_NO,  "+//9
			"DECODE(A.STATUS,'B','Bank','C','Cancel','E','Entered','REC','Receipt','RET','Return'),"+//10
			"to_char(A.EFF_VALDATE,'dd-mm-yyyy') EFF_VALDATE,   "+//11
			"to_char(A.REALISED_DATE,'dd-mm-yyyy') REALISED_DATE ,   "+//12
			"A.CHEQUE_NO CHEQUE_NO,   "+//13
			"to_char(A.CHEQUE_DATE,'dd-mm-yyyy'),   "+//14
			"to_char(A.BANK_DATE,'dd-mm-yyyy') BANK_DATE,  "+//15
			"A.RETURN_AMOUNT RETURN_AMOUNT,   "+//16
			"B.RETURN_NO RETURN_NO,  "+//17
			"B.DIPOSIT_NO DIPOSIT_NO,   "+//18 
			"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.PAYER_BRANCH_CODE),'-'), "+	//19
			"NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(A.BRANCH_CODE),'-'), "+  //20
			" NVL(COMMENTS,'-'), "+//21 Added by Dineth on 26-06-2009
			" NVL(TO_CHAR(b.ent_date,'DD-MM-YYYY'),'-'), "+//22 Added by Dineth on 26-06-2009
			" NVL(A.ENT_USER_LOCATION,'-') "+//23 Added by Dineth on 02-07-2009
			"FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
			""+m_schema_name+".AF_CO_PRO_RETURN_DETAILS B WHERE A.REC_NO=B.RECEIPT_NO "+
			" AND A.ENT_USER_LOCATION='"+m_branch+"' "+
			//" and upper(A.REC_NO) like upper('"+m_receipt_no+"%') "+
			//" AND A.STATUS LIKE UPPER('"+m_rel_type+"%') "+  //--- Added by Chandana on 08/05/2007  ---------//
			//"AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
      //"AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
			" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+ 
			" AND TO_DATE(TO_CHAR(A.REALISED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
			" ORDER BY "+m_order_by+" "+m_sort_by+" ");   		
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

						out.println("<TD class='txt_report_data'  style= cursor:hand;cursor-color:blue onclick=show_settle_receipt_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
						out.println("<TD class='txt_report_data' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(5)+"')><U>"+rs.getString(5)+"</U></TD>");//Changed the position by Dineth on 26-06-2009
						out.println("<TD class='txt_report_data'  style= cursor:hand;cursor-color:blue onclick=show_return_detail_drill('"+rs.getString(17)+"')><u>"+rs.getString(17)+"</u></TD>");
						out.println("<TD class='txt_report_data'  style= cursor:hand;cursor-color:blue onclick=show_deposit_drill('"+rs.getString(18)+"')><u>"+rs.getString(18)+"</u></TD>");
						//out.println("<TD class='txt_report_data' >"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(19)+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(4)+"</TD>");
						
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(7))+"</TD>");
						if(rs.getString(8)!=null){
								
							out.println("<TD class='txt_report_data' >"+rs.getString(8)+"</TD>");
						}		
						if(rs.getString(8)==null){
								
							out.println("<TD class='txt_report_data' ></TD>");
						}	
						
						out.println("<TD class='txt_report_data' >"+rs.getString(20)+"</TD>");
						
						
						if(rs.getString(8)!=null){

						out.println("<TD class='txt_report_data' >"+rs.getString(9)+"</TD>");
					  }
						if(rs.getString(8)==null){

						out.println("<TD class='txt_report_data' ></TD>");
					  }
						
						out.println("<TD class='txt_report_data' >"+rs.getString(10)+"</TD>");
						
							
					  if(rs.getString(12)!=null){
						out.println("<TD class='txt_report_data' >"+rs.getString(12)+"</TD>");
					  }
					
						if(rs.getString(12)==null ){
						out.println("<TD class='txt_report_data' align='right'></TD>");
					  }
			      out.println("<TD class='txt_report_data' >"+rs.getString(22)+"</TD>");//Added by Dineth on 26-06-2009
					
						out.println("<TD class='txt_report_data' align='right'>"+nf.format(rs.getDouble(16))+"</TD>");
						out.println("<TD class='txt_report_data' >"+rs.getString(21)+"</TD>");//Added by Dineth on 26-06-2009
			      out.println("<TD class='txt_report_data' >"+rs.getString(23)+"</TD>");//Added by Dineth on 02-07-2009
			      out.println("</tr >"); 
						more=rs.next(); 
						j=j+1;
						
			} 
			
			
			

			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
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


