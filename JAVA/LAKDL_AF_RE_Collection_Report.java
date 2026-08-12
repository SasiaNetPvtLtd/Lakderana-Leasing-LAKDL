//--
//SCREEN NAME:CREDIT PROCESS -RENTAL DATE CHANE
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_Collection_Report extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs2;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username; //added by nuwan de silva 19-09-07------------------
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(0);
			nf.setMaximumFractionDigits(0);
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(2);
			nf1.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			String m_sort_column   = "CLIENT_CODE";	
			String m_order_by_type = "ASC";
		
			if(m_chksql.equals("main_page")){ 
			
			String m_generate=req.getParameter("generate");
			
			if(m_generate.equals("page")){ 
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			out.println("var count=0;");
			out.println("var timerID;");
			out.println("var durationID=0;");

			
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("     client_help();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M_CLIENT' ){");
			out.println("			document.Form1.CLIENT_CODE.value=data_vec[0]");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_COLLECTION_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_COLLECTION_OFFICER' ){");
			out.println("     help_button_collection(data_vec);");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_COLLECTION_OFFICER.value!=\"\" && document.Form1.hid_chk_status.value=='M_COLLECTION_OFFICER' ){");
			out.println("			document.Form1.TXT_COLLECTION_OFFICER.value=data_vec[0]");
			out.println("			}");
			//added by nuwan de silva on 18-10-07--------------------------------------
			out.println("			else");
			out.println("			if(data_vec.length>0 &&  document.Form1.hid_chk_status.value=='M_SYS_DATE' ){");
			out.println("			document.Form1.VAL_DAY.value=data_vec[0]");
			out.println("			document.Form1.VAL_MONTH.value=data_vec[1]");
			out.println("			document.Form1.VAL_YEAR.value=data_vec[2]");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("			}");
			
			out.println("}");
			
			
				out.println("function get_vector_normal(m_data){");
				out.println("alert('m_data'+m_data);");
				out.println("		if(m_data==\"OK\"){");
				out.println("			view_report_2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				
				/*out.println("function print_report_2(){");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("			m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=print_report\";"); 
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
		   */
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=run_report&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value;");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");



			
			 /* out.println("function sort_data(m_sort_col) {");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
	      out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=detail&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				*/
				
				out.println("function drill_down_asset(m_finance_no) {");
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				out.println("popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
				out.println("}");	
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			out.println("if(document.Form1.hid_chk_status.value=='M_CLIENT' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
					
			//out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_mk_officer&data_val=\"+obj.value+\"&ac_status=Y\";");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_COLLECTION_OFFICER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_col_officer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_COLLECTION_OFFICER.value+\"&ac_status=Y\";");	
				
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");
			
			
			
			
			out.println("function count_selected(){ ");
			out.println("count=0;");
			out.println("j=1;");
			
			out.println("if(document.Form1.CHK_LAST_PAY_TYPE.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_POD_CHEQUE_IN_HAND.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_RENTAL_DUE_DATE.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_ACTUAL_BALACE.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_RENTAL_GROSS.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_NIBSM.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_GROSS_ARREAS.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_CAPITAL_BAL_OUTSTANDING.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_PERIOD.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_TOTAL_RENTAL_PAID.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_RENTAL_PAID.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_PENDING.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_GROSS_RECEVIED.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_ODI_FOR_MONTH.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_TOTAL_ODI_UNRECOVER.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_RECOVER_ODI.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			out.println("if(document.Form1.CHK_FOLLOW_UP.checked==true ){");
			out.println("count=count+j;"); 	 
			out.println("}");	
			
			
			out.println("}");	
			

			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.VAL_DAY.value==\"\" || document.Form1.VAL_MONTH.value==\"\"  || document.Form1.VAL_YEAR.value==\"\"  ){  "); 
			out.println("VDATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function load_sys_date(){	"); 
			out.println("assignState('M_SYS_DATE')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_sys_date\";");
			out.println("load_interface(m_url,'XML');");
			out.println("}	"); 
			
			

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_Collection_Report\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection Process - Collection Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Collection Report - \"+document.Form1.hid_status.value;"); 
			out.println("}"); 

			out.println("function load_screen_status(m_val){"); 
			out.println("if(m_val==\"NEW\"){"); 
			out.println("new_window();"); 
			out.println("}"); 
			out.println("else if(m_val==\"HELP\"){"); 
			out.println("load_help_msg();"); 
			out.println("}"); 
			out.println("else if(m_val!=\"EDIT\"){"); 
			out.println(" if(confirm(\"Are you sure you want to Delete a record\")){  ");
			out.println("}"); 
			out.println("}"); 
			out.println("else{");
			out.println("}"); 
			out.println("document.Form1.SCREEN_NAME.value=m_val;"); 
			out.println("if(m_val==\"NEW\"){");
			out.println("document.Form1.hid_status.value=\"New\";"); 
			out.println("document.Form1.hid_save_status.value=\"Save\";"); 
			out.println("}else if(m_val==\"EDIT\"){");  
			out.println("document.Form1.hid_status.value=\"Edit\";");  
			out.println("document.Form1.hid_save_status.value=\"Modify\";"); 
			out.println("}else if(m_val==\"DEL\"){");  
			out.println("document.Form1.hid_status.value=\"Delete\";");
			out.println("document.Form1.hid_save_status.value=\"Delete\";"); 
			out.println("}else if(m_val==\"RACT\"){");  
			out.println("document.Form1.hid_status.value=\"Reactivate\";");
			out.println("document.Form1.hid_save_status.value=\"Reactivate\";"); 
			out.println("}else{");  
			out.println("document.Form1.hid_status.value=\"\";");  
			out.println("}"); 
			out.println("}"); 

			out.println("function MyDialog(){"); 
			out.println("    this.valout   = new Array(10);"); 
			out.println("}		"); 
			out.println(""); 
			//----------------------------------------------------------------------------------------------------------------------------------------
			
			out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("		if(IfCount==\"99\"){"); 
			out.println("		help_value_assign_advetst_no(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		client_assign(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_user(oBj);"); 
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_collection(oBj);"); 
	  	out.println("		}"); 
								
			out.println("	}"); //end next
			
			out.println("	else{"); 
			out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
			out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No,Crit,Sql,IfCount);"); 
			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(IfCount);");//Added To The Clear 
			out.println("	}");
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No,Crit,Sql,IfCount){"); 
			out.println("    HelpBox(Start,End,Hid_No,Crit,Sql,IfCount);"); 
			out.println("}"); 
			out.println(""); 
			//-----------------------------------------------------------------------------------------------------------------------------------------

			out.println(""); 
				//client Help
			out.println("function client_help(){");
			out.println("Crit=document.Form1.CLIENT_CODE.value+\"@\";");
			out.println(" document.Form1.hid_help_type.value='1' ");
			out.println("HelpBox('1','10','0',Crit,'ClientSql','1');");
			out.println("}");		
			
			out.println("function client_assign(oBj){");
			out.println(" document.Form1.CLIENT_CODE.value =oBj.valout[2]");
			out.println("}");
			
			out.println("function help_button_user() {"); 
			out.println(" document.Form1.hid_help_type.value='2' ");
			out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.TXT_COLLECTION_OFFICER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer_colection','2');");  //added by nuwan de silva on 14-09-07
			out.println("}"); 
			
			out.println("function help_value_assign_user(oBj) {"); 
			out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
			out.println("}"); 
			
			/*out.println("function help_button_collection() {"); 
			out.println(" document.Form1.hid_help_type.value='3' ");
			out.println("    Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@\"+document.Form1.TXT_USER.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");  //added by nuwan de silva on 14-09-07
			out.println("}"); 
			
			out.println("function help_value_assign_collection(oBj) {"); 
			out.println("    document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 
			*/
			
			out.println("function help_button_collection() {"); 
			out.println(" document.Form1.hid_help_type.value='3';");
			out.println(" Crit = document.Form1.TXT_COLLECTION_OFFICER.value+\"@\"+document.Form1.CLIENT_CODE.value+\"@Y@\";"); 
			out.println(" HelpBox('1','10','0',Crit,'m_help_collection_officer_colection','3');");
			out.println("}"); 
			
			out.println("function help_value_assign_collection(oBj) {"); 
			out.println(" document.Form1.TXT_COLLECTION_OFFICER.value=oBj.valout[2];"); 
			out.println("}"); 

			
			out.println("function clear_data(IfCount) {");
			out.println("if(IfCount=='1') {");
			out.println("document.Form1.CLIENT_CODE.value='';");
			out.println("}");
			out.println("if(IfCount=='2') {");
			out.println("document.Form1.TXT_USER.value='';");
			out.println("}");
			out.println("if(IfCount=='3') {");
			out.println("document.Form1.TXT_COLLECTION_OFFICER.value='';");
			out.println("}");
			out.println("}");
			
			out.println("function get_rental_dates(date,m_client_code,m_officer){");
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("}"); 
			
			/*out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("document.Form1.BUT_PRINT.disabled=false;");
			out.println("}");
			*/
			
			out.println("function view_report_2() {");
			out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
			out.println("count_selected();");
			//out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=view_report&count=\"+count+\"&date=\"+m_date+\"&client_code=\"+document.Form1.CLIENT_CODE.value+\"&coll_officer=\"+document.Form1.TXT_COLLECTION_OFFICER.value+\" "+
									"&CHK_LAST_PAY_TYPE=\"+document.Form1.CHK_LAST_PAY_TYPE.value+\""+
									"&CHK_POD_CHEQUE_IN_HAND=\"+document.Form1.CHK_POD_CHEQUE_IN_HAND.value+\""+
									"&CHK_RENTAL_DUE_DATE=\"+document.Form1.CHK_RENTAL_DUE_DATE.value+\""+
									"&CHK_ACTUAL_BALACE=\"+document.Form1.CHK_ACTUAL_BALACE.value+\""+
									"&CHK_RENTAL_GROSS=\"+document.Form1.CHK_RENTAL_GROSS.value+\""+
									"&CHK_NIBSM=\"+document.Form1.CHK_NIBSM.value+\""+
									"&CHK_GROSS_ARREAS=\"+document.Form1.CHK_GROSS_ARREAS.value+\""+
									"&CHK_CAPITAL_BAL_OUTSTANDING=\"+document.Form1.CHK_CAPITAL_BAL_OUTSTANDING.value+\""+
									"&CHK_PERIOD=\"+document.Form1.CHK_PERIOD.value+\""+
									"&CHK_TOTAL_RENTAL_PAID=\"+document.Form1.CHK_TOTAL_RENTAL_PAID.value+\""+
									"&CHK_RENTAL_PAID=\"+document.Form1.CHK_RENTAL_PAID.value+\""+
									"&CHK_PENDING=\"+document.Form1.CHK_PENDING.value+\""+
									"&CHK_GROSS_RECEVIED=\"+document.Form1.CHK_GROSS_RECEVIED.value+\""+
									"&CHK_ODI_FOR_MONTH=\"+document.Form1.CHK_ODI_FOR_MONTH.value+\""+
									"&CHK_TOTAL_ODI_UNRECOVER=\"+document.Form1.CHK_TOTAL_ODI_UNRECOVER.value+\""+
									"&CHK_FOLLOW_UP=\"+document.Form1.CHK_FOLLOW_UP.value+\""+
									"&CHK_RECOVER_ODI=\"+document.Form1.CHK_RECOVER_ODI.value;");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,addressBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}");	

			
			
			
			out.println("function view_report(date,m_client_code,m_col_officer) {");
			out.println("count_selected();");
			out.println("if(validate_data()){");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&col_officer=\"+m_col_officer+\"&date=\"+date;");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=view_report&count=\"+count+\"&client_code=\"+m_client_code+\"&col_officer=\"+m_col_officer+\"&date=\"+date+\" "+
									"&CHK_LAST_PAY_TYPE=\"+document.Form1.CHK_LAST_PAY_TYPE.value+\""+
									"&CHK_POD_CHEQUE_IN_HAND=\"+document.Form1.CHK_POD_CHEQUE_IN_HAND.value+\""+
									"&CHK_RENTAL_DUE_DATE=\"+document.Form1.CHK_RENTAL_DUE_DATE.value+\""+
									"&CHK_ACTUAL_BALACE=\"+document.Form1.CHK_ACTUAL_BALACE.value+\""+
									"&CHK_RENTAL_GROSS=\"+document.Form1.CHK_RENTAL_GROSS.value+\""+
									"&CHK_NIBSM=\"+document.Form1.CHK_NIBSM.value+\""+
									"&CHK_GROSS_ARREAS=\"+document.Form1.CHK_GROSS_ARREAS.value+\""+
									"&CHK_CAPITAL_BAL_OUTSTANDING=\"+document.Form1.CHK_CAPITAL_BAL_OUTSTANDING.value+\""+
									"&CHK_PERIOD=\"+document.Form1.CHK_PERIOD.value+\""+
									"&CHK_TOTAL_RENTAL_PAID=\"+document.Form1.CHK_TOTAL_RENTAL_PAID.value+\""+
									"&CHK_RENTAL_PAID=\"+document.Form1.CHK_RENTAL_PAID.value+\""+
									"&CHK_PENDING=\"+document.Form1.CHK_PENDING.value+\""+
									"&CHK_GROSS_RECEVIED=\"+document.Form1.CHK_GROSS_RECEVIED.value+\""+
									"&CHK_ODI_FOR_MONTH=\"+document.Form1.CHK_ODI_FOR_MONTH.value+\""+
									"&CHK_TOTAL_ODI_UNRECOVER=\"+document.Form1.CHK_TOTAL_ODI_UNRECOVER.value+\""+
									"&CHK_FOLLOW_UP=\"+document.Form1.CHK_FOLLOW_UP.value+\""+
									"&CHK_RECOVER_ODI=\"+document.Form1.CHK_RECOVER_ODI.value;");
			//out.println("window.open(m_url);");						
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,addressBar=1,status=0,scrollbars=1,resizable=1');");
			out.println("}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("}");	

			
			out.println("function print_report(date,m_client_code,m_officer,m_col_officer) {");
			out.println("count_selected();");
			out.println("if(validate_data()){");
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&col_officer=\"+m_col_officer+\"&date=\"+date;");	
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&count=\"+count+\"&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&col_officer=\"+m_col_officer+\"&date=\"+date+\" "+
									"&CHK_LAST_PAY_TYPE=\"+document.Form1.CHK_LAST_PAY_TYPE.value+\""+
									"&CHK_POD_CHEQUE_IN_HAND=\"+document.Form1.CHK_POD_CHEQUE_IN_HAND.value+\""+
									"&CHK_RENTAL_DUE_DATE=\"+document.Form1.CHK_RENTAL_DUE_DATE.value+\""+
									"&CHK_ACTUAL_BALACE=\"+document.Form1.CHK_ACTUAL_BALACE.value+\""+
									"&CHK_RENTAL_GROSS=\"+document.Form1.CHK_RENTAL_GROSS.value+\""+
									"&CHK_NIBSM=\"+document.Form1.CHK_NIBSM.value+\""+
									"&CHK_GROSS_ARREAS=\"+document.Form1.CHK_GROSS_ARREAS.value+\""+
									"&CHK_CAPITAL_BAL_OUTSTANDING=\"+document.Form1.CHK_CAPITAL_BAL_OUTSTANDING.value+\""+
									"&CHK_PERIOD=\"+document.Form1.CHK_PERIOD.value+\""+
									"&CHK_TOTAL_RENTAL_PAID=\"+document.Form1.CHK_TOTAL_RENTAL_PAID.value+\""+
									"&CHK_RENTAL_PAID=\"+document.Form1.CHK_RENTAL_PAID.value+\""+
									"&CHK_PENDING=\"+document.Form1.CHK_PENDING.value+\""+
									"&CHK_GROSS_RECEVIED=\"+document.Form1.CHK_GROSS_RECEVIED.value+\""+
									"&CHK_ODI_FOR_MONTH=\"+document.Form1.CHK_ODI_FOR_MONTH.value+\""+
									"&CHK_TOTAL_ODI_UNRECOVER=\"+document.Form1.CHK_TOTAL_ODI_UNRECOVER.value+\""+
									"&CHK_FOLLOW_UP=\"+document.Form1.CHK_FOLLOW_UP.value+\""+
									"&CHK_RECOVER_ODI=\"+document.Form1.CHK_RECOVER_ODI.value;");
			out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,addressBar=1,status=0,scrollbars=1,resizable=1');");
		//	out.println("window.open(m_url)");
			out.println("}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
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
			out.println("}");
			out.println("}");
			
			out.println("function check_Date(objDD,objMM,objYY) {");
			out.println("if(objDD.value!=\"\" && objMM.value!=\"\" && objYY.value!=\"\" )");
			out.println("if(checkMonthLength(objDD,objMM,objYY))");
			out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");			
			out.println("}");
			
			
			out.println("function Val_Change(obj1){");
			out.println("if(obj1.checked==true ){");
			out.println("obj1.value='on'");
			out.println("obj1.checked=true;");
			out.println("}else if(obj1.checked==false){");
			out.println("obj1.value='off'");
			out.println("obj1.checked=false;");
			out.println("}");	
			out.println("}");	
			
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"load_sys_date()\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_COLLECTION_REPORT\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\">");
			out.println("<input type=hidden name='hid_cal_date' value=\"\">");
			out.println("<input type=hidden name='hid_row_no' value=\"\">");
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Collection Report </td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			out.println("<td width='10%'></td>");
			out.println("<td width='10%'></td>");
			out.println("<td width='10%'></td>");
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


			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
			  out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='15%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("</td>");
				//out.println("<td width='*%'><input class='but_input' type='button' name='BUT_PRINT' value=\"View\" onClick=\"print_report(document.Form1.hid_date.value,document.Form1.CLIENT_CODE.value,document.Form1.TXT_USER.value,document.Form1.TXT_COLLECTION_OFFICER.value)\" ></td>"); 
				out.println("<td width='*%'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"view_report(document.Form1.hid_date.value,document.Form1.CLIENT_CODE.value,document.Form1.TXT_COLLECTION_OFFICER.value)\" style='{width=150px}'>");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style='{width=150px}'>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='CLIENT_CODE' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_CLIENT'),makeRequest(document.Form1.CLIENT_CODE)\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"Help\" onClick=\"client_help()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				*/
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_COLLECTION_OFFICER'  class=div_input>Collection Officer </DIV></td>"); 
				out.println("<td width='15%' ><input class='txt_input' type='text' name='TXT_COLLECTION_OFFICER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_COLLECTION_OFFICER'),makeRequest(document.Form1.TXT_COLLECTION_OFFICER)\">"); 
				out.println("</td>");
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_COLLECTION_OFFICER' value=\"Help\" onClick=\"help_button_collection()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
			  out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
			  out.println("</tr>"); 
				out.println("</table>"); 

				
				  out.println("<br><br>"); 
				  out.println("<table align='center' width='100%' class='table' border='0'>"); 
				  out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 ><b>Report Field</td>"); 
					out.println("<td width='15%' align='center' ><b>Display Status</td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
					
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Follow Up</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_FOLLOW_UP'  onclick=\"Val_Change(this)\" value=\"on\" checked ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					
				  out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Last Pay Type</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_LAST_PAY_TYPE'  onclick=\"Val_Change(this)\" value=\"on\" checked ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Post Dated Cheque In Hand</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_POD_CHEQUE_IN_HAND'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Rental Due Date</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_RENTAL_DUE_DATE'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Actual Balance</td>"); 
					out.println("<td width='15%'align='center' ><input  type='checkbox' name='CHK_ACTUAL_BALACE'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Rental Gross</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_RENTAL_GROSS'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >NIBSM</td>"); 
					out.println("<td width='15%'  align='center'><input  type='checkbox' name='CHK_NIBSM'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Arrears Gross</td>"); 
					out.println("<td width='15%'  align='center'><input  type='checkbox' name='CHK_GROSS_ARREAS'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Capital Balance O/S</td>"); 
					out.println("<td width='15%'  align='center'><input  type='checkbox' name='CHK_CAPITAL_BAL_OUTSTANDING'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Lease Period</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_PERIOD'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Total Renrals</td>"); 
					out.println("<td width='15%'  align='center'><input  type='checkbox' name='CHK_TOTAL_RENTAL_PAID'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Rentals Paid</td>"); 
					out.println("<td width='15%'  align='center' ><input  type='checkbox' name='CHK_RENTAL_PAID'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Rentals Outstanding</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_PENDING'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Gross Received</td>"); 
					out.println("<td width='15%'  align='center' ><input  type='checkbox' name='CHK_GROSS_RECEVIED'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >ODI For Month</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_ODI_FOR_MONTH'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
			    out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Total ODI Unrecovered</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_TOTAL_ODI_UNRECOVER'  onclick=\"Val_Change(this)\" value=\"on\" checked></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("<tr>"); 
					out.println("<td width='20%' ID=ROW21 >Total ODI recovered</td>"); 
					out.println("<td width='15%' align='center' ><input  type='checkbox' name='CHK_RECOVER_ODI'  onclick=\"Val_Change(this)\" value=\"on\" checked ></td>"); 
					out.println("<td width='*%'></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					out.println("<br><br>"); 
			
				
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v1.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
      out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}
		

				else if(m_generate.equals("run_report")){ 
				String m_date="";
				String m_client_code="";
				String m_col_officer="";
				
				if(req.getParameter("date")!=null ){
				m_date=req.getParameter("date");
				}
				if(req.getParameter("client_code")!=null ){
				m_client_code=req.getParameter("client_code");
				}
				if(req.getParameter("col_officer")!=null ){
				m_col_officer=req.getParameter("col_officer");
				}
				
				
				try{
				callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+".AF_RE_SAVE_COLLECTION_RPT_TM(:1,:2,:3,:4);END;");
				callstmt1.setString(1 ,m_date);
				callstmt1.setString(2 ,m_client_code);
				callstmt1.setString(3 ,m_col_officer);
				//callstmt1.setString(4 ,""m_officer);
				callstmt1.setString(4 ,m_username);				
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}
				
				}

else if(m_generate.equals("print_report")){		
	  String m_date="";
	  String m_client_code="";
	  String m_officer="";
		String m_col_officer="";
		int m_table_width=600;
		int m_table_colspan=6;
		
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date");
		}
		if(req.getParameter("client_code")!=null ){
		m_client_code=req.getParameter("client_code");
		}
		if(req.getParameter("officer")!=null ){
		m_officer=req.getParameter("officer");
		}
		//added by nuwan de silva on 14-09-07-------
		if(req.getParameter("col_officer")!=null ){
		m_col_officer=req.getParameter("col_officer");
		}
		
		int m_count     									 = Integer.parseInt(req.getParameter("count"));
		//String m_count					             =req.getParameter("count");
		String m_CHK_LAST_PAY_TYPE           =req.getParameter("CHK_LAST_PAY_TYPE").trim();
		String m_CHK_POD_CHEQUE_IN_HAND      =req.getParameter("CHK_POD_CHEQUE_IN_HAND").trim();
		String m_CHK_RENTAL_DUE_DATE         =req.getParameter("CHK_RENTAL_DUE_DATE").trim();
		String m_CHK_ACTUAL_BALACE           =req.getParameter("CHK_ACTUAL_BALACE").trim();
		String m_CHK_RENTAL_GROSS            =req.getParameter("CHK_RENTAL_GROSS").trim();
		String m_CHK_NIBSM                   =req.getParameter("CHK_NIBSM").trim();
		String m_CHK_GROSS_ARREAS            =req.getParameter("CHK_GROSS_ARREAS").trim();
		String m_CHK_CAPITAL_BAL_OUTSTANDING =req.getParameter("CHK_CAPITAL_BAL_OUTSTANDING").trim();
		String m_CHK_PERIOD                  =req.getParameter("CHK_PERIOD").trim();
		String m_CHK_TOTAL_RENTAL_PAID       =req.getParameter("CHK_TOTAL_RENTAL_PAID").trim();
		String m_CHK_RENTAL_PAID             =req.getParameter("CHK_RENTAL_PAID").trim();
		String m_CHK_PENDING                 =req.getParameter("CHK_PENDING").trim();
		String m_CHK_GROSS_RECEVIED          =req.getParameter("CHK_GROSS_RECEVIED").trim();
		String m_CHK_ODI_FOR_MONTH           =req.getParameter("CHK_ODI_FOR_MONTH").trim();
		String m_CHK_TOTAL_ODI_UNRECOVER     =req.getParameter("CHK_TOTAL_ODI_UNRECOVER").trim();
		String m_CHK_RECOVER_ODI             =req.getParameter("CHK_RECOVER_ODI").trim();
		String m_CHK_FOLLOW_UP               =req.getParameter("CHK_FOLLOW_UP").trim();
		//out.println("m_CHK_LAST_PAY_TYPE"+m_CHK_LAST_PAY_TYPE);
		m_table_width+=m_count*100;
		m_table_colspan+=m_count;
		
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			          m_sort_column = req.getParameter("sort_column");
			          m_order_by_type = req.getParameter("order_by_type");
							}
					else
							{
					callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
		      "AF_RE_SAVE_COLLECTION_RPT_TM(:1,:2,:3,:4,:5);END;");
				  callstmt1.setString(1 ,m_date);
          callstmt1.setString(2 ,m_client_code);
          callstmt1.setString(3 ,m_col_officer);
					callstmt1.setString(4 ,m_officer);
					callstmt1.setString(5 ,m_username);				
 				  callstmt1.execute();
							}
		stmt = conn.createStatement ();
		rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') FROM DUAL ");
		rs.next();
		String report_date=rs.getString(1);
			
		String Sql="SELECT  "+
  	" NVL(FINANCE_NO,'-') FINANCE_NO, "+//1
    " NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-') ACTIVATE_DATE, "+//2
    " CLIENT_CODE, "+ //3
    " FULL_NAME, "+//4
    " ADRESS1, "+ //5
    " ADRESS2, "+  //6
    " CITY_NAME, "+//7
    " TEL_NO, "+//8
    " NVL(LAST_PAY_AMT,0) LAST_PAY_AMT, "+//9
    " NVL(TO_CHAR(LAST_PAY_DATE,'DD-MM-YYYY'),'-') LAST_PAY_DATE , "+//10
    " NVL(LAST_PAY_TYPE,'-'), "+ //11
	  " NVL(TO_CHAR(RENTAL_DUE_DATE,'DD-MM-YYYY'),'-') RENTAL_DUE_DATE , "+//12
    " NVL(ACTUAL_BALACE,0) ACTUAL_BALACE, "+//13
    " NVL(RENTAL_GROSS,0) RENTAL_GROSS, "+//14
    " NVL(GROSS_ARREAS,0) GROSS_ARREAS, "+ //15
    " NVL(CAPITAL_BAL_OUTSTANDING,0) CAPITAL_BAL_OUTSTANDING, "+//16
    " NVL(NIBMSM,0) NIBMSM, "+//17
    " NVL(ODI_FOR_MONTH,0) ODI_FOR_MONTH, "+//18
    " NVL(TOTAL_ODI_UNRECOVER,0) TOTAL_ODI_UNRECOVER, "+//19
    " NVL(RECOVER_ODI,0) RECOVER_ODI, "+//20
    " NVL(ODI_COLLECTED,0) ODI_COLLECTED, "+//21
    " NVL(TOTAL_RENTAL_PAID,0) TOTAL_RENTAL_PAID, "+//22
    " NVL(RENTAL_PAID,0) RENTAL_PAID, "+//23
		" NVL(GROSS_RECEVIED,0) GROSS_RECEVIED, "+//24
    " NVL(AMI,0) AMI, "+//25
    " NVL(PD_CHEQUE,0) PD_CHEQUE, "+//26
	  " SUB_MODEL, "+//27
		" (TOTAL_RENTAL_PAID - RENTAL_PAID) PENDING, "+ //28
		" INITCAP(NVL("+m_schema_name+".AF_CO_GET_USER_NAME(MKT_OFFICER_NAME),'-')) MKT_OFFICER_NAME, "+//29
		" POD_CHEQUE_IN_HAND, "+//30
		" A.ITEM_SUB_CAT ITEM_SUB_CAT, "+//31  //" NVL(B.DESCRIPTION,'-') DESCRIPTION ,"+//32
		" (C.MODEL_CODE ||'  '||C.DESCRIPTION ||'-'||NVL(B.DESCRIPTION,'-')) DESCRIPTION,"+//32 //Added by Chandana on 22/06/2007
		" PERIOD, "+//33
		" NVL("+m_schema_name+".AF_CO_GET_USER_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER "+//34
    " FROM "+m_schema_name+".AF_RE_TBD_COLLECTION_RPT_TM  A, "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B, "+m_schema_name+".AF_CO_MAS_SUB_MODLE C"+
		" WHERE UPPER(A.ENT_USER)=UPPER('"+m_username+"')  "+
		" AND A.ITEM_SUB_CAT=B.ITEM_SUB_CAT(+) "+
		" AND A.SUB_MODEL=C.SUB_CODE "+
		" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
		
		
		  out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
				out.println("<SCRIPT language=\"JavaScript\">"); 
			
			  out.println("function sort_data(m_sort_col) {");
				out.println("count_selected();");
				out.println("	 m_order_by_type = 'ASC'; ");  
				out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
				out.println("	   if('"+m_order_by_type+"'=='DESC'){");
				out.println("	      m_order_by_type = 'ASC'; ");  
				out.println("    }else{");
			  out.println("       m_order_by_type = 'DESC'; ");
			  out.println("    }");
			  out.println("  }else{");
			  out.println("    m_order_by_type = 'ASC'; ");
			  out.println("  }");
			  // out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&client_code="+m_client_code+"&officer="+m_officer+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&count="+m_count+"&client_code="+m_client_code+"&officer="+m_officer+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type+\" "+
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&count=\"+count+\"&client_code=\"+m_client_code+\"&col_officer=\"+m_col_officer+\"&date=\"+date+\" "+
									"&CHK_LAST_PAY_TYPE="+m_CHK_LAST_PAY_TYPE+""+
									"&CHK_POD_CHEQUE_IN_HAND="+m_CHK_POD_CHEQUE_IN_HAND+""+
									"&CHK_RENTAL_DUE_DATE="+m_CHK_RENTAL_DUE_DATE+""+
									"&CHK_ACTUAL_BALACE="+m_CHK_ACTUAL_BALACE+""+
									"&CHK_RENTAL_GROSS="+m_CHK_RENTAL_GROSS+""+
									"&CHK_NIBSM="+m_CHK_NIBSM+""+
									"&CHK_GROSS_ARREAS="+m_CHK_GROSS_ARREAS+""+
									"&CHK_CAPITAL_BAL_OUTSTANDING="+m_CHK_CAPITAL_BAL_OUTSTANDING+""+
									"&CHK_PERIOD="+m_CHK_PERIOD+""+
									"&CHK_TOTAL_RENTAL_PAID="+m_CHK_TOTAL_RENTAL_PAID+""+
									"&CHK_RENTAL_PAID="+m_CHK_RENTAL_PAID+""+
									"&CHK_PENDING="+m_CHK_PENDING+""+
									"&CHK_GROSS_RECEVIED="+m_CHK_GROSS_RECEVIED+""+
									"&CHK_ODI_FOR_MONTH="+m_CHK_ODI_FOR_MONTH+""+
									"&CHK_TOTAL_ODI_UNRECOVER="+m_CHK_TOTAL_ODI_UNRECOVER+""+
									"&CHK_FOLLOW_UP="+m_CHK_FOLLOW_UP+""+
									"&CHK_RECOVER_ODI=="+m_CHK_RECOVER_ODI+";");
				out.println("window.open(m_url)");
			  out.println(" window.location.href=m_url;"); 
				out.println("}");
				
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			//out.println("<table align=\"center\" width=\"2300px\" border=\"0\" class=\"table\">");
			out.println("<table align=\"center\" width='"+m_table_width+"' border=\"0\" class=\"table\">");
			 out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"*%\" align=center  ><u><b>COLLECTION RERPORT AS AT "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			//out.println("<table align=\"center\" width=\"2300px\" border=\"0\" class=\"table\">");
			out.println("<table align=\"center\" width="+m_table_width+" border=\"0\" class=\"table\">");
			out.println("<br>");			
		  out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Agr.No  '    onclick=sort_data('FINANCE_NO') >Agr.No</td>"); 
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Mkt.Officer         '    onclick=sort_data('MKT_OFFICER_NAME')>Mkt.Officer</td>"); 
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Col.Officer         '    onclick=sort_data('COLLECTION_OFFICER')>Col.Officer</td>"); 
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Item Sub Category   '    onclick=sort_data('DESCRIPTION')>Item Sub Category</td>"); 
			
			if(m_CHK_FOLLOW_UP.equals("on")){
			out.println("<td width=\"100px\" align=left >Follow up</td>"); 
			}
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Agr.Date         '    onclick=sort_data('ACTIVATE_DATE')>Agr.Date</td>"); 
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Last Payment Date  '    onclick=sort_data('LAST_PAY_DATE')>Last Payment Date</td>"); 
			
			if(m_CHK_LAST_PAY_TYPE.equals("on")){
			out.println("true");
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Last Payment Type  '    onclick=sort_data('LAST_PAY_TYPE')>Last Payment Type</td>"); 
			}
			if(m_CHK_POD_CHEQUE_IN_HAND.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Post Dated Cheque In Hand  '    onclick=sort_data('POD_CHEQUE_IN_HAND')>Post Dated Cheque In Hand</td>"); 
			}
			if(m_CHK_RENTAL_DUE_DATE.equals("on")){
			out.println("<td width=\"100px\" align=left style= cursor:hand; title='Click here to sort by - Rental Due Date  '    onclick=sort_data('RENTAL_DUE_DATE')>Rental Due Date</td>"); 
			}
			if(m_CHK_ACTUAL_BALACE.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Actual Balance  '    onclick=sort_data('ACTUAL_BALACE')>Actual Balance</td>"); 
			}
			if(m_CHK_RENTAL_GROSS.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Rental Gross  '    onclick=sort_data('RENTAL_GROSS')>Rental Gross</td>"); 
			}
			if(m_CHK_NIBSM.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - NIBSM        '    onclick=sort_data('NIBMSM')>NIBSM</td>"); 
			}
			if(m_CHK_GROSS_ARREAS.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Arrears Gross  '    onclick=sort_data('GROSS_ARREAS')>Arrears Gross</td>"); 
			}
			if(m_CHK_CAPITAL_BAL_OUTSTANDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Capital Balance O/S  '    onclick=sort_data('CAPITAL_BAL_OUTSTANDING')>Capital Balance O/S</td>"); 
			}
			if(m_CHK_PERIOD.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Lease Period  '    onclick=sort_data('PERIOD')>Lease Period</td>"); 
			}
			if(m_CHK_TOTAL_RENTAL_PAID.equals("on")){
      out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Total Renrals  '    onclick=sort_data('TOTAL_RENTAL_PAID')>Total Rentals</td>"); 
			}
			if(m_CHK_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Rentals Paid  '    onclick=sort_data('RENTAL_PAID')>Rentals Paid</td>"); 
			}
			if(m_CHK_PENDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Rentals Outstanding  '    onclick=sort_data('PENDING')>Rentals Outstanding</td>"); 
			}
			if(m_CHK_GROSS_RECEVIED.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Gross Received  '    onclick=sort_data('GROSS_RECEVIED')>Gross Received</td>"); 
			}
			if(m_CHK_ODI_FOR_MONTH.equals("on")){
      out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - ODI For Month  '    onclick=sort_data('ODI_FOR_MONTH')>ODI For Month</td>"); 
			}
			if(m_CHK_TOTAL_ODI_UNRECOVER.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Total ODI Unrecovered '    onclick=sort_data('TOTAL_ODI_UNRECOVER')>Total ODI Unrecovered </td>"); 
			}
			if(m_CHK_RECOVER_ODI.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; title='Click here to sort by - Total ODI recovered '    onclick=sort_data('RECOVER_ODI')>Total ODI recovered </td>"); 		
			}
			
			out.println("</tr >"); 
			out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>"); 
			
		 rs=stmt.executeQuery(Sql);
			boolean more=rs.next();
			int row=0;
		 int j=1;
   //  while(rs.next()){
		  while(more){	
			String client_code=rs.getString(3);
		//	out.println("<tr class=pdn_txtpos2 ><td colspan=\"23\" align=left style=\"{cursor:hand; }\" onclick=show_client('"+rs.getString(3)+"')>"+j+".&nbsp;<b><u>"+rs.getString(4)+"</u><b>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+"&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(8)+"</b></td></tr>"); 
		out.println("<tr class=pdn_txtpos2 ><td colspan="+m_table_colspan+" align=left style=\"{cursor:hand; }\" onclick=show_client('"+rs.getString(3)+"')>"+j+".&nbsp;<b><u>"+rs.getString(4)+"</u><b>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+"&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(8)+"</b></td></tr>"); 
			
			int i=1;
			row=0;
			while(client_code.equals(rs.getString(3))){
		 					if(row>0 && row%2==1){
                  	out.println("<tr class=tr_input1 >");
									}
									else{
                  	out.println("<tr class=tr_input >");
									}
			out.println("<td width=\"100px\" align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>"); //Agreement Number 
			out.println("<td width=\"100px\" align=left>"+rs.getString(29)+"</td>"); //officer
			out.println("<td width=\"100px\" align=left>"+rs.getString(34)+"</td>"); //officer
			out.println("<td width=\"100px\" align=left style= cursor:hand; onClick=\"show_item_sub_category_drill('"+rs.getString(31)+"')\" ><u>"+rs.getString(32)+"</u></td>"); //Item Sub Cateory
			
			if(m_CHK_FOLLOW_UP.equals("on")){
			out.println("<td width=\"100px\" align=left style= cursor:hand; onclick=\"show_followup('"+rs.getString(1)+"')\" ><u>Follow up<u></td>"); 
			}
			out.println("<td width=\"100px\" align=left>"+rs.getString(2)+"</td>"); //Agreement Date
			out.println("<td width=\"100px\" align=left>"+rs.getString(10)+"</td>"); //Last Payment date
			
			
			if(m_CHK_LAST_PAY_TYPE.equals("on")){
			out.println("<td width=\"100px\" align=left>"+rs.getString(11)+"</td>");  //Last Payment Type 
			}
			if(m_CHK_POD_CHEQUE_IN_HAND.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(30))+"</td>");// pod in hand
			}
			if(m_CHK_RENTAL_DUE_DATE.equals("on")){
			out.println("<td width=\"100px\" align=left>"+rs.getString(12)+"</td>"); //Rental Due Date
			}
			if(m_CHK_ACTUAL_BALACE.equals("on")){
			out.println("<td width=\"100px\" align=left>-</td>"); //Actual Balance
			}
			if(m_CHK_RENTAL_GROSS.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(14))+"</td>");// Rental Gross
			}
			if(m_CHK_NIBSM.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(17))+"</td>");   //NIBSM
			}
			if(m_CHK_GROSS_ARREAS.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_arrears_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf.format(rs.getDouble(15))+"</u></td>");// Arreas Gross
			}
			if(m_CHK_CAPITAL_BAL_OUTSTANDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_capital_balace_outstanding_drill('"+rs.getString(1)+"')\" ><u>"+nf.format(rs.getDouble(16))+"</u></td>"); // Capital Balance Outstanding
			}
			if(m_CHK_PERIOD.equals("on")){
			out.println("<td width=\"100px\" align=right>"+rs.getInt(33)+"</td>");  //Lease Period
			}
			if(m_CHK_TOTAL_RENTAL_PAID.equals("on")){
      out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_total_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(rs.getDouble(22))+"</u></td>");  //Total Renral
			}
			if(m_CHK_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_rental_paid_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(rs.getDouble(23))+"</u></td>"); 
			}
			
			
			double tot=rs.getDouble(22);
			double rent=rs.getDouble(23);
			double pending=tot-rent;
			if(m_CHK_PENDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_outstanding_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(pending)+"</u></td>"); 
			}
			if(m_CHK_GROSS_RECEVIED.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_gross_received_drill('"+rs.getString(3)+"')\" ><u>"+nf.format(rs.getDouble(24))+"</u></td>"); 
			}
			if(m_CHK_ODI_FOR_MONTH.equals("on")){
      out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(18))+"</td>"); //ODI For Month
			}
			if(m_CHK_TOTAL_ODI_UNRECOVER.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(19))+"</td>"); //Total ODI Unrecover
			}
			if(m_CHK_RECOVER_ODI.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(20))+"</td>"); //Total ODI recover
			}
			out.println("</tr >"); 
     	row=row+1;
			more=rs.next();
			if(!more){
			break;
			}
			i=i+1;
			}
			out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>"); 
			j=j+1;
			}
			rs.close();
     out.println("</table >"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 		
		out.println("</body>");
		out.println("</html>");
}



			else if(m_generate.equals("view_report")){		
			String m_date="";
			String m_client_code="";
			String m_officer="";
			String m_col_officer="";
			int m_table_width=600;
			int m_table_colspan=6;
			
			if(req.getParameter("date")!=null ){
			m_date=req.getParameter("date").trim();
			}
			if(req.getParameter("client_code")!=null ){
			m_client_code=req.getParameter("client_code").trim();
			}
						
			//added by nuwan de silva on 14-09-07-------
			if(req.getParameter("col_officer")!=null ){
			m_col_officer=req.getParameter("col_officer").trim();
			}
			
			int m_count     									 = Integer.parseInt(req.getParameter("count"));
			//String m_count					             =req.getParameter("count");
			String m_CHK_LAST_PAY_TYPE           =req.getParameter("CHK_LAST_PAY_TYPE").trim();
			String m_CHK_POD_CHEQUE_IN_HAND      =req.getParameter("CHK_POD_CHEQUE_IN_HAND").trim();
			String m_CHK_RENTAL_DUE_DATE         =req.getParameter("CHK_RENTAL_DUE_DATE").trim();
			String m_CHK_ACTUAL_BALACE           =req.getParameter("CHK_ACTUAL_BALACE").trim();
			String m_CHK_RENTAL_GROSS            =req.getParameter("CHK_RENTAL_GROSS").trim();
			String m_CHK_NIBSM                   =req.getParameter("CHK_NIBSM").trim();
			String m_CHK_GROSS_ARREAS            =req.getParameter("CHK_GROSS_ARREAS").trim();
			String m_CHK_CAPITAL_BAL_OUTSTANDING =req.getParameter("CHK_CAPITAL_BAL_OUTSTANDING").trim();
			String m_CHK_PERIOD                  =req.getParameter("CHK_PERIOD").trim();
			String m_CHK_TOTAL_RENTAL_PAID       =req.getParameter("CHK_TOTAL_RENTAL_PAID").trim();
			String m_CHK_RENTAL_PAID             =req.getParameter("CHK_RENTAL_PAID").trim();
			String m_CHK_PENDING                 =req.getParameter("CHK_PENDING").trim();
			String m_CHK_GROSS_RECEVIED          =req.getParameter("CHK_GROSS_RECEVIED").trim();
			String m_CHK_ODI_FOR_MONTH           =req.getParameter("CHK_ODI_FOR_MONTH").trim();
			String m_CHK_TOTAL_ODI_UNRECOVER     =req.getParameter("CHK_TOTAL_ODI_UNRECOVER").trim();
			String m_CHK_RECOVER_ODI             =req.getParameter("CHK_RECOVER_ODI").trim();
			String m_CHK_FOLLOW_UP               =req.getParameter("CHK_FOLLOW_UP").trim();
			//out.println("m_CHK_LAST_PAY_TYPE"+m_CHK_LAST_PAY_TYPE);
			m_table_width+=m_count*100;
			m_table_colspan+=m_count;
			
			if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
			m_sort_column = req.getParameter("sort_column");
			m_order_by_type = req.getParameter("order_by_type");
			}
			
			stmt = conn.createStatement ();
			rs=stmt.executeQuery("SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MON-YYYY') FROM DUAL ");
			rs.next();
			String report_date=rs.getString(1);
			
			String Sql="SELECT  "+
			" NVL(FINANCE_NO,'-') FINANCE_NO, "+//1
			" NVL(TO_CHAR(ACTIVATE_DATE,'DD-MM-YYYY'),'-') ACTIVATE_DATE, "+//2
			" CLIENT_CODE, "+ //3
			" FULL_NAME, "+//4
			" ADRESS1, "+ //5
			" ADRESS2, "+  //6
			" CITY_NAME, "+//7
			" TEL_NO, "+//8
			" NVL(LAST_PAY_AMT,0) LAST_PAY_AMT, "+//9
			" NVL(TO_CHAR(LAST_PAY_DATE,'DD-MM-YYYY'),'-') LAST_PAY_DATE , "+//10
			" NVL(LAST_PAY_TYPE,'-'), "+ //11
			" NVL(TO_CHAR(RENTAL_DUE_DATE,'DD-MM-YYYY'),'-') RENTAL_DUE_DATE , "+//12
			" NVL(ACTUAL_BALACE,0) ACTUAL_BALACE, "+//13
			" NVL(RENTAL_GROSS,0) RENTAL_GROSS, "+//14
			" NVL(GROSS_ARREAS,0) GROSS_ARREAS, "+ //15
			" NVL(CAPITAL_BAL_OUTSTANDING,0) CAPITAL_BAL_OUTSTANDING, "+//16
			" NVL(NIBMSM,0) NIBMSM, "+//17
			" NVL(ODI_FOR_MONTH,0) ODI_FOR_MONTH, "+//18
			" NVL(TOTAL_ODI_UNRECOVER,0) TOTAL_ODI_UNRECOVER, "+//19
			" NVL(RECOVER_ODI,0) RECOVER_ODI, "+//20
			" NVL(ODI_COLLECTED,0) ODI_COLLECTED, "+//21
			" NVL(TOTAL_RENTAL_PAID,0) TOTAL_RENTAL_PAID, "+//22
			" NVL(RENTAL_PAID,0) RENTAL_PAID, "+//23
			" NVL(GROSS_RECEVIED,0) GROSS_RECEVIED, "+//24
			" NVL(AMI,0) AMI, "+//25
			" NVL(PD_CHEQUE,0) PD_CHEQUE, "+//26
			" SUB_MODEL, "+//27
			" (TOTAL_RENTAL_PAID - RENTAL_PAID) PENDING, "+ //28
			" INITCAP(NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(MKT_OFFICER_NAME),'-')) MKT_OFFICER_NAME, "+//29
			" POD_CHEQUE_IN_HAND, "+//30
			" A.ITEM_SUB_CAT ITEM_SUB_CAT, "+//31  //" NVL(B.DESCRIPTION,'-') DESCRIPTION ,"+//32
			" NVL(B.DESCRIPTION,'-') DESCRIPTION , "+
			" PERIOD, "+//33
			" NVL("+m_schema_name+".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER),'-') COLLECTION_OFFICER "+//34
			" FROM "+m_schema_name+".AF_RE_TBD_COLLECTION_RPT_TM  A, "+m_schema_name+".AF_CO_MAS_ITEM_SUB_CATEGORY B  "+
			" WHERE UPPER(A.ENT_USER)=UPPER('"+m_username+"')  "+
			" AND  ( CLIENT_CODE LIKE UPPER('"+m_client_code+"%') "+
			" AND    COLLECTION_OFFICER LIKE UPPER('"+m_col_officer+"%') )"+
			" AND A.ITEM_SUB_CAT=B.ITEM_SUB_CAT(+) "+
			" ORDER BY "+m_sort_column+" "+m_order_by_type+" ";
			
			

			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("function sort_data(m_sort_col) {");
			out.println("	 m_order_by_type = 'ASC'; ");  
			out.println("	 if(m_sort_col=='"+m_sort_column+"'){");
			out.println("	   if('"+m_order_by_type+"'=='DESC'){");
			out.println("	      m_order_by_type = 'ASC'; ");  
			out.println("    }else{");
			out.println("       m_order_by_type = 'DESC'; ");
			out.println("    }");
			out.println("  }else{");
			out.println("    m_order_by_type = 'ASC'; ");
			out.println("  }");
			out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report?chksql=main_page&generate=print_report&client_code="+m_client_code+"&officer="+m_officer+"&officer="+m_officer+"&date="+m_date+"&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
			out.println(" window.location.href=m_url;"); 
			out.println("}");
			
			out.println("function show_followup(val){ "); 
			out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
			out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
			out.println("}");
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
			out.println("<br>");	
			out.println("<br>");	
			//out.println("<table align=\"center\" width=\"2300px\" border=\"0\" class=\"table\">");
			out.println("<table align=\"center\" width='"+m_table_width+"' border=\"0\" class=\"table\">");
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"*%\" align=center  ><u><b>COLLECTION RERPORT AS AT "+report_date+" </u></td>"); 
			out.println("</tr >");
			out.println("</table>");			
			out.println("<br>");		
			//out.println("<table align=\"center\" width=\"2300px\" border=\"0\" class=\"table\">");
			out.println("<table align=\"center\" width="+m_table_width+" border=\"0\" class=\"table\">");
			out.println("<br>");			
			out.println("<tr class=pdn_txtpos2>");
			out.println("<td width=\"100px\" align=left class='rep-body1' style= cursor:hand; title='Click here to sort by - Agr.No  '    onclick=sort_data('FINANCE_NO') >Agr.No</td>"); 
			out.println("<td width=\"100px\" align=left class='rep-body1' style= cursor:hand; title='Click here to sort by - Mkt.Officer         '    onclick=sort_data('MKT_OFFICER_NAME')>Mkt.Officer</td>"); 
			out.println("<td width=\"100px\" align=left class='rep-body1' style= cursor:hand; title='Click here to sort by - Col.Officer         '    onclick=sort_data('COLLECTION_OFFICER')>Col.Officer</td>"); 
			out.println("<td width=\"100px\" align=left class='rep-body1' style= cursor:hand; title='Click here to sort by - Item Sub Category   '    onclick=sort_data('DESCRIPTION')>Item Sub Category</td>"); 
			
			if(m_CHK_FOLLOW_UP.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=left >Follow up</td>"); 
			}
			out.println("<td width=\"100px\" class='rep-body1' align=left style= cursor:hand; title='Click here to sort by - Agr.Date         '    onclick=sort_data('ACTIVATE_DATE')>Agr.Date</td>"); 
			out.println("<td width=\"100px\" class='rep-body1' align=left style= cursor:hand; title='Click here to sort by - Last Payment Date  '    onclick=sort_data('LAST_PAY_DATE')>Last Payment Date</td>"); 
			
			if(m_CHK_LAST_PAY_TYPE.equals("on")){
			//out.println("true");
			out.println("<td width=\"100px\" class='rep-body1' align=left style= cursor:hand; title='Click here to sort by - Last Payment Type  '    onclick=sort_data('LAST_PAY_TYPE')>Last Payment Type</td>"); 
			}
			if(m_CHK_POD_CHEQUE_IN_HAND.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Post Dated Cheque In Hand  '    onclick=sort_data('POD_CHEQUE_IN_HAND')>Post Dated Cheque In Hand</td>"); 
			}
			if(m_CHK_RENTAL_DUE_DATE.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=left style= cursor:hand; title='Click here to sort by - Rental Due Date  '    onclick=sort_data('RENTAL_DUE_DATE')>Rental Due Date</td>"); 
			}
			if(m_CHK_ACTUAL_BALACE.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Actual Balance  '    onclick=sort_data('ACTUAL_BALACE')>Actual Balance</td>"); 
			}
			if(m_CHK_RENTAL_GROSS.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Rental Gross  '    onclick=sort_data('RENTAL_GROSS')>Rental Gross</td>"); 
			}
			if(m_CHK_NIBSM.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - NIBSM        '    onclick=sort_data('NIBMSM')>NIBSM</td>"); 
			}
			if(m_CHK_GROSS_ARREAS.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Arrears Gross  '    onclick=sort_data('GROSS_ARREAS')>Arrears Gross</td>"); 
			}
			if(m_CHK_CAPITAL_BAL_OUTSTANDING.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Capital Balance O/S  '    onclick=sort_data('CAPITAL_BAL_OUTSTANDING')>Capital Balance O/S</td>"); 
			}
			if(m_CHK_PERIOD.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Lease Period  '    onclick=sort_data('PERIOD')>Lease Period</td>"); 
			}
			if(m_CHK_TOTAL_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Total Renrals  '    onclick=sort_data('TOTAL_RENTAL_PAID')>Total Rentals</td>"); 
			}
			if(m_CHK_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Rentals Paid  '    onclick=sort_data('RENTAL_PAID')>Rentals Paid</td>"); 
			}
			if(m_CHK_PENDING.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Rentals Outstanding  '    onclick=sort_data('PENDING')>Rentals Outstanding</td>"); 
			}
			if(m_CHK_GROSS_RECEVIED.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Gross Received  '    onclick=sort_data('GROSS_RECEVIED')>Gross Received</td>"); 
			}
			if(m_CHK_ODI_FOR_MONTH.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - ODI For Month  '    onclick=sort_data('ODI_FOR_MONTH')>ODI For Month</td>"); 
			}
			if(m_CHK_TOTAL_ODI_UNRECOVER.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Total ODI Unrecovered '    onclick=sort_data('TOTAL_ODI_UNRECOVER')>Total ODI Unrecovered </td>"); 
			}
			if(m_CHK_RECOVER_ODI.equals("on")){
			out.println("<td width=\"100px\" class='rep-body1' align=right style= cursor:hand; title='Click here to sort by - Total ODI recovered '    onclick=sort_data('RECOVER_ODI')>Total ODI recovered </td>"); 		
			}
			
			out.println("</tr >"); 
			out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>"); 
			
			rs=stmt.executeQuery(Sql);
			boolean more=rs.next();
			int row=0;
			int j=1;
			//  while(rs.next()){
			while(more){	
			String client_code=rs.getString(3);
			//	out.println("<tr class=pdn_txtpos2 ><td colspan=\"23\" align=left style=\"{cursor:hand; }\" onclick=show_client('"+rs.getString(3)+"')>"+j+".&nbsp;<b><u>"+rs.getString(4)+"</u><b>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+"&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(8)+"</b></td></tr>"); 
			out.println("<tr class=pdn_txtpos2 ><td colspan="+m_table_colspan+" align=left style=\"{cursor:hand; }\" onclick=show_client('"+rs.getString(3)+"')>"+j+".&nbsp;<b><u>"+rs.getString(4)+"</u><b>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+"&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;"+rs.getString(8)+"</b></td></tr>"); 
			
			int i=1;
			row=0;
			while(client_code.equals(rs.getString(3))){
			if(row>0 && row%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			out.println("<td width=\"100px\" align=left style= cursor:hand; onClick=\"show_finance_detail_drill('"+rs.getString(1)+"')\" ><u>"+rs.getString(1)+"</u></td>"); //Agreement Number 
			out.println("<td width=\"100px\" align=left>"+rs.getString(29)+"</td>"); //officer
			out.println("<td width=\"100px\" align=left>"+rs.getString(34)+"</td>"); //officer
			out.println("<td width=\"100px\" align=left style= cursor:hand; onClick=\"show_item_sub_category_drill('"+rs.getString(31)+"')\" ><u>"+rs.getString(32)+"</u></td>"); //Item Sub Cateory
			
			if(m_CHK_FOLLOW_UP.equals("on")){
			out.println("<td width=\"100px\" align=left style= cursor:hand; onclick=\"show_followup('"+rs.getString(1)+"')\" ><u>Follow up<u></td>"); 
			}
			out.println("<td width=\"100px\" align=left>"+rs.getString(2)+"</td>"); //Agreement Date
			out.println("<td width=\"100px\" align=left>"+rs.getString(10)+"</td>"); //Last Payment date
			
			if(m_CHK_LAST_PAY_TYPE.equals("on")){
			out.println("<td width=\"100px\" align=left>"+rs.getString(11)+"</td>");  //Last Payment Type 
			}
			if(m_CHK_POD_CHEQUE_IN_HAND.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(30))+"</td>");// pod in hand
			}
			if(m_CHK_RENTAL_DUE_DATE.equals("on")){
			out.println("<td width=\"100px\" align=left>"+rs.getString(12)+"</td>"); //Rental Due Date
			}
			if(m_CHK_ACTUAL_BALACE.equals("on")){
			out.println("<td width=\"100px\" align=left>-</td>"); //Actual Balance
			}
			if(m_CHK_RENTAL_GROSS.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(14))+"</td>");// Rental Gross
			}
			if(m_CHK_NIBSM.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(17))+"</td>");   //NIBSM
			}
			if(m_CHK_GROSS_ARREAS.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_arrears_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf.format(rs.getDouble(15))+"</u></td>");// Arreas Gross
			}
			if(m_CHK_CAPITAL_BAL_OUTSTANDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_capital_balace_outstanding_drill('"+rs.getString(1)+"')\" ><u>"+nf.format(rs.getDouble(16))+"</u></td>"); // Capital Balance Outstanding
			}
			if(m_CHK_PERIOD.equals("on")){
			out.println("<td width=\"100px\" align=right>"+rs.getInt(33)+"</td>");  //Lease Period
			}
			if(m_CHK_TOTAL_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_total_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(rs.getDouble(22))+"</u></td>");  //Total Renral
			}
			if(m_CHK_RENTAL_PAID.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_rental_paid_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(rs.getDouble(23))+"</u></td>"); 
			}
			
			double tot=rs.getDouble(22);
			double rent=rs.getDouble(23);
			double pending=tot-rent;
			if(m_CHK_PENDING.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_outstanding_rental_drill('"+rs.getString(1)+"','"+m_date+"')\" ><u>"+nf1.format(pending)+"</u></td>"); 
			}
			if(m_CHK_GROSS_RECEVIED.equals("on")){
			out.println("<td width=\"100px\" align=right style= cursor:hand; onclick=\"show_gross_received_drill('"+rs.getString(3)+"')\" ><u>"+nf.format(rs.getDouble(24))+"</u></td>"); 
			}
			if(m_CHK_ODI_FOR_MONTH.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(18))+"</td>"); //ODI For Month
			}
			if(m_CHK_TOTAL_ODI_UNRECOVER.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(19))+"</td>"); //Total ODI Unrecover
			}
			if(m_CHK_RECOVER_ODI.equals("on")){
			out.println("<td width=\"100px\" align=right>"+nf.format(rs.getDouble(20))+"</td>"); //Total ODI recover
			}
			out.println("</tr >"); 
			row=row+1;
			more=rs.next();
			if(!more){
			break;
			}
			i=i+1;
			}
			out.println("<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>"); 
			j=j+1;
			}
			rs.close();
			out.println("</table >"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 		
			out.println("</body>");
			out.println("</html>");
			}
				else if(m_generate.equals("drill_down_asset")){		
	String m_string="";
	String m_finance_no=req.getParameter("finance_no");
	stmt2 = conn.createStatement ();
	rs2= stmt2.executeQuery (" SELECT "+
    " B.ASSET_ID, "+
		" D.MAKE_CODE, "+
		" B.MODEL_CODE, "+
    " B.SUB_MODEL_CODE, "+
    " C.DESCRIPTION, "+
    " DECODE(B.STATUS,'N','New',DECODE(B.STATUS,'R','Re-Condition','Used') ), "+
    " DECODE(B.PURPOSE,'P','Private Use','Business'), "+
    " C.ENGINE_CAPACITY, "+
    " C.YEAR_OF_MANUFACTURE "+
		" FROM "+
		" "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_ASSET_DETAILS B,"+m_schema_name+".AF_CO_MAS_SUB_MODLE C ,"+m_schema_name+".AF_CO_MAS_MODEL D"+
		" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND "+
		" UPPER(A.FINANCE_NO)=UPPER('"+m_finance_no+"') AND "+
		"       B.SUB_MODEL_CODE=C.SUB_CODE AND       "+
		"       D.MODEL_CODE=C.MODEL_CODE AND       "+
		"       B.ACTIVE_STATUS='Y' AND "+
		"       C.ACTIVE_STATUS='Y' "+
		"       ORDER BY B.ASSET_ID ");
				m_string=m_string+"<table class='table' border='1' cellpadding='0' cellspacing='0' width='100%'>"; 
				m_string=m_string+"<tr><td align='left' class='pdn_txtpos2' >ASSET DETAILS FOR THE FINANCE NO: "+m_finance_no+"</td></tr>"; 
				m_string=m_string+"</table>"; 
				m_string=m_string+"<br>"; 
				m_string=m_string+"</table>"; 
  			m_string=m_string+"<br>"; 
				m_string=m_string+"<table align='center' width='100%' class='table' border=\"1\">";
				m_string=m_string+"<tr class='pdn_txtpos2' >";
				//m_string=m_string+"<th width='1%'></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Asset Id</DIV></th>";
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Make Code</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Sub Model Code</DIV></th>";
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Model Code</DIV></th>"; 
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Description</DIV></th>";
				m_string=m_string+"<th width='15%' align='left'><DIV class=div_input>Status</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Purpose</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Engine Capacity</DIV></th>"; 
				m_string=m_string+"<th width='10%' align='left'><DIV class=div_input>Year of Manufacture</DIV></th>"; 
				m_string=m_string+"</tr>";
				int i=0;
				while(rs2.next()){
					m_string=m_string+"<tr>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(1)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(2)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(3)+"</DIV></td>";
					m_string=m_string+"<td width='15%' align='left'><DIV class=div_input>"+rs2.getString(4)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(5)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(6)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(7)+"</DIV></td>";
					m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(8)+"</DIV></td>";
		      m_string=m_string+"<td width='10%' align='left'><DIV class=div_input>"+rs2.getString(9)+"</DIV></td>";
					m_string=m_string+"</tr>";
					i++;
				}
				rs2.close();
				m_string=m_string+"</table>";
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println(m_string);
			out.println("</html>");
						
}

			


}
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
		    if(rs!=null){try{rs.close(); }catch(Exception e){}}
			  if(rs2!=null){try{rs2.close(); }catch(Exception e){}}
		    if(stmt!=null){try{stmt.close(); }catch(Exception e){}}
				if(stmt2!=null){try{stmt2.close(); }catch(Exception e){}}
		    if(conn!=null){try{conn.close(); }catch(Exception e){}}
				if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	}
}
