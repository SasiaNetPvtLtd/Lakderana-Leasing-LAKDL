
//--
//SCREEN NAME:SYSTEM ADMINISTRATION - RETURN & REALIZATION
//CREATED BY:M.M. Wickramasekara
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CO_return_realization_display extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	public ResultSet rs,rs1;
	Statement stmt;
	Connection conn;
	java.text.NumberFormat nf;
	
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
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>System Administration - Documents Required</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 

			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_deposit' ){");
			out.println("			display_receipt(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_deposit' ){");
			out.println("     alert('No Records Available ')");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			
			out.println("			else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"DEL\" && document.Form1.hid_help_status.value == 'H_deposit_del' ){");
			out.println("			display_receipt(data_vec);");
			out.println("			}");
			
			out.println("			else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"DEL\" && document.Form1.hid_help_status.value == 'H_deposit_del' ){");
			out.println("     alert('No Records Available ')");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			
			out.println("		 else if(data_vec.length>0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_accno' ){");
			//out.println("				alert('Record already Exists.');");
			out.println("				assign_data(data_vec);");
			out.println("			}");
			
			out.println("		 else if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" && document.Form1.hid_help_status.value == 'H_accno' ){");
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("			}");
			
			/*out.println("    else if(data_vec.length > 0 && document.Form1.SCREEN_NAME.value!=\"NEW\" ){");
      out.println("    assign_data(data_vec);");
      out.println("    }");
      out.println("    else if(data_vec.length == 0 && document.Form1.SCREEN_NAME.value!=\"NEW\"&& document.Form1.TXT_CODE.value !=''&& document.Form1.hid_help_status.value == 'H9' ){");
			//out.println("    alert('Selected District code is incorrect,use help...!')");
			out.println("    help_update();");
      out.println("   }");*/
			
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_documents_required&data_val=\"+obj.value;");
			out.println(" document.Form1.hid_help_status.value ='H_deposit'; ");
			//out.println("    if(document.Form1.hid_help_status.value=='H_deposit' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization&data_val=\"+obj.value;");
			//out.println("  window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");
			
			out.println("function makeRequest3(obj) {");
			
			out.println(" real_dd = document.Form1.TXT_REAL_DATE_DD.value ");
			out.println(" real_mm = document.Form1.TXT_REAL_DATE_MM.value ");
			out.println(" real_yy = document.Form1.TXT_REAL_DATE_YY.value ");
			
			out.println(" real_date = real_dd+real_mm+real_yy; ");
			
			out.println(" real_dd_to = document.Form1.TXT_REAL_DATE_DD2.value ");
			out.println(" real_mm_to = document.Form1.TXT_REAL_DATE_MM2.value ");
			out.println(" real_yy_to = document.Form1.TXT_REAL_DATE_YY2.value ");
			
			out.println(" real_date_to = real_dd_to+real_mm_to+real_yy_to; ");
			
			out.println(" if(validate_date()) { ");
			//out.println(" alert('DAte To @@'+real_date_to ); ");
			out.println(" document.Form1.hid_help_status.value ='H_deposit_del'; ");
			//out.println("    if(document.Form1.hid_help_status.value=='H_deposit' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization_del&data_val=\"+obj.value+\"&data_val2=\"+real_date+\"&data_val3=\"+real_date_to;");
			//out.println("   window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println(" }");
			out.println("}");
			
			out.println("function validate_date_2(){");
			out.println(" m_line_no = document.Form1.hid_deposit_lineno.value ; ");
			//out.println(" alert('Line No @'+m_line_no);");
			out.println(" for(var i=0;i<m_line_no;i++){ ");
			out.println(" real_dd = \"TXT_REAL_DATE_DD\"+i ");
			out.println(" real_mm = \"TXT_REAL_DATE_MM\"+i ");
			out.println(" real_yy = \"TXT_REAL_DATE_YY\"+i ");
			
			out.println(" if( document.Form1.elements[real_dd].value !='' || document.Form1.elements[real_mm].value != '' || document.Form1.elements[real_yy].value != '' ) {");
			out.println("    if(!checkMonthLength(document.Form1.elements[real_dd],document.Form1.elements[real_mm],document.Form1.elements[real_yy])){  "); 
			out.println("     return false;"); 
			out.println("    }");
			out.println("  }");
			out.println(" }");
			out.println("      return true;"); 
			out.println("}");
			
		  out.println("function validate_date(){");
			out.println(" real_dd = document.Form1.TXT_REAL_DATE_DD.value ");
			out.println(" real_mm = document.Form1.TXT_REAL_DATE_MM.value ");
			out.println(" real_yy = document.Form1.TXT_REAL_DATE_YY.value ");
			
			out.println(" real_dd_to = document.Form1.TXT_REAL_DATE_DD2.value ");
			out.println(" real_mm_to = document.Form1.TXT_REAL_DATE_MM2.value ");
			out.println(" real_yy_to = document.Form1.TXT_REAL_DATE_YY2.value ");
			
			out.println(" if(real_dd != '' && real_mm !='' && real_yy !=''  ) { ");
			out.println("  if(!checkMonthLength(document.Form1.TXT_REAL_DATE_DD,document.Form1.TXT_REAL_DATE_MM,document.Form1.TXT_REAL_DATE_YY)){  "); 
			out.println("   return false;"); 
			out.println("  }");
			out.println(" else {");
			
			out.println(" 	 if(real_dd_to != '' && real_mm_to !='' && real_yy_to !=''  ) { ");
			out.println("  		  if(checkMonthLength(document.Form1.TXT_REAL_DATE_DD2,document.Form1.TXT_REAL_DATE_MM2,document.Form1.TXT_REAL_DATE_YY2)){  "); 
			out.println("   	  	return true;"); 
			out.println("  	  	}");
			out.println("       else "); 
			out.println("       return false; "); 
			out.println("    }");
			out.println(" 	 else { ");
			out.println("   	 alert('To Date cannot be null ')");
			out.println("   	 return false;"); 
			out.println(" 	 }");
			
			out.println("  }");
			out.println("  }");
			out.println(" else { ");
			out.println("   alert('From Date cannot be null ')");
			out.println("   return false;"); 
			out.println("  }");
			out.println(" }");
			
			out.println("function makeRequest2(obj) {");
			out.println(" document.Form1.hid_help_status.value ='H_accno'; ");
			out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_display_return_realization2&data_val=\"+obj.value;");
			//out.println("  window.open(m_url);");
			out.println("		load_interface(m_url,'XML');");
			out.println("}");

      out.println("   function assign_data(data_vec) { ");
			out.println("    document.Form1.TXT_ACC_NO.value=data_vec[0];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=data_vec[2];"); 
			out.println("    document.Form1.TXT_BANK_NAME.value=data_vec[3];"); 
			out.println("    makeRequest(document.Form1.TXT_ACC_NO);");
			out.println("}");
			
			out.println(" function assign_help_status(obj){");
      out.println(" document.Form1.hid_help_status.value =obj; ");
      out.println("}");
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_CODE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else if(document.Form1.TXT_DESCRIPTION.value==\"\"){  "); 
			out.println("DIV_TXT_DESCRIPTION.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 

			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function before_submit(){ "); 
			out.println("   m_status = document.Form1.hid_status.value ");
			out.println("   m_save_msg='Are you sure you want to Save ? ';"); 
			out.println("   if(m_status == \"Delete\"){ ");
			out.println("   m_save_msg = 'Are you sure you want to Delete ? '");
			out.println("   }"); 
			out.println("		if(validate_date_2()){");
			out.println("		if(confirm(m_save_msg)){ ");
			out.println("		for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("		document.Form1.elements[i].disabled=false;");
			out.println("		}");
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MAS_save_return_realization';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}");
			out.println("		}");
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_return_realization_display';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CO_return_realization_display';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 
			
			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("    document.Form1.TXT_DESCRIPTION.focus();"); 
			out.println("}");

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_CO_return_realization_display\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Return & Realization - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Return & Realization - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){ "); 
			out.println("new_window(); "); 
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=true;"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=true;"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else{");
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=true;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=true;"); 
			out.println("document.Form1.BUT_DETAILS.disabled=true;"); 
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.TXT_REAL_DATE_DD.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_DD2.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_MM2.disabled=false;"); 
			out.println("document.Form1.TXT_REAL_DATE_YY2.disabled=false;"); 
			out.println("document.Form1.BUT_DETAILS.disabled=false;");
			out.println("clear_all(); ");
			//out.println("document.Form1.TXT_DESCRIPTION.disabled=false;"); 
			out.println("document.Form1.hid_status.value=\"Delete\";");  
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

			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
		
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("	"); 
			//out.println("window.open('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'')"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("    clear_fields(); ");
			out.println("		} else ");
			
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			
			out.println("if(oBj.valout[1]=='Next')  {");
			out.println("Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
			out.println("}");
			out.println("else if  (oBj.valout[1]=='Prev') {");
			out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
			out.println("}		");
			out.println("else if(oBj.valout[1] == 'Close'){");
			/*out.println("if(oBj.valout[1]=='Next')  {");
				out.println("Next(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
				out.println("}");
				out.println("else if  (oBj.valout[1]=='Prev') {");
				out.println("Prev(oBj.valout[2],oBj.valout[3],Hid_No,Sql,IfCount);");
				out.println("}		");
				out.println("else if(oBj.valout[0] == 'Exit'){");*/
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
		
				
					
			out.println("	}"); 
			out.println("	}"); 
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			out.println("	}"); 
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); 
			out.println("	else{	"); 
			out.println("    clear_fields(); ");
			out.println("	}	"); 
			out.println("	}	"); 
			out.println("}"); 
			out.println(""); 

			
			
			out.println("function clear_fields() {");
			out.println("document.Form1.TXT_ACC_NO.value='';"); 
			out.println("document.Form1.TXT_BRANCH_NAME.value='';"); 
			out.println("document.Form1.TXT_BANK_NAME.value='';"); 
			out.println("e_deposit_details.innerHTML='' ");
			//out.println("document.Form1.TXT_DESCRIPTION.value='';"); 
			out.println("}");
			

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 
			
			out.println("function help_update(Start,End,Hid_No,Sql,IfCount) {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    Crit = document.Form1.TXT_ACC_NO.value+\"@Y@\";"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			
			out.println("function help_update_desc() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			out.println("    m_sql = \"m_help_TXT_CODE_DESC_sql_1\";"); 
			out.println("    m_criteria = document.Form1.TXT_DESCRIPTION.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_ACC_NO.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value=oBj.valout[4];"); 
			out.println("    document.Form1.TXT_BANK_NAME.value=oBj.valout[5];"); 
			out.println("    makeRequest(document.Form1.TXT_ACC_NO);");
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
			out.println("    m_sql = \"m_view_TXT_CODE_sql_1\";");
			out.println("    m_criteria = document.Form1.TXT_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpView('1',50,'0');"); 
			out.println("}"); 

			out.println("function change_val_return_status(row_no){")	;
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_return_status].checked==true && document.Form1.elements[m_chk_realize_status].checked==true ){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("document.Form1.elements[m_chk_realize_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_realize_status].value = 'off'; ");
			out.println("}else if(document.Form1.elements[m_chk_return_status].checked==true && document.Form1.elements[m_chk_realize_status].checked==false ){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("}");	
			out.println("else { ");
			out.println("document.Form1.elements[m_chk_return_status].value='off'");
			out.println("}");
			out.println("}");
			
			out.println("function change_val_realize_status(row_no){")	;
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("if(document.Form1.elements[m_chk_realize_status].checked==true && document.Form1.elements[m_chk_return_status].checked==true ){");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("document.Form1.elements[m_chk_return_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_return_status].value = 'off'; ");
			out.println("}else if(document.Form1.elements[m_chk_realize_status].checked==true && document.Form1.elements[m_chk_return_status].checked==false ){");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("}");	
			out.println("else { ");
			out.println("document.Form1.elements[m_chk_realize_status].value='off'");
			out.println("}");
			out.println("}");
			
			out.println("function clear_all() { ");
			out.println("    document.Form1.TXT_ACC_NO.value='';"); 
			out.println("    document.Form1.TXT_BRANCH_NAME.value='';"); 
			out.println("    document.Form1.TXT_BANK_NAME.value='';"); 
			out.println(" 	  e_deposit_details.innerHTML='' ");
			out.println("}");
			
			out.println("function load_details(lineno){");
			//out.println("alert('linnoe ** '+lineno)");
			out.println(" m_deposit_no = \"hid_deposit_no\"+lineno;");
			out.println(" m_receipt_no = \"hid_receipt_no\"+lineno;");
			out.println(" m_receipt_no_val = document.Form1.elements[m_receipt_no].value;");
			//out.println("alert('receipt no ** '+m_receipt_no_val)");
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CO_display_receipt_details?chksql=pop_receipt_details&REC_NO='+m_receipt_no_val;"); 
			out.println("window.open(m_url,'displayWindow3','left=80,top=200,width=900,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
		 	out.println("}");
			
			out.println("function change_status(row_no){")	;
			out.println("m_chk_realize_status=\"CHK_REALIZED_STATUS\"+row_no;");
			out.println("m_chk_return_status=\"CHK_RETURNED_STATUS\"+row_no;");
			out.println("flag_return = 0;  ");
			out.println("flag_realize = 0;  ");
			out.println("if(document.Form1.elements[m_chk_realize_status].checked == true   ){");
			//out.println("flag_return = 1 ");
			out.println("document.Form1.elements[m_chk_realize_status].value='on'");
			out.println("document.Form1.elements[m_chk_return_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_return_status].value = 'off'; ");
			out.println("}");
			
			out.println("if(document.Form1.elements[m_chk_return_status].checked==true){");
			out.println("document.Form1.elements[m_chk_return_status].value='on'");
			out.println("document.Form1.elements[m_chk_realize_status].checked = false; ");
			out.println("document.Form1.elements[m_chk_realize_status].value = 'off'; ");
			out.println("}");	
			out.println("}");
			
			out.println("function load_sys_date(count){ ");
			//out.println(" alert('count &&'+count); ");
			rs=stmt.executeQuery(" select to_char(sysdate,'dd-mm-yyyy') from dual ");											
			boolean more4=rs.next();
			while(more4){
			out.println(" m_sysdate = '"+rs.getString(1)+"'");			
			more4=rs.next();
			} 
			out.println(" for(var i=0;i<count;i++ ){ ");
			out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+i;");
			out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+i;");
			out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+i;");
			out.println("document.Form1.elements[m_real_dd].value = m_sysdate.substring(0,2)  ");
			out.println("document.Form1.elements[m_real_mm].value = m_sysdate.substring(3,5)  ");
			out.println("document.Form1.elements[m_real_yy].value = m_sysdate.substring(6,10)  ");
			out.println("}");
			
			out.println("}");

			out.println("function assign_date(count,date_val){ ");
			//out.println(" alert('date val @@'+date_val) ");
			out.println(" line_no = count-1; ");
			out.println("m_real_dd=\"TXT_REAL_DATE_DD\"+line_no;");
			out.println("m_real_mm=\"TXT_REAL_DATE_MM\"+line_no;");
			out.println("m_real_yy=\"TXT_REAL_DATE_YY\"+line_no;");
			out.println("document.Form1.elements[m_real_dd].value = date_val.substring(0,2)  ");
			out.println("document.Form1.elements[m_real_mm].value = date_val.substring(3,5)  ");
			out.println("document.Form1.elements[m_real_yy].value = date_val.substring(6,10)  ");
			out.println("}");
			
			
			out.println("function display_receipt(data_vec){");
			out.println(" var i=0; "); 
			out.println(" var line_no=0; "); 
			
			out.println("  e_deposit_details.innerHTML='' ");
			out.println("	 e_deposit_details.innerHTML='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println(" ' <tr></tr>'+");
			out.println("		'<TR><TD width=\"11%\"><B>Receipt No</B></TD> ' +");
			out.println("   '<TD width=\"11%\" ><B>Deposit No</B></TD> '+ ");
			out.println("   '<TD width=\"12%\" ><B>Transaction Date</B></TD>'+");
			out.println("   '<TD width=\"12%\"><B>Settlement Mode</B></TD>'+ ");
			out.println("   '<TD width=\"11%\"><B>Returned Status</B></TD>' +");
			out.println("   '<TD width=\"11%\"><B>Realized Status</B></TD>' +");
			out.println("   '<TD width=\"12%\"><B>Realized date</B></TD>' +");
			out.println("   '<TD width=\"8%\"><B>Cheque No</B></TD>' +");
			out.println("   '<TD width=\"*%\"><B>Amount</B></TD></TR>' +");
			out.println(" 	'</table>'");
			
			
			out.println("  while(i<data_vec.length ) {  ");	
			out.println(" m_realize_date = data_vec[i+5]; ");
			out.println(" m_receipt_no = '<td width=\"11%\">'+data_vec[i]+'<input type=hidden name=hid_receipt_no'+line_no+' value=\"'+data_vec[i]+'\" ></td>'");
			out.println(" m_deposit_no = '<TD WIDTH=\"11%\">'+data_vec[i+1]+'<input type=hidden name=hid_deposit_no'+line_no+' value=\"'+data_vec[i+1]+'\" ></td>';");		
			out.println(" m_trans_date = '<TD WIDTH=\"12%\" align=\"center\" >'+data_vec[i+2]+'</td>';");		
			out.println(" m_settle_mode = '<TD WIDTH=\"12%\">'+data_vec[i+3]+'</td>';");	
			
			out.println(" if(data_vec[i+4] == 'Y' ){ ");
		  out.println(" m_returned_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\"  ></td>';");		
			out.println(" m_realized_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_realize_status('+line_no+')\" checked ></td>';");			
			out.println(" } ");
			out.println(" else if(data_vec[i+4] == 'N' ) {");
			out.println(" m_realized_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\"  ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"on\" onclick=\"change_val_return_status('+line_no+')\" checked ></td>';");		
			out.println(" } ");
			out.println(" else {");
			out.println(" m_realized_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_REALIZED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_realize_status('+line_no+')\" ></td>';");			
			out.println(" m_returned_status = '<TD WIDTH=\"11%\"><INPUT TYPE=\"checkbox\" NAME=CHK_RETURNED_STATUS'+line_no+' VALUE=\"off\" onclick=\"change_val_return_status('+line_no+')\" ></td>';");		
			out.println(" } ");
			
			out.println(" m_realized_date = '<TD WIDTH=\"12%\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD'+line_no+' maxlength=\"2\" size=\"2\"  >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM'+line_no+' maxlength=\"2\" size=\"2\"  >'+");
			out.println("'<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY'+line_no+' maxlength=\"4\" size=\"4\"  ></TD>'");	
			out.println(" m_cheq_no = '<TD WIDTH=\"8%\" align=\"center\" >'+data_vec[i+6]+'</td>';");	
			out.println(" m_amount = '<TD WIDTH=\"6%\">'+data_vec[i+7]+'<input type=hidden name=hid_amount'+line_no+' value=\"'+data_vec[i+7]+'\" ></td>';");	
			out.println(" m_but_detail = '<td width=\"*%\" ><input class=\"but_input\" type=\"button\" name=BUT_DETAIL'+line_no+' value=\"Details\" onClick=\"load_details('+line_no+')\"  style=\"width: 40px\" ></td>'");
			
			out.println(" m_write_data = '<TR>'+m_receipt_no+m_deposit_no+m_trans_date+m_settle_mode+m_returned_status+m_realized_status+m_realized_date+m_cheq_no+m_amount+m_but_detail+'</TR>' ");
			
			out.println(" 		e_deposit_details.innerHTML+='<table  align=\"center\" border=\"0\" width=\"100%\" class=\"table\">'+");
			out.println(" 		m_write_data ");
			out.println(" 		'</table>'");
			out.println(" i=i+8; ");
			out.println(" line_no=line_no+1; ");
			out.println(" 		if(document.Form1.SCREEN_NAME.value == 'NEW'){ ");
			out.println(" 		load_sys_date(line_no); ");
			out.println(" 		}"); 
			out.println(" 		else {");
			out.println(" 		assign_date(line_no,m_realize_date); ");
			out.println("     m_realize_date = '' ");
			out.println(" 		}"); 
			out.println("	 }");
			out.println(" document.Form1.hid_deposit_lineno.value=line_no ; ");
			out.println("}"); 


			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock(),load_roll_value('New')\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_deposit_lineno' VALUE=\"\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>System Administration - Documents Required</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"View All\");'  onclick='View_all()' value=\"View All\"></td>");
			out.println("<td width='6%'></td>");  
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
			out.println("<tr>");
			out.println("<td colspan=4 ></td>"); 
			out.println("</tr>");
			
			out.println("<tr >"); 
			out.println("<td  width='20%' ><DIV id='DIV_TXT_ACC_NO'  class=div_input> Account No </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_ACC_NO' maxlength='10' size='10' onblur=\"makeRequest2(this)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update('1','10','0','m_help_TXT_ACCOUNT_NO_sql','99')\" ></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BRANCH_NAME'  class=div_input> Branch Name *</DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BRANCH_NAME' maxlength='100' size='100' onblur=\"\" ></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_BANK_NAME'  class=div_input> Bank Name </DIV></td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_BANK_NAME' maxlength='100' size='100' onblur=\"\" ></td>"); 
			out.println("<td width='20%'></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("<tr >"); 
			out.println("<td width='20%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input> Realized Date From </DIV></td>"); 
			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD maxlength=\"2\" size=\"2\" disabled >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM  maxlength=\"2\" size=\"2\" disabled >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY maxlength=\"4\" size=\"4\" disabled >");	
			out.println("</td> ");
			out.println("<td width='20%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input> Realized Date To </DIV></td>"); 
			out.println(" <TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_DD2 maxlength=\"2\" size=\"2\" disabled >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_MM2  maxlength=\"2\" size=\"2\" disabled >");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_REAL_DATE_YY2 maxlength=\"4\" size=\"4\" disabled >");	
			out.println("<input class='but_input' type='button' name='BUT_DETAILS' value=\"Details\" onClick=\"makeRequest3(document.Form1.TXT_ACC_NO)\" disabled ></td> ");
			//out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
		  out.println("<tr>" );
		  out.println("<td width=\"100%\"><DIV ID=e_deposit_details>  </DIV></td>");				
			out.println("</tr>" );
      out.println("</table>");
			
			out.println("<br>"); 
			out.println("<table align='center' width='100%'>"); 
			out.println("<tr>"); 
			out.println("<td width='100%' class='note'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
			out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
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
