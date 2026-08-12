
//SCREEN NAME:VAT REPORT
//CREATED BY:SANDUN
//DATE/TIME:02/01/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_MISF_Display_Vat_Report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt,pstmt1,pstmt2;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

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
			
			String m_date_dd = "";
			String m_date_mm = "";
			String m_date_yy = "";
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System -VAT Report</title>");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" VAT Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" VAT Report  - \"+document.Form1.hid_status.value;"); 
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
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
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
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Display_Vat_Report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Display_Vat_Report?chksql=main_page';"); 
			out.println("}"); 			
			
			out.println("function load_payment_detail(){ ");	
			out.println("from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("var d1 = new Date(from_date);");
			out.println("var d2 = new Date(to_date);");		
			out.println("if(d2>=d1){");		
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Display_Vat_Report?chksql=MAIN&from_date=\"+from_date+\"&to_date=\"+to_date+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");				
			out.println(" else{");
			out.println("alert('To Date must be greater than From Date..!');");
			out.println(" } ");	
			out.println(" } ");
			
			out.println("function load_screen_status(m_val){"); 			
			out.println("if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 			
			out.println("}"); 			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY1.value=v_date;");
			out.println("     document.Form1.VAL_MONTH1.value=v_month;");
			out.println("     document.Form1.VAL_YEAR1.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_from_date.value=date1");			
			out.println("}");
				
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form1.VAL_DAY2.value=v_date;");
			out.println("     document.Form1.VAL_MONTH2.value=v_month;");
			out.println("     document.Form1.VAL_YEAR2.value=val;");
			out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("document.Form1.hid_to_date.value=date2");
			out.println("}");
			out.println("}");

		  out.println("function get_sys_date(){");
			rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			if(rs.next()){
			m_date_dd = rs.getString(1).substring(0,2);
			m_date_mm = rs.getString(1).substring(3,5);
			m_date_yy = rs.getString(1).substring(6,10);
			}
			out.println("document.Form1.VAL_DAY1.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH1.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR1.value  =\""+m_date_yy+"\"");
			out.println("document.Form1.VAL_DAY2.value   =\""+m_date_dd+"\"");
			out.println("document.Form1.VAL_MONTH2.value =\""+m_date_mm+"\"");
			out.println("document.Form1.VAL_YEAR2.value  =\""+m_date_yy+"\"");
			out.println("}");
			
			
      out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
      out.println("}");
			out.println("}");
			
				
			out.println("</Script>");
			
			out.println("<body onload=\"get_sys_date()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>VAT Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<td width='6%'></td>"); 
			out.println("<td width='6%'></td>"); 
			out.println("<td width='6%'></td>"); 
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
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table'>"); 
  			
			out.println("<tr class=tr_input>");
			out.println("<td width='5%' ID=VDATE><b>Date</b></td>");
			out.println("<td width='5%' ID=VDATE></td>");
			out.println("<td width='12%' ID=VDATE>From</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			
			out.println("<td width='7%' ID=VDATE>To</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_payment_detail()\"></td>"); 
		  out.println("<td width='*%'></td>");
			out.println("</tr>");	
			out.println("</table>");			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){  
			
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");			
			
			double m_tot_exclud_vat = 0.0;
			double m_tot_vat_value  = 0.0;
			double m_tot_includ_vat = 0.0;
			double m_tot_allowable  = 0.0;
			double m_tot_disallowbl = 0.0;
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>VAT Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='center'  style='height: 18px'><b>VAT Report - From "+m_from_date+" To "+m_to_date+"</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
		
			int j = 0;			
		  pstmt = conn.prepareStatement( " SELECT DISTINCT A.APPLICATION_NO, "+//1
																		" A.FINANCE_NO REFERENCE_NO, "+//2
																		" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') REF_TYPE,"+//3
																		" B.GROSS_AMOUNT INCLUDING_VAT,"+//4
																		" B.VAT_AMOUNT VAT_VALUE,"+//5
																		" B.NET_AMOUNT EXCLUDING_VAT,"+//6
																		" ROUND("+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO)-B.NET_AMOUNT),"+//7
																		" B.VAT_PERCENTAGE ,"+//8
																		" B.VAT_APP,"+//9
																		" "+m_schema_name+".AF_CO_GET_VENDOR_NAME(C.VENDOR_CODE),"+//10
																		" NVL(E.TAX_INV_NO,'-'), "+//11
																		" NVL(TO_CHAR(E.TAX_INV_DATE,'DD-MM-YYYY'),'-'), "+//12	
																		" NVL("+m_schema_name+".AF_CO_GET_VENDOR_VAT_REG_NO(C.VENDOR_CODE),'-') "+//13
																		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																		"      "+m_schema_name+".AF_CO_PRO_APP_PRICING B, "+
																		"	     "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C,"+
																		"	     "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT D, "+
																		"	     "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN E "+
																		" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
																		" AND   A.APPLICATION_NO = C.APPLICATION_NO "+
																		" AND   A.FINANCE_NO = D.FINANCE_NO(+) "+
																		" AND   D.PAYMENT_NO = E.PAYMENT_NO(+) "+
																		" AND   A.APPLICATION_STATUS = 'ACTIVATED' "+
																		" AND   A.APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM   "+m_schema_name+".AF_CR_PRO_TERMINATION WHERE TERMINATION_TYPE = 'BALANCE_RE') "+ //Added By Sandun on 24-06-2009
																		" AND   A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" AND   A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
																		
		
		 rs=pstmt.executeQuery(); 			
	
			boolean more=rs.next();
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width=100% align='center'><font color=red>No Data Found...!</font></td><tr>");
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='*%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='10%' align='left'>Ref Type</td>");
			out.println("<td width='10%' align='left'>Reference No</td>"); 			 
			out.println("<td width='10%' align='left'>Invoice No</td>"); 
			out.println("<td width='10%' align='left'>Invoice Date</td>"); 
			out.println("<td width='15%' align='left'>Suppliers' VAT Registration No</td>"); 
			out.println("<td width='20%' align='lrft'>Supplier Name</td>"); 
			out.println("<td width='10%'  align='right'>Excluding VAT</td>"); 
			out.println("<td width='10%'  align='right'>VAT Value</td>"); 
			out.println("<td width='10%' align='right'>Including VAT</td>"); 
			out.println("<td width='10%' align='right'>Allowable 12%</td>");
			out.println("<td width='10%' align='right'>Disallowable</td>");
			//out.println("<td width='10%' align='right'>Debit Amount</td>");
			out.println("</tr >");
			
			
			while(more){
									
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input1 >");
			}
			else{
      	out.println("<tr class=tr_input >");
			}
			out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
			out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>"); 			 
			out.println("<td width='10%' align='left'>"+rs.getString(11)+"</td>");
			out.println("<td width='10%' align='left'>"+rs.getString(12)+"</td>");
			out.println("<td width='15%' align='left'>"+rs.getString(13)+"</td>"); 
			out.println("<td width='20%' align='left'>"+rs.getString(10)+"</td>");
			out.println("<td width='10%'  align='right'>"+nf.format(rs.getDouble(6))+"</td>"); 
			out.println("<td width='10%'  align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)-rs.getDouble(7))+"</td>"); 
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(7))+"</td>"); 
			//out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(5)-2*rs.getDouble(7))+"</td>"); 
			out.println("</tr >");
			
			m_tot_exclud_vat = m_tot_exclud_vat+rs.getDouble(6);
			m_tot_vat_value  = m_tot_vat_value+rs.getDouble(5);
			m_tot_includ_vat = m_tot_includ_vat+rs.getDouble(4);
			m_tot_allowable  = m_tot_allowable+(rs.getDouble(5)-rs.getDouble(7));
			m_tot_disallowbl = m_tot_disallowbl+rs.getDouble(7);
			
			more=rs.next(); 
			j=j+1;
			
			} 
      
			out.println("<tr bgcolor='66CCFF'>");
			out.println("<td width='10%' align=center>&nbsp;</td>");
			out.println("<td width='10%' align=center>&nbsp;</td>");
			out.println("<td width='10%' align=center>&nbsp;</td>");
			out.println("<td width='10%' align=center>&nbsp;</td>");
			out.println("<td width='15%' align=center>&nbsp;</td>");
			out.println("<td width='20%' align=center><b>Total</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_tot_exclud_vat)+"</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_tot_vat_value)+"</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_tot_includ_vat)+"</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_tot_allowable)+"</td>");
			out.println("<td width='10%' align=right><b>"+nf.format(m_tot_disallowbl)+"</td>");
			out.println("<tr>");
			out.println("</table>");	
			}
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


