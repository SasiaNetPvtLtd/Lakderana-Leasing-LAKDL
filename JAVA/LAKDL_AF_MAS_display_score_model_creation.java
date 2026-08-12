
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - CREDIT SCORE MODEL CREATION
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MAS_display_score_model_creation extends javax.servlet.http.HttpServlet { 
	
	Connection conn;
	Statement stmt,stmt1;
  public ResultSet rs,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			//SCREEN_METHODS m_sn_methods = new SCREEN_METHODS(); 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html");
			//res.setHeader("Cache-Control","No-Cache");
			
			ServletOutputStream out = res.getOutputStream(); 
			
			String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("NEW")){
			
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>System Administration - Credit Scoring Models</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				
				out.println("function get_vector(data_vec) {");
				out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H1' ){");
				out.println("				alert('Record already exists');");
				out.println("				new_window();");
				out.println("			}");
				out.println("		  else	if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_desc' ){");
				out.println("				alert('Record already exists');");
				out.println("				help_update_desc();");
				out.println("			}");
				out.println("		  else	if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_SCORE_MODEL_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
				out.println("				assign_data(data_vec);");
				out.println("			}");
				out.println("		  else	if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_SCORE_MODEL_CODE.value !=''&& document.Form1.hid_help_status.value == 'H1' ){");
				out.println("				help_update();");
				out.println("			}");
				out.println("}");
				
				out.println("function val_num(obj){");
				out.println("if(isnumberok(obj,3)){");
				out.println("format_noobject_nodecimal1(obj)");
				out.println("}"); 
				out.println("else");
				out.println("if(obj.value!=\"\"){");
				out.println("alert('Please enter a number');");
				out.println("obj.value=\"\"");
				out.println("obj.focus();");
				out.println("}"); 
				out.println("}");

				out.println("function makeRequest(obj,obj1) {");
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
				out.println("if(obj1==\"M1\"){");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation&data_val=\"+obj.value;");
				out.println("}else{");
				out.println("url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_SCORE_validations?chksql=SCORE_DETAILS&model=\"+obj;");
				out.println("}");
				out.println("http_request.onreadystatechange = function() {");
				out.println("alertContents(http_request,1,obj1); ");
				out.println("};");
				out.println("http_request.open('GET',url, true);");
				out.println("http_request.send(null);");
				out.println("}");
				
				
			out.println("function makeRequest1(obj) {");
			//m_prime_chk_LAKDL_AF_MAS_display_score_model_creation
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_help_status.value == 'H1'){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation&data_val=\"+obj.value;");
			//out.println(" 	 window.open(m_url); ");
			out.println("		 }");
			out.println("    else");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"  && document.Form1.hid_help_status.value == 'H_desc'){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation_desc&data_val=\"+obj.value;");
			//out.println(" 	 window.open(m_url); ");
			out.println("		 }");
			out.println("    else");
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"RACT\" && document.Form1.hid_help_status.value == 'H1' ){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation1&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("		 }");
			out.println("    else");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"RACT\" && document.Form1.hid_help_status.value == 'H1' ){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation1&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("		 }");
			out.println("    else");
			out.println("    if(document.Form1.hid_help_status.value == 'H_desc'){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_score_model_creation_desc&data_val=\"+obj.value;");
			out.println("		}");
			out.println("    else");
			out.println("    if((document.Form1.SCREEN_NAME.value!=\"RACT\")&&(document.Form1.SCREEN_NAME.value!=\"NEW\")){");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_country&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("		}");
    	out.println("    if(document.Form1.SCREEN_NAME.value==\"RACT\")");
      out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_country&data_val=\"+obj.value+\"&ac_status=N\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
				
				
				
				out.println("function alertContents(http_request,count,obj) {");
				out.println(" if (http_request.readyState == 4) {");
				out.println("    if (http_request.status == 200) {");
				out.println("      	if(http_request.responseText!=\"\"){");
				out.println(" 				m_data=http_request.responseText;");
				out.println("					if(obj==\"M1\"){");
				out.println("						if(m_data==\"Y\"){");
				out.println("							if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("							alert('Record already exsist');");
				out.println("							new_window();");
				out.println("							}");	
				out.println("						}");
				out.println("					}");
				out.println("					else{");
				out.println("						//alert(m_data);");
				out.println("						score_details.innerHTML=m_data;");
				out.println("					}");
				out.println("				}");
				out.println("    } else {");
				out.println("        alert('There was a problem with the request.');");
				out.println("    }");
				out.println(" }");
				out.println("}");
	
				out.println("function validate_data(){"); 
				out.println("//validations goes here"); 
				out.println("if(document.Form1.TXT_SCORE_MODEL_CODE.value==\"\"){  "); 
				out.println("DIV_TXT_SCORE_MODEL_CODE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
				out.println("DIV_TXT_DESCRIPTION.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
				out.println("else if(document.Form1.TXT_TOTAL_SCORE.value==\"\"){  "); 
				out.println("DIV_TXT_TOTAL_SCORE.style.color='red';");
				out.println("return false;"); 
				out.println("}"); 
	
				out.println("else{"); 
				out.println("return true;"); 
				out.println("}"); 
				out.println("}"); 
	
				out.println("function before_submit(){ "); 
				out.println("   m_status = document.Form1.hid_status.value ");
				out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
				out.println("   if(m_status == \"Edit\"){ ");
				out.println("   m_save_msg = 'Are you sure you want to Modify ? '");
				out.println("   }"); 
				out.println("   else if(m_status == \"Deactivate\"){ ");
				out.println("   m_save_msg = 'Are you sure you want to Deactivate ? '");
				out.println("   }"); 
				out.println("   else if(m_status == \"Reactivate\"){ ");
				out.println("   m_save_msg = 'Are you sure you want to Reactivate ? '");
				out.println("   }"); 
				out.println("		if(validate_data()){"); 
				out.println("		if(confirm(m_save_msg)){ "); 
				out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		document.Form1.elements[i].disabled=false;");
				out.println("		}");
				//out.println("		if(validate_data()){");
				out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_score_model_creation';");  
				out.println("		document.Form1.submit();	"); 
				//out.println("		}"); 
				out.println("		}");
				out.println("		}"); 
				out.println("		else { "); 
				out.println("		alert(\"Please enter all required fields marked with a '*' on screen.\"); ");
				out.println("		}");
				out.println("} "); 
	
				out.println("function load_lock(){	"); 
				out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
	
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_score_model_creation?chksql=NEW';"); 
				out.println("		}"); 
				out.println("}"); 
	
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_display_score_model_creation?chksql=NEW';"); 
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
				out.println("help_box.innerHTML=\" System Administration - Credit Scoring Models - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
	
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" System Administration - Credit Scoring Models - \"+document.Form1.hid_status.value;"); 
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
				out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 
				out.println("document.Form1.TXT_TOTAL_SCORE.disabled=true;"); 
				out.println("}"); 
				out.println("else{");
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
				out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("if(m_val==\"NEW\"){");
				out.println("document.Form1.hid_status.value=\"New\";"); 
				out.println("}else if(m_val==\"EDIT\"){");  
				out.println("document.Form1.TXT_DESCRIPTION.disabled=false;"); 
				out.println("document.Form1.TXT_TOTAL_SCORE.disabled=false;"); 
				out.println("document.Form1.hid_status.value=\"Edit\";");  
				out.println("}else if(m_val==\"DACT\"){"); 
				
				/*out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
				out.println("		document.Form1.elements[i].disabled=true;");
				out.println("		}");
				out.println("document.Form1.TXT_SCORE_MODEL_CODE.disabled=false;"); 
				out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
				out.println("document.Form1.new.disabled=false;"); 
				out.println("document.Form1.edit.disabled=false;"); 
				out.println("document.Form1.save.disabled=false;"); 
				out.println("document.Form1.dact.disabled=false;"); 
				out.println("document.Form1.ract.disabled=false;"); 
				out.println("document.Form1.view.disabled=false;"); 
				out.println("document.Form1.close.disabled=false;"); 
				out.println("document.Form1.cancel.disabled=false;"); 
				out.println("document.Form1.help.disabled=false;"); */
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
				out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value='';"); 
				out.println("    document.Form1.TXT_SCORE_MODEL_CODE.focus();");
				out.println("		} else "); 
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
				out.println("	else{	"); 
			  out.println("       document.Form1.TXT_SCORE_MODEL_CODE.value='';");
			  out.println("       document.Form1.TXT_SCORE_MODEL_CODE.focus();");
			  out.println("	}	"); 
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
				
				out.println("function help_value_assign_1() {"); 
				out.println("    document.Form1.TXT_DESCRIPTION.value='';"); 
				out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
				out.println("}");
				
				out.println("function help_update() {"); 
				out.println("    document.Form1.hid_help_type.value=\"99\";"); 
				out.println("    m_sql = \"m_help_TXT_SCORE_MODEL_CODE_sql\";"); 
				out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"){ ");
				out.println("    m_criteria = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
				out.println("    } ");
				out.println("    else{");
				out.println("    m_criteria = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"N@\";}"); 
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function help_update_desc() {"); 
				out.println("    document.Form1.hid_help_type.value=\"1\";"); 
				out.println("    m_sql = \"m_help_TXT_SCORE_MODEL_CODE_DESC_sql\";"); 
				out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";");
				out.println("    HelpBox('1','10','0');"); 
				out.println("}"); 
				
				out.println("function assign_data(data_vec){ ");
				out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=data_vec[0];"); 
				out.println("    document.Form1.TXT_DESCRIPTION.value=data_vec[1];"); 
				out.println("    document.Form1.TXT_TOTAL_SCORE.value=data_vec[2];"); 
				//out.println("		assign_help_status('H4');");
				out.println("    makeRequest(document.Form1.TXT_SCORE_MODEL_CODE.value,'M2');");
				out.println("}");
	
				out.println("function help_update_value_assign_99() {"); 
				out.println("    document.Form1.TXT_SCORE_MODEL_CODE.value=oBj.valout[2];"); 
				out.println("    document.Form1.TXT_DESCRIPTION.value=oBj.valout[3];"); 
				out.println("    document.Form1.TXT_TOTAL_SCORE.value=oBj.valout[4];"); 
				out.println("    makeRequest(document.Form1.TXT_SCORE_MODEL_CODE.value,'M2');");
				out.println("}"); 
				
	//ONLOAD=\"load_lock()\"
	
	
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
			
			
			out.println(" function assign_help_status(obj){");
			//out.println(" alert('ok');");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("}");
			
			out.println("function View_all(){");	
			out.println("    m_sql = \"m_view_TXT_SCORE_MODEL_CODE_sql\";");
			out.println("    m_criteria = document.Form1.TXT_SCORE_MODEL_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
	
	
	
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"makeRequest('NEW','M2')\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Credit Scoring Models</td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' name=\"new\" onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' name=\"edit\" onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' name=\"dact\" onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' name=\"ract\" onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");' name=\"view\" onclick='View_all()' value=\"View All\"></td>");
				out.println("<td width='6%'></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");' name=\"save\" onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' name=\"help\" onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");' name=\"cancel\" onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");' name=\"close\"  onclick='close_window()' value=\"Close\"></td>");  
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
	
				out.println("<table align='center' width='100%' class='table'>"); 
	
				out.println("<tr >"); 
				out.println("<td width='1%'></td>"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_SCORE_MODEL_CODE'  class=div_input>Score Model Code *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCORE_MODEL_CODE' maxlength='10' size='10' onblur=\"assign_help_status('H1'),makeRequest1(document.Form1.TXT_SCORE_MODEL_CODE)\">"); 
				//out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_SCORE_MODEL_CODE' maxlength='10' size='10' onblur=\"makeRequest(document.Form1.TXT_SCORE_MODEL_CODE,'M1'),assign_help_status('H1'),makeRequest1(document.Form1.TXT_SCORE_MODEL_CODE)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" disabled></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr >"); 
				out.println("<td width='1%'></td>"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_DESCRIPTION'  class=div_input>Score Model Description *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_DESCRIPTION'  onblur=\"assign_help_status('H_desc'),makeRequest1(document.Form1.TXT_DESCRIPTION)\"  style=\"{width:200px;}\"  maxlength='100' ></td>"); //size='50'
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("<tr >"); 
				out.println("<td width='1%'></td>"); 
				out.println("<td width='30%' ><DIV id='DIV_TXT_TOTAL_SCORE'  class=div_input>Total Score *</DIV></td>"); 
				out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_TOTAL_SCORE' maxlength='3' size='3' onblur='val_num(this)' STYLE=\"{text-align:right;}\" ></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("<DIV id='score_details'  class=div_input></DIV>");
				
				out.println("<br>"); 
				out.println("<table align='center' width='100%'>"); 
				out.println("<tr>"); 
				out.println("<td width='100%' class='note'></td>"); 
				out.println("</tr>"); 
				out.println("</table>"); 
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
			}

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


