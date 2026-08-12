//Created by AH
//Payment Status Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MK_PAyment_Status_Rpt_Data extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1,nf2;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			nf2 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf2.setMinimumFractionDigits(2);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			stmt1= conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("show_detail")){
			  String m_app_no1 = req.getParameter("app_no");
				rs1  = stmt1.executeQuery("SELECT APPLICATION_NO,"+
																	"     NVL(DECODE(STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																	"     TO_CHAR(ENT_DATE,'DD-MM-YYYY hh:mm:ss'),"+
																	"			NVL("+m_schema_name+".AF_CO_GET_USER_NAME(ENT_USER),'-') "+
																	"     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL       "+
																	"     WHERE APPLICATION_NO='"+m_app_no1+"'"+
																	"			UNION ALL "+
																	"SELECT APPLICATION_NO,"+
																	"     NVL(DECODE(APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																	"     TO_CHAR(MOD_DATE,'DD-MM-YYYY hh:mm:ss'),"+
																	"			NVL("+m_schema_name+".AF_CO_GET_USER_NAME(MOD_USER),'-') "+
																	"     FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS       "+
																	"     WHERE APPLICATION_NO='"+m_app_no1+"'"+
																	"     AND APPLICATION_STATUS='ACTIVATED'");
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<script language=\"javascript\">");
				out.println("   function load_roll_value(m_val){"); 
				out.println("      if(m_val==''){");
				out.println("          help_box.innerHTML=\"Marketing - Payment Status Report \";"); 
			  out.println("      }else{");
				out.println("          help_box.innerHTML=\"Marketing - Payment Status Report - \"+m_val;"); 
			  out.println("      }");
				out.println("   }");
				
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("</script>");
				out.println("</HEAD>");
				out.println("<BODY>");
				/*out.println(m_app_no1);
				out.println("<table class=table border='0' width='100%' >");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing - Payment Status Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				*/
				
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing - Payment Status Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='70%' >");
					/*out.println("<tr class=tr_input>");
				  out.println("<td colspan=3 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onMouseOut='load_roll_value(\"\");'></td>");
          out.println("</tr>");*/
					
				out.println("<tr class=pdn_txtpos2 align='left'>");
					out.println("<td  width='20%'  >Application Status</td>");
          out.println("<td  width='30%'  >Status Changed Date</td>");
					out.println("<td  width='20%'  >Status Changed User</td>");
					out.println("</tr>");
					boolean more=rs1.next();
					while(more){
					out.println("<tr class=tr_input >");
					out.println("<td width='20%'  align='left'>"+rs1.getString(2) +"</td>");
          out.println("<td width='30%'  align='left'>"+rs1.getString(3) +"</td>");
          out.println("<td width='20%'  align='left'>"+rs1.getString(4) +"</td>");
					out.println("</tr>");
					more=rs1.next();
					}
					/*		out.println("<tr class=tr_input>");
				  out.println("<td colspan=3 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=right><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(\"\");'></td>");
 */
          out.println("</table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</BODY>");
				out.println("</HTML>");
			}
					else if(m_chksql.trim().equals("main_page")){
							String m_from_date=req.getParameter("from_date");
							String m_to_date=req.getParameter("to_date");
							String m_app_status = req.getParameter("app_status");
							String payment_catogary = req.getParameter("pay_cat");//Added By Sandun on 09/12/2008
							String sql1 = "";
							// Commented by Dineth on 05-08-2008
							//String m_type= req.getParameter("type");
							//String m_pay_no=req.getParameter("pay_no");

	
						  String m_sort_column   = "PAY_NO";	
							String m_order_by_type = "ASC";
							
							if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
							}
					
			
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\"Marketing - Payment Status Report \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\"Marketing - Payment Status Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");
				

			  /*out.println("function load_data(m_app_no,m_app_sts) {");
				out.println(" if(m_app_sts=='IP') { ");
	      out.println("	  m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Application_Process?chksql=main_page&CLOSE=Y&application_no=\"+m_app_no;"); 
			  out.println("   window.open(m_url,'displayWindowap','left=0,top=33,width=950,height=650,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("  }");
				out.println(" else { ");
			  out.println("   alert(m_app_no+'  is complete.');"); 
				out.println("  }");
				out.println("}");*/
			  //Added by Dineth on 2009-02-10
			  out.println("function Payment_breakup(val) {"); 
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=\"+val+\"&print=TRUE;\"");
				
				out.println("window.open(m_url,'popupwin','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=0')");
				out.println("} "); 			
				//End by Dineth on 2009-02-10			
			
			  out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
				//out.println("alert(m_sort_col);");
	      out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=main_page&app_status="+m_app_status+"&pay_cat="+payment_catogary+"&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
			  //===========================Added by Dineth on 06-08-2008==============================
				out.println(" function load_data_frame(val){  ");
				out.println("m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_PAyment_Status_Rpt_Data?chksql=show_detail&app_no=\"+val+\"\";");
				out.println("popupwin=window.open(m_url,'displayWindow2','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");				
				out.println("  } ");
				//===========================End by Dineth on 06-08-2008================================
        out.println("</Script>");
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
        out.println("<input type=hidden name=\"ROW_ID\" ></td>");
				
                  
				out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
				out.println("<tr>");
				
				out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
				out.println("<td class=\"border_wht\" valign=\"top\"> ");
				out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("<tr> ");
				out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
				out.println("</tr>");
				out.println("<tr> ");
				out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td style=\"height: 327px\">");
				
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
				out.println("<tr>");
				out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Marketing - Payment Status Report</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");

        out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				
				out.println("</table>");
				
				out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
				out.println("<tr class=tr_input>");
				out.println("<td valign=top  width=100% Id=Follow_up> ");
				
				  
					out.println("<table class=table border='0' width='100%' >");
					out.println("<tr class=tr_input>");
				  //out.println("<td colspan=12 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          //out.println("<td colspan=14 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
          if(payment_catogary.trim().equals("VEN_PAY")){
					out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td colspan=7 align=left><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					}
					else if(payment_catogary.trim().equals("OTR_PAY")){
					out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td colspan=7 align=left><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					}
					out.println("</tr>");
					
          out.println("<tr class=pdn_txtpos2 align='center'>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Payment No  '            onclick=sort_data('PAY_NO')   >Payment No</td>");
          if(payment_catogary.trim().equals("VEN_PAY")){
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Finance No  '            onclick=sort_data('FIN_NO')    >Finance No</td>");
					out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Client Name  '            onclick=sort_data('CLI_NAME')   >Client Name</td>");
          }
					out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Vender Name  '            onclick=sort_data('VEN_NAME')  >Vender Name/Payee Name</td>");
   				out.println("<td  width='10%'   >Payee Code</td>");
		//     out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Client Name  '            onclick=sort_data('CLI_NAME')   >Client Name</td>");
					if(payment_catogary.trim().equals("OTR_PAY")){
					out.println("<td  width='10%'   >Entry Type</td>");
					
					}
					out.println("<td  width='10%' align='right'>Amount</td>");
					//out.println("<td  width='10%' align='right'>Settled Amount</td>");
					//out.println("<td  width='10%' align='right'>To Be Paid</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Priority  '            onclick=sort_data('PRIORITY') >Priority</td>");
					//out.println("<td  width='10%' style= cursor:hand; title='Click here to sort by - Marketing Officer  ' onclick=sort_data('MK_NAME') >Marketing Officer</td>");
					//out.println("<td  width='15%' style= cursor:hand; title='Click here to sort by - Client  '            onclick=sort_data('CLIENT') >Client</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Assets  '      onclick=sort_data('ASSET_COUNT') >Total Assets</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Pricing Status  '    onclick=sort_data('PRICING_STS') >Pricing Status</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pricing  '     onclick=sort_data('PRICING_COUNT') >Total Pricing</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Pro Forma  '   onclick=sort_data('PROFORMA_COUNT') >Total Pro Forma</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Total Valuation  '   onclick=sort_data('VALUATION_COUNT') >Total Valuation</td>");
					out.println("<td  width='20%'   >Status</td>");
				//	out.println("<td  width='10%'  >Detail</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Status2  '           onclick=sort_data('TERM_TYPE') >Status 2</td>");
					//out.println("<td  width='5%'  style= cursor:hand; title='Click here to sort by - Currency  '          onclick=sort_data('CURRENCY_CODE') >Currency</td>");
					
					
					out.println("<td  width='20%'  >Branch&nbsp;A/C&nbsp;VC&nbsp;No.</td>");
					
					//Added by Dineth on 2009-02-10
					if(payment_catogary.trim().equals("OTR_PAY")){
					out.println("<td  width='10%'  >Details</td>");
					}
					//End by Dineth on 2009-02-10
					
					
					
					out.println("</tr>");
					 
           int j = 0;   
						
						
						//  ========================== Commented by Dineth on 05-08-2008
						
							//if(m_type.equals("V")){ 
					    /*rs = stmt.executeQuery(" SELECT "+
																			" A.PAYMENT_NO, "+
																			" "+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO), "+
																			" "+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER), "+
																			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement') "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			" AND C.REF_NO=D.INVOICE_NO  "+
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
							}					
							if(m_type.equals("E")){ 
					    rs = stmt.executeQuery (" SELECT "+
																			" A.PAYMENT_NO, "+
																			" "+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO), "+
																			" "+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER), "+
																			" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement') "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
																			" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			" AND C.REF_NO=D.INVOICE_NO  "+
					                            " AND TO_DATE(TO_CHAR(A.ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(A.ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND A.PAYMENT_NO LIKE  '"+m_pay_no+"%' "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
							}	*/	
							if(payment_catogary.trim().equals("VEN_PAY")){
							 														
							if(m_app_status.trim().equals("DISBRS")){
																sql1= " SELECT PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT), "+
																			"	SUM(PAY_AMOUNT),PAYEE_NAME,SUS_REF_NO,BRAC_VOU_NO "+
																			"	FROM "+
																			"	(SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+//1
																			" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+//2
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+//3
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLI_NAME , "+//4
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+//5
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//6
																			" D.APPLICATION_NO APPLICATION_NO, "+//7
																			" A.ENTRY_TYPE ENTRY_TYPE,"+//8
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+//9
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT, "+//11
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(A.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(A.BRAC_VOU_NO,'-') BRAC_VOU_NO "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			" AND C.REF_NO=D.INVOICE_NO  "+
																			" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+//RECON_DATE
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.PROCESS_STATUS = 'DISBRS' "+
																			" AND A.ENTRY_TYPE = 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.CLIENT_CODE,A.PROCESS_STATUS,D.APPLICATION_NO,A.ENTRY_TYPE,A.PAYEE_NAME,A.SUS_REF_NO, A.BRAC_VOU_NO "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			//" ORDER BY "+m_sort_column+" "+m_order_by_type+" "+
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			" NVL(C.FINANCE_NO,'-') FINANCE_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-') CLI_NAME , "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			" D.APPLICATION_NO APPLICATION_NO, "+
																			" E.ENTRY_TYPE ENTRY_TYPE, "+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT,"+//10
																			" NVL(SUM(E.PAY_AMOUNT),0) PAY_AMOUNT,"+
																			" NVL(E.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(E.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(E.BRAC_VOU_NO,'-') BRAC_VOU_NO "+
																			" FROM  "+
	       														  ""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																			" B.REF_NO=D.INVOICE_NO "+
																			" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" AND B.SUS_REF_NO=E.SUS_REF_NO "+
																			" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.PROCESS_STATUS = 'DISBRS' "+
																			" AND E.ENTRY_TYPE = 'V' "+
																			" GROUP BY 1,C.FINANCE_NO,B.RECEIVER,C.CLIENT_CODE,E.PROCESS_STATUS,D.APPLICATION_NO,E.ENTRY_TYPE,E.PAYEE_NAME,E.SUS_REF_NO,E.BRAC_VOU_NO)"+
																			" GROUP BY PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,PAYEE_NAME,SUS_REF_NO,BRAC_VOU_NO "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
							}
							
							else if(m_app_status.trim().equals("OTHER")){
																 sql1= " SELECT PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT), "+
																			"	SUM(PAY_AMOUNT),PAYEE_NAME,SUS_REF_NO, BRAC_VOU_NO "+
																			"	FROM "+
																			"	(SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+//1
																			" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+//2
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+//3
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLI_NAME , "+//4
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+//5
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//6
																			" D.APPLICATION_NO APPLICATION_NO, "+//7
																			" A.ENTRY_TYPE ENTRY_TYPE,"+//8
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+//9
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT, "+//11
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(A.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(A.BRAC_VOU_NO,'-') BRAC_VOU_NO  "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			" AND C.REF_NO=D.INVOICE_NO  "+
																			" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+//RECON_DATE
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.PROCESS_STATUS <> 'DISBRS' "+
																			" AND A.ENTRY_TYPE = 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.CLIENT_CODE,A.PROCESS_STATUS,D.APPLICATION_NO,A.ENTRY_TYPE,A.PAYEE_NAME,A.SUS_REF_NO, A.BRAC_VOU_NO "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			//" ORDER BY "+m_sort_column+" "+m_order_by_type+" "+
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			" NVL(C.FINANCE_NO,'-') FINANCE_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-') CLI_NAME , "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			" D.APPLICATION_NO APPLICATION_NO, "+
																			" E.ENTRY_TYPE ENTRY_TYPE, "+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT,"+//10
																			" NVL(SUM(E.PAY_AMOUNT),0) PAY_AMOUNT,"+
																			" NVL(E.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(E.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(E.BRAC_VOU_NO,'-') BRAC_VOU_NO  "+
																			" FROM  "+
	       														  ""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																			" B.REF_NO=D.INVOICE_NO "+
																			" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" AND B.SUS_REF_NO=E.SUS_REF_NO "+
																			" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.PROCESS_STATUS <> 'DISBRS' "+
																			" AND E.ENTRY_TYPE = 'V' "+
																			" GROUP BY 1,C.FINANCE_NO,B.RECEIVER,C.CLIENT_CODE,E.PROCESS_STATUS,D.APPLICATION_NO,E.ENTRY_TYPE,E.PAYEE_NAME,E.SUS_REF_NO, E.BRAC_VOU_NO)"+
																			" GROUP BY PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,PAYEE_NAME,SUS_REF_NO,BRAC_VOU_NO "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
							
							
							}else{
							
							                  sql1= " SELECT PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT), "+
																			"	SUM(PAY_AMOUNT),PAYEE_NAME,SUS_REF_NO,BRAC_VOU_NO "+
																			"	FROM "+
																			"	(SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+//1
																			" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+//2
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+//3
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLI_NAME , "+//4
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+//5
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//6
																			" D.APPLICATION_NO APPLICATION_NO, "+//7
																			" A.ENTRY_TYPE ENTRY_TYPE,"+//8
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+//9
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT, "+//11
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(A.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(A.BRAC_VOU_NO,'-') BRAC_VOU_NO  "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C, "+
																			" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			" AND C.REF_NO=D.INVOICE_NO  "+
																			" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+//RECON_DATE
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.ENTRY_TYPE = 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.CLIENT_CODE,A.PROCESS_STATUS,D.APPLICATION_NO,A.ENTRY_TYPE,A.PAYEE_NAME,A.SUS_REF_NO,A.BRAC_VOU_NO "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			//" ORDER BY "+m_sort_column+" "+m_order_by_type+" "+
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			" NVL(C.FINANCE_NO,'-') FINANCE_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(C.CLIENT_CODE),'-') CLI_NAME , "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			" D.APPLICATION_NO APPLICATION_NO, "+
																			" E.ENTRY_TYPE ENTRY_TYPE, "+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT,"+//10
																			" NVL(SUM(E.PAY_AMOUNT),0) PAY_AMOUNT,"+
																			" NVL(E.PAYEE_NAME,'-') PAYEE_NAME, "+
																			" NVL(E.SUS_REF_NO,'-') SUS_REF_NO, "+
																			" NVL(E.BRAC_VOU_NO,'-') BRAC_VOU_NO  "+
																			" FROM  "+
	       														  ""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																			" B.REF_NO=D.INVOICE_NO "+
																			" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" AND B.SUS_REF_NO=E.SUS_REF_NO "+
																			" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.ENTRY_TYPE = 'V' "+
																			" GROUP BY 1,C.FINANCE_NO,B.RECEIVER,C.CLIENT_CODE,E.PROCESS_STATUS,D.APPLICATION_NO,E.ENTRY_TYPE,E.PAYEE_NAME,E.SUS_REF_NO,E.BRAC_VOU_NO "+ 
																			
																			
																			// added by udara 27-07-2015
																			 " UNION "+
																			
																			 " SELECT "+
																			   	 " A.PAYMENT_NO PAY_NO,  "+
																				 " A.FINANCE_NO FIN_NO, "+
																				 " '-' VEN_NAME , "+
																				 " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-') CLI_NAME , "+
																				 " 0 TOT_SETTLE_AMOUNT, "+
																				 " DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printing','DISBRS','Cheque Disbursement','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+
																				 " "+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO) APPLICATION_NO, "+
																				 " A.ENTRY_TYPE ENTRY_TYPE, "+
																				 " NVL(A.PAY_AMOUNT,0) BAL_TO_BE_PAID,  "+
																				 " NVL(A.PAY_AMOUNT,0) INT_BAL_SETTLE_AMOUNT, "+
																				 " NVL(A.PAY_AMOUNT,0) PAY_AMOUNT, "+
																				 " NVL(A.PAYEE_NAME,'-') PAYEE_NAME, "+
																				 " NVL(A.SUS_REF_NO,'-') SUS_REF_NO, "+
																					" NVL(A.BRAC_VOU_NO,'-') BRAC_VOU_NO "+
																					  " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A "+
																					  " WHERE A.ENTRY_TYPE = 'V'  "+
																					  " AND A.PROCESS_STATUS = 'CANCEL' "+
																					  " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																					  " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			
																			// end by udara 27-07-2015
																			
																			
																			
																			
																			" )"+
																			" GROUP BY PAY_NO,FIN_NO,VEN_NAME,CLI_NAME,PROCESS_STATUS,APPLICATION_NO,ENTRY_TYPE,PAYEE_NAME,SUS_REF_NO,BRAC_VOU_NO "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
																			
							}
							}		
							else if(payment_catogary.trim().equals("OTR_PAY")){
							 														
							if(m_app_status.trim().equals("DISBRS")){
															/*
															sql1= " SELECT PAY_NO,' ',VEN_NAME,' ',SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,' ',ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT),SUM(PAY_AMOUNT),PAYEE_NAME "+
																      " FROM "+
																			" (SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+
																			" ' ' ,"+
																			//" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ' ,"+
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',A.PROCESS_STATUS) PROCESS_STATUS, "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ENTRY_TYPE, "+
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT, "+
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME"+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			//" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			//" AND C.REF_NO=D.INVOICE_NO  "+
																		//	" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+//RECON_DATE
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.PROCESS_STATUS = 'DISBRS' "+
																			" AND A.ENTRY_TYPE <> 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.PROCESS_STATUS,A.ENTRY_TYPE,A.PAYEE_NAME "+
																			
																			/*
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			//" NVL(C.FINANCE_NO,'-'), "+
																			" ' ', "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(E.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ', "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT ,"+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(E.ENTRY_TYPE),'-') ENTRY_TYPE,"+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID,"+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT"+//10
																			" FROM  "+
	       														 // " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																		//	" B.REF_NO=D.INVOICE_NO "+
																		//	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" B.SUS_REF_NO=E.SUS_REF_NO "+
																			//" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.PROCESS_STATUS = 'DISBRS' "+
																			" AND E.ENTRY_TYPE <> 'V' "+
																			" GROUP BY 1, B.RECEIVER,E.PROCESS_STATUS,E.ENTRY_TYPE"+
																			*/
																			/*
																			")"+
																			" GROUP BY PAY_NO,VEN_NAME,PROCESS_STATUS,ENTRY_TYPE,PAYEE_NAME "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
																			*/
																sql1=" SELECT "+
																		 " A.PAYMENT_NO, "+
																		 " '','','','', "+
																		 " DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//APPRO2 DESC CHANGED BY Lalanka on 23-06-2009
																		 " '',"+
																		 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ,"+
																		 " '','',"+
																		 " NVL(A.PAY_AMOUNT,0) , "+
																		 " NVL(A.PAYEE_NAME,'-'), "+
																		 " NVL(A.SUS_REF_NO,'-'), "+
																		 " NVL(A.BRAC_VOU_NO,'-') "+
																		 " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A  "+
																		 " WHERE A.ENTRY_TYPE <> 'V' "+
																		 " AND A.PROCESS_STATUS = 'DISBRS' "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
																			
							}
							
							else if(m_app_status.trim().equals("OTHER")){
															/*	 sql1=" SELECT PAY_NO,' ',VEN_NAME,' ',SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,' ',ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT),SUM(PAY_AMOUNT),PAYEE_NAME "+
																      " FROM "+
																			" (SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+
																			" ' ' ,"+
																			//" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ' ,"+
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',A.PROCESS_STATUS) PROCESS_STATUS, "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ENTRY_TYPE, "+
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT ,"+
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME"+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			//" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			//" AND C.REF_NO=D.INVOICE_NO  "+
																		//	" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.PROCESS_STATUS <> 'DISBRS' "+
																			" AND A.ENTRY_TYPE <> 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.PROCESS_STATUS,A.ENTRY_TYPE,A.PAYEE_NAME "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			//" ORDER BY "+m_sort_column+" "+m_order_by_type+" "+
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			//" NVL(C.FINANCE_NO,'-'), "+
																			" ' ', "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(E.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ', "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT ,"+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(E.ENTRY_TYPE),'-') ENTRY_TYPE,"+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID,"+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT ,"+//10
																			" NVL(SUM(E.PAY_AMOUNT),0) PAY_AMOUNT,"+
																			" NVL(E.PAYEE_NAME,'-') PAYEE_NAME"+
																			" FROM  "+
	       														 // " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																		//	" B.REF_NO=D.INVOICE_NO "+
																		//	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" B.SUS_REF_NO=E.SUS_REF_NO "+
																			//" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.PROCESS_STATUS <> 'DISBRS' "+
																			" AND E.ENTRY_TYPE <> 'V' "+
																			" GROUP BY 1, B.RECEIVER,E.PROCESS_STATUS,E.ENTRY_TYPE,E.PAYEE_NAME )"+
																			" GROUP BY PAY_NO,VEN_NAME,PROCESS_STATUS,ENTRY_TYPE,PAYEE_NAME "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
																			*/
																sql1=" SELECT "+
																		 " A.PAYMENT_NO, "+
																		 " '','','','', "+
																		 " DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//APPRO2 DESC CHANGED BY Lalanka on 23-06-2009
																		 " '',"+
																		 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ,"+
																		 " '','',"+
																		 " NVL(A.PAY_AMOUNT,0) , "+
																		 " NVL(A.PAYEE_NAME,'-'), "+
																		 " NVL(A.SUS_REF_NO,'-'), "+
																		 " NVL(A.BRAC_VOU_NO,'-') "+
																		 " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A  "+
																		 " WHERE A.ENTRY_TYPE <> 'V' "+
																		 " AND A.PROCESS_STATUS <> 'DISBRS' "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
							
							
							}else{
							
							                /*  sql1=" SELECT PAY_NO,' ',VEN_NAME,' ',SUM(TOT_SETTLE_AMOUNT),PROCESS_STATUS,' ',ENTRY_TYPE,SUM(BAL_TO_BE_PAID),SUM(INT_BAL_SETTLE_AMOUNT),SUM(PAY_AMOUNT),PAYEE_NAME "+
																      " FROM "+
																			" (SELECT "+
																			" A.PAYMENT_NO PAY_NO, "+
																			" ' ' ,"+
																			//" NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO(D.APPLICATION_NO),'-') FIN_NO, "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ' ,"+
																			" NVL(SUM(C.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT, "+
																			" DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',A.PROCESS_STATUS) PROCESS_STATUS, "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ENTRY_TYPE, "+
																			" NVL(SUM(C.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID, "+
																			" NVL(SUM(C.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT, "+//10
																			" NVL(SUM(A.PAY_AMOUNT),0) PAY_AMOUNT, "+
																			" NVL(A.PAYEE_NAME,'-') PAYEE_NAME "+
																			" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+
																			" "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN B, "+
																			" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			//" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS E "+
																	  	" WHERE A.PAYMENT_NO =B.PAYMENT_NO "+
																			" AND B.SUS_REF_NO=C.SUS_REF_NO "+
																			//" AND C.REF_NO=D.INVOICE_NO  "+
																		//	" AND A.FINANCE_NO = E.FINANCE_NO "+
																			" AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																			" AND A.ENTRY_TYPE <> 'V' "+
																			" GROUP BY A.PAYMENT_NO,C.RECEIVER,A.PROCESS_STATUS,A.ENTRY_TYPE,A.PAYEE_NAME "+
																			//" AND A.PAYMENT_NO LIKE '"+m_pay_no+"%' "+
																			//" ORDER BY "+m_sort_column+" "+m_order_by_type+" "+
																			" UNION ALL "+
																			" SELECT 'N/A' PAY_NO, "+
																			//" NVL(C.FINANCE_NO,'-'), "+
																			" ' ', "+
																			" NVL("+m_schema_name+".AF_CO_GET_VENDOR_NAME(B.RECEIVER),'-') VEN_NAME , "+
																			//" NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(E.CLIENT_CODE),'-') CLI_NAME , "+
																			" ' ', "+
																			" NVL(SUM(B.TOT_SETTLE_AMOUNT),0) TOT_SETTLE_AMOUNT ,"+
																			" NVL(DECODE(E.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 1','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated',E.PROCESS_STATUS),'-') PROCESS_STATUS, "+
																			//" NVL(DECODE(C.APPLICATION_STATUS,'ENTERED','Entered','ENT_CON','Completed','VERIFY1','Verification','V-APP','Score Approval','VERIFY-M','Approval 1','VERIFY2','Approval 2','VERIFYL','Entered Leasing No','ACTIVATED','Activated','CANCEL','Cancel','REPOSSESS','Repossess','REJECT','Rejected'),'-'), "+
																			//" D.APPLICATION_NO, "+
																			" ' ' ,"+
																			" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(E.ENTRY_TYPE),'-') ENTRY_TYPE,"+
																			" NVL(SUM(B.BAL_TO_BE_PAID),0) BAL_TO_BE_PAID,"+
																			" NVL(SUM(B.INT_BAL_SETTLE_AMOUNT),0) INT_BAL_SETTLE_AMOUNT ,"+//10
																			" NVL(SUM(E.PAY_AMOUNT),0) PAY_AMOUNT, "+
																			" NVL(E.PAYEE_NAME,'-') PAYEE_NAME "+
																			" FROM  "+
	       														 // " "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
	        														" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+
																			//" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
																			" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
																			" WHERE "+
																		//	" B.REF_NO=D.INVOICE_NO "+
																		//	" AND D.APPLICATION_NO=C.APPLICATION_NO "+
																			" B.SUS_REF_NO=E.SUS_REF_NO "+
																			//" AND D.ACTIVE_STATUS <>'C' "+
																			" AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
					                            " AND TO_DATE(TO_CHAR(E.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
																			" AND E.ENTRY_TYPE <> 'V' "+
																			" GROUP BY 1, B.RECEIVER,E.PROCESS_STATUS,E.ENTRY_TYPE,E.PAYEE_NAME )"+
																			" GROUP BY PAY_NO,VEN_NAME,PROCESS_STATUS,ENTRY_TYPE,PAYEE_NAME "+
																			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
																			*/
												        sql1=" SELECT "+
																		 " A.PAYMENT_NO, "+
																		 " '','','','', "+
																		 " DECODE(A.PROCESS_STATUS,'RE-APP','Payment Requisition-Generation - Credit','TM_APP',' Payment Requsition Special Approval - Credit','RE_A_2','Payment Requsition - Processing','APPRO1','Payment Approval 1','APPRO2','Payment Approval 2','PRINT','Cheque Printed','DISBRS','Cheque Disbursement','Y','Bank Account Allocated','AUTHO','Authorized By Division',A.PROCESS_STATUS) PROCESS_STATUS, "+//APPRO2 DESC CHANGED BY Lalanka on 23-06-2009
																		 " '',"+
																		 " NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.ENTRY_TYPE),'-') ,"+
																		 " '','',"+
																		 " NVL(A.PAY_AMOUNT,0) , "+
																		 " NVL(A.PAYEE_NAME,'-'), "+
																		 " NVL(A.SUS_REF_NO,'-'), "+
																		 " NVL(A.BRAC_VOU_NO,'-') "+
																		 " FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A  "+
																		 " WHERE A.ENTRY_TYPE <> 'V' "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
																		 " AND TO_DATE(TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
							}
							}
							
																			//out.println(sql1);
       rs = stmt.executeQuery(sql1);
              while(rs.next()){
	
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									   out.println("<tr class=tr_input >");
									}
									
							    
									/*if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}*/
									
									//out.println("<td width='15%' align='left' style= cursor:hand;cursor-color:blue ><u>"+rs.getString(1) +"</u></td>");
									//out.println("<td width='15%' align='center' STYLE='{text-align:left; cursor:hand;}'><u>"+rs.getString(2) +"</u></td>");

									out.println("<td width='10%' align='left' style= cursor:hand;cursor-color:blue onclick=\"show_payment_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1) +"</u></td>");
                  //out.println("<td width='15%' align='left'>"+rs.getString(1) +"</td>");
									 if(payment_catogary.trim().equals("VEN_PAY")){
                  out.println("<td width='10%' align='left' STYLE='{text-align:left; cursor:hand;}' onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2) +"</u></td>");
                   
									out.println("<td width='20%'  align='left'>"+rs.getString(4) +"</td>");
									}
									out.println("<td width='20%'  align='left'>"+rs.getString(12) +"</td>");
									out.println("<td  width='10%'   >"+rs.getString(13)+"</td>");
                  //out.println("<td width='20%'  align='left'>"+rs.getString(4) +"</td>");
									if(payment_catogary.trim().equals("OTR_PAY")){
									out.println("<td width='20%'  align='left'>"+rs.getString(8) +"</td>");
									
									}
                  out.println("<td width='10%'  align='right'>"+nf2.format(rs.getDouble(11)) +"</td>");
									//out.println("<td width='10%'  align='right'>"+nf2.format(rs.getDouble(10)) +"</td>");
									//out.println("<td width='10%'  align='right'>"+nf2.format(rs.getDouble(9)) +"</td>");
									out.println("<td width='10%'  align='left'>"+rs.getString(6) +"</td>");
									//out.println("<td width='*%'  align='center'><input type=button name=Detail value=Detail class=mainbut onclick=\"load_data_frame('"+rs.getString(7)+"')\" ></td>");
									
									
									out.println("<td width='20%'  align='left'>"+rs.getString(14) +"</td>");
									
									//Added by Dineth on 2009-02-10
									if(payment_catogary.trim().equals("OTR_PAY")){
									out.println("<TD WIDTH='10%' align=\"center\"><input class='but_input' type='button' name=but_details"+j+" value=\"Details\" onClick=\"Payment_breakup('"+rs.getString(1)+"')\"></td>");
									
									}
									//End by Dineth on 2009-02-10
									
									
									
									out.println("</tr>");
                	j=j+1;
              }
          

					out.println("<tr class=tr_input>");
				  //out.println("<td colspan=12 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          //out.println("<td align=right colspan=14><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
         if(payment_catogary.trim().equals("VEN_PAY")){
					out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=left colspan=7><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
 					}
					else  if(payment_catogary.trim().equals("OTR_PAY")){
					out.println("<td colspan=6 align=right><input type=button name=Close value=Close class=mainbut onclick=close_window(); ></td>");
          out.println("<td align=left colspan=7><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
					}
          out.println("</tr></table>");
          out.println("</td>");
			
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("</html>");
      }
			
			//=========================================================================================================================			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
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
