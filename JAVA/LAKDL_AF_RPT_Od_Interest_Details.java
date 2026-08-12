
//--
//SCREEN NAME:OD INTEREST DETAILS(MONTHLY)
//CREATED BY:
//DATE/TIME:
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Od_Interest_Details?chksql=MAIN&
  
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Od_Interest_Details extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs1;
		PreparedStatement pstmt,pstmt1;
		java.text.NumberFormat nf;

public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 

		try { 

			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);

			//SCREEN_METHODS con_method = new SCREEN_METHODS(); 
	LAKDL_AF_CO_conn_methods con_method=new LAKDL_AF_CO_conn_methods();
	
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
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			
      if(m_screen_type.trim().equals("main_page1")){	
					String m_date_dd = "";
					String m_date_mm = "";
					String m_date_yy = "";
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			
			
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
					//Commented by Dineth on 03-04-2009
					/*out.println("document.Form1.VAL_DAY2.value   =\""+m_date_dd+"\"");
					out.println("document.Form1.VAL_MONTH2.value =\""+m_date_mm+"\"");
					out.println("document.Form1.VAL_YEAR2.value  =\""+m_date_yy+"\"");*/
					out.println("}");
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_OD_INTEREST_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_OD_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_OD_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			//Added by Dineth on 13-03-2009
			out.println("function help_update1() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"98\";");
			out.println("    m_sql = \"m_help_TXT_FinanceSql_new3\";");//Modified by Dineth on 20-03-2009 
			out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'98');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_98() {");
			out.println("    document.Form1.TXT_FIN_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			out.println("function check_date(objdd,objmm,objyy) {");						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
     	out.println("}");
			out.println("}");
			    
			//End by Dineth on 13-03-2009
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
			out.println("		if(IfCount==\"98\"){"); 
			out.println("		help_update_value_assign_98();"); 
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
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=main_page1';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_od_interest_details_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Od Interest Details(Monthly) - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Od Interest Details(Monthly) \";"); 
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
			
				//------ Added by Chandana on 07/05/2007 -----///
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
			//out.println("date1=v_date+'-'+v_month+'-'+val;");//Commented by Dineth on 13-03-2009
			out.println("date1=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");//Added by Dineth on 13-03-2009
			out.println("document.Form1.hid_from_date.value=date1");
			//	out.println("alert(document.Form1.hid_from_date.value);");
			out.println("}");
			//Commented by Dineth on 03-04-2009
			/*	
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
			*/
			out.println("}");

			//-------- End by Chandana on 07/05/2007 ---------//

			
			
			
			out.println("function load_data_frame(){ ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_OD_NO.value+\" \");  ");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_OD_NO.value+\" \";");
			out.println("date3=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");//Added by Dineth on 13-03-2009
			//Commented by Dineth on 03-04-2009
			//out.println("date4=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");//Added by Dineth on 13-03-2009
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&FINANCE_NO=\"+document.Form1.TXT_FIN_NO.value+\"&from_date=\"+date3+\"&to_date=\"+date4+\"&sort_column=FINANCE_NO&order_by_type=DESC \";");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&FINANCE_NO=\"+document.Form1.TXT_FIN_NO.value+\"&from_date=\"+date3+\"&sort_column=FINANCE_NO&order_by_type=DESC \";");
			
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
				
				
			out.println("</Script>");
			
			out.println("<body onload=\"get_sys_date();\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Od Interest Details(Monthly) </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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
  		/*out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_OD_NO'  class=div_input>ODI Ref No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_OD_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_OD_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");*/
			//Added by Dineth on 13-03-2009
			out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FIN_NO'  class=div_input>Finance No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FIN_NO' value=\"Help\" onClick=\"help_update1()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			//End by Dineth on 13-03-2009
			
			//---- Added by Chandana on 07/04/2007 -----//
			out.println("<tr class=tr_input>");
			out.println("<td width='12%' ID=VDATE>As At Date</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
				
			/*out.println("<td width='7%' ID=VDATE>To</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");*/
				
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_data_frame()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
      //----- End Chandana on 07/05/2007  --------------//
			
			out.println("</table>");
				
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else	if(m_screen_type.equals("MAIN")){
			
			String m_finance_no = req.getParameter("FINANCE_NO");
			String m_from_date = req.getParameter("from_date");
			//String m_to_date = req.getParameter("to_date");
			String m_fin_no_3 = "";
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Od Interest Details(Monthly)</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			
			out.println("function load_odi_details(m_fin_no,m_from_date){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=ODI_MONTH_WISE&FROM_DATE=\"+m_from_date+\"&FINANCE_NO=\"+m_fin_no;");
			out.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
			out.println(" } ");
				
			
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
				//Added by Dineth on 13-03-2009
				if(m_finance_no.equals("") && !m_from_date.equals("")){
	     // out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&FINANCE_NO=&from_date="+m_from_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				}
				else{
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=MAIN&FINANCE_NO="+m_finance_no+"&from_date="+m_from_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				}
				//End by Dineth on 13-03-2009
				out.println(" window.location.href=m_url;");
				
				out.println("}");
			
			
			
			
			
			
			
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			pstmt = conn.prepareStatement(
			" SELECT C.FINANCE_NO , NVL(SUM(A.ODI_CAL_AMOUNT),0), "+ 
			" NVL(SUM(A.ODI_BAL_AMOUNT),0),NVL(SUM(A.ODI_SETTLED_AMOUNT),0),NVL(SUM(A.ADJUSTED_AMOUNT),0) "+ 
			" FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
			" WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO=C.FINANCE_NO AND "+
			" TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+ 
			//" TO_DATE(TO_CHAR(C.ACTIVATED_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+//Commented by Dineth on 03-04-2009
			" C.FINANCE_NO LIKE '%"+m_finance_no+"%' "+
			" AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL','NORM_TERMI','TERMI','TERMINATED')"+
			" GROUP BY C.FINANCE_NO "); 
			
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			out.println("<DIV STYLE='{position:absolute; top:0; left:0 cursor: hand;}'>");
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			while(more){
						/*
						m_fin_no_3=rs.getString(1);
						pstmt1 = conn.prepareStatement(" SELECT C.FINANCE_NO,NVL(SUM(A.ODI_CAL_AMOUNT),0), "+ 
                                      " NVL(SUM(A.ODI_BAL_AMOUNT),0),NVL(SUM(A.ODI_SETTLED_AMOUNT),0),NVL(SUM(A.ADJUSTED_AMOUNT),0) "+ 
                                      " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+ 
                                      " WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO=C.FINANCE_NO AND "+
                                      " C.FINANCE_NO LIKE '%"+m_fin_no_3+"%' "+
																			" AND APPLICATION_STATUS IN ('ACTIVATED','LEGAL','NORM_TERMI','TERMI','TERMINATED')"+
																			" GROUP BY C.FINANCE_NO "); 
						
						rs1=pstmt1.executeQuery();
						boolean more3=rs1.next();
						*/
						
			
						out.println("<tr >"); 
						out.println("<TD width='20%' class='txt_report_data' STYLE='{cursor:hand;}' onclick=\"show_finance_detail_drill('"+rs.getString(1)+"');\"><U>"+rs.getString(1)+"</U></TD>");
						//out.println("<TD width='100' class='txt_report_data' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(2)+"');\"><U>"+rs.getString(2)+"</U></TD>");
						//out.println("<TD width='100' class='txt_report_data' >"+rs.getString(3)+"</TD>");
						out.println("<TD width='20%' class='txt_report_data' align='right' STYLE='{cursor:hand;}' onclick=\"load_odi_details('"+rs.getString(1)+"','"+m_from_date+"');\"><u>"+nf.format(rs.getDouble(2))+"</u></TD>");
						out.println("<TD width='20%' class='txt_report_data' align='right' STYLE='{cursor:hand;}' onclick=\"load_odi_details('"+rs.getString(1)+"','"+m_from_date+"');\"><u>"+nf.format(rs.getDouble(4))+"</u></TD>");
						out.println("<TD width='20%' class='txt_report_data' align='right' STYLE='{cursor:hand;}' onclick=\"load_odi_details('"+rs.getString(1)+"','"+m_from_date+"');\"><u>"+nf.format(rs.getDouble(5))+"</u></TD>");
						out.println("<TD width='20%' class='txt_report_data' align='right' STYLE='{cursor:hand;}' onclick=\"load_odi_details('"+rs.getString(1)+"','"+m_from_date+"');\"><u>"+nf.format(rs.getDouble(3))+"</u></TD>");
						//out.println("<TD width='100' class='txt_report_data' >"+rs.getString(8)+"</TD>");
						//out.println("<TD width='100' class='txt_report_data' >"+rs.getString(9)+"</TD>");
						//out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(10))+"</TD>");
						//out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(11))+"</TD>");
						out.println("</tr >");
						
						more=rs.next(); 
			} 
			out.println("</table>"); 
			out.println("</DIV>");
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   		out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 100%; height: 0'>");
				
				
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr>"); 
			out.println("<td width='20%' ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\"></td>"); 
			out.println("<td width='20%' ></td>"); 
			out.println("<td width='20%' ></td>"); 
			out.println("<td width='20%' ></td>"); 
			out.println("<td width='20%' ></td>"); 
			
			out.println("</tr >");
			out.println("</table>"); 
				
				
				
			out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='20%' class='txt_report_column' style='text-align:left;'>FINANCE NO</td>"); 
			//out.println("<td width='100' class='txt_report_column' style='cursor:hand;text-align:left;' onclick=sort_data('A.INVOICE_NO')>INVOICE NO</td>"); 
			//out.println("<td width='100' class='txt_report_column' style='cursor:hand;text-align:left;' onclick=sort_data('A.ODI_DATE')>ODI DATE</td>"); 
			out.println("<td width='20%' class='txt_report_column' align='right'>ACTUAL ODI</td>"); 
			out.println("<td width='20%' class='txt_report_column' align='right'>AMOUNT COLLECTED</td>"); 
			out.println("<td width='20%' class='txt_report_column' align='right'>AMOUNT WAVED OFF</td>"); 
			out.println("<td width='20%' class='txt_report_column' align='right'>ODI BAL AMOUNT</td>"); 
			//out.println("<td width='100' class='txt_report_column' style='cursor:hand;text-align:left;' onclick=sort_data('A.ADJUSTED_DATE')>WAVED OFF DATE</td>"); 
			//out.println("<td width='100' class='txt_report_column' style='cursor:hand;text-align:left;' onclick=sort_data('A.SETTLED_DATE')>COLLECTED DATE</td>"); 
			//out.println("<td width='100' class='txt_report_column' align='right'>ODI BAL AMOUNT</td>"); 
			//out.println("<td width='100' class='txt_report_column' align='right'>ODI SETTLED AMOUNT</td>"); 
			out.println("</tr >");
			out.println("</table>"); 
			out.println("</div>"); 
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
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
			else	if(m_screen_type.equals("ODI_DETAILS")){
					String m_fin_no_1=req.getParameter("FINANCE_NO");
					String m_odi_date=req.getParameter("ODI_DATE");
					
					
										pstmt = conn.prepareStatement(" SELECT A.INVOICE_NO,NVL(SUM(A.ODI_CAL_AMOUNT),0), "+ 
                                        " NVL(SUM(A.ODI_BAL_AMOUNT),0),NVL(SUM(A.ODI_SETTLED_AMOUNT),0),NVL(SUM(A.ADJUSTED_AMOUNT),0) "+ 
                                        " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
                                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO=C.FINANCE_NO AND "+ 
                                        " C.FINANCE_NO='"+m_fin_no_1+"' AND TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')=TO_DATE('"+m_odi_date+"','DD-MM-YYYY')"+
                                        " GROUP BY A.INVOICE_NO ");
																				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Od Interest Details(Monthly)</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					
					out.println("</script>");
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
					out.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Finance No - "+m_fin_no_1 +"</td></tr>");
					out.println("</table>");
					
					rs=pstmt.executeQuery(); 
					double m_act_odi=0;
					double m_bal_odi=0;
					double m_collected=0;
					double m_waved_off=0;
			
					boolean more=rs.next();
					if(!more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					out.println("</table>");
					
					
					}
					if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr>");
					out.println("<td width='20%' ><b>Invoice No</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Actual ODI</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>ODI Bal Amount</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Amount Collected</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Amount Waved Off</b></td>");
					out.println("</tr>");
					while(more){
					out.println("<tr>");
					out.println("<td width='20%' STYLE='{cursor:hand;}' onclick=\"show_invoice_drill('"+rs.getString(1)+"');\"><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width='20%' style='text-align:right'>"+nf.format(rs.getDouble(2))+"</td>");
					out.println("<td width='20%' style='text-align:right'>"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='20%' style='text-align:right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td width='20%' style='text-align:right'>"+nf.format(rs.getDouble(5))+"</td>");
					out.println("</tr>");
					m_act_odi=m_act_odi+rs.getDouble(2);
					m_bal_odi=m_bal_odi+rs.getDouble(3);
					m_collected=m_collected+rs.getDouble(4);
					m_waved_off=m_waved_off+rs.getDouble(5);
					
					more=rs.next();
					}
					out.println("<tr>");
					out.println("<td width='20%'>&nbsp;</td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_act_odi)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_bal_odi)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_collected)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_waved_off)+"</b></td>");
					out.println("</tr>");
					
					
					
					
					out.println("</table>");
					}
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("</body>"); 
					out.println("</html>"); 
		
			
			
					
		
			
			}
			else if(m_screen_type.equals("ODI_MONTH_WISE")){
				String m_fin_no_2=req.getParameter("FINANCE_NO");
				String m_from_date_2=req.getParameter("FROM_DATE");
					
					pstmt = conn.prepareStatement(" SELECT TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),TO_CHAR(A.ODI_DATE,'Month YYYY'),NVL(SUM(A.ODI_CAL_AMOUNT),0), "+ 
                                        " NVL(SUM(A.ODI_BAL_AMOUNT),0),NVL(SUM(A.ODI_SETTLED_AMOUNT),0),NVL(SUM(A.ADJUSTED_AMOUNT),0) "+ 
                                        " FROM "+m_schema_name+".AF_CO_PRO_OD_INTEREST_MONTHLY A,"+m_schema_name+".AF_CO_PRO_INVOICE B,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
                                        " WHERE A.INVOICE_NO=B.INVOICE_NO AND B.FINANCE_NO=C.FINANCE_NO AND "+ 
                                        " C.FINANCE_NO='"+m_fin_no_2+"' AND TO_DATE(TO_CHAR(A.ODI_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')<=TO_DATE('"+m_from_date_2+"','DD-MM-YYYY') "+
                                        " GROUP BY A.ODI_DATE ");
																				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Od Interest Details(Monthly)</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function load_odi_details_invoice(m_fin_no,m_odi_date){ ");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Od_Interest_Details?chksql=ODI_DETAILS&ODI_DATE=\"+m_odi_date+\"&FINANCE_NO=\"+m_fin_no;");
					out.println("popupwin=window.open(m_url,\"popupwin1\",\"status=0,menubar=0,scrollbars=1,height=500,width=700,resizable=1\");");
					out.println(" } ");
				
					out.println("</script>");
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
					out.println("<FORM NAME='Form1' method='post'>");
					out.println("<table align='center' width='100%' class='table' border=0 cellspacing=\"0\" cellpadding=\"1\">");
					out.println("<tr><td class=\"pdn_txtpos2\" style='text-align:center'>Finance No - "+m_fin_no_2 +"</td></tr>");
					out.println("</table>");
					
					rs=pstmt.executeQuery(); 
					double m_act_odi=0;
					double m_bal_odi=0;
					double m_collected=0;
					double m_waved_off=0;
			
					boolean more=rs.next();
					if(!more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr><td style='text-align:center'><b>No Records</b></td></tr>");
					out.println("</table>");
					
					
					}
					if(more){
					out.println("<br>");
					out.println("<table align='center' width='100%' class='table' border=0>");
					out.println("<tr>");
					out.println("<td width='20%' ><b>Odi Date</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Actual ODI</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>ODI Bal Amount</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Amount Collected</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>Amount Waved Off</b></td>");
					out.println("</tr>");
					while(more){
					out.println("<tr>");
					out.println("<td width='20%' STYLE='{cursor:hand;}'>"+rs.getString(2)+"</td>");
					out.println("<td width='20%' style='text-align:right' STYLE='{cursor:hand;}' onclick=\"load_odi_details_invoice('"+m_fin_no_2+"','"+rs.getString(1)+"');\"><u>"+nf.format(rs.getDouble(3))+"</u></td>");
					out.println("<td width='20%' style='text-align:right' STYLE='{cursor:hand;}' onclick=\"load_odi_details_invoice('"+m_fin_no_2+"','"+rs.getString(1)+"');\"><u>"+nf.format(rs.getDouble(4))+"</u></td>");
					out.println("<td width='20%' style='text-align:right' STYLE='{cursor:hand;}' onclick=\"load_odi_details_invoice('"+m_fin_no_2+"','"+rs.getString(1)+"');\"><u>"+nf.format(rs.getDouble(5))+"</u></td>");
					out.println("<td width='20%' style='text-align:right' STYLE='{cursor:hand;}' onclick=\"load_odi_details_invoice('"+m_fin_no_2+"','"+rs.getString(1)+"');\"><u>"+nf.format(rs.getDouble(6))+"</u></td>");
					out.println("</tr>");
					m_act_odi=m_act_odi+rs.getDouble(3);
					m_bal_odi=m_bal_odi+rs.getDouble(4);
					m_collected=m_collected+rs.getDouble(5);
					m_waved_off=m_waved_off+rs.getDouble(6);
					
					more=rs.next();
					}
					out.println("<tr>");
					out.println("<td width='20%'>&nbsp;</td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_act_odi)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_bal_odi)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_collected)+"</b></td>");
					out.println("<td width='20%' style='text-align:right'><b>"+nf.format(m_waved_off)+"</b></td>");
					out.println("</tr>");
					
					
					
					
					out.println("</table>");
					}
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("</body>"); 
					out.println("</html>"); 
		
			
			
					
		

			
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


