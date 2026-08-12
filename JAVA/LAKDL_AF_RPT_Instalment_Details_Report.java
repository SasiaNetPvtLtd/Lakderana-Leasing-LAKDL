 
//Created by Chandana on 04-05-2007 
//Instalment Details Report

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import sun.misc.BASE64Decoder; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RPT_Instalment_Details_Report extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;
	public String m_chksql;
	ServletOutputStream out = null;
	int m_appno_count=0;
	
  public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_MK_CO_methods CO_methods = new LAKDL_AF_MK_CO_methods();
			
			LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
			conn = con_method.met_user_validate(req); 
			String m_html_client_url = con_method.html_client_url;
			String m_class_url=con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;
      String m_username 						= "AA";//m_sn_methods.username;
			out = res.getOutputStream();
			CallableStatement callstmt1 =null;
	
					
			//************************************************************
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		  nf.setMinimumFractionDigits(0);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);   
		  nf1.setMinimumFractionDigits(4);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			
			m_chksql = req.getParameter("chksql");
			stmt = conn.createStatement ();
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}
			else if(m_chksql.trim().equals("main_page1")){	
			
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");
			out.println("<Script>");
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_LAKDL_AF_MISF_display_quotation_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Instalment Details Report  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Instalment Details Report \";"); 
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
			
			out.println("function help_update() {");
			out.println("m_help=\"1\";");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("    m_sql = \"m_help_TXT_LEASE_NO_sql_report\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
							
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}");
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Instalment_Details_Report?chksql=main_page1';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RPT_Instalment_Details_Report?chksql=main_page1';"); 
			out.println("}");
			
			
				
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
			
			out.println("alert(document.Form1.hid_from_date.value);");
			
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
			
			//out.println("date2=v_date+'-'+v_month+'-'+val;");
			
			out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			
			out.println("alert(date2);");
			
			out.println("document.Form1.hid_to_date.value=date2");
			out.println("}");
			out.println("}");
			
			
			
			
			out.println("function check_date(){ ");
		 
			out.println("var date='' ");
			                                 
			out.println(" if((document.Form1.VAL_DAY1.value !=\"\")&&(document.Form1.VAL_MONTH1.value !=\"\")&&(document.Form1.VAL_YEAR1.value !=\"\")){");
			out.println("  if(checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)){");
			out.println("date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("document.Form1.hid_from_date.value=date");
			out.println(" }");
			out.println(" }");
			
			out.println(" if((document.Form1.VAL_DAY2.value !=\"\")&&(document.Form1.VAL_MONTH2.value !=\"\")&&(document.Form1.VAL_YEAR2.value !=\"\")){");
			out.println("  if(checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)){");
			out.println("date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("document.Form1.hid_to_date.value=date");
			out.println(" }");
			out.println(" }");
						
			out.println("}");

					
		
			out.println("function load_data_frame(){ ");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RPT_Instalment_Details_Report?chksql=main_page&LEASE_NO=\"+document.Form1.TXT_FINANCE_NO.value+\"&from_date=\"+document.Form1.hid_from_date.value+\"&to_date=\"+document.Form1.hid_to_date.value+\" \";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=250,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box> Instalment Details Report </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
      out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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
			out.println("<td width='12%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Lease No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
  		out.println("<tr>"); 
			out.println("<td width='12%' ><DIV id='DIV_TXT_FINANCE_NO1'  class=div_input>Client No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO1' maxlength='15' size='15' onkeypress=\"\" onblur=\"\" onchange=\"\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_FINANCE_NO' value=\"Help\" onClick=\"help_update()\" ></td>"); 
		//	out.println("<td width='10%'><input class='mainbut' type='button' name='BUT_LOAD_DATA' value=\"View\" onClick=\"load_data_frame()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
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
			
			
			
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
			out.println("</html>");

					
			}
			else if(m_chksql.trim().equals("main_page")){
			
			   String m_lease_no = req.getParameter("LEASE_NO");
				 String m_from_date = req.getParameter("from_date");
				 String m_to_date = req.getParameter("to_date");
					   
	
     
        out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
				
				out.println("function load_roll_value(m_val){"); 
				out.println("if(m_val==''){");
				out.println("help_box.innerHTML=\" Instalment Details Report \";"); 
			  out.println("}else{");
				out.println("help_box.innerHTML=\" Instalment Details Report - \"+m_val;"); 
			  out.println("}");
				out.println("}");

			  out.println("function load_details_deposit(deposit_no){");
				//out.println("alert('dePOSIT nO ** '+deposit_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_deposit_details&DEP_NO='+deposit_no;"); 
				out.println("window.open(m_url,'displayWindow4','left=150,top=250,width=800,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
			
				out.println("function load_details_receipt(receipt_no){");
				//out.println("alert('receipt no ** '+receipt_no)");
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+receipt_no;"); 
				out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 		out.println("}");
				
	
							
			
			

	    //  out.println("	m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_RPT_Repossession_Details_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\"&REPOSS_NO=\"+document.Form1.m_hid_repos_no.value;"); 				
			//  out.println(" window.location.href=m_url;"); 
			//	out.println("}");
			
        out.println("</Script>");
				
				
				out.println("<body onload=\"\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
				out.println("<input type=hidden name=\"m_hid_repos_no\" value=\"\"></td>");
       
				out.println("<table class=table border='0' width='1425' >");
				out.println("</table>"); 
				
				//out.println("m_from_date==="+m_from_date+"m_TO_date"+m_to_date);
				out.println("<table class=table border='0' width='1425' >");
				out.println("</table>");
				
				out.println("<br>"); 
				out.println("<br>");
				out.println("<br>");
				
				
					out.println("<table class=table border='0' width='1425' >");
          out.println("<tr class=pdn_txtpos2 STYLE='{font: bold 8pt arial; }'>");
					out.println("<td  width='100' style= cursor:hand; align='center' >Lease No</td>");
          out.println("<td  width='100' style= cursor:hand; align='center' >Lessee</td>");
          out.println("<td  width='100' style= cursor:hand; align='center' >NIL</td>");
          out.println("<td  width='100' style= cursor:hand; align='center' >Repayable</td>");
					out.println("<td  width='100' style= cursor:hand; align='center' >Future Income</td>");
					out.println("<td  width='100' style= cursor:hand; align='center' >Arrears (Capital)</td>");
					out.println("<td  width='80'  style= cursor:hand; align='center' >Arrears (Net)</td>");
					out.println("<td  width='90'  style= cursor:hand; align='center' >1 Month</td>");
					out.println("<td  width='80'  style= cursor:hand; align='center' >2 Month</td>");
					out.println("<td  width='90'  style= cursor:hand; align='center' >3 Month</td>");
					out.println("<td  width='90'  style= cursor:hand; align='center' >4-5 Months</td>");
					out.println("<td  width='90'  style= cursor:hand; align='center' >6 Months</td>");
					out.println("<td  width='90'  style= cursor:hand; align='center' >Demand</td>");
					out.println("<td  width='100'  style= cursor:hand; align='center' >Status</td>");
					out.println("</tr>");
					out.println("</table>"); 
				
				
				
				  rs = stmt.executeQuery (" SELECT DISTINCT X.LEASE_NO,X.LESSEE,X.NIL,Y.REPAYABLE,Z.FUTURE_INCOME,X.CLIENT_CODE "+
					                        " FROM "+
																	" (SELECT A.APPLICATION_NO APPLICATION_NO, A.FINANCE_NO LEASE_NO,LAKDL.AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) LESSEE,  ((B.VAT_PERCENTAGE - B.VAT_APP)*B.NET_AMOUNT)/100 + B.NET_AMOUNT NIL ,A.FINANCE_NO,A.CLIENT_CODE "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_MK_PRO_PRICING B "+
																	" WHERE A.FINANCE_NO IS NOT NULL "+
																	" AND B.APP_NO = A.APPLICATION_NO "+
																	" AND A.FINANCE_NO LIKE UPPER('%"+m_lease_no+"%')) X, "+
																	" (SELECT APPLICATION_NO,SUM(GRENTAL_AMOUNT) REPAYABLE "+
																	" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
																	" WHERE "+
																	" TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
																	" TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																	" GROUP BY APPLICATION_NO ) Y, "+
																	" (SELECT SUM(BALANCE_TO_BE_RECEIVED ) FUTURE_INCOME ,FINANCE_NO "+
																	" FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
																	" WHERE FINANCE_NO LIKE UPPER('%"+m_lease_no+"%') AND "+
																	" TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
																	" TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
																	" GROUP BY FINANCE_NO) Z "+
																	" WHERE Y.APPLICATION_NO = X.APPLICATION_NO AND "+
																	" X.FINANCE_NO = Z.FINANCE_NO ");
				
				   
				   
              
				
					
					if(rs.next()){	
					
					out.println("<table class=table border='0' width='1425' >");
          out.println("<tr class=tr_input STYLE='{font: bold 8pt arial; }'>");
																	

					out.println("<td  width='100'  style= cursor:hand; align='center' onclick = show_finance_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"</U></td>");
          out.println("<td  width='100' style= cursor:hand;  align='left' onclick = show_client('"+rs.getString(6)+"')><U>"+rs.getString(2)+"</U></td>");
          out.println("<td  width='100'  align='right'>"+nf.format(rs.getDouble(3))+"</td>");
          out.println("<td  width='100'  align='right'>"+nf.format(rs.getDouble(4))+"</td>");
					out.println("<td  width='100'  align='right'>"+nf.format(rs.getDouble(5))+"</td>");
					
						
						
			rs1 = stmt.executeQuery (" SELECT  NVL(SUM(CAPITAL_AMOUNT),0),NVL(SUM(INTEREST_AMOUNT + CAPITAL_AMOUNT ),0) "+
			                         " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
															 " WHERE APPLICATION_NO = (SELECT APPLICATION_NO "+
															 " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+
															 " WHERE FINANCE_NO ='"+m_lease_no+"' )  AND "+
															 " TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
															 " TO_DATE(TO_CHAR(RENTAL_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') "); 	
				
				double m_capital = 0.00;
				double m_total   = 0.00;
				
				
					if(rs1.next()){
					m_capital = rs.getDouble(1);
					m_total   = rs.getDouble(2);
					}
										
					
					out.println("<td  width='100'  align='right' >"+nf.format(m_capital)+"</td>");
					out.println("<td  width='90'   align='right' >"+nf.format(m_total)+"</td>");
					
					
		/*	rs1 = stmt.executeQuery (" SELECT  SUM(ROWNUM) , NVL(SUM(BALANCE_TO_BE_RECEIVED),0) ARREARS "+
			                         " FROM LAKDL.AF_CO_PRO_INVOICE "+
															 " WHERE "+
															 " TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
															 " TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
															 " FINANCE_NO ='"+m_lease_no+"' "); */

      rs1 = stmt.executeQuery (" SELECT  ROWNUM , NVL(BALANCE_TO_BE_RECEIVED,0) ARREARS "+
			                         " FROM "+m_schema_name+".AF_CO_PRO_INVOICE "+
															 " WHERE "+
															 " TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') < TO_DATE('"+m_to_date+"','DD-MM-YYYY') AND "+
															 " TO_DATE(TO_CHAR(DUE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') > TO_DATE('"+m_from_date+"','DD-MM-YYYY') AND "+
															 " FINANCE_NO ='"+m_lease_no+"' ");
      int i=1;
			double arrears1 = 0;
			double arrears2 = 0;
			double arrears3 = 0;
			double arrears45 = 0;
			double arrears6 = 0;
			double tot_arrears = 0;
			
			
			boolean more = rs1.next();
			
      while(more){
			
			if(i==1){
			arrears1 = rs1.getDouble(2);
			}else
			if(i==2){
			arrears2 = rs1.getDouble(2);
			}else
			if(i==3){
			arrears3 = rs1.getDouble(2);
			}else			
			if(i==4 || i==5){
			arrears45 = arrears45 + rs1.getDouble(2);
			}else
			if(i>5){
			arrears6 = arrears6 + rs1.getDouble(2);
			}
			tot_arrears=tot_arrears + rs1.getDouble(2);			
			i=i+1;
			more = rs1.next();  
			}
      

			
			if(arrears1>0 ){			
			out.println("<td  width='80'   align='right' >"+nf.format(arrears1)+"</td>");
			}else{
			out.println("<td  width='80'   align='right' >0.00</td>");
			}			
			if(arrears2>0){			
			out.println("<td  width='80'   align='right' >"+nf.format(arrears2)+"</td>");
			}else{
			out.println("<td  width='80'   align='right' >0.00</td>");
			}			
			if(arrears3>0){			
			out.println("<td  width='90'   align='right' >"+nf.format(arrears3)+"</td>");
			}else{
			out.println("<td  width='90'   align='right' >0.00</td>");
			}			
			if(arrears45>0){			
			out.println("<td  width='90'   align='right' >"+nf.format(arrears45)+"</td>");
			}else{
			out.println("<td  width='90'   align='right' >0.00</td>");
			}			
			if(arrears6 > 0){			
			out.println("<td  width='90'   align='right' >"+nf.format(arrears6)+"</td>");
			}else{
			out.println("<td  width='90'   align='right' >0.00</td>");
			}
			
			
			rs1 = stmt.executeQuery (" SELECT DISTINCT B.APPLICATION_NO,A.GRENTAL_AMOUNT "+
			      " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE A.APPLICATION_NO = B.APPLICATION_NO AND "+
						" B.FINANCE_NO ='"+m_lease_no+"' ");
			
			double demand =0;
			
			if(rs1.next()){			
			demand = rs1.getDouble(2);
			}			
					
			out.println("<td  width='90'  align='right'>"+nf.format(demand)+"</td>");
			out.println("<td  width='100' align='right'>"+nf.format(tot_arrears/demand)+"</td>");
			out.println("</tr>");
			out.println("</table>"); 
			
			}
																	
																	
				out.println("</form>");
				out.println("</body>");
				out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
				out.println("</html>");
      }
			
			//=========================================================================================================================			
			
  		/*else {
				out.println("Undefined");
			}
			*/
      //out.close();
			//conn.close();
			//this.destroy();
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
      //return null;
		}finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}
