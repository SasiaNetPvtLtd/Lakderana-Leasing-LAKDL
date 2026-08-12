//--
//SCREEN NAME:CREDIT PROCESS -CHANGE RENTAL AMOUNT
//CREATED BY:Nuwan De Silva
//DATE/TIME: created on 05-09-07 5.26PM
//NOTES:

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
 

public class LAKDL_AF_CR_PRO_Change_Rental_Amount extends javax.servlet.http.HttpServlet { 

	ServletOutputStream out = null;
	Connection conn;
	java.text.NumberFormat nf,nf1;
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
			
			nf1 = java.text.NumberFormat.getInstance(Locale.US);
			nf1.setMinimumFractionDigits(0);
			nf1.setMaximumFractionDigits(0);
			
			
			String m_chksql=req.getParameter("chksql");
		
			if(m_chksql.equals("main_page")){ 
			
			
				
			out.println("<HTML>"); 
			out.println("<HEAD>"); 
			out.println("<TITLE>Credit - Change Rental Amount</TITLE>"); 
			out.println("</HEAD>"); 
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">"); 
			out.println("<SCRIPT language=\"JavaScript\">"); 
			
			out.println("var b_flag=0;");
			out.println("var m_hid_row_no=0;");
			out.println("var array_net_amount=new Array();");
			out.println("var m_old_amount=0;");
			out.println("var m_new_amount=0;");
			out.println("var b_status=0;");
			out.println("var b_diff=0;"); 			
			
			
			
			out.println("function get_vector(data_vec) {");
			
			
			out.println("			if(data_vec.length>0 && document.Form1.TXT_FINANCE_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_FINANCE' ){");
			out.println("     document.Form1.TXT_APPLICATION_NO.value=data_vec[0];");
			out.println("     document.Form1.TXT_FINANCE_NO.value=data_vec[1];");
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
			out.println("get_client_details();");
			out.println("			}");
			
			out.println("			else");
			out.println("			if(data_vec.length==0 && document.Form1.TXT_APPLICATION_NO.value!=\"\" && document.Form1.hid_chk_status.value=='M_APP' ){");
			out.println("     help_button_application_no();");
			out.println("			}");
						
			out.println("			else");
			out.println("			if(data_vec.length>0 && document.Form1.TXT_OLD_AMOUNT.value!=\"\" && document.Form1.hid_chk_status.value=='M_OLD' ){");
			out.println("     assign_rental_values(data_vec);");
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
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_app_finum&data_val=\"+document.Form1.TXT_FINANCE_NO.value;");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_APP')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_LAKDL_AF_CR_PRO_app_appnum&data_val=\"+document.Form1.TXT_APPLICATION_NO.value;");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_OLD')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_PRO_net_amount_validation&data_val=\"+obj.value;");	
			
			out.println("else if(document.Form1.hid_chk_status.value=='M_NEW')");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_sql_validations?chksql=m_prime_chk_AF_CR_PRO_net_amount_validation&data_val=\"+obj.value;");	
			
			//out.println("window.open(m_url);");
			out.println("load_interface(m_url,'XML');");
			out.println("}");


			out.println("function validate_data(){"); 
			out.println("//validations goes here"); 
			out.println("if(document.Form1.TXT_NEW_AMOUNT.value==\"\" ){  "); 
			out.println("DIV_TXT_NEW_AMOUNT.style.color='red';");
			out.println("return false;"); 
			out.println("}"); 
			out.println("else{"); 
			out.println("return true;"); 
			out.println("}"); 
			out.println("}"); 
			
			
			out.println("function before_submit(){ "); 
						
			out.println("		if(validate_data()){"); 
			//out.println("ckeck_new_date();");
		//	out.println("if(b_flag==0)");
			
			//out.println("		if(confirm(\"Are you sure selected offer is not the maximum\")){ "); 
			out.println("		if(confirm(\"Are you sure you want to \"+document.Form1.hid_save_status.value+\"?\")){ "); 
			out.println("		if(validate_data()){"); 
			out.println("for (var i=0; i < document.Form1.elements.length; i++ ) {");
			out.println("document.Form1.elements[i].disabled=false;");
			out.println("}");
		//	out.println("   document.Form1.hid_no_rec.value=arr_size;");//Added By Nuwan De Silva
			out.println("		document.Form1.action='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Save_Rental_Amount_Change';");  
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
			out.println("		window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Rental_Amount?chksql=main_page';"); 
			out.println("		}"); 
			out.println("}"); 

			out.println("function new_window(){	"); 
			out.println("window.location.href='"+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Rental_Amount?chksql=main_page';"); 
			out.println("}"); 
			out.println(""); 
			out.println(""); 

			out.println("function save_window(){	"); 
			out.println("before_submit();"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_help_msg() {"); 
			out.println("    m_help_message = \"m_help_msg_AF_CR_PRO_Rental_amount_Change\";"); 
			out.println("    HelpBox_msg(m_help_message);"); 
			out.println("}"); 	
			out.println("function HelpBox_msg(m_help_message) {"); 
			out.println("	popupwin = window.showModalDialog(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"AF_PRO_CR_Help_Msg_Servlet?class_in=\"+client_name+\"AF_PRO_CR_Help_Msg_select\"+"); 
			out.println("  \"&help_message_in=\"+m_help_message);"); 
			out.println("}"); 

			out.println("function load_roll_value(m_val){"); 
			out.println("help_box.innerHTML=\" Credit Process - Change Rental Amount  - \"+m_val;"); 
			out.println("}"); 
			out.println(""); 

			out.println("function load_roll_out_value(){");
			out.println("help_box.innerHTML=\" Credit Process - Change Rental Amount - \"+document.Form1.hid_status.value;"); 
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
			
			
		///===================
		
		out.println("function HelpBox(Start,End,Hid_No,Max) {"); 
			out.println("    oBj = new MyDialog();"); 
			out.println("    oBj.valout[1]  = \" \";"); 
			out.println("    oBj.valout[2]  = \" \";"); 
			out.println("    oBj.valout[3]  = \" \";"); 
			out.println("	"); 
			
			out.println("popupwin=window.showModalDialog('"+m_class_url+"/"+m_fschema_name+"AF_PRO_CR_Help_Servlet?class_in="+m_fschema_name+"AF_PRO_CR_help_select&Sql_in='+m_sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+m_criteria+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
			out.println("	if(oBj.valout[4] ==\" \"){"); 
			out.println(" clear_data(); ");
			out.println(" }");

			out.println("	if(oBj.valout[1] !=\" \"){"); 
			out.println("	if(oBj.valout[1] !=\"Close\"){"); 
			out.println("	if(oBj.valout[1]!=\"Prev\"){"); 
			out.println("	if(oBj.valout[1]!=\"Next\"){"); 
			
			out.println("if(document.Form1.hid_help_type.value=='2'){"); 
			out.println("		help_value_assign_finance_no();"); 
			out.println("}");
			out.println("else if(document.Form1.hid_help_type.value=='3'){"); 
			out.println("		help_value_assign_application_no();"); 
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
			out.println("clear_data()");
			out.println("	}");
			out.println("}");
			out.println("if(oBj.valout[2]==' '){");
			out.println("clear_data()");
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

			out.println("function clear_data(){");
			out.println(" if(document.Form1.hid_help_type.value==\"2\"){;"); 
			out.println("document.Form1.TXT_FINANCE_NO.value='';");
			out.println("}");
			out.println(" else  if(document.Form1.hid_help_type.value==\"3\"){;"); 
			out.println("document.Form1.TXT_APPLICATION_NO.value=\"\"");
			out.println("}");
			out.println("}");
			
		///===================	
			
		/****  out.println("function HelpBox(Start,End,Hid_No,Crit,Sql,IfCount) {"); 
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
		  **********/
		
			
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
							
				  out.println("function help_button_finance_no() {"); 
					out.println("    document.Form1.hid_help_type.value=\"2\";"); 
//					out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_rental_amount','2');"); 
          out.println("    m_criteria = document.Form1.TXT_FINANCE_NO.value+\"@\"+\"Y@\";"); 
					out.println("    m_sql = \"m_help_Finance_no_Change_rental_amount\";"); 
			    out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_finance_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("document.Form1.TXT_OLD_AMOUNT.disabled=false;"); 
					out.println("get_client_details();");
					out.println("}"); 
					
					
					out.println("function help_button_application_no() {"); 
					out.println("    document.Form1.hid_help_type.value=\"3\";"); 
					//out.println("    Crit = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
					//out.println("    HelpBox('1','10','0',Crit,'m_help_Finance_no_Change_rental_amount_app_no','3');"); 
					out.println("    m_criteria = document.Form1.TXT_APPLICATION_NO.value+\"@\"+\"Y@\";"); 
					out.println("    m_sql = \"m_help_Finance_no_Change_rental_amount_app_no\";"); 
			    out.println("    HelpBox('1','10','0');"); 
					out.println("}"); 
					
					out.println("function help_value_assign_application_no() {"); 
					out.println("    document.Form1.TXT_APPLICATION_NO.value=oBj.valout[2];"); 
					out.println("if(oBj.valout[3]=='-'){"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value='';"); 
					out.println("}"); 
					out.println("else {"); 
					out.println("    document.Form1.TXT_FINANCE_NO.value=oBj.valout[3];"); 
					out.println("}"); 
					out.println("document.Form1.TXT_OLD_AMOUNT.disabled=false;"); 
					out.println("get_client_details();");
					out.println("}"); 
					
     
			out.println("function get_client_details(){");
			out.println("assignState('M_PRICE');"); 
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Rental_Amount?chksql=view_pricing&application_no=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			out.println("function get_client_details_2(val){");
			out.println("assignState('M_INST');"); 
			//out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Rental_Amount?chksql=view_pricing&application_no=\"+document.Form1.TXT_APPLICATION_NO.value;");
			out.println("m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Change_Rental_Amount?chksql=view_app&application_no=\"+document.Form1.TXT_APPLICATION_NO.value+\"&pricing_no=\"+val;");
			out.println("load_interface(m_url,'NORM');");
			//out.println("window.open(m_url);");
			out.println("}"); 
			
			//validate the new amount
			out.println("function validate_new_amount(obj){");
			out.println("check_number(obj);"); 			
			out.println("m_old_amount=0;"); 			
			out.println("m_new_amount=0;"); 			
			out.println("var m_diff=0;"); 			
			out.println("b_diff=0;"); 	
			out.println("if(obj.value!=''){"); 	
			
			out.println("m_new_amount=unformat_noobject(obj.value);"); 
			out.println("m_old_amount=unformat_noobject(document.Form1.TXT_OLD_AMOUNT.value);"); 
			out.println("m_diff=Math.abs(parseFloat(m_new_amount)- parseFloat(m_old_amount));"); 						
			//out.println("alert('Amount diff'+m_diff);"); 
			out.println("if(m_diff < 10 ){"); 
			out.println("b_diff=1;"); 			
			out.println("document.Form1.save_btn.disabled=false;"); 
			out.println("}"); 
			out.println("else {"); 
			out.println("document.Form1.save_btn.disabled=true;"); 
			out.println("}"); 
			
			out.println("}"); 
			out.println("}"); 
			
			out.println("function assign_rental_values(data_vec){");
			out.println("b_status=0;"); 						
			out.println("array_net_amount=data_vec;"); 
			out.println("m_old_amount=unformat_noobject(document.Form1.TXT_OLD_AMOUNT.value);"); 
			
			out.println("for(i=0;i<array_net_amount.length;i++){");
			out.println("if(m_old_amount==array_net_amount[i]){ "); 						
			out.println("b_status=1;"); 	
			out.println("break;"); 	
			out.println("}"); 						
			out.println("}"); 						
			out.println("if(b_status==1){"); 									
			//out.println("alert('Amount Match');"); 
			out.println("document.Form1.TXT_NEW_AMOUNT.disabled=false;"); 
			out.println("}"); 
			
			out.println("}"); 
			
			
			
			
			out.println("function get_vector_normal(http_response) {");
			
			out.println("if(document.Form1.hid_chk_status.value=='M_PRICE'){");
			out.println(" m_table.innerHTML = ''; ");
			out.println(" m_table.innerHTML = http_response; ");
			out.println("}else if(document.Form1.hid_chk_status.value=='M_INST'){");
			out.println(" m_table_inst.innerHTML = ''; ");
			out.println(" m_table_inst.innerHTML = http_response; ");
			out.println("}");
			
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
			out.println(" if((FROM_OBJ_DD.value !=\"\")&&(FROM_OBJ_DD.value !=\"\")&&(FROM_OBJ_DD.value !=\"\")){");
			out.println("if(!chk_validity(FROM_OBJ_DD,FROM_OBJ_MM,FROM_OBJ_YY,TO_OBJ_DD,TO_OBJ_MM,TO_OBJ_YY)){");
			out.println("TO_OBJ_DD.value=\"\"; ");
			out.println("TO_OBJ_MM.value=\"\"; ");
			out.println("TO_OBJ_YY.value=\"\"; ");
			out.println(" }");
			out.println(" }");
			out.println(" }");
			out.println("}");
			
			out.println("}");
			
			
			
			//==========Added By Nuwan De Silva ======================================================
			out.println("function chk_validity(FROM_DD,FROM_MM,FROM_YY,TO_DD,TO_MM,TO_YY){  ");	
		  out.println("if((FROM_DD.value!=\"\" || FROM_MM.value!=\"\" || FROM_YY.value!=\"\")  && (TO_DD.value!=\"\" || TO_MM.value!=\"\" || TO_YY.value!=\"\" )){");
      out.println("if((parseInt(FROM_DD.value))>=(parseInt(TO_DD.value))){");
      out.println("if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
      out.println("if((parseInt(FROM_YY.value))<=(parseInt(TO_YY.value))){");
      out.println(" if(((parseInt(FROM_DD.value))<(parseInt(TO_DD.value)))&&");
      out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
      out.println("((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
      out.println("}");
      out.println("else if(((parseInt(FROM_DD.value))>(parseInt(TO_DD.value)))&&");//>=
      out.println("((parseInt(FROM_MM.value))==(parseInt(TO_MM.value)))&&");
      out.println(" ((parseInt(FROM_YY.value))==(parseInt(TO_YY.value)))){");
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
      out.println("   if((parseInt(FROM_YY.value))>=(parseInt(TO_YY.value))){");
        out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println("   }");
      out.println("   else{");
      out.println("   } ");
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println(" if((parseInt(FROM_MM.value))<=(parseInt(TO_MM.value))){");
      out.println("  if((FROM_YY.value)<=(TO_YY.value)){");
      out.println(" }");
      out.println(" else{");
      out.println("      alert('New Next Payment Date should be greater than New Activated Date');");
			out.println("return false;"); 
      out.println(" }");
      out.println("}");
      out.println("else{");
      out.println("   if((parseInt(FROM_YY.value))<(parseInt(TO_YY.value))){ ");
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
			out.println("<INPUT TYPE='Hidden' NAME='Hid_scr_name' VALUE=\"AF_CR_PRO_CHANGE_RENTAL_AMOUNT\">"); 
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
			out.println("<td align='left' class='pdn_txtpos2' style='height: 18px' id='help_box'>Credit Process - Change Rental Amount </td>"); 
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
			out.println("<td width='10%' align='center'><input type=\"button\" name='save_btn' class='mainbut' onMouseout='load_roll_out_value();' onMouseOver='load_roll_value(\"Save\");'  onClick='save_window()' value=\"Save\" disabled ></td>");  
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
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_OLD_AMOUNT'  class=div_input>Old Amount </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_OLD_AMOUNT' maxlength='25' size='15' style=\"{text-align:right;}\" onblur=\"check_number(this), assignState('M_OLD'),makeRequest(document.Form1.TXT_APPLICATION_NO)\" disabled></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
			
			out.println("<tr class=tr_input>"); 
			out.println("<td width='30%' ><DIV id='DIV_TXT_NEW_AMOUNT'  class=div_input>New Amount * </DIV></td>"); 
			out.println("<td width='40%' ><input class='txt_input' type='text' name='TXT_NEW_AMOUNT' maxlength='20' size='15' style=\"{text-align:right;}\" onblur=\"validate_new_amount(this)\" disabled ></td>"); 
			out.println("<td width='*%'></td>");
			out.println("</tr>"); 
					
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table'></DIV></td>");
		  out.println("</tr>"); 
			out.println("</table>"); 
			
			out.println("<table align='center' width='100%' class='table'>"); 
			out.println("<tr>");  
			out.println("<td width=\"100%\"><DIV ID='m_table_inst'></DIV></td>");
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
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate_v2.js'></SCRIPT>"); 
			
      out.println("</body>"); 
			out.println("</html>"); 
			out.flush();
			}

		else if(m_chksql.equals("view_app")){		
		String m_application_no=req.getParameter("application_no").trim();
		String m_pricing_no=req.getParameter("pricing_no").trim();
		
		stmt = conn.createStatement ();

	 rs=stmt.executeQuery("SELECT  "+	
	 " PRICING_NO,TO_NUMBER(INSTALLMENT_NO)+1,NET_RENTAL_AMOUNT,TO_CHAR(RENTAL_DATE,'DD-MM-YYYY') "+
	 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
	 " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
	"  AND  UPPER(PRICING_NO)=UPPER('"+m_pricing_no+"') "+
	 " GROUP BY APPLICATION_NO,PRICING_NO,NET_RENTAL_AMOUNT,INSTALLMENT_NO,RENTAL_DATE "+
	 " ORDER BY PRICING_NO,TO_NUMBER(INSTALLMENT_NO) ");
		

		
		
			out.println("<br>");			
						
			out.println("<table width=\"50%\"  align=\"left\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
			
			out.println(" <tr class=pdn_txtpos2 style={padding-left:0px} > ");//pdn_txtpos2
			out.println("  <td width=\"15%\" align='left'>Installment No</td> ");
			out.println("  <td width=\"15%\" align='left'>Rental Date</td> ");
			out.println("  <td width=\"20%\" align='right'>Net Amount</td> ");
			out.println(" </tr>");
				
				 
					int j=0;
		     
					while(rs.next()){
					
					if(j>0 && j%2==1){
					out.println("<tr class=tr_input1 >");
					}
					else{
					out.println("<tr class=tr_input >");
					}
					
					
						out.println("  <td width=\"15%\" align='left'>"+rs.getString(2)+"</td> ");
						out.println("  <td width=\"15%\" align='left'>"+rs.getString(4)+"</td> ");
						out.println("  <td width=\"20%\" align='right'>"+nf1.format(rs.getDouble(3))+"</td> ");
							
		     			out.println("</tr >"); 
							j=j+1;
					}
					
		      out.println("</table >"); 
		}

	else if(m_chksql.equals("view_pricing")){		
	String m_application_no=req.getParameter("application_no").trim();
	stmt = conn.createStatement ();
	
	 rs=stmt.executeQuery("SELECT  "+	
	 " DISTINCT PRICING_NO,SUM(NET_RENTAL_AMOUNT) "+
	 " FROM "+m_schema_name+".AF_CO_PRO_APP_INSTALLMENT "+
	 " WHERE UPPER(APPLICATION_NO)=UPPER('"+m_application_no+"') "+
	 " GROUP BY APPLICATION_NO,PRICING_NO,PRO_INVOICE_NO "+
	 " ORDER BY PRICING_NO ");
			
		out.println("<br>");			
		out.println("<table width=\"50%\"  align=\"left\" class=\"table\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> ");
		out.println(" <tr class=pdn_txtpos2 style={padding-left:0px} > ");//pdn_txtpos2
		out.println("  <td width=\"15%\" align='left'>Pricing No</td> ");
		out.println("  <td width=\"35%\" align='right'>Total Net Amount</td> ");
	 	out.println(" </tr>");
			
			 
				int j=0;
	     
				while(rs.next()){
				
				if(j>0 && j%2==1){
				out.println("<tr class=tr_input1 >");
				}
				else{
				out.println("<tr class=tr_input >");
				}
				
				
					out.println("  <td width=\"15%\" align='left'  style= cursor:hand;cursor-color:blue onclick=show_pricing_drill('"+rs.getString(1)+"')   ><u>"+rs.getString(1)+"</u></td> ");
					out.println("  <td width=\"35%\" align='right' style= cursor:hand;cursor-color:blue onclick=get_client_details_2('"+rs.getString(1)+"') ><u>"+nf1.format(rs.getDouble(2))+"</u></td> ");
				 	out.println("</tr >"); 
						j=j+1;
				}
				
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
