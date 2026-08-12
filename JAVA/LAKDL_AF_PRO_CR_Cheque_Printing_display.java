/*
 * SCREEN NAME  : Credit Process - Cheque Print
 * CREATED BY   : Samitha Kulatilaka
 * DATE / TIME  : 2011-09-14
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

public class LAKDL_AF_PRO_CR_Cheque_Printing_display extends HttpServlet {
	
	public synchronized void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		
		LAKDL_AF_CO_conn_methods m_sn_methods = null;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = null;
		
		ServletOutputStream out = null;
		
		NumberFormat numberFormat = null;
		
		String m_chksql = null;
		String m_chksql1 = null;
		String m_sql = null;
		String m_sort_column = null;
		String m_order_by_type = null;
		String m_screen_type = null;
		String m_status_edit = null;
		String m_status_new = null;
		
		String m_schema_name = null;
		String m_fschema_name = null;
		String m_html_client_url = null;
		String m_class_url = null;
		String m_username = null;
		
		String vendor_code = null;
		String process_status = null;
		String bank_account_no = null;
		String group_payment_no = null;
		
		String process_status_get = null;
		String process_status_post = null;
		
		int j = 0;
		
		try {
			
			m_sn_methods = new LAKDL_AF_CO_conn_methods();
			connection = m_sn_methods.met_user_validate(httpServletRequest);
			
			m_schema_name       = m_sn_methods.schema_name;
			m_fschema_name      = m_sn_methods.client_name;
			m_html_client_url   = m_sn_methods.html_client_url;
			m_class_url         = m_sn_methods.servlet_client_url + ":" + m_sn_methods.client_t3_port;
			m_username          = m_sn_methods.username;
			statement           = connection.createStatement();
			
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.setContentType("text/html");
			out = httpServletResponse.getOutputStream();
			
			
			numberFormat = NumberFormat.getInstance(Locale.US);
			numberFormat.setMinimumFractionDigits(2);
			numberFormat.setMaximumFractionDigits(2);
			
			// if(m_chksql1.equals("B")){
			// // m_level="approval_main";
			// // m_screen_name1="approval_main";
			// // m_screen_name1 = "Requisition - Processing";
			// m_status_new="RE_APP";
			// m_status_edit="RE_A_2";
			
			// if(m_screen_type.equals("NEW")){
			// process_status="RE-APP";
			// // m_new_status="Y";
			
			// }
			// //if(m_screen_type.equals("EDIT")){
			// if(m_screen_type.equals("REVERSE")){
			// process_status="RE_A_2";
			// // m_new_status="RE_A_2";
			
			// }
			// }
			
			/*else if(m_chksql1.equals("A")){
			m_level="Approval_2";
			m_screen_name1="Approval 2";

			m_status_new="APPRO1";
			m_status_edit="APPRO2";
			if(m_screen_type.equals("NEW")){
			process_status="APPRO1";
			m_new_status="APPRO1";

			}
			if(m_screen_type.equals("EDIT")){
			process_status="APPRO2";
			m_new_status="APPRO2";
			}
			}
			*/
			
			
			m_chksql = httpServletRequest.getParameter("chksql");
			
			process_status_get = "APPRO2";
			
			
			if (m_chksql.equals("main_page")) {
				
				// m_sort_column   = "APPLICATION_NO";
				// m_order_by_type = "DESC";
				
				// if ((httpServletRequest.getParameter("sort_column") != null) && (httpServletRequest.getParameter("order_by_type") != null)) {
				// m_sort_column = httpServletRequest.getParameter("sort_column");
				// m_order_by_type = httpServletRequest.getParameter("order_by_type");
				// }
				
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
				out.println("<html>");
				out.println("   <head>");
				out.println("       <title>Finance - Cheque Printing</title>");
				
				out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url+"/css/Asset_Financing_System.css\">");
				
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/leasing_drill_down.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
				out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
				
				out.println("       <script type=\"text/javascript\">");
				
				out.println("           var g_help_type = 0;");
				out.println("           var g_get_data_type = 0;");
				
				// out.println("var st_val='';");
				// out.println("var st_val1='';");
				// out.println("st_val='"+m_chksql+"'");//VERIFY
				// out.println("st_val1='"+m_chksql1+"'");//B
				// out.println("var m_order_by_type");
				// out.println("var row_arry=new Array();");
				// out.println("var chk_chng=0");
				// out.println("var m_row=''");
				
				
				out.println("function get_vector(data_vec) {");
				// commented by udara 24-07-2015
				/*
				out.println("	 if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("alert('Temporary Invoice exsits')");
				out.println("document.Form1.elements[\"BUT_VIEW_\"+m_row].disabled=true");
				out.println("			}");
				out.println("if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("		if(confirm(\"Are you sure you want to generate Payment Requisition?\")){ "); 
				out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_req_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+m_row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+m_row].value+'&CURR_CODE='+document.Form1.elements[\"TXT_CURR_CODE_\"+m_row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+m_row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+m_row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+m_row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+m_row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+m_row].value+'&chksql1="+m_chksql1+"';"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}"); 
				out.println("			}");
				*/
				
				//out.println("   alert('get_vector'); ");
				
				//out.println("   alert(g_get_data_type+'  '+data_vec.length); ");
				
				// added by udara 24-07-2015
				out.println("   if((g_get_data_type==3) && (data_vec.length>0)){ ");
				out.println("	    if(data_vec.length>0){");
				out.println("	        if(data_vec[0]=='Y'){");
				out.println("              alert('Cheque number is excisting'); ");
				out.println("              ext_obj.focus(); "); // added by udara 28-07-2015
				out.println("           }");
				out.println("       }");
				out.println("   }");
				// end by udara 24-07-2015
				
				out.println("}");
				
				
				// out.println("function before_submit(){ "); 
				// out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
				// out.println("  chk=\"CHK_APP_\"+d;");
				// out.println("if(document.Form1.elements[chk].checked==false){"); 
				// out.println("chk_chng=0");
				// out.println("} ");
				// out.println("if(document.Form1.elements[chk].checked==true){"); 
				// out.println("chk_chng=1");
				// out.println("break");
				// out.println("} ");
				// out.println("		}");
				// out.println("		if(chk_chng==1){");
				// out.println("		if(confirm(\"Are you sure you want to delete?\")){ "); 
				// out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				// out.println("document.Form1.elements[i].disabled=false;");
				// out.println("}");
				// //out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_details1?screen_type="+m_screen_type+"&level="+m_level+"&number='+document.Form1.hid_count.value+'&status="+m_status+"&chksql="+m_chksql+"&chksql1="+m_chksql1+"';");   
				// out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Save_Payment_Reverse';");   
				// out.println("		document.Form1.submit();	"); 
				// out.println("		}"); 
				// out.println("} ");
				// out.println("else{");
				// out.println("alert('Please select a Application No')");
				// out.println("}");
				// out.println("} ");
				
				
				out.println("           function clear_window() {");
				out.println("               if (confirm('Are you sure you want to clear the screen?')) {");
				out.println("                   window.location.href = window.location.href;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function new_window() {");
				out.println("               window.location.href = window.location.href;");
				out.println("           }");
				
				
				out.println("function edit_window(){	"); 
				//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=REVERSE&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
				out.println("}"); 
				
				
				// out.println("function load_help_msg() {");
				// if(m_chksql1.equals("B")){
				// out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_1\";"); 
				// }
				// else if(m_chksql1.equals("A")){
				// out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
				// }
				// else if(m_chksql1.equals("R")){
				// out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_req\";"); 
				// }
				
				// out.println("    HelpBox_msg(m_help_message);"); 
				// out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				
				out.println("           function load_roll_value(m_val) {");
				out.println("               document.getElementById('help_box').innerHTML = 'Finance - Cheque Printing - ' + m_val;");
				out.println("           }");
				
				
				out.println("           function load_roll_out_value() {");
				out.println("               document.getElementById('help_box').innerHTML = 'Finance - Cheque Printing - ' + document.getElementById('hid_status').value;");
				out.println("           }");
				
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}");
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val==\"REVERSE\"){"); //Added BY Sandun on 19-01-2009
				out.println("edit_window();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("}"); 
				out.println("else{");
				out.println("edit_window();"); 
				out.println("}");
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("document.Form1.hid_save.value=\"save\";");
				out.println("}else if(m_val==\"EDIT\"){");
				out.println("document.Form1.hid_status.value=\"Delete\";");
				out.println("document.Form1.hid_save.value=\"Delete\";");
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("}else if(m_val==\"DACT\"){");  
				out.println("document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("}else if(m_val==\"RACT\"){");  
				out.println("document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("}else{");  
				out.println("document.Form1.hid_status.value=\"\";");  
				out.println("}"); 
				out.println("}"); 
				
				
				out.println("           function MyDialog() {");
				out.println("               this.valout = new Array(10);");
				out.println("           }");
				
				
				out.println("           function HelpBox(Start, End, Hid_No, Max) {");
				out.println("               oBj = new MyDialog();"); 
				out.println("               oBj.valout[1] = \" \";");
				out.println("               oBj.valout[2] = \" \";");
				out.println("               oBj.valout[3] = \" \";");
				
				out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println(" if(g_help_type == 1){"); 
				out.println("		help_value_assign_1(oBj);"); 
				out.println("}");
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
				out.println("clear_data()");
				out.println("	}");
				out.println("}");
				out.println("if(oBj.valout[2]==' '){");
				out.println("clear_data()");
				out.println("	}	");
				out.println("}"); 
				
				out.println("           function Prev(Start, End, Hid_No) {");
				out.println("               HelpBox(Start, End, Hid_No);");
				out.println("           }");
				
				
				out.println("           function Next(Start, End, Hid_No) {");
				out.println("               HelpBox(Start, End, Hid_No);");
				out.println("           }");
				
				
				out.println("           function help_button(row) {");
				
				/*if(m_level.equals("Requisition_Approval")){
                out.println("check_invoice(row)");
                }
                
                if(m_level.equals("approval_main")){
                out.println("		if(confirm(\"Are you sure you want to select account for this this Payment?\")){ "); 
                out.println("		if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)==\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&chksql1="+m_chksql1+"';"); 
                out.println("}");
                
                out.println("else	if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)!=\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}");
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                }
                if(m_level.equals("Approval_2")){
                out.println("		if(confirm(\"Are you sure you want to approve this Payment?\")){ "); 
                out.println("if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)==\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_app2_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}"); 		
                out.println("else	if((document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)!=\"PI\"){");
                out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_pay_repos2_details?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&ENT_TYPE='+(document.Form1.elements[\"TXT_REF_NO_\"+row].value).substring(0,2)+'&chksql1="+m_chksql1+"';"); 
                out.println("}"); 		
                out.println(" window.location.href=m_url;"); 
                out.println("}"); 
                }
                */
				out.println("		if(confirm(\"Are you sure you want to select account for this this Payment?\")){ "); 
				//out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&chksql1="+m_chksql1+"';"); 
				out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Account_Selection?TOT_SET='+document.Form1.elements[\"TXT_TOT_SETTLE_AMT_\"+row].value+'&INIT_BAL='+document.Form1.elements[\"TXT_INIT_BAL_AMT_\"+row].value+'&PAID_AMT='+document.Form1.elements[\"TXT_PAID_\"+row].value+'&PAY_NO='+document.Form1.elements[\"TXT_PAYMENT_NO_\"+row].value+'&CURR_CODE='+document.Form1.elements[\"TXT_CURR_CODE_\"+row].value+'&APP_NO='+document.Form1.elements[\"TXT_APP_NO_\"+row].value+'&BAL='+document.Form1.elements[\"TXT_BAL_TO_BE_PAID_\"+row].value+'&VALUE_DATE='+document.Form1.elements[\"TXT_VALUE_DATE_\"+row].value+'&chksql1="+m_chksql1+"';"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}"); 
				
				out.println("}");
				
				
				
				// out.println("           function sort_data(m_sort_col) {");
				// out.println("               m_order_by_type = 'DESC';");
				// out.println("               if (m_sort_col == '" + m_sort_column + "') {");
				// out.println("                   if ('" + m_order_by_type + "' == 'DESC') {");
				// out.println("                       m_order_by_type = 'ASC';");
				// out.println("                   }");
				// out.println("                   else {");
				// out.println("                       m_order_by_type = 'DESC'; ");
				// out.println("                   }");
				// out.println("               }");
				// out.println("               else {");
				// out.println("                   m_order_by_type = 'ASC'; ");
				// out.println("               }");
				
				// if (m_screen_type.equals("NEW")) {
				// // out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				// out.println("m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Payment_Req_Main_Screen_2?screen_type=NEW&sql=load_data_client&chksql=" + m_chksql + "&chksql2=" + m_chksql1 + "&m_client_name=' + document.getElementById('hid_m_client_name').value + '&sort_column=' + m_sort_col + '&order_by_type=' + m_order_by_type;");
				// }
				// else if (m_screen_type.equals("EDIT")) {
				// //out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&status_edit="+m_status_edit+"&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				// out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				// //out.println(" window.location.href=m_url;"); 
				// }
				// else if (m_screen_type.equals("REVERSE")) {
				// out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
				// }
				
				// out.println("               load_interface(m_url, 'NORM');");
				// out.println("           }");
				
				
				out.println("           function help_button_1() {");
				out.println("               g_help_type = 1;");
				out.println("               m_sql = 'm_help_TXT_ACCOUNT_2_sql';");
				out.println("               m_criteria = document.getElementById('TXT_ACC_NO').value + '@Y@';");
				out.println("               HelpBox('1', '10', '0');");
				out.println("           }");
				
				
				out.println("           function help_value_assign_1(oBj) {"); 
				out.println("               document.getElementById('TXT_ACC_NO').value = oBj.valout[2];");
				out.println("               document.getElementById('TXT_BRANCH_CODE').value = oBj.valout[3];");
				out.println("               document.getElementById('TXT_BRANCH_NAME').value = oBj.valout[4];");
				out.println("               load_payments(document.getElementById('TXT_ACC_NO').value);");
				out.println("           }");
				
				
				out.println("           function clear_data() {");
				out.println("               if (g_help_type == 1) {");
				out.println("                   document.getElementById('TXT_ACC_NO').value = '';");
				out.println("                   document.getElementById('TXT_BRANCH_CODE').value = '';");
				out.println("                   document.getElementById('TXT_BRANCH_NAME').value = '';");
				out.println("                   document.getElementById('DIV_PAYMENTS').innerHTML = '';");
				out.println("                   document.getElementById('hid_selected_checkbox_count').value = '0';");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function load_payments(bank_account_no) {");
				out.println("               g_get_data_type = 1;");
				out.println("               m_url = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Cheque_Printing_display?chksql=load_data&bank_account_no=' + bank_account_no;");
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
				out.println("                   document.getElementById('hid_selected_checkbox_count').value = '0';");
				out.println("               }");
				out.println("           }");
				
				
				out.println(" function change_acc_payee(obj){");
				out.println("	if(obj.checked==true){");
				out.println("		obj.value = \"Y\" ");
				out.println("	}else{");
				out.println("		obj.value = \"N\" ");
				
				out.println("}");
				//out.println("alert(obj.value)"); 
				out.println("}");
				
				out.println("           function check_approve(row_id) {");
				out.println("               var check_status = document.getElementById('CHK_APPROVE_' + row_id).checked;");
				out.println("               var checked_count = parseInt(document.getElementById('hid_selected_checkbox_count').value);");
				
				out.println("               if (check_status == true) {");
				out.println("                   checked_count += 1;");
				out.println("                   document.getElementById('hid_selected_checkbox_count').value = checked_count;");
				out.println("               }");
				out.println("               else {");
				out.println("                   checked_count -= 1;");
				out.println("                   document.getElementById('hid_selected_checkbox_count').value = checked_count;");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function save_window() {");
				out.println("               before_submit();");
				out.println("           }");
				
				
				out.println("           function before_submit() {");
				out.println("               if ((validate_data()) && (confirm('Are you sure you want to save?'))) {");
				out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_PRO_CR_Cheque_Printing_save';");
				out.println("                   document.Form1.submit();");
				out.println("               }");
				out.println("           }");
				
				
				out.println("           function validate_data() {");
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
				out.println("                       }");
				out.println("                   }");
				out.println("               }");
				out.println("               return true;");
				out.println("           }");
				
				// added by udara 24-07-2015
				out.println("           var ext_obj;   ");
				
				out.println("           function cheque_no_validate(obj,row_id) {");
				out.println("               var total_count = document.getElementById('hid_count').value;");
				out.println("               for (var i = 1; i <= total_count; i++) {");
				out.println("                  if(document.getElementById('TXT_CHEQUE_NO_' + i).name != obj.name ){");
				out.println("                    if((document.getElementById('TXT_CHEQUE_NO_' + i).value == obj.value) && (document.getElementById('TXT_CHEQUE_NO_' + i).value!='')){");
			    out.println("                      alert('Existing cheque number on this screen for ' + document.getElementById('HID_PAYMENT_NO_' + i).value); "); 
			    out.println("                      obj.focus(); "); // added by udara 09-08-2016
				out.println("                    }"); 
				out.println("                  }");
				out.println("               }");
				
				//out.println("               alert('cheque_no_validate 1');");
				out.println("               var payment_no = document.getElementById('HID_PAYMENT_NO_' + row_id).value;   ");
				
				out.println("               ext_obj =  obj;   ");
				
				out.println("               if(obj.value!=''){");
				//out.println("               alert('cheque_no_validate 2');");
				out.println("                   g_get_data_type = 3;");
				out.println("                   m_url = '" + m_class_url + "/" + m_fschema_name + "AF_MK_sql_validations?chksql=cheque_no_validate&cheque_no='+obj.value+'&payment_no='+payment_no;");
				out.println("                   load_interface(m_url, 'XML');");
				//out.println("                   window.open(m_url); ");
				out.println("               }");
				
				out.println("           }");
				// end by udara 24-07-2015
				
				out.println("       </script>");
				out.println("   </head>");
				
				out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\">");
				out.println("       <form name=\"Form1\" method=\"post\" action=\"\">");
				out.println("           <input type=\"hidden\" name=\"SCREEN_NAME\" id=\"SCREEN_NAME\" value=\"NEW\">");
				out.println("           <input type=\"hidden\" name=\"hid_status\" id=\"hid_status\" value=\"New\">");
				out.println("           <input type=\"hidden\" name=\"hid_no\" id=\"hid_no\" value=\"\">");
				out.println("           <input type=\"hidden\" name=\"hid_save\" id=\"hid_save\" value=\"Save\">");
				out.println("           <input type=\"hidden\" name=\"Hid_scr_name\" id=\"Hid_scr_name\" value=\"AF_CR_PRO_PAYMENT_REQ_MAIN\">");
				out.println("           <input type=\"hidden\" name=\"hid_selected_checkbox_count\" id=\"hid_selected_checkbox_count\" value=\"0\">");
				
				out.println("           <table width=\"100%\" class=\"table\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
				out.println("               <tr>"); 
				// out.println("                   <td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("                   <td width='8' valign='top'></td>");
				out.println("                   <td class='border_wht' valign='top'> "); 
				out.println("                       <table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("                           <tr> "); 
				out.println("                               <td height='30' class='pdn_mainHD'>Asset Financing System</td>"); 
				out.println("                           </tr>"); 
				out.println("                           <tr> "); 
				// out.println("                               <td height='1'><img src='spacer.gif' width='1' height='1'></td>");
				out.println("                               <td height='1'></td>");
				out.println("                           </tr>"); 
				out.println("                           <tr>"); 
				// out.println("                               <td style='height: 327px'>"); 
				out.println("                               <td>");
				out.println("                                   <table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>");
				out.println("                                       <tr>"); 
				// out.println("                                           <td height='1'><img height='1' src='spacer.gif' width='1'></td>");
				out.println("                                           <td height='1'></td>");
				out.println("                                       </tr>"); 
				out.println("                                       <tr>"); 
				out.println("                                           <td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Cheque Printing - New</td>"); 
				out.println("                                       </tr>"); 
				out.println("                                       <tr>"); 
				out.println("                                           <td  height='10px' class='pdn_txtpos'>"); 
				out.println("                                               <table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("                                                   <tr><td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");
				// out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onClick='load_screen_status(\"REVERSE\")' name=\"bt_rev\" value=\"Reverse\"></td>");  
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window();' name=\"bt_save\" value=\"Save\"></td>");
				// out.println("<td width='10%' align='center'><input name =\"save\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("                                                       <td width='100%'></td>");  
				/*if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                */
				// if(m_screen_type.equals("REVERSE") ){
				// out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
				// }
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("                                                       <td><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("                                                       <td><input type=button name=back value=\"Close\" class=mainbut onclick=\"close_window();\" onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
				// out.println("                                                       <td width='*%' align='right' class='div_input'></td>");  
				out.println("                                                   </tr>");  
				out.println("                                               </table>");  
				out.println("                                           </td>");  
				out.println("                                       </tr>");  
				out.println("                                       <tr>");  
				// out.println("                                           <td class='line' height='1'><img height='1' src='spacer.gif' width='1'></td>");
				out.println("                                           <td class='line' height='1'></td>");
				out.println("                                       </tr>");  
				out.println("                                       <tr>");  
				out.println("                                           <td class='pdn_txtpos' valign='top'>");
				out.println("                                               <br>");
				
				out.println("                                               <table align=\"left\" class=\"table\">");
				out.println("                                                   <tr class=\"tr_input\">");
				out.println("                                                       <td style=\"width: 200px;\"><div class=\"div_input\" id=\"DIV_TXT_ACC_NO\">Account No.</div></td>");
				out.println("                                                       <td style=\"width: 300px;\">");
				out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_ACC_NO\" id=\"TXT_ACC_NO\" style=\"width: 90px;\" maxlength=\"20\" size=\"10\" onblur=\"help_button_1();\">");
				out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_TXT_ACC_NO\" id=\"BUT_TXT_ACC_NO\" value=\"...\" onclick=\"help_button_1();\">");
				out.println("                                                       </td>");
				out.println("                                                   </tr>");
				
				out.println("                                                   <tr class=\"tr_input\">");
				out.println("                                                       <td><div class=\"div_input\" id=\"DIV_BRANCH_CODE\">Branch Code</div></td>");
				out.println("                                                       <td>");
				out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_BRANCH_CODE\" id=\"TXT_BRANCH_CODE\" style=\"width: 150px;\" disabled=\"disabled\">");
				out.println("                                                       </td>");
				out.println("                                                   </tr>");
				
				out.println("                                                   <tr class=\"tr_input\">");
				out.println("                                                       <td><div class=\"div_input\" id=\"DIV_BRANCH_NAME\">Branch Name</div></td>");
				out.println("                                                       <td>");
				out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_BRANCH_NAME\" id=\"TXT_BRANCH_NAME\" style=\"width: 250px;\" disabled=\"disabled\">"); // check_branch(this)
				out.println("                                                       </td>");
				out.println("                                                   </tr>");
				//PRINTER PART
				out.println("                                                   <tr class=\"tr_input\">");
				out.println("                                                       <td><div class=\"div_input\" id=\"DIV_PRINTER_NAME\">Printer Name</div></td>");
				out.println("                                                       <td>");
				out.println("<select name=\"TXT_PRINTER\" class=\"txt_input\" style=\"width:180px;\" >");
				
				resultSet = statement.executeQuery(	"  SELECT "+
					"  PRINTER,PRINTER_PATH "+					
					"  FROM "+m_schema_name+".REF_USER_PRINTER "+
					//"  WHERE DIVISION_CODE='AF' "+
					"  WHERE USER_NAME='"+m_username+"' and sel_type='Y' ");
				
				boolean more = resultSet.next();
				while(more){
					out.println("<OPTION value=\""+resultSet.getString(2)+"\">"+resultSet.getString(1)+"</option>");
					more = resultSet.next();	
				}	
				out.println("</SELECT>");
				
				out.println("                                                       </td>");
				out.println("                                                   </tr>");
				out.println("                                               </table>");
				
				out.println("                                           </td>");
				out.println("                                       </tr>");
				
				out.println("                                       <tr class='pdn_txtpos' valign='top'>");
				out.println("                                           <td>");
				out.println("                                               <br>");
				out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
				out.println("                                           </td>");
				out.println("                                       </tr>");
				
				// out.println("                                       <tr class='pdn_txtpos' valign='top'>");
				// out.println("                                           <td>");
				// out.println("                                               <br>");
				
				// out.println("                                               <div id=\"DIV_PAYMENTS\"></div>");
				// // out.println("                                               <table width='100%' class=\"table\">");
				// out.println("                                               <table class=\"table\">");
				// out.println("                                                   <tr>");  
				// // out.println("                                                       <td width=\"100%\"><div id=\"DIV_PAYMENTS\"></div></td>");
				// out.println("                                                       <td><div id=\"DIV_PAYMENTS\"></div></td>");
				// out.println("                                                   </tr>"); 
				// out.println("                                               </table>"); 
				
				
				/*out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
                out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");  
                out.println("<td width='6%'></td>");  
                if(m_screen_type.equals("NEW")){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
                }
                if(m_screen_type.equals("EDIT") ){
                out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
                }
                
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
                out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
                out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=\"close_window();\" onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
                out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
                out.println("</table>"); 
                */
				
				// out.println("                                           </td>");
				// out.println("                                       </tr>");
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
				
				// if ((httpServletRequest.getParameter("sort_column") != null) && (httpServletRequest.getParameter("order_by_type") != null)) {
				// m_sort_column = httpServletRequest.getParameter("sort_column");
				// m_order_by_type = httpServletRequest.getParameter("order_by_type");
				// }
				
				bank_account_no = httpServletRequest.getParameter("bank_account_no");
				
				sql = " " +
					"   SELECT B.GROUP_PAYMENT_NO PAYMENT_NO, " +
					"          NVL(B.PAYEE_NAME, '-') PAYEE_NAME, " +
					"          SUM(A.SETTELED_AMOUNT) PAY_AMOUNT, " +
					"          DECODE(B.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash', '-') SETTLE_MODE, " +
					"          'VENDOR' PAYMENT_TYPE " +
					"   FROM   " + m_schema_name + ".AF_CR_PRO_SET_PAY_BREAKDOWN A, " +
					"          " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT B " +
					"   WHERE  A.PAYMENT_NO = B.PAYMENT_NO " +
					"   AND    B.PROCESS_STATUS = '" + process_status_get + "' " +
					"   AND    B.LIC_ACC_NO = '" + bank_account_no + "' " +
					"   AND    A.ENTRY_TYPE = 'V' " +
					"   AND    B.GROUP_PAYMENT_NO IS NOT NULL " +
					"   GROUP BY B.GROUP_PAYMENT_NO, " +
					"            B.PAYEE_NAME, " +
					"            B.SETTLE_MODE " +
					
					"   UNION ALL " +
					
					"   SELECT A.PAYMENT_NO, " +
					"          NVL(A.PAYEE_NAME, '-') PAYEE_NAME, " +
					"          A.PAY_AMOUNT, " +
					"          DECODE(A.SETTLE_MODE, 'CHQ', 'Cheque', 'Cash', '-') SETTLE_MODE, " +
					"          'OTHER' PAYMENT_TYPE " +
					"   FROM   " + m_schema_name + ".AF_RE_PRO_SETTLMENT_PAYMENT A " +
					"   WHERE  A.PROCESS_STATUS = '" + process_status_get + "' " +
					"   AND    A.LIC_ACC_NO = '" + bank_account_no + "' " +
					"   AND    A.GROUP_PAYMENT_NO IS NULL " +
					// "   ORDER BY " + m_sort_column + " " + m_order_by_type + " " +
					" ";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				j = 0;
				
				out.println("<table class=\"table\" align=\"left\" border=\"0\">");
				
				while (resultSet.next()) {
					
					j++;
					
					if (j == 1) {
						out.println("<tr class=\"pdn_txtpos2\">");
						out.println("<td width=\"2%\" style=\"text-align: center;\">No.</td>");
						out.println("<td width=\"10%\" style=\"text-align: center;\">Payment No.</td>");
						out.println("<td width=\"9%\" style=\"text-align: center;\">Receiver</td>");
						out.println("<td width=\"9%\" style=\"text-align: center;\">Amount</td>"); 
						out.println("<td width=\"9%\" style=\"text-align: center;\">Cheque No.</td>"); 
						out.println("<td width=\"11%\" style=\"text-align: center;\">Settlement Mode</td>"); 
						out.println("<td width=\"3%\" style=\"text-align: center;\">User</td>");
						out.println("<td width=\"4%\" style=\"text-align: center;\">Approved</td>");
						out.println("<td width=\"4%\" style=\"text-align: center;\">A/C Payee Only</td>");
						out.println("<td width=\"4%\" style=\"text-align: center;\">Details</td>");
						out.println("</tr>");
					}
					
					if (j % 2 == 1) {
						out.println("<tr class=tr_input>");
					}
					else {
						out.println("<tr class=tr_input1>");
					}
					
					out.println("<td style=\"text-align: right;\">" + j + ".</td>");
					out.println("<td style=\"text-align: center;\"><div>" + resultSet.getString("PAYMENT_NO") + "</div><input type=\"hidden\" name=\"HID_PAYMENT_NO_" + j + "\" id=\"HID_PAYMENT_NO_" + j + "\" value=\"" + resultSet.getString("PAYMENT_NO") + "\" /></td>");
					out.println("<td style=\"text-align: left;\">" + resultSet.getString("PAYEE_NAME") + "</td>");
					out.println("<td style=\"text-align: right;\">" + numberFormat.format(resultSet.getBigDecimal("PAY_AMOUNT")) + "</td>");
					//out.println("<td style=\"text-align: center;\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CHEQUE_NO_"+j+"\" id=\"TXT_CHEQUE_NO_"+j+"\" /></td>"); // commented by udara 24-07-2015
					out.println("<td style=\"text-align: center;\"><input class=\"txt_input\" type=\"text\" name=\"TXT_CHEQUE_NO_"+j+"\" id=\"TXT_CHEQUE_NO_"+j+"\" onblur=\"cheque_no_validate(this,'"+j+"');\" /></td>"); // added by udara 24-07-2015
					out.println("<td style=\"text-align: center;\">" + resultSet.getString("SETTLE_MODE") + "</td>");
					out.println("<td style=\"text-align: center;\">" + m_username + "</td>");
					out.println("<td style=\"text-align: center;\"><input type=\"checkbox\" name=\"CHK_APPROVE_"+j+"\" id=\"CHK_APPROVE_"+j+"\" value=\"A\" onclick=\"check_approve('" + j + "');\" /></td>");
					//out.println("<td style=\"text-align: center;\"><input type=\"checkbox\" name=\"chkbox_acc_payee"+j+"\" id=\"chkbox_acc_payee"+j+"\" value=\"N\" onclick=\"change_acc_payee(this);\" /></td>");
					out.println("<td style=\"text-align: center;\"><input type=\"checkbox\" name=\"chkbox_acc_payee"+j+"\" id=\"chkbox_acc_payee"+j+"\"   onclick=\"change_acc_payee(this);\" /></td>");
					out.println("<td style=\"text-align: center;\"><input class=\"but_input\" type=\"button\" name=\"BUT_DETAILS_"+j+"\" id=\"BUT_DETAILS_"+j+"\" value=\"Details\" onclick=\"show_details('" + j + "', '" + resultSet.getString("PAYMENT_TYPE") + "');\" /></td>");
					
				}
				resultSet.close();
				statement.close();
				
				out.println("</table>");
				out.println("<input type=\"hidden\" name=\"hid_count\" id=\"hid_count\" value=\"" + j + "\" />");
				
			}
			
			/*
			// added by udara 24-07-2015
			else if (m_chksql.equals("cheque_no_validate")){
				
					String m_cheque_no = httpServletRequest.getParameter("cheque_no");
					String m_value_str = "N";
					int m_value = 0;
				
					resultSet = statement.executeQuery (" "+
							" SELECT COUNT(PAYMENT_NO) "+
							" FROM "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT  "+
							" WHERE CHEQUE_NO = '"+m_cheque_no+"'  "+
						" ");
				
					if(resultSet.next()){
						m_value = resultSet.getInt(1);
					}
				
					if(m_value>=0)
						m_value_str = "Y";
					else
						m_value_str = "N";

				    out.print("<DATA>");
					out.print("<ITEM>");
					out.print("<R1>"+m_value_str+"</R1>");
					out.print("</ITEM>");
				    out.print("</DATA>");
				
				
			}
			// end by udara 24-07-2015
			*/
			
			else {
				out.println("idle");
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error : "+ex.toString());}catch(Exception e){}
			ex.printStackTrace();
		}
		finally{
			if(resultSet    !=null){try{resultSet.close();   }catch(Exception e){}}
			
			if(statement  !=null){try{statement.close(); }catch(Exception e){}}
			
			if(connection  !=null){try{connection.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
		
	}
	
}
