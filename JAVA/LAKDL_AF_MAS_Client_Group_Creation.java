//--
//SCREEN NAME	:
//CREATED BY	:NUWAN	
//DATE/TIME		:21-11-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_Client_Group_Creation extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs;
	Statement stmt;
	Connection conn;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			String m_schema_name=m_sn_methods.schema_name.trim();

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Conditions</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			out.println("var b_flag=0;"); 
			
			out.println("var array_client_code=new Array();");
			out.println("var array_client_name=new Array();");
			out.println("var array_client_type=new Array();");

			out.println("function get_vector(data_vec) {");
			out.println("if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_chk_status.value=='M1' && document.Form1.TXT_GROUP_CODE.value!=''){");
			out.println("	help_group();");
			out.println("			}");
			out.println("else	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_chk_status.value=='M1' && document.Form1.TXT_GROUP_CODE.value!=''){");

      out.println("    document.Form1.TXT_GROUP_CODE.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT_DESC.value=data_vec[2];"); 
			out.println("    display_member_details(document.Form1.TXT_GROUP_CODE.value);");
			out.println("			}");
			
			out.println("else	if(document.Form1.SCREEN_NAME.value==\"EDIT\" && document.Form1.hid_chk_status.value=='M_MEMBERS' && document.Form1.TXT_GROUP_CODE.value!=''){");
			out.println("display_members(data_vec);");
			out.println("			}");
			
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_reference_conditions&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_MASTER_CLIENT.value==\"\"){  "); 
			out.println("DIV_TXT_MASTER_CLIENT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 

			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
      out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ ");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Save_Client_Group';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
						out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 

			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Client_Group_Creation';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Client_Group_Creation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_reference_conditions\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"System Administration - Client Group Creation - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"System Administration - Client Group Creation - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_TXT_GROUP_CODE.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_TXT_GROUP_CODE.disabled=false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_TXT_GROUP_CODE.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value='New'");
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value='Modify'");

			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";");  
			out.println("document.Form1.hid_save.value='Deactivate'");

			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");  
			out.println("document.Form1.hid_save.value='Reactivate'");
		
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
			
			out.println("	if(oBj.valout[1] ==\" \"){");  //added by nuwan de silva 20-07-07
			out.println("	clear_data();");
			out.println("	}else"); 
			
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		assign_client_code(document.Form1.hid_row_no.value,oBj);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		assign_master_client();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		assign_group();"); 
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
			out.println("	clear_data();"); //added by nuwan de silva 20-07-07
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
			
			//added by nuwan de silva 20-07-07
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_CODE.value='';");
			out.println("document.Form1.TXT_DESCRIPTION.value='';");
			out.println("}");
			out.println("}");
			
			out.println(""); 
			out.println("function help_button_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_CODE.value=oBj.valout[2];"); 
			out.println("}"); 


			
	 //!-----------Function To Hold The Current Make Request -------------//
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
		//------------------------------------------------------------------------

			
			
			out.println("function help_group() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_client_group\";"); 
			out.println("    m_criteria = document.Form1.TXT_GROUP_CODE.value+\"@M@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function assign_group() {"); 
			out.println("    document.Form1.TXT_GROUP_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT_DESC.value=oBj.valout[4];"); 
			out.println("display_member_details(document.Form1.TXT_GROUP_CODE);");
			out.println("}"); 
			
			out.println("function display_member_details(obj) {");
			out.println("assignState('M_MEMBERS')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_get_client_group_members&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");



			out.println("function help_master_client() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"Client_code_help_client_creation\";"); 
			out.println("    m_criteria = document.Form1.TXT_MASTER_CLIENT.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function assign_master_client() {"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_MASTER_CLIENT_DESC.value=oBj.valout[4];"); 
			out.println("}"); 

			out.println("function help_button_client_code(rowNo) {"); 
			out.println("    m_client_code = \"TXT_CLIENT_CODE\"+rowNo;");
			out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"Client_code_help_client_creation\";"); 
			out.println("    m_criteria = document.Form1.elements[m_client_code].value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 


			out.println("function assign_client_code(rowNo,oBj) {"); 
			out.println("m_client_code=\"TXT_CLIENT_CODE\"+rowNo;");	
			out.println("m_client_name=\"TXT_CLIENT_NAME\"+rowNo");
			
			out.println("    document.Form1.elements[m_client_code].value=oBj.valout[2];"); 
			out.println("    document.Form1.elements[m_client_name].value=oBj.valout[4];"); 

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
			//out.println("		help_update_value_assign_99();"); 
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
			out.println("    m_sql = \"m_view_TXT_REFRENCE_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			//______________________________________________________________________________________________________________
		 	out.println("function header_members(){");
			out.println("m_table_members.innerHTML=\"\"");
			out.println("m_table_members.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\" ><TR >'+"); //class=pdn_txtpos2
			out.println("'<TD WIDTH=\"20%\" align=\"left\"><B>Client Code</B></TD>'+");
			out.println("'<TD WIDTH=\"35%\" align=\"left\"><B>Client Name</TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status of Member </TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" align=\"center\"></TD>'+");
			out.println("'</TR></table>';");
     	out.println("}");
				
				
			 out.println("function add_row_members(){"); 
			 out.println("b_flag=0;");
			 out.println("if(lineno!=0){");
			 out.println("count=lineno-1;");
			 out.println("m_client_code=\"TXT_CLIENT_CODE\"+count");
			 out.println("if(document.Form1.elements[m_client_code].value==\"\") {");
			 out.println("alert('Client Code can not be null.');");
			 out.println("b_flag=1;");
			 out.println("}");
			 out.println("}");
			 out.println("if(b_flag==0){");
			 out.println("m_table_members.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_CODE'+lineno+' maxlength=\"10\"  size=\"10\" onblur=\"\" >'+");
			 out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE'+lineno+' value=\"Help\" onClick=\"help_button_client_code('+lineno+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"35%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_NAME'+lineno+' style=\"width:300px; height:20px;\" maxlength=\"200\" size=\"200\" disabled onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"15%\"><SELECT name=TXT_TYPE'+lineno+' class=\"txt_input\">'+");
			 out.println("'<option value=\"HOLD_COMPANY\"  >Holding Company</option>'+");
			 out.println("'<option value=\"SUBS_COMPANY\"  >Subsidiary Company</option>'+");
			 out.println("'<option value=\"DIRECTOR\"      >Director</option>'+");
			 out.println("'<option value=\"OTHER\" SELECTED> Other</option>'+");
			 out.println("'</SELECT></TD>'+");
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row_members('+lineno+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>'+");
			 out.println("'</tr></table>';");
			 out.println("lineno=lineno+1;");
			 out.println("arr_size=arr_size+1;");
       out.println("}");
		   out.println("}");
				
				
      /*	out.println("<td width='40%' ><select name='TXT_DEFAULT_VALUE' class='txt_input'>");
			out.println("<option value=\"Y\"         >Yes</option>");
			out.println("<option value=\"N\" SELECTED>No </option>");
			out.println("</select>");	*/
				
				
				
			out.println("function display_members(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			
			out.println("header_members();");
			out.println("if(data_vec.length>0){");
			out.println("while(i<data_vec.length){");
			
			//out.println("alert(data_vec[i+2]);");
			
		 out.println("m_table_members.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
		 out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_CODE'+lineno+' maxlength=\"10\"   VALUE=\"'+data_vec[i]+'\" size=\"10\" onblur=\"\" >'+");
		 out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE'+lineno+' value=\"Help\" onClick=\"help_button_client_code('+lineno+')\"></TD>'+");
		 out.println("'<TD WIDTH=\"35%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_NAME'+lineno+' VALUE=\"'+data_vec[i+1]+'\" style=\"width:300px; height:20px;\" maxlength=\"200\" size=\"200\" disabled onblur=\"\">'+");
		 out.println("'<TD WIDTH=\"15%\"><SELECT name=TXT_TYPE'+lineno+' class=\"txt_input\" >'+");
		 out.println("'<option value=\"HOLD_COMPANY\"  >Holding Company</option>'+");
		 out.println("'<option value=\"SUBS_COMPANY\"  >Subsidiary Company</option>'+");
		 out.println("'<option value=\"DIRECTOR\"      >Director</option>'+");
		 out.println("'<option value=\"OTHER\"         > Other</option>'+");
		 out.println("'</SELECT></TD>'+");
		 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row_members('+lineno+')\"></TD>'+");
		 out.println("'<TD WIDTH=\"*%\" ></TD>'+");
		 out.println("'</tr></table>';");
			
      out.println("assign_state(i+2,lineno);");			
			out.println("i=i+3;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			
			out.println("else if(data_vec.length==0){");
			out.println("add_row_members()");
			out.println("}");
			out.println("}");


				
				
			 out.println("function del_row_members(rowNo){"); 
			 out.println("if(arr_size!=1){");
			 out.println("var j=0;");
			 out.println("for(var i=0;i<arr_size;i++){");
			 out.println("m_client_code=\"TXT_CLIENT_CODE\"+i");
			 out.println("m_client_name=\"TXT_CLIENT_NAME\"+i");
			 out.println("m_client_type=\"TXT_TYPE\"+i");	
			 out.println("if(i==rowNo)");
			 out.println("continue;");
			 out.println("array_client_code[j]          = document.Form1.elements[m_client_code].value;");
			 out.println("array_client_name[j]          = document.Form1.elements[m_client_name].value;");
			 out.println("array_client_type[j]          = document.Form1.elements[m_client_type].value;");	
		   out.println("j=j+1;");
			 out.println("}");
			 out.println("lineno=lineno-1;");
			 out.println("arr_size=arr_size-1;");
		   out.println("write_data_members(arr_size);");
			 out.println("}");
				
				
							 //added by nuwan de silva on 09-11-07----
			 out.println("function write_data_members(size){");
				
			 out.println("m_table_members.innerHTML=\"\";");
			 out.println("header_members();");
       out.println(" for(var j=0;j<size;j++){");
				
		   out.println("if(array_client_code[j]==\"\" && array_client_name[j]==\"\" && array_client_type[j]==\"\" ){");
		   out.println("m_table_members.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_CODE'+j+' maxlength=\"10\"  size=\"10\" onblur=\"\" >'+");
			 out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE'+j+' value=\"Help\" onClick=\"help_button_client_code('+j+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"35%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_NAME'+j+' style=\"width:300px; height:20px;\" maxlength=\"200\" size=\"200\" disabled onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"15%\"><SELECT name=TXT_TYPE'+lineno+' class=\"txt_input\">'+");
			 out.println("'<option value=\"HOLD_COMPANY\"  >Holding Company</option>'+");
			 out.println("'<option value=\"SUBS_COMPANY\"  >Subsidiary Company</option>'+");
			 out.println("'<option value=\"DIRECTOR\"      >Director</option>'+");
			 out.println("'<option value=\"OTHER\" SELECTED> Other</option>'+");
			 out.println("'</SELECT></TD>'+");	
				
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_members('+j+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>'+");
			 out.println("'</tr></table>';");
							
			 out.println("continue;");
			 out.println("}");
				
			 out.println("else if(array_client_code[j]!=\"\" && array_client_name[j]!=\"\" && array_client_type[j]!=\"\" ){");
				
			 out.println("m_table_members.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			 out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_CODE'+j+' maxlength=\"10\"   VALUE=\"'+array_client_code[j]+'\" size=\"10\" onblur=\"\" >'+");
			 out.println("'<input class=\"but_input\" type=\"button\" name=BUT_TXT_CLIENT_CODE'+j+' value=\"Help\" onClick=\"help_button_client_code('+j+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"35%\" align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_CLIENT_NAME'+j+' VALUE=\"'+array_client_name[j]+'\" style=\"width:300px; height:20px;\" maxlength=\"200\" size=\"200\" disabled onblur=\"\">'+");
			 out.println("'<TD WIDTH=\"15%\"><SELECT name=TXT_TYPE'+j+' class=\"txt_input\" VALUE=\"'+array_client_type[j]+'\">'+");
			 out.println("'<option value=\"HOLD_COMPANY\"  >Holding Company</option>'+");
			 out.println("'<option value=\"SUBS_COMPANY\"  >Subsidiary Company</option>'+");
			 out.println("'<option value=\"DIRECTOR\"      >Director</option>'+");
			 out.println("'<option value=\"OTHER\" SELECTED> Other</option>'+");
			 out.println("'</SELECT></TD>'+");	
			 out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row_members('+j+')\"></TD>'+");
			 out.println("'<TD WIDTH=\"*%\" ></TD>'+");
			 out.println("'</tr></table>';");
       
			 out.println("assign_status(j);");	
								
			 out.println("continue;");
			 out.println("}");
				
			 out.println("}");		
			 out.println("}");		
			 out.println("}");		


       out.println("function assign_status(line_no){");
       out.println("m_client_type=\"TXT_TYPE\"+line_no");		
			 out.println("document.Form1.elements[m_client_type].value = array_client_type[line_no];");	
				
       out.println("}");
				
			 out.println("function assign_state(k,line_no){"); 
			 out.println("m_client_type=\"TXT_TYPE\"+line_no");		
			 out.println("document.Form1.elements[m_client_type].value = data_vec[k];");	
				
       out.println("}");
	
				
			 	
				
				
				

			//_______________________________________________________________________________________________________________
			
			

			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),header_members(),add_row_members()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_row_no' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"New\">"); 
			
				
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Client Group Creation</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>"); 
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
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_GROUP_CODE'  class=div_input>Group Code</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_GROUP_CODE' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_GROUP_CODE' value=\"Help\" onClick=\"help_group()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MASTER_CLIENT'  class=div_input>Group Master</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MASTER_CLIENT' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_MASTER_CLIENT' value=\"Help\" onClick=\"help_master_client()\" ></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_MASTER_CLIENT_DESC'  class=div_input>Master Client Name</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_MASTER_CLIENT_DESC' maxlength='100' style={width:350px} size='100' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
					
			out.println("</table>"); 
			out.println("<br>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr align='left' class='tr_input'>");  
			out.println("<td width='*%'><b><u>Group Members</td> ");
			out.println("</tr>");  
			
			out.println("<tr align='left'>");  
			out.println("<td width='10'><input class='but_input' type='button' name='MORE_BUT_CON' value=\"Add\" onClick=\"add_row_members()\"></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_members'></DIV></td>");
			out.println("</tr>"); 
			out.println("</table>");


			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
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
