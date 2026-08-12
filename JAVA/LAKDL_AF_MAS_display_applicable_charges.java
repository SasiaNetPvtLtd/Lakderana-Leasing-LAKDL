//--
//SCREEN NAME	:SYSTEM ADMINISTRATION APPLICABLE CHARGES
//ID					:
//MODIFIED BY	:DELANJALI
//DATE/TIME		:
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_applicable_charges extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1,rs2;
	Statement stmt,stmt1;
	Connection conn;
	String reqstr;
	String m_chksql;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			
			String m_schema_name = m_sn_methods.schema_name;

			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			m_chksql=req.getParameter("chksql");
		
			 if(m_chksql.equals("main_page")){
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Applicable Charges By Sub Items</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_flag='1'");
			out.println("function get_vector(data_vec) {");
			out.println("    if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value==\"NEW\"&& document.Form1.TXT_ITEM_CAT_CODE.value !=''&& document.Form1.hid_help_state.value == '80' ){");
			out.println("   help_update();");
			out.println("   }");
			out.println("    }");
			
			out.println("   function assign_data(data_vec) { ");
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=data_vec[0]; ");
			out.println("    document.Form1.TXT_SUB_TYPE_CODE.value=data_vec[1]; ");
			out.println("    document.Form1.TXT_FUAL_TYPE_CODE.value=data_vec[2]; ");
			out.println("    getfromDate(data_vec[3]);");
			out.println("    gettoDate(data_vec[4]);");
			out.println("    document.Form1.TXT_AMOUNT.value=data_vec[5]; ");
			out.println("  format_number(document.Form1.TXT_AMOUNT,25); ");
			out.println("    document.Form1.TXT_PERCENTAGE.value=data_vec[6]; ");
			out.println("  format_number(document.Form1.TXT_PERCENTAGE,6); ");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=data_vec[7]; ");
			out.println("    }");
			
			out.println("function makeRequest(obj) {");
			out.println("    if(document.Form1.hid_help_status.value=='H9' && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_applicable_charges2&data_val=\"+document.Form1.TXT_ITEM_CAT_CODE.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("    load_interface(m_url,'XML');");
			out.println("    }"); 

			out.println("function makeRequest_item(val) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.TXT_ITEM_CAT_CODE.value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_display_applicable_charges?chksql=item_details&m_val=&m_item_code=\"+val+\"\";");
			out.println("load_interface(m_url,'NORM');");
			out.println("}");
			out.println("}");


			out.println("function get_vector_normal(http_response) {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println(" item_details.innerHTML = ''; ");
			out.println(" item_details.innerHTML = http_response; ");
			out.println(" ");
  		out.println("}");
			out.println("}");
			

			out.println(" function assign_help_status(obj){");   
      out.println("    document.Form1.hid_help_status.value =obj; ");
      out.println("    }");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			
			out.println("if(document.Form1.TXT_ITEM_CAT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_ITEM_CAT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ ");
			out.println("		if(validate_data()){");
			out.println("if(document.Form1.TXT_ITEM_CAT_CODE.value!=\"\"){");		
			out.println("for(var i=0;i<document.Form1.hid_count.value;i++){");
			out.println("if(document.Form1.elements[\"CHK_STATUS_\"+i].value==\"on\"){");		
			out.println("if(document.Form1.elements[\"TXT_FROM_DATE_DD_\"+i].value==\"\" && document.Form1.elements[\"TXT_FROM_DATE_MM_\"+i].value==\"\" && document.Form1.elements[\"TXT_FROM_DATE_YY_\"+i].value==\"\"){");
			out.println("alert('Please enter From date')");
			out.println("document.Form1.elements[\"TXT_FROM_DATE_DD_\"+i].focus();");
			out.println("m_flag=0");
			out.println("break;");
			out.println("}");
  		out.println("else if(document.Form1.elements[\"TXT_TO_DATE_DD_\"+i].value==\"\" && document.Form1.elements[\"TXT_TO_DATE_MM_\"+i].value==\"\" && document.Form1.elements[\"TXT_TO_DATE_YY_\"+i].value==\"\"){");
			out.println("alert('Please enter To date')");
			out.println("document.Form1.elements[\"TXT_TO_DATE_DD_\"+i].focus();");
			out.println("m_flag=0");
			out.println("break;");
			out.println("}");
			out.println("else{");
			out.println("m_flag=1");
			out.println("}");
			out.println("}");
			out.println("}");
			
			out.println("if(m_flag!=\"0\"){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_applicable_charges?number='+document.Form1.hid_count.value+'';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}");
			out.println("} "); 
			out.println("} ");
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 
			
			
			out.println("function load_lock(){	"); 
		//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_applicable_charges?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_applicable_charges?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	");
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_applicable_charges\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Applicable Charges By Sub Items  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Applicable Charges By Sub Items  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function val_from_date(){ ");
			out.println("if((document.Form1.TXT_FROM_DD.value !=\"\")&&(document.Form1.TXT_FROM_MM.value !=\"\")&&(document.Form1.TXT_FROM_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_FROM_DD,document.Form1.TXT_FROM_MM,document.Form1.TXT_FROM_YY);");
			out.println("}");
			out.println("}");
			
			
			out.println("function val_to_date(){ ");
			out.println("if((document.Form1.TXT_TO_DD.value !=\"\")&&(document.Form1.TXT_TO_MM.value !=\"\")&&(document.Form1.TXT_TO_YY.value !=\"\")){");
			out.println("checkMonthLength(document.Form1.TXT_TO_DD,document.Form1.TXT_TO_MM,document.Form1.TXT_TO_YY);");
			out.println("}");
			out.println("}");
			
			
			out.println("function chk_validity(row){  ");			
      out.println("if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_DD_\"+row].value))>=(parseInt(document.Form1.elements[\"TXT_TO_DATE_DD_\"+row].value))){");
      out.println("if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_MM_\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_TO_DATE_MM_\"+row].value))){");
      out.println("if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value))){");
      out.println(" if(((parseInt(document.Form1.elements[\"TXT_FROM_DATE_DD_\"+row].value))==(parseInt(document.Form1.elements[\"TXT_TO_DATE_DD_\"+row].value)))&&");
      out.println("((parseInt(document.Form1.elements[\"TXT_FROM_DATE_MM_\"+row].value))==(parseInt(document.Form1.elements[\"TXT_TO_DATE_MM_\"+row].value)))&&");
      out.println("((parseInt(document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value))==(parseInt(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value)))){");
      out.println("}");
      out.println("else if(((parseInt(document.Form1.elements[\"TXT_FROM_DATE_DD_\"+row].value))>(parseInt(document.Form1.elements[\"TXT_TO_DATE_DD_\"+row].value)))&&");
      out.println("((parseInt(document.Form1.elements[\"TXT_FROM_DATE_MM_\"+row].value))==(parseInt(document.Form1.elements[\"TXT_TO_DATE_MM_\"+row].value)))&&");
      out.println(" ((parseInt(document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value))==(parseInt(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value)))){");
      out.println("      alert('To Date should be greater than From Date');");
			
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println(" alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value))>=(parseInt(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value))){");
      out.println("    alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_MM_\"+row].value))<=(parseInt(document.Form1.elements[\"TXT_TO_DATE_MM_\"+row].value))){");
      out.println("  if((document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value)<=(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value)){");
      out.println(" }");
      out.println(" else{");
      out.println("   alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(document.Form1.elements[\"TXT_FROM_DATE_YY_\"+row].value))<(parseInt(document.Form1.elements[\"TXT_TO_DATE_YY_\"+row].value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("  alert('To Date should be greater than From Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("document.Form1.elements[\"TXT_FROM_DATE_DD_\"+row].focus();");
			out.println("return true;");
      out.println("}");
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_SUB_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FUAL_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FROM_DD.disabled=true;");
			out.println("document.Form1.TXT_FROM_MM.disabled=true;");
			out.println("document.Form1.TXT_FROM_YY.disabled=true;");
			out.println("document.Form1.TXT_TO_DD.disabled=true;");
			out.println("document.Form1.TXT_TO_MM.disabled=true;");
			out.println("document.Form1.TXT_TO_YY.disabled=true;");		
			out.println("document.Form1.TXT_AMOUNT.disabled=true;"); 
			out.println("document.Form1.TXT_PERCENTAGE.disabled=true;"); 
			out.println("document.Form1.TXT_DEFAULT_VALUE.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.TXT_SUB_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FUAL_TYPE_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_FROM_DD.disabled=true;");
			out.println("document.Form1.TXT_FROM_MM.disabled=true;");
			out.println("document.Form1.TXT_FROM_YY.disabled=true;");
			out.println("document.Form1.TXT_TO_DD.disabled=true;");
			out.println("document.Form1.TXT_TO_MM.disabled=true;");
			out.println("document.Form1.TXT_TO_YY.disabled=true;");		
			out.println("document.Form1.BUT_TXT_FUAL_TYPE_CODE.disabled=true;");		
			out.println("document.Form1.BUT_TXT_SUB_TYPE_CODE.disabled=true;");		
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
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
			out.println("if(oBj.valout[4]==\"\"){");
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value=\"\"");
			out.println(" item_details.innerHTML = ''; ");
			out.println("}");
			
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}");
			
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
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
			
			
			out.println("function clear_data() {");
			out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.value='';"); 
			out.println("document.Form1.TXT_ITEM_SUB_CAT.focus();"); 
			out.println(" item_details.innerHTML = ''; ");
	  	out.println("		}");
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("document.Form1.TXT_SUB_TYPE_CODE.value='';");
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("document.Form1.TXT_FUAL_TYPE_CODE.value='';"); 
	  	out.println("		}"); 
			out.println("}");
			
			

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
			out.println("    m_sql = \"m_help_TXT_SUB_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_SUB_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_FUAL_TYPE_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_FUAL_TYPE_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.TXT_FUAL_TYPE_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			
			/*out.println("function help_update() {");
			out.println(" item_details.innerHTML = ''; ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("  help_Item_Sub();");
			out.println("    } ");
			out.println("    else{");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_1\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			out.println("}");
			*/
			
		  out.println("function help_update() {");
			out.println(" item_details.innerHTML = ''; ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.hid_help_type.value=\"99\";");
			out.println("  help_Item_Sub();");
			out.println("    } ");
			out.println("    else{");
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql_1\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','5');"); 
			out.println("}"); 
			out.println("}");


			out.println("function help_Item_Sub() {");
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			out.println("    m_sql = \"m_help_TXT_ITEM_CAT_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_ITEM_CAT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			
			
			
			/*out.println("function help_Item_Sub() {");
			out.println("    document.Form1.hid_help_type.value=\"3\";");
			out.println("    m_sql = \"m_help_TXT_ITEM_SUB_CAT_sql\";");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','2');"); 
			out.println("}"); 
			*/
			
			out.println("function help_value_assign_3() {"); 
			out.println("    document.Form1.TXT_ITEM_CAT_CODE.value=oBj.valout[2];"); 
			out.println("makeRequest_item(document.Form1.TXT_ITEM_CAT_CODE.value)");
			out.println("}");
			
			out.println("function assign_val_new() {"); 
			out.println("    document.Form1.hid_help_state.value=\"80\";");
			out.println("}");

			out.println("function help_update_value_assign_99() {"); 
			
			out.println("    document.Form1.TXT_ITEM_SUB_CAT.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SUB_TYPE_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_FUAL_TYPE_CODE.value=oBj.valout[4];");
			out.println("    getfromDate(oBj.valout[5]);");
			out.println("    gettoDate(oBj.valout[6]);");
			out.println("    document.Form1.TXT_AMOUNT.value=oBj.valout[7];");
			out.println("    format_number(document.Form1.TXT_AMOUNT,18); ");
			out.println("    document.Form1.TXT_PERCENTAGE.value=oBj.valout[8];");
			out.println("    format_number(document.Form1.TXT_PERCENTAGE,6); ");
			out.println("    document.Form1.TXT_DEFAULT_VALUE.value=oBj.valout[9];"); 
			out.println("makeRequest_item(document.Form1.TXT_ITEM_CAT_CODE.value)");
			out.println("}"); 
			
			
			out.println("function getfromDate(dval){");
			out.println("document.Form1.TXT_FROM_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_FROM_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_FROM_YY.value=dval.substring(6,10)");
			out.println("}");
			
			out.println("function gettoDate(dval){");
			out.println("document.Form1.TXT_TO_DD.value=dval.substring(0,2)");
			out.println("document.Form1.TXT_TO_MM.value=dval.substring(3,5)");
			out.println("document.Form1.TXT_TO_YY.value=dval.substring(6,10)");
			out.println("}");
			
			
			out.println("function val_amount(obj){");
		  out.println(" format_number(document.Form1.TXT_AMOUNT,18); ");
			out.println("}");
			
			out.println("function val_presentage(obj){");
			out.println(" format_number(document.Form1.TXT_PERCENTAGE,6); ");
			out.println("}");
			
			
			out.println("function check_number(obj,size,m_val,row){");
			out.println("if(document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value==\"\" && document.Form1.elements[\"TXT_AMOUNT_\"+row].value==\"\") ");
			out.println("alert('Please enter either rate or amount');");
			out.println("else");
			out.println("if(document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value!=\"\" && document.Form1.elements[\"TXT_AMOUNT_\"+row].value!=\"\") ");
			out.println("alert('Please enter either rate or amount');");
			out.println("if(m_val==\"M1\" && document.Form1.TXT_AMOUNT.value!=\"\")");
			out.println("document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value=\"0.00\"");
			out.println("if(m_val==\"M2\" && document.Form1.TXT_PERCENTAGE.value!=\"\")");
			out.println("document.Form1.elements[\"TXT_AMOUNT_\"+row].value=\"0.00\"");
			out.println("}");
			
			
			
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
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
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
			out.println("    m_sql = \"m_view_TXT_ITEM_SUB_CAT_sql\";");
			out.println("    m_criteria = document.Form1.TXT_ITEM_SUB_CAT.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
				
			out.println("function load_calendar(num,row) {");
		  out.println(" document.Form1.hid_row.value=row;"); 
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
			out.println("m_row=document.Form1.hid_row.value");

      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.elements[\"TXT_TO_DATE_DD_\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_TO_DATE_MM_\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_TO_DATE_YY_\"+m_row].value=v_yy;");
			out.println("fr_dd='TXT_FROM_DATE_DD_'+m_row;");
			out.println("fr_mm='TXT_FROM_DATE_MM_'+m_row;");
			out.println("fr_yy='TXT_FROM_DATE_YY_'+m_row;");
			out.println("to_dd='TXT_TO_DATE_DD_'+m_row;");
			out.println("to_mm='TXT_TO_DATE_MM_'+m_row;");
			out.println("to_yy='TXT_TO_DATE_YY_'+m_row;");
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
			out.println("     document.Form1.elements[\"TXT_FROM_DATE_DD_\"+m_row].value=v_dd;");
			out.println("     document.Form1.elements[\"TXT_FROM_DATE_MM_\"+m_row].value=v_mm;");
			out.println("     document.Form1.elements[\"TXT_FROM_DATE_YY_\"+m_row].value=v_yy;");
			out.println("fr_dd='TXT_FROM_DATE_DD_'+m_row;");
			out.println("fr_mm='TXT_FROM_DATE_MM_'+m_row;");
			out.println("fr_yy='TXT_FROM_DATE_YY_'+m_row;");
			out.println("to_dd='TXT_TO_DATE_DD_'+m_row;");
			out.println("to_mm='TXT_TO_DATE_MM_'+m_row;");
			out.println("to_yy='TXT_TO_DATE_YY_'+m_row;");
			out.println("  }");	
			out.println(" if((document.Form1.elements[fr_dd].value !=\"\")&&(document.Form1.elements[fr_mm].value !=\"\")&&(document.Form1.elements[fr_yy].value !=\"\") && (document.Form1.elements[to_dd].value !=\"\")&&(document.Form1.elements[to_mm].value !=\"\")&&(document.Form1.elements[to_yy].value !=\"\")){");
			out.println("chk_validity(m_row)");
			out.println("}");	
			out.println("}");				

			
			out.println("function check_date_from(ln){ ");
			out.println("ind_dd='TXT_FROM_DATE_DD_'+ln;");
			out.println("ind_mm='TXT_FROM_DATE_MM_'+ln;");
			out.println("ind_yy='TXT_FROM_DATE_YY_'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")||(document.Form1.elements[ind_mm].value !=\"\")||(document.Form1.elements[ind_yy].value !=\"\")){"); // Change && to || by Chatura Jayawardena
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("to_dd='TXT_FROM_DATE_DD_'+ln;");
			out.println("to_mm='TXT_FROM_DATE_MM_'+ln;");
			out.println("to_yy='TXT_FROM_DATE_YY_'+ln;");
			out.println("ChkValidity(document.Form1.elements[to_dd],document.Form1.elements[to_mm],document.Form1.elements[to_yy],document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy])");
			out.println("}");


			out.println("function check_date_to(ln){ ");
			out.println("ind_dd='TXT_TO_DATE_DD_'+ln;");
			out.println("ind_mm='TXT_TO_DATE_MM_'+ln;");
			out.println("ind_yy='TXT_TO_DATE_YY_'+ln;");
			out.println(" if((document.Form1.elements[ind_dd].value !=\"\")||(document.Form1.elements[ind_mm].value !=\"\")||(document.Form1.elements[ind_yy].value !=\"\")){");  // Change && to || by Chatura Jayawardena
			out.println("  checkMonthLength(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy]);");
			out.println(" }");
			out.println("fr_dd='TXT_FROM_DATE_DD_'+ln;");
			out.println("fr_mm='TXT_FROM_DATE_MM_'+ln;");
			out.println("fr_yy='TXT_FROM_DATE_YY_'+ln;");
			out.println("ChkValidity(document.Form1.elements[ind_dd],document.Form1.elements[ind_mm],document.Form1.elements[ind_yy],document.Form1.elements[fr_dd],document.Form1.elements[fr_mm],document.Form1.elements[fr_yy])");
			out.println("}");
			
			
			out.println("function check_amt(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_AMOUNT_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(document.Form1.elements[nt].value!=\"\" && document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value==\"\"){");
			out.println("format_number(document.Form1.elements[nt],20)");
			out.println("}");
			out.println("if(document.Form1.elements[nt].value!=\"\" && document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value!=\"\"){");
			out.println("alert('Please enter either rate or amount');");
			out.println("document.Form1.elements[nt].value=\"\""); 
			out.println("}");
			out.println("if(document.Form1.elements[\"TXT_PERCENTAGE_\"+row].value!=\"\"){");
			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].disabled=false");
			out.println("}");
			out.println("else{");
			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].value=\"\"");
			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].disabled=true");
			out.println("}");
			out.println("}");


			out.println("function check_per(row) {");
			out.println("var m_value;");
			out.println("var m_size;");
			out.println("var valno;");
			out.println("var inputStr;");
			out.println("    nt=\"TXT_PERCENTAGE_\"+row;");
			out.println("valno	=    document.Form1.elements[nt].value;"); 
			out.println("valno=valno.toUpperCase();");
			out.println("m_size=valno.length;");
			out.println("if(document.Form1.elements[nt].value!=\"\" && document.Form1.elements[\"TXT_AMOUNT_\"+row].value==\"\"){");
			out.println("format_number(document.Form1.elements[nt],10)");
			out.println("}");
			out.println("if(document.Form1.elements[nt].value!=\"\" && document.Form1.elements[\"TXT_AMOUNT_\"+row].value!=\"\"){");
			out.println("alert('Please enter either rate or amount');");
			out.println("document.Form1.elements[nt].value=\"\""); 
			out.println("}");
			out.println("if(document.Form1.elements[nt].value!=\"\"){");
			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].disabled=false");
			out.println("}");
			out.println("else{");
			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].value=\"\"");

			out.println("document.Form1.elements[\"TXT_TYPE_\"+row].disabled=true");
			out.println("}");
			out.println("}");
			
			out.println("function change(row_no){")	;
			out.println("m_chk_status=\"CHK_STATUS_\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_status].checked==true){");
			out.println("document.Form1.elements[m_chk_status].value='on'");
			out.println("}else if(document.Form1.elements[m_chk_status].checked==false){");
			out.println("document.Form1.elements[m_chk_status].value='off'");
			out.println("}");	
			out.println("}");	
//************************************************************************************************************************************************
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_state' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_state1' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_row' VALUE=\"0\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Applicable Charges By Sub Items </td>"); 
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


			out.println("<br >"); 
			out.println("<table align='center' width='100%' class='table'>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_ITEM_SUB_CAT'  class=div_input>Item Sub Category *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ITEM_SUB_CAT' maxlength='10' size='10' onblur=\"assign_help_status('H9'),assign_val_new(),makeRequest_item(document.Form1.TXT_ITEM_SUB_CAT.value),makeRequest(document.Form1.TXT_ITEM_SUB_CAT)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); */
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='div_TXT_ITEM_CAT_CODE'  class=div_input>Item Category *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_ITEM_CAT_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H9'),assign_val_new(),makeRequest_item(document.Form1.TXT_ITEM_CAT_CODE.value),makeRequest(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
			out.println("<td ><div id=item_details></div></td></tr></table>");

			out.println("<br>"); 
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
		
		else if(m_chksql.equals("item_details")){		
			int i=0;		
			int x=0;	
			String m_item_code = req.getParameter("m_item_code");	
			String m_screen_name= req.getParameter("m_val");	
	
	/*
			rs = stmt.executeQuery ("SELECT a.ITEM_SUB_CAT,a.SUB_TYPE_CODE,(SELECT TYPE_CODE from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES WHERE SUB_TYPE_CODE=a.SUB_TYPE_CODE), "+
			"(SELECT DESCRIPTION from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=a.SUB_TYPE_CODE),a.FUAL_TYPE_CODE,a.FROM_DATE,a.TO_DATE,a.AMOUNT,a.PERCENTAGE,'',a.DEFAULT_VALUE,a.PERCENTAGE_TYPE "+
			" from (SELECT ITEM_SUB_CAT ,SUB_TYPE_CODE,'','',FUAL_TYPE_CODE, TO_CHAR(FROM_DATE,'DD-MM-YYYY') as FROM_DATE,TO_CHAR(TO_DATE,'DD-MM-YYYY') as TO_DATE, "+
			"	NVL(TO_CHAR(AMOUNT,'999,999,999,999.99'),'') AS AMOUNT,NVL(TO_CHAR(PERCENTAGE,'999,999,999,999,999,999.99'),'') AS PERCENTAGE,'',DEFAULT_VALUE,PERCENTAGE_TYPE "+
			"	FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
			"	WHERE ITEM_SUB_CAT=UPPER('"+m_item_code+"') AND ACTIVE_STATUS=('Y'))a "+
			"	UNION ALL "+
			"	SELECT '',SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION,'','','','','',MAINTENANCE_STATUS,'','' "+
			"	FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			"	WHERE ACTIVE_STATUS=('Y') "+
			" AND SUB_TYPE_CODE NOT IN (SELECT SUB_TYPE_CODE "+
			"	FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
			"	WHERE ITEM_SUB_CAT=UPPER('"+m_item_code+"') AND ACTIVE_STATUS=('Y')) "); */ //'999,999,999,999.99'
			
			
			
			rs = stmt.executeQuery ("SELECT ITEM_SUB_CAT,SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION ,FUAL_TYPE_CODE,FROM_DATE,TO_DATE,AMOUNT,PERCENTAGE,MAINTENANCE_STATUS,DEFAULT_VALUE,PERCENTAGE_TYPE "+
			" FROM( "+
			" SELECT a.ITEM_SUB_CAT,a.SUB_TYPE_CODE,(SELECT TYPE_CODE from LAKDL.AF_CO_MAS_SUB_CHARGES WHERE SUB_TYPE_CODE=a.SUB_TYPE_CODE) TYPE_CODE, "+
			" (SELECT DESCRIPTION from "+m_schema_name+".AF_CO_MAS_SUB_CHARGES where SUB_TYPE_CODE=a.SUB_TYPE_CODE) DESCRIPTION,a.FUAL_TYPE_CODE,a.FROM_DATE,a.TO_DATE,a.AMOUNT,a.PERCENTAGE,'' MAINTENANCE_STATUS,a.DEFAULT_VALUE,a.PERCENTAGE_TYPE "+
			" from (SELECT ITEM_SUB_CAT ,SUB_TYPE_CODE,'','',FUAL_TYPE_CODE, TO_CHAR(FROM_DATE,'DD-MM-YYYY') as FROM_DATE,TO_CHAR(TO_DATE,'DD-MM-YYYY') as TO_DATE, "+
			" NVL(TO_CHAR(AMOUNT,'999,999,999,999,999,999.99'),'') AMOUNT,NVL(TO_CHAR(PERCENTAGE,'999,999,999,999,999,999.99'),'') AS PERCENTAGE,'' MAINTENANCE_STATUS,DEFAULT_VALUE,PERCENTAGE_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
			" WHERE ITEM_SUB_CAT=UPPER('"+m_item_code+"') AND ACTIVE_STATUS=('Y'))a "+
			" UNION ALL "+
			" SELECT '',SUB_TYPE_CODE,TYPE_CODE,DESCRIPTION,'','','','','',MAINTENANCE_STATUS,'' DEFAULT_VALUE,'' PERCENTAGE_TYPE "+
			" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
			" WHERE ACTIVE_STATUS=('Y') "+
			" AND SUB_TYPE_CODE NOT IN (SELECT SUB_TYPE_CODE "+
			" FROM "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE "+
			" WHERE ITEM_SUB_CAT=UPPER('"+m_item_code+"') AND ACTIVE_STATUS=('Y'))) "+
			" ORDER BY DESCRIPTION ASC ");		
			
		
			boolean more = rs.next();

			out.println("<br>");			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
			out.println("<br>");			
	

			out.println("<tr class=pdn_txtpos2>");
			//out.println("<td width='25%' >Sub Charges Code </td>"); // commented by udara 29-08-2017
			out.println("<td width='25%' >Sub Charge Name </td>"); // added by udara 29-08-2017
		    out.println("<td width='10%' >Sub Charge Code </td>"); // added by udara 29-08-2017
		//	out.println("<td width='10%' >Fuel Type </td>"); 
			out.println("<td width='20%' >From Date </td>"); 
			out.println("<td width='20%' >To Date </td>"); 
			out.println("<td width='8%' align=right>Amount </td>"); 
			out.println("<td width='8%' align=right>Percentage </td>"); 
			out.println("<td width='5%' align=center>Default</td>");
			out.println("<td width='5%' align=center>Type</td>");

			out.println("<td width='5%' >Status</td>");
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
			out.println("<tr >"); 
			out.println("</tr >"); 
	

		  int j = 0; 
			while(more){


			out.println("<tr><td width='25%' >"+rs.getString(4)+"<input class='txt_input' type='hidden' name='TXT_SUB_TYPE_CODE_"+j+"' maxlength='10' size='10'  value=\""+rs.getString(2)+"\" onblur=\"assign_help_status('H1'),makeRequest(document.Form1.TXT_SUB_TYPE_CODE)\"></td>");
			out.println("<td width='10%' >"+rs.getString(2)+"</td>"); // added by udara 29-08-2017
			
			String m_fual=rs.getString(5);
			
		/*	out.println("<td width=\"10%\" ><select class=\"txt_input2\" type=\"text\" name=TXT_FUAL_TYPE_CODE_"+j+" maxlength=1 style=\"width:100px\" >");  

																
			rs2 = stmt1.executeQuery("SELECT CODE,DESCRIPTION,DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_FUEL_TYPE "+
			"WHERE ACTIVE_STATUS=('Y') "+
			"ORDER BY CODE ASC ");

			 boolean more1 = rs2.next();		
			while(more1){
			if(rs2.getString(1).equals(rs.getString(5))){
			out.println("<option value="+rs2.getString(1)+" selected>"+rs2.getString(2)+"</option>");
			}
			else if(!rs2.getString(1).equals(rs.getString(5))){
			out.println("<option value="+rs2.getString(1)+" >"+rs2.getString(2)+"</option>");
			}
			more1=rs2.next();
			}
			
			out.println("</select>");
			out.println("</td> ");
			*/
			
			
			if(rs.getString(6)==null){
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
			out.println("</td> ");
			}
			else {
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(6).substring(0,2)+"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\""+rs.getString(6).substring(3,5)+"\" onblur=\"check_date_from("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(6).substring(6,10)+"\" onblur=\"check_date_from("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('1',"+j+")>   Calendar</a> ");	
			out.println("</td> ");
			}
			
			if(rs.getString(7)==null){
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_to("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_to("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_to("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")>   Calendar</a> ");	
			out.println("</td> ");
			}
			else {
			out.println("<TD WIDTH=\"20%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(7).substring(0,2)+"\" onblur=\"check_date_to("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\""+rs.getString(7).substring(3,5)+"\" onblur=\"check_date_to("+j+")\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\""+rs.getString(7).substring(6,10)+"\" onblur=\"check_date_to("+j+")\"><a href style='{cursor:hand; }' onclick=load_calendar('2',"+j+")>   Calendar</a> ");	
			out.println("</td> ");
			}
			
			if(rs.getString(8)!=null){
			out.println("<td width='8%' ><input class='txt_input' type='text' name='TXT_AMOUNT_"+j+"' maxlength='15'  style='{width:100px;text-align:right;}' value=\""+rs.getString(8).trim()+"\" onblur=\"check_amt("+j+")\"></td>"); 
			}
			else if(rs.getString(8)==null){
			out.println("<td width='8%' ><input class='txt_input' type='text' name='TXT_AMOUNT_"+j+"' maxlength='15'  style='{width:100px;text-align:right;}' value=\"\" onblur=\"check_amt("+j+")\"></td>"); 
			}
			if(rs.getString(9)!=null){
			out.println("<td width='8%' ><input class='txt_input1' style=\"width:100px;text-align:right;\" type='text' name='TXT_PERCENTAGE_"+j+"' maxlength='2'  value=\""+rs.getString(9)+"\" onblur=\"check_per("+j+")\"></td>"); 
			}
			else if(rs.getString(9)==null){
			out.println("<td width='8%' ><input class='txt_input1' style=\"width:100px;text-align:right;\" type='text' name='TXT_PERCENTAGE_"+j+"' maxlength='2'   value=\"\" onblur=\"check_per("+j+")\"></td>"); 
			}

			
			out.println("<td width='5%' ><select class='txt_input5' type='text' name='TXT_DEFAULT_VALUE_"+j+"' maxlength='1' size='1'>");
			
			String m_default=rs.getString(11);
			
			if(m_default==null){
			m_default="N";
			}
			if(m_default.equals("Y")){
			out.println("<option value='N' > No </option>");
			out.println("<option value='Y' selected> Yes </option>");


			}
			
			else if(m_default.equals("N")){
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");


			}
		
			else{
			out.println("<option value='N' selected> No </option>");
			out.println("<option value='Y' > Yes </option>");

			}
	

			out.println("</select>");
			out.println("</td>");
			
			String m_type=rs.getString(12);

			if(m_type==null){
			m_type="";
			out.println("<td width='9%' ><select class='txt_input1' type='text' name='TXT_TYPE_"+j+"' maxlength='1' size='1' disabled>");
			}
			else{
			out.println("<td width='9%' ><select class='txt_input1' type='text' name='TXT_TYPE_"+j+"' maxlength='1' size='1' >");
			}		
	
			
			if(m_type.equals("G")){
			out.println("<option value=\"\" > -- </option>");
			out.println("<option value='NET' > Net </option>");			
			out.println("<option value='VAT' > Vat </option>");
			out.println("<option value='GROSS' selected> Gross </option>");
			}
			
			else if(m_type.equals("N")){
			out.println("<option value=\"\" > -- </option>");
			out.println("<option value='NET' selected> Net </option>");
			out.println("<option value='VAT' > Vat </option>");
			out.println("<option value='GROSS' > Gross </option>");
			}
			else if(m_type.equals("")){
			out.println("<option value=\"\" selected> -- </option>");
			out.println("<option value='NET' > Net </option>");
			out.println("<option value='VAT' > Vat </option>");
			out.println("<option value='GROSS' > Gross </option>");
			}
		
			else{out.println("<option value=\"\" > -- </option>");
			out.println("<option value='NET' > Net </option>");
			out.println("<option value='VAT' > Vat </option>");
			out.println("<option value='GROSS' selected> Gross </option>");
			}
	

			out.println("</select>");

			out.println("</td>");

			if(rs.getString(1)==null){
			out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"off\" unchecked onclick=\"change("+j+")\"></td>");
			}
			else if(rs.getString(1)!=null){
			out.println("<td width=\"5%\" align=center><input type=\"checkbox\" name=CHK_STATUS_"+j+" value=\"on\" checked onclick=\"change("+j+")\"></td>");
			}


			out.println("<input class=\"txt_input2\" type=\"hidden\" name=TXT_FUAL_TYPE_CODE_"+j+" value=\"All\" maxlength=1 style=\"width:100px\" >");  
	

			more=rs.next();
			j=j+1;
			if (!more)
			{
			break;
			}
				
			}	

			out.println("<input type=hidden name=hid_count value="+j+">");
			out.println("</table>");
		

}


		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
