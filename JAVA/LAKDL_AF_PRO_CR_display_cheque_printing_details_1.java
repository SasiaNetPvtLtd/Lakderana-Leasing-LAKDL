//--
//SCREEN NAME:Credit Process - Cheque Printing/Disburse
//CREATED BY :DELANJALI	
//DATE/TIME  :25-01-2007
//NOTES      :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_cheque_printing_details_1 extends javax.servlet.http.HttpServlet { 
	Connection conn;
	ServletOutputStream out = null;
	Statement stmt,stmt1,stmt2;
	public ResultSet rs,rs1,rs2;
	
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
      String m_username = m_sn_methods.username;

			String m_screen_type = req.getParameter("screen");
			String m_chksql = req.getParameter("chksql");
			String m_chksql1 = req.getParameter("chksql2");
			String m_head="";
		
			stmt=conn.createStatement();
			stmt1=conn.createStatement();

			String fschema_name = m_sn_methods.schema_name;

		
			if (m_chksql1.equals("PRINT")){
			m_head="Printing";
			}
			else if (m_chksql1.equals("DISBURSE")){
			m_head="Disburse";
			}
				else if (m_chksql1.equals("CANCEL")){
			m_head="Cancel";
			}
			
			
			String m_sort_column   = "PURCHASE_ORDER_NO";	
			String m_order_by_type = "DESC";
			
		
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
		

			if(m_sort_column==null){
			m_sort_column="PURCHASE_ORDER_NO";	
			}

			if(m_order_by_type==null){
			m_order_by_type = "DESC";
			}
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - "+m_head+" cheques  - New</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var st_val='"+m_chksql+"'");//APPRO2/PRINT/PRINT
			out.println("var st_val1='"+m_chksql1+"'");//PRINT/DISBURSE/CANCEL
			out.println("var chk_chng=1");
			out.println("var m_order_by_type='DESC'");

			out.println("function view() {"); 
			out.println("if(document.Form1.TXT_ACC_NO.value!=\"\"){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Cheque_details?chksql=m_cheque&data_val='+document.Form1.TXT_ACC_NO.value+'';");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=200,top=210,width=550,height=300,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("else if(document.Form1.TXT_ACC_NO.value==\"\"){");
			out.println("alert('Please enter Account No to view details')");
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
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_cheque_printing_details?number='+document.Form1.hid_no.value+'&actst1='+st_val+'&actst2='+st_val1+'';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("var sc_typ='"+m_screen_type+"'");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("if(st_val1=='PRINT'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque\";"); 
			out.println("}");
			out.println("else if(st_val1=='CANCEL'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_can\";"); 
			out.println("}");
			out.println("else if(st_val1=='DISBURSE'){");
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_cheque_dis\";"); 
			out.println("}");
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	

			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 			


			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - "+m_head+" cheques  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - "+m_head+" cheques - \"+document.Form1.hid_status.value;"); 
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
			out.println("clear_data()");
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
			out.println("else if(oBj.valout[4] != \" \"){ ");
			out.println("Crit = oBj.valout[4];");
			out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
			out.println("}	");
			out.println("if(oBj.valout[4]==' '){ ");
			out.println("Close()	");
			out.println("}	");
			out.println("}");	
			
			out.println(" function Close(){");//**
			out.println("clear_data()	");
      out.println(" }");
			
			out.println("function clear_data(){");
			out.println("if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("    document.Form1.TXT_ACC_NO.value='';"); 
			out.println("    document.Form1.TXT_ACC_NO.focus();");
			out.println("    document.Form1.TXT_BRANCH_CODE.value='';"); 
			out.println("change1.innerHTML=\"\";");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("    document.Form1.TXT_ACC_NO.value='';"); 
			out.println("    document.Form1.TXT_ACC_NO.focus();");
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println(" district='TXT_DISTRICT_CODE'+document.Form1.hid_val.value");
			out.println(" document.Form1.elements[district].value='';"); 
			out.println(" document.Form1.elements[district].focus();"); 
			out.println("}");
			out.println("}");		

			out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);");
			out.println("}");		
				
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
				
			out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
			out.println("HelpBox(Start,End,Hid_No,Crit,Sql,IfCount)");
			out.println("}");
			

			
			out.println("function help_button_1(row) {"); 
			out.println("pur=\"TXT_PURCHASE_ORDER_NO_\"+row");
			out.println("if(st_val1=='B'){");
			out.println("		if(confirm(\"Are you sure you want to view Approval level 1?\")){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details_screen1?PO_NO='+document.Form1.elements[pur].value+'&CLSTATUS=A';"); 
	   	out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else if(st_val1=='A'){");
			out.println("		if(confirm(\"Are you sure you want to view Approval level 2?\")){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details?PO_NO='+document.Form1.elements[pur].value+'&CLSTATUS=A';"); 
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
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
			
			

			out.println("function sort_data(m_sort_col) {");
			out.println("alert(m_sort_col)");
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_cheque_printing_details_1?chksql="+m_chksql+"&chksql2="+m_chksql1+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println(" window.location.href=m_url;"); 
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
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("for(var d=0;d<document.Form1.hid_no.value;d++){");
			out.println("  chk=\"CHK_APP_\"+d;");
			out.println("    document.Form1.elements[chk].disabled=false;"); 
			out.println("    document.Form1.bt_save.disabled=false;");
			out.println("}");
			out.println("}"); 
			out.println("}"); 
			
			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");
		  out.println("   if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_DD_\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_MM_\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_POST_DATE_YY_\"+m_row].value=v_yy;");
			out.println("  }");	
			out.println("}");				

			
			out.println("function check_date_from(ln){ ");
			out.println("ind_dd='TXT_POST_DATE_DD_'+ln;");
			out.println("ind_mm='TXT_POST_DATE_MM_'+ln;");
			out.println("ind_yy='TXT_POST_DATE_YY_'+ln;");
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");




//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<table width='150%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - "+m_head+" cheques</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
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
		
			out.println("<table align='center' width='100%' class='table' borer=\"1\">"); 
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</r>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<br>");
			int i=0;		
			int x=0;	

			rs= stmt.executeQuery ("SELECT DISTINCT A.PURCHASE_ORDER_NO,FINANCE_NO,C.APPLICATION_NO,NAME,B.VENDOR_CODE,FULL_NAME, "+
			"TOTAL_NET,TOTAL_VAT,TO_CHAR((TOTAL_NET+TOTAL_VAT),'999,999,999,999,999,999,999,999.99') TOTAL, "+
			"TO_CHAR(PRINTED_DATE,'DD-MM-YYYY')PRINT_DATE,PRINTED_USER,ACC_NO,BRANCH_CODE "+
			",(SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL) AS SYDATE,E.REQU_NO ,D.CLIENT_CODE, "+
			"SUS_REF_NO,TO_CHAR(E.ENT_DATE,'DD-MM-YYYY'),A.CURR_CODE "+
			"FROM "+fschema_name+".AF_CR_PRO_PURCHASE_ORDER A, "+
			""+fschema_name+".AF_CO_MAS_VENDORS B, "+
			""+fschema_name+".AF_CO_PRO_APPLICATION_DETAILS C, "+
			""+fschema_name+".AF_CO_MAS_CLIENT D , "+
			""+fschema_name+".AF_CR_PRO_PURCHASE_ORDER_SETT E, "+
			""+fschema_name+".AF_CR_PRO_PUR_ORDER_SETDET F , "+
			""+fschema_name+".AF_RE_ACC_SUS_PAYMENT G, "+
			""+fschema_name+".AF_CO_PRO_APP_INVOICE_DETAILS H "+
			"WHERE A.VENDER_CODE     =B.VENDOR_CODE "+
			"AND A.APPLICATION_NO    =C.APPLICATION_NO "+
			"AND C.CLIENT_CODE       =D.CLIENT_CODE "+
			"AND A.PURCHASE_ORDER_NO =E.PURCHASE_ORDER_NO "+
			"AND E.ACTIVE_STATUS     ='PRINT' "+
			"AND (TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(PRINTED_DATE,'DD-MM-YYYY')) <7  "+//< 7
			"AND E.REQU_NO           =F.REQU_NO "+
			"AND F.INVOICE_NO        =G.REF_NO "+
			"AND G.REF_NO            =H.INVOICE_NO "+
				//AND A.ACTIVE_STATUS='PRINT'
			" ORDER BY "+m_sort_column+" "+m_order_by_type+"");





			boolean more = rs.next();
			
			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			out.println("<br>");		
			out.println("<tr class=pdn_txtpos2>");
		
			out.println("<td width=\"5%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Account No' onclick=sort_data(\"ACC_NO\")>Account</td>"); 
			out.println("<td width=\"5%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Branch Code' onclick=sort_data(\"BRANCH_CODE\")>Branch</td>"); 
			out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Purchase Order No' onclick=sort_data(\"PURCHASE_ORDER_NO\")>P/O No</td>"); 
			out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Finance No' onclick=sort_data(\"FINANCE_NO\")>Finance No</td>"); 
			out.println("<td width=\"8%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Supplier Name' onclick=sort_data(\"NAME\")>Supplier Name</td>"); 
			out.println("<td width=\"8\" align=\"left\" style=cursor:hand;title='Click here to sort by - Client Name' onclick=sort_data(\"FULL_NAME\")>Client Name</td>"); 
			out.println("<td width=\"10%\" align=\"right\" style=cursor:hand;title='Click here to sort by - Total Amount' onclick=sort_data(\"TOTAL_NET\")>Total</td>"); 
			out.println("<td width=\"5%\" align=\"left\" style=cursor:hand;title='Click here to sort by - Print Date' onclick=sort_data(\"PRINT_DATE\")>Print Date</td>"); 
			out.println("<td width=\"5%\" align=\"left\" >Dis.To</td>"); 
			out.println("<td width=\"5%\" align=\"left\"  >Chq.No</td>");			
			out.println("<td width=\"5%\" align=\"left\" >Dis.By</td>"); 
			out.println("<td width=\"12%\" align=\"left\"  >Post Date</td>"); 
			out.println("<td width=\"8%\" align=\"left\"  >Application No</td>"); 
			out.println("<td width=\"5%\" align=\"left\">ID No</td>"); 
			out.println("<td width=\"5%\" align=\"center\" >Approve</td>"); 
			out.println("</tr >"); 

		
	    int j = 0; 
			while(more){
			
									if(j>0 && j%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
									
                  	out.println("<tr class=tr_input >");
									}
							


			out.println("<td width=\"5%\" align=\"left\">"+rs.getString(12)+"<input  type=\"hidden\" name=TXT_ACC_"+j+" value=\""+rs.getString(12)+"\" maxlength=\"200\" ></td>"); 
			out.println("<td width=\"5%\" align=\"left\">"+rs.getString(13)+"<input  type=\"hidden\" name=TXT_BRANCH_"+j+" value=\""+rs.getString(13)+"\" maxlength=\"200\" ></td>"); 
			out.println("<td width=\"8%\" align=\"left\">"+rs.getString(1)+"<input type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_"+j+" value=\""+rs.getString(1)+"\" maxlength=\"15\"></td>"); 
			out.println("<td width=\"8%\" align=\"left\" >"+rs.getString(2)+"<input type=\"hidden\" name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\" maxlength=\"20\" >"); 
			out.println("</td>"); 
			out.println("<td width=\"8%\" align=\"left\">"+rs.getString(4)+"<input  type=\"hidden\" name=TXT_NAME_"+j+" value=\""+rs.getString(4)+"\" maxlength=\"200\" size=\"200\"><input  type=\"hidden\" name=TXT_VENDOR_CODE_"+j+" value=\""+rs.getString(5)+"\"></td>"); 
			out.println("<td width=\"8%\" align=\"left\">"+rs.getString(6)+"<input type=\"hidden\" name=TXT_FULL_NAME_"+j+" value=\""+rs.getString(6)+"\" maxlength=\"200\" ></td>"); 
			out.println("<td width=\"10%\" align=\"right\">"+rs.getString(9)+"<input type=\"hidden\" name=TXT_TOTAL_NET_"+j+" value=\""+rs.getString(9)+"\" maxlength=\"25\" ></td>"); 
			out.println("<td width=\"5%\" align=\"left\">"+rs.getString(10)+"<input type=\"hidden\" name=TXT_PRINT_DATE"+j+" value=\""+rs.getString(10)+"\" maxlength=\"10\" ></td>"); 
			out.println("<td width=\"5%\" align=\"left\"><input type=\"text\" class='txt_input2' name=TXT_DISB_TO_"+j+" value=\"\" maxlength=\"200\" ></td>"); 
			out.println("<td width=\"5%\" align=\"left\"><input  type=\"text\" class='txt_input1' name=TXT_CHEQUE_NO_"+j+" value=\"\" maxlength=\"8\" ></td>"); 
			out.println("<td width=\"5%\" align=\"left\">"+m_username+"<input  type=\"hidden\" class='txt_input1' name=TXT_ID_"+j+" value=\""+m_username+"\" maxlength=\"10\" ></td>"); 
			out.println("<td width=\"12%\" align=\"left\"><input class=\"txt_input5\" type=\"text\" name=TXT_POST_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(14).substring(0,2)+"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_POST_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\""+rs.getString(14).substring(3,5)+"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_POST_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(14).substring(6,10)+"\" onblur=\"check_date_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
			out.println("</td> ");
			out.println("<td width=\"8%\" align=\"left\">"+rs.getString(3)+"<input  type=\"hidden\" name=TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(3)+"\"></td>"); 
			out.println("<td width=\"5%\" align=\"left\"><input  type=\"text\" class='txt_input1' name=TXT_ID_NO_"+j+" value=\"\" maxlength=\"10\" ></td>"); 
			out.println("<td width=\"5%\" align=\"center\"><input  type=\"checkbox\" name=CHK_APP_"+j+" value=\"N\" unchecked onclick=\"check_change("+j+")\">");
			out.println("<input  type=\"hidden\" name=TXT_POST_DATE_"+j+" value=\"\" ></td>");
			out.println("<input type=\"hidden\" name=TXT_REQ_NO_"+j+" value=\""+rs.getString(15)+"\" >"); 
			out.println("<input type=\"hidden\" name=TXT_CLIENT_CODE_"+j+" value=\""+rs.getString(16)+"\" >"); 
			out.println("<input type=\"hidden\" name=TXT_SUS_REF_NO_"+j+" value=\""+rs.getString(17)+"\" >"); 
			out.println("<input type=\"hidden\" name=TXT_SETTLE_DATE_"+j+" value=\""+rs.getString(18)+"\" >"); 
			out.println("<input type=\"hidden\" name=TXT_CURR_"+j+" value=\""+rs.getString(19)+"\" >"); 


	
			out.println("</tr>");
			
			more=rs.next();
			j=j+1;
		
			if (!more)
			{
			break;
			}
			
			}	
		
			out.println("<input type=hidden name=hid_no value="+j+">");
			out.println("</table>");
		
			
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
		
		
		
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
