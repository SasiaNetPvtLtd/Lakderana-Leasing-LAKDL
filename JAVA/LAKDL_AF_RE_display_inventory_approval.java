//--
//SCREEN NAME :INVENTORY APPROVAL
//CREATED BY	:DELANJALI
//DATE/TIME		:2007-02-27
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_RE_display_inventory_approval extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	Connection conn;
	String m_chksql="";
	java.text.NumberFormat nf,nf1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn=m_sn_methods.met_user_validate(req);
			stmt=conn.createStatement();
			
			m_chksql=req.getParameter("chksql");
			String m_schema_name=m_sn_methods.schema_name;
			
			if(m_chksql.equals("main_page")){
				
				String m_sort_column   = "A.INVENTORY_NO";	
				String m_order_by_type = "ASC";
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Repossesion-Vehicle Inventory Approval</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("var b_flag=0");
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("				alert('Record already exsist');");
				out.println("				new_window();");
				out.println("			}");
				out.println("}");
				out.println("function makeRequest(obj) {");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_inventory_approval&data_val=\"+obj.value;");
				out.println("load_interface(m_url,'XML');");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\"){  "); 
				out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_REPOSSESSION_NO.value==\"\"){  "); 
				out.println("DIV_TXT_REPOSSESSION_NO.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_SEIZER_CODE.value==\"\"){  "); 
				out.println("DIV_TXT_SEIZER_CODE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_INVENTORY_NO.value==\"\"){  "); 
				out.println("DIV_TXT_INVENTORY_NO.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_YARD_CODE.value==\"\"){  "); 
				out.println("DIV_TXT_YARD_CODE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
				out.println("DIV_TXT_FINANCE_NO.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_INVOICE_AMOUNT.value==\"\"){  "); 
				out.println("DIV_TXT_INVOICE_AMOUNT.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
				
				
				out.println("function count_docs(){ ");
				out.println("count=0;");
				out.println("for(i=0;i<document.Form1.hid_count_inv.value;i++){");
				out.println("m_chk=\"chk_app_\"+i;");
				out.println("if(document.Form1.elements[m_chk].checked==true){");
				out.println("count=count+1;");
				out.println("}");		
				out.println("}");		
				out.println("if(count>0){");
				out.println("return true;");
				out.println("}"); 
				out.println("else{");
				out.println("return false;");
				out.println("}"); 
				out.println("}"); 
				
				
				
				out.println("function check_select(){ "); 
				out.println("if(!count_docs()){"); 
				out.println("alert('Please Select Inventory');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				out.println("}"); 
				
				out.println("function before_submit(){ "); 
				out.println("check_select()");
				out.println("if(b_flag!=1){");
				out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("document.Form1.elements[i].disabled=false;");
				out.println("}");
				out.println("		if(confirm(\"Are you sure you want to save?\")){ "); 
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_save_inventory_approval';");  
				out.println("		document.Form1.submit();	"); 
				out.println("		}"); 
				out.println("} ");
				out.println("} "); 
				
				
				out.println("function sort_data(m_sort_col) {");
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
				out.println("	m_url = \""+m_class_url+"/"+m_fschema_name+"AF_RE_display_inventory_approval?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_inventory_approval?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_display_inventory_approval?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
				out.println("}"); 
				out.println(""); 
				out.println(""); 
				
				out.println("function save_window(){	"); 
				out.println("before_submit();"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_display_inventory_approval\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("help_box.innerHTML=\" Collection - Repossesion-Vehicle Inventory Approval - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Collection - Repossesion-Vehicle Inventory Approval - \"+document.Form1.hid_status.value;"); 
				out.println("}"); 
				
				out.println("function load_screen_status(m_val){"); 
				out.println("if(m_val==\"NEW\"){"); 
				out.println("new_window();"); 
				out.println("}"); 
				out.println("else if(m_val==\"HELP\"){"); 
				out.println("load_help_msg();"); 
				out.println("}"); 
				out.println("else if(m_val!=\"EDIT\"){"); 
				out.println("document.Form1.TXT_REPOSSESSION_NO.disabled=true;"); 
				out.println("document.Form1.TXT_SEIZER_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_INVENTORY_NO.disabled=true;"); 
				out.println("document.Form1.TXT_YARD_CODE.disabled=true;"); 
				out.println("document.Form1.TXT_FINANCE_NO.disabled=true;"); 
				out.println("document.Form1.TXT_INVOICE_AMOUNT.disabled=true;"); 
				out.println("}"); 
				out.println("else{");
				out.println("}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
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
				out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Servlet?class_in=\"+client_name+\"AF_RE_help_select\"+"); 
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
				out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
				out.println("		help_value_assign_4();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
				out.println("		help_value_assign_5();"); 
				out.println("		}"); 
				out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
				out.println("		help_value_assign_6();"); 
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
				out.println("function help_button_1() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_1() {"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_2() {"); 
				out.println("    document.Form1.hid_help_type.value=\"2\";"); 
				out.println("    m_sql = \"m_help_TXT_REPOSSESSION_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_REPOSSESSION_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_2() {"); 
				out.println("    document.Form1.TXT_REPOSSESSION_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_3() {"); 
				out.println("    document.Form1.hid_help_type.value=\"3\";"); 
				out.println("    m_sql = \"m_help_TXT_SEIZER_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_SEIZER_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_3() {"); 
				out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_4() {"); 
				out.println("    document.Form1.hid_help_type.value=\"4\";"); 
				out.println("    m_sql = \"m_help_TXT_INVENTORY_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_INVENTORY_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_4() {"); 
				out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_5() {"); 
				out.println("    document.Form1.hid_help_type.value=\"5\";"); 
				out.println("    m_sql = \"m_help_TXT_YARD_CODE_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_YARD_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_5() {"); 
				out.println("    document.Form1.TXT_YARD_CODE.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_button_6() {"); 
				out.println("    document.Form1.hid_help_type.value=\"6\";"); 
				out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function help_value_assign_6() {"); 
				out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function help_update() {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_CLIENT_CODE_sql\";"); 
				out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    } ");
				out.println("    else{");
				out.println("    m_criteria = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"N@\";}"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_REPOSSESSION_NO.value=oBj.valout[3];"); 
				out.println("    document.Form1.TXT_SEIZER_CODE.value=oBj.valout[4];"); 
				out.println("    document.Form1.TXT_INVENTORY_NO.value=oBj.valout[5];"); 
				out.println("    document.Form1.TXT_YARD_CODE.value=oBj.valout[6];"); 
				out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[7];"); 
				out.println("    document.Form1.TXT_INVOICE_AMOUNT.value=oBj.valout[8];"); 
				out.println("}"); 
				
				
				
				out.println("function change(row) {");
				out.println("   if(document.Form1.elements[\"chk_app_\"+row].checked==true){"); 
				out.println("document.Form1.elements[\"chk_app_\"+row].value='Y'");
				out.println("}");	
				out.println("  else if(document.Form1.elements[\"chk_app_\"+row].checked==false ){"); 
				out.println("document.Form1.elements[\"chk_app_\"+row].value='N'");
				out.println("}");	
				out.println("}"); 
				
				out.println("function view(val) {"); 
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_display_inventory_approval?chksql=view&inv=\"+val+\"\";");
				out.println("   window.open(m_url,'displayWindowap','left=100,top=100,width=860,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}"); 
				
				out.println("function befor_end(m_obj) {");
				out.println("   m_obj.focus();");
				out.println("}");
				
				out.println("function load_inventory(inv_no,rep_no,code,name,fee){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Vehicle_Inventory?fee=\"+fee+\"&code=\"+code+\"&name=\"+name+\"&rep_no=\"+rep_no+\"&inv_no=\"+inv_no+\"\";");
				//out.println("location.href = m_url;");
				out.println("   window.open(m_url,'displayWindowap44','left=0,top=0,width=1000,height=1200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara 06-05-2015
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("	}");
				// end by udara 06-05-2014
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Repossesion-Vehicle Inventory Approval</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				rs = stmt.executeQuery ("SELECT A.INVENTORY_NO,A.REPOSSESSION_NO,B.FINANCE_NO,"+
					//" A.SEIZER_CODE,"+
					" DECODE(B.REPOSSESS_TYPE,'OFFICER',B.REPOSSESS_OFFICER,'SEIZER',B.SEIZER_CODE,'COMPANY',B.REPOSSESS_OFFICER)  SEIZER_CODE, "+//Sandun on 03-07-2009
					// " "+m_schema_name+".af_co_get_seizer_name(A.SEIZER_CODE) AS SEIZER_NAME,"+//Mod By Sandun on 02-07-2009
					" NVL(DECODE(B.REPOSSESS_TYPE,'OFFICER',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER),'SEIZER',"+m_schema_name+".AF_CO_GET_SEIZER_NAME(B.SEIZER_CODE),'COMPANY',"+m_schema_name+".AF_CO_GET_EMP_NAME(B.REPOSSESS_OFFICER)),'-') AS SEIZER_NAME , "+ ////Added By Sandun on 02-07-2009
					"	A.CLIENT_CODE,"+m_schema_name+".af_co_get_client_name(A.CLIENT_CODE) AS CLIENT_NAME,A.YARD_CODE, "+
					"(SELECT NAME FROM "+m_schema_name+".AF_CO_MAS_YARD WHERE YARD_CODE=A.YARD_CODE) AS YARD_NAME,TO_CHAR(B.INVOICE_AMOUNT,'9,999,999,999,999,999,999,999,999.99') AS INVOICE_AMOUNT ,NVL("+m_schema_name+".AF_CO_GET_SEIZER_FEE(A.SEIZER_CODE),0) "+
					"FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY A,"+m_schema_name+".AF_RE_PRO_REPOSSESSION B "+
					"WHERE A.INVENTORY_NO=B.INVENTORY_NO "+
					"AND A.ACTIVE_STATUS='ENT' "+
					"AND (SELECT APPLICATION_STATUS FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS WHERE FINANCE_NO=B.FINANCE_NO) NOT IN ('TERMINATED','TERMI','TERMINATE','NORM_TERMI') "+	//ADDED BY KANCHANA ON 2016-06-02 FOR ISSUE NO 20497
					"ORDER BY "+m_sort_column+" "+m_order_by_type+" ");
				
				
				out.println("<br>");
				
				
				out.println("<table align='center' width='100%' border=\"0\" class='table'>");
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=13><input type=button name=top_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(\"Top\");'></td>");
				
				out.println("<tr class=\"pdn_txtpos2\">"); 
				out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Inventory No  '    onclick=sort_data('A.INVENTORY_NO') >Inventory No</td>");
				out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Repossision No  '    onclick=sort_data('A.REPOSSESSION_NO') >Repossision No</td>");
				out.println("<td  width='11%' style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('B.FINANCE_NO') >Finance No</td>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Seizer Name  '    onclick=sort_data('SEIZER_NAME') >Seizer Name/Officer Name</td>");
				out.println("<td  width='20%' style= cursor:hand; title='Click here to sort by - Client Name  '    onclick=sort_data('CLIENT_NAME') >Client Name</td>");
				out.println("<td  width='12%' style= cursor:hand; title='Click here to sort by - Yard Name  '    onclick=sort_data('YARD_NAME') >Yard Name</td>");
				out.println("<td  width='13%' align=right style= cursor:hand; title='Click here to sort by - Invoice Amount  '    onclick=sort_data('INVOICE_AMOUNT') >Invoice Amount</td>");
				out.println("<td width='5%' style='{text-align:center;}' >Approve</td>"); 
				out.println("<td width='3%' >&nbsp</td>"); 
				// out.println("<td width='5%' >&nbsp</td></tr>"); 
				
				int j=0;
				while(rs.next()){
					if(j>0 && j%2==1){
						out.println("<tr class=tr_input1 >");
					}
					else{
						out.println("<tr class=tr_input >");
					}
					out.println("<td width='11%' style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u><input class='txt_input' type='hidden' name=TXT_INVENTORY_NO_"+j+" value=\""+rs.getString(1)+"\"></td>"); 
					out.println("<td width='11%' style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(2)+"')\"><u>"+rs.getString(2)+"</u><input class='txt_input' type='hidden' name=TXT_REPOSSESSION_NO_"+j+"  value=\""+rs.getString(2)+"\">"); 
					//out.println("<td width='11%' style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(3)+"')\" ><u>"+rs.getString(3)+"</u><input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+"  value=\""+rs.getString(3)+"\">"); // commented by udara 06-05-2015
					out.println("<td width='11%' style= cursor:hand; onClick=\"show_transaction_info('','"+rs.getString(3)+"')\" ><u>"+rs.getString(3)+"</u><input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+"  value=\""+rs.getString(3)+"\">");  // added by udara 06-05-2015
					out.println("<td width='12%' >"+rs.getString(5)+"<input class='txt_input' type='hidden' name=TXT_SEIZER_CODE_"+j+" value=\""+rs.getString(5)+"\">"); 
					out.println("<td width='20%' onclick=\"show_client('"+rs.getString(6)+"')\" style='cursor:hand'><u>"+rs.getString(7)+"</u><input class='txt_input' type='hidden' name=TXT_CLIENT_CODE_"+j+"  value=\""+rs.getString(7)+"\">"); 
					out.println("<td width='12%' >"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_YARD_CODE_"+j+" value=\""+rs.getString(9)+"\">"); 
					out.println("<td width='13%' style='{text-align:right;}'>"+rs.getString(10)+"<input class='txt_input' type='hidden' name=TXT_INVOICE_AMOUNT_"+j+"  value=\""+rs.getString(10)+"\"></td>"); 
					out.println("<td width='5%' style='{text-align:center;}'><input type='checkbox' name=chk_app_"+j+" value=\"N\" unchecked onClick=\"change("+j+")\"></td>"); 
					out.println("<td width='3%' ><input class='but_input' style=\"width:30px;\" type='button' name=BUT_TXT_INVENTORY_NO_"+j+" value=\"View\" onClick=\"view('"+rs.getString(1)+"')\" ></td>"); 
					//out.println("<td width='5%' ><input class='but_input' style=\"width:80px;\" type='button' name=BUT_VEH_INVENT_"+j+" value=\"Inventory\" onClick=\"load_inventory('"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(5)+"','"+rs.getDouble(11)+"')\" ></td>"); //Added By Sandun on 02-07-2009
					out.println("</tr>"); 
					j=j+1;
				}
				out.println("<input type=hidden name=hid_count_inv value="+j+">");
				out.println("<tr class=tr_input>");
				out.println("<td align=right colspan=13><input type=button name=end_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(\"End\");'></td>");
				
				out.println("</table>"); 
				
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				out.flush();
			}
			//-------------------------------------------------------------------------------------------------------------------
			else if(m_chksql.equals("view")){
				
				String m_inv=req.getParameter("inv");
				
				out.println("<html><head><font 12pt arial><title>Inventory Details Report</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				out.println("<br>");
				out.println("<br>");
				out.println("<table border='0' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
				out.println("<div align='center' width='180%' class='rep-body' style='{font: bold;text-align:center;}'>Inventory Details Report</div>");
				out.println("</table>");			
				out.println("<form name='form1'>");
				out.println("<table border='0' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
				out.println("</table>");			
				out.println("<HR width='100%' color='black'>");
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				
				//Modified by Mahela on 23-05-2007
				rs = stmt.executeQuery ("SELECT "+
					"INVENTORY_NO,"+//1
					"REPOSSESSION_NO,"+//2
					"VEHICLE_NO,"+//3
					"nvl(CUSTOMER_NAME,'-'),"+//4
					"nvl(ASSET_DESCRIPTION,'-'),"+//5
					"NVL(MILEAGE,0),"+//6
					"NVL(DECODE(KEY,'Y','Yes','N','No'),'-'), "+//7
					"DECODE(nvl(LICENSE,'-'),'Y','Yes','N','No'),"+//8
					"NVL(DECODE(INSURANCE,'Y','Yes','N','No'),'-'),"+//9
					"nvl(DECODE(VEHICLE_ID_CARD,'Y','Yes','N','No'),'-'),"+//10
					"nvl(DECODE(CASSETTE,'Y','Yes','N','No'),'-'),"+//11
					"NVL(DECODE(RADIO,'Y','Yes','N','No'),'-'),"+//12
					"NVL(DECODE(CD_PLAYER,'Y','Yes','N','No'),'-'), "+//13
					"NVL(DECODE(TOOL_KIT,'Y','Yes','N','No'),'-'),"+//14
					"NVL(DECODE(SPEAR_WHEEL,'Y','Yes','N','No'),'-'),"+//15
					"NVL(DECODE(JACK,'Y','Yes','N','No'),'-'),"+//16
					"NVL(DECODE(LIGHTER,'Y','Yes','N','No'),'-'),"+//17
					"NVL(DECODE(FUEL_CAP,'Y','Yes','N','No'),'-'),"+//18
					"NVL(DECODE(CARPETS,'Y','Yes','N','No'),'-'),"+//19
					//"NVL(DECODE(WHEEL,'Y','Yes','N','No'),'-'), "+//20
					"NVL(DECODE(WHEEL,'Y','Alloy','N','Cup Set'),'-'), "+//20 //mod By Sandun on 02-07-2009
					//"NVL(DECODE(BODY,'Y','Yes','N','No'),'-'),"+//21
					"NVL(DECODE(BODY,'G','Good','S','Scratched','D','Damaged'),'-'),"+//21
					"NVL(DECODE(MIRROR,'Y','Yes','N','No'),'-'),"+//22
					"NVL(DECODE(LEFT_SIDE_MIRROR,'Y','Yes','N','No'),'-'),"+//23
					"NVL(DECODE(RIGHT_SIDE_MIRROR,'Y','Yes','N','No'),'-'), "+//24
					"NVL(DECODE(LEFT_SIGNAL_LIGHT_FRONT,'Y','Yes','N','No'),'-'),"+//25
					"NVL(DECODE(RIGHT_SIGNAL_LIGHT_FRONT,'Y','Yes','N','No'),'-'),"+//26
					"NVL(DECODE(LEFT_SIGNAL_LIGHT_REAR,'Y','Yes','N','No'),'-'), "+//27
					"NVL(DECODE(RIGHT_SIGNAL_LIGHT_REAR,'Y','Yes','N','No'),'-'),"+//28
					"NVL(COMMENTS,'-'),"+//29
					"NVL(DECODE(POLICE_REPORT,'Y','Yes','N','No'),'-'),"+//30
					"NVL(DECODE(CUSTOMERS_SIGNATURE,'Y','Yes','N','No'),'-'), "+//31
					"NVL(DECODE(SEIZERS_SIGNATURE,'Y','Yes','N','No'),'-'),"+//32
					"NVL(DECODE(RECEIVERS_SIGNATURE,'Y','Yes','N','No'),'-'),"+//33
					"NVL(INITCAP(ADVERTISMENT_STATUS),'-'), "+//34
					"NVL(OFFER_STATU,'-'), "+//35
					"NVL(NO_OF_ADVERTISMENT_GEN,0),"+//36
					"NVL(COMPLETED_OFFER_NO,'-'), "+//37
					"NVL(CLIENT_CODE,'-'), "+ //38
					"NVL(DECODE(D_KEY,'Y','Yes','N','No'),'-') "+//39
					"FROM "+m_schema_name+".AF_RE_PRO_VEHICLE_INVENTORY "+
					"WHERE INVENTORY_NO =('"+m_inv+"') "+
					"ORDER BY INVENTORY_NO ASC ");
				
				int j=0;
				
				boolean more=rs.next();
				
				
				
				
				while(more){
					
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2>");
					
					out.println("<td width='20%'><b>Inventory No</td>");
					out.println("<td width='20%'><b>Repossession No</td>");
					out.println("<td width='20%' style='{text-align:left;}'><b>Vehicle No</td>");
					out.println("<td width='20%' style='{text-align:left;}'><b>Customer Name</td>");
					out.println("<td width='20%' style='{text-align:left;}'><b>Asset Description</td></tr>");
					out.println("<tr><td width=\"20%\" style= cursor:hand; onClick=\"show_inventory_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>");
					out.println("<td width=\"20%\"     style= cursor:hand; onClick=\"show_repossession_drill('"+rs.getString(2)+"')\" ><u>"+rs.getString(2)+"</u></td>");
					out.println("<td width=\"20%\" >"+rs.getString(3)+"</td>");
					out.println("<td width=\"20%\"     style= cursor:hand; onClick=\"show_client('"+rs.getString(38)+"')\" ><u>"+rs.getString(4)+"</u></td>");
					out.println("<td width=\"20%\" >"+rs.getString(5)+"</td></tr>");
					out.println("</table>");	
					out.println("<br>");
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>Mileage</td><td width=\"10%\" >: "+rs.getString(6)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>Comments</td><td width=\"10%\" >: "+rs.getString(29)+"</td></tr>");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>Advertisment Status</td><td width=\"10%\" >: "+rs.getString(34)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>Offer Status</td><td width=\"10%\" >: "+rs.getString(35)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>No of Advertisment Generated</td><td width=\"10%\" >: "+rs.getString(36)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b></td><td width='25%' style='{text-align:left;}'><b>Completed offer No </td><td width=\"10%\" >: "+rs.getString(37)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("</table>");	
					out.println("<br>");		
					out.println("<br>");		
					
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
					//out.println("<tr><td width='2%'><b>(1).</td><td width='25%' style='{text-align:left;}'><b>Key</td><td width=\"10%\" >: "+rs.getString(7)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(1).</td><td width='25%' style='{text-align:left;}'><b>Key</td><td width=\"10%\" >&nbsp;</td><td width=\"*%\" >&nbsp</td></tr>");
					//Added By Sandun on 02-07-2009
					out.println("<tr><td width='2%'><b>&nbsp;</td><td width='25%' style='{text-align:left;}'><li><b>Original Key</td><td width=\"10%\" >: "+rs.getString(7)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>&nbsp;</td><td width='25%' style='{text-align:left;}'><li><b>Duplicate Key</td><td width=\"10%\" >: "+rs.getString(39)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					
					out.println("<tr><td width='2%'><b>(2).</td><td width='25%' style='{text-align:left;}'><b>License</td><td width=\"10%\" >: "+rs.getString(8)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(3).</td><td width='25' style='{text-align:left;}'><b>Insurance</td><td width=\"10%\" >: "+rs.getString(9)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(4).</td><td width='25%' style='{text-align:left;}'><b>Vehicle Id Card</td><td width=\"10%\" >: "+rs.getString(10)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(5).</td><td width='25%' style='{text-align:left;}'><b>Cassette</td><td width=\"10%\" >: "+rs.getString(11)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr><td width='2%'><b>(6).</td><td width='25%' style='{text-align:left;}'><b>Radio</td><td width=\"10%\" >: "+rs.getString(12)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(7).</td><td width='25%' style='{text-align:left;}'><b>CD Player</td><td width=\"10%\" >: "+rs.getString(13)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(8).</td><td width='25%' style='{text-align:left;}'><b>Tool Kit</td><td width=\"10%\" >: "+rs.getString(14)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(9).</td><td width='25%' style='{text-align:left;}'><b>Spear Wheel</td><td width=\"10%\" >: "+rs.getString(15)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(10).</td><td width='25%' style='{text-align:left;}'><b>Jack</td><td width=\"10%\" >: "+rs.getString(16)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr><td width='2%'><b>(11).</td><td width='25%' style='{text-align:left;}'><b>Lighter</td><td width=\"10%\" >: "+rs.getString(17)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(12).</td><td width='25%' style='{text-align:left;}'><b>Fuel Cap</td><td width=\"10%\" >: "+rs.getString(18)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(13).</td><td width='25%' style='{text-align:left;}'><b>Carpets</td><td width=\"10%\" >: "+rs.getString(19)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(14).</td><td width='25%' style='{text-align:left;}'><b>Wheel</td><td width=\"10%\" >: "+rs.getString(20)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					//out.println("<tr><td width='2%'><b>(15).</td><td width='25%' style='{text-align:left;}'><b>Body</td><td width=\"10%\" >: "+rs.getString(21)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr></tr>");
					
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr><td width='2%'><b>(15).</td><td width='25%' style='{text-align:left;}'><b>Rear Mirror/Front Mirror</td><td width=\"10%\" >: "+rs.getString(22)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(16).</td><td width='25%' style='{text-align:left;}'><b>Left Side Mirror</td><td width=\"10%\" >: "+rs.getString(23)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(17).</td><td width='25%' style='{text-align:left;}'><b>Right Side Mirror</td><td width=\"10%\" >: "+rs.getString(24)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(18).</td><td width='25%' style='{text-align:left;}'><b>Left Signal Light Front</td><td width=\"10%\" >: "+rs.getString(25)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(19).</td><td width='25%' style='{text-align:left;}'><b>Right Signal Light Front</td><td width=\"10%\" >: "+rs.getString(26)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr></tr>");
					
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr><td width='2%'><b>(20).</td><td width='25%' style='{text-align:left;}'><b>Left Signal Light Rear</td><td width=\"10%\" >: "+rs.getString(27)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(21).</td><td width='25%' style='{text-align:left;}'><b>Right Signal Light Rear</td><td width=\"10%\" >: "+rs.getString(28)+"</td></tr>");
					out.println("<tr><td width='2%'><b>(22).</td><td width='25%' style='{text-align:left;}'><b>Body</td><td width=\"10%\" >: "+rs.getString(21)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					out.println("<tr><td width='2%'><b>(23).</td><td width='25%' style='{text-align:left;}'><b>Police Report</td><td width=\"10%\" >: "+rs.getString(30)+"</td></tr>");
					out.println("<tr><td width='2%'><b>(24).</td><td width='25%' style='{text-align:left;}'><b>Customers Signature</td><td width=\"10%\" >: "+rs.getString(31)+"</td></tr>");
					out.println("<tr><td width='2%'><b>(25).</td><td width='25%' style='{text-align:left;}'><b>Seizer Signature</td><td width=\"10%\" >: "+rs.getString(32)+"</td></tr>");
					out.println("<tr><td width='2%'><b>(26).</td><td width='25%' style='{text-align:left;}'><b>Receivers Signature</td><td width=\"10%\" >: "+rs.getString(33)+"</td><td width=\"*%\" >&nbsp</td></tr>");
					
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					out.println("<tr></tr>");
					
					//out.println("<tr class=pdn_txtpos2>");
					
					
					j=j+1;				
					more=rs.next();
					
					out.println("</table>");	
					
				}
				out.println("<br>");		
				
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</html>");
				
			}
			
			//-------------------------------------------------------------------------------------------------------------------
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
