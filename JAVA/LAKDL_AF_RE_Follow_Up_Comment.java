
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Follow_Up_Comment extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt, stmt1;
	public ResultSet rs, rs1;
	java.text.NumberFormat nf,nf1;
	public String m_chksql;
	

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn =m_sn_methods.met_user_validate(req);
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			res.setHeader("Cache-Control", "No-Cache");
     		res.setDateHeader("Expires", 0);
			out = res.getOutputStream(); 
 
			// conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();

      		m_chksql=req.getParameter("chksql");
			String m_finance_no = req.getParameter("finance_no");
			String m_comment_id = req.getParameter("comment_id");
			String m_schema_name = m_sn_methods.schema_name;
			String phase = req.getParameter("phase");
			
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			if(m_chksql.equals("main_page")){

				out.println("<html><head><title>Add Follow Up Comment</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\">");
				
				// JS function to handle save and refresh parent window
				out.println("<script>");

				out.println("function checkCompletedStatus(dropdown) {");
				out.println("    var isCompleted = (dropdown.value === '005');");
				out.println("    ");
				out.println("    // Get the date input fields and the calendar button");
				out.println("    var followDay = document.getElementsByName('FOLLOW_DAY')[0];");
				out.println("    var followMonth = document.getElementsByName('FOLLOW_MONTH')[0];");
				out.println("    var followYear = document.getElementsByName('FOLLOW_YEAR')[0];");
				out.println("    var calendarBtn = document.getElementById('btn_calendar');");
				out.println("    ");
				out.println("    var displayStyle = isCompleted ? 'none' : '';");
				out.println("    // Disable or Enable them based on whether '005' is selected");
				out.println("    if (followDay) followDay.disabled = isCompleted;");
				out.println("    if (followMonth) followMonth.disabled = isCompleted;");
				out.println("    if (followYear) followYear.disabled = isCompleted;");
				out.println("    ");
				out.println("    // Hide the calendar button if it exists");
				out.println("    if (calendarBtn) calendarBtn.style.display = displayStyle;");				
				out.println("    ");
				// out.println("    // Clear the date fields if '005' is selected to prevent saving stale data");
				// out.println("    if (isCompleted) {");
				// out.println("        if (followDay) followDay.value = '';");
				// out.println("        if (followMonth) followMonth.value = '';");
				// out.println("        if (followYear) followYear.value = '';");
				// out.println("    }");
				out.println("}");

				out.println("function validate_form() {");
				out.println("    var form = document.Form1;");
				out.println("");
				out.println("    var actionStatus = form.txt_action.value;");
				out.println("    // 1. Validate Action Dropdown");
				out.println("    if (actionStatus === \"\") {");
				out.println("        alert(\"Please select an Action from the dropdown.\");");
				out.println("        form.txt_action.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				// out.println("    // 2. Validate Follow Up Date is not empty");
				// out.println("    var dayVal = form.FOLLOW_DAY.value;");
				// out.println("    var monthVal = form.FOLLOW_MONTH.value;");
				// out.println("    var yearVal = form.FOLLOW_YEAR.value;");
				// out.println("");
				// out.println("    if (dayVal === \"\" || monthVal === \"\" || yearVal === \"\") {");
				// out.println("        alert(\"Please enter a complete Follow Up Date (DD-MM-YYYY).\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				// out.println("    // 3. Validate Date is not in the past");
				// out.println("    var dayNum = parseInt(dayVal, 10);");
				// out.println("    var monthNum = parseInt(monthVal, 10) - 1;"); // JavaScript months are 0-indexed
				// out.println("    var yearNum = parseInt(yearVal, 10);");
				// out.println("");
				// out.println("    var selectedDate = new Date(yearNum, monthNum, dayNum);");
				// out.println("    var today = new Date();");
				// out.println("    today.setHours(0, 0, 0, 0);"); // Zero out the time for accurate day comparison
				// out.println("");
				// out.println("    if (selectedDate < today) {");
				// out.println("        alert(\"The Follow Up Date cannot be in the past. Please select today or a future date.\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				out.println("    if (actionStatus !== \"005\") {");
				out.println("");
				out.println("        // 2. Validate Follow Up Date is not empty");
				out.println("        var dayVal = form.FOLLOW_DAY.value;");
				out.println("        var monthVal = form.FOLLOW_MONTH.value;");
				out.println("        var yearVal = form.FOLLOW_YEAR.value;");
				out.println("");
				out.println("        if (dayVal === \"\" || monthVal === \"\" || yearVal === \"\") {");
				out.println("            alert(\"Please enter a complete Follow Up Date (DD-MM-YYYY).\");");
				out.println("            form.FOLLOW_DAY.focus();");
				out.println("            return false;");
				out.println("        }");
				out.println("");
				out.println("        // 3. Validate Date is not in the past");
				out.println("        var dayNum = parseInt(dayVal, 10);");
				out.println("        var monthNum = parseInt(monthVal, 10) - 1; // JavaScript months are 0-indexed");
				out.println("        var yearNum = parseInt(yearVal, 10);");
				out.println("");
				out.println("        var selectedDate = new Date(yearNum, monthNum, dayNum);");
				out.println("        var today = new Date();");
				out.println("        today.setHours(0, 0, 0, 0); // Zero out the time for accurate day comparison");
				out.println("");
				out.println("        if (selectedDate < today) {");
				out.println("            alert(\"The Follow Up Date cannot be in the past. Please select today or a future date.\");");
				out.println("            form.FOLLOW_DAY.focus();");
				out.println("            return false;");
				out.println("        }");
				out.println("");
				out.println("    }");
				out.println("    // 4. Validate Assign Person");
				out.println("    if (form.TXT_ASSIGN_PER.value === \"\") {");
				out.println("        alert(\"Please enter or select an Assign Person.\");");
				out.println("        form.TXT_ASSIGN_PER.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				out.println("    // 5. Validate Comment");
				out.println("    if (form.comment.value === \"\") {");
				out.println("        alert(\"Please enter a Comment.\");");
				out.println("        form.comment.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				out.println("    return true;");
				out.println("}");

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");

				out.println("function load_sysdate_new(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					out.println("document.Form1.FOLLOW_DAY.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.FOLLOW_MONTH.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.FOLLOW_YEAR.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 

				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open('"+m_class_url+"/"+m_fschema_name+"CO_Calendar_Window', \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				out.println("     document.Form1.FOLLOW_DAY.value=v_date;");
				out.println("     document.Form1.FOLLOW_MONTH.value=v_month;");
				out.println("     document.Form1.FOLLOW_YEAR.value=val;");
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_as_at_date.value=date1");
				//	out.println("alert(document.Form1.hid_as_at_date.value);");
				out.println("}");
				
				out.println("}");

				out.println("function help_button_assign_per() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_USERS_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_ASSIGN_PER.value+\"@\";"); 
				// out.println(" alert('check 1');");
				out.println("    HelpBox('1','10','0');"); 
				// out.println(" alert('check 1');");
				out.println("}");

				out.println("function MyDialog() {"); 
				out.println("    this.valout=new Array(10);"); 
				out.println("}");

				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				// out.println(" alert('check 2');");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("	help_value_assign_1()");
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
				out.println(" document.Form1.TXT_ASSIGN_PER.value=\"\";"); 
				
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("document.Form1.TXT_ASSIGN_PER.value=\"\"");
				out.println("	}	"); 
				out.println("}"); 

				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function help_value_assign_1() {"); 
				out.println("   document.Form1.TXT_ASSIGN_PER.value=oBj.valout[2];");
				out.println("}");

				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_ASSIGN_PER.value!=\"\"){");
				out.println("help_button_assign_per();");
				out.println("			}");
				out.println("}");

				out.println("function makeRequest1(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=check_active_status&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");

				String sqlFollowUp = "SELECT ACTION_CODE, "+
											"ACTION_DESC "+
											"FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION "+
											"ORDER BY ACTION_CODE ASC";

				rs=stmt1.executeQuery(sqlFollowUp);

				// out.println("function saveComment() {");
				// out.println("    alert('Check');"); 
				// String targetUrl = m_class_url + "/" + m_fschema_name + "AF_RE_Save_Follow_Up_Comment";
				// System.out.println("Target action URL: " + targetUrl);
				// out.println("    document.Form1.action = '"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");
				// out.println("    document.Form1.submit();");
				// out.println("}");

				out.println("function save_window(){");
				out.println("    if (validate_form()) {");
				out.println("        before_submit();");
				out.println("    }");
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");  
				out.println("			document.Form1.submit();"); 
				// out.println("    alert('Check' + document.Form1.TXT_ASSIGN_PER.value);"); 
				out.println("		}"); 
				out.println("} "); 
				

				out.println("function closePopup() { window.close(); }");
				out.println("</script>");
				out.println("</head><body class='body' onload='load_sysdate_new()'>");
				
				out.println("<form name='Form1' method='post'>");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<input type='hidden' name='finance_no' value='"+m_finance_no+"'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_as_at_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr><td width='30%'><b><u>Comment Option</u></b></td></tr>");

				out.println("<tr><td><b>Action</b></td><td><select name='txt_action' class='txt_input' onchange='checkCompletedStatus(this)'><option value='' selected ></option>");
				while(rs.next()){
					out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				};		
				out.println("</select></td></tr>");
				out.println("<tr><td><b>Follow Up Date</b></td>");
				out.println("<td width='15%'><input name=\"FOLLOW_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				out.println("<input  name=\"FOLLOW_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				out.println("<input name=\"FOLLOW_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)><a id='btn_calendar' href style='{cursor:hand; }' onclick=load_calendar('2')>  Calendar</a></td>");
				out.println("</tr>");
				out.println("<tr><td><b>Assign Person</b></td>");
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ASSIGN_PER' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_ASSIGN_PER)\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGN_PER' value=\"Help\" onClick=\"help_button_assign_per()\">"); 
				out.println("<tr><td valign='top'><b>Comment</b></td><td><textarea name='comment' class='txt_input' style='width:250px; height:80px;'></textarea></td></tr>");
				out.println("</table>");

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr>");
				out.println("<td width='10%'><input type='button' value='Save' class='mainbut'  onclick='save_window()'></td>");
				out.println("<td width='10%'><input type='button' value='Cancel' class='mainbut' onclick='closePopup()'></td>");
				out.println("<td width='*%'></td></tr>");
				out.println("</table>");
				
				out.println("</form></body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</html>");
				out.flush();
			}
			else if(m_chksql.equals("update_page")){

				String m_assign_by = "";
				String m_followup_date = "";
				String m_assign_to = "";
				String m_status = "";
				String m_comments = "";
				String m_action_code = "";
				// String m_ent_user = "";
				// String m_ent_date = "";
				
				out.println("<html><head><title>Follow Up Section</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\">");
				
				out.println("<script>");

				out.println("function checkCompletedStatus(dropdown) {");
				out.println("    var isCompleted = (dropdown.value === '005');");
				out.println("    ");
				out.println("    // Get the date input fields and the calendar button");
				out.println("    var followDay = document.getElementsByName('FOLLOW_DAY')[0];");
				out.println("    var followMonth = document.getElementsByName('FOLLOW_MONTH')[0];");
				out.println("    var followYear = document.getElementsByName('FOLLOW_YEAR')[0];");
				out.println("    var calendarBtn = document.getElementById('btn_calendar');");
				out.println("    ");
				out.println("    var displayStyle = isCompleted ? 'none' : '';");
				out.println("    // Disable or Enable them based on whether '005' is selected");
				out.println("    if (followDay) followDay.disabled = isCompleted;");
				out.println("    if (followMonth) followMonth.disabled = isCompleted;");
				out.println("    if (followYear) followYear.disabled = isCompleted;");
				out.println("    ");
				out.println("    // Hide the calendar button if it exists");
				out.println("    if (calendarBtn) calendarBtn.style.display = displayStyle;");				
				out.println("    ");
				// out.println("    // Clear the date fields if '005' is selected to prevent saving stale data");
				// out.println("    if (isCompleted) {");
				// out.println("        if (followDay) followDay.value = '';");
				// out.println("        if (followMonth) followMonth.value = '';");
				// out.println("        if (followYear) followYear.value = '';");
				// out.println("    }");
				out.println("}");

				out.println("function validate_form() {");
				out.println("    var form = document.Form1;");
				out.println("");
				out.println("    var actionStatus = form.txt_action_new.value;");
				out.println("    // 1. Validate Action Dropdown");
				out.println("    if (actionStatus === \"\") {");
				out.println("        alert(\"Please select a Status from the dropdown.\");");
				out.println("        form.txt_action_new.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				// out.println("    // 2. Validate Follow Up Date is not empty");
				// out.println("    var dayVal = form.FOLLOW_DAY.value;");
				// out.println("    var monthVal = form.FOLLOW_MONTH.value;");
				// out.println("    var yearVal = form.FOLLOW_YEAR.value;");
				// out.println("");
				// out.println("    if (dayVal === \"\" || monthVal === \"\" || yearVal === \"\") {");
				// out.println("        alert(\"Please enter a complete Follow Up Date (DD-MM-YYYY).\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				// out.println("    // 3. Validate Date is not in the past");
				// out.println("    var dayNum = parseInt(dayVal, 10);");
				// out.println("    var monthNum = parseInt(monthVal, 10) - 1;"); // JavaScript months are 0-indexed
				// out.println("    var yearNum = parseInt(yearVal, 10);");
				// out.println("");
				// out.println("    var selectedDate = new Date(yearNum, monthNum, dayNum);");
				// out.println("    var today = new Date();");
				// out.println("    today.setHours(0, 0, 0, 0);"); // Zero out the time for accurate day comparison
				// out.println("");
				// out.println("    if (selectedDate < today) {");
				// out.println("        alert(\"The Follow Up Date cannot be in the past. Please select today or a future date.\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				out.println("    if (actionStatus !== \"005\") {");
				out.println("");
				out.println("        // 2. Validate Follow Up Date is not empty");
				out.println("        var dayVal = form.FOLLOW_DAY.value;");
				out.println("        var monthVal = form.FOLLOW_MONTH.value;");
				out.println("        var yearVal = form.FOLLOW_YEAR.value;");
				out.println("");
				out.println("        if (dayVal === \"\" || monthVal === \"\" || yearVal === \"\") {");
				out.println("            alert(\"Please enter a complete Follow Up Date (DD-MM-YYYY).\");");
				out.println("            form.FOLLOW_DAY.focus();");
				out.println("            return false;");
				out.println("        }");
				out.println("");
				out.println("        // 3. Validate Date is not in the past");
				out.println("        var dayNum = parseInt(dayVal, 10);");
				out.println("        var monthNum = parseInt(monthVal, 10) - 1; // JavaScript months are 0-indexed");
				out.println("        var yearNum = parseInt(yearVal, 10);");
				out.println("");
				out.println("        var selectedDate = new Date(yearNum, monthNum, dayNum);");
				out.println("        var today = new Date();");
				out.println("        today.setHours(0, 0, 0, 0); // Zero out the time for accurate day comparison");
				out.println("");
				out.println("        if (selectedDate < today) {");
				out.println("            alert(\"The Follow Up Date cannot be in the past. Please select today or a future date.\");");
				out.println("            form.FOLLOW_DAY.focus();");
				out.println("            return false;");
				out.println("        }");
				out.println("");
				out.println("    }");
				out.println("    // 4. Validate Assign Person");
				out.println("    if (form.TXT_ASSIGN_TO.value === \"\") {");
				out.println("        alert(\"Please enter or select an Assign Person.\");");
				out.println("        form.TXT_ASSIGN_TO.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				out.println("    // 5. Validate Comment");
				out.println("    if (form.comment.value === \"\") {");
				out.println("        alert(\"Please enter a Comment.\");");
				out.println("        form.comment.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				out.println("    return true;");
				out.println("}");

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");

				

				out.println("function load_sysdate_new(){	"); 
				// rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				// if(rs1.next()){
				// 	out.println("document.Form1.FOLLOW_DAY.value='"+rs1.getString(1)+"';");
				// 	out.println("document.Form1.FOLLOW_MONTH.value='"+rs1.getString(2)+"';");
				// 	out.println("document.Form1.FOLLOW_YEAR.value='"+rs1.getString(3)+"';");
				// }
					String sqlFollowUp = " SELECT " +
											" FINANCE_NO, " +           // 1
											// " NVL(DECODE(ACTION, '001', 'New', '002', 'Pending', '003', 'In Progress','004','Hold','005','Completed', ACTION), '-'), " +               // 2
											" NVL((SELECT ACTION_DESC FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION WHERE ACTION_CODE = ACTION), '-'), " + // 2
											" TO_CHAR(FOLLOWUP_DATE,'DD'), " +  // 3 - Day
											" TO_CHAR(FOLLOWUP_DATE,'MM'), " +  // 4 - Month
											" TO_CHAR(FOLLOWUP_DATE,'YYYY'), " + // 5 - Year
											" ASSIGN_PERSON, " +        // 6
											" NVL(COMMENTS, '-') AS COMMENTS, " +             // 7
											" ENT_USER, " +             // 8
											" ENT_DATE, " +             // 9
											" FINISH_DATE, " +           // 10
											" ACTION, " +           // 11
											" COMMENT_ID " +           // 12
											" FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COMMENTS " +
											" WHERE FINANCE_NO='" + m_finance_no + "'" +
											// " AND UPPER(ASSIGN_PERSON) = UPPER('" + m_username+ "') " +
											" AND  COMMENT_ID = '" + m_comment_id+ "' ";

										rs = stmt1.executeQuery(sqlFollowUp);

										if(rs.next()){
											out.println("document.Form1.TXT_ASSIGN_BY.value='" + rs.getString(8) + "';");
											out.println("document.Form1.TXT_ASSIGN_TO.value='" + rs.getString(6) + "';");
											out.println("document.Form1.FOLLOW_DAY.value='" + rs.getString(3) + "';");
											out.println("document.Form1.FOLLOW_MONTH.value='" + rs.getString(4) + "';");
											out.println("document.Form1.FOLLOW_YEAR.value='" + rs.getString(5) + "';");
											out.println("document.Form1.txt_action.value='" + rs.getString(2) + "';");
											out.println("document.Form1.comment.value='" + rs.getString(7) + "';");
											out.println("document.Form1.hid_comment_id.value='" + rs.getString(12) + "';");
											m_action_code = rs.getString(11);
										}else{
											out.println("document.Form1.TXT_ASSIGN_BY.value='-';");
											out.println("document.Form1.TXT_ASSIGN_TO.value='-';");
											out.println("document.Form1.FOLLOW_DAY.value='-';");
											out.println("document.Form1.FOLLOW_MONTH.value='-';");
											out.println("document.Form1.FOLLOW_YEAR.value='-';");
											out.println("document.Form1.txt_action.value='-';");
											out.println("document.Form1.comment.value='-';");
											out.println("document.Form1.hid_comment_id.value='-';");

										}
				out.println("}"); 

				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open('"+m_class_url+"/"+m_fschema_name+"CO_Calendar_Window', \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				out.println("     document.Form1.FOLLOW_DAY.value=v_date;");
				out.println("     document.Form1.FOLLOW_MONTH.value=v_month;");
				out.println("     document.Form1.FOLLOW_YEAR.value=val;");
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_as_at_date.value=date1");
				//	out.println("alert(document.Form1.hid_as_at_date.value);");
				out.println("}");
				
				out.println("}");

				out.println("function help_button_assign_per() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_USERS_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_ASSIGN_TO.value+\"@\";"); 
				// out.println(" alert('check 1');");
				out.println("    HelpBox('1','10','0');"); 
				// out.println(" alert('check 1');");
				out.println("}");

				out.println("function MyDialog() {"); 
				out.println("    this.valout=new Array(10);"); 
				out.println("}");

				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				// out.println(" alert('check 2');");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("	help_value_assign_1()");
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
				out.println(" document.Form1.TXT_ASSIGN_TO.value=\"\";"); 
				
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("document.Form1.TXT_ASSIGN_TO.value=\"\"");
				out.println("	}	"); 
				out.println("}"); 

				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function help_value_assign_1() {"); 
				out.println("   document.Form1.TXT_ASSIGN_TO.value=oBj.valout[2];");
				out.println("}");

				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\" &&  document.Form1.TXT_ASSIGN_TO.value!=\"\"){");
				out.println("help_button_assign_per();");
				out.println("			}");
				out.println("}");

				out.println("function makeRequest1(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=check_active_status&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");

				String sqlFollowUp1 = "SELECT ACTION_CODE, "+
											"ACTION_DESC "+
											"FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION "+
											"ORDER BY ACTION_CODE ASC";

				rs=stmt1.executeQuery(sqlFollowUp1);

				// out.println("function saveComment() {");
				// out.println("    alert('Check');"); 
				// String targetUrl = m_class_url + "/" + m_fschema_name + "AF_RE_Save_Follow_Up_Comment";
				// System.out.println("Target action URL: " + targetUrl);
				// out.println("    document.Form1.action = '"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");
				// out.println("    document.Form1.submit();");
				// out.println("}");

				// out.println("function save_window(){	"); 
				// out.println("before_submit();"); 
				// out.println("}");
				
				out.println("function save_window(){");
				out.println("    if (validate_form()) {");
				out.println("        before_submit();");
				out.println("    }");
				out.println("}");
				
				out.println("function before_submit(){ "); 
				out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
				out.println("			var formElements = document.Form1.elements;");
				out.println("			for(var i = 0; i < formElements.length; i++) {");
				out.println("				if(formElements[i].disabled) {");
				out.println("					formElements[i].disabled = false;");
				out.println("				}");
				out.println("			}");
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");  
				out.println("			document.Form1.submit();"); 
				// out.println("    alert('Check' + document.Form1.TXT_ASSIGN_PER.value);"); 
				out.println("		}"); 
				out.println("} "); 
				

				out.println("function closePopup() { window.close(); }");
				out.println("</script>");
				out.println("</head><body class='body' onload='load_sysdate_new()'>"); // 
				
				out.println("<form name='Form1' method='post'>");
				out.println("<input  type='hidden' value='EDIT' name='SCREEN_NAME'> "); 
				out.println("<input type='hidden' name='finance_no' value='"+m_finance_no+"'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_as_at_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">");
				out.println("<input type='hidden' name='action_code' value='"+m_action_code+"'>");
				out.println("<input type='hidden' name='hid_comment_id' value='"+m_comment_id+"'>");
				 

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr><td width='50%'><b>Follow up Section</b></td></tr>");

				out.println("<tr><td><b>Contract Number</b></td>");
				out.println("<td width='*%' ><input class='txt_input' type='text' value='"+m_finance_no+"' name='TXT_CONTRACT_NO' maxlength='200' size='10' disabled>");
				out.println("</td></tr>"); 
				out.println("<tr><td><b>Assign By</b></td>");
				out.println("<td width='*%' ><input class='txt_input' type='text' value='' name='TXT_ASSIGN_BY' maxlength='200' size='10' disabled>");
				out.println("</td></tr>"); 
				out.println("<tr><td><b>Assign To</b></td>");
				out.println("<td width='*%' ><input class='txt_input' type='text' value='' name='TXT_ASSIGN_TO' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_ASSIGN_PER)\" >");
				out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGN_PER' value=\"Help\" onClick=\"help_button_assign_per()\">");
				// out.println("</td></tr>");
				// out.println("<tr><td><b>Assign Person</b></td>");
				// out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ASSIGN_PER' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_ASSIGN_PER)\" >"); 
				// out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGN_PER' value=\"Help\" onClick=\"help_button_assign_per()\">");
				out.println("<tr><td><b>Status</b></td>");
				out.println("<td width='*%' ><input class='txt_input' type='text' value='' name='txt_action' maxlength='200' size='10' disabled>");
				out.println("</td>");
				// out.println("<td><b>Change Status</b></td><td><select name='txt_action_new' class='txt_input'><option value='' selected></option>");
				out.println("<td><b>Change Status</b></td><td><select name='txt_action_new' class='txt_input' onchange='checkCompletedStatus(this)'><option value='' selected></option>");
				while(rs.next()){
					out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				};
				out.println("</select></td></tr>");

				out.println("</select></td></tr>");
				out.println("<tr><td><b>Follow Up Date</b></td>");
				out.println("<td width='15%'><input  name=\"FOLLOW_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				out.println("<input name=\"FOLLOW_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				out.println("<input name=\"FOLLOW_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)><a id='btn_calendar' href style='{cursor:hand; }' onclick=load_calendar('2')>  Calendar</a></td>"); //<a type='hidden' style='{cursor:hand; }' onclick=load_calendar('2')>  Calendar</a>
				out.println("</tr>");
				// out.println("<tr><td><b>Assign Person</b></td>");
				// out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ASSIGN_PER' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_ASSIGN_PER)\" >"); 
				// out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGN_PER' value=\"Help\" onClick=\"help_button_assign_per()\">"); 
				out.println("<tr><td valign='top'><b>Comment</b></td><td><textarea name='comment' class='txt_input' style='width:250px; height:80px;'></textarea></td></tr>");
				out.println("</table>");

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr>");
				out.println("<td width='10%'><input type='button' value='Save' class='mainbut'  onclick='save_window()'></td>");
				out.println("<td width='10%'><input type='button' value='Cancel' class='mainbut' onclick='closePopup()'></td>");
				out.println("<td width='*%'></td></tr>");
				out.println("</table>");
				
				out.println("</form></body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</html>");
				out.flush();
			}if(m_chksql.equals("normal_comment_page")){

				out.println("<html><head><title>Add Comment</title>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\">");
				
				// JS function to handle save and refresh parent window
				out.println("<script>");


				out.println("function validate_form() {");
				out.println("    var form = document.Form1;");
				out.println("");
				// out.println("    // 1. Validate Action Dropdown");
				// out.println("    if (form.txt_action.value === \"\") {");
				// out.println("        alert(\"Please select an Action from the dropdown.\");");
				// out.println("        form.txt_action.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				// out.println("    // 2. Validate Follow Up Date is not empty");
				// out.println("    var dayVal = form.FOLLOW_DAY.value;");
				// out.println("    var monthVal = form.FOLLOW_MONTH.value;");
				// out.println("    var yearVal = form.FOLLOW_YEAR.value;");
				// out.println("");
				// out.println("    if (dayVal === \"\" || monthVal === \"\" || yearVal === \"\") {");
				// out.println("        alert(\"Please enter a complete Follow Up Date (DD-MM-YYYY).\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				// out.println("    // 3. Validate Date is not in the past");
				// out.println("    var dayNum = parseInt(dayVal, 10);");
				// out.println("    var monthNum = parseInt(monthVal, 10) - 1;"); // JavaScript months are 0-indexed
				// out.println("    var yearNum = parseInt(yearVal, 10);");
				// out.println("");
				// out.println("    var selectedDate = new Date(yearNum, monthNum, dayNum);");
				// out.println("    var today = new Date();");
				// out.println("    today.setHours(0, 0, 0, 0);"); // Zero out the time for accurate day comparison
				// out.println("");
				// out.println("    if (selectedDate < today) {");
				// out.println("        alert(\"The Follow Up Date cannot be in the past. Please select today or a future date.\");");
				// out.println("        form.FOLLOW_DAY.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				// out.println("    // 4. Validate Assign Person");
				// out.println("    if (form.TXT_ASSIGN_PER.value === \"\") {");
				// out.println("        alert(\"Please enter or select an Assign Person.\");");
				// out.println("        form.TXT_ASSIGN_PER.focus();");
				// out.println("        return false;");
				// out.println("    }");
				// out.println("");
				out.println("    // 5. Validate Comment");
				out.println("    if (form.comment.value === \"\") {");
				out.println("        alert(\"Please enter a Comment.\");");
				out.println("        form.comment.focus();");
				out.println("        return false;");
				out.println("    }");
				out.println("");
				out.println("    return true;");
				out.println("}");

				out.println("function check_Date(objDD,objMM,objYY) {");
				out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
				out.println("if(checkMonthLength(objDD,objMM,objYY)){");
				out.println("}");
				out.println("}");

				out.println("function load_sysdate_new(){	"); 
				rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				if(rs1.next()){
					out.println("document.Form1.FOLLOW_DAY.value='"+rs1.getString(1)+"';");
					out.println("document.Form1.FOLLOW_MONTH.value='"+rs1.getString(2)+"';");
					out.println("document.Form1.FOLLOW_YEAR.value='"+rs1.getString(3)+"';");
				}
				out.println("}"); 

				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open('"+m_class_url+"/"+m_fschema_name+"CO_Calendar_Window', \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				out.println(" load_c_date(document.Form1.hid_cal_date.value);");
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
				out.println("     document.Form1.FOLLOW_DAY.value=v_date;");
				out.println("     document.Form1.FOLLOW_MONTH.value=v_month;");
				out.println("     document.Form1.FOLLOW_YEAR.value=val;");
				out.println("date1=v_date+'-'+v_month+'-'+val;");
				out.println("document.Form1.hid_as_at_date.value=date1");
				//	out.println("alert(document.Form1.hid_as_at_date.value);");
				out.println("}");
				
				out.println("}");

				out.println("function help_button_assign_per() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_USERS_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_ASSIGN_PER.value+\"@\";"); 
				// out.println(" alert('check 1');");
				out.println("    HelpBox('1','10','0');"); 
				// out.println(" alert('check 1');");
				out.println("}");

				out.println("function MyDialog() {"); 
				out.println("    this.valout=new Array(10);"); 
				out.println("}");

				out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("	"); 
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				// out.println(" alert('check 2');");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
				out.println("	help_value_assign_1()");
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
				out.println(" document.Form1.TXT_ASSIGN_PER.value=\"\";"); 
				
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("document.Form1.TXT_ASSIGN_PER.value=\"\"");
				out.println("	}	"); 
				out.println("}"); 

				out.println("function Prev(Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function Next (Start,End,Hid_No){"); 
				out.println("    HelpBox(Start,End,Hid_No);"); 
				out.println("}"); 
				out.println(""); 

				out.println("function help_value_assign_1() {"); 
				out.println("   document.Form1.TXT_ASSIGN_PER.value=oBj.valout[2];");
				out.println("}");

				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_ASSIGN_PER.value!=\"\"){");
				out.println("help_button_assign_per();");
				out.println("			}");
				out.println("}");

				out.println("function makeRequest1(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=check_active_status&data_val=\"+obj.value+\"&ac_status=Y\";");
				out.println("load_interface(m_url,'XML');");
				out.println("}");

				String sqlFollowUp = "SELECT ACTION_CODE, "+
											"ACTION_DESC "+
											"FROM " + m_schema_name + ".AF_CO_FOLLOWUP_COM_ACTION "+
											"ORDER BY ACTION_CODE ASC";

				rs=stmt1.executeQuery(sqlFollowUp);

				// out.println("function saveComment() {");
				// out.println("    alert('Check');"); 
				// String targetUrl = m_class_url + "/" + m_fschema_name + "AF_RE_Save_Follow_Up_Comment";
				// System.out.println("Target action URL: " + targetUrl);
				// out.println("    document.Form1.action = '"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");
				// out.println("    document.Form1.submit();");
				// out.println("}");

				out.println("function save_window(){");
				out.println("    if (validate_form()) {");
				out.println("        before_submit();");
				out.println("    }");
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println("		if(confirm(\"Are you sure you want to Save?\")){ "); 
				out.println("			document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Save_Follow_Up_Comment';");  
				out.println("			document.Form1.submit();"); 
				// out.println("    alert('Check' + document.Form1.TXT_ASSIGN_PER.value);"); 
				out.println("		}"); 
				out.println("} "); 
				

				out.println("function closePopup() { window.close(); }");
				out.println("</script>");
				out.println("</head><body class='body' onload='load_sysdate_new()'>");
				
				out.println("<form name='Form1' method='post'>");
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<input type='hidden' name='finance_no' value='"+m_finance_no+"'>");
				out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_as_at_date' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr><td width='30%'><b><u>Comment Option</u></b></td></tr>");

				// out.println("<tr><td><b>Action</b></td><td><select name='txt_action' class='txt_input'><option value='' selected></option>");
				// while(rs.next()){
				// 	out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				// };		
				// out.println("</select></td></tr>");
				// out.println("<tr><td><b>Follow Up Date</b></td>");
				// out.println("<td width='15%'><input name=\"FOLLOW_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				// out.println("<input  name=\"FOLLOW_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)>");
				// out.println("<input name=\"FOLLOW_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FOLLOW_DAY,document.Form1.FOLLOW_MONTH,document.Form1.FOLLOW_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>  Calendar</a></td>");
				// out.println("</tr>");
				// out.println("<tr><td><b>Assign Person</b></td>");
				// out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_ASSIGN_PER' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_ASSIGN_PER)\" >"); 
				// out.println("<input class='but_input' type='button' name='BUT_HELP_ASSIGN_PER' value=\"Help\" onClick=\"help_button_assign_per()\">"); 
				out.println("<tr><td valign='top'><b>Comment</b></td><td><textarea name='comment' class='txt_input' style='width:250px; height:80px;'></textarea></td></tr>");
				out.println("</table>");

				out.println("<table class='table' width='100%' cellpadding='5'>");
				out.println("<tr>");
				out.println("<td width='10%'><input type='button' value='Save' class='mainbut'  onclick='save_window()'></td>");
				out.println("<td width='10%'><input type='button' value='Cancel' class='mainbut' onclick='closePopup()'></td>");
				out.println("<td width='*%'></td></tr>");
				out.println("</table>");
				
				out.println("</form></body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</html>");
				out.flush();
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
