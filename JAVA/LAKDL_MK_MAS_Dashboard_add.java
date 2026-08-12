//SCREEN NAME:SYSTEM ADMINISTRATION - SUB CHARGE assign
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_MK_MAS_Dashboard_add extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	String header_id = "";	
	Statement stmt;
	public ResultSet rs;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			Connection conn = m_sn_methods.met_user_validate(req);
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			String m_schema_name = m_sn_methods.schema_name;
			stmt = conn.createStatement ();
			
			
			
			
			rs=stmt.executeQuery(" SELECT HEDER_ID, "+
				" DESC_1, "+
				" DESC_2, "+
				" HEIGHT_1 "+
				" FROM "+m_schema_name+".DH_DASH_HEADER ");
			/*************************************************************/			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection Process - Dashboard</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<link REL=\"STYLESHEET\" HREF='"+m_html_client_url+"/csss/jquery-ui.css'  TYPE=\"text/css\">");
			out.println("<link REL=\"STYLESHEET\" HREF='"+m_html_client_url+"/csss/jquery.ui.button.css'  TYPE=\"text/css\" >");
			out.println("<link REL=\"stylesheet\" type=\"text/css\" href='"+m_html_client_url+"/csss/ui.jqgrid.css'>");
			out.println("<link REL=\"stylesheet\" type=\"text/css\" href='"+m_html_client_url+"/csss/jquery.ui.datepicker.css'>");
			
			//out.println("<script type=\"text/javascript\" src=\"" + m_jsp_client_url + "/js/jquery.min.js\"></script>");
			//out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/jquery.jqGrid.min.js\"></script>");
			out.println("<script type=\"text/javascript\" src= \""+m_html_client_url+"/newdash/jquery.min.js\"></script> ");
			out.println("<script type=\"text/javascript\" src= \""+m_html_client_url+"/newdash/jquery-ui.min.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/json2.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/popup.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/grid.locale-en.js\"></script>");
			out.println("<script type=\"text/javascript\" src=\""+m_html_client_url+"/newdash/jquery.jqGrid.min.js\"></script>");
			out.println("<SCRIPT language=\"JavaScript\">");
			
			
			
			
			
			
			out.println("function get_vector(data_vec) {");
			
			
			out.println("if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.hid_val.value=='t1'){");
			out.println("	alert('Record already exist');");
			out.println("	new_window();");
			out.println(" }");
			out.println("else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_TYPE_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("help_update()"); //added by nuwan de silva on 04-12-2007
			out.println("}");
			
			out.println("else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_SUB_TYPE_CODE.value!='' && document.Form1.hid_val.value=='t1'){");
			out.println("document.Form1.TXT_SUB_TYPE_CODE.value=data_vec[0];"); 
			out.println("document.Form1.TXT_TYPE_CODE.value=data_vec[1];"); 
			out.println("document.Form1.TXT_DESCRIPTION.value=data_vec[2];"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.value=data_vec[3];"); 
			out.println("document.Form1.TXT_MAINTENANCE_STATUS.value=data_vec[4];"); 
			out.println("document.Form1.TXT_CHARGE_TYPE.value=data_vec[5];"); 
			out.println("document.Form1.TXT_ACC_TYPE.value=data_vec[6];");
			out.println("}");
			
			out.println("if(data_vec.length==0 && document.Form1.hid_val.value=='t2' && document.Form1.TXT_TYPE_CODE.value!=''){");
			out.println("   help_button_2();");
			out.println("	}");
			out.println("}");
			
			out.println("function assig(val) {");
			out.println("document.Form1.hid_val.value=val;");
			out.println(" if ((document.Form1.SCREEN_NAME.value==\"NEW\")||(document.Form1.SCREEN_NAME.value==\"EDIT\")||(document.Form1.SCREEN_NAME.value==\"DACT\")){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println(" if (document.Form1.SCREEN_NAME.value==\"RACT\"){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			
			out.println("}");
			
			out.println("function clear_data() {");
			out.println("	if(document.Form1.hid_help_type.value==\"99\"){"); 
			//out.println("document.Form1.TXT_SUB_TYPE_CODE.value='';"); 
			//out.println("document.Form1.TXT_SUB_TYPE_CODE.focus();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			//out.println("document.Form1.TXT_TYPE_CODE.value='';"); 
			//out.println("document.Form1.TXT_TYPE_CODE.focus();"); 
			out.println("		}"); 
			out.println("		}"); 
			
			out.println("function makeRequest(obj) {");
			out.println("	if(document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.SCREEN_NAME.value==\"NEW\"  )");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_charge&data_val=\"+obj.value;");
			out.println(" else");
			out.println(" m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_sub_charge1&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println(" load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_charges&data_val=\"+obj.value+\"&ac_status=\"+document.Form1.hid_st.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_SUB_SEC_ID.value==\"\"){  "); 
			out.println("DIV_TXT_SUB_TYPE_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_NAME.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_NAME.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_ADD1.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_ADD1.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_PAYEE_ADD2.value==\"\"){  "); 
			out.println("DIV_TXT_PAYEE_ADD2.style.color='red';");
			
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			out.println(" m_status = document.Form1.hid_status.value ");
			out.println(" m_save_msg='Are you sure you want to Save ? ';");
			out.println(" if(m_status == \"New\"){ ");
			out.println(" m_save_msg = 'Are you sure you want to Save ? '");
			out.println(" }"); 
			out.println(" else if(m_status == \"Edit\"){ ");
			out.println(" m_save_msg = 'Are you sure you want to Modify ? '");
			out.println(" }"); 
			
			out.println("else if(m_status == \"DACT\"){");
			out.println(" m_save_msg = 'Are you sure you want to Deactivate ? '");
			out.println(" }"); 
			
			out.println("else if(m_status == \"RACT\"){");
			out.println(" m_save_msg = 'Are you sure you want to Reactivate ? '");
			out.println(" }"); 
			
			
			
			out.println("		if(confirm(m_save_msg)){ "); 
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			//out.println("alert(document.Form1.TXT_SUB_SEC_ID.value);");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Add_Sub_Sections_Save';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			//out.println("		}"); 
			//out.println("		else { "); 
			//out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
			//out.println("		}");
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Dashboard_add';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"MK_MAS_Dashboard_add';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_sub_charge\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Dashboard - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Dashboard - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_CODE.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_PAYEE_CODE.disabled=false;"); 
			out.println("document.Form1.TXT_SUB_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_ADD1.disabled=true;"); 
			out.println("document.Form1.TXT_PAYEE_ADD2.disabled=true;"); 
			out.println("document.Form1.TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.BUT_TXT_CITY_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_VAT_REG_NO.disabled=true;");
			out.println("document.Form1.TXT_WHT.disabled=true;");
			out.println("document.Form1.TXT_PAYEE_NAME.disabled=true;");
			
			
			out.println("}"); 
			
			
			
			
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
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
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data();");
			out.println("	}else"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		assign_help_sub_charge_code();"); 
			out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		assign_help_city_code();"); 
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
			out.println("	clear_data();");
			out.println("	}");
			
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
			
			
			
			
			out.println("function HelpView(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_View_Help_Servlet?class_in=\"+client_name+\"AF_MAS_View_help_select\"+"); 
			out.println("    \"&Sql_in=\"+m_sql+\"&Start_in=\"+Start+"); 
			out.println("    \"&End_in=\"+End+\"&Crit_In=\"+m_criteria+"); 
			out.println("    \"&Hid_No=\"+Hid_No, oBj,\"dialogWidth:40em; dialogHeight:25em; center:yes; status:no\");"); 
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		ViewNext(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	ViewPrev(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println("");
			
			out.println("function ViewPrev(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function ViewNext(Start,End,Hid_No){"); 
			out.println("    HelpView(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println("");
			
			
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_SUB_TYPE_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_SUB_TYPE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			out.println("function check_number_precent(obj,size){");
			out.println("if(obj.value!='' && obj.value!='-' ) ");
			out.println("if(isnumberok(obj,size)){ ");
			
			out.println("if( parseInt(obj.value) <= 100 ){ ");
			out.println("format_number(obj,size) ;");
			out.println("}");
			out.println("else");
			out.println("{");
			out.println("alert('Number can not exceed 100'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("}");
			out.println("}");
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("} ");
			out.println("} ");
			
			
			out.println("function help_sub_charge_code() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_sub_charges_help_liability\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_TYPE_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function assign_help_sub_charge_code() {"); 
			out.println("    document.Form1.TXT_SUB_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_city_code() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CITY_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CITY_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function assign_help_city_code() {"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_sub_charge_payee_code\";"); 
			//out.println("    m_criteria = document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"Y@\";"); //commented milinda 2014-01-20 
			//out.println("    HelpBox('1','10','0');"); 
			//out.println("}"); 
			
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");//added milinda 2014-01*20
			out.println("    m_criteria = document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"Y@\";"); 
			
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_PAYEE_CODE.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {");
			out.println("    document.Form1.TXT_PAYEE_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SUB_TYPE_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_PAYEE_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_PAYEE_ADD1.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_PAYEE_ADD2.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_CITY_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_VAT_REG_NO.value=oBj.valout[8];"); 
			out.println("    document.Form1.TXT_WHT.value=oBj.valout[9];"); 
			out.println("}"); 
			
			out.println("function sec() {");
			if(rs.next()){
				header_id=rs.getString(1);
				out.println("document.Form1.TXT_HEADER_DESC.value='"+rs.getString(2)+"' ");
				out.println("document.Form1.TXT_HEADER_COM_NAME.value='"+rs.getString(3)+"' ");
				out.println("document.Form1.TXT_HEADER_HEIGHT.value='"+rs.getString(4)+"' ");
			}
			out.println("}"); 	
			
			out.println("var lineno=0; ");
			out.println("var arr_size=0;");
			
			out.println("function load_grid_app_details(){");
			
			//*************  USE JQUERY GRIDS  ****************//
			//m_url="../../validations/PW_MK_sql_validate_duplication.jsp?chksql=get_sub_sections";
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_profit_analysis_report?chksql=run_report&date=\"+m_date;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"PW_MK_sql_validate_duplication?chksql=get_sub_sections\";	");
			//out.println("window.open")
			out.println("	jQuery(\"#verification_app_details\").jqGrid(");
			out.println("		{");
			out.println("			loadonce: 1,");
			out.println("			url:m_url,");
			out.println("			datatype: \"xml\", ");
			out.println("				colNames:['Sub Section ID','Description 1','Description 2', 'Height', 'Add Widget', 'Edit', 'Remove' ],");
			out.println("				colModel:[ ");
			out.println("			{name:'SUB_SEC_ID' 			,index:'SUB_SEC_ID' 	  	    , hidden:false	,width:100,align:\"center\" 	,formatter:application_no_fmatter},");
			out.println("			{name:'DESCRIPTION_1' 		,index:'DESCRIPTION_1' 		    , hidden:false	,width:100,align:\"center\" 	,formatter:application_description1},");
			out.println("			{name:'DESCRIPTION_2' 		,index:'DESCRIPTION_2' 			, hidden:false	,width:100,align:\"center\" 	,formatter:application_description2},");
			out.println("			{name:'HEIGHT' 				,index:'HEIGHT' 				, hidden:false	,width:100,align:\"center\" 	,formatter:application_height},");
			out.println("			{name:'DETAILS' 			,index:'DETAILS' 				, hidden:false	,width:200,align:\"center\" 	,formatter:detail_fmatter},");
			out.println("			{name:'EDIT' 				,index:'EDIT' 					, hidden:true	,width:200,align:\"center\" 	,formatter:edit_btn_fmatter},");
			out.println("			{name:'REMOVE' 				,index:'REMOVE' 				, hidden:false	,width:200,align:\"center\" 	,formatter:delete_btn_fmatter},");
			
			out.println("			],");
			out.println("			viewrecords: true, ");
			out.println("			hidegrid: false, ");
			out.println("			rownumbers:true,");
			out.println("			gridview: true,");
			out.println("			caption:\"Sub Sections\",");
			out.println("				height: 'auto',");
			out.println("				gridComplete: function () { ");
			//alert(document.Form1.elements["ROW_COUNT"].value);
			out.println("				var records_count = $(\"#verification_app_details\").jqGrid('getGridParam',\"records\");");
			out.println("				document.Form1.elements[\"row_count\"].value = records_count;");
			out.println("			},");
			out.println("			beforeShowForm :false");
			
			out.println("			}");
			out.println("		);");
			out.println("}");
			
			out.println("function application_description1(cellvalue, options, rowObject){");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"hidden\" name=\"HID_APP_DESC1_'+rowid+'\" value='+cellvalue+' > '+cellvalue; ");
			out.println("	return link;");
			
			out.println("}");
			
			out.println("function application_description2(cellvalue, options, rowObject){");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"hidden\" name=\"HID_APP_DESC2_'+rowid+'\" value='+cellvalue+' > '+cellvalue; ");
			out.println("	return link;");
			
			out.println("}");
			
			out.println("function application_height(cellvalue, options, rowObject){");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"hidden\" name=\"HID_APP_HEIGHT_'+rowid+'\" value='+cellvalue+' > '+cellvalue; ");
			out.println("	return link;");
			
			out.println("}");
			
			out.println("function delete_btn_fmatter (cellvalue, options, rowObject)");
			out.println("{");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"button\" style=\"width: 80px;\" onclick=\"delete_article_data('+rowid+');\"  value=\"Delete\" name=\"BUT_DELETE_DETAIL_'+rowid+'\" class=\"but_input\">'; ");
			out.println("	return link;	");
			out.println("}");
			/************ADD BUT ACTION***************/
			out.println("function cli_info(row_id){");
			//out.println("		m_url='add_graph_tables.jsp?APP_NO='+document.Form1.elements[\"HID_APP_NO_\"+row_id].value;");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"PW_MK_sql_validate_duplication?chksql=get_sub_sections\";	");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"MK_MAS_add_graph_tables?APP_NO=\"+document.Form1.elements[\"HID_APP_NO_\"+row_id].value+\"\";");
			out.println("		window.open(m_url,'displayWindow3','left=50,top=60,width=1100,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=1'); ");
			
			out.println("	}");
			
			/************DELETE BUT ACTION *******************/
			out.println("function delete_article_data(row_id){");
			//delete record from jq grid
			//jQuery('#ticket_details').jqGrid('delGridRow', row_id,{dataType: 'local',msg: 'Are you sure want to Delete this entry?',reloadAfterSubmit: false,url:"../../validations/PW_MK_sql_validations.jsp?chksql=get_article_record&mode=DELETE"});
			out.println("		if(confirm(\"Are you sure you want to delete ? \")){ ");
			out.println("			jQuery('#verification_app_details').jqGrid('delRowData', row_id);");
			//document.Form1.elements["row_count"].value =  parseInt(document.Form1.elements["row_count"].value) - 1;
			
			out.println("		}");
			out.println("	}");
			
			
			out.println("function edit_article_data(row_id){");
			out.println("	if(confirm(\"Are you sure you want to edit ? \")){ ");
			out.println("		document.Form1.TXT_SUB_SEC_ID.value 	= document.Form1.elements[\"HID_APP_NO_\"+row_id].value;");
			out.println("		document.Form1.TXT_SUB_DESC1.value		= document.Form1.elements[\"HID_APP_DESC1_\"+row_id].value;");
			out.println("		document.Form1.TXT_SUB_DESC2.value 	= document.Form1.elements[\"HID_APP_DESC2_\"+row_id].value;");
			out.println("		document.Form1.TXT_SUB_HEIGHT.value		= document.Form1.elements[\"HID_APP_HEIGHT_\"+row_id].value;");
					
					//remove edit record from grid
			out.println("		jQuery('#verification_app_details').jqGrid('delRowData', row_id);");
					//document.Form1.elements["row_count"].value =  parseInt(document.Form1.elements["row_count"].value) - 1;
					//cal_total_amount_existing_article();
			out.println("	}");
			out.println("}");			
			
			out.println("function edit_btn_fmatter (cellvalue, options, rowObject)");
			out.println("{");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"button\" style=\"width: 80px;\" onclick=\"edit_article_data('+rowid+');\" value=\"Edit\" name=\"BUT_EDIT_DETAIL_'+rowid+'\" class=\"but_input\">'; ");
			out.println("	return link;");	
			out.println("}");
			
			out.println("function detail_fmatter (cellvalue, options, rowObject)");
			out.println("{");
			out.println("	var rowid  = options['rowId'];");
			out.println("	var link ='<input type=\"button\" style=\"width: 80px;\" onclick=\"cli_info('+rowid+');\"  value=\"Add\" class=\"but_input\">'; ");
			out.println("	return link;");	
			out.println("} ");
			
			out.println("function application_no_fmatter (cellvalue, options, rowObject)");
			out.println("{");
			out.println("	var rowid = options['rowId'];");
			out.println("	var link ='<input type=\"hidden\" name=\"HID_APP_NO_'+rowid+'\" value='+cellvalue+' > '+cellvalue; ");
			out.println("	return link;");	
			out.println("}");
			//ADD BUT ACTION
			out.println("function add_row(){");
			out.println("	var m_status= 'ADD';");
			out.println("	if(lineno == 0){");
			//out.println("lineno**********"+lineno);
			out.println("		load_grid_app_details();");
			out.println("	}");
			out.println("	more_records();");
			//out.println("	clear_top_feilds();");
			
			out.println("}");
			//END BUT
			
			
			out.println("function more_records(){"); 
			out.println("	var sub_sec_id 	= document.Form1.elements[\"TXT_SUB_SEC_ID\"].value;"); 
			out.println("	var sub_des1 	= document.Form1.elements[\"TXT_SUB_DESC1\"].value;"); 
			out.println("	var sub_des2 	= document.Form1.elements[\"TXT_SUB_DESC2\"].value;"); 
			out.println("	var sub_height 	= document.Form1.elements[\"TXT_SUB_HEIGHT\"].value;"); 
			//temp
			//new_item_value = "25.000524";
			//new_loan_value = "30.255787";
			
			/*out.println("var data={\"SUB_SEC_ID\":\"\"+sub_sec_id+\"\" \",\""); 
			out.println("		\"DESCRIPTION_1\":\"\"+sub_des1+\"\" \",\""); 
			out.println("		\"DESCRIPTION_2\":\"\"+sub_des2+\"\" \",\" "); 
			out.println("		\"HEIGHT\":\"\"+sub_height+\"\" \",\" "); 
			out.println("		\"ADD_ROW\":\"\" \" \",\" ");
			out.println("	    \"EDIT_ROW\":\"\" \" \",\"");
			out.println("	    \"REMOVE_ROW\":\" \"\"} "); */
			//out.println("	}"); 
			//work before
			/*out.println("var data='{''SUB_SEC_ID':'\"+sub_sec_id+\"' \",\" "); 
			out.println("		'DESCRIPTION_1':'\"+sub_des1+\"' ,\",\" "); 
			out.println("		'DESCRIPTION_2':'\"+sub_des2+\"' \",\"  "); 
			out.println("		'HEIGHT':'\"+sub_height+\"' \",\" "); 
			out.println("		'ADD_ROW': '\"\"' \",\" ");
			out.println("	    'EDIT_ROW': '\"\"' \",\" ");
			out.println("	    'REMOVE_ROW': '\"\"' '}' "); 
			*/
			
			out.println("var data={SUB_SEC_ID:sub_sec_id, ");
			out.println("		DESCRIPTION_1:sub_des1, ");
			out.println("		DESCRIPTION_2:sub_des2, ");
			out.println("		HEIGHT:sub_height, ");
			out.println("		ADD_ROW:\" \",EDIT_ROW:\" \",REMOVE_ROW:\" \" ");
			out.println("	}");
			
			
			//	out.println("	 var data={\"SUB_SEC_ID\":\"\"+sub_sec_id+\"\",\"DESCRIPTION_1\":\"\"+sub_des1+\"\",\"DESCRIPTION_2\":\"\"+sub_des2+\"\",\"HEIGHT\":\"\"+sub_height+\"\",\"ADD_ROW\":\" \",\"EDIT_ROW\":\" \",\"REMOVE_ROW\":\"\ \" } ");
			
			out.println("	var lineno = parseInt(document.Form1.elements[\"row_count\"].value);"); 
			out.println("	jQuery('#verification_app_details').jqGrid('addRowData',lineno,data,'first'); "); 
			//document.Form1.elements["row_count"].value = lineno + 1;
			out.println("	}"); 
			
			
			
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"sec();load_grid_app_details();\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_val' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='row_count' value=0>");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"DASH\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='HID_HEADER_ID' value="+header_id+">");
			
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
			out.println("<td class='border_wht' valign='top'> "); 
			out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
			out.println("<tr> "); 
			out.println("<td height='30' class='pdn_mainHD'>"+header_name+"</td>"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Dashboard</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>"); //2014-01-20  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='10%'></td>");  
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
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			//out.println("conn"+conn.toString());
			out.println("<tr>"); 	
			out.println("<td width='10%' ><b>Header Details</b></td> "); 
			out.println("</tr>		"); 										
			
			
			
			
			/*	rs=stmt.executeQuery(" SELECT HEDER_ID, "+
					" DESC_1, "+
					" DESC_2, "+
					" HEIGHT_1 "+
					" FROM "+m_schema_name+".DH_DASH_HEADER ");
				boolean more = rs.next();
				if(more){*/
			
			
			
			
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Main Description *</DIV></td> "); 
			out.println("<td width='50%' ><input class='txt_input' value=\"\" type='text' name='TXT_HEADER_DESC' maxlength='100' size='200' onblur=\"\"></td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> "); 
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Company Name *</DIV></td> "); 
			out.println("<td width='40%' ><input class='txt_input' value=\"\" type='text' name='TXT_HEADER_COM_NAME' maxlength='100' size='200' onblur=\"\"></td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> 		"); 											
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Heading Height</DIV></td>");  
			out.println("<td width='40%' ><input class='txt_input' value=\"\" type='text' name='TXT_HEADER_HEIGHT' maxlength='100' size='200'></td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> "); 
			//more = rs.next();		
			
			//}
			
			
			out.println("</table>"); 
			out.println("<br><br>"); 
			
			
			out.println("<table align='center' width='100%' class='table'> "); 
			
			out.println("<td width='10%' ><b>Add Sub Sections</b></td>");  
			out.println("</tr>	"); 											
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Sub Section ID *</DIV></td> "); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_SEC_ID' maxlength='100' size='100' onblur=\"\"></td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> "); 
			
			out.println("	<tr > "); 
			
			out.println("<td width='30%' >Description 1 *</DIV></td> "); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_DESC1' maxlength='100' size='100' onblur=\"\"></td> "); 
			out.println("	<td width='*%'></td> "); 
			out.println("	</tr> 		"); 											
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Description 2</DIV></td> "); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_DESC2' maxlength='100' size='50'></td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> "); 
			
			out.println("<tr > "); 
			
			out.println("<td width='30%' >Height</DIV></td> "); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_HEIGHT' maxlength='100' size='50'>"); 
			out.println("<input type=\"button\" class='mainbut' onclick='add_row();' value=\"Add\"></td>  </td> "); 
			out.println("<td width='*%'></td> "); 
			out.println("</tr> "); 
			
			
			
			out.println("<tr>"); 
			out.println("<div ALIGN='LEFT'><table id=\"verification_app_details\" ALIGN='CENTER'></table></DIV>"); 		
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			
			out.println("<br> "); 
			out.println("<table align='center' width='100%'> "); 
			out.println("<tr> "); 
			out.println("<td width='100%' class='note'></td> "); 
			out.println("</tr> "); 
			out.println("</table> "); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
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