
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Old_Repayment_Schedule extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;

			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.trim().equals("main_page")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Post Dated Cheque Report-Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
						
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_FIN_SQL\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");	
			
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];");
			out.println("}"); 
			
						
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(IfCount==\"100\"){"); 
			out.println("		help_update_value_assign_100();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"39\"){"); 
			out.println("		help_value_assign_app_no();"); 
	  	out.println("		}"); 
      		
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		assign_client_no();"); 
	  	out.println("		}"); 
					
			out.println("	}"); //end next
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function help_button_4() {");
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			out.println("    m_sql = \"m_help_client_code_help\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\";"); 							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'3');"); 
			out.println("}");
			
			out.println("function assign_client_no() {");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];");
			out.println("}");
			
			out.println("function help_button_2() {");
			out.println("    document.Form1.hid_help_type.value=\"39\";");
			out.println("    m_sql = \"m_help_TXT_FIN_SQL\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\";"); 							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'39');"); 
			out.println("}");
			
			out.println("function help_value_assign_app_no() {");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];");
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];");
			out.println("}");
			
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Old_Repayment_Schedule?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Old_Repayment_Schedule?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_payment_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Old Repayment Schedule - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Old Repayment Schedule \";"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}");
			
			
			out.println("function view(){ ");
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Old_Repayment_Schedule?chksql=MAIN&fin_no=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&app_no=\"+document.Form1.TXT_APPLICATION_NO.value+\" \";");
			out.println("window.open(m_url,'displayWindow1343','left=0,top=100,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println("}else{");
		  out.println("DIV_TXT_FINANCE_NO.style.color=\"red\"; ");
			out.println("}");
			out.println("}");
			
			
			
							
				
			out.println("</Script>");
			
			out.println("<body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
					
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Old Repayment Schedule</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			
			out.println("<td width='36%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
      out.println("</table>");
			out.println("</td>	");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
			
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"4\" width=\"100%\" >");
				
  		out.println("<tr>"); 
			out.println("<td width='10%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No* </DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onblur=\"help_update()\"  >"); 
			out.println("<input class='but_input' type='button' name='BUT_PURCHASE_ORDER_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			out.println("<tr>");		
			out.println("<td width='20%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Application No</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\" help_button_2()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APP_NO' value=\"Help\" onClick=\" help_button_2()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");		
			
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT'  class=div_input>Client Code</DIV></td>"); 
			out.println("<td width='25%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' >"); 
			out.println("<input class='but_input' type='button' text-align='center' name='BUT_HELP_CLIENT' value=\" Help \" onClick=\"help_button_4()\"></td>"); 
			out.println("<td width=\"5%\" >&nbsp;</td>");
			out.println("<td width='*%'><input type='button' name='VIEW_BT' value='View' class='but_input' onclick='view()'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>");
						
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 

			out.println("</html>");

					
			}else	if(m_screen_type.equals("MAIN")){ 
			
			String m_finance_no = req.getParameter("fin_no");
			String m_application_no = req.getParameter("app_no");
			
					
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Old Repayment Schedule</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

				
			pstmt = conn.prepareStatement("  SELECT A.APPLICATION_NO, "+//1
							" A.PRICING_NO,  "+//2
							" A.INSTALLMENT_NO, "+//3
							" A.GRENTAL_AMOUNT,  "+//4
							" A.NET_RENTAL_AMOUNT, "+//5
							" A.BALANCE_TO_BE_RECEIVED, "+//6
							" TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY'), "+//7
							" NVL(A.INVOICE_NO,'-'), "+//8
							" A.PRO_INVOICE_NO, "+//9
							" B.ASSET_ID "+//10
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
							" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
							" AND   A.PRO_INVOICE_NO = B.INVOICE_NO "+
							" AND   A.PRICING_NO     = B.PRICING_NO "+
							" AND   B.ACTIVE_STATUS  = 'T' "+
							" AND   A.APPLICATION_NO = '"+m_application_no+"' "+
							" ORDER BY A.PRICING_NO,A.PRO_INVOICE_NO,TO_NUMBER(A.INSTALLMENT_NO) ");
			
			rs2=pstmt.executeQuery(); 

			boolean more2=rs2.next();
			int row = 0;
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<br>");
			if(!more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\"  style=\"height: 18px\" id=help_box><b>Old Repayment Schedule</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<br><br>");
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><font color='red'>No Data Found...!</font></td></tr>");
			out.println("</table>");
			}
			
			if(more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Old Repayment Schedule</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='10%' >Pricing No</td>");  
			out.println("<td width='10%' >Pro.Invoice_no</td>"); 
			out.println("<td width='10%' >Asset Id</td>"); 
			out.println("<td width='10%' >Invoice No</td>"); 
			out.println("<td width='5%' >Ins. No</td>");
			out.println("<td width='10%' align='center'>Rental Date</td>");
			out.println("<td width='10%' align='right'>Gross Amount</td>"); 
			out.println("<td width='10%' align='right'>Net Amount</td>");			
			out.println("<td width='10%' align='right'>Balance Amount</td>");
			out.println("</tr >");
			
			while(more2){
						
      if(row>0 && row%2==1){
			out.println("<tr class = 'tr_input' bgcolor=silver >");
			}else{
			out.println("<tr class = 'tr_input' >");
			}
			out.println("<TD width='10%'   >"+rs2.getString(2)+"</TD>");			
			out.println("<TD width='10%'   >"+rs2.getString(9)+"</TD>");			
			out.println("<TD width='10%'   >"+rs2.getString(10)+"</TD>");
			out.println("<TD width='10%'   >"+rs2.getString(8)+"</TD>");
			out.println("<TD width='5%'  align='center' >"+rs2.getString(3)+"</TD>");
			out.println("<TD width='10%' align='center' >"+rs2.getString(7)+"</TD>");
			out.println("<TD width='10%' align='right'  >"+nf.format(rs2.getDouble(4))+"</TD>");			
			out.println("<TD width='10%' align='right'  >"+nf.format(rs2.getDouble(5))+"</TD>");
			out.println("<TD width='10%' align='right'  >"+nf.format(rs2.getDouble(6))+"</TD>");
			
			out.println("</tr >"); 
			 more2=rs2.next(); 
			 row++;
				}
		}
		  out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			rs2.close();
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


