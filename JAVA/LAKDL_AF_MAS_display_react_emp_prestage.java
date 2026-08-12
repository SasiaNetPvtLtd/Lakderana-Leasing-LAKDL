//--
//SCREEN NAME:Adiminstration-Reactivate Employee
//CREATED BY :delanjali
//DATE/TIME  :2007-11-15
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_react_emp_prestage extends javax.servlet.http.HttpServlet { 
	Connection conn;
	ServletOutputStream out = null;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	java.text.NumberFormat nf,nf1;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			String fschema_name = m_sn_methods.schema_name;
			String m_chksql = req.getParameter("chksql");
			String m_act_status = req.getParameter("status");//status it is going to be changed into.
			String m_pre_status = req.getParameter("pre_status");//previouse status from which records to be updated.
			
			String m_head="";
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(2);
		  nf.setMaximumFractionDigits(2);
			
			
		
			String m_sort_column   = "EMP_CODE";	
			String m_order_by_type = "ASC";
			if(m_chksql.equals("main_page")){
			
			
			if(m_act_status.equals("Y")){
			m_head="2";
			}
			if(m_act_status.equals("B")){
			m_head="1";
			}


			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Employees Reactivation Stage "+m_head+" </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var chk_chng=0");
			out.println("var m_order_by_type='DESC'");

			out.println("function get_vector(data_vec) {");
			out.println("	 if(data_vec.length==0  && document.Form1.TXT_EMP_CODE.value!=''){");
			out.println("document.Form1.hid_st.value=''");
			out.println("emp_details.innerHTML=\"\";");
			out.println("help_update()");
			out.println("			}");
			out.println("}");
			

			out.println("function get_vector_normal(http_response) {");
			out.println(" emp_details.innerHTML = ''; ");
			out.println(" emp_details.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function call_url(){");
			out.println("if(emp_details.innerHTML==''){");
			out.println("chk_chng=0");
			out.println("}");
			out.println("else{");
			out.println("chk_chng=1");
			out.println("}");
			out.println("}");
			
			out.println("function view() {"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_react_emp_prestage?chksql=emp_details&act_status1="+m_act_status+"&pre_act1="+m_pre_status+"&emp='+document.Form1.TXT_EMP_CODE.value+'&order_type="+m_sort_column+"&type="+m_order_by_type+"';");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			
			out.println("function before_submit(){ ");
			out.println("call_url()");
			out.println("if(chk_chng==1){");
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("if(document.Form1.elements[chk].checked==false){"); 
			out.println("chk_chng=0");
			out.println("} ");
			out.println("else if (document.Form1.elements[chk].checked==true){");
			out.println("chk_chng=1");
			out.println("break");
			out.println("		}");		
			out.println("		}");
			out.println("		if(chk_chng==1){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_employee_react_stage'");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("else{");
			out.println("alert('Please select an employee to reactivate')");
			out.println("}");
			out.println("} "); 
			out.println("else{");
			out.println("alert('Please select an employee to reactivate')");
			out.println("}");
			out.println("} "); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_react_emp_prestage?chksql=main_page&status="+m_act_status+"&pre_status="+m_pre_status+"';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_react_emp_prestage?chksql=main_page&status="+m_act_status+"&pre_status="+m_pre_status+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

		  out.println("function sort_data(m_sort_col,val) {");
			out.println(" if(val=='ASC'){");
			out.println(" m_order_by_type='DESC'");
			out.println(" }");
			out.println("else{");
			out.println(" m_order_by_type='ASC'");
			out.println(" }");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_react_emp_prestage?chksql=emp_details&act_status1="+m_act_status+"&pre_act1="+m_pre_status+"&emp='+document.Form1.TXT_EMP_CODE.value+'&order_type='+m_sort_col+'&type='+m_order_by_type+'';");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_employee\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"System Administration - Employees Reactivation Stage "+m_head+" - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"System Administration - Employees Reactivation Stage "+m_head+" - \"+document.Form1.hid_status.value;"); 
			out.println("}");  

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"\";"); 
			out.println("document.Form1.hid_save.value=\"save\";");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.hid_save.value=\"Delete\";");
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

			out.println("function clear_data(){");
			out.println("    document.Form1.TXT_EMP_CODE.value='';"); 
			out.println("    document.Form1.TXT_EMP_CODE.focus();");
			out.println("emp_details.innerHTML=\"\";");
			out.println("}");		

			out.println("function check_change(row) {"); 
			out.println("  chk=\"CHK_APP_\"+row;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"N\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"Y\";"); 
			out.println("chk_chng=0");
			out.println("}");
			out.println("}"); 
			
			out.println("function check_account(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_employee&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_EMP_CODE.value+\"@\"+document.Form1.Hid_pre.value+\"@\";"); 
			out.println("    HelpBox('1','10','10');"); 
			out.println("}"); 
 
 			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_EMP_CODE.value=oBj.valout[2];"); 
			out.println(" view() "); 
			out.println("}"); 
			
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
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
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
			out.println("	else{");
			out.println("	clear_data();");
			out.println("	}");
			out.println("	}	"); 
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear_data()"); 
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
			out.println(""); 
			
			out.println("function check_change(row) {"); 
			out.println("  chk=\"CHK_APP_\"+row;");
			out.println("   if(document.Form1.elements[chk].checked==false){"); 
			out.println("    document.Form1.elements[chk].value=\"N\";"); 
			out.println("chk_chng=1");
			out.println("}");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("    document.Form1.elements[chk].value=\"Y\";"); 
			out.println("chk_chng=0");
			out.println("}");
			out.println("}"); 

			out.println("function View_all(){");	
			out.println("    m_sql = \"m_help_TXT_EMP_CODE_sql\";");
			out.println("    m_criteria =  document.Form1.TXT_EMP_CODE.value+\"@\";");
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 

			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No+\"&Screen_Name=EMP\", oBj,\"dialogWidth:50em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
	  	out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_act_screen_name' VALUE=\"AF_AD_EMPLOYEE_STAGE1\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_pre' VALUE=\""+m_pre_status+"\">");//EMPLOYEE PREVIOUSE STATUS
			out.println("<INPUT TYPE='Hidden' NAME='hid_act_stage' VALUE=\""+m_act_status+"\">");//EMPLOYEE IS SECOND TIME REACTIVATED
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Employees Reactivation Stage "+m_head+" </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\" ></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
		
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_EMP_CODE'  class=div_input>Employee Code</DIV></td>"); 
			out.println("<td width='27%' ><input class='txt_input' type='text' name='TXT_EMP_CODE' maxlength='20' size='20' onblur=\"check_account(document.Form1.TXT_EMP_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_EMP_CODE' value=\"Help\" onClick=\"help_update()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_EMP_DETAILS' value=\"Details\" onClick=\"view()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<br>"); 
			
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=emp_details></div></td></tr></table>");

			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			out.println("<br>"); 
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
	//---------------------------------------------------------------------------------------------------------------------------------------
	else if(m_chksql.equals("emp_details")){
				
			String m_emp=req.getParameter("emp");
			 m_sort_column = req.getParameter("order_type");	
			 m_order_by_type= req.getParameter("type");	
			
			String m_act_status1 = req.getParameter("act_status1");
			String m_pre_status1 = req.getParameter("pre_act1");

			rs= stmt.executeQuery
			("SELECT DISTINCT EMP_CODE, "+
			"(FIRST_NAME ||' '|| LAST_NAME) NAME, "+
			"ADDRESS, "+
			"DECODE(ACTIVE_STATUS,'A','Reactivated from Employee','B','Reactivated from Stage1') "+
			"FROM "+fschema_name+".CO_CO_MAS_EMPLOYEE "+
			"WHERE UPPER(EMP_CODE) LIKE UPPER('"+m_emp+"%') "+
			"AND  ACTIVE_STATUS='"+m_pre_status1+"' "+
			"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");



			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");	

			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"20%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Employee '    onclick=sort_data('EMP_CODE','"+m_order_by_type+"')>Employee Code</td>"); 
			out.println("<td width=\"20%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Name  '    onclick=sort_data('NAME','"+m_order_by_type+"')>Name</td>"); 
			out.println("<td width=\"25%\" align=\"left\" style= cursor:hand; title='Click here to sort by - Address  '    onclick=sort_data('ADDRESS','"+m_order_by_type+"')>Address</td>"); 
			out.println("<td width=\"25%\" align=\"left\" >Active Status</td>");
			out.println("<td width=\"*%\" align=\"center\" >Reactivate</td>");
			out.println("</tr>");
			boolean more=rs.next();
			int j=0;
			while(more){
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{
			
      out.println("<tr class=tr_input >");
			}
			out.println("<td align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_employee_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input  type=\"hidden\" name=TXT_EMPLOYEE_CODE_"+j+" value=\""+rs.getString(1)+"\" ></td>"); 
			out.println("<td align=\"left\" >"+rs.getString(2)+"</td>"); 
			out.println("<td align=\"left\" >"+rs.getString(3)+"</td>"); 
			out.println("<td align=\"left\" >"+rs.getString(4)+"</td>"); 
			out.println("<td align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\" ></td>");
			out.println("</tr>");
			

			j=j+1;
			more=rs.next();
			
			}
			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
		
			
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		  if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt1.close(); }catch(Exception e){}}
			
	    if(conn  !=null){try{conn.close(); }catch(Exception e){}}

				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
