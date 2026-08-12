//--
//SCREEN NAME:Credit Process - Payment Higer Approval - NEW
//CREATED BY :delanjli
//DATE/TIME  :
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Autherization_higher_Pay_1 extends javax.servlet.http.HttpServlet { 
Connection conn;
	ServletOutputStream out = null;
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
			String m_level="";
			String m_sus_ref = req.getParameter("SUS_REF_NO");
			String m_ref = req.getParameter("REF_NO");

			String m_chksql = req.getParameter("chksql");
			String m_chksql1 = req.getParameter("chksql2");
			
			//out.println("m_chksql"+m_chksql);
			//out.println("m_chksql1"+m_chksql1);
			
			String m_username =  m_sn_methods.username;
			String m_cls="2";
			if(m_cls!=null){
			m_cls = req.getParameter("cls");
			}
			
			
			
			if(m_chksql1.equals("R")){
			m_level="Requisition Approval";
			}
			else if(m_chksql1.equals("B")){
			m_level="Approval 1";
			}
			else if(m_chksql1.equals("A")){
			m_level="Approval 2";
			}

			
			String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "ASC";
			if(req.getParameter("st_c")!=null && req.getParameter("oby")!=null){
			m_sort_column = req.getParameter("st_c");
			m_order_by_type = req.getParameter("oby");
			}

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Payment Higer "+m_level+"</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var st_val='';");
			out.println("var st_val1='';");
			out.println("st_val='"+m_chksql+"'");//VERIFY
			out.println("st_val1='"+m_chksql1+"'");//B
			out.println("var m_order_by_type");
			out.println("var row_arry=new Array();");
			out.println("var st_des1='';");
			out.println("var st_des2='';");
			out.println("var chk_chng=1");
			out.println("var m_row3=''");
			out.println("var m_row=''");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("				display_fields(data_vec);");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("				display_fields(data_vec);");
			out.println("check_box()");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("change1.innerHTML=\"\"");
			out.println("			}");
			out.println("}");
			
			out.println("function makeRequest(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; "); 
			out.println("nval=''");
			
			out.println("if(st_val=='A'){");
			out.println("st_des1='APPRO2'");
			out.println("}");
			out.println("else if(st_val=='B'){");
			out.println("st_des1='APPRO1'");
			out.println("}");
			out.println("else if(st_val=='R'){");
			out.println("st_des1='RE-APP'");
			out.println("}");
			out.println("else if(st_val=='VERIFY'){");
			out.println("st_des1='VERIFY'");
			out.println("}");
			out.println("if(st_val1=='A'){");
			out.println("st_des2='APPRO2'");
			out.println("}");
			out.println("else if(st_val1=='B'){");
			out.println("st_des2='APPRO1'");
			out.println("}");
			//*
			out.println("else if(st_val1=='R'){");
			out.println("st_des2='RE-APP'");
			out.println("}");
			//*
			
			out.println("else if(st_val1=='VERIFY'){");
			out.println("st_des2='VERIFY'");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("if(st_val=='VERIFY'){");
			out.println("m_pay=''");
			out.println("}");
			out.println("else if(st_val=='B'){");
			out.println("m_pay='APPRO1'");
			out.println("}");
			out.println("else if(st_val=='R'){");
			out.println("m_pay=''");
			out.println("}");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Autherization_higher_Pay_1_sort&screen_type="+m_level+"&app_status=ACTIVATED&payment_status=\"+m_pay+\"&data_val=\"+nval+\"&ac_status=\"+st_des1+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
		//out.println("window.open(m_url)");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("if(st_val=='B'){");
			out.println("m_pay='APPRO2'");
			out.println("}");
			out.println("else if(st_val=='A'){");
			out.println("m_pay='APPRO1'");
			out.println("}");
			out.println("else if(st_val=='R'){");
			out.println("m_pay=''");
			out.println("}");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Autherization_higher_Pay_1_sort&screen_type="+m_level+"&app_status=ACTIVATED&payment_status=\"+m_pay+\"&data_val=\"+nval+\"&ac_status=\"+st_des2+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_PURCHASE_ORDER_NO.value==\"\"){  "); 
			out.println("DIV_TXT_PURCHASE_ORDER_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_TOTAL_NET.value==\"\"){  "); 
			out.println("DIV_TXT_TOTAL_NET.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_VENDOR_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_VENDOR_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_FULL_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_FULL_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for(var d=0;d<document.Form1.hid_no.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("if(document.Form1.elements[chk].checked==false){"); 
			out.println("chk_chng=chk_chng*1");
			out.println("} ");
			out.println("else if (document.Form1.elements[chk].checked==true){");
			out.println("chk_chng=chk_chng*-1");
			out.println("		}"); 
			out.println("		}");
			out.println("		if(chk_chng<=0){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_details?type=H&number='+document.Form1.hid_no.value+'&actst1='+st_val+'&actst2='+st_val1+'';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} ");
			out.println("} ");
			
			out.println("function load_lock(){	"); 
		//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Autherization_higher_Pay_1?chksql='+st_val+'&chksql2='+st_val1+'';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Autherization_higher_Pay_1?chksql='+st_val+'&chksql2='+st_val1+'&st_c="+m_sort_column+"&oby="+m_order_by_type+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

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
			out.println("help_box.innerHTML=\" Credit Process - Payment Higer  "+m_level+"- \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Payment  Higher "+m_level+"- \"+document.Form1.hid_status.value;"); 
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
			
			out.println("function help_button_1(row) {"); 
			out.println("pur=\"TXT_PURCHASE_ORDER_NO_\"+row");
			out.println("if(st_val1=='B'){");
			out.println("		if(confirm(\"Are you sure you want to view Approval level 1?\")){ "); 
			out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details_screen1?type=H&PO_NO='+document.Form1.elements[pur].value+'&REQ_NO='+document.Form1.elements[\"TXT_REQU_\"+row].value+'&CLSTATUS=A&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&st_val="+m_chksql+"&st_val1="+m_chksql1+"';"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else if(st_val1=='A'){");
			out.println("		if(confirm(\"Are you sure you want to view Approval level 2?\")){ "); 
			out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details_1?type=H&PO_NO='+document.Form1.elements[pur].value+'&REQ_NO='+document.Form1.elements[\"TXT_REQU_\"+row].value+'&CLSTATUS=A&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&st_val="+m_chksql+"&st_val1="+m_chksql1+"';"); 
			
			out.println(" window.location.href=m_url;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else if(st_val1=='R'){");
			out.println("		if(confirm(\"Are you sure you want to view Requisition Approval level ?\")){ "); 
			out.println("	m_url = '"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details?PO_NO='+document.Form1.elements[pur].value+'&REQ_NO='+document.Form1.elements[\"TXT_REQU_\"+row].value+'&CLSTATUS=A&SUS_REF_NO='+document.Form1.elements[\"TXT_SUS_REF_NO_\"+row].value+'&REF_NO='+document.Form1.elements[\"TXT_REF_NO_\"+row].value+'&st_val="+m_chksql+"&st_val1="+m_chksql1+"';"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			out.println(""); 


			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_VENDOR_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_VENDOR_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_PURCHASE_ORDER_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_PURCHASE_ORDER_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_PURCHASE_ORDER_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TOTAL_NET.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_VENDOR_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_FULL_NAME.value=oBj.valout[8];"); 
			out.println("}"); 
			
			
			out.println("function header(){");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("m_header='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+"); 

			out.println("'<tr class=\"pdn_txtpos2\">'+");
			out.println("'<td width=\"11%\" align=center style=cursor:hand; title=\"Click here to sort by - Application No  \" onclick=sort_data(\"APPLICATION_NO\")>Application No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Purchase Order No  \" onclick=sort_data(\"PURCHASE_ORDER_NO\")>P/O No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Finance No  \" onclick=sort_data(\"FINANCE_NO\")>Finance No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Supplier Name  \" onclick=sort_data(\"NAME\")>Supplier Name</td>';"); 
			out.println("if(st_val!='B'){");
			out.println("m_header=m_header+'<td width=\"15%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Client Name  \" onclick=sort_data(\"FULL_NAME\")>Client Name</td>';"); 
			out.println("}");
		  out.println("else{");
			out.println("m_header=m_header+'<td width=\"10%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Client Name  \" onclick=sort_data(\"FULL_NAME\")>Client Name</td>';"); 
			out.println("}");
			out.println("m_header=m_header+'<td width=\"10%\" align=\"right\" style=cursor:hand; title=\"Click here to sort by - Net Amount  \" onclick=sort_data(\"TOTAL_NET\")>Net</td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" style=cursor:hand; title=\"Click here to sort by - Total Amount  \" onclick=sort_data(\"TOTAL\")>Total</td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Purchase Order Date  \" onclick=sort_data(\"PURCHASE_ORDER_DATE\")>P/O Date</td>'+"); 
			out.println("'<td width=\"6%\" align=\"center\">User</td>';"); 
			out.println("if(st_val!='B'){");
			out.println("m_header=m_header+'<td width=\"7%\" align=\"center\">&nbsp</td>';");
		  out.println("}");
			out.println("else{");
			out.println("m_header=m_header+'<td width=\"5%\" align=\"center\">&nbsp</td>'+"); 
			out.println("'<td width=\"7%\" align=\"center\">&nbsp</td>';");
			out.println("}");
			out.println("m_header=m_header+'</tr >'+");
			out.println("'</table >';");
			out.println("change1.innerHTML=m_header");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");	
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+"); 
			out.println("'<tr class=\"pdn_txtpos2\">'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Application No  \" onclick=sort_data(\"APPLICATION_NO\")>Application No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Purchase Order No  \" onclick=sort_data(\"PURCHASE_ORDER_NO\")>P/O No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Finance No  \" onclick=sort_data(\"FINANCE_NO\")>Finance No</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Supplier Name  \" onclick=sort_data(\"NAME\")>Supplier Name</td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Client Name  \" onclick=sort_data(\"FULL_NAME\")>Client Name</td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" 	style=cursor:hand; title=\"Click here to sort by - Net Amount  \" onclick=sort_data(\"TOTAL_NET\")>Net</td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" style=cursor:hand; title=\"Click here to sort by - Vat Amount  \" onclick=sort_data(\"TOTAL_VAT\")>VAT</td>'+"); 
			out.println("'<td width=\"8%\" align=\"right\" style=cursor:hand; title=\"Click here to sort by - Total Amount  \" onclick=sort_data(\"TOTAL\")>Total</td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\" style=cursor:hand; title=\"Click here to sort by - Purchase Order Date  \" onclick=sort_data(\"PURCHASE_ORDER_DATE\")>P/O Date</td>'+"); 
			out.println("'<td width=\"6%\" align=\"center\">User</td>'+"); 
			out.println("'<td width=\"4%\" align=\"center\">&nbsp</td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("}");
			out.println("}");
	
	
			out.println("function display_fields(data_vec){");
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("header();");
			out.println("m_row = '';");
			out.println("while(i<data_vec.length){");
			
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("if(j>0 && j%2==1){");
			out.println("m_row='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><tr class=\"tr_input1\"><td width=\"11%\">'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_APPLICATION_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\">'+data_vec[i]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\">'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_FINANCE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" >'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_NAME_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>';"); 
			out.println("if(st_val!='B'){");
			out.println("m_row=m_row+'<td width=\"15%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>';"); 
			out.println("}");
			out.println("else{");
			out.println("m_row=m_row+'<td width=\"10%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>';"); 
			out.println("}");
			out.println("m_row=m_row+'<td width=\"10%\" align=\"right\" >'+data_vec[i+6]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_NET_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+8]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_TOTAL_NET_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\" >'+data_vec[i+9]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PO_DATE_'+j+' value=\"'+data_vec[i+9]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"6%\" align=\"left\" >'+data_vec[i+12]+'</td>';");
			out.println("if(st_val!='B'){");
			out.println("m_row=m_row+'<input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"><input type=\"hidden\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"></td>'+"); 
			out.println("'<td width=\"7%\" align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 50px\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"></td></tr></table>';"); 
			out.println("}");
			out.println("else{");
			out.println("m_row=m_row+'<td width=\"5%\" align=\"center\" ><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"></td>'+"); 
			out.println("'<td width=\"7%\" align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 50px\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"><input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"></td></tr></table>';"); 
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("m_row='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\"><tr class=\"tr_input\"><td width=\"11%\">'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_APPLICATION_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\">'+data_vec[i]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\">'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_FINANCE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" >'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_NAME_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>';"); 
			out.println("if(st_val!='B'){");
			out.println("m_row=m_row+'<td width=\"15%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>';"); 
			out.println("}");
			out.println("else{");
			out.println("m_row=m_row+'<td width=\"10%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>';"); 
			out.println("}");
			out.println("m_row=m_row+'<td width=\"10%\" align=\"right\" >'+data_vec[i+6]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_NET_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+8]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_TOTAL_NET_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\" >'+data_vec[i+9]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PO_DATE_'+j+' value=\"'+data_vec[i+9]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"6%\" align=\"left\" >'+data_vec[i+12]+'</td>';");
			out.println("if(st_val!='B'){");
			out.println("m_row=m_row+'<input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"><input type=\"hidden\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"></td>'+"); 
			out.println("'<td width=\"7%\" align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 50px\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"></td></tr></table>';"); 
			out.println("}");
			out.println("else{");
			out.println("m_row=m_row+'<td width=\"5%\" align=\"center\" ><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"></td>'+"); 
			out.println("'<td width=\"7%\" align=\"center\" ><input class=\"but_input\" type=\"button\" style=\"width: 50px\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"><input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"></td></tr></table>';"); 
			out.println("}");
			out.println("}");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("if(j>0 && j%2==1){");
			out.println("m_row='<table align=\"center\" width=\"100%\"  border=\"0\" class=\"table\"><tr class=\"tr_input1\"><td width=\"11%\" >'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_APPLICATION_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" >'+data_vec[i]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" >'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_FINANCE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" >'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_NAME_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+6]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+7]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_NET_'+j+' value=\"'+data_vec[i+7]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"right\" >'+data_vec[i+8]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_TOTAL_NET_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\"  >'+data_vec[i+9]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PO_DATE_'+j+' value=\"'+data_vec[i+9]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"6%\" align=\"left\" >'+data_vec[i+12]+'</td>';");
			out.println("m_row=m_row+'<td width=\"4%\" align=\"center\" ><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"><input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"><input class=\"but_input\" style=\"width: 25px\" type=\"hidden\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"><input type=\"hidden\" name=TXT_SETTLE_'+j+' value=\"'+data_vec[i+15]+'\"></td></tr></table>';");
			out.println("}");
			out.println("else{");
			out.println("m_row='<table align=\"center\" width=\"100%\"  border=\"0\" class=\"table\"><tr class=\"tr_input\"><td width=\"11%\" >'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_APPLICATION_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" >'+data_vec[i]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"11%\" >'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_FINANCE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"11%\" align=\"left\" >'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_NAME_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" >'+data_vec[i+5]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_FULL_NAME_'+j+' value=\"'+data_vec[i+5]+'\" maxlength=\"250\" size=\"250\"><input type=\"hidden\" name=TXT_VENDOR_CODE_'+j+' value=\"\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+6]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_VAT_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"right\" >'+data_vec[i+7]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_NET_'+j+' value=\"'+data_vec[i+7]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"right\" >'+data_vec[i+8]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_TOTAL_NET_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"8%\" align=\"left\"  >'+data_vec[i+9]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PO_DATE_'+j+' value=\"'+data_vec[i+9]+'\" maxlength=\"25\" size=\"25\"></td>'+"); 
			out.println("'<td width=\"6%\" align=\"left\" >'+data_vec[i+12]+'</td>';");
			out.println("m_row=m_row+'<td width=\"4%\" align=\"center\" ><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\" ><input type=\"hidden\" name=TXT_SUS_REF_NO_'+j+' VALUE=\"'+data_vec[i+16]+'\"><input type=\"hidden\" name=TXT_REF_NO_'+j+' VALUE=\"'+data_vec[i+17]+'\"><input class=\"but_input\" style=\"width: 25px\" type=\"hidden\" name=BUT_VIEW_'+j+' value=\"Approve\" onClick=\"help_button_1('+j+')\"><input type=\"hidden\" name=TXT_REQU_'+j+' value=\"'+data_vec[i+14]+'\"><input type=\"hidden\" name=TXT_SETTLE_'+j+' value=\"'+data_vec[i+15]+'\"></td></tr></table>';");
			out.println("}");
			out.println("}");
			out.println("change1.innerHTML+='<table align=\"center\" border\"1\" width=\"100%\" class=\"table\">'+");
	    out.println("m_row+'</table>'+'<table align=\"center\" border\"1\" width=\"100%\" class=\"table\">'+");
	   	out.println("'</table>';");
			out.println("j=j+1;");
			out.println("i=i+18;");
			out.println("}");
			out.println("document.Form1.hid_no.value=j");	
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
			
			out.println("function check_box() {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");	
			out.println("for(var d=0;d<document.Form1.hid_no.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("  vw=\"BUT_VIEW_\"+d;");
			out.println("    document.Form1.elements[chk].disabled=false;"); 
			out.println("    document.Form1.elements[vw].disabled=true;");
			out.println("    document.Form1.bt_save.disabled=false;");
			out.println("}");
			out.println("}"); 
			out.println("}"); 
			
		
			out.println("function sort_data(m_sort_col) {");
			out.println("var m_bk=1");
			out.println("	 if(m_sort_col='"+m_sort_column+"'){");
			out.println("	   if(data_vec[11]=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; "); 
			out.println("    }");
			out.println(" else if (data_vec[11]=='ASC'){");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }");
			out.println("else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("nval=''");
			out.println("if(st_val=='A'){");
			out.println("st_des1='APPRO2'");
			out.println("}");
			out.println("else if(st_val=='B'){");
			out.println("st_des1='APPRO1'");
			out.println("}");
			out.println("else if(st_val=='R'){");
			out.println("st_des1='RE-APP'");
			out.println("}");
			out.println("else if(st_val=='VERIFY'){");
			out.println("st_des1='VERIFY'");
			out.println("}");
			out.println("if(st_val1=='A'){");
			out.println("st_des2='APPRO2'");
			out.println("m_pay='APPRO2'");
			out.println("}");
			out.println("else if(st_val1=='B'){");
			out.println("st_des2='APPRO1'");
			out.println("m_pay='APPRO1'");
			out.println("}");
			out.println("else if(st_val1=='R'){");
			out.println("st_des2=''");
			out.println("m_pay=''");
			out.println("}");
			out.println("else if(st_val1=='VERIFY'){");
			out.println("st_des2='VERIFY'");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Autherization_higher_Pay_1_sort&screen_type="+m_level+"&app_status=ACTIVATED&payment_status=\"+m_pay+\"&data_val=\"+nval+\"&ac_status=\"+st_des1+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Autherization_higher_Pay_1_sort&screen_type="+m_level+"&app_status=ACTIVATED&payment_status=\"+m_pay+\"&data_val=\"+nval+\"&ac_status=\"+st_des2+\"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			out.println("function close_window_1() {");
			out.println("close_window()");
			out.println("}");
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest('APPLICATION_NO')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Payment Higher  "+m_level+"- New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\"),makeRequest(\"APPLICATION_NO\")' name=\"bt_dele\" value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			if(m_chksql.equals("B")){
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" ></td>");  
			}
			if(!m_chksql.equals("B")){
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
			}
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window_1(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("<br>");
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_top     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_bot);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
			out.println("<td width='100%'><div id=change1></div></td></tr></table>");
			out.println("<br>"); 
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_bot     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_top);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"EDIT\"),makeRequest(\"APPLICATION_NO\")' name=\"bt_dele\" value=\"Delete\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" name=bt_save class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' onClick='save_window()' value=\"Save\" disabled></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window_1(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  

			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 

			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
