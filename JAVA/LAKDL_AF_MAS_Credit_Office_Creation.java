//milinda 2013-10-15

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MAS_Credit_Office_Creation extends javax.servlet.http.HttpServlet { 
	
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
			out.println("<TITLE>System Administration - Credit Officer</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'M1' ){");
			out.println("				alert('Record already exists');");
			out.println("				new_window();");
			out.println("			}");
			
			
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M_MKT_OFFC'  && document.Form1.MKT_OFFICER.value!=\"\"){");
			out.println("				mk_officer_help('11');");  
			
			out.println("			}");
			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M_MKT_OFFC'  && document.Form1.MKT_OFFICER.value!=\"\"){");
			out.println("           document.Form1.MKT_OFFICER.value=data_vec[0];"); 
			out.println("			}");	
			
			out.println("			if(data_vec.length==0 && document.Form1.hid_chk_status.value=='M1'  && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
		//	out.println("	alert('Record already exists');");
			out.println("			help_update('1','10','10','m_help_TXT_APPLICATION_NOCK','99','0');");//,m_help_TXT_APPLICATION_NO
			
			out.println("			}");
			out.println("			if(data_vec.length > 0 && document.Form1.hid_chk_status.value=='M1'  && document.Form1.TXT_APPLICATION_NO.value!=\"\"){");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[0];");
			//out.println("    document.Form1.MKT_OFFICER.value=data_vec[9];");
			out.println("    document.Form1.hid_inqury.value=data_vec[7];");
			out.println("alert(data_vec[7]);");
			out.println("			}");	
			
			
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1'){");
			out.println("				help_update('1','10','10','m_help_TXT_APPLICATION_NO','99','0');");
			out.println("			}");
			//out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value!=\"NEW\" && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
			
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[1];");
			//out.println("    document.Form1.MKT_OFFICER.value=data_vec[9];");
			out.println("    document.Form1.hid_inqury.value=data_vec[7];");
			
			out.println("}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			//out.println("m_gur_code=\"TXT_GAURANTOR_CODE\"+document.Form1.hid_row_no.value;");
			out.println("if(document.Form1.hid_chk_status.value=='M1'){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_CK&data_val=\"+obj.value+\"&ac_status=ENTERED&ac_status2=ENT_CON\";");
			//out.println("window.open(m_url);");
			out.println("}else if(document.Form1.hid_chk_status.value=='M2')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M3')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&data_val2=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&data_val3=\"+document.Form1.elements[m_gur_code].value+\"&ac_status=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M4')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process&data_val=\"+document.Form1.TXT_CORE_APPLICANT_CODE.value+\"&data_val2=\"+document.Form1.TXT_APPLICANT_CODE.value+\"&data_val3=\"+document.Form1.elements[m_gur_code].value+\"&ac_status=Y\";");
			out.println("else if(document.Form1.hid_chk_status.value=='M6')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_LAKDL_AF_MK_Application_Process_val_inq_no&data_val=\"+obj.value; ");
			out.println("else if(document.Form1.hid_chk_status.value=='M7')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_data&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_VAL')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_inv_valuation&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			//added by nuwan de silva 25-06-07------------------------------------
			out.println("else if(document.Form1.hid_chk_status.value=='M_BRANCH')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_application_process_branch_code&data_val=\"+obj.value+\"&ac_status=Y\";");
			//--------------------------------------------------------------------
			//Added by Chandana on 30/11/2007-------------------------------------
			out.println("else if(document.Form1.hid_chk_status.value=='M_MKT_OFFC')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer&data_val=\"+obj.value+\"&ac_status=Y\";");
			//--------------------------------------------------------------------
			//out.println("window.open(m_url);");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_INS_OFFC')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_Application_Process_mkt_officer&data_val=\"+obj.value+\"&ac_status=Y\";");
			
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");	
			out.println("}");
			
			out.println("function makeRequest1(obj) {");
			out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations1?chksql=m_prime_chk_LAKDL_AF_MAS_display_conasst_1&data_val=\"+obj.value;");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			out.println("function assign_data(data_vec){ ");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=data_vec[1];"); 
			out.println("    document.Form1.MKT_OFFICER.value=data_vec[0];"); 
			out.println("    document.Form1.hid_inqury.value=data_vec[2];"); 
			out.println("}");
			
			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.MKT_OFFICER.value==\"\"){  "); 
			out.println("DIV_MKT_OFFICER.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_APPLICATION_NO.value==\"\"){  "); 
			out.println("DIV_TXT_APPLICATION_NO.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			out.println("function before_submit(){ "); 
			
			
			out.println("		if(validate_data()){"); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_credit_officer';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 
			
			out.println("function load_lock(){	"); 
			//	out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Credit_Office_Creation';"); 
			out.println("		}"); 
			out.println("}"); 
			
			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_Credit_Office_Creation';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MAS_display_mileage\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 
			
			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" System Administration - Credit Officer - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" System Administration - Credit Officer - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 
			
			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			//out.println("document.Form1.TXT_SUB_MODEL.disabled=true;"); 
			out.println("document.Form1.BUT_CONDITION_OF_ASSET.disabled=true;"); 
			out.println("document.Form1.MKT_OFFICER.disabled=true;"); 
			//out.println("document.Form1.BUT_TXT_SUB_MODEL.disabled=true;"); 
			//out.println("document.Form1.BUT_CONDITION_OF_ASSET.disabled=true;"); 
			
			
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			//out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DACT\"){");  
			out.println("document.Form1.hid_status.value=\"Deactivate\";"); 
			out.println("document.Form1.hid_save.value=\"Deactivate\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 
			
			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Max+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println(" var m_url = '"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Max+'&Args=';	"); 
			out.println("popupwin=window.showModalDialog(m_url, oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			out.println("if(oBj.valout[1]=='Next')  {");
			out.println("Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount,Max);");
			out.println("}");
			out.println("else if  (oBj.valout[1]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			out.println("}");
			out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
			out.println("if(IfCount=='99'){"); 
			out.println("		help_update_value_assign_99(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='1'){"); 
			out.println("		help_value_assign_1(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='2'){"); 
			out.println("		help_value_assign_2(oBj);"); 
			out.println("");
			out.println("}");
			out.println("else if(IfCount=='3'){"); 
			out.println("		help_value_assign_3(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='4'){"); 
			out.println("		help_value_assign_4(oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='5'){"); 
			out.println("		help_value_assign_5(document.Form1.hid_row_no.value,oBj);"); 
			out.println("}");
			out.println("else if(IfCount=='6'){"); 
			out.println("		help_value_assign_6(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='9'){"); 
			out.println("		help_value_assign_location(oBj);"); 
			out.println("}");
			
			out.println("else if(IfCount=='8'){"); 
			out.println("		help_value_assign_8(oBj);"); 
			out.println("}");	
			
			out.println("else if(IfCount=='10'){"); 
			out.println("		brk_assign(oBj);"); 
			out.println("}");	
			
			out.println("else if(IfCount=='11'){"); 
			out.println("		mk_officer_assign(oBj);"); 
			out.println("}");		
			out.println("else if(IfCount=='12'){"); //added by nuwan de silva
			out.println("		ins_officer_assign(oBj);"); 
			out.println("}");	
			
			
			out.println("	}"); 
			out.println("	}"); 
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount,Max);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount,Max){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount,Max){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function clear_fields(){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"99\") {" ); 
			out.println("    document.Form1.TXT_APPLICATION_NO.value =''; ");
			out.println("   }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"2\") {" ); 
			out.println("    document.Form1.TXT_APPLICANT_CODE.value =''; ");
			out.println("  }		"); 
			out.println("	 else	if(document.Form1.hid_help_type.value==\"6\") {" ); 
			out.println("    document.Form1.TXT_CORE_APPLICANT_CODE.value =''; ");
			out.println("  }		"); 
			out.println("		else if(document.Form1.hid_help_type.value==\"8\") {" ); 
			out.println("    document.Form1.elements[\"TXT_ISSUER_CODE\"+document.Form1.hid_row.value].value =''; ");
			out.println("   }		"); 
			
			out.println("	 else	if(document.Form1.hid_help_type.value==\"3\") {" );  //added by nuwan de silva 27-06-07
			out.println("    document.Form1.TXT_INQUARY_NO.value =''; ");
			out.println("  }		"); 
			
			out.println("	 else	if(document.Form1.hid_help_type.value==\"9\") {" );  //added by nuwan de silva 27-06-07
			out.println("    document.Form1.TXT_LOCATION_CODE.value =''; ");
			out.println("  }		"); 
			//Commented by Dineth on 2009-01-16
			// Else If Block Uncommented By Samitha Kulatilaka On 2009-10-15
			out.println("	 else	if(document.Form1.hid_help_type.value==\"11\") {" );  //added by Chandana 29-11-07
			out.println("    document.Form1.INSURANCE_OFFICER.value =''; ");
			out.println("  }		");
			//Modified by Dineth on 2009-01-16
			out.println("	 else	if(document.Form1.hid_help_type.value==\"5\") {" );  //added by Chandana 29-11-07
			out.println("    document.Form1.MKT_OFFICER.value =''; ");
			out.println("  }		");
			
			out.println("	 else	if(document.Form1.hid_help_type.value==\"55\") {" );  //added by thamali 29-11-07
			out.println("    document.Form1.LEAD_SOURCE_CODE.value =''; ");
			out.println("    document.Form1.LEAD_SOURCE_NAME.value =''; ");
			out.println("  }		");
			
			
			//End by Dineth on 2009-01-16
			// Else If Block Added By Samitha Kulatilaka On 2009-10-15
			out.println("	 else	if(document.Form1.hid_help_type.value==\"80\") {" );
			//out.println("    document.Form1.TXT_GAURANTOR_CODE.value =''; ");
			out.println("  }		");
			out.println("}		"); 					
			
			
			out.println("function help_button_1() {"); 
			//out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			
			//out.println("    help_update('0','10','10','m_help_TXT_APPLICATION_NO','99','0');"); 
			out.println("    help_update('0','10','10','m_help_TXT_APPLICATION_NO_new','99','0');"); 
			
			out.println("    } ");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"EDIT\" || document.Form1.SCREEN_NAME.value==\"DACT\"  ){ ");
			
			//out.println("    alert('1');"); 
			out.println("    help_update('0','10','10','m_help_TXT_APPLICATION_NO_MOD','99','0');"); 
			out.println("    } ");
			out.println("    if( document.Form1.SCREEN_NAME.value==\"RACT\"){ ");
			out.println("    help_update('0','10','0','m_help_TXT_APPLICATION_NO_REA','99','0');"); 
			out.println("    } ");
			
			out.println("}"); 
			//out.println(""); 
			
		
			
			out.println("function help_button_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.MKT_OFFICER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 
			//credit  officer help
			out.println("function mk_officer_help(rowNo) {"); 
			out.println("    document.Form1.hid_help_type.value=\"5\";"); 
			//out.println("    document.Form1.hid_row_no.value=rowNo;"); 
			out.println("   Sql = \"MKOfficerSqlNew\";");//MKOfficerSqlNew Modified By Sandun 25-08-2008
			out.println("   Crit=document.Form1.MKT_OFFICER.value+\"@AF@\";");
			out.println("    HelpBox('1','10','0',Crit,Sql,'11','0');");
			out.println("}"); 
			
			out.println("function help_value_assign_2() {"); 
			out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 
			
			out.println("function mk_officer_assign(oBj){");
			out.println(" document.Form1.MKT_OFFICER.value =oBj.valout[2]");
			//out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
			out.println("}");	
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount,Max) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"ENTERED@\"+\"ENT_CON@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount,Max);"); 
			//out.println("    HelpBox('1','10','10',Crit,'m_help_TXT_APPLICATION_NO','99');"); 
			
			out.println("}"); 
			
			out.println(" function assign_help_status(obj){");
			//out.println(" alert('ok');");
			out.println(" document.Form1.hid_help_status.value =obj; ");
			out.println("if(document.Form1.SCREEN_NAME.value==\"NEW\"||document.Form1.SCREEN_NAME.value==\"EDIT\"||document.Form1.SCREEN_NAME.value==\"DACT\" ){");
			out.println("document.Form1.hid_st.value='Y';");
			out.println("}");
			out.println("if(document.Form1.SCREEN_NAME.value==\"RACT\" ){");
			out.println("document.Form1.hid_st.value='N';");
			out.println("}");
			out.println("}");
			
			
			out.println("function help_update_value_assign_99(oBj) {"); 
			out.println("    if(document.Form1.SCREEN_NAME.value!=\"NEW\"){ ");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.MKT_OFFICER.value=oBj.valout[4];");
			out.println("    document.Form1.hid_inqury.value=oBj.valout[10];"); 
			
			out.println("}");
			out.println("    if(document.Form1.SCREEN_NAME.value==\"NEW\"){ ");
			out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
			//out.println("    document.Form1.CONTRACT_TXT.value=oBj.valout[5];"); //ADDED MILINDA 2014-03-17
			out.println("    document.Form1.hid_inqury.value=oBj.valout[10];"); 
			out.println("    fin.innerHTML=oBj.valout[5]; ");
			//out.println("alert(oBj.valout[9]);");
			out.println("}");
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
			out.println("    m_sql = \"m_view_TXT_app_sql\";");
			out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 
			
			
			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_st' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_inqury' VALUE=\"\">");
			//out.println("<INPUT TYPE='Hidden' NAME='CONTRACT_TXT' VALUE=\"\">");//added milinda 2014-03-19
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Credit Officer</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center' ><input type=\"button\" style=\"visibility:hidden;\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center' ><input type=\"button\" style=\"visibility:hidden;\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Deactivate\");' onClick='load_screen_status(\"DACT\")' value=\"De-activate\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" style=\"visibility:hidden;\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");
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
			out.println("<td width='20%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application No *</DIV></td>"); 
			out.println("<td width='12%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='15' size='15' onblur=\"assignState('M1'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_button_1()\" > </td>"); //M_APPLICATION_PROCESS_APPLICATION_HELP m_help_TXT_APPLICATION_NO
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='CONTRACT_TXT' maxlength='50' size='10' ></td>");
			out.println("<TD ><DIV id='fin' STYLE=\"{color: black; font: bold 9pt arial;}\"> </DIV></TD> ");
			out.println("<td width='*%'></td>"); 
			out.println("</tr>");
			
			
			out.println("<tr >"); 
			
			out.println("<td width='30%' ><DIV id='DIV_MKT_OFFICER'  class=div_input>Credit Officer *</DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='MKT_OFFICER' maxlength='50' size='10' onblur=\"assignState('M_MKT_OFFC'),makeRequest(document.Form1.MKT_OFFICER)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"mk_officer_help('11')\"></td>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			//ADDED MILIINDA 2014-03-17 FOR VIEW CONTRACT NUMBER
			out.println("<tr >"); 
			
			//out.println("<td width='30%' ><DIV id='DIV_CONTRAC'  class=div_input>Contract Number *</DIV></td>"); 
			//out.println("<td width='40%' ><input class='txt_input' type='text' name='CONTRACT_TXT' maxlength='50' size='10' onblur=\"\">"); 
		///	out.println("<input class='but_input' type='button' name='BUT_CONDITION_OF_ASSET' value=\"Help\" onClick=\"\"></td>"); 
			out.println("</td>"); 
			
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("<tr >"); 
			
			
			
			out.println("</table>"); 
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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