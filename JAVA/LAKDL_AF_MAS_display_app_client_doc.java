
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - APPLICABLE CLIENT DOCUMENTS
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_app_client_doc extends javax.servlet.http.HttpServlet { 

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
			
			String m_ent_type="";
			String m_c_code="C004";
			String m_stage="";
			String m_app_num="A03";
			//m_ent_type=req.getParameter("ent_type").trim();
			//m_c_code=req.getParameter("c_code").trim();
			//m_stage=req.getParameter("stage");
			//m_app_num=req.getParameter("app_num");
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Applicable Client Documents</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
			
			out.println("var array_team=new Array();");
			out.println("var array_user=new Array();");
			out.println("var array_code=new Array();");
			out.println("var array_remarks=new Array();");
			out.println("var array_chkval=new Array();");
	    out.println("m_writedata='<TR>' +");
	    out.println("'<TD><B>Code</B></TD><TD><B>Description</B></TD><TD><B>Remarks</B></TD><TD><B>Status</B></TD>' +");
	    out.println("'</TR>';");
      out.println("array_team[lineno]=m_writedata;");		 
	    out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			
			
			
			

		out.println("function get_vector(data_vec) {");
			//out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\"){");
	//		out.println("				alert('Record already exsist');");
			//out.println("				new_window();");
			//out.println("			}");
			out.println("     if(data_vec.length > 0 && document.Form1.hid_data_type1.value ==\"M1\"){");
			//out.println("			alert('M1');");
      out.println("     write_data1(data_vec);");
	    out.println("}");
	    out.println("else if(data_vec.length > 0 && document.Form1.hid_data_type2.value ==\"M2\"){");
			//out.println("			alert('M2');");
      out.println("     write_data2(data_vec);");
    	out.println("}");
			out.println("}");
			
			
			
			
			


			out.println("function before_submit(){ "); 
			//out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			//out.println("document.Form1.elements[i].disabled=false;");
			//out.println("}");
			//out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are You Sure?\")){ ");
			out.println("   get_value(arr_size); ");
			out.println("   document.Form1.hid_no_rec.value=arr_size;");
			out.println("   document.Form1.hid_app_num.value='"+m_app_num+"';");
			out.println("   document.Form1.hid_c_code.value='"+m_c_code+"';");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_app_client_doc';");  
			out.println("		document.Form1.submit();	"); 
			//out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			
			
			/*out.println("function load_screen(){ "); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_app_client_doc&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("} ");

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); */

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_app_client_doc';"); 
			out.println("		}"); 
			out.println("}"); 

		/*	out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_app_client_doc';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); */

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_app_client_doc\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	  popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("   \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

		  out.println("function load_roll_value(m_val){"); 
			//out.println("help_box.innerHTML=\" System Administration - Applicable Client Documents - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			//out.println("help_box.innerHTML=\" System Administration - Applicable Client Documents - \"+document.Form1.hid_status.value;"); 
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
			out.println("document.Form1.TXT_CLIENT_CODE.disabled=true;"); 

			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
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

			
			/*out.println("function MyDialog(){"); 
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

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_NO_sql\";"); 
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    } ");
			out.println("    else{");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"N@\";}"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

      out.println(" function assign_help_status(obj){");   
      out.println("    document.Form1.hid_help_status.value =obj; ");
      out.println("    }");

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[3];"); 

			out.println("}"); */
			
			
			
			
	//====================================================================================================
	//====================================================================================================
			out.println(" function write_data1(data_vec){");
			
			out.println("j=1;");
			out.println("i=0;");
			out.println("while(i<data_vec.length){");
			
			out.println("m_code='<TD WIDTH=\"25%\">'+data_vec[i]+'</TD>';");		
		  out.println("m_description='<TD WIDTH=\"25%\">'+data_vec[i+1]+'</TD>';");
		  out.println("m_remarks='<TD WIDTH=\"25%\"><INPUT TYPE=\"TEXT\" NAME=TXT_REMARKS_'+j+' VALUE='+data_vec[i+2]+' maxlength=\"100\" size=\"15\"></td>';");
			out.println("m_chk_val='<TD WIDTH=\"25%\"><INPUT TYPE=\"checkbox\" NAME=CHK_VAL_'+j+' VALUE='+data_vec[i+3]+' onclick=\"change_val('+j+')\"></td>';");
									
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=TXT_CODE_'+j+'	VALUE='+data_vec[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_row_state_'+j+'	VALUE=\"EDIT\">';");
			/*out.println("'<INPUT TYPE=\"Hidden\" NAME=TXT_REMARKS_'+j+' VALUE='+data_vec[i+1]+'></td>';");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=CHK_VAL_'+j+' VALUE='+data_vec[i+2]+'></td>';");*/
				    
	    out.println("m_writedata='<TR>'+m_code+m_description+m_remarks+m_chk_val+'</TR>'+m_hid_input;");
			// out.println("m_writedata='<TR>'+m_user_id+m_name+m_emp_id+m_division_code+m_but_del+'</TR>'+m_hid_input;");
     	out.println("array_team[j]=m_writedata;");
      
		
		  out.println("select.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    out.println("array_team.join(\" \")+'</table>';");
			out.println("check_exec(j);");
      out.println("j=j+1;");
			out.println("i=i+4;");
			out.println("}"); //End of for loop;
			
			out.println("lineno=j;");
			out.println("arr_size=j;");
			
			out.println("}");
			
			
			out.println(" function write_data2(data_vec){");
			
			out.println("j=1;");
			out.println("i=0;");
			out.println("while(i<data_vec.length){");
			
			out.println("m_code='<TD WIDTH=\"25%\">'+data_vec[i]+'</TD>';");		
		  out.println("m_description='<TD WIDTH=\"25%\">'+data_vec[i+1]+'</TD>';");
		  out.println("m_remarks='<TD WIDTH=\"25%\"><INPUT TYPE=\"TEXT\" NAME=TXT_REMARKS_'+j+' VALUE='+data_vec[i+2]+' maxlength=\"100\" size=\"15\" ></td>';"); 
			out.println("m_chk_val='<TD WIDTH=\"25%\"><INPUT TYPE=\"checkbox\" NAME=CHK_VAL_'+j+' VALUE='+data_vec[i+3]+' onclick=\"change_val('+j+')\"></td>';");
			
			out.println("m_hid_input='<INPUT TYPE=\"Hidden\" NAME=TXT_CODE_'+j+'	VALUE='+data_vec[i]+'>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_row_state_'+j+'	VALUE=\"NEW\">';");
							    
	    out.println("m_writedata='<TR>'+m_code+m_description+m_remarks+m_chk_val+'</TR>'+m_hid_input;");
			// out.println("m_writedata='<TR>'+m_user_id+m_name+m_emp_id+m_division_code+m_but_del+'</TR>'+m_hid_input;");
		
     	out.println("array_team[j]=m_writedata;");
      
		
		  out.println("select.innerHTML='<table align=\"center\" width=\"100%\" class=\"table\">'+");
	    out.println("array_team.join(\" \")+'</table>';");
	//		out.println("check_exec(j);");
      out.println("j=j+1;");
			out.println("i=i+4;");
			
			out.println("}"); //End of for loop;
			
			out.println("lineno=j;");
			out.println("arr_size=j;");
			
			out.println("check_exec(arr_size);");
			
			out.println("}");
			
			
			
			out.println("function check_exec(size){ ");
			out.println("for(var i=1; i<=size;i++){");
   		out.println("M_CHKVAL=\"CHK_VAL_\"+i;");
			out.println("if(document.Form1.elements[M_CHKVAL].value==\"on\"){");
      out.println("document.Form1.elements[M_CHKVAL].checked=true;");
			out.println("document.Form1.elements[M_CHKVAL].value=\"on\";");
      out.println("}else");
			out.println("if(document.Form1.elements[M_CHKVAL].value==\"off\"){");
		  out.println("document.Form1.elements[M_CHKVAL].checked=false;");
			out.println("document.Form1.elements[M_CHKVAL].value=\"off\";");
      out.println("}");   
			out.println("}");
      out.println("}");
			
			
			
			
			out.println("function change_val(val) {");
			out.println("i=0;");
			//out.println("alert('val'+val);");
			out.println("i=val;");
			out.println("M_CHKVAL=\"CHK_VAL_\"+i;");
			//out.println("			alert(document.Form1.elements[M_CHKVAL].value);");
			
			out.println("if(document.Form1.elements[M_CHKVAL].value==\"on\"){");
			out.println("document.Form1.elements[M_CHKVAL].value=\"off\";" );
			//out.println("			alert(document.Form1.elements[M_CHKVAL].value);");
			out.println("}");
			out.println("else ");
			out.println("if(document.Form1.elements[M_CHKVAL].value==\"off\"){");
			out.println("document.Form1.elements[M_CHKVAL].value=\"on\";" );
			//out.println("			alert(document.Form1.elements[M_CHKVAL].value);");
			out.println("}");
			out.println("}");
			
			out.println("function get_value(size) {");
		//	out.println("i=1;");
		//o	out.println("j=0;");
			out.println("for(var i=1;i<size;i++){");
			//out.println("M_REMARKS=\"TXT_REMARKS_\"+i;");
			out.println("M_CHKVAL=\"CHK_VAL_\"+i;");
			//out.println("while(i<size){");
			//out.println("alert(i);");
			//out.println("alert(document.Form1.elements[M_CHKVAL].value);");
			//out.println("i=i+1;");
			/*out.println("alert(i);");
			out.println("alert(size);");
			out.println("array_code[j]=obj[j];");
			out.println("array_remarks[j]=document.Form1.elements[M_REMARKS].value;");
			out.println("array_chkval[j]=document.Form1.elements[M_CHKVAL].value;");
			out.println("alert(array_code[j]);");
			out.println("alert(array_remarks[j]);");
			out.println("alert(array_chkval[j]);");*/
			
			//out.println("j=j+3;");
			out.println("}");
			out.println("}");
			
			
	
			
			
			out.println(" function get_url_data(){");

			//out.println("alert('"+m_c_code+"');");
			//out.println("alert('"+m_ent_type+"');");
			//out.println("alert('"+m_app_num+"');");
			out.println("Ent_Type='"+m_ent_type+"'");
			out.println("Stage='"+m_stage+"'");
			
		  out.println("     if((Ent_Type==\"\")&&(Stage==\"\")){");
		 	out.println("     m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_app_client_doc&app_num="+m_app_num+"\";");
			out.println("     document.Form1.hid_data_type1.value ='M1'; ");
		  out.println("     load_interface(m_url,'XML');");
			out.println("}");
			out.println("     else{ ");
			out.println("     m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_app_client_doc1&entity_type="+m_ent_type+"&stage="+m_stage+"\";");
			out.println("     document.Form1.hid_data_type2.value ='M2'; ");
			out.println("     load_interface(m_url,'XML');");
		  out.println("}");
			//out.println("		 window.open(m_url);");
			out.println("}");
			
		
			
			
			

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_url_data()\" >"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_app_num' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_c_code' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_data_type1' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_data_type2' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_data_state' VALUE=\"EDIT\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_stat' VALUE=\"off\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Applicable Client Documents</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
			out.println("<tr><td width='30%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table'>"); 
			
			//out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen()' value=\"New\"></td>");
			//out.println("</tr>");

			/*out.println("<tr >"); 

			out.println("<td width='10%' >Document Code</td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_DOCUMENT_CODE' maxlength='15' size='15' onblur=\"\">");
			out.println("<td width='10%' >Description</td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_DESCRIPTION' maxlength='15' size='15' onblur=\"\">");
			//out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			 
      			 
			out.println("</table>");*/
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
			//<TD STYLE="{color: white ;font: 10pt Helvetica;text-align:right;}" WIDTH="100%" >
		 out.println("<td width=\"100%\"><DIV ID=select></DIV>");				
		 out.println("</tr>");
     out.println("</table>");

			
			
			
			
			//out.println("<TD WIDTH=\"25%\"><INPUT TYPE=\"checkbox\" NAME=CHK_VAL_'+j+' OPTION=\"1\"></td>");
			
			out.println("<br>"); 
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
