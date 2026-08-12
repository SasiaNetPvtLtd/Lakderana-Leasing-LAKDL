

//SCREEN NAME:
//CREATED BY:SANDUN
//DATE/TIME:18/06/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Rental_Target_Setup extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1,stmt2;
		public ResultSet rs,rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
		CallableStatement callstmt1 =null;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
      nf.setMaximumFractionDigits(2);
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();			

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			
			conn  = con_method.met_user_validate(req); 
			stmt  = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			
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
			
			 if(m_screen_type.trim().equals("main_page")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			out.println("var flag =0;");
		  out.println("var date1='' ");
			out.println("var date2='' ");
			
			 out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
				out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
				out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
				out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
				
				}
				out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  System Administration - Rental Target Setup - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  System Administration - Rental Target Setup - \"+document.Form1.hid_status.value;"); 
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
			out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("		if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("	if(oBj.valout[1]=='Next')  {");
			out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("	}");
			out.println("	else if  (oBj.valout[1]=='Prev') {");
			out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("	}		");
			out.println("	else if(oBj.valout[1] == 'Close'){");
			out.println("	}");
			out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("	if(IfCount=='4'){"); 
			out.println("		finance_assign(oBj);"); 
			out.println("	}");
			out.println("	if(IfCount=='5'){"); 
			out.println("		officer_assign(oBj);"); 
			out.println("	}");
			out.println("	}"); 
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
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}");  
      
			out.println("function clear_fields(){ ");
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"5\"){");
			out.println("document.Form1.TXT_MK_OFFICER.value=\"\";");
			out.println("}");
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
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}");
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Target_Setup?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Target_Setup?chksql=main_page';"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println(" if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("alert('Do you want to modify recodes?');");
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			  out.println("}");
			out.println("}");
			
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			
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
			out.println("}");
			
			out.println("function get_vector_normal(http_response) {");	
		 	out.println(" request_fin_details.innerHTML = ''; ");
			out.println(" request_fin_details.innerHTML = http_response; ");
			out.println("}");
					
									
			out.println("function makeRequest(){");	
			out.println("	if(checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){ ");// added by Chatura Jayawardena
			out.println("if(validate_req()){");
			out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("finance_no = document.Form1.TXT_FINANCE_NO.value;");
			out.println("if(finance_no!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Target_Setup?chksql=load_details&date=\"+m_from_date+\"&mk_officer=\"+document.Form1.TXT_MK_OFFICER.value+\"&finance_no=\"+finance_no+\" \";");
			out.println("window.open(m_url,'displayWindow23','left=50,top=60,width=1250,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");	
			out.println("else{");	
			out.println("status = document.Form1.hid_status.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Target_Setup?chksql=load_finance_details&status=\"+status+\"&date=\"+m_from_date+\"&mk_officer=\"+document.Form1.TXT_MK_OFFICER.value+\" \";");
			out.println("load_interface(m_url,'NORM');");	
			out.println("}");	
			out.println("}");		
			out.println("}");
			out.println("}");
			
			
			out.println("function help_finance() {"); 
			out.println("document.Form1.hid_help_type.value=\"4\";"); 
			out.println("Crit = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_5','4');");
			out.println("}");
		 
		  out.println("function finance_assign(oBj) {");				
			out.println("document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_MK_OFFICER.value=oBj.valout[6];"); 
			out.println("request_fin_details.innerHTML = ''; ");
			out.println("load_but_val();");
			out.println("}");
			
			out.println("function help_mk_officer() {"); 
			out.println("document.Form1.hid_help_type.value=\"5\";"); 
			out.println("Crit = document.Form1.TXT_MK_OFFICER.value+\"@\";"); 
			out.println("HelpBox('1','10','0',Crit,'MKOfficer_help_Sql','5');");
			out.println("}");
		 
		 
		  out.println("function officer_assign(oBj) {");				
			out.println("document.Form1.TXT_MK_OFFICER.value=oBj.valout[2];"); 
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";"); 			
			out.println(" request_fin_details.innerHTML = ''; ");
			out.println("}");
			
					
			out.println("function load_but_val(){");
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("document.Form1.BUT_PROCESS.value=\"Go\" ");
			out.println("}else{");
			out.println("document.Form1.BUT_PROCESS.value=\"Setup Target\"");
			out.println("}");
			out.println("}");
			
			out.println("function load_setup(finance_no){");
			out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_Rental_Target_Setup?chksql=load_details&mk_officer=\"+document.Form1.TXT_MK_OFFICER.value+\"&date=\"+m_from_date+\"&finance_no=\"+finance_no+\" \";");
			out.println("window.open(m_url,'displayWindow23','left=50,top=60,width=1250,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("function validate_req(){");
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\" && document.Form1.TXT_MK_OFFICER.value==\"\"){");
		  out.println("alert('Please input value for collection officer or finance no..!');"); 
			out.println("return false;"); 
			out.println("}else{return true;}");
			out.println("return true;");
			out.println("}");
					
			out.println("</Script>");
			
			out.println("<body onload=\"load_sysdate();load_but_val()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>System Administration - Rental Target Setup</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");
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
			out.println("<table class=table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
			out.println("</table>");
			out.println("<BR>");
			
			out.println("<table width='*%' class='table' border='0'>"); 			
			
			out.println("<tr class=tr_input>");
			out.println("<td width='7%' ID=VDATE>Date*</td>");
			out.println("<td width='8%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='10%'></td>"); 
			out.println("<td width='10%'></td>"); 
			
			out.println("<tr >"); 
			out.println("<td  width='9%' ><DIV id='DIV_TXT_MK_OFFICER'  class=div_input>Collection Officer</DIV></td>"); 
			out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_MK_OFFICER' maxlength='20' style='width:100'  OnBlur=\"help_mk_officer()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MK_OFFICER' value=\"Help\" onClick=\"help_mk_officer()\"> </td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td  width='9%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
			out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' style='width:100'  OnBlur=\"help_finance()\" onchange=\"load_but_val()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_finance()\" > </td>"); 
					
			out.println("<td width='8%' align='left'><input type=\"button\" name=\"BUT_PROCESS\" class='but_input' onClick='makeRequest()' style='width:110px' value=\"Setup Targets\"></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("</tr>");
			out.println("</table>");
		  
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='request_fin_details'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");
						
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
		  out.println("</table>"); 
			out.println("</form>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>");

					
			}else	if(m_screen_type.equals("load_details")){
			
			 String m_finance_no = req.getParameter("finance_no");
			 String m_date       = req.getParameter("date");
			 String m_mk_officer = req.getParameter("mk_officer");
				
			 String  m_client="";
			 String m_month_1="";
			 String m_month_2="";
			 String m_month_3="";
			 String m_month_4="";
			 String m_month_5="";
			 String m_month_6="";
			 String m_month_7="";
			 String m_month_8="";
			 String m_month_9="";
			 String m_month_10="";
			 String m_month_11="";
			 String m_month_12="";
			 double m_amount =0;
			 int m_finance_count=0;
			 double m_target_amt_1=0,m_target_amt_2=0,m_target_amt_3=0,m_target_amt_4=0,m_target_amt_5=0,m_target_amt_6=0,m_target_amt_7=0,m_target_amt_8=0,m_target_amt_9=0,m_target_amt_10=0,m_target_amt_11=0,m_target_amt_12=0;
				
				rs2=stmt2.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_OPENING_BAL('"+m_finance_no+"','"+m_date+"'),0), "+
															 " "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO('"+m_finance_no+"')) "+
															 " FROM  DUAL ");
																
																
				if(rs2.next()){
				m_client = rs2.getString(2);
				m_amount= rs2.getDouble(1);
				}
				
			  out.println("<html>");
			  out.println("<head>");
			  out.println("<title>Asset Financing System</title>    ");
			  out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			  out.println("</head>");
			  out.println("<Script>");
				
				out.println("function load_screen_status(m_val){"); 
				out.println(" if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
			
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  System Administration - Rental Target Setup - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  System Administration - Rental Target Setup - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function change_value(col) {");	
				out.println("var m_open_arr=0;");
				out.println("var m_open_arr_new=0;");
			  out.println("tot_amount = parseFloat(unformat_noobject(document.getElementById('arr_'+col).value))+parseFloat(unformat_noobject(document.Form1.elements['hid_arr_'+col].value));"); 
				out.println("document.Form1.elements['txt_tot_'+col].value=tot_amount;");			
				out.println("for(h=1;h<13;h++){");			
				out.println("m_open_arr += parseFloat(unformat_noobject(document.Form1.elements['txt_arr_'+h].value));");
				out.println("}");
				out.println("document.Form1.TOT_ARR_AMT.value=parseFloat(m_open_arr);");
				out.println("document.Form1.TOT_OPEN_BAL_AMT.value = parseFloat(unformat_noobject(document.Form1.TOT_OPEN_AMT.value))-parseFloat(unformat_noobject(document.Form1.TOT_ARR_AMT.value));");
				
				out.println("format_num(document.Form1.elements['txt_tot_'+col],0)");
				out.println("format_num(document.Form1.elements['txt_arr_'+col],0)");
				out.println("format_num(document.Form1.TOT_ARR_AMT,0)");
				out.println("format_num(document.Form1.TOT_OPEN_BAL_AMT,0)");
				
				out.println("if(parseFloat(m_open_arr)>parseFloat(unformat_noobject(document.Form1.TOT_OPEN_AMT.value))){");
				out.println("alert('Cannot exceed target value more than opening balance..!');");
				out.println("document.Form1.elements['txt_arr_'+col].value=0");
				out.println("for(h=1;h<13;h++){");			
				out.println("m_open_arr_new += parseFloat(unformat_noobject(document.Form1.elements['txt_arr_'+h].value));");
				out.println("}");				
				out.println("document.Form1.elements['txt_tot_'+col].value=parseFloat(unformat_noobject(document.Form1.elements['hid_arr_'+col].value))");
			  out.println("document.Form1.TOT_ARR_AMT.value=parseFloat(m_open_arr_new);");
				out.println("document.Form1.TOT_OPEN_BAL_AMT.value = parseFloat(unformat_noobject(document.Form1.TOT_OPEN_AMT.value))-parseFloat(unformat_noobject(document.Form1.TOT_ARR_AMT.value));");
				out.println("}");					
				out.println("format_num(document.Form1.elements['txt_tot_'+col],0)");
				out.println("format_num(document.Form1.elements['txt_arr_'+col],0)");
				out.println("format_num(document.Form1.TOT_ARR_AMT,0)");
				out.println("format_num(document.Form1.TOT_OPEN_BAL_AMT,0)");
				out.println("}");
						
				out.println("function onload_value(){");
				out.println("var m_load_open_arr=0;");
				out.println("for(k=1;k<13;k++){");
				out.println("document.Form1.elements['txt_tot_'+k].value=parseFloat(unformat_noobject(document.Form1.elements['hid_arr_'+k].value))+parseFloat(unformat_noobject(document.Form1.elements['txt_arr_'+k].value));");
				out.println("m_load_open_arr += parseFloat(unformat_noobject(document.Form1.elements['txt_arr_'+k].value));");
				out.println("format_num(document.Form1.elements['txt_tot_'+k],0)");
				out.println("}");
				out.println("document.Form1.TOT_ARR_AMT.value = parseFloat(m_load_open_arr);");
				out.println("document.Form1.TOT_OPEN_BAL_AMT.value = parseFloat(unformat_noobject(document.Form1.TOT_OPEN_AMT.value))-parseFloat(unformat_noobject(document.Form1.TOT_ARR_AMT.value));");
				out.println("format_num(document.Form1.TOT_ARR_AMT,0)");
				out.println("format_num(document.Form1.TOT_OPEN_BAL_AMT,0)");
				out.println("}");
				
				out.println("function select_vaue(col){");
				out.println("document.Form1.elements['txt_arr_'+col].select();");
				out.println("}");
				
				out.println("function check_ntm(row){");
				out.println("var m_value;");
				out.println("var m_size;");
				out.println("var valno;");
				out.println("var inputStr;");			
				out.println("    nt=\"txt_arr_\"+row;");					
				out.println("valno	=    document.Form1.elements[nt].value;"); 
				out.println("valno=valno.toUpperCase();");
			  out.println("m_size=valno.length;");
				out.println("check_number(document.Form1.elements[nt],22)");
				out.println("}");
			
				out.println("function check_number(obj,size){");
				out.println("if(obj.value!='')"); 
				out.println("if(isnumberok(obj,size)){"); 
				out.println("format_number(obj,size)"); 
				out.println("}"); 
				out.println("else{");
				out.println("alert('please enter a number');"); 
				out.println("obj.value=format_noobject(0.00);"); 
				out.println("obj.focus();"); 
				out.println("}"); 
				out.println("}"); 
			
				
			
				out.println("function before_save(){");
				out.println("submit_data();");
				out.println("}");	
				
				out.println("function submit_data(){");
				//out.println("	if(checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){ ");
				out.println("	if(confirm(\"Are you sure, you want to save data?\")){ ");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_Rental_Target_Setup';");  
				out.println("		document.Form1.submit();	");
				out.println("}");
				//out.println("}");
				out.println("}");
				
				out.println("function format_num(obj,i){");
				out.println("  if(isNaN(unformat_noobject(obj.value))){");
				out.println("   alert('Please Enter Number!');");
				out.println("   obj.value='0';");
				out.println("  }else{");
				out.println("   if(i=='0'){");
				out.println("    obj.value=unformat_noobject(obj.value)");
				out.println("    obj.value=format_noobject_nodecimal(obj.value);"); 
			 	out.println("   }else if(i!='6'){");
				out.println("    m_val_o=unformat_noobject(obj.value)");
				out.println("    obj.value=format_noobject_nodecimal(m_val_o);"); 
			  out.println("   }");
				out.println("  }");
				out.println("}");
				
				out.println("</Script>");
				
				out.println("<body ONLOAD=\"onload_value()\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			  out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='TXT_FINANCE_NO' VALUE=\""+m_finance_no+"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='TXT_MK_OFFICER' VALUE=\""+m_mk_officer+"\">"); 
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>System Administration - Rental Target Setup</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='window.close()' value=\"Close\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='load_screen_status(\"SAVE\"),before_save()' value=\"Save\"></td>");
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
	      out.println("</table>");
				out.println("</td>	");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
				out.println("<table class=table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
				out.println("</table>");
				out.println("<br><br>");	
				out.println("<table  width='100%'  class='table' border=0 cellspacing=0 cellpadding=0 bordercolor='#FFFACD'> ");
				
				out.println("<tr bgcolor='#FFFACD' style='height:25px'>");
				out.println("<td width='5%' align ='left' ><b>Contract No</td><td width='16%' align ='left' >"+m_finance_no+"</td><td width='10%' align ='left'><b>Opening Balance</td><td width='6%' align ='right'   ><input type=text readonly name=TOT_OPEN_AMT id=TOT_OPEN_AMT value="+nf.format(m_amount)+" class='txt_input' style='text-align:right;border:none;background:#FFFACD;font-color:black'></td><td width='*%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#FFFACD' style='height:25px'>");
				out.println("<td width='5%' align ='left' ><b>Client</td><td width='16%' align ='left' >"+m_client+"</td><td width='10%' align ='left'><b>Arrears Target</td><td width='6%' align ='right'  ><input type=text readonly name=TOT_ARR_AMT id=TOT_ARR_AMT value="+nf.format(0)+" class='txt_input' style='text-align:right;border:none;background:#FFFACD'></td><td width='*%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#FFFACD' style='height:25px'>");
				out.println("<td width='5%' align ='left' >&nbsp;</td><td width='16%' align ='left' >&nbsp;</td><td width='10%' align ='left'><b>Balance</td><td width='6%' align ='right'  ><input type=text readonly name=TOT_OPEN_BAL_AMT id=TOT_OPEN_BAL_AMT value="+nf.format(m_amount)+" class='txt_input' style='text-align:right;border:none;background:#FFFACD'></td><td width='*%'>&nbsp;</td>");
				out.println("</tr>");
				out.println("<tr bgcolor='#FFFACD' style='height:25px'>");
				out.println("</tr>");
				out.println("<tr bgcolor='#FFFACD' style='height:25px'>");
				out.println("</tr>");
				
			  out.println("</table>");
				
				 rs=stmt.executeQuery(" SELECT COUNT(*)  "+
														  " FROM "+m_schema_name+".AF_MAS_RENTAL_ARR_SETUP A "+
														  " WHERE A.RENTAL_DATE >= LAST_DAY(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1))+1 "+
														  " AND   A.FINANCE_NO    = '"+m_finance_no+"' ");
				
					if(rs.next()){
					m_finance_count = rs.getInt(1);
					}
					
					
					
			 rs=stmt.executeQuery("SELECT  TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0),'Mon-YYYY'), "+//1
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),1),'Mon-YYYY'), "+//2
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),2),'Mon-YYYY'), "+//3
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),3),'Mon-YYYY'), "+//4
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),4),'Mon-YYYY'), "+//5
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),5),'Mon-YYYY'), "+//6
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),6),'Mon-YYYY'), "+//7
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),7),'Mon-YYYY'), "+//8
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),8),'Mon-YYYY'), "+//9
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),9),'Mon-YYYY'), "+//10
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),10),'Mon-YYYY'), "+//11
        " TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),11),'Mon-YYYY') "+//12
        " FROM DUAL ");
			
			
			
			out.println("<table  width='100%'  class='table' border=1 cellspacing=0 cellpadding=0 bordercolor='#D8D8D8'>");
			
			if(rs.next()){
			m_month_1  = rs.getString(1);
			m_month_2  = rs.getString(2);
			m_month_3  = rs.getString(3);
			m_month_4  = rs.getString(4);
			m_month_5  = rs.getString(5);
			m_month_6  = rs.getString(6);
			m_month_7  = rs.getString(7);
			m_month_8  = rs.getString(8);
			m_month_9  = rs.getString(9);
			m_month_10 = rs.getString(10);
			m_month_11 = rs.getString(11);
			m_month_12 = rs.getString(12);
			}
			
			out.println("<tr    >");//class=pdn_txtpos2 
			out.println("<td width='25%' align ='left' bgcolor='#FFFACD' ><b>Item</td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_1+"<input type='hidden' name='hid_date_1' value="+m_month_1+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_2+"<input type='hidden' name='hid_date_2' value="+m_month_2+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_3+"<input type='hidden' name='hid_date_3' value="+m_month_3+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_4+"<input type='hidden' name='hid_date_4' value="+m_month_4+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_5+"<input type='hidden' name='hid_date_5' value="+m_month_5+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_6+"<input type='hidden' name='hid_date_6' value="+m_month_6+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_7+"<input type='hidden' name='hid_date_7' value="+m_month_7+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_8+"<input type='hidden' name='hid_date_8' value="+m_month_8+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_9+"<input type='hidden' name='hid_date_9' value="+m_month_9+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_10+"<input type='hidden' name='hid_date_10' value="+m_month_10+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_11+"<input type='hidden' name='hid_date_11' value="+m_month_11+"></td>"); 
			out.println("<td width='8%' align ='center'><b>"+m_month_12+"<input type='hidden' name='hid_date_12' value="+m_month_12+"></td>");
			out.println("</tr >"); 
			
			
			rs1=stmt1.executeQuery(  " SELECT  "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),0),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),1),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),2),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),3),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),4),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),5),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),6),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),7),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),8),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),9),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),10),'DD-MM-YYYY')), "+
														" "+m_schema_name+".AF_CO_MAS_MONTHLY_RENTAL('"+m_finance_no+"',TO_CHAR( ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),11),'DD-MM-YYYY')) "+
														" FROM DUAL ");

      if(rs1.next()){
			out.println("<tr   >");//class=pdn_txtpos2 
			out.println("<td width='25%' align ='left' bgcolor='#FFFACD'><b>Current Month(Default)</td>"); 
			out.println("<td width='8%' align ='right' >"+nf.format(rs1.getDouble(1))+"<input type='hidden' name='hid_arr_1' value="+rs1.getDouble(1)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(2))+"<input type='hidden' name='hid_arr_2' value="+rs1.getDouble(2)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(3))+"<input type='hidden' name='hid_arr_3' value="+rs1.getDouble(3)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(4))+"<input type='hidden' name='hid_arr_4' value="+rs1.getDouble(4)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(5))+"<input type='hidden' name='hid_arr_5' value="+rs1.getDouble(5)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(6))+"<input type='hidden' name='hid_arr_6' value="+rs1.getDouble(6)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(7))+"<input type='hidden' name='hid_arr_7' value="+rs1.getDouble(7)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(8))+"<input type='hidden' name='hid_arr_8' value="+rs1.getDouble(8)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(9))+"<input type='hidden' name='hid_arr_9' value="+rs1.getDouble(9)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(10))+"<input type='hidden' name='hid_arr_10' value="+rs1.getDouble(10)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(11))+"<input type='hidden' name='hid_arr_11' value="+rs1.getDouble(11)+"></td>"); 
			out.println("<td width='8%' align ='right'>"+nf.format(rs1.getDouble(12))+"<input type='hidden' name='hid_arr_12' value="+rs1.getDouble(12)+"></td>");
			out.println("</tr >"); 
			}
			
		
			rs1=stmt1.executeQuery(" SELECT NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_1+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_2+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_3+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_4+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_5+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_6+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_7+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_8+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_9+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_10+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_11+"'),0), "+
														" NVL("+m_schema_name+".AF_CO_GET_TARGET_AMOUNT('"+m_finance_no+"','"+m_month_12+"'),0) "+
														" FROM DUAL ");
				if(rs1.next()){
				
				m_target_amt_1 = rs1.getDouble(1);
				m_target_amt_2 = rs1.getDouble(2);
				m_target_amt_3 = rs1.getDouble(3);
				m_target_amt_4 = rs1.getDouble(4);
				m_target_amt_5 = rs1.getDouble(5);
				m_target_amt_6 = rs1.getDouble(6);
				m_target_amt_7 = rs1.getDouble(7);
				m_target_amt_8 = rs1.getDouble(8);
				m_target_amt_9 = rs1.getDouble(9);
				m_target_amt_10 = rs1.getDouble(10);
				m_target_amt_11 = rs1.getDouble(11);
				m_target_amt_12 = rs1.getDouble(12);				
				}
				
      
			out.println("<tr  >");
			out.println("<td width='25%' align ='left' bgcolor='#FFFACD'><b>Arrears Target</td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_1 type='text' style='text-align:right' name=txt_arr_1 class='txt_input' value=\""+nf.format(m_target_amt_1)+"\" onchange=\"change_value(1);\" onclick=\"select_vaue(1)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_2 type='text' style='text-align:right' name=txt_arr_2 class='txt_input' value=\""+nf.format(m_target_amt_2)+"\" onchange=\"change_value(2);\" onclick=\"select_vaue(2)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_3 type='text' style='text-align:right' name=txt_arr_3 class='txt_input' value=\""+nf.format(m_target_amt_3)+"\" onchange=\"change_value(3);\" onclick=\"select_vaue(3)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_4 type='text' style='text-align:right' name=txt_arr_4 class='txt_input' value=\""+nf.format(m_target_amt_4)+"\" onchange=\"change_value(4);\" onclick=\"select_vaue(4)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_5 type='text' style='text-align:right' name=txt_arr_5 class='txt_input' value=\""+nf.format(m_target_amt_5)+"\" onchange=\"change_value(5);\" onclick=\"select_vaue(5)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_6 type='text' style='text-align:right' name=txt_arr_6 class='txt_input' value=\""+nf.format(m_target_amt_6)+"\" onchange=\"change_value(6);\" onclick=\"select_vaue(6)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_7 type='text' style='text-align:right' name=txt_arr_7 class='txt_input' value=\""+nf.format(m_target_amt_7)+"\" onchange=\"change_value(7);\" onclick=\"select_vaue(7)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_8 type='text' style='text-align:right' name=txt_arr_8 class='txt_input' value=\""+nf.format(m_target_amt_8)+"\" onchange=\"change_value(8);\" onclick=\"select_vaue(8)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_9 type='text' style='text-align:right' name=txt_arr_9 class='txt_input' value=\""+nf.format(m_target_amt_9)+"\" onchange=\"change_value(9);\" onclick=\"select_vaue(9)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_10 type='text' style='text-align:right' name=txt_arr_10 class='txt_input' value=\""+nf.format(m_target_amt_10)+"\" onchange=\"change_value(10);\" onclick=\"select_vaue(10)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_11 type='text' style='text-align:right' name=txt_arr_11 class='txt_input' value=\""+nf.format(m_target_amt_11)+"\" onchange=\"change_value(11);\" onclick=\"select_vaue(11)\"></td>"); 
			out.println("<td width='8%' align ='center' ><input id=arr_12 type='text' style='text-align:right' name=txt_arr_12 class='txt_input' value=\""+nf.format(m_target_amt_12)+"\" onchange=\"change_value(12);\" onclick=\"select_vaue(12)\"></td>"); 
			
			
			out.println("</tr >"); 
			
			out.println("<tr   >");
			out.println("<td width='25%' align ='left'  bgcolor='#FFFACD'><b>Total Target</td>"); 
			for(int i=1;i<13;i++){
			out.println("<td width='8%' align ='center'><input type='text' style='text-align:right;border:none' readonly name=txt_tot_"+i+" class='txt_input' onblur=\"change_value('"+i+"');\"></td>"); 
			}
			out.println("</tr >"); 
			out.println("</table>"); 
			out.println("</td >"); 
			out.println("</tr >"); 
			out.println("</table >"); 
			
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</form>"); 
			out.println("</body>"); 
			out.println("</html>");
			
				
			}	
			
      else	if(m_screen_type.equals("load_finance_details")){
			
			String mk_officer = req.getParameter("mk_officer");
			String m_date     = req.getParameter("date");
			String m_status   = req.getParameter("status");
			int j=1;
			
			if(m_status.equals("New")){
			
			 rs1=stmt1.executeQuery(" SELECT DISTINCT  A.FINANCE_NO, "+
				                      " A.CLIENT_CODE, "+
															" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)"+
															" FROM    "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A ,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B"+
															" WHERE   A.APPLICATION_NO = B.APPLICATION_NO "+  
															" AND     A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
															" AND     A.COLLECTION_OFFICER='"+mk_officer+"' "+
															" AND     A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_MAS_RENTAL_ARR_SETUP WHERE TO_CHAR(ENT_DATE,'MM-YYYY') = TO_CHAR(SYSDATE,'MM-YYYY') AND COLL_OFFICER='"+mk_officer+"') ");
															
															
			}else{
			
			rs1=stmt1.executeQuery(" SELECT  A.FINANCE_NO, "+
				                      " A.CLIENT_CODE, "+
															" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE)"+
															" FROM    "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A "+
															" WHERE   A.APPLICATION_STATUS IN ('ACTIVATED','LEGAL') "+
															" AND     A.COLLECTION_OFFICER = '"+mk_officer+"' "+
															" AND     A.FINANCE_NO IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_MAS_RENTAL_ARR_SETUP WHERE TO_CHAR(ENT_DATE,'MM-YYYY') = TO_CHAR(SYSDATE,'MM-YYYY') AND COLL_OFFICER='"+mk_officer+"') ");
			
			}
			out.println("<table  width='100%'  class='table' border=0 > ");
			out.println("<tr class=pdn_txtpos2 >");
			out.println("<td width='5%' align=center>No</td>");
			out.println("<td width='25%' >Contract No</td>");
			out.println("<td width='50%' >Client Name</td>");
			out.println("<td width='20%' >&nbsp;</td>");
			out.println("</tr>");
			boolean more = rs1.next();
			
			while (more){
			
			if(j%2==0 && j>0){
			out.println("<tr  bgcolor=\"#C0C0C0\">");
			}
			else {
			out.println("<tr>");
			}
			out.println("<td width='5%'  align=center >"+j+"</td>");
			out.println("<td width='25%' align=left   >"+rs1.getString(1)+"</td>");
			out.println("<td width='50%' align=left   >"+rs1.getString(3)+"</td>");
			out.println("<td width='20%' align=center ><input type='button' name='but_setup' class='but_input' value=\"Setup Target\" style='width:100' onclick=\"load_setup('"+rs1.getString(1)+"')\"></td>");
			out.println("</tr>");			
			more = rs1.next();
			j++;
			
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


