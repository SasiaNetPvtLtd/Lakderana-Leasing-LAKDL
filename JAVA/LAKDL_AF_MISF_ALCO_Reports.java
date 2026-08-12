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
 

public class LAKDL_AF_MISF_ALCO_Reports extends javax.servlet.http.HttpServlet { 

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
												
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_MISF_SAVE_ALCO_RPT(:1,:2); END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_username);
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
			out.println("<TITLE>Finance - ALCO Reports</TITLE>"); 
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
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=run_report&date=\"+m_date;"); 
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
				
				out.println("		m_report_type=document.Form1.TXT_REPORT_TYPE.value;");
				
				
				out.println("if(m_report_type=='RATE_ACT'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=RATE_ACT&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='RATE_BRANCH'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=RATE_BRANCH&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='CREDIT_RATE'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=CREDIT_RATE&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='MKT_EXECUT'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=MKT_EXECUT&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='LEN_RATE'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=LEN_RATE&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='LEN_RATE_BRANCH'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=LEN_RATE_BRANCH&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='PERIOD'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=PERIOD&date=\"+m_date;");	
				out.println("	}");
				out.println("else if(m_report_type=='ASSET_LIABILITY'  ){");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=ASSET_LIABILITY&date=\"+m_date;");	
				out.println("	}");
				
				out.println("			window.open(m_url);");
				
        out.println("}");
				out.println("}");
			


      out.println("function get_vector(data_vec) {");
			
			out.println("}");
			
				
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=main_page&generate=page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_ALCO_Reports?chksql=main_page&generate=page';"); 
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
			out.println("help_box.innerHTML=\" Finance Process - ALCO Reports - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Finance Process - ALCO Reports - \"+document.Form1.hid_status.value;"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Finance Process - ALCO Reports</td>"); 
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
				out.println("<td width='20%' ><DIV id='DIV_TXT_REPORT_TYPE'  class=div_input>Report Type</DIV></td>"); 
				out.println("<td width='60%' ><SELECT onchange=\"\" name=\"TXT_REPORT_TYPE\" style=\"{width:200px;}\" class=\"txt_input\" > ");
				out.println("<OPTION value=\"RATE_ACT\" >Rate Activity</OPTION>");
				out.println("<OPTION value=\"RATE_BRANCH\" >Rate Branch</OPTION>");
				out.println("<OPTION value=\"CREDIT_RATE\" >Credit Rating</OPTION>");
				out.println("<OPTION value=\"MKT_EXECUT\" >Mkt Executive</OPTION>");
				out.println("<OPTION value=\"LEN_RATE\" >Lending Rates</OPTION>");
				out.println("<OPTION value=\"LEN_RATE_BRANCH\" >Lending Rates Branch Level</OPTION>");
				out.println("<OPTION value=\"PERIOD\" >Period</OPTION>");
				out.println("<OPTION value=\"MARGIN\" >Margin</OPTION>");
				out.println("<OPTION value=\"ASSET_LIABILITY\" >Asset Liability Profile</OPTION>");
				out.println("</SELECT></td>");
				out.println("<td width='*%'></td>");								
				out.println("</tr>"); 
					
			
			  out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='60%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("<td width='*%'></td>");								
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
		
		else if(m_chksql.equals("MKT_EXECUT")){		
		
		String m_date="",m_report_date="",m_trn_type="";
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Finance - ALCO Reports Credit Activity </TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("</script>"); 
		out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
		out.println("<br>");	
		out.println("<br>");	
						
		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		out.println("<tr >");
		out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Average rate for New Businesses - By Marketing Executive</u></td>"); 
		out.println("</tr >");
		out.println("</table >");
		out.println("<br>");
		out.println("<br>");
			
		rs=stmt.executeQuery(
		"SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON-YY')                , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'MON-YY') , "+
		
		"       TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')  "+
		"FROM DUAL ");
		
		boolean more=rs.next();
	
     out.println("	<table width='100%'  align=\"center\" border=\"1\" class=\"table\" cellspacing='0' cellpadding='0'>");

			if(more){
			m_report_date=rs.getString(13);
			
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td rowspan='2' align=\"center\"><b>Activity</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(12)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(11)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(10)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(9)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(8)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(7)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(6)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(5)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(4)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(3)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(2)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(1)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>LAST 12 MONTHS</td>");
			out.println("	</tr>");
			
			//1
			out.println("	<tr bgcolor=#CCCCCC>");
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//2
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//3
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//4
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//5
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//6
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//7
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//8
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//9
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//10
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//11
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//12
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//13
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			out.println("	</tr>");
			
			}
			
			rs=stmt.executeQuery(
			" SELECT  DISTINCT A.transaction_type ,  a.mkt_executive , "+m_schema_name+".AF_CO_GET_EMP_NAME(a.mkt_executive) ,"+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.transaction_type)  "+
      " FROM    "+m_schema_name+".af_tbd_alco_rpt A "+
			" WHERE   ent_user='"+m_username+"'  ORDER BY  A.transaction_type , a.mkt_executive ");
      more=rs.next();
			
			while(more){
      //while(rs.next()){
			m_trn_type=rs.getString(1);
			out.println("	<tr>");
			out.println("	<td colspan='40' align='left'><u>"+rs.getString(4)+"</u<</td>");
			out.println("	</tr>");
			
			while(m_trn_type.equals(rs.getString(1))){
			
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.MKT_EXECUTIVE='"+rs.getString(2)+"' )M ");

			if(rs1.next()){
						
			
			out.println("	<tr bgcolor=#FFCC99 >");
			out.println("	<td align='left'><B>"+rs.getString(3)+"</td>");
			//1
			out.println("	<td align='right'>"+rs1.getInt(1)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
			//2
			out.println("	<td align='right'>"+rs1.getInt(4)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
			//3
			out.println("	<td align='right'>"+rs1.getInt(7)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			//4
      out.println("	<td align='right'>"+rs1.getInt(10)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
			//5
      out.println("	<td align='right'>"+rs1.getInt(13)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
			//6
			out.println("	<td align='right'>"+rs1.getInt(16)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
			//7
			out.println("	<td align='right'>"+rs1.getInt(19)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
			//8
			out.println("	<td align='right'>"+rs1.getInt(22)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(23))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(24))+"</td>");
			
			//9
			out.println("	<td align='right'>"+rs1.getInt(25)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(26))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(27))+"</td>");
			//10
			out.println("	<td align='right'>"+rs1.getInt(28)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(29))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(30))+"</td>");
			//11
			out.println("	<td align='right'>"+rs1.getInt(31)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(32))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(33))+"</td>");
			//12
			out.println("	<td align='right'>"+rs1.getInt(34)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(35))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(36))+"</td>");
			//13
			out.println("	<td align='right'>"+rs1.getInt(37)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(38))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(39))+"</td>");
			out.println("	</tr>");
			}
			
			more=rs.next();
			if(!more){break;}
			
			}
			
			
			}
			
		out.println("	</table>");
						
		out.println("</table>");		 
			
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
		
    }
		
		
		else if(m_chksql.equals("LEN_RATE")){		
		
		String m_date="",m_report_date="",m_trn_type="";
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Finance - ALCO Reports Credit Activity </TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("</script>"); 
		out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
		out.println("<br>");	
		out.println("<br>");	
						
		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		out.println("<tr >");
		out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>New Businesses - Lending Rate Structure</u></td>"); 
		out.println("</tr >");
		out.println("</table >");
		out.println("<br>");
		out.println("<br>");
			
		rs=stmt.executeQuery(
		"SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON-YY')                , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'MON-YY') , "+
		
		"       TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')  "+
		"FROM DUAL ");
		
		boolean more=rs.next();
	
     out.println("	<table width='100%'  align=\"center\" border=\"1\" class=\"table\" cellspacing='0' cellpadding='0'>");

			if(more){
			m_report_date=rs.getString(13);
			
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td rowspan='2' align=\"center\"><b>Activity</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(12)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(11)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(10)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(9)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(8)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(7)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(6)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(5)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(4)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(3)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(2)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(1)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>LAST 12 MONTHS</td>");
			out.println("	</tr>");
			
			//1
			out.println("	<tr bgcolor=#CCCCCC>");
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//2
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//3
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//4
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//5
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//6
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//7
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//8
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//9
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//10
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//11
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//12
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//13
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			out.println("	</tr>");
			
			}
			
			rs=stmt.executeQuery(
			" SELECT  DISTINCT A.transaction_type ,"+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.transaction_type)  "+
      " FROM    "+m_schema_name+".af_tbd_alco_rpt A "+
			" WHERE   ent_user='"+m_username+"'  ORDER BY  A.transaction_type ");
      more=rs.next();
			
			int count_rate=0,m_rate1=0,m_rate2=0;
			while(more){
      //while(rs.next()){
			m_trn_type=rs.getString(1);
			out.println("	<tr>");
			out.println("	<td colspan='40' align='left'><u>"+rs.getString(2)+"</u<</td>");
			out.println("	</tr>");
			
			while(m_trn_type.equals(rs.getString(1))){
			count_rate=0;
			
			while(count_rate<9){
			
			if(count_rate==0){
			m_rate1=17;
			}
			else if(count_rate==1){
			m_rate1=17;
			m_rate2=19;
			}
			else if(count_rate==2){
			m_rate1=19;
			m_rate2=21;
			}
			else if(count_rate==3){
			m_rate1=21;
			m_rate2=23;
			}
			else if(count_rate==4){
			m_rate1=23;
			m_rate2=25;
			}
			else if(count_rate==5){
			m_rate1=25;
			m_rate2=27;
			}
			else if(count_rate==6){
			m_rate1=27;
			m_rate2=31;
			}
			else if(count_rate==7){
			m_rate1=31;
			m_rate2=35;
			}
			else if(count_rate==8){
			m_rate1=35;
			}
			
			if(count_rate==0){
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR < "+m_rate1+" )M ");

			}
			
			else	if(count_rate==8){
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+"  )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR > "+m_rate1+" )M ");

			}
			else{
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )M ");
      }
			
			
			if(rs1.next()){
						
			
			out.println("	<tr bgcolor=#FFCC99 >");
			if(count_rate==0){
			out.println("	<td align='left'><B>"+m_rate1+"% <</td>");
			}else if(count_rate==8){
			out.println("	<td align='left'><B>"+m_rate1+" % > </td>");
			
			}
			else{
			out.println("	<td align='left'><B>"+m_rate1+" % - "+m_rate2+"% </td>");
			}
			
			//1
			out.println("	<td align='right'>"+rs1.getInt(1)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
			//2
			out.println("	<td align='right'>"+rs1.getInt(4)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
			//3
			out.println("	<td align='right'>"+rs1.getInt(7)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			//4
      out.println("	<td align='right'>"+rs1.getInt(10)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
			//5
      out.println("	<td align='right'>"+rs1.getInt(13)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
			//6
			out.println("	<td align='right'>"+rs1.getInt(16)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
			//7
			out.println("	<td align='right'>"+rs1.getInt(19)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
			//8
			out.println("	<td align='right'>"+rs1.getInt(22)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(23))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(24))+"</td>");
			
			//9
			out.println("	<td align='right'>"+rs1.getInt(25)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(26))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(27))+"</td>");
			//10
			out.println("	<td align='right'>"+rs1.getInt(28)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(29))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(30))+"</td>");
			//11
			out.println("	<td align='right'>"+rs1.getInt(31)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(32))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(33))+"</td>");
			//12
			out.println("	<td align='right'>"+rs1.getInt(34)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(35))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(36))+"</td>");
			//13
			out.println("	<td align='right'>"+rs1.getInt(37)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(38))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(39))+"</td>");
			out.println("	</tr>");
			}
			
			count_rate=count_rate+1;
			} //end count_rate while
			
			more=rs.next();
			if(!more){break;}
			
			}
			
			
			}
			
		out.println("	</table>");
						
		out.println("</table>");		 
			
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
		
    }
		
		else if(m_chksql.equals("LEN_RATE_BRANCH")){		
		
		String m_date="",m_report_date="",m_trn_type="";
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Finance - ALCO Reports Credit Activity </TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("</script>"); 
		out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
		out.println("<br>");	
		out.println("<br>");	
						
		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		out.println("<tr >");
		out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>New Businesses - Lending Rate Structure Branch Level</u></td>"); 
		out.println("</tr >");
		out.println("</table >");
		out.println("<br>");
		out.println("<br>");
			
		rs=stmt.executeQuery(
		"SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON-YY')                , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'MON-YY') , "+
		
		"       TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')  "+
		"FROM DUAL ");
		
		boolean more=rs.next();
	
     out.println("	<table width='100%'  align=\"center\" border=\"1\" class=\"table\" cellspacing='0' cellpadding='0'>");

			if(more){
			m_report_date=rs.getString(13);
			
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td rowspan='2' align=\"center\"><b>Activity</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(12)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(11)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(10)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(9)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(8)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(7)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(6)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(5)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(4)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(3)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(2)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(1)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>LAST 12 MONTHS</td>");
			out.println("	</tr>");
			
			//1
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//2
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//3
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//4
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//5
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//6
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//7
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//8
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//9
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//10
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//11
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//12
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//13
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			out.println("	</tr>");
			
			}
			
			
			rs=stmt.executeQuery(
			" SELECT  DISTINCT A.transaction_type, "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.transaction_type),NVL(A.location_code,'UNDEFINED') location_code , "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.location_code)  "+
      " FROM    "+m_schema_name+".af_tbd_alco_rpt A "+
			" WHERE   ent_user='"+m_username+"'  ORDER BY  A.transaction_type, location_code ");
			
      more=rs.next();
			
			int count_rate=0,m_rate1=0,m_rate2=0;
			while(more){
   		m_trn_type=rs.getString(1);
			out.println("	<tr>");
			out.println("	<td colspan='40' align='left'><u>"+rs.getString(2)+"</u<</td>");
			out.println("	</tr>");
			
			while(m_trn_type.equals(rs.getString(1))){
			
			out.println("	<tr>");
			out.println("	<td colspan='40' align='left'><u>"+rs.getString(4)+"</u<</td>");
			out.println("	</tr>");
			count_rate=0;
			
			while(count_rate<9){
			
			if(count_rate==0){
			m_rate1=17;
			}
			else if(count_rate==1){
			m_rate1=17;
			m_rate2=19;
			}
			else if(count_rate==2){
			m_rate1=19;
			m_rate2=21;
			}
			else if(count_rate==3){
			m_rate1=21;
			m_rate2=23;
			}
			else if(count_rate==4){
			m_rate1=23;
			m_rate2=25;
			}
			else if(count_rate==5){
			m_rate1=25;
			m_rate2=27;
			}
			else if(count_rate==6){
			m_rate1=27;
			m_rate2=31;
			}
			else if(count_rate==7){
			m_rate1=31;
			m_rate2=35;
			}
			else if(count_rate==8){
			m_rate1=35;
			}
			
			if(count_rate==0){
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR < "+m_rate1+" )M ");

			}
			
			else	if(count_rate==8){
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+"  )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR > "+m_rate1+" )M ");

			}
			else{
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(3)+"' AND  A.IRR >= "+m_rate1+" AND  A.IRR < "+m_rate2+" )M ");
      }
			
			
			if(rs1.next()){
						
			
			out.println("	<tr bgcolor=#FFCC99 >");
			if(count_rate==0){
			out.println("	<td align='left'><B>"+m_rate1+"% <</td>");
			}else if(count_rate==8){
			out.println("	<td align='left'><B>"+m_rate1+" % > </td>");
			
			}
			else{
			out.println("	<td align='left'><B>"+m_rate1+" % - "+m_rate2+"% </td>");
			}
			
			//1
			out.println("	<td align='right'>"+rs1.getInt(1)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
			//2
			out.println("	<td align='right'>"+rs1.getInt(4)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
			//3
			out.println("	<td align='right'>"+rs1.getInt(7)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			//4
      out.println("	<td align='right'>"+rs1.getInt(10)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
			//5
      out.println("	<td align='right'>"+rs1.getInt(13)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
			//6
			out.println("	<td align='right'>"+rs1.getInt(16)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
			//7
			out.println("	<td align='right'>"+rs1.getInt(19)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
			//8
			out.println("	<td align='right'>"+rs1.getInt(22)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(23))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(24))+"</td>");
			
			//9
			out.println("	<td align='right'>"+rs1.getInt(25)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(26))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(27))+"</td>");
			//10
			out.println("	<td align='right'>"+rs1.getInt(28)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(29))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(30))+"</td>");
			//11
			out.println("	<td align='right'>"+rs1.getInt(31)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(32))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(33))+"</td>");
			//12
			out.println("	<td align='right'>"+rs1.getInt(34)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(35))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(36))+"</td>");
			//13
			out.println("	<td align='right'>"+rs1.getInt(37)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(38))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(39))+"</td>");
			out.println("	</tr>");
			}
			
			count_rate=count_rate+1;
			} //end count_rate while
			
			more=rs.next();
			if(!more){break;}
			
			}
			
			
			}
			
		out.println("	</table>");
						
		out.println("</table>");		 
			
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
		
    }
		
		
		else if(m_chksql.equals("RATE_BRANCH")){		
		
		String m_date="",m_report_date="",m_trn_type="";
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Finance - ALCO Reports Credit Activity </TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("</script>"); 
		out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
		out.println("<br>");	
		out.println("<br>");	
						
		out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
		out.println("<tr >");
		out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Average rate for New Businesses - By Branch</u></td>"); 
		out.println("</tr >");
		out.println("</table >");
		out.println("<br>");
		out.println("<br>");
			
		rs=stmt.executeQuery(
		"SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON-YY')                , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'MON-YY') , "+
		
		"       TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')  "+
		"FROM DUAL ");
		
		boolean more=rs.next();
	
     out.println("	<table width='100%'  align=\"center\" border=\"1\" class=\"table\" cellspacing='0' cellpadding='0'>");

			if(more){
			m_report_date=rs.getString(13);
			
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td rowspan='2' align=\"center\"><b>Activity</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(12)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(11)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(10)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(9)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(8)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(7)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(6)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(5)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(4)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(3)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(2)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(1)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>LAST 12 MONTHS</td>");
			out.println("	</tr>");
			
			//1
			out.println("	<tr >");
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//2
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//3
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//4
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//5
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//6
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//7
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//8
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//9
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//10
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//11
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//12
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//13
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			out.println("	</tr>");
			
			}
			
			rs=stmt.executeQuery(
			" SELECT  DISTINCT A.transaction_type, NVL(A.location_code,'UNDEFINED') location_code , "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.location_code) , "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.transaction_type) "+
      " FROM    "+m_schema_name+".af_tbd_alco_rpt A "+
			" WHERE   ent_user='"+m_username+"'  ORDER BY  A.transaction_type, location_code ");
      more=rs.next();
			
			while(more){
      //while(rs.next()){
			m_trn_type=rs.getString(1);
			
			out.println("	<tr>");
			out.println("	<td colspan='40' align='left'><u>"+rs.getString(4)+"</u<</td>");
			out.println("	</tr>");
			
			while(m_trn_type.equals(rs.getString(1))){
			
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"' AND  A.LOCATION_CODE='"+rs.getString(2)+"' )M ");

			if(rs1.next()){
						
			
			out.println("	<tr bgcolor=#FFCC99 >");
			out.println("	<td align='left'><B>"+rs.getString(3)+"</td>");
			//1
			out.println("	<td align='right'>"+rs1.getInt(1)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
			//2
			out.println("	<td align='right'>"+rs1.getInt(4)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
			//3
			out.println("	<td align='right'>"+rs1.getInt(7)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			//4
      out.println("	<td align='right'>"+rs1.getInt(10)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
			//5
      out.println("	<td align='right'>"+rs1.getInt(13)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
			//6
			out.println("	<td align='right'>"+rs1.getInt(16)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
			//7
			out.println("	<td align='right'>"+rs1.getInt(19)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
			//8
			out.println("	<td align='right'>"+rs1.getInt(22)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(23))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(24))+"</td>");
			
			//9
			out.println("	<td align='right'>"+rs1.getInt(25)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(26))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(27))+"</td>");
			//10
			out.println("	<td align='right'>"+rs1.getInt(28)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(29))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(30))+"</td>");
			//11
			out.println("	<td align='right'>"+rs1.getInt(31)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(32))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(33))+"</td>");
			//12
			out.println("	<td align='right'>"+rs1.getInt(34)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(35))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(36))+"</td>");
			//13
			out.println("	<td align='right'>"+rs1.getInt(37)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(38))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(39))+"</td>");
			out.println("	</tr>");
			}
			
			more=rs.next();
			if(!more){break;}
			
			}
			
			
			}
			
		out.println("	</table>");
						
		out.println("</table>");		 
			
		out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
		out.println("</body>");
		out.println("</html>");
		
    }
		else if(m_chksql.equals("RATE_ACT")){		
		
		String m_date="",m_report_date="";
		if(req.getParameter("date")!=null ){
		m_date=req.getParameter("date").trim();
		}
		
		out.println("<HTML>"); 
		out.println("<HEAD>"); 
		out.println("<TITLE>Finance - ALCO Reports Credit Activity </TITLE>"); 
		out.println("</HEAD>"); 
		out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
		out.println("<SCRIPT language=\"JavaScript\">"); 
		
		out.println("</script>"); 
		out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' > ");
		out.println("<br>");	
		out.println("<br>");	
			
			
			out.println("<table align=\"center\" width=\"95%\" border=\"0\" class=\"table\">");
			out.println("<tr >");
			out.println("<td width=\"*\" STYLE='{font: bold 10pt arial; text-align:center;}'   ><u>Average rate for New Businesses - By Activity</u></td>"); 
			out.println("</tr >");
			out.println("</table >");
			out.println("<br>");
			out.println("<br>");
			
			
		rs=stmt.executeQuery(
		"SELECT TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'MON-YY')                , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-1),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-2),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-3),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-4),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-5),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-6),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-7),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-8),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-9),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-10),'MON-YY') , "+
		"       TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MM-YYYY'),-11),'MON-YY') , "+
		
		"       TO_CHAR(TO_DATE('"+m_date+"','DD-MM-YYYY'),'DD-MM-YYYY')  "+
		"FROM DUAL ");
		
		boolean more=rs.next();
	

      out.println("	<table width='100%'  align=\"center\" border=\"1\" class=\"table\" cellspacing='0' cellpadding='0'>");

			if(more){
			m_report_date=rs.getString(13);
			
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td rowspan='2' align=\"center\"><b>Activity</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(12)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(11)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(10)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(9)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(8)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(7)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(6)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(5)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(4)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(3)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(2)+"</td>");
			out.println("	<td colspan='3' align=\"center\"><b>"+rs.getString(1)+"</td>");
			
			out.println("	<td colspan='3' align=\"center\"><b>LAST 12 MONTHS</td>");
			out.println("	</tr>");
			
			//1
			out.println("	<tr bgcolor=#CCCCCC >");
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//2
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//3
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//4
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//5
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//6
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//7
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//8
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//9
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//10
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//11
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//12
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			//13
			out.println("	<td align='right'>No</td>");
			out.println("	<td align='right'>Volume</td>");
			out.println("	<td align='right'>Weighted Average</td>");
			out.println("	</tr>");
			
			}
			
			rs=stmt.executeQuery(
			" SELECT  DISTINCT A.transaction_type, "+m_schema_name+".AF_CO_GET_TRANSACTION_DESC(A.transaction_type) "+
      " FROM    "+m_schema_name+".af_tbd_alco_rpt A "+
			" WHERE   ent_user='"+m_username+"' ");

      while(rs.next()){
			
			rs1=stmt2.executeQuery(
			" SELECT  "+
			" A.NUM,A.AMOUNT,ROUND(A.WI,2) WI, "+
			" B.NUM,B.AMOUNT,ROUND(B.WI,2) WI, "+
			" C.NUM,C.AMOUNT,ROUND(C.WI,2) WI, "+
			" D.NUM,D.AMOUNT,ROUND(D.WI,2) WI, "+
			" E.NUM,E.AMOUNT,ROUND(E.WI,2) WI, "+
			" F.NUM,F.AMOUNT,ROUND(F.WI,2) WI, "+
			" G.NUM,G.AMOUNT,ROUND(G.WI,2) WI, "+
			" H.NUM,H.AMOUNT,ROUND(H.WI,2) WI, "+
			" I.NUM,I.AMOUNT,ROUND(I.WI,2) WI, "+
			" J.NUM,J.AMOUNT,ROUND(J.WI,2) WI, "+
			" K.NUM,K.AMOUNT,ROUND(K.WI,2) WI, "+
			" L.NUM,L.AMOUNT,ROUND(L.WI,2) WI, "+
			" M.NUM,M.AMOUNT,ROUND(M.WI,2) WI "+
			
			" FROM "+ 
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-12)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')A, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')B, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-10)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')C, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-9)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')D, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-8)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')E, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-7)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')F, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-6)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')G, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-5)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')H, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-4)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')I, "+
			
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-3)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')J, "+
			
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE   A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-2)) "+
			" AND     A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')K, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM   "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-1)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),0)) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')L, "+
						
			" (SELECT COUNT(A.FINANCE_NO) NUM ,SUM(A.FACILITY_AMOUNT) AMOUNT, (SUM(A.FACILITY_AMOUNT*A.IRR) / SUM(A.FACILITY_AMOUNT)) WI  "+
			" FROM    "+m_schema_name+".AF_TBD_ALCO_RPT A "+
			" WHERE A.ACTIVATED_DATE > LAST_DAY(ADD_MONTHS(TO_DATE('"+m_report_date+"','DD-MM-YYYY'),-11)) "+
			" AND   A.ACTIVATED_DATE <=LAST_DAY(TO_DATE('"+m_report_date+"','DD-MM-YYYY')) "+
			" AND     A.TRANSACTION_TYPE='"+rs.getString(1)+"')M ");

			if(rs1.next()){
			out.println("	<tr bgcolor=#FFCC99 >");
			out.println("	<td align='left'><B>"+rs.getString(2)+"</td>");
			//1
			out.println("	<td align='right'>"+rs1.getInt(1)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(2))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(3))+"</td>");
			//2
			out.println("	<td align='right'>"+rs1.getInt(4)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(5))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(6))+"</td>");
			//3
			out.println("	<td align='right'>"+rs1.getInt(7)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(8))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(9))+"</td>");
			//4
      out.println("	<td align='right'>"+rs1.getInt(10)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(11))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(12))+"</td>");
			//5
      out.println("	<td align='right'>"+rs1.getInt(13)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(14))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(15))+"</td>");
			//6
			out.println("	<td align='right'>"+rs1.getInt(16)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(17))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(18))+"</td>");
			//7
			out.println("	<td align='right'>"+rs1.getInt(19)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(20))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(21))+"</td>");
			//8
			out.println("	<td align='right'>"+rs1.getInt(22)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(23))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(24))+"</td>");
			
			//9
			out.println("	<td align='right'>"+rs1.getInt(25)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(26))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(27))+"</td>");
			//10
			out.println("	<td align='right'>"+rs1.getInt(28)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(29))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(30))+"</td>");
			//11
			out.println("	<td align='right'>"+rs1.getInt(31)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(32))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(33))+"</td>");
			//12
			out.println("	<td align='right'>"+rs1.getInt(34)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(35))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(36))+"</td>");
			//13
			out.println("	<td align='right'>"+rs1.getInt(37)+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(38))+"</td>");
			out.println("	<td align='right'>"+nf.format(rs1.getDouble(39))+"</td>");
			out.println("	</tr>");
			}
			
			}
			
		out.println("	</table>");
						
		out.println("</table>");		 
			
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
