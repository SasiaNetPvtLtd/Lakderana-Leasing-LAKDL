//--
//SCREEN NAME:Credit Process - Main screen payment new
//CREATED BY :delanjli
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_Payment_Req_Main_Screen extends javax.servlet.http.HttpServlet { 
	Connection conn;
	ServletOutputStream out = null;
	java.text.NumberFormat nf;
	public ResultSet rs,rs1;
	Statement stmt,stmt1;
	public String m_chksql;
	public String m_chksql1;
	public String m_sql;
	String m_sort_column  ;	
	String m_order_by_type;
	String m_screen_type;
	String m_status;
	String m_status_edit;
	String m_status_new;
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
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			
			String m_schema_name = m_sn_methods.schema_name;

			String m_level="";
			String m_screen_name1="";

			String m_username =  m_sn_methods.username;

		  m_sql = req.getParameter("sql");
		  m_chksql = req.getParameter("chksql");
		  m_chksql1 = req.getParameter("chksql2");
			m_screen_type= req.getParameter("screen_type");
			m_status_new= req.getParameter("status_new");
			m_status_edit= req.getParameter("status_edit");
			if(m_screen_type==null){
			m_screen_type="NEW";
			}
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
		  nf.setMinimumFractionDigits(0);
		  nf.setMaximumFractionDigits(0);
			String m_new_status="";

		  if(m_chksql1.equals("B")){
			m_level="approval_main";
			m_screen_name1="approval_main";
			m_status_new="RE_APP";
			m_status_edit="RE_A_2";
			
			if(m_screen_type.equals("NEW")){
			m_status="RE-APP";
			m_new_status="Y";

			}
			//if(m_screen_type.equals("EDIT")){
			if(m_screen_type.equals("REVERSE")){
			m_status="RE_A_2";
			m_new_status="RE_A_2";

			}
			}
			
			/*else if(m_chksql1.equals("A")){
			m_level="Approval_2";
			m_screen_name1="Approval 2";

			m_status_new="APPRO1";
			m_status_edit="APPRO2";
			if(m_screen_type.equals("NEW")){
			m_status="APPRO1";
			m_new_status="APPRO1";

			}
			if(m_screen_type.equals("EDIT")){
			m_status="APPRO2";
			m_new_status="APPRO2";
			}
			}
			*/
			
			
			if (m_chksql.trim().equals("idle")) {
			out.println("idle");
			}

			else if(m_sql.trim().equals("main_page")){

		  m_sort_column   = "APPLICATION_NO";	
		  m_order_by_type = "DESC";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			//}

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Finance - Payment "+m_screen_name1+"</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var st_val='';");
			out.println("var st_val1='';");
			out.println("st_val='"+m_chksql+"'");//VERIFY
			out.println("st_val1='"+m_chksql1+"'");//B
			out.println("var m_order_by_type");
			out.println("var row_arry=new Array();");
			out.println("var chk_chng=0");
			out.println("var m_row=''");



			out.println("function get_vector(data_vec) {");
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
			out.println("}");

			out.println("function check_invoice(row) {");
			out.println("m_row=row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_settle_temp&sus_ref_no=\"+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+\"&ref_no=\"+document.Form1.elements[\"TXT_REF_NO_\"+row].value+\"\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");



			out.println("function before_submit(){ "); 
			out.println("for(var d=0;d<document.Form1.hid_count.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("if(document.Form1.elements[chk].checked==false){"); 
			out.println("chk_chng=0");
			out.println("} ");
			out.println("if(document.Form1.elements[chk].checked==true){"); 
			out.println("chk_chng=1");
			out.println("break");
			out.println("} ");
			out.println("		}");
			out.println("		if(chk_chng==1){");
			out.println("		if(confirm(\"Are you sure you want to delete?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			//out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_details1?screen_type="+m_screen_type+"&level="+m_level+"&number='+document.Form1.hid_count.value+'&status="+m_status+"&chksql="+m_chksql+"&chksql1="+m_chksql1+"';");   
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Save_Payment_Reverse';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("else{");
			out.println("alert('Please select a Application No')");
			out.println("}");
			out.println("} ");
			

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type="+m_screen_type+"&sql=main_page&status_new="+m_status_new+"&chksql='+st_val+'&chksql2='+st_val1+'';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=main_page&status_new="+m_status_new+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
			out.println("}"); 

			out.println("function edit_window(){	"); 
			//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=REVERSE&sql=main_page&status_edit="+m_status_edit+"&chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
			out.println("}"); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {");
			if(m_chksql1.equals("B")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_1\";"); 
			}
			else if(m_chksql1.equals("A")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_2\";"); 
			}
			else if(m_chksql1.equals("R")){
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_req\";"); 
			}
			
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Finance - Payment Requsition Payment Enter Main - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance - Payment Requsition Payment Enter Main - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 


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
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
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
			out.println(""); 
			
			out.println("function help_button(row) {"); 
			
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
			

		
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'DESC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			if(m_screen_type.equals("NEW")){
			
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&m_client_name=\"+document.Form1.hid_m_client_name.value+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			//out.println(" window.location.href=m_url;"); 
			}
			if(m_screen_type.equals("EDIT")){

			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=EDIT&status_edit="+m_status_edit+"&sql=main_page&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			//out.println(" window.location.href=m_url;"); 
			}
			
			if(m_screen_type.equals("REVERSE")){			
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			}
			
			out.println("load_interface(m_url,'NORM');");

			out.println("}");
			
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
			
			
			out.println("function search_client_details(m_client_name) {"); 
			//out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?sql=load_data_client&screen_type="+m_screen_type+"&status_new="+m_status_new+"&m_client_name=\"+m_client_name+\"&chksql=\"+st_val+\"&chksql2=\"+st_val1;"); 
    //	out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type=NEW&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&m_client_name=\"+m_client_name;");
			//out.println("window.open(m_url);");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Payment_Req_Main_Screen?screen_type="+m_screen_type+"&sql=load_data_client&chksql="+m_chksql+"&chksql2="+m_chksql1+"&m_client_name=\"+m_client_name;");//Mod By Sandun on 19-01-2009
			
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function check_change(row) {"); //Added By Sandun on 19-01-2009
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
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_PAYMENT_REQ_MAIN\">"); 		
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance - Payment Requsition Payment Enter Main</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\")' name=\"bt_dele\" value=\"Delete\"></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onClick='load_screen_status(\"REVERSE\")' name=\"bt_rev\" value=\"Reverse\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%'></td>");  
			/*if(m_screen_type.equals("NEW")){
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
			}
			if(m_screen_type.equals("EDIT") ){
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
			}
			*/
			if(m_screen_type.equals("REVERSE") ){
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
			}
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>"); 
			
			
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_NAME'  class=div_input>Client  / Receiver  Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='200' style=\"{width:250px;}\" size='15' >");  //onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_APPLICATION_NO)\"
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_NAME_SEARCH' value=\"Search\" onClick=\"search_client_details(document.Form1.TXT_CLIENT_NAME.value)\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
		  out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>"); 

			
			//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			/*out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_top     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_bot);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
			*/
			
		/*	if(m_level.equals("Requisition_Approval") && m_screen_type.equals("NEW")){

			rs= stmt.executeQuery
														("SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+
														"nvl(C.FINANCE_NO,'-') FINANCE_NO, "+
														"C.TOTAL_FINANCE_AMOUNT TOTAL_FINANCE_AMOUNT , "+
														"C.CLIENT_CODE , "+
														""+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
														"B.SUS_REF_NO SUS_REF_NO,  "+
														"B.REF_NO REF_NO,  "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+		
														""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
												    "B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT "+
														"FROM  "+
														""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D "+
														"WHERE  "+
														//"UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
														" B.REF_NO=D.INVOICE_NO "+
														"AND D.APPLICATION_NO=C.APPLICATION_NO "+
														"AND B.BAL_TO_BE_PAID >0 "+
														"AND D.ACTIVE_STATUS<>'C' "+ //NOT CANCELLED
														"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

		}
			else if (m_level.equals("Requisition_Approval") && m_screen_type.equals("EDIT")){

		rs= stmt.executeQuery
		
	//	out.println
														("SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+
														"C.FINANCE_NO FINANCE_NO, "+
														"C.TOTAL_FINANCE_AMOUNT TOTAL_FINANCE_AMOUNT , "+
														"C.CLIENT_CODE , "+
														""+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
														"B.SUS_REF_NO SUS_REF_NO,  "+
														"B.REF_NO REF_NO,  "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+		
														""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"E.PAY_AMOUNT PAID_AMT, "+
														"E.PAYMENT_NO PAYMENT_NO, "+
														"E.LIC_BRANCH_CODE, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"E.LIC_ACC_NO, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
												    "B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT, "+
														" nvl(decode(B.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Ad','R','Repos'),'-') SUS_ENTRY_TYPE   "+
														"FROM  "+
														""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
														""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
														"WHERE "+
														//"UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
														"B.REF_NO=D.INVOICE_NO "+
														"AND D.APPLICATION_NO=C.APPLICATION_NO "+
														//"AND B.BAL_TO_BE_PAID >0 "+
													  "AND B.SUS_REF_NO=E.SUS_REF_NO "+
														"AND E.PROCESS_STATUS='"+m_status+"' "+ 
													  "AND D.ACTIVE_STATUS<>'C' "+ //NOT CANCELLED
														"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
													
										


			}
			
			
		else {
		
		*/
		
		
	  //________________________________________________comment on 18-01-2008_____________________________________________________	
		/*rs= stmt.executeQuery
		
														("SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+
														"NVL(C.FINANCE_NO,'-') FINANCE_NO, "+
														"C.TOTAL_FINANCE_AMOUNT TOTAL_FINANCE_AMOUNT , "+
														"C.CLIENT_CODE , "+
														""+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
														"B.SUS_REF_NO SUS_REF_NO,  "+
														"upper(B.REF_NO) REF_NO,  "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+		
														""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"E.PAY_AMOUNT PAID_AMT, "+
														"E.PAYMENT_NO PAYMENT_NO, "+
														"E.LIC_BRANCH_CODE, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"E.LIC_ACC_NO, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
												    "B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT, "+
														" nvl(decode(B.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Ad','R','Repos'),'-') SUS_ENTRY_TYPE   "+
														"FROM  "+
														""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
														""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
														"WHERE "+
														//"UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
														"B.REF_NO=D.INVOICE_NO "+
														"AND D.APPLICATION_NO=C.APPLICATION_NO "+
														//"AND B.BAL_TO_BE_PAID >0 "+
													  "AND B.SUS_REF_NO=E.SUS_REF_NO "+
														"AND E.PROCESS_STATUS='"+m_status+"' "+ 
													  "AND D.ACTIVE_STATUS<>'C' "+ //NOT CANCELLED
														
														
														
					//----MODIFIED BY : DELANJALI------------------------------------------------------------------------------------
					//----DATE				: 2007-06-14-----------------------------------------------------------------------------------
					//---------------------------------------------------------------------------------------------------------------
														
		
														"UNION "+

														"SELECT DD.APP, "+
														""+m_schema_name+".AF_CO_GET_FINANCE_NO(DD.APP), "+
														"0, "+
														""+m_schema_name+".af_co_get_client_CODE(DD.APP), "+ 
														""+m_schema_name+".AF_CO_GET_APP_NAME(DD.APP), "+
														"DD.SUS_REF_NO,DD.REF_NO, "+
														"DD.BAL_TO_BE_PAID, "+
														"DD.value_date, "+
														"DD.RECEIVER, "+
														"DD.VENDOR_NAME, "+
														"DD.PAID_AMT, DD.PAYMENT_NO, "+
														"DD.LIC_BRANCH_CODE, "+
														"DD.CURR_CODE, "+
														"DD.LIC_ACC_NO, "+
														"DD.TOT_SETTLE_AMOUNT, "+
														"DD.INT_BAL_SETTLE_AMOUNT, "+
														"DD.SUS_ENTRY_TYPE "+
														"FROM (SELECT DISTINCT "+m_schema_name+".AF_CO_GET_FIN_NO(B.REF_NO) APP,  "+
														"B.SUS_REF_NO SUS_REF_NO, "+
														"B.REF_NO REF_NO, "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+
														"B.RECEIVER VENDOR_NAME, "+//to match for above query
														//""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"E.PAY_AMOUNT PAID_AMT, E.PAYMENT_NO PAYMENT_NO, "+
														"E.LIC_BRANCH_CODE LIC_BRANCH_CODE, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"E.LIC_ACC_NO LIC_ACC_NO, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
														"B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT, "+
														" nvl(decode(B.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Ad','R','Repos'),'-') SUS_ENTRY_TYPE   "+
														"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E "+
														"WHERE  B.SUS_REF_NO=E.SUS_REF_NO  "+
														"AND E.PROCESS_STATUS='"+m_new_status+"'  "+
														"AND b.SUSPENSE_ENTRY_TYPE in ('E','L','A')) DD  "+
														"WHERE DD.APP IS NOT NULL "+
														"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
														
	//__________________________________end of comment on 18-01-2008______________________________________________________________________
																			
		//	}											
//----------------------------------------------------------------------------------------------------------------									


			
			
			/*boolean more=rs.next();
			int j=0;
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">"); 
		
			while(more){
									
	
			if(j==0){

			out.println("<tr class=\"pdn_txtpos2\">");
			
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Application No  \" onclick=sort_data(\"APPLICATION_NO\")>Application No</td>"); 
			if(m_screen_type.equals("NEW")){
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by -  Finance No  \" onclick=sort_data(\"FINANCE_NO\")>Finance No</td>"); 
			}
			if(!m_level.equals("Requisition_Approval") ||  m_screen_type.equals("EDIT")){
			out.println("<td width=\"10%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by -  Payment No  \" onclick=sort_data(\"PAYMENT_NO\")>Payment No</td>"); 
			}
			
			out.println("<td width=\"10%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Suspense Reference No  \" onclick=sort_data(\"SUS_REF_NO\")>Sus Ref. No</td>"); 
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Invoice No  \" onclick=sort_data(\"REF_NO\")>Reference No</td>"); 
			out.println("<td width=\"14%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Client Name \" onclick=sort_data(\"NAME\")>Client Name</td>"); 
			out.println("<td width=\"8%\" align=\"left\" 	style=cursor:hand; title=\"Click here to sort by - Value Date  \" onclick=sort_data(\"VALUE_DATE\")>Value Date</td>"); 
		  if(m_level.equals("Requisition_Approval")){
			out.println("<td width=\"9%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Vendor  \" onclick=sort_data(\"VENDOR_NAME\")>Vendor</td>"); 
		 }
			
		  if(!m_level.equals("Requisition_Approval")){
			out.println("<td width=\"9%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Vendor  \" onclick=sort_data(\"VENDOR_NAME\")>Receiver</td>"); 
		 }
			
			if(m_level.equals("Requisition_Approval") && m_screen_type.equals("NEW")){
			out.println("<td width=\"9%\" align=\"right\" 	style=cursor:hand; title=\"Click here to sort by - Blanace to be paid  \" onclick=sort_data(\"BAL_TO_BE_PAID\")>Balance</td>"); 
			}
			
			
			else{
			out.println("<td width=\"9%\" align=\"right\" 	style=cursor:hand; title=\"Click here to sort by - Paid Amount  \" onclick=sort_data(\"PAID_AMT\")>Paid Amount</td>"); 
			}
			if(!m_level.equals("Requisition_Approval")){

			out.println("<td width=\"4%\" align=\"center\">Type</td>"); 

			out.println("<td width=\"3%\" align=\"left\">User</td>");
			if(m_screen_type.equals("EDIT") ){
			out.println("<td width=\"4%\" align=\"center\">&nbsp</td>"); 
			}
			if(m_screen_type.equals("NEW")){
			out.println("<td width=\"3%\" align=\"center\">&nbsp</td>");
			}
			}
			if(m_level.equals("Requisition_Approval")){


			out.println("<td width=\"4%\" align=\"left\">User</td>");
			if(m_screen_type.equals("EDIT") ){
			out.println("<td width=\"4%\" align=\"center\">&nbsp</td>"); 
			}
			if(m_screen_type.equals("NEW")){
			out.println("<td width=\"4%\" align=\"center\">&nbsp</td>");
			}
			}

			out.println("</tr>");

			}
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{									
      out.println("<tr class=tr_input >");
			}
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_APP_NO_"+j+" value=\""+rs.getString(1)+"\"></td>"); 
			if(m_screen_type.equals("NEW")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(2)+"')\"><U>"+rs.getString(2)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\"></td>"); 
			}
			if(!m_level.equals("Requisition_Approval") ||  m_screen_type.equals("EDIT")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(13)+"')\"><U>"+rs.getString(13)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(13)+"\"></td>"); 
			}
		
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_sus_payment_drill('"+rs.getString(6)+"')\"><U>"+rs.getString(6)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_SUS_REF_NO_"+j+" value=\""+rs.getString(6)+"\"></td>"); 
			
			if(rs.getString(7).substring(0,2).equals("PI")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_proforma_invoice_drill('"+rs.getString(7)+"')\"><U>"+rs.getString(7)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
			}
					
			if(rs.getString(7).substring(0,2).equals("AD")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_advertistment_drill('"+rs.getString(7)+"')\"><U>"+rs.getString(7)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
			}
			if(rs.getString(7).substring(0,2).equals("LN")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_legal_drill('"+rs.getString(7)+"')\"><U>"+rs.getString(7)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
			}
			if(rs.getString(7).substring(0,2).equals("RP")){
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_repossession_drill('"+rs.getString(7)+"')\"><U>"+rs.getString(7)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_REF_NO_"+j+" value=\""+rs.getString(7)+"\"></td>"); 
			}
	
			
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(4)+"')\"><U>"+rs.getString(5)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_CLIENT_CODE_"+j+" value=\""+rs.getString(4)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_CLIENT_NAME_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(9)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_VALUE_DATE_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
			if(rs.getString(7).substring(0,2).equals("PI")){
		
			out.println("<td  align=\"left\"  style= \"cursor:hand;cursor-color:blue\" onclick=\"show_vendor_drill('"+rs.getString(10)+"')\"><U>"+rs.getString(11)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_RECEIVER_"+j+" value=\""+rs.getString(10)+"\"></td>"); 
			}
			if(!rs.getString(7).substring(0,2).equals("PI")){
		
			out.println("<td  align=\"left\" >"+rs.getString(11)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_RECEIVER_"+j+" value=\""+rs.getString(10)+"\"></td>"); 
			}
		
			
			if(m_level.equals("Requisition_Approval") && m_screen_type.equals("NEW")){
			out.println("<td  align=\"right\" >"+nf.format(rs.getDouble(8))+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_BAL_TO_BE_PAID_"+j+" value=\""+rs.getString(8)+"\"></td>"); 
	
			}
	
			else{
			out.println("<td  align=\"right\" >"+nf.format(rs.getDouble(12))+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAID_"+j+" value=\""+rs.getString(12)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BAL_TO_BE_PAID_"+j+" value=\""+rs.getString(8)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BRANCH_"+j+" value=\""+rs.getString(14)+"\"></td>"); 

			}
			if(!m_level.equals("Requisition_Approval")){

			out.println("<td  align=\"left\" >"+rs.getString(19)+"<input type=\"hidden\" name=TXT_ENT_TYPE_"+j+" value=\""+rs.getString(19)+"\"></td>"); 
			}
			out.println("<td  align=\"left\" >"+m_username.toLowerCase()+"</td>"); 
			if(m_screen_type.equals("EDIT") ){

			out.println("<td align=\"center\" ><input type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\"></td>");
			}
			if(m_level.equals("Requisition_Approval")&& m_screen_type.equals("NEW")){
			out.println("<td align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 50px\" name=BUT_VIEW_"+j+" value=\"Generate\" onClick=\"help_button("+j+")\"></td>"); 
			}
			if (!m_level.equals("Requisition_Approval")&& m_screen_type.equals("NEW")){
			out.println("<td align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 45px\" name=BUT_VIEW_"+j+" value=\"Approve\" onClick=\"help_button("+j+")\"></td>"); 
			}
			out.println("</tr>");
			
			if(m_level.equals("Requisition_Approval")&& m_screen_type.equals("NEW")){
			out.println("<input type=\"hidden\" name=TXT_CURR_CODE_"+j+" value=\""+rs.getString(12)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_ACC_NO_"+j+" value=\"\">"); 
			out.println("<input type=\"hidden\" name=TXT_TOT_SETTLE_AMT_"+j+" value=\""+rs.getDouble(13)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_INIT_BAL_AMT_"+j+" value=\""+rs.getDouble(14)+"\">"); 
	
			
			}
			else{
			out.println("<input type=\"hidden\" name=TXT_CURR_CODE_"+j+" value=\""+rs.getString(15)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_ACC_NO_"+j+" value=\""+rs.getString(16)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_TOT_SETTLE_AMT_"+j+" value=\""+rs.getDouble(17)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_INIT_BAL_AMT_"+j+" value=\""+rs.getDouble(18)+"\">"); 
			}
		

			more=rs.next();
			j=j+1;
			}
		
			out.println("<input type=\"hidden\" name=hid_count value="+j+"></td>");
			out.println("</table>");
			out.println("<BR>");
			out.println("<HR>");
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>");

			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_bot     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_top);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
      */

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
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>"); 
			*/
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			else if(m_sql.trim().equals("load_data_client")){
						
			String  _m_client_name="";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			if	(req.getParameter("m_client_name")==null){
			 _m_client_name="";
			}
			_m_client_name=req.getParameter("m_client_name").trim();
			
			//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_top     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_bot);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
			//out.println(m_status);
			/*rs= stmt.executeQuery
														("SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+
														"NVL(C.FINANCE_NO,'-') FINANCE_NO, "+
														"C.TOTAL_FINANCE_AMOUNT TOTAL_FINANCE_AMOUNT , "+
														"C.CLIENT_CODE , "+
														""+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE) NAME, "+
														"B.SUS_REF_NO SUS_REF_NO,  "+
														"upper(B.REF_NO) REF_NO,  "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+		
														//""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"NVL(E.PAYEE_NAME,'-') VENDOR_NAME ,"+
														"E.PAY_AMOUNT PAID_AMT, "+
														"E.PAYMENT_NO PAYMENT_NO, "+
														"E.LIC_BRANCH_CODE, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"E.LIC_ACC_NO, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
												    "B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT, "+
														" nvl(decode(B.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Ad','R','Repos'),'-') SUS_ENTRY_TYPE   "+
														"FROM  "+
														""+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
														""+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D, "+
														""+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
														"WHERE "+
														//"UPPER(C.APPLICATION_STATUS) =UPPER('ACTIVATED') "+
														"B.REF_NO=D.INVOICE_NO "+
														"AND D.APPLICATION_NO=C.APPLICATION_NO "+
														//"AND B.BAL_TO_BE_PAID >0 "+
													  "AND B.SUS_REF_NO=E.SUS_REF_NO "+
														"AND E.PROCESS_STATUS='"+m_status+"' "+ 
														" AND( UPPER(E.PAYEE_NAME) LIKE UPPER('%"+_m_client_name+"%')"+ //"+m_schema_name+".af_co_get_vendor_name(B.RECEIVER)
														" OR UPPER("+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE)) LIKE UPPER('%"+_m_client_name+"%') ) "+
													  "AND D.ACTIVE_STATUS<>'C' "+ //NOT CANCELLED
					//----MODIFIED BY : DELANJALI------------------------------------------------------------------------------------
					//----DATE				: 2007-06-14-----------------------------------------------------------------------------------
					//---------------------------------------------------------------------------------------------------------------
														"UNION "+
														"SELECT DD.APP, "+
														""+m_schema_name+".AF_CO_GET_FINANCE_NO(DD.APP), "+
														"0, "+
														""+m_schema_name+".af_co_get_client_CODE(DD.APP), "+ 
														""+m_schema_name+".AF_CO_GET_APP_NAME(DD.APP), "+
														"DD.SUS_REF_NO,DD.REF_NO, "+
														"DD.BAL_TO_BE_PAID, "+
														"DD.value_date, "+
														"DD.RECEIVER, "+
														"DD.VENDOR_NAME, "+
														"DD.PAID_AMT, DD.PAYMENT_NO, "+
														"DD.LIC_BRANCH_CODE, "+
														"DD.CURR_CODE, "+
														"DD.LIC_ACC_NO, "+
														"DD.TOT_SETTLE_AMOUNT, "+
														"DD.INT_BAL_SETTLE_AMOUNT, "+
														"DD.SUS_ENTRY_TYPE "+
														"FROM (SELECT DISTINCT "+m_schema_name+".AF_CO_GET_FIN_NO(B.REF_NO) APP,  "+
														"B.SUS_REF_NO SUS_REF_NO, "+
														"B.REF_NO REF_NO, "+
														"B.BAL_TO_BE_PAID BAL_TO_BE_PAID, "+
														"to_char(b.value_date,'dd-mm-yyyy') value_date, "+
														"B.RECEIVER RECEIVER, "+
														//"B.RECEIVER VENDOR_NAME, "+//to match for above query
														"NVL(E.PAYEE_NAME,'-') VENDOR_NAME ,"+
														//""+m_schema_name+".af_co_get_vendor_name(B.RECEIVER) VENDOR_NAME, "+
														"E.PAY_AMOUNT PAID_AMT, E.PAYMENT_NO PAYMENT_NO, "+
														"E.LIC_BRANCH_CODE LIC_BRANCH_CODE, "+
														"B.CURR_CODE AS CURR_CODE, "+
														"E.LIC_ACC_NO LIC_ACC_NO, "+
														"B.TOT_SETTLE_AMOUNT TOT_SETTLE_AMOUNT, "+
														"B.INT_BAL_SETTLE_AMOUNT INT_BAL_SETTLE_AMOUNT, "+
														" nvl(decode(B.SUSPENSE_ENTRY_TYPE,'S','Supplier','V','Vendor','E','Seizer','L','Lawyer','A','Ad','R','Repos'),'-') SUS_ENTRY_TYPE   "+
														"FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B,"+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E "+
														"WHERE  B.SUS_REF_NO=E.SUS_REF_NO  "+
														"AND E.PROCESS_STATUS='"+m_new_status+"'  "+
														"AND b.SUSPENSE_ENTRY_TYPE in ('E','L','A')) DD  "+
														"WHERE DD.APP IS NOT NULL "+
														" AND( UPPER(DD.RECEIVER) LIKE UPPER('%"+_m_client_name+"%')"+
														" OR UPPER(DD.VENDOR_NAME) LIKE UPPER('%"+_m_client_name+"%') ) "+
														"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				*/
								
				  rs= stmt.executeQuery			
       		(" SELECT  "+
					" NVL("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO),'-') APPLICATION_NO, "+
					" NVL(FINANCE_NO,'-') , "+
					" A.PAYMENT_NO,  "+
					" TO_CHAR(EFF_VAL_DATE,'DD-MM-YYYY'),  "+
					" NVL(PAYEE_NAME,'-') ,"+
					//" SUM(SETTELED_AMOUNT),  "+
					" SUM(C.TOT_SETTLE_AMOUNT), "+
          " SUM(C.INT_BAL_SETTLE_AMOUNT), "+
          " SUM(C.BAL_TO_BE_PAID), "+
					//" SUM(PAY_AMOUNT), "+ ///9
					" SUM(A.SETTELED_AMOUNT), "+//Sandun on 11-03-2009
					" DECODE(SETTLE_MODE,'CHQ','Cheque','Cash') SETTLE_MODE,  "+
					" DECODE(A.ENTRY_TYPE,'V','Vendor','-') ENTRY_TYPE,  "+
					// --B.CLIENT_CODE,
					//--LAKDL.AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE) 
					" B.PROCESS_STATUS "+
					" FROM "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN A, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT B  "+
					" , "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT C "+
					" WHERE A.PAYMENT_NO=B.PAYMENT_NO  "+
					"  AND A.SUS_REF_NO=C.SUS_REF_NO "+
					" AND B.PROCESS_STATUS='"+m_status+"' "+//RE-APP  //Mod By Sandun on 19-01-2009
					" AND A.ENTRY_TYPE='V' "+
					//" AND UPPER("+m_schema_name+".AF_CO_GET_CLIENT_NAME(B.CLIENT_CODE)) LIKE UPPER('%"+_m_client_name+"%')  "+
					" AND UPPER(PAYEE_NAME) LIKE UPPER('%"+_m_client_name+"%')  "+
					" GROUP BY A.PAYMENT_NO,SETTLE_MODE,EFF_VAL_DATE,A.ENTRY_TYPE,FINANCE_NO ,B.CLIENT_CODE,PAYEE_NAME,B.PROCESS_STATUS "); //--,B.CLIENT_CODE
					//" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");

				
				
					/*rs= stmt.executeQuery			
					(" SELECT DISTINCT C.APPLICATION_NO APPLICATION_NO, "+
					" nvl(C.FINANCE_NO,'-') FINANCE_NO, "+
					" SUM(C.TOTAL_FINANCE_AMOUNT) TOTAL_FINANCE_AMOUNT , "+
					"'',"+
					"'',"+
					"'',"+
					"'',"+
					" SUM(B.BAL_TO_BE_PAID) BAL_TO_BE_PAID, "+
					" to_char(b.value_date,'dd-mm-yyyy') value_date, "+
					" NVL(B.RECEIVER,'-')  RECEIVER, 		"+
					" NVL("+m_schema_name+".af_co_get_vendor_name(B.RECEIVER),'-') VENDOR_NAME, "+
					"'',"+
					" SUM(B.TOT_SETTLE_AMOUNT) TOT_SETTLE_AMOUNT, "+
					" SUM(B.INT_BAL_SETTLE_AMOUNT) INT_BAL_SETTLE_AMOUNT "+
					" FROM  "+
					" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C,  "+
					" "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT B, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS D ,"+
					" "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT E  "+
					" WHERE  "+
					" B.REF_NO=D.INVOICE_NO "+
					" AND D.APPLICATION_NO=C.APPLICATION_NO "+
					" AND B.BAL_TO_BE_PAID >0  "+
					" AND D.ACTIVE_STATUS<>'C'   "+ //--NOT CANCELLED
					" AND E.PROCESS_STATUS='"+m_status+"' "+ 
					" AND  UPPER("+m_schema_name+".af_co_get_vendor_name(B.RECEIVER))    LIKE UPPER('%"+_m_client_name+"%')   "+
					//" OR  UPPER("+m_schema_name+".af_co_get_client_name(C.CLIENT_CODE)) LIKE UPPER('%"+_m_client_name+"%'))  "+
					" GROUP BY  C.APPLICATION_NO,C.FINANCE_NO,b.value_date ,B.RECEIVER    "+
					" ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
         */
				
																				
		//	}											
//----------------------------------------------------------------------------------------------------------------									


			
			
			boolean more=rs.next();
			int j=0;
			//out.println("rs.getString(7).substring(0,2)"+rs.getString(7).substring(0,2));
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">"); 
		  
			while(more){
									
	
			if(j==0){
			out.println("<tr class=\"pdn_txtpos2\">");
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Application No  \" onclick=sort_data(\"APPLICATION_NO\")>Application No</td>"); 
			out.println("<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by -  Finance No  \" onclick=sort_data(\"FINANCE_NO\")>Finance No</td>"); 
			out.println("<td width=\"10%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by -  Payment No  \" onclick=sort_data(\"PAYMENT_NO\")>Payment No</td>"); 
			out.println("<td width=\"8%\" align=\"left\" 	style=cursor:hand; title=\"Click here to sort by - Value Date  \" onclick=sort_data(\"VALUE_DATE\")>Value Date</td>"); 
			out.println("<td width=\"9%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Vendor  \" onclick=sort_data(\"VENDOR_NAME\")>Receiver</td>"); 
			out.println("<td width=\"9%\" align=\"right\" 	style=cursor:hand; title=\"Click here to sort by - Total Paid Amount  \" onclick=sort_data(\"PAID_AMT\")>Paid Amount</td>"); 
			out.println("<td width=\"4%\" align=\"center\">Type</td>"); 
			out.println("<td width=\"3%\" align=\"left\">User</td>");
			out.println("<td width=\"4%\" align=\"center\">&nbsp</td>");
			out.println("</tr>");
			}
			
			if(j>0 && j%2==1){
      out.println("<tr class=tr_input1 >");
			}
			else{									
      out.println("<tr class=tr_input >");
			}
			
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=show_application_detail_drill('"+rs.getString(1)+"')><U>"+rs.getString(1)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_APP_NO_"+j+" value=\""+rs.getString(1)+"\"></td>"); 
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=show_finance_detail_drill('"+rs.getString(2)+"')><U>"+rs.getString(2)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\"></td>"); 
			out.println("<td  align=\"left\" style= \"cursor:hand;cursor-color:blue\" onclick=\"show_payment_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAYMENT_NO_"+j+" value=\""+rs.getString(3)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(4)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_VALUE_DATE_"+j+" value=\""+rs.getString(4)+"\"></td>"); 
		  out.println("<td  align=\"left\" >"+rs.getString(5)+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_RECEIVER_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
			out.println("<td  align=\"right\" >"+nf.format(rs.getDouble(9))+"<input  class=\"txt_input\" type=\"hidden\" name=TXT_PAID_"+j+" value=\""+rs.getString(9)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BAL_TO_BE_PAID_"+j+" value=\""+rs.getString(8)+"\"><input  class=\"txt_input\" type=\"hidden\" name=TXT_BRANCH_"+j+" value=\"\"></td>"); 
			out.println("<td  align=\"left\" >"+rs.getString(10)+"<input type=\"hidden\" name=TXT_ENT_TYPE_"+j+" value=\""+rs.getString(9)+"\"></td>"); 
			out.println("<td  align=\"left\" >"+m_username+"</td>");  // m_username.toLowerCase() // comment nuwan de silva on 21-01-08
			if(m_screen_type.equals("NEW"))			
			out.println("<td align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 45px\" name=BUT_VIEW_"+j+" value=\"Approve\" onClick=\"help_button("+j+")\"></td>"); 
			else
			out.println("<td align=\"center\" ><input type=\"checkbox\" name=CHK_APP_"+j+" value=\"\" onClick=\"check_change("+j+")\"></td>"); 
			out.println("</tr>");
			
			out.println("<input type=\"hidden\" name=TXT_CURR_CODE_"+j+" value=\"\">"); 
			out.println("<input type=\"hidden\" name=TXT_ACC_NO_"+j+" value=\"\">"); 
			out.println("<input type=\"hidden\" name=TXT_TOT_SETTLE_AMT_"+j+" value=\""+rs.getDouble(6)+"\">"); 
			out.println("<input type=\"hidden\" name=TXT_INIT_BAL_AMT_"+j+" value=\""+rs.getDouble(7)+"\">"); 
			//out.println("<input type=\"hidden\" name=TXT_PAID_"+j+" value=\""+rs.getDouble(7)+"\">"); 
      out.println("<input type=\"hidden\" name=HID_PAY_NO_"+j+" value=\""+rs.getString(3)+"\">"); //Added BY Sandun on 19-01-2009
			out.println("<input type=\"hidden\" name=\"HID_STATUS\" value=\""+rs.getString(12)+"\" >"); //Added BY Sandun on 19-01-2009
			
			more=rs.next();
			j=j+1;
			}
		
			out.println("<input type=\"hidden\" name=hid_count value="+j+"></td>");
			out.println("</table>");
			
			out.println("<BR>");
			out.println("<HR>");
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>");

			out.println("<input type=\"hidden\" name=hid_m_client_name value="+_m_client_name+">");//added by nuwan de silva on 17-01-2008

			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_bot     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_top);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
      //____________________________________________________________________________________________________
			
			}
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(rs1    !=null){try{rs.close();   }catch(Exception e){}}
			
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(stmt1  !=null){try{stmt.close(); }catch(Exception e){}}
			
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
