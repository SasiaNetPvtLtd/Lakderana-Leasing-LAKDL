//Created By Minal on 17-06-2015 for #17087
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 

public class LAKDL_AF_MISF_Confirmation_Report_approval_view extends javax.servlet.http.HttpServlet { //LAKDL_AF_CR_PRO_Enter_Deletion_letter_details_app

	// commented by udara 18-05-2017 
	/*
	ServletOutputStream out = null;
	public String m_chksql;
	Connection conn;
	Statement stmt,stmt1,stmt2;
	java.text.NumberFormat nf,nf1;
	public ResultSet rs,rs1,rs2;
	*/
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // commented by udara 18-05-2017
	public void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { // added by udara 18-05-2017
		
		
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
			
			//out.println(" m_chksql " + m_chksql);
			
			//if( m_chksql.equals("") || m_chksql==null )
			//	m_chksql = "main_page";
			
			if(m_chksql.equals("main_page")){
				stmt2 = conn.createStatement ();
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
					out.println("<HTML>"); 
					out.println("<HEAD>"); 
					out.println("<TITLE>Confirmation Report Approval View</TITLE>"); 
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
					//out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value;");  // commented by udara 18-01-2018
					out.println("      m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value+\"&sort_by=NVL(A.MOD_DATE,A.ENT_DATE)&order_by_type=ASC\";"); // added by udara 18-01-2018   
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
					out.println("			window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=main_page';"); 
					out.println("		}"); 
					out.println("}"); 

					out.println("function new_window(){	"); 
					out.println("   window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=main_page';"); 
					out.println("}"); 
					out.println(""); 
					out.println(""); 
					
										
					out.println("function load_roll_value(m_val){"); 
					out.println("   help_box.innerHTML=\" Confirmation Report Approval View - \"+m_val;"); 
					out.println("}"); 
					out.println(""); 

					out.println("function load_roll_out_value(){");
					out.println("help_box.innerHTML=\" Confirmation Report Approval View - \"+document.Form1.hid_status.value;"); 
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
					
					// added by udara 14-07-2017
					out.println("function load_details_report_new(val){"); 
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Detail_Snap?chksql=main_page&snap_view_status=N&compare_status=Y&generate_status=R&view_only_status=N&snap_position=APPROVE2&finance_no=\"+val; ");
					out.println("window.open(m_url,'popupwin_conf_rpt_app_1','left=110,top=110,width=750,height=800,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1')");
					out.println("} "); 
					// end by udara 14-07-2017
					
					// added by udara 28-07-2017
					out.println("function snap_drilldown(val){ "); 
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_Snap_Drill?chksql=drill_down&finance_no='+val;"); 
					out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("}");
					// end by udara 28-07-2017
					
					out.println("function show_transaction_history_new(val,val2){ "); 
				    out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				    out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				    out.println("}");
				
					out.println("function drilldown(val,val2){ "); 
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=drill_down&finance_no='+val+'&app_status='+val2;"); 
					out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("}");
					
					
					out.println("var mm_order_by_type = 'DESC';  ");
					out.println("function sort_data(m_sort_col,m_order_by_type) {");					

					out.println("	if(mm_order_by_type=='DESC'){");
					out.println("	    mm_order_by_type = 'ASC'; "); 			    
					out.println("   }else{");
					out.println("       mm_order_by_type = 'DESC'; ");
					out.println("   }");
					
					out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Confirmation_Report_approval_view?chksql=details&from_date=\"+m_from_date+\"&to_date=\"+m_to_date+\"&APP_STAT=\"+document.Form1.TXT_APP_NO.value+\"&FIN_NO=\"+document.Form1.TXT_FIN_NO.value+\"&sort_by=\"+m_sort_col+\"&order_by_type=\"+mm_order_by_type;"); // added by udara 18-01-2018   
					out.println("   load_interface(m_url,'NORM');");
					
					out.println("}");
					
					// added by udara 24-07-2018
					out.println("function insurance_details_app_drill(val2){ "); 
					out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Asset_Insurnce_Detail_app_drill?chksql=insurance_details_app_drill&applicaton_no='+val2;"); 
					out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					out.println("}");
					// end by udara 24-07-2018
					
					
					
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
					out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Confirmation Report Approval View</td>"); 
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
					
					String m_user_branch = "";
			
					rs=stmt.executeQuery(" "+
						" SELECT "+ 
							" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
								" FROM DUAL ");					
					if(rs.next()){
						m_user_branch = rs.getString(1);
					}
					
					if(m_user_branch.equals("HO")){
						m_user_branch = "";
					}
			
					String m_app_no  = req.getParameter("APP_STAT");
					String m_from_date= req.getParameter("from_date");
					String m_to_date = req.getParameter("to_date");
					String m_fin_no = req.getParameter("FIN_NO"); 
					
					String m_sort_by = req.getParameter("sort_by"); // added by udara 18-01-2018
					String m_order_by_type = req.getParameter("order_by_type"); // added by udara 18-01-2018
				
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	
				
					
					
					// added by udara 31-08-2015
					if(m_app_no.equals("ALL")){
						
						rs=stmt.executeQuery(" "+ 								
									" SELECT "+
										" A.FINANCE_NO, "+ // 1
										" NVL(DECODE(A.NEW_REGEN_STATUS,'N','New','R','Re-Generated','M','Modified','Generated'),'-'), "+    // 2
										//" NVL(APP_USER,A.ENT_USER), "+ // 3//Commeneted by Jithendra 24-11-2016 for SR#22273 
										"(SELECT NAME FROM "+m_schema_name+".CO_CO_MAS_USER  WHERE USER_ID= NVL(APP_USER,A.ENT_USER)), "+ // 3//Added by Jithendra 24-11-2016 for SR#22273 
										" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'DD-MM-YYYY'),'-'), "+ // 4
										" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'HH24:MI:SS'),'-'), "+ // 5
										//" NVL(APP_COMMENT,CONF_RPT_COMMENT) APP_COMMENT, "+ // 6
										" SUBSTR(NVL(APP_COMMENT,CONF_RPT_COMMENT),0,40) APP_COMMENT,  "+  // " SUBSTR(NVL(APP_COMMENT,CONF_RPT_COMMENT),0,20) APP_COMMENT,  "+ 
										//" NVL(A.APP_COMMENT,'-'), "+ //6
										" NVL(A.APP_COMMENT_2,'-'), "+ //7
										" NVL(A.APP_COMMENT_3,'-'), "+ //8
										" NVL(A.APP_COMMENT_4,'-'), "+ //9
										" DECODE(A.REPORT_STATUS,'GEN','Approval Level 1','APPROVED','Approval Level 2','APPROVED2','Approval Level 3','APPROVED3','Approval Level 4','CONFIRMED','Confirmed'), "+ // 10
										" NVL("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'-'), "+ // 11 
										
										" DECODE(B.APPLICATION_STATUS,'ACTIVATED', "+
											 " (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH24:MI:SS') "+ // HH:MI:SS changed 24hrs format by udara 11-04-2019
																	         " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	         " WHERE APPLICATION_NO = B.APPLICATION_NO "+
																	         " AND STAGE = 'PO_APP' "+
								                           " ), "+ 
																		
											 " (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH24:MI:SS')  "+ // HH:MI:SS changed 24hrs format by udara 11-04-2019
																	         " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																	         " WHERE APPLICATION_NO = B.APPLICATION_NO  "+
								                           " AND STATUS = B.APPLICATION_STATUS "+
								                            " )   "+
											" ) "+
											" ,NVL("+m_schema_name+".AF_GET_INIT_INSURANCE_COUNT_2(A.FINANCE_NO),0)  "+ // 13 added by udara 23-08-2018
										
										  " FROM "+m_schema_name+".AF_CONFIRMATION_REPORT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
										  " WHERE A.FINANCE_NO = B.FINANCE_NO  "+
										  " AND A.FINANCE_NO LIKE '%"+m_fin_no+"%'  "+ 
										  //" AND B.BRANCH_CODE LIKE '"+m_user_branch+"%'  "+ // commented by udara 15-12-2017		
										  " AND TRUNC(NVL(A.APP_DATE,A.ENT_DATE),'DD') >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
										  " AND TRUNC(NVL(A.APP_DATE,A.ENT_DATE),'DD') <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
										  //" ORDER BY NVL(A.MOD_DATE,A.ENT_DATE)  "+ // commented by udara 18-01-2018 // added by udara 20-10-2015
											" ORDER BY "+m_sort_by+" "+m_order_by_type+"  "+ // added by udara 18-01-2018
											" ");
						
					}
					else{
						
						rs=stmt.executeQuery(" "+ 								
									" SELECT "+
										" A.FINANCE_NO, "+ // 1
										" NVL(DECODE(A.NEW_REGEN_STATUS,'N','New','R','Re-Generated','M','Modified','Generated'),'-'), "+    // 2
										//" NVL(APP_USER,A.ENT_USER), "+ // 3//Commeneted by Jithendra 24-11-2016 for SR#22273 
										"(SELECT NAME FROM "+m_schema_name+".CO_CO_MAS_USER  WHERE USER_ID= NVL(APP_USER,A.ENT_USER)), "+ // 3//Added by Jithendra 24-11-2016 for SR#22273 
										" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'DD-MM-YYYY'),'-'), "+ // 4
										" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'HH24:MI:SS'),'-'), "+ // 5
										//" NVL(APP_COMMENT,CONF_RPT_COMMENT) APP_COMMENT, "+ // 6
										" SUBSTR(NVL(APP_COMMENT,CONF_RPT_COMMENT),0,40) APP_COMMENT,  "+  // " SUBSTR(NVL(APP_COMMENT,CONF_RPT_COMMENT),0,20) APP_COMMENT,  "+ 
										//" NVL(A.APP_COMMENT,'-'), "+ //6
										" NVL(A.APP_COMMENT_2,'-'), "+ //7
										" NVL(A.APP_COMMENT_3,'-'), "+ //8
										" NVL(A.APP_COMMENT_4,'-'), "+ //9
										" DECODE(A.REPORT_STATUS,'GEN','Approval Level 1','APPROVED','Approval Level 2','APPROVED2','Approval Level 3','APPROVED3','Approval Level 4','CONFIRMED','Confirmed'), "+ // 10
										" NVL("+m_schema_name+".AF_CO_GET_APP_STATUS(B.APPLICATION_NO),'-'), "+ // 11

										
										// added by udara 22-03-2017
										" DECODE(B.APPLICATION_STATUS,'ACTIVATED', "+
											 " (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH24:MI:SS') "+ // HH:MI:SS changed 24hrs format by udara 11-04-2019
																	         " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL "+
																	         " WHERE APPLICATION_NO = B.APPLICATION_NO "+
																	         " AND STAGE = 'PO_APP' "+
								                           " ), "+ 
																		
											 				" NVL(  "+
															" (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH24:MI:SS')  "+ // HH:MI:SS changed 24hrs format by udara 11-04-2019
																	         " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																	         " WHERE APPLICATION_NO = B.APPLICATION_NO  "+
								                           " AND STATUS = B.APPLICATION_STATUS "+
								                            " )   "+
															" ,  "+
															" (SELECT TO_CHAR(MAX(ENT_DATE),'DD-MM-YYYY HH:MI:SS')  "+
																	         " FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_APPROVAL  "+
																	         " WHERE APPLICATION_NO = B.APPLICATION_NO  "+
								                            " )   "+
															" ) "+ // end nvl
											" ) "+
											" ,NVL("+m_schema_name+".AF_GET_INIT_INSURANCE_COUNT_2(A.FINANCE_NO),0)  "+ // 13 added by udara 23-08-2018
										
										
										  " FROM "+m_schema_name+".AF_CONFIRMATION_REPORT A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
										  " WHERE A.FINANCE_NO = B.FINANCE_NO  "+
										  " AND A.FINANCE_NO LIKE '%"+m_fin_no+"%'  "+ 
										  //" AND B.BRANCH_CODE LIKE '"+m_user_branch+"%'  "+ // commented by udara 15-12-2017		
										  " AND TRUNC(NVL(A.APP_DATE,A.ENT_DATE),'DD') >= TO_DATE ( '"+m_from_date+"','DD-MM-YYYY' )  "+
										  " AND TRUNC(NVL(A.APP_DATE,A.ENT_DATE),'DD') <= TO_DATE ( '"+m_to_date+"','DD-MM-YYYY' )  "+
										  " AND A.REPORT_STATUS = '"+m_app_no+"'  "+ 
											//" ORDER BY NVL(A.MOD_DATE,A.ENT_DATE)  "+ // commented by udara 18-01-2018 // added by udara 15-10-2015
											" ORDER BY "+m_sort_by+" "+m_order_by_type+"  "+ // added by udara 18-01-2018
										  " ");
						
					}
					// end by udara 31-08-2015
					
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</B></td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				
				int counts = 0; // added by udara 15-10-2015
				
				if(more){		
				
				out.println("<br>");	
				out.println("<b><HR>");	
				out.println("<br>");	
				out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
	      		out.println("<tr class=pdn_txtpos2 align='left'>");
					out.println("<td  width='1%' > No. </td>"); // added by udara 15-10-2015
					out.println("<td  width='10%' style= cursor:hand; onclick=sort_data('FINANCE_NO','"+m_order_by_type+"') > Contract No </td>");
					
					/*
					out.println("<td  width='15%' > Last Approved date/Last Reversed Date </td>");
					out.println("<td  width='15%' > Last Approved/Reversed Time</td>");
					out.println("<td  width='15%' > Last Approved/Reversed User</td>");
					out.println("<td  width='10%' > Last Comment </td>");
					*/
					
					out.println("<td  width='40%' > Last approved/reversed date, time, user, comment </td>"); // added by udara 09-10-2015
					
					out.println("<td  width='10%' > Approval Level </td>");
					out.println("<td  width='10%' > System Approval Level </td>");
					out.println("<td  width='10%' > Confirmation Report </td>");
					out.println("<td  width='10%' > Snaps </td>"); // added by udara 28-07-2017
						
					out.println("<td  width='10%' > Documents </td>");//JB02012018-02276 inesh 2017-01-22
					
					out.println("<td  width='10%' > Insurance </td>"); // added by udara 24-07-2018
			

				out.println("</tr>");
				while(more){
					
					counts = counts + 1; // added by udara 15-10-2015
			
			    out.println("<tr>");
				out.println("<td  width='1%' >"+counts+"</td>"); // added by udara 15-10-2015
				out.println("<td  width='10%' STYLE='{text-align:left;cursor:pointer;}' onClick=show_transaction_history_new('','"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u> </td>");
				
				/*
				out.println("<td  width='15%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"','"+m_app_no+"')><u>"+rs.getString(4)+"</u> </td>");
				out.println("<td  width='15%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"','"+m_app_no+"')><u>"+rs.getString(5)+"</u> </td>");
				out.println("<td  width='15%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"','"+m_app_no+"')><u>"+rs.getString(3)+"</u> </td>");	
				out.println("<td  width='10%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"')><u>"+rs.getString(6)+"</u></td>");
				*/
				
				out.println("<td  width='40%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"')> <u>"+rs.getString(4)+"</u> &nbsp; <u>"+rs.getString(5)+"</u> &nbsp; <u>"+rs.getString(3)+"</u> &nbsp; <u>"+rs.getString(6)+"</u> </td>"); // added by udara 09-10-2015
				
				out.println("<td  width='10%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"')><u>"+rs.getString(10)+"</u></td>");
				
				//out.println("<td  width='10%' STYLE='{text-align:left;cursor:pointer;}' onClick=drilldown('"+rs.getString(1)+"')><u>"+rs.getString(11)+"</u></td>"); //added by udara 03-09-2015 
				out.println("<td  width='10%' STYLE='{text-align:left;}' >"+rs.getString(11)+" - "+rs.getString(12)+"</td>"); //added by udara 03-09-2015 
				
				//out.println("<td  width='10%' align=center><input type='text' name='APPROVE_COMMENT_"+j+"'></td>");	
				//out.println("<td  width='10' align=center><input type='button' name='DETA_BUTTON_"+j+"' value='View' class='but_input' onClick=load_details_report('"+rs.getString(1)+"') ></td>"); // 				
				
				out.println("<td  width='10' align=center><input type='button' name='DETA_BUTTON_"+j+"' value='View' class='but_input' onClick=load_details_report_new('"+rs.getString(1)+"') ></td>"); 
				
				out.println("<td  width='10' align=center><input type='button' name='SNAP_BUTTON_"+j+"' value='View' class='but_input' onClick=snap_drilldown('"+rs.getString(1)+"') ></td>"); // added by udara 28-07-2017
				
				
				out.println("<td  width='10' align=center><input type='button' name='VIEW_DOCUMENT_"+j+"' value='View' class='but_input' onClick=viewUploadDocs('"+rs.getString(1)+"') ></td>"); // added by udara 15-08-2017
				
				if(rs.getInt(13)>0)
					out.println("<td  width='10' align=center><input type='button' name='INSURANCE_BUTTON_"+j+"' value='View' class='but_input' onClick=insurance_details_app_drill('"+rs.getString(1)+"') ></td>"); // added by udara 24-07-2018
				else
					out.println("<td  width='10' align=center><input type='button' name='INSURANCE_BUTTON_"+j+"' value='View' class='but_input' onClick=insurance_details_app_drill('"+rs.getString(1)+"') disabled ></td>");
				
				
				more=rs.next();
				j=j+1;
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				}
			} // end if details
					else if(m_chksql.equals("drill_down")){
					String m_app_status="";
					String m_contract_no="";
					
					m_app_status=req.getParameter("app_status");
					m_contract_no = req.getParameter("finance_no");
					
					String m_user_branch = "";
			
					rs=stmt.executeQuery(" "+
						" SELECT "+ 
							" "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+m_username+"') "+
								" FROM DUAL ");
					
					if(rs.next()){
						m_user_branch = rs.getString(1);
					}
					
					if(m_user_branch.equals("HO")){
						m_user_branch = "";
					}
					
					out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");	
				
					/*
					rs=stmt.executeQuery(" "+ 								
									" SELECT "+
										" A.FINANCE_NO, "+ // 1
										" NVL(APP_USER,'-'), "+ // 2
										" NVL(TO_CHAR(A.APP_DATE,'DD-MM-YYYY'),'-'), "+ // 3
										" NVL(TO_CHAR(A.APP_DATE,'HH24:MI:SS'),'-'), "+ // 4
										" NVL(A.APP_COMMENT,'-'), "+///5
										" NVL(A.APP_COMMENT_2,'-'), "+//6
										" NVL(A.APP_COMMENT_3,'-'), "+//7
										" NVL(A.APP_COMMENT_4,'-') "+//8
										" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT_BK A, "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS B "+
										" WHERE A.FINANCE_NO LIKE '%"+m_contract_no+"%'   "+
										" AND A.FINANCE_NO = B.FINANCE_NO  "+
										" AND B.BRANCH_CODE LIKE '"+m_user_branch+"%'  "+
									    " AND A.REPORT_STATUS = '"+m_app_status+"'  "+ 
					               " ");
					*/
					
					rs=stmt.executeQuery(" "+ 	
						
								   " SELECT   FINANCE_NO,  "+
											  " APP_USER,  "+
											  " APP_DATE_DATE,  "+
											  " APP_DATE_TIME,  "+
											  " APP_COMMENT,  "+
											  " APP_DATE, "+
												" REPORT_STATUS "+ // 7
													" FROM ( "+
													
													" SELECT "+
															" A.FINANCE_NO FINANCE_NO, "+ // 1
														//	" A.ENT_USER APP_USER, "+ // 2//Commeneted by Jithendra 24-11-2016 for SR#22273
															"(SELECT NAME FROM "+m_schema_name+".CO_CO_MAS_USER  WHERE USER_ID=A.ENT_USER) APP_USER, "+ // 2//Added by Jithendra 24-11-2016 for SR#22273
															" NVL(TO_CHAR(A.ENT_DATE,'DD-MM-YYYY'),'-') APP_DATE_DATE, "+ // 3
															" NVL(TO_CHAR(A.ENT_DATE,'HH24:MI:SS'),'-') APP_DATE_TIME, "+ // 4
															" A.APP_COMMENT APP_COMMENT, "+
															" A.ENT_DATE APP_DATE, "+
															" DECODE(REPORT_STATUS,'GEN','Generation','APPROVED','Approval 1','APPROVED2','Approval 2','APPROVED3','Approval 3','CONFIRMED','Confirmed','DISAPPROVE','Reversed from approval 2','DISAPPROVE2','Reversed from approval 3','DISAPPROVE3','Reversed from approval 4',REPORT_STATUS) REPORT_STATUS "+ // 7
															" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT_STATUS A "+
															" WHERE A.FINANCE_NO LIKE '"+m_contract_no+"%'   "+
													
													/*
													   " SELECT "+
															" A.FINANCE_NO FINANCE_NO, "+ // 1
															" NVL(APP_USER,A.ENT_USER) APP_USER, "+ // 2
															" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'DD-MM-YYYY'),'-') APP_DATE_DATE, "+ // 3
															" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'HH24:MI:SS'),'-') APP_DATE_TIME, "+ // 4
															" NVL(APP_COMMENT,CONF_RPT_COMMENT) APP_COMMENT, "+
															" NVL(APP_DATE,A.ENT_DATE) APP_DATE, "+
															" DECODE(REPORT_STATUS,'GEN','Generation','APPROVED','Approval 1','APPROVED2','Approval 2','APPROVED3','Approval 3','CONFIRMED','Confirmed',REPORT_STATUS) REPORT_STATUS "+ // 7
															" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT A, AF_CO_PRO_APPLICATION_DETAILS B "+
															" WHERE A.FINANCE_NO LIKE '%"+m_contract_no+"%'   "+
															" AND A.FINANCE_NO = B.FINANCE_NO  "+
															
														" UNION "+
											
														" SELECT "+
															" A.FINANCE_NO FINANCE_NO, "+ // 1
															" NVL(APP_USER,A.ENT_USER) APP_USER, "+ // 2
															" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'DD-MM-YYYY'),'-') APP_DATE, "+ // 3
															" NVL(TO_CHAR(NVL(APP_DATE,A.ENT_DATE),'HH24:MI:SS'),'-') APP_DATE_TIME, "+ // 4
															" NVL(APP_COMMENT,CONF_RPT_COMMENT) APP_COMMENT, "+
															" NVL(APP_DATE,A.ENT_DATE) APP_DATE, "+
															" DECODE(REPORT_STATUS,'GEN','Generation','APPROVED','Approval 1','APPROVED2','Approval 2','APPROVED3','Approval 3','CONFIRMED','Confirmed',REPORT_STATUS) REPORT_STATUS "+ // 7
															" FROM "+m_schema_name+".AF_CONFIRMATION_REPORT_BK A, AF_CO_PRO_APPLICATION_DETAILS B "+
															" WHERE A.FINANCE_NO LIKE '%"+m_contract_no+"%'   "+
															" AND A.FINANCE_NO = B.FINANCE_NO  "+
															*/
															
															
														" )  "+	
														
														" ORDER BY APP_DATE DESC  "+
										
					" ");
					
					// end by udara 03-07-2015
						
				int j=0;
		    	boolean more = rs.next();
				if(!more){
						out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
						out.println("<td  width=\"*%\" align=\"center\"><B>No Records Found</td>");
	          			out.println("</TR></table>");
						out.println("<input type='hidden' name='REC_COUNT' value=0>");
				}
				
				int counts = 0; // added by udara 15-10-2015
				
				if(more){	
					
				out.println("<table align=\"center\" width=\"100%\" class=\"table\"><TR class=\"pdn_txtpos2\" align=\"center\">");
				out.println("<td  width=\"*%\" align=\"center\"><B> Confirmation Report - Activity Drilldown - "+m_contract_no+" </td>");
	          	out.println("</TR></table>");	
					
					
				out.println("<br>");	
				out.println("<b><HR>");	
				out.println("<br>");	
			    out.println("<table align=\"center\" width=\"100%\" border=\"1\" class=\"table\">");
	      		out.println("<tr class=pdn_txtpos2 align='left'>");
					out.println("<td  width='1%' > <b>No.</b> </td>"); // added by udara 15-10-2015
					out.println("<td  width='20%' align=left> <b>Status</b></td>");
					out.println("<td  width='20%' align=left> <b>Last Approved Date</b></td>");
					out.println("<td  width='20%' align=left> <b>Last Approved Time</b></td>");
					out.println("<td  width='20%' align=left> <b>Last Approved User</b></td>");
					out.println("<td  width='10%' align=left> <b> Last Comment</b> </td>");
				   // out.println("<td  width='*%'  > &nbsp; </td>");
				out.println("</tr>");
				while(more){
					
				counts = counts + 1; // added by udara 15-10-2015	
					
			    out.println("<tr>");
				out.println("<td  width='1%' >"+counts+"</td>"); // added by udara 15-10-2015
				out.println("<td  width='20%' align=left>"+rs.getString(7)+"</td>");
				out.println("<td  width='20%' align=left>"+rs.getString(3)+"</td>");
				out.println("<td  width='20%' align=left>"+rs.getString(4)+"</td>");
				out.println("<td  width='20%' align=left>"+rs.getString(2)+"</td>");
				/*
				if(m_app_status.equals("GEN")){
				out.println("<td  width='20%' align=center> - </td>");
				}
				else if(m_app_status.equals("APPROVED")){
				out.println("<td  width='20%' align=center>"+rs.getString(5)+"</td>");
				}
				else if(m_app_status.equals("APPROVED2")){
				out.println("<td  width='20%' align=center>"+rs.getString(6)+"</td>");
				}
				else if(m_app_status.equals("APPROVED3")){
				out.println("<td  width='20%' align=center>"+rs.getString(7)+"</td>");
				}else if(m_app_status.equals("CONFIRMED")){
					out.println("<td  width='10%' >"+rs.getString(8)+"</td>");
				}
				*/
				
				out.println("<td  width='20%' align=left>"+rs.getString(5)+"</td>");
			
				//out.println("<td  width='*%'  > &nbsp; </td>");
				more=rs.next();
				j=j+1;
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='hidden' name='REC_COUNT' value="+j+">");
				}		
			}
					
					
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
	