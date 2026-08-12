
//--
//SCREEN NAME:FOLLOW UP HISTORY
//CREATED BY:SANDUN JAYATHILAKE
//DATE/TIME:28-11-2008
//NOTES:

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;
 

public class LAKDL_AF_CO_REP_display_follow_up_history extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			Connection conn;
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
						
			ResultSet rs,rs1;
			Statement stmt,stmt1;
			
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			
			String m_chksql = req.getParameter("chksql");
			
		if(m_chksql.equals("main_page")){
		
		  String m_sys_date = "";
			String m_date     = "";
			String m_month    = "";
			String m_year      = "";
		
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Follow Up History Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exsist');");
			out.println("				new_window();");
			out.println("			}");
			out.println("}");
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_follow_up&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			
			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

					
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_CO_REP_display_follow_up_history?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
		

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_follow_up\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Follow Up History Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Follow Up History Report  \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
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

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 

			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Servlet?class_in=\"+client_name+\"AF_MAS_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:25em; dialogHeight:18em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_user_assign();"); 
	  	out.println("		}");
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
						
			out.println("function help_user() {"); 
		  out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_Fin_No_Sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE.value+\"@\" ;"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_user_assign() {"); 
			out.println("    documentfrom.Form1.TXT_FINANCE.value=oBj.valout[2];");
			out.println("}");			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=340,height=230\");"); 
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
			out.println("}");
			out.println("}");
			
				
			
			out.println("function get_sys_date(){");
			rs = stmt.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL");
			if(rs.next()){
			m_date = rs.getString(1).substring(0,2);
			m_month = rs.getString(1).substring(3,5);
			m_year = rs.getString(1).substring(6,10);
			}
			out.println("document.Form1.VAL_DAY1.value   =\""+m_date+"\"");
			out.println("document.Form1.VAL_MONTH1.value =\""+m_month+"\"");
			out.println("document.Form1.VAL_YEAR1.value  =\""+m_year+"\"");
			out.println("document.Form1.VAL_DAY2.value   =\""+m_date+"\"");
			out.println("document.Form1.VAL_MONTH2.value =\""+m_month+"\"");
			out.println("document.Form1.VAL_YEAR2.value  =\""+m_year+"\"");
			out.println("}");
			
			
      out.println("function check_date(objdd,objmm,objyy) {");						
		//	out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
			out.println("  checkMonthLength(objdd,objmm,objyy);");			
     // out.println("}");
			out.println("}");
			
			
			
			out.println("function view_report(){");
			out.println("if(checkMonthLength(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)&&checkMonthLength(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)){");
			out.println("m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value;");
			out.println("m_to_date = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
			out.println("m_finance = document.Form1.TXT_FINANCE.value;");
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CO_REP_display_follow_up_history?chksql=histoty_report&m_to_date=\"+m_to_date+\"&m_from_date=\"+m_from_date+\"&finance_no=\"+m_finance+\"\";");  
		//out.println("alert(m_url);");
		  out.println("window.open(m_url,'displayWindow','left=50,top=60,width=1000,height=450,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			out.println("}");
     
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_sys_date()\">");  
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Follow Up History Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>");
			
			
			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");
		  out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' onClick='close_window()' value=\"Close\"></td>");
			
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


		  out.println("<br><br>");
		  out.println("<table class='table' border='0'> ");	
			out.println("<tr class=tr_input>");
			out.println("<td width='10%' ID=VDATE><b>From</td>");
			out.println("<td width='20%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			out.println("<input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			out.println("<input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
			out.println("</td>");
			
			out.println("<td width='5%' ID=VDATE><b>To</b>&nbsp;&nbsp;");
			out.println("<input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			out.println("<input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" > ");
			out.println("<input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
			out.println("</td>");
			out.println("<td width='*%'>&nbsp;</td>");
			out.println("<td width='*%'>&nbsp;</td>");
			out.println("</tr>");
			
			
			out.println("<tr><td width='*%'>&nbsp;</td></tr>");
			
			out.println("<tr >");
			out.println("<td width='10%' ><b>Finance No</td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='25' size='15' style='width:120'onblur=\"help_user()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_user()\"></td>");
		  out.println("<td width='20%' align='center'><input type='button' class='but_input' name='BTT_VIEW' value=\"View\" onClick=\"view_report()\"></td>");
			out.println("<td width='40%'></td>");
     	out.println("</tr>"); 
			
			out.println("<td></td>");
	  	out.println("</tr>"); 			
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
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			//stmt.close();
			}
			
			else if(m_chksql.equals("histoty_report")){
			
			String m_to_date = req.getParameter("m_to_date");
			String m_from_date = req.getParameter("m_from_date");
			String m_finance_no = req.getParameter("finance_no");
		
		  out.println("<html>");
			out.println("<title>Follow Up History Report</title>");
			out.println("<head>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("</head>");
			out.println("<body class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >");
			out.println("<br>");
			out.println("<table width='100%' align='center' border='0' class='table'>");
			out.println("<tr>");
			out.println("<td width='*%' align='center' ><b>Follow Up History Report - From "+m_from_date+" To "+m_to_date+" </td>");
			out.println("</tr>");
			out.println("</table>");
			
			out.println("<br><br>");
		
		
		if(m_finance_no.equals("")){
			rs1 = stmt1.executeQuery(" SELECT DISTINCT A.FOLLOW_UP_NO, "+  //1     
														 " NVL(A.ENT_USER,'-'), "+//2
														 " NVL(A.ENT_REMARKS,'-'),"+//3
														 " A.ACTION_ENT_DATE, "+//4
														 " NVL(C.FINANCE_NO,'-') "+//5
														 " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A ,"+
														 "	    "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT B,"+
														 "      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
														 "	WHERE A.ID_NO = B.OTHER_NO "+
														 "	AND B.APPLICATION_NO = C.APPLICATION_NO "+
														// "  AND TO_DATE(TO_CHAR(A.FOLLOWUP_DATE,'DD-MON-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
														// "  AND TO_DATE(TO_CHAR(A.FOLLOWUP_DATE,'DD-MON-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
														 "  AND A.FOLLOWUP_DATE >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
														 "  AND A.FOLLOWUP_DATE <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
			
			
			
			
			boolean more = rs1.next();
			int j=1;			
			
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width=100% align='center'><font color=red>No Data Found...!</font></td><tr>");
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='5%' align='left'>No</td>");
			out.println("<td width='15%' align='left'>Finance No</td>");
			out.println("<td width='15%' align='left'>Followup No</td>");
			out.println("<td width='30%' align='left'>Remarks</td>"); 			 
			out.println("<td width='10%' align='left'>User</td>"); 
			out.println("<td width='20%' align='left'>Completed Date</td>"); 
			out.println("</tr >");
			}
			
			while(more){			
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input1 >");
			}
			else{
      	out.println("<tr class=tr_input >");
			}
			out.println("<td width='5%' align='left'>"+j+"</td>");
			out.println("<td width='15%' align='left'>"+rs1.getString(5)+"</td>");
			out.println("<td width='15%' align='left'>"+rs1.getString(1)+"</td>");
			out.println("<td width='30%' align='left'>"+rs1.getString(3)+"</td>"); 			 
			out.println("<td width='20%' align='left'>"+rs1.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left'>"+rs1.getString(4)+"</td>"); 
			out.println("</tr >");
			more=rs1.next(); 
			j=j+1;
			} 
			out.println("</table>");
     }
			else{
			rs1 = stmt1.executeQuery(" SELECT DISTINCT A.FOLLOW_UP_NO, "+  //1     
														 " NVL(A.ENT_USER,'-'), "+//2
														 " NVL(A.ENT_REMARKS,'-'),"+//3
														 " A.ACTION_ENT_DATE "+//4
														 " FROM "+m_schema_name+".AF_CO_PRO_FOLLOW_UP A ,"+
														 "	    "+m_schema_name+".AF_CR_PRO_APPLICANT_DOCUMENT B,"+
														 "      "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
														 "	WHERE A.ID_NO = B.OTHER_NO "+
														 "	AND B.APPLICATION_NO = C.APPLICATION_NO "+
														 "  AND C.FINANCE_NO = '"+m_finance_no+"' "+
														 "  AND TO_DATE(TO_CHAR(A.FOLLOWUP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
														 "  AND TO_DATE(TO_CHAR(A.FOLLOWUP_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ");
			
			
			
			
			boolean more = rs1.next();
			int j=1;			
			if(!more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr><td width=100% align='center'><font color=red>No Data Found...!</font></td><tr>");
			out.println("</table>");
			}
			if(more){
			out.println("<table align='center' width='100%' class='table' border=0>"); 
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width='5%' align='left'>No</td>");
			out.println("<td width='15%' align='left'>Followup No</td>");
			out.println("<td width='30%' align='left'>Remarks</td>"); 			 
			out.println("<td width='10%' align='left'>User</td>"); 
			out.println("<td width='20%' align='left'>Completed Date</td>"); 
			out.println("</tr >");
			}
			
			while(more){			
			if(j>0 && j%2==1){
      	out.println("<tr class=tr_input1 >");
			}
			else{
      	out.println("<tr class=tr_input >");
			}
			out.println("<td width='5%' align='left'>"+j+"</td>");
			out.println("<td width='15%' align='left'>"+rs1.getString(1)+"</td>");
			out.println("<td width='30%' align='left'>"+rs1.getString(3)+"</td>"); 			 
			out.println("<td width='20%' align='left'>"+rs1.getString(2)+"</td>"); 
			out.println("<td width='10%' align='left'>"+rs1.getString(4)+"</td>"); 
			out.println("</tr >");
			more=rs1.next(); 
			j=j+1;
			} 
			out.println("</table>");	
			
			}
					
			out.println("</body >");
			out.println("</html >");
			
			
			}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
