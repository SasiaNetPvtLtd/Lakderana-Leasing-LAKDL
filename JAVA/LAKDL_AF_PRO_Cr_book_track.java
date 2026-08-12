//Created by Minal for #14286 on 10-10-2014
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class LAKDL_AF_PRO_Cr_book_track extends javax.servlet.http.HttpServlet {
	/*
	ServletOutputStream out = null;
	Connection conn;
	Statement stmt, stmt1, stmt2, stmt3;
	java.text.NumberFormat nf, nf1;
	public ResultSet rs, rs1, rs2, rs3;
	public String m_chksql;
	*/
	
	    public  void service(HttpServletRequest req, HttpServletResponse res) throws IOException { // synchronized
			
		ServletOutputStream out = null;
		Connection conn = null;
		Statement stmt = null, stmt1 = null, stmt2 = null, stmt3 = null;
		java.text.NumberFormat nf = null, nf1 = null;
		ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null;
		String m_chksql = null;	
			
			
			
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
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			
			//ServletOutputStream 
				out = res.getOutputStream(); 
			
			
			//String m_screen_type= req.getParameter("chksql");		
			//String 
				m_chksql= req.getParameter("chksql");	
			
			
			if(m_chksql.equals("main_page")){ 
				
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Asset Financing System</title>    ");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("</head>");
				out.println("<Script>");
				
				out.println("function load_sysdate(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
					out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\"  CR Book Track - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  CR Book Track - \"+document.Form1.hid_status.value;"); 
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
				out.println("	if(IfCount=='7'){"); //Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("		branch_assign(oBj);"); 
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_track?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_track?chksql=main_page';"); 
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
				
				out.println("function main_date_chk(){");
				out.println("if(document.Form1.VAL_DAY1.value==\"\" || document.Form1.VAL_MONTH1.value==\"\" || document.Form1.VAL_YEAR1.value==\"\"){;");
				out.println("alert('Enter valid From Date...!');");
				out.println("return false;");
				out.println("}else");	
				out.println("if(document.Form1.VAL_DAY2.value==\"\" || document.Form1.VAL_MONTH2.value==\"\" || document.Form1.VAL_YEAR2.value==\"\"){;");
				out.println("alert('Enter valid To Date...!');");	
				out.println("return false;");
				out.println("}else{");
				out.println("return true;");
				out.println("}");	
				out.println("}");
				
				
				out.println("function makeRequest(){");
				out.println("if(main_date_chk()){");
				
				out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				//out.println("m_branch_code=document.Form1.TXT_BRANCH_CODE.value;"); //Added by Kanchana on 2015-12-29 for issue no 18429
				out.println("m_branch_code='';");
				out.println("chkSql = 'view_report';");			
				
				
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_Cr_book_track?chksql=\"+chkSql+\"&branch_code=\"+m_branch_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;");
				
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
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
				
				//Added by Kanchana Karunarathna 2015-12-29 for issue no 18429
				out.println("function branch_help() {"); 
                out.println("    document.Form1.hid_help_type.value=\"3\";"); 
                out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
                out.println("    m_criteria = document.Form1.TXT_BRANCH_CODE.value+\"@\"+\"Y@\"; "); 
                out.println("    HelpBox('1','10','0',m_criteria,m_sql,7);"); 
                out.println("}");
	
				out.println("function branch_assign(oBj){");			
                out.println("   document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");			
                out.println("}");
				// end by Kanchana 2015-12-29
				
				out.println("</Script>");
			
				out.println("<body onload=\"load_sysdate()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");//,load_criteria()
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box> CR Book Track </td>");
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
				
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=VDATE>From</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='*%' > </td>");
				out.println("</tr>");
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=VDATE>To</td>");
				out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				
				out.println("&nbsp; <input class='but_input' type='button'  value=\"View Report\" onClick=\"makeRequest()\" style='{width=150px}'>");
				//out.println("<input class='but_input' type='button'  value=\"Run Report\"  style='{width=150px}'>");
				
				out.println("</td>");
				out.println("<td width='*%' > </td>");
				out.println("</tr>");
				
				//Added by Kanchana on 2015-12-29 for issue no 18429
				/*
				out.println("<tr class=tr_input>");
				out.println("<td width='7%' ID=DIV_BRANCH_CODE>Branch</td>");
				out.println("<td width='40%' ><input name=\"TXT_BRANCH_CODE\"   type=\"text\" maxlength=\"30\" style=\"width: 100px\" class=\"txt_input\" > ");
                out.println("<input class='but_input' type='button' name='HELP_BRANCH_CODE' value=\"Help\" onClick=\"branch_help()\" ></td>"); 	
				out.println("</td>");
				out.println("<td width='*%' > </td>");
				out.println("</tr>");
				*/

				
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

				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_ins_company=req.getParameter("ins_company");
				String m_branch_code=req.getParameter("branch_code");
				
				out.println("<HTML><HEAD><TITLE>Insurance To Be Renewed </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_insurance_details(val,val1){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=SHOW_INSURANCE_BY_CONTRACT&date='+val1+'&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function finance_drill(finance,client){ ");
                out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+client+\"&finance_no=\"+finance+\"\";");
                out.println("window.open(m_url,'displayWindow12','left=110,top=90,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
                out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
                out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
                out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
                out.println("}");
				
				out.println("function load_documents(m_application_no,m_client_code,m_doc_code){");
				//out.println("  alert('load_documents'); ");
				//out.println("        m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_New_Delivery_order?chksql=main_page&application_no='+m_application_no+'&document_code=DEL_ORD&print=TRUE&client_code='+m_client_code+'';"); 
				out.println("        m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_New_Delivery_order?chksql=main_page&application_no='+m_application_no+'&document_code=DEL_ORD&print=TRUE&client_code='+m_client_code+'&doc_code='+m_doc_code+'';"); 
				out.println("        window.open(m_url,'displayWindow3','left=50,top=60,width=650,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
				out.println("}"); 
				
				out.println("</script>");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				out.println("<TABLE  WIDTH='80%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B> CR Book Track</B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table width='80%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='10%'    align=left><DIV class=factoring-letter-body><b>Contract No. </b></DIV></td>");
				out.println("<td width='10%'    align=left><DIV class=factoring-letter-body><b>Vehicle No. </b></DIV></td>");
				out.println("<td width='10%'    align=left><DIV class=factoring-letter-body><b>Status </b></DIV></td>");
				out.println("</tr>");

				// added by udara 06-05-2015
				rs= stmt.executeQuery(" "+							
						" SELECT A.FINANCE_NO, B.CLIENT_CODE, A.VEHICLE_NO, A.CR_STATUS "+
						" FROM "+m_schema_name+".AF_PRO_CR_BOOK A, "+m_schema_name+".AF_CO_PRO_SETTL_RECEIPT B "+
						" WHERE A.FINANCE_NO = B.SUS_REF_NO "+
						" AND B.CLOSING_FLAG = 'Y' "+
						" AND B.EFF_VALDATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND B.EFF_VALDATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+								
						" ");
					
	
				int j=1;
				int count = 0;
				
				try{
				
					while(rs.next()){
						
						count = count + 1;
						
						if(j==0){
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#FFFFFF\">");
							
							j=0;
						}
						
						out.println("<td width='10%' class=factoring-letter-body style=cursor:hand onClick=\"show_transaction_history_new('"+rs.getString(2)+"','"+rs.getString(1)+"')\"><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(4)+"</td>");
						out.println("</tr>");
					}

					
					
				
				}
				catch(Exception eee){
					out.println(eee.toString());
				}
				
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			
			
			
			
			
		}  catch(Exception ex) {
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