

// CREATED BY Udara on 10-04-2013
// DISPLAY NAME List Of Debit Notes
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MISF_list_of_debit_notes_2 extends javax.servlet.http.HttpServlet {
	
	/*
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	
	public ResultSet rs;
	public String m_chksql;
	*/
	ServletOutputStream out = null;
	
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		Connection conn = null;
		Statement stmt = null;
		CallableStatement callstmt1 = null;
		java.text.NumberFormat nf = null, nf1 = null;
		
		ResultSet rs = null;
		//String m_chksql = null;
		//ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
			String m_username = m_sn_methods.username;
			//**************************************************************					
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);      
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			ServletOutputStream out = res.getOutputStream();
			
			String m_chksql=req.getParameter("chksql");
			
			stmt = conn.createStatement();
			
			if(m_chksql.equals("run_report")){ 
				
				String m_invoice_type = req.getParameter("invoice_type");
				String m_from_date = req.getParameter("from_date");
				String m_to_date = req.getParameter("to_date");
				String m_status = req.getParameter("status");
				String m_branch   = req.getParameter("branch");
				String m_ins_agent   = req.getParameter("ins_agent"); 
				
				try{
					
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MIS_SAVE_DEBIT_NOTE_RPT(:1,:2,:3,:4,:5,:6,:7);END;"); 
					callstmt1.setString(1,m_invoice_type);
					callstmt1.setString(2,m_from_date);
					callstmt1.setString(3,m_to_date);
					callstmt1.setString(4,m_status);
					callstmt1.setString(5,m_branch);
					callstmt1.setString(6,m_username); 
					callstmt1.setString(7,m_ins_agent);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			
			else if(m_chksql.equals("main_page")){
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>List Of Debit Notes </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">");
				out.println("var m_sav_msg='';");
				out.println("function get_vector(data_vec) {");
				out.println("	  if(data_vec.length>0 && document.Form1.hid_option.value==\"1\"){");
				out.println("			document.Form1.TXT_AS_AT_DATE_DD.value=data_vec[0];");
				out.println("			document.Form1.TXT_AS_AT_DATE_MM.value=data_vec[1];");
				out.println("			document.Form1.TXT_AS_AT_DATE_YY.value=data_vec[2];");
				out.println("			document.Form1.TXT_AS_AT_DATE_DD1.value=data_vec[0];");
				out.println("			document.Form1.TXT_AS_AT_DATE_MM1.value=data_vec[1];");
				out.println("			document.Form1.TXT_AS_AT_DATE_YY1.value=data_vec[2];");
				out.println("		}");
				out.println("}");
				
				out.println("function validate_date(){");
				out.println(" 	m_as_at_dd = document.Form1.TXT_AS_AT_DATE_DD.value ");
				out.println(" 	m_as_at_mm = document.Form1.TXT_AS_AT_DATE_MM.value ");
				out.println("		m_as_at_yy = document.Form1.TXT_AS_AT_DATE_YY.value ");
				
				out.println(" if(m_as_at_dd != '' && m_as_at_mm !='' && m_as_at_yy !=''  ) { ");
				out.println("    if(!checkMonthLength(document.Form1.TXT_AS_AT_DATE_DD,document.Form1.TXT_AS_AT_DATE_MM,document.Form1.TXT_AS_AT_DATE_YY)){  "); 
				out.println("     return false;"); 
				out.println("     }");
				out.println("    else {");
				out.println("      	  return true;"); 
				
				out.println("    }");
				out.println("  }");
				out.println(" else { ");
				out.println("   alert('From Date cannot be null ')");
				out.println("   return false;"); 
				out.println("  }");
				out.println(" }");
				
				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function show_report() {");
				
				out.println("    var invoice_type = document.Form1.TXT_INVOICE_TYPE.value; ");
				out.println("    var from_date = document.Form1.TXT_AS_AT_DATE_DD.value+'-'+document.Form1.TXT_AS_AT_DATE_MM.value+'-'+document.Form1.TXT_AS_AT_DATE_YY.value;");
				out.println("    var to_date   = document.Form1.TXT_AS_AT_DATE_DD1.value+'-'+document.Form1.TXT_AS_AT_DATE_MM1.value+'-'+document.Form1.TXT_AS_AT_DATE_YY1.value;");
				out.println("    var ins_officer = document.Form1.TXT_INSUR_CODE.value;  ");
				out.println("    var branch_code = document.Form1.TXT_BRANCH_CODE.value; ");
				out.println("    var req_type    = document.Form1.TXT_REQ_TYPE.value; "); 
				out.println("    var ins_agent_hid    = document.Form1.hid_TXT_INSUR_AGET.value; "); 
				//	out.println("   alert(document.Form1.TXT_INSUR_AGET_NAME.value)");
				out.println("    if(document.Form1.hid_TXT_INSUR_AGET.value==\"YES\"){  ");
				//out.println("   alert(document.Form1.hid_TXT_INSUR_AGET.value+'insu_agent')");
				out.println("    var ins_agent    = document.Form1.TXT_INSUR_AGET_NAME.value; "); 
				out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2?chksql=view_report_det&from_date=\"+from_date+\"&to_date=\"+to_date+\"&ins_officer=\"+ins_officer+\"&branch_code=\"+branch_code+\"&invoice_type=\"+invoice_type+\"&ins_agent=\"+ins_agent+\"&ins_agent_hid=\"+ins_agent_hid+\"&req_type=\"+req_type ;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				//out.println("    document.Form1.hid_TXT_INSUR_AGET.value=\"NO\";  ");
				out.println("    document.Form1.TXT_INSUR_AGET_NAME.value=\"\"; "); 
				out.println("    }");
				out.println("    else{");
				//out.println("   alert(document.Form1.hid_TXT_INSUR_AGET.value+'insu_agent')");
				//out.println("    var ins_agent    = \"\"; "); 
				out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2?chksql=view_report_det&from_date=\"+from_date+\"&to_date=\"+to_date+\"&ins_officer=\"+ins_officer+\"&branch_code=\"+branch_code+\"&invoice_type=\"+invoice_type+\"&ins_agent_hid=\"+ins_agent_hid+\"&req_type=\"+req_type;");
				out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
				out.println("}");
				out.println("}");
				
				
				out.println("function validate_data(){"); 
				out.println(" if(document.Form1.COM_NAME.value==\"\"){ ");
				out.println(" alert('Client Code cannot be empty'); ");
				out.println(" DIV_COM_NAME.style.color='red';");
				out.println(" return false;");
				out.println(" }else{ ");
				out.println("	return true;");
				out.println(" }");
				out.println("}"); 			
				
				
				
				
				out.println("function load_c_date(val) {");
				out.println("var date1='' ");
				out.println("var date2='' ");
				out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
				out.println("		v_date=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_date.length<2)");
				out.println("			v_date=0+v_date");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("			v_month=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_month.length<2)");
				out.println("			v_month=0+v_month");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.TXT_AS_AT_DATE_DD.value=v_date;");
				out.println("     document.Form1.TXT_AS_AT_DATE_MM.value=v_month;");
				out.println("     document.Form1.TXT_AS_AT_DATE_YY.value=val;");
				
				
				//out.println("			date1=v_date+'-'+v_month+'-'+val;");
				//out.println("			document.Form1.hid_from_date.value=date1");
				out.println("	}");
				out.println(" if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("		v_date=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_date.length<2)");
				out.println("			v_date=0+v_date");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("			v_month=val.substr(0,val.indexOf('-'));");
				out.println("		if(v_month.length<2)");
				out.println("			v_month=0+v_month");
				out.println("			val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("     document.Form1.TXT_AS_AT_DATE_DD1.value=v_date;");
				out.println("     document.Form1.TXT_AS_AT_DATE_MM1.value=v_month;");
				out.println("     document.Form1.TXT_AS_AT_DATE_YY1.value=val;");
				//out.println("			date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
				//out.println("			document.Form1.hid_to_date.value=date2");
				out.println(" }");
				out.println("}");
				
				
				out.println("function get_vector_normal(m_data){");
				out.println("		invoice_detail_data.innerHTML=m_data;");
				out.println("}");
				out.println("function load_lock(){	"); 
				//out.println("document.oncontextmenu=new Function(\"return false\");"); 
				out.println("}	"); 
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen ?\")){ "); 
				//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2';"); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2?chksql=main_page';"); 
				out.println("		}"); 
				out.println("}"); 
				out.println("function new_window(){	"); 
				
				//out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2';");
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2?chksql=main_page';"); 
				out.println("}"); 
				
				
				out.println("function load_help_msg() {"); 
				out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MISF_list_of_debit_notes_2\";"); 
				out.println("    HelpBox_msg(m_help_message);"); 
				out.println("}"); 	
				
				out.println("function HelpBox_msg(m_help_message) {"); 
				out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
				out.println("  \"&help_message_in=\"+m_help_message);"); 
				out.println("}"); 
				
				out.println("function load_roll_value(m_val){"); 
				out.println("	help_box.innerHTML=\" List Of Debit Notes - \"+m_val;"); 
				out.println("}"); 
				
				out.println("function load_roll_out_value(){");
				out.println("	help_box.innerHTML=\" List Of Debit Notes \";"); 
				out.println("}"); 
				
				out.println("function get_system_date() {");
				out.println("	  document.Form1.hid_option.value=\"1\";");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_sql_validations?chksql=get_sys_date\";");
				out.println("		load_interface(m_url,'XML');");
				out.println("}");
				
				out.println("function load_screen_status(m_val){"); 
				out.println("		if(m_val==\"NEW\"){"); 
				out.println("			new_window();"); 
				out.println("		}"); 
				out.println("		else if(m_val==\"HELP\"){"); 
				out.println("			load_help_msg();"); 
				out.println("		}"); 
				out.println("		document.Form1.SCREEN_NAME.value=m_val;"); 
				out.println("		if(m_val==\"NEW\"){");
				out.println("			document.Form1.hid_status.value=\"New\";"); 
				out.println("		}");
				out.println("		else if(m_val==\"EDIT\"){");  
				out.println("			document.Form1.hid_status.value=\"Edit\";");  
				out.println("		}");
				out.println("		else if(m_val==\"DACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Deactivate\";");  
				out.println("		}");
				out.println("		else if(m_val==\"RACT\"){");  
				out.println("			document.Form1.hid_status.value=\"Reactivate\";");  
				out.println("		}");
				out.println("		else{");  
				out.println("			document.Form1.hid_status.value=\"\";");  
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function get_display_msg(){"); 
				out.println("	if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
				out.println("		m_sav_msg=\"Save\";"); 
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");  
				out.println("		m_sav_msg=\"Modify\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"DACT\"){");  
				out.println("		m_sav_msg=\"Deactivate\";");  
				out.println("	}");
				out.println("	else if(document.Form1.SCREEN_NAME.value==\"RACT\"){");  
				out.println("		m_sav_msg=\"Reactivate\";");  
				out.println("	}");
				out.println("	else{");  
				out.println("		m_sav_msg=\"\";");  
				out.println("	}"); 
				out.println("}"); 
				
				out.println("function MyDialog(){"); 
				out.println("	this.valout   = new Array(10);"); 
				out.println("}"); 
				//-------------------------------------------------------------------**	
				out.println("function finance_assign(oBj){");			
				out.println("document.Form1.FIN_NO.value=oBj.valout[2];");			
				out.println("}");	
				
				
				//------------------------------------------------------------------------------------------------**
				out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
				out.println("    oBj = new MyDialog();"); 
				out.println("    oBj.valout[1]  = \" \";"); 
				out.println("    oBj.valout[2]  = \" \";"); 
				out.println("    oBj.valout[3]  = \" \";"); 
				out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");"); //m_help_TXT_INSU_AGENT_sql
				out.println("		if(oBj.valout[1] ==\" \"){"); 
				out.println("    clear_fields(); ");
				out.println("		} else ");
				out.println("	if(oBj.valout[1] !=\" \"){"); 
				out.println("	if(oBj.valout[1] !=\"Close\"){"); 
				out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
				out.println("	if(oBj.valout[1]!=\"Next\"){"); 
				out.println("	if(oBj.valout[1]=='Next')  {");
				
				
				out.println("		Next(oBj.valout[3],oBj.valout[4],Hid_No,Crit,Sql,IfCount);");
				out.println("	}");
				out.println("	else if  (oBj.valout[1]=='Prev') {");
				out.println("		Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);");
				out.println("	}		");
				out.println("	else if(oBj.valout[1] == 'Close'){");
				out.println("	}");
				out.println("	else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
				out.println("	if(IfCount=='2'){"); 
				out.println("		assign_insurance_officer(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='3'){"); 
				out.println("		branch_assign(oBj);"); 
				out.println("	}");
				out.println("	if(IfCount=='4'){"); 
				out.println("		insu_agent_assign(oBj);"); 
				out.println("	}");
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
				
				out.println("function clear_fields(){");			
				out.println("if(document.Form1.hid_help_type.value==\"2\"){");
				out.println(" document.Form1.TXT_INSUR_CODE.value=\"\";"); 
				out.println("}");
				out.println("if(document.Form1.hid_help_type.value==\"3\"){");
				out.println(" document.Form1.TXT_BRANCH_CODE.value=\"\";"); 
				out.println("}");
				out.println("}");
				
				out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
				out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
				out.println("}"); 
				out.println("");
				
				// ================================================================================
				
				out.println("function help_ins_officer(){");
				out.println("    Crit = document.Form1.TXT_INSUR_CODE.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_USERS_sql','2');"); // m_help_TXT_EMP_CODE_sql
				out.println("}");
				
				out.println("function help_ins_agent(){");
				out.println("    Crit = document.Form1.TXT_INSUR_AGET.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_INSU_AGENT_sql','4');"); // m_help_TXT_EMP_CODE_sql
				out.println("}");
				
				
				out.println("function assign_insurance_officer(){"); 
				out.println("document.Form1.TXT_INSUR_CODE.value=oBj.valout[2];");
				out.println("}");
				
				
				out.println("function help_branch(){");
				out.println("    Crit = document.Form1.TXT_BRANCH_CODE.value+\"@Y@\";"); 
				//out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','3');"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_BRANCH_sql','3');");
				out.println("}");
				
				
				out.println("function branch_assign(){"); 
				out.println("document.Form1.TXT_BRANCH_CODE.value=oBj.valout[2];");
				out.println("}");
				
				out.println("function insu_agent_assign(){"); 
				out.println("document.Form1.TXT_INSUR_AGET.value=oBj.valout[2];");
				out.println("document.Form1.TXT_INSUR_AGET_NAME.value=oBj.valout[3];");
				out.println("}");
				
				
				
				out.println("function Change_Type(obj){");
				//out.println(" alert(obj.value); ");
				
				out.println("  if(obj.value=='INSINV' || obj.value=='INSURANCE'){");
				out.println("document.Form1.hid_TXT_INSUR_AGET.value='YES';");
				out.println("m_writedata=''+");
				out.println("'<tr>'+");
				out.println("'<td width=\"15%\">Insurance Agent</td>'+ "); 
				out.println("'<td width=\"25%\"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"TXT_INSUR_AGET\" class=\"txt_input\" style=\"width:100px\" onblur=\"help_ins_agent()\">  '+ ");
				out.println("'<input type=\"button\" value=\"Help\" name=\"BUT_INSUR_AGET\" class=\"but_input\" onclick=\"help_ins_agent();\"></td> '+ "); 
				out.println("'<td width=\"*%\"></td> '+ "); 
				out.println("'</tr>'+");
				out.println("'';");
				out.println("e_mode.innerHTML=m_writedata;");
				out.println("   }else{");
				out.println("    document.Form1.hid_TXT_INSUR_AGET.value=\"NO\";  ");
				out.println("m_writedata='' ");
				out.println("e_mode.innerHTML=m_writedata;");
				
				out.println("   }");
				out.println("}");
				
				out.println("function run_report() {");
				
				out.println("		m_from_date = document.Form1.TXT_AS_AT_DATE_DD.value+'-'+document.Form1.TXT_AS_AT_DATE_MM.value+'-'+document.Form1.TXT_AS_AT_DATE_YY.value;");
				out.println("		m_to_date = document.Form1.TXT_AS_AT_DATE_DD1.value+'-'+document.Form1.TXT_AS_AT_DATE_MM1.value+'-'+document.Form1.TXT_AS_AT_DATE_YY1.value;");
				
				out.println(" 		m_invoice_type  = document.Form1.TXT_INVOICE_TYPE.value;");
				out.println(" 		m_status  = document.Form1.TXT_REQ_TYPE.value;");
				
				out.println("       m_debit_user = document.Form1.TXT_INSUR_CODE.value;"); 
				
				out.println("       m_branch  = document.Form1.TXT_BRANCH_CODE.value;"); 
				
				out.println(" 		m_ins_agent = '';"); 
				out.println("       if(document.Form1.TXT_INVOICE_TYPE.value=='INSINV' || document.Form1.TXT_INVOICE_TYPE.value=='INSURANCE'){");
				out.println(" 			m_ins_agent = document.Form1.TXT_INSUR_AGET.value;"); 
				out.println("       }");
				
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_list_of_debit_notes_2?chksql=run_report&invoice_type=\"+m_invoice_type+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&status=\"+m_status+\"&debit_user=\"+m_debit_user+\"&branch=\"+m_branch+\"&ins_agent=\"+m_ins_agent;"); 
				out.println("       set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			alert('Report is generated. Use View Report button to get the view.');"); // out.println("			alert('The report is generated. Use View Report button to get the view.');"); // added by udara 25-03-2014
				//out.println("			print_report2();"); // commented by udara 25-03-2014 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("       durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				
				//------------------------------------------------------------------------------------------------**
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"get_system_date();load_lock();\">"); 
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_option' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_TXT_INSUR_AGET' VALUE=\"No\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='TXT_INSUR_AGET_NAME' VALUE=\"\">"); 
				
				
				out.println("<table width='100%' class=table border='0' cellspacing='0' cellpadding='0'>"); 
				out.println("<tr>"); 
				out.println("<td width='8' valign='top'><img src='spacer.gif' width='8' height='8'></td>"); 
				out.println("<td class='border_wht' valign='top'> "); 
				out.println("<table class='table' width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'>"); 
				out.println("<tr> "); 
				out.println("<td height='30' class='pdn_mainHD'>"+m_header_name+"</td>"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>List Of Debit Notes </td>"); 
				out.println("</tr>"); 
				out.println("<tr>"); 
				out.println("<td  height='10px' class='pdn_txtpos'>"); 
				out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
				out.println("<tr><td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");  
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='10%' align='center'></td>");
				out.println("<td width='6%'></td>");  
				//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
				out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
				out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
				out.println("</table>");  
				out.println("</td></tr><tr>");  
				out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
				out.println("</tr><tr>");  
				out.println("<td class='pdn_txtpos' height='150' valign='top'>");  
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' >Invoice Type </td>"); 
				out.println("<td WIDTH=\"18%\"><SELECT  name=\"TXT_INVOICE_TYPE\" class=\"txt_input\"   style=\"{width:150px;}\" onChange=\"Change_Type(this)\" > "); // onChange=\"Change_Type(this)\" 
				
				out.println("<OPTION value=\"\"> ALL </OPTION>");
				rs = stmt.executeQuery (" SELECT  SUB_TYPE_CODE,DESCRIPTION "+
					" FROM "+m_schema_name+".AF_CO_MAS_SUB_CHARGES "+
					" WHERE ACTIVE_STATUS='Y' ");
				
				while(rs.next()){
					out.println("<OPTION value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</OPTION>");
				}
				
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>");  
				
				
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE'  class=div_input>From Date</DIV></td>"); 
				out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM  maxlength=\"2\" size=\"2\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a>  ");	
				out.println("</td> ");
				out.println("<td width='*%' ></td>");
				out.println("</tr >");
				out.println("</table>");  
				
				
				//sanjeewa
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >"); 
				out.println("<td width='15%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input>To Date</DIV></td>"); 
				out.println("<TD WIDTH=\"18%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD1 maxlength=\"2\" size=\"2\" >");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM1  maxlength=\"2\" size=\"2\">");
				out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY1 maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>  ");	
				out.println("</td> ");
				
				out.println("<td width='*%' ></td>");
				out.println("</table>"); 
				//sanjeewa
				
				
				// added by udara on 09-08-2013
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr>"); 
				out.println("<td width='15%' > Debit Note Status </td>"); 
				out.println("<td WIDTH=\"18%\"><SELECT  name=\"TXT_REQ_TYPE\" class=\"txt_input\"   style=\"{width:150px;}\"> "); // onChange=\"Change_Type(this)\" 
				
				out.println("<OPTION value=\"NON_CANCEL\"> Not Cancelled </OPTION>");
				out.println("<OPTION value=\"CANCEL\"> Cancelled </OPTION>");
				
				out.println("</td>");
				out.println("<td width='*%'></td>"); 
				out.println("</tr>"); 
				out.println("</table>");  
				// end by udara on 09-08-2013
				
				
				out.println("<table class='table' width='100%'  >"); 
				
				out.println("<tr>");
				out.println("<td width='15%'>Entered User</td>"); 
				out.println("<td width='25%'><input type='text' name='TXT_INSUR_CODE' class='txt_input' style='width:100px' onblur='help_ins_officer()'>&nbsp;<input type='button' value='Help' name='BUT_INSURANCE_OFF' class='but_input' onclick='help_ins_officer();'></td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<table class='table' width='100%'  >"); 
				out.println("<tr >" );
				out.println("<td clospan=2 width=\"100%\"><DIV ID=e_mode></DIV></td> ");				
				out.println("</tr>");
				out.println("</table>");
				
				
				out.println("<table class='table' width='100%'  >"); 
				
				out.println("<tr>");
				out.println("<td width='15%'>Branch</td>"); 
				out.println("<td width='30%'><input type='text' name='TXT_BRANCH_CODE' class='txt_input' style='width:100px' onblur='help_branch()'>&nbsp;<input type='button' value='Help' name='BUT_BRANCH_CODE' class='but_input' onclick='help_branch();'>");
				out.println("<input type='button' value='View' name='BUT_VIEW' class='but_input' onclick='show_report();'> ");
				out.println("<input type='button' value='Run' name='BUT_RUN' class='but_input' onclick='run_report();'> ");
				out.println("</td>"); 
				out.println("<td width='*%'></td>"); 
				out.println("</tr>");
				
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
				
				
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
				//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
				out.println("</body>"); 
				out.println("</html>"); 
				
			}
			else{
				
				String m_invoice_type=req.getParameter("invoice_type");
				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_ins_officer=req.getParameter("ins_officer");
				String m_branch_code=req.getParameter("branch_code");
				String m_ins_agent=req.getParameter("ins_agent");
				String m_ins_agent_hid=req.getParameter("ins_agent_hid");
				
				
				if(m_ins_agent==null){
					m_ins_agent="";
				}
				
				String m_req_type=req.getParameter("req_type"); // added by udara on 09-08-2013
				
				// added by udara on 29-07-2013
				String m_ins_type_check = "";
				
				if(m_invoice_type.equals("INSURANCE"))
					m_ins_type_check = " WHERE ( A.INVOICE_TYPE = 'INSURANCE' OR A.REMARKS = 'CHARGES - INSURANCE') ";
				else
					m_ins_type_check = " WHERE DECODE(A.INVOICE_TYPE,'INV_OTHER',SUBSTR(A.REMARKS,11),A.INVOICE_TYPE) LIKE '%"+m_invoice_type+"' "; // added by udara 18-04-2017
				//m_ins_type_check = " WHERE DECODE(A.INVOICE_TYPE,'INV_OTHER',SUBSTR(A.REMARKS,11),A.INVOICE_TYPE) LIKE '%"+m_invoice_type+"%' ";
				//m_ins_type_check = " WHERE A.INVOICE_TYPE LIKE '%"+m_invoice_type+"%' "; // commented by udara 07-08-2015
				
				// added by udara on 09-08-2013
				String req_type_check = "";
				
				if (m_req_type.equals("NON_CANCEL"))
					req_type_check = " AND ACTIVE_STATUS NOT IN ('C','DB_CAN')  ";
				else
					req_type_check = " AND ACTIVE_STATUS IN ('DB_CAN')  ";
				
				
				
				// added by udara on 23-08-2013
				
				String m_date_range_check = "";
				String userFilter = "";//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
				String branchFilter = "";//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
				
				if (m_req_type.equals("NON_CANCEL")){
					m_date_range_check = ""+
						" AND   TRUNC(A.VALUE_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND   TRUNC(A.VALUE_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
				}
				else{
					m_date_range_check = ""+
						" AND   TRUNC(NVL(A.MOD_DATE,A.ENT_DATE)) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+ 
						" AND   TRUNC(NVL(A.MOD_DATE,A.ENT_DATE)) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') ";
				}
				//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
				if(!m_ins_officer.equals("")){
					userFilter=""+
						" AND  A.DEBIT_USER = '"+m_ins_officer+"' ";
				}
				//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
				if(!m_branch_code.equals("")){
					branchFilter=""+
						" AND A.BRANCH_COD = '"+m_branch_code+"' ";
					
				}
				
				
				
				
				out.println("<HTML><HEAD><TITLE>Insurance Invoices Report </TITLE></HEAD>");
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_insurance_details(val,val1){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_Ledger?chksql=SHOW_INSURANCE_BY_CONTRACT&date='+val1+'&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function finance_drill(finance,client){ ");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+client+\"&finance_no=\"+finance+\"\";");
				out.println("window.open(m_url,'displayWindow12','left=110,top=90,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');");
				out.println("}");
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				// added by udara on 21-08-2013
				out.println("	function show_transaction_info(m_client_code,m_finance_no){");
				out.println("    m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&client_code=\"+m_client_code+\"&finance_no=\"+m_finance_no+\"\";");
				out.println("    window.open(m_url); ");
				out.println("	}");
				
				
				out.println("</script>");
				
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				// ADDED BY SAJITH MENDIS ON 04/09/2013
				String invoice_name = "";
				rs = stmt.executeQuery (" SELECT  "+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC('"+m_invoice_type+"') "+
					" FROM DUAL ");
				
				while(rs.next()){
					invoice_name = rs.getString(1);
				}
				
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				// ADDED BY SAJITH MENDIS ON 04/09/2013
				if(invoice_name == null){
					out.println("<TR><TD align='Center' class=factoring-letter-body><B>All Invoices As At "+m_to_date+" </B></TD></TR>"); 
				}
				else{
					out.println("<TR><TD align='Center' class=factoring-letter-body><B>"+invoice_name+" Report As At "+m_to_date+"  </B></TD></TR>"); // ADDED BY SAJITH MENDIS ON 04/09/2013
				}
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table width='*%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Invoice No<DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Invoice Type<DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Finance No<DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Value date</b></DIV></td>");				
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Due Date</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Total Amount</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Balance To Be Received</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Remarks</b></DIV></td>");
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Entered User</b></DIV></td>");
				
				if(m_ins_agent_hid.equals("YES")){
					//if(document.Form1.hid_TXT_INSUR_AGET.value=="YES"){ 	
					out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Insurance Agent</b></DIV></td>");
				}
				
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>No. Of Rentals</b></DIV></td>"); // added by udara 26-02-2014
				out.println("<td width='10%' align=center><DIV class=factoring-letter-body><b>Rental Date</b></DIV></td>"); // added by udara 26-02-2014
				
				out.println("</tr>");
				
				/*
				rs= stmt.executeQuery(" "+
								//out.println(" "+
								" SELECT "+
								" A.INVOICE_NO, "+
								" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(A.INVOICE_TYPE),A.INVOICE_TYPE), "+
								" A.FINANCE_NO, "+
								" TO_CHAR(A.VALUE_DATE,'DD-MM-YYYY'), "+
								" TO_CHAR(A.DUE_DATE,'DD-MM-YYYY'), "+
								" A.TOTAL_AMOUNT, "+
								" A.BALANCE_TO_BE_RECEIVED, "+

								" NVL(DECODE(A.INVOICE_TYPE,'INV_OTHER',"+m_schema_name+".AF_CO_GET_SUB_CHARG_DESC(SUBSTR(A.REMARKS,11)),A.REMARKS),'-'), "+
								// added by udara 05-08-2015
								
								" A.ENT_USER, "+
								" A.CLIENT_CODE, "+ // 10 added by udara on 21-08-2013
								" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME("+m_schema_name+".FA_GET_INSURANCE_RECEVER("+m_schema_name+".FA_GET_INVOICE_NO_INSURANCE(A.FINANCE_NO))),'-') "+
								
								// added by udara 26-02-2014
								" ,"+m_schema_name+".AF_CO_GET_TOT_NO_RENTALS_2(A.FINANCE_NO,TO_CHAR(SYSDATE,'DD-MM-YYYY')) NO_OF_RENTALS "+ // 12 
								
								" ,( "+
								" SELECT TO_CHAR(MAX(RENTAL_DATE),'DD') "+
								" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
								" WHERE APPLICATION_NO = B.APPLICATION_NO "+
								"  ) RENTAL_DATE "+ // 13
								// end by udara 26-02-2014
								
								//" NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(C.RECEIVER),'-')"+ //added by ishani 2014-01-08
								" FROM "+m_schema_name+".AF_CO_PRO_INVOICE A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								"  "+m_ins_type_check+" "+ // added by udara on 29-07-2013
								" AND  A.FINANCE_NO = B.FINANCE_NO   "+  //AND C.REF_NO=A.INVOICE_NO added by  ishani 2014-01-08
								" AND  A.ENT_USER LIKE '"+m_ins_officer+"%'  "+
								" " + m_date_range_check + " "+
								" AND   NVL("+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME("+m_schema_name+".FA_GET_INSURANCE_RECEVER("+m_schema_name+".FA_GET_INVOICE_NO_INSURANCE(A.FINANCE_NO))), ' ') LIKE '"+m_ins_agent+"%' "+ //added by ishani 2014-01-08//" AND "+m_schema_name+".AF_CO_GET_APP_LOCATION(A.INVOICE_NO) LIKE '"+m_branch_code+"%' "+ // added by udara on 14-06-2013
								" AND B.BRANCH_CODE LIKE '"+m_branch_code+"%' "+
								" AND A.TOTAL_AMOUNT <> 0 "+
								//" AND ACTIVE_STATUS NOT IN ('C','DB_CAN')  "+ // added by udara on 03-07-2013
								"  "+req_type_check+" "+ 
								"  ORDER BY A.ENT_DATE "+ // added by udara on 22-08-2013
								*/
				
				
				rs= stmt.executeQuery(" "+
					//out.println(" "+
					" SELECT "+
					" A.APPLICATION_NO, "+
					" A.INVOICE_NO INVOICE_NO, "+
					" A.INVOICE_TYPE INVOICE_TYPE, "+
					" A.FINANCE_NO FINANCE_NO, "+
					" A.VALUE_DATE VALUE_DATE, "+
					" A.DUE_DATE DUE_DATE, "+
					" A.TOTAL_AMOUNT TOTAL_AMOUNT, "+
					" A.BALANCE_TO_BE_RECEIVED BALANCE_TO_BE_RECEIVED, "+
					" A.REMARKS, "+
					" A.DEBIT_USER, "+
					" A.NO_OF_RENTALS NO_OF_RENTALS, "+
					" A.RENTAL_DATE RENTAL_DATE, "+
					" A.BRANCH_CODE, "+
					" A.PAYEE_CODE PAYEE_CODE, "+
					" A.CLIENT_CODE CLIENT_CODE "+
					" FROM "+m_schema_name+".AF_TBL_DEBIT_NOTE_RPT A "+
					"  "+m_ins_type_check+" "+ 
					" "+userFilter+" "+//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
					" "+branchFilter+" "+//[ADDED BY MILINDA ON 20-09-2022 JB16092022-18349]
					" AND A.ENT_USER = '"+m_username+"' "+
					" ");
				
				
				
				int j=1;
				
				double tot_tot_amnt = 0; // added by udara on 08-10-2013
				double tot_bal_to_be_rec_amnt = 0; // added by udara on 08-10-2013
				
				try{
					
					
					while(rs.next()){
						
						tot_tot_amnt = tot_tot_amnt + rs.getDouble("TOTAL_AMOUNT"); // added by udara on 08-10-2013
						tot_bal_to_be_rec_amnt = tot_bal_to_be_rec_amnt + rs.getDouble("BALANCE_TO_BE_RECEIVED"); // added by udara on 08-10-2013
						
						
						if(j==0){
							out.println("<tr bgcolor=\"#C0C0C0\" >");
							j=1;
						}
						else{
							out.println("<tr bgcolor=\"#FFFFFF\">");
							
							j=0;
						}
						
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("INVOICE_NO")+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("INVOICE_TYPE")+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' class=div_input style='cursor:hand' onclick=\"show_transaction_info('"+rs.getString("CLIENT_CODE")+"','"+rs.getString("FINANCE_NO")+"')\" ><u>"+rs.getString("FINANCE_NO")+"</u></td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("VALUE_DATE")+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("VALUE_DATE")+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='right'>"+nf.format(rs.getDouble("TOTAL_AMOUNT"))+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='right'>"+nf.format(rs.getDouble("BALANCE_TO_BE_RECEIVED"))+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("REMARKS")+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("DEBIT_USER")+"</td>");
						if(m_ins_agent_hid.equals("YES")){
							out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("PAYEE_CODE")+"</td>");
						}
						
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("NO_OF_RENTALS")+"</td>"); // added by udara 26-02-2014
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString("RENTAL_DATE")+"</td>"); // added by udara 26-02-2014
						
						
						out.println("</tr>");
					}
					
					// added by udara on 08-10-2013
					out.println("<tr>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' ><b>  Total </b></td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					out.println("<td width='10%' class=factoring-letter-body align='right'><b>"+nf.format(tot_tot_amnt)+"</b></td>");
					out.println("<td width='10%' class=factoring-letter-body align='right'><b>"+nf.format(tot_bal_to_be_rec_amnt)+"</b></td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>");
					
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>"); // added by udara 26-02-2014
					out.println("<td width='10%' class=factoring-letter-body align='left' >  &nbsp; </td>"); // added by udara 26-02-2014
					
					out.println("</tr>");
					// end by udara on 08-10-2013
					
					
				}
				catch(Exception eee){
					out.println(eee.toString());
				}
				
				
				out.println("</table>");
				out.println("</form>"); 
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</BODY></HTML>");
				
			}
			
			
			out.flush();
			
			
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			
			
			
			
		}
		catch (Exception e) {
			try{out.println(e.toString());}catch(Exception e1){}
			//return null;
		}finally{
			if(rs    !=null){try{rs.close();   }catch(Exception e){}}
			if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
			if(conn  !=null){try{conn.close(); }catch(Exception e){}}
			if(out   !=null){try{out.close();  }catch(Exception e){}}
			//try{conn.setAutoCommit(true);
		}
	}
}







