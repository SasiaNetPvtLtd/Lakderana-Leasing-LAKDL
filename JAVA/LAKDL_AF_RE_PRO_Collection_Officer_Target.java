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
 

public class LAKDL_AF_RE_PRO_Collection_Officer_Target extends javax.servlet.http.HttpServlet { 

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
			
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			
			nf = java.text.NumberFormat.getInstance(Locale.US);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			
			String m_chksql=req.getParameter("chksql");
			String m_year="",m_month="",m_next_month="",m_next_year="",m_date_today="";;
		
			if(m_chksql.equals("main_page")){ 
		
			stmt = conn.createStatement ();
			stmt2 = conn.createStatement ();

			
			stmt = conn.createStatement ();
			rs = stmt.executeQuery ("SELECT "+
																	" TO_CHAR(SYSDATE,'MON'), "+
																	" TO_CHAR(ADD_MONTHS(SYSDATE,11),'MON'), "+
																	" TO_CHAR(SYSDATE,'YYYY'), "+
																	" TO_CHAR(ADD_MONTHS(SYSDATE,12),'YYYY'), "+
																	" TO_CHAR(SYSDATE,'DD-MM-YYYY') "+
																	" FROM DUAL ");
								
		  if(rs.next()){
			m_month=rs.getString(1);
			m_next_month=rs.getString(2);
			m_year=rs.getString(3);
			m_next_year=rs.getString(4);
			m_date_today=rs.getString(5);
			}
			
			
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Collection - Application Status Change</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			
			out.println("var m_year='"+m_year+"';");
			out.println("var m_date='01';");
			
			out.println("function get_vector(data_vec) {");
					
			out.println("if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M_MONTH_YEAR' ){");
			out.println("document.Form1.TO_MONTH.value=data_vec[0];");
			out.println("document.Form1.TO_YEAR.value=data_vec[1];");
			out.println("}");
			out.println("else if(data_vec.length>0  && document.Form1.hid_chk_status.value=='M_MONTH_DATA' ){");
			out.println("if(confirm(\"Records already Exist...do you want to Modify ?\")){ ");
			out.println("document.Form1.SCREEN_NAME.value=\"EDIT\";"); 
			out.println("s_date=01+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");			
			out.println("get_app_details(s_date);");
			out.println("}");
			out.println("}");
			out.println("else if(data_vec.length==0  && document.Form1.hid_chk_status.value=='M_MONTH_DATA' ){");
			out.println("s_date=01+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");			
			out.println("get_app_details(s_date);");
			out.println("}");
			
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
			//out.println("//validations goes here"); 
			out.println("if( document.Form1.FROM_YEAR.value.length<4 || isNaN(document.Form1.FROM_YEAR.value)   ){  "); //added by G.L.C.T. Jayawardena
		//	out.println("document.Form1.FROM_YEAR.style.color='red';");
			out.println("alert(\"Please enter a valid year\")"); 
			out.println("return false;"); 
			out.println("}"); 
			out.println("else {"); 
			out.println("if( document.Form1.FROM_YEAR.value<1900 || document.Form1.FROM_YEAR.value>3000  ){  "); //added by G.L.C.T. Jayawardena
		//	out.println("document.Form1.FROM_YEAR.style.color='red';");
			out.println("alert(\"Year should be between 1900 and 3000\")"); 
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{");
			//out.println("return true;"); 
			//out.println("}"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
			//out.println("alert('records'+document.Form1.hid_no_rec.value);");
			out.println("		if(validate_data()){"); 
			out.println("if(check_value==0){");  //added by G.L.C.T. Jayawardena
			out.println("alert(\"Please press 'View' button before 'Save'\")");
			out.println("} else {");
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Save_Collection_Officer_Target';");  
			out.println("		document.Form1.submit();	"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("		}"); 
			out.println("else{");
			//out.println("alert(\"Please enter all required fields marked with a '*' on screen\");");
			out.println("} "); 
			out.println("} "); 

			out.println("function load_lock(){	"); 
			out.println("document.oncontextmenu=new Function(\"return false\");"); 
			out.println("}	"); 
			
			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Officer_Target?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Officer_Target?chksql=main_page';"); 
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
			out.println("help_box.innerHTML=\" Collection - Collection Officer Target - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Collection - Collection Officer Target - \"+document.Form1.hid_status.value;"); 
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
			//out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			
				  out.println("function help_button_app_no() {"); 
					out.println("document.Form1.hid_help_type.value=\"1\" ");
					out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					out.println("    Sql  = \"m_help_finance_no_application_status_change_edit\";"); 
					out.println("    m_sql = Sql;"); 
				  out.println("    m_criteria = Crit"); 
					//out.println("    HelpBox('1','10','2',Crit,'m_help_finance_no_application_status_change_edit','1');"); 
					out.println("    HelpBox('1','10','0');"); 

					out.println("}"); 
					out.println("else {"); 
					//out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"ACTIVATED@\"+\"Y@\";"); 
					out.println("    Sql  = \"m_help_finance_no_application_status_change\";"); 
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Officer_Target?chksql=view\";");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			


			
			
			out.println("function get_app_details(val){");
			out.println("if(document.Form1.SCREEN_NAME.value==\"EDIT\"){"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Officer_Target?chksql=edit&data_val=\"+val;");
			out.println("}"); 
			out.println("else {"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_PRO_Collection_Officer_Target?chksql=view&data_val=\"+val;");
			out.println("}"); 
			out.println("load_interface(m_url,'NORM');");
			///out.println("window.open(m_url);");
			out.println("}"); 
					
			out.println("function check_data(obj){");
			out.println("assignState('M_MONTH_DATA');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=check_data&data_val=\"+obj;");
			out.println("load_interface(m_url,'XML');");
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
			out.println(" if(checkMonthLength(document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_MM,document.Form1.TXT_EFF_DATE_YY)){");
			out.println(" }");
			out.println(" }");
			out.println("}");
			
			out.println("function load_calendar(num) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
							
			out.println("function load_c_date(val) {");
			out.println("var date='' ");
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
			out.println("var check_value =0;");
			out.println("function help_button_View() {");
			out.println("if(document.Form1.FROM_YEAR.value!=\"\" ){");
			out.println("if(validate_data()){");//added by G.L.C.T. Jayawardena
			out.println("check_value =1;");
			out.println("s_date=01+'-'+document.Form1.FROM_MONTH.value+'-'+document.Form1.FROM_YEAR.value;");			
			//out.println("get_app_details(s_date);");
      out.println("check_data(s_date);");
		  out.println("}");		
			out.println("}");		
			out.println("else");		
			out.println("{");		
			out.println("  DIV_FROM_MONTH.style.color='red';");
			out.println("}");		
			out.println("}");
			
			out.println("function change_val_receipt_status(obj){")	;
			out.println("if(obj.checked==true){");
			out.println("obj.value='on'");
			out.println("}else if(obj.checked==false){");
			out.println("obj.value='off'");
			out.println("}");
			out.println("}");			
			
			out.println("function Change_to_month(obj){")	;
			out.println("date=m_date+'-'+obj.value+'-'+document.Form1.FROM_YEAR.value;");
			out.println("get_to_month_and_year(date);");
			out.println("}");			
			
			/*out.println("function Change_to_year(obj){");
			out.println("if(obj.value!='' &&  obj.value!='-'  && obj.value!='null') ");
			out.println("if(isPosInteger(obj.value) ){ ");
			out.println("if(parseInt(obj.value.length) == 4 ){ ");
			out.println("document.Form1.TO_YEAR.value=parseInt(obj.value)+1;");
			out.println("} ");
			out.println("else { ");
			out.println("alert('Year should be four digit number'); ");
			out.println("document.Form1.FROM_YEAR.value='';");
			out.println("document.Form1.TO_YEAR.value='';");
			out.println("} ");
			out.println("} ");
			out.println("else{");
			out.println("alert('please enter a number'); ");
			out.println("document.Form1.FROM_YEAR.value='';");
			out.println("document.Form1.TO_YEAR.value='';");
			out.println("obj.value=''; ");
			out.println("obj.focus(); ");
			out.println("} "); 
			out.println("}"); 
			*/
			
			//added by nuwan de silva on 16-10-07---------------
			out.println("function assign_system_date(){	"); 
			out.println("document.Form1.FROM_MONTH.value='"+m_month+"';");
			out.println("document.Form1.FROM_YEAR.value='"+m_year+"';");
			out.println("document.Form1.TO_MONTH.value='"+m_next_month+"';");
			out.println("document.Form1.TO_YEAR.value='"+m_next_year+"';");
			//out.println("assignState('M_MONTH_YEAR');");
			//out.println("get_to_month_and_year('"+m_date_today+"');"); 
			out.println("}	"); 
			
			//added by nuwan de silva on 16-10-07---------------
			out.println("function get_to_month_and_year(obj){ ");
			out.println("assignState('M_MONTH_YEAR');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_RE_sql_validations?chksql=get_to_month_and_year&data_val=\"+obj;");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);	"); 
			out.println("}	"); 
			
						
			out.println("function check_number(obj){ ");
			out.println("if(obj.value!='' && obj.value!='-'  && obj.value!='null' )  ");
			out.println("if(isnumberok(obj,obj.maxlength)){  ");
			out.println("format_number(obj,obj.maxlength) ; ");
			out.println("}  ");
			out.println("else{ ");
			out.println("alert('please enter a number');  ");
			out.println("obj.value='';  ");
			out.println("}  ");
			out.println("} ");


			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"assign_system_date()\"> "); //load_lock(), header(),add_row() //assign_system_date()
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Collection - Collection Officer Target</td>"); 
			out.println("</tr>"); 
			out.println("<tr>"); 
			out.println("<td  height='10px' class='pdn_txtpos'>"); 
			out.println("<table class='table' cellpadding='2' cellspacing='2' border='0'> "); 
			//out.println("<tr><td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"New\");' onclick='load_screen_status(\"NEW\")' value=\"New\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Edit\");' onClick='load_screen_status(\"EDIT\")' value=\"Edit\"></td>");  
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
				out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Reactivate\");' onClick='load_screen_status(\"RACT\")' value=\"Re-activate\"></td>");  
		//	out.println("<td width='10%'></td>");
			//		out.println("<td width='10%'></td>");
			//out.println("<td width='10%' align='center'><input type=\"button\" name='btn_delete' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Delete\");' onClick='load_screen_status(\"DEL\")' value=\"Delete\"></td>");  
			//out.println("<td width='10%'></td>");  
			out.println("<td width='6%'></td>");  
			out.println("<td width='10%' align='center'><input type=\"button\" class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\"></td>");  
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

      out.println("<br><br>");  
			out.println("<table align='center' width='100%' class='table' border='0'>"); 
			
				out.println("<tr class=tr_input>");
				out.println("<td ID=DIV_FROM_MONTH width=\"15%\">From </td>");
				out.println("<td width=\"10%\" ><SELECT  name=FROM_MONTH class=\"txt_input\" onChange=\"Change_to_month(this)\"> ");
				out.println("<OPTION value=\"JAN\">January</OPTION>");
				out.println("<OPTION value=\"FEB\">February</OPTION>");
				out.println("<OPTION value=\"MAR\">March</OPTION>");
				out.println("<OPTION value=\"APR\">April</OPTION>");
				out.println("<OPTION value=\"MAY\">May</OPTION>");
				out.println("<OPTION value=\"JUN\">June</OPTION>");
				out.println("<OPTION value=\"JUL\">July</OPTION>");
				out.println("<OPTION value=\"AUG\">August</OPTION>");
				out.println("<OPTION value=\"SEP\">September</OPTION>");
				out.println("<OPTION value=\"OCT\">October</OPTION>");
				out.println("<OPTION value=\"NOV\">November</OPTION>");
				out.println("<OPTION value=\"DEC\">December</OPTION>");

				out.println("</SELECT></td>");
				out.println("<td width='10%' ><input class='txt_input' type='text' name='FROM_YEAR' maxlength='4' style={width:100px;text-align:right;} onblur=\"\" ></td>"); //Change_to_year(this)
				
				out.println("<td width=\"10%\">&nbsp;</td>");
				out.println("<td ID=DIV_TO_MONTH width=\"15%\">To</td>");
				out.println("<td width=\"10%\" ><SELECT  name=TO_MONTH class=\"txt_input\" disabled > ");
				out.println("<OPTION value=\"JAN\">January</OPTION>");
				out.println("<OPTION value=\"FEB\">February</OPTION>");
				out.println("<OPTION value=\"MAR\">March</OPTION>");
				out.println("<OPTION value=\"APR\">April</OPTION>");
				out.println("<OPTION value=\"MAY\">May</OPTION>");
				out.println("<OPTION value=\"JUN\">June</OPTION>");
				out.println("<OPTION value=\"JUL\">July</OPTION>");
				out.println("<OPTION value=\"AUG\">August</OPTION>");
				out.println("<OPTION value=\"SEP\">September</OPTION>");
				out.println("<OPTION value=\"OCT\">October</OPTION>");
				out.println("<OPTION value=\"NOV\">November</OPTION>");
				out.println("<OPTION value=\"DEC\">December</OPTION>");
				
				
				out.println("</SELECT></td>");
				out.println("<td width='10%' ><input class='txt_input' type='text' name='TO_YEAR' maxlength='4' style={width:100px;text-align:right;} onblur=\"\" disabled></td>"); 
				out.println("<td width='*%'><input class='but_input' type='button' name='BUT_VIEW' value=\"View\" onClick=\"help_button_View()\"></td>"); 
				//out.println("<td width='*%'></td>");
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
		String m_date=req.getParameter("data_val").trim();
		double rep_total=0;
	
		out.println("<TABLE  WIDTH='100%' BORDER='1' >");
		out.println("<TR class=pdn_txtpos2 >");
		out.println("<TD WIDTH ='5%'  STYLE='{text-align: center;font: 10pt Arial}' rowspan=2><B>Branch</B></TD>");
		out.println("<TD WIDTH ='13%' STYLE='{text-align: center;font: 10pt Arial}' rowspan=2><B>Collection <br>Officer Name</B></TD>");
		out.println("<TD WIDTH ='2%'  STYLE='{text-align: center;font: 10pt Arial}' rowspan=2><B>User ID</B></TD>");
		out.println("<TD WIDTH ='80%' STYLE='{text-align: center;font: 10pt Arial}' colspan=12><B>Target Amount</B></TD>");
		out.println("</TR>");

		int i=0;
		rs = stmt.executeQuery(" SELECT  "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE), '-','-','-' ,USER_ID,NAME, "+							                                                         
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),0),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),0),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),1),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),1),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),2),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),2),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),3),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),3),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),4),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),4),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),5),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),5),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),6),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),6),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),7),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),7),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),8),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),8),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),9),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),9),'DD-MON-YYYY'),   "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),10),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),10),'DD-MON-YYYY'), "+
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),11),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),11),'DD-MON-YYYY')  "+
		",A.LOCATION_CODE "+
		
		" ,TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),0),'YYYY'),  "+//32
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),1),'YYYY'),   "+//33
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),2),'YYYY'),   "+//34
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),3),'YYYY'),   "+//35
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),4),'YYYY'),  "+//36
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),5),'YYYY'),  "+//37
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),6),'YYYY'),   "+//38
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),7),'YYYY'),  "+//39
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),8),'YYYY'),   "+//40
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),9),'YYYY'),  "+//41
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),10),'YYYY'), "+//42
		" TO_CHAR(ADD_MONTHS(TO_DATE('"+m_date+"','DD-MON-YYYY'),11),'YYYY') "+//43
	  " FROM "+m_schema_name+".CO_CO_MAS_USER A, "+m_schema_name+".CO_CO_MAS_EMPLOYEE B "+
    " WHERE A.EMP_ID=B.EMP_CODE "+
    " AND A.ACTIVE_STATUS='Y' ");
 
		
		
        boolean more = rs.next();
                if(more)
                {
                    out.println("<TR class=pdn_txtpos2 >");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(7) + "-"+rs.getString(32)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(9) + "-"+rs.getString(33)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(11) + "-"+rs.getString(34)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(13) + "-"+rs.getString(35)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(15) + "-"+rs.getString(36)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(17) + "-"+rs.getString(37)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(19) + "-"+rs.getString(38)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(21) + "-"+rs.getString(39)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(23) + "-"+rs.getString(40)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(25) + "-"+rs.getString(41)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(27) + "-"+rs.getString(42)+"</TD>");
                    out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial ; text-align:center;}' ><b>" + rs.getString(29) + "-"+rs.getString(43)+"</TD>");
                    
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_0' VALUE='"+rs.getString(8)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_1' VALUE='"+rs.getString(10)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_2' VALUE='"+rs.getString(12)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_3' VALUE='"+rs.getString(14)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_4' VALUE='"+rs.getString(16)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_5' VALUE='"+rs.getString(18)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_6' VALUE='"+rs.getString(20)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_7' VALUE='"+rs.getString(22)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_8' VALUE='"+rs.getString(24)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_9' VALUE='"+rs.getString(26)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_10' VALUE='"+rs.getString(28)+"'>");		
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_11' VALUE='"+rs.getString(30)+"'>");		
										
                    out.println("</TR>");
										int row=0;
										while(more) 
                    {
										
												if(row>0 && row%2==1){
												out.println("<tr class=tr_input1 >");
												}
												else{
												out.println("<tr class=tr_input >");
												}
									
                        //out.println("<TR >");//to get account code
												out.println("<TD WIDTH='2%' STYLE='{ text-align:center;}' >"+rs.getString(1)+"");
												
												out.println("<INPUT TYPE='Hidden' NAME='Hid_branch_"+i+"' VALUE='"+rs.getString(31)+"'>");
												out.println("<INPUT TYPE='Hidden' NAME='Hid_advisor_"+i+"' VALUE='"+rs.getString(5)+"'>");
												out.println("</TD>");
                        
												out.println("<TD WIDTH='13%' STYLE='{ text-align:left;}'  >" + rs.getString(6) + "</TD>");
												out.println("<TD WIDTH='2%' STYLE='{  text-align:center;}' >"+rs.getString(5)+"");
												
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_1_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)> </TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_2_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_3_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_4_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_5_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_6_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_7_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_8_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_9_" + i +"'  VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_10_" + i +"' VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_11_" + i +"' VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_12_" + i +"' VALUE='0.00'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
                        i++;
                        more = rs.next();
                        out.println("<TR>");
												row=row+1;
                    }
                }
								out.println("<INPUT TYPE='Hidden' NAME='Hid_Count' VALUE='"+i+"'>");
                out.println("</TABLE>");
			
    }
		///////////////////////////////////////////////////////////
		
		else if(m_chksql.equals("edit")){		
							String s_date=req.getParameter("data_val").trim();
							 double bud_val=0.00;

						  /*String f_month = req.getParameter("f_month");
							String f_year = req.getParameter("f_year");
							String t_month = req.getParameter("t_month");
							String t_year = req.getParameter("t_year");
						  double bud_val=0.00;
							int to_month;
							int s_month;					
							*/
							
               //String s_date="01-"+f_month+"-"+f_year;
								
								int i=0;
								int j=1;
								
									out.println("<TABLE  WIDTH='100%' BORDER='1' >");
									out.println("<TR class=pdn_txtpos2 >");
									out.println("<TD WIDTH ='5%'  STYLE='{text-align: center;}' rowspan=2><B>Branch</B></TD>");
									out.println("<TD WIDTH ='13%' STYLE='{text-align: center;}' rowspan=2><B>Collection <br>Officer Name</B></TD>");
									out.println("<TD WIDTH ='2%'  STYLE='{text-align: center;}' rowspan=2><B>User ID</B></TD>");
									out.println("<TD WIDTH ='80%' STYLE='{text-align: center;}' colspan=12><B>Target Amount</B></TD>");
									out.println("</TR>");
        				
										//////////////////load header///////////////////////
										rs = stmt.executeQuery(" SELECT  TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),0),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),0),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),1),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),1),'DD-MON-YYYY'), "+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),2),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),2),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),3),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),3),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),4),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),4),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),5),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),5),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),6),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),6),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),7),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),7),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),8),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),8),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),9),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),9),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),10),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),10),'DD-MON-YYYY'),"+
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),11),'MON'),TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),11),'DD-MON-YYYY') "+
										
										" ,TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),0),'YYYY'),  "+//25
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),1),'YYYY'),   "+//26
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),2),'YYYY'),   "+//27
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),3),'YYYY'),   "+//28
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),4),'YYYY'),  "+//29
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),5),'YYYY'),  "+//30
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),6),'YYYY'),   "+//31
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),7),'YYYY'),  "+//32
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),8),'YYYY'),   "+//33
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),9),'YYYY'),  "+//34
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),10),'YYYY'), "+//35
										" TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MON-YYYY'),11),'YYYY') "+//36
										" FROM DUAL");
										
										
										boolean more1 = rs.next();
		                if(more1){
		                    out.println("<TR class=pdn_txtpos2  >");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(1) + "-" + rs.getString(25) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(3) + "-" + rs.getString(26) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(5) + "-" + rs.getString(27) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(7) + "-" + rs.getString(28) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(9) + "-" + rs.getString(29) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(11) + "-" + rs.getString(30) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(13) + "-" + rs.getString(31) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(15) + "-" + rs.getString(32) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(17) + "-" + rs.getString(33) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(19) + "-" + rs.getString(34) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(21) + "-" + rs.getString(35) + "</TD>");
		                    out.println("<TD WIDTH='5%' STYLE='{ text-align:center;}' ><b>" + rs.getString(23) + "-" + rs.getString(36) + "</TD>");
										   }
												
										/****/
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_0' VALUE='"+rs.getString(2)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_1' VALUE='"+rs.getString(4)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_2' VALUE='"+rs.getString(6)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_3' VALUE='"+rs.getString(8)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_4' VALUE='"+rs.getString(10)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_5' VALUE='"+rs.getString(12)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_6' VALUE='"+rs.getString(14)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_7' VALUE='"+rs.getString(16)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_8' VALUE='"+rs.getString(18)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_9' VALUE='"+rs.getString(20)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_10' VALUE='"+rs.getString(22)+"'>");	
										out.println("<INPUT TYPE='Hidden' NAME='Hid_St_Date_11' VALUE='"+rs.getString(24)+"'>");	
										out.println("</TR>");
										
									
							
								rs = stmt.executeQuery(" SELECT K.LOC_DESC,'-','-','-',K.USER_ID,K.USER_NAME,K.LOCATION_CODE,SUM(K.AMT1),SUM(K.AMT2),SUM(K.AMT3),SUM(K.AMT4),SUM(K.AMT5),SUM(K.AMT6),SUM(K.AMT7),SUM(K.AMT8),SUM(K.AMT9),SUM(K.AMT10),SUM(K.AMT11),SUM(K.AMT12) "+
								" FROM( "+
								//------------------------- (1) ------------------------------
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,NVL(C.TARGET_AMT,'0.00') AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),0),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),1),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+

								//--ORDER BY A.BRANCH_CODE,A.ADVISOR_CODE,A.TARGET_START_DATE
								
								//----------------------- (2) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,NVL(C.TARGET_AMT,'0.00') AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),1),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),2),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+

								
								//----------------------- (3) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,NVL(C.TARGET_AMT,'0.00') AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),2),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),3),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+

								
								//----------------------- (4) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,NVL(C.TARGET_AMT,'0.00') AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),3),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),4),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								
								//----------------------- (5) ---------------------------------
								" UNION ALL "+
							
							  " SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4, NVL(C.TARGET_AMT,'0.00') AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),4),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),5),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								//----------------------- (6) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5, NVL(C.TARGET_AMT,'0.00') AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),5),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),6),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								
								//----------------------- (7) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6, NVL(C.TARGET_AMT,'0.00') AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),6),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),7),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								//----------------------- (8) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,NVL(C.TARGET_AMT,'0.00') AMT8,0 AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),7),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),8),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								
								//----------------------- (9) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,NVL(C.TARGET_AMT,'0.00') AMT9,0 AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),8),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),9),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								
								//----------------------- (10) ---------------------------------
								" UNION ALL "+
							  " SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,NVL(C.TARGET_AMT,'0.00') AMT10,0 AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),9),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),10),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								
								
								//----------------------- (11) ---------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,NVL(C.TARGET_AMT,'0.00') AMT11,0 AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),10),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),11),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
							
								
								//----------------------- (12) --------------------------------
								" UNION ALL "+
								
								" SELECT "+m_schema_name+".AF_CO_GET_LOCATION_DESC(A.LOCATION_CODE) LOC_DESC,'-','-','-',A.USER_ID USER_ID,A.NAME USER_NAME,A.LOCATION_CODE LOCATION_CODE,0 AMT1,0 AMT2,0 AMT3,0 AMT4,0 AMT5,0 AMT6,0 AMT7,0 AMT8,0 AMT9,0 AMT10,0 AMT11,NVL(C.TARGET_AMT,'0.00') AMT12 "+
								" ,C.TARGET_START_DATE TARGET_START_DATE "+
								"	FROM "+m_schema_name+".CO_CO_MAS_USER A,"+m_schema_name+".CO_CO_MAS_EMPLOYEE B ,"+m_schema_name+".AF_RE_PRO_OFFICER_MONTH_TARGET C "+
								"	WHERE C.TARGET_START_DATE >= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),11),'DD-MON-YYYY') "+
								"	AND   C.TARGET_FINISH_DATE <= TO_CHAR(ADD_MONTHS(TO_DATE('"+s_date+"','DD-MM-YYYY'),12),'DD-MON-YYYY')  "+
								"	AND   A.EMP_ID=B.EMP_CODE "+
								"	AND   A.USER_ID=C.USER_ID "+
								"	AND   A.ACTIVE_STATUS='Y' "+
								" )K "+
								
								" GROUP BY  K.LOCATION_CODE,K.USER_ID,K.LOC_DESC,K.USER_NAME "+
								" ORDER BY K.LOCATION_CODE,K.USER_ID	 ");				
							
							
//=============== End ===============================================================================================
													
								int row=0;					
													
								boolean more = rs.next();
                while(more){
											//out.println("<TR >");
											
										if(row>0 && row%2==1){
										out.println("<tr class=tr_input1 >");
										}
										else{
										out.println("<tr class=tr_input >");
										}


											out.println("<TD WIDTH='2%' STYLE='{  text-align:center;}' >" + rs.getString(1) + "");	
											out.println("<TD WIDTH='13%' STYLE='{ text-align:left;}'  >" + rs.getString(6) + "</TD>");
											out.println("<TD WIDTH='2%' STYLE='{  text-align:left;}'  >" + rs.getString(5) + "</TD>");
											
									out.println("<INPUT TYPE='Hidden' NAME='Hid_branch_"+i+"' VALUE='"+rs.getString(7)+"'>");
									out.println("<INPUT TYPE='Hidden' NAME='Hid_advisor_"+i+"' VALUE='"+rs.getString(5)+"'>");	
											
											out.println("<INPUT TYPE='Hidden' NAME='Hid_Acc_Add_st_"+i+"' VALUE='1'>"); 
									//	} temp shan
										bud_val=rs.getDouble(8);
										
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_1_" + i +"'  VALUE='"+nf.format(rs.getDouble(8))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)> </TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_2_" + i +"'  VALUE='"+nf.format(rs.getDouble(9))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_3_" + i +"'  VALUE='"+nf.format(rs.getDouble(10))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_4_" + i +"'  VALUE='"+nf.format(rs.getDouble(11))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_5_" + i +"'  VALUE='"+nf.format(rs.getDouble(12))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_6_" + i +"'  VALUE='"+nf.format(rs.getDouble(13))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_7_" + i +"'  VALUE='"+nf.format(rs.getDouble(14))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_8_" + i +"'  VALUE='"+nf.format(rs.getDouble(15))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_9_" + i +"'  VALUE='"+nf.format(rs.getDouble(16))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_10_" + i +"' VALUE='"+nf.format(rs.getDouble(17))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_11_" + i +"' VALUE='"+nf.format(rs.getDouble(18))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										out.println("<TD WIDTH='5%'  ><INPUT TYPE='TEXT' class='txt_input' NAME='MONTH_12_" + i +"' VALUE='"+nf.format(rs.getDouble(19))+"'  style={width:100px;text-align:right;}  maxlength='23' ONBLUR=check_number(this)></TD>");
										          j++;
										
										/*out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_1_"+i+"' VALUE='"+nf.format(rs.getDouble(8))+"' ONBLUR='budget_value(" + i + ",1)'> </TD>");
                    j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_2_"+i+"' VALUE='"+nf.format(rs.getDouble(9))+"' ONBLUR='budget_value(" + i + ",2)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_3_"+i+"' VALUE='"+nf.format(rs.getDouble(10))+"' ONBLUR='budget_value(" + i + ",3)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_4_"+i+"' VALUE='"+nf.format(rs.getDouble(11))+"' ONBLUR='budget_value(" + i + ",4)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_5_"+i+"' VALUE='"+nf.format(rs.getDouble(12))+"' ONBLUR='budget_value(" + i + ",5)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_6_"+i+"' VALUE='"+nf.format(rs.getDouble(13))+"' ONBLUR='budget_value(" + i + ",6)'> </TD>");
										j++;//
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_7_"+i+"' VALUE='"+nf.format(rs.getDouble(14))+"' ONBLUR='budget_value(" + i + ",7)'> </TD>");
                    j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_8_"+i+"' VALUE='"+nf.format(rs.getDouble(15))+"' ONBLUR='budget_value(" + i + ",8)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_9_"+i+"' VALUE='"+nf.format(rs.getDouble(16))+"' ONBLUR='budget_value(" + i + ",9)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_10_"+i+"' VALUE='"+nf.format(rs.getDouble(17))+"' ONBLUR='budget_value(" + i + ",10)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_11_"+i+"' VALUE='"+nf.format(rs.getDouble(18))+"' ONBLUR='budget_value(" + i + ",11)'> </TD>");
										j++;
										
										out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_12_"+i+"' VALUE='"+nf.format(rs.getDouble(19))+"' ONBLUR='budget_value(" + i + ",12)'> </TD>");
										j++;
										*/
										
										out.println("</TR>");
									  j++;
										if(j==13) {
											j=1;//i++;
										}
								    i++;
                    more = rs.next();
										row=row+1;
                }
								
		/*						String sdate="01"+"-"+f_month+"-"+f_year;

		//if newly added accounts found

rs = stmt.executeQuery(" SELECT D.LOC_CODE,D.LOC_DESC,D.ADV_CODE,D.ADV_NAME,'-','-' "+
          " FROM( "+
          " SELECT A.LOC_CODE LOC_CODE,A.LOC_DESC LOC_DESC,'-','-','-',C.ADV_CODE,C.ADV_NAME "+
          " FROM "+
          " " + m_schema_name + ".WEBDN_REF_LOCATION A," + m_schema_name + ".WEBDN_REF_ADVISOR C "+
          " WHERE "+
          " A.LOC_CODE=C.LOC_CODE AND "+
          " A.ACTIVE_STATUS='Y' "+
          " MINUS "+
          " SELECT A.BRANCH_CODE LOC_CODE,M.LOC_DESC LOC_DESC,'-','-','-',A.ADVISOR_CODE,N.ADV_NAME "+
          " FROM "+
          " " + m_schema_name + ".WEBDN_REF_ADVISOR_MONTH_TARGET A , "+
          " " + m_schema_name + ".WEBDN_REF_LOCATION M, "+
          " " + m_schema_name + ".WEBDN_REF_ADVISOR N "+
          " WHERE TARGET_START_DATE=TO_DATE('"+sdate+"','DD-MM-YYYY') "+
          " AND A.BRANCH_CODE=M.LOC_CODE AND A.ADVISOR_CODE=N.ADV_CODE "+
					" AND M.LOC_CODE=N.LOC_CODE AND M.ACTIVE_STATUS='Y' "+
          " )D ORDER BY LOC_CODE ");
									
									
								boolean more3 = rs.next();
								
								while(more3){
											
											out.println("<TR BGCOLOR='white'>");
										//	out.println("<TD WIDTH='2%' STYLE='{cursor: hand; font: 9pt arial; text-align:center;}' >" + rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4) + "-" + rs.getString(5) + "");	
										  out.println("<TD WIDTH='2%' STYLE='{ font: 9pt arial; text-align:center;}' >" + rs.getString(2) + "");	
											out.println("<TD WIDTH='13%' STYLE='{ font: 9pt arial; text-align:left;}'  >" + rs.getString(4) + "</TD>");
											out.println("<TD WIDTH='2%' STYLE='{ font: 9pt arial; text-align:left;}'  >" + rs.getString(3) + "</TD>");/////
									
									//----CHECH -----		
									out.println("<INPUT TYPE='Hidden' NAME='Hid_branch_"+i+"' VALUE='"+rs.getString(1)+"'>");
									out.println("<INPUT TYPE='Hidden' NAME='Hid_advisor_"+i+"' VALUE='"+rs.getString(3)+"'>");	
									//----END CHECK -----------
									
											
											
											out.println("<INPUT TYPE='Hidden' NAME='Hid_Acc_Add_st_"+i+"' VALUE='2'>");
											//bud_val=rs.getDouble(6);
											
										for(int k=1;k<=12;k++){
										
										
										
											//	if(k < r_diff){
		                    	//out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_"+k+"_"+i+"' VALUE='0.00' ONBLUR='budget_value(" + i + ","+k+")'> </TD>");
												//}
											//else{
													out.println("<TD WIDTH='5%' STYLE='{ font: 9pt arial; text-align:center;}' ><INPUT TYPE='TEXT' NAME='MONTH_"+k+"_"+i+"' VALUE='0.00' ONBLUR='budget_value(" + i + ","+k+")'> </TD>");
												//}
												
												
												
												if(k==12) {
													out.println("</TR>");
												}
										}
										i++;
                    more3 = rs.next();
											
                }
								*/
								out.println("<INPUT TYPE='Hidden' NAME='Hid_Count' VALUE='"+i+"'>");
								out.println("</TABLE>");
								///////////////////////////////////
               
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
