//SCREEN NAME	:Security_and_Marketting_file Report
//CREATED BY	:DELANJALI
//DATE/TIME		:24-09-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MISF_Security_and_Marketting_file_3 extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt,stmt1;
		public ResultSet rs1,rs2;
		PreparedStatement pstmt;
		java.text.NumberFormat nf;
	  ServletOutputStream out = null;
public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

      LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();

			String m_html_client_url=con_method.html_client_url.trim(); 
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			conn = con_method.met_user_validate(req); 
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
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
					   
		  String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
          m_sort_column = req.getParameter("sort_column");
          m_order_by_type = req.getParameter("order_by_type");
				}
				
			if(m_screen_type.trim().equals("main_page1")){	
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" ){");
			out.println("help_update();");
			out.println("			}");
			out.println("}");

			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_application_1&data_val=\"+obj.value+\"\";");
			out.println("load_interface(m_url,'XML');");
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
			
			out.println("if(document.Form1.VAL_DAY1.value!=\"\" && document.Form1.VAL_MONTH1.value!=\"\" && document.Form1.VAL_YEAR1.value!=\"\" && document.Form1.VAL_DAY2.value!=\"\" && document.Form1.VAL_MONTH2.value!=\"\" && document.Form1.VAL_YEAR2.value!=\"\"){");
			out.println("ChkValidity(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2,document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)");
			out.println("}");
			out.println("}");
			
			
			out.println("function check_date(obj1,obj2,obj3){");
			
			out.println("if(obj1.value!=\"\" || obj2.value!=\"\" || obj3.value!=\"\"){");
			out.println("checkMonthLength(obj1,obj2,obj3);");
			out.println("}");
			out.println("date1=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("document.Form1.hid_from_date.value=date1");
			out.println("document.Form1.hid_to_date.value=date2");

			out.println("}");
			
			
			out.println("function help_update() {");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"FinanceSql_period\";"); 
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];");
			out.println("    document.Form1.hid_fin_no.value=oBj.valout[3];");
			out.println("    document.Form1.hid_client.value=oBj.valout[4];");
			out.println("    document.Form1.hid_client_name.value=oBj.valout[5];");
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
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=main_page1';"); 
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
			out.println("help_box.innerHTML=\"  Facilities Identified withing a given Period - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Facilities Identified withing a given Period\";"); 
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
			
			//added by nuwan de silva on 23-10-07---------------------------
			out.println("function view_report_2(){ ");
			out.println("  if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT_2&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&APP_STATUS=\"+document.Form1.TXT_APP_STATUS.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("  else if(document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT_DRILL&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow2','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("}");
			
			//comment by nuwan de silva on 23-10-07---------------------------
		/*	out.println("function view_report(){ ");
			out.println("  if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
			out.println("    document.Form1.hid_fin_no.value=\"\";");
			out.println("    document.Form1.hid_client.value=\"\";");
			out.println("    document.Form1.hid_client_name.value=\"\";");
			out.println("}");
			
			//out.println("if(document.Form1.VAL_DAY1.value!=\"\" && document.Form1.VAL_MONTH1.value!=\"\" && document.Form1.VAL_YEAR1.value!=\"\" && document.Form1.VAL_DAY2.value!=\"\" && document.Form1.VAL_MONTH2.value!=\"\" && document.Form1.VAL_YEAR2.value!=\"\"){");
			//out.println("document.Form1.hid_from_date.value=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			//out.println("document.Form1.hid_to_date.value=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&FROM_DATE=\"+document.Form1.hid_from_date.value+\"&TO_DATE=\"+document.Form1.hid_to_date.value+\"&CLIENT_CODE=\"+document.Form1.hid_client.value+\"&CLIENT_NAME=\"+document.Form1.hid_client_name.value+\"&FIN_NO=\"+document.Form1.hid_fin_no.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&APP_STATUS=\"+document.Form1.TXT_APP_STATUS.value+\"\";");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&CLIENT_CODE=\"+document.Form1.hid_client.value+\"&CLIENT_NAME=\"+document.Form1.hid_client_name.value+\"&FIN_NO=\"+document.Form1.hid_fin_no.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&APP_STATUS=\"+document.Form1.TXT_APP_STATUS.value+\"\";");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT_2&CLIENT_CODE=\"+document.Form1.hid_client.value+\"&CLIENT_NAME=\"+document.Form1.hid_client_name.value+\"&FIN_NO=\"+document.Form1.hid_fin_no.value+\"&APP_NO=\"+document.Form1.TXT_APPLICATION_NO.value+\"&APP_STATUS=\"+document.Form1.TXT_APP_STATUS.value+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			//out.println(" } ");
			//out.println("else{");
			//out.println("alert('Please enter Date Range to View Report')");
			//out.println("}");
			
			out.println("}");
				*/
				
				
			out.println("</Script>");
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_name' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_fin_no' VALUE=\"\">");
			out.println("<input  type='hidden' value='REPORT' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Report\">"); 
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Facilities Identified withing a given Period</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
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
			out.println("<BR>");
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
  		
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='20' size='20' onblur=\"makeRequest(document.Form1.TXT_APPLICATION_NO)\" style='{width:230px}'>"); 
			out.println("<input class='but_input' type='button' name='BUT_APP_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");	
			
			
			rs2=stmt1.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			boolean more1=rs2.next();
			String m_day="";
			if(more1){
			 m_day=rs2.getString(1);
			}
			
			/*out.println("<tr class=tr_input>");
			out.println("<td width='30%' ID=VDATE>From Date</td>");
			out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_day.substring(0,2)+"\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_day.substring(3,5)+"\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_day.substring(6,10)+"\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");	
			
			out.println("<tr>"); 
			out.println("<td width='30%' ID=VDATE>To Date</td>");
			out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_day.substring(0,2)+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_day.substring(3,5)+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_day.substring(6,10)+"\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='*%'></td>");
			out.println("</tr>");	
			*/
			
			rs2 = stmt1.executeQuery ("SELECT "+
			" DISPLAY_NAME,POSITION,SCREEN_NAME "+
			" FROM "+m_schema_name+".CO_CO_MAS_USER_SCREEN "+
			" WHERE POSITION IS NOT NULL "+
			" AND DIVISION_CODE='AF' "+
			" AND POSITION_STATUS='Y' "+
			" ORDER BY POSITION ASC");
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_AG'  class=div_input>Agreement Process State</DIV></td>"); 
			out.println("<td width='40%' ><select class=txt_input type=text name=TXT_APP_STATUS maxlength=1 size=1 style='{width:150px}'>");  
			out.println("<option value=\"\" selected>ALL</option>");
			/*out.println("<option value=\"VERIFY2\" >Approval Level</option>");
			out.println("<option value=\"VERIFYL\" >Leasing Level</option>");
			out.println("<option value=\"ACTIVATED\" >Payment Level</option>");*/
					
			boolean more5 = rs2.next();		
			while(more5){
			out.println("<option value="+rs2.getString(3)+" >"+rs2.getString(1)+"</option>");
			more5=rs2.next();
			}
      out.println("</select></td>");
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view_report_2()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else if(m_screen_type.equals("SEC_REPORT")){
			
			String m_app_no = req.getParameter("APP_NO");
			String m_fin_no = req.getParameter("APP_NO");
			String m_client = req.getParameter("CLIENT_CODE");
			String m_name = req.getParameter("CLIENT_NAME");
			//String m_from_date = req.getParameter("FROM_DATE");
			//String m_to_date = req.getParameter("TO_DATE");
			String m_app_status = req.getParameter("APP_STATUS");
						
			String Sql_Data=" SELECT " +
											" DISTINCT A.APPLICATION_NO,B.FINANCE_NO,B.MASTER_AGREEMENT_NO,B.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) "+
											" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_FOLLOW_UP C "+
											" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
											" AND A.REF_NO=C.FOLLOW_UP_NO "+
											" AND REF_NO IS NOT NULL "+
											" AND B.APPLICATION_STATUS<>'CANCEL' "+
											" AND C.STATUS!='COMPLETED' ";


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Facilities Identified withing a given Period </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
				
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
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&FROM_DATE="+m_from_date+"&TO_DATE="+m_to_date+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=100,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr>");
			out.println("<td align=\"right\"  style=\"height: 18px\" ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" style=\"height: 18px\" ></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			//out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Facilities Identified withing a given Period (From : "+m_from_date+" To : "+m_to_date+")</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");


			if(!m_app_status.equals("") &&  m_app_status!=null){
			
			
			rs2 = stmt.executeQuery 
			("SELECT "+
		   " DISTINCT A.APPLICATION_NO APPLICATION_NO, "+//1
			 " NVL(B.FINANCE_NO,'-') FINANCE_NO, "+//2
			 " NVL(MASTER_AGREEMENT_NO,'-') MASTER_NO , "+			 //3
			 " B.CLIENT_CODE CLIENT_CODE, "+//4
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+//5
		   " A.DOCUMENT_TYPE DOCUMENT_CODE, "+//6
			 " "+m_schema_name+".AF_CO_GET_DOC_DESC(A.DOCUMENT_TYPE) DOC_NAME, "+//7
			 " C.DOC_APP_TYPE DOC_APP_TYPE, "+//8
			 " NVL(A.REF_NO,'-') FOLL_NO, "+ //9		
			 " "+m_schema_name+".AF_CO_GET_APPROVED_DATE(A.APPLICATION_NO,'"+m_app_status+"') APP_DATE, "+		//10
			 " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+//11
			 " NVL(A.PRO_INVOICE_NO,'-') INVOICE_NO "+//12
			 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C "+
			 " WHERE UPPER(A.APPLICATION_NO) LIKE UPPER('"+m_app_no+"%') "+
			 " AND A.DOCUMENT_TYPE=C.CODE "+
			 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
			 " AND UPPER(B.APPLICATION_STATUS)=UPPER('"+m_app_status+"') "+
			 //" AND TO_DATE(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    //   " AND TO_DATE(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 " AND A.STATUS='N' "+
			 " ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

				
			}

			else if(m_app_status.equals("") ||  m_app_status==null){
				
				rs2 = stmt.executeQuery ("SELECT "+
		   " DISTINCT A.APPLICATION_NO APPLICATION_NO, "+//1
			 " NVL(B.FINANCE_NO,'-') FINANCE_NO, "+//2
			 " NVL(MASTER_AGREEMENT_NO,'-') MASTER_NO , "+			 //3
			 " B.CLIENT_CODE CLIENT_CODE, "+//4
			 " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) CLIENT_NAME, "+//5
		   " A.DOCUMENT_TYPE DOCUMENT_CODE, "+//6
			 " "+m_schema_name+".AF_CO_GET_DOC_DESC(A.DOCUMENT_TYPE) DOC_NAME, "+//7
			 " C.DOC_APP_TYPE DOC_APP_TYPE, "+//8
			 " NVL(A.REF_NO,'-') FOLL_NO, "+ //9		
			 " "+m_schema_name+".AF_CO_GET_APPROVED_DATE(A.APPLICATION_NO,D.STATUS) APP_DATE, "+		//10
			 " TO_CHAR(A.ENT_DATE,'DD-MM-YYYY') ENT_DATE, "+//11
			 " NVL(A.PRO_INVOICE_NO,'-') INVOICE_NO "+//12
			 " FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_MAS_DOCUMENTS_REQUIRED C, "+
			 " "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL D "+
			 " WHERE UPPER(A.APPLICATION_NO) LIKE UPPER('"+m_app_no+"%') "+
			 " AND A.APPLICATION_NO=B.APPLICATION_NO "+
			 " AND D.APPLICATION_NO=A.APPLICATION_NO "+				
			 " AND UPPER(B.APPLICATION_STATUS) IN ('ACTIVATED','VERIFY2','VERIFYL') "+
			 //" AND TO_DATE(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
    //   " AND TO_DATE(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
			 " AND A.DOCUMENT_TYPE=C.CODE "+
			 " AND A.STATUS='N' "+
			 " ORDER BY "+m_sort_column+" "+m_order_by_type+" ");


			}
			out.println("<br>");
			
			boolean more1=rs2.next();

		
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Application No  ' onclick=sort_data('APPLICATION_NO')>APPLICATION NO</td></TD>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Finance No  ' onclick=sort_data('FINANCE_NO')>FINANCE NO</td></TD>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Agreement No  ' onclick=sort_data('MASTER_NO')>AGREEMENT NO</td></TD>");
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client Code  ' onclick=sort_data('CLIENT_CODE')>CLIENT CODE</td></TD>");
			out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client Name  ' onclick=sort_data('CLIENT_NAME')>CLIENT NAME</td></TD>");
			out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Name  ' onclick=sort_data('DOC_NAME') >DOCUMENT NAME</td>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Type  ' onclick=sort_data('DOC_APP_TYPE') >TYPE</td>"); 
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Follow up No  ' onclick=sort_data('FOLL_NO') >FOLLOW-UP NO</td>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approve Date  ' onclick=sort_data('APP_DATE') >APPROVED DATE</td>"); 

			
			out.println("</tr >");
			int j=0;

			while(more1){
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_application_detail_drill('"+rs2.getString(1)+"')><u>"+rs2.getString(1)+"</TD>");
			if(!rs2.getString(2).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_finance_detail_drill('"+rs2.getString(2)+"')><u>"+rs2.getString(2)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			
			if(!rs2.getString(3).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_master_lease_agreement_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(3)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(5)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_document_drill('"+rs2.getString(6)+"')><u>"+rs2.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(8)+"</TD>");
			
			if(!rs2.getString(9).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_followup_drill('"+rs2.getString(9)+"')><u>"+rs2.getString(9)+"</TD>");			
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(9)+"</TD>");			
			}
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(10)+"</TD>");
			out.println("</tr >");	
			more1=rs2.next();
			j=j+1;
			}
				
			
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
			
			//rs1.close();
			//rs2.close();
			//stmt.close();
			//stmt1.close();
			conn.close();
			out.flush();
			out.close();
			}
			
			
			
			else if(m_screen_type.equals("SEC_REPORT_2")){
			
			String m_app_no = req.getParameter("APP_NO");
			String m_fin_no = req.getParameter("APP_NO");
			String m_client = req.getParameter("CLIENT_CODE");
			String m_name = req.getParameter("CLIENT_NAME");
			String m_app_status = req.getParameter("APP_STATUS");
						
			String Sql_Data=" SELECT " +
											" DISTINCT A.APPLICATION_NO,NVL(B.FINANCE_NO,'-'),NVL(B.MASTER_AGREEMENT_NO,'-'),B.CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) "+
											" FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_FOLLOW_UP C "+
											" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
											" AND A.REF_NO=C.FOLLOW_UP_NO "+
											" AND REF_NO IS NOT NULL "+
											" AND B.APPLICATION_STATUS<>'CANCEL' "+
											//" AND UPPER(A.STAGE) LIKE UPPER('"+m_app_status+"%') "+
											" AND A.STAGE LIKE UPPER('"+m_app_status+"%') "+
											" AND C.STATUS!='COMPLETED' ";


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Facilities Identified withing a given Period </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
				
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
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&FROM_DATE="+m_from_date+"&TO_DATE="+m_to_date+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=100,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("function load_drill(val){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT_DRILL&APP_NO=\"+val+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=100,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr>");
			out.println("<td align=\"right\"  style=\"height: 18px\" ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" style=\"height: 18px\" ></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			//out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Facilities Identified withing a given Period (From : "+m_from_date+" To : "+m_to_date+")</td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");

			
			out.println("<br>");
			rs2 = stmt.executeQuery(Sql_Data); 
			
			boolean more1=rs2.next();

		
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Application No  ' onclick=sort_data('APPLICATION_NO')>APPLICATION NO</td></TD>"); 
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Finance No  ' onclick=sort_data('FINANCE_NO')>FINANCE NO</td></TD>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Agreement No  ' onclick=sort_data('MASTER_NO')>AGREEMENT NO</td></TD>");
			out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client Code  ' onclick=sort_data('CLIENT_CODE')>CLIENT CODE</td></TD>");
			out.println("<td width='20%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Client Name  ' onclick=sort_data('CLIENT_NAME')>CLIENT NAME</td></TD>");
			out.println("<td width='10%' class='txt_report_column' style= cursor:hand; >Document Status</td>"); 
			//out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Document Type  ' onclick=sort_data('DOC_APP_TYPE') >TYPE</td>"); 
			//out.println("<td width='10%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Follow up No  ' onclick=sort_data('FOLL_NO') >FOLLOW-UP NO</td>"); 
			//out.println("<td width='8%' class='txt_report_column' style= cursor:hand; title='Click here to sort by - Approve Date  ' onclick=sort_data('APP_DATE') >APPROVED DATE</td>"); 

			
			out.println("</tr >");
			int j=0;

			while(more1){
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}

			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=load_drill('"+rs2.getString(1)+"')><u>"+rs2.getString(1)+"</TD>");
			if(!rs2.getString(2).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_finance_detail_drill('"+rs2.getString(2)+"')><u>"+rs2.getString(2)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			
			if(!rs2.getString(3).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_master_lease_agreement_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(3)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(5)+"</TD>");
			out.println("<td width='10%' class='txt_report_data' align='left' STYLE='{text-align:left;}' >Pending</td>"); 
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_document_drill('"+rs2.getString(6)+"')><u>"+rs2.getString(7)+"</TD>");
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(8)+"</TD>");
			
			//if(!rs2.getString(9).equals("-")){
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_followup_drill('"+rs2.getString(9)+"')><u>"+rs2.getString(9)+"</TD>");			
			//}
			//else{
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(9)+"</TD>");			
			//}
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(10)+"</TD>");
			out.println("</tr >");	
			more1=rs2.next();
			j=j+1;
			}
				
			
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
			conn.close();
			out.flush();
			out.close();
			}
			
			
			else if(m_screen_type.equals("SEC_REPORT_DRILL")){
			
			String m_app_no = req.getParameter("APP_NO");
			//String m_app_status = req.getParameter("APP_STATUS");
			String m_app_status="";

			String Sql_Data=" SELECT " +
			"    DISTINCT A.APPLICATION_NO, "+ //1
			"    NVL(B.FINANCE_NO,'-'), "+ //2
			"    NVL(B.MASTER_AGREEMENT_NO,'-'), "+ //3
			"    B.CLIENT_CODE, "+ //4
			"    "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+ //5
			"    REF_NO, "+ //6
			"    NVL(STAGE,'-'), "+ //7
			"    NVL(DOCUMENT_TYPE,'-'), "+ //8
			"    NVL(A.STATUS,'-'), "+ //9
			"    NVL(REMARK,'-'), "+ //10
			"    OTHER_NO, "+ //11
			"    NVL(FOLLOWUP_REMARKS,'-'), "+ //12
			"    "+m_schema_name+".AF_CO_GET_DOC_DESC(DOCUMENT_TYPE), "+ //13
			"    A.ENT_STAGE , "+ //14
			"    "+m_schema_name+".AF_CO_GET_SCR_DISPLAY_NAME(STAGE), "+ //15
			"    DECODE(C.STATUS,'INPROGRESS','In Progress','COMPLETED','Completed','PENDING','Pending') "+//16
			"    FROM "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B,"+m_schema_name+".AF_CO_PRO_FOLLOW_UP C "+ 
			"    WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			"    AND A.REF_NO=C.FOLLOW_UP_NO "+
			"    AND REF_NO IS NOT NULL "+
			"    AND B.APPLICATION_STATUS<>'CANCEL' "+
			"    AND C.STATUS!='COMPLETED' "+
			//"    AND UPPER(A.APPLICATION_NO)=UPPER('"+m_app_no+"') "+
			//"    AND UPPER(A.ENT_STAGE) LIKE UPPER('"+m_app_status+"%') ";
			"    AND A.APPLICATION_NO=UPPER('"+m_app_no+"') "+
			"    AND A.ENT_STAGE LIKE UPPER('"+m_app_status+"%') ";




			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Facilities Identified withing a given Period </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
				
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
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&FROM_DATE="+m_from_date+"&TO_DATE="+m_to_date+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Security_and_Marketting_file_3?chksql=SEC_REPORT&APP_STATUS="+m_app_status+"&CLIENT_CODE="+m_client+"&CLIENT_NAME="+m_name+"&FIN_NO="+m_fin_no+"&APP_NO="+m_app_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=60,top=100,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");
			
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			out.println("<tr>");
			out.println("<td align=\"right\"  style=\"height: 18px\" ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td align=\"center\" style=\"height: 18px\" ></td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");

			out.println("<br>");
			rs2 = stmt.executeQuery(Sql_Data); 
			
			boolean more=rs2.next();
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='*%' align='center' >No Pending Pending Document For Application No : "+m_app_no+" </td></TD>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='*%' align='center' >Pending Document For Application No : "+rs2.getString(1)+" </td></TD>"); 
			out.println("</tr>");
			out.println("<tr class='pdn_txtpos2'>");
			out.println("<td width='*%' align='center' >Client Name : "+rs2.getString(5)+" </td></TD>"); 
			out.println("</tr>");
			out.println("</table>");
			}
			
		  out.println("<br><br>");
		
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class='pdn_txtpos2'>");
			
			out.println("<td width='10%' class='txt_report_column' >Follow up No</td></TD>"); 
			out.println("<td width='10%' class='txt_report_column' >Document Code</td></TD>");
			out.println("<td width='10%' class='txt_report_column' >Document Name</td></TD>");
			out.println("<td width='10%' class='txt_report_column' >Remarks</td></TD>");
			out.println("<td width='10%' class='txt_report_column' >Follow up Remarks</td></TD>");
			out.println("<td width='10%' class='txt_report_column' >Status</td></TD>"); //style= cursor:hand; title='Click here to sort by - Client Name  ' onclick=sort_data('CLIENT_NAME')
			
			
			out.println("</tr >");
			int j=0;

			while(more){
			String m_stage=rs2.getString(7);
			out.println("<tr class=pdn_txtpos2 ><td colspan=6 align=left ><b>Screen Level : - &nbsp;&nbsp;&nbsp;&nbsp;"+rs2.getString(15)+"</b></td></tr>"); 
			while(m_stage.equals(rs2.getString(7))){
			
			if(j>0 && j%2==1){
		  out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
						
      out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}'  onclick=show_followup_drill('"+rs2.getString(6)+"') ><u>"+rs2.getString(6)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}'  onclick=show_document_drill('"+rs2.getString(8)+"') ><u>"+rs2.getString(8)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}'  onclick=show_document_drill('"+rs2.getString(8)+"') ><u>"+rs2.getString(13)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' >"+rs2.getString(10)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' >"+rs2.getString(12)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' >"+rs2.getString(16)+"</TD>");
    // 	row=row+1;
			more=rs2.next();
			if(!more){
			break;
			}
			//i=i+1;

			}
			//m_stage=rs.getString(7);
			
			
/*			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_application_detail_drill('"+rs2.getString(1)+"')><u>"+rs2.getString(1)+"</TD>");
			if(!rs2.getString(2).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_finance_detail_drill('"+rs2.getString(2)+"')><u>"+rs2.getString(2)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			
			if(!rs2.getString(3).equals("-")){
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_master_lease_agreement_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(3)+"</TD>");
			}
			else{
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >-</TD>");
			}
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_client('"+rs2.getString(4)+"')><u>"+rs2.getString(5)+"</TD>");
			out.println("<td width='10%' class='txt_report_data' align='left' STYLE='{text-align:left;}' >Pending</td>"); 
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_document_drill('"+rs2.getString(6)+"')><u>"+rs2.getString(7)+"</TD>");
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(8)+"</TD>");
			
			//if(!rs2.getString(9).equals("-")){
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' STYLE='{cursor:hand;}' onclick=show_followup_drill('"+rs2.getString(9)+"')><u>"+rs2.getString(9)+"</TD>");			
			//}
			//else{
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(9)+"</TD>");			
			//}
			//out.println("<TD class='txt_report_data' align='left' STYLE='{text-align:left;}' >"+rs2.getString(10)+"</TD>");
			out.println("</tr >");	
			more1=rs2.next();
			j=j+1;
			
			*/
			
			}
				
			
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


