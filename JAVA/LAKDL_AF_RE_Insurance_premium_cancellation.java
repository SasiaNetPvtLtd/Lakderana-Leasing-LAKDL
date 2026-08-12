//CREATED BY UDARA
//CREATED DATE 19-12-2012

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_RE_Insurance_premium_cancellation extends javax.servlet.http.HttpServlet { 

	/*
	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
		ServletOutputStream out = null;
		//String m_chksql = null;
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null;
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			conn = m_sn_methods.met_user_validate(req); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);   
		    nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String m_schema_name = m_sn_methods.schema_name;
		    String m_chksql=req.getParameter("chksql");
			
			if(m_chksql.equals("main_page")){
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Insurance Premium Cancellation </TITLE>"); 
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
					out.println("      var m_from_date = document.Form1.VAL_DAY1.value+'-'+document.Form1.VAL_MONTH1.value+'-'+document.Form1.VAL_YEAR1.value; ");
					out.println("      var m_to_date   = document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value; ");
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_premium_cancellation?chksql=details&status=\"+document.Form1.hid_status.value+\"&FINANCE_NO=\"+document.Form1.TXT_FIN_NO.value+\"&from_date=\"+m_from_date+\"&to_date=\"+m_to_date;"); 
					out.println("      load_interface(m_url,'NORM');");
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
					out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_premium_cancellation?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_premium_cancellation?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("   help_box.innerHTML=\" Insurance Premium Cancellation  - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Insurance Premium Cancellation  - \"+document.Form1.hid_status.value;"); 
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
					out.println("document.Form1.TXT_FIN_NO.value=\"\";");
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
					out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
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
					out.println("document.Form1.TXT_FIN_NO.value=\"\"");
					
					out.println(" details.innerHTML = ''; ");
					out.println("}");
					out.println("}");
					
					out.println("function help_button_1() {"); 
					out.println("    document.Form1.hid_help_type.value=\"1\";"); 
					//out.println("    m_sql = \"FinanceSql\";"); 
					//out.println("    m_sql = \"DueDateChangeFinanceSql\";");
					//out.println("    m_sql = \"m_help_TXT_SIEZER_CODE_LOAD_APP\";");
					out.println("    m_sql = \"m_help_TXT_INS_CANCEL\";"); 
					out.println("    m_criteria = document.Form1.TXT_FIN_NO.value+\"@\";");
					out.println("    HelpBox('1','10','0');"); 
					out.println("}");
					
					out.println(" function help_value_assign_1(oBj) {"); 
					out.println(" 	document.Form1.TXT_FIN_NO.value=oBj.valout[2];");
					out.println("   load_details()");
					out.println("}"); 
					
					out.println("function check_date_start(row){ ");
					out.println("  checkMonthLength(document.Form1.TXT_VALUE_DATE_DD,document.Form1.TXT_VALUE_DATE_MM,document.Form1.TXT_VALUE_DATE_YY);");
					out.println("}");

					out.println("function load_calendar(num,row_num) {");
      		        out.println("   document.Form1.hid_cal_date.value = num;");
			        out.println("   document.Form1.hid_cal_id.value   = row_num;"); 
					out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\");"); 
					out.println("}");
					
					out.println("function load_sysdate(){	"); 
					rs1= stmt1.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
					if(rs1.next()){
						out.println("document.Form1.VAL_DAY1.value='"+rs1.getString(1)+"';");
						out.println("document.Form1.VAL_MONTH1.value='"+rs1.getString(2)+"';");
						out.println("document.Form1.VAL_YEAR1.value='"+rs1.getString(3)+"';");
						out.println("document.Form1.VAL_DAY2.value='"+rs1.getString(1)+"';");
						out.println("document.Form1.VAL_MONTH2.value='"+rs1.getString(2)+"';");
						out.println("document.Form1.VAL_YEAR2.value='"+rs1.getString(3)+"';");
					}
					out.println("}"); 
			
					out.println("function load_c_date(val) {");
					out.println("var date1='' ");
					out.println("var date2='' ");
					out.println("  if(document.Form1.hid_cal_date.value=='2'){"); 
					out.println("v_date=val.substr(0,val.indexOf('-'));");
					out.println("if(v_date.length<2)");
					out.println("v_date=0+v_date");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("v_month=val.substr(0,val.indexOf('-'));");
					out.println("if(v_month.length<2)");
					out.println("v_month=0+v_month");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("     document.Form1.VAL_DAY1.value=v_date;");
					out.println("     document.Form1.VAL_MONTH1.value=v_month;");
					out.println("     document.Form1.VAL_YEAR1.value=val;");
					out.println("date1=v_date+'-'+v_month+'-'+val;");
					out.println("document.Form1.hid_from_date.value=date1");
					//	out.println("alert(document.Form1.hid_from_date.value);");
					out.println("}");
					
					out.println("  if(document.Form1.hid_cal_date.value=='3'){"); 
					out.println("v_date=val.substr(0,val.indexOf('-'));");
					out.println("if(v_date.length<2)");
					out.println("v_date=0+v_date");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("v_month=val.substr(0,val.indexOf('-'));");
					out.println("if(v_month.length<2)");
					out.println("v_month=0+v_month");
					out.println("val=val.substr(val.indexOf('-')+1,val.length);");
					out.println("     document.Form1.VAL_DAY2.value=v_date;");
					out.println("     document.Form1.VAL_MONTH2.value=v_month;");
					out.println("     document.Form1.VAL_YEAR2.value=val;");
					out.println("date2=document.Form1.VAL_DAY2.value+'-'+document.Form1.VAL_MONTH2.value+'-'+document.Form1.VAL_YEAR2.value;");
					out.println("document.Form1.hid_to_date.value=date2");
					out.println("}");
					
					out.println("}");
					
					
					out.println("function check_date(objdd,objmm,objyy) {"); 						
					out.println("  if((objdd.value !=\"\")&&(objmm.value !=\"\")&&(objyy.value !=\"\")){");
					out.println("  checkMonthLength(objdd,objmm,objyy);");
					//out.println("  validate_date(objdd,objmm,objyy,document.Form1.HID_SYS_VAL_DAY,document.Form1.HID_SYS_VAL_MONTH,document.Form1.HID_SYS_VAL_YEAR);");
					out.println("}");
					out.println("}");
					
					out.println("function sysdate() {");
					out.println("document.Form1.hid_text.value=\"J6\"");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_display_enter_lease_sysdate\";");
					out.println("load_interface(m_url,'XML');");
					out.println("}");
					//---------------------------Added by Sandun on 11-03-2009----------------------------
				
					
					out.println("function validate_data(){"); 
					out.println("if(document.Form1.TXT_FIN_NO.value==\"\"){  "); 
					out.println("DIV_TXT_FIN_NO.style.color='red';");
					out.println("return false;"); 
					out.println("}else{"); 
					out.println("return true;"); 
					out.println("}"); 
					out.println("}"); 
					 
					out.println("function disabled_all(){"); 
					out.println("   for(var y=0;y<parseInt(document.Form1.REC_COUNT.value);y++){");
					out.println("      document.Form1.elements[\"CHK_\"+y].disabled      =false;");	
					out.println("   }");
					out.println("}");
			
					out.println("function count_receipts(){ ");			
					out.println("count=0;");		
					out.println("for(i=0;i<parseInt(document.Form1.REC_COUNT.value);i++){");	
					out.println("m_chk=\"CHK_\"+i;");
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
			    
					
					
					// added by udara on 22-11-2012
					out.println("function before_submit(){ "); 
					//out.println("if(validate_data()){"); 
					out.println("ckeck_data();");
					out.println("if(b_flag==0){");
					out.println("disabled_all();");
					out.println("		if(confirm(\"Are you sure you want save data\")){ "); 
					//out.println("		if(validate_data()){");	
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_Insurance_premium_cancellation_save';");  
					out.println("		document.Form1.submit();	"); 
					//out.println("		}"); 
					out.println("		}"); 
					out.println("		}");  
					//out.println("		}"); 
					//out.println("else{");
					//out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
					//out.println("} "); 			
					out.println("} "); 
				
					
				
				
				out.println("function check_status_app(row_num) {");
				
				//out.println(" alert(document.Form1.elements[\"HID_APP_NO_\"+row_num].name); ");
				
				out.println("   if(document.Form1.elements[\"CHK_\"+row_num].checked){");
				out.println("         	document.Form1.elements[\"CHK_\"+row_num].value     = \"on\";");
				out.println("   }");
				out.println("   else{");				
				out.println("      document.Form1.elements[\"CHK_\"+row_num].value =\"off\";");
				out.println("   }");
				
				out.println("}");
				
				
				
				out.println("function chng_butt(){");
				out.println("if(document.Form1.hid_status.value==\"Edit\"){");
				out.println("document.Form1.MAIN_BUT_3.value=\"Generate\"; ");	
				out.println("}");	
				out.println("}");	
				
				
				
				
					//-------------------------------end---------------------------------------------------------------------
					out.println("</script>"); 
					//out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"sysdate()\">"); 
					out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sysdate();\">"); // load_details();
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
					out.println("<INPUT TYPE='Hidden' NAME='HID_OPTION' VALUE=\"NEW\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_DD' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_MM' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='HID_VAL_YY' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_from_date' VALUE=\"\">");
					out.println("<INPUT TYPE='Hidden' NAME='hid_to_date' VALUE=\"\">");
					
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Insurance Premium Cancellation </td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td  height='10px' class='pdn_txtpos'>"); 
					out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
					out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
					//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  //modified by nuwan  de silva 20-08-07
					out.println("<td width='10%'> &nbsp; </td>"); 
					out.println("<td width='6%'></td>");  
					out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'    onClick='before_submit()' name=\"MAIN_BUT_3\" value=\"Save\"></td>");  
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


					out.println("<tr >"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_FIN_NO' class=div_input>Finance No</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='25' size='25' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" > ");
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr >"); 
					out.println("<td width='10%' ID=VDATE >From Date</td>");
					out.println("<td width='40%' ><input name=\"VAL_DAY1\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onblur=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_MONTH1\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)> ");
					out.println("    <input name=\"VAL_YEAR1\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY1,document.Form1.VAL_MONTH1,document.Form1.VAL_YEAR1)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
					out.println("</td>");
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					out.println("<tr >"); 
					out.println("<td width='10%' ID=VDATE>To Date</td>");
					out.println("<td width='40%' ><input name=\"VAL_DAY2\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_MONTH2\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)> ");
					out.println("    <input name=\"VAL_YEAR2\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_date(document.Form1.VAL_DAY2,document.Form1.VAL_MONTH2,document.Form1.VAL_YEAR2)><a href style='{cursor:hand; }' onclick=load_calendar('3')>   Calendar</a> ");
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

			
				else if(m_chksql.equals("details")){
					
					String	m_app_no     = req.getParameter("FINANCE_NO");
					String	m_from_date  = req.getParameter("from_date");
					String	m_to_date    = req.getParameter("to_date");
				
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	

					/*
					rs=stmt.executeQuery(" "+ 								
									" select a.FINANCE_NO, "+
									" a.REF_DEBIT_NOTE_NO, "+
									" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)) "+
									" from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA a, "+m_schema_name+".AF_CO_PRO_INVOICE B "+
									" where a.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
									" and a.FINANCE_NO LIKE '"+m_app_no+"%'  "+
									" and B.SETTELE_AMOUNT = 0  "+
									" and TRUNC(A.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
									" and TRUNC(A.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
									" order by a.FINANCE_NO,B.ENT_DATE  "+
								" ");
					*/
					
					rs=stmt.executeQuery(" "+ 								
									" select a.FINANCE_NO, "+ // 1
									" NVL(a.REF_DEBIT_NOTE_NO,'-'), "+ // 2
									" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)), "+ // 3
									" NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-'), "+ // 4
									" B.ENT_DATE ENT_DATE, "+ // 5
									" A.POLICY_NO,  "+ // 6
									" A.DEBIT_NOTE_NO "+ // 7
									" from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA a, "+m_schema_name+".AF_CO_PRO_INVOICE B "+
									" where a.REF_DEBIT_NOTE_NO = B.INVOICE_NO "+
									" and a.FINANCE_NO LIKE '"+m_app_no+"%'  "+
									" and B.SETTELE_AMOUNT = 0  "+
									" and TRUNC(A.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
									" and TRUNC(A.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
									//" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'LICENSEE'  "+ // commented by udara 04-03-2015
									
									// added by udara 04-03-2015
									" and a.REF_DEBIT_NOTE_NO NOT IN ( "+
									
										" SELECT X.REF_NO "+
										" FROM "+m_schema_name+".AF_RE_ACC_SUS_PAYMENT X, "+m_schema_name+".AF_CR_PRO_SET_PAY_BREAKDOWN Y, "+m_schema_name+".AF_RE_PRO_SETTLMENT_PAYMENT Z "+
										" WHERE X.SUS_REF_NO = Y.SUS_REF_NO "+  
										" AND   Z.PAYMENT_NO = Y.PAYMENT_NO "+
										" AND   X.SUSPENSE_ENTRY_TYPE = 'INSURANCE' "+
										//" AND   "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(X.REF_NO)) = '"+m_app_no+"' "+
										" AND   "+m_schema_name+".AF_CO_GET_FINANCE_NO("+m_schema_name+".AF_CO_GET_FIN_NO(X.REF_NO)) = a.FINANCE_NO "+ // udara 09-03-2015
										" AND Z.PROCESS_STATUS <> 'CANCEL' "+ // added by udara 10-03-2016
									" ) "+
									
									// end by udara 04-03-2015 
										
									//" order by a.FINANCE_NO,B.ENT_DATE  "+
									
									" UNION "+
									
									" select a.FINANCE_NO, "+ // 1
									" NVL(a.REF_DEBIT_NOTE_NO,'-'), "+ // 2
									" "+m_schema_name+".AF_CO_GET_CLI_NAME("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)), "+ // 3
									" NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-'), "+ // 4
									" A.ENT_DATE ENT_DATE, "+ // 5
									" A.POLICY_NO,  "+ // 6
									" A.DEBIT_NOTE_NO "+ // 7
									" from "+m_schema_name+".AF_IS_PRO_ASET_INSUR_DETA a "+
									" where a.FINANCE_NO LIKE '"+m_app_no+"%'  "+
									" and TRUNC(A.ENT_DATE) >= TO_DATE('"+m_from_date+"','DD-MM-YYYY')  "+
									" and TRUNC(A.ENT_DATE) <= TO_DATE('"+m_to_date+"','DD-MM-YYYY')  "+
									//" and NVL("+m_schema_name+".AF_CO_GET_INSURENCE_DONE_BY("+m_schema_name+".AF_CO_GET_APPLICATION_NO(a.FINANCE_NO)),'-') = 'CLIENT'  "+
									" AND REF_DEBIT_NOTE_NO IS  NULL "+ // added by udara 26-02-2015
									//" order by a.FINANCE_NO,A.ENT_DATE  "+
									" order by ENT_DATE "+
									
								" ");
						
						
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				if(more){		
				
				out.println("<br>");	
				out.println("<b><HR>");	
				out.println("<br>");	
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	      		out.println("<tr class=pdn_txtpos2 align='left'>");
				out.println("<td  width='10%' > Fiance No </td>");
				out.println("<td  width='20%' > Client Name </td>");
				out.println("<td  width='10%' > Client Type </td>");
	      		out.println("<td  width='10%' > Invoice No </td>");
				out.println("<td  width='10%' > Policy No </td>");
				out.println("<td  width='10%' > Debit Note Ref </td>");
				//out.println("<td  width='20%' > Seizer Name </td>");
				//out.println("<td  width='20%' > Seizer Address </td>");
				//out.println("<td  width='10%' > Telephone </td>");
				//out.println("<td  width='10%' > Mobile </td>");
				out.println("<td  width='20%' > Cancellation Remark </td>"); // added by udara 22-08-2014
				out.println("<td  width='5%'  > Select </td>");
				out.println("<td  width='*%'  > &nbsp; </td>");
				
				while(more){
						
				
					if(j>0 && j%2==1){
	              		out.println("<tr class=tr_input1 >");
					}
					else{					
	               		out.println("<tr class=tr_input >");
					}
	
				out.println("<td  width='10%' > <input type='hidden' name='HID_FIN_NO_"+j+"'    id='HID_FIN_NO_"+j+"'    value ='"+rs.getString(1)+"' > "+rs.getString(1)+" </td>"); 
				out.println("<td  width='20%' >  "+rs.getString(3)+" </td>"); 
				out.println("<td  width='10%' > <input type='hidden' name='HID_INS_DONE_BY_"+j+"' id='HID_INS_DONE_BY_"+j+"' value ='"+rs.getString(4)+"' > "+rs.getString(4)+" </td>"); 
				out.println("<td  width='10%' > <input type='hidden' name='HID_INV_NO_"+j+"'      id='HID_INV_NO_"+j+"'      value ='"+rs.getString(2)+"' > "+rs.getString(2)+" </td>"); 
				out.println("<td  width='10%' > <input type='hidden' name='HID_POLICY_NO_"+j+"'   id='HID_POLICY_NO_"+j+"'   value ='"+rs.getString(6)+"' > "+rs.getString(6)+" </td>"); 
				out.println("<td  width='10%' > <input type='hidden' name='HID_DB_REF_NO_"+j+"'   id='HID_DB_REF_NO_"+j+"'   value ='"+rs.getString(7)+"' > "+rs.getString(7)+" </td>"); 
				//out.println("<td  width='20%' >  "+rs.getString(3)+" </td>"); 
                //out.println("<td  width='20%' >  "+rs.getString(4)+" </td>");
				//out.println("<td  width='20%' >  "+rs.getString(5)+" </td>");
				//out.println("<td  width='20%' >  "+rs.getString(6)+" </td>");
				
				out.println("<td  width='20%' > <input  type='text' name=TXT_REMARK_"+j+" id=TXT_REMARK_"+j+" maxlength='300' size='50'  value=\"\" > </td>"); // added by udara 22-08-2014
				out.println("<td  width='5%' align=center><input type='checkbox' name='CHK_"+j+"' value ='off' onclick=\"check_status_app("+j+");\" ></td>");
				out.println("<td  width='*%'  > &nbsp; </td>");
				
				more=rs.next();
				j=j+1;
				}
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				}
				
				
				
				
			} // end if details
				
				
			
			
			
			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
	
