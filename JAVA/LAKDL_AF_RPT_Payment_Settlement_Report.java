
//--
//SCREEN NAME:PAYMENT SETTLEMENT REPORT 
//CREATED BY:CHANDANA
//DATE/TIME:30/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Payment_Settlement_Report ?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
	//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Payment_Settlement_Report extends javax.servlet.http.HttpServlet { 

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
			String m_sort_column   = "A.PAYMENT_NO";	
			String m_order_by_type = "ASC";
			
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
			out.println("    m_sql = \"m_help_TXT_PAYMENT_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_RECIEPT_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_RECIEPT_NO.value=oBj.valout[2];");
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
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Payment_Settlement_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Payment_Settlement_Report?chksql=main_page1';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_application_process_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Payment Settlement Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Payment Settlement Report \";"); 
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
			//added by validate_date() function madhawa 2009-10-14
			out.println("function validate_date(){");
   		out.println("  m_from_dd = document.Form1.VAL_DAY1.value ");
   		out.println("  m_from_mm = document.Form1.VAL_MONTH1.value ");
   		out.println("  m_from_yy = document.Form1.VAL_YEAR1.value ");
   		out.println("  m_to_dd = document.Form1.VAL_DAY2.value ");
   		out.println("  m_to_mm = document.Form1.VAL_MONTH2.value ");
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
   		out.println("     alert('To Date cannot be null ')");
   		out.println("     return false;"); 
   		out.println("     }");
   		out.println("    }");
   		out.println("  }");
   		out.println(" else { ");
   		out.println("   alert('From Date cannot be null ')");
   		out.println("   return false;"); 
   		out.println("  }");
   		out.println(" }"); 
			//end by madhawa validate_date() function 2009-10-14
			
			out.println("function load_data_frame(){ ");
			//modified by madhawa 2009-10-14 added below condition
			out.println("if(validate_date()){");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Payment_Settlement_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_RECIEPT_NO.value+\" \");  ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Payment_Settlement_Report?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_RECIEPT_NO.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&sort_column=A.PAYMENT_NO&order_by_type=DESC \";");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=\"+document.Form1.TXT_OFFER_NO.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\"&sort_column=OFFER_NO&order_by_type=DESC \";");
  		out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
			//modified by madhawa 2009-10-14 
			out.println(" } ");
				
				
			out.println("</Script>");
			
			out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Payment Settlement Report</td>");
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
  		out.println("<tr>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_RECIEPT_NO'  class=div_input>Payment Settlement No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_RECIEPT_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_RECIEPT_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			//---- Added by Chandana on 07/04/2007 -----//
			out.println("<tr class=tr_input>");
			out.println("<td width='12%' ID=VDATE>Date</td>");
			 
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  > ");
			//modified by madhawa add call to "checkMonthLength" on onblur event
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
				
			out.println("<td width='7%' ID=VDATE>To</td>");
			
			out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  > ");
			//modified by madhawa add call to "checkMonthLength" on onblur event
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			//modified by madhawa add call to "checkMonthLength" on onblur event
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=\"checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)\" ><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
				
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_data_frame()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
      //----- End Chandana on 07/05/2007  --------------//

			
			
			
			out.println("</table>");
			
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){
			
			
      String m_reciept_no = req.getParameter("PURCH_ORD_NO");
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			
			
			
			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Payment Settlement Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			//--------- Added by Chandana on 08/05/2007 -----------//
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
	    //  out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Advertisement_Offers_Report?chksql=MAIN&OFFER_NO=&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Payment_Settlement_Report?chksql=MAIN&PURCH_ORD_NO=&from_date="+m_from_date+"&to_date="+m_to_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				
				out.println(" window.location.href=m_url;");
				
				out.println("}");
      //--------- End by Chandana on 08/05/2007 -----------//
			
			
			out.println("</script>"); 

			
			if(m_reciept_no.equals("") && !m_from_date.equals("") && !m_to_date.equals("")){
			
			pstmt = conn.prepareStatement(" SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,B.FULL_NAME, "+
			                              " NVL(DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash'),'-'), "+
																		" NVL(A.PAY_AMOUNT,0), NVL(A.LIC_BRANCH_CODE,'-'), NVL(A.LIC_ACC_NO,'-'), "+
																		" NVL(A.PAYEE_BRANCH_CODE,'-'), NVL(A.PAYEE_ACC_NO,'-'), NVL(TO_CHAR(A.EFF_VALDATE,'DD-MON-YYYY'),'-'), "+
																		" NVL(TO_CHAR(A.REALISED_DATE,'DD-MON-YYYY'),'-'), NVL(A.PAYEE_NAME,'-'), "+
																		" NVL(A.PAY_AMOUNT_CURR,0), NVL(A.EXCHANGE_RATE_BANK,0), NVL(A.EXCHANGE_RATE_REP_CURR,0), "+
																		" NVL(A.REC_AMMOUNT_REP_CURR,0), NVL(A.EXCHANGE_GAIN_LOSS,0), NVL(A.COMMENTS,'-') "+
																		" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																		" where A.CLIENT_CODE=B.CLIENT_CODE AND "+
																		" TO_DATE(TO_CHAR(A.ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
                                    " TO_DATE(TO_CHAR(A.ENTDATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																		" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
							}else{
      pstmt = conn.prepareStatement(" SELECT A.PAYMENT_NO,A.SUS_REF_NO,A.CLIENT_CODE,B.FULL_NAME, "+
			                              " NVL(DECODE(A.SETTLE_MODE,'CHEQUE','Cheque','CASH','Cash'),'-'), "+
																		" NVL(A.PAY_AMOUNT,0), NVL(A.LIC_BRANCH_CODE,'-'), NVL(A.LIC_ACC_NO,'-'), "+
																		" NVL(A.PAYEE_BRANCH_CODE,'-'), NVL(A.PAYEE_ACC_NO,'-'), NVL(TO_CHAR(A.EFF_VALDATE,'DD-MON-YYYY'),'-'), "+
																		" NVL(TO_CHAR(A.REALISED_DATE,'DD-MON-YYYY'),'-'), NVL(A.PAYEE_NAME,'-'), "+
																		" NVL(A.PAY_AMOUNT_CURR,0), NVL(A.EXCHANGE_RATE_BANK,0), NVL(A.EXCHANGE_RATE_REP_CURR,0), "+
																		" NVL(A.REC_AMMOUNT_REP_CURR,0), NVL(A.EXCHANGE_GAIN_LOSS,0), NVL(A.COMMENTS,'-') "+
																		" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT A, "+m_schema_name+".AF_CO_MAS_CLIENT B "+
																		" where A.CLIENT_CODE=B.CLIENT_CODE AND "+
																		" A.PAYMENT_NO LIKE UPPER('"+m_reciept_no+"%') "+
																		" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
							
							}											
																		

			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			out.println("<DIV STYLE='{position:absolute; top:60; left:0 cursor: hand;}'>");
			out.println("<br>");
			out.println("<table align='center' width='2000' class='table' border=1>"); 
			while(more){
						out.println("<tr >"); 
						out.println("<TD width='100' class='txt_report_data' style= cursor:hand;cursor-color:blue onclick=show_payment_drill('"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></TD>");
						out.println("<TD width='100' class='txt_report_data' style= cursor:hand onclick=show_sus_payment_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></TD>");
						out.println("<TD width='130' class='txt_report_data' style= cursor:hand;cursor-color:blue onclick=show_client('"+rs.getString(3)+"')><U>"+rs.getString(4)+"</U></TD>");
						out.println("<TD width='80' class='txt_report_data' >"+rs.getString(5)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(6))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' >"+rs.getString(7)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' >"+rs.getString(8)+"</TD>");
						out.println("<TD width='80' class='txt_report_data' >"+rs.getString(9)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' >"+rs.getString(10)+"</TD>");
						out.println("<TD width='80' class='txt_report_data' >"+rs.getString(11)+"</TD>");
						out.println("<TD width='80' class='txt_report_data' >"+rs.getString(12)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' >"+rs.getString(13)+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(14))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(15))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(16))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(17))+"</TD>");
						out.println("<TD width='100' class='txt_report_data' align='right'>"+nf.format(rs.getDouble(18))+"</TD>");
						out.println("<TD width='130' class='txt_report_data' >"+rs.getString(19)+"</TD>");
						out.println("</tr >"); 
						more=rs.next(); 
			} 

			out.println("</table>"); 
			out.println("</DIV>");
			
			out.println("<DIV STYLE='position: absolute; top: 0; left: 0; width:0; height: 0'></DIV>");
   		out.println("<DIV STYLE='position: absolute; top: 0; left: 3; width : 1325; height: 30'>");
			out.println("<table align='center' width='2000' class='table' border=0>");
			out.println("<tr><td align ='right' ><input type=\"button\" class='mainbut'  onclick='close_window()' value=\"Close\">");
			out.println("</td></tr>");
			out.println("</table>");
			
			out.println("<table align='center' width='2000' class='table' border=1>");
			out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.PAYMENT_NO')>PAYMENT NO</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.SUS_REF_NO')>SUS REF NO</td>"); 
			out.println("<td width='130' class='txt_report_column' style= cursor:hand; onclick=sort_data('B.FULL_NAME')>CLIENT NAME</td>"); 
			out.println("<td width='80' class='txt_report_column'  style= cursor:hand; onclick=sort_data('A.SETTLE_MODE')>SETTLE MODE</td>"); 
			out.println("<td width='100' align='right' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.PAY_AMOUNT')>PAY AMOUNT</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.LIC_BRANCH_CODE')>LIC BRANCH CODE</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.LIC_ACC_NO')>LIC ACC NO </td>"); 
			out.println("<td width='80' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.PAYEE_BRANCH_CODE')>PAYEE BRANCH CODE</td>"); 
			
			out.println("<td width='100' class='txt_report_column'>PAYEE ACC NO</td>"); 
			out.println("<td width='80' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.EFF_VALDATE')>EFF VALDATE</td>"); 
			out.println("<td width='80' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.REALISED_DATE')>REALISED DATE</td>"); 
			out.println("<td width='100' class='txt_report_column' style= cursor:hand; onclick=sort_data('A.PAYEE_NAME')>PAYEE NAME</td>"); 
			out.println("<td width='100' align='right' class='txt_report_column'>PAY AMOUNT CURR</td>"); 
			out.println("<td width='100' align='right' class='txt_report_column'>BANK EXCHANGE RATE </td>"); 
			out.println("<td width='100' align='right' class='txt_report_column'>EXCHANGE RATE REP CURR</td>"); 
			out.println("<td width='100' align='right' class='txt_report_column'>REC AMMOUNT REP CURR</td>"); 
			out.println("<td width='100' align='right' class='txt_report_column'>EXCHANGE GAIN LOSS</td>"); 
			out.println("<td width='130' class='txt_report_column'>COMMENTS</td>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
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


