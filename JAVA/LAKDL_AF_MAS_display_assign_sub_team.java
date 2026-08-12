
//ID         :1.72 Marketing Team Process
//SCREEN NAME:SYSTEM ADMINISTRATION - ASSIGN TEAM MEMBERS
//CREATED BY : NUWAN DE SILVA
//DATE/TIME  :26-07-06
//NOTES:


import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_assign_sub_team extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Assign Employees To Teams</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			
			
			out.println("var array_team=new Array();");
			out.println("var array_user=new Array();");
	    out.println("m_writedata='<TR>' +");
	    out.println("'<TD><B>Sub Team ID</B></TD><TD><B>Sub Team Desc</B></TD><TD><B>Sub Team Head</B></TD><TD><B>Division Code</B></TD>' +"); //modified nuwan de silva 20-07-07
	    out.println("'</TR>';");
      out.println("array_team[lineno]=m_writedata;");		 
	    out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			
      out.println("function display_data(data_vec){");
			//out.println("lineno=1;");
		  out.println("j=1;");
			out.println("i=0;");
			out.println("while(i<data_vec.length){");
			
			/*out.println("m_user_id='<TD WIDTH=\"20%\">'+data_vec[i]+'</TD>';");		
		  out.println("m_name='<TD WIDTH=\"30%\">'+data_vec[i+1]+'</TD>';");
		  out.println("m_emp_id='<TD WIDTH=\"30%\">'+data_vec[i+2]+'</TD>';");
			out.println("m_division_code='<TD WIDTH=\"20%\">'+data_vec[i+3]+'</TD>';");
			out.println("m_but_del='<td><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td>';");
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_USER_ID'+j+'	VALUE='+data_vec[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_NAME'+j+'	VALUE='+data_vec[i+1]+'>'+");	
			out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_SUB_TEAM_ID'+j+'	VALUE='+data_vec[i+2]+'>'+");
	    out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DIVISION_CODE'+j+'	VALUE='+data_vec[i+3]+'>';");
			*/
			
			out.println("m_sub_team='<TD WIDTH=\"20%\">'+data_vec[i]+'</TD>';");	//-'	
		  out.println("m_sub_team_desc='<TD WIDTH=\"30%\">'+data_vec[i+1]+'</TD>';");
			out.println("m_sub_team_head='<TD WIDTH=\"30%\">'+data_vec[i+2]+'</TD>';");
			out.println("m_division='<TD WIDTH=\"20%\">'+data_vec[i+3]+'</TD>';");
			out.println("m_but_del='<td><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+j+' value=\"Delete\" onClick=\"del_row('+j+')\"></td>';");
			
      out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_ID'+j+'	VALUE='+data_vec[i]+'>'+"); 
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_DESC'+j+'	VALUE='+data_vec[i+1]+'>'+");	
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_HEAD'+j+'	VALUE='+data_vec[i+2]+'>'+");
	    out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DIVISION_CODE'+j+'	VALUE='+data_vec[i+3]+'>';");
 			
			out.println("m_writedata='<TR>'+m_sub_team+m_sub_team_desc+m_sub_team_head+m_division+m_but_del+'</TR>'+m_hid_input;");
		  //out.println("m_writedata='<TR>'+m_emp_id+m_user_id+m_name+m_division_code+m_but_del+'</TR>'+m_hid_input;");
     	out.println("array_team[j]=m_writedata;");
      //out.println("alert(m_writedata);");
			
		  out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    out.println("array_team.join(\" \")+'</table>';");
      out.println("j=j+1;");
			out.println("i=i+4;");
			out.println("}"); //End of for loop;
			
			out.println("lineno=j;");
			out.println("arr_size=j;");
			out.println("}");
			
			
			
			//This function Used to Fill User Data
			
			out.println("  function display_user(data_vec){  "); 
		  out.println("document.Form1.TXT_USER_ID.value=data_vec[0];"); 
			out.println("array_user[0]=data_vec[0];");
			out.println("array_user[1]=data_vec[1];");
			out.println("array_user[2]=data_vec[4];");
			out.println("array_user[3]=data_vec[5];");
			
			out.println("  }  "); 
			
				
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0  && document.Form1.TXT_TEAM_ID.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("       help_update();  "); 
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_SUB_TEAM_ID.value!=\"\"){");
			out.println("    help_button_3(); "); 
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_TEAM_ID.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			out.println("    document.Form1.TXT_TEAM_ID.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_TEAM_DESC.value=data_vec[1];"); 
			out.println("    document.Form1.TXT_TEAM_HEAD.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=data_vec[3];"); 
			out.println("    document.Form1.TXT_SUB_DIVISION_CODE.value=data_vec[4];"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			out.println("    assignState('M2');"); 
			out.println("    makeRequest(document.Form1.TXT_TEAM_ID)");
			out.println("			}");
      out.println("			}");
			
			out.println("			else");
			out.println("    if(document.Form1.hid_chk_status.value==\"M2\"){"); 
			out.println("      display_data(data_vec);");
			out.println("			}");
			out.println("			if(data_vec.length>0 && document.Form1.hid_chk_status.value=='M3' && document.Form1.TXT_USER_ID.value!=\"\"){");
			out.println("     display_user(data_vec);  "); 
			out.println("			}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("  if(document.Form1.hid_chk_status.value==\"M1\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_assign_members&data_val=\"+obj.value;");
			out.println("}");
			out.println("  if(document.Form1.hid_chk_status.value==\"M2\"){"); 
			out.println("  m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations2?chksql=m_prime_chk_LAKDL_AF_MAS_display_assign_team_2&data_val=\"+obj.value;");
			//out.println("  window.open(m_url);");
			out.println("  }");
			out.println("else");
			out.println("  if(document.Form1.hid_chk_status.value==\"M3\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_TEAM_ID.value==\"\"){  "); 
			out.println("DIV_TXT_TEAM_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_SUB_TEAM_ID.value==\"\" && m_table.innerHTML==\"\"){  "); 
			out.println("DIV_TXT_SUB_TEAM_ID.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_SUB_TEAM_ID.value==\"\" && m_table.innerHTML==\"\" && document.Form1.SCREEN_NAME.value==\"NEW\"){  "); 
			out.println("alert('Please Add The Records');");
			out.println("return false;"); 
			out.println("}"); 
     
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function add_row(obj){"); 

			out.println("var b_status=1;"); // Variable To Hold The Status

			out.println("if(obj.value==\"\"){");
			out.println("alert('Please enter Sub Team ID');");
			out.println("b_status=0;");
			out.println("}");

			out.println("else");

			out.println("if(obj.value!=\"\"){");			
			out.println("for(var i=1;i<lineno;i++){");
			out.println("m_user=\"hid_TXT_SUB_TEAM_ID\"+i");

			//out.println("alert(document.Form1.elements[\"hid_TXT_SUB_TEAM_ID\"+i].value);");
			//out.println("alert(document.Form1.elements[m_user].value);");
			out.println("if(obj.value==document.Form1.elements[m_user].value && document.Form1.elements[m_user].value!=\"N\"){");
			out.println("alert('Sub Team ID Already Exist');");
			out.println("b_status=0;");
			out.println("break;");
			out.println("}");
			out.println("}");
			out.println("}");		
			out.println("if(b_status==1){");
			out.println("m_sub_team='<TD WIDTH=\"20%\">'+array_user[0]+'</TD>';");		
			out.println("m_sub_team_desc='<TD WIDTH=\"30%\">'+array_user[1]+'</TD>';");
			out.println("m_sub_team_head='<TD WIDTH=\"30%\">'+array_user[2]+'</TD>';");
			out.println("m_division='<TD WIDTH=\"20%\">'+array_user[3]+'</TD>';");
			out.println("m_but_del='<td><input class=\"but_input\" type=\"button\" name=BUT_TXT_USER_ID_DEL'+lineno+' value=\"Delete\" onClick=\"del_row('+lineno+')\"></td>';");

			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_ID'+lineno+'	VALUE=\"'+array_user[0]+'\" >'+"); 
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_DESC'+lineno+'	        VALUE=\"'+array_user[1]+'\" >'+");	
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_HEAD'+lineno+'	        VALUE=\"'+array_user[2]+'\" >'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_DIVISION_CODE'+lineno+'	        VALUE=\"'+array_user[3]+'\" >';");

			out.println("m_writedata='<TR>'+m_sub_team+m_sub_team_desc+m_sub_team_head+m_division+m_but_del+'</TR>'+m_hid_input;");
			out.println("array_team[lineno]=m_writedata;");
			out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+");
			out.println("array_team.join(\" \")+'</table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}"); 
			out.println("document.Form1.TXT_SUB_TEAM_ID.value=\"\""); 
			out.println("document.Form1.TXT_SUB_TEAM_ID.focus()"); 
			out.println("}"); //End Of Add Row.		
			
			
			
			
			
			out.println("function del_row(lineno){"); 
			out.println("if(confirm('Are you sure you want to delete the team member ?')) {");  //added by nuwan de silva 20-07-07
			out.println("array_team[lineno]='<INPUT TYPE=\"Hidden\" NAME=hid_TXT_SUB_TEAM_ID'+lineno+' VALUE=\"N\">';");
	    out.println("arr_size=arr_size-1;");
	    out.println("m_table.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    out.println("array_team.join(\" \")+'</table>';");
			out.println("}"); 
			out.println("}"); 


			out.println("function before_submit(){ "); 
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_assign_sub_teams_to_teams';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_assign_sub_team';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_assign_sub_team';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_assign_members\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Assign Sub Teams To Teams - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Assign Sub Teams To Teams - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.BUT_TXT_USER_ID.disabled=false;"); 
			out.println("document.Form1.TXT_TEAM_DESC.disabled=true;"); 
			out.println("document.Form1.TXT_TEAM_HEAD.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_DIVISION_CODE.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
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
			out.println("m_table.innerHTML=\"\""); //Added By Nuwa De Silva
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			//out.println("document.Form1.BUT_TXT_USER_ID.disabled=false;"); 
			out.println("document.Form1.TXT_TEAM_DESC.disabled=true;"); 
			out.println("document.Form1.TXT_TEAM_HEAD.disabled=true;"); 
			out.println("document.Form1.TXT_DIVISION_CODE.disabled=true;"); 
			out.println("document.Form1.TXT_SUB_DIVISION_CODE.disabled=true;"); 
			
			
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
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("		help_value_assign_3();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
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
			
			
			out.println("function clear_data() {");
			
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_TEAM_ID.value='';");
			out.println("document.Form1.TXT_TEAM_ID.focus();");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"4\"){");
			out.println("document.Form1.TXT_DESCRIPTION.value='';");
			out.println("document.Form1.TXT_DESCRIPTION.focus();");
			out.println("}");
			
			out.println("if(document.Form1.hid_help_type.value==\"3\"){");
			out.println("document.Form1.TXT_SUB_TEAM_ID.value='';");
			out.println("document.Form1.TXT_SUB_TEAM_ID.focus();");
			out.println("}");

			
			
			out.println("}");
			

			
			
			out.println("function help_button_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			out.println("    m_sql = \"m_help_txt_sub_team_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_SUB_TEAM_ID.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','4');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_3() {"); 
			out.println("document.Form1.TXT_SUB_TEAM_ID.value=oBj.valout[2];"); 
			out.println("array_user[0]=oBj.valout[2];"); // sub_team_id
			out.println("array_user[1]=oBj.valout[3]+ ' '+oBj.valout[5] ;");//sub_team_desc
			out.println("array_user[2]=oBj.valout[4];"); // sub_team_head
			out.println("array_user[3]=oBj.valout[5];"); // division
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			//out.println("    m_sql = \"m_help_TXT_TEAM_ID_sql_new\";"); 
			out.println("    m_sql = \"m_help_txt_sub_team_id_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("}"); 
			out.println("    else{");
			out.println("    m_sql = \"m_help_txt_team_sub_team_id_team_sql\";"); 			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"N@\";}"); 
			out.println("}"); 
			out.println("    HelpBox('1','10','3');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_TEAM_ID.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TEAM_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_TEAM_HEAD.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_DIVISION_CODE.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_SUB_DIVISION_CODE.value=oBj.valout[6];"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\" ){ ");
			out.println("    assignState('M2');"); 
			out.println("    makeRequest(document.Form1.TXT_TEAM_ID)");
			out.println("}");

			out.println("}"); 
			
			out.println("function help_value_assign_4() {"); 
			//out.println("    document.Form1.TXT_AREA_DESC.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value=\"\";"); 
			out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}"); 
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\" ){ ");
			out.println("    m_sql = \"m_help_TXT_TEAM_ID_sql_new_desc\";"); 
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("}"); 
			out.println("    else{");
			out.println("    m_sql = \"m_help_TXT_TEAM_ID_team_sql_DESC\";"); 			
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
						
			out.println("    HelpBox('1','10','3');"); 
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
			
			/*out.println("		if(document.Form1.hid_help_type.value==\"99\"){"); 
			out.println("		help_update_value_assign_99();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("		help_value_assign_1();"); 
	  	out.println("		}"); 
			*/
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
			out.println("    m_sql = \"m_view_TXT_TEAM_ID_sql_new\";");
			out.println("    m_criteria = document.Form1.TXT_TEAM_ID.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_roll_value('New')\">"); //load_lock(),
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Assign Sub Teams To Teams</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
		//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
		 out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
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
			out.println("<td width='30%' ><DIV id='DIV_TXT_TEAM_ID'  class=div_input>Team Id *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEAM_ID' maxlength='10' size='10' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_TEAM_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Team Description </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEAM_DESC' maxlength='50' size='50'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' >Team Head </td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TEAM_HEAD' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Division Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_DIVISION_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td width='30%' >Sub Division Code</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_SUB_DIVISION_CODE' maxlength='10' size='10'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			/*out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER_ID'  class=div_input>Employee Id *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_USER_ID' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_USER_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_USER_ID' value=\"Help\" onClick=\"help_button_3()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ADD' value=\"Add\" onClick=\"add_row(document.Form1.TXT_USER_ID)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			*/
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SUB_TEAM_ID'  class=div_input>Sub Team Id *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SUB_TEAM_ID' maxlength='10' size='10' onblur=\"assignState('M3'),makeRequest(document.Form1.TXT_SUB_TEAM_ID)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_SUB_TEAM_ID' value=\"Help\" onClick=\"help_button_3()\">"); 
			out.println("<input class='but_input' type='button' name='BUT_ADD' value=\"Add\" onClick=\"add_row(document.Form1.TXT_SUB_TEAM_ID)\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%' border=\"0\">"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
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
