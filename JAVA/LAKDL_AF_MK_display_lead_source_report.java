//--
//SCREEN NAME	:Marketing - LEAD SOURCE REPORT
//CREATED BY	:delanjali	
//DATE/TIME		:07-02-2007
//NOTES				:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_MK_display_lead_source_report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	String m_chksql;
	Connection conn;
	Statement stmt,stmt1;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1;

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			conn = m_sn_methods.met_user_validate(req); 

			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			stmt=conn.createStatement();
			stmt1=conn.createStatement();

			String m_schema_name = m_sn_methods.schema_name;
	
			m_chksql=req.getParameter("chksql");
		
		 if(m_chksql.equals("main_page")){
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Marketing - Lead Source Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var m_from_date='' ");
			out.println("var m_to_date='' ");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.hid_assig.value ==\"J1\" && document.Form1.TXT_LEAD_CODE.value!=\"\" && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("help_update();");
			out.println("			}");
			out.println("			if(data_vec.length==0 && document.Form1.hid_assig.value ==\"J2\"  && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			
			//out.println("alert('No records to view');");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_display_lead_source_report?chksql=lead_details&lead_source=\"+document.Form1.TXT_LEAD_CODE.value;");
			//out.println("popupwin=window.close(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("			}");
			out.println("	else if(data_vec.length>0 && document.Form1.hid_assig.value ==\"J2\"  && document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_from_date=document.Form1.TXT_FROM_DATE_DD.value+'-'+document.Form1.TXT_FROM_DATE_MM.value+'-'+document.Form1.TXT_FROM_DATE_YY.value");

			out.println("m_to_date=document.Form1.TXT_TO_DATE_DD.value+'-'+document.Form1.TXT_TO_DATE_MM.value+'-'+document.Form1.TXT_TO_DATE_YY.value");
			out.println("if(document.Form1.TXT_FROM_DATE_DD.value==\"\" || document.Form1.TXT_FROM_DATE_MM.value==\"\" || document.Form1.TXT_FROM_DATE_YY.value==\"\"){");
			out.println("m_from_date=\"\"");
			out.println("}");
			
			out.println("if(document.Form1.TXT_TO_DATE_DD.value==\"\" || document.Form1.TXT_TO_DATE_MM.value==\"\" || document.Form1.TXT_TO_DATE_YY.value==\"\"){");
			out.println("m_to_date=\"\"");
			out.println("}");

			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_display_lead_source_report?chksql=lead_details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&lead_name=\"+document.Form1.TXT_NAME.value+\"&lead_source=\"+document.Form1.TXT_LEAD_CODE.value;");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=100,top=100,width=890,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("			}");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_assig.value ==\"J1\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_lead_source_report&data_val=\"+obj.value;");
			out.println("}");
			out.println("if(document.Form1.hid_assig.value ==\"J2\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MK_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_lead_source_report_data&data_val=\"+obj.value;");
			out.println("}");
			out.println("load_interface(m_url,'XML');");
			out.println("}");

			out.println("function assig_text(val){"); 
			out.println("document.Form1.hid_assig.value=val");
			out.println(" makeRequest(document.Form1.TXT_LEAD_CODE) ");
			out.println("}");

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_LEAD_CODE.value==\"\"){  "); 
			out.println("DIV_TXT_LEAD_CODE.style.color='red';");
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
			out.println("		if(confirm(\"Are You Sure?\")){ "); 
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_MK_save_lead_source_report';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			//out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_lead_source_report?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MK_display_lead_source_report?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MK_display_lead_source_report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Marketing - Lead Source Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Marketing - Lead Source Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.BUT_HELP_MAIN.disabled=false;"); 
			out.println("document.Form1.TXT_DESCRIPTION.disabled=true;"); 

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
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MK_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
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
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("		help_value_assign_2();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			
			
			out.println("		help_value_assign_3(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
			
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("		help_value_assign_4();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"5\"){"); 
			out.println("		help_value_assign_5(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("		help_value_assign_6();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"7\"){"); 
			out.println("		help_value_assign_7();"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"8\"){"); 
			out.println("		help_value_assign_8(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"9\"){"); 
			out.println("		help_value_assign_9(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
	  	out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"10\"){"); 
			out.println("		help_value_assign_10(document.Form1.hid_row1_no.value,document.Form1.hid_row_no.value);"); 
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
			out.println("document.Form1.TXT_LEAD_CODE.value=\"\";"); 
			out.println("document.Form1.TXT_LEAD_CODE.focus();"); 
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");//**
			out.println("document.Form1.TXT_LEAD_CODE.value=\"\";"); 
			out.println("document.Form1.TXT_LEAD_CODE.focus();"); 
			out.println("	}	"); 
			out.println("}"); 
			
			

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
			out.println("    m_sql = \"m_help_TXT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LEAD_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 
			out.println(""); 

			out.println("function help_value_assign_1() {"); 
			out.println("    document.Form1.TXT_LEAD_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function help_update() {"); 
			out.println("    document.Form1.hid_help_type.value=\"99\";"); 
			out.println("    m_sql = \"m_help_TXT_LEAD_SOURCE_REPORT_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LEAD_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}"); 

			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LEAD_CODE.value=oBj.valout[3];"); 
			out.println("    document.Form1.TXT_NAME.value=oBj.valout[2];"); 
			out.println("}"); 

			out.println("function view() {"); 
			out.println(" assig_text('J2')"); 
			out.println("}");

			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
			out.println("}");
			
					
			out.println("function load_c_date(val) {");
      out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_TO_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_TO_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_TO_DATE_YY.value=v_yy;");
			out.println("  }");		
		  out.println("  else if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("  v_dd = val.substr(0,val.indexOf('-'))");
			out.println("   if(v_dd.length <2) ");
			out.println("   v_dd = 0+v_dd ");
			out.println("  val = val.substr(val.indexOf('-')+1,val.length);");
			out.println("  v_mm = val.substr(0,val.indexOf('-')); ");
			out.println("   if(v_mm.length <2) ");
			out.println("   v_mm = 0+v_mm ");
			out.println("  v_yy = val.substr(val.indexOf('-')+1,val.length)");
			out.println("     document.Form1.TXT_FROM_DATE_DD.value=v_dd;");
			out.println("     document.Form1.TXT_FROM_DATE_MM.value=v_mm;");
			out.println("     document.Form1.TXT_FROM_DATE_YY.value=v_yy;");
			out.println("  }");	
			out.println(" if((document.Form1.TXT_FROM_DATE_DD.value !=\"\" || document.Form1.TXT_FROM_DATE_MM.value !=\"\"|| document.Form1.TXT_FROM_DATE_YY.value !=\"\") && (document.Form1.TXT_TO_DATE_DD.value !=\"\" || document.Form1.TXT_TO_DATE_MM.value !=\"\" || document.Form1.TXT_TO_DATE_YY.value !=\"\")){");
			out.println("chk_validity()");
			out.println("}");	
			out.println("}");				

			
			out.println("function check_date_from(){ ");
			out.println("  if(document.Form1.TXT_FROM_DATE_DD.value!=\"\" && document.Form1.TXT_FROM_DATE_MM.value!=\"\" && document.Form1.TXT_FROM_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_FROM_DATE_DD,document.Form1.TXT_FROM_DATE_MM,document.Form1.TXT_FROM_DATE_YY);");
			out.println("}");
			out.println("}");


			out.println("function check_date_to(){ ");
			out.println("  if(document.Form1.TXT_TO_DATE_DD.value!=\"\" && document.Form1.TXT_TO_DATE_MM.value!=\"\" && document.Form1.TXT_TO_DATE_YY.value!=\"\"){");
			out.println("  checkMonthLength(document.Form1.TXT_TO_DATE_DD,document.Form1.TXT_TO_DATE_MM,document.Form1.TXT_TO_DATE_YY);");
			out.println("}");	
			out.println("}");	
			
			
			out.println("function chk_validity(){  ");			
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_DD.value))>=(parseInt(document.Form1.TXT_TO_DATE_DD.value))){");
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_MM.value))<=(parseInt(document.Form1.TXT_TO_DATE_MM.value))){");
      out.println("if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))<=(parseInt(document.Form1.TXT_TO_DATE_YY.value))){");
      out.println(" if(((parseInt(document.Form1.TXT_FROM_DATE_DD.value))==(parseInt(document.Form1.TXT_TO_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_MM.value))==(parseInt(document.Form1.TXT_TO_DATE_MM.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_YY.value))==(parseInt(document.Form1.TXT_TO_DATE_YY.value)))){");
      out.println("}");
      out.println("else if(((parseInt(document.Form1.TXT_FROM_DATE_DD.value))>(parseInt(document.Form1.TXT_TO_DATE_DD.value)))&&");
      out.println("((parseInt(document.Form1.TXT_FROM_DATE_MM.value))==(parseInt(document.Form1.TXT_TO_DATE_MM.value)))&&");
      out.println(" ((parseInt(document.Form1.TXT_FROM_DATE_YY.value))==(parseInt(document.Form1.TXT_TO_DATE_YY.value)))){");
      out.println("      alert('End Date should be greater than Start Date');");
      out.println("     } ");
      out.println("}");
      out.println("else{");
      out.println(" alert('End Date should be greater than Start Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))>=(parseInt(document.Form1.TXT_TO_DATE_YY.value))){");
      out.println("    alert('End Date should be greater than Start Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(document.Form1.TXT_FROM_DATE_MM.value))<=(parseInt(document.Form1.TXT_TO_DATE_MM.value))){");
      out.println("  if((document.Form1.TXT_FROM_DATE_YY.value)<=(document.Form1.TXT_TO_DATE_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("   alert('End Date should be greater than Start Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(document.Form1.TXT_FROM_DATE_YY.value))<(parseInt(document.Form1.TXT_TO_DATE_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("  alert('End Date should be greater than Start Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			out.println("document.Form1.TXT_FROM_DATE_DD.focus();");
			out.println("return true;");
      out.println("}");


			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">"); 
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Marketing - Lead Source Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
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
			out.println("<br>"); 

			out.println("<br>"); 

			out.println("<table align='center' width='100%' class='table' border=\"0\">"); 


			out.println("<tr >"); 
			out.println("<td width='30%' >Lead Source Category</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_LEAD_CODE' maxlength='200' size='10' onblur=\"assig_text('J1'),makeRequest(document.Form1.TXT_LEAD_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\"></td></tr>"); 
			out.println("<tr ><td width='30%' >Lead Source Name</td>"); 
			out.println("<td width='30%' ><input class='txt_input' type='text' name='TXT_NAME'></td>"); 
			out.println("</tr>");

			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FROM' class=div_input>Start Date</DIV></td>"); 
			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_from()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_FROM_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_from()\"><a href style='{cursor:hand; }' onclick=load_calendar('1')>   Calendar</a> ");	
			out.println("</td> ");
			out.println("</tr>");
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FROM' class=div_input>End Date</DIV></td>"); 

			out.println("<TD WIDTH=\"30%\"><input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_DD maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_to()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_MM  maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date_to()\">");
			out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_TO_DATE_YY maxlength=\"4\" size=\"4\" value=\"\" onblur=\"check_date_to()\"><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");	
			out.println("</td> ");

			
			
			out.println("<td width='10%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"view()\"></td>"); 
			out.println("<td width='*%'></td>"); 
			out.println("</tr>"); 
			out.println("</table>"); 
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
			
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		else if(m_chksql.equals("lead_details")){		
			int i=0;		
			int x=0;	
			String m_lead_source = req.getParameter("lead_source");	
			String m_lead_name = req.getParameter("lead_name");	
			String m_from_date= req.getParameter("from_date");	
			String m_to_date= req.getParameter("to_date");	
			
			String m_lead_cat;
			String m_app_no;
			String m_app_status;
			String m_app_amt;


		rs = stmt.executeQuery ("SELECT DISTINCT TRIM(LEAD_SOURCE_NAME) LEAD_SOURCE_NAME,LEAD_SOURCE_CATEGORY "+
      " FROM "+m_schema_name+".AF_MK_PRO_INQUIRY  "+
		  " WHERE UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('"+m_lead_source+"%')  "+
			"and upper(LEAD_SOURCE_NAME) like UPPER('"+m_lead_name+"%') "+
      "or (to_date(to_char(ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')>=to_date('"+m_from_date+"','dd-mm-yyyy') "+
      "and to_date(to_char(ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')<=to_date('"+m_to_date+"','dd-mm-yyyy')) "+
      "and status='Y' "+
			"and INQUIRY_CODE is not null "+
			" ORDER BY LEAD_SOURCE_NAME ASC ");


			boolean more = rs.next();


				out.println("<html><head><font 12pt arial><title>Marketing - Lead Source Report</title></head>");
				out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
		
				out.println("<body leftmargin='0' topmargin='0' class=body>");
				out.println("<br>");
				out.println("<br>");
				out.println("<div align='center' width='180%' class='rep-body' style='{font: bold;text-align:center;}'>Marketing - Lead Source Report</div>");
				out.println("<form name='form1'>");
				out.println("<table border='1' width='100%' bgcolor='white' style='{color: black; font: bold 10px;}'>");
				out.println("</table>");			
		    out.println("<HR width='100%'>");
		
				out.println("<br>");			
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
				
		
				while(more){
				int j = 0; 
		
		
					rs1 = stmt1.executeQuery ("SELECT NULL,INQUIRY_CODE, STATUS, "+
				"LEAD_SOURCE_CATEGORY, "+
				"initcap(LEAD_SOURCE_NAME),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'), "+
				"NULL,INQUIRY_STATUS,CLIENT_LAST_NAME,NULL,NULL,NULL "+
				"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A "+
				"WHERE "+
				" UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('"+rs.getString(2)+"%') "+
				"and upper(LEAD_SOURCE_NAME) like UPPER('"+rs.getString(1)+"%') "+
        "or (to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')>=to_date('"+m_from_date+"','dd-mm-yyyy') "+
        "and to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')<=to_date('"+m_to_date+"','dd-mm-yyyy')) "+
        "and status='Y' "+
				"and INQUIRY_CODE is not null "+

				"UNION "+
				"SELECT APPLICATION_NO,INQUIRY_CODE, STATUS, LEAD_SOURCE_CATEGORY, "+
				"initcap(LEAD_SOURCE_NAME),TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),to_char(nvl(total_finance_amount,0),'999,999,999,999,999,999.99'),INQUIRY_STATUS,CLIENT_LAST_NAME,FINANCE_NO, "+
				"APPLICATION_STATUS,PAYMENT_STATUS "+
				"FROM "+m_schema_name+".AF_MK_PRO_INQUIRY A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
				"where INQUARY_NO=INQUIRY_CODE "+
				" and UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('"+rs.getString(2)+"%') "+
				"and upper(LEAD_SOURCE_NAME) like UPPER('"+rs.getString(1)+"%') "+
        "or (to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')>=to_date('"+m_from_date+"','dd-mm-yyyy') "+
        "and to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')<=to_date('"+m_to_date+"','dd-mm-yyyy')) "+
				" and application_status='ACTIVATED' "+
        "and status='Y' "+
			  "and INQUIRY_CODE is not null "+

				"UNION "+
				"SELECT APPLICATION_NO,INQUIRY_CODE, STATUS,LEAD_SOURCE_CATEGORY, LEAD_SOURCE_NAME, "+
				"TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),to_char(nvl(total_finance_amount,0),'999,999,999,999,999,999.99'),INQUIRY_STATUS,CLIENT_LAST_NAME,FINANCE_NO, APPLICATION_STATUS, PAYMENT_STATUS "+
				"FROM "+m_schema_name+".AF_MK_HIS_INQUIRY a, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS C "+
				"where UPPER(LEAD_SOURCE_CATEGORY) LIKE UPPER('"+rs.getString(2)+"%') "+
				"and upper(LEAD_SOURCE_NAME) like UPPER('"+rs.getString(1)+"%') "+
        "or (to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')>=to_date('"+m_from_date+"','dd-mm-yyyy') "+
        "and to_date(to_char(A.ENT_DATE,'dd-mm-yyyy'),'dd-mm-yyyy')<=to_date('"+m_to_date+"','dd-mm-yyyy')) "+
 				" and application_status='ACTIVATED' "+
        "and status='Y' "+
				"and INQUIRY_CODE is not null "+

				"AND INQUARY_NO=INQUIRY_CODE ");
		
			boolean more1 = rs1.next();


		
		
				if(rs.getString(2).equals("N/A") || rs.getString(2).equals("N/P")){
				m_lead_cat="-";
				}
				else{
				m_lead_cat=rs.getString(2);
				}
				
				
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
				out.println("<tr><td width='20%' style='{text-align:left;}'><b>Lead Source Category </td><td width='1%' style='{text-align:left;}'>:</td><td width='20%' style='{text-align:left;}'><b>"+m_lead_cat+"</td><td width='*%'></td></tr>");
				out.println("<tr><td width='20%' style='{text-align:left;}'><b>Lead Source Name </td><td width='1%' style='{text-align:left;}'>:</td><td width='20%' style='{text-align:left;}'><b>"+rs.getString(1)+"</td><td width='*%'></td></tr>");
				//out.println("<tr><td width='20%' style='{text-align:left;}'><b>Enter Date</td><td width='1%' style='{text-align:left;}'>:</td><td width='20%' style='{text-align:left;}'><b>"+rs.getString(6)+"</td><td width='*%'></td></tr>");
				out.println("<tr></tr>");
				out.println("<tr></tr>");
				out.println("<tr></tr>");
					
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
		
					
				out.println("<tr class=pdn_txtpos2>");
				out.println("<td width=\"20%\" ><b>Inquiry No</td>");
				out.println("<td width=\"20%\" ><b>Inquiry Status </td>"); 

				out.println("<td width=\"20%\" ><b>Application No </td>"); 
				out.println("<td width=\"10%\" ><b>Application Status </td>"); 
				out.println("<td width=\"20%\" align=right><b>Total Amount </td>");
				out.println("<td width=\"10%\" align=right><b>Entered Date </td>");
				out.println("</tr >"); 


			while(more1){
				if(rs1.getString(1)==null){
				
				
				m_app_no="-";
				}
				else{
				m_app_no=rs1.getString(1);
				}
				
				if(rs1.getString(11)==null){
				m_app_status="-";
				}
				else{
				m_app_status=rs1.getString(11);
				}
				
				if(rs1.getString(7)==null){
				m_app_amt="-";
				}
				else{
				m_app_amt=rs1.getString(7);
				}
				
				
				
				if(j>0 && j%2==1){
        out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
							
				out.println("<td width='20%' style='{text-align:left;}'>"+rs1.getString(2)+"</td>");
				out.println("<td width='20%' style='{text-align:left;}'>"+rs1.getString(8)+"</td>");
				out.println("<td width='20%' style='{text-align:left;}'>"+m_app_no+"</td>");
				out.println("<td width='10%' style='{text-align:left;}'>"+m_app_status+"</td>");
				out.println("<td width='20%' style='{text-align:right;}'>"+m_app_amt+"</td>");
				out.println("<td width='10%' style='{text-align:right;}'>"+rs1.getString(6)+"</td></tr>");

				j=j+1;
				more1=rs1.next();
	
				if (!more)
				{
				break;
				}
				}
				
				out.println("</table>");
				out.println("</table>");
				out.println("<br>");			
		
						
				out.println("<tr></tr>");
				out.println("<tr></tr>");
				out.println("<tr></tr>");

				out.println("</table>");
				out.println("<br>");			
				more=rs.next();
								if (!more)
				{
				break;
				}
				}

				out.println("</table>");
				out.println("</form>");
				out.println("</body>");
		    out.println("</html>");

				}
		
//--------------------------------------------------------------------------------------------------------------------			
		
		
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
