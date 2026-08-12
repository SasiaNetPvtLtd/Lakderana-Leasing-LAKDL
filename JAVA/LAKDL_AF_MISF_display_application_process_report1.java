
//--
//SCREEN NAME:APPLICATION PROCESS REPORT
//CREATED BY:delanjali 
//DATE/TIME:
//NOTES:
//URL:http://www.MULTI-netasset.lk:/myserver/servlet/LAKDL_AF_MISF_display_application_process_report1?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_display_application_process_report1 extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs,rs1;
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
			stmt1 = conn.createStatement();

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
			
			//Added by Dineth on 2008-12-05
			out.println(" function show_termination_approval(term_no){");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_application_process_report1?chksql=SHOW_TERM_APPROVAL&TERM_NO=\"+term_no+\"\";");
			out.println("window.open(m_url,'displayWindow4','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');");
			out.println("}");
			//End by Dineth on 2008-12-05

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
			out.println("<td colspan=11 align=right></td>");
			out.println("<td colspan=12 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_out_value();'></td>");
			out.println("</tr>");

			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column'     >APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >CLIENT NAME</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >FINANCE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'     >FINANCE AMOUNT</td>"); ///// manjula multi modifications
			out.println("<td width='10%' class='txt_report_column'     >RATE</td>"); ///// manjula multi modifications
			out.println("<td width='10%' class='txt_report_column'     >APPLICATION STATUS</td>");
			out.println("<td width='5%' class='txt_report_column'     >START DATE</td>");///// manjula multi modifications
			out.println("<td width='5%' class='txt_report_column'     >END DATE</td>");///// manjula multi modifications
			out.println("<td width='10%' class='txt_report_column'     >TRANSACTION TYPE </td>"); 
			out.println("<td width='5%' class='txt_report_column'     >TRANSACTION SUB TYPE </td>");
			out.println("<td width='5%' class='txt_report_column'     >DIVISION CODE </td>"); 
			//out.println("<td width='10%' class='txt_report_column'     >FD NUMBER </td>"); 
			out.println("<td width='5%' class='txt_report_column'     >ENTER DATE</td>"); 
			out.println("<td width='5%' class='txt_report_column'     >ENTER USER</td>"); 
	 		out.println("<td width='5%' class='txt_report_column'     >Asset</td>"); 
			out.println("<td width='5%' class='txt_report_column'     >Total net receivables</td>");// Total net receivables
			out.println("<td width='5%' class='txt_report_column'     >Termination Process</td>");//Added by Dineth on 2008-12-05
	 		out.println("</tr >"); 
			
			
		if(m_app_no.equals(" ") && !m_client_no.equals(" ")){
			
			
    pstmt = conn.prepareStatement
	//out.println
		("SELECT APPLICATION_NO, "+
		" CLIENT_CODE, "+
    " "+m_schema_name+".af_co_get_client_name(CLIENT_CODE), "+
		" NVL(FINANCE_NO,'-'), "+
		 // " INITCAP(DECODE(APPLICATION_STATUS,'ENT_CON','APPLICATION COMPLETED','TERMI','TERMINATED','VERIFY1','CREDIT VERIFICATION','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No',APPLICATION_STATUS)), "+  /* comment and add by malik on 26-8-2008*/  /* comment and add by malik on 26-8-2008*/
		//"INITCAP(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','TERMI','Terminated','VERIFY1','Credit Verification','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No',APPLICATION_STATUS)), "+
		//Commented by Dineth on 29-01-2009
		//" INITCAP(DECODE(APPLICATION_STATUS,'ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO),'ACTIVATED','ACTIVATED',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'ENTERED','ENTERED','ENT_CON','COMPLETED','VERIFY1','CREDIT VERIFICATION','V-APP','CREDIT SCORE APPROVAL','VERIFY-M','CREDIT APPROVAL 1','VERIFY2','CREDIT APPROVAL 2','VERIFYL','ENTERED LEASING NO')) , "+//add by malik on 7-10-2008
		" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected','NORM_TERMI','Normal Termination'),'-'), "+//Added by Dineth on 29-01-2009
		" TRANSACTION_TYPE, "+
    " NVL(DIVISION_CODE,'-'), "+
		" ENT_USER, "+
    " TO_CHAR(ENT_DATE,'DD-MM-YYYY') ,"+  //9
		" NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY') ,"+ //10
		" (SELECT TO_CHAR(MAX(RENTAL_DATE),'DD-MM-YYYY')     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER(APPLICATION_NO))  , "+ //10
		" NVL("+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(APPLICATION_NO),0), "+ //12
		" NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_CATEGORY(APPLICATION_NO),'-'),"+// 13 MANJULA ON 09-07-2008
		//" NVL("+m_schema_name+".AF_FD_RETURN_FD_LOANS(APPLICATION_NO),'-'),"+// 14 MANJULA ON 09-07-2008
		" NVL("+m_schema_name+".AF_CO_RETURN_RATE(APPLICATION_NO),0),"+// 15 MANJULA ON 09-07-2008
		" "+m_schema_name+".AF_CO_GET_ASSET_DESC(APPLICATION_NO), "+
		" "+m_schema_name+".AF_CO_GET_NET_INSTALL_AMT(APPLICATION_NO),  "+// 17 MANJULA ON 09-07-2008
    " NVL(TERMINATION_NO,'-') "+//17 Added by Dineth on 2008-12-05
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS    "+ 
		" WHERE CLIENT_CODE like UPPER('"+m_client_no+"%') "+
		" ORDER BY APPLICATION_NO DESC ");
			
			
			}else if(!m_app_no.equals(" ") && !m_client_no.equals(" ")){
		 
		pstmt = conn.prepareStatement
		//out.println
		("SELECT APPLICATION_NO, "+
		" CLIENT_CODE, "+
    " "+m_schema_name+".af_co_get_client_name(CLIENT_CODE), "+
		" NVL(FINANCE_NO,'-'), "+
   // " INITCAP(DECODE(APPLICATION_STATUS,'ENT_CON','APPLICATION COMPLETED','TERMI','TERMINATED','VERIFY1','CREDIT VERIFICATION','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','PURCHASE ORDER LEVEL',APPLICATION_STATUS)), "+//comment and add by malik on 26-8-2008
		//Commented by Dineth on 29-01-2009
		//"INITCAP(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','TERMI','Terminated','VERIFY1','Credit Verification','VERIFY-M','Credit Approval 1','VERIFY2','Credit Approval 2','VERIFYL','Entered Leasing No',APPLICATION_STATUS)), "+
	//	" NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED',DECODE("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO),'ACTIVATED','Activated',"+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO)),'CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected','NORM_TERMI','Normal Termination'),'-'), "+//Added by Dineth on 29-01-2009
    	" "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO), "+//Added By Sandun on 02-02-2009
		" TRANSACTION_TYPE, "+
		" NVL(DIVISION_CODE,'-'), "+
		" ENT_USER, "+
    " TO_CHAR(ENT_DATE,'DD-MM-YYYY') ,"+
		" NVL(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'-') ,"+ //9
		" NVL((SELECT TO_CHAR(MAX(RENTAL_DATE),'DD-MM-YYYY')     FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE UPPER(APPLICATION_NO) =UPPER(APPLICATION_NO)),'-'),   "+ //10
		" NVL("+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(APPLICATION_NO),0), "+ //12
		" NVL("+m_schema_name+".AF_CO_GET_ITEM_SUB_CATEGORY(APPLICATION_NO),'-'),"+// MANJULA ON 09-07-2008
		//" NVL("+m_schema_name+".AF_FD_RETURN_FD_LOANS(APPLICATION_NO),'-'),"+// 14 MANJULA ON 09-07-2008
		" NVL("+m_schema_name+".AF_CO_RETURN_RATE(APPLICATION_NO),0),"+// 15 MANJULA ON 09-07-2008
		" "+m_schema_name+".AF_CO_GET_ASSET_DESC(APPLICATION_NO), "+
		" "+m_schema_name+".AF_CO_GET_NET_INSTALL_AMT(APPLICATION_NO),  "+
		" NVL(TERMINATION_NO,'-') "+//17 Added by Dineth on 2008-12-05
		
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
		" WHERE CLIENT_CODE like UPPER('"+m_client_no+"%') AND "+
		" UPPER(APPLICATION_NO) like UPPER('"+m_app_no+"%') "+
		" ORDER BY APPLICATION_NO DESC ");
			
		  
			
			}
			
			rs=pstmt.executeQuery(); 
			int j=0;
			String m_term_no="";
			boolean more=rs.next();
			while(more){
								if(j>0 && j%2==1){
		      out.println("<tr class=tr_input1 >");
					}
					else{
					
		      out.println("<tr class=tr_input >");
					}

						out.println("<TD class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='left' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(2)+"') ><U>"+rs.getString(3)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(4)+"') ><U>"+rs.getString(4)+"</U></TD>");
						out.println("<TD class='txt_report_data' align='center'>"+nf.format(rs.getDouble(12))+"</TD>"); //finance amount
						
						out.println("<TD class='txt_report_data' align='center'>"+nf.format(rs.getDouble(14))+"</TD>"); //rate
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(10)+"</TD>"); //start date
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(11)+"</TD>"); //end date
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(6)+"</TD>");
						
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(13)+"</TD>"); // MANJULA ON 09-07-2008
						
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(7)+"</TD>");
						
						//out.println("<TD class='txt_report_data' align='center'>"+rs.getString(14)+"</TD>");// MANJULA ON 09-07-2008
						
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(9)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(8)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(15)+"</TD>");
						out.println("<TD class='txt_report_data' align='center'>"+rs.getString(16)+"</TD>");
						m_term_no=rs.getString(17);
						if(m_term_no.trim().equals("-")){
						out.println("<TD class='txt_report_data' align='center'>"+m_term_no+"</TD>");
						}else{
						out.println("<TD class='txt_report_data' align='center' style= cursor:hand;cursor-color:blue onclick=show_termination_approval('"+m_term_no+"') ><U>"+m_term_no+"</U></TD>");
						}
						out.println("</tr >"); 
						j=j+1;
						more=rs.next(); 
			} 
			out.println("<tr class=tr_input>");
			out.println("<td colspan=11 align=right></td>");
			out.println("<td colspan=12 align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_out_value();'></td>");

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
			//Added by Dineth on 2008-12-05
			if(m_screen_type.equals("SHOW_TERM_APPROVAL")){
			      String m_term_no_1= req.getParameter("TERM_NO");
						out.println("<HTML><HEAD><TITLE>Termination Approval Details -"+m_term_no_1+"</TITLE></HEAD>");
						out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
						out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
						out.println("<FORM NAME='Form1' method='post'>");
						out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
						out.println("<TR><TD><CENTER><B>Termination Approval Details - Termination No: "+m_term_no_1+"</B></TD></TR>");
						out.println("</TABLE>");
						out.println("<BR><BR><BR><BR><BR><BR>");
		
			
						// commented by udara 12-08-2016
						/*
					  rs=stmt.executeQuery(" SELECT NVL(ENT_USER,'-'),NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY:hh:mi:ss'),'-'), "+
						                     " NVL(APPROVED1_BY,'-'),NVL(TO_CHAR(APPROVED1_DATE,'DD-MM-YYYY:hh:mi:ss'),'-'), "+
																 " NVL(APPROVED2_BY,'-'),NVL(TO_CHAR(APPROVED2_DATE,'DD-MM-YYYY:hh:mi:ss'),'-'), "+
																 " NVL(MOD_USER,'-'),NVL(TO_CHAR(MOD_DATE,'DD-MM-YYYY:hh:mi:ss'),'-'), "+
																 " NVL(REMARKS,'-') "+	
																 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
																 " WHERE TERMINATION_NO='"+m_term_no_1+"'");
						*/
						
						// added by udara 12-08-2016
						rs=stmt.executeQuery(" SELECT NVL(ENT_USER,'-'),NVL(TO_CHAR(ENT_DATE,'DD-MM-YYYY HH:MI:SS'),'-'), "+
						                     " NVL(APPROVED1_BY,'-'),NVL(TO_CHAR(APPROVED1_DATE,'DD-MM-YYYY HH:MI:SS'),'-'), "+
																 " NVL(APPROVED2_BY,'-'),NVL(TO_CHAR(APPROVED2_DATE,'DD-MM-YYYY HH:MI:SS'),'-'), "+
																 " NVL(MOD_USER,'-'),NVL(TO_CHAR(MOD_DATE,'DD-MM-YYYY HH:MI:SS'),'-'), "+
																 " NVL(REMARKS,'-') "+	
																 " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION "+
																 " WHERE TERMINATION_NO='"+m_term_no_1+"' "+
																 " ORDER BY ENT_DATE DESC "+
																 " ");
						// end by udara 12-08-2016
						
						
						
						boolean more=rs.next();
				/*		if(more){
						out.println("<table align='center' border='1' bordercolor='lightgrey' width='60%' class='table' cellspacing='0' >");
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Entered by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(1)+"</td>");
						out.println("<td width='20%' class=div_input><b>Entered Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("</tr>");
			
						//Added by Dineth on 2009-02-05
						if(rs.getString(3).equals("-") && rs.getString(5).equals("-")){
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 1 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
			
			
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 2 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
			
						out.println("<tr>");
				    
				
				    
						out.println("<td width='20%' class=div_input><b>Processed by</b></td>");
						out.println("<td width='10%' class=div_input>-</td>");
						out.println("<td width='20%' class=div_input><b>Processed Date</b></td>");
						out.println("<td width='10%' class=div_input>-</td>");
						out.println("</tr>");
			 
						
						}else{
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 1 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(3)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(4)+"</td>");
						out.println("</tr>");
			
			
						out.println("<tr>");
				
						out.println("<td width='20%' class=div_input><b>Approved 2 by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
						out.println("<td width='20%' class=div_input><b>Approval Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(6)+"</td>");
						out.println("</tr>");
						
						out.println("<tr>");
				    
				
				    
						out.println("<td width='20%' class=div_input><b>Processed by</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(7)+"</td>");
						out.println("<td width='20%' class=div_input><b>Processed Date</b></td>");
						out.println("<td width='10%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("</tr>");
			      }
						//End by Dineth on 2009-02-05
						out.println("</table>");
						}
						*/ //commented by kanchana on 2016-06-16
		//*********************************************************Added by kanchana for issue no 20327*******************************
		  if(more){
						
						//out.println("<BR><BR><BR><BR><BR><BR>");
						out.println("<table align='left' border='1' bordercolor='lightgrey' width='100%' class='table' cellspacing='0' >");
						out.println("<tr bgcolor='silver'>");
						//out.println("<td width='5%'>No.</td>");					
						//out.println("<td width='12%' class=div_input>Termination No</td>");
						out.println("<td width='15%' class=div_input><b>Level</b></td>");
						out.println("<td width='10%' class=div_input><b>Status</b></td>");
						out.println("<td width='35%' class=div_input><b>Remarks</b></td>");
						out.println("<td width='15%' class=div_input><b>Entered Date</b></td>");
						out.println("<td width='15%' class=div_input><b>Entered User</b></td>");								
						out.println("</tr>");
						
						out.println("<tr >");
						//out.println("<td width='5%'>"+count+"</td>"); 
						//out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='12%' class=div_input>Entered</td>");
						out.println("<td width='10%' class=div_input>Entered</td>");
						out.println("<td width='35%' class=div_input>"+rs.getString(9)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(2)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");	
						out.println("</tr>");

						// commented by udara 12-08-2016		
						/*
								rs1=stmt1.executeQuery(" SELECT  A.TERMINATION_NO, "+//1
													        " A.STAGE, "+//2
													        " NVL(A.REMARKS,'-'), "+//3
													        " INITCAP(A.STATUS), "+//4
													        " A.ENT_DATE, "+ //5
													        " A.ENT_USER, "+//6
																	" DECODE(A.STAGE,'APPROVE1','Approve Level 1','APPROVE2','Approve Level 2'), "+ //7
																	" A.FINANCE_NO "+
													  			" FROM "+m_schema_name+".AF_CO_PRO_TERMI_APP_REMARKS A "+
																	" WHERE A.TERMINATION_NO ='"+m_term_no_1+"' ");
								*/
						
						// added by udara 12-08-2016
						rs1=stmt1.executeQuery(" SELECT  A.TERMINATION_NO, "+//1
													        " A.STAGE, "+//2
													        " NVL(A.REMARKS,'-'), "+//3
													        " INITCAP(A.STATUS), "+//4
													        " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY HH:MI:SS'), "+ //5
													        " A.ENT_USER, "+//6
																	" DECODE(A.STAGE,'APPROVE1','Approve Level 1','APPROVE2','Approve Level 2'), "+ //7
																	" A.FINANCE_NO "+
													  			" FROM "+m_schema_name+".AF_CO_PRO_TERMI_APP_REMARKS A "+
																	" WHERE A.TERMINATION_NO ='"+m_term_no_1+"' "+
																	" ORDER BY A.ENT_DATE "+
																	" ");
						
						
								
							boolean more1=rs1.next();
							int count=1;
							while(more1){
							out.println("<tr >");
							//out.println("<td width='5%'>"+count+"</td>"); 
							//out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
							out.println("<td width='12%' class=div_input>"+rs1.getString(7)+"</td>");
							out.println("<td width='10%' class=div_input>"+rs1.getString(4)+"</td>");
							out.println("<td width='35%' class=div_input>"+rs1.getString(3)+"</td>");
							out.println("<td width='15%' class=div_input>"+rs1.getString(5)+"</td>");
							out.println("<td width='15%' class=div_input>"+rs1.getString(6)+"</td>");	
							out.println("</tr>");
							more1 =rs1.next();
							count = count+1;
							}	
				
							if(rs.getString(3).equals("-") && rs.getString(5).equals("-")){
						
						out.println("<tr >");
						//out.println("<td width='5%'>"+count+"</td>"); 
						//out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='12%' class=div_input>Processed</td>");
						out.println("<td width='10%' class=div_input>Processed</td>");
						out.println("<td width='35%' class=div_input>-</td>");
						out.println("<td width='15%' class=div_input>-</td>");
						out.println("<td width='15%' class=div_input>-</td>");	
						out.println("</tr>");;
			 
						
						}else{						
						
						out.println("<tr >");
						//out.println("<td width='5%'>"+count+"</td>"); 
						//out.println("<td width='15%' class=div_input>"+rs1.getString(1)+"</td>");
						out.println("<td width='12%' class=div_input>Processed</td>");
						out.println("<td width='10%' class=div_input>Processed</td>");
						out.println("<td width='35%' class=div_input>"+rs.getString(9)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(8)+"</td>");
						out.println("<td width='15%' class=div_input>"+rs.getString(7)+"</td>");	
						out.println("</tr>");
			      		}
				
				
						}//end of if
		
		//*********************************************************enddede by Kanchana************************************************* 
						out.println("</FORM>");
						out.println("</BODY></HTML>");
			}
			//End by Dineth on 2008-12-05
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


