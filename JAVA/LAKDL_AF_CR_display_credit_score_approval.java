//--
//SCREEN NAME:
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_display_credit_score_approval extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1,stmt2;
  public ResultSet rs,rs1,rs2;
	String reqstr;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
		 	

			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
	
			String m_username=m_sn_methods.username;
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_Hid_scr_name="";
			
			out = res.getOutputStream();
			

			String m_applicaton_no        = req.getParameter("applicaton_no");
			String m_pre_stage=req.getParameter("pre");
			String m_app_stage=req.getParameter("appro");
			String m_pre_stage1=req.getParameter("qry");
			m_Hid_scr_name=req.getParameter("Hid_scr_name");
			
			String m_schema_name = m_sn_methods.schema_name;

			String m_sort_column   = "APPLICATION_NO";	
			String m_order_by_type = "ASC";
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			String m_scr="2";
			if(req.getParameter("m_scr")!=null){
			m_scr=req.getParameter("m_scr");
			}
			//------------------------------------------------------------------------------------------------------

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var m_app1='"+m_applicaton_no+"'");
			out.println("var b_flag=0;");
			out.println("var field='T1'");
			
			//Declare Global Variavles
			out.println("var lineno=0;");
			out.println("var arr_size=0;");
				
			//Declare Global Arrays
			out.println("var array_follow_up_no=new Array();");
			out.println("var array_condition=new Array();");
			out.println("var array_status=new Array();");
			
			   
			
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
			
			
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("    try {");
			out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("    } catch (e) {");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("        } catch (e) {}");
			out.println("    }");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("    return false;");
			out.println("}");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_APPROVAL&pre_stage1="+m_pre_stage+"&app_no="+m_applicaton_no+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
  		out.println("}");

			out.println("function makeRequest() {");
			out.println(" field='T1'");
			out.println("var http_request = false;");
			out.println("if (window.XMLHttpRequest) {");
			out.println("http_request = new XMLHttpRequest();");
			out.println("if (http_request.overrideMimeType) {");
			out.println("     http_request.overrideMimeType('text/xml');");
			out.println("}");
			out.println("} else if (window.ActiveXObject) { ");
			out.println("    try {");
			out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
			out.println("    } catch (e) {");
			out.println("        try {");
			out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
			out.println("        } catch (e) {}");
			out.println("    }");
			out.println("}");
			out.println("if (!http_request) {");
			out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
			out.println("    return false;");
			out.println("}");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_APPROVAL&pre_stage1="+m_pre_stage+"&app_no="+m_applicaton_no+"\";");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			
			out.println("}");
			
			
			
			// ------------------ADDED BY CHANDANA ON 10/04/2007 ---------------- //			
			out.println("function get_remarks1(){ ");		
			out.println("field='T4'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");			
			out.println("}");
			
			out.println("function assing_remarks1(data_vec) { ");
			out.println("m_table_remaks1.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"90%\" align=\"left\">'+data_vec[1]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("get_remarks2();");
			out.println("}");	
				
			out.println("function assing_blank_remarks1() { ");
			out.println("m_table_remaks1.innerHTML='';");
			out.println("get_remarks2();");
			out.println("}");		
						
			out.println("function get_remarks2(){ ");		
			out.println("field='T5'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks2&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");			
			out.println("}");			
			
			out.println("function assing_remarks2(data_vec) { ");
			out.println("m_table_remaks2.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"90%\" align=\"left\">'+data_vec[1]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("get_remarks3();");
			out.println("}");	
				
			out.println("function assing_blank_remarks2() { ");
			out.println("m_table_remaks2.innerHTML='';");
			out.println("get_remarks3();");
			out.println("}");	
						
			out.println("function get_remarks3(){ ");		
			out.println("field='T6'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks3&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");			
			out.println("}");		
						
			out.println("function assing_remarks3(data_vec) { ");
										
			out.println("m_table_remaks3.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"90%\" align=\"left\">'+data_vec[1]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" ></TD>' +");
			out.println("'</tr></table>';");
			if(m_pre_stage.equals("VERIFY-M")){			
			out.println("get_remarks4()");	
			}			
			out.println("}");	
				
			out.println("function assing_blank_remarks3() { ");
			out.println("m_table_remaks3.innerHTML='';");
			if(m_pre_stage.equals("VERIFY-M")){			
			out.println("get_remarks4()");	
			}	
			out.println("}");	
			
			
			
			out.println("function get_remarks4(){ ");		
			out.println("field='T7'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks4&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");			
			out.println("}");		
						
			out.println("function assing_remarks4(data_vec) { ");
			out.println("m_table_remaks4.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"90%\" align=\"left\">'+data_vec[1]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("}");	
				
			out.println("function assing_blank_remarks4() { ");
			out.println("m_table_remaks4.innerHTML='';");
			out.println("}");		
					
			
		// ------------------------ END ------------------------- //

					
			
			
			
			
			out.println("function alertContents(http_request,count) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					score_details.innerHTML=m_data;");
			out.println("			get_conditions();");
			
			out.println("if(document.Form1.NUM_CHKS.value==0 && '"+m_scr+"'=='1'){");
			out.println("alert('No records')");
			out.println("score_details.innerHTML=\"\"");
			out.println("	window.close()");
			out.println("				}");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			out.println(" }");
			out.println("}");
			
			
			out.println("function get_conditions(){ "); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_conditions&data_val="+m_applicaton_no+"&ac_status=COMPLETED\";");
		  out.println("load_interface(m_url,'XML');");
      out.println("}");
			
			
			out.println("function load_Follow(row_No){ "); 
			out.println("m_fol_no=\"TXT_FOLLOW_UP_NO\"+row_No");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_Followup?chksql=main_page&Followu_no='+document.Form1.elements[m_fol_no].value;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=950,height=590,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			out.println("function header(){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%%\" class=\"table\" border=\"0\"><TR>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Follow up No</B></TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><B>Condition</B></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\"><B>Status</B></TD>'+");
			out.println("'<TD WIDTH=\"5%\" align=\"center\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</TR></table>';");
     	out.println("}");
				
			
			
			
			
			out.println("function get_vector(data_vec){ "); 
			out.println("lineno=0 ");
			out.println("arr_size=0 ");
			out.println("var i=0");
			out.println("if(data_vec.length>0 && field=='T1'){");
			out.println("header();");
			out.println("while(i<data_vec.length){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" value='+data_vec[i]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand; }\" onclick=\"load_Follow('+lineno+')\" >Follow up</a></TD>'+");//&nbsp;&nbsp;Follow up
			out.println("'<TD WIDTH=\"30%\"  align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+data_vec[i+1]+'\" onblur=\"\" disabled>'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">'+data_vec[i+2]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE='+data_vec[i+2]+'>'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("i=i+3;");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0 && field=='T1'){");
			out.println("add_row()");
			out.println("}");
			
			
			out.println("	else	if(data_vec.length>0  && field=='T4' ){");
			out.println("assing_remarks1(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length == 0  && field=='T4' ){");
			out.println("assing_blank_remarks1(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length>0  && field=='T5' ){");
			out.println("assing_remarks2(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length == 0 && field=='T5' ){");
		//	out.println("assing_blank_remarks2(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length>0  && field=='T6' ){"); 
			out.println("assing_remarks3(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length == 0  && field=='T6' ){");
		//	out.println("assing_blank_remarks3(data_vec);");
			out.println("			}");
			if(m_pre_stage.equals("VERIFY-M")){
			out.println("if(data_vec.length>0  && field=='T7' ){");
			out.println("assing_remarks4(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length == 0  && field=='T7' ){");
			out.println("assing_blank_remarks4(data_vec);");
			out.println("			}");
						}
						
			out.println("}");
			
			
			
			out.println("function check_app(){ "); 
			out.println("if(!count_chk()){"); 
			out.println("alert(\"Please approve or reject the application \");");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
		  out.println("}"); 
			
			out.println("function count_chk(){ ");
			out.println("count=0;");
			out.println(" m_row = document.Form1.NUM_CHKS.value ; ");
			out.println("for(i=0;i<m_row;i++){");
			out.println("  chk_rej=\"CHK_REJ_\"+i;");
			out.println("  chk=\"CHK_\"+i;");
			out.println("if(document.Form1.elements[chk].checked==true || document.Form1.elements[chk_rej].checked==true){");
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


			out.println("function before_submit(){ "); 
			out.println("check_app();");
			out.println("if(b_flag==0){");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("   document.Form1.Hid_scr_name.value=\""+m_Hid_scr_name+"\";");//Added By Nuwan De Silva
			out.println("   document.Form1.Hid_app_no.value='"+m_applicaton_no+"';");//Added By Nuwan De Silva
			
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_save_score_approval?&pre_stage1="+m_pre_stage1+"app_no="+m_applicaton_no+"&appro="+m_app_stage+"';");
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("get_remarks2();");
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_display_credit_score_approval?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&m_scr=1&applicaton_no="+m_applicaton_no+"'");
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_display_credit_score_approval?pre="+m_pre_stage+"&appro="+m_app_stage+"&qry="+m_pre_stage1+"&m_scr=1&applicaton_no="+m_applicaton_no+"'");
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_score_model_creation\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Approval  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Approval  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

						
			out.println("function display_data(obj1,obj2){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_VIEW&pre_stage1="+m_pre_stage1+"&model=\"+obj2+\"&application=\"+obj1;");
			out.println("window.open(url,'win1','left=200,top=100,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			out.println("}"); 
			
			
			out.println("function close_1(){");
			out.println("if('"+m_scr+"'==\"1\"){");
			out.println("		if(confirm(\"Are you sure you want to close the screen?\")){ "); 
			out.println("window.close()"); 
			out.println("}"); 
			out.println("}"); 
			out.println("else if('"+m_scr+"'==\"2\") {");
			out.println("close_window()"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";");
			out.println("document.Form1.hid_save.value=\"Save\";"); 
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



			out.println("function change(row) {"); 
			out.println("  chk_rej=\"CHK_REJ_\"+row;");
			out.println("  chk_quot=\"CHK_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("document.Form1.elements[chk_rej].checked=false");
			out.println("document.Form1.elements[chk_rej].value='N'");
			out.println("ck=-1");
			out.println("}");
			out.println("else if(document.Form1.elements[chk_quot].checked==false && document.Form1.elements[chk_rej].checked==false){");
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_quot].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 


			out.println("function change_reject(row) {"); 
			out.println("  chk_rej=\"CHK_REJ_\"+row;");
			out.println("  chk_quot=\"CHK_\"+row;");
			out.println("if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("document.Form1.elements[chk_quot].checked=false");
			out.println("document.Form1.elements[chk_quot].value='N'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else if(document.Form1.elements[chk_quot].checked==true && document.Form1.elements[chk_rej].checked==true){");
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("else");	
			out.println("{");	
			out.println("document.Form1.elements[chk_rej].value='Y'");
			out.println("ck=-1");
			out.println("}");	
			out.println("}"); 
			
			
			
	/*----------------------------------------------------------------
		Purpose  : Add Conditions
	
	----------------------------------------------------------------*/			

			out.println("function add_row(){"); 
			out.println("b_flag=0;");
			out.println("if(lineno!=0){");
			out.println("count=lineno-1;");
			out.println("m_condition=\"TXT_CONDITION\"+count");
			out.println("if(document.Form1.elements[m_condition].value==\"\") {");
			out.println("alert('Condition can not be null.');");
			out.println("b_flag=1;");
			out.println("}");
			out.println("}");
			out.println("if(b_flag==0){");
			out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+lineno+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+lineno+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+lineno+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\">'+");
			out.println("'<TD WIDTH=\"15%\"  align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+lineno+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\" ><input class=\"but_input\" type=\"button\" name=BUT_DEL'+lineno+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+lineno+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("lineno=lineno+1;");
			out.println("arr_size=arr_size+1;");
     	out.println("}");
      out.println("}");
			
			out.println("function del_row(rowNo){"); 
			out.println("if(rowNo!=0){");
			out.println("var j=0;");
			out.println("for(var i=0;i<arr_size;i++){");
			out.println("m_follow_up=\"TXT_FOLLOW_UP_NO\"+i");
			out.println("m_condition=\"TXT_CONDITION\"+i");
			out.println("m_status=\"hid_TXT_STATUS\"+i");					
			out.println("if(i==rowNo)");
			out.println("continue;");
			out.println("array_follow_up_no[j]=document.Form1.elements[m_follow_up].value;");
			out.println("array_condition[j]=document.Form1.elements[m_condition].value;");
		  out.println("array_status[j]=document.Form1.elements[m_status].value;");    
			out.println("j=j+1;");
			out.println("}");
			out.println("lineno=lineno-1;");
			out.println("arr_size=arr_size-1;");
		  out.println("write_data(arr_size);");
			out.println("}");
			
			
			
			out.println("function write_data(size){");
			out.println("sum=0;");
			out.println("m_table.innerHTML=\"\";");
			out.println("header();");
      out.println(" for(var j=0;j<size;j++){");
	    out.println("if(array_follow_up_no[j]==\"\" && array_condition[j]==\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" onblur=\"\"></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\"></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
			out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]==\"\" && array_condition[j]!=\"\" ){");
		  out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\">Follow up</TD>'+");
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" value='+array_condition[j]+' maxlength=\"200\" size=\"200\" onblur=\"\" ></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">-</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE=\"-\">'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" ></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
			out.println("else if(array_follow_up_no[j]!=\"\" && array_condition[j]!=\"\" ){");
      out.println("m_table.innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\"><tr ID=T_ID'+j+'>'+");									
			out.println("'<TD WIDTH=\"20%\"  align=\"left\"><input class=\"txt_input\" type=\"text\" name=TXT_FOLLOW_UP_NO'+j+' maxlength=\"10\" size=\"10\" value='+array_follow_up_no[j]+' onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"10%\"  align=\"left\"><a href  style=\"{cursor:hand; }\" onclick=\"load_Follow('+j+')\" >Follow up</a></TD>'+"); //Follow up
			out.println("'<TD WIDTH=\"30%\" align=\"left\"><input class=\"txt_input\" type=\"textarea\" name=TXT_CONDITION'+j+' style=\"width:200px; height:20px;\" maxlength=\"200\" size=\"200\" value=\"'+array_condition[j]+'\" onblur=\"\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"15%\" align=\"left\">'+array_status[j]+'</TD>'+");
			out.println("'<INPUT TYPE=\"Hidden\" NAME=hid_TXT_STATUS'+j+'	VALUE='+array_status[j]+'>'+");
			out.println("'<TD WIDTH=\"5%\"><input class=\"but_input\" type=\"button\" name=BUT_DEL'+j+' style=\"width:30px\" value=\" X \" onClick=\"del_row('+j+')\" disabled></TD>'+");
			out.println("'<TD WIDTH=\"*%\" ></TD>' +");
			out.println("'</tr></table>';");
      out.println("continue;");
			out.println("}");
		 	out.println("}");		
			out.println("}");		
		  out.println("}");		
			
//-----------------------------------------------------------------------------------------------------------------------			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' onload=\"makeRequest(),load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='Hid_app_no' VALUE=\"\">"); 
			
			
				
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Credit Score Approval </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='10%' align='center'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_1()' value=\"Close\"></td>");  
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
			out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' width='100%' >");  
			out.println("<tr class='tr_input'>");  
			out.println("<td width='100%'><img height='1' src='spacer.gif' width='100%' /></td>");  
			out.println("</tr>");  
			out.println("</table>");  
			
			
			
			
			out.println("<br>");
					

				rs2= stmt2.executeQuery ("SELECT DISTINCT ISSUER_CODE,TO_CHAR(AMOUNT,'999,999,999,999,999.99'),TO_CHAR(ISSUED_DATE,'DD-MM-YYYY'),TO_CHAR(START_DATE,'DD-MM-YYYY'), "+
				"TO_CHAR(END_DATE,'DD-MM-YYYY'),STATUS "+
				"FROM "+m_schema_name+".AF_CO_PRO_APP_BANK_GUARANTEES "+	
				"WHERE UPPER(APPLICATION_NO) = UPPER('"+m_applicaton_no+"')  "+
				"AND STATUS='Y' ");


				boolean more2=rs2.next();	
				int j=0;

				
			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 
				
				while(more2){
				
				if(j==0){
		
			out.println("<tr>");
			out.println("<td width='20%' style='{text-align:left;}'><b><u>Issuer Details :-</td>");
			out.println("</tr>");
							
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<tr class=\"pdn_txtpos2\">");
						
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Code</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Amount</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Issuer Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>Start Date</td>");
			out.println("<td width='20%' style='{text-align:left;}'><b>End Date</td></tr>");
			out.println("<tr>");
			}
		
		
			out.println("<td width=\"20%\" >"+rs2.getString(1)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(2)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(3)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(4)+"</td>");
			out.println("<td width=\"20%\" >"+rs2.getString(5)+"</td></tr>");
			
			more2=rs2.next();
			j=j+1;
			}

			out.println("</table>");

			out.println("<HR>");

			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr align='left' class='tr_input'>");  
			out.println("<td width='*%'><b><u>Condition List</td> ");
			out.println("</tr>");  

			out.println("<tr align='left'>");  
			out.println("<td width='10'><input class='but_input' type='button' name='MORE_BUT' value=\"Add\" onClick=\"add_row()\"></td>"); 
			out.println("</tr>");  
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<HR>");

			out.println("<br>"); 
			out.println("<DIV id='score_details'  class=div_input></DIV>");			
			out.println("<br>"); 
			
			//-------------ADDED BY CHANDANA ON 10/04/2007-------------------//	
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
      out.println("<td width='30%' ><B>Remarks</B></td>");
			out.println("<td width='*%' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<hr>");			
					
			out.println("<table align='center' width='100%' class='table'>"); 
		/*	out.println("<tr >"); 
      out.println("<td width='30%' valign='top'><B>Enter Level:-</B></td>");
		  out.println("<td width=\"*%\"><DIV ID='m_table_remaks1'></DIV></td>");  //<DIV ID='m_table_remaks1'></DIV>
			out.println("</tr>"); */
			out.println("<tr >"); 
      out.println("<td width='30%' valign='top'><B>Credit Verification Level:-</B></td>");
			out.println("<td width='*%' ><DIV ID='m_table_remaks2'></DIV></td>"); //<DIV ID='m_table_remaks2'></DIV>
			out.println("</tr>");		
			out.println("<tr >"); 
      out.println("<td width='30%' valign='top'><B>Credit Score Evaluation Level:-</B></td>");
			out.println("<td width='*%' ><DIV ID='m_table_remaks3'></DIV></td>"); //<DIV ID='m_table_remaks2'></DIV>
			out.println("</tr>");	
			
			if(m_pre_stage.equals("VERIFY-M")){
			out.println("<tr >"); 
      out.println("<td width='30%' valign='top'><B>Credit Approval 1:-</B></td>");
			out.println("<td width='*%' ><DIV ID='m_table_remaks4'></DIV></td>"); //<DIV ID='m_table_remaks2'></DIV>
			out.println("</tr>");	
			}
						
			out.println("</table>");
			out.println("<hr>");	
			//-------------- END --------------------------//
			
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			
			
			
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 

 
			out.flush();
			out.close();
			conn.close();
			this.destroy();
			}
			catch (Exception e) { 
			try { 
		
			}	 
			catch (Exception eti) {}
		
			ByteArrayOutputStream ostr = new ByteArrayOutputStream(); 
			e.printStackTrace(new PrintStream(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString()); 
			out.close();
			
			}
	}
}


