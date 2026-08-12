
//--
//SCREEN NAME:SECURITY FILE MOVEMENT 
//CREATED BY:CHANDNA
//DATE/TIME:30/03/2007
//NOTES:
//URL:https://dev-lakdl.sasianet.com:/myserver/servlet/LAKDL_AF_RPT_Security_File_Movement ?chksql=MAIN&

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 
//Modified by Mahela on 10-04-2007

public class LAKDL_AF_RPT_Security_File_Movement extends javax.servlet.http.HttpServlet { 

		Connection conn;
		Statement stmt;
		public ResultSet rs,rs2;
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
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql_report\";"); 
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Security_File_Movement?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Security_File_Movement?chksql=main_page1';"); 
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
			out.println("help_box.innerHTML=\"  Security File Movement Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Security File Movement Report \";"); 
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
			
			out.println("function load_data_frame(){ ");
			//out.println("alert(parent.frames[1].location); ");
			//out.println("parent.frames[1].location.replace(\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Security_File_Movement?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\" \");  ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Security_File_Movement?chksql=MAIN&PURCH_ORD_NO=\"+document.Form1.TXT_PURCHASE_ORDER_NO.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println(" } ");
				
			
			//------ Added by Chandana on 08/05/2007 -----///
			
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

			//-------- End by Chandana on 08/05/2007 ---------//

			
			
				
				
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Security File Movement Report </td>");
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
			out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_PURCHASE_ORDER_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_PURCHASE_ORDER_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
						//---- Added by Chandana on 08/04/2007 -----//
			out.println("<tr class=tr_input>");
			out.println("<td width='12%' ID=VDATE>Date</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
				
			out.println("<td width='7%' ID=VDATE>To</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
				
			out.println("<td width='10%' align=\"left\"><input class='mainbut' type='button' name='BUT_VIEW' value=\"View\" onClick=\"load_data_frame()\"></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>");
      //----- End Chandana on 08/05/2007  --------------//
			
			
			out.println("</table>");

			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}else
			if(m_screen_type.equals("MAIN")){
			
			String m_fin_no = req.getParameter("PURCH_ORD_NO");
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");

			//m_chksql = req.getParameter(\"chksql\");
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Security File Movement </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
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
			out.println("<td align=\"center\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Security File Movement Report </td>");
			out.println("</tr>");
			out.println("</table>");  
			out.println("</BR>");

			//out.println("<table align='left' width='60%' class='table' border=0>"); 
		/*	out.println("<tr class=pdn_txtpos2>"); 
			out.println("<td width='10%' class='txt_report_column'>FINANCE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>TOTAL COUNT</td>"); 
			out.println("<td width='10%' class='txt_report_column'>IN COUNT</td>"); 
			out.println("<td width='10%' class='txt_report_column'>OUT COUNT</td>"); 
			out.println("</tr >"); */
			
			if(m_fin_no.equals("") && !m_from_date.equals("") && !m_to_date.equals("")){
			pstmt = conn.prepareStatement("SELECT  FINANCE_NO,APPLICATION_NO,NVL(STATUS,'-'),NVL(TOTAL_COUNT,0), "+
			                              " NVL(IN_COUNT,0), NVL(OUT_COUNT,0) "+
																		" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT "+
																		" WHERE TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
                                    " TO_DATE(TO_CHAR(ENT_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
																		
																		
				}else{
			pstmt = conn.prepareStatement("SELECT  FINANCE_NO,APPLICATION_NO,NVL(STATUS,'-'),NVL(TOTAL_COUNT,0), "+
			                              " NVL(IN_COUNT,0), NVL(OUT_COUNT,0) "+
																		" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_MOVMENT "+
				                            " WHERE  FINANCE_NO LIKE ('"+m_fin_no+"%') "); 
				
				
				}														
																		
																		
																		
			rs=pstmt.executeQuery(); 

			boolean more=rs.next();
			
			String m_quotation;
			
			while(more){
			m_quotation=rs.getString(1);

			out.println("<br>");
			out.println("<table align='center' width='100%' class='table' border=0>"); 
	
			out.println("<tr >"); 
			out.println("<td width='15%'  class='txt_report_column' style='{text-align:left;}'>FINANCE NO</td><TD width='15%' align='left' class='txt_report_data' STYLE='{text-align:right; cursor:hand;}' onclick=show_finance_detail_drill('"+rs.getString(1)+"') >:<U> "+rs.getString(1)+"</U></TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 	
			
			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>APPLICATION NO</td><TD width='15%' align='left'  class='txt_report_data' STYLE='{text-align:right; cursor:hand;}' onclick=show_application_detail_drill('"+rs.getString(2)+"')>: <U> "+rs.getString(2)+" </U></TD>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>TOTAL COUNT</td><TD width='15%' align='left'  class='txt_report_data' align='right'>: "+rs.getString(4)+"</TD>"); 
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>IN COUNT</td><TD width='15%' align='left'  class='txt_report_data' align='right'>: "+rs.getString(5)+"</TD>");
			out.println("<td width='15%'class='txt_report_column' style='{text-align:left;}'>OUT COUNT</td><TD width='15%' align='left'  class='txt_report_data' align='right'>: "+rs.getString(6)+"</TD>");
			out.println("<td width='*%'></td>"); 
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
	

			out.println("<tr class='pdn_txtpos2'>"); 
			out.println("<td width='10%' class='txt_report_column'>REQ NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>PRO FORMA INVOICE NO</td>"); 
			out.println("<td width='10%' class='txt_report_column'>DOCUMENT CODE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>DOCUMENT STATUS</td>"); 
			out.println("<td width='10%' class='txt_report_column'>REASON</td>"); 
			out.println("<td width='10%' class='txt_report_column'>REQUESTED USER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>REQUESTED DATE</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPRO USER</td>"); 
			out.println("<td width='10%' class='txt_report_column'>APPRO DATE</td>");			
			out.println("</tr >");
			
			
			pstmt = conn.prepareStatement(" SELECT "+
			" REQ_NO, "+
			" NVL(PRO_FORMA_INVOICE_NO,'-'), "+
			" NVL(DOCUMENT_CODE,'-'), "+
			" NVL(DOCUMENT_STATUS,'-'), "+
			" NVL(REASON,'-'), "+
			" NVL(REQUESTED_USER,'-'), "+
			" TO_CHAR(REQUESTED_DATE,'DD-MON-YYYY'), "+
			" NVL(APPRO_USER,'-'), "+
			" NVL(TO_CHAR(APPRO_DATE,'DD-MON-YYYY'),'-') "+ 
			" FROM "+m_schema_name+".AF_CO_PRO_SECURITYFILE_DETAIL "+
			" WHERE FINANCE_NO='"+m_quotation+"' ");
			
			rs2=pstmt.executeQuery(); 

			boolean more2=rs2.next();
			
			
			

			while(more2){

			out.println("<tr >"); 
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(1)+"</TD>");
			out.println("<TD class='txt_report_data' align='right' STYLE='{text-align:right; cursor:hand;}' onclick=show_proforma_invoice_drill('"+rs2.getString(2)+"') ><u>"+rs2.getString(2)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right' STYLE='{text-align:right; cursor:hand;}' onclick=show_document_drill('"+rs2.getString(3)+"')><u>"+rs2.getString(3)+"</u></TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(4)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(5)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(6)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(7)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(8)+"</TD>");
			out.println("<TD class='txt_report_data' align='right'>"+rs2.getString(9)+"</TD>");
			out.println("</tr >"); 
			
			
			
			out.println("<tr >"); 
			out.println("</tr >"); 			
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			
			
			

			more2=rs2.next(); 
			 
			if(!more2){
			break;
			}
	
			}	
			
			if(more){
			m_quotation=rs.getString(1);
			}
      more=rs.next();
			
			out.println("</table>"); 
			//out.println("<BR >"); 
			out.println("<HR>"); 

		}
			
			
			
			
			
			
			
			
			
			
			
	/*		while(more){
			      //out.println("<table align='left' width='60%' class='table' border=0>"); 
			      out.println("<tr class=pdn_txtpos2>"); 
			      out.println("<td width='10%' class='txt_report_column'>FINANCE NO</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>TOTAL COUNT</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>IN COUNT</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>OUT COUNT</td>"); 
			      out.println("</tr >"); 
			
						out.println("<tr >"); 
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(1)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(2)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(3)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(4)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(5)+"</TD>");
						out.println("<TD class='txt_report_data' align='right'>"+rs.getString(6)+"</TD>");
						out.println("</tr >");
						
						//out.println("</table>");
						
						//out.println("<br>");
						out.println("<tr >"); 
						out.println("<table align='left' width='60%' class='table' border=0>"); 
						out.println("<tr >");
						out.println("<td width='10%' class='txt_report_column'>FINANCE NO</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>APPLICATION NO</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>STATUS</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>TOTAL COUNT</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>IN COUNT</td>"); 
			      out.println("<td width='10%' class='txt_report_column'>OUT COUNT</td>"); 
						out.println("</tr >"); 
						 
						out.println("</table>");
						
						
			       
						out.println("<tr >"); 
						
						more=rs.next(); 
						
			} */

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


