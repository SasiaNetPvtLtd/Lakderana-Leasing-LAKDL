//--
//SCREEN NAME: Vendor Balance Report
//CREATED BY : SH
//DATE/TIME  : 11/06/2007
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Vendor_Bal_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url      =m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_client_name    =m_sn_methods.client_name.trim();
			String m_schema_name    = m_sn_methods.schema_name;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			conn = m_sn_methods.met_user_validate(req); 
			PreparedStatement pstmt;
			stmt=conn.createStatement();
			
      m_chksql=req.getParameter("chksql");
			out = res.getOutputStream();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			
			String m_fin_code,m_invoice;
			
			

		if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>  Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_cnt=0");
			out.println("var m_chk=0");
			out.println("var b_flag=0;");
				
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
			out.println(" ");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_no_val.value==\"0\"){");
			out.println("alert('No records')");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("document.Form1.hid_st.value='T'");
			out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=request_details&m_val=\"+document.Form1.hid_status.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_client_name+"AF_CO_PRO_display_security_and_marketing_file?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_PRO_display_security_and_marketing_file\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Vendor Balance Report  \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Vendor Balance Report  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"Request\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("document.Form1.hid_save.value=\"Return\";"); 
			out.println("document.Form1.hid_status.value=\"Return\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

      out.println("function clear_data(){ ");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){");
			out.println(" document.Form1.TXT_CLIENT_NAME.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"100\"){ ");
			out.println(" document.Form1.TXT_MKT_OFFICER.value=\"\";");
			out.println("}else if(document.Form1.hid_help_type.value==\"101\"){ ");
			out.println(" document.Form1.TXT_SUP_NAME.value=\"\";");
			out.println("} ");
			out.println("} ");
			out.println("");
			
			out.println("function clear_screen(){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("request_details.innerHTML = ''; ");
			out.println("}"); 

			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("   checkMonthLength(objDD,objMM,objYY);");
			out.println("}");

     out.println("function Generate_Report() {");
		 out.println(" 	m_from_dd = document.Form1.VAL_DAY.value ");
		 out.println(" 	m_from_mm = document.Form1.VAL_MONTH.value ");
		 out.println(" 	m_from_yy = document.Form1.VAL_YEAR.value ");
		 out.println(" 	m_from_date = m_from_dd+\"-\"+m_from_mm+\"-\"+m_from_yy; ");
		 //out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=Report&from_date="+m_from_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"");			
     out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=Report&from_date=\"+m_from_date+\"&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=250,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
      
     out.println("function get_sus_pay() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_sus_pay&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=250,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     
		 out.println("function get_set_pay() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_set_pay&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=250,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     			
		 out.println("function get_vendor() {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_vendor&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=250,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     	
//______________________________________________________________________________________________________________________________
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value(' ')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
			out.println("</tr>"); 
			out.println("<tr> "); 
			out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td style='height: 327px'>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
			out.println("<tr>"); 
			out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>  Rental Details Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<br>");
			
		  	out.println("<table align='center' width='100%' class='table'>"); 

			
			
			rs = stmt.executeQuery ("SELECT SUM(DECODE(DRCR_STATUS,'DR',TRNAMOUNT,TRNAMOUNT*-1)), "+
			                        "       "+m_schema_name+".AF_CO_GET_VENDOR_PAY_BAL(TO_CHAR(SYSDATE,'DD-MM-YYYY')), "+
															"       "+m_schema_name+".AF_CO_GET_VENDOR_PEN_PAY_BAL (TO_CHAR(SYSDATE,'DD-MM-YYYY')) "+ 
														  "FROM   "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT "+
														  "WHERE  ACC_TYPE_CODE='10001' AND "+
														  "       ENTDATE >='12-JUN-2007' AND "+  
															"       TRNDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
								
					boolean more1 = rs.next();
						
			if (more1){
      			
	      out.println("<tr class=pdn_txtpos2>"); 
			  out.println("<td width='20%' class='txt_report_column2'>Outstanding Payments</td>");
				out.println("<td width='20%' class='txt_report_column2'>Inprogress Payments</td>");
				out.println("<td width='20%' class='txt_report_column2'>Vendor Account Balance</td>");
				out.println("</tr>");
			  
				out.println("<tr>");
				out.println("<td onclick=get_sus_pay() class='txt_report_column2' style= cursor:hand; ><u>"+nf.format(rs.getDouble(2))+"</u></td>");
				out.println("<td onclick=get_set_pay() class='txt_report_column2' style= cursor:hand; ><u>"+nf.format(rs.getDouble(3))+"</u></td>");
				
				if(rs.getDouble(1)>=0){ 
				  out.println("<td onclick=get_vendor() class='txt_report_column2' style= cursor:hand; ><u> "+nf.format(rs.getDouble(1))+"</u></td>");
				}else{
				  out.println("<td onclick=get_vendor() class='txt_report_column2' style= cursor:hand; ><u> ("+nf.format((rs.getDouble(1)*-1))+")</u></td>");
				}
				out.println("</tr>");
			
			  
			}
			out.println("</table>");
			 
			out.println("<br>");
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=request_details></div></td></tr></table>");
			
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	

else if(m_chksql.equals("get_sus_pay")){

String m_appl_no   = req.getParameter("appl_no");
String m_from_date = req.getParameter("from_date");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_sus_pay_drill(num) {");
		  out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_sus_pay_drill&vendor=\"+num+\"&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		  out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
      out.println("}");
     
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>Vendor Balance - Invoices </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT A.RECEIVER,"+m_schema_name+".AF_CO_GET_VENDOR_NAME(RECEIVER), "+
															"        SUM(A.TOT_SETTLE_AMOUNT),SUM(A.INT_BAL_SETTLE_AMOUNT),SUM(BAL_TO_BE_PAID) "+
															" FROM   "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  A "+
															" WHERE  SUSPENSE_ENTRY_TYPE='V' AND  "+
															"        VALUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND  "+
															"        ENT_DATE>='12-JUN-2007' AND BAL_TO_BE_PAID>0 "+
															" GROUP  BY RECEIVER  ");
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Vendor Code</td>"); 
			out.println("<td width='20%' class='txt_report_column'>Vendor Name</td>");
			out.println("<td width='15%' class='txt_report_column2'>Total Amount</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Settled Amount</td>"); 
			out.println("<td width='15%' class='txt_report_column2' >Balance Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
			int i=0;																
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=get_sus_pay_drill(escape(document.Form1.VEN_"+i+".value))><u>"+rs.getString(1)+"</u><INPUT TYPE=HIDDEN NAME=VEN_"+i+" value=\""+rs.getString(1)+"\"></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(2)+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(3);
			tot_capital=tot_capital + rs.getDouble(4);
			tot_interest=tot_interest + rs.getDouble(5);
			
			more = rs.next();
			i=i+1;
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_interest)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	

else if(m_chksql.equals("get_sus_pay_drill")){

   String m_vendor   = req.getParameter("vendor");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("function get_sus_pay_drill(num) {");
		 out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_set_pay_drill&from_date=&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		 out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
     out.println("}");
     
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>Vendor Balance - Invoices </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT SUS_REF_NO,TO_CHAR(VALUE_DATE,'DD-MM-YYYY'), "+
															"        (A.TOT_SETTLE_AMOUNT),(A.INT_BAL_SETTLE_AMOUNT),(BAL_TO_BE_PAID) "+
															" FROM   "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT  A "+
															" WHERE  SUSPENSE_ENTRY_TYPE='V' AND A.RECEIVER='"+m_vendor+"' AND "+
															"        VALUE_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND  "+
															"        ENT_DATE>='12-JUN-2007' AND BAL_TO_BE_PAID>0 "+
															"   ");
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Ref. No</td>"); 
			out.println("<td width='20%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column2'>Total Amount</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Settled Amount</td>"); 
			out.println("<td width='15%' class='txt_report_column2' >Balance Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=call_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; >"+rs.getString(2)+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(3);
			tot_capital=tot_capital + rs.getDouble(4);
			tot_interest=tot_interest + rs.getDouble(5);
			
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_interest)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
else if(m_chksql.equals("get_set_pay")){

   String m_vendor   = req.getParameter("vendor");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_set_pay_drill(num) {");
		  out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_set_pay_drill&vendor=\"+num+\"&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		  out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
      out.println("}");
   
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>Vendor Balance - Pending Payments </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT B.RECEIVER,"+m_schema_name+".AF_CO_GET_VENDOR_NAME(RECEIVER), "+
															"        SUM(PAY_AMOUNT) "+
															" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  A, "+
															"        "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B "+
															" WHERE  A.SUS_REF_NO=B.SUS_REF_NO AND ENTRY_TYPE='V' AND  "+
															"        EFF_VALDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND  "+
															"        ENTDATE>='12-JUN-2007' AND PROCESS_STATUS NOT IN ('DISBRS','CANCEL') "+
															" GROUP  BY RECEIVER  ");
															
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Vendor Code</td>"); 
			out.println("<td width='20%' class='txt_report_column'>Vendor Name</td>");
			out.println("<td width='15%' class='txt_report_column2'>Payment Amount</td>");
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=get_set_pay_drill(escape('"+rs.getString(1)+"'))><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; ><U>"+rs.getString(2)+"</U></td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(3);
			
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
else if(m_chksql.equals("get_set_pay_drill")){

   String m_vendor   = req.getParameter("vendor");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function get_set_pay_drill(num) {");
		  out.println("m_url=\""+m_class_url+"/"+m_client_name+"AF_CR_PRO_Vendor_Bal_Report?chksql=get_sset_pay_drill&vendor=\"+num+\"&sort_column=APPLICATION_NO&order_by_type=DESC\";");
		  out.println("popupwin=window.open(m_url,'displayWindow2','left=60,top=350,width=900,height=350,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
      out.println("}");
   
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>Vendor Balance - Pending Payments </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT PAYMENT_NO,TO_CHAR(EFF_VALDATE,'DD-MM-YYYY'), "+
															"        (PAY_AMOUNT) "+
															" FROM   "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  A, "+
															"        "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B "+
															" WHERE  A.SUS_REF_NO=B.SUS_REF_NO AND ENTRY_TYPE='V' AND  "+
															"        B.RECEIVER='"+m_vendor+"' AND "+
															"        EFF_VALDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') AND  "+
															"        ENTDATE>='12-JUN-2007' AND PROCESS_STATUS NOT IN ('DISBRS','CANCEL') ");
															
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Payment No</td>"); 
			out.println("<td width='20%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column2'>Payment Amount</td>");
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=call_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(2)+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(3))+"</td>"); 
			out.println("</tr >"); 	
			
			tot_retal=tot_retal + rs.getDouble(3);
			
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_retal)+"</B></td>"); 
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


	
else if(m_chksql.equals("get_vendor")){

String m_appl_no   = req.getParameter("appl_no");
String m_from_date = req.getParameter("from_date");

    	out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Vendor Balance Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 

      out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("</table>"); 
			
			out.println("<BR>");
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100%' class='txt_report_column'>Vendor Balance - Vendor </td>"); 
			out.println("</table>");
			
			out.println("<BR>");

			
			rs =	stmt.executeQuery(" SELECT A.DOCREFNO,TO_CHAR(A.TRNDATE,'DD-MM-YYYY'),A.DRCR_STATUS,A.TRNAMOUNT "+
															" FROM   "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+ 
															" WHERE  ACC_TYPE_CODE='10001' AND "+
															"        A.ENTDATE >='12-JUN-2007' AND "+  
															"        TRNDATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') ");
																			
			boolean more = rs.next();		
						
			double tot_retal=0.00;		
			double tot_capital=0.00;
			double tot_interest=0.00;
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='1%'></td>");
			out.println("<td width='25%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column'>Reference No</td>"); 
			out.println("<td width='15%' class='txt_report_column'>Value Date</td>");
			out.println("<td width='15%' class='txt_report_column'>Dr/Cr</td>");
			
			out.println("<td width='15%' class='txt_report_column2' >Amount</td>"); 
			out.println("</tr >"); 
			//out.println("</table>");
			
																			
			while(more){																
			//out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center' style= cursor:hand; onClick=call_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(2)+"</td>");
			out.println("<td class='txt_report_data' align='center'>"+rs.getString(3)+"</td>"); 
			out.println("<td class='txt_report_data' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("</tr >"); 	
			if(rs.getString(3).equals("DR")){
			  tot_capital=tot_capital + rs.getDouble(4);
		  }else{
			  tot_capital=tot_capital - rs.getDouble(4);
			}
			more = rs.next();
			
			}
			
			out.println("<tr >"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'></td>"); 
			out.println("<td class='txt_report_data' align='center'><B>Total</B></td>");
			if(tot_capital>=0){
			 out.println("<td class='txt_report_data' align='right'><B>"+nf.format(tot_capital)+"</B></td>"); 
			}else{
			 out.println("<td class='txt_report_data' align='right'><B>("+nf.format(tot_capital*-1)+")</B></td>"); 
			}
			out.println("</tr >"); 	
			out.println("</table>"); 
							
							

			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	
		
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null)  {try{rs.close();  }catch(Exception e){}}
				if(stmt!=null){try{stmt.close();}catch(Exception e){}}
				if(conn!=null){try{conn.close();}catch(Exception e){}}
				if(out!=null) {try{out.close(); }catch(Exception e){}}
		}
	}
}
