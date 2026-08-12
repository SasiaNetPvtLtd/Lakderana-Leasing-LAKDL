
//CREATED BY DINETH
//ON 2009-01-16
//SCREEN NAME:AF_MISF_TERMINATION_REPORT

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Termination_Report_New extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt1,stmt,stmt2;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try {
		nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt1 = conn.createStatement();
			stmt  = conn.createStatement();
			stmt2 = conn.createStatement();
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username 						= con_method.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 

			ServletOutputStream out = res.getOutputStream(); 


			String m_screen_type= req.getParameter("chksql");

			if(m_screen_type.trim().equals("main_page")){
		      //out.println(m_screen_type);
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
					out.println("help_box.innerHTML=\"  Termination Report  - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\"  Termination Report  - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
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
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Termination_Report_New?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
					
					
					out.println("function load_screen_status(m_val){"); 			
					out.println("if(m_val==\"HELP\"){"); 
					out.println("load_help_msg();"); 
					out.println("}"); 			
					out.println("}"); 
					
			    out.println("function run_report(){");
					out.println("from_date = document.Form1.TXT_FROM_DAY.value+'-'+document.Form1.TXT_FROM_MONTH.value+'-'+document.Form1.TXT_FROM_YEAR.value;");
					out.println("to_date = document.Form1.TXT_TO_DAY.value+'-'+document.Form1.TXT_TO_MONTH.value+'-'+document.Form1.TXT_TO_YEAR.value;");
					out.println("term_status = document.Form1.DRP_TERM_STATUS.value;");
					out.println("term_type = document.Form1.DRP_TERM_TYPE.value;");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termination_Report_New?chksql=run_report&termination_status=\"+term_status+\"&termination_type=\"+term_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\" \";");
					out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					out.println("}");
					
					out.println("function load_details(){ ");	
					out.println("from_date = document.Form1.TXT_FROM_DAY.value+'-'+document.Form1.TXT_FROM_MONTH.value+'-'+document.Form1.TXT_FROM_YEAR.value;");
					out.println("to_date = document.Form1.TXT_TO_DAY.value+'-'+document.Form1.TXT_TO_MONTH.value+'-'+document.Form1.TXT_TO_YEAR.value;");
					out.println("term_status = document.Form1.DRP_TERM_STATUS.value;");
					out.println("term_type = document.Form1.DRP_TERM_TYPE.value;");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termination_Report_New?chksql=MAIN&termination_status=\"+term_status+\"&termination_type=\"+term_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\" \";");
					out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					out.println(" } ");	
					
					
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
					out.println("     document.Form1.TXT_FROM_DAY.value=v_date;");
					out.println("     document.Form1.TXT_FROM_MONTH.value=v_month;");
					out.println("     document.Form1.TXT_FROM_YEAR.value=val;");
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
					out.println("     document.Form1.TXT_TO_DAY.value=v_date;");
					out.println("     document.Form1.TXT_TO_MONTH.value=v_month;");
					out.println("     document.Form1.TXT_TO_YEAR.value=val;");
					out.println("date2=document.Form1.TXT_TO_DAY.value+'-'+document.Form1.TXT_TO_MONTH.value+'-'+document.Form1.TXT_TO_YEAR.value;");
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
					out.println("document.Form1.TXT_FROM_DAY.value   =\""+m_date_dd+"\"");
					out.println("document.Form1.TXT_FROM_MONTH.value =\""+m_date_mm+"\"");
					out.println("document.Form1.TXT_FROM_YEAR.value  =\""+m_date_yy+"\"");
					out.println("document.Form1.TXT_TO_DAY.value   =\""+m_date_dd+"\"");
					out.println("document.Form1.TXT_TO_MONTH.value =\""+m_date_mm+"\"");
					out.println("document.Form1.TXT_TO_YEAR.value  =\""+m_date_yy+"\"");
					out.println("}");
			
			
      		out.println("function check_date(objdd,objmm,objyy) {");						
					out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
					out.println("  checkMonthLength(objdd,objmm,objyy);");			
     		  out.println("}");
					out.println("}");
			    
					
					out.println(" function disable_run(){");//Modified By Sandun on 03-08-2009
					//out.println("if(document.Form1.DRP_TERM_TYPE.value=='Normal-Termination'){");
					out.println("if(document.Form1.DRP_TERM_TYPE.value=='NOR_TER'){");
					out.println("document.Form1.BUT_VIEW1.disabled=false;");
					out.println("}");
					//out.println("if(document.Form1.DRP_TERM_TYPE.value=='Other-Termination'){");
					out.println("else{");
					out.println("document.Form1.BUT_VIEW1.disabled=true;");
					out.println("}");
					out.println("}");
					out.println("</Script>");
			
					out.println("<body onload=\"get_sys_date();disable_run();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
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
					out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report</td>");
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
					
					/****
					out.println("<table align='center' width='100%' class='table' border=0>"); 
  				out.println("<tr class=tr_input>");
					out.println("<td width='20%' ID=DIV_FROM_DATE>From</td>");
					out.println("<td width='30%' ><input name=\"TXT_FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_FROM_DAY,document.Form1.TXT_FROM_MONTH,document.Form1.TXT_FROM_YEAR)> ");
					out.println("    <input name=\"TXT_FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_FROM_DAY,document.Form1.TXT_FROM_MONTH,document.Form1.TXT_FROM_YEAR)> ");
					out.println("    <input name=\"TXT_FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_FROM_DAY,document.Form1.TXT_FROM_MONTH,document.Form1.TXT_FROM_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
			
					out.println("<td width='3%' ID=DIV_TO_DATE>To</td>");
					out.println("<td width='20%' ><input name=\"TXT_TO_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_TO_DAY,document.Form1.TXT_TO_MONTH,document.Form1.TXT_TO_YEAR)> ");
					out.println("    <input name=\"TXT_TO_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_TO_DAY,document.Form1.TXT_TO_MONTH,document.Form1.TXT_TO_YEAR)> ");
					out.println("    <input name=\"TXT_TO_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.TXT_TO_DAY,document.Form1.TXT_TO_MONTH,document.Form1.TXT_TO_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='*%'></td>");
					out.println("</tr>");	
			
					out.println("<tr class=tr_input>");
					out.println("<td width='20%' >Status</td>");	
					out.println("<td width='30%'><select name='DRP_TERM_STATUS' class='txt_input' style=\"width:120px;\" >");
					out.println("<option value=\"TERMINATED\" >Terminated</option>");
		  		out.println("</select>");
					out.println("</td>");
					out.println("<td width='3%' >&nbsp;</td>");
					out.println("<td width='20%'>&nbsp;</td>");
					out.println("<td width='20%' align=\"left\">&nbsp;</td>"); 
		  		out.println("<td width='*%'></td>");
					out.println("</tr>");	
					
					 rs = stmt.executeQuery(" SELECT A.TERMINATION_TYPE, A.TERMINATION_DESC "+//Added By Sandun on 03-08-2009
                                  " FROM   "+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE A "+
																	" ORDER BY TERMINATION_DESC ");
					
					out.println("<tr class=tr_input>");
					out.println("<td width='20%' >Termination Type</td>");	
					out.println("<td width='30%'><select name='DRP_TERM_TYPE' class='txt_input' style=\"width:120px;\" onchange=\"disable_run();\">");
					out.println("<option value=\"ALL\" selected>All</option>");
					while(rs.next()){
					out.println("<option value=\""+rs.getString(1)+"\" >"+rs.getString(2)+"</option>");
					}
					out.println("</select>");
					out.println("</td>");
					out.println("<td width='3%' >&nbsp;</td>");
					out.println("<td width='20%'>&nbsp;</td>");
					out.println("<td width='20%' align=\"left\"><input class='but_input' style='width:100px' type='button' name='BUT_VIEW1' value=\"Run Report\" onClick=\"run_report()\">&nbsp;<input class='but_input' style='width:100px' type='button' name='BUT_VIEW2' value=\"View Report\" onClick=\"load_details()\"></td>"); 
		  		out.println("<td width='*%'></td>");
					out.println("</tr>");
					out.println("</table>");			
			    *****/
					
					
					
			    out.println("<table class='table' width='100%'  >"); 
			    out.println("<tr >"); 
			    out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
			    out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" >");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\"  value=\"\" >");	
			    out.println("</td> ");
			    out.println("<td width='10%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>To Date</DIV></td>"); 
			    out.println(" <TD WIDTH=\"50%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" >");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" >");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_1' value=\"Run Report\" onClick=\"run_report()\"><input class='but_input' type='button' style='{width:150;}'  name='BUT_HELP_MAIN_2' value=\"View Report\" onClick=\"view_details()\">");	
			    out.println("</td> ");
			    out.println("</table>");
							
					rs = stmt.executeQuery(" SELECT A.TERMINATION_TYPE, A.TERMINATION_DESC "+//Added By Sandun on 03-08-2009
                                  " FROM   "+m_schema_name+".AF_CO_MAS_TERMINATION_TYPE A "+
																	" ORDER BY TERMINATION_DESC ");
					
					out.println("<tr class=tr_input>");
					out.println("<td width='20%' >Termination Type</td>");	
					out.println("<td width='30%'><select name='DRP_TERM_TYPE' class='txt_input' style=\"width:120px;\" onchange=\"disable_run();\">");
					out.println("<option value=\"ALL\" selected>All</option>");
					while(rs.next()){
					out.println("<option value=\""+rs.getString(1)+"\" >"+rs.getString(2)+"</option>");
					}
					out.println("</select>");
					out.println("</td>");
					out.println("<td width='3%' >&nbsp;</td>");
					out.println("<td width='20%'>&nbsp;</td>");
					out.println("<td width='20%' align=\"left\"><input class='but_input' style='width:100px' type='button' name='BUT_VIEW1' value=\"Run Report\" onClick=\"run_report()\">&nbsp;<input class='but_input' style='width:100px' type='button' name='BUT_VIEW2' value=\"View Report\" onClick=\"load_details()\"></td>"); 
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
			String m_term_status = req.getParameter("termination_status");
			String m_term_type  = req.getParameter("termination_type");
			String m_application_no = "";
			String m_term_type_1 = "";
			/*out.println(m_from_date);
			out.println(m_to_date);
			out.println(m_term_status);*/
			//if(m_term_status.equals("TERMINATED") && m_term_type.trim().equals("Normal-Termination")){
			if(m_term_status.equals("TERMINATED") && m_term_type.trim().equals("NOR_TER")){//Modified By Sandun on 03-08-2009
			
			/*rs1=stmt1.executeQuery(" SELECT A.TERMINATION_NO, "+
			                       " A.FINANCE_NO, "+
			                       " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+
			                       //" AMOUNT-NVL(INVOICED_RENTALS,0), "+//Commented by Dineth on 2009-01-26
														 " NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_AMOUNT,0)+NVL(A.CHARGES,0), "+
			                       " NVL("+m_schema_name+".AF_CO_GET_TERM_SET_AMT(A.TERMINATION_NO),0), "+
			                       " NVL("+m_schema_name+".AF_CO_GET_TERM_BAL_AMT(A.TERMINATION_NO),0), "+
														 " NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(A.TERMINATION_TYPE),'-'), "+
														 " A.CLIENT_CODE, "+
														 " B.APPLICATION_NO "+
                             " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                             " WHERE TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                             " AND TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
                             " AND A.ACTIVE_STATUS<>'CANCEL' "+
														 " AND A.FINANCE_NO=B.FINANCE_NO "+
														 " AND "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(B.APPLICATION_NO)='Normal-Termination' "+
                             " AND (B.APPLICATION_STATUS='TERMI' OR B.APPLICATION_STATUS='TERMINATED')");*/
											
			/*rs1=stmt1.executeQuery(" SELECT A.APPLICATION_NO,A.FINANCE_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),A.CLIENT_CODE, "+
														 " TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),SUM(B.BALANCE_TO_BE_RECEIVED)+NVL("+m_schema_name+".AF_CO_GET_ODI_DUE(A.FINANCE_NO),0) "+
                             " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
                             " WHERE TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                             " AND TO_DATE(TO_CHAR(B.VALUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
														 " AND A.FINANCE_NO=B.FINANCE_NO "+
                             " AND B.INVOICE_NO IN (SELECT  INVOICE_NO "+ 
                             "                      FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
                             "                      WHERE   TO_NUMBER(INSTALLMENT_NO)=( "+
                             "                                             SELECT  MAX(TO_NUMBER(C.INSTALLMENT_NO)) "+
                             "                                             FROM    "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT C "+
                             "                                             WHERE   APPLICATION_NO=A.APPLICATION_NO) "+
                             "                                              AND     APPLICATION_NO=A.APPLICATION_NO) "+
                             " AND UPPER("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(APPLICATION_NO))=UPPER('NORMAL-TERMINATION') "+
                             " GROUP BY A.APPLICATION_NO,A.FINANCE_NO,A.CLIENT_CODE,B.VALUE_DATE ");*/
															
				rs1=stmt1.executeQuery("SELECT A.FINANCE_NO,A.CLIENT_CODE,A.CLIENT_NAME,A.TOTAL_BALANCE_AMOUNT FROM "+m_schema_name+".AF_TBD_TEMP_NORMAL_TERM_TAB A");
				
				
			}
			//if(m_term_status.equals("TERMINATED") && m_term_type.trim().equals("Other-Termination")){
			if(m_term_status.equals("TERMINATED") && !m_term_type.trim().equals("NOR_TER")){//Modified By Sandun 03-08-2009
			
			if(m_term_type.equals("ALL")){//Added By Sandun on 03-08-2009
			rs1=stmt1.executeQuery(" SELECT A.TERMINATION_NO, "+
			                       " A.FINANCE_NO, "+
			                       " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+
			                       //" AMOUNT-NVL(INVOICED_RENTALS,0), "+//Commented by Dineth on 2009-01-26
														 " NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0), "+
			                       //" NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0)+NVL("+m_schema_name+".AF_CO_GET_ADJUSTED_AMT(A.TERMINATION_NO),0)-NVL("+m_schema_name+".AF_CO_GET_TERM_BAL_AMT(A.TERMINATION_NO),0), "+//Commented by Dineth on 09-06-2009
			                       //" NVL("+m_schema_name+".AF_CO_GET_TERM_BAL_AMT(A.TERMINATION_NO),0), "+//Commented by Dineth on 09-06-2009
														 " NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0)+NVL("+m_schema_name+".AF_CO_GET_ADJUSTED_AMT(A.TERMINATION_NO),0)-NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),'"+m_username+"'),0), "+//Added by Dineth on 09-06-2009
														 " NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),'"+m_username+"'),0), "+//Added by Dineth on 09-06-2009
														 //" NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(A.TERMINATION_TYPE),'-'), "+
															" NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(B.TER_TYPE),'-'), "+	
														 " A.CLIENT_CODE, "+
														 " B.APPLICATION_NO "+
                             " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                             " WHERE TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                             " AND TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
                             " AND A.ACTIVE_STATUS = 'TERM_CHECK' "+
														 " AND A.APPLICATION_NO=B.APPLICATION_NO ");
														// " AND "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(B.APPLICATION_NO)<>'Normal-Termination' ");
                            // " AND (B.APPLICATION_STATUS='TERMI' OR B.APPLICATION_STATUS='TERMINATED')"+
														
			}
			else{
			
			
			rs1=stmt1.executeQuery(" SELECT A.TERMINATION_NO, "+
			                       " A.FINANCE_NO, "+
			                       " NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE),'-'), "+
			                       //" AMOUNT-NVL(INVOICED_RENTALS,0), "+//Commented by Dineth on 2009-01-26
														 " NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0), "+
			                       //" NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0)+NVL("+m_schema_name+".AF_CO_GET_ADJUSTED_AMT(A.TERMINATION_NO),0)-NVL("+m_schema_name+".AF_CO_GET_TERM_BAL_AMT(A.TERMINATION_NO),0), "+//Commented by Dineth on 09-06-2009
			                       //" NVL("+m_schema_name+".AF_CO_GET_TERM_BAL_AMT(A.TERMINATION_NO),0), "+//Commented by Dineth on 09-06-2009
														 " NVL(A.AMOUNT,0)+NVL(A.DUE_AMOUNT,0)+NVL(A.ODI_NET,0)+NVL(A.CHARGES,0)+NVL("+m_schema_name+".AF_CO_GET_ADJUSTED_AMT(A.TERMINATION_NO),0)-NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),'"+m_username+"'),0), "+//Added by Dineth on 09-06-2009
														 " NVL("+m_schema_name+".AF_CO_GET_CONTRACT_BAL(B.FINANCE_NO,B.CLIENT_CODE,TO_CHAR(TO_DATE('"+m_to_date+"','DD-MM-YYYY'),'DD-MM-YYYY'),'"+m_username+"'),0), "+//Added by Dineth on 09-06-2009
														 //" NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(A.TERMINATION_TYPE),'-'), "+
															" NVL("+m_schema_name+".AF_CO_GET_TERMINATION_DESC(B.TER_TYPE),'-'), "+
														 " A.CLIENT_CODE, "+
														 " B.APPLICATION_NO "+
                             " FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
                             " WHERE TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
                             " AND TO_DATE(TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
                             " AND A.ACTIVE_STATUS = 'TERM_CHECK' "+
														 " AND A.APPLICATION_NO =B.APPLICATION_NO "+
														// " AND "+m_schema_name+".AF_CO_GET_APP_TER_STATUS(B.APPLICATION_NO)<>'Normal-Termination' "+
                            // " AND (B.APPLICATION_STATUS='TERMI' OR B.APPLICATION_STATUS='TERMINATED') "+
														 "	AND B.TER_TYPE = '"+m_term_type+"' ");
															}
			}
			int j=1;
			boolean more2=rs1.next();
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Termination Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			out.println("function show_account_entries(m_fin_no){");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Termination_Report_New?chksql=show_account_entries&FIN_NO=\"+m_fin_no+\"\";");
			out.println("window.open(m_url,'displayWindow4','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');");
			out.println("}");
			
			
			out.println(" function show_termination_approval_done(term_no){");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_display_application_process_report1?chksql=SHOW_TERM_APPROVAL&TERM_NO=\"+term_no+\"\";");
			out.println("window.open(m_url,'displayWindow4','status=0,menubar=0,scrollbars=1,height=450,width=700,resizable=1');");
			out.println("}");
			
			out.println("function show_termination_appr(finance_no,termination_type,termination_no){");
			/*out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_CR_TerminationApprovalReport?chksql=show_report&FINANCE_NO=\"+finance_no+\"&TERMINATION_TYPE=\"+termination_type+\"&TERMINATION_NO=\"+termination_no+\"\";");*/  
			out.println("m_url=\""+m_class_url+"/"+m_schema_name+"_AF_CR_TerminationCalculationReport2?chksql=main_page&FINANCE_NO=\"+finance_no+\"&TERMINATION_TYPE=\"+termination_type+\"&TERMINATION_NO=\"+termination_no+\"\";");  
			out.println("window.open(m_url,'displayWindow4','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
      out.println("}");

			out.println("</script>"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			if(!more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			}
			if(more2){
			
			//if(m_term_type.trim().equals("Other-Termination")){
			if(!m_term_type.trim().equals("NOR_TER")){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report<br><br></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='15%' class='txt_report_column'>TERMINATION NO</td>");  
			out.println("<td width='15%' class='txt_report_column'>FINANCE NO</td>"); 
			out.println("<td width='15%' class='txt_report_column'>CLIENT NAME</td>"); 
			out.println("<td width='15%' class='txt_report_column'>TERMINATION TYPE</td>");
			out.println("<td width='10%' class='txt_report_column'>TERMINATION VALUE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>SETTLE VALUE</td>");
			out.println("<td width='10%' class='txt_report_column'>BALANCE VALUE</td>");
			out.println("<td width='5%' class='txt_report_column'>&nbsp;</td>");
			out.println("<td width='5%' class='txt_report_column'>&nbsp;</td>");
			out.println("</tr >");
			
			while(more2){
			m_application_no=rs1.getString(9);
			rs2=stmt2.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_APP_TER_STATUS('"+m_application_no+"') "+
														 " FROM DUAL");
			if(rs2.next()){
			m_term_type_1=rs2.getString(1);
			}
			//out.println("m_term_type_1--"+m_term_type_1);
			//if(!m_term_type_1.trim().equals("Normal-Termination")){
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input >");
			}
			else{
      	out.println("<tr class=tr_input1 >");
			}
			
			out.println("<TD  align='left' style= cursor:hand;cursor-color:blue onclick=\"show_termination_approval_done('"+rs1.getString(1)+"');\" ><u>"+rs1.getString(1)+"</u></TD>");
			out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_finance_detail_drill('"+rs1.getString(2)+"');\" ><u> "+rs1.getString(2)+"</u></TD>");
			out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_client('"+rs1.getString(8)+"');\"><u>"+rs1.getString(3)+"</u></TD>");
			out.println("<TD  align='left' >"+rs1.getString(7)+"</TD>");
			out.println("<TD  align='right'>"+nf.format(rs1.getDouble(4))+"</TD>");
			out.println("<TD  align='right'>"+nf.format(rs1.getDouble(5))+"</TD>");
			out.println("<TD  align='right'>"+nf.format(rs1.getDouble(6))+"</TD>");
			out.println("<TD  align='center'><input type='button' name=\"btn_dis_"+j+"\" class='but_input' value='Sheet' onclick=\"show_termination_appr('"+rs1.getString(2)+"','"+rs1.getString(7)+"','"+rs1.getString(1)+"')\"></TD>");
			out.println("<TD  align='center'><input type='button' name=\"btn_dis1_"+j+"\" style='width:100px' class='but_input' value='Account Entries' onclick=\"show_account_entries('"+rs1.getString(2)+"');\"></TD>");
			out.println("</tr >"); 
			j=j+1;
			//}
			more2=rs1.next(); 
			 
			}	
			
				
			out.println("</table>"); 
			 		}
					//else if(m_term_type.trim().equals("Normal-Termination")){
					else if(m_term_type.trim().equals("NOR_TER")){
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>");
					out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report<br><br></td>");
					out.println("</tr>");
					out.println("</table>"); 
			
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class='pdn_txtpos2'>");
					 
					out.println("<td width='40%' class='txt_report_column'>FINANCE NO</td>"); 
					out.println("<td width='40%' class='txt_report_column'>CLIENT NAME</td>"); 
					out.println("<td width='20%' class='txt_report_column'>TERMINATION BALANCE AMOUNT</td>"); 
			
					out.println("</tr >");
					
					while(more2){
						/*m_application_no=rs1.getString(1);
						rs2=stmt2.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_APP_TER_STATUS('"+m_application_no+"') "+
														 			 " FROM DUAL");
					if(rs2.next()){
						m_term_type_1=rs2.getString(1);
					}*/
					//if(m_term_type_1.trim().equals("Normal-Termination")){
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
					
					out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_finance_detail_drill('"+rs1.getString(1)+"');\" ><u> "+rs1.getString(1)+"</u></TD>");
					out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_client('"+rs1.getString(2)+"');\"><u>"+rs1.getString(3)+"</u></TD>");
					out.println("<TD  align='right'>"+nf.format(rs1.getDouble(4))+"</TD>");
					out.println("</tr >"); 
					j=j+1; 
						//}
					more2=rs1.next(); 
					
					}	
			
				
			out.println("</table>");
					
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




			}
			else
			if(m_screen_type.equals("run_report")){
			String m_from_date  = req.getParameter("from_date");
			String m_to_date    = req.getParameter("to_date");	
			String m_term_status = req.getParameter("termination_status");
			String m_term_type  = req.getParameter("termination_type");
		
			
			callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
					            "AF_MISF_TEMP_SAVE_NORMAL_TERM(:1,:2,:3);END;");
				  callstmt1.setString(1 ,m_from_date);
          callstmt1.setString(2 ,m_to_date);
          callstmt1.setString(3 ,m_username);
          callstmt1.execute();
			//out.println("Report successfully run");
			//if(m_term_status.equals("TERMINATED") && m_term_type.trim().equals("Normal-Termination")){
			if(m_term_status.equals("TERMINATED") && m_term_type.trim().equals("NOR_TER")){
			   rs1=stmt1.executeQuery("SELECT A.FINANCE_NO,A.CLIENT_CODE,A.CLIENT_NAME,A.TOTAL_BALANCE_AMOUNT FROM "+m_schema_name+".AF_TBD_TEMP_NORMAL_TERM_TAB A");
				
			}
			int j=0;
			boolean more2=rs1.next();
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Termination Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			
			out.println("</script>"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			if(!more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			}
			if(more2){
			// if(m_term_type.trim().equals("Normal-Termination")){
			 if(m_term_type.trim().equals("NOR_TER")){//Modified By Sandun on 03-08-2009
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr>");
					out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Termination Report<br><br></td>");
					out.println("</tr>");
					out.println("</table>"); 
			
					out.println("<table align='center' width='100%' class='table' border=0>"); 
					out.println("<tr class='pdn_txtpos2'>");
					 
					out.println("<td width='40%' class='txt_report_column'>FINANCE NO</td>"); 
					out.println("<td width='40%' class='txt_report_column'>CLIENT NAME</td>"); 
					out.println("<td width='20%' class='txt_report_column'>TERMINATION BALANCE AMOUNT</td>"); 
			
					out.println("</tr >");
					
					while(more2){
						/*m_application_no=rs1.getString(1);
						rs2=stmt2.executeQuery(" SELECT "+m_schema_name+".AF_CO_GET_APP_TER_STATUS('"+m_application_no+"') "+
														 			 " FROM DUAL");
					if(rs2.next()){
						m_term_type_1=rs2.getString(1);
					}*/
					//if(m_term_type_1.trim().equals("Normal-Termination")){
							if(j>0 && j%2==1){
      					out.println("<tr class=tr_input >");
							}
							else{
      					out.println("<tr class=tr_input1 >");
							}
					
					out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_finance_detail_drill('"+rs1.getString(1)+"');\" ><u> "+rs1.getString(1)+"</u></TD>");
					out.println("<TD  align='left' style=cursor:hand;cursor-color:blue onclick=\"show_client('"+rs1.getString(2)+"');\"><u>"+rs1.getString(3)+"</u></TD>");
					out.println("<TD  align='right'>"+nf.format(rs1.getDouble(4))+"</TD>");
					out.println("</tr >"); 
					j=j+1; 
						//}
					more2=rs1.next(); 
					
					}	
			
				
			out.println("</table>");
					
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


			
			}
			else
			if(m_screen_type.equals("show_account_entries")){
			String m_fin_no=req.getParameter("FIN_NO");
			double m_credit_amt=0;
			double m_debit_amt=0;
			double m_total_credit=0;
			double m_total_debit=0;
			rs1=stmt1.executeQuery(" SELECT A.ACC_TYPE_CODE,INITCAP(A.PROC_DESC), "+
                             " TO_CHAR(A.TRNDATE,'DD-MM-YYYY'), "+ 
                             " DECODE(A.DRCR_STATUS,'CR', A.TRNAMOUNT,0), "+
                             " DECODE(A.DRCR_STATUS,'DR', A.TRNAMOUNT,0), "+
														 " (SELECT ACC_TYPE_DESC FROM "+m_schema_name+".CO_FN_MAS_ACCOUNT_CODE WHERE ACC_TYPE_CODE=A.ACC_TYPE_CODE) "+
                             " FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
                             " WHERE A.DOCREFNO='"+m_fin_no+"' "+
                             " AND A.PROC_DESC LIKE '%TERMI%' ");    
															
			boolean more2=rs1.next();
			//out.println(m_fin_no);
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Account Entries</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">");
			out.println("</script>"); 
      out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			if(!more2){
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Account Entries</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
			out.println("</table>");
			}
			if(more2){
			
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>");
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Account Entries For Finance No:"+m_fin_no+"<br><br></td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table' border=\"1\" cellpadding=\"0\" cellspacing=\"0\">"); 
			out.println("<tr>");
			out.println("<td width='15%' class='txt_report_column'>ACCOUNT TYPE CODE</td>");
			out.println("<td width='30%' class='txt_report_column'>ACCOUNT TYPE DESC</td>");
			out.println("<td width='15%' class='txt_report_column'>DESCRIPTION</td>"); 
			out.println("<td width='15%' class='txt_report_column'>TRANSACTION DATE</td>"); 
			out.println("<td width='12%' class='txt_report_column' style='text-align:right'>DEBIT</td>");  
			out.println("<td width='12%' class='txt_report_column' style='text-align:right'>CREDIT</td>"); 
			out.println("</tr>");
			while(more2){
				out.println("<tr>");
				out.println("<td>"+rs1.getString(1)+"</td>");
				out.println("<td>"+rs1.getString(6)+"</td>");
				out.println("<td>"+rs1.getString(2)+"</td>");
				out.println("<td>"+rs1.getString(3)+"</td>");
				out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(5))+"</td>");
				out.println("<td style='text-align:right'>"+nf.format(rs1.getDouble(4))+"</td>");
				out.println("</tr>");
				m_credit_amt=m_credit_amt+rs1.getDouble(4);
				m_debit_amt=m_debit_amt+rs1.getDouble(5);
				more2=rs1.next();
			}
			 out.println("<tr>");
				out.println("<td class='txt_report_column'>Total</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td>&nbsp;</td>");
				out.println("<td style='text-align:right' class='txt_report_column'>"+nf.format(m_credit_amt)+"</td>");
				out.println("<td style='text-align:right' class='txt_report_column'>"+nf.format(m_debit_amt)+"</td>");
				out.println("</tr>");
				out.println("</table>");
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


			
