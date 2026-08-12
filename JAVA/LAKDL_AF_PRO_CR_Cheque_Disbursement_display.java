/*
 * SCREEN NAME  : Credit Process - Cheque Disbursement
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-22
 * NOTES        :
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LAKDL_AF_PRO_CR_Cheque_Disbursement_display extends HttpServlet {
	
	public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		
		LAKDL_AF_CO_conn_methods m_sn_methods = null;
		String m_schema_name = null;
		String m_fschema_name = null;
		String m_class_url = null;
		String m_html_client_url = null;
		String m_username = null;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		ServletOutputStream out = null;
		
		NumberFormat numberFormat = null;
		
		String m_chksql = null;
		
		String m_screen_title = null;
		
		String group_payment_no = null;
		
		String m_sysdate_dd = null;
		String m_sysdate_mm = null;
		String m_sysdate_yy = null;
		String m_paymno;      //Added by Prabash on 14-02-2012
		
		int j = 0;
		
		try {
			
			m_sn_methods = new LAKDL_AF_CO_conn_methods();
			connection = m_sn_methods.met_user_validate(httpServletRequest);
			
			m_schema_name       = m_sn_methods.schema_name;
			m_fschema_name      = m_sn_methods.client_name;
			m_class_url         = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
			m_html_client_url   = m_sn_methods.html_client_url;
			m_username          = m_sn_methods.username;
			
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.setContentType("text/html");
			out = httpServletResponse.getOutputStream();
			
			numberFormat = NumberFormat.getInstance(Locale.US);
			numberFormat.setMinimumFractionDigits(2);
			numberFormat.setMaximumFractionDigits(2);
			
			
			
			sql = " " +
				"   SELECT TO_CHAR(SYSDATE, 'DD') DAY, " +
				"          TO_CHAR(SYSDATE, 'MM') MONTH, " +
				"          TO_CHAR(SYSDATE, 'YYYY') YEAR " +
				"   FROM   DUAL " +
				" ";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			resultSet.next();
			m_sysdate_dd        = resultSet.getString("DAY");
			m_sysdate_mm        = resultSet.getString("MONTH");
			m_sysdate_yy        = resultSet.getString("YEAR");
			
			resultSet.close();
			statement.close();
			
			
			m_chksql            = httpServletRequest.getParameter("chksql");
			
			if (m_chksql.equals("main_page")) {
				
				m_screen_title = "Finance - Cheque Disbursement";
				
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
				out.println("<html>");
				out.println("   <head>");
				out.println("       <title>" + m_screen_title + "</title>");
				
				out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
				
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/leasing_drill_down.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
				
				out.println("       <script type=\"text/javascript\">");
				
				out.println("           var g_get_data_type = 0;");
				out.println("           var g_calendar_type = 0;");
				out.println("           var g_calendar_row_id = 0;");
				
				out.println("           var g_screen_title = '" + m_screen_title + "';");
				out.println("           var g_sysdate = '" + m_sysdate_dd + " - " + m_sysdate_mm + " - " + m_sysdate_yy + "';");
				
				
				out.println("           function new_window() {");
				out.println("               window.location.href = window.location.href;");
				out.println("           }");
				
				
				out.println("           function clear_window() {");
				out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
				out.println("                   window.location.href = window.location.href;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function load_help_msg() {");
				out.println("               var m_help_message = 'm_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_dis';");
				out.println("               HelpBox_msg(m_help_message);");
				out.println("           }");
				
				
				out.println("           function HelpBox_msg(m_help_message) {");
				out.println("               var popupwin = window.showModalDialog(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Help_Msg_Servlet?class_in=' + client_name + 'AF_PRO_CR_Help_Msg_select&help_message_in=' + m_help_message);");
				out.println("           }");
				
				
				out.println("           function load_roll_value(m_val) {");
				out.println("               document.getElementById('help_box').innerHTML = g_screen_title + ' - ' + m_val;");
				out.println("           }");
				
				
				out.println("           function load_roll_out_value() {");
				out.println("               document.getElementById('help_box').innerHTML = g_screen_title;");
				out.println("           }");
				
				
				out.println("           function load_payments() {");
				out.println("               g_get_data_type = 1;");
				out.println("               var m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Cheque_Disbursement_display?chksql=load_data&paymno='+document.Form1.TXT_PAYMENT_NO.value+'';");
				out.println("               load_interface(m_url, 'NORM');");
				out.println("           }");
				
				
				out.println("           function show_details(row_id, payment_type) {");
				// out.println("               g_get_data_type = 2;");
				out.println("               var payment_no = document.getElementById('HID_PAYMENT_NO_' + row_id).value;");
				out.println("               var m_url = null;");
				
				out.println("               if (payment_type == 'VENDOR') {");
				out.println("                   m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_PRO_CR_Group_Payment_Details_display?group_payment_no=' + payment_no;");
				out.println("               }");
				out.println("               else if (payment_type == 'OTHER') {");
				out.println("                   m_url = servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_CR_PRO_Document_Payment_Voucher?chksql=main_page&payment_no=' + payment_no + '&print=TRUE';");
				out.println("               }");
				// out.println("               load_interface(m_url, 'NORM');");
				
				out.println("               var sFeatures = '';");
				out.println("               sFeatures += 'dialogHeight: ' + (parseInt(screen.availHeight) - 100) + 'px;';");
				out.println("               sFeatures += 'dialogWidth: ' + (parseInt(screen.availWidth) - 150) + 'px;';");
				// out.println("               sFeatures += 'status: no;';");
				
				out.println("               window.showModalDialog(");
				out.println("                   m_url,");
				out.println("                   payment_voucher,");
				out.println("                   sFeatures");
				out.println("               );");
				out.println("           }");
				
				
				out.println("           function payment_voucher() {");
				out.println("               var payment_voucher;");
				out.println("           }");
				
				
				out.println("           function get_vector_normal(http_response) {");
				out.println("               if (g_get_data_type == 1) {");
				out.println("                   document.getElementById('DIV_PAYMENTS').innerHTML = http_response;");
				out.println("               }");
				out.println("           }");
				
				
				// added by udara 24-07-2015
				out.println("function get_vector(data_vec) {");
				
				out.println("   if((g_get_data_type==3) && (data_vec.length>0)){ ");
				out.println("	    if(data_vec.length>0){");
				out.println("	        if(data_vec[0]=='Y'){");
				out.println("              alert('Cheque number is excisting'); ");
				out.println("              ext_obj.focus(); "); // added by udara 28-07-2015
				out.println("              ext_obj_chk.checked=false; ");
				out.println("           }");
				out.println("       }");
				out.println("   }");
				
				out.println("}");
				// end by udara 24-07-2015
				
				
				out.println("           function check_approve(row_id) {");
				out.println("               var check_status = document.getElementById('CHK_APPROVE_' + row_id).checked;");
				out.println("               var checked_count = parseInt(document.getElementById('hid_selected_checkbox_count').value);");
				
				out.println("               if (check_status == true) {");
				out.println("                   checked_count += 1;");
				out.println("               }");
				out.println("               else {");
				out.println("                   checked_count -= 1;");
				out.println("               }");
				out.println("               document.getElementById('hid_selected_checkbox_count').value = checked_count;");
				out.println("           }");
				
				
				
				out.println("function check_all_chkbox()");
				out.println("{ ");
				out.println("   check_true=false;    ");
				out.println("       if(document.Form1.CHECK_ALL.checked==true)");
				out.println("       { ");
				out.println("          check_true=true;");
				out.println("       }else  ");
				out.println("       { ");
				out.println("    	    check_true=false;");
				out.println("       } ");
				out.println("   for(m=1;m<=parseInt(document.Form1.hid_count.value);m++) ");
				out.println("   { ");
				out.println("        document.Form1.elements[\"CHK_APPROVE_\"+m].checked=check_true; ");
				out.println("        check_approve(m); ");	
				//out.println("        alert(document.Form1.elements[\"CHK_REQUIRED\"+m].value); ");
				out.println("   } ");	
				//out.println("        alert(document.Form1.elements[\"hid_selected_checkbox_count\"].value); ");
				out.println("} ");
				
				
				out.println("           function check_date_dis(row_id) {");
				out.println("               if((document.getElementById('TXT_DIS_DATE_DD_' + row_id).value != '') && (document.getElementById('TXT_DIS_DATE_MM_' + row_id).value != '') && (document.getElementById('TXT_DIS_DATE_YY_' + row_id).value != '')) {");
				out.println("                   checkMonthLength(document.getElementById('TXT_DIS_DATE_DD_' + row_id), document.getElementById('TXT_DIS_DATE_MM_' + row_id), document.getElementById('TXT_DIS_DATE_YY_' + row_id));");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function check_date(row_id) {");
				out.println("               var m_dis_date = document.getElementById('TXT_DIS_DATE_DD_' + row_id).value + '-' + document.getElementById('TXT_DIS_DATE_MM_' + row_id).value + '-' + document.getElementById('TXT_DIS_DATE_YY_' + row_id).value;");
				out.println("               var dis_date = new Date(m_dis_date);");
				out.println("               var sys_date = new Date(g_sysdate);");
				out.println("               if (dis_date > sys_date) {");
				out.println("                   alert('Disbursement Date cannot be greater than System Date!');");
				out.println("                   document.getElementById('TXT_DIS_DATE_DD_' + row_id).value = '" + m_sysdate_dd + "';");
				out.println("                   document.getElementById('TXT_DIS_DATE_MM_' + row_id).value = '" + m_sysdate_mm + "';");
				out.println("                   document.getElementById('TXT_DIS_DATE_YY_' + row_id).value = '" + m_sysdate_yy + "';");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function check_date_post(row_id) {");
				out.println("               if((document.getElementById('TXT_POST_DATE_DD_' + row_id).value != '') && (document.getElementById('TXT_POST_DATE_MM_' + row_id).value != '') && (document.getElementById('TXT_POST_DATE_YY_' + row_id).value != '')) {");
				out.println("                   checkMonthLength(document.getElementById('TXT_POST_DATE_DD_' + row_id), document.getElementById('TXT_POST_DATE_MM_' + row_id), document.getElementById('TXT_POST_DATE_YY_' + row_id));");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function load_calendar(type, row_id) {");
				out.println("               g_calendar_type = type;"); 
				out.println("               g_calendar_row_id = row_id;"); 
				out.println("               var popupwin = window.open(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'CO_Calendar_Window', 'oBj', 'left=450, top=200, width=320, height=230');");
				out.println("           }");
				
				
				out.println("           function load_c_date(val) {");
				out.println("               var v_dd = null;");
				out.println("               var v_mm = null;");
				out.println("               var v_yy = null;");
				
				out.println("               v_dd = val.substr(0, val.indexOf('-'));");
				out.println("               if (v_dd.length < 2) {");
				out.println("                   v_dd = 0 + v_dd;");
				out.println("               }");
				out.println("               val = val.substr(val.indexOf('-') + 1, val.length);");
				out.println("               v_mm = val.substr(0, val.indexOf('-'));");
				out.println("               if (v_mm.length < 2) {");
				out.println("                   v_mm = 0 + v_mm;");
				out.println("               }");
				out.println("               v_yy = val.substr(val.indexOf('-') + 1, val.length);");
				
				out.println("               if (g_calendar_type == '1') {");
				out.println("                   document.getElementById('TXT_DIS_DATE_DD_' + g_calendar_row_id).value = v_dd;");
				out.println("                   document.getElementById('TXT_DIS_DATE_MM_' + g_calendar_row_id).value = v_mm;");
				out.println("                   document.getElementById('TXT_DIS_DATE_YY_' + g_calendar_row_id).value = v_yy;");
				out.println("                   check_date(g_calendar_row_id);");
				out.println("               }");
				out.println("               else if (g_calendar_type == '2') {");
				out.println("                   document.getElementById('TXT_POST_DATE_DD_' + g_calendar_row_id).value = v_dd;");
				out.println("                   document.getElementById('TXT_POST_DATE_MM_' + g_calendar_row_id).value = v_mm;");
				out.println("                   document.getElementById('TXT_POST_DATE_YY_' + g_calendar_row_id).value = v_yy;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function save_window() {");
				out.println("               before_submit();");
				out.println("           }");
				
				
				out.println("           function before_submit() {");
				out.println("               if ((validate_data()) && (confirm('Are you sure you want to save?'))) {");
				out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Cheque_Disbursement_save';");
				out.println("                   document.Form1.submit();");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function validate_data() {");
				
				// added by udara 28-07-2015
				out.println("               var total_count = document.getElementById('hid_count').value;");
				out.println("               var new_count = 0;  ");
				out.println("               for (var i = 1; i <= total_count; i++) {");
				out.println("                  if(document.getElementById('CHK_APPROVE_' + i).checked==true){"); 
				out.println("                     new_count = new_count + 1; ");
				out.println("                  }");
				out.println("               }");
				out.println("               document.getElementById('hid_selected_checkbox_count').value = new_count;  ");
				// end by udara 28-07-2015
				
				
				out.println("               if (document.getElementById('hid_selected_checkbox_count').value == '0') {");
				out.println("                   alert('Please select at leaset one payment.');");
				out.println("                   return false;");
				out.println("               }");
				out.println("               else if (document.getElementById('hid_selected_checkbox_count').value != '0') {");
				out.println("                   var total_count = document.getElementById('hid_count').value;");
				out.println("                   for (var i = 1; i <= total_count; i++) {");
				out.println("                       if (document.getElementById('CHK_APPROVE_' + i).checked == true) {");
				out.println("                           if (document.getElementById('TXT_CHEQUE_NO_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_CHEQUE_NO_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_DIS_TO_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_DIS_TO_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_ID_NO_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_ID_NO_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_DIS_BY_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_DIS_BY_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_DIS_DATE_DD_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_DIS_DATE_DD_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_DIS_DATE_MM_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_DIS_DATE_MM_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_DIS_DATE_YY_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_DIS_DATE_YY_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_POST_DATE_DD_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_POST_DATE_DD_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_POST_DATE_MM_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_POST_DATE_MM_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                           else if (document.getElementById('TXT_POST_DATE_YY_' + i).value == '') {");
				out.println("                               alert('Please fill the relevant details.');");
				out.println("                               document.getElementById('TXT_POST_DATE_YY_' + i).focus();");
				out.println("                               return false;");
				out.println("                           }");
				out.println("                       }");
				out.println("                   }");
				out.println("               }");
				out.println("               return true;");
				out.println("           }");
				
				//=====Added by Prabash on 14-02-2012 ==========================================
				out.println("function payment_no_help(){");
				out.println("Crit=document.Form1.TXT_PAYMENT_NO.value+\"@\";");
				out.println("HelpBox('1','10','0',Crit,'Paymentno2','1');");
				out.println("}");
				out.println("function client_assign(oBj){");
				out.println(" document.Form1.TXT_PAYMENT_NO.value =oBj.valout[2]");
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
				
				out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_CO_Help_Servlet?class_in="+m_fschema_name+"AF_CR_Ter_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				
				out.println("	"); 
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99();"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"1\"){"); 
				out.println("		client_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		receipt_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"4\"){"); 
				out.println("		vehicle_assign(oBj);"); 
				out.println("		}");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		lease_assign(oBj);"); 
				out.println("		}");
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
				out.println("	}	"); 
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
				
				out.println("function check_client(val) {");
				out.println(" document.Form1.TXT_PAYMENT_NO.value = ''  ");
				out.println("}");	
				//=======================================================================	
				
				// added by udara 24-07-2015
				out.println("           var ext_obj;   ");
				out.println("           var ext_obj_chk;   ");
				
				out.println("   function cheque_no_validate(obj,row_id) {");
				
				//out.println("               alert('cheque_no_validate 1'); ");
				
				out.println("     if(obj.checked==true){ ");
				
				out.println("               var total_count = document.getElementById('hid_count').value;");
				out.println("               for (var i = 1; i <= total_count; i++) {");
				out.println("                  if(document.getElementById('TXT_CHEQUE_NO_' + i).name != document.getElementById('TXT_CHEQUE_NO_' + row_id).name ){"); 
				out.println("                    if((document.getElementById('TXT_CHEQUE_NO_' + i).value == document.getElementById('TXT_CHEQUE_NO_' + row_id).value) && (document.getElementById('TXT_CHEQUE_NO_' + i).value!='')){");
			    out.println("                      alert('Existing cheque number on this screen for ' + document.getElementById('HID_PAYMENT_NO_' + i).value); "); 
			    out.println("                    }"); 
				out.println("                  }");
				out.println("               }");
				
				//out.println("               alert('cheque_no_validate 2'); ");
				
				out.println("               var payment_no = document.getElementById('HID_PAYMENT_NO_' + row_id).value;   ");
				
				out.println("               ext_obj     =  document.getElementById('TXT_CHEQUE_NO_' + row_id);   ");
				out.println("               ext_obj_chk =  document.getElementById('CHK_APPROVE_' + row_id); ");
				
				out.println("               if(obj.value!=''){");
				
				//out.println("               alert('cheque_no_validate 3'); ");
				
				out.println("                   g_get_data_type = 3;");
				out.println("                   m_url = '" + m_class_url + "/" + m_fschema_name + "AF_MK_sql_validations?chksql=cheque_no_validate_disburse&cheque_no='+document.getElementById('TXT_CHEQUE_NO_' + row_id).value+'&payment_no='+payment_no;");
				out.println("                   load_interface(m_url, 'XML');");
				//out.println("                   window.open(m_url); ");
				out.println("               }");
				
				out.println("     }");
				
				out.println("  }");
				// end by udara 24-07-2015
				
				
				out.println("       </script>");
				out.println("   </head>");
				
				out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" >");
				out.println("       <form name=\"Form1\" method=\"post\">");
				out.println("           <input type=\"hidden\" name=\"SCREEN_NAME\" id=\"SCREEN_NAME\" value=\"NEW\" />");
				out.println("           <input type=\"hidden\" name=\"hid_help_type\" id=\"hid_help_type\" />");
				out.println("           <input type=\"hidden\" name=\"hid_status\" id=\"hid_status\" value=\"New\" />");
				out.println("           <input type=\"hidden\" name=\"hid_no\" id=\"hid_no\" />");
				out.println("           <input type=\"hidden\" name=\"hid_save\" id=\"hid_save\" value=\"Save\" />");
				out.println("           <input type=\"hidden\" name=\"hid_selected_checkbox_count\" id=\"hid_selected_checkbox_count\" value=\"0\" />");
				
				out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
				out.println("               <tr>");
				out.println("                   <td width=\"8\" valign=\"top\"></td>");
				out.println("                   <td class=\"border_wht\" valign=\"top\">");
				out.println("                       <table class=\"table\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
				out.println("                           <tr>");
				out.println("                               <td height=\"30\" class=\"pdn_mainHD\">Asset Financing System</td>");
				out.println("                           </tr>");
				out.println("                           <tr>");
				out.println("                               <td height=\"1\"></td>");
				out.println("                           </tr>");
				out.println("                           <tr>");
				out.println("                               <td>");
				out.println("                                   <table class=\"table\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">");
				out.println("                                       <tr>");
				out.println("                                           <td height=\"1\"></td>");
				out.println("                                       </tr>");
				out.println("                                       <tr>");
				out.println("                                           <td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">" + m_screen_title + "</td>");
				out.println("                                       </tr>");
				out.println("                                       <tr>");
				out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
				out.println("                                               <table class=\"table\" cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
				out.println("                                                   <tr>");
				out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('New');\" onclick=\"new_window();\" name=\"bt_new\" value=\"New\" /></td>");
				out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Save');\" onclick=\"save_window();\" name=\"bt_save\" value=\"Save\" /></td>");
				out.println("                                                       <td width=\"100%\"></td>");
				out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Help');\" onclick=\"load_help_msg();\" value=\"Help\" /></td>");
				out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Cancel');\" onclick=\"clear_window();\" value=\"Cancel\" /></td>");
				out.println("                                                       <td><input type=\"button\" class=\"mainbut\" onmouseout=\"load_roll_out_value();\" onmouseover=\"load_roll_value('Close');\" onclick=\"close_window();\" name=\"back\" value=\"Close\" /></td>");
				out.println("                                                   </tr>");
				out.println("                                               </table>");
				
				
				//=======added by Prabash on 14-02-2012=====================
				out.println("<table align='center' width='100%' class='table' border='0'>"); 
				out.println("<tr class=tr_input>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_PAYMENT_NO'  class=div_input>Payment No </DIV></td>"); 
				out.println("<td><input class='txt_input' type='text' name='TXT_PAYMENT_NO' maxlength='15' style=\"{width:250px;}\" size='15' >");
				out.println("<input type=button name=cli_help value=... class=\"but_input\" onclick=\"payment_no_help()\">");
				out.println("<input class='but_input' type='button' name='BUT_TXT_PAYMENT_NO_SEARCH' value=\"Search\" onClick=\"load_payments();\"></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				//=========================================================
				
				
				
				
				out.println("                                           </td>");
				out.println("                                       </tr>");
				out.println("                                       <tr>");
				out.println("                                           <td class=\"line\" height=\"1\"></td>");
				out.println("                                       </tr>");
				
				out.println("                                       <tr class=\"pdn_txtpos\" valign=\"top\">");
				out.println("                                           <td>");
				out.println("                                               <br />");
				out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
				out.println("                                           </td>");
				out.println("                                       </tr>");
				out.println("                                   </table>");
				out.println("                               </td>");
				out.println("                           </tr>");
				out.println("                       </table>");
				out.println("                   </td>");
				out.println("               </tr>");
				out.println("           </table>");
				out.println("       </form>");
				out.println("   </body>");
				out.println("</html>");
				
			}
			
			else if (m_chksql.equals("load_data")) {
				m_paymno      = httpServletRequest.getParameter("paymno"); //Added by Prabash on 14-02-2012
				
				sql = " " +
					"   SELECT B.GROUP_PAYMENT_NO PAYMENT_NO, " +
					"          NVL(B.PAYEE_NAME, '-') PAYEE_NAME, " +
					"          SUM(A.SETTELED_AMOUNT) PAY_AMOUNT, " +
					"          B.CHEQUE_NO, " +
					"          'VENDOR' PAYMENT_TYPE " +
					"   FROM   " + m_schema_name + ".AF_CR_PRO_SET_PAY_BREAKDOWN A, " +
					"          " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT B " +
					"   WHERE  A.PAYMENT_NO = B.PAYMENT_NO " +
					"   AND    B.GROUP_PAYMENT_NO LIKE '%"+m_paymno+"%'  "+ 
					"   AND    B.PROCESS_STATUS = 'PRINT' " +
					"   AND    A.ENTRY_TYPE = 'V' " +
					"   AND    B.GROUP_PAYMENT_NO IS NOT NULL " +
					"   GROUP BY B.GROUP_PAYMENT_NO, " +
					"            B.PAYEE_NAME, " +
					"            B.SETTLE_MODE, " +
					"            B.CHEQUE_NO " +
					
					"   UNION ALL " +
					
					"   SELECT A.PAYMENT_NO, " +
					"          NVL(A.PAYEE_NAME, '-') PAYEE_NAME, " +
					"          A.PAY_AMOUNT, " +
					"          A.CHEQUE_NO, " +
					"          'OTHER' PAYMENT_TYPE " +
					"   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
					"   WHERE  A.PROCESS_STATUS = 'PRINT' " +
					"   AND    A.PAYMENT_NO LIKE '%"+m_paymno+"%'  "+ 
					"   AND    A.GROUP_PAYMENT_NO IS NULL " +
					" ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				j = 0;
				
				out.println("<table class=\"table\" align=\"left\" border=\"0\">");
				
				while (resultSet.next()) {
					
					j++;
					
					if (j == 1) {
						out.println("   <tr class=\"pdn_txtpos2\">");
						out.println("       <td style=\"text-align: center\">No.</td>");
						out.println("       <td style=\"text-align: center\">Payment No.</td>");
						out.println("       <td style=\"text-align: center\">Receiver</td>");
						out.println("       <td style=\"text-align: center\">Amount</td>");
						
						out.println("       <td style=\"text-align: center\">Cheque No.</td>");
						out.println("       <td style=\"text-align: center\">Disbursed To</td>");
						out.println("       <td style=\"text-align: center\">ID No.</td>");
						out.println("       <td style=\"text-align: center\">By</td>");
						out.println("       <td style=\"text-align: center\">Disburse Date</td>"); //added span tag mcp 2012-02-18
						out.println("       <td style=\"text-align: center\">Posted Date</td>"); //added span tag mcp 2012-02-18
						
						// out.println("       <td style=\"text-align: center\">Type</td>"); 
						// out.println("       <td style=\"text-align: center\">User</td>");
						out.println("       <td style=\"text-align: center\">Approved <INPUT TYPE=\"checkbox\" NAME=\"CHECK_ALL\" VALUE=\"on\" onclick=\"check_all_chkbox()\"  > </td>");
						out.println("       <td style=\"text-align: center\">Details</td>");
						out.println("   </tr>");
					}
					
					if (j % 2 == 1) {
						out.println("   <tr class=\"tr_input\">");
					}
					else {
						out.println("   <tr class=\"tr_input1\">");
					}
					
					out.println("       <td style=\"text-align: right;\">" + j + ".</td>");
					out.println("       <td style=\"text-align: center;\">" + resultSet.getString("PAYMENT_NO") + "<input type=\"hidden\" name=\"HID_PAYMENT_NO_" + j + "\" id=\"HID_PAYMENT_NO_" + j + "\" value=\"" + resultSet.getString("PAYMENT_NO") + "\" /></td>");
					out.println("       <td style=\"text-align: left;\">" + resultSet.getString("PAYEE_NAME") + "</td>");
					out.println("       <td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
					
					out.println("       <td style=\"text-align: center\"><input class=\"txt_input\" type=\"text\" style=\"width: 80px;\" maxlength=\"10\" name=\"TXT_CHEQUE_NO_" + j + "\" id=\"TXT_CHEQUE_NO_" + j + "\" value=\"" + resultSet.getString("CHEQUE_NO") + "\" /></td>");
					out.println("       <td style=\"text-align: center\"><input class=\"txt_input\" type=\"text\" style=\"width: 100px;\" maxlength=\"250\" name=\"TXT_DIS_TO_" + j + "\" id=\"TXT_DIS_TO_" + j + "\" /></td>");
					out.println("       <td style=\"text-align: center\"><input class=\"txt_input\" type=\"text\" style=\"width: 70px;\" maxlength=\"10\" name=\"TXT_ID_NO_" + j + "\" id=\"TXT_ID_NO_" + j + "\" /></td>");
					out.println("       <td style=\"text-align: center\"><input class=\"txt_input\" type=\"text\" style=\"width: 60px;\" maxlength=\"10\" name=\"TXT_DIS_BY_" + j + "\" id=\"TXT_DIS_BY_" + j + "\" /></td>");
					out.println("       <td style=\"text-align:\"> <div style=\"width:148px\" > </div> ");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 20px;\" name=\"TXT_DIS_DATE_DD_" + j + "\" id=\"TXT_DIS_DATE_DD_" + j + "\" maxlength=\"2\" value=\"" + m_sysdate_dd + "\" onblur=\"check_date_dis('" + j + "'); check_date('" + j + "');\" />");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 20px;\" name=\"TXT_DIS_DATE_MM_" + j + "\" id=\"TXT_DIS_DATE_MM_" + j + "\" maxlength=\"2\" value=\"" + m_sysdate_mm + "\" onblur=\"check_date_dis('" + j + "'); check_date('" + j + "');\" />");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 30px;\" name=\"TXT_DIS_DATE_YY_" + j + "\" id=\"TXT_DIS_DATE_YY_" + j + "\" maxlength=\"4\" value=\"" + m_sysdate_yy + "\" onblur=\"check_date_dis('" + j + "'); check_date('" + j + "');\" />");
					out.println("           <a style=\"cursor: pointer; text-decoration: underline;\" onclick=\"load_calendar('1', '" + j + "');\">Calendar</a>");
					out.println("       </td>");
					out.println("       <td style=\"text-align:\"> <div style=\"width:148px\" > </div> ");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 20px;\" name=\"TXT_POST_DATE_DD_" + j + "\" id=\"TXT_POST_DATE_DD_" + j + "\" maxlength=\"2\" value=\"" + m_sysdate_dd + "\" onblur=\"check_date_post('" + j + "');\" />");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 20px;\" name=\"TXT_POST_DATE_MM_" + j + "\" id=\"TXT_POST_DATE_MM_" + j + "\" maxlength=\"2\" value=\"" + m_sysdate_mm + "\" onblur=\"check_date_post('" + j + "');\" />");
					out.println("           <input class=\"txt_input5\" type=\"text\" style=\"width: 30px;\" name=\"TXT_POST_DATE_YY_" + j + "\" id=\"TXT_POST_DATE_YY_" + j + "\" maxlength=\"4\" value=\"" + m_sysdate_yy + "\" onblur=\"check_date_post('" + j + "');\" />");
					out.println("           <a style=\"cursor: pointer; text-decoration: underline;\" onclick=\"load_calendar('2', '" + j + "');\">Calendar</a>");
					out.println("       </td>");
					
					// out.println("       <td style=\"text-align: center;\">" + resultSet.getString("SETTLE_MODE") + "</td>");
					// out.println("       <td style=\"text-align: center;\">" + m_username + "</td>");
					out.println("       <td style=\"text-align: center;\"><input type=\"checkbox\" name=\"CHK_APPROVE_" + j + "\" id=\"CHK_APPROVE_" + j + "\" value=\"A\" onclick=\"check_approve('" + j + "'); cheque_no_validate(this,'"+j+"');\"  /></td>");
					out.println("       <td style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_" + j + "\" id=\"BUT_DETAILS_" + j + "\" value=\"Details\" onclick=\"show_details('" + j + "', '" + resultSet.getString("PAYMENT_TYPE") + "');\" /></td>");
					out.println("   </tr>");
					
				}
				resultSet.close();
				statement.close();
				
				out.println("</table>");
				out.println("<input type=\"hidden\" name=\"hid_count\" id=\"hid_count\" value=\"" + j + "\" />");
				
			}
			
			else {
				out.println("idle");
			}
			
		}
		
		catch (Exception exception) {
			
			try {
				out.println("Error : " + exception.toString());
			}
			catch(Exception e) {}
			
			exception.printStackTrace();
			
		}
		
		finally {
			
			if (resultSet != null) {
				try {
					resultSet.close();
				}
				catch(Exception e) {}
			}
			if (statement != null) {
				try {
					statement.close();
				}
				catch(Exception e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				}
				catch(Exception e) {}
			}
			if (out != null) {
				try {
					out.close();
				}
				catch(Exception e) {}
			}
			
		}
		
	}
	
}
