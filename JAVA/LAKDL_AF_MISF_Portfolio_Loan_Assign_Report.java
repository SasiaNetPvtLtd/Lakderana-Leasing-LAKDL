//--
//SCREEN NAME:CREDIT PROCESS -LAKDL_AF_RE_Collection_Movement_Report
//CREATED BY:
//DATE/TIME:
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 


public class LAKDL_AF_MISF_Portfolio_Loan_Assign_Report extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
	java.lang.Math a;
	Statement stmt,stmt2,stmt1;
	CallableStatement callstmt1 =null;
	public ResultSet rs,rs2,rs1;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		try { 
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			String m_username = m_sn_methods.username;
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();
			
			String m_chksql=req.getParameter("chksql");
			
			String m_sort_column   = "FINANCE_NO";	
			String m_order_by_type = "ASC";
			
			if(m_chksql.equals("run_report")){ 
				
				String m_date=req.getParameter("date");
				String m_facility_code=req.getParameter("facility_code");
				//String m_user_id=req.getParameter("user_id");
				
				try{
					callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_LOAN_FACILITY_RPT(:1,:2,:3);END;");
					callstmt1.setString(1,m_date);
					callstmt1.setString(2,m_facility_code);
					callstmt1.setString(3,m_username);
					callstmt1.execute();
					out.print("OK"); 
				}
				catch(Exception ex){
					out.println("ERROR"+ex.toString()); 
				}
				
			}
			
			if(m_chksql.equals("main_page")){ 
				
				rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report Branch</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("var b_flag=0;");
				
				
				out.println("var b_flag=0;");
				
				out.println("var timerID;");
				out.println("var durationID=0;");
				
				out.println("function set_timer_actions() {");
				out.println("   durationID=durationID+1;");
				out.println("		timerID = setTimeout(\"set_timer_actions()\",1000);");
				out.println("		m_table.innerHTML=\"<p><b>Please Wait...\"+durationID+\" s</b></P>\";");
				out.println("}");
				
				
				out.println("function run_report() {");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=run_report&date=\"+m_date+\"&facility_code=\"+document.Form1.TXT_LOAN_FACILITY.value;"); 
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				out.println("			print_report2();"); 
				out.println("		}");
				out.println("		else{");
				out.println("			alert('Error when generating Report...'+m_data);");
				out.println("		}");
				out.println("}");
				
				out.println("function print_report2(){");
				out.println("		clearTimeout(timerID);");
				out.println("		m_table.innerHTML=\"\";");
				out.println("	if(document.Form1.VAL_DAY.value!=\"\" && document.Form1.VAL_MONTH.value!=\"\" && document.Form1.VAL_YEAR.value!=\"\"){");
				out.println("		m_date=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");
				out.println("		m_facility_code=document.Form1.TXT_LOAN_FACILITY.value;");
				out.println("if(m_facility_code!=''  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=print_report_new&facility_code=\"+m_facility_code+\"&date=\"+m_date;");	
				out.println("	}");
				out.println("else {");
				//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=print_report_new_all&facility_code=\"+m_facility_code+\"&date=\"+m_date;");	
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=print_report_new_all_2&facility_code=\"+m_facility_code+\"&date=\"+m_date;");	
				out.println("	}");
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector(data_vec) {");
				
				/*out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("     help_button_user(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
				out.println("			document.Form1.TXT_USER.value=data_vec[0]");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[2]");
				out.println("			}");
					out.println("			else");
				out.println("			if(data_vec.length==0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("     help_update(data_vec);");
				out.println("			}");
				out.println("			else");
				out.println("			if(data_vec.length>0 && document.Form1.TXT_LOCATION_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M1' ){");
				out.println("			document.Form1.TXT_LOCATION_CODE.value=data_vec[0]");
				out.println("			}");
				*/					
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
	      out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Marketing_Officer_Performance_Report?chksql=main_page&sort_column=\"+m_sort_col+\"&order_by_type=\"+m_order_by_type;"); 
				out.println("load_interface(m_url,'NORM');");
				out.println("}");
				*/
				
				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Marketing_Officer_Performance_Report?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_Collection_user_id&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M1' )");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_MAS_display_location&data_val=\"+obj.value+\"&ac_status=Y\";");
				
				out.println("else if(document.Form1.hid_chk_status.value=='M_USER' && document.Form1.SCREEN_NAME.value==\"NEW\")");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_Re_marketing_offcer&data_val=\"+obj.value+\"&data_val2=\"+document.Form1.TXT_LOCATION_CODE.value+\"&ac_status=Y\";");	
				
				//out.println("window.open(m_url);");
				
				out.println("load_interface(m_url,'XML');");
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
				
				out.println("function clear_window(){	"); 
				out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
				out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=main_page&generate=page';"); 
				out.println("		}"); 
				out.println("}"); 
				
				out.println("function new_window(){	"); 
				out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=main_page&generate=page';"); 
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
				out.println("help_box.innerHTML=\" Finance Process - Loan Facility Report - \"+m_val;"); 
				out.println("}"); 
				out.println(""); 
				
				out.println("function load_roll_out_value(){");
				out.println("help_box.innerHTML=\" Finance Process - Loan Facility Report - \"+document.Form1.hid_status.value;"); 
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
				
				out.println("		if(IfCount==\"2\"){"); 
				out.println("		team_assign(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"3\"){"); 
				out.println("		help_value_assign_user(oBj);"); 
				out.println("		}"); 
				out.println("		if(IfCount==\"99\"){"); 
				out.println("		help_update_value_assign_99(oBj);"); 
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
				
				out.println("function help_button_user() {"); 
				out.println(" document.Form1.hid_help_type.value='3' ");
				out.println("    Crit = document.Form1.TXT_LOAN_FACILITY.value+\"@Y@\";"); 
				out.println("    HelpBox('1','10','0',Crit,'m_help_loan_facilities_sql','3');"); 
				out.println("}"); 
				
				
				out.println("function help_value_assign_user(oBj) {"); 
				out.println("    document.Form1.TXT_LOAN_FACILITY.value=oBj.valout[2];"); 
				out.println("}"); 
				
				out.println("function clear_data(IfCount) {");
				out.println("		if(IfCount==\"3\"){"); 
				out.println("document.Form1.TXT_LOAN_FACILITY.value='';");
				out.println("		}"); 
				out.println("}");
				
				
				/*	out.println("function get_rental_dates(date,m_client_code,m_officer){");
					
					out.println("if(validate_data()){");
					out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Performance_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
					out.println("load_interface(m_url,'NORM');");
					//out.println("window.open(m_url);");
					out.println("}"); 
					
					out.println("else{");
					out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
					out.println("} "); 
					out.println("}"); 
					*/
				
				out.println("function print_report(date,m_location,m_officer) {");
				out.println("if(validate_data()){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_Marketing_Officer_Performance_Report?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
				out.println("popupwin=window.open(m_url,'displayWindow1','left=10,top=60,width=1000,height=500,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
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
				
				
				out.println("function load_sysdate(){	"); 
				if(rs2.next()){
					out.println("document.Form1.VAL_DAY.value='"+rs2.getString(1)+"';");
					out.println("document.Form1.VAL_MONTH.value='"+rs2.getString(2)+"';");
					out.println("document.Form1.VAL_YEAR.value='"+rs2.getString(3)+"';");
					out.println("     document.Form1.hid_date.value=document.Form1.VAL_DAY.value+'-'+document.Form1.VAL_MONTH.value+'-'+document.Form1.VAL_YEAR.value;");				
				}
				out.println("}"); 
				
				
				out.println("</script>"); 
				
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD='load_sysdate()' > "); //load_lock(), header(),add_row()
				out.println("<FORM NAME='Form1' method='post'>"); 
				out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"\">"); 
				out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
				out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
				out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_MISF_PORTFOLIO_LOAN_ASSIGN\">"); 
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
				out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance Process - Loan Facility Report </td>"); 
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
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				/*out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				*/
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Loan Facility </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOAN_FACILITY' maxlength='10' style='{width=150px}' size='10' onblur=\"help_button_user()\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_LOAN_FACILITY' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				
				
				out.println("</table>"); 
				
				out.println("<table align='center' width='100%' class='table'>"); 
				
				out.println("<tr>");  
				out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
				out.println("</tr>"); 
				out.println("</table>"); 
				
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
			
			
			
			else if(m_chksql.equals("drill_down_asset")){		
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
					//m_string=m_string+"<td width='1%'></td>"; 
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
				
				m_string=m_string+"</table>";
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Collection - Collection Report</TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println(m_string);
				out.println("</html>");
				
			}
			
			else if(m_chksql.equals("print_report_new")){		
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				if(req.getParameter("facility_code")!=null ){
					m_facility_code=req.getParameter("facility_code").trim();
				}
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_rental_structure(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=rental_schedule&application_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=600,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				Sql_data=" SELECT  "+
					" a.application_no, "+ //1
					" a.finance_no, "+ //2
					" a.loan_no,  "+ //3
					" a.client_code, "+ //4
					" a.client_full_name,"+ //5
					" a.client_address, "+  //6
					" nvl(a.total_rentals,0) - nvl(a.rentals_paid,0) age , "+ //7
					" nvl(a.total_amount,0) - (nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) , "+ //8
					" nvl(a.arr_capital_portion,0), "+ //9
					" nvl(a.future_receivable,0), "+ //10
					" to_char(a.activated_date,'dd-mm-yyyy') activated_date,  "+ //11
					" nvl(a.payment_structure ,'-' ), "+ //12
					" nvl(a.facility_amount,0),  "+ //13
					" nvl(a.irr,0), "+ //14
					" nvl(a.flat_rate,0),  "+ //15
					" nvl(a.rental_amount,0), "+ //16
					" nvl(a.frequency,'-') ,"+ //17
					" nvl(a.total_period,0) - nvl(a.rentals_paid,0) , "+ //18
					" nvl(a.rentals_paid,0) "+ //19
					" FROM "+m_schema_name+".af_misf_tbd_loan_facility a "+
					" WHERE ENT_USER='"+m_username+"' "+	
					
					" AND UPPER(loan_no) like UPPER('%"+m_facility_code+"%') ORDER BY  a.finance_no ";
				
				
				rs=stmt.executeQuery(Sql_data);
				boolean more=rs.next();
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Loan Facilities Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				if(more){
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement No</td>"); 
					out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Name</td>"); 
					out.println("<td width=\"15%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Address</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement Date</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Payment Structure</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period Structure</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Facility Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >IRR</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Flat Rate</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Rent</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Frequency</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Due Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Age</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Capital Receivable</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Future Receivable</td>"); 
					out.println("</tr >");
				}
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				String m_application_no="";
				
				while(more){
					j=1;
					m_application_no=rs.getString(1);
					
					out.println("<tr  >");
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(5)+"</td>"); 
					out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(6)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(11)+"</td>"); 
					out.println("<td width=\"7%\"  onClick=\"show_rental_structure('"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:center; cursor:hand; }'   ><u>Payment Structure</u></td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >Paid - "+nf.format(rs.getDouble(19))+" &nbsp; Future - "+nf.format(rs.getDouble(18))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(13))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(14))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(15))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(16))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(8))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(7))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(9))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(10))+"</td>"); 
					out.println("</tr >");
					
					rs2=stmt2.executeQuery(
						"SELECT "+
						"a.application_no, "+
						"a.asset_id, "+
						"a.make,"+
						"nvl(a.reg_no,'-'), "+
						"nvl(a.engine_no,'-'), "+
						"nvl(a.chassis_no,'-'), "+
						"nvl(a.year_of_manufacture,0),"+
						"a.asset_status "+
						"FROM "+m_schema_name+".af_misf_tbd_loan_faci_det a "+
						"WHERE ENT_USER='"+m_username+"' "+	
						"AND a.application_no='"+m_application_no+"' ");
					
					while(rs2.next()){
						if(j==1){
							out.println("<tr >");
							out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Id</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Make</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Reg No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Engine No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Chassis No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Year Of Manufacture</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Status</td>"); 
							out.println("</tr >");
						}
						out.println("<tr  >");
						out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >"+j+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(2)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(3)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(4)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(5)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(6)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(7)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(8)+"</td>"); 
						out.println("</tr >");
						j+=1;
					}	
					
					more=rs.next();
					if(more){
						out.println("<tr  bgcolor=\"#CCCCFF\" >");
						out.println("<td  colspan='15' width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
						out.println("</tr >");
						
						out.println("<tr bgcolor=\"#CCCCCC\"  >");
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement No</td>"); 
						out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Name</td>"); 
						out.println("<td width=\"15%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Address</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement Date</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Payment Structure</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period Structure</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Facility Amount</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >IRR</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Flat Rate</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Rent</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Frequency</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Due Amount</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Age</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Capital Receivable</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Future Receivable</td>"); 
						out.println("</tr >");
					}
					
				}
				
				
				out.println("</table>");		 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			}
			
			else if(m_chksql.equals("print_report_new_all_2")){		
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				/*if(req.getParameter("facility_code")!=null ){
				m_facility_code=req.getParameter("facility_code").trim();
				}
			*/	
				stmt = conn.createStatement ();
				stmt2 = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_rental_structure(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=rental_schedule&application_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=600,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				/*
				Sql_data=" SELECT  "+
					" a.application_no, "+ //1
			" a.finance_no, "+ //2
			" a.loan_no,  "+ //3
			" a.client_code, "+ //4
			" a.client_full_name,"+ //5
					" a.client_address, "+  //6
			" a.total_rentals - nvl(a.rentals_paid,0) age , "+ //7
			" a.total_amount - (nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) , "+ //8
			" nvl(a.arr_capital_portion,0), "+ //9
			" nvl(a.future_receivable,0), "+ //10
			" to_char(a.activated_date,'dd-mm-yyyy') activated_date,  "+ //11
			" a.payment_structure, "+ //12
			" NVL(a.facility_amount,0),  "+ //13
			" NVL(a.irr,0), "+ //14
			" NVL(a.flat_rate,0),  "+ //15
			" NVL(a.rental_amount,0), "+ //16
			" a.frequency ,"+ //17
					" nvl(a.TOTAL_PERIOD,0) - nvl(a.rentals_paid,0) , "+ //18
					" nvl(a.rentals_paid,0) "+ //19
			" FROM "+m_schema_name+".af_misf_tbd_loan_facility a "+
					" WHERE ENT_USER='"+m_username+"' ";
					//" AND UPPER(loan_no) like UPPER('%"+m_facility_code+"%') ";
					
					*/
				Sql_data=" SELECT  "+
					" DISTINCT a.application_no,  "+//1
					" a.finance_no,  "+//2
					" nvl(a.loan_no,'-'),   "+//3
					" a.client_code,  "+//4
					" a.client_full_name, "+//5
					" a.client_address,   "+//6
					" a.total_rentals - nvl(a.rentals_paid,0) age ,  "+//7
					" a.total_amount - (nvl(a.settled_amount,0)  +nvl(a.adjusted_amount,0)) ,  "+//8
					" nvl(a.arr_capital_portion,0) + nvl(FUTURE_RECEIVABLE_CAP,0) ,  "+//9
					" nvl(a.future_receivable,0),  "+//10
					" to_char(a.activated_date,'dd-mm-yyyy') activated_date,   "+//11
					" a.payment_structure, "+ //12
					" NVL(a.facility_amount,0), "+  //13
					" NVL(a.irr,0),  "+//14
					" NVL(a.flat_rate,0),   "+//15
					" NVL(a.rental_amount,0),  "+//16
					" a.frequency ,"+ //17
					" nvl(a.TOTAL_PERIOD,0) - nvl(a.rentals_paid,0) ,  "+//18
					" nvl(a.rentals_paid,0), "+ //19
					" b.make, "+ //20
					" nvl(B.reg_no,'-') reg_no, "+ //21
					" nvl(B.engine_no,'-') engine_no,"+ //22 
					" nvl(B.chassis_no,'-') chassis_no,"+  //23
					" nvl(B.year_of_manufacture,0) year_of_manufacture,"+ //24
					" B.asset_status ,"+ //25
					" a.TERMS_OF_PAYMENTS , "+ //26
					" a.NO_OF_FUTURE_INS, "+ //27
					" nvl(c.ref_no,'-')  "+ //28
					" FROM "+m_schema_name+".af_misf_tbd_loan_facility a  , "+m_schema_name+".af_misf_tbd_loan_faci_det B , "+m_schema_name+".af_co_mas_loan_facilities C "+
					" WHERE A.application_no=B.application_no "+
					" AND   A.loan_no=C.loan_facility_no(+) "+
					"  AND A.ENT_USER='"+m_username+"' "+
					"  AND B.ENT_USER='"+m_username+"' "+
					" ORDER BY a.finance_no ";
				
				
				rs=stmt.executeQuery(Sql_data);
				
				boolean more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Loan Facilities Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				if(more){
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement No</td>"); 
					out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Name</td>"); 
					out.println("<td width=\"15%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Address</td>"); 
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Make</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Reg No</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Engine No</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Chassis No</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Year Of Manufacture</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Status</td>"); 
					
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement Date</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Payment Structure</td>"); 
					//out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period Structure</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No of Future Ins.</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Facility Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >IRR</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Flat Rate</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Rent</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Frequency</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Due Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Age</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Capital Receivable</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Future Receivable</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Loan No</td>"); 
					out.println("</tr >");
				}
				int j=1;
				//int count=0;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				String m_application_no="";
				
				while(more)
				{
					j=1;
					count=0;
					m_application_no=rs.getString(1);
					
					while(m_application_no.equals(rs.getString(1)))
					{
						
						if (count==0)
						{
							out.println("<tr   bgcolor=\"#FCEBC5\" >");
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>"); 
							out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(5)+"</td>"); 
							out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(6)+"</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(20)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(21)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(22)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(23)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(24)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(25)+"</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(11)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(26)+"</td>"); 
							//out.println("<td width=\"7%\"  onClick=\"show_rental_structure('"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:center; cursor:hand; }'   ><u>Payment Structure</u></td>"); 
							//out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >Paid - "+nf.format(rs.getDouble(19))+" &nbsp; Future - "+nf.format(rs.getDouble(18))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(27))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(13))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(14))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(15))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(16))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(8))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(7))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(9))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(10))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(28)+"</td>"); //3
							out.println("</tr >");			
						}
						else
						{
							
							out.println("<tr  bgcolor=\"#FCEBC5\" >");
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>"); 
							out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >&nbsp;</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(20)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(21)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(22)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(23)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(24)+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(25)+"</td>"); 
							
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							//out.println("<td width=\"7%\"  onClick=\"show_rental_structure('"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:center; cursor:hand; }'   ><u>Payment Structure</u></td>"); 
							//out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >Paid - "+nf.format(rs.getDouble(19))+" &nbsp; Future - "+nf.format(rs.getDouble(18))+"</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >&nbsp;</td>"); 
							//out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(28)+"</td>"); //3
							out.println("</tr >");			
						}
						count+=1;
						more=rs.next();
						if(!more){break;}
					}
					
					/*out.println("<tr  >");
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(5)+"</td>"); 
					out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(6)+"</td>"); 
								
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(20)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(21)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(22)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(23)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(24)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(25)+"</td>"); 
					
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(11)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(26)+"</td>"); 
					//out.println("<td width=\"7%\"  onClick=\"show_rental_structure('"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:center; cursor:hand; }'   ><u>Payment Structure</u></td>"); 
					//out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >Paid - "+nf.format(rs.getDouble(19))+" &nbsp; Future - "+nf.format(rs.getDouble(18))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(27))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(13))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(14))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(15))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(16))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(8))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(7))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(9))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(10))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs.getString(3)+"</td>"); 
					out.println("</tr >");			
					*/
					
					
					
					/*rs2=stmt2.executeQuery(
					"SELECT "+
					"a.application_no, "+
					"a.asset_id, "+
					"a.make,"+
					"nvl(a.reg_no,'-'), "+
					"nvl(a.engine_no,'-'), "+
					"nvl(a.chassis_no,'-'), "+
					"nvl(a.year_of_manufacture,0),"+
					"a.asset_status "+
					"FROM "+m_schema_name+".af_misf_tbd_loan_faci_det a "+
					"WHERE ENT_USER='"+m_username+"' "+	
					"AND a.application_no='"+m_application_no+"' ");
					*/
					
					/*while(rs2.next()){
					if(j==1){
					out.println("<tr >");
					out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Id</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Make</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Reg No</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Engine No</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Chassis No</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Year Of Manufacture</td>"); 
					out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Status</td>"); 
					out.println("</tr >");
					}
					out.println("<tr  >");
					out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >"+j+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(2)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(3)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(4)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(5)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(6)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(7)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(8)+"</td>"); 
					out.println("</tr >");
					j+=1;
					}	*/
					
					//more=rs.next();
				}
				
				
				
				out.println("</table>");		 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			}
			
			else if(m_chksql.equals("print_report_new_all")){		
				
				String m_date="";
				String m_facility_code="";
				String m_officer="";
				String m_officer_name="";
				String m_location_desc="";
				String m_start_date="";
				String m_end_date="";
				
				if(req.getParameter("date")!=null ){
					m_date=req.getParameter("date").trim();
				}
				
				/*if(req.getParameter("facility_code")!=null ){
				m_facility_code=req.getParameter("facility_code").trim();
				}
			*/	
				stmt = conn.createStatement ();
				stmt2 = conn.createStatement ();
				
				
				if(req.getParameter("sort_column")!=null && req.getParameter("order_by_type")!=null){
					m_sort_column = req.getParameter("sort_column");
					m_order_by_type = req.getParameter("order_by_type");
				}
				
				else{	}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				
				out.println("function show_transaction_history_new(val,val2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+val2+'&client_code='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function add_client_comments(val_1,val_2){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=enter_comments&client_code='+val_1+'&application_no='+val_2;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				out.println("function show_followup(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_Follow_up?chksql=main_page&finance_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("function show_rental_structure(val){ "); 
				out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_MISF_Portfolio_Loan_Assign_Report?chksql=rental_schedule&application_no='+val;"); 
				out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=600,height=200,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
				out.println("}");
				
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");	
				
				String Sql_data="";
				
				Sql_data=" SELECT  "+
					" a.application_no, "+ //1
					" a.finance_no, "+ //2
					" a.loan_no,  "+ //3
					" a.client_code, "+ //4
					" a.client_full_name,"+ //5
					" a.client_address, "+  //6
					" a.total_rentals - nvl(a.rentals_paid,0) age , "+ //7
					" a.total_amount - (nvl(a.settled_amount,0) + nvl(a.adjusted_amount,0)) , "+ //8
					" nvl(a.arr_capital_portion,0), "+ //9
					" nvl(a.future_receivable,0), "+ //10
					" to_char(a.activated_date,'dd-mm-yyyy') activated_date,  "+ //11
					" a.payment_structure, "+ //12
					" NVL(a.facility_amount,0),  "+ //13
					" NVL(a.irr,0), "+ //14
					" NVL(a.flat_rate,0),  "+ //15
					" NVL(a.rental_amount,0), "+ //16
					" a.frequency ,"+ //17
					" nvl(a.TOTAL_PERIOD,0) - nvl(a.rentals_paid,0) , "+ //18
					" nvl(a.rentals_paid,0) "+ //19
					" FROM "+m_schema_name+".af_misf_tbd_loan_facility a "+
					" WHERE ENT_USER='"+m_username+"' ";
				//" AND UPPER(loan_no) like UPPER('%"+m_facility_code+"%') ";
				
				
				rs=stmt.executeQuery(Sql_data);
				
				boolean more=rs.next();
				int count=0;
				double closing_bal=0;
				double total_open_bal=0;
				double total_cur_due=0;
				double total_collection=0;
				double total_closing_bal=0;
				double achievement=0;
				double open_pre=0;
				double cur_pre=0;
				double col_pre=0;
				double closing_pre=0;
				double total_bal=0;
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Loan Facilities Report</u></td>"); 
				out.println("</tr >");
				out.println("</table >");
				out.println("<br>");
				out.println("<br>");
				
				
				if(!more){
					out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
					out.println("<tr class=pdn_txtpos2  >");
					out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>No Records To Display</u></td>"); 
					out.println("</tr >");
					out.println("</table >");
				}
				
				//=================================================
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\"  >"); //bordercolor='black' cellspacing=0
				if(more){
					out.println("<tr bgcolor=\"#CCCCCC\"  >");
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement No</td>"); 
					out.println("<td width=\"12%\" STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Name</td>"); 
					out.println("<td width=\"15%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Address</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Agreement Date</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Payment Structure</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Period Structure</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Facility Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >IRR</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Flat Rate</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Rent</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Frequency</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Due Amount</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Age</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Capital Receivable</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Future Receivable</td>"); 
					out.println("</tr >");
				}
				int j=1;
				double m_total_due=0,total_mon_rental=0,total_curr_due=0;
				String m_application_no="";
				while(more){
					j=1;
					m_application_no=rs.getString(1);
					
					out.println("<tr  >");
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(2)+"</td>"); 
					out.println("<td width=\"12%\" STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(5)+"</td>"); 
					out.println("<td width=\"15%\"  STYLE='{font: 8pt arial; text-align:center; }'    >"+rs.getString(6)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(11)+"</td>"); 
					out.println("<td width=\"7%\"  onClick=\"show_rental_structure('"+rs.getString(1)+"')\" STYLE='{font:  8pt arial; text-align:center; cursor:hand; }'   ><u>Payment Structure</u></td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >Paid - "+nf.format(rs.getDouble(19))+" &nbsp; Future - "+nf.format(rs.getDouble(18))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(13))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(14))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(15))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(16))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+rs.getString(17)+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(8))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(7))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(9))+"</td>"); 
					out.println("<td width=\"7%\"  STYLE='{font:  8pt arial; text-align:center;  }'   >"+nf.format(rs.getDouble(10))+"</td>"); 
					out.println("</tr >");			
					
					rs2=stmt2.executeQuery(
						"SELECT "+
						"a.application_no, "+
						"a.asset_id, "+
						"a.make,"+
						"nvl(a.reg_no,'-'), "+
						"nvl(a.engine_no,'-'), "+
						"nvl(a.chassis_no,'-'), "+
						"nvl(a.year_of_manufacture,0),"+
						"a.asset_status "+
						"FROM "+m_schema_name+".af_misf_tbd_loan_faci_det a "+
						"WHERE ENT_USER='"+m_username+"' "+	
						"AND a.application_no='"+m_application_no+"' ");
					
					while(rs2.next()){
						if(j==1){
							out.println("<tr >");
							out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Id</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Make</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Reg No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Engine No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Chassis No</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Year Of Manufacture</td>"); 
							out.println("<td width=\"7%\"  bgcolor=\"#CCCCCC\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >Asset Status</td>"); 
							out.println("</tr >");
						}
						out.println("<tr  >");
						out.println("<td width=\"7%\"  colspan='7' STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >&nbsp;</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: bold 8pt arial; text-align:center; cursor:hand; }'   >"+j+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(2)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(3)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(4)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(5)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(6)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(7)+"</td>"); 
						out.println("<td width=\"7%\"  STYLE='{font: 8pt arial; text-align:center; cursor:hand; }'   >"+rs2.getString(8)+"</td>"); 
						out.println("</tr >");
						j+=1;
					}	
					more=rs.next();
				}
				
				
				
				out.println("</table>");		 
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			}
			else if(m_chksql.equals("rental_schedule")){	
				stmt1 = conn.createStatement ();
				
				String m_application_no="";
				String m_no_of="",m_no_of_mon="";
				
				if(req.getParameter("application_no")!=null ){
					m_application_no=req.getParameter("application_no").trim();
				}
				
				
				out.println("<HTML>"); 
				out.println("<HEAD>"); 
				out.println("<TITLE>Finance - Loan Facilities Report </TITLE>"); 
				out.println("</HEAD>"); 
				out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
				out.println("<SCRIPT language=\"JavaScript\">"); 
				out.println("</script>"); 
				out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
				out.println("<br>");	
				out.println("<br>");	
				
				out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
				out.println("<tr class=pdn_txtpos2  >");
				out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   >Rental Break up : "+m_application_no+"</td>"); 
				out.println("</tr >");
				out.println("</table >");
				
				
				rs1=stmt1.executeQuery(" SELECT "+
					" DECODE(DURATION_TYPE,'Daily','Days','Monthly','Months','Weekly','Weeks','Quarterly','Quarters','Semi Annually','Half Years','Annually','Years','Once Every 4 Months','Once Every 4 Months' ) INTERVELS, "+ 
					" DECODE(DURATION_TYPE,'Daily','Day','Monthly','Month','Weekly','Week','Quarterly','Quarter','Semi Annually','Half Year','Annually','Year','Once Every 4 Months','Once Every 4 Month' ) INTERVEL "+ 
					" FROM "+m_schema_name+".AF_CO_MAS_REPAYMENT_INTERVAL "+
					" WHERE DURATION IN( "+
					" SELECT DISTINCT PAYMENT_INTERVAL "+
					" FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING "+ 
					" WHERE APPLICATION_NO=UPPER('"+m_application_no+"')) ");
				
				boolean more = rs1.next();
				
				if(more){
					m_no_of=rs1.getString(1);
					m_no_of_mon=rs1.getString(2);
				}
				
				
				
				String sql_rent_new="   SELECT  "+
					" TO_NUMBER(INSTALLMENT_NO)+1 , "+//1
					" SUM(NET_RENTAL_AMOUNT),  "+//2
					" SUM(GRENTAL_AMOUNT - NET_RENTAL_AMOUNT) VAT_AMOUNT,  "+//3
					" SUM(GRENTAL_AMOUNT), "+//4
					" TO_CHAR(RENTAL_DATE,'MON-YYYY') "+//5
					" FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT A "+
					" WHERE A.APPLICATION_NO=UPPER('"+m_application_no+"')  "+
					" GROUP BY   TO_NUMBER(INSTALLMENT_NO),RENTAL_DATE "+ 
					" ORDER BY TO_NUMBER(INSTALLMENT_NO) ";
				
				int end=0;
				int start=0;
				String m_ins="";
				double m_rental_new=0;
				double m_vat_new=0;
				double m_gross_new=0;
				int count_period=0;
				String rental_start_date="";
				String rental_end_date="";
				
				rs1 = stmt1.executeQuery(sql_rent_new);
				
				boolean more3 =rs1.next();
				
				if(more3)
				{
					m_rental_new=rs1.getDouble(2);
					start=rs1.getInt(1);
					rental_start_date=rs1.getString(5);
					m_vat_new=rs1.getDouble(3);
					m_gross_new=rs1.getDouble(4);
					
					
					while(more3) //START INSTALLMENT LOOP
					{
						
						
						if(m_rental_new!=rs1.getDouble(2))
						{
							out.println("<table border='0' width='80%' class='table'>"); 		
							out.println("<tr><td width='5%' class='rep-body1' ><b>&nbsp;</b></td>");
							out.println("    <td width='35%' class='rep-body1' ><b>&nbsp;</b></td>");
							if(start==end){
								out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of_mon+" ,"+rental_start_date+" - "+rental_start_date+")  </b></td>");
								out.println("<tr></tr>");
							}
							else{
								out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of+" ,"+rental_start_date+" - "+rental_end_date+")  </b></td>");
								out.println("<tr></tr>");
							}
							out.println("</table>");
							
							start=rs1.getInt(1);		
							rental_start_date=rs1.getString(5);
							m_rental_new=rs1.getDouble(2);
							m_vat_new=rs1.getDouble(3);
							m_gross_new=rs1.getDouble(4);
							count_period=0;
						}
						
						count_period=count_period+1;
						end=rs1.getInt(1);
						rental_end_date=rs1.getString(5);
						
						more3=rs1.next();
						
						if(!more3)
						{
							break;
						}
						
					}
					
					
					out.println("<table border='0' width='80%' class='table'>"); 		
					out.println("<tr><td width='5%' class='rep-body1' ><b>&nbsp;</b></td>");
					out.println("    <td width='35%' class='rep-body1' ><b>&nbsp;</b></td>");
					if(start==end){
						out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of_mon+" ,"+rental_start_date+" - "+rental_start_date+")  </b></td>");
						out.println("<tr></tr>");
					}
					else{
						out.println("    <td width='*%' class='rep-body1' ><b>&nbsp;&nbsp;Rs."+nf.format(m_gross_new)+" - ("+count_period+" "+m_no_of+" ,"+rental_start_date+" - "+rental_end_date+")  </b></td>");
						out.println("<tr></tr>");
					}
					out.println("</table>");
					
				}
				
				
				out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
				out.println("</body>");
				out.println("</html>");
			}
			
		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
			if(rs!=null){try{rs.close();  }catch(Exception e){}}
			if(rs2!=null){try{rs2.close();  }catch(Exception e){}}
			if(stmt!=null){try{stmt.close();  }catch(Exception e){}}
			if(stmt2!=null){try{stmt2.close();  }catch(Exception e){}}
			if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
