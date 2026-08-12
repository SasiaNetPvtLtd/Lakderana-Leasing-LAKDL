//--
//SCREEN NAME:Credit Process Temporary Payment Higher Approval - NEW
//CREATED BY:Delanjali
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_PRO_CR_display_main_screen_payment_temp extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			String m_screen_type = req.getParameter("screen");
			String m_chksql = req.getParameter("chksql");
			String m_chksql1 = req.getParameter("chksql2");
			
			String m_sort_column   = "PURCHASE_ORDER_NO";	
			String m_order_by_type = "ASC";
			if(req.getParameter("st_c")!=null && req.getParameter("oby")!=null){
			m_sort_column = req.getParameter("st_c");
			m_order_by_type = req.getParameter("oby");
			}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process Temporary Payment Higher Approval - New</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var st_val='"+m_chksql+"'");
			out.println("var st_val1='"+m_chksql1+"'");
			out.println("var chk_chng=1");
			out.println("var m_order_by_type");
			out.println("var row_arry=new Array();");
			out.println("var st_des1='';");

			out.println("var b_inv=1");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value!=\"j1\"){");
			out.println("				display_fields(data_vec);");
			out.println("			}");
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"EDIT\" &&  document.Form1.hid_val.value!=\"j1\"){");
			out.println("				display_fields(data_vec);");
			out.println("check_box()");
			out.println("			}");
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\" &&  document.Form1.hid_val.value!=\"j1\"){");
			out.println("change1.innerHTML=\"\"");
			out.println("			}");
			out.println("			 if(data_vec.length>1 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value==\"j1\" ){");
			out.println("alert('Select all invoices for pirticular purchase order')");
			out.println("			}");
			out.println("}");
			
			out.println("function makeRequest() {");
			out.println("	 m_order_by_type = 'ASC'; "); 
			out.println("nval=''");
			out.println("if(st_val=='A'){");
			out.println("st_des1='APPRO2'");
			out.println("}");
			out.println("else if(st_val=='B'){");
			out.println("st_des1='APPRO1'");
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
			out.println("else if(st_val1=='VERIFY'){");
			out.println("st_des2='VERIFY'");
			out.println("}");
  		out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_temp_sort&&data_val1=TEMP&data_val=\"+nval+\"&ac_status=T&sort_column=PURCHASE_ORDER_NO&order_by_type=ASC\";");
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

			out.println("function check_po(){ "); 
			out.println("if(change1.innerHTML==\"\"){");
			out.println("alert('No Purchse orders to approve ');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_po()){"); 
			out.println("alert('Please Select Purchse orders to approve');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else");
			out.println("b_flag=0;");
      out.println("}"); 

			out.println("function count_po(){ ");
			out.println("count=0;");
			out.println("for(i=0;i<document.Form1.hid_no.value;i++){");
			out.println("m_chk_tmp=\"CHK_APP_\"+i;");
			out.println("if(document.Form1.elements[m_chk_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			out.println("}");		
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println("check_po()");
			
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_save_main_screen_payment_temp?number='+document.Form1.hid_no.value+'&actst1='+st_val+'&actst2='+st_val1+'';");   
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("var sc_typ='"+m_screen_type+"'");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("nval=''");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_main_screen_payment_temp?chksql="+m_chksql+"&chksql2="+m_chksql1+"';");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_PRO_CR_display_main_screen_payment_temp\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Temporary Payment Higher Approval  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process Temporary Payment Higher Approval - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("check_box()");
			out.println("}");
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("}"); 
			out.println("else{");
			out.println("check_box()");
			out.println("}");
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"save\";");
			out.println("check_box()");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");
			out.println("document.Form1.hid_save.value=\"Delete\";");
			out.println("check_box()");
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
			out.println("		if(confirm(\"Are you sure you want to view Approval level 1?\")){ "); 
			out.println("if(st_val1=='B'){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details_screen1?PO_NO='+document.Form1.elements[pur].value+'&CLSTATUS=A';"); 
	   	out.println("}"); 
			out.println("else if(st_val1=='A'){");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_display_payment_details?PO_NO='+document.Form1.elements[pur].value+'&CLSTATUS=A';"); 
	   	out.println("}"); 
			out.println("window.open(m_url,'displayWindow3','left=0,top=0,width=1200,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');"); 
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
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">'+"); 
			out.println("'<tr class=\"pdn_txtpos2\">'+"); 
			out.println("'<td width=\"10%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"PURCHASE_ORDER_NO\")>Purchase Order No</td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"INVOICE_NO\")>Invoice No</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"ENGINE_NO\")>P/O Engine No</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"CHASSSIS_NO\")>P/O Chassis No</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"REG_NO\")>P/O Vehicle No</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"PURCHASE_ORDER_DATE\")>P/O Date</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"ENGINE_NO\")>Engine No</td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"CHASSIS_NO\")>Chassis No</td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"REG_NO\")>Vehicle No</td>'+"); 
			out.println("'<td width=\"12%\" align=\"left\" style=\"{cursor:hand;}\" onclick=sort_data(\"REG_NO\")>Select</td>'+"); 
			out.println("'<td width=\"3%\" align=\"left\"  style=\"{cursor:hand;}\" >&nbsp</td>'+"); 
			out.println("'</tr >'+");
			out.println("'</table >';");
			out.println("}");
			


			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; "); 
			out.println("var m_bk=1");
			out.println("	 if(m_sort_col='"+m_sort_column+"'){");
			out.println("	   if(data_vec[10]=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; "); 
			out.println("    }");
			out.println(" else if (data_vec[10]=='ASC'){");
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
			out.println("else if(st_val=='VERIFY'){");
			out.println("st_des1='VERIFY'");
			out.println("}");
			out.println("if(st_val1=='A'){");
			out.println("st_des2='APPRO2'");
			out.println("}");
			out.println("else if(st_val1=='B'){");
			out.println("st_des2='APPRO1'");
			out.println("}");
			out.println("else if(st_val1=='VERIFY'){");
			out.println("st_des2='VERIFY'");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_payment_temp_sort&&data_val1=TEMP&data_val=\"+nval+\"&ac_status=T&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			out.println("}");
			
			out.println("function display_fields(data_vec){");
			out.println("if(data_vec[2]=='null' || data_vec[2]==''){");
			out.println("data_vec[2]=''");
			out.println("}");
			out.println("if(data_vec[3]=='null' || data_vec[3]==''){");
			out.println("data_vec[3]=''");
			out.println("}");
			out.println("change1.innerHTML=\"\"");
		  out.println("var j=0;");
			out.println("var i=0;");
			out.println("header();");
			
			out.println("if(data_vec.length==0){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\"  border=\"0\" class=\"table\">'+"); 
			out.println("'<tr >'+"); 
			out.println("'<td width=\"20%\" ><input style=\"width: 100px\" class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"\" maxlength=\"15\" size=\"15\" ></td>'+"); 
			out.println("'<td width=\"20%\" ><input style=\"width: 82px\" class=\"txt_input2\" type=\"hidden\" name=TXT_INVOICE_NO_'+j+' value=\"\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"15%\" ><input style=\"width: 100px\" class=\"txt_input2\" type=\"hidden\" name=TXT_ENGIN_NO_'+j+' value=\"\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input style=\"width: 180px\" class=\"txt_input\" type=\"hidden\" name=TXT_CHASSIS_'+j+' value=\"\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input style=\"width: 180px\" class=\"txt_input\" type=\"hidden\" name=TXT_VEHICLE_NO_'+j+' value=\"\" maxlength=\"250\" size=\"250\"></td>'+"); 
			out.println("'<td width=\"10%\" ><input style=\"width: 180px\" class=\"txt_input\" type=\"hidden\" name=TXT_PO_DATE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"5%\" align=\"center\"><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\" disabled><input type=\"hidden\" name=TXT_REQU_NO_'+j+' value=\"'+data_vec[i+11]+'\"></td>'+");
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 
		  out.println("j=j+1;");	
			out.println("}");
			out.println("else{");
			out.println("while(i<data_vec.length){");
			out.println("if(j>0 && j%2==1){");
			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\"  border=\"0\" class=\"table\">'+"); 
			out.println("'<tr class=\"tr_input1\">'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_INVOICE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_ENGIN_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_CHASSIS_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+4]+'<input   class=\"txt_input2\" type=\"hidden\" name=TXT_VEHICLE_NO_'+j+' value=\"'+data_vec[i+4]+'\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" >'+data_vec[i+5]+'<input class=\"txt_input\" type=\"hidden\" name=TXT_PO_DATE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+6]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PRO_ENGIN_NO_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+7]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_PRO_CHASSIS_'+j+' value=\"'+data_vec[i+7]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+8]+'<input   class=\"txt_input2\" type=\"hidden\" name=TXT_PRO_VEHICLE_NO_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"22\" size=\"22\"></td>'+"); 
			//out.println("'<td width=\"9%\" align=\"left\"><input class=\"txt_input2\" type=\"text\" name=TXT_SELECT_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"22\" size=\"22\"><input   class=\"txt_input2\" type=\"hidden\" name=TXT_PRO_VEHICLE_NO_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"22\" size=\"22\"></td>'+"); 
						
			out.println("'<td width=\"12%\" align=\"left\"><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input2\">'+");
				                                                    out.println("'<option value=\"N\" >New</option>'+");
			                                                      out.println("'<option value=\"O\" >Old</option></select></TD>'+");
																														
			
			out.println("'<td width=\"3%\" align=\"left\"><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"><input type=\"hidden\" name=TXT_REQU_NO_'+j+' value=\"'+data_vec[i+11]+'\"</td>'+");
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 
			out.println("}");
			out.println("else{");

			out.println("change1.innerHTML+='<table align=\"center\" width=\"100%\"  border=\"0\" class=\"table\">'+"); 
			out.println("'<tr class=\"tr_input\">'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i]+'<input class=\"txt_input2\" type=\"hidden\" name=TXT_PURCHASE_ORDER_NO_'+j+' value=\"'+data_vec[i]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+1]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_INVOICE_NO_'+j+' value=\"'+data_vec[i+1]+'\" maxlength=\"20\" size=\"20\">'+"); 
			out.println("'</td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+2]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_ENGIN_NO_'+j+' value=\"'+data_vec[i+2]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+3]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_CHASSIS_'+j+' value=\"'+data_vec[i+3]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+4]+'<input   class=\"txt_input2\" type=\"hidden\" name=TXT_VEHICLE_NO_'+j+' value=\"'+data_vec[i+4]+'\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\" >'+data_vec[i+5]+'<input class=\"txt_input\" type=\"hidden\" name=TXT_PO_DATE'+j+' value=\"\" maxlength=\"10\" size=\"10\"></td>'+"); 
			out.println("'<td width=\"9%\" align=\"left\">'+data_vec[i+6]+'<input  class=\"txt_input2\" type=\"hidden\" name=TXT_PRO_ENGIN_NO_'+j+' value=\"'+data_vec[i+6]+'\" maxlength=\"15\" size=\"15\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+7]+'<input  class=\"txt_input\" type=\"hidden\" name=TXT_PRO_CHASSIS_'+j+' value=\"'+data_vec[i+7]+'\" maxlength=\"200\" size=\"200\"></td>'+"); 
			out.println("'<td width=\"10%\" align=\"left\">'+data_vec[i+8]+'<input   class=\"txt_input2\" type=\"hidden\" name=TXT_PRO_VEHICLE_NO_'+j+' value=\"'+data_vec[i+8]+'\" maxlength=\"22\" size=\"22\"></td>'+"); 
			out.println("'<td width=\"12%\" align=\"left\"><select name=TXT_STATUS'+j+' STYLE=\"width:100\" class=\"txt_input2\">'+");
				                                                    out.println("'<option value=\"N\" >New</option>'+");
			                                                      out.println("'<option value=\"O\" >Old</option></select></TD>'+");
			
			out.println("'<td width=\"3%\" align=\"left\"><input type=\"checkbox\" name=CHK_APP_'+j+' value=\"N\" unchecked onclick=\"check_change('+j+')\"><input type=\"hidden\" name=TXT_REQU_NO_'+j+' value=\"'+data_vec[i+11]+'\"</td>'+");
			out.println("'</tr >'+"); 
			out.println("'</table>';"); 
			out.println("}");
			out.println("j=j+1;");
			out.println("i=i+13;");
			out.println("}"); 
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
			out.println("check_invoice(row)");
			out.println("}");
			
			
			out.println("function check_inv1(data_vec){");
			out.println(" var d=document.Form1.hid_row.value");
			out.println("for(var f=0;f<document.Form1.hid_no.value;f++){");
			out.println("  chk1=\"CHK_APP_\"+f;");
			out.println("if(document.Form1.elements[\"PURCHASE_ORDER_NO_\"+f].value==data_vec[0]){");
			out.println(" if(document.Form1.elements[chk1].checked==true){");
			out.println("b_inv=1");
			out.println("}"); 
			out.println("else{"); 	
			out.println("b_inv=0");
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
				
				
				
			out.println("function check_invoice(row){");
			out.println("    document.Form1.hid_val.value='j1'"); 
			out.println("    document.Form1.hid_row.value=row"); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_PRO_CR_display_main_screen_temp_invoice&data_val='+document.Form1.elements[\"TXT_PURCHASE_ORDER_NO_\"+row].value+'&inv_no='+document.Form1.elements[\"TXT_INVOICE_NO_\"+row].value+'';");
			out.println("load_interface(m_url,'XML');");
			
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
			
			out.println("function close_window_1() {");
			out.println("close_window()");
			out.println("}");
			
			out.println("function befor_end(m_obj) {");
      out.println("   m_obj.focus();");
      out.println("}");
			
//-------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),makeRequest()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_no' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process Temporary Payment Higher Approval - New</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\"></td>");  
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
			
			out.println("<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr class=tr_input>");
      out.println("<td colspan=11 align=right></td>");
			out.println("<td align=right colspan=13><input type=button name=top_bot     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.top_top);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.hid_status.value);'></td>");
      out.println("</tr></table>");

			
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' name=\"bt_new\" value=\"New\"></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  name=\"bt_save\" onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
   		out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_window(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("<br>"); 
			
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
