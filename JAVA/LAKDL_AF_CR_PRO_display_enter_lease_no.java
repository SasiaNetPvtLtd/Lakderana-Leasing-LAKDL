//--
//SCREEN NAME	:Credit Process - ENTER LEASE NO/CHANGE ACTIVATED DATE
//CREATED BY	:DELANJALI	
//DATE/TIME		:12-02-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_CR_PRO_display_enter_lease_no extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt;//,stmt1,stmt2,stmt3,stmt4;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;//,rs1,rs2,rs3,rs4;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			String	m_screen_type=req.getParameter("SCREEN_TYPE");
			String m_st="";
			String m_show="";
			
			String m_status=req.getParameter("status");
			
			if(m_status.equals("lease")){
				m_st="lease";
				m_show="Finance No Entry";
				
			}
			else if(m_status.equals("act")){
				m_st="act";
				m_show="Change Activation Date";
			}
			
			
			String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "ASC";
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
				m_sort_column = req.getParameter("sort_column");
				m_order_by_type = req.getParameter("order_by_type");
			}
			
			String m_client_code=req.getParameter("CLIENT_CODE");
			
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			
			String m_postdate_dd="";
			String m_postdate_mm="";
			String m_postdate_yy="";
			
			
			
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - "+m_show+"</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			out.println("var b_flag1=0");
			out.println("var b_row=0");
			
			out.println("function get_vector(data_vec) {");
			
			
			out.println("			if(data_vec.length>0 && document.Form1.hid_req.value==\"J1\" && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				alert('Record already exists');");
			out.println("document.Form1.elements[\"TXT_FINANCE_NO_\"+b_row].value=\"\"");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.hid_req.value==\"J1\" && document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("	help_update();");
			out.println("			}");
			out.println("		else if(data_vec.length>0 && document.Form1.hid_req.value==\"M_AGREMNT\" && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("			if(document.Form1.elements[\"HID_TXT_CLIENT_CODE\"+b_row].value !=data_vec[1] ){ ");
			out.println("				alert('Record already exists');");
			out.println("       document.Form1.elements[\"TXT_M_FINANCE_NO_\"+b_row].value=\"\"");
			out.println("			}");
			out.println("			}");
			
			
			out.println("		else if(data_vec.length>0 && document.Form1.hid_req.value==\"M_TERMI_CHK\" && document.Form1.SCREEN_NAME.value==\"NEW\" ){");
			//out.println("				  alert('data_vec[3]'+data_vec[3]);");
			//out.println("				  alert('data_vec[1]'+data_vec[1]);");
			//out.println("				  alert('data_vec[0]'+data_vec[0]);");
			//out.println("				  alert('document.Form1.hid_trmi_dd.value'+document.Form1.hid_trmi_dd.value);");
			out.println("			if(data_vec[3]==\"TERMI\" || data_vec[3]==\"TERMINATED\"|| data_vec[3]==\"NORM_TERMI\" ){ ");
			out.println("			   if(document.Form1.hid_trmi_dd.value !=data_vec[0] || document.Form1.hid_trmi_mm.value !=data_vec[1] || document.Form1.hid_trmi_yy.value !=data_vec[2]  ){ ");
			//out.println("			   if(document.Form1.elements[\"hid_trmi_dd\"+b_row].value !=data_vec[0] || document.Form1.elements[\"hid_trmi_mm\"+b_row].value !=data_vec[1] || document.Form1.elements[\"hid_trmi_yy\"+b_row].value !=data_vec[2]  ){ ");
			out.println("				  alert('Activated date must equal to previous terminated date');");
			out.println("                 document.Form1.elements[\"TXT_DATE_YY\"+b_row].value=\"\"");
			out.println("                 document.Form1.elements[\"TXT_DATE_DD\"+b_row].value=\"\"");
			out.println("                 document.Form1.elements[\"TXT_DATE_MM\"+b_row].value=\"\"");
			out.println("			   }");
			out.println("			 }");
			out.println("			}");
			
			
			out.println("}");
			
			
			
			out.println("function makeRequest(row) {");
			/*out.println("document.Form1.hid_req.value=\"J1\"");
			out.println("b_row=row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_no&data_val=\"+document.Form1.elements[\"TXT_FINANCE_NO_\"+row].value+\"&ac_status=VERIFYL\";");
			out.println("load_interface(m_url,'XML');");
			*/
			out.println("}");
			
			
			out.println("function validate_agreement_no(row) {");
			out.println("document.Form1.hid_req.value=\"M_AGREMNT\"");
			out.println("b_row=row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_no_validate_agreement_no&data_val=\"+document.Form1.elements[\"TXT_M_FINANCE_NO_\"+row].value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			out.println("function sysdate() {");
			out.println("document.Form1.hid_req.value=\"J2\"");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(row){"); 
			//if(m_status.equals("lease")){
			out.println("m_status='"+m_status+"';");
			out.println("m_type='"+m_screen_type+"';");
			out.println("for(var f=0;f<row;f++){");
			
			out.println("if(document.Form1.elements[\"chk_app_\"+f].checked==true){");
			
			out.println("b_flag1=1"); 
			
			out.println("if(document.Form1.elements[\"TXT_FINANCE_NO_\"+f].value==\"\"){  "); 
			out.println(" b_flag=0");
			out.println("}"); 
			
			//out.println("else if( m_status==\"lease\" && m_type!=\"reverse\" && document.Form1.elements[\"TXT_M_FINANCE_NO_\"+f].value==\"\" && document.Form1.elements[\"HID_TRANSACTION_TYPE\"+f].value!=\"HIREPURCH\"){  "); // commented by udara 10-08-2018
			out.println("else if( m_status==\"lease\" && m_type!=\"reverse\" && document.Form1.elements[\"TXT_M_FINANCE_NO_\"+f].value==\"\" && document.Form1.elements[\"HID_TRANSACTION_TYPE\"+f].value!=\"HIREPURCH\" && document.Form1.elements[\"HID_TRANSACTION_TYPE\"+f].value!=\"LOANS\"){  "); // mod by udara 10-08-2018
			out.println(" b_flag=0");
			out.println("}"); 
			
			out.println(" else if(m_status==\"lease\" && m_type!=\"reverse\" &&  document.Form1.elements[\"TXT_ML_DATE_DD\"+f].value==\"\" && document.Form1.elements[\"TXT_ML_DATE_MM\"+f].value==\"\" && document.Form1.elements[\"TXT_ML_DATE_YY\"+f].value==\"\"  && document.Form1.elements[\"HID_TRANSACTION_TYPE\"+f].value!=\"HIREPURCH\" ){  "); 
			out.println(" b_flag=0");
			out.println("}"); 
			
			out.println(" else if(m_status==\"lease\" && m_type!=\"reverse\" && document.Form1.elements[\"TXT_AGR_DATE_DD\"+f].value==\"\" && document.Form1.elements[\"TXT_AGR_DATE_MM\"+f].value==\"\" && document.Form1.elements[\"TXT_AGR_DATE_YY\"+f].value==\"\"){  "); 
			out.println(" b_flag=0");
			out.println("}"); 
			
			out.println(" else if(m_status==\"lease\" && m_type!=\"reverse\" && document.Form1.elements[\"TXT_NEXT_DATE_DD\"+f].value==\"\" && document.Form1.elements[\"TXT_NEXT_DATE_MM\"+f].value==\"\" && document.Form1.elements[\"TXT_NEXT_DATE_YY\"+f].value==\"\"){  "); 
			out.println(" b_flag=0");
			out.println("}"); 
			
			
			
			out.println("else{  "); 
			out.println(" b_flag=1");
			out.println("}");
			
			
			out.println(" if(document.Form1.elements[\"TXT_DATE_DD\"+f].value==\"\" && document.Form1.elements[\"TXT_DATE_MM\"+f].value==\"\" && document.Form1.elements[\"TXT_DATE_YY\"+f].value==\"\"){  "); 
			out.println(" b_flag=0");
			out.println("return false;"); 
			out.println("}"); 
			
			out.println("if(b_flag==1 ){"); 
			out.println("break"); 
			out.println("}"); 
			out.println("if(document.Form1.elements[\"chk_app_\"+f].checked==false){");
			out.println("b_flag1=0"); 
			out.println("continue"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			
			
			
			out.println("function sort_data(m_sort_col) {");
			//out.println("var m_bk=1");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_enter_lease_no?chksql=main_page&CLIENT_CODE="+m_client_code+"&SCREEN_TYPE="+m_screen_type+"&status="+m_st+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");   
			
			out.println("function before_submit(){ "); 
			out.println("document.Form1.hid_client_code.value='"+m_client_code+"'");
			//out.println("document.Form1.hid_transaction_type.value='"+m_screen_type+"'");
			
			//out.println(" alert('client &&'+document.Form1.hid_client_code.value)");
			out.println("var row=document.Form1.hid_no.value");	
			out.println("		validate_data(row)"); 
			out.println("	if(b_flag1==0){");
			out.println("alert('Please select an Application no')");
			out.println("	}");
			out.println("	else{");
			out.println("	if(b_flag==1){");
			
			// commented by udara 25-05-2016
			//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("document.Form1.elements[i].disabled=false;");
			//out.println("}");
			
			
			out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
	
			// added by udara 25-05-2016
			out.println("         for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("              document.Form1.elements[i].disabled=false;");
			out.println("         }");
			// end by udara 25-05-2016
			
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_save_enter_lease_no?status="+m_st+"&m_screen="+m_screen_type+"';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_enter_lease_no?chksql=main_page&CLIENT_CODE="+m_client_code+"&SCREEN_TYPE="+m_screen_type+"&status="+m_st+"';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_enter_lease_no?chksql=main_page&CLIENT_CODE="+m_client_code+"&SCREEN_TYPE=new&status="+m_st+"';"); 
			
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function reverse_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_enter_lease_no?chksql=main_page&SCREEN_TYPE=reverse&status="+m_st+"';"); 
			out.println("}"); 
			
			out.println("function edit_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_enter_lease_no?chksql=main_page&SCREEN_TYPE=edit&status="+m_st+"';"); 
			out.println("}"); 
			
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_enter_lease_no\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit - "+m_show+"- \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit - "+m_show+" - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}");
			out.println("else if(m_val==\"REVERSE\"){"); 
			out.println("reverse_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled =false;"); 
			out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ACTIVATED_DATE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("edit_window()");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"REVERSE\"){");  
			out.println("document.Form1.hid_status.value=\"Reverse\";");  
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
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {");			
			out.println("oBj = new MyDialog();");
			out.println("oBj.valout[3]  = \" \";");
			out.println("oBj.valout[4]  = \" \";");
			out.println("oBj.valout[5]  = \" \";");
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	"); 
			out.println("if(oBj.valout[0]=='Next')  {");
			out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[0]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != null){");
			out.println("if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("}");
			out.println("}");	
			
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			
			
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@VERIFY2@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_APPLICATION_NO','1');");
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println(" if(document.Form1.SCREEN_NAME.value=='EDIT' || document.Form1.SCREEN_NAME.value=='DACT' || document.Form1.SCREEN_NAME.value=='RACT'){");
			out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"VERIFYL@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'FinanceSql','2');");
			out.println("}");
			out.println("}");
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function check_date_po(row){ ");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_POSTED_DATE_DD_\"+row],document.Form1.elements[\"TXT_POSTED_DATE_MM_\"+row],document.Form1.elements[\"TXT_POSTED_DATE_YY_\"+row]);");
			out.println("}");
			
			
			out.println("function check_date_to(row){ ");
			out.println(" alert();");
			out.println("  checkMonthLength(document.Form1.elements[\"TXT_DATE_DD\"+row],document.Form1.elements[\"TXT_DATE_MM\"+row],document.Form1.elements[\"TXT_DATE_YY\"+row]);");
			out.println("}");
			
			//---added by ishani
			out.println("function chek_termi_date(dd,mm,yy,row){ ");
			out.println("document.Form1.hid_req.value=\"M_TERMI_CHK\"");
			out.println("document.Form1.hid_trmi_dd.value=dd.value; ");
			out.println("document.Form1.hid_trmi_mm.value=mm.value; ");
			out.println("document.Form1.hid_trmi_yy.value=yy.value; ");
			out.println("b_row=row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_validate_activation_with_termination_in_refinance&data_val=\"+document.Form1.elements[\"TXT_FINANCE_NO_\"+row].value;");
			//	out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");out.println("}");
			
			
			/*out.println("function chek_termi_date(row) {");
			out.println("document.Form1.hid_req.value=\"M_AGREMNT\"");
			out.println("b_row=row");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_no_validate_agreement_no&data_val=\"+document.Form1.elements[\"TXT_M_FINANCE_NO_\"+row].value;");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");*/
			
			
			
			out.println("function check_date(OBJ_DD,OBJ_MM,OBJ_YY){ ");
			
			out.println(" if((OBJ_DD.value !=\"\")&&(OBJ_MM.value !=\"\")&&(OBJ_YY.value !=\"\")){");
			out.println("  checkMonthLength(OBJ_DD,OBJ_MM,OBJ_YY);");
			out.println(" }");
			
			out.println("}");
			
			
			//==========Added By Nuwan De Silva ======================================================
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
			//out.println("alert('from date' +FROM_DD.value+FROM_MM.value+FROM_YY.value 'to date' +TO_DD.value+TO_MM.value+TO_YY.value);");
			out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
			//out.println("a=parseInt(FROM_MM.value); ");
			//out.println("b=parseFloat(TO_MM.value); ");
			//out.println("alert('a'+TO_MM.value);");
			//out.println("alert('b'+a);");
			out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
			out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
			out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
			out.println(" if(((parseFloat(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
			out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
			out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
			out.println("}");
			out.println("else if(((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value)))&&");
			out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
			out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("     } ");
			out.println("}");
			out.println("else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("}");
			out.println(" }");
			out.println(" else{");
			out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("   }");
			out.println("   else{");
			out.println("   } ");
			out.println(" }");
			out.println("}");
			out.println("else{");
			out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
			out.println("  if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
			out.println(" }");
			out.println(" else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println(" }");
			out.println("}");
			out.println("else{");
			out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
			out.println("    }");
			out.println("  else{");
			out.println("      alert('Next Payment Date should be greater than Activated Date');");
			out.println("return false;"); 
			out.println("  }");
			out.println(" }");
			out.println("}");
			//	out.println("TO_DD.focus();");
			out.println("return true;");
			out.println("}");
			out.println("}");
			
			//===========================================================================================
			
			out.println("function check_date_next(FROM_OBJ_DD,FROM_OBJ_MM,FROM_OBJ_YY,TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY){ ");
			
			//	out.println("alert('date' +FROM_OBJ_DD.value+FROM_OBJ_MM.value+FROM_OBJ_YY.value+TO_OBJ_DD.value+TO_OBJ_MM.value+TO_OBJ_YY.value);");
			
			out.println(" if((TO_OBJ_DD.value !=\"\")&&(TO_OBJ_MM.value !=\"\")&&(TO_OBJ_YY.value !=\"\")){");
			
			out.println("if(checkMonthLength(TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY)){");
			
			out.println(" if((FROM_OBJ_DD.value !=\"-\")&&(FROM_OBJ_DD.value !=\"-\")&&(FROM_OBJ_DD.value !=\"-\")){");
			
			out.println("if(!chk_validity(FROM_OBJ_DD,FROM_OBJ_MM,FROM_OBJ_YY,TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY)){");
			
			out.println("TO_OBJ_DD.value=\"\"; ");
			out.println("TO_OBJ_MM.value=\"\"; ");
			out.println("TO_OBJ_YY.value=\"\"; ");
			
			out.println(" }");
			
			out.println(" }");
			
			out.println(" }");
			
			out.println("}");
			
			out.println("}");
			
			// commented by udara 18-04-2016
			/*
			out.println("function load_calendar(num,row) {");
			out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println(" document.Form1.hid_count.value=row;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			*/
			
			// added by udara 18-04-2016
			out.println("function load_calendar(num,row) {");
			
			out.println("}");
			
			// end by udara 18-04-2016
			
			out.println("function load_c_date(val) {");
			//out.println("v_date='';");
			//out.println("v_month='';");
			//out.println("val='';");
			
			out.println("m_row=document.Form1.hid_count.value");
			
			out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
			out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			
			
			out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
			
			out.println("     document.Form1.elements[\"TXT_DATE_DD\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_DATE_MM\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_DATE_YY\"+m_row].value=val;");
			
			//out.println("check_date(document.Form1.elements[\"hid_TXT_INS_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_INS_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_INS_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_INS_NEW_DATE_YY_\"+m_row]) ");
			
			out.println("  }");				
			
			out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 
			
			out.println("     document.Form1.elements[\"TXT_ML_DATE_DD\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_ML_DATE_MM\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_ML_DATE_YY\"+m_row].value=val;");
			
			//out.println("check_date(document.Form1.elements[\"hid_TXT_REV_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_YY_\"+m_row]) ");
			
			out.println("  }");				
			
			out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 
			
			out.println("     document.Form1.elements[\"TXT_AGR_DATE_DD\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_AGR_DATE_MM\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_AGR_DATE_YY\"+m_row].value=val;");
			
			//out.println("check_date(document.Form1.elements[\"hid_TXT_TAX_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_TAX_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_TAX_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_TAX_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_TAX_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_TAX_NEW_DATE_YY_\"+m_row]) ");
			
			out.println("  }");				
			
			
			out.println("  else if(document.Form1.hid_cal_date.value=='4'){"); 
			
			//out.println("alert('v_date'+v_date);");
			//out.println("alert('v_month'+v_month);");
			//out.println("alert('val'+val);");
			out.println("     document.Form1.elements[\"TXT_NEXT_DATE_DD\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_NEXT_DATE_MM\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_NEXT_DATE_YY\"+m_row].value=val;");
			
			out.println("check_date_next(document.Form1.elements[\"TXT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_DATE_YY\"+m_row],document.Form1.elements[\"TXT_NEXT_DATE_DD\"+m_row],document.Form1.elements[\"TXT_NEXT_DATE_MM\"+m_row],document.Form1.elements[\"TXT_NEXT_DATE_YY\"+m_row]) ");
			
			out.println("  }");				
			
			
			out.println("}");
			out.println("}");
			
			
			/*	out.println("function load_c_date(val) {");
				out.println("m_row=document.Form1.hid_count.value");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				out.println("     document.Form1.elements[\"TXT_DATE_DD\"+m_row].value=v_dd;");
				out.println("     document.Form1.elements[\"TXT_DATE_MM\"+m_row].value=v_mm;");
				out.println("     document.Form1.elements[\"TXT_DATE_YY\"+m_row].value=v_yy;");
				out.println("  }");	
			out.println(" else if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("  v_dd = val.substr(0,val.indexOf('-'))");
				out.println("   if(v_dd.length <2) ");
				out.println("   v_dd = 0+v_dd ");
				out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
				out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
				out.println("   if(v_mm.length <2) ");
				out.println("   v_mm = 0+v_mm ");
				out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
				
				out.println("     document.Form1.elements[\"TXT_POSTED_DATE_DD_\"+m_row].value=v_dd;");
				out.println("     document.Form1.elements[\"TXT_POSTED_DATE_MM_\"+m_row].value=v_mm;");
				out.println("     document.Form1.elements[\"TXT_POSTED_DATE_YY_\"+m_row].value=v_yy;");
				
				out.println("  }");	
				
				out.println("}");				
			*/
			
			out.println("function change(row) {"); 
			out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==false){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"N\";"); 
			out.println("}");
			out.println("else if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
			out.println("    document.Form1.elements[\"chk_app_\"+row].value=\"Y\";"); 
			out.println("}");
			out.println("}"); 
			
			/*out.println("function close(){"); 
			out.println("		if(confirm(\"Are you sure you want to close this screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_change_activation_date?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 
*/
			//*************************************************************************************************************************
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),sysdate()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 
			//	out.println("<INPUT TYPE='Hidden' NAME='hid_transaction_type' VALUE=\"\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_req' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_count' VALUE=\"\">");
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_trmi_dd' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_trmi_mm' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_trmi_yy' VALUE=\"\">");
			
			
			
			
			
			if(m_st.equals("act")){
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			}
			if(m_st.equals("lease")){
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			}
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit - "+m_show+"</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			if(m_st.equals("lease")){
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onclick='load_screen_status(\"REVERSE\")' value=\"Reverse\"></td>");  // commented by udara 31-05-2019
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reverse\");' onclick='load_screen_status(\"REVERSE\")' value=\"Reverse\" disabled ></td>");  // added by udara 31-05-2019
			}
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			
			if(m_st.equals("lease")){
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			}
			if(m_st.equals("act")){
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			}
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			
			
			out.println("<table align='center' border='0' width='100%' class='table'>"); 
			
			//********************************************************************************************************************************************************************************************************************************
			
			
			if(m_st.equals("lease")){
				out.println("<table class=table border='0' width='1290'  >"); //1170
				out.println("<tr class=pdn_txtpos2 align='left'>");
			}
			if(m_st.equals("act")){
				out.println("<table class=table border='0' width='100%'  >");
				out.println("<tr class=pdn_txtpos2 align='left'>");
			}
			
			
			
			if(m_st.equals("lease")){
				
				out.println("<td  width='100' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
				out.println("<td  width='120' style= cursor:hand; title='Click here to sort by - Master Lease No   '    onclick=sort_data('FINANCE_NO') >Master Lease No * </td>");
				out.println("<td  width='120' style= cursor:hand; title='Click here to sort by - Finance No   '    onclick=sort_data('FINANCE_NO') >Finance No * </td>");
				out.println("<td  width='120' style= cursor:hand; title='Click here to sort by - Client Name   '    onclick=sort_data('CLIENT') >Client Name</td>"); //added by nuwan de silva on 26-10-07--------------
				if(m_screen_type.equals("new") || m_screen_type.equals("edit")){
					out.println("<td  width='170'>Master Lease Date *</td>");
					out.println("<td  width='170'>Agreement Date *</td>");
					out.println("<td  width='170'>Activate Date *</td>");
					out.println("<td  width='170'>Next Payment Date *</td>");
				}
				
				out.println("<td  width='100' style= cursor:hand; title='Click here to sort by - Posted To  '    onclick=sort_data('CLIENT') >Posted To</td>");
				//	out.println("<td  width='100' style= cursor:hand; title='Click here to sort by - Address 1  '    onclick=sort_data('ADD1') >Address1</td>");
				//	out.println("<td  width='100' style= cursor:hand; title='Click here to sort by - Address 2  '    onclick=sort_data('ADD2') >Address2</td>");
				//  out.println("<td  width='100' style= cursor:hand; title='Click here to sort by - Telephone  '    onclick=sort_data('TEL') >Telephone</td>");
				
				if(m_screen_type.equals("new") || m_screen_type.equals("edit")){
					//	out.println("<td  width='170'>Posted Date</td>");
					//out.println("<td  width='150'>Activate Date </td>");
				}
				if(m_screen_type.equals("reverse")){
					//out.println("<td  width='170' style= cursor:hand; title='Click here to sort by - Posted Date  '    onclick=sort_data('POSTED_DATE') >Posted Date</td>");
					out.println("<td  width='170' style= cursor:hand; title='Click here to sort by - Master Lease Date  '    onclick=sort_data('START_DATE') >Master Lease Date </td>");
					out.println("<td  width='170' style= cursor:hand; title='Click here to sort by - Agreement Date  '    onclick=sort_data('AGREEMENT_DATE') >Agreement Date</td>");
					out.println("<td  width='170' style= cursor:hand; title='Click here to sort by - Activated Date  '    onclick=sort_data('ACTIVATED_DATE') >Activate Date </td>");
					out.println("<td  width='170' style= cursor:hand; title='Click here to sort by - Next Payment Date  '    onclick=sort_data('NEXT_DATE') >Next Payment Date </td>");
					
				}
				
			}
			
			
			else if(m_st.equals("act")){
				out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('FINANCE_NO') >Finance No </td>");
				out.println("<td  width='25%' style= cursor:hand; title='Click here to sort by - Application No  '    onclick=sort_data('APPLICATION_NO') >Application No</td>");
				out.println("<td  width='25%'  style= cursor:hand; title='Click here to sort by - Client Code  '    onclick=sort_data('CLIENT') >Client Name</td>");
				out.println("<td  width='20%'>Activate Date </td>");
				
			}		
			
			
			
			
			
			out.println("<td  width='50' align=center >Status</td></tr>");
			
			//===========END OF HEADER ================================================================
			
			int j=0;
			String m_fin="";
			String m_status1="";
			
			String m_po_status_q = " "; // added by udara 03-06-2019
			
			//-------------------------------------------------------------------------------------------------------------------------------------------------
			//--if menu option is Enter Leasing--//
			if(m_status.equals("lease")){
				
				if(m_screen_type.equals("new")){
					m_status1="VERIFY2";
					m_po_status_q = " "; // added by udara 03-06-2019
				}
				if(m_screen_type.equals("edit")){
					m_status1="VERIFYL";
					m_po_status_q = " "; // added by udara 03-06-2019
				}
				if(m_screen_type.equals("reverse")){
					m_status1="VERIFYL";
					m_po_status_q = " AND A.APPLICATION_NO NOT IN (SELECT APPLICATION_NO FROM "+m_schema_name+".AF_CR_PRO_PURCHASE_ORDER WHERE APPLICATION_NO = A.APPLICATION_NO) "; // added by udara 03-06-2019
				}
				
				/*		rs = stmt.executeQuery ("SELECT A.APPLICATION_NO AS APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT,FINANCE_NO,SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),0,2) "+
						" ,SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),7,4),SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),0,2), "+
					" SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),7,4) AS ACTIVATED_DATE,SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),0,2) AS POSTED_DATE,SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),7,4) "+
					" ,POSTED_NAME AS POSTED_NAME,POST_ADDRESS1 AS ADDRESS1,POST_ADDRESS2 AS ADDRESS2,TELEPHONE AS TELEPHONE,A.CLIENT_CODE AS CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.CLIENT_CODE) AS ADD1,"+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.CLIENT_CODE) AS ADD2,"+m_schema_name+".AF_CO_GET_CLIENT_TEL(A.CLIENT_CODE) AS TEL,A.MASTER_AGREEMENT_NO "+
						" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
						" WHERE APPLICATION_STATUS =('"+m_status1+"') "+
						" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				*/
				
				//=====modofied by Nuwan De Silva 10-07-07===================================================
				rs = stmt.executeQuery 
					("SELECT "+
					" A.APPLICATION_NO AS APPLICATION_NO, "+ //1
					" "+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT, "+ //2
					" FINANCE_NO, "+ //3
					//" SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),0,2), "+//4
					//" SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),4,2), "+//5
					//" SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),7,4), "+//6
					" TO_CHAR(SYSDATE,'DD'), "+//4
					" TO_CHAR(SYSDATE,'MM'), "+//5
					" TO_CHAR(SYSDATE,'YYYY'), "+//6 
					//" SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),0,2), "+//7
					//" SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),4,2), "+//8
					//" SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),7,4) AS ACTIVATED_DATE, "+//9
					" decode(NVL(TER_TYPE,'-'),'-',TO_CHAR(ACTIVATED_DATE,'DD'),TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'DD')), "+//7
					" decode(NVL(TER_TYPE,'-'),'-',TO_CHAR(ACTIVATED_DATE,'MM'),TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'MM')), "+//8
					" decode(NVL(TER_TYPE,'-'),'-',TO_CHAR(ACTIVATED_DATE,'YYYY'),TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'YYYY')), "+//9
					//" TO_CHAR(ACTIVATED_DATE,'MM'), "+//8
					//" TO_CHAR(ACTIVATED_DATE,'YYYY')  AS ACTIVATED_DATE, "+//9
					//" SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),0,2) AS POSTED_DATE, "+//10
					//" SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),4,2), "+//11
					//" SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),7,4), "+//12
					" TO_CHAR(POSTED_DATE,'DD') AS POSTED_DATE , "+//10
					" TO_CHAR(POSTED_DATE,'MM'), "+//11
					" TO_CHAR(POSTED_DATE,'YYYY'), "+//12
					" POSTED_NAME AS POSTED_NAME, "+//13
					" POST_ADDRESS1 AS ADDRESS1, "+//14
					" POST_ADDRESS2 AS ADDRESS2, "+//15
					" TELEPHONE AS TELEPHONE, "+//16
					" A.CLIENT_CODE AS CLIENT_CODE, "+//17
					" "+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.CLIENT_CODE) AS ADD1, "+//18
					" "+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.CLIENT_CODE) AS ADD2, "+//19
					" "+m_schema_name+".AF_CO_GET_CLIENT_TEL(A.CLIENT_CODE) AS TEL, "+//20
					"   B.AGREEMENT_NO AGREEMENT_NO,  "+ //21
					"   B.START_DATE START_DATE, "+  //22
					"   B.END_DATE END_DATE, "+ //23
					"   NVL("+m_schema_name+".AF_CO_GET_MASTER_AGR_STATUS(A.CLIENT_CODE,A.APPLICATION_NO),0) M_STATUS, "+ //24
					"   B.START_DATE_DD, "+  //25
					"   B.START_DATE_MM, "+  //26
					"   B.START_DATE_YY, "+  //27
					/* COMMENT  AND CHANGE BELOW BY WARUNA 2012-04-23
					"   NVL(TO_CHAR(ADD_MONTHS(SYSDATE,1),'DD'),'') NEXT_DATE_DD, "+ //28
					"   NVL(TO_CHAR(ADD_MONTHS(SYSDATE,1),'MM'),'') NEXT_DATE_MM, "+ //29
					"   NVL(TO_CHAR(ADD_MONTHS(SYSDATE,1),'YYYY'),'') NEXT_DATE_YY, "+ //30*/	
					"   NVL(TO_CHAR("+m_schema_name+".AF_CR_GET_NEXT_PAYMENT_DATE(ADD_MONTHS(SYSDATE,1)),'DD'),'') NEXT_DATE_DD, "+ //28
					"   NVL(TO_CHAR("+m_schema_name+".AF_CR_GET_NEXT_PAYMENT_DATE(ADD_MONTHS(SYSDATE,1)),'MM'),'') NEXT_DATE_MM, "+ //29
					"   NVL(TO_CHAR("+m_schema_name+".AF_CR_GET_NEXT_PAYMENT_DATE(ADD_MONTHS(SYSDATE,1)),'YYYY'),'') NEXT_DATE_YY, "+ //30
					"   A.TRANSACTION_TYPE ,"+ //31
					"   DECODE(TO_CHAR(A.AGREEMENT_DATE,'DD'),'',TO_CHAR(SYSDATE,'DD'),TO_CHAR(A.AGREEMENT_DATE,'DD')) AGREEMENT_DATE_DD,"+ //32
					"   DECODE(TO_CHAR(A.AGREEMENT_DATE,'MM'),'',TO_CHAR(SYSDATE,'MM'),TO_CHAR(A.AGREEMENT_DATE,'MM')) AGREEMENT_DATE_MM ,"+ //33
					"   DECODE(TO_CHAR(A.AGREEMENT_DATE,'YYYY'),'',TO_CHAR(SYSDATE,'YYYY'),TO_CHAR(A.AGREEMENT_DATE,'YYYY')) AGREEMENT_DATE_YY ,"+ //34
					
					"   (SELECT TO_CHAR(MAX(RENTAL_DATE),'DD') FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE TO_NUMBER(INSTALLMENT_NO)=1 AND APPLICATION_NO=A.APPLICATION_NO) NEXT_DATE_DD, "+ //35
					"   (SELECT TO_CHAR(MAX(RENTAL_DATE),'MM') FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE TO_NUMBER(INSTALLMENT_NO)=1 AND APPLICATION_NO=A.APPLICATION_NO) NEXT_DATE_DD, "+ //36
					"   (SELECT TO_CHAR(MAX(RENTAL_DATE),'YYYY') FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE TO_NUMBER(INSTALLMENT_NO)=1 AND APPLICATION_NO=A.APPLICATION_NO) NEXT_DATE_DD, "+ //37
					
					"   (SELECT TO_CHAR(MAX(RENTAL_DATE),'DD-MM-YYYY') FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT WHERE TO_NUMBER(INSTALLMENT_NO)=1 AND APPLICATION_NO=A.APPLICATION_NO) NEXT_DATE, "+ //38
					"   DECODE(TO_CHAR(A.AGREEMENT_DATE,'DD-MM-YYYY'),'',TO_CHAR(SYSDATE,'DD-MM-YYYY'),TO_CHAR(A.AGREEMENT_DATE,'DD-MM-YYYY')) AGREEMENT_DATE, "+ //39
					"   NVL(TER_TYPE,'-') TER_TYPE,  "+// added by SH on 30-10-2008 for terminations
					"   NVL(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),1),'DD'),' ') NEXT_DATE_DD, "+ //41
					"   NVL(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),1),'MM'),' ') NEXT_DATE_MM, "+ //42
					"   NVL(TO_CHAR(ADD_MONTHS(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),1),'YYYY'),' ') NEXT_DATE_YY, "+ //43  //Added By sandun on 16-12-2008
					"   NVL(TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'DD'),' ') DATE_DD, "+ //44
					"   NVL(TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'MM'),' ') DATE_MM, "+ //45
					"   NVL(TO_CHAR(TO_DATE("+m_schema_name+".AF_CO_GET_TERMINATION_DATE(TERMINATION_NO),'DD-MM-YYYY'),'YYYY'),' ') DATE_YY "+ //46  //Added By sandun on 16-12-2008
					"   FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A , "+
					"   (SELECT CLIENT_CODE,MAX(AGREEMENT_NO) AGREEMENT_NO ,TO_CHAR(MAX(START_DATE),'DD-MM-YYYY') START_DATE,TO_CHAR(MAX(END_DATE),'DD-MM-YYYY') END_DATE, "+
					"   TO_CHAR(MAX(START_DATE),'DD') START_DATE_DD,TO_CHAR(MAX(START_DATE),'MM') START_DATE_MM,TO_CHAR(MAX(START_DATE),'YYYY') START_DATE_YY  "+
					"   FROM "+m_schema_name+".AF_CO_MAS_MASTER_AGREEMENT_DET  "+
					"   GROUP BY CLIENT_CODE) B "+
					"   WHERE A.APPLICATION_STATUS =('"+m_status1+"')  AND A.CLIENT_CODE=B.CLIENT_CODE(+) "+
					"   "+m_po_status_q+"   "+
					"   ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				
				//==========================================================================================
				
			}
			
			
			//-------------------------------------------------------------------------------------------------------------------------------------------------
			//--if menu option is Change Activated Date--//
			
			if(m_status.equals("act")){
				m_status1="";
				rs = stmt.executeQuery 
					("SELECT A.APPLICATION_NO AS APPLICATION_NO,"+m_schema_name+".AF_CO_GET_CLIENT_NAME(A.CLIENT_CODE) CLIENT,FINANCE_NO,SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),0,2) "+
					",SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(SYSDATE,'DD-MM-YYYY'),7,4),SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),0,2), "+
					"SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(ACTIVATED_DATE,'DD-MM-YYYY'),7,4),SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),0,2),SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),4,2),SUBSTR(TO_CHAR(POSTED_DATE,'DD-MM-YYYY'),7,4) "+
					",POSTED_NAME,POST_ADDRESS1 AS ADDRESS1,POST_ADDRESS2 AS ADDRESS2,TELEPHONE AS TELEPHONE,A.CLIENT_CODE AS CLIENT_CODE,"+m_schema_name+".AF_CO_GET_CLIENT_ADD1(A.CLIENT_CODE) AS ADD1,"+m_schema_name+".AF_CO_GET_CLIENT_ADD2(A.CLIENT_CODE) AS ADD2,"+m_schema_name+".AF_CO_GET_CLIENT_TEL(A.CLIENT_CODE) AS TEL "+
					"FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A  "+
					"WHERE APPLICATION_STATUS NOT IN ('CANCEL') "+
					"AND UPPER(CLIENT_CODE) like UPPER('"+m_client_code+"%') "+
					"AND finance_no IS NOT NULL "+
					" ORDER BY "+m_sort_column+" "+m_order_by_type+"");
				
				
			}	
			//-------------------------------------------------------------------------------------------------------------------------------------------------
			
			while(rs.next()){
				
				
				if(rs.getString(3)==null){
					m_fin="";
				}
				else{	m_fin=rs.getString(3);
				}
				
				//---------------------------------------
				m_date_dd=rs.getString(7);
				if(m_date_dd!=null){
					m_date_dd=rs.getString(7);
				}
				else{	m_date_dd=rs.getString(4);
				}	
				
				m_date_mm=rs.getString(8);
				
				if(m_date_mm!=null){
					m_date_mm=rs.getString(8);
				}
				else{	m_date_mm=rs.getString(5);
				}
				
				m_date_yy=rs.getString(9);
				if(m_date_yy!=null){
					m_date_yy=rs.getString(9);
				}
				else{	m_date_yy=rs.getString(6);
				}
				
				//---------------------------------------
				if(rs.getString(10)!=null){
					m_postdate_dd=rs.getString(10);
				}
				else{	m_postdate_dd=rs.getString(4);
				}	
				
				if(rs.getString(11)!=null){
					m_postdate_mm=rs.getString(11);
				}
				else{	m_postdate_mm=rs.getString(5);
				}
				
				if(rs.getString(12)!=null){
					m_postdate_yy=rs.getString(12);
				}
				else{	m_postdate_yy=rs.getString(6);
				}
				
				//	out.println("client_code"+rs.getString(17));
				
				
				
				if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
				}
				else{
					
					out.println("<tr class=tr_input >");
				}
				
				
				
				
				
				//-------------------------------------------------------------------------------------------------------------------------------------------------
				//--if menu option is Change Activated Date--//
				
				if(m_st.equals("act")){
					
					out.println("<td width='20%' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+m_fin+"')\"><U>"+m_fin+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+m_fin+"\" onblur=\"makeRequest("+j+")\"></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
					out.println("<td width='25%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input class='txt_input' type='hidden' name=TXT_APPLICATION_NO_"+j+" maxlength='15' size='15' value=\""+rs.getString(1)+"\"></td>");
					out.println("<td width='25%' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(17)+"')\"><U>"+rs.getString(2)+"</td>");
					out.println("<td width=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\" onblur=\"check_date_to("+j+")\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DATE_MM"+j+"  maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\" onblur=\"check_date_to("+j+")\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onblur=\"check_date_to("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('3',"+j+")>   Calendar</a> ");	
					out.println("</td> ");
					
					
				}
				
				
				
				//-------------------------------------------------------------------------------------------------------------------------------------------------
				//--if menu option is Enter Leasing--//
				
				if(m_st.equals("lease")){
					
					out.println("<td width='100' align='left' style= \"cursor:hand;cursor-color:blue\" onclick=\"show_application_detail_drill('"+rs.getString(1)+"')\"><U>"+rs.getString(1)+"<input type='hidden' name=TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\"></td>");
					
					
					if(m_screen_type.equals("new")){
						
						//Added By Nuwan De Silva 30-05-07=============================================
						//if(rs.getString(21)!=null){  //PPPPPPPPPPPPPPPPPPPPPPPP
						
						if(rs.getString(31).equals("HIREPURCH")){
							out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\"\" onblur=\"validate_agreement_no("+j+")\" style=\"width:120px;\"  disabled></td>"); 
							
							
						}
						else
						{
							
							if(rs.getString(24).equals("2")){
								out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\"\" onblur=\"validate_agreement_no("+j+")\" style=\"width:120px;\"  disabled ></td>"); //disabled // mod by udara 18-04-2016 addin disabled
								
								
							}
							
							else if(rs.getString(24).equals("1")){
								out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"validate_agreement_no("+j+")\" style=\"width:120px;\"  disabled ></td>"); //disabled // mod by udara 18-04-2016 addin disabled
								
							}
							else
							{
								out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"validate_agreement_no("+j+")\" style=\"width:120px;\"  disabled ></td>"); //disabled // mod by udara 18-04-2016 addin disabled
							}
							
						}
						
						if(rs.getString(40).equals("BALANCE_RE") || rs.getString(40).equals("-")){
							out.println("<td width='120'><input class='txt_input'  style='{text-align:left;}' type='text' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+m_fin+"\" onblur=\"makeRequest("+j+")\" style=\"width:120px;\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled //disabled //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}else{
							out.println("<td width='120'><input class='txt_input'  style='{text-align:left;}' type='text' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+m_fin+"\" onblur=\"makeRequest("+j+")\" style=\"width:120px;\" disabled></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}
						out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(17)+"')\" ><U>"+rs.getString(2)+"</U></td>"); 
						
						
					}
					else if(m_screen_type.equals("edit")){
						//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
						
						
						//Added By Nuwan De Silva 30-05-07=============================================
						if(rs.getString(21)!=null){
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+rs.getString(21)+"')\"><U>"+rs.getString(21)+"<input class='txt_input' type='hidden' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"makeRequest("+j+")\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
							
						}
						else if(rs.getString(21)==null){
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+rs.getString(21)+"')\"><U>-</U><input class='txt_input' type='hidden' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"makeRequest("+j+")\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
							
						}
						//===========================================================================
						
						if(rs.getString(40).equals("BALANCE_RE") || rs.getString(40).equals("-")){
							out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(3)+"\" onblur=\"makeRequest("+j+")\" style=\"width:120px;\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled //disabled//MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}else{
							out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(3)+"\" onblur=\"makeRequest("+j+")\" style=\"width:120px;\" disabled></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}
						out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(17)+"')\" ><U>"+rs.getString(2)+"</U></td>"); 
						
					}
					
					
					if(m_screen_type.equals("new") || m_screen_type.equals("edit")){
						
						if(rs.getString(31).equals("HIREPURCH")){
							
							out.println("<td width=\"170\" ><input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_DD"+j+" maxlength=\"2\" size=\"2\"          value=\""+rs.getString(4)+"\" onblur=\"check_date(document.Form1.TXT_DATE_ML_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled>");
							out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(5)+"\" onblur=\"check_date(document.Form1.TXT_DATE_ML_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled>");
							out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(6)+"\" onblur=\"check_date(document.Form1.TXT_DATE_ML_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+") disabled >   Calendar</a> ");	
							out.println("</td> ");
							
						}
						
						else
						{
							// commented by udara 18-04-2016
							/*
							if(rs.getString(24).equals("2")){
								out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\"              onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_MM"+j+"  maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\"                               onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\"                                onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
								out.println("</td> ");
								
							}
							
							else if(rs.getString(24).equals("1")){
								out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(25)+"\"         onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_MM"+j+"  maxlength=\"2\" size=\"2\" value=\""+rs.getString(26)+"\"                          onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(27)+"\"                           onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
								out.println("</td> ");
								
							}
							else
							{
								
								out.println("<td width=\"170\" ><input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(25)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(26)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" >");
								out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(27)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
								out.println("</td> ");
								
							}
							*/
							
							
							// added by udara 18-04-2016
							out.println("<td width=\"170\" ><input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(4)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled >"); // mod by udara 18-04-2016 addin disabled
							out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(5)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled >"); // mod by udara 18-04-2016 addin disabled
							out.println("                            <input class=\"txt_input5\" type=\"text\" name=TXT_ML_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(6)+"\" onblur=\"check_date(document.Form1.TXT_ML_DATE_DD"+j+",document.Form1.TXT_ML_DATE_MM"+j+",document.Form1.TXT_ML_DATE_YY"+j+")\" disabled ><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+") disabled >   Calendar</a> ");	 // mod by udara 18-04-2016 addin disabled
							out.println("</td> ");
							
							
						}
						
						
						out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_AGR_DATE_DD"+j+" maxlength=\"2\" size=\"2\"  value=\""+rs.getString(32)+"\" onblur=\"check_date(document.Form1.TXT_AGR_DATE_DD"+j+",document.Form1.TXT_AGR_DATE_MM"+j+",document.Form1.TXT_AGR_DATE_YY"+j+")\" disabled >"); // mod by udara 18-04-2016 addin disabled
						out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_AGR_DATE_MM"+j+" maxlength=\"2\" size=\"2\"  value=\""+rs.getString(33)+"\" onblur=\"check_date(document.Form1.TXT_AGR_DATE_DD"+j+",document.Form1.TXT_AGR_DATE_MM"+j+",document.Form1.TXT_AGR_DATE_YY"+j+")\" disabled >"); // mod by udara 18-04-2016 addin disabled
						out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_AGR_DATE_YY"+j+" maxlength=\"4\" size=\"4\"  value=\""+rs.getString(34)+"\" onblur=\"check_date(document.Form1.TXT_AGR_DATE_DD"+j+",document.Form1.TXT_AGR_DATE_MM"+j+",document.Form1.TXT_AGR_DATE_YY"+j+")\" disabled ><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+") disabled >   Calendar</a> ");	 // mod by udara 18-04-2016 addin disabled
						out.println("</td> ");
						
						if(rs.getString(40).equals("-")){
							out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+"); \" disabled >"); // mod by udara 18-04-2016 addin disabled
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+"); \" disabled >"); // mod by udara 18-04-2016 addin disabled
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+");chek_termi_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+","+j+");\" disabled ><a href style='{cursor:hand; }' onclick=load_calendar('3',"+j+") disabled >   Calendar</a> "); // mod by udara 18-04-2016 addin disabled	
							out.println("</td> ");
						}else if(rs.getString(40).equals("BALANCE_RE")){
							out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(44)+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled>");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(45)+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled>");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(46)+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled><a href style='{cursor:hand; }' onclick=load_calendar('3',"+j+") disabled >   Calendar</a> ");	
							out.println("</td> ");
						}else{
							out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled>");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled>");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onblur=\"check_date(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+")\" disabled><a href style='{cursor:hand; }' onclick=load_calendar('3',"+j+") disabled >   Calendar</a> ");	
							out.println("</td> ");
						}
						
						//ADDED BY NUWAN  DE SILVA 06-06-07===============================
						if(m_screen_type.equals("new") ) {
							
							String m_next_date_dd="";
							String m_next_date_mm ="";
							String m_next_date_yy="";
							
							if(!rs.getString(40).equals("-")){//Added By sandun on 16-12-2008
								m_next_date_dd = rs.getString(41);
								m_next_date_mm = rs.getString(42);
								m_next_date_yy = rs.getString(43);
							}
							else{
								m_next_date_dd = rs.getString(28);
								m_next_date_mm = rs.getString(29);
								m_next_date_yy = rs.getString(30);
							}
							
							out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_next_date_dd+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled	
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+m_next_date_mm+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled	
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_next_date_yy+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")  >   Calendar</a> ");	// mod by udara 18-04-2016 addin disabled	 
							out.println("</td> ");
							/*}
							else if(rs.getString(40).equals(" ")){
							out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(28)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(29)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
							out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(30)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")>   Calendar</a> ");	
							out.println("</td> ");
						}*/
							
							if(rs.getString(2)!=null){
								out.println("<td width='100' ><input class='txt_input2' style='{text-align:left;}'type='text' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\""+rs.getString(2)+"\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled	
							}
							else if(rs.getString(2)==null){
								out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\"\" disabled ></td>"); // mod by udara 18-04-2016 addin disabled	
							}
							
							/*if(rs.getString(17)!=null){
							out.println("<td width='100' ><input class='txt_input2' style='{text-align:left;}'type='text' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\""+rs.getString(18)+"\" ></td>"); 
							}
								
							else if(rs.getString(17)==null){
							out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\"\" ></td>"); 
							}
							
							if(rs.getString(19)!=null){
							out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\""+rs.getString(19)+"\" ></td>"); 
							}
							else if(rs.getString(19)==null){
							out.println("<td width='100''><input class='txt_input2'  style='{text-align:left;}type='text' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\"\" ></td>"); 
							}		
							
							
							if(rs.getString(20)!=null){
							out.println("<td width='100'><input class='txt_input2'  style='{text-align:left;}'type='text' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\""+rs.getString(20)+"\" ></td>"); 
							}
							else if(rs.getString(20)==null){
							out.println("<td width='100'><input class='txt_input2'  style='{text-align:left;}'type='text' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\"\" ></td>"); 
							}*/
							
							
						}	
						if(m_screen_type.equals("edit") ) {
							//out.println("@@"+rs.getString(28)+"@@"+rs.getString(29)+"@@"+rs.getString(30));
							
							/*if(rs.getString(35)!=null || rs.getString(36)!=null || rs.getString(37)!=null){
										out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(35)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
									out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(36)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
									out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(37)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")>   Calendar</a> ");	
									out.println("</td> ");
						}
							if(rs.getString(35)==null || rs.getString(36)==null || rs.getString(37)==null){
										out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
									out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" >");
									out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\" ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")>   Calendar</a> ");	
									out.println("</td> ");
						}*/
							if(rs.getString(35)!=null || rs.getString(36)!=null || rs.getString(37)!=null){
								out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(35)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled	
								out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(36)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled	
								out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(37)+"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")  >   Calendar</a> ");	 // mod by udara 18-04-2016 addin disabled
								out.println("</td> ");
							}
							if(rs.getString(35)==null || rs.getString(36)==null || rs.getString(37)==null){
								out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled
								out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_MM"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  >"); // mod by udara 18-04-2016 addin disabled
								out.println("                  <input class=\"txt_input5\" type=\"text\" name=TXT_NEXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_next(document.Form1.TXT_DATE_DD"+j+",document.Form1.TXT_DATE_MM"+j+",document.Form1.TXT_DATE_YY"+j+",document.Form1.TXT_NEXT_DATE_DD"+j+",document.Form1.TXT_NEXT_DATE_MM"+j+",document.Form1.TXT_NEXT_DATE_YY"+j+")\"  ><a href style='{cursor:hand; }' onclick=load_calendar('4',"+j+")  >   Calendar</a> ");	// mod by udara 18-04-2016 addin disabled
								out.println("</td> ");
							}
							
							
							if(rs.getString(13)!=null){
								out.println("<td width='100' ><input class='txt_input2' style='{text-align:left;}'type='text' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\""+rs.getString(13)+"\" disabled ></td>");  // mod by udara 18-04-2016 addin disabled
							}
							else if(rs.getString(13)==null){
								out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\"\" disabled ></td>");  // mod by udara 18-04-2016 addin disabled
							}
							
							/*	if(rs.getString(14)!=null){
								out.println("<td width='100' ><input class='txt_input2' style='{text-align:left;}'type='text' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\""+rs.getString(14)+"\" ></td>"); 
								}
									
								else if(rs.getString(14)==null){
								out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\"\" ></td>"); 
								}
								
								if(rs.getString(15)!=null){
								out.println("<td width='100'><input class='txt_input2' style='{text-align:left;}' type='text' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\""+rs.getString(15)+"\" ></td>"); 
								}
								else if(rs.getString(15)==null){
								out.println("<td width='100''><input class='txt_input2'  style='{text-align:left;}type='text' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\"\" ></td>"); 
								}		
								
								
								if(rs.getString(16)!=null){
								out.println("<td width='100'><input class='txt_input2'  style='{text-align:left;}'type='text' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\""+rs.getString(16)+"\" ></td>"); 
								}
								else if(rs.getString(16)==null){
								out.println("<td width='100'><input class='txt_input2'  style='{text-align:left;}'type='text' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\"\" ></td>"); 
								}*/
							
							
						}
						
						
						/*	out.println("<td width=\"170\" style='{text-align:left;}'><input class='txt_input5' type='text' name=TXT_POSTED_DATE_DD_"+j+" maxlength='2' size='2' value=\""+m_postdate_dd+"\" onblur=\"check_date_po("+j+")\">"); 
							out.println("<input class='txt_input5' type='text' name=TXT_POSTED_DATE_MM_"+j+" maxlength='2' size='2'  value=\""+m_postdate_mm+"\" onblur=\"check_date_po("+j+")\">"); 
							out.println("<input class='txt_input5' type='text' name=TXT_POSTED_DATE_YY_"+j+" maxlength='4' size='4'  value=\""+m_postdate_yy+"\" onblur=\"check_date_po("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")>   Calendar</a></td>"); 
					*/
						/*out.println("<td width=\"170\"><input class=\"txt_input5\" type=\"text\" name=TXT_DATE_DD"+j+" maxlength=\"2\" size=\"2\" value=\""+m_date_dd+"\" onblur=\"check_date_to("+j+")\">");
						out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DATE_MM"+j+"  maxlength=\"2\" size=\"2\" value=\""+m_date_mm+"\" onblur=\"check_date_to("+j+")\">");
						out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_DATE_YY"+j+" maxlength=\"4\" size=\"4\" value=\""+m_date_yy+"\" onblur=\"check_date_to("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
						out.println("</td> ");
						*/
						
					}
					//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
					else if(m_screen_type.equals("reverse")){
						
						//Added By Nuwan De Silva 30-05-07=============================================
						if(rs.getString(21)!=null){
							
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+rs.getString(21)+"')\"><U>"+rs.getString(21)+"<input class='txt_input' type='hidden' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"makeRequest("+j+")\"></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
							
							/*if(rs.getString(24).equals("0")){
							out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"\" style=\"width:120px;\"  ></td>"); 
							}
							else
							{
							out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"\" disabled style=\"width:120px;\"  ></td>"); 
							}
							*/
						}
						else if(rs.getString(21)==null){
							//out.println("<td width='120'><input class='txt_input' style='{text-align:left;}'type='text' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\"\" onblur=\"\" style=\"width:120px;\"  ></td>"); //
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_master_lease_agreement_drill('"+rs.getString(21)+"')\"><U>-<input class='txt_input' type='hidden' name=TXT_M_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(21)+"\" onblur=\"makeRequest("+j+")\"></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}
						
						
						//===========================================================================
						
						
						
						//out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(3)+"\" onblur=\"makeRequest("+j+")\"></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						if(rs.getString(40).equals("BALANCE_RE") || rs.getString(40).equals("-")){
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(3)+"\" onblur=\"makeRequest("+j+")\"></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}else{
							out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_finance_detail_drill('"+rs.getString(3)+"')\"><U>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" maxlength='20'  value=\""+rs.getString(3)+"\" onblur=\"makeRequest("+j+")\" disabled></td>"); //MODIFIED MAXLENGTH TO 20 NUWAN DE SILVA 22-05-07
						}
						out.println("<td width='120' style= \"text-align:left;cursor:hand;cursor-color:blue\" onclick=\"show_client('"+rs.getString(17)+"')\" ><U>"+rs.getString(2)+"</U></td>"); 			
						if(rs.getString(13)!=null){
							out.println("<td width='100' style='{text-align:left;}'>"+rs.getString(13)+"<input class='txt_input2' type='hidden' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\""+rs.getString(13)+"\" ></td>"); 
						}
						
						else if(rs.getString(13)==null){
							out.println("<td width='100' style='{text-align:left;}'><input class='txt_input2' type='hidden' name=TXT_POSTED_TO_"+j+" maxlength='200'  value=\"\" ></td>"); 
						}
						
						/*	if(rs.getString(14)!=null){
							out.println("<td width='100' style='{text-align:left;}'>"+rs.getString(14)+"<input class='txt_input2' type='hidden' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\""+rs.getString(14)+"\" ></td>"); 
							}
								
							else if(rs.getString(14)==null){
							out.println("<td width='100' style='{text-align:left;}'><input class='txt_input2' type='hidden' name=TXT_POSTED_ADD1_"+j+" maxlength='100'  value=\"\" ></td>"); 
							}
							if(rs.getString(15)!=null){
							out.println("<td width='100' style='{text-align:left;}'>"+rs.getString(15)+"<input class='txt_input2' type='hidden' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\""+rs.getString(15)+"\" ></td>"); 
							}
							else if(rs.getString(15)==null){
							out.println("<td width='100' style='{text-align:left;}'><input class='txt_input2' type='hidden' name=TXT_POSTED_ADD2_"+j+" maxlength='100'  value=\"\" ></td>"); 
							}		
							if(rs.getString(16)!=null){
							out.println("<td width='100' style='{text-align:left;}'>"+rs.getString(16)+"<input class='txt_input2' type='hidden' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\""+rs.getString(16)+"\" ></td>"); 
							}
							else if(rs.getString(16)==null){
							out.println("<td width='100' style='{text-align:left;}'><input class='txt_input2' type='hidden' name=TXT_POSTED_TEL_"+j+" maxlength='60'  value=\"\" ></td>"); 
							}
							*/
						
						//out.println("<td width=\"150\" style='{text-align:left;}'>"+m_postdate_dd+"-"+m_postdate_mm+"-"+m_postdate_yy+"</td>"); 
						
						
						out.println("<td width=\"150\" style='{text-align:left;}'>"+rs.getString(22)+"</td>"); 
						out.println("<td width=\"150\" style='{text-align:left;}'>"+rs.getString(39)+"</td>"); 
						
						if(m_date_dd!=null || m_date_mm!=null || m_date_yy!=null){
							out.println("<td width=\"150\" style='{text-align:left;}'>"+m_date_dd+"-"+m_date_mm+"-"+m_date_yy+"</td>"); 
						}
						if(m_date_dd==null || m_date_mm==null || m_date_yy==null){
							out.println("<td width=\"150\" style='{text-align:left;}'></td>"); 
						}
						if(rs.getString(38)!=null){
							out.println("<td width=\"150\" style='{text-align:left;}'>"+rs.getString(38)+"</td>"); 
						}
						if(rs.getString(38)==null){
							out.println("<td width=\"150\" style='{text-align:left;}'></td>"); 
						}
						
						out.println("<input type='hidden' name=TXT_POSTED_DATE_DD_"+j+"  value=\""+m_postdate_dd+"\" onblur=\"check_date_po("+j+")\">"); 
						out.println("<input type='hidden' name=TXT_POSTED_DATE_MM_"+j+"  value=\""+m_postdate_mm+"\" onblur=\"check_date_po("+j+")\">"); 
						out.println("<input type='hidden' name=TXT_POSTED_DATE_YY_"+j+"  value=\""+m_postdate_yy+"\" onblur=\"check_date_po("+j+")\">"); 
						
						out.println("<input  type=\"hidden\" name=TXT_DATE_DD"+j+"  value=\""+m_date_dd+"\" onblur=\"check_date_to("+j+")\">");
						out.println("<input  type=\"hidden\" name=TXT_DATE_MM"+j+"  value=\""+m_date_mm+"\" onblur=\"check_date_to("+j+")\">");
						out.println("<input  type=\"hidden\" name=TXT_DATE_YY"+j+"  value=\""+m_date_yy+"\" onblur=\"check_date_to("+j+")\">");	
						
						
					}
					//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
					out.println("<input  type=\"hidden\" name=HID_TXT_CLIENT_CODE"+j+"  value=\""+rs.getString(17)+"\" >");	
					out.println("<input  type=\"hidden\" name=HID_TRANSACTION_TYPE"+j+"  value=\""+rs.getString(31)+"\" >");	
					
				}
				//-------------------------------------------------------------------------------------------------------------------------------------------------
				
				
				out.println("<td width='50' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change("+j+")\"></td>"); 
				j=j+1;
			}
			
			out.println("<tr>");
			out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
			out.println("</tr>");
			
			
			
			
			
			
			//*********************************************************************************************************************************************************************************************************************************
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
