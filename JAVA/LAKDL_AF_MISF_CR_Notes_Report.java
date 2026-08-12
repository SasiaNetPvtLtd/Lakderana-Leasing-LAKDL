//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

// LAKDL_AF_MISF_CR_Notes_Report
public class LAKDL_AF_MISF_CR_Notes_Report extends javax.servlet.http.HttpServlet { 
	
	
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		ServletOutputStream out = null;
		Connection conn=null;
		java.text.NumberFormat nf= null,nf1= null;
		java.lang.Math a= null;
		Statement stmt= null,stmt2= null;
		CallableStatement callstmt1 =null;
		ResultSet rs= null,rs1= null,rs2= null;
		
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			
			
			
			if(m_chksql.equals("main_page")){ 
				
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");

				
				out.println("function print_report2(){");

				
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\" && document.Form1.VAL_DAY_TO.value!=\"\" && document.Form1.VAL_MONTH_TO.value!=\"\" && document.Form1.VAL_YEAR_TO.value!=\"\"){");
				out.println("			m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("			m_date_to=document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value;");
				out.println("			m_location=document.Form1.TXT_LOCATION_CODE.value;");
				
				//out.println("           var m_from_date     = '';  ");
			//	out.println("           var m_to_date       = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;  ");
				out.println("           var m_from_date       = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;  ");
				out.println("           var m_to_date       = document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value; ");
				//out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new&location=\"+m_location+\"&to_date=\"+m_to_date;");
				out.println("           m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=print_report_new_2&location=\"+m_location+\"&to_date=\"+m_to_date+\"&from_date=\"+m_from_date;");
				out.println("			window.open(m_url);");
				
				out.println("	}");
				out.println("}");
				

				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				
				
				
				out.println("function assignState(val){");
				out.println("document.Form1.hid_chk_status.value=val");
				out.println("}");
				

				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\" || document.Form1.VAL_DAY_TO.value==\"\" || document.Form1.VAL_MONTH_TO.value==\"\"  || document.Form1.VAL_YEAR_TO.value==\"\"   ){  "); 
				out.println("VDATE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CR_Notes_Report?chksql=main_page&generate=page';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Credit Notes Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Credit Notes Report - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
				out.println("}"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save_status.value=\"Save\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
				out.println("}else if(m_val==\"DEL\"){");  
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");
				out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("    this.valout   = new Array(10);"); 
				out.println("}		"); 
				out.println(""); 
				
				
				//----------------------------------------------------------------------------------------------------------------------------------------
				
				
				
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	if(oBj.valout[1] ==\" \"){"); 
				out.println("	clear_data(IfCount);");
				out.println("	}else");
				
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		team_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_user(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		help_value_assign_finance(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
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
				
				out.println("	else{");
				out.println("	clear_data(IfCount);");//Added To The Clear 
				out.println("	}");
				
				
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
				
				//-----------------------------------------------------------------------------------------------------------------------------------------
				
				out.println(""); 
				
				out.println("function help_button_finance() {"); 
				out.println(" document.Form1.hid_help_type.value='4' ");
				out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
				out.println("}"); 
				
				out.println("function help_value_assign_finance(oBj) {"); 
				out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
				out.println("}");
				
				out.println("function help_update() {"); 
				out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"99\"){"); 
				out.println("document.Form1.TXT_LOCATION_CODE.value='';");
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_USER.value='';");
				out.println("		}"); 
				out.println("}"); 
				
				
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//---added by Prabash on 09-05-2012-----**
				out.println("else  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.REN_DAY.value=v_date;");		
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//--------------------------------------**
				
				out.println("}");
				out.println("}");
				
				
				out.println("function check_Date(objDD,objMM,objYY) {");
				
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				
				out.println("}");
				
				out.println("function check_Date_2(objDD,objMM,objYY) {");
				out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY))");
				//out.println("     document.Form1.hid_date_2.value=document.Form1.TXT_DAY.value+'-'+document.Form1.TXT_MONTH.value+'-'+document.Form1.TXT_YEAR.value;");			
				out.println("     document.Form1.hid_date_2.value=document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value;");			
				out.println("}");
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					
					out.println("document.Form1.VAL_DAY_TO.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH_TO.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR_TO.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
					out.println("document.Form1.hid_date_2.value=document.Form1.VAL_DAY_TO.value+'-'+document.Form1.VAL_MONTH_TO.value+'-'+document.Form1.VAL_YEAR_TO.value;");				
				
				}
				out.println("}"); 
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()' > "); //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
				out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
				out.println("<input type=hidden name='hid_cal_date' value=\"\">");
				out.println("<input type=hidden name='hid_row_no' value=\"\">");
				out.println("<input type=hidden name='hid_date' value=\"\">");
				out.println("<input type=hidden name='hid_date_2' value=\"\">");
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("</tr>"); 
				out.println("<tr> "); 
				out.println("<td height='1'><img src='spacer.gif' width='1' height='1'></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td style='height: 327px'>"); 
				out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
				out.println("<tr>"); 
				out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Notes Report </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				
				out.println("<table align='center' width='100%' class='table' border='0'>"); 

				
				out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>From Date</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
				//out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width='20%'ID=VDATE>To Date</td>");//Added By Minal on 29-05-2015 for #16805
				out.println("<td width='*%'><input name=\"VAL_DAY_TO\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO)> ");
				out.println("    <input name=\"VAL_MONTH_TO\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO)> ");
				out.println("    <input name=\"VAL_YEAR_TO\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY_TO,document.Form1.VAL_MONTH_TO,document.Form1.VAL_YEAR_TO)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
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
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			
			
			else if(m_chksql.equals("print_report_new")){		

				String m_location="";
				String m_to_date="";

				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	


				
				String Sql_data="";				   
				
				/*
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE <> 'INV_GENER' "+
						              " AND ( INVOICE_TYPE <> 'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' )  "+            
						                                 
						              " AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+
						
						" UNION ALL "+
						                      
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT INVOICE_NO "+
						            " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
						            " WHERE  FINANCE_NO LIKE '%%' "+
						            " AND ACTIVE_STATUS = 'Y' "+
						            " AND INVOICE_TYPE <> 'INV_GENER' "+
						            " AND ( INVOICE_TYPE <> "+
						                            "  'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' ) "+
						          
						            " AND VALUE_DATE > TO_DATE ('"+m_to_date+"','DD-MM-YYYY') "+
						      " ) "+
						" AND ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 ))+ 1 ) "+
						" AND ADJUSTED_DATE <=  TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' )  "+
                " ";                  
				*/
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              //" AND INVOICE_TYPE <> 'INV_GENER' "+
						              " AND ( INVOICE_TYPE <> 'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' )  "+            
						                                 
						              " AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+
						
						" UNION ALL "+
						                      
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT INVOICE_NO "+
						            " FROM   "+m_schema_name+".AF_CO_PRO_INVOICE "+
						            " WHERE  FINANCE_NO LIKE '%%' "+
						            " AND ACTIVE_STATUS = 'Y' "+
						            //" AND INVOICE_TYPE <> 'INV_GENER' "+
						            " AND ( INVOICE_TYPE <> "+
						                            "  'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' ) "+
						          
						            " AND VALUE_DATE > TO_DATE ('"+m_to_date+"','DD-MM-YYYY') "+
						      " ) "+
						" AND ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 ))+ 1 ) "+
						" AND ADJUSTED_DATE <=  TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' )  "+
                " "; 

				
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='10%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='10%' ><b>Invoice No.</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Amount</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='10%' ><b>Value Date</b></td>");
				out.println("<td  width='*%' > &nbsp; </td>");
				out.println("</tr >");

				double tot_adj_amnt = 0;
				
				while(rs.next()){
					
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='10%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='10%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='10%' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='10%' ><b> &nbsp; </b></td>");
					out.println("<td width='10%' ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// ================================== added by udara 26-05-2015 ======================================================
			
			else if(m_chksql.equals("print_report_new_2")){		

				String m_location="";
				String m_to_date="";
				String m_from_date=""; 
				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}

				if(req.getParameter("from_date")!=null ){
					m_from_date=req.getParameter("from_date").trim();
				}
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";				   
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE, "+
						
						" ( SELECT  DECODE(NVL("+m_schema_name+".af_co_get_sub_charg_desc(INVOICE_TYPE),INVOICE_TYPE),'TERMINA','Closing Invoice','INV_GENER','Rental Invoice',NVL("+m_schema_name+".af_co_get_sub_charg_desc(INVOICE_TYPE),INVOICE_TYPE)) "+
						 " FROM AF_CO_PRO_INVOICE  "+
						 " WHERE INVOICE_NO = A.INVOICE_NO ) INV_TYPE, "+// 6
						"  A.REF_NO, "+ // 7   Add by A S 0n 2017-09-22
                    //    " NVL(INITCAP(a.NARRATIONS_CODE),'-') "+	//8  Add by A S 0n 2017-09-22
					//    " NVL("+m_schema_name+".AF_CO_GET_NARRATIONS_DETAILS(A.NARRATIONS_CODE),'-') "+
					      " NVL(A.REMARKS,'-') "+
						
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						//" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              //" AND INVOICE_TYPE <> 'INV_GENER' "+
										
										/* COMMENTED BY NISHANTHA ON 12-10-2020 #JB07102020-12050
						              " AND ( INVOICE_TYPE <> 'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' )  "+ 
										*/	//END NP		
													
													
						               //" AND VALUE_DATE >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+ // commented by udara 09-12-2015
									   //" AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+ // commented by udara 09-12-2015
										
						             // " AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
						      " ) "+
						//" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						//" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+
						" AND A.ADJUSTED_DATE >= TO_DATE ( '"+m_from_date+"', 'DD-MM-YYYY' ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+
						" ORDER BY A.FINANCE_NO,A.INVOICE_NO,A.ADJUSTED_DATE  "+
						
                " "; 

				//out.println(Sql_data);
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='1%' ><b>No.</b></td>"); 
				out.println("<td  width='15%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='15%' ><b>Invoice No.</b></td>");
				out.println("<td  width='15%' ><b>Invoice Type</b></td>"); // added by udara 29-06-2015
				out.println("<td  width='15%' align=\"right\" ><b>Adjusted Amount</b></td>");
				out.println("<td  width='1%' > &nbsp; </td>"); 
				out.println("<td  width='15%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='15%' ><b>Value Date</b></td>");
				out.println("<td  width='2*%' > &nbsp; </td>");
				out.println("<td  width='15%' ><b>Credit Note No</b></td>"); // Add by A S 0n 2017-09-22
				out.println("<td  width='15%' ><b>Narrations</b></td>"); //Add by A S 0n 2017-09-22
				out.println("</tr >");

				double tot_adj_amnt = 0;
				int counts = 0;
				
				while(rs.next()){
					counts = counts + 1;
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='1%' >"+counts+"</td>"); 
					out.println("<td width='15%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='15%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width='15%' >"+rs.getString(6)+"</td>");
					out.println("<td width='15%' align=\"right\" >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td  width='1%' > &nbsp; </td>"); 
					out.println("<td width='15%' >"+rs.getString(4)+"</td>");
					out.println("<td width='15%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='2*%' > &nbsp; </td>");
					out.println("<td width='15%' >"+rs.getString(7)+"</td>"); // Add by A S 0n 2017-09-22
					out.println("<td width='15%' >"+rs.getString(8)+"</td>"); // Add by A S 0n 2017-09-22
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='1%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='15%' ><b> Total </b></td>"); 
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("<td width='15%' align=\"right\" ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='1%' > &nbsp; </td>"); 
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("<td  width='2*%' > &nbsp; </td>");
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("<td width='15%' ><b> &nbsp; </b></td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// ========================================= end by udara 26-05-2015 ==================================================
			
			
			// ========================================= added by udara 07-04-2014 ============================================================
			
			else if(m_chksql.equals("print_report_new_cr_rental")){		

				String m_location="";
				String m_to_date="";

				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	


				
				String Sql_data="";				   
				
				
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE = 'INV_GENER' "+        
						              " AND VALUE_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+                  
						              " AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+												
                " "; 

				
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='10%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='10%' ><b>Invoice No.</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Amount</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='10%' ><b>Value Date</b></td>");
				out.println("<td  width='*%' > &nbsp; </td>");
				out.println("</tr >");

				double tot_adj_amnt = 0;
				
				while(rs.next()){
					
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='10%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='10%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>"); // out.println("<td width='10%' >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='10%' ><b> &nbsp; </b></td>");
					out.println("<td width='10%' ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			
			else if(m_chksql.equals("print_report_new_cr_arrears")){		

				String m_location="";
				String m_to_date="";

				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	


				
				String Sql_data="";				   
				
				
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE = 'INV_GENER' "+                        
						              " AND VALUE_DATE < ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+												
                " "; 

				
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='10%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='10%' ><b>Invoice No.</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Amount</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='10%' ><b>Value Date</b></td>");
				out.println("<td  width='*%' > &nbsp; </td>");
				out.println("</tr >");

				double tot_adj_amnt = 0;
				
				while(rs.next()){
					
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='10%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='10%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>"); // out.println("<td width='10%' >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='10%' ><b> &nbsp; </b></td>");
					out.println("<td width='10%' ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			else if(m_chksql.equals("print_report_new_cr_other_curr")){		

				String m_location="";
				String m_to_date="";

				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	


				
				String Sql_data="";				   
				
				
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE <> 'INV_GENER' "+  
										" AND ( INVOICE_TYPE <> "+
						                            "  'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' ) "+
						              " AND VALUE_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+                  
						              " AND VALUE_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+												
                " "; 

				
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='10%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='10%' ><b>Invoice No.</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Amount</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='10%' ><b>Value Date</b></td>");
				out.println("<td  width='*%' > &nbsp; </td>");
				out.println("</tr >");

				double tot_adj_amnt = 0;
				
				while(rs.next()){
					
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='10%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='10%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>"); // out.println("<td width='10%' >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='10%' ><b> &nbsp; </b></td>");
					out.println("<td width='10%' ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			
			else if(m_chksql.equals("print_report_new_cr_other_prev")){		

				String m_location="";
				String m_to_date="";

				if(req.getParameter("location")!=null ){
					m_location=req.getParameter("location").trim();
				}

				if(req.getParameter("to_date")!=null ){
					m_to_date=req.getParameter("to_date").trim();
				}
				
				stmt = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}

				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Credit Notes Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");

				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<form name='Form1'>");
				out.println("<br>");	
				out.println("<br>");	


				
				String Sql_data="";				   
				
				
				
				Sql_data=" "+		
					" SELECT "+
						" A.FINANCE_NO, "+
						" A.INVOICE_NO, "+
						" A.ADJUSTED_AMOUNT, "+
						" TO_CHAR(A.ADJUSTED_DATE,'DD-MM-YYYY'), "+
						" TO_CHAR((SELECT VALUE_DATE FROM  "+m_schema_name+".AF_CO_PRO_INVOICE WHERE  INVOICE_NO =  A.INVOICE_NO),'DD-MM-YYYY') VALUE_DATE "+
						" FROM   "+m_schema_name+".AF_CO_PRO_CREDIT_DETAILS A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						" WHERE  A.FINANCE_NO LIKE '%%' "+
						" AND    A.FINANCE_NO = B.FINANCE_NO "+
						" AND    A.FINANCE_NO IN (SELECT FINANCE_NO FROM LAKDL.AF_RE_TBD_RECOVERY_REPORT WHERE ENT_USER = '"+m_username+"') "+
						" AND    B.BRANCH_CODE LIKE '"+m_location+"%' "+
						" AND    A.INVOICE_NO IN "+
						      " ( SELECT  INVOICE_NO "+
						              " FROM     "+m_schema_name+".AF_CO_PRO_INVOICE "+
						              " WHERE  FINANCE_NO LIKE '%%' "+
						              " AND ACTIVE_STATUS = 'Y' "+
						              " AND INVOICE_TYPE <> 'INV_GENER' "+  
										" AND ( INVOICE_TYPE <> "+
						                            "  'INSURANCE' "+
						                         " AND REMARKS <> 'CHARGES - INSURANCE' ) "+
						              " AND VALUE_DATE < ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 )  "+
						      " ) "+
						" AND A.ADJUSTED_DATE >= ( LAST_DAY(ADD_MONTHS ( TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' ), -1 )) + 1 ) "+
						" AND A.ADJUSTED_DATE <= TO_DATE ( '"+m_to_date+"', 'DD-MM-YYYY' ) "+												
                " "; 

				
				int count = 0;
				
				rs=stmt.executeQuery(Sql_data);

				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u> Credit Notes Report </u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");

				out.println("<tr>");
				out.println("<td  width='10%' ><b>Finance No.</b></td>"); 
				out.println("<td  width='10%' ><b>Invoice No.</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Amount</b></td>");
				out.println("<td  width='10%' ><b>Adjusted Date</b></td>");
				out.println("<td  width='10%' ><b>Value Date</b></td>");
				out.println("<td  width='*%' > &nbsp; </td>");
				out.println("</tr >");

				double tot_adj_amnt = 0;
				
				while(rs.next()){
					
					tot_adj_amnt = tot_adj_amnt + rs.getDouble(3);
					
					out.println("<tr>");
					out.println("<td width='10%' >"+rs.getString(1)+"</td>"); 
					out.println("<td width='10%' STYLE=\"{cursor:hand;}\" onclick=\"show_invoice_drill('"+rs.getString(2)+"');\" ><u>"+rs.getString(2)+"</u></td>"); // out.println("<td width='10%' >"+rs.getString(2)+"</td>");
					out.println("<td width='10%' >"+nf.format(rs.getDouble(3))+"</td>");
					out.println("<td width='10%' >"+rs.getString(4)+"</td>");
					out.println("<td width='10%' >"+rs.getString(5)+"</td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");

					count+=1;
	
				}

				
				if(count>0){
					out.println("<tr>");
					out.println("<td width='10%' ><b> &nbsp; </b></td>"); 
					out.println("<td width='10%' ><b> &nbsp; </b></td>");
					out.println("<td width='10%' ><b>"+nf.format(tot_adj_amnt)+"</b></td>");
					out.println("<td  width='*%' > &nbsp; </td>");
					out.println("</tr>");	
					
				}
				
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/float_1.js'></SCRIPT>"); 
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
				
			}
			
			// ============================================= end by udara 07-04-2014 =======================================================
			
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
