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
 

public class LAKDL_AF_MISF_CMS_Details extends javax.servlet.http.HttpServlet { 


	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
		
				ServletOutputStream out = null;
				Connection conn=null;
				java.text.NumberFormat nf,nf1;
				java.lang.Math a;
				Statement stmt=null;
				Statement stmt2=null;
				Statement statement1=null;
				CallableStatement callstmt1 =null;
				ResultSet rs=null;
				ResultSet rs2=null;
				ResultSet resultSet1=null;
		 
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
			
			String m_chksql=req.getParameter("chksql");
			
			  String m_sort_column   = "FINANCE_NO";	
				String m_order_by_type = "ASC";

			if(m_chksql.equals("run_report")){ 
			
				String m_date=req.getParameter("date");
				String m_location_id=req.getParameter("location_id");
				String m_user_id=req.getParameter("user_id");
				//String m_finance_no = req.getParameter("finance_no");
				
				try{
				callstmt1 = conn.prepareCall("BEGIN "+m_schema_name+".AF_RPT_COLLECTION_FIGURES(:1,:2,:3,:4);END;");
				callstmt1.setString(1,m_date);
				callstmt1.setString(2,m_location_id);
				callstmt1.setString(3,m_user_id);
				callstmt1.setString(4,m_username);
				callstmt1.execute();
				out.print("OK"); 
				}
				catch(Exception ex){
				out.println("ERROR"+ex.toString()); 
				}

			}
			
			
			if(m_chksql.equals("main_page")){ 
			
			stmt2 = conn.createStatement ();
			rs2= stmt2.executeQuery(" SELECT TO_CHAR(SYSDATE,'DD'),TO_CHAR(SYSDATE,'MM'),TO_CHAR(SYSDATE,'YYYY') FROM DUAL ");

				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Collection Report </TITLE>"); 
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
				//out.println("finance_no = document.Form1.TXT_FINANCE.value;");
				//out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=run_report&finance_no=\"+finance_no+\"&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); 
				out.println("		m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=run_report&date=\"+m_date+\"&location_id=\"+document.Form1.TXT_LOCATION_CODE.value+\"&user_id=\"+document.Form1.TXT_USER.value;"); 
				//out.println("		window.open(m_url)");
				out.println("   set_timer_actions();");
				out.println("		load_interface(m_url,'NORM');");
				out.println("	}");
				out.println("}");
				
				out.println("function get_vector_normal(m_data){");
				out.println("		if(m_data==\"OK\"){");
				//out.println("			print_report2();"); 
				out.println("			alert('Report Generated Successfully');");  //added by ns on 29-04-2010
				out.println("		  m_table.innerHTML=\"\";");
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
				out.println("		m_location=document.Form1.TXT_LOCATION_CODE.value;");
				out.println("		m_officer=document.Form1.TXT_USER.value;");
				out.println("		m_rpt_type=document.Form1.CONTRACT_STATUS.value;");
				//out.println("		var m_rpt_type='';");
				out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=load_report&rpt_type=\"+m_rpt_type+\"&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+m_date;");	
				out.println("			window.open(m_url);");
				out.println("	}");
				out.println("}");
				
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_USER.value!=\"\" && document.Form1.hid_chk_status.value=='M_USER' ){");
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
								
			out.println("}");
			

				out.println("function drill_down_asset(m_finance_no) {");
				
				out.println("	m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=main_page&generate=drill_down_asset&finance_no=\"+m_finance_no;"); 
				
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=main_page&generate=page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=main_page&generate=page';"); 
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
			out.println("help_box.innerHTML=\" Collection Process - Rental Collection Report - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection Process - Rental Collection Report - \"+document.Form1.hid_status.value;"); 
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
			out.println("		if(IfCount==\"4\"){"); 
			out.println("		help_value_assign_finance(oBj);"); 
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
			
			out.println("function help_button_finance() {"); 
			out.println(" document.Form1.hid_help_type.value='4' ");
			out.println("    Crit = document.Form1.TXT_FINANCE.value+\"@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_FINANCE_NO_branch_sql','4');"); 
			out.println("}"); 
			
			out.println("function help_value_assign_finance(oBj) {"); 
			out.println("    document.Form1.TXT_FINANCE.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
			out.println("    document.Form1.TXT_USER.value=oBj.valout[9];"); 
			out.println("}");
				
			out.println("function help_update() {"); 
			out.println("    m_sql = \"m_help_TXT_LOCATION_CODE_sql\";"); 
			out.println("    m_criteria = document.Form1.TXT_LOCATION_CODE.value+\"@\"+\"Y@\";"); 
			out.println("    HelpBox('1','10','0',m_criteria,m_sql,'99');"); 
			out.println("}"); 
			
			out.println("function help_update_value_assign_99() {"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[2];"); 
			out.println("}"); 

					
			out.println("function help_button_user() {"); 
			out.println(" document.Form1.hid_help_type.value='3' ");
			out.println("    Crit = document.Form1.TXT_USER.value+\"@\"+document.Form1.TXT_LOCATION_CODE.value+\"@Y@\";"); 
			out.println("    HelpBox('1','10','0',Crit,'m_help_marketing_officer','3');"); 
			out.println("}"); 
			

			out.println("function help_value_assign_user(oBj) {"); 
			out.println("    document.Form1.TXT_USER.value=oBj.valout[2];"); 
			out.println("    document.Form1.TXT_LOCATION_CODE.value=oBj.valout[7];"); 
			out.println("}"); 
			
			out.println("function clear_data(IfCount) {");
			out.println("		if(IfCount==\"99\"){"); 
			out.println("document.Form1.TXT_LOCATION_CODE.value='';");
	  	out.println("		}"); 
			out.println("		if(IfCount==\"3\"){"); 
			out.println("document.Form1.TXT_USER.value='';");
	  	out.println("		}"); 
			out.println("}");
			
     
			out.println("function get_rental_dates(date,m_client_code,m_officer){");
			
			out.println("if(validate_data()){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Movement_Report?chksql=main_page&generate=detail&client_code=\"+m_client_code+\"&officer=\"+m_officer+\"&date=\"+date;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("}"); 
			
			
			  out.println("function print_report(date,m_location,m_officer) {");
			 	out.println("if(validate_data()){");
			  out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=main_page&generate=print_report&location=\"+m_location+\"&officer=\"+m_officer+\"&date=\"+date;");	
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection Process - Rental Collection Report </td>"); 
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
			
				out.println("<tr>"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_CONTACT_STATUS'  class=div_input>Report Type </DIV></td>"); 
				out.println("<td width='*%' ><select class='txt_input' name='CONTRACT_STATUS' style='width:100'>"); 
				out.println("<option value='ARREARS' >Arrears</option>");
				out.println("<option value='DUE' selected >Due</option>");
				//out.println("<option value='ALL'>All</option></select>");
				out.println("</td>");
				out.println("</tr>");
				
			
			  out.println("<tr class=tr_input>");
				out.println("<td width='20%'ID=VDATE>Date As At *</td>");
				out.println("<td width='*%'><input name=\"VAL_DAY\"   type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_MONTH\" type=\"text\" maxlength=\"2\" style=\"width: 25px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)> ");
				out.println("    <input name=\"VAL_YEAR\"  type=\"text\" maxlength=\"4\" style=\"width: 45px\" class=\"txt_input\" onchange=check_Date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a> ");
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"View Report\" onClick=\"print_report2()\" style=\"{width:110px;}\">"); 
				out.println("<input class='but_input' type='button' name='BUT_PRINT' value=\"Run Report\" onClick=\"run_report()\" style=\"{width:110px;}\"></td>"); 
				out.println("</tr>");
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_LOCATION_CODE'  class=div_input>Branch *</DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_LOCATION_CODE' maxlength='10' size='10' style=\"{width:150px}\" >"); 
				out.println("<input class='but_input' type='button' name='BUT_HELP_MAIN' value=\"Help\" onClick=\"help_update()\" ></td>"); 
				out.println("</tr>"); 
				
				out.println("<tr >"); 
				out.println("<td width='20%' ><DIV id='DIV_TXT_USER'  class=div_input>Marketing Officer </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_USER' maxlength='10' style='{width=150px}' size='10' onblur=\"assignState('M_USER'),makeRequest(document.Form1.TXT_USER)\">"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_USER' value=\"Help\" onClick=\"help_button_user()\">"); 
				out.println("</td>");
				out.println("</tr>"); 
				
				/*out.println("<tr >"); //Added By Sandun on 07-11-2008
				out.println("<td width='20%' ><DIV id='DIV_TXT_FINANCE'  class=div_input>Finance No </DIV></td>"); 
				out.println("<td width='*%' ><input class='txt_input' type='text' name='TXT_FINANCE' maxlength='10' style='{width=150px}' size='10' onblur='help_button_finance()'>"); 
				out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE' value=\"Help\" onClick=\"help_button_finance()\">"); //
				out.println("</td>");
				out.println("</tr>");*/
				
				
			
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
			
				else if(m_chksql.equals("load_report")){
			
				String m_date     = req.getParameter("date");
				String m_rpt_type = req.getParameter("rpt_type");
				String m_officer  = req.getParameter("officer");
				String m_location = req.getParameter("location");
				
				
				String m_date_time ="";
				String sql_query   ="";
				String sqlQuery2   ="";
				String m_location_rpt_header ="";
				String m_officer_rpt_header  ="";
				
				stmt = conn.createStatement ();
				
				String m_logged_user="";
				rs= stmt.executeQuery(" SELECT "+
				" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
				" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"') "+
				" FROM DUAL ");
				
				while(rs.next()){
				m_date_time = rs.getString(1);
				m_logged_user=rs.getString(2);
				}
				
								if (!m_location.equals("") ) {
				
				// Get The Location
				sql_query = " " +
				" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC('"+m_location+"') LOC_DESC FROM DUAL"+
				"";
				
				statement1 = conn.createStatement();
				resultSet1 = statement1.executeQuery(sql_query);
				
				if(resultSet1.next()){
				m_location_rpt_header= resultSet1.getString("LOC_DESC");
				}
				}else{
				m_location_rpt_header="ALL";
				}
				
			    if (!m_officer.equals("") ) {
				
				// Get The Officer Name
				sql_query = " " +
				" SELECT "+m_schema_name+".AF_CO_GET_EMP_NAME('"+m_officer+"') OFFICER_NAME FROM DUAL"+
				"";
				
				statement1 = conn.createStatement();
				resultSet1 = statement1.executeQuery(sql_query);
				
				if(resultSet1.next()){
				m_officer_rpt_header= resultSet1.getString("OFFICER_NAME");
				}
				}else{
				m_officer_rpt_header="ALL";
				}
				
				if (!m_location.equals("") && !m_officer.equals("")  ) {
				
				sqlQuery2 =  " "+ 
				" WHERE  LOCATION_CODE		    = '"+m_location+"' "+
				" AND    COLLECTION_OFFICER	    = '"+m_officer+"' "+
			    " GROUP BY A.TRANSACTION_TYPE "+
				"";
				}
				else if (m_location.equals("") && !m_officer.equals("")  ) {
					
				sqlQuery2 =  " "+ 
				" WHERE  COLLECTION_OFFICER	    = '"+m_officer+"' "+
				" GROUP BY A.TRANSACTION_TYPE "+
				"";
				}
				else if (!m_location.equals("") && m_officer.equals("")  ) {
					
				sqlQuery2 =  " "+ 
				" WHERE  LOCATION_CODE		    = '"+m_location+"' "+
				" GROUP BY A.TRANSACTION_TYPE "+
				"";
				}
				else if (m_location.equals("") && m_officer.equals("")  ) {
					
				sqlQuery2 =  " "+ 
				" GROUP BY A.TRANSACTION_TYPE "+
				"";
				}
				
					
				if (m_rpt_type.equals("DUE")) {
				
				sql_query =
				" SELECT  "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, A.TRANSACTION_TYPE,NVL(SUM(A.RENTAL_DUE_AMOUNT),0)  RENTAL_DUE_AMOUNT ,NVL(SUM(A.RENTAL_SET_AMOUNT),0) RENTAL_SET_AMOUNT "+
				" FROM    "+m_schema_name+".AF_TBD_COLLECTION_FIGURS A "+
				"";
				
				}else if (m_rpt_type.equals("ARREARS")) {
				
				sql_query =
				" SELECT  "+m_schema_name+".AF_CO_GET_TRAN_TYPE_DESC(TRANSACTION_TYPE) TRANSACTION_DESC, A.TRANSACTION_TYPE,NVL(SUM(A.ARREARS),0)  RENTAL_DUE_AMOUNT ,NVL(SUM(A.ARREARS_SETTLED),0) RENTAL_SET_AMOUNT "+
				" FROM    "+m_schema_name+".AF_TBD_COLLECTION_FIGURS A "+
				"";
				
				}
                
						
				sql_query=sql_query+sqlQuery2;
					
					rs= stmt.executeQuery(sql_query);
					
					boolean mflag=true;							
					boolean more = rs.next();
					
					double mm_RENTAL_DUE_AMOUNT=0;
					double mm_RENTAL_SET_AMOUNT=0;
					
					 out.println("<HTML><HEAD><TITLE>Rental Collection Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
	
					 out.println("	function show_details(m_transaction_type){");
						
					 out.println("   m_url=\""+m_class_url+"/"+m_fschema_name+"AF_MISF_CMS_Details?chksql=SHOW_DRILL_LEVEL_1&rpt_type="+m_rpt_type+"&officer="+m_officer+"&location="+m_location+"&date="+m_date+"&transaction_type=\"+m_transaction_type+\"\";");
					 //out.println("window.open(m_url);");
					 out.println("   window.open(m_url,'popupwin10','status=0,menubar=0,scrollbars=1,height=700,width=800');	");
					 out.println("	}");	
					 out.println("</SCRIPT>");
						
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>");
						
						
			
					out.println("<br>");
					out.println("<br>");
					
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='center'>   "); 
					out.println("<tr>"); 
					out.println("<td align='left' ><b>Rental Collection  Report</b></td>"); 
					out.println("</tr>"); 
					out.println("</table>"); 
					
					out.println("<br><br>"); 
					
					out.println("<table class='table' border='0' cellpadding='0' cellspacing='0' align='left'>   "); 
					out.println("<tr><td align='left'  ><b>Report Generated User : "+m_username+" </td></tr>"); 
					out.println("<tr><td align='left'  ><b>Report Date           : "+m_date+" </td></tr>"); 
					out.println("<tr><td align='left'  ><b>Report Type           :  "+m_rpt_type+"  </td></tr>"); 
					out.println("<tr><td align='left'  ><b>Location              :  "+m_location_rpt_header+"  </td></tr>"); 
					out.println("<tr><td align='left'  ><b>Officer               :  "+m_officer_rpt_header+"  </td></tr>"); 
					out.println("</table>"); 
					
					out.println("<br><br><br><br><br>"); 
					
					if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
					}					
						
					if(more){
					out.println("<table width='100%' class='table' cellspacing='1' cellpadding='2' border='1'  >");						
					out.println("<tr >"); //class=pdn_txtpos2
					out.println("<td width='1%'><b>No</td>"); 
					out.println("<td width='10%'><b>Transaction Type</b></td>"); 
					out.println("<td width='20%' ><b>Rental Amount</b></td>"); 
					out.println("<td width='20%'><b>Settled Amount</b></td>"); 
					out.println("</tr>"); 
					}
					
					int i=1;
					while(more){
				
							if(mflag){
								out.println("<tr >"); //class=tr_input1
								mflag=false;
							}
							else{
								out.println("<tr >"); //class=tr_input
								mflag=true;
							}
							out.println("<td >"+i+"</td>"); 
							out.println("<td  class=div_input >"+rs.getString("TRANSACTION_DESC")+"</td>"); 
							out.println("<td  class=div_input onClick=\"show_details('"+rs.getString("TRANSACTION_TYPE")+"')\" style='cursor:hand' align='right'>"+nf.format(rs.getDouble("RENTAL_DUE_AMOUNT"))+"</td>"); 
							out.println("<td  class=div_input align='right'><p>"+nf.format(rs.getDouble("RENTAL_SET_AMOUNT"))+"</p></td>"); //width='15%'
							out.println("</tr>");
							mm_RENTAL_DUE_AMOUNT = mm_RENTAL_DUE_AMOUNT+rs.getDouble("RENTAL_DUE_AMOUNT");
							mm_RENTAL_SET_AMOUNT = mm_RENTAL_SET_AMOUNT+rs.getDouble("RENTAL_SET_AMOUNT");
							i++;
							more = rs.next();
							
					}	
						
						out.println("<tr>");
						out.println("<td >&nbsp;</td>"); 
						out.println("<td class=div_input ><B>TOTAL</B></td>");
						out.println("<td class=div_input align='right' ><b>"+nf.format(mm_RENTAL_DUE_AMOUNT)+"</b></td>");
						out.println("<td class=div_input align='right' ><b>"+nf.format(mm_RENTAL_SET_AMOUNT)+"</b></td>");
						out.println("</tr>");
						out.println("</table>"); 
												
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
			}
				
				else if(m_chksql.equals("SHOW_DRILL_LEVEL_1")){
			
				
				
				String m_rpt_type         = req.getParameter("rpt_type");
				String m_date             = req.getParameter("date");
				String m_transaction_type = req.getParameter("transaction_type");
				String m_officer          = req.getParameter("officer");
				String m_location         = req.getParameter("location");
				
				String m_date_time ="";
				String sql_query="";
				
				stmt = conn.createStatement ();
				
				String m_logged_user="";
				rs= stmt.executeQuery(" SELECT "+
				" TO_CHAR(SYSDATE,'DD-MM-YYYY  HH24:MI:SS'), "+
				" "+m_schema_name+".AF_CO_GET_USER_NAME('"+m_username+"') "+
				" FROM DUAL ");
				
				while(rs.next()){
				m_date_time = rs.getString(1);
				m_logged_user=rs.getString(2);
				}
				
				
				if (m_rpt_type.equals("DUE")) {
				
				
				sql_query =
				" SELECT  CLIENT_CODE,FINANCE_NO,NVL(SUM(RENTAL_DUE_AMOUNT),0)  RENTAL_DUE_AMOUNT ,NVL(SUM(RENTAL_SET_AMOUNT),0) RENTAL_SET_AMOUNT,"+m_schema_name+".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER) COLLECTION_OFFICER_NAME "+
				" FROM    "+m_schema_name+".AF_TBD_COLLECTION_FIGURS  "+
				" WHERE   TRANSACTION_TYPE ='"+m_transaction_type+"' "+
					" AND     LOCATION_CODE      LIKE '%"+m_location.trim()+"%' "+
					" AND     COLLECTION_OFFICER LIKE '%"+m_officer.trim()+"%' "+
					" AND     RENTAL_DUE_AMOUNT <> 0 "+
					" GROUP BY CLIENT_CODE,FINANCE_NO,COLLECTION_OFFICER "+
					" ORDER BY COLLECTION_OFFICER "+
				"";
				
				}else if (m_rpt_type.equals("ARREARS")) {
				
				sql_query =
				" SELECT  CLIENT_CODE,FINANCE_NO,NVL(SUM(ARREARS),0)  RENTAL_DUE_AMOUNT ,NVL(SUM(ARREARS_SETTLED),0) RENTAL_SET_AMOUNT,"+m_schema_name+".AF_CO_GET_EMP_NAME(COLLECTION_OFFICER)  COLLECTION_OFFICER_NAME "+
				" FROM    "+m_schema_name+".AF_TBD_COLLECTION_FIGURS  "+
				" WHERE   TRANSACTION_TYPE ='"+m_transaction_type+"' "+
					" AND     LOCATION_CODE      LIKE '%"+m_location.trim()+"%' "+
					" AND     COLLECTION_OFFICER LIKE '%"+m_officer.trim()+"%' "+
					" AND     RENTAL_DUE_AMOUNT <> 0 "+
					" GROUP BY CLIENT_CODE,FINANCE_NO,COLLECTION_OFFICER "+
					" ORDER BY COLLECTION_OFFICER "+
				"";
				
				}


					
					

					
					rs= stmt.executeQuery(sql_query);
					boolean mflag=true;							
					boolean more = rs.next();
					
					double mm_RENTAL_DUE_AMOUNT=0;
					double mm_RENTAL_SET_AMOUNT=0;
					
					 out.println("<HTML><HEAD><TITLE>Rental Collection Report </TITLE></HEAD>");
					 out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
					 out.println("<SCRIPT language=\"JavaScript\">"); 
	
						
					 out.println("function show_transaction_history_new(m_finance_no,m_client_code){ "); 
					 out.println("m_url='"+m_class_url+"/"+m_fschema_name+"AF_RE_Collection_Report_With_Age?chksql=SHOW_TRANSACTION_HISTORY_BY_CONTRACT&finance_no='+m_finance_no+'&client_code='+m_client_code;"); 
					 out.println("window.open(m_url,'displayWindow3','left=50,top=60,width=850,height=500,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1');"); 
					 out.println("}");
				
					 out.println("</SCRIPT>");
						
					 out.println("<BODY   class='body & txt-body' LEFTMARGIN='0' TOPMARGIN='0'>");
					 out.println("<FORM NAME='Form1' method='post'>");
						
						
			
					out.println("<br>");
					out.println("<br>");
					
					out.println("<TABLE  WIDTH='100%'  >");
					out.println("<TR><TD align='Center' ><B> Total Due Rental as at  "+m_date+" </B></TD></TR>");
					out.println("</TABLE>");
					if(!more){
					out.println("<TABLE  WIDTH='100%'  STYLE='{ bgcolor='#8fb382' color: black; font: 20pt arial;}'>");
					out.println("<TR><TD align='Center' ><B> No Data Found </B></TD></TR>");
					out.println("</TABLE>");
					}					
						
					if(more){
					out.println("<table width='100%' class='table'  cellspacing='1' cellpadding='2' border='1' >");						
					out.println("<tr >"); //class=pdn_txtpos2
					out.println("<td width='1%'><b>No</td>"); 
					out.println("<td width='10%'><b>Contract No</b></td>"); 
					out.println("<td width='20%'><b>Collection Officer</b></td>"); 
					out.println("<td width='20%' ><b>Rental Amount</b></td>"); 
					out.println("<td width='20%'><b>Settled Amount</b></td>"); 
					out.println("</tr>"); 
					}
					
					int i=1;
					while(more){
				
							if(mflag){
								out.println("<tr >"); //class=tr_input1
								mflag=false;
							}
							else{
								out.println("<tr >"); //class=tr_input
								mflag=true;
							}
							out.println("<td >"+i+"</td>"); 
							out.println("<td  class=div_input >"+rs.getString("FINANCE_NO")+"</td>"); 
							out.println("<td  class=div_input >"+rs.getString("COLLECTION_OFFICER_NAME")+"</td>"); 
							out.println("<td  class=div_input onClick=\"show_transaction_history_new('"+rs.getString("FINANCE_NO")+"','"+rs.getString("CLIENT_CODE")+"')\" style='cursor:hand' align='right'>"+nf.format(rs.getDouble("RENTAL_DUE_AMOUNT"))+"</td>"); 
							out.println("<td  class=div_input align='right'><p>"+nf.format(rs.getDouble("RENTAL_SET_AMOUNT"))+"</p></td>"); //width='15%'
							out.println("</tr>");
							mm_RENTAL_DUE_AMOUNT = mm_RENTAL_DUE_AMOUNT+rs.getDouble("RENTAL_DUE_AMOUNT");
							mm_RENTAL_SET_AMOUNT = mm_RENTAL_SET_AMOUNT+rs.getDouble("RENTAL_SET_AMOUNT");
							i++;
							more = rs.next();
							
					}	
						
						out.println("<tr>");
						out.println("<td >&nbsp;</td>"); 
						out.println("<td >&nbsp;</td>"); 
						out.println("<td class=div_input ><B>TOTAL</B></td>");
						out.println("<td class=div_input align='right' ><b>"+nf.format(mm_RENTAL_DUE_AMOUNT)+"</b></td>");
						out.println("<td class=div_input align='right' ><b>"+nf.format(mm_RENTAL_SET_AMOUNT)+"</b></td>");
						out.println("</tr>");
						out.println("</table>"); 
												
						out.println("</form>"); 
						out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
						out.println("</BODY></HTML>");

					
					
			}
		


		}
		
		
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
				if(out!=null){try{out.close();  }catch(Exception e){}}
				if(conn!=null){try{conn.close();  }catch(Exception e){}}
		}
	}
}
