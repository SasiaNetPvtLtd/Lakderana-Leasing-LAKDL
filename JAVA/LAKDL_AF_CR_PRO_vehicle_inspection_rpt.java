//CREATED BY	:SAJITH MENDIS
//DATE/TIME		:09-04-2014

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_vehicle_inspection_rpt extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	java.text.NumberFormat nf,nf1;
	public String m_chksql;
	

	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
 
			conn = m_sn_methods.met_user_validate(req); 
			stmt=conn.createStatement();

      m_chksql=req.getParameter("chksql");
			
			String m_schema_name = m_sn_methods.schema_name;
			
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			if(m_chksql.equals("main_page")){

			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit Process -Vehicle Inspection Report </TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			out.println("var b_flag=0");
			
			out.println("function get_vector(data_vec) {");
			out.println("			if(data_vec.length==0 && document.Form1.SCREEN_NAME.value==\"NEW\" &&  document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("help_button_finance();");
			out.println("			}");
			out.println("}");




			out.println("function load_lock(){	"); 
			out.println("}	"); 

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_vehicle_inspection_rpt?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_vehicle_inspection_rpt?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 


			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_LAKDL_AF_MISF_display_contract_details\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}");
			
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_MAS_Help_Msg_Servlet?class_in=\"+client_name+\"AF_MAS_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\"Credit Process -  Vehicle Inspection Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\"Credit Process -  Vehicle Inspection Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println("document.Form1.TXT_APPLICATION_NO.disabled=true;"); 
			out.println("document.Form1.TXT_ASSET_ID.disabled=true;"); 
			out.println("document.Form1.TXT_ENGINE_NO.disabled=true;"); 
			out.println("document.Form1.TXT_CHASSIS_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_NO.disabled=true;"); 
			out.println("document.Form1.TXT_REG_DATE.disabled=true;"); 
			out.println("document.Form1.TXT_VEHICLE_NO.disabled=true;"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
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
			
			out.println("function makeRequest1(obj) {");
		  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_sql_validations?chksql=m_prime_chk_LAKDL_AF_MK_display_finance&data_val=\"+obj.value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_MK_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			out.println("		if(document.Form1.hid_help_type.value==\"1\"){"); 
			out.println("			help_value_assign_1()");
	  		out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"2\"){"); 
			out.println("			help_value_assign_doc_1()");
	  		out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"3\"){"); 
			out.println("			help_value_assign_doc_2()");
	  		out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"4\"){"); 
			out.println("			help_value_assign_doc_3()");
	  		out.println("		}"); 
			out.println("		if(document.Form1.hid_help_type.value==\"6\"){"); 
			out.println("			help_value_assign_new()");
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
			//out.println(" document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			
			//added by udara 01-09-2014
			out.println("      if(document.Form1.hid_help_type.value==\"1\"){ ");
			out.println("          document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("      }");
			// end by udara 01-09-2014
			
			// added by udara 04-09-2014
			out.println("      if(document.Form1.hid_help_type.value==\"6\"){ ");
			out.println("          document.Form1.TXT_FINANCE_NO_1.value=\"\";"); 
			out.println("      }");
			// added by udara 04-09-2014
			
			//out.println("	alert('Close'); ");
			
			out.println("	}"); // exit button
			
			out.println("}");

			// udara 01-09-2014
			out.println("	else{");
			out.println("      if(document.Form1.hid_help_type.value==\"1\"){ ");
			out.println("          document.Form1.TXT_FINANCE_NO.value=\"\";"); 
			out.println("      }");
			out.println("	}");
			// udara 01-09-2014

			out.println("if(oBj.valout[2]==' '){");
			//out.println("document.Form1.TXT_FINANCE_NO.value=\"\"");
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


			out.println("function assig(val) {"); 
			out.println("document.Form1.hid_assig.value=val");
			out.println("}");
			
			// commented by udara 29-08-2014
			/*
		  	out.println("function run_report() {");
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_vehicle_inspection_report_details?chksql=main_page&print=TRUE&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&doc_1=\"+document.Form1.TXT_DOC_UPLOAD_1.value+\"&doc_2=\"+document.Form1.TXT_DOC_UPLOAD_2.value+\"&doc_3=\"+document.Form1.TXT_DOC_UPLOAD_3.value+\"&status=run_report\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_vehicle_inspection_rpt?chksql=main_page';"); // added by udara 29-08-2014
			
			out.println("}");
			out.println("else{");
			out.println("alert('Please enter Finance No')");
			out.println("}");
			out.println("}"); 
			*/
			//TXT_DOC_UPLOAD_1
			
			// added by udara 29-08-2014
			
			out.println("function run_report() {");
			out.println("    if(document.Form1.TXT_FINANCE_NO.value==\"\"){");
			out.println("       alert('Please enter Finance No')");
			out.println("    }"); 
			out.println("    else if(document.Form1.TXT_DOC_UPLOAD_1.value==\"\"){");
			out.println("       alert('Please select document 1')");
			out.println("    }"); 
			out.println("    else if(document.Form1.TXT_DOC_UPLOAD_2.value==\"\"){");
			out.println("       alert('Please select document 2')");
			out.println("    }");
			out.println("    else if(document.Form1.TXT_DOC_UPLOAD_3.value==\"\"){");
			out.println("       alert('Please select document 3')");
			out.println("    }"); 
			out.println("    else{"); 
			out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_vehicle_inspection_report_details?chksql=main_page&print=TRUE&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&doc_1=\"+document.Form1.TXT_DOC_UPLOAD_1.value+\"&doc_2=\"+document.Form1.TXT_DOC_UPLOAD_2.value+\"&doc_3=\"+document.Form1.TXT_DOC_UPLOAD_3.value+\"&status=run_report\";");
			out.println("       popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_vehicle_inspection_rpt?chksql=main_page';"); 
			out.println("    }");
			out.println("}"); 
			
			// end by udara 29-08-2014
			
			
			out.println("function view() {");
			out.println("if(document.Form1.TXT_FINANCE_NO_1.value!=\"\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_vehicle_inspection_report_details?chksql=main_page&print=TRUE&finance_no=\"+document.Form1.TXT_FINANCE_NO_1.value+\"&doc_1=\"+document.Form1.TXT_DOC_UPLOAD_1.value+\"&doc_2=\"+document.Form1.TXT_DOC_UPLOAD_2.value+\"&doc_3=\"+document.Form1.TXT_DOC_UPLOAD_3.value+\"&status=view_report\";");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=550,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");
			out.println("else{");
			out.println("alert('Please enter Finance No')");
			out.println("}");
			out.println("}"); 
			
			



			out.println("function help_button_finance() {"); 
			out.println("    document.Form1.hid_help_type.value=\"1\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql2_sql_new\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			out.println("function help_button_finance_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"6\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql2_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_FinanceSql2_VIR_sql_new\";"); // added by udara 04-09-2014
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO_1.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			
			out.println("function help_upload_doc_1() {"); 
			out.println("    document.Form1.hid_help_type.value=\"2\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_upload_doc\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			
			out.println("function help_upload_doc_2() {"); 
			out.println("    document.Form1.hid_help_type.value=\"3\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_upload_doc\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			
			out.println("function help_upload_doc_3() {"); 
			out.println("    document.Form1.hid_help_type.value=\"4\";"); 
			//out.println("    m_sql = \"m_help_TXT_FinanceSql_sql\";"); 
			out.println("    m_sql = \"m_help_TXT_upload_doc\";"); 
			out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\";"); 
			out.println("    HelpBox('1','10','0');"); 
			out.println("}");
			
			
			out.println("function help_value_assign_1() {"); 
			out.println("   document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];");
			out.println("}");
			
			out.println("function help_value_assign_new() {"); 
			out.println("   document.Form1.TXT_FINANCE_NO_1.value=oBj.valout[2];");
			out.println("}");
			
			
			out.println("function help_value_assign_doc_1() {"); 
			out.println("   document.Form1.TXT_DOC_UPLOAD_1.value=oBj.valout[4];");
			out.println("}");
			
			out.println("function help_value_assign_doc_2() {"); 
			out.println("   document.Form1.TXT_DOC_UPLOAD_2.value=oBj.valout[4];");
			out.println("}");
			
			out.println("function help_value_assign_doc_3() {"); 
			out.println("   document.Form1.TXT_DOC_UPLOAD_3.value=oBj.valout[4];");
			out.println("}");
			
			out.println("function get_document(doc_no){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_vehicle_inspection_rpt?chksql=get_document&doc_no='+doc_no;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			
			

//-----------------------------------------------------------------------------------------------------------------------

			out.println("</script>"); 
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_lock()\">"); 
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_assig' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_client_code' VALUE=\"\">"); 

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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Vehicle Inspection Report</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
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

			out.println("<table align='center' width='100%' class='table'>"); 
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_GEN_REP'  class=div_input><b>UPLOAD DOCUMENTS <b></DIV></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance No </DIV></td>"); 
			//out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_FINANCE_NO)\" >"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='200' size='10' onblur=\"help_button_finance()\" >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO' value=\"Help\" onClick=\"help_button_finance()\">"); 
			//out.println("<td width='*%'></td>");
			out.println("<input class='but_input' type='button' name='BUT_RUN' value=\"Save Report\"  Style=\"{width:110px;}\"  onClick=\"run_report()\"></td>"); 

			out.println("</tr>"); 

			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_DOC_UPLOAD_1'  class=div_input>Upload Document 1 </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_DOC_UPLOAD_1' maxlength='200' size='10'  >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_UP_DOC_1' value=\"Help\" onClick=\"help_upload_doc_1()\">"); 
			//out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\"  Style=\"{width:110px;}\"  onClick=\"get_document(document.Form1.TXT_DOC_UPLOAD_1.value)\"></td>");

			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_DOC_UPLOAD_2'  class=div_input>Upload Document 2 </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_DOC_UPLOAD_2' maxlength='200' size='10'  >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_UP_DOC_2' value=\"Help\" onClick=\"help_upload_doc_2()\">"); 

			out.println("</tr>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_DOC_UPLOAD_3'  class=div_input>Upload Document 3 </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_DOC_UPLOAD_3' maxlength='200' size='10' >"); 
			out.println("<input class='but_input' type='button' name='BUT_HELP_UP_DOC_3' value=\"Help\" onClick=\"help_upload_doc_3()\">"); 

			out.println("</tr>"); 
	
			out.println("</table>"); 
			
			
			
			out.println("<br>"); 
			out.println("<br>"); 
			out.println("<br>"); 
			
			
			
			out.println("<table align='center' width='100%' class='table'>"); 
			
			
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_VIEW_REP'  class=div_input><b>VIEW REPORT <b></DIV></td>"); 
			out.println("</tr>"); 
		
			out.println("<tr >"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO_1'  class=div_input>Finance No </DIV></td>"); 
			//out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO_1' maxlength='200' size='10' onblur=\"makeRequest1(document.Form1.TXT_FINANCE_NO_1)\" >"); 
			out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO_1' maxlength='200' size='10' onblur=\"help_button_finance_1()\" >"); // udara 04-09-2014
			out.println("<input class='but_input' type='button' name='BUT_HELP_FINANCE_NO_1' value=\"Help\" onClick=\"help_button_finance_1()\">"); 
			//out.println("<td width='*%'></td>");
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View Report\"  Style=\"{width:110px;}\"  onClick=\"view()\"></td>"); 

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
		
		if(m_chksql.equals("get_document")){ 
				res.setContentType("APPLICATION/OCTET-STREAM");
				stmt = conn.createStatement ();
				try {
					String m_document_no = req.getParameter("doc_no");
					Blob document = null;
					
					String Sql_data="";
					
					
					Sql_data=
						
						" SELECT	A.DOCUMENT, A.FILE_NAME"+
						"  FROM	"+m_schema_name+".AF_MK_DOCUMENT_UPLOAD A "+
						"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = '"+m_document_no+"'";
						//"  WHERE	A.ACTIVE_STATUS = 'Y' AND A.DOCUMENT_NO = 'DOC-FIN20081208-0001'";
					
					
					rs=stmt.executeQuery(Sql_data);
					//out.println(""+Sql_data+"");
					boolean more=rs.next();
					
					if(more){
						try{
							res.setHeader("Content-Disposition",
								"attachment;filename="+rs.getString(2)+"");
							document = rs.getBlob(1);
							
							
							InputStream in = document.getBinaryStream();
							int length = (int) document.length();
							
							int bufferSize = 1024;
							byte[] buffer = new byte[bufferSize];
							
							while ((length = in.read(buffer)) != -1) {
								out.write(buffer, 0, length);
							}
							
							in.close();
							out.flush();
						}
						catch (Exception ex) {
							ex.printStackTrace();
							try{conn.rollback();}catch(Exception e){};
							res.setContentType("text/html");
							out.println("<HTML><HEAD>");
							out.println("<SCRIPT language='JavaScript'>");
							out.println("function displaymsg() {");
							out.println("alert('Error when downloading document....');");
							out.println("window.close();"); 
							out.println("}</SCRIPT></HEAD>");
							out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
							out.println("</html>");
							out.flush();
							out.close();
							
						}
						
						
					}else{
						
						res.setContentType("text/html");
						out.println("<HTML><HEAD>");
						out.println("<SCRIPT language='JavaScript'>");
						out.println("function displaymsg() {");
						out.println("alert('Document Not Found....');");
						out.println("window.close();"); 
						out.println("}</SCRIPT></HEAD>");
						out.println("<body onload='displaymsg();'></body>");
						out.println("</html>");
						out.flush();
						out.close();
					}
					
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					try{conn.rollback();}catch(Exception e){};
					res.setContentType("text/html");
					out.println("<HTML><HEAD>");
					out.println("<SCRIPT language='JavaScript'>");
					out.println("function displaymsg() {");
					out.println("alert('Error when downloading document....');");
					out.println("window.close();"); 
					out.println("}</SCRIPT></HEAD>");
					out.println("<body onload='displaymsg();'>Error:"+ex.toString()+"</body>");
					out.println("</html>");
					out.flush();
					out.close();
					
				}finally{
					
					if(out!=null){try{out.close();  }catch(Exception e){}}
					if(conn!=null){try{conn.close();  }catch(Exception e){}}
				}
				
			}
			

			
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}