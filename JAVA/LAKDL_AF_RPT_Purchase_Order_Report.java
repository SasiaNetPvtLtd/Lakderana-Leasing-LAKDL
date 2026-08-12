
//--
//SCREEN NAME:PURCHASE ORDER REPORT
//CREATED BY:CHANDANA
//DATE/TIME:28/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Purchase_Order_Report?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
// Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Purchase_Order_Report extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs;
		PreparedStatement pstmt;
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
			
			
			
			if(m_screen_type.trim().equals("main_page1")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql_report\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];");
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Purchase Order Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Purchase Order Report  - \"+document.Form1.hid_status.value;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Purchase_Order_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Purchase_Order_Report?chksql=main_page1';"); 
			out.println("}"); 
			
			//Modified by Mahela on 10-04-2007
			out.println("function load_data_frame(){ ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Purchase_Order_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\" \");  ");
			// Modified by Thamali Jayatunga on 2009.10.14, Added if condition. 	
			out.println(" if (validate_date()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Purchase_Order_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
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
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_from_date.value=date1");
			//	out.println("alert(document.Form1.hid_from_date.value);");
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

			//-------- End by Chandana on 07/05/2007 ---------//
			
	 		// Modified by Thamali Jayatunga on 2009.10.14, Added function validate_date. 	
	 		out.println("function validate_date(){");
   		out.println("  m_from_dd = document.Form1.VAL_DAY1.value ");
   		out.println("  m_from_mm = document.Form1.VAL_MONTH1.value ");
   		out.println("  m_from_yy = document.Form1.VAL_YEAR1.value ");
   		out.println("  m_to_dd = document.Form1.VAL_DAY2.value ");
   		out.println("  m_to_mm = document.Form1.VAL_MONTH1.value ");
   		out.println("  m_to_yy = document.Form1.VAL_YEAR2.value ");
   		out.println(" if(m_from_dd != '' && m_from_mm !='' && m_from_yy !=''  ) { ");
   		out.println("    if(!checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){  "); 
   		out.println("     return false;"); 
   		out.println("     }");
   		out.println("    else {");
   		out.println("    if(m_to_dd != '' && m_to_mm !='' && m_to_yy !=''  ) { ");
   		out.println("        if(checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)){  "); 
   		out.println("         return true;"); 
   		out.println("       }");
   		out.println("        else "); 
   		out.println("         return false; "); 
   		out.println("     }");
   		out.println("   else {");
   		out.println("     alert('To Date cannot be empty ')");
   		out.println("     return false;"); 
   		out.println("     }");
   		out.println("    }");
   		out.println("  }");
   		out.println(" else { ");
   		out.println("   alert('From Date cannot be empty ')");
   		out.println("   return false;"); 
   		out.println("  }");
   		out.println(" }"); 

				
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Purchase Order Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
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
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Purchase Order No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_PURCHASE_ORDER_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			out.println("<tr class=tr_input>");
				// Modified by Thamali Jayatunga on 2009.10.14, Remove check_date event.
				out.println("<td width='12%' ID=VDATE>Purchase Order Date</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
				out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				
				// Modified by Thamali Jayatunga on 2009.10.14, Remove check_date event.
				out.println("<td width='7%' ID=VDATE>To</td>");
				out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)\"> ");
				out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=\"checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)\"> ");
				out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)\"><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");
				
				out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_data_frame()\"></td>"); 
			  out.println("<td width='*%'></td>");
				out.println("</tr>");
			
			
			
			
			
			out.println("</table>");
				
			
			
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){
      
			String m_Purch_ord_no= req.getParameter("PURCH_ORD_NO"); 
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Purchase Order Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("</script>"); 

			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0'>"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			
			/*out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' >"); 
			//out.println("<tr><td height='30' class='pdn_mainHD'>Asset Financing System</td></tr>");
			out.println("<tr><td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td></tr>");
			
			out.println("<tr><td height='1'><img src='spacer.gif' height='1'></td></tr>"); 
			out.println("<tr>"); 
			out.println("<td>"); 
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%'>"); 
			out.println("<tr><td height='1'><img height='1' src='spacer.gif' width='1' /></td></tr>"); 
			out.println("<tr><td align='left' class='pdn_txtpos2' style='height: 18px'>Purchase Order Report</td></tr>"); 
			out.println("</table>");  
			out.println("</table>");  
			out.println("</BR>");
			out.println("</BR>");

		/*	out.println("<table align='center' width='100%' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='12%' class='txt_report_column'>PURCHASE ORDER NO</td>"); 
			out.println("<td width='12%' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>VENDER NAME</td>"); 
			out.println("<td width='8%' class='txt_report_column'>ISSUED DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TOTAL NET</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TOTAL VAT</td>"); 
			out.println("<td width='8%' class='txt_report_column'>PURCHASE ORDER DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>BRANCH CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ACC NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>CHEQUE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPROVAL STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>ID NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PAYER NAME</td>"); 
			out.println("</tr >"); 
			out.println("</table >"); 
			
		*/	
			
			
			out.println("<DIV STYLE='{position:absolute; top:40; left:0 cursor: hand;}'>");
			out.println("<table align='center' width='1425' class='table' border=1>"); 
		
			if(m_Purch_ord_no.equals("") && !m_from_date.equals("") && !m_to_date.equals("")){
			//Modified by Mahela on 10-04-2007
			pstmt = conn.prepareStatement(" SELECT A.PURCHASE_ORDER_NO, "+
			                                      " A.APPLICATION_NO, "+
																						" B.NAME, "+
																						" NVL(TO_CHAR(ISSUED_DATE,'DD-MON-YYYY'),'-'), "+
																						" DECODE(A.ACTIVE_STATUS,'CANCEL','Deleted','PRINT','Cheque Printed','VERIFY','Verified','ENT','Entered Level','TEMP','Temparery App. Level',A.ACTIVE_STATUS) STATUS, "+
																						" A.TOTAL_NET, "+
																						" A.TOTAL_VAT, "+
																						" NVL(TO_CHAR(PURCHASE_ORDER_DATE,'DD-MON-YYYY'),'-'), "+
																						" NVL(BRANCH_CODE,'-'), "+
																						" NVL(ACC_NO,'-'), "+
																						" NVL(CHEQUE_NO,'-'), "+
																						" DECODE(A.APPROVAL_STATUS,'Y','Yes','N','No','','Verified'), "+
																						" NVL(TO_CHAR(SUPPLIER_APPROVAL_DATE,'DD-MON-YYYY'),'-'), "+
																						" NVL(ID_NO,'-'), "+
																						" NVL(PAYER_NAME,'-'),"+
																						" B.VENDOR_CODE, "+
																						" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-') "+
																						" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
																						" "+m_schema_name+".AF_CO_MAS_VENDORS B "+
																						" WHERE B.VENDOR_CODE=A.VENDER_CODE AND "+
																						" TO_DATE(TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
                                            " TO_DATE(TO_CHAR(PURCHASE_ORDER_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
          
					}else {
						
		pstmt = conn.prepareStatement(" SELECT A.PURCHASE_ORDER_NO, "+
			                                      " A.APPLICATION_NO, "+
																						" B.NAME, "+
																						" NVL(TO_CHAR(ISSUED_DATE,'DD-MON-YYYY'),'-'), "+
																						" DECODE(A.ACTIVE_STATUS,'CANCEL','Deleted','PRINT','Cheque Printed','VERIFY','Verified','ENT','Entered Level','TEMP','Temparery App. Level',A.ACTIVE_STATUS) STATUS, "+
																						" A.TOTAL_NET, "+
																						" A.TOTAL_VAT, "+
																						" NVL(TO_CHAR(PURCHASE_ORDER_DATE,'DD-MON-YYYY'),'-'), "+
																						" NVL(BRANCH_CODE,'-'), "+
																						" NVL(ACC_NO,'-'), "+
																						" NVL(CHEQUE_NO,'-'), "+
																						" DECODE(A.APPROVAL_STATUS,'Y','Yes','N','No','','Verified'), "+
																						" NVL(TO_CHAR(SUPPLIER_APPROVAL_DATE,'DD-MON-YYYY'),'-'), "+
																						" NVL(ID_NO,'-'), "+
																						" NVL(PAYER_NAME,'-'), "+
																						" B.VENDOR_CODE, "+
																						" NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BRANCH_CODE),'-') "+
																						" FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
																						" "+m_schema_name+".AF_CO_MAS_VENDORS B "+
																						" WHERE B.VENDOR_CODE=A.VENDER_CODE AND "+
						  															" A.PURCHASE_ORDER_NO LIKE UPPER('"+m_Purch_ord_no+"%')");
   				}                                  
		
		
		
		rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			while(more){
						out.println("<tr >"); 
						out.println("<TD width='100' class='txt_report_data' align='right' STYLE='{text-align:right; cursor:hand;}' onclick=show_purchase_order_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></TD>");
						out.println("<TD width='100' class='txt_report_data' STYLE='{text-align:right; cursor:hand;}' onclick=show_application_detail_drill('"+rs.getString(2)+"')><U>"+rs.getString(2)+"</U></TD>");
						out.println("<TD width='100' class='txt_report_data' align='right' STYLE='{text-align:right; cursor:hand;}' onclick=show_vendor_drill('"+rs.getString(16)+"') ><u>"+rs.getString(3)+"</u></TD>");
						out.println("<TD width='75' class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(7))+"</TD>");
						out.println("<TD width='75' class='txt_report_data' align='right'>"+rs.getString(8)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(9)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(17)+"</TD>"); //
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(10)+"</TD>");						
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(11)+"</TD>");
						out.println("<TD width='75' class='txt_report_data' align='right'>"+rs.getString(12)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(14)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+rs.getString(15)+"</TD>");
						
					//	out.println("<td width='8%' STYLE='{text-align:right; cursor:hand;}' onclick=show_model_details_drill('"+rs.getString(7)+"')>"+rs.getString(7)+"</td>");
						
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>");
			out.println("</div>"); 
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
      out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1425; height: 15'>");


			
			out.println("<table align='center' width='1425' class='table' border=1>"); 
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100' class='txt_report_column'>PURCHASE ORDER NO</td>"); 
			out.println("<td width='100' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='100' class='txt_report_column'>VENDER NAME</td>"); 
			out.println("<td width='75' class='txt_report_column'>ISSUED DATE</td>"); 
			out.println("<td width='100' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='100' class='txt_report_column'>TOTAL NET</td>"); 
			out.println("<td width='100' class='txt_report_column'>TOTAL VAT</td>"); 
			out.println("<td width='75' class='txt_report_column'>PURCHASE ORDER DATE</td>"); 
			out.println("<td width='100' class='txt_report_column'>BRANCH CODE</td>"); 
			out.println("<td width='100' class='txt_report_column'>BRANCH NAME</td>");
			out.println("<td width='100' class='txt_report_column'>ACC NO</td>"); 
			out.println("<td width='100' class='txt_report_column'>CHEQUE NO</td>"); 
			out.println("<td width='75' class='txt_report_column'>APPROVAL STATUS</td>"); 
			out.println("<td width='100' class='txt_report_column'>ID NO</td>"); 
			out.println("<td width='100' class='txt_report_column'>PAYER NAME</td>"); 
			out.println("</tr >");
			out.println("</table >"); 
			out.println("</div>"); 
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width: 0; height: 0'></DIV>");
    
			
			
			
			
			
			
			
			
			
			
		/*	out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr><td width='100%' class='note'></td></tr>"); 
			out.println("</table>"); */
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
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


