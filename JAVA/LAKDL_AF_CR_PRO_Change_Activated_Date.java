//--
//SCREEN NAME:CREDIT PROCESS -CHANGE ACTIVATED DATE
//CREATED BY:Nuwan De Silva
//DATE/TIME: created on 24-07-07 11.26am
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Change_Activated_Date extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf;
	java.lang.Math a;
	Statement stmt;
	public ResultSet rs;
	
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
		
			if(m_chksql.equals("main_page")){ 
			
			
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - Change Activated Date</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			out.println("var m_hid_row_no=0;");
			
			
			out.println("function get_vector(data_vec) {");
			
			out.println("			if(data_vec.length==0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     help_button_client_code();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_CLIENT_CODE.value!=\"\" && document.Form1.hid_chk_status.value=='M2' ){");
			out.println("     document.Form1.TXT_CLIENT_CODE.value=data_vec[0];");
			out.println("     document.Form1.TXT_CLIENT_NAME.value=data_vec[1];");
			out.println("get_client_details();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_FINANCE' ){");
			out.println("     document.Form1.TXT_APPLICATION_NO.value=data_vec[0];");
			out.println("     document.Form1.TXT_FINANCE_NO.value=data_vec[1];");
			out.println("     document.Form1.TXT_CLIENT_CODE.value=data_vec[2];");
			out.println("     document.Form1.TXT_CLIENT_NAME.value=data_vec[3];");
			out.println("get_client_details();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_FINANCE' ){");
			out.println("     help_button_finance_no();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_APP' ){");
			out.println("     document.Form1.TXT_APPLICATION_NO.value=data_vec[0];");
			out.println("     document.Form1.TXT_FINANCE_NO.value=data_vec[1];");
			out.println("     document.Form1.TXT_CLIENT_CODE.value=data_vec[2];");
			out.println("     document.Form1.TXT_CLIENT_NAME.value=data_vec[3];");
			out.println("get_client_details_2();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_APP' ){");
			out.println("     help_button_application_no();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.elements[\"TXT_NEW_FINANCE_NO_\"+m_hid_row_no].value!=\"\" && document.Form1.hid_chk_status.value=='M_FIN_VAL' ){");
			out.println("				alert('Record already exsist');");
			out.println("			document.Form1.elements[\"TXT_NEW_FINANCE_NO_\"+m_hid_row_no].value='';");
			out.println("			}");
					 								
			out.println("}");
			
			
			
			
			
			
			
				out.println("function befor_end(m_obj) {");
        out.println("   m_obj.focus();");
        out.println("}");
		
			out.println("function assignState(val){");
			out.println("document.Form1.hid_chk_status.value=val");
			out.println("}");
			
			out.println("function validate_finance_no(app_no,row){");
			//out.println("alert('row'+row);");
			out.println("m_new_fin = \"TXT_NEW_FINANCE_NO_\"+row;");
			out.println("m_hid_row_no =row;");
			out.println("assignState('M_FIN_VAL');");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_finance_no&application_no=\"+app_no+\"&data_val=\"+document.Form1.elements[m_new_fin].value+\"&ac_status=Y\";");
			out.println("load_interface(m_url,'XML');");
			//out.println("window.open(m_url);");
			out.println("}");
			
			
			
			
			out.println("function makeRequest(obj) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M2' )");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_client_code&data_val=\"+obj.value+\"&ac_status=Y\";");
						
			out.println("else if(document.Form1.hid_chk_status.value=='M_FINANCE')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_finance_no_activated_date_change&data_val=\"+obj.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&ac_status=CANCEL\";");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_APP')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_finance_no_activated_date_change_app_no&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&data_val=\"+obj.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value+\"&ac_status=CANCEL\";");	
			
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			//out.println("if(document.Form1.TXT_CLIENT_CODE.value==\"\" ){  "); 
			//out.println("DIV_TXT_CLIENT_CODE.style.color='red';");
			//out.println("return false;"); 
			//out.println("}"); 
			//out.println("else{"); 
			out.println("return true;"); 
			//out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			out.println("ckeck_new_date();");
			out.println("if(b_flag==0)");
			
			//out.println("		if(confirm(\"Are you sure selected offer is not the maximum\")){ "); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Change_Activate_Date_Change';");  
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

			out.println("function clear_window(){	"); 
			out.println("		if(confirm(\"Are you sure you want to clear the screen? \")){ "); 
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Activated_Date?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Activated_Date?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Activate_Date_Change\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Change Activated Date  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Change Activated Date - \"+document.Form1.hid_status.value;"); 
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
		
		 out.println("popupwin = window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			
			out.println("	if(oBj.valout[1] ==\" \"){"); 
			out.println("	clear_data(IfCount);");
			out.println("	}else");
			
				
			out.println("	"); 
			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
		
			out.println("		if(IfCount==\"1\"){"); 
			out.println("		help_value_assign_client_code();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("		help_value_assign_finance_no();"); 
	  	out.println("		}"); 
			
			out.println("		if(IfCount==\"3\"){"); 
			out.println("		help_value_assign_application_no();"); 
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
			out.println("function clear_data(IfCount) {");
			out.println("		if(IfCount==\"1\"){"); 
			out.println("document.Form1.TXT_CLIENT_CODE.value='';");
			out.println("document.Form1.TXT_CLIENT_NAME.value='';");
			out.println("}");
			
			out.println("		if(IfCount==\"2\"){"); 
			out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			
			out.println("}");
			
				  out.println("function help_button_client_code() {"); 
					out.println("    Crit = document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_TXT_CLIENT_CODE_new','1');"); 
					out.println("}"); 
		
					out.println("function help_value_assign_client_code() {"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[3];"); 
					out.println("get_client_details();");
					out.println("}"); 
							
				  out.println("function help_button_finance_no() {"); 
					out.println("    Crit = document.Form1.TXT_FINANCE_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
					//out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.elements[m_invoice].value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_activated_date','2');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_finance_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
					
					out.println("get_client_details();");
					out.println("}"); 
					
					
					
					
					
					
					
					out.println("function help_button_application_no() {"); 
					out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.TXT_CLIENT_CODE.value+\"@\"+document.Form1.TXT_FINANCE_NO.value+\"@\"+\"CANCEL@\"+\"Y@\";"); 
					//out.println("    Crit = document.Form1.TXT_VENDOR_CODE.value+\"@\"+document.Form1.TXT_APPLICATION_NO.value+\"@\"+document.Form1.elements[m_invoice].value+\"@Y@\";"); 
					out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_activated_date_app_no','3');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_application_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("if(oBj.valout[3]=='-'){"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("}"); 
					out.println("else {"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("}"); 
					
					out.println("    document.Form1.TXT_CLIENT_CODE.value=oBj.valout[4];"); 
					out.println("    document.Form1.TXT_CLIENT_NAME.value=oBj.valout[5];"); 
					out.println("get_client_details_2();");
					out.println("}"); 
					
     
			out.println("function get_client_details(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Activated_Date?chksql=view&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_client_details_2(){");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Activated_Date?chksql=view_app&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&finance_no=\"+document.Form1.TXT_FINANCE_NO.value+\"&client_code=\"+document.Form1.TXT_CLIENT_CODE.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_vector_normal(http_response) {");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}");
			
			out.println("function ckeck_new_date(){ "); 
			out.println("b_flag=0;");
			out.println("if(m_table.innerHTML==\"\"){");
			out.println("alert('No data to save');");
			out.println("b_flag=1;");
			out.println("}"); 
			out.println("else if(!count_date_selected()){"); 
			out.println("alert('Please enter the activated date and next payment date');");
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
			
			out.println("m_ins_new_date_dd=\"TXT_NEW_ACT_DATE_DD_\"+i;");
			out.println("m_ins_new_date_mm=\"TXT_NEW_ACT_DATE_MM_\"+i;");
			out.println("m_ins_new_date_yy=\"TXT_NEW_ACT_DATE_YY_\"+i;");
			
			out.println("m_rev_new_date_dd=\"TXT_NEW_NEXT_DATE_DD_\"+i;");
			out.println("m_rev_new_date_mm=\"TXT_NEW_NEXT_DATE_MM_\"+i;");
			out.println("m_rev_new_date_yy=\"TXT_NEW_NEXT_DATE_YY_\"+i;");

      
			out.println("if( (document.Form1.elements[m_ins_new_date_dd].value!='' && document.Form1.elements[m_ins_new_date_mm].value!='' &&  document.Form1.elements[m_ins_new_date_yy].value!='') && ");
			out.println("    (document.Form1.elements[m_rev_new_date_dd].value!='' && document.Form1.elements[m_rev_new_date_mm].value!='' &&  document.Form1.elements[m_rev_new_date_yy].value!='') ){ ");
			//out.println("    (document.Form1.elements[m_tax_new_date_dd].value!='' && document.Form1.elements[m_tax_new_date_mm].value!='' &&  document.Form1.elements[m_tax_new_date_yy].value!='') || ");
			//out.println("    (document.Form1.elements[m_dri_new_date_dd].value!='' && document.Form1.elements[m_dri_new_date_mm].value!='' &&  document.Form1.elements[m_dri_new_date_yy].value!='') ){");
		  
			out.println("count=count+1;");
			
			out.println("}");		
			
			out.println("}");		
			
			out.println("if(count>0)");
			out.println("return true;");
			out.println("else");
			out.println("return false;");
			out.println("}"); 
			
				
			
			out.println("function load_calendar(num,row_no) {");
      out.println(" document.Form1.hid_cal_date.value=num;"); 
			out.println(" document.Form1.hid_row_no.value=row_no;"); 
			out.println("	popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=190,top=380,width=320,height=230\");"); 
			out.println("}");
								
					
			out.println("function load_c_date(val) {");
		  out.println("m_row=document.Form1.hid_row_no.value");
		  out.println("if(document.Form1.SCREEN_NAME.value!=\"DEL\"){");
   		out.println("v_date=val.substr(0,val.indexOf('-'));");
			out.println("if(v_date.length<2)");
			out.println("v_date=0+v_date");
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("v_month=val.substr(0,val.indexOf('-'));");
			out.println("if(v_month.length<2)");
			out.println("v_month=0+v_month");
			
			out.println("val=val.substr(val.indexOf('-')+1,val.length);");
			out.println("  if(document.Form1.hid_cal_date.value=='1'){"); 
			out.println("     document.Form1.elements[\"TXT_NEW_ACT_DATE_DD_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_NEW_ACT_DATE_MM_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_NEW_ACT_DATE_YY_\"+m_row].value=val;");
			
			//out.println("check_date(document.Form1.elements[\"TXT_NEW_ACT_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_YY_\"+m_row]) ");
			out.println("check_date(document.Form1.elements[\"TXT_NEW_ACT_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_YY_\"+m_row]) ");
			out.println("  }");				
			out.println("  else if(document.Form1.hid_cal_date.value=='2'){"); 
			out.println("     document.Form1.elements[\"TXT_NEW_NEXT_DATE_DD_\"+m_row].value=v_date;");
			out.println("     document.Form1.elements[\"TXT_NEW_NEXT_DATE_MM_\"+m_row].value=v_month;");
			out.println("     document.Form1.elements[\"TXT_NEW_NEXT_DATE_YY_\"+m_row].value=val;");
			out.println("check_date(document.Form1.elements[\"TXT_NEW_ACT_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_NEW_ACT_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_NEW_NEXT_DATE_YY_\"+m_row]) ");
			//out.println("check_date(document.Form1.elements[\"hid_TXT_REV_DATE_DD_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_MM_\"+m_row],document.Form1.elements[\"hid_TXT_REV_DATE_YY_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_DD_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_MM_\"+m_row],document.Form1.elements[\"TXT_REV_NEW_DATE_YY_\"+m_row]) ");
			out.println("  }");				

   		out.println("}");
			out.println("}");
						
			/*out.println("function check_date(OBJ_DD,OBJ_MM,OBJ_YY){ ");
			out.println(" if((OBJ_DD.value !=\"\")&&(OBJ_MM.value !=\"\")&&(OBJ_YY.value !=\"\")){");
			out.println("  checkMonthLength(OBJ_DD,OBJ_MM,OBJ_YY);");
			out.println(" }");
			out.println("}");
			*/
			
			
			
			
			
			out.println("function check_date(FROM_OBJ_DD,FROM_OBJ_MM,FROM_OBJ_YY,TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY){ ");
			//out.println("alert('ssf'+TO_OBJ_DD.value); ");
			//out.println("alert('ssffh'+FROM_OBJ_DD.value); ");
			out.println(" if((TO_OBJ_DD.value !=\"\")&&(TO_OBJ_MM.value !=\"\")&&(TO_OBJ_YY.value !=\"\")){");
			out.println("if(checkMonthLength(TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY)){");
			out.println(" if((FROM_OBJ_DD.value !=\"\")&&(FROM_OBJ_MM.value !=\"\")&&(FROM_OBJ_YY.value !=\"\")){");
			out.println("if(!chk_validity(FROM_OBJ_DD,FROM_OBJ_MM,FROM_OBJ_YY,TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY)){");
			out.println("TO_OBJ_DD.value=\"\"; ");
			out.println("TO_OBJ_MM.value=\"\"; ");
			out.println("TO_OBJ_YY.value=\"\"; ");
			out.println(" }");
			out.println(" }");
			out.println("}");
			out.println("}");
			out.println("}");
			
			
			
			//==========Added By Nuwan De Silva ======================================================
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
		  out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
      out.println("if((parseFloat(FROM_DD.value))>=(parseFloat(TO_DD.value))){");
      out.println("if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("if((parseFloat(FROM_YY.value))<=(parseFloat(TO_YY.value))){");
      out.println(" if(((parseFloat(FROM_DD.value))<(parseFloat(TO_DD.value)))&&");
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println("((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
      out.println("}");
      out.println("else if(((parseFloat(FROM_DD.value))>(parseFloat(TO_DD.value)))&&");//>=
      out.println("((parseFloat(FROM_MM.value))==(parseFloat(TO_MM.value)))&&");
      out.println(" ((parseFloat(FROM_YY.value))==(parseFloat(TO_YY.value)))){");
	    out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println("     } ");
      out.println("}");
      out.println("else{");
        out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println("}");
      out.println(" }");
      out.println(" else{");
      out.println("   if((parseFloat(FROM_YY.value))>=(parseFloat(TO_YY.value))){");
        out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseFloat(FROM_MM.value))<=(parseFloat(TO_MM.value))){");
      out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseFloat(FROM_YY.value))<(parseFloat(TO_YY.value))){ ");
      out.println("    }");
      out.println("  else{");
      out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println("  }");
      out.println(" }");
      out.println("}");
			//out.println("TO_DD.focus();");
			out.println("return true;");
      out.println("}");
		  out.println("}");
			//===========================================================================================
			
			out.println("</script>"); 
			
			out.println("<BODY class='body & txt-body' leftmargin='0' topmargin='0' marginwidth='0' ONLOAD=\"\"> "); //load_lock(), header(),add_row()
			out.println("<FORM NAME='Form1' method='post'>"); 
			out.println("<input  type='hidden' value='NEW' name='SCREEN_NAME'> "); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_help_type' VALUE=\"\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_status' VALUE=\"Save\">"); 
			out.println("<INPUT TYPE='Hidden' NAME='hid_chk_status' VALUE=\"\">");
			out.println("<INPUT TYPE='Hidden' NAME='hid_save_status' VALUE=\"Save\">");
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_ACTIVATED_DATE_CHANGE\">"); 
			out.println("<input type=hidden name=\"OPTION_DESC\" value=\"\"></td>");
			out.println("<input type=hidden name='hid_cal_date' value=\"\"></td>");
			out.println("<input type=hidden name='hid_row_no' value=\"\"></td>");
			
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Change Activated Date </td>"); 
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


			out.println("<table align='center' width='100%' class='table' border='0'>"); 

		
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_CLIENT_CODE'  class=div_input>Client Code </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_CLIENT_CODE' maxlength='15' size='15' onblur=\"assignState('M2'),makeRequest(document.Form1.TXT_CLIENT_CODE)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_CLIENT_CODE' value=\"...\" onClick=\"help_button_client_code()\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' >Client Name</td>"); 
			out.println("<td width='40%'><input class='txt_input' type='text' name='TXT_CLIENT_NAME' maxlength='20' style=\"width:250px;\"  disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_FINANCE_NO'  class=div_input>Finance Number </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_FINANCE_NO' maxlength='20' size='15' onblur=\"assignState('M_FINANCE'),makeRequest(document.Form1.TXT_FINANCE_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_FINANCE_NO' value=\"...\" onClick=\"help_button_finance_no()\"></td>"); //m_help_TXT_APPLICATION_NO
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_APPLICATION_NO'  class=div_input>Application Number </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_APPLICATION_NO' maxlength='20' size='15' onblur=\"assignState('M_APP'),makeRequest(document.Form1.TXT_APPLICATION_NO)\">"); 
			out.println("<input class='but_input' type='button' name='BUT_TXT_APPLICATION_NO' value=\"...\" onClick=\"help_button_application_no()\"></td>"); //m_help_TXT_APPLICATION_NO
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
		
		String m_client_code=req.getParameter("client_code").trim();
		String m_finance_no=req.getParameter("finance_no").trim();
		
		stmt = conn.createStatement ();
		
		rs=stmt.executeQuery("SELECT  "+
  	" DISTINCT A.APPLICATION_NO, "+ //1
		" A.FINANCE_NO, "+ //2
	//	" A.CLIENT_CODE, "+ //3
	//	" A.APPLICATION_STATUS, "+ //4
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), "+ //3
	//	" A.MASTER_AGREEMENT_NO, "+ //6
	//	" A.AGREEMENT_DATE, "+ //7
		" NVL(TO_CHAR(MAX(B.RENTAL_DATE),'DD-MM-YYYY'),'-') ,"+ //4
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'DD'),'-'), "+ //5
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'MM'),'-'), "+ //6
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'YYYY'),'-'), "+ //7
		"(SELECT MAX(PAYMENT_INTERVAL) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  WHERE APPLICATION_NO=A.APPLICATION_NO) "+ //8
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B "+
		" WHERE  A.APPLICATION_NO=B.APPLICATION_NO(+) AND "+
		" UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') AND "+
		" TO_NUMBER(INSTALLMENT_NO)=1 AND  "+
		" A.FINANCE_NO LIKE '"+m_finance_no+"%'   "+
		" GROUP BY A.APPLICATION_NO,A.FINANCE_NO,A.ACTIVATED_DATE ");
 
			
	out.println("<br>");			
				
	out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
 
	out.println(" <tr class=pdn_txtpos2 style={padding-left:0px} > ");//pdn_txtpos2
	out.println("  <td width=\"12%\" align='left'>Application No</td> ");
	out.println("  <td width=\"12%\" align='left'>Finance No</td> ");
  out.println("  <td width=\"12%\" align='left'>Activated Date</td> ");
	out.println("  <td colspan=\"2\" width=\"17%\" align='left'>New Activated Date</td> ");
	out.println("  <td width=\"12%\"  align='left'>Next Payment Date</td> ");
  out.println("  <td colspan=\"2\" width=\"17%\"  align='left'>New Next Payment Date</td> ");
	out.println(" </tr>");
		
		 
			int j=0;
     
			while(rs.next()){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
				//style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(7)+"')
					out.println("<td width=\"12%\" align=left  style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td> ");
					out.println("<td width=\"12%\" align=left  style=cursor:hand;cursor-color:blue onclick=show_finance_detail_drill('"+rs.getString(2)+"')><u>"+rs.getString(2)+"</u></td> ");
					
					out.println("<td width=\"12%\" align=left>"+rs.getString(3)+"</td> ");
					if(rs.getString(3).trim().equals("-")){
					//out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+j+") ><u>Calender</u></td>"); 
					out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_DD_"+j+" value=\"\">");
          out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_MM_"+j+" value=\"\">");
          out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_YY_"+j+" value=\"\">");
					}
					else
					{
					//out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(5)+"\" onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\"                             value=\""+rs.getString(6)+"\"           onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\"                              value=\""+rs.getString(7)+"\"           onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
					// Modified by Disnaka Jayasuriya on 2009.10.13, 
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(5)+"\"           onblur=\"check_date(document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+",document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\"                             value=\""+rs.getString(6)+"\"           onblur=\"check_date(document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+",document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\"                              value=\""+rs.getString(7)+"\"           onblur=\"check_date(document.Form1.hid_TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_NEXT_DATE_YY_"+j+",document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+j+") ><u>Calender</u></td>"); 
					out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_DD_"+j+" value=\""+rs.getString(5)+"\" >");
          out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_MM_"+j+" value=\""+rs.getString(6)+"\">");
          out.println("<input type=hidden name=hid_TXT_NEW_ACT_DATE_YY_"+j+" value=\""+rs.getString(7)+"\">");
					}
					
					out.println("<td width=\"12%\" align=left>"+rs.getString(4)+"</td> ");
					//out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    //out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.hid_TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.hid_TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.hid_TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.hid_TXT_NEW_ACT_DATE_YY_"+j+",document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('2',"+j+") ><u>Calender</u></td>"); 
					
          out.println("<input type=hidden name=hid_TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\">");  
			    out.println("<input type=hidden name=hid_TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\">");
					out.println("<input type=hidden name=hid_PAYMENT_INTERVAL_"+j+" value=\""+rs.getString(8)+"\">");
									
					
					out.println("<input type=hidden name=hid_TXT_NEW_NEXT_DATE_DD_"+j+" value=\"\">");
          out.println("<input type=hidden name=hid_TXT_NEW_NEXT_DATE_MM_"+j+" value=\"\">");
          out.println("<input type=hidden name=hid_TXT_NEW_NEXT_DATE_YY_"+j+" value=\"\">");
			  	
					/*out.println("<input type=hidden name=hid_TXT_VEHICLE_NO_"+j+" value=\""+rs.getString(7)+"\">");
					out.println("<input type=hidden name=hid_TXT_INS_DATE_DD_"+j+" value=\""+rs.getString(8)+"\">");
          out.println("<input type=hidden name=hid_TXT_INS_DATE_MM_"+j+" value=\""+rs.getString(9)+"\">");
          out.println("<input type=hidden name=hid_TXT_INS_DATE_YY_"+j+" value=\""+rs.getString(10)+"\">");
					*/
     			out.println("</tr >"); 
					j=j+1;
			}
			out.println("<input type=hidden name=hid_no_rec value="+j+">");
			out.println("<input type=hidden name=hid_no_rec_app value=\"FALSE\">");
      out.println("</table >"); 
}



    else if(m_chksql.equals("view_app")){		
		
		String m_client_code=req.getParameter("client_code").trim();
		String m_finance_no=req.getParameter("finance_no").trim();
		String m_application_no=req.getParameter("application_no").trim();
		stmt = conn.createStatement ();
		
		rs=stmt.executeQuery("SELECT  "+
  	" DISTINCT A.APPLICATION_NO, "+ //1
		" NVL(A.FINANCE_NO,'-'), "+ //2
	//	" A.CLIENT_CODE, "+ //3
	//	" A.APPLICATION_STATUS, "+ //4
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'DD-MM-YYYY'),'-'), "+ //3
	//	" A.MASTER_AGREEMENT_NO, "+ //6
	//	" A.AGREEMENT_DATE, "+ //7
		" NVL(TO_CHAR(MAX(B.RENTAL_DATE),'DD-MM-YYYY'),'-') ,"+ //4
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'DD'),'-'), "+ //5
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'MM'),'-'), "+ //6
		" NVL(TO_CHAR(A.ACTIVATED_DATE,'YYYY'),'-'), "+ //7
		"(SELECT MAX(PAYMENT_INTERVAL) FROM "+m_schema_name+".AF_CO_PRO_APP_PRICING  WHERE APPLICATION_NO=A.APPLICATION_NO) "+ //8
		" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A,"+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT B "+
		" WHERE  A.APPLICATION_NO=B.APPLICATION_NO(+) AND "+
		" UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_client_code+"%') AND "+
		" TO_NUMBER(INSTALLMENT_NO)=1 AND  "+
		" A.APPLICATION_NO LIKE '"+m_application_no+"%'   "+
		" GROUP BY A.APPLICATION_NO,A.FINANCE_NO,A.ACTIVATED_DATE ");
 
			
	out.println("<br>");			
				
	out.println("<table width=\"100%\"  align=\"left\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
 
	out.println(" <tr class=pdn_txtpos2 style={padding-left:0px} > ");//pdn_txtpos2
	out.println("  <td width=\"12%\" align='left'>Application No</td> ");
	out.println("  <td width=\"12%\" align='left'>Finance No</td> ");
  out.println("  <td width=\"12%\" align='left'>Activated Date</td> ");
	out.println("  <td colspan=\"2\" width=\"17%\" align='left'>New Activated Date</td> ");
	out.println("  <td width=\"12%\"  align='left'>Next Payment Date</td> ");
  out.println("  <td colspan=\"2\" width=\"17%\"  align='left'>New Next Payment Date</td> ");
	out.println(" </tr>");
		
		 
			int j=0;
     
			while(rs.next()){
			
			if(j>0 && j%2==1){
			out.println("<tr class=tr_input1 >");
			}
			else{
			out.println("<tr class=tr_input >");
			}
			
				//style=cursor:hand;cursor-color:blue onclick=show_proforma_invoice_drill('"+rs.getString(7)+"')
					out.println("<td width=\"12%\" align=left  style=cursor:hand;cursor-color:blue onclick=show_application_detail_drill('"+rs.getString(1)+"') ><u>"+rs.getString(1)+"</u></td> ");
					if(rs.getString(2).equals("-")){
					out.println("<td width=\"12%\" align=left><input class=\"txt_input\" type=\"text\" name=TXT_NEW_FINANCE_NO_"+j+"  maxlength=\"20\" size=\"20\" value=\"\" style=\"{width:120px;}\" onBlur=validate_finance_no('"+rs.getString(1)+"','"+j+"')></td>");
					}
					else{
					out.println("<td width=\"12%\" align=left><input class=\"txt_input\" type=\"text\" name=TXT_NEW_FINANCE_NO_"+j+"  maxlength=\"20\" size=\"20\" value=\""+rs.getString(2)+"\" style=\"{width:120px;}\" onBlur=validate_finance_no('"+rs.getString(1)+"','"+j+"')></td>");
					}
					
					out.println("<td width=\"12%\" align=left>"+rs.getString(3)+"</td> ");
					if(rs.getString(3).trim().equals("-")){
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+j+") ><u>Calender</u></td>"); 
					}
					else
					{
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\""+rs.getString(5)+"\" onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\"                             value=\""+rs.getString(6)+"\"           onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_ACT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\"                              value=\""+rs.getString(7)+"\"           onblur=\"check_date(document.Form1.TXT_NEW_ACT_DATE_DD_"+j+",document.Form1.TXT_NEW_ACT_DATE_MM_"+j+",document.Form1.TXT_NEW_ACT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('1',"+j+") ><u>Calender</u></td>"); 
					}
					
					out.println("<td width=\"12%\" align=left>"+rs.getString(4)+"</td> ");
					out.println("<td width=\"10%\" align=left><input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_DD_"+j+" maxlength=\"2\" size=\"2\" value=\"\" onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_MM_"+j+"  maxlength=\"2\" size=\"2\" value=\"\"                             onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
			    out.println("<input class=\"txt_input5\" type=\"text\" name=TXT_NEW_NEXT_DATE_YY_"+j+" maxlength=\"4\" size=\"4\" value=\"\"                              onblur=\"check_date(document.Form1.TXT_NEW_NEXT_DATE_DD_"+j+",document.Form1.TXT_NEW_NEXT_DATE_MM_"+j+",document.Form1.TXT_NEW_NEXT_DATE_YY_"+j+")\">");
					out.println("<td width=\"7%\" align=left style= cursor:hand;cursor-color:blue onclick=load_calendar('2',"+j+") ><u>Calender</u></td>"); 
					
          out.println("<input type=hidden name=hid_TXT_APPLICATION_NO_"+j+" value=\""+rs.getString(1)+"\">");  
			    out.println("<input type=hidden name=hid_TXT_FINANCE_NO_"+j+" value=\""+rs.getString(2)+"\">");
					out.println("<input type=hidden name=hid_PAYMENT_INTERVAL_"+j+" value=\""+rs.getString(8)+"\">");
			  	
					/*out.println("<input type=hidden name=hid_TXT_VEHICLE_NO_"+j+" value=\""+rs.getString(7)+"\">");
					out.println("<input type=hidden name=hid_TXT_INS_DATE_DD_"+j+" value=\""+rs.getString(8)+"\">");
          out.println("<input type=hidden name=hid_TXT_INS_DATE_MM_"+j+" value=\""+rs.getString(9)+"\">");
          out.println("<input type=hidden name=hid_TXT_INS_DATE_YY_"+j+" value=\""+rs.getString(10)+"\">");
					*/
     			out.println("</tr >"); 
					j=j+1;
			}
			out.println("<input type=hidden name=hid_no_rec value="+j+">");
			out.println("<input type=hidden name=hid_no_rec_app value=\"TRUE\">");
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
