//--
//SCREEN NAME:CREDIT PROCESS -AF_RE_PRO_Application_Status_Change
//CREATED BY: nuwan de silva 
//DATE/TIME:28-09-07 10.23 am
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_RE_PRO_Application_Status_Change extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt,stmt2;
	public ResultSet rs,rs2;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		 
		try { 
			 
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			
			conn = m_sn_methods.met_user_validate(req); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String m_schema_name = m_sn_methods.schema_name;
			
			String m_date_dd="";
			String m_date_mm="";
			String m_date_yy="";
			String m_val_date="";
			
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			
			
		
			if(m_chksql.equals("main_page")){ 
			
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();

			/*rs = stmt.executeQuery ("SELECT TO_CHAR(SYSDATE,'DD'), "+
																	"TO_CHAR(SYSDATE,'MM'), "+
																	"TO_CHAR(SYSDATE,'YYYY') "+
																	"FROM DUAL ");
								
		  if(rs.next()){
			m_date_dd=rs.getString(1);
			m_date_mm=rs.getString(2);
			m_date_yy=rs.getString(3);
			}
			rs.close();
			*/
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Application Status Change</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_button_app_no();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			//out.println("     assign_values(data_vec);");
			out.println("document.Form1.TXT_FINANCE_NO.value=data_vec[0];");
			out.println("			}");
			
			/*out.println("			else if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_NO' ){");
			out.println("     help_button_app_no();");
			out.println("			}");
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_NO' ){");
			out.println("     assign_values(data_vec);");
			out.println("			}");
			*/
			
			out.println("}");
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
			
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=m_prime_chk_LAKDL_AF_RE_get_finance_no&data_val=\"+obj.value;");
			
			//out.println("window.open(m_url);");

			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			
			out.println("if(document.Form1.TXT_EFF_DATE_DD.value==\"\" || document.Form1.TXT_EFF_DATE_MM.value==\"\" || document.Form1.TXT_EFF_DATE_YY.value==\"\" ){  "); 
			out.println("DIV_TXT_DATE.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 


			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
			
			//out.println("alert('records'+document.Form1.hid_no_rec.value);");
						
			out.println("		if(validate_data()){"); 
			//out.println("ckeck_data();");
			//out.println("if(b_flag==0)");
						
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Save_Application_Status_Change';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			//out.println("		}"); 
			
			out.println("		}"); 
			out.println("else{");
			out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			
			out.println("} "); 



			out.println("function load_lock(){	"); 
			
						
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			
			
			/*out.println("function assign_system_date(){	"); 
			
			    out.println("document.Form1.VAL_DAY.value='"+m_date_dd+"'");
					out.println("document.Form1.VAL_MONTH.value='"+m_date_mm+"'");
					out.println("document.Form1.VAL_YEAR.value='"+m_date_yy+"'");
					
					m_val_date=m_date_dd+"-"+m_date_mm+"-"+m_date_yy;
					
					out.println("document.Form1.hid_bank_date.value='"+m_val_date+"'");
			  // out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value);");

					
			out.println("}	"); 
			*/
			

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_RE_APP_Status_change\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_RE_Help_Msg_Servlet?class_in=\"+client_name+\"AF_RE_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Collection - Application Status Change - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Application Status Change - \"+document.Form1.hid_status.value;"); 
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
			out.println("m_table.innerHTML=\"\";");
			out.println("document.Form1.TXT_FINANCE_NO.value=\"\";");
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
			/*out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
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
			
		
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_app_no();"); 
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
			out.println("	clear_data(IfCount);");//Added To The Clear The Area Code
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
			*/
			
			
			out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_RE_Help_Servlet?class_in="+m_fschema_name+"AF_RE_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(document.Form1.hid_help_type.value);");
			out.println("	}else");
							
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
		  out.println("if(document.Form1.hid_help_type.value=='1'){"); 
			out.println("		help_value_assign_app_no(oBj);"); 
			out.println("}");
											
			out.println("	}"); //end next
			
			out.println("	else{"); 
						out.println("		Next(oBj.valout[2],oBj.valout[3],Hid_No);"); 
			out.println("		return false;"); 
			out.println("	} "); 
			
			out.println("	}"); //end prev
			out.println("	else{	"); 
						out.println("	Prev(oBj.valout[2],oBj.valout[3],Hid_No);"); 

			out.println("	}	"); 
			out.println("	}		"); ///close
			
			out.println("	else{");
			out.println("	clear_data(document.Form1.hid_help_type.value);");//Added To The Clear 
			out.println("	}");
			
			
			out.println("	}	"); //
			out.println("}"); 
			out.println(""); 

			out.println("function Prev(Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			out.println("function Next (Start,End,Hid_No){"); 
			out.println("    HelpBox(Start,End,Hid_No);"); 
			out.println("}"); 
			out.println(""); 

			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------

			out.println(""); 
			
			
			out.println("function clear_data(IfCount) {");
			out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			
				  out.println("function help_button_app_no() {"); 
					out.println("document.Form1.hid_help_type.value=\"1\" ");
					out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					out.println("    Sql  = \"m_help_finance_no_application_status_change_edit_new\";"); 
					out.println("    m_sql = Sql;"); 
				  out.println("    m_criteria = Crit"); 
					//out.println("    HelpBox('1','10','2',Crit,'m_help_finance_no_application_status_change_edit','1');"); 
					out.println("    HelpBox('1','10','0');"); 

					out.println("}"); 
					out.println("else {"); 
					//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					//out.println("    Sql  = \"m_help_finance_no_application_status_change\";"); //Comment By Sandun on 23-10-2008
					out.println("    Sql  = \"m_help_finance_no_application_status_change_new\";");
					out.println("    m_sql = Sql;"); 
				  out.println("    m_criteria = Crit"); 
					//out.println("    HelpBox('1','10','2',Crit,'m_help_finance_no_application_status_change','1');"); 
					out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
					//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					//out.println("    HelpBox('1','10','2',Crit,'m_help_finance_no_application_status_change','1');"); 
					out.println("}"); 
		
					out.println("function help_value_assign_app_no() {"); 
					out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[2];"); 
          out.println("}"); 
					out.println("else {"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("}"); 
					
					out.println("}"); 
			
     
			out.println("function get_rental_dates(){");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=edit&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("}"); 
			out.println("else if(document.Form1.SCREEN_NAME.value==\"NEW\"){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=view&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");
			out.println("}"); 
			out.println("load_interface(m_url,'NORM');");
			out.println("}"); 
			


			
			
			out.println("function get_app_details(val){");			
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=edit&data_val=\"+val;");
			out.println("}"); 
			out.println("else {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Application_Status_Change?chksql=view&data_val=\"+val;");
			out.println("}"); 
			
			out.println("load_interface(m_url,'NORM');");
			///out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
					
		
				
					
			
			out.println("function ckeck_data(){ "); 
			
			out.println("b_flag=0;");
						
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_receipts()){"); 
			out.println("alert('No cheques selected');");
			out.println("b_flag=1;");
			out.println("}"); 
						
			out.println("else{");
			out.println("b_flag=0;");
			out.println("}"); 
			
      out.println("}"); 
			
			

			out.println("function count_receipts(){ ");
			
			out.println("count=0;");
		
			out.println("for(i=0;i<parseInt(document.Form1.hid_no_rec.value);i++){");
			
			out.println("m_chk_deposit_tmp=\"CHK_RECEIPT\"+i;");
			
			out.println("if(document.Form1.elements[m_chk_deposit_tmp].checked==true){");
		  out.println("count=count+1;");
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			
			out.println("}"); 
					
						
			out.println("function check_date(){ ");
			out.println("var date='' ");
			out.println(" if((document.Form1.TXT_EFF_DATE_DD.value !=\"\")&&(document.Form1.TXT_EFF_DATE_MM.value !=\"\")&&(document.Form1.TXT_EFF_DATE_YY.value !=\"\")){");
			out.println(" if(checkMonthLength(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)){");
			//out.println("date=document.Form1.TXT_EFF_DATE_DD.value+'-'+document.Form1.TXT_EFF_DATE_MM.value+'-'+document.Form1.TXT_EFF_DATE_YY.value;");
			//out.println("document.Form1.hid_bank_date.value=date");
		  //out.println("get_post_dated_cheques(document.Form1.hid_bank_date.value);");
			out.println(" }");
			out.println(" }");
			out.println("}");
			
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			//out.println(" load_c_date(document.Form1.hid_cal_date.value);");
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date='' ");
		//	out.println("alert('date valaue'+val);");
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
			out.println("     document.Form1.TXT_EFF_DATE_DD.value=v_date;");
			out.println("     document.Form1.TXT_EFF_DATE_MM.value=v_month;");
			out.println("     document.Form1.TXT_EFF_DATE_YY.value=val;");

			out.println("date=v_date+'-'+v_month+'-'+val;");
			out.println("document.Form1.hid_bank_date.value=date");
			out.println("  }");				
			out.println("}");
			out.println("}");
			
			out.println("function help_button_View() {");			
			out.println("if(document.Form1.TXT_FINANCE_NO.value!=\"\" ){");
			//out.println("get_details(document.Form1.TXT_FINANCE_NO.value);");
			out.println("get_app_details(document.Form1.TXT_FINANCE_NO.value);");
		  out.println("}");		
			out.println("else");		
			out.println("{");		
			out.println("  DIV_TXT_FINANCE_NO.style.color='red';");
			out.println("}");		
			out.println("}");		
			
			out.println("function change_val_receipt_status(obj){")	;								
			out.println("if(obj.checked==true){");
			out.println("obj.value='on'");
			out.println("}else if(obj.checked==false){");
			out.println("obj.value='off'");
			out.println("}");				
			out.println("}");			

			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_lock(), header(),add_row() //assign_system_date()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"New\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_RE_PRO_APP_STATUS_CHANGE\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			out.println("<INPUT TYPE='Hidden' NAME='hid_bank_date' VALUE=\"\">"); 
			//out.println("<INPUT TYPE='Hidden' NAME='hid_no_rec' VALUE=0>"); 
			

			
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Application Status Change</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				//out.println("<td width='10%'></td>");
				//out.println("<td width='10%'></td>");
				//out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onClick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
			out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>"); 			
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Help\");' onClick='load_screen_status(\"HELP\")' value=\"Help\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Cancel\");'  onclick='clear_window()' value=\"Cancel\"></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Close\");'  onclick='close_window()' value=\"Close\"></td>"); 
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Letter\");'  onclick='View_Letter()' value=\"Letter\"></td>"); 
			out.println("<td width='*%' align='right' class='div_input'></td></tr>");  
			out.println("</table>");  
			out.println("</td></tr><tr>");  
			out.println("<td class='line' height='1'><img height='1' src='spacer.gif' width='1' /></td>");  
			out.println("</tr><tr>");  
			out.println("<td class='pdn_txtpos' height='150' valign='top'>");  


			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' style={width:150px;} size='20' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_button_app_no()\">"); //m_help_TXT_APPLICATION_NO
			//out.println("<td width='*%'></td>");
			out.println("<input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
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
		
		else if(m_chksql.equals("view")){		
		String m_finance_no=req.getParameter("data_val").trim();
		double rep_total=0;

		String Sql_main="SELECT  "+
		    "  FINANCE_NO, "+
				"  CLIENT_CODE, "+
				"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+
				"  APPLICATION_STATUS "+
				"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS "+ 
				"  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') ";
		  //  "  AND APPLICATION_STATUS='ACTIVATED' ";

String Sql_ref=" SELECT STATUS_CODE,INITCAP(STATUS_DESC) "+
               " FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+
							 " WHERE ACTIVE_STATUS <> 'N' OR ACTIVE_STATUS IS NULL"; //--Added By Sandun on 29-10-2008
 		
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			
		 int j=0;
		 rs=stmt.executeQuery(Sql_main);
		 boolean more=rs.next();
		 if(more){
			out.println("<tr>");			
		 	out.println("<td width=\"30%\" align=left>Finance no</td>"); 		
			out.println("<td width=\"*%\" align=left style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<input type=hidden name=hid_finance_no value="+rs.getString(1)+">");
		  out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Client Code</td>"); 
			out.println("<td width=\"*%\" align=left  style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<input type=hidden name=hid_client_code value="+rs.getString(2)+">");
			out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Client Name</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(3)+"</td>"); 
			out.println("<input type=hidden name=hid_client_name value=\""+rs.getString(3)+"\">");
			out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Contract Status</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(4)+"</td>"); 
			out.println("<input type=hidden name=hid_app_status value="+rs.getString(4)+">");
			out.println("</tr>");			
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_DATE'  class=div_input>Effective Date [DD-MM-YYYY] * </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input5' type='text' name='TXT_EFF_DATE_DD' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'>");
			out.println("                <input class='txt_input5' type='text' name='TXT_EFF_DATE_MM' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'>");
			out.println("                <input class='txt_input5' type='text' name='TXT_EFF_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>New Status</td>"); 
			out.println("<td width='*%' class=div_input><select name=TXT_STATUS_TYPE class='txt_input' style=\"{width:150px;}\">");
			rs2=stmt2.executeQuery(Sql_ref);
			while(rs2.next()){
			out.println("<OPTION value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			
			out.println("</SELECT></td>");
			out.println("</tr>");			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Remark</td>"); 
			//out.println("<td width=\"*%\" class=div_input><INPUT TYPE=TEXT class='txt_input' NAME=TXT_ENT_COMMENT  style=\"{width:350px;}\" maxlength=\"200\"></td>"); 
			out.println("<td><textarea class='txt_input' NAME=TXT_ENT_COMMENT  style=\"{width:300px;height:80px}\" maxlength=\"200\"></textarea></td>");
			out.println("</tr >"); 
			
			
			
			j=j+1;
			
			}
		//	out.println("<input type=hidden name=hid_no_rec value="+j+">");
			
			rs.close();
			rs2.close();
			out.println("</table >"); 
    }
		
		
		else if(m_chksql.equals("edit")){		
		String m_finance_no=req.getParameter("data_val").trim();
		double rep_total=0;

		String Sql_main="SELECT  "+
		    "  FINANCE_NO, "+//1
				"  CLIENT_CODE, "+//2
				"  "+m_schema_name+".AF_CO_GET_CLIENT_NAME(CLIENT_CODE), "+//3
				"  DECODE(STATUS,'RESCH','Re-Sheduled','NEGOT','Negotiation','REBON','Vehicle Relesed Under Bond','ARBIT','Referred To Arbitaration','ACTIVATED','Activated','REPOS','Repossed Vehicle',STATUS), "+//4
				"  TO_CHAR(EFF_DATE,'DD-MM-YYYY'),"+//5
				"  ENT_USER, "+//6
				"  NVL(ENT_COMMENT,'-'), "+//7
				"  NVL(MOD_USER,' ') "+//8
				"  FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE "+ 
				"  WHERE UPPER(FINANCE_NO)=UPPER('"+m_finance_no+"') "+
				"  AND ENT_DATE IN (SELECT MAX(ENT_DATE) FROM "+m_schema_name+".AF_RE_PRO_APP_STATUS_CHANGE GROUP BY FINANCE_NO)";
		

String Sql_ref=" SELECT STATUS_CODE,INITCAP(STATUS_DESC) "+
               " FROM "+m_schema_name+".AF_RE_PRO_REFERENECE_STATUS "+
 							 " WHERE ACTIVE_STATUS <> 'N' OR ACTIVE_STATUS IS NULL ";// Added By Sandun on 29-10-2008
			
			out.println("<table align=\"center\" width=\"100%\" border=\"0\" class=\"table\">");
			
		 int j=0;
		 rs=stmt.executeQuery(Sql_main);
		 boolean more=rs.next();
		 if(more){
			out.println("<tr>");			
		 	out.println("<td width=\"30%\" align=left>Finance no</td>"); 		
			out.println("<td width=\"*%\" align=left style= cursor:hand; onclick=show_finance_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td>"); 
			out.println("<input type=hidden name=hid_finance_no value="+rs.getString(1)+">");
		  out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Client Code</td>"); 
			out.println("<td width=\"*%\" align=left  style= cursor:hand; onclick=show_client('"+rs.getString(2)+"') ><u>"+rs.getString(2)+"</u></td>"); 
			out.println("<input type=hidden name=hid_client_code value="+rs.getString(2)+">");
			out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Client Name</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(3)+"</td>"); 
			out.println("<input type=hidden name=hid_client_name value=\""+rs.getString(3)+"\">");
			out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Contract Status</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(4)+"</td>"); 
			out.println("<input type=hidden name=hid_app_status value="+rs.getString(4)+">");
			out.println("</tr>");
			
			out.println("<tr>");	
			if(rs.getString(8).equals(" ")){					
			out.println("<td width=\"30%\" align=left>Enetr User</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(6)+"</td>"); 
			}
			else{
			out.println("<td width=\"30%\" align=left>Modify User</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(8)+"</td>"); 
			}
			//out.println("<input type=hidden name=hid_app_status value="+rs.getString(4)+">");
			out.println("</tr>");			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Early Effective Date</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(5)+"</td>"); 
			//out.println("<input type=hidden name=hid_app_status value="+rs.getString(4)+">");
			out.println("</tr>");	
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Early Remark</td>"); 
			out.println("<td width=\"*%\" align=left>"+rs.getString(7)+"</td>"); 
			//out.println("<input type=hidden name=hid_app_status value="+rs.getString(4)+">");
			out.println("</tr>");
			
			
			
			out.println("<tr>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_DATE'  class=div_input>Effective Date [DD-MM-YYYY] * </DIV></td>"); 
			out.println("<td width='*%' ><input class='txt_input5' type='text' name='TXT_EFF_DATE_DD' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'>");
			out.println("                <input class='txt_input5' type='text' name='TXT_EFF_DATE_MM' maxlength='2' size='2' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'>");
			out.println("                <input class='txt_input5' type='text' name='TXT_EFF_DATE_YY' maxlength='4' size='4' onBlur='check_date(document.Form1.TXT_EFF_DATE_DD,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)'><a href style='{cursor:hand; }' onclick=load_calendar('2')>   Calendar</a></td>"); 
			out.println("</tr>"); 
			
			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>New Status</td>"); 
			out.println("<td width='*%' class=div_input><select name=TXT_STATUS_TYPE class='txt_input' style=\"{width:150px;}\">");
			rs2=stmt2.executeQuery(Sql_ref);
			while(rs2.next()){
			out.println("<OPTION value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			
			out.println("</SELECT></td>");
			out.println("</tr>");			
			out.println("<tr>");			
			out.println("<td width=\"30%\" align=left>Remark</td>"); 
			//out.println("<td width=\"*%\" class=div_input><INPUT TYPE=TEXT class='txt_input' NAME=TXT_ENT_COMMENT  style=\"{width:350px;}\" maxlength=\"200\"></td>"); 
			out.println("<td><textarea class='txt_input' NAME=TXT_ENT_COMMENT  style=\"{width:300px;height:80px}\" maxlength=\"200\"></textarea></td>");
			out.println("</tr >"); 
			
			
			
			j=j+1;
			
			}
		//	out.println("<input type=hidden name=hid_no_rec value="+j+">");
			
			rs.close();
			rs2.close();
			out.println("</table >"); 
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
