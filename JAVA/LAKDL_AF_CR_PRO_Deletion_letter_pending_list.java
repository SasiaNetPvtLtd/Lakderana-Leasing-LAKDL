//Created By Minal on 17-06-2015 for #17087
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_CR_PRO_Deletion_letter_pending_list extends javax.servlet.http.HttpServlet { 


	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
		// added by udara 18-05-2017
		ServletOutputStream out = null;
		//String m_chksql = null;
		Connection conn = null;
		Statement stmt= null,stmt1= null,stmt2= null;
		java.text.NumberFormat nf= null,nf1= null;
		ResultSet rs= null,rs1= null,rs2= null;
		// end by udara 18-05-2017
		 
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
			String m_username = m_sn_methods.username; // added by udara 13-05-2015
			

			
			if(m_chksql.equals("main_page")){
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Deletion Letter Pending List</TITLE>"); 
					out.println("</HEAD>"); 
					out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
					out.println("<SCRIPT language=\"JavaScript\">");
					out.println("var m_flag=0");

					
					out.println("function viewUploadDocs(fin_no) {");
					out.println("   m_url = \""+m_class_url+"/"+m_fschema_name+"AF_MK_Document_Upload_New_View?chksql=viewAllDocuments&finance_no=\"+fin_no; ");
					out.println("   window.open(m_url); ");		
					out.println("   }");
					 
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
					//out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_pending_list?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value;");  // commented by udara 18-01-2018
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_pending_list?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value+\"&sort_by=NVL(A.MOD_DATE,A.ENT_DATE)&order_by_type=ASC\";"); // added by udara 18-01-2018   
					out.println("      load_interface(m_url,'NORM');");
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
					out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_pending_list?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_pending_list?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("   help_box.innerHTML=\" Deletion Letter Pending List - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Deletion Letter Pending List - \"+document.Form1.hid_status.value;"); 
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

					
					out.println("function show_transaction_history_new(val,val2){ "); 
				    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				    out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				    out.println("}");
					
					out.println("function print_letter(row_id){");
					
					out.println("m_save_msg = \"Do you want to print the Deletion letter?\";");
					
					out.println("m_cr_status      = document.getElementById('CR_STATUS_'+row_id).value; ");
					out.println("m_application_no = document.getElementById('APPLICATION_NO_'+row_id).value; ");
					out.println("m_client_code    = document.getElementById('CLIENT_CODE_'+row_id).value; ");
					out.println("m_finance_no     = document.getElementById('FINANCE_NO_'+row_id).value; "); 
					
					// added by udara 25-11-2021
					out.println("var m_checked_status = ''; "); 
					
					out.println("if(document.getElementById('CANCEL_FROM_SCREEN_'+row_id).checked==true){ ");
					out.println("   m_checked_status='Y'; ");
					out.println("}");
					
					// end by udara 25-11-2021
					
					out.println("if(confirm(m_save_msg)){");		
					
					out.println("   if(m_cr_status=='SAFE'){ ");				
					//out.println("	     m_url = \""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=generate&application_no="+m_application_no+"&client_code="+m_client_no+"&finance_no="+m_fin_no1+"&document_code=DELE_LETT&print=TRUE&del_code=\";"); 
					//out.println("        m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=generate_process&application_no='+m_application_no+'&client_code='+m_client_code+'&finance_no='+m_finance_no+'&document_code=DELE_LETT&print=TRUE&del_code=';");  // commented by udara 25-11-2021
					out.println("        m_url='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_new_process?chksql=generate_process&application_no='+m_application_no+'&client_code='+m_client_code+'&finance_no='+m_finance_no+'&document_code=DELE_LETT&print=TRUE&del_code=&checked_status='+m_checked_status;");  // added by udara 25-11-2021
					out.println("        popupwin=window.open(m_url,'displayWindow1_del_let_pending_list','left=110,top=110,width=720,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=0');"); 				
					out.println("        new_window();"); 
					out.println("   } ");
					out.println("   else{ ");
					out.println("         alert('You cant print the letter. CR book of selected contract not in safe'); ");
					out.println("   } ");
					
					out.println("} ");
					
					out.println("}");
					
					out.println("function check_status_app(row_num) {");

					out.println("   if(document.Form1.elements[\"CANCEL_FROM_SCREEN_\"+row_num].checked){");
					out.println("         	document.Form1.elements[\"CANCEL_FROM_SCREEN_\"+row_num].value     = \"on\";");
					out.println("   }");
					out.println("   else{");				
					out.println("      document.Form1.elements[\"CANCEL_FROM_SCREEN_\"+row_num].value =\"off\";");
					out.println("   }");
					
					out.println("}");
					
					
					out.println("function before_submit(){ "); 
					out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Deletion_letter_pending_list_save';");  
					out.println("		document.Form1.submit();	"); 
					out.println("} "); 
				

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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Deletion Letter Pending List</td>"); 
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
					out.println("<td width='10%'>Approval Level</td>"); 
					out.println("<td width='40%'><select class='txt_input' name='TXT_APP_NO'>");
					out.println("<option value='ALL'>All</option>"); // added by udara 31-08-2015
					out.println("<option value='GEN'>Approval Level 1</option>");
					out.println("<option value='APPROVED'>Approval Level 2</option>");
					out.println("<option value='APPROVED2'>Approval Level 3</option>");
					out.println("<option value='APPROVED3'>Approval Level 4</option>");
					out.println("<option value='CONFIRMED'>Confirmed</option>");
					out.println("</select></td>"); 
					out.println("<tr class=tr_input>");
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
		       		//out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
					out.println("</tr>");
					
					// added by udara 31-08-2015
					
					out.println("<tr >"); 
					out.println("<td width='10%' ><DIV id='DIV_TXT_APP_NO' class=div_input>Finance No</div></td>"); 
					out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FIN_NO' maxlength='30' size='30' >"); 
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_1' value=\"...\" onClick=\"help_button_1()\" > ");
					out.println("<input class='but_input' type='button' name='BUT_BUTTON_S' value=\"Search\" onClick=\"load_details()\" > ");
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

			
				else if(m_chksql.equals("details")){

			
					String m_app_no  = req.getParameter("APP_STAT");
					String m_from_date= req.getParameter("from_date");
					String m_to_date = req.getParameter("to_date");
					String m_fin_no = req.getParameter("FIN_NO"); 
					
					String m_sort_by = req.getParameter("sort_by"); // added by udara 18-01-2018
					String m_order_by_type = req.getParameter("order_by_type"); // added by udara 18-01-2018
				
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	
				

						rs=stmt.executeQuery(" "+ 								
											/*
										   " SELECT "+
											    " A.FINANCE_NO FINANCE_NO, "+
												" A.CLIENT_CODE CLIENT_CODE, "+
												" A.APPLICATION_NO APPLICATION_NO, "+
											    " B.VEHICLE_NO VEHICLE_NO, "+
											    " TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY') TERMINATED_DATE, "+
											    " "+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER) TERMINATION_DONE_BY, "+
											    " NVL("+m_schema_name+".AF_CO_GET_CR_LAST_WITHDRAW(A.FINANCE_NO),'-') LAST_WITHDRAW, "+
											    " B.CR_STATUS CR_STATUS "+
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A, "+m_schema_name+".AF_PRO_CR_BOOK B "+
													" WHERE A.FINANCE_NO LIKE '%"+m_fin_no+"%' "+
													" AND A.FINANCE_NO = B.FINANCE_NO "+
													" AND A.ACTIVE_STATUS = 'TERM_CHECK' "+
													" AND A.FINANCE_NO NOT IN ( "+
													" SELECT FINANCE_NO "+
													" FROM "+m_schema_name+".AF_DEL_LETTER_PROCESS "+
													" ) "+
													" AND A.APPLY_DATE >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
												    " AND A.APPLY_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
													*/
											
											// commented by udara 06-10-2021
											/*
											" SELECT "+
											    " A.FINANCE_NO FINANCE_NO, "+
												" A.CLIENT_CODE CLIENT_CODE, "+
												" A.APPLICATION_NO APPLICATION_NO, "+
												" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_2(A.APPLICATION_NO),'-') VEHICLE_NO, "+ 
											    " TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY') TERMINATED_DATE, "+
											    " "+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER) TERMINATION_DONE_BY, "+
											    " NVL("+m_schema_name+".AF_CO_GET_CR_LAST_WITHDRAW(A.FINANCE_NO),'-') LAST_WITHDRAW, "+
												" NVL("+m_schema_name+".AF_GET_CR_BOOK_SAFE_STATUS(A.FINANCE_NO),'-') CR_STATUS, "+
												" NVL("+m_schema_name+".AF_CO_GET_CR_BOOK_STATUS(A.FINANCE_NO),'-') CR_STATUS_DESC "+ 
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A "+
													" WHERE A.FINANCE_NO LIKE '%"+m_fin_no+"%' "+
													" AND A.ACTIVE_STATUS = 'TERM_CHECK' "+
													" AND A.FINANCE_NO NOT IN ( "+
													" SELECT FINANCE_NO "+
													" FROM "+m_schema_name+".AF_DEL_LETTER_PROCESS "+
													" WHERE FINANCE_NO = A.FINANCE_NO "+
													" ) "+
													
													" AND A.FINANCE_NO NOT IN ( "+
													" SELECT FINANCE_NO "+
													" FROM "+m_schema_name+".AF_DEL_LET_PEND_CANCEL "+
													" WHERE FINANCE_NO = A.FINANCE_NO "+
													" ) "+
													
													" AND A.APPLY_DATE >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
												    " AND A.APPLY_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
													" ORDER BY A.APPLY_DATE DESC "+
													*/
													
											
											" SELECT "+
											    " A.FINANCE_NO FINANCE_NO, "+
												" A.CLIENT_CODE CLIENT_CODE, "+
												" A.APPLICATION_NO APPLICATION_NO, "+
												" NVL("+m_schema_name+".AF_CO_GET_VEHICLE_NO_2(A.APPLICATION_NO),'-') VEHICLE_NO, "+ 
											    " TO_CHAR(A.APPLY_DATE,'DD-MM-YYYY') TERMINATED_DATE, "+
											    " "+m_schema_name+".AF_CO_GET_USER_NAME(A.MOD_USER) TERMINATION_DONE_BY, "+
											    " NVL("+m_schema_name+".AF_CO_GET_CR_LAST_WITHDRAW(A.FINANCE_NO),'-') LAST_WITHDRAW, "+
												" NVL("+m_schema_name+".AF_GET_CR_BOOK_SAFE_STATUS(A.FINANCE_NO),'-') CR_STATUS, "+
												" NVL("+m_schema_name+".AF_CO_GET_CR_BOOK_STATUS(A.FINANCE_NO),'-') CR_STATUS_DESC "+ 
													" FROM "+m_schema_name+".AF_CR_PRO_TERMINATION A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
													" WHERE A.FINANCE_NO LIKE '%"+m_fin_no+"%' "+
													" AND A.FINANCE_NO = B.FINANCE_NO "+
													" AND B.TRANSACTION_TYPE = 'HIREPURCH'   "+
													" AND A.ACTIVE_STATUS = 'TERM_CHECK' "+
													" AND A.FINANCE_NO NOT IN ( "+
													" SELECT FINANCE_NO "+
													" FROM "+m_schema_name+".AF_DEL_LETTER_PROCESS "+
													" WHERE FINANCE_NO = A.FINANCE_NO "+
													" ) "+
													
													" AND A.FINANCE_NO NOT IN ( "+
													" SELECT FINANCE_NO "+
													" FROM "+m_schema_name+".AF_DEL_LET_PEND_CANCEL "+
													" WHERE FINANCE_NO = A.FINANCE_NO "+
													" ) "+
													
													" AND A.APPLY_DATE >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
												    " AND A.APPLY_DATE <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
													" ORDER BY A.APPLY_DATE DESC "+
													

											" ");

					
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</B></td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				
				int counts = 0; 
				
				if(more){		
				
				out.println("<br>");	
				out.println("<b><HR>");	
				out.println("<br>");	
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	      		out.println("<tr class=pdn_txtpos2 align='left'>");
					
					out.println("<td  width='1%'  > No. </td>"); 
					out.println("<td  width='10%' > Contract No </td>");
					out.println("<td  width='10%' > Vehicle Number </td>");
					out.println("<td  width='10%' > Terminated Date </td>");
					out.println("<td  width='10%' > Termination Processing Done By </td>");
					out.println("<td  width='10%' > CR Status </td>");
					out.println("<td  width='10%' > CR Status Date </td>"); 
					out.println("<td  width='10%' > Print </td>");
					out.println("<td  width='10%' > Cancel From Screen </td>");
			

				out.println("</tr>");
				while(more){
					
					counts = counts + 1; 
			
			    out.println("<tr>");
				out.println("<td  width='1%' >"+counts+"</td>"); 
				out.println("<td  width='10%' STYLE='{text-align:left;cursor:pointer;}' onClick=show_transaction_history_new('','"+rs.getString("FINANCE_NO")+"') ><u>"+rs.getString("FINANCE_NO")+"</u> </td>");
				out.println("<td  width='10%' > "+rs.getString("VEHICLE_NO")+" </td>"); 
				out.println("<td  width='10%' > "+rs.getString("TERMINATED_DATE")+" </td>");
				out.println("<td  width='10%' > "+rs.getString("TERMINATION_DONE_BY")+" </td>");
				out.println("<td  width='10%' > "+rs.getString("CR_STATUS_DESC")+" </td>"); // CR_STATUS
				out.println("<td  width='10%' > "+rs.getString("LAST_WITHDRAW")+" </td>");
				
				
				if(rs.getString("CR_STATUS").equals("SAFE")){
					out.println("<td  width='10' align=center>");
					out.println("    <input type='button' name='PRINT_LETTER_"+j+"'   value='Print' class='but_input' onClick=print_letter('"+j+"') >");
					out.println("    <input type='hidden' name='CR_STATUS_"+j+"'      id='CR_STATUS_"+j+"'      value='"+rs.getString("CR_STATUS")+"' > ");
					out.println("    <input type='hidden' name='APPLICATION_NO_"+j+"' id='APPLICATION_NO_"+j+"' value='"+rs.getString("APPLICATION_NO")+"' > ");
					out.println("    <input type='hidden' name='CLIENT_CODE_"+j+"'    id='CLIENT_CODE_"+j+"'    value='"+rs.getString("CLIENT_CODE")+"' > ");
					out.println("    <input type='hidden' name='FINANCE_NO_"+j+"'     id='FINANCE_NO_"+j+"'     value='"+rs.getString("FINANCE_NO")+"' > ");
					out.println("</td>");
				}
				else{
					out.println("<td  width='10' align=center>");
					out.println("    <input type='button' name='PRINT_LETTER_"+j+"'   value='Print' class='but_input' onClick=print_letter('"+j+"') disabled >");
					out.println("    <input type='hidden' name='CR_STATUS_"+j+"'      id='CR_STATUS_"+j+"'      value='"+rs.getString("CR_STATUS")+"' > ");
					out.println("    <input type='hidden' name='APPLICATION_NO_"+j+"' id='APPLICATION_NO_"+j+"' value='"+rs.getString("APPLICATION_NO")+"' > ");
					out.println("    <input type='hidden' name='CLIENT_CODE_"+j+"'    id='CLIENT_CODE_"+j+"'    value='"+rs.getString("CLIENT_CODE")+"' > ");
					out.println("    <input type='hidden' name='FINANCE_NO_"+j+"'     id='FINANCE_NO_"+j+"'     value='"+rs.getString("FINANCE_NO")+"' > ");
					out.println("</td>");
				}
				
				out.println("<td  width='10' align=center>");
				out.println("    <input type='checkbox' name='CANCEL_FROM_SCREEN_"+j+"' id='CANCEL_FROM_SCREEN_"+j+"'   value='off'  onclick=\"check_status_app("+j+");\" >");
				out.println("</td>");
				
				
				more=rs.next();
				j=j+1;
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				}
			} // end if details
					
					
					
			// added by udara 18-05-2017
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs1!=null){try{rs1.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt1!=null){try{stmt1.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
			// end by udara 18-05-2017		
					
			//*****************************************************************************************************************
		}catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
	