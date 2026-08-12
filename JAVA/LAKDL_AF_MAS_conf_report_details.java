//Created by Minal on 26-06-2015 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import java.util.*;
import oracle.jdbc.driver.*;


public class LAKDL_AF_MAS_conf_report_details extends javax.servlet.http.HttpServlet {
	
	Connection conn;
	Statement stmt_2,stmt,stmt_invoice,stmt_rental,stmt_pricing,stmt_charges,stmt2;
	CallableStatement callstmt;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	
	
	// public ResultSet rs1,rs_doc_charge;
	public ResultSet rs,rs2,rs3,rs_rental,rs_pricing,rs_charges;
	
	public String m_chksql;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)	throws IOException	{
		
		try {
			
			//************************************************************	
			//LAKDL_AF_RE_PRO_drill_downs obj =new LAKDL_AF_RE_PRO_drill_downs();
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_username = m_sn_methods.username;
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
			
			ServletOutputStream out = res.getOutputStream();
			m_chksql=req.getParameter("chksql");
			
			stmt_invoice=conn.createStatement();
			stmt_pricing=conn.createStatement();
			stmt=conn.createStatement();
			stmt_2=conn.createStatement();
			stmt_rental=conn.createStatement();
			stmt_charges = conn.createStatement ();
			String query="";
			if (m_chksql.trim().equals("idle")) {
				out.println("idle");
			}	
			
			else if(m_chksql.equals("main_page")){
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Confirmation Report Details - Admin</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					out.println("var m_flag=0");

					
					out.println("function get_vector(data_vec) {");
					out.println("	if(data_vec.length>0 && document.Form1.hid_text.value==\"J8\"){");
					out.println("        if(data_vec[0]=='N'){");
					out.println("           alert('New due date cannot be less than day end date'); ");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_DD_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_MM_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("     	    document.getElementById('TXT_VALUE_DATE_YY_NEW_'+document.Form1.hid_cal_id.value).value='';");
					out.println("        }");
					out.println("   }");
					out.println("}");
					
					 
			
					out.println("function get_vector_normal(http_response) {");
					out.println(" details.innerHTML = ''; ");
					out.println(" details.innerHTML = http_response; ");
					out.println(" ");
					out.println("}");
					
					out.println("function load_details() {");
					out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
			        out.println("       m_from_date = document.Form1.FROM_DAY.value+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");  
				    out.println("       m_to_date   = document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;"); 
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MAS_conf_report_details?chksql=load_details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value;");  
					//out.println("    alert(m_url); ");
					//out.println("      load_interface(m_url,'NORM');");
					out.println("	   window.open(m_url); ");
					out.println("	}");
				    out.println("}");
					
					out.println("function load_date_validations(due_date,new_due_date,row_num) {");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=load_date_validations&due_date=\"+due_date+\"&new_due_date=\"+new_due_date;");
					out.println("      document.Form1.hid_text.value = 'J8';   ");
					out.println("      load_interface(m_url,'XML');");
					out.println("}");
					
					out.println("function load_date_validations_on_blur(row_num) {");
					
					out.println("    if(document.getElementById('TXT_VALUE_DATE_DD_NEW_'+row_num).value!='' && document.getElementById('TXT_VALUE_DATE_MM_NEW_'+row_num).value!='' && document.getElementById('TXT_VALUE_DATE_YY_NEW_'+row_num).value!=''){ ");
					
					out.println("       var due_date     = document.getElementById('TXT_VALUE_DATE_DD_'+row_num).value     +'-'+ document.getElementById('TXT_VALUE_DATE_MM_'+row_num).value     + '-' + document.getElementById('TXT_VALUE_DATE_YY_'+row_num).value ");
					out.println("       var due_date_new = document.getElementById('TXT_VALUE_DATE_DD_NEW_'+row_num).value +'-'+ document.getElementById('TXT_VALUE_DATE_MM_NEW_'+row_num).value + '-' + document.getElementById('TXT_VALUE_DATE_YY_NEW_'+row_num).value ");
					out.println("       m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=load_date_validations&due_date=\"+due_date+\"&new_due_date=\"+due_date_new;");
					out.println("       document.Form1.hid_text.value = 'J8';   ");
					out.println("       document.Form1.hid_cal_id.value = row_num;   ");
					out.println("       load_interface(m_url,'XML');");
					
					out.println("   }");
					
					out.println("}");
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					//out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=main_page';"); 
					out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_conf_report_details?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					//out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=main_page';"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_conf_report_details?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("   help_box.innerHTML=\" Confirmation Report Details - Admin - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Confirmation Report Details - Admin - \"+document.Form1.hid_status.value;"); 
					out.println("}"); 
					
					
					out.println("function load_screen_status(m_val){"); 
					out.println("if(m_val==\"NEW\"){"); 
					out.println("new_window();"); 
					out.println("}"); 
					out.println("else if(m_val==\"HELP\"){"); 
					//out.println("load_help_msg();"); 
					out.println("}");
					 
					out.println("else{");
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					//out.println("edit_window();"); 

					out.println("}"); 
					out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
					out.println("if(m_val==\"NEW\"){");
					out.println("document.Form1.hid_status.value=\"New\";"); 
					out.println("document.Form1.hid_delete.value=\"Delete\";"); 
					out.println("document.Form1.hid_save.value=\"reverse\";");
					out.println("}else if(m_val==\"EDIT\"){");  
					out.println("	if(confirm(\"Are you sure you want to modify records?\")){ "); 
					out.println("document.Form1.hid_status.value=\"Edit\";");  
					out.println("document.Form1.hid_delete.value=\"Modify\";");
					out.println("document.Form1.hid_save.value=\"generate\";");
					out.println("chng_butt();");					
					out.println("document.Form1.TXT_APP_NO.value=\"\";");
					out.println("details.innerHTML=\"\";");
					out.println("document.Form1.TXT_VALUE_DATE_DD.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_MM.disabled=true;");
				  out.println("document.Form1.TXT_VALUE_DATE_YY.disabled=true;");
					out.println("}"); 
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
					
					//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");"); // commented by udara on 22-11-2012
					//out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					
					out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_MISF_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
					
					out.println("	if(oBj.valout[4] ==\" \"){"); 
					out.println(" clear(); ");
					out.println(" }");
					
					out.println("	if(oBj.valout[1] !=\" \"){"); 
					out.println("	if(oBj.valout[1] !=\"Close\"){"); 
					out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
					out.println("	if(oBj.valout[1]!=\"Next\"){"); 
					out.println("if(document.Form1.hid_help_type.value=='1'){"); 
					out.println("		help_value_assign_1(oBj);"); 
					out.println("}");
					
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
					out.println("clear()");
					out.println("	}");
					out.println("}");
					out.println("if(oBj.valout[2]==' '){");
					out.println("clear()");
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
					
					
					out.println("function clear(){");
					out.println("   if(document.Form1.hid_help_type.value==\"1\"){;"); 
					//out.println("document.Form1.TXT_APP_NO.value=\"\"");
					out.println("document.Form1.TXT_FIN_NO.value=\"\""); 
					out.println(" details.innerHTML = ''; ");
					out.println("}");
					out.println("}");
					
					out.println("function help_button_1() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					//out.println("    m_sql = \"FinanceSql\";"); 
					//out.println("    m_sql = \"DueDateChangeFinanceSql\";");
					//out.println("    m_sql = \"m_help_DELETION_LETTER_APP\";");
					//out.println("    m_sql = \"m_help_TXT_confirmation_rpt_appr_sql\";"); 
					out.println("    m_sql = \"m_help_TXT_confirmation_rpt_view_sql\";"); 
					//out.println("    alert(document.Form1.TXT_FIN_NO.value);"); 
					out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\";");
					out.println("    HelpBox('1','10','0');"); 
					out.println("}");
					
					out.println(" function help_value_assign_1(oBj) {"); 
					out.println(" 	document.Form1.TXT_FIN_NO.value=oBj.valout[2];");
					//out.println("   load_details()");
					out.println("}"); 					

					
						out.println("function ckeck_new_date(){ "); 
				out.println("b_flag=0;");
				out.println("if(m_table.innerHTML==\"\"){");
				out.println("alert('No data to save');");
				out.println("b_flag=1;");
				out.println("}"); 
				out.println("else if(!count_date_selected()){"); 
				out.println("alert('Please enter new date');");
				out.println("b_flag=1;");
				out.println("}"); 
				
				out.println("else{");
				out.println("b_flag=0;");
				out.println("}"); 
				
				out.println("}"); 

				out.println("function count_date_selected(){ ");
				out.println("count=0;");
				out.println("var arr_size=document.Form1.hid_no_rec.value;");
				
				out.println("for(i=0;i<arr_size;i++){");
				out.println("m_new_date_dd=\"TXT_NEW_DATE_DD_\"+i;");
				out.println("m_new_date_mm=\"TXT_NEW_DATE_MM_\"+i;");
				out.println("m_new_date_yy=\"TXT_NEW_DATE_YY_\"+i;");
				
				out.println("if(document.Form1.elements[m_new_date_dd].value!='' && document.Form1.elements[m_new_date_mm].value!='' &&  document.Form1.elements[m_new_date_yy].value!=''){");
				out.println("count=count+1;");
				out.println("}");		
				
				out.println("}");		
				
				out.println("if(count>0)");
				out.println("return true;");
				out.println("else");
				out.println("return false;");
				
				out.println("}"); 

				out.println("function load_calendar(num) {");
				out.println(" document.Form1.hid_cal_date.value=num;"); 
				out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
				//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
				out.println("}");
				
				out.println("function load_c_date(val) {");
				
				
				out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
				out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.VAL_DAY.value=v_date;");
				out.println("     document.Form1.VAL_MONTH.value=v_month;");
				out.println("     document.Form1.VAL_YEAR.value=val;");
				out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//---added by Prabash on 09-05-2012-----**
				out.println("else  if(document.Form1.hid_cal_date.value=='3'){"); 
				out.println("v_date=val.substr(0,val.indexOf('-'));");
				out.println("if(v_date.length<2)");
				out.println("v_date=0+v_date");
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				out.println("v_month=val.substr(0,val.indexOf('-'));");
				out.println("if(v_month.length<2)");
				out.println("v_month=0+v_month");
				
				out.println("val=val.substr(val.indexOf('-')+1,val.length);");
				
				
				out.println("     document.Form1.REN_DAY.value=v_date;");		
				//	out.println("alert('date'+document.Form1.hid_date.value);");
				out.println("  }");
				//--------------------------------------**
				
				out.println("}");
				out.println("}");
				
				
					out.println("function check_Date(objDD,objMM,objYY) {");
					out.println("if( objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
					out.println("if(checkMonthLength(objDD,objMM,objYY))");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");						
					out.println("}");
				
					out.println("function validate_data(){"); 
					out.println("if(document.Form1.TXT_APP_NO.value==\"\"){  "); 
					out.println("DIV_TXT_APP_NO.style.color='red';");
					out.println("return false;"); 
					out.println("}else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 

					out.println("function load_sysdate(){	"); 
					if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.FROM_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.FROM_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.FROM_YEAR.value='"+rs2.getString(3)+"';");
					out.println("document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");//To-date
					}
					out.println("}"); 

					out.println("function load_details_report(val){"); 
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_View?chksql=main_page&finance_no=\"+val; ");
					out.println("window.open(m_url,'popupwin_conf_rpt_app_view','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1')");
					out.println("} "); 
					
					out.println("function show_transaction_history_new(val,val2){ "); 
				    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				    out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				    out.println("}");
				
					out.println("function drilldown(val,val2){ "); 
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=drill_down&finance_no='+val+'&app_status='+val2;"); 
					out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("}");
					//-------------------------------end---------------------------------------------------------------------
					out.println("</script>"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sysdate();\">"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_save' VALUE=\"reverse\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_cal_id' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_delete' VALUE=\"Delete\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_text' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_SCREEN' VALUE=\"AF_CR_PRO_INVOICE_REVERSAL_OPTION\">");
					out.println("<input type=hidden name='hid_date' value=\"\">");

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Confirmation Report Details - Admin</td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  //modified by nuwan  de silva 20-08-07
					out.println("<td width='10%'> &nbsp; </td>"); 
					out.println("<td width='6%'></td>");  
				//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'    onClick='before_submit()' name=\"MAIN_BUT_3\" value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");'    onClick='load_screen_status(\"HELP\")'        value=\"Help\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()'                      value=\"Cancel\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'   onclick='close_window()'                      value=\"Close\"></td>");  

					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td></tr><tr>");  
					out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
					out.println("</tr><tr>");  
					out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


					out.println("<table align='center' width='100%' class='table'>");

					out.println("<tr style = \"display:none\" >"); 
					out.println("<td width='10%'>Approval Level</td>"); 
					out.println("<td width='40%'><select class='txt_input' name='TXT_APP_NO'>");
					out.println("<option value='ALL'>All</option>"); // added by udara 31-08-2015
					out.println("<option value='GEN'>Approval Level 1</option>");
					out.println("<option value='APPROVED'>Approval Level 2</option>");
					out.println("<option value='APPROVED2'>Approval Level 3</option>");
					out.println("<option value='APPROVED3'>Approval Level 4</option>");
					out.println("<option value='CONFIRMED'>Confirmed</option>");
					out.println("</select></td>"); 
					out.println("<tr class=tr_input >");
					out.println("<td width='20%'ID=VDATE>From Date</td>");
					out.println("<td width='*%'><input name=\"FROM_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)> ");
					out.println("    <input name=\"FROM_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.FROM_DAY,document.Form1.FROM_MONTH,document.Form1.FROM_YEAR)>");
					out.println("</tr>");

					out.println("<tr class=tr_input>");
					out.println("<td width='20%'ID=VDATE>To Date</td>");
					out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
					out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)>"); // <a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> 
		       		out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</tr>");
					
					// added by udara 31-08-2015
					
					out.println("<tr style = \"display:none\" >"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Finance No</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='30' size='30' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" > ");
					//out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>");

					
					// end by udara 31-08-2015

					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 

					out.println("</table>");
					
					out.println("<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr>");
					out.println("<td ><div id=details></div></td></tr></table>");
		
					out.println("</form>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>");
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</body>"); 
					out.println("</html>"); 
					out.flush();
		
			}
			
			
			//else if(m_chksql.equals("main_page")) {
			else if(m_chksql.equals("load_details")) {
				
					String m_from_date= req.getParameter("from_date");
					String m_to_date = req.getParameter("to_date");
				
					out.println("<HTML>"); 
			
					out.println("<HEAD>"); 
					out.println("<TITLE> Confirmation Report Details </TITLE>"); 
					
					out.println("<SCRIPT language=\"JavaScript\">"); 
					out.println("function save_window(){	"); 
					out.println("before_submit();"); 
					out.println("}"); 
					
					out.println("function load_roll_value(m_val){");
				   	out.println("help_box.innerHTML=\" Confirmation Report Details - \"+m_val;"); 
					out.println("}"); 
					out.println("");
					
					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\"Confirmation Report Details - \"+document.Form1.hid_status.value;"); 
					out.println("}");
					
					
					out.println("function clear_window(){	"); 
					out.println("		if(confirm(\"Are you sure you want to clear the screen?\")){ "); 
					out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MAS_conf_report_details';"); 
					out.println("		}"); 
					out.println("}");
					
					out.println("function close_screen() {");
					out.println("		if(document.Form1.HID_CLOSE_STS.value=='Y'){ "); 
					out.println("		     if(confirm(\"Are you sure you want to close the screen?\")){ "); 
					out.println("		      window.close();"); 
					out.println("		     }"); 
					out.println("		 }"); 
					out.println("		else { "); 
					out.println("		     close_window();"); 
					out.println("		}"); 
					out.println("}");
					
					out.println("function disabled_all(){"); 
							out.println("   for(var y=0;y<parseInt(document.Form1.noofrecodes.value);y++){");
							out.println("      document.Form1.elements[\"CHK_\"+y].disabled      =false;");	
							out.println("   }");
							out.println("}");
							
							out.println("function count_receipts(){ ");			
							out.println("count=0;");		
							out.println("for(i=0;i<parseInt(document.Form1.noofrecodes.value);i++){");	
							out.println("m_chk=\"CHK_\"+i;");
							out.println("m_chk_dis=\"CHK_DIS_\"+i;");
							//out.println("if((document.Form1.elements[m_chk].checked==true)||(document.Form1.elements[m_chk_dis].checked==true)){");
						    out.println("if(document.Form1.elements[m_chk].checked==true){");
							out.println("count=count+1;");
							out.println("}");		
							out.println("}");				
							out.println("if(count>0)");
							out.println("return true;");
							out.println("else");
							out.println("return false;");		
							out.println("}"); 
							
					out.println("function ckeck_data(){ "); 			
							out.println("b_flag=0;");						
							out.println("if(details.innerHTML==\"\"){");
							out.println("alert('No data to delete..!');");
							out.println("b_flag=1;");
							out.println("}"); 
							out.println("else if(!count_receipts()){"); 
							out.println("alert('No invoice selected...!');");
							out.println("b_flag=1;");
							out.println("}"); 						
							out.println("else{");
							out.println("b_flag=0;");
							out.println("}"); 			
				            out.println("}"); 
		
							
						   out.println("function before_submit(){ "); 
							
							out.println("check_comments_field();"); // added by udara 03-07-2015
							
							out.println("checked_count()");
							//out.println("if(b_flag==0){");
							
							//out.println("   if(m_count==0){"); // added by udara 03-07-2015
							out.println("if(chk_count==0){");
							out.println("  alert('Please select a record');  ");
							out.println("} ");
							
							out.println("else if(chk_count>0 && m_count==0){"); // added by udara 03-07-2015
							
							out.println("disabled_all();");
							out.println("		if(confirm(\"Are you sure you want save data\")){ "); 
							out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Conf_Details_Save';");  
							out.println("		document.Form1.submit();	"); 					 
							out.println("		}"); 
							
							out.println("		}");  // udara 16-07-2015
							
							out.println("} ");
		
						
						    out.println("function check_status_app(row_num) {");
						out.println("   if(document.Form1.elements[\"CHK_\"+row_num].checked){");
						out.println("         	document.Form1.elements[\"CHK_\"+row_num].value     = \"on\";");
						out.println("   }");
						out.println("   else{");				
						out.println("      document.Form1.elements[\"CHK_\"+row_num].value =\"off\";");
						out.println("   }");
				
						out.println("}");
						
						out.println("function show_transaction_history_new(val,val2){ "); 
						out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
						out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
						out.println("}");
						
						
						out.println("function drilldown(val){ "); 
						out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval?chksql=drill_down&finance_no='+val;"); 
						out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
						out.println("}");
						
						// added by udara 03-07-2015
							out.println("var m_count = 0;  ");
							out.println("function check_comments_field(){ "); 
							//out.println("    alert('check_comments_field'); ");
							out.println("    m_count = 0;  ");
							out.println("	 for (var i=0; i < document.Form1.noofrecodes.value; i++ ){");
							//out.println("       alert(document.Form1.elements[\"APPROVE_COMMENT_\"+i].value); ");
							//out.println("       if(document.Form1.elements[\"APPROVE_COMMENT_\"+i].value==''){ ");
							out.println("       if( (document.Form1.elements[\"APPROVE_COMMENT_\"+i].value=='') && (document.Form1.elements[\"CHK_\"+i].checked==true) ){ "); 
							out.println("          alert('Please enter the comment'); ");
							out.println("          document.Form1.elements[\"APPROVE_COMMENT_\"+i].focus(); ");
							out.println("          m_count = m_count +1; "); 
							out.println("          break; "); 
							out.println("       }"); 
							out.println("    }"); 
							out.println("}"); 
							
							
							out.println("var m_count = 0;  ");
							out.println("function checked_count(){ "); 
							//out.println("    alert('check_comments_field'); ");
							out.println("    chk_count = 0;  ");
							out.println("	 for (var i=0; i < document.Form1.noofrecodes.value; i++ ){");
		
							out.println("       if(document.Form1.elements[\"CHK_\"+i].checked==true){ "); 
							out.println("          chk_count = chk_count +1; "); 
							out.println("       }"); 
							out.println("    }"); 
							out.println("}");
							// end by udara 03-07-2015
						
					out.println("</SCRIPT> "); 
					
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' >"); 
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
					out.println("<td style='height: 30px'>"); 
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>   "); 
					out.println("<tr>"); 
					out.println("<td height='1'><img height='1' src='spacer.gif' width='1' /></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Client Details Approval</td>"); 
					out.println("</tr>"); 
					out.println("<tr>");
					//*********
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<td width='6%'></td>");  
				//	out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut'  onMouseOver='load_roll_value(\"Save\");'   value=\"Save\" onClick='before_submit();'></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  // commented by udara 15-10-2015
					out.println("<td width='10%' align='center'><input type=button name=back value=\"Close\" class=mainbut onclick=close_screen(); onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(\"Close\");'></td>");
					out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
					out.println("</table>");  
					out.println("</td>");
					//*********
				    out.println("</tr>");
					out.println("</table>"); 
					out.println("<FORM NAME='Form1' method='post'>"); 
					
					
					out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
		
					out.println("<INPUT TYPE='Hidden' NAME='HID_CLOSE_STS' VALUE=\"N\">"); 
					out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
					
		
					out.println("</table>"); 
			
					query=" SELECT "+
						  " A.FINANCE_NO, "+ 
						  " DECODE(A.REPORT_STATUS,'GEN','Generated','APPROVED','Approved Level 1','APPROVED2','Approval Level 2','APPROVED3','Approval Level 3','CONFIRMED','Confirmed') "+
						 /* " DECODE("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'Normal Termination','Normal Termination Pending',"+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO)) "+*/
						  " FROM "+m_schema_name+".AF_CONFIRMATION_REPORT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
						  " WHERE A.FINANCE_NO = B.FINANCE_NO "+
						  //" AND B.APPLICATION_STATUS <> 'ACTIVATED' ";
							" AND TRUNC(A.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY') "+
							" AND TRUNC(A.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY') "+
							" ORDER BY A.FINANCE_NO  "+
							" ";
		
					rs = stmt.executeQuery(query);
					boolean more = rs.next();
				
				if(rs!=null){
								out.println("<br>");
					            out.println("<table border=0 class='table' width='100%'>");
					            out.println("<tr class='pdn_txtpos2'>");
					            out.println("<td width='5%' align='center'>Finance No.</td>");	  
								out.println("<td width='5%' align='center'>Current Status</td>");
								out.println("<td width='10%' align='center'>Required Status</td>");
								out.println("<td width='10%' align='center'>Comment</td>");		
								out.println("<td width='10%' align='center'> &nbsp; </td>"); // out.println("<td width='10%' align='center'>Generate</td>");	
							    out.println("</tr>");
		         
		             			 int j = 0;
					 			// int i=0;	
		             			 while(more){
									//	i++;
										
											if(j>0 && j%2==1){
												out.println("<tr class=tr_input >");
										    }
											else{
												out.println("<tr class=tr_input1 >");
											}
									
											out.println("<td width='5%' style= cursor:hand;cursor-color:blue onClick=show_transaction_history_new('','"+rs.getString(1)+"')><u>"+rs.getString(1)+"</u></td>"); 						
											out.println("<td width='5%' align='left' style= cursor:hand;cursor-color:blue onClick=drilldown('"+rs.getString(1)+"'); >"+rs.getString(2)+"</td>"); 	// 		
											out.println("<td width='5%' style='display:none'><input type='hidden' name='HID_APP_NO_"+j+"' value="+rs.getString(1)+"></td>");
											out.println("<td width='5%' align='center' style= cursor:hand;cursor-color:blue >"+
														" <select class='txt_input' name='TXT_APP_NO_"+j+"'>"+
														" <option value='NEW' Selected>Generation</option>"+
		                                        	///	" <option value='GEN' style='display:none'>Approval Level 1</option>"+
													//	" <option value='APPROVED' style='display:none'>Approval Level 2</option>"+
													//	" <option value='APPROVED2' style='display:none'>Approval Level 3</option>"+
													//	" <option value='APPROVED3' style='display:none'>Approval Level 4</option>"+					
		                                       			" </select> "+
														" </td>");	
									        out.println("<td width='10%' align='center' style= cursor-color:blue ><input type='text' name='APPROVE_COMMENT_"+j+"'></td>"); 
											out.println("<td width='10%' align='center' style= cursor-color:blue ><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_app("+j+");\" ></td>");					
											out.println("</tr>"); 
											more = rs.next();
											j=j+1;
											
			              }		
					out.println("<input type='hidden' name='noofrecodes' value='"+j+"'>");	
					out.println("<td ><div id=details></div></td></tr></table>");
					}
		
					out.println("</table>");
				    out.println("</FORM>"); 
						
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/factoring_drill_down.js'></SCRIPT>"); 
					out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
					out.println("</BODY>"); 
					out.println("</html>"); 
					out.flush();
			
			}
			
			
			}
		catch (Exception e) {
			try {
				conn.close();
			}catch (Exception eti) {}
			
			ByteArrayOutputStream ostr = new ByteArrayOutputStream();
			e.printStackTrace(new PrintWriter(ostr));
			
			ServletOutputStream out = res.getOutputStream();
			out.println(ostr.toString());
			out.close();	
		}
	}
}