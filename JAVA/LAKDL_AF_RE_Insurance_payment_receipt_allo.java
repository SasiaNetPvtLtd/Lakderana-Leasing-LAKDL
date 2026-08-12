

//SCREEN NAME:INSURANCE DETAIL REPORT FOR MANAGEMENT INFORMATION INSURANCE 
//CREATED BY:SANDUN JAYATHILAKE
//DATE/TIME:12/01/2009
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_Insurance_payment_receipt_allo extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	PreparedStatement pstmt;
	java.text.NumberFormat nf;
	
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
			
			
			if(m_screen_type.trim().equals("main_page")){	
				
				
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
				out.println("help_box.innerHTML=\"  Insuarance Payment Receipt Allocation - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\"  Insuarance Payment Receipt Allocation - \"+document.Form1.hid_status.value;"); 
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
				out.println("		document.Form1.hid_help_type.value = IfCount;"); 
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
				out.println("if(document.Form1.hid_help_type.value==\"5\"){");
				out.println("document.Form1.COM_NAME.value=\"\";");
				out.println("document.Form1.PAYEE.value=\"\";");
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
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_payment_receipt_allo?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_payment_receipt_allo?chksql=main_page';"); 
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
				
				/*
				out.println("function makeRequest(){");
				out.println("if(main_date_chk()){");
				out.println("var m_id =\"\" ;");
				out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				out.println("m_report_type = document.Form1.TXT_SELECT_CRIT.value;");
				out.println("if(m_report_type==\"COM_WISE\"){");
				out.println("m_id = document.Form1.COM_NAME.value;");
				out.println("chkSql = 'company_report';");		
				out.println("}else{");
				out.println("m_id = document.Form1.FIN_NO.value;");
				out.println("chkSql = 'client_report';");			
				out.println("}");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_com_client_Report?chksql=\"+chkSql+\"&id=\"+m_id+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&insurance_done=\"+document.Form1.TXT_INSURANCE_DONE.value;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				out.println("}");
				
				*/
				
				out.println("function load_criteria(){");
				out.println("m_selection = document.Form1.TXT_SELECT_CRIT.value;");
				out.println("if(m_selection==\"COM_WISE\"){");
				out.println("m_table_header.innerHTML=\"Payee\";");
				out.println("m_table_txt.innerHTML=\"<input type='hidden' name='PAYEE' ><input type='text' name='COM_NAME' class='txt_input' style='width:200' onblur='company_help()'  >\";");
				out.println("m_table_btton.innerHTML=\"<input type='button' name='COM_HELP' value='...' class='but_input' onClick='company_help()' >\";");
				out.println("}else{");
				out.println("m_table_header.innerHTML=\"Finance No\";");
				out.println("m_table_txt.innerHTML=\"<input type='text' name='FIN_NO' class='txt_input' style='width:150' onblur='finance_help()' >\";");
				out.println("m_table_btton.innerHTML=\"<input type='button' name='FIN_HELP' value='...' class='but_input' onClick='finance_help()' >\";");
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
				out.println("document.Form1.PAYEE.value=oBj.valout[2];");	
				out.println("}");	
				
				
				out.println("function get_vector_normal(http_response) {");
				out.println(" request_details.innerHTML = ''; ");
				out.println(" request_details.innerHTML = http_response; ");
				out.println("}");
				
				
				out.println("function makeRequest(){");
				out.println("if(main_date_chk()){");
				out.println("m_scr_name=document.Form1.hid_status.value");
				out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
				out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				//out.println("m_opt = document.Form1.TXT_INSURANCE_DONE.value");
				//out.println("m_opt = document.Form1.TXT_DEVISION.value");
				out.println("var m_payee_code = document.Form1.PAYEE.value;");
				out.println("var m_payment_code = document.Form1.PAYMENT_NO.value;");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_payment_receipt_allo?chksql=load_details&sr_name=\"+m_scr_name+\"&payee_code=\"+m_payee_code+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&payment_code=\"+m_payment_code+\"\";");
				//out.println("window.open(m_url);");
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				out.println("}");
				
				
				
				out.println("function before_submit() {");
				out.println(" var flag = true;");
				out.println(" for(var i=1;i<=parseInt(document.Form1.ROWCOUNT.value); i++){");
				out.println("    if(document.Form1.elements['SELECTION_'+i].checked){");
				out.println("      flag = false;");
				out.println("    }");
				out.println(" }");
				out.println("    if(flag){");
				out.println("      alert('Please select atleast one record');");
				out.println("      return;");
				out.println("    }");
				out.println(" ");
				out.println(" if(confirm(\"Are you sure, you want to save data?\")){ ");
		        out.println("  document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_Save_Insurance_receipt_allo';");
		        out.println("  document.Form1.submit();	");
                out.println(" }");
				out.println("}");
				
				
				out.println("</Script>");
				
				out.println("<body onload=\"load_sysdate(),load_criteria()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
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
				out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Insuarance Payment Receipt Allocation </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
				out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("<tr>");
				
				out.println("<td width='4%'></td>");
		
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onClick='load_screen_status(\"NEW\")' value=\"New\"></td>"); 
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>"); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='before_submit()' value=\"Save\"></td>"); 
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
				out.println("<td width='15%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
				out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				out.println("<td width='2%'></td>"); 
				out.println("<td width='2%' ID=VDATE>To</td>");
				out.println("<td width='15%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
				out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
				out.println("</td>");			
				out.println("</tr>");
				
				out.println("<tr class='tr_input' style='display:none'>"); 
				out.println("<td width='15%' style='display:none'>Selection Criteria</td>"); 
				out.println("<td width='15%' style='display:none'><select name='TXT_SELECT_CRIT' class='txt_input' style=\"width:100px;\" onchange=\"load_criteria()\">");
				out.println("<option value=\"COM_WISE\" >Company wise</option>");
				out.println("<option value=\"CLT_WISE\" >Client wise</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("<td width='2%' style='display:none'></td>"); 
				out.println("<td  width='15%' >Insurance Done By</td>"); 
				out.println("<td ='15%' ><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:80px;\" >");
				//out.println("<option value=\"LICENSEE\" >Licensee</option>");
				out.println("<option value=\"ALL\" SELECTED >All</option>");
				out.println("<option value=\"LICENSEE\" >Company</option>");
				out.println("<option value=\"BROKER\" >Broker</option>");
				out.println("<option value=\"CLIENT\" >Lessee</option>");
				
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>"); 
				
				out.println("<tr class=tr_input>"); 
				out.println("<td width='15%'><DIV ID='m_table_header'></td></DIV>");
				out.println("<td width='15%'><DIV ID='m_table_txt'></td></DIV>");
				out.println("<td width='10%' colspan=3><DIV ID='m_table_btton'></td></DIV>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='15%' align='left'></td>"); 
				out.println("<td width='10%'></td>"); 
				out.println("</tr>");		
				
				out.println("<tr class=tr_input>"); 
				out.println("<td width='15%'>Payment No</td>");
				out.println("<td width='15%'><input type='text' name='PAYMENT_NO' class='txt_input' style='width:200'   ></td>");
				out.println("<td width='10%' colspan=3></td>");
				out.println("<td width='10%'></td>"); 
				out.println("<td width='15%' align='left'><input type=\"button\" class='mainbut'onClick='makeRequest()' value=\"Go\"></td>"); 
				out.println("<td width='10%'></td>"); 
				out.println("</tr>");		
				
				out.println("</table>");	
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='request_details'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("</table>"); 
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</html>");
				
			}else if(m_screen_type.trim().equals("load_details")){	
				
				//String m_insuarane_type= req.getParameter("insuarane_type"); 
				String m_devision_type= req.getParameter("devision_type"); 
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date");
				String m_scr_name=req.getParameter("sr_name");
				String m_payee_no=req.getParameter("payee_code");
				String m_payment_no=req.getParameter("payment_code");
				
					int j=0;
					/*
					if(m_devision_type.equals("BIKE")){ 
						m_devision_type = "BD";
					}else if(m_devision_type.equals("LEASE")){
						m_devision_type = "AF";
					}
					
					if(m_finance_no.equals("")){			
					*/
					
					if(m_scr_name.equals("New")){
					rs=stmt.executeQuery(" SELECT   DISTINCT B.PAYMENT_NO, A.SUS_REF_NO,  "+
						"          NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), ' '),  "+ 
						"          NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))), ' '),  "+
						"          A.RECEIVER,  "+ 
						"          NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO, A.RECEIVER), ' ') PAYEE_NAME , "+
						"          NVL(C.RECEIPT_NO,' ') "+
						"     FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,  "+
						"          "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B,  "+
						"          "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C   "+
						"    WHERE UPPER(A.RECEIVER) LIKE UPPER('%"+m_payee_no+"%')   "+
						"      AND B.PAYMENT_NO LIKE UPPER('%"+m_payment_no+"%')     "+
						"      AND B.PAYMENT_NO = C.PAYMENT_NO   "+
						"      AND C.SUS_REF_NO = A.SUS_REF_NO      "+
						"      AND B.PROCESS_STATUS = 'DISBRS'      "+
						"      AND C.RECEIPT_NO IS NULL      "+
						"      AND B.ENTRY_TYPE = 'INSURANCE'       "+
						"      AND B.DISBURSE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')       "+
						"      AND B.DISBURSE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')       "+
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), ' ') ");       											
				}else  if(m_scr_name.equals("Edit")){
							
							rs=stmt.executeQuery(" SELECT   DISTINCT B.PAYMENT_NO, A.SUS_REF_NO,  "+
						"          NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), ' '),  "+ 
						"          NVL("+m_schema_name+".AF_CO_GET_CLIENT_NAME("+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO))), ' '),  "+
						"          A.RECEIVER,  "+ 
						"          NVL("+m_schema_name+".AF_CO_GET_REF_NAME(A.REF_NO, A.RECEIVER), ' ') PAYEE_NAME , "+
						"          NVL(C.RECEIPT_NO,' ') "+
						"     FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT A,  "+
						"          "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B,  "+
						"          "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN C   "+
						"    WHERE UPPER(A.RECEIVER) LIKE UPPER('%"+m_payee_no+"%')   "+
						"      AND B.PAYMENT_NO LIKE UPPER('%"+m_payment_no+"%')     "+
						"      AND B.PAYMENT_NO = C.PAYMENT_NO   "+
						"      AND C.SUS_REF_NO = A.SUS_REF_NO      "+
						"      AND B.PROCESS_STATUS = 'DISBRS'      "+
						"      AND C.RECEIPT_NO IS NOT NULL      "+
						"      AND B.ENTRY_TYPE = 'INSURANCE'       "+
						"      AND B.DISBURSE_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')       "+
						"      AND B.DISBURSE_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')       "+
						" ORDER BY NVL("+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(A.REF_NO)), ' ') "); 
							
						}
					/*}
				else{
					rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
						" A.CLIENT_CODE, "+//2
						" TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//3
						" DECODE(A.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//4
						" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//5
						" A.INSURANCE_DONE_BY, "+//6
						" A.APPLICATION_NO "+//7
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
						"      "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+                                                     
						" WHERE A.APPLICATION_NO = B.APPLICATION_NO "+
						" AND (A.FINANCE_NO ,B.INVOICE_NO) NOT IN( "+
						"                    SELECT C.FINANCE_NO ,C.PRO_INVOICE_NO "+
						"                    FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA C "+
						"                    ) "+
						// " AND A.INSURANCE_DONE_BY ='"+m_insuarane_type+"' "+ 
						" AND A.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008
						" AND A.FINANCE_NO = '"+m_finance_no+"' "+
						" AND A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
						" AND A.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
						" AND A.APPLICATION_STATUS='ACTIVATED' "); 			
				}*/
					
					
					
					boolean more = rs.next();
					if(!more){
						out.println("<table align='center' width='100%' class='table' border=0>");
						out.println("<tr>"); 
						out.println("<td width='20%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
						out.println("</tr>"); 
						out.println("<table>");
					}
					if(more){
						out.println("<table align='center' width='100%' class='table' border=0>"); 
						out.println("<tr class=pdn_txtpos2>"); 
						out.println("<td width='20%' align ='left'>Payment No.</td>");
						out.println("<td width='20%' align ='left'>Finance No.</td>"); 
						out.println("<td width='25%' align ='left'>Client Name</td>"); 
						out.println("<td width='25%' align ='left'>Payee Name</td>"); 
						out.println("<td width='25%' align ='left'>Receipt No</td>"); 
						out.println("<td width='10%' align ='left'>Selection</td>"); 
						
						
						out.println("</tr >"); 
					}
					while(more){
						j=j+1;
						if(j%2==1){
							out.println("<tr class=tr_input>"); 
						}
						else{
							out.println("<tr class=tr_input1>"); 
						}
						out.println("<td width='20%' align ='left' style='cursor:hand;' onclick=\"show_payment_drill('"+rs.getString(1)+"')\" ><input type=hidden name='HID_PAYMENT_NO_"+j+"'  value=\""+rs.getString(1)+"\"><input type=hidden name='HID_SUS_REF_NO_"+j+"'  value=\""+rs.getString(2)+"\"><u>"+rs.getString(1)+"</u></td>");
						out.println("<td width='20%' align ='left' STYLE=' cursor:hand;' onclick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><u>"+rs.getString(3)+"</u></td>"); 
						out.println("<td width='25%' align ='left'>"+rs.getString(4)+"</td>"); 
						out.println("<td width='10%' align ='left'>"+rs.getString(6)+"<input type='hidden' name='PAYEE_CODE_"+j+"' value ='"+rs.getString(5)+"' ></td>");		
						out.println("<td width='20%' align ='center'>"); 
						out.println("<input type='text' name='TXT_RECEIPT_NO_"+j+"' class='txt_input' style='width:120' value ='"+rs.getString(7)+"' >");
						
						
						/*
						rs1=stmt1.executeQuery("SELECT B.MAKE_CODE || '-' || B.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.REG_NO, "+//A.REG_NO Added By SJ on 28-11-2008
							" B.MAKE_CODE || '-' || B.MODEL_CODE || '-' ||A.ENGINE_NO || '-'|| A.REG_NO || '@' || A.INVOICE_NO || '@' "+
							" FROM "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS A,"+
							"      "+m_schema_name+".AF_CO_MAS_MODEL B, "+
							"      "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS C,"+
							"	   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS D"+
							" WHERE A.APPLICATION_NO=C.APPLICATION_NO "+
							" AND A.APPLICATION_NO=D.APPLICATION_NO"+
							" AND A.APPLICATION_NO='"+rs.getString(7)+"' "+
							" AND A.ASSET_ID=C.ASSET_ID "+
							" AND A.MODEL_CODE=B.MODEL_CODE "+
							// " AND D.INSURANCE_DONE_BY ='"+m_insuarane_type+"' "+\
							" AND D.DIVISION_CODE     = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008
							" AND A.ACTIVE_STATUS = 'Y'							"+							
							" AND (D.FINANCE_NO ,A.INVOICE_NO) NOT IN( "+
							" SELECT E.FINANCE_NO ,E.PRO_INVOICE_NO "+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA E )");													
						
						
						
						boolean more1 = rs1.next();
						while(more1){			
							out.println("<option value=\""+rs1.getString(2)+"\" >"+rs1.getString(1)+"</option>");	
							more1 = rs1.next();
							
						}
						
						*/
						out.println("</td>");			
						out.println("<td width='10%' align ='center'><input type='checkbox' name='SELECTION_"+j+"'  value='Y'> </td>");  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value--onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"',"+j+"')\"
						out.println("</tr>");
						
						more = rs.next();
						
					}
					
					out.println("</table >"); 	
					out.println("<input type='hidden' name='ROWCOUNT'  value='"+j+"'>");
					
				/*
				else if(m_scr_name.equals("Edit")){			
					int j=1;
					
					if(m_devision_type.equals("BIKE")){ 
						m_devision_type = "BD";
					}else if(m_devision_type.equals("LEASE")){
						m_devision_type = "AF";
					}
					
					
					if(m_finance_no.equals("")){	
						rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
							" A.ASSET_DESCRIPTION, "+//2
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
							" B.ACTIVATED_DATE, "+ //4
							" DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//5
							" A.PRO_INVOICE_NO, "+//6
							" B.APPLICATION_NO, "+//7
							" A.POLICY_NO, "+ //8
							" A.DEBIT_NOTE_NO "+ //9
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
							" AND B.APPLICATION_STATUS='ACTIVATED' "+	
							" AND A.BUSINESS_TYPE = 'NEW' "+
							" AND B.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008
							" AND B.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND B.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						// " AND A.INSURED_BY ='"+m_insuarane_type+"' ");
						
					}
					else{
						rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
							" A.ASSET_DESCRIPTION, "+//2
							" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
							" B.ACTIVATED_DATE, "+ //4
							" DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//5
							" A.PRO_INVOICE_NO, "+//6
							" B.APPLICATION_NO, "+//7
							" A.POLICY_NO, "+ //8
							" A.DEBIT_NOTE_NO "+ //9
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
							" WHERE  A.FINANCE_NO=B.FINANCE_NO "+
							" AND B.APPLICATION_STATUS ='ACTIVATED' "+
							" AND A.BUSINESS_TYPE  = 'NEW' "+
							" AND A.FINANCE_NO = '"+m_finance_no+"' "+
							" AND B.DIVISION_CODE  = '"+m_devision_type+"'  "+ //Added By Sanudn on 08-12-2008
							" AND B.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" AND B.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') ");
						// " AND A.INSURED_BY ='"+m_insuarane_type+"' ");
						
					}
					
					boolean more = rs.next();
					
					if(!more){
						out.println("<table align='center' width='100%' class='table' border=0>");
						out.println("<tr>"); 
						out.println("<td width='20%' align ='center'><font color='red'>No Data Found...!</font></td>"); 
						out.println("</tr>"); 
						out.println("<table>");
					}
					if(more){
						out.println("<table align='center' width='100%' class='table' border=0>"); 
						out.println("<tr class=pdn_txtpos2>"); 
						out.println("<td width='20%' align ='left'>Finance No.</td>"); 
						out.println("<td width='25%' align ='left'>Client Name</td>"); 
						out.println("<td width='10%' align ='left'>Status</td>"); 
						out.println("<td width='20%' align ='center'>Asset Description</td>"); 
						out.println("<td width='10%' align ='center'>Details</td>"); 
						out.println("</tr >"); 
					}
					while(more){
						
						if(j%2==1){
							out.println("<tr class=tr_input>"); 
						}
						else{
							out.println("<tr class=tr_input1>"); 
						}
						out.println("<input type=hidden name=\"app_no\" value=\""+rs.getString(7)+"\"></td>");
						out.println("<td width='20%' align ='left'>"+rs.getString(1)+"</td>"); 
						out.println("<td width='25%' align ='left'>"+rs.getString(3)+"</td>"); 
						out.println("<td width='10%' align ='left'>"+rs.getString(5)+"</td>");		
						out.println("<td width='20%' align ='center'>"); 
						out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\">");
						
						rs1=stmt1.executeQuery(" SELECT A.ASSET_DESCRIPTION , "+
							" A.ASSET_DESCRIPTION || '@' || A.PRO_INVOICE_NO ||'@'"+
							" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
							" WHERE A.FINANCE_NO = '"+rs.getString(1)+"' ");
						//" AND A.INSURED_BY ='"+m_insuarane_type+"'");
						
						boolean more1 = rs1.next();
						while(more1){			
							out.println("<option value=\""+rs1.getString(2)+"\" >"+rs1.getString(1)+"</option>");
							more1 = rs1.next();
						}
						out.println("</select></td>");
						out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(3)+"',"+j+",'"+rs.getString(8)+"','"+rs.getString(9)+"')\"></td>");  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value
						out.println("</tr>"); 			
						more = rs.next();
						j=j+1;
					}
					
					out.println("</table >"); 			
					
					
				}*/
				
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
			out.println(ostr.toString(e.toString())); 
			out.close();
			
		}
	}
}


