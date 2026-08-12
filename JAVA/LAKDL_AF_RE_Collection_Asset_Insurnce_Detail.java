
//SCREEN NAME:ASSET INSURANCE DETAIL FOR MANAGEMENT INFORMATION COLLECTION 
//CREATED BY:SANDUN
//DATE/TIME:02/10/2008
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Asset_Insurnce_Detail extends javax.servlet.http.HttpServlet { 

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
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+document.Form1.hid_status.value;"); 
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=main_page';"); 
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
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" request_details.innerHTML = ''; ");
			out.println(" request_details.innerHTML = http_response; ");
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
			out.println("m_scr_name=document.Form1.hid_status.value");
			out.println("m_from_date=document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("m_to_date=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			//out.println("m_opt = document.Form1.TXT_INSURANCE_DONE.value");
			out.println("m_opt = document.Form1.TXT_DEVISION.value");
			out.println("finance_no = document.Form1.TXT_FINANCE_NO.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=load_details&sr_name=\"+m_scr_name+\"&finance_no=\"+finance_no+\"&devision_type=\"+m_opt+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			out.println("}");
			
			
			out.println("function load_scr(val1,val2,val3){");
			out.println("ast_des=document.Form1.elements['TXT_ASSET_DETA_'+val3].value;");	
			//out.println("alert(ast_des)");
			out.println("foundAtStartPos = ast_des.indexOf('@');");
			out.println("foundAtEndPos = ast_des.lastIndexOf('@');");
			out.println("description = ast_des.substring(0,foundAtStartPos);");
			//out.println("alert(description)");
			out.println("invoice_no= ast_des.substring(foundAtStartPos+1,foundAtEndPos);");
			//out.println("alert(invo_no)");
			out.println("m_scr_name=document.Form1.hid_status.value");
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=load_details_btt&finance_no=\"+val1+\"&clint_name=\"+val2+\"&asst_deta=\"+val3+\"\";");//
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail?chksql=load_details_btt&sr_name=\"+m_scr_name+\"&invo_no=\"+invoice_no+\"&finance_no=\"+val1+\"&clint_name=\"+val2+\"&asst_deta=\"+description+\"\";");
			out.println("window.open(m_url,'displayWindow2','left=175,top=60,width=650,height=600,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("function help_finance() {"); 
			out.println("document.Form1.hid_help_type.value=\"4\";"); 
			out.println("Crit = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_4','4');");
			out.println("}");
		 
		  out.println("function finance_assign(oBj) {");				
			out.println("document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}");
					
			out.println("</Script>");
			
			out.println("<body onload=\"load_sysdate()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
			out.println("<form name=\"Form1\" method=post>");
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
			//out.println("<input type='hidden' name='hid_invo_no_0' value=\"\">");
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
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Prossess - Asset Insurance Details </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");
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
			out.println("<table align='center' width='100%' class='table' border='0'>"); 			
			
			out.println("<tr class=tr_input>");
			out.println("<td width='7%' ID=VDATE>From</td>");
			out.println("<td width='8%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
			out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='2%'></td>"); 
			out.println("<td width='2%' ID=VDATE>To</td>");
			out.println("<td width='15%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
			out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='10%'></td>"); 
			out.println("<td width='10%'></td>"); 
			out.println("</tr>");	
			out.println("<tr width=100%><td>&nbsp;</td>"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			
		  out.println("<td width='9%'>Devision</td>"); //Mod By SJ on 08-12-2008
			out.println("<td width='15%'><select name='TXT_DEVISION' class='txt_input' style=\"width:100px;\" onchange=\"\">");
			out.println("<option value=\"BIKE\" >Bike</option>");
			out.println("<option value=\"LEASE\" selected>Leasing</option>");			
			out.println("</select>");
			out.println("</td>");
			
			out.println("</tr>"); 
			out.println("<tr class=tr_input>"); 
		  /*
		  out.println("<td width='9%'>Insurance Done By</td>");  //Commented By SJ on 08-12-2008
			out.println("<td width='15%'><select name='TXT_INSURANCE_DONE' class='txt_input' style=\"width:100px;\" onchange=\"\">");
			out.println("<option value=\"LICENSEE\" >Licensee</option>");
			out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" >Client</option>");
			out.println("</select>");
			out.println("</td>");
			*/				
			
			
				out.println("<td  width='9%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number</DIV></td>"); 
			out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' style='width:100'  OnBlur=\"help_finance()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"Help\" onClick=\"help_finance()\" > </td>"); 
			out.println("<td width='5%'></td>");
			
			//out.println("<td width='1%'></td>"); 
			//out.println("<td width='2%'></td>");
			out.println("<td width='8%' align='left'><input type=\"button\" class='mainbut'onClick='makeRequest()' value=\"Go\"></td>"); 
			out.println("<td width='20%'></td>"); 
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("</html>");

					
			}else	if(m_screen_type.equals("load_details")){
      
			//String m_insuarane_type= req.getParameter("insuarane_type"); 
			String m_devision_type= req.getParameter("devision_type"); //Added By Sanudn on 08-12-2008
			String m_from_date = req.getParameter("from_date");
			String m_to_date = req.getParameter("to_date");
			String m_scr_name=req.getParameter("sr_name");
			String m_finance_no=req.getParameter("finance_no");
			if(m_scr_name.equals("New")){
			int j=1;
			
			if(m_devision_type.equals("BIKE")){ //Added By Sanudn on 08-12-2008
			m_devision_type = "BD";
			}else if(m_devision_type.equals("LEASE")){
			m_devision_type = "AF";
			}
		
		if(m_finance_no.equals("")){												
			rs=stmt.executeQuery(" SELECT DISTINCT A.FINANCE_NO, "+//1
													 " A.CLIENT_CODE, "+//2
													 " TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'), "+//3
													 " DECODE(A.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//4
													 " LAKDL.AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE), "+//5
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
                           " AND A.ACTIVATED_DATE <=TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
													 " AND A.ACTIVATED_DATE >=TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
													 " AND A.APPLICATION_STATUS='ACTIVATED' ");             											
			
			}
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
			out.println("<td width='25%' align ='left'>"+rs.getString(5)+"</td>"); 
			out.println("<td width='10%' align ='left'>"+rs.getString(4)+"</td>");		
			out.println("<td width='20%' align ='center'>"); 
			out.println("<select name=TXT_ASSET_DETA_"+j+" class='txt_input' style=\"width:200px;\">");
			
																	
																
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
			out.println("</select></td>");			
			out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"','"+j+"');\"></td>");  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value--onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(5)+"',"+j+"')\"
			out.println("</tr>");
			
			more = rs.next();
			j=j+1;
			}
						
			out.println("</table >"); 	
			
			}
			else if(m_scr_name.equals("Edit")){			
			int j=1;
			
		if(m_finance_no.equals("")){	
			rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+//1
										       " A.ASSET_DESCRIPTION, "+//2
										       " "+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE), "+//3
										       " B.ACTIVATED_DATE, "+ //4
										       " DECODE(B.APPLICATION_STATUS,'ACTIVATED','Activated'), "+//5
										       " A.PRO_INVOICE_NO, "+//6
													 " B.APPLICATION_NO "+//7
													 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													 " WHERE  A.FINANCE_NO=B.FINANCE_NO "+
													 " AND B.APPLICATION_STATUS='ACTIVATED' "+														
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
													 " B.APPLICATION_NO "+//7
													 " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A,"+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													 " WHERE  A.FINANCE_NO=B.FINANCE_NO "+
													 " AND B.APPLICATION_STATUS ='ACTIVATED' "+
													 " AND A.FINANCE_NO = '"+m_finance_no+"' "+
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
			out.println("<td width='10%' align ='center'><input type='button' name='butt_detail' class='mainbut' value='Details' onclick=\"load_scr('"+rs.getString(1)+"','"+rs.getString(3)+"',"+j+")\"></td>");  //'+document.Form1.elements[TXT_ASSET_DETA_"+j+"].value
			out.println("</tr>"); 			
			more = rs.next();
			j=j+1;
			}
						
			out.println("</table >"); 			
					
			
			}
			}
			
			else	if(m_screen_type.equals("load_details_btt")){
			
			String m_finance_no=req.getParameter("finance_no");
			String m_cli_name=req.getParameter("clint_name");
			String m_asset_deta=req.getParameter("asst_deta");
			String m_invoice_no=req.getParameter("invo_no");
			String m_scr_name=req.getParameter("sr_name");
						
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Asset Financing System</title>    ");
			out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
			out.println("</head>");

			out.println("<script>");
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println(" document.Form2.txt_policy_no.value=\"\";");
			out.println(" document.Form2.start_dd.value=\"\";");
			out.println(" document.Form2.start_mm.value=\"\";");
			out.println(" document.Form2.start_yy.value=\"\";");
			out.println(" document.Form2.end_dd.value=\"\";");
			out.println(" document.Form2.end_mm.value=\"\";");
			out.println(" document.Form2.end_yy.value=\"\";");
			out.println(" document.Form2.txt_sum_in.value=\"\";");
			out.println(" document.Form2.txt_premium.value=\"\";");
			out.println(" document.Form2.txt_in_company.value=\"\";");
			//out.println(" document.Form2.txt_insurance_done.value=\"\";");
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form2.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date1='' ");
			out.println("var date2='' ");
		  out.println("  if(document.Form2.hid_cal_date.value=='2'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form2.start_dd.value=v_date;");
			out.println("     document.Form2.start_mm.value=v_month;");
			out.println("     document.Form2.start_yy.value=val;");
			out.println("date1=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form2.hid_from_date.value=date1");
						
			out.println("}");
				
			out.println("  if(document.Form2.hid_cal_date.value=='3'){"); 
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("     document.Form2.end_dd.value=v_date;");
			out.println("     document.Form2.end_mm.value=v_month;");
			out.println("     document.Form2.end_yy.value=val;");
			out.println("date2=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
			out.println("document.Form2.hid_to_date.value=date2");
			
			out.println("}");

			out.println("}");
			
			out.println("function submit_data(){");
			out.println("if(validate_data()){");
			out.println("document.Form2.txt_policy_no.disabled=false;");
			out.println("	if(confirm(\"Are you sure, you want to save data?\")){ ");
			out.println("		document.Form2.action='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Save_Asset_Insurance_Detail';");  
			out.println("		document.Form2.submit();	");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("function validate_data(){");
			out.println("if(document.Form2.txt_policy_no.value==\"\"){;");
			out.println("alert('Please Enter Policy No..!');");
			out.println("document.Form2.txt_policy_no.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.start_dd.value==\"\" || document.Form2.start_mm.value==\"\" || document.Form2.start_yy.value==\"\"){");
			out.println("alert('Please Enter Start Date..!');");			
			out.println("return false;");
			out.println("}else if(document.Form2.end_dd.value==\"\" || document.Form2.end_mm.value==\"\" || document.Form2.end_yy.value==\"\"){");
			out.println("alert('Please Enter End Date..!');");			
			out.println("return false;");			
			out.println("}else if(document.Form2.txt_sum_in.value==\"\"){;");
			out.println("alert('Please Enter Inssured Sum..!');");
			out.println("document.Form2.txt_sum_in.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.txt_premium.value==\"\"){;");
			out.println("alert('Please Enter Premium Value..!');");
			out.println("document.Form2.txt_premium.focus();");
			out.println("return false;");
			out.println("}else if(document.Form2.txt_in_company.value==\"\"){;");
			out.println("alert('Please Enter Insurance Company Name..!');");
			out.println("document.Form2.txt_in_company.focus();");
			out.println("return false;");
			out.println("}else if(!chk_date()){;");//Added By Sandun on 01-01-2009
			out.println("alert('End date must be greater than Start Date..!');");
			out.println("return false;");
			out.println("}else{");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function chk_date(){");//Added By Sandun on 01-01-2009
			out.println("end_date=document.Form2.end_dd.value+'-'+document.Form2.end_mm.value+'-'+document.Form2.end_yy.value;");
			out.println("start_date=document.Form2.start_dd.value+'-'+document.Form2.start_mm.value+'-'+document.Form2.start_yy.value;");
			out.println("var d1 = new Date(start_date);");
			out.println("var d2 = new Date(end_date);");		
			out.println("if(d2>d1){");		
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"  Collection Prossess - Asset Insurance Details - \"+document.Form2.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function check_amt(obj,size){");
			out.println("if(obj.value!=''){"); 
			out.println("format_number(obj,18)"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function window_close(){");
			out.println("	if(confirm(\"Are you sure you want to close the screen?\")){ ");
			out.println("window.close()");
			out.println("}");
			out.println("}");
			
			out.println("function check_date(objdd,objmm,objyy) {"); 						
			out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");
			//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
      out.println("}");
			out.println("}");
			
			out.println("</script>");
			out.println("</body>");
			
			out.println("<form name=\"Form2\" method=post>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_finance_no' VALUE=\""+m_finance_no+"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_asset_deta' VALUE=\""+m_asset_deta+"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_invo_no' VALUE=\""+m_invoice_no+"\">");
			
		
			out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >");
			out.println("<tr>");
			out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
			out.println("<tr>");
			out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box>Collection Prossess - Asset Insurance Details </td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
			out.println("<table class=table align=center cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
			out.println("<tr>");			
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='submit_data()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");' onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onclick='window_close()' value=\"Close\"></td>");  
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
		
						
			out.println("<table width='80%' class='table' border='0'>"); 	
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Finance No</td>"); 
			out.println("<td width='20%' align ='left'>"+m_finance_no+"</td>"); 
			out.println("</tr>");
			out.println("<td width='20%' align ='left'>Client Name</td>");
			out.println("<td width='25%' align ='left'>"+m_cli_name+"</td>"); 
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Asset Description</td>");
			out.println("<td width='25%' align ='left'>"+m_asset_deta+"</td>");
			out.println("</tr>");
			
			
			
			if(m_scr_name.equals("New")){			
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Policy No</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:153px;\" maxlength=20 value=\"\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Start Date</td>"); 
			out.println("<td width='10%' ><input name=\"start_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>End Date</td>"); 
			out.println("<td width='10%' ><input name=\"end_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_sum_in\" value=\"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Premium</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_premium\" value=\"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insured By</td>"); 
			out.println("<td width='8%'><select name='txt_insurance_done' class='txt_input' style=\"width:153px;\">");
			out.println("<option value=\"LICENSEE\" selected>Company</option>");
			//out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" >Lessee</option>");
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>"); 
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insurance company</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:250px;\" name=\"txt_in_company\" maxlength=100 value=\"\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");//Added By Sandun on 08-01-2009
			out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
			out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px'></TEXTAREA></td>"); 			
			out.println("</tr>");
			
			}	
			
			
			else if(m_scr_name.equals("Edit")){
			
					String m_start_date_dd="";
					String m_start_date_mm="";
					String m_start_date_yy="";
					String m_end_date_dd="";
					String m_end_date_mm="";
					String m_end_date_yy="";
					String m_policy_no="";
					String m_start_date="";
					String m_end_date="";
					String m_insur_by="";
					String m_insur_company="";
					String m_insur_remarks="";
					double m_premium=0.0;
					double m_sum_insur=0.0;
			
				  rs=stmt.executeQuery(" SELECT A.FINANCE_NO, "+ //1
												       " A.PRO_INVOICE_NO,"+ //2
												       " A.POLICY_NO, "+ //3
												       " A.ASSET_DESCRIPTION, "+ //4
												       " TO_CHAR(A.START_DATE,'DD-MM-YYYY'), "+ //5
												       " TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ //6
												       " A.SUM_INSSURED, "+ //7
												       " A.PREMIUM, "+ //8
												       " A.INSURED_BY, "+ //9
												       " A.INSUR_COM, "+  //10
															 " NVL(A.REMARKS,'-') "+//11
												       " FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+		
															 " WHERE A.FINANCE_NO = '"+m_finance_no+"' "+
															 " AND A.PRO_INVOICE_NO='"+m_invoice_no+"' ");
			
			boolean more = rs.next();
			if(more){
			m_policy_no     = rs.getString(3);
			m_start_date    = rs.getString(5);
			m_end_date      = rs.getString(6);
			m_premium       = rs.getDouble(8);
			m_sum_insur     = rs.getDouble(7);
			m_insur_by      = rs.getString(9);
			m_insur_company = rs.getString(10);
			m_insur_remarks = rs.getString(11);
			}
			m_start_date_dd = m_start_date.substring(0,2);
			m_start_date_mm = m_start_date.substring(3,5);
			m_start_date_yy = m_start_date.substring(6,10);
			m_end_date_dd   = m_end_date.substring(0,2);
			m_end_date_mm   = m_end_date.substring(3,5);
			m_end_date_yy   = m_end_date.substring(6,10);
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Policy No</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" name=\"txt_policy_no\" style=\"width:153px;\" maxlength=20 value=\""+m_policy_no+"\" ></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Start Date</td>"); 
			out.println("<td width='10%' ><input name=\"start_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_dd+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" value=\""+m_start_date_mm+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)> ");
			out.println("    <input name=\"start_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_start_date_yy+"\" onblur=check_date(document.Form2.start_dd,document.Form2.start_mm,document.Form2.start_yy)><a href style='{cursor:hand; }' onclick=load_calendar('2')> Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>End Date</td>"); 
			out.println("<td width='10%' ><input name=\"end_dd\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_end_date_dd+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_mm\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\"  value=\""+m_end_date_mm+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)> ");
			out.println("    <input name=\"end_yy\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" value=\""+m_end_date_yy+"\" onblur=check_date(document.Form2.end_dd,document.Form2.end_mm,document.Form2.end_yy)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Sum Inssured</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_sum_in\" value=\""+nf.format(m_sum_insur)+"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Premium</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:153px;text-align:right\" name=\"txt_premium\" value=\""+nf.format(m_premium)+"\" maxlength=18 onblur=\"check_amt(this,18)\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insured By</td>"); 
			out.println("<td width='8%'><select name='txt_insurance_done' class='txt_input' style=\"width:153px;\">");
			if(m_insur_by.trim().equals("LICENSEE")){
			out.println("<option value=\"LICENSEE\" SELECTED>Company</option>");
			//out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" >Lessee</option>");
		/*	}else if(m_insur_by.trim().equals("BROKER")){
			out.println("<option value=\"LICENSEE\" >Licensee</option>");
			out.println("<option value=\"BROKER\" SELECTED>Broker</option>");  //Mod By Sandun on 22-12-2008
			out.println("<option value=\"CLIENT\" >Client</option>");
			*/
			}else if(m_insur_by.trim().equals("CLIENT")){
			out.println("<option value=\"LICENSEE\" >Company</option>");
			//out.println("<option value=\"BROKER\" >Broker</option>");
			out.println("<option value=\"CLIENT\" SELECTED>Lessee</option>");
			}
			out.println("</select>");
			out.println("</td>");
			out.println("</tr>");
					
					
			out.println("<tr>");
			out.println("<td width='20%' align ='left'>Insurance company</td>"); 
			out.println("<td width='20%' align ='left'><input type=\"text\" class=\"txt_input\" style=\"width:250px;\" name=\"txt_in_company\" maxlength=100 value=\""+m_insur_company+"\"></td>"); 			
			out.println("</tr>");
			
			out.println("<tr>");//Added By Sandun on 08-01-2009
			out.println("<td width='20%' align ='left' valign=top>Remarks</td>"); 
			out.println("<td width='*%' align ='left'><TEXTAREA name='TXT_REMARK' class=\"txt_input\" style='width:250px' >"+m_insur_remarks+"</TEXTAREA></td>"); 			
			out.println("</tr>");
			
			}
					
			out.println("</table>");
		
			out.println("</form>");
			out.println("</body>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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


