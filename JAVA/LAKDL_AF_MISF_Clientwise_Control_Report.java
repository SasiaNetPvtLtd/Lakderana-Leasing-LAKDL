//Created By Kanishka Dilshan on 23-10-2014
import java.io.*; 
import javax.servlet.*;   
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*;  


public class LAKDL_AF_MISF_Clientwise_Control_Report extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt_rental;
	public ResultSet rs,rs1,rs2,rs_rental;
	PreparedStatement pstmt,pstmt1,pstmt2;
	java.text.NumberFormat nf;
	java.lang.Math a;
	CallableStatement callstmt1 =null;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			
			
			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();	
			stmt2 = conn.createStatement();	
			stmt_rental = conn.createStatement();	
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
			String m_username=con_method.username;
			
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			
			ServletOutputStream out = res.getOutputStream(); 
			
			
			String m_screen_type= req.getParameter("chksql");
			
			if(m_screen_type.trim().equals("run_report")){	
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date");
				String m_client = req.getParameter("client");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_CO_TBD_CLIENT_CONTROL_RPT(:1,:2,:3,:4);END;");
					callstmt1.setString(1,m_from_date);
					callstmt1.setString(2,m_to_date);
					callstmt1.setString(3,m_client);
					callstmt1.setString(4,m_username);
					callstmt1.execute();
					
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			else if(m_screen_type.trim().equals("main_page")){	
				
				String m_date_dd = "";
				String m_date_mm = "";
				String m_date_yy = "";
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");	
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY1.value!=\"\" && document.Form1.VAL_MONTH1.value!=\"\" && document.Form1.VAL_YEAR1.value!=\"\"){");
				out.println("	if(document.Form1.VAL_DAY2.value!=\"\" && document.Form1.VAL_MONTH2.value!=\"\" && document.Form1.VAL_YEAR2.value!=\"\"){");
				out.println("	  from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("	  to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("	  m_client=document.Form1.CLIENT_CODE.value; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Clientwise_Control_Report?chksql=run_report&to_date=\"+to_date+\"&from_date=\"+from_date+\"&client=\"+m_client+\" \" "); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report();");			
				out.println("		alert('Successfully completed..!');");
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Control Report  - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Control Report  - \"+document.Form1.hid_status.value;"); 
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
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
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
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear 
				out.println("	}");
				
				out.println("	}	"); //
				out.println("}"); 
				out.println(""); 
				
				out.println("function clear_data(IfCount) {");
				out.println(" 	if(IfCount=='1') {");
				out.println(" 		document.Form1.CLIENT_CODE.value='';");
				out.println(" 	}");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_MULTI_MULTI_AF_MISF_display_quotation_report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}");
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}");
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Clientwise_Control_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function load_detail(){ ");
				out.println("rpt_type = document.getElementById('rpt_type').options[document.getElementById('rpt_type').selectedIndex].text;");
				out.println("from_date   = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("to_date     = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("client_code = document.Form1.CLIENT_CODE.value; ");
				out.println("report_type = document.Form1.TXT_RPT_TYPE.value;");
				out.println("if(report_type!=\"\"){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Clientwise_Control_Report?chksql=MAIN&report_type=\"+report_type+\"&from_date=\"+from_date+\"&to_date=\"+to_date+\"&rpt_type=\"+rpt_type+\"&m_client=\"+client_code+\" \";");
				out.println("popupwin=window.open(m_url,'displayWindow1','left=50,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println(" }else{ ");	
				out.println(" alert('Please select a report type..!'); ");
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
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_from_date.value=date1");			
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
				
				out.println("function client_help(){");
				out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
				out.println(" document.Form1.hid_help_type.value='1' ");
				out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
				out.println("}");
				
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
				out.println("}");
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				
				out.println("function makeRequest(obj) {");
				out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
				out.println(" 	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function get_vector(data_vec) {");
				out.println("	if(data_vec.length==0 && document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
				out.println("     	client_help();");
				out.println("	}else if(data_vec.length>0 && document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
				out.println("		document.Form1.CLIENT_CODE.value=data_vec[0]; ");
				out.println("	}");
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
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Control Report</td>");
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
				out.println("<td width='25%' >To&nbsp;&nbsp;&nbsp;<input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='20%'>&nbsp;</td>");
				out.println("<td width='3%' >&nbsp;</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");	
				
				out.println("<tr >"); 
				out.println("<td width='10%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
				out.println("<td width='25%' ><input class='txt_input' type='text' name='CLIENT_CODE'  style='{width=150px}' onblur=\"assignState('M_CLIENT'),makeRequest(document.Form1.CLIENT_CODE)\">&nbsp;<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
				out.println("</td>");
				out.println("<td width='*%'>"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr class=tr_input>");
				out.println("<td width='10%' >Report Type</td>");	
				out.println("<td width='25%'><select name='TXT_RPT_TYPE' class='txt_input' id='rpt_type' style=\"width:200px;\" >");
				out.println("<option value=\"\" selected >---------------Select Type---------------</option>");
				out.println("<option value=\"80023\" >AMI</option>");
				out.println("<option value=\"30007\" >NIBSM</option>");
				out.println("<option value=\"20002\" >Due Lease Rental Receivable</option>");
				out.println("<option value=\"20000\" >Lease Receivables - Non Current</option>");
				out.println("<option value=\"10000\" >Unearned Lease Income - Non Current</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='25%'>&nbsp;</td>");
				out.println("<td width='20%' align=\"left\"><input class='but_input' type='button' style='width:150px' name='BUT_VIEW' value=\"Run Report\" onClick=\"run_report()\">&nbsp;<input class='but_input' style='width:150px' type='button' name='BUT_VIEW' value=\"View Report\" onClick=\"load_detail()\"></td>"); 
				out.println("<td width='3%' >&nbsp;</td>");
				out.println("<td width='*%'></td>");
				out.println("</tr>");	
				out.println("</table>");	
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("</form>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("</body>");			
				out.println("</html>");
				
				
			}else
				if(m_screen_type.equals("MAIN")){  
					
					String m_from_date  = req.getParameter("from_date");
					String m_to_date  = req.getParameter("to_date");
					String m_report_type = req.getParameter("report_type");
					String m_report_desc = req.getParameter("rpt_type");
					String M_CLIENT = req.getParameter("m_client");//ADDED BY KANISHKA DILSHAN ON  23-10-2014
					
					double m_shnew_total=0,m_shold_total=0,m_acc_total=0,m_diff_total=0;
					
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Control Report</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("function mouse_over(id){");
					out.println("document.getElementById(id).style.textDecoration='underline';");
					out.println("}");
					
					out.println("function mouse_out(id){");
					out.println("document.getElementById(id).style.textDecoration='none';");
					out.println("}");			
					
					out.println("function show_transaction_detail(finance_no,client_code){ ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Clientwise_Control_Report?chksql=transaction_detail&finance_no=\"+finance_no+\"&client_code=\"+client_code+\"&acc_code="+m_report_type+"&to_date="+m_to_date+"&from_date="+m_from_date+" \";");
					out.println("popupwin=window.open(m_url,'displayWindow44','left=200,top=100,width=700,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					out.println(" } ");	
					
					out.println("function view_exp_list(finance_no){ ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Clientwise_Control_Report?chksql=exception_list&finance_no=\"+finance_no+\"&to_date="+m_to_date+"&from_date="+m_from_date+" \";");
					out.println("popupwin=window.open(m_url,'displayWindow45','left=200,top=100,width=700,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
					out.println(" } ");	
					
					out.println("</script>"); 
					
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>"); 			
					
					out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
					out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
					out.println("<tr>"); 
					out.println("<td>"); 
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
					out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
					out.println("<tr><td align='center'  style='height: 18px'><b>Control Report - "+m_report_desc+" - From "+m_from_date+" To "+m_to_date+"</td></tr>"); 
					out.println("</table>");  
					out.println("</table>");  
					out.println("</BR>");
					out.println("</BR>");
					
					
					int j = 1;	
					if(m_report_type.equals("80023")){//AMI
						
						pstmt = conn.prepareStatement(" SELECT A.FINANCE_NO, "+//1
							" A.APPLICATION_NO, "+ //2
							" A.CLIENT_CODE, "+ //3
							" A.NA_STATUS, "+ //4
							" NVL(A.AMI,0) , "+ //5
							" NVL(A.AMI_O,0) , "+ //6
							" NVL(A.ACC80023,0) , "+ //7
							" NVL(A.AMI,0)-NVL(A.AMI_O,0)+NVL(A.ACC80023,0), "+ //8
							" NVL(INITCAP("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'-'), "+//9
							" NVL("+m_schema_name+".AF_CO_GET_TER_DATE(A.FINANCE_NO,'"+m_to_date+"'),'-') "+//10
							" FROM "+m_schema_name+".AF_CO_TBD_ACCOUNT_CHECK A "+
							" WHERE  NVL(A.AMI,0)-NVL(A.AMI_O,0)+NVL(A.ACC80023,0) <> 0 "+
							" AND A.PROC_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.PROC_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
					}else if(m_report_type.equals("30007")){//NIBSM
						
						pstmt = conn.prepareStatement(" SELECT A.FINANCE_NO, "+//1
							" A.APPLICATION_NO,  "+//2
							" A.CLIENT_CODE,  "+//3
							" A.NA_STATUS, "+//4
							" NVL(A.NIBSM,0), "+//5
							" NVL(A.NIBSM_O,0), "+//6
							" NVL(A.ACC30007,0)+NVL(A.ACC80022,0), "+//7
							" NVL(A.NIBSM,0)-NVL(A.NIBSM_O,0)+NVL(A.ACC30007,0)+NVL(A.ACC80022,0), "+//8
							" NVL(INITCAP("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'-'), "+//9
							" NVL("+m_schema_name+".AF_CO_GET_TER_DATE(A.FINANCE_NO,'"+m_to_date+"'),'-') "+//10
							" FROM "+m_schema_name+".AF_CO_TBD_ACCOUNT_CHECK A "+
							" WHERE  NVL(A.NIBSM,0)-NVL(A.NIBSM_O,0)+NVL(A.ACC30007,0)+NVL(A.ACC80022,0)<>0 "+
							" AND A.PROC_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.PROC_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
						
					}else if(m_report_type.equals("20002")){//DUE LEASE RENTAL RECEIVABLE
						
						pstmt = conn.prepareStatement(" SELECT A.FINANCE_NO, "+//1
							" A.APPLICATION_NO, "+//2
							" A.CLIENT_CODE, "+//3
							" A.NA_STATUS, "+//4
							" NVL(A.NA_AMOUNT,0), "+//5
							" NVL(A.NA_AMOUNT_O,0),  "+//6
							" NVL(A.ACC20002,0), "+//7
							" NVL(NA_AMOUNT,0)-NVL(NA_AMOUNT_O,0)-NVL(ACC20002,0) DIFF20002, "+//8
							" NVL(INITCAP("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'-'), "+//9
							" NVL("+m_schema_name+".AF_CO_GET_TER_DATE(A.FINANCE_NO,'"+m_to_date+"'),'-') "+//10
							" FROM "+m_schema_name+".AF_CO_TBD_ACCOUNT_CHECK A "+
							" WHERE  NVL(NA_AMOUNT,0)-NVL(NA_AMOUNT_O,0)-NVL(ACC20002,0)<>0  "+
							" AND A.PROC_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.PROC_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
						
					}else if(m_report_type.equals("20000")){//LEASE RECEIVABLE  - NON CURRENT
						
						pstmt = conn.prepareStatement(" SELECT A.FINANCE_NO, "+//1
							" A.APPLICATION_NO,  "+//2
							" A.CLIENT_CODE, "+//3
							" A.NA_STATUS, "+//4
							" NVL(A.FUTURE_RECEIVABLE,0), "+//5
							" NVL(A.FUTURE_RECEIVABLE_O,0),  "+//6
							" NVL(A.ACC20000,0), "+//7
							" NVL(FUTURE_RECEIVABLE,0)-NVL(FUTURE_RECEIVABLE_O,0)-NVL(ACC20000,0) DIFF20000, "+//8
							" NVL(INITCAP("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'-'), "+//9
							" NVL("+m_schema_name+".AF_CO_GET_TER_DATE(A.FINANCE_NO,'"+m_to_date+"'),'-') "+//10
							" FROM "+m_schema_name+".AF_CO_TBD_ACCOUNT_CHECK A "+
							" WHERE  NVL(FUTURE_RECEIVABLE,0)-NVL(FUTURE_RECEIVABLE_O,0)-NVL(ACC20000,0)<>0  "+
							" AND A.PROC_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.PROC_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
						
					}else if(m_report_type.equals("10000")){//UNEARNED INCOME - NON CURRENT
						
						pstmt = conn.prepareStatement(" SELECT A.FINANCE_NO, "+//1
							" A.APPLICATION_NO,  "+//2
							" A.CLIENT_CODE, "+ //3
							" A.NA_STATUS, "+//4
							" NVL(A.UNEARNED_INCOME,0), "+//5
							" NVL(A.UNEARNED_INCOME_O,0), "+//6
							" NVL(A.ACC10000,0),  "+//7
							" NVL(UNEARNED_INCOME,0)-NVL(UNEARNED_INCOME_O,0)+NVL(ACC10000,0) DIFF10000 ,"+//8
							" NVL(INITCAP("+m_schema_name+".AF_CO_GET_APP_TER_STATUS(A.APPLICATION_NO)),'-'), "+//9
							" NVL("+m_schema_name+".AF_CO_GET_TER_DATE(A.FINANCE_NO,'"+m_to_date+"'),'-') "+//10
							" FROM "+m_schema_name+".AF_CO_TBD_ACCOUNT_CHECK A "+
							" WHERE  ABS(NVL(UNEARNED_INCOME,0)-NVL(UNEARNED_INCOME_O,0)+NVL(ACC10000,0))>0.01 "+
							" AND A.PROC_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND A.PROC_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						
					}
					
					rs=pstmt.executeQuery();
					
					
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
						out.println("<td width='15%' align='left'>Finance No</td>");
						out.println("<td width='10%' align='left'>Status</td>");
						out.println("<td width='10%' align='left'>Termination Date</td>");
						out.println("<td width='15%' align='right'>Shedule Amount New</td>"); 			 
						out.println("<td width='15%' align='right'>Shedule Amount old</td>"); 
						out.println("<td width='15%' align='right'>Account Balance</td>"); 
						out.println("<td width='15%' align='right'>Diffrent Amount</td>"); 
						//out.println("<td width='10%' align='center'>Invoice Exception List</td>"); 
						out.println("</tr>");
						
						
						
						while(more){
							
							if(j>0 && j%2==1){
								out.println("<tr class=tr_input >");
							}
							else{
								out.println("<tr class=tr_input1 >");
							}
							out.println("<td width='5%' align='left'>"+j+"</td>");
							out.println("<td width='15%' align='left' onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\" style='cursor:hand' id=\"finance_"+j+"\" onmouseover=\"mouse_over('finance_"+j+"')\" onmouseout=\"mouse_out('finance_"+j+"')\" >"+rs.getString(1)+"</td>");
							out.println("<td width='10%' align='left'>"+rs.getString(9)+"</td>"); 
							out.println("<td width='10%' align='left'>"+rs.getString(10)+"</td>"); 
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(5))+"</td>"); 			 
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(6))+"</td>");
							out.println("<td width='15%' align='right'>"+nf.format(rs.getDouble(7))+"</td>");
							out.println("<td width='15%' align='right' onClick=\"show_transaction_detail('"+rs.getString(1)+"','"+rs.getString(3)+"')\" style='cursor:hand' id=\"diff_"+j+"\" onmouseover=\"mouse_over('diff_"+j+"')\" onmouseout=\"mouse_out('diff_"+j+"')\">"+nf.format(rs.getDouble(8))+"</td>"); 
							//out.println("<td width='10%' align='center'><input type='button' value='View' name='but_exp' class='but_input' onclick=\"view_exp_list('"+rs.getString(1)+"')\"></td>"); 
							out.println("</tr>");
							
							
							m_shnew_total = m_shnew_total+rs.getDouble(5);
							m_shold_total = m_shold_total+rs.getDouble(6);
							m_acc_total = m_acc_total+rs.getDouble(7);
							m_diff_total = m_diff_total+rs.getDouble(8);
							
							more=rs.next(); 
							j=j+1;
						} 
						
						out.println("<tr >");			
						out.println("<td width='40%' colspan= 4 align='right' ><b>Total</td>");
						out.println("<td width='15%' align='right'><b>"+nf.format(m_shnew_total)+"</td>"); 			 
						out.println("<td width='15%' align='right'><b>"+nf.format(m_shold_total)+"</td>");
						out.println("<td width='15%' align='right'><b>"+nf.format(m_acc_total)+"</td>");
						out.println("<td width='15%' align='right'><b>"+nf.format(m_diff_total)+"</td>"); 
						//out.println("<td width='10%' align='right'>&nbsp;</td>");
						out.println("</tr>");
					}
					if(m_report_type.equals("20002")){
						rs1=stmt1.executeQuery(" SELECT SUM(B.BAL_TOBE_RECEIVE) "+
							" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT A,  "+
							" "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT_BAL B  "+
							" WHERE A.REC_NO = B.REC_NO  "+
							" AND  EFF_VALDATE >=  TO_DATE('01-04-2009','DD-MM-YYYY')   "+
							" AND  EFF_VALDATE <=  TO_DATE('"+m_to_date+"','DD-MM-YYYY')   "+
							" AND  BAL_TOBE_RECEIVE <> 0  "+
							" AND  BAL_TOBE_RECEIVE > 0  "+
							" AND  A.STATUS NOT IN('C','CAD','RET')  "+
							" AND  A.CLIENT_CODE  = '"+M_CLIENT+"' "+//ADDED BY KANISHKA DILSHAN ON  23-10-2014
							" AND  A.REC_NO NOT IN (  "+
							" SELECT GROUP_REC_NO  "+
							" FROM "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT  "+
							" WHERE GROUP_REC_NO IS NOT NULL ) ");
						
						
						if(rs1.next()){
							out.println("<tr >");
							out.println("<td width='5%' align='left'></td>");
							out.println("<td width='15%' align='left' ></td>");
							out.println("<td width='10%' align='left' ></td>");
							out.println("<td width='10%' align='left' ></td>");
							out.println("<td width='15%' align='right'></td>"); 			 
							out.println("<td width='15%' align='right' colspan=2><b>Unallocated Receipt Amount</td>");
							out.println("<td width='15%' align='right'><b>"+nf.format(rs1.getDouble(1))+"</td>"); 
							out.println("</tr>");
						}
						
						
					}
					out.println("</table>");	
					
					out.println("<br>");out.println("<br>");out.println("<br>");
					
					
					rs = stmt.executeQuery(" SELECT DISTINCT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') RENTAL_DATE, "+
						"        TO_CHAR(A.RENTAL_DATE,'DD'),TO_CHAR(A.RENTAL_DATE,'MM'),"+
						"        TO_CHAR(A.RENTAL_DATE,'YYYY'),A.CASH_OUT_FLOW, "+
						"        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO, "+
						"        "+m_schema_name+".AF_CO_GET_RESIDUAL_VAL_INV(A.APPLICATION_NO,INSTALLMENT_NO) "+	
						" FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"        "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+//Added By Sandun on 19-10-2009
						" WHERE  A.RENTAL_DATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
						"        B.APPLICATION_NO=A.APPLICATION_NO AND B.APPLICATION_STATUS='ACTIVATED' AND "+
						"        B.APPLICATION_NO   = C.APPLICATION_NO AND "+ //  |
						"        A.PRO_INVOICE_NO   = C.INVOICE_NO AND "+     //  Added By Sandun on 19-10-2009 
						"        C.ACTIVE_STATUS    = 'Y' AND "+              //  |
						"        RENTAL_DATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
						"        A.INVOICE_NO IS NULL AND "+
						"        B.RENTAL_FREEZ ='N' "+ //Added by NS/DJ for remove rental freez contractors
						"        AND B.CLIENT_CODE  = '"+M_CLIENT+"' "+//ADDED BY KANISHKA DILSHAN ON  23-10-2014
						" ORDER BY TO_DATE(RENTAL_DATE,'DD-MM-YYYY') ");
					
					
					boolean more_inv = rs.next();
					out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
					out.println("<tr><td width=100% align='center'><b><u>Exception List From "+m_from_date+" To "+m_to_date+"</td></td>");
					out.println("</table>");
					
					out.println("<br>");
					
					if(!more_inv){
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						out.println("<tr><td width=100% align='center'><font color='red'>No Data Found..!</font></td></td>");
						out.println("</table>");
					}
					
					if(more_inv){
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						
						out.println("<tr class=tr_input>");
						out.println("<td><b>&nbsp;&nbsp;Finance No</td>");
						out.println("<td><b>Rental Amount</td>");
						out.println("<td><b>Rental Date</td>");
						out.println("<td><b>Note</td>");
						out.println("</tr>");
						
						while(more_inv){
							out.println("<tr class=tr_input>");
							out.println("<td >&nbsp;&nbsp;"+rs.getString(1)+"</td>");
							out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
							out.println("<td >"+rs.getString(3)+"</td>");
							if(rs.getString(10)==null){
								out.println("<td ></td>");
							}else{
								if(rs.getString(10).equals("N")){
									out.println("<td >Residual value Not entered</td>");
								}else{
									out.println("<td ></td>");
								}
							}
							out.println("</tr>");
							more_inv = rs.next();	
						}	 
						
						out.println("</table>");
						out.println("<br>");
						out.println("<br>");
					}
					
					rs1 = stmt1.executeQuery(" SELECT A.APPLICATION_NO, "+
						//out.println(" SELECT A.APPLICATION_NO, "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
						" A.FINANCE_NO,  "+
						" A.APPLICATION_STATUS,  "+
						" "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(A.TRANSACTION_TYPE), "+
						" "+m_schema_name+".AF_CO_GET_TERMINATION_TYPE(A.TER_TYPE) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						" WHERE A.APPLICATION_STATUS IN ('ENTERED','ENT_CON','VERIFY1','V-APP','VERIFY-M','VERIFY2','VERIFYL') "+
						" AND A.CLIENT_CODE  = '"+M_CLIENT+"' "+//ADDED BY KANISHKA DILSHAN ON  23-10-2014
						" AND A.APPLICATION_NO IN (SELECT B.APPLICATION_NO "+
						" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B "+
						" WHERE B.ACTIVE_STATUS = 'TERM_CHECK'  "+
						" AND TERMINATION_TYPE IN ('RESCHEDULE','PAR_TER','RENT_REVIS','ENHA_DOWN'))");
					
					
					boolean more1 = rs1.next();
					if(more1){
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						out.println("<tr><td width=100% align='center'><b><u>Application Detail</td></td>");
						out.println("</table>");
						
						out.println("<br>");
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						
						out.println("<tr class=tr_input>");
						out.println("<td><b>&nbsp;&nbsp;Application No</td>");
						out.println("<td><b>Client Name</td>");
						out.println("<td><b>Finance No</td>");
						out.println("<td><b>Application Status</td>");
						out.println("<td><b>Transaction Type</td>");
						out.println("<td><b>Termination Type</td>");
						out.println("</tr>");
						
						while(more1){
							out.println("<tr class=tr_input>");
							out.println("<td >&nbsp;&nbsp;"+rs1.getString(1)+"</td>");
							out.println("<td >"+rs1.getString(2)+"</td>");
							out.println("<td >"+rs1.getString(3)+"</td>");
							out.println("<td >"+rs1.getString(4)+"</td>");
							out.println("<td >"+rs1.getString(5)+"</td>");
							out.println("<td >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more1 = rs1.next();	
						}
					}
					out.println("</table>");
					out.println("<br>");
					out.println("<br>");
					
					
					
					
					
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
				
				else if(m_screen_type.equals("transaction_detail")){
			
			String m_finance_no  = req.getParameter("finance_no");		
			String m_client_code = req.getParameter("client_code");		
			String m_from_date   = req.getParameter("from_date");			
			String m_to_date     = req.getParameter("to_date");		
			String m_acc_code    = req.getParameter("acc_code");		
				
      
				
					int count = 0;
					String m_string="";		
					String m_orient_name="";
					String m_name="";
					String m_cheque_no="";
					String Sql_account="";
					double m_cum_value=0;
					double m_val=0;
					double m_debit=0;
					double m_credit=0;
				
					out.println("<HTML><HEAD><TITLE> Transaction & Account Detais</TITLE></HEAD>");
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					out.println("<Script>");
					
					out.println("function lookup_docref_detail(m_val){");
					out.println("if(m_val.substring(0,2)=='IN'){");
					out.println("show_invoice_info_2(m_val);");
					out.println("}else if(m_val.substring(0,2)=='SR'){");
					out.println("show_settle_receipt_drill(m_val);");
					out.println("}");
					out.println("}");
					
					out.println("</Script>");
					out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<TABLE  WIDTH='100%'  STYLE='{color: black; font: 10pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Transaction Details - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");

								String		Sql_invoice=" SELECT REF_NO,TO_CHAR(DUE_DATE,'DD-MM-YYYY'),AMOUNT,TYP,NVL(CHEQUE_NO,'-') CHEQUE_NO,DESCRIPTION,STATUS,NO "+
					" FROM "+
					" ( "+
					"  SELECT "+
					"  INVOICE_NO REF_NO, "+
					//"  VALUE_DATE   DUE_DATE, "+ // DUE_DATE --modified nuwan de silva 25-07-07 REF NO 708
					"  DECODE(INVOICE_TYPE,'LEGAL_CAP',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),'LEGAL_ARR',(SELECT APPLY_DATE FROM "+m_schema_name+".AF_CR_PRO_LEGAL_TERMINATION WHERE FINANCE_NO='"+m_finance_no+"' AND ACTIVE_STATUS='CONF' ),VALUE_DATE) DUE_DATE ,"+
					"  TOTAL_AMOUNT AMOUNT, "+
					"  'INVOICE' TYP, "+
					"  NVL(NULL,'-') CHEQUE_NO, "+
					"  NVL((SELECT INVOICE_DESC FROM "+m_schema_name+".AF_CO_MAS_INVOICE_RECEIPT_ORD WHERE INVOICE_TYPE_CODE=INVOICE_TYPE),"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(INVOICE_TYPE)) DESCRIPTION , "+// __ Added by nuwan de silva on 05-12-2007
					"  NULL STATUS ,"+
					"  NVL("+m_schema_name+".AF_CO_GET_INSTALLMENT_NO(INVOICE_NO),' ')  NO "+
					"  FROM "+m_schema_name+".AF_CO_PRO_INVOICE   "+
					"  WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"        ACTIVE_STATUS='Y'  "+
					"        ACTIVE_STATUS IN ('Y','DB_CAN','C')  "+
					"   AND INVOICE_TYPE NOT IN ('LEGAL_ODI') "+
					" AND VALUE_DATE <=SYSDATE "+  
					
					"  UNION ALL "+
					" SELECT "+
					" REC_NO REF_NO,  "+
				  " EFF_VALDATE DUE_DATE,  "+
					" SUM(SETTELED_AMOUNT) AMOUNT,  "+
					" 'RECEIPT' TYP,  "+
					" CHEQUE_NO  CHEQUE_NO , "+
					" DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order') || ' Value Date ' || "+m_schema_name+".AF_CO_GET_REC_VAL_DATE(REC_NO) DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					" STATUS,  "+
					" '' NO  "+
					" FROM  "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+//,"+m_schema_name+".AF_CO_PRO_INVOICE C "+
					" WHERE RECEIPT_NO=REC_NO "+
					//" A.INVOICE_NO=C.INVOICE_NO AND "+
					//" C.FINANCE_NO='"+m_finance_no+"' "+
					"	AND B.STATUS NOT IN ('RET','C','CAD') "+

					
					" AND A.INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"                   WHERE FINANCE_NO='"+m_finance_no+"' "+
					"                   AND   ACTIVE_STATUS='Y' "+
          "                   UNION ALL "+
          "                   SELECT ODI_REF_NO "+
          "                   FROM  "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+
          "                   WHERE INVOICE_NO IN (SELECT INVOICE_NO FROM "+m_schema_name+".AF_CO_PRO_INVOICE  "+
          "                   WHERE FINANCE_NO='"+m_finance_no+"' /*AND ACTIVE_STATUS='Y'*/   )) "+ //comment by ns on 23-12-2011
					
					" AND EFF_VALDATE <=SYSDATE "+  
					" GROUP BY REC_NO,EFF_VALDATE,CHEQUE_NO,SETTLE_MODE,STATUS "+
					
					
					
					"	UNION ALL "+
					/*" SELECT "+
					"    INVOICE_NO REF_NO, "+
					"    DUE_DATE  DUE_DATE, "+
					"    ODI_SETTLED_AMOUNT AMOUNT, "+ //ODI_SETTLED_AMOUNT  ODI_CAL_AMOUNT //modified by nuwan de silva on 14-09-07
					"    'ODI' TYP, "+
					"    NULL CHEQUE_NO, "+
					"    'Over Due Interst' DESCRIPTION, "+
					"    NULL STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY "+*/
					
					
					" SELECT "+
					" A.INVOICE_NO REF_NO, "+
					" C.ALLOCATED_DATE DUE_DATE, "+//A.DUE_DATE  DUE_DATE, "+
					" C.SETTELED_AMOUNT AMOUNT, "+//A.ODI_SETTLED_AMOUNT AMOUNT, "+
					" 'ODI' TYP, "+
					" NULL CHEQUE_NO, "+
					" 'Over Due Interst' DESCRIPTION, "+
					" NULL STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+//m_schema_name+".AF_CO_PRO_INVOICE B, "+
					"      "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS C "+
					" WHERE "+//A.ODI_SETTLED_AMOUNT > 0 "+
					//"     A.INVOICE_NO=B.INVOICE_NO "+
					"       C.INVOICE_NO=A.ODI_REF_NO "+
					//" AND B.CLIENT_CODE='"+m_client_code+"'  "+
					" AND SETTELED_AMOUNT <> 0 "+
					" AND A.INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' "+//AND "+ //commented AND  by SH on 13-12-2010
					//"          ACTIVE_STATUS='Y' "+
					//"            ACTIVE_STATUS IN ('Y','DB_CAN')  "+//commented by SH on 13-12-2010
					") "+
					" AND C.ALLOCATED_DATE <=SYSDATE "+ //changed by SH on 29-09-2009 DUE_DATE <=SYSDATE "+   

					
					" UNION ALL "+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note','Debit Note') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					"      AND   INVOICE_TYPE<>'LEGAL_ODI'"+
					" ) "+
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE NOT IN ('LEGAL_AD','LEGAL_ODI','TERM_ODI','ODI') "+ //'LEGAL_ODI','TERM_ODI','ODI' added by ns on 31-05-2011
					" AND ADJUSTED_DATE <=SYSDATE "+  
					
					//--------------------------Sandun on 18-03-2009---------------------------------
					
					" UNION ALL"+
					" SELECT "+
					" INVOICE_NO REF_NO, "+
					" ADJUSTED_DATE DUE_DATE, "+ //ADJUSTED_DATE ///ENT_DATE
					" ADJUSTED_AMOUNT AMOUNT, "+
					" 'DR/CR' TYP, "+
					" NULL CHEQUE_NO, "+
					" DECODE(CREDIT_TYPE,'CR','Credit Note - Legal Termination') DESCRIPTION, "+
					" CREDIT_TYPE STATUS, "+
					"     '' NO "+ //7
					" FROM "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS "+
					" WHERE INVOICE_NO IN "+
					" ( "+
					" SELECT INVOICE_NO "+
					"    FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
					"    WHERE FINANCE_NO='"+m_finance_no+"' AND "+
					//"    ACTIVE_STATUS='Y' "+
					"      ACTIVE_STATUS IN ('Y','DB_CAN')  "+	
					" ) "+					
					" AND   ACTIVE_STATUS IN ('Y','C') "+
					" AND   ADJUST_TYPE = 'LEGAL_AD' "+
					" AND ADJUSTED_DATE <=SYSDATE "+ 
					
					
					" UNION ALL"+
					
					" SELECT B.INVOICE_NO REF_NO, "+
				  " NVL(B.INV_REV_DATE,B.VALUE_DATE) DUE_DATE,  "+
				  " B.TOTAL_AMOUNT AMOUNT,  "+
				  " 'INV_REV' TYP, "+
				  " '-'  CHEQUE_NO ,   "+
				  " 'Invoice Cancel '  DESCRIPTION,     "+
				  " '-'  STATUS,   "+ 
				  " 'CR' STYPE  "+
				  " FROM "+m_schema_name+".AF_CO_PRO_INVOICE B "+
				  " WHERE "+				  
				  " B.FINANCE_NO = '"+m_finance_no+"' "+
				  " AND B.ACTIVE_STATUS = 'C' "+
					" AND VALUE_DATE <= SYSDATE "+
					
					//------------------------------------------------------------
					
					" UNION ALL "+
					
					" SELECT a.rec_no REF_NO , "+
					" b.eff_valdate   DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'BAL' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					//" NULL DESCRIPTION , "+
					" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') || ' Value Date ' || TO_CHAR(VALUE_DATE_ODI,'DD-MM-YYYY')   DESCRIPTION,  "+ //ADDED BY NS 01-10-2009
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	A.REC_NO REF_NO,   "+
					//"	C.ENT_DATE DUE_DATE,  "+ //ADDDE BY NUWAN DE SILVA 03-11-2008
					" B.REALISED_DATE DUE_DATE,"+
					"	NVL(A.BAL_TOBE_RECEIVE,0)  AMOUNT,   "+
					"	'RETURN_DEBIT' TYP,  "+
					"	B.CHEQUE_NO  CHEQUE_NO ,  "+
					"	'Cheque Return '  DESCRIPTION,    "+
					"	B.STATUS STATUS,   "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" ,"+m_schema_name+".AF_CO_PRO_RETURN_DETAILS C "+
					" WHERE A.REC_NO=B.REC_NO "+
					"	AND   A.REC_NO=C.RECEIPT_NO(+) "+
					" AND A.FINANCE_NO='"+m_finance_no+"'"+
					" AND A.BAL_TOBE_RECEIVE <> 0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='RET' "+
					
					
					//ADDED BY NUWAN DE SILVA 10-08-2009
					" UNION ALL "+
					
					" SELECT a.rec_no REF_NO , "+
					" NVL(b.rec_cancel_date,b.eff_valdate)   DUE_DATE ,"+ //				
					" a.bal_tobe_receive ,"+
					"'REC_CAN' TYP, "+
					" CHEQUE_NO CHEQUE_NO ,"+
					" 'Receipt Cancelation' DESCRIPTION , "+
					//" NVL(DECODE(SETTLE_MODE,'CHEQUE',DECODE(STATUS,'RET','Chq Return','Receipt'),'CASH','Cash Receipt','DIR_DEP','Bank Transfer','JVD','Journal Voucher Desc','STD_ORD','Standing Order'),'-') DESCRIPTION,  "+
					" DECODE(STATUS,'RET','RE',STATUS)  STATUS ,"+//modified nuwan de silva
					" '' NO "+ //7
					" FROM "+m_schema_name+".af_co_pro_settl_rec_app_bal a , "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT b "+
					" where a.rec_no=b.rec_no "+
					" and a.finance_no='"+m_finance_no+"'"+
					" and a.bal_tobe_receive <>0 "+
					" AND B.eff_valdate <=SYSDATE "+  
					"	AND B.STATUS='CAD' "+
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	NVL(AMOUNT,0)  AMOUNT,   "+
					"	'DEBIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Debit Note Cancelation'  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_DR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					
					" AND   TRN_DATE <=SYSDATE "+  
					
					
					" UNION ALL "+
					
					"	SELECT    "+
					"	INVOICE_NO REF_NO,   "+
					"	TRN_DATE DUE_DATE,  "+ 
					"	NVL(ADJUSTED_AMOUNT,0)  AMOUNT,   "+
					"	'CREDIT_CANCEL' TYP,  "+
					"	'-'  CHEQUE_NO ,  "+
					"	'Credit Note Cancelation '  DESCRIPTION,    "+
					"	'-'  STATUS,   "+
					" 'CR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_CANCEL_CR_NOTE "+
					" WHERE FINANCE_NO='"+m_finance_no+"' "+
					" AND   TRN_DATE <=SYSDATE "+  
					
					//added by ns 10-08-2009 ------------------------
					" UNION  ALL "+
					" SELECT  "+
					" A.INVOICE_NO REF_NO , "+
					" A.ALLOCATED_DATE DUE_DATE, "+
					" SUM(A.SETTELED_AMOUNT) AMOUNT , "+
					" 'LEGAL_ODI' TYP, "+
					" '-' CHEQUE_NO, "+
					" 'Legal ODI Allocation' DESCRIPTION, "+ 
					" '' STATUS, "+
					" 'DR' STYPE "+
					" FROM "+m_schema_name+".AF_CO_PRO_INVOICE_DETAILS A , "+m_schema_name+".AF_CO_PRO_INVOICE B "+
					" WHERE A.INVOICE_NO=B.INVOICE_NO "+
					" AND   B.FINANCE_NO='"+m_finance_no+"' "+
					" AND   B.INVOICE_TYPE='LEGAL_ODI' "+
					" AND   B.ACTIVE_STATUS='Y' "+
					" AND   A.ALLOCATED_DATE <=SYSDATE "+
					" GROUP BY A.INVOICE_NO, A.ALLOCATED_DATE "+
          //-----------------------------------------------------
					
				
				 " ) "+ 
					
					" ORDER BY DUE_DATE ";
					
				  rs=stmt1.executeQuery(Sql_invoice);
					boolean  more_inv =rs.next();
					String m_application_no="";
															
					if(more_inv){
					
					while(more_inv){
					count++;
					if(count==1){
					out.println("<table align='center' width='100%' class='table'>");
					out.println("<tr>");
					out.println("<td width='*%'align='center' class=div_input><b>"+m_orient_name.toUpperCase()+"</b></td>");
					out.println("</tr>");
				
					String sql_col_status  = " SELECT "+ m_schema_name + ".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),"+
																	 " "+m_schema_name+".AF_CO_GET_APP_STATUS(APPLICATION_NO) "+
																	 " ,APPLICATION_NO "+
																	 ","+m_schema_name+".af_co_get_client_name('"+m_client_code+"') "+
																	 " FROM "+ m_schema_name + ".AF_CO_PRO_APPLICATION_DETAILS" +
																	 " WHERE FINANCE_NO = '"+m_finance_no +"'";
					rs2 = stmt2.executeQuery(sql_col_status);
					boolean more2 = rs2.next();
					if(more2){
					
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Client Name</td><td>: "+rs2.getString(4)+"</td>");
					out.println("</tr>");
					
					
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Collection Officer</td><td>: "+rs2.getString(1)+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='12%' align='left' class=div_input>Application Status</td><td>: "+rs2.getString(2)+"</td>");
					out.println("</tr>");
					m_application_no=rs2.getString(3);
					}
					out.println("<tr>");
					out.println("<td width='100%'align='center' colspan='2' class=div_input><b>Asset Finance Ledger</b></td>");
					out.println("</tr>");
					out.println("</table>");
					
					out.println("<hr color='black'>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' >");
					
					
					out.println("</table>");
					
					out.println("<br>");
					
					out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>Date</td>");
					out.println("<td width='15%' class=div_input>Doc Ref</td>");
					out.println("<td width='30%' class=div_input>Narration</td>");
					out.println("<td width='10%' class=div_input>Reference No.</td>"); //Cheque No.
					out.println("<td width='10%' class=div_input align='right'>Debit</td>");
					out.println("<td width='10%' class=div_input align='right'>Credit</td>");
					out.println("<td width='10%' class=div_input align='right'>Cum. Value</td>");
					out.println("<td width='4%' class=div_input></td>");
					out.println("</tr>");
										

					}
					m_debit =0;
					m_credit=0;
					
					if(rs.getString(4).equals("INVOICE")){
					m_debit=rs.getDouble(3);

					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					if(rs.getString(7).equals("RET")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					}
					
					else if(rs.getString(4).equals("DR/CR")){
					
					if(rs.getString(7).equals("DR")){
					m_debit=rs.getDouble(3);
          }
					else
					{
					m_credit=rs.getDouble(3);
					}
					
					}
					
					else if(rs.getString(4).equals("ODI")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("OTHER")){
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RET_CHARGE")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("RETURN_DEBIT")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("DEBIT_CANCEL")){ //added by nuwan de silva on 14-08-07
					m_credit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("CREDIT_CANCEL")){ //added by nuwan de silva on 14-08-07
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("INV_REV")){ 
					m_credit=rs.getDouble(3);
					}
					//added by ns
					else if(rs.getString(4).equals("LEGAL_ODI")){ 
					m_debit=rs.getDouble(3);
					}
					else if(rs.getString(4).equals("REC_CAN")){ 
					m_debit=rs.getDouble(3);
					}
					
					
					/*else if(rs.getString(4).equals("BAL")){
					m_credit=rs.getDouble(3);
					}*/
					
					else if(rs.getString(4).equals("BAL")){
					/*if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){
					m_debit=rs.getDouble(3);
					}else{
					m_credit=rs.getDouble(3);
					}
					*/
					m_credit=rs.getDouble(3);
					}
					
					m_val=m_debit-m_credit;
					
					if(m_cum_value < 0 && m_credit >0){ //added by nuwan de silva on 10-07-2008 
					m_cum_value=m_cum_value-m_credit;
					}
					else{
					m_cum_value=m_cum_value+m_val;
					}

					//m_cum_value=m_cum_value+m_val;
          					
					if(rs.getString(4).equals("INVOICE")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					else if(rs.getString(4).equals("LEGAL_ODI")){ //added by ns 10-08-2009
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					else if(rs.getString(4).equals("REC_CAN")){ //added by ns 10-08-2009
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					//out.println("<td width='10%' class=div_input>&nbsp;</td>");
					
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}


					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					}
					
					else if(rs.getString(4).equals("RECEIPT")){
					
				 if(rs.getString(7).equals("RET") ){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					//if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
				  m_val=m_debit-m_credit;
          m_cum_value=m_cum_value-m_val;
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					//if(rs.getString(7).equals("RET") ){
					//out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					//}
					//else
					//{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					//}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
          else if(!rs.getString(7).equals("RET") ){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
          //---------------------------------------------------------------------------------------------------
					if(rs.getString(7).equals("CAD")  || rs.getString(7).equals("C")){
					m_debit =0;
					m_credit=0;
					m_debit=rs.getDouble(3);
          				
					m_val=m_debit-m_credit;
					if(m_cum_value>0){  
            m_cum_value=m_cum_value+m_val;
					}else if (m_cum_value<0 ){
					  m_cum_value=m_cum_value-m_val;
					}

					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					}
					//---------------------------------------------------------------------------------------------------
					
					
					}
					//return charges------------
					else if(rs.getString(4).equals("RET_CHARGE")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_return_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					if(rs.getString(7).equals("RET") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					//--------------------
					else if(rs.getString(4).equals("ODI")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					
					else if(rs.getString(4).equals("OTHER")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input >"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_payment('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					
					/*else if(rs.getString(4).equals("BAL")){
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}*/
					
					else if(rs.getString(4).equals("BAL")){
					
					//if(rs.getString(7).equals("RET") || rs.getString(7).equals("CAD") || rs.getString(7).equals("C") ){ //comment by nuwan de silva 10-08-2009
					if(rs.getString(7).equals("RET")  || rs.getString(7).equals("C") ){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>Receipt</td>"); //"+rs.getString(6)+"
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					
					//m_val=m_debit;
          //m_cum_value=m_cum_value-m_val;
					m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008
					/*if (m_cum_value<0 && m_val<0)
					{
					m_cum_value=m_cum_value-m_val;
					}
					else if (m_cum_value>=0 && m_val<0)
					{
					m_cum_value=m_cum_value-m_val;
					}
					else{
					m_cum_value=m_cum_value+m_val;
					}*/
					//added by SH on 15-01-2009 ***********
					if(m_val>0){ 
						if (m_cum_value>0)
						{
						m_cum_value=m_cum_value-m_val;
						}					
						else{
						m_cum_value=m_cum_value+m_val;
						}
					}else{
					//end of addition ******************
						if (m_cum_value<0 && m_val<0)
						{
						m_cum_value=m_cum_value-m_val;
						}
						else if (m_cum_value>=0 && m_val<0)
						{
						m_cum_value=m_cum_value-m_val;
						}
						else{
						m_cum_value=m_cum_value+m_val;
						}
					
					}//added by SH on 15-01-2009 ***********
     
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
          }
					else{
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					
					}
					
					else if (rs.getString(4).equals("RETURN_DEBIT")){
					
					/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008

					if (m_cum_value >0 ){
					m_cum_value=m_cum_value+m_val;
					}
					else{
					m_cum_value=m_cum_value-m_val;
					}
					*/
					
     
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					
					else if (rs.getString(4).equals("DEBIT_CANCEL")){
					
					/*m_val=m_debit-m_credit;  // added by nuwan de silva on 10-07-2008

					if (m_cum_value >0 ){
					m_cum_value=m_cum_value+m_val;
					}
					else{
					m_cum_value=m_cum_value-m_val;
					}
          */
					
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					else if (rs.getString(4).equals("INV_REV")){
														
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}
					
					else if (rs.getString(4).equals("CREDIT_CANCEL")){
															
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_settle_receipt_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"  </td>");
					if(rs.getString(5).equals("-") ||rs.getString(5).equals("null")){
					out.println("<td width='10%' class=div_input>&nbsp;</td>");
					}
					else{
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
					}
					
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}else{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
         
					}

					
					else if(rs.getString(4).equals("DR/CR")){
					out.println("<tr>");
					out.println("<td width='1%'></td>"); 
					out.println("<td width='10%' class=div_input>"+rs.getString(2)+"</td>");
					//out.println("<td width='15%' class=div_input>"+rs.getString(1)+"</td>");
					out.println("<td width='15%' class=div_input style= cursor:hand; onclick=show_invoice_info_2('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='30%' class=div_input>"+rs.getString(6)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(5)+"</td>");
				  if(rs.getString(7).equals("DR") ){
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='10%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(rs.getDouble(3))+"</td>");
					}
					out.println("<td width='10%' class=div_input align='right'>"+nf.format(a.abs(m_cum_value))+"</td>");
					if(m_cum_value>0){
					out.println("<td width='4%' class=div_input>&nbsp;</td>");
					}
					else
					{
					out.println("<td width='4%' class=div_input>Cr</td>");
					}
					out.println("</tr>");
					}
					more_inv = rs.next();
				}
				}
				out.println("</table>");
				
				 out.println("<br>");
				
				
				 Sql_account= " SELECT DRCR_STATUS, AMT,DOCREFNO,FN,TRNDATE  "+
										 "	FROM ( "+
										 "	SELECT '1' X,  "+
										 "	DRCR_STATUS, "+
										 "	TRNAMOUNT AMT, "+
										 "	DOCREFNO DOCREFNO, "+
										 "	DOCREFNO FN, "+
										 "  TO_CHAR(TRNDATE,'DD-MM-YYYY') TRNDATE "+
										 "	FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A "+
										 "	WHERE  "+
										 "	TRNDATE<=to_date('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE >= to_date('"+m_from_date+"','DD-MM-YYYY') "+
										 "	AND ACC_TYPE_CODE='"+m_acc_code+"' "+
										 "	AND DOCREFNO NOT LIKE 'IN%' AND DOCREFNO NOT LIKE 'SP%' AND DOCREFNO NOT LIKE 'OD%' AND  "+
										 "	DOCREFNO NOT LIKE 'SR%' "+
										 "  AND DOCREFNO = '"+m_finance_no+"' "+	
											 
										 "	UNION ALL "+
											
										 "	SELECT '2' X, "+
										 "	DRCR_STATUS, "+
										 "	TRNAMOUNT AMT, "+
										 "	DOCREFNO, "+
										 "	NVL(FINANCE_NO,'IN') FN, "+
										 "  TO_CHAR(TRNDATE,'DD-MM-YYYY') TRNDATE "+
										 "	FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_INVOICE B "+
										 "	WHERE INVOICE_NO = DOCREFNO AND "+
										 "	TRNDATE<=to_date('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=to_date('"+m_from_date+"','DD-MM-YYYY') "+
										 "	AND ACC_TYPE_CODE='"+m_acc_code+"' "+
										 "  AND FINANCE_NO = '"+m_finance_no+"' "+
											
										 "	UNION ALL "+
											
										 "	SELECT '3' X,  "+
										 "	DRCR_STATUS, "+ 
										 "	TRNAMOUNT AMT, "+
										 "	DOCREFNO, "+
										 "	NVL(C.FIN_NO,'OD') FN, "+
										"  TO_CHAR(TRNDATE,'DD-MM-YYYY') TRNDATE "+
										 "	FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY C "+
										 "	WHERE B.INVOICE_NO = C.INVOICE_NO AND C.ODI_REF_NO=DOCREFNO AND "+
										 "	TRNDATE<=to_date('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=to_date('"+m_from_date+"','DD-MM-YYYY') "+
										 "	AND ACC_TYPE_CODE='"+m_acc_code+"' "+
										 "  AND C.FIN_NO = '"+m_finance_no+"' "+
											
										 "	UNION ALL "+
											
										 "	SELECT '4' X, "+
										 "	DRCR_STATUS, "+
										 "	APP_REC_AMOUNT*(A.TRNAMOUNT/C.REC_AMOUNT) AMT, "+
										 "	DOCREFNO, "+
										 "	NVL(FINANCE_NO,'SR') FN, "+
										 "  TO_CHAR(TRNDATE,'DD-MM-YYYY') TRNDATE "+
										 "	FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C "+
										 "	WHERE B.REC_NO = DOCREFNO AND "+
										 "	TRNDATE<=to_date('"+m_to_date+"','DD-MM-YYYY') AND TRNDATE>=to_date('"+m_from_date+"','DD-MM-YYYY') "+
										 "	AND ACC_TYPE_CODE='"+m_acc_code+"' "+
										 "  AND FINANCE_NO = '"+m_finance_no+"' "+
										 "	AND B.REC_NO=C.REC_NO "+
										 "	AND C.REC_AMOUNT<>0 "+
										 "	AND TRNAMOUNT <>0  "+
											
										 "	UNION ALL "+
										 	
										 "  SELECT '5' X, "+
										 "  DRCR_STATUS,  "+
										 "	APP_REC_AMOUNT*(A.TRNAMOUNT/D.REC_AMOUNT) AMT, "+
										 "	DOCREFNO, "+
										 "	NVL(FINANCE_NO,'SR') FN, "+
										 "  TO_CHAR(TRNDATE,'DD-MM-YYYY') TRNDATE "+
										 "	FROM "+m_schema_name+".CO_FN_ACC_LICENCEE_GEN_ACCOUNT A,"+m_schema_name+".AF_CO_PRO_SETTL_REC_APP_BAL B, "+
										 "	"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT C,"+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT D "+
										 "	WHERE C.GROUP_REC_NO = DOCREFNO AND TRNDATE <= to_date('"+m_to_date+"','DD-MM-YYYY') AND "+
										 "	C.GROUP_REC_NO = D.REC_NO AND "+
										 "	D.REC_NO = DOCREFNO AND "+
										 "	TRNDATE >= to_date('"+m_from_date+"','DD-MM-YYYY') "+
										 "	AND a.ACC_TYPE_CODE = '"+m_acc_code+"' "+
										 "  AND FINANCE_NO = '"+m_finance_no+"' "+
										 "	AND B.REC_NO=C.REC_NO  "+
										 "	AND C.GROUP_REC_NO IS NOT NULL "+
										 "	AND C.REC_AMOUNT<>0 "+
										 "	AND TRNAMOUNT <>0  "+
										 "	) "+											
										 //"	GROUP BY DRCR_STATUS ,DOCREFNO ,FN,TRNDATE ";
											" ORDER BY TO_DATE(TRNDATE,'DD-MM-YYYY') ";

			rs=stmt1.executeQuery(Sql_account);	
			//out.println(Sql_account);
			boolean more_acc  = rs.next(); 
			double m_dr_amount=0,m_cr_amount=0,m_net_change=0;     
					if(more_acc){
			    out.println("<hr >");
				
				  out.println("<br>");
					
					out.println("<TABLE  WIDTH='100%'  STYLE='{color: black; font: 10pt arial;}'>");
					out.println("<TR><TD><CENTER><B> Accounting Details - Finance No : "+m_finance_no+" </B></TD></TR>");
					out.println("</TABLE>");
					out.println("<BR><BR>");
				
				  out.println("<table align='center' width='100%' class='table' border='1' bordercolor='black' cellspacing='0' >");
					out.println("<tr class=pdn_txtpos2>");
					out.println("<td width='15%' class=div_input>Date</td>");
					out.println("<td width='25%' class=div_input>Doc Ref</td>");
					out.println("<td width='10%' class=div_input>DR/CR Status</td>");
					out.println("<td width='20%' class=div_input align='right'>Dr</td>");
					out.println("<td width='20%' class=div_input align='right'>Cr</td>");
					out.println("</tr>");
				
				while(more_acc){
				
				  out.println("<tr>");
					out.println("<td width='15%' class=div_input>"+rs.getString(5)+"</td>");
					out.println("<td width='25%' class=div_input style='cursor:hand' onclick=lookup_docref_detail('"+rs.getString(3)+"')><u>"+rs.getString(3)+"</td>");
					out.println("<td width='10%' class=div_input>"+rs.getString(1)+"</td>");
					if(rs.getString(1).equals("DR")){
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					m_dr_amount = m_dr_amount+rs.getDouble(2);
					}else{
					out.println("<td width='20%' class=div_input align='right'>&nbsp;</td>");
					out.println("<td width='20%' class=div_input align='right'>"+nf.format(rs.getDouble(2))+"</td>");
					m_cr_amount = m_cr_amount+rs.getDouble(2);
					}
					out.println("</tr>");
				
				more_acc  = rs.next(); 
				
				}
				
				out.println("<tr>");
				out.println("<td width=15% class=div_input>&nbsp;</td>");
				out.println("<td width=25% class=div_input>&nbsp;</td>");
				out.println("<td width=10% class=div_input align='right'><b>Total</td>");
				out.println("<td width=20% class=div_input align='right'>"+nf.format(m_dr_amount)+"</td>");
				out.println("<td width=20% class=div_input align='right'>"+nf.format(m_cr_amount)+"</td>");
				out.println("<tr>");
				
				m_net_change = m_dr_amount-m_cr_amount;
				
				out.println("<tr>");
				out.println("<td width=15% class=div_input>&nbsp;</td>");
				out.println("<td width=25% class=div_input>&nbsp;</td>");
				out.println("<td width=10% class=div_input align='right'><b>Net Change</td>");
				if(m_net_change>0){
				out.println("<td width=20% class=div_input align='right'>"+nf.format(m_net_change)+"</td>");
				out.println("<td width=20% class=div_input align='right'>&nbsp;</td>");
				}else{
				out.println("<td width=20% class=div_input align='right'>&nbsp;</td>");
				out.println("<td width=20% class=div_input align='right'>"+nf.format(m_net_change*-1)+"</td>");
				}
				out.println("<tr>");
				
				}
				out.println("</table>");
				
				out.println("<BR><BR>");
				
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
    }
				
				else if(m_screen_type.equals("exception_list")){
					
					String m_to_date    = req.getParameter("to_date");
					String m_from_date  = req.getParameter("from_date");
					String m_finance_no = req.getParameter("finance_no");
					
					out.println("<html><head><title>Exception List</title>");
					out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
					out.println("</head><body>");
					
					rs = stmt.executeQuery(" SELECT DISTINCT FINANCE_NO, A.GRENTAL_AMOUNT,TO_CHAR(A.RENTAL_DATE,'DD-MM-YYYY') RENTAL_DATE, "+
						"        TO_CHAR(A.RENTAL_DATE,'DD'),TO_CHAR(A.RENTAL_DATE,'MM'),"+
						"        TO_CHAR(A.RENTAL_DATE,'YYYY'),A.CASH_OUT_FLOW, "+
						"        A.VAT_RENTAL_AMOUNT, A.INVOICE_NO, "+
						"        "+m_schema_name+".AF_CO_GET_RESIDUAL_VAL_INV(A.APPLICATION_NO,INSTALLMENT_NO) "+	
						" FROM   "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B, "+
						"        "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS C "+//Added By Sandun on 19-10-2009
						" WHERE  A.RENTAL_DATE>=TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
						"        B.APPLICATION_NO=A.APPLICATION_NO AND B.APPLICATION_STATUS='ACTIVATED' AND "+
						"        B.APPLICATION_NO   = C.APPLICATION_NO AND "+ //  |
						"        A.PRO_INVOICE_NO   = C.INVOICE_NO AND "+     //  Added By Sandun on 19-10-2009 
						"        C.ACTIVE_STATUS    = 'Y' AND "+              //  |
						"        FINANCE_NO = '"+m_finance_no+"' AND "+
						"        RENTAL_DATE<=TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
						"        A.INVOICE_NO IS NULL "+
						" ORDER BY TO_DATE(RENTAL_DATE,'DD-MM-YYYY') ");
					
					
					boolean more = rs.next();
					out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
					out.println("<tr><td width=100% align='center'><b><u>Exception List From "+m_from_date+" To "+m_to_date+"</td></td>");
					out.println("</table>");
					
					out.println("<br>");
					
					if(!more){
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						out.println("<tr><td width=100% align='center'><font color='red'>No Data Found..!</font></td></td>");
						out.println("</table>");
					}
					
					if(more){
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						
						out.println("<tr class=tr_input>");
						out.println("<td><b>Finance No</td>");
						out.println("<td><b>Rental Amount</td>");
						out.println("<td><b>Rental Date</td>");
						out.println("<td><b>Note</td>");
						out.println("</tr>");
						
						while(more){
							out.println("<tr class=tr_input>");
							out.println("<td >"+rs.getString(1)+"</td>");
							out.println("<td >"+nf.format(rs.getDouble(2))+"</td>");
							out.println("<td >"+rs.getString(3)+"</td>");
							if(rs.getString(10)==null){
								out.println("<td ></td>");
							}else{
								if(rs.getString(10).equals("N")){
									out.println("<td >Residual value Not entered</td>");
								}else{
									out.println("<td ></td>");
								}
							}
							out.println("</tr>");
							more = rs.next();	
						}	 
						
						out.println("</table>");
						out.println("<br>");
						out.println("<br>");
					}
					
					rs1 = stmt1.executeQuery(" SELECT A.APPLICATION_NO, "+
						//out.println(" SELECT A.APPLICATION_NO, "+
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+
						" A.FINANCE_NO,  "+
						" A.APPLICATION_STATUS,  "+
						" "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(A.TRANSACTION_TYPE), "+
						" "+m_schema_name+".AF_CO_GET_TERMINATION_TYPE(A.TER_TYPE) "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
						" WHERE A.APPLICATION_STATUS IN ('ENTERED','ENT_CON','VERIFY1','V-APP','VERIFY-M','VERIFY2','VERIFYL') "+
						" AND A.FINANCE_NO='"+m_finance_no+"' "+
						" AND A.APPLICATION_NO IN (SELECT B.APPLICATION_NO "+
						" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION B "+
						" WHERE B.ACTIVE_STATUS = 'TERM_CHECK'  "+
						" AND TERMINATION_TYPE IN ('RESCHEDULE','PAR_TER','RENT_REVIS','ENHA_DOWN'))");
					
					
					boolean more1 = rs1.next();
					if(more1){
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<br>");
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						out.println("<tr><td width=100% align='center'><b><u>Application detail</td></td>");
						out.println("</table>");
						
						out.println("<br>");
						out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=table>");
						
						out.println("<tr class=tr_input>");
						out.println("<td><b>Application No</td>");
						out.println("<td><b>Client Name</td>");
						out.println("<td><b>Finance No</td>");
						out.println("<td><b>Application Status</td>");
						out.println("<td><b>Transaction Type</td>");
						out.println("<td><b>Termination Type</td>");
						out.println("</tr>");
						
						while(more1){
							out.println("<tr class=tr_input>");
							out.println("<td >"+rs1.getString(1)+"</td>");
							out.println("<td >"+rs1.getString(2)+"</td>");
							out.println("<td >"+rs1.getString(3)+"</td>");
							out.println("<td >"+rs1.getString(4)+"</td>");
							out.println("<td >"+rs1.getString(5)+"</td>");
							out.println("<td >"+rs1.getString(6)+"</td>");
							out.println("</tr>");
							more1 = rs1.next();	
						}
					}
					out.println("</table>");
					
					
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("</body></html>");
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


