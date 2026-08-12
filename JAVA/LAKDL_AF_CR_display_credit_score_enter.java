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


public class LAKDL_AF_CR_display_credit_score_enter extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
	public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			//String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_username=m_sn_methods.username;
			stmt=conn.createStatement();
			String m_user_name="";
			
			String m_Score_Model="";
			String m_Eval_User="";
			String m_Final_App_Score="";
			String m_Model_Score="";
			String m_Comments="";
			String m_return_status="" , m_return_comment="" ; //added by nuwan de silva on 04-10-07
			
			
			
			
			String m_application   = req.getParameter("application_no");
			String m_screen_type   = req.getParameter("screen_type");
			String m_finance_no    = req.getParameter("finance_no");
			
			rs= stmt.executeQuery ("SELECT "+
				"	USER "+
				"	FROM DUAL ");
			
			boolean more=rs.next();
			if(more)
			{
				m_user_name=rs.getString(1);
			}
			
			//added by nuwan de silva on 04-10-07=======================
			rs = stmt.executeQuery(" SELECT "+		
				" STATUS,NVL(REMARK,'-')  FROM "+
				" "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
				" WHERE APPLICATION_NO='"+m_application+"' "+
				" AND   ENT_DATE =( "+
				" SELECT  MAX(ENT_DATE) "+
				" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
				" WHERE APPLICATION_NO='"+m_application+"') "); 
			
			more=rs.next();
			
			if(more){
				m_return_status   = rs.getString(1);
				m_return_comment  = rs.getString(2);
			}
			
			rs.close();
			//==========================================================
			
			if(m_screen_type.equals("REVERSE") ){
				
				rs= stmt.executeQuery ("SELECT "+
					" SCORE_MODEL_CODE, "+ 
					" EVAL_USER, "+ 
					" NVL(FINAL_APP_SCORE,0) FINAL_APP_SCORE, "+ 
					" NVL(MODEL_SCORE,0) MODEL_SCORE, "+ 
					" NVL(COMMENTS,'-') COMMENTS "+ 
					" FROM "+m_schema_name+".AF_CR_PRO_CRSCORE "+ 
					" WHERE APP_STATUS=('V-APP') AND UPPER(APPLICATION_CODE)=UPPER('"+m_application+"')");
				
				more=rs.next();
				if(more)
				{
					m_Score_Model=rs.getString(1);
					m_Eval_User=rs.getString(2);
					m_Final_App_Score=rs.getString(3);
					m_Model_Score=rs.getString(4);
					m_Comments=rs.getString(5);
				}
				
				
			}
			
			
			
			//stmt1=conn.createStatement();
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process - Credit Score Evaluation </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var field");
			out.println("var field1");
			out.println("var m_return_status='"+m_return_status+"'"); //added by nuwan de silva on 04-10-07
			
			
			
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && field=='T1' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("help_update_application();");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T2' && document.Form1.TXT_CREDIT_EVAL.value!=\"\"){");
			out.println("help_update_user();");
			out.println("			}");
			out.println("	else	if(data_vec.length>0  && field=='T4' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("assing_remarks1(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T4' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("assing_blank_remarks1(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length>0  && field=='T5' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("assing_remarks2(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T5' && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("assing_blank_remarks2(data_vec);");
			out.println("			}");
			out.println("	else	if(data_vec.length==0  && field=='T3' && document.Form1.TXT_SCORE_MODEL_CODE.value!=\"\"){");
			out.println("help_update_model();");
			out.println("			}");
			
			out.println("}");
			
			out.println("function check_app(obj) {");
			out.println("field='T1'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_Application_no&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=VERIFY1\";");
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function check_credit(obj) {");
			out.println("field='T2'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_user&data_val=\"+document.Form1.TXT_CREDIT_EVAL.value+\"&ac_status=VERIFY1\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function check_score() {");
			out.println("field='T3'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation&data_val=\"+document.Form1.TXT_CREDIT_EVAL.value+\"&ac_status=VERIFY1\";");
			
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function makeRequest(obj) {");
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
			out.println("url=\"\";");
			out.println("if(obj==\"M1\"){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_EDIT&pre_stage1='ENTER'&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&application=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("}else{");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_ENTER&pre_stage1='ENTER'&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value;");
			out.println("}");
			out.println("http_request.onreadystatechange = function() {");
			out.println("alertContents(http_request,1,obj); ");
			out.println("};");
			out.println("http_request.open('GET',url, true);");
			out.println("http_request.send(null);");
			out.println("}");
			
			// ------------------ADDED BY CHANDANA ON 10/04/2007 ---------------- //
			
			
			out.println("function get_remarks1(){ ");		
			out.println("field='T4'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=COMPLETED\";");
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
			
			
			//added by nuwan de silva on 04-10-07---------
			out.println("function show_return_comment(){"); 
			out.println("if(m_return_status=='RET-VE-APP') {");		
			out.println("m_table_return.innerHTML+='<table width=\"100%\" align=\"center\" class=table border=\"0\" ><tr>'+");		
			out.println("													 '<td width=\"30%\" valign=\"top\"  ><b>Return Comment</td>'+"); 
			out.println("													 '<td width=\"*%\"                  ><div id=m_value></div></td>'+"); 
			out.println("													 '</tr></table>';");		
			out.println("m_value.innerHTML=\""+m_return_comment+"\";");//added by nuwan de silva on 30-11-2007
			out.println("}");		
			out.println("else {");		
			out.println("m_table_return.innerHTML='';");		
			out.println("}");		
			out.println("}");		
			
			out.println("function get_remarks2(){ ");		
			out.println("field='T5'");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_CREDIT_PROCESS_remarks2&data_val=\"+document.Form1.TXT_APPLICATION_NO.value+\"&ac_status=COMPLETED\";");
			out.println("load_interface(m_url,'XML');");			
			out.println("}");			
			
			out.println("function assing_remarks2(data_vec) { ");
			out.println("m_table_remaks2.innerHTML='<table align=\"left\" width=\"100%\" class=\"table\"><tr>'+");									
			out.println("'<TD WIDTH=\"90%\" align=\"left\">'+data_vec[1]+'</TD>'+");
			out.println("'<TD WIDTH=\"10%\" ></TD>' +");
			out.println("'</tr></table>';");
			
			
			
			
			if(m_screen_type.equals("REVERSE")){
				out.println("    makeRequest('M1');"); //Added By Nuwan De Silva 16-05-07
			}
			
			out.println("}");	
			
			out.println("function assing_blank_remarks2() { ");
			out.println("m_table_remaks2.innerHTML='';");
			out.println("}");	
			
			// ------------------------ END ------------------------- //
			
			
			
			out.println("function alertContents(http_request,count,obj) {");
			out.println(" if (http_request.readyState == 4) {");
			out.println("    if (http_request.status == 200) {");
			out.println("      	if(http_request.responseText!=\"\"){");
			out.println(" 				m_data=http_request.responseText;");
			out.println("					if(obj==\"M1\"){");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("					else{");
			out.println("						score_details.innerHTML=m_data;");
			out.println("					}");
			out.println("				}");
			out.println("    } else {");
			out.println("        alert('There was a problem with the request.');");
			out.println("    }");
			
			out.println(" }");
			out.println("}");
			
			out.println("function valdate_values(obj,min,max){");
			out.println("objname=document.Form1.elements[\"SCORE_\"+obj];");
			out.println("if((parseFloat(objname.value)>parseFloat(max)) || (parseFloat(objname.value)<parseFloat(min))){");
			
			//out.println("alert('Credit score should between the Min and Max value for the Score Category');"); //comment by nuwan de silva 22-05-07
			out.println("alert(\"Credit score should between the Min : \"+min+\" and Max : \"+max+\" value for the Score Category\");");//added by nuwan de silva 22-05-07
			
			out.println("objname.value=\"0\";");
			out.println("}");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function load_default_score(val1){");
			out.println("obj_select=document.Form1.elements[\"RATE_\"+val1];");
			out.println("val=obj_select.value;");
			out.println("obj1=document.Form1.elements[\"RATE_VAL_\"+val];");
			out.println("obj2=document.Form1.elements[\"SCORE_\"+val1];");
			out.println("obj3=document.Form1.elements[\"MAX_\"+val1];");
			out.println("obj2.value=format_noobject((parseFloat(obj3.value)/100)*parseFloat(obj1.value));");
			out.println("obj4=document.Form1.elements[\"NUM_COLS\"];");
			out.println("m_val=0;");
			out.println("for(i=1;i<parseInt(obj4.value);i++){");
			out.println("obj5=document.Form1.elements[\"SCORE_\"+i];");
			out.println("m_val=parseFloat(m_val)+parseFloat(obj5.value);");
			out.println("}");
			out.println("document.Form1.TXT_TOTAL_SCORE_APP.value=format_noobject(m_val);");
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(parseFloat(document.Form1.TXT_TOTAL_SCORE_APP.value)>parseFloat(document.Form1.TXT_TOTAL_SCORE.value)){");
			out.println("DIV_TXT_TOTAL_SCORE_AP.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("if(document.Form1.TXT_CREDIT_EVAL.value==\"\"){  "); 
			out.println("DIV_TXT_USER.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_SCORE_MODEL_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_SCORE_MODEL_CODE.style.color='red';");
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
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_save_credit_score_enter';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 
			
			out.println("function load_lock(){	");  
			
			out.println("document.Form1.TXT_APPLICATION_NO.value='"+m_application+"'");
			
			
			
			if(m_screen_type.equals("NEW")){
				
				out.println("document.Form1.BUT_HELP_MAIN.disabled=true");
				out.println("document.Form1.button_del.disabled=true");
				out.println("document.Form1.button_edit.disabled=true");
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=true");
				
			}
			
			if(m_screen_type.equals("REVERSE")){
				//	out.println("");
				out.println("document.Form1.button_new.disabled=true");
				out.println("document.Form1.BUT_HELP_MAIN.disabled=true");
				out.println("document.Form1.TXT_APPLICATION_NO.disabled=true");
				out.println("document.Form1.BUT_HELP_UPDATE.disabled=true");
				out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value='"+m_Score_Model+"';"); 
				out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value='"+m_Final_App_Score+"';"); 
				out.println("    document.Form1.TXT_TOTAL_SCORE.value='"+m_Model_Score+"';"); 
				out.println("    document.Form1.TXT_COMMENTS.value='"+m_Comments+"';"); 
			}
			out.println("show_return_comment();"); //added by nuwan de silva 04-10-07
			out.println("get_remarks1();");
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_display_credit_score_enter?screen_type="+m_screen_type+"&application_no="+m_application+"&finance_no="+m_finance_no+"';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_display_credit_score_enter?screen_type="+m_screen_type+"&application_no="+m_application+"&finance_no="+m_finance_no+"';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CR_display_credit_score_enter\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Evaluation  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Credit Score Evaluation  - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			
			out.println("document.Form1.BUT_HELP_MAIN_USER.disabled=false;"); 
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';"); 
			
			
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){"); 
			out.println("clear_data_edit_delete()");
			
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}");  
			out.println("else if(m_val==\"DELETE\"){"); 
			//out.println("clear_data_edit_delete()");
			//out.println("clear_data_disable()");
			
			out.println("document.Form1.hid_status.value=\"Delete\";");  
			out.println("document.Form1.hid_save.value=\"Delete\";"); 
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
			out.println("else if(IfCount=='10'){"); 
			out.println("		help_update_value_assign_10(oBj);"); 
			out.println("		}"); 
			out.println("else if(IfCount=='100'){"); 
			out.println("		help_update_value_assign_100(oBj);"); 
			out.println("		}");
			out.println("}");
			out.println("if(oBj.valout[2]==undefined){");
			out.println("Close_2()	");
			out.println("	}"); 
			out.println("}");	
			
			
			
			out.println(" function Close_2(){");//**
			out.println("clear_data()	");
			out.println(" }");
			
			
			out.println("function clear_data_edit_delete() {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=false");
			out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			out.println("document.Form1.BUT_HELP_UPDATE.disabled=true");
			out.println("}");
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("    document.Form1.TXT_COMMENTS.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			
			out.println("function clear_data_disable() {");
			//	out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			//	out.println("document.Form1.TXT_CREDIT_EVAL.disabled=true;");
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.disabled=true;");
			out.println("    document.Form1.BUT_HELP_UPDATE.disabled=true;");
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.disabled=true;");
			out.println("    document.Form1.TXT_TOTAL_SCORE.disabled=true;");
			out.println("    document.Form1.TXT_COMMENTS.disabled=true;");
			out.println("}");
			
			
			
			out.println("function clear_data() {");
			out.println("if(document.Form1.hid_help_type.value==\"10\"){");
			//out.println("document.Form1.TXT_APPLICATION_NO.value='';");
			//out.println("document.Form1.TXT_APPLICATION_NO.focus();");
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value='';"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("    document.Form1.TXT_COMMENTS.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"100\"){");
			out.println("document.Form1.TXT_CREDIT_EVAL.value='';");
			out.println("document.Form1.TXT_CREDIT_EVAL.focus();");
			out.println("}");
			out.println("if(document.Form1.hid_help_type.value==\"99\"){");
			out.println("document.Form1.TXT_SCORE_MODEL_CODE.value='';");
			out.println("    document.Form1.TXT_TOTAL_SCORE.value='';"); 
			out.println("score_details.innerHTML=\"\";");
			out.println("}");
			out.println("}");
			
			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			
			out.println("function help_button_8() {"); 
			out.println("    document.Form1.hid_help_type.value=\"8\";"); 
			out.println("    m_sql = \"m_help_TXT_APPLICATION_STATUS_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_STATUS.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_value_assign_8(oBj) {"); 
			out.println("    document.Form1.TXT_APPLICATION_STATUS.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_update_user() {"); 
			out.println("    document.Form1.hid_help_type.value=\"100\";"); 
			out.println("    Sql = \"m_help_TXT_USER_ID_sql\";"); 
			out.println("    Crit = document.Form1.TXT_CREDIT_EVAL.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,6,Crit,Sql,100);"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_100(oBj) {"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[2];"); 
			out.println("}"); 
			
			
			out.println("function help_update_model() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Sql = \"m_help_TXT_SCORE_MODEL_CODE_sql\";"); 
			out.println("    Crit = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,99);"); 
			out.println("}"); 
			
			
			out.println("function help_update_application() {");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    Sql = \"m_help_cr_score_application_sql_1\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"VERIFY1@\";"); 
			out.println("    HelpBox(0,10,0,Crit,Sql,10);"); 
			out.println("}");
			out.println("else if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.hid_help_type.value=\"10\";"); 
			out.println("    Sql = \"m_help_cr_score_application_sql\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"V-APP@\";"); 
			out.println("    HelpBox(0,10,4,Crit,Sql,10);"); 
			out.println("}");
			out.println("}"); 
			
			out.println("function help_update_value_assign_10(oBj) {"); 
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    makeRequest('M1');");
			out.println(" get_remarks1();");
			out.println("}"); 
			out.println("else if(document.Form1.SCREEN_NAME.value!=\"NEW\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_CREDIT_EVAL.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE_APP.value=oBj.valout[5];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[6];"); 
			out.println("    document.Form1.TXT_COMMENTS.value=oBj.valout[7];"); 
			out.println("    makeRequest('M1');");
			out.println(" get_remarks1();");
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[4];"); 
			out.println("    makeRequest('M2');");
			out.println("}"); 
			
			out.println("function load_default_user(){");
			out.println("    document.Form1.TXT_CREDIT_EVAL.value='"+m_username+"';"); 
			out.println("}"); 
			
			out.println("function load_view_score(){");
			out.println("if(document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS_VIEW&pre_stage1='ENTER'&model=\"+document.Form1.TXT_SCORE_MODEL_CODE.value+\"&application=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("window.open(url,'win1','left=200,top=200,width=500,height=300,toolbar=0,location=center,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,fullscreen=0');");
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){");
			out.println("alert('Please enter Application No to view report')");
			out.println("}");
			out.println("}"); 
			
			out.println("function count_length1(val){");
			out.println("var j=val.value.length - 999 ");
			out.println("	if(val.value.length >999){");
			out.println("document.Form1.TXT_COMMENTS.focus()");
			out.println("}");
			out.println("}");
			
			out.println("function count_length(val){");
			out.println("var j=val.value.length - 999 ");
			out.println("	if(val.value.length >999){");
			out.println("	alert('Comment is too long, remove '+j+ 'character')");
			out.println("document.Form1.TXT_COMMENTS.focus()");
			out.println("}");
			out.println("}");
			
			
			
			
			//=========added by nuwan de silva 22-05-07=============
			//===========validate the number=======================
			
			out.println("	function chk_comment_length(obj){ ");
			
			out.println(" var remarks_length=obj.value.toString().length;");
			
			out.println("if(remarks_length>obj.maxlength) ");
			out.println("		window.event.keyCode=\"\"; ");
			
			out.println("} ");
			
			
			out.println("function count_length(obj){ ");
			out.println("var remarks_length=obj.value.toString().length; ");
			out.println("var remarks=obj.value.toString(); ");
			out.println("if(remarks_length>obj.maxlength){ ");
			out.println("obj.value=remarks.substring(0,obj.maxlength); ");
			out.println("} ");
			out.println("} ");
			
			
			
			//================================================
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='1' topmargin='10' marginwidth='0' onload=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			
			if(m_screen_type.equals("NEW")){
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> ");
			}
			if(m_screen_type.equals("REVERSE")){
				out.println("<input  type='hidden' value='DELETE' name='SCREEN_NAME'> ");
			}
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			
			if(m_screen_type.equals("NEW")){
				
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">");
			}
			
			if(m_screen_type.equals("REVERSE")){
				
				//out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Reverse\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Delete\">");
			}
			
			
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
			out.println("<tr>"); 
			out.println("<td width='1' valign='top'><img src='spacer.gif' width='1' height='1'></td>"); 
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
			
			if(m_screen_type.equals("NEW")){
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Credit Score Evaluation - New </td>"); 
			}
			
			else if(m_screen_type.equals("REVERSE")){
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Credit Score Evaluation - Delete </td>"); 
			}
			
			out.println("</tr>"); 
			
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input name=\"button_new\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input name=\"button_edit\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input name=\"button_del\" type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DELETE\")' value=\"Delete\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View\");' onClick='load_view_score();load_screen_status(\"VIEW\")' value=\"View\"></td>");  
			out.println("<td width='10%' align='center'></td>");  
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
			
			
			out.println("<table align='center' width='100%' class='table'  border='0'>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='20%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='10' onblur=\"check_app(document.Form1.TXT_APPLICATION_NO)\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update_application()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER'  class=div_input>Contract Number</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CONTRACT_NUMBER' value=\""+m_finance_no+"\" maxlength='10' size='10'disabled>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 

			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_USER'  class=div_input>Credit Evaluator *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CREDIT_EVAL' value=\""+m_username+"\" maxlength='10' size='10' onblur=\"check_credit(document.Form1.TXT_CREDIT_EVAL)\" disabled>"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN_USER' value=\"Help\" onClick=\"help_update_user()\" disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_SCORE_MODEL_CODE'  class=div_input>Score Model Code *</DIV></td>"); 
			//---modified by : delanjali---------------------------------------------------------------------------------------------------------------------------------------
			//---date				: 2007-06-19---------------------------------------------------------------------------------------------------------------------------------------
			//	out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCORE_MODEL_CODE' maxlength='10' size='10' onblur=\"makeRequest('M1')\" disabled>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCORE_MODEL_CODE' maxlength='10' size='10' onblur=\"makeRequest('M1'),check_score()\" >"); 
			
			out.println("<input class='but_input' type='button' name='BUT_HELP_UPDATE' value=\"Help\" onClick=\"help_update_model()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_SCORE_AP'  class=div_input>Total Score for Application</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_SCORE_APP' maxlength='3' size='3' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_SCORE'  class=div_input>Total Score for Model</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_SCORE' maxlength='3' size='3' disabled></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINAL_COMMENTS'  class=div_input>Comments</DIV></td>"); 
			out.println("<td width='40%' ><TEXTAREA class='txt_input' name='TXT_COMMENTS'  maxlength='1000' style='{width:750px; height:50px;}' size='200' onKeypress=\"chk_comment_length(this)\" onKeyDown=\"count_length(this)\" onKeyUp=\"count_length(this)\" ></TEXTAREA></td>");  //onblur=\"count_length(this)\" modified by nuwan de silva 22-05-07
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>");
			
			
			//----------------ADDED BY CHANDANA ON 10/04/2007------------------------//
			out.println("<br>");
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' ><B>Remarks</B></td>");
			out.println("<td width='*%' ></td>");
			out.println("</tr>"); 
			out.println("</table>");
			
			out.println("<hr>");
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			//   out.println("<td width='30%' valign='top'><B> Enter Level:-</B></td>"); //comment by nuwan de silva 22-05-07
			out.println("<td width='30%' valign='top'><B>Application Entry Level:-</B></td>"); //added by nuwan de silva 22-05-07
			//out.println("<td width='*%' >TEST rereeer </td>");
			out.println("<td width=\"*%\"><DIV ID='m_table_remaks1'></DIV></td>");
			out.println("</tr>"); 
			out.println("<tr >"); 
			out.println("<td width='30%' valign='top'><B>Credit Verification Level:-</B></td>");
			out.println("<td width='*%' ><DIV ID='m_table_remaks2'></DIV></td>");
			out.println("</tr>");			
			
			
			//===================================================================
			out.println("</table>");
			
			//added by nuwan de silva on 04-10-07-----------------------------
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr >"); 
			out.println("<td width='*%' ><DIV ID='m_table_return'></DIV></td>");
			out.println("</tr>");		
			out.println("</table>");
			
			out.println("<hr>");
			//----------------END ---------------------------------//
			
			out.println("<br>"); 
			out.println("<DIV id='score_details'  class=div_input></DIV>");
			
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


