//Created by Minal for #14286 on 10-10-2014
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class LAKDL_AF_RE_Insurance_Payments_Commission_Receivable_Report extends javax.servlet.http.HttpServlet {
  
    public  void service(HttpServletRequest req, HttpServletResponse res) throws IOException { // synchronized
		
		ServletOutputStream out = null;
	    Connection conn = null;
	    Statement stmt = null, stmt1 = null, stmt2 = null, stmt3 = null;
	    java.text.NumberFormat nf= null, nf1= null;
	    ResultSet rs= null, rs1= null, rs2= null, rs3= null;
	    String m_chksql = null ;
		CallableStatement callstmt1 =null;
        
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
			
			String header_name=con_method.header_name.trim();
			String m_schema_name = con_method.schema_name;
			String m_fschema_name=con_method.client_name.trim();
			String m_servlet_client_url=con_method.servlet_client_url;
			String m_client_name=con_method.client_name;
			String m_client_t3_port=con_method.client_t3_port;	
			String m_username = con_method.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			
			out = res.getOutputStream(); 
		
			m_chksql= req.getParameter("chksql");
			
			if(m_chksql.equals("run_report")){ 
				
				String m_from_date = req.getParameter("from_date");
				String m_to_date   = req.getParameter("to_date");
				String m_business_type = req.getParameter("business_type");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_SAVE_INSUR_COM_RECEV_RPT(:1,:2,:3);END;"); 
					callstmt1.setString(1,m_to_date);
					callstmt1.setString(2,m_username);
					callstmt1.setString(3,m_business_type);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
            
			else if(m_chksql.equals("main_page")){
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					/*
					out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
					*/
					out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  Insurance Commission Receivable Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Insurance Commission Receivable Report - \"+document.Form1.hid_status.value;"); 
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
				out.println("		company_assign(oBj);"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_Commission_Receivable_Report?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_Commission_Receivable_Report?chksql=main_page';"); 
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
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function check_date(objdd,objmm,objyy) {");						
				out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
				out.println("  checkMonthLength(objdd,objmm,objyy);");
				//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
				out.println("}");
				out.println("}");
				

				out.println("function load_calendar(num) {");
				out.println("   document.Form1.hid_cal_date.value=num;"); 
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
				
				out.println("function main_date_chk(){");
				/*
				out.println("if(document.Form1.VAL_DAY1.value==\"\" || document.Form1.VAL_MONTH1.value==\"\" || document.Form1.VAL_YEAR1.value==\"\"){;");
				out.println("alert('Enter valid From Date...!');");
				out.println("return false;");
				out.println("}else");	
				*/
				out.println("if(document.Form1.VAL_DAY2.value==\"\" || document.Form1.VAL_MONTH2.value==\"\" || document.Form1.VAL_YEAR2.value==\"\"){;");
				out.println("alert('Enter valid To Date...!');");	
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");	
				out.println("}");
				
				out.println("function finance_help(){");
				out.println("Sql=\"m_help_Finance_Is_History\"; ");
				out.println("Crit=document.Form1.FIN_NO.value+'@';");
				out.println("HelpBox(1,10,0,Crit,Sql,4)");
				out.println("}");
				
				out.println("function finance_assign(oBj){");			
				out.println("document.Form1.FIN_NO.value=oBj.valout[2];");			
				out.println("}");	
				
				out.println("function company_help(){");
				out.println("Sql=\"m_help_Company\"; ");
				out.println("Crit=document.Form1.COM_NAME.value+'@';");
				out.println("HelpBox(1,10,0,Crit,Sql,5)");
				out.println("}");
				
				out.println("function company_assign(oBj){");			
				out.println("document.Form1.COM_NAME.value=oBj.valout[3];");			
				out.println("}");
			
			    out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("       durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				out.println("function run_report() {");
				//out.println("       var m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value; ");
				out.println("       var m_from_date = ''; ");
				out.println("       var m_business_type = document.Form1.TXT_BUSINESS_TYPE.value; ");
				out.println("       var m_to_date   = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value; ");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_Commission_Receivable_Report?chksql=run_report&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&business_type=\"+m_business_type;"); 
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("}");
				
				out.println("function view_report(){");
				
				out.println("   if(main_date_chk()){");
				
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				
				//out.println("       m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("       m_from_date='';");
				out.println("       m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				
				out.println("       chkSql = 'view_report';");			
				out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Payments_Commission_Receivable_Report?chksql=\"+chkSql+\"&to_date=\"+m_to_date;");
				out.println("       popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("   }");
				
				
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Insurance payment commission receivable report is generated. Use View Report button to get the view.');"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				out.println("</Script>");
			
				out.println("<body onload=\"load_sysdate()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
				out.println("<form name=\"Form1\" method=post>");
				out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Insurance Commission Receivable Report </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				
				out.println("<td width='6%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
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
				
				out.println("<table align='center' width='100%' class='table' border='0'>");	
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width='5%' ID=VDATE>From</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("</tr>");
				*/
				
				// added by udara 11-11-2014
				out.println("<tr>"); ; 
				out.println("<td  width='5%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input> Business Type </DIV></td>"); 
				out.println("<td ='40%' ><select name='TXT_BUSINESS_TYPE' class='txt_input' style=\"width:100px;\" >");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"NEW\" >New</option>");
				out.println("<option value=\"RENEW\" >Renewal</option>");				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				// end by udara 11-11-2014
				
				out.println("<tr class=tr_input>");
				out.println("<td width='5%' ID=VDATE>As at Date</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				
				out.println("<input class='but_input' type='button'  value=\"View Report\" onClick=\"view_report()\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button'  value=\"Run Report\"  onClick=\"run_report()\"  style='{width=150px}'>");
				
				out.println("</td>");			
				out.println("</tr>");
				
				out.println("</table>");
			
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</html>");

			}
			
			else if(m_chksql.equals("view_report")){ 
				
				String m_to_date   = req.getParameter("to_date");
				
				out.println("<HTML><HEAD><TITLE>Asset Financing System</TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_drill(m_branch,m_insurance_comp,m_insurance_done_by){ ");
				out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_insurance_summery_view?chksql=show_drill_window&branch=\"+m_branch+\"&insurance_comp=\"+m_insurance_comp+\"&insurance_done_by=\"+m_insurance_done_by;");
				out.println("   window.open(m_url,'slab','width=400,height=500,center=yes,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</SCRIPT>");
				out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				out.println("<TABLE  WIDTH='100%' class='pdn_txtpos2' STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
				out.println("<TR><TD align='Center' ><B> Insurance Commission Receivable Report as at "+m_to_date+" </B></TD></TR>");
				out.println("</TABLE>");
				out.println("<BR>");	


				out.println("<table width='80%' class='table' border=1 >");	
			
				// ===================== Heading Start =========================================			
				out.println("<tr>");				
				out.println("<td width='1%'  align='left'  ><b>No.</b></td>"); 
				out.println("<td width='10%' align='left'  ><b>Insurance Company</b></td>"); 	
			    out.println("<td width='5%'  align='right' ><b>Total Commission Receivable</b></td>"); 
				out.println("<td width='5%'  align='right' ><b>Commission Received</b></td>"); 
				out.println("<td width='5%'  align='right' ><b>Balance Commission Receivable</b></td>"); 
				out.println("</tr>"); 
				
				
				// ===================== Heading End ============================================
				
				int row_count = 0;
				double tot_comm_receivable = 0;
				double tot_comm_received = 0;
				double tot_comm_bal = 0;

				/*
				rs= stmt.executeQuery(" "+		
						" SELECT "+
							" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-'), "+
							" SUM(COMMISSION_RECEIVABLE), "+
							" SUM(COMMISSION_RECEIVED), "+
	      					" SUM(COMMISSION_BALANCE) "+
								" FROM "+m_schema_name+".AF_INSUR_COM_RECEV_RPT "+
								" WHERE ENT_USER = '"+m_username+"' "+ 
								" GROUP BY INSUR_COM	"+
				" ");
				*/
				
				rs= stmt.executeQuery(" "+	
					
					" SELECT "+
					    " INSUR_COM, "+
						" COMMISSION_RECEIVABLE, "+
						" COMMISSION_RECEIVED, "+
						" COMMISSION_BALANCE "+
							" FROM ( "+
								" SELECT "+
									" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM),'-') INSUR_COM, "+
									" SUM(COMMISSION_RECEIVABLE) COMMISSION_RECEIVABLE, "+
									" SUM(COMMISSION_RECEIVED) COMMISSION_RECEIVED, "+
			      					" SUM(COMMISSION_BALANCE) COMMISSION_BALANCE "+
										" FROM "+m_schema_name+".AF_INSUR_COM_RECEV_RPT "+
										" WHERE ENT_USER = '"+m_username+"' "+ 
										" GROUP BY INSUR_COM	"+
							" ) "+
							" WHERE COMMISSION_RECEIVABLE > 0 "+
				" ");

				
				// main while
				while(rs.next()){
					
					tot_comm_receivable = tot_comm_receivable + rs.getDouble(2);
					tot_comm_received = tot_comm_received + rs.getDouble(3);
					tot_comm_bal = tot_comm_bal + rs.getDouble(4);

					row_count++;
					
					out.println("<tr>");
					out.println("<td width='1%'  align='left'  > "+row_count+" </td>"); 
					out.println("<td width='10%' align='left'  > "+rs.getString(1)+" </td>");
					out.println("<td width='5%'  align='right' > "+nf.format(rs.getDouble(2))+" </td>");
					out.println("<td width='5%'  align='right' > "+nf.format(rs.getDouble(3))+" </td>");
					out.println("<td width='5%'  align='right' > "+nf.format(rs.getDouble(4))+" </td>");
					out.println("</tr>");
					
					
				}
				// main while end
				
				out.println("<tr>");
				out.println("<td width='1%'  align='left'  > &nbsp; </td>"); 
				out.println("<td width='10%' align='left'  ><b> Total </b></td>");
				out.println("<td width='5%'  align='right' ><b> "+nf.format(tot_comm_receivable)+" </b></td>");
				out.println("<td width='5%'  align='right' ><b> "+nf.format(tot_comm_received)+" </b></td>");
				out.println("<td width='5%'  align='right' ><b> "+nf.format(tot_comm_bal)+" </b></td>");
				out.println("</tr>");
				
				out.println("</table>"); 

				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			else if(m_chksql.equals("show_drill_window")){		
				
				String m_branch = "";
				String m_insurance_comp = "";
				String m_insurance_done_by = "";
				
				String m_branch_condition = "";
				String m_insurance_comp_condition = "";
				String m_insurance_done_by_condition = "";
				
				
				if(req.getParameter("branch")!=null ){
					m_branch = req.getParameter("branch").trim();
				}
			
				if(req.getParameter("insurance_comp")!=null ){
					m_insurance_comp = req.getParameter("insurance_comp").trim();
				}
				
				if(req.getParameter("insurance_done_by")!=null ){
					m_insurance_done_by = req.getParameter("insurance_done_by").trim();
				}
				
				if(m_branch.equals("ALL")){
					m_branch_condition = " ";
				}
				else{
					m_branch_condition = " AND   BRANCH_CODE = '"+m_branch+"'  ";
				}
				
				if(m_insurance_comp.equals("ALL")){
					m_insurance_comp_condition = " ";
				}
				else{
					m_insurance_comp_condition = " AND   INSUR_COM = '"+m_insurance_comp+"'  ";
				}
				
				if(m_insurance_done_by.equals("ALL")){
					m_insurance_done_by_condition = " ";
				}
				else{
					m_insurance_done_by_condition = " AND   INSURANCE_DONE_BY = '"+m_insurance_done_by+"'  ";
				}
				
				stmt = conn.createStatement ();
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE> Insurance Commission Receivable Report - Drill </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 

				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 9pt arial; text-align:center;}'   ><u> Insurance Commission Receivable Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				int rec_count = 1;
				String  Sql_data="";
				boolean more;

				
				Sql_data = " "+
						" SELECT "+
						" FINANCE_NO, "+
						" POLICY_NO, "+
						" VALUE_AMOUNT "+
						" FROM  "+m_schema_name+".AF_INSURANCE_SUMMARY_REPORT  "+ 
						" WHERE ENT_USER = '"+m_username+"' "+
						" AND   BRANCH_CODE IS NOT NULL "+
						" "+ m_branch_condition +" "+
						" "+ m_insurance_comp_condition +" "+
						" "+ m_insurance_done_by_condition +" "+
					" ";
				
				//out.println(Sql_data);
				
				rs=stmt.executeQuery(Sql_data);
				more=rs.next();
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				double m_tot = 0.00;
				String m_td_color=null;
				int num_row=0;
				out.println("<table id=mytable align=\"center\" width=\"90%\" border=\"1\" class=\"table\"  cellspacing=0 > ");
				out.println("<tr>");
				out.println("<td width='5%' class=div_input  align='center' bgcolor='lightblue' ><B> No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Finance No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Policy No. </B></td>");
				out.println("<td width='20%' class=div_input align='center' bgcolor='lightblue' ><B> Amount </B></td>");
				out.println("</tr >");
				while(more){
					m_td_color="white";
					if(num_row%2==0){
						m_td_color="#C9EEFF";
					}
					out.println("<tr>");	
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+rec_count+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left; cursor:hand;' bgcolor='"+m_td_color+"' onclick=\"show_transaction_info('','"+rs.getString(1)+"');\"  >"+rs.getString(1)+"</td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:left;' bgcolor='"+m_td_color+"' > "+rs.getString(2)+" </td>"); 
					out.println("<td class='factoring-letter-body' STYLE='text-align:right;' bgcolor='"+m_td_color+"' > "+nf.format(rs.getDouble(3))+" </td>"); 
					out.println("</tr >");
					m_tot = m_tot + rs.getDouble(3);
					num_row++;
					more=rs.next();
					
					rec_count++;
				}
				out.println("<tr>");
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b>Total</b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:left;' ><b> &nbsp; </b></td>"); 
				out.println("<td class='factoring-letter-body' STYLE='text-align:right;' ><b>"+nf.format(m_tot)+"</b></td>"); 
				out.println("</tr >");
				out.println("</table>");
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			// end drilldown
			
			
			
			
			
			
			
			
        
		}     catch(Exception ex) {
            try {
                out.println("Error : " + ex.toString());
            }
            catch(Exception e) {}
        }
        
        
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch(Exception e) {}
            }
        }
	}
}