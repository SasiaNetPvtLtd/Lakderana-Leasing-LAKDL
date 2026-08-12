//--
//SCREEN NAME:BANK STANDING ORDERS REPORT
//CREATED BY :DELANJALI
//DATE/TIME  :2007-03-02
//NOTES			 :

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_display_bank_standing_orders extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
		  
			m_chksql=req.getParameter("chksql");
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
		
			String m_sort_column   = "";	
			String m_order_by_type = "";
							
					
		 if(m_chksql.equals("main_page")){



			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>bank standing orders</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector_normal(http_response) {");
			out.println(" so_details.innerHTML = ''; ");
			out.println(" so_details.innerHTML = http_response; ");
			out.println("}");
			

		  out.println("function sort_data(m_sort_col,val) {");
			out.println(" if(val=='ASC'){");
			out.println(" m_order_by_type='DESC'");
			out.println(" }");
			out.println("else{");
			out.println(" m_order_by_type='ASC'");
			out.println(" }");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_bank_standing_orders?chksql=so_details&to_date=\"+to_date+\"&from_date=\"+from_date+\"&order_type=\"+m_sort_col+\"&type=\"+m_order_by_type;");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_FINANCE_NO.value==\"\"){  "); 
			out.println("DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/LAKDL_AF_CR_PRO_save_bank_standing_orders';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
		//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/LAKDL_AF_CR_PRO_display_bank_standing_orders?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/LAKDL_AF_CR_PRO_display_bank_standing_orders?chksql=main_page&sort_column="+m_sort_column+"&order_by_type="+m_order_by_type+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_PRO_display_bank_standing_orders\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" bank standing orders - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" bank standing orders - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_SO_NO.disabled=true;"); 
			out.println("document.Form1.TXT_START_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_END_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_BANK_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_STATUS.disabled=true;"); 
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
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
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("}"); 
			out.println("function help_button_2() {"); 

			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_SO_NO_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SO_NO.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_SO_NO.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_TXT_BANK_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_BANK_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_FINANCE_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SO_NO.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_START_DATE.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_END_DATE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_BANK_CODE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_STATUS.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[8];"); 
			out.println("}"); 


			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");	
			out.println(" chk_validity() ");			
			out.println("}");				

			out.println("function view() {"); 
			out.println(" so_details.innerHTML = ''; ");
			out.println(" makeRequest() ");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");
			out.println("to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value==\"\" || document.Form1.TXT_FROM_DATE_MM.value==\"\" || document.Form1.TXT_FROM_DATE_YY.value==\"\"){");
			out.println("from_date=\"\"");
			out.println("}");
			out.println("if(document.Form1.TXT_TO_DATE_DD.value==\"\" || document.Form1.TXT_TO_DATE_MM.value==\"\" || document.Form1.TXT_TO_DATE_YY.value==\"\"){");
			out.println("to_date=\"\"");
			out.println("}");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_display_bank_standing_orders?chksql=so_details&to_date='+to_date+'&from_date='+from_date+'&order_type=SO_NO&type=DESC';");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");


			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");


			out.println("function val_to_date(){ ");
			out.println("checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY);");
			out.println("}");
			
			out.println("function val_from_date(){ ");
			out.println("checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");

			out.println("function chk_validity(){  ");	
		  out.println("if((document.Form1.TXT_FROM_DATE_DD.value!=\"\" || document.Form1.TXT_FROM_DATE_MM.value!=\"\" || document.Form1.TXT_FROM_DATE_YY.value!=\"\")  && (document.Form1.TXT_TO_DATE_DD.value!=\"\" || document.Form1.TXT_TO_DATE_MM.value!=\"\" || document.Form1.TXT_TO_DATE_YY.value!=\"\" )){");
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_DD.value))>=(parseInt(document.Form1.TXT_TO_DATE_DD.value))){");
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_MM.value))<=(parseInt(document.Form1.TXT_TO_DATE_MM.value))){");
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))<=(parseInt(document.Form1.TXT_TO_DATE_YY.value))){");
      out.println(" if(((parseInt(document.Form1.TXT_FROM_DATE_DD.value))==(parseInt(document.Form1.TXT_TO_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_MM.value))==(parseInt(document.Form1.TXT_TO_DATE_MM.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_YY.value))==(parseInt(document.Form1.TXT_TO_DATE_YY.value)))){");
      out.println("}");
      out.println("else if(((parseInt(document.Form1.TXT_FROM_DATE_DD.value))>(parseInt(document.Form1.TXT_TO_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_MM.value))==(parseInt(document.Form1.TXT_TO_DATE_MM.value)))&&");
      out.println(" ((parseInt(document.Form1.TXT_FROM_DATE_YY.value))==(parseInt(document.Form1.TXT_TO_DATE_YY.value)))){");
      out.println("      alert('To Date should be greater than From Date');");
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println(" alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))>=(parseInt(document.Form1.TXT_TO_DATE_YY.value))){");
      out.println("    alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(document.Form1.TXT_FROM_DATE_MM.value))<=(parseInt(document.Form1.TXT_TO_DATE_MM.value))){");
      out.println("  if((document.Form1.TXT_FROM_DATE_YY.value)<=(document.Form1.TXT_TO_DATE_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("   alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))<(parseInt(document.Form1.TXT_TO_DATE_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("  alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("document.Form1.TXT_FROM_DATE_DD.focus();");
			out.println("return true;");
      out.println("}");
		  out.println("}");
	

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>bank standing orders</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  

			out.println("<br >"); 
			out.println("<br >"); 

			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FROM_DATE'  class=div_input>From Date</DIV></td>"); 
			out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"val_from_date()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"val_from_date()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"val_from_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("</tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TO_DATE'  class=div_input>To Date</DIV></td>"); 
			out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"val_to_date()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"val_to_date()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"val_to_date()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
			out.println("</tr >"); 


			out.println("</table>"); 
			
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=so_details></div></td></tr></table>");

			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
			
	//-----------------------------------------------------------------------------------------------------------------		
			
		else if(m_chksql.equals("so_details")){		
			int j=0;		
			int x=0;	
			String m_from_date = req.getParameter("from_date");	
			String m_to_date= req.getParameter("to_date");	
			
			 m_sort_column = req.getParameter("order_type");	
			 m_order_by_type= req.getParameter("type");	

			if(m_from_date==null){
			m_from_date="";
			}
			if(m_to_date==null){
			m_to_date="";
			}

			if(!m_from_date.equals("") && !m_to_date.equals("")){	

			rs = stmt.executeQuery ("SELECT SO_NO,FINANCE_NO,TO_CHAR(START_DATE,'DD-MM-YYYY'),TO_CHAR(END_DATE,'DD-MM-YYYY'),NVL(ACC_NO,'-'),NVL(BANK_CODE,'-'),NVL(TO_CHAR(AMOUNT,'99,999,999,999,999,999,999,999.99'),0),DECODE(STATUS,'Y','Yes','N','No'),NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BANK_CODE),'-') "+
			"FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS "+
			"WHERE (TO_DATE(TO_CHAR(START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY')>=  TO_DATE(('"+m_from_date+"'),'DD-MM-YYYY') "+
      "AND TO_DATE(TO_CHAR(START_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') <= TO_DATE(('"+m_to_date+"'),'DD-MM-YYYY')) "+
			"AND STATUS='Y' "+ 

			"ORDER BY "+m_sort_column+"  "+m_order_by_type+" ");
			}
			
			if(m_from_date.equals("") || m_to_date.equals("")){	

			rs = stmt.executeQuery ("SELECT SO_NO,FINANCE_NO,TO_CHAR(START_DATE,'DD-MM-YYYY'),TO_CHAR(END_DATE,'DD-MM-YYYY'),NVL(ACC_NO,'-'),NVL(BANK_CODE,'-'),NVL(TO_CHAR(AMOUNT,'99,999,999,999,999,999,999,999.99'),0),DECODE(STATUS,'Y','Yes','N','No'),NVL("+m_schema_name+".AF_CO_GET_BRANCH_NAME(BANK_CODE),'-') "+
			"FROM "+m_schema_name+".AF_CO_PRO_STANDING_ORDERS "+
			"WHERE STATUS='Y' "+ 
			"ORDER BY "+m_sort_column+"  "+m_order_by_type+" ");
			}
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<HR>"); 
	

			out.println("<table align='center' border=\"0\" width='100%' class='table'>"); 
			
			out.println("<tr><td align=right colspan=13><input type=button name=top_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(\"Top\");'></td></tr>");

		  out.println("<tr class=pdn_txtpos2 >");

			out.println("<td width='15%' style= cursor:hand; title='Click here to sort by - Standing Order No  '    onclick=sort_data('SO_NO','"+m_order_by_type+"') >Standing Order No</td>"); 
			out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Finance No  '    onclick=sort_data('FINANCE_NO','"+m_order_by_type+"') >Finance No</td>"); 
			out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - Start Date  '    onclick=sort_data('START_DATE','"+m_order_by_type+"') >Start Date</td>"); 
			out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - End Date  '    onclick=sort_data('END_DATE','"+m_order_by_type+"') >End Date</td>"); 
			out.println("<td width='10%' style= cursor:hand; title='Click here to sort by - Account No  '    onclick=sort_data('ACC_NO','"+m_order_by_type+"') >Account No</td>"); 
			out.println("<td width='8%' style= cursor:hand; title='Click here to sort by - Bank code '    onclick=sort_data('BANK_CODE','"+m_order_by_type+"') >Bank Code</td>"); 
		  out.println("<td width='12%' style= cursor:hand; title='Click here to sort by - Bank Name '    onclick=sort_data('BANK_CODE','"+m_order_by_type+"') >Bank Name</td>"); //Added by Chandana on 21/05/2007 for Ref No 45
			out.println("<td width='10%' align=right style= cursor:hand; title='Click here to sort by - Amount  '    onclick=sort_data('AMOUNT','"+m_order_by_type+"') >Amount</td>"); 
			out.println("<td width='7%' align=center style= cursor:hand; title='Click here to sort by - Status  '    onclick=sort_data('STATUS','"+m_order_by_type+"') >Status</td>"); 
				
            while(rs.next()){
							
							if(j>0 && j%2==1){
               	out.println("<tr class=tr_input1 >");
							}
							else{
									
                out.println("<tr class=tr_input >");
							}
							
							
									
			out.println("<td width='15%' align='left' style= cursor:hand; onclick=show_std_order_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u><input class='txt_input' type='hidden' name=TXT_SO_NO_"+j+" value=\""+rs.getString(1)+"\"></td>");
      out.println("<td width='12%' align='left' style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u><input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\"></td>");
      out.println("<td width='8%' align='left'>"+rs.getString(3)+"<input class='txt_input' type='hidden' name=TXT_START_DATE_"+j+" value=\""+rs.getString(3)+"\"></td>");
      out.println("<td width='8%' align='left'>"+rs.getString(4)+"<input class='txt_input' type='hidden' name=TXT_END_DATE_"+j+" value=\""+rs.getString(4)+"\"></td>");
      out.println("<td width='10%' align='left'>"+rs.getString(5)+"<input class='txt_input' type='hidden' name=TXT_ACC_NO_"+j+" value=\""+rs.getString(5)+"\"></td>");
      out.println("<td width='8%' align='left'>"+rs.getString(6)+"<input class='txt_input' type='hidden' name=TXT_BRANCH_CODE_"+j+" value=\""+rs.getString(6)+"\"></td>");
      out.println("<td width='12%' align='left'>"+rs.getString(9)+"<input class='txt_input' type='hidden' name=TXT_FINANCE_NO_"+j+" value=\""+rs.getString(6)+"\"></td>"); //Added by Chandana on 21/05/2007 for Ref No 45
			out.println("<td width='10%' align='right'>"+rs.getString(7)+"<input class='txt_input' type='hidden' name=TXT_AMOUNT_"+j+" value=\""+rs.getString(7)+"\"></td>");
      out.println("<td width='7%' align='center'>"+rs.getString(8)+"<input class='txt_input' type='hidden' name=TXT_STATUS_"+j+" value=\""+rs.getString(8)+"\"></td>");
			out.println("</tr>");
     	j=j+1;
							
      }
          
			out.println("<tr>");
			out.println("<td><input type=\"hidden\" name=hid_no value="+j+"></td>");
			out.println("</tr>");
			
			out.println("<tr class=tr_input>");
      out.println("<td align=right colspan=13><input type=button name=end_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(\"End\");'></td>");

			out.println("</table>"); 
		}	
	//-----------------------------------------------------------------------------------------------------------------		
			
			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
