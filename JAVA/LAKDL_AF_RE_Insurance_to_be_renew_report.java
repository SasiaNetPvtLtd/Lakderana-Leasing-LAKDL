

// CREATED BY Udara on 09-10-2012
// DISPLAY NAME Insurance To Be Renewed
//Edited by Minal for #14286 on 10-10-2014
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_RE_Insurance_to_be_renew_report extends javax.servlet.http.HttpServlet {
	
	
	/*
	Connection conn;
	Statement stmt;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	
	public ResultSet rs;
	public String m_chksql;
	ServletOutputStream out = null;
	*/
	
	public void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{ // synchronized
		
		Connection conn= null;
		Statement stmt= null;
		CallableStatement callstmt= null;
		java.text.NumberFormat nf= null,nf1= null;
		
		ResultSet rs= null;
		String m_chksql= null;
		ServletOutputStream out = null;
		
		try {
			
			//************************************************************	
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_header_name=m_sn_methods.header_name.trim();
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
			
			//ServletOutputStream out = res.getOutputStream();
			 out = res.getOutputStream();
			
			//String m_chksql=req.getParameter("chksql");
			 m_chksql=req.getParameter("chksql");
			
			stmt = conn.createStatement();
			
			if(m_chksql.equals("main_page")){			
			
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE> Insurance To Be Renewed </TITLE>"); 
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

					
					out.println("    var from_date     = document.Form1.TXT_AS_AT_DATE_DD.value+'-'+document.Form1.TXT_AS_AT_DATE_MM.value+'-'+document.Form1.TXT_AS_AT_DATE_YY.value;");
					out.println("    var to_date       = document.Form1.TXT_AS_AT_DATE_DD1.value+'-'+document.Form1.TXT_AS_AT_DATE_MM1.value+'-'+document.Form1.TXT_AS_AT_DATE_YY1.value;");
					out.println("    var m_ins_company = document.Form1.COM_NAME.value;  "); 
					//out.println("    var ins_officer = document.Form1.TXT_INSUR_CODE.value;  ");
					out.println("    var m_branch_code = document.Form1.TXT_BRANCH_CODE.value; ");
					out.println("    var m_business_type = document.Form1.TXT_BUSINESS_TYPE.value; ");
					out.println("    var m_insurence_type = document.Form1.TXT_INSURENCE_TYPE.value; ");
					//out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report?chksql=view_report_det&from_date=\"+from_date+\"&to_date=\"+to_date+\"&ins_officer=\"+ins_officer+\"&branch_code=\"+branch_code;");
					//out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report?chksql=view_report&from_date=\"+from_date+\"&to_date=\"+to_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type;");
					out.println("	 m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report?chksql=view_report&from_date=\"+from_date+\"&to_date=\"+to_date+\"&ins_company=\"+m_ins_company+\"&business_type=\"+m_business_type+\"&insu_type=\"+m_insurence_type+\"&branch_code=\"+m_branch_code;");
					out.println("    popupwin=window.open(m_url,'displayWindow1','left=70,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
					
					
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
					//out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report';"); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 
					out.println("function new_window(){	"); 
					
					//out.println("	window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report';");
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_to_be_renew_report?chksql=main_page';"); 
					out.println("}"); 
					
					
					out.println("function load_help_msg() {"); 
					out.println("    m_help_message = \"m_help_msg_LAKDL_AF_RE_Insurance_to_be_renew_report\";"); 
					out.println("    HelpBox_msg(m_help_message);"); 
					out.println("}"); 	
					
					out.println("function HelpBox_msg(m_help_message) {"); 
					out.println("		popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"FA_MAS_Help_Msg_select\"+"); 
					out.println("  \"&help_message_in=\"+m_help_message);"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){"); 
					out.println("	help_box.innerHTML=\" Insurance To Be Renewed - \"+m_val;"); 
					out.println("}"); 
					
					out.println("function load_roll_out_value(){");
					out.println("	help_box.innerHTML=\" Insurance To Be Renewed \";"); 
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
					out.println("		 popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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
					out.println("	if(IfCount=='99'){"); 
					out.println("		help_company_assign_99(oBj);"); 
					out.println("	}");
					out.println("	if(IfCount=='2'){"); 
					out.println("		assign_insurance_officer(oBj);"); 
					out.println("	}");
					out.println("	if(IfCount=='3'){"); 
					out.println("		branch_assign(oBj);"); 
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
			
					out.println("   if(document.Form1.hid_help_type.value==\"2\"){");
					out.println("     document.Form1.TXT_INSUR_CODE.value=\"\";"); 
					out.println("   }");
					
					out.println("   if(document.Form1.hid_help_type.value==\"3\"){");
					out.println("      document.Form1.TXT_BRANCH_CODE.value=\"\";"); 
					out.println("   }");
					
					out.println("   if(document.Form1.hid_help_type.value==\"99\"){");
					out.println("       document.Form1.COM_NAME.value=\"\";"); 
					//out.println("       document.Form1.COM_NAME_code.value=\"\";"); 
					out.println("   }");
					
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
					out.println("function help_company() {"); 
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    m_sql = \"m_help_insurance_company_code\";"); 
					out.println("    m_criteria = document.Form1.COM_NAME.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
					out.println("}"); 
					
				/*	out.println("function help_branch() {"); 
					out.println("    document.Form1.hid_help_type.value=\"99\";"); 
					out.println("    m_sql = \"m_help_insurance_company_code\";"); 
					out.println("    m_criteria = document.Form1.COM_NAME_1.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
					out.println("}"); */
					
					out.println("function help_company_assign_99() {");
					//out.println("    document.Form1.COM_NAME_code.value=oBj.valout[2];"); 
					out.println("    document.Form1.COM_NAME.value=oBj.valout[2];"); 
					out.println("}"); 					
					
					out.println("function help_ins_officer(){");
					out.println("    Crit = document.Form1.TXT_INSUR_CODE.value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_EMP_CODE_sql','2');"); 
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance To Be Renewed </td>"); 
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
					out.println("<TD WIDTH=\"40%\"><input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_DD1 maxlength=\"2\" size=\"2\" >");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_MM1  maxlength=\"2\" size=\"2\">");
					out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_AS_AT_DATE_YY1 maxlength=\"4\" size=\"4\" ><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a>  ");	
					out.println("</td> ");
					out.println("</tr> ");
					
					// added by udara 11-11-2014
					out.println("<tr>"); ; 
					out.println("<td  width='5%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input> Business Type </DIV></td>"); 
					out.println("<td ='40%' ><select name='TXT_BUSINESS_TYPE' class='txt_input' style=\"width:100px;\" >");
					out.println("<option value=\"ALL\" SELECTED >All</option>");
					out.println("<option value=\"NEW\" >New</option>");
					out.println("<option value=\"RENEW\" >Renewal</option>");				
					out.println("</select>");
					out.println("</td>");
					out.println("</tr>");
					// end by udara 11-11-2014
					
					
					out.println("<tr> ");
					out.println("<td width='7%' ><DIV id='DIV_COM_NAME'  class=div_input> Insurance Company</DIV></td>"); 
                	out.println("<td width='40%' ><input class='txt_input' type='text' name='COM_NAME' maxlength='10' size='50' style='width:100' >"); 
					out.println("<input class='but_input' type='button' name='BUT_HELP_COMPANY' value=\"Help\" onClick=\"help_company()\" > ");
					out.println("</td> ");
					out.println("<tr>"); 
		
					//Minal
					out.println("<tr>"); 
					out.println("<td  width='5%' ><DIV id='DIV_TXT_REAL_DATE1'  class=div_input> Insurence Done By </DIV></td>"); 
					out.println("<td ='40%' ><select name='TXT_INSURENCE_TYPE' class='txt_input' style=\"width:100px;\" >");
					out.println("<option value=\"ALL\" SELECTED >All</option>");
					out.println("<option value=\"CLIENT\" >Client</option>");
					out.println("<option value=\"LICENSEE\" >Company</option>");				
					out.println("</select>");
					out.println("</td>");
					out.println("</tr>");
					
					
					out.println("<tr> ");
					out.println("<td width='7%' ><DIV id='DIV_COM_NAME_1'  class=div_input>Branch Code</DIV></td>"); 
                	out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_BRANCH_CODE' maxlength='10' size='50' style='width:100' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BRANCH_CODE' value=\"Help\" onClick=\"help_branch()\" > ");
					
					//out.println("<td></td>");
					//out.println("<td></td>");

					//out.println("<td width='15%'>");
					
					out.println("<input class='but_input' type='button'  value=\"View Report\" onClick=\"show_report()\" style='{width=150px}'>");
					//out.println("<input class='but_input' type='button'  value=\"Run Report\"  style='{width=150px}'>");
			        out.println("</td>");
				
	
					out.println("<td width='*%' ></td>");
					out.println("</tr> ");
					out.println("</table>"); 
					//sanjeewa
					
					/*
					out.println("<table class='table' width='100%'  >"); 
					
					out.println("<tr>");
					out.println("<td width='15%'>Marketing Officer</td>"); 
					out.println("<td width='25%'><input type='text' name='TXT_INSUR_CODE' class='txt_input' style='width:100px' onblur='help_ins_officer()'>&nbsp;<input type='button' value='Help' name='BUT_INSURANCE_OFF' class='but_input' onclick='help_ins_officer();'></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td width='15%'>Branch</td>"); 
					out.println("<td width='20%'><input type='text' name='TXT_BRANCH_CODE' class='txt_input' style='width:100px' onblur='help_branch()'>&nbsp;<input type='button' value='Help' name='BUT_BRANCH_CODE' class='but_input' onclick='help_branch();'>");
			        out.println("<input type='button' value='View' name='BUT_VIEW' class='but_input' onclick='show_report();'> ");
			        out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");
					
					
					out.println("</table>"); 
					*/
					
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					//out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
			
			}
			else if(m_chksql.equals("view_report")){	

				String m_from_date=req.getParameter("from_date");
				String m_to_date=req.getParameter("to_date");
				String m_ins_company=req.getParameter("ins_company");
				String m_business_type=req.getParameter("business_type"); 
				String m_branch_code=req.getParameter("branch_code");
				String m_insu_type=req.getParameter("insu_type");
				
				if(m_business_type.equals("ALL"))
					m_business_type = "  ";
				else if(m_business_type.equals("NEW"))
					m_business_type = "AND BUSINESS_TYPE = 'NEW' ";
				else if(m_business_type.equals("RENEW"))
					m_business_type = "AND BUSINESS_TYPE = 'RENEWAL' ";
				
				
				if(m_insu_type.equals("ALL"))
					m_insu_type = "  ";
				else if(m_insu_type.equals("CLIENT"))
					m_insu_type = "AND INSURANCE_DONE_BY = 'CLIENT' ";
				else if(m_insu_type.equals("LICENSEE"))
					m_insu_type = "AND INSURANCE_DONE_BY = 'LICENSEE' ";

				
				out.println("<HTML><HEAD><TITLE>Insurance To Be Renewed </TITLE></HEAD>");
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
				
				out.println("</script>");
				out.println("<BODY class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
				out.println("<FORM NAME='Form1' method='post'>"); 							
				
				/*
				out.println("<TABLE  WIDTH='100%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B>Janashakthi renewals month of October</B></TD></TR>");
				out.println("</TABLE>");
				*/
				
				out.println("<TABLE  WIDTH='50%' class='factoring-letter-body'>");
				out.println("<TR><TD align='Center' class=factoring-letter-body><B> Insurance To Be Renewed </B></TD></TR>");
				out.println("</TABLE>");
				
				out.println("<br>");
				out.println("<table width='*%' class='table' border='1'  cellspacing='0' cellspacing='1' >");
				out.println("<tr class=factoring-letter-body bgcolor=\"#C0C0C0\">");
				out.println("<td width='10%'   align=center><DIV class=factoring-letter-body><b>No<DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Contract No</b></DIV></td>");
				out.println("<td width='8%'    align=center><DIV class=factoring-letter-body><b>Vehicle No </b></DIV></td>");
				out.println("<td width='12%'   align=center><DIV class=factoring-letter-body><b>Insured Name</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Policy No</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Debit Note No</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Expiry Date</b></DIV></td>");
				out.println("<td width='12.5%' align=center><DIV class=factoring-letter-body><b>Sum</b></DIV></td>");
			
				
				out.println("</tr>");
				// commented by udara 27-11-2014
				/*
				rs= stmt.executeQuery(" "+
						" SELECT FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(INSUR_COM), "+ // 3
							" POLICY_NO, "+ // 4
							" TO_CHAR(END_DATE,'DD-MM-YYYY'), "+ // 5
							" SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(FINANCE_NO)) "+ // 7
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA "+
								" WHERE INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND TRUNC(END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								"  "+m_business_type+"  "+
							" ");
				*/
				
				// added by udara 27-11-2014
				/*
				rs= stmt.executeQuery(" "+
						" SELECT A.FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)) "+ // 7
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
								" WHERE A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								"  "+m_business_type+"  "+
							" ");
				*/
				
				// commented by udara 19-12-2014
				/*
				rs= stmt.executeQuery(" "+
						" SELECT A.FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)), "+ // 7
							" A.DEBIT_NOTE_NO "+ // 8
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
								" WHERE A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								"  "+m_business_type+"  "+
								" and BUSINESS_TYPE = 'RENEWAL' "+
								
							" UNION  "+
								
							" SELECT A.FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)), "+ // 7
							" A.DEBIT_NOTE_NO "+ // 8
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
								" WHERE A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								"  "+m_business_type+"  "+
								" and BUSINESS_TYPE = 'NEW'   "+ 
       							" and (select count(finance_no) from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA where finance_no = a.finance_no and BUSINESS_TYPE = 'RENEWAL' ) < 1 "+

							" ");
				*/
				
				// commented by udara 19-01-2015
				/*
				// added by udara 19-12-2014
				rs= stmt.executeQuery(" "+
						" SELECT A.FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)), "+ // 7
							" A.DEBIT_NOTE_NO "+ // 8
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A "+
								" WHERE A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								//" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								" AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM  "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE FINANCE_NO = A.FINANCE_NO) "+ // added by udara 19-12-2014
								"  "+m_business_type+"  "+
							" ");
				// end by udara 19-12-2014
				*/
				
				
				// added by udara 19-01-2015
				rs= stmt.executeQuery(" "+
						" SELECT A.FINANCE_NO, "+ // 1
							" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)),'-'), "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)), "+ // 7
							" A.DEBIT_NOTE_NO "+ // 8
							" ,' ' POLICY_TYPE "+ //ADDED BY JITHENDRA 28-03-2019
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE  A.FINANCE_NO = B.FINANCE_NO "+
							    " AND B.APPLICATION_STATUS IN ( 'ACTIVATED','REPOSSESS')"+	
								" AND UPPER(A.BUSINESS_TYPE) IN('NEW','RENEWAL') "+
								" AND UPPER(B.INSURANCE_DONE_BY) IN('CLIENT','LICENSEE') "+
								" AND A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND B.BRANCH_CODE LIKE '"+m_branch_code+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								//" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								" AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM  "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA WHERE FINANCE_NO = A.FINANCE_NO) "+ // added by udara 19-12-2014
								"  "+m_business_type+"  "+
								"  "+m_insu_type+" "+
								
								"UNION ALL "+
								
								" SELECT A.FINANCE_NO, "+ // 1
							"  NVL((SELECT VEHICLE_NO FROM "+m_schema_name+".AF_MK_APP_SECURITY_VEHICLE WHERE SECURITY_ID = A.SECURITY_ID ),'-') VEHICLE_NO, "+ // 2
							" "+m_schema_name+".AF_CO_GET_SUB_CHARG_PAYEE_NAME(A.INSUR_COM), "+ // 3
							" A.POLICY_NO, "+ // 4
							" TO_CHAR(A.END_DATE,'DD-MM-YYYY'), "+ // 5
							" A.SUM_INSSURED, "+ // 6
							" "+m_schema_name+".AF_CO_GET_CLIENT_CODE("+m_schema_name+".AF_CO_GET_APPLICATION_NO(A.FINANCE_NO)), "+ // 7
							" A.DEBIT_NOTE_NO "+ // 8
							" ,'SECURITY' POLICY_TYPE "+  //ADDED BY JITHENDRA 28-03-2019
								" FROM "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
								" WHERE  A.FINANCE_NO = B.FINANCE_NO "+
							    " AND B.APPLICATION_STATUS IN ( 'ACTIVATED','REPOSSESS')"+	
								" AND UPPER(A.BUSINESS_TYPE) IN('NEW','RENEWAL') "+
								" AND UPPER(B.INSURANCE_DONE_BY) IN('CLIENT','LICENSEE') "+
								" AND A.INSUR_COM LIKE '"+m_ins_company+"%'  "+
								" AND B.BRANCH_CODE LIKE '"+m_branch_code+"%'  "+
								" AND TRUNC(A.END_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
								" AND TRUNC(A.END_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
								//" AND (A.POLICY_NO,A.DEBIT_NOTE_NO) NOT IN (SELECT  PREV_POLICY_NO,PREV_DEBIT_NOTE_NO FROM AF_IS_PRO_ASET_INSUR_DETA WHERE PREV_POLICY_NO IS NOT NULL AND PREV_DEBIT_NOTE_NO IS NOT NULL  ) "+
								" AND A.ENT_DATE IN (SELECT MAX(ENT_DATE) FROM  "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA_SEC WHERE FINANCE_NO = A.FINANCE_NO) "+ // added by udara 19-12-2014
								"  "+m_business_type+"  "+
								"  "+m_insu_type+" "+
							" ");
				
	
				// end by udara 19-01-2015
				
	
				int j=1;
				int count = 0;
				
				try{
				
					while(rs.next()){
						
						   count = count + 1;
						   if(j==0){
								out.println("<tr bgcolor=\"#C0C0C0\" >");
								j=1;
							}
							else{
								out.println("<tr bgcolor=\"#FFFFFF\" >");
								
								j=0;
							}
						
						if(rs.getString("POLICY_TYPE").equals("SECURITY")){
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'> "+count+" </td>");
						out.println("<td width='10%' class=factoring-letter-body style='cursor:hand;color:red' onClick=\"show_transaction_history_new('"+rs.getString(7)+"','"+rs.getString(1)+"')\">"+rs.getString(1)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'>"+rs.getString(2)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'>"+rs.getString(4)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'>"+rs.getString(8)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left' style='color:red'>"+rs.getString(5)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='right' style='color:red'>"+nf.format(rs.getDouble(6))+"</td>");
					}else{
					
						out.println("<td width='10%' class=factoring-letter-body align='left'> "+count+" </td>");
						out.println("<td width='10%' class=factoring-letter-body style=cursor:hand onClick=\"show_transaction_history_new('"+rs.getString(7)+"','"+rs.getString(1)+"')\">"+rs.getString(1)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(2)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(3)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(4)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(8)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='left'>"+rs.getString(5)+"</td>");
						out.println("<td width='10%' class=factoring-letter-body align='right'>"+nf.format(rs.getDouble(6))+"</td>");
					}
						out.println("</tr>");
					}

					
					
				
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







