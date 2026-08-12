
//SCREEN NAME:STAMP DUTY REPORT
//CREATED BY:SANDUN
//DATE/TIME:11/11/2008
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_MISF_Stamp_Duty_report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt_rental;
		public ResultSet rs,rs1,rs2,rs_rental;
		PreparedStatement pstmt,pstmt1,pstmt2;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);

			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();	
			stmt_rental = conn.createStatement();	
			
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
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Stamp Duty Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Stamp Duty Report  - \"+document.Form1.hid_status.value;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Stamp_Duty_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function load_detail(){ ");	
			out.println("from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("stmp_type = document.Form1.TXT_STMP_DUTY.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Stamp_Duty_report?chksql=MAIN&stmp_type=\"+stmp_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Stamp Duty Report</td>");
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
			out.println("<table align='center' width='100%' class='table' border=0>"); 
  			
			out.println("<tr class=tr_input>");
			out.println("<td width='10%' ID=VDATE>From</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
		out.println("</td>");
			
			out.println("<td width='3%' ID=VDATE>To</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");	
			
			out.println("<tr class=tr_input>");
			out.println("<td width='10%' >Stamp Duty</td>");	
			out.println("<td width='20%'><select name='TXT_STMP_DUTY' class='txt_input' style=\"width:100px;\" >");
			out.println("<option value=\"FAC\" >Facility</option>");
		  out.println("<option value=\"REC\" >Receipts</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("<td width='3%' >&nbsp;</td>");
			out.println("<td width='20%'>&nbsp;</td>");
			out.println("<td width='20%' align=\"left\"><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_detail()\"></td>"); 
		  out.println("<td width='*%'></td>");
			out.println("</tr>");	
			out.println("</table>");			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){  
			
			String m_from_date  = req.getParameter("from_date");
			String m_to_date    = req.getParameter("to_date");	
			String m_stamp_type = req.getParameter("stmp_type");
			String m_header     = "";
			double m_tot_amt    = 0.0;
			double m_tot_stmp   = 0.0;
			
			if(m_stamp_type.equals("REC")){
			m_header = "Receipts";
			}else{
			m_header = "Facility";
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Stamp Duty Report</TITLE>"); 
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
			out.println("<tr><td align='center'  style='height: 18px'><b>Stamp Duty Report - "+m_header+" - From "+m_from_date+" To "+m_to_date+"</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

		
			int j = 1;	
			if(m_stamp_type.equals("REC")){
			
			pstmt = conn.prepareStatement(" SELECT DISTINCT A.REC_NO, "+//1
																    " DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash',INITCAP(A.SETTLE_MODE)) , "+//2
																    " TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY'),"+//3
																    " NVL(A.REC_AMOUNT,0), "+//4
																    " NVL(B.FINANCE_NO,'-') "+   //5   
																    " FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A, "+
																		"      "+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B "+
																    " WHERE A.REC_NO = B.REC_NO "+
																    " AND  A.EFF_VALDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																    " AND  A.EFF_VALDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																    " ORDER BY  TO_CHAR(A.EFF_VALDATE,'DD-MM-YYYY')");			
			
			
		  																		
		 rs=pstmt.executeQuery();
			
	    double m_rec_amount = 0.0;
			double m_stmp_duty  = 0.0;
			
			
			boolean more=rs.next();
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width=100% align='center'><font color=red>No Data Found...!</font></td><tr>");
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='5%' align='left'>No</td>");
			out.println("<td width='10%' align='left'>Date</td>");
			out.println("<td width='10%' align='left'>Type</td>"); 			 
			out.println("<td width='15%' align='left'>Receipt No</td>"); 
			out.println("<td width='15%' align='left'>Contract No</td>"); 
			out.println("<td width='15%' align='right'>Amount</td>"); 
			out.println("<td width='15%' align='right'>Stamp Duty Paid</td>"); 
			out.println("</tr>");
			
			}
			
			while(more){
			
			m_rec_amount = rs.getDouble(4);
			
			if(m_rec_amount >= 1000 && m_rec_amount<50000){
			   m_stmp_duty  = m_rec_amount*(0.1/100);
				}
			else if(m_rec_amount>=50000){
			   m_stmp_duty  = 50.0;	
			} 
			else{
			   m_stmp_duty  = 0.0;
			}			
				
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input >");
			}
			else{
      	out.println("<tr class=tr_input1 >");
			}
			out.println("<td width='5%' align='left'>"+j+"</td>");
			out.println("<td width='10%' align='left'>"+rs.getString(3)+"</td>");
			out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>"); 			 
			out.println("<td width='20%' align='left'>"+rs.getString(1)+"</td>");
			out.println("<td width='20%' align='left'>"+rs.getString(5)+"</td>");
			out.println("<td width='10%' align='right'>"+nf.format(rs.getDouble(4))+"</td>"); 
			out.println("<td width='10%' align='right'>"+nf.format(Math.round(m_stmp_duty))+"</td>");
			out.println("</tr>");
		
			m_tot_amt  = m_tot_amt  + rs.getDouble(4);
			m_tot_stmp = m_tot_stmp + Math.round(m_stmp_duty);
			more=rs.next(); 
			j=j+1;
			} 
      out.println("<tr>");
			out.println("<td colspan=5 align='right'><b>Total</td>");
			out.println("<td align='right'><b>"+nf.format(m_tot_amt)+"</td>");
			out.println("<td align='right'><b>"+nf.format(m_tot_stmp)+"</td>");
			out.println("</tr>");
			
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
    
		
		else if(m_stamp_type.equals("FAC")){		

		      rs = stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
																	" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),"+//2
																	//" "+m_schema_name+".AF_CO_GET_APP_FINANCE_AMT(A.APPLICATION_NO), "+//3
																	" (SELECT SUM(E.CAPITAL_AMOUNT+E.INTEREST_AMOUNT) "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT E,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS F "+
																	" WHERE E.APPLICATION_NO=F.APPLICATION_NO "+
																	" AND F.FINANCE_NO=A.FINANCE_NO "+
																	" GROUP BY A.APPLICATION_NO), "+//3
																	" INITCAP(C.LOCATION_CODE), "+//4
																	//" NVL(B.GRENTAL_AMOUNT,0), "+//5
																	" '' ,"+
																	" "+m_schema_name+".AF_CO_GET_NO_OF_INSTALMENT(A.APPLICATION_NO), "+//6
																	" "+m_schema_name+".AF_CO_MAS_ASSET_DESC(A.APPLICATION_NO), "+//7
																	" A.APPLICATION_NO "+//8																	
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
																	"      "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B, "+
																	"      "+m_schema_name+".CO_CO_MAS_EMPLOYEE C "+
																	" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
																	" AND   A.COLLECTION_OFFICER = C.EMP_CODE "+
																	" AND A.ACTIVATED_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																	" AND A.ACTIVATED_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
																	" ORDER BY TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY') DESC ");			
			
						
	    double m_rec_amount = 0.0;
			double m_stmp_duty = 0.0;
			
			boolean more=rs.next();
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width=100% align='center'><font color=red>No Data Found...!</font></td><tr>");
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='5%' align='left'>No</td>");
			out.println("<td width='15%' align='left'>Contract No</td>"); 
			out.println("<td width='20%' align='left'>Type Of Asset</td>"); 
			out.println("<td width='10%' align='left'>Branch</td>");
			out.println("<td width='10%' align='left'>Date Of Contract</td>"); 			 
			out.println("<td width='15%' align='right'>Facility Amount</td>"); 			
			out.println("<td width='15%' align='right'>Rental(Without VAT)</td>"); 
			out.println("<td width='10%' align='left'>No of Periods</td>"); 
			out.println("<td width='15%' align='right'>S.D.Liable Amount</td>"); 
			out.println("<td width='15%' align='right'>Stamp Duty Compound</td>"); 
			out.println("</tr>");
			
			
			
			while(more){
			
			rs1 = stmt1.executeQuery(" SELECT REG_NO,'','', "+
													" MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,PRICING_NO,SUB_MODEL_CODE"+
													" FROM "+
													" (SELECT NVL(B.REG_NO,'-') REG_NO, "+
													" nvl(B.ENGINE_NO,'-') ENGINE_NO, "+
													" nvl(B.CHASSIS_NO,'-') CHASSIS_NO, "+
													" B.MODEL_CODE, "+
													" INITCAP(C.MAKE_DESC||' '||D.DESCRIPTION||' '||H.DESCRIPTION) MODEL_DESC, "+ 
													" NVL(D.YEAR_OF_MANUFACTURE,'') YEAR_OF_MANUFACTURE, "+
													" B.PRICING_NO, "+
													" NVL(B.SUB_MODEL_CODE,' ') SUB_MODEL_CODE, "+
												  " INITCAP(H.DESCRIPTION) ITEM_SUB_CAT_DESC, "+
													" C.MAKE_CODE, "+
													" F.ITEM_SUB_CAT, "+
													" UPPER(E.VENDOR_CODE), "+
													" UPPER(E.BRANCH), "+
													" INITCAP(G.NAME) "+													
													" FROM "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS A, "+
													" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
													" "+m_schema_name+".AF_CO_MAS_MAKE C, "+
													" "+m_schema_name+".AF_CO_MAS_SUB_MODLE D, "+
													" "+m_schema_name+".AF_CO_MAS_VENDOR_LOCATION E, "+
													" "+m_schema_name+".AF_CO_MAS_MODEL F , "+
													" "+m_schema_name+".AF_CO_MAS_VENDORS G , "+
													" "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY H "+
													" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
													" A.ACTIVE_STATUS='Y' AND "+
													" B.ACTIVE_STATUS='Y' AND "+
													" A.APPLICATION_NO=UPPER('"+rs.getString(8)+"') AND "+
													" A.ASSET_ID=B.ASSET_ID AND "+	
													" C.MAKE_CODE=(SELECT "+
													" MAKE_CODE "+
													" FROM "+m_schema_name+".AF_CO_MAS_MODEL "+
													" WHERE "+
													" MODEL_CODE IN ( SELECT "+
													" MODEL_CODE "+
													" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS "+
													" WHERE INVOICE_NO=B.INVOICE_NO AND B.ACTIVE_STATUS='Y' "+
													" )) AND "+
													" D.SUB_CODE=B.SUB_MODEL_CODE AND "+
													" UPPER(E.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
													" UPPER(G.VENDOR_CODE) =UPPER(B.VENDOR_CODE) AND "+
													" UPPER(F.ITEM_SUB_CAT) =UPPER(H.ITEM_SUB_CAT) AND "+
													" UPPER(E.BRANCH)=UPPER(B.BRANCH_ID) AND "+
													" B.MODEL_CODE=F.MODEL_CODE)  "+
													" GROUP BY REG_NO,PRICING_NO,MODEL_CODE,MODEL_DESC,YEAR_OF_MANUFACTURE,SUB_MODEL_CODE"); 
			
			
			int i = 1;
			
			m_rec_amount = rs.getDouble(3);
			
			if(m_rec_amount >= 1000 ){
			   m_stmp_duty  = m_rec_amount*(0.1/100);
				}
			else{
			   m_stmp_duty  = 0.0;
			}			
				
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input valign=top>");
			}
			else{
      	out.println("<tr class=tr_input1 valign=top>");
			}
			out.println("<td width='5%'  align='left'>"+j+"</td>");
			out.println("<td width='15%' align='left'>"+rs.getString(1)+"</td>");
			
			out.println("<td width='20%' align='left'>");
			
			if(j>0 && j%2==1){
      	out.println("<table class=tr_input valign=top>");
			}
			else{
      	out.println("<table class=tr_input1 valign=top>");
			}			
			
			while(rs1.next()){			
			out.println("<tr ><td align='left'>"+i+"."+rs1.getString(5)+"</td></tr>");
			i++;
			}
			out.println("</table>");
			out.println("</td>");
			
			out.println("<td width='10%' align='left'>"+rs.getString(4)+"</td>");
			out.println("<td width='10%' align='left'>"+rs.getString(2)+"</td>");
			out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='15%' align='right'>");
			
			String sql_rent_new="   SELECT  "+
			" TO_NUMBER(INSTALLMENT_NO) , "+//1
			" SUM(NET_RENTAL_AMOUNT),  "+//2
			" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
			" SUM(GRENTAL_AMOUNT), "+//4
			" TO_CHAR(RENTAL_DATE,'Month') || TO_CHAR(RENTAL_DATE,'YYYY') RENTAL_DATE "+//5
			" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT  "+
			" WHERE  APPLICATION_NO=UPPER('"+rs.getString(8)+"')  "+
			" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE,APPLICATION_NO"+
			" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
		
				
					int end=0;
					int start=0;
					String start_date="";
					String end_date="";
					String m_ins="";
					double m_rental_new=0;
					double m_vat_new=0;
					double m_gross_new=0;
					int count_period=0;
					
					rs_rental = stmt_rental.executeQuery(sql_rent_new);
					
					boolean more3 =rs_rental.next();
					
					if(more3)
									{
								m_rental_new=rs_rental.getDouble(2);
								start=rs_rental.getInt(1);
								m_vat_new=rs_rental.getDouble(3);
								m_gross_new=rs_rental.getDouble(4);
					  		start_date=rs_rental.getString(5);
								end_date=rs_rental.getString(5);
									
									
								while(more3) 
									{
													  									
									
									if(m_rental_new!=rs_rental.getDouble(2))
										{
									     	
												if(j>0 && j%2==1){
      	                 out.println("<table class=tr_input valign=top>");
			                   }
			                    else{
      	                out.println("<table class=tr_input1 valign=top>");
			                   }			
												if(start==end)
									       {																			
													out.println(" <tr><td >"+count_period+" ("+start_date+" - "+end_date+") "+nf.format(m_rental_new)+" </td>");
													}													
													else
										      {
													out.println(" <tr><td >"+count_period+" ("+start_date+" - "+end_date+") "+nf.format(m_rental_new)+" </td>");
																										
													}			
													
												
													out.println("</tr></table>"); 
													
									        start=rs_rental.getInt(1);		
													start_date=rs_rental.getString(5);
												  m_rental_new=rs_rental.getDouble(2);
													m_vat_new=rs_rental.getDouble(3);
								          m_gross_new=rs_rental.getDouble(4);
													count_period=0;
										}
											
								  count_period=count_period+1;
								  end=rs_rental.getInt(1);
									end_date=rs_rental.getString(5);
															
									more3=rs_rental.next();
									
									if(!more3)
										{
									break;
									  }
																					
									}
									
												if(j>0 && j%2==1){
										      	out.println("<table class=tr_input valign=top>");
													}
													else{
										      	out.println("<table class=tr_input1 valign=top>");
													}			
													if(start==end)
									        {
													out.println(" <tr ><td >"+count_period+" ("+start_date+" - "+end_date+") "+nf.format(m_rental_new)+"</td>");
													}
													
													else
									        {
													out.println(" <tr ><td >"+count_period+" ("+start_date+" - "+end_date+") "+nf.format(m_rental_new)+"</td>");
													}													
													
													out.println("</tr></table>"); 
								
								} 
								

			
			out.println("</td>"); 			
			out.println("<td width='10%' align='left'>"+rs.getInt(6)+" Months</td>");
			out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(3))+"</td>");
			out.println("<td width='15%' align='right'>"+nf.format(Math.round(m_stmp_duty))+"</td>");
			
			out.println("</tr >");
			m_tot_amt  = m_tot_amt  + rs.getDouble(3);
			m_tot_stmp = m_tot_stmp + Math.round(m_stmp_duty);
			more=rs.next(); 
			j=j+1;
			} 
      out.println("<tr>");
			out.println("<td colspan=8 align='right'><b>Total</td>");
			out.println("<td align='right'><b>"+nf.format(m_tot_amt)+"</td>");
			out.println("<td align='right'><b>"+nf.format(m_tot_stmp)+"</td>");
			out.println("</tr>");
			out.println("</table>");	
			}
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			rs.close();
			conn.close();
			out.flush();
			out.close();
			}
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


